package org.eclipse.datatools.connectivity.sqm.core.rte.fe;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
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
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.ecore.EObject;

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
    protected final static String NEW_TABLE            = "NEW_TABLE"; //$NON-NLS-1$
    protected final static String OLD_TABLE            = "OLD_TABLE"; //$NON-NLS-1$
    protected final static String EACH                 = "EACH"; //$NON-NLS-1$
    protected final static String ROW                  = "ROW"; //$NON-NLS-1$
    protected final static String STATEMENT            = "STATEMENT"; //$NON-NLS-1$
    protected final static String WHEN                 = "WHEN"; //$NON-NLS-1$

    
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

    public String createTable(BaseTable table, boolean quoteIdentifiers, boolean qualifyNames) {
        String statement = CREATE + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames)
        	+ SPACE + LEFT_PARENTHESIS + NEWLINE ;
        Iterator it = table.getColumns().iterator();
        while(it.hasNext()) {
            Column column = (Column) it.next();
            statement += TAB + TAB + getColumnString(column, quoteIdentifiers);
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
        viewDefinition += AS + NEWLINE;
        viewDefinition += view.getQueryExpression().getSQL();
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
            statement += REFERENCING + SPACE + NEW + SPACE + AS + SPACE + newRow + NEWLINE;
        }
        if(oldRow != null && oldRow.length() != 0) {
            statement += REFERENCING + SPACE + OLD + SPACE + AS + SPACE + oldRow + NEWLINE;
        }

        if(trigger.getActionGranularity() == ActionGranularityType.ROW_LITERAL) {
            statement += FOR + SPACE + EACH + SPACE + ROW + NEWLINE;
    	}
        else {
            statement += FOR + SPACE + EACH + SPACE + STATEMENT + NEWLINE;
    	}

        Iterator it = trigger.getActionStatement().iterator();
        while(it.hasNext()) {
            SQLStatement s = (SQLStatement) it.next();
            statement += s.getSQL();
        }

        return statement;
    }
    
    public String addCheckConstraint(CheckConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames) {
        BaseTable table = constraint.getBaseTable();
        String tableName = table.getName();
        String schemaName = table.getSchema().getName();

        if(quoteIdentifiers) {
            tableName = this.getDoubleQuotedString(tableName);
            schemaName = this.getDoubleQuotedString(schemaName);
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
            parentKey = this.getKeyColumns(uniqueConstraint);
        }
        else if(index != null) {
            Table indexedTable = index.getTable();
            parentKey = this.getParentKeyColumns(index, quoteIdentifiers);
        }
        if(parentTable == null) {
            // TODO report error
            return null;
        }

        String statement = ALTER + SPACE + TABLE + SPACE + getName(foreignKey.getBaseTable(), quoteIdentifiers, qualifyNames)
        	+ SPACE + ADD + SPACE + CONSTRAINT + SPACE + getName(foreignKey, quoteIdentifiers) + SPACE + FOREIGN_KEY 
        	+ SPACE + LEFT_PARENTHESIS + this.getKeyColumns(foreignKey) + RIGHT_PARENTHESIS + NEWLINE;
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
            columnName = this.getDoubleQuotedString(columnName);
        }
        
        String columnString = columnName + SPACE + getDataTypeString(column,column.getTable().getSchema());
        String defaultValue = column.getDefaultValue();
        if(defaultValue != null) {
          columnString = columnString + SPACE + DEFAULT + SPACE + defaultValue;
        }
        
        if(!column.isNullable()) {
            columnString = columnString + SPACE + NOT + SPACE + NULL;
        }
        
        return columnString;
    }

    protected String getAddUniqueConstraintClause(UniqueConstraint constraint, boolean quoteIdentifiers) {
        String constraintName = getName(constraint, quoteIdentifiers);

        String text =  ADD + SPACE + CONSTRAINT + SPACE + constraintName 
        	+ SPACE + getUniqueConstraintType(constraint) + SPACE
        	+ LEFT_PARENTHESIS + this.getKeyColumns(constraint) + RIGHT_PARENTHESIS;
        
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

        String text =  ADD + SPACE + CONSTRAINT + SPACE + constraintName + SPACE + CHECK
        	+ SPACE + LEFT_PARENTHESIS + constraint.getSearchCondition().getSQL() + RIGHT_PARENTHESIS;
        if(constraint.isDeferrable()) {
            text += SPACE + getDeferrableClause(constraint);
        }
        return text;
    }

    protected String getKeyColumns(ReferenceConstraint constraint) {
        String columns = null;
        Iterator it = constraint.getMembers().iterator();
        if(it.hasNext()) {
            Column c = (Column) it.next();
            columns = c.getName();
        }
        else {
            // TODO report error
            return null;
        }
        
        while(it.hasNext()) {
            Column c = (Column) it.next();
            columns += COMMA + SPACE + c.getName();
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
                columnName = this.getDoubleQuotedString(columnName);
            }
            columns = columnName + SPACE + m.getIncrementType().getName();
        }
        else {
            // TODO report error
            return null;
        }
        
        while(it.hasNext()) {
            IndexMember m = (IndexMember) it.next();
            String columnName = m.getColumn().getName();
            if(quoteIdentifiers) {
                columnName = this.getDoubleQuotedString(columnName);
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
                columnName = this.getDoubleQuotedString(columnName);
            }
            columns = columnName;
        }
        else {
            // TODO report error
            return null;
        }
        
        while(it.hasNext()) {
            IndexMember m = (IndexMember) it.next();
            String columnName = m.getColumn().getName();
            if (quoteIdentifiers) {
                columnName = this.getDoubleQuotedString(columnName);
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
        return null;
    }

    protected String getName(TableConstraint constraint, boolean quoteIdentifiers) {
        String name = constraint.getName();

        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
        }
    
        return name;
    }

    protected String getName(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames) {
        String name = trigger.getName();
        String schemaName = trigger.getSchema().getName();
    
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
        String schemaName = index.getSchema().getName();

        if(quoteIdentifiers) {
            indexName = this.getDoubleQuotedString(indexName);
            schemaName = this.getDoubleQuotedString(schemaName);
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
        String schemaName = sequence.getSchema().getName();

        if(quoteIdentifiers) {
            sequenceName = this.getDoubleQuotedString(sequenceName);
            schemaName = this.getDoubleQuotedString(schemaName);
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
            typeName = this.getDoubleQuotedString(typeName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if(qualifyNames) {
            typeName = schemaName + DOT + typeName;
        }
    
        return typeName;
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
}
