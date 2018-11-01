/*******************************************************************************
 * Copyright (c) 2001, 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.ibm.icu.text.Collator;

public class ConnectionFilterComposite extends Composite implements Listener {

	private static final ResourceLoader resource = ResourceLoader
			.getResourceLoader();

	protected ISelection selection;

	private Button disableFilterCheckbox;

	private Button expressionRadioButton;

	private Button selectionRadioButton;

	private Group expressionGroup;

	private Group selectionGroup;

	private Label expressionLabel;

	private Combo expressionPredicate;

	private Text expressionField;

	private Combo selectionPredicate;

	protected Table selectionTable;

	private Button selectAllButton;

	private Button deselectAllButton;

	private String STARTS_WITH_TEXT;

	private String CONTAINS_TEXT;

	private String ENDS_WITH_TEXT;

	private String NOT_START_WITH_TEXT;

	private String NOT_CONTAIN_TEXT;

	private String NOT_END_WITH_TEXT;

	private String INCLUDE_ITEMS_TEXT;

	private String EXCLUDE_ITEMS_TEXT;

	private static String LIKE_PREDICATE_TEXT = "LIKE"; //$NON-NLS-1$

	private static String IN_PREDICATE_TEXT = "IN"; //$NON-NLS-1$

	private static String NOT_PREDICATE_TEXT = "NOT"; //$NON-NLS-1$

	protected boolean isSelectionListPopulated = false;

	private static String IDENTIFIER_DELIMITER = "'"; //$NON-NLS-1$

	public String DEFAULT_MESSAGE = resource
			.queryString("_UI_DESCRIPTION_FILTER"); //$NON-NLS-1$

	private boolean hideExpressionOption = false;

	private boolean hideSelectionOption = false;

	private Collator collator = Collator.getInstance(Locale.getDefault());
	
	private IConnectionFilterProvider connectionFilterProvider;

	private ColumnTable columnTable;
	
	private boolean isMultiplePredicatesMode = false;
	
	public ConnectionFilterComposite(Composite parent, int style, IConnectionFilterProvider connectionFilterProvider,
			boolean hideExpressionOption, boolean hideSelectionOption) {
		super(parent, style);
		this.hideExpressionOption = hideExpressionOption;
		this.hideSelectionOption = hideSelectionOption;
		this.connectionFilterProvider = connectionFilterProvider;
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.verticalSpacing = 5;
		this.setLayout(layout);
		this.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		GridData gd = null;

		if (!hideExpressionOption) {
			expressionRadioButton = new Button(this, SWT.RADIO);
			expressionRadioButton.setText(resource
					.queryString("_UI_RADIO_BUTTON_EXPRESSION")); //$NON-NLS-1$
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			expressionRadioButton.setLayoutData(gd);

			expressionGroup = new Group(this, SWT.NONE);
			layout = new GridLayout();
			layout.numColumns = 3;
			layout.verticalSpacing = 5;
			expressionGroup.setLayout(layout);
			gd = new GridData(GridData.GRAB_HORIZONTAL
					| GridData.FILL_HORIZONTAL);
			gd.verticalAlignment = GridData.BEGINNING;
			expressionGroup.setLayoutData(gd);

			expressionLabel = new Label(expressionGroup, SWT.NONE);
			expressionLabel.setText(resource
					.queryString("_UI_LABEL_EXPRESSION")); //$NON-NLS-1$
			gd = new GridData();
			expressionLabel.setLayoutData(gd);

			expressionPredicate = new Combo(expressionGroup, SWT.READ_ONLY);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			expressionPredicate.setLayoutData(gd);
			STARTS_WITH_TEXT = resource.queryString("_UI_COMBO_STARTS_WITH"); //$NON-NLS-1$
			CONTAINS_TEXT = resource.queryString("_UI_COMBO_CONTAINS"); //$NON-NLS-1$
			ENDS_WITH_TEXT = resource.queryString("_UI_COMBO_ENDS_WITH"); //$NON-NLS-1$
			NOT_START_WITH_TEXT = resource
					.queryString("_UI_COMBO_NOT_START_WITH"); //$NON-NLS-1$;
			NOT_CONTAIN_TEXT = resource.queryString("_UI_COMBO_NOT_CONTAIN"); //$NON-NLS-1$;
			NOT_END_WITH_TEXT = resource.queryString("_UI_COMBO_NOT_END_WITH"); //$NON-NLS-1$;

			expressionPredicate.add(STARTS_WITH_TEXT);
			expressionPredicate.add(CONTAINS_TEXT);
			expressionPredicate.add(ENDS_WITH_TEXT);
			expressionPredicate.add(NOT_START_WITH_TEXT);
			expressionPredicate.add(NOT_CONTAIN_TEXT);
			expressionPredicate.add(NOT_END_WITH_TEXT);

			expressionField = new Text(expressionGroup, SWT.BORDER);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			expressionField.setLayoutData(gd);
		}

		if (!hideSelectionOption) {
			selectionRadioButton = new Button(this, SWT.RADIO);
			selectionRadioButton.setText(resource
					.queryString("_UI_RADIO_BUTTON_SELECTION")); //$NON-NLS-1$
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			selectionRadioButton.setLayoutData(gd);

			selectionGroup = new Group(this, SWT.NONE);
			layout = new GridLayout();
			layout.numColumns = 2;
			layout.verticalSpacing = 5;
			selectionGroup.setLayout(layout);
			gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL);
			selectionGroup.setLayoutData(gd);

			selectionPredicate = new Combo(selectionGroup, SWT.READ_ONLY);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 2;
			selectionPredicate.setLayoutData(gd);
			INCLUDE_ITEMS_TEXT = resource
					.queryString("_UI_COMBO_INCLUDE_ITEMS"); //$NON-NLS-1$
			EXCLUDE_ITEMS_TEXT = resource
					.queryString("_UI_COMBO_EXCLUDE_ITEMS"); //$NON-NLS-1$
			selectionPredicate.add(INCLUDE_ITEMS_TEXT);
			selectionPredicate.add(EXCLUDE_ITEMS_TEXT);

			selectionTable = new Table(selectionGroup, SWT.CHECK | SWT.BORDER);
			gd = new GridData(GridData.FILL_BOTH);
			gd.heightHint = 140;
			selectionTable.setLayoutData(gd);

			Composite buttonComposite = new Composite(selectionGroup, SWT.NONE);
			layout = new GridLayout();
			layout.numColumns = 1;
			layout.verticalSpacing = 5;
			buttonComposite.setLayout(layout);
			buttonComposite.setLayoutData(new GridData(GridData.FILL_VERTICAL
					| GridData.VERTICAL_ALIGN_BEGINNING));

			selectAllButton = new Button(buttonComposite, SWT.NONE);
			selectAllButton.setText(resource
					.queryString("_UI_BUTTON_SELECT_ALL")); //$NON-NLS-1$
			selectAllButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
					| GridData.VERTICAL_ALIGN_BEGINNING));

			deselectAllButton = new Button(buttonComposite, SWT.NONE);
			deselectAllButton.setText(resource
					.queryString("_UI_BUTTON_DESELECT_ALL")); //$NON-NLS-1$
			deselectAllButton.setLayoutData(new GridData(
					GridData.FILL_HORIZONTAL
							| GridData.VERTICAL_ALIGN_BEGINNING));
		}

		disableFilterCheckbox = new Button(this, SWT.CHECK);
		disableFilterCheckbox.setText(resource
				.queryString("_UI_CHECKBOX_DISABLE_FILTER")); //$NON-NLS-1$
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		disableFilterCheckbox.setLayoutData(gd);

		disableFilterCheckbox.addListener(SWT.Selection, this);
		if (!hideExpressionOption) {
			expressionRadioButton.addListener(SWT.Selection, this);
			if (!hideSelectionOption)
				selectionRadioButton.addListener(SWT.Selection, this);
			expressionField.addListener(SWT.Modify, this);
		}
		if (!hideSelectionOption) {
			selectionTable.addListener(SWT.Selection, this);
			selectionPredicate.addListener(SWT.Selection, this);
			selectAllButton.addListener(SWT.Selection, this);
			deselectAllButton.addListener(SWT.Selection, this);
		}

		disableFilterCheckbox.setSelection(false);
		if (!hideExpressionOption) {
			expressionRadioButton.setSelection(true);
			expressionPredicate.select(expressionPredicate
					.indexOf(STARTS_WITH_TEXT));
			if (!hideSelectionOption) {
				selectionPredicate.select(selectionPredicate
						.indexOf(INCLUDE_ITEMS_TEXT));
				enableSelectionGroupControls(false);
			}
		} else if (!hideSelectionOption) {
			selectionPredicate.select(selectionPredicate
					.indexOf(INCLUDE_ITEMS_TEXT));
		}
	}
	
	public ConnectionFilterComposite(Composite parent, int style, IConnectionFilterProvider connectionFilterProvider,
			boolean hideExpressionOption, boolean hideSelectionOption, boolean isMultiplePredicatesMode, ConnectionFilter connFilter, ConnectionFilterPropertyPage connectionFilterPropertyPage) {
		super(parent, style);
		
		this.hideExpressionOption = hideExpressionOption;
		this.hideSelectionOption = hideSelectionOption;
		this.connectionFilterProvider = connectionFilterProvider;
		this.isMultiplePredicatesMode = isMultiplePredicatesMode;
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.verticalSpacing = 5;
		this.setLayout(layout);
		this.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		GridData gd = null;

		if (!hideExpressionOption) {
			expressionRadioButton = new Button(this, SWT.RADIO);
			expressionRadioButton.setText(resource
					.queryString("_UI_RADIO_BUTTON_EXPRESSION")); //$NON-NLS-1$
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			expressionRadioButton.setLayoutData(gd);

			expressionGroup = new Group(this, SWT.NONE);
			layout = new GridLayout();
			layout.numColumns = 3;
			layout.verticalSpacing = 5;
			expressionGroup.setLayout(layout);
			gd = new GridData(GridData.GRAB_HORIZONTAL
					| GridData.FILL_HORIZONTAL);
			gd.verticalAlignment = GridData.BEGINNING;
			expressionGroup.setLayoutData(gd);

			columnTable = new ColumnTable(expressionGroup, connectionFilterPropertyPage, connFilter);
			
			STARTS_WITH_TEXT = resource.queryString("_UI_COMBO_STARTS_WITH"); //$NON-NLS-1$
			CONTAINS_TEXT = resource.queryString("_UI_COMBO_CONTAINS"); //$NON-NLS-1$
			ENDS_WITH_TEXT = resource.queryString("_UI_COMBO_ENDS_WITH"); //$NON-NLS-1$
			NOT_START_WITH_TEXT = resource
					.queryString("_UI_COMBO_NOT_START_WITH"); //$NON-NLS-1$;
			NOT_CONTAIN_TEXT = resource.queryString("_UI_COMBO_NOT_CONTAIN"); //$NON-NLS-1$;
			NOT_END_WITH_TEXT = resource.queryString("_UI_COMBO_NOT_END_WITH"); //$NON-NLS-1$;
		}

		if (!hideSelectionOption) {
			selectionRadioButton = new Button(this, SWT.RADIO);
			selectionRadioButton.setText(resource
					.queryString("_UI_RADIO_BUTTON_SELECTION")); //$NON-NLS-1$
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			selectionRadioButton.setLayoutData(gd);

			selectionGroup = new Group(this, SWT.NONE);
			layout = new GridLayout();
			layout.numColumns = 2;
			layout.verticalSpacing = 5;
			selectionGroup.setLayout(layout);
			gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL);
			selectionGroup.setLayoutData(gd);

			selectionPredicate = new Combo(selectionGroup, SWT.READ_ONLY);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 2;
			selectionPredicate.setLayoutData(gd);
			INCLUDE_ITEMS_TEXT = resource
					.queryString("_UI_COMBO_INCLUDE_ITEMS"); //$NON-NLS-1$
			EXCLUDE_ITEMS_TEXT = resource
					.queryString("_UI_COMBO_EXCLUDE_ITEMS"); //$NON-NLS-1$
			selectionPredicate.add(INCLUDE_ITEMS_TEXT);
			selectionPredicate.add(EXCLUDE_ITEMS_TEXT);

			selectionTable = new Table(selectionGroup, SWT.CHECK | SWT.BORDER);
			gd = new GridData(GridData.FILL_BOTH);
			gd.heightHint = 140;
			selectionTable.setLayoutData(gd);

			Composite buttonComposite = new Composite(selectionGroup, SWT.NONE);
			layout = new GridLayout();
			layout.numColumns = 1;
			layout.verticalSpacing = 5;
			buttonComposite.setLayout(layout);
			buttonComposite.setLayoutData(new GridData(GridData.FILL_VERTICAL
					| GridData.VERTICAL_ALIGN_BEGINNING));

			selectAllButton = new Button(buttonComposite, SWT.NONE);
			selectAllButton.setText(resource
					.queryString("_UI_BUTTON_SELECT_ALL")); //$NON-NLS-1$
			selectAllButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
					| GridData.VERTICAL_ALIGN_BEGINNING));

			deselectAllButton = new Button(buttonComposite, SWT.NONE);
			deselectAllButton.setText(resource
					.queryString("_UI_BUTTON_DESELECT_ALL")); //$NON-NLS-1$
			deselectAllButton.setLayoutData(new GridData(
					GridData.FILL_HORIZONTAL
							| GridData.VERTICAL_ALIGN_BEGINNING));
		}

		disableFilterCheckbox = new Button(this, SWT.CHECK);
		disableFilterCheckbox.setText(resource
				.queryString("_UI_CHECKBOX_DISABLE_FILTER")); //$NON-NLS-1$
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		disableFilterCheckbox.setLayoutData(gd);

		disableFilterCheckbox.addListener(SWT.Selection, this);
		if (!hideExpressionOption) {
			expressionRadioButton.addListener(SWT.Selection, this);
			if (!hideSelectionOption)
				selectionRadioButton.addListener(SWT.Selection, this);
		}
		if (!hideSelectionOption) {
			selectionTable.addListener(SWT.Selection, this);
			selectionPredicate.addListener(SWT.Selection, this);
			selectAllButton.addListener(SWT.Selection, this);
			deselectAllButton.addListener(SWT.Selection, this);
		}

		disableFilterCheckbox.setSelection(false);
		if (!hideExpressionOption) {
			expressionRadioButton.setSelection(true);
			
			if (!hideSelectionOption) {
				selectionPredicate.select(selectionPredicate
						.indexOf(INCLUDE_ITEMS_TEXT));
				enableSelectionGroupControls(false);
			}
		} else if (!hideSelectionOption) {
			selectionPredicate.select(selectionPredicate
					.indexOf(INCLUDE_ITEMS_TEXT));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		Widget source = event.widget;
		if (source == disableFilterCheckbox) {
			enableFilterSpecificationControls(!disableFilterCheckbox
					.getSelection());
		} else if (source == expressionRadioButton) {
			enableFilterSpecificationControls(true);
		} else if (source == selectAllButton) {
			setAllTableItemsChecked(true);
		} else if (source == deselectAllButton) {
			setAllTableItemsChecked(false);
		}
		connectionFilterProvider.dataChanged();
	}

	private void setAllTableItemsChecked(boolean checked) {
		TableItem[] tableItems = selectionTable.getItems();
		int tableItemCount = tableItems.length;
		for (int index = 0; index < tableItemCount; index++) {
			tableItems[index].setChecked(checked);
		}
	}

	private void enableExpressionGroupControls(boolean isEnabled) {
		if (!hideExpressionOption) {

			if (!isMultiplePredicatesMode) {
				expressionGroup.setEnabled(isEnabled);
				expressionLabel.setEnabled(isEnabled);
				expressionPredicate.setEnabled(isEnabled);
				expressionField.setEnabled(isEnabled);
			} else {
				expressionGroup.setEnabled(isEnabled);
			}
		}
	}

	private void enableSelectionGroupControls(boolean isEnabled) {
		if (!hideSelectionOption) {
			selectionGroup.setEnabled(isEnabled);
			selectionPredicate.setEnabled(isEnabled);
			selectionTable.setEnabled(isEnabled);
			selectAllButton.setEnabled(isEnabled);
			deselectAllButton.setEnabled(isEnabled);
			TableItem[] tableItems = selectionTable.getItems();
			int tableItemCount = tableItems.length;
			for (int index = 0; index < tableItemCount; index++) {
				tableItems[index].setGrayed(!isEnabled);
			}
			if (isEnabled && !isSelectionListPopulated) {
				populateSelectionTableWithBusyCursor();
			}
		}
	}

	private void enableFilterSpecificationControls(boolean isEnabled) {
		if (!hideExpressionOption) {
			if (!isMultiplePredicatesMode) {
				if (isEnabled) {
					expressionRadioButton.setEnabled(true);
					if (!hideSelectionOption)
						selectionRadioButton.setEnabled(true);
					if (expressionRadioButton.getSelection()) {
						enableSelectionGroupControls(false);
						enableExpressionGroupControls(true);
					} else {
						enableExpressionGroupControls(false);
						enableSelectionGroupControls(true);
					}
				} else {
					enableExpressionGroupControls(false);
					enableSelectionGroupControls(false);
					expressionRadioButton.setEnabled(false);
					if (!hideSelectionOption)
						selectionRadioButton.setEnabled(false);
				}
			}else { // Added for the new mode
				if (isEnabled) {
					expressionRadioButton.setEnabled(true);
					selectionRadioButton.setEnabled(true);
					
					if (expressionRadioButton.getSelection()) {
						enableSelectionGroupControls(false);
						enableExpressionGroupControls(true);
						columnTable.enableTableSpecificationControls(true);
					} else {
						enableExpressionGroupControls(false);
						columnTable.enableTableSpecificationControls(false);
						enableSelectionGroupControls(true);
					}
				} else {
					enableExpressionGroupControls(false);
					enableSelectionGroupControls(false);
					expressionRadioButton.setEnabled(false);
					columnTable.enableTableSpecificationControls(false);
					selectionRadioButton.setEnabled(false);
				}
			}
		} else {
			enableSelectionGroupControls(isEnabled);
		}
	}

	public void initializeValues() {
		isSelectionListPopulated = false;
		if (!hideSelectionOption)
			selectionTable.removeAll();

		ConnectionFilter connFilter = connectionFilterProvider.getConnectionFilter();

		if (connFilter != null) {
			disableFilterCheckbox.setSelection(false);
			String predicate = connFilter.getPredicate();
			
			if (!hideExpressionOption && isPredicateAnExpression(predicate)) {
				
				expressionRadioButton.setSelection(true);
				if (!hideSelectionOption)
					selectionRadioButton.setSelection(false);
				
				if(!isMultiplePredicatesMode){
					expressionPredicate.select(expressionPredicate
							.indexOf(findExpressionPredicate(predicate)));
					expressionField.setText(findExpressionValue(predicate));
				}
				else
					columnTable.initializeValues(true);

				enableFilterSpecificationControls(true);
			} else {
				if (!hideExpressionOption) {
					if (!hideSelectionOption)
						selectionRadioButton.setSelection(true);
					expressionRadioButton.setSelection(false);
					
					if (isMultiplePredicatesMode){
						columnTable.initializeValues(false);
					}
				}
				if (!hideSelectionOption) {
					if (isPredicateNegated(predicate)) {
						selectionPredicate.select(selectionPredicate
								.indexOf(EXCLUDE_ITEMS_TEXT));
					}
					populateSelectionTableWithBusyCursor();
					selectSelectionFilterItems(findSelectionFilterItems(predicate));
				}
				enableFilterSpecificationControls(true);
			}
		} else {
			disableFilterCheckbox.setSelection(true);
			enableFilterSpecificationControls(false);
			
			if(isMultiplePredicatesMode)
				columnTable.initializeValues(false);
		}
	}

	private String[] findSelectionFilterItems(String filterPredicate) {
		String[] filterItems = {};
		if (filterPredicate != null) {
			String[] predicateSubStrings = filterPredicate
					.split(IDENTIFIER_DELIMITER);
			// Remove first and last items from array
			int predicateSubStringsCount = predicateSubStrings.length;
			Vector filterItemCollection = new Vector();
			for (int index = 1; index < (predicateSubStringsCount - 1); index++) {
				filterItemCollection.add(predicateSubStrings[index]);
			}
			filterItems = new String[filterItemCollection.size()];
			filterItemCollection.copyInto(filterItems);
		}
		return filterItems;
	}

	private void selectSelectionFilterItems(String[] filterItems) {
		TableItem[] tableItems = selectionTable.getItems();
		Hashtable tableItemsCollection = new Hashtable();
		int tableItemsCount = tableItems.length;
		for (int index = 0; index < tableItemsCount; index++) {
			tableItemsCollection.put(tableItems[index].getText(),
					tableItems[index]);
		}
		int filterItemsCount = filterItems.length;
		for (int index = 0; index < filterItemsCount; index++) {
			Object tableItem = tableItemsCollection.get(filterItems[index]);
			if (tableItem != null) {
				((TableItem) tableItem).setChecked(true);
			}
		}
	}

	private boolean isPredicateAnExpression(String filterPredicate) {
		boolean isExpression = false;
		if (filterPredicate != null
				&& filterPredicate.split(IDENTIFIER_DELIMITER)[0]
						.indexOf(LIKE_PREDICATE_TEXT) != -1) {
			isExpression = true;
		}
		return isExpression;
	}

	private boolean isPredicateNegated(String filterPredicate) {
		boolean isNegated = false;
		if (filterPredicate != null
				&& filterPredicate.split(IDENTIFIER_DELIMITER)[0]
						.indexOf(NOT_PREDICATE_TEXT) != -1) {
			isNegated = true;
		}
		return isNegated;
	}

	private String findExpressionValue(String filterPredicate) {
		String expressionValue = filterPredicate.split(IDENTIFIER_DELIMITER)[1];
		if (expressionValue.startsWith("%")) { //$NON-NLS-1$
			expressionValue = expressionValue.substring(1);
		}

		if (expressionValue.endsWith("%")) { //$NON-NLS-1$
			expressionValue = expressionValue.substring(0, (expressionValue
					.length() - 1));
		}

		return expressionValue;
	}

	private String findExpressionPredicate(String filterPredicate) {
		String expressionPredicate = CONTAINS_TEXT;
		boolean isNotLike = false;
		boolean startsWithPercentSign = false;
		boolean endsWithPercentSign = false;
		if (filterPredicate.startsWith(NOT_PREDICATE_TEXT)) {
			isNotLike = true;
		}
		String expressionValue = filterPredicate.split(IDENTIFIER_DELIMITER)[1];
		if (expressionValue.startsWith("%")) { //$NON-NLS-1$
			startsWithPercentSign = true;
		}

		if (expressionValue.endsWith("%")) { //$NON-NLS-1$
			endsWithPercentSign = true;
		}

		if (startsWithPercentSign && endsWithPercentSign) {
			if (isNotLike) {
				expressionPredicate = NOT_CONTAIN_TEXT;
			} else {
				expressionPredicate = CONTAINS_TEXT;
			}
		} else if (startsWithPercentSign) {
			if (isNotLike) {
				expressionPredicate = NOT_END_WITH_TEXT;
			} else {
				expressionPredicate = ENDS_WITH_TEXT;
			}
		} else if (endsWithPercentSign) {
			if (isNotLike) {
				expressionPredicate = NOT_START_WITH_TEXT;
			} else {
				expressionPredicate = STARTS_WITH_TEXT;
			}
		}
		return expressionPredicate;
	}

	public boolean validatePage(DialogPage page) {
		boolean isValid = true;
		if (!disableFilterCheckbox.getSelection()) {
			if (!hideExpressionOption && expressionRadioButton.getSelection()) {
				
				if (!isMultiplePredicatesMode) {
					if (expressionField.getText().length() == 0) {
						page.setMessage(resource
								.queryString("_UI_MESSAGE_CRITERIA_REQUIRED")); //$NON-NLS-1$
						page.setErrorMessage(null);
						isValid = false;
					} else if ((expressionField.getText().indexOf(
							IDENTIFIER_DELIMITER) > -1)
							|| (expressionField.getText().indexOf('"') > -1)) {
						page.setErrorMessage(resource
								.queryString("_UI_MESSAGE_NO_QUOTES")); //$NON-NLS-1$
						page.setDescription(DEFAULT_MESSAGE);
						isValid = false;
					}
				} else {
					if (columnTable.hasEmptyExpression()) {
						page.setMessage(resource
								.queryString("_UI_MESSAGE_CRITERIA_REQUIRED")); //$NON-NLS-1$
						page.setErrorMessage(null);
						isValid = false;
					} else if (columnTable.hasQuoteInExpression()) {
						page.setErrorMessage(resource
								.queryString("_UI_MESSAGE_NO_QUOTES")); //$NON-NLS-1$
						page.setMessage(DEFAULT_MESSAGE);
						isValid = false;
					}
				}
			} else if (!hideSelectionOption) {
				TableItem[] items = selectionTable.getItems();
				int itemCount = items.length;
				boolean isItemSelected = false;
				for (int index = 0; index < itemCount; index++) {
					if (items[index].getChecked()) {
						isItemSelected = true;
						break;
					}
				}
				if (!isItemSelected) {
					page.setMessage(resource
							.queryString("_UI_MESSAGE_SELECTION_REQUIRED")); //$NON-NLS-1$
					page.setErrorMessage(null);
					isValid = false;
				}
			}
		}
		if (isValid) {
			page.setMessage(DEFAULT_MESSAGE);
			page.setErrorMessage(null);
		}
		return isValid;
	}

	public String getPredicate() {
		String predicate = ""; //$NON-NLS-1$
		if (isFilterSpecified()) {
			if (!hideExpressionOption && expressionRadioButton.getSelection()) {
				String selectedPredicate = expressionPredicate
						.getItem(expressionPredicate.getSelectionIndex());
				String cleanedString = expressionField.getText().trim();
				if (selectedPredicate.equals(STARTS_WITH_TEXT)) {
					predicate = LIKE_PREDICATE_TEXT + " " //$NON-NLS-1$
							+ IDENTIFIER_DELIMITER + cleanedString + "%" //$NON-NLS-1$
							+ IDENTIFIER_DELIMITER;
				} else if (selectedPredicate.equals(NOT_START_WITH_TEXT)) {
					predicate = NOT_PREDICATE_TEXT + " " //$NON-NLS-1$
							+ LIKE_PREDICATE_TEXT + " " //$NON-NLS-1$
							+ IDENTIFIER_DELIMITER + cleanedString + "%" //$NON-NLS-1$
							+ IDENTIFIER_DELIMITER;
				} else if (selectedPredicate.equals(CONTAINS_TEXT)) {
					predicate = LIKE_PREDICATE_TEXT + " " //$NON-NLS-1$
							+ IDENTIFIER_DELIMITER + "%" + cleanedString + "%" //$NON-NLS-1$ //$NON-NLS-2$
							+ IDENTIFIER_DELIMITER;
				} else if (selectedPredicate.equals(NOT_CONTAIN_TEXT)) {
					predicate = NOT_PREDICATE_TEXT + " " //$NON-NLS-1$
							+ LIKE_PREDICATE_TEXT + " " //$NON-NLS-1$
							+ IDENTIFIER_DELIMITER + "%" + cleanedString + "%" //$NON-NLS-1$ //$NON-NLS-2$
							+ IDENTIFIER_DELIMITER;
				} else if (selectedPredicate.equals(ENDS_WITH_TEXT)) {
					predicate = LIKE_PREDICATE_TEXT + " " //$NON-NLS-1$
							+ IDENTIFIER_DELIMITER + "%" + cleanedString //$NON-NLS-1$
							+ IDENTIFIER_DELIMITER;
				} else if (selectedPredicate.equals(NOT_END_WITH_TEXT)) {
					predicate = NOT_PREDICATE_TEXT + " " //$NON-NLS-1$
							+ LIKE_PREDICATE_TEXT + " " //$NON-NLS-1$
							+ IDENTIFIER_DELIMITER + "%" + cleanedString //$NON-NLS-1$
							+ IDENTIFIER_DELIMITER;
				}
			} else if (!hideSelectionOption) {
				String itemList = ""; //$NON-NLS-1$

				// Generate Item list
				TableItem[] items = selectionTable.getItems();
				Vector selectedItemsCollection = new Vector();
				int itemCount = items.length;
				for (int index = 0; index < itemCount; index++) {
					if (items[index].getChecked()) {
						selectedItemsCollection.add(items[index]);
					}
				}
				TableItem[] selectedItems = new TableItem[selectedItemsCollection
						.size()];
				selectedItemsCollection.copyInto(selectedItems);
				int selectedItemCount = selectedItems.length;
				if (selectedItemCount > 0) {
					itemList = itemList + IDENTIFIER_DELIMITER
							+ selectedItems[0].getText() + IDENTIFIER_DELIMITER;
					for (int index = 1; index < selectedItemCount; index++) {
						itemList = itemList + ", " + IDENTIFIER_DELIMITER //$NON-NLS-1$
								+ selectedItems[index].getText()
								+ IDENTIFIER_DELIMITER;
					}
					predicate = IN_PREDICATE_TEXT + "(" + itemList + ")"; //$NON-NLS-1$ //$NON-NLS-2$
					if (selectionPredicate.getItem(
							selectionPredicate.getSelectionIndex()).equals(
							EXCLUDE_ITEMS_TEXT)) {
						predicate = NOT_PREDICATE_TEXT + " " + predicate; //$NON-NLS-1$
					}
				}
			}
		}
		return predicate;
	}
	
	public String getPredicates() {
		
		String predicates = "";
		
		if (isFilterSpecified()) {

			if (!hideExpressionOption && expressionRadioButton.getSelection()) {
				String[] operators = columnTable.getOperators();
				String[] values = columnTable.getValues();
				String condition = null;
				String SQLPredicate = "";
				
				if(columnTable.isAnded())
					condition = "AND";
				else
					condition = "OR";
				
				for (int i = 0; i < operators.length; i++) {

					if (operators[i].equals(STARTS_WITH_TEXT)) {
						SQLPredicate = LIKE_PREDICATE_TEXT + " " //$NON-NLS-1$
								+ IDENTIFIER_DELIMITER + values[i] + "%" //$NON-NLS-1$
								+ IDENTIFIER_DELIMITER;

					} else if (operators[i].equals(NOT_START_WITH_TEXT)) {
						SQLPredicate = NOT_PREDICATE_TEXT + " " //$NON-NLS-1$
								+ LIKE_PREDICATE_TEXT + " " //$NON-NLS-1$
								+ IDENTIFIER_DELIMITER + values[i] + "%" //$NON-NLS-1$
								+ IDENTIFIER_DELIMITER;

					} else if (operators[i].equals(CONTAINS_TEXT)) {
						SQLPredicate = LIKE_PREDICATE_TEXT + " " //$NON-NLS-1$
								+ IDENTIFIER_DELIMITER + "%" + values[i] + "%" //$NON-NLS-1$ //$NON-NLS-2$
								+ IDENTIFIER_DELIMITER;

					} else if (operators[i].equals(NOT_CONTAIN_TEXT)) {
						SQLPredicate = NOT_PREDICATE_TEXT + " " //$NON-NLS-1$
								+ LIKE_PREDICATE_TEXT + " " //$NON-NLS-1$
								+ IDENTIFIER_DELIMITER + "%" + values[i] + "%" //$NON-NLS-1$ //$NON-NLS-2$
								+ IDENTIFIER_DELIMITER;

					} else if (operators[i].equals(ENDS_WITH_TEXT)) {
						SQLPredicate = LIKE_PREDICATE_TEXT + " " //$NON-NLS-1$
								+ IDENTIFIER_DELIMITER + "%" + values[i] //$NON-NLS-1$
								+ IDENTIFIER_DELIMITER;

					} else if (operators[i].equals(NOT_END_WITH_TEXT)) {
						SQLPredicate = NOT_PREDICATE_TEXT + " " //$NON-NLS-1$
								+ LIKE_PREDICATE_TEXT + " " //$NON-NLS-1$
								+ IDENTIFIER_DELIMITER + "%" + values[i] //$NON-NLS-1$
								+ IDENTIFIER_DELIMITER;
					}
					
					if(operators.length == 1)
						predicates = SQLPredicate;
					else
						predicates =  predicates + " {" + i + "} " + SQLPredicate;
					
					if ((i + 1) != operators.length)
						predicates = predicates + " " + condition;
				}

			} else if (!hideSelectionOption) {
				String itemList = ""; //$NON-NLS-1$

				// Generate Item list
				TableItem[] items = selectionTable.getItems();
				Vector selectedItemsCollection = new Vector();
				int itemCount = items.length;
				for (int index = 0; index < itemCount; index++) {
					if (items[index].getChecked()) {
						selectedItemsCollection.add(items[index]);
					}
				}
				TableItem[] selectedItems = new TableItem[selectedItemsCollection
						.size()];
				selectedItemsCollection.copyInto(selectedItems);
				int selectedItemCount = selectedItems.length;
				if (selectedItemCount > 0) {
					itemList = itemList + IDENTIFIER_DELIMITER
							+ selectedItems[0].getText() + IDENTIFIER_DELIMITER;
					for (int index = 1; index < selectedItemCount; index++) {
						itemList = itemList + ", " + IDENTIFIER_DELIMITER //$NON-NLS-1$
								+ selectedItems[index].getText()
								+ IDENTIFIER_DELIMITER;
					}
					
					predicates = IN_PREDICATE_TEXT + "(" + itemList + ")"; //$NON-NLS-1$ //$NON-NLS-2$
					if (selectionPredicate.getItem(
							selectionPredicate.getSelectionIndex()).equals(
							EXCLUDE_ITEMS_TEXT)) {
						predicates = NOT_PREDICATE_TEXT + " " + predicates; //$NON-NLS-1$
					}
				}
			}
		}
		return predicates.trim();
	}
	
	public boolean isFilterSpecified() {
		return !disableFilterCheckbox.getSelection();
	}

	public boolean isIncludeSelected() {
		if (selectionPredicate.getItem(selectionPredicate.getSelectionIndex())
				.equals(INCLUDE_ITEMS_TEXT)) {
			return true;
		}
		return false;
	}

	private EObject getParentEObject(Object current) {
		while (!(current instanceof EObject)
				&& (current = ((IVirtualNode) current).getParent()) != null)
			;
		return (EObject) current;
	}

	public void populateSelectionTable() {
		populateSelectionTableWithBusyCursor();
	}
	
	public void populateSelectionTable(Table selectionTable) {
		if (selection != null) {
			Object sel = ((StructuredSelection) selection).getFirstElement();
			selectionTable.removeAll();
			if (sel instanceof IVirtualNode) {
				ContainmentService containmentService = RDBCorePlugin
						.getDefault().getContainmentService();
				EObject schema = (EObject) getParentEObject(sel);
				Collection viewsCollection = containmentService
						.getContainedDisplayableElements(schema,
								((IVirtualNode) sel).getGroupID());
				ArrayList viewsList = new ArrayList(viewsCollection);
				Collections.sort(viewsList, new Comparator() {
					public int compare(Object sqlObject1, Object sqlObject2) {
						return collator
								.getCollationKey(
										((SQLObject) sqlObject1).getName())
								.compareTo(
										collator
												.getCollationKey(((SQLObject) sqlObject2)
														.getName()));
					}
				});
				Iterator views = viewsList.iterator();
				while (views.hasNext()) {
					TableItem tableItem = new TableItem(selectionTable,
							SWT.NONE);
					tableItem.setText(((SQLObject) views.next()).getName());
				}			
			}
		}
	}
	
	private void populateSelectionTableWithBusyCursor() {
		try {
			Cursor waitCursor = new Cursor(this.getShell().getDisplay(),
					SWT.CURSOR_WAIT);
			this.getShell().setCursor(waitCursor);
			connectionFilterProvider.populateSelectionTable(selectionTable);
			isSelectionListPopulated = true;
		} catch (Exception e) {
			// Since populateSelectonTable() can be overridden by subclasses,
			// we need to insulate this class from exceptions so that we don't
			// have the cursor hung-up in the busy cursor.
		} finally {
			this.getShell().setCursor(null);
		}
	}
	
	public boolean isHideExpressionOption(){
		return this.hideExpressionOption;
	}
	
	public boolean isHideSelectionOption(){
		return this.hideSelectionOption;
	}
	
    public void performDefaults()
    {
        //Restore default values and settings
        disableFilterCheckbox.setSelection(true);
        
        if(!isMultiplePredicatesMode)
        {
        	expressionField.setText("");
        	expressionPredicate.select(0);
        }
        else
        {
        	columnTable.performDefaults();
        }
        
        if (!hideSelectionOption) {
        	selectionTable.removeAll();
        	selectionPredicate.select(0);
            selectionRadioButton.setSelection(false);
        }
        expressionRadioButton.setSelection(true);
        enableFilterSpecificationControls(false);
        isSelectionListPopulated = false;
    }  
    
    public void setSelection(ISelection selection){
    	this.selection = selection;
    }
    
    public void setSelectionListPopulated(boolean isPopulated){
        this.isSelectionListPopulated = isPopulated;
    }
}
