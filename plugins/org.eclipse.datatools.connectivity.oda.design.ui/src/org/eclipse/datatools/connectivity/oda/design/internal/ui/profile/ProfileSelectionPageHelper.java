/*
 *************************************************************************
 * Copyright (c) 2007, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ExportProfilesDialog;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSourceDesignSessionBase.IDesignNameValidatorBase;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSourceDesignSessionBase.ProfileReferenceBase;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.TextProcessorWrapper;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;
import org.eclipse.datatools.connectivity.oda.profile.internal.ProfileCategoryUtil;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer.Filter;
import org.eclipse.datatools.connectivity.ui.actions.ExportProfileViewAction;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * Internal UI helper that provides the UI controls and behavior for 
 * both the wizard page and editor page for selecting 
 * a connection profile instance.
 * @since   3.0.4
 */
class ProfileSelectionPageHelper
{
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$
    private static final String CONTEXT_ID_CONNECTIONPROFILE = 
        "org.eclipse.datatools.oda.cshelp.Wizard_ConnectionProfile_ID";//$NON-NLS-1$

    private static final int TREE_ITEM_STYLE = SWT.NONE;
    private static final int BUTTON_MIN_WIDTH = 80;
    
    private WizardPage m_wizardPage;
    private PreferencePage m_propertyPage;

    private transient Label m_dataSourceNameLabel = null;
    private transient Text m_connectionProfilePath = null;
    private transient Text m_dataSourceDesignNameControl = null;
    private transient Button m_linkRefCheckBox = null;
    private transient String m_profileID;
    private transient String m_odaDataSourceID;
    private transient Button m_useDefaultDSNameCheckBox = null;
    private transient Tree m_odaDataSourceTree = null;
    private transient String m_treeFilter;
    private transient SortedSet m_dataSourceIDs = null;
    private transient IDesignNameValidatorBase m_designNameValidator;
    
    private String m_dataSourceDesignName = EMPTY_STRING;

    ProfileSelectionPageHelper( WizardPage page )
    {
        m_wizardPage = page;
    }
    
    ProfileSelectionPageHelper( PropertyPage page )
    {
        m_propertyPage = page;
    }
    
    Control createControl( Composite parent )
    {
        Composite container = new Composite( parent, SWT.NONE );
        GridLayout layout = new GridLayout( );
        layout.numColumns = 3;
        container.setLayout( layout );

        setupConnectionProfilePath( container );
        setupLinkCheckBox( container );
        setupTreeViewer( container );
        setupDataSourceName( container );
        
        PlatformUI.getWorkbench().getHelpSystem()
            .setHelp( container, CONTEXT_ID_CONNECTIONPROFILE );

        return container;
    }
    
    void initControl( ProfileSelection selectedProfile )
    {
        if( selectedProfile == null )
            return;     // nothing to initialize        

        // limits profile tree to include the selected oda data source type
        m_treeFilter = selectedProfile.getOdaDataSourceId();

        ProfileReferenceBase profileRef = selectedProfile.getProfileRef();
        if( profileRef != null )
        {       
            String profileStorePath = profileRef.getStorageFilePath();
            if( profileStorePath != null )
            {
                // triggers tree population
                setConnProfilePathControlText( profileStorePath );
                
                TreeItem profileItem = 
                    findProfileInTree( selectedProfile.getOdaDataSourceId(),
                                        profileRef.getInstanceId() );
                if( profileItem != null )
                {
                    assert( m_odaDataSourceTree != null );
                    m_odaDataSourceTree.setSelection( profileItem );
                    m_odaDataSourceTree.showSelection();

                    m_useDefaultDSNameCheckBox.setSelection( 
                            profileItem.getText().equals( 
                                selectedProfile.getDataSourceDesignName() ) );
                    m_profileID = profileItem.getData().toString();
                    m_odaDataSourceID = profileItem.getParentItem().getData().toString();
                    setExternalLinkOption( m_odaDataSourceID );
                    setPageComplete( true );
                }
            }
            
            m_linkRefCheckBox.setSelection( profileRef.maintainExternalLink() ); 
        }
        
        // initializes the designName variable first, which is checked 
        // by the name control's modify listener
        m_dataSourceDesignName = selectedProfile.getDataSourceDesignName();
        m_dataSourceDesignNameControl.setText( m_dataSourceDesignName );
        
        // when editing an existing data source design, hide controls to
        // disallow user from changing the design name
        if( inEditMode() )
            setDataSourceNameEditorVisible( false );
    }
    
