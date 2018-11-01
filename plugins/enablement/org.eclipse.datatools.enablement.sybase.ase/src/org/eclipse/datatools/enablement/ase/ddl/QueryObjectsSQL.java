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

public class QueryObjectsSQL
{
    public static String QUERY_CATALOG = "select 1 from master.dbo.sysdatabases where name = ''{0}''";
    public static String QUERY_PROCEDURE = "SELECT 1 FROM dbo.sysobjects O WHERE O.uid = USER_ID(''{0}'') AND O.name = ''{1}'' and O.type=''P''";
    public static String QUERY_UDT = "SELECT 1 FROM dbo.systypes T WHERE T.name = ''{0}''";
    public static String QUERY_DEFAULT = "SELECT 1 FROM dbo.sysobjects O WHERE O.uid = USER_ID(''{0}'') AND O.name = ''{1}'' and O.type=''D''";
    public static String QUERY_RULE = "SELECT 1 FROM dbo.sysobjects O WHERE O.uid = USER_ID(''{0}'') AND O.name = ''{1}'' and O.type=''R''";
    public static String QUERY_TRIGGER = "SELECT 1 FROM dbo.sysobjects O WHERE O.uid = USER_ID(''{0}'') AND O.name = ''{1}'' and O.type=''TR''";
    
    public static String QUERY_TABLE = "SELECT 1 FROM dbo.sysobjects O WHERE O.uid = USER_ID(''{0}'') AND O.name = ''{1}'' and O.type=''U''";
    public static String QUERY_VIEW = "SELECT 1 FROM dbo.sysobjects O WHERE O.uid = USER_ID(''{0}'') AND O.name = ''{1}'' and O.type=''V''";
    
    public static String QUERY_INDEX = "select 1 from dbo.sysindexes where name = ''{0}'' and id = object_id(''{1}'') " +
            "\n    and indid > 0 and indid < 255";
    
    public static String QUERY_AUTHID = "select 1 from dbo.sysusers where name = ''{0}''";
    public static String QUERY_SEGMENT = "select 1 from dbo.syssegments where name = ''{0}''";;
    
    public static String QUERY_PK_UNIQUE = "SELECT 1 FROM dbo.sysindexes i WHERE i.id = object_id(''{0}'') AND i.indid > 0 " +
            "\n    AND i.status2 & 2 = 2 and i.name = ''{1}''";
    
    public static String QUERY_FOREIGNKEY = "SELECT 1 FROM dbo.sysreferences WHERE tableid=object_id(''{0}'') and object_name(constrid,frgndbid) = ''{1}''";

    public static String QUERY_CHECK_CONSTRAINT = "SELECT 1 FROM dbo.sysconstraints a, dbo.sysobjects o WHERE a.tableid = object_id(''{0}'') " +
            "\n    AND a.status & 128 = 128 AND a.constrid not in (select domain from syscolumns where id = object_id(''{1}'')) " +
            "\n    AND o.id = a.constrid AND o.name = ''{2}''";

    public static String QUERY_COLUMN_CHECK_CONSTRAINT = 
        "SELECT 1 FROM syscomments R, sysprocedures P, sysobjects O ,syscolumns C " +
        "\n    WHERE R.id = C.domain AND P.id = R.id AND P.id = O.id AND P.sequence = 0 AND P.status & 4096 = 4096 " +
        "\n    AND C.id = object_id(''{0}'') and O.name = ''{1}''";
    public static String QUERY_OBJECT_DEF = "SELECT 1 FROM dbo.sysattributes a WHERE a.object_type = ''OD'' AND a.object_cinfo = ''{0}'' ";
}