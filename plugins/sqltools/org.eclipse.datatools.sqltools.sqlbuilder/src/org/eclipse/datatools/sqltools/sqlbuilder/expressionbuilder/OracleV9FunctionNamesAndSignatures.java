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


public class OracleV9FunctionNamesAndSignatures
{
  public static String oracleAll = Messages._UI_FCN_ALL;
  public static String oracleAggregate = Messages._UI_FCN_AGGREGATE;
  public static String oracleDate = Messages._UI_FCN_DATE;
  public static String oracleNumber = Messages._UI_FCN_NUMBER;
  public static String oracleCharacter = Messages._UI_FCN_CHARACTER;
  public static String oracleConversion = Messages._UI_FCN_CONVERSION;
  public static String oracleMiscellaneousSingleRow = Messages._UI_FCN_MISCELLANEOUS_SINGLE_ROW;
  public static String oracleObjectReference = Messages._UI_FCN_OBJECT_REFERENCE;
  public static String dbUDF = Messages._UI_FCN_UDF;

  private static final String allFunctions[] =
  {
    "ABS",
    "ACOS",
    "ADD_MONTHS",
    "ASCII",
    "ASCIISTR",  // new to 9i
    "ASIN",
    "ATAN",
    "ATAN2",
    "AVG",
    "BFILENAME",
    "BIN_TO_NUM",  // new to 9i // (expr1, expr2, ....)
    "BITAND", // new to 9i
    "CEIL",
    "CHARTOROWID",
    "CHR",
    "COALESCE",   // new to 9i // (expr1, expr2, ....)
    "COMPOSE",     // new to 9i
    "CONCAT",
    "CONVERT",
    "CORR",
    "COS",
    "COSH",
    "COUNT",
    "COVAR_POP",
    "COVAR_SAMP",
//    "CUME_DIST",   // new to 9i - we don't support
    "CURRENT_DATE",     // new to 9i
    "CURRENT_TIMESTAMP",   // new to 9i
    "DBTIMEZONE",   // new to 9i
//    "DECODE",      // new to 9i  - we don't support
    "DECOMPOSE",   // new to 9i
//    "DENSE_RANK",  // new to 9i - we don't support
    "DEREF",
    "DUMP",
    "EMPTY_BLOB",
    "EMPTY_CLOB",
    "EXISTSNODE",   // new to 9i
    "EXP",
//    "EXTRACT",  // new to 9i EXTRACT (DATETIME)
    "EXTRACT",  // new to 9i EXTRACT (XML)
//    "FIRST",  // new to 9i - we don't support
//     "FIRST_VALUE",   // new to 9i  Analytic function not supported
    "FLOOR",
    "FROM_TZ",    // new to 9i
    "GREATEST",  // (expr1, expr2, ....)
    "GROUP_ID",   // new to 9i
    "GROUPING",
//    "GROUPING_ID",   // new to 9i we don't support -> (expr1, expr2,....)
    "HEXTORAW",
    "INITCAP",
    "INSTR",
//    "INSTRB",   // NOT IN 9i
//    "LAG",    // new to 9i Analytic function not supported
//    "LAST",   // new to 9i Analytic function not supported
    "LAST_DAY",
//    "LAST_VALUE",    // new to 9i Analytic function not supported
//    "LEAD",     // new to 9i Analytic function not supported
    "LEAST",  // (expr1, expr2, ....)
    "LENGTH",
//    "LENGTHB",  // NOT IN 9i
    "LN",
    "LOCALTIMESTAMP",   // new to 9i
    "LOG",
    "LOWER",
    "LPAD",
    "LTRIM",
//    "MAKE_REF",  //????
    "MAX",
    "MIN",
    "MOD",
    "MONTHS_BETWEEN",
    "NCHR",   // new to 9i
    "NEW_TIME",
//    "NEW_DAY", // NOT IN 9i
    "NEXT_DAY",  // new to 9i
    "NLS_CHARSET_DECL_LEN",
    "NLS_CHARSET_ID",
    "NLS_CHARSET_NAME",
    "NLS_INITCAP",
    "NLS_LOWER",
    "NLSSORT",
    "NLS_UPPER",
//    "NTILE", // new to 9i Analytic function not supported
    "NULLIF", // new to 9i
    "NUMTODSINTERVAL",
    "NUMTOYMINTERVAL",
    "NVL",
    "NVL2",
//    "PERCENT_RANK",  // new to 9i we don't support
//    "PERCENTILE_CONT",  // new to 9i we don't support
//    "PERCENTILE_DISC",  // new to 9i we don't support
    "POWER",
//    "RANK",  // new to 9i  we don't support
//    "RATIO_TO_REPORT",  // new to 9i Analytic function not supported
    "RAWTOHEX",
    "RAWTONHEX",  // new to 9i
    "REF",
    "REFTOHEX",
    "REGR_SLOPE",
    "REGR_INTERCEPT",
    "REGR_COUNT",
    "REGR_R2",
    "REGR_AVGX",
    "REGR_AVGY",
    "REGR_SXX",
    "REGR_SYY",
    "REGR_SXY",
    "REPLACE",
    "ROUND",
//    "ROW_NUMBER",  // new to 9i Analytic function not supported
    "ROWIDTOCHAR",
    "ROWIDTONCHAR",  // new to 9i
    "RPAD",
    "RTRIM",
    "SESSIONTIMEZONE",  // new to 9i
    "SIGN",
    "SIN",
    "SINH",
    "SOUNDEX",
    "SQRT",
    "STDDEV",
    "STDDEV_POP",
    "STDDEV_SAMP",
    "SUBSTR",
//    "SUBSTRB",  // NOT IN 9i
    "SUM",
    "SYS_CONNECT_BY_PATH",  // new to 9i
    "SYS_CONTEXT",
//    "SYS_DBURIGEN",     // new to 9i we don't support
    "SYS_EXTRACT_UTC",   // new to 9i
    "SYS_GUID",
    "SYS_TYPEID",   // new to 9i
    "SYS_XMLAGG",  // new to 9i
    "SYS_XMLGEN",   // new to 9i
    "SYSDATE",
    "SYSTIMESTAMP",  // new to 9i
    "TAN",
    "TANH",
    "TO_CHAR",
    "TO_CLOB",  // new to 9i
    "TO_DATE",
    "TO_DSINTERVAL",  // new to 9i
    "TO_LOB",
    "TO_MULTI_BYTE",
    "TO_NCHAR",  // new to 9i
    "TO_NCLOB",   // new to 9i
    "TO_NUMBER",
    "TO_SINGLE_BYTE",
    "TO_TIMESTAMP",  // new to 9i
    "TO_TIMESTAMP_TZ",   // new to 9i
    "TO_YMINTERVAL",   // new to 9i
    "TRANSLATE",  // ???
 // "TRANSLATE ... USING",  ???
//    "TREAT",  // new to 9i we don't support
//    "TRIM",     // ???
    "TRUNC",
    "TZ_OFFSET",  // new to 9i
    "UID",
    "UNISTR",  // new to 9i
    "UPPER",
    "USER",
    "USERENV",
//    "VALUE",
    "VAR_POP",
    "VAR_SAMP",
    "VARIANCE",
    "VSIZE",
    "WIDTH_BUCKET"  // new to 9i
  };

