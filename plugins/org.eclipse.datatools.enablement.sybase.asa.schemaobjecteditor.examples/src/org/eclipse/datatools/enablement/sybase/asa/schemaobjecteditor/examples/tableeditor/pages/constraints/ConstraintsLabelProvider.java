/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.constraints;

import java.util.Iterator;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.FolderNode;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.LeafNode;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SybaseImages;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * The label provider for constraints tree on "Constraints" page of table schema editor
 *
 * <pre>
 *  root
 *  |-- Constraint
 *       |
 *       |---Primary Key
 *       |---Unique Constraints
 *       |---Foreign Keys
 *       |---Check Constraints
 * </pre>
 * 
 * @author Idull
 */
public class ConstraintsLabelProvider implements ITableLabelProvider
{
    public Image getColumnImage(Object element, int columnIndex)
    {
        if (!(element instanceof LeafNode) && columnIndex == 0)
        {
            return SybaseImages.get(SybaseImages.IMG_FOLDER);
        }
        if (columnIndex == 0 && (element instanceof LeafNode))
        {
            LeafNode leafNode = (LeafNode) element;
            Object constraint = leafNode.getData();
            if (constraint instanceof PrimaryKey)
            {
                return SybaseImages.get(SybaseImages.IMG_PK);
            }
            if (constraint instanceof UniqueConstraint)
            {
                return SybaseImages.get(SybaseImages.IMG_UNIQUE);
            }
            if (constraint instanceof ForeignKey)
            {
                return SybaseImages.get(SybaseImages.IMG_FK);
            }
            if (constraint instanceof CheckConstraint)
            {
                return SybaseImages.get(SybaseImages.IMG_CK);
            }
        }
        return null;
    }

    public String getColumnText(Object element, int columnIndex)
    {
        if (!(element instanceof FolderNode))
        {
            return "";
        }
        FolderNode node = (FolderNode) element;

        switch (columnIndex)
        {
            case SQLConstraintsBlock.ConstraintsSection.CONSTRAINT_NAME_COLUMN:
                if (node instanceof LeafNode)
                {
                    LeafNode leafNode = (LeafNode) element;
                    String dspName = "";
                    if(leafNode.isDirty())
                    {
                        dspName = "* ";
                    }
                    if(((SQLObject) leafNode.getData()).getName() != null)
                    {
                        dspName += ((SQLObject) leafNode.getData()).getName();
                    }
                    return dspName;
                }
                return node.getName();
            case SQLConstraintsBlock.ConstraintsSection.CONSTRAINT_INFO_COLUMN:
                if (!(element instanceof LeafNode))
                {
                    return "";
                }
                LeafNode leafNode = (LeafNode) element;
                Object constraint = leafNode.getData();
                if (constraint instanceof UniqueConstraint)
                {
                    UniqueConstraint unique = (UniqueConstraint) constraint;
                    StringBuffer sb = new StringBuffer("(");
                    Iterator iter = unique.getMembers().iterator();
                    while (iter.hasNext())
                    {
                        if (!sb.toString().equals("("))
                        {
                            sb.append(",");
                        }
                        Column col = (Column) iter.next();
                        sb.append(col.getName());
                    }
                    sb.append(")");
                    return sb.toString();
                }
                if (constraint instanceof ForeignKey)
                {
                    ForeignKey fk = (ForeignKey) constraint;
                    StringBuffer sb = new StringBuffer("(");
                    Iterator iter = fk.getMembers().iterator();
                    while (iter.hasNext())
                    {
                        if (!sb.toString().equals("("))
                        {
                            sb.append(",");
                        }
                        Column col = (Column) iter.next();
                        sb.append(col.getName());
                    }
                    sb.append(") reference ").append(
                            fk.getReferencedTable() == null ? "" : fk.getReferencedTable().getName()).append("(");

                    iter = fk.getReferencedMembers().iterator();
                    while (iter.hasNext())
                    {
                        if (sb.toString().charAt(sb.length() - 1) != '(')
                        {
                            sb.append(",");
                        }
                        Column col = (Column) iter.next();
                        // sb.append(fk.getReferencedTable().getName()).append(".").append(col.getName());
                        sb.append(col.getName());
                    }
                    sb.append(")");
                    return sb.toString();
                }

                if (constraint instanceof CheckConstraint)
                {
                    CheckConstraint ck = (CheckConstraint) constraint;
                    return ck.getSearchCondition() == null ? "" : ck.getSearchCondition().getSQL();
                }
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
