/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.query.helper;

import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesFactory;

/**
 * This helper class provides convenience methods related to
 * {@link org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType}.
 *
 *
 * @author ckadner
 *
 */
public class DataTypeHelper
{
    // TODO: sort out which names to publish, doc them with value:"xxx"
    public static final String TYPENAME_DOUBLE_PRECISION = "DOUBLE PRECISION";
    public static final String TYPENAME_BIGINT 		= "BIGINT";
	public static final String TYPENAME_BLOB 		= "BLOB";
	public static final String TYPENAME_CHAR		= "CHAR";
	public static final String TYPENAME_DBCLOB 		= "DBCLOB";
	public static final String TYPENAME_CHARACTER 	= "CHARACTER";
	public static final String TYPENAME_CHARACTER_VARYING = "CHARACTER VARYING";
	public static final String TYPENAME_CLOB 		= "CLOB";
	public static final String TYPENAME_DATALINK 	= "DATALINK";
	public static final String TYPENAME_DATE 		= "DATE";
	public static final String TYPENAME_DECIMAL 	= "DECIMAL";
	public static final String TYPENAME_DOUBLE 		= "DOUBLE";
	public static final String TYPENAME_GRAPHIC 	= "GRAPHIC";
	public static final String TYPENAME_INT 		= "INT";
	public static final String TYPENAME_INTEGER 	= "INTEGER";
	public static final String TYPENAME_LONGVARG 	= "LONGVARG";
	public static final String TYPENAME_LONGVARN 	= "LONGVARN";
	public static final String TYPENAME_LONGVARCHAR = "LONGVARCHAR";
	public static final String TYPENAME_LONG_VARGRAPHIC = "LONG VARGRAPHIC";
	public static final String TYPENAME_LONG_VARCHAR = "LONG VARCHAR";
	public static final String TYPENAME_REAL 		= "REAL";
	public static final String TYPENAME_SMALLINT 	= "SMALLINT";
	public static final String TYPENAME_TIMESTAMP 	= "TIMESTAMP";
	public static final String TYPENAME_TIME 		= "TIME";
	public static final String TYPENAME_NUMERIC 	= "NUMERIC";
	public static final String TYPENAME_VARBINARY 	= "VARBINARY";
	public static final String TYPENAME_VARCHAR 	= "VARCHAR";
	public static final String TYPENAME_VARGRAPHIC 	= "VARGRAPHIC";
	
	//!TODO: who is going to maintain this DatatypeHelper hack? should be close
	// to EMF stuff and not in a separate class in different PlugIn 
	
    public static final String TYPENAME_CHARACTER_LARGE_OBJECT = TYPENAME_CLOB;
    public static final String TYPENAME_NATIONAL_CHARACTER = TYPENAME_GRAPHIC;
    public static final String TYPENAME_NATIONAL_CHARACTER_VARYING = TYPENAME_VARGRAPHIC;
    public static final String TYPENAME_NATIONAL_CHARACTER_LARGE_OBJECT = TYPENAME_DBCLOB;
    public static final String TYPENAME_BINARY = "BINARY";
    public static final String TYPENAME_BINARY_VARYING = "BINARY VARYING";
    public static final String TYPENAME_BINARY_LARGE_OBJECT = TYPENAME_BLOB;
    public static final String TYPENAME_FLOAT = "FLOAT";
    public static final String TYPENAME_BOOLEAN = "BOOLEAN";
    public static final String TYPENAME_INTERVAL = "INTERVAL";
    public static final String TYPENAME_XML_TYPE = "XML";

