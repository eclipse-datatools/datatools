/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.filters;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IUDFNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IUDTNode;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSchema;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Filter to exclude UDF and UDT nodes.
 * 
 * @author enrico.schenk@ingres.com
 */
public class UdtUdfFilterFilter extends ViewerFilter {

    /**
     * Returns false if the given element is an <code>IUDFNode</code> or an
     * <code>IUDTNode</code> that is part of a representation of an Ingres
     * database.
     * 
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    public boolean select(final Viewer viewer, final Object parentElement,
            final Object element) {
        if (element instanceof IUDFNode
                && ((IUDFNode) element).getParent() instanceof IngresSchema) {
            return false;
        }
        if (element instanceof IUDTNode
                && ((IUDTNode) element).getParent() instanceof IngresSchema) {
            return false;
        }

        return true;
    }

}
