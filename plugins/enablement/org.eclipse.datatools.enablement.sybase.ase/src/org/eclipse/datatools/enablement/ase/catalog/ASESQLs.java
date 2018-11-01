/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    linsong - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ase.catalog;

public interface ASESQLs
{
    // query schemas(user)
    String QUERY_SCHEMA = "select name as TABLE_SCHEM from sysusers where uid != gid";
    
    // query all tables(user table/system table/proxy table/view)
    String ALL_TABLES_QUERY                    = "SELECT o.id, o.name as TABLE_NAME, o.type as TABLE_TYPE, o.sysstat2, o.sysstat FROM dbo.sysobjects AS o, dbo.sysusers AS u WHERE o.uid = u.uid AND u.name = ? AND (o.type = 'U' OR o.type = 'S' OR o.type = 'V') ORDER BY TABLE_NAME";

    // query table info for user table / proxy table
    String QUERY_TABLE_TYPE_INFO 			   = "SELECT o.id, o.type, o.sysstat2 FROM dbo.sysobjects AS o, dbo.sysusers AS u WHERE o.uid = u.uid AND u.name = ? AND o.name = ? AND (o.type = 'U' OR o.type = 'S')";
    String TABLE_INFO_QUERY                    = "SELECT o.sysstat2,s.name,isnull(i.maxrowsperpage,0),isnull(i.exp_rowsize,0),isnull(i.res_page_gap,0),isnull(i.identitygap,0),isnull(i.fill_factor,0),(SELECT count(*) FROM dbo.syspartitions p WHERE p.id=o.id),o.id FROM dbo.sysobjects o,dbo.sysusers u, dbo.sysindexes i, dbo.syssegments s WHERE u.uid=o.uid AND i.id = o.id AND s.segment = i.segment AND i.indid<2 AND (o.type='U' OR  o.type='S') AND u.name = ? AND o.name = ?";
    String PLACE_OBJECT_OF_TABLE_QUERY         = "SELECT I.name,S.name FROM dbo.sysindexes I, dbo.syssegments S WHERE I.id = ? AND I.segment = S.segment AND I.indid=255";

    // query compiled objects (SP/rule/default)
    
//    String COMPILED_OBJECTS_QUERY              = "SELECT O.name, P.number FROM sysusers U, sysobjects O, sysprocedures P WHERE U.uid=O.uid and P.id = O.id and P.sequence =0 AND P.status & 4096 != 4096 AND U.name like ? and O.name like ? and O.type=?";
    String COMPILED_OBJECTS_QUERY              = "select distinct O.name, U.name from dbo.sysobjects O, dbo.sysusers U, dbo.sysprocedures P where O.uid = U.uid and O.id = P.id and P.sequence = 0 and P.status & 4096 != 4096 and U.name = ? and O.type = ? order by 1";
    
    String STORED_PROCEDURE_QUERY              = "SELECT O.name as PROCEDURE_NAME, O.sysstat2 as PROCEDURE_TYPE FROM sysusers U, sysobjects O WHERE U.uid=O.uid AND U.name = ? and O.type='P' order by 1";
    String PARAMETERS_QUERY                    = "select c.name as COLUMN_NAME,c.status2 COLUMN_TYPE, c.usertype DATA_TYPE,t.name TYPE_NAME,c.prec PRECISION,c.length,c.scale,t.allownulls NULLABLE,NULL as REMARKS,char_length = isnull(@@ncharsize,1) from syscolumns c join systypes t on c.usertype = t.usertype where id = object_id(?) order by colid";
    // query user defined datatype
    String USER_DEFINED_DATATYPE_QUERY         = "SELECT T.name udt_name FROM  systypes T, sysusers U, systypes PHY WHERE T.uid = U.uid AND PHY.usertype =(SELECT min(usertype) FROM systypes WHERE type = T.type )AND T.type = PHY.type AND T.usertype >= 100 AND U.name = ?  order by 1";
    String USER_DEFINED_DATATYPE_INFO_QUERY    = "SELECT U.name owner_name,T.name udt_name, PHY.name udt_sys_name, T.length udt_length, T.prec udt_precision, T.scale udt_scale, T.allownulls udt_allow_nulls, T.ident udt_is_identity, (select name FROM sysusers where uid=(SELECT uid from sysobjects where id = T.tdefault)) default_owner, object_name(T.tdefault,db_id(?)) udt_default_name, (SELECT RO.name FROM sysusers RO WHERE RO.uid = (SELECT uid from sysobjects where id  = T.domain OR id = T.accessrule)) rule_owner, object_name((case when T.domain<>0 then T.domain else T.accessrule end),db_id(?)) udt_rule_name FROM  systypes T, sysusers U, systypes PHY where T.name = ? and T.uid = U.uid AND PHY.usertype =(SELECT min(usertype) FROM systypes WHERE type = T.type )  and T.type = PHY.type";
    String ALL_USER_DEFINED_DATATYPE_INFO_QUERY= "SELECT U.name owner_name,T.name udt_name, PHY.name udt_sys_name, T.length udt_length, T.prec udt_precision, T.scale udt_scale, T.allownulls udt_allow_nulls, T.ident udt_is_identity, (select name FROM sysusers where uid=(SELECT uid from sysobjects where id = T.tdefault)) default_owner, object_name(T.tdefault,db_id(?)) udt_default_name, (SELECT RO.name FROM sysusers RO WHERE RO.uid = (SELECT uid from sysobjects where id  = T.domain OR id = T.accessrule)) rule_owner, object_name((case when T.domain<>0 then T.domain else T.accessrule end),db_id(?)) udt_rule_name FROM  systypes T, sysusers U, systypes PHY where T.uid = U.uid AND PHY.usertype =(SELECT min(usertype) FROM systypes WHERE type = T.type )  and T.type = PHY.type and T.name != PHY.name";
    
