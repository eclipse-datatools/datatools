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
package org.eclipse.datatools.enablement.ase.deltaddl;

import java.util.List;
import java.util.Map;

import org.eclipse.datatools.enablement.ase.ISybaseASEDdlConstants;
import org.eclipse.datatools.enablement.ase.ddl.ASEDdlUtils;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheStrategyType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlUtils;
import org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * ASE index delta ddl provider
 * 
 * @author Hui Wan
 */
public class SybaseASEIndexDeltaDdlGenProvider extends AbstractDeltaDdlGenProvider implements IDeltaDdlGenProvider, ISybaseASEDdlConstants
{

     
    public void analyze(SQLObject element, Map changeMap, Map modificationRecords)
    {
        super.analyze(element, changeMap, modificationRecords);
        
        SybaseASEIndex index = (SybaseASEIndex)element;
        boolean isSysGenIndex = index.isSystemGenerated() || (index.eContainer() instanceof UniqueConstraint);
        
        if(isSysGenIndex)
        {//if system gen index, no create or drop script needed
            int flag = ((Integer)changeMap.get(element)).intValue();
            if((flag & (SybaseDeltaDdlGeneration.CREATE | SybaseDeltaDdlGeneration.DROP)) != 0)
            {
                changeMap.remove(element);
            }
        }
        
        List records = (List) modificationRecords.get(element);        
        if (records != null)
        {
            for (int i = records.size() - 1; i >= 0; i--)
            {
                SybaseDeltaDdlGeneration.FeatureChangeRecord rc = (SybaseDeltaDdlGeneration.FeatureChangeRecord) records
                        .get(i);
                EStructuralFeature feature = rc.feature;
                // if need drop and rename
                if (needRecreate(feature) && !SybaseDdlUtils.isEqualIndexMembers(rc.newValue, rc.oldValue))
                {
                    changeMap.remove(element);
                    if(element instanceof SybaseASEIndex)
                    {
                        //special handle system generated index 
                        //TODO: modify model to simplify this trick code
                        if(isSysGenIndex)
                        {
                            EObject container = element.eContainer();
                            if(container.eContainer() instanceof PrimaryKey)
                                container = container.eContainer();
//                            changeMap.put(container, new Integer(SybaseDeltaDdlGeneration.CREATE | SybaseDeltaDdlGeneration.DROP));
                            element = (SQLObject)container;
                            if(changeMap.get(element) == null)
                            {//if user add uniqueconstraint and drop it, no delta ddl should generate
                            	continue;
                            }
                            int flag = ((Integer)changeMap.get(element)).intValue();
                            if((flag & (SybaseDeltaDdlGeneration.CREATE | SybaseDeltaDdlGeneration.DROP)) == 0)
                            	changeMap.put(element, new Integer(SybaseDeltaDdlGeneration.CREATE | SybaseDeltaDdlGeneration.DROP));
                        }
                        else
                        {
                            changeMap.put(element, new Integer(SybaseDeltaDdlGeneration.CREATE | SybaseDeltaDdlGeneration.DROP));
                        }
                    }
                }
            }
        }
    }
    
    protected boolean needRecreate(EStructuralFeature feature)
    {
        if (feature != null)
        {

            switch (feature.getFeatureID())
            {
                case SQLConstraintsPackage.INDEX__NAME:
                    return false;
                case SQLConstraintsPackage.INDEX__DESCRIPTION:
                    return false;
                case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__FILL_FACTOR:
                    return false;
                case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__MAX_ROW_PER_PAGE:
                    return false;
                case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__REVERSE_PAGE_GAP:
                    return false;
                case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CACHE_INFO:
                    return false;
                case SybaseasesqlmodelPackage.CACHE_INFO__CACHE_STRATEGY:
                    return false;
                case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SEGMENT:
                    return false;
                default:
                    return true;
            }
        }
        return true;
    }

    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {
        script.addCreateIndexStatement(statement);
    }

