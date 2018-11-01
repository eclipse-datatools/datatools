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


public class InformixFunctionNamesAndSignatures
{

  public static final String informixAll = Messages._UI_FCN_ALL;
  public static final String informixAggregate = Messages._UI_FCN_AGGREGATE;
  public static final String informixAlgebraic = "Algebraic";
  public static final String informixConstant = "Constant";
  public static final String informixExponentialAndLogarithmic = "Exponential and Logarithmic";
  public static final String informixLength = "Length";
  public static final String informixMisc = "Miscellaneous";
  public static final String informixSmartLargeObject = "Smart-Large-Object";
  public static final String informixString = "String";
  public static final String informixTime = "Time";
  public static final String informixTrigonometric = "Trigonometric";
  public static final String informixNotSupported = Messages._UI_FCN_NOT_SUPPORTED;
  public static String dbUDF = Messages._UI_FCN_UDF;


  private static final String allFunctions[] =
  {
    "ABS",
    "ACOS",
    "ASIN",
    "ATAN2",
    "ATAN",
    "AVG",
    "CARDINALITY",
    "CHARACTER_LENGTH",
    "CHAR_LENGTH",
    "COS",
    "COUNT",
    "CURRENT",
    "DATETIME",
    "DATE",
    "DAY",
    "DBINFO",
    "DBSERVERNAME",
    "EXP",
    "EXTEND",
    "FILETOBLOB",
    "FILETOCLOB",
    "HEX",
    "IFX_ALLOW_NEWLINE",
    "IFX_REPLACE_MODULE",
    "INITCAP",
    "INTERVAL",
    "LENGTH",
    "LOCOPY",
    "LOG10",
    "LOGN",
    "LOTOTFILE",
    "LOWER",
    "LPAD",
    "MAX",
    "MDY",
    "MIN",
    "MOD",
    "MONTH",
    "OCTET_LENGTH",
    "POW",
    "RANGE",
    "REPLACE",
    "ROOT",
    "ROUND",
    "RPAD",
    "SIN",
    "SITENAME",
    "SQRT",
    "STDEV",
    "SUBSTRING",
    "SUBSTR",
    "SUM",
    "TAN",
    "TODAY",
    "TO_CHAR",
    "TO_DATE",
    "TRIM",
    "TRUNC",
    "UPPER",
    "USER",
    "VARIANCE",
    "WEEKDAY",
    "YEAR"
  };

  private static final String allColumnSupportedFunctions[] = allFunctions;

  private static final String functionsSupportingStar[] =
  {
    "COUNT"
  };

  private static final String unsupportedFunctions[] =
  {
    "INTERVAL",
    "SUBSTRING",
    "TRIM"
  };

  private static final String noBracketFunctions[] =
  {
    "CURRENT",
    "DATETIME",
    "DBSERVERNAME",
    "INTERVAL",
    "SITENAME",
    "TODAY",
    "USER",
  };

  private static final String constantFunctions[] = noBracketFunctions;

  private static final String algebraicFunctions[] =
  {
    "ABS",
    "MOD",
    "POW",
    "ROOT",
    "ROUND",
    "SQRT",
    "TRUNC"
  };

  private static final String miscFunctions[] =
  {
    "CARDINALITY",
    "DBINFO",
    "HEX",
    "IFX_REPLACE_MODULE",
    "IFX_ALLOW_NEWLINE"
  };

  private static final String exponentialAndLogarithmicFunctions[] =
  {
    "EXP",
    "LOGN",
    "LOG10"
  };

  private static final String lengthFunctions[] =
  {
    "CHAR_LENGTH",
    "CHARACTER_LENGTH",
    "LENGTH",
    "OCTET_LENGTH"
  };

  private static final String smallLargeObjectFunctions[] =
  {
    "FILETOBLOB",
    "FILETOCLOB",
    "LOTOTFILE",
    "LOCOPY"
  };

  private static final String timeFunctions[] =
  {
    "DATE",
    "DAY",
    "EXTEND",
    "MDY",
    "MONTH",
    "TO_CHAR",
    "TO_DATE",
    "WEEKDAY",
    "YEAR"
  };

  private static final String trigonometricFunctions[] =
  {
    "ACOS",
    "ASIN",
    "ATAN",
    "ATAN2",
    "COS",
    "SIN",
    "TAN"
  };

