/*******************************************************************************
 * Copyright © 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views;

/**
 * Defines an interface for providing the text value for a given object.  This is used with
 * the ComboBoxCellEditor and other cell editors as a means to provide a context-specific text 
 * value to the cell editor.
 * 
 * @author bpayton
 */
public interface ITextProvider {
    /**
     * Gets the text representation of the given value.
     * @see org.eclipse.datatools.sqltools.sqlbuilder.views.criteria.CriteriaGridViewer#CriteriaGridViewer(int, org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel, org.eclipse.swt.widgets.Composite, boolean)
     * @see org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor#doGetValue()
     * 
     * @param value the value for which a text representation is needed
     * @return the text representation of the value
     */
    String getText(Object value);
}
