/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.examples.util;

import java.util.Date;

import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Filters the result history based on current SQL statement.
 * It shows only those statements executed for the current connection profile.
 * 
 * @author Jeremy Lindop
 */
public class ResultsHistoryFilter extends ViewerFilter
{
    String _profileName;
    Date _creationDate;
    
    /**
     * Constructor
     * @param store the preference store used to store the filters information
     */
    public ResultsHistoryFilter(String profile)
    {
        super();
        if (profile == null){
        	_profileName = "";
        }
        else {
        	_profileName = profile;
        }
        _creationDate = new Date();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public boolean select(Viewer viewer, Object parentElement, Object element)
    {
        if(element instanceof IResultInstance)
        {
        	IResultInstance instance = (IResultInstance)element;
            if (_profileName.equals(instance.getOperationCommand().getProfileName())){
            	if (_creationDate.before(instance.getExecuteDate()))
            	return true;
            }
         }

        return false;
    }
}
