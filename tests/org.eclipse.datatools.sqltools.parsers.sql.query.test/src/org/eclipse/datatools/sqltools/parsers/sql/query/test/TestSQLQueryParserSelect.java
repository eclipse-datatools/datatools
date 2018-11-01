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
package org.eclipse.datatools.sqltools.parsers.sql.query.test;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist;
import org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn;
import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElement;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * @author ckadner
 *
 * A Test parser implementation
 */
public final class TestSQLQueryParserSelect extends AbstractTestSQLQueryParser {


    /**
     * Constructor for TestDb2SqlParser.
     * 
     * @param name
     */
    public TestSQLQueryParserSelect(String name) {
        super(name);
        
        DEBUG = true;
        CONTROL = true;
        PRINT_ERRORS = true;
        syntaxCheckOnly = false;
    }

    
    
    
    
    
/*
    public void testSqlDmlParser() {
        System.out.println("test");
        
        
        // the SQL statement to be parsed into a SQLQueryObject model instance
        String sqlStmt = "select * from employee where empno = :h1;";
        
        Database database = (Database) new Object();
        String currentSchemaName = "YOUR_DEFAULT_SCHEMA_FOR_UNQUALIFIED_TABLES";
        
        // create the post parse processors
        PostParseProcessor tableRefResolver = new TableReferenceResolver(database,currentSchemaName);
        PostParseProcessor dataTypeResolver = new DataTypeResolver();

        // ordering is important for post parse processing! first we need to fill
        // in the database information for table references and column types
        List postParseProcessors = new ArrayList();
        postParseProcessors.add(0, tableRefResolver);
        postParseProcessors.add(1, dataTypeResolver);
        
        
        // get the SQL source format options and set at least the current schema
        // that is omited but implicit for any unqualified table references
        // important for later resolving of table references!
        SQLQuerySourceFormat sourceFormat = SQLQuerySourceFormat.copyDefaultFormat();
        sourceFormat.setOmitSchema(currentSchemaName);
        
        // parse the SQL statement
        SQLQueryParseResult parseResult = SQLQueryParserManager.parse(sqlStmt, sourceFormat, postParseProcessors);

        // get the parsed SQLQueryObject model instance
        QueryStatement queryStatement = parseResult.getQueryStatement();
        
        // get the List of all host variables in the SQL statement 
        List hostVariables = StatementHelper.getAllVariablesInQueryStatement(queryStatement);
       
    }
*/
    
    
    
    public void testSqlDmlParser004_preserving_comments()  throws Exception {
        System.out.println("test 004_preserving_comments"); //$NON-NLS-1$
        
        String stmt =   "-- first line comnt" + NL +    //$NON-NLS-1$
						"select col1 as c1 -- com1" + NL + //$NON-NLS-1$
						"  from tab1 as t1 -- better name it t1" + NL + //$NON-NLS-1$
						" where col1 = 5" + NL + //$NON-NLS-1$
						"-- and col2 = 5" + NL + //$NON-NLS-1$
						"   and col2 = 10 -- has to be 10" + NL + //$NON-NLS-1$
						";" + NL + //$NON-NLS-1$
						NL +
						
						"select col1 as c1 -- com1" + NL + //$NON-NLS-1$
						"-- comment anfang 2" + NL + //$NON-NLS-1$
						"  from tab1 t1;" + NL + //$NON-NLS-1$
						NL +
						
						"select col1 as c1 -- com_ln1" + NL + //$NON-NLS-1$
						"                  -- com_ln2" + NL + //$NON-NLS-1$
						" from tab1 as t1 -- com_ln3" + NL + //$NON-NLS-1$
						" where col1 = 5" + NL + //$NON-NLS-1$
						"-- ln begin" + NL + //$NON-NLS-1$
						"  and col2 = 10" + NL + //$NON-NLS-1$
						"-- last line comnt" + NL + //$NON-NLS-1$
						";" + NL + //$NON-NLS-1$
						NL +
						
						"select" + NL + //$NON-NLS-1$
						"-- col1 as c" + NL + //$NON-NLS-1$
						" col1 as c1" + //$NON-NLS-1$
						" from tab1;"; //$NON-NLS-1$
        
        parserVerifySuccess(stmt, matchInput);
        
        
        stmt =  "select col1 as c1 -- com1" + NL + //$NON-NLS-1$
				"  from tab1 as -- tbl1" + NL + //$NON-NLS-1$
				"              t1 -- better name it t1" + NL + //$NON-NLS-1$
				" where col1 = 5;"; //$NON-NLS-1$
        // we can't match the input, as the comment "-- tbl1" is included in
        // table reference, but comment position only allows before or after
        parserVerifySuccess(stmt, this.getSourceFormat().isPreserveSourceFormat());

		stmt =  "select col1 as c1 -- com1" + NL + //$NON-NLS-1$
				"-- comment anfang 2" + NL + //$NON-NLS-1$
				"  from tab1 t1; -- comment after stmt on last line"; //$NON-NLS-1$
        // we can't match the input, as the last comment is outside the span
        // of the statement, but will be attached anyway to the last line AST
		// where the original source's boundary stays the stmt terminator
		parserVerifySuccess(stmt, this.getSourceFormat().isPreserveSourceFormat());

        
        
        // test the comments for statement only option
        
        stmt =  "select col1 as c1 -- com col1" + NL + //$NON-NLS-1$
                "  from tab1 t1; -- comment after tab"; //$NON-NLS-1$
        this.getSourceFormat().setPreserveComments(true);
        QuerySelectStatement result = (QuerySelectStatement)
            parserVerifySuccessSingle(stmt, this.getSourceFormat().isPreserveSourceFormat());
        ResultColumn rCol = getResultColumn(result, 0);
        QuerySelect select = (QuerySelect) result.getQueryExpr().getQuery();
        TableExpression tabl = (TableExpression) select.getFromClause().get(0);
        
        this.getSourceFormat().setGenerateCommentsForStatementOnly(true);
        String srcQry = result.getSQL();
        String srcRCol = rCol.getSQL();
        String srcTabl = tabl.getSQL();
        
        assertTrue("comments in Query should be preserved: "+srcQry,srcQry.indexOf("-- com col1")>0 && srcQry.indexOf("-- comment")>0);
        assertTrue("comments in ResultColumn only should be supressed: "+srcRCol,srcRCol.indexOf("-- com col1")==-1);
        assertTrue("comments in table only should be supressed: "+srcTabl,srcTabl.indexOf("-- comment")==-1);
        
        this.getSourceFormat().setGenerateCommentsForStatementOnly(false);
        String srcQryCmt = result.getSQL();
        String srcRColCmt = rCol.getSQL();
        String srcTablCmt = tabl.getSQL();
        assertTrue("comments Query should be preserved: "+srcQryCmt,srcQryCmt.indexOf("-- com col1")>0 && srcQry.indexOf("-- comment")>0);
        assertTrue("comments in ResultColumn only should be generated: "+srcRColCmt,srcRColCmt.indexOf("-- com col1")>0);
        assertTrue("comments in table only should be generated: "+srcTablCmt,srcTablCmt.indexOf("-- comment")>0);
        
        this.getSourceFormat().setPreserveComments(false);
        String srcQryNoCmt = result.getSQL();
        String srcRColNoCmt = rCol.getSQL();
        String srcTablNoCmt = tabl.getSQL();
        assertTrue("comments Query should be supressed: "+srcQryNoCmt,srcQryNoCmt.indexOf("-- com col1")==-1 && srcQryNoCmt.indexOf("-- comment")==-1);
        assertTrue("comments in ResultColumn only should be supressed: "+srcRColNoCmt,srcRColNoCmt.indexOf("-- com col1")==-1);
        assertTrue("comments in table only should be supressed: "+srcTablNoCmt,srcTablNoCmt.indexOf("-- comment")==-1);
        
        
        // restore defaults
        this.getSourceFormat().setPreserveComments(true);
        this.getSourceFormat().setGenerateCommentsForStatementOnly(true);
    }
    
  
    
    public void testSqlDmlParser004()  throws Exception {
        System.out.println("test 004"); //$NON-NLS-1$
        parserVerifySuccess(
            "select * from table1;" + NL +  //$NON-NLS-1$
            "select col1 from table1 as tbl;" + NL +  //$NON-NLS-1$
            "", matchInput); //$NON-NLS-1$
    }

    public void testSqlDmlParser004_concat()  throws Exception {
        System.out.println("test 004_concat"); //$NON-NLS-1$
        parserVerifySuccess(
			"select E.FIRSTNME || ' ' concat E.LASTNAME AS \"Employee Name\" from tab1 E;"+NL+ //$NON-NLS-1$
			"select 'comparison operator OR ||' from tab1 as \"OR || operator\" ;"+NL+ //$NON-NLS-1$
			"select * from tab1; --com || ment \n", false); //$NON-NLS-1$
    }
        
    public void testSqlDmlParser004_softkeywords()  throws Exception {
        System.out.println("test 004_softkeywords"); //$NON-NLS-1$
        parserVerifySuccess(
            "select from from from from;" + NL +  //$NON-NLS-1$
            "select as as from from as;" + NL +  //$NON-NLS-1$
            "", false); //$NON-NLS-1$
    }

    public void testSqlDmlParser004_stmtTerminator()  throws Exception {
        System.out.println("test 004_stmtTerminator"); //$NON-NLS-1$
        boolean matchInputBefore = matchInput;
        matchInput = false;
        parserVerifySuccess(
            "select 'stmtTerm"+getSourceFormat().getStatementTerminator()+"' from tab1;" + NL +  //$NON-NLS-1$ //$NON-NLS-2$
            "select col1 -- here no stmtTerm"+getSourceFormat().getStatementTerminator() + NL +  //$NON-NLS-1$
            " from tab1;", matchInput); //$NON-NLS-1$
        matchInput = matchInputBefore;
    }

    public void testSqlDmlParser004_delimitedIdentifier_variable()  throws Exception {
        System.out.println("test 004_delimitedIdentifier_variable"); //$NON-NLS-1$
        QueryStatement stmt = null;
        
//        try {
//            stmt = parserVerifySuccessSingleQuery("select $ColA$ from tab1;", false); //$NON-NLS-1$
//            String sqlSource = stmt.getSQL();
//            assertTrue( sqlSource.indexOf("$ColA$") < 0); //$NON-NLS-1$
//            assertTrue( sqlSource.indexOf("$COLA$") > 0); //$NON-NLS-1$
//        }
//        catch (Exception e) {
//            // do nothing, semi expected exception, behavior undefined so far
//        }
//            
//        System.out.println("\n * * * set delimited identifier quote to: '$' * * * \n"); //$NON-NLS-1$
//        getSourceFormat().setDelimitedIdentifierQuote('$');
//        updateParserManagerCharacterMapping();
//        
//        stmt = parserVerifySuccessSingleQuery("select $ColA$ from tab1;", matchInput); //$NON-NLS-1$
//        String sqlSource = stmt.getSQL();
//        assertTrue( sqlSource.indexOf("$ColA$") > 0); //$NON-NLS-1$
//        assertTrue( sqlSource.indexOf("COLA") < 0); //$NON-NLS-1$
//
//        try {
//            
//            stmt = parserVerifySuccessSingleQuery("select \"ColA\" from delim_tab1;", false); //$NON-NLS-1$
//            sqlSource = stmt.getSQL();
//            assertTrue("delimited identifier quote was not '\"'", sqlSource.indexOf("\"ColA\"") < 0); //$NON-NLS-1$ //$NON-NLS-2$
//            assertTrue("delimited identifier quote was not '\"'", sqlSource.indexOf("COLA") > 0); //$NON-NLS-1$ //$NON-NLS-2$
//        }
//        catch (Exception e) {
//            // kind of expected to error, but backtracking may allow it
//            System.out.println("\n Parser exception expected as delimited identifier quote was not '\"' "); //$NON-NLS-1$
//        }
//
//        
//        System.out.println("\n * * * set delimited identifier quote to: '\"' * * * \n"); //$NON-NLS-1$
//        getSourceFormat().setDelimitedIdentifierQuote('"');
//        updateParserManagerCharacterMapping();
    }
    
   public void testSqlDmlParser004_delimitedIdentifiers()  throws Exception {
        System.out.println("test 004_delimitedIdentifiers"); //$NON-NLS-1$
//        parserVerifySuccess(
//                "select col1, \"col2\", \"CO L\", \"CO.L\" from tab;", matchInput); //$NON-NLS-1$
//        parserVerifySuccess(
//                "select col1, \"1COL\", \"CO\"\"L\", \"COL\"\"1\"\"\" from tab;", matchInput); //$NON-NLS-1$
//        parserVerifySuccess(
//                "select col1, \"COL1\", _COL, $COL from tab;", false); //$NON-NLS-1$
    }
   
