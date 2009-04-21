/**
 * Created on Jan. 7, 2005
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.routineeditor.ui.actions;

import java.util.ResourceBundle;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorImages;
import org.eclipse.datatools.sqltools.sqleditor.IPageUpdate;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.TextEditorAction;

/**
 * @author Li Huang
 * 
 */
public class SaveToDatabaseAction extends TextEditorAction implements IPageUpdate {
	SQLEditor _sqlEditor;
    private boolean _isSourcePage = true;

	/**
	 * @param bundle
	 * @param prefix
	 * @param editor
	 */
	public SaveToDatabaseAction(ResourceBundle bundle, String prefix,
			ITextEditor editor) {
		super(bundle, prefix, editor);
		_sqlEditor = (SQLEditor) editor;
		setImageDescriptor(RoutineEditorImages
				.getImageDescriptor("save_to_database"));
		setActionDefinitionId(ISQLEditorActionConstants.SAVE_TO_DATABASE_ACTION_ID);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
				HelpUtil.getContextId(IHelpContextIds.SAVE_TO_DATABASE_ACTION, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
	}

	public void run() {
        IEditorPart parent = _sqlEditor.getParentEditor();
        if (parent != null)
        {
            parent.doSave(new NullProgressMonitor());
        }
        else
        {
            _sqlEditor.doSave(new NullProgressMonitor());
        }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.texteditor.IUpdate#update()
	 */
	public void update() {
		_sqlEditor = (SQLEditor) getTextEditor();
		if (_sqlEditor.isEditorInputReadOnly()) {
			setEnabled(false);
		} else {
			setEnabled(_sqlEditor.isDirty());
		}
	}

    public void update(boolean isSQLEditorPage)
    {
        _isSourcePage = isSQLEditorPage;
        update();
    }
}
