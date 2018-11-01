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


public class MySQLFunctionNamesAndSignatures
{

  public static final String mySQLAll = Messages._UI_FCN_ALL;
  public static final String mySQLString = Messages._UI_FCN_STRING;
  public static final String mySQLControlFlow = Messages._UI_FCN_CONTROL_FLOW;
  public static final String mySQLMath = Messages._UI_FCN_MATH;
  public static final String mySQLBit = Messages._UI_FCN_BIT;
  public static final String mySQLDateAndTime = Messages._UI_FCN_DATE_TIME;
  public static final String mySQLMiscellaneous = Messages._UI_FCN_MISCELLANEOUS;
  public static final String mySQLAggregate = Messages._UI_FCN_AGGREGATE;
  public static final String mySQLNotSupported = Messages._UI_FCN_NOT_SUPPORTED;
  public static String dbUDF = Messages._UI_FCN_UDF;

  private static final String allFunctions[] =
  {
    "ABS",
    "ACOS",
    "ADDDATE",
    "ASCII",
    "ASIN",
    "ATAN",
    "ATAN2",
    "AVG",
    "BENCHMARK",
    "BIN",
    "BIT_AND",
    "BIT_COUNT",
    "BIT_OR",
    "CEILING",
    "CHAR",
    "CHAR_LENGTH",
    "CHARACTER_LENGTH",
    "COALESCE",
    "CONCAT",
    "CONCAT_WS",
    "CONNECTION_ID",
    "CONV",
    "COS",
    "COT",
    "COUNT",
    "CURDATE",
    "CURRENT_DATE",
    "CURRENT_TIME",
    "CURRENT_TIMESTAMP",
    "CURTIME",
    "DATABASE",
    "DATE_ADD",
    "DATE_FORMAT",
    "DATE_SUB",
    "DAYNAME",
    "DAYOFMONTH",
    "DAYOFWEEK",
    "DAYOFYEAR",
    "DECODE",
    "DEGREES",
    "ELT",
    "ENCODE",
    "ENCRYPT",
    "EXP",
    "EXPORT_SET",
    "FIELD",
    "FIND_IN_SET",
    "FLOOR",
    "FORMAT",
    "FROM_DAYS",
    "FROM_UNIXTIME",
    "GET_LOCK",
    "GREATEST",
    "HEX",
    "HOUR",
    "IF",
    "IFNULL",
    "INET_ATON",
    "INET_NTOA",
    "INSERT",
    "INSTR",
    "INTERVAL",
    "ISNULL",
    "LAST_INSERT_ID",
    "LCASE",
    "LEAST",
    "LEFT",
    "LENGTH",
    "LOAD_FILE",
    "LOCATE",
    "LOG",
    "LOG10",
    "LOWER",
    "LPAD",
    "LTRIM",
    "MAKE_SET",
    "MASTER_POS_WAIT",
    // "MATCH ... AGAINST", // not supported now
    "MAX",
    "MD5",
    "MID",
    "MIN",
    "MINUTE",
    "MOD",
    "MONTH",
    "MONTHNAME",
    "NOW",
    "NULLIF",
    "OCT",
    "OCTET_LENGTH",
    "ORD",
    "PASSWORD",
    "PERIOD_ADD",
    "PERIOD_DIFF",
    "PI",
    "POSITION",
    "POW",
    "POWER",
    "QUARTER",
    "RADIANS",
    "RAND",
    "RELEASE_LOCK",
    "REPEAT",
    "REPLACE",
    "REVERSE",
    "RIGHT",
    "ROUND",
    "RPAD",
    "RTRIM",
    "SEC_TO_TIME",
    "SECOND",
    "SESSION_USER",
    "SIGN",
    "SIN",
    "SOUNDEX",
    "SPACE",
    "SQRT",
    "STD",
    "STDDEV",
    "STRCMP",
    "SUBDATE",
    "SUBSTRING",
    "SUBSTRING_INDEX",
    "SUM",
    "SYSDATE",
    "SYSTEM_USER",
    "TAN",
    "TIME_FORMAT",
    "TIME_TO_SEC",
    "TO_DAYS",
    "TRIM",
    "TRUNCATE",
    "UCASE",
    "UNIX_TIMESTAMP",
    "UPPER",
    "USER",
    "VERSION",
    "WEEK",
    "WEEKDAY",
    "YEAR",
    "YEARWEEK"
  };

