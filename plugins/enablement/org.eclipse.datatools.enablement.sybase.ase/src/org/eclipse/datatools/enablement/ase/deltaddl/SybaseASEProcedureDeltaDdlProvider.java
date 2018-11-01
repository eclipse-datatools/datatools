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
package org.eclipse.datatools.enablement.ase.deltaddl;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.enablement.ase.ISybaseASEDdlConstants;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.TransactionModeType;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration.FeatureChangeRecord;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SybaseASEProcedureDeltaDdlProvider implements IDeltaDdlGenProvider, ISybaseASEDdlConstants
{
    static int _localhost = 0; 
    
    private static final String DROP ="drop proc ";
    private static final String DECLARE           =         "declare @name varchar(767)--proc name\r\n"
                                                          + "select @name = <<@name>>\r\n"
                                                          + "declare @newname varchar(767)\r\n"
                                                          + "select @newname = <<@newname>>\r\n"; 
    private static final String BACKUP =                    "declare @backupstatus int\r\n"
                                                          + "exec @backupstatus = sp_rename @name, @newname\r\n";
    private static final String CLEANUP =                   "if exists (select 1 from dbo.sysobjects o where o.uid = user_id(<<@owner>>) and o.name = <<@name>>)\r\n"
                                                          + "begin\r\n" 
                                                          + "    exec ('drop proc '+@newname)\r\n"
                                                          + "    --restore the x mode\r\n"
                                                          + "    exec sp_procxmode @name, <<xmode>>\r\n"
                                                          + "end \r\n" 
                                                          + "else\r\n" 
                                                          + "begin\r\n"
                                                          + "    exec sp_rename @newname, @name --restore\r\n"
                                                          + "end\r\n"; 
    private static final String RENAME            =         "declare @newname varchar(767)\r\n"
	      												  + "select @newname = <<@newname>>\r\n"
	      												  + "if exists (select 1 from dbo.sysobjects o where o.uid = user_id(<<@owner>>) and o.name = <<@newname>>)\r\n"
                                                          + "begin\r\n" 
                                                          + "    exec (<<@drop>>)\r\n"
                                                          + "    --restore the x mode\r\n"
                                                          + "    exec sp_procxmode @newname, <<xmode>>\r\n"
                                                          + "end \r\n"; 

    public void analyze(SQLObject element, Map changeMap, Map changedRecords)
    {
        // TODO Auto-generated method stub

    }

    public void processAlterStatement(SQLObject element, Map modifyRecordMap, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script, IProgressMonitor monitor)
    {
        if (!(element instanceof SybaseASEProcedure))
        {
            return;
        }
        SybaseASEProcedure proc = (SybaseASEProcedure)element;
        TransactionModeType xmode = proc.getTransactionMode();
        String modeLiteral = SQLUtil.quote(xmode.getLiteral(), "'");
        String owner = SQLUtil.quote(proc.getSchema().getName(), "'");
        boolean xmodeChanged = false;
        boolean sourceChanged = false;

        List records = (List)modifyRecordMap.get(element);
        if (records != null)
        {
            String source = proc.getSource().getBody();
            for (Iterator iter = records.iterator(); iter.hasNext();)
            {
                FeatureChangeRecord r = (FeatureChangeRecord) iter.next();
                if (r.feature.getFeatureID() == SQLRoutinesPackage.PROCEDURE__NAME && r.changed == proc)
                {
                    //rename, the name inside source should have already been updated
                    String name = SQLUtil.quote((String)r.oldValue, "'");
                    String drop = DROP + (quoteIdentifiers? SQLUtil.quote((String)r.oldValue, "\""): (String)r.oldValue);
                    drop = SQLUtil.quote(drop, "'");
                    String newname = SQLUtil.quote((String)r.newValue, "'");

                    script.addCreateRoutineStatements(source);
                    String alter = (RENAME).replaceAll("<<@owner>>", owner).replaceAll("<<@name>>", name).replaceAll("<<@drop>>", drop).replaceAll("<<@newname>>", newname).replaceAll("<<xmode>>", modeLiteral);
                    script.addCreateRoutineStatements(alter);
                    
                    return;
                }
                else if (r.feature.getFeatureID() == SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__TRANSACTION_MODE)
                {
                    xmodeChanged = true;
                }
                else if (r.feature.getFeatureID() == SQLRoutinesPackage.SOURCE__BODY && r.changed == proc.getSource() )
                {
                    sourceChanged = true;
                }
                
            }
            //normal modification
            String name = SQLUtil.quote(proc.getName(), "'");
            String drop = DROP + (quoteIdentifiers? SQLUtil.quote(proc.getName(), "\""): proc.getName());
            drop = SQLUtil.quote(drop, "'");
            String newname = SQLUtil.quote(makeNewName(name), "'");

            String alter = "";
            if (xmodeChanged && !sourceChanged)
            {
                alter = "exec sp_procxmode " + name + ", " + modeLiteral;
                script.addCreateRoutineStatements(alter);
                return;
            }

            if (!sourceChanged)
            {
                return;
            }
            
            alter = (DECLARE + BACKUP).replaceAll("<<@name>>", name).replaceAll("<<@drop>>", drop).replaceAll("<<@newname>>", newname).replaceAll("<<xmode>>", modeLiteral);
            script.addCreateRoutineStatements(alter);
            script.addCreateRoutineStatements(source);
            alter = (DECLARE + CLEANUP).replaceAll("<<@owner>>", owner).replaceAll("<<@name>>", name).replaceAll("<<@drop>>", drop).replaceAll("<<@newname>>", newname).replaceAll("<<xmode>>", modeLiteral);
            script.addCreateRoutineStatements(alter);
            
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

    /**
     * This is a crude implementation of GUID generator. the new name is generated using letter 'T', localhost's ip
     * address and the current timestamp plus three digit of random number, with a maximum total length of 27. There're
     * rare chances of name conflict. But even if that does happen, user can always save it again.
     */ 
    static String makeNewName(String oldName)
    {
        if (_localhost == 0)
        {
            try
            {
                _localhost = Math.abs(InetAddress.getLocalHost().hashCode());
            }
            catch (Exception e)
            {
                JDBCASEPlugin.getDefault().log(e);
            }
        }
        String timeStamp = String.valueOf(Math.abs(System.currentTimeMillis()));
        int random = Math.abs(new Random().nextInt() % 1000);

        return ("T" + _localhost + timeStamp + random);
    }

    
}