    /**
     * Set the call-back validator provided by an ODA host, for validation of
     * the data source design named collected in the UI control.
     */
    void setDesignNameValidator( IDesignNameValidatorBase validator )
    {
        m_designNameValidator = validator;
    }
    
    /**
     * Collects the current selection of connection profile instance.
     * @return  the current selection if no error is found;
     *          null otherwise
     */
    ProfileSelection collectProfileSelection()
    {
        if( ! hasSelectedProfile() || ! isPageComplete() )
            return null;
            
        return new ProfileSelection(
                m_odaDataSourceID,
                m_dataSourceDesignName,
                new ProfileReferenceBase( m_profileID,
                        new Path( getConnProfilePathControlText() ).toFile( ),
                        m_linkRefCheckBox.getSelection() ) );
     }
    
    private boolean isPageComplete()
    {
        boolean isPageComplete = true;
        if ( m_wizardPage != null )
            isPageComplete = m_wizardPage.isPageComplete();
        else if ( m_propertyPage != null )
            isPageComplete = m_propertyPage.isValid();
        else
            isPageComplete = ( getMessageType() != IMessageProvider.ERROR );
        
        if( ! isPageComplete )      // page itself is not complete
            return isPageComplete;  // no need to do extra checking
        
        // the page itself considers itself complete, next 
        // check if external reference is selected, must have a selected profile
        if( m_linkRefCheckBox != null && m_linkRefCheckBox.getSelection() )
            isPageComplete = hasSelectedProfile();
        
        return isPageComplete;
    }
    
    private void setPageComplete( boolean complete )
    {
        if( m_wizardPage != null )
            m_wizardPage.setPageComplete( complete );
        else if( m_propertyPage != null )
            m_propertyPage.setValid( complete );
        
        if( complete )
            setMessage( EMPTY_STRING, IMessageProvider.NONE );
        else
            setDefaultMessageAsError( true );
    }

    private void setMessage( String newMessage, int newType )
    {
        if( m_wizardPage != null )
            m_wizardPage.setMessage( newMessage, newType );
        else if( m_propertyPage != null )
        {
            // use default title instead of leaving the message area empty
            if( newMessage == EMPTY_STRING && newType == IMessageProvider.NONE )
                newMessage = Messages.profilePage_pageTitle;    
            m_propertyPage.setMessage( newMessage, newType );
        }
    }

    private void setDefaultMessageAsError( boolean isError )
    {
        int newType = isError ? IMessageProvider.ERROR : IMessageProvider.NONE;
        setMessage( Messages.profilePage_selectProfileDefaultMessage, newType );
    }
    
    int getMessageType()
    {
        if( m_wizardPage != null )
            return m_wizardPage.getMessageType();
        if( m_propertyPage != null )
            return m_propertyPage.getMessageType();       
        return IMessageProvider.NONE;        
    }

    private Shell getShell()
    {
        if( m_wizardPage != null )
            return m_wizardPage.getShell();
        assert( m_propertyPage != null );
        return m_propertyPage.getShell();
    }
    
    private void setupConnectionProfilePath( Composite composite )
    {
        Label label = new Label( composite, SWT.NONE );
        label.setText( Messages.profilePage_label_profileStore );
        label.setLayoutData( new GridData( GridData.HORIZONTAL_ALIGN_BEGINNING ) );

        m_connectionProfilePath = new Text( composite, SWT.BORDER );
        GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
        layoutData.widthHint = 260;
        m_connectionProfilePath.setLayoutData( layoutData );
        m_connectionProfilePath.addModifyListener( new ModifyListener( ) 
        {
            public void modifyText( ModifyEvent e )
            {
                BusyIndicator.showWhile( getShell( ) == null ? null
                        : getShell( ).getDisplay( ), new Runnable( ) {

                    public void run( )
                    {
                       populateTree( );
                       
                       // clear any previously selected profile settings
                       clearSelectedProfile();
                       setSelectedDataSourceName( EMPTY_STRING );
                       boolean isError = ( hasConnectionProfilePath() );
                       setDefaultMessageAsError( isError );
                    }
                } );
            }
        } );

        // Browse... button
        Button browseButton = new Button( composite, SWT.PUSH );
        browseButton.setText( Messages.profilePage_button_browse );
        {
            GridData data = new GridData();
	        data.widthHint = computeButtonWidth( browseButton );
            browseButton.setLayoutData( data );
        }
        browseButton.addSelectionListener( new SelectionAdapter() 
        {
            public void widgetSelected( SelectionEvent e )
            {
				FileDialog dialog = new FileDialog( getShell() );
				String text = dialog.open();
				if( text != null )
				    setConnProfilePathControlText( text );
			}
        } );
        
        // New... button
        createNewProfileStoreButton( composite );
    }

