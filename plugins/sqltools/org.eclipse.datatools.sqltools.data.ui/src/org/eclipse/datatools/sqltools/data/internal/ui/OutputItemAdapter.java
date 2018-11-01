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
package org.eclipse.datatools.sqltools.data.internal.ui;

import org.eclipse.datatools.sqltools.data.internal.core.common.Output;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsViewAPI;

/**
 * @author groux
 */
public class OutputItemAdapter implements Output {

    protected OperationCommand outputItem; 
    
    public OutputItemAdapter(OperationCommand outputItem)
    {
        this.outputItem = outputItem;
    }
    
    public void write(String s)
    {
    	ResultsViewAPI resultsView = ResultsViewAPI.getInstance();
        resultsView.createNewInstance(outputItem, null);
        resultsView.appendPlainMessage(outputItem, s);
    	//ResultsViewAPI.getInstance().appendPlainMessage(outputItem, s);
    }

}
