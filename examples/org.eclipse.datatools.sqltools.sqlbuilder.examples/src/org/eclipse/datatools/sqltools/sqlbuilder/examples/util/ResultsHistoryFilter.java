/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.examples.util;

//import org.eclipse.datatools.sqltools.result.internal.model.ResultInstance;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Filters the result history based on current SQL statement
 * @author Jeremy Lindop
 */
public class ResultsHistoryFilter extends ViewerFilter
{
    String _profileName;
    
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
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public boolean select(Viewer viewer, Object parentElement, Object element)
    {
//        if(element instanceof ResultInstance)
//        {
//        	ResultInstance instance = (ResultInstance)element;
//            if (_profileName.equals(instance.getOperationCommand().getProfileName())){
            	return true;
//            }
//            else {
//            	return false;
//            }
//         }
//
//        return false;
    }
}
