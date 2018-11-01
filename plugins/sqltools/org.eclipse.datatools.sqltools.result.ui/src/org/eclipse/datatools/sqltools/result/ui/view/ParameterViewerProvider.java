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
package org.eclipse.datatools.sqltools.result.ui.view;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.sqltools.result.Parameter;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ParameterTableViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * Parameter viewer provider configures the parameter table viewer
 * @author administrator
 *
 */
public class ParameterViewerProvider {

	protected TableViewer tableViewer;

	protected Composite parentComposite;
	protected int tableStyle;
	protected List parameter;

	/**
	 * Configures the viewer. The default implementation creates a
	 * ParameterTableViewer. Subclasses should extend add additional menus, listeners
	 * and label and content providers
	 */
	public void configureViewer() {
		tableViewer = new ParameterTableViewer(parentComposite, tableStyle);
	}

	/**
	 * Configures the parameter table viewer
	 * 
	 * @param parent
	 *            the parent composite
	 * @param style
	 *            table style
	 */
	public void configureViewer(Composite parent, int style) {
		tableStyle = style;
		parentComposite = parent;
		configureViewer();
	}
/**
 * fills the data into parameter table view
 * @param params paramter table items
 */
	public void fillDataIntoParamsTable(List params) {
		parameter = params;
		Table currentTable = tableViewer.getTable();
		currentTable.removeAll();
		Iterator iter = params.iterator();
		while (iter.hasNext()) {
			Parameter param = (Parameter) iter.next();
			TableItem item = new TableItem(currentTable, SWT.NONE);
			item.setText(ParameterTableViewer.PARAM_NAME, param.getParamName());
			item.setText(ParameterTableViewer.PARAM_TYPE, param.getParamType());
			item.setText(ParameterTableViewer.PARAM_DATA_TYPE, param
					.getParamDataType());
			item.setText(ParameterTableViewer.PARAM_VALUE, param
					.getParamValue());
			item.setText(ParameterTableViewer.PARAM_VALUE_OUT, param
					.getParamOutValue());
		}

	}
/**
 * gets the  table viewer for parameter
 * @return
 */
	public Table getTable() {
		return tableViewer.getTable();
	}

}
