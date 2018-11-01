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
package org.eclipse.datatools.sqltools.data.internal.ui.editor;


/**
 * IExternalTableDataEditor provides a simple interface for the 
 * org.eclipse.wst.rdb.data.ui.externalTableDataEditor extension point.
 * Besides implementing this interface, contributors of 
 * org.eclipse.wst.rdb.data.ui.externalTableDataEditor also need 
 * to provide a zero-argument Constructor, as implementing classes of this interface 
 * are instantiated using IConfigurationElement.createExecutableExtension(String propertyName).
 * @author sschaer
 * @see org.eclipse.core.runtime.IConfigurationElement#createExecutableExtension(java.lang.String)
 */
public interface IExternalTableDataEditor {

	/**
	 * externalEdit() passes the TableDataEditor to the IExternalTableDataEditor in order
     * to allow full access to both model and ui components. It is the responsiblity of 
     * the IExternalTableDataEditor to retrieve the currently selected cell, modify and update it accordingly.
     * externalEdit() is called from within the TableDataEditor's cursor TableDataTableCursorExternalEditingSupport.edit()
     * DefaultExternalTableDataEditor provides a default implementation.
     * @see TableDataTableCursorExternalEditingSupport#edit()
     * @param editor the TableDataEditor 
	 */
	public void externalEdit(ITableDataEditor editor);
}
