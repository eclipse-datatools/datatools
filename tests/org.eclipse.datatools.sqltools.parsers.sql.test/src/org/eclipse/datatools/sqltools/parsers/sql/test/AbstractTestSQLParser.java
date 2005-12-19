/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParseErrorInfo;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParseResult;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserManager;

/**
 * Base TestCase for <code>SQLParser</code>.
 * 
 * @author ckadner
 *
 */
public abstract class AbstractTestSQLParser extends TestCase
{
    /** if set to true the successfuly parsed statements AST will be printed out
     * and a char index ruler to detect parser errors */
    protected boolean DEBUG = true;
    
    /** if set to true the successfuly parsed statements' SQL will be printed out */
    protected boolean CONTROL = true;
    
    /** if set to true the successfuly parsed statements' semantical errors be printed out */
    protected boolean PRINT_ERRORS = true;

    /** if true, only empty QueryStatement objects will be returned for parse */
    protected boolean syntaxCheckOnly = false;

    /** if true, for every parse the input SQL source will be compared against
     *  the generated SQL source ignoring whitespace and case. */
    protected boolean matchInput = true;
    

    
    protected static final String TEST_SEPARATOR = "\n\n * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * "; //$NON-NLS-1$
    
    
    /** newline character "\n" */
    protected static final char NL = '\n';


    private static boolean isTestIsRegistrationChecked = false;
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
            isTestIsRegistrationChecked = true;
            
	        boolean isRegistred = false;
	        Class[] allRegistredTest = AllTests.registredTestClasses;
	        Class thisTestClass = this.getClass();
	        
	        for (int i = 0; i < allRegistredTest.length; i++)
	        {
	            Class registredTestClass = allRegistredTest[i];
	            if (thisTestClass == registredTestClass)
	            {
	                isRegistred = true;
	                break;
	            }
	        }
	        