  private static final String allColumnSupportedFunctions[] = allFunctions;

  private static final String functionsSupportingStar[] =
  {
    "COUNT"
  };

  private static final String noBracketFunctions[] =
  {
    "CURRENT_DATE",
    "CURRENT_TIMESTAMP", //
    "DBTIMEZONE",
    "LOCAL_TIMESTAMP",  //
    "SESSIONTIMEZONE",
    "SYSDATE",
    "SYSTIMESTAMP",
    "USER"
  };

  private static final String aggregateFunctions[] =   // oracle
  {
    "AVG",
    "CORR",
    "COUNT",
    "COVAR_POP",
    "COVAR_SAMP",
//    "CUME_DIST",
//    "DENSE_RANK",
//    "FIRST",
    "GROUP_ID",   // new to 9i
    "GROUPING",
//    "GROUPING_ID",   // new to 9i
    "MAX",
    "MIN",
//    "PERCENTILE_CONT",  // new to 9i
//    "PERCENTILE_DISC",  // new to 9i
//    "PERCENT_RANK",  // new to 9i
//    "RANK",  // new to 9i
    "REGR_SLOPE",
    "REGR_INTERCEPT",
    "REGR_COUNT",
    "REGR_R2",
    "REGR_AVGX",
    "REGR_AVGY",
    "REGR_SXX",
    "REGR_SYY",
    "REGR_SXY",
    "STDDEV",
    "STDDEV_POP",
    "STDDEV_SAMP",
    "SUM",
    "VAR_POP",
    "VAR_SAMP",
    "VARIANCE"
  };

  private static final String conversionFunctions[] =   // oracle
  {
    "ASCIISTR",
    "BIN_TO_NUM",
    "CHARTOROWID",
    "COMPOSE",
    "CONVERT",
    "DECOMPOSE",
    "HEXTORAW",
    "NUMTODSINTERVAL",
    "NUMTOYMINTERVAL",
    "RAWTOHEX",
    "RAWTONHEX",  // new to 9i
    "ROWIDTOCHAR",
    "ROWIDTONCHAR",  // new to 9i
    "TO_CHAR",
    "TO_CLOB",  // new to 9i
    "TO_DATE",
    "TO_LOB",
    "TO_MULTI_BYTE",
    "TO_NCHAR",  // new to 9i
    "TO_NCLOB",   // new to 9i
    "TO_NUMBER",
    "TO_SINGLE_BYTE",
    // TRANSLATE..USING --> CAST
    "UNISTR"  // new to 9i
  };

