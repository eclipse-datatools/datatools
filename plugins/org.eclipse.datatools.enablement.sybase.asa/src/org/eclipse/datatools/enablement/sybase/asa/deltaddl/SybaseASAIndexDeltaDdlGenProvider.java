/**
 * Created on 2007-1-22
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.enablement.sybase.asa.deltaddl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.enablement.sybase.asa.ISybaseASADdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAIndex;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * ASA index delta ddl generator provider
 * 
 * @author Hui Wan
 */
public class SybaseASAIndexDeltaDdlGenProvider extends AbstractDeltaDdlGenProvider implements IDeltaDdlGenProvider,
        ISybaseASADdlConstants
{
    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {
        script.addCreateIndexStatement(statement);

    }
    protected void addDropStatement(SybaseDdlScript script, String statement)
    {
        script.addDropIndexStatement(statement);

    }
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        SybaseASAIndex index = (SybaseASAIndex) e;
        int featureId = feature.getFeatureID();
        Table table = index.getTable();
        String owner = table.getSchema().getName();
        String tableName = table.getName();
        StringBuffer statement = new StringBuffer(256);
        switch (featureId)
        {
            // index name
            case EcorePackage.ENAMED_ELEMENT__NAME:
                // ALTER INDEX COL1_HG_OLD ON jak.mytable RENAME AS COL1_HG_NEW
                statement.append(ALTER + SPACE + INDEX + SPACE);
                statement.append(SQLUtil.quote(oldValue.toString(), DOUBLE_QUOTE));
                statement.append(SPACE + ON + SPACE);
                statement.append(SQLUtil.quote(owner, DOUBLE_QUOTE));
                statement.append(DOT);
                statement.append(SQLUtil.quote(tableName, DOUBLE_QUOTE));
                statement.append(SPACE + RENAME + SPACE + AS + SPACE);
                statement.append(SQLUtil.quote(newValue.toString(), DOUBLE_QUOTE));
                break;
            // Clustered
            case SQLConstraintsPackage.INDEX__CLUSTERED:
                // ALTER INDEX COL1_HG_OLD ON jak.mytable CLUSTERED
                statement.append(ALTER + SPACE + INDEX + SPACE);
                statement.append(SQLUtil.quote(((SybaseASAIndex) (oldValue)).getName(), DOUBLE_QUOTE));
                statement.append(SPACE + ON + SPACE);
                statement.append(SQLUtil.quote(owner, DOUBLE_QUOTE));
                statement.append(DOT);
                statement.append(SQLUtil.quote(tableName, DOUBLE_QUOTE));
                if (newValue instanceof Boolean && ((Boolean) newValue).booleanValue())
                {
                    statement.append(SPACE + CLUSTERED);
                }
                else
                {
                    statement.append(SPACE + NONCLUSTERED);
                }
                break;
            default:
                return;
        }
        script.addAlterOtherStatements(statement.toString());
    }

    protected void analyze(Map changeMap)
    {
        List elements = new LinkedList();
        elements.addAll(changeMap.keySet());
        Iterator it = elements.iterator();
        while (it.hasNext())
        {
            EObject e = (EObject) it.next();
            int flag = ((Integer) changeMap.get(e)).intValue();
            if (needRecreate(e, flag))
            {
                changeMap = new HashMap();
                changeMap.put(e.eContainer(), new Integer(1 | 2));
                break;
            }
        }
    }

    protected boolean needRecreate(EObject e, int flag)
    {
        if (e == null)
        {
            return false;
        }
        switch (e.eContainingFeature().getFeatureID())
        {
            case SybaseasasqlmodelPackage.SYBASE_ASA_INDEX__NAME:
                return true;
            case SybaseasasqlmodelPackage.SYBASE_ASA_INDEX__CLUSTERED:
                return true;
            default:
                return false;
        }
    }

}
