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

import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.jface.window.SameShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Dialog for editing driver properties
 * 
 * @author brianf
 *
 */
public class DriverPropertyDialog extends TrayDialog {

	private boolean inInitialize = false;
	private PropertyObject propObject = null;
	private Text idText = null;
	private Text nameText = null;
	private Text descriptionText = null;
	private Text valueText = null;
	private Text categoryText = null;
	private Combo requiredCombo = null;
	private Combo visibleCombo = null;
	
	/**
	 * Constructor
	 * @param parentShell
	 */
	protected DriverPropertyDialog(IShellProvider parentShell) {
		super(parentShell);
	}
	
	/**
	 * Constructor
	 * @param shell
	 */
	protected DriverPropertyDialog (Shell shell) {
		super (new SameShellProvider(shell));
	}
	
	/**
	 * Sets the initially selected property object
	 * @param element
	 */
	public void setPropertyObject ( PropertyObject element ) {
		this.propObject = element;
	}
	
	/**
	 * Returns the created or edited property object
	 */
	public PropertyObject getPropertyObject () {
		return this.propObject;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite baseComposite = new Composite(parent, SWT.None);
		baseComposite.setLayout(new GridLayout(2, false));
		baseComposite.setLayoutData(new GridData());
		
		Label label = new Label(baseComposite, SWT.LEFT);
		label.setLayoutData(new GridData());
		label.setText(Messages.getString("DriverPropertyDialog.IDLabel")); //$NON-NLS-1$
		
		idText = new Text(baseComposite, SWT.BORDER);
		idText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		idText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				propertiesUpdated();
			}
		});

		label = new Label(baseComposite, SWT.LEFT);
		label.setLayoutData(new GridData());
		label.setText(Messages.getString("DriverPropertyDialog.NameLabel")); //$NON-NLS-1$

		nameText = new Text(baseComposite, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		nameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				propertiesUpdated();
			}
		});

		label = new Label(baseComposite, SWT.LEFT);
		label.setLayoutData(new GridData());
		label.setText(Messages.getString("DriverPropertyDialog.DescriptionLabel")); //$NON-NLS-1$

		descriptionText = new Text(baseComposite, SWT.BORDER);
		descriptionText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		descriptionText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				propertiesUpdated();
			}
		});

		label = new Label(baseComposite, SWT.LEFT);
		label.setLayoutData(new GridData());
		label.setText(Messages.getString("DriverPropertyDialog.ValueLabel")); //$NON-NLS-1$
		
		valueText = new Text(baseComposite, SWT.BORDER);
		valueText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		valueText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				propertiesUpdated();
			}
		});

		label = new Label(baseComposite, SWT.LEFT);
		label.setLayoutData(new GridData());
		label.setText(Messages.getString("DriverPropertyDialog.CategoryLabel")); //$NON-NLS-1$
		
		categoryText = new Text(baseComposite, SWT.BORDER);
		categoryText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		categoryText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				propertiesUpdated();
			}
		});

		label = new Label(baseComposite, SWT.LEFT);
		label.setLayoutData(new GridData());
		label.setText(Messages.getString("DriverPropertyDialog.VisibleLabel")); //$NON-NLS-1$

		visibleCombo = new Combo(baseComposite, SWT.DROP_DOWN | SWT.READ_ONLY);
		visibleCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		visibleCombo.add(Boolean.toString(false), 0);
		visibleCombo.add(Boolean.toString(true), 1);
		visibleCombo.select(1);
		visibleCombo.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				propertiesUpdated();
			}

			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}
		});

		label = new Label(baseComposite, SWT.LEFT);
		label.setLayoutData(new GridData());
		label.setText(Messages.getString("DriverPropertyDialog.RequiredLabel")); //$NON-NLS-1$
		
		requiredCombo = new Combo(baseComposite, SWT.DROP_DOWN | SWT.READ_ONLY);
		requiredCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		requiredCombo.add(Boolean.toString(false), 0);
		requiredCombo.add(Boolean.toString(true), 1);
		requiredCombo.select(1);
		requiredCombo.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				propertiesUpdated();
			}

			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}
		});
		
		initialize();
		
		return baseComposite;
	}
	
	/**
	 * Update the generated property object if the properties of
	 * the dialog change
	 */
	private void propertiesUpdated() {
		if (inInitialize)
			return;
		this.propObject = generateElement();
	}
	
	/**
	 * Create the property object from the dialog properties
	 * @return
	 */
	private PropertyObject generateElement() {
		propObject.setPropertyID(idText.getText());
		propObject.setPropertyName(nameText.getText());
		if (descriptionText.getText().trim().length() > 0)
			propObject.setPropertyDescription(descriptionText.getText());
		if (valueText.getText().trim().length() > 0)
			propObject.setPropertyValue(valueText.getText());
		if (categoryText.getText().trim().length() > 0)
			propObject.setPropertyCategory(categoryText.getText());
		
		propObject.setPropertyVisible(visibleCombo.getText());
		propObject.setPropertyRequired(requiredCombo.getText());
			
		return propObject;
	}
	
	/**
	 * Initialize the UI from the incoming property object 
	 * or from default values 
	 */
	private void initialize() {
		inInitialize = true;
		if (propObject != null) {
			String id = propObject.getPropertyID();
			String name = propObject.getPropertyName();
			String description = propObject.getPropertyDescription();
			String value = propObject.getPropertyValue();
			String visible = propObject.getPropertyVisible();
			String required = propObject.getPropertyRequired();
			String category = propObject.getPropertyCategory();
			boolean visibleFlag = Boolean.getBoolean(visible.toLowerCase());
			boolean requiredFlag = Boolean.getBoolean(required.toLowerCase());
			
			if (id != null && id.trim().length() > 0 && idText != null) {
				idText.setText(id);
			}
			if (name != null && name.trim().length() > 0 && nameText != null) {
				nameText.setText(name);
			}
			if (description != null && description.trim().length() > 0 && descriptionText != null) {
				descriptionText.setText(description);
			}
			if (value != null && value.trim().length() > 0 && valueText != null) {
				valueText.setText(value);
			}
			if (category != null && category.trim().length() > 0 && categoryText != null) {
				categoryText.setText(category);
			}
			if (visible != null && visible.trim().length() > 0 && visibleCombo != null) {
				if (visibleFlag)
					visibleCombo.select(1);
				else
					visibleCombo.select(0);
			}
			if (required != null && required.trim().length() > 0 && requiredCombo != null) {
				if (requiredFlag)
					requiredCombo.select(1);
				else
					requiredCombo.select(0);
			}
		}
		else {
			propObject = new PropertyObject();
			idText.setText(propObject.getPropertyID());
			nameText.setText(propObject.getPropertyName());
			valueText.setText(propObject.getPropertyValue());
		}
		inInitialize = false;
	}
}
