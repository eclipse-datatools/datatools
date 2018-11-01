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
package org.eclipse.datatools.sqltools.parsers.sql.query.postparse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.datatools.modelbase.sql.query.MergeOnCondition;
import org.eclipse.datatools.modelbase.sql.query.MergeSourceTable;
import org.eclipse.datatools.modelbase.sql.query.MergeTargetTable;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableFunction;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.WithTableReference;
import org.eclipse.datatools.modelbase.sql.query.helper.DatabaseHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceInfo;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParseErrorInfo;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserLogger;
import org.eclipse.datatools.sqltools.parsers.sql.postparse.PostParseProcessor;
import org.eclipse.datatools.sqltools.parsers.sql.postparse.PostParseProcessorConfiguration;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserMessages;
import org.eclipse.emf.common.util.EList;

/**
 * The <code>TableReferenceResolver</code> resolves references between columns
 * and tables as well as tables and schemas. It also populates tables with the
 * columns they expose and their datatypes.
 * 
 * @author ckadner
 */
public class TableReferenceResolver implements PostParseProcessor
{

    //dev note: when changed update JavaDoc of #getProcessCandidateTypes() also!
    protected static Class[] CANDIDATE_TYPES = 
        new Class[] { QueryStatement.class,
                      TableExpression.class,
                      QuerySelect.class, 
                      TableInDatabase.class,
                      TableJoined.class,
                      WithTableReference.class,
                      ValueExpressionColumn.class }; 
    
    /** Error code constant - not a message, database schemas not loaded */
    public static final String ERROR_CODE_DATABASE_SCHEMAS_NOTLOADED = 
        "DatabaseSchemasNotLoaded"; //$NON-NLS-1$

    /** Error code constant - not a message, column reference unresolved */
    public static final String ERROR_CODE_COLUMN_UNRESOLVED = 
        "SQL_?ColumnRefUnresolved"; //$NON-NLS-1$

    /** Error code constant - not a message, table reference unresolved */
    public static final String ERROR_CODE_TABLE_UNRESOLVED = 
        "SQL_?TableRefUnresolved"; //$NON-NLS-1$

    /** Error code constant - not a message, column not existent in table */
    public static final String ERROR_CODE_NONEXISTENT_COLUMN =
        "SQL_?NoSuchColumn"; //$NON-NLS-1$

    /** Error code constant - not a message, column exists in multiple tables */
    public static final String ERROR_CODE_COLUMN_AMBIGUOUS =
        "SQL_?AmbiguousColumn"; //$NON-NLS-1$
    
    
    protected static final String ERROR_MESSAGE_KEY_DATABASE_SCHEMAS_NOTLOADED =
        "TableReferenceResolver.SCHEMAS_NOT_LOADED"; //$NON-NLS-1$
    
    protected static final String ERROR_MESSAGE_KEY_UNRESOLVED_COLUMN =
        "TableReferenceResolver.UNRESOLVED_COLUMN"; //$NON-NLS-1$
    
    protected static final String ERROR_MESSAGE_KEY_UNRESOLVED_TABLE_NAME =
        "TableReferenceResolver.UNRESOLVED_TABLE"; //$NON-NLS-1$
    
    protected static final String ERROR_MESSAGE_KEY_NONEXISTENT_COLUMN =
        "TableReferenceResolver.TABLE_HAS_NO_SUCH_COLUMN"; //$NON-NLS-1$
    
    protected static final String ERROR_MESSAGE_KEY_AMBIGUOUS_COLUMN =
        "TableReferenceResolver.AMBIGUOUS_COLUMN"; //$NON-NLS-1$
    
    /** 
     * Stateful list of columns, to check for success of resolving after
     * processing, valid for one <code>QueryStatement</code>.
     * 
     * To clear in {@link #resetState()}, to not creating errors for other
     * out of context QueryStatements
     */
    protected List stmtsColumnList = new ArrayList();
    
    /** 
     * Stateful Map of <code>SQLQueryObject</code>s created by the parser that
     * are to be substituted or removed from the parser's parsed-element-list,
     * valid for one <code>QueryStatement</code>.
     * 
     * To clear in {@link #resetState()}
     */ 
    protected IdentityHashMap parsedObjectsReplacementMap = new IdentityHashMap();
    
    protected Database database = null;
    
    protected String defaultSchemaName = null;
    
    protected boolean logDebug = false;
    protected boolean logError = true;

    
 
    
    // ********************************************************** public methods
    
    /**
     * <b>Note:</b> use {@link #TableReferenceResolver(Database, String)} if the
     * <code>Database</code> is available and the default <code>Schema</code>
     * is know in order to properly resolve table references! <p> 
     * Constructs a new <code>TableReferenceResolver</code> without the
     * {@link Database} connected to which table references and column
     * references in {@link TableInDatabase} will be resolved.
     * For resolving of table and column references with the database tables and
     * columns use constructor {@link #TableReferenceResolver(Database,String)}.
     * @see #TableReferenceResolver(Database, String)
     */
    public TableReferenceResolver() {
    }

    /**
     * <b>Note:</b> use {@link #TableReferenceResolver(Database, String)} if the
     * <code>Database</code> is available and the default <code>Schema</code>
     * is know in order to properly resolve table references! <p> 
     * Constructs a new <code>TableReferenceResolver</code> without the
     * {@link Database} connected to which table references and column
     * references in {@link TableInDatabase} will be resolved.
     * For resolving of table and column references with the database tables and
     * columns use constructor {@link #TableReferenceResolver(Database,String)}.
     * @see #TableReferenceResolver(Database, String)
     * @param logError if <code>true</code> errors will be logged to the console,
     *   default is <code>true</code>
     */
    public TableReferenceResolver(boolean logError) {
        this();
        this.logError = logError;
    }

