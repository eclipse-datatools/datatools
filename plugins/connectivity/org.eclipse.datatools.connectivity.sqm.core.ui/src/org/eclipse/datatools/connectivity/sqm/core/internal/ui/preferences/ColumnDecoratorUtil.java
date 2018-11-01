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
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.preferences;

/**
 * @author ljulien
 */
public class ColumnDecoratorUtil
{
    private static final LabelDecoratorPreference preference = new LabelDecoratorPreference();
    
    public static String getPKFKColumnDecoration (String dataType)
    {
        return preference.getColumnDecoration(dataType, false, true, true);
    }
    public static String getPKColumnDecoration (String dataType)
    {
        return preference.getColumnDecoration(dataType, false, true, false);
    }
    public static String getFKNullableColumnDecoration (String dataType)
    {
        return preference.getColumnDecoration(dataType, true, false, true);
    }
    public static String getNullableColumnDecoration (String dataType)
    {
        return preference.getColumnDecoration(dataType, true, false, false);
    }
    public static String getFKColumnDecoration (String dataType)
    {
        return preference.getColumnDecoration(dataType, false, false, true);
    }
    public static String getColumnDecoration (String dataType)
    {
        return preference.getColumnDecoration(dataType, false, false, false);
    }
}
