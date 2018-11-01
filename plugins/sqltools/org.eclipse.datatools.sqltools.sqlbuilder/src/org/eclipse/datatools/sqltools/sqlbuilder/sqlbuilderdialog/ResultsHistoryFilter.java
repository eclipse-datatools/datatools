/*******************************************************************************
 * Copyright (c) 2005, 2008 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.sqlbuilderdialog;

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
     * @param profile the connection profile name used in a result instance
     */
    public ResultsHistoryFilter(String profile)
    {
        super();
        init( profile, new Date() );
    }

    /**
     * Constructor
     * @param profile the connection profile name for which statements are executed
     * @param since   the date after which executed statements are included
     */
    public ResultsHistoryFilter(String profile, Date since)
    {
        super();
        init( profile, since );
    }

    private void init( String profile, Date since )
    {
        if (profile == null){
            _profileName = ""; //$NON-NLS-1$
        }
        else {
            _profileName = profile;
        }
        _creationDate = since;
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
