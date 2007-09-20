/*******************************************************************************
 * Copyright (c) 2001, 2004, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.ui.dse.dialogs;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.ui.dse.DSEPlugin;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

public class ExistingConnectionProfilesDialogPage extends DialogPage implements
		Listener {
	private Button newConnectionRadioButton;

	private Button existingConnectionRadioButton;

	private Group existingConnectionsGroup;

	private List existingConnectionsList;

	private Label propertiesLabel;

	private Table connectionPropertiesTable;

	private Hashtable existingConnections;

	private int lastSelectedConnection = -1;

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.verticalSpacing = 5;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		newConnectionRadioButton = new Button(composite, SWT.RADIO);
		newConnectionRadioButton.setText(DSEPlugin.getDefault().getResourceString("ExistingConnectionsDialogPage.CreateNewConnection.button")); //$NON-NLS-1$
		GridData gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		newConnectionRadioButton.setLayoutData(gd);

		existingConnectionRadioButton = new Button(composite, SWT.RADIO);
		existingConnectionRadioButton.setText(DSEPlugin.getDefault().getResourceString("ExistingConnectionsDialogPage.UseExistingConnection.button")); //$NON-NLS-1$
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		existingConnectionRadioButton.setLayoutData(gd);

		Composite indentationComposite = new Composite(composite, SWT.NONE);
		layout = new GridLayout();
		layout.numColumns = 2;
		layout.verticalSpacing = 5;
		indentationComposite.setLayout(layout);
		indentationComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		existingConnectionsGroup = new Group(indentationComposite, SWT.NONE);
		existingConnectionsGroup.setText(DSEPlugin.getDefault().getResourceString("ExistingConnectionsDialogPage.ExistingConnections.group")); //$NON-NLS-1$
		layout = new GridLayout();
		layout.numColumns = 1;
		layout.verticalSpacing = 5;
		existingConnectionsGroup.setLayout(layout);
		gd = new GridData(GridData.FILL_BOTH);
		existingConnectionsGroup.setLayoutData(gd);

		existingConnectionsList = new List(existingConnectionsGroup, SWT.BORDER
				| SWT.V_SCROLL);
		gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
		gd.heightHint = 100;
		existingConnectionsList.setLayoutData(gd);

		propertiesLabel = new Label(existingConnectionsGroup, SWT.NONE);
		propertiesLabel.setText(DSEPlugin.getDefault().getResourceString("ExistingConnectionsDialogPage.Properties.label")); //$NON-NLS-1$
		gd = new GridData();
		propertiesLabel.setLayoutData(gd);

		connectionPropertiesTable = new Table(existingConnectionsGroup,
				SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		connectionPropertiesTable.setLayoutData(gd);
		connectionPropertiesTable.setLinesVisible(true);
		connectionPropertiesTable.setHeaderVisible(true);

		TableColumn tc1 = new TableColumn(connectionPropertiesTable, SWT.NONE);
		tc1.setText(DSEPlugin.getDefault().getResourceString("ExistingConnectionsDialogPage.Property.columnHeader")); //$NON-NLS-1$
		tc1.setResizable(true);
		tc1.setWidth(140);

		TableColumn tc2 = new TableColumn(connectionPropertiesTable, SWT.NONE);
		tc2.setText(DSEPlugin.getDefault().getResourceString("ExistingConnectionsDialogPage.Value.columnHeader")); //$NON-NLS-1$
		tc2.setResizable(true);
		tc2.setWidth(250);

		initializeDialogUnits(composite);

		newConnectionRadioButton.addListener(SWT.Selection, this);
		newConnectionRadioButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				onCreateNewConnectionSelectionChanged();

			}
		});
		existingConnectionRadioButton.addListener(SWT.Selection, this);
		existingConnectionsList.addListener(SWT.Selection, this);

		newConnectionRadioButton.setSelection(true);
		enableExistingConnectionsControls(false);
		initializeValues();
	}

	public void handleEvent(Event event) {
		Widget source = event.widget;
		if (source == newConnectionRadioButton) {
			enableExistingConnectionsControls(existingConnectionRadioButton
					.getSelection());
		} else if (source == existingConnectionsList) {
			updateConnectionProperties();
		}

	}

	/**
	 * Override this method to handle the radio button selection change event
	 * 
	 */
	protected void onCreateNewConnectionSelectionChanged() {
	}

	/**
	 * Returns the list of existing connections to display to the user. Override
	 * this method to provide a filtered list of connections.
	 * 
	 * @return An array of IConnectionProfile objects that should be displayed in
	 *         the existing connections list
	 */
	protected IConnectionProfile[] getConnectionsToDisplay() {
		return ProfileManager.getInstance().getProfiles(false);
	}

	private void initializeValues() {
		existingConnectionsList.removeAll();
		IConnectionProfile[] connInfos = getConnectionsToDisplay();
		if (connInfos != null) {
			existingConnections = new Hashtable();
			Iterator connections = Arrays.asList(connInfos).iterator();
			IConnectionProfile con;
			while (connections.hasNext()) {
				con = (IConnectionProfile) connections.next();
				existingConnections.put(con.getName(), con);
			}
			Enumeration existingConnectionNames = existingConnections.keys();
			while (existingConnectionNames.hasMoreElements()) {
				existingConnectionsList.add(existingConnectionNames
						.nextElement().toString());
			}
		}

		if (existingConnectionsList.getItemCount() > 0) {
			existingConnectionsList.select(0);
			updateConnectionProperties();
			enableExistingConnectionsControls(false);
			existingConnectionRadioButton.setEnabled(true);
		} else {
			enableExistingConnectionsControls(false);
			existingConnectionRadioButton.setEnabled(false);
		}
		newConnectionRadioButton.setSelection(true);
		existingConnectionRadioButton.setSelection(false);
	}

	private void enableExistingConnectionsControls(boolean isEnabled) {
		existingConnectionsGroup.setEnabled(isEnabled);
		existingConnectionsList.setEnabled(isEnabled);
		propertiesLabel.setEnabled(isEnabled);
		connectionPropertiesTable.setEnabled(isEnabled);
		if (isEnabled) {
			existingConnectionsList.select(lastSelectedConnection);
			updateConnectionProperties();
		} else {
			lastSelectedConnection = existingConnectionsList
					.getSelectionIndex();
			existingConnectionsList.deselectAll();
			updateConnectionProperties();
		}
	}

	private void updateConnectionProperties() {
		connectionPropertiesTable.removeAll();
		if (existingConnectionsList.getSelectionIndex() > -1) {
			IConnectionProfile selectedConnection = (IConnectionProfile) existingConnections
					.get((String) existingConnectionsList.getSelection()[0]);
			if (selectedConnection != null) {

				ConnectionDisplayProperty[] properties = getConnectionDisplayProperties(selectedConnection);
				if (properties != null) {
					int propertyCount = properties.length;
					for (int index = 0; index < propertyCount; index++) {
						TableItem tableItem = new TableItem(
								connectionPropertiesTable, SWT.NONE);
						tableItem.setText(new String[] {
								properties[index].getPropertyName(),
								properties[index].getValue() });
					}
				}
			}
		}
	}

	private ConnectionDisplayProperty[] getConnectionDisplayProperties(
			IConnectionProfile connectionProfile) {
		ConnectionDisplayProperty[] properties = new ConnectionDisplayProperty[] {};
		properties = updateConnectionDisplayProperties(connectionProfile,
				properties);
		return properties;
	}

	protected ConnectionDisplayProperty[] updateConnectionDisplayProperties(
			IConnectionProfile connectionProfile,
			ConnectionDisplayProperty[] defaultDisplayProperties) {
		return defaultDisplayProperties;
	}

	/**
	 * Use to determine whether the user has selected an existing connection
	 * 
	 * @return A boolean indicating the user has chosen an existing connection
	 */
	public boolean isExistingConnectionSelected() {
		return existingConnectionRadioButton.getSelection();
	}

	/**
	 * Use to determine whether the user wishes to create a new connection
	 * 
	 * @return A boolean indicating the user has chosen a new connection
	 */
	public boolean isNewConnectionSelected() {
		return newConnectionRadioButton.getSelection();
	}

	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			initializeValues();
			if (this.newConnectionRadioButton.getSelection()) {
				newConnectionRadioButton.setFocus();
			} else {
				existingConnectionRadioButton.setFocus();
			}
		}
	}

	/**
	 * Returns either the existing connection selected by the user or if the
	 * user has indicated they would like to use a new connection then this
	 * method returns null.
	 * 
	 * @return An IConnectionProfile object representing the users selection in
	 *         the list
	 */
	public IConnectionProfile getSelectedConnection() {
		IConnectionProfile connection = null;
		if (existingConnectionRadioButton.getSelection()) {
			connection = (IConnectionProfile) existingConnections
					.get(existingConnectionsList.getSelection()[0]);
		}
		return connection;
	}

	public void setDefaultConnection(String connectionName) {
		if (existingConnectionsList.indexOf(connectionName) > -1) {
			existingConnectionRadioButton.setSelection(true);
			existingConnectionRadioButton.setFocus();
			newConnectionRadioButton.setSelection(false);
			enableExistingConnectionsControls(true);
			existingConnectionsList
					.setSelection(new String[] { connectionName });
			updateConnectionProperties();
		}
	}
}
