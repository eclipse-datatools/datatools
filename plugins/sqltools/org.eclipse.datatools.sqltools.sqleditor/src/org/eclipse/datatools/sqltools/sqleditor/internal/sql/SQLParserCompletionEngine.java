/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.editor.contentassist.ISQLDBProposalsService;
import org.eclipse.datatools.sqltools.editor.contentassist.SQLDBProposalsRequest;
import org.eclipse.datatools.sqltools.editor.template.TemplateConstant;
import org.eclipse.datatools.sqltools.sql.ISQLSyntax;
import org.eclipse.datatools.sqltools.sql.parser.ParserParameters;
import org.eclipse.datatools.sqltools.sql.parser.ParserProposalAdvisor;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParam;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.datatools.sqltools.sql.util.SQLUtil;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.datatools.sqltools.sqleditor.internal.templates.SQLIntelligentTemplate;
import org.eclipse.datatools.sqltools.sqleditor.internal.templates.SQLTemplateProposal;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.SQLDBUtils;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.SQLWordFinder;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IEditorPart;

/**
 * Computes proposals by <code>SQLParser</code>
 * 
 * @author Hui Cao
 * 
 */
public class SQLParserCompletionEngine implements ISQLCompletionEngine {

	private String _fFullText;

	/**
	 * 
	 */
	private String _fStartText;

	/**
	 * The offset of the current word, including the preceding white space if
	 * any. Starting from zero.
	 */
	private int _fWordOffset;

	/**
	 * The offset of the position where content assist is invoked.
	 */
	private int _fDocumentOffset;

	/**
	 * The last word before the _fDocumentOffset, including "." if any.
	 */
	private String _fWord;

	private int _fStartOffset;

	private SQLEditor _editor;
	/**
	 * Set content assist scope.
	 */
	private Point _selection;

	private SQLDevToolsConfiguration _config;

	private DatabaseVendorDefinitionId _databaseVendorDefinitionId;

	private ResultCollector resultCollector = null;

	private SQLCompletionProposalFactory fProposalFactory;

	private ISQLDBProposalsService fDBProposalsService;

	public SQLParserCompletionEngine() {
		fProposalFactory = new SQLCompletionProposalFactory();
		fDBProposalsService = null;
	}

	public ICompletionProposal[] computeProposals(IDocument doc,
			ITypedRegion partition, int documentOffset, Point selection) {
		// init
		resultCollector = new ResultCollector();

		IEditorPart part = SQLEditorPlugin.getActiveEditor();
		if (part != null)
		{
			_editor = (SQLEditor) part.getAdapter(SQLEditor.class);
		}
		if (_editor == null)
		{
			return null;
		}
		_fDocumentOffset = documentOffset;
		if (!needsContentAssist()) {
			return null;
		}
		_databaseVendorDefinitionId = _editor.getConnectionInfo()
				.getDatabaseVendorDefinitionId();
		_config = SQLToolsFacade
				.getConfigurationByVendorIdentifier(_databaseVendorDefinitionId);
		SQLParser _parser = _config.getSQLService().getSQLParser();
		
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
			int expStart = 0;
			if (_parser != null) {
				findStatementStart(text, _fDocumentOffset - 1,
				_parser.getStatementStartTokens(), _parser
						.getStatementTerminators());
			}
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

		if (_parser == null) {
			return getTemplateProposalsAtLineStart();
		}
		
		String parseText = null;

		if (_fFullText.equals("")) //$NON-NLS-1$
		{
			parseText = _fFullText;
		} else {
			parseText = _fFullText.substring(_fStartOffset, _fWordOffset + 1);
		}

        boolean useDelimiter = _editor.getSQLType() == SQLParserConstants.TYPE_SQL_ROOT;
        ParserParameters pp = new ParserParameters(useDelimiter, _editor.getSQLType());
		prepareParserParameter(pp);
		ParsingResult result = _parser.parse(parseText
				+ SQLParser.SPECIAL_TOKEN, pp);
		result.getRootNode().setDocument(doc);

		ParserProposalAdvisor parserAdvisor = _config.getSQLService()
				.getParserProposalAdvisor();
		return getProposals(result, parserAdvisor);

	}

