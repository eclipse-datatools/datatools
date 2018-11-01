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


public class InstantDBFunctionNamesAndSignatures {

    public static final String instantDBAll = Messages._UI_FCN_ALL;
    public static final String instantDBAggregate = Messages._UI_FCN_AGGREGATE;
    public static String dbUDF = Messages._UI_FCN_UDF;

    private static final String allFunctions[] = { "AVG", "COUNT", "MAX", "MIN", "SUM" };

    private static final String allColumnSupportedFunctions[] = allFunctions;

    private static final String functionsSupportingStar[] = { "COUNT" };

    private static final String aggregateFunctions[] = { "AVG", "COUNT", "MAX", "MIN", "SUM" };

    public static Object[][] getParms(String func) {
        Object[][] list = new Object[1][2];
        list[0][0] = "???";
        list[0][1] = "???";

        if (func.equals("AVG")) // DISTINCT, ALL
        {
            list[0][0] = "numeric_expression";
            list[0][1] = "expression";
        }
        else if (func.equals("COUNT")) // DISTINCT, ALL
        {
            list[0][0] = "numeric_expression";
            list[0][1] = "expression";
        }
        else if (func.equals("MAX")) {
            list[0][0] = "expression";
            list[0][1] = "expression";
        }
        else if (func.equals("MIN")) {
            list[0][0] = "expression";
            list[0][1] = "expression";
        }
        else if (func.equals("SUM")) // DISTINCT | ALL
        {
            list[0][0] = "expression";
            list[0][1] = "expression";
        }
        else {
            list = UDFNamesAndSignatures.getUDFParams(func);
        }

        return list;
    } // end getParms

    public static Object[][] getParameterFormats(String functionName) {
        return getParms(functionName);
    }

    public static void fillCategoryCombo(Combo functionsCategoryCombo, boolean isColumn) {
        if (!isColumn) {
            functionsCategoryCombo.add(instantDBAll);
            functionsCategoryCombo.add(instantDBAggregate);
            functionsCategoryCombo.add(dbUDF);
        }
        else // need to filter out the valid. For now, allow all.
        {
            functionsCategoryCombo.add(instantDBAll);
            functionsCategoryCombo.add(instantDBAggregate);
            functionsCategoryCombo.add(dbUDF);
        }
    }

    // Set up superclass for the various vendor function names and signatures
    public static boolean isFunctionSupportingStar(String s) {
        List funcList = Arrays.asList(functionsSupportingStar);
        if (funcList.contains(s)) {
            return true;
        }
        return false;
    }

    public static String[] getFunctionList(String category, boolean isColumn, SQLDomainModel domainModel) {
        if (category.equals(instantDBAll)) {
            if (!isColumn) {
                return UDFNamesAndSignatures.mergeTwoArrays(allFunctions, UDFNamesAndSignatures.getUDFNames(domainModel));
            }
            
            return UDFNamesAndSignatures.mergeTwoArrays(allColumnSupportedFunctions, UDFNamesAndSignatures.getUDFNames(domainModel));
        }
        else if (category.equals(instantDBAggregate)) {
            return aggregateFunctions;
        }
        else if (category.equals(dbUDF)) {
            return UDFNamesAndSignatures.getUDFNames(domainModel);
        }
        return UDFNamesAndSignatures.mergeTwoArrays(allFunctions, UDFNamesAndSignatures.getUDFNames(domainModel));
    }
}