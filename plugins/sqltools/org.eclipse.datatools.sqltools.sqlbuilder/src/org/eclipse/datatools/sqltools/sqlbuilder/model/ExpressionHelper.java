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

package org.eclipse.datatools.sqltools.sqlbuilder.model;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.datatypes.ApproximateNumericDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.FixedPrecisionDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.NumericalDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.modelbase.sql.tables.Column;

import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple;
import org.eclipse.datatools.modelbase.sql.query.helper.DataTypeHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.ValueExpressionHelper;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelFactoryImpl;

/**
 * Helper class for manipulating <code>QueryValueExpression</code> 
 */

public class ExpressionHelper {
//DOCME doc the methods in this class
    private static SQLQueryModelFactory factory;

    public ExpressionHelper() {
        factory = SQLQueryModelFactoryImpl.eINSTANCE;
    }

    /**
     * Determines whether or not the given SQL string contains a parameter
     * name.
     * @param source the SQL source to check 
     * @return true when the source contains a parameter name, otherwise false
     */
    public boolean containsParameterName(String source) {
        // b3170 bgp 20040729 - new method
        boolean containsParam = false;
        String paramName = findParameterName(source);
        if (paramName.length() > 0) {
            containsParam = true;
        }
        return containsParam;
    }

    /**
     * Determines whether or not the given SQL string starts with a parameter character
     * name.
     * @param source the SQL source to check 
     * @return true when the source starts with parameter character, otherwise false
     */
    public boolean firstParameterChar(String source) {
        // b3170 bgp 20040729 - new method
        boolean firstParamChar = false;
        if (source.length() > 0) {
            if (" :@|&*/-+><(),\r\n\t".indexOf(source.trim().charAt(0)) >= 0) {
                firstParamChar = true;
            }
        }
        return firstParamChar;
    }

    /**
     * Builds a <code>ValueExpressionColumn</code>  object and sets the given name and TableExpression.
     * @param name the String name for the new ValueExpressionColumn
     * @param tblExpr the TableExpression that needs to be set for the new ValueExpressionColumn
     * @return the newly created QueryValueExpression
     */
    public static ValueExpressionColumn createValueExpressionColumn(String name, TableExpression tblExpr) {
        ValueExpressionColumn colExpr = null;
        colExpr = factory.createValueExpressionColumn();
        colExpr.setName(name);
        colExpr.setTableExpr(tblExpr);
        return colExpr;

    }

    /**
     * Builds a ValueExpressionColumn object from the given ValueExpressionColumn.
     * @param oldValExpr the ValueExpressionColumn that needs to be set for the new ValueExpressionColumn
     * @return the newly created QueryValueExpression
     */
    public static ValueExpressionColumn createValueExpressionColumn(ValueExpressionColumn oldValExpr) {
        ValueExpressionColumn colExpr = null;
        colExpr = factory.createValueExpressionColumn();
        if (oldValExpr != null) {
            colExpr.setName(oldValExpr.getName());
            // if the given valexprcol is part of the exposed dbcollist then we get parenttableexpr else tableexprref 
            if (oldValExpr.getParentTableExpr() != null) {
                colExpr.setTableExpr(oldValExpr.getParentTableExpr());
            }
            else if (oldValExpr.getTableExpr() != null) {
                colExpr.setTableExpr(oldValExpr.getTableExpr());
            }
            colExpr.setDataType(ValueExpressionHelper.copyDataType(oldValExpr.getDataType()));
        }
        return colExpr;

    }
    public static QueryValueExpression createValueExpressionNullValue() {
        QueryValueExpression expr = factory.createValueExpressionNullValue();
        return expr;
    }

    public static QueryValueExpression createValueExpressionDefaultValue() {
        QueryValueExpression expr = factory.createValueExpressionDefaultValue();
        return expr;
    }

