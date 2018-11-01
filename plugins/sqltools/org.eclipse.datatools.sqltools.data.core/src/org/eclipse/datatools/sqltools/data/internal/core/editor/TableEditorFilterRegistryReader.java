/*******************************************************************************
 * Copyright (c) 2001, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.data.internal.core.editor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import java.sql.ResultSet;


/**
 * Registry reader for Table Editor result set filters
 * @author Quy On
 */
public class TableEditorFilterRegistryReader
{
	public static final String TABLE_EDITOR_RESULT_FILTER = 
    	"org.eclipse.datatools.sqltools.data.core.tableEditorResultFilter"; //$NON-NLS-1$ 
	public static final String TABLE_EDITOR_RESULT_FILTER_VENDOR =
		"vendor"; //$NON-NLS-1$
	public static final String TABLE_EDITOR_RESULT_FILTER_CLASS = 
		"class"; //$NON-NLS-1$
	private static TableEditorFilterRegistryReader myInstance;
	private boolean filterCanceled;
	private ITableEditorResultFilter filterClass;
	
	/**
	 * Creates an instance of TableEditorFilterRegistryReader
	 */
	private TableEditorFilterRegistryReader()
	{
		super();
	}
	
	/**
	 * Gets an instance of TableEditorFilterRegistryReader
	 * @return an instance of TableEditorFilterRegistryReader
	 */
	public synchronized static TableEditorFilterRegistryReader getInstance()
	{
		if (myInstance == null)
		{
			myInstance = new TableEditorFilterRegistryReader();			
		}
		return myInstance;
	}
	
	/**
	 * Gets the vendor attribute as set by the user
	 * @return the vendor attribute as set by the user, or null if omitted
	 */
	public String getVendor()
	{
		String vendor = null;
		
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = 
	        	extensionRegistry.getExtensionPoint(TABLE_EDITOR_RESULT_FILTER);
		if (extensionPoint != null)
		{
			IExtension [] extensions = extensionPoint.getExtensions();
			if (extensions.length > 0)
			{
				IExtension ext = extensions[0];
				IConfigurationElement [] configElements = ext.getConfigurationElements();
				if (configElements.length > 0)
				{
					vendor = 
						configElements[0].getAttribute(TABLE_EDITOR_RESULT_FILTER_VENDOR);
					
				}
			}
		}
		return vendor;
	}
	
	/**
	 * Gets the executable for external Table editor result filter.
	 * @return the executable for the table editor result filter.  The previous cached
	 * filter class is returned if there is one.  If not then a new class will be created.
	 */
	public ITableEditorResultFilter getTableEditorResultFilter()
	{
		if (filterClass == null)
		{
			return createTableEditorResultFilterExecutable();
		}
		return filterClass;
	}
	
	/**
	 * Creates a new instance of filter executable.  The new executable will override the
	 * previous one if it existed.
	 * @return a newly created executable for the editor result filter, or null if
	 * problem occurs during the creation. The return class implements ITableEditorResultFilter.  
	 */
	public ITableEditorResultFilter createTableEditorResultFilterExecutable()
	{
		filterClass = null;
		try
		{
			IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
			IExtensionPoint extensionPoint = 
		        	extensionRegistry.getExtensionPoint(TABLE_EDITOR_RESULT_FILTER);
			if (extensionPoint != null)
			{
				IExtension [] extensions = extensionPoint.getExtensions();
				if (extensions.length > 0)
				{
					IExtension ext = extensions[0];
					IConfigurationElement [] configElements = ext.getConfigurationElements();
					if (configElements.length > 0)
					{
						filterClass = (ITableEditorResultFilter)
							configElements[0].createExecutableExtension(TABLE_EDITOR_RESULT_FILTER_CLASS);
					}
				}
			}
		}
		catch (CoreException ex)
		{
			// problem with creating executable just return null			
		}
		return filterClass;
	}
	
	/**
	 * Determines whether or not a table belongs to a vendor which matches the
	 * vendor specified in the extension.
	 * If user omits the vendor element then this method will return true
	 * @param aTable the table to check against the vendor specified in the extension
	 * @return true if vendor of the table matches the vendor specified in the extension
	 * or if vendor is omitted in the extension.  False will be returned if user specifies
	 * a vendor which does not match the vendor of the table.
	 */
	public boolean isMatchingVendor(Table aTable)
	{
		boolean matching = false;
		if (aTable != null)
		{
			String vendor = getVendor();
			if (vendor == null || vendor.trim().equals(""))
			{
				matching = true;
			}
			else
			{
				String vendorFromTable = aTable.getSchema().getDatabase().getVendor();
				if (vendor.equalsIgnoreCase(vendorFromTable))
				{
					matching = true;
				}
			}
		}
		return matching;
	}
	
	/**
	 * Determines whether or not there are any extensions to this extension point
	 * @return true if there is an extension to tableEditorResultFilter extension-point,
	 * false if not.
	 */
	public boolean isExtenionFound()
	{
		boolean found = false;
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = 
	        	extensionRegistry.getExtensionPoint(TABLE_EDITOR_RESULT_FILTER);
		if (extensionPoint != null)
		{
			IExtension [] extensions = extensionPoint.getExtensions();
			if (extensions.length > 0)
			{
				found = true;
			}
		}
		return found;
	}
		
	/**
	 * Sets whether or not user cancels the filter dialog
	 * @param cancel whether or not user cancels the filter action
	 */
	public void setFilterCanceled(boolean cancel)
	{
		filterCanceled = cancel;
	}
	
	/**
	 * Gets whether or not user cancels the filter dialog
	 * @return true if use cancels the filter dialog, false if not.  By default,
	 * this value is false
	 */
	public boolean isFilterCanceled()
	{
		return filterCanceled;
	}	
}
