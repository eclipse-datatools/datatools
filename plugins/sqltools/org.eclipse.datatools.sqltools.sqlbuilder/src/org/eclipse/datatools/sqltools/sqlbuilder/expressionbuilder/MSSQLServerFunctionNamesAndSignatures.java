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
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder;

import java.util.Arrays;
import java.util.List;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.swt.widgets.Combo;


public class MSSQLServerFunctionNamesAndSignatures
{

  public static final String msSQLServerAll = Messages._UI_FCN_ALL;
  public static final String msSQLServerAggregate = Messages._UI_FCN_AGGREGATE;
  public static final String msSQLServerConfiguration = "Configuration";
  public static final String msSQLServerCursor = "Cursor";
  public static final String msSQLServerDateAndTime = Messages._UI_FCN_DATE_TIME;
  public static final String msSQLServerMath = Messages._UI_FCN_MATH;
  public static final String msSQLServerMetadata = "Metadata";
  public static final String msSQLServerSecurity = "Security";
  public static final String msSQLServerString = Messages._UI_FCN_STRING;
  public static final String msSQLServerSystem = Messages._UI_FCN_SYSTEM;
  public static final String msSQLServerSystemStatistical = "System Statistical";
  public static final String msSQLServerTextAndImage = "Text and Image";
  public static final String msSQLServerRowset = "Rowset";
  public static final String msSQLServerNotSupported = Messages._UI_FCN_NOT_SUPPORTED;
  public static String dbUDF = Messages._UI_FCN_UDF;

  private static final String allFunctions[] =
  {
    "ABS",
    "ACOS",
    "APP_NAME",
    "ASCII",
    "ASIN",
    "ATAN",
    "ATN2",
    "AVG",
    "CEILING",
    "CHAR",
    "CHARINDEX",
    "COALESCE",
    "COLUMNPROPERTY",
    "COL_LENGTH",
    "COL_NAME",
    "CONTAINSTABLE",
    "CONVERT",
    "COS",
    "COT",
    "COUNT",
    "CURRENT_TIMESTAMP", // no brackets
    "CURRENT_USER", // no brackets
    "CURSOR_STATUS",
    "DATABASEPROPERTY",
    "DATALENGTH",
    "DATEADD",
    "DATEDIFF",
    "DATENAME",
    "DATEPART",
    "DAY",
    "DB_ID",
    "DB_NAME",
    "DEGREES",
    "DIFFERENCE",
    "EXP",
    "FILEGROUPPROPERTY",
    "FILEGROUP_ID",
    "FILEGROUP_NAME",
    "FILEPROPERTY",
    "FILE_ID",
    "FILE_NAME",
    "FLOOR",
    "FORMATMESSAGE",
    "FREETEXTTABLE",
    "FULLTEXTCATALOGPROPERTY",
    "FULLTEXTSERVICEPROPERTY",
    "GETANSINULL",
    "GETDATE",
    "GROUPING",
    "HOST_ID",
    "HOST_NAME",
    "IDENTITY",
    "IDENT_INCR",
    "IDENT_SEED",
    "INDEXPROPERTY",
    "INDEX_COL",
    "ISDATE",
    "ISNULL",
    "ISNUMERIC",
    "IS_MEMBER",
    "IS_SRVROLEMEMBER",
    "LEFT",
    "LEN",
    "LOG10",
    "LOG",
    "LOWER",
    "LTRIM",
    "MAX",
    "MIN",
    "MONTH",
    "NCHAR",
    "NEWID",
    "NULLIF",
    "OBJECTPROPERTY",
    "OBJECT_ID",
    "OBJECT_NAME",
    "OPENQUERY",
    "OPENROWSET",
    "PARSENAME",
    "PATINDEX",
    "PATINDEX",
    "PERMISSIONS",
    "PI",
    "POWER",
    "QUOTENAME",
    "RADIANS",
    "RAND",
    "REPLACE",
    "REPLICATE",
    "REVERSE",
    "RIGHT",
    "ROUND",
    "RTRIM",
    "SESSION_USER",  // no brackets
    "SIGN",
    "SIN",
    "SOUNDEX",
    "SPACE",
    "SQRT",
    "SQUARE",
    "STATS_DATE",
    "STDEVP",
    "STDEV",
    "STR",
    "STUFF",
    "SUBSTRING",
    "SUM",
    "SUSER_ID",
    "SUSER_NAME",
    "SUSER_SID",
    "SUSER_SNAME",
    "SYSTEM_USER",  // no brackets
    "TAN",
    "TEXTPTR",
    "TEXTVALID",
    "TYPEPROPERTY",
    "UNICODE",
    "UPPER",
    "USER", // no brackets
    "USER_ID",
    "USER_NAME",
    "VARP",
    "VAR",
    "YEAR",
    "@@CONNECTIONS", // no brackets
    "@@CPU_BUSY",
    "@@CURSOR_ROWS",
    "@@DATEFIRST",
    "@@DBTS",
    "@@ERROR",
    "@@FETCH_STATUS",
    "@@IDENTITY",
    "@@IDLE",
    "@@IO_BUSY",
    "@@LANGID",
    "@@LANGUAGE",
    "@@LOCK_TIMEOUT",
    "@@MAX_CONNECTIONS",
    "@@MAX_PRECISION",
    "@@NESTLEVEL",
    "@@OPTIONS",
    "@@PACKET_ERRORS",
    "@@PACK_RECEIVED",
    "@@PACK_SENT",
    "@@PROCID",
    "@@REMSERVER",
    "@@ROWCOUNT",
    "@@SERVERNAME",
    "@@SERVICENAME",
    "@@SPID",
    "@@TEXTSIZE",
    "@@TIMETICKS",
    "@@TOTAL_ERRORS",
    "@@TOTAL_READ",
    "@@TOTAL_WRITE",
    "@@TRANCOUNT",
    "@@VERSION"
  };

