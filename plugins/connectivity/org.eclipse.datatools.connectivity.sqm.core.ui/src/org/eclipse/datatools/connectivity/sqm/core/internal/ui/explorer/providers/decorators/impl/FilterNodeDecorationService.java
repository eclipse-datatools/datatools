/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.impl;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.FilterUtil;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IFilterNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.IFilterNodeDecorationService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.icons.ImageDescription;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.widgets.Display;

/**
 * @author ljulien
 */
public class FilterNodeDecorationService extends AbstractDecorationService implements ILightweightLabelDecorator, IFilterNodeDecorationService
{
    private static final String FILTERED = " " + ResourceLoader.getResourceLoader().queryString("DATATOOLS.SERVER.UI.EXPLORER.FILTER_DECORATION");  //$NON-NLS-1$//$NON-NLS-2$
    
    private boolean hasFiltering (IFilterNode filterNode)
    {
        return FilterUtil.hasFilter(filterNode);
    }
    
    /**
     * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object, org.eclipse.jface.viewers.IDecoration)
     */
    public void decorate(Object element, IDecoration decoration)
    {
        IFilterNode filterNode = (IFilterNode) element;
        if (hasFiltering(filterNode))
        {
            decoration.addOverlay(ImageDescription.getFilterDecorationDescriptor(), IDecoration.BOTTOM_RIGHT);
            decoration.addSuffix(FILTERED);
        }
    }

    /**
     * @see org.eclipse.wst.rdb.core.internal.ui.explorer.providers.decorators.IDecorationService#refreshDecoration(org.eclipse.core.runtime.IAdaptable[])
     */
    public void refreshDecoration(Object[] elements)
    {
        fireLabelChangedEvent(new LabelProviderChangedEvent(this, elements));
    }

    /**
     * Will notify the Model Explorer to redecorate
     * @param event
     */
    protected void fireLabelChangedEvent(final LabelProviderChangedEvent event)
    {
        Display.getDefault().asyncExec(new Runnable()
        {
            public void run()
            {
                fireLabelProviderChanged(event);
            }
        });
    }
}
