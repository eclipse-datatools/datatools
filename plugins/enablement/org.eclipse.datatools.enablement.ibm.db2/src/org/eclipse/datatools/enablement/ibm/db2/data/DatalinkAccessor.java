/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.data;

import org.eclipse.datatools.sqltools.data.internal.core.common.DefaultColumnDataAccessor;


public class DatalinkAccessor extends DefaultColumnDataAccessor {

	public String getSelectExpr() {
		String col = super.getSelectExpr();
        return "DLURLCOMPLETEONLY(" + col + ")";
	}
    
	public boolean supportsInlineEdit() {		
		return false;
	}
    
    
}