    //query system defined datatype
    String SYSTEM_DEFINED_DATATPYE_QUERY 	   = "select name from systypes where usertype < 100 and usertype > 0 and name not in ('floatn', 'datetimn', 'numericn', 'decimaln', 'moneyn', 'intn', 'daten', 'timen', 'uintn') order by name"; 
    
//    String USER_DEFINED_DATATYPE_RULE_DEFAULT  = "SELECT T.allownulls udt_allow_nulls, T.ident udt_is_identity,(select name FROM sysusers where uid=(SELECT uid from sysobjects where id = T.tdefault)) default_owner, object_name(T.tdefault,db_id(?)) udt_default_name, (SELECT RO.name FROM sysusers RO WHERE RO.uid = (SELECT uid from sysobjects where id  = T.domain OR id = T.accessrule)) rule_owner, object_name((case when T.domain<>0 then T.domain else T.accessrule end),db_id(?)) udt_rule_name FROM  systypes T WHERE T.name like ?";
//    String USER_DEFINED_DATATYPE_QUERY 		   = "select TYPE_CAT = db_name(), TYPE_SCHEM = user_name(uid), TYPE_NAME = name, CLASS_NAME = null, DATA_TYPE = 2001, REMARKS = '''', BASE_TYPE = case type when 34 then -4 when 35 then -1 when 36 then 2000 when 37 then -3 when 39 then 12 when 45 then -2 when 47 then 1 when 48 then -6 when 49 then 91 when 50 then -7 when 51 then 92 when 52 then 5 when 55 then 3 when 56 then 4 when 58 then 93 when 59 then 7 when 61 then 93 when 62 then 6 when 63 then 2 when 135 then 1 when 155 then 12 else 1111 end, length, prec, scale from systypes where usertype > 100 and uid=user_id(''{0}'')";
    // query index info
    String INDEX_NAME_QUERY                    = "SELECT I.name, I.status2 FROM sysindexes I WHERE I.id = ? AND I.indid BETWEEN 1 AND 254 AND ((I.status & 16 = 16 OR I.status2 & 512 = 512) OR (I.status & 16 = 0 AND I.status2 & 512 = 0))  order by 1";
    String QUERY_INDICES_OF_SCHEMA_TABLE       = "SELECT I.name, T.name, I.status2 FROM sysindexes I, sysobjects T, sysusers U WHERE U.name = ? and U.uid = T.uid and I.id = T.id AND I.indid BETWEEN 1 AND 254 AND ((I.status & 16 = 16 OR I.status2 & 512 = 512) OR (I.status & 16 = 0 AND I.status2 & 512 = 0))  order by 1";
    String INDEX_INFO_QUERY					   = "SELECT I.indid, I.status, I.keycnt, I.maxrowsperpage, I.fill_factor, I.res_page_gap,S.name,I.status2 FROM sysindexes I, syssegments S WHERE I.id = ? AND I.name = ? AND I.segment = S.segment AND I.indid BETWEEN 1 AND 254 AND ((I.status & 16 = 16 OR I.status2 & 512 = 512) OR (I.status & 16 = 0 AND I.status2 & 512 = 0))";

    // query index column
    String INDEX_COL_QUERY                     = "SELECT COL_NAME = INDEX_COL(?, ?, ?), INDEX_COLORDER(?, ?, ?)";

