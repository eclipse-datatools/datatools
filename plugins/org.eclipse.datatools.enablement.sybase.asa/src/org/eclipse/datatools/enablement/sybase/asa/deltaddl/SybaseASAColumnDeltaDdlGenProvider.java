package org.eclipse.datatools.enablement.sybase.asa.deltaddl;

import java.util.List;
import java.util.Map;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.sybase.asa.ISybaseASADdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.ddl.SybaseASADdlBuilder;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

public class SybaseASAColumnDeltaDdlGenProvider extends AbstractDeltaDdlGenProvider implements IDeltaDdlGenProvider,ISybaseASADdlConstants
{

    
    public void analyze(SQLObject element, Map changeMap, Map modificationRecords)
    {
        super.analyze(element, changeMap, modificationRecords);
        List records = (List)modificationRecords.get(element);
        if(records == null)
            return;

        boolean flag1 = false;
        boolean flag2 = false;
        for (int i = records.size() - 1; i >= 0; i--)
        {
            SybaseDeltaDdlGeneration.FeatureChangeRecord record = (SybaseDeltaDdlGeneration.FeatureChangeRecord)records.get(i);
            EStructuralFeature f = record.feature;
            
            //if records contains (SQLSchemaPackage.eINSTANCE.getTypedElement_ContainedType() 
            //or SQLSchemaPackage.eINSTANCE.getTypedElement_ReferencedType())
            //and SQLTablesPackage.eINSTANCE.getColumn_Nullable(), remove the others
            if(f == SQLSchemaPackage.eINSTANCE.getTypedElement_ContainedType()
                || f == SQLSchemaPackage.eINSTANCE.getTypedElement_ReferencedType()
                || f == SQLTablesPackage.eINSTANCE.getColumn_Nullable())
            {
                if(flag1)
                {
                    records.remove(i);
                }
                flag1 = true;
            }
            
            //if records contains SQLTablesPackage.eINSTANCE.getColumn_GenerateExpression()
            //and SQLTablesPackage.eINSTANCE.getColumn_DefaultValue(), remove the one whose newValue is empty
            if (f == SQLTablesPackage.eINSTANCE.getColumn_GenerateExpression()
                    || f == SQLTablesPackage.eINSTANCE.getColumn_DefaultValue())
            {
                
                if( record.newValue == null || record.newValue.equals(""))
                {
                    if(flag2)
                    {
                        records.remove(i);
                    }
                    flag2 = true;
                }
            }
        }
    }

    /**
     * Don't use qualified name for ASA
     */
    protected String getDataTypeString(TypedElement typedElement, Schema schema, boolean quotedIdentifier)
    {
        SQLDataType containedType = typedElement.getContainedType();
        if (containedType != null)
        {
            if (containedType instanceof PredefinedDataType)
            {
                EObject root = ContainmentServiceImpl.INSTANCE.getRootElement(typedElement);
                if (root instanceof Database)
                {
                    DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition((Database) root);
                    return def.getPredefinedDataTypeFormattedName((PredefinedDataType) containedType);
                }
            }
        }
        else
        {
            UserDefinedType referencedType = typedElement.getReferencedType();
            if (referencedType != null)
            {
                if (referencedType.getSchema() != schema)
                {
                    return this.getName(referencedType, quotedIdentifier, false);
                }
                else
                {
                    if(quotedIdentifier)
                    {
                        return getDoubleQuotedString(referencedType.getName());
                    }
                    return referencedType.getName();
                }
            }
        }
        return null;
    }
    
