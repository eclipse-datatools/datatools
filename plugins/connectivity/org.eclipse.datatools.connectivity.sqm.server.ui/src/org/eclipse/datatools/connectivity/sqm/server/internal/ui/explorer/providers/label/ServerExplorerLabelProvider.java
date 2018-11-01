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
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.label;

import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.eclipse.ui.navigator.IExtensionStateModel;


/**
 * @author ljulien
 */
public class ServerExplorerLabelProvider extends LabelProvider implements ICommonLabelProvider
{
	private static final ResourceLoader resourceLoader = ResourceLoader.INSTANCE;
	private static final IDataToolsUIServiceManager imageService = IDataToolsUIServiceManager.INSTANCE;
	private static final String EXTERNAL_DEPENDENCY = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.EXTERNAL_DEPENDENCY"); //$NON-NLS-1$

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
		else if (element instanceof Dependency)
		{
		    return EXTERNAL_DEPENDENCY;
		}
		else if (element instanceof ENamedElement)
		{
		    return ((ENamedElement)element).getName();
		}
		else 
		{
			return imageService.getLabelService(element).getName();
		}
    }

	public void initialize(String aViewerId) {
		// TODO Auto-generated method stub
		
	}

	public String getDescription(Object selection) {
        return IDataToolsUIServiceManager.INSTANCE.getLabelService(selection).getName();
	}

	public void init(IExtensionStateModel aStateModel, ITreeContentProvider aContentProvider) {
		// TODO Auto-generated method stub
		
	}

	public void restoreState(IMemento aMemento) {
		// TODO Auto-generated method stub
		
	}

	public void saveState(IMemento aMemento) {
		// TODO Auto-generated method stub
		
	}

	public void init(ICommonContentExtensionSite aConfig) {
		// TODO Auto-generated method stub
		
	}
}
