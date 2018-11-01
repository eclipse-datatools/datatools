/*******************************************************************************
 * Copyright (c) 2005, 2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  shongxum - initial API and implementation
 *  Actuate Corporation - refactored to improve extendability
******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.io.File;
import java.util.Vector;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.ui.ProfileImageRegistry;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * @author shongxum
 */
public class ExportProfilesDialog extends TrayDialog implements IContextProvider{

	private CheckboxTableViewer tvViewer;

	private Text txtFile;

	private Button btnEncryption;

	private IConnectionProfile[] mProfiles;

	private File mFile;

	private boolean mNeedEncryption;

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());

	class TableLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		public String getColumnText(Object element, int columnIndex) {
			IConnectionProfile profile = (IConnectionProfile) element;
			return profile.getName();
		}

		public Image getColumnImage(Object element, int columnIndex) {
			IConnectionProfile profile = (IConnectionProfile) element;
			return ProfileImageRegistry.getInstance().getProfileImage(
					profile.getProvider());
		}
	}

	class ContentProvider implements IStructuredContentProvider {

		public Object[] getElements(Object inputElement) {
			return ProfileManager.getInstance().getProfiles();
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	public ExportProfilesDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.RESIZE);
	}

	protected Control createDialogArea(Composite parent) {
        
        Composite container = createParentDialogArea(parent);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = 20;
		gridLayout.numColumns = 3;
		container.setLayout(gridLayout);
        
        // profiles selection group 
		{
			final GridLayout groupGridLayout = new GridLayout();
			groupGridLayout.makeColumnsEqualWidth = true;
			groupGridLayout.numColumns = 3;
			final Group group = createProfileSelectionGroup( container, groupGridLayout );

            // profiles selection viewer
			setupCheckboxTableViewer(group);

			// SelectAll button
			createSelectAllButton( group, new GridData(
	                GridData.HORIZONTAL_ALIGN_CENTER) );

			// invisible horizontal spacing between the 2 buttons
			{
				new Label(group, SWT.NONE);
			}
			
            // DeselectAll button
			createDeselectAllButton( group, new GridData(
	                GridData.HORIZONTAL_ALIGN_CENTER) );
		}

		createVerticalSpacingLabel( container );
		
		// File path label
		{
			final GridData gridData = new GridData();
			gridData.horizontalIndent = 5;
            createFilePathLabel( container, gridData );
		}
		// File path text control
		{
			final GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.widthHint = 243;
			setupFilePathText( container, gridData);
		}
		
		// Browse... button
		createFilePathBrowseButton( container, new GridData(
                GridData.HORIZONTAL_ALIGN_CENTER) );
		
		// Encrypt file checkbox
		setupEncryptContentCheckbox( container );

        setupHelp( getShell() );
		return container;
	}

    protected Composite createParentDialogArea(Composite parent)
    {
        return (Composite) super.createDialogArea(parent);
    }

    protected Group createProfileSelectionGroup( Composite parent, Layout layout )
    {
        final Group group = new Group(parent, SWT.NONE);
        group.setText(ConnectivityUIPlugin.getDefault().getResourceString(
        		"ExportProfilesDialog.group.text")); //$NON-NLS-1$
        final GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.horizontalSpan = 3;
        group.setLayoutData(gridData);
        group.setLayout(layout);
        return group;
    }

    protected CheckboxTableViewer setupCheckboxTableViewer(Composite parent)
    {
        final CheckboxTableViewer checkboxTableViewer = CheckboxTableViewer
                .newCheckList(parent, SWT.V_SCROLL | SWT.BORDER
                        | SWT.H_SCROLL);
        checkboxTableViewer.setLabelProvider(new TableLabelProvider());
        checkboxTableViewer.setContentProvider(new ContentProvider());
        final Table table = checkboxTableViewer.getTable();
        final GridData gridData_1 = new GridData(GridData.FILL_BOTH);
        gridData_1.horizontalSpan = 3;
        gridData_1.widthHint = 392;
        table.setLayoutData(gridData_1);
        checkboxTableViewer.setInput(new Object());
        checkboxTableViewer.setSorter(new ProfileSorter());
        tvViewer = checkboxTableViewer;
        tvViewer.refresh();
        return tvViewer;
    }

    protected Button createSelectAllButton( Composite parent, Object layoutData )
    {
        final Button button = new Button(parent, SWT.NONE);
        button.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                tvViewer.setAllChecked(true);
            }
        });
        button.setLayoutData(layoutData);
        button.setText(ConnectivityUIPlugin.getDefault()
                .getResourceString("ExportProfilesDialog.button.text")); //$NON-NLS-1$
        return button;
    }
    
    protected Button createDeselectAllButton( Composite parent, Object layoutData )
    {
        final Button button = new Button(parent, SWT.NONE);
        button.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                tvViewer.setAllChecked(false);
            }
        });
        button.setLayoutData(layoutData);
        button.setText(ConnectivityUIPlugin.getDefault()
                        .getResourceString(
                                "ExportProfilesDialog.button.text1")); //$NON-NLS-1$
        return button;
    }
    
    /**
     * Creates an invisible label to be used for spacing between the group control and 
     * the file path controls.
     * @param parent    a composite control which will be the parent of the new label instance 
     *                  (cannot be null)
     */
	protected Label createVerticalSpacingLabel( Composite parent )
	{
		final Label label = new Label(parent, SWT.NONE);
		final GridData gridData = new GridData(
				GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 3;
		gridData.widthHint = 495;
		label.setLayoutData(gridData);
		return label;
	}    

    protected Label createFilePathLabel( Composite parent, Object layoutData )
    {
        final Label label = new Label(parent, SWT.NONE);
        label.setLayoutData( layoutData );
        label.setText(ConnectivityUIPlugin.getDefault().getResourceString(
                "ExportProfilesDialog.label.text")); //$NON-NLS-1$
        return label;
    }

    protected Text setupFilePathText( Composite parent, Object layoutData )
    {
        final Text text = new Text(parent, SWT.BORDER);
        text.setLayoutData(layoutData);
        txtFile = text;
        return txtFile;
    }

    protected Button createFilePathBrowseButton( Composite parent, Object layoutData )
    {
		final Button button = new Button(parent, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				String filePath = new FileDialog(getShell()).open();
				if( filePath != null )
				    setFilePathText(filePath);
			}
		});
		button.setLayoutData( layoutData );
		button.setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ExportProfilesDialog.button.text2")); //$NON-NLS-1$
        return button;
    }

    protected Button setupEncryptContentCheckbox( Composite parent )
    {
        final Button button = new Button(parent, SWT.CHECK);
		final GridData gridData = new GridData(GridData.GRAB_HORIZONTAL);
		gridData.horizontalIndent = 10;
		gridData.horizontalSpan = 3;
		button.setLayoutData(gridData);
		button.setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ExportProfilesDialog.btnEncryption.text")); //$NON-NLS-1$
		button.setSelection(true);
		btnEncryption = button;
		return btnEncryption;
    }

    protected void setupHelp( Control control )
    {
        control.setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
//      HelpUtil.setHelp( getShell(), IHelpConstants.CONTEXT_ID_EXPORT_PROFILES_DIALOG);
        String contextId = HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_EXPORT_PROFILES_DIALOG, 
                ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());
        HelpUtil.setHelp( control, contextId);
    }

	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ExportProfilesDialog.null.title")); //$NON-NLS-1$
	}

	public IConnectionProfile[] getSelectedProfiles() {
		return mProfiles;
	}

	public File getFile() {
		return mFile;
	}

	public boolean needEncryption() {
		return mNeedEncryption;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		Vector vec = new Vector();
		Object[] elements = tvViewer.getCheckedElements();

		if (elements.length == 0) {
			MessageDialog.openError(getShell(), ConnectivityUIPlugin
					.getDefault().getResourceString("dialog.title.error"), //$NON-NLS-1$
					ConnectivityUIPlugin.getDefault().getResourceString(
							"actions.export.noselection")); //$NON-NLS-1$
			return;
		}

        if ( !validateFilePath() )
            return;
		
		for (int i = 0; i < elements.length; i++) {
			vec.add(elements[i]);
		}
		mProfiles = (IConnectionProfile[]) vec
				.toArray(new IConnectionProfile[0]);
		mFile = new File(getFilePathText());
		
		/* validate that the file name has a valid directory as parent */
		String fileParent = mFile.getParent();
		boolean hasParent = !(fileParent == null || mFile.getParent().length() == 0);
		boolean pathEndsInFileSeparator = false;
		boolean hasValidParent = false;
		if (hasParent) {
			File parentFile = new File(fileParent);
			hasValidParent = parentFile.exists();
			pathEndsInFileSeparator = parentFile.isDirectory();
		}
		if (!hasParent || !hasValidParent || !pathEndsInFileSeparator ) {
			MessageDialog.openError(getShell(), ConnectivityUIPlugin
					.getDefault().getResourceString("dialog.title.error"), //$NON-NLS-1$
					ConnectivityUIPlugin.getDefault().getResourceString(
							"actions.export.notvalidfile")); //$NON-NLS-1$
			return;
		}
		mNeedEncryption = btnEncryption.getSelection();
		super.okPressed();
	}
    
	/*
	 * @since DTP 1.9.2
	 */
    protected String getFilePathText()
    {
        String localizedText = txtFile.getText();
        return TextProcessor.deprocess( localizedText );
    }
    
    /*
     * @since DTP 1.9.2
     */
    protected void setFilePathText( String text )
    {
        String localizedText = TextProcessor.process( text );
        txtFile.setText( localizedText );
    }

    /*
     * @since DTP 1.9.2
     */
    protected boolean validateFilePath() 
    {
        if ( getFilePathText().trim().length() == 0 ) 
        {
            MessageDialog.openError(getShell(), ConnectivityUIPlugin
                    .getDefault().getResourceString("dialog.title.error"), //$NON-NLS-1$
                    ConnectivityUIPlugin.getDefault().getResourceString(
                            "actions.export.nofile")); //$NON-NLS-1$
            return false;
        }
        return true;
    }

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}
	
	private class ProfileSorter extends ViewerSorter {

		public int compare(Viewer viewer, Object e1, Object e2) {
			if (e1 instanceof IConnectionProfile && e2 instanceof IConnectionProfile) {
				return ((IConnectionProfile)e1).getName().compareToIgnoreCase(((IConnectionProfile)e2).getName());
			}
			return super.compare(viewer, e1, e2);
		}
		
	}
}