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

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
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
        // [bug 223776]
        /* Get a list of parameter marker-type ("?") variables in the statement. */
        List paramMarkerList = StatementHelper.getAllParameterMarkersInQueryStatement(sqlStatement);
        
        /* Get a list of all variables in the statement.  This has the bad side-effect
         * of modifying the model to give generated names (ie, "VAR01") to all parameter 
         * marker-type variables.  These names are needed when the variable values are 
         * substituted into the SQL source for execution.  We will have to "un-name" the
         * parameter marker variables later. 
         */
        List allVarList = StatementHelper.getAllVariablesInQueryStatement(sqlStatement);
        /* The parameter marker dialog wants a vector rather than a list, so 
         * create one. */
        Vector paramVec = new Vector();
        paramVec.addAll(allVarList);

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
        
        if (paramVec.size() > 0) {
            ParameterWizard wizard = new ParameterWizard(paramVec);

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

                for (int i = 0; i < paramVec.size(); i++) {
                    if (paramVec.elementAt(i) instanceof QueryValueExpression) {
                        expr = (QueryValueExpression) paramVec.elementAt(i);

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
            /* If there were any parameter markers that were modified with
             * variable names, set them back to null and refresh the display
             * so the model change will be picked up. */
            if (paramMarkerList.size() > 0) {
                Iterator paramMarkerListIter = paramMarkerList.iterator();
                while (paramMarkerListIter.hasNext()) {
                    ValueExpressionVariable var = (ValueExpressionVariable) paramMarkerListIter.next();
                    var.setName(null);
                }
                SelectHelper.refresh(sqlStatement);
            }
        }
        return sqlString;
    }
}
