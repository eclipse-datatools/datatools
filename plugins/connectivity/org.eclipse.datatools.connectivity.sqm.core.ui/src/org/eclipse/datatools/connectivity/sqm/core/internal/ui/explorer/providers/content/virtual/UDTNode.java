/*******************************************************************************
 * Copyright (c) 2001, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IFilterNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IUDTNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.icons.ImageDescription;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.AddObjectRegistry;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IAddObjectProvider;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * @author ljulien
 */
public class UDTNode extends VirtualNode implements IUDTNode, IFilterNode
{
	protected boolean isStructuredUDTSupported;
	protected boolean isArrayTypeSupported;
	protected boolean isRowTypeSupported;
	protected boolean isDistinctTypeSupported;
	protected ImageDescriptor addObjectDescriptor = null;
	protected String addObjectLabel = null;
	protected EClass addObjectEClass = null;
	
	/**
	 * @param name
	 * @param displayName
	 */
	public UDTNode(String name, String displayName, Object parent)
	{
        super(name, displayName, parent);
        isDistinctTypeSupported = isDistinctTypeSupported();
        isStructuredUDTSupported = isStructuredUserDefinedTypeSupported();
        isArrayTypeSupported = isArrayTypeSupported();
        isRowTypeSupported = isRowTypeSupported();
        IAddObjectProvider provider = AddObjectRegistry.INSTANCE.getProvider(this);
        if (provider != null) {
            setAddedObjectValues(provider);
        }
	}

	public String getGroupID ()
    {
        return GroupID.USER_DEFINED_TYPE;
    }

    public String getFilterName() {
		return getFilterName(ConnectionFilter.USER_DEFINED_TYPE_FILTER);
	}
    
    //@Override
	public ImageDescriptor[] getCreateImageDescriptor() {		
        List imageDescriptors = new ArrayList();
        if (isDistinctTypeSupported) {
            imageDescriptors.add(ImageDescription.getUDTDescriptor());  
        }       
        if (isStructuredUDTSupported)
        {
            imageDescriptors.add(ImageDescription.getUDTDescriptor());
        }
        if (isArrayTypeSupported)
        {
            imageDescriptors.add(ImageDescription.getUDTDescriptor());
        }
        if (isRowTypeSupported)
        {
            imageDescriptors.add(ImageDescription.getUDTDescriptor());
        }
        if (addObjectDescriptor != null) {
            imageDescriptors.add(addObjectDescriptor);
        }
        return (ImageDescriptor[])imageDescriptors.toArray(new ImageDescriptor[0]);
        
        /*return isStructuredUDTSupported ? new ImageDescriptor[] { 
                ImageDescription.getUDTDescriptor(),
                ImageDescription.getUDTDescriptor(),
                ImageDescription.getUDTDescriptor(),
                ImageDescription.getUDTDescriptor()
        }
        : new ImageDescriptor[] { ImageDescription.getUDTDescriptor() }; */
	}

	//@Override
	public String[] getCreateLabel() {
	      List labels = new ArrayList();
	        if (this.isDistinctTypeSupported) {
	            labels.add(ResourceLoader.getResourceLoader().queryString("SCHEMA_MANAGEMENT_CREATE_DISTINCT_TYPE"));   
	        }       
	        if (isStructuredUDTSupported)
	        {
	            labels.add(ResourceLoader.getResourceLoader().queryString("SCHEMA_MANAGEMENT_CREATE_STRUCTURED_TYPE"));
	        }
	        if (isArrayTypeSupported)
	        {
	            labels.add(ResourceLoader.getResourceLoader().queryString("SCHEMA_MANAGEMENT_CREATE_ARRAY_TYPE"));
	        }
	        if (isRowTypeSupported)
	        {
	            labels.add(ResourceLoader.getResourceLoader().queryString("SCHEMA_MANAGEMENT_CREATE_ROW_TYPE"));
	        }
	        if (addObjectLabel != null) {
	            labels.add(addObjectLabel);
	        }
	        return (String[])labels.toArray(new String[0]);
	        
	    /*  return isStructuredUDTSupported ? new String[] {
	                ResourceLoader.getResourceLoader().queryString("SCHEMA_MANAGEMENT_CREATE_DISTINCT_TYPE"),
	                ResourceLoader.getResourceLoader().queryString("SCHEMA_MANAGEMENT_CREATE_STRUCTURED_TYPE"),
	                ResourceLoader.getResourceLoader().queryString("SCHEMA_MANAGEMENT_CREATE_ARRAY_TYPE"),
	                ResourceLoader.getResourceLoader().queryString("SCHEMA_MANAGEMENT_CREATE_ROW_TYPE")
	        }
	        : new String[] { ResourceLoader.getResourceLoader().queryString("SCHEMA_MANAGEMENT_CREATE_DISTINCT_TYPE") }; */
	}