    public void testSqlDmlParser004_orderBy()  throws Exception {
        System.out.println("test 004_orderBy"); //$NON-NLS-1$
        parserVerifySuccess(
            "select col1 from table0 order by col2;" + NL +  //$NON-NLS-1$
            "select col1 from table1 order by col1;" + NL +  //$NON-NLS-1$
            "select col1 as col2 from table2 order by col2;" + NL +  //$NON-NLS-1$
            //"select col1 as col2 from table2;" + NL + 
            "select col1 as col2 from table3 order by col1;" + NL +  //$NON-NLS-1$
            "select col4 from table6 order by 1;" + NL +  //$NON-NLS-1$
            "select col4 from table7 order by 1 desc;" + NL +  //$NON-NLS-1$
            "select col4 from table8 order by 1+2;" + NL +  //$NON-NLS-1$
            "select col4, col3 from table9 order by col4+col3, col3;" + NL +  //$NON-NLS-1$
            "select \"ColA\" from table10 order by colA;" + NL +  //$NON-NLS-1$
            "select col1 as \"ColA\" from table11 order by colA;" + NL +  //$NON-NLS-1$
            "select \"ColA\" from table12 order by \"ColA\";" + NL +  //$NON-NLS-1$
            "select col1 as \"ColA\" from table13 order by \"ColA\";" + NL +  //$NON-NLS-1$
            "select colA from table14 order by \"ColA\";" + NL +  //$NON-NLS-1$
            "select col1 as colA from table15 order by \"ColA\";" + NL +  //$NON-NLS-1$
            "", false); //$NON-NLS-1$

        // here the input != output
        parserVerifySuccess(
            "select col3 from table4 order by col2 desc, col1 asc;" + NL +  //$NON-NLS-1$
            "select col3 from table5 order by col2 desc, col3 asc;" + NL +  //$NON-NLS-1$
            "", false); //$NON-NLS-1$

        // Test ORDER BY at the fullselect level.
        parserVerifySuccess(
            "(select c1 from t1 order by c1 asc);" + NL +
            "select * from (select * from t1 order by c1) as t2;" + NL +
            "select * from (select * from t1 order by c1) as t2 order by c2;" + NL +
            "select c1 from t1 order by c1 union select c2 from t2 order by c2;" + NL +
            "select c1 from t1 order by c1 desc intersect select c2 from t2 order by c2 union select c3 from t3 order by c3 order by c1;" + NL +
            "values (1, 2, 3) order by 1;" + NL +
            "select * from (values (1, 2, 3) order by 1) as t1;" + NL +
            "select * from (values (1, 2, 3) order by 1 asc, 2 desc) as t1;" + NL +
            "select * from (select * from t1 order by c1 asc, c2 desc) as t2 order by c3;" + NL +
            "select * from (select * from t1 order by c1 + 10 asc) as t2 order by c3 - c4 / 5;" + NL +
            "(select deptno from department where deptno in (select workdept from employee order by workdept) order by deptno) intersect (select workdept from employee order by workdept) union (values ('E22') order by 1) order by 1" + NL +
            "", false);
        
        // Test mix of column expr and ordinal sor specs
        StatementCache stmtCache = StatementCache.getInstance();
        
        String sourceScript = stmtCache.getScript("SELECT", "ORDER BY");
        String templateScript = stmtCache.getTemplateScript("SELECT", "ORDER BY");
        parserVerifySuccess(sourceScript, templateScript);
        
// NULLS FIRST/LAST not supported by DB2
//        parserVerifySuccess(
//                "select col3 from table4 order by col2 desc, col1 asc NULLS FIRST;" + NL +  //$NON-NLS-1$
//                "select col3 from table5 order by col2 desc, col3 asc NULLS LAST;" + NL +  //$NON-NLS-1$
//                "", matchInput); //$NON-NLS-1$

        
    }

    // TODO: test scalar subselect including WITH-clause
    public void testSqlDmlParser004_scalarSubselect()  throws Exception {
        System.out.println("test 004_scalarSubselect"); //$NON-NLS-1$
        QueryStatement queryStmt =  parserVerifySuccessSingleQuery(
            "select col1 + (select col1 from table2 group by col1) from table1;" + NL +  //$NON-NLS-1$
            "", matchInput); //$NON-NLS-1$
        
        
        QuerySelectStatement querySelect = (QuerySelectStatement) queryStmt;
        QueryExpressionRoot queryExprRoot = querySelect.getQueryExpr();
        QuerySelect select = (QuerySelect) queryExprRoot.getQuery();
        ResultColumn resultCol = (ResultColumn) select.getSelectClause().get(0);
        ValueExpressionCombined resultColExpr = (ValueExpressionCombined)resultCol.getValueExpr();
        
        ValueExpressionColumn col1InSelect = (ValueExpressionColumn) resultColExpr.getLeftValueExpr();
        TableExpression tableOfCol1InSelect = col1InSelect.getTableExpr();
        
        
        TableReference table1 = (TableReference) select.getFromClause().get(0);
        
        ValueExpressionScalarSelect subScalarSelect = (ValueExpressionScalarSelect)resultColExpr.getRightValueExpr();
        QueryExpressionRoot subqueryExprRoot = subScalarSelect.getQueryExpr();
        QuerySelect subselect = (QuerySelect) subqueryExprRoot.getQuery();
        ResultColumn subResultCol = (ResultColumn) subselect.getSelectClause().get(0);

        ValueExpressionColumn col1InSubselect = (ValueExpressionColumn) subResultCol.getValueExpr();
        TableExpression tableOfCol1InSubselect = col1InSubselect.getTableExpr();
        
        TableReference table2 = (TableReference) subselect.getFromClause().get(0);
        
        assertTrue("col1 in outer select should belong to table1", //$NON-NLS-1$
                        ((tableOfCol1InSelect != tableOfCol1InSubselect)
                        && (tableOfCol1InSelect == table1)
                        && (tableOfCol1InSubselect == table2)));

    }

    // TODO: test all types of TableExpression (tableFunction,tableWithSpec,TableInDB)
    public void testSqlDmlParser004_tableExpr_subselect()  throws Exception {
        System.out.println("test 004_tableExpr_subselect"); //$NON-NLS-1$
        parserVerifySuccess(
            "select col1 from (select col1 from table0) query0;" + NL +  //$NON-NLS-1$
            "select col1 from (select col1 from table1) as query1;" + NL +  //$NON-NLS-1$
            "", matchInput); //$NON-NLS-1$
        parserVerifySuccess(
            "select col1 from TABLE (select col1 from table2) query2;" + NL +  //$NON-NLS-1$
            "", false); //$NON-NLS-1$
    }

    public void testSqlDmlParser004_tableCorrelationNameList() throws Exception {
        System.out.println("test 004_tableCorrelationNameList"); //$NON-NLS-1$
        
        parserVerifySuccess(
                "select col1, col3 from t1_long t1(col1, col2, col3, col4);" + NL +  //$NON-NLS-1$
                "select col1, col3 from t1_long as t1(col1, col2, col3, col4);" + NL +  //$NON-NLS-1$
                "", matchInput); //$NON-NLS-1$
    }
    
    // TODO: test all types of TableExpression (tableFunction,tableWithSpec,TableInDB)
    public void testSqlDmlParser004_tableNested()  throws Exception {
        System.out.println("test 004_tableNested"); //$NON-NLS-1$
        parserVerifySuccess(
            "select col1 from ((select col1 from table0) query0);" + NL +  //$NON-NLS-1$
            "select col1 from ((select col1 from table1) as query1);" + NL +  //$NON-NLS-1$
            "select * from (table1), table2;" + NL +  //$NON-NLS-1$
            "", matchInput); //$NON-NLS-1$
        parserVerifySuccess(
            "select col1 from (TABLE (select col1 from table2) query2);" + NL +  //$NON-NLS-1$
            "", false); //$NON-NLS-1$
    }

    // TODO: test all types of TableExpression (tableFunction,tableWithSpec,TableInDB)
    public void testSqlDmlParser004_tableJoined()  throws Exception {
        System.out.println("test 004_tableJoined"); //$NON-NLS-1$
        parserVerifySuccess(
            "select j,   col1, col2 from tbl1 JOIN tbl2 ON tbl1.col1 = tbl2.col2;" + NL +  //$NON-NLS-1$
            "select ij,  col1, col2 from tbl1 INNER JOIN tbl2 ON tbl1.col1 = tbl2.col2;" + NL +  //$NON-NLS-1$
            "select lj,  col1, col2 from tbl1 LEFT JOIN tbl2 ON tbl1.col1 = tbl2.col2;" + NL +  //$NON-NLS-1$
            "select loj, col1, col2 from tbl1 LEFT OUTER JOIN tbl2 ON tbl1.col1 = tbl2.col2;" + NL +  //$NON-NLS-1$
            "select rj,  col1, col2 from tbl1 RIGHT JOIN tbl2 ON tbl1.col1 = tbl2.col2;" + NL +  //$NON-NLS-1$
            "select roj, col1, col2 from tbl1 RIGHT OUTER JOIN tbl2 ON tbl1.col1 = tbl2.col2;" + NL +  //$NON-NLS-1$
            "select fj,  col1, col2 from tbl1 FULL JOIN tbl2 ON tbl1.col1 = tbl2.col2;" + NL +  //$NON-NLS-1$
            "select foj, col1, col2 from tbl1 FULL OUTER JOIN tbl2 ON tbl1.col1 = tbl2.col2;" + NL +  //$NON-NLS-1$
            "select tbl1.col1, tbl2.col2 from tbl1 JOIN tbl2 ON tbl1.col1 = tbl2.col1;" + NL + //$NON-NLS-1$
            "", false); // we don't know how columnRefs will be qualified //$NON-NLS-1$
    }

    public void testSqlDmlParser004_join_conditions()  throws Exception {
        System.out.println("test 004_join_conditions"); //$NON-NLS-1$
        parserVerifySuccess(
            "SELECT * FROM A    JOIN B    ON A.COL1 = B.COL1;" + NL +  //$NON-NLS-1$
            "SELECT * FROM S1.A JOIN S1.B ON S1.A.COL1 = S1.B.COL1;" + NL +  //$NON-NLS-1$
            "", matchInput); //$NON-NLS-1$
        parserVerifySuccess(
            "SELECT * FROM S1.A JOIN S1.B ON A.COL1 = B.COL1;" + NL +  //$NON-NLS-1$
            "", false); //$NON-NLS-1$
    }

    public void testSqlDmlParser004_existPredicate()  throws Exception {
        System.out.println("test 004_existPredicate"); //$NON-NLS-1$
        parserVerifySuccess(
            "select * from table1 where exists (select * from table2);" + NL +  //$NON-NLS-1$
            "select * from table1 where not exists (select * from table2);" + NL +  //$NON-NLS-1$
            "", matchInput); //$NON-NLS-1$
    }
    
    public void testSqlDmlParser004_quantifiedPredicate()  throws Exception {
        System.out.println("test 004_quantifiedPredicate"); //$NON-NLS-1$
        parserVerifySuccess(
            "select * from tableA where colA >  any (select colB from tableB);" + NL +  //$NON-NLS-1$
            "select * from tableA where colA >= all (select colB from tableB);" + NL +  //$NON-NLS-1$
            "select * from tableA where colA = some (select colB from tableB);" + NL +  //$NON-NLS-1$
            "", matchInput); //$NON-NLS-1$
        parserVerifySuccess(
            "select * from tableA where (colA,colB,1+3) = any (select colB from tableB);" + NL +  //$NON-NLS-1$
            "select * from tableA where (colA,colB,1+3) = some (select colB from tableB);" + NL +  //$NON-NLS-1$
            "", false); //$NON-NLS-1$
        // parsed as PredicateQuantifiedValueSelect as one-element PredicateQuantifiedRowSelect
        parserVerifySuccess(
            "select * from tableA where (colB+3) = any (select colA from tableB);" + NL +  //$NON-NLS-1$
            "select * from tableA where colB+3 = any (select colA from tableB);" + NL +  //$NON-NLS-1$
            "", matchInput); //$NON-NLS-1$
        
        // ALL not allowed for quantified row select
        parserVerifyErrorSingle(
            "select * from tableA where (colA,colB,1+3) = all (select colA from tableB);" + NL +  //$NON-NLS-1$
            ""); //$NON-NLS-1$
        // >,<,>=,<=,<> not allowed for quantified row select
        parserVerifyError(
            "select * from table0 where (colA,colB,1+3) > any (select colA from tableB);" + NL +  //$NON-NLS-1$
            "select * from table1 where (colA,colB,1+3) < any (select colA from tableB);" + NL +  //$NON-NLS-1$
            "select * from table2 where (colA,colB,1+3) >= any (select colA from tableB);" + NL +  //$NON-NLS-1$
            "select * from table3 where (colA,colB,1+3) <= some (select colA from tableB);" + NL +  //$NON-NLS-1$
            "select * from table4 where (colA,colB,1+3) <> some (select colA from tableB);" + NL +  //$NON-NLS-1$
            ""); //$NON-NLS-1$
    }
    
    
    public void testSqlDmlParser004_inPredicate_valueList()  throws Exception {
        System.out.println("test 004_inPredicate_valueList"); //$NON-NLS-1$
        parserVerifySuccess(
            "select * from table0 where col0 in (30,40,50);" + NL +  //$NON-NLS-1$
            "select * from table1 where col1 not in (30,40,50);" + NL +  //$NON-NLS-1$
            "select * from table2 where not col2 in (30,40,50);", matchInput); //$NON-NLS-1$
    }

