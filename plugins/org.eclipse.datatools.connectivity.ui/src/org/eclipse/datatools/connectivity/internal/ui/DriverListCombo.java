/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverMgmtMessages;
import org.eclipse.datatools.connectivity.drivers.DriverValidator;
import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.XMLFileManager;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.DriverDefinitionsDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.ibm.icu.util.StringTokenizer;

/**
 * Composite that provides a drop-down list of available drivers as well as a
 * button to launch the Driver Management dialog to manage those drivers.
 * 
 * @author brianf
 */
public class DriverListCombo {

	// ui pieces
	private Composite mPanel;
	private Combo mComboList;
	private Button mModify;
	private Label mLabel;
	private String mLabelText;
	private String mCategoryId;
	private String mErrorMessage;
	private String mInitialDriverName;
	private boolean mNullDriverValid = true;
	private String mFilter = null;

	// show the label?
	private boolean mShowLabel = true;

	// change listeners
	private ListenerList changeListeners;

	/**
	 * Constructor
	 * 
	 * @param parent
	 * @param style
	 */
	public DriverListCombo() {
		this.changeListeners = new ListenerList();
	}

	/**
	 * Return the error message back to the consumer.
	 * 
	 * @return
	 */
	public String getErrorMessage() {
		return this.mErrorMessage;
	}

	/**
	 * Set the initial driver name to be selected.
	 * 
	 * @param driverName
	 */
	public void setInitialDriverName(String driverName) {
		this.mInitialDriverName = driverName;
	}

	/**
	 * Return the initial driver name to be selected.
	 * 
	 * @return
	 */
	public String getInitialDriverName() {
		return this.mInitialDriverName;
	}

	/**
	 * Set the category to display in the drop-down.
	 * 
	 * @param category
	 */
	public void setCategory(String category) {
		this.mCategoryId = category;
	}

	/**
	 * Return the category.
	 * 
	 * @return
	 */
	public String getCategory() {
		return this.mCategoryId;
	}

	public void setFilter(String filter) {
		this.mFilter = filter;
	}

	public String getFilter() {
		return this.mFilter;
	}

	/**
	 * Indicate whether the default label should be shown or not.
	 * 
	 * @param flag
	 */
	public void setShowLabel(boolean flag) {
		this.mShowLabel = flag;
	}

	/**
	 * Indicate the state of the "show label" flag
	 * 
	 * @return
	 */
	public boolean getShowLabel() {
		return this.mShowLabel;
	}

	/**
	 * Change the text of the label
	 * 
	 * @param label
	 */
	public void setLabelText(String label) {
		this.mLabelText = label;
	}

	/**
	 * Return the text of the label
	 * 
	 * @return
	 */
	public String getLabelText() {
		return this.mLabelText;
	}

	public boolean getNullDriverIsValid() {
		return this.mNullDriverValid;
	}

	public void setNullDriverIsValid(boolean flag) {
		this.mNullDriverValid = flag;
	}

	/**
	 * Create the UI contents
	 * 
	 * @param parent
	 */
	public void createContents(Composite parent) {
		this.mPanel = new Composite(parent, SWT.NULL);

		GridData vdata = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_FILL);
		vdata.horizontalSpan = 2;
		this.mPanel.setLayoutData(vdata);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		this.mPanel.setLayout(gridLayout);

		if (this.mShowLabel) {
			this.mLabel = new Label(this.mPanel, SWT.NONE);
			GridData ldata = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			ldata.horizontalSpan = 2;
			this.mLabel.setLayoutData(ldata);
			this.mLabel.setText(this.mLabelText);
		}

