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

import org.eclipse.datatools.connectivity.internal.ui.wizards.ExportProfilesDialog;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.ui.actions.AddProfileViewAction;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ProfileStoreCreationDialog extends ExportProfilesDialog
{
	private static final int BUTTON_WIDTH = 80;
	
	public ProfileStoreCreationDialog( Shell parentShell )
	{
		super( parentShell );
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
            final Group group = createGroupAndLayoutData( container );
            // override group text
			group.setText( Messages.profileStoreCreationDialog_grouptext ); 

			// profiles selection viewer
			final CheckboxTableViewer profilesViewer = setupCheckboxTableViewer( group );
			
            // SelectAll button
			{				
				GridData selectAllButtonData = new GridData();
				selectAllButtonData.horizontalAlignment = SWT.LEFT;
				selectAllButtonData.widthHint = BUTTON_WIDTH;
				createSelectAllButton( group, selectAllButtonData );
			}
			
            // DeselectAll button
			{
				GridData deselectAllButtonData = new GridData();
				deselectAllButtonData.horizontalAlignment = SWT.LEFT;
				deselectAllButtonData.widthHint = BUTTON_WIDTH;
				createDeselectAllButton( group, deselectAllButtonData );
			}
			
            // New... button
			{
		        Button newButton = new Button( group, SWT.PUSH );
		        newButton.setText( Messages.profilePage_button_new );
		        newButton.setToolTipText( Messages.profileStoreCreationDialog_newbutton_tooltiptext );
		        GridData newButtonData = new GridData();
		        newButtonData.horizontalAlignment = SWT.RIGHT;
		        newButtonData.verticalAlignment = SWT.TOP;
		        newButtonData.widthHint = BUTTON_WIDTH;
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
						AddProfileViewAction newAction = new AddProfileViewAction();
						newAction.run( null );
						profilesViewer.refresh();						
					}
		        } );      
			}
			            
            final GridLayout gridLayout_1 = new GridLayout();
            gridLayout_1.makeColumnsEqualWidth = false;
            gridLayout_1.numColumns = 3;
			group.setLayout(gridLayout_1);
		}
		
		{
			final Label label = new Label( container, SWT.NONE );
			final GridData gridData = new GridData(
					GridData.HORIZONTAL_ALIGN_FILL );
			gridData.horizontalSpan = 3;
			gridData.widthHint = 410;
			label.setLayoutData( gridData );
		}		
        // composite for the new profile store file controls
		Composite composite = new Composite( container, SWT.NONE );
		{
			GridData gridData = new GridData();
			gridData.horizontalSpan = 3;
			gridData.grabExcessHorizontalSpace = true;
			gridData.minimumWidth = 392;
			composite.setLayoutData( gridData );
		}
		composite.setLayout( new FormLayout() );
        // File path label
		final Label fileNameLabel = createFilePathLabel( composite, null );
        // File path text control
		Text filenameText;
		{
			FormData data = new FormData();
			data.left = new FormAttachment( fileNameLabel, 10 );
			data.width = 250;
			filenameText = setupFilePathText( composite, data ); 
		}
		// Browse... button
		{
			FormData data = new FormData();
			data.left = new FormAttachment( filenameText, 10 );
			data.width = BUTTON_WIDTH;
			
		    createFilePathBrowseButton( composite, data );
		} 
        
        // Encrypt file checkbox
	    setupEncryptContentCheckbox( container );
	    
        setupHelp();
		return container;
	}
	
}


