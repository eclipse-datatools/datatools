/*******************************************************************************
 * Copyright (c) 2009, 2014 IBM Corporation and others.
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
import org.eclipse.datatools.sqltools.data.internal.core.editor.TableDataImpl;

public class DistinctAccessor extends DefaultColumnDataAccessor
{
	public String getWhereCond(Object val)
    {
    	String s = getQuotedColumnName();
        if (val==null) {
            s += " is null"; //$NON-NLS-1$   	
	    } 
        else {
//        	A parameter marker cannot have a user-defined type or reference type as its data type 
//        	unless it is part of an assignment (VALUES clause of INSERT or SET clause of UPDATE) or 
//        	it is being explicitly cast to a user-defined distinct data type.
	    	s += "=CAST(? AS "; //$NON-NLS-1$
            s += TableDataImpl.getFormattedTypeName(sqlCol) + ")"; //$NON-NLS-1$ //$NON-NLS-2$       }
        }
        return s;
    }

}