    public static QueryValueExpression createExpression() {
        QueryValueExpression expr = factory.createValueExpressionSimple();
        return expr;
    }
//FIXME this method and its usage needs to be reviewed
    /**
     * Build a <code>QueryValueExpression</code> object from the input
     */
    public static QueryValueExpression createExpression(Object item) {
        QueryValueExpression expr = null;

        if (item instanceof String) {
            String source = (String) item;
            ValueExpressionSimple simpleExpr = factory.createValueExpressionSimple();
            simpleExpr.setValue(source);

            // We need to locate any parameter names (host variable names) in the
            // expression string and remember it along with the expression text.
            // Note: the method that finds the parameter name returns an empty
            // string if no parameter name is found.
            // Note: a better way to handle parameters would be to run the source
            // expression through the parser and generate a query model subtree
            // for the expression.
            //String paramName = findParameterName( source );
            //qmp-all need some rework here bcz the simple expr parameter marker is replaced with sqlvalueexpressionvariable
            //simpleExpr.setParameterMarkerName( paramName );

            // Return the simple expression object we created.
            expr = simpleExpr;
            // b3170 bgp 20040728 - end
        }
        // When the source object is a QueryValueExpression already, simply
        // return it.
        else if (item instanceof QueryValueExpression) {
            expr = (QueryValueExpression) item;
        }

        return expr;
    }

    /**
     * Creates a SQLColumnExpression for the given table and column.
     * @param table the table containing the column
     * @param col the column for which a column expression object is wanted
     * @return the new column expression object
     */
    public static ValueExpressionColumn createColumnExpression(TableExpression table, Column col) {
        ValueExpressionColumn colExpr = factory.createValueExpressionColumn();
        colExpr.setName(col.getName());
        colExpr.setTableExpr(table);
        colExpr.setDataType(ValueExpressionHelper.copyDataType(col.getDataType()));
        return colExpr;
    }
    
    /**
     * Creates a SQLValueExpressionColumn for the given table and column.
     * @param table the table containing the column
     * @param col the column for which a column expression object is wanted
     * @return the new column expression object
     */
    /*
     public SQLValueExpressionColumn createColumnExpression( SQLFromTable table, RDBColumn col )
     {
     SQLCorrelation corr = table.getTableAlias();
     SQLValueExpressionColumn colExpr = createColumnExpression( corr, col );
     
     return colExpr;
     }
     */

    /**
     * Create a SQLPredicate object given a left, right and comparsionKind
     * If the comparisonKind requires no right side, set the right side to ""
     */
    public PredicateBasic createPredicate(Object left, Object right, String comparisonKind) {
        PredicateBasic sqlPredicate = factory.createPredicateBasic();

        sqlPredicate.setLeftValueExpr(createExpression(left));
        sqlPredicate.setRightValueExpr(createExpression(right));
        sqlPredicate.setComparisonOperator(PredicateComparisonOperator.get(comparisonKind));
        return sqlPredicate;
    }

    /**
     * Create a <code>ValueExpressionFunction</code> , given a function name,
     * An example of using this call will be to create function such as
     * CURRENT DATE, CURRENT TIME
     *
     * @param functionName - the name of the function
     */
    public static ValueExpressionFunction createFunction(String functionName) {
        ValueExpressionFunction function = factory.createValueExpressionFunction();
        function.setName(functionName);
        return function;
    }

    /**
     * Create a <code>ValueExpressionFunction</code> given a function name and a parameter list.
     * Examples of using this call be:
     *   - MAX(Salary)
     *   - CORRELATION(Salary, Bonus)
     *   - CHAR(Staring + Duration, USA)
     *
     * @param functionName - the name of the function
     * @param parmList - the parm list
     *
     * See createList, and createExpressionGroup for more information
     */
    public static ValueExpressionFunction createFunction(String functionName, List parmList) {
        ValueExpressionFunction function = createFunction(functionName);
        if (parmList != null && parmList.size() > 0) {
            function.getParameterList().addAll(parmList);
        }
        return function;
    }