    // query index column status and id
    String INDEX_COL_STATUS3_QUERY             = "SELECT isnull(status3,0),computedcol FROM syscolumns  WHERE name like ? AND id = object_id(?)";

    // query functional index column expression
    String FUNC_INDEX_COL_QUERY                = "SELECT text FROM syscomments WHERE id = ?";

    // query cache name
    String CACHE_QUERY                         = "select char_value FROM sysattributes b, sysobjects c WHERE c.id = b.object AND b.object_info1 = ? AND c.id in (Select object_id(?)) AND object_type = 'I'  order by 1";

    // query index partition
    String PARTITION_QUERY                     = "select partition_name = case when p.indid = 1 then p.cdataptnname else p.name end, segment = s.name,status3 = isnull(i.status3,0), status = isnull(p.status,0) from syspartitions p, sysindexes i, syssegments s where i.id = p.id and i.id = object_id(?) and p.indid >0 and i.indid< 255 and i.indid = p.indid and s.segment = p.segment  and i.conditionid is not null and p.name like '%' and i.name like ? order by 1";
    // query table partition
    String TABLE_PARTITION_INFO                = "select number = (select count(*) from syspartitions p  where p.id = o.id and p.indid < 2 ), partition_type = isnull((select v.name from master.dbo.spt_values v where v.type = 'PN' and v.number = i.partitiontype), 'roundrobin') from .sysobjects o, sysindexes i where  o.id = ? and i.id = o.id and i.indid <2";
    String TABLE_PARTITION_COLS                = "select column_name = c.name, column_type = isnull(x.xtname, isnull(get_xtypename(c.xtype,c.xdbid), t.name)) from syscolumns c, sysxtypes x, systypes t where id = ? and c.xtype *= x.xtid and c.usertype *= t.usertype and colid in (select colid from syspartitionkeys where id = ? )";

    String SELECT_ROUND_PARTITIONS             = "select partition_name = case when p.indid = 1 then p.cdataptnname else p.name end, segment = s.name, partition_id = isnull(p.partitionid,0), condition_id = i.conditionid, status = isnull(p.status,0) from syspartitions p, sysindexes i, syssegments s where i.id = p.id and i.id = ? and p.indid < 2 and i.indid < 2 and s.segment = p.segment  and i.conditionid is not null and p.name like '%' order by p.partitionid";
    String SELECT_HASH_PARTITIONS              = "select partition_name = case when p.indid = 1 then p.cdataptnname else p.name end, segment = s.name, partition_id = isnull(p.partitionid,0), condition_id = i.conditionid, status = isnull(p.status,0) from syspartitions p, sysindexes i, syssegments s where i.id = p.id and i.id = ? and p.indid < 2 and i.indid = 0 and s.segment = p.segment  and i.conditionid is not null and p.name like '%' order by p.partitionid";

    String SELECT_RANGE_LIST_PARTITIONS        = "select partition_name = case when p.indid = 1 then p.cdataptnname else p.name end, segment = s.name, condition = c.text, status = isnull(p.status,0) from syspartitions p, sysindexes i, syssegments s, syscomments c where c.partitionid = p.partitionid and c.id = i.conditionid and i.id = p.id and i.id = ? and p.indid < 2 and i.indid = 0 and s.segment = p.segment  and i.conditionid is not null and p.name like '%' order by p.partitionid";
    // query segment
    String SEGMENT_QUERY                       = "SELECT s.name, dev.name FROM master.dbo.sysusages u, syssegments s, master.dbo.sysdatabases d, master.dbo.sysdevices dev WHERE u.dbid = d.dbid AND d.name = ?";

    // query segment threshold
    String SEGMENT_THRESHOLD_QUERY             = "SELECT s.name, t.free_space, t.proc_name, convert(bit,t.status&1) FROM syssegments s, systhresholds t WHERE s.segment = t.segment AND s.name LIKE ?";

    // query triggers
    String TRIGGER_QUERY                         = "SELECT o.name, U.name, o.sysstat2 AS TRIGGER_CREATOR FROM sysobjects o, sysusers U WHERE o.type='TR' and o.deltrig = ? AND o.uid = U.uid order by 1";
    // query triggers of all tables of specified schema
    String QUERY_TRIGGERS_OF_SCHEMA_TABLE        = "SELECT TB.name as TABLE_NAME, TR.name AS TRIGGER_NAME, TU.name as TABLE_OWNER FROM sysobjects TR, sysobjects TB, sysusers TU, sysusers TRS WHERE TR.type='TR' and TR.deltrig = TB.id and TU.uid = TB.uid and TRS.name = ? and TRS.uid = TR.uid order by 2";