    private Button createNewProfileStoreButton( Composite parent )
    {
        Button newButton = new Button( parent, SWT.PUSH );
        newButton.setText( Messages.profilePage_button_new );
        {
            GridData data = new GridData();
            data.horizontalSpan = 3;
            data.horizontalAlignment = SWT.RIGHT;
		    data.widthHint = computeButtonWidth( newButton );
            newButton.setLayoutData( data );
        }
        
        newButton.addSelectionListener( new SelectionAdapter() 
        {
            public void widgetSelected( SelectionEvent e )
            {
                handleNewProfileStore();
            }
            
            private void handleNewProfileStore()
            {
                CreateProfileStoreAction createAction = 
                    new CreateProfileStoreAction( getShell() );
                createAction.run();
                if( createAction.isCompleted() )
                {
                    // copy the newly created profile store file path
                    ProfileStoreCreationDialog dlg = createAction.getProfileStoreCreationDialog();
                    if( dlg != null && dlg.getFile() != null )
                        setConnProfilePathControlText( dlg.getFile().getPath() );                            
                }
            }
            
            final class CreateProfileStoreAction extends ExportProfileViewAction
            {
                private ProfileStoreCreationDialog m_dialog;
                
                CreateProfileStoreAction( Shell dialogShell )
                {
                    super();
                    init( dialogShell );
                }

                /* (non-Javadoc)
                 * @see org.eclipse.datatools.connectivity.ui.actions.ExportProfileViewAction#createExportProfilesDialog(org.eclipse.swt.widgets.Shell)
                 * @override base method
                 */
                protected ExportProfilesDialog createExportProfilesDialog( Shell parentShell )
                {
                    m_dialog = new ProfileStoreCreationDialog( parentShell );
                    m_dialog.setBlockOnOpen( true );
                    return m_dialog;
                }
                
                ProfileStoreCreationDialog getProfileStoreCreationDialog()
                {
                    return m_dialog;
                }
            }
        } );
        
        return newButton;
    }

    /**
     * Setup TreeViewer layout
     */
    private void setupTreeViewer( Composite composite )
    {
        m_odaDataSourceTree = new Tree( composite, SWT.BORDER
                | SWT.SINGLE | SWT.V_SCROLL );
        m_odaDataSourceTree.setLayout( new GridLayout( ) );
        GridData layoutData = new GridData( GridData.FILL_BOTH );
        layoutData.horizontalSpan = 3;
        m_odaDataSourceTree.setLayoutData( layoutData );

        m_odaDataSourceTree.addSelectionListener( new SelectionAdapter( ) 
        {
            public void widgetSelected( SelectionEvent event )
            {
                handleTreeSelection( );
            }

            private void handleTreeSelection( )
            {
                TreeItem item = getSelectedProfileItem();

                if( item == null )
                {
                    clearSelectedProfile();
                    setSelectedDataSourceName( EMPTY_STRING );
                    setExternalLinkOption( null );
                    setDefaultMessageAsError( true );
                }
                else
                {
                    m_profileID = item.getData().toString();
                    m_odaDataSourceID = item.getParentItem().getData().toString();
                    setSelectedDataSourceName( item.getText() );
                    setExternalLinkOption( m_odaDataSourceID );
                }
            }
        } );

        populateTree( );
    }

    private void setSelectedDataSourceName( String name )
    {
        if( m_dataSourceDesignNameControl == null )
            return;     // nothing to set
        
        String trimmedName = name.trim();
        m_dataSourceDesignNameControl.setText( trimmedName );   
        // setText triggers its modifyListener to update data source design name if appropriate
    }

