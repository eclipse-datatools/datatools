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
package org.eclipse.datatools.sqltools.db.derby.sql;

import org.eclipse.datatools.sqltools.db.generic.sql.GenericSQLSyntax;

/**
 * 
 * @author Hui Cao
 * 
 */
public class DerbySQLSyntax extends GenericSQLSyntax {

	private static final String[] _unreservedwords = { "after", "before",
			"each", "referencing", "row", "statement", "mode", "db2sql", "old",
			"of", "new", "old_table", "new_table", "trigger", };

	private static final String[] reservedwords = { "action", "add", "all",
			"alter", "and", "any", "as", "asc", "authorization", "begin",
			"between", "boolean", "by", "cascade", "cascaded", "char",
			"character", "check", "collate", "column", "commit", "constant",
			"constraint", "corresponding", "create", "cross", "cursor", "date",
			"default", "declare", "delete", "deferrable", "desc", "distinct",
			"drop", "end", "escape", "except", "exists", "false", "foreign",
			"from", "full", "global", "group", "having", "immediate", "in",
			"index", "initially", "inner", "insert", "intersect", "into", "is",
			"join", "left", "like", "key", "local", "match", "natural", "no",
			"not", "null", "on", "option", "outer", "or", "order", "partial",
			"pendant", "preserve", "primary", "procedure", "record",
			"references", "restrict", "right", "rows", "schema", "select",
			"set", "some", "table", "temporary", "then", "time", "timestamp",
			"true", "type", "union", "unique", "unknown", "update", "user",
			"using", "value", "values", "varchar", "varying", "view", "where",
			"with", "zone", };

	private static final String[] predicates = { "||", ",", ";", ".", "~", "<",
			"<=", ",", ",=", "=", "!=", "(+)", "(", ")", "*", "/", "+", "-",
			"?" };

	private static final String[] types = { "dec", "decimal", "double",
			"float", "int", "integer", "numeric", "real", "smallint",

	};

	private static final String[] constants = { "FALSE", "NULL", "TRUE",
			"false", "true", "null" };

	private static final String[] functions = { "avg", "both", "convert",
			"count", "for", "length", "leading", "lower", "lpad", "ltrim",
			"max", "min", "replace", "rtrim", "substr", "substring", "sum",
			"trailing", "translate", "trim", "upper", };

	private static final String[] _comments = { "--" };

	/**
	 * @return Returns the functions.
	 */
	public String[] getFunctions() {
		return functions;
	}

	/**
	 * @return Returns the predicates.
	 */
	public String[] getPredicates() {
		return predicates;
	}

	/**
	 * @return Returns the reservedwords.
	 */
	public String[] getReservedwords() {
		return reservedwords;
	}

	public String[] getUnreservedwords() {
		return _unreservedwords;
	}

	/**
	 * @return Returns the types.
	 */
	public String[] getTypes() {
		return types;
	}

	/**
	 * @return Returns the constants.
	 */
	public String[] getConstants() {
		return constants;
	}

	public String[] getSingleLineComments() {
		return _comments;
	}

	public String[] getGlobalVariables() {
		return EMPTY;
	}
}
