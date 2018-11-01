/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.util.ArrayList;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.drivers.models.OverrideTemplateDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Table content provider
 * 
 * @author brianf
 */
public class PropertiesContentProvider implements IStructuredContentProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		// empty
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (newInput != null && newInput.equals(oldInput)) {
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(Object)
	 */
	public Object[] getChildren(Object parentElement) {
		Object children[];

		if (parentElement instanceof TemplateDescriptor) {
			TemplateDescriptor target = (TemplateDescriptor) parentElement;
			IConfigurationElement[] props = target.getProperties();
			ArrayList list = new ArrayList();
			for (int i = 0; i < props.length; i++) {
				String id = props[i].getAttribute("id");//$NON-NLS-1$
				String visible = props[i].getAttribute("visible"); //$NON-NLS-1$
				OverrideTemplateDescriptor[] otds = 
					OverrideTemplateDescriptor.getByDriverTemplate(target.getId());
				if (otds != null && otds.length > 0) {
					String temp =
						otds[0].getPropertyVisibleFromId(id);
					if (temp != null && temp.length() > 0)
						visible = temp;
				}
				boolean propvisible = true;
				if (visible != null && visible.equals("false")) //$NON-NLS-1$
					propvisible = false;
				if (propvisible)
					list.add(props[i]);
			}
			children = list.toArray(new IConfigurationElement[list.size()]);
		}
		else if (parentElement instanceof IConfigurationElement) {
			children = new Object[0];
		}
		else {
			children = new Object[0];
		}
		return children;
	}
}
