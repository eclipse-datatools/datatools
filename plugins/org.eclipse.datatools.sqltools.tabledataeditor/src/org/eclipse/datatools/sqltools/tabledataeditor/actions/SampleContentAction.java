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
package org.eclipse.datatools.sqltools.tabledataeditor.actions;

import java.sql.Connection;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.popup.AbstractAction;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.icons.ImageDescription;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.DerivedTable;
import org.eclipse.datatools.sqltools.internal.tabledataeditor.query.execute.QueryOutputHelper;
import org.eclipse.datatools.sqltools.internal.tabledataeditor.util.ResourceLoader;
import org.eclipse.datatools.sqltools.internal.tabledataeditor.IExternalRunQuery;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public class SampleContentAction extends AbstractAction 
{
    private static final String TEXT = ResourceLoader.INSTANCE.queryString("DATATOOLS.SERVER.UI.EXPLORER.DATA.SAMPCONTENTS");//$NON-NLS-1$

    private final String SQ = "'"; //$NON-NLS-1$ //$NON-NLS-2$
    private final String SQ2 = "`"; //$NON-NLS-1$ //$NON-NLS-2$
    private final String DQ = "\""; //$NON-NLS-1$ //$NON-NLS-2$
    private final String SPACE = " "; //$NON-NLS-1$ //$NON-NLS-2$
    private String quote = ""; //$NON-NLS-1$ //$NON-NLS-2$
    private static final String EXTERNAL_RUN_QUERY = 
    	"org.eclipse.datatools.sqltools.tabledataeditor.externalRunQuery"; //$NON-NLS-1$ //$NON-NLS-2$
    private static final String EXTERNAL_RUN_QUERY_VENDOR = "vendor"; //$NON-NLS-1$ //$NON-NLS-2$
    private static final String EXTERNAL_RUN_QUERY_CLASS = "class"; //$NON-NLS-1$ //$NON-NLS-2$

    private String wrapName(String name)
    {
        if (this.quote.equals(SQ) || this.quote.equals(SQ2))
        {
            return this.quote + doubleStringDelim(name, quote) + this.quote;
        }
        else if (SPACE.equals(this.quote))
        {
            return doubleStringDelim(name, SPACE);
        }
        else
            return DQ + doubleStringDelim(name, DQ) + DQ; //$NON-NLS-1$ //$NON-NLS-2$
    }

    private String getFullyQualifiedName(Table table)
    {
        // return this.wrapName(table.getSchema().getName()) + "." + this.wrapName(table.getName()); //$NON-NLS-1$
        
        Database db = table.getSchema().getCatalog() != null ?
            table.getSchema().getCatalog().getDatabase() :
            table.getSchema().getDatabase();

        RDBCorePlugin plugin = RDBCorePlugin.getDefault();

        DatabaseDefinition dbDefinition = 
            plugin.getDatabaseDefinitionRegistry().getDefinition(db);

       if (dbDefinition.supportsSchema() && (table instanceof DerivedTable ||
    		   table instanceof BaseTable)) {
            return this.wrapName(table.getSchema().getName())
                    + "." + this.wrapName(table.getName()); //$NON-NLS-1$
        } else {
            return this.wrapName(table.getName()); //$NON-NLS-1$
        }

    }

    private Database getDatabase (Schema schema)
    {
        return schema.getCatalog() == null ? schema.getDatabase() : schema.getCatalog().getDatabase();
    }
    
    public void selectionChanged(SelectionChangedEvent event)
    {
        super.selectionChanged(event);

        if (event.getSelection() instanceof IStructuredSelection
                && ((IStructuredSelection) event.getSelection()).getFirstElement() instanceof EObject)
        {
            EObject o = (EObject) ((IStructuredSelection) event.getSelection()).getFirstElement();
            ContainmentService containmentService = RDBCorePlugin.getDefault().getContainmentService();
            String groupID = containmentService.getGroupId(o);
            setEnabled((groupID != null) && (groupID.startsWith(GroupID.CORE_PREFIX)));
        }
    }

    public void initialize()
    {
        ImageDescriptor descriptor = ImageDescription.getSampleContent();
        initializeAction(descriptor, descriptor, TEXT, TEXT);
    }
    
    public void run()
    {
        this.quote = DQ;
        Connection connection = null;

        if (!this.event.getSelection().isEmpty())
        {
            Iterator iter = ((IStructuredSelection) event.getSelection()).iterator();
            String selectString = ""; //$NON-NLS-1$
            /*
             * int i=0; while(iter.hasNext()) { Object obj1 =
             * (Object)iter.next(); if (obj1 instanceof ITableViewer) { Object
             * obj2 = ((ITableViewer)obj1).getElement(); if (obj2 instanceof
             * Table) { if (connection == null) connection =
             * ((ICatalogObject)obj2).getConnection(); selectString +=
             * ((Table)obj2).getSchema().getName() + "." +
             * ((Table)obj2).getName(); if (i != (this.selection.size()-1)) {
             * selectString += ","; } } } i++; }
             */
            Object selectedObj = iter.next();
            Database database = null;
            
            if (selectedObj instanceof Table)
            {
                database = getDatabase(((Table)selectedObj).getSchema());
                if (connection == null)
                {
                    connection = ((ICatalogObject) selectedObj).getConnection();
                }
                // Get the identifier quote string from JDBC connection
                // because databaseDefinition.getIdentifierQuoteString() always
                // return \"
                try
                {
                    this.quote = connection.getMetaData().getIdentifierQuoteString();
                }
                catch (Exception ex)
                {
                    // ignore
                }

                selectString += "SELECT * FROM " + this.getFullyQualifiedName((Table) selectedObj); //$NON-NLS-1$
            }
            else if (selectedObj instanceof Column)
            {
                if (connection == null)
                {
                    connection = ((ICatalogObject) selectedObj).getConnection();
                }
                // BZ 206601 drigby 5th Dec 2008
                // Get the identifier quote string from JDBC connection
                // because databaseDefinition.getIdentifierQuoteString() always
                // return \"
                try
                {
                   this.quote = connection.getMetaData().getIdentifierQuoteString();
                }
                catch (Exception ex)
                {
                    // ignore
                }
                final Column column = (Column) selectedObj;
                final String columnName = column.getName();

                database = getDatabase(column.getTable().getSchema());
                final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry()
                        .getDefinition(database);
                DataType datatype = column.getDataType();
                if (datatype instanceof DistinctUserDefinedType)
                    datatype = ((DistinctUserDefinedType) datatype).getPredefinedRepresentation();
                final PredefinedDataTypeDefinition datatypeDefinition = databaseDefinition
                        .getPredefinedDataTypeDefinition(datatype.getName());

                if (datatypeDefinition.isOrderingSupported() && datatypeDefinition.isGroupingSupported())
                {
                    selectString += "SELECT DISTINCT " + this.wrapName(columnName) + " , COUNT(*) AS OCCURRENCE"; //$NON-NLS-1$ //$NON-NLS-2$
                    selectString += " FROM " + this.getFullyQualifiedName(column.getTable()); //$NON-NLS-1$
                    selectString += " GROUP BY " + this.wrapName(columnName); //$NON-NLS-1$
                    selectString += " ORDER BY " + this.wrapName(columnName); //$NON-NLS-1$
                }
                else
                {
                    selectString += "SELECT " + this.wrapName(columnName); //$NON-NLS-1$
                    selectString += " FROM " + this.getFullyQualifiedName(column.getTable()); //$NON-NLS-1$
                }
            }
            
            String profileName = DatabaseConnectionRegistry.getConnectionForDatabase(database).getName();
            // check for extensions, if none do the old way
            final IExternalRunQuery externalRun = (IExternalRunQuery)getExternalRunQuery(database);
            if (externalRun != null)
            {
            	externalRun.init(selectString, connection, profileName, database, 
            			ResourceLoader.INSTANCE.queryString("_UI_SAMPLE_CONTENTS_OUTPUTVIEW_TITLE"));
            	externalRun.runQuery();
            }
            else
            {
            	QueryOutputHelper queryHelper = new QueryOutputHelper(selectString, connection, profileName, database.getName());
                queryHelper.setObjectName(ResourceLoader.INSTANCE.queryString("_UI_SAMPLE_CONTENTS_OUTPUTVIEW_TITLE")); //$NON-NLS-1$
                queryHelper.runQuery();
            }            
        }
    }
    
    /**
     * Gets the extension which provides the external run query
     * @param database the Database associated with the query
     * @return the object created from the extension, or null if none exists
     */
    private Object getExternalRunQuery(Database database)
    {
    	Object externalExecutable = null;
    	if (database != null)
    	{
    		String thisVendor = database.getVendor();
    		try
    		{
    			IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
    			IExtensionPoint extensionPoint = 
		        	extensionRegistry.getExtensionPoint(SampleContentAction.EXTERNAL_RUN_QUERY);
    			IExtension [] extensions = extensionPoint.getExtensions();
    			for (int numExt=0;numExt<extensions.length;numExt++)
		        {
		        	IExtension ext = extensions[numExt];
		        	IConfigurationElement [] configElements = ext.getConfigurationElements();
		        	for (int config=0;config<configElements.length;config++)
		        	{
		        		String extensionVendor = 
		        			configElements[config].getAttribute(SampleContentAction.EXTERNAL_RUN_QUERY_VENDOR);
		        		if (thisVendor.equalsIgnoreCase(extensionVendor))
		        		{
		        			externalExecutable = 
		        				configElements[config].createExecutableExtension(SampleContentAction.EXTERNAL_RUN_QUERY_CLASS);
		        			break;
		        		}
		        	}
		        }
    		}
    		catch (CoreException ex)
			{
				// problem with creating executable, return null
			}
    	}
    	return externalExecutable;
    }

    public static String doubleStringDelim(String s, String delim)
    {
        if (delim == null || delim.length() == 0)
            return s;

        int i = s.indexOf(delim);
        if (i != -1)
        {
            StringBuffer sb = new StringBuffer(s);
            int j;
            for (; i != -1; i = sb.toString().indexOf(delim, j))
            {
                sb = sb.insert(i, delim);
                j = i + 2 * delim.length();
            }
            return sb.toString();
        }
        else
            return s;
    }
}