    /**
     * Create an expression group. For example:
     *  Case (1) : A + B
     *     a binary tree with 2 leaf =>   +
     *                                  A   B
     *
     *  Case (2) : A + B - C
     *      -
     *    +   C
     *   A B
     */
    /*public ValueExpressionCombined createExpressionGroup(QueryValueExpression left, QueryValueExpression right, String operatorKind)
     {
     ValueExpressionCombined newGroup = factory.createValueExpressionCombined();
     newGroup.setCombinedOperator(ValueExpressionCombinedOperator.get(operatorKind)  );
     newGroup.setLeftValueExpr(left);
     newGroup.setRightValueExpr(right);

     if (left instanceof SQLExpressionGroup)
     {
     // The left is already an ExpressionGroup, so just set the left to this group
     newGroup.setLeft((SQLExpressionGroup)left);
     newGroup.setRight(createExpression(right));
     }
     else
     {
     // A brand new ExpressionGroup
     newGroup.setLeft(createExpression(left));
     newGroup.setRight(createExpression(right));
     }

     return newGroup;
     }*/

    /**
     * Creates a subquery statement expression
     */
    public ValueExpressionScalarSelect createScalarSelect(QueryStatement stmt) {
        ValueExpressionScalarSelect newSubQuery = factory.createValueExpressionScalarSelect();
        if (stmt instanceof QuerySelectStatement) {
            QueryExpressionRoot queryExpr = ((QuerySelectStatement) stmt).getQueryExpr();
            newSubQuery.setQueryExpr(queryExpr);
        }
        return newSubQuery;
    }

    /**
     * Creates a cast expression
     */
    public ValueExpressionCast createCast(QueryValueExpression expr, String dataType) {
        ValueExpressionCast sqlCast = factory.createValueExpressionCast();
        sqlCast.setValueExpr(expr);
        sqlCast.setDataType(DataTypeHelper.getPredefinedDataTypeForNamedType(dataType));
        return sqlCast;
    }

    /**
     * Searches the given SQL source string for a parameter (host variable) 
     * name and returns it.  Only the first parameter name in the string is
     * returned.  An empty string is returned if no parameter name is found. 
     * Text inside single quotes or double quotes is ignored.
     * Note: this routine does not know what the current database is, so
     * it is unable to determine what the correct parameter marker character
     * to look for is.  It will assume that any one of ':', '@', or '?' indicates the
     * start of a parameter name. 
     * @param source the SQL source to search
     * @return the parameter name found, or empty string if not found
     */
    protected String findParameterName(String source) {
        // b3170 bgp 20040727 - new method
        final int IN_PLAIN_TEXT = 0;
        final int IN_SINGLE_QUOTE = 1;
        final int IN_DOUBLE_QUOTE = 2;
        final int IN_PARAM = 3;

        String paramName = "";

        // Iterate character by character through the source string looking
        // for a valid parameter name.  The end of the parameter name is marked
        // by any of the defined set of delimeter characters.
        int scanState = IN_PLAIN_TEXT;
        int paramStartIndex = -1;
        int paramEndIndex = -1;
        String delims = " :@|&*/-+><(),\r\n\t";
        boolean needNext;
        StringCharacterIterator iter = new StringCharacterIterator(source);
        char c = iter.first();
        int scanIndex = 0;
        while ((c != CharacterIterator.DONE) && (paramName.length() == 0)) {
            needNext = true;
            switch (scanState) {
                case IN_PLAIN_TEXT:
                    if (c == '\'') {
                        scanState = IN_SINGLE_QUOTE;
                    }
                    else if (c == '"') {
                        scanState = IN_DOUBLE_QUOTE;
                    }
                    // Look for a parameter marker.  '@' is used my MySQL,
                    // Sybase, and SQLServer.  '?' is used by Cloudscape and
                    // DB2Everyplace.  The others (including DB2) use ':'.
                    else if (c == ':' || c == '@' || c == '?') {
                        scanState = IN_PARAM;
                        paramStartIndex = scanIndex;
                    }
                    break;
                case IN_SINGLE_QUOTE:
                    if (c == '\'') {
                        c = iter.next();
                        scanIndex++;
                        if (c != '\'') {
                            scanState = IN_PLAIN_TEXT;
                            needNext = false;
                        }
                    }
                    break;
                case IN_DOUBLE_QUOTE:
                    if (c == '"') {
                        c = iter.next();
                        scanIndex++;
                        if (c != '"') {
                            scanState = IN_PLAIN_TEXT;
                            needNext = false;
                        }
                    }
                    break;
                case IN_PARAM:
                    if (delims.indexOf(c) >= 0) {
                        paramEndIndex = scanIndex;
                        paramName = source.substring(paramStartIndex, paramEndIndex);
                        scanState = IN_PLAIN_TEXT;
                    }
                    break;
            }
            if (needNext == true) {
                c = iter.next();
                scanIndex++;
            }
        }

        // Handle the (not so) special case where the parameter name is at
        // the end of the source string.
        if (scanState == IN_PARAM) {
            paramEndIndex = source.length();
            paramName = source.substring(paramStartIndex, paramEndIndex);
        }

        return paramName;
    }

