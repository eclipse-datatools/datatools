/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.sql.AbstractSQLEditorTextHover;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.information.IInformationProviderExtension2;
import org.eclipse.ui.IEditorPart;


/**
 * This is a hover manager which chooses the best matched hover.
 * 
 * @author Hui Cao
 */
public class BestMatchHover extends AbstractSQLEditorTextHover implements ITextHover, ITextHoverExtension,
    IInformationProviderExtension2
{

    private List        _fInstantiatedTextHovers = new ArrayList(2);
    private ITextHover  _fBestHover;
    private IEditorPart _fEditor;

    public BestMatchHover(IEditorPart editor)
    {
        _fEditor = editor;
        installTextHovers();
    }

    /*
     * @see AbstractSQLEditorTextHover#setEditor(IEditorPart)
     */
    public void setEditor(IEditorPart editor)
    {
        _fEditor = editor;
        for (Iterator iterator = _fInstantiatedTextHovers.iterator(); iterator.hasNext();)
        {
            ITextHover hover = (ITextHover) iterator.next();
            if (hover instanceof AbstractSQLEditorTextHover)
            {
                ((AbstractSQLEditorTextHover) hover).setEditor(editor);
            }
        }
    }

    /**
     * Installs all text hovers.
     */
    private void installTextHovers()
    {

        // initialize lists - indicates that the initialization happened

        // populate list
        _fInstantiatedTextHovers.add(new SQLAnnotationHover(_fEditor));

        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint(
            SQLEditorPlugin.PLUGIN_ID, "texthover"); //$NON-NLS-1$ //$NON-NLS-2$
        IExtension[] extensions = extensionPoint.getExtensions();
        if (extensionPoint != null) 
        {
            for (int i = 0; i < extensions.length; ++i) 
            {
                IConfigurationElement[] configElements = extensions[i]
                    .getConfigurationElements();
                for (int j = 0; j < configElements.length; ++j) 
                {
                    if (configElements[j].getName().equals("hover")) 
                    {
                        //$NON-NLS-1$
                        String className = configElements[j]
                            .getAttribute("class");
                        try 
                        {
                            AbstractSQLEditorTextHover h = (AbstractSQLEditorTextHover) configElements[j]
                                .createExecutableExtension("class"); //$NON-NLS-1$
                            h.setEditor(_fEditor);
                            _fInstantiatedTextHovers.add(h);
                        }
                        catch (CoreException e) 
                        {
                            SQLEditorPlugin.getDefault().log(e);
                        }

                    }
                }
            }
        }

    }

    private void checkTextHovers()
    {
    }

    protected void addTextHover(ITextHover hover)
    {
        if (!_fInstantiatedTextHovers.contains(hover))
        {
            _fInstantiatedTextHovers.add(hover);
        }
    }

    /*
     * @see ITextHover#getHoverInfo(ITextViewer, IRegion)
     */
    public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion)
    {

        checkTextHovers();
        _fBestHover = null;

        if (_fInstantiatedTextHovers == null)
        {
            return null;
        }

        for (Iterator iterator = _fInstantiatedTextHovers.iterator(); iterator.hasNext();)
        {
            ITextHover hover = (ITextHover) iterator.next();

            String s = hover.getHoverInfo(textViewer, hoverRegion);
            if (s != null && s.trim().length() > 0)
            {
                _fBestHover = hover;
                return s;
            }
        }

        return null;
    }

    /*
     * @see org.eclipse.jface.text.ITextHoverExtension#getHoverControlCreator()
     * @since 3.0
     */
    public IInformationControlCreator getHoverControlCreator()
    {
        if (_fBestHover instanceof ITextHoverExtension)
        {
            return ((ITextHoverExtension) _fBestHover).getHoverControlCreator();
        }

        return null;
    }

    /*
     * @see org.eclipse.jface.text.information.IInformationProviderExtension2#getInformationPresenterControlCreator()
     * @since 3.0
     */
    public IInformationControlCreator getInformationPresenterControlCreator()
    {
        if (_fBestHover instanceof IInformationProviderExtension2)
        {
            return ((IInformationProviderExtension2) _fBestHover).getInformationPresenterControlCreator();
        }

        return null;
    }

}
