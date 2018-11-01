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
package org.eclipse.datatools.enablement.ase.ddl;

import java.util.ArrayList;

import org.eclipse.datatools.enablement.ase.ISybaseASEDdlConstants;
import org.eclipse.datatools.enablement.sybase.IGenericDdlConstants;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheStrategyType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseIndexMember;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.emf.common.util.EList;

public class ASEDdlUtils implements IGenericDdlConstants, ISybaseASEDdlConstants
{
    /**
     * Get cache binding statement
     *
     * @param cache Cache name
     * @param index Index model
     * @return Cache binding statment
     */
    public static String getCacheBindStatement(String cache, Index index, boolean qualifyNames, boolean fullSyntax, DatabaseIdentifier dbId )
    {
        StringBuffer statment = new StringBuffer();
        statment.append(EXEC + SPACE + SP_BINDCACHE + SPACE);
        statment.append(SQLUtil.quote(cache, SINGLE_QUOTE) + COMMA + SPACE);
        statment.append(SQLUtil.quote(index.getTable().getSchema().getCatalog().getName(), SINGLE_QUOTE) + COMMA
                + SPACE);
        statment.append(SQLUtil.quote(index.getTable().getSchema().getName() + DOT + index.getTable().getName(),
                SINGLE_QUOTE)
                + COMMA + SPACE);
        statment.append(SQLUtil.quote(SQLDevToolsUtil.quoteWhenNecessary(index.getName(),dbId), SINGLE_QUOTE));
        return statment.toString();
    }

    /**
     * Get change attributes statments
     * @param object
     * @param attribute Attribute
     * @param value  Attribute value
     * @return the change attribute statment
     */
    public static String getChangeAttributeStatement(String object, String attribute, String value)
    {
        StringBuffer statment = new StringBuffer();
        statment.append(EXEC + SPACE + SP_CHGATTRIBUTE + SPACE);
        statment.append(object + COMMA + SPACE);
        statment.append(attribute + COMMA + SPACE);
        statment.append(value);
        return statment.toString();
    }

    /**
     * Get partition name
     * 
     * @param index
     * @param quoteIdentifiers quoted identifier
     * @param qualifyNames qualified
     * @return partition name
     */
    public static String getPartitionName(SybaseASEIndex index, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer result = new StringBuffer();
        EList list = index.getPartitions();

        for (int i = 0; i < list.size(); i++)
        {
            PartitionSegmentPair partion = (PartitionSegmentPair) list.get(i);
            if (i > 0)
            {
                result.append(NEWLINE + TAB + COMMA + SPACE);
            }
            result.append(partion.getPartitionName());
            if (partion.getSegment() != null)
            {
                result.append(SPACE + ON + SPACE + SQLUtil.quote(partion.getSegment().getName(),"\'"));
            }
        }
        return result.toString();
    }

    public static String[] getAllCacheStatement(SybaseASEIndex index, boolean qualifyNames, boolean fullSyntax, DatabaseIdentifier dbId)
    {
        CacheInfo cacheInfo = index.getCacheInfo();
        ArrayList statements = new ArrayList();
        if (cacheInfo != null && cacheInfo.getCache() != null)
        {
            statements.add(getCacheBindStatement(cacheInfo.getCache().getName(), index, qualifyNames, fullSyntax, dbId));
        }
        if (cacheInfo != null && cacheInfo.getCacheStrategy() >= 0)
        {
            int cacheStrategy = cacheInfo.getCacheStrategy();
            if ((cacheStrategy & CacheStrategyType.PREFETCH) != 0 && fullSyntax)
            {
                statements.add(getCacheStrategyStatement(PREFETCH, index, true, dbId));
            }
            if ((cacheStrategy & CacheStrategyType.PREFETCH) == 0)
            {
                statements.add(getCacheStrategyStatement(PREFETCH, index, false, dbId)); 
            }
            // exec sp_cachestrategy 'testdb', 'dbo.testTable', 'tes3232', mru, 'off'
            if ((cacheStrategy & CacheStrategyType.MRU) != 0 && fullSyntax)
            {
                statements.add(getCacheStrategyStatement(MRU, index, true, dbId));
            }
            if ((cacheStrategy & CacheStrategyType.MRU) == 0)
            {
                statements.add(getCacheStrategyStatement(MRU, index, false, dbId));
            }
        }
        return (String[]) statements.toArray(new String[statements.size()]);
    }

    /**
     * Get Cache strategy statement
     * 
     * @param stragegy
     * @param index
     * @param on
     * @return
     */
    public static String getCacheStrategyStatement(String stragegy, Index index, boolean on, DatabaseIdentifier dbId)
    {
        // exec sp_cachestrategy 'pubs2', 'dbo.titles', 'titleidind', prefetch, 'off'
        StringBuffer statment = new StringBuffer();
        statment.append(EXEC + SPACE + SP_CACHESTRATEGY + SPACE);
        String databseName = index.getTable().getSchema().getCatalog().getName();
        statment.append(SQLUtil
                .quote(databseName, SINGLE_QUOTE)
                + COMMA + SPACE);
        String schemaName = index.getTable().getSchema().getName();
        statment.append(SQLUtil.quote(schemaName + DOT
                + index.getTable().getName(), SINGLE_QUOTE)
                + COMMA + SPACE);
        statment.append(SQLUtil.quote(SQLDevToolsUtil.quoteWhenNecessary(index.getName(), dbId),SINGLE_QUOTE));
        statment.append(COMMA + SPACE + SQLUtil.quote(stragegy, SINGLE_QUOTE) + COMMA + SPACE);
        statment.append(SQLUtil.quote(on ? ON.toLowerCase() : OFF.toLowerCase(), SINGLE_QUOTE));
        return statment.toString();
    }

	public static String getIndexMemberKeys(EList members, boolean quoteIdentifiers) {
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
            boolean isHidden = false;         
            if (member.getColumn() instanceof SybaseASEColumn)
            {
                isHidden = ((SybaseASEColumn)member.getColumn()).isHidden();
            }
            // computed column 
            if (((SybaseIndexMember) member).getName() != null && !isHidden)
            {
                result.append(((SybaseIndexMember) member).getName());
            }
            // function-based index(with column name)
            else if (((SybaseIndexMember) member).getColumnExpression() != null && isHidden)
            {
                result.append(((SybaseIndexMember) member).getColumnExpression());
            }
            // user input function-based index(no column name)
            else if (((SybaseIndexMember) member).getColumnExpression() != null && member.getColumn() == null)
            {
                result.append(((SybaseIndexMember) member).getColumnExpression());
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
        result.append(SPACE + member.getIncrementType().getName());
 
        return result.toString();
    }
}
