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
package org.eclipse.datatools.enablement.ase;

import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlConstants;

public interface ISybaseASEDdlConstants extends ISybaseDdlConstants
{
    public final static String EXISTING                   = "EXISTING";                  //$NON-NLS-1$
    public final static String SP_WEBSERVICES             = "sp_webservices";            //$NON-NLS-1$
    public final static String COLUMN_DELIMITER           = "COLUMN DELIMITER";          //$NON-NLS-1$
    public final static String MODIFY                     = "MODIFY";                    //$NON-NLS-1$
    public final static String FILLFACTOR                 = "fillfactor";                //$NON-NLS-1$
    public final static String MATCHFULL                  = "match full";                //$NON-NLS-1$
    public static final String IDENTITY                   = "IDENTITY";                  //$NON-NLS-1$
    public static final String MATERIALIZED               = "MATERIALIZED";              //$NON-NLS-1$
    public static final String LOCK                       = "LOCK";                      //$NON-NLS-1$
    public static final String DATAROWS                   = "datarows";                  //$NON-NLS-1$
    public static final String DATAPAGES                  = "datapages";                 //$NON-NLS-1$
    public static final String ALLPAGES                   = "allpages";                  //$NON-NLS-1$
    public static final String MAX_ROWS_PER_PAGE          = "max_rows_per_page";         //$NON-NLS-1$
    public static final String EXP_ROW_SIZE               = "exp_row_size";              //$NON-NLS-1$
    public static final String RESERVEPAGEGAP             = "reservepagegap";            //$NON-NLS-1$
    public static final String IDENTITY_GAP               = "identity_gap";              //$NON-NLS-1$
    public static final String PARTITION                  = "PARTITION";                 //$NON-NLS-1$
    public static final String RANGE                      = "RANGE";                     //$NON-NLS-1$
    public static final String HASH                       = "HASH";                      //$NON-NLS-1$
    public static final String LIST                       = "LIST";                      //$NON-NLS-1$
    public static final String ROUNDROBIN                 = "ROUNDROBIN";                //$NON-NLS-1$
    public static final String BY                         = "BY";                        //$NON-NLS-1$
    public static final String VALUES                     = "VALUES";                    //$NON-NLS-1$
    public static final String LESSEQUAL                  = "<=";                        //$NON-NLS-1$
    public final static String RULE                       = "RULE";                      //$NON-NLS-1$
    public final static String ACCESS                     = "ACCESS";                    //$NON-NLS-1$
    public final static String ACCESSRULE                 = "accessrule";                //$NON-NLS-1$
    public final static String ALL                        = "all";                       //$NON-NLS-1$
    public final static String AND                        = "AND";                       //$NON-NLS-1$
    public final static String OR                         = "OR";                        //$NON-NLS-1$
    public final static String EXEC                       = "EXEC";                      //$NON-NLS-1$
    public final static String FUTUREONLY                 = "futureonly";                //$NON-NLS-1$
    public final static String SP_ADDTYPE                 = "sp_addtype";                //$NON-NLS-1$
    public final static String SP_DROPTYPE                = "sp_droptype";               //$NON-NLS-1$
    public final static String SP_BINDEFAULT              = "sp_bindefault";             //$NON-NLS-1$
    public final static String SP_BINDRULE                = "sp_bindrule";               //$NON-NLS-1$
    public final static String CONSUMERS                  = "consumers";                 //$NON-NLS-1$
    public final static String IGNORE_DUPLICATE_ROW       = "ignore_dup_row";            //$NON-NLS-1$
    public final static String ALLOW_DUPLICATE_ROW        = "allow_dup_row";             //$NON-NLS-1$
    public final static String IGNORE_DUPLICATE_KEY       = "ignore_dup_key";            //$NON-NLS-1$
    public final static String SORTED_DATA                = "sorted_data";               //$NON-NLS-1$
    public final static String STATISTICS_USING           = "statistics using";          //$NON-NLS-1$
    public final static String LOCAL_INDEX                = "local index";               //$NON-NLS-1$
    public final static String MRU                        = "mru";                       //$NON-NLS-1$
    public final static String PREFETCH                   = "prefetch";                  //$NON-NLS-1$
    public final static String SP_CACHESTRATEGY           = "sp_cachestrategy";          //$NON-NLS-1$
    public final static String SP_CHGATTRIBUTE            = "sp_chgattribute";           //$NON-NLS-1$
    public final static String SP_BINDCACHE               = "sp_bindcache";              //$NON-NLS-1$
    public final static String MODIFIES_SQL_DATA          = "MODIFIES SQL DATA";         //$NON-NLS-1$
//    public final static String RETURNS_NULL_ON_NULL_INPUT = "RETURNS NULL ON NULL INPUT"; //$NON-NLS-1$
//    public final static String CALLED_ON_NULL_INPUT       = "CALLED ON NULL INPUT";      //$NON-NLS-1$
    public final static String REPLACE                    = "REPLACE";                   //$NON-NLS-1$
    public final static String SETUSER                    = "SETUSER";                   //$NON-NLS-1$
    public final static String OVERRIDE                   = "OVERRIDE";                  //$NON-NLS-1$
    public final static String DEFAULT_LOCATION           = "default_location";          //$NON-NLS-1$
    public final static String LOAD                       = "LOAD";                      //$NON-NLS-1$
    public final static String PROXY_UPDATE               = "proxy_update";              //$NON-NLS-1$

    public final static String CONCURRENCY_OPT_THRESHOLD  = "concurrency_opt_threshold"; //$NON-NLS-1$
    public final static String TABLE_ONLY                 = "table only";                //$NON-NLS-1$
    public final static String TEXT_ONLY                  = "text only";                 //$NON-NLS-1$
    public final static String OFF                        = "off";                       //$NON-NLS-1$
    public final static String SP_RENAME                  = "sp_rename";                 //$NON-NLS-1$
    public final static String SP_PLACEOBJECT             = "sp_placeobject";            //$NON-NLS-1$
    public final static String SP_UNBINDEFAULT            = "sp_unbindefault";           //$NON-NLS-1$
    public final static String SP_UNBINDRULE              = "sp_unbindrule";             //$NON-NLS-1$

    public final static String SP_ADDUSER                 = "sp_adduser";                //$NON-NLS-1$
    public final static String SP_ADDGROUP                = "sp_addgroup";               //$NON-NLS-1$
    public final static String SP_DROPUSER                = "sp_dropuser";               //$NON-NLS-1$
    public final static String SP_DROPGROUP               = "sp_dropgroup";              //$NON-NLS-1$
    public final static String SP_CHANGEGROUP             = "sp_changegroup";            //$NON-NLS-1$
    public final static String SP_ADDSEGMENT              = "sp_addsegment";             //$NON-NLS-1$
    public final static String SP_EXTENDSEGMENT           = "sp_extendsegment";          //$NON-NLS-1$
    public final static String SP_DROPSEGMENT             = "sp_dropsegment";            //$NON-NLS-1$

    public final static String USE                        = "USE";                       //$NON-NLS-1$
    public final static String master                     = "master";                    //$NON-NLS-1$

    public final static String ENABLE                     = "ENABLE";                    //$NON-NLS-1$
    public final static String DISABLE                    = "DISABLE";                   //$NON-NLS-1$
    public final static String DATABASE_OWNER            = "dbo";                      //$NON-NLS-1$
    
    public final static String SP_ADDOBJECTDEF			  = "sp_addobjectdef";
    public final static String SP_DROPOBJECTDEF			  = "sp_dropobjectdef";
}
