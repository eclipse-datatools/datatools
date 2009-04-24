/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.commonui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils.SQLObjectComparator;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;


/**
 * The content provider for schema objects viewer
 * 
 * @author Idull
 */
public class SchemaObjectsViewerContentProvider implements IStructuredContentProvider
{
    protected int           _objsType;
    public static final int INDEXES_TYPE  = 0;
    public static final int TRIGGERS_TYPE = 1;

    public SchemaObjectsViewerContentProvider(int type)
    {
        _objsType = type;
    }

    public Object[] getElements(Object inputElement)
    {
        if (inputElement == null)
        {
            return new Object[0];
        }
        if (inputElement instanceof Table)
        {
            Table table = (Table) inputElement;
            switch (_objsType)
            {
                case INDEXES_TYPE:
                    if (table.getIndex() != null)
                    {
                        return getSortedIndexes(table);
                    }
                    break;
                case TRIGGERS_TYPE:
                    if (table.getTriggers() != null)
                    {
                        return getSortedTriggers(table);
                    }
                    break;
                default:
                    break;
            }
        }
        return new Object[0];
    }

    public void dispose()
    {

    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
    {

    }

    protected Object[] getSortedIndexes(Table table)
    {
        List indexes = new ArrayList();
        indexes.addAll(table.getIndex());
        Collections.sort(indexes, new SQLObjectComparator());
        return indexes.toArray(new Index[indexes.size()]);
    }

    protected Object[] getSortedTriggers(Table table)
    {
        List triggers = new ArrayList();
        triggers.addAll(table.getTriggers());
        Collections.sort(triggers, new SQLObjectComparator());
        return triggers.toArray(new Trigger[triggers.size()]);
    }
}