    /** keeps the Context of the variable which needs quotes.
     * depending upon the context "NULL" and "Default" 
     * is ignored from being quoted
     */
    private String QuotesContext = "";

    /**
     * Get the Context of the variable which needs quotes.
     * depending upon the context "NULL" and "Default" 
     * is ignored from being quoted
     * @return Returns the quotesContext.
     */
    public String getQuotesContext() {
        return QuotesContext;
    }

    /**
     * Set the Context of the variable which needs quotes.
     * depending upon the context "NULL" and "Default" 
     * is ignored from being quoted
     * @param quotesContext The quotesContext to set.
     */
    public void setQuotesContext(String quotesContext) {
        QuotesContext = quotesContext;
    }

    /**
     * Adds quotes to the string value passed which has given DataType.
     * @param the DataType of the value passed 
     * @param value the String value that needs quotes
     * @return quoted value
     */
    public String appendQuotes(DataType columnType, String value) {
        if (columnType instanceof PredefinedDataType) {
            int dataTypeInt = ((PredefinedDataType) columnType).getPrimitiveType().getValue();
            value = appendQuotes(dataTypeInt, value);
        }
        return value;
    }

    /**
     * Adds quotes to the string value passed.
     * @param columntype the name of the datatype 
     * @param value the String value that needs quotes
     * @return quoted value
     */
    public String appendQuotes(String columnType, String value) {
        int dataTypeInt = PrimitiveType.get(columnType).getValue();
        value = appendQuotes(dataTypeInt, value);
        return value;

    }

