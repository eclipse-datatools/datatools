/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.ui.load;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.sqltools.data.internal.core.DataCorePlugin;
import org.eclipse.datatools.sqltools.data.internal.core.load.LoadData;
import org.eclipse.datatools.sqltools.data.internal.ui.OutputItemAdapter;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsViewAPI;
import org.eclipse.datatools.connectivity.sqm.core.connection.DatabaseConnectionRegistry;
//import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.jface.wizard.Wizard;


public class LoadDataWizard extends Wizard
{

    protected Table table;
    
    protected LoadDataWizardPage page;
    
    public LoadDataWizard(Table table)
    {
        this.table = table;
        setWindowTitle(Messages.getString("LoadDataWizard.LoadData")); //$NON-NLS-1$
    }
    
    public void addPages() {
        page = new LoadDataWizardPage("org.eclipse.wst.rdb.data.ui.loadData"); //$NON-NLS-1$
        addPage(page);
    }

    public boolean performFinish()
    {
        page.saveSettings();
        
        String colDelim = page.getColumnDelimiter();        
        String stringDelim = page.getStringDelimiter();
        String filePath = page.getFilePath();
        boolean replace = page.getReplace();
//        String rowDelim = page.getLocale();
        
        final LoadData load = new LoadData(table, filePath);
        load.setDelims(colDelim, stringDelim);
        load.setReplace(replace);
        
        final OperationCommand item = initDbOutputItem();
        
        Job job = new Job(Messages.getString("LoadDataWizard.DataLoading")) { //$NON-NLS-1$
            protected IStatus run(IProgressMonitor monitor) {
                int ret = load.doLoad(new OutputItemAdapter(item));                
                ResultsViewAPI.getInstance().updateStatus(item, ret);
                return Status.OK_STATUS;
               }
            };
        job.setPriority(Job.SHORT);
        job.schedule();
        
        return true;        
    }
    
    protected void showReport()
    {
        
    }
    
    protected OperationCommand initDbOutputItem()
    {      
    	String qualifiedTableName = DataCorePlugin.getQualifiedTableName(table);
        
        int actionType = OperationCommand.ACTION_LOAD;
        String displayStr = qualifiedTableName;
        String consumerName = null;        
        Database database = table.getSchema().getCatalog().getDatabase();
        ConnectionInfo connInfo = DatabaseConnectionRegistry.getConnectionForDatabase(database);
        String connectionProfieName = 
        	((ConnectionInfoImpl)connInfo).getConnectionProfile().getName();         
        String databaseName = database.getName();        
        
        OperationCommand oCommand = new OperationCommand(actionType, displayStr,
        		consumerName, connectionProfieName, databaseName);     
        
        return oCommand;
    }
    
    protected String getFullyQualifiedName() {
    	return "\"" + table.getSchema().getName() + "\".\"" + table.getName() + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    
}
