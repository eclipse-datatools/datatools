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

import org.eclipse.datatools.enablement.ase.ISybaseASEDdlConstants;
import org.eclipse.datatools.enablement.ase.SybaseASESQLUtil;
import org.eclipse.datatools.enablement.ase.ddl.SybaseASEDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheStrategyType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

public class SybaseASETableDeltaDdlGenProvider extends AbstractDeltaDdlGenProvider implements IDeltaDdlGenProvider,
        ISybaseASEDdlConstants
{

    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        SybaseDdlBuilder builder = (SybaseDdlBuilder) SybaseASEDdlBuilder.getInstance();
        SybaseASETable table = (SybaseASETable) e;
        SybaseASECatalog catalog = (SybaseASECatalog) table.getSchema().getCatalog();
        if (feature == EcorePackage.eINSTANCE.getENamedElement_Name())
        {
            StringBuffer stmt = new StringBuffer();
            stmt.append(EXEC).append(SPACE).append(SP_RENAME).append(SPACE).append(
                    quoteIdentifiers ? SQLUtil.quote((String) oldValue, SINGLE_QUOTE) : (String) oldValue).append(COMMA)
                    .append(SQLDevToolsUtil.quoteWhenNecessary((String)newValue,(DatabaseIdentifier)getParameter()));
            Schema creator = table.getSchema();
            String setUserStr = SybaseASESQLUtil.getSetNewUserStatement(creator);
            if(!setUserStr.equals(EMPTY_STRING))
            {
                script.addAlterTableRenameStatements(setUserStr);
            }
            script.addAlterTableRenameStatements(stmt.toString());
            String setUserDbo = SybaseASESQLUtil.getSetUserDBOStatement(creator);
            if(!setUserDbo.equals(EMPTY_STRING))
            {
                script.addAlterTableRenameStatements(setUserDbo);
            }
        }
        //fill factor
        //exec sp_chgattribute 'tableName', 'fillfactor', 12
        if (feature == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEBaseTable_FillFactor())
        {
            StringBuffer stmt = new StringBuffer();
            stmt.append(EXEC).append(SPACE).append(SP_CHGATTRIBUTE).append(SPACE)
                    .append(builder.getName(table, quoteIdentifiers, false)).append(COMMA).append(
                            SQLUtil.quote(FILLFACTOR, "'")).append(COMMA).append(newValue);
            script.addAlterOtherStatements(stmt.toString());
        }
        //segment
        //exec sp_placeobject 'newSegment', 'tableName'
        if (feature == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEBaseTable_Segment())
        {
            String segmentName = ((SybaseASESegment) newValue).getName();
            StringBuffer stmt = new StringBuffer();
            stmt.append(EXEC).append(SPACE).append(SP_PLACEOBJECT).append(SPACE)
                    .append(SQLUtil.quote(segmentName, "'")).append(COMMA).append(
                            builder.getName(table, quoteIdentifiers, false));
            script.addAlterOtherStatements(stmt.toString());
        }
        //max row per page
        //exec sp_chgattribute 'tableName', 'max_rows_per_page', 12
        if (feature == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEBaseTable_MaxRowPerPage())
        {
            StringBuffer stmt = new StringBuffer();
            stmt.append(EXEC).append(SPACE).append(SP_CHGATTRIBUTE).append(SPACE).append(
                    builder.getName(table, quoteIdentifiers, false)).append(COMMA).append(
                    SQLUtil.quote(MAX_ROWS_PER_PAGE, "'")).append(COMMA).append(newValue);
            script.addAlterOtherStatements(stmt.toString());
        }
        //reserve page gap
        //exec sp_chgattribute 'tableName', 'reservepagegap', 12
        if (feature == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEBaseTable_ReservePageGap())
        {
            StringBuffer stmt = new StringBuffer();
            stmt.append(EXEC).append(SPACE).append(SP_CHGATTRIBUTE).append(SPACE).append(
                    builder.getName(table, quoteIdentifiers, false)).append(COMMA).append(
                    SQLUtil.quote(RESERVEPAGEGAP, "'")).append(COMMA).append(newValue);
            script.addAlterOtherStatements(stmt.toString());
        }
        //exp row size
        //exec sp_chgattribute 'tableName', 'exp_row_size', 12
        if (feature == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEBaseTable_ExpRowSize())
        {
            StringBuffer stmt = new StringBuffer();
            stmt.append(EXEC).append(SPACE).append(SP_CHGATTRIBUTE).append(SPACE).append(
                    builder.getName(table, quoteIdentifiers, false)).append(COMMA).append(
                    SQLUtil.quote(EXP_ROW_SIZE, "'")).append(COMMA).append(newValue);
            script.addAlterOtherStatements(stmt.toString());
        }
        //identity gap
        //exec sp_chgattribute 'tableName', 'identity_gap', 12
        if (feature == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEBaseTable_IdentityGap())
        {
            StringBuffer stmt = new StringBuffer();
            stmt.append(EXEC).append(SPACE).append(SP_CHGATTRIBUTE).append(SPACE).append(
                    builder.getName(table, quoteIdentifiers, false)).append(COMMA).append(
                    SQLUtil.quote(IDENTITY_GAP, "'")).append(COMMA).append(newValue);
            script.addAlterOtherStatements(stmt.toString());
        }
        // concurrency opt threshold
        // exec sp_chgattribute 'tableName', 'concurrency_opt_threshold', 12
        if (feature == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEBaseTable_ConcurrencyOptThreshold())
        {
            StringBuffer stmt = new StringBuffer();
            stmt.append(EXEC).append(SPACE).append(SP_CHGATTRIBUTE).append(SPACE).append(
                    builder.getName(table, quoteIdentifiers, false)).append(COMMA).append(
                    SQLUtil.quote(CONCURRENCY_OPT_THRESHOLD, "'")).append(COMMA).append(newValue);
            script.addAlterOtherStatements(stmt.toString());
        }

        //table only cache info
        /**
         * <pre>
         *  sp_cachestrategy dbname, [ownername.]tablename
         *          [, indexname | &quot;text only&quot; | &quot;table only&quot;
         *          [, { prefetch | mru }, { &quot;on&quot; | &quot;off&quot;}]]
         * 
         */
        if (feature == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEBaseTable_TableOnlyCacheInfo())
        {
            StringBuffer stmt = new StringBuffer();
            CacheInfo cache_new = (CacheInfo) newValue;
            CacheInfo cache_old = (CacheInfo) oldValue;
            int changes=0;
            int mru_status=0;
            int fetch_status=0;
            if(cache_old!=null)
            {
                changes = cache_new.getCacheStrategy() ^ cache_old.getCacheStrategy();
            }
            else
            {
                changes = cache_new.getCacheStrategy() ^ (CacheStrategyType.MRU|CacheStrategyType.PREFETCH);
            }
            mru_status = changes & CacheStrategyType.MRU;
            fetch_status = changes & CacheStrategyType.PREFETCH;
           
            if (mru_status > 0)
            {
                // mru on
                //exec sp_cachestrategy dbname, tablename, "table only", mru, "on"
                if ((mru_status & cache_new.getCacheStrategy()) > 0)
                {
                    stmt.append(EXEC).append(SPACE).append(SP_CACHESTRATEGY).append(SPACE).append(
                    		SQLUtil.quote(catalog.getName(), SINGLE_QUOTE)).append(COMMA).append(
                            SQLUtil.quote(table.getSchema().getName() + DOT + table.getName(), SINGLE_QUOTE)).append(COMMA).append(
                            SQLUtil.quote(TABLE_ONLY, SINGLE_QUOTE)).append(COMMA).append(CacheStrategyType.MRU_LITERAL).append(
                            COMMA).append(SQLUtil.quote(ON, SINGLE_QUOTE));
                }
                else
                // mru off
                //                  exec sp_cachestrategy dbname, tablename, "table only", mru, "off"
                {
                    stmt.append(EXEC).append(SPACE).append(SP_CACHESTRATEGY).append(SPACE).append(
                            SQLUtil.quote(catalog.getName(), SINGLE_QUOTE)).append(COMMA).append(
                            SQLUtil.quote(table.getSchema().getName() + DOT + table.getName(), SINGLE_QUOTE)).append(COMMA).append(
                            SQLUtil.quote(TABLE_ONLY, SINGLE_QUOTE)).append(COMMA).append(CacheStrategyType.MRU_LITERAL).append(
                            COMMA).append(SQLUtil.quote(OFF, SINGLE_QUOTE));
                }
                stmt.append(NEWLINE);

            }
            if (fetch_status > 0)
            {
                // prefetch on
                //              exec sp_cachestrategy dbname, tablename, "table only", prefetch, "on"
                if ((fetch_status & cache_new.getCacheStrategy()) > 0)
                {
                    stmt.append(EXEC).append(SPACE).append(SP_CACHESTRATEGY).append(SPACE).append(
                    		SQLUtil.quote(catalog.getName(), SINGLE_QUOTE)).append(COMMA).append(
                    				SQLUtil.quote(table.getSchema().getName() + DOT + table.getName(), SINGLE_QUOTE)).append(COMMA).append(
                            SQLUtil.quote(TABLE_ONLY, SINGLE_QUOTE)).append(COMMA).append(CacheStrategyType.PREFETCH_LITERAL)
                            .append(COMMA).append(SQLUtil.quote(ON, SINGLE_QUOTE));
                }
                else
                // prefetch off
                //                  exec sp_cachestrategy dbname, tablename, "table only", prefetch, "off"
                {
                    stmt.append(EXEC).append(SPACE).append(SP_CACHESTRATEGY).append(SPACE).append(
                    		SQLUtil.quote(catalog.getName(), SINGLE_QUOTE)).append(COMMA).append(
                            SQLUtil.quote(table.getSchema().getName() + DOT + table.getName(), SINGLE_QUOTE)).append(COMMA).append(
                            SQLUtil.quote(TABLE_ONLY, SINGLE_QUOTE)).append(COMMA).append(CacheStrategyType.PREFETCH_LITERAL)
                            .append(COMMA).append(SQLUtil.quote(OFF, SINGLE_QUOTE));
                }

            }
            script.addAlterOtherStatements(stmt.toString());
        }

        //      text only cache info
        if (feature == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEBaseTable_TextOnlyCacheInfo())
        {
            StringBuffer stmt = new StringBuffer();
            CacheInfo cache_new = (CacheInfo) newValue;
            CacheInfo cache_old = (CacheInfo) oldValue;

            int changes = cache_new.getCacheStrategy() ^ cache_old.getCacheStrategy();
            int mru_status = changes & CacheStrategyType.MRU;
            int fetch_status = changes & CacheStrategyType.PREFETCH;
            if (mru_status > 0)
            {
                // mru on
                //              exec sp_cachestrategy dbname, tablename, "text only", mru, "on"
                if ((mru_status & cache_new.getCacheStrategy()) > 0)
                {
                    stmt.append(EXEC).append(SPACE).append(SP_CACHESTRATEGY).append(SPACE).append(
                    		SQLUtil.quote(catalog.getName(), "\'")).append(COMMA).append(
                            SQLUtil.quote(table.getSchema().getName() + DOT + table.getName(), SINGLE_QUOTE)).append(COMMA).append(
                            SQLUtil.quote(TEXT_ONLY, SINGLE_QUOTE)).append(COMMA).append(CacheStrategyType.MRU_LITERAL).append(
                            COMMA).append(SQLUtil.quote(ON, SINGLE_QUOTE));
                }
                else
                // mru off
                //                  exec sp_cachestrategy dbname, tablename, "text only", mru, "off"
                {
                    stmt.append(EXEC).append(SPACE).append(SP_CACHESTRATEGY).append(SPACE).append(
                    		SQLUtil.quote(catalog.getName(), "\'")).append(COMMA).append(
                            SQLUtil.quote(table.getSchema().getName() + DOT + table.getName(), SINGLE_QUOTE)).append(COMMA).append(
                            SQLUtil.quote(TEXT_ONLY, SINGLE_QUOTE)).append(COMMA).append(CacheStrategyType.MRU_LITERAL).append(
                            COMMA).append(SQLUtil.quote(OFF, SINGLE_QUOTE));
                }
                stmt.append(NEWLINE);

            }
            if (fetch_status > 0)
            {
                // prefetch on
                //              exec sp_cachestrategy dbname, tablename, "text only", prefetch, "on"
                if ((fetch_status & cache_new.getCacheStrategy()) > 0)
                {
                    stmt.append(EXEC).append(SPACE).append(SP_CACHESTRATEGY).append(SPACE).append(
                    		SQLUtil.quote(catalog.getName(), "\'")).append(COMMA).append(
                            SQLUtil.quote(table.getSchema().getName() + DOT + table.getName(), SINGLE_QUOTE)).append(COMMA).append(
                            SQLUtil.quote(TEXT_ONLY, SINGLE_QUOTE)).append(COMMA).append(CacheStrategyType.PREFETCH_LITERAL)
                            .append(COMMA).append(SQLUtil.quote(ON, SINGLE_QUOTE));
                }
                else
                // prefetch off
                //                  exec sp_cachestrategy dbname, tablename, "text only", prefetch, "off"
                {
                    stmt.append(EXEC).append(SPACE).append(SP_CACHESTRATEGY).append(SPACE).append(
                    		SQLUtil.quote(catalog.getName(), "\'")).append(COMMA).append(
                            SQLUtil.quote(table.getSchema().getName() + DOT + table.getName(), SINGLE_QUOTE)).append(COMMA).append(
                            SQLUtil.quote(TEXT_ONLY, SINGLE_QUOTE)).append(COMMA).append(CacheStrategyType.PREFETCH_LITERAL)
                            .append(COMMA).append(SQLUtil.quote(OFF, SINGLE_QUOTE));
                }

            }
            script.addAlterOtherStatements(stmt.toString());
        }

        //locking schema
        //alter table tablename lock DATAPAGES
        if (feature == SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEBaseTable_LockSchema())
        {
            StringBuffer stmt = new StringBuffer();
            String lockSchem;
            LockingSchemaType schemType = (LockingSchemaType)newValue;
            switch (schemType.getValue())
            {
                case LockingSchemaType.LOCKALLPAGES:
                    lockSchem = ALLPAGES;
                    break;
                case LockingSchemaType.LOCKDATAPAGES:
                    lockSchem = DATAPAGES;
                    break;
                default:
                    lockSchem = DATAROWS; 
            }
            stmt.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                    builder.getName(table, quoteIdentifiers, qualifyNames)).append(SPACE).append(LOCK).append(SPACE)
                    .append(lockSchem);
            script.addAlterOtherStatements(stmt.toString());
        }

        // TODO: add other features support here
    }

    
    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {
        script.addCreateTableStatement(statement);

    }

    
    protected void addDropStatement(SybaseDdlScript script, String statement)
    {
        script.addDropTableStatement(statement);

    }
}
