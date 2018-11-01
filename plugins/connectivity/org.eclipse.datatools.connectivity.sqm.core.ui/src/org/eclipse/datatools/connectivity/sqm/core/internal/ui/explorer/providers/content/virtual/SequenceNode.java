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
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IFilterNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.ISequenceNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.icons.ImageDescription;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * @author ljulien
 */
public class SequenceNode extends VirtualNode implements ISequenceNode, IFilterNode
{
	/**
	 * @param name
	 * @param displayName
	 */
	public SequenceNode(String name, String displayName, Object parent)
	{
		super(name, displayName, parent);
	}

    public String getGroupID ()
    {
        return GroupID.SEQUENCE;
    }

    public String getFilterName() {
    	return getFilterName(ConnectionFilter.SEQUENCE_FILTER);
	}
    
	//@Override
	public ImageDescriptor[] getCreateImageDescriptor() {
		return new ImageDescriptor[] { 
				ImageDescription.getSequenceDescriptor()
		};
	}

	//@Override
	public String[] getCreateLabel() {
		return new String[] {
				ResourceLoader.getResourceLoader().queryString("SCHEMA_MANAGEMENT_CREATE_SEQUENCE")
		};
	}

	//@Override
	public EClass[] getCreateType() {
		return new EClass[] {
				SQLSchemaPackage.eINSTANCE.getSequence()
		};
	}

	//@Override
	public boolean shouldDisplayCreate() {
		return true;
	}

	//@Override
	public boolean shouldDisplayAdd() {
		return false;
	}

}
