/*
 *************************************************************************
 * Copyright (c) 2007, 2013 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile;

import java.io.File;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ExportProfilesDialog;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.browse.IBrowseButtonHost;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.browse.IMenuButtonProvider;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.browse.MenuButtonProvider;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.browse.ProfileStoreBrowseButton;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.filter.NewProfileAction;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.datatools.connectivity.oda.profile.ProfileFileExtension;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class ProfileStoreCreationDialog extends ExportProfilesDialog 
    implements IBrowseButtonHost
{
    private static final String EXT_SEPARATOR = ProfileFileExtension.FILE_EXT_SEPARATOR;

    private CheckboxTableViewer m_profilesViewer;
    private ProfileStoreBrowseButton m_browseButton;
    private IConnectionProfile m_preSelectProfile;
    private ResourceIdentifiers m_resourceIdentifiers = null;
    private boolean m_isProfileStorePathRelative = false;
    
	public ProfileStoreCreationDialog( Shell parentShell )
	{
		super( parentShell );
        setShellStyle( super.getShellStyle() | SWT.PRIMARY_MODAL );
	}
	
	//@Override base method
	protected void configureShell( Shell newShell ) 
	{
		super.configureShell( newShell );
		newShell.setText( Messages.profileStoreCreationDialog_title ); 
	}
	
	//@Override base method
	protected Control createDialogArea( Composite parent ) 
	{
		Composite container = createParentDialogArea( parent );
		final GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = 20;
		gridLayout.numColumns = 3;		
		container.setLayout( gridLayout );
		
		// profiles selection group 
		{	
			final GridLayout groupGridLayout = new GridLayout();
            groupGridLayout.makeColumnsEqualWidth = false;
            groupGridLayout.numColumns = 3;            
            final Group group = createProfileSelectionGroup( container, groupGridLayout );
            
            // override group text
			group.setText( Messages.profileStoreCreationDialog_grouptext ); 

			// profiles selection viewer
			m_profilesViewer = setupCheckboxTableViewer( group );
			setCheckedProfile();  // pre-select profile, if specified
			
            // SelectAll button
			{				
				GridData selectAllButtonData = new GridData();
				selectAllButtonData.horizontalAlignment = SWT.LEFT;
				Button button = createSelectAllButton( group, selectAllButtonData );
		        selectAllButtonData.widthHint = ProfileSelectionPageHelper.computeButtonWidth( button );
			}
			
            // DeselectAll button
			{
				GridData deselectAllButtonData = new GridData();
				deselectAllButtonData.horizontalAlignment = SWT.LEFT;
				Button button = createDeselectAllButton( group, deselectAllButtonData );
				deselectAllButtonData.widthHint = ProfileSelectionPageHelper.computeButtonWidth( button );
			}
			
            // New... button
			{
		        Button newButton = new Button( group, SWT.PUSH );
		        newButton.setText( Messages.profileStoreCreationDialog_button_new );
		        newButton.setToolTipText( Messages.profileStoreCreationDialog_newbutton_tooltiptext );
		        GridData newButtonData = new GridData();
		        newButtonData.horizontalAlignment = SWT.RIGHT;
		        newButtonData.verticalAlignment = SWT.TOP;
		        newButtonData.widthHint = ProfileSelectionPageHelper.computeButtonWidth( newButton );
		        newButton.setLayoutData( newButtonData );

		        newButton.addSelectionListener( new SelectionAdapter() 
		        {
		            public void widgetSelected( SelectionEvent e )
		            {
						handleNewProfile();
					}

					private void handleNewProfile()
					{
						// Create a new connection profile
						NewProfileAction newProfileAction = new NewProfileAction( getShell() );
						newProfileAction.run();
						m_profilesViewer.refresh();						
					}
		        } );      
			}			
		}	
		
        createVerticalSpacingLabel( container );
				
        // composite for the new profile store file controls
		Composite composite = new Composite( container, SWT.NONE );
		{
			GridData gridData = new GridData();
			gridData.horizontalSpan = 3;
			gridData.grabExcessHorizontalSpace = true;
			gridData.minimumWidth = 410;
			composite.setLayoutData( gridData );
		}
        GridLayout layout = new GridLayout( );
        layout.numColumns = 3;
        composite.setLayout( layout );
        // File path label
		createFilePathLabel( composite, null );

		// File path text control
        {
            GridData data = new GridData( GridData.FILL_HORIZONTAL );
            data.widthHint = 290;
            setupFilePathText( composite, data ); 
        }

        // Browse... button
        {
            IMenuButtonProvider provider = new MenuButtonProvider( true );
            m_browseButton = new ProfileStoreBrowseButton( composite,
                    SWT.PUSH,
                    provider,
                    this );
            GridData data = new GridData();
            data.widthHint = m_browseButton.computeButtonWidth( );
            m_browseButton.setLayoutData(data);

            if( m_resourceIdentifiers != null )
            {
                File resourceBase= new File( m_resourceIdentifiers.getApplResourceBaseURI());
                m_browseButton.getMenuButtonProvider( ).setProperty( 
                        IBrowseButtonHost.RESOURCE_FILE_DIR, resourceBase );
            }
            m_browseButton.getMenuButtonProvider( ).setProperty( 
                    IBrowseButtonHost.IS_CREATE_PROFILE, Boolean.TRUE );
            m_browseButton.refreshMenuItems( );
        } 
        
        // Encrypt file checkbox
	    setupEncryptContentCheckbox( container );
	    
        setupHelp( getShell() );
		return container;
	}
	
	/**
	 * Specifies the connection profile element to pre-select in the dialog
     * to get included in the new profile store by default.
     * This method can be called before the dialog area is created.
	 * @param profile  the profile element to pre-select in the dialog's profiles viewer; 
	 *                 may be null to unset before the dialog area is created
	 */
	public void setPreSelectedProfile( IConnectionProfile profile )
	{
	    // hold on to profile to pre-select when the dialog area is created
	    m_preSelectProfile = profile;
	}
	
	private void setCheckedProfile()
	{
	    if( m_profilesViewer == null || m_preSelectProfile == null )
	        return;
	    
	    m_profilesViewer.setChecked( m_preSelectProfile, true );
	}

    void setHostResourceIdentifiers( ResourceIdentifiers resourceIdentifiers ) 
    {
        m_resourceIdentifiers = resourceIdentifiers;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.ui.wizards.ExportProfilesDialog#getFilePathText()
     */
    protected String getFilePathText()
    {
        String profileStorePath = super.getFilePathText();
        if( profileStorePath == null || profileStorePath.trim().length() == 0 )
        {
            m_isProfileStorePathRelative = false; // default setting
            return profileStorePath;
        }

        // override base class method to resolve profile store file path, 
        // if a relative path is specified;
        // also reset flag based on latest value found in text control 
        String trimmedPath = profileStorePath.trim();
        m_isProfileStorePathRelative = ! new File( trimmedPath ).isAbsolute();
        String resolvedProfilePath = DesignUtil.resolveToApplResourcePath( trimmedPath, m_resourceIdentifiers );
        return resolvedProfilePath != null && ! resolvedProfilePath.equals( trimmedPath ) ?
                    resolvedProfilePath : // got resolved to absolute value
                    profileStorePath;     // unable to resolve profileStorePath, return original file path text
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.ui.wizards.ExportProfilesDialog#validateFilePath()
     */
    protected boolean validateFilePath() 
    {
        if( ! super.validateFilePath() )
            return false;

        File file = new File( getFilePathText() );

        // Overrides to further validate the file extension, if a default extension is defined
        String defaultExtension = ProfileFileExtension.getDefault();        
        if( ProfileFileExtension.exists( defaultExtension ) )
        {
            if( ! validateFileExtension( file, defaultExtension ) )
                return false;
        }
        
        // file extension is valid, now check if file already exists
        return validateOverwriteExistingFile( file );
    }
    
    private boolean validateFileExtension( File file, String defaultExtension ) 
    {
        String fileName = file.getName();
        
        int lastIndex = fileName.lastIndexOf( EXT_SEPARATOR );
        if( lastIndex >= 0 && fileName.length() > lastIndex+1 )     // file has file extension
            return true;
        
        // raise error dialog about missing file extension in the file name
        String[] dialogLabels = new String[] { 
                IDialogConstants.OK_LABEL,
                IDialogConstants.CANCEL_LABEL };            
        MessageDialog dialog = new MessageDialog( getShell(),
                Messages.ui_errorLabel,
                null,
                Messages.bind( Messages.profileStoreCreationDialog_fileext_error, defaultExtension ),
                MessageDialog.ERROR,
                dialogLabels,
                0);
        
        int response = dialog.open();            
        if( response != 0 ) // user presses Cancel
            return false;
        
        // user presses Ok,
        // append the default extension to the user-input file path
        String userFilePathText = super.getFilePathText();
        StringBuilder revisedFilePathText = new StringBuilder( userFilePathText );
        if( ! userFilePathText.endsWith( EXT_SEPARATOR ) )
            revisedFilePathText.append( EXT_SEPARATOR );
        revisedFilePathText.append( defaultExtension );

        super.setFilePathText( revisedFilePathText.toString() );
        return false;   // requires user to verify the filePath and press Ok to continue
    }

    private boolean validateOverwriteExistingFile( File file ) 
    {
        if( ! file.exists() )
            return true;
        
        // raise dialog about overwriting the specified file
        return MessageDialog.openConfirm( getShell(), 
                Messages.ui_saveAsTitle,
                Messages.bind( Messages.ui_replaceFilePrompt, file ) );
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.browse.IBrowseButtonHost#setProfileStorePath(java.lang.String, boolean)
     */
    public void setProfileStorePath( String path, boolean isRelative ) 
    {
        m_isProfileStorePathRelative = isRelative;
        super.setFilePathText( path );
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.browse.IBrowseButtonHost#browseSelected()
     */
    public void browseSelected()
    {
        m_browseButton.getMenuButtonProvider().setProperty(
                IBrowseButtonHost.STORED_PATH, getFilePathText() );
    }

    public boolean isProfileStorePathRelative()
    {
        return m_isProfileStorePathRelative;
    }       
	
}


