/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.ui.load;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.data.internal.core.DataCorePlugin;
import org.eclipse.datatools.sqltools.data.internal.core.load.LoadData;
import org.eclipse.datatools.sqltools.data.internal.ui.OutputItemAdapter;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsViewAPI;
import org.eclipse.jface.wizard.Wizard;


public class LoadDataWizard extends Wizard
{

    protected Table table;
    
    protected LoadDataWizardPage page;
    
    /** extension-point id for external load support */
    private String EXTERNAL_LOAD_EXT_POINT = 
    	"org.eclipse.datatools.sqltools.data.ui.externalTableDataLoad"; //$NON-NLS-1$
    private String EXTERNAL_LOAD_EXT_POINT_VENDOR = "vendor"; //$NON-NLS-1$
    private String EXTERNAL_LOAD_EXT_POINT_VERSION = "version"; //$NON-NLS-1$
    private String EXTERNAL_LOAD_EXT_POINT_CLASS = "class"; //$NON-NLS-1$
    
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
        
        final OperationCommand item = initDbOutputItem();
        
        // check for extensions, if none do the old way
        Connection conn = ((ICatalogObject)table).getConnection();
        final IExternalLoad externalLoad = (IExternalLoad)getExternalLoad(conn);
        if (externalLoad != null && externalLoad.isUseExternalLoad())
        {
            externalLoad.setDelimiters(colDelim, stringDelim);
        	externalLoad.setFilePath(filePath);
        	externalLoad.setReplace(replace);
        	externalLoad.setTable(table);
        	Job job = new Job(Messages.getString("LoadDataWizard.DataLoading")) { //$NON-NLS-1$
        		protected IStatus run(IProgressMonitor monitor) {
        			int ret = externalLoad.doLoad(new OutputItemAdapter(item));
        			ResultsViewAPI.getInstance().updateStatus(item, ret);
                    return Status.OK_STATUS;
        		}
        	};
        	job.setPriority(Job.SHORT);
            job.schedule();
        }
        else
        {
        	final LoadData load = new LoadData(table, filePath);
            load.setDelims(colDelim, stringDelim);
            load.setReplace(replace);
            
            Job job = new Job(Messages.getString("LoadDataWizard.DataLoading")) { //$NON-NLS-1$
                protected IStatus run(IProgressMonitor monitor) {
                    int ret = load.doLoad(new OutputItemAdapter(item));                
                    ResultsViewAPI.getInstance().updateStatus(item, ret);
                    return Status.OK_STATUS;
                   }
                };
            job.setPriority(Job.SHORT);
            job.schedule();
        }
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
        Database database = table.getSchema().getCatalog() != null ?
       		 table.getSchema().getCatalog().getDatabase():
       		 table.getSchema().getDatabase();        
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
    
    /**
     * Gets the extension which provides the load function
     * @param conn the Connection associated with the table
     * @return the object created from the extension, or null if none exists
     */
    private Object getExternalLoad(Connection conn)
    {
    	Object externalExecutable = null;
    	Database db = table.getSchema().getCatalog() != null ?
				table.getSchema().getCatalog().getDatabase() :
				table.getSchema().getDatabase();
		String thisVendor = db.getVendor();
		if (conn != null)
		{
			try
			{
				int thisMajorVersion = conn.getMetaData().getDatabaseMajorVersion();
				int thisMinorVersion = conn.getMetaData().getDatabaseMinorVersion();				
				
		    	IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		        IExtensionPoint extensionPoint = 
		        	extensionRegistry.getExtensionPoint(EXTERNAL_LOAD_EXT_POINT);
		        IExtension [] extensions = extensionPoint.getExtensions();
		        for (int numExt=0;numExt<extensions.length;numExt++)
		        {
		        	IExtension ext = extensions[numExt];
		        	IConfigurationElement [] configElements = ext.getConfigurationElements();
		        	for (int config=0;config<configElements.length;config++)
		        	{
		        		String extensionVendor = 
		        			configElements[config].getAttribute(EXTERNAL_LOAD_EXT_POINT_VENDOR);
		        		if (thisVendor.equalsIgnoreCase(extensionVendor))
		        		{
		        			// vendor matched so far, check for version is at least this
		        			String extensionVersion = 
		        				configElements[config].getAttribute(EXTERNAL_LOAD_EXT_POINT_VERSION);
		        			float ver = Float.parseFloat(extensionVersion);
		        			if (thisMajorVersion + (thisMinorVersion * .1) >= ver)
		        			{
		        				// use the external loader
		        				externalExecutable = 
		        					configElements[config].createExecutableExtension(EXTERNAL_LOAD_EXT_POINT_CLASS);		        				
		        			}
		        		}
		        	}
		        }
			}
			catch (SQLException ex)
			{
				// ignore since null will be returned
			}
			catch (NumberFormatException ex)
			{
				// something wrong, return null
			}
			catch (CoreException ex)
			{
				// problem with creating executable, return null
			}
		}
		return externalExecutable;		
    }
    
}
