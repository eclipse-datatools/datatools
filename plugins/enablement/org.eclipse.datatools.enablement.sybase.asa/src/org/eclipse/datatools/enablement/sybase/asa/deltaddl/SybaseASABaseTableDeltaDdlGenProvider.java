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

import org.eclipse.datatools.enablement.sybase.asa.ISybaseASADdlConstants;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

public class SybaseASABaseTableDeltaDdlGenProvider extends AbstractDeltaDdlGenProvider implements IDeltaDdlGenProvider,
        ISybaseASADdlConstants
{
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        StringBuffer sb = new StringBuffer(128);
        if (feature == EcorePackage.eINSTANCE.getENamedElement_Name())
        {
            Table table = (Table)e;
            sb.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                    getTableName((String) oldValue, table.getSchema().getName(), quoteIdentifiers, qualifyNames))
                    .append(SPACE).append(RENAME).append(SPACE).append(
                            quoteIdentifiers ? SQLUtil.quote((String) newValue, DOUBLE_QUOTE) : newValue);
        }
        script.addAlterTableRenameStatements(sb.toString());
    }
    
    private String getTableName(String tableName, String ownerName, boolean quoteIdentifiers, boolean qualifyNames)
    {
        if(quoteIdentifiers)
        {
            tableName = SQLUtil.quote(tableName, DOUBLE_QUOTE);
            ownerName = SQLUtil.quote(ownerName, DOUBLE_QUOTE);
        }
        
        if(qualifyNames)
        {
            return ownerName + DOT + tableName;
        }
        else
        {
            return tableName;
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
