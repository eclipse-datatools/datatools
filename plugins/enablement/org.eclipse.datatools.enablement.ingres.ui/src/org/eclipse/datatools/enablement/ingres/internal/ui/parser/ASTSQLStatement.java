/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.parser;

import java.util.ArrayList;
import java.util.Collection;


import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.datatools.sqltools.sql.parser.Token;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLStatement;
import org.eclipse.datatools.sqltools.sql.parser.util.ASTSQLStatementUtil;
import org.eclipse.swt.graphics.Image;

public class ASTSQLStatement extends SimpleNode implements IASTSQLStatement, IngresSQLParserConstants {

	private int _type = -1;

	private ArrayList objectIds = new ArrayList();

	public ASTSQLStatement(int id) {
		super(id);
	}

	public ASTSQLStatement(IngresSQLParser p, int id) {
		super(p, id);
	}

	/** Accept the visitor. * */
	public Object jjtAccept(IngresSQLParserVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	public int getType() {
		if (_type == -1) {
			_type = determineType();
		}
		return _type;
	}

	/**
	 * @return
	 */
	private int determineType() {
		switch (_firstToken.kind) {
		case SELECT:
			return SQLParserConstants.TYPE_SQL_SELECT;
		case UPDATE:
			return SQLParserConstants.TYPE_SQL_UPDATE;
		case DELETE:
			return SQLParserConstants.TYPE_SQL_DELETE;
		case INSERT:
			return SQLParserConstants.TYPE_SQL_INSERT;
		case CREATE:
			Token createToken = _firstToken.next;
			switch (createToken.kind) {
			case DATABASE:
			case TEMPORARY:
				return SQLParserConstants.TYPE_SQL_CREATE_DATABASE;
			case TABLE:
				return SQLParserConstants.TYPE_SQL_CREATE_TABLE;
			case VIEW:
				return SQLParserConstants.TYPE_SQL_CREATE_VIEW;
			case PROCEDURE:
			case ID:
				if ("FUNCTION".equalsIgnoreCase(createToken.image)) {
					return SQLParserConstants.TYPE_SQL_CREATE_FUNCTION;
				}
				if ("TRIGGER".equalsIgnoreCase(createToken.image)) {
					return SQLParserConstants.TYPE_SQL_CREATE_TRIGGER;
				}
			case DEFAULT_VAL:
				return SQLParserConstants.TYPE_SQL_CREATE_DEFAULT;
			default:
				break;
			}
			break;
		case ALTER:
			Token alterToken = _firstToken.next;
			switch (alterToken.kind) {
			case DATABASE:
			case TEMPORARY:
				return SQLParserConstants.TYPE_SQL_ALTER_DATABASE;
			case TABLE:
				return SQLParserConstants.TYPE_SQL_ALTER_TABLE;
			default:
				break;
			}
		case DECLARE:
			Token declareToken = _firstToken.next;
			switch (declareToken.kind) {
			case VAR_NAME:
				return SQLParserConstants.TYPE_SQL_DECLARE;
			default:
				break;
			}
		case BEGIN:
			return SQLParserConstants.TYPE_SQL_BEGIN;
		default:
			break;
		}
		return SQLParserConstants.TYPE_SQL_OTHERS;
	}

	public String toString() {
		return ASTSQLStatementUtil.toString(getType(), this,
				getObjectIdentifiers());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.IASTSQLStatement#getAffectedTables()
	 */
	public Collection getObjectIdentifiers() {
		return objectIds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.IASTSQLStatement#addObjectIdentifiers(java.lang.String)
	 */
	public void addObjectIdentifier(String name) {
		objectIds.add(name);
	}

}
