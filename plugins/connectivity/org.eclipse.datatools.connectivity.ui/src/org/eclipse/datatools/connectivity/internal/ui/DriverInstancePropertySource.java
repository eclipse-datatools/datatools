/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 * 				 brianf - updated property descriptor to use createExecutableExtension
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.IDriverInstancePropertyDescriptor;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.models.OverrideTemplateDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.datatools.connectivity.internal.ui.drivers.DriverPropertyEditorDescriptor;
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
	
	// change listeners
	private ListenerList changeListeners;

	/**
	 * Constructor
	 * @param di - incoming driver instance
	 */
	public DriverInstancePropertySource(DriverInstance di) {
		mDI = di;
		this.descriptor = mDI.getTemplate();
		changeListeners = new ListenerList();
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
	 * Pass in an instance to work on
	 * @param di
	 */
	public void setDriverInstance (DriverInstance di) {
		mDI = di;
		this.descriptor = mDI.getTemplate();
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
			OverrideTemplateDescriptor[] otds = 
				OverrideTemplateDescriptor.getByDriverTemplate(descriptor.getId());
			for (int i = 0; i < props.length; i++) {
				String id = props[i].getAttribute(P_ID);
				String visible = props[i].getAttribute(P_VISIBLE);
				boolean remove = false;
				if (otds != null && otds.length > 0) {
					String temp =
						otds[0].getPropertyVisibleFromId(id);
					if (temp != null && temp.length() > 0)
						visible = temp;
					remove = otds[0].getPropertyRemoveFlagFromID(id);
				}
				boolean propvisible = true;
				if (visible != null && visible.equalsIgnoreCase(Boolean.toString(false)))
					propvisible = false;
				if (propvisible && !remove)
					list.add(props[i]);
			}
			if (list.size() > 0) {
				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					IConfigurationElement ice = (IConfigurationElement) iter.next();
					String id = ice.getAttribute(P_ID);
					String name = ice.getAttribute(P_NAME);
					if (otds != null && otds.length > 0) {
						String temp =
							otds[0].getPropertyNameFromId(id);
						if (temp != null && temp.length() > 0)
							name = temp;
					}
					String ctceClass = ice.getAttribute(P_CUSTOM_PROPERTY_DESCRIPTOR);
					DriverPropertyEditorDescriptor[] dpeds =
						DriverPropertyEditorDescriptor.getByDriverTemplateAndProperty(descriptor.getId(), id);
					if (dpeds != null && dpeds.length > 0) {
						ctceClass = dpeds[0].getCustomPropertyEditor();
						ice = dpeds[0].getElement();
					}
					if (otds != null && otds.length > 0) {
						String temp =
							otds[0].getPropertyCustomPropDescriptorFromId(id);
						if (temp != null && temp.length() > 0)
							ctceClass = temp;
					}
					String category = ice.getAttribute(P_CATEGORY);
					if (otds != null && otds.length > 0) {
						String temp =
							otds[0].getPropertyCategoryFromId(id);
						if (temp != null && temp.length() > 0)
							category = temp;
					}
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
		return ""; //$NON-NLS-1$
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
			fireChangedEvent(this);
		}
	}
	
	/*
	 * If we changed, fire a changed event.
	 * 
	 * @param source
	 */
	private void fireChangedEvent(Object source) {
		ChangeEvent e = new ChangeEvent(source);
		// inform any listeners of the resize event
		Object[] listeners = this.changeListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			((ChangeListener) listeners[i]).stateChanged(e);
		}
	}

	/**
	 * Add a change listener
	 * 
	 * @param listener
	 */
	public void addChangeListener(ChangeListener listener) {
		this.changeListeners.add(listener);
	}

	/**
	 * Remove a change listener.
	 * 
	 * @param listener
	 */
	public void removeChangeListener(ChangeListener listener) {
		this.changeListeners.remove(listener);
	}
}
