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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.enablement.sybase.IGenericDdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.ISybaseASADdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.ddl.SybaseASADdlBuilder;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration.FeatureChangeRecord;
import org.eclipse.datatools.enablement.sybase.parser.QuickSQLParser;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.common.util.EList;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SybaseASAEventDeltaDdlGenProvider implements IDeltaDdlGenProvider, IGenericDdlConstants, ISybaseASADdlConstants
{
    private static final int TYPE_IND = 0;
    private static final int WHERE_IND = 1;
    private static final int SCHEDULE_IND = 2;
    private static final int ENABLE_IND = 3;
    private static final int LOCATION_IND = 4;
    
    private static final String MODIFY = "MODIFY";
    
    public void processAlterStatement(SQLObject element, Map modifyRecordMap, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script, IProgressMonitor monitor)
    {
        if (!(element instanceof SybaseASABaseEvent))
        {
            return;
        }
        SybaseASABaseEvent event = (SybaseASABaseEvent)element;
        SybaseASADdlBuilder builder = SybaseASADdlBuilder.getInstance();
        String[] alters = new String[5];//0:type; 1: where; 2: schedule; 3: enable; 4: location
        //if there're additional schedule changes, they are put into this arraylist
        ArrayList altSchedules = new ArrayList();//other schedules
        
        List records = (List)modifyRecordMap.get(element);
        if (records != null)
        {
            String[] create = builder.createEvent(event, quoteIdentifiers, qualifyNames, fullSyntax);
            String comment = null;
            
            for (Iterator iter = records.iterator(); iter.hasNext();)
            {
                FeatureChangeRecord r = (FeatureChangeRecord) iter.next();
                if (r.feature.getFeatureID() == SQLSchemaPackage.SQL_OBJECT__NAME && r.changed == event)
                {
                    //rename: create new and drop old
                    String oldName = (String)r.oldValue;
                    String schemaName = event.getEventCreator().getName();

                    if (quoteIdentifiers)
                    {
                        oldName = SQLUtil.quote(oldName, "\"");
                        schemaName = SQLUtil.quote(schemaName, "\"");
                    }

                    StringBuffer drop = new StringBuffer(128);
                    drop.append(DROP).append(SPACE).append(EVENT).append(SPACE).append(oldName);

                    for (int i = 0; i < create.length; i++)
                    {
                        script.addCreateEventStatements(create[i]);
                    }
                    script.addDropEventStatements(drop.toString());
                    return;
                }
                else if (r.feature.getFeatureID() == SQLSchemaPackage.SQL_OBJECT__DESCRIPTION)
                {
                    comment = ((SybaseASADdlBuilder)builder).createComment(event, quoteIdentifiers, qualifyNames, true);
                }
                else if (r.feature.getFeatureID() == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT__EVENT_TYPE)
                {
                    if (event.getEventType() == null || event.getEventType().getValue() == EventType.NOEVENTTYPE)
                    {
                        alters[TYPE_IND] = DELETE + SPACE + TYPE + NEWLINE;
                    }
                    else
                    {
                        alters[TYPE_IND] = TYPE + SPACE + event.getEventType().getLiteral() + NEWLINE;
                    }
                }
                else if (r.feature.getFeatureID() == SQLSchemaPackage.EVENT__CONDITION)
                {
                    if (event.getCondition() == null || event.getCondition().trim().equals(""))
                    {
                        alters[WHERE_IND] = WHERE + SPACE + NULL + NEWLINE;
                    }
                    else
                    {
                        alters[WHERE_IND] = WHERE + SPACE + event.getCondition() + NEWLINE;
                    }
                }
                else if (r.feature.getFeatureID() == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT__SCHEDULES)
                {
                    //alter schedule can't be combined with alter where statement, 
                    //to simplify the logic, always create separate statements for schedule
                    EList oldSch = (EList)r.oldValue;
                    EList schedules = event.getSchedules();
                    if (schedules == null || schedules.isEmpty())
                    {
                        if (oldSch != null && !oldSch.isEmpty())
                        {
//                            String sch = DELETE + SPACE + SCHEDULE  + SPACE + ((Schedule)oldSch.get(0)).getName() + NEWLINE;
//                            alters[SCHEDULE_IND] = sch;
                            for (int i = 0; i < oldSch.size(); i++)
                            {
                                String sch = DELETE + SPACE + SCHEDULE  + SPACE + ((Schedule)oldSch.get(i)).getName() + NEWLINE;
                                altSchedules.add(sch);
                            }
                        }
                    }
                    else
                    {
                        if (oldSch == null && oldSch.isEmpty())
                        {
//                            String sch = ADD + SPACE + SCHEDULE  + SPACE + builder.getEventSchedule((Schedule)schedules.get(0)) + NEWLINE;
//                            alters[SCHEDULE_IND] = sch;
                            for (int i = 0; i < schedules.size(); i++)
                            {
                                String sch = ADD + SPACE + SCHEDULE  + SPACE + ((Schedule)schedules.get(i)).getName() + NEWLINE;
                                altSchedules.add(sch);
                            }
                        }
                        else
                        {
                            for (int i = 0; i < oldSch.size(); i++)
                            {
                                boolean found = false;
                                for (int j = 0; j < schedules.size(); j++)
                                {
                                    if (((Schedule)schedules.get(j)).getName().equals(((Schedule)oldSch.get(i)).getName()))
                                    {
                                        found = true;
                                        break;
                                    }
                                }
                                if (!found)
                                {
                                    String sch = DELETE + SPACE + SCHEDULE  + SPACE + ((Schedule)oldSch.get(i)).getName() + NEWLINE;
//                                    if (i == 0 && alters[SCHEDULE_IND] == null)
//                                    {
//                                        alters[SCHEDULE_IND] = sch;
//                                    }
//                                    else
//                                    {
                                        altSchedules.add(sch);
//                                    }
                                }
                            }
                            
                            for (int j = 0; j < schedules.size(); j++)
                            {
                                boolean found = false;
                                boolean same = false;
                                {
                                    for (int i = 0; i < oldSch.size(); i++)
                                    if (((Schedule)schedules.get(j)).getName().equals(((Schedule)oldSch.get(i)).getName()))
                                    {
                                        found = true;
                                        if (((Schedule)schedules.get(j)).equals(oldSch.get(i)))
                                        {
                                            same = true;
                                        }
                                        
                                        break;
                                    }
                                }
                                if (found && !same)
                                {
                                    String sch = MODIFY + SPACE + builder.getEventSchedule((Schedule)schedules.get(j)) + NEWLINE;
//                                    if (j == 0 && alters[SCHEDULE_IND] == null)
//                                    {
//                                        alters[SCHEDULE_IND] = sch;
//                                    }
//                                    else
//                                    {
                                        altSchedules.add(sch);
//                                    }
                                }
                                else if (!found)
                                {
                                    String sch = ADD + SPACE + builder.getEventSchedule((Schedule)schedules.get(j)) + NEWLINE;
//                                    if (j == 0 && alters[SCHEDULE_IND] == null)
//                                    {
//                                        alters[SCHEDULE_IND] = sch;
//                                    }
//                                    else
//                                    {
                                        altSchedules.add(sch);
//                                    }
                                }
                            }
                        }
                    }
                }
                else if (r.feature.getFeatureID() == SQLSchemaPackage.EVENT__ENABLED)
                {
                    if (event.isEnabled())
                    {
                        alters[ ENABLE_IND] = ENABLE + NEWLINE;
                    }
                    else
                    {
                        alters[ ENABLE_IND] = DISABLE + NEWLINE;
                    }
                }
                else if (r.feature.getFeatureID() == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT__LOCATION)
                {
                    alters[ LOCATION_IND] = AT + SPACE + event.getLocation().getLiteral() + NEWLINE;
                }
                
            }

            if (records.size() == 1 && ((FeatureChangeRecord)records.get(0)).feature.getFeatureID() == SQLSchemaPackage.SQL_OBJECT__DESCRIPTION)
            {
                //comments only
                script.addCreateEventStatements(comment);
                return;
            }

            //normal modification, change create to alter
            QuickSQLParser parser = QuickSQLParser.getInstance();
            int[] createIndex = parser.find(create[0], new String[]{CREATE});
            int[] handlerIndex = parser.find(create[0], new String[]{HANDLER});
            
            if (createIndex[0] >= 0 && handlerIndex[0] >= 0 )
            {
                //do not include header comment in alter: create[0].substring(0, createIndex[0]) +
                String alter =  ALTER + SPACE + EVENT + SPACE + builder.getName(event, quoteIdentifiers, false) + NEWLINE;
                if (alters[WHERE_IND] == null && !altSchedules.isEmpty())
                {
                    alters[SCHEDULE_IND] = (String)altSchedules.get(0);
                    altSchedules.remove(0);
                }
                for (int i = 0; i < alters.length; i++)
                {
                    if (alters[i] != null)
                    {
                        alter += alters[i]; 
                    }
                }
                alter += create[0].substring(handlerIndex[0]);
                script.addCreateEventStatements(alter);
                
                String alterHeader = ALTER + SPACE + EVENT + SPACE + builder.getName(event, quoteIdentifiers, false) + NEWLINE;
                for (Iterator it = altSchedules.iterator(); it.hasNext();)
                {
                    String sch = (String) it.next();
                    script.addCreateEventStatements( alterHeader + sch);
                }
                
                if (comment != null)
                {
                    script.addCreateEventStatements(comment);
                }
            }
            else
            {
                //should not happen, might be the parser issue
//                String drop = builder.dropEvent(event, quoteIdentifiers, qualifyNames);
//                script.addDropEventStatements(drop);
                for (int i = 0; i < create.length; i++)
                {
                    script.addCreateEventStatements(create[i]);
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
