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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.CallStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParseErrorInfo;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParseResult;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLControlParseResult;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager;
import org.eclipse.datatools.sqltools.parsers.sql.query.postparse.DataTypeResolver;
import org.eclipse.datatools.sqltools.parsers.sql.query.postparse.RoutineReferenceResolver;
import org.eclipse.datatools.sqltools.parsers.sql.query.postparse.TableReferenceResolver;

/**
 * The <code>InteractiveTest</code> is a command line tool allowing
 * console input of one SQL DML statement.
 * @author ckadner
 */
public class SQLQueryParserInteractiveTest
{

    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader( System.in );
        BufferedReader reader = new BufferedReader( isr );
        
        SQLQueryParserManager parserManager = SQLQueryParserManager.getInstance();

        String prompt1 = "Enter a SQL statement, ';' to end input and start parsing, or 'q' to quit:"; //$NON-NLS-1$
        long timeStart = 0;
        long timeConsumed = 0;
        try {
           String nextLine;
           String qstmt = new String();
           System.out.println( prompt1 );
           nextLine = reader.readLine();
           qstmt += nextLine;
           while (!nextLine.equalsIgnoreCase( "q" )) { //$NON-NLS-1$
              while (!nextLine.endsWith( ";" )) { //$NON-NLS-1$
                  nextLine = reader.readLine();
                  qstmt += "\n"; //$NON-NLS-1$
                  qstmt += nextLine;
              }
              
              System.out.println( "\nParsing:" ); //$NON-NLS-1$
              System.out.println( qstmt );
              
              //TODO: that might not be accurate
              // remove the last semicolon
              int endIndex = qstmt.lastIndexOf(';');
              qstmt = qstmt.substring(0, endIndex);
                            
              List p3s = new ArrayList(); // = SQLQueryParserManager.DEFAULT_POST_PARSE_PROCESSOR_LIST;
              p3s.add( new TableReferenceResolver() );
              p3s.add( new DataTypeResolver() );
              p3s.add(new RoutineReferenceResolver());
              
              SQLQuerySourceFormat sourceFormat = SQLQuerySourceFormat.copyDefaultFormat();
//              sourceFormat.setGenerateAsKeywordForTableCorrID(false);
              parserManager.configParser(
                              sourceFormat,
                              p3s);
              
              timeStart = System.currentTimeMillis();

              try {
                  SQLParseResult parseResult = parserManager.parse(qstmt);

                  timeConsumed = System.currentTimeMillis() - timeStart;

                  SQLStatement stmt = parseResult.getSQLStatement();
                  System.out.println( "\nGenerated root model object: "); //$NON-NLS-1$
                  System.out.println( stmt );
                  if (stmt != null) {
                      System.out.println( "\nGenerated query model AST:" ); //$NON-NLS-1$
                      parserManager.printAST(stmt);
                      System.out.println( "\nGenerated SQL from the query model:" ); //$NON-NLS-1$
                      System.out.println( stmt.getSQL() );

                      if (stmt instanceof QuerySelectStatement) {
                          QuerySelectStatement selectStmt = (QuerySelectStatement) stmt;
                          printEffectiveResultColumns(selectStmt);
                      }

                      List parseErrorList = parseResult.getErrorList();
                      printErrorList(parseErrorList);
                  }
                  else {
                      System.out.println( "No query model objects generated!" ); //$NON-NLS-1$
                  }

                  System.out.println("total time consumed:   "+timeConsumed+" ms"); //$NON-NLS-1$ //$NON-NLS-2$
              }
              catch(SQLParserException sqlEx) {
                  timeConsumed = System.currentTimeMillis() - timeStart;
                  System.out.println( "SQLParserException encountered." );
                  printErrorList(sqlEx.getErrorInfoList());
                  System.out.println("total time consumed:   "+timeConsumed+" ms"); //$NON-NLS-1$ //$NON-NLS-2$
              }
              catch(SQLParserInternalException sqlIntEx) {
                  timeConsumed = System.currentTimeMillis() - timeStart;
                  System.out.println( "SQLParserInternalException encountered." );
                  System.out.println("total time consumed:   "+timeConsumed+" ms"); //$NON-NLS-1$ //$NON-NLS-2$              
              }
              System.out.println("- - - - - - - - - - - - - - - - - - - - "); //$NON-NLS-1$
              System.out.println( prompt1 );
              nextLine = reader.readLine();
              qstmt = nextLine;

           }
           System.out.println();
           System.out.println( "Garbage collecting..." );          //$NON-NLS-1$
           System.gc();
           System.out.println();
           System.out.println( "Goodbye!" );          //$NON-NLS-1$
        
	    } catch (Exception ex) {
	        System.out.println( "Error reading from stdin, quitting..." + ex.getMessage() ); //$NON-NLS-1$
	        ex.printStackTrace();
	        System.exit(0);
	    }
     }
    
    /**
     * @param resultQuery
     */
    private static void printEffectiveResultColumns(QueryStatement resultQuery)
    {
        QuerySelectStatement selectStmt = 
              (QuerySelectStatement) resultQuery;
          List effectiveResultColumns = StatementHelper.getEffectiveResultColumns(selectStmt); 
          
          System.out.println( "\nEffective result columns (DataType):" ); //$NON-NLS-1$
          for (Iterator it = effectiveResultColumns.iterator(); it
                        .hasNext();)
          {
              ValueExpressionColumn col =
                  (ValueExpressionColumn) it.next();
              String colName = col.getName();
              String colType = null;
              if (col.getDataType() != null) {
                  colType = col.getDataType().toString();
                  colType = colType.substring(
                                  colType.lastIndexOf('.')+1,
                                  colType.lastIndexOf("Impl")); //$NON-NLS-1$
              }
              System.out.println( colName+
                              " ("+ colType + ")" ); //$NON-NLS-1$ //$NON-NLS-2$
          }
    }

    /**
     * @param errorList
     */
    private static void printErrorList(List errorList)
    {
        if (errorList == null || errorList.isEmpty()) {return;}
        
        System.out.println("\nErrors encountered:"); //$NON-NLS-1$
        int i = 1;
        for (Iterator errorIt = errorList.iterator(); errorIt.hasNext(); i++)
        {
            SQLParseErrorInfo errorInfo = (SQLParseErrorInfo) errorIt.next();
            
            String expected = ((errorInfo.getExpectedText() != null) ? ", expected: \"" //$NON-NLS-1$
                            + errorInfo.getExpectedText() + "\"" //$NON-NLS-1$
                            : ""); //$NON-NLS-1$
            
            System.out.println(i+": " +errorInfo.getParserErrorMessage()+
                            " at line:column "+ //$NON-NLS-1$
                            errorInfo.getLineNumberStart()+":"+ //$NON-NLS-1$
                            errorInfo.getColumnNumberStart()+
                            " to line:column "+ //$NON-NLS-1$
                            errorInfo.getLineNumberEnd()+":"+ //$NON-NLS-1$
                            errorInfo.getColumnNumberEnd()+
                            " \""+ //$NON-NLS-1$
                            errorInfo.getErrorSourceText()+"\""+ //$NON-NLS-1$
                            expected
                            );
        }
    }



}
