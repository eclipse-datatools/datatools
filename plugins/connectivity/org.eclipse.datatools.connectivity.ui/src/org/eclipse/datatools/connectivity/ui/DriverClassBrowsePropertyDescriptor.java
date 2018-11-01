/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
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
import org.eclipse.datatools.connectivity.drivers.IDriverInstancePropertyDescriptor;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.drivers.DriverClassEditDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * @author brianf
 *
 */
public class DriverClassBrowsePropertyDescriptor extends TextPropertyDescriptor implements IDriverInstancePropertyDescriptor {

	private static String DRIVER_CLASS_PROP_ID = "org.eclipse.datatools.connectivity.db.driverClass"; //$NON-NLS-1$

	private String[] mJarList = null;
	
	/**
	 * No arg constructor
	 */
	public DriverClassBrowsePropertyDescriptor() {
		super(DRIVER_CLASS_PROP_ID, 
				ConnectivityUIPlugin.getDefault().getResourceString("DriverClassBrowsePropertyDescriptor.property.label")); //$NON-NLS-1$
	}
	
	/**
	 * @param id
	 * @param displayName
	 * @param jarList
	 */
	public DriverClassBrowsePropertyDescriptor(Object id, String displayName, String[] jarList ) {
		super(id, displayName);
		this.mJarList = jarList;
	}

	/**
	 * @param id
	 * @param displayName
	 */
	public DriverClassBrowsePropertyDescriptor(Object id, String displayName ) {
		super(id, displayName);
	}
	
	/**
	 * @param jarList
	 */
	public void setJarList( String[] jarList ) {
		this.mJarList = jarList;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.TextPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
	 */
	public CellEditor createPropertyEditor(Composite parent) {
		DialogCellEditor editor = new DriverClassDialogCellEditor(parent);
		return editor;
	}
	
	/**
	 * @author brianf
	 *
	 */
	private class DriverClassDialogCellEditor extends DialogCellEditor {

		/**
		 * @param parent
		 */
		public DriverClassDialogCellEditor ( Composite parent ) {
			super(parent);
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
		 */
		protected Object openDialogBox(Control cellEditorWindow) {
			DriverClassEditDialog listDialog = new DriverClassEditDialog(cellEditorWindow.getShell());
			listDialog.setJarList(mJarList);
			listDialog.setTitle(
					ConnectivityUIPlugin.getDefault().getResourceString("DriverClassBrowsePropertyDescriptor.jardialog.title")); //$NON-NLS-1$
			listDialog.setMessage(
					ConnectivityUIPlugin.getDefault().getResourceString("DriverClassBrowsePropertyDescriptor.jardialog.msg")); //$NON-NLS-1$
			listDialog.setHelpAvailable(false);
			listDialog.setInput(getValue());
			int returnCode = listDialog.open();
			if (returnCode == Window.OK) {
				Object[] results = listDialog.getResult();
				if (results.length > 0 && results[0] instanceof String) {
					return results[0];
				}
			}
			return null;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.drivers.IDriverInstancePropertyDescriptor#setDriverInstance(org.eclipse.datatools.connectivity.drivers.DriverInstance)
	 */
	public void setDriverInstance(DriverInstance instance) {
		this.mJarList = instance.getJarListAsArray();
	}
}
