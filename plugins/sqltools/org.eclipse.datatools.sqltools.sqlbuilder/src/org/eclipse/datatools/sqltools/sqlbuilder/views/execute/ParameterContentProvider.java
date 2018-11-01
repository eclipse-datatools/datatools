/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views.execute;

import java.util.ArrayList;
import java.util.Vector;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

class ParameterContentProvider implements IStructuredContentProvider {

    private Vector parmMarkers;

    public ParameterContentProvider(Vector parmMarkers) {
        this.parmMarkers = parmMarkers;
    }

    public Object[] getElements(java.lang.Object property) {
        ArrayList tableElements = new ArrayList();
        if (property instanceof Vector) {
            Vector parmValues = (Vector) property;

            for (int i = 0; i < parmMarkers.size(); i++) {
                tableElements.add(new ParameterElement(parmMarkers.elementAt(i), parmValues, i));
            }
        }
        return tableElements.toArray();
    }

    public void dispose() {
    }

    public void inputChanged(Viewer viewer, Object old, Object newobj) {
    }

}
