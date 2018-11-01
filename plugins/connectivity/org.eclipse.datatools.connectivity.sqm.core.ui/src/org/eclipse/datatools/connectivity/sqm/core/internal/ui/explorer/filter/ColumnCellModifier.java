/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter;

import java.text.Collator;

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.Predicate;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.TableItem;

public class ColumnCellModifier implements ICellModifier {
	private ColumnTable m_columnTable;

	private String[] comboItems;

	private int index;

	private TableItem item;

	private Predicate predicate;

	public ColumnCellModifier(ColumnTable columnTable) {
		m_columnTable = columnTable;
	}

	public Object getValue(Object element, String property) {
		predicate = (Predicate) element;

		Collator collator =Collator.getInstance(); 
		collator.setStrength(Collator.PRIMARY);
		if (collator.compare(property,m_columnTable.getFirstColumnName()) == 0)
			return new Integer(predicate.getOperator() - 1);
		else
			return predicate.getValue();
	}

	public void modify(Object element, String property, Object value) {
		item = (TableItem) element;
		predicate = (Predicate) item.getData();

		Collator collator =Collator.getInstance(); 
		collator.setStrength(Collator.PRIMARY);
		if (collator.compare(property,m_columnTable.getFirstColumnName()) == 0){
			index = ((Integer) value).intValue();
			predicate.setOperator(index + 1);
		} else {
			predicate.setValue((value.toString()));
		}
		m_columnTable.updatePredicate(predicate);
	}

	public boolean canModify(Object element, String property) {
		return true;
	}
}
