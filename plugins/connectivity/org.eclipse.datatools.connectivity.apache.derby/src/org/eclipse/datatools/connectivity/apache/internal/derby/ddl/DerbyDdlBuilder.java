/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/


package org.eclipse.datatools.connectivity.apache.internal.derby.ddl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlBuilder;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType;
import org.eclipse.datatools.modelbase.sql.tables.ActionTimeType;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;

public class DerbyDdlBuilder extends GenericDdlBuilder {
 
    protected final static String IN                   = "IN"; //$NON-NLS-1$
    protected final static String OUT                  = "OUT"; //$NON-NLS-1$
    protected final static String INOUT                = "INOUT"; //$NON-NLS-1$
    protected final static String LOCATOR              = "LOCATOR"; //$NON-NLS-1$
    protected final static String EXTERNAL             = "EXTERNAL"; //$NON-NLS-1$
    protected final static String RETURNS              = "RETURNS"; //$NON-NLS-1$
    protected final static String CAST                 = "CAST"; //$NON-NLS-1$
    protected final static String FROM                 = "FROM"; //$NON-NLS-1$
    protected final static String COMMENT              = "COMMENT"; //$NON-NLS-1$
    protected final static String IS                   = "IS"; //$NON-NLS-1$
    protected final static String COLUMN               = "COLUMN"; //$NON-NLS-1$
    protected final static String MODE_DB2SQL          = "MODE DB2SQL"; //$NON-NLS-1$
	
