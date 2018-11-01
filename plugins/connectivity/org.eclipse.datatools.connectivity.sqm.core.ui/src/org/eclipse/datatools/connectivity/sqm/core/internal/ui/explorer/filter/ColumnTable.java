/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.RDBCoreUIPlugin;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ImagePath;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.Predicate;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.osgi.framework.Bundle;

/**
 * This class creates the table columns.
 * 
 * @author David Archer <a href="mailto:dwarcher@us.ibm.com">dwarcher@us.ibm.com</a>
 */
public class ColumnTable {
	
	private static final ResourceLoader resource = ResourceLoader.getResourceLoader();
	
	Bundle bundle = RDBCoreUIPlugin.getDefault().getBundle();
	
	private static final String ICONS_DIRECTORY = "/icons/"; //$NON-NLS-1$
	
	private ConnectionFilter connFilter = null;

	private ToolItem m_newColumnToolItem = null;

	private ToolItem m_deleteColumnToolItem = null;

	private Image m_newColumnImage = null;

	private Image m_deleteColumnImage = null;

	private Button andRadioButton;

	private Button orRadioButton;

	private Table m_columnTable = null;

	private TableColumn tableColumn;

	private TableViewer m_columnTableViewer = null;

	private GridData gd;

	private List columnNames = new ArrayList();

	private List editors = new ArrayList();

	private int currentSelectedIndex = -1;

	private Predicate predicate;

	private ArrayList predicates;

	public static final int STARTS_WITH_OPERATOR = 1;

	public static final int CONTAINS_OPERATOR = 2;

	public static final int ENDS_WITH_OPERATOR = 3;

	public static final int NOT_START_WITH_OPERATOR = 4;

	public static final int NOT_CONTAIN_OPERATOR = 5;

	public static final int NOT_END_WITH_OPERATOR = 6;

	public static final int SELECTION_OPERATOR = 7;

	private String STARTS_WITH_TEXT = resource
			.queryString("_UI_COMBO_STARTS_WITH"); //$NON-NLS-1$

	private String CONTAINS_TEXT = resource.queryString("_UI_COMBO_CONTAINS"); //$NON-NLS-1$

	private String ENDS_WITH_TEXT = resource.queryString("_UI_COMBO_ENDS_WITH"); //$NON-NLS-1$

	private String NOT_START_WITH_TEXT = resource
			.queryString("_UI_COMBO_NOT_START_WITH"); //$NON-NLS-1$;

	private String NOT_CONTAIN_TEXT = resource
			.queryString("_UI_COMBO_NOT_CONTAIN"); //$NON-NLS-1$;

	private String NOT_END_WITH_TEXT = resource
			.queryString("_UI_COMBO_NOT_END_WITH"); //$NON-NLS-1$;

	private String[] predicatesArray = { STARTS_WITH_TEXT, CONTAINS_TEXT,
			ENDS_WITH_TEXT, NOT_START_WITH_TEXT, NOT_CONTAIN_TEXT,
			NOT_END_WITH_TEXT };

	private ConnectionFilterPropertyPage page;
	
	private boolean isAnded = true;
	
	private CommonTableCursor cursor;
	
	//Used for Translated Column Name String
	private String firstColumnName;
	
	public ColumnTable(Composite composite,
			ConnectionFilterPropertyPage filterWizardPage,
			ConnectionFilter connectionFilter) {
		
		page = filterWizardPage;
		connFilter = connectionFilter;
		isAnded = connFilter.isMeetsAllConditions();
		
		GridLayout compositeLayout = new GridLayout(3, false);
		composite.setLayout(compositeLayout);

		// toolbar
		ToolBar toolbar = new ToolBar(composite, SWT.FLAT);
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalAlignment = GridData.BEGINNING;
		gd.widthHint = 100;
		toolbar.setLayoutData(gd);

		// add button
		m_newColumnToolItem = new ToolItem(toolbar, SWT.NONE);
		m_newColumnToolItem.setToolTipText(resource
				.queryString("_UI_TOOLTIP_NEW"));
		m_newColumnImage = resource.queryAbsolutePathImageFromRegistry(bundle, ICONS_DIRECTORY + ImagePath.NEW);//ImageDescription.getNewDescriptor().createImage();
		m_newColumnToolItem.setImage(m_newColumnImage);
		m_newColumnToolItem.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent event) {
				// TODO Auto-generated method stub
			}

