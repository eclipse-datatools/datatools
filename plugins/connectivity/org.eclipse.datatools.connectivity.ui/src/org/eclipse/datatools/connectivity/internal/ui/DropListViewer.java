/*******************************************************************************
 * Copyright (c) 2003-2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

/**
 * @author rcernich
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DropListViewer extends StructuredViewer {

	/**
	 * This viewer's list control.
	 */
	private Combo mCombo;

	/**
	 * A list of viewer elements (element type: <code>Object</code>).
	 */
	private List listMap = new ArrayList();

	public DropListViewer(Composite parent, int style) {
		mCombo = new Combo(parent, style | SWT.READ_ONLY);
		hookControl(mCombo);
	}

	/**
	 * Creates a list viewer on a newly-created Combo control under the given parent.
	 * The Combo control is created using the SWT style bits <code>DROP_DOWN</code> and <code>READ_ONLY</code>.
	 * The viewer has no input, no content provider, a default label provider, 
	 * no sorter, and no filters.
	 *
	 * @param parent the parent control
	 */
	public DropListViewer(Composite parent) {
		this(parent, SWT.BORDER | SWT.DROP_DOWN);
	}

	public Combo getCombo() {
		return mCombo;
	}

	/**
	 * @see org.eclipse.jface.viewers.StructuredViewer#doFindInputItem(Object)
	 */
	protected Widget doFindInputItem(Object element) {
		if (element != null && element.equals(getRoot()))
			return getCombo();
		return null;
	}

	/**
	 * @see org.eclipse.jface.viewers.StructuredViewer#doFindItem(Object)
	 */
	protected Widget doFindItem(Object element) {
		if (element != null && listMap.contains(element)) {
			return getCombo();
		}
		return null;
	}

	/**
	 * @see org.eclipse.jface.viewers.StructuredViewer#doUpdateItem(Widget, Object, boolean)
	 */
	protected void doUpdateItem(Widget item, Object element, boolean fullMap) {
		if (element != null) {
			int ix = listMap.indexOf(element);
			if (ix >= 0) {
				ILabelProvider labelProvider =
					(ILabelProvider) getLabelProvider();
				mCombo.setItem(ix, labelProvider.getText(element));
			}
		}
	}

	/**
	 * @see org.eclipse.jface.viewers.StructuredViewer#getSelectionFromWidget()
	 */
	protected List getSelectionFromWidget() {
		int index = getCombo().getSelectionIndex();
		ArrayList list = new ArrayList();
		if (index >= 0) {
			Object o = listMap.get(index);
			if (o != null) {
				list.add(o);
			}
		}
		return list;
	}

	/**
	 * @see org.eclipse.jface.viewers.StructuredViewer#internalRefresh(Object)
	 */
	protected void internalRefresh(Object element) {
		if (element == null || element.equals(getRoot())) {
			Combo combo = getCombo();
			Object[] children = getSortedChildren(getRoot());
			String labels[] = new String[children.length];
			List selection = getSelectionFromWidget();

			combo.setRedraw(false);
			listMap.clear();
			combo.removeAll();
			unmapAllElements();

			for (int index = 0, count = children.length;
				index < count;
				++index) {
				Object o = children[index];
				labels[index] =
					((ILabelProvider) getLabelProvider()).getText(o);
				listMap.add(o);
				mapElement(o, combo);
			}

			combo.setItems(labels);

			combo.setRedraw(true);
			setSelectionToWidget(selection, false);
		}
		else {
			doUpdateItem(getCombo(), element, true);
		}
	}

	/**
	 * @see org.eclipse.jface.viewers.StructuredViewer#reveal(Object)
	 */
	public void reveal(Object element) {
		// Empty
	}

	/**
	 * @see org.eclipse.jface.viewers.StructuredViewer#setSelectionToWidget(List, boolean)
	 */
	protected void setSelectionToWidget(List l, boolean reveal) {
		Combo combo = getCombo();
		if (l == null || l.size() == 0) {
			combo.clearSelection();
		}
		else {
			combo.select(listMap.indexOf(l.get(0)));
		}
	}

	/**
	 * @see org.eclipse.jface.viewers.Viewer#getControl()
	 */
	public Control getControl() {
		return mCombo;
	}

	/**
	 * @see org.eclipse.jface.viewers.Viewer#inputChanged(Object, Object)
	 */
	protected void inputChanged(Object input, Object oldInput) {
		internalRefresh(getRoot());
		setSelectionToWidget((List) null, false);

		super.inputChanged(input, oldInput);
	}

}