    public String createProcedure(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {

        Database database = procedure.getSchema().getDatabase();

        String text = CREATE + SPACE + PROCEDURE + SPACE + getName(procedure, quoteIdentifiers, qualifyNames)
    	+ SPACE + LEFT_PARENTHESIS + getParameters(procedure) + RIGHT_PARENTHESIS;

        text += NEWLINE + TAB + "LANGUAGE " + procedure.getLanguage(); //$NON-NLS-1$
        text += getExternalNameOption(procedure,quoteIdentifiers,qualifyNames);
        text += getParameterStyleOption(procedure);
    	
        return text;      
    }
	
    public String createUserDefinedFunction(UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames) {
    	Database database = function.getSchema().getDatabase();

    	String text = CREATE + SPACE + FUNCTION + SPACE + getName(function, quoteIdentifiers, qualifyNames);
    	
    	Source source = function.getSource();
    	if (source != null) {
    		text += source.getBody();
    	}
    	text += getExternalNameOption(function,quoteIdentifiers,qualifyNames);
        
        return text;      
    }

    public String dropProcedure(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {
    	Database database = procedure.getSchema().getDatabase();
   		return DROP + SPACE + PROCEDURE + SPACE + getName(procedure, quoteIdentifiers, qualifyNames);
    }
    	
    public String dropFunction(UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames) {
    	Database database = function.getSchema().getDatabase();
   		return DROP + SPACE + FUNCTION + SPACE + getName(function, quoteIdentifiers, qualifyNames);
    }
    
    
    protected String getColumnString(Column column, boolean quoteIdentifiers) {
        String columnDefinition =  super.getColumnString(column, quoteIdentifiers);
        IdentitySpecifier spec = column.getIdentitySpecifier();
        ValueExpression v = column.getGenerateExpression();
        if(spec != null) {
            columnDefinition += " GENERATED ALWAYS AS IDENTITY "; //$NON-NLS-1$
            columnDefinition += SPACE + LEFT_PARENTHESIS + this.getIdentityString(spec) + RIGHT_PARENTHESIS;
        }
        else if(v != null){
            columnDefinition += " GENERATED ALWAYS AS " + v.getSQL();             //$NON-NLS-1$
        }

        return columnDefinition;
    }
	
    protected String getIdentityString(IdentitySpecifier identitySpecifier) {
        String clause = "START WITH " + identitySpecifier.getStartValue() //$NON-NLS-1$
        	+ " ,INCREMENT BY " + identitySpecifier.getIncrement(); //$NON-NLS-1$
        return clause;
    }
    
    protected String getParameters(Routine routine) {
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
            parameters += this.getDataTypeString(p, routine.getSchema());
            if(p.isLocator()) {
                parameters += SPACE + AS + SPACE + LOCATOR;
            }
            if(it.hasNext()) {
                parameters += COMMA + NEWLINE + TAB + TAB;                
            }
        }
        return parameters;
    }
    
    protected String getExternalNameOption(Routine routine,boolean quoteIdentifiers, boolean qualifyNames) {
        String option = NEWLINE + TAB + EXTERNAL;
        String externalName = routine.getExternalName();
        if(externalName != null && externalName.length() != 0) {
            String schemaName = routine.getSchema().getName();
            
            if (routine.getLanguage().equalsIgnoreCase("JAVA")){ //$NON-NLS-1$
            	externalName = this.getSingleQuotedString(externalName);
            } else {
	            if(quoteIdentifiers) {
	            	externalName = this.getDoubleQuotedString(externalName);
	                schemaName  = this.getDoubleQuotedString(schemaName);
	            }
	            if(qualifyNames) {
	            	externalName = schemaName + DOT + externalName;
	            }
            }

            option += " NAME " + externalName; //$NON-NLS-1$
        }
        return option;
    }
 
    protected String getParameterStyleOption(Routine routine) {
        return NEWLINE + TAB + "PARAMETER STYLE " + routine.getParameterStyle(); //$NON-NLS-1$
    }

    protected String getFunctionReturnsClause(UserDefinedFunction function) {
        if(function.getReturnScalar() != null) {
            Parameter scaler = function.getReturnScalar();
            String text = RETURNS + SPACE + this.getDataTypeString(scaler,function.getSchema());
            if(function.getReturnCast() != null) {
                Parameter cast = function.getReturnCast();
                text += SPACE + CAST + SPACE + FROM + SPACE + this.getDataTypeString(cast,function.getSchema());
            }
            return text;
        }
        else if(function.getReturnTable() != null) {
            RoutineResultTable resultTable = function.getReturnTable();
            String text = RETURNS + SPACE + TABLE + SPACE + LEFT_PARENTHESIS;
            Iterator it = resultTable.getColumns().iterator();
            while(it.hasNext()) {
                Column c = (Column) it.next();
                text += c.getName() + SPACE + getDataTypeString(c,function.getSchema());
                if(it.hasNext()) {
                    text += COMMA + SPACE;
                }
            }
            text += RIGHT_PARENTHESIS;
            return text;
        }
        else {
        	Parameter parameter = function.getReturnCast();
        	if (parameter != null) {
        		return parameter.getName();
        	} else {
        		return null;
        	}
        }
    }
    
    public String createTrigger(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames) {
        String statement = CREATE + SPACE + TRIGGER + SPACE + getName(trigger, quoteIdentifiers, qualifyNames) + SPACE;

        final ActionTimeType actionTime = trigger.getActionTime();
        if(actionTime == ActionTimeType.AFTER_LITERAL) {
            statement += AFTER;
        }
        else if(actionTime == ActionTimeType.BEFORE_LITERAL) {
            statement += BEFORE;
        }
        else if(actionTime == ActionTimeType.INSTEADOF_LITERAL) {
            statement += INSTEAD_OF;
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
                    statement += column.getName();
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

        if(newRow != null && newRow.length() != 0) {
            statement += REFERENCING + SPACE + NEW + SPACE + AS + SPACE + newRow + NEWLINE;
        }
        if(oldRow != null && oldRow.length() != 0) {
            statement += REFERENCING + SPACE + OLD + SPACE + AS + SPACE + oldRow + NEWLINE;
        }

        if(trigger.getActionGranularity() == ActionGranularityType.ROW_LITERAL) {
            statement += FOR + SPACE + EACH + SPACE + ROW + SPACE + MODE_DB2SQL + NEWLINE;
    	}
        else {
            statement += FOR + SPACE + EACH + SPACE + STATEMENT + SPACE + MODE_DB2SQL + NEWLINE;
    	}

        Iterator it = trigger.getActionStatement().iterator();
        while(it.hasNext()) {
            SQLStatement s = (SQLStatement) it.next();
            statement += s.getSQL();
        }

        return statement;
    }
    
}

