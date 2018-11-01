/*******************************************************************************
 * Copyright (c) 2001, 2004, 2007, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.ui.dse.dialogs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.ui.ProfileImageRegistry;
import org.eclipse.datatools.connectivity.ui.actions.AddProfileViewAction;
import org.eclipse.datatools.connectivity.ui.actions.DeleteAction;
import org.eclipse.datatools.connectivity.ui.actions.ViewPropertyAction;
import org.eclipse.datatools.connectivity.ui.dse.DSEPlugin;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

public class ExistingConnectionProfilesDialogPage extends DialogPage implements
		Listener {
	private Button newConnectionButton;

	private Button editConnectionButton;

	private Button deleteConnectionButton;

	private Group existingConnectionsGroup;

	private TreeViewer existingConnectionsList;

	private Table connectionPropertiesTable;

	private boolean isShowProperties;

	private boolean isPropertiesSectionExpanded;

	protected ExistingConnectionProfilesDialogPage(boolean isShowProperties,
			boolean isPropertiesSectionExpanded) {
		super();
		this.isShowProperties = isShowProperties;
		this.isPropertiesSectionExpanded = isPropertiesSectionExpanded;
	}

	protected ExistingConnectionProfilesDialogPage(String title,
			boolean isShowProperties, boolean isPropertiesSectionExpanded) {
		super(title);
		this.isShowProperties = isShowProperties;
		this.isPropertiesSectionExpanded = isPropertiesSectionExpanded;
	}

	protected ExistingConnectionProfilesDialogPage(String title,
			ImageDescriptor image, boolean isShowProperties,
			boolean isPropertiesSectionExpanded) {
		super(title, image);
		this.isShowProperties = isShowProperties;
		this.isPropertiesSectionExpanded = isPropertiesSectionExpanded;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		existingConnectionsGroup = new Group(composite, SWT.NONE);
		existingConnectionsGroup
				.setText(DSEPlugin
						.getDefault()
						.getResourceString(
								"ExistingConnectionsDialogPage.ExistingConnections.group")); //$NON-NLS-1$
		layout = new GridLayout();
		layout.horizontalSpacing = 0;
		existingConnectionsGroup.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		existingConnectionsGroup.setLayoutData(gd);

		Composite existingListComposite = new Composite(
				existingConnectionsGroup, SWT.NONE);
		layout = new GridLayout();
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = false;
		layout.verticalSpacing = 0;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		existingListComposite.setLayout(layout);
		existingListComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		existingConnectionsList = new TreeViewer(existingListComposite,
				SWT.BORDER | SWT.V_SCROLL);
		gd = new GridData(GridData.FILL_BOTH);
		existingConnectionsList.getTree().setLayoutData(gd);

		Composite buttonComposite = new Composite(existingListComposite,
				SWT.NONE);
		layout = new GridLayout();
		layout.horizontalSpacing = 0;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		buttonComposite.setLayout(layout);
		gd = new GridData();
		gd.horizontalAlignment = SWT.END;
		gd.verticalAlignment = SWT.TOP;
		buttonComposite.setLayoutData(gd);

		newConnectionButton = new Button(buttonComposite, SWT.NONE);
		newConnectionButton.setText(DSEPlugin.getDefault().getResourceString(
				"ExistingConnectionsDialogPage.Button.new")); //$NON-NLS-1$
		gd = new GridData();
		gd.widthHint = 80;
		gd.horizontalAlignment = SWT.END;
		gd.verticalAlignment = SWT.TOP;
		newConnectionButton.setLayoutData(gd);

		editConnectionButton = new Button(buttonComposite, SWT.NONE);
		editConnectionButton.setText(DSEPlugin.getDefault().getResourceString(
				"ExistingConnectionsDialogPage.Button.edit")); //$NON-NLS-1$
		gd = new GridData();
		gd.widthHint = 80;
		gd.horizontalAlignment = SWT.END;
		gd.verticalAlignment = SWT.TOP;
		editConnectionButton.setLayoutData(gd);

		deleteConnectionButton = new Button(buttonComposite, SWT.NONE);
		deleteConnectionButton.setText(DSEPlugin.getDefault()
				.getResourceString(
						"ExistingConnectionsDialogPage.Button.delete")); //$NON-NLS-1$
		gd = new GridData();
		gd.widthHint = 80;
		gd.horizontalAlignment = SWT.END;
		gd.verticalAlignment = SWT.TOP;
		deleteConnectionButton.setLayoutData(gd);

		if (isShowProperties) {
			ExpandableComposite expandableComposite = new ExpandableComposite(
					existingConnectionsGroup,
					ExpandableComposite.SHORT_TITLE_BAR
							| ExpandableComposite.TWISTIE);
			expandableComposite.setText(DSEPlugin.getDefault()
					.getResourceString(
							"ExistingConnectionsDialogPage.Properties.label"));
			expandableComposite.setExpanded(isPropertiesSectionExpanded);
			layout = new GridLayout();
			expandableComposite.setLayout(layout);
			gd = new GridData(GridData.FILL_BOTH);
			gd.minimumHeight = 170;
			expandableComposite.setLayoutData(gd);
			if (expandableComposite.getVerticalBar() != null) {
				expandableComposite.getVerticalBar().setVisible(false);
			}
			if (expandableComposite.getHorizontalBar() != null) {
				expandableComposite.getHorizontalBar().setVisible(false);
			}

			connectionPropertiesTable = new Table(expandableComposite,
					SWT.BORDER);
			gd = new GridData(GridData.FILL_BOTH);
			connectionPropertiesTable.setLayoutData(gd);
			connectionPropertiesTable.setLinesVisible(true);
			connectionPropertiesTable.setHeaderVisible(true);

			expandableComposite.setClient(connectionPropertiesTable);

			TableColumn tc1 = new TableColumn(connectionPropertiesTable,
					SWT.NONE);
			tc1.setText(DSEPlugin.getDefault().getResourceString(
					"ExistingConnectionsDialogPage.Property.columnHeader")); //$NON-NLS-1$
			tc1.setResizable(true);
			tc1.setWidth(140);

			TableColumn tc2 = new TableColumn(connectionPropertiesTable,
					SWT.NONE);
			tc2.setText(DSEPlugin.getDefault().getResourceString(
					"ExistingConnectionsDialogPage.Value.columnHeader")); //$NON-NLS-1$
			tc2.setResizable(true);
			tc2.setWidth(250);
		}
		initializeDialogUnits(composite);

		newConnectionButton.addListener(SWT.Selection, this);
		editConnectionButton.addListener(SWT.Selection, this);
		deleteConnectionButton.addListener(SWT.Selection, this);
		existingConnectionsList.getTree().addListener(SWT.Selection, this);

		initializeValues();
	}

	public void handleEvent(Event event) {
		Widget source = event.widget;
		if (source == newConnectionButton) {
			newConnection();
		} else if (source == editConnectionButton) {
			editConnection();
		} else if (source == deleteConnectionButton) {
			deleteConnection();
		} else if (source == existingConnectionsList.getTree()) {
			updateConnectionProperties();
			updateButtonEnablement();
		}
	}

	/**
	 * Returns the list of existing connections to display to the user. Override
	 * this method to provide a filtered list of connections.
	 * 
	 * @return An array of IConnectionProfile objects that should be displayed
	 *         in the existing connections list
	 */
	protected IConnectionProfile[] getConnectionsToDisplay() {
		return ProfileManager.getInstance().getProfiles(false);
	}

	protected void initializeValues() {
		existingConnectionsList.getTree().removeAll();
		IConnectionProfile[] connectionProfiles = getConnectionsToDisplay();
		if (connectionProfiles != null) {
			Hashtable existingConnections = new Hashtable();
			Iterator connections = Arrays.asList(connectionProfiles).iterator();
			IConnectionProfile connection;
			while (connections.hasNext()) {
				connection = (IConnectionProfile) connections.next();
				existingConnections.put(connection.getName(), connection);
			}
			Object[] sortedNames = this.sortItems(existingConnections.keySet()
					.toArray());
			for (int index = 0; index < sortedNames.length; index++) {
				String name = (String) sortedNames[index];
				IConnectionProfile profile = (IConnectionProfile) existingConnections
						.get(name);
				TreeItem item = new TreeItem(existingConnectionsList.getTree(),
						SWT.NONE);
				item.setText(name);
				item.setData(existingConnections.get(name));
				item.setImage(ProfileImageRegistry.getInstance()
						.getProfileImage(profile.getProvider()));
			}
		}
		updateConnectionProperties();
		updateButtonEnablement();
	}

	private Object[] sortItems(Object[] names) {
		Arrays.sort(names, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				int result = -1;
				if ((arg0 != null) && (arg1 != null)) {
					result = ((String) arg0).compareToIgnoreCase((String) arg1);
				}
				return result;
			}
		});
		return names;
	}

	private void updateButtonEnablement() {
		if (existingConnectionsList.getTree().getSelectionCount() > 0) {
			editConnectionButton.setEnabled(true);
			deleteConnectionButton.setEnabled(true);
		} else {
			editConnectionButton.setEnabled(false);
			deleteConnectionButton.setEnabled(false);
		}
	}

	private void updateConnectionProperties() {
		if (connectionPropertiesTable != null) {
			connectionPropertiesTable.removeAll();
			if (existingConnectionsList.getTree().getSelectionCount() > 0) {
				IConnectionProfile selectedConnection = (IConnectionProfile) existingConnectionsList
						.getTree().getSelection()[0].getData();
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
	}

	private ConnectionDisplayProperty[] getConnectionDisplayProperties(
			IConnectionProfile connectionProfile) {
		ConnectionDisplayProperty[] properties = null;
		Vector propertiesCollection = new Vector();

		propertiesCollection.add(new ConnectionDisplayProperty(DSEPlugin
				.getDefault().getResourceString(
						"ExistingConnectionsDialogPage.PropertyName.name"), //$NON-NLS-1$
				connectionProfile.getName()));
		propertiesCollection
				.add(new ConnectionDisplayProperty(
						DSEPlugin
								.getDefault()
								.getResourceString(
										"ExistingConnectionsDialogPage.PropertyName.description"), //$NON-NLS-1$
						connectionProfile.getDescription()));
		propertiesCollection.add(new ConnectionDisplayProperty(DSEPlugin
				.getDefault().getResourceString(
						"ExistingConnectionsDialogPage.PropertyName.category"), //$NON-NLS-1$
				connectionProfile.getCategory().getName()));
		properties = new ConnectionDisplayProperty[propertiesCollection.size()];
		propertiesCollection.toArray(properties);

		properties = updateConnectionDisplayProperties(connectionProfile,
				properties);
		return properties;
	}

	/**
	 * Returns an array of connection properties to display. Override this
	 * method to provide a custom set of properties.
	 * 
	 * @return An array of IConnectionProfile objects that should be displayed
	 *         in the existing connections list
	 */
	protected ConnectionDisplayProperty[] updateConnectionDisplayProperties(
			IConnectionProfile connectionProfile,
			ConnectionDisplayProperty[] defaultDisplayProperties) {
		return defaultDisplayProperties;
	}

	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			initializeValues();
			existingConnectionsList.getTree().setFocus();
		}
	}

	/**
	 * Returns either the existing connection selected by the user or null.
	 * 
	 * @return An IConnectionProfile object representing the users selection in
	 *         the list
	 */
	public IConnectionProfile getSelectedConnection() {
		IConnectionProfile connection = null;

		if (existingConnectionsList.getTree().getSelectionCount() > 0) {
			connection = (IConnectionProfile) existingConnectionsList.getTree()
					.getSelection()[0].getData();
		}
		return connection;
	}

	/**
	 * Sets the selection in the list of connections.
	 */
	public void setDefaultConnection(String connectionName) {
		TreeItem[] items = existingConnectionsList.getTree().getItems();
		TreeItem foundItem = findTreeItemByName(items, connectionName);
		if (foundItem != null) {
			existingConnectionsList.getTree().setSelection(foundItem);
			updateConnectionProperties();
		}
	}

	private TreeItem findTreeItemByName(TreeItem[] items, String name) {
		TreeItem result = null;
		for (int index = 0; index < items.length; index++) {
			if (name.equals(items[index].getText())) {
				result = items[index];
				break;
			}
		}
		return result;
	}

	protected void newConnection() {
		AddProfileViewAction newConnectionProfileAction = new AddProfileViewAction(
				(String) null);
		newConnectionProfileAction.selectionChanged(null, null);
		newConnectionProfileAction.run(null);
		this.initializeValues();
	}

	private void editConnection() {
		ViewPropertyAction editConnectionProfileAction = new ViewPropertyAction(
				existingConnectionsList);
		editConnectionProfileAction.run();
		this.initializeValues();
	}

	private void deleteConnection() {
		DeleteAction deleteConnectionProfileAction = new DeleteAction();
		deleteConnectionProfileAction.selectionChanged(
				deleteConnectionProfileAction, existingConnectionsList
						.getSelection());
		deleteConnectionProfileAction.run();
		this.initializeValues();
	}
}
