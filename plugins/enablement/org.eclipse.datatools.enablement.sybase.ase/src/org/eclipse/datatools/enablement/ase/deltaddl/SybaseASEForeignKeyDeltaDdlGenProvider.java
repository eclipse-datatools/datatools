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
package org.eclipse.datatools.enablement.ase.deltaddl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration.FeatureChangeRecord;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * @author David Cui
 */
public class SybaseASEForeignKeyDeltaDdlGenProvider extends SybaseASEConstraintDeltaDdlGenProvider
{

    /* (non-Javadoc)
     * @see org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider#processAlterStatement(org.eclipse.datatools.modelbase.sql.schema.SQLObject, java.util.Map, boolean, boolean, boolean, org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript, org.eclipse.core.runtime.IProgressMonitor)
     */
    
    public void processAlterStatement(SQLObject element, Map modifyRecordMap, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script, IProgressMonitor monitor)
    {
        if (!(element instanceof ForeignKey))
        {
            return;
        }
        ForeignKey foreignKey = (ForeignKey)element;
        List records = (List)modifyRecordMap.get(element);
        if (records != null)
        {
            for (Iterator iter = records.iterator(); iter.hasNext();)
            {
                FeatureChangeRecord r = (FeatureChangeRecord) iter.next();
                int featureID = r.feature.getFeatureID();
                //FIXME:shold we remove "featureID == SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_MEMBERS"?
                if (featureID == SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_MEMBERS
                        || featureID == SQLConstraintsPackage.FOREIGN_KEY__MATCH
                        || featureID == SQLConstraintsPackage.FOREIGN_KEY__MEMBERS
                        || featureID == SQLConstraintsPackage.CONSTRAINT__NAME)
                {
                    // drop and create
                    String oldName = foreignKey.getName();
                    String newName = foreignKey.getName();
                    if(isConstraintNameChanged(modifyRecordMap,foreignKey))
                    {
                        oldName = getConstraintOldName(modifyRecordMap,foreignKey);
                    }
                    foreignKey.setName(oldName);
                    String dropStatement = _builder.dropForeignKey(foreignKey, quoteIdentifiers, qualifyNames);
                    addDropStatement(script,dropStatement);
                    foreignKey.setName(newName);
                    String[] addStatement = _builder.addForeignKey(foreignKey, quoteIdentifiers, qualifyNames,fullSyntax);
                    for(int i=0;i<addStatement.length;i++)
                    {
                        addCreateStatement(script,addStatement[i]);
                    }
                    return;
                }
            }
        }
    }
}
