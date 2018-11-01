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
package org.eclipse.datatools.sqltools.sql.parser;

import java.util.HashMap;

/**
 * This class defines the parameters passed into the parser. Some standard
 * parameter keys are defined in this class but different parser implementations
 * can also use their specific parameter types via this generic mechanism.
 * 
 * @author Hui Cao
 * 
 */
public class ParserParameters {
	/**
	 * Parser parameter type constant indicating whether to use delimiter for
	 * SQL statements . e.g. ASE uses "go" to group statements as a batch
	 * operation; ASA uses ";" or "go" to delimit SQL statements.
	 */
	public static final String PARAM_USE_DELIMITER = "__use_delimiter";

	/**
	 * Parser parameter type constant indicating the connection profile name
	 * that can be used in the parser.
	 */
	public static final String PARAM_PROFILE_NAME = "__profile_name";

	/**
	 * Parser parameter type constant indicating the database name in which
	 * database objects can be found.
	 */
	public static final String PARAM_DB_NAME = "__db_name";

	/**
	 * Parser parameter type constant indicating the outmost statement type.
	 * 
	 * @see SQLParserConstants
	 */
	public static final String PARAM_STMT_TYPE = "__statement_type";

	/**
	 * Parser parameter type constant of type Boolean indicating only the routine header is interesting to the consumer. Can be combined with other routine types.
	 * 
	 * @see SQLParserConstants
	 */
	public static final String PARAM_HEADER_MODE = "__header_mode";
	
	/**
	 * Parser parameter type constant of type Boolean indicating whether needs to link sql model object in the generated ast nodes.
	 * @see IASTDeployable#setSQLModel
	 * @see SQLParserConstants
	 */
	public static final String PARAM_LINK_SQL_MODEL = "__link_sql_model";
	
	/**
	 * Parser parameter type constant indicating whether the
	 * <code>ParseException</code> should be consumed during parsing. If the
	 * parser is not accurate, this parameter should be set to true.
	 */
	public static final String PARAM_CONSUME_EXCEPTION = "__consume_exception";

	private HashMap _properties = new HashMap();

	/**
	 * Constructs a ParserParameter using the given useDelimiter value
	 * 
	 * @param useDelimiter
	 *            whether to use delimiter for SQL statements
	 */
	public ParserParameters(boolean useDelimiter) {
		setProperty(PARAM_USE_DELIMITER, new Boolean(useDelimiter));
	}

	/**
	 * Constructs a ParserParameter using the given useDelimiter and type value.
	 * 
	 * @param useDelimiter
	 *            whether to use delimiter for SQL statements
	 * @param type
	 *            the outmost statement type.
	 * @see SQLParserConstants
	 */
	public ParserParameters(boolean useDelimiter, int type) {
		setProperty(PARAM_USE_DELIMITER, new Boolean(useDelimiter));
		setProperty(PARAM_STMT_TYPE, new Integer(type));
	}

	/**
	 * Returns a boolean indicating whether to use delimiter for SQL statements.
	 */
	public boolean useDelimiter() {
		return Boolean.TRUE.equals(getProperty(PARAM_USE_DELIMITER));
	}

	/**
	 * Sets the useDelimiter value
	 * 
	 * @param useDelimiter
	 */
	public void setUseDelimiter(boolean useDelimiter) {
		setProperty(PARAM_USE_DELIMITER, new Boolean(useDelimiter));
	}

	/**
	 * Gets all the properties.
	 * 
	 * @return
	 */
	public HashMap getProperties() {
		return _properties;
	}

	/**
	 * Sets the properties.
	 * 
	 * @param _properties
	 */
	public void setProperties(HashMap _properties) {
		this._properties = _properties;
	}

	/**
	 * Gets the property value as <code>Object</code>
	 * 
	 * @param key
	 *            property key
	 * @return
	 */
	public Object getProperty(Object key) {
		return _properties.get(key);
	}

	/**
	 * Sets the property <code>key</code> to <code>value</code>
	 * 
	 * @param key
	 * @param value
	 */
	public void setProperty(Object key, Object value) {
		_properties.put(key, value);
	}

	/**
	 * Convenient method for getProperty(Object key)
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(Object key) {
		Integer integer = ((Integer) _properties.get(key));
		return integer == null ? 0 : integer.intValue();
	}

	/**
	 * Convenient method for getProperty(Object key)
	 * 
	 * @param key
	 * @return
	 */
	public boolean getBoolean(Object key) {
		Boolean boolean1 = ((Boolean) _properties.get(key));
		return boolean1 == null ? false : boolean1.booleanValue();
	}

}
