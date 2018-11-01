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

package org.eclipse.datatools.sqltools.data.internal.ui.extract;

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
import org.eclipse.datatools.sqltools.data.internal.core.extract.ExtractData;
import org.eclipse.datatools.sqltools.data.internal.ui.FileFormatWizardPage;
import org.eclipse.datatools.sqltools.data.internal.ui.OutputItemAdapter;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsViewAPI;
import org.eclipse.jface.wizard.Wizard;


public class ExtractDataWizard extends Wizard
{

    protected Table table;
    
    protected FileFormatWizardPage page;
    
    /** extension-point id for external extract support */
    private String EXTERNAL_EXTRACT_EXT_POINT = 
    	"org.eclipse.datatools.sqltools.data.ui.externalTableDataExtract"; //$NON-NLS-1$
    private String EXTERNAL_EXTRACT_EXT_POINT_VENDOR = "vendor"; //$NON-NLS-1$
    private String EXTERNAL_EXTRACT_EXT_POINT_VERSION = "version"; //$NON-NLS-1$
    private String EXTERNAL_EXTRACT_EXT_POINT_CLASS = "class"; //$NON-NLS-1$
    
    public ExtractDataWizard(Table table)
    {
        this.table = table;
        setWindowTitle(Messages.getString("ExtractDataWizard.ExtractData")); //$NON-NLS-1$
    }
    
    public void addPages() {
        page = new ExtractDataWizardPage("org.eclipse.wst.rdb.data.ui.extractData"); //$NON-NLS-1$
        addPage(page);
    }

    public boolean performFinish()
    {    	
    	page.saveSettings();
        
        String colDelim = page.getColumnDelimiter();
        String stringDelim = page.getStringDelimiter();
        String filePath = page.getFilePath();
        
        final OperationCommand item = initDbOutputItem();
        
        // check for extensions, if none do the old way
        Connection conn = ((ICatalogObject)table).getConnection();
        final IExternalExtract externalExtract = (IExternalExtract)getExternalExtract(conn);
        if (externalExtract != null && externalExtract.isUseExternalExtract())
        {
        	externalExtract.setDelimiters(colDelim, stringDelim);
        	externalExtract.setFilePath(filePath);
        	externalExtract.setTable(table);
        	Job job = new Job(Messages.getString("ExtractDataWizard.DataExtraction")) { //$NON-NLS-1$
                 protected IStatus run(IProgressMonitor monitor) {
                	 int ret = externalExtract.doExtract(new OutputItemAdapter(item));
                	 ResultsViewAPI.getInstance().updateStatus(item, ret);
                     return Status.OK_STATUS;
                 }
        	 };
        	 job.setPriority(Job.SHORT);
             job.schedule();
        }
        else
        {
        	final ExtractData extract = new ExtractData(table, filePath);
            extract.setDelims(colDelim, stringDelim);           
            
            Job job = new Job(Messages.getString("ExtractDataWizard.DataExtraction")) { //$NON-NLS-1$
                protected IStatus run(IProgressMonitor monitor) {
                      int ret = extract.doExtract(new OutputItemAdapter(item));                  
                      ResultsViewAPI.getInstance().updateStatus(item, ret);
                      return Status.OK_STATUS;
                   }
                };
            job.setPriority(Job.SHORT);
            job.schedule();
        }
        return true;
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
     * Gets the extension which provides the extract function
     * @param conn the Connection associated with the table
     * @return the object created from the extension, or null if none exists
     */
    private Object getExternalExtract(Connection conn)
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
		        	extensionRegistry.getExtensionPoint(EXTERNAL_EXTRACT_EXT_POINT);
		        IExtension [] extensions = extensionPoint.getExtensions();
		        for (int numExt=0;numExt<extensions.length;numExt++)
		        {
		        	IExtension ext = extensions[numExt];
		        	IConfigurationElement [] configElements = ext.getConfigurationElements();
		        	for (int config=0;config<configElements.length;config++)
		        	{
		        		String extensionVendor = 
		        			configElements[config].getAttribute(EXTERNAL_EXTRACT_EXT_POINT_VENDOR);
		        		if (thisVendor.equalsIgnoreCase(extensionVendor))
		        		{
		        			// vendor matched so far, check for version is at least this
		        			String extensionVersion = 
		        				configElements[config].getAttribute(EXTERNAL_EXTRACT_EXT_POINT_VERSION);
		        			float ver = Float.parseFloat(extensionVersion);
		        			if (thisMajorVersion + (thisMinorVersion * .1) >= ver)
		        			{
		        				// use the external extractor
		        				externalExecutable = 
		        					configElements[config].createExecutableExtension(EXTERNAL_EXTRACT_EXT_POINT_CLASS);
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
