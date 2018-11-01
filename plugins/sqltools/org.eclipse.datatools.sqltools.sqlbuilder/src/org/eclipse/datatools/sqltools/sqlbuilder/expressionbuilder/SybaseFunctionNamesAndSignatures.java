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


public class SybaseFunctionNamesAndSignatures
{

  public static final String sybaseAll = Messages._UI_FCN_ALL;
  public static final String sybaseSystem = Messages._UI_FCN_SYSTEM;
  public static final String sybaseString = Messages._UI_FCN_STRING;
  public static final String sybaseText = Messages._UI_FCN_TEXT;
  public static final String sybaseAggregate = Messages._UI_FCN_AGGREGATE;
  public static final String sybaseMath = Messages._UI_FCN_MATH;
  public static final String sybaseDate = Messages._UI_FCN_DATE;
  public static final String sybaseConversion = Messages._UI_FCN_CONVERSION;
  public static final String sybaseSecurity = Messages._UI_FCN_SECURITY;
  public static String dbUDF = Messages._UI_FCN_UDF;

  private static final String allFunctions[] =
  {
    "ABS",
    "ACOS",
    "ASCII",
    "ASIN",
    "ATAN",
    "ATN2",
    "AVG",
    "CEILING",
    "CHAR",
    "CHARINDEX",
    "CHAR_LENGTH",
    "COL_LENGTH",
    "COL_NAME",
    "COMPARE",
    "CONVERT",
    "COS",
    "COT",
    "COUNT",
    "CURUNRESERVEDPGS",
    "DATA_PGS",
    "DATALENGTH",
    "DATEADD",
    "DATEDIFF",
    "DATENAME",
    "DATEPART",
    "DB_ID",
    "DB_NAME",
    "DEGREES",
    "DIFFERENCE",
    "EXP",
    "FLOOR",
    "GETDATE",
    "HEXTOINT",
    "HOST_ID",
    "HOST_NAME",
    "INDEX_COL",
    "INDEX_COLORDER",
    "INTTOHEX",
    "ISNULL",
    "IS_SEC_SERVICE_ON",
    "LCT_ADMIN",
    "LICENSE_ENABLED",
    "LOG",
    "LOG10",
    "LOWER",
    "LTRIM",
    "MAX",
    "MIN",
    "MUT_EXCL_ROLES",
    "OBJECT_ID",
    "OBJECT_NAME",
    "PATINDEX",
    "PI",
    "POWER",
    "PROC_ROLE",
    "PTN_DATA_PGS",
    "RADIANS",
    "RAND",
    "REPLICATE",
    "RESERVED_PGS",
    "REVERSE",
    "RIGHT",
    "ROLE_CONTAIN",
    "ROLE_ID",
    "ROLE_NAME",
    "ROUND",
    "ROWCNT",
    "RTRIM",
    "SHOW_ROLE",
    "SHOW_SEC_SERVICES",
    "SIGN",
    "SIN",
    "SORTKEY",
    "SOUNDEX",
    "SPACE",
    "SQRT",
    "STR",
    "STUFF",
    "SUBSTRING",
    "SUM",
    "SUSER_ID",
    "SUSER_NAME",
    "TAN",
    "TEXTPTR",
    "TEXTVALID",
    "TSEQUAL",
    "UPPER",
    "USED_PGS",
    "USER",
    "USER_ID",
    "USER_NAME",
    "VALID_NAME",
    "VALID_USER"
  };

  private static final String allColumnSupportedFunctions[] = allFunctions;

  private static final String functionsSupportingStar[] =
  {
    "COUNT"
  };

  private static final String systemFunctions[] =
  {
    "COL_LENGTH",
    "COL_NAME",
    "CURUNRESERVEDPGS",
    "DATA_PGS",
    "DATALENGTH",
    "DB_ID",
    "DB_NAME",
    "HOST_ID",
    "HOST_NAME",
    "INDEX_COL",
    "ISNULL",
    "LCT_ADMIN",
    "OBJECT_ID",
    "OBJECT_NAME",
    "RESERVED_PGS",
    "ROWCNT",
    "SUSER_ID",
    "SUSER_NAME",
    "TSEQUAL",
    "USED_PGS",
    "USER",
    "USER_ID",
    "USER_NAME",
    "VALID_NAME",
    "VALID_USER"
  };