    /**
     * Constructs a new <code>TableReferenceResolver</code> with the
     * {@link Database} connected to which table references and column
     * references in {@link TableInDatabase} will be resolved. If the optional
     * default <code>Schema</code> name is given the unqualified table
     * references (<code>((TableInDatabase) 
     * tableRef).getDatabaseTable().getSchema() == null</code>)
     * are resolved only within the context of the <code>Schema</code> with the
     * given name <code>aDefaultSchemaName</code> in the <code>Database</code>.
     * <p>
     * <b>Note:</b> If the parser also is provided a current default
     * <code>Schema</code> name
     * (
     * {@link org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager#parse(String, SQLQuerySourceFormat)},
     * {@link org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat#getOmitSchema()}
     * ),
     * which will be associated with all unqualified
     * table references in the SQL source parsed, the given default
     * <code>Schema</code> name for this <code>TableReferenceResolver</code>
     * will not have the expected effect if it the two names are different!
     * </p>
     * 
     * @see org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager#parse(String, SQLQuerySourceFormat)
     * @see org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager#parse(String, SQLQuerySourceFormat, List)
     * @see org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager#parseScript(String, SQLQuerySourceFormat)
     * @see org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager#parseScript(String, SQLQuerySourceFormat, List)
     * @see org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat#getOmitSchema()
     * 
     * @param aDB
     *            the {@link Database} connected to which table references and
     *            column references in {@link TableInDatabase}will be resolved
     * @param aDefaultSchemaName -
     *            optional - name of default
     *            {@link org.eclipse.wst.rdb.internal.models.sql.schema.Schema},
     *            if specified the unqualified table references (
     *            <code>((TableInDatabase) tableRef).getDatabaseTable().getSchema() == null</code>)
     *            are resolved only within the context of the
     *            <code>Schema</code> with the given name
     *            <code>aDefaultSchemaName</code> in the <code>Database</code>,
     * 
     */
    public TableReferenceResolver(Database aDB, String aDefaultSchemaName) {
        this.database = aDB;
        this.defaultSchemaName = aDefaultSchemaName;
    }
    
    /**
     * Constructs a new <code>TableReferenceResolver</code> with the
     * {@link Database} connected to which table references and column
     * references in {@link TableInDatabase} will be resolved. If the optional
     * default <code>Schema</code> name is given the unqualified table
     * references (<code>((TableInDatabase) 
     * tableRef).getDatabaseTable().getSchema() == null</code>)
     * are resolved only within the context of the <code>Schema</code> with the
     * given name <code>aDefaultSchemaName</code> in the <code>Database</code>.
     * <p>
     * <b>Note:</b> If the parser also is provided a current default
     * <code>Schema</code> name
     * (
     * {@link org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager#parse(String, SQLQuerySourceFormat)},
     * {@link org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat#getOmitSchema()}
     * ),
     * which will be associated with all unqualified
     * table references in the SQL source parsed, the given default
     * <code>Schema</code> name for this <code>TableReferenceResolver</code>
     * will not have the expected effect if it the two names are different!
     * </p>
     * 
     * @see org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager#parse(String, SQLQuerySourceFormat)
     * @see org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager#parse(String, SQLQuerySourceFormat, List)
     * @see org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager#parseScript(String, SQLQuerySourceFormat)
     * @see org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager#parseScript(String, SQLQuerySourceFormat, List)
     * @see org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat#getOmitSchema()
     * 
     * @param aDB
     *            the {@link Database} connected to which table references and
     *            column references in {@link TableInDatabase}will be resolved
     * @param aDefaultSchemaName -
     *            optional - name of default
     *            {@link org.eclipse.wst.rdb.internal.models.sql.schema.Schema},
     *            if specified the unqualified table references (
     *            <code>((TableInDatabase) tableRef).getDatabaseTable().getSchema() == null</code>)
     *            are resolved only within the context of the
     *            <code>Schema</code> with the given name
     *            <code>aDefaultSchemaName</code> in the <code>Database</code>
     * @param logError if <code>true</code> errors will be logged to the console,
     *   default is <code>true</code>
     * 
     */
    public TableReferenceResolver(Database aDB, String aDefaultSchemaName, boolean logError) {
        this(aDB, aDefaultSchemaName);
        this.logError = logError;
    }
    
    /**
     * @return Returns the database.
     */
    public Database getDatabase()
    {
        return database;
    }
    /**
     * @param database The database to set.
     */
    public void setDatabase(Database database)
    {
        this.database = database;
    }
    /**
     * @return Returns the defaultSchemaName.
     */
    public String getDefaultSchemaName()
    {
        return defaultSchemaName;
    }
    /**
     * @param defaultSchemaName The defaultSchemaName to set.
     */
    public void setDefaultSchemaName(String defaultSchemaName)
    {
        this.defaultSchemaName = defaultSchemaName;
    }
    
    
    /**
     * This <code>TableReferenceResolver</code>'s candidate types are:
     * <ul>
     *  <li><code>QueryStatement</code></li>
     *  <li><code>TableExpression</code>*</li>
     *  <li><code>QuerySelect</code></li>
     *  <li><code>TableJoined</code></li>
     *  <li><code>TableWithSpecification</code></li>
     *  <li><code>TableInDatabase</code></li>
     *  <li><code>ValueExpressionColumn</code></li>
     * </ul>
     * 
     * <br>* even though <code>TableExpression</code> includes some of the other
     * candidate types, more special processing steps will be executed for its
     * subtypes, that are listed here separately 
     * 
     * @return <code>Class[]</code> of <code>SQLObject</code> types
     * 
     * @see org.eclipse.datatools.sqltools.parsers.sql.query.postparse.PostParseProcessor#getProcessCandidateTypes()
     */
    public Class[] getProcessCandidateTypes()
    {
        return TableReferenceResolver.CANDIDATE_TYPES;
    }

