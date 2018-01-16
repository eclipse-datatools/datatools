package org.eclipse.datatools.sqltools.sqlscrapbook.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor.SQLScrapbookEditor;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.SQLFileUtil;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorageEditorInput;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Our sample action implements workbench action delegate. The action proxy will
 * be created by the workbench and shown in the UI. When the user tries to use
 * the action, this delegate will be created and execution will be delegated to
 * it.
 * 
 * @see IWorkbenchWindowActionDelegate
 */
public class OpenScrapbookAction extends Action implements IHandler,
		IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	private transient ListenerList listenerList = null;

	/**
	 * The constructor.
	 */
	public OpenScrapbookAction() {
	}

	/**
	 * The action has been activated. The argument of the method represents the
	 * 'real' action sitting in the workbench UI.
	 * 
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		ISQLEditorConnectionInfo editorConnectionInfo = SQLFileUtil.getDefaultConnectionInfo();
		
		String scrap = "";
		SQLEditorStorageEditorInput editorStorageEditorInput = new SQLEditorStorageEditorInput(
				"", scrap);
		
		editorStorageEditorInput.setConnectionInfo(SQLFileUtil.getConnectionInfo4Scrapbook(editorConnectionInfo));

		// the name will show as the title of the editor
		IEditorReference[] editors = window.getActivePage()
				.getEditorReferences();
		int suffix = 0;
		List editorNameList = new ArrayList();
		for (int i = 0; i < editors.length; i++) {
			editorNameList.add(editors[i].getName());
		}

		for (;;) {
			String name = NLS.bind(
					Messages.OpenScrapbookAction_scrapbook_title, Integer
							.toString(suffix));
			if (!editorNameList.contains(name)) {
				editorStorageEditorInput.setName(name);
				try {
					window.getActivePage().openEditor(editorStorageEditorInput,
							SQLScrapbookEditor.EDITOR_ID);
				} catch (PartInitException e) {
					SqlscrapbookPlugin.log(e);
				}
				break;
			}
			suffix++;
		}

	}

	/**
	 * Selection in the workbench has been changed. We can change the state of
	 * the 'real' action here if we want, but this can only happen after the
	 * delegate has been created.
	 * 
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		window = SqlscrapbookPlugin.getActiveWorkbenchWindow();
	}

	/**
	 * We can use this method to dispose of any system resources we previously
	 * allocated.
	 * 
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
		listenerList = null;
	}

	/**
	 * We will cache window object in order to be able to provide parent shell
	 * for the message dialog.
	 * 
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void addHandlerListener(IHandlerListener handlerListener) {
		if (listenerList == null) {
			listenerList = new ListenerList(ListenerList.IDENTITY);
		}

		listenerList.add(handlerListener);
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart part = HandlerUtil.getActivePart(event);
		if (part == null)
			return null;

		init(part.getSite().getWorkbenchWindow());

		run(null);

		return null;
	}

	public void removeHandlerListener(IHandlerListener handlerListener) {
		if (listenerList != null) {
			listenerList.remove(handlerListener);

			if (listenerList.isEmpty()) {
				listenerList = null;
			}
		}
	}

}