    /**
     * Adds quotes to the string value passed
     * @param columntype - SQLDefinedType datatype 
     * @param value - string value needs quotes
     * @return - string quoted value
     */
    public String appendQuotes(int columnType, String value) {
        // this condition checks if there is any parenthesis or concat symbol and 
        // assumes that this value does not needs quotes.
        if (value.indexOf('(') >= 0 || value.indexOf(')') >= 0 || value.indexOf('|') >= 0)
            return value;
        // this condition checks the context of the value and if context is insert or update ignore the 
        // "NULL" and "DEFAULT" from being quoted
        if (this.getQuotesContext().equalsIgnoreCase("insert") || this.getQuotesContext().equalsIgnoreCase("update")) {
            if (value.equalsIgnoreCase("NULL") || value.equalsIgnoreCase("DEFAULT")) {
                return value;
            }
        }
        switch (columnType) {
            // Character and date/time datatypes: format 'xxxx'
            case PrimitiveType.CHARACTER:
            case PrimitiveType.CHARACTER_VARYING:
            case PrimitiveType.CHARACTER_LARGE_OBJECT:
            case PrimitiveType.DATE:
                // Don't quote if already quoted.

                if ((!(value.startsWith("'") && value.endsWith("'"))) && (!(value.startsWith("\"") && value.endsWith("\""))) && (!containsParameterName(value))) {
                    value = "'" + value + "'";
                }
                break;
            case PrimitiveType.TIME:
            case PrimitiveType.TIMESTAMP:
                // Don't quote if already quoted.

                if ((!(value.startsWith("'") && value.endsWith("'"))) && (!(value.startsWith("\"") && value.endsWith("\""))) && (!firstParameterChar(value))) {
                    value = "'" + value + "'";
                }
                break;

            // Graphic, Vargraphic, and Longvargraphic datatypes: format G'xxxx' (or N'xxx')
            case PrimitiveType.NATIONAL_CHARACTER:
            case PrimitiveType.NATIONAL_CHARACTER_VARYING:
            case PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT:
                if (!(((value.startsWith("G'")) || (value.startsWith("g'")) || (value.startsWith("N'")) || (value.startsWith("n'"))) && (value.endsWith("'")))) {
                    // May already have quotes, but not the N.
                    if ((value.startsWith("'") && value.endsWith("'"))) {
                        value = "N" + value;
                    }
                    // Otherwise add the N and quotes.
                    else {
                        value = "N'" + value + "'";
                    }
                    //if LIKE predicate percent is used, replace it with double-byte percent
                    String percent = "\uFF05"; //double-byte percent
                    String gPercent = "N'" + percent;
                    String percentEnd = percent + "'";
                    if (value.startsWith("N'%")) {
                        value = new StringBuffer(value).replace(0, 3, gPercent).toString();
                    }
                    if (value.endsWith("%'")) {
                        value = new StringBuffer(value).replace(value.length() - 2, value.length(), percentEnd).toString();
                    }
                }
                break;

            // Binary datatypes:  format X'xxxx'
            case PrimitiveType.BINARY_LARGE_OBJECT:
            case PrimitiveType.BINARY_VARYING:
            case PrimitiveType.BINARY:
                if (!(((value.startsWith("X'")) || (value.startsWith("x'"))) && (value.endsWith("'")))) {
                    // May already have quotes, but not the X.
                    if ((value.startsWith("'") && value.endsWith("'"))) {
                        value = "X" + value;
                    }
                    // Otherwise add the X and the quotes.
                    else {
                        value = "X'" + value + "'";
                    }
                }
                break;

            // Otherwise, if the value has quotes but shouldn't, remove them.
            default:
                if (value.startsWith("'") && value.endsWith("'") && value.length() >= 2) { // b4096 - nb 20040818
                    value = value.substring(1, value.length() - 1);
                }
        } // switch

        return value;
    }

    /**
     * Unhooks all the <code>ValueExpressionColumn</code> s in the given
     * temporary <code>QuerySelect</code> and hooks them into the corresponding
     * <code>TableExpression</code> s in the given <code>tableExprList</code>,
     * regardless of duplicate column names - identical logical column references.
     * All the <code>ValueExpressionColumn</code> s in the given temporary
     * <code>QuerySelect</code> must reference to one
     * <code>TableReference</code> in the given temporary
     * <code>QuerySelect</code>'s<code>fromClause</code>, in order to be
     * detached from their temporary <code>TableReference</code> and attached to
     * a corresponding <code>TableReference</code> in the given
     * <code>tableExprList</code>.
     * <p><b>Note:</b> 
     * All <code>TableReference</code> in the given <code>QuerySelect</code>'s
     * <code>fromClause</code> and the given <code>tableExprList</code>, that
     * belong to the default Schema, must all either be not qualified with their
     * Schema name or must all be qualified with their Schema name.
     * </p>
     * 
     * @param select
     *             a temporary valid <code>QuerySelect</code> with
     *             <code>fromClause</code>
     * @param tableExpressionList
     *             a List of <code>TableExpression</code> s
     */
    public void resolveColumnReferencesInTemporaryStatement(QuerySelect select, List tableExprList) {

        // what comes in:
        //   select with table expressions in the from clause with column
        // expressions
        //     thus column exprs must have a table expression which could be the
        //   existing table expressions in a List

        // iterate over table expressions in the select stmt
        //   find table expression w/ matching name in tableExprList
        //   add all column references from the temp stmt's table to the table
        //     in the tableExprList
        //   remove all columns from the temp table

        if (select != null && select.getFromClause() != null) {
            List fromTableList = TableHelper.getTableExpressionsInTableReferenceList(select.getFromClause());

            for (Iterator fromTableIt = fromTableList.iterator(); fromTableIt.hasNext();) {
                TableExpression fromTable = (TableExpression) fromTableIt.next();
                String schemaName = TableHelper.getSchemaNameForTableExpression(fromTable);
                String tableName = fromTable.getName();

                TableExpression matchingTable = TableHelper.findTableExpressionInTableExpressionList(schemaName, tableName, tableExprList);

                if (matchingTable != null) {
                    matchingTable.getValueExprColumns().addAll(fromTable.getValueExprColumns());
                    // remove columns explizitely cause they are not EMF-containment referenced by tableExpression
                    fromTable.getValueExprColumns().clear();
                }
            }

        }
    }

