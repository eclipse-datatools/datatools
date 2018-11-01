/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ddl;

import org.eclipse.datatools.enablement.sybase.IGenericDdlConstants;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseIndexMember;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;

public class SybaseDdlUtils implements IGenericDdlConstants, ISybaseDdlConstants
{

    /**
     * Get SQL object name
     * 
     * @param obj
     * @param quoteIdentifiers quoted identifier option
     * @param qualifyNames qualify
     * @return
     */
    public static String getSQLObjectName(SQLObject obj, boolean quoteIdentifiers, boolean qualifyNames)
    {
        if (obj == null)
        {
            return "";
        }
        if (obj instanceof Table)
        {
            return getSQLObjectName((Table) obj, quoteIdentifiers, qualifyNames);
        }
        else if (obj instanceof Routine)
        {
            return getSQLObjectName((Routine) obj, quoteIdentifiers, qualifyNames);
        }
        else if (obj instanceof SQLObject)
        {
            if (quoteIdentifiers)
            {
                return SQLUtil.quote(((SQLObject) obj).getName(), DOUBLE_QUOTE);
            }
            else
            {
                return ((SQLObject) obj).getName();
            }
        }
        return "";
    }
    
    /**
     * Get index members stattement by the members list
     * 
     * @param members Index member list
     * @param quoteIdentifiers quoted identifier option
     * @return
     */
    public static String getIndexMemberKeys(EList members, boolean quoteIdentifiers)
    {
        StringBuffer result = new StringBuffer();
        for (int i=0;i< members.size();i++)
        {
            IndexMember m = (IndexMember)members.get(i);
            if (i==0)
            {
                result.append(getIndexMember(quoteIdentifiers, m, false));
            }
            else
            {
                result.append(getIndexMember(quoteIdentifiers, m, true));
            }           
        }       
        return result.toString();
    }

    /**
     * Get the index member
     * 
     * @param quoteIdentifiers quoted identifier option
     * @param member index member
     * @param hasComma comma flag
     * @return
     */
    public static String getIndexMember(boolean quoteIdentifiers, IndexMember member, boolean hasComma)
    {
        StringBuffer result = new StringBuffer(""); //$NON-NLS-1$
        if (hasComma)
        {
            result.append(SPACE+ COMMA + SPACE);
        }
        if (member instanceof SybaseIndexMember)
        {
        	
            if (member.getName() == null)
            {
                result.append(((SybaseIndexMember) member).getColumnExpression());
            }
            else
            {
                result.append(((SybaseIndexMember) member).getColumnExpression() + SPACE + AS + SPACE + member.getName());
            }
        }
        else
        {
        	if(member.getColumn()!=null)
        	{
                if (quoteIdentifiers)
                {
                    result.append(SQLUtil.quote(member.getColumn().getName(), DOUBLE_QUOTE));
                }
                else
                {
                    result.append(member.getColumn().getName());
                }
        	} 
        }
        if (member instanceof SybaseIndexMember && member.getName()!=null)
        {
           return result.toString();
        }
        else
        {
            result.append(SPACE + member.getIncrementType().getName());
        }
        return result.toString();
    }
    
    /**
     * Checked the index member is equals, used in detal ddl
     * 
     * @param newValue
     * @param oldValue
     * @return
     */
    public static boolean isEqualIndexMembers(Object newValue, Object oldValue)
    {
        if (newValue == oldValue)
        {
            return true;
        }
        if (newValue instanceof EList && ((EList) newValue).size() > 0
                && ((EList) newValue).get(0) instanceof IndexMember)
        {
            EList newList = (EList) newValue;
            EList oldList = (EList) oldValue;
            if (SybaseDdlUtils.getIndexMemberKeys(newList, true).equals(
                    SybaseDdlUtils.getIndexMemberKeys(oldList, true)))
            {
                return true;
            }
        }
        if (newValue != null)
        {
            return newValue.equals(oldValue);
        }
        return newValue == oldValue;
    }
}
