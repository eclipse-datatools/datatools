/*******************************************************************************
 * Copyright (c) 2004, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.query.test;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.datatools.modelbase.sql.query.CallStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceInfo;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserManager;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager;
import org.eclipse.datatools.sqltools.parsers.sql.test.AbstractTestSQLParser;
import org.eclipse.datatools.sqltools.parsers.sql.test.TestSQLParser;

/**
 * @author ckadner
 *
 */
public abstract class AbstractTestSQLQueryParser extends AbstractTestSQLParser
{

    private static boolean isTestIsRegistrationChecked = false;
    
    /** The class of the TestSuite that should contain this test case - to be
     * overwritten in sub-class with TestSuite class for the test-sub-package!*/
    protected static Class testSuite = AllTests.class;
    
    /**
     * Public constructor used by JUnit for this
     * <code>AbstractTestSQLQueryParser</code>.
     * For subclass use {@link #AbstractTestSQLQueryParser(String, Class)}!
     * 
     * @param name name of the test case (test~ method's name)
     */
    public AbstractTestSQLQueryParser(String name)
    {
        super(name, SQLQueryParserManager.class);
        checkIfTestIsRegistred();
    }

    /**
     * Protected constructor to be used by subclass.
     * 
     * @param name name of the test case (test~ method's name)
     * @param parserManagerClass the type of the <code>SQLParserManager</code>
     * to be instanciated for this <code>AbstractTestSQLQueryParser</code>.
     */
    protected AbstractTestSQLQueryParser(String name, Class parserManagerClass)
    {
        super(name, parserManagerClass);
        checkIfTestIsRegistred();
    }
    
    
    /**
     * Sets the parser managers source format to the
	 * default <code>SQLQuerySourceFormat</code> in case a
	 * test case modifies it and fails, ergo fails to reset it
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        if (getParserManager() != null) {
            getParserManager().getSourceFormat().copyFields(SQLQuerySourceFormat.SQL_SOURCE_FORMAT_DEFAULT);
        }
    }
    
    /**
     * Sets the parser managers source format to the
     * default <code>SQLQuerySourceFormat</code> in case a
     * test case modifies it and fails, ergo fails to reset it
     * @throws Exception
     * @see junit.framework.TestCase#setUp()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        if (getParserManager() != null) {
            getParserManager().getSourceFormat().copyFields(SQLQuerySourceFormat.SQL_SOURCE_FORMAT_DEFAULT);
        }
    }
    
    
    /**
     * Checks if this testcase is in the <code>AllTests</code>
     * <code>TestSuite</code>.
     * 
     * @see AllTests#registredTestClasses
     */
    protected void checkIfTestIsRegistred()
    {
        if (!isTestIsRegistrationChecked)
        {
            isTestIsRegistrationChecked = true; // only check once for one run of multiple test cases
            
	        boolean isRegistred = false;
	        Class[] allRegistredTests = null;
	        
	        //get the TestSuite "AllTests" for this TestPackage
	        if (testSuite.getPackage() != this.getClass().getPackage())
	        {
	            String testSuiteClassName =
	                this.getClass().getPackage().getName() + ".AllTests"; //$NON-NLS-1$
	            try
                {
                    testSuite = Class.forName(testSuiteClassName);
                    
                }
                catch (ClassNotFoundException e)
                {
                    System.err.println("TestSuite " + testSuiteClassName + //$NON-NLS-1$
                                    " not found. It should contain this" + //$NON-NLS-1$
                                    " TestCase: " + this.getClass().getName() + //$NON-NLS-1$
                                    " in its registredTestClasses[]."); //$NON-NLS-1$
                }
                
                // get the registered TestCases for this packages TestSuite
                try
                {
                    Field testSuiteTestCases =
                        testSuite.getDeclaredField("registredTestClasses"); //$NON-NLS-1$
                    testSuiteTestCases.setAccessible(true);
                    allRegistredTests = (Class[]) testSuiteTestCases.get(null);
                }
                catch (SecurityException e1)
                {
                    System.err.println("TestSuite " + testSuite.getName() + //$NON-NLS-1$
                                    " does not allow access to the field" + //$NON-NLS-1$
                                    " registredTestClasses[]" + //$NON-NLS-1$
                                    " ...SecurityException."); //$NON-NLS-1$
                }
                catch (NoSuchFieldException e1)
                {
                    System.err.println("TestSuite " + testSuite.getName() + //$NON-NLS-1$
                                    " has no field registredTestClasses[]" + //$NON-NLS-1$
                                    " ...NoSuchFieldException."); //$NON-NLS-1$
                }
                catch (IllegalAccessException e1) {
                    System.err.println("TestSuite " + testSuite.getName() + //$NON-NLS-1$
                                    ".registredTestClasses[] can not be" + //$NON-NLS-1$
                                    " accessed" + //$NON-NLS-1$
                                    " ...IllegalAccessException."); //$NON-NLS-1$
                }
                catch (ClassCastException e1) {
                    System.err.println("TestSuite " + testSuite.getName() + //$NON-NLS-1$
                                    ".registredTestClasses is not of type" + //$NON-NLS-1$
                                    " Class[]" + //$NON-NLS-1$
                                    " ...ClassCastException, but it should" + //$NON-NLS-1$
                                    " contain the TestCase classes it suits"); //$NON-NLS-1$
                }
                
	        }
	        if (allRegistredTests == null) {
	            allRegistredTests = AllTests.registredTestClasses;
	        }
	        
	        
	        Class thisTestClass = this.getClass();
	        
	        for (int i = 0; i < allRegistredTests.length; i++)
	        {
	            Class registredTestClass = allRegistredTests[i];
	            if (thisTestClass == registredTestClass)
	            {
	                isRegistred = true;
	                break;
	            }
	        }
	        
	        if (!isRegistred)
	        {
	            System.err.println("WARNING: TestCase "+thisTestClass.getName() //$NON-NLS-1$
	                            +  " is not registered in TestSuite " //$NON-NLS-1$
	                            + testSuite.getName()
	                            + "\n"); //$NON-NLS-1$
	        }
        }
    }




