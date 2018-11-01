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


import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLDataType;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParam;

public class ASTSQLParam extends SimpleNode implements IASTSQLParam {

	private String _name;

	private String _type;

	private IASTSQLDataType _typeObj;

	private String _defaultValue;

	private int _direction = INPUT;

	public ASTSQLParam(int id) {
		super(id);
	}

	public ASTSQLParam(IngresSQLParser p, int id) {
		super(p, id);
	}

	/** Accept the visitor. * */
	public Object jjtAccept(IngresSQLParserVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	public String getDefaultValue() {
		return _defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this._defaultValue = defaultValue;
	}

	public int getDirection() {
		return _direction;
	}

	public void setDirection(int direction) {
		this._direction = direction;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		this._type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.IASTSQLParam#getTypeObject()
	 */
	public IASTSQLDataType getTypeObject() {
		return _typeObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.IASTSQLParam#setTypeObject(com.sybase.stf.dmp.ui.sqleditor.sql.parser.IASTSQLDataType)
	 */
	public void setTypeObject(IASTSQLDataType type) {
		_typeObj = type;
	}

	public String toString() {
		String retval = _name == null ? "" : _name;
		retval += (_type == null ? "" : ":" + _type);
		retval += (_defaultValue == null ? "" : ":" + _defaultValue);
		retval += (_direction == 1 ? ":" + "OUT" : (_direction == 2 ? ":" + "INOUT" : "")); //$NON-NLS-2$ //$NON-NLS-4$

		return retval;
	}
}
