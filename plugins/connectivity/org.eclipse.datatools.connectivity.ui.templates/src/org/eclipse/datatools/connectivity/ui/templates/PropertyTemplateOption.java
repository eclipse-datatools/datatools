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

import java.util.ArrayList;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.window.Window;
import org.eclipse.pde.ui.templates.BaseOptionTemplateSection;
import org.eclipse.pde.ui.templates.TemplateOption;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

/**
 * PDE template for Driver Template properties
 * @author brianf
 *
 */
public class PropertyTemplateOption extends TemplateOption {

	private TreeViewer tv = null;
	private ListViewer lv = null;
	private Shell shell = null;
	private ArrayList properties = new ArrayList();
	private Button addButton = null;
	private Button removeButton = null;
	private Button editButton = null;
	private Button duplicateButton = null;
	private Button clearAllButton = null;
	
	/**
	 * Constructor
	 * @param section
	 * @param name
	 * @param label
	 */
	public PropertyTemplateOption(BaseOptionTemplateSection section,
			String name, String label) {
		super(section, name, label);
		setRequired(true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.TemplateField#createControl(org.eclipse.swt.widgets.Composite, int)
	 */
	public void createControl(Composite parent, int span) {

		this.shell = parent.getShell();
		
		Composite baseComposite = new Composite(parent, SWT.NONE);
		GridData bcGD = new GridData(SWT.FILL, SWT.FILL, true, true);
		bcGD.horizontalSpan = span;
		baseComposite.setLayoutData(bcGD);
		GridLayout bcGL = new GridLayout (3, false);
		bcGL.marginHeight = bcGL.marginWidth = 0;
		baseComposite.setLayout(bcGL);
		
		Tree tree = new Tree (baseComposite, SWT.V_SCROLL | SWT.BORDER | SWT.SINGLE );
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.verticalSpan = 4;
		gd.grabExcessHorizontalSpace = true;
		tree.setLayoutData(gd);
		tv = new TreeViewer(tree);
		tv.setContentProvider( new PropertyTreeContentProvider());
		tv.setLabelProvider(new PropertyTreeLabelProvider());
		tv.setSorter(new ViewerSorter());
		if (getValue() != null) {
			tv.setInput(ResourcesPlugin.getWorkspace().getRoot());
		}
		tv.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
			}
		});
		
