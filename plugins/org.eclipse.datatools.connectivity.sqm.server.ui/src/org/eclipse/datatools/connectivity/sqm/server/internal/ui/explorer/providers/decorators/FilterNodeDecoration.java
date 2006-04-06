package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.decorators;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.FilterUtil;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IFilterNode;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.icons.ImageDescription;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IFilterNodeDecorationService;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.widgets.Display;

/**
 * @author ljulien
 */
public class FilterNodeDecoration extends LabelProvider implements ILightweightLabelDecorator, IFilterNodeDecorationService
{
    private static final String FILTERED = " " + ResourceLoader.INSTANCE.queryString("DATATOOLS.SERVER.UI.EXPLORER.FILTER_DECORATION");  //$NON-NLS-1$//$NON-NLS-2$
    
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
     * @see org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.IDecorationService#refreshDecoration(org.eclipse.core.runtime.IAdaptable[])
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
