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
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration.FeatureChangeRecord;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * @author David Cui
 */
public class SybaseASEUniqueDeltaDdlGenProvider extends SybaseASEConstraintDeltaDdlGenProvider
{
    
    public void analyze(SQLObject element, Map changeMap, Map modificationRecords)
    {
        super.analyze(element, changeMap, modificationRecords);
        int flag = ((Integer)changeMap.get(element)).intValue();
        if((flag & (SybaseDeltaDdlGeneration.CREATE | SybaseDeltaDdlGeneration.DROP)) != 0)
        {
            if((element instanceof SybaseASEUniqueConstraint) && (element.eContainer() instanceof SybaseASEPrimaryKey))
            {
                changeMap.remove(element);
            }
            else if(element instanceof SybaseASEPrimaryKey)
            {
                changeMap.remove(((SybaseASEPrimaryKey)element).getAseUniqueConstraint());
            }
        }
    }
    /* (non-Javadoc)
     * @see org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider#processAlterStatement(org.eclipse.datatools.modelbase.sql.schema.SQLObject, java.util.Map, boolean, boolean, boolean, org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript, org.eclipse.core.runtime.IProgressMonitor)
     */
    
    public void processAlterStatement(SQLObject element, Map modifyRecordMap, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script, IProgressMonitor monitor)
    {
        if (!(element instanceof UniqueConstraint))
        {
            return;
        }
        UniqueConstraint constraint = (UniqueConstraint)element;
        List records = (List)modifyRecordMap.get(element);
        if (records != null)
        {
            for (Iterator iter = records.iterator(); iter.hasNext();)
            {
                FeatureChangeRecord r = (FeatureChangeRecord) iter.next();
                int featureID = r.feature.getFeatureID();
                if (featureID == SQLConstraintsPackage.INDEX__CLUSTERED
                        || featureID == SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__MEMBERS)
                {
                    //drop and create
                    String oldName = constraint.getName();
                    String newName = constraint.getName();
                    if(isConstraintNameChanged(modifyRecordMap,constraint))
                    {
                        oldName = getConstraintOldName(modifyRecordMap,constraint);
                    }
                    constraint.setName(oldName);
                    String dropStatement = _builder.dropUniqueConstraint(constraint, quoteIdentifiers, qualifyNames);
                    addDropStatement(script,dropStatement);
                    constraint.setName(newName);
                    String[] addStatement = _builder.addUniqueConstraint(constraint, quoteIdentifiers, qualifyNames,fullSyntax);
                    for(int i=0;i<addStatement.length;i++)
                    {
                        addCreateStatement(script,addStatement[i]);
                    }
                    return;
                }
                else if(featureID == SQLConstraintsPackage.INDEX__FILL_FACTOR)
                {
                    String statement = alterIndexParameter(constraint, r.newValue, FILLFACTOR);
                    script.addAlterOtherStatements(statement); 
                }
                else if(featureID == SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__MAX_ROW_PER_PAGE)
                {
                    String statement = alterIndexParameter(constraint, r.newValue, MAX_ROWS_PER_PAGE);
                    script.addAlterOtherStatements(statement); 
                }
                else if(featureID == SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__REVERSE_PAGE_GAP)
                {
                    String statement = alterIndexParameter(constraint, r.newValue, RESERVEPAGEGAP);
                    script.addAlterOtherStatements(statement); 
                }
                else if(featureID == SQLConstraintsPackage.CONSTRAINT__NAME)
                {
                    addConstraintRenameStatement(constraint, r.oldValue, r.newValue, quoteIdentifiers, qualifyNames, fullSyntax, script);
                }
                else if(featureID == SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SEGMENT)
                {
                    StringBuffer statement = new StringBuffer();
                    String objectName = constraint.getBaseTable().getName() + DOT + constraint.getName();
                    statement.append(EXEC).append(SPACE).append(SP_PLACEOBJECT).append(SPACE)
                            .append(SQLUtil.quote((String)r.newValue,SINGLE_QUOTE)).append(COMMA)
                                    .append(SQLUtil.quote(objectName,SINGLE_QUOTE));
                }
            }
        }
    }
    
    private String alterIndexParameter(TableConstraint constraint, Object newValue, String parameter)
    {
        StringBuffer statement = new StringBuffer(128);
        String objectName = constraint.getBaseTable().getName() + DOT + constraint.getName();
        statement.append(EXEC).append(SPACE).append(SP_CHGATTRIBUTE).append(SPACE)
           .append(SQLUtil.quote(objectName, SINGLE_QUOTE)).append(COMMA)
           .append(SQLUtil.quote(parameter,SINGLE_QUOTE)).append(COMMA).append((String)newValue);
        return statement.toString();
    }

}
