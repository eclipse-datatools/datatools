/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.deltaddl;

import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.enablement.sybase.asa.ISybaseASADdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.ddl.SybaseASADdlBuilder;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelFactory;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.ENamedElement;
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
        if (feature == SybaseasasqlmodelPackage.eINSTANCE.getSybaseASATable_Pctfree()
                || feature == SybaseasasqlmodelPackage.eINSTANCE.getSybaseASATempTable_Pctfree())
        {
            int pctvalue = ((Integer)newValue).intValue();
            sb.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                    builder.getName(e, quoteIdentifiers, qualifyNames));
            if(pctvalue != -1)
            {
                sb.append(SPACE).append(ADD);
            }
            sb.append(SPACE).append(PCTFREE)
                    .append(SPACE).append(pctvalue == -1 ? DEFAULT : newValue);
            script.addAlterTableStatement(sb.toString());
        }
        
        if (feature == SQLSchemaPackage.eINSTANCE.getSQLObject_Description())
        {
            sb = new StringBuffer("");
            sb.append(SybaseASADdlBuilder.getInstance().createComment(e, quoteIdentifiers, qualifyNames, true));
            script.addAlterOtherStatements(sb.toString());
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