    String QUERY_TRIGGER_INFO                  = "SELECT o.sysstat2, deltrig = CASE when o1.deltrig = o.id then 1 else 0 end, updtrig = CASE when o1.updtrig = o.id then 1 else 0 end, instrig = CASE when o1.instrig = o.id then 1 else 0 end , o.id FROM sysobjects o, sysobjects o1 WHERE o.name = ? and o.type='TR' and o.deltrig = o1.id and o1.id = ?";
    String QUERY_TRIGGER_ENABLE                = "select 1 from dbo.sysobjects trig, dbo.sysobjects tab where trig.id = ? and trig.deltrig = tab.id and ((trig.id = tab.deltrig and tab.sysstat2 & 2097152 <> 0) or (trig.id = tab.updtrig and tab.sysstat2 & 4194304 <> 0) or (trig.id = tab.instrig and tab.sysstat2 & 1048576 <> 0))";
    
    // query table concurrency threshold
    String CONCURRENCY_THRESHOLD_QUERY         = "select conopt_thld from systabstats where id = ? and indid = 0";

//    String TABLE_PARTITION_INFO_QUERY          = "";

    String TABLE_CACHE_INFO_QUERY              = "SELECT char_value,object_type FROM sysattributes WHERE class=3 AND attribute=0 AND object= ? AND object_info1 IN (null,255)";

    String TABLE_CACHE_STRATEGY_QUERY          = "select i.name, i.indid, i.status2 from sysindexes i where i.id = ?";

    String UNIQUE_PK_QUERY                     = "SELECT COLUMNS_COUNT = i.keycnt, i.name , INDEX_ID = i.indid, STATUS = i.status, STATUS2 = i.status2, FILL_FACTOR = isnull(i.fill_factor,0), MAXROWSPERPAGE = isnull(i.maxrowsperpage,0), RES_PAGE_GAP = isnull(i.res_page_gap,0), SEGMENT_NAME = s.name FROM sysindexes i, syssegments s WHERE i.segment = s.segment AND i.id = ? AND i.indid > 0 AND i.status2 & 2 = 2";

    // query foreign key
    String QUERY_FOREIGN_KEY_NAME              = "SELECT CONSTRAINT_NAME =object_name(constrid,frgndbid) FROM sysreferences WHERE tableid=?  order by 1";
    String QUERY_FOREIGNKEY_MEMBERS  		   = "SELECT col1 = col_name(tableid,fokey1,frgndbid), col2 = col_name(tableid,fokey2,frgndbid), col3 = col_name(tableid,fokey3,frgndbid), col4 =col_name(tableid,fokey4,frgndbid), col5 =col_name(tableid,fokey5,frgndbid), col6 =col_name(tableid,fokey6,frgndbid), col7 =col_name(tableid,fokey7,frgndbid), col8 =col_name(tableid,fokey8,frgndbid), col9 =col_name(tableid,fokey9,frgndbid), col10 = col_name(tableid,fokey10,frgndbid), col11 = col_name(tableid,fokey11,frgndbid), col12 = col_name(tableid,fokey12,frgndbid), col13 = col_name(tableid,fokey13,frgndbid), col14 = col_name(tableid,fokey14,frgndbid) , col15 = col_name(tableid,fokey15,frgndbid), col16 = col_name(tableid,fokey16,frgndbid) FROM sysreferences R, sysobjects O, sysusers U WHERE R.constrid = O.id and O.name = ? and O.type = 'RI' and O.uid = U.uid and U.name = ?";
    String QUERY_FOREIGNKEY_UNIQUEINDEX 	   = "SELECT indexid, pmrydbname,reftabid FROM sysreferences R, sysobjects O, sysusers U WHERE R.constrid = O.id and O.name = ? and O.type = 'RI' and O.uid = U.uid and U.name = ?";
    
    String QUERY_REF_INDEX                     = "SELECT o.name, u.name, i.name FROM sysobjects o, sysindexes i, sysusers u WHERE o.id = ? and i.id = o.id and indid = ? and u.uid = o.uid";