    public static ValueExpressionNested createNestedExpression(QueryValueExpression expr) {
        ValueExpressionNested nestingExpr = factory.createValueExpressionNested();
        nestingExpr.setNestedValueExpr(expr);
        return nestingExpr;

    }

    public static boolean isComplete(QueryValueExpression expr) {

        boolean isComplete = true;
        if (expr instanceof ValueExpressionCombined) {
            ValueExpressionCombined parent = (ValueExpressionCombined) expr;

            QueryValueExpression leftChild = parent.getLeftValueExpr();
            QueryValueExpression rightChild = parent.getRightValueExpr();
            if (leftChild != null && rightChild == null) {
                isComplete = false;
            }
        }
        return isComplete;
    }

    /**
     * Replaces an expression in  an expression tree with a new expression.
     * If the expression is to be replaced is not a node in a tree of expressions,
     * nothing is changed
     * The expression tree for the expression  A + B - C is as follows
     *      -
     *    +   C
     *   A B
     * @param oldExpr the QueryValueExpression to be replaced 
     * @param newExpr the new QueryValueExpression 
     */
    public static void replaceExpression(QueryValueExpression oldExpr, QueryValueExpression newExpr) {
        if (oldExpr != null) {
            ValueExpressionCombined parent = oldExpr.getValueExprCombinedLeft();
            if (parent != null) { //oldExpr is the child on the left side
                parent.setLeftValueExpr(newExpr);
                newExpr.setValueExprCombinedLeft(parent);
            }
            else {
                parent = oldExpr.getValueExprCombinedRight();
                if (parent != null) { //oldExpr is the child on the right side
                    parent.setRightValueExpr(newExpr);
                    newExpr.setValueExprCombinedRight(parent);

                }
            }
        }

    }

    /**
     * Replaces the operator on a ValueExpressionCombined node in the expression tree
     * The expression tree for the expression  A + B - C is as follows
     *      -
     *    +   C
     *   A B
     *
     * @param expression the expression in the same row as that of the operator being replaced, int the expressionbuilder UI 
     * @param newOperator the new operator to be set in the tree
     * 
     */
    public static void replaceOperator(QueryValueExpression expression, ValueExpressionCombinedOperator newOperator) {
        if (expression != null) {
            ValueExpressionCombined parent = expression.getValueExprCombinedLeft();
            if (parent != null) {
                // if expression is the lef side child of a node, replace the opeartor of its parent node
                parent.setCombinedOperator(newOperator);
            }
            else {
                // if expression is the right side child of a node,the operator to be replaced is the one for its parent's parent
                parent = expression.getValueExprCombinedRight();
                if (parent != null) {
                    ValueExpressionCombined gParent = parent.getValueExprCombinedLeft();
                    if (gParent != null) {
                        gParent.setCombinedOperator(newOperator);
                    }
                }
            }
        }

    }

