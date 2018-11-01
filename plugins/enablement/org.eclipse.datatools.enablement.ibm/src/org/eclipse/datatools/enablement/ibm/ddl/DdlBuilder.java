/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.ddl;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.IEngineeringCallBack;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.ibm.IBMPluginActivator;
import org.eclipse.datatools.enablement.ibm.util.AccessControlUtilities;
import org.eclipse.datatools.enablement.ibm.util.ModelHelper;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

 /**
 * @author chetabha
 *
 */
public abstract class DdlBuilder {
    protected final static String ADMIN                = "ADMIN"; //$NON-NLS-1$
	protected final static String NEWLINE              = System.getProperty("line.separator"); //$NON-NLS-1$
    protected final static String UNDERSCORE           = "_"; //$NON-NLS-1$
    protected final static String EMPTY_STRING         = ""; //$NON-NLS-1$
    protected final static String DOT                  = "."; //$NON-NLS-1$
    protected final static String SPACE                = " "; //$NON-NLS-1$
    protected final static String COMMA                = ","; //$NON-NLS-1$
    protected final static String SINGLE_QUOTE         = "'"; //$NON-NLS-1$
    protected final static String DOUBLE_QUOTE         = "\""; //$NON-NLS-1$
    protected final static String TAB                  = "\t"; //$NON-NLS-1$
    protected final static String LEFT_PARENTHESIS     = "("; //$NON-NLS-1$
    protected final static String RIGHT_PARENTHESIS    = ")"; //$NON-NLS-1$
    protected final static String LEFT_SQUARE     	   = "["; //$NON-NLS-1$
    protected final static String RIGHT_SQUARE		   = "]"; //$NON-NLS-1$
    protected final static String COLON                = ":"; //$NON-NLS-1$
    protected final static String DROP                 = "DROP"; //$NON-NLS-1$
    protected final static String REMOVE               = "REMOVE"; //$NON-NLS-1$
    protected final static String CREATE               = "CREATE"; //$NON-NLS-1$
    protected final static String ALTER                = "ALTER"; //$NON-NLS-1$
    protected final static String ADD                  = "ADD"; //$NON-NLS-1$
    protected final static String DELETE               = "DELETE"; //$NON-NLS-1$
    protected final static String UPDATE               = "UPDATE"; //$NON-NLS-1$
    protected final static String CASCADE              = "CASCADE"; //$NON-NLS-1$
    protected final static String CASCADED             = "CASCADED"; //$NON-NLS-1$
    protected final static String LOCAL                = "LOCAL"; //$NON-NLS-1$
    protected final static String OPTION               = "OPTION"; //$NON-NLS-1$
    protected final static String RESTRICT             = "RESTRICT"; //$NON-NLS-1$
    protected final static String NULL                 = "NULL"; //$NON-NLS-1$
    protected final static String NOT                  = "NOT"; //$NON-NLS-1$
    protected final static String DEFAULT              = "DEFAULT"; //$NON-NLS-1$
    protected final static String SET                  = "SET"; //$NON-NLS-1$
    protected final static String DATABASE             = "DATABASE"; //$NON-NLS-1$
    protected final static String DATA_TYPE            = "DATA TYPE";
    protected final static String TRIGGER              = "TRIGGER"; //$NON-NLS-1$
    protected final static String TABLE                = "TABLE"; //$NON-NLS-1$
    protected final static String COLUMN               = "COLUMN";
    protected final static String TABLESPACE           = "TABLESPACE"; //$NON-NLS-1$
    protected final static String VIEW                 = "VIEW"; //$NON-NLS-1$
    protected final static String INDEX                = "INDEX"; //$NON-NLS-1$
    protected final static String ROUTINE              = "ROUTINE"; //$NON-NLS-1$
    protected final static String PROCEDURE            = "PROCEDURE"; //$NON-NLS-1$
    protected final static String FUNCTION             = "FUNCTION"; //$NON-NLS-1$
    protected final static String METHOD               = "METHOD"; //$NON-NLS-1$
    protected final static String SEQUENCE             = "SEQUENCE"; //$NON-NLS-1$
    protected final static String CONSTRAINT           = "CONSTRAINT"; //$NON-NLS-1$
    protected final static String UNIQUE               = "UNIQUE"; //$NON-NLS-1$
    protected final static String CHECK                = "CHECK"; //$NON-NLS-1$
    protected final static String TYPE                 = "TYPE"; //$NON-NLS-1$
    protected final static String ON                   = "ON"; //$NON-NLS-1$
    protected final static String FOREIGN_KEY          = "FOREIGN KEY"; //$NON-NLS-1$
    protected final static String REFERENCES           = "REFERENCES"; //$NON-NLS-1$
    protected final static String PRIMARY_KEY          = "PRIMARY KEY"; //$NON-NLS-1$
    protected final static String DEFERRABLE           = "DEFERRABLE"; //$NON-NLS-1$
    protected final static String DEFERRED             = "DEFERRED"; //$NON-NLS-1$
    protected final static String INITIALLY            = "INITIALLY"; //$NON-NLS-1$
    protected final static String ALIAS                = "ALIAS"; //$NON-NLS-1$
    protected final static String AS                   = "AS"; //$NON-NLS-1$
    protected final static String FOR                  = "FOR"; //$NON-NLS-1$
    protected final static String DISTINCT             = "DISTINCT"; //$NON-NLS-1$
    protected final static String STRUCTURED           = "STRUCTURED"; //$NON-NLS-1$
    protected final static String LONG                 = "LONG"; //$NON-NLS-1$
    protected final static String BLOB                 = "BLOB"; //$NON-NLS-1$
    protected final static String DBCLOB               = "DBCLOB"; //$NON-NLS-1$
    protected final static String CLOB                 = "CLOB"; //$NON-NLS-1$
    protected final static String VARCHAR              = "VARCHAR"; //$NON-NLS-1$
    protected final static String WITH                 = "WITH"; //$NON-NLS-1$
    protected final static String COMPARISONS          = "COMPARISONS"; //$NON-NLS-1$
    protected final static String DATALINK             = "DATALINK"; //$NON-NLS-1$
    protected final static String VARGRAPHIC           = "VARGRAPHIC"; //$NON-NLS-1$
    protected final static String AFTER                = "AFTER"; //$NON-NLS-1$
    protected final static String BEFORE               = "BEFORE"; //$NON-NLS-1$
    protected final static String INSTEAD_OF           = "INSTEAD OF"; //$NON-NLS-1$
    protected final static String INSERT               = "INSERT"; //$NON-NLS-1$
    protected final static String NO                   = "NO"; //$NON-NLS-1$
    protected final static String OF                   = "OF"; //$NON-NLS-1$
    protected final static String REFERENCING          = "REFERENCING"; //$NON-NLS-1$
    protected final static String NEW                  = "NEW"; //$NON-NLS-1$
    protected final static String OLD                  = "OLD"; //$NON-NLS-1$
    protected final static String NEW_TABLE            = "NEW_TABLE"; //$NON-NLS-1$
    protected final static String OLD_TABLE            = "OLD_TABLE"; //$NON-NLS-1$
    protected final static String EACH                 = "EACH"; //$NON-NLS-1$
    protected final static String ROW                  = "ROW"; //$NON-NLS-1$
    protected final static String STATEMENT            = "STATEMENT"; //$NON-NLS-1$
    protected final static String WHEN                 = "WHEN"; //$NON-NLS-1$
    protected final static String SCHEMA               = "SCHEMA"; //$NON-NLS-1$
    protected final static String AUTHORIZATION        = "AUTHORIZATION"; //$NON-NLS-1$
    protected final static String COMMENT              = "COMMENT"; //$NON-NLS-1$
    protected final static String IS                   = "IS"; //$NON-NLS-1$
    protected final static String GRANT                = "GRANT"; //$NON-NLS-1$
    protected final static String REVOKE               = "REVOKE"; //$NON-NLS-1$
    protected final static String TO               	   = "TO"; //$NON-NLS-1$
    protected final static String FROM                 = "FROM"; //$NON-NLS-1$
    protected final static String ROLE                 = "ROLE"; //$NON-NLS-1$
    protected final static String USER                 = "USER"; //$NON-NLS-1$
    protected final static String GROUP                = "GROUP"; //$NON-NLS-1$
	protected final static String NO_ACTION			   = "NO ACTION";  //$NON-NLS-1$
	protected final static String ZERO_STRING    	   = "0";  //$NON-NLS-1$
	protected final static String RENAME           	   = "RENAME"; //$NON-NLS-1$
	protected static final String WHERE				   = "WHERE"; //$NON-NLS-1$
	protected static final String EQUALS			   = "="; //$NON-NLS-1$
	protected static final String COMMENT_DELIMITER    = "--"; //$NON-NLS-1$
    protected static final String LOCK                 = "LOCK"; //$NON-NLS-1$
    protected static final String OR                   = "OR"; //$NON-NLS-1$
    protected static final String USING                = "USING"; //$NON-NLS-1$

    private static RoutineDdlBuilder routineDdlBuilder = null;
    private IEngineeringCallBack callback = null;
    private IEngineeringCallBack dummyCallback = null;
    
    
    public void setEngineeringCallBack(IEngineeringCallBack callback) {
    	this.callback = callback;
    }

    public IEngineeringCallBack getEngineeringCallBack() {
    	if (this.callback != null) {
    		return this.callback;
    	} else{
    		return this.getDummyEngineeringCallBack();
    	}
    }
    
    public String dropTrigger(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + TRIGGER + SPACE + getName(trigger, quoteIdentifiers, qualifyNames);
    }

