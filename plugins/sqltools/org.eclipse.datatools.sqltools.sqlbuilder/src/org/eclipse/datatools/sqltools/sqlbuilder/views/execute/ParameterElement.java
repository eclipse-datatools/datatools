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

import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;

public class ParameterElement {

    Object aParm;
    Vector parmValues;
    String value = "";
    int row;

    public ParameterElement(Object aParm, Vector parmValues, int row) {
        this.aParm = aParm;
        this.parmValues = parmValues;
        this.row = row;
    }

    public void setValue(String v) {
        this.value = v;
        parmValues.setElementAt(value, row);
    }

    public int getRow() {
        return row;
    }

    public String getColumnText(int columnIndex) {
        if (columnIndex == 0) {
            if (aParm instanceof QueryValueExpression) {
                QueryValueExpression expr = (QueryValueExpression) aParm;

                if (expr != null) {
                    return expr.getSQL();
                }
            }
            return "";
        }
        else if (columnIndex == 1) {
            if (aParm instanceof QueryValueExpression) {
                // Parameter marker is in the where clause
                QueryValueExpression valExpr = (QueryValueExpression) aParm;
                String dTypeName = "";
                DataType dType = valExpr.getDataType();
                if (dType != null) {
                    dTypeName = dType.getName();
                }
                return dTypeName;                
            }
            return "N/A";
        }
        else if (columnIndex == 2) {
            value = (String) parmValues.elementAt(row);
            return value;
        }
        return "";
    }
}
