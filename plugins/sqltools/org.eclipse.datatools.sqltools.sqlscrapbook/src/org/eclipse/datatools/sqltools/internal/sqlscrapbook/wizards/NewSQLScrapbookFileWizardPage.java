/*******************************************************************************
 * Copyright (c) 2002, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Exadel Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IPath;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.common.ui.resource.WizardNewFileCreationPage;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SQLFilePreferenceConstants;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection.ConnectionInfoGroup;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor.SQLScrapbookEditor;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor.SQLScrapbookEditorInput;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.ExceptionHandler;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.SQLFileUtil;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ISetSelectionTarget;

/**
 * Page to create a new SQL scrapbook file.
 */
public class NewSQLScrapbookFileWizardPage extends WizardNewFileCreationPage {
	
	private static final String fgDefaultExtension= ".sql"; //$NON-NLS-1$
	private ConnectionInfoGroup _group     = null;
	 
	public NewSQLScrapbookFileWizardPage(IStructuredSelection selection) {
		super("createScrapBookPage", selection); //$NON-NLS-1$
		setTitle(SqlscrapbookPlugin.getResourceString("NewSQLScrapbookFileWizardPage.title")); //$NON-NLS-1$
	}

	public boolean finish() {
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
				_group.finish();
				
				if(SqlscrapbookPlugin.getDefault().getPreferenceStore().getBoolean(SQLFilePreferenceConstants.DEFAULT_OPEN))
				{
					SQLScrapbookEditorInput editorInput = new SQLScrapbookEditorInput(file, _group.getConnectionInfo());
				    
	                SqlscrapbookPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(editorInput,
	                SQLScrapbookEditor.EDITOR_ID);	
				}
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
	
		String fileName= getFileName();
		if (fileName != null && !fileName.endsWith(fgDefaultExtension)) {		
			fileName= fileName + fgDefaultExtension;
			IPath path= getContainerFullPath();
			
			if (path != null && workspaceRoot.exists(path.append(fileName))) {
				setErrorMessage(SqlscrapbookPlugin.getResourceString("NewSQLScrapbookFileWizardPage.error.AlreadyExists")); //$NON-NLS-1$
				return false;
			}
		}
        //check profile
        if (valid && _group != null && !_group.canFinish())
        {
            setMessage(SqlscrapbookPlugin.getResourceString("NewFileWithProfilePage.error.profile"));
            setErrorMessage(null);
            valid = false;
        }
        if (valid && _group != null && _group.getWarning() != null)
        {
            setMessage(_group.getWarning());
            setErrorMessage(null);
        }
        return valid;
	}
	
    /**
     * (non-Javadoc) Method declared on WizardNewFileCreateWizard.
     */
    public void createPageControl(Composite parent)
    {
        super.createPageControl(parent);

        ISQLEditorConnectionInfo connInfo = SQLFileUtil.getDefaultConnectionInfo();
        _group = new ConnectionInfoGroup(parent, this, connInfo, false, false);

        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, HelpUtil.getContextId(IHelpContextIds.NEW_SQL_FILE, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
    }

}


