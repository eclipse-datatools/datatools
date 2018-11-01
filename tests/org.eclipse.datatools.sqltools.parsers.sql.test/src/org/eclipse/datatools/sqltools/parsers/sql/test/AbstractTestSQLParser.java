/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
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

    /** Standard statement separator -- semi-colon and newline. */
    protected static final String SEP = ";\n";

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
     * Parses the statements in the given script and returns a list of SQL statement objects
     * if the parse was successful.  Optionally compare the generated statement SQL with the 
     * original script statements.
     * 
     * @param sourceScript the SQL script to parse and verify
     * @param compareGeneratedSQLToSource when true, compare the generated SQL with the source script
     * @return a list of <code>SQLStatement</code> objects or an empty list if the script did not parse
     */
    protected List parserVerifySuccess(String sourceScript, boolean compareGeneratedSQLToSource) 
        throws SQLParserException,SQLParserInternalException {
        List stmtObjList = parserVerifySuccess(sourceScript);
        
        if (compareGeneratedSQLToSource == true) {
            compareStatementListToTemplateScript(stmtObjList, sourceScript, true, true);
        }
        
        return stmtObjList;
    }
    
    
    /**
     * Parses the statements in the given source script and returns a list of SQL statement objects
     * if the parse was successful.  Optionally compare the generated statement SQL with the 
     * given template script.
     * 
     * @param sourceScript the SQL script to parse and verify 
     * @param a template script to compare with the generated SQL or null if comparison is not wanted
     * @return a list of <code>SQLStatement</code> objects or an empty list if the script did not parse
     */
    protected List parserVerifySuccess(String sourceScript, String templateScript) 
        throws SQLParserException,SQLParserInternalException {
        List stmtObjList = parserVerifySuccess(sourceScript);
        
        if (templateScript != null && templateScript.length() > 0) {
            compareStatementListToTemplateScript(stmtObjList, templateScript, true, false);
        }
        
        return stmtObjList;
    }
    
    /**
     * Parses the statements in the given script and returns a list of SQL statement objects
     * if the parse was successful.
     * 
     * @param p_script the SQL script to parse
     * @return a list of <code>SQLStatement</code> objects or an empty list if the script did not parse
     */
    protected List parserVerifySuccess(String p_script) 
        throws SQLParserException, SQLParserInternalException {
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
    protected void matchInputScript(String p_script, List parseResultStmts) {
        compareStatementListToTemplateScript(parseResultStmts, p_script, true, true);
    }
    
    /**
     * Compares the SQL generated from the given list of SQL statement objects to the 
     * SQL of the given script.  Optionally ignore extra whitespace and AS keywords when 
     * doing the comparison.
     * 
     * @param stmtObjList the list of SQL statement objects to use for the comparison
     * @param templateScript the template SQL script to use for the comparison
     * @param ignoreExtraWhiteSpace when true, ignore extra whitespace in the SQL statement strings when comparing
     * @param ignoreAS when true, ignore AS keywords in the SQL statement strings when comparing
     */
    protected void compareStatementListToTemplateScript(List stmtObjList, String templateScript, boolean ignoreExtraWhiteSpace, boolean ignoreAS) {
        /* Get the current statement terminator. */
        String stmtTerm = ";";
        SQLQuerySourceFormat srcFormat = getParserManager().getSourceFormat();
        if (srcFormat != null) {
            stmtTerm = String.valueOf(srcFormat.getStatementTerminator());
        }
       
        /* Prepare the template script by removing newlines with spaces and removing the final
         * statement terminator. */ 
        templateScript = templateScript.replaceAll("\n", " ").trim();
        if (templateScript.endsWith(stmtTerm)) {
            templateScript = templateScript.substring(0, templateScript.length()-1);
        }
        
        /* Create an array of statement strings from the script. Check that the number of 
         * template statements matches the number of statement objects. */
        String[] templateStmtArray = templateScript.split(stmtTerm);       
        assertTrue("the number of parsed statements != the number of template statements",
                stmtObjList.size() == templateStmtArray.length);
        
        /* Compare the generated SQL for each statement object with the corresponding template SQL. */
        for (int i = 0; i < templateStmtArray.length; i++) {
            SQLStatement stmtObj = (SQLStatement) stmtObjList.get(i);
            String genSQL = stmtObj.getSQL();
            String templateSQL = templateStmtArray[i];
            compareGeneratedSQLToTemplateSQL(genSQL, templateSQL, ignoreExtraWhiteSpace, ignoreAS);
        }
    }
    
    /**
     * Compares a given template SQL string against the generated SQL from the given SQL 
     * statement object and throws an assertion exception if they do not match.  Extra 
     * whitespace and AS keywords are ignored in the comparison.
     * 
     * @param templateSQL the template SQL string to compare against
     * @param stmtObj the SQL statement object to check
     */
    protected void matchInputStatement(String templateSQL, SQLStatement stmtObj) {
        String genSQL = stmtObj.getSQL();
        compareGeneratedSQLToTemplateSQL(genSQL, templateSQL, true, true);
    }
    
    /**
     * Compares the given generated SQL source against the given template and throws
     * an assertion exception if they do not match.  Optionally ignore extra whitespace  
     * and AS keywords when doing the comparison.
     * 
     * @param genSQL the generated SQL string
     * @param templateSQL the template SQL string to compare against
     * @param ignoreExtraWhiteSpace when true, ignore extra whitespace in the SQL statement strings when comparing
     * @param ignoreAS when true, ignore AS keywords in the SQL statement strings when comparing
     */
    protected void compareGeneratedSQLToTemplateSQL(String genSQL, String templateSQL, boolean ignoreExtraWhiteSpace, boolean ignoreAS) {
        String tmpGenSQL = genSQL;
        String tmpTemplateSQL = templateSQL;

        if (ignoreExtraWhiteSpace == true) {
            tmpGenSQL = stripWhiteSpace(genSQL);
            tmpTemplateSQL = stripWhiteSpace(templateSQL);
        }
        if (ignoreAS == true) {
            tmpGenSQL = removeAS(tmpGenSQL);
            tmpTemplateSQL = removeAS(tmpTemplateSQL);
        }

        assertTrue("generated SQL does not match template SQL:\n" //$NON-NLS-1$
                        + "generated: " + tmpGenSQL + "\n" //$NON-NLS-1$ //$NON-NLS-2$
                        + "template:  " + tmpTemplateSQL, //$NON-NLS-1$
                        tmpGenSQL.equals(tmpTemplateSQL));
    }
    
    /**
     * Removes the "AS" keyword from the input SQL string and returns it.  The AS keyword is usually
     * optional, so remove it before comparing two SQL strings.
     * 
     * @param sql the SQL statement to process
     * @return the SQL statement with AS keywords removed
     */
    private String removeAS(String sql) {
        sql = sql.replaceAll(" AS ", " ");      // AS is optional //$NON-NLS-1$ //$NON-NLS-2$
        
        return sql;
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
    private String stripWhiteSpace(String sql)
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
