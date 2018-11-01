/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.query.test;

import java.util.List;

import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntegerDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;

/**
 * @author ckadner
 *
 */
public final class TestSQLQueryParserColumnTableReferences extends
                AbstractTestSQLQueryParser
{

    /**
     * @param name
     */
    public TestSQLQueryParserColumnTableReferences(String name)
    {
        super(name);
    }

    
    
    
    public void testSqlDmlParser004_resolve_ColumnReferences() throws Exception {
        System.out.println("test 004_resolve_ColumnReferences"); //$NON-NLS-1$
//        String with = "with\n"; //$NON-NLS-1$
//        String withTable1 = " table1 (col1,col2) as (values (1,2))"; //$NON-NLS-1$
        getSourceFormat().setOmitSchema("s1".toUpperCase()); //$NON-NLS-1$
        getParserManager().setSourceFormat(getSourceFormat());
        
        List result = parserVerifySuccess(
               /*  0 */ " select t1.c0    from t1,    s2.t1;   " + NL + //$NON-NLS-1$
               /*  1 */ " select t1.c1    from s1.t1, s2.t1;   " + NL + //$NON-NLS-1$
               /*  2 */ " select s1.t1.c2 from t1,    s2.t1;   " + NL + //$NON-NLS-1$
               /*  3 */ " select s2.t1.c3 from t1,    s2.t1;   " + NL + //$NON-NLS-1$
               /*  4 */ " select t1.c4    from t1,    s2.t1 t1;" + NL + //$NON-NLS-1$
               
               /*  5 */ " select t1.c5    from t1 t2, s2.t1;   " + NL + //$NON-NLS-1$
               /*  6 */ " select s1.t1.c6 from t1 t2, s2.t1;   " + NL + //$NON-NLS-1$
               /*  7 */ " select s2.t1.c7 from t1 t1, s2.t1 t2;" + NL + //$NON-NLS-1$
               /*  8 */ " select s2.t2.c8 from t1,    s2.t1 t2;" + NL + //$NON-NLS-1$
               /*  9 */ " select s2.t2.c9 from s2.t1, s2.t1 t2;" + NL + //$NON-NLS-1$
                        "", false); //$NON-NLS-1$
        
        assertTrue( columnRefTable(result, 0, "t1      ")  ); //$NON-NLS-1$
        assertTrue( columnRefTable(result, 1, "s1.t1   ")  ); //$NON-NLS-1$
        assertTrue( columnRefTable(result, 2, "t1      ")  ); //$NON-NLS-1$
        assertTrue( columnRefTable(result, 3, "s2.t1   ")  ); //$NON-NLS-1$
        assertTrue( columnRefTable(result, 4, "s2.t1 t1")  ); //$NON-NLS-1$
        
        assertTrue( columnRefTable(result, 5, "s2.t1   ")  ); //$NON-NLS-1$
        //assertTrue( columnRefTable(result, 5, null      )  );
        //assertTrue( columnRefTable(result, 6, "t1 t2   ")  );
        assertTrue( columnRefTable(result, 6, null      )  );
        //assertTrue( columnRefTable(result, 7, "s2.t1 t2")  );
        assertTrue( columnRefTable(result, 7, null      )  );
        assertTrue( columnRefTable(result, 8, null      )  );
        assertTrue( columnRefTable(result, 8, null      )  );
        //assertTrue( columnRefTable(result, 9, "t1      ")  );
        //assertTrue( columnRefTable(result,10, "t1      ")  );
        
        getSourceFormat().setOmitSchema(null);
        getParserManager().setSourceFormat(getSourceFormat());
    }





    public void testSqlDmlParser004_resolvedColumnsAndTables() throws Exception, SQLParserException {
        System.out.println("test 004_resolvedColumnsAndTables"); //$NON-NLS-1$
                    
        
        // TODO: implement test for parser resolve of tables and columns
        
        // consider ONLY result columns in subqueries: 
        List stmtList = parserVerifySuccess(
            "select col1 from table1, (select col1         from table0) query0;" + NL +  //$NON-NLS-1$
            "select col1 from table1, (select col2 as col1 from table0) query1;" + NL +  //$NON-NLS-1$
            "select col1 from table1, (select col1 as col2 from table0) query2;" + NL +  //$NON-NLS-1$
            "", false); //$NON-NLS-1$
        assertTrue( columnRefTable(stmtList, 0, "query0")  ); //$NON-NLS-1$
        assertTrue( columnRefTable(stmtList, 1, "query1")  ); //$NON-NLS-1$
        assertTrue( columnRefTable(stmtList, 2, null)  );

        
        
        
        parserVerifySuccess(
            "select table1.col1 from table1, (select col1         from table0) as query0;" + NL +  //$NON-NLS-1$
            "select query1.col1 from table1, (select col2 as col1 from table0) as query1;" + NL +  //$NON-NLS-1$
            "select col1        from table1, (select col1 as col2 from table0) as query2;" + NL +  //$NON-NLS-1$
            "select col1, col2  from table1, (select col1 as col2 from table0) as query2;" + NL +  //$NON-NLS-1$
            "select table1.col1, query1.col1 from table1, (select col2 as col1 from table0) as query1;" + NL +  //$NON-NLS-1$
            "", false); // we don't know how column references will be generated //$NON-NLS-1$

        getSourceFormat().setOmitSchema(null);
        getParserManager().setSourceFormat(getSourceFormat());
        // TODO: consider ONLY result columns in subqueries: 
        //   e.g.: select col1 from table1, (select col1 as col2 from table0) as query2;
        //         col1 in outer query is therefore not to be associated with table0.col1!
        QueryStatement query = parserVerifySuccessSingleQuery(
            "select col1 from table1, (select col1 as col2 from table0) as query0;", matchInput); //$NON-NLS-1$
        String columnName = "COL1"; //$NON-NLS-1$
        String tableName = "TABLE0"; //$NON-NLS-1$
        QuerySelect select = (QuerySelect)((QuerySelectStatement)query).getQueryExpr().getQuery();
        ResultColumn resultCol = StatementHelper.findResultColumnForColumnNameOrAlias(select, columnName);
        ValueExpressionColumn columnExpr = (ValueExpressionColumn) resultCol.getValueExpr();
        //String colsTableName = columnExpr.getTableExpr().getName();
        
        assertNotNull("SelectStatement null", select); //$NON-NLS-1$
        assertNotNull("ResultColumn null", resultCol); //$NON-NLS-1$
        assertNotNull("ValueExpressionColumn null", columnExpr); //$NON-NLS-1$
        
        assertTrue("columns are only to be associated to its tableExpression in the containing query. result table column col1 should not be associated to table0",  //$NON-NLS-1$
                        columnExpr.getTableExpr() == null || !tableName.equals(columnExpr.getTableExpr().getName()));
        assertTrue("columns are only to be associated to its tableExpression in the containing query. result table column col1 should not be associated any table or nested query",  //$NON-NLS-1$
                        columnExpr.getTableExpr() == null);
        
        
        
        parserVerifySuccess(
            "select table1.* from table1;", matchInput); //$NON-NLS-1$
        
        
        QueryStatement stmt = parserVerifySuccessSingleQuery(
            "with" + NL + //$NON-NLS-1$
            "  t1 (col1) as (values (1))" + NL + //$NON-NLS-1$
            "select s2.t1.col1" + NL + //$NON-NLS-1$
            "  from t1, s2.t1" + NL + //$NON-NLS-1$
            "  where t1.col1 = s2.t1.col1;", false); //$NON-NLS-1$
        String sqlSrc = stmt.getSQL().toLowerCase();
        assertTrue("SQL for column reference s2.t1.col1 not correctly generated or incorrectly resolved", //$NON-NLS-1$
                        sqlSrc.indexOf("s2.t1.col1") > -1  //$NON-NLS-1$
                        && sqlSrc.indexOf(" t1.col1") > -1); //$NON-NLS-1$
        
        stmt = parserVerifySuccessSingleQuery(
            "with" + NL + //$NON-NLS-1$
            "  t1 (col1) as (values 1)" + NL + //$NON-NLS-1$
            "select s2.t1.col1" + NL + //$NON-NLS-1$
            "  from t1 t3, s2.t1" + NL + //$NON-NLS-1$
            "  where t3.col1 = s2.t1.col1;", matchInput); //$NON-NLS-1$
        sqlSrc = stmt.getSQL().toLowerCase();
        assertTrue("SQL for column reference s2.t1.col1 not correctly generated", //$NON-NLS-1$
                        sqlSrc.indexOf("s2.t1.col1") > -1 //$NON-NLS-1$
                        && sqlSrc.indexOf(" t3.col1") > -1 // would only work with db connected //$NON-NLS-1$
                        );
        
        
        
        
        // compare the results of both queries, are columns associated to right 
        // table/table alias,  difference: 1st qry - select from both table1
        parserVerifySuccess(
        	"select t1.col1, (t2.col1+t1.col2) as col2 " + //$NON-NLS-1$
        	"  from table1 as t1, TABLE1 as t2" + //$NON-NLS-1$
        	"  where t1.col1 = t2.col1-t1.col2" + //$NON-NLS-1$
        	"  order by t1.col1, col2;" //$NON-NLS-1$
        	+
        	"select t1.col1, (t2.col1+t1.col2) as col2 " + //$NON-NLS-1$
        	"  from table1 as t1, TABLE2 as t2" + //$NON-NLS-1$
        	"  where t1.col1 = t2.col1-t1.col2" + //$NON-NLS-1$
        	"  order by t1.col1, col2;", matchInput //$NON-NLS-1$
        	);

        // consider same table name in different schema
        
        // TODO:test resolving - consider columns in joinConditions
        // TODO:test resolving - query combined
        parserVerifySuccess(
            "select * from table1" + " where col0 = 12;" + NL + //$NON-NLS-1$ //$NON-NLS-2$
        	"", matchInput); //$NON-NLS-1$
        
        // where-clause col refs can't be resolved, are not allowed actually!
        stmtList = parserVerifySuccess(
            "select t1.col1 as c1, t2.col1 as c2" + NL + //$NON-NLS-1$
        	"  from table1 as t1, table2 as t2" + NL + //$NON-NLS-1$
        	"  where c1 > c2;" + NL + //$NON-NLS-1$
        	"", matchInput); //$NON-NLS-1$
        String msg = "column references in where clause can't refer to result column names"; //$NON-NLS-1$
        assertTrue(msg, columnRefTable(stmtList, 0, "c1", null)); //$NON-NLS-1$
        assertTrue(msg, columnRefTable(stmtList, 0, "c2", null)); //$NON-NLS-1$
        
        
        
        
        parserVerifySuccess(
        	"select t1.col1 as c1, t2.col1 as c2" + NL + //$NON-NLS-1$
        	"  from table1 as t1, table2 as t2" + NL + //$NON-NLS-1$
        	"  where t1.col1 > t2.col1;" + NL + //$NON-NLS-1$
        	"", matchInput); //$NON-NLS-1$
        
        
        parserVerifySuccess(
                "select * from (values ('string',256)) as v;" + NL + //$NON-NLS-1$
                "with temp_tab (colA,colB) as " + NL + //$NON-NLS-1$
                "    (select v.* from (values ('string', 256)) as v)" + NL + //$NON-NLS-1$
        		"select * from temp_tab;", matchInput); //$NON-NLS-1$

        parserVerifySuccess(
                        "select v._1, v._2 from (values ('string',256)) as v;" + NL + //$NON-NLS-1$
                		"", false); // we might get unqualifie colRefs //$NON-NLS-1$
        
        parserVerifySuccess(
            "with temp_tab as (select v.* from (values ('string', 256)) as v)" + NL + //$NON-NLS-1$
            "select * from temp_tab;" + NL + //$NON-NLS-1$
            "with temp_tab (colA,colB) as (select v.* from (values ('string', 256)) as v)" + NL + //$NON-NLS-1$
            "select * from temp_tab;" + NL + //$NON-NLS-1$
            "with temp_tab (colA,colB) as (select v.* from (values ('string', 256)) as v)" + NL + //$NON-NLS-1$
            "select w.*  from temp_tab w;" + NL + //$NON-NLS-1$
            "with temp_tab (colA,colB) as (select v.* from (values ('string', 256)) as v)" + NL + //$NON-NLS-1$
            "select w.colA, w.colB-1 from temp_tab w;" + NL + //$NON-NLS-1$
            "with temp_tab (colA,colB) as (select v.* from (values ('string', 256)) as v)" + NL + //$NON-NLS-1$
            "select w.colB-1, w.colA from temp_tab w;" + NL + //$NON-NLS-1$
            "with temp_tab (colA,colB) as (select v.* from (values ('string', 256)) as v)" + NL + //$NON-NLS-1$
            "select w.colA, w.colB-1 as B from temp_tab w;", false); // the colRefs will be generated without the table qualifier cause we have only one table //$NON-NLS-1$
			/*
			with temp_tab as (select v.* from (values ('string', 256)) as v)
			 select * from temp_tab;
			with temp_tab (colA,colB) as (select v.* from (values ('string', 256)) as v)
			 select * from temp_tab;
			with temp_tab (colA,colB) as (select v.* from (values ('string', 256)) as v)
			 select w.*  from temp_tab w; 
			with temp_tab (colA,colB) as (select v.* from (values ('string', 256)) as v)
			 select w.colA, w.colB-1 from temp_tab w; 
			with temp_tab (colA,colB) as (select v.* from (values ('string', 256)) as v)
			 select w.colA, w.colB-1 as B from temp_tab w; 
			with temp_tab (colA,colB) as (select v.* from (values ('string', 256)) as v)
			 select w.colB-1, w.colA from temp_tab w; 
			*/
        String input = 
            "with temp_1 (col1,col2) as (values (255,'string'))," + NL + //$NON-NLS-1$
            "     temp_2 (col3) as " + NL + //$NON-NLS-1$
            "        (select v.* from (values (23423412)) v)" + NL + //$NON-NLS-1$
            "  select * from temp_1 t1, table3, temp_2 t2 where col3 = col1;"; //$NON-NLS-1$
        QuerySelectStatement withQuery = (QuerySelectStatement) parserVerifySuccessSingle(
                        input ,false);
        List resultCols = StatementHelper.getEffectiveResultColumns(withQuery);
        assertTrue("the resolved stmt should have 3 effective result columns: " //$NON-NLS-1$
                        +input,
                        resultCols != null && resultCols.size()==3);
        ValueExpressionColumn col1 = (ValueExpressionColumn) resultCols.get(0);
        ValueExpressionColumn col2 = (ValueExpressionColumn) resultCols.get(1);
        ValueExpressionColumn col3 = (ValueExpressionColumn) resultCols.get(2);
        
        assertTrue( "the 1st effective result column should be \"COL1\"" + //$NON-NLS-1$
	        		" of data type \"SMALLINT\": "+input, //$NON-NLS-1$
	        		col1.getName().equalsIgnoreCase("COL1") //$NON-NLS-1$
	        		&& col1.getDataType() instanceof IntegerDataType
	        		&& ((IntegerDataType)col1.getDataType()).getPrimitiveType() == PrimitiveType.SMALLINT_LITERAL);    
        assertTrue( "the 2nd effective result column should be \"COL2\"" + //$NON-NLS-1$
	        		" of data type \"CHARACTER\": "+input, //$NON-NLS-1$
	        		col2.getName().equalsIgnoreCase("COL2") //$NON-NLS-1$
	        		&& col2.getDataType() instanceof CharacterStringDataType
	        		&& ((CharacterStringDataType)col2.getDataType()).getPrimitiveType() == PrimitiveType.CHARACTER_LITERAL);    
        assertTrue( "the 3rd effective result column should be \"COL3\"" + //$NON-NLS-1$
            		" of data type \"INTEGER\": "+input, //$NON-NLS-1$
            		col3.getName().equalsIgnoreCase("COL3") //$NON-NLS-1$
            		&& col3.getDataType() instanceof IntegerDataType
            		&& ((IntegerDataType)col3.getDataType()).getPrimitiveType() == PrimitiveType.INTEGER_LITERAL);    
        
        
        // check for resolving in context of "parent"-select
        // test resolving WithTableReference resolving
        input = 
            "with temp1 (col1) as (values 256)," + //$NON-NLS-1$
            "     temp2 (col2) as (values 'string')" + NL + //$NON-NLS-1$
            "select * from temp1 t1" + NL + //$NON-NLS-1$
            "  where exists (" + //$NON-NLS-1$
            "           select * from temp2 t2 where t1.col1 = t2.col2);"; //$NON-NLS-1$
        withQuery = (QuerySelectStatement) parserVerifySuccessSingleQuery(input, false);
        
        assertTrue( "column reference \"t1.col1\" should be resolved", //$NON-NLS-1$
                        columnRefTable(withQuery, "t1.col1", "t1") ); //$NON-NLS-1$ //$NON-NLS-2$
        
        // check that the column in the sub-query doesn't get qualified by the sub-query correlation name.
        input = "SELECT * FROM (SELECT COLA FROM T1, T2) QUERY1";
        parserVerifySuccess(input, true);
        
    }

    
    
    
       
    // with t1 ("Col""1""") as (values (256)), t2 ("CO L1") as (values ('string')), t3 ("1COL") as (values (4234234234.45)) select "Col""1""", "CO L1", "1COL" from t1,t2,t3;
    public void testSqlDmlParser004_resolvedColumns_delimitedIdentifiers() throws Exception, SQLParserException {
       System.out.println("test 004_resolvedColumns_delimitedIdentifiers"); //$NON-NLS-1$
                   
       String input =
           "with t1 (\"Col\"\"1\"\"\") as (values (256))," + NL +  //$NON-NLS-1$
           "     t2 (\"CO L1\") as (values ('string'))," + NL +  //$NON-NLS-1$
           "     t3 (\"1COL\") as (values (4234234234.45))," + NL +  //$NON-NLS-1$
           "     t4 (col1) as (values (32000000))" + NL +  //$NON-NLS-1$
           "select \"Col\"\"1\"\"\", \"CO L1\", \"1COL\", Col1 from t1, t2, t3, t4;" + NL +  //$NON-NLS-1$
           ""; //$NON-NLS-1$

       // don't match input as after resolving columns will be table qualified
       QuerySelectStatement result =
           (QuerySelectStatement) parserVerifySuccessSingleQuery(input, false);

       assertTrue( columnRefTable(result, "\"Col\"\"1\"\"\"", "t1") ); //$NON-NLS-1$  //$NON-NLS-2$
       assertTrue( columnRefTable(result, "\"CO L1\"", "t2") ); //$NON-NLS-1$  //$NON-NLS-2$
       assertTrue( columnRefTable(result, "\"1COL\"", "t3") ); //$NON-NLS-1$  //$NON-NLS-2$
       assertTrue( columnRefTable(result, "COL1", "t4") ); //$NON-NLS-1$  //$NON-NLS-2$
       
       String sqlSrc = result.getSQL();
//     assertTrue("SQL for column reference not correctly generated or incorrectly resolved", //$NON-NLS-1$
//     sqlSrc.indexOf("T1.\"Col\"\"1\"\"\"") > -1  //$NON-NLS-1$
//     && sqlSrc.indexOf("T2.\"CO L1\"") > -1  //$NON-NLS-1$
//     && sqlSrc.indexOf("T3.\"1COL\"") > -1  //$NON-NLS-1$
//     && sqlSrc.indexOf("T4.COL1") > -1); //$NON-NLS-1$
//    
    }


    
    public void testSqlDmlParser004_resolve_WITH_table_references() throws Exception {
        System.out.println("test 004_resolve_WITH_table_references"); //$NON-NLS-1$
        String withTableClause = "with" + NL +
            "   t1 (t1c1, t1c2) as (values (11, 12)),   " + NL +
            "   t2 (t2c1, t2c2) as (values (21, 22)),   " + NL +
            "   t3 (t3c1, t3c2) as (values (31, 32))    " + NL;
        
        String stmt = withTableClause +
            "select *                               " + NL +
            "  from t1, t2 x                        " + NL +
            "  where t1c1 = t2c1                    " + NL +
            "    and t1c1 in ( select t2c1          " + NL +
            "                    from t2 y, t3      " + NL +
            "                    where t2c2 > t3c1);";
        QuerySelectStatement result =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, false);
        String resultSQL = result.getSQL();
        
        assertTrue(
                msgColTableNotResolved("T2C2","T2 as Y"),
                resultSQL.indexOf("Y.T2C2") > -1 );
        
        stmt = withTableClause +
            "select *                                                   " + NL +
            "  from t1 a, t2 x                                          " + NL +
            "  where t1c1 = t2c1                                        " + NL +
            "    and t1c1 in ( select t2c1                              " + NL +
            "                    from t2 y, t3 b                        " + NL +
            "                    where t2c2 > t3c1                      " + NL +
            "                      and t3c1 in ( select t3c1            " + NL +
            "                                      from t1 z, t3 c      " + NL +
            "                                      where t1c1 > t3c1) );";
        result =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, false);
        resultSQL = result.getSQL();
        
        assertTrue(
                msgColTableNotResolved("T2C2","T2 as Y") +
                msgColTableNotResolved("T3C1","T3 as B"),
                resultSQL.indexOf("Y.T2C2 > B.T3C1") > -1 );

        assertTrue(
                msgColTableNotResolved("T1C1","T1 as Z") +
                msgColTableNotResolved("T3C1","T3 as C"),
                resultSQL.indexOf("Z.T1C1 > C.T3C1") > -1 );

        
        stmt = withTableClause +
            "select *                                                   " + NL +
            "  from t1 a, t2 x                                          " + NL +
            "  where t1c1 = t2c1                                        " + NL +
            "    and t1c1 in ( select t2c1                              " + NL +
            "                    from t2 y, t3 b                        " + NL +
            "                    where t2c2 > t1c1                      " + NL +
            "                      and t3c1 in ( select t3c1            " + NL +
            "                                      from t1 z, t3 c      " + NL +
            "                                      where t1c1 > t3c1    " + NL +
            "                                        and t1c2 > t2c2 ) );";
        result =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, false);
        resultSQL = result.getSQL();
        
        assertTrue(
                msgColTableNotResolved("T1C2","T1 as Z") +
                msgColTableNotResolved("T2C2","T2 as Y"),
                resultSQL.indexOf("Z.T1C2 > Y.T2C2") > -1 );
        
    }

    public void testSqlDmlParser004_columnQualification_inContext() throws Exception {
        System.out.println("test 004_columnQualification_inContext"); //$NON-NLS-1$
        
        String withClause =
            "with temp1 (col1) as (values 256),                              " + NL +
            "     temp2 (col2) as (values 'string'),                         " + NL +
            "     temp3 (col3) as (values 256),                              " + NL +
            "     temp4 (col4) as (values 'string')                          ";

        String stmt = withClause + NL +
            "select col1                                                     " + NL +
            "  from temp1 t1                                                 " + NL +
            "  where exists (select * from temp2 t2 where t1.col1 = t2.col1);";
        QueryStatement result =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, false);


        stmt = withClause + NL +
            "select col1                                                     " + NL +
            "  from temp1 t1                                                 " + NL +
            "  where exists (select col2 from temp2 t2);                     ";
        result =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, false);


        stmt = withClause + NL +
            "select col1                                                     " + NL +
            "  from temp1 t1                                                 " + NL +
            "  where exists (select * from temp2 t2 where col1 = col2);      ";
        result =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, false);


        stmt = withClause + NL +
            "select col1                                                     " + NL +
            "  from temp1 t1                                                 " + NL +
            "  where exists (select * from temp2 t2 where t1.col1 = t2.col2);";
        result =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, false);


        stmt = withClause + NL +
            "select col1, col3                                               " + NL +
            "  from temp1 t1, temp3 t3                                       " + NL +
            "  where exists (select * from temp2 t2 where t1.col1 = t2.col2);";
        result =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, false);


        stmt = withClause + NL +
            "select col1                                                     " + NL +
            "  from temp1 t1                                                 " + NL +
            "  where exists (select * from temp2 t2, temp4 t4                " + NL +
            "                  where t2.col2 = t4.col4);                     ";            
        result =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, false);

        
        // trying to check if visibility of tables is correct
        // if it doesn't work than we will get qualified columns because the
        // column names will be considered ambiguous and then the input doesn 
        // match the output
        boolean matchInput = true;
        withClause =
            "with t1 (colA, colB) as (values (256,'string')),                " + NL +
            "     t2 (col1, col2) as (values (256,'string'))                ";
        stmt = withClause + NL +
            "select col1, col2                                               " + NL + // $NON-NLS-1$
            "     from (select colA as col1, colB as col2 from t1            " + NL + // $NON-NLS-1$
            "           union all                                            " + NL + // $NON-NLS-1$
            "           select col1, col2 from t2) as query1                 " + NL + // $NON-NLS-1$
            "     where col1 > col2                                          "; // $NON-NLS-1$
        result =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, matchInput);

        stmt = withClause + NL +
            "select col1, col2                                               " + NL + // $NON-NLS-1$
            "     from (select col1, col2 from t2) as query1                 " + NL + // $NON-NLS-1$
            "     where col1 > col2                                          "; // $NON-NLS-1$
        result =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, matchInput);


    }

    public void testSqlDmlParser004_columnQualification_specified()  throws Exception {
        System.out.println("test 004_columnQualification_specified"); //$NON-NLS-1$

        getSourceFormat().setOmitSchema("S1");
       
        String stmt = "select t1.c1, s2.t1.c2, t3.c1 from t1, s2.t1, s3.t1 as t3;";
        QueryStatement result =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, matchInput);
        String resultSchemaQualified = StatementHelper.getSQLSchemaQualified(result);
        String resultTableQualified = StatementHelper.getSQLTableQualified(result);
        String resultUnqualified = StatementHelper.getSQLUnqualified(result);
        
        assertTrue("column references should be schema qualified",
                resultSchemaQualified.indexOf("SELECT S1.T1.C1, S2.T1.C2, T3.C1") > -1);
        assertTrue("table references should be schema qualified",
                resultSchemaQualified.indexOf("FROM S1.T1, S2.T1, S3.T1 AS T3") > -1);
        
        assertTrue("column references should be table qualified",
                resultTableQualified.indexOf("SELECT T1.C1, T1.C2, T3.C1") > -1);
        assertTrue("table references should be unqualified",
                resultTableQualified.indexOf("FROM T1, T1, T1 AS T3") > -1);

        assertTrue("column references should be unqualified",
                resultUnqualified.indexOf("SELECT C1, C2, C1") > -1);
        assertTrue("table references should be unqualified",
                resultUnqualified.indexOf("FROM T1, T1, T1 AS T3") > -1);

        
        stmt = "with temp1 (col1) as (values 256)" +
                " select t1.col1, s2.t1.c1 from temp1 t1, s2.t1;";
        result =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, matchInput);
        resultSchemaQualified = StatementHelper.getSQLSchemaQualified(result);
        resultTableQualified = StatementHelper.getSQLTableQualified(result);
        resultUnqualified = StatementHelper.getSQLUnqualified(result);

        assertTrue("column references should be schema qualified",
                resultSchemaQualified.indexOf("SELECT T1.COL1, S2.T1.C1") > -1);
        assertTrue("table references should be schema qualified",
                resultSchemaQualified.indexOf("FROM TEMP1 AS T1, S2.T1") > -1);
        
        assertTrue("column references should be table qualified",
                resultTableQualified.indexOf("SELECT T1.COL1, T1.C1") > -1);
        assertTrue("table references should be unqualified",
                resultTableQualified.indexOf("FROM TEMP1 AS T1, T1") > -1);

        assertTrue("column references should be unqualified",
                resultUnqualified.indexOf("SELECT COL1, C1") > -1);
        assertTrue("table references should be unqualified",
                resultUnqualified.indexOf("FROM TEMP1 AS T1, T1") > -1);


        stmt = "select t1.c1, s2.t1.c1 from (select c1 from s2.t1) t1, s2.t1;";
        result =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, matchInput);
        resultSchemaQualified = StatementHelper.getSQLSchemaQualified(result);
        resultTableQualified = StatementHelper.getSQLTableQualified(result);
        resultUnqualified = StatementHelper.getSQLUnqualified(result);

        assertTrue("column references should be schema qualified",
                resultSchemaQualified.indexOf("SELECT T1.C1, S2.T1.C1") > -1);
        assertTrue("column references should be schema qualified",
                resultSchemaQualified.indexOf("SELECT S2.T1.C1") > -1);
        assertTrue("table references should be schema qualified",
                resultSchemaQualified.indexOf("FROM S2.T1") > -1);
        assertTrue("table references should be schema qualified",
                resultSchemaQualified.indexOf("AS T1, S2.T1") > -1);
        
        assertTrue("column references should be table qualified",
                resultTableQualified.indexOf("SELECT T1.C1, T1.C1") > -1);
        assertTrue("column references should be table qualified",
                resultTableQualified.indexOf("SELECT T1.C1") > -1);
        assertTrue("table references should be unqualified",
                resultTableQualified.indexOf("FROM T1") > -1);
        assertTrue("table references should be unqualified",
                resultTableQualified.indexOf("AS T1, T1") > -1);

        assertTrue("column references should be unqualified",
                resultUnqualified.indexOf("SELECT C1, C1") > -1);
        assertTrue("column references should be unqualified",
                resultUnqualified.indexOf("SELECT C1") > -1);
        assertTrue("table references should be unqualified",
                resultUnqualified.indexOf("FROM T1") > -1);
        assertTrue("table references should be unqualified",
                resultUnqualified.indexOf("AS T1, T1") > -1);

    }


    /**
     * @param column
     * @param table
     * @return "column reference X to table Y was not resolved correctly" where
     *         "X" is substituted with given <code>column</code> and "Y" is
     *         substituted with given <code>table</code>
     */
    private String msgColTableNotResolved(String column, String table) {
        return "column reference "+column+" to table "+table+" was not resolved correctly";
    }
    
}
