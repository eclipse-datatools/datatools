/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples;

import java.sql.Types;

/**
 * The class describe all datatypes in ASE12/ASE15/ASA/ASIQ. These datatypes are defined in 
 * String form because, generallly speaking, we can not get the datatype's sql type from database's
 * systemp tables which are integer.
 * In addition, a map to JDBC SQL type is designed. 
 * 
 * @author Shi-feng Yu
 */
public class SQLDataTypes
{
    public static final String BINARY              = "binary";
    public static final String BIT                 = "bit";
    public static final String BIGINT              = "bigint";
    public static final String CHAR                = "char";
    public static final String CHARACTER           = "character";
    public static final String CHARACTER_VARYING   = "character varying";
    public static final String DATE                = "date";
    public static final String DATETIME            = "datetime";
    public static final String DECIMAL             = "decimal";
    public static final String DOUBLE              = "double";
    public static final String DOUBLE_PRECISION    = "double precision";
    public static final String FLOAT               = "float";
    public static final String IMAGE               = "image";
    public static final String INT                 = "int";
    public static final String INTEGER             = "integer";
    public static final String LONG_VARCHAR        = "long varchar";
    public static final String LONG_BINARY         = "long binary";
    public static final String LONG_VARBINARY      = "long varbinary";
    public static final String LONGSYSNAME         = "longsysname";
    public static final String MONEY               = "money";
    public static final String NUMERIC             = "numeric";
    public static final String NCHAR               = "nchar";
    public static final String NATIONAL_CHARACTER  = "national character";
    public static final String NATIONAL_CHAR       = "national char";
    public static final String NVARCHAR            = "nvarchar";
    public static final String REAL                = "real";
    public static final String SMALLDATETIME       = "smalldatetime";
    public static final String SMALLMONEY          = "smallmoney";
    public static final String SYSNAME             = "sysname";
    public static final String SMALLINT            = "smallint";
    public static final String TIME                = "time";
    public static final String TIMESTAMP           = "timestamp";
    public static final String TEXT                = "text";
    public static final String TINYINT             = "tinyint";
    public static final String UNICHAR             = "unichar";
    public static final String UNITEXT             = "unitext";
    public static final String UNIVARCHAR          = "univarchar";
    public static final String UNSIGNED_SMALLINT   = "unsigned smallint";
    public static final String UNSIGNED_INT        = "unsigned int";
    public static final String UNSIGNED_BIGINT     = "unsigned bigint";
    public static final String UNSIGNED_INTEGER    = "unsigned integer";
    public static final String UNIQUEIDENTIFIER    = "uniqueidentifier";
    public static final String UNIQUEIDENTIFIERSTR = "uniqueidentifierstr";
    public static final String VARBINARY           = "varbinary";
    public static final String VARCHAR             = "varchar";
    public static final String XML                 = "xml";
    public static final String JAVA_LANG_OBJECT    = "java.lang.object";
    public static final String JAVA_SERIALIZARION  = "java serialization";

    /**
     * Convert datatype in String to JDBC SQL type in integer.
     * @param typeName
     * @return
     */
    public static int convert2SQLType(String typeName)
    {
        if (typeName == null)
        {
            return 0;
        }
        String name = typeName.toLowerCase();

        if (name.equals(BIGINT) || name.equals(UNSIGNED_BIGINT))
        {
            return Types.BIGINT;
        }
        else if (name.equals(BINARY) || name.equals(UNIQUEIDENTIFIER))
        {
            return Types.BINARY;
        }
        else if (name.equals(BIT))
        {
            return Types.BIT;
        }
        else if (name.equals("BLOB"))
        {
            return Types.BLOB;
        }
        else if (name.equals("BOOLEAN") || name.equals("BOOL"))
        {
            return Types.BOOLEAN;
        }
        else if (name.equals(CHAR) || name.equals(CHARACTER) || name.equals(NCHAR) || name.equals(NATIONAL_CHARACTER)
        || name.equals(NATIONAL_CHAR) || name.equals(UNICHAR) || name.equals(UNIQUEIDENTIFIERSTR))
        {
            return Types.CHAR;
        }
        else if (name.equals("CLOB"))
        {
            return Types.CLOB;
        }
        else if (name.equals("DATALINK"))
        {
            return Types.DATALINK;
        }
        else if (name.equals(DATE))
        {
            return Types.DATE;
        }
        else if (name.equals(DECIMAL) || name.equals("DEC") || name.equals(MONEY) || name.equals(SMALLMONEY))
        {
            return Types.DECIMAL;
        }
        else if (name.equals("DISTINCT"))
        {
            return Types.DISTINCT;
        }
        else if (name.equals(DOUBLE) || name.equals(DOUBLE_PRECISION))
        {
            return Types.DOUBLE;
        }
        else if (name.equals(FLOAT))
        {
            return Types.FLOAT;
        }
        else if (name.equals(INTEGER) || name.equals(INT) || name.equals(UNSIGNED_INTEGER) || name.equals(UNSIGNED_INT) || name.equals(UNSIGNED_SMALLINT))
        {
            return Types.INTEGER;
        }
        else if (name.equals(JAVA_LANG_OBJECT))
        {
            return Types.JAVA_OBJECT;
        }
        else if (name.equals(LONG_VARBINARY) || name.equals(IMAGE) || name.equals(XML))
        {
            return Types.LONGVARBINARY;
        }
        else if (name.equals(LONG_VARCHAR) || name.equals(TEXT) || name.equals(UNITEXT))
        {
            return Types.LONGVARCHAR;
        }
        else if (name.equals("NULL"))
        {
            return Types.NULL;
        }
        else if (name.equals(NUMERIC))
        {
            return Types.NUMERIC;
        }
        else if (name.equals("OTHER"))
        {
            return Types.OTHER;
        }
        else if (name.equals(REAL))
        {
            return Types.REAL;
        }
        else if (name.equals("REF"))
        {
            return Types.REF;
        }
        else if (name.equals(SMALLINT))
        {
            return Types.SMALLINT;
        }
        else if (name.equals("STRUCT"))
        {
            return Types.STRUCT;
        }
        else if (name.equals(TIME))
        {
            return Types.TIME;
        }
        else if (name.equals(TIMESTAMP) || name.equals(DATETIME) || name.equals(SMALLDATETIME))
        {
            return Types.TIMESTAMP;
        }
        else if (name.equals(TINYINT))
        {
            return Types.TINYINT;
        }
        else if (name.equals(LONG_BINARY)||name.equals(LONG_VARBINARY))
        {
            return Types.LONGVARBINARY;
        }
        else if (name.equals(VARBINARY))
        {
            return Types.VARBINARY;
        }
        else if (name.equals(SYSNAME)||name.equals(VARCHAR) || name.equals(NVARCHAR) || name.equals(UNIVARCHAR)
        || (name.indexOf("CHAR") > 0 && name.indexOf("VARYING") > 0))
        {
            return Types.VARCHAR;
        }
        else
        {
            return Types.OTHER;
        }
    }
}
