/*******************************************************************************
 * Copyright (c) 2004, 2010 Sybase, Inc. and others
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 *         IBM Corporation - defect fix #213266
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.drivers.DriverMgmtMessages;
import org.eclipse.datatools.connectivity.drivers.DriverValidator;
import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.PropertySetImpl;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.DriverDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.plugin.AbstractUIPlugin;

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
	private Label mLabel;
	private String mLabelText;
	private String mCategoryId;
	private String mErrorMessage;
	private String mInitialDriverName;
	private boolean mNullDriverValid = true;
	private String mFilter = null;
	private boolean isReadOnly = false;

	// show the label?
	private boolean mShowLabel = true;

	// change listeners
	private ListenerList changeListeners;

	private Image mDriverImage = null;
	private Image mDriverWithPlusImage = null;
	private Image mChangeImage = null;

	private static ImageDescriptor PLUS = null;

	private static ImageDescriptor ARROW = null;

	private static ImageDescriptor CHANGE = null;

	private Image mArrowImage = null;

	// show the new driver button?
	private boolean mShowNewDriverButton = true;
	private boolean mShowGenericDriverButton = false;
	private boolean mShowMenu = false;
	private boolean mShowEditButton = true;
	private ToolItem mTBButtonEdit;
	private ToolItem item1;
	private ToolItem item2;
	private ToolItem item3;

	/**
	 * Constructor
	 * 
	 * @param parent
	 * @param style
	 */
	public DriverListCombo() {
		this(false);
	}
	
	public DriverListCombo(boolean isReadOnly) {
		this.changeListeners = new ListenerList();
		this.isReadOnly = isReadOnly;
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
	public void setShowNewDriverButton(boolean flag) {
		this.mShowNewDriverButton = flag;
	}
	public void setShowGenericDriverButton(boolean flag) {
		this.mShowGenericDriverButton = flag;
	}
	public void setShowMenu(boolean flag) {
		this.mShowMenu = flag;
	}
	public void setShowEditButton(boolean flag) {
		this.mShowEditButton = flag;
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
		makeImages();
		this.mPanel = new Composite(parent, SWT.NULL);

		GridData vdata = new GridData(GridData.FILL_HORIZONTAL);
		this.mPanel.setLayoutData(vdata);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		this.mPanel.setLayout(gridLayout);

		if (this.mShowLabel) {
			this.mLabel = new Label(this.mPanel, SWT.NONE);
			GridData ldata = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			ldata.horizontalSpan = 1;
			this.mLabel.setLayoutData(ldata);
			this.mLabel.setText(this.mLabelText);
		}

		this.mComboList = new Combo(this.mPanel, SWT.DROP_DOWN | SWT.BORDER
				| SWT.READ_ONLY);
		this.mComboList.setEnabled(!isReadOnly);
		GridData cdata = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
		cdata.widthHint = 20;
		this.mComboList.setLayoutData(cdata);

		ComboSelectionListener listener = new ComboSelectionListener(this);

		this.mComboList.addModifyListener(listener);
		this.mComboList.addSelectionListener(listener);
		
		Composite tempComposite = new Composite(this.mPanel, SWT.NONE);
		tempComposite.setLayout(new FillLayout());

		if (mShowNewDriverButton) {
		    final ToolBar toolBar = new ToolBar(tempComposite/*this.mPanel*/, SWT.FLAT);
		    /*final ToolItem*/ item1 = new ToolItem(toolBar, SWT.PUSH);
		    item1.setImage(mDriverWithPlusImage);
		    item1.setToolTipText(DriverMgmtMessages.getString("DriverListCombo.button.newdriver")); //$NON-NLS-1$
			item1
				.addSelectionListener(new NewButtonSelectionChangedListener(
					this));
			item1.setEnabled(!isReadOnly);
			toolBar.getAccessible().addAccessibleListener(
					new AccessibleAdapter() {			
						public void getName(AccessibleEvent e) {
							e.result = DriverMgmtMessages.getString("DriverListCombo.button.newdriver"); //$NON-NLS-1$
						}
					}
			);		
		}
		
		if (mShowGenericDriverButton) {
		    final ToolBar toolBar = new ToolBar(tempComposite/*this.mPanel*/, SWT.FLAT);
		    /*final ToolItem*/ item2 = new ToolItem(toolBar, SWT.PUSH);
		    item2.setImage(mDriverImage);
		    item2.setToolTipText(DriverMgmtMessages.getString("DriverListCombo.button.generic")); //$NON-NLS-1$
			item2.
				addSelectionListener(new NewGenericSelectionChangedListener(
						this));
			item2.setEnabled(!isReadOnly);
			toolBar.getAccessible().addAccessibleListener(
					new AccessibleAdapter() {			
						public void getName(AccessibleEvent e) {
							e.result = DriverMgmtMessages.getString("DriverListCombo.button.generic"); //$NON-NLS-1$
						}
					}
			);		
		}
		
		if (mShowEditButton) {
		    final ToolBar toolBar = new ToolBar(tempComposite/*this.mPanel*/, SWT.FLAT);
			mTBButtonEdit = new ToolItem(toolBar, SWT.PUSH);
			mTBButtonEdit.setImage(mChangeImage);
			mTBButtonEdit.setToolTipText(DriverMgmtMessages.getString("DriverListCombo.EditDriverButton.tooltip")); //$NON-NLS-1$
			mTBButtonEdit.
				addSelectionListener(new EditButtonSelectionChangedListener(
					this));
			toolBar.getAccessible().addAccessibleListener(
					new AccessibleAdapter() {			
						public void getName(AccessibleEvent e) {
							e.result = DriverMgmtMessages.getString("DriverListCombo.EditDriverButton.tooltip"); //$NON-NLS-1$
						}
					}
			);		
		}

		if (mShowMenu) {
		    final ToolBar toolBar = new ToolBar(tempComposite/*this.mPanel*/, SWT.FLAT);
			final Menu menu = new Menu (this.mPanel.getShell(), SWT.POP_UP);
			MenuItem mitem1 = new MenuItem(menu, SWT.PUSH);
			mitem1.setText(DriverMgmtMessages.getString("DriverListCombo.button.newdriver")); //$NON-NLS-1$
			mitem1.addSelectionListener(new NewButtonSelectionChangedListener(this));
			MenuItem mitem2 = new MenuItem(menu, SWT.PUSH);
			mitem2.setText(DriverMgmtMessages.getString("DriverListCombo.button.generic")); //$NON-NLS-1$
			mitem2.addSelectionListener(new NewGenericSelectionChangedListener(this));

			/*final ToolItem*/ item3 = new ToolItem(toolBar, SWT.PUSH );
			item3.setImage(mArrowImage);
		    item3.addListener (SWT.Selection, new Listener () {
		        public void handleEvent (org.eclipse.swt.widgets.Event event) {
	                Rectangle rect = item3.getBounds ();
	                Point pt = new Point (rect.x, rect.y + rect.height);
	                pt = toolBar.toDisplay (pt);
	                menu.setLocation (pt.x, pt.y);
	                menu.setVisible (true);
		        }
		    });
		    item3.setEnabled(!isReadOnly);
		}
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
	
	public void selectFirstItem() {
		if (this.mComboList.getItemCount() > 0) {
			this.mComboList.select(0);
		}
		else {
			if (DriverListCombo.this.mNullDriverValid)
				DriverListCombo.this.mErrorMessage = null;
			else
				DriverListCombo.this.mErrorMessage = DriverMgmtMessages
						.getString("DriverValidator.msg.no_driver_selected"); //$NON-NLS-1$
		}

		fireChangedEvent(this.mComboList.getParent());
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
				else if (arg.equals("templateIDEndsWith")) { //$NON-NLS-1$
					if (!template.getId().endsWith(value)) {
						rtn = false;
						break;
					}
				}
				else if (arg.equals("templateIDContains")) { //$NON-NLS-1$
					if (template.getId().indexOf(value) == -1) {
						rtn = false;
						break;
					}
				}
			}
		}
		return rtn;
	}

	private class TemplateDescriptorComparator implements Comparator {

		public int compare(Object arg0, Object arg1) {
			TemplateDescriptor td1 = (TemplateDescriptor) arg0;
			TemplateDescriptor td2 = (TemplateDescriptor) arg1;
			return td1.getName().compareToIgnoreCase(td2.getName());
		}
		
	}
	
	private class PropertySetComparator implements Comparator {

		public int compare(Object arg0, Object arg1) {
			IPropertySet ps1 = (IPropertySet) arg0;
			IPropertySet ps2 = (IPropertySet) arg1;
			return ps1.getName().compareToIgnoreCase(ps2.getName());
		}
		
	}

	/**
	 * Refresh the combo list
	 */
	public void refreshCombo() {

		getCombo().removeAll();

		IPropertySet[] psets = new IPropertySet[0];
		IPropertySet[] comboPsets = new IPropertySet[0];
		DriverInstance[] dilist = DriverManager.getInstance().getAllDriverInstances();
		if (dilist != null && dilist.length > 0) {
			psets = new IPropertySet[dilist.length];
			for (int i = 0; i< psets.length; i++) {
				psets[i] = dilist[i].getPropertySet();
			}
		}

		if (this.mCategoryId != null) {
			CategoryDescriptor category = CategoryDescriptor
					.getCategoryDescriptor(this.mCategoryId);
			List templates = new ArrayList();
			if (category == null) {
				CategoryDescriptor[] categories = CategoryDescriptor.getRootCategories();
				for (int index = 0, count = categories.length; index < count; ++index) {
					populateAssociatedDriverTypes(categories[index],templates);
				}
			}
			else {
				populateAssociatedDriverTypes(category,templates);
			}
			TemplateDescriptorComparator comparator = 
				new TemplateDescriptorComparator();
			Object[] templatesArray = templates.toArray();
			Arrays.sort(templatesArray, comparator);
			Arrays.sort(psets, new PropertySetComparator());
			
			ArrayList listForCombo = new ArrayList();
			for (int i = 0; i < templatesArray.length; i++)  {
				TemplateDescriptor template = (TemplateDescriptor) templatesArray[i];
				for (int j = 0; j < psets.length; j++) {
					IPropertySet pset = psets[j];
					String driverType = pset.getBaseProperties().getProperty(
							IDriverMgmtConstants.PROP_DEFN_TYPE); //$NON-NLS-1$
					if (driverType.equals(template.getId())) {
						if (passesFilter(template, pset)) {
							listForCombo.add(pset);
						}
					}
				}
			}
			comboPsets = (IPropertySet[]) listForCombo.toArray(new IPropertySet[listForCombo.size()]);
		}
		else {
			comboPsets = psets;
		}
		
		if (comboPsets != null && comboPsets.length > 0) {
			PropertySetComparator comparator = 
				new PropertySetComparator();
			Arrays.sort(comboPsets, comparator);
			for (int i = 0; i < comboPsets.length; i++) {
				IPropertySet pset = comboPsets[i];
				getCombo().add(pset.getName());
				getCombo().setData(pset.getName(), pset);
			}
		}

		if (mTBButtonEdit != null) // && !isReadOnly)
			mTBButtonEdit.setEnabled(false);
		String text = getCombo().getText();
		if (text != null && text.trim().length() > 0) {
			for (int i = 0; i < getCombo().getItemCount(); i++) {
				if (getCombo().getItem(i).equals(text)) {
					getCombo().select(i);
					break;
				}
			}
		}
		else {
			if (getCombo().getItemCount() > 0) { // && !isReadOnly) {
				if (mTBButtonEdit != null){				
					mTBButtonEdit.setEnabled(true);
				}
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
			if (keyIndex == -1 && mTBButtonEdit != null) // && !isReadOnly)
				mTBButtonEdit.setEnabled(false);
			else if (keyIndex > -1 && mTBButtonEdit != null) // && !isReadOnly)
				mTBButtonEdit.setEnabled(true);
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
								if (!validator.isValid(false)) {
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
								if (!validator.isValid(false)) {
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
	public class NewButtonSelectionChangedListener implements
			SelectionListener {

		private DriverListCombo parent;

		public NewButtonSelectionChangedListener(DriverListCombo combo) {
			this.parent = combo;
		}

		public void widgetSelected(SelectionEvent e) {
			Shell newShell = parent.getCombo().getShell();
			DriverDialog dlg;
			if (DriverListCombo.this.mCategoryId != null) {
				dlg = new DriverDialog(newShell,
						DriverListCombo.this.mCategoryId);
			}
			else {
				dlg = new DriverDialog(newShell);
			}

			int rtn = dlg.open();
			if (rtn != Window.OK)
				return;

			String tempStore = DriverListCombo.this.mComboList.getText();
			
			if (dlg.getPropertySet() != null) {
				DriverManager.getInstance().addDriverInstance(dlg.getPropertySet());
//				saveChanges();
			}
			
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
									if (!validator.isValid(false)) {
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

			int keyIndex = DriverListCombo.this.mComboList.getSelectionIndex();
			if (keyIndex == -1 && mTBButtonEdit != null) // && !isReadOnly)
				mTBButtonEdit.setEnabled(false);
			else if (keyIndex > -1 && mTBButtonEdit != null) // && !isReadOnly)
				mTBButtonEdit.setEnabled(true);
			
			if (fireEvent)
				fireChangedEvent(this.parent);
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}
	}

	public class NewGenericSelectionChangedListener implements
		SelectionListener {

		private DriverListCombo parent;

		public NewGenericSelectionChangedListener(DriverListCombo combo) {
			this.parent = combo;
		}

		public void widgetSelected(SelectionEvent e) {
			Shell newShell = parent.getCombo().getShell();
			DriverDialog dlg;
			if (DriverListCombo.this.mCategoryId != null) {
				dlg = new DriverDialog(newShell,
						DriverListCombo.this.mCategoryId);
			}
			else {
				dlg = new DriverDialog(newShell);
			}
			dlg.setHideDriverList(true);
			dlg.setInitialTemplate("org.eclipse.datatools.connectivity.db.generic.genericDriverTemplate"); //$NON-NLS-1$

			int rtn = dlg.open();
			if (rtn != Window.OK)
				return;

			String tempStore = DriverListCombo.this.mComboList.getText();

			if (dlg.getPropertySet() != null) {
				DriverManager.getInstance().addDriverInstance(dlg.getPropertySet());
//				saveChanges();
			}

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
									if (!validator.isValid(false)) {
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

			int keyIndex = DriverListCombo.this.mComboList.getSelectionIndex();
			if (keyIndex == -1 && mTBButtonEdit != null) // && !isReadOnly)
				mTBButtonEdit.setEnabled(false);
			else if (keyIndex > -1 && mTBButtonEdit != null) // && !isReadOnly)
				mTBButtonEdit.setEnabled(true);

			if (fireEvent)
				fireChangedEvent(this.parent);
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
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
			Shell newShell = parent.getCombo().getShell();
			DriverDialog dlg;
			if (DriverListCombo.this.mCategoryId != null) {
				dlg = new DriverDialog(newShell,
						DriverListCombo.this.mCategoryId);
			}
			else {
				dlg = new DriverDialog(newShell);
			}
			dlg.setIsEditable(!isReadOnly);
			if (parent.getSelectedDriver() == null) 
				return;
			
			IPropertySet copy = duplicatePropertySet(parent.getSelectedDriver());
			dlg.setPropertySet(copy);
			dlg.setEditMode(true);
			
			int rtn = dlg.open();
			if (rtn != Window.OK)
				return;

			copy = dlg.getPropertySet();
			copyPropertySet(copy, parent.getSelectedDriver());
			DriverManager.getInstance().removeDriverInstance(parent.getSelectedDriver().getID());
			
			/*
			 * This call to garbage collect is to try and reclaim
			 * the classloader held by the last instance of the 
			 * DriverInstance that is being dropped and re-added.
			 * Note that if the class is in use (i.e. any profile
			 * is connected that uses the referenced driver), it 
			 * won't be unloaded and subsequent connections will 
			 * fail.
			 */
			System.gc();
			
			DriverManager.getInstance().addDriverInstance(copy);

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
									if (!validator.isValid(false)) {
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
				DriverListCombo.this.mComboList.setText(copy.getName());

			int keyIndex = DriverListCombo.this.mComboList.getSelectionIndex();
			if (keyIndex == -1 && mTBButtonEdit != null) // && !isReadOnly)
				mTBButtonEdit.setEnabled(false);
			else if (keyIndex > -1 && mTBButtonEdit != null) // && !isReadOnly)
				mTBButtonEdit.setEnabled(true);

			if (fireEvent)
				fireChangedEvent(this.parent);
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}
	}

	public void dispose() {
		if (this.mDriverImage != null)
			this.mDriverImage.dispose();
		if (this.mDriverWithPlusImage != null)
			this.mDriverWithPlusImage.dispose();
		if (this.mChangeImage != null)
			this.mChangeImage.dispose();
		if (this.mArrowImage != null)
			this.mArrowImage.dispose();
	}
	
	private void makeImages() {
		mDriverImage = DriverImages.DRIVER.createImage();

		PLUS = AbstractUIPlugin
			.imageDescriptorFromPlugin(ConnectivityUIPlugin.getDefault()
				.getBundle().getSymbolicName(), "icons/add_obj2.gif"); //$NON-NLS-1$

		DecorationOverlayIcon icon = new DecorationOverlayIcon(mDriverImage, PLUS, IDecoration.TOP_RIGHT);
		mDriverWithPlusImage = icon.createImage();

		ARROW = AbstractUIPlugin
			.imageDescriptorFromPlugin(ConnectivityUIPlugin.getDefault()
					.getBundle().getSymbolicName(), "icons/view_menu.gif"); //$NON-NLS-1$
		
		mArrowImage = ARROW.createImage();

		CHANGE = AbstractUIPlugin
			.imageDescriptorFromPlugin(ConnectivityUIPlugin.getDefault()
					.getBundle().getSymbolicName(), "icons/change_obj.gif"); //$NON-NLS-1$
		
		mChangeImage = CHANGE.createImage();
	}

	private IPropertySet duplicatePropertySet ( IPropertySet pset ) {
		IPropertySet newPset = new PropertySetImpl(pset.getName(), pset.getID());
		if (pset.getBaseProperties().size() > 0) {
			Properties newProps = new Properties();
			newPset.setBaseProperties(newProps);
			newPset.getBaseProperties().putAll(pset.getBaseProperties());
		}
		return newPset;
	}

	private void copyPropertySet ( IPropertySet fromPset, IPropertySet topset ) {
		topset.setID(fromPset.getID());
		topset.setName(fromPset.getName());
		if (topset.getBaseProperties().size() > 0) {
			topset.getBaseProperties().putAll(fromPset.getBaseProperties());
		}
	}
}
