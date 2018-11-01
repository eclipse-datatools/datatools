/*******************************************************************************
 * Copyright (c) 2007-8 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase
 ******************************************************************************/
package org.eclipse.datatools.enablement.ase.deltaddl;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.enablement.ase.ISybaseASEDdlConstants;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

public class SybaseASERuleDeltaDdlGenProvider extends AbstractDeltaDdlGenProvider implements IDeltaDdlGenProvider, ISybaseASEDdlConstants
{
    /**
     * Processes the alter statement on UserDefinedType.<p>
     * 
     * @param element
     * @param modifyRecordMap
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @param script
     * @param monitor
     * 
     * @author renj
     */
    public void processAlterStatement(SQLObject element, Map modifyRecordMap, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script, IProgressMonitor monitor)
    {
        List list = (List) modifyRecordMap.get(element);
        if (list != null && list.size() > 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                SybaseDeltaDdlGeneration.FeatureChangeRecord cr = (SybaseDeltaDdlGeneration.FeatureChangeRecord) list
                        .get(i);
                // guarantee the rename statement should be the first script
                if (cr.feature == EcorePackage.eINSTANCE.getENamedElement_Name())
                {
                    list.remove(i);
                    list.add(0, cr);
                }
            }
        }
        if (list != null)
        {
            for (int i = 0; i < list.size(); i++)
            {
                StringBuffer sb = new StringBuffer(128);
                SybaseDeltaDdlGeneration.FeatureChangeRecord cr = (SybaseDeltaDdlGeneration.FeatureChangeRecord) list.get(i);
                int crFeatureID = cr.feature.getFeatureID();
                if (crFeatureID == EcorePackage.EATTRIBUTE__NAME)
                {
                    sb.append(EXEC).append(SPACE).append(SP_RENAME).append(SPACE);
                    if (quoteIdentifiers)
                    {
                        sb.append(getName((String) cr.oldValue, quoteIdentifiers)).append(COMMA).append(SPACE).append(
                                getName((String) cr.newValue, quoteIdentifiers));
                    }
                    else
                    {
                        sb.append(SQLUtil.quote((String) cr.oldValue, SINGLE_QUOTE)).append(COMMA).append(SPACE).append(
                                SQLUtil.quote((String) cr.newValue, SINGLE_QUOTE));
                    }
                    script.addAlterOtherStatements(sb.toString());
                }
                
            }
        }
    }

    /**
     * Gets the modification result.<p>
     * 
     * @param e
     * @param feature
     * @param oldValue
     * @param newValue
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @param script
     * 
     * @author renj
     */
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
    }

    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {
        // TODO Auto-generated method stub

    }

    protected void addDropStatement(SybaseDdlScript script, String statement)
    {
        // TODO Auto-generated method stub

    }

    protected String getName(String name, boolean quoteIdentifiers)
    {
        String Name = name;

        if (quoteIdentifiers)
        {
            Name = SQLDevToolsUtil.quoteWhenNecessary(name, (DatabaseIdentifier)getParameter());
        }
        else
        {
            Name = SQLUtil.quote(name, SINGLE_QUOTE);
        }

        return Name;
    }
}