			public void widgetSelected(SelectionEvent event) {
				predicate = new Predicate(1, "");
				m_columnTableViewer.add(predicate);
				updateDeleteButtonState();
				page.setPageValidity();
			}
		});

		// delete button
		m_deleteColumnToolItem = new ToolItem(toolbar, SWT.NONE);
		m_deleteColumnToolItem.setToolTipText(resource
				.queryString("_UI_TOOLTIP_DELETE"));
		m_deleteColumnImage = resource.queryAbsolutePathImageFromRegistry(bundle, ICONS_DIRECTORY + ImagePath.DELETE);
		m_deleteColumnToolItem.setImage(m_deleteColumnImage);

		m_deleteColumnToolItem.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent event) {//
			}

			public void widgetSelected(SelectionEvent event) {

				predicate = (Predicate) ((IStructuredSelection) m_columnTableViewer
						.getSelection()).getFirstElement();

				currentSelectedIndex = m_columnTable.getSelectionIndex();

				if ((m_columnTable.getItemCount() - 1) == currentSelectedIndex)
					m_columnTable.setSelection(currentSelectedIndex - 1);
				else
					m_columnTable.setSelection(currentSelectedIndex + 1);

				m_columnTableViewer.remove(predicate);
				updateDeleteButtonState();
				m_columnTableViewer.refresh(predicate);
			}
		});

		Composite radioButtonComposite = new Composite(composite, SWT.NONE);
		compositeLayout = new GridLayout(2, false);
		gd = new GridData();
		gd.horizontalSpan = 2;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalAlignment = GridData.END;
		radioButtonComposite.setLayout(compositeLayout);
		radioButtonComposite.setLayoutData(gd);

		// AND radio button
		andRadioButton = new Button(radioButtonComposite, SWT.RADIO);
		andRadioButton.setText(resource.queryString("_UI_RADIO_BUTTON_AND")); //$NON-NLS-1$		
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalAlignment = GridData.END;
		andRadioButton.setLayoutData(gd);
		andRadioButton.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent e) {
				isAnded = true;
			}
			
		});

		// OR radio button
		orRadioButton = new Button(radioButtonComposite, SWT.RADIO);
		orRadioButton.setText(resource.queryString("_UI_RADIO_BUTTON_OR")); //$NON-NLS-1$
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalAlignment = GridData.END;
		orRadioButton.setLayoutData(gd);
		orRadioButton.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent e) {
				isAnded = false;
			}
			
		});
		
		
		if (connFilter.isMeetsAllConditions())
			andRadioButton.setSelection(true);
		else
			orRadioButton.setSelection(true);

		// table
		m_columnTable = new Table(composite, SWT.SINGLE | SWT.BORDER
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		m_columnTable.setHeaderVisible(true);
		gd = new GridData(GridData.FILL_BOTH);
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalSpan = 3;
		gd.heightHint = 90;
		gd.widthHint = 50;
		m_columnTable.setLayoutData(gd);
		m_columnTable.setLinesVisible(true);

		// operator Column
		tableColumn = new TableColumn(m_columnTable, SWT.LEFT);
		tableColumn.setText(resource.queryString("_UI_COMBO_PREDICATE"));
		columnNames.add(resource.queryString("_UI_COMBO_PREDICATE"));
		setFirstColumnName(resource.queryString("_UI_COMBO_PREDICATE"));
		tableColumn.setWidth(200);
		tableColumn.setResizable(true);

		// value Column
		tableColumn = new TableColumn(m_columnTable, SWT.LEFT);
		tableColumn.setText(resource.queryString("_UI_TEXT_VALUE"));
		columnNames.add(resource.queryString("_UI_TEXT_VALUE"));
		tableColumn.setWidth(390);
		tableColumn.setResizable(true);

		// predicate combo box
		ComboBoxCellEditor predicateEditor = new ComboBoxCellEditor(
				m_columnTable, predicatesArray, SWT.READ_ONLY);
		editors.add(predicateEditor);
		TextCellEditor valueEditor = new TextCellEditor(m_columnTable);
		editors.add(valueEditor);

		// table viewer
		m_columnTableViewer = new TableViewer(m_columnTable);
		m_columnTableViewer.setColumnProperties((String[]) columnNames
				.toArray(new String[columnNames.size()]));
		m_columnTableViewer.setCellEditors((CellEditor[]) editors
				.toArray(new CellEditor[editors.size()]));
		m_columnTableViewer.setLabelProvider(new ColumnLabelProvider(this));
		
		if(page.hideSelectionOption){
			m_columnTableViewer.setContentProvider(new ColumnContentProvider(
					connFilter));
		}
		
		m_columnTableViewer.setCellModifier(new ColumnCellModifier(this));
		cursor = new CommonTableCursor(m_columnTableViewer);
	}

	// Gets the predicate of the first row (for migration purpose only)
	protected String getPredicate() {
		return getOperators()[0];
	}

	// Gets the value of the first row (for migration purpose only)
	protected String getValue() {
		return getValues()[0];
	}
	
	//Use for Translated Column Name String
	public String getFirstColumnName() {
		return firstColumnName;
	}

	//Use for Translated Column Name String
	public void setFirstColumnName(String firstColumnName) {
		this.firstColumnName = firstColumnName;
	}

	protected boolean hasEmptyExpression() {
		boolean isEmptyExpression = false;
		String[] values = getValues();

		for (int i = 0; i < values.length; i++) {

			if (values[i].length() == 0) {
				isEmptyExpression = true;
				i = values.length + 1;
			}
		}
		return isEmptyExpression;
	}

	protected boolean hasQuoteInExpression() {
		boolean isQuoteInExpression = false;
		String[] values = getValues();

		for (int i = 0; i < values.length; i++) {

			if ((values[i].indexOf("'") > -1) || (values[i].indexOf('"') > -1)) {
				isQuoteInExpression = true;
				i = values.length + 1;
			}
		}
		return isQuoteInExpression;
	}

	public void enableTableSpecificationControls(boolean isEnabled) {

		if (isEnabled) {
			m_newColumnToolItem.setEnabled(true);
			andRadioButton.setEnabled(true);
			orRadioButton.setEnabled(true);
			m_columnTable.setEnabled(true);
		} else {
			m_newColumnToolItem.setEnabled(false);
			m_deleteColumnToolItem.setEnabled(false);
			andRadioButton.setEnabled(false);
			orRadioButton.setEnabled(false);
			m_columnTable.setEnabled(false);
		}
	}

	private void clearTable(){
		m_columnTable.removeAll();
		predicate = new Predicate(1, "");
		m_columnTableViewer.add(predicate);
	}
	protected void initializeValues(boolean isExpressionRadioButtonSelected) {

		if(!isExpressionRadioButtonSelected){
			clearTable();
		}
		else{
			predicates = connFilter.getPredicatesCollection();
	
			if (predicates.size() > 0)
				predicate = (Predicate) predicates.get(0);
	
			if (connFilter.getPredicatesCollection().size() == 0
					|| predicate.getOperator() == SELECTION_OPERATOR) {
				clearTable();
			} else {
	
				for (int i = 0; i < predicates.size(); i++) {
					predicate = (Predicate) predicates.get(i);
					m_columnTableViewer.add(predicate);
				}
			}
		}
		updateDeleteButtonState();
	}

	private void updateDeleteButtonState() {

		if (m_columnTable.getItemCount() > 1)
			m_deleteColumnToolItem.setEnabled(true);
		else
			m_deleteColumnToolItem.setEnabled(false);
	}

	protected String getSQLOperator(int operator) {
		String SQLOperator = null;

		if (operator == STARTS_WITH_OPERATOR)
			SQLOperator = STARTS_WITH_TEXT;
		else if (operator == CONTAINS_OPERATOR)
			SQLOperator = CONTAINS_TEXT;
		else if (operator == ENDS_WITH_OPERATOR)
			SQLOperator = ENDS_WITH_TEXT;
		else if (operator == NOT_START_WITH_OPERATOR)
			SQLOperator = NOT_START_WITH_TEXT;
		else if (operator == NOT_CONTAIN_OPERATOR)
			SQLOperator = NOT_CONTAIN_TEXT;
		else if (operator == NOT_END_WITH_OPERATOR)
			SQLOperator = NOT_END_WITH_TEXT;

		return SQLOperator;
	}

	protected String[] getOperators() {
		TableItem[] tableItems = m_columnTable.getItems();
		String[] operators = new String[tableItems.length];

		for (int i = 0; i < operators.length; i++) {
			operators[i] = tableItems[i].getText(0);
		}

		return operators;
	}

	protected String[] getValues() {
		TableItem[] tableItems = m_columnTable.getItems();
		String[] values = new String[tableItems.length];

		for (int i = 0; i < values.length; i++) {
			values[i] = tableItems[i].getText(1);
		}

		return values;
	}

	protected void updatePredicate(Predicate predicate) {
		m_columnTableViewer.update(predicate, null);
		page.setPageValidity();
	}
	
	protected void performDefaults(){
		connFilter.setPredicate("");
		initializeValues(true);
	}
	
	protected boolean isAnded(){
		return isAnded;
	}
}
