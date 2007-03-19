package org.eclipse.datatools.enablement.sybase.asa.deltaddl;

import java.util.List;
import java.util.Map;

import org.eclipse.datatools.enablement.sybase.asa.ISybaseASADdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.ddl.SybaseASADdlBuilder;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
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
                
                if( record.newValue == null || record.newValue.equals("")) //$NON-NLS-1$
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
            sb.append(RENAME).append(SPACE).append(oldValue).append(SPACE).append(TO).append(newValue);
            script.addAlterOtherStatements(sb.toString());
        }
        else if (f == SQLSchemaPackage.eINSTANCE.getTypedElement_ContainedType()
                || f == SQLSchemaPackage.eINSTANCE.getTypedElement_ReferencedType()
                || f == SQLTablesPackage.eINSTANCE.getColumn_Nullable())
        {
            sb.append(MODIFY).append(SPACE).append(builder.getName(column, quoteIdentifiers, false)).append(SPACE)
                    .append(getDataTypeString(column, column.getTable().getSchema())).append(SPACE).append(
                            column.isNullable() ? EMPTY_STRING : NOT + SPACE).append(NULL);
        }
        //alter column
        sb.append(ALTER).append(SPACE).append(builder.getName(e, quoteIdentifiers, qualifyNames)).append(SPACE);
        if (f == SQLTablesPackage.eINSTANCE.getColumn_DefaultValue())
        {
            if (newValue != null)
            {
                sb.append(SET).append(SPACE).append(DEFAULT).append(SPACE).append(newValue);
            }
            else
            {
                sb.append(DROP).append(SPACE).append(DEFAULT);
            }
        }
        else if (f == SQLTablesPackage.eINSTANCE.getColumn_GenerateExpression())
        {
            ValueExpression ve = (ValueExpression)newValue;
            if (ve != null && ve.getSQL() != null && !ve.getSQL().equals("")) //$NON-NLS-1$
            {
                sb.append(SET).append(SPACE).append(COMPUTE).append(SPACE).append(LEFT_PARENTHESIS).append(ve.getSQL())
                        .append(RIGHT_PARENTHESIS);
            }
            else
            {
                sb.append(DROP).append(SPACE).append(COMPUTE);
            }
        }

        script.addAlterTableColumnStatements(sb.toString());
    }

    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {
        script.addAlterTableColumnStatements(statement);
    }

    protected void addDropStatement(SybaseDdlScript script, String statement)
    {
        script.addAlterTableColumnStatements(statement);
    }

}
