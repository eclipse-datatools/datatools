/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.util.ArrayList;

import org.eclipse.datatools.sqltools.editor.template.GenericSQLContext;
import org.eclipse.datatools.sqltools.editor.template.GenericSQLContextType;
import org.eclipse.datatools.sqltools.editor.template.SQLTemplate;
import org.eclipse.datatools.sqltools.editor.template.TemplateConstant;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.datatools.sqltools.sqleditor.internal.templates.SQLTemplateProposal;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.templates.GlobalTemplateVariables;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SQLTemplateProposalsService {
    private static final String             _LINE_SELECTION           = "${"  + GlobalTemplateVariables.LineSelection.NAME + "}"; //$NON-NLS-1$ //$NON-NLS-2$
    private static final String             _WORD_SELECTION           = "${"  + GlobalTemplateVariables.WordSelection.NAME + "}"; //$NON-NLS-1$ //$NON-NLS-2$


	/**
	 * Inspects the context of the compilation unit around
	 * <code>completionPosition</code> and feeds the collector with proposals.
	 * 
	 * @param viewer
	 *            the text viewer
	 * @param completionPosition
	 *            the context position in the document of the text viewer
	 * @param word
	 *            the word before the proposal invocation point
	 * @param isStatementStart
	 *            whether it is possible to start a new statement
	 *  @return ArrayList of <code>TemplateProposal</code>s
	 */
	public ArrayList getProposals(SQLEditor editor, int completionPosition,
			String word, boolean isStatementStart, Point selection) {
		ArrayList proposals = new ArrayList();
		IDocument document = editor.getSV().getDocument();

		TemplateContextType contextType;
		SQLDevToolsUIConfiguration config = SQLToolsUIFacade
				.getConfigurationByVendorIdentifier(editor.getConnectionInfo()
						.getDatabaseVendorDefinitionId());
		if (config != null) {
			contextType = config.getSQLUIService().getSQLContextType();
		} else {
			contextType = SQLEditorPlugin.getDefault()
					.getTemplateContextTypeRegistry().getContextType(
							GenericSQLContextType.SQL_CONTEXT_TYPE);
		}

		if (!(contextType instanceof GenericSQLContextType)) {
			return proposals;
		}

		// remember selected text
		String selectedText = null;
		if (selection.y != 0) {
			try {
				selectedText = document.get(selection.x, selection.y);
			} catch (BadLocationException e) {
			}
		}

		GenericSQLContext context = ((GenericSQLContextType) contextType)
				.createContext(document, completionPosition - word.length(),
						selection.y, editor.getParsingResult());
		context.setVariable("selection", selectedText); //$NON-NLS-1$
		IRegion region = new Region(completionPosition, selection.y);

		Template[] templates = SQLEditorPlugin.getDefault().getTemplateStore()
				.getTemplates();

		if (selection.y == 0) {
			for (int i = 0; i != templates.length; i++) {
				if (context.canEvaluate(templates[i])) {
					if (word.equals("")) //$NON-NLS-1$
					{
						if (isStatementStart) {
							proposals.add(new SQLTemplateProposal(editor, templates[i],
									context, region, getTemplateImage(templates[i]),
									SQLCompletionProposal.TEMPLATE));
						}
					} else if (SQLParserCompletionEngine.startsWithIgnoreCase(templates[i].getName(),
							word)) {
						proposals.add(new SQLTemplateProposal(editor, templates[i],
								context, region, getTemplateImage(templates[i]),
								SQLCompletionProposal.TEMPLATE));
					}
				}
			}

		} else {
			boolean multipleLinesSelected = areMultipleLinesSelected(editor.getSV());

			for (int i = 0; i != templates.length; i++) {
				Template template = templates[i];
				if (context.canEvaluate(template)
						&& (!multipleLinesSelected
								&& template.getPattern().indexOf(
										_WORD_SELECTION) != -1 || (multipleLinesSelected && template
								.getPattern().indexOf(_LINE_SELECTION) != -1))) {
					proposals.add(new SQLTemplateProposal(editor, templates[i], context,
							region, getTemplateImage(templates[i]),
							SQLCompletionProposal.TEMPLATE));
				}
			}
		}
		return proposals;
	}

	private Image getTemplateImage(Template template)
	{
	    if (template instanceof SQLTemplate)
	    {
	        SQLTemplate sqlTemplate = (SQLTemplate) template;
	        if (sqlTemplate.getId().startsWith(TemplateConstant.INTELLIGENT_TEMPLATE))
	        {
	            return SQLEditorResources.getImage("intelligent_template");
	        }
	    }
	    return SQLEditorResources.getImage("template_obj");
	}
	/**
	 * Returns <code>true</code> if one line is completely selected or if
	 * multiple lines are selected. Being completely selected means that all
	 * characters except the new line characters are selected.
	 * 
	 * @return <code>true</code> if one or multiple lines are selected
	 * @since 2.1
	 */
	protected boolean areMultipleLinesSelected(ITextViewer viewer) {
		if (viewer == null) {
			return false;
		}

		Point s = viewer.getSelectedRange();
		if (s.y == 0) {
			return false;
		}
		try {

			IDocument document = viewer.getDocument();
			int startLine = document.getLineOfOffset(s.x);
			int endLine = document.getLineOfOffset(s.x + s.y);
			IRegion line = document.getLineInformation(startLine);
			return startLine != endLine
					|| (s.x == line.getOffset() && s.y == line.getLength());

		} catch (BadLocationException x) {
			return false;
		}
	}

}
