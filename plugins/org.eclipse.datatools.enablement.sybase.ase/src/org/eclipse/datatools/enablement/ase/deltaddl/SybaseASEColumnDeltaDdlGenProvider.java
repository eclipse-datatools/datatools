package org.eclipse.datatools.enablement.ase.deltaddl;

import java.util.List;
import java.util.Map;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.ase.ISybaseASEDdlConstants;
import org.eclipse.datatools.enablement.ase.SybaseASESQLUtil;
import org.eclipse.datatools.enablement.ase.ddl.SybaseASEDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

public class SybaseASEColumnDeltaDdlGenProvider extends AbstractDeltaDdlGenProvider implements IDeltaDdlGenProvider,
        ISybaseASEDdlConstants
{
    public void analyze(SQLObject element, Map changeMap, Map modificationRecords)
    {
        super.analyze(element, changeMap, modificationRecords);
        List records = (List)modificationRecords.get(element);
        if(records == null)
            return;

        boolean isGenExpChanged = false;
        int genExpChangedRcIndex = -1;
        boolean isMatChanged = false;
        int matChangedRcIndex = -1;
//        boolean isNullableChanged = false;
//        int nullableChangedRcIndex = -1;
        for (int i = records.size() - 1; i >= 0; i--)
        {
            SybaseDeltaDdlGeneration.FeatureChangeRecord record = (SybaseDeltaDdlGeneration.FeatureChangeRecord)records.get(i);
            EStructuralFeature f = record.feature;
            
            if(f == SQLTablesPackage.eINSTANCE.getColumn_GenerateExpression())
            {
                isGenExpChanged = true;
                genExpChangedRcIndex = i;
            }
            if(f == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEColumn_Materialized())
            {
                isMatChanged = true;
                matChangedRcIndex = i;
            }
//            if(f == SQLTablesPackage.eINSTANCE.getColumn_Nullable())
//            {
//                isNullableChanged = true;
//                nullableChangedRcIndex = i;
//            }
        }
        if(isGenExpChanged&&isMatChanged)
        {
            records.remove(matChangedRcIndex);
        }
//        if(isGenExpChanged&&isNullableChanged)
//        {
//            records.remove(nullableChangedRcIndex);
//        }
    }

    protected void getModificationResult(SQLObject e, EStructuralFeature f, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        SybaseDdlBuilder builder = (SybaseDdlBuilder) SybaseASEDdlBuilder.getInstance();
        SybaseASEColumn column = (SybaseASEColumn) e;
        if (f.getFeatureID() == EcorePackage.ENAMED_ELEMENT__NAME)
        {
            if (!oldValue.toString().equals(newValue.toString()))
            {
                String tableName = column.getTable().getName();
                tableName = SQLDevToolsUtil.quoteWhenNecessary(tableName, (DatabaseIdentifier)getParameter());
                //Old column name must be quoted. Otherwise server throws an syntax error.
                String columnOldName = SQLUtil.quote(tableName+DOT+oldValue,SINGLE_QUOTE);
                String columnNewName = newValue.toString();
                columnNewName = SQLDevToolsUtil.quoteWhenNecessary(columnNewName, (DatabaseIdentifier)getParameter());
                StringBuffer sb = new StringBuffer(256);
                sb.append(EXEC).append(SPACE).append(SP_RENAME).append(SPACE).append(columnOldName).append(COMMA).append(SPACE).append(columnNewName);
                Schema creator = column.getTable().getSchema();
                String setUserStr = SybaseASESQLUtil.getSetNewUserStatement(creator);
                if(!setUserStr.equals(EMPTY_STRING))
                {
                    script.addAlterTableRenameColumnStatements(setUserStr);
                }
                script.addAlterTableRenameColumnStatements(sb.toString());
                String setUserDbo = SybaseASESQLUtil.getSetUserDBOStatement(creator);
                if(!setUserDbo.equals(EMPTY_STRING))
                {
                    script.addAlterTableRenameColumnStatements(setUserDbo);
                }
            }
        }

        else if (f.getFeatureID() == SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__NULLABLE)
        {
            if (((Boolean) newValue).booleanValue() != ((Boolean) oldValue).booleanValue())
            {
                StringBuffer stmt = new StringBuffer();
                String nullClause = column.isNullable() == true ? NULL : (NOT + SPACE + NULL);

                // for non computed column
                if (!column.isComputedColumn())
                {
                    stmt.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                            builder.getName(column.getTable(), quoteIdentifiers, qualifyNames)).append(NEWLINE).append(
                            SPACE).append(MODIFY).append(SPACE).append(
                            getColumnName(column, quoteIdentifiers)).append(SPACE).append(
                            getDataTypeString(column, column.getTable().getSchema())).append(SPACE).append(nullClause);
                    script.addAlterTableColumnStatements(stmt.toString());
                }
                // for computed column
                else
                {
                    stmt.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                            builder.getName(column.getTable(), quoteIdentifiers, qualifyNames)).append(NEWLINE).append(
                            SPACE).append(MODIFY).append(SPACE).append(
                                    getColumnName(column, quoteIdentifiers)).append(SPACE).append(nullClause);
                    script.addAlterTableColumnStatements(stmt.toString());
                }
            }
        }

        else if (f.getFeatureID() == SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__CONTAINED_TYPE
                ||f.getFeatureID() == SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__REFERENCED_TYPE)
        {
            StringBuffer stmt = new StringBuffer();
            String nullClause = column.isNullable() == true ? NULL : (NOT + SPACE + NULL);
            
            //deal with the float type if necessary.
            if(!isFloatTypeDDLNeeded((DataType)oldValue, (DataType)newValue))
            {
            	return;
            }
            stmt.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                    builder.getName(column.getTable(), quoteIdentifiers, qualifyNames)).append(NEWLINE).append(SPACE)
                    .append(MODIFY).append(SPACE).append(getColumnName(column, quoteIdentifiers))
                    .append(SPACE).append(getDataTypeString(column, column.getTable().getSchema())).append(SPACE)
                    .append(nullClause);
            script.addAlterTableModifyColumnTypeStatements(stmt.toString());

        }

        else if (f.getFeatureID() == SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__DEFAULT_VALUE)
        {
            StringBuffer stmt = null;
            stmt = new StringBuffer();
            stmt.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                    builder.getName(column.getTable(), quoteIdentifiers, qualifyNames)).append(NEWLINE).append(SPACE)
                    .append(REPLACE).append(SPACE).append(getColumnName(column, quoteIdentifiers))
                    .append(SPACE).append(DEFAULT).append(SPACE).append(newValue);
            script.addAlterTableColumnStatements(stmt.toString());
        }
        
        else if (f.getFeatureID() == SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_DEFAULT)
        {
            StringBuffer unBindStmt = new StringBuffer();
            unBindStmt.append(EXEC).append(SPACE).append(SP_UNBINDEFAULT).append(SPACE).append(
                    SQLUtil.quote(column.getTable().getName() + DOT + column.getName(), SINGLE_QUOTE));            
            
            //if new value is null, unbind the default
            if(newValue == null)
            {
                script.addAlterTableColumnStatements(unBindStmt.toString());
            }
            
            //if bind default has been changed,rebind.
            else if(oldValue == null)
            {
                StringBuffer bindStmt = new StringBuffer();
                bindStmt.append(EXEC).append(SPACE).append(SP_BINDEFAULT).append(SPACE).append(
                        SQLUtil.quote(SybaseASEDdlBuilder.getInstance().getName(column.getBoundDefault(), false, true),
                                SINGLE_QUOTE)).append(COMMA).append(
                        SQLUtil.quote(column.getTable().getName() + DOT + column.getName(), SINGLE_QUOTE));
                script.addAlterTableColumnStatements(bindStmt.toString());
            }
            else 
            {
                StringBuffer bindStmt = new StringBuffer();
                bindStmt.append(EXEC).append(SPACE).append(SP_BINDEFAULT).append(SPACE).append(
                        SQLUtil.quote(SybaseASEDdlBuilder.getInstance().getName(column.getBoundDefault(), false, true),
                                SINGLE_QUOTE)).append(COMMA).append(
                        SQLUtil.quote(column.getTable().getName() + DOT + column.getName(), SINGLE_QUOTE));
                script.addAlterTableColumnStatements(unBindStmt.toString());
                script.addAlterTableColumnStatements(bindStmt.toString());
            }

        }

        else if (f.getFeatureID() == SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__GENERATE_EXPRESSION)
        {
            String matClause = column.isMaterialized() == true ? MATERIALIZED : NOT + SPACE + MATERIALIZED;
//            String nullClause = column.isNullable() == true ? NULL : (NOT + SPACE + NULL);
            StringBuffer stmt = new StringBuffer();
            stmt.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                    builder.getName(column.getTable(), quoteIdentifiers, qualifyNames)).append(NEWLINE).append(SPACE)
                    .append(MODIFY).append(SPACE).append(getColumnName(column, quoteIdentifiers)).append(SPACE).append(
                            AS).append(SPACE).append(((ValueExpression) newValue).getSQL()).append(SPACE).append(matClause);
            script.addAlterTableColumnStatements(stmt.toString());

        }

        else if (f.getFeatureID() == SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__MATERIALIZED)
        {
            StringBuffer stmt = new StringBuffer();
            String matClause = column.isMaterialized() == true ? MATERIALIZED : NOT + SPACE + MATERIALIZED;
            stmt.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                    builder.getName(column.getTable(), quoteIdentifiers, qualifyNames)).append(NEWLINE).append(SPACE)
                    .append(MODIFY).append(SPACE).append(getColumnName(column, quoteIdentifiers))
                    .append(SPACE).append(matClause);
            script.addAlterTableColumnStatements(stmt.toString());
        }
        
        else if (f.getFeatureID() == SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN__BOUND_RULE)
        {
            StringBuffer unBindStmt = new StringBuffer();
            unBindStmt.append(EXEC).append(SPACE).append(SP_UNBINDRULE).append(SPACE).append(
                    SQLUtil.quote(column.getTable().getName() + DOT + column.getName(), SINGLE_QUOTE)).append(COMMA)
                    .append(NULL).append(COMMA).append(SINGLE_QUOTE).append(ALL).append(SINGLE_QUOTE);
            
            if(newValue!=null)
            {
//                if(oldValue==null
//                        ||!((SybaseASERule)oldValue).getName().equalsIgnoreCase(((SybaseASERule)newValue).getName())
//                        ||!((SybaseASERule)oldValue).getSchema().getName().equalsIgnoreCase(((SybaseASERule)newValue).getSchema().getName()))
//                {
                    StringBuffer bindStmt = new StringBuffer();
                    
                    bindStmt.append(EXEC).append(SPACE).append(SP_BINDRULE).append(SPACE).append(
                            SQLUtil.quote(SybaseASEDdlBuilder.getInstance().getName((SybaseASERule)newValue, false, true), SINGLE_QUOTE)).append(COMMA).append(
                                    SQLUtil.quote(column.getTable().getName()+DOT+column.getName(),SINGLE_QUOTE));
                    
                    if(oldValue==null)
                    {
                        script.addAlterOtherStatements(bindStmt.toString());
                    }
                    else
                    {                        
                        script.addAlterTableColumnStatements(unBindStmt.toString());
                        script.addAlterTableColumnStatements(bindStmt.toString());
                    }                
//                }
            }
            else
            {                
                script.addAlterTableColumnStatements(unBindStmt.toString());
            }
        }
    }

    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {
        // TODO Auto-generated method stub
        script.addAlterTableColumnStatements(statement);
    }

    protected void addDropStatement(SybaseDdlScript script, String statement)
    {
        // TODO Auto-generated method stub
        script.addAlterTableDropColumnStatements(statement);
    }
    
    private String getColumnName(Column column, boolean quoted_id)
    {
        if(quoted_id)
        {
            return DOUBLE_QUOTE+column.getName()+DOUBLE_QUOTE;
        }
        return column.getName();
    }
    
    /**
     * In ASE, if a UDT name contains double quote, we should quote it twice with double quote.
     * <p>
     * For example:
     * A UDT named "a b", then in DDL, the statements should like this: """a b"""
     */
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
                String udtName;
                if (referencedType.getSchema() != schema) {
                    udtName = getName(referencedType,false, false);
                } else {
                    udtName = referencedType.getName();
                }
                udtName = SQLUtil.quote(udtName, DOUBLE_QUOTE);
                return udtName;
            }
        }
        return null;
    }
    
    /**
     * In ASE, precision of float type can only be either 8 or 16. Any length below 16 is converted to 8,
     * rest is converted to 16. 
     * So if user changes the precision in the value scope {x:int|x<16}, we should not generate DDL for this
     * because server will throws an error saying no change has been made. And the situation is same for the 
     * scope {x:int|x>=16}
     * @param oldValue
     * @param newValue
     * @return
     */
    private boolean isFloatTypeDDLNeeded(DataType oldValue, DataType newValue)
    {
    	if(oldValue==null||newValue==null)
    	{
    		return true;
    	}
    	if(oldValue.getName().equalsIgnoreCase("float")&&newValue.getName().equalsIgnoreCase("float"))
    	{
    		EStructuralFeature feature = oldValue.eClass().getEStructuralFeature("precision");
            Integer oldValueLength = (Integer)oldValue.eGet(feature);
            feature = newValue.eClass().getEStructuralFeature("precision");
            Integer newValueLength = (Integer)newValue.eGet(feature);
            if(oldValueLength.intValue()<16&&newValueLength.intValue()<16
            		||oldValueLength.intValue()>=16&&newValueLength.intValue()>=16)
            {
            	return false;
            }
    	}
    	return true;
    }
    
}