    /**
     * Adds an expression to the expression tree 
     * @param prevExpr the expression the row above this expression is to be added in the expression builder UI
     * @param newExpr the new expression to be added to the tree
     * @param operator the new operator
     */
    public static void addExpression(QueryValueExpression prevExpr, QueryValueExpression newExpr, ValueExpressionCombinedOperator operator) {
        if (prevExpr != null) {
            ValueExpressionCombined parent = prevExpr.getValueExprCombinedLeft();
            if (parent != null) { // set newExpr on right side if right child is null
                if (parent.getRightValueExpr() == null) {
                    parent.setRightValueExpr(newExpr);
                    if (newExpr != null) {
                        newExpr.setValueExprCombinedRight(parent);
                    }
                }
            }
            else { // create a new level in the tree
                parent = prevExpr.getValueExprCombinedRight();
                ValueExpressionCombined newParent = ExpressionHelper.factory.createValueExpressionCombined();

                if (parent != null) {
                    newParent.setLeftValueExpr(parent);
                    parent.setValueExprCombinedLeft(newParent);
                }
                else {
                    newParent.setLeftValueExpr(prevExpr);
                    prevExpr.setValueExprCombinedLeft(newParent);
                }

                newParent.setRightValueExpr(newExpr);
                if (newExpr != null) {
                    newExpr.setValueExprCombinedRight(newParent);
                }

                newParent.setCombinedOperator(operator);

            }
        }

    }

    /**
     * Removes the given expression from a tree of expressions
     * The expression tree for the expression  A + B - C is as follows
     *      -
     *    +   C
     *   A B
     *     
     * @param expr the expression to be removed
     */
    public static void removeExpression(QueryValueExpression expr) {
        if (expr != null) {
            ValueExpressionCombined parent = expr.getValueExprCombinedLeft();
            if (parent != null) {
                // expr is the child on the left side and is a leaf node  
                QueryValueExpression rightChild = parent.getRightValueExpr();
                if (rightChild != null) {
                    ValueExpressionCombined gParent = parent.getValueExprCombinedLeft();
                    if (gParent != null) {
                        // move rightChild one level up in the tree , to set it as the left side
                        // of its parent's parent node
                        gParent.setLeftValueExpr(rightChild);
                        rightChild.setValueExprCombinedLeft(gParent);
                        // this seems to set the left reference also to null rightChild.setValueExprCombinedRight(null); 
                    }

                    else {
                        // there only one level in the tree , move rightChild to the left side of its parent
                        parent.setLeftValueExpr(rightChild);
                        parent.setRightValueExpr(null);
                        rightChild.setValueExprCombinedLeft(parent);
                        rightChild.setValueExprCombinedRight(null);
                    }
                }
                else {
                    // the node being removed is the only node in the tree
                    parent.setLeftValueExpr(null);
                }
            }
            else {
                parent = expr.getValueExprCombinedRight();
                if (parent != null) {
                    // the node to be removed is the child on the right side
                    QueryValueExpression leftChild = parent.getLeftValueExpr();
                    ValueExpressionCombined gParent = parent.getValueExprCombinedLeft();
                    if (gParent != null) {
                        gParent.setLeftValueExpr(leftChild);
                        if (leftChild != null) {
                            leftChild.setValueExprCombinedLeft(gParent);
                        }
                    }
                    else {
                        leftChild.setValueExprCombinedLeft(null);
                    }
                }
            }
        }
    }

    /*    public static ValueExpressionCombined getAppendableNode(ValueExpressionCombined node){
     ValueExpressionCombined targetNode = null;
     QueryValueExpression left = node.getLeftValueExpr();
     QueryValueExpression right = node.getRightValueExpr();
     if(left == null || right == null){
     targetNode = node;
     }
     else {
     ValueExpressionCombined leftParent = node.getValueExprCombinedLeft();
     if(leftParent instanceof ValueExpressionCombined){
     targetNode = getAppendableNode(leftParent);
     }
     else if(leftParent == null){
     targetNode = node;
     }
     }
     return targetNode;
     }
     */

    public static String getDisplayString(String opString) {
        String displayStr = "";
        if (opString.equals("ADD")) {
            displayStr = "+";
        }
        else if (opString.equals("SUBTRACT")) {
            displayStr = "-";
        }
        else if (opString.equals("MULTIPLY")) {
            displayStr = "*";
        }
        else if (opString.equals("DIVIDE")) {
            displayStr = "/";
        }
        else if (opString.equals("CONCATENATE")) {
            displayStr = "CONCAT";
        }
        return displayStr;
    }