	        if (!isRegistred)
	        {
	            System.err.println("\nTestCase "+thisTestClass.getName() //$NON-NLS-1$
	                            +  " is not registered in TestSuite " //$NON-NLS-1$
	                            + AllTests.class.getName()
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
     * Can't be an instance variable cause static fields are the only possibilty
     * to share information between different test cases (each test case is run
     * in new single instance of the test class)
     * SQLParserManager non-static here would only allow 15 test cases to run,
     * and then fail with OutOfMemoryError. Why? maybe because multi-threaded
     * execution of test cases, however a static reference to the
     * SQLParaserManager solves the problem TODO: fix that restriction! */
    private static SQLParserManager staticParserManager = null;
    
    
    /** The ParserManager for parsing the test cases 
     * @return*/
    protected SQLParserManager getParserManager() {
        return staticParserManager;
    }
    
    /**
     * Sets the ParserManager for parsing in the test cases.
     * @param parserManager
     * @see #parserVerifySuccess(String, boolean)
     * @see #parserVerifySuccessSingle(String, boolean)
     * @see #parserVerifyError(String)
     * @see #parserVerifyErrorSingle(String)
     */
    protected void setParserManager(SQLParserManager parserManager) {
        staticParserManager = parserManager;
    }

    /**
     * Public constructor only used by JUnit for this
     * <code>AbstractTestSQLParser</code>.
     * For subclass use {@link #AbstractTestSQLParser(String, Class)}!
     * 
     * @param name name of the test case (test~ method's name)
     */
    public AbstractTestSQLParser(String name)
    {
        super(name);
    }


    
    /**
     * Protected constructor to be used by subclass.
     * 
     * @param name name of the test case (test~ method's name)
     * @param parserManagerType the type of the <code>SQLParserManager</code>
     * to be instanciated for this <code>AbstractTestSQLParser</code> .
     */
    protected AbstractTestSQLParser(String name, Class parserManagerType) {
        super(name);

        if (parserManagerType == null)
        {
            throw new IllegalArgumentException(
                        "parserManagerType must not be null"); //$NON-NLS-1$
        }
        if (!SQLParserManager.class.isAssignableFrom(parserManagerType))
        {
            throw new IllegalArgumentException(parserManagerType.getName()+
                            " is not of type " + //$NON-NLS-1$
                            SQLParserManager.class.getName());
        }
        // create a new SQLParserManager only if we not already have a static
        // one, as JUnit creates a new TestCase instance for every test~ method
        // or if the one instanciated already is not a subclass of the type given
        if (getParserManager() == null ||
                        !parserManagerType.isAssignableFrom(getParserManager().getClass()))
        {
            try
            {
                SQLParserManager parserMan =
                    (SQLParserManager) parserManagerType.newInstance();
                setParserManager(parserMan);
                parserMan.setSourceFormat(SQLQuerySourceFormat
                                .copyDefaultFormat());
            }
            catch (InstantiationException e)
            {
                RuntimeException iae = new IllegalArgumentException(
                                parserManagerType.getName()
                                                + " could not be instanciated" //$NON-NLS-1$
                                                + " using empty constructor."); //$NON-NLS-1$
                iae.initCause(e);
                throw iae;
            }
            catch (IllegalAccessException e)
            {
                RuntimeException iae =
                    new IllegalArgumentException(parserManagerType.getName()
                                    + " could not be instanciated," //$NON-NLS-1$
                                    + " empty constructor not accessible."); //$NON-NLS-1$
                iae.initCause(e);
                throw iae;
            }
             
        }

        if (getParserManager() != null) {
            getParserManager().debugPerformance = true;
        }
        
        
        if (!DEBUG) {
            getParserManager().setPostParseProcessors(null);
        }
        
        initTestStatistics();
    }


    static protected long timeStart = 0; // must be static cause we have multiple instances for a test run of a TestCase with more than one test~ method
    
    /**
     * initialization for test statistics, method will be executed by test
     * constructor.
     *
     */
    protected void initTestStatistics()
    {
        if (timeStart > 0) {
            // initTestStatistics will be called for every instance of
            // this TesCase, means once for every test~~~~ method
            // we want to only execute it once
            return;
        }
        System.out.println("test init"); //$NON-NLS-1$
        getParserManager().astElementCount = 0;
        getParserManager().statementCount = 0;
        getParserManager().byteCount = 0;
        getParserManager().timeCount = 0;
        timeStart = System.currentTimeMillis();
    }
    
    /** 
     * Public statistics, method will be executed after all test~ methods
     * are executed, included in test run as fake public test method in the end.
     */
    public void test_Statistics()
    {
        System.out.println("\n\n\nstatistics:\n"); //$NON-NLS-1$
        System.out.println("============================================="); //$NON-NLS-1$
        if(getParserManager() != null){
            int bytes = getParserManager().byteCount;
            String size = String.valueOf( bytes );
            long timeConsumed = System.currentTimeMillis() - timeStart;
            long parseTime = getParserManager().timeCount;
            
            double perf = (double)bytes/((double)parseTime*1.024);
            DecimalFormat df = new DecimalFormat( "0.00" ); //$NON-NLS-1$
            String performance = df.format( perf );
            
            System.out.println("total QueryStatements: "+getParserManager().statementCount); //$NON-NLS-1$
            System.out.println("total SQLQueryObjects: "+getParserManager().astElementCount); //$NON-NLS-1$
            System.out.println("total size parsed:     "+size+" byte"); //$NON-NLS-1$ //$NON-NLS-2$
            System.out.println("total time parser:     "+parseTime+" ms"); //$NON-NLS-1$ //$NON-NLS-2$
            System.out.println("parser performance:    "+performance+" Kbyte/sec"); //$NON-NLS-1$ //$NON-NLS-2$
            System.out.println("============================================="); //$NON-NLS-1$
            System.out.println("total time consumed:   "+timeConsumed+" ms"); //$NON-NLS-1$ //$NON-NLS-2$
            
            //System.out.println("remapping time:   "+SQLParser.timeConsumedByTokenKindRemapping+" ms");
            
            //timeStart = 0;
        }
    }

    

    
/*    public void testSqlDmlParser001() {
        System.out.println("test 001");
        parserVerifySuccess(
			"select * from employee;");
    }
*/
    

    
    
    /**
     * @param p_script SQL script String
     * @param p_matchInput flag to indicate whether or not the input SQL is
     *        checked against the generated SQL
     * @return List of <code>SQLStatement</code> s
     */
    protected List parserVerifySuccess(String p_script, boolean p_matchInput) 
    	throws SQLParserException,SQLParserInternalException
    
    {
        try {
            
            if (DEBUG) {
	            printColumnIndexNumbers();
	            System.out.println(p_script);
	        }
            else if (CONTROL) 
            {
                System.out.println("parsing \n" + p_script); //$NON-NLS-1$
            }
            
            List parseResultList = Collections.EMPTY_LIST;
            
            if (syntaxCheckOnly) {
                parseResultList = getParserManager().checkSyntaxScript(p_script);
            } else {
                parseResultList = getParserManager().parseScript(p_script);
            }
            List parseResultStmts = new ArrayList();
            
            assertNotNull("Result list expected for parse of "+p_script,parseResultList); //$NON-NLS-1$
            
            int i = 0;
            for (Iterator resultIt = parseResultList.iterator(); resultIt.hasNext();i++)
            {
                SQLStatement sqlStmt = null;
                //QueryStatement queryStmt = null;
                List errorList = null;
                
                if (syntaxCheckOnly) {
                    sqlStmt = (SQLStatement) resultIt.next();
                } else {
                    SQLParseResult parseResult = (SQLParseResult) resultIt.next();
                    sqlStmt = parseResult.getSQLStatement();
                    errorList = parseResult.getErrorList();
                }
                parseResultStmts.add(sqlStmt);
                
                if (DEBUG) {
                    System.out.println("\nStatement "+i+":"+NL+sqlStmt.getSQL()); //$NON-NLS-1$ //$NON-NLS-2$
    	            getParserManager().printAST(sqlStmt);
                    printErrorList(errorList);

                    StringBuffer testSeparator = new StringBuffer(TEST_SEPARATOR);
                    String testCaseName = getName();
                    testSeparator.replace(testSeparator.length()-testCaseName.length(), testSeparator.length(), testCaseName);
        	        System.out.println(testSeparator+"\n"); //$NON-NLS-1$
    	        } else if (CONTROL) {
    	            System.out.println("\nStatement "+i+":"+NL+sqlStmt.getSQL()); //$NON-NLS-1$ //$NON-NLS-2$
    	        } else {
    	            System.out.println("...parse successful"); //$NON-NLS-1$
    	        }
            }
            
            
	        if (p_matchInput)
	        {
	            matchInputScript(p_script,parseResultStmts);
	        }
	        
	        return parseResultStmts;
	        
        } 
        catch( SQLParserException e) 
        {
        	getParserManager().printParseRuntimeException(e);
        	throw e;
        }
    }

    protected SQLStatement parserVerifySuccessSingle(String p_stmt, boolean p_matchInput) 
    	throws SQLParserException,SQLParserInternalException{
        try {
            
            if (DEBUG) {
	            printColumnIndexNumbers();
	            System.out.println(p_stmt);
            } else if (CONTROL) {
                System.out.println("parsing \"" + p_stmt + "\""); //$NON-NLS-1$ //$NON-NLS-2$
            }
            
//            QueryStatement parsedStmt = SQLParserManager.parse(p_stmt);
            SQLStatement parsedStmt = null;
            List errorList = null;
            
            if (syntaxCheckOnly) {
                parsedStmt = getParserManager().checkSyntax(p_stmt);
            } else {
                SQLParseResult parseResult = getParserManager().parse(p_stmt);
                parsedStmt = parseResult.getSQLStatement();
                errorList = parseResult.getErrorList();
            }

            assertNotNull("Result QueryStatement expected for parse of "+p_stmt,parsedStmt); //$NON-NLS-1$
            
            if (DEBUG) {
                System.out.println("Statement:"+NL+parsedStmt.getSQL()); //$NON-NLS-1$
	            getParserManager().printAST(parsedStmt);
	            printErrorList(errorList);

	            StringBuffer testSeparator = new StringBuffer(TEST_SEPARATOR);
	            String testCaseName = getName();
	            testSeparator.replace(testSeparator.length()-testCaseName.length(), testSeparator.length(), testCaseName);
		        System.out.println(testSeparator+"\n"); //$NON-NLS-1$

            } else if (CONTROL) {
	            System.out.println("Statement:"+NL+parsedStmt.getSQL()); //$NON-NLS-1$
	        } else {
	            System.out.println("...parse successful"); //$NON-NLS-1$
	        }
            
            if (p_matchInput)
            {
                matchInputStatement(p_stmt, parsedStmt);
            }
            
	        return parsedStmt;
        } catch( SQLParserException e) {
        	getParserManager().printParseRuntimeException(e);
        	throw e;
        }
	}

	/**
	 * Matches the input SQL source against the generated output SQL source.
	 * 
     * @param p_script
     * @param parseResultStmts
     */
    protected void matchInputScript(String p_script, List parseResultStmts)
    {
        String stmtTerm = ";"; //$NON-NLS-1$
        if (getParserManager().getSourceFormat() != null)
        {
            stmtTerm = String.valueOf(getParserManager().getSourceFormat()
                            .getStatementTerminator());
        }
        
        // remove trailing statement terminator and line breaks before,
        // to also catch stmt terminators that have a line break after them
        p_script = p_script.replaceAll("\n", " ").trim(); //$NON-NLS-1$ //$NON-NLS-2$
        if (p_script.endsWith(stmtTerm)) {
            p_script = p_script.substring(0, p_script.length()-1);
        }
        
        String[] stmtList = p_script.split(stmtTerm);
        
        assertTrue("number of input statements != number of parsed statements -" + //$NON-NLS-1$
        		" ! discard that warning if there is a Statement terminator in a String or a comment", //$NON-NLS-1$
                        stmtList.length == parseResultStmts.size());
        
        for (int i = 0; i < stmtList.length; i++)
        {
            matchInputStatement(stmtList[i], ((SQLStatement) parseResultStmts
                            .get(i)));
        }

    }
    
    /**
	 * Matches the input SQL source against the generated output SQL source.
     * @param input
     * @param parsedStmt
     */
    protected void matchInputStatement(String input, SQLStatement parsedStmt)
    {
        input = stripWhiteSpaceAndAS(input);
        String output = stripWhiteSpaceAndAS(parsedStmt.getSQL());
        assertTrue("input SQL != generated SQL:\n" //$NON-NLS-1$
                        + " in: " + input + "\n" //$NON-NLS-1$ //$NON-NLS-2$
                        + "out: " + output, //$NON-NLS-1$
                        input.equals(output));
    }
    
    /**
     * Strips of the optional "AS" keywords from the given <code>sql</code>
     * and replaces all new lines and multiple occurences of whitespace.
     * 
     * @param sql
     * @return
     * 		<code>sql.toUpperCase().replaceAll(" AS ", " ").replaceAll("\n",
     * 				" ").replaceAll("\\s+", " ");</code>
     */
    private String stripWhiteSpaceAndAS(String sql)
    {
        String stmtTerm = ";"; //$NON-NLS-1$
        if (getParserManager().getSourceFormat() != null)
        {
            stmtTerm = String.valueOf(getParserManager().getSourceFormat()
                            .getStatementTerminator());
        }
        if (sql != null)
        {
            sql = toSQLFormatUpperCase(sql);
            sql = sql.replaceAll("\n", " ");		// line breaks to space //$NON-NLS-1$ //$NON-NLS-2$
            sql = sql.replaceAll(" AS ", " ");		// AS is optional //$NON-NLS-1$ //$NON-NLS-2$
            sql = sql.replaceAll(",", ", ");		// space after every comma //$NON-NLS-1$ //$NON-NLS-2$
            sql = sql.replaceAll("\\s+,", ",");		// eliminate white space before comma //$NON-NLS-1$ //$NON-NLS-2$
            sql = sql.replaceAll("\\s+", " ");		// multiple space into single space //$NON-NLS-1$ //$NON-NLS-2$
            sql = sql.replaceAll("\\s*\\(\\s*", " (");	// one space before left paren, no more space before or after //$NON-NLS-1$ //$NON-NLS-2$
            sql = sql.replaceAll("\\s*\\)\\s*", ") ");	// one space after right paren, no more space before or after //$NON-NLS-1$ //$NON-NLS-2$
            //sql = sql.replaceAll("\\s*\\(\\+\\*-/\\)\\s*", " \\1 ");
            sql = sql.replaceAll("\\s*=\\s*", " = ");	// one space before and after = //$NON-NLS-1$ //$NON-NLS-2$
            sql = sql.replaceAll("\\s*-\\s*", " - ");	// one space before and after - //$NON-NLS-1$ //$NON-NLS-2$
            sql = sql.replaceAll("\\s*\\+\\s*", " + ");	// one space before and after + //$NON-NLS-1$ //$NON-NLS-2$
            sql = sql.replaceAll("\\s*\\*\\s*", " * ");	// one space before and after * //$NON-NLS-1$ //$NON-NLS-2$
            sql = sql.replaceAll("\\s*/\\s*", " / ");	// one space before and after / //$NON-NLS-1$ //$NON-NLS-2$
            sql = sql.replaceAll(stmtTerm, "");		// eliminate optional trailing stmt terminator //$NON-NLS-1$
        }
        return sql.trim();
    }

    /**
     * @param sql
     * @return
     */
    private String toSQLFormatUpperCase(String sql)
    {
        StringBuffer sqlUC = new StringBuffer();
        char[] sqlChar = sql.toCharArray();
        char delimiterQuote = getSourceFormat().getDelimitedIdentifierQuote();
        boolean inDelimitedIdentifier = false;

        for (int i = 0; i < sqlChar.length; i++)
        {
            char c = sqlChar[i];
            if (c == delimiterQuote)
            {
                inDelimitedIdentifier = !inDelimitedIdentifier;
            }
            if (!inDelimitedIdentifier)
            {
                c = Character.toUpperCase(c);
            }
            sqlUC.append(c);
        }
        
        
        return sqlUC.toString();
    }


    /**
     * 
     */
    private void printColumnIndexNumbers()
    {
        System.out.print(" "); //$NON-NLS-1$
        for (int i = 1; i < 10; i++)
            System.out.print("        "+i+"0"); //$NON-NLS-1$ //$NON-NLS-2$
        System.out.print(" "); //$NON-NLS-1$
        for (int i = 10; i < 20; i++)
            System.out.print("       "+i+"0"); //$NON-NLS-1$ //$NON-NLS-2$
        System.out.print('\n');
        for (int i = 0; i < 20; i++)
            System.out.print("1234567890"); //$NON-NLS-1$
        System.out.print('\n');
    }

    /**
     * @param errorList
     */
    protected void printErrorList(List errorList)
    {
        if (errorList == null || errorList.isEmpty() || !PRINT_ERRORS) {return;}
        
        System.out.println("Errors encountered:"); //$NON-NLS-1$
        for (Iterator errorIt = errorList.iterator(); errorIt.hasNext();)
        {
            SQLParseErrorInfo errorInfo = (SQLParseErrorInfo) errorIt.next();
            System.out.println(errorInfo.getParserErrorMessage()+
                            " at line:column "+ //$NON-NLS-1$
                            errorInfo.getLineNumberStart()+":"+ //$NON-NLS-1$
                            errorInfo.getColumnNumberStart()+
                            " to line:column "+ //$NON-NLS-1$
                            errorInfo.getLineNumberEnd()+":"+ //$NON-NLS-1$
                            errorInfo.getColumnNumberEnd()+
                            " \""+ //$NON-NLS-1$
                            errorInfo.getErrorSourceText()+"\""+ //$NON-NLS-1$
                            ((errorInfo.getExpectedText()!=null)?
                                            ", expected: \""+errorInfo.getExpectedText():"") //$NON-NLS-1$ //$NON-NLS-2$
                            );
        }
    }



    protected void parserVerifyError(String p_stmt) throws SQLParserInternalException{
        try {
            if (DEBUG || CONTROL) {
                System.out.println("\nparsing \"" + p_stmt + "\""); //$NON-NLS-1$ //$NON-NLS-2$
            }
            List result = null;
            if (syntaxCheckOnly) {
                result = getParserManager().checkSyntaxScript(p_stmt);
            } else {
                result = getParserManager().parseScript(p_stmt);
            }
            if (result != null) {
                fail(p_stmt + " contained an intended error and should not be accepted by the parser"); //$NON-NLS-1$
            }
        } catch (SQLParserException e) {
            if (DEBUG) {
                System.out.println("Successfully caught expected " + e.getClass().getName() + ": " + e.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
                //formatParseError(e, p_stmt);
                System.out.println(" caused by "+e.getCause()); //$NON-NLS-1$
                System.out.println("\n\n * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n"); //$NON-NLS-1$
            }
            
        }
    }
    
    protected void parserVerifyErrorSingle(String p_stmt)throws SQLParserInternalException {
        try {
            if (CONTROL) {
                System.out.println("\nparsing :\n" + p_stmt); //$NON-NLS-1$
            }
            SQLStatement parsedStmt = null;
            if (syntaxCheckOnly) { 
                parsedStmt = getParserManager().checkSyntax(p_stmt);
            } else {
                SQLParseResult parseResult = getParserManager().parse(p_stmt);
                parsedStmt = parseResult.getSQLStatement();
            }
            
            if (DEBUG) {
                System.out.println("Statement:"+NL+parsedStmt.getSQL()); //$NON-NLS-1$
	            getParserManager().printAST(parsedStmt);
	            System.out.println("\n\n * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n"); //$NON-NLS-1$
	        } else if (CONTROL) {
	            System.out.println("Statement:"+NL+parsedStmt.getSQL()); //$NON-NLS-1$
	            System.out.println("\n\n * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n"); //$NON-NLS-1$
	        } else {
	            System.out.println("...parse successful"); //$NON-NLS-1$
	        }
            
            if (parsedStmt != null) {
                fail(p_stmt + " contained an intended error and should not be accepted by the parser"); //$NON-NLS-1$
            }
        } catch (SQLParserException e) {
            System.out.println("Successfully caught expected " + e.getClass().getName() + ": " + e.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
            //formatParseError(e, p_stmt);
            System.out.println(" caused by "+e.getCause()); //$NON-NLS-1$
            System.out.println();
        }
    }

    protected void formatParseError(SQLParserException p_e, String p_stmt) {
        String stmt = p_stmt;

        //		StringBuffer message = new StringBuffer(p_e.getMessage());
        System.out.println(p_e.toString());
        System.out.println("\nOn: "+p_stmt); //$NON-NLS-1$
        getParserManager().printParseRuntimeException(p_e);

    }


    
    
    
    
    
    

}