    /**
     * For use by the tree viewer selection listener to trigger setting 
     * the external link checkbox, based on the selected profile's oda category
     */
    private void setExternalLinkOption( String odaDataSourceId )
    {         
        //by default, if no category is specified, it is optional to maintain an external link 
        OdaProfileCategoryInfo categoryInfo = getOdaProfileCategoryInfo( odaDataSourceId );
        boolean isExternalLinkOptional = ( categoryInfo != null ) ?
                                            categoryInfo.isExternalLinkOptional() : true;
                                            
        // set the external link control accordingly
        setExternalLinkOptionControl( isExternalLinkOptional );
    }
    
    private boolean hasSelectedProfile()
    {
        return ( m_profileID != null && m_odaDataSourceID != null );
    }
    
    private void clearSelectedProfile()
    {
        m_profileID = null;
        m_odaDataSourceID = null;
    }
    
    /**
     * Updates the data source design name only if creating a new design.
     */
    private void setDataSourceDesignName( String name )
    {
        /* when editing an existing data source design, 
         * do not allow to change the design name from this page
         */
        if( inEditMode() )
            return;
        
        // updates design name only when creating a new design
        assert( name != null );
        m_dataSourceDesignName = name.trim();
    }

    /**
     * Populate the connection profile instances in the tree viewer
     */
    private void populateTree( )
    {
        resetTreeViewer();
        if( ! hasConnectionProfilePath() )
            return;

        // populate tree with ODA extensions' profile instances
        OdaProfileExplorer.getInstance().refresh(); // reset cached profiles in instance
        
        SortedSet odaCategoryInfoSet = getOdaCategoryInfoSet();

        // iterate thru each type of applicable ODA data source categories
        TreeItem odaRoot = null;
        Iterator iterator = odaCategoryInfoSet.iterator();
        while( iterator.hasNext() )
        {
            OdaProfileCategoryInfo categoryInfo = (OdaProfileCategoryInfo) iterator.next();

            TreeItem categoryItem = 
                createCategoryTreeItems( m_odaDataSourceTree, odaRoot, categoryInfo  );
            
            if( categoryItem != null )
            {
                if( odaRoot == null && categoryInfo.hasOdaParentCategory() )
                    odaRoot = categoryItem.getParentItem();   // reuse same parent item for next ODA category
                categoryItem.setExpanded( true );
                m_odaDataSourceTree.showItem( categoryItem ); // scroll to the latest category
            }
        }
        
        // if the specified profile store file has no profile instances at all,
        // create an empty ODA root item in the tree for UI visibility
        if( m_odaDataSourceTree.getItemCount() == 0 )
            createOdaRootItem( m_odaDataSourceTree );
    }
    
    /**
     * Reset TreeViwewer layout
     */
    private void resetTreeViewer( )
    {
        if( m_odaDataSourceTree != null )
            m_odaDataSourceTree.removeAll( );
    }
    
    /**
     * Returns the current selected tree item that represents a 
     * connection profile instance.
     * @return  selected profile tree item; or null if no selection
     *          is made, or the selected item is not a profile item
     */
    private TreeItem getSelectedProfileItem()
    {
        TreeItem[] items = m_odaDataSourceTree.getSelection();
        if( items.length == 0 )
            return null;
        
        TreeItem item = items[0];
        if( item.getParentItem( ) == null || 
            item.getItemCount() > 0 )
            return null;    // selected item is not a profile instance
        
        return item;
    }
    
    /**
     * Search and return the tree item that represents the specified profile
     * instance based on its instanceId.
     * The odaDataSourceId type is specified to optimize the search for
     * matching tree branch. 
     * @return  selected profile tree item; or null if no match is found
     */
    private TreeItem findProfileInTree( String odaDataSourceId, String profileId )
    {
        if( profileId == null )
            return null;
        
        TreeItem dsCategory = findOdaCategoryInTree( odaDataSourceId );
        if( dsCategory == null )
            return null;    // specified oda data source category not in tree
        
        // found the specified odaDataSourceId category
        TreeItem[] profileItems = dsCategory.getItems();
        for( int i = 0; i < profileItems.length; i++ )
        {
            TreeItem profileItem = profileItems[i];
            if( profileItem.getData().toString().equals( profileId ) )
                return profileItem;
        }

        // no match is found
        return null;
    }

    /**
     * Search and return the tree item that represents the specified 
     * odaDataSourceId type category.
     * @return  selected oda category tree item; or null if no match is found
     */
    private TreeItem findOdaCategoryInTree( String odaDataSourceId )
    {
        if( m_odaDataSourceTree == null || odaDataSourceId == null )
            return null;
        
        TreeItem[] topItems = m_odaDataSourceTree.getItems();
        if( topItems.length == 0 )
            return null;
        
        // assumes an oda category is at one of the top 2 levels in tree
        for( int i = 0; i < topItems.length; i++ )
        {
            TreeItem topLevelItem = topItems[i];
            if( isOdaCategoryItem( topLevelItem, odaDataSourceId ) )
                return topLevelItem;
            
            TreeItem[] subCategories = topLevelItem.getItems();
            for( int j = 0; j < subCategories.length; j++ )
            {
                TreeItem subCategory = subCategories[j];
                if( isOdaCategoryItem( subCategory, odaDataSourceId ) )
                    return subCategory;
            }
        }
        
        return null;        
    }

    private boolean isOdaCategoryItem( TreeItem item, String odaDataSourceId )
    {
        if( item.getItemCount() == 0 )
            return false;   // not a category folder item
        
        if( item.getData() != null &&
            item.getData().toString().equals( odaDataSourceId ) )
            return true;
        return false;
    }

    private SortedSet getOdaCategoryInfoSet()
    {
        if( m_dataSourceIDs == null )
            m_dataSourceIDs = createOdaProfileCategoryInfoSet();
        return m_dataSourceIDs;
    }
    
    /**
     * Creates and returns a filtered collection of OdaProfileCategoryInfo
     * on ODA data source extensions found in the extensions registry,
     * sorted by their category display names.
     */
    private SortedSet createOdaProfileCategoryInfoSet()
    {
        Filter profileTypeFilter = ManifestExplorer.createFilter();
        profileTypeFilter.setMissingDataSetTypesFilter( true );
        profileTypeFilter.setDeprecatedFilter( true );
        profileTypeFilter.setHideWrapper( false );
        
        Properties dsIdentifiers = ManifestExplorer.getInstance()
                .getDataSourceIdentifiers( profileTypeFilter );
        
        TreeSet sortedSet = new TreeSet();
        Iterator keyIter = dsIdentifiers.keySet().iterator();
        while( keyIter.hasNext() )
        {
            String odaDataSourceId = keyIter.next().toString();
            if( m_treeFilter != null && ! m_treeFilter.equals( odaDataSourceId ) )
                continue;   // skip oda data source type not in filter
                    
            // sort the identifiers by their category display name
           sortedSet.add( 
                    new OdaProfileCategoryInfo( odaDataSourceId,
                            dsIdentifiers.getProperty( odaDataSourceId ) ));
        }

        return sortedSet;
    }
    
    private OdaProfileCategoryInfo getOdaProfileCategoryInfo( String odaDataSourceId )
    {
        if( odaDataSourceId == null )
            return null;
        
        Iterator iterator = getOdaCategoryInfoSet().iterator();
        while( iterator.hasNext() )
        {
            OdaProfileCategoryInfo categoryInfo = (OdaProfileCategoryInfo) iterator.next();
            if( odaDataSourceId.equals( categoryInfo.getOdaDataSourceId() ))
                return categoryInfo;
        }
        
        return null;    // no match found
    }
    
    private Map getProfileIdentifiersByCategory( String categoryId )
    {
        try
        {
            return DesignSessionUtil.getProfileIdentifiersByCategory( categoryId,
                    new Path( getConnProfilePathControlText() ).toFile( ) );
        }
        catch ( OdaException ex )
        {
            setMessage( Messages.profilePage_error_invalidProfileStorePath, IMessageProvider.ERROR );
        }

        return null;
    }

    private TreeItem createOdaRootItem( Tree parent )
    {
        TreeItem odaRoot = new TreeItem( parent, TREE_ITEM_STYLE );
        odaRoot.setText( Messages.profilePage_odaTreeName );
        return odaRoot;
    }
    
