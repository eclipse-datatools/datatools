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
package org.eclipse.datatools.sqltools.editor.contentassist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.datatools.sqltools.sql.reference.DBObject;
import org.eclipse.datatools.sqltools.sql.reference.ITable;
import org.eclipse.datatools.sqltools.sql.util.SQLUtil;

/**
 * Encapsulates all the information used to retrieve database meta data
 * information for content assist.
 * 
 * @author Hui Cao
 * 
 */
public class SQLDBProposalsRequest {

	private String _prefix;

	private int _scope;

	private ParsingResult _result;

	private String _database;

	private String _schema;

	private String _defaultSchema;

	private String _table;

	private HashMap _refTables;

	public SQLDBProposalsRequest(String tokens, int scope,
			String defaultSchema, ParsingResult result) {
		super();
		this._prefix = tokens;
		this._scope = scope;
		this._result = result;
		this._defaultSchema = defaultSchema;
		init();
	}

	private void init() {

		DBObject[] objects = null;
		// TODO: extract from prefix.

		String[] tokens = SQLUtil.splitDotStr(_prefix);
		int count = tokens.length;
		int i = 0;
		// ("tokens: " + count);
		switch (count) {
		case 4: // "database..tab.col" or "database.owner.tab.col"
			_database = tokens[0];
			_schema = tokens[1];
			_table = tokens[2];
			break;
		case 3: // "database..", "database.schema.tab", or "schema.tab.col"; we
			// read last 2 as one.
			if (tokens[1] == null ) {
				_database = tokens[0];
                _table = tokens[2];
			} else if (_scope == SQLParser.SCOPE_COLUMNS) {
				_schema = tokens[0];
				_table = tokens[1];
			} else if (_scope == SQLParser.SCOPE_TABLES) {
				_database = tokens[0];
				_schema = tokens[1];
				_table = tokens[2];
			}
			break;
		case 2: // either table.col or schema.obj or database.schema
			// check with parser on the current scope
			if (_scope == SQLParser.SCOPE_COLUMNS) // table.col
			{
				// schema = tokens[0];
				_table = tokens[0];
			} 
            else if (_scope == SQLParser.SCOPE_CATALOGS) // database.schema
			{
				_database = tokens[0];
				_schema = tokens[1];
			} 
            else 
            {
				_schema = tokens[0];
			}
			break;
		case 1:
			// do full query for the current context without any qualifier in
			// where clause.
			break;
		}
		_scope = determineProposalType();
	}

	public String getDatabase() {
		return _database;
	}

	public String getSchema() {
		if (_schema == null) {
			return _defaultSchema;
		}
		return _schema;
	}

	/**
	 * Returns the table name prefix, may be partial
	 * 
	 * @return
	 */
	public String getTable() {
		return _table;
	}

	/**
	 * In case getTable() returns the alias name, this method tries to find the
	 * real table name. May equal to getTable()
	 * 
	 * @return
	 */
	public String getRealTable() {
		if (_table == null) {
			return null;
		}
		List ctx = getContextTables();
		for (Iterator iter = ctx.iterator(); iter.hasNext();) {
			ITable table = (ITable) iter.next();
			if (_table.equals(table.getAliasName())) {
				return table.getName();
			}
		}
		return _table;
	}

	protected int determineProposalType() {
		int proposalType = getScope();
		// try to determine a scope
		if (getSchema() == null) {
			proposalType = SQLParserConstants.SCOPE_SCHEMAS;
		} else {
			    if (proposalType == SQLParserConstants.SCOPE_COLUMNS) {
				if (getTable() == null && getContextTables().isEmpty()) {
					proposalType = SQLParserConstants.SCOPE_TABLES;
				}
				else
				{
					//double scope
					proposalType = proposalType | SQLParserConstants.SCOPE_TABLES;
				}
			}
		}
		return proposalType;
	}

	public String getPrefix() {
		return _prefix;
	}

	/**
	 * Returns the proper scope. Note: this might be different with the original
	 * input scope.
	 * 
	 * @return
	 */
	public int getScope() {
		return _scope;
	}

	/**
	 * Returns the referenced
	 * <code>org.eclipse.datatools.sqltools.sql.reference.ITable</code>s.
	 * 
	 * @return
	 */
	public List getContextTables() {

		if (_result != null) {
			return _result.getCurrentTables();
		}
		return new ArrayList();
	}

}
