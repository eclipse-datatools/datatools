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

import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlConstants;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlUtils;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Index delta ddl provider
 * 
 * @author Hui Wan
 */
public abstract class SybaseIndexDeltaDdlGenProvider extends AbstractDeltaDdlGenProvider implements
        IDeltaDdlGenProvider, ISybaseDdlConstants
{

    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {
        script.addCreateIndexStatement(statement);
    }

    protected void addDropStatement(SybaseDdlScript script, String statement)
    {
        script.addDropIndexStatement(statement);
    }

    public void analyze(SQLObject element, Map changeMap, Map modificationRecords)
    {
        super.analyze(element, changeMap, modificationRecords);
        List records = (List) modificationRecords.get(element);
        if (records != null)
        {
            for (int i = records.size() - 1; i >= 0; i--)
            {
                SybaseDeltaDdlGeneration.FeatureChangeRecord rc = (SybaseDeltaDdlGeneration.FeatureChangeRecord) records
                        .get(i);
                EStructuralFeature feature = rc.feature;
                // if need drop and rename
                if (needRecreate(feature) && !SybaseDdlUtils.isEqualIndexMembers(rc.newValue, rc.oldValue))
                {
                    changeMap.clear();
                    changeMap
                            .put(element, new Integer(SybaseDeltaDdlGeneration.CREATE | SybaseDeltaDdlGeneration.DROP));
                }
            }
        }
    }

    /**
     * Check whether the element need to be drop and recreate, sub class should override
     * 
     * @param feature
     * @return
     */
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
                default:
                    return true;
            }
        }
        return true;
    }

}
