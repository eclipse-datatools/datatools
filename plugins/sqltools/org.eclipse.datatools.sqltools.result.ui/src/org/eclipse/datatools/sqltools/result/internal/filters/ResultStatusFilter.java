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
package org.eclipse.datatools.sqltools.result.internal.filters;

import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.internal.model.ResultInstance;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Filters the result history based on result status
 * 
 * @author Dafan Yang
 */
public class ResultStatusFilter extends ViewerFilter
{
    IPreferenceStore _store;
    
    /**
     * Constructor
     * @param store the preference store used to store the filters information
     */
    public ResultStatusFilter(IPreferenceStore store)
    {
        super();
        _store = store;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public boolean select(Viewer viewer, Object parentElement, Object element)
    {
        if(element instanceof ResultInstance)
        {
            ResultInstance instance = (ResultInstance)element;
            
            // The sub results are always displayed
            if(!instance.isParentResult())
            {
                return true;
            }
            switch (instance.getStatus())
            {
                case OperationCommand.STATUS_SUCCEEDED:
                    if(_store.getBoolean(PreferenceConstants.PROFILE_FILTERS_STATUS_SUCCESS))
                    {
                        return true;
                    }
                    return false;
                case OperationCommand.STATUS_FAILED:
                    if(_store.getBoolean(PreferenceConstants.PROFILE_FILTERS_STATUS_FAILED))
                    {
                        return true;
                    }
                    return false;
                case OperationCommand.STATUS_TERMINATED:
                    if(_store.getBoolean(PreferenceConstants.PROFILE_FILTERS_STATUS_TERMINATED))
                    {
                        return true;
                    }
                    return false;
                case OperationCommand.STATUS_WARNING:
                    if(_store.getBoolean(PreferenceConstants.PROFILE_FILTERS_STATUS_WARNING))
                    {
                        return true;
                    }
                    return false;
                case OperationCommand.STATUS_CRITICAL_ERROR:
                    if(_store.getBoolean(PreferenceConstants.PROFILE_FILTERS_STATUS_CRITICAL))
                    {
                        return true;
                    }
                    return false;
                // Always display the running and started items
                case OperationCommand.STATUS_RUNNING:
                    return true;
                case OperationCommand.STATUS_STARTED:
                    return true;
                default:
                    break;
            }
        }

        //should not happen
        return false;
    }
}
