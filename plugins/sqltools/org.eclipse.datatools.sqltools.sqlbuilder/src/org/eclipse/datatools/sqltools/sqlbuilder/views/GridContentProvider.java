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
package org.eclipse.datatools.sqltools.sqlbuilder.views;

import java.util.Vector;

import org.eclipse.datatools.sqltools.sqlbuilder.util.RSCCoreUIUtil;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;


public abstract class GridContentProvider extends AdapterFactoryContentProvider {

    protected Vector tableElements = new Vector();

    public GridContentProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    public void notifyChanged(Notification msg) {
        // we override adapterfactorycontentprovider's method and just refresh the whole grid
        if (viewer != null) {
            Object input = viewer.getInput();
            if (input != null && input instanceof EObject && msg.getNotifier() instanceof EObject)
                if (RSCCoreUIUtil.chkIfEObjectsMatched((EObject) msg.getNotifier(), (EObject) input, true) == true)
                    viewer.refresh();
        }
    }

}
