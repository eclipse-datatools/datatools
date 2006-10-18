/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 * 				 brianf - updated property descriptor to use createExecutableExtension
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.IDriverInstancePropertyDescriptor;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * @author brianf
 */
public class DriverInstancePropertySource implements IPropertySource {

	private static final String P_NAME = "name"; //$NON-NLS-1$
	private static final String P_ID = "id"; //$NON-NLS-1$
	private static final String P_VISIBLE = "visible"; //$NON-NLS-1$
	private static final String P_CATEGORY = "category"; //$NON-NLS-1$
	private static final String P_CUSTOM_PROPERTY_DESCRIPTOR = "customPropertyDescriptor"; //$NON-NLS-1$
	
	private DriverInstance mDI;
	private TemplateDescriptor descriptor = null;

	/**
	 * @param cp
	 */
	public DriverInstancePropertySource(DriverInstance di) {
		mDI = di;
		this.descriptor = mDI.getTemplate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	public Object getEditableValue() {
		return mDI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		
		ArrayList descList = new ArrayList();
		
		if (descriptor != null) {
			IConfigurationElement[] props = descriptor.getProperties();
			ArrayList list = new ArrayList();
			for (int i = 0; i < props.length; i++) {
				String visible = props[i].getAttribute(P_VISIBLE);
				boolean propvisible = true;
				if (visible != null && visible.equalsIgnoreCase(Boolean.toString(false)))
					propvisible = false;
				if (propvisible)
					list.add(props[i]);
			}
			if (list.size() > 0) {
				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					IConfigurationElement ice = (IConfigurationElement) iter.next();
					String name = ice.getAttribute(P_NAME);
					String id = ice.getAttribute(P_ID);
					String ctceClass = ice.getAttribute(P_CUSTOM_PROPERTY_DESCRIPTOR);
					String category = ice.getAttribute(P_CATEGORY);
					if (category == null) {
						category = ConnectivityUIPlugin.getDefault().getResourceString("properties.category.general"); //$NON-NLS-1$
					}
					if (ctceClass != null) {
						if (ctceClass != null) {
							try {
								PropertyDescriptor pd = (PropertyDescriptor) ice.createExecutableExtension(P_CUSTOM_PROPERTY_DESCRIPTOR);
								
								if (category != null) {
									pd.setCategory(category);
								}
								
								if (pd instanceof IDriverInstancePropertyDescriptor ) {
									((IDriverInstancePropertyDescriptor)pd).setDriverInstance(this.mDI);
								}
								descList.add(pd);
							} catch (SecurityException e) {
								ExceptionHandler.showException(new Shell(), 
										ConnectivityUIPlugin.getDefault().getResourceString("PropertyDescriptor.error.title"), //$NON-NLS-1$
										e.getLocalizedMessage(), e);
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								ExceptionHandler.showException(new Shell(), 
										ConnectivityUIPlugin.getDefault().getResourceString("PropertyDescriptor.error.title"), //$NON-NLS-1$
										e.getLocalizedMessage(), e);
								e.printStackTrace();
							} catch (CoreException e ) {
								ExceptionHandler.showException(new Shell(), 
										ConnectivityUIPlugin.getDefault().getResourceString("PropertyDescriptor.error.title"), //$NON-NLS-1$
										e.getLocalizedMessage(), e);
								e.printStackTrace();
							}
						}
					}
					else {
						TextPropertyDescriptor tpd = new TextPropertyDescriptor(id, name);
						if (category != null) {
							tpd.setCategory(category);
						}
						descList.add(tpd);
					}
				}
			}
			if (descList.size() > 0) {
				IPropertyDescriptor[] pdArray = (IPropertyDescriptor[]) descList.toArray(new IPropertyDescriptor[descList.size()]);
				Arrays.sort(pdArray, new Comparator(){

					public int compare(Object o1, Object o2) {
						IPropertyDescriptor pd1 = (IPropertyDescriptor) o1;
						IPropertyDescriptor pd2 = (IPropertyDescriptor) o2;
						return pd1.getDisplayName().compareTo(pd2.getDisplayName());
					}});
				return pdArray;
			}
		}

		return new IPropertyDescriptor[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		if (descriptor != null) {
			String idStr = (String) id;
			IPropertySet propSet = mDI.getPropertySet();
			String propval = propSet.getBaseProperties()
					.getProperty(idStr);
			if (propval != null && propval.length() > 0) {
				return propval;
			}
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
	 */
	public boolean isPropertySet(Object id) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
	 */
	public void resetPropertyValue(Object id) {
		// Do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object,
	 *      java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		String strid = (String) id;
		if (this.mDI.getPropertySet() != null) {
			this.mDI.getPropertySet().getBaseProperties().setProperty(strid, (String) value);
		}
	}
}
