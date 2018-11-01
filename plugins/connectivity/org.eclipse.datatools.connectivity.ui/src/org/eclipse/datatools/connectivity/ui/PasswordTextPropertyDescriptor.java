/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui;

import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * @author brianf
 *
 */
public class PasswordTextPropertyDescriptor extends TextPropertyDescriptor {

	private static String PASSWORD_PROP_ID = "org.eclipse.datatools.connectivity.db.password"; //$NON-NLS-1$
	
	public PasswordTextPropertyDescriptor() {
		this(PASSWORD_PROP_ID, 
				ConnectivityUIPlugin.getDefault().getResourceString("PasswordTextPropertyDescriptor.property.label")); //$NON-NLS-1$
	}
	
	/**
	 * @param id
	 * @param displayName
	 */
	public PasswordTextPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
		this.setLabelProvider(new PasswordLabelProvider());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.TextPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
	 */
	public CellEditor createPropertyEditor(Composite parent) {
        CellEditor editor = new TextCellEditor(parent, SWT.PASSWORD);
        if (getValidator() != null) {
			editor.setValidator(getValidator());
		}
        return editor;
	}

	/**
	 * @author brianf
	 *
	 */
	class PasswordLabelProvider implements ILabelProvider {

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
		 */
		public Image getImage(Object element) {
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		public String getText(Object element) {
			if (element instanceof String) {
				String value = (String) element;
				if (value != null) {
					String mask = ""; //$NON-NLS-1$
					for (int i = 0; i < value.length(); i++) {
						mask = mask + "*"; //$NON-NLS-1$
					}
					return mask;
				}
			}
			else if (element instanceof DriverInstance) {
				DriverInstance di = (DriverInstance) element;
				String value = di.getNamedProperty("password"); //$NON-NLS-1$
				if (value != null) {
					String mask = ""; //$NON-NLS-1$
					for (int i = 0; i < value.length(); i++) {
						mask = mask + "*"; //$NON-NLS-1$
					}
					return mask;
				}
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
		 */
		public void addListener(ILabelProviderListener listener) {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
		 */
		public void dispose() {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
		 */
		public boolean isLabelProperty(Object element, String property) {
			return true;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
		 */
		public void removeListener(ILabelProviderListener listener) {
		}
		
	}

}
