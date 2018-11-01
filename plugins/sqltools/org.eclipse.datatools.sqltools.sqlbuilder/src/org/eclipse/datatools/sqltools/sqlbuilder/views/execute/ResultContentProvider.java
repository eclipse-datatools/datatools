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

import java.sql.ResultSet;
import java.util.ArrayList;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

class ResultContentProvider implements IStructuredContentProvider {

    protected java.lang.Object[] myColumnProperties;
    private ResultSet resultSet;
    private int numRecords;

    public ResultContentProvider(java.lang.Object[] columnProperties, ResultSet resultSet) {
        myColumnProperties = columnProperties;
        this.resultSet = resultSet;
    }

    public static ResultContentProvider createMyTable(java.lang.Object[] columnProperties, ResultSet resultSet) {
        return new ResultContentProvider(columnProperties, resultSet);
    }

    public int getRecordsDisplayedCount() {
        return numRecords;
    }

    public Object[] getElements(java.lang.Object property) {
        ArrayList tableElements = new ArrayList();
        if (resultSet != null) {
            try {
                numRecords = 0;
                while (resultSet.next() && numRecords < 1100) {
                    String[] values = new String[myColumnProperties.length];
                    for (int i = 1; i <= myColumnProperties.length; i++) {
                        String name = resultSet.getString(i);
                        if (name == null)
                            name = "-";
                        values[i - 1] = name;
                    }
                    tableElements.add(new ResultTableElement(values, myColumnProperties));
                    numRecords++;
                }
            }
            catch (Exception exception) {
            }
        }

        return tableElements.toArray();
    }

    public void dispose() {
    }

    public void inputChanged(Viewer viewer, Object old, Object newobj) {
    }
    
}