  private static final String allColumnSupportedFunctions[] = allFunctions;

  private static final String unsupportedFunctions[] =
  {
    "CHAR",
    "COALESCE",
    "ELT",
    "FIELD",
    "GREATEST",
    "INTERVAL",
    "LEAST",
    "MAKE_SET",
    "MASTER_POS_WAIT",
    // "MATCH ... AGAINST",
    "POSITION"
  };

  private static final String functionsSupportingStar[] =
  {
    "COUNT"
  };

  private static final String noBracketFunctions[] =
  {
    "CURRENT_DATE",
    "CURRENT_TIME",
    "CURRENT_TIMESTAMP"
  };

  private static final String bitFunctions[] =
  {
    "BIT_COUNT"
  };

  private static final String stringFunctions[] =
  {
    "ASCII",             // ASCII(str)
    "BIN",               // BIN(N)
    "CHAR",              // CHAR(N,...)
    "CHAR_LENGTH",       // CHAR_LENGTH(str)
    "CHARACTER_LENGTH",  // CHARACTER_LENGTH(str)
    "CONCAT",            // CONCAT(str1, str2, ...)
    "CONCAT_WS",         // CONCAT_WS(separator, str1, str2, ...)
    "CONV",              // CONV(N, from_base, to_base)
    "ELT",
    "EXPORT_SET",
    "FIELD",
    "FIND_IN_SET",
    "HEX",      // HEX(BIGINT)
    "INSERT",
    "INSTR",   // INSTR(str, substr)
    "LCASE",
    "LEFT",     // LEFT(str, len)
    "LENGTH",   // LENGTH(str)
    "LOAD_FILE",
    "LOCATE",   // LOCATE(substr, str)  LOCATE(substr, str, pos)
    "LOWER",
    "LPAD",     // LPAD(str,len,padstr)
    "LTRIM",
    "MAKE_SET",
    //"MATCH ... AGAINST", // not supported now
    "MID",        // MID(str, pos, len)
    "OCT",            // OCT(longlong)
    "OCTET_LENGTH",   // OCTET_LENGTH(str)
    "ORD",       // ORD(str)
    "POSITION",  // POSITION(substr IN str)
    "REPEAT",
    "REPLACE",
    "REVERSE",
    "RIGHT",     // RIGHT(str, len)
    "RPAD",      // RPAD(str, len, padstr)
    "RTRIM",
    "SOUNDEX",
    "SPACE",
    "STRCMP",
    "SUBSTRING",
    "SUBSTRING_INDEX",
    "TRIM",
    "UCASE",
    "UPPER"
  };

  private static final String controlFlowFunctions[] =
  {
    "IF",
    "IFNULL",
    "NULLIF"
  };

  private static final String mathFunctions[] =
  {
    "ABS",
    "ACOS",
    "ASIN",
    "ATAN",
    "ATAN2",
    "CEILING",
    "COS",
    "COT",
    "DEGREES",
    "EXP",
    "FLOOR",
    "GREATEST",
    "LEAST",
    "LOG",
    "LOG10",
    "MOD",
    "PI",
    "POW",
    "POWER",
    "RADIANS",
    "RAND",
    "ROUND",
    "SIGN",
    "SIN",
    "SQRT",
    "TAN",
    "TRUNCATE"
  };

  private static final String dateAndTimeFunctions[] =
  {
    "ADDDATE",
    "CURDATE",
    "CURRENT_DATE",
    "CURRENT_TIME",
    "CURRENT_TIMESTAMP",
    "CURTIME",
    "DATE_ADD",
    "DATE_FORMAT",
    "DATE_SUB",
    "DAYNAME",
    "DAYOFMONTH",
    "DAYOFWEEK",
    "DAYOFYEAR",
    "FROM_DAYS",
    "FROM_UNIXTIME",
    "HOUR",
    "MINUTE",
    "MONTH",
    "MONTHNAME",
    "NOW",
    "PERIOD_ADD",
    "PERIOD_DIFF",
    "QUARTER",
    "SEC_TO_TIME",
    "SECOND",
    "SUBDATE",
    "SYSDATE",
    "TIME_FORMAT",
    "TIME_TO_SEC",
    "TO_DAYS",
    "UNIX_TIMESTAMP",
    "WEEK",
    "WEEKDAY",
    "YEAR",
    "YEARWEEK"
  };