	protected void prepareParserParameter(ParserParameters pp) {
		pp.setProperty(ParserParameters.PARAM_PROFILE_NAME, _editor
				.getConnectionInfo().getConnectionProfileName());
		pp.setProperty(ParserParameters.PARAM_DB_NAME, _editor
				.getConnectionInfo().getDatabaseName());
		pp.setProperty(ParserParameters.PARAM_CONSUME_EXCEPTION, Boolean.FALSE);
	}

	/**
	 * 
	 * @param result
	 * @param advisor
	 * @return
	 */
	private ICompletionProposal[] getProposals(ParsingResult result,
			ParserProposalAdvisor advisor) {
		String defaultSchemaName = _config.getDBHelper().getDefaultSchemaName(_editor.getConnectionInfo().getConnectionProfile());
		
		String[] parserProposals = advisor.getParserProposals(result);

		int replacementOffset = (_fWordOffset >= 0 ? _fWordOffset + 1 : 0);
		int replacementLength = (_fDocumentOffset > _fWordOffset ? _fDocumentOffset
				- (_fWordOffset + 1) + _selection.y
				: _fDocumentOffset);
		for (int i = 0; i < parserProposals.length; i++) {

			String expected = parserProposals[i];

			// local variables
			if (advisor.isLocalVariableTokenDefinition(expected)) //$NON-NLS-1$
			{
				String localVariablePrefix = advisor.getLocalVariablePrefix();
				if (_fWord.startsWith(localVariablePrefix)
						&& !_fWord
								.startsWith(advisor.getGlobalVariablePrefix())) //$NON-NLS-1$ //$NON-NLS-2$
				{
					if (SQLParser.SCOPE_DEFINE_VARIABLES != result.getScope()) {
						resultCollector.setVariableList(new ArrayList(Arrays
								.asList(createVarProposals(result.getEntries(SQLParserConstants.PARAMETERS), replacementOffset, replacementLength))));
						break;
					}
				}
				// we don't want @ to show up everywhere identifier is needed
				else if (_fWord.equals("")) //$NON-NLS-1$
				{
					resultCollector
							.addVariable(new SQLCompletionProposal(
									localVariablePrefix, _fDocumentOffset,
									_selection.y,
									1,
									null,
									localVariablePrefix,
									null, //$NON-NLS-1$ //$NON-NLS-2$
									null,
									SQLCompletionProposal.VARIABLE,
									_editor
											.getAction(ISQLEditorActionConstants.CONTENT_ASSIST_ACTION_ID)));
					continue;
				}
			}

			// global variables
			if (advisor.isGlobalVariableTokenDefinition(expected)) //$NON-NLS-1$
			{
				String globalVariablePrefix = advisor.getGlobalVariablePrefix();
				if (_fWord.startsWith(globalVariablePrefix)) //$NON-NLS-1$
				{
					ISQLSyntax syntax = _config.getSQLService().getSQLSyntax();
					resultCollector.setVariableList(new ArrayList(Arrays
							.asList(createVarProposals(syntax
									.getGlobalVariables(), replacementOffset, replacementLength))));
					break;
				} else if (_fWord.equals("")) //$NON-NLS-1$
				{
					resultCollector
							.addVariable(new SQLCompletionProposal(
									globalVariablePrefix,
									_fDocumentOffset,
									_selection.y,
									2,
									null,
									globalVariablePrefix, //$NON-NLS-1$ //$NON-NLS-2$
									null,
									null,
									SQLCompletionProposal.VARIABLE,
									_editor
											.getAction(ISQLEditorActionConstants.CONTENT_ASSIST_ACTION_ID)));
					continue;
				}
			}

			// db proposals
			if (advisor.isIdentifierTokenDefinition(expected)) //$NON-NLS-1$
			{
				if (_editor.getConnectionInfo().getSharedConnection() != null) {
					SQLDBProposalsRequest request = new SQLDBProposalsRequest(
							_fWord, result.getScope(),
							defaultSchemaName,
							result);
					/*
					 * BZ 387818: The wrong proposals service is used if editors for multiple vendors
					 * are open at the same time. The proposals service of the last vendor's editor
					 * to be created is used for all editor.
					 * 
					 * This fix uses the proposals service associated with the current editor if
					 * it is available. Otherwise, the proposals service contained in the proposal
					 * factory is used (as was always being done before).
					 */
					List proposalList;
					ISQLDBProposalsService editorProposalService = _editor.getDBProposalsService();
					if (editorProposalService != null) {
						proposalList = new ArrayList(); 
			            if (editorProposalService.populate( request )) {
			            	proposalList.addAll(editorProposalService.getDBProposals());                
			            }           
					} else {
						proposalList = fProposalFactory
								.getDBObjectProposals(request);
					}
					resultCollector
							.setDBProposalList(adaptDBProposals(proposalList, request.getScope()),
									request.getScope());
				}

			}

			if (advisor.isTokenDefinition(expected)) //$NON-NLS-1$ //$NON-NLS-2$
			{
				continue; // lets not print the token names. doesn't help
							// user.
			}

			// operator
			if (!(Character.isLetter(expected.charAt(0)))) {
				if (startsWithIgnoreCase(expected, _fWord)) {
					resultCollector.addOperator(new SQLCompletionProposal(
							expected,

							replacementOffset, replacementLength, expected
									.length(), null, expected, null, null,
							SQLCompletionProposal.OPERATOR));
				}
				continue;
			}

			// keywords
			if (_fWord.equals("")) //$NON-NLS-1$
			{
				resultCollector.addReservedKeyword(new SQLCompletionProposal(
						expected, _fDocumentOffset, _fWord.length()
								+ _selection.y, expected.length(), SQLEditorResources.getImage("keyword"),
						expected, null, null, SQLCompletionProposal.KEYWORD));
			} else if (startsWithIgnoreCase(expected, _fWord)) {
				resultCollector.addReservedKeyword(new SQLCompletionProposal(
						expected, replacementOffset, replacementLength,
						expected.length(), SQLEditorResources.getImage("keyword"), expected, null, null,
						SQLCompletionProposal.KEYWORD));
				continue;
			}

		}

		// Add unreserved keyword
		Set unservedKeywords = new HashSet(result
				.getExpectedUnreservedKeywords());
		boolean containsDT = advisor.containsDataTypeProposals(parserProposals,
				unservedKeywords);
		if (containsDT) {
			Database database = _editor.getConnectionInfo().getDatabase();
			
			if (database != null && defaultSchemaName != null) {
			    EList schemas = ModelUtil.getSchemas(database, _editor.getConnectionInfo().getDatabaseName());
				for (Iterator iter = schemas.iterator(); iter.hasNext();) {
					Schema schema = (Schema) iter.next();
					if (schema.getName().equals(defaultSchemaName)) {
						EList udts = schema.getUserDefinedTypes();
						for (Iterator iterator = udts.iterator(); iterator
								.hasNext();) {
							UserDefinedType udt = (UserDefinedType) iterator
									.next();
							if (advisor.acceptsUserDefinedDataType(udt)) {
								unservedKeywords.add(udt.getName());
							}
						}

					}
				}
			}
		}
		for (Iterator iter = unservedKeywords.iterator(); iter.hasNext();) {
			String sqlWordName = (String) iter.next();
			if (startsWithIgnoreCase(sqlWordName, _fWord)) {
				resultCollector
						.addUnreservedKeywordList(new SQLCompletionProposal(
								sqlWordName, replacementOffset,
								replacementLength, sqlWordName.length(), SQLEditorResources.getImage("unreservedkeyword"),
								sqlWordName, null, null,
								SQLCompletionProposal.UNRESERVEDKEYWORD));
			}

		}

		ICompletionProposal[] results = resultCollector.getResults();
		
        // Now insert templates
        //holds the typical initial commands
        List cmds = Arrays.asList(new Object[]
        {
            "select", "insert", "create"
        }
        ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        int cmdSize = 0;
        for (int i = 0; i < results.length; i++)
        {
            if (cmds.contains(((ICompletionProposal) results[i]).getDisplayString().toLowerCase()))
            {
                cmdSize++;
            }
        }
        boolean isStatementStart = cmdSize == cmds.size();
        ArrayList templateList = new SQLTemplateProposalsService().getProposals(_editor, _fDocumentOffset, _fWord, isStatementStart, _selection);
        ICompletionProposal[] templateResults = (ICompletionProposal[]) templateList
            .toArray(new SQLTemplateProposal[templateList.size()]);

        // concatenate arrays
        if (templateResults != null)
        {
            ICompletionProposal[] total = new ICompletionProposal[results.length + templateResults.length];
            System.arraycopy(templateResults, 0, total, 0, templateResults.length);
            System.arraycopy(results, 0, total, templateResults.length, results.length);
            results = total;
        }
		

		return results;
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

	public static boolean startsWithIgnoreCase(String string, String prefix) {
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

	/**
	 * This method is used to judge if the current content type allows content
	 * assist. Currently the content types which don't allow content assist
	 * have: <br>
	 * <code>ISQLPartitions.SQL_COMMENT</code><br>
	 * <code> ISQLPartitions.SQL_MULTILINE_COMMENT</code>
	 * 
	 * @return true if the content type allows content assist.
	 */
	protected boolean needsContentAssist() {
		String contentType = null;
		try {
			contentType = ((IDocumentExtension3) _editor.getSV().getDocument())
					.getContentType(SQLPartitionScanner.SQL_PARTITIONING,
							_fDocumentOffset, true);
		} catch (BadLocationException e) {
			// _log.error(EditorMessages.error_badLocationException, e);
		} catch (BadPartitioningException e) {
			// _log.error(EditorMessages.error_BadPartitioningException, e);
		}

		if (contentType.equals(SQLPartitionScanner.SQL_COMMENT)
				|| contentType
						.equals(SQLPartitionScanner.SQL_MULTILINE_COMMENT)
				|| contentType
						.equals(SQLPartitionScanner.SQL_DOUBLE_QUOTES_IDENTIFIER)
				|| contentType.equals(SQLPartitionScanner.SQL_STRING)) {
			return false;
		}
		return true;
	}

	protected ICompletionProposal[] createVarProposals(String[] elements, int replacementOffset, int replacementLength) {
		if (elements == null) {
			return null;
		}

		List result = new ArrayList();
		for (int i = 0; i < elements.length; i++) {
			int typeIndex = elements[i].indexOf(" - ");
			String replaceString = elements[i];
			if (typeIndex > 0) {
				replaceString = elements[i].substring(0, typeIndex);
			}
			if (startsWithIgnoreCase(replaceString, _fWord)) {
				result.add(new SQLCompletionProposal(replaceString,
						replacementOffset,
						replacementLength, replaceString.length(),
						null, elements[i], null, null,
						SQLCompletionProposal.VARIABLE));
			}

		}

		return (ICompletionProposal[]) result
				.toArray(new ICompletionProposal[result.size()]);
	}

	protected ICompletionProposal[] createVarProposals(HashMap elements, int replacementOffset, int replacementLength) {
		if (elements == null) {
			return null;
		}

		List result = new ArrayList();
		for (Iterator iter = elements.values().iterator(); iter.hasNext();) {
			IASTSQLParam element = (IASTSQLParam) iter.next();

			if (startsWithIgnoreCase(element.getName(), _fWord)) {
				result.add(new SQLCompletionProposal(element.getName(),
						replacementOffset,
						replacementLength,
						element.getName().length(), null, element.getName() + " - " + element.getType(),
						null, null, SQLCompletionProposal.VARIABLE));
			}
		}

		return (ICompletionProposal[]) result
				.toArray(new ICompletionProposal[result.size()]);
	}

	/**
	 * Converts SQLDBProposal db object type to SQLCompletionProposal
	 * 
	 * @param dbObjectType
	 * @return
	 */
	private int getRelevance(int dbObjectType) {
		switch (dbObjectType) {
		case SQLDBProposal.SCHEMA_OBJTYPE:
			return SQLCompletionProposal.OTHER;
		case SQLDBProposal.TABLE_OBJTYPE:
		case SQLDBProposal.TABLEALIAS_OBJTYPE:
			return SQLCompletionProposal.TABLE;
		case SQLDBProposal.TABLECOLUMN_OBJTYPE:
			return SQLCompletionProposal.COLUMN;
		case SQLDBProposal.CATALOG_OBJTYPE:
			return SQLCompletionProposal.DATABASE;
		case SQLDBProposal.FUNCTION_OBJTYPE:
			return SQLCompletionProposal.FUNCTION;
		case SQLDBProposal.STORED_PROCEDURE_OBJTYPE:
			return SQLCompletionProposal.STORED_PROCEDURE;
		case SQLDBProposal.TRIGGER_OBJTYPE:
			return SQLCompletionProposal.TRIGGER;
		case SQLDBProposal.EVENT_OBJTYPE:
			return SQLCompletionProposal.EVENT;
		default:
			return SQLCompletionProposal.OTHER;
		}

	}

	/**
	 * Sets the <code>DBProposalsService</code> to use.
	 * 
	 * @param dbProposalsService
	 *            the <code>DBProposalsService</code> to use
	 */
	public void setDBProposalsService(ISQLDBProposalsService dbProposalsService) {
		fDBProposalsService = dbProposalsService;
		fProposalFactory.setFactoryDBContext(dbProposalsService);
	}

	/**
	 * Gets the current <code>DBProposalsService</code>.
	 * 
	 * @param dbProposalsService
	 *            the current <code>DBProposalsService</code>.
	 */
	public ISQLDBProposalsService getDBProposalsService() {
		return fDBProposalsService;
	}

	/**
	 * Computes the replacement location and content and converts SQLDBProposals
	 * into SQLCompletionProposals
	 * 
	 * @param proposalList
	 *            list of SQLDBProposals
	 * @return list of SQLCompletionProposals
	 */
	protected ArrayList adaptDBProposals(List proposalList, int scope) {
		if (proposalList == null) {
			return new ArrayList();
		}

		boolean notShowTable = false;
		if ((scope & SQLParser.SCOPE_WITHOUT_TABLE) == SQLParser.SCOPE_WITHOUT_TABLE)
		{
			notShowTable = true;
		}
		ArrayList result = new ArrayList();
		String[] tokens = SQLUtil.splitDotStr(_fWord);
		int indexInWord = _fWord.lastIndexOf('.') + 1;
		int length = tokens.length;
        String objectName = null;
        String ownerName = null;
        String dbName = null;
        switch (length)
        {
            case 1:
                objectName = tokens[0];
                break;
            case 2:
                ownerName = tokens[0];
                break;
            case 3:
                dbName = tokens[0];
                ownerName = tokens[1];
                objectName = tokens[2];
        }

		String checkword = tokens[length - 1]; // use the last item in the
												// prefix
		// in the case of "APP." checkword is null. We convert it into empty
		// string to prevent NPE
		if (checkword == null) {
			checkword = "";
		}

		for (int i = 0; i < proposalList.size(); i++) {
			SQLDBProposal proposal = (SQLDBProposal) proposalList.get(i);
			if (proposal == null || proposal.getName() == null) {
				continue;
			}
			String name = proposal.getName();
			//The fully qualified name for the proposal, with the same levels of _fWord
			StringBuffer fullName = new StringBuffer(name);
			
			if (length >= 2 && proposal.getParentAlias() != null)
			{
				fullName = fullName.insert(0, '.').insert(0, proposal.getParentAlias());
			}
			if (length >= 3 && proposal.getGrandParentName() != null )
			{
				fullName = fullName.insert(0, '.').insert(0, proposal.getGrandParentName());
			}
			if (length >= 4 && proposal.getGrandGrandParentName() != null )
			{
				fullName = fullName.insert(0, '.').insert(0, proposal.getGrandGrandParentName());
			}
			
			//TODO consider default schema: master..table.column
			
			// Get the image to display for this proposal according to scope
			Image image = proposal.getImage();

			// displayString: owner.name
			StringBuffer display = new StringBuffer();

			SQLObject parentObject = (SQLObject)proposal.getParentObject();
			boolean displayParent = !notShowTable && needsDisplayOwner(proposal, length);
            
			displayParent = displayParent && proposal.getDBObject() != null;
			if (displayParent && proposal.getParentAlias() != null) {
				display.append(proposal.getParentAlias());
				display.append('.');
			}
			display.append(proposal.getName());
			//always uses the longer name except column
			String replace = display.length() > fullName.length() ? display.toString() : fullName.toString();
			if (proposal.getType() == SQLDBProposal.TABLECOLUMN_OBJTYPE)
			{
                // reduce 'datatype' behind the column name
			    replace = replace.substring(0, replace.indexOf(" - ")); //$NON-NLS-1$
            }
			if (length != 3)
            {
                display.insert(0, ' ');
            }
			
			int relevance = getRelevance(proposal.getType());
			int replaceOffset = _fWordOffset + 1;
			// match the whole string
			if (startsWithIgnoreCase(replace.toString(), _fWord)) {
				result.add(new SQLCompletionProposal(replace,
						replaceOffset, _fWord.length() + _selection.y, replace
								.length(), image,
						display.toString(), null, null, relevance));
			}
            else if (objectName != null && startsWithIgnoreCase(proposal.getName(), _fWord)){
                result.add(new SQLCompletionProposal(replace, replaceOffset, _fWord.length() + _selection.y, replace
                        .length(), image, display.toString(), null, null, relevance));
            }
            else if (length == 3 && startsWithIgnoreCase(name, objectName))
            {
                if (ownerName == null)
                {
                    replace = dbName + ".." + display.toString();
                }
                else if (proposal.getDBObject() instanceof Table)
                {
                    replace = ((Table) proposal.getDBObject()).getSchema().getName() + "." + display.toString();
                }
                result.add(new SQLCompletionProposal(replace, replaceOffset, _fWord.length() +_selection.y, replace.length(), image, display.toString(), null, null, relevance));
            }
//			// match only the last part, assuming the propoal must have been
//			// filtered
//			else if (startsWithIgnoreCase(name, checkword)) {
//				replace = new StringBuffer(name);
//				replaceOffset = replaceOffset + indexInWord;
//				result.add(new SQLCompletionProposal(replace.toString(),
//						replaceOffset, checkword.length() + _selection.y, name
//								.length(), image, display.toString(), null,
//						null, relevance));
//			}

		}

		return result;
	}

    
	public boolean needsDisplayOwner(SQLDBProposal proposal, int length) {
		if (proposal.getDBObject() instanceof Table
				|| proposal.getDBObject() instanceof ViewTable || proposal.getDBObject() instanceof Column) {

			boolean isShow = SQLEditorPlugin.getDefault().getPreferenceStore()
					.getBoolean(PreferenceConstants.SHOW_OWNER_OF_TABLE);
			if (!isShow) {
				return false;
			}
			// if (proposal.getParentAlias() != null &&
			// proposal.getParentAlias().equals(proposal.getParentName()))
			// {
			// return false;
			// }
			else if (length == 3) {
				return false;
			}
		} else if (proposal.getDBObject() instanceof Event
				|| proposal.getDBObject() instanceof Database
                || proposal.getDBObject() instanceof Catalog) {
			return false;
		}
		return true;
	}

    /**
     * Return template proposal.
     * TemplateProposal will be returned if the text from the offset of the first character in the current line to the current offset only 
     * includes spaces. 
     * This is a temporary solution. 
     *
     * @author Li Huang
     * 
     * @return ICompletionProposal[]
     */
    protected ICompletionProposal[] getTemplateProposalsAtLineStart()
    {
        //the number of the line
        int lineNumber = 0;
        //the offset of the first character of the given line
        int lineOffset = 0;
        IDocument document = _editor.getSV().getDocument();

        try
        {
            lineNumber = document.getLineOfOffset(_fDocumentOffset);
            lineOffset = document.getLineOffset(lineNumber);
            String text = document.get(lineOffset, (_fDocumentOffset - lineOffset));
            if (text != null && !text.trim().equals(""))
            {
                return null;
            }
        }
        catch (BadLocationException e)
        {
//            _log.error(EditorMessages.plugin_debug, e); //$NON-NLS-1$
        }

        ArrayList templateList = new SQLTemplateProposalsService().getProposals(_editor, _fDocumentOffset, _fWord, true, _selection);
        ICompletionProposal[] templateResults = (ICompletionProposal[]) templateList
            .toArray(new SQLTemplateProposal[templateList.size()]);

        return templateResults;
    }

    
    public IContextInformation[] computeContextInformation(IDocument doc, ITypedRegion partition, int documentOffset,
            Point selection)
    {
        IContextInformation[] contextInformations = null;
        
        contextInformations = computeTemplateContextInformation(doc, partition, documentOffset, selection);
        
        return contextInformations;
    }

    
    private IContextInformation[] computeTemplateContextInformation(IDocument doc, ITypedRegion partition, int documentOffset,
            Point selection)
    {
        String tempalteId = SQLEditorPlugin.getDefault().getPreferenceStore().getString(
                TemplateConstant.INTELLIGENT_TEMPLATE);
        if (tempalteId == null || tempalteId.trim().equals(""))
        {
            return null;
        }
        
        String contextInfo = null;

        SQLIntelligentTemplate template = SQLEditorPlugin.getDefault().getTemplateStore()
                .getRegisteredIntelligentTemplate(tempalteId);
        
        if (template == null)
        {
            return null;
        }
        
        String word = findWord(doc.get(), documentOffset);
        contextInfo = template.getContextInformation(word);
        
        if (contextInfo != null)
        {
            return new ContextInformation[]{new ContextInformation(contextInfo, contextInfo)};
        }
        else
        {
            return null;
        }
    }
    /**
     * Find the qualified object identifier.
     * @param text
     * @param offset
     * @return
     */
    private String findWord(String text, int offset)
    {
        // find word from variable's offset until white space is encountered.
        int start = offset;
        int end = offset;

        for (int i = offset -1 ; i > 0; i--)
        {
            if (!isValidChar(text.charAt(i)))
            {
                break;
            }
            start--;
        }
        
        for (int i = offset ; i < text.length(); i++)
        {
            if (!isValidChar(text.charAt(i)))
            {
                break;
            }
            end++;
        }

        return text.substring(start, end);
    }
    
    private boolean isValidChar(char character)
    {
        if (character == ' ' || character == '\n' || character == '\t' || character == '\r' || character == '(' || character == ',' || character == ')')
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
