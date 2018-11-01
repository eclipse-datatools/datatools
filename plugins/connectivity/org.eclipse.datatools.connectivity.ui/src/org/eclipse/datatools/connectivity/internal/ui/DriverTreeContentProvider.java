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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.XMLFileManager;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.DriversProvider;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Content provider for the driver tree.
 * 
 * Note that this is used on both the preference page, the management dialog,
 * and the add driver dialog. In the first two cases, it is used to display
 * categories and driver instances. In the last case, it is used to display
 * categories and driver templates.
 * 
 * @author brianf
 */
public class DriverTreeContentProvider implements ITreeContentProvider {

	// local list of driver instances
	private List psetsList;

	// local stash of file modified date
	private String modified;

	// flag indicating whether to show templates or not
	private boolean mShowDriverTemplates = false;

	/**
	 * Constructor
	 */
	public DriverTreeContentProvider() {
		super();
	}

	/**
	 * Constructor with flag to show templates
	 * 
	 * @param showDriverTemplates
	 */
	public DriverTreeContentProvider(boolean showDriverTemplates) {
		this();
		this.mShowDriverTemplates = showDriverTemplates;
	}

	/**
	 * Return a list of driver instances
	 * 
	 * @return
	 */
	public List getDriverInstances() {
		return this.psetsList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		if (this.psetsList != null)
			this.psetsList.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object element) {
		List children = new ArrayList();
		if (element instanceof DriversProvider) {
			children = Arrays.asList(CategoryDescriptor.getRootCategories());
		}
		if (element instanceof CategoryDescriptor) {
			CategoryDescriptor descriptor = (CategoryDescriptor) element;
			children = descriptor.getChildCategories();
			if (descriptor.getAssociatedDriverTypes().size() > 0) {
				if (this.mShowDriverTemplates) {
					children.addAll(descriptor.getAssociatedDriverTypes());
				}
				else {
					List driverTypes = descriptor.getAssociatedDriverTypes();
					Iterator iter = driverTypes.iterator();
					while (iter.hasNext()) {
						TemplateDescriptor template = (TemplateDescriptor) iter
								.next();
						children.addAll(Arrays.asList(getDriverDefn(template
								.getId())));
					}
				}
			}
		}
		else if (element instanceof TemplateDescriptor) {
			if (!this.mShowDriverTemplates) {
				TemplateDescriptor descriptor = (TemplateDescriptor) element;
				children = Arrays.asList(getDriverDefn(descriptor.getId()));
			}
		}
		return children.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object element) {
		List children = new ArrayList();
		if (element instanceof DriversProvider) {
			children = Arrays.asList(CategoryDescriptor.getRootCategories());
		}
		else if (element instanceof CategoryDescriptor) {
			CategoryDescriptor descriptor = (CategoryDescriptor) element;
			children = descriptor.getChildCategories();
			children.addAll(descriptor.getAssociatedDriverTypes());
			Iterator iter = descriptor.getAssociatedDriverTypes().iterator();
			while (iter.hasNext()) {
				TemplateDescriptor template = (TemplateDescriptor) iter
						.next();
				children.addAll(Arrays.asList(getDriverDefn(template
						.getId())));
			}
		}
		return children.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element) {
		if (element instanceof DriversProvider)
			return null;
		else if (element instanceof CategoryDescriptor) {
			CategoryDescriptor descriptor = (CategoryDescriptor) element;
			return descriptor.getParent();
		}
		else if (element instanceof TemplateDescriptor) {
			TemplateDescriptor descriptor = (TemplateDescriptor) element;
			return descriptor.getParent();
		}
		else if (element instanceof IPropertySet) {
			IPropertySet propset = (IPropertySet) element;
			DriverInstance di = DriverManager.getInstance().getDriverInstanceByID(propset.getID());
			if (di != null)
				return di.getTemplate();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element) {
		Object[] children = getChildren(element);
		if (children != null && children.length > 0)
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.psetsList = null;
	}

	/*
	 * Return a list of driver instances based on a category id
	 */
	private Object[] getDriverDefn(String categoryID) {

		XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
		String newModified = XMLFileManager.getFileDateTimeStamp();
		if (this.psetsList == null || (modified == null && newModified != null)
				|| ((this.modified != null) && !(this.modified
						.equals(newModified)))) {

			DriverManager.getInstance().resetDefaultInstances();

			this.modified = XMLFileManager.getFileDateTimeStamp();

			try {
				this.psetsList = new ArrayList();
				IPropertySet[] psets = XMLFileManager.loadPropertySets();
				if (psets.length > 0) {
					java.util.List tempList = Arrays.asList(psets);
					this.psetsList.addAll(tempList);
				}
			}
			catch (CoreException e) {
				ConnectivityUIPlugin.getDefault().log(e);
				return new Object[0];
			}
		}

		List children = new ArrayList();
		if (this.psetsList.size() > 0) {
			for (int i = 0; i < this.psetsList.size(); i++) {
				IPropertySet pset = (IPropertySet) this.psetsList.get(i);
				Properties props = pset.getBaseProperties();
				String category = props
						.getProperty(IDriverMgmtConstants.PROP_DEFN_TYPE); //$NON-NLS-1$
				if (category.equals(categoryID)) {
					children.add(pset);
				}
			}
		}
		return children.toArray();
	}
}