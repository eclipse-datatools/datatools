/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.datatools.sqltools.core.IDBFactory;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.sql.parser.ParseException;
import org.eclipse.datatools.sqltools.sql.parser.ParserParameters;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.SQLWord;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.SQLWordFinder;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.editors.text.ILocationProvider;
import org.eclipse.ui.internal.Workbench;

/**
 * Computes proposals by <code>SQLParser</code>
 * 
 * @author Hui Cao
 * 
 */
public class SQLParserCompletionEngine implements ISQLCompletionEngine {

	private SQLParser _parser;

	private String _fFullText;

	private String _fStartText;

	private int _fWordOffset;

	private int _fDocumentOffset;

	private String _fWord;

	private int _fStartOffset;

	private SQLEditor _editor;

	private Comparator _comparator;

	/**
	 * Set content assist scope.
	 */
	private Point _selection;

	public SQLParserCompletionEngine() {
	}

	public ICompletionProposal[] computeProposals(IDocument doc,
			ITypedRegion partition, int documentOffset, Point selection) {
		_editor = (SQLEditor) Workbench.getInstance()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		IDBFactory factory = SQLToolsFacade
				.getDBFactoryByVendorIdentifier(_editor.getConnectionInfo()
						.getDatabaseVendorDefinitionId());
		_parser = factory.getSQLService().getSQLParser();
		if (_parser == null) {
			return null;
		}
		_fDocumentOffset = documentOffset;
		_selection = selection;
		String text = doc.get();
		if (text.trim().length() == 0) {
			_fStartText = text.trim();
			_fFullText = text.trim();
			_fWordOffset = 0;
			_fStartOffset = 0;
			_fWord = text.trim();
		} else {

			int wordOffset = SQLWordFinder.getWordStartOffset(text,
					documentOffset - 1);
			int expStart = findStatementStart(text, _fDocumentOffset - 1,
					_parser.getStatementStartTokens(), _parser
							.getStatementTerminators());
			String uptoCurrentCursorText = text.substring(0, documentOffset);
			String sqlCmdStartToCurrentCursorText = text.substring(expStart,
					documentOffset);

			_fStartText = sqlCmdStartToCurrentCursorText;
			_fFullText = uptoCurrentCursorText;
			_fWordOffset = wordOffset;
			_fStartOffset = expStart;
			_fWord = _fStartText.substring(_fWordOffset + 1 - _fStartOffset,
					_fDocumentOffset - _fStartOffset);

		}

		String parseText = null;

		if (_fFullText.equals("")) //$NON-NLS-1$
		{
			parseText = _fFullText;
		} else {
			parseText = _fFullText.substring(_fStartOffset, _fWordOffset + 1);
		}

		ParserParameters pp = new ParserParameters(
				_editor.getEditorInput() instanceof IFileEditorInput
						|| _editor.getEditorInput() instanceof ILocationProvider);
		pp.setProperty(ParserParameters.PARAM_PROFILE_NAME, _editor
				.getConnectionInfo().getConnectionProfileName());
		pp.setProperty(ParserParameters.PARAM_DB_NAME, _editor
				.getConnectionInfo().getDatabaseName());
		pp.setProperty(ParserParameters.PARAM_CONSUME_EXCEPTION, Boolean.FALSE);
		ParsingResult result = _parser.parse(parseText
				+ SQLParser.SPECIAL_TOKEN, pp);
		if (result.getExceptions() == null
				|| result.getExceptions().size() == 0) {
			return null;
		} else {
			ParseException pe = (ParseException) result.getExceptions().get(
					result.getExceptions().size() - 1);
			return getProposals(pe.tokenImage, pe.expectedTokenSequences,
					_parser);

		}
	}

	/**
	 * 
	 * @param context
	 * @param defaultTable
	 *            used when get column information, might contains dots
	 * @return
	 */