    String QUERY_CHECK_CONSTRAINTS             = "SELECT o.name,u.name FROM sysconstraints a, sysobjects o, sysusers u WHERE a.tableid = ? AND u.uid=o.uid AND  a.status & 128 = 128 AND a.constrid not in (select domain from syscolumns where id = ?)AND o.id = a.constrid ORDER BY a.constrid";
    String QUERY_CHECK_CONSTRAINTS_SOURCE      = "SELECT b.text FROM sysconstraints a, syscomments b, sysprocedures p, sysobjects o, sysusers U WHERE a.constrid = b.id AND p.id = b.id AND p.sequence = 0 AND p.status & 4096 = 4096 AND a.status & 128 = 128 AND o.id = a.constrid AND o.name like ? AND o.uid = U.uid AND U.name = ?";
    
    String QUERY_CHECK_CONSTRAINTS_CREATOR	   = "SELECT u.name FROM sysconstraints a, sysobjects o, sysusers u WHERE o.name=? AND u.uid=o.uid AND a.tableid = ? AND  a.status & 128 = 128 AND a.constrid not in (select domain from syscolumns where id = ?) AND o.id = a.constrid";
    String QUERY_COLUMNCHECK_CONSTRAINTS_CREATOR= "SELECT u.name FROM sysconstraints a, sysobjects o, sysusers u WHERE o.name=? AND u.uid=o.uid AND a.tableid = ? AND  a.status & 128 = 128 AND a.constrid in (select domain from syscolumns where id = ?) AND o.id = a.constrid";
    // query table columns
    String QUERY_TABLE_COLUMNS 				   = "select c.name as COLUMN_NAME from syscolumns c where id = ? order by c.colid";
    String QUERY_COMPUTED_ENCRYPTED_COLUMNS    = "SELECT name=C.name , type = isnull(X.xtname, isnull(get_xtypename(C.xtype,C.xdbid), T.name)), col_length = C.length, prec = C.prec, scale = C.scale, status = C.status, type_id = T.usertype, column_id = C.id, inline_rule=C.domain, inline_default=C.cdefault, object_storage = case when (C.xstatus is null) then '' when (C.xstatus & 1) = 1 then 'off row' else 'in row' end, offline_default = (SELECT U.name + '.' + O.name FROM sysusers U, sysobjects O, sysprocedures P WHERE C.cdefault<>0 AND U.uid=O.uid AND O.id=C.cdefault AND P.sequence = 0 AND P.status & 4096 != 4096 AND P.id = O.id), offline_rule = (SELECT U.name + '.' + O.name FROM sysusers U, sysobjects O,sysprocedures P WHERE C.domain<>0 AND U.uid=O.uid AND O.id=C.domain AND P.sequence = 0 AND P.status & 4096 != 4096 AND P.id = O.id), char_length = isnull(@@ncharsize,1), status2 = isnull(C.status2,0), encrkey_id = isnull(C.encrkeyid,0), encrdb = C.encrkeydb FROM syscolumns C, systypes T, sysxtypes X WHERE C.id = ? AND C.usertype *= T.usertype AND C.xtype *= X.xtid and (C.status3 is null or C.status3 & 1 != 1)ORDER BY colid";
    String QUERY_COMPUTED_COLUMNS              = "SELECT name=C.name , type = isnull(X.xtname, isnull(get_xtypename(C.xtype,C.xdbid), T.name)), col_length = C.length, prec = C.prec, scale = C.scale, status = C.status, type_id = T.usertype, column_id = C.id, inline_rule=C.domain, inline_default=C.cdefault, object_storage = case when (C.xstatus is null) then '' when (C.xstatus & 1) = 1 then 'off row' else 'in row' end, offline_default = (SELECT U.name + '.' + O.name FROM sysusers U, sysobjects O, sysprocedures P WHERE C.cdefault<>0 AND U.uid=O.uid AND O.id=C.cdefault AND P.sequence = 0 AND P.status & 4096 != 4096 AND P.id = O.id), offline_rule = (SELECT U.name + '.' + O.name FROM sysusers U, sysobjects O,sysprocedures P WHERE C.domain<>0 AND U.uid=O.uid AND O.id=C.domain AND P.sequence = 0 AND P.status & 4096 != 4096 AND P.id = O.id), char_length = isnull(@@ncharsize,1), status2 = isnull(C.status2,0) FROM syscolumns C, systypes T, sysxtypes X WHERE C.id = ? AND C.usertype *= T.usertype AND C.xtype *= X.xtid and (C.status3 is null or C.status3 & 1 != 1)ORDER BY colid";
    String QUERY_ENCRYPTED_COLUMNS             = "SELECT name=C.name , type = isnull(X.xtname, isnull(get_xtypename(C.xtype,C.xdbid), T.name)), col_length = C.length, prec = C.prec, scale = C.scale, status = C.status, type_id = T.usertype, column_id = C.id, inline_rule=C.domain, inline_default=C.cdefault, object_storage = case when (C.xstatus is null) then '' when (C.xstatus & 1) = 1 then 'off row' else 'in row' end, offline_default = (SELECT U.name + '.' + O.name FROM sysusers U, sysobjects O, sysprocedures P WHERE C.cdefault<>0 AND U.uid=O.uid AND O.id=C.cdefault AND P.sequence = 0 AND P.status & 4096 != 4096 AND P.id = O.id), offline_rule = (SELECT U.name + '.' + O.name FROM sysusers U, sysobjects O,sysprocedures P WHERE C.domain<>0 AND U.uid=O.uid AND O.id=C.domain AND P.sequence = 0 AND P.status & 4096 != 4096 AND P.id = O.id), char_length = isnull(@@ncharsize,1), encrkey_id = isnull(C.encrkeyid,0), encrdb = C.encrkeydb FROM syscolumns C, systypes T, sysxtypes X WHERE C.id = ? AND C.usertype *= T.usertype AND C.xtype *= X.xtid ORDER BY colid";
    String COLUMNS_OF_TABLE_QUERY_12x          = "SELECT name=C.name , type = isnull(X.xtname, isnull(get_xtypename(C.xtype,C.xdbid), T.name)), col_length = C.length, prec = C.prec, scale = C.scale, status = C.status, type_id = T.usertype, column_id = C.id, inline_rule=C.domain, inline_default=C.cdefault, object_storage = case when (C.xstatus is null) then '' when (C.xstatus & 1) = 1 then 'off row' else 'in row' end, offline_default = (SELECT U.name + '.' + O.name FROM sysusers U, sysobjects O, sysprocedures P WHERE C.cdefault<>0 AND U.uid=O.uid AND O.id=C.cdefault AND P.sequence = 0 AND P.status & 4096 != 4096 AND P.id = O.id), offline_rule = (SELECT U.name + '.' + O.name FROM sysusers U, sysobjects O,sysprocedures P WHERE C.domain<>0 AND U.uid=O.uid AND O.id=C.domain AND P.sequence = 0 AND P.status & 4096 != 4096 AND P.id = O.id), char_length = isnull(@@ncharsize,1) FROM syscolumns C, systypes T, sysxtypes X WHERE C.id = ? AND C.usertype *= T.usertype AND C.xtype *= X.xtid ORDER BY colid";

