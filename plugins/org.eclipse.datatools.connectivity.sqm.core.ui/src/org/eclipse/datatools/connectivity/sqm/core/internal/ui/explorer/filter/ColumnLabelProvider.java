/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2003, 2004.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
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