    /**
     * <b>Note: </b> to modify the variable character mapping for the parser,
     * set the special character mapping on the
     * <code>SQLQuerySourceFormat</code> (e.g.
     * {@link SQLQuerySourceFormat#setHostVariablePrefix(char)}) and set this
     * modification into effect for the next parse by calling
     * {@link #updateParserManagerCharacterMapping()}
     * 
     * @return the <code>SQLQuerySourceFormat</code> of this
     *         <code>TestSQLQueryParserSelect</code>'s
     *         <code>SQLParserManager</code>
     */
    protected SQLQuerySourceFormat getSourceFormat() {
        SQLQuerySourceFormat sourceFormat =
            getParserManager().getSourceFormat();
        return sourceFormat;
    }
    
    
    /**
     * Update the variable character mapping of this
     * <code>TestSQLQueryParserSelect</code>'s<code>SQLParserManager</code>,
     * after variable character mapping was modified on
     * {@link #getSourceFormat()}
     * 
     * @see #getSourceFormat()
     */
    protected void updateParserManagerCharacterMapping() {
        getParserManager().setSourceFormat(getParserManager().getSourceFormat());
    }
  
    
    
    /**
     * Type cast proxy to {@link TestSQLParser#parserVerifySuccessSingle(String, boolean)}
     * @param p_stmt
     * @param p_matchInput if true the input SQL will be matched against the
     * generated output SQL
     * @return
     * @throws SQLParserException TODO
     */
    public QueryStatement parserVerifySuccessSingleQuery(String p_stmt, boolean p_matchInput)
    	throws SQLParserException,SQLParserInternalException
    {
    	return (QueryStatement) parserVerifySuccessSingle(p_stmt, p_matchInput);
    }