  private static final String stringFunctions[] =
  {
    "LOWER",
    "LPAD",
    "INITCAP",
    "REPLACE",
    "RPAD",
    "SUBSTRING",
    "SUBSTR",
    "TRIM",
    "UPPER"
  };

  private static final String aggregateFunctions[] =
  {
    "AVG",
    "COUNT",
    "MAX",
    "MIN",
    "SUM",
    "RANGE",
    "STDEV",
    "VARIANCE"
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
    else if (func.equals("AVG")) // DISTINCT, UNIQUE, ALL
    {
      list[0][0] = "numeric_expression"; list[0][1] = "expression";
    }
    else if (func.equals("CARDINALITY"))
    {
      list[0][0] = "int"; list[0][1] = "collection_expression";
    }
    else if (func.equals("CHAR_LENGTH") ||
             func.equals("CHARACTER_LENGTH") )
    {
      list[0][0] = "int"; list[0][1] = "expression";
    }
    else if (func.equals("COS"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("COUNT")) // DISTINCT, UNIQUE, ALL
    {
      list[0][0] = "int"; list[0][1] = "expression";
    }
    else if (func.equals("CURRENT"))
    {
      list = new Object[1][1];
      list[0][0] = "datetime";
    }
    else if (func.equals("DATETIME"))
    {
      list = new Object[1][1];
      list[0][0] = "datetime";
    }
    else if (func.equals("DATE"))
    {
      list[0][0] = "date"; list[0][1] = "expression";
    }
    else if (func.equals("DAY"))
    {
      list[0][0] = "int"; list[0][1] = "date|datetime_expression";
    }
    else if (func.equals("DBINFO"))
    {
      list = new Object[3][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[2] = new Object[4];

      list[0][0] = "info"; list[0][1] = "expression";
      list[1][0] = "info"; list[1][1] = "expression"; list[1][2] = "expression";
      list[2][0] = "info"; list[2][1] = "expression"; list[2][2] = "expression"; list[2][3] = "expression";
    }
    else if (func.equals("DBSERVERNAME"))
    {
      list = new Object[1][1];
      list[0][0] = "server_name";
    }
    else if (func.equals("EXP"))
    {
      list[0][0] = "float_expression"; list[0][1] = "float_expression";
    }
    else if (func.equals("EXTEND"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "date|datetime_expression"; list[0][1] = "date|datetime_expression";
      list[1][0] = "date|datetime_expression"; list[1][1] = "date|datetime_expression"; list[1][2] = "first TO last";
    }
    else if (func.equals("FILETOBLOB"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[5];

      list[0][0] = "blob"; list[0][1] = "pathname"; list[0][2] = "file_destination";
      list[1][0] = "blob"; list[1][1] = "pathname"; list[1][2] = "file_destination"; list[1][3] = "table"; list[1][4] = "column";
    }
    else if (func.equals("FILETOCLOB"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[5];

      list[0][0] = "blob"; list[0][1] = "pathname"; list[0][2] = "file_destination";
      list[1][0] = "blob"; list[1][1] = "pathname"; list[1][2] = "file_destination"; list[1][3] = "table"; list[1][4] = "column";
    }
    else if (func.equals("HEX"))
    {
      list[0][0] = "hex_expression"; list[0][1] = "int_expression";
    }
    else if (func.equals("IFX_ALLOW_NEWLINE")) // argument is fixed
    {
      list = new Object[2][2];
      list[0][0] = ""; list[0][1] = "'t'";
      list[1][0] = ""; list[1][1] = "'f'";
    }
    else if (func.equals("IFX_REPLACE_MODULE"))
    {
      list = new Object[1][4];
      list[0][0] = ""; list[0][1] = "old_module"; list[0][2] = "new_module"; list[0][3] = "\"C\"";
    }
    else if (func.equals("INITCAP"))
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("INTERVAL")) // TODO: Need to support INTERVAL Field Qualifier in UI
    {
      list = new Object[1][1];
      list[0][0] = informixNotSupported;
    }
    else if (func.equals("LENGTH"))
    {
      list[0][0] = "int"; list[0][1] = "expression";
    }
    else if (func.equals("LOCOPY"))
    {
      list = new Object[4][];
      list[0] = new Object[2];
      list[1] = new Object[4];
      list[2] = new Object[2];
      list[3] = new Object[4];

      list[0][0] = "handle"; list[0][1] = "BLOB_column";
      list[1][0] = "handle"; list[1][1] = "BLOB_column"; list[1][2] = "table"; list[1][3] = "column";
      list[2][0] = "handle"; list[2][1] = "CLOB_column";
      list[3][0] = "handle"; list[3][1] = "CLOB_column"; list[3][2] = "table"; list[3][3] = "column";
    }
    else if (func.equals("LOG10"))
    {
      list[0][0] = "float_expression"; list[0][1] = "float_expression";
    }
    else if (func.equals("LOGN"))
    {
      list[0][0] = "float_expression"; list[0][1] = "float_expression";
    }
    else if (func.equals("LOTOTFILE"))
    {
      list = new Object[4][];
      list[0] = new Object[2];
      list[1] = new Object[4];
      list[2] = new Object[2];
      list[3] = new Object[4];

      list[0][0] = ""; list[0][1] = "BLOB_column";
      list[1][0] = ""; list[1][1] = "BLOB_column"; list[1][2] = "pathname"; list[1][3] = "file_destination";
      list[2][0] = ""; list[2][1] = "CLOB_column";
      list[3][0] = ""; list[3][1] = "CLOB_column"; list[3][2] = "pathname"; list[3][3] = "file_destination";
    }
    else if (func.equals("LOWER"))
    {
      list[0][0] = "string_expression"; list[0][1] = "string_expression";
    }
    else if (func.equals("LPAD"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];

      list[0][0] = "string_expression"; list[0][1] = "string_expression"; list[0][2] = "length";
      list[1][0] = "string_expression"; list[1][1] = "string_expression"; list[1][2] = "length"; list[1][3] = "pad_string";
    }
    else if (func.equals("MAX")) // ALL | UNIQUE | DISTINCT
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("MDY"))
    {
      list = new Object[1][4];
      list[0][0] = "date"; list[0][1] = "int_month"; list[0][2] = "int_day"; list[0][3] = "int_year";
    }
    else if (func.equals("MIN")) // ALL | UNIQUE | DISTINCT
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("MOD"))
    {
      list = new Object[1][3];
      list[0][0] = "INT8"; list[0][1] = "numeric_expression"; list[0][2] = "numeric_expression";
    }
    else if (func.equals("MONTH"))
    {
      list[0][0] = "int"; list[0][1] = "date|datetime_expression";
    }
    else if (func.equals("OCTET_LENGTH"))
    {
      list[0][0] = "int"; list[0][1] = "expression";
    }
    else if (func.equals("POW"))
    {
      list = new Object[1][3];
      list[0][0] = "FLOAT"; list[0][1] = "numeric_expression"; list[0][2] = "numeric_expression";
    }
    else if (func.equals("RANGE"))
    {
      list[0][0] = "int"; list[0][1] = "expression";
    }
    else if (func.equals("REPLACE"))
    {
      list = new Object[1][4];
      list[0][0] = "string_expression"; list[0][1] = "source_string"; list[0][2] = "old_string"; list[0][3] = "new_string";
    }
    else if (func.equals("ROOT"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
      list[1][0] = "numeric_expression"; list[1][1] = "numeric_expression"; list[1][2] = "index";
    }
    else if (func.equals("ROUND"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
      list[1][0] = "numeric_expression"; list[1][1] = "numeric_expression"; list[1][2] = "rounding_factor";
    }
    else if (func.equals("RPAD"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];

      list[0][0] = "string_expression"; list[0][1] = "string_expression"; list[0][2] = "length";
      list[1][0] = "string_expression"; list[1][1] = "string_expression"; list[1][2] = "length"; list[1][3] = "pad_string";
    }
    else if (func.equals("SIN"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("SITENAME"))
    {
      list = new Object[1][1];
      list[0][0] = "server_name";
    }
    else if (func.equals("SQRT"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("STDEV"))
    {
      list[0][0] = "float"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("SUBSTRING"))
    {
      list[0][0] = informixNotSupported; list[0][1] = "source FROM start FOR length";
    }
    else if (func.equals("SUBSTR"))
    {
      list = new Object[1][4];
      list[0][0] = "expression"; list[0][1] = "expression"; list[0][2] = "start"; list[0][3] = "length";
    }
    else if (func.equals("SUM")) // DISTINCT | UNIQUE | ALL
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("TAN"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("TODAY"))
    {
      list = new Object[1][1];
      list[0][0] = "date";
    }
    else if (func.equals("TO_CHAR"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "char_string"; list[0][1] = "source_date";
      list[1][0] = "char_string"; list[1][1] = "source_date"; list[1][2] = "format_string";
    }
    else if (func.equals("TO_DATE"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "datetime"; list[0][1] = "char_expression";
      list[1][0] = "datetime"; list[1][1] = "char_expression"; list[1][2] = "format_string";
    }
    else if (func.equals("TRIM"))
    {
      list = new Object[1][1];
      list[0][0] = informixNotSupported;
    }
    else if (func.equals("TRUNC"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];

      list[0][0] = "numeric_expression"; list[0][1] = "expression";
      list[1][0] = "numeric_expression"; list[1][1] = "expression"; list[1][2] = "truncate_factor";
    }
    else if (func.equals("UPPER"))
    {
      list[0][0] = "string_expression"; list[0][1] = "string_expression";
    }
    else if (func.equals("USER"))
    {
      list = new Object[1][1];
      list[0][0] = "string";
    }
    else if (func.equals("VARIANCE"))
    {
      list[0][0] = "float"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("WEEKDAY"))
    {
      list[0][0] = "int"; list[0][1] = "date|datetime_expression";
    }
    else if (func.equals("YEAR"))
    {
      list[0][0] = "int"; list[0][1] = "date|datetime_expression";
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
      functionsCategoryCombo.add(informixAll);
      functionsCategoryCombo.add(informixAggregate);
      functionsCategoryCombo.add(informixAlgebraic);
      functionsCategoryCombo.add(informixConstant);
      functionsCategoryCombo.add(informixExponentialAndLogarithmic);
      functionsCategoryCombo.add(informixLength);
      functionsCategoryCombo.add(informixMisc);
      functionsCategoryCombo.add(informixSmartLargeObject);
      functionsCategoryCombo.add(informixString);
      functionsCategoryCombo.add(informixTime);
      functionsCategoryCombo.add(informixTrigonometric);
      functionsCategoryCombo.add(dbUDF);
    }
    else
    {
      functionsCategoryCombo.add(informixAll);
      functionsCategoryCombo.add(informixAggregate);
      functionsCategoryCombo.add(informixAlgebraic);
      functionsCategoryCombo.add(informixConstant);
      functionsCategoryCombo.add(informixExponentialAndLogarithmic);
      functionsCategoryCombo.add(informixLength);
      functionsCategoryCombo.add(informixMisc);
      functionsCategoryCombo.add(informixSmartLargeObject);
      functionsCategoryCombo.add(informixString);
      functionsCategoryCombo.add(informixTime);
      functionsCategoryCombo.add(informixTrigonometric);
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
    if (category.equals(informixAll))
    {
      if (!isColumn)
      {
        return UDFNamesAndSignatures.mergeTwoArrays(allFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
      }

      return UDFNamesAndSignatures.mergeTwoArrays(allColumnSupportedFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
    }
    else if (category.equals(informixAggregate))
    {
      return aggregateFunctions;
    }
    else if (category.equals(informixAlgebraic))
    {
      return algebraicFunctions;
    }
    else if (category.equals(informixConstant))
    {
      return constantFunctions;
    }
    else if (category.equals(informixExponentialAndLogarithmic))
    {
      return exponentialAndLogarithmicFunctions;
    }
    else if (category.equals(informixLength))
    {
      return lengthFunctions;
    }
    else if (category.equals(informixMisc))
    {
      return miscFunctions;
    }
    else if (category.equals(informixSmartLargeObject))
    {
      return smallLargeObjectFunctions;
    }
    else if (category.equals(informixString))
    {
      return stringFunctions;
    }
    else if (category.equals(informixTime))
    {
      return timeFunctions;
    }
    else if (category.equals(informixTrigonometric))
    {
      return trigonometricFunctions;
    }
    else if (category.equals(dbUDF))
    {
      return UDFNamesAndSignatures.getUDFNames(domainModel);
    }
    return UDFNamesAndSignatures.mergeTwoArrays(allFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;

  }
}