		this.mComboList = new Combo(this.mPanel, SWT.DROP_DOWN | SWT.BORDER
				| SWT.READ_ONLY);
		GridData cdata = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.GRAB_HORIZONTAL);
		this.mComboList.setLayoutData(cdata);

		ComboSelectionListener listener = new ComboSelectionListener(this);

		this.mComboList.addModifyListener(listener);
		this.mComboList.addSelectionListener(listener);

		this.mModify = new Button(this.mPanel, SWT.PUSH);
		this.mModify.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		this.mModify.setText(DriverMgmtMessages
				.getString("DriverListCombo.button.browse")); //$NON-NLS-1$
		this.mModify
				.addSelectionListener(new EditButtonSelectionChangedListener(
						this));

		refreshCombo();
	}

	/**
	 * Return the combo list
	 * 
	 * @return
	 */
	public Combo getCombo() {
		return this.mComboList;
	}

	/**
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

	/**
	 * Parse the list of jars
	 * 
	 * @param str_list
	 * @return
	 */
	protected String[] parseString(String str_list) {
		return parseString(str_list, IDriverMgmtConstants.PATH_DELIMITER);
	}

	protected String[] parseString(String str_list, String token) {
		StringTokenizer tk = new StringTokenizer(str_list, token);
		String[] pieces = new String[tk.countTokens()];
		int index = 0;
		while (tk.hasMoreTokens())
			pieces[index++] = tk.nextToken();
		return pieces;
	}

	/**
	 * Return the instance of the selected driver.
	 * 
	 * @return
	 */
	private IPropertySet getSelectedDriver() {
		int keyIndex = this.mComboList.getSelectionIndex();
		if (keyIndex > -1) {
			String keyString = this.mComboList.getItem(keyIndex);
			if (this.mComboList.getData(keyString) != null) {
				IPropertySet ps = (IPropertySet) this.mComboList
						.getData(keyString);
				return ps;
			}
		}
		return null;
	}

	/**
	 * Return a Driver Instance from the combo.
	 * 
	 * @return
	 */
	public DriverInstance getSelectedDriverInstance() {
		IPropertySet ps = getSelectedDriver();
		if (ps != null)
			return new DriverInstance(ps);
		return null;
	}

	/**
	 * Return the name of the selected driver.
	 * 
	 * @return
	 */
	public String getSelectedDriverName() {
		IPropertySet ps = getSelectedDriver();
		if (ps != null) {
			return ps.getName();
		}
		return null;
	}

	/**
	 * Return the ID of the selected driver.
	 * 
	 * @return
	 */
	public String getSelectedDriverID() {
		IPropertySet ps = getSelectedDriver();
		if (ps != null) {
			return ps.getID();
		}
		return null;
	}

	/**
	 * Set the initial selection
	 * 
	 * @param name
	 */
	public void setSelection(String name) {
		List list = Arrays.asList(this.mComboList.getItems());
		if (list.contains(name)) {
			this.mComboList.select(list.indexOf(name));
			this.mComboList.setText(name);
		}
	}

	public void setSelectionToID(String id) {
		String driverName = null;
		List list = Arrays.asList(this.mComboList.getItems());
		for (int i = 0; i < list.size(); i++) {
			String keyString = DriverListCombo.this.mComboList.getItem(i);
			if (DriverListCombo.this.mComboList.getData(keyString) != null) {
				IPropertySet ps = (IPropertySet) DriverListCombo.this.mComboList
						.getData(keyString);
				if (ps.getID().equals(id)) {
					driverName = keyString;
					break;
				}
			}
		}
		if (driverName != null) {
			this.mComboList.select(list.indexOf(driverName));
			this.mComboList.setText(driverName);
		}
	}

	private boolean passesFilter(TemplateDescriptor template, IPropertySet pset) {
		boolean rtn = true;
		if (this.mFilter != null && template != null && pset != null) {
			String[] filters = parseString(this.mFilter, ","); //$NON-NLS-1$
			for (int i = 0; i < filters.length; i++) {
				String filter = filters[i];
				String args[] = parseString(filter, "="); //$NON-NLS-1$
				String arg = args[0].trim();
				String value = args[1].trim();
				if (arg.equals("id")) { //$NON-NLS-1$
					if (pset.getID().indexOf(value) == -1) {
						rtn = false;
						break;
					}
				}
				else if (arg.equals("name")) { //$NON-NLS-1$
					if (pset.getName().indexOf(value) == -1) {
						rtn = false;
						break;
					}
				}
				else if (arg.equals("templateID")) { //$NON-NLS-1$
					if (template.getId().indexOf(value) == -1) {
						rtn = false;
						break;
					}
				}
				else if (arg.equals("templateIDStartsWith")) { //$NON-NLS-1$
					if (!template.getId().startsWith(value)) {
						rtn = false;
						break;
					}
				}
			}
		}
		return rtn;
	}

	/**
	 * Refresh the combo list
	 */
	public void refreshCombo() {

		getCombo().removeAll();

		IPropertySet[] psets = new IPropertySet[0];
		XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
		try {
			psets = XMLFileManager.loadPropertySets();
		}
		catch (CoreException e) {
			ConnectivityUIPlugin.getDefault().log(e);
			return;
		}

		if (this.mCategoryId != null) {
			CategoryDescriptor category = CategoryDescriptor
					.getCategoryDescriptor(this.mCategoryId);
			List templates = new ArrayList();
			if (category == null) {
				// TODO: log error message
				CategoryDescriptor[] categories = CategoryDescriptor.getRootCategories();
				for (int index = 0, count = categories.length; index < count; ++index) {
					populateAssociatedDriverTypes(categories[index],templates);
				}
			}
			else {
				populateAssociatedDriverTypes(category,templates);
			}
			Iterator iter = templates.iterator();
			while (iter.hasNext()) {
				TemplateDescriptor template = (TemplateDescriptor) iter.next();
				for (int i = 0; i < psets.length; i++) {
					IPropertySet pset = psets[i];
					String driverType = pset.getBaseProperties().getProperty(
							IDriverMgmtConstants.PROP_DEFN_TYPE); //$NON-NLS-1$
					if (driverType.equals(template.getId())) {
						if (passesFilter(template, pset)) {
							getCombo().add(pset.getName());
							getCombo().setData(pset.getName(), pset);
						}
					}
				}
			}
		}
		else {
			for (int i = 0; i < psets.length; i++) {
				IPropertySet pset = psets[i];
				getCombo().add(pset.getName());
				getCombo().setData(pset.getName(), pset);
			}
		}

		String text = getCombo().getText();
		for (int i = 0; i < getCombo().getItemCount(); i++) {
			if (getCombo().getItem(i).equals(text)) {
				getCombo().select(i);
				break;
			}
		}
	}
	
	private void populateAssociatedDriverTypes(CategoryDescriptor category,List templates) {
		templates.addAll(category.getAssociatedDriverTypes());
		for (Iterator it = category.getChildCategories().iterator(); it.hasNext();) {
			populateAssociatedDriverTypes((CategoryDescriptor)it.next(),templates);
		}
	}

	/**
	 * Listener for combo selection events
	 * 
	 * @author brianf
	 */
	private class ComboSelectionListener implements SelectionListener,
			ModifyListener {

		private DriverListCombo parent;

		public ComboSelectionListener(DriverListCombo combo) {
			this.parent = combo;
		}

		public void widgetSelected(SelectionEvent e) {
			int keyIndex = DriverListCombo.this.mComboList.getSelectionIndex();
			if (keyIndex > -1) {
				String keyString = DriverListCombo.this.mComboList
						.getItem(keyIndex);
				if (DriverListCombo.this.mComboList.getData(keyString) != null) {
					IPropertySet ps = (IPropertySet) DriverListCombo.this.mComboList
							.getData(keyString);
					if (ps != null) {
						String driverType = ps.getBaseProperties().getProperty(
								IDriverMgmtConstants.PROP_DEFN_TYPE); //$NON-NLS-1$
						if (driverType != null) {
							TemplateDescriptor template = TemplateDescriptor
									.getDriverTemplateDescriptor(driverType);
							if (template != null) {
								DriverValidator validator = new DriverValidator(
										template, ps);
								DriverListCombo.this.mErrorMessage = null;
								if (!validator.isValid()) {
									DriverListCombo.this.mErrorMessage = validator
											.getMessage();
								}
							}
						}
					}
				}
			}
			else {
				if (DriverListCombo.this.mNullDriverValid)
					DriverListCombo.this.mErrorMessage = null;
				else
					DriverListCombo.this.mErrorMessage = DriverMgmtMessages
							.getString("DriverValidator.msg.no_driver_selected"); //$NON-NLS-1$
			}

			fireChangedEvent(this.parent);
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
		 */
		public void modifyText(ModifyEvent e) {
			String keyString = DriverListCombo.this.mComboList.getText();
			if (keyString.length() > 0) {
				if (DriverListCombo.this.mComboList.getData(keyString) != null) {
					IPropertySet ps = (IPropertySet) DriverListCombo.this.mComboList
							.getData(keyString);
					if (ps != null) {
						String driverType = ps.getBaseProperties().getProperty(
								IDriverMgmtConstants.PROP_DEFN_TYPE); //$NON-NLS-1$
						if (driverType != null) {
							TemplateDescriptor template = TemplateDescriptor
									.getDriverTemplateDescriptor(driverType);
							if (template != null) {
								DriverValidator validator = new DriverValidator(
										template, ps);
								DriverListCombo.this.mErrorMessage = null;
								if (!validator.isValid()) {
									DriverListCombo.this.mErrorMessage = validator
											.getMessage();
								}
							}
						}
					}
				}
			}
			else {
				if (DriverListCombo.this.mNullDriverValid)
					DriverListCombo.this.mErrorMessage = null;
				else
					DriverListCombo.this.mErrorMessage = DriverMgmtMessages
							.getString("DriverValidator.msg.no_driver_selected"); //$NON-NLS-1$
			}
			fireChangedEvent(this.parent);
		}

	}

	/**
	 * Listener for edit button clicks events
	 * 
	 * @author brianf
	 */
	public class EditButtonSelectionChangedListener implements
			SelectionListener {

		private DriverListCombo parent;

		public EditButtonSelectionChangedListener(DriverListCombo combo) {
			this.parent = combo;
		}

		public void widgetSelected(SelectionEvent e) {
			Shell newShell = new Shell();
			DriverDefinitionsDialog dlg;
			if (DriverListCombo.this.mCategoryId != null) {
				dlg = new DriverDefinitionsDialog(newShell,
						DriverListCombo.this.mCategoryId);
			}
			else {
				dlg = new DriverDefinitionsDialog(newShell);
			}
			if (this.parent.getCombo().getText() != null
					&& this.parent.getCombo().getText().length() > 0)
				dlg.setInitialDriverName(this.parent.getCombo().getText());

			int rtn = dlg.open();
			if (rtn != Window.OK)
				return;

			String tempStore = DriverListCombo.this.mComboList.getText();

			refreshCombo();

			boolean fireEvent = false;
			if (dlg.getSelectedDefinition() != null) {
				fireEvent = true;
				String driverName = dlg.getSelectedDefinition().getName();
				String[] itemList = DriverListCombo.this.mComboList.getItems();
				if (itemList.length > 0) {
					for (int i = 0; i < itemList.length; i++) {
						String item = itemList[i];
						IPropertySet temp = (IPropertySet) DriverListCombo.this.mComboList
								.getData(item);
						if (temp.getID().equals(
								dlg.getSelectedDefinition().getID())) {
							DriverListCombo.this.mComboList.setText(driverName);
							DriverListCombo.this.mComboList.select(i);

							String driverType = temp
									.getBaseProperties()
									.getProperty(
											IDriverMgmtConstants.PROP_DEFN_TYPE); //$NON-NLS-1$
							if (driverType != null) {
								TemplateDescriptor template = TemplateDescriptor
										.getDriverTemplateDescriptor(driverType);
								if (template != null) {
									DriverValidator validator = new DriverValidator(
											template, temp);
									DriverListCombo.this.mErrorMessage = null;
									if (!validator.isValid()) {
										DriverListCombo.this.mErrorMessage = validator
												.getMessage();
									}
								}
							}
							break;
						}
					}
				}
			}
			else
				DriverListCombo.this.mComboList.setText(tempStore);

			newShell.dispose();

			if (fireEvent)
				fireChangedEvent(this.parent);
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}

	}

}
