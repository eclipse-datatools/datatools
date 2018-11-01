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

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.jface.wizard.Wizard;


public class ParameterWizard extends Wizard {

    ParameterMarkerPage paramMarkerPage;
    Vector parmValues;

    public ParameterWizard(Vector parms) {
        super();
        setDefaultPageImageDescriptor(SQLBuilderPlugin.getPlugin().getImageDescriptor("parametermarker_wiz"));
        setWindowTitle(Messages._UI_WIZARD_SPECIFY_VARIABLE_VALUES_TITLE);

        paramMarkerPage = new ParameterMarkerPage(parms);
        addPage(paramMarkerPage);
    }

    public void setValueMarkers(Vector v) {
        this.parmValues = v;
    }

    public Vector getParameterMarkers() {
        return parmValues;
    }

    public boolean performFinish() {
        return true;
    }
}
