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
package org.eclipse.datatools.enablement.sybase.deltaddl;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * This class provides a skeletal implementation of the IDeltaDdlGenProvider interface to minimize the effort required
 * to implement getModificationResult method to generate modifcation sql script.
 * 
 * @author linsong
 */
public abstract class AbstractDeltaDdlGenProvider extends GenericDdlBuilder implements IDeltaDdlGenProvider
{
    private Object _parameter;
    
    public void analyze(SQLObject element, Map changeMap, Map modificationRecords)
    {
        List records = (List) modificationRecords.get(element);
        if (records != null)
        {
            for (int i = records.size() - 1; i >= 0; i--)
            {
                SybaseDeltaDdlGeneration.FeatureChangeRecord rc = (SybaseDeltaDdlGeneration.FeatureChangeRecord) records
                        .get(i);
                EStructuralFeature feature = rc.feature;
                if (feature instanceof EReference
                // && feature.isMany()
                        && ((EReference) feature).getEOpposite() != null && ((EReference) feature).isContainment())
                {
                    records.remove(i);
                }
            }
            
            if(records.size() == 0)
                changeMap.remove(element);
        }
    }

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
            for (int i = 0; i < list.size(); i++)
            {
                SybaseDeltaDdlGeneration.FeatureChangeRecord cr = (SybaseDeltaDdlGeneration.FeatureChangeRecord) list
                        .get(i);
                getModificationResult(element, cr.feature, cr.oldValue, cr.newValue, quoteIdentifiers, qualifyNames, fullSyntax, script);
            }
        }
    }

    public void processCreateStatement(SQLObject element, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax, SybaseDdlScript script, ISybaseDdlGenerator generator, IProgressMonitor monitor)
    {
        String[] statements = generator.createSQLObjectsForDeltaDDL(new SQLObject[]
        {
            element
        }, quoteIdentifiers, qualifyNames, fullSyntax, monitor);

        for (int i = 0; i < statements.length; i++)
        {
            addCreateStatement(script, statements[i]);
        }
    }

    public void processDropStatement(SQLObject element, boolean quoteIdentifiers, boolean qualifyNames,
            SybaseDdlScript script, ISybaseDdlGenerator generator, IProgressMonitor monitor)
    {
        String[] statements = generator.dropSQLObjectsForDeltaDDL(new SQLObject[]
        {
            element
        }, quoteIdentifiers, qualifyNames, monitor);

        for (int i = 0; i < statements.length; i++)
        {
            addDropStatement(script, statements[i]);
        }
    }

    public void setParameter(Object obj) {
        _parameter = obj;
    }

    public Object getParameter() {
        return _parameter;
    }
    
    /**
     * add create statement to the specified position of the scripts
     * 
     * @param script
     * @param statement
     */
    protected abstract void addCreateStatement(SybaseDdlScript script, String statement);

    /**
     * add drop statement to the specified position of the scripts
     * 
     * @param script
     * @param statement
     */
    protected abstract void addDropStatement(SybaseDdlScript script, String statement);

    /**
     * add generated modification sql script to script, according specified SQLObject, feature, oldValue and newValue
     * 
     * @param e
     * @param feature
     * @param oldValue
     * @param newValue
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @param script
     */
    protected abstract void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue,
            Object newValue, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script);
}