    /**
     * Configures this <code>TableReferenceResolver</code> with the
     * <code>Database</code> and default <code>Schema</code> name.
     * 
     * @param config the <code>PostParseProcessorConfiguration</code>
     *        providing the <code>Database</code> and the default
     *        <code>Schema</code> name
     */
    public void config(PostParseProcessorConfiguration config)
    {
        if (config != null) {
            this.database = config.getDatabase();
            this.defaultSchemaName = config.getDefaultSchemaName();
        }
    }
        
    /**
     * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.postparse.PostParseProcessor#process(org.eclipse.datatools.modelbase.sql.query.SQLQueryObject)
     */
    public List process(SQLQueryObject sqlQuery) throws SQLParserException
    {
        List errorList = new ArrayList();
        
        if (sqlQuery == null) {
            return errorList;
        }
        // check for most likely type first!
        else if (sqlQuery instanceof ValueExpressionColumn)
        {
            ValueExpressionColumn columnExpr = (ValueExpressionColumn) sqlQuery;
            this.stmtsColumnList.add( columnExpr );
        }
        else if (sqlQuery instanceof TableInDatabase)
        {
            TableInDatabase tableInDB = (TableInDatabase) sqlQuery;
            errorList.addAll( resolveTableReference(tableInDB) );
            
            if (parsedObjectsReplacementMap.containsKey(tableInDB))
            {
                sqlQuery = 
                    (SQLQueryObject) parsedObjectsReplacementMap.get(tableInDB);
            }
        }
        else if (sqlQuery instanceof TableJoined)
        {
            TableJoined tableJoin = (TableJoined) sqlQuery;
            errorList.addAll( resolveTableReference(tableJoin) );
        }
        else if (sqlQuery instanceof QuerySelect)
        {
            QuerySelect select = (QuerySelect) sqlQuery;
            errorList.addAll( resolveTableReferences(select) );
        }
        // check for general TableExpression after explicit subtypes of it!
        else if (sqlQuery instanceof QuerySelectStatement)
        {
            QuerySelectStatement selectStmt = (QuerySelectStatement) sqlQuery;
            errorList.addAll( resolveTableReferences(selectStmt) );
        }
        else if (sqlQuery instanceof QueryInsertStatement)
        {
            QueryInsertStatement insertStmt = (QueryInsertStatement) sqlQuery;
            errorList.addAll( resolveTableReferences(insertStmt) );
        }
        else if (sqlQuery instanceof QueryMergeStatement) 
        {
            QueryMergeStatement mergeStmt = (QueryMergeStatement) sqlQuery;
            errorList.addAll( resolveTableReferences(mergeStmt) );
        }
        else if (sqlQuery instanceof QueryUpdateStatement)
        {
            QueryUpdateStatement updateStmt = (QueryUpdateStatement) sqlQuery;
            errorList.addAll( resolveTableReferences(updateStmt) );
        }
        else if (sqlQuery instanceof QueryDeleteStatement)
        {
            QueryDeleteStatement deleteStmt = (QueryDeleteStatement) sqlQuery;
            errorList.addAll( resolveTableReferences(deleteStmt) );
        }

        
        // =====================================================================
        // aditionally checks for more general type resolvings
        // =====================================================================

        // check for general TableExpression after explicit subtypes of it!
        if (sqlQuery instanceof TableExpression)
        {
            TableExpression tableExpr = (TableExpression) sqlQuery;
            
            // export the result columns as columns of a table expression if the
            // QuerySelect serves as a nested query/table query
            if (tableExpr.getColumnList() == null
                            || tableExpr.getColumnList().isEmpty())
            {
                TableHelper.exposeEffectiveResultColumns(tableExpr);
            }
            errorList.addAll( checkForUnresolvedTable(tableExpr) );
        }
        // check if all columns could be resolved
        else if (sqlQuery instanceof QueryStatement)
        {
            // onetime check if the default-schema given matches the
            // default-schema given to the parser, kindof post process here, but
            // still in scope to put out an error to the log
            checkForAmbiguousDefaultSchemas(sqlQuery);
            
            // onetime check if the database given is populated with Schemas
            // if not, return one error instead of many errors for unresolved table
            errorList.addAll( checkForUnloadedDatabaseSchemas() );
            
            errorList.addAll( checkForUnresolvedColumns() );
        }
        
        return errorList;
    }

    
    /**
     * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.postparse.PostParseProcessor#getParsedObjectsReplacementMap()
     */
    public Map getParsedObjectsReplacementMap()
    {
        return parsedObjectsReplacementMap;
    }
    
    /**
     * @see org.eclipse.datatools.sqltools.parsers.sql.query.postparse.PostParseProcessor#resetState()
     */
    public void resetState()
    {
        parsedObjectsReplacementMap = new IdentityHashMap(); 
        // not Map.clear() as we don't know what will be done with the refernce
        // with just gave away in getParsedObjectsReplacementMap(), we would
        // make it useless for the consumer!
        
        stmtsColumnList.clear();
    }

    
    
    // ******************************************************* protected methods
    
