/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.result;

/**
 * Store preference in non-ui part of result view.
 * 
 * @author juewu
 */
public class ResultConfiguration
{
    private static ResultConfiguration INSTANCE;

    private int                        maxRowCount        = 500;

    private int                        maxDisplayRowCount = 500;

    private boolean                    autoSave           = true;

    private boolean                    autoClean          = false;
    
    private ResultConfiguration()
    {
    }

    public static ResultConfiguration getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new ResultConfiguration();
        }

        return INSTANCE;
    }

    public void setMaxRowCount(int maxRowCount)
    {
        this.maxRowCount = maxRowCount;
    }

    public int getMaxRowCount()
    {
        return this.maxRowCount;
    }

    public void setMaxDisplayRowCount(int maxDisplayRowCount)
    {
        this.maxDisplayRowCount = maxDisplayRowCount;
    }

    public int getMaxDisplayRowCount()
    {
        return this.maxDisplayRowCount;
    }

    public void setAutoSave(boolean autoSave)
    {
        this.autoSave = autoSave;
    }

    public boolean isAutoSave()
    {
        return autoSave;
    }

    public void setAutoClean(boolean autoClean)
    {
        this.autoClean = autoClean;
    }

    public boolean isAutoClean()
    {
        return autoClean;
    }
}