    String QUERY_INLINE_DEFAULT                = "SELECT text FROM syscomments D, sysprocedures P, syscolumns C WHERE C.id =? and C.name = ? and D.id = C.cdefault AND P.id = D.id AND P.sequence = 0 AND P.status & 4096 = 4096";
    String QUERY_COLUMN_BINDED_DEFAULT 		   = "SELECT U.name, O.name FROM sysusers U, sysobjects O, sysprocedures P, syscolumns C WHERE C.cdefault<>0 AND U.uid=O.uid AND O.id=C.cdefault AND P.sequence = 0 AND P.status & 4096 != 4096 AND P.id = O.id AND C.id = ? and C.name = ?";
    
    String QUERY_COLUMN_DATATYPE			   = "SELECT type = isnull(X.xtname, isnull(get_xtypename(C.xtype,C.xdbid), T.name)),col_length = C.length, prec = C.prec, scale = C.scale, type_id = T.usertype,char_length = isnull(@@ncharsize,1) FROM syscolumns C, systypes T, sysxtypes X, sysobjects O, sysusers U WHERE U.name = ? AND U.uid = O.uid AND O.name = ? AND C.id = O.id AND C.name like ? AND C.usertype *= T.usertype AND C.xtype *= X.xtid ORDER BY colid";
    
    String QUERY_COLUMN_ENCRYPTIONKEY 		   = "SELECT encrkey_id = isnull(C.encrkeyid,0), encrdb = C.encrkeydb FROM syscolumns C WHERE C.id = ? AND C.name like ?";
    String ENCRYPT_COL_ATTR                    = "select keyname = o.name, owner = u.name from  sysobjects o,  sysusers u  where o.id  = ?  and u.uid = o.uid";

    String COMPUTED_COL_EXPRESSION             = "SELECT text from syscomments where id = (select computedcol from syscolumns where id = ? and name like ? )";