    /**
         * Checks if the defaultSchema given to this
         * <code>TableReferenceResolver</code> matches the default omitSchema
         * provided to/by the parser.
         * <p>
         * <b>Note:</b> should not return <code>SQLParseErrorInfo</code> as the user
         * can't do anything about it! It's an programming/usage mistake!
         * @param sqlQuery
         */
        protected void checkForAmbiguousDefaultSchemas(SQLQueryObject sqlQuery)
        {
            SQLQuerySourceInfo sourceInfo = sqlQuery.getSourceInfo();
            if (sourceInfo != null && sourceInfo.getSqlFormat() != null
                            && this.defaultSchemaName != null)
            {
                SQLQuerySourceFormat sourceFormat = sourceInfo.getSqlFormat();
                String omitSchemaName = sourceFormat.getOmitSchema();
    //            char delimitedIdentQt =
    //                sourceFormat.getDelimitedIdentifierQuote();
    //            String identDelimQtString = String.valueOf(delimitedIdentQt);
                
                //FIXME quest wsdbu00036477: remove this conversion, as it should be converted
    //            if (omitSchemaName != null
    //                    && omitSchemaName.startsWith(identDelimQtString)
    //                    && omitSchemaName.endsWith(identDelimQtString))
    //            {
    //                omitSchemaName =
    //                    StatementHelper.convertSQLIdentifierToCatalogFormat(omitSchemaName, delimitedIdentQt);
    //            }
                
                //problem? check if we have different current schema names
                // given to the parser and given to the PostParseProcessor
                // no problem if omitSchema == null, then fallback to defaultSchema
                if (omitSchemaName != null
                                && omitSchemaName.length() > 0
                                && !omitSchemaName.equals(this.defaultSchemaName))
                {
                    logInfo("checkForAmbiguousDefaultSchemas(SQLQueryObject)", //$NON-NLS-1$
                             " default schema name given doesn't match" //$NON-NLS-1$
                                    + " default schema name given to parser: " //$NON-NLS-1$
                                    + this.defaultSchemaName
                                    + " != " + omitSchemaName); //$NON-NLS-1$
                }
            }
        }

    /**
     * Checks if this <code>TableReferenceResolver</code>'s
     * <code>database</code> is populated with <code>Schema</code>s.
     * 
     * @return List with one <code>SQLParseErrorInfo</code> with
     *         {@link SQLParseErrorInfo#getErrorCode()}==
     *         {@link #ERROR_CODE_DATABASE_SCHEMAS_NOTLOADED}and
     *         {@link SQLParseErrorInfo#getParserErrorMessage()}==
     *         {@link #ERROR_MESSAGE_DATABASE_SCHEMAS_NOTLOADED_}
     *         <code>.concat(database.getName())</code>
     */
    protected List checkForUnloadedDatabaseSchemas()
    {
        List errorList = new ArrayList();
        boolean foundSchemas = false;
    
        // TODO: how to deal with databases that don't support schemas
        if (database != null){
            // First check to see if schemas are attached to the database object directly.
            List schemaList = database.getSchemas();
            if (schemaList != null && schemaList.size() > 0) {
                foundSchemas = true;
            }
            
            // Otherwise we need to see if there are any catalogs attached to the
            // database, and look for schemas belonging to the catalog.
            List catalogList = database.getCatalogs();
            if (!foundSchemas && catalogList != null) {
                Iterator catalogListIter = catalogList.iterator();
                while (catalogListIter.hasNext() && foundSchemas == false){
                    Catalog catalog = (Catalog) catalogListIter.next();
                    schemaList = catalog.getSchemas();
                    if (schemaList != null && schemaList.size() > 0) {
                        foundSchemas = true;
                    }
                }
            }
    
            // If we didn't find any schemas anywhere, create a parse error.
            // Make it the most important, first error as most further resolving depends on it.
            if (!foundSchemas){
            errorList.add(0, 
                new SQLParseErrorInfo( 0, 0, 0, 0, null, null,
                    SQLQueryParserMessages.getString( 
                        ERROR_MESSAGE_KEY_DATABASE_SCHEMAS_NOTLOADED,
                        new String[] {database.getName()}
                    ), 
                    ERROR_CODE_DATABASE_SCHEMAS_NOTLOADED 
                )
            );
            }
        }
    
        return errorList;
    }

    /**
     * @return List of {@link SQLParseErrorInfo}
     */
    protected List checkForUnresolvedColumns()
    {
        List errorList = new ArrayList();
        
        for (Iterator colIt = stmtsColumnList.iterator(); colIt.hasNext();)
        {
            ValueExpressionColumn colExpr = 
                (ValueExpressionColumn) colIt.next();
            errorList.addAll( checkForUnresolvedColumn(colExpr) );
        }
        return errorList;
    }

