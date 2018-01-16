/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
