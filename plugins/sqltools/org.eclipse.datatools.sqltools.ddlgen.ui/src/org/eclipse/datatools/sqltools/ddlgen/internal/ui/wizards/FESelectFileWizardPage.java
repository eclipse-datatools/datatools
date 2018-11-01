/*******************************************************************************
 * Copyright (c) 2001, 2009 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.sqltools.ddlgen.internal.ui.wizards;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.dialogs.ResourceChooserDialog;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.FEUiPlugin;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.IHelpContextsSQMFEUI;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.util.ResourceLoader;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class FESelectFileWizardPage 
	extends WizardPage
	implements IContextProvider {
	
	private static final String FE_SELECT_DATABASE_PAGE_HEADER_SUBTITLE = ResourceLoader.INSTANCE
			.queryString("FE_SELECT_DATABASE_PAGE_HEADER_SUBTITLE"); //$NON-NLS-1$

	private static final String FE_SELECT_DATABASE_PAGE_HEADER_TITLE = ResourceLoader.INSTANCE
			.queryString("FE_SELECT_DATABASE_PAGE_HEADER_TITLE"); //$NON-NLS-1$

	private static final String FILE_PATH_LABEL_TEXT = ResourceLoader.INSTANCE
			.queryString("FILE_PATH_LABEL_TEXT"); //$NON-NLS-1$

	private static final String BROWSE_BUTTON_TEXT_2 = ResourceLoader.INSTANCE
			.queryString("BROWSE_BUTTON_TEXT_2"); //$NON-NLS-1$

	private static final String DEFAULT_SCRIPT_FILE_BASE_NAME = ResourceLoader.INSTANCE
			.queryString("FEWizard.SelectFilePage.defaultScriptFileBaseName"); //$NON-NLS-1$
	
	private Label projectPathLabel;

	private Text projectPathText;

	private Button browseButton;

	private Label fileNameLabel;

	private Text fileNameText;

	private IContainer selectedContainer;

	private Text ddlPreviewText;

	private Button executeCheckBox;
	
	private Button openDDLCheckbox;

	private List selection;
	
	private String terminator = ";";
	
	private Text terminatorText;
	
	private Button applyTerminatorButton;

	private static final String SCRIPT_FILE_EXTENSION = "sql";

	private String deltaddl = null;

	private boolean needsGenerateDDL;

	private boolean executeDisallowed = false;

	private boolean editDisallowed = false;

	public FESelectFileWizardPage(String pageName) {
		this(pageName, null);
	}

	public FESelectFileWizardPage(String pageName, List selection) {
		super(pageName);
		this.selection = selection;
		setTitle(FE_SELECT_DATABASE_PAGE_HEADER_TITLE);
		setDescription(FE_SELECT_DATABASE_PAGE_HEADER_SUBTITLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

//		WorkbenchHelp.setHelp(composite,
//				"org.eclipse.wst.rdb.fe.ui.infopop.genddl_wiz_save");

		projectPathLabel = new Label(composite, SWT.NONE);
		projectPathLabel.setText(ResourceLoader.INSTANCE
				.queryString("FEWizard.SelectFilePage.folderLabel")); //$NON-NLS-1$
		projectPathLabel.setLayoutData(new GridData());

		projectPathText = new Text(composite, SWT.BORDER);
		projectPathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
				| GridData.GRAB_HORIZONTAL));
		projectPathText.setText(""); //$NON-NLS-1$
		projectPathText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}
		});

		browseButton = new Button(composite, SWT.NONE);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		data.horizontalSpan = 1;
		browseButton.setLayoutData(data);
		browseButton.setText(BROWSE_BUTTON_TEXT_2);
		browseButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				FESelectFileWizardPage.this.onClickBrowse();
			}
		});

		fileNameLabel = new Label(composite, SWT.NULL);
		fileNameLabel.setText(FILE_PATH_LABEL_TEXT);
		fileNameLabel.setLayoutData(new GridData());

		fileNameText = new Text(composite, SWT.BORDER | SWT.SINGLE);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		fileNameText.setLayoutData(data);
		fileNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}
		});

		Group previewGroup = new Group(composite, SWT.NONE);
		previewGroup.setText(ResourceLoader.INSTANCE
				.queryString("FEWizard.SelectFilePage.ddlPreviewGroup")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.FILL_VERTICAL);
		data.horizontalSpan = 3;
		previewGroup.setLayoutData(data);
		layout = new GridLayout();
		layout.numColumns = 3;
		previewGroup.setLayout(layout);

		ddlPreviewText = new Text(previewGroup, SWT.BORDER | SWT.READ_ONLY
				| SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 3;
	    //the following two lines seem to fix the preview window scroll bar disappearing issues
        //when there is a lot of DDL statements.
        data.heightHint = 400;
        data.widthHint = 400;
		ddlPreviewText.setLayoutData(data);

		Label terminatorLabel = new Label(previewGroup, SWT.NULL);
		terminatorLabel.setText(ResourceLoader.INSTANCE.queryString("FEWizard.SelectFilePage.terminatorLabel")); //$NON-NLS-1$
		terminatorLabel.setLayoutData(new GridData());

		terminatorText = new Text(previewGroup, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL
				| GridData.GRAB_HORIZONTAL);
		data.horizontalSpan = 1;
		terminatorText.setLayoutData(data);		
		terminatorText.setText(terminator); //$NON-NLS-1$
		terminatorText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				FESelectFileWizardPage.this.onTerminatorTextModified();
			}
		});


		applyTerminatorButton = new Button(previewGroup, SWT.NONE);
		data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		data.horizontalSpan = 1;
		applyTerminatorButton.setLayoutData(data);
		applyTerminatorButton.setText(ResourceLoader.INSTANCE.queryString("FEWizard.SelectFilePage.terminatorApplyButton")); //$NON-NLS-1$
		applyTerminatorButton.setEnabled(false);
		applyTerminatorButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				FESelectFileWizardPage.this.onClickApplyTerminator();
			}
		});
		
		executeCheckBox = new Button(composite, SWT.CHECK);
		executeCheckBox.setText(ResourceLoader.INSTANCE
				.queryString("FEWizard.SelectFilePage.executeDDLCheckbox")); //$NON-NLS-1$
		executeCheckBox.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				setPageComplete(validatePage());
			}
		});

		data = new GridData(GridData.VERTICAL_ALIGN_END);
		data.horizontalSpan = 2;
		executeCheckBox.setLayoutData(data);
		
		openDDLCheckbox = new Button(composite, SWT.CHECK);
		openDDLCheckbox.setText(ResourceLoader.INSTANCE
				.queryString("FEWizard.SelectFilePage.openDDLCheckbox")); //$NON-NLS-1$
		data = new GridData(GridData.VERTICAL_ALIGN_END);
		data.horizontalSpan = 2;
		openDDLCheckbox.setLayoutData(data);

		this.setControl(composite);
		String defaultProjectName = getDefaultProjectName();
		projectPathText.setText(defaultProjectName);
		fileNameText.setText(getDefaultFileName(defaultProjectName));
		setPageComplete(validatePage());

		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), 
				HelpUtil.getContextId(IHelpContextsSQMFEUI.GENERATE_DDL_FILE_WIZARD_PAGE, 
						FEUiPlugin.getDefault().getBundle().getSymbolicName()));
	}

	private void populatePreviewDDL() {
		String ddlScriptText = ((IGenerateDDL) this.getWizard()).generateDDL()
				.toString();
		ddlPreviewText.setText(ddlScriptText);
	    deltaddl = ddlScriptText;
	}

	protected String getSubtitle() {
		return FE_SELECT_DATABASE_PAGE_HEADER_SUBTITLE;
	}

	public boolean validatePage() {
		boolean isValid = true;
		String container = projectPathText.getText();
		String fileName = fileNameText.getText();

		if (container.length() == 0) {
			updateMessage(ResourceLoader.INSTANCE
					.queryString("FEWizard.SelectFilePage.specifyFolderMessage")); //$NON-NLS-1$
			return false;
		}

		// Check if container exists
		IPath containerPath = new Path(String.valueOf(IPath.SEPARATOR)
				+ container.trim());

		if ((containerPath.segmentCount() == 0)) {
			updateError(ResourceLoader.INSTANCE
					.queryString("FEWizard.SelectFilePage.specifyValidFolderMessage")); //$NON-NLS-1$
			return false;
		} else if ((containerPath.segmentCount() == 1)) {
			if ((!ResourcesPlugin.getWorkspace().getRoot()
					.exists(containerPath) || !ResourcesPlugin.getWorkspace()
					.getRoot().getProject(container.trim()).exists())) {
				updateError(ResourceLoader.INSTANCE
						.queryString("FEWizard.SelectFilePage.specifyValidFolderMessage")); //$NON-NLS-1$
				return false;
			}
		} else {
			if (!ResourcesPlugin.getWorkspace().getRoot().exists(containerPath)
					|| !ResourcesPlugin.getWorkspace().getRoot().getFolder(
							containerPath).exists()) {
				updateError(ResourceLoader.INSTANCE
						.queryString("FEWizard.SelectFilePage.specifyValidFolderMessage")); //$NON-NLS-1$
				return false;
			}
		}

		if (fileName.length() == 0) {
			updateMessage(ResourceLoader.INSTANCE
					.queryString("FEWizard.SelectFilePage.specifyFileMessage")); //$NON-NLS-1$
			return false;
		}

		int dotLoc = fileName.lastIndexOf('.');
		if (dotLoc != -1) {
			String ext = fileName.substring(dotLoc + 1);
			if (ext.equalsIgnoreCase(SCRIPT_FILE_EXTENSION) == false) { //$NON-NLS-1$
				updateError(ResourceLoader.INSTANCE
						.queryString("FEWizard.SelectFilePage.invalidFileExtensionMessage")); //$NON-NLS-1$
				return false;
			}
		}

		if (isValid) {
			updateError(null);
		}
		return isValid;
	}

	private void updateError(String message) {
		this.setErrorMessage(message);
		this.setMessage(null);
	}

	private void updateMessage(String message) {
		this.setMessage(message);
		this.setErrorMessage(null);
	}

	private void onClickBrowse() {
		ResourceChooserDialog dialog = new ResourceChooserDialog(getShell(),
				ResourcesPlugin.getWorkspace().getRoot(), false, null, false);
		dialog.showClosedProjects(false);

		if (dialog.open() == ResourceChooserDialog.OK) {
			Object[] result = dialog.getResult();
			IStructuredSelection selection = (IStructuredSelection) result[0];
			if (selection != null) {
				Iterator selectionIterator = selection.iterator();
				if (selectionIterator.hasNext()) {
					IResource selectedItem = (IResource) selectionIterator
							.next();
					selectedContainer = (IContainer) selectedItem;
					projectPathText.setText(selectedItem.getFullPath()
							.toOSString());
				}

			}
		}
	}

	public String getResourcePath() {
		String path = ""; //$NON-NLS-1$
		if (projectPathText.getText() != null) {
			path = projectPathText.getText() + String.valueOf(IPath.SEPARATOR) + fileNameText.getText(); //$NON-NLS-1$
		}
		return path;
	}

	public IContainer getSelectedContainer() {
		return this.selectedContainer;
	}
	
	public boolean isExecuteSelected() {
		boolean selected = false;
		if (executeCheckBox != null) {
			selected = executeCheckBox.getSelection();
		}
		return selected;
	}
	
	public void disallowExecute() {
	    executeDisallowed  = true;
	    executeCheckBox.setSelection(false);
	    executeCheckBox.setEnabled(false);
	}

	public boolean isExecuteDisallowed() {
	    return executeDisallowed;
	}

	public boolean isOpenDDLSelected() {
		boolean isSelected = false;
		if (openDDLCheckbox != null) {
			isSelected = openDDLCheckbox.getSelection();
		}
		return isSelected;
	}

	public void disallowEdit() {
	    editDisallowed  = true;
	    openDDLCheckbox.setSelection(false);
	    openDDLCheckbox.setEnabled(false);
	}

	public boolean isEditDisallowed() {
	    return editDisallowed;
	}

	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
	        if (deltaddl == null) {
	            populatePreviewDDL();   
	        }           
		}
	}

	public void setTerminator(String terminator){
		this.terminator = terminator;
		if (terminatorText != null){
			terminatorText.setText(terminator);
		}
	}
	
	public String getTerminator(){
		return this.terminator;
	}
	
	private void onClickApplyTerminator(){
		if (terminatorText != null){
			this.terminator = terminatorText.getText().trim();
			populatePreviewDDL();			
			this.applyTerminatorButton.setEnabled(false);
	        this.setNeedsGenerateDDL(false);
		}		
	}
	
	private void onTerminatorTextModified(){
	    this.setNeedsGenerateDDL(true);
		this.applyTerminatorButton.setEnabled(true);
	}
	
	private String getDefaultProjectName() {
		String projectName = "";
		String selectionProject = null;

		try {
			selectionProject = selection != null && ((SQLObject) selection.get(0)).eResource() != null ? ((SQLObject) selection.get(0)).eResource()
					.getURI().segmentsList().get(1).toString() : null;
		} catch (Exception e) {
		}

		if ((selectionProject != null)
				&& (!selectionProject.equals(""))
				&& (ResourcesPlugin.getWorkspace().getRoot().exists(new Path(
						selectionProject)))) {
			projectName = String.valueOf(IPath.SEPARATOR) + selectionProject;

		} else {
			IProject[] allProjects = ResourcesPlugin.getWorkspace().getRoot()
					.getProjects();
			for (int index = 0; index < allProjects.length; index++) {
				if (allProjects[index].isOpen()) {
                    // Remove the trailing slash.
                    String path = allProjects[index].getFullPath().toString();
					projectName = path.substring(1, path.length());
					break;
				}
			}
		}
		return projectName;
	}

	private String getDefaultFileName(String folderName) {
		String fileName = "";
		String baseName = DEFAULT_SCRIPT_FILE_BASE_NAME;
		if (!ResourcesPlugin.getWorkspace().getRoot().exists(
				new Path(folderName + String.valueOf(IPath.SEPARATOR) + baseName + "."
						+ SCRIPT_FILE_EXTENSION))) {
			fileName = baseName + "." + SCRIPT_FILE_EXTENSION;
		} else {
			for (int index = 1;; index++) {
				try {
					if (!ResourcesPlugin.getWorkspace().getRoot().exists(
							new Path(folderName + String.valueOf(IPath.SEPARATOR) + baseName + index + "."
									+ SCRIPT_FILE_EXTENSION))) {
						fileName = baseName + index + "."
								+ SCRIPT_FILE_EXTENSION;
						break;
					}
				} catch (Exception e) {
				}
			}
		}

		return fileName;
	}

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(FEUiPlugin.getDefault().getBundle().getSymbolicName());

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}

	public void setNeedsGenerateDDL(boolean needsGenerateDDL) {
	    this.needsGenerateDDL = needsGenerateDDL;
	}

	public boolean isNeedsGenerateDDL() {
	    return needsGenerateDDL;
	}
	    
	public void clearDeltaDDL() {
	    this.deltaddl = null;
	}
}