    /**
         * @param colExpr
         * @return List of {@link SQLParseErrorInfo}
         */
        protected List checkForUnresolvedColumn(ValueExpressionColumn colExpr)
        {
            List errorList = new ArrayList();
            
                
            TableExpression colTable = colExpr.getTableExpr();
            
            // error if column has no table reference at all
            // or if column's table name could not have been resolved
            if (colTable == null)
            {
                if (StatementHelper.isColumnNameAmbiguous(colExpr))
                {
                    errorList.add( new SQLParseErrorInfo(
                            colExpr.getSourceInfo(),
                            "<table_name_or_alias>."+colExpr.getName(), //$NON-NLS-1$
                            SQLQueryParserMessages.getString(
                                            ERROR_MESSAGE_KEY_AMBIGUOUS_COLUMN,
                                            new String[] {colExpr.getName()}),
                            ERROR_CODE_COLUMN_AMBIGUOUS)
                        );
                    
                    // TODO: find potential tables to resolve and generate SQL for input correction suggestion
                }
                else
                {
                    errorList.add( new SQLParseErrorInfo(
                                    colExpr.getSourceInfo(),
                                    "<table_name_or_alias>."+colExpr.getName(), //$NON-NLS-1$
                                    SQLQueryParserMessages.getString(
                                                    ERROR_MESSAGE_KEY_UNRESOLVED_COLUMN,
                                                    new String[] {colExpr.getName()}),
                                    ERROR_CODE_COLUMN_UNRESOLVED)
                                );
                }
            }
            else 
    //                if (stmt instanceof QuerySelectStatement)
    //                {
    //                    // if the column was not resolved, the table it references will not
    //                    // be the tableRef that is part of the FROM-clause of the containing
    //                    // select statement
    //                    if (StatementHelper.getQuerySelectForTableReference(colTable) == null )
    //                  {
    //                      errorList.add( new SQLParseErrorInfo(
    //                                          colExpr.getSourceInfo(),
    //                                          "<table_name_or_alias>."+colExpr.getName(),
    //                                          ERROR_MESSAGE_UNRESOLVED_TABLE_NAME_+colTable.getName())
    //                                      );
    //                  }
    //                } 
    //                else
                if (colTable.eContainer() == null)
                {
                    // if the column was not resolved, the table it references will not
                    // be the tableRef that is part of the FROM-clause of the containing
                    // select statement, delete, insert or update stmt
                    errorList.add( new SQLParseErrorInfo(
                                        colExpr.getSourceInfo(),
                                        "<table_name_or_alias>."+colExpr.getName(), //$NON-NLS-1$
                                        SQLQueryParserMessages.getString(
                                                        ERROR_MESSAGE_KEY_UNRESOLVED_TABLE_NAME,
                                                        new String[] {colTable.getName()}),
                                        ERROR_CODE_TABLE_UNRESOLVED)
                                    );
                    
                    // TODO: find potential tables that contain a likenamed column to resolve and generate SQL for input correction suggestion
    
                }
                // if table has been populated with its columns but the column we check is not in it
                else if (!colTable.getColumnList().isEmpty() && !isColumnInTable(colTable,colExpr))
                {
    //                String errorMsgColTable = null;
    //                
    //                if (colExpr.getTableExpr() != null)
    //                {
    //                    errorMsgColTable = " in " + colExpr.getTableExpr().getName(); //$NON-NLS-1$
    //                }
    //                else
    //                {
    //                    errorMsgColTable = ""; //$NON-NLS-1$
    //                }
    
                    errorList.add( new SQLParseErrorInfo(
                                    colExpr.getSourceInfo(),
                                    "<table_name_or_alias>.<column_name>", //$NON-NLS-1$
                                    SQLQueryParserMessages.getString(
                                                    ERROR_MESSAGE_KEY_NONEXISTENT_COLUMN,
                                                    new String[] {colTable.getName(),colExpr.getName()}),
                                    ERROR_CODE_NONEXISTENT_COLUMN)
                                );
                    
                    // TODO: find potential tables in FROM-clause that contain a likenamed column to resolve and generate SQL for input correction suggestion
    
                }
                    
                
    //                // should be no more else case here, cause we think we have no
    //                // different stmt type, but who knows what got extended 
    //                
    //                // if the column SQL is like "t1.col1" and it was not resolved
    //                // t1 will be the name of a fake TableInDatabase because the parser
    //                // guesses it is meant to be a table in database
    //                // this condition is not sufficient if the column SQL was like "s1.t1.col1"
    //                // cause the the parser will create a Table to associate a Schema to
    //                // keep the Schema information for the column, see factory for <schema>.<table>.<column>
    //                else if (colTable instanceof TableInDatabase &&
    //                                ((TableInDatabase) colTable).getDatabaseTable() == null )
    //                {
    //                    errorList.add( new SQLParseErrorInfo(
    //                                      colExpr.getSourceInfo(),
    //                                      "<table_name_or_alias>."+colExpr.getName(),
    //                                      ERROR_MESSAGE_UNRESOLVED_TABLE_NAME_+colTable.getName())
    //                                    );
    //                }
    
                
            
            
            
            return errorList;
        }

    /**
         * Checks if the given <code>tableExpr</code> could have been resolved,
         * by checking for the <code>tableExpr</code>'s <code>columnList</code> to
         * be not <code>null</code> and not empty under the condition, that a
         * <code>Database</code> was provided to this
         * <code>TableReferenceResolver</code>.
         * @param tableExpr
         * @return List of {@link SQLParseErrorInfo}
         */
        protected List checkForUnresolvedTable(TableExpression tableExpr)
        {
            List errorList = new ArrayList();
    
            boolean isQuery = tableExpr instanceof QueryExpressionBody;
            boolean isFromTable = tableExpr.getQuerySelect() != null;
            boolean hasColumns = (tableExpr.getColumnList() != null
                    && !tableExpr.getColumnList().isEmpty());
            boolean isTableFunction = tableExpr instanceof TableFunction;
    
            // if we had a database only, otherwise we might not have any column
            // information even for non-TableInDatabase TableExpressions,
            // but if we had a database, we must have the columnList compiled and
            // exposed for all types of TableExpressions
            // check for not populated database is done separately
    // TODO remove the check for Tablefunction, once semantic resolution of table functions is implemented
            if (this.database != null && !database.getSchemas().isEmpty()
                    && (!isQuery || isFromTable)  // don't check the columns of QuerySelects or QueryCombined, as that is resolved differently, except the query is used as nested query table
                    && !hasColumns
                    && !isTableFunction) //do not try to resolve table functions. This is temporary 
            {
                SQLQuerySourceInfo sourceInfo = tableExpr.getSourceInfo();
                String errorMsg =
                    SQLQueryParserMessages.getString(
                                ERROR_MESSAGE_KEY_UNRESOLVED_TABLE_NAME,
                                new String[] {sourceInfo.getSourceSnippet()});
                errorList.add( new SQLParseErrorInfo(sourceInfo, null, errorMsg,
                                ERROR_CODE_TABLE_UNRESOLVED) );
            }
            return errorList;
        }