  private static final String dateFunctions[] =   // oracle
  {
    "ADD_MONTHS",
    "CURRENT_DATE",
    "CURRENT_TIMESTAMP",
    "DBTIMEZONE",
    "FROM_TZ",
    "LAST_DAY",
    "LOCALTIMESTAMP",   // new to 9i
    "MONTHS_BETWEEN",
    "NEW_TIME",
//    "NEW_DAY",
    "NEXT_DAY",  // new to 9i
    "ROUND",
    "SESSIONTIMEZONE",  // new to 9i
    "SYSTIMESTAMP",  // new to 9i
    "SYSDATE",
    "TO_DSINTERVAL",  // new to 9i
    "TO_TIMESTAMP",  // new to 9i
    "TO_TIMESTAMP_TZ",   // new to 9i
    "TO_YMINTERVAL",   // new to 9i
    "TRUNC",
    "TZ_OFFSET"  // new to 9i
  };

  private static final String numberFunctions[] =   // oracle
  {
    "ABS",
    "ACOS",
    "ADD_MONTHS",
    "ASIN",
    "ATAN",
    "ATAN2",
    "BITAND",
    "CEIL",
    "COS",
    "COSH",
    "EXP",
    "FLOOR",
    "LN",
    "LOG",
    "MOD",
    "POWER",
    "ROUND",
    "SIGN",
    "SIN",
    "SINH",
    "SQRT",
    "TAN",
    "TANH",
    "TRUNC",
    "WIDTH_BUCKET"  // new to 9i
  };

  private static final String characterFunctions[] =  // oracle
  {
    "ASCII",
    "CHR",
    "CONCAT",
    "INITCAP",
    "INSTR",
//    "INSTRB",
    "LENGTH",
//    "LENGTHB",
    "LOWER",
    "LPAD",
    "LTRIM",
    "NCHR",   // new to 9i    
    "NLS_INITCAP",
    "NLS_LOWER",
    "NLSSORT",
    "NLS_UPPER",
    "REPLACE",
    "RPAD",
    "RTRIM",
    "SOUNDEX",
    "SUBSTR",
//    "SUBSTRB",
    "TRANSLATE",
//    "TREAT",  // new to 9i
//    "TRIM",
    "UPPER"
  };

  private static final String miscellaneousSingleRowFunctions[] =  // oracle
  {
    "BFILENAME",
    "COALESCE",
//    "DECODE",
    "DUMP",
    "EMPTY_BLOB",
    "EMPTY_CLOB",
    "EXISTSNODE",
    "EXTRACT",  // EXTRACT (XML)
    "GREATEST",
    "LEAST",
    "NLS_CHARSET_DECL_LEN",
    "NLS_CHARSET_ID",
    "NLS_CHARSET_NAME",
    "NULLIF", // new to 9i
    "NVL",
    "NVL2",
    "SYS_CONNECT_BY_PATH",  // new to 9i
    "SYS_CONTEXT",
//    "SYS_DBURIGEN",     // new to 9i
    "SYS_EXTRACT_UTC",   // new to 9i
    "SYS_GUID",
    "SYS_TYPEID",   // new to 9i
    "SYS_XMLAGG",  // new to 9i
    "SYS_XMLGEN",   // new to 9i
    "UID",
    "USER",
    "USERENV",
    "VSIZE"
  };

  private static final String objectReferenceFunctions[] =  // oracle
  {
    "DEREF",
//    "MAKE_REF",
    "REF",
    "REFTOHEX",
//    "VALUE"
  };

