/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.ase.deltaddl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.enablement.ase.ISybaseASEDdlConstants;
import org.eclipse.datatools.enablement.ase.ddl.SybaseASEDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETrigger;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration.FeatureChangeRecord;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SybaseASETriggerDeltaDdlProvider extends AbstractDeltaDdlGenProvider implements IDeltaDdlGenProvider,
        ISybaseASEDdlConstants
{
    // create a trigger will drop the old trigger of the same type automatically
    private static final String DECLARE = "declare @name varchar(767)\r\n"
                                                          + "declare @newname varchar(767)\r\n"
                                                          + "select @name = <<@name>>\r\n"
                                                          + "select @newname = <<@newname>>\r\n";
    private static final String BACKUP = DECLARE
                                                          + "declare @backupstatus int\r\n"
                                                          + "exec @backupstatus = sp_rename @name, @newname\r\n";

    // To avoid "DROP TRIGGER command not allowed within a trigger." when the trigger type if "Delete"
    // we have to separate the drop statement with the create statement
    private static final String DROP              = "if not exists(select 1 from sysobjects where uid = user_id(<<@owner>>) and name=<<@name>>)\r\n"
                                                          + "begin\r\n" 
                                                          + "    declare @oldname varchar(767)\r\n"
                                                          + "    declare @newname varchar(767)\r\n"
                                                          + "    select @oldname = <<@oldname>>\r\n"
                                                          + "    select @newname = <<@newname>>\r\n"
                                                          + "    exec sp_rename @newname, @oldname --restore\r\n"
                                                          + "end \r\n"
                                                          + "else if exists(select 1 from sysobjects where uid = user_id(<<@owner>>) and name=<<@newname>>)\r\n"
                                                          + "begin\r\n" 
                                                          + "    exec ('drop trigger '+<<@newname>>)\r\n"
                                                          + "end \r\n";
                                                          

    public void analyze(SQLObject element, Map changeMap, Map changedRecords)
    {
        // TODO Auto-generated method stub

    }

    public void processAlterStatement(SQLObject element, Map modifyRecordMap, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script, IProgressMonitor monitor)
    {
        if (!(element instanceof SybaseASETrigger))
        {
            return;
        }
        SybaseASETrigger trigger = (SybaseASETrigger) element;
        String owner = SQLUtil.quote(trigger.getSchema().getName(), "'");
        List records = (List) modifyRecordMap.get(element);
        if (records != null)
        {
            SybaseDdlBuilder builder = SybaseASEDdlBuilder.getInstance();
            String[] trigBody = builder.createTrigger(trigger, quoteIdentifiers, qualifyNames, fullSyntax);
            String source = trigBody[trigBody.length/2];
            String name = SQLUtil.quote(trigger.getName(), "'");
            String oldname = name;
            
            boolean enableChanged = false;
            boolean nameChanged = false;
            for (Iterator iter = records.iterator(); iter.hasNext();)
            {
                FeatureChangeRecord r = (FeatureChangeRecord) iter.next();
                if (r.feature.getFeatureID() == SQLSchemaPackage.SQL_OBJECT__NAME && r.changed == trigger)
                {
                    // rename, the name inside source should have already been updated, and create new one will
                    // automatically drop old one.
                    // script.addCreateTriggerStatement(source);
                    // return;
                    oldname = SQLUtil.quote((String) r.oldValue, "'");
                    nameChanged = true;
                }
                if (r.feature.getFeatureID() == SybaseasesqlmodelPackage.SYBASE_ASE_TRIGGER__ENABLED && r.changed == trigger)
                {
                    enableChanged = true;
                }
            }
            // normal modification
            if (! (enableChanged && records.size() == 1))
            {
                String tempname = SQLUtil.quote(SybaseASEProcedureDeltaDdlProvider.makeNewName(oldname), "'");
                String alter = BACKUP.replaceAll("<<@name>>", oldname).replaceAll("<<@newname>>", tempname);
                script.addCreateTriggerStatement(alter);
                script.addCreateTriggerStatement(source);

                String drop = DROP.replaceAll("<<@owner>>", owner).replaceAll("<<@name>>", name).replaceAll("<<@newname>>", tempname).replaceAll("<<@oldname>>", oldname);
                script.addCreateTriggerStatement(drop);
            }

        
            if (enableChanged || nameChanged)
            {
                StringBuffer sb = new StringBuffer(128);
                sb.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                        getTableName(trigger.getSubjectTable(), quoteIdentifiers, qualifyNames));
    
                if (trigger.isEnabled())
                {
                    sb.append(SPACE).append(ENABLE).append(SPACE);
                }
                else
                {
                    sb.append(SPACE).append(DISABLE).append(SPACE);
                }
    
                sb.append(TRIGGER).append(SPACE).append(
                        builder.getName(trigger, quoteIdentifiers, qualifyNames));
    
                script.addAlterOtherStatements(sb.toString());
            }
        }

    }

    public String getTableName(Table table, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String name = table.getName();
        String schemaName = table.getSchema().getName();
        String databaseName = table.getSchema().getCatalog().getName();

        if (quoteIdentifiers)
        {
            name = SQLDevToolsUtil.quoteWhenNecessary(name, SQLDevToolsUtil.getDatabaseIdentifier(table));
        }
        if (qualifyNames)
        {
            name = databaseName + DOT + schemaName + DOT + name;
        }

        return name;
    }

    public void processCreateStatement(SQLObject element, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax, SybaseDdlScript script, ISybaseDdlGenerator generator, IProgressMonitor monitor)
    {
        // TODO Auto-generated method stub

    }

    public void processDropStatement(SQLObject element, boolean quoteIdentifiers, boolean qualifyNames,
            SybaseDdlScript script, DDLGenerator generator, IProgressMonitor monitor)
    {
        // TODO Auto-generated method stub

    }

    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {
        // TODO Auto-generated method stub

    }

    protected void addDropStatement(SybaseDdlScript script, String statement)
    {
        // TODO Auto-generated method stub

    }

    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        // TODO Auto-generated method stub

    }

}