    protected void addDropStatement(SybaseDdlScript script, String statement)
    {
        script.addDropIndexStatement(statement);
    }

    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        SybaseASEIndex index = (SybaseASEIndex) e;
        int featureId = feature.getFeatureID();
        Table table = index.getTable();
        String owner = table.getSchema().getName();
        String tableName = table.getName();
        String oldIndexFullName = SQLUtil.quote(SQLDevToolsUtil.quoteWhenNecessary(index.getTable().getName(),(DatabaseIdentifier)getParameter()) + DOT + SQLDevToolsUtil.quoteWhenNecessary(index.getName(),(DatabaseIdentifier)getParameter()),
                SINGLE_QUOTE);
        StringBuffer result = new StringBuffer(256);
        if(feature == SybaseasesqlmodelPackage.eINSTANCE.getCacheInfo_Cache())
        {
            result.append(ASEDdlUtils.getCacheBindStatement(((SybaseASECache) newValue).getName(), index,
                     qualifyNames, fullSyntax, (DatabaseIdentifier)getParameter()));
        }
        else if(feature == EcorePackage.eINSTANCE.getENamedElement_Name())
        {
            result.append(SP_RENAME + SPACE);
            result.append(SQLUtil.quote(tableName + DOT + oldValue, SINGLE_QUOTE));
            result.append(COMMA + SPACE);
            if(quoteIdentifiers)
            {
                result.append(SQLDevToolsUtil.quoteWhenNecessary(String.valueOf(newValue),(DatabaseIdentifier)getParameter()));
            }
            else
            {
                result.append(SQLUtil.quote(String.valueOf(newValue), SINGLE_QUOTE));
            }
            result.append(COMMA + SPACE);
            result.append(SINGLE_QUOTE + INDEX.toLowerCase() + SINGLE_QUOTE);
        }
        else if(feature == SQLConstraintsPackage.eINSTANCE.getIndex_Clustered())
        {
            result.append(ALTER + SPACE + INDEX+SPACE);
            result.append(oldIndexFullName);
            result.append(COMMA + SPACE);
            result.append(SQLUtil.quote(String.valueOf(newValue), SINGLE_QUOTE));
        }
        else if(feature == SQLConstraintsPackage.eINSTANCE.getIndex_FillFactor())
        {
        	//DDL should be generated only if newValue is different from oldValue and they don't equal to server wide default.
            if(((Integer)newValue).intValue()!=((Integer)oldValue).intValue()
            		&&!(isEqualToServerWideDefault((Integer)oldValue)&&isEqualToServerWideDefault((Integer)newValue)))
            {
                String value = ((Integer) newValue).intValue() > 0 ? newValue.toString() : "0";
                result.append(ASEDdlUtils.getChangeAttributeStatement(oldIndexFullName, SQLUtil.quote(FILLFACTOR,
                        SINGLE_QUOTE), value));
            }
        }
        else if(feature == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEIndex_MaxRowPerPage())
        {
        	//DDL should be generated only if newValue is different from oldValue and they don't equal to server wide default.
            if(((Integer)newValue).intValue()!=((Integer)oldValue).intValue()
            		&&!(isEqualToServerWideDefault((Integer)newValue)&&isEqualToServerWideDefault((Integer)oldValue)))
            {
                String value = ((Integer) newValue).intValue() > 0 ? newValue.toString() : "0";
                result.append(ASEDdlUtils.getChangeAttributeStatement(oldIndexFullName, SQLUtil.quote(MAX_ROWS_PER_PAGE,
                        SINGLE_QUOTE), value));   
            }
            
        }
        else if(feature == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEIndex_ReversePageGap())
        {
        	//DDL should be generated only if newValue is different from oldValue and they don't equal to server wide default.
            if(((Integer)newValue).intValue()!=((Integer)oldValue).intValue()
            		&&!(isEqualToServerWideDefault((Integer)newValue)&&isEqualToServerWideDefault((Integer)oldValue)))
            {
                String value = ((Integer) newValue).intValue() > 0 ? newValue.toString() : "0";
                result.append(ASEDdlUtils.getChangeAttributeStatement(oldIndexFullName, SQLUtil.quote(RESERVEPAGEGAP,
                        SINGLE_QUOTE), value));                
            }
        }
        else if(feature == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEIndex_Segment())
        {
            String segName;
            if(oldValue==null&&newValue!=null)
            {
                segName = ((SybaseASESegment)newValue).getName();
            }
            else if(oldValue!=null&&newValue==null)
            {
                if(!((SybaseASEBaseTable)table).getSegment().getName().equalsIgnoreCase(((SybaseASESegment)oldValue).getName()))
                {
                    segName = ((SybaseASEBaseTable)table).getSegment().getName();
                }
                else
                {
                    return;
                }
            }
            else if(oldValue!=null&&newValue!=null)
            {
                if(!((SybaseASESegment)oldValue).getName().equalsIgnoreCase(((SybaseASESegment)newValue).getName()))
                {
                    segName = ((SybaseASESegment)newValue).getName();
                }
                else
                {
                    return;
                }
            }
            else
            {
                return;
            }
            result.append(EXEC + SPACE + SP_PLACEOBJECT + SPACE);
            result.append(SQLUtil.quote(segName, SINGLE_QUOTE));            
            result.append(COMMA + SPACE);
            result.append(SQLUtil.quote(owner + DOT + tableName + DOT +index.getName(), SINGLE_QUOTE));
        }
        else if(feature == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEIndex_CacheInfo())
        {
            CacheInfo cacheInfo = ((CacheInfo) newValue);
            String[] statments = ASEDdlUtils.getAllCacheStatement(index, qualifyNames, fullSyntax, (DatabaseIdentifier)getParameter());
            for (int i = 0; i < statments.length; i++)
            {
                script.addAlterOtherStatements(statments[i]);
            }
        }
        else if(feature == SybaseasesqlmodelPackage.eINSTANCE.getCacheInfo_CacheStrategy())
        {
            int newCacheStr = ((Integer)newValue).intValue();
            int oldCacheStr = ((Integer)oldValue).intValue();
            int changes = newCacheStr ^ oldCacheStr;
            int mru_status = changes & CacheStrategyType.MRU;
            int fetch_status = changes & CacheStrategyType.PREFETCH;
            if(mru_status!=0)
            {
                if((newCacheStr&mru_status)!=0)
                {
                    script.addAlterOtherStatements(ASEDdlUtils.getCacheStrategyStatement(MRU, index, true, (DatabaseIdentifier)getParameter()));
                }
                else
                {
                    script.addAlterOtherStatements(ASEDdlUtils.getCacheStrategyStatement(MRU, index, false, (DatabaseIdentifier)getParameter()));
                }
            }
            if(fetch_status!=0)
            {
                if((newCacheStr&fetch_status)!=0)
                {
                    script.addAlterOtherStatements(ASEDdlUtils.getCacheStrategyStatement(PREFETCH, index, true, (DatabaseIdentifier)getParameter()));
                }
                else
                {
                    script.addAlterOtherStatements(ASEDdlUtils.getCacheStrategyStatement(PREFETCH, index, false, (DatabaseIdentifier)getParameter()));
                }
            }
        }
        if(!result.toString().equals(""))
        {
            script.addAlterOtherStatements(result.toString());        
        }
    }
    
    /**
     * Check if the given max row per page/reserver page gap/fill factor value equals to the server wide default value.
     * @return
     */
    private boolean isEqualToServerWideDefault(Integer value)
    {
        int intValue = value.intValue();
        if(intValue == 0 || intValue == -1)
        {
            return true;
        }
        return false;
    }

}
