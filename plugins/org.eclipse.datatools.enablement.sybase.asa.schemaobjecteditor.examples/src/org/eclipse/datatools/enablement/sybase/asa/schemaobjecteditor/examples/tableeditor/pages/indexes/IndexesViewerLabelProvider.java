/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.indexes;

import java.util.Iterator;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAIndex;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SybaseImages;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.common.ui.util.Images;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * ASA "Indexes" section label provider
 * 
 * @author Idull
 */
public class IndexesViewerLabelProvider implements ITableLabelProvider
{
    public Image getColumnImage(Object element, int columnIndex)
    {
        if (!(element instanceof SybaseASAIndex))
        {
            return null;
        }
        SybaseASAIndex index = (SybaseASAIndex) element;
        switch (columnIndex)
        {
            case IndexesSectionMetaData.NAME_COL:
                return SybaseImages.get(SybaseImages.IMG_INDEX);
            case IndexesSectionMetaData.UNIQUE_COL:
                if (index.isUnique())
                {
                    return Images.get(Images.IMG_OTHER_CHECKED);
                }
                else
                {
                    return Images.get(Images.IMG_OTHER_UNCHECKED);
                }
            case IndexesSectionMetaData.CLUSTERED_COL:
                if (index.isClustered())
                {
                    return Images.get(Images.IMG_OTHER_CHECKED);
                }
                else
                {
                    return Images.get(Images.IMG_OTHER_UNCHECKED);
                }
            default:
                break;
        }
        return null;
    }

    public String getColumnText(Object element, int columnIndex)
    {
        if (!(element instanceof SybaseASAIndex))
        {
            return "";
        }
        SybaseASAIndex index = (SybaseASAIndex) element;
        switch (columnIndex)
        {
            case IndexesSectionMetaData.NAME_COL:
                return index.getName();
            case IndexesSectionMetaData.COLUMNS_COL:
                StringBuffer sb = new StringBuffer();
                Iterator iter = index.getMembers().iterator();
                while (iter.hasNext())
                {
                    if (sb.toString().length() != 0)
                    {
                        sb.append(",");
                    }
                    IndexMember member = (IndexMember) iter.next();
                    Column col = member.getColumn();
                    sb.append(col.getName());
                }
                return sb.toString();
            case IndexesSectionMetaData.UNIQUE_COL:
            case IndexesSectionMetaData.CLUSTERED_COL:
                return "";
            case IndexesSectionMetaData.DBSPACE_COL:
                return index.getDbSpace().getName();
            default:
                break;
        }
        return "";
    }

    public void addListener(ILabelProviderListener listener)
    {

    }

    public void dispose()
    {

    }

    public boolean isLabelProperty(Object element, String property)
    {
        return false;
    }

    public void removeListener(ILabelProviderListener listener)
    {

    }
}
