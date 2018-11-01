package org.eclipse.datatools.sqltools.sqlbuilder.model;
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

/*
package org.eclipse.datatools.sqltools.sqlbuilder.model;

// This class is not used.  But it is retained, since it has some methods copied from SQL Assist 
// which we may use when resolving function data types.

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.eclipse.wst.rdb.internal.models.sql.datatypes.BinaryStringDataType;
import org.eclipse.wst.rdb.internal.models.sql.datatypes.CharacterStringDataType;
import org.eclipse.wst.rdb.internal.models.sql.datatypes.DataLinkDataType;
import org.eclipse.wst.rdb.internal.models.sql.datatypes.DataType;
import org.eclipse.wst.rdb.internal.models.sql.datatypes.NumericalDataType;
import org.eclipse.wst.rdb.internal.models.sql.datatypes.PredefinedDataType;
import org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType;
import org.eclipse.wst.rdb.internal.models.sql.datatypes.SQLDataTypesFactory;
import org.eclipse.wst.rdb.internal.models.sql.schema.Database;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.helper.DataTypeHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.DatabaseHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.ValueExpressionHelper;

*//**
 * This class provides a set of utility methods for dealing with
 * Value Expression objects.
 *//*
public class SQLBuilderValueExpressionHelper extends ValueExpressionHelper {

    *//**
     * Tries to determine and set the datatype of the given function value
     * expression.
     * @param aValExpr a value expression to resolve
     * @param aTableRefList the current list of From clause table references
     * @param aDB a RDBDatabase object containing datatype information
     * @param aDBVersion an object containing database version information
     *//*
    public static void resolveValueExpressionFunctionDatatype(ValueExpressionFunction aValExpr, List aTableRefList, Database aDB) {

        //    throw new UnsupportedOperationException(SQLBuilderValueExpressionHelper.class.getName()+"#resolveValueExpressionFunctionDatatype() not implemented!");

        // Get the function name.
        String funcName = aValExpr.getName();

        int returnJDBCTypeID = 0;
        String returnDatatypeName = "";
        int returnDatatypeLen = 0;
        String param1DatatypeName = "";
        int param1DatatypeLen = 0;

        DataType datatype = null;

        // Get the parameter list, then get the first parameter's datatype name
        // and length, if they are known.
        List paramList = aValExpr.getParameterList();
        if (paramList.size() > 0) {
            QueryValueExpression param1ValExpr = (QueryValueExpression) paramList.get(0);
            if (param1ValExpr != null) {
                DataType param1Datatype = param1ValExpr.getDataType();
                if (param1Datatype != null) {
                    param1DatatypeName = param1Datatype.getName();
                    // Get a length, if there is one.
                    if (param1Datatype instanceof CharacterStringDataType) {
                        param1DatatypeLen = ((CharacterStringDataType) param1Datatype).getLength();
                    }
                    else if (param1Datatype instanceof DataLinkDataType) {
                        param1DatatypeLen = ((DataLinkDataType) param1Datatype).getLength();
                    }
                    else if (param1Datatype instanceof BinaryStringDataType) {
                        param1DatatypeLen = ((BinaryStringDataType) param1Datatype).getLength();
                    }
                    else if (param1Datatype instanceof NumericalDataType) {
                        param1DatatypeLen = ((NumericalDataType) param1Datatype).getPrecision();
                    }
                }
            }
        }

        // ***TODO: load/use the appropriate function helper for the connected database.

        // Get an array containing all the return and parameter type information
        // for the function.
        Object[][] funcParams = getParameterFormats(funcName);

        // Determine if the return type names for all input parameter variations
        // are the same.  If so, that's the name of our return datatype!
        boolean isVariableReturnType = false;
        returnDatatypeName = (String) funcParams[0][0];
        for (int i = 1; i < funcParams.length; i++) {
            if (!returnDatatypeName.equals(funcParams[i][0])) {
                isVariableReturnType = true;
            }
        }

        // If the return type is not always the same for this function, we need to look at the
        // input parameter types to try to figure out the return type.  With
        // just a few exceptions, when the return type is variable, the return
        // type is the same as the datatype of the first input parameter.
        if (isVariableReturnType == true) {
            // Handle the special cases.
            if (funcName.equals("AVG") || funcName.equals("SUM")) {
                if (param1DatatypeName.equals("SMALLINT")) {
                    returnDatatypeName = "INTEGER";
                }
                else if (param1DatatypeName.equals("REAL")) {
                    returnDatatypeName = "DOUBLE";
                }
                else {
                    returnDatatypeName = param1DatatypeName;
                }
            }
            else if (funcName.equals("CONCAT")) {
                // CONCAT needs extra special handling.  The datatype of a CONCAT
                // function depends on the datatypes and lengths of both function
                // arguments.
                QueryValueExpression param1ValExpr = (QueryValueExpression) paramList.get(0);
                QueryValueExpression param2ValExpr = (QueryValueExpression) paramList.get(1);
                resolveValueExpressionConcatDatatype(aValExpr, param1ValExpr, param2ValExpr, aTableRefList, aDB);
                datatype = aValExpr.getDataType();
            }
            else if (funcName.equals("LTRIM") || funcName.equals("RTRIM")) {
                if (param1DatatypeName.equals("CHAR")) {
                    returnDatatypeName = "VARCHAR";
                }
                else if (param1DatatypeName.equals("GRAPHIC")) {
                    returnDatatypeName = "VARGRAPHIC";
                }
                else {
                    returnDatatypeName = param1DatatypeName;
                }
            }
            else {
                returnDatatypeName = param1DatatypeName;
            }
        }

        // The datatype may have been resolved already.  (See the CONCAT case.)
        // If not, try to resolve it now.
        if (datatype == null) {
            // Get the length of the return type.
            returnDatatypeLen = getReturnDatatypeLength(funcName, returnDatatypeName, param1DatatypeName, param1DatatypeLen);

            // Get the JDBC type ID for the datatype name.
            if (returnDatatypeName.length() > 0) {
                returnJDBCTypeID = DataTypeHelper.getJDBCTypeForNamedType(returnDatatypeName);
            }

            // Now that we have all the datatype information we can reasonably get,
            // get a DataType object for the function return type and set that
            // as the datatype for this value expression.

            // TODO: getDataType

            datatype = DatabaseHelper.getDataType(returnJDBCTypeID, returnDatatypeName, "" + returnDatatypeLen, "0", aDB);
        }

        aValExpr.setDataType(datatype);
    }

    *//** 
     * TODO: implement this Proxy method. Throws UnsupportedOperationException now.
     * @param funcName
     * @param returnDatatypeName
     * @param param1DatatypeName
     * @param param1DatatypeLen
     * @return
     *//*
    private static int getReturnDatatypeLength(String funcName, String returnDatatypeName, String param1DatatypeName, int param1DatatypeLen) {
        throw new UnsupportedOperationException(SQLBuilderValueExpressionHelper.class.getName()
                + "#getReturnDatatypeLength(String funcName, String returnDatatypeName, String param1DatatypeName, int param1DatatypeLen) not implemented!");
        //return FunctionHelper.getReturnDatatypeLength( funcName, returnDatatypeName, param1DatatypeName, param1DatatypeLen );
    }

    *//** 
     * TODO: implement this Proxy method. Throws UnsupportedOperationException now.
     * @param funcName
     * @return
     *//*
    private static Object[][] getParameterFormats(String funcName) {
        throw new UnsupportedOperationException(SQLBuilderValueExpressionHelper.class.getName() + "#getParameterFormats(String funcName) not implemented!");
        //return FunctionHelper.getParameterFormats( funcName );
    }

    // TODO: to be implemented properly using DatabaseDefinitionRegistry
    *//**
     * Guesses the DataType of the given value of a ValueExpression.
     * TODO: document this properly, after code review
     * 
     * @param value the value to which a DataType should be guessed
     * @return the DataType that was guessed
     *//*
    private static PredefinedDataType getPredefinedDataTypeForSimpleValue(String value) {

        PredefinedDataType datatype = null;

        // ask Hemant Kolwalkar

        // should deal with that Types factory
        SQLDataTypesFactory sqlDataTypesFactory = SQLDataTypesFactory.eINSTANCE;

        // first you get DatabaseDefinitionRegistry 

        //DataToolsPlugin.getDatabaseDefinitionRegistry();

        // the DatabaseDefinitionRegistry has two methods which give you the DatabaseDefinition.

        //public DatabaseDefinition getDefinition(String product, String version);
        //public DatabaseDefinition getDefinition(Database database);

        String lenStr = null;
        String scaleStr = null;
        if (value != null && value.length() > 0) {

            // Check for a string datatype by looking for enclosing quotes.
            if (value.startsWith("'") && value.endsWith("'")) {
                Integer len = new Integer(value.length() - 2);
                lenStr = len.toString();
                datatype = sqlDataTypesFactory.createCharacterStringDataType();
                //TODO: when is it, what about length
                //PrimitiveType.CHARACTER_LARGE_OBJECT_LITERAL;
                //PrimitiveType.CHARACTER_VARYING_LITERAL;
                datatype.setPrimitiveType(PrimitiveType.CHARACTER_LITERAL);
                //TODO: better!
                ((CharacterStringDataType) datatype).setLength(len.intValue());
            }

            // Check for a "graphic" (G'dddd' or N'dddd') double-byte literal.
            else if ((value.startsWith("G'") || value.startsWith("g'") || value.startsWith("N'") || value.startsWith("n'")) && value.endsWith("'")) {
                Integer len = new Integer((value.length() - 3) / 2);
                lenStr = len.toString();
                //datatype = DatabaseHelper.getDataType( Types.CHAR, "GRAPHIC", lenStr, scaleStr, aDB );
                datatype = sqlDataTypesFactory.createCharacterStringDataType();
                datatype.setPrimitiveType(PrimitiveType.NATIONAL_CHARACTER_LITERAL);
            }

            // Check for a "hex" (X'cccc') literal.
            else if ((value.startsWith("X'") || value.startsWith("x'")) && value.endsWith("'")) {
                // TODO: Now that I've found it, what do I do with it?
                datatype = sqlDataTypesFactory.createBinaryStringDataType();
                //TODO: HEX is not large per se, Hemant
                datatype.setPrimitiveType(PrimitiveType.BINARY_LARGE_OBJECT_LITERAL);
            }

            // Must not be a string.  Check for a numeric value.
            else {
                // Since we know we don't have a string literal, we can upper case the string to
                // simplify our search for special characters.
                String ucValue = value.toUpperCase();

                // We can also ignore any leading sign, since we're not really
                // interested in the value, just its

                // Check for double literal.  (Floating point constants are always
                // assumed to be double-precision.)
                int eIndex = ucValue.indexOf('E');
                if (eIndex != -1) {
                    try {
                        Double doubleObject = new Double(ucValue);

                        // If we reached here, we really do have a double.  Get its 'precision'.
                        // (I'm assuming that the precision, like for decimal numbers, is the
                        // total number of digits, not including the 'E' part.)  When
                        // computing the precision, take into account any leading sign
                        // and decimal point.
                        int valPrecision = eIndex;
                        if (ucValue.startsWith("-") || ucValue.startsWith("+")) {
                            valPrecision--;
                        }
                        if (ucValue.lastIndexOf('.', eIndex) != -1) {
                            valPrecision--;
                        }
                        //datatype = DatabaseHelper.getDataType( Types.DOUBLE, "DOUBLE", "" + valPrecision, "0", aDB );
                        //TODO: check the right datatype?
                        datatype = sqlDataTypesFactory.createApproximateNumericDataType();
                        datatype.setPrimitiveType(PrimitiveType.DOUBLE_PRECISION_LITERAL);
                    }
                    catch (NumberFormatException e) {
                    }
                }

                //TODO: don't make trivial ints to doubles, and ....

                // Check for a decimal literal.  We'll assume it's a decimal if it
                // has a decimal point.
                // ***TODO:  handle case of decimal separator is not a decimal point
                //           (ie, a comma, as in the German locale.)
                if (ucValue.indexOf('.') != -1) {
                    try {
                        BigDecimal decimal = new BigDecimal(ucValue);

                        // If we reached here we know we have a decimal.  Get it's precision
                        // and scale.
                        BigInteger bigIntVal = decimal.unscaledValue();
                        bigIntVal = bigIntVal.abs(); // eliminate the minus sign, if any.
                        String bigIntValStr = bigIntVal.toString();
                        int precision = bigIntValStr.length();

                        int scale = decimal.scale();
                        //datatype = DatabaseHelper.getDataType( Types.DECIMAL, "DECIMAL", "" + precision, "" + scale, aDB );
                        //TODO: check for right datatype
                        datatype = sqlDataTypesFactory.createFixedPrecisionDataType();
                        datatype.setPrimitiveType(PrimitiveType.DECIMAL_LITERAL);
                    }
                    catch (NumberFormatException e2) {
                    }
                }
                // If not a decimal, try for an integer.
                else {
                    try {
                        Integer intObj = new Integer(ucValue);

                        // If we reached here we know we have an integer.
                        //datatype = DatabaseHelper.getDataType( Types.INTEGER, "INTEGER", "10", "0", aDB );
                        datatype = sqlDataTypesFactory.createIntegerDataType();
                        datatype.setPrimitiveType(PrimitiveType.INTEGER_LITERAL);

                    }
                    catch (NumberFormatException e2) {
                        try {
                            BigInteger bigInt = new BigInteger(ucValue);

                            // If we reached here we know we have a big integer.
                            //datatype = DatabaseHelper.getDataType( Types.INTEGER, "INTEGER", "19", "0", aDB );
                            datatype = sqlDataTypesFactory.createIntegerDataType();
                            // TODO: check that
                            datatype.setPrimitiveType(PrimitiveType.BIGINT_LITERAL);
                        }
                        catch (NumberFormatException e3) {

                            // Oh well, we can't interpret the literal value.

                            //TODO: is there an default DataType????

                        }
                    }
                }
            }
        }

        //TODO: review

        return datatype;

    }

} // end class
*/