    /**
     * Create child tree items for the given ODA data source category.
     */    
    private TreeItem createCategoryTreeItems( Tree parent, TreeItem parentItem,
            OdaProfileCategoryInfo categoryInfo ) 
    {
        String categoryId = categoryInfo.getEffectiveCategoryId();       
        Map profileIds = getProfileIdentifiersByCategory( categoryId );
        // if no profile instances found under given category
        if( profileIds == null || profileIds.isEmpty() )  
            return null;             // done; no need to create a child tree item for category
        
        // create a tree item for the specified category
        
        // first determine which parent item to use
        if( categoryInfo.hasOdaParentCategory() )
        {
            // if this is under the ODA parent category, put it under an ODA parent item
            if( parentItem == null )
                parentItem = createOdaRootItem( parent );
        }
        else    // not under an ODA parent category
        {
            // create category item directly in tree as a top level item
            // TODO - handle sub-categories
            parentItem = null;
        }
        
        TreeItem categoryItem = ( parentItem != null ) ?
                new TreeItem( parentItem, TREE_ITEM_STYLE ) :
                new TreeItem( parent, TREE_ITEM_STYLE );
            
        // the tree view expects a profile instance's parent item to contain an odaDataSourceId in item data
        categoryItem.setData( categoryInfo.getOdaDataSourceId() );
        categoryItem.setText( categoryInfo.getDisplayName() );
        
        // create children items for each of its profile instances
        createChildTreeItems( categoryItem, profileIds, TREE_ITEM_STYLE );
        
        return categoryItem;
    }
        
    /**
     * Build a child tree item for each connection profile identifier in the specified Map.
     */
    private void createChildTreeItems( TreeItem parentItem, Map profileIds, int style )
    {
        if( profileIds == null )
            return;

        Iterator iterator = profileIds.keySet().iterator();
        while( iterator.hasNext() )
        {
            Object profileInstanceId = iterator.next();
            TreeItem item = new TreeItem( parentItem, style );
            item.setData( profileInstanceId );
            // profile instance display name
            item.setText( profileIds.get( profileInstanceId ).toString( ) );   
        }
    }

    /**
     * Setup DataSourceName layout
     */
    private void setupDataSourceName( Composite composite )
    {
        GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
        layoutData.horizontalSpan = 3;
        m_useDefaultDSNameCheckBox = new Button( composite, SWT.CHECK );
        m_useDefaultDSNameCheckBox.setText( Messages.profilePage_checkboxLabel_useDefaultName );
        m_useDefaultDSNameCheckBox.setSelection( true );
        m_useDefaultDSNameCheckBox.setLayoutData( layoutData );
        m_useDefaultDSNameCheckBox.addSelectionListener( new SelectionAdapter( ) 
        {
            public void widgetSelected( SelectionEvent e )
            {
                if( m_useDefaultDSNameCheckBox.getSelection() )
                {
                    // reset the design name to the default
                    TreeItem item = getSelectedProfileItem();
                    if( item != null )
                        setSelectedDataSourceName( item.getText() );
                }
                
                enableDataSourceNameEditor( ! m_useDefaultDSNameCheckBox.getSelection() );
            }
        } );

        m_dataSourceNameLabel = new Label( composite, SWT.NONE );
        m_dataSourceNameLabel.setText( Messages.profilePage_label_dataSourceName );
        m_dataSourceNameLabel.setLayoutData( new GridData( GridData.HORIZONTAL_ALIGN_BEGINNING ) );

        layoutData = new GridData( GridData.FILL_HORIZONTAL );
        layoutData.horizontalSpan = 2;
        m_dataSourceDesignNameControl = new Text( composite, SWT.BORDER );
        m_dataSourceDesignNameControl.setLayoutData( layoutData );
        m_dataSourceDesignNameControl.addModifyListener( new ModifyListener( ) 
        {
            public void modifyText( ModifyEvent e )
            {
                setDataSourceDesignName( m_dataSourceDesignNameControl.getText() );

                String invalidMessage = null;
                if( isDesignNameBlank() )
                {
                    setPageComplete( false );
                    setMessage( Messages.profilePage_error_emptyName, IMessageProvider.ERROR );
                }
                else if( ( invalidMessage = validateAndGetErrorMessage() ) != null )
                {
                    setPageComplete( false );
                    setMessage( invalidMessage, IMessageProvider.ERROR );
                }
                else    // name value is ok
                {
                    // when creating a new data source, the name must be associated
                    // with a profile selection either by import or reference
                    boolean isNameOk = inEditMode() ? true : hasSelectedProfile();
                    setPageComplete( isNameOk );
                }
            }
        } );

        enableDataSourceNameEditor( false );
    }
    