	/** CHECK IMPLEMENTATION! CODE UNTESTED!
	 * Gets the JDBC datatype identifier corresponding to the given named  datatype.
	 * This is for use with the datatype names returned from the FunctionHelper.
	 * Note: there is probably a utility somewhere else that does this.  If so,
	 * we can get rid of this method.
	 *
	 * Use type names in constants <code>TYPENAME_*</code> provided by
	 * <code>ValueExpressionHelper</code>.
	 *
	 * @see #TYPENAME_BIGINT
	 * @see #TYPENAME_VARGRAPHIC
	 *
	 * @param aTypeName a  type name to use to lookup a JDBC datatype
	 * @return int a JDBC type identifier.
	 */
	public static int getJDBCTypeForNamedType( String aTypeName ) {
	  int jdbcTypeID = 0;

	  // The types that appear most frequently in the FunctionHelper parameter
	  // lists are checked first.
	  if (aTypeName.equalsIgnoreCase(TYPENAME_INTEGER) || aTypeName.equalsIgnoreCase(TYPENAME_INT)) {
	    jdbcTypeID = java.sql.Types.INTEGER;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_DECIMAL) || aTypeName.equalsIgnoreCase("DEC")
	        || aTypeName.equalsIgnoreCase(TYPENAME_NUMERIC) || aTypeName.equalsIgnoreCase("NUM")) {
	    jdbcTypeID = java.sql.Types.DECIMAL;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_VARCHAR)
	        || aTypeName.equalsIgnoreCase(TYPENAME_CHARACTER_VARYING) || aTypeName.equalsIgnoreCase("CHAR VARYING")) {
	    jdbcTypeID = java.sql.Types.VARCHAR;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_DOUBLE) || aTypeName.equalsIgnoreCase(TYPENAME_DOUBLE_PRECISION)) {
	    jdbcTypeID = java.sql.Types.DOUBLE;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_CHARACTER) || aTypeName.equalsIgnoreCase(TYPENAME_CHAR)) {
	    jdbcTypeID = java.sql.Types.CHAR;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_SMALLINT)) {
	    jdbcTypeID = java.sql.Types.SMALLINT;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_BIGINT)) {
	    jdbcTypeID = java.sql.Types.BIGINT;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_REAL)) {
	    jdbcTypeID = java.sql.Types.REAL;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_DATE)) {
	    jdbcTypeID = java.sql.Types.DATE;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_TIME)) {
	    jdbcTypeID = java.sql.Types.TIME;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_TIMESTAMP)) {
	    jdbcTypeID = java.sql.Types.TIMESTAMP;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_LONG_VARCHAR)) {
	    jdbcTypeID = java.sql.Types.LONGVARCHAR;
	  }
	  // Graphic types map to JDBC char types.
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_GRAPHIC)) {
	    jdbcTypeID = java.sql.Types.CHAR;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_VARGRAPHIC)) {
	    jdbcTypeID = java.sql.Types.VARCHAR;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_LONG_VARGRAPHIC)) {
	    jdbcTypeID = java.sql.Types.LONGVARCHAR;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_VARBINARY)) {
	    jdbcTypeID = java.sql.Types.VARBINARY;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_DATALINK)) {
	    jdbcTypeID = 70;  // is this java.sql.Types.REF?
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_BLOB)) {
	    jdbcTypeID = java.sql.Types.BLOB;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_CLOB)) {
	    jdbcTypeID = java.sql.Types.CLOB;
	  }
	  // DBCLOB maps to JDBC CLOB type.
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_DBCLOB)) {
	    jdbcTypeID = java.sql.Types.CLOB;
	  }

	  return jdbcTypeID;
	}

	/** CHECK IMPLEMENTATION! CODE UNTESTED!
	 * Gets the PrimitiveType enum value corresponding to the given type name.
	 * Use type names in constants <code>TYPENAME_*</code> provided by
	 * <code>ValueExpressionHelper</code>.
	 *
	 * @see #TYPENAME_BIGINT
	 * @see #TYPENAME_VARGRAPHIC
	 * @param aTypeName a  type name to use to lookup a PrimitiveType
	 * @return the PrimitiveType corresponding to the named type
	 */
	public static int getPrimitiveTypeForNamedType( String aTypeName ) {
	  // @d301485 bgp 09Feb2004 - new method
	  int typeEnum = 0;
	  if (aTypeName.equalsIgnoreCase(TYPENAME_BIGINT)) {
	    typeEnum = PrimitiveType.INTEGER;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_BLOB)) {
	    typeEnum = PrimitiveType.BINARY_LARGE_OBJECT;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_CHAR)) {
	    typeEnum = PrimitiveType.CHARACTER;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_CLOB)) {
	    typeEnum = PrimitiveType.CHARACTER_LARGE_OBJECT;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_DATE)) {
	    typeEnum = PrimitiveType.DATE;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_DBCLOB)) {
	    typeEnum = PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_DECIMAL)) {
	    typeEnum = PrimitiveType.DECIMAL;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_DOUBLE)) {
	    typeEnum = PrimitiveType.DOUBLE_PRECISION;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_GRAPHIC)) {
	    typeEnum = PrimitiveType.NATIONAL_CHARACTER;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_INTEGER)) {
	    typeEnum = PrimitiveType.INTEGER;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_LONGVARCHAR)) {
	    typeEnum = PrimitiveType.CHARACTER_LARGE_OBJECT;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_LONGVARG)) {
	      typeEnum = PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT;
	    }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_LONGVARN)) {
	      typeEnum = PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT;
	    }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_REAL)) {
	    typeEnum = PrimitiveType.REAL;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_SMALLINT)) {
	    typeEnum = PrimitiveType.SMALLINT;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_TIME)) {
	    typeEnum = PrimitiveType.TIME;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_TIMESTAMP)) {
	    typeEnum = PrimitiveType.TIMESTAMP;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_VARCHAR)) {
	    typeEnum = PrimitiveType.CHARACTER_VARYING;
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_VARGRAPHIC)) {
	    typeEnum = PrimitiveType.NATIONAL_CHARACTER_VARYING;
	  }

	  return typeEnum;
	}

	/** CHECK IMPLEMENTATION! CODE UNTESTED!
	 * Gets the <code>PredefinedDataType</code> PrimitiveType enum value corresponding to the given type name.
	 * Use type names in constants <code>TYPENAME_*</code> provided by
	 * <code>ValueExpressionHelper</code>.
	 *
	 * @see #TYPENAME_BIGINT
	 * @see #TYPENAME_VARGRAPHIC
	 * @param aTypeName a  type name to use to lookup a PrimitiveType
	 * @return the PrimitiveType corresponding to the named type
	 */
	public static PredefinedDataType getPredefinedDataTypeForNamedType( String aTypeName ) {
	  // ck 04Sep2004 - new method
	  PrimitiveType primitive = null;
	  PredefinedDataType dataType = null;
	  SQLDataTypesFactory dataTypesFactory = SQLDataTypesFactory.eINSTANCE;
	  if (aTypeName.equalsIgnoreCase(TYPENAME_BIGINT)) {
	    primitive = PrimitiveType.INTEGER_LITERAL;
	    dataType = dataTypesFactory.createIntegerDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_BLOB)) {
	    primitive = PrimitiveType.BINARY_LARGE_OBJECT_LITERAL;
	    dataType = dataTypesFactory.createBinaryStringDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_CHAR)) {
	    primitive = PrimitiveType.CHARACTER_LITERAL;
	    dataType = dataTypesFactory.createCharacterStringDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_CLOB)) {
	    primitive = PrimitiveType.CHARACTER_LARGE_OBJECT_LITERAL;
	    dataType = dataTypesFactory.createCharacterStringDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_DATE)) {
	    primitive = PrimitiveType.DATE_LITERAL;
	    dataType = dataTypesFactory.createDateDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_DBCLOB)) {
	    primitive = PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT_LITERAL;
	    dataType = dataTypesFactory.createCharacterStringDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_DECIMAL)) {
	    primitive = PrimitiveType.DECIMAL_LITERAL;
	    dataType = dataTypesFactory.createFixedPrecisionDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_DOUBLE)) {
	    primitive = PrimitiveType.DOUBLE_PRECISION_LITERAL;
	    dataType = dataTypesFactory.createApproximateNumericDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_GRAPHIC)) {
	    primitive = PrimitiveType.NATIONAL_CHARACTER_LITERAL;
	    dataType = dataTypesFactory.createCharacterStringDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_INTEGER)) {
	    primitive = PrimitiveType.INTEGER_LITERAL;
	    dataType = dataTypesFactory.createIntegerDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_LONGVARCHAR)) {
	    primitive = PrimitiveType.CHARACTER_LARGE_OBJECT_LITERAL;
	    dataType = dataTypesFactory.createCharacterStringDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_LONGVARG)) {
	      primitive = PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT_LITERAL;
	      dataType = dataTypesFactory.createCharacterStringDataType();
	    }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_LONGVARN)) {
	      primitive = PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT_LITERAL;
	      dataType = dataTypesFactory.createCharacterStringDataType();
	    }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_REAL)) {
	    primitive = PrimitiveType.REAL_LITERAL; //TODO: check
	    dataType = dataTypesFactory.createFixedPrecisionDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_SMALLINT)) {
	    primitive = PrimitiveType.SMALLINT_LITERAL;
	    dataType = dataTypesFactory.createIntegerDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_TIME)) {
	    primitive = PrimitiveType.TIME_LITERAL;
	    dataType = dataTypesFactory.createTimeDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_TIMESTAMP)) {
	    primitive = PrimitiveType.TIMESTAMP_LITERAL;
	    dataType = dataTypesFactory.createTimeDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_VARCHAR)) {
	    primitive = PrimitiveType.CHARACTER_VARYING_LITERAL;
	    dataType = dataTypesFactory.createCharacterStringDataType();
	  }
	  else if (aTypeName.equalsIgnoreCase(TYPENAME_VARGRAPHIC)) {
	    primitive = PrimitiveType.NATIONAL_CHARACTER_VARYING_LITERAL;
	    dataType = dataTypesFactory.createCharacterStringDataType();
	  }

	  if (dataType != null) {
	      dataType.setPrimitiveType(primitive);
	  }

	  return dataType;
	}

	/**
	 * Returns the name of the given '<em><b>Primitive Type</b></em>' literal.
	 * @param primitiveType
	 * @return the String representing the <code>PrimitiveType</code> one of the
	 * 		<code>TYPENAME_xxx</code> constants
	 *
	 * @see PrimitiveType
	 */
	public static String getPrimitiveTypeName(PrimitiveType primitiveType) {
	    if (primitiveType == PrimitiveType.CHARACTER_LITERAL) { return TYPENAME_CHARACTER; }
        if (primitiveType == PrimitiveType.CHARACTER_VARYING_LITERAL) { return TYPENAME_VARCHAR; }
        if (primitiveType == PrimitiveType.CHARACTER_LARGE_OBJECT_LITERAL) { return TYPENAME_CHARACTER_LARGE_OBJECT; }
        if (primitiveType == PrimitiveType.NATIONAL_CHARACTER_LITERAL) { return TYPENAME_NATIONAL_CHARACTER; }
        if (primitiveType == PrimitiveType.NATIONAL_CHARACTER_VARYING_LITERAL) { return TYPENAME_VARGRAPHIC; }
        if (primitiveType == PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT_LITERAL) { return TYPENAME_NATIONAL_CHARACTER_LARGE_OBJECT; }
        if (primitiveType == PrimitiveType.BINARY_LITERAL) { return TYPENAME_BINARY; }
        if (primitiveType == PrimitiveType.BINARY_VARYING_LITERAL) { return TYPENAME_BINARY_VARYING; }
        if (primitiveType == PrimitiveType.BINARY_LARGE_OBJECT_LITERAL) { return TYPENAME_BINARY_LARGE_OBJECT; }
        if (primitiveType == PrimitiveType.NUMERIC_LITERAL) { return TYPENAME_NUMERIC; }
        if (primitiveType == PrimitiveType.DECIMAL_LITERAL) { return TYPENAME_DECIMAL; }
        if (primitiveType == PrimitiveType.SMALLINT_LITERAL) { return TYPENAME_SMALLINT; }
        if (primitiveType == PrimitiveType.INTEGER_LITERAL) { return TYPENAME_INTEGER; }
        if (primitiveType == PrimitiveType.BIGINT_LITERAL) { return TYPENAME_BIGINT; }
        if (primitiveType == PrimitiveType.FLOAT_LITERAL) { return TYPENAME_FLOAT; }
        if (primitiveType == PrimitiveType.REAL_LITERAL) { return TYPENAME_REAL; }
        if (primitiveType == PrimitiveType.DOUBLE_PRECISION_LITERAL) { return TYPENAME_DOUBLE_PRECISION; }
        if (primitiveType == PrimitiveType.BOOLEAN_LITERAL) { return TYPENAME_BOOLEAN; }
        if (primitiveType == PrimitiveType.DATE_LITERAL) { return TYPENAME_DATE; }
        if (primitiveType == PrimitiveType.TIME_LITERAL) { return TYPENAME_TIME; }
        if (primitiveType == PrimitiveType.TIMESTAMP_LITERAL) { return TYPENAME_TIMESTAMP; }
        if (primitiveType == PrimitiveType.INTERVAL_LITERAL) { return TYPENAME_INTERVAL; }
        if (primitiveType == PrimitiveType.DATALINK_LITERAL) { return TYPENAME_DATALINK; }
        if (primitiveType == PrimitiveType.XML_TYPE_LITERAL) { return TYPENAME_XML_TYPE; }
	    
		return primitiveType.getName();
	}

}
