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
package org.eclipse.datatools.connectivity.apache.derby.internal.ui.explorer.providers.label;

import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ServerExplorerlabelProvider extends LabelProvider implements ILabelProvider
{
	private static final IDataToolsUIServiceManager imageService = IDataToolsUIServiceManager.INSTANCE;

	/**
     * @return the Image associated with this element
     */
    public Image getImage(Object element)
    {
        return imageService.getLabelService(element).getIcon();
    }

    /**
     * @return the Text associated with this element
     */
    public String getText(Object element)
    {
		if (element instanceof IVirtualNode)
		{
			return ((IVirtualNode) element).getDisplayName();
		}
		else if (element instanceof ENamedElement)
		{
		    return ((ENamedElement)element).getName();
		}
		else 
		{
		    return super.getText(element);
		}
    }

	public void initialize(String aViewerId) {
		// TODO Auto-generated method stub
		
	}

	public String getDescription(Object selection) {
        return IDataToolsUIServiceManager.INSTANCE.getLabelService(selection).getName();
	}
}