  private static final String stringFunctions[] =
  {
    "ASCII",
    "CHAR",
    "CHARINDEX",
    "CHAR_LENGTH",
    "DIFFERENCE",
    "LOWER",
    "LTRIM",
    "PATINDEX",
    "REPLICATE",
    "REVERSE",
    "RIGHT",
    "RTRIM",
    "SOUNDEX",
    "SPACE",
    "STR",
    "STUFF",
    "SUBSTRING",
    "UPPER"
  };

  private static final String textFunctions[] =
  {
    "PATINDEX",
    "TEXTPTR",
    "TEXTVALID",
  };

  private static final String aggregateFunctions[] =
  {
    "AVG",
    "COUNT",
    "MAX",
    "MIN",
    "SUM"
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
    "SQRT",
    "TAN"
  };

  private static final String dateFunctions[] =
  {
    "DATEADD",
    "DATEDIFF",
    "DATENAME",
    "DATEPART",
    "GETDATE",
  };

  private static final String dataTypeConversionFunctions[] =
  {
    "CONVERT",
    "HEXTOINT",
    "INTTOHEX"
  };

  private static final String securityFunctions[] =
  {
    "IS_SEC_SERVICE_ON",
    "MUT_EXCL_ROLES",
    "PROC_ROLE",
    "ROLE_CONTAIN",
    "ROLE_ID",
    "ROLE_NAME",
    "SHOW_ROLE",
    "SHOW_SEC_SERVICES",
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
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("ASCII"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "char_expression";
    }
    else if (func.equals("ASIN"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("ATAN"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("ATN2"))
    {
      list = new Object[1][3];
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression"; list[0][2] = "numeric_expression";
    }
    else if (func.equals("AVG")) // DISTINCT, ALL
    {
      list[0][0] = "numeric_expression"; list[0][1] = "expression";
    }
    else if (func.equals("CEILING"))
    {
      list[0][0] = "integer"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("CHAR"))
    {
      list[0][0] = "character"; list[0][1] = "integer";
    }
    else if (func.equals("CHARINDEX"))  // char, varchar, nchar or nvarchar data, binary or varbinary.
    {
      list = new Object[1][3];
      list[0][0] = "integer"; list[0][1] = "expression"; list[0][2] = "expression";
    }
    else if (func.equals("CHAR_LENGTH"))
    {
      list[0][0] = "integer"; list[0][1] = "char_expression";
    }
    else if (func.equals("COL_LENGTH"))
    {
      list = new Object[1][3];
      list[0][0] = "integer"; list[0][1] = "object_name"; list[0][2] = "column_name";
    }
    else if (func.equals("COL_NAME"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];

      list[0][0] = "column_name (string)"; list[0][1] = "object_id (numeric)"; list[0][2] = "column_id (numeric)";
      list[1][0] = "column_name (string)"; list[1][1] = "object_id (numeric)"; list[1][2] = "column_id (numeric)"; list[1][3] = "database_id (numeric)";
    }
    else if (func.equals("COMPARE"))
    {
      list = new Object[3][];
      list[0] = new Object[3];
      list[1] = new Object[4];
      list[2] = new Object[4];

      list[0][0] = "integer"; list[0][1] = "char_expression"; list[0][2] = "char_expression";
      list[1][0] = "integer"; list[1][1] = "char_expression"; list[1][2] = "char_expression"; list[1][3] = "collation_name (string|char)";
      list[2][0] = "integer"; list[2][1] = "object_id (numeric)"; list[2][2] = "column_id (numeric)"; list[2][3] = "collation_ID (integer)";
    }
    else if (func.equals("CONVERT"))  // like cast
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];

      list[0][0] = "datatype"; list[0][1] = "datatype"; list[0][2] = "expression";
      list[1][0] = "datatype"; list[1][1] = "datatype"; list[1][2] = "expression"; list[1][3] = "style";
    }
    else if (func.equals("COS"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("COT"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("COUNT")) // DISTINCT, ALL
    {
      list[0][0] = "numeric_expression"; list[0][1] = "expression";
    }
    else if (func.equals("CURUNRESERVEDPGS"))
    {
      list = new Object[1][4];
      list[0][0] = "integer"; list[0][1] = "dbid"; list[0][2] = "lstart"; list[0][3] = "unreservedpgs";
    }
    else if (func.equals("DATA_PGS"))
    {
      list = new Object[2][3];
      list[0][0] = "integer"; list[0][1] = "object_id"; list[0][2] = "data_oam_pg_id";
      list[1][0] = "integer"; list[1][1] = "object_id"; list[1][2] = "index_oam_pg_id";
    }
    else if (func.equals("DATALENGTH"))
    {
      list[0][0] = "length (in bytes)"; list[0][1] = "expression";
    }
    else if (func.equals("DATEADD"))
    {
      list = new Object[1][4];
      list[0][0] = "date"; list[0][1] = "date_part"; list[0][2] = "integer"; list[0][3] = "date";
    }
    else if (func.equals("DATEDIFF"))
    {
      list = new Object[1][4];
      list[0][0] = "integer"; list[0][1] = "date_part"; list[0][2] = "date"; list[0][3] = "date";
    }
    else if (func.equals("DATENAME"))
    {
      list = new Object[1][3];
      list[0][0] = "string"; list[0][1] = "date_part"; list[0][2] = "date";
    }
    else if (func.equals("DATEPART"))
    {
      list = new Object[1][3];
      list[0][0] = "integer"; list[0][1] = "date_part"; list[0][2] = "date";
    }
    else if (func.equals("DB_ID"))
    {
      list[0][0] = "integer"; list[0][1] = "database_name (character_expression)";
    }
    else if (func.equals("DB_NAME"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "string";
      list[1][0] = "string"; list[1][1] = "database_id (numeric_expression)";
    }
    else if (func.equals("DEGREES"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "nuneric_expression";
    }
    else if (func.equals("DIFFERENCE"))
    {
      list = new Object[1][3];
      list[0][0] = "integer"; list[0][1] = "char_expression"; list[0][2] = "char_expression";
    }
    else if (func.equals("EXP")) // float, real, or double precision
    {
      list[0][0] = "numeric_expression"; list[0][1] = "approx_numeric";
    }
    else if (func.equals("FLOOR"))
    {
      list = new Object[3][2];
      list[0][0] = "integer"; list[0][1] = "exact_numeric_expression";
      list[1][0] = "integer"; list[1][1] = "approx_numeric_expression";
      list[2][0] = "integer"; list[2][1] = "money_expression";
    }
    else if (func.equals("GETDATE"))
    {
      list = new Object[1][1];
      list[0][0] = "date";
    }
    else if (func.equals("HEXTOINT"))
    {
      list[0][0] = "integer"; list[0][1] = "hexadecimal_string";
    }
    else if (func.equals("HOST_ID"))
    {
      list = new Object[1][1];
      list[0][0] = "process_ID";
    }
    else if (func.equals("HOST_NAME"))
    {
      list = new Object[1][1];
      list[0][0] = "string";
    }
    else if (func.equals("INDEX_COL"))
    {
      list = new Object[2][];
      list[0] = new Object[4];
      list[1] = new Object[5];

      list[0][0] = "string"; list[0][1] = "object_name"; list[0][2] = "index_id"; list[0][3] = "key_#";
      list[1][0] = "string"; list[1][1] = "object_name"; list[1][2] = "index_id"; list[1][3] = "key_#"; list[1][4] = "user_id";
    }
    else if (func.equals("INDEX_COLORDER"))
    {
      list = new Object[2][];
      list[0] = new Object[4];
      list[1] = new Object[5];

      list[0][0] = "string"; list[0][1] = "object_name"; list[0][2] = "index_id"; list[0][3] = "key_#";
      list[1][0] = "string"; list[1][1] = "object_name"; list[1][2] = "index_id"; list[1][3] = "key_#"; list[1][4] = "user_id";
    }
    else if (func.equals("INTTOHEX"))
    {
      list[0][0] = "hexadecimal_string"; list[0][1] = "integer_expression";
    }
    else if (func.equals("ISNULL"))
    {
      list = new Object[1][3];
      list[0][0] = ""; list[0][1] = "expression"; list[0][2] = "expression";
    }
    else if (func.equals("IS_SEC_SERVICE_ON"))
    {
      list[0][0] = "integer"; list[0][1] = "security_service_nm";
    }
    else if (func.equals("LCT_ADMIN"))
    {
      list = new Object[4][];
      list[0] = new Object[3];
      list[1] = new Object[3];
      list[2] = new Object[3];
      list[3] = new Object[4];

      list[0][0] = "threshold"; list[0][1] = "\"lastchance\" | \"logfull\""; list[0][2] = "database_id";
      list[1][0] = "threshold"; list[1][1] = "\"reserve\""; list[1][2] = "log_pages | 0";
      list[2][0] = "threshold"; list[2][1] = "\"abort\""; list[1][2] = "process_id";
      list[3][0] = "threshold"; list[3][1] = "\"abort\""; list[3][2] = "process_id"; list[3][3] = "database_id";
    }
    else if (func.equals("LICENSE_ENABLED"))
    {
      list = new Object[5][2];
      list[0][0] = "integer"; list[0][1] = "\"ase_server\"";
      list[1][0] = "integer"; list[1][1] = "\"ase_ha\"";
      list[2][0] = "integer"; list[2][1] = "\"ase_dtm\"";
      list[3][0] = "integer"; list[3][1] = "\"ase_java\"";
      list[4][0] = "integer"; list[4][1] = "\"ase_asm\"";
    }
    else if (func.equals("LOG"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "approx_numeric";
    }
    else if (func.equals("LOG10"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "approx_numeric";
    }
    else if (func.equals("LOWER"))
    {
      list[0][0] = "char_expression"; list[0][1] = "char_expression";
    }
    else if (func.equals("LTRIM"))
    {
      list[0][0] = "char_expression"; list[0][1] = "char_expression";
    }
    else if (func.equals("MAX"))
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("MIN"))
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("MUT_EXCL_ROLES"))
    {
      list = new Object[1][4];
      list[0][0] = "integer"; list[0][1] = "role1 (string)"; list[0][2] = "role2 (string)"; list[0][3] = "level (\"membership\" | \"activation\")";
    }
    else if (func.equals("OBJECT_ID"))
    {
      list[0][0] = "object ID"; list[0][1] = "object_name";
    }
    else if (func.equals("OBJECT_NAME"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "object name"; list[0][1] = "object_name";
      list[1][0] = "object name"; list[1][1] = "object_name"; list[1][2] = "database_id";
    }
    else if (func.equals("PATINDEX")) // using
    {
      list = new Object[4][];
      list[0] = new Object[3];
      list[1] = new Object[4];
      list[2] = new Object[4];
      list[3] = new Object[4];

      list[0][0] = "offset"; list[0][1] = "\"%pattern%\""; list[0][2] = "char_expression";
      list[1][0] = "offset"; list[1][1] = "\"%pattern%\""; list[1][2] = "char_expression"; list[1][3] = "using bytes";
      list[2][0] = "offset"; list[2][1] = "\"%pattern%\""; list[2][2] = "char_expression"; list[2][3] = "using chars";
      list[3][0] = "offset"; list[3][1] = "\"%pattern%\""; list[3][2] = "char_expression"; list[3][3] = "using characters";
    }
    else if (func.equals("PI"))
    {
      list = new Object[1][1];
      list[0][0] = "numeric";
    }
    else if (func.equals("POWER"))
    {
      list = new Object[1][3];
      list[0][0] = "numeric"; list[0][1] = "value"; list[0][2] = "power";
    }
    else if (func.equals("PROC_ROLE"))
    {
      list[0][0] = "integer"; list[0][1] = "role_name";
    }
    else if (func.equals("PTN_DATA_PGS"))
    {
      list = new Object[1][3];
      list[0][0] = "integer"; list[0][1] = "object_id"; list[0][2] = "partition_id";
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

      list[0][0] = "numeric";
      list[1][0] = "numeric"; list[1][1] = "integer";
    }
    else if (func.equals("REPLICATE"))
    {
      list = new Object[1][3];
      list[0][0] = "string"; list[0][1] = "char_expr"; list[0][2] = "integer_expr";
    }
    else if (func.equals("RESERVED_PGS"))
    {
      list = new Object[2][3];
      list[0][0] = "integer"; list[0][1] = "object_id"; list[0][2] = "doampg";
      list[1][0] = "integer"; list[1][1] = "object_id"; list[1][2] = "ioampg";
    }
    else if (func.equals("REVERSE"))
    {
      list[0][0] = "string"; list[0][1] = "expression";
    }
    else if (func.equals("RIGHT"))
    {
      list = new Object[1][3];
      list[0][0] = "expression"; list[0][1] = "expression"; list[0][2] = "integer_expr";
    }
    else if (func.equals("ROLE_CONTAIN"))
    {
      list = new Object[1][3];
      list[0][0] = "integer"; list[0][1] = "\"role1\""; list[0][2] = "\"role2\"";
    }
    else if (func.equals("ROLE_ID"))
    {
      list[0][0] = "srid"; list[0][1] = "\"role_name\"";
    }
    else if (func.equals("ROLE_NAME"))
    {
      list[0][0] = "role name"; list[0][1] = "role_id";
    }
    else if (func.equals("ROUND"))
    {
      list = new Object[1][3];
      list[0][0] = "numeric"; list[0][1] = "numeric"; list[0][2] = "decimal_places (integer)";
    }
    else if (func.equals("ROWCNT"))
    {
      list[0][0] = "integer"; list[0][1] = "sysindexes.doampg";
    }
    else if (func.equals("RTRIM"))
    {
      list[0][0] = "char_expression"; list[0][1] = "char_expression";
    }
    else if (func.equals("SHOW_ROLE"))
    {
      list = new Object[1][1];
      list[0][0] = "roles";
    }
    else if (func.equals("SHOW_SEC_SERVICES"))
    {
      list = new Object[1][1];
      list[0][0] = "security services";
    }
    else if (func.equals("SIGN"))
    {
      list[0][0] = "sign (integer)"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("SIN"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("SORTKEY"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "varbinary"; list[0][1] = "char_expression";
      list[1][0] = "varbinary"; list[1][1] = "char_expression"; list[1][2] = "collation (name | ID)";
    }
    else if (func.equals("SOUNDEX"))  // ?
    {
      list[0][0] = "char_expression"; list[0][1] = "char_expression";
    }
    else if (func.equals("SPACE"))
    {
      list[0][0] = "string"; list[0][1] = "integer_expression";
    }
    else if (func.equals("SQRT"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("STR"))
    {
      list = new Object[3][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[2] = new Object[4];

      list[0][0] = "char_expression"; list[0][1] = "approx_numeric";
      list[1][0] = "char_expression"; list[1][1] = "approx_numeric"; list[1][2] = "length";
      list[2][0] = "char_expression"; list[2][1] = "approx_numeric"; list[2][2] = "length"; list[2][3] = "decimal";
    }
    else if (func.equals("STUFF"))
    {
      list = new Object[1][5];
      list[0][0] = "string"; list[0][1] = "char_expression"; list[0][2] = "start"; list[0][3] = "length"; list[0][4] = "char_expression";
    }
    else if (func.equals("SUBSTRING"))
    {
      list = new Object[1][4];
      list[0][0] = "string"; list[0][1] = "expression"; list[0][2] = "start"; list[0][3] = "length";
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

      list[0][0] = "user ID";
      list[1][0] = "user ID"; list[1][1] = "server_user_name";
    }
    else if (func.equals("SUSER_NAME"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "user name";
      list[1][0] = "user name"; list[1][1] = "server_user_id";
    }
    else if (func.equals("TAN"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("TEXTPTR"))
    {
      list[0][0] = "varbinary ptr"; list[0][1] = "column_name";
    }
    else if (func.equals("TEXTVALID"))
    {
      list = new Object[1][3];
      list[0][0] = "integer"; list[0][1] = "column_name"; list[0][2] = "textpointer";
    }
    else if (func.equals("TSEQUAL"))
    {
      list = new Object[1][3];
      list[0][0] = ""; list[0][1] = "browsed_row_timestamp"; list[0][2] = "stored_row_timestamp";
    }
    else if (func.equals("UPPER"))
    {
      list[0][0] = "char_expression"; list[0][1] = "char_expression";
    }
    else if (func.equals("USED_PGS"))
    {
      list = new Object[1][4];
      list[0][0] = "integer"; list[0][1] = "object_id"; list[0][2] = "doampg"; list[0][3] = "ioampg";
    }
    else if (func.equals("USER"))
    {
      list = new Object[1][1];
      list[0][0] = "user name";
    }
    else if (func.equals("USER_ID"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "user ID";
      list[1][0] = "user ID"; list[1][1] = "user_name";
    }
    else if (func.equals("USER_NAME"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "user name";
      list[1][0] = "user name"; list[1][1] = "user_id";
    }
    else if (func.equals("VALID_NAME"))
    {
      list[0][0] = "integer"; list[0][1] = "char_expression";
    }
    else if (func.equals("VALID_USER"))
    {
      list[0][0] = "integer"; list[0][1] = "server_user_id";
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
      functionsCategoryCombo.add(sybaseAll);
      functionsCategoryCombo.add(sybaseAggregate);
      functionsCategoryCombo.add(sybaseConversion);
      functionsCategoryCombo.add(sybaseDate);
      functionsCategoryCombo.add(sybaseMath);
      functionsCategoryCombo.add(sybaseSecurity);
      functionsCategoryCombo.add(sybaseString);
      functionsCategoryCombo.add(sybaseSystem);
      functionsCategoryCombo.add(sybaseText);
      functionsCategoryCombo.add(dbUDF);
    }
    else  // need to filter out the valid. For now, allow all.
    {
      functionsCategoryCombo.add(sybaseAll);
      functionsCategoryCombo.add(sybaseAggregate);
      functionsCategoryCombo.add(sybaseConversion);
      functionsCategoryCombo.add(sybaseDate);
      functionsCategoryCombo.add(sybaseMath);
      functionsCategoryCombo.add(sybaseSecurity);
      functionsCategoryCombo.add(sybaseString);
      functionsCategoryCombo.add(sybaseSystem);
      functionsCategoryCombo.add(sybaseText);
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

  public static String[] getFunctionList(String category, boolean isColumn, SQLDomainModel domainModel)
  {
    if (category.equals(sybaseAll))
    {
      if (!isColumn)
      {
        return UDFNamesAndSignatures.mergeTwoArrays(allFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
      }

      return UDFNamesAndSignatures.mergeTwoArrays(allColumnSupportedFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
    }
    else if (category.equals(sybaseSystem))
    {
      return systemFunctions;
    }
    else if (category.equals(sybaseString))
    {
      return stringFunctions;
    }
    else if (category.equals(sybaseText))
    {
      return textFunctions;
    }
    else if (category.equals(sybaseAggregate))
    {
      return aggregateFunctions;
    }
    else if (category.equals(sybaseMath))
    {
      return mathFunctions;
    }
    else if (category.equals(sybaseDate))
    {
      return dateFunctions;
    }
    else if (category.equals(sybaseConversion))
    {
      return dataTypeConversionFunctions;
    }
    else if (category.equals(sybaseSecurity))
    {
      return securityFunctions;
    }
    else if (category.equals(dbUDF))
    {
      return UDFNamesAndSignatures.getUDFNames(domainModel);
    }
    return UDFNamesAndSignatures.mergeTwoArrays(allFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
  }
}
