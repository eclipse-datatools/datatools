/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * Compares the SQL objects based on the object name, using the default collator
 * 
 * @author Idull
 */
public class SQLObjectComparator implements Comparator
{
    public int compare(Object obj1, Object obj2)
    {
        if (!(obj1 instanceof SQLObject) || !(obj2 instanceof SQLObject))
        {
            return 0;
        }
        SQLObject sqlObj1 = (SQLObject) obj1;
        SQLObject sqlObj2 = (SQLObject) obj2;
        if (sqlObj1 == null || sqlObj1.getName() == null)
        {
            return -1;
        }
        if (sqlObj2 == null || sqlObj2.getName() == null)
        {
            return 1;
        }
        Collator c = Collator.getInstance(Locale.getDefault());
        return c.compare(sqlObj1.getName(), sqlObj2.getName());
    }
}
