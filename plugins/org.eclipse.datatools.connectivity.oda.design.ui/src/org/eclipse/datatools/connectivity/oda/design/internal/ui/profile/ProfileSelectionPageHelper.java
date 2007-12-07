/*
 *************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
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

import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ExportProfilesDialog;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSourceDesignSessionBase.IDesignNameValidatorBase;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSourceDesignSessionBase.ProfileReferenceBase;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.TextProcessorWrapper;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
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

    private static final String CONNECTIVITY_DB_CATEGORY_ID = 
        "org.eclipse.datatools.connectivity.db.category"; //$NON-NLS-1$

    private static final int TREE_ITEM_STYLE = SWT.NONE;
    
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
    private transient Properties m_dataSourceIDProperties = null;
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
            data.widthHint = 80;
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
            data.widthHint = 80;
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
                    setDefaultMessageAsError( true );
                }
                else
                {
                    m_profileID = item.getData().toString();
                    m_odaDataSourceID = item.getParentItem().getData().toString();
                    setSelectedDataSourceName( item.getText() );
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
//        OdaProfileExplorer.getInstance().refresh(); // reset cached profiles in instance
        if( ! hasConnectionProfilePath() )
            return;
        
        // TODO replace hard-coded ids with use of wrapper extension info
        // populate tree with all wrapped profiles under the corresponding oda data source id
//            TreeItem dbCategoryItem = 
//                createCategoryTreeItems( dsCategory, CONNECTIVITY_DB_CATEGORY_ID, ODA_CONNECTIVITY_DB_DATA_SOURCE_ID );
            // TODO Does the db category have sub-categories?
        
        // populate tree with ODA extensions' profile instances
        
        generateODADataSourceIdentifiers( );

        TreeItem root = new TreeItem( m_odaDataSourceTree, TREE_ITEM_STYLE );
        root.setText( Messages.profilePage_odaTreeName );

        try
        {
            Iterator iterator = m_dataSourceIDProperties.keySet().iterator();
            while( iterator.hasNext() )
            {
                Object odaDataSourceIdObj = iterator.next();
                String odaDataSourceId = odaDataSourceIdObj.toString( );
                if( m_treeFilter != null && ! m_treeFilter.equals( odaDataSourceId ) )
                    continue;   // skip oda data source type not in filter

                // don't bother create a category item if it has no profile instances
                if( ! hasProfileInstance( odaDataSourceId ) )
                    continue;

                TreeItem dsCategory = new TreeItem( root, TREE_ITEM_STYLE );
                String dsDisplayName = m_dataSourceIDProperties.getProperty( odaDataSourceId );
                dsCategory.setData( odaDataSourceId );
                dsCategory.setText( dsDisplayName );

                createODATreeItems( dsCategory );
                
                m_odaDataSourceTree.showItem( dsCategory );
            }
        }
        catch ( OdaException e )
        {
            // unexpected exception
            e.printStackTrace( );
        }
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
            m_dataSourceIDProperties.containsKey( item.getData() ) )
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
        
        TreeItem[] root = m_odaDataSourceTree.getItems();
        if( root.length == 0 )
            return null;
        
        TreeItem[] dsCategories = root[0].getItems();
        for( int i = 0; i < dsCategories.length; i++ )
        {
            TreeItem dsCategory = dsCategories[i];
            if( dsCategory.getData().toString().equals( odaDataSourceId ) )
                return dsCategory;
        }
        
        return null;        
    }

    /**
     * Collect DataSourceIdentifiers from ODA extensions registry.
     */
    private void generateODADataSourceIdentifiers( )
    {
        if( m_dataSourceIDProperties == null )
            m_dataSourceIDProperties = ManifestExplorer.getInstance()
                    .getDataSourceIdentifiers( );
    }

    /**
     * Check if the specified type of ODA data source has at least
     * one connection profile instance.
     * @return
     * @throws OdaException
     */
    private boolean hasProfileInstance( Object odaDataSourceId ) throws OdaException
    {
        Map profiles = getProfileIdentifiers( odaDataSourceId.toString() );
        if( profiles == null )
            return false;

        return ( profiles.keySet().size() > 0 );
    }

    /**
     * Retrieve a collection of the connection profile instances for the
     * given odaDataSourceId in the user-specified profile store path.
     * @param odaDataSourceId
     * @return  a <code>Map</code> containing the instance Id
     *          and display name of all existing profiles of given odaDataSourceId.
     *          The connection profiles' instance Id and display name
     *          are stored as the key and value strings in the returned <code>Map</code> instance.
     *          Returns an empty collection if there are no matching connection profiles found.
     */
    private Map getProfileIdentifiers( String odaDataSourceId )
    {
        try
        {
            return DesignSessionUtil.getProfileIdentifiers( odaDataSourceId,
                    new Path( getConnProfilePathControlText() ).toFile( ) );
        }
        catch ( OdaException ex )
        {
            setMessage( Messages.profilePage_error_invalidProfileStorePath, IMessageProvider.ERROR );
        }

        return null;
    }
    
    private Map getProfileIdentifiersByCategory( String categoryId )
    {
//        try
//        {
//            return DesignSessionUtil.getProfileIdentifiersByCategory( categoryId,
//                    new Path( getConnProfilePathControlText() ).toFile( ) );
//        }
//        catch ( OdaException ex )
//        {
//            setMessage( Messages.profilePage_error_invalidProfileStorePath, IMessageProvider.ERROR );
//        }

        return null;
    }

    /**
     * Create child tree items for the given ODA data source category.
     * @throws OdaException
     */
    private void createODATreeItems( TreeItem dsCategory ) throws OdaException
    {
        Map profileIds = getProfileIdentifiers( dsCategory.getData().toString() );
        createChildTreeItems( dsCategory, profileIds, TREE_ITEM_STYLE );
    }
    
    private TreeItem createCategoryTreeItems( TreeItem parent, String categoryId, 
            String odaDataSourceId ) 
        throws OdaException
    {
        Map profileIds = getProfileIdentifiersByCategory( categoryId );
        // if no profile instances found under given category
        if( profileIds == null || profileIds.isEmpty() )  
            return null;             // done; no need to create a child tree item for category

        // get the category object from the first profile
        ICategory category = null;
        String profileInstanceId = profileIds.keySet().iterator().next().toString();
        IConnectionProfile profile = 
            OdaProfileExplorer.getInstance().getProfile( profileInstanceId );
        if( profile != null )
            category = profile.getCategory();
       
        // create a tree item for the specified category
        TreeItem categoryItem = new TreeItem( parent, TREE_ITEM_STYLE );
            // the tree view expects a profile instance's parent item data to be an odaDataSourceId
        categoryItem.setData( odaDataSourceId );
        categoryItem.setText( ( category != null ) ? category.getName() : categoryId );
        
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
    private void enableDataSourceNameEditor( boolean bool )
    {
        m_dataSourceNameLabel.setEnabled( bool );
        m_dataSourceDesignNameControl.setEnabled( bool );
    }
    
    private void setDataSourceNameEditorVisible( boolean visible )
    {
        m_useDefaultDSNameCheckBox.setVisible( visible );
        m_dataSourceNameLabel.setVisible( visible );
        m_dataSourceDesignNameControl.setVisible( visible );
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

}