  private static final String miscFunctions[] =
  {
    "BENCHMARK",
    "COALESCE",
    "CONNECTION_ID",
    "DATABASE",
    "DECODE",
    "ENCODE",
    "ENCRYPT",
    "FORMAT",
    "GET_LOCK",
    "INET_ATON",
    "INET_NTOA",
    "INTERVAL",
    "ISNULL",
    "LAST_INSERT_ID",
    "MASTER_POS_WAIT",
    "MD5",
    "PASSWORD",
    "RELEASE_LOCK",
    "SESSION_USER",
    "SYSTEM_USER",
    "USER",
    "VERSION"
  };

  private static final String aggregateFunctions[] =
  {
    "AVG",
    "BIT_AND",
    "BIT_OR",
    "COUNT",
    "MAX",
    "MIN",
    "STD",
    "STDDEV",
    "SUM"
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
    else if (func.equals("ADDDATE"))
    {
      list[0][0] = "date"; list[0][1] = "INTERVAL expr type";
    }
    else if (func.equals("ASCII"))
    {
      list = new Object[2][2];
      list[0][0] = "integer"; list[0][1] = "string";
      list[1][0] = "integer"; list[1][1] = "string";
    }
    else if (func.equals("ASIN"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("ATAN"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("ATAN2"))
    {
      list = new Object[1][3];
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression"; list[0][2] = "numeric_expression";
    }
    else if (func.equals("AVG")) // DISTINCT, ALL
    {
      list[0][0] = "numeric_expression"; list[0][1] = "expression";
    }
    else if (func.equals("BENCHMARK"))
    {
      list = new Object[1][3];
      list[0][0] = "0"; list[0][1] = "numeric_expression"; list[0][2] = "expression";
    }
    else if (func.equals("BIN"))
    {
      list[0][0] = "string"; list[0][1] = "BIGINT";
    }
    else if (func.equals("BIT_AND"))
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("BIT_COUNT"))
    {
      list[0][0] = "integer"; list[0][1] = "expression";
    }
    else if (func.equals("BIT_OR"))
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("CEILING"))
    {
      list[0][0] = "integer"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("CHAR")) // signature is char(N,...)
    {
      list = new Object[1][1];
      list[0][0] = mySQLNotSupported;
      // list[0][0] = "character"; list[0][1] = "integer";
    }
    else if (func.equals("CHAR_LENGTH") ||
             func.equals("CHARACTER_LENGTH") ||
             func.equals("LENGTH") ||
             func.equals("OCTET_LENGTH"))
    {
      list[0][0] = "integer"; list[0][1] = "char_expression";
    }
    else if (func.equals("COALESCE"))
    {
      list = new Object[1][1];
      list[0][0] = mySQLNotSupported;
    }
    else if (func.equals("CONCAT")) // signature is CONCAT(str1, str2, ...)
    {
      list = new Object[1][3];
      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "string";
    }
    else if (func.equals("CONCAT_WS")) // signature is CONCAT(separator, str1, str2, ...)
    {
      list = new Object[1][4];
      list[0][0] = "string"; list[0][1] = "separator string"; list[0][2] = "string"; list[0][3] = "string";
    }
    else if (func.equals("CONNECTION_ID"))
    {
      list = new Object[1][1];
      list[0][0] = "integer";
    }
    else if (func.equals("CONV"))
    {
      list = new Object[2][4];
      list[0][0] = "string"; list[0][1] = "integer"; list[0][2] = "from_base"; list[0][3] = "to_base";
      list[1][0] = "string"; list[1][1] = "string"; list[1][2] = "from_base"; list[1][3] = "to_base";
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
    else if (func.equals("CURDATE") ||
             func.equals("CURRENT_DATE"))
    {
      list = new Object[1][1];
      list[0][0] = "date";
    }
    else if (func.equals("CURRENT_TIME") ||
             func.equals("CURTIME"))
    {
      list = new Object[1][1];
      list[0][0] = "time";
    }
    else if (func.equals("CURRENT_TIMESTAMP"))
    {
      list = new Object[1][1];
      list[0][0] = "date and time";
    }
    else if (func.equals("DATABASE"))
    {
      list = new Object[1][1];
      list[0][0] = "database name (string)";
    }
    else if (func.equals("DATE_ADD"))
    {
      list[0][0] = "date"; list[0][1] = "INTERVAL expr type";
    }
    else if (func.equals("DATE_FORMAT"))
    {
      list = new Object[1][3];
      list[0][0] = "date"; list[0][1] = "date"; list[0][2] = "format string";
    }
    else if (func.equals("DATE_SUB"))
    {
      list[0][0] = "date"; list[0][1] = "INTERVAL expr type";
    }
    else if (func.equals("DAYNAME"))
    {
      list[0][0] = "string"; list[0][1] = "date";
    }
    else if (func.equals("DAYOFMONTH"))
    {
      list[0][0] = "decimal"; list[0][1] = "date";
    }
    else if (func.equals("DAYOFWEEK"))
    {
      list[0][0] = "decimal"; list[0][1] = "date";
    }
    else if (func.equals("DAYOFYEAR"))
    {
      list[0][0] = "decimal"; list[0][1] = "date";
    }
    else if (func.equals("DECODE"))
    {
      list = new Object[1][3];
      list[0][0] = "string"; list[0][1] = "crypt_str"; list[0][2] = "pass_string";
    }
    else if (func.equals("DEGREES"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "nuneric_expression";
    }
    else if (func.equals("ELT")) // signature is ELT(N, str1, str2, str3,...)
    {
      list = new Object[1][1];
      list[0][0] = mySQLNotSupported;
    }
    else if (func.equals("ENCODE"))
    {
      list = new Object[1][3];
      list[0][0] = "binary string"; list[0][1] = "string"; list[0][2] = "pass_string";
    }
    else if (func.equals("ENCRYPT"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "string"; list[0][1] = "string";
      list[1][0] = "string"; list[1][1] = "string"; list[1][2] = "string";
    }
    else if (func.equals("EXP")) // float, real, or double precision
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("EXPORT_SET"))
    {
      list = new Object[3][];
      list[0] = new Object[4];
      list[1] = new Object[5];
      list[2] = new Object[6];

      list[0][0] = "string"; list[0][1] = "bits"; list[0][2] = "on string"; list[0][3] = "off string";
      list[1][0] = "string"; list[1][1] = "bits"; list[1][2] = "on string"; list[1][3] = "off string"; list[1][4] = "separator string";
      list[2][0] = "string"; list[2][1] = "bits"; list[2][2] = "on string"; list[2][3] = "off string"; list[2][4] = "separator string"; list[2][5] = "number_of_bits";
    }
    else if (func.equals("FIELD")) // signature is FIELD(str,str1,str2,str3,...)
    {
      list = new Object[1][1];
      list[0][0] = mySQLNotSupported;
    }
    else if (func.equals("FIND_IN_SET"))
    {
      list = new Object[1][3];
      list[0][0] = "integer"; list[0][1] = "string"; list[0][2] = "string";
    }
    else if (func.equals("FLOOR"))
    {
      list[0][0] = "integer"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("FORMAT"))
    {
      list = new Object[1][3];
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression"; list[0][2] = "integer";
    }
    else if (func.equals("FROM_DAYS"))
    {
      list[0][0] = "date"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("FROM_UNIXTIME"))
    {
      list = new Object[4][];
      list[0] = new Object[2];
      list[1] = new Object[2];
      list[2] = new Object[3];
      list[3] = new Object[3];

      list[0][0] = "string"; list[0][1] = "unix_timestamp";
      list[1][0] = "numeric"; list[1][1] = "unix_timestamp";
      list[2][0] = "string"; list[2][1] = "unix_timestamp"; list[2][2] = "format";
      list[3][0] = "numeric"; list[3][1] = "unix_timestamp"; list[3][2] = "format";
    }
    else if (func.equals("GET_LOCK"))
    {
      list = new Object[1][3];
      list[0][0] = "integer"; list[0][1] = "string"; list[0][2] = "timeout";
    }
    else if (func.equals("GREATEST"))
    {
      list = new Object[1][1];
      list[0][0] = mySQLNotSupported;
      // list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("HEX"))
    {
      list[0][0] = "string"; list[0][1] = "BIGINT";
    }
    else if (func.equals("HOUR"))
    {
      list[0][0] = "string"; list[0][1] = "TIME";
    }
    else if (func.equals("IF"))
    {
      list = new Object[1][4];
      list[0][0] = "expression"; list[0][1] = "expression"; list[0][2] = "expression"; list[0][3] = "expression";
    }
    else if (func.equals("IFNULL"))
    {
      list = new Object[1][3];
      list[0][0] = "expression"; list[0][1] = "expression"; list[0][2] = "expression";
    }
    else if (func.equals("INET_ATON"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "expression";
    }
    else if (func.equals("INET_NTOA"))
    {
      list[0][0] = "expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("INSERT"))
    {
      list = new Object[1][5];
      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "pos"; list[0][3] = "len"; list[0][4] = "string";
    }
    else if (func.equals("INSTR"))
    {
      list = new Object[1][3];
      list[0][0] = "integer"; list[0][1] = "string"; list[0][2] = "substr";
    }
    else if (func.equals("INTERVAL")) // Signature is INTERVAL(N,N1,N2,N3,...)
    {
      list = new Object[1][1];
      list[0][0] = mySQLNotSupported;
    }
    else if (func.equals("ISNULL"))
    {
      list[0][0] = "integer"; list[0][1] = "expression";
    }
    else if (func.equals("LAST_INSERT_ID"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "expression";
      list[1][0] = "expression"; list[1][1] = "expression";
    }
    else if (func.equals("LCASE") ||
             func.equals("LOWER"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("LEAST")) // Signature is LEAST(X,Y,...)
    {
      list = new Object[1][1];
      list[0][0] = mySQLNotSupported;
      // list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("LEFT"))
    {
      list = new Object[1][3];
      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "len";
    }
    else if (func.equals("LOAD_FILE"))
    {
      list[0][0] = "string"; list[0][1] = "file_name (string)";
    }
    else if (func.equals("LOCATE"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];

      list[0][0] = "integer"; list[0][1] = "substr"; list[0][2] = "string";
      list[1][0] = "integer"; list[1][1] = "substr"; list[1][2] = "string"; list[1][3] = "pos";
    }
    else if (func.equals("LOG"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("LOG10"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("LPAD"))
    {
      list = new Object[1][4];
      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "len"; list[0][3] = "padstring";
    }
    else if (func.equals("LTRIM"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("MAKE_SET")) // Signature is MAKE_SET(bits,str1,str2,...)
    {
      list = new Object[1][1];
      list[0][0] = mySQLNotSupported;
    }
    else if (func.equals("MASTER_POS_WAIT"))
    {
      list = new Object[1][1];
      list[0][0] = mySQLNotSupported;
    }
    else if (func.equals("MAX"))
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("MD5"))
    {
      list[0][0] = "hexadecimal"; list[0][1] = "string";
    }
    else if (func.equals("MID"))
    {
      list = new Object[1][4];
      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "pos"; list[0][3] = "len";
    }
    else if (func.equals("MIN"))
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("MINUTE"))
    {
      list[0][0] = "decimal"; list[0][1] = "time";
    }
    else if (func.equals("MOD"))
    {
      list = new Object[1][3];
      list[0][0] = "numeric"; list[0][1] = "numeric"; list[0][2] = "numeric";
    }
    else if (func.equals("MONTH"))
    {
      list[0][0] = "integer"; list[0][1] = "date";
    }
    else if (func.equals("MONTHNAME"))
    {
      list[0][0] = "string"; list[0][1] = "date";
    }
    else if (func.equals("NOW"))
    {
      list = new Object[1][1];
      list[0][0] = "date and time";
    }
    else if (func.equals("NULLIF"))
    {
      list = new Object[1][3];
      list[0][0] = "expression"; list[0][1] = "expression"; list[0][2] = "expression";
    }
    else if (func.equals("OCT"))
    {
      list[0][0] = "string"; list[0][1] = "longlong";
    }
    else if (func.equals("ORD"))
    {
      list[0][0] = "ASCII code"; list[0][1] = "string";
    }
    else if (func.equals("PASSWORD"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("PERIOD_ADD"))
    {
      list = new Object[1][3];
      list[0][0] = "YYYYMM (numeric)"; list[0][1] = "YYMM | YYYYMM (numeric)"; list[0][2] = "integer";
    }
    else if (func.equals("PERIOD_DIFF"))
    {
      list = new Object[1][3];
      list[0][0] = "decimal"; list[0][1] = "YYMM | YYYYMM (numeric)"; list[0][2] = "YYMM | YYYYMM (numeric)";
    }
    else if (func.equals("PI"))
    {
      list = new Object[1][1];
      list[0][0] = "numeric";
    }
    else if (func.equals("POSITION"))
    {
      list = new Object[1][1];
      list[0][0] = mySQLNotSupported;
    }
    else if (func.equals("POW") ||
             func.equals("POWER"))
    {
      list = new Object[1][3];
      list[0][0] = "numeric"; list[0][1] = "value"; list[0][2] = "power";
    }
    else if (func.equals("QUARTER"))
    {
      list[0][0] = "decimal"; list[0][1] = "date";
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
    else if (func.equals("RELEASE_LOCK"))
    {
      list[0][0] = "integer"; list[0][1] = "string";
    }
    else if (func.equals("REPEAT"))
    {
      list = new Object[1][3];
      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "integer";
    }
    else if (func.equals("REPLACE"))
    {
      list = new Object[1][4];
      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "from_string"; list[0][3] = "to_string";
    }
    else if (func.equals("REVERSE"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("RIGHT"))
    {
      list = new Object[1][3];
      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "length";
    }
    else if (func.equals("ROUND"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "integer"; list[0][1] = "numeric";
      list[1][0] = "numeric"; list[1][1] = "numeric"; list[1][2] = "decimal";
    }
    else if (func.equals("RPAD"))
    {
      list = new Object[1][4];
      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "len"; list[0][3] = "padstring";
    }
    else if (func.equals("RTRIM"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("SEC_TO_TIME"))
    {
      list[0][0] = "time"; list[0][1] = "numeric";
    }
    else if (func.equals("SECOND"))
    {
      list[0][0] = "decimal"; list[0][1] = "time";
    }
    else if (func.equals("SESSION_USER"))
    {
      list = new Object[1][1];
      list[0][0] = "string";
    }
    else if (func.equals("SIGN"))
    {
      list[0][0] = "sign (integer)"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("SIN"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("SOUNDEX"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("SPACE"))
    {
      list[0][0] = "string"; list[0][1] = "integer";
    }
    else if (func.equals("SQRT"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("STD") ||
             func.equals("STDDEV"))
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("STRCMP"))
    {
      list = new Object[1][3];
      list[0][0] = "integer"; list[0][1] = "string"; list[0][2] = "string";
    }
    else if (func.equals("SUBDATE"))
    {
      list[0][0] = "date"; list[0][1] = "INTERVAL expr type";
    }
    else if (func.equals("SUBSTRING"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];

      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "pos";
      list[1][0] = "string"; list[1][1] = "string"; list[1][2] = "pos"; list[1][3] = "len";
    }
    else if (func.equals("SUBSTRING_INDEX"))
    {
      list = new Object[1][4];
      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "delim"; list[0][3] = "count";
    }
    else if (func.equals("SUM")) // DISTINCT | ALL
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("SYSDATE"))
    {
      list = new Object[1][1];
      list[0][0] = "date and time";
    }
    else if (func.equals("SYSTEM_USER"))
    {
      list = new Object[1][1];
      list[0][0] = "string";
    }
    else if (func.equals("TAN"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("TIME_FORMAT"))
    {
      list = new Object[1][3];
      list[0][0] = "time"; list[0][1] = "time"; list[0][2] = "format";
    }
    else if (func.equals("TIME_TO_SEC"))
    {
      list[0][0] = "decimal"; list[0][1] = "time";
    }
    else if (func.equals("TO_DAYS"))
    {
      list[0][0] = "decimal"; list[0][1] = "date";
    }
    else if (func.equals("TRIM")) // signature is TRIM([[BOTH | LEADING | TRAILING] [remstr] FROM] str)
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("TRUNCATE"))
    {
      list = new Object[1][3];
      list[0][0] = "numeric"; list[0][1] = "numeric"; list[0][2] = "decimal";
    }
    else if (func.equals("UCASE") ||
             func.equals("UPPER"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("UNIX_TIMESTAMP"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];

      list[0][0] = "decimal";
      list[1][0] = "decimal"; list[1][1] = "date";
    }
    else if (func.equals("USER"))
    {
      list = new Object[1][1];
      list[0][0] = "string";
    }
    else if (func.equals("VERSION"))
    {
      list = new Object[1][1];
      list[0][0] = "string";
    }
    else if (func.equals("WEEK"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "decimal"; list[0][1] = "date";
      list[1][0] = "decimal"; list[1][1] = "date"; list[1][2] = "first (decimal)";
    }
    else if (func.equals("WEEKDAY"))
    {
      list[0][0] = "decimal"; list[0][1] = "date";
    }
    else if (func.equals("YEAR"))
    {
      list[0][0] = "decimal"; list[0][1] = "date";
    }
    else if (func.equals("YEARWEEK"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "decimal"; list[0][1] = "date";
      list[1][0] = "decimal"; list[1][1] = "date"; list[1][2] = "first (decimal)";
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
      functionsCategoryCombo.add(mySQLAll);
      functionsCategoryCombo.add(mySQLAggregate);
      functionsCategoryCombo.add(mySQLBit);
      functionsCategoryCombo.add(mySQLControlFlow);
      functionsCategoryCombo.add(mySQLDateAndTime);
      functionsCategoryCombo.add(mySQLMath);
      functionsCategoryCombo.add(mySQLMiscellaneous);
      functionsCategoryCombo.add(mySQLString);
      functionsCategoryCombo.add(dbUDF);
    }
    else  // need to filter out the valid. For now, allow all.
    {
      functionsCategoryCombo.add(mySQLAll);
      functionsCategoryCombo.add(mySQLAggregate);
      functionsCategoryCombo.add(mySQLBit);
      functionsCategoryCombo.add(mySQLControlFlow);
      functionsCategoryCombo.add(mySQLDateAndTime);
      functionsCategoryCombo.add(mySQLMath);
      functionsCategoryCombo.add(mySQLMiscellaneous);
      functionsCategoryCombo.add(mySQLString);
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

  public static boolean isNotSupported(String functionName)
  {
    List funcList = Arrays.asList(unsupportedFunctions);
    if (funcList.contains(functionName))
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

  public static String[] getFunctionList(String category, boolean isColumn, SQLDomainModel domainModel)
  {
    if (category.equals(mySQLAll))
    {
      if (!isColumn)
      {
        return UDFNamesAndSignatures.mergeTwoArrays(allFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
      }

      return UDFNamesAndSignatures.mergeTwoArrays(allColumnSupportedFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
    }
    else if (category.equals(mySQLBit))
    {
      return bitFunctions;
    }
    else if (category.equals(mySQLString))
    {
      return stringFunctions;
    }
    else if (category.equals(mySQLAggregate))
    {
      return aggregateFunctions;
    }
    else if (category.equals(mySQLMath))
    {
      return mathFunctions;
    }
    else if (category.equals(mySQLDateAndTime))
    {
      return dateAndTimeFunctions;
    }
    else if (category.equals(mySQLMiscellaneous))
    {
      return miscFunctions;
    }
    else if (category.equals(mySQLControlFlow))
    {
      return controlFlowFunctions;
    }
    else if (category.equals(dbUDF))
    {
      return UDFNamesAndSignatures.getUDFNames(domainModel);
    }
    return UDFNamesAndSignatures.mergeTwoArrays(allFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
  }
}