    public String dropProcedure(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {
    	Database database = ModelHelper.getDatabase(procedure.getSchema());
    	RoutineDdlBuilder routineDdlBuilder = DdlBuilder.getRoutineDdlBuilder(database.getVendor(),database.getVersion());
    	if (routineDdlBuilder != null) {
    		String ddl = routineDdlBuilder.buildDropRoutineStatement(procedure,quoteIdentifiers,qualifyNames);
    		if (ddl != null) return ddl;
    	}
   		return DROP + SPACE + PROCEDURE + SPACE + getName(procedure, quoteIdentifiers, qualifyNames);
    }
    	
    public String dropFunction(UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames) {
    	Database database = ModelHelper.getDatabase(function.getSchema());
    	RoutineDdlBuilder routineDdlBuilder = DdlBuilder.getRoutineDdlBuilder(database.getVendor(),database.getVersion());
    	if (routineDdlBuilder != null) {
    		String ddl = routineDdlBuilder.buildDropRoutineStatement(function,quoteIdentifiers,qualifyNames);
    		if (ddl!= null) return ddl;
    	}
   		return DROP + SPACE + FUNCTION + SPACE + getName(function, quoteIdentifiers, qualifyNames);
    }

    public String dropView(ViewTable view, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + VIEW + SPACE + getName(view, quoteIdentifiers, qualifyNames);
    }

    public String dropTableConstraint(TableConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames) {
        return ALTER + SPACE + this.getOwnerType(constraint) + SPACE + getName(constraint.getBaseTable(), quoteIdentifiers, qualifyNames)
        	+ " DROP CONSTRAINT " + getName(constraint, quoteIdentifiers,qualifyNames); //$NON-NLS-1$
    }
    
    public String dropIndex(Index index, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + INDEX + SPACE + getName(index, quoteIdentifiers, qualifyNames);
    }

    public String dropTable(BaseTable table, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames);
    }

    public String dropSequence(Sequence sequence, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + SEQUENCE + SPACE + getName(sequence, quoteIdentifiers, qualifyNames);
    }
    
    public String dropSchema(Schema schema, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + SCHEMA + SPACE + getName(schema, quoteIdentifiers,qualifyNames);
    }

	protected String getCommentString( SQLObject sqlObject )
	{
		String comment = sqlObject.getDescription();
		if ( comment == null || comment.length() == 0 )
		{
			comment = EMPTY_STRING;
		}
		return comment;
	}
	
    public String commentOn(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {
        Database database = ModelHelper.getDatabase(procedure.getSchema());
        RoutineDdlBuilder routineDdlBuilder = DdlBuilder.getRoutineDdlBuilder(database.getVendor(),database.getVersion());
        if (routineDdlBuilder != null) {
            String ddl = routineDdlBuilder.buildCommentOnStatement(procedure,quoteIdentifiers,qualifyNames);
            if (ddl != null) return ddl;
        }

        String comment = procedure.getDescription();
        if(comment == null || comment.length() == 0) {
            return null;
        }

        String name = getName(procedure, quoteIdentifiers, qualifyNames);

        return COMMENT + SPACE + ON + SPACE + PROCEDURE + SPACE + name + SPACE + IS
            + NEWLINE + this.getSingleQuotedString(comment);
    }
    
    public String commentOn(UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames) {
        Database database = ModelHelper.getDatabase(function.getSchema());
        RoutineDdlBuilder routineDdlBuilder = DdlBuilder.getRoutineDdlBuilder(database.getVendor(),database.getVersion());
        if (routineDdlBuilder != null) {
            String ddl = routineDdlBuilder.buildCommentOnStatement(function,quoteIdentifiers,qualifyNames);
            if (ddl != null) return ddl;
        }

        String comment = function.getDescription();
        if(comment == null || comment.length() == 0) {
            return null;
        }

        String functionName = getName(function, quoteIdentifiers, qualifyNames);

        return COMMENT + SPACE + ON + SPACE + FUNCTION + SPACE + functionName + SPACE + IS
            + NEWLINE + this.getSingleQuotedString(comment);
    }
    
    public String commentOn(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames) {
        String comment = trigger.getDescription();
        if(comment == null || comment.length() == 0) {
            return null;
        }

        String name = getName(trigger, quoteIdentifiers, qualifyNames);

        return COMMENT + SPACE + ON + SPACE + TRIGGER + SPACE + name + SPACE + IS
            + NEWLINE + this.getSingleQuotedString(comment);
    }
    
    public String commentOn(TableConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames) {
    	return commentOn(constraint, quoteIdentifiers, qualifyNames, false);
    }
    
    public String commentOn(TableConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames, boolean allowEmptyComment) {
        String comment = constraint.getDescription();
        if(comment == null || comment.length() == 0) {
        	if (allowEmptyComment) {
            	comment = EMPTY_STRING;
            } else {
            	return null;
            }
        }
        String constraintName = constraint.getName();
        String tableName = constraint.getBaseTable().getName();
        String schemaName = constraint.getBaseTable().getSchema().getName();

        if(quoteIdentifiers) {
            constraintName = this.getDoubleQuotedString(constraintName);
            tableName = this.getDoubleQuotedString(tableName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }
        if(qualifyNames) {
            constraintName = schemaName + DOT + tableName + DOT + constraintName;
        }
        else {
            constraintName = tableName + DOT + constraintName;            
        }

        return COMMENT + SPACE + ON + SPACE + CONSTRAINT + SPACE + constraintName + SPACE + IS
            + NEWLINE + this.getSingleQuotedString(comment);
    }
    
    public String commentOn(Index index, boolean quoteIdentifiers, boolean qualifyNames) {
        String comment = index.getDescription();

        if(comment == null || comment.length() == 0) {
            return null;
        }

        String indexName = getName(index, quoteIdentifiers, qualifyNames);

        return COMMENT + SPACE + ON + SPACE + INDEX + SPACE + indexName + SPACE + IS
            + NEWLINE + this.getSingleQuotedString(comment);
    }
    
    public String commentOn(Sequence sequence, boolean quoteIdentifiers, boolean qualifyNames) {
    	return commentOn(sequence, quoteIdentifiers, qualifyNames, false);
    }
    
    public String commentOn(Sequence sequence, boolean quoteIdentifiers, boolean qualifyNames, boolean allowEmptyComment) {
        String comment = sequence.getDescription();
        if(comment == null || comment.length() == 0) {
        	if (allowEmptyComment) {
            	comment = EMPTY_STRING;
            } else {
            	return null;
            }
        }

        String name = getName(sequence, quoteIdentifiers, qualifyNames);

        return COMMENT + SPACE + ON + SPACE + SEQUENCE + SPACE + name + SPACE + IS
            + NEWLINE + this.getSingleQuotedString(comment);
    }
    
    public String commentOn(Schema schema, boolean quoteIdentifiers, boolean qualifyNames) {
        String comment = schema.getDescription();
        if(comment == null || comment.length() == 0) {
            return null;
        }

        return COMMENT + SPACE + ON + SPACE + SCHEMA + SPACE + getName(schema, quoteIdentifiers,qualifyNames) //@d00058820gs
            + SPACE + IS + NEWLINE + this.getSingleQuotedString(comment);
    }
    
    public String commentOn(StructuredUserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames) {
        String comment = type.getDescription();
        if(comment == null || comment.length() == 0) {
            return null;
        }

        String name = getName(type, quoteIdentifiers, qualifyNames);

        return COMMENT + SPACE + ON + SPACE + STRUCTURED + SPACE + TYPE + SPACE + name + SPACE + IS
            + NEWLINE + this.getSingleQuotedString(comment);
    }
    
    public String commentOn(Table table, boolean quoteIdentifiers, boolean qualifyNames) {
    	return  commentOn(table, quoteIdentifiers, qualifyNames, false);
    }
    
    public String commentOn(Table table, boolean quoteIdentifiers, boolean qualifyNames, boolean allowEmptyComment) {
        String comment = table.getDescription();
        if(comment == null || comment.length() == 0) {
        	if (allowEmptyComment) {
            	comment = EMPTY_STRING;
            } else {
            	return null;
            }
        }

        String name = getName(table, quoteIdentifiers, qualifyNames);

        return COMMENT + SPACE + ON + SPACE + TABLE + SPACE + name + SPACE + IS
            + NEWLINE + this.getSingleQuotedString(comment);
    }
	//@ed00058820gs
	
    public String dropUserDefinedType(UserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + TYPE + SPACE + getName(type, quoteIdentifiers, qualifyNames);
    }
    
    public String createSchema(Schema schema, boolean quoteIdentifiers,boolean qualifyNames) {
        String statement = CREATE + SPACE + SCHEMA + SPACE + AUTHORIZATION + SPACE + getName(schema, quoteIdentifiers,qualifyNames);
    	return statement;
    }    
    public String createTable(Table table, boolean quoteIdentifiers, boolean qualifyNames) {
        String statement = CREATE + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames)
        	+ SPACE + LEFT_PARENTHESIS + NEWLINE ;
        Iterator it = table.getColumns().iterator();
        while(it.hasNext()) {
            Column column = (Column) it.next();
            statement += TAB + TAB + getColumnString(column, quoteIdentifiers,qualifyNames);
            if(it.hasNext()) {
                statement += COMMA;                
            }
            statement += NEWLINE;                
        }
        statement += TAB + RIGHT_PARENTHESIS;
        
        return statement;
    }
    
    public String alterTableAddColumn(Column column,  boolean quoteIdentifiers, boolean qualifyNames) {
    	Table table = column.getTable();
    	if(table instanceof BaseTable) {
	        String statement = ALTER + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames)
	    		+ SPACE +addColumnClause(column, quoteIdentifiers,qualifyNames); //$NON-NLS-1$
	        return statement;
    	}
    	return null;
    }
    