    private void setupLinkCheckBox( Composite composite )
    {
        m_linkRefCheckBox = new Button( composite, SWT.CHECK );
        m_linkRefCheckBox.setToolTipText( Messages.profilePage_checkboxTooltip_maintainLink );
        GridData data = new GridData( );
        data.horizontalSpan = 3;
        m_linkRefCheckBox.setLayoutData( data );
        m_linkRefCheckBox.setText( Messages.profilePage_checkboxLabel_maintainLink );
        m_linkRefCheckBox.setSelection( true );
        m_linkRefCheckBox.addSelectionListener( new SelectionAdapter( ) 
        {
            public void widgetSelected( SelectionEvent e )
            {
                if( ! inEditMode() || m_odaDataSourceTree == null )
                    return;     // nothing to do

                // editing of selected profile is allowed only if a link is maintained
                boolean maintainLink = m_linkRefCheckBox.getSelection();
                m_odaDataSourceTree.setEnabled( maintainLink );

                if( isPageComplete() )
                    setMessage( EMPTY_STRING, IMessageProvider.NONE );
                else
                    setDefaultMessageAsError( maintainLink );
            }
        } );
    }
    
    /**
     * Validate whether the data source design name is blank
     */
    private boolean isDesignNameBlank( )
    {
        return m_dataSourceDesignName == null || m_dataSourceDesignName.length() == 0;
    }

    /**
     * Validate whether the data source design name is valid.  
     * @return  null if the name is valid; 
     *          otherwise, return corresponding error message
     */
    private String validateAndGetErrorMessage()
    {
        if( m_designNameValidator != null )
        {
            try
            {
                if( m_designNameValidator.isValid( m_dataSourceDesignName ) )
                    return null;    // is valid, no error message
                else    // validator did not throw exception to provide error message, 
                        // use default error message
                    return Messages.profilePage_error_invalidName;
            }
            catch( OdaException ex )
            {
                return ex.getMessage();    // is invalid
            }
        }
        
        return null;    // is valid, no error message
    }

    /**
     * Enable DataSourceNameEditor
     */
    private void enableDataSourceNameEditor( boolean enabled )
    {
        m_dataSourceNameLabel.setEnabled( enabled );
        m_dataSourceDesignNameControl.setEnabled( enabled );
    }
    
    private void setDataSourceNameEditorVisible( boolean visible )
    {
        m_useDefaultDSNameCheckBox.setVisible( visible );
        m_dataSourceNameLabel.setVisible( visible );
        m_dataSourceDesignNameControl.setVisible( visible );
    }

    /**
     * Set the checkbox state for maintaining a link to the selected connection profile
     * according to the specified state.
     * @param isExternalLinkOptional   true indicates whether an user is allowed to set/unset
     *              the external link option; false means that an external link is required
     */
    private void setExternalLinkOptionControl( boolean isExternalLinkOptional )
    {
        m_linkRefCheckBox.setEnabled( isExternalLinkOptional );
        if( ! isExternalLinkOptional )  // requires to maintain an external link
            m_linkRefCheckBox.setSelection( true );
    }
    
    private boolean inEditMode()
    {
        return ( m_propertyPage != null );
    }
    
    private boolean hasConnectionProfilePath( )
    {
        return getConnProfilePathControlText().trim().length() > 0;
    }

    /**
     * Process the given text in bi-directional locale for rendering in the
     * connection profile store file path UI text control.  
     * @param text
     */
    private void setConnProfilePathControlText( String text )
    {
        String localizedText = TextProcessorWrapper.process( text );
        m_connectionProfilePath.setText( localizedText );
    }
    
    /**
     * Return a de-processed text value in the connection profile store file path 
     * UI text control.
     * @return
     */
    private String getConnProfilePathControlText()
    {
        String localizedText = m_connectionProfilePath.getText();
        return TextProcessorWrapper.deprocess( localizedText );
    }

    /**
     * Compute the preferred width of the specified button based on
     * its label size or the minimum button width, whichever is wider.
     * @param button    
     * @return      preferred button width
     */
    static int computeButtonWidth( Button button )
    {
        int defaultWidth = button.computeSize( SWT.DEFAULT, SWT.DEFAULT ).x;
        return ( defaultWidth < BUTTON_MIN_WIDTH ) ? 
                BUTTON_MIN_WIDTH : defaultWidth;
    }
    