  private static final String allColumnSupportedFunctions[] = allFunctions;

  private static final String functionsSupportingStar[] =
  {
    "COUNT"
  };

  private static final String unsupportedFunctions[] =
  {
    "COALESCE",
    "CONTAINSTABLE",
    "CONVERT",
    "CURSOR_STATUS",
    "FORMATMESSAGE",
    "FREETEXTTABLE",
    "IDENTITY",
    "OPENROWSET"
  };

  private static final String noBracketFunctions[] =
  {
    "CURRENT_TIMESTAMP",
    "CURRENT_USER",
    "SESSION_USER",
    "SYSTEM_USER",
    "USER",
    "@@CONNECTIONS",
    "@@CPU_BUSY",
    "@@CURSOR_ROWS",
    "@@DATEFIRST",
    "@@DBTS",
    "@@ERROR",
    "@@FETCH_STATUS",
    "@@IDENTITY",
    "@@IDLE",
    "@@IO_BUSY",
    "@@LANGID",
    "@@LANGUAGE",
    "@@LOCK_TIMEOUT",
    "@@MAX_CONNECTIONS",
    "@@MAX_PRECISION",
    "@@NESTLEVEL",
    "@@OPTIONS",
    "@@PACKET_ERRORS",
    "@@PACK_RECEIVED",
    "@@PACK_SENT",
    "@@PROCID",
    "@@REMSERVER",
    "@@ROWCOUNT",
    "@@SERVERNAME",
    "@@SERVICENAME",
    "@@SPID",
    "@@TEXTSIZE",
    "@@TIMETICKS",
    "@@TOTAL_ERRORS",
    "@@TOTAL_READ",
    "@@TOTAL_WRITE",
    "@@TRANCOUNT",
    "@@VERSION"
  };

  private static final String aggregateFunctions[] =
  {
    "AVG",
    "COUNT",
    "GROUPING",
    "MAX",
    "MIN",
    "SUM",
    "STDEV",
    "STDEVP",
    "VAR",
    "VARP"
  };

  private static final String configurationFunctions[] =
  {
    "@@CONNECTIONS",
    "@@DATEFIRST",
    "@@DBTS",
    "@@LANGID",
    "@@LANGUAGE",
    "@@LOCK_TIMEOUT",
    "@@MAX_CONNECTIONS",
    "@@MAX_PRECISION",
    "@@NESTLEVEL",
    "@@OPTIONS",
    "@@REMSERVER",
    "@@SERVERNAME",
    "@@SERVICENAME",
    "@@SPID",
    "@@TEXTSIZE",
    "@@VERSION"
  };

  private static final String cursorFunctions[] =
  {
    "CURSOR_STATUS",
    "@@CURSOR_ROWS",
    "@@FETCH_STATUS"
  };

  private static final String dateAndTimeFunctions[] =
  {
    "DATEADD",
    "DATEDIFF",
    "DATENAME",
    "DATEPART",
    "DAY",
    "GETDATE",
    "MONTH",
    "YEAR"
  };

  private static final String mathFunctions[] =
  {
    "ABS",
    "ACOS",
    "ASIN",
    "ATAN",
    "ATN2",
    "CEILING",
    "COS",
    "COT",
    "DEGREES",
    "EXP",
    "FLOOR",
    "LOG",
    "LOG10",
    "PI",
    "POWER",
    "RADIANS",
    "RAND",
    "ROUND",
    "SIGN",
    "SIN",
    "SQUARE",
    "SQRT",
    "TAN"
  };

