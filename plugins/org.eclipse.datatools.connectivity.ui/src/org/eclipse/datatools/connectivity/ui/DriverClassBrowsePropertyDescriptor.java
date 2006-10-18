/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.IDriverInstancePropertyDescriptor;
import org.eclipse.datatools.connectivity.internal.DriverUtil;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.ListDialog;
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
			final ArrayList classes = new ArrayList();
			for (int i = 0; i < mJarList.length; i++) {
				String filepath = mJarList[i];
				final File file = new File(filepath);
				ProgressMonitorDialog pmd = new ProgressMonitorDialog(cellEditorWindow.getShell());
				try {
					pmd.run(true, false, new IRunnableWithProgress() {

						public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
							try {
								String[] classStr = DriverUtil.getDriverClassesFromJar(file, monitor);
								classes.addAll(Arrays.asList(classStr));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			ListDialog listDialog = new ListDialog(cellEditorWindow.getShell());
			listDialog.setTitle(
					ConnectivityUIPlugin.getDefault().getResourceString("DriverClassBrowsePropertyDescriptor.jardialog.title")); //$NON-NLS-1$
			listDialog.setMessage(
					ConnectivityUIPlugin.getDefault().getResourceString("DriverClassBrowsePropertyDescriptor.jardialog.msg")); //$NON-NLS-1$
			listDialog.setHelpAvailable(false);
			listDialog.setContentProvider(new ListContentProvider(classes));
			listDialog.setLabelProvider(new ListLabelProvider());
			listDialog.setInput(classes);
			listDialog.setInitialSelections(new Object[] {getValue()});
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
	
	/**
	 * @author brianf
	 *
	 */
	private class ListContentProvider implements IStructuredContentProvider {
		
		private ArrayList mList = null;
		
		public ListContentProvider(ArrayList list) {
			mList = list;
		}
		
		public Object[] getElements(Object inputElement) {
			return mList.toArray();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	
	/**
	 * @author brianf
	 *
	 */
	private class ListLabelProvider extends LabelProvider {
		public String getText(Object obj) {
			String name = (String)obj;
			if (name!=null)
				return name;
			return obj.toString();
		}
		public Image getImage(Object obj) {
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
