/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.dialogs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class DriverTreeTableContentProvider implements ITreeContentProvider {

	public DriverTreeTableContentProvider() {
		// empty
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof CategoryRoot) {
			return CategoryDescriptor.getRootCategories();
		}
		else if (parentElement instanceof CategoryDescriptor) {
			CategoryDescriptor cd = (CategoryDescriptor) parentElement;
			ArrayList templateList = new ArrayList();
			gatherTemplates(cd, templateList);
			return templateList.toArray();
		}
		return new Object[0];
	}

	public Object getParent(Object element) {
		if (element instanceof CategoryDescriptor) {
			return ((CategoryDescriptor)element).getParent();
		}
		else if (element instanceof TemplateDescriptor) {
			return ((TemplateDescriptor)element).getParent();
		}
		return null;
	}

	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	private void gatherTemplates ( CategoryDescriptor cd, List totalList ) {
		if (cd.getAssociatedDriverTypes().size() > 0 )
			totalList.addAll(cd.getAssociatedDriverTypes());
		if (cd.getChildCategories().size() > 0) {
			Iterator iter = cd.getChildCategories().iterator();
			while (iter.hasNext()) {
				gatherTemplates((CategoryDescriptor) iter.next(), totalList);
			}
		}
	}

	public List getDriverInstances() {
		DriverInstance[] dinstances = 
			DriverManager.getInstance().getAllDriverInstances();
		ArrayList list = new ArrayList();
		for (int i = 0; i < dinstances.length; i++) {
			list.add(dinstances[i].getPropertySet());
		}
		return list;
	}
}
