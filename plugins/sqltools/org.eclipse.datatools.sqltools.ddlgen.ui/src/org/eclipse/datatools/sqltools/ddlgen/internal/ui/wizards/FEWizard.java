/*******************************************************************************
 * Copyright (c) 2001, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.ddlgen.internal.ui.wizards;

import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileProvider;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.icons.ImageDescription;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.SaveDDLUtility;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.FEUiPlugin;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.IHelpContextsSQMFEUI;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.util.ResourceLoader;
import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.Messages;
import org.eclipse.datatools.sqltools.sqleditor.result.GroupSQLResultRunnable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.SameShellProvider;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.PropertyDialogAction;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class FEWizard extends Wizard implements IGenerateDDL, IContextProvider {

	private FESelectOptionsWizardPage selectOptionsPage;

	private FESelectObjectsWizardPage selectObjectsPage;

	private FESelectFileWizardPage selectFilePage;

	private FESpecifyExistingConnectionsWizardPage existingConnectionsPage;

	private FESummaryWizardPage summaryPage;

	private static String SELECT_OPTIONS_WIZARD_PAGE_NAME = "com.ibm.datatools.modeler.fe.ui.wizards.SelectOptionsPage"; //$NON-NLS-1$

	private static String SELECT_OBJECTS_WIZARD_PAGE_NAME = "com.ibm.datatools.modeler.fe.ui.wizards.SelectObjectsPage"; //$NON-NLS-1$

	private static String SELECT_FILE_WIZARD_PAGE_NAME = "com.ibm.datatools.modeler.fe.ui.wizards.SelectFilePage"; //$NON-NLS-1$

	private static String EXISTING_CONNECTIONS_WIZARD_PAGE_NAME = "com.ibm.datatools.modeler.fe.ui.wizards.ExistingConnectionsPage"; //$NON-NLS-1$

	private static String SUMMARY_WIZARD_PAGE_NAME = "com.ibm.datatools.modeler.fe.ui.wizards.SummaryPage"; //$NON-NLS-1$

	private static final String TRUE_SUMMARY_DATA_TEXT = ResourceLoader.INSTANCE
			.queryString("FEWizard.trueSummaryValueText"); //$NON-NLS-1$

	private static final String FALSE_SUMMARY_DATA_TEXT = ResourceLoader.INSTANCE
			.queryString("FEWizard.falseSummaryValueText"); //$NON-NLS-1$

	private List selection;

	private DDLGenerator feProvider;

	private DatabaseDefinition databaseDefinition;

	String lastProductValueSet = ""; //$NON-NLS-1$

	String lastVersionValueSet = ""; //$NON-NLS-1$

	private StringWriter writer = new StringWriter();

	private String[] ddlScripts = new String[0];

	private boolean hasGenerated = false;

	private String LINE_RETURN = System.getProperty("line.separator");

	public FEWizard(List selection) {
		super();
		this.selection = selection;
		this.setWindowTitle(ResourceLoader.INSTANCE
				.queryString("FE_WIZARD_TITLEBAR_TEXT")); //$NON-NLS-1$
		this.setDefaultPageImageDescriptor(ImageDescription
				.getGenerateDDLWizard());
		setNeedsProgressMonitor(true);
	}

	public void addPages() {

		Database database = this.getDatabase((SQLObject) selection.get(0));
		databaseDefinition = RDBCorePlugin.getDefault()
				.getDatabaseDefinitionRegistry().getDefinition(
						database.getVendor(), database.getVersion());
		this.feProvider = databaseDefinition.getDDLGenerator();
		FEConfigurationData configurationData = new FEConfigurationData(
				this.feProvider.getOptions((SQLObject[]) selection
						.toArray(new SQLObject[selection.size()])));

		selectOptionsPage = new FESelectOptionsWizardPage(
                SELECT_OPTIONS_WIZARD_PAGE_NAME, configurationData,new Listener(){
                    public void handleEvent(Event event) {
                        if (selectFilePage != null){
                            selectFilePage.clearDeltaDDL();
                        }
                    }});
		addPage(selectOptionsPage);

		selectObjectsPage = new FESelectObjectsWizardPage(
                SELECT_OBJECTS_WIZARD_PAGE_NAME, configurationData,new Listener(){
                    public void handleEvent(Event event) {
                        if (selectFilePage != null){
                            selectFilePage.clearDeltaDDL();
                        }
                    }});
		addPage(selectObjectsPage);

		selectFilePage = new FESelectFileWizardPage(
				SELECT_FILE_WIZARD_PAGE_NAME, this.selection);
		addPage(selectFilePage);
		selectFilePage.setTerminator(databaseDefinition
				.getSQLTerminationCharacter());

		existingConnectionsPage = new FESpecifyExistingConnectionsWizardPage(
				EXISTING_CONNECTIONS_WIZARD_PAGE_NAME);
		addPage(existingConnectionsPage);

		summaryPage = new FESummaryWizardPage(SUMMARY_WIZARD_PAGE_NAME);
		addPage(summaryPage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			protected void execute(IProgressMonitor monitor) {
				try {
					performDDLGeneration(monitor);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {
					monitor.done();
				}
			}
		};

		try {
			getContainer().run(false, false, operation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public StringWriter generateDDL() {
		try {
			getContainer().run(false, false, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					writer = new StringWriter();
					monitor.beginTask("", 5); //$NON-NLS-1$
					monitor
							.setTaskName(ResourceLoader.INSTANCE
									.queryString("FEWizard.generatingDDLProgressMessage")); //$NON-NLS-1$
					monitor.worked(1);
					hasGenerated = false;
					FEConfigurationData configurationData = selectOptionsPage
							.getOptions();
					if (configurationData != null) {
						try {
							String fullStatementTermination = "";
							String statementTerminator = selectFilePage
									.getTerminator();
							if (statementTerminator.length() < 2) {
								fullStatementTermination += statementTerminator
										+ LINE_RETURN + LINE_RETURN;
							} else {
								fullStatementTermination += LINE_RETURN
										+ statementTerminator + LINE_RETURN
										+ LINE_RETURN;
							}
							ddlScripts = feProvider.generateDDL(
									(SQLObject[]) selection
											.toArray(new SQLObject[selection
													.size()]), monitor);
							hasGenerated = true;
							monitor.worked(3);
                            String defaultEditorTermnator = "--<ScriptOptions statementTerminator=\"" 
                                + statementTerminator 
                                + "\"/>"
                                + LINE_RETURN + LINE_RETURN;
                            writer.write(defaultEditorTermnator);
                            for (int i = 0; i < ddlScripts.length; i++) {
                                ddlScripts[i] = ddlScripts[i].trim();
                                if(ddlScripts[i].endsWith(statementTerminator)){
                                    ddlScripts[i] = ddlScripts[i].substring(0, ddlScripts[i].length()-statementTerminator.length());
                                }
                                writer.write(ddlScripts[i] + fullStatementTermination);
                            }
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.writer;
	}

	public void performDDLGeneration(IProgressMonitor monitor) {

		try {
			if (hasGenerated) {
				monitor.worked(2);
				monitor.setTaskName(ResourceLoader.INSTANCE
						.queryString("FEWizard.savingDDLProgressMessage")); //$NON-NLS-1$               
				IFile ddlFile = SaveDDLUtility.getInstance()
						.saveDDLFileAsResource(this.writer,
								selectFilePage.getResourcePath());
				monitor.worked(4);
				if (selectFilePage.isOpenDDLSelected()) {
					monitor.setTaskName(ResourceLoader.INSTANCE
							.queryString("FEWizard.openingDDLProgressMessage")); //$NON-NLS-1$
					if (ddlFile != null) {
						IEditorDescriptor editorDescriptor = PlatformUI
								.getWorkbench().getEditorRegistry()
								.getDefaultEditor(ddlFile.getName());
						if (editorDescriptor == null) {
							editorDescriptor = PlatformUI.getWorkbench()
									.getEditorRegistry().getDefaultEditor(
											"file.txt"); //$NON-NLS-1$
						}
						PlatformUI.getWorkbench().getActiveWorkbenchWindow()
								.getActivePage().openEditor(
										new FileEditorInput(ddlFile),
										editorDescriptor.getId());
					}
				}
				if (selectFilePage.isExecuteSelected()) {
					IConnectionProfile conn = getDBConnection();
					if (conn != null && ddlScripts != null) {
						this.executeDDL(ddlScripts, conn);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!hasGenerated) {
				MessageBox box = new MessageBox(Display.getCurrent()
						.getActiveShell(), SWT.ABORT | SWT.ICON_ERROR);
				box.setMessage(ResourceLoader.INSTANCE
						.queryString("FEWizard.ddlGenerationErrorMessage")); //$NON-NLS-1$
				box.open();
			}
		}
		monitor.worked(5);
	}

	private void executeDDL(String[] sqlStatements,
			IConnectionProfile connectionProfile) {
		DatabaseIdentifier databaseIdentifier = new DatabaseIdentifier(
				connectionProfile.getName(), "");
		if (databaseIdentifier != null) {
			try {
				Job job = new GroupSQLResultRunnable(null, sqlStatements, null,
						null, databaseIdentifier, false, new HashMap(),
						Messages.BaseExecuteAction_group_exec_title,
						"Generate DDL");
				job.setName(Messages.BaseExecuteAction_job_title);
				job.setUser(true);
				job.schedule();

				PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().showView(EditorConstants.RESULTS_VIEW);
			} catch (Exception e) {
			}
		}
	}

	public boolean canFinish() {
		boolean canFinish = false;
		canFinish = selectOptionsPage.isPageComplete()
				&& selectObjectsPage.isPageComplete()
				&& selectFilePage.isPageComplete() && summaryPage.isVisible();
		return canFinish;

	}

	public IWizardPage getNextPage(IWizardPage wizardPage) {
		IWizardPage nextPage = null;

		String currentProductSet = databaseDefinition.getProduct();
		String currentVersionSet = databaseDefinition.getVersion();

		if ((!lastProductValueSet.equals(currentProductSet))
				|| ((!lastVersionValueSet.equals(currentVersionSet)))) {
			lastProductValueSet = currentProductSet;
			lastVersionValueSet = currentVersionSet;
			existingConnectionsPage.setAllowedVendor(databaseDefinition
					.getProduct());
			existingConnectionsPage.setAllowedVersion(databaseDefinition
					.getVersion());
		}

		if (wizardPage.getName().equals(SELECT_OPTIONS_WIZARD_PAGE_NAME)) {
			nextPage = selectObjectsPage;
		} else if (wizardPage.getName().equals(SELECT_OBJECTS_WIZARD_PAGE_NAME)) {
			nextPage = selectFilePage;
		} else if (wizardPage.getName().equals(SELECT_FILE_WIZARD_PAGE_NAME)) {
			if (selectFilePage.isExecuteSelected()) {
				nextPage = existingConnectionsPage;
			} else {
				nextPage = populateSummaryPage();
			}
		} else if (wizardPage.getName().equals(
				EXISTING_CONNECTIONS_WIZARD_PAGE_NAME)) {
			nextPage = populateSummaryPage();
		} else if (wizardPage.getName().equals(SUMMARY_WIZARD_PAGE_NAME)) {
			nextPage = null;
		} else {
			nextPage = super.getNextPage(wizardPage);
		}
		return nextPage;
	}

	// Populate Summary Page
	private FESummaryWizardPage populateSummaryPage() {
		if (summaryPage == null) {
			summaryPage = new FESummaryWizardPage(SUMMARY_WIZARD_PAGE_NAME);
			addPage(summaryPage);
		}
		// Populate summary page
		EngineeringOption[] options = selectOptionsPage.getOptions()
				.getOptions();
		FESummaryProperty[] properties = null;
		Vector propertiesCollection = new Vector();
		propertiesCollection
				.add(new FESummaryProperty(
						ResourceLoader.INSTANCE
								.queryString("FEWizard.fileLocationSummaryProperty"), selectFilePage.getResourcePath())); //$NON-NLS-1$
		propertiesCollection
				.add(new FESummaryProperty(
						ResourceLoader.INSTANCE
								.queryString("FEWizard.openDDLForEditingSummaryProperty"), String.valueOf(selectFilePage.isOpenDDLSelected()))); //$NON-NLS-1$     
		propertiesCollection.add(new FESummaryProperty(ResourceLoader.INSTANCE
				.queryString("FEWizard.runDDLOnServerSummaryProperty"),
				selectFilePage.isExecuteSelected() ? TRUE_SUMMARY_DATA_TEXT
						: FALSE_SUMMARY_DATA_TEXT)); //$NON-NLS-1$ 
		
		if (selectFilePage.isExecuteSelected()){
			propertiesCollection
			.add(new FESummaryProperty(
					ResourceLoader.INSTANCE
							.queryString("FEWizard.connectionNameSummaryProperty"), existingConnectionsPage.getSelectedConnection().getName())); //$NON-NLS-1$
		}

		propertiesCollection.add(new FESummaryProperty("", "")); //$NON-NLS-1$ //$NON-NLS-2$

		// Add generation options
		for (int index = 0; index < options.length; index++) {
			propertiesCollection.add(new FESummaryProperty(options[index]
					.getOptionDescription(), String.valueOf(options[index]
					.getBoolean())));
		}
		propertiesCollection.add(new FESummaryProperty("", "")); //$NON-NLS-1$ //$NON-NLS-2$

		// Add object selection options
		options = selectObjectsPage.getOptions().getOptions();
		for (int index = 0; index < options.length; index++) {
			propertiesCollection.add(new FESummaryProperty(options[index]
					.getOptionDescription(), String.valueOf(options[index]
					.getBoolean())));
		}

		properties = new FESummaryProperty[propertiesCollection.size()];
		propertiesCollection.toArray(properties);
		summaryPage.setProperties(properties);

		return summaryPage;
	}

	private Database getDatabase(EObject object) {
		if (object == null) {
			return null;
		} else if (object instanceof Database) {
			return (Database) object;
		} else {
			return getDatabase(RDBCorePlugin.getDefault()
					.getContainmentService().getContainer(object));
		}
	}

	private ContextProviderDelegate contextProviderDelegate = new ContextProviderDelegate(
			FEUiPlugin.getDefault().getBundle().getSymbolicName());

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}

	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp(getShell(), HelpUtil.getContextId(
				IHelpContextsSQMFEUI.GENERATE_DDL_WIZARD, FEUiPlugin
						.getDefault().getBundle().getSymbolicName()));
	}

	private IConnectionProfile getDBConnection() {
		IConnectionProfile connection = null;
		IConnectionProfile connectionProfile = existingConnectionsPage
				.getSelectedConnection();
		if (connectionProfile != null) {
			connection = getActiveConnection(connectionProfile);
		}
		return connection;
	}

	private IConnectionProfile getActiveConnection(
			IConnectionProfile connectionProfile) {
		IConnectionProfile activeConnection = null;
		IManagedConnection connection = null;
		IStatus connectionStatus = null;

		if (connectionProfile.getConnectionState() == IConnectionProfile.CONNECTED_STATE) {
			activeConnection = connectionProfile.getManagedConnection(
					"java.sql.Connection").getConnection()
					.getConnectionProfile();
		} else {
			connectionStatus = connectionProfile.connectWithoutJob();
			if (connectionProfile.getConnectionState() == IConnectionProfile.CONNECTED_STATE) {
				return connectionProfile.getManagedConnection(
						"java.sql.Connection").getConnection()
						.getConnectionProfile();
			}
			while (connectionProfile.getConnectionState() != IConnectionProfile.CONNECTED_STATE) {
				// display error message
				new DisplayMessage(ResourceLoader.INSTANCE
						.queryString("FE_WIZARD_TITLEBAR_TEXT"),
						connectionStatus.getChildren()[0].getException()
								.getLocalizedMessage()).run();

				// Prompt to fix properties
				PropertyDialogAction propertyDialogAction = new PropertyDialogAction(
						new SameShellProvider(this.getShell()),
						new FEWizardSelectionProvider(connectionProfile));

				StructuredSelection selection = new StructuredSelection(
						connectionProfile);
				propertyDialogAction.selectionChanged(selection);
				if (propertyDialogAction.isApplicableForSelection()) {
					PreferenceDialog dialog = propertyDialogAction
							.createDialog();
					String initialPage = ((ConnectionProfileProvider) connectionProfile
							.getProvider()).getPropertiesPersistenceHook()
							.getConnectionPropertiesPageID();
					if (initialPage != null) {
						((IWorkbenchPreferenceContainer) dialog).openPage(
								initialPage, null);
					}
					if (dialog.open() == Dialog.CANCEL) {
						return activeConnection;
					}
				}
				connectionStatus = connectionProfile.connectWithoutJob();
				connection = connectionProfile
						.getManagedConnection("java.sql.Connection");
			}
			activeConnection = connection.getConnection()
					.getConnectionProfile();
		}
		return activeConnection;
	}

	public class DisplayMessage implements Runnable {
		String title, message;

		public DisplayMessage(String title, String message) {
			this.title = title;
			this.message = message;
		}

		public void run() {
			MessageDialog.openInformation(getShell(), title, message);
		}
	}

	private class FEWizardSelectionProvider implements ISelectionProvider {

		private IStructuredSelection selection;

		public FEWizardSelectionProvider(IConnectionProfile connectionProfile) {
			selection = new StructuredSelection(connectionProfile);
		}

		public void addSelectionChangedListener(
				ISelectionChangedListener listener) {
		}

		public ISelection getSelection() {
			return selection;
		}

		public void removeSelectionChangedListener(
				ISelectionChangedListener listener) {
		}

		public void setSelection(ISelection selection) {
		}
	}
}