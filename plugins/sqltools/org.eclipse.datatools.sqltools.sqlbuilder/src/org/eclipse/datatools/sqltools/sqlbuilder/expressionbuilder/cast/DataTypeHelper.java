/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.cast;

// TODO: get datatypes from SQL Model rather than hard-coding them here
public class DataTypeHelper
{
  public static final String db2DataTypes [] =
  {
    "SMALLINT",
    "INTEGER",
//    "BIGINT",    
    "DECIMAL",
    "REAL",
    "DOUBLE",
    "CHAR",
    "VARCHAR",
//    "LONG VARCHAR",
    "CLOB",
    "GRAPHIC",
    "VARGRAPHIC",
//    "LONG VARG",
//    "DBCLOB",
    "DATE",
    "TIME",
    "TIMESTAMP",
    "BLOB",
    "NUMERIC",
    "FLOAT",
//    "DATALINK"

//    "NATIONAL CHARACTER",
//    "NATIONAL CHARACTER VARYING",
//    "NATIONAL CHARACTER LOB",
//    "BOOLEAN",
//    "BIT",
//    "BIT VARYING",
//    "REF",
//    "ARRAY",

  };

  public static final String mySQLDataTypes [] =
  {
//    "TINYINT",
    "SMALLINT",
//    "MEDIUMINT",
    "INT",
    "INTEGER",
//    "BIGINT",
    "FLOAT",
    "DOUBLE",
    "DOUBLE PRECISION",
    "REAL",
    "DECIMAL",
    "NUMERIC",
    "DATE",
//    "DATETIME",
    "TIMESTAMP",
    "TIME",
//    "YEAR",
    "NATIONAL CHAR",  // BINARY?
    "CHAR",
    "NATIONAL VARCHAR", // BINARY?
    "VARCHAR",
//    "TINYBLOB",
//    "TINYTEXT",
    "BLOB"
//    "TEXT",
//    "MEDIUMBLOB",
//    "MEDIUMTEXT",
//    "LONGBLOB",
//    "LONGTEXT"
  };

  public static final String oracleDataTypes [] =
  {
    "CHAR",
    "CHARACTER",
    "CHARACTER VARYING",
    "CHAR VARYING",
    "NATIONAL CHARACTER",
    "NATIONAL CHAR",
    "NCHAR",
    "NATIONAL CHARACTER VARYING",
    "NATIONAL CHAR VARYING",
    "NCHAR VARYING",
    "NUMERIC",
    "DECIMAL",
    "INTEGER",
    "INT",
    "SMALLINT",
    "FLOAT",
    "DOUBLE PRECISION",
    "REAL",
    "VARCHAR",
//    "LONG VARCHAR",
//    "VARCHAR2",
//    "NVARCHAR2",
//    "NUMBER",
//    "LONG",
    "DATE",
//    "RAW",
//    "LONG RAW",
//    "ROWID",
//    "UROWID",
    "CHAR",
    "NCHAR",
    "CLOB",
//    "NCLOB",
    "BLOB"
//    "BFILE"
  };

  public static final String sybaseDataTypes [] =
  {
    "char",
    "nchar",
    "varchar",
    "nvarchar",
//    "text",
//    "datetime",
//    "smalldatetime",
//    "binary",
//    "varbinary",
//    "image",
//    "money",
//    "smallmoney",
    "float",
    "real",
    "double precision",
    "decimal",
    "numeric",
    "int",
    "smallint",
//    "tinyint",
    "timestamp"
  };

  public static final String cloudscapeDataTypes [] =
  {
  //  "LONG VARBINARY",
  //  "LONG BIT VARYING",
  //  "LONG BINARY",
  //  "BIT",
  //  "BIT VARYING",
    "CHAR",
//    "CHARACTER",
    "VARCHAR",
//    "CHAR VARYING",
//    "CHARACTER VARYING",
//    "NCHAR",
//    "NATIONAL CHAR",
    "NATIONAL CHARACTER",
//    "NVARCHAR",
//    "NCHAR VARYING",
//    "NATIONAL CHAR VARYING",
    "NATIONAL CHARACTER VARYING",
//    "LONG VARCHAR",
//    "LONG NVARCHAR",
    "DECIMAL",
//    "DEC",
    "NUMERIC",
    "INTEGER",
//    "INT",
//    "BIGINT",
    "SMALLINT",
//    "TINYINT",
    "DOUBLE PRECISION",
    "REAL",
    "FLOAT",
    "BOOLEAN",
    "DATE",
    "TIME",
    "TIMESTAMP"
  };
}