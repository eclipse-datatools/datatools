/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views.execute;

class ResultTableElement {

    protected java.lang.Object[] columnProperties;
    protected java.lang.Object[] values;

    public ResultTableElement(java.lang.Object[] values, java.lang.Object[] columnProperties) {
        this.values = values;
        this.columnProperties = columnProperties;
    }

    public String getColumnText(int columnIndex) {
        return (String) values[columnIndex];
    }    
}
