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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSourceDesignSessionBase.IDesignNameValidatorBase;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSourceDesignSessionBase.ProfileReferenceBase;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
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
    private static final String CONEXT_ID_CONNECTIONPROFILE = 
        "org.eclipse.datatools.oda.cshelp.Wizard_ConnectionProfile_ID";//$NON-NLS-1$
    
    private WizardPage m_wizardPage;
    private PreferencePage m_propertyPage;

    private transient Label m_dataSourceNameLabel = null;
    private transient Text m_connectionProfilePath = null;
    private transient Text m_dataSourceDesignName = null;
    private transient Button m_linkRefCheckBox = null;
    private transient String m_profileID;
    private transient String m_odaDataSourceID;
    private transient Button m_useDefaultDSNameCheckBox = null;
    private transient Tree m_odaDataSourceTree = null;
    private transient String m_treeFilter;
    private transient Properties m_dataSourceIDProperties = null;
    private transient IDesignNameValidatorBase m_designNameValidator;

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
            .setHelp( container, CONEXT_ID_CONNECTIONPROFILE );

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
                m_connectionProfilePath.setText( profileStorePath );
                
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
        
        m_dataSourceDesignName.setText( selectedProfile.getDataSourceDesignName() );

        // when editing an existing data source design, hide controls to
        // disallow change of the design name
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
        if( isPageComplete() )
            return new ProfileSelection(
                m_odaDataSourceID,
                m_dataSourceDesignName.getText().trim(),
                new ProfileReferenceBase( m_profileID,
                        new Path( m_connectionProfilePath.getText() ).toFile( ),
                        m_linkRefCheckBox.getSelection() ) );
        return null;
    }
    
    private boolean isPageComplete()
    {
        if ( m_wizardPage != null )
            return m_wizardPage.isPageComplete();

        if ( m_propertyPage != null )
            return m_propertyPage.isValid();
        
        return getMessageType() != IMessageProvider.ERROR;
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
            setMessage( Messages.profilePage_selectProfileDefaultMessage, IMessageProvider.ERROR );
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
                    }
                } );
            }
        } );

        // add Browse... button
        Button button = new Button( composite, SWT.PUSH );
        button.setText( Messages.profilePage_button_browse );
        button.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
        button.addSelectionListener( new SelectionAdapter() 
        {
            public void widgetSelected( SelectionEvent e )
            {
                m_connectionProfilePath.setText( 
                        new FileDialog( getShell() ).open() );
            }
        } );
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
                    m_dataSourceDesignName.setText( EMPTY_STRING );
                }
                else
                {
                    m_profileID = item.getData().toString();
                    m_odaDataSourceID = item.getParentItem().getData().toString();
                    m_dataSourceDesignName.setText( item.getText( ) );
                }
            }
        } );

        populateTree( );
    }

    /**
     * Populate the connection profile instances in the tree viewer
     */
    private void populateTree( )
    {
        if( ! hasConnectionProfilePath() )
            return;

        resetTreeViewer( );
        generateDataSourceIdentifiers( );

        TreeItem root = new TreeItem( m_odaDataSourceTree, SWT.NONE );
        root.setText( Messages.profilePage_odaTreeName );

        try
        {
            Iterator iterator = m_dataSourceIDProperties.keySet().iterator();
            while( iterator.hasNext() )
            {
                Object odaDataSourceId = iterator.next();
                String dsID = odaDataSourceId.toString( );
                if( m_treeFilter != null && ! m_treeFilter.equals( dsID ) )
                    continue;   // skip oda data source type not in filter
                if( ! hasProfileInstance( odaDataSourceId ) )
                    continue;

                TreeItem dsCategory = new TreeItem( root, SWT.NONE );
                String dsDisplayName = m_dataSourceIDProperties.getProperty( dsID );
                dsCategory.setData( dsID );
                dsCategory.setText( dsDisplayName );

                createDSTreeItems( dsCategory );
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
        m_dataSourceDesignName.setText( EMPTY_STRING );
        m_odaDataSourceTree.removeAll( );
        setPageComplete( false );
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
    private void generateDataSourceIdentifiers( )
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
     * @return
     */
    private Map getProfileIdentifiers( String odaDataSourceId )
    {
        try
        {
            return DesignSessionUtil.getProfileIdentifiers( odaDataSourceId,
                    new Path( m_connectionProfilePath.getText( ) ).toFile( ) );
        }
        catch ( OdaException ex )
        {
            setMessage( "Invalid path for a connection profile store", IMessageProvider.ERROR);
        }

        return null;
    }

    /**
     * Create tree items for each data source category
     * @throws OdaException
     */
    private void createDSTreeItems( TreeItem dsCategory ) throws OdaException
    {
        Map profiles = getProfileIdentifiers( dsCategory.getData().toString() );
        if( profiles == null )
            return;
        
        ArrayList treeList = new ArrayList( );
        Iterator iterator = profiles.keySet( ).iterator( );
        while( iterator.hasNext() )
        {
            treeList.add( iterator.next() );
        }

        createTreeItems( profiles, dsCategory, treeList, SWT.NONE );
    }

    /**
     * Build tree item for each data source in the specified arrayList.
     * @return
     */
    private TreeItem[] createTreeItems( Map profiles, TreeItem dsCategory,
            ArrayList dataSource, int style )
    {
        if( dataSource == null )
            return null;

        TreeItem item[] = new TreeItem[dataSource.size()];
        for( int i = 0; i < dataSource.size( ); i++ )
        {
            item[i] = new TreeItem( dsCategory, style );
            Object source = dataSource.get( i );
            item[i].setData( source );
            item[i].setText( profiles.get( source ).toString( ) );
        }

        return item;
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
                        m_dataSourceDesignName.setText( item.getText( ) );
                }
                
                enableDataSourceNameEditor( ! m_useDefaultDSNameCheckBox.getSelection() );
            }
        } );

        m_dataSourceNameLabel = new Label( composite, SWT.NONE );
        m_dataSourceNameLabel.setText( Messages.profilePage_label_dataSourceName );
        m_dataSourceNameLabel.setLayoutData( new GridData( GridData.HORIZONTAL_ALIGN_BEGINNING ) );

        layoutData = new GridData( GridData.FILL_HORIZONTAL );
        layoutData.horizontalSpan = 2;
        m_dataSourceDesignName = new Text( composite, SWT.BORDER );
        m_dataSourceDesignName.setLayoutData( layoutData );
        m_dataSourceDesignName.addModifyListener( new ModifyListener( ) 
        {
            public void modifyText( ModifyEvent e )
            {
                // name change is ignored in edit mode, no need to validate
                if( inEditMode() )
                    return;     
                
                String invalidMessage = null;
                if ( isNameBlank( ) )
                {
                    setPageComplete( false );
                    setMessage( Messages.profilePage_error_emptyName, IMessageProvider.ERROR );
                }
                else if( ( invalidMessage = validateAndGetErrorMessage() ) != null )
                {
                    setPageComplete( false );
                    setMessage( invalidMessage, IMessageProvider.ERROR );
                }
                else
                {
                    setPageComplete( true );
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
               
            }
        } );
    }
    
    /**
     * Validate whether the data source design name is blank
     */
    private boolean isNameBlank( )
    {
        return m_dataSourceDesignName.getText().trim().length() == 0;
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
                if( m_designNameValidator.isValid( m_dataSourceDesignName.getText().trim() ))
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
        m_dataSourceDesignName.setEnabled( bool );
    }
    
    private void setDataSourceNameEditorVisible( boolean visible )
    {
        m_useDefaultDSNameCheckBox.setVisible( visible );
        m_dataSourceNameLabel.setVisible( visible );
        m_dataSourceDesignName.setVisible( visible );
    }
    
    private boolean inEditMode()
    {
        return ( m_propertyPage != null );
    }
    
    private boolean hasConnectionProfilePath( )
    {
        return m_connectionProfilePath.getText().trim().length() > 0;
    }

}