	//@Override
	public EClass[] getCreateType() {
	      List types = new ArrayList();
	        if (isDistinctTypeSupported) {
	            types.add(SQLDataTypesPackage.eINSTANCE.getDistinctUserDefinedType());  
	        }       
	        if (isStructuredUDTSupported)
	        {
	            types.add(SQLDataTypesPackage.eINSTANCE.getStructuredUserDefinedType());
	        }
	        if (isArrayTypeSupported)
	        {
	            types.add(SQLDataTypesPackage.eINSTANCE.getArrayDataType());
	        }
	        if (isRowTypeSupported)
	        {
	            types.add(SQLDataTypesPackage.eINSTANCE.getRowDataType());
	        }
	        if (addObjectEClass != null) {
	            types.add(addObjectEClass);
	        }
	        return ((EClass[])types.toArray(new EClass[0]));
	        
	    /*  return isStructuredUDTSupported ? new EClass[] {
	                SQLDataTypesPackage.eINSTANCE.getDistinctUserDefinedType(),
	                SQLDataTypesPackage.eINSTANCE.getStructuredUserDefinedType()
	        }
	        : new EClass[] { SQLDataTypesPackage.eINSTANCE.getDistinctUserDefinedType() };
	    */
	}

	//@Override
	public boolean shouldDisplayCreate() {
		return true;
	}

	//@Override
	public boolean shouldDisplayAdd() {
		return false;
	}

	protected boolean isDistinctTypeSupported() {
	    boolean isSupported = false;
	    Object parent = getParent();
	    if (parent != null && parent instanceof Schema)
	    {
	        Database db = ((Schema)parent).getDatabase();
	        if (db != null)
	        {
	            isSupported = isCreateDistinctTypeSupported(RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(db));
	        }
	    }
	        
	    return isSupported;
	}

	protected boolean isStructuredUserDefinedTypeSupported() {
		boolean isSupported = false;
		// check with vendor doc for structured UDT support
		Object parent = getParent();
		if (parent != null && parent instanceof Schema) {
			Database db = ((Schema)parent).getDatabase();
			if (db != null) {
				isSupported = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(db).supportsStructuredUserDefinedType();	
			}			
		}
		return isSupported;
	}

	   /**
     * Gets whether or not Array data type is supported
     * @return true if array type is supported, false if not
     */
    protected boolean isArrayTypeSupported()
    {       
        boolean isSupported = false;
        Object parent = getParent();
        if (parent != null && parent instanceof Schema)
        {
            Database db = ((Schema)parent).getDatabase();
            if (db != null)
            {
                isSupported = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(db).supportsArrayDataType();
            }
        }
        return isSupported; 
    }
    
    /**
     * Gets whether or not Row data type is supported
     * @return true if Row data type is supported, false if not
     */
    protected boolean isRowTypeSupported()
    {       
        boolean isSupported = false;
        Object parent = getParent();
        if (parent != null && parent instanceof Schema)
        {
            Database db = ((Schema)parent).getDatabase();
            if (db != null)
            {
                isSupported = isCreateRowTypeSupported(RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(db));
                if (isSupported) {
                    isSupported = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(db).supportsRowDataType();   
                }               
            }
        }
        return isSupported; 
    }
    
    protected void setAddedObjectValues(IAddObjectProvider provider) {
        Object objParent = getParent();
        if (objParent != null) {
            addObjectDescriptor = provider.getCreateImageDescriptor(objParent);
            addObjectLabel = provider.getCreateLabel(objParent);
            addObjectEClass = provider.getCreateType(objParent);
        }
    }

}
