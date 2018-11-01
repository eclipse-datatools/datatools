/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.deltaddl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.enablement.sybase.IGenericDdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.ddl.SybaseASADdlBuilder;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration.FeatureChangeRecord;
import org.eclipse.datatools.enablement.sybase.parser.QuickSQLParser;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SybaseASATriggerDeltaDdlGenProvider implements IDeltaDdlGenProvider, IGenericDdlConstants
{

    public void processAlterStatement(SQLObject element, Map modifyRecordMap, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script, IProgressMonitor monitor)
    {
        if (!(element instanceof SybaseASABaseTrigger))
        {
            return;
        }
        SybaseASABaseTrigger trigger = (SybaseASABaseTrigger)element;
        SybaseDdlBuilder builder = SybaseASADdlBuilder.getInstance();
        
        List records = (List)modifyRecordMap.get(element);
        if (records != null)
        {
            String[] create = ((SybaseASADdlBuilder)builder).createTrigger(trigger, quoteIdentifiers, qualifyNames, fullSyntax);
            String comment = null;
            
            for (Iterator iter = records.iterator(); iter.hasNext();)
            {
                FeatureChangeRecord r = (FeatureChangeRecord) iter.next();
                if (r.feature.getFeatureID() == SQLSchemaPackage.SQL_OBJECT__NAME && r.changed == trigger)
                {
                    //rename: create new and drop old
                    String oldName = (String)r.oldValue;

                    //we have to include the table name as well
                    if (quoteIdentifiers)
                    {
                        oldName = SQLUtil.quote(oldName, "\"");
                    }

                    if(qualifyNames) {
                        String schemaName = trigger.getSchema().getName();
                        String tableName = trigger.getSubjectTable().getName();
                        if(quoteIdentifiers) {
                            tableName = SQLUtil.quote(tableName, "\"");
                        }
                        oldName = schemaName + DOT + tableName + DOT + oldName;
                    }
                    
                    StringBuffer drop = new StringBuffer(128);
                    drop.append(DROP).append(SPACE).append(TRIGGER).append(SPACE).append(oldName);

                    //for asa trigger, we must drop before create
                    script.addDropTriggerStatement(drop.toString());
                    for (int i = 0; i < create.length; i++)
                    {
                        script.addCreateTriggerStatement(create[i]);
                    }

                    return;
                }
                else if (r.feature.getFeatureID() == SQLSchemaPackage.SQL_OBJECT__DESCRIPTION)
                {
                    comment = ((SybaseASADdlBuilder)builder).createComment(trigger, quoteIdentifiers, qualifyNames, true);
                }
            }

            if (records.size() == 1 && ((FeatureChangeRecord)records.get(0)).feature.getFeatureID() == SQLSchemaPackage.SQL_OBJECT__DESCRIPTION)
            {
                //comments only
                script.addCreateTriggerStatement(comment);
                return;
            }

            //normal modification, change create to alter
            QuickSQLParser parser = QuickSQLParser.getInstance();
            int[] createIndex = parser.find(create[0], new String[]{CREATE});
            if (createIndex[0] >= 0)
            {
                String alter = create[0].substring(0, createIndex[0]) + ALTER + create[0].substring(createIndex[0] + 6);
                script.addCreateTriggerStatement(alter);
                if (comment != null)
                {
                    script.addCreateTriggerStatement(comment);
                }
            }
            else
            {
                //should not happen, might be the parser issue
//                String drop = builder.dropTrigger(trigger, quoteIdentifiers, qualifyNames);
//                script.addDropTriggerStatement(drop);
                for (int i = 0; i < create.length; i++)
                {
                    script.addCreateTriggerStatement(create[i]);
                }

            }
        }
    }

    public void processCreateStatement(SQLObject element, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax, SybaseDdlScript script, ISybaseDdlGenerator generator, IProgressMonitor monitor)
    {
        // TODO Auto-generated method stub

    }

    public void processDropStatement(SQLObject element, boolean quoteIdentifiers, boolean qualifyNames,
            SybaseDdlScript script, ISybaseDdlGenerator generator, IProgressMonitor monitor)
    {
        // TODO Auto-generated method stub

    }

    public void analyze(SQLObject element, Map changeMap, Map modificationRecords)
    {
        // TODO Auto-generated method stub
        
    }

}
