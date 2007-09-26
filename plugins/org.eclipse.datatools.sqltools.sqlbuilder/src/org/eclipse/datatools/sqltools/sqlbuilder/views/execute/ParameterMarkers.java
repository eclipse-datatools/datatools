/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views.execute;

import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.StringUtility;
import org.eclipse.swt.widgets.Display;


/**
 * A helper class that takes a SQLStatement as input and substitute
 * the parameter markers with value by invoking the ParameterWizard dialog
 */
public class ParameterMarkers {

    QueryStatement sqlStatement;
    boolean continueExecution;

    public ParameterMarkers(QueryStatement sqlStatement) {
        this.sqlStatement = sqlStatement;
        continueExecution = true;
    }

    public boolean getContinueExecution() {
        return continueExecution;
    }

    Vector markerValues = new Vector();

    public Vector getMarkerValues() {
        return markerValues;
    }

    /**
     * Return the SQL statement with values substituted if there are any markers
     */
    public String substituteParameters() {
        List parms = StatementHelper.getAllVariablesInQueryStatement(sqlStatement);
        Vector parmMarkers = new Vector();
        parmMarkers.addAll(parms);

//        // Qualified any statement omitted schema
//        SQLQuerySourceFormat srcFormat = sqlStatement.getSourceInfo().getSqlFormat();
//        String previousSchema = srcFormat.getOmitSchema();
//        // tell parser to return schema with statement
//        srcFormat.setOmitSchema(null);
//
//        // First remove any newline characters from the SQL statement
//        // FIXME: what about comments here, removing new lines?
//        String sqlString = SQLStringHelper.trimBlanks(sqlStatement.getSQL());
//
//        // reset schema
//        srcFormat.setOmitSchema(previousSchema);
        // fix: wsdbu00037267
        String sqlString = StatementHelper.getSQLForExecution(sqlStatement);
        
        if (parmMarkers.size() > 0) {
            ParameterWizard wizard = new ParameterWizard(parmMarkers);

            Vector valueMarkers = new Vector();
            ParameterWizardDialog wizardDialog = new ParameterWizardDialog(Display.getCurrent().getActiveShell(), wizard);
            wizardDialog.setBlockOnOpen(true);
            wizardDialog.create();
            int result = wizardDialog.open();

            if (result == 0) {
                continueExecution = true;
                valueMarkers = wizard.getParameterMarkers();

                String marker = "";
                String value = "";
                QueryValueExpression expr;

                for (int i = 0; i < parmMarkers.size(); i++) {
                    if (parmMarkers.elementAt(i) instanceof QueryValueExpression) {
                        expr = (QueryValueExpression) parmMarkers.elementAt(i);

                        if (expr != null) {
                            marker = expr.getSQL();
                        }
                    }
                    else {
                        marker = "";
                    }
                    value = (String) valueMarkers.elementAt(i);
                    markerValues.add(value);
                    sqlString = StringUtility.change(sqlString, marker, value, 0, 1);
                }
            }
            else {
                sqlString = "";
                continueExecution = false;
            }
        }
        return sqlString;
    }
}
