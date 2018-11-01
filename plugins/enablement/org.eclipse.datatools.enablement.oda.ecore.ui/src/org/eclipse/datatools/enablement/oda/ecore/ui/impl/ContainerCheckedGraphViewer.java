/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2007-2008 SolutionsIQ, Inc.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *
 * </copyright>
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.ui.impl;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

/**
 * This class started life as a whole-cloth copy of org.eclipse.ui.dialogs.ContainerCheckedTreeViewer, which does not handle
 * selection of inifintely-nested / repeating elements and which currently cannot be overridden to supply the needed
 * behavior, i.e., that when the checkbox for an element with children is selected, only the parents of that element should
 * be updated. The children should only be updated if the checkbox is grey and is unselected.
 */
class ContainerCheckedGraphViewer extends CheckboxTreeViewer {

	ContainerCheckedGraphViewer(final Composite parent, final int style) {
		super(parent, style);
		initViewer();
	}

	private void initViewer() {
		setUseHashlookup(true);
		addCheckStateListener(new ICheckStateListener() {

			public void checkStateChanged(final CheckStateChangedEvent event) {
				doCheckStateChanged(event.getElement());
			}
		});
		addTreeListener(new ITreeViewerListener() {

			public void treeCollapsed(final TreeExpansionEvent event) {
				// no-op
			}

			public void treeExpanded(final TreeExpansionEvent event) {
				final Widget item = findItem(event.getElement());
				if (item instanceof TreeItem) {
					initializeItem((TreeItem) item, true);
				}
			}
		});
	}

	/**
	 * Update element after a checkstate change.
	 * 
	 * @param element
	 */
	private void doCheckStateChanged(final Object element) {
		final Widget item = findItem(element);
		if (item instanceof TreeItem) {
			final TreeItem treeItem = (TreeItem) item;
			if (!treeItem.getChecked()) {
				treeItem.setGrayed(false);
				updateChildrenItems(treeItem, false);
			} else {
				if (treeItem.getItemCount() != 0) {
					treeItem.setGrayed(true);
				}
			}
			updateParentItems(treeItem.getParentItem());
		}
	}

	/**
	 * The item has expanded. Updates the checked state of its children.
	 */
	private void initializeItem(final TreeItem item, final boolean isExpanding) {
		if (!item.getGrayed()) {
			updateChildrenItems(item, isExpanding);
		}
	}

	private void updateChildrenItems(final TreeItem parent, boolean isExpanding) {
		final Item[] children = getChildren(parent);
		if ((children == null || children.length == 0) && !isExpanding) {
			return;
		}
		final boolean state = parent.getChecked();
		for (int i = 0; i < children.length; i++) {
			final TreeItem current = (TreeItem) children[i];
			if (current.getData() != null && (current.getChecked() != state || current.getGrayed())) {
				current.setChecked(state);
				current.setGrayed(false);
				updateChildrenItems(current, false);
			}
		}
	}

	/**
	 * Updates the check / gray state of all parent items
	 */
	private void updateParentItems(final TreeItem item) {
		if (item == null) {
			return;
		}
		final Item[] children = getChildren(item);
		boolean containsChecked = false;
		boolean containsUnchecked = false;
		for (int i = 0; i < children.length; i++) {
			final TreeItem curr = (TreeItem) children[i];
			containsChecked |= curr.getChecked();
			containsUnchecked |= !curr.getChecked() || curr.getGrayed();
		}
		item.setChecked(containsChecked);
		item.setGrayed(containsChecked && containsUnchecked);
		updateParentItems(item.getParentItem());
	}

	@Override
	public boolean setChecked(final Object element, final boolean state) {
		if (super.setChecked(element, state)) {
			doCheckStateChanged(element);
			return true;
		}
		return false;
	}

	@Override
	public void setCheckedElements(final Object[] elements) {
		super.setCheckedElements(elements);
		for (int i = 0; i < elements.length; i++) {
			doCheckStateChanged(elements[i]);
		}
	}

	@Override
	protected void setExpanded(final Item item, final boolean expand) {
		super.setExpanded(item, expand);
		if (expand && item instanceof TreeItem) {
			initializeItem((TreeItem) item, expand);
		}
	}
}
