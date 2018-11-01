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


public class CloudscapeFunctionNamesAndSignatures
{
  public static final String copyright = "(c) Copyright IBM Corporation 2000, 2002.";
  public static final String cloudscapeAll = Messages._UI_FCN_ALL;
  public static final String cloudscapeAggregate = Messages._UI_FCN_AGGREGATE;
  public static final String cloudscapeNotSupported = Messages._UI_FCN_NOT_SUPPORTED;
  public static String dbUDF = Messages._UI_FCN_UDF;

  private static final String allFunctions[] =
  {
    "ABSOLUTE",
    "AVG",
    "BIT_LENGTH",
    "CHAR_LENGTH",
    "CHARACTER_LENGTH",
    "COUNT",
    "CURRENT_DATE",
    "CURRENT_TIME",
    "CURRENT_TIMESTAMP",
    "CURRENT_USER",
    "EXTRACT",
    "LOCATE",
    "LOWER",
    "LTRIM",
    "MAX",
    "MIN",
    "OCTET_LENGTH",
    "RTRIM",
    "RUNTIMESTATISTICS",
    "SESSION_USER",
    "SQRT",
    "SUBSTRING",
    "SUBSTR",
    "SUM",
    "TRIM",
    "UPPER",
    "USER"
  };

  private static final String allColumnSupportedFunctions[] = allFunctions;

  private static final String functionsSupportingStar[] =
  {
    "COUNT"
  };

  private static final String unsupportedFunctions[] =
  {
    "EXTRACT",
    "TRIM"
  };

  private static final String noBracketFunctions[] =
  {
    "CURRENT_DATE",
    "CURRENT_TIME",
    "CURRENT_TIMESTAMP",
    "CURRENT_USER",
    "SESSION_USER",
    "USER",
  };

  //private static final String constantFunctions[] = noBracketFunctions;

  private static final String aggregateFunctions[] =
  {
    "AVG",
    "COUNT",
    "MAX",
    "MIN",
    "SUM"
  };

  public static Object[][] getParms(String func)
  {
    Object[][] list = new Object[1][2];
    list[0][0] = "???"; list[0][1] = "???";

    if (func.equals("ABSOLUTE"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("AVG")) // DISTINCT, ALL
    {
      list[0][0] = "numeric_expression"; list[0][1] = "expression";
    }
    else if (func.equals("BIT_LENGTH"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "expression";
    }
    else if (func.equals("CHAR_LENGTH") ||
             func.equals("CHARACTER_LENGTH") )
    {
      list[0][0] = "numeric_expression"; list[0][1] = "string_expression";
    }
    else if (func.equals("COUNT")) // DISTINCT, ALL
    {
      list[0][0] = "numeric_expression"; list[0][1] = "expression";
    }
    else if (func.equals("CURRENT_DATE"))
    {
      list = new Object[1][1];
      list[0][0] = "date";
    }
    else if (func.equals("CURRENT_TIME"))
    {
      list = new Object[1][1];
      list[0][0] = "time";
    }
    else if (func.equals("CURRENT_TIMESTAMP"))
    {
      list = new Object[1][1];
      list[0][0] = "timestamp";
    }
    else if (func.equals("CURRENT_USER") ||
             func.equals("SESSION_USER") ||
             func.equals("USER") )
    {
      list = new Object[1][1];
      list[0][0] = "string";
    }
    else if (func.equals("EXTRACT"))  // extract(MONTH FROM date_expression)
    {
      list = new Object[1][1];
      list[0][0] = cloudscapeNotSupported;
    }
    else if (func.equals("LOCATE"))
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];
      list[0][0] = "integer"; list[0][1] = "char_expression"; list[0][2] = "char_expression";
      list[1][0] = "integer"; list[1][1] = "char_expression"; list[1][2] = "char_expression"; list[1][3] = "integer";
    }
    else if (func.equals("LOWER"))
    {
      list[0][0] = "string_expression"; list[0][1] = "string_expression";
    }
    else if (func.equals("LTRIM"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[0][0] = "string_expression"; list[0][1] = "string_expression";
      list[1][0] = "string_expression"; list[1][1] = "string_expression"; list[1][2] = "char_expression";
    }
    else if (func.equals("MAX")) // ALL | DISTINCT
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("MIN")) // ALL | DISTINCT
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("OCTET_LENGTH"))
    {
      list[0][0] = "int"; list[0][1] = "expression";
    }
    else if (func.equals("RTRIM"))
    {
      list = new Object[2][];
      list[0] = new Object[2];
      list[1] = new Object[3];
      list[0][0] = "string_expression"; list[0][1] = "string_expression";
      list[1][0] = "string_expression"; list[1][1] = "string_expression"; list[1][2] = "char_expression";
    }
    else if (func.equals("RUNTIMESTATISTICS"))
    {
      list = new Object[1][1];
      list[0][0] = "RunTimeStatistics";
    }
    else if (func.equals("SQRT"))
    {
      list[0][0] = "numeric_expression"; list[0][1] = "numeric_expression";
    }
    else if (func.equals("SUBSTR") ||
             func.equals("SUBSTRING") )
    {
      list = new Object[2][];
      list[0] = new Object[3];
      list[1] = new Object[4];
      list[0][0] = "string_expression"; list[0][1] = "string_expression"; list[0][2] = "start_expression";
      list[1][0] = "string_expression"; list[1][1] = "string_expression"; list[1][2] = "start_expression"; list[1][3] = "length";
    }
    else if (func.equals("SUM")) // DISTINCT | ALL
    {
      list[0][0] = "expression"; list[0][1] = "expression";
    }
    else if (func.equals("TRIM"))
    {
      // TRIM( [ [ { LEADING | TRAILING | BOTH } ]
      //     [ TrimCharacter ] FROM ] CharacterExpression ) 
   	
      list = new Object[1][1];
      list[0][0] = cloudscapeNotSupported;
    }
    else if (func.equals("UPPER"))
    {
      list[0][0] = "string_expression"; list[0][1] = "string_expression";
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
      functionsCategoryCombo.add(cloudscapeAll);
      functionsCategoryCombo.add(cloudscapeAggregate);
      functionsCategoryCombo.add(dbUDF);
    }
    else // for now add all
    {
      functionsCategoryCombo.add(cloudscapeAll);
      functionsCategoryCombo.add(cloudscapeAggregate);
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
    if (category.equals(cloudscapeAll))
    {
      if (!isColumn)
      {
        return UDFNamesAndSignatures.mergeTwoArrays(allFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
      }

      return UDFNamesAndSignatures.mergeTwoArrays(allColumnSupportedFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
    }
    else if (category.equals(cloudscapeAggregate))
    {
      return aggregateFunctions;
    }
    else if (category.equals(dbUDF))
    {
      return UDFNamesAndSignatures.getUDFNames(domainModel);
    }

    return UDFNamesAndSignatures.mergeTwoArrays(allFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
  }
}