    /**
     * Resolves references between <code>ValueExpressionColumn</code>s in the
     * given <code>columns</code> and the <code>TableReference</code>s in
     * the given list <code>tableRefs</code>
     * @param columns set of columns to be resolved with table references
     * @param tableRefs list of tables (in a FROM clause)
     */
    protected void resolveColumnTableReferences(Collection columns, List tableRefs) {
        // TODO: check implementation to not resolve inprecisely qualified columns
        //       but later on provide correction suggestions when creating errors
        //       for unresolved columns
        TableHelper.resolveColumnTableReferences( columns, tableRefs );
    }

    /**
     * Resolves the given <code>TableInDatabase</code> proxy with the real
     * <code>Table</code> in the <code>database</code>.
     * If one is found matching the <code>name</code>
     * and, if present, the <code>Schema</code> <code>name</code> of the
     * given <code>tableInDB</code>.
     * 
     * @param tableInDB
     *            the <code>TableInDatabase</code> proxy to be resolved
     * @param aDefaultSchemaName
     *            the Schema name ommited in the SQL source or the name of the
     *            current Schema for the <code>database</code>
     */
    protected void resolveTableInDatabaseWithRDBTable(TableInDatabase tableInDB, String aDefaultSchemaName)
    {
        DatabaseHelper.resolveTableReferenceRDBTable(tableInDB, database, aDefaultSchemaName);
    }

    /**
     * @param tableInDB
     * @return
     */
    protected List resolveTableReference(TableInDatabase tableInDB)
    {
        List errorList = new ArrayList();
        
        // try to resolve the tableRef into a WithTableReference, as the
        // tableInDB could have been mistakenly created but really should be a
        // reference to a WithTable
        WithTableReference withTable = 
            resolveWithTableSpecificationForTableRef(tableInDB);
        
        // if we don't have a reference to a WithTable we really have a proxy to
        // a database table and we must have a database connection to resolve
        // the table with the real RDB table and columns
        if (withTable != null)
        {
            // this will not be done in process() cause we only just created the
            // WithTableReference and it the parser didn't export it! do it now
            if (withTable.getColumnList() == null
                            || withTable.getColumnList().isEmpty())
            {
                TableHelper.exposeEffectiveResultColumns(withTable);
            }
            
            // we also don't want the mistakenly generated tableInDB reference
            // to be kept in the parser's parsed-object-list for further
            // postParseProcessing, but have the withTable refenrence in place
            parsedObjectsReplacementMap.put(tableInDB, withTable);
            // also switch the source info
            SQLQuerySourceInfo sourceInfo = tableInDB.getSourceInfo();
            tableInDB.setSourceInfo(null);
            withTable.setSourceInfo( sourceInfo );
        }
        else //withTable == null
        {
            if (this.database != null) 
            {
                String aDefaultSchemaName = null;
                
                // prioritized defaultSchema is the one Schema to omit given to the
                // parser via SQLQuerySourceFormat#omitSchema
                SQLQuerySourceInfo tdbsi = tableInDB.getSourceInfo();
                if (tdbsi != null && tdbsi.getSqlFormat() != null) {
                    aDefaultSchemaName = tdbsi.getSqlFormat().getOmitSchema();
                }
                if (aDefaultSchemaName == null || aDefaultSchemaName.trim().length() == 0) {
                    aDefaultSchemaName = this.defaultSchemaName;
                }
                
                resolveTableInDatabaseWithRDBTable(tableInDB, aDefaultSchemaName);
            }
            else
            {   
                // do not log errors when there is no database conenction
/*              logError("resolveTableReference(TableInDatabase)", //$NON-NLS-1$
                         " could not be executed because " //$NON-NLS-1$
                         + Database.class.getName() + " was not provided." //$NON-NLS-1$
                         + " Init the "+TableReferenceResolver.class.getName() //$NON-NLS-1$
                         + " with the database connected!"); //$NON-NLS-1$
*/          }
        }
        return errorList;
    }

    /**
     * @param tableJoin
     * @return
     */
    protected List resolveTableReference(TableJoined tableJoin)
    {
        List errorList = new ArrayList();
        
        List tableRefList = new ArrayList();
        tableRefList.add(tableJoin.getTableRefLeft());
        tableRefList.add(tableJoin.getTableRefRight());
        
        Set columnExprSet = TableHelper.findColumnReferencesInSearchCondition(tableJoin.getJoinCondition());
        resolveColumnTableReferences( columnExprSet, tableRefList );
        
        
/*
        //throw new UnsupportedOperationException(
        SQLParserManager.logError(TableReferenceResolver.class.getName()
            + "#resolveTableReference(TableJoined) not implemented for "
            + TableTableJoined.class.getName()+ ".");
*/        
        
        return errorList;
    }

    
    /**
     * @param deleteStmt
     * @return
     */
    protected List resolveTableReferences(QueryDeleteStatement deleteStmt)
    {
        List errorList = new ArrayList();
        
        List tableRefList = new ArrayList();
        tableRefList.add(deleteStmt.getTargetTable());
    
        Collection columnExprs = TableHelper.findColumnReferencesInSearchCondition( deleteStmt.getWhereClause() );
        
        resolveColumnTableReferences( columnExprs, tableRefList );
        
        return errorList;
    }

    /**
     * @param insertStmt
     * @return
     */
    protected List resolveTableReferences(QueryInsertStatement insertStmt)
    {
        List errorList = new ArrayList();
        
        List tableRefList = new ArrayList();
        tableRefList.add(insertStmt.getTargetTable());

        // check resolveColumnTableReferences, we can not just pass the targetColumnList? not the targetColumnList cause it would get modified!
        List columnList = new ArrayList( insertStmt.getTargetColumnList() );
        
        resolveColumnTableReferences( columnList, tableRefList );
        
        return errorList;
    }