    public static ValueExpressionCombinedOperator createCombinedOperator(String opType) {
        String operator = null;
        if (opType.equals("+")) {
            operator = "ADD";
        }
        else if (opType.equals("-")) {
            operator = "SUBTRACT";
        }
        else if (opType.equals("*")) {
            operator = "MULTIPLY";
        }
        else if (opType.equals("/")) {
            operator = "DIVIDE";
        }
        else if (opType.equals("CONCAT")) {
            operator = "CONCATENATE";
        }
        ValueExpressionCombinedOperator combOpr = ValueExpressionCombinedOperator.get(operator);
        return combOpr;
    }

    /**
     * Returns the root node of the tree in which the given node is a left side child.
     * Traverses up the tree recursively, finding in each recursion, the parent node of which 
     * the given node is a left side child.Recursion does not proceed if the given node is a right child of 
     * any parent node
     * @param node a node in the tree
     * @return 
     */
    public static QueryValueExpression getRoot(QueryValueExpression node) {
        QueryValueExpression root = node;
        QueryValueExpression temp;
        if (node != null) {
            temp = node.getValueExprCombinedLeft();
            if (temp != null) {
                root = getRoot(temp);
            }
        }
        return root;
    }

    public static QueryValueExpression getLowestLeftChild(QueryValueExpression expr) {
        QueryValueExpression lowestLeftChild = expr;
        if (expr instanceof ValueExpressionCombined) {
            QueryValueExpression leftChild = ((ValueExpressionCombined) expr).getLeftValueExpr();
            if (leftChild != null) {
                lowestLeftChild = getLowestLeftChild(leftChild);
            }
        }
        return lowestLeftChild;
    }

    /**
     * Returns the TableExpression for the given ValueExpressionColumn.
     * @param colValExp the given ValueExpressionColumn for which TableExpression is needed
     * @return the TableExpression
     */
    public static TableExpression getTableExprForValueExpressionColumn(ValueExpressionColumn colValExp) {
        TableExpression retTableExpr = null;
        retTableExpr = colValExp.getParentTableExpr();       
        if (retTableExpr == null) {
            retTableExpr = colValExp.getTableExpr();            
        }
        return retTableExpr;
    }
    
    //  [RATLC00485883] bgp 13Sept2006 - new method
    /**
     * Gets an acceptable default value (as a string) for the given database 
     * column, based on its datatype.  This can be used for INSERT and UPDATE
     * statements.  String and date, time, and timestamp values are surrounded
     * by single quotes.
     * 
     * @param aColumn a column for which the default value is needed
     * @return the default value string 
     */
    public static String getDefaultValueForColumn(Column aColumn) {
        /* Default is an empty string.  This should work for all
         * string types. */
        String defaultValue = "''";
        if (aColumn != null) {
            DataType datatype = aColumn.getDataType();
            if (datatype instanceof NumericalDataType) {
                if (datatype instanceof ApproximateNumericDataType) {
                    /* This is Float, Real, and Double. */
                    defaultValue = "0.0";
                }
                else if (datatype instanceof FixedPrecisionDataType) {
                    /* This is Numeric and Decimal. */
                    defaultValue = "0.0";
                }
                else {
                    /* This is the other numeric types: Integer, SmallInt, BigInt. */
                    defaultValue = "0";
                }
            }
            /* For the Date, Time, and Timestamp types we will use the 
             * ISO format. */
            else if (datatype instanceof DateDataType) {
                defaultValue = "'2000-01-01'";
            }
            else if (datatype instanceof TimeDataType) {
                /* Time types have two sub-types:  TIME and TIMESTAMP. */
                TimeDataType timetype = (TimeDataType) datatype;
                PrimitiveType primitiveType = timetype.getPrimitiveType();
                if (primitiveType.getValue() == primitiveType.TIME) {
                    defaultValue = "'00.00.00'";
                }
                else if (primitiveType.getValue() == primitiveType.TIMESTAMP) {
                    defaultValue = "'2000-01-01-00.00.00'";
                }
            }
        }
        
        return defaultValue;
    }

} // end class