    /**
     * <pre>
     *  ALTER TABLE [ owner.]table-name
     *  { modify-clause | rename-clause }
     *  
     *  modify-clause :
     *  MODIFY column-definition
     *  | MODIFY column-name { DEFAULT default-value
     *  | [ NOT ] NULL
     *  | [ CONSTRAINT constraint-name ]
     *  CHECK { NULL |  ( new-condition ) } }
     *  | ALTER column-name  column-modification
     *  | ALTER constraint-name CHECK ( new-condition )
     *  
     *  rename-clause :
     *   RENAME column-name TO new-column-name
     *   
     *  column-definition :
     *  column-name data-type [ NOT NULL ] [ DEFAULT default-value ] [ column-constraint ... ]
     *  
     *  column-modification :
     *    SET DEFAULT default-value
     *    | DROP DEFAULT
     *    | ADD [ CONSTRAINT column-constraint-name ] CHECK ( condition )
     *    | { DELETE | DROP } CONSTRAINT column-constraint-name
     *    | { DELETE | DROP } CHECK
     *    | SET COMPUTE ( expression )
     *    | DROP COMPUTE
     * </pre>
     */
    protected void getModificationResult(SQLObject e, EStructuralFeature f, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        SybaseDdlBuilder builder = (SybaseDdlBuilder) SybaseASADdlBuilder.getInstance();
        SybaseASABaseColumn column = (SybaseASABaseColumn) e;
        Table table = column.getTable();

        StringBuffer sb = new StringBuffer(256);
        sb.append(ALTER).append(SPACE).append(TABLE).append(SPACE);
        sb.append(builder.getName(table, quoteIdentifiers, qualifyNames)).append(SPACE);

        if (f == EcorePackage.eINSTANCE.getENamedElement_Name())
        {
            String oldName = (String)oldValue;
            String newName = (String)newValue;
            sb.append(RENAME).append(SPACE).append(quoteIdentifiers ? getDoubleQuotedString(oldName) : oldName).append(
                    SPACE).append(TO).append(SPACE).append(quoteIdentifiers ? getDoubleQuotedString(newName) : newName);
            script.addAlterTableRenameColumnStatements(sb.toString());
        }
        else if (f == SQLSchemaPackage.eINSTANCE.getTypedElement_ContainedType()
                || f == SQLSchemaPackage.eINSTANCE.getTypedElement_ReferencedType()
                )
        {
            sb.append(MODIFY).append(SPACE).append(builder.getName(column, quoteIdentifiers, false)).append(SPACE)
                    .append(getDataTypeString(column, column.getTable().getSchema(), quoteIdentifiers)).append(SPACE).append(
                            column.isNullable() ? EMPTY_STRING : NOT + SPACE).append(NULL);
            script.addAlterTableModifyColumnTypeStatements(sb.toString());
        }
        else if(f== SQLTablesPackage.eINSTANCE.getColumn_Nullable())
        {
            sb.append(MODIFY).append(SPACE).append(builder.getName(column, quoteIdentifiers, false)).append(SPACE)
                    .append(column.isNullable() ? EMPTY_STRING : NOT + SPACE).append(NULL);
            script.addAlterTableColumnStatements(sb.toString());
        }
        
        // alter column
        if (f == SQLTablesPackage.eINSTANCE.getColumn_DefaultValue())
        {
            sb = new StringBuffer("");

            if (column.isIsComputedColumn())
            {
                sb.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                        builder.getName(table, quoteIdentifiers, qualifyNames)).append(SPACE).append(ALTER).append(
                        SPACE).append(builder.getName(e, quoteIdentifiers, false)).append(SPACE);

                sb.append(SET).append(SPACE).append(COMPUTE).append(SPACE).append(LEFT_PARENTHESIS).append(newValue).append(RIGHT_PARENTHESIS);
            }
            else
            {
                StringBuffer dropComp = new StringBuffer("");
                dropComp.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                        builder.getName(table, quoteIdentifiers, qualifyNames)).append(SPACE).append(ALTER).append(
                        SPACE).append(builder.getName(e, quoteIdentifiers, false)).append(SPACE).append(DROP).append(SPACE).append(COMPUTE);
                script.addAlterTableColumnStatements(dropComp.toString());
                
                sb.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                        builder.getName(table, quoteIdentifiers, qualifyNames)).append(SPACE).append(MODIFY).append(
                        SPACE).append(builder.getName(e, quoteIdentifiers, false)).append(SPACE);

                if (newValue != null)
                {
                    sb.append(DEFAULT).append(SPACE).append(newValue);
                }
                else
                {
                    sb.append(DEFAULT).append(SPACE).append(NULL);
                }
            }
            script.addAlterTableColumnStatements(sb.toString());
        }
        else if (f == SQLTablesPackage.eINSTANCE.getColumn_GenerateExpression())
        {
        	sb = new StringBuffer("");
        	sb.append(ALTER).append(SPACE).append(builder.getName(e, quoteIdentifiers, qualifyNames)).append(SPACE);
            ValueExpression ve = (ValueExpression)newValue;
            if (ve != null && ve.getSQL() != null && !ve.getSQL().equals(""))
            {
                sb.append(SET).append(SPACE).append(COMPUTE).append(SPACE).append(LEFT_PARENTHESIS).append(ve.getSQL())
                        .append(RIGHT_PARENTHESIS);
            }
            else
            {
                sb.append(DROP).append(SPACE).append(COMPUTE);
            }
            script.addAlterTableColumnStatements(sb.toString());
        }
        else if(f == SQLSchemaPackage.eINSTANCE.getSQLObject_Description())
        {
            sb = new StringBuffer("");
            sb.append(SybaseASADdlBuilder.getInstance().createComment(e, quoteIdentifiers, true, true));
            script.addAlterOtherStatements(sb.toString());
        }
    }

    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {
        script.addAlterTableColumnStatements(statement);
    }

    protected void addDropStatement(SybaseDdlScript script, String statement)
    {
        script.addAlterTableDropColumnStatements(statement);
    }

}
