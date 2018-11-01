/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
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
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.modelbase.sql.query.CallStatement;
import org.eclipse.datatools.modelbase.sql.query.ProcedureReference;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.helper.DatabaseHelper;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceInfo;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParseErrorInfo;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.postparse.PostParseProcessor;
import org.eclipse.datatools.sqltools.parsers.sql.postparse.PostParseProcessorConfiguration;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserMessages;

/**
 * This class resolves and validates references to a SQL model stored procedure in a CALL statement during 
 * the post-parse processing (semantic processing) phase of parsing.
 */
public class RoutineReferenceResolver implements PostParseProcessor {

    /** Lists the classes of objects that this post-parse processor is interested in. */
    protected static Class[] CANDIDATE_TYPES = 
        new Class[] { CallStatement.class }; 
    
    /** Error code for procedure reference not found. */
    protected static final String ERROR_MESSAGE_KEY_UNRESOLVED_PROCEDURE =
        "RoutineReferenceResolver.UNRESOLVED_PROC"; //$NON-NLS-1$
    
    /** Error code for incorrect number of arguments for a procedure. */
    protected static final String ERROR_MESSAGE_KEY_INCORRECT_PARM_COUNT =
        "RoutineReferenceResolver.INCORRECT_PARM_COUNT" ; //$NON-NLS-1$
    
    /** The database object to use to find SQL model objects. */
    private Database fDatabase = null;
    
    /** The default schema name to use to resolve SQL model object references. */
    private String fDefaultSchemaName = null;
 
    /**
     * Constructs an instance of this class.  This is the default constructor.
     * Note: instances of this class should normally be constructed using the 
     * {@link #RoutineReferenceResolver(Database, String)} constructor in order to provide SQL model
     * information for validation.
     */
    public RoutineReferenceResolver() {
        this(null, null);
    }

    /**
     * Constructs an instance of this class with the given SQL model database object.
     * Note: instances of this class should normally be constructed using the 
     * {@link #RoutineReferenceResolver(Database, String)} constructor in order to provide SQL model
     * information for validation.
     */
    public RoutineReferenceResolver(Database database) {
        this(database, null);
    }
    
    /**
     * Constructs an instance of this class using the given database and default schema name.
     * 
     * @param aDB the SQL model Database object to use to locate objects
     * @param aDefaultSchemaName the default schema name to use to locate objects
     */
    public RoutineReferenceResolver(Database database, String defaultSchemaName) {
        setDatabase( database );
        setDefaultSchemaName( defaultSchemaName );
    }
        
    /**
     * Gets the current SQL model Database object.
     * 
     * @return Returns the database.
     */
    public Database getDatabase() {
        return fDatabase;
    }
    
    /**
     * Sets the SQL model Database object to use to find object references.
     * 
     * @param database the SQL model Database to use
     */
    public void setDatabase(Database database) {
        fDatabase = database;
    }
    
    /**
     * Gets the current default schema name.
     * 
     * @return the default schema name to use to find SQL model object references
     */
    public String getDefaultSchemaName() {
        return fDefaultSchemaName;
    }
    
    /**
     * Sets the default schema name to use to find SQL model object references.
     * 
     * @param defaultSchemaName the default schema name to use
     */
    public void setDefaultSchemaName(String defaultSchemaName) {
        fDefaultSchemaName = defaultSchemaName;
    }
        
    /**
     * Gets a list of classes that this processor is interested in.
     * 
     * @return an array of Class objects
     * @see org.eclipse.datatools.sqltools.parsers.sql.query.postparse.PostParseProcessor#getProcessCandidateTypes()
     */
    public Class[] getProcessCandidateTypes() {
        return RoutineReferenceResolver.CANDIDATE_TYPES;
    }

    /**
     * Configures this post-parse process with the Database object and default schema name contained
     * in the given configuration object.
     * 
     * @param config the configuration object to use
     */
    public void config(PostParseProcessorConfiguration config) {
        if (config != null) {
            setDatabase( config.getDatabase() );
            setDefaultSchemaName( config.getDefaultSchemaName() );
        }
    }
        