    String INLINE_CHECKCONSTRAINT_OF_COLUMN    = "SELECT O.name, text FROM syscomments R, sysprocedures P, syscolumns C, sysobjects O WHERE R.id = C.domain AND P.id = R.id AND P.id = O.id AND P.sequence = 0 AND P.status & 4096 = 4096 AND C.id = ? AND C.name = ?";
	String QUERY_COLUMN_BINDED_RULE 		   = "(SELECT U.name, O.name FROM sysusers U, sysobjects O,sysprocedures P, syscolumns C WHERE C.domain<>0 and O.id=C.domain AND U.uid=O.uid AND P.sequence = 0 AND P.status & 4096 != 4096 AND P.id = O.id AND C.id = ? AND C.name like ?) UNION (SELECT U.name, O.name FROM sysusers U, sysobjects O,sysprocedures P, syscolumns C WHERE C.domain=0 AND O.id=C.accessrule AND U.uid=O.uid AND P.sequence = 0 AND P.status & 4096 != 4096 AND P.id = O.id AND C.id = ? AND C.name like ?)";
	
    String LOCK_PROMOTE                        = "select  lwm = object_info1, hwm = object_info2, pct = object_info3, attribute from sysattributes where class = 5 and  object_type = 'T' and  object = ?";

    String SP_CONFIGURE                        = "sp_configure ''{0}''";

    String REMOTE_OBJECT_OF_PROXY_TABLE_QUERY  = "SELECT CASE WHEN v.name='rpc' THEN 'procedure' ELSE v.name END,a.char_value, a.comments FROM sysattributes a,master.dbo.spt_values v WHERE object_cinfo=? and object_type='OD' and a.class = 9 and a.attribute = 1    and a.object_info2 = v.number and v.type = 'Y' and v.name != 'view'";

    // query database info
    String ATTRIBUTES_OF_DATABASE_QUERY_PRE150 = "SELECT d.name, convert(int, round((v.low * convert(float, u.size))/1048576, 0)), sign(u.segmap & (~4)) FROM master..sysusages u, master..spt_values v, master..sysdevices d WHERE d.low <= u.vstart and d.high >= u.size + u.vstart -1 AND u.dbid = db_id(?) AND d.status & 2 = 2 AND v.type = 'E' AND v.number = 1";
    String ATTRIBUTES_OF_DATABASE_QUERY        = "SELECT d.name, convert(int, round((v.low * convert(float, u.size))/1048576, 0)), sign(u.segmap & (~4)) FROM master..sysusages u, master..spt_values v, master..sysdevices d WHERE u.dbid = db_id( ? ) AND d.status & 2 = 2 AND v.type = 'E' AND v.number = 1 and u.vdevno=d.vdevno";

//    String NONE_TEMP_DATABASE_QUERY            = "SELECT name, suser_name(suid) as owner, status, status2, def_remote_loc FROM master.dbo.sysdatabases WHERE name like ? AND (status & 32) != 32 AND (status & 256) != 256 AND name != 'tempdb' AND status3 & 256 != 256 ORDER BY 1";
//    String TEMP_DBS_OPEN_QUERY                 = "SELECT name, suser_name(suid) as owner, status, status2, def_remote_loc FROM master.dbo.sysdatabases WHERE name like ? AND (status & 32) != 32 AND status3 & 256 = 256 UNION SELECT name, suser_name(suid) as owner, status, status2, def_remote_loc FROM master.dbo.sysdatabases WHERE name like ? AND (status & 32) != 32 AND name = 'tempdb'";
    String DATABASE_QUERY                        = "SELECT name as TABLE_CAT, def_remote_loc, status3 FROM master.dbo.sysdatabases WHERE (status & 32) != 32 ORDER BY 1";
    String DATABASE_QUERY_PARM_4_DBNAME          = "SELECT name as TABLE_CAT, def_remote_loc, status3 FROM master.dbo.sysdatabases WHERE (status & 32) != 32 AND name = ''{0}'' ORDER BY 1";
    
    String CACHE_BINDINGS_OF_DATABASE_QUERY    = "SELECT char_value,object_type FROM master.dbo.sysattributes WHERE class=3 AND attribute=0 AND object= db_id(?)";
    String DB_RECOVERY_ORDER                   = "select int_value from master.dbo.sysattributes where class=10 and attribute=6 and object_type='D' and object=db_id(?)";
    String LOG_IO_SIZE_OF_DB                   = "select int_value from sysattributes where object=object_id('syslogs') and object_type='T' and char_value=NULL";

