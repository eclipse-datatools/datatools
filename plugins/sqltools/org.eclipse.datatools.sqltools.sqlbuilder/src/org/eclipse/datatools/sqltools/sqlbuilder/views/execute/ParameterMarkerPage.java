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
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.TableNavigator;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class ParameterMarkerPage extends WizardPage {

    private Vector parmMarkers;
    private Vector parmValues;
    private ParameterTableViewer paramMarkerTable;
    private Composite tablePanel;

    public ParameterMarkerPage(Vector parms) {
        super(Messages._UI_WIZARD_PARAMETER_MARKER_TITLE);
        this.parmMarkers = parms;
        setTitle(Messages._UI_WIZARD_PARAMETER_MARKER_HEADING);
        setDescription(Messages._UI_WIZARD_PARAMETER_MARKER_EXPL);
        setPageComplete(false);
        parmValues = new Vector();
    }

    public void createControl(Composite parent) {
        Composite mainPanel = new Composite(parent, SWT.NONE);
        GridLayout mainPanelLayout = new GridLayout();
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setLayoutData(ViewUtility.createFill());

        tablePanel = new Composite(mainPanel, SWT.NONE);
        GridLayout tablePanelLayout = new GridLayout();
        tablePanel.setLayout(tablePanelLayout);
        tablePanel.setLayoutData(ViewUtility.createFill());

        fillParameterMarkers();

        paramMarkerTable = new ParameterTableViewer(this, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL, tablePanel, parmMarkers);
        paramMarkerTable.getTable().setLinesVisible(true);
        paramMarkerTable.getTable().setLayoutData(ViewUtility.createFill());
        paramMarkerTable.setInput(parmValues);

        // defect 251018, in linux GTK, we can NOT set the focus on the cell without following changes, we have to setVisible = false for
        // TableNavigator first???
        Control[] tmp = paramMarkerTable.getTable().getChildren();
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] instanceof TableNavigator && !tmp[i].isDisposed()) //
            {
                tmp[i].setVisible(false);
                break;
            }
        }
        // end of change

        setControl(mainPanel);
    }

    private void fillParameterMarkers() {
        for (int i = 0; i < parmMarkers.size(); i++) {
            String defaultValue = "";
            parmValues.add(defaultValue);
        }
        updateFinishButton();
    }

    public boolean updateFinishButton() {
        boolean ready = true;
        for (int i = 0; i < parmMarkers.size(); i++) {
            if (parmValues.elementAt(i) == null || (parmValues.elementAt(i).equals(""))) {
                ready = false;
            }
        }
        //  calling appendQuotes here bcz this method is always called
        //  when cell lose focus to update the state (enable or disable) 
        //  of finish button
        appendQuotes(); //b370 nb 20040729
        setPageComplete(ready);
        return ready;
    }

    /**
     * Appends quotes to the prameters
     * @return
     */
    private void appendQuotes() {
        //b370 nb 20040729 - new method
        ExpressionHelper eh = new ExpressionHelper();
        eh.setQuotesContext("");

        for (int i = 0; i < parmMarkers.size(); i++) {
            if ((parmValues.elementAt(i) != null) && !(parmValues.elementAt(i).equals(""))) {
                Object aParm = parmMarkers.elementAt(i);
                if (aParm instanceof ValueExpressionVariable) {
                    ValueExpressionVariable var = (ValueExpressionVariable) aParm;
                    DataType dType = var.getDataType();
                    parmValues.setElementAt(eh.appendQuotes(dType, parmValues.elementAt(i).toString()), i);                    
                }                	    	
            }
        }
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        performOk();
    }

    public boolean performOk() {
        if (getWizard() instanceof ParameterWizard) {
            ((ParameterWizard) getWizard()).setValueMarkers(parmValues);
        }
        return true;
    }

    public Vector getParameterMarkers() {
        return parmValues;
    }
}