		Composite buttonArea = new Composite(baseComposite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		buttonArea.setLayout(layout);
		GridData data = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		buttonArea.setLayoutData(data);

		addButton = new Button(buttonArea, SWT.PUSH);
		gd = new GridData(SWT.LEFT, SWT.TOP, true, false);
		addButton.setLayoutData(gd);
		addButton.setText(Messages.getString("PropertyTemplateOption.AddButton")); //$NON-NLS-1$
		addButton.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				addProperty();
			}

			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}
		});
		
		editButton = new Button(buttonArea, SWT.PUSH);
		editButton.setLayoutData(gd);
		editButton.setText(Messages.getString("PropertyTemplateOption.EditButton")); //$NON-NLS-1$
		editButton.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				editProperty();
			}

			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}
		});

		duplicateButton = new Button(buttonArea, SWT.PUSH);
		duplicateButton.setLayoutData(gd);
		duplicateButton.setText(Messages.getString("PropertyTemplateOption.DuplicateButton")); //$NON-NLS-1$
		duplicateButton.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				duplicateProperty();
			}

			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}
		});

		removeButton = new Button(buttonArea, SWT.PUSH);
		removeButton.setLayoutData(gd);
		removeButton.setText(Messages.getString("PropertyTemplateOption.RemoveButton")); //$NON-NLS-1$
		removeButton.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				removeProperty();
			}

			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}
		});
		
		clearAllButton = new Button(buttonArea, SWT.PUSH);
		clearAllButton.setLayoutData(gd);
		clearAllButton.setText(Messages.getString("PropertyTemplateOption.ClearSelectedButton")); //$NON-NLS-1$
		clearAllButton.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				clearAllProperties();
			}

			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}
		});

		List list = new List (baseComposite, SWT.V_SCROLL | SWT.BORDER | SWT.SINGLE );
		gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.verticalSpan = 4;
		gd.grabExcessHorizontalSpace = true;
		list.setLayoutData(gd);
		lv = new ListViewer(list);
		lv.setContentProvider( new PropertyListContentProvider());
		lv.setLabelProvider(new PropertyListLabelProvider());
		lv.setSorter(new ViewerSorter());
		if (getValue() != null) {
			lv.setInput(getValue());
		}
		lv.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
			}
		});
		
		updateButtons();
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.TemplateOption#isEmpty()
	 */
	public boolean isEmpty() {
		return getValue() == null || getValue().toString().length() == 0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.TemplateOption#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if (lv != null) {
			lv.getList().setEnabled(enabled);
		}
	}
	
	/**
	 * Clears the selected property list
	 */
	private void clearAllProperties() {
		properties.clear();
		lv.refresh();
		updateButtons();
	}
	
	/**
	 * Duplicates the selected property
	 */
	private void duplicateProperty() {
		IStructuredSelection selection = (IStructuredSelection) lv.getSelection();
		PropertyObject selectedPO = (PropertyObject) selection.getFirstElement();
		PropertyObject duplicatePO = getDuplicateProperty(selectedPO);
		properties.add(duplicatePO);
		lv.refresh();
	}
	
	/**
	 * Works through properties to find a unique ID
	 * @param ID
	 * @return
	 */
	private String getUniqueID ( String ID ) {
		int count = 0;
		String test = ID;
		boolean foundOne = false;
		boolean tryAgain = true;
		while (tryAgain) {
			for (int i = 0; i < properties.size(); i++) {
				PropertyObject po = (PropertyObject) properties.get(i);
				if (po.getPropertyID().equals(test)) {
					foundOne = true;
					break;
				}
			}
			if (!foundOne) {
				break;
			}
			if (foundOne) {
				count++;
				test = ID + count;
				tryAgain = true;
				foundOne = false;
			}
			else {
				tryAgain = false;
				break;
			}
		}
		return test;
	}

	/**
	 * Works through properties to find a unique name
	 * @param name
	 * @return
	 */
	private String getUniqueName ( String name ) {
		int count = 0;
		String test = name;
		boolean foundOne = false;
		boolean tryAgain = true;
		while (tryAgain) {
			for (int i = 0; i < properties.size(); i++) {
				PropertyObject po = (PropertyObject) properties.get(i);
				if (po.getPropertyName().equals(test)) {
					foundOne = true;
					break;
				}
			}
			if (!foundOne) {
				break;
			}
			if (foundOne) {
				count++;
				test = name + count;
				foundOne = false;
			}
			else {
				tryAgain = false;
				break;
			}
		}
		return test;
	}

	/**
	 * Removes the selected property
	 */
	private void removeProperty() {
		IStructuredSelection selection = (IStructuredSelection) lv.getSelection();
		PropertyObject selectedPO = (PropertyObject) selection.getFirstElement();
		properties.remove(selectedPO);
		lv.refresh();
		updateButtons();
	}
	
	/**
	 * Edits the selected property
	 */
	private void editProperty() {
		IStructuredSelection selection = (IStructuredSelection) lv.getSelection();
		PropertyObject selectedPO = (PropertyObject) selection.getFirstElement();
		DriverPropertyDialog dialog = new DriverPropertyDialog(this.shell);
		dialog.setPropertyObject(selectedPO);
		int returnCode = dialog.open();
		if (returnCode == Window.OK) {
			lv.refresh();
		}
		lv.refresh();
	}

	/**
	 * Clones a property object
	 * @param original
	 * @return
	 */
	private PropertyObject getDuplicateProperty(PropertyObject original) {
		PropertyObject duplicatePO = new PropertyObject();
		duplicatePO.setPropertyCategory(original.getPropertyCategory());
		duplicatePO.setPropertyDescription(original.getPropertyDescription());
		
		String newID = getUniqueID(original.getPropertyID());
		duplicatePO.setPropertyID(newID);
		
		String newName = getUniqueName(original.getPropertyName());
		duplicatePO.setPropertyName(newName);
		duplicatePO.setPropertyRequired(original.getPropertyRequired());
		duplicatePO.setPropertyValue(original.getPropertyValue());
		duplicatePO.setPropertyVisible(original.getPropertyVisible());
		
		return duplicatePO;
	}
	
	/**
	 * Add a new property, either from the tree or from a default 
	 * property object instance
	 */
	private void addProperty() {
		IStructuredSelection selection = (IStructuredSelection) tv.getSelection();
		if (!selection.isEmpty()) {
			if (selection.getFirstElement() instanceof TreeObject &&
					!(selection.getFirstElement() instanceof TreeParent)) {
				TreeObject selectedTO = (TreeObject) selection.getFirstElement();
				properties.add(getDuplicateProperty((PropertyObject)selectedTO.getValue()));
			}
			else if (selection.getFirstElement() instanceof TreeParent) {
				TreeParent selectedTP = (TreeParent) selection.getFirstElement();
				if (!selectedTP.getChildren().isEmpty()) {
					Object[] children = selectedTP.getChildrenArray();
					for (int i = 0; i < children.length; i++) {
						TreeObject to = (TreeObject) children[i];
						properties.add(getDuplicateProperty((PropertyObject)to.getValue()));
					}
				}
			}
			lv.refresh();
			updateButtons();
		}
	}
	
	/**
	 * Update buttons based on selected items
	 */
	private void updateButtons() {
		if (lv.getList().isFocusControl()) {
			IStructuredSelection lvSelection = (IStructuredSelection) lv.getSelection();
			editButton.setEnabled(!lvSelection.isEmpty());
			removeButton.setEnabled(!lvSelection.isEmpty());
			duplicateButton.setEnabled(!lvSelection.isEmpty());
		} else {
			editButton.setEnabled(false);
			removeButton.setEnabled(false);
			duplicateButton.setEnabled(false);
		}
		if (properties.size() > 0)
			clearAllButton.setEnabled(true);
		else
			clearAllButton.setEnabled(false);
		
		if (tv.getTree().isFocusControl()) {
			IStructuredSelection tvSelection = (IStructuredSelection) tv.getSelection();
			addButton.setEnabled(!tvSelection.isEmpty());
		} else {
			addButton.setEnabled(false);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.TemplateOption#getValue()
	 */
	public Object getValue() {
		return properties;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.TemplateOption#setValue(java.lang.Object)
	 */
	public void setValue(Object value) {
		super.setValue(value);
		if (lv != null) {
			lv.setInput(value);
		}
	}

}