  public static Object[][] getParms(String func)
  {
    Object[][] list = new Object[1][2];
    list[0][0] = "???"; list[0][1] = "???";

    if (func.equals("ABS"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("ACOS"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("ADD_MONTHS"))
    {
      list = new Object[1][3];
      list[0][0] = "d"; list[0][1] = "d"; list[0][2] = "n";
    }
    else if (func.equals("ASCII"))
    {
      list[0][0] = "d"; list[0][1] = "char";
    }
    else if (func.equals("ASCIISTR"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("ASIN"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("ATAN"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("ATAN2"))
    {
      list = new Object[1][3];
      list[0][0] = "n"; list[0][1] = "n"; list[0][2] = "n";
    }
    else if (func.equals("AVG"))   // DISTINCT, ALL
    {
      list[0][0] = "expr"; list[0][1] = "expr";
    }
    else if (func.equals("BFILENAME"))
    {
      list = new Object[1][3];
      list[0][0] = "BFILE"; list[0][1] = "'<directory>'"; list[0][2] = "'<filename>'";
    }
    else if (func.equals("BIN_TO_NUM"))
    {
      list = new Object[5][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[2] = new Object[4];
      list[3] = new Object[5];
      list[4] = new Object[6];

      list[0][0] = "NUMBER"; list[0][1] = "expr, ...";
      list[1][0] = "NUMBER"; list[1][1] = "expr"; list[1][2] = "expr";
      list[2][0] = "NUMBER"; list[2][1] = "expr"; list[2][2] = "expr"; list[2][3] = "expr";
      list[3][0] = "NUMBER"; list[3][1] = "expr"; list[3][2] = "expr"; list[3][3] = "expr"; list[3][4] = "expr";
      list[4][0] = "NUMBER"; list[4][1] = "expr"; list[4][2] = "expr"; list[4][3] = "expr"; list[4][4] = "expr"; list[4][5] = "expr";
    }
    else if (func.equals("BITAND"))
    {
      list = new Object[1][3];
      list[0][0] = "int"; list[0][1] = "int"; list[0][2] = "int";
    }
    else if (func.equals("CEIL"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("CHARTOROWID"))
    {
      list[0][0] = "ROWID"; list[0][1] = "char";

      //Object[][] list = new Object[2][2];
      //list[0][0] = "ROWID"; list[0][1] = "CHAR";
      //list[1][0] = "ROWID"; list[1][1] = "VARCHAR2";
    }
    else if (func.equals("CHR"))
    {
      list[0][0] = "char"; list[0][1] = "n";
    }
    else if (func.equals("COALESCE"))
    {
      list = new Object[5][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[2] = new Object[4];
      list[3] = new Object[5];
      list[4] = new Object[6];

      list[0][0] = "expr"; list[0][1] = "expr";
      list[1][0] = "expr"; list[1][1] = "expr"; list[1][2] = "expr";
      list[2][0] = "expr"; list[2][1] = "expr"; list[2][2] = "expr"; list[2][3] = "expr";
      list[3][0] = "expr"; list[3][1] = "expr"; list[3][2] = "expr"; list[3][3] = "expr"; list[3][4] = "expr";
      list[4][0] = "expr"; list[4][1] = "expr"; list[4][2] = "expr"; list[4][3] = "expr"; list[4][4] = "expr"; list[4][5] = "expr";
    }
    else if (func.equals("COMPOSE"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("CONCAT"))
    {
      list = new Object[1][3];
      list[0][0] = "str"; list[0][1] = "str"; list[0][2] = "str";
    }
    else if (func.equals("CONVERT"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];

      list[0][0] = "n"; list[0][1] = "char"; list[0][2] = "dest char set";
      list[1][0] = "n"; list[1][1] = "char"; list[1][2] = "dest char set"; list[1][3] = "source char set";
    }
    else if (func.equals("CORR"))
    {
      list = new Object[1][3];
      list[0][0] = "n"; list[0][1] = "expr"; list[0][2] = "expr";
    }
    else if (func.equals("COS"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("COSH"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("COUNT"))   // DISTINCT, ALL
    {
      list[0][0] = "n"; list[0][1] = "expr";
    }
    else if (func.equals("COVAR_POP"))
    {
      list = new Object[1][3];
      list[0][0] = "n"; list[0][1] = "expr"; list[0][2] = "expr";
    }
    else if (func.equals("COVAR_SAMP"))
    {
      list = new Object[1][3];
      list[0][0] = "n"; list[0][1] = "expr"; list[0][2] = "expr";
    }
    else if (func.equals("CURRENT_DATE"))
    {
      list = new Object[1][1];
      list[0][0] = "DATE";
    }
    else if (func.equals("CURRENT_TIMESTAMP"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];
      
      list[0][0] = "TIMESTAMP WITH TIME ZONE";
      list[1][0] = "TIMESTAMP WITH TIME ZONE"; list[1][1] = "n";

      list = new Object[1][1];
      list[0][0] = "TIMESTAMP WITH TIME ZONE";

    }
    else if (func.equals("DBTIMEZONE"))
    {
      list = new Object[1][1];
      list[0][0] = "char";
    }
    else if (func.equals("DECOMPOSE"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("DEREF"))
    {
      list[0][0] = "ref"; list[0][1] = "expr";
    }
    else if (func.equals("DUMP"))
    {
      list = new Object[4][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[2] = new Object[4];
      list[3] = new Object[5];

      list[0][0] = "VARCHAR2"; list[0][1] = "expr";
      list[1][0] = "VARCHAR2"; list[1][1] = "expr"; list[1][2] = "return format";
      list[2][0] = "VARCHAR2"; list[2][1] = "expr"; list[2][2] = "return format"; list[2][3] = "start position";
      list[3][0] = "VARCHAR2"; list[3][1] = "expr"; list[3][2] = "return format"; list[3][3] = "start position"; list[3][4] = "length";
    }
    else if (func.equals("EMPTY_BLOB"))
    {
      list = new Object[1][1];
      //Function Returns             Function Input Parm Type
      list[0][0] = "LOB";
    }
    else if (func.equals("EMPTY_CLOB"))
    {
      list = new Object[1][1];
      //Function Returns             Function Input Parm Type
      list[0][0] = "LOB";
    }
    else if (func.equals("EXISTSNODE"))
    {
      list = new Object[1][3];
      list[0][0] = "NUMBER"; list[0][1] = "XMLType"; list[0][2] = "VARCHAR2";
    }
    else if (func.equals("EXP"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("EXTRACT"))
    {
      list = new Object[1][3];
      list[0][0] = "XMLType"; list[0][1] = "XMLType"; list[0][2] = "VARCHAR2";
    }
    else if (func.equals("FLOOR"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("FROM_TZ"))
    {
      list = new Object[1][3];
      list[0][0] = "TIMESTAMP WITH TIME ZONE"; list[0][1] = "TIMESTAMP"; list[0][2] = "string";
    }
    else if (func.equals("GREATEST"))
    {
      list[0][0] = "expr"; list[0][1] = "expr";
    }
    else if (func.equals("GROUPING"))
    {
      list[0][0] = "i"; list[0][1] = "expr";
    }
    else if (func.equals("GROUP_ID"))
    {
      list = new Object[1][1];
      list[0][0] = "NUMBER";
    }
    
    else if (func.equals("HEXTORAW"))
    {
      list[0][0] = "r"; list[0][1] = "string";  // string ^---- check char=string
    }
    else if (func.equals("INITCAP"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("INSTR"))
//           || func.equals("INSTRB"))
    {
      list = new Object[3][];
      list[0] = new Object[3];
      list[1] = new Object[4];
      list[2] = new Object[5];

      list[0][0] = "VARCHAR2"; list[0][1] = "string"; list[0][2] = "string";
      list[1][0] = "VARCHAR2"; list[1][1] = "string"; list[1][2] = "string"; list[1][3] = "n";
      list[2][0] = "VARCHAR2"; list[2][1] = "string"; list[2][2] = "string"; list[2][3] = "n"; list[2][4] = "m";
    }
    else if (func.equals("LAST_DAY"))
    {
      list[0][0] = "date"; list[0][1] = "date";
    }
    else if (func.equals("LEAST"))
    {
      list[0][0] = "expr"; list[0][1] = "expr";
    }
    else if (func.equals("LENGTH"))
    {
      list[0][0] = "character"; list[0][1] = "string";
    }
//    else if (func.equals("LENGTHB"))
//    {
//      list[0][0] = "bytes"; list[0][1] = "string";
//    }
    else if (func.equals("LN"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("LOCALTIMESTAMP"))
    {
      list = new Object[2][];
      list[0] = new Object[1];
      list[1] = new Object[2];
      
      list[0][0] = "TIMESTAMP";
      list[1][0] = "TIMESTAMP"; list[1][1] = "n";

      list = new Object[1][1];
      list[0][0] = "TIMESTAMP";
    }
    else if (func.equals("LOG"))
    {
      list = new Object[1][3];
      list[0][0] = "n"; list[0][1] = "m"; list[0][2] = "n";
    }
    else if (func.equals("LOWER"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("LPAD"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "string"; list[0][1] = "n";
      list[1][0] = "string"; list[1][1] = "n"; list[1][2] = "string";
    }
    else if (func.equals("LTRIM"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "string"; list[0][1] = "n";
      list[1][0] = "string"; list[1][1] = "n"; list[1][2] = "string";
    }
//    else if (func.equals("MAKE_REF"))
//    {
//    }
    else if (func.equals("MAX"))
    {
      list[0][0] = "expr"; list[0][1] = "expr";
    }
    else if (func.equals("MIN"))
    {
      list[0][0] = "expr"; list[0][1] = "expr";
    }
    else if (func.equals("MOD"))
    {
      list = new Object[1][3];
      list[0][0] = "n"; list[0][1] = "n"; list[0][2] = "m";
    }
    else if (func.equals("MONTHS_BETWEEN"))
    {
      list = new Object[1][3];
      list[0][0] = "n"; list[0][1] = "date1"; list[0][2] = "date2";
    }
    else if (func.equals("NCHR"))
    {
      list[0][0] = "NVARCHAR2"; list[0][1] = "n";
    }
    else if (func.equals("NEW_TIME"))
    {
      list = new Object[1][4];
      list[0][0] = "date"; list[0][1] = "date"; list[0][2] = "timezone string"; list[0][3] = "timezone string";
    }
//    else if (func.equals("NEW_DAY"))
//    {
//      list = new Object[1][3];
//      list[0][0] = "date"; list[0][1] = "date"; list[0][2] = "day string";
//    }
    else if (func.equals("NEXT_DAY"))
    {
      list = new Object[1][3];
      list[0][0] = "date"; list[0][1] = "date"; list[0][2] = "char";
    }
    else if (func.equals("NLS_CHARSET_DECL_LEN"))
    {
      list = new Object[1][3];
      list[0][0] = "n"; list[0][1] = "bytecnt"; list[0][2] = "csid";
    }
    else if (func.equals("NLS_CHARSET_ID"))
    {
      list[0][0] = "csid"; list[0][1] = "string";
    }
    else if (func.equals("NLS_CHARSET_NAME"))
    {
      list[0][0] = "nlsch"; list[0][1] = "n";
    }
    else if (func.equals("NLS_INITCAP") ||
             func.equals("NLS_LOWER") ||
             func.equals("NLSSORT") ||
             func.equals("NLS_UPPER"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "string"; list[0][1] = "string";
      list[1][0] = "string"; list[1][1] = "string"; list[1][2] = "nlsparam";
    }
    else if (func.equals("NULLIF"))
    {
      list = new Object[1][3];
      list[0][0] = "expr"; list[0][1] = "expr"; list[0][2] = "expr";
    }
    else if (func.equals("NUMTODSINTERVAL") ||
             func.equals("NUMTOYMINTERVAL"))
    {
      list = new Object[1][3];
      list[0][0] = "n"; list[0][1] = "n"; list[0][2] = "char_expr";
    }
    else if (func.equals("NVL"))
    {
      list = new Object[1][3];
      list[0][0] = "expr"; list[0][1] = "expr1"; list[0][2] = "expr2";
    }
    else if (func.equals("NVL2"))
    {
      list = new Object[1][4];
      list[0][0] = "expr"; list[0][1] = "expr1"; list[0][2] = "expr2"; list[0][3] = "expr3";
    }
    else if (func.equals("POWER"))
    {
      list = new Object[1][3];
      list[0][0] = "n"; list[0][1] = "n"; list[0][2] = "n";
    }
    else if (func.equals("RAWTOHEX"))
    {
      list[0][0] = "n"; list[0][1] = "raw";
    }
    else if (func.equals("RAWTONHEX"))
    {
      list[0][0] = "NVARCHAR2"; list[0][1] = "raw";
    }
    else if (func.equals("REF"))
    {
      list[0][0] = "REF"; list[0][1] = "correlation_variable";
    }
    else if (func.equals("REFTOHEX"))
    {
      list[0][0] = "n"; list[0][1] = "r";
    }
    else if (func.equals("REGR_SLOPE") ||
             func.equals("REGR_INTERCEPT") ||
             func.equals("REGR_COUNT") ||
             func.equals("REGR_R2") ||
             func.equals("REGR_AVGX") ||
             func.equals("REGR_AVGY") ||
             func.equals("REGR_SXX") ||
             func.equals("REGR_SYY") ||
             func.equals("REGR_SXY"))
    {
      list = new Object[1][3];
      list[0][0] = "expr"; list[0][1] = "expr1"; list[0][2] = "expr2";
    }
    else if (func.equals("REPLACE"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];

      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "string";
      list[1][0] = "string"; list[1][1] = "string"; list[1][2] = "string"; list[1][3] = "string";
    }
    else if (func.equals("ROUND"))
    {
      list = new Object[4][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[2] = new Object[2];
      list[3] = new Object[3];

      list[0][0] = "n"; list[0][1] = "n";
      list[1][0] = "n"; list[1][1] = "n"; list[1][2] = "n";
      list[2][0] = "d"; list[0][1] = "d";
      list[3][0] = "d"; list[1][1] = "d"; list[1][2] = "fmt";
    }
    else if (func.equals("ROWIDTOCHAR"))
    {
      list[0][0] = "VARCHAR2"; list[0][1] = "rowid";
    }
    else if (func.equals("ROWIDTONCHAR"))
    {
      list[0][0] = "NVARCHAR2"; list[0][1] = "rowid";
    }
    else if (func.equals("RPAD"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];

      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "n";
      list[1][0] = "string"; list[1][1] = "string"; list[1][2] = "n"; list[1][3] = "string";
    }
    else if (func.equals("RTRIM"))
    {
      list = new Object[2][3];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "string"; list[0][1] = "string";
      list[1][0] = "string"; list[1][1] = "string"; list[1][2] = "set string";
    }
    else if (func.equals("SESSIONTIMEZONE"))
    {
      list = new Object[1][1];
      list[0][0] = "char";
    }
    else if (func.equals("SIGN"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("SIN"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("SINH"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("SOUNDEX"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("SQRT"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("STDDEV") ||
             func.equals("STDDEV_POP") ||
             func.equals("STDDEV_SAMP"))
    {
      list[0][0] = "n"; list[0][1] = "expr";
    }
    else if (func.equals("SUBSTR"))
//           ||  func.equals("SUBSTRB"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];

      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "n";
      list[1][0] = "string"; list[1][1] = "string"; list[1][2] = "n"; list[1][3] = "n";
    }
    else if (func.equals("SUM"))
    {
      list[0][0] = "n"; list[0][1] = "expr";
    }
    else if (func.equals("SYS_CONNECT_BY_PATH"))
    {
      list = new Object[1][3];
      list[0][0] = "VARCHAR2"; list[0][1] = "column"; list[0][2] = "char";
    }
    else if (func.equals("SYS_CONTEXT"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];

      list[0][0] = "VARCHAR2"; list[0][1] = "namespace"; list[0][2] = "attribute";
      list[1][0] = "VARCHAR2"; list[1][1] = "namespace"; list[1][2] = "attribute"; list[1][3] = "length";
    }
    else if (func.equals("SYS_EXTRACT_UTC"))
    {
      list[0][0] = "UTC"; list[0][1] = "datetime_with_timezone";
    }
    else if (func.equals("SYS_GUID"))
    {
      list = new Object[1][1];
      //Function Returns             Function Input Parm Type
      list[0][0] = "RAW";
    }
    else if (func.equals("SYS_TYPEID"))
    {
      list[0][0] = "typeid"; list[0][1] = "object";
    }
    else if (func.equals("SYS_XMLAGG"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "XML doc"; list[0][1] = "expr";
      list[1][0] = "XML doc"; list[1][1] = "expr"; list[1][2] = "fmt";
    }
    else if (func.equals("SYS_XMLGEN"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "XMLType"; list[0][1] = "expr";
      list[1][0] = "XMLType"; list[1][1] = "expr"; list[1][2] = "fmt";
    }
    else if (func.equals("SYSDATE"))
    {
      list = new Object[1][1];
      //Function Returns             Function Input Parm Type
      list[0][0] = "date";
    }
    else if (func.equals("SYSTIMESTAMP"))
    {
      list = new Object[1][1];
      list[0][0] = "TIMESTAMP WITH TIME ZONE";
    }
    else if (func.equals("TAN"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("TANH"))
    {
      list[0][0] = "n"; list[0][1] = "n";
    }
    else if (func.equals("TO_CHAR"))
    {
      list = new Object[6][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[2] = new Object[4];
      list[3] = new Object[2];
      list[4] = new Object[3];
      list[5] = new Object[4];

      list[0][0] = "VARCHAR2"; list[0][1] = "d";
      list[1][0] = "VARCHAR2"; list[1][1] = "d"; list[1][2] = "fmt";
      list[2][0] = "VARCHAR2"; list[2][1] = "d"; list[2][2] = "fmt"; list[2][3] = "nlsparam";
      list[3][0] = "VARCHAR2"; list[3][1] = "n";
      list[4][0] = "VARCHAR2"; list[4][1] = "n"; list[4][2] = "fmt";
      list[5][0] = "VARCHAR2"; list[5][1] = "n"; list[5][2] = "fmt"; list[5][3] = "nlsparam";
    }
    else if (func.equals("TO_CLOB"))
    {
      list[0][0] = "CLOB"; list[0][1] = "lob_column or char";
    }
    else if (func.equals("TO_DATE"))
    {
      list = new Object[3][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[2] = new Object[4];

      list[0][0] = "VARCHAR2"; list[0][1] = "string";
      list[1][0] = "VARCHAR2"; list[1][1] = "string"; list[1][2] = "fmt";
      list[2][0] = "VARCHAR2"; list[2][1] = "string"; list[2][2] = "fmt"; list[2][3] = "nlsparam";
    }
    else if (func.equals("TO_DSINTERVAL"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "INTERVAL DAY TO SECOND"; list[0][1] = "string";
      list[1][0] = "INTERVAL DAY TO SECOND"; list[1][1] = "string"; list[1][2] = "nlsparam";
    }
    else if (func.equals("TO_LOB"))
    {
      list[0][0] = "LOB"; list[0][1] = "long_column";
    }
    else if (func.equals("TO_MULTI_BYTE"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("TO_NCHAR"))
    {
      list = new Object[3][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[2] = new Object[4];

      list[0][0] = "NVARCHAR2"; list[0][1] = "char|datetime|n";
      list[1][0] = "NVARCHAR2"; list[1][1] = "char|datetime|n"; list[1][2] = "fmt";
      list[2][0] = "NVARCHAR2"; list[2][1] = "char|datetime|n"; list[2][2] = "fmt"; list[2][3] = "nlsparam";
    }
    else if (func.equals("TO_NCLOB"))
    {
      list[0][0] = "NCLOB"; list[0][1] = "lob_column or char";
    }
    else if (func.equals("TO_NUMBER"))
    {
      list = new Object[3][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[2] = new Object[4];

      list[0][0] = "n"; list[0][1] = "string";
      list[1][0] = "n"; list[1][1] = "string"; list[1][2] = "fmt";
      list[2][0] = "n"; list[2][1] = "string"; list[2][2] = "fmt"; list[2][3] = "nlsparam";
    }
    else if (func.equals("TO_SINGLE_BYTE"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("TO_TIMESTAMP"))
    {
      list = new Object[3][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[2] = new Object[4];

      list[0][0] = "TIMESTAMP"; list[0][1] = "char";
      list[1][0] = "TIMESTAMP"; list[1][1] = "char"; list[1][2] = "fmt";
      list[2][0] = "TIMESTAMP"; list[2][1] = "char"; list[2][2] = "fmt"; list[2][3] = "nlsparam";
    }
    else if (func.equals("TO_TIMESTAMP_TZ"))
    {
      list = new Object[3][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[2] = new Object[4];

      list[0][0] = "TIMESTAMP WITH TIME ZONE"; list[0][1] = "char";
      list[1][0] = "TIMESTAMP WITH TIME ZONE"; list[1][1] = "char"; list[1][2] = "fmt";
      list[2][0] = "TIMESTAMP WITH TIME ZONE"; list[2][1] = "char"; list[2][2] = "fmt"; list[2][3] = "nlsparam";
    }
    else if (func.equals("TO_YMINTERVAL"))
    {
      list[0][0] = "INTERVAL YEAR TO MONTH"; list[0][1] = "char";
    }
    else if (func.equals("TRANSLATE"))
    {
      list = new Object[1][4];
      list[0][0] = "string"; list[0][1] = "string"; list[0][2] = "string"; list[0][3] = "string";
    }
//    else if (func.equals("TRIM"))
//    {
//    }
    else if (func.equals("TRUNC"))
    {
      list = new Object[4][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[2] = new Object[2];
      list[3] = new Object[3];

      list[0][0] = "n"; list[0][1] = "n";
      list[1][0] = "n"; list[1][1] = "n"; list[1][2] = "n";
      list[2][0] = "d"; list[2][1] = "d";
      list[3][0] = "d"; list[3][1] = "d"; list[3][2] = "fmt";
    }
    else if (func.equals("TZ_OFFSET"))
    {
      list[0][0] = "tz_offset"; list[0][1] = "timezone";
    }
    else if (func.equals("UID"))
    {
      list = new Object[1][1];
      //Function Returns             Function Input Parm Type
      list[0][0] = "n";
    }
    else if (func.equals("UNISTR"))
    {
      list[0][0] = "unicode_string"; list[0][1] = "string";
    }
    else if (func.equals("UPPER"))
    {
      list[0][0] = "string"; list[0][1] = "string";
    }
    else if (func.equals("USER"))
    {
      list = new Object[1][1];
      //Function Returns             Function Input Parm Type
      list[0][0] = "string";
    }
    else if (func.equals("USERENV"))
    {
      list = new Object[8][];
      list[0] = new Object[2];
      list[1] = new Object[2];
      list[2] = new Object[2];
      list[3] = new Object[2];
      list[4] = new Object[2];
      list[5] = new Object[2];
      list[6] = new Object[2];
      list[7] = new Object[2];

      list[0][0] = "VARCHAR2"; list[0][1] = "'ISDBA'";
      list[1][0] = "VARCHAR2"; list[1][1] = "'LANGUAGE'";
      list[2][0] = "VARCHAR2"; list[2][1] = "'TERMINAL'";
      list[3][0] = "VARCHAR2"; list[3][1] = "'SESSIONID'";
      list[4][0] = "VARCHAR2"; list[4][1] = "'ENTRYID'";
      list[5][0] = "VARCHAR2"; list[5][1] = "'LANG'";
      list[6][0] = "VARCHAR2"; list[6][1] = "'INSTANCE'";
      list[7][0] = "VARCHAR2"; list[7][1] = "'CLIENT_INFO'";
    }
//    else if (func.equals("VALUE"))
//    {
//    }
    else if (func.equals("VAR_POP") ||
             func.equals("VAR_SAMP"))
    {
      list[0][0] = "n"; list[0][1] = "expr";
    }
    else if (func.equals("VARIANCE"))
    {
      list[0][0] = "n"; list[0][1] = "expr";
    }
    else if (func.equals("VSIZE"))
    {
      list[0][0] = "n"; list[0][1] = "expr";
    }
    else if (func.equals("WIDTH_BUCKET"))
    {
      list = new Object[1][5];
      list[0][0] = "n"; list[0][1] = "expr"; list[0][2] = "expr"; list[0][3] = "n";
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
      functionsCategoryCombo.add(oracleAll);
      functionsCategoryCombo.add(oracleAggregate);
      functionsCategoryCombo.add(oracleDate);
      functionsCategoryCombo.add(oracleNumber);
      functionsCategoryCombo.add(oracleCharacter);
      functionsCategoryCombo.add(oracleConversion);
      functionsCategoryCombo.add(oracleMiscellaneousSingleRow);
      functionsCategoryCombo.add(oracleObjectReference);
      functionsCategoryCombo.add(dbUDF);
    }
    else
    {
      functionsCategoryCombo.add(oracleAll);
      functionsCategoryCombo.add(oracleAggregate);
      functionsCategoryCombo.add(oracleDate);
      functionsCategoryCombo.add(oracleNumber);
      functionsCategoryCombo.add(oracleCharacter);
      functionsCategoryCombo.add(oracleConversion);
      functionsCategoryCombo.add(oracleMiscellaneousSingleRow);
      functionsCategoryCombo.add(oracleObjectReference);
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

  public static String[] getFunctionList(String category, boolean isColumn, SQLDomainModel domainModel)
  {
    if (category.equals(oracleAll))
    {
      if (!isColumn)
      {
        return UDFNamesAndSignatures.mergeTwoArrays(allFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
      }

      return UDFNamesAndSignatures.mergeTwoArrays(allColumnSupportedFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
    }
    else if (category.equals(oracleAggregate))
    {
      return aggregateFunctions;
    }
    else if (category.equals(oracleDate))
    {
      return dateFunctions;
    }
    else if (category.equals(oracleNumber))
    {
      return numberFunctions;
    }
    else if (category.equals(oracleCharacter))
    {
      return characterFunctions;
    }
    else if (category.equals(oracleConversion))
    {
    	return conversionFunctions;
    }
    else if (category.equals(oracleMiscellaneousSingleRow))
    {
      return miscellaneousSingleRowFunctions;
    }
    else if (category.equals(oracleObjectReference))
    {
      return objectReferenceFunctions;
    }
    else if (category.equals(dbUDF))
    {
      return UDFNamesAndSignatures.getUDFNames(domainModel);
    }
    return UDFNamesAndSignatures.mergeTwoArrays(allFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
  }
}

