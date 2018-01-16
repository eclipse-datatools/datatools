/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.ddl;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.ibm.db2.DB2PluginActivator;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Alias;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Column;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Function;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Index;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Table;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2UserDefinedFunction;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2View;
import org.eclipse.datatools.enablement.ibm.db2.model.DataCaptureType;
import org.eclipse.datatools.enablement.ibm.db2.model.GenerateType;
import org.eclipse.datatools.enablement.ibm.db2.model.OriginType;
import org.eclipse.datatools.enablement.ibm.ddl.DdlBuilder;
import org.eclipse.datatools.enablement.ibm.ddl.DdlGenerationUtility;
import org.eclipse.datatools.enablement.ibm.ddl.RoutineDdlBuilder;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.routines.DataAccess;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType;
import org.eclipse.datatools.modelbase.sql.tables.ActionTimeType;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.CheckType;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.ecore.EObject;

public abstract class DB2DdlBuilder extends DdlBuilder {
	protected final static String DB2SQL               = "DB2SQL"; //$NON-NLS-1$
    protected final static String MODE                 = "MODE"; //$NON-NLS-1$
    protected final static String CYCLE                = "CYCLE"; //$NON-NLS-1$
    protected final static String CACHE                = "CACHE"; //$NON-NLS-1$
    protected final static String IN                   = "IN"; //$NON-NLS-1$
    protected final static String OUT                  = "OUT"; //$NON-NLS-1$
    protected final static String INOUT                = "INOUT"; //$NON-NLS-1$
    protected final static String LOCATOR              = "LOCATOR"; //$NON-NLS-1$
    protected final static String RETURNS              = "RETURNS"; //$NON-NLS-1$
    protected final static String CAST                 = "CAST"; //$NON-NLS-1$
    protected final static String SPECIFIC             = "SPECIFIC"; //$NON-NLS-1$
    protected final static String EXTERNAL             = "EXTERNAL"; //$NON-NLS-1$
    protected final static String COLUMN               = "COLUMN"; //$NON-NLS-1$
    protected final static String NICKNAME             = "NICKNAME"; //$NON-NLS-1$
    protected final static String TEMPLATE             = "TEMPLATE"; //$NON-NLS-1$
    protected final static String LABEL                = "LABEL"; //$NON-NLS-1$
    protected final static String PACKAGE              = "PACKAGE"; //$NON-NLS-1$
    protected static final String MAXVALUE             = "MAXVALUE "; //$NON-NLS-1$
    protected static final String NO_MAXVALUE          = "NO MAXVALUE "; //$NON-NLS-1$
    protected static final String MINVALUE             = "MINVALUE "; //$NON-NLS-1$
	protected static final String NO_MINVALUE          = "NO MINVALUE "; //$NON-NLS-1$
	protected static final String INCREMENT_BY         = "INCREMENT BY "; //$NON-NLS-1$
	protected static final String START_WITH           = "START WITH "; //$NON-NLS-1$
	protected static final String SET_MAXVALUE         = "SET MAXVALUE "; //$NON-NLS-1$
	protected static final String SET_NO_MAXVALUE      = "SET NO MAXVALUE "; //$NON-NLS-1$
	protected static final String SET_MINVALUE         = "SET MINVALUE "; //$NON-NLS-1$
	protected static final String SET_NO_MINVALUE      = "SET NO MINVALUE "; //$NON-NLS-1$
	protected static final String SET_INCREMENT_BY     = "SET INCREMENT BY "; //$NON-NLS-1$
	protected static final String RESTART_WITH         = "RESTART WITH "; //$NON-NLS-1$
	protected static final String RESTART         	   = "RESTART "; //$NON-NLS-1$
	protected static final String EMPTY_STRING         = ""; //$NON-NLS-1$
	protected static final String COMMA                = ", "; //$NON-NLS-1$
	protected static final String ORDER				   = "ORDER "; //$NON-NLS-1$
	protected static final String SINGLE_QUOTED_EMPTY_STRING = "\'\'"; //$NON-NLS-1$

