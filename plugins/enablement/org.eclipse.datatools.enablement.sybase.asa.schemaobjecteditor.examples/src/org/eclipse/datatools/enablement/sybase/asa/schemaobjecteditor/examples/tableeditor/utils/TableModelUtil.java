/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;

/**
 * Table related utilities
 * 
 * @author Idull
 */
public class TableModelUtil
{
    /**
     * Returns all the unique constraints of the given table which only reference the given column
     * 
     * @param table the base table
     * @param column the referencing column
     * @return
     */
    public static List getMatchedColumnUniqueConstraint(BaseTable table, Column column)
    {
        List constraints = new ArrayList();
        Iterator iter = table.getUniqueConstraints().iterator();
        while (iter.hasNext())
        {
            UniqueConstraint unique = (UniqueConstraint) iter.next();
            List members = unique.getMembers();
            if (members.size() != 1)
            {
                continue;
            }
            if (members.get(0).equals(column))
            {
                constraints.add(unique);
            }
        }

        return constraints;
    }

    public static List getMatchedColumnCheckConstraint(BaseTable table, Column column)
    {
        List constraints = new ArrayList();
        Iterator iter = table.getConstraints().iterator();
        while (iter.hasNext())
        {
            Constraint obj = (Constraint) iter.next();
            if (obj instanceof SybaseASABaseColumnCheckConstraint)
            {
                SybaseASABaseColumnCheckConstraint columnCk = (SybaseASABaseColumnCheckConstraint) obj;
                if (columnCk.getColumn() == column)
                {
                    constraints.add(obj);
                }
            }
        }

        return constraints;
    }

    /**
     * Returns all the foreign keys which reference the given column
     * 
     * @param table
     * @param column
     * @return
     */
    public static List getMatchedColumnForeignKeys(BaseTable table, Column column)
    {
        List constraints = new ArrayList();
        Iterator iter = table.getForeignKeys().iterator();
        while (iter.hasNext())
        {
            ForeignKey fk = (ForeignKey) iter.next();
            List members = fk.getMembers();
            if (members.size() < 1)
            {
                continue;
            }
            if (members.contains(column))
            {
                constraints.add(fk);
            }
        }
        return constraints;
    }

    /**
     * Checks if the given column is referenced in one of the reference constraints of the given table
     * 
     * @param table
     * @param column
     * @return
     */
    public static boolean isReferencedColumn(BaseTable table, Column column)
    {
        Iterator iter = table.getConstraints().iterator();
        while (iter.hasNext())
        {
            Constraint constraint = (Constraint) iter.next();
            if (constraint instanceof ReferenceConstraint)
            {
                ReferenceConstraint refConstraint = (ReferenceConstraint) constraint;
                if (refConstraint.getMembers().contains(column))
                {
                    return true;
                }
            }
            if (constraint instanceof SybaseASABaseColumnCheckConstraint)
            {
                SybaseASABaseColumnCheckConstraint columnCk = (SybaseASABaseColumnCheckConstraint) constraint;
                if (columnCk.getColumn() == column)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static void removeColumnFromRefConstraints(BaseTable table, Column column)
    {
        Iterator iter = table.getConstraints().iterator();
        while (iter.hasNext())
        {
            Constraint constraint = (Constraint) iter.next();
            if (constraint instanceof ReferenceConstraint)
            {
                ReferenceConstraint refConstraint = (ReferenceConstraint) constraint;
                if (refConstraint.getMembers().contains(column))
                {
                    refConstraint.getMembers().remove(column);
                }
            }
        }
    }

    public static void removePrivilegeForColumn(Column column, List authids)
    {
        Iterator iter = authids.iterator();
        while (iter.hasNext())
        {
            AuthorizationIdentifier authid = (AuthorizationIdentifier) iter.next();
            Iterator pIter = authid.getReceivedPrivilege().iterator();
            List privilegeTobeRemoved = new ArrayList();
            while (pIter.hasNext())
            {
                Privilege p = (Privilege) pIter.next();
                if (p.getObject() == column)
                {
                    privilegeTobeRemoved.add(p);
                }
            }
            authid.getReceivedPrivilege().removeAll(privilegeTobeRemoved);
        }
    }

    /**
     * Returns the columns list which is not nullable
     * 
     * @param table
     * @return
     */
    public static List getNotNullableColumns(BaseTable table)
    {
        if (table == null || table.getColumns() == null)
        {
            return new ArrayList(0);
        }
        List notNullableColumns = new ArrayList();
        Iterator iter = table.getColumns().iterator();
        while (iter.hasNext())
        {
            Column col = (Column) iter.next();
            if (!col.isNullable())
            {
                notNullableColumns.add(col);
            }
        }
        return notNullableColumns;
    }

    public static String constructConstraintNamesList(List matches)
    {
        StringBuffer sb = new StringBuffer("");
        Iterator iter = matches.iterator();
        while (iter.hasNext())
        {
            if (!sb.toString().equals(""))
            {
                sb.append(",");
            }
            String name = ((Constraint) iter.next()).getName();
            if (name == null || name.equals(""))
            {
                name = " ";
            }
            sb.append(name);
        }
        return sb.toString();
    }
}