    protected List resolveTableReferences(QueryMergeStatement mergeStmt)
    {
        List errorList = new ArrayList();
        
        List tableRefList = new ArrayList();
        MergeTargetTable targetTable = mergeStmt.getTargetTable();
        TableExpression targetTableExpr = targetTable.getTableExpr();
        
        MergeSourceTable sourceTable = mergeStmt.getSourceTable();
        TableReference sourceTableRef = sourceTable.getTableRef();
        
        /* Columns in the left side of the update or insert must reference the target table.  
         * Resolve those columns first. (When there is a single table in the list, the column resolver 
         * code assumes that must be the correct table.)*/
        List colList = StatementHelper.getMergeModificationTargetColumnReferences(mergeStmt);
        tableRefList.add(targetTableExpr);
        resolveColumnTableReferences(colList, tableRefList);
        
        /* Now get all the column references in the statement and try to resolve them against
         * all the tables referenced in the statement. */
        Set colSet = TableHelper.findColumnReferencesInQueryMergeStatement(mergeStmt);
        tableRefList.add(sourceTableRef);
        resolveColumnTableReferences(colSet, tableRefList);
        
        return errorList;
    }

    /**
     * @param select
     * @return
     */
    protected List resolveTableReferences(QuerySelect select)
    {
        List errorList = new ArrayList();
        
        // Get the column references in this QuerySelect.
        Set columnSet = TableHelper.findColumnReferencesInQueryExpressionBody(select); 
        columnSet.addAll(findUnresolvedColumnReferencesInScope(select));
        
        // First try resolving the columns using only tables in the local scope.
        List tableList = StatementHelper.getTableExpressionsInQuerySelect(select);
        resolveColumnTableReferences(columnSet, tableList);

        // Get a list of all the tables that are visible for this query select and try
        // resolving again.
        List visibleTableList = StatementHelper.getTableExpressionsVisibleInQuerySelect(select);
        resolveColumnTableReferences(columnSet, visibleTableList); 
        
        // resolve resultColumns in ResultSpec ...is included up here already!
        // only resolve resultTableAllColumns in ResultSpec with actual tableRef
        StatementHelper.resolveResultTableAllColumns(select);
        
        // export the result columns as columns of a table expression if the QuerySelect serves as a nested query/table query
        // already coded in process()
        //TableHelper.exposeEffectiveResultColumns(select);
        
        return errorList;
    }

    /**
     * @param selectStmt
     * @return
     */
    protected List resolveTableReferences(QuerySelectStatement selectStmt)
    {
        List errorList = new ArrayList();
        
        Collection removedColumns = null;
        
        //resolve column references in ORDER BY clause with result columns
        // change mistakenly created OrderByValueExpression to OrderByResultColumn
        // for every OrderByValueExpression refering to a ValueExpressionColumn,
        // which is also refered to by a ResultColumn.
        // keep collection of removed columns of the former OrderByValueExpression
        // to also be removed from this TableReferenceResolver's stmtsColumnList
        removedColumns = StatementHelper.resolveOrderByColumns(selectStmt.getQueryExpr().getQuery(), selectStmt.getOrderByClause());
        stmtsColumnList.removeAll(removedColumns);
        for (Iterator removeIt = removedColumns.iterator(); removeIt.hasNext();)
        {
            parsedObjectsReplacementMap.put(removeIt.next(), null);
        }
    
        
        //resolve column references
        List tableRefList = StatementHelper.getTablesForStatement(selectStmt);
        Set columnExprSet = TableHelper.findColumnReferencesInQuerySelectStatement(selectStmt);
        resolveColumnTableReferences( columnExprSet, tableRefList );
    
        
        // make sure the result columns of the Query are exposed
        QueryExpressionBody queryExpr = selectStmt.getQueryExpr().getQuery();
        if (queryExpr.getColumnList() == null ||
                        queryExpr.getColumnList().isEmpty())
        {
            TableHelper.exposeEffectiveResultColumns(queryExpr);
        }
    
        
        return errorList;
    }

    /**
     * @param updateStmt
     * @return
     */
    protected List resolveTableReferences(QueryUpdateStatement updateStmt)
    {
        List errorList = new ArrayList();
    
        List tableRefList = new ArrayList();
        tableRefList.add(updateStmt.getTargetTable());

        Set columnSet = TableHelper.findColumnReferencesInQueryUpdateStatement(updateStmt);
        
        resolveColumnTableReferences( columnSet, tableRefList );
        
        return errorList;
    }

    
    /**
     * Checks if the given <code>tableInDB</code> mistakenly was created by the
     * parser, but is supposed to be a reference to a
     * <code>WithTableSpecification</code>. If so, the given
     * <code>tableInDB</code> will be resolved to a
     * <code>WithTableReference</code> and hooked into the associated
     * <code>QuerySelect</code> instead of the given <code>tableInDB</code>.
     * 
     * @param tableInDB
     * @return the <code>WithTableReference</code> or <code>null</code>
     */
    protected WithTableReference resolveWithTableSpecificationForTableRef(TableInDatabase tableInDB)
    {
        WithTableReference withTableRef = null;
        
        Table dbTable     = tableInDB.getDatabaseTable();
        Schema schema     = (dbTable == null)? null : dbTable.getSchema();
        String schemaName = (schema == null)? null : schema.getName();
        
        SQLQuerySourceInfo sourceInfo = tableInDB.getSourceInfo();
        SQLQuerySourceFormat sourceFormat = 
            (sourceInfo == null)? SQLQuerySourceFormat.SQL_SOURCE_FORMAT_DEFAULT : sourceInfo.getSqlFormat();
        String omitSchema = sourceFormat.getOmitSchema();
        char identDelimQt = sourceFormat.getDelimitedIdentifierQuote();
        String identDelimQtString = String.valueOf(identDelimQt);
        
        if (omitSchema != null
                && omitSchema.startsWith(identDelimQtString)
                && omitSchema.endsWith(identDelimQtString))
        {
            omitSchema = StatementHelper.convertSQLIdentifierToCatalogFormat(omitSchema, identDelimQt);
        }
        
        // it can only be a reference to a WithTable, if the table reference
        // doesn't have a Schema or the Schema itself was mistakenly created
        // by the parser as it associates unqualified table references with the
        // default Schema
        if (dbTable == null
                        || schema == null
                        || schemaName == null
                        || schemaName.equals(omitSchema)
                        || schemaName.equals(defaultSchemaName))
        {
            
            // check for existence of TableWithSpec is implicit!
            withTableRef = 
                StatementHelper.resolveWithTableSpecificationReference(tableInDB);
            
            // already been done above in resolveWithTableSpecificationReference()
            //TableHelper.exposeEffectiveResultColumns((TableExpression)withTableRef);
        }
        return withTableRef;
    }