    public void testSqlDmlParser004_inPredicate_valueSelect()  throws Exception {
        System.out.println("test 004_inPredicate_valueSelect"); //$NON-NLS-1$
        parserVerifySuccess(
            "select * from table0 where col0 in (select col1 from table0);" + NL +  //$NON-NLS-1$
            "select * from table1 where col1 not in (select col0 from table1);" + NL +  //$NON-NLS-1$
            "select * from table2 where not col2 in (select col0 from table2);", matchInput); //$NON-NLS-1$
    }

    public void testSqlDmlParser004_inPredicate_valueRowSelect()  throws Exception {
        System.out.println("test 004_inPredicate_valueRowSelect"); //$NON-NLS-1$
        parserVerifySuccess(
            "select * from table0 where (col0,col1,col2) in (select col0,col1,col2 from tableA);" + NL +  //$NON-NLS-1$
            "select * from table1 where (col0,col1,col2) not in (select col0,col1,col2 from tableB);" + NL +  //$NON-NLS-1$
            "select * from table2 where not (col0,col1,col2) in (select col0,col1,col2 from tableC);", false); //$NON-NLS-1$
    }

    public void testSqlDmlParser004_inPredicate_valueRowSelect_parser_conflict()  throws Exception {
        System.out.println("test 004_inPredicate_valueRowSelect_parser_conflict"); //$NON-NLS-1$
        parserVerifySuccess(
            "--RowSelect or ValueSelect?" + NL + //$NON-NLS-1$
            "select * from table0 where (col0) in (select col0,col1,col2 from table0);", false);  //$NON-NLS-1$
    }

    public void testSqlDmlParser004_rowBasicPredicate() throws Exception {
        System.out.println("test 004_rowBasicPredicate");
        parserVerifySuccess(
            "select c1 from table2 where (col0,col1,col2) = (1, 'abc', col4);", matchInput); //$NON-NLS-1$
    }

    public void testSqlDmlParser004_caseExpression()  throws Exception {
        System.out.println("test 004_caseExpression"); //$NON-NLS-1$
        parserVerifySuccess(
            "SELECT EMPNO, LASTNAME," + NL + //$NON-NLS-1$
            "    CASE WORKDEPT" + NL + //$NON-NLS-1$
            "      WHEN 'A' THEN 'Administration'" + NL + //$NON-NLS-1$
            "      WHEN 'B' THEN 'Human Resources'" + NL + //$NON-NLS-1$
            "      WHEN 'C' THEN 'Accounting'" + NL + //$NON-NLS-1$
            "      WHEN 'D' THEN 'Design'" + NL + //$NON-NLS-1$
            "      WHEN 'E' THEN 'Operations'" + NL + //$NON-NLS-1$
            "      ELSE 'New Hire'" + NL + //$NON-NLS-1$
            "    END" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE;" + NL + //$NON-NLS-1$
            NL +
            "SELECT EMPNO, FIRSTNME, MIDINIT, LASTNAME," + NL + //$NON-NLS-1$
            "    CASE " + NL + //$NON-NLS-1$
            "      WHEN EDLEVEL < 15 THEN 'SECONDARY'" + NL + //$NON-NLS-1$
            "      WHEN EDLEVEL < 19 THEN 'COLLEGE'" + NL + //$NON-NLS-1$
            "      ELSE 'POST GRADUATE'" + NL + //$NON-NLS-1$
            "    END" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE;" + NL + //$NON-NLS-1$
            NL +
            "SELECT EMPNO, WORKDEPT, SALARY+COMMISION" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE" + NL + //$NON-NLS-1$
            "  WHERE (CASE WHEN SALARY=0 THEN NULL " + NL + //$NON-NLS-1$
            "              ELSE COMMISION/SALARY END) > 0.25;" + NL + //$NON-NLS-1$
            NL +
            "SELECT LASTNAME, " + NL + //$NON-NLS-1$
            "    CASE " + NL + //$NON-NLS-1$
            "      WHEN LASTNAME = 'Haas' THEN 'President'" + NL + //$NON-NLS-1$
            "      ELSE 'Unknown'" + NL + //$NON-NLS-1$
            "    END" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE;" + NL + //$NON-NLS-1$
            NL +
            "SELECT LASTNAME, " + NL + //$NON-NLS-1$
            "    CASE LASTNAME " + NL + //$NON-NLS-1$
            "      WHEN 'Haas' THEN 'President'" + NL + //$NON-NLS-1$
            "      ELSE 'Unknown'" + NL + //$NON-NLS-1$
            "    END" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE;" + NL + //$NON-NLS-1$
            "", matchInput); //$NON-NLS-1$
    }
    
