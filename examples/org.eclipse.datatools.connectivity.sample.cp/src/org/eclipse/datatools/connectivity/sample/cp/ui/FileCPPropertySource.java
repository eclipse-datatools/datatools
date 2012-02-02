/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.cp.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.ConnectionProfilePropertySource;
import org.eclipse.datatools.connectivity.sample.cp.IFileProfilePropertyConstants;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * @author brianf
 */
public class FileCPPropertySource extends ConnectionProfilePropertySource {

	private static final String P_ID_FILEPATH = "org.eclipse.datatools.connectivity.properties.sample.profile.filepath"; //$NON-NLS-1$

	private IConnectionProfile mCP;

	public FileCPPropertySource(IConnectionProfile cp) {
		super(cp);
		mCP = cp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	public Object getEditableValue() {
		return mCP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		
		IPropertyDescriptor[] superDescriptors =
			super.getPropertyDescriptors();
		
		List descList = Arrays.asList(superDescriptors);
		ArrayList fullList = new ArrayList();
		fullList.addAll(descList);
		
		PropertyDescriptor desFilePath = new PropertyDescriptor(P_ID_FILEPATH,
				"File Path"); //$NON-NLS-1$
		desFilePath.setCategory("Core"); //$NON-NLS-1$
		
		fullList.add(desFilePath);

		return (IPropertyDescriptor[]) fullList.toArray( new IPropertyDescriptor[ fullList.size() ]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		Object value =
			super.getPropertyValue(id);
		if (value == null) {
			if (id.equals(P_ID_FILEPATH)) {
				return mCP.getBaseProperties().getProperty(IFileProfilePropertyConstants.FILE_PATH);
			}
			else {
				return null;
			}
		}
		else
			return value;
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
		// Do nothing
	}
}
