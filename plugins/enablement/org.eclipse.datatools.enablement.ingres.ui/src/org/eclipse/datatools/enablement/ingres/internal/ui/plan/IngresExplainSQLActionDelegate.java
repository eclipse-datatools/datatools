/*******************************************************************************
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.plan;

import java.util.HashMap;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.plan.BaseExplainAction;
import org.eclipse.datatools.sqltools.sqleditor.plan.Images;
import org.eclipse.datatools.sqltools.sqleditor.plan.Messages;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * Action class to explain the selected SQL statements in SQL Editor or Routine
 * Editor
 * 
 * Basically a copy of
 * org.eclipse.datatools.sqltools.sqleditor.plan.ExplainSQLActionDelegate with
 * extension to be used as additional action in the SQLEditorService. XXX This class
 * is for provisional usage only. It's obsolete when a menu entry for QEP generation is available.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresExplainSQLActionDelegate extends BaseExplainAction implements
		IEditorActionDelegate, ISelectionChangedListener, IUpdate {

	protected SQLEditor _sqlEditor;

	public IngresExplainSQLActionDelegate() {
		setText(Messages.getString("ExplainSQLActionDelegate.action_title")); //$NON-NLS-1$
		setToolTipText(Messages
				.getString("ExplainSQLActionDelegate.action_tooltip")); //$NON-NLS-1$
		setImageDescriptor(Images.DESC_EXPLAIN_SQL);
		setActionDefinitionId(ISQLEditorActionConstants.EXPLAIN_SQL_ACTION_ID);
	}

	public void setActiveEditor(SQLEditor targetEditor) {
		_sqlEditor = targetEditor;
		targetEditor.getSelectionProvider().addSelectionChangedListener(this);
		update();
	}

	public void update() {
		setEnabled(_sqlEditor != null && (_sqlEditor.isConnected())
				&& super.canBeEnabled());
	}

	public DatabaseIdentifier getDatabaseIdentifier() {
		return _sqlEditor == null ? null : _sqlEditor.getDatabaseIdentifier();
	}

	public String getSQLStatements() {
		String orignalSql = (_sqlEditor == null) ? null : _sqlEditor
				.getTargetText();
		return orignalSql;
	}

	/**
	 * Sets the focus to the editor after the execution plan is shown
	 */
	public Runnable getPostRun() {
		Runnable postRun = new Runnable() {
			public void run() {
				_sqlEditor.getEditorSite().getPage().activate(_sqlEditor);
			}
		};
		return postRun;
	}

	/**
	 * Returns the variable declarations in the SQL Editor
	 */
	protected HashMap getVariableDeclarations() {
		ITextSelection _selection = (ITextSelection) _sqlEditor
				.getSelectionProvider().getSelection();
		int start = 0;
		int length = 0;
		if (_selection == null) {
			_selection = (ITextSelection) _sqlEditor.getSelectionProvider()
					.getSelection();
		}
		// get the offset of the selection
		if (_selection != null && !_selection.isEmpty()) {
			start = _selection.getOffset();
			length = _selection.getLength();
			if (length < 0) {
				length = -length;
				start -= length;
			}
		}
		// when user selects a range
		int offset = length > 0 ? start + 1 : start;

		IDocument document = _sqlEditor.getDocumentProvider().getDocument(
				_sqlEditor.getEditorInput());
		ParsingResult result = _sqlEditor.getParsingResult();
		HashMap variables = new HashMap();
		if (result != null) {
			variables = result.getVariables(document, offset);
			HashMap sp_params = result.getParameters(document, offset);
			variables.putAll(sp_params);
		}
		return variables;
	}

	/**
	 * Updates the action when selection changes
	 * 
	 * @param event
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelection() instanceof ITextSelection) {
			update();
		}
	}

	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		setActiveEditor((SQLEditor) targetEditor);
	}

	public void run(IAction action) {
		run();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof ITextSelection) {
			update();
		}
	}
}