    public String addColumnClause(Column column,  boolean quoteIdentifiers, boolean qualifyNames)
    {
    	String clause=ADD + SPACE + COLUMN +SPACE +getAlterTableAddColumnColumnString(column, quoteIdentifiers,qualifyNames);
    	return clause;
    }

    public String alterTableDropColumn(Column column,  boolean quoteIdentifiers, boolean qualifyNames, CoreDeltaDdlGenerator deltaGen) {
    	Table table = deltaGen.getOldContainer(column);
    	if(table instanceof BaseTable) {
	        String statement = ALTER + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames)
	    		+ SPACE + dropColumnClause(column, quoteIdentifiers, qualifyNames, deltaGen);
	        return statement;
    	}
    	return null;
    }
    
    public String dropColumnClause(Column column,  boolean quoteIdentifiers, boolean qualifyNames, CoreDeltaDdlGenerator deltaGen)
    {
    	String clause=DROP + SPACE + COLUMN + SPACE + getColumnNameString(column, quoteIdentifiers,qualifyNames);
    	return clause;
    }

    public String alterTableWithClauses(Table table, Vector<String> clauses, boolean quoteIdentifiers, boolean qualifyNames)
    {
		String statement=ALTER + SPACE +TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames) + SPACE;
		boolean first=true;
		for (String clause: clauses)
		{
			if (!first)
			{
				statement += NEWLINE + TAB;
			}
			statement += clause;
			first=false;
		}
		return statement;
    }
    
    public String alterTableAlterColumnDataType(Column column,  boolean quoteIdentifiers, boolean qualifyNames) {
    	Table table = column.getTable();

    	if(table instanceof BaseTable) {
	        String statement = ALTER + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames)
	    		+ SPACE + alterColumnDatatypeClause(column, quoteIdentifiers, qualifyNames); //$NON-NLS-1$
	        return statement;
    	}
    	return null;
    }
    
    public String alterColumnDatatypeClause(Column column, boolean quoteIdentifiers, boolean qualifyNames)
    {
    	String columnName = column.getName();
    	
        if(quoteIdentifiers) {
            columnName = this.getDoubleQuotedString(columnName);
        }
            	
        String columnString = ALTER + SPACE + COLUMN + SPACE + columnName + SPACE + 
       // SET + SPACE + DATA_TYPE  + SPACE + getDataTypeString(column, column.getTable().getSchema(),qualifyNames); //$NON-NLS-1$
       // fix for issue wsdbu00985096
        SET + SPACE + DATA_TYPE  + SPACE + getDataTypeString(column,column.getTable().getSchema(),quoteIdentifiers,qualifyNames); //$NON-NLS-1$
        return columnString;
    }

    public String alterTableAlterColumnDefaultValue(Column column,  boolean quoteIdentifiers, boolean qualifyNames) {
    	Table table = column.getTable();
    	if (!(table instanceof BaseTable)) return null;
        String statement = ALTER + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames)
        + SPACE + alterColumnDefaultValueClause(column, quoteIdentifiers, qualifyNames);
    	return statement;
    }
    
    public String alterColumnDefaultValueClause(Column column,  boolean quoteIdentifiers, boolean qualifyNames)
    {
    	String columnName = column.getName();
        if(quoteIdentifiers) {
            columnName = this.getDoubleQuotedString(columnName);
        }
        
        String clause=ALTER + SPACE + COLUMN +SPACE + columnName; //$NON-NLS-1$
    	String defaultValue = column.getDefaultValue();
    	if (defaultValue == null || defaultValue.equals("")) { //$NON-NLS-1$
    		clause += " DROP DEFAULT "; //$NON-NLS-1$
    	} else {
    		clause += " SET DEFAULT " + getDefaultValue(column); //$NON-NLS-1$
    	}
        
    	return clause;
    }
    
    public String getUpdateColumnNullValues(Column column, String defaultValue, boolean quoteIdentifiers, boolean qualifyNames)
    {
    	String updateStatement = null;
    	String formattedValue = formatDefaultValue(column.getContainedType(), defaultValue);
    	if (formattedValue != null && !formattedValue.equals("")) {
    		String columnName = column.getName();
    		String tableName = getName(column.getTable(), quoteIdentifiers, qualifyNames);
    		if (quoteIdentifiers) {
    			columnName = this.getDoubleQuotedString(columnName);
    		}
    		updateStatement = COMMENT_DELIMITER + SPACE + UPDATE + SPACE + tableName + SPACE +
    		SET + SPACE + columnName + EQUALS + formattedValue + SPACE +
    		WHERE + SPACE + columnName + SPACE + IS + SPACE + NULL;
    	}
    	return updateStatement;
    }

    public String alterTableAlterColumnExpression(Column column,  boolean quoteIdentifiers, boolean qualifyNames) {
    	Table table = column.getTable();
    	if(table instanceof BaseTable)  return null;
    	String columnName = column.getName();
        if(quoteIdentifiers) {
            columnName = this.getDoubleQuotedString(columnName);
        }

        String statement = ALTER + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames)
        				+ " ALTER COLUMN " + columnName ; //$NON-NLS-1$
        ValueExpression v = column.getGenerateExpression();
        if (v != null && !"".equals(v.getSQL())) {
        	statement +=" SET EXPRESSION AS " + v.getSQL();   //$NON-NLS-1$
        } else {
        	statement += " DROP EXPRESSION"; //$NON-NLS-1$
        }
        
        return statement;
    }
    
    public String alterTableAlterColumnIdentity(Column column,  boolean quoteIdentifiers, boolean qualifyNames) {
    	return null;
    }
    
    public String alterTableAlterColumnNullable(Column column,  boolean quoteIdentifiers, boolean qualifyNames) {
    	Table table = column.getTable();
    	if (!(table instanceof BaseTable)) return null;
    	
        String statement = ALTER + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames)
		+ SPACE + alterNullableClause(column, quoteIdentifiers, qualifyNames);
    	return statement;
    }
    
    public String alterNullableClause(Column column,  boolean quoteIdentifiers, boolean qualifyNames)
    {
    	String columnName = column.getName();
        if(quoteIdentifiers) {
            columnName = this.getDoubleQuotedString(columnName);
        }
    	String clause = ALTER + SPACE + COLUMN + SPACE + columnName;
        
    	if(column.isNullable()) {
	        clause += " DROP NOT NULL"; //$NON-NLS-1$
    	} else {
	        clause += " SET NOT NULL"; //$NON-NLS-1$
    	}
    	return clause;
    }

    public String alterTableAlterProperties(Table table, int propertyType, boolean quoteIdentifiers, boolean qualifyNames) {
        return null;
    }

    public String renameTable(Table table, String oldTableName, boolean quoteIdentifiers, boolean qualifyNames) {
    	if (table == null) return null;
        String schemaName = table.getSchema() != null ? table.getSchema().getName() : "nullschema";

        if(quoteIdentifiers) {
            oldTableName = this.getDoubleQuotedString(oldTableName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if(qualifyNames) {
            oldTableName = schemaName + DOT + oldTableName;
        }

    	return RENAME + SPACE + oldTableName + SPACE + TO + SPACE + getName(table, quoteIdentifiers, false);
    }

    public String createIndex(Index index, boolean quoteIdentifiers, boolean qualifyNames) {
        String statement = CREATE + SPACE;
        statement += getIndexTypePrefix(index);
        statement += INDEX + SPACE + getName(index, quoteIdentifiers, qualifyNames);
        statement += NEWLINE + TAB + ON + SPACE	+ getName(index.getTable(), quoteIdentifiers, qualifyNames);
        statement += getIndexKeyColumns(index, quoteIdentifiers);
        return statement;
    }
	
    public String addCheckConstraint(CheckConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames) {
    	String checkCondition = this.getAddCheckConstraintClause(constraint, quoteIdentifiers,qualifyNames);
    	if (checkCondition == null) return null;
    	return ALTER + SPACE + this.getOwnerType(constraint) + SPACE + getName(constraint.getBaseTable(), quoteIdentifiers, qualifyNames)
        	+ SPACE + checkCondition;
    }

    public String addUniqueConstraint(UniqueConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames) {
        String statement = ALTER + SPACE + this.getOwnerType(constraint) + SPACE + getName(constraint.getBaseTable(), quoteIdentifiers, qualifyNames) + SPACE;
        String uniqueClause = this.getAddUniqueConstraintClause(constraint, quoteIdentifiers,qualifyNames);
        if (uniqueClause == null) return null; //report error
        statement += uniqueClause;
        
        return statement;
    }

    public String addForeignKey(ForeignKey foreignKey, boolean quoteIdentifiers, boolean qualifyNames) {
        UniqueConstraint uniqueConstraint = foreignKey.getUniqueConstraint();
        Index index = foreignKey.getUniqueIndex();
        Table parentTable = null;
        String parentKey = null;
        if(uniqueConstraint != null) {
            parentTable = uniqueConstraint.getBaseTable();
            parentKey = this.getKeyColumns(uniqueConstraint,quoteIdentifiers);
            if (parentKey == null) {
                // TODO report error
                return null;
            }
        }
        else if(index != null) {
        	parentTable = index.getTable();
            parentKey = this.getParentKeyColumns(index, quoteIdentifiers);
        }
        if(parentTable == null) {
            // TODO report error
            return null;
        }

        String statement = ALTER + SPACE + this.getOwnerType(foreignKey) + SPACE + getName(foreignKey.getBaseTable(), quoteIdentifiers, qualifyNames)
        	+ SPACE + ADD + SPACE + CONSTRAINT + SPACE + getName(foreignKey, quoteIdentifiers,qualifyNames) + SPACE + FOREIGN_KEY;
        statement += this.getKeyColumns(foreignKey,quoteIdentifiers);
        statement += NEWLINE + TAB + REFERENCES + SPACE + getName(parentTable, quoteIdentifiers, qualifyNames);
        statement += parentKey ;
		
        statement += this.getDeleteReferentialActionAction(foreignKey);
        statement += this.getUpdateReferentialActionAction(foreignKey);

        if(foreignKey.isDeferrable()) {
            statement += NEWLINE + TAB + getDeferrableClause(foreignKey);
        }
        return statement;
    }

    public String createRole(Role role, boolean quoteIdentifiers) {
        String dbName = role.getName();
        if(quoteIdentifiers) {
        	dbName = this.getDoubleQuotedString(dbName);
        }
        String statement = "CREATE ROLE " + dbName; //$NON-NLS-1$ //$NON-NLS-2$

        return statement;
    }

    public String dropRole(Role role, boolean quoteIdentifiers) {
        String dbName = role.getName();
        if(quoteIdentifiers) {
        	dbName = this.getDoubleQuotedString(dbName);
        }
        String statement = "DROP ROLE " + dbName; //$NON-NLS-1$ //$NON-NLS-2$

        return statement;
    }

    public String[] grantOn(Database database, boolean quoteIdentifiers) {
    	return this.getGrantString(database,quoteIdentifiers, false);
    }
    
    public String[] grantOn(Schema schema, boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getGrantString(schema,quoteIdentifiers,qualifyNames);
    }
    
    public String[] grantOn(Table table, boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getGrantString(table,quoteIdentifiers,qualifyNames);
    }

    public String[] grantOn(UserDefinedFunction function,boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getGrantString(function,quoteIdentifiers,qualifyNames);
    }

    public String[] grantOn(Procedure procedure,boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getGrantString(procedure,quoteIdentifiers,qualifyNames);
    }

    public String[] grantOn(Sequence sequence, boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getGrantString(sequence,quoteIdentifiers,qualifyNames);
    }

    public String[] grantOn(Index index, boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getGrantString(index,quoteIdentifiers,qualifyNames);
    }

    public String[] grantOn(DistinctUserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getGrantString(type,quoteIdentifiers,qualifyNames);
    }

    public String[] grantOn(Role role, boolean quoteIdentifiers) {
    	Vector authVec = new Vector();
    	for (Iterator iter = role.getRoleAuthorization().iterator(); iter.hasNext();) {
    		authVec.add(this.getGrantRoleAuthorizationString((RoleAuthorization)iter.next(), quoteIdentifiers));
    	}
    	String[] authStr = new String[authVec.size()];
    	authVec.copyInto(authStr);
    	return authStr;
    }

    public String getGrantRoleAuthorizationStatement(RoleAuthorization roleAuth, boolean quoteIdentifiers) {
    	return getGrantRoleAuthorizationString(roleAuth,quoteIdentifiers);
    }

    public String[] revokeFrom(Database database, boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getRevokeString(database,quoteIdentifiers,false);
    }

    public String[] revokeFrom(Schema schema, boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getRevokeString(schema,quoteIdentifiers,qualifyNames);
    }

    public String[] revokeFrom(Table table, boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getRevokeString(table,quoteIdentifiers,qualifyNames);
    }

    public String[] revokeFrom(UserDefinedFunction function,boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getRevokeString(function,quoteIdentifiers,qualifyNames);
    }

    public String[] revokeFrom(Procedure procedure,boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getRevokeString(procedure,quoteIdentifiers,qualifyNames);
    }

    public String[] revokeFrom(Sequence sequence, boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getRevokeString(sequence,quoteIdentifiers,qualifyNames);
    }

    public String[] revokeFrom(Index index, boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getRevokeString(index,quoteIdentifiers,qualifyNames);
    }

    public String[] revokeFrom(DistinctUserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getRevokeString(type,quoteIdentifiers,qualifyNames);
    }

    public String[] revokeFrom(Role role, boolean quoteIdentifiers) {
    	Vector authVec = new Vector();
    	for (Iterator iter = role.getRoleAuthorization().iterator(); iter.hasNext();) {
    		authVec.add(this.getRevokeRoleAuthorizationString((RoleAuthorization)iter.next(), quoteIdentifiers));
    	}
    	String[] authStr = new String[authVec.size()];
    	authVec.copyInto(authStr);
    	return authStr;
    }
    
	public String getRevokeRoleAuthorizationStatement(RoleAuthorization roleAuth, boolean quoteIdentifiers) {
    	return 	getRevokeRoleAuthorizationString(roleAuth,quoteIdentifiers);
}

    public String createTableCodetemplate(Table table, int appliedType, boolean isProlog, Map patternMap){
    	if (!patternMap.containsKey("Table")) return null;
    	CodeTemplateContextPattern pattern = (CodeTemplateContextPattern) patternMap.get("Table");

    	if ((pattern.getAppliedType() & appliedType) != appliedType)  return null;

		final DatabaseDefinition databaseDefinition = IBMPluginActivator.getInstance().getDatabaseDefinitionRegistry().getDefinition(ModelHelper.getDatabase(table.getSchema()));
		String terminator = databaseDefinition.getSQLTerminationCharacter();
		
    	if (appliedType == DdlGenerationUtility.GENERATE_CREATE_PATTERN) {
    		if (isProlog) {
	    		String prolog = pattern.getCreateProlog();
	    		if (prolog != null) {
	    			prolog = prolog.replaceAll("\\{schema\\}",table.getSchema().getName());
	    			prolog = prolog.replaceAll("\\{table\\}",table.getName());
	    			prolog = prolog.replaceAll("\\{terminator\\}",terminator);
	    			return prolog;
	    		};
    		} else {
	    		String postlog = pattern.getCreatePostlog();
	    		if (postlog != null) {
	    			postlog = postlog.replaceAll("\\{schema\\}",table.getSchema().getName());
	    			postlog = postlog.replaceAll("\\{table\\}",table.getName());
	    			postlog = postlog.replaceAll("\\{terminator\\}",terminator);
	    			return postlog;
	    		}
    		}
    	} else if (appliedType == DdlGenerationUtility.GENERATE_DROP_PATTERN) {
    		if (isProlog) {
	    		String prolog = pattern.getDropProlog();
	    		if (prolog != null) {
	    			prolog = prolog.replaceAll("\\{schema\\}",table.getSchema().getName());
	    			prolog = prolog.replaceAll("\\{table\\}",table.getName());
	    			prolog = prolog.replaceAll("\\{terminator\\}",terminator);
	    			return prolog;
	       		}
    		} else {
	    		String postlog = pattern.getDropPostlog();
	    		if (postlog != null) {
	    			postlog = postlog.replaceAll("\\{schema\\}",table.getSchema().getName());
	    			postlog = postlog.replaceAll("\\{table\\}",table.getName());
	    			postlog = postlog.replaceAll("\\{terminator\\}",terminator);
	    			return postlog;
	    		}
    		}
    	}
    	return null;
    }
    
    public String createRoutineCodetemplate(Routine routine, int appliedType, boolean isProlog, Map patternMap){
    	if (!patternMap.containsKey("Routine")) return null;
    	CodeTemplateContextPattern pattern = (CodeTemplateContextPattern) patternMap.get("Routine");

    	if ((pattern.getAppliedType() & appliedType) != appliedType)  return null;

		final DatabaseDefinition databaseDefinition = IBMPluginActivator.getInstance().getDatabaseDefinitionRegistry().getDefinition(ModelHelper.getDatabase(routine.getSchema()));
		String terminator = databaseDefinition.getSQLTerminationCharacter();
		
    	if (appliedType == DdlGenerationUtility.GENERATE_CREATE_PATTERN) {
    		if (isProlog) {
	    		String prolog = pattern.getCreateProlog();
	    		if (prolog != null) {
	    			prolog = prolog.replaceAll("\\{schema\\}",routine.getSchema().getName());
	    			prolog = prolog.replaceAll("\\{routine\\}",routine.getName());
	    			prolog = prolog.replaceAll("\\{terminator\\}",terminator);
	    			return prolog;
	    		};
    		} else {
	    		String postlog = pattern.getCreatePostlog();
	    		if (postlog != null) {
	    			postlog = postlog.replaceAll("\\{schema\\}",routine.getSchema().getName());
	    			postlog = postlog.replaceAll("\\{routine\\}",routine.getName());
	    			postlog = postlog.replaceAll("\\{terminator\\}",terminator);
	    			return postlog;
	    		}
    		}
    	} else if (appliedType == DdlGenerationUtility.GENERATE_DROP_PATTERN) {
    		if (isProlog) {
	    		String prolog = pattern.getDropProlog();
	    		if (prolog != null) {
	    			prolog = prolog.replaceAll("\\{schema\\}",routine.getSchema().getName());
	    			prolog = prolog.replaceAll("\\{routine\\}",routine.getName());
	    			prolog = prolog.replaceAll("\\{terminator\\}",terminator);
	    			return prolog;
	       		}
    		} else {
	    		String postlog = pattern.getDropPostlog();
	    		if (postlog != null) {
	    			postlog = postlog.replaceAll("\\{schema\\}",routine.getSchema().getName());
	    			postlog = postlog.replaceAll("\\{routine\\}",routine.getName());
	    			postlog = postlog.replaceAll("\\{terminator\\}",terminator);
	    			return postlog;
	    		}
    		}
    	}
    	return null;
    }

    public String createConstraintCodetemplate(TableConstraint constraint, int appliedType, boolean isProlog, Map patternMap){
    	if (!patternMap.containsKey("Constraint")) return null;
    	CodeTemplateContextPattern pattern = (CodeTemplateContextPattern) patternMap.get("Constraint");

    	if ((pattern.getAppliedType() & appliedType) != appliedType)  return null;

		final DatabaseDefinition databaseDefinition = IBMPluginActivator.getInstance().getDatabaseDefinitionRegistry().getDefinition(ModelHelper.getDatabase(constraint.getBaseTable().getSchema()));
		String terminator = databaseDefinition.getSQLTerminationCharacter();
		
    	if (appliedType == DdlGenerationUtility.GENERATE_CREATE_PATTERN) {
    		if (isProlog) {
	    		String prolog = pattern.getCreateProlog();
	    		if (prolog != null) {
	    			prolog = prolog.replaceAll("\\{schema\\}",constraint.getBaseTable().getSchema().getName());
	    			prolog = prolog.replaceAll("\\{table\\}",constraint.getBaseTable().getName());
	    			prolog = prolog.replaceAll("\\{constraint\\}",constraint.getName());
	    			prolog = prolog.replaceAll("\\{terminator\\}",terminator);
	    			return prolog;
	    		};
    		} else {
	    		String postlog = pattern.getCreatePostlog();
	    		if (postlog != null) {
	    			postlog = postlog.replaceAll("\\{schema\\}",constraint.getBaseTable().getSchema().getName());
	    			postlog = postlog.replaceAll("\\{table\\}",constraint.getBaseTable().getName());
	    			postlog = postlog.replaceAll("\\{constraint\\}",constraint.getName());
	    			postlog = postlog.replaceAll("\\{terminator\\}",terminator);
	    			return postlog;
	    		}
    		}
    	} else if (appliedType == DdlGenerationUtility.GENERATE_DROP_PATTERN) {
    		if (isProlog) {
	    		String prolog = pattern.getDropProlog();
	    		if (prolog != null) {
	    			prolog = prolog.replaceAll("\\{schema\\}",constraint.getBaseTable().getSchema().getName());
	    			prolog = prolog.replaceAll("\\{table\\}",constraint.getBaseTable().getName());
	    			prolog = prolog.replaceAll("\\{constraint\\}",constraint.getName());
	    			prolog = prolog.replaceAll("\\{terminator\\}",terminator);
	    			return prolog;
	       		}
    		} else {
	    		String postlog = pattern.getDropPostlog();
	    		if (postlog != null) {
	    			postlog = postlog.replaceAll("\\{schema\\}",constraint.getBaseTable().getSchema().getName());
	    			postlog = postlog.replaceAll("\\{table\\}",constraint.getBaseTable().getName());
	    			postlog = postlog.replaceAll("\\{constraint\\}",constraint.getName());
	    			postlog = postlog.replaceAll("\\{terminator\\}",terminator);
	    			return postlog;
	    		}
    		}
    	}
    	return null;
    }
    
    public String createViewCodetemplate(ViewTable view, int appliedType, boolean isProlog, Map patternMap){
    	if (!patternMap.containsKey("View")) return null;
    	CodeTemplateContextPattern pattern = (CodeTemplateContextPattern) patternMap.get("View");

    	if ((pattern.getAppliedType() & appliedType) != appliedType)  return null;

		final DatabaseDefinition databaseDefinition = IBMPluginActivator.getInstance().getDatabaseDefinitionRegistry().getDefinition(ModelHelper.getDatabase(view.getSchema()));
		String terminator = databaseDefinition.getSQLTerminationCharacter();
		
    	if (appliedType == DdlGenerationUtility.GENERATE_CREATE_PATTERN) {
    		if (isProlog) {
	    		String prolog = pattern.getCreateProlog();
	    		if (prolog != null) {
	    			prolog = prolog.replaceAll("\\{schema\\}",view.getSchema().getName());
	    			prolog = prolog.replaceAll("\\{view\\}",view.getName());
	    			prolog = prolog.replaceAll("\\{terminator\\}",terminator);
	    			return prolog;
	    		};
    		} else {
	    		String postlog = pattern.getCreatePostlog();
	    		if (postlog != null) {
	    			postlog = postlog.replaceAll("\\{schema\\}",view.getSchema().getName());
	    			postlog = postlog.replaceAll("\\{view\\}",view.getName());
	    			postlog = postlog.replaceAll("\\{terminator\\}",terminator);
	    			return postlog;
	    		}
    		}
    	} else if (appliedType == DdlGenerationUtility.GENERATE_DROP_PATTERN) {
    		if (isProlog) {
	    		String prolog = pattern.getDropProlog();
	    		if (prolog != null) {
	    			prolog = prolog.replaceAll("\\{schema\\}",view.getSchema().getName());
	    			prolog = prolog.replaceAll("\\{view\\}",view.getName());
	    			prolog = prolog.replaceAll("\\{terminator\\}",terminator);
	    			return prolog;
	       		}
    		} else {
	    		String postlog = pattern.getDropPostlog();
	    		if (postlog != null) {
	    			postlog = postlog.replaceAll("\\{schema\\}",view.getSchema().getName());
	    			postlog = postlog.replaceAll("\\{view\\}",view.getName());
	    			postlog = postlog.replaceAll("\\{terminator\\}",terminator);
	    			return postlog;
	    		}
    		}
    	}
    	return null;
    }

    
    protected String getDeferrableClause(Constraint constraint) {
        String clause = null;
        if(constraint.isDeferrable()) {
            clause = DEFERRABLE;
            if(constraint.isInitiallyDeferred()) {
                clause += SPACE + INITIALLY + SPACE + DEFERRED;
            }
        }
        return clause;
    }
    
    protected String getReferentialAction(ReferentialActionType action) {
        if(action == ReferentialActionType.CASCADE_LITERAL) {
            return CASCADE;
        }
        else if(action == ReferentialActionType.RESTRICT_LITERAL) {
            return RESTRICT;
        }
        else if(action == ReferentialActionType.SET_DEFAULT_LITERAL) {
            return SET + SPACE + DEFAULT;
        }
        else if(action == ReferentialActionType.SET_NULL_LITERAL) {
            return SET + SPACE + NULL;
        }
        return "";  //$NON-NLS-1$
    }

    protected String getViewColumnList(ViewTable view, boolean quoteIdentifiers) {
        String columns = null;
        Iterator it = view.getColumns().iterator();
        if(it.hasNext()) {
            Column c = (Column) it.next();
            columns = c.getName();
            if (quoteIdentifiers) {
            	columns = this.getDoubleQuotedString(columns);
            }
        }
        else {
            return null;
        }

        while(it.hasNext()) {
            Column c = (Column) it.next();
            String columnName = c.getName();
            if (quoteIdentifiers) {
            	columnName = this.getDoubleQuotedString(columnName);
            }
            columns += COMMA + SPACE + columnName;
        }
        
        return columns;
    }
    
    protected String getColumnNameString(Column column, boolean quoteIdentifiers,boolean qualifyNames) {
        String columnName = column.getName();
        if(quoteIdentifiers) {
            columnName = this.getDoubleQuotedString(columnName);
        }
        return columnName;
    }
 
    protected String getAlterTableAddColumnColumnString(Column column, boolean quoteIdentifiers,boolean qualifyNames) {
    	return getColumnString(column,quoteIdentifiers,qualifyNames);
    }
    
    protected String getColumnString(Column column, boolean quoteIdentifiers,boolean qualifyNames) {
            String columnName = column.getName();
            if(quoteIdentifiers) {
                columnName = this.getDoubleQuotedString(columnName);
            }
            
        // String columnString = columnName + SPACE + getDataTypeString(column, column.getTable().getSchema(),qualifyNames);
        // fix for issue wsdbu00985096
        String columnString = columnName + SPACE + getDataTypeString(column,column.getTable().getSchema(),quoteIdentifiers,qualifyNames); 
        String defaultValue = this.getDefaultValue(column);
        if (defaultValue != null && !defaultValue.equals(EMPTY_STRING)) {
       		columnString = columnString + SPACE + DEFAULT + SPACE + defaultValue;
        }
    
        if(!column.isNullable()) {
            columnString = columnString + SPACE + NOT + SPACE + NULL;
        }
        
        return columnString;
    }

    protected String getAddUniqueConstraintClause(UniqueConstraint constraint, boolean quoteIdentifiers) {
    	return this.getAddUniqueConstraintClause(constraint, quoteIdentifiers, false);
    }

    protected String getAddUniqueConstraintClause(UniqueConstraint constraint, boolean quoteIdentifiers,boolean qualifyNames) {
        String constraintName = getName(constraint, quoteIdentifiers, qualifyNames);

        String text =  ADD + SPACE + CONSTRAINT + SPACE + constraintName 
        	+ SPACE + getUniqueConstraintType(constraint);
        	
        String keyList = this.getKeyColumns(constraint,quoteIdentifiers);
        
        if (keyList == null){
            //report error
            return null;
        }
        
        text += keyList;

        if(constraint.isDeferrable()) {
            text += SPACE + getDeferrableClause(constraint);
        }
        return text;
    }

    protected String getUniqueConstraintType(UniqueConstraint constraint) {
        if(constraint instanceof PrimaryKey) {
            return PRIMARY_KEY;
        }
        return UNIQUE;
    }

    protected String getAddCheckConstraintClause(CheckConstraint constraint, boolean quoteIdentifiers) {
        String constraintName = getName(constraint, quoteIdentifiers);

        SearchCondition searchCondition = constraint.getSearchCondition();
        if (searchCondition == null) return null;
        String text =  ADD + SPACE + CONSTRAINT + SPACE + constraintName + SPACE + CHECK
        	+ SPACE + LEFT_PARENTHESIS + searchCondition.getSQL() + RIGHT_PARENTHESIS;
        if(constraint.isDeferrable()) {
            text += SPACE + getDeferrableClause(constraint);
        }
        return text;
    }

    protected String getAddCheckConstraintClause(CheckConstraint constraint, boolean quoteIdentifiers,boolean qualifyNames) {
        String constraintName = getName(constraint, quoteIdentifiers,false);

        SearchCondition searchCondition = constraint.getSearchCondition();
        if (searchCondition == null) return null;
        String text =  ADD + SPACE + CONSTRAINT + SPACE + constraintName + SPACE + CHECK
        	+ SPACE + LEFT_PARENTHESIS + searchCondition.getSQL() + RIGHT_PARENTHESIS;
        if(constraint.isDeferrable()) {
            text += SPACE + getDeferrableClause(constraint);
        }
        return text;
    }

    protected String getKeyColumns(ReferenceConstraint constraint, boolean quoteIdentifiers) {
        String columns = NEWLINE + TAB + LEFT_PARENTHESIS;
        Iterator it = constraint.getMembers().iterator();
        if(it.hasNext()) {
            Column c = (Column) it.next();
            String colName = c.getName();
            if (quoteIdentifiers) {
            	colName = this.getDoubleQuotedString(colName);
            }
            columns += colName;
        }
        else {
            // TODO report error
            return null;
        }
        
        while(it.hasNext()) {
            Column c = (Column) it.next();
            String colName = c.getName();
            if (quoteIdentifiers) {
            	colName = this.getDoubleQuotedString(colName);
            }
            columns += COMMA;
            columns += NEWLINE + TAB + SPACE + colName;
        }

        columns += RIGHT_PARENTHESIS;
        return columns;
    }

    protected String getIndexKeyColumns(Index index, boolean quoteIdentifiers) {
        String columns = NEWLINE + TAB + LEFT_PARENTHESIS;
        Iterator it = index.getMembers().iterator();
        if(it.hasNext()) {
        	String keyMemberString = null;
            IndexMember m = (IndexMember) it.next();
            Column column = m.getColumn();
            if (column != null) {
            	keyMemberString = getIndexKeyColumnName(index, column, quoteIdentifiers);
            }
            else {
            	if (m.getExpression() != null) {
            		keyMemberString = m.getExpression().getSql();
            	}
            }
            columns += keyMemberString + this.getIncrementTypeString(m);
        }
        else {
            // TODO report error
            return null;
        }
        
        while(it.hasNext()) {
        	String keyMemberString = null;
            IndexMember m = (IndexMember) it.next();
            Column column = m.getColumn();
            if (column != null) {
            	keyMemberString = getIndexKeyColumnName(index, column, quoteIdentifiers);
            }
            else {
            	if (m.getExpression() != null) {
            		keyMemberString = m.getExpression().getSql();
            	}
            }
            columns += COMMA;
            columns += NEWLINE+ TAB + SPACE + SPACE + keyMemberString +  this.getIncrementTypeString(m);
        }
        columns += RIGHT_PARENTHESIS;
        return columns;
    }

	/**
	 * Get the name of the column for use in the create index statement key members list
	 * 
	 * @param column An index key column
	 * @param quoteIdentifiers Delimit identifiers if true
	 * @return String containing the name of the column
	 */
	protected String getIndexKeyColumnName(Index index, Column column, boolean quoteIdentifiers) {
		String keyMemberString;
		keyMemberString = column.getName();
		if (quoteIdentifiers) {
			keyMemberString = this.getDoubleQuotedString(keyMemberString);
		}
		return keyMemberString;
	}
  
    protected String getParentKeyColumns(Index index, boolean quoteIdentifiers) {
        String columns = NEWLINE + TAB + LEFT_PARENTHESIS;
        Iterator it = index.getMembers().iterator();
        if(it.hasNext()) {
            IndexMember m = (IndexMember) it.next();
            Column column = m.getColumn();
            if (column != null) {
            	String columnName = column.getName();
            	if(quoteIdentifiers) {
            		columnName = this.getDoubleQuotedString(columnName);
            	}
            	columns += columnName;
            }
            else {
            	return null;
            }
        }
        else {
            // TODO report error
            return null;
        }
        
        while(it.hasNext()) {
            IndexMember m = (IndexMember) it.next();
            Column column = m.getColumn();
            if (column != null) {
            	String columnName = column.getName();
            	if (quoteIdentifiers) {
            		columnName = this.getDoubleQuotedString(columnName);
            	}
            	columns += COMMA;
            	columns += NEWLINE + TAB +SPACE + SPACE + columnName;
            }
            else {
            	return null;
            }
        }
        columns += RIGHT_PARENTHESIS;
        return columns;
    }
  
	protected String getRoutineSpecifier(SQLObject obj, boolean quoteIdentifiers, boolean qualifyNames) {
		String name;
		Routine routine = (Routine)obj;
		Schema schema = routine.getSchema();
		name = getName(routine, quoteIdentifiers, qualifyNames);
		name += "("; //$NON-NLS-1$
		Iterator it = routine.getParameters().iterator();
		while (it.hasNext()) {
			Parameter parm = (Parameter)it.next();
			//name += getDataTypeString(parm, schema, qualifyNames) + (it.hasNext()?",":""); //$NON-NLS-1$ //$NON-NLS-2$
			// fix for issue wsdbu00985096
			name += getDataTypeString(parm,schema,quoteIdentifiers,qualifyNames) + (it.hasNext()?",":""); //$NON-NLS-1$ //$NON-NLS-2$
		}
		name += ")"; //$NON-NLS-1$
		return name;
	}

	public String getParameterString(Parameter parameter, Schema schema, boolean qualifyNames) {
		return getDataTypeString(parameter, schema, qualifyNames);
	}
	
	/**
	 * Get the string for the row data type fields. Subclass can override
	 * this method to provide the database manager specific string.
	 * This method was created for the compare and sync to compare row data type fields.
	 * @param type A row data type object
	 * @return The string for the fields of the row data type.
	 */
	public String getRowDataTypeFields(DataType type) {
		return null;
	}
	
	//(JYEH)This method was created for the compare and sync to compare data type strings.
	public String getObjectDataTypeString(TypedElement typedElement) {
		return getDataTypeStringIgnoreDomain(typedElement, null, true, null);
	}

	public String getObjectDataTypeString(TypedElement typedElement, SQLDataType type) {
		return getDataTypeStringIgnoreDomain(typedElement, null, true, type);
	}

	//(JYEH)The default implementation returns the data type string without considering the domains. This is required by
	//the getObjectDataTypeString(TypedElement) method which is used by the compare and sync editor.
	protected String getDataTypeString(TypedElement typedElement, Schema schema,boolean qualifyNames) {
		return getDataTypeStringIgnoreDomain(typedElement, schema, qualifyNames, null);
	}

	private String getDataTypeStringIgnoreDomain(TypedElement typedElement,
			Schema schema, boolean qualifyNames, SQLDataType type) {
		SQLDataType containedType = null;
		if (type != null) {
			containedType = type;
		}
		else {
			containedType = typedElement.getContainedType();
		}
        if(containedType != null) {
            if(containedType instanceof PredefinedDataType) {
                EObject root = ContainmentServiceImpl.INSTANCE.getRootElement(typedElement);
                if(root instanceof Database) {
                    DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition((Database) root);
                    return def.getPredefinedDataTypeFormattedName((PredefinedDataType) containedType) + this.getCharacterSetString(containedType);
                }
            }
        }
        else {
            UserDefinedType referencedType = typedElement.getReferencedType();
            if(referencedType != null) {
                if (referencedType.getSchema() != schema || qualifyNames) {
                	return this.getName(referencedType,false, true);
                } else {
                	return referencedType.getName();
                }
            }
        }
        return null;
	}

    protected String getDataTypeString(TypedElement typedElement, Schema schema,boolean quoteIdentifiers,boolean qualifyNames) {
        SQLDataType containedType = typedElement.getContainedType();
        if(containedType != null) {
            if(containedType instanceof PredefinedDataType) {
                EObject root = ContainmentServiceImpl.INSTANCE.getRootElement(typedElement);
                if(root instanceof Database) {
                    DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition((Database) root);
                    return def.getPredefinedDataTypeFormattedName((PredefinedDataType) containedType) + this.getCharacterSetString(containedType);
                }
            }
        }
        else {
            UserDefinedType referencedType = typedElement.getReferencedType();
            if(referencedType != null) {
                if (referencedType.getSchema() != schema || qualifyNames) {
                	return this.getName(referencedType,quoteIdentifiers, true);
                } else {
                	return this.getName(referencedType,quoteIdentifiers, false);
                }
            }
        }
        return null;
    }

    public String getPredefinedTypeCharacterSetString (PredefinedDataType type) {
    	return getCharacterSetString(type);
    }
    
    protected String getCharacterSetString(SQLDataType type){
    	return "";
    }
    protected String getDomainTypeString(TypedElement typedElement, Schema schema,boolean qualifyNames) {
    	TypedElementLogicalDomainProvider elementDomainProvider = DdlGenerationUtility.getElementDomainProvider();
    	if (elementDomainProvider != null) {
    		if(elementDomainProvider.hasDomain(typedElement)) {
    			String domainType = elementDomainProvider.getDomainBaseType(typedElement);
    			if ("".equals(domainType)) return null;
    			return domainType;
    		}
    	}
    	return null;
    }
    
    
    protected String getName(TableConstraint constraint, boolean quoteIdentifiers) {
        String name = constraint.getName();

        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
        }
    
        return name;
    }
    
    protected String getName(TableConstraint constraint, boolean quoteIdentifiers,boolean qualifyNames) {
    	return this.getName(constraint, quoteIdentifiers);
    }
    
    protected String getTableConstraintName(TableConstraint constraint, boolean quoteIdentifiers,boolean qualifyNames) {
    	String name = constraint.getName();
        String tableName = constraint.getBaseTable().getName();
        
        if(quoteIdentifiers) {
        	name = this.getDoubleQuotedString(name);
        	tableName = this.getDoubleQuotedString(tableName);
        }
        if(qualifyNames) {
        	name = tableName + DOT + name;
        }
        
        return name;
    }

    protected String getName(Column column, boolean quoteIdentifiers, boolean qualifyNames) {
        String name = column.getName();
        String tableName = column.getTable().getName();
        String schemaName = column.getTable().getSchema().getName();
    
        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
            tableName  = this.getDoubleQuotedString(tableName);
            schemaName  = this.getDoubleQuotedString(schemaName);
        }
        if(qualifyNames) {
            name = schemaName + DOT + tableName + DOT + name;
        }
		
        return name;
    }

    protected String getName(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames) {
        String name = trigger.getName();
        Schema schema = trigger.getSchema() ==null? trigger.getSubjectTable().getSchema():trigger.getSchema();
        String schemaName = schema.getName();
    
        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
            schemaName  = this.getDoubleQuotedString(schemaName);
        }
        if(qualifyNames) {
            name = schemaName + DOT + name;
        }
		
        return name;
    }

    protected String getName(Routine routine, boolean quoteIdentifiers, boolean qualifyNames) {
        String name = routine.getName();
        String schemaName = routine.getSchema().getName();

        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
            schemaName  = this.getDoubleQuotedString(schemaName);
        }
        if(qualifyNames) {
            name = schemaName + DOT + name;
        }

        return name;
    }

    protected String getName(Index index, boolean quoteIdentifiers, boolean qualifyNames) {
        Table table = index.getTable();

        String indexName = index.getName();
        Schema schema = index.getSchema();
        if (schema == null) {
        	schema = table.getSchema();
        }
    	String schemaName = schema.getName();
        if(quoteIdentifiers) {
            indexName = this.getDoubleQuotedString(indexName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if(qualifyNames) {
            indexName = schemaName + DOT + indexName;
	    }
        return indexName;
    }

    protected String getName(Database database, boolean quoteIdentifiers) {
        String name = database.getName();

        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
        }
    
        return name;
    }

    protected String getName(AuthorizationIdentifier auth, boolean quoteIdentifiers) {
        String name = auth.getName();

        if(quoteIdentifiers && !name.equals("PUBLIC")) { // $NON-NLS-1$
            name = this.getDoubleQuotedString(name);
        }
    
        return name;
    }

    protected String getName(Schema schema, boolean quoteIdentifiers, boolean qualifyNames) {
        String schemaName = schema.getName();

        if(quoteIdentifiers) {
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        return schemaName;
    }   
    
    protected String getName(Table table, boolean quoteIdentifiers, boolean qualifyNames) {
        String tableName = table.getName();
        String schemaName = table.getSchema() != null ? table.getSchema().getName() : "nullschema";
        if (table.getSchema() == null) {
        	IBMPluginActivator.getInstance().writeLog(IStatus.ERROR, 0, "Table " + tableName + " does not have a schema. The table name will be qualified with \"nullschema\" in the DDL for now.", null);	        	
        }
        
        if(quoteIdentifiers) {
            tableName = this.getDoubleQuotedString(tableName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if(qualifyNames) {
            tableName = schemaName + DOT + tableName;
        }
    
        return tableName;
    }

    protected String getName(Sequence sequence, boolean quoteIdentifiers, boolean qualifyNames) {
        String sequenceName = sequence.getName();
        String schemaName = sequence.getSchema() != null ? sequence.getSchema().getName() : null;

        if (schemaName == null) {
        	IBMPluginActivator.getInstance().writeLog(IStatus.ERROR, 0, "Sequence " + sequence + " does not have a schema. The sequence name will not be qualified in the DDL.", null);	
        }

        if(quoteIdentifiers) {
            sequenceName = this.getDoubleQuotedString(sequenceName);
            if (schemaName != null) {
            	schemaName = this.getDoubleQuotedString(schemaName);	
            }            
        }

        if(qualifyNames && schemaName != null) {
            sequenceName = schemaName + DOT + sequenceName;
        }
    
        return sequenceName;
    }
    
    protected String getName(UserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames) {
        String typeName = type.getName();
        String schemaName = type.getSchema() != null ? type.getSchema().getName() : null;

        if (schemaName == null) {
        	IBMPluginActivator.getInstance().writeLog(IStatus.ERROR, 0, "User-defined type " + type + " does not have a schema. The user-defined type name will not be qualified in the DDL.", null);	
        }

        if(quoteIdentifiers) {
            typeName = this.getDoubleQuotedString(typeName);
            if (schemaName != null) {
            	schemaName = this.getDoubleQuotedString(schemaName);	
            }            
        }

        if(qualifyNames && schemaName != null) {
            typeName = schemaName + DOT + typeName;
        }
    
        return typeName;
    }

    protected String ensureSingleQuotedString(String original) {
    	if (original == null) return null;
    	if (original.startsWith(SINGLE_QUOTE) && 
    			original.endsWith(SINGLE_QUOTE)) 
    		return original;
    	return getSingleQuotedString(original);
    }
    
    protected String getSingleQuotedString(String orignal) {
        String result  = orignal;
        if ( orignal.startsWith( SINGLE_QUOTE )
                && orignal.endsWith( SINGLE_QUOTE ) ) {
            result= orignal.substring(1,orignal.length()-1);
        }

        result = result.replaceAll(SINGLE_QUOTE,SINGLE_QUOTE + SINGLE_QUOTE);
        return SINGLE_QUOTE + result + SINGLE_QUOTE;

    }
  
    protected String getDoubleQuotedString(String orignal) {
    	orignal = orignal.replaceAll(DOUBLE_QUOTE,DOUBLE_QUOTE + DOUBLE_QUOTE);
    	String result  = orignal;
    	return DOUBLE_QUOTE + result + DOUBLE_QUOTE;
    }
    
	public static String makeCharacterConstant( String constant )
	{
		StringBuffer work = new StringBuffer( constant );
		int pos = 0;
		while ( 0 <= (pos = work.indexOf( "'", pos )) ) //$NON-NLS-1$
		{
			work.insert( pos, '\'' );
			pos += 2;
		}
		work.insert( 0, '\'' );
		work.append( '\'' );
		return work.toString();
	}

    protected String getOwnerType(TableConstraint constraint){
    	return TABLE;
    }
 
	public static RoutineDdlBuilder getRoutineDdlBuilder(String dbProduct, String dbVersion) {
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.core", "routineDdlBuilder"); //$NON-NLS-1$ //$NON-NLS-2$
		if (extensionPoint != null) {
		    IExtension[] extensions = extensionPoint.getExtensions();
		    for(int i=0; i<extensions.length; ++i) {
		        IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
		        for(int j=0; j<configElements.length; ++j) {
		            if(configElements[j].getName().equals("builder")) { //$NON-NLS-1$
		                String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
		                if(!product.equals(dbProduct)) continue;
		                String version = configElements[j].getAttribute("version"); //$NON-NLS-1$
		                if(!version.equals(dbVersion)) continue;
		                try {
		                    routineDdlBuilder = (RoutineDdlBuilder) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
		                }
		                catch(CoreException e) {
		                    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getDefault().getBundle().getSymbolicName(), IStatus.ERROR,
		                            "The error was detected when creating the routine ddl builder for " + product + " "+ version, e); //$NON-NLS-1$ //$NON-NLS-2$
		                    IBMPluginActivator.getInstance().getLog().log(status);
		                }
		                break;
		            }
		        }
		    }
		}
		return routineDdlBuilder;
	}

	public boolean isImplicitPK (TableConstraint constraint) {
		return (constraint instanceof PrimaryKey) && !constraint.isEnforced();
	}
	
    protected String getDefaultValue(Column column) {
    	String defaultValue = column.getDefaultValue();
    	SQLDataType type = column.getContainedType();
    	return formatDefaultValue(type, defaultValue);
    }

    protected String formatDefaultValue(SQLDataType type, String defaultValue) {
    	if (defaultValue == null || defaultValue.equals("")) return null;
    	if (type == null) return null;
    	if (type instanceof CharacterStringDataType 
    		|| type instanceof DateDataType || type instanceof TimeDataType) {
    		return this.getSingleQuotedString(defaultValue);
    	} 
    	else 
    		return defaultValue;
    }
    
	protected String[] getGrantString(SQLObject sqlObject,boolean quoteIdentifiers,boolean qualifyNames) {
		Vector grantVec = new Vector();
    	Iterator iter = sqlObject.getPrivileges().iterator();
    	while (iter.hasNext()) {
    		Privilege privilege = (Privilege) iter.next();
    		String statement = this.getGrantPrivilegeStatement(privilege, quoteIdentifiers, qualifyNames);
    		if (statement != null) {
    			grantVec.add(this.getGrantPrivilegeStatement(privilege, quoteIdentifiers, qualifyNames));
    		}
    	}

		String[] grantStr = new String[grantVec.size()];
		grantVec.copyInto(grantStr);
    	return grantStr;
	}

	protected String[] getRevokeString(SQLObject sqlObject,boolean quoteIdentifiers,boolean qualifyNames) {
		Vector revokeVec = new Vector();
    	Iterator iter = sqlObject.getPrivileges().iterator();
    	while (iter.hasNext()) {
    		Privilege privilege = (Privilege) iter.next();
    		String statement = this.getGrantPrivilegeStatement(privilege, quoteIdentifiers, qualifyNames);
    		if (statement != null) {
    			revokeVec.add(this.getRevokePrivilegeStatement(privilege, quoteIdentifiers, qualifyNames));
    		}
   		}

		String[] revokeStr = new String[revokeVec.size()];
		revokeVec.copyInto(revokeStr);
    	return revokeStr;
	}

	public String getGrantPrivilegeStatement(Privilege privilege,boolean quoteIdentifiers,boolean qualifyNames) {
		if (this.isDDLSupressable(privilege)) return null;
		String ret = GRANT + SPACE + privilege.getAction() + SPACE + ON + SPACE 
				+ getPrivilegedObjectTypeString(privilege) + SPACE
				+ getPrivilegedObjectName(privilege, quoteIdentifiers, qualifyNames) 
				+ SPACE + TO + SPACE + getGranteeSubstring(privilege.getGrantee(), quoteIdentifiers);
		if (privilege.isGrantable()) ret += SPACE + WITH + SPACE + GRANT + SPACE + OPTION;
		return ret;
	}

	public String getRevokePrivilegeStatement(Privilege privilege,boolean quoteIdentifiers,boolean qualifyNames) {
		if (this.isDDLSupressable(privilege)) return null;
		String ret = REVOKE + SPACE + privilege.getAction() + SPACE + ON + SPACE 
		+ getPrivilegedObjectTypeString(privilege) + SPACE
		+ getPrivilegedObjectName(privilege, quoteIdentifiers, qualifyNames) 
		+ SPACE + FROM + SPACE + getGranteeSubstring(privilege.getGrantee(), quoteIdentifiers);
		return ret;
	}
	
    protected String getGranteeSubstring(AuthorizationIdentifier authId, boolean quoteIdentifiers) {
    	return getName(authId,quoteIdentifiers);
    }
    
	protected String getPrivilegedObjectName(Privilege privilege, boolean quoteIdentifiers, boolean qualifyNames) {
		SQLObject obj = privilege.getObject();
		String name = null;
		if (obj instanceof Table) name = getName((Table)obj, quoteIdentifiers, qualifyNames);
		if (obj instanceof Index) name = getName((Index)obj, quoteIdentifiers, qualifyNames);
		if (obj instanceof Sequence) name = getName((Sequence)obj, quoteIdentifiers, qualifyNames);
		if (obj instanceof Schema) name = getName((Schema)obj, quoteIdentifiers, qualifyNames);
		if (obj instanceof Routine) name = getName((Routine)obj, quoteIdentifiers, qualifyNames);
		if (obj instanceof UserDefinedType) name = getName((UserDefinedType)obj, quoteIdentifiers, qualifyNames);
		if (obj instanceof Database) name = "";
		return name;
	}

	protected String getPrivilegedObjectTypeString(Privilege privilege) {
		SQLObject obj = privilege.getObject();
		if (obj instanceof BaseTable) return TABLE;
		if (obj instanceof ViewTable) return TABLE;
		if (obj instanceof Index) return INDEX;
		if (obj instanceof Schema) return SCHEMA;
		if (obj instanceof Sequence) return SEQUENCE;
		if (obj instanceof UserDefinedType) return TYPE;
		if (obj instanceof Database) return DATABASE;
		return EMPTY_STRING;
	}

	protected String getGrantRoleAuthorizationString(RoleAuthorization roleAuth, boolean quoteIdentifiers) {
		String authStr = NEWLINE + GRANT  + SPACE + this.getName(roleAuth.getRole(), quoteIdentifiers) 
						+ SPACE + TO + this.getGranteeType(roleAuth.getGrantee()) + SPACE + this.getName(roleAuth.getGrantee(), quoteIdentifiers);
		if (roleAuth.isGrantable()) {
			authStr += SPACE + WITH + SPACE + GRANT + SPACE + OPTION;
		}
    	return authStr;
	}


	protected String getRevokeRoleAuthorizationString(RoleAuthorization roleAuth, boolean quoteIdentifiers) {
		String authStr = NEWLINE + REVOKE  + SPACE + this.getName(roleAuth.getRole(), quoteIdentifiers) 
    						+ SPACE + FROM + this.getGranteeType(roleAuth.getGrantee()) + SPACE + this.getName(roleAuth.getGrantee(), quoteIdentifiers);
    	return authStr;
	}

	protected String getGranteeType(AuthorizationIdentifier authID){
		return "";
	}
	
	protected String getIndexTypePrefix(Index index) {
        if(index.isUnique()) {
            return UNIQUE + SPACE;
        }
        return EMPTY_STRING;
	}
	
    private IEngineeringCallBack getDummyEngineeringCallBack(){
    	if (this.dummyCallback == null) this.dummyCallback = new dummyEngineeringCallBack();
    	return dummyCallback;
    }

    public class dummyEngineeringCallBack implements IEngineeringCallBack {
    	public String[] getMessages(){
    		return new String[]{};
    	}
    	
    	public void writeMessage(String message) {
    	}
    }
    
    protected boolean isDDLSupressable(Privilege privilege) {
    	return AccessControlUtilities.isDDLSuppressable(privilege);
    }
    
    protected String getDeleteReferentialActionAction(ForeignKey foreignKey){
    	String statement="";
    	ReferentialActionType action = foreignKey.getOnDelete();
        if(action != ReferentialActionType.NO_ACTION_LITERAL) {
            statement += NEWLINE + TAB + ON + SPACE + DELETE + SPACE + getReferentialAction(action);            
        }
        return statement;
    }

    protected String getUpdateReferentialActionAction(ForeignKey foreignKey){
    	String statement="";
        ReferentialActionType action = foreignKey.getOnUpdate();
        if(action != ReferentialActionType.NO_ACTION_LITERAL) {
            statement += NEWLINE + TAB + ON + SPACE + UPDATE + SPACE + getReferentialAction(action);      
        }
        return statement;
    }

    protected String getIncrementTypeString(IndexMember m) {
    	 return TAB + TAB + m.getIncrementType().getName();
    }
    
    protected String getSystemGrantMessage(String action,Privilege privilege,boolean quoteIdentifiers,boolean qualifyNames) {
    	StringBuffer msg = new StringBuffer();
    	if (action.equals(GRANT)) msg.append(DdlGenerationMessages.GRANT_NOT_ALLOWED);
    	if (action.equals(REVOKE)) msg.append(DdlGenerationMessages.REVOKE_NOT_ALLOWED);
    	if (msg.length() == 0) return null;
    	msg.append(COLON + SPACE);
    	msg.append(privilege.getAction());
    	msg.append(SPACE + ON + SPACE);
    	msg.append(getPrivilegedObjectName(privilege, quoteIdentifiers, qualifyNames));
    	return msg.toString();
    }
    
	protected String getAttrString(AttributeDefinition attr, Schema schema,
			boolean qualifyNames) {
		String typeName = getDataTypeString(attr,schema,qualifyNames);
		String statement = attr.getName() + SPACE + typeName;
		return statement;
	}

	public String getAttrString(AttributeDefinition attr, Schema schema) {
		return this.getAttrString(attr, schema, false);
	}
	
	//For certain objects, like SQL procedure, we get DDL source from catalog, which is 
	//same as what user originally specified on CREATE. This DDL can have the case that
	//name is not qualified with schema. Per DB2 customer request, we re-parse DDL
	//and qualify name with schema as required by Generate DDL option. So far, we only
	//fix this for oracle SQL function and procedure when they are existing objects
	//of the database server.
	protected String getNameQualifiedWithSchema(String ddlSource, Routine routine, boolean quoteIdentifiers, boolean qualifyNames)
	{
		Schema schema = routine.getSchema();
		String name = routine.getName();
		if ( ddlSource == null //
				|| ddlSource.trim().isEmpty() //
				|| !( routine instanceof ICatalogObject ) //
				|| schema == null //
				|| schema.getName() == null //
				|| schema.getName().trim().isEmpty() //
				|| name == null //
				|| name.trim().isEmpty() ) {
			return ddlSource;
		}
		
		String result = ddlSource;		
		if ( qualifyNames ) {
			String schemaName = schema.getName();
			String quotedSchemaName = this.getDoubleQuotedString(schemaName);
			String quotedName = this.getDoubleQuotedString(name);
			String upperCaseDdlSource = ddlSource.toUpperCase();
			String upperCaseName = name.toUpperCase();
			String upperCaseSchemaName = schemaName.toUpperCase();
			String usedName = quotedName;
			
			//first check quoted name
			int nameIndex = ddlSource.indexOf(quotedName);
    		if ( nameIndex == -1 ) {
    			//check unquoted name        			
    			nameIndex = upperCaseDdlSource.indexOf(upperCaseName);
    			if ( nameIndex != -1 ) {  			
    				usedName = ddlSource.substring(nameIndex, nameIndex + name.length());
    			}
    		}    		   		
    		if ( nameIndex > 0 ) {
    			String pretext = ddlSource.substring(0, nameIndex);
    			//name is not qualified with quoted schema
    			if ( pretext.indexOf(quotedSchemaName) == -1 ) {
    				String upperCasePretext = pretext.toUpperCase();
    				//name is not qualified with unquoted schema
    				if ( upperCasePretext.indexOf(upperCaseSchemaName) == -1 ) {
        				if ( quoteIdentifiers ) {
        					result = ddlSource.replaceFirst(usedName, quotedSchemaName + DOT + quotedName);
        				}
        				else {
        					result = ddlSource.replaceFirst(usedName, schemaName + DOT + name);
        				}
    				}    				
    			}
    		}
		}
		
		return result;
	}

	//This method is not for the DDL Generation, instead used by the compare
	public String getFullParameterString(Routine routine){
		EList parms = routine.getParameters();
		StringBuffer buf = new StringBuffer();
		if (!parms.isEmpty()) {

			Iterator it = parms.iterator();
			while(it.hasNext()) {
				buf.append("(");
				Parameter parm = (Parameter) it.next();
				buf.append(parm.getMode());
				buf.append(" ");
				buf.append(parm.getName());
				buf.append(" ");
				buf.append(getParameterString(parm, routine.getSchema(), false));
				buf.append(")");
				if(it.hasNext()) {
					buf.append(", ");                 //$NON-NLS-1$
				}
			}
		}            	                	
		String result = buf.toString();
		return result;
	}
	
}