  private static final String metadataFunctions[] =
  {
    "COL_LENGTH",
    "COL_NAME",
    "COLUMNPROPERTY",
    "DATABASEPROPERTY",
    "DB_ID",
    "DB_NAME",
    "FILEGROUPPROPERTY",
    "FILEGROUP_ID",
    "FILEGROUP_NAME",
    "FILEPROPERTY",
    "FILE_ID",
    "FILE_NAME",
    "FULLTEXTCATALOGPROPERTY",
    "FULLTEXTSERVICEPROPERTY",
    "INDEXPROPERTY",
    "INDEX_COL",
    "OBJECTPROPERTY",
    "OBJECT_ID",
    "OBJECT_NAME",
    "TYPEPROPERTY",
    "@@PROCID"
  };

  private static final String securityFunctions[] =
  {
    "IS_MEMBER",
    "IS_SRVROLEMEMBER",
    "SUSER_ID",
    "SUSER_NAME",
    "SUSER_SID",
    "SUSER_SNAME",
    "USER_ID",
    "USER"  // no brackets
  };

  private static final String stringFunctions[] =
  {
    "ASCII",
    "CHAR",
    "CHARINDEX",
    "DIFFERENCE",
    "LEFT",
    "LEN",
    "LOWER",
    "LTRIM",
    "NCHAR",
    "PATINDEX",
    "QUOTENAME",
    "REPLACE",
    "REPLICATE",
    "REVERSE",
    "RIGHT",
    "RTRIM",
    "SOUNDEX",
    "SPACE",
    "STR",
    "STUFF",
    "SUBSTRING",
    "UNICODE",
    "UPPER"
  };

  private static final String systemFunctions[] =
  {
    "APP_NAME",
    "CONVERT",
    "COALESCE",
    "CURRENT_TIMESTAMP", // no brackets
    "CURRENT_USER", // no brackets
    "DATALENGTH",
    "FORMATMESSAGE",
    "GETANSINULL",
    "HOST_ID",
    "HOST_NAME",
    "IDENT_INCR",
    "IDENT_SEED",
    "IDENTITY",
    "ISDATE",
    "ISNULL",
    "ISNUMERIC",
    "NEWID",
    "NULLIF",
    "PARSENAME",
    "PERMISSIONS",
    "SESSION_USER",  // no brackets
    "STATS_DATE",
    "SYSTEM_USER",  // no brackets
    "USER_NAME",
    "@@ERROR",
    "@@IDENTITY",
    "@@ROWCOUNT",
    "@@TRANCOUNT"
  };

  private static final String systemStatisticalFunctions[] =
  {
    "@@CPU_BUSY",
    "@@IDLE",
    "@@IO_BUSY",
    "@@PACKET_ERRORS",
    "@@PACK_RECEIVED",
    "@@PACK_SENT",
    "@@TIMETICKS",
    "@@TOTAL_ERRORS",
    "@@TOTAL_READ",
    "@@TOTAL_WRITE"
  };

  private static final String textAndImageFunctions[] =
  {
    "PATINDEX",
    "TEXTPTR",
    "TEXTVALID"
  };

  private static final String rowsetFunctions[] =
  {
    "CONTAINSTABLE",
    "FREETEXTTABLE",
    "OPENQUERY",
    "OPENROWSET"
  };


