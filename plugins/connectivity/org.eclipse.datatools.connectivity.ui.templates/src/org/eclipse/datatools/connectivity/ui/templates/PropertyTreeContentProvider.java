/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.templates;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Tree content provider for the available pre-populated property objects
 * @author brianf
 *
 */
public class PropertyTreeContentProvider implements ITreeContentProvider {

	private TreeParent rootElement = null;
	
	/**
	 * Constructor
	 */
	public PropertyTreeContentProvider() {
		rootElement = new TreeParent();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		if (inputElement != null) {
			return getChildren(inputElement);
		}
		return new Object[0];
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		rootElement.getChildren().clear();
		rootElement = null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// empty
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IWorkspaceRoot) {
			generateChildren();
			return rootElement.getChildrenArray();
		}
		else if (parentElement instanceof TreeParent) {
			return ((TreeParent)parentElement).getChildrenArray();
		}
		return new Object[0];
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element) {
		if (element instanceof TreeObject) {
			return ((TreeObject) element).getParent();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof IWorkspaceRoot) {
			return rootElement.getChildren().size() > 0;
		}
		else if (element instanceof TreeParent) {
			return ((TreeParent) element).getChildren().size() > 0;
		}
		return false;
	}
	
	/**
	 * generates the list of pre-populated children
	 * TODO: Defining these pre-populated property objects should be migrated at some point to an extension point
	 */
	private void generateChildren() {
		rootElement.getChildren().clear();
		
		TreeParent jdbcProps = new TreeParent();
		jdbcProps.setName(Messages.getString("PropertyTreeContentProvider.Folder.GenericJDBCProperties")); //$NON-NLS-1$
		jdbcProps.setParent(rootElement);
		
		PropertyObject driverClassPO = new PropertyObject();
		driverClassPO.setPropertyID(Messages.getString("PropertyTreeContentProvider.DriverClassPropertyID")); //$NON-NLS-1$
		driverClassPO.setPropertyName(Messages.getString("PropertyTreeContentProvider.DriverClassPropertyName")); //$NON-NLS-1$
		driverClassPO.setPropertyValue(""); //$NON-NLS-1$
		jdbcProps.addChild(new TreeObject(driverClassPO.getPropertyName(), 
				driverClassPO));
		
		PropertyObject vendorPO = new PropertyObject();
		vendorPO.setPropertyID(Messages.getString("PropertyTreeContentProvider.VendorPropertyID")); //$NON-NLS-1$
		vendorPO.setPropertyName(Messages.getString("PropertyTreeContentProvider.VendorPropertyName")); //$NON-NLS-1$
		vendorPO.setPropertyValue(Messages.getString("PropertyTreeContentProvider.VendorPropertyValue")); //$NON-NLS-1$
		vendorPO.setPropertyVisible(Messages.getString("PropertyTreeContentProvider.VendorPropertyVisible")); //$NON-NLS-1$
		jdbcProps.addChild(new TreeObject(vendorPO.getPropertyName(), 
				vendorPO));
		
		PropertyObject versionPO = new PropertyObject();
		versionPO.setPropertyID(Messages.getString("PropertyTreeContentProvider.VersionPropertyID")); //$NON-NLS-1$
		versionPO.setPropertyName(Messages.getString("PropertyTreeContentProvider.VersionPropertyName")); //$NON-NLS-1$
		versionPO.setPropertyValue(Messages.getString("PropertyTreeContentProvider.VersionPropertyValue")); //$NON-NLS-1$
		versionPO.setPropertyVisible(Messages.getString("PropertyTreeContentProvider.VersionPropertyVisible")); //$NON-NLS-1$
		jdbcProps.addChild(new TreeObject(versionPO.getPropertyName(), 
				versionPO));
		
		PropertyObject databaseNamePO = new PropertyObject();
		databaseNamePO.setPropertyID(Messages.getString("PropertyTreeContentProvider.DatabaseNamePropertyID")); //$NON-NLS-1$
		databaseNamePO.setPropertyName(Messages.getString("PropertyTreeContentProvider.DatabaseNamePropertyName")); //$NON-NLS-1$
		databaseNamePO.setPropertyValue(Messages.getString("PropertyTreeContentProvider.DatabaseNamePropertyValue")); //$NON-NLS-1$
		jdbcProps.addChild(new TreeObject(databaseNamePO.getPropertyName(), 
				databaseNamePO));

		PropertyObject urlPO = new PropertyObject();
		urlPO.setPropertyID(Messages.getString("PropertyTreeContentProvider.URLPropertyID")); //$NON-NLS-1$
		urlPO.setPropertyName(Messages.getString("PropertyTreeContentProvider.URLPropertyName")); //$NON-NLS-1$
		urlPO.setPropertyValue(Messages.getString("PropertyTreeContentProvider.URLPropertyValue")); //$NON-NLS-1$
		jdbcProps.addChild(new TreeObject(urlPO.getPropertyName(), 
				urlPO));

		PropertyObject usernamePO = new PropertyObject();
		usernamePO.setPropertyID(Messages.getString("PropertyTreeContentProvider.UsernamePropertyID")); //$NON-NLS-1$
		usernamePO.setPropertyName(Messages.getString("PropertyTreeContentProvider.UsernamePropertyName")); //$NON-NLS-1$
		usernamePO.setPropertyValue(""); //$NON-NLS-1$
		jdbcProps.addChild(new TreeObject(usernamePO.getPropertyName(), 
				usernamePO));
		
		rootElement.addChild(jdbcProps);

		TreeParent otherProps = new TreeParent();
		otherProps.setName(Messages.getString("PropertyTreeContentProvider.OtherCommonPropertiesFolder")); //$NON-NLS-1$
		otherProps.setParent(rootElement);

		PropertyObject passwordPO = new PropertyObject();
		passwordPO.setPropertyID(Messages.getString("PropertyTreeContentProvider.PasswordPropertyID")); //$NON-NLS-1$
		passwordPO.setPropertyName(Messages.getString("PropertyTreeContentProvider.PasswordPropertyName")); //$NON-NLS-1$
		passwordPO.setPropertyValue(""); //$NON-NLS-1$
		otherProps.addChild(new TreeObject(passwordPO.getPropertyName(), 
				passwordPO));
		
		PropertyObject connectionPropsPO = new PropertyObject();
		connectionPropsPO.setPropertyID(Messages.getString("PropertyTreeContentProvider.ConnectionPropertiesPropertyID")); //$NON-NLS-1$
		connectionPropsPO.setPropertyName(Messages.getString("PropertyTreeContentProvider.ConnectionPropertiesPropertyName")); //$NON-NLS-1$
		connectionPropsPO.setPropertyValue(Messages.getString("PropertyTreeContentProvider.ConnectionPropertiesPropertyValue")); //$NON-NLS-1$
		otherProps.addChild(new TreeObject(connectionPropsPO.getPropertyName(), 
				connectionPropsPO));

		rootElement.addChild(otherProps);

		TreeParent customProps = new TreeParent();
		customProps.setName(Messages.getString("PropertyTreeContentProvider.CustomPropertiesFolder")); //$NON-NLS-1$
		customProps.setParent(rootElement);

		PropertyObject newPropPO = new PropertyObject();
		customProps.addChild(new TreeObject(newPropPO.getPropertyName(), 
				newPropPO));

		rootElement.addChild(customProps);
	}

}
