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
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProcedure;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration.FeatureChangeRecord;
import org.eclipse.datatools.enablement.sybase.parser.QuickSQLParser;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SybaseASAProcedureDeltaDdlGenProvider implements IDeltaDdlGenProvider, IGenericDdlConstants
{

    public void processAlterStatement(SQLObject element, Map modifyRecordMap, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script, IProgressMonitor monitor)
    {
        if (!(element instanceof SybaseASABaseProcedure))
        {
            return;
        }
        SybaseASABaseProcedure proc = (SybaseASABaseProcedure)element;
        SybaseDdlBuilder builder = SybaseASADdlBuilder.getInstance();
        
        List records = (List)modifyRecordMap.get(element);
        if (records != null)
        {
            String[] create = builder.createProcedure(proc, quoteIdentifiers, qualifyNames, fullSyntax);
            String comment = null;
            boolean sourceChanged = false;
            boolean commentChanged = false;
            
            for (Iterator iter = records.iterator(); iter.hasNext();)
            {
                FeatureChangeRecord r = (FeatureChangeRecord) iter.next();
                //to filter out parameter name changes
                if (r.feature.getFeatureID() == SQLRoutinesPackage.PROCEDURE__NAME && r.changed == proc)
                {
                    //rename: create new and drop old
                    String oldName = (String)r.oldValue;
                    String schemaName = proc.getSchema().getName();

                    if (quoteIdentifiers)
                    {
                        oldName = SQLUtil.quote(oldName, "\"");
                        schemaName = SQLUtil.quote(schemaName, "\"");
                    }

                    if (qualifyNames)
                    {
                        oldName = schemaName + DOT + oldName;
                    }
                    
                    StringBuffer drop = new StringBuffer(128);
                    drop.append(DROP).append(SPACE).append(PROCEDURE).append(SPACE).append(oldName);

                    for (int i = 0; i < create.length; i++)
                    {
                        script.addCreateRoutineStatements(create[i]);
                    }
                    script.addDropRoutineStatement(drop.toString());
                    return;
                }
                else if (r.feature.getFeatureID() == SQLSchemaPackage.SQL_OBJECT__DESCRIPTION)
                {
                    commentChanged = true;
                    comment = ((SybaseASADdlBuilder)builder).createComment(proc, quoteIdentifiers, qualifyNames, true);
                }
                else if (r.feature.getFeatureID() == SQLRoutinesPackage.SOURCE__BODY && r.changed == proc.getSource())
                {
                    sourceChanged = true;
                }
            }

            if (commentChanged && !sourceChanged && comment != null)
            {
                //comments only
                script.addCreateRoutineStatements(comment);
                return;
            }
            
            if (!sourceChanged)
            {
                //let AuthorizationIdentifier delta ddl to handle privileges
                return;
            }

            //normal modification, change create to alter
            QuickSQLParser parser = QuickSQLParser.getInstance();
            int[] createIndex = parser.find(create[0], new String[]{CREATE});
            if (createIndex[0] >= 0)
            {
                String alter = create[0].substring(0, createIndex[0]) + ALTER + create[0].substring(createIndex[0] + 6);
                script.addCreateRoutineStatements(alter);
                if (comment != null)
                {
                    script.addCreateRoutineStatements(comment);
                }
            }
            else
            {
                //should not happen, might be the parser issue
//                String drop = builder.dropProcedure(proc, quoteIdentifiers, qualifyNames);
//                script.addDropRoutineStatement(drop);
                for (int i = 0; i < create.length; i++)
                {
                    script.addCreateRoutineStatements(create[i]);
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
