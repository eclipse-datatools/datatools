/**
 * Created on 2006-8-22
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.enablement.sybase.asa.deltaddl;

import org.eclipse.datatools.enablement.sybase.asa.ISybaseASADdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.ddl.SybaseASADdlBuilder;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASATableDeltaDdlGenProvider extends SybaseASABaseTableDeltaDdlGenProvider implements IDeltaDdlGenProvider,
        ISybaseASADdlConstants
{
    /**
     * <pre>
     * ALTER TABLE [ owner.]table-name
     * { ADD PCTFREE integer | PCTFREE DEFAULT }
     * </pre>
     */
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        super.getModificationResult(e, feature, oldValue, newValue, quoteIdentifiers, qualifyNames, fullSyntax, script);
        
        SybaseDdlBuilder builder = (SybaseDdlBuilder) SybaseASADdlBuilder.getInstance();
        StringBuffer sb = new StringBuffer(128);
        if (feature == SybaseasasqlmodelPackage.eINSTANCE.getSybaseASATable_Pctfree())
        {
            sb.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                    builder.getName(e, quoteIdentifiers, qualifyNames)).append(SPACE).append(ADD).append(SPACE).append(PCTFREE)
                    .append(SPACE).append(((Integer)newValue).intValue() == -1 ? DEFAULT : newValue);
            script.addAlterTableStatement(sb.toString());
        }
    }

    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {
        script.addCreateTableStatement(statement);
    }

    protected void addDropStatement(SybaseDdlScript script, String statement)
    {
        script.addDropTableStatement(statement);
    }
}
