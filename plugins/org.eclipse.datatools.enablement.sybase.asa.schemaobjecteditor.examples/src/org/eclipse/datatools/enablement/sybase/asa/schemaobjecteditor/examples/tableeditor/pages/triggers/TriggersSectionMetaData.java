/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.triggers;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.commonui.ISchemaObjectsViewerMetaData;

/**
 * Meta data of "Triggers" section
 * 
 * @author Idull
 */
public class TriggersSectionMetaData implements ISchemaObjectsViewerMetaData
{
    public static final int      NAME_COL         = 0;
    public static final int      LEVEL_COL        = 1;
    public static final int      TRIGGER_TIME_COL = 2;
    public static final int      EVENT_COL        = 3;
    public static final int      ORDER_COL        = 4;

    public static final String[] COLUMN_NAMES     = new String[]
                                                  {
        Messages.TriggersSectionMetaData_name, Messages.TriggersSectionMetaData_level,
        Messages.TriggersSectionMetaData_trigger_time, Messages.TriggersSectionMetaData_event,
        Messages.TriggersSectionMetaData_order
                                                  };
    public static final int[]    COLUMN_WIDTH     = new int[]
                                                  {
        130, 100, 110, 170, 60
                                                  };

    public int getColumnCount()
    {
        return COLUMN_NAMES.length;
    }

    public String getColumnName(int colIndex)
    {
        if (colIndex < 0 || colIndex >= getColumnCount())
        {
            return ""; //$NON-NLS-1$
        }
        return COLUMN_NAMES[colIndex];
    }

    public int getColumnWidth(int colIndex)
    {
        if (colIndex < 0 || colIndex >= getColumnCount())
        {
            return 0;
        }
        return COLUMN_WIDTH[colIndex];
    }

}
