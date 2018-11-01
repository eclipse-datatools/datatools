/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.dialogs;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;

public abstract class AbstractInvertableTableSorter extends InvertableSorter {

	private final InvertableSorter inverse = new InvertableSorter() {
		 
		public int compare(Viewer viewer, Object e1, Object e2) {
			return (-1)*AbstractInvertableTableSorter.this
							.compare(viewer, e1, e2);
		}
 
		InvertableSorter getInverseSorter() {
			return AbstractInvertableTableSorter.this;
		}
 
		public int getSortDirection() {
			return SWT.DOWN;
		}
	};
 
	InvertableSorter getInverseSorter() {
		return inverse;
	}
 
	public int getSortDirection() {
		return SWT.UP;
	}

}