    public String createAlias(DB2Alias alias, boolean quoteIdentifiers, boolean qualifyNames) {
        Table aliased = alias.getAliasedTable();
        if(aliased == null) {
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					DB2DdlMessages.FE_ALIAS_TABLE_NOT_EXIST, new Object[] { getName(alias, false, true)}));
            return null;
        }
        return CREATE + SPACE + ALIAS + SPACE + getName(alias, quoteIdentifiers, qualifyNames)
        	+ SPACE +  FOR + SPACE + getName(aliased, quoteIdentifiers, qualifyNames);
    }

    public String dropAlias(DB2Alias alias, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + ALIAS + SPACE + getName(alias, quoteIdentifiers, qualifyNames);
    }

    public String createSchema(Schema schema, boolean quoteIdentifiers,boolean qualifyNames) {
        return CREATE + SPACE + SCHEMA + SPACE + getName(schema, quoteIdentifiers,qualifyNames);
    }
    
    //@bd00058820gs
    public String dropSchema(Schema schema, boolean quoteIdentifiers, boolean qualifyNames) {
        return super.dropSchema(schema,quoteIdentifiers,qualifyNames) + SPACE + RESTRICT;
    }
    
    public String createDistinctUserDefinedType(DistinctUserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames) {
        PredefinedDataType dataType = type.getPredefinedRepresentation();
        if (dataType == null) {
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					DB2DdlMessages.FE_DISTINCT_TYPE_HAS_NO_SOURCE_TYPE, new Object[] { getName(type, false, true)}));
        	return null;
        }
        String dataTypeString;
        EObject root = ContainmentServiceImpl.INSTANCE.getRootElement(type);
        if(root instanceof Database) {
            DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition((Database) root);
            dataTypeString = def.getPredefinedDataTypeFormattedName(dataType);
        }
        else {
            //TODO report error
            return null;
        }
        
        String statement = CREATE + SPACE + DISTINCT + SPACE + TYPE + SPACE + getName(type, quoteIdentifiers, qualifyNames)
        	+ SPACE + AS + SPACE + dataTypeString;
        if(!dataTypeString.equals(BLOB) && !dataTypeString.equals(CLOB) && !dataTypeString.equals(DBCLOB) 
                && !dataTypeString.equals(LONG + SPACE + VARCHAR) && !dataTypeString.equals(LONG + SPACE + VARGRAPHIC)
                && !dataTypeString.equals(DATALINK)) {
            statement += SPACE + WITH + SPACE + COMPARISONS;
        }
        return statement;
    }
    
    public String createTrigger(DB2Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames) {
        String statement = CREATE + SPACE + TRIGGER + SPACE + getName(trigger, quoteIdentifiers, qualifyNames) + SPACE;

        final ActionTimeType actionTime = trigger.getActionTime();
        if(actionTime == ActionTimeType.AFTER_LITERAL) {
            statement += NEWLINE  + TAB + AFTER;
        }
        else if(actionTime == ActionTimeType.BEFORE_LITERAL) {
            statement += NEWLINE + TAB + NO + SPACE + CASCADE + SPACE + BEFORE;
        }
        else if(actionTime == ActionTimeType.INSTEADOF_LITERAL) {
            statement += NEWLINE + TAB + INSTEAD_OF;
        }
	    statement += SPACE;

        if(trigger.isDeleteType()) {
    	    statement += DELETE;
    	}
    	else if(trigger.isInsertType()) {
    	    statement += INSERT;
    	}
    	else if(trigger.isUpdateType()) {
    	    statement += UPDATE;
    	    Collection updateColumns = trigger.getTriggerColumn();
            if(!updateColumns.isEmpty()) {
                statement += SPACE + OF + SPACE ;
                Iterator it = updateColumns.iterator();
                while(it.hasNext()) {
                    Column column = (Column) it.next();
                    statement += this.getName(column, quoteIdentifiers, false);
                    if(it.hasNext()) {
                        statement += COMMA + SPACE;
                    }
                }
            }
    	}
    	
        statement += SPACE + ON + SPACE + getName(trigger.getSubjectTable(), quoteIdentifiers, qualifyNames) + NEWLINE;

        final String newRow = trigger.getNewRow();
        final String oldRow = trigger.getOldRow();
        final String newTable = trigger.getNewTable();
        final String oldTable = trigger.getOldTable();

        String referenceStr = EMPTY_STRING;
        if(newRow != null && newRow.length() != 0) {
        	referenceStr += (referenceStr.equals(EMPTY_STRING)? SPACE: NEWLINE + TAB + TAB) + NEW + SPACE + AS + SPACE + newRow;
        }
        if(oldRow != null && oldRow.length() != 0) {
        	referenceStr += (referenceStr.equals(EMPTY_STRING)? SPACE: NEWLINE +TAB + TAB) + OLD + SPACE + AS + SPACE + oldRow;
        }
        if(newTable != null && newTable.length() != 0) {
        	referenceStr += (referenceStr.equals(EMPTY_STRING)? SPACE: NEWLINE +TAB + TAB) + this.getTriggerReferenceNewTable() + SPACE + AS + SPACE + newTable;
        }
        if(oldTable != null && oldTable.length() != 0) {
        	referenceStr += (referenceStr.equals(EMPTY_STRING)? SPACE: NEWLINE +TAB + TAB) + this.getTriggerReferenceOldTable() + SPACE + AS + SPACE + oldTable;
        }

        if (!referenceStr.equals(EMPTY_STRING)) {
        	statement += TAB + REFERENCING + SPACE + referenceStr + NEWLINE;
        }

        if(trigger.getActionGranularity() == ActionGranularityType.ROW_LITERAL) {
            statement += TAB + FOR + SPACE + EACH + SPACE + ROW;
    	}
        else {
            statement += TAB + FOR + SPACE + EACH + SPACE + STATEMENT;
    	}

        statement += SPACE + MODE + SPACE + DB2SQL + NEWLINE;

        SearchCondition condition = trigger.getWhen();
        if(condition != null) {
        	String c = condition.getSQL();
			if ( c != null && c.trim().length() != 0 && DdlGenerationUtility.filterOutComments( c ) )
			{
        		statement += WHEN + SPACE + LEFT_PARENTHESIS + DdlGenerationUtility.convertLineEndings(c, NEWLINE) + RIGHT_PARENTHESIS + NEWLINE;
        	}
        }

        String sqlBody = EMPTY_STRING;
        Iterator it = trigger.getActionStatement().iterator();
        while(it.hasNext()) {
            SQLStatement s = (SQLStatement) it.next();
            sqlBody += s.getSQL();
        }

		if ( sqlBody.equals( EMPTY_STRING ) || !DdlGenerationUtility.filterOutComments( sqlBody ) )
		{
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					DB2DdlMessages.FE_TRIGGER_ACTION_EMPTY, new Object[] { getName(trigger, quoteIdentifiers, qualifyNames)}));
        	return null;
        }
        
        statement += DdlGenerationUtility.convertLineEndings(sqlBody, NEWLINE);
        
        return statement;
    }
    
    public String createView(DB2View view, boolean quoteIdentifiers, boolean qualifyNames) {
        String viewDefinition = CREATE + SPACE + VIEW + SPACE + getName(view, quoteIdentifiers, qualifyNames) + SPACE;
        
        String columns = getViewColumnList(view,quoteIdentifiers);
        if(columns != null) {
            viewDefinition += LEFT_PARENTHESIS + columns + RIGHT_PARENTHESIS + SPACE;
        }
        viewDefinition += AS + NEWLINE;
        QueryExpression expression = view.getQueryExpression();
        if (expression == null || expression.getSQL() == null || expression.getSQL().length() == 0) {
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					DB2DdlMessages.FE_VIEW_HAS_NO_BODY, new Object[] { getName(view, false, true)}));        	
        }
        else {
        	viewDefinition += expression.getSQL();
        }
        CheckType checkType = view.getCheckType();
        if(checkType == CheckType.CASCADED_LITERAL) {
            viewDefinition += NEWLINE + WITH + SPACE + CASCADED + SPACE + CHECK + SPACE + OPTION;
        }
        else if(checkType == CheckType.LOCAL_LITERAL) {
            viewDefinition += NEWLINE + WITH + SPACE + LOCAL + SPACE + CHECK + SPACE + OPTION;
        }
        return viewDefinition;
    }

    public String createUserDefinedFunction(DB2UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames) {
    	Database database = function.getSchema().getDatabase();
    	RoutineDdlBuilder routineDdlBuilder = DdlBuilder.getRoutineDdlBuilder(database.getVendor(),database.getVersion());
    	if (routineDdlBuilder != null) {
    		String ddl = routineDdlBuilder.buildCreateRoutineStatement(function,quoteIdentifiers,qualifyNames);
    		if (ddl != null) return ddl;
    	}

        String language = function.getLanguage();
        if (language == null && function.getOrigin() == OriginType.NONE_LITERAL) language = "SQL"; //$NON-NLS-1$
        if(language.trim().equalsIgnoreCase("SQL")) { //$NON-NLS-1$
        	Source source = function.getSource();
        	if (source != null) {
        		return source.getBody();
        	} else {
            	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
    					DB2DdlMessages.FE_ROUTINE_SOURCE_EMPTY, new Object[] { getName(function, false, true)}));
        		return null;
        	}
        }
    	
    	String text = CREATE + SPACE + FUNCTION + SPACE + getName(function, quoteIdentifiers, qualifyNames)
        	+ SPACE + LEFT_PARENTHESIS + getParameters(function,qualifyNames) + RIGHT_PARENTHESIS + NEWLINE
        	+ TAB + getFunctionReturnsClause(function,qualifyNames);

    	if (function.getOrigin() == OriginType.TEMPLATE_LITERAL ) {
    		text += NEWLINE + TAB + AS + SPACE + TEMPLATE;  //$NON-NLS-1$
		    text += getDeterministicOption(function);
		    text += getExternalActionOption(function);
    	} else {
		    text += getSpecificOption(function,quoteIdentifiers,qualifyNames);
		    text += getDeterministicOption(function);
		    text += getDataAccessOption(function);
		    text += getExternalActionOption(function);
		    text += getNullCallOption(function);
		    text += getParallelOption(function);
		    text += getFederatedOption(function);
		    
		    text += getLanguageOption(function);
		    text += getCardinalityOption(function);
		    text += getDBInfoOption(function);
		    text += getExternalNameOption(function,quoteIdentifiers,qualifyNames);
		    text += getFencedOption(function);
		    text += getFinalCallOption(function);
		    text += getParameterStyleOption(function);
		    text += getParameterCcsidOption(function);
		    text += getPredicatesOption(function);
		    text += getScratchPadCallOption(function);
		    text += getSecurityOption(function);

    	}
        return text;      
    }

    public String createProcedure(DB2Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {

        Database database = procedure.getSchema().getDatabase();
    	RoutineDdlBuilder routineDdlBuilder = DdlBuilder.getRoutineDdlBuilder(database.getVendor(),database.getVersion());
    	if (routineDdlBuilder != null) {
    		String ddl = routineDdlBuilder.buildCreateRoutineStatement(procedure,quoteIdentifiers,qualifyNames);
    		if (ddl != null) return ddl;
    	}
    	
        String language = procedure.getLanguage();
        if (language == null) language = "SQL"; //$NON-NLS-1$
        if(language.equalsIgnoreCase("SQL")) { //$NON-NLS-1$
        	Source source = procedure.getSource();
        	if (source != null) {
        		return source.getBody();
        	} else {
            	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
    					DB2DdlMessages.FE_ROUTINE_SOURCE_EMPTY, new Object[] { getName(procedure, false, true)}));
        		return null; 
        	}
        }

    	String text = CREATE + SPACE + PROCEDURE + SPACE + getName(procedure, quoteIdentifiers, qualifyNames)
    	+ SPACE + LEFT_PARENTHESIS + getParameters(procedure,qualifyNames) + RIGHT_PARENTHESIS;

        text += getSpecificOption(procedure,quoteIdentifiers,qualifyNames);
        text += getDataAccessOption(procedure);
        text += getDeterministicOption(procedure);
        text += getDynamicResultSetsOption(procedure);
        text += getFederatedOption(procedure);
    
        text += NEWLINE + TAB + "LANGUAGE " + language; //$NON-NLS-1$
        text += getFencedOption(procedure);
        text += getParameterStyleOption(procedure);
        text += getProgramTypeOption(procedure);
        text += getDBInfoOption(procedure);
        text += getExternalNameOption(procedure,quoteIdentifiers,qualifyNames);

        return text;      
    }
    

    public String addCheckConstraint(CheckConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames) {
    	String text = super.addCheckConstraint(constraint,quoteIdentifiers,qualifyNames);
    	text += this.getEnforcedOption(constraint);
    	return text;
    }
    
    public String addForeignKey(ForeignKey foreignKey, boolean quoteIdentifiers, boolean qualifyNames) {
    	String text =super.addForeignKey(foreignKey,quoteIdentifiers,qualifyNames);
    	if (text == null) {
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					DB2DdlMessages.FE_PARENT_TABLLE_OR_KEY_DO_NOT_EXIST, new Object[] { foreignKey.getName()}));
    		return null; 
    	}
    	text += this.getEnforcedOption(foreignKey);
    	return text;
    }
    
    public String commentOn(DB2Alias alias, boolean quoteIdentifiers, boolean qualifyNames) {
        String comment = alias.getDescription();
        if(comment == null || comment.length() == 0) {
            return null;
        }

        return COMMENT + SPACE + ON + SPACE + ALIAS + SPACE + getName(alias, quoteIdentifiers, qualifyNames)
        	+ SPACE + IS + NEWLINE + this.getSingleQuotedString(comment);
    }

    public String commentOn(Column column, boolean quoteIdentifiers, boolean qualifyNames) {
        String comment = column.getDescription();
        if(comment == null || comment.length() == 0) {
            return null;
        }

    	Table table = column.getTable();
    	if (! (table instanceof PersistentTable) && ! (table instanceof ViewTable)){
    		return null;
    	}
        
        String columnName = column.getName();
        String tableName = table.getName();
        String schemaName = column.getTable().getSchema().getName();

        if(quoteIdentifiers) {
            columnName = this.getDoubleQuotedString(columnName);
            tableName = this.getDoubleQuotedString(tableName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }
        if(qualifyNames) {
            columnName = schemaName + DOT + tableName + DOT + columnName;
        } else {
            columnName = tableName + DOT + columnName;
        }

        return COMMENT + SPACE + ON + SPACE + COLUMN + SPACE + columnName
    		+ SPACE + IS + NEWLINE + this.getSingleQuotedString(comment);
    }

    public String commentOn(DistinctUserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames) {
        String comment = type.getDescription();
        if(comment == null || comment.length() == 0) {
            return null;
        }

        String name = getName(type, quoteIdentifiers, qualifyNames);

        return COMMENT + SPACE + ON + SPACE + DISTINCT + SPACE + TYPE + SPACE + name + SPACE + IS
        	+ NEWLINE + this.getSingleQuotedString(comment);
    }
    
    public String commentOn(ArrayDataType type, boolean quoteIdentifiers, boolean qualifyNames) {
        String comment = type.getDescription();
        if(comment == null || comment.length() == 0) {
            return null;
        }

        String name = getName(type, quoteIdentifiers, qualifyNames);

        return COMMENT + SPACE + ON + SPACE  + TYPE + SPACE + name + SPACE + IS
        	+ NEWLINE + this.getSingleQuotedString(comment);
    }
    
    private String getName(ArrayDataType type, boolean quoteIdentifiers,
			boolean qualifyNames) {
    	 String typeName = type.getName();
         String schemaName = ((UserDefinedType) type).getSchema() != null ? ((UserDefinedType) type).getSchema().getName() : null;

         if (schemaName == null) {
         	DB2PluginActivator.getInstance().writeLog(IStatus.ERROR, 0, "User-defined type " + type + " does not have a schema. The user-defined type name will not be qualified in the DDL.", null);	
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

	public String labelOn(Column column, boolean quoteIdentifiers, boolean qualifyNames) {
        String label = column.getLabel();
        if (label == null || label.equals(EMPTY_STRING)){ //$NON-NLS-1$
            return null;
        }

        Table table = column.getTable();
        if (! (table instanceof PersistentTable) && ! (table instanceof ViewTable)){
            return null;
        }
        
        String columnName = column.getName();
        String tableName = table.getName();
        String schemaName = column.getTable().getSchema().getName();

        if(quoteIdentifiers) {
            columnName = this.getDoubleQuotedString(columnName);
            tableName = this.getDoubleQuotedString(tableName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }
        if(qualifyNames) {
            columnName = schemaName + DOT + tableName + DOT + columnName;
        } else {
            columnName = tableName + DOT + columnName;
        }
        
        return LABEL + SPACE + ON + SPACE + COLUMN + SPACE + columnName + SPACE + IS + NEWLINE + this.getSingleQuotedString(label); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    public String labelOn(DB2Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {
        String label = procedure.getLabel();

        if (label == null || label.equals(EMPTY_STRING)){ //$NON-NLS-1$
            return null;
        }

        String procedureName = procedure.getName();
        String schemaName = procedure.getSchema().getName();

        if(quoteIdentifiers) {
            procedureName = this.getDoubleQuotedString(procedureName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if(qualifyNames) {
            procedureName = schemaName + DOT + procedureName; //$NON-NLS-1$
        }
        
        return LABEL + SPACE + ON + SPACE + PROCEDURE + SPACE + procedureName + SPACE + IS + NEWLINE + this.getSingleQuotedString(label); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    public String labelOn(DB2UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames) {
        String label = function.getLabel();

        if (label == null || label.equals(EMPTY_STRING)){ //$NON-NLS-1$
            return null;
        }

        String functionName = function.getName();
        String schemaName = function.getSchema().getName();

        if(quoteIdentifiers) {
            functionName = this.getDoubleQuotedString(functionName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if(qualifyNames) {
            functionName = schemaName + DOT + functionName; //$NON-NLS-1$
        }
        
        return LABEL + SPACE + ON + SPACE + FUNCTION + SPACE + functionName + SPACE + IS + NEWLINE + this.getSingleQuotedString(label); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public String labelOn(DB2Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames) {
        String label = trigger.getLabel();

        if (label == null || label.equals(EMPTY_STRING)){ //$NON-NLS-1$
            return null;
        }

        String triggerName = trigger.getName();
        String schemaName = trigger.getSchema().getName();

        if(quoteIdentifiers) {
            triggerName = this.getDoubleQuotedString(triggerName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if(qualifyNames) {
            triggerName = schemaName + DOT + triggerName; //$NON-NLS-1$
        }     
        
        return LABEL + SPACE + ON + SPACE + TRIGGER + SPACE + triggerName + SPACE + IS + NEWLINE + this.getSingleQuotedString(label); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public String labelOn(TableConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames) {
        String label = constraint.getLabel();

        if (label == null || label.equals(EMPTY_STRING)){ //$NON-NLS-1$
            return null;
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
        
        return LABEL + SPACE + ON + SPACE + CONSTRAINT + SPACE + constraintName + SPACE + IS + NEWLINE + this.getSingleQuotedString(label); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public String labelOn(DB2Index index, boolean quoteIdentifiers, boolean qualifyNames) {
        String label = index.getLabel();

        if (label == null || label.equals(EMPTY_STRING)){ //$NON-NLS-1$
            return null;
        }

        String indexName = index.getName();
        String schemaName = index.getSchema().getName();

        if(quoteIdentifiers) {
            indexName = this.getDoubleQuotedString(indexName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }
        
        if(qualifyNames){
        	indexName = schemaName + DOT + indexName;
        }

        return LABEL + SPACE + ON + SPACE + INDEX + SPACE + indexName + SPACE + IS + NEWLINE + this.getSingleQuotedString(label); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public String labelOn(DistinctUserDefinedType userDefinedType, boolean quoteIdentifiers, boolean qualifyNames) {
        String label = userDefinedType.getLabel();

        if (label == null || label.equals(EMPTY_STRING)){ //$NON-NLS-1$
            return null;
        }

        String userDefinedTypeName = userDefinedType.getName();
        String schemaName = userDefinedType.getSchema().getName();

        if(quoteIdentifiers) {
            userDefinedTypeName = this.getDoubleQuotedString(userDefinedTypeName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if(qualifyNames) {
            userDefinedTypeName = schemaName + DOT + userDefinedTypeName; //$NON-NLS-1$
        }

        return LABEL + SPACE + ON + SPACE + TYPE + SPACE + userDefinedTypeName + SPACE + IS + NEWLINE + this.getSingleQuotedString(label); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public String labelOn(Sequence sequence, boolean quoteIdentifiers, boolean qualifyNames) {
        String label = sequence.getLabel();

        if (label == null || label.equals(EMPTY_STRING)){ //$NON-NLS-1$
            return null;
        }

        String sequenceName = sequence.getName();
        String schemaName = sequence.getSchema().getName();

        if(quoteIdentifiers) {
            sequenceName = this.getDoubleQuotedString(sequenceName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if(qualifyNames) {
            sequenceName = schemaName + DOT + sequenceName; //$NON-NLS-1$
        }
        
        return LABEL + SPACE + ON + SPACE + SEQUENCE + SPACE + sequenceName + SPACE + IS + NEWLINE + this.getSingleQuotedString(label); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public String labelOn(Schema schema, boolean quoteIdentifiers) {
        String label = schema.getLabel();

        if (label == null || label.equals(EMPTY_STRING)){ //$NON-NLS-1$
            return null;
        }

        String schemaName = schema.getName();

        if(quoteIdentifiers) {
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        return LABEL + SPACE + ON + SPACE + SCHEMA + SPACE + schemaName + SPACE + IS + NEWLINE + this.getSingleQuotedString(label); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    public String labelOn(Table table, boolean quoteIdentifiers,boolean qualifyNames) {
        String label = table.getLabel();

        if (label == null){ //$NON-NLS-1$
            return null;
        }

        label = label.trim();
        if (label.equals(EMPTY_STRING)) //$NON-NLS-1$
            return null;
        
        String tableName = table.getName();
        String schemaName = table.getSchema().getName();

        if(quoteIdentifiers) {
            tableName = this.getDoubleQuotedString(tableName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if(qualifyNames) {
            tableName = schemaName + DOT + tableName; //$NON-NLS-1$
        }
        
        return LABEL + SPACE + ON  + SPACE + TABLE + SPACE + tableName + SPACE + IS + NEWLINE + this.getSingleQuotedString(label); //$NON-NLS-1$ //$NON-NLS-2$

    }

    public String labelOn(DB2Alias alias, boolean quoteIdentifiers, boolean qualifyNames) {
        String label = alias.getLabel();

        if (label == null || label.equals(EMPTY_STRING)){ //$NON-NLS-1$
            return null;
        }

        String aliasName = alias.getName();
        String schemaName = alias.getSchema().getName();

        if(quoteIdentifiers) {
            aliasName = this.getDoubleQuotedString(aliasName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if(qualifyNames) {
            aliasName = schemaName + DOT + aliasName; //$NON-NLS-1$
        }
        
        return LABEL + SPACE + ON  + SPACE + ALIAS + SPACE + aliasName + SPACE + IS + NEWLINE + this.getSingleQuotedString(label); //$NON-NLS-1$ //$NON-NLS-2$
    }

    
    public String alterTableAlterColumnIdentity(Column column,  boolean quoteIdentifiers, boolean qualifyNames) {
    	Table table = column.getTable();
    	if(!(table instanceof BaseTable)) return null;

    	DB2Column db2Column = (DB2Column) column;
        String columnName = db2Column.getName();
        if(quoteIdentifiers) {
            columnName = this.getDoubleQuotedString(columnName);
        }
        
        String statement = ALTER + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames)
					+ " ALTER COLUMN "+columnName;

        DB2IdentitySpecifier identitySpecifier = (DB2IdentitySpecifier)db2Column.getIdentitySpecifier();
        if (identitySpecifier == null) {   //Drop
        	statement += " DROP IDENTITY";
        } else {				//modify
           	String columnString = EMPTY_STRING;  
	        GenerateType generateType = db2Column.getGenerationType();
	        if(generateType == GenerateType.ALWAYS_LITERAL) {
	        	columnString += " SET GENERATED ALWAYS "; //$NON-NLS-1$
	        }
	        else {
	        	columnString += " SET GENERATED BY DEFAULT ";                 //$NON-NLS-1$
	        }
	            
	        columnString += getIdentityAlterationString(identitySpecifier);

	        statement += columnString;
        }
    	return statement;
    }

    protected String getIdentityString(DB2IdentitySpecifier identitySpecifier) {
    	StringBuffer sb = new StringBuffer();
    	if (identitySpecifier.getStartValue() != null)
    		sb.append(NEWLINE + TAB + TAB + TAB + START_WITH + identitySpecifier.getStartValue());
    	if (identitySpecifier.getIncrement() != null) {
    		sb.append(NEWLINE + TAB + TAB + TAB + INCREMENT_BY + identitySpecifier.getIncrement());
    	}
    	if (identitySpecifier.getMinimum() != null) {
    		sb.append(NEWLINE + TAB +TAB + TAB + MINVALUE + identitySpecifier.getMinimum());
    	}
    	else {
    		sb.append(NEWLINE + TAB +TAB + TAB + NO_MINVALUE);
    	}
    	if (identitySpecifier.getMaximum() != null) {
    		sb.append(NEWLINE + TAB + TAB + TAB + MAXVALUE + identitySpecifier.getMaximum());
    	}
    	else {
    		sb.append(NEWLINE + TAB + TAB + TAB + NO_MAXVALUE);
    	}
    	if(identitySpecifier.isCycleOption()) {
    		sb.append(NEWLINE + TAB  +TAB + TAB + CYCLE);
    	}
    	else {
    		sb.append(NEWLINE + TAB  + TAB + TAB + NO + SPACE + CYCLE);
    	}
    	if (identitySpecifier.getCache() > 1) {
    		sb.append(NEWLINE + TAB  +TAB + TAB + CACHE + SPACE +  identitySpecifier.getCache());
    	} else {
    		sb.append(NEWLINE + TAB  + TAB + TAB +NO + SPACE + CACHE);
    	}
    	if(identitySpecifier.isOrder()) {
    		sb.append(NEWLINE + TAB  + TAB + TAB +ORDER);
    	}
    	else {
    		sb.append(NEWLINE + TAB  + TAB + TAB +NO + SPACE + ORDER);
    	}
        return sb.toString();
    }
 
    protected String getIdentityAlterationString(DB2IdentitySpecifier identitySpecifier) {
    	StringBuffer sb = new StringBuffer();
    	if (identitySpecifier.getStartValue() != null)
    		sb.append(SPACE + RESTART_WITH + identitySpecifier.getStartValue());
    	if (identitySpecifier.getIncrement() != null) {
    		sb.append(SPACE + SET_INCREMENT_BY + identitySpecifier.getIncrement());
    	}
    	if (identitySpecifier.getMinimum() != null) {
    		sb.append(SPACE + SET_MINVALUE + identitySpecifier.getMinimum());
    	}
    	if (identitySpecifier.getMaximum() != null) {
    		sb.append(SPACE + SET_MAXVALUE + identitySpecifier.getMaximum());
    	}
    	if(identitySpecifier.isCycleOption()) {
    		sb.append(SPACE + SET + SPACE + CYCLE);
    	}
    	else {
    		sb.append(SPACE + SET + SPACE + NO + SPACE + CYCLE);
    	}
    	if (identitySpecifier.getCache() > 1) {
    		sb.append(SPACE + SET + SPACE + CACHE + SPACE +  identitySpecifier.getCache());
    	} else {
    		sb.append(SPACE + SET + SPACE + NO + SPACE + CACHE);
    	}
        return sb.toString();
    }

    protected String getParameters(Routine routine, boolean qualifyNames) {
        String parameters = EMPTY_STRING;
        Iterator it = routine.getParameters().iterator();
        while(it.hasNext()) {
            Parameter p = (Parameter) it.next();
            ParameterMode mode = p.getMode();
            if(mode == ParameterMode.INOUT_LITERAL) {
                parameters += INOUT + SPACE;
            }
            else if(mode == ParameterMode.OUT_LITERAL) {
                parameters += OUT + SPACE;
            }
            String name = p.getName();
            if(name != null && name.length() != 0) {
                parameters += p.getName() + SPACE;
            }
            parameters += this.getDataTypeString(p, routine.getSchema(),qualifyNames);
            if(p.isLocator()) {
                parameters += SPACE + AS + SPACE + LOCATOR;
            }
            if(it.hasNext()) {
                parameters += COMMA + NEWLINE + TAB + TAB;                
            }
        }
        return parameters;
    }
    
    protected String getFunctionReturnsClause(DB2UserDefinedFunction function, boolean qualifyNames) {
        if(function.getReturnScalar() != null) {
            Parameter scaler = function.getReturnScalar();
            String text = RETURNS + SPACE + this.getDataTypeString(scaler,function.getSchema(),qualifyNames);
            if(function.getReturnCast() != null) {
                Parameter cast = function.getReturnCast();
                text += SPACE + CAST + SPACE + FROM + SPACE + this.getDataTypeString(cast,function.getSchema(),qualifyNames);
            }
            return text;
        }
        else if(function.getReturnTable() != null) {
            RoutineResultTable resultTable = function.getReturnTable();
            String text = RETURNS + SPACE + TABLE + SPACE + LEFT_PARENTHESIS;
            Iterator it = resultTable.getColumns().iterator();
            while(it.hasNext()) {
                Column c = (Column) it.next();
                text += c.getName() + SPACE + getDataTypeString(c,function.getSchema(),qualifyNames);
                if(it.hasNext()) {
                    text += COMMA + SPACE;
                }
            }
            text += RIGHT_PARENTHESIS;
            return text;
        }
        if(function.getReturnCast() != null) {
            Parameter cast = function.getReturnCast();
            String text = RETURNS + SPACE + this.getDataTypeString(cast,function.getSchema(),qualifyNames);
            return text;
        }
        else {
            return function.getReturnClause();
        }
    }

    protected String getSpecificOption(Routine routine,boolean quoteIdentifiers, boolean qualifyNames) {
        String specificName = routine.getSpecificName(); 
        if(specificName != null && specificName.length() != 0) {
            String schemaName = routine.getSchema().getName();

            if(quoteIdentifiers) {
            	specificName = this.getDoubleQuotedString(specificName);
                schemaName  = this.getDoubleQuotedString(schemaName);
            }
            if(qualifyNames) {
            	specificName = schemaName + DOT + specificName;
            }

            return  NEWLINE + TAB + SPECIFIC + SPACE + specificName;
        }
        return EMPTY_STRING;
    }
    
    protected String getParameterStyleOption(Routine routine) {
    	String parameterStyle = routine.getParameterStyle();
        if ( parameterStyle == null || parameterStyle.trim().isEmpty() ) {
        	return EMPTY_STRING;
        }           
        if ( parameterStyle.trim().equals("GNRLNULL") ) {  //$NON-NLS-1$
        	parameterStyle = "GENERAL WITH NULLS"; //$NON-NLS-1$
        }
        return NEWLINE + TAB + "PARAMETER STYLE " + parameterStyle; //$NON-NLS-1$
    }

    protected String getDeterministicOption(Routine routine) {
        if(routine.isDeterministic()) {
            return NEWLINE + TAB + "DETERMINISTIC"; //$NON-NLS-1$
        } 
        return EMPTY_STRING;
    }
    
    protected String getLanguageOption(Routine routine) {	  
    	String language = routine.getLanguage();        
        if ( language == null || language.trim().isEmpty() ) {
        	return EMPTY_STRING;
        }

        return NEWLINE + TAB + "LANGUAGE " + language; //$NON-NLS-1$
    }

    /**
     * The input routine must be a JAVA routine.
     * It looks like zSeries JAVA routine's externalName field does not include a JAR ID
     * after catalog load. The zSeries DDL builder can compensate this by implementing
     * this method. 
     * @param routine
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return the Jar ID of the JAVA routine or null
     */
    protected String getJarID (Routine routine, boolean quoteIdentifiers, boolean qualifyNames) {
    	return null;
    }
    
    /**
     * Returns the JAR name of the DB2Jar object or null.
     * @param jar
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     */
    protected String getName(DB2Jar jar, boolean quoteIdentifiers, boolean qualifyNames) {
        String jarschema = null;
        String jarname = null;
        if (jar != null) {
            if (jar.getSchema() != null) {
                jarschema = jar.getSchema().getName();
            }                  
            jarname = jar.getName();                    
        }
        StringBuffer jaridbuf = new StringBuffer(50);
        if (jarname != null && jarname.length() > 0) {
            if(quoteIdentifiers) {
                jarname = this.getDoubleQuotedString(jarname);
            }
            jaridbuf.append(jarname);   
        }
        if (jarschema != null && jarschema.length() > 0) {
            if(quoteIdentifiers) {
            	jarschema = this.getDoubleQuotedString(jarschema);
            }
            jaridbuf.insert(0, '.');
            jaridbuf.insert(0, jarschema);
        }
        if (jaridbuf.length() == 0) {
        	return null;
        }
        else {
        	return jaridbuf.toString();
        }
    }

    protected String getExternalNameOption(Routine routine,boolean quoteIdentifiers, boolean qualifyNames) {
        String option = NEWLINE + TAB + EXTERNAL;
        String externalName = routine.getExternalName();
        if(externalName != null && externalName.length() != 0) {
            String schemaName = routine.getSchema().getName();
            
            if (routine.getLanguage().equalsIgnoreCase("JAVA")){ //$NON-NLS-1$
            	//RATLC00399525 Sev 2 CUST:FORUM:Generate DDL shows incorrect External Name for z/OS Java stored procedure
            	//The External Name that was constructed by the DDL generator based on the value of 
            	//the externalName field of the routine object. The externalName was set during catalog load based on 
            	//the EXTERNAL_NAME column of SYSIBM.SYSROUTINES. The jar name is not part of the externalName.
            	//The fix here is to add the jar name to the external name clause when constructing the clause for 
            	//zSeries Java procedures. This is not needed for LUW or iSeries Java procedures because their 
            	//externalName field has been loaded with the jar name.
            	String jarid = getJarID(routine, quoteIdentifiers, qualifyNames);
            	if (jarid != null) {
            		externalName = jarid + ":" + externalName;
            	}            	            	
            	externalName = this.getSingleQuotedString(externalName);
            } else {
	            if(quoteIdentifiers) {
	            	externalName = this.getDoubleQuotedString(externalName);
//	                schemaName  = this.getDoubleQuotedString(schemaName);
//	            }
//	            if(qualifyNames) {
//	            	externalName = schemaName + DOT + externalName;
	            }
            }

            option += " NAME " + externalName; //$NON-NLS-1$
        }
        return option;
    }
    protected String getDataAccessOption(Routine routine) {
        DataAccess dataAccess = routine.getSqlDataAccess();
        if(dataAccess == DataAccess.CONTAINS_SQL_LITERAL) {
            return NEWLINE + TAB + "CONTAINS SQL"; //$NON-NLS-1$
        }
        else if(dataAccess == DataAccess.MODIFIES_SQL_DATA_LITERAL) {
            return NEWLINE + TAB + "MODIFIES SQL DATA"; //$NON-NLS-1$
        }
        else if(dataAccess == DataAccess.NO_SQL_LITERAL) {
            return NEWLINE + TAB + "NO SQL"; //$NON-NLS-1$
        }
        else if(dataAccess == DataAccess.READS_SQL_DATA_LITERAL) {
            return NEWLINE + TAB + "READS SQL DATA"; //$NON-NLS-1$
        }
        return EMPTY_STRING;
    }
    protected String getSecurityOption(Routine routine) {
        String security = routine.getSecurity();
        if(security != null && security.length() != 0) {
            return NEWLINE + TAB + "SECURITY " + security; //$NON-NLS-1$
        }
        return EMPTY_STRING;
    }
    protected String getDBInfoOption(DB2Routine routine) {
        if(routine.isDbInfo()) {
            return NEWLINE + TAB + "DBINFO"; //$NON-NLS-1$
        }
        return EMPTY_STRING;
    }
    protected String getParameterCcsidOption(DB2Routine routine) {
        String ccsid = routine.getParmCcsid();
        if(ccsid != null && ccsid.length() != 0) {
            return NEWLINE + TAB + "PARAMETER CCSID " + ccsid; //$NON-NLS-1$
        }
        return EMPTY_STRING;
    }
    protected String getProgramTypeOption(DB2Routine routine) {
        String programType = routine.getProgramType();
        if(programType != null && programType.length() != 0) {
            return NEWLINE + TAB + "PROGRAM TYPE " + programType; //$NON-NLS-1$
        }
        return EMPTY_STRING;
    }
    protected String getFederatedOption(DB2Routine routine) {
        if(routine.isFederated()) {
            return NEWLINE + TAB + "FEDERATED"; //$NON-NLS-1$
        }
        return EMPTY_STRING;
    }
    protected String getFencedOption(DB2Routine routine) {
        String fenced = routine.getFenced();
        if (fenced == null) fenced = "FENCED"; //$NON-NLS-1$
        if(fenced.equalsIgnoreCase("FENCED")) { //$NON-NLS-1$
        	String threadsafe = routine.getThreadsafe();
        	if (threadsafe == null) threadsafe ="THREADSAFE"; //$NON-NLS-1$
            fenced += SPACE + threadsafe;
        }
        return NEWLINE + TAB + fenced;
    }
    protected String getNullCallOption(Function function) {
        if(function.isNullCall()) {
            return NEWLINE + TAB + "CALLED ON NULL INPUT"; //$NON-NLS-1$
        }
        return EMPTY_STRING;
    }
    protected String getTransformGroupOption(Function function) {
        String transform = function.getTransformGroup();
        if(transform != null && transform.length() != 0) {
            return NEWLINE + TAB + "TRANSFORM GROUP " + transform; //$NON-NLS-1$
        }
        return EMPTY_STRING;
    }
    protected String getDynamicResultSetsOption(Procedure procedure) {
        int rs = procedure.getMaxResultSets();
        if(rs > 0) {
            return NEWLINE + TAB + "DYNAMIC RESULT SETS " + rs; //$NON-NLS-1$
        }
        return EMPTY_STRING;
    }
    protected String getExternalActionOption(DB2Function function) {
        if(!function.isExternalAction()) {
            return NEWLINE + TAB + "NO EXTERNAL ACTION"; //$NON-NLS-1$
        }
        return EMPTY_STRING;
    }
    protected String getCardinalityOption(DB2Function function) {
        int c = function.getCardinality();
        if(c > 0) {
            return NEWLINE + TAB + "CARDINALITY " + c; //$NON-NLS-1$            
        }
        return EMPTY_STRING;
    }
    protected String getParallelOption(DB2Function function) {
        if(function.isAllowParallel()) {
            return NEWLINE + TAB + "ALLOW PARALLEL"; //$NON-NLS-1$
        }
        else {
            return NEWLINE + TAB + "DISALLOW PARALLEL"; //$NON-NLS-1$
        }
    }
    protected String getFinalCallOption(DB2Function function) {
        if(function.isFinalCall()) {
            return NEWLINE + TAB + "FINAL CALL"; //$NON-NLS-1$
        }
        return EMPTY_STRING;
    }
    protected String getScratchPadCallOption(DB2Function function) {
        int scratch = function.getScratchPadLength();
        if(scratch > 0) {
            return NEWLINE + TAB + "SCRATCHPAD " + scratch; //$NON-NLS-1$
        }
        return EMPTY_STRING;
    }
    protected String getPredicatesOption(DB2Function function) {
        String predicates = function.getPredicate();
        if(predicates != null && predicates.length() != 0) {
            return NEWLINE + TAB + "PREDICATES " + LEFT_PARENTHESIS + predicates + RIGHT_PARENTHESIS; //$NON-NLS-1$
        }
        return EMPTY_STRING;
    }

    public String getEnforcedOption(Constraint constraint) {
    	if (!constraint.isEnforced()) {
    		return NEWLINE + TAB + "NOT ENFORCED"; //$NON-NLS-1$
    	}
		return EMPTY_STRING;
    }

    //this is for PB generate routine body according to the signature. 
    public String createRoutineStatement(DB2Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {
    	Database database = procedure.getSchema().getDatabase();
    	RoutineDdlBuilder routineDdlBuilder = DdlBuilder.getRoutineDdlBuilder(database.getVendor(),database.getVersion());
    	if (routineDdlBuilder != null) {
    		String ddl = routineDdlBuilder.buildCreateRoutineStatement(procedure,quoteIdentifiers,qualifyNames);
    		if (ddl != null) return ddl;
    	}
    	
    	String text = CREATE + SPACE + PROCEDURE + SPACE + getName(procedure, quoteIdentifiers, qualifyNames)
    	+ SPACE + LEFT_PARENTHESIS + getParameters(procedure,qualifyNames) + RIGHT_PARENTHESIS;

        text += getSpecificOption(procedure,quoteIdentifiers,qualifyNames);
        text += getDataAccessOption(procedure);
        text += getDeterministicOption(procedure);
        text += getDynamicResultSetsOption(procedure);
        text += getFederatedOption(procedure);
    
        String language = procedure.getLanguage();
        if (language == null) language = "SQL"; //$NON-NLS-1$
        if(language.equalsIgnoreCase("SQL")) { //$NON-NLS-1$
            text += TAB + "LANGUAGE " + language + NEWLINE; //$NON-NLS-1$
            text += procedure.getSource().getBody();
        }
        else {
            text += NEWLINE + TAB + "LANGUAGE " + language; //$NON-NLS-1$
            text += getFencedOption(procedure);
            text += getParameterStyleOption(procedure);
            text += getProgramTypeOption(procedure);
            text += getDBInfoOption(procedure);
            text += getExternalNameOption(procedure,quoteIdentifiers,qualifyNames);
        }
        return text;      
    }

    //this is for PB generate routine body according to the signature. 
    public String createRoutineStatement(DB2UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames) {
    	Database database = function.getSchema().getDatabase();
    	RoutineDdlBuilder routineDdlBuilder = DdlBuilder.getRoutineDdlBuilder(database.getVendor(),database.getVersion());
    	if (routineDdlBuilder != null) {
    		String ddl = routineDdlBuilder.buildCreateRoutineStatement(function,quoteIdentifiers,qualifyNames);
    		if (ddl != null) return ddl;
    	}

    	String text = CREATE + SPACE + FUNCTION + SPACE + getName(function, quoteIdentifiers, qualifyNames)
        	+ SPACE + LEFT_PARENTHESIS + getParameters(function,qualifyNames) + RIGHT_PARENTHESIS + NEWLINE
        	+ TAB + getFunctionReturnsClause(function,qualifyNames);

        text += getSpecificOption(function,quoteIdentifiers,qualifyNames);
        text += getDeterministicOption(function);
        text += getDataAccessOption(function);
        text += getExternalActionOption(function);
        text += getNullCallOption(function);
        text += getParallelOption(function);
        text += getFederatedOption(function);
        
        String language = function.getLanguage();
        if (language == null) language = "SQL"; //$NON-NLS-1$
        if(language.equalsIgnoreCase("SQL")) { //$NON-NLS-1$
           text += function.getSource().getBody();
        }
        else {
            text += NEWLINE + TAB + "LANGUAGE " + language; //$NON-NLS-1$
            text += getCardinalityOption(function);
            text += getDBInfoOption(function);
            text += getExternalNameOption(function,quoteIdentifiers,qualifyNames);
            text += getFencedOption(function);
            text += getFinalCallOption(function);
            text += getParameterStyleOption(function);
            text += getParameterCcsidOption(function);
            text += getPredicatesOption(function);
            text += getScratchPadCallOption(function);
            text += getSecurityOption(function);
        }
        return text;      
    }
    
    protected String getTriggerReferenceNewTable(){
    	return NEW_TABLE;
    }

    protected String getTriggerReferenceOldTable(){
    	return OLD_TABLE;
    }

	protected String getDataCapture(DB2Table table) {
		DataCaptureType validProc = table.getDataCapture();
		if (validProc == DataCaptureType.NONE_LITERAL) {
			return NEWLINE + TAB + "DATA CAPTURE NONE ";
		} else {
			return NEWLINE + TAB + "DATA CAPTURE CHANGES ";
		}
	}
	
	protected String getGrantUseOfStatement(Privilege privilege, boolean quoteIdentifiers, boolean qualifyNames) {
		String ret = NEWLINE + GRANT + SPACE + privilege.getAction() + SPACE + OF + SPACE 
		+ getPrivilegedObjectTypeString(privilege) + SPACE
		+ getPrivilegedObjectName(privilege, quoteIdentifiers, qualifyNames) 
		+ SPACE + TO + SPACE + getGranteeSubstring(privilege.getGrantee(), quoteIdentifiers);
		if (privilege.isGrantable()) ret += SPACE + WITH + SPACE + GRANT + SPACE + OPTION;
		return ret;
	}

	protected String getRevokeUseOfStatement(Privilege privilege, boolean quoteIdentifiers, boolean qualifyNames) {
		String ret = NEWLINE + REVOKE + SPACE + privilege.getAction() + SPACE + OF + SPACE 
		+ getPrivilegedObjectTypeString(privilege) + SPACE
		+ getPrivilegedObjectName(privilege, quoteIdentifiers, qualifyNames) 
		+ SPACE + FROM + SPACE + getGranteeSubstring(privilege.getGrantee(), quoteIdentifiers);
		return ret;
	}
	
}