    /**
     * Parses the given statement, which must be a CALL statement. The statement is assumed
     * to parse correctly.  The generated statement will be compared to the input statement
     * when the match input parm is true.
     * 
     * @param p_stmt the statement to parse
     * @param p_matchInput true when the generated output statement should be matched against
     * the input statement, otherwise false
     * @return the generated CALL statement object
     * @throws SQLParserException when the statement does not parse
     * @throws SQLParserInternalException when a parser internal error occurs
     */
    public CallStatement parserVerifySuccessSingleCall(String p_stmt, boolean p_matchInput)
        throws SQLParserException,SQLParserInternalException
    {
        return (CallStatement) parserVerifySuccessSingle(p_stmt, p_matchInput);
    }
    
    /**
     * @param stmt
     * @param columnSQLSource
     * @return
     */
    protected ValueExpressionColumn getColumnForSQLSource(QuerySelectStatement stmt, String columnSQLSource)
    {
        if (stmt == null)
        {
            return null;
        }
        
        ValueExpressionColumn colFound = null;
        
        char identDelimQt =
            stmt.getSourceInfo().getSqlFormat().getDelimitedIdentifierQuote();
        
        Set allStmtColumns =
            TableHelper.findColumnReferencesInQuerySelectStatement(stmt);
        
        for (Iterator it = allStmtColumns.iterator(); it.hasNext();)
        {
            ValueExpressionColumn colExpr = (ValueExpressionColumn) it.next();
            SQLQuerySourceInfo sourceInfo = colExpr.getSourceInfo();
            
            if (columnSQLSource != null
                            && sourceInfo != null
                            && StatementHelper.equalSQLIdentifiers(
                                            columnSQLSource,
                                            sourceInfo.getSourceSnippet(),
                                            identDelimQt))
            {
                colFound = colExpr;
                break;
            }
        }

        return colFound;
    }


    protected ResultColumn getResultColumn(QuerySelectStatement stmt, int number) {
        ResultColumn resultCol = null;
        
        if (stmt != null && stmt.getQueryExpr() != null) {
            QueryExpressionBody queryExpr = stmt.getQueryExpr().getQuery();
            
            if (queryExpr instanceof QuerySelect) {
                QuerySelect querySelect = (QuerySelect) queryExpr;
                List resultColumns = querySelect.getSelectClause();
                
                if (number > -1 && number < resultColumns.size()) {
                    QueryResultSpecification resultSpec =
                        (QueryResultSpecification) resultColumns.get(number);
                    
                    if (resultSpec instanceof ResultColumn) {
                        resultCol = (ResultColumn) resultSpec;
                    }
                }
            }
        }

        
        return resultCol;
    }
    
    /**
     * @param stmtList
     * @param stmtNumber
     * @param tableSQLSource
     * @return
     */
    protected boolean columnRefTable(List stmtList, int stmtNumber, String tableSQLSource)
    {
        boolean columnResolved = false;
        try
        {
            QuerySelectStatement stmt = 
                (QuerySelectStatement) stmtList.get(stmtNumber);
            QuerySelect select =
                (QuerySelect) stmt.getQueryExpr().getQuery();
            ResultColumn resultColumn = 
                (ResultColumn) select.getSelectClause().get(0);
            ValueExpressionColumn colExpr =
                (ValueExpressionColumn) resultColumn.getValueExpr();
            
            TableExpression tableExpr =
                colExpr.getTableExpr();
            
            if (tableSQLSource == null)
            {
                // we assume that the fake table of the column was not
                // generated by parser and has no sourceInfo,
                // if it was resolved then it is the parser generated table
                // with the sourceInfo
                columnResolved =
                    tableExpr == null
                    || tableExpr.getSourceInfo() == null
                    || tableExpr.getSourceInfo().getSourceSnippet() == null;
            }
            else
            {
	            String tableExprSQLSource =
	                tableExpr.getSourceInfo().getSourceSnippet();
	            
                columnResolved = tableSQLSource.trim().equals(tableExprSQLSource);
	            
	            // maybe only the table alias was given
	            if (!columnResolved && tableExpr.getTableCorrelation() != null)
	            {
	                TableCorrelation tableAlias =
	                    tableExpr.getTableCorrelation();
	                
	                String tableAliasSQLSource =
	                     tableAlias.getSourceInfo().getSourceSnippet();
	                
		            columnResolved = tableSQLSource.trim().equals(tableAliasSQLSource);
	            }
            }
        }
        catch (Exception e)
        {
            // do nothing
        }
        return columnResolved;
    }