	private ICompletionProposal[] getProposals(String[] tokenImage,
			int[][] expectedTokenSequences, SQLParser parser) {
		List proposals = new ArrayList();

		int maxLookAhead = 0;
		for (int i = 0; i < expectedTokenSequences.length; i++) {
			int n = expectedTokenSequences[i].length - 1;
			if (n > maxLookAhead) {
				maxLookAhead = n;
			}
		}

		int replacementOffset = (_fWordOffset >= 0 ? _fWordOffset + 1 : 0);
		int replacementLength = (_fDocumentOffset > _fWordOffset ? _fDocumentOffset
				- (_fWordOffset + 1) + _selection.y
				: _fDocumentOffset);
		for (int i = 0; i < expectedTokenSequences.length; i++) {
			int maxLookIndex = expectedTokenSequences[i].length - 1;
			if (maxLookIndex < maxLookAhead) {
				continue;
			}
			int idex = expectedTokenSequences[i][maxLookIndex];

			String expected = removeQuotes(tokenImage[idex]);

			// operator
			if (!(Character.isLetter(expected.charAt(0)))) {
				if (startsWithIgnoreCase(expected, _fWord)) {
					proposals.add(new SQLCompletionProposal(expected,

					replacementOffset, replacementLength, expected.length(),
							null, expected, null, null,
							SQLCompletionProposal.OPERATOR));
				}
				continue;
			}

			// keywords
			if (_fWord.equals("")) //$NON-NLS-1$
			{
				proposals.add(new SQLCompletionProposal(expected,
						_fDocumentOffset, _fWord.length() + _selection.y,
						expected.length(), null, expected, null, null,
						SQLCompletionProposal.KEYWORD));
			} else if (startsWithIgnoreCase(expected, _fWord)) {
				proposals.add(new SQLCompletionProposal(expected,
						replacementOffset, replacementLength,
						expected.length(), null, expected, null, null,
						SQLCompletionProposal.KEYWORD));
				continue;
			}

		}

		// Add unserved keyword
		List unservedKeywordsList = _parser.getExpectedUnreservedKeywords();
		// unservedKeywordsList =
		// SqleditorUtil.RemoveDuplicateElement(unservedKeywordsList);
		// unservedKeywordsList =
		// _parser.getUserDefinedDatatypes(unservedKeywordsList);
		for (int i = 0; i < unservedKeywordsList.size(); i++) {
			String sqlWordName = (String) unservedKeywordsList.get(i);
			if (startsWithIgnoreCase(sqlWordName, _fWord)) {
				proposals.add(new SQLCompletionProposal(sqlWordName,
						replacementOffset, replacementLength, sqlWordName
								.length(), null, sqlWordName, null, null,
						SQLCompletionProposal.KEYWORD));
			}
		}

		return order((ICompletionProposal[]) proposals
				.toArray(new ICompletionProposal[0]));
	}

	/**
	 * Finds the start offset of the current statement.
	 * 
	 * @param text
	 * @param offset
	 * @param startTokens
	 * @param terminators
	 * @return
	 */
	public static int findStatementStart(String text, int offset,
			String[] startTokens, String[] terminators) {
		// TODO utilize startTokens, notice statements containing multiple
		// keywords which can start a statement such as: "insert into table1
		// select * from table2"
		if (terminators == null || terminators.length == 0) {
			return 0;
		}
		for (int i = offset, lastWhitespace = i; i >= 0; i--) {
			char c = text.charAt(i);
			if (Character.isWhitespace(c)) {
				lastWhitespace = i;
			}

			String check = text.substring(i, lastWhitespace);
			if (check.length() == 0) {
				continue;
			}
			for (int k = 0; k < terminators.length; k++) {
				if (check.equalsIgnoreCase(terminators[k])) {
					return i;
				}
				if (terminators[k].length() == 1
						&& !Character.isLetter(terminators[k].charAt(0))
						&& check.endsWith(terminators[k])) {
					return i;
				}
			}
		}
		return 0;
	}

	private static String removeQuotes(String string) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (c != '"' && c != '\\') {
				sb.append(c);
			} else if (c == '\\' && string.charAt(i + 1) == '"') {
				sb.append('"');
				i = i + 1;
			}

		}
		return sb.toString();
	}

	/**
	 * Order the given proposals.
	 */
	protected ICompletionProposal[] order(ICompletionProposal[] proposals) {
		if (_comparator != null) {
			Arrays.sort(proposals, _comparator);
		}
		return proposals;
	}

	private static boolean startsWithIgnoreCase(String string, String prefix) {
		if (prefix == null) {
			return true;
		}
		int i = 0;
		int n = prefix.length();
		int m = string.length();

		for (; i < n && i < m; i++) {
			if (Character.toLowerCase(string.charAt(i)) != Character
					.toLowerCase(prefix.charAt(i))) {
				break;
			}
		}
		return i == n;
	}

}