    public void testSqlDmlParser004_variable()  throws Exception {
        System.out.println("test 004_variable"); //$NON-NLS-1$
        parserVerifySuccess(
            "SELECT EMPNO, LASTNAME, :hostVar" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE WHERE :hostVar = 12;" + NL + //$NON-NLS-1$
            "SELECT EMPNO, LASTNAME" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE WHERE DEPT = ? AND MGR = ?;", matchInput); //$NON-NLS-1$
        
        
    }
    
    public void testSqlDmlParser004_variable_variablePrefix()  throws Exception {
        System.out.println("test 004_variable_variablePrefix\n"); //$NON-NLS-1$
        
        this.getSourceFormat().setHostVariablePrefix(':');
        updateParserManagerCharacterMapping();
        
        System.out.println(" * * * set hostvariable prefix to '"+this.getSourceFormat().getHostVariablePrefix()+"' * * * "); //$NON-NLS-1$ //$NON-NLS-2$
        
        List result = parserVerifySuccess(
            "SELECT EMPNO, LASTNAME, col1" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE WHERE col1 = :hostVar;" + NL + //$NON-NLS-1$
            "SELECT EMPNO, LASTNAME" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE WHERE DEPT = ? AND MGR = ?;", matchInput); //$NON-NLS-1$
        
        try {
	        QuerySelectStatement stmt = (QuerySelectStatement) result.get(0);
	        QuerySelect select = (QuerySelect) stmt.getQueryExpr().getQuery();
	        PredicateBasic pred = (PredicateBasic) select.getWhereClause();
	        QueryValueExpression expr = pred.getRightValueExpr();
	        assertTrue("expected hostvariable is not been parsed as hostvariable",  //$NON-NLS-1$
	                        expr instanceof ValueExpressionVariable);
        } catch (Exception e) {
            assertTrue("parse was successful but model was not constructed in the expected way",false); //$NON-NLS-1$
        }
                    
        this.getSourceFormat().setHostVariablePrefix('$');
        updateParserManagerCharacterMapping();
        
        System.out.println(" * * * set hostvariable prefix to '"+this.getSourceFormat().getHostVariablePrefix()+"' * * * "); //$NON-NLS-1$ //$NON-NLS-2$
        
        parserVerifyError(
            "SELECT EMPNO, LASTNAME, :hostVar" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE WHERE :hostVar = 12;" + NL + //$NON-NLS-1$
            "SELECT EMPNO, LASTNAME" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE WHERE DEPT = ? AND MGR = ?;"); //$NON-NLS-1$
        
        result = parserVerifySuccess(
            "SELECT EMPNO, LASTNAME, col1" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE WHERE col1 = $hostVar;" + NL + //$NON-NLS-1$
            "SELECT EMPNO, LASTNAME" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE WHERE DEPT = ? AND MGR = ?;", matchInput); //$NON-NLS-1$

        try {
	        QuerySelectStatement stmt = (QuerySelectStatement) result.get(0);
	        QuerySelect select = (QuerySelect) stmt.getQueryExpr().getQuery();
	        PredicateBasic pred = (PredicateBasic) select.getWhereClause();
	        QueryValueExpression expr = pred.getRightValueExpr();
	        assertTrue("expected hostvariable is not been parsed as hostvariable",  //$NON-NLS-1$
	                        expr instanceof ValueExpressionVariable);
        } catch (Exception e) {
            assertTrue("parse was successful but model was not constructed in the expected way",false); //$NON-NLS-1$
        }

        this.getSourceFormat().setParameterMarker('&');
        updateParserManagerCharacterMapping();
        
        // copy a new SourcFormat for future playing
        SQLQuerySourceFormat sf = SQLQuerySourceFormat.copyDefaultFormat();
        SQLQuerySourceFormat.copyFields(this.getSourceFormat(), sf);
        
        System.out.println(" * * * set param marker to '"+this.getSourceFormat().getParameterMarker()+"' * * * "); //$NON-NLS-1$ //$NON-NLS-2$
        
        parserVerifyError(
            "SELECT EMPNO, LASTNAME, $hostVar" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE WHERE $hostVar = 12;" + NL + //$NON-NLS-1$
            "SELECT EMPNO, LASTNAME" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE WHERE DEPT = ? AND MGR = ?;"); //$NON-NLS-1$
        
        parserVerifySuccess(
            "SELECT EMPNO, LASTNAME, col1" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE WHERE col1 = $hostVar;" + NL + //$NON-NLS-1$
            "SELECT EMPNO, LASTNAME" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE WHERE DEPT = & AND MGR = &;", matchInput); //$NON-NLS-1$

        
        this.getSourceFormat().setHostVariablePrefix(':');
        updateParserManagerCharacterMapping();
        
        System.out.println(" * * * set hostvariable prefix to '"+this.getSourceFormat().getHostVariablePrefix()+"' * * * "); //$NON-NLS-1$ //$NON-NLS-2$
        
        parserVerifySuccess(
            "SELECT EMPNO, LASTNAME, :hostVar" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE WHERE :hostVar = 12;" + NL + //$NON-NLS-1$
            "SELECT EMPNO, LASTNAME" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE WHERE DEPT = & AND MGR = &;", matchInput); //$NON-NLS-1$

        try {
            result = parserVerifySuccess(
                "SELECT EMPNO, LASTNAME, col1" + NL + //$NON-NLS-1$
                "  FROM EMPLOYEE WHERE col1 = $hostVar;", false); //$NON-NLS-1$

	        QuerySelectStatement stmt = (QuerySelectStatement) result.get(0);
	        QuerySelect select = (QuerySelect) stmt.getQueryExpr().getQuery();
	        PredicateBasic pred = (PredicateBasic) select.getWhereClause();
	        QueryValueExpression expr = pred.getRightValueExpr();
	        assertFalse("\"$hostVar\" expected to raise ParseException or to be parsed as identifier, but not as hostvariable",  //$NON-NLS-1$
	                        expr instanceof ValueExpressionVariable);
        } catch (Exception e) {
            //SQLParserException expected or no hostvariable
        } finally {
            // ensure next test cases run w/ correct setting even if that one fails
            this.getSourceFormat().setParameterMarker('?');
            updateParserManagerCharacterMapping();
        }
        
        QueryStatement query = parserVerifySuccessSingleQuery(
            "SELECT EMPNO, LASTNAME" + NL + //$NON-NLS-1$
            "  FROM EMPLOYEE" + NL + //$NON-NLS-1$
            "  WHERE LASTNAME = :hostVar" + NL + //$NON-NLS-1$
            "    AND DEPT = ?;", matchInput); //$NON-NLS-1$

        // copy the fields of the SourceFormat rather than setting a new
        // reference to it, which would not affect the SQLQueryObjects in the
        // QueryStatement (tree)
        SQLQuerySourceFormat.copyFields(sf, query.getSourceInfo().getSqlFormat());
        //query.getSourceInfo().setSqlFormat(sf);
        
        String sql = query.getSQL();

        System.out.println(" with source format for $ and & "); //$NON-NLS-1$
        System.out.println(sql);
        
        assertTrue("SourceFormat ooptions have no affect." + //$NON-NLS-1$
        		" Hostvar prefix should be " + sf.getHostVariablePrefix() + //$NON-NLS-1$
        				" and Parameter marker should be " + sf.getParameterMarker() + //$NON-NLS-1$
        				" in generated SQL source:\n" + sql, //$NON-NLS-1$
                        sql.indexOf(sf.getHostVariablePrefix()) > 0
                        && sql.indexOf(sf.getParameterMarker()) > 0);
        
        // reset the sourceFormat
        SQLQuerySourceFormat.copyFields(SQLQuerySourceFormat.SQL_SOURCE_FORMAT_DEFAULT, query.getSourceInfo().getSqlFormat());

    }
    
    //TODO: check schema qualifier
    public void testSqlDmlParser004_functions()  throws Exception {
        System.out.println("test 004_functions"); //$NON-NLS-1$
        parserVerifySuccess(
            "select schema1.func1(col1) from table1;" + NL + //$NON-NLS-1$
            "select count(distinct firstname) from employee;" + NL + //$NON-NLS-1$
            "select deptno, avg(age) from employee group by deptno order by avg(age);" + NL + //$NON-NLS-1$
            "", matchInput); //$NON-NLS-1$
        parserVerifySuccess(
                "select schema1.func1() from table1;" + NL + //$NON-NLS-1$
                "select func1() from table1;" + NL + //$NON-NLS-1$
                "", matchInput); //$NON-NLS-1$
        
        parserVerifySuccess(
                "SELECT INT(SUM(SALARY)) AS SAL,"       + NL + //$NON-NLS-1$ 
                "       SMALLINT(COUNT(*)) AS R"        + NL + //$NON-NLS-1$  
                "  FROM EMPLOYEE_VIEW"                  + NL + //$NON-NLS-1$ 
                "  ORDER BY SAL;"                       + NL + //$NON-NLS-1$
                "", matchInput); //$NON-NLS-1$
    }
    
    public void testSqlDmlParser004_tableFunctions() throws Exception {
        System.out.println("test 004_tableFunctions"); //$NON-NLS-1$
        
        parserVerifySuccess(
                "select c1, c2 from table (tablefunc1()) t1 where c1 = 'foo';" + NL + //$NON-NLS-1$
                "select c1, c2 from table (tablefunc1()) as t1 where c1 = 'foo';" + NL + //$NON-NLS-1$
                "select t2.c1 from t1, table (schema1.tablefunc2()) as t2;" + NL + //$NON-NLS-1$
                "select t2.c1 from table1 as t1, table (schema1.tablefunc2()) as t2;" + NL + //$NON-NLS-1$
                "select c1, c2 from table (tablefunc1(parm1, parm2)) t1 where c1 = 'foo';" + NL + //$NON-NLS-1$
                "select c1, c2 from table (tablefunc1(parm1, parm2)) t1(c1, c2, c3);" + NL + //$NON-NLS-1$
                "", matchInput); //$NON-NLS-1$
    }
    
    public void testSqlDmlParser004_durations()  throws Exception {
        System.out.println("test 004_durations"); //$NON-NLS-1$
        parserVerifySuccess(
            "select HIREDATE + 1 YEARS + 2 MONTHS + 14 DAYS from employee;" + NL + //$NON-NLS-1$
            // TODO: supposed to fail, see SQL Xref 2 p.109
            //"select HIREDATE + (2 MONTHS + 14 DAYS) from employee;" + NL +
            "select SYNC + 2 HOURS + 30 MINUTES + 10 SECONDS + 500 MICROSECONDS from nightly_proc;" + NL + //$NON-NLS-1$
            "", matchInput); //$NON-NLS-1$
    }
   
    public void testSqlDB2Parser004_special_registers() throws Exception {
        System.out.println("test 004_special_registers"); //$NON-NLS-1$
        
        
        String[] specialRegs = new String[] {
                "CURRENT_DATE", //$NON-NLS-1$
                "CURRENT_TIME", //$NON-NLS-1$
                "CURRENT_TIMESTAMP", //$NON-NLS-1$
                "LOCALTIME", //$NON-NLS-1$
                "LOCALTIMESTAMP", //$NON-NLS-1$
                "CURRENT_DEFAULT_TRANSFORM_GROUP", //$NON-NLS-1$
                "CURRENT_TRANSFORM_GROUP_FOR_TYPE schema1.type1", //$NON-NLS-1$
                "CURRENT_TRANSFORM_GROUP_FOR_TYPE \"schema1\".\"type1\"", //$NON-NLS-1$
                "CURRENT_PATH", //$NON-NLS-1$
                "CURRENT_ROLE", //$NON-NLS-1$
                "CURRENT_USER", //$NON-NLS-1$
                "SESSION_USER", //$NON-NLS-1$
                "SYSTEM_USER", //$NON-NLS-1$
                "USER", //$NON-NLS-1$
                "VALUE"}; //$NON-NLS-1$


        String stmtHead = "select "; //$NON-NLS-1$
        String stmtTail = " from tab1;\n"; //$NON-NLS-1$
        
        StringBuffer stmt = new StringBuffer();
        
        for (int i = 0; i < specialRegs.length; i++) {
            String specialReg = specialRegs[i];
            stmt.append(stmtHead);
            stmt.append(specialReg);
            stmt.append(stmtTail);
        }
        
        List parseResults = parserVerifySuccess(stmt.toString(), matchInput);
        
        // assert we have a special register function and no column ref,
        // say Lexer works correct and knows the special registers as keywords
        for (Iterator iter = parseResults.iterator(); iter.hasNext();) {
            QuerySelectStatement query = (QuerySelectStatement) iter.next();
            QueryExpressionRoot queryExpr = query.getQueryExpr();
            QuerySelect querySelect = (QuerySelect) queryExpr.getQuery();
            ResultColumn resultColumn =
                (ResultColumn) querySelect.getSelectClause().get(0);
            QueryValueExpression valueExpr = resultColumn.getValueExpr();
            assertTrue("special register "+valueExpr.getName()+ //$NON-NLS-1$
                    " must be parsed as function ValueExpressionFunction" + //$NON-NLS-1$
                    " not as "+valueExpr.getClass().getName(), //$NON-NLS-1$
                    valueExpr instanceof ValueExpressionFunction);
            ValueExpressionFunction func = (ValueExpressionFunction) valueExpr;
            assertTrue("special registers must be parsed as functions " + //$NON-NLS-1$
                    "with flag \"isSpecialRegister()\"", func.isSpecialRegister()); //$NON-NLS-1$
        }
        
    }
    
    // TODO: extensive test of valids and extensive test of invalids!
    public void testSqlDmlParser004_castExpression_PredefinedType()  throws Exception {
        System.out.println("test 004_castExpression_PredefinedType"); //$NON-NLS-1$
        
        String[][] dataTypeGroupList = new String[][] {
                        new String[] {  "SMALLINT", //verify error on precision //$NON-NLS-1$
                                        "INT",
                                        "INTEGER",
                                        "BIGINT"
                        },
                        new String[] {  "DECIMAL", 
                                        "DEC", 
                                        "NUMERIC", 
                                        "DECIMAL (1)", 
                                        "DEC (10)",
                                        "NUMERIC (31)", // verify error in precision greater 31 
                                      //  "DECIMAL (5,0)", // gets generated as DECIMAL(5), so doesn't match
                                        "DEC (10,10)", 
                                        "NUMERIC (15,5)" //verify error on scale larger than precision 'DEC(5,6)' 
                        },
                        new String[] {  "FLOAT", 
                                        "FLOAT (1)", 
                                        "FLOAT (53)", // verify error in precision greater 53 
                                        "REAL", 
                                        "DOUBLE", 
                                        "DOUBLE PRECISION"  
                        },
                        new String[] {  //"CHARACTER", // gets generated as CHARACTER(1), so doesn't match
                                        "CHARACTER (1)", 
                                        "CHARACTER (256)", 
                                        //"CHAR", // gets generated as CHARACTER(1), so doesn't match
                                        "CHAR (256)", 
                                        "CHARACTER VARYING (1)", 
                                        "CHARACTER VARYING (256)", 
                                        "CHAR VARYING (256)", 
                                        "VARCHAR (1)", //verify error on missing '(length)'
                                        "VARCHAR (256)", 
                                        "CLOB (1024)", 
                                        "CLOB (1024K)",
                                    //    "CLOB (2048M)", // gets generated as CLOB (2G), so doesn't match
                                        "CLOB (2G)" 
                        },
                        new String[] {  //"GRAPHIC", // gets generated as GRAPHIC(1), so doesn't match
                                        "GRAPHIC (1)", 
                                        "GRAPHIC (256)", 
                                        "VARGRAPHIC (256)",
                                        "DBCLOB (1024)",
                                        "DBCLOB (1024K)", 
                                     //  "DBCLOB (2048M)", // gets generated as DBCLOB (2G), so doesn't match
                                        "DBCLOB (2G)" 
                        },
                        new String[] {  "BLOB (1024)",
                                        "BLOB (1024 )",
                                     //   "BLOB (2048M)", // gets generated as BLOB(2G), so doesn't match
                                        "BLOB (2G)"
                        },
                        new String[] {  "DATE",
                                        "TIME",
                                        "TIMESTAMP"
                        }
                         
        
        };
        
        for (int i = 0; i < dataTypeGroupList.length; i++)
        {
            String[] dataTypeGroup = dataTypeGroupList[i];
            StringBuffer dataTypeGroupStmts = new StringBuffer();
            
            for (int j = 0; j < dataTypeGroup.length; j++)
            {
                String castExprs = "cast ( col"+j+" as "+dataTypeGroup[j]+")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                String select = "select "+castExprs+" from table"+i; //$NON-NLS-1$ //$NON-NLS-2$
                dataTypeGroupStmts.append( select + ";" + NL ); //$NON-NLS-1$
            }
            
            parserVerifySuccess(dataTypeGroupStmts.toString(), matchInput);
        }
        parserVerifyErrorSingle("select cast(colA as CHAR("+(Integer.MAX_VALUE+1)+")) from tableA; --should raise NumberFormatException\n");//MAX_INT+1==2147483648 //$NON-NLS-1$ //$NON-NLS-2$
        parserVerifyErrorSingle("select cast(colA as CLOB("+(2097152+1)+" K)) from tableA;  --should raise NumberFormatException\n"); //$NON-NLS-1$ //$NON-NLS-2$
        parserVerifyErrorSingle("select cast(colA as CLOB("+(2048   +1)+" M)) from tableA;  --should raise NumberFormatException\n"); //$NON-NLS-1$ //$NON-NLS-2$
        parserVerifyErrorSingle("select cast(colA as CLOB("+(2      +1)+" G)) from tableA;  --should raise NumberFormatException\n"); //$NON-NLS-1$ //$NON-NLS-2$
        //TODO: activate the following test case, VARCHAR should not be accepted as userdefined type -- since softkeyword support...
        //parserVerifyErrorSingle("select cast(colA as VARCHAR) from tableA; --should raise parse error because mandatory (length) is missing\n");
        
    }

    //TODO: elaborate these test cases, fix validation with parse post processing and Span info
    // and activate parsing/ deactivate dummy parse
    public void testSqlDmlParser004_castExpression_PredefinedType_notAccepted()  throws Exception {
        System.out.println("test 004_castExpression_PredefinedType_notAccepted"); //$NON-NLS-1$
        
        String[][] dataTypeGroupList = new String[][] {
                        new String[] {  "SMALLINT (5)", // 5 is max, but should not be specified //$NON-NLS-1$
                                        "SMALLINT (10)", // 5 is max, but should not be specified //$NON-NLS-1$
                                        "INT (11)", // 10 is max, but should not be specified //$NON-NLS-1$
                                        "INTEGER (11)", // 10 is max, but should not be specified //$NON-NLS-1$
                                        "SMALLINT (10,10)", // 5 is max, scale always 0, but should not be specified //$NON-NLS-1$
                                        "INT (10,11)",  // 10 is max, scale always <= precision, but should not be specified //$NON-NLS-1$
                                        "INTEGER (10,0)" //$NON-NLS-1$
                                        //, BIGINT
                        },
                        new String[] {  "DECIMAL (-1)", //$NON-NLS-1$
                                        
        // TODO: go on below here
                                        
                                        "DEC (10)", //$NON-NLS-1$
                                        "NUMERIC (31)", // verify error in precision greater 31 //$NON-NLS-1$
                                        "DECIMAL (5,0)", //$NON-NLS-1$
                                        "DEC (10,10)", //$NON-NLS-1$
                                        "NUMERIC (15,5)" //verify error on scale larger than precision 'DEC(5,6)' //$NON-NLS-1$
                        },
                        new String[] {  "FLOAT", //$NON-NLS-1$
                                        "FLOAT (1)", //$NON-NLS-1$
                                        "FLOAT (53)", // verify error in precision greater 53 //$NON-NLS-1$
                                        "REAL", //$NON-NLS-1$
                                        "DOUBLE PRECISION", //$NON-NLS-1$
                                        "DOUBLE" //$NON-NLS-1$
                        },
                        new String[] {  "CHARACTER", //$NON-NLS-1$
                                        "CHARACTER (1)", //$NON-NLS-1$
                                        "CHARACTER (256)", //$NON-NLS-1$
                                        "CHAR", //$NON-NLS-1$
                                        "CHAR (256)", //$NON-NLS-1$
                                        "CHARACTER VARYING (1)", //$NON-NLS-1$
                                        "CHARACTER VARYING (256)", //$NON-NLS-1$
                                        "CHAR VARYING (256)", //$NON-NLS-1$
                                        "VARCHAR (1)", //verify error on missing '(length)' //$NON-NLS-1$
                                        "VARCHAR (256)", //$NON-NLS-1$
                                        "CLOB (1024)", //$NON-NLS-1$
                                        "CLOB (1024 K)", //$NON-NLS-1$
                                        "CLOB (2048 M)", //$NON-NLS-1$
                                        "CLOB (2 G)" //$NON-NLS-1$
                        },
                        new String[] {  "GRAPHIC", //$NON-NLS-1$
                                        "GRAPHIC (1)", //$NON-NLS-1$
                                        "GRAPHIC (256)", //$NON-NLS-1$
                                        "VARGRAPHIC (256)", //$NON-NLS-1$
                                        "DBCLOB (1024)", //$NON-NLS-1$
                                        "DBCLOB (1024 K)", //$NON-NLS-1$
                                        "DBCLOB (2048 M)", //$NON-NLS-1$
                                        "DBCLOB (2 G)" //$NON-NLS-1$
                        },
                        new String[] {  "BLOB (1024)", //$NON-NLS-1$
                                        "BLOB (1024 K)", //$NON-NLS-1$
                                        "BLOB (2048 M)", //$NON-NLS-1$
                                        "BLOB (2 G)" //$NON-NLS-1$
                        },
                        new String[] {  "DATE", //$NON-NLS-1$
                                        "TIME", //$NON-NLS-1$
                                        "TIMESTAMP" //$NON-NLS-1$
                        }
                         
        
        };
        
        for (int i = 0; i < dataTypeGroupList.length; i++)
        {
            String[] dataTypeGroup = dataTypeGroupList[i];
            StringBuffer dataTypeGroupStmts = new StringBuffer();
            
            for (int j = 0; j < dataTypeGroup.length; j++)
            {
                String castExprs = "cast( col"+j+" as "+dataTypeGroup[j]+")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                String select = "select "+castExprs+" from table"+i; //$NON-NLS-1$ //$NON-NLS-2$
                dataTypeGroupStmts.append( select + ";" + NL ); //$NON-NLS-1$
            }
 
            //TODO: deactivate dummy parse below, uncomment right parse
            //parserVerifySuccess(dataTypeGroupStmts.toString());
        }
        //TODO: deactivate dummy parse, uncomment right parse obove
        parserVerifySuccess("-- implement test cases and parser validierung, then remove dummy select\nselect * from table1;", false); //$NON-NLS-1$
    }

    public void testSqlDmlParser004_castExpression_UserDefinedType()  throws Exception {
        System.out.println("test 004_castExpression_UserDefinedType");
        String u = "update cl_sched set col0 = 00120"; //$NON-NLS-1$
        parserVerifySuccess(
            "update cl_sched set col0 = 00120 where col0 = cast( col0 as user_def_distinct_type );" + NL +
            "select cast(col1 as myschema.mytype) from t1;" + NL +
            "select cast(col1 as myschema.\"mytype\") from t1;" + NL +
            "select cast(col1 as \"myschema\".\"mytype\") from t1;" + NL +
            "", matchInput); //$NON-NLS-1$
    }


    
    public void testSqlDmlParser004_modelHelperTests()  throws Exception {
        System.out.println("test 004_modelHelperTests"); //$NON-NLS-1$
    
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(
                    "with withTable (col1) as (values (256))                   " + NL + //$NON-NLS-1$
                    "select *                                           " + NL + //$NON-NLS-1$
                    "  from table1 t1, (table2 n1), ((table2 n2)),      " + NL + //$NON-NLS-1$
                    "       tbl3 t3 join tbl4 on t3.c1 = t4.c1,         " + NL + //$NON-NLS-1$
                    "       withTable as wt,                            " + NL + //$NON-NLS-1$
                    "       (select * from tbl5, tbl6) as nq1;", false);         //$NON-NLS-1$

        QueryExpressionRoot qer = query.getQueryExpr();
        QuerySelect select = (QuerySelect) qer.getQuery();
        List tableRefList = select.getFromClause();
        List tableInDBList = TableHelper.getTableInDatabaseInTableReferenceList(tableRefList);
        String dbTableList = new String();
        for (Iterator it = tableInDBList.iterator(); it.hasNext();) {
            TableInDatabase tableInDB = (TableInDatabase) it.next();
            dbTableList = dbTableList.concat(tableInDB.getName());
            if (it.hasNext()) dbTableList = dbTableList.concat(", "); //$NON-NLS-1$
        }
        System.out.println("found table in database: "+dbTableList); //$NON-NLS-1$
        assertTrue("helper method to retrieve database tables does not work, found TableInDatabases: "+dbTableList, //$NON-NLS-1$
                tableInDBList.size() == 5);
        
        // test StatementHelper#getSQLSourceUnformatted(SQLQueryObject)
        query =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(
                    "with withTable (col1) as (values (256))            " + NL + //$NON-NLS-1$
                    "select col1 - col2 -- funny comment                " + NL + //$NON-NLS-1$
                    "  from table1 t1, (table2 n1), ((table2 n2)),      " + NL + //$NON-NLS-1$
                    "       tbl3 t3 join tbl4 on t3.c1 = t4.c1,         " + NL + //$NON-NLS-1$
                    "       withTable as wt,  -- withTable              " + NL + //$NON-NLS-1$
                    "       (select * from tbl5, tbl6) as nq1           " + NL + //$NON-NLS-1$
                    "  where col1 in ( 256, 128, 64  );", false);         //$NON-NLS-1$

        String rawSQL = StatementHelper.getSQLSourceUnformatted(query);
        
        assertTrue("SQL should not contain comments",
                rawSQL.toLowerCase().indexOf("-- funny comment") < 0
                &&  rawSQL.toLowerCase().indexOf("-- withTable") < 0 );
        
        // does the unformatted SQL parse?
        query =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(rawSQL, false);
                
        char delimIdQt = getParserManager().getSourceFormat().getDelimitedIdentifierQuote();
        assertTrue("unformatted SQL should syntactically match the formatted one after reparse",
                StatementHelper.compareSQL(rawSQL, query.getSQL(),delimIdQt) == 0);

        // test StatementHelper#getSQLWithoutComments(SQLQueryObject)
        query =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(
                    " -- starting line comment                          " + NL + //$NON-NLS-1$
                    "select col1 - col2 -- funny comment                " + NL + //$NON-NLS-1$
                    "  from table1 t1,  -- join to come                 " + NL + //$NON-NLS-1$
                    "       tbl3 t3 join tbl4 on t3.c1 = t4.c1,         " + NL + //$NON-NLS-1$
                    "       -- nested select next                       " + NL + //$NON-NLS-1$
                    "       (select * from tbl5, tbl6) as nq1           " + NL + //$NON-NLS-1$
                    "  -- where col1 in ( 256, 128, 64  );", false);         //$NON-NLS-1$

        rawSQL = StatementHelper.getSQLWithoutComments(query);
        String comSQL = query.getSQL();
        
        assertTrue("SQL should not contain comments", rawSQL.indexOf("--") < 0);
        assertTrue("non-comment SQL should syntactically match the original SQL:\n"
                + rawSQL + "\ncompared to:\n" + comSQL,
                StatementHelper.compareSQL(rawSQL, comSQL, delimIdQt) == 0);
        
        // does the uncommented SQL parse?
        System.out.println("\n\nSQL without comments should parse:\n"+rawSQL);
        query =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(rawSQL, matchInput);
                

    }
    
 

    public void testSqlDmlParser004_modelTests()  throws Exception {
        System.out.println("test 004_modelTests"); //$NON-NLS-1$
        
        // TODO: implement test for parser resolve of tables and columns
        
        // consider result columns in subqueries
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(
                            "select table1.col1, t1.col2 as c2 from table1 t1," + //$NON-NLS-1$
                            " (select col1 from table0) as query0;", false); //$NON-NLS-1$
        assertTrue( columnRefTable(query, "table1.col1", null) ); //$NON-NLS-1$
        assertTrue( columnRefTable(query, "t1.col2", "t1") ); //$NON-NLS-1$ //$NON-NLS-2$
        
        // to debug
        String tableName = "T1"; //$NON-NLS-1$
        List tableExprList = StatementHelper.getTablesForStatement(query);
        TableExpression tableExpr = TableHelper.getTableExpressionFromTableExprList(tableName, tableExprList);
        StatementHelper.removeTableExpressionFromQueryStatement(tableExpr, query);
        
        
        
        // test hostvariable recognition and the helper method returning all of'em
        QueryStatement hostVarQuery = parserVerifySuccessSingleQuery(
        	"select :v1, (col1+:v2) from table1 as t1, (select col1, :v3 from table0) as query0 where :v3 = :v2+:v4;", false); //$NON-NLS-1$
        List hostVarList = StatementHelper.getAllVariablesInQueryStatement(hostVarQuery);
        for (Iterator varIt = hostVarList.iterator(); varIt.hasNext();)
        {
            ValueExpressionVariable hostVar = (ValueExpressionVariable) varIt.next();
            Predicate pred = StatementHelper.getPredicateOfVariable(hostVar);
            System.out.println("Found hostVariable: "+hostVar.getName()+" within predicate: "+pred); //$NON-NLS-1$ //$NON-NLS-2$
        }

        // test hostvariable recognition and the helper method returning all of'em
        // if there are no hostvars
        QueryStatement hostVarQuery2 = parserVerifySuccessSingleQuery(
        	"select * from table1 t1, table2 t2;", matchInput); //$NON-NLS-1$
        List hostVarList2 = StatementHelper.getAllVariablesInQueryStatement(hostVarQuery2);
        assertTrue("should be no hostvariables found in stmt w/o hostvariables",hostVarList2 == null || hostVarList2.isEmpty()); //$NON-NLS-1$

        
        // test distinct column recognition helper method -- not needed anymore
//        QueryStatement duplicateColQuery = parserVerifySuccessSingleQuery(
//        	"with table1 (col1,col2,\"CoL2\") as ( values (256,256,256) )" +
//        	"select t1.col1, (t2.col1+t1.\"CoL2\") as col2 from table1 as t1, table1 as t2 where t1.col1 = t2.CoL1-t1.CoL2 order by t1.col1, col2;");
//        QuerySelect select = (QuerySelect) ((QuerySelectStatement)duplicateColQuery).getQueryExpr().getQuery();
//        tableExpr = (TableExpression) select.getFromClause().get(0);
//        Set distinctColumnSet = TableHelper.getDistinctColumnExpressionList(tableExpr);
//        for (Iterator colIt = distinctColumnSet.iterator(); colIt.hasNext();)
//        {
//            ValueExpressionColumn col = (ValueExpressionColumn) colIt.next();
//            System.out.println("distinct Column found in table1 t1: "+col.getName());
//        }
        

        
        //TODO: test valueExpressiontypes, what if one hostVar for example is 
        //      used one time as number and another time as string?
        
    }

    
    
    
    
    
    
    
    public void testSqlDmlParser004_variableStatementTerminator()  throws Exception {
        System.out.println("test 004_variableStatementTerminator"); //$NON-NLS-1$
        
        //SQLQueryParserManager.testCharacterKindMap();
        
        // TODO: to really test it we must maintain ONE CharacterKindMap, now the parser gives a new one for every call
        
        parserVerifySuccess(
        	"select * from table0;" + NL + //$NON-NLS-1$
        	"select * from table1;" + NL + //$NON-NLS-1$
        	"", matchInput); //$NON-NLS-1$
        
        this.getSourceFormat().setStatementTerminator('!');
        updateParserManagerCharacterMapping();
        
        parserVerifySuccess(
        	"select * from table0!" + NL + //$NON-NLS-1$
        	"select * from table1!" + NL + //$NON-NLS-1$
        	"", matchInput); //$NON-NLS-1$
                    
        parserVerifyError(
        	"select * from table0;" + NL + //$NON-NLS-1$
        	"select * from table1;" + NL + //$NON-NLS-1$
        	""); //$NON-NLS-1$
                                
        parserVerifyError(
        	"select * from table0!" + NL + //$NON-NLS-1$
        	"select * from table1;" + NL + //$NON-NLS-1$
        	""); //$NON-NLS-1$
                                            
        this.getSourceFormat().setStatementTerminator(';');
        updateParserManagerCharacterMapping();
        
        parserVerifySuccess(
        	"select * from table0;" + NL + //$NON-NLS-1$
        	"select * from table1;" + NL + //$NON-NLS-1$
        	"", matchInput); //$NON-NLS-1$
                    
        this.getSourceFormat().setStatementTerminator('!');
        updateParserManagerCharacterMapping();
        
        parserVerifySuccess(
        	"select * from table0!" + NL + //$NON-NLS-1$
        	"select * from table1!" + NL + //$NON-NLS-1$
        	"", matchInput); //$NON-NLS-1$
                    
        this.getSourceFormat().setStatementTerminator('&');
        updateParserManagerCharacterMapping();
        
        parserVerifySuccess(
        	"select * from table0&" + NL + //$NON-NLS-1$
        	"select * from table1&" + NL + //$NON-NLS-1$
        	"", matchInput); //$NON-NLS-1$
                    
        this.getSourceFormat().setStatementTerminator(';');
        updateParserManagerCharacterMapping();
        
        parserVerifySuccess(
        	"select * from table0;" + NL + //$NON-NLS-1$
        	"select * from table1;" + NL + //$NON-NLS-1$
        	"", matchInput); //$NON-NLS-1$
        
    }

    public void testSqlDmlParser004_omitSchema_SCHEMA1()  throws Exception {
        System.out.println("test 004_omitSchema_SCHEMA1"); //$NON-NLS-1$
        
        this.getSourceFormat().setOmitSchema("SCHEMA1"); //$NON-NLS-1$
        
        QuerySelectStatement query0 =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(
                            "select table1.col1, t1.col2, t2.col1" + //$NON-NLS-1$
                            " from table1 t1, schema2.table1 t2;", false); //$NON-NLS-1$
        assertTrue( columnRefTable(query0, "table1.col1", null) ); //$NON-NLS-1$
        assertTrue( columnRefTable(query0, "t1.col2", "t1") ); //$NON-NLS-1$ //$NON-NLS-2$
        assertTrue( columnRefTable(query0, "t2.col1", "t2") ); //$NON-NLS-1$ //$NON-NLS-2$

        System.out.println("\n * * * source with omitSchema = SCHEMA1 * * * \n"); //$NON-NLS-1$
        query0.getSourceInfo().getSqlFormat().setOmitSchema("SCHEMA1"); //$NON-NLS-1$
        String sqlSource = query0.getSQL();
        System.out.println(sqlSource);
        assertTrue( sqlSource.toUpperCase().indexOf("SCHEMA1") < 0); //$NON-NLS-1$
        
        System.out.println("\n * * * source with omitSchema = SCHEMA2 * * * \n"); //$NON-NLS-1$
        query0.getSourceInfo().getSqlFormat().setOmitSchema("SCHEMA2"); //$NON-NLS-1$
        sqlSource = query0.getSQL();
        System.out.println(sqlSource);
        assertTrue( sqlSource.toUpperCase().indexOf("SCHEMA2") < 0); //$NON-NLS-1$
        
        // this SQLFormat is the same instance as the one referenced by query0
        // so reset the schema to omit to SCHEMA1
        this.getSourceFormat().setOmitSchema("SCHEMA1"); //$NON-NLS-1$
        
        QuerySelectStatement query1 = (QuerySelectStatement) parserVerifySuccessSingleQuery(
        	"select table1.col1, t1.col2 as c2 from schema1.table1 as t1, (select col1 from schema2.table1) as query0;", false); // colRef "table1.col1" will not be resolved and SQL will not match //$NON-NLS-1$
        QuerySelectStatement query2 = (QuerySelectStatement) parserVerifySuccessSingleQuery(
        	"select schema1.table1.col1, t1.col2 from schema1.table1 as t1, schema2.table1 as t2;", false); //$NON-NLS-1$
        
        assertTrue("\"table1.col1\" should not be resolved, table \"schema1.table1 as t1\" will only be accessible via alias name \"t1\"", //$NON-NLS-1$
                        columnRefTable(query1, "table1.col1", null)); //$NON-NLS-1$
        assertTrue("\"schema1.table1.col1\" should not be resolved, table \"schema1.table1 as t1\" will only be accessible via alias name \"t1\"", //$NON-NLS-1$
                        columnRefTable(query2, "schema1.table1.col1", null)); //$NON-NLS-1$
        
        
        System.out.println("\n * * * reset omitSchema to SCHEMA2 * * * \n"); //$NON-NLS-1$
        
        SQLQuerySourceFormat sf = SQLQuerySourceFormat.copyDefaultFormat();
        sf.setOmitSchema("SCHEMA2"); //$NON-NLS-1$
        query1.getSourceInfo().setSqlFormat(sf);
        query2.getSourceInfo().setSqlFormat(sf);
        
        System.out.println("Statement0: "+query1.getSQL()); //$NON-NLS-1$
        System.out.println("Statement1: "+query2.getSQL()); //$NON-NLS-1$
        

    }
    
    public void testSqlDmlParser004_groupBy_super()  throws Exception {
        System.out.println("test 004_groupBy_super"); //$NON-NLS-1$
        
        parserVerifySuccess(
        	"select * from table0 GROUP BY CUBE(Province, (County, City));" + NL + //$NON-NLS-1$
        	"select * from table1 GROUP BY ROLLUP(Province, (County, City));" + NL + //$NON-NLS-1$
        	"select * from table2 GROUP BY ();" + NL + //$NON-NLS-1$
        	"", matchInput); //$NON-NLS-1$
        parserVerifySuccess(
                "SELECT EMPNO, PROJNO"                          + NL + //$NON-NLS-1$
                "  FROM EMP_ACT"                                + NL + //$NON-NLS-1$
                " GROUP BY GROUPING SETS ((empno,projno),"      + NL + //$NON-NLS-1$
                "                         (empno),"             + NL + //$NON-NLS-1$
                "                         (projno),"            + NL + //$NON-NLS-1$
                "                         ());"                 + NL + //$NON-NLS-1$
                "", matchInput); //$NON-NLS-1$
        parserVerifySuccess(
        	"select * from table3 GROUP BY Province, (County, City) WITH CUBE;" + NL + //$NON-NLS-1$
        	"select * from table4 GROUP BY Province, (County, City) WITH ROLLUP;" + NL + //$NON-NLS-1$
        	"", false); //$NON-NLS-1$
        
        QueryStatement superGroupStmt = parserVerifySuccessSingleQuery(
            "select * from tbl5 t5 GROUP BY CUBE(t5.Brand, (t5.Sales), (t5.City,ZIP));", false); // with one table only we will might not have qualified colRefs, we don't know how colRefs are generated //$NON-NLS-1$
        
        QuerySelectStatement selectStmt = (QuerySelectStatement) superGroupStmt;
        QuerySelect select = (QuerySelect) selectStmt.getQueryExpr().getQuery();
        List groupByClause = select.getGroupByClause();
        SuperGroup superGroup = (SuperGroup) groupByClause.get(0);
        // (Sales) should not be parsed as SuperGroupElementExpression with a ValueExpressionNested 
        // but should be parsed as a one-element SuperGroupElementSublist with a simple Column
        SuperGroupElement notBeSGEExpr = (SuperGroupElement) superGroup.getSuperGroupElementList().get(1); 
        
        //<super_groups_element> ::=?
		//		_LPAREN <super_groups_element_exp_list> _RPAREN
		//			/. !BeginAction setSym1( m_factory.createSuperGroupsElementSublist(getList(2)) );  !EndAction ./
        //	|	<super_groups_element_exp>
        assertTrue("Any one-element SuperGroupElementSublist should not be parsed" + //$NON-NLS-1$
	    		" into a SuperGroupElementExpression with a simple nested ValueExpression" + //$NON-NLS-1$
	    		"  e.g. ...GROUP BY CUBE(Brand, (Sales), (City,ZIP))" + //$NON-NLS-1$
	    		"  where (Sales)  could be a SuperGroupElementExpression with an" + //$NON-NLS-1$
	    		" ValueExpressionColumn in parenthesis" + //$NON-NLS-1$
	    		" or a SuperGroupElementSublist with only one" + //$NON-NLS-1$
	    		" SuperGroupElementExpression that has a ValueExpressionColumn", //$NON-NLS-1$
	    		notBeSGEExpr instanceof SuperGroupElementSublist);
    }
    
    public void testSqlDmlParser004_groupBy_grouping_sets()  throws Exception {
        System.out.println("test 004_groupBy_grouping_sets"); //$NON-NLS-1$
        
        parserVerifySuccess(
        	"select * from table0 GROUP BY GROUPING SETS (Province, (County, City));" + NL + //$NON-NLS-1$
        	"select * from table1 GROUP BY GROUPING SETS ((County, City));" + NL + //$NON-NLS-1$
        	"select * from table2 GROUP BY GROUPING SETS ((County));" + NL + //$NON-NLS-1$
        	"select * from table3 GROUP BY GROUPING SETS (County);" + NL + //$NON-NLS-1$
        	"", matchInput); //$NON-NLS-1$
        
        QueryStatement superGroupStmt = parserVerifySuccessSingleQuery(
            "select * from tbl4 GROUP BY GROUPING SETS (Brand, (Sales), (City,ZIP));", matchInput); //$NON-NLS-1$
        
        QuerySelectStatement selectStmt = (QuerySelectStatement) superGroupStmt;
        QuerySelect select = (QuerySelect) selectStmt.getQueryExpr().getQuery();
        List groupByClause = select.getGroupByClause();
        GroupingSets groupingSet = (GroupingSets) groupByClause.get(0);
        // (Sales) should not be parsed as GroupingSetsElementExpression with a ValueExpressionNested 
        // but should be parsed as a one-element GroupingSetsElementSublist with a simple Column
        GroupingSetsElement notBeGSEExpr = (GroupingSetsElement) groupingSet.getGroupingSetsElementList().get(1); 
        
        //<grouping_sets_element> ::=?
		//		_LPAREN <grouping_sets_element_exp_list> _RPAREN
		//			/. !BeginAction setSym1( m_factory.createGroupingSetsElementSublist(getList(2)) );  !EndAction ./
        //	|	<grouping_sets_element_exp>
        assertTrue("Any one-element GroupingSetsElementSublist should not be parsed" + //$NON-NLS-1$
	    		" into a GroupingSetsElementExpression with a simple nested ValueExpression" + //$NON-NLS-1$
	    		"  e.g. ...GROUP BY CUBE(Brand, (Sales), (City,ZIP))" + //$NON-NLS-1$
	    		"  where (Sales)  could be a GroupingSetsElementExpression with an" + //$NON-NLS-1$
	    		" ValueExpressionColumn in parenthesis" + //$NON-NLS-1$
	    		" or a GroupingSetsElementSublist with only one" + //$NON-NLS-1$
	    		" GroupingSetsElementExpression that has a ValueExpressionColumn", //$NON-NLS-1$
	    		notBeGSEExpr instanceof GroupingSetsElementSublist);
        
        parserVerifySuccess(
                "SELECT D1,"                                + NL + //$NON-NLS-1$
                "       DEPT,"                              + NL + //$NON-NLS-1$
                "       SEX,"                               + NL + //$NON-NLS-1$
                "       INT(SUM(SALARY))   AS SAL,"         + NL + //$NON-NLS-1$
                "       SMALLINT(COUNT(*)) AS R,"           + NL + //$NON-NLS-1$
                "       GROUPING(D1)       AS F1,"          + NL + //$NON-NLS-1$
                "       GROUPING(DEPT)     AS FD,"          + NL + //$NON-NLS-1$
                "       GROUPING(SEX)      AS FS"           + NL + //$NON-NLS-1$
                "  FROM EMPLOYEE_VIEW"                      + NL + //$NON-NLS-1$
                "  GROUP BY GROUPING SETS ((D1, DEPT, SEX),"+ NL + //$NON-NLS-1$
                "                          (D1, DEPT),"     + NL + //$NON-NLS-1$
                "                          (D1, SEX),"      + NL + //$NON-NLS-1$
                "                          (DEPT, SEX),"    + NL + //$NON-NLS-1$
                "                          (D1),"           + NL + //$NON-NLS-1$
                "                          (DEPT),"         + NL + //$NON-NLS-1$
                "                          (SEX),"          + NL + //$NON-NLS-1$
                "                          ())"             + NL + //$NON-NLS-1$
                "  ORDER BY D1,"                            + NL + //$NON-NLS-1$
                "           DEPT,"                          + NL + //$NON-NLS-1$
                "           SEX;"                           + NL + //$NON-NLS-1$
                "", matchInput);                                   //$NON-NLS-1$
    }
    
    
    public void testSqlDmlParser004_groupBy_clause()  throws Exception {
        System.out.println("test 004_groupBy_clause"); //$NON-NLS-1$
        
        parserVerifySuccess(
        	"select col1, province from table0" + //$NON-NLS-1$
        	"  GROUP BY col1," + //$NON-NLS-1$
        	"      GROUPING SETS (Province, (County, City))," + //$NON-NLS-1$
        	"      CUBE (Province, (County, City));" + NL + //$NON-NLS-1$
        	"", matchInput); //$NON-NLS-1$
    } 

    
    public void testSqlDmlParser004_with_table_spec()  throws Exception {
        System.out.println("test 004_with_table_spec"); //$NON-NLS-1$
        
        parserVerifySuccess(
        	"with temp_table1 (tcol1, tcol2) as (select col1, col2 from table2)" + NL + //$NON-NLS-1$
        	"select * from temp_table1 t1, table3, temp_table1 t2;" + NL + //$NON-NLS-1$
        	
        	"with temp_table1 (tcol1, tcol2) as (select * from table1)," + //$NON-NLS-1$
        	"     temp_table2 as (select col1, col2 from table2)" + NL + //$NON-NLS-1$
        	"select * from tabl3;" + NL + //$NON-NLS-1$
        	"", matchInput); //$NON-NLS-1$
        	
       parserVerifySuccess(
        	"with" + NL + //$NON-NLS-1$
        	"  temp_table1 (tcol1, tcol2) as" + NL + //$NON-NLS-1$
        	"     (select t1.col1, t2.col2" + NL + //$NON-NLS-1$
        	"        from table1 as t1, table2 as t2" + NL + //$NON-NLS-1$
        	"        where t1.col3 = t2.col5" + NL + //$NON-NLS-1$
        	"        group by t1.col1)," + NL + //$NON-NLS-1$
        	"  temp_table2 as" + NL + //$NON-NLS-1$
        	"     (select col1, col2 from table2)" + NL + //$NON-NLS-1$
        	"select * from temp_table1, table3;" + NL + //$NON-NLS-1$
        	"", matchInput); //$NON-NLS-1$
   	
       parserVerifySuccess(
        	"with temp_table1 (tcol1, tcol2) as (select * from table1)," + //$NON-NLS-1$
        	"     temp_table2 as (select col1, col2 from table2)" + NL + //$NON-NLS-1$
        	"select tcol1, col1 from temp_table1, temp_table2;" + NL + //$NON-NLS-1$
        	
        	"with temp_table1 (tcol1, tcol2) as (select * from table1)," + //$NON-NLS-1$
        	"     temp_table2 as (select col1, col2 from table2)" + NL + //$NON-NLS-1$
        	"select t1.tcol1, t2.col1 from temp_table1 as t1, temp_table2 as t2;" + NL + //$NON-NLS-1$
        	
        	"", false); // we don't know how colRefs are generated  //$NON-NLS-1$
         
    }

    // fullselect
    public void testSqlDmlParser004_query_combined()  throws Exception {
        System.out.println("test 004_query_combined"); //$NON-NLS-1$
        
        parserVerifySuccess(
        	"select * from t0 union select * from tA;" + NL + //$NON-NLS-1$
            "(select * from t0) union (select * from tA);" + NL + //$NON-NLS-1$
            "(select * from t0) union select * from tA;" + NL + //$NON-NLS-1$
            "(select * from t0 union select * from tA);" + NL + //$NON-NLS-1$
        	"select * from t1 union all select * from tA;" + NL + //$NON-NLS-1$
        	"select * from t2 intersect select * from tA;" + NL + //$NON-NLS-1$
        	"select * from t3 intersect all select * from tA;" + NL + //$NON-NLS-1$
        	"select * from t4 except select * from tA;" + NL + //$NON-NLS-1$
        	"select * from t5 except all select * from tA;" + NL + //$NON-NLS-1$

        	"", matchInput); //$NON-NLS-1$
        	
        parserVerifySuccess(
        	"select col1, col2 from table0" + NL + //$NON-NLS-1$
        	"  where col1 in (select * from t1 union all select * from t2);" + NL + //$NON-NLS-1$
        	
        	"select col1, col2 from table1" + NL + //$NON-NLS-1$
        	"  where col1 = (select * from t1 union all select * from t2);" + NL + //$NON-NLS-1$
        	
        	"select col1, (select * from t1 union all select * from t2) from table2;" + NL + //$NON-NLS-1$
        	
        	//TODO: check for resolving and exposed result columns in QueryCombined
        	
        	"select col1, col2" + NL + //$NON-NLS-1$
        	"  from (select colA as col1, colB as col2 from t1" + NL + //$NON-NLS-1$
        	"        union all" + NL + //$NON-NLS-1$
        	"        select col1, col2 from t2) as query1" + NL + //$NON-NLS-1$
        	"  where col1 > col2;" + NL + //$NON-NLS-1$
        	
        	"select q1.col1 qc1, t1.col1 tc1" + NL + //$NON-NLS-1$
        	"  from (select colA as col1 from t1" + NL + //$NON-NLS-1$
        	"        union all" + NL + //$NON-NLS-1$
        	"        select col1 from t2) as q1," + NL + //$NON-NLS-1$
        	"       table1 as t1" + NL + //$NON-NLS-1$
        	"  where q1.col1 > t1.col1;" + NL + //$NON-NLS-1$
        	
        	"", matchInput); //$NON-NLS-1$
        
        // test nesting and precedence
        parserVerifySuccess(
        	"SELECT DEPTNO FROM DEPARTMENT UNION" + NL + //$NON-NLS-1$
        	"  ( SELECT empno FROM EMP_ACT UNION VALUES ('1212',256) );" + //$NON-NLS-1$
        	"", matchInput); //$NON-NLS-1$
        parserVerifySuccess(
        	"(SELECT DEPTNO FROM DEPT UNION SELECT empno FROM EMP);" + //$NON-NLS-1$
        	"", false); //$NON-NLS-1$
        
        // test nesting of query combined as nested expression:
        //  second result column must be parsed as NESTED expression, where the
        //  expression is a subquery, not as an expression with subquery, where
        //  the nesting is the optional nesting of a combined query
        //  priority in grammar:
        // <expression_factor> ::=?
        //		_LPAREN <expression> _RPAREN
        //   |  <subquery>
        // <query_exp> ::=?
		//      <query_combined>
   		//   |  <query_term>
        // <query_term> ::=
		//      <query_select>
		//   |  <query_values>
		//   |  _LPAREN <query_combined> _RPAREN
        String stmt =
        	"SELECT DEPTNO, ((SELECT EMPNO FROM EMP_ACT UNION VALUES ('1212',256))) as EMPNO" + NL + //$NON-NLS-1$
        	" FROM DEPARTMENT;"; //$NON-NLS-1$
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, matchInput);
        
        QuerySelect select = (QuerySelect)query.getQueryExpr().getQuery();
        ResultColumn resultColumn = (ResultColumn) select.getSelectClause().get(1);
        assertTrue("second result column should have been parsed as nested expression", //$NON-NLS-1$
                        resultColumn.getValueExpr() instanceof ValueExpressionNested );
    }

    // fullselect
    public void testSqlDmlParser004_query_values()  throws Exception {
        System.out.println("test 004_query_values"); //$NON-NLS-1$
        
        parserVerifySuccess(
            "VALUES (1),(2),(3);         -- 3 rows of 1 column" + NL + //$NON-NLS-1$
            "VALUES 1, 2, 3;             -- 3 rows of 1 column" + NL + //$NON-NLS-1$
            "VALUES (1, 2, 3);           -- 1 row of 3 columns" + NL + //$NON-NLS-1$
            "VALUES (1,21),(2,22),(3,23);-- 3 rows of 2 columns" + NL + //$NON-NLS-1$
            "select * from (values (cast(? as integer), cast(? as varchar(10)))) as t1;" + NL +
            "select * from (values (cast(:var1 as integer), cast(:var2 as varchar(10)))) as t1;" + NL +
            "select * from t5 union values (1, 2, 3);" + NL + //$NON-NLS-1$
            "select * from t6 union values 1, 2, 3;" + NL + //$NON-NLS-1$
            "select * from t7 union values (1),(2),(3);" + NL + //$NON-NLS-1$
            "select * from t8 union values (1,21),(2,22),(3,23);" + NL + //$NON-NLS-1$
            "", false); // input "VALUES (1),(2),(3)" will not match generated "VALUES 1, 2, 3"  //$NON-NLS-1$
            
    }

    public void testSqlDmlParser004_updatability()  throws Exception {
        System.out.println("test 004_updatability");
        parserVerifySuccess(
            "select col1 from table0;" + NL +  
            "select col1 from table1 for read only;" + NL + 
            "select col1 from table1 for update;" + NL +
            "select col1 from table1 for update of col1, col2;" + NL +
            "select col1 from table1 where col1 = 1 for update of col1, col2;" + NL +
            "select col1 from table1 order by col1 asc for update of col1, col2;" + NL +
            "", true);
    }
    
    public void testSqlDmlParser004_fetch_first() throws Exception {
        System.out.println("test 004_fetch_first");
        parserVerifySuccess(
            "select col1 from table1;" + NL +
            "select col1 from table1 fetch first row only;" + NL +
            "select col1 from table1 fetch first 5 rows only;" + NL +
            "select col1 from table1 where col1 in (select col2 from table2 fetch first 5 rows only);" + NL +
            "select col1 from table1 where col1 in (select col2 from table2 fetch first 5 rows only) fetch first row only;" + NL +
            "select * from table1 union select * from table2 fetch first 10 rows only;" + NL +
            "(select * from table1) fetch first 10 rows only;" + NL +
            "values (1, 2, 3, 4) fetch first 10 rows only;" + NL +
            "", true);
        
        /* The following will generate SQL slightly different than the input. */
        parserVerifySuccess(
            "select col1 from table1 fetch first 1 row only;" + NL +
            "select col1 from table1 fetch first 1 rows only;" + NL +
            "select col1 from table1 fetch first 5 row only;" + NL +
            "", false);
    }

    public void testSqlDmlParser004_guessing_datatypes()  throws Exception {
        System.out.println("test 004_resolving_datatypes"); //$NON-NLS-1$
        
        parserVerifySuccess(
        	"select * from t0 where :h0 = 'char5';" + NL + //$NON-NLS-1$
        	"select * from t1 where :h1 = 265;" + NL + //$NON-NLS-1$
        	"select * from t2 where :smallInt = -32768 + 32767;" + NL + //$NON-NLS-1$
        	"select * from t3 where :int = -2147483648 + 2147483647;" + NL + //$NON-NLS-1$
        	"select * from t4 where :bigInt = -9223372036854775808 + 9223372036854775807;" + NL + //$NON-NLS-1$
        	"select * from t5 where :real = -3.402E+38 + -1.175E-37;" + NL + //$NON-NLS-1$
        	"select * from t6 where :real = 1.175E-37 + 3.402E+38;" + NL + //$NON-NLS-1$
        	"select * from t7 where :double = -1.79769E+308 + -2.225E-307" + NL + //$NON-NLS-1$
        	"                    or :float = " + //$NON-NLS-1$
        						 new Double("2.225E-307").toString()+ " + " + //$NON-NLS-1$ //$NON-NLS-2$
        						 new Double("1.79769E+308").toString()+ ";" + NL + //$NON-NLS-1$ //$NON-NLS-2$
        	"select * from t8_2 where :decimal = " + //$NON-NLS-1$
        		new BigDecimal("-10.0E+32").toString()+ //$NON-NLS-1$
        		" + " + //$NON-NLS-1$
        		new BigDecimal("10E+30").toString()+ //$NON-NLS-1$
        		";" + NL + //$NON-NLS-1$
        	
        	"", matchInput); //$NON-NLS-1$
    }
 
    public void testSqlDmlParser004_resolving_datatypes()  throws Exception {
        System.out.println("test 004_resolving_datatypes"); //$NON-NLS-1$
        
        parserVerifySuccess(
        	"select * from t0 where :h0 = 'char5';" + NL + //$NON-NLS-1$
        	
        	"", matchInput); //$NON-NLS-1$
        
        QueryStatement stmt = 
            parserVerifySuccessSingleQuery("with temp (col1) as (values 256) select col1 from temp where col1 = (:h1);", matchInput); //$NON-NLS-1$
        List allVars =
            StatementHelper.getAllVariablesInQueryStatement(stmt);
        assertTrue("\"with temp (col1) as (values 256) select col1 from temp where col1 = (:h1);\" must contain at least one host variable",allVars != null); //$NON-NLS-1$
        if (allVars != null && allVars.size() > 0) 
        {
            ValueExpressionVariable var = 
                (ValueExpressionVariable) allVars.get(0);
            DataType dataType = var.getDataType();
            assertTrue("data type of hostVar \":h1\" in nested expression was not resolved", dataType != null); //$NON-NLS-1$
        }
    }
    
 
    /** TODO: open issue, remove ParserManager.preprocessStatement()
     * and this test case shouldn't but will fail */
    public void testSqlDmlParser000_no_ending_new_line_after_comment()  throws Exception {
        System.out.println("test 000_no_ending_new_line_after_comment"); //$NON-NLS-1$
        parserVerifySuccessSingleQuery("select * from tableA;  --will not raise BadParseException because of newLineChar after comment here" + NL, false ); //$NON-NLS-1$
        parserVerifySuccessSingleQuery("select * from tableB;  --will raise BadParseException because of missing newLineChar after comment here?", false); //$NON-NLS-1$
    }
    
    
    /** TODO: open issue, remove ParserManager.preprocessStatement() */
    public void testSqlDmlParser000_no_ending_semicolon()  throws Exception {
        System.out.println("test 000_no_ending_semicolon"); //$NON-NLS-1$
        parserVerifySuccess(
            "UPDATE dept SET deptname = 'aaa' WHERE admrdept IS NOT NULL;" + NL + //$NON-NLS-1$
	        "UPDATE dept SET deptname = 'aaa' WHERE admrdept IS NOT NULL", matchInput); //$NON-NLS-1$
        // Token kind of NULL is lexed as TK_REGULAR_IDENTIFIER (parsersym)
        // instead of TK_NULL
    }

    /** TODO: open issue, remove ParserManager.preprocessStatement()
     * and this test case shouldn't but will fail */
    public void testSqlDmlParser000_no_ending_semicolon_single_stmt()  throws Exception {
        System.out.println("test 000_no_ending_semicolon_single_stmt"); //$NON-NLS-1$
        parserVerifySuccessSingleQuery(
            "UPDATE dept SET deptname = 'aaa' WHERE admrdept IS NOT NULL", matchInput); //$NON-NLS-1$
        
        parserVerifySuccessSingleQuery("select * from A join B on A.c1=B.c1", matchInput); //$NON-NLS-1$
        // Token kind of NULL is lexed as TK_REGULAR_IDENTIFIER (parsersym)
        // instead of TK_NULL
    }

    
    

//    public void testSqlDmlParser000_empty()  throws Exception {
//        System.out.println("test 004_empty"); //$NON-NLS-1$
//        parserVerifyError(""); //$NON-NLS-1$
//    }

//    public void testSqlDmlParser000_null()  throws Exception {
//        System.out.println("test 005_null"); //$NON-NLS-1$
//        try {
//            parserVerifyError(null);
//        } catch (Exception e) {
//            // TODO: handle exception
//            System.err.println(e.getClass().getName()+" at "+getClass().getName()+".testSqlDmlParser005_null()"); //$NON-NLS-1$ //$NON-NLS-2$
//            e.printStackTrace();
//        }
//    }

    public void testSqlDmlParser000_invalidCharacters() throws Exception {
        System.out.println("test 000_invalidCharacters");
        // Note the select expression in the next statement is Data Warehouse host variable syntax
        parserVerifyError("select ${aaa/bbb} from temp");
        parserVerifyError("select aaa`bbb from temp");
    }
    
    public void testSqlDmlParser000_delimitedIdentifier_newLine()  throws Exception {
        System.out.println("test 000_delimitedIdentifier_newLine"); //$NON-NLS-1$
        parserVerifySuccess("select * from test.\"mytablename\nisverylong\";", true); //$NON-NLS-1$
    }

    public void testSqlDmlParser000_delimitedIdentifier_whiteSpace()  throws Exception {
        System.out.println("test 000_delimitedIdentifier_whiteSpace"); //$NON-NLS-1$
        parserVerifySuccess("select * from test.\"mytablename with spaces\";", true); //$NON-NLS-1$
        parserVerifySuccess("select * from test.\"mytablename with \ttabs\";", true); //$NON-NLS-1$
    }

    public void testSqlDmlParser000_stmtTerminatorInString()  throws Exception {
        System.out.println("test 000_stmtTerminator"); //$NON-NLS-1$
        parserVerifySuccess("insert into dep values ';',3;", false); //$NON-NLS-1$
    }

    public void testSqlDmlParser000_stmtTerminatorInComment()  throws Exception {
        System.out.println("test 000_stmtTerminatorInComment"); //$NON-NLS-1$
        parserVerifySuccess(
            "insert into dep --values 2;\n " + //$NON-NLS-1$
            	"values 3;", false //$NON-NLS-1$
            );
    }

    public void testSqlDmlParser000_lexicalErrors()  throws Exception {
        System.out.println("test 000_lexicalErrors"); //$NON-NLS-1$
        getSourceFormat().setHostVariablePrefix(':');
        parserVerifyErrorSingle("select !colA from tab1;");  //$NON-NLS-1$
    }

    public void testSqlDmlParser004_remove_resultCol()  throws Exception {
        System.out.println("test 004_remove_resultCol"); //$NON-NLS-1$
        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(
                    "WITH table1 (col1,col2) AS (VALUES ('a String', 256))" + //$NON-NLS-1$
                    "SELECT t1.col1 as c1, t2.col2" +                         //$NON-NLS-1$
                    " FROM table1 t1, table1 t2" +                            //$NON-NLS-1$
                    " ORDER BY c1, t2.col2;", false);                         //$NON-NLS-1$

        QueryExpressionRoot qryExprRt = query.getQueryExpr();
        QueryExpressionBody qExprBody = qryExprRt.getQuery();
        if (qExprBody instanceof QuerySelect) {
            QuerySelect qSelect = (QuerySelect) qExprBody;

            System.out.println("\n\nbefore:\n"+query.getSQL());
            
            // get the result columns of the query/ select columns
            List qSelList = qSelect.getSelectClause();
            for (Iterator rsltIt = qSelList.iterator(); rsltIt.hasNext();) {
                QueryResultSpecification rsltSpec =
                    (QueryResultSpecification) rsltIt.next();
                
                if (rsltSpec instanceof ResultColumn) {
                    ResultColumn resCol = (ResultColumn) rsltSpec;
                    QueryValueExpression resExpr = resCol.getValueExpr();
                    
                    if (resExpr instanceof ValueExpressionColumn) {
                        ValueExpressionColumn col = (ValueExpressionColumn) resExpr;
                        
                        
                        if ("COL1".equals(col.getName())) {
                            
                            // remove the result column
                            rsltIt.remove();
                            
                            // remove the column from the referenced column list of the table
                            col.setTableExpr(null);
                            
                            // remove the occurences in the ORDER BY clause
                            List ordByColList = query.getOrderByClause();
                            for (Iterator orderByIt = ordByColList.iterator(); orderByIt.hasNext();) {
                                OrderBySpecification orderBySpec = (OrderBySpecification) orderByIt.next();
                                
                                if (orderBySpec instanceof OrderByResultColumn) {
                                    OrderByResultColumn orderByResCol = (OrderByResultColumn) orderBySpec;
                                    if (orderByResCol.getResultCol() == resCol) {
                                        orderByIt.remove();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            String sqlAfter = query.getSQL();
            assertFalse("col1 not properly removed", sqlAfter.toUpperCase().indexOf("T1.COL1") > -1);
            assertFalse("ORDER BY c1 not properly removed", sqlAfter.toUpperCase().indexOf("C1") > -1);
            System.out.println("after:\n"+sqlAfter+"\n");

        }

    }
    
    public void testSqlDmlParser004_add_resultCol()  throws Exception {
        System.out.println("test 004_add_resultCol"); //$NON-NLS-1$
        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(
                    "WITH table1 (col1,col2) AS (VALUES ('a String', 256))" + //$NON-NLS-1$
                    "SELECT t2.col2" +                                        //$NON-NLS-1$
                    " FROM table1 t1, table1 t2" +                            //$NON-NLS-1$
                    " ORDER BY t2.col2;", false);                             //$NON-NLS-1$

        QueryExpressionRoot qryExprRt = query.getQueryExpr();
        QueryExpressionBody qExprBody = qryExprRt.getQuery();
        if (qExprBody instanceof QuerySelect) {
            QuerySelect qSelect = (QuerySelect) qExprBody;

            System.out.println("\n\nbefore:\n"+query.getSQL());
            
            SQLQueryModelFactory sqlQueryFactory = SQLQueryModelFactory.eINSTANCE;
           
            // get the column we want to add to the SELECT clause from the table in the FROM clause
            // say we know it's the first table in the list and it's not of type TableInDatabase here!
            // we pick the first column in the first table (random choice here)
            // important: due to EMF ownership between table and it's "exposed" columns,
            //     we have to create a copy of the table's column! copy DataType, too
            List fromTables = qSelect.getFromClause();
            TableExpression table = (TableExpression) fromTables.get(0);
            ValueExpressionColumn tableCol = (ValueExpressionColumn) table.getColumnList().get(0);
            ValueExpressionColumn selectCol = sqlQueryFactory.createValueExpressionColumn();
            selectCol.setName(tableCol.getName());

            DataType tableColType = tableCol.getDataType();
            DataType selectColType = (DataType) EcoreUtil.copy(tableColType);
            selectCol.setDataType(selectColType);
 
            // add column to tables list of referencing columns, so that the column can be
            // qualified in case of ambiguous columns in the select statement
            selectCol.setTableExpr(table);
  
            // create a new ResultColumn and add the created column, give it alias "c1"
            ResultColumn resCol = sqlQueryFactory.createResultColumn();
            resCol.setValueExpr(selectCol);
            resCol.setName("C1");

            // add the column to the list of result columns of the query, say at first position
            qSelect.getSelectClause().add(0, resCol);
            
            
            // we want to add the column to the ORDER BY clause as well
            OrderByResultColumn orderByResCol = sqlQueryFactory.createOrderByResultColumn();
            orderByResCol.setResultCol(resCol);

            query.getOrderByClause().add(0,orderByResCol);
            
            
            String sqlAfter = query.getSQL();
            assertTrue("col1 not properly added", sqlAfter.toUpperCase().indexOf("T1.COL1") > -1);
            assertTrue("ORDER BY C1 not properly added", sqlAfter.toUpperCase().indexOf("C1") > -1);
            System.out.println("after:\n"+sqlAfter+"\n\n");

        }

    }
    
    public void testGenerateAsKeywordOption() throws Exception {
        System.out.println("test generateAsKeywordOption");
        String sourceSQL = 
            "with withTable (col1, col2) as (values (256, 255)) " + NL +
            "select *                                           " + NL +
            "  from table1 t1, (table2 n1), ((table2 n2)),      " + NL + 
            "       tbl3 t3 join tbl4 on t3.c1 = t4.c1,         " + NL + 
            "       withTable as wt,                            " + NL + 
            "       (select * from tbl5, tbl6) as nq1;";
        QuerySelectStatement query =(QuerySelectStatement) parserVerifySuccessSingleQuery(sourceSQL, true);
        
        setGenerateAsKeywordForTableCorrID(false);
        query =(QuerySelectStatement) parserVerifySuccessSingleQuery(sourceSQL, true);
 
        String genSQL = query.getSQL();
        
        String templateSQL = 
            "WITH WITHTABLE (COL1, COL2) AS (VALUES (256, 255)) " + NL +
            "SELECT *                                           " + NL +
            "  FROM TABLE1 T1, (TABLE2 N1), ((TABLE2 N2)),      " + NL + 
            "       TBL3 T3 JOIN TBL4 ON T3.C1 = T4.C1,         " + NL + 
            "       WITHTABLE WT,                               " + NL + 
            "       (SELECT * FROM TBL5, TBL6) NQ1;";
        
        compareGeneratedSQLToTemplateSQL(genSQL, templateSQL, true, false);
    }

}
