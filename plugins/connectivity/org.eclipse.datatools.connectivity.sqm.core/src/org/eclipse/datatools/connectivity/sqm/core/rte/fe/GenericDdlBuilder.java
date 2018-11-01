/*******************************************************************************
 * Copyright (c) 2007, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.rte.fe;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.IEngineeringCallBack;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.GenericCatalogMessages;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.constraints.Assertion;
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
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.Domain;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.routines.Method;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType;
import org.eclipse.datatools.modelbase.sql.tables.ActionTimeType;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.CheckType;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.ecore.EObject;

import com.ibm.icu.text.MessageFormat;
import com.ibm.icu.util.StringTokenizer;

public class GenericDdlBuilder {
    protected final static String NEWLINE              = System.getProperty("line.separator"); //$NON-NLS-1$
    protected final static String EMPTY_STRING         = ""; //$NON-NLS-1$
    protected final static String DOT                  = "."; //$NON-NLS-1$
    protected final static String SPACE                = " "; //$NON-NLS-1$
    protected final static String COMMA                = ","; //$NON-NLS-1$
    protected final static String SINGLE_QUOTE         = "'"; //$NON-NLS-1$
    protected final static String DOUBLE_QUOTE         = "\""; //$NON-NLS-1$
    protected final static String TAB                  = "\t"; //$NON-NLS-1$
    protected final static String LEFT_PARENTHESIS     = "("; //$NON-NLS-1$
    protected final static String RIGHT_PARENTHESIS    = ")"; //$NON-NLS-1$
    protected final static String DROP                 = "DROP"; //$NON-NLS-1$
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
    protected final static String TRIGGER              = "TRIGGER"; //$NON-NLS-1$
    protected final static String TABLE                = "TABLE"; //$NON-NLS-1$
    protected final static String VIEW                 = "VIEW"; //$NON-NLS-1$
    protected final static String INDEX                = "INDEX"; //$NON-NLS-1$
    protected final static String PROCEDURE            = "PROCEDURE"; //$NON-NLS-1$
    protected final static String FUNCTION             = "FUNCTION"; //$NON-NLS-1$
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
    protected final static String NEW_TABLE            = "NEW TABLE"; //$NON-NLS-1$
    protected final static String OLD_TABLE            = "OLD TABLE"; //$NON-NLS-1$
    protected final static String EACH                 = "EACH"; //$NON-NLS-1$
    protected final static String ROW                  = "ROW"; //$NON-NLS-1$
    protected final static String STATEMENT            = "STATEMENT"; //$NON-NLS-1$
    protected final static String WHEN                 = "WHEN"; //$NON-NLS-1$
    protected static final String LANGUAGE             = "LANGUAGE"; //$NON-NLS-1$
    protected static final String PARAMETER_STYLE      = "PARAMETER STYLE"; //$NON-NLS-1$
    protected static final String DETERMINISTIC        = "DETERMINISTIC"; //$NON-NLS-1$
    protected static final String NOT_DETERMINISTIC    = "NOT DETERMINISTIC"; //$NON-NLS-1$
    protected static final String DYNAMIC_RESULT_SETS  = "DYNAMIC RESULT SETS"; //$NON-NLS-1$
    protected static final String RETURNS              = "RETURNS"; //$NON-NLS-1$
    protected static final String CAST_FROM            = "CAST FROM"; //$NON-NLS-1$
    protected static final String RETURNS_NULL_ON_NULL_INPUT = "RETURNS NULL ON NULL INPUT"; //$NON-NLS-1$
    protected static final String CALLED_ON_NULL_INPUT = "CALLED ON NULL INPUT"; //$NON-NLS-1$
    protected static final String TRANSFORM_GROUP      = "TRANSFORM GROUP"; //$NON-NLS-1$
    protected static final String STATIC_DISPATCH      = "STATIC DISPATCH"; //$NON-NLS-1$
    protected static final String SCHEMA               = "SCHEMA"; //$NON-NLS-1$
    protected static final String AUTHORIZATION        = "AUTHORIZATION"; //$NON-NLS-1$
    protected static final String GLOBAL               = "GLOBAL"; //$NON-NLS-1$
    protected static final String TEMPORARY            = "TEMPORARY"; //$NON-NLS-1$
    protected static final String ON_COMMIT            = "ON COMMIT"; //$NON-NLS-1$
    protected static final String PRESERVE             = "PRESERVE"; //$NON-NLS-1$
    protected static final String ROWS                 = "ROWS"; //$NON-NLS-1$
    protected static final String UNDER                = "UNDER"; //$NON-NLS-1$
    protected static final String INSTANTIABLE         = "INSTANTIABLE"; //$NON-NLS-1$
    protected static final String NOT_INSTANTIABLE     = "NOT INSTANTIABLE"; //$NON-NLS-1$
    protected static final String FINAL                = "FINAL"; //$NON-NLS-1$
    protected static final String NOT_FINAL            = "NOT FINAL"; //$NON-NLS-1$
    protected static final String OVERRIDING           = "OVERRIDING"; //$NON-NLS-1$
    protected static final String STATIC               = "STATIC"; //$NON-NLS-1$
    protected static final String INSTANCE             = "INSTANCE"; //$NON-NLS-1$
    protected static final String SPECIFIC             = "SPECIFIC"; //$NON-NLS-1$
    protected static final String DOMAIN               = "DOMAIN"; //$NON-NLS-1$
    protected static final String REFERENCES_ARE_CHECKED = "REFERENCES ARE CHECKED"; //$NON-NLS-1$
    protected static final String REFERENCES_ARE_NOT_CHECKED = "REFERENCES ARE NOT CHECKED"; //$NON-NLS-1$
	protected static final String ASSERTION            = "ASSERTION"; //$NON-NLS-1$

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

    public String dropView(ViewTable view, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + VIEW + SPACE + getName(view, quoteIdentifiers, qualifyNames);
    }

    public String dropTableConstraint(TableConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames) {
        return ALTER + SPACE + TABLE + SPACE + getName(constraint.getBaseTable(), quoteIdentifiers, qualifyNames)
        	+ " DROP CONSTRAINT " + getName(constraint, quoteIdentifiers); //$NON-NLS-1$
    }
    
    public String dropIndex(Index index, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + INDEX + SPACE + getName(index, quoteIdentifiers, qualifyNames);
    }

    public String dropTable(BaseTable table, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames);
    }

	public String dropProcedure(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + PROCEDURE + SPACE + getName(procedure, quoteIdentifiers, qualifyNames);
	}

	public String dropFunction(UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + FUNCTION + SPACE + getName(function, quoteIdentifiers, qualifyNames);
	}

	public String dropSchema(Schema schema, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + SCHEMA + SPACE + getName(schema, quoteIdentifiers, qualifyNames);
	}

	public String dropUserDefinedType(UserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames) {
		if (type instanceof Domain) {
	        return DROP + SPACE + DOMAIN + SPACE + getName(type, quoteIdentifiers, qualifyNames);
		}
        return DROP + SPACE + TYPE + SPACE + getName(type, quoteIdentifiers, qualifyNames);
	}

	public String dropAssertion(Assertion assertion, boolean quoteIdentifiers, boolean qualifyNames) {
        return DROP + SPACE + ASSERTION + SPACE + getName(assertion, quoteIdentifiers, qualifyNames);
	}

	public String createTable(BaseTable table, boolean quoteIdentifiers, boolean qualifyNames) {
        StringBuffer statement = new StringBuffer();
        boolean isTemp = table instanceof TemporaryTable;
        
        statement.append(CREATE).append(SPACE);
        if (isTemp) {
        	if (((TemporaryTable)table).isLocal()) {
        		statement.append(LOCAL).append(SPACE);
        	}
        	else {
        		statement.append(GLOBAL).append(SPACE);
        	}
        	statement.append(TEMPORARY).append(SPACE);
        }
        statement.append(TABLE).append(SPACE).append(getName(table, quoteIdentifiers, qualifyNames)).append(SPACE);
        
        statement.append(LEFT_PARENTHESIS).append(NEWLINE);
        Iterator it = table.getColumns().iterator();
        while(it.hasNext()) {
            Column column = (Column) it.next();
            statement.append(TAB).append(TAB).append(getColumnString(column, quoteIdentifiers));
            if(it.hasNext()) {
                statement.append(COMMA);                
            }
            statement.append(NEWLINE);                
        }
        statement.append(TAB).append(RIGHT_PARENTHESIS);
        
        if (isTemp) {
        	statement.append(NEWLINE).append(TAB).append(ON_COMMIT).append(SPACE);
        	if (((TemporaryTable)table).isDeleteOnCommit()) {
        		statement.append(DELETE).append(SPACE);
        	}
        	else {
        		statement.append(PRESERVE).append(SPACE);
        	}
        	statement.append(ROWS);
        }
        
        return statement.toString();
    }
    
    public String alterTableAddColumn(Column column,  boolean quoteIdentifiers, boolean qualifyNames) {
    	Table table = column.getTable();
    	if(table instanceof BaseTable) {
	        String statement = ALTER + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames)
	    		+ " ADD COLUMN " + getColumnString(column, quoteIdentifiers); //$NON-NLS-1$
	        return statement;
    	}
    	return null;
    }

    public String createView(ViewTable view, boolean quoteIdentifiers, boolean qualifyNames) {
        String viewDefinition = CREATE + SPACE;
        viewDefinition += VIEW + SPACE + getName(view, quoteIdentifiers, qualifyNames) + SPACE;
        
        String columns = getViewColumnList(view);
        if(columns != null) {
            viewDefinition += LEFT_PARENTHESIS + columns + RIGHT_PARENTHESIS + SPACE;
        }
        // make sure the queryExpression exists
        if(view.getQueryExpression()!=null){
            viewDefinition += AS + NEWLINE;
            viewDefinition += view.getQueryExpression().getSQL();
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
    
    
    public String createIndex(Index index, boolean quoteIdentifiers, boolean qualifyNames) {
        String statement = CREATE + SPACE;
        if(index.isUnique()) {
            statement += UNIQUE + SPACE;
        }
        statement += INDEX + SPACE + getName(index, quoteIdentifiers, qualifyNames) + SPACE + ON + SPACE
        	+ getName(index.getTable(), quoteIdentifiers, qualifyNames) + SPACE + LEFT_PARENTHESIS
        	+ getIndexKeyColumns(index, quoteIdentifiers) + RIGHT_PARENTHESIS;
        return statement;
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
            statement += REFERENCING + SPACE + NEW + SPACE + ROW + SPACE + AS + SPACE + newRow + NEWLINE;
        }
        if(oldRow != null && oldRow.length() != 0) {
            statement += REFERENCING + SPACE + OLD + SPACE + ROW + SPACE + AS + SPACE + oldRow + NEWLINE;
        }
        if(newTable != null && newTable.length() != 0) {
            statement += REFERENCING + SPACE + NEW + SPACE + TABLE + SPACE + AS + SPACE + newTable + NEWLINE;
        }
        if(oldTable != null && oldTable.length() != 0) {
            statement += REFERENCING + SPACE + OLD + SPACE + TABLE + SPACE + AS + SPACE + oldTable + NEWLINE;
        }

        if(trigger.getActionGranularity() == ActionGranularityType.ROW_LITERAL) {
            statement += FOR + SPACE + EACH + SPACE + ROW + NEWLINE;
    	}
        else {
            statement += FOR + SPACE + EACH + SPACE + STATEMENT + NEWLINE;
    	}

        String triggerBody = "";
        Iterator it = trigger.getActionStatement().iterator();
        while(it.hasNext()) {
            SQLStatement s = (SQLStatement) it.next();
            triggerBody += s.getSQL();
        }

        if (triggerBody.equals("")) {
	    	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
	    			GenericCatalogMessages.FE_TRIGGER_ACTION_EMPTY, new Object[] { getName(trigger, false, true)}));
	    	return null;
        }

    	statement += triggerBody;

        return statement;
    }
    
    public String createProcedure(Procedure procedure,
			boolean quoteIdentifiers, boolean qualifyNames) {
		StringBuffer statement = new StringBuffer();

		// CREATE PROCEDURE procedure_name
		statement.append(CREATE).append(SPACE).append(PROCEDURE).append(SPACE)
				.append(getName(procedure, quoteIdentifiers, qualifyNames))
				.append(SPACE);

		// parameters
		statement.append(getParameterListClause(procedure,quoteIdentifiers));

		// begin characteristics
		// language
		if (procedure.getLanguage() != null
				&& procedure.getLanguage().length() > 0) {
			statement.append(TAB).append(LANGUAGE).append(SPACE).append(
					procedure.getLanguage()).append(NEWLINE);
		}

		// parameter style
		if (procedure.getParameterStyle() != null
				&& procedure.getParameterStyle().length() > 0) {
			statement.append(TAB).append(PARAMETER_STYLE).append(SPACE).append(
					procedure.getParameterStyle()).append(NEWLINE);
		}

		// determinism
		if (procedure.isDeterministic()) {
			statement.append(TAB).append(DETERMINISTIC).append(NEWLINE);
		}
		else {
			statement.append(TAB).append(NOT_DETERMINISTIC).append(NEWLINE);
		}

		// SQL data access
		if (procedure.getSqlDataAccess() != null) {
			statement.append(TAB).append(
					procedure.getSqlDataAccess().toString()).append(NEWLINE);
		}

		// null-call
		// nothing to do

		// transform group
		// nothing to do

		// dynamic result sets
		if (procedure.getMaxResultSets() > 0) {
			statement.append(TAB).append(DYNAMIC_RESULT_SETS).append(SPACE)
					.append(procedure.getMaxResultSets()).append(NEWLINE);
		}
		// end characteristics

		// body
		if (procedure.getSource() != null) {
			String body = procedure.getSource().getBody();
			if (body != null && body.length() > 0) {
				statement.append(body).append(NEWLINE);
			}
		}

		return statement.toString();
	}

    public String createUserDefinedFunction(UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames) {
		StringBuffer statement = new StringBuffer();

		// CREATE FUNCTION function_name
		statement.append(CREATE).append(SPACE).append(FUNCTION).append(SPACE)
				.append(getName(function, quoteIdentifiers, qualifyNames))
				.append(SPACE);

		// parameters
		statement.append(getParameterListClause(function,quoteIdentifiers));
		
		// returns
		statement.append(getReturnsClause(function,quoteIdentifiers));

		// begin characteristics
		// language
		if (function.getLanguage() != null
				&& function.getLanguage().length() > 0) {
			statement.append(TAB).append(LANGUAGE).append(SPACE).append(
					function.getLanguage()).append(NEWLINE);
		}

		// parameter style
		if (function.getParameterStyle() != null
				&& function.getParameterStyle().length() > 0) {
			statement.append(TAB).append(PARAMETER_STYLE).append(SPACE).append(
					function.getParameterStyle()).append(NEWLINE);
		}

		// determinism
		if (function.isDeterministic()) {
			statement.append(TAB).append(DETERMINISTIC).append(NEWLINE);
		}
		else {
			statement.append(TAB).append(NOT_DETERMINISTIC).append(NEWLINE);
		}

		// SQL data access
		if (function.getSqlDataAccess() != null) {
			statement.append(TAB).append(
					function.getSqlDataAccess().toString()).append(NEWLINE);
		}

		// null-call
		if (function.isNullCall()) {
			statement.append(TAB).append(RETURNS_NULL_ON_NULL_INPUT).append(NEWLINE);
		}
		else {
			statement.append(TAB).append(CALLED_ON_NULL_INPUT).append(NEWLINE);
		}

		// transform group
		if (function.getTransformGroup() != null
				&& function.getTransformGroup().length() > 0) {
			statement.append(TAB).append(TRANSFORM_GROUP).append(SPACE).append(
					function.getTransformGroup()).append(NEWLINE);
		}

		// dynamic result sets
		// nothing to do
		// end characteristics

		// dispatch
		if (function.isStatic()) {
			statement.append(TAB).append(STATIC_DISPATCH).append(NEWLINE);
		}

		// body
		if (function.getSource() != null) {
			String body = function.getSource().getBody();
			if (body != null && body.length() > 0) {
				statement.append(body).append(NEWLINE);
			}
		}

		return statement.toString();
    }
    
    protected String getParameterListClause(Routine routine, boolean quoteIdentifiers) {
    	StringBuffer statement = new StringBuffer();
		statement.append(LEFT_PARENTHESIS).append(NEWLINE);
		for (Iterator it = routine.getParameters().iterator(); it.hasNext();) {
			Parameter param = (Parameter) it.next();
			String name = param.getName();
			ParameterMode mode = param.getMode();

			// formatting
			statement.append(TAB).append(TAB);

			// mode (IN, INOUT, OUT)
			if (mode != null) {
				statement.append(mode.toString()).append(SPACE);
			}

			// name
			if (name != null && name.length() > 0) {
				statement.append(
						quoteIdentifiers ? getQuotedIdentifierString(param)
								: name).append(SPACE);
			}

			// type
			statement.append(getDataTypeString(param, routine.getSchema()));

			// locator
			// TODO: anything?

			if (it.hasNext()) {
				// prepare for the next parameter
				statement.append(COMMA);
				statement.append(NEWLINE);
			}
		}
		statement.append(RIGHT_PARENTHESIS).append(NEWLINE);
		
		return statement.toString();
    }
    
    protected String getReturnsClause(Function function, boolean quoteIdentifiers) {
    	StringBuffer statement = new StringBuffer();
    	
		statement.append(TAB).append(RETURNS).append(SPACE);
		if (function.getReturnScalar() != null) {
			statement.append(getDataTypeString(function.getReturnScalar(),
					function.getSchema()));
			if (function.getReturnCast() != null) {
				statement.append(SPACE).append(CAST_FROM).append(
						getDataTypeString(function.getReturnCast(), function
								.getSchema()));
			}
		}
		else if (function.getReturnTable() != null) {
			statement.append(ROW).append(SPACE).append(LEFT_PARENTHESIS).append(NEWLINE);
			for (Iterator it = function.getReturnTable().getColumns().iterator(); it.hasNext();) {
				Column col = (Column) it.next();
				String name = col.getName();

				// formatting
				statement.append(TAB).append(TAB);
				
				// name
				if (name != null && name.length() > 0) {
					statement.append(
							quoteIdentifiers ? getQuotedIdentifierString(col)
									: name).append(SPACE);
				}
				
				// type
				statement.append(getDataTypeString(col,function.getSchema()));
				
				if (it.hasNext()) {
					// prepare for the next parameter
					statement.append(COMMA);
					statement.append(NEWLINE);
				}
			}
			statement.append(TAB).append(RIGHT_PARENTHESIS);
		}
		statement.append(NEWLINE);
		
		return statement.toString();
    }

    public String createSchema(Schema schema, boolean quoteIdentifiers,
			boolean qualifyNames) {
		StringBuffer statement = new StringBuffer();

		// CREATE SCHEMA schema_name
		statement.append(CREATE).append(SPACE).append(SCHEMA).append(SPACE)
				.append(getName(schema, quoteIdentifiers, qualifyNames));

		// AUTHORIZATION
		if (schema.getOwner() != null) {
			statement.append(SPACE).append(AUTHORIZATION).append(SPACE).append(
					getName(schema.getOwner(), quoteIdentifiers));
		}

		return statement.toString();
	}

    public String createUserDefinedType(UserDefinedType type, boolean quoteIdentifiers,
			boolean qualifyNames) {
		if (type instanceof StructuredUserDefinedType) {
			return createStructuredUserDefinedType((StructuredUserDefinedType)type,quoteIdentifiers,qualifyNames);
		}
		else if (type instanceof Domain) {
			return createDomain((Domain)type,quoteIdentifiers,qualifyNames);
		}
		else if (type instanceof DistinctUserDefinedType) {
			return createDistinctUserDefinedType((DistinctUserDefinedType)type,quoteIdentifiers,qualifyNames);
		}

		StringBuffer statement = new StringBuffer();

		// CREATE TYPE type_name
		statement.append(CREATE).append(SPACE).append(TYPE).append(SPACE)
				.append(getName(type, quoteIdentifiers, qualifyNames));

		return statement.toString();
	}
    
    protected String createStructuredUserDefinedType(StructuredUserDefinedType type, boolean quoteIdentifiers,
			boolean qualifyNames) {
		StringBuffer statement = new StringBuffer();

		// CREATE TYPE type_name
		statement.append(CREATE).append(SPACE).append(TYPE).append(SPACE)
				.append(getName(type, quoteIdentifiers, qualifyNames)).append(NEWLINE);

		// subtype
		if (type.getSuper() != null) {
			statement.append(TAB).append(UNDER).append(SPACE).append(getName(type.getSuper(),quoteIdentifiers,qualifyNames)).append(NEWLINE);
		}
		
		statement.append(TAB).append(AS).append(SPACE).append(LEFT_PARENTHESIS).append(NEWLINE);
		for (Iterator it = type.getAttributes().iterator(); it.hasNext();) {
			AttributeDefinition attr = (AttributeDefinition)it.next();
			statement.append(TAB).append(TAB).append(getAttributeString(attr,quoteIdentifiers));
		}
		statement.append(TAB).append(RIGHT_PARENTHESIS).append(NEWLINE);
		
		// instantiable
		if (type.isInstantiable()) {
			statement.append(TAB).append(INSTANTIABLE).append(NEWLINE);
		}
		else {
			statement.append(TAB).append(NOT_INSTANTIABLE).append(NEWLINE);
		}
		
		// finality
		if (type.isFinal()) {
			// should never be this
			statement.append(TAB).append(FINAL).append(NEWLINE);
		}
		else {
			statement.append(TAB).append(NOT_FINAL).append(NEWLINE);
		}
		
		// reference type
		// nothing to do
		
		// cast
		// nothing to do
		
		// method list
		for (Iterator methodIt = type.getMethods().iterator(); methodIt.hasNext(); ) {
			Method method = (Method)methodIt.next();
			
			if (method.isOverriding()) {
				statement.append(OVERRIDING).append(SPACE);
			}

			if (method.isStatic()) {
				statement.append(STATIC);
			}
			else {
				statement.append(INSTANCE);
			}
			statement.append(SPACE).append(getName(method,quoteIdentifiers,qualifyNames)).append(SPACE);
			
			// parameters
			statement.append(getParameterListClause(method,quoteIdentifiers));
			
			// returns
			statement.append(getReturnsClause(method,quoteIdentifiers));
			
			if (method.getSpecificName() != null && method.getSpecificName().length() > 0) {
				statement.append(TAB).append(SPECIFIC).append(SPACE).append(getSpecificName(method,quoteIdentifiers,qualifyNames)).append(NEWLINE);
			}

			// begin characteristics
			// language
			if (method.getLanguage() != null
					&& method.getLanguage().length() > 0) {
				statement.append(TAB).append(LANGUAGE).append(SPACE).append(
						method.getLanguage()).append(NEWLINE);
			}

			// parameter style
			if (method.getParameterStyle() != null
					&& method.getParameterStyle().length() > 0) {
				statement.append(TAB).append(PARAMETER_STYLE).append(SPACE).append(
						method.getParameterStyle()).append(NEWLINE);
			}

			// determinism
			if (method.isDeterministic()) {
				statement.append(TAB).append(DETERMINISTIC).append(NEWLINE);
			}
			else {
				statement.append(TAB).append(NOT_DETERMINISTIC).append(NEWLINE);
			}

			// SQL data access
			if (method.getSqlDataAccess() != null) {
				statement.append(TAB).append(
						method.getSqlDataAccess().toString()).append(NEWLINE);
			}

			// null-call
			if (method.isNullCall()) {
				statement.append(TAB).append(RETURNS_NULL_ON_NULL_INPUT).append(NEWLINE);
			}
			else {
				statement.append(TAB).append(CALLED_ON_NULL_INPUT).append(NEWLINE);
			}

			// transform group
			if (method.getTransformGroup() != null
					&& method.getTransformGroup().length() > 0) {
				statement.append(TAB).append(TRANSFORM_GROUP).append(SPACE).append(
						method.getTransformGroup()).append(NEWLINE);
			}
			// end characteristics

			if (methodIt.hasNext()) {
				// prepare for the next parameter
				statement.append(COMMA);
				statement.append(NEWLINE);
			}
		}

		return statement.toString();
    }

    protected String createDomain(Domain type, boolean quoteIdentifiers,
			boolean qualifyNames) {
		StringBuffer statement = new StringBuffer();

		// CREATE DOMAIN type_name
		statement.append(CREATE).append(SPACE).append(DOMAIN).append(SPACE)
				.append(getName(type, quoteIdentifiers, qualifyNames)).append(
						SPACE).append(AS).append(SPACE).append(
						getDataTypeString(type));

		if (type.getDefaultValue() != null) {
			statement.append(SPACE).append(DEFAULT).append(SPACE).append(
					type.getDefaultValue());
		}
		
		for (Iterator it = type.getConstraint().iterator(); it.hasNext(); ) {
			Object obj = it.next();
			if (obj instanceof CheckConstraint) {
				statement.append(NEWLINE).append(getCheckConstraintClause((CheckConstraint)obj, quoteIdentifiers));
			}
		}

		return statement.toString();
	}

    protected String createDistinctUserDefinedType(
			DistinctUserDefinedType type, boolean quoteIdentifiers,
			boolean qualifyNames) {
		StringBuffer statement = new StringBuffer();

		// CREATE TYPE type_name
		statement.append(CREATE).append(SPACE).append(TYPE).append(SPACE)
				.append(getName(type, quoteIdentifiers, qualifyNames)).append(
						SPACE).append(AS).append(SPACE).append(
						getDataTypeString(type)).append(SPACE).append(FINAL);

		return statement.toString();
	}

    public String createAssertion(Assertion assertion,
			boolean quoteIdentifiers, boolean qualifyNames) {
		String text = CREATE + SPACE + ASSERTION + SPACE
				+ getName(assertion, quoteIdentifiers, qualifyNames) + SPACE
				+ CHECK + SPACE + LEFT_PARENTHESIS
				+ assertion.getSearchCondition().getSQL() + RIGHT_PARENTHESIS;
		if (assertion.isDeferrable()) {
			text += SPACE + getDeferrableClause(assertion);
		}
		return text;
	}

    public String addCheckConstraint(CheckConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames) {
        BaseTable table = constraint.getBaseTable();
        String tableName = table.getName();
        String schemaName = table.getSchema().getName();

        if(quoteIdentifiers) {
            tableName = this.getQuotedIdentifierString(table);
            schemaName = this.getQuotedIdentifierString(table.getSchema());
        }
        if(qualifyNames) {
            tableName = schemaName + DOT + tableName;
        }
    
        return ALTER + SPACE + TABLE + SPACE + getName(constraint.getBaseTable(), quoteIdentifiers, qualifyNames)
        	+ SPACE + this.getAddCheckConstraintClause(constraint, quoteIdentifiers);
    }

    public String addUniqueConstraint(UniqueConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames) {
        String statement = ALTER + SPACE + TABLE + SPACE + getName(constraint.getBaseTable(), quoteIdentifiers, qualifyNames) + SPACE;
        statement += this.getAddUniqueConstraintClause(constraint, quoteIdentifiers);
        
        return statement;
    }

    public String addForeignKey(ForeignKey foreignKey, boolean quoteIdentifiers, boolean qualifyNames) {
        UniqueConstraint uniqueConstraint = foreignKey.getUniqueConstraint();
        Index index = foreignKey.getUniqueIndex();
        Table parentTable = null;
        String parentKey = null;
        if(uniqueConstraint != null) {
            parentTable = uniqueConstraint.getBaseTable();
            parentKey = this.getKeyColumns(uniqueConstraint, quoteIdentifiers);
        }
        else if(index != null) {
        	parentTable = index.getTable();
            parentKey = this.getParentKeyColumns(index, quoteIdentifiers);
        }
        if(parentTable == null) {
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					GenericCatalogMessages.FE_PARENT_TABLLE_OR_KEY_DO_NOT_EXIST, new Object[] { foreignKey.getName()}));
        	return null;
        }

        String statement = ALTER + SPACE + TABLE + SPACE + getName(foreignKey.getBaseTable(), quoteIdentifiers, qualifyNames)
        	+ SPACE + ADD + SPACE + CONSTRAINT + SPACE + getName(foreignKey, quoteIdentifiers) + SPACE + FOREIGN_KEY 
        	+ SPACE + LEFT_PARENTHESIS + this.getKeyColumns(foreignKey, quoteIdentifiers) + RIGHT_PARENTHESIS + NEWLINE;
        statement += TAB + REFERENCES + SPACE + getName(parentTable, quoteIdentifiers, qualifyNames) + SPACE + LEFT_PARENTHESIS
        	+ parentKey + RIGHT_PARENTHESIS;
		
        ReferentialActionType action = foreignKey.getOnDelete();
        if(action != ReferentialActionType.NO_ACTION_LITERAL) {
            statement += NEWLINE + TAB + ON + SPACE + DELETE + SPACE;            
        }
        statement += getReferentialAction(action);
        
        action = foreignKey.getOnUpdate();
        if(action != ReferentialActionType.NO_ACTION_LITERAL) {
            statement += NEWLINE + TAB + ON + SPACE + UPDATE + SPACE;            
        }
        statement += getReferentialAction(action);

        if(foreignKey.isDeferrable()) {
            statement += NEWLINE + TAB + getDeferrableClause(foreignKey);
        }
        return statement;
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

    protected String getViewColumnList(ViewTable view) {
        String columns = null;
        Iterator it = view.getColumns().iterator();
        if(it.hasNext()) {
            Column c = (Column) it.next();
            columns = c.getName();
        }
        else {
            return null;
        }
        
        while(it.hasNext()) {
            Column c = (Column) it.next();
            columns += COMMA + SPACE + c.getName();
        }
        
        return columns;
        
    }
    
    protected String getColumnString(Column column, boolean quoteIdentifiers) {
        String columnName = column.getName();
        if(quoteIdentifiers) {
            columnName = this.getQuotedIdentifierString(column);
        }
        
        String columnString = columnName + SPACE + getDataTypeString(column,column.getTable().getSchema());
        String defaultValue = column.getDefaultValue();
        if(defaultValue != null && defaultValue.trim().length()>0) {
          columnString = columnString + SPACE + DEFAULT + SPACE + defaultValue;
        }
        
        if(!column.isNullable()) {
            columnString = columnString + SPACE + NOT + SPACE + NULL;
        }
        
        return columnString;
    }

    protected String getAttributeString(AttributeDefinition attr, boolean quoteIdentifiers) {
        String attrName = attr.getName();
        if(quoteIdentifiers) {
        	attrName = this.getQuotedIdentifierString(attr);
        }
        
        StringBuffer attrString = new StringBuffer();
        
        // name
        attrString.append(attrName).append(SPACE);
        
        // type
        attrString.append(getDataTypeString(attr,((UserDefinedType)attr.eContainer()).getSchema())).append(SPACE);
        
        // scope
        if (attr.isScopeChecked()) {
        	attrString.append(REFERENCES_ARE_CHECKED);
        }
        else {
        	attrString.append(REFERENCES_ARE_NOT_CHECKED);
        }
        if (attr.getScopeCheck() != null && attr.getScopeCheck() != ReferentialActionType.NO_ACTION_LITERAL) {
        	attrString.append(SPACE).append(ON).append(SPACE).append(DELETE).append(SPACE);
        	switch(attr.getScopeCheck().getValue()) {
        	case ReferentialActionType.CASCADE:
        		attrString.append(CASCADE);
        		break;
        	case ReferentialActionType.RESTRICT:
        		attrString.append(RESTRICT);
        		break;
        	case ReferentialActionType.SET_NULL:
        		attrString.append(SET).append(SPACE).append(NULL);
        		break;
        	case ReferentialActionType.SET_DEFAULT:
        		attrString.append(SET).append(SPACE).append(DEFAULT);
        		break;
        	}
        }
        
        // default
        if(attr.getDefaultValue() != null) {
          attrString.append(SPACE).append(DEFAULT).append(SPACE).append(attr.getDefaultValue());
        }
        
        return attrString.toString();
    }

    protected String getAddUniqueConstraintClause(UniqueConstraint constraint, boolean quoteIdentifiers) {
        String constraintName = getName(constraint, quoteIdentifiers);

        String text =  ADD + SPACE + CONSTRAINT + SPACE + constraintName 
        	+ SPACE + getUniqueConstraintType(constraint) + SPACE
        	+ LEFT_PARENTHESIS + this.getKeyColumns(constraint, quoteIdentifiers) + RIGHT_PARENTHESIS;
        
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
        return ADD + SPACE + getCheckConstraintClause(constraint,quoteIdentifiers);
    }
    
    protected String getCheckConstraintClause(CheckConstraint constraint, boolean quoteIdentifiers) {
        String constraintName = getName(constraint, quoteIdentifiers);

        String text = CONSTRAINT + SPACE + constraintName + SPACE + CHECK
        	+ SPACE + LEFT_PARENTHESIS + constraint.getSearchCondition().getSQL() + RIGHT_PARENTHESIS;
        if(constraint.isDeferrable()) {
            text += SPACE + getDeferrableClause(constraint);
        }
        return text;
    }

    protected String getKeyColumns(ReferenceConstraint constraint, boolean quoteIdentifiers) {
        String columns = null;
        Iterator it = constraint.getMembers().iterator();
        if(it.hasNext()) {
            Column c = (Column) it.next();
            final String columnName;
            if (quoteIdentifiers) {
                columnName = getQuotedIdentifierString(c);
            } else {
            	columnName = c.getName();
            }
            
            columns = columnName;
        }
        else {
	    	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					GenericCatalogMessages.FE_REFERENCE_CONSTAINT_HAS_NO_KEY, new Object[] { constraint.getName()}));
            return null;
        }
        
        while(it.hasNext()) {
            Column c = (Column) it.next();
            final String columnName;
            if (quoteIdentifiers) {
                columnName = getQuotedIdentifierString(c);
            } else {
            	columnName = c.getName();
            }
            columns += COMMA + SPACE + columnName;
        }
        
        return columns;
    }

    protected String getIndexKeyColumns(Index index, boolean quoteIdentifiers) {
        String columns;

        Iterator it = index.getMembers().iterator();
        if(it.hasNext()) {
            IndexMember m = (IndexMember) it.next();
            String columnName = m.getColumn().getName();
            if (quoteIdentifiers) {
                columnName = this.getQuotedIdentifierString(m.getColumn());
            }
            columns = columnName + SPACE + m.getIncrementType().getName();
        }
        else {
	    	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					GenericCatalogMessages.FE_INDEX_HAS_NO_MEMBER, new Object[] { getName(index,false,true)}));
            return null;
        }
        
        while(it.hasNext()) {
            IndexMember m = (IndexMember) it.next();
            String columnName = m.getColumn().getName();
            if(quoteIdentifiers) {
                columnName = this.getQuotedIdentifierString(m.getColumn());
            }
            columns += COMMA + SPACE;
            columns += columnName + SPACE + m.getIncrementType().getName();
        }
        return columns;
    }
  
    protected String getParentKeyColumns(Index index, boolean quoteIdentifiers) {
        String columns;
        Iterator it = index.getMembers().iterator();
        if(it.hasNext()) {
            IndexMember m = (IndexMember) it.next();
            String columnName = m.getColumn().getName();
            if(quoteIdentifiers) {
                columnName = this.getQuotedIdentifierString(m.getColumn());
            }
            columns = columnName;
        }
        else {
	    	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					GenericCatalogMessages.FE_INDEX_HAS_NO_MEMBER, new Object[] { getName(index,false,true)}));
            return null;
        }
        
        while(it.hasNext()) {
            IndexMember m = (IndexMember) it.next();
            String columnName = m.getColumn().getName();
            if (quoteIdentifiers) {
                columnName = this.getQuotedIdentifierString(m.getColumn());
            }
            columns += COMMA + SPACE + columnName;
        }
        return columns;
    }
  
    protected String getDataTypeString(TypedElement typedElement, Schema schema) {
        SQLDataType containedType = typedElement.getContainedType();
        if(containedType != null) {
            if(containedType instanceof PredefinedDataType) {
                EObject root = ContainmentServiceImpl.INSTANCE.getRootElement(typedElement);
                if(root instanceof Database) {
                    DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition((Database) root);
                    return def.getPredefinedDataTypeFormattedName((PredefinedDataType) containedType);
                }
            }
        }
        else {
            UserDefinedType referencedType = typedElement.getReferencedType();
            if(referencedType != null) {
                if (referencedType.getSchema() != schema) {
                	return this.getName(referencedType,false, true);
                } else {
                	return referencedType.getName();
                }
            }
        }
    	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
				GenericCatalogMessages.FE_ELEMENT_HAS_NO_TYPE, new Object[] { typedElement.getName()}));
        return null;
    }

    protected String getDataTypeString(DistinctUserDefinedType typedElement) {
		PredefinedDataType containedType = typedElement
				.getPredefinedRepresentation();
		if (containedType != null) {
			EObject root = ContainmentServiceImpl.INSTANCE
					.getRootElement(typedElement);
			if (root instanceof Database) {
				DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition((Database) root);
				return def.getPredefinedDataTypeFormattedName((PredefinedDataType) containedType);
			}
		}
		return null;
	}

    protected String getName(TableConstraint constraint, boolean quoteIdentifiers) {
        String name = constraint.getName();

        if(quoteIdentifiers) {
            name = this.getQuotedIdentifierString(constraint);
        }
    
        return name;
    }

    protected String getName(Assertion assertion, boolean quoteIdentifiers, boolean qualifyNames) {
        String name = assertion.getName();
        String schemaName = assertion.getSchema().getName();
    
        if(quoteIdentifiers) {
            name = this.getQuotedIdentifierString(assertion);
            schemaName  = this.getQuotedIdentifierString(assertion.getSchema());
        }
        if(qualifyNames) {
            name = schemaName + DOT + name;
        }
		
        return name;
    }

    protected String getName(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames) {
        String name = trigger.getName();
        String schemaName = trigger.getSchema().getName();
    
        if(quoteIdentifiers) {
            name = this.getQuotedIdentifierString(trigger);
            schemaName  = this.getQuotedIdentifierString(trigger.getSchema());
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
            name = this.getQuotedIdentifierString(routine);
            schemaName  = this.getQuotedIdentifierString(routine.getSchema());
        }
        if(qualifyNames) {
            name = schemaName + DOT + name;
        }

        return name;
    }

    protected String getSpecificName(Method method, boolean quoteIdentifiers, boolean qualifyNames) {
        String name = method.getSpecificName();

        if(quoteIdentifiers) {
            name = this.getQuotedIdentifierString(method);
        }

        return name;
    }

    protected String getName(Index index, boolean quoteIdentifiers, boolean qualifyNames) {
        String indexName = index.getName();
        String schemaName = index.getSchema().getName();

        if(quoteIdentifiers) {
            indexName = this.getQuotedIdentifierString(index);
            schemaName = this.getQuotedIdentifierString(index.getSchema());
        }

        if(qualifyNames) {
            indexName = schemaName + DOT + indexName;
	    }
    
        return indexName;
    }

    protected String getName(Table table, boolean quoteIdentifiers, boolean qualifyNames) {
        String tableName = table.getName();
        String schemaName = table.getSchema().getName();

        if(quoteIdentifiers) {
            tableName = this.getQuotedIdentifierString(table);
            schemaName = this.getQuotedIdentifierString(table.getSchema());
        }

        if(qualifyNames) {
            tableName = schemaName + DOT + tableName;
        }
    
        return tableName;
    }

    protected String getName(Sequence sequence, boolean quoteIdentifiers, boolean qualifyNames) {
        String sequenceName = sequence.getName();
        String schemaName = sequence.getSchema().getName();

        if(quoteIdentifiers) {
            sequenceName = this.getQuotedIdentifierString(sequence);
            schemaName = this.getQuotedIdentifierString(sequence.getSchema());
        }

        if(qualifyNames) {
            sequenceName = schemaName + DOT + sequenceName;
        }
    
        return sequenceName;
    }
    
    protected String getName(UserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames) {
        String typeName = type.getName();
        String schemaName = type.getSchema().getName();

        if(quoteIdentifiers) {
            typeName = this.getQuotedIdentifierString(type);
            schemaName = this.getQuotedIdentifierString(type.getSchema());
        }

        if(qualifyNames) {
            typeName = schemaName + DOT + typeName;
        }
    
        return typeName;
    }
    
    protected String getName(Schema schema, boolean quoteIdentifiers, boolean qualifyNames) {
        String schemaName = schema.getName();

        if(quoteIdentifiers) {
            schemaName = this.getQuotedIdentifierString(schema);
        }

        if(qualifyNames) {
        	// TODO: need to accommodate catalogs
        }
    
        return schemaName;
    }
    
    protected String getName(AuthorizationIdentifier authID, boolean quoteIdentifiers) {
        String authIDName = authID.getName();

        if(quoteIdentifiers) {
        	authIDName = this.getQuotedIdentifierString(authID);
        }

        return authIDName;
    }
    
    protected DatabaseDefinition getDatabaseDefinition(EObject object) {
		EObject root = ContainmentServiceImpl.INSTANCE.getRootElement(object);
		if (root instanceof Database) {
			return DatabaseDefinitionRegistryImpl.INSTANCE
					.getDefinition((Database) root);
		}
		return null;
	}
    
    protected String getQuotedIdentifierString(SQLObject object) {
		DatabaseDefinition dbDef = getDatabaseDefinition(object);
		if (dbDef == null) {
			return getDoubleQuotedString(object.getName());
		}

		String quote = dbDef.getIdentifierQuoteString();
		if (quote == null || quote.length() == 0) 
			quote = "\""; //$NON-NLS-1$
		if (quote.equals("\\\"")) // workaround for BZ 224853
			quote = "\""; //$NON-NLS-1$
			
		StringTokenizer tokenizer = new StringTokenizer(object.getName(), quote);
		String result = null;
		if (tokenizer.countTokens() > 1) {
			if (tokenizer.hasMoreTokens()) {
				result = tokenizer.nextToken();
			}
			while (tokenizer.hasMoreTokens()) {
				result = result + quote + quote + tokenizer.nextToken();
			}
		}
		else {
			if (tokenizer.hasMoreTokens()) {
				result = tokenizer.nextToken();
			}
		}
		return quote + result + quote;
	}

    protected String getSingleQuotedString(String orignal) {
  	    StringTokenizer tokenizer = new StringTokenizer(orignal, SINGLE_QUOTE);
        String result = tokenizer.nextToken();
        while(tokenizer.hasMoreTokens()) {
            result =  result + SINGLE_QUOTE + SINGLE_QUOTE + tokenizer.nextToken();
        }
        return SINGLE_QUOTE + result + SINGLE_QUOTE;
    }
  
    protected String getDoubleQuotedString(String orignal) {
        StringTokenizer tokenizer = new StringTokenizer(orignal, DOUBLE_QUOTE);
        String result = null;
        if(tokenizer.countTokens () > 1) {
            if(tokenizer.hasMoreTokens()) {
                result = tokenizer.nextToken();
            }
            while (tokenizer.hasMoreTokens()) {
                result =  result + DOUBLE_QUOTE + DOUBLE_QUOTE + tokenizer.nextToken();
            }
        }
        else {
            if(tokenizer.hasMoreTokens()) {
                result = tokenizer.nextToken();
            }
        }
        return DOUBLE_QUOTE + result + DOUBLE_QUOTE;
    }

    public boolean isImplicitConstraint(TableConstraint constraint) {
    	return !constraint.isEnforced();
    }

    private IEngineeringCallBack getDummyEngineeringCallBack(){
    	if (this.dummyCallback == null) this.dummyCallback = new dummyEngineeringCallBack();
    	return dummyCallback;
    }

    private class dummyEngineeringCallBack implements IEngineeringCallBack {
    	public String[] getMessages(){
    		return new String[]{};
    	}
    	
    	public void writeMessage(String message) {
    	}
    }

}