    /**
     * Post-parse processes the given SQL query object.
     * 
     * @param queryObj the SQL Query object to process
     * @throws SQLParserException when the given object fails validation
     */
    public List process(SQLQueryObject queryObj) throws SQLParserException {
        List errorList = new ArrayList();
        
        Database database = getDatabase();
        if (database != null && queryObj instanceof CallStatement) {
            CallStatement callStmt = (CallStatement) queryObj;
            
            String defaultSchemaName = getDefaultSchemaName();
            /* A default (omit) schema name in the query object's source info take precedence. */
            SQLQuerySourceInfo sourceInfo = callStmt.getSourceInfo();
            if (sourceInfo != null && sourceInfo.getSqlFormat() != null) {
                String tempSchemaName = sourceInfo.getSqlFormat().getOmitSchema();
                if (tempSchemaName != null && tempSchemaName.trim().length() > 0) {
                    defaultSchemaName = tempSchemaName;
                }
            }

            /* Get the "temporary" Procedure and Schema objects in the call statement and their names. */
            ProcedureReference procRef = callStmt.getProcedureRef();
            Procedure tempProc = null;
            String procName = null;
            Schema tempSchema = null;
            String schemaName = null;
            if (procRef != null) {
                tempProc = procRef.getProcedure();
                if (tempProc != null) {
                    procName = tempProc.getName();
                    tempSchema = tempProc.getSchema();
                    if (tempSchema != null) {
                        schemaName = tempSchema.getName();
                    }
                }
            }
            
            /* If no schema object was in the statement, use the default schema name. */
            if (schemaName == null) {
                schemaName = defaultSchemaName;
            }
            
            /* Locate the named database Schema object in the SQL model Database object. */
            Schema rdbSchema = DatabaseHelper.findSchema(database, schemaName);
            
            /* Locate the database Procedure object in the database schema. */
            Procedure rdbProc = null;
            if (rdbSchema != null) {
                rdbProc = DatabaseHelper.findProcedure(rdbSchema, procName);
            }
            
            /* Return an error if the proc is not found. */
            if (rdbProc == null) {
                String errMsg = SQLQueryParserMessages.getString( ERROR_MESSAGE_KEY_UNRESOLVED_PROCEDURE, null );
                SQLParseErrorInfo errInfo = new SQLParseErrorInfo( 0, 0, 0, 0, null, null, errMsg, ERROR_MESSAGE_KEY_UNRESOLVED_PROCEDURE );
                errorList.add(errInfo);
            }
            /* Otherwise continue processing the procedure reference. */
            else {
                /* Verify that the number of call arguments matches the procedure parameter count. */
                int argCount = 0;
                List argList = callStmt.getArgumentList();
                if (argList != null) {
                    argCount = argList.size();
                }
                int parmCount = 0;
                List parmList = rdbProc.getParameters();
                if (parmList != null) {
                    parmCount = parmList.size();
                }
                /* Return an error if the argument count doesn't match the parameter count. */
                if (argCount != parmCount) {
                    String[] errMsgArgs = new String[] {Integer.toString(parmCount), Integer.toString(argCount)};
                    String errMsg = SQLQueryParserMessages.getString( ERROR_MESSAGE_KEY_INCORRECT_PARM_COUNT, errMsgArgs );
                    SQLParseErrorInfo errInfo = new SQLParseErrorInfo( 0, 0, 0, 0, null, null, errMsg, ERROR_MESSAGE_KEY_INCORRECT_PARM_COUNT );
                    errorList.add(errInfo);
                }
                /* When everything is OK, substitute the SQL model procedure object for the temporary one. */
                else {
                    procRef.setProcedure(rdbProc);
                }
            }
        }
        
        return errorList;
    }

    /**
     * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.postparse.PostParseProcessor#getParsedObjectsReplacementMap()
     */
    public Map getParsedObjectsReplacementMap() {
        //return fParsedObjectsReplacementMap;
        return null;
    }
    
    /**
     * @see org.eclipse.datatools.sqltools.parsers.sql.query.postparse.PostParseProcessor#resetState()
     */
    public void resetState()  {
        //fParsedObjectsReplacementMap = new IdentityHashMap(); 
    }

}
