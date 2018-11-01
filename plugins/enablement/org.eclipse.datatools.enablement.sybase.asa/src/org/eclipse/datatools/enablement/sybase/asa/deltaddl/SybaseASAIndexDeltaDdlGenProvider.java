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
import org.eclipse.datatools.enablement.sybase.asa.ddl.SybaseASADdlBuilder;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseIndexDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * ASA index delta ddl generator provider
 * 
 * @author Hui Wan
 */
public class SybaseASAIndexDeltaDdlGenProvider extends SybaseIndexDeltaDdlGenProvider implements IDeltaDdlGenProvider,
        ISybaseASADdlConstants
{
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        SybaseASABaseIndex index = (SybaseASABaseIndex) e;
        int featureId = feature.getFeatureID();
        Table table = index.getTable();
        String owner = table.getSchema().getName();
        String tableName = table.getName();
        StringBuffer statement = new StringBuffer(256);
        switch (featureId)
        {
            // index name
            case SQLConstraintsPackage.INDEX__NAME:
                // ALTER INDEX COL1_HG_OLD ON jak.mytable RENAME AS COL1_HG_NEW
                statement.append(ALTER + SPACE + INDEX + SPACE);
                statement.append(quoteIdentifiers ? SQLUtil.quote(oldValue.toString(), DOUBLE_QUOTE) : oldValue
                        .toString());
                statement.append(SPACE + ON + SPACE);
                statement.append(quoteIdentifiers ? SQLUtil.quote(owner, DOUBLE_QUOTE) : owner);
                statement.append(DOT);
                statement.append(quoteIdentifiers ? SQLUtil.quote(tableName, DOUBLE_QUOTE) : tableName);
                statement.append(SPACE + RENAME + SPACE + AS + SPACE);
                statement.append(quoteIdentifiers ? SQLUtil.quote(newValue.toString(), DOUBLE_QUOTE) : newValue);
                script.addAlterOtherStatements(statement.toString());
                break;
            // Clustered
            case SQLConstraintsPackage.INDEX__CLUSTERED:
                // ALTER INDEX COL1_HG_OLD ON jak.mytable CLUSTERED
                statement.append(ALTER + SPACE + INDEX + SPACE);
                statement.append(quoteIdentifiers ? SQLUtil.quote(index.getName(), DOUBLE_QUOTE) : index.getName());
                statement.append(SPACE + ON + SPACE);
                statement.append(quoteIdentifiers ? SQLUtil.quote(owner, DOUBLE_QUOTE) : owner);
                statement.append(DOT);
                statement.append(quoteIdentifiers ? SQLUtil.quote(tableName, DOUBLE_QUOTE) : tableName);
                if (newValue instanceof Boolean && ((Boolean) newValue).booleanValue())
                {
                    statement.append(SPACE + CLUSTERED);
                }
                else
                {
                    statement.append(SPACE + NONCLUSTERED);
                }
                if (((Boolean) newValue).booleanValue())
                {
                    script.addAlterIndexClusteredStatements(statement.toString());
                }
                else
                {
                    script.addAlterIndexNonclusteredStatements(statement.toString());
                }
                break;
            case SQLConstraintsPackage.INDEX__DESCRIPTION:
                 script.addAlterOtherStatements(SybaseASADdlBuilder.getInstance().createComment(e, quoteIdentifiers, qualifyNames));
                break;
            default:
                return;
        }
        
    }
    
    protected boolean needRecreate(EStructuralFeature feature)
    {
        if (feature != null)
        {
            switch (feature.getFeatureID())
            {
                case SQLConstraintsPackage.INDEX__NAME:
                    return false;
                case SQLConstraintsPackage.INDEX__CLUSTERED:
                    return false;
                case SQLConstraintsPackage.INDEX__DESCRIPTION:
                    return false;
                default:
                    return true;
            }
        }
        return true;
    }

}
