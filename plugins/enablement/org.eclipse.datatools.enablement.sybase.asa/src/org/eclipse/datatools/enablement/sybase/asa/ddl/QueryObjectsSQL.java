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
package org.eclipse.datatools.enablement.sybase.asa.ddl;

public class QueryObjectsSQL
{
    public static String QUERY_PROCEDURE = "select 1 from sys.sysprocedure where proc_name = ''{1}'' and user_name(creator) = ''{0}''";
    
    public static String QUERY_TRIGGER =  "select 1 from sys.systriggers where owner = ''{0}'' and tname = ''{1}'' and trigname = ''{2}''";

    public static String QUERY_TABLE = "select 1 from sys.systable where table_name=''{1}'' and table_type in (''BASE'', ''GBL TEMP'') and creator=user_id(''{0}'')";

    public static String QUERY_VIEW = "select 1 from sys.systable where table_name=''{1}'' and table_type = ''VIEW'' and creator=user_id(''{0}'')";
    
    public static String QUERY_INDEX = "select 1 from sys.sysindexes where tname = ''{1}'' and creator = ''{0}'' and iname = ''{2}''";

    public static String QUERY_UDT = "select 1 from sys.sysusertype where type_name=''{0}''";
    
    public static String QUERY_AUTHID =  "select * from SYSUSERLIST where name = ''{0}'' and user_group = ''{1}''";
    
    public static String QUERY_EVENT =  "select * from sysevent where event_name = ''{0}''";
    
    public static String QUERY_DBSPACE =  "select * from sysfile where dbspace_name = ''{0}''";
    
    public static String QUERY_CONSTRAINT = "select 1 from SYS.SYSCONSTRAINT P, SYS.SYSTABLE T, sys.sysuserperms U WHERE P.table_id = T.table_id AND P.constraint_type = ''{3}'' AND T.table_name = ''{1}'' and T.creator = U.user_id and U.user_name = ''{0}'' and P.constraint_name = ''{2}''";
    
    public static String QUERY_CONSTRAINT_FOR_ASA10 = "select 1 as constraint_name from SYS.SYSCONSTRAINT P, SYS.SYSTABLE T, sys.sysuserperms U WHERE P.table_object_id = T.object_id AND P.constraint_type = ''{3}'' AND T.table_name = ''{1}'' and T.creator = U.user_id and U.user_name = ''{0}'' and P.constraint_name = ''{2}''"; 
}