    // group / user/ role
    String SQL_GET_GROUPS_IN_A_DATABASE        = "SELECT name FROM sysusers WHERE gid= uid AND name LIKE ? AND gid NOT IN (SELECT lrid FROM sysroles) ORDER BY 1";
    String SQL_GET_USERS_IN_A_DATABASE         = "SELECT a.name, b.name, g.name FROM master.dbo.syslogins a, sysusers b, sysusers g WHERE b.name LIKE ? AND b.suid *= a.suid AND b.gid *= g.uid AND b.uid >= 1 AND b.suid >= -1  ORDER BY 1";

    //privilege
//    String PERMISSION_QUERY                    = "{ call sp_helprotect ? }";

    String QROUP_USERS_QUERY                   = "select u1.name from sysusers u1, sysusers u2 where u2.name = ? and u1.gid = u2.gid and u1.name <> u2.name";
    String ROLES_QUERY                         = "SELECT name FROM master.dbo.syssrvroles where name like ? order by 1";
	String ROLES_QUERY_NONDBO                  = "select S.name from {0}.dbo.sysroles R, {0}.dbo.sysusers S where R.lrid=S.uid";

    //value 6 represents sds class server.
    String SDSSERVER_QUERY                     = "SELECT srvname FROM master.dbo.sysservers WHERE srvclass = 6";
    
    // query database caches
    String QUERY_DATABASE_CACHES               = "SELECT a.name, a.status, a.value FROM master.dbo.sysconfigures a WHERE parent = 19 AND config = parent and a.name like ? ORDER BY 1";

	String QUERY_SYSTEM_PROCEDURE 			   = "SELECT O.name, P.number FROM sybsystemprocs..sysusers U, sybsystemprocs..sysobjects O, sybsystemprocs..sysprocedures P WHERE U.uid=O.uid and P.id = O.id and P.sequence =0 AND P.status & 4096 != 4096 AND U.name like 'dbo' and O.name like '%' and O.type='P'";
	
	//query authid privileges
	String QUERY_AUTHID_PRIVILEGES 			   = "select distinct grantor.name as grantor, authid.name as grantee, p.protecttype as type, p.action, owner.name as owner, obj.name as object, obj.type as objectType, cols.name as column_name from sysprotects p join master.dbo.spt_values c on convert(tinyint,substring(isnull(p.columns,0x1), c.low,1)) & c.high != 0 and c.type = 'P' and c.number <= 1024 join sysusers authid on authid.uid = p.uid and authid.name like ? join sysobjects obj on p.id = obj.id join sysusers owner on obj.uid = owner.uid join sysusers grantor on p.grantor = grantor.uid left  join  syscolumns cols on	(cols.id = p.id and cols.colid = c.number)";

    //query object privileges
    String QUERY_OBJECT_PRIVILEGES 			   = "select distinct grantor.name as grantor,  authid.name as grantee, p.protecttype as type,  p.action, owner.name as owner, obj.name as object, obj.type as objectType, cols.name as column_name  from sysprotects p join master.dbo.spt_values c  on convert(tinyint,substring(isnull(p.columns,0x1), c.low,1)) & c.high != 0  and c.type = 'P' and c.number <= 1024   join sysusers authid on authid.uid = p.uid  join sysobjects obj on p.id = obj.id   join sysusers owner on obj.uid = owner.uid   join sysusers grantor on p.grantor = grantor.uid   left  join  syscolumns cols on    (cols.id = p.id and cols.colid = c.number)  where obj.name = ?";

    String QUERY_OBJECT_PRIVILEGES_FILTER_SCHEMA 			   = QUERY_OBJECT_PRIVILEGES + " and owner.name = ?";
    
	String QUERY_PROCEDURE_STATUS 			   = "SELECT O.sysstat2 FROM sysusers U, sysobjects O, sysprocedures P WHERE U.uid=O.uid and P.id = O.id and P.sequence =0 AND P.status & 4096 != 4096 AND U.name like ? and O.name like ? and O.type='P' and P.number = ?";

	String QUERY_COLUMN_STATUS 				   = "SELECT C.status, C.status2 FROM syscolumns C where C.id = ? AND C.name like ?";

	String QUERY_OBJECT_ID 					   = "SELECT O.id from sysobjects O, sysusers U where U.uid = O.uid and U.name = ? and O.name = ?";

    String QUERY_USER_INFO                     = "SELECT L.name AS LONGIN_NAME FROM master.dbo.syslogins L, sysusers U WHERE U.suid = L.suid AND U.name = ?";

    String QUERY_FOREIGNKEY_MATCH_FULL         = "SELECT status from sysobjects O, sysreferences R where R.constrid = O.id and O.name = ? and O.type = 'RI'";
    
    String QUERY_TEMPDB_NAME 				   = "SELECT db_name(@@tempdbid)";

}