    /**
     * @param stmtList
     * @param stmtNumber
     * @param columnSQLSource
     * @param tableSQLSource
     * @return
     */
    protected boolean columnRefTable(List stmtList, int stmtNumber, String columnSQLSource, String tableSQLSource)
    {
        QuerySelectStatement stmt =
            (QuerySelectStatement) stmtList.get(stmtNumber);
        return columnRefTable(stmt, columnSQLSource, tableSQLSource);
    }


    /**
     * @param stmt
     * @param stmtNumber
     * @param columnSQLSource
     * @param tableSQLSource
     * @return
     */
    protected boolean columnRefTable(QuerySelectStatement stmt, String columnSQLSource, String tableSQLSource)
    {
        boolean correctlyResolved = false;
        try
        {
            ValueExpressionColumn colExpr =
                getColumnForSQLSource(stmt, columnSQLSource);
            
            TableExpression tableExpr =
                colExpr.getTableExpr();
            
            if (tableSQLSource == null)
            {
                // we assume that the fake table of the column was not
                // generated by parser and has no sourceInfo
                // if it was resolved then it is the parser generated table
                // with the sourceInfo
                correctlyResolved = 
                    tableExpr == null
//                    || tableExpr.getSourceInfo() == null
//                    || tableExpr.getSourceInfo().getSourceSnippet() == null;
                    || tableExpr.eContainer() == null;
            }
            else
            {
	            String tableExprSQLSource =
	                tableExpr.getSourceInfo().getSourceSnippet();
                
                correctlyResolved = tableSQLSource.trim().equals(tableExprSQLSource);
	            
	            if (!correctlyResolved
	                            && tableExpr.getTableCorrelation() != null)
	            {
	                // check if we were given the table alias
	                String tableAliasSQLSource =
	                    tableExpr.getTableCorrelation().getSourceInfo().getSourceSnippet();

	                correctlyResolved = tableSQLSource.trim().equals(tableAliasSQLSource);
	            }
            }
        }
        catch (Exception e)
        {
            // do nothing
        }
        return correctlyResolved;
    }


	/**
	 * Matches the input SQL source against the generated output SQL source.
	 * 
     * @param p_script
     * @param parseResultStmts
     */
    protected void matchInputScript(String p_script, List parseResultStmts)
    {
        for (Iterator it = parseResultStmts.iterator(); it.hasNext();)
        {
            QueryStatement stmt = (QueryStatement) it.next();
            
            matchInputStatement(stmt.getSourceInfo().getSourceSnippet(), stmt);
        }

    }
    
    /**
     * Gets the current <code>SQLQuerySourceFormat</code> <code>generateAsKeywordForTableCorrID</code> option setting.
     * 
     * @return the parser manager source format setting
     */
    protected boolean getGenerateAsKeywordForTableCorrID() {
        SQLParserManager parserMgr = getParserManager();
        SQLQuerySourceFormat sourceFormat = parserMgr.getSourceFormat();
        boolean genAsKeywordForTableCorrID = sourceFormat.getGenerateAsKeywordForTableCorrID();
        return genAsKeywordForTableCorrID;
    }
    
    /**
     * Sets the <code>SQLQuerySourceFormat</code> <code>generateAsKeywordForTableCorrID</code> option setting 
     * to the given value.  Subsequently, SQL generated from the query model will use this setting.
     * 
     * @param generateAsKeywordForTableCorrID the option value to set
     */
    protected void setGenerateAsKeywordForTableCorrID(boolean genAsKeywordForTableCorrID) {
        SQLParserManager parserMgr = getParserManager();
        SQLQuerySourceFormat sourceFormat = parserMgr.getSourceFormat();
        SQLQuerySourceFormat sourceFormat2 = SQLQuerySourceFormat.copySourceFormat(sourceFormat);
        sourceFormat2.setGenerateAsKeywordForTableCorrID(genAsKeywordForTableCorrID);
        parserMgr.setSourceFormat(sourceFormat2);
    }

}
