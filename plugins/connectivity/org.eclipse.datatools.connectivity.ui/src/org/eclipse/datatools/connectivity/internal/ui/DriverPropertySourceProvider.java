/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * @author brianf
 *
 */
public class DriverPropertySourceProvider implements IPropertySourceProvider, ChangeListener {

	// Cached driver instance and property source
	private DriverInstance di = null;
	private DriverInstancePropertySource dips = null;
	
	// change listeners
	private static ListenerList changeListeners  = new ListenerList();

	/**
	 * Constructor 
	 */
	public DriverPropertySourceProvider() {
	}
	
	/**
	 * Constructor 
	 * @param propSet
	 * @param descriptor
	 */
	public DriverPropertySourceProvider ( IPropertySet propSet, TemplateDescriptor descriptor ) {
		this();
		di = new DriverInstance(descriptor, propSet);
		dips = new DriverInstancePropertySource(this.di);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySourceProvider#getPropertySource(java.lang.Object)
	 */
	public IPropertySource getPropertySource(Object object) {
		
		if (object instanceof DriverInstance) {
			dips.removeChangeListener(this);
			dips.setDriverInstance((DriverInstance) object);
			dips.addChangeListener(this);
			return dips;
		}
		else if (object instanceof IPropertySet) {
			dips.removeChangeListener(this);
			dips.setDriverInstance(this.di);
			dips.addChangeListener(this);
			return dips;
		}
		return null;
	}

	/*
	 * If we changed, fire a changed event.
	 * 
	 * @param source
	 */
	private void fireChangedEvent(Object source) {
		ChangeEvent e = new ChangeEvent(source);
		// inform any listeners of the resize event
		Object[] listeners = DriverPropertySourceProvider.changeListeners.getListeners();
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
		DriverPropertySourceProvider.changeListeners.add(listener);
	}

	/**
	 * Remove a change listener.
	 * 
	 * @param listener
	 */
	public void removeChangeListener(ChangeListener listener) {
		DriverPropertySourceProvider.changeListeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	public void stateChanged(ChangeEvent e) {
		fireChangedEvent(this);
	}
}