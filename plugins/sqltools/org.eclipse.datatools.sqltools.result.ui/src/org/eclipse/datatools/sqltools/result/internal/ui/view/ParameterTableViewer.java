/*******************************************************************************
 * Copyright 2001, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui.view;

import org.eclipse.datatools.sqltools.result.internal.ui.LongDataDialog;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
/**
 * Describes a table viewer for parameter
 * @author Chetan Bhatia
 *
 */
public class ParameterTableViewer extends TableViewer {

	public static final int PARAM_NAME = 0;
	public static final int PARAM_TYPE = 1;
	public static final int PARAM_DATA_TYPE = 2;
	public static final int PARAM_VALUE = 3;
	public static final int PARAM_VALUE_OUT = 4;
	protected static final String BUTTON_TEXT = "...";

	public ParameterTableViewer(Composite parent, int style) {
		super(parent, style);
		configureTableViewer();
	}

	/**
	 * Configure the complete table by overriding this method.
	 */
	protected void configureTableViewer() {
		setupMouseListener(getTable());
		getTable().setLinesVisible(true);
		getTable().setHeaderVisible(true);
		getTable().setLayoutData(new GridData(GridData.FILL_BOTH));

		TableColumn nameColumn = new TableColumn(getTable(), SWT.NONE);
		nameColumn.setText(Messages.MultipleTabsGridSection_parameter_name);

		TableColumn typeColumn = new TableColumn(getTable(), SWT.NONE);
		typeColumn.setText(Messages.MultipleTabsGridSection_parameter_type);

		TableColumn dataTypeColumn = new TableColumn(getTable(), SWT.NONE);
		dataTypeColumn
				.setText(Messages.MultipleTabsGridSection_parameter_datatype);

		TableColumn valueColumn = new TableColumn(getTable(), SWT.NONE);
		valueColumn.setText(Messages.MultipleTabsGridSection_value);

		TableColumn outValueColumn = new TableColumn(getTable(), SWT.NONE);
		outValueColumn.setText(Messages.MultipleTabsGridSection_value_out);

		int defaultWidth = 0;

		int columnCount = getTable().getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			TableColumn column = getTable().getColumn(i);
			column.pack();
			defaultWidth = defaultWidth + column.getWidth()
					+ getTable().getGridLineWidth();
		}

		Composite comp = getTable().getParent();
		int moreWidth = comp.getParent().getBounds().width - 2 - defaultWidth;
		if (moreWidth > 0) {
			for (int i = 0; i < columnCount; i++) {
				TableColumn col = getTable().getColumn(i);
				col.setWidth(col.getWidth() + moreWidth / columnCount);
			}
		}
		getTable().pack();
	}

	/**
	 * This method sets the mouse listener for ellipsis button.
	 * @param paramTable
	 */
	protected void setupMouseListener(final Table paramTable) {
		final TableEditor editor = new TableEditor(paramTable);
		editor.horizontalAlignment = SWT.LEAD;
		paramTable.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent evt) {
				int b = evt.button;
				int stateMask = evt.stateMask;
				if (b == 1 && stateMask == 0) {
					int rowSelected = paramTable.getSelectionIndex();
					if (rowSelected == -1) {
						return;
					}
					TableItem tabItem = paramTable.getItem(rowSelected);
					int columnSelected = -1;
					int columns = paramTable.getColumnCount();
					int maxColumnCount = (columns == 0) ? 0 : columns - 1;
					Rectangle clientR = paramTable.getClientArea();
					Point point = new Point(evt.x, evt.y);
					for (int i = 0; i <= maxColumnCount; i++) {
						Rectangle rect = tabItem.getBounds(i);
						if (rect.y > clientR.y + clientR.height) {
							return;
						}
						if (rect.contains(point)) {
							columnSelected = i;
							break;
						}
					}
					if (columnSelected != -1) {
						int parameterMaxLength = ResultsViewUIPlugin.getDefault().getPreferenceStore().getInt(PreferenceConstants.ELLIPSIS_ENABLED_VALUE_LENGTH);
						if (columnSelected > 2
								&& tabItem.getText(columnSelected).length() > parameterMaxLength) {
							// Clean up any previous editor control
							Control oldEditor = editor.getEditor();
							if (oldEditor != null)
								oldEditor.dispose();
							Button button = new Button(paramTable, SWT.PUSH);
							button.setText(BUTTON_TEXT);
							button
									.setToolTipText(Messages.MultibleTabsEllipsisToolTipText);
							button.setSize(button.computeSize(SWT.DEFAULT,
									SWT.DEFAULT));

							editor.minimumWidth = button.computeSize(
									SWT.DEFAULT, SWT.DEFAULT).x;
							editor.setEditor(button, tabItem, columnSelected);
							addListenerForEllipses(paramTable, tabItem,
									columnSelected, button);
						} else {
							// Clean up any previous editor control if user
							// clicks on other part of the table
							Control oldEditor = editor.getEditor();
							if (oldEditor != null)
								oldEditor.dispose();
						}
					}
				}
			}
		});
	}
	/**
	 * Used to add ellipsis for default parameter viewer
	 * @param paramTable
	 * @param tabItem
	 * @param columnSelected
	 * @param button
	 */
	protected void addListenerForEllipses(final Table paramTable,
			TableItem tabItem, int columnSelected, Button button) {
		final String paramName = tabItem
				.getText(PARAM_NAME);
		final String paramDatatype = tabItem
				.getText(PARAM_DATA_TYPE);
		final String paramValue = tabItem
				.getText(columnSelected);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				LongDataDialog dialog = new LongDataDialog(
						paramTable.getShell(), paramName,
						paramDatatype, paramValue);
				dialog.open();
			}
		});
	}
}