  public static Object[][] getParms(String func)
  {
    Object[][] list = new Object[1][2];
    list[0][0] = "???"; list[0][1] = "???";

    if (func.equals("ABS"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("ACOS"))
    {
      list[0][0] = "float_expression"; list[0][1] = "float_expression";
    }
    else if (func.equals("APP_NAME"))
    {
      list = new Object[1][1];
      list[0][0] = "nvarchar";
    }
    else if (func.equals("ASCII"))
    {
      list[0][0] = "int"; list[0][1] = "character_expression";
    }
    else if (func.equals("ASIN"))
    {
      list[0][0] = "float_expression"; list[0][1] = "float_expression";
    }
    else if (func.equals("ATAN"))
    {
      list[0][0] = "float_expression"; list[0][1] = "float_expression";
    }
    else if (func.equals("ATN2"))
    {
      list = new Object[1][3];
      list[0][0] = "float_expression"; list[0][1] = "float_expression"; list[0][2] = "float_expression";
    }
    else if (func.equals("AVG")) // DISTINCT, ALL
    {
      list[0][0] = "numeric_expression"; list[0][1] = "expression";
    }
    else if (func.equals("CEILING"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("CHAR"))
    {
      list[0][0] = "char(1)"; list[0][1] = "integer";
    }
    else if (func.equals("CHARINDEX"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];

      list[0][0] = "int"; list[0][1] = "expression"; list[0][2] = "expression";
      list[1][0] = "int"; list[1][1] = "expression"; list[1][2] = "expression"; list[1][3] = "start_location";
    }
    else if (func.equals("COALESCE"))
    {
      list = new Object[1][1];
      list[0][0] = msSQLServerNotSupported;
    }
    else if (func.equals("COLUMNPROPERTY")) // property is fixed
    {
      list = new Object[1][4];
      list[0][0] = "property"; list[0][1] = "id"; list[0][2] = "column"; list[0][3] = "property";
    }
    else if (func.equals("COL_LENGTH"))
    {
      list = new Object[1][3];
      list[0][0] = "int"; list[0][1] = "'table'"; list[0][2] = "'column'";
    }
    else if (func.equals("COL_NAME"))
    {
      list = new Object[1][3];
      list[0][0] = "sysname"; list[0][1] = "table_id"; list[0][2] = "column_id";
    }
    else if (func.equals("CONTAINSTABLE"))
    {
      list = new Object[1][1];
      list[0][0] = msSQLServerNotSupported;
    }
    else if (func.equals("CONVERT"))
    {
      list = new Object[1][1];
      list[0][0] = msSQLServerNotSupported;
    }
    else if (func.equals("COS"))
    {
      list[0][0] = "float_expression"; list[0][1] = "float_expression";
    }
    else if (func.equals("COT"))
    {
      list[0][0] = "float_expression"; list[0][1] = "float_expression";
    }
    else if (func.equals("COUNT")) // DISTINCT, ALL
    {
      list[0][0] = "int"; list[0][1] = "expression";
    }
    else if (func.equals("CURRENT_TIMESTAMP")) // no brackets
    {
      list = new Object[1][1];
      list[0][0] = "datetime";
    }
    else if (func.equals("CURRENT_USER")) // no brackets
    {
      list = new Object[1][1];
      list[0][0] = "sysname";
    }
    else if (func.equals("CURSOR_STATUS"))
    {
      list = new Object[1][1];
      list[0][0] = msSQLServerNotSupported;
    }
    else if (func.equals("DATABASEPROPERTY")) // property is fixed
    {
      list = new Object[1][3];
      list[0][0] = "property"; list[0][1] = "database"; list[0][2] = "property";
    }
    else if (func.equals("DATALENGTH"))
    {
      list[0][0] = "int"; list[0][1] = "expression";
    }
    else if (func.equals("DATEADD")) // datepart is fixed
    {
      list = new Object[2][4];
      list[0][0] = "datetime"; list[0][1] = "datepart"; list[0][2] = "number"; list[0][3] = "datetime";
      list[1][0] = "smalldatetime"; list[1][1] = "datepart"; list[1][2] = "number"; list[1][3] = "smalldatetime";
    }
    else if (func.equals("DATEDIFF")) // datepart is fixed
    {
      list = new Object[1][4];
      list[0][0] = "integer"; list[0][1] = "datepart"; list[0][2] = "startdate"; list[0][3] = "enddate";
    }
    else if (func.equals("DATENAME")) // datepart is fixed
    {
      list = new Object[1][3];
      list[0][0] = "nvarchar"; list[0][1] = "datepart"; list[0][2] = "date";
    }
    else if (func.equals("DATEPART")) // datepart is fixed
    {
      list = new Object[1][3];
      list[0][0] = "int"; list[0][1] = "datepart"; list[0][2] = "date";
    }
    else if (func.equals("DAY"))
    {
      list[0][0] = "int"; list[0][1] = "date";
    }
    else if (func.equals("DB_ID"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "smallint";
      list[1][0] = "smallint"; list[1][1] = "'database_name'";
    }
    else if (func.equals("DB_NAME"))
    {
      list[0][0] = "nvarchar(128)"; list[0][1] = "database_id";
    }
    else if (func.equals("DEGREES"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("DIFFERENCE"))
    {
      list = new Object[1][3];
      list[0][0] = "int"; list[0][1] = "character_expression"; list[0][2] = "character_expression";
    }
    else if (func.equals("EXP"))
    {
      list[0][0] = "float_expression"; list[0][1] = "float_expression";
    }
    else if (func.equals("FILEGROUPPROPERTY")) // property is fixed
    {
      list = new Object[1][3];
      list[0][0] = "int"; list[0][1] = "filegroup_name"; list[0][2] = "property";
    }
    else if (func.equals("FILEGROUP_ID"))
    {
      list[0][0] = "smallint"; list[0][1] = "'filegroup_name'";
    }
    else if (func.equals("FILEGROUP_NAME"))
    {
      list[0][0] = "nvarchar(128)"; list[0][1] = "filegroup_id";
    }
    else if (func.equals("FILEPROPERTY"))  // property is fixed
    {
      list = new Object[1][3];
      list[0][0] = "int"; list[0][1] = "file_name"; list[0][2] = "property";
    }
    else if (func.equals("FILE_ID"))
    {
      list[0][0] = "smallint"; list[0][1] = "'file_name'";
    }
    else if (func.equals("FILE_NAME"))
    {
      list[0][0] = "nvarchar(128)"; list[0][1] = "file_id";
    }
    else if (func.equals("FLOOR"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("FORMATMESSAGE"))
    {
      list = new Object[1][1];
      list[0][0] = msSQLServerNotSupported;
    }
    else if (func.equals("FREETEXTTABLE"))
    {
      list = new Object[1][1];
      list[0][0] = msSQLServerNotSupported;
    }
    else if (func.equals("FULLTEXTCATALOGPROPERTY")) // property is fixed
    {
      list = new Object[1][3];
      list[0][0] = "int"; list[0][1] = "catalog_name"; list[0][2] = "property";
    }
    else if (func.equals("FULLTEXTSERVICEPROPERTY")) // property is fixed
    {
      list[0][0] = "int"; list[0][1] = "property";
    }
    else if (func.equals("GETANSINULL"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "int";
      list[1][0] = "int"; list[1][1] = "'database'";
    }
    else if (func.equals("GETDATE"))
    {
      list = new Object[1][1];
      list[0][0] = "datetime";
    }
    else if (func.equals("GROUPING"))
    {
      list[0][0] = "int"; list[0][1] = "column_name";
    }
    else if (func.equals("HOST_ID"))
    {
      list = new Object[1][1];
      list[0][0] = "char(8)";
    }
    else if (func.equals("HOST_NAME"))
    {
      list = new Object[1][1];
      list[0][0] = "nchar";
    }
    else if (func.equals("IDENTITY"))
    {
      list = new Object[1][1];
      list[0][0] = msSQLServerNotSupported;
    }
    else if (func.equals("IDENT_INCR"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "'table_or_view'";
    }
    else if (func.equals("IDENT_SEED"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "'table_or_view'";
    }
    else if (func.equals("INDEXPROPERTY"))
    {
      list = new Object[1][4];
      list[0][0] = "int"; list[0][1] = "table_ID"; list[0][2] = "index"; list[0][3] = "property";
    }
    else if (func.equals("INDEX_COL"))
    {
      list = new Object[1][4];
      list[0][0] = "nchar"; list[0][1] = "'table'"; list[0][2] = "index_id"; list[0][3] = "key_id";
    }
    else if (func.equals("ISDATE"))
    {
      list[0][0] = "int"; list[0][1] = "expression";
    }
    else if (func.equals("ISNULL"))
    {
      list = new Object[1][3];
      list[0][0] = "integer"; list[0][1] = "check_expression"; list[0][2] = "replacement_value";
    }
    else if (func.equals("ISNUMERIC"))
    {
      list[0][0] = "int"; list[0][1] = "expression";
    }
    else if (func.equals("IS_MEMBER"))
    {
      list[0][0] = "int"; list[0][1] = "'group' | 'role'";
    }
    else if (func.equals("IS_SRVROLEMEMBER"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "int"; list[0][1] = "'role'";
      list[1][0] = "int"; list[1][1] = "'role'"; list[1][2] = "'login'";
    }
    else if (func.equals("LEFT"))
    {
      list = new Object[1][3];
      list[0][0] = "varchar"; list[0][1] = "character_expression"; list[0][2] = "integer_expression";
    }
    else if (func.equals("LEN"))
    {
      list[0][0] = "int"; list[0][1] = "string_expression";
    }
    else if (func.equals("LOG"))
    {
      list[0][0] = "float"; list[0][1] = "float_expression";
    }
    else if (func.equals("LOG10"))
    {
      list[0][0] = "float"; list[0][1] = "float_expression";
    }
    else if (func.equals("LOWER"))
    {
      list[0][0] = "varchar"; list[0][1] = "character_expression";
    }
    else if (func.equals("LTRIM"))
    {
      list[0][0] = "varchar"; list[0][1] = "character_expression";
    }
    else if (func.equals("MAX")) // ALL | DISTINCT
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("MIN")) // ALL | DISTINCT
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("MONTH"))
    {
      list[0][0] = "int"; list[0][1] = "date";
    }
    else if (func.equals("NCHAR"))
    {
      list[0][0] = "nchar(1)"; list[0][1] = "integer_expression";
    }
    else if (func.equals("NEWID"))
    {
      list = new Object[1][1];
      list[0][0] = "uniqueidentifier";
    }
    else if (func.equals("NULLIF"))
    {
      list = new Object[1][3];
      list[0][0] = "expression"; list[0][1] = "expression"; list[0][2] = "expression";
    }
    else if (func.equals("OBJECTPROPERTY")) // property is fixed
    {
      list = new Object[1][3];
      list[0][0] = "int"; list[0][1] = "object_id"; list[0][2] = "property";
    }
    else if (func.equals("OBJECT_ID"))
    {
      list[0][0] = "int"; list[0][1] = "'object'";
    }
    else if (func.equals("OBJECT_NAME"))
    {
      list[0][0] = "nchar"; list[0][1] = "object_id";
    }
    else if (func.equals("OPENQUERY"))
    {
      list[0][0] = "result_set"; list[0][1] = "'query'";
    }
    else if (func.equals("OPENROWSET"))
    {
      list = new Object[1][1];
      list[0][0] = msSQLServerNotSupported;
    }
    else if (func.equals("PARSENAME")) // object_piece is fixed: 1,2,3 or 4
    {
      list = new Object[1][3];
      list[0][0] = "nchar"; list[0][1] = "'object_name'"; list[0][2] = "object_piece";
    }
    else if (func.equals("PATINDEX"))
    {
      list = new Object[1][3];
      list[0][0] = "int"; list[0][1] = "'%pattern%'"; list[0][2] = "expression";
    }
    else if (func.equals("PERMISSIONS"))
    {
      list = new Object[3][];
      list[0] = new Object[1];
      list[1] = new Object[2];
      list[2] = new Object[3];

      list[0][0] = "int";
      list[1][0] = "int"; list[1][1] = "object_id";
      list[2][0] = "int"; list[2][1] = "object_id"; list[2][2] = "'column'";
    }
    else if (func.equals("PI"))
    {
      list = new Object[1][1];
      list[0][0] = "float";
    }
    else if (func.equals("POWER"))
    {
      list = new Object[1][3];
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression"; list[0][2] = "numeric_expression";
    }
    else if (func.equals("QUOTENAME"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "nvarchar(129)"; list[0][1] = "'character_string'";
      list[1][0] = "nvarchar(129)"; list[1][1] = "'character_string'"; list[1][2] = "'quote_character'";
    }
    else if (func.equals("RADIANS"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("RAND"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "float";
      list[1][0] = "float"; list[1][1] = "integer_expression";
    }
    else if (func.equals("REPLACE"))
    {
      list = new Object[1][4];
      list[0][0] = "character_data"; list[0][1] = "string_expression1"; list[0][2] = "string_expression2"; list[0][3] = "string_expression3";
    }
    else if (func.equals("REPLICATE"))
    {
      list = new Object[1][3];
      list[0][0] = "varchar"; list[0][1] = "character_expression"; list[0][2] = "integer_expression";
    }
    else if (func.equals("REVERSE"))
    {
      list[0][0] = "varchar"; list[0][1] = "character_expression";
    }
    else if (func.equals("RIGHT"))
    {
      list = new Object[1][3];
      list[0][0] = "varchar"; list[0][1] = "character_expression"; list[0][2] = "integer_expression";
    }
    else if (func.equals("ROUND"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];

      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression"; list[0][2] = "length";
      list[1][0] = "numeric_expression"; list[1][1] = "numeric_expression"; list[1][2] = "length"; list[1][3] = "function";
    }
    else if (func.equals("RTRIM"))
    {
      list[0][0] = "varchar"; list[0][1] = "character_expression";
    }
    else if (func.equals("SESSION_USER"))  // no brackets
    {
      list = new Object[1][1];
      list[0][0] = "nchar";
    }
    else if (func.equals("SIGN"))
    {
      list[0][0] = "float"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("SIN"))
    {
      list[0][0] = "float"; list[0][1] = "float_expression";
    }
    else if (func.equals("SOUNDEX"))
    {
      list[0][0] = "char"; list[0][1] = "character_expression";
    }
    else if (func.equals("SPACE"))
    {
      list[0][0] = "char"; list[0][1] = "integer_expression";
    }
    else if (func.equals("SQRT"))
    {
      list[0][0] = "float"; list[0][1] = "float_expression";
    }
    else if (func.equals("SQUARE"))
    {
      list[0][0] = "float"; list[0][1] = "float_expression";
    }
    else if (func.equals("STATS_DATE"))
    {
      list = new Object[1][3];
      list[0][0] = "datetime"; list[0][1] = "table_id"; list[0][2] = "index_id";
    }
    else if (func.equals("STDEV"))
    {
      list[0][0] = "float"; list[0][1] = "expression";
    }
    else if (func.equals("STDEVP"))
    {
      list[0][0] = "float"; list[0][1] = "expression";
    }
    else if (func.equals("STR"))
    {
      list = new Object[3][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[2] = new Object[4];

      list[0][0] = "char"; list[0][1] = "float_expression";
      list[1][0] = "char"; list[1][1] = "float_expression"; list[1][2] = "length";
      list[2][0] = "char"; list[2][1] = "float_expression"; list[2][2] = "length"; list[2][3] = "decimal";
    }
    else if (func.equals("STUFF"))
    {
      list = new Object[2][5];
      list[0][0] = "character_data"; list[0][1] = "character_expression"; list[0][2] = "start"; list[0][3] = "length"; list[0][4] = "character_expression";
      list[1][0] = "binary_data"; list[1][1] = "binary_expression"; list[1][2] = "start"; list[1][3] = "length"; list[1][4] = "binary_expression";
    }
    else if (func.equals("SUBSTRING"))
    {
      list = new Object[1][4];
      list[0][0] = "expression"; list[0][1] = "expression"; list[0][2] = "start"; list[0][3] = "length";
    }
    else if (func.equals("SUM")) // DISTINCT | ALL
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("SUSER_ID"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "int";
      list[1][0] = "int"; list[1][1] = "'login'";
    }
    else if (func.equals("SUSER_NAME"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "nchar";
      list[1][0] = "nchar"; list[1][1] = "server_user_id";
    }
    else if (func.equals("SUSER_SID"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "int";
      list[1][0] = "int"; list[1][1] = "'login'";
    }
    else if (func.equals("SUSER_SNAME"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "nchar";
      list[1][0] = "nchar"; list[1][1] = "server_user_id";
    }
    else if (func.equals("SYSTEM_USER"))  // no brackets
    {
      list = new Object[1][1];
      list[0][0] = "char";
    }
    else if (func.equals("TAN"))
    {
      list[0][0] = "float"; list[0][1] = "float_expression";
    }
    else if (func.equals("TEXTPTR"))
    {
      list[0][0] = "varbinary"; list[0][1] = "column";
    }
    else if (func.equals("TEXTVALID"))
    {
      list = new Object[1][3];
      list[0][0] = "int"; list[0][1] = "'table.column'"; list[0][2] = "text_ptr";
    }
    else if (func.equals("TYPEPROPERTY"))  // property is fixed
    {
      list = new Object[1][3];
      list[0][0] = "int"; list[0][1] = "type"; list[0][2] = "property";
    }
    else if (func.equals("UNICODE"))
    {
      list[0][0] = "int"; list[0][1] = "'ncharacter_expression'";
    }
    else if (func.equals("UPPER"))
    {
      list[0][0] = "varchar"; list[0][1] = "character_expression";
    }
    else if (func.equals("USER")) // no brackets
    {
      list = new Object[1][1];
      list[0][0] = "char";
    }
    else if (func.equals("USER_ID"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "int";
      list[1][0] = "int"; list[1][1] = "'user'";
    }
    else if (func.equals("USER_NAME"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "nchar";
      list[1][0] = "nchar"; list[1][1] = "id";
    }
    else if (func.equals("VAR"))
    {
      list[0][0] = "float"; list[0][1] = "expression";
    }
    else if (func.equals("VARP"))
    {
      list[0][0] = "float"; list[0][1] = "expression";
    }
    else if (func.equals("YEAR"))
    {
      list[0][0] = "int"; list[0][1] = "date";
    }
    else if (func.equals("@@CONNECTIONS") ||
             func.equals("@@CPU_BUSY") ||
             func.equals("@@CURSOR_ROWS") ||
             func.equals("@@ERROR") ||
             func.equals("@@FETCH_STATUS") ||
             func.equals("@@IDLE") ||
             func.equals("@@IO_BUSY") ||
             func.equals("@@LOCK_TIMEOUT") ||
             func.equals("@@MAX_CONNECTIONS") ||
             func.equals("@@NESTLEVEL") ||
             func.equals("@@OPTIONS") ||
             func.equals("@@PACKET_ERRORS") ||
             func.equals("@@PACK_RECEIVED") ||
             func.equals("@@PACK_SENT") ||
             func.equals("@@PROCID") ||
             func.equals("@@ROWCOUNT") ||
             func.equals("@@TEXTSIZE") ||
             func.equals("@@TIMETICKS") ||
             func.equals("@@TOTAL_ERRORS") ||
             func.equals("@@TOTAL_READ") ||
             func.equals("@@TOTAL_WRITE") ||
             func.equals("@@TRANCOUNT"))
    {
      list = new Object[1][1];
      list[0][0] = "int";
    }
    else if (func.equals("@@DATEFIRST") ||
             func.equals("@@MAX_PRECISION"))
    {
      list = new Object[1][1];
      list[0][0] = "tinyint";
    }
    else if (func.equals("@@DBTS"))
    {
      list = new Object[1][1];
      list[0][0] = "varbinary";
    }
    else if (func.equals("@@IDENTITY"))
    {
      list = new Object[1][1];
      list[0][0] = "numeric";
    }
    else if (func.equals("@@LANGID") ||
             func.equals("@@SPID"))
    {
      list = new Object[1][1];
      list[0][0] = "smallint";
    }
    else if (func.equals("@@LANGUAGE") ||
             func.equals("@@SERVERNAME") ||
             func.equals("@@SERVICENAME") ||
             func.equals("@@VERSION"))
    {
      list = new Object[1][1];
      list[0][0] = "nvarchar";
    }
    else if (func.equals("@@REMSERVER"))
    {
      list = new Object[1][1];
      list[0][0] = "nvarchar(128)";
    }
    else
    {
    	list = UDFNamesAndSignatures.getUDFParams(func);
    }
    return list;
  } // end getParms

  public static Object[][] getParameterFormats(String functionName)
  {
    return getParms(functionName);
  }

  public static void fillCategoryCombo(Combo functionsCategoryCombo, boolean isColumn)
  {
    if (!isColumn)
    {
      functionsCategoryCombo.add(msSQLServerAll);
      functionsCategoryCombo.add(msSQLServerAggregate);
      functionsCategoryCombo.add(msSQLServerConfiguration);
      functionsCategoryCombo.add(msSQLServerCursor);
      functionsCategoryCombo.add(msSQLServerDateAndTime);
      functionsCategoryCombo.add(msSQLServerMath);
      functionsCategoryCombo.add(msSQLServerMetadata);
      functionsCategoryCombo.add(msSQLServerSecurity);
      functionsCategoryCombo.add(msSQLServerString);
      functionsCategoryCombo.add(msSQLServerSystem);
      functionsCategoryCombo.add(msSQLServerSystemStatistical);
      functionsCategoryCombo.add(msSQLServerTextAndImage);
      functionsCategoryCombo.add(msSQLServerRowset);
      functionsCategoryCombo.add(dbUDF);
    }
    else
    {
      functionsCategoryCombo.add(msSQLServerAll);
      functionsCategoryCombo.add(msSQLServerAggregate);
      functionsCategoryCombo.add(msSQLServerConfiguration);
      functionsCategoryCombo.add(msSQLServerCursor);
      functionsCategoryCombo.add(msSQLServerDateAndTime);
      functionsCategoryCombo.add(msSQLServerMath);
      functionsCategoryCombo.add(msSQLServerMetadata);
      functionsCategoryCombo.add(msSQLServerSecurity);
      functionsCategoryCombo.add(msSQLServerString);
      functionsCategoryCombo.add(msSQLServerSystem);
      functionsCategoryCombo.add(msSQLServerSystemStatistical);
      functionsCategoryCombo.add(msSQLServerTextAndImage);
      functionsCategoryCombo.add(msSQLServerRowset);
      functionsCategoryCombo.add(dbUDF);
    }
  }

  // Set up superclass for the various vendor function names and signatures
  public static boolean isFunctionSupportingStar(String s)
  {
    List funcList = Arrays.asList(functionsSupportingStar);
    if (funcList.contains(s))
    {
      return true;
    }
    return false;
  }

  public static boolean requiresNoBrackets(String functionName)
  {
    List funcList = Arrays.asList(noBracketFunctions);
    if (funcList.contains(functionName))
    {
      return true;
    }
    return false;
  }

  public static boolean isNotSupported(String functionName)
  {
    List funcList = Arrays.asList(unsupportedFunctions);
    if (funcList.contains(functionName))
    {
      return true;
    }
    return false;
  }

  public static String[] getFunctionList(String category, boolean isColumn, SQLDomainModel domainModel)
  {
    if (category.equals(msSQLServerAll))
    {
      if (!isColumn)
      {
        return UDFNamesAndSignatures.mergeTwoArrays(allFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
      }
      
      return UDFNamesAndSignatures.mergeTwoArrays(allColumnSupportedFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
    }
    else if (category.equals(msSQLServerAggregate))
    {
      return aggregateFunctions;
    }
    else if (category.equals(msSQLServerConfiguration))
    {
      return configurationFunctions;
    }
    else if (category.equals(msSQLServerCursor))
    {
      return cursorFunctions;
    }
    else if (category.equals(msSQLServerDateAndTime))
    {
      return dateAndTimeFunctions;
    }
    else if (category.equals(msSQLServerMath))
    {
      return mathFunctions;
    }
    else if (category.equals(msSQLServerMetadata))
    {
      return metadataFunctions;
    }
    else if (category.equals(msSQLServerSecurity))
    {
      return securityFunctions;
    }
    else if (category.equals(msSQLServerString))
    {
      return stringFunctions;
    }
    else if (category.equals(msSQLServerSystem))
    {
      return systemFunctions;
    }
    else if (category.equals(msSQLServerSystemStatistical))
    {
      return systemStatisticalFunctions;
    }
    else if (category.equals(msSQLServerTextAndImage))
    {
      return textAndImageFunctions;
    }
    else if (category.equals(msSQLServerRowset))
    {
      return rowsetFunctions;
    }
    else if (category.equals(dbUDF))
    {
      return UDFNamesAndSignatures.getUDFNames(domainModel);
    }
    return UDFNamesAndSignatures.mergeTwoArrays(allFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
  }
}
