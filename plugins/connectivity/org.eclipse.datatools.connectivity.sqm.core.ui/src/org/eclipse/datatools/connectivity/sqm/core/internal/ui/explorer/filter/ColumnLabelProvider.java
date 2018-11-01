/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter;

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.Predicate;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ColumnLabelProvider extends LabelProvider implements
		ITableLabelProvider {
	Predicate predicate;

	ColumnTable columnTable;

	public ColumnLabelProvider(ColumnTable columnTable) {
		this.columnTable = columnTable;
	}

	public String getColumnText(Object element, int columnIndex) {
		predicate = (Predicate) element;

		if (columnIndex == 0)
			return columnTable.getSQLOperator(predicate.getOperator());
		else
			return predicate.getValue();
	}

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
}
