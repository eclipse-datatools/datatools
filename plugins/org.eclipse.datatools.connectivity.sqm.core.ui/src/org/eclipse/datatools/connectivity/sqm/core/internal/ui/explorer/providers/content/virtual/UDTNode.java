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
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IFilterNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IUDTNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.icons.ImageDescription;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
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
	
	/**
	 * @param name
	 * @param displayName
	 */
	public UDTNode(String name, String displayName, Object parent)
	{
		super(name, displayName, parent);
		isStructuredUDTSupported = isStructuredUserDefinedTypeSupported();
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
		return isStructuredUDTSupported ? new ImageDescriptor[] { 
				ImageDescription.getUDTDescriptor(),
				ImageDescription.getUDTDescriptor()
		}
		: new ImageDescriptor[] { ImageDescription.getUDTDescriptor() };
	}

	//@Override
	public String[] getCreateLabel() {
		return isStructuredUDTSupported ? new String[] {
				ResourceLoader.getResourceLoader().queryString("SCHEMA_MANAGEMENT_CREATE_DISTINCT_TYPE"),
				ResourceLoader.getResourceLoader().queryString("SCHEMA_MANAGEMENT_CREATE_STRUCTURED_TYPE")
		}
		: new String[] { ResourceLoader.getResourceLoader().queryString("SCHEMA_MANAGEMENT_CREATE_DISTINCT_TYPE") };
	}

	//@Override
	public EClass[] getCreateType() {
		return isStructuredUDTSupported ? new EClass[] {
				SQLDataTypesPackage.eINSTANCE.getDistinctUserDefinedType(),
				SQLDataTypesPackage.eINSTANCE.getStructuredUserDefinedType()
		}
		: new EClass[] { SQLDataTypesPackage.eINSTANCE.getDistinctUserDefinedType() };
	}

	//@Override
	public boolean shouldDisplayCreate() {
		return true;
	}

	//@Override
	public boolean shouldDisplayAdd() {
		return false;
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

}