    /**
     * Internal class that encapsulates related identifier information on 
     * an ODA connection profile category.  
     * It provides info for a category item in the connection profile tree viewer,
     * and can be used for Comparable sorting in a collection.
     * @since 3.0.6
     */
    private class OdaProfileCategoryInfo implements Comparable
    {
        private String m_odaDataSourceId;
        private boolean m_hasOdaParentCategory;
        private String m_categoryDisplayName;
        private String m_effectiveCategoryId;
        
        OdaProfileCategoryInfo( String id, String profileDisplayName )
        {
            m_odaDataSourceId = id;

            ICategory profileCategory = ProfileCategoryUtil.getCategory( m_odaDataSourceId );
            m_hasOdaParentCategory = ProfileCategoryUtil.hasODAParentCategory( profileCategory );

            // if parent category is not ODA type, use the parent category info
            boolean useParentCategory = ! m_hasOdaParentCategory;
            m_categoryDisplayName = 
                getEffectiveDisplayName( profileCategory, useParentCategory,
                        profileDisplayName );  
            m_effectiveCategoryId = 
                getEffectiveCategoryId( profileCategory, useParentCategory );
        }

        private String getEffectiveDisplayName( ICategory profileCategory,
                    boolean useParentCategory, String defaultDisplayName )
        {
            if( profileCategory == null )
                return defaultDisplayName;
            
            if( useParentCategory && profileCategory.getParent() != null )
                profileCategory = profileCategory.getParent();                       

            String displayName = profileCategory.getName();
                    
            if( displayName == null || displayName.length() == 0 ||
                ProfileCategoryUtil.isUnknownCategory( profileCategory ) )
                displayName = defaultDisplayName;

            return displayName;
        }
        
        private String getEffectiveCategoryId( ICategory profileCategory,
                    boolean useParentCategory )
        {
            String categoryId = null;
            if( profileCategory != null )
            {
                if( useParentCategory && profileCategory.getParent() != null )
                    profileCategory = profileCategory.getParent();                       
                categoryId = profileCategory.getId();
            }

            if( ProfileCategoryUtil.isUnknownCategory( categoryId ) )
                categoryId = m_odaDataSourceId;      // default is the same as oda data source id  
                               
            return categoryId;
        }
        
        String getOdaDataSourceId()
        {
            return m_odaDataSourceId;
        }

        boolean hasOdaParentCategory()
        {
            return m_hasOdaParentCategory;
        }

        String getDisplayName()
        {
            return m_categoryDisplayName;
        }

        String getEffectiveCategoryId()
        {
            return m_effectiveCategoryId;
        }

        boolean isExternalLinkOptional()
        {
            /* a profile category without an ODA parent category requires maintaining an external link 
             * to a connection profile, cuz importing a profile's properties to a 
             * data source design private properties (which are not encyrpted) is not supported 
             * due to security concern
             */
            return hasOdaParentCategory();
        }
        
        /* (non-Javadoc)
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        public int compareTo( Object obj )
        {
            if( !(obj instanceof OdaProfileCategoryInfo) )
                return -1;
            return compareTo( (OdaProfileCategoryInfo) obj );
        }
        
        public int compareTo( OdaProfileCategoryInfo anotherId )
        {
            // first compare the parent category type
            if( m_hasOdaParentCategory != anotherId.m_hasOdaParentCategory )
            {
                // non ODA parent category is lower in ascending order
                return ! m_hasOdaParentCategory ? -1 : 1;
            }
            
            // same parent category type, compare its own category name
            int result = m_categoryDisplayName.compareTo( anotherId.m_categoryDisplayName );
            if( result != 0 )   // not the same display name
                return result;
            // next compare its id
            return m_odaDataSourceId.compareTo( anotherId.m_odaDataSourceId );    
        }

        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        public boolean equals( Object obj )
        {
            if( !(obj instanceof OdaProfileCategoryInfo) )
                return false;
            OdaProfileCategoryInfo anotherId = (OdaProfileCategoryInfo) obj;
            return m_categoryDisplayName.equals( anotherId.m_categoryDisplayName ) &&
                   m_odaDataSourceId.equals( anotherId.m_odaDataSourceId ) &&
                   m_hasOdaParentCategory == anotherId.m_hasOdaParentCategory &&
                   m_effectiveCategoryId.equals( anotherId.m_effectiveCategoryId );
        }
    };

}