    /**
     * @param errorList
     */
    protected void printErrorList(List errorList)
    {
        if (errorList == null || errorList.isEmpty()) {return;}
        
        SQLParserLogger.getLogger().writeInfo("Errors encountered by "+this.getClass().getName()+":"); //$NON-NLS-1$ //$NON-NLS-2$
        for (Iterator errorIt = errorList.iterator(); errorIt.hasNext();)
        {
            SQLParseErrorInfo errorInfo = (SQLParseErrorInfo) errorIt.next();
            
            String expected = ((errorInfo.getExpectedText() != null) ? ", expected: \"" //$NON-NLS-1$
                            + errorInfo.getExpectedText() + "\"" //$NON-NLS-1$
                            : ""); //$NON-NLS-1$
            
            SQLParserLogger.getLogger().writeInfo(errorInfo.getParserErrorMessage()+
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

    /**
     * for example in:<br>
     * 
     * "with temp1 (col1) as (values 256),
     *       temp2 (col2) as (values 'string')
     *  select t1.col1 from temp1 t1
     *  where exists (
     *          select * from temp2 t2 where t1.col1 = t2.col2);"
     * 
     * here "t1.col1" will be attempted to resolve in the context of the nested
     * select, but must be resolved in the context of the sourrounding select.
     * 
     * 
     * @param scope
     * @return
     */
    private List findUnresolvedColumnReferencesInScope(SQLQueryObject scope)
    {
        List unresolvedColumnsInScope = new ArrayList();
        
        int scopeSpanBegin = scope.getSourceInfo().getSpanStartOffset();
        int scopeSpanEnd = scope.getSourceInfo().getSpanEndOffset();
        
        // check if we have span information at all, if not, we can't proceed
        if (scopeSpanBegin == 0 && scopeSpanEnd == 0)
        {
            return unresolvedColumnsInScope;
        }
        
        // check the columns of the whole stmt for their position in the input
        // source to figure out if they are in the given scope
        for (Iterator it = stmtsColumnList.iterator(); it.hasNext();)
        {
            ValueExpressionColumn column = (ValueExpressionColumn) it.next();
            int columnSpanBegin = column.getSourceInfo().getSpanStartOffset();
            int columnSpanEnd = column.getSourceInfo().getSpanEndOffset();
            
            // TODO performance optimize: filter out the columns already resolved
            // if it was resolved, we should not get an error in the list returned
            // and then we don't proceed as we look for unresolved columns
            if (checkForUnresolvedColumn(column).isEmpty())
            {
                continue;
            }
            else if (columnSpanBegin >= scopeSpanBegin
                            && columnSpanEnd <= scopeSpanEnd)
            {
                unresolvedColumnsInScope.add(column);
            }
            
        }
        
        
        return unresolvedColumnsInScope;
    }

    /**
     * @param tableExpr
     * @param colExpr
     * @return
     */
    private boolean isColumnInTable(TableExpression tableExpr, ValueExpressionColumn colExpr) {
        boolean hasTableColumn = false;
        
        if (tableExpr != null && colExpr != null) {
//          List tableColList = tableExpr.getColumnList();
          String colExprName = colExpr.getName();
          ValueExpressionColumn resolvedCol = TableHelper.getColumnExpressionForName(tableExpr, colExprName);
          if (resolvedCol != null) {
              hasTableColumn = true;
          }
//          if (tableColList != null && colExprName != null) {
//              Iterator tableColListIter = tableColList.iterator();
//              while (tableColListIter.hasNext() && hasTableColumn == false) {
//                  ValueExpressionColumn tableCol = (ValueExpressionColumn) tableColListIter.next();
//                  String tableColName = tableCol.getName();
//                  if (StatementHelper.equalSQLIdentifiers(colExprName, tableColName)) {
//                      hasTableColumn = true;
//                  }
//              }
//          }
      }
        
        return hasTableColumn;
    }
    
    /**
     * Writes given String to error log.
     * Functions as a proxy to common logging
     * @param errorMsg error message
     * @param methodName the name of the method raising the error
     */
    private void logError(String methodName, String errorMsg)
    {
        if (logError)
        {
            SQLParserLogger.getLogger().writeLog(this.getClass().getName() +
                        "#" + methodName + ": " + //$NON-NLS-1$ //$NON-NLS-2$
                        errorMsg);
        }
    }

    /**
     * Writes given String to error log if log level is INFO.
     * Functions as a proxy to common logging
     * @param errorMsg error message
     * @param methodName the name of the method raising the error
     */
    private void logInfo(String methodName, String errorMsg)
    {
//        if (logDebug)
//        {
            SQLParserLogger.getLogger().writeInfo(this.getClass().getName() +
                        "#" + methodName + ": " + //$NON-NLS-1$ //$NON-NLS-2$
                        errorMsg);
//        }
    }

}
