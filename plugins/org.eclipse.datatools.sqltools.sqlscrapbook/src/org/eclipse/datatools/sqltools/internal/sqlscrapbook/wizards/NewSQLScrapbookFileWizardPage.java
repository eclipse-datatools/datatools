/*******************************************************************************
 * Copyright (c) 2002, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Exadel Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IPath;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor.SQLScrapbookEditorInput;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.ExceptionHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.part.ISetSelectionTarget;

/**
 * Page to create a new SQL scrapbook file.
 */
public class NewSQLScrapbookFileWizardPage extends WizardNewFileCreationPage {
	
	private static final String fgDefaultExtension= ".sqlpage"; //$NON-NLS-1$
	
	public NewSQLScrapbookFileWizardPage(IStructuredSelection selection) {
		super("createScrapBookPage", selection); //$NON-NLS-1$
		setTitle(SqlscrapbookPlugin.getResourceString("NewSQLScrapbookFileWizardPage.title")); //$NON-NLS-1$
	}

	public boolean finish(ISQLEditorConnectionInfo connectionInfo) {
		// add extension if non is provided 
		String fileName= getFileName();
		if (fileName != null && !fileName.endsWith(fgDefaultExtension)) {
			setFileName(fileName + fgDefaultExtension);
		}

		boolean retValue= super.validatePage();

		final IFile file= createNewFile();
		if (retValue && file != null) {
			Shell shell= getShell();
			IWorkbenchPage page= SqlscrapbookPlugin.getActivePage();
			if (shell == null || page == null) {
				return true;
			}
			final IWorkbenchPart focusPart= page.getActivePart();
			if (focusPart instanceof ISetSelectionTarget) {
				shell.getDisplay().asyncExec(new Runnable() {
					public void run() {
						ISelection selection= new StructuredSelection(file);
						((ISetSelectionTarget) focusPart).selectReveal(selection);
					}
				});
			}
			
			
			try {
				//IDE.openEditor(page, file, true); // tau 04.07.04
				// tau 04.07.04, 17.07.04
				
				// add tau 10.03.2005
			    SQLScrapbookEditorInput editorInput = new SQLScrapbookEditorInput(file, connectionInfo);
			    
				/*
                SqlscrapbookPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(editorInput,
                    "org.eclipse.datatools.sqltools.internal.sqlscrapbook.views.SQLScrapbookEditor");
				*/

                SqlscrapbookPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(editorInput,
                "org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor.SQLScrapbookEditor");				
				
				return true;
			} catch (PartInitException e) {
				ExceptionHandler.handle(e, shell, SqlscrapbookPlugin.getResourceString("NewSQLScrapbookFileWizardPage.open_error.message"),  e.getMessage()); //$NON-NLS-1$
			}
			
		}
		return false;
	}
	
	/**
	 * @see WizardNewFileCreationPage#validatePage
	 */
	protected boolean validatePage() {
		// check whether file with extension doesn't exist
		boolean valid= super.validatePage();
		if (!valid)
			return false;
		
		IWorkspaceRoot workspaceRoot= SqlscrapbookPlugin.getWorkspace().getRoot();
		/*IPath containerPath= getContainerFullPath();
		if (containerPath != null && containerPath.segmentCount() > 0) {
			IProject project= workspaceRoot.getProject(containerPath.segment(0));
			try {
				if (!project.hasNature(JavaCore.NATURE_ID)) {
					setErrorMessage(SQLScrapbookMessages.getString("NewSQLScrapbookFileWizardPage.error.OnlyInJavaProject")); //$NON-NLS-1$
					return false;
				}
			} catch (CoreException e) {
                SqlscrapbookPlugin.log(e.getStatus());
			}
		}*/
	
		String fileName= getFileName();
		if (fileName != null && !fileName.endsWith(fgDefaultExtension)) {		
			fileName= fileName + fgDefaultExtension;
			IPath path= getContainerFullPath();
			
			if (path != null && workspaceRoot.exists(path.append(fileName))) {
				setErrorMessage(SqlscrapbookPlugin.getResourceString("NewSQLScrapbookFileWizardPage.error.AlreadyExists")); //$NON-NLS-1$
				return false;
			}
		}
		return true;
	}
	
	/*
	 * @see WizardNewFileCreationPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		super.createControl(parent);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "new_sqlscrapbook_wizard_page_context");		
	}

}


