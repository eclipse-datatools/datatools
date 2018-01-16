package org.eclipse.datatools.enablement.sybase.asa.catalog;

public interface ASASQLs {

	final static public String COLUMN_NAME_DELIMITER = " #$% ";
	final static public String COLUMN_ORDER_DELIMITER = " , ";
	
	// event
	static final String QUERY_EVENTS = "SELECT TRIM(event_name) as event_name,TRIM(user_name) as user_name from sys.sysevent as evt, sysuserperms as up where evt.creator = up.user_id ORDER BY event_name";

	static final String QUERY_EVENT_INFO = "select enabled, location, source, condition,	evtt.name as 'event type', evt.remarks from sysevent as evt left outer join syseventtype as evtt on evtt.event_type_id = evt.event_type_id where event_name like ?";
	
	static final String QUERY_EVENT_SCHEDULES = "select sched_name, recurring, start_time, stop_time, start_date, days_of_week,	days_of_month, interval_units, interval_amt from sysschedule as sched, sysevent as evt where sched.event_id = evt.event_id and evt.event_name like ?";

	// user & group
	static final String QUERY_USER_GROUP = "SELECT TRIM(user_name) as user_name, user_group from SYS.SYSUSERPERMS ORDER BY user_name";

	// dbspace
	static final String QUERY_DBSPACES = "SELECT TRIM(dbspace_name) as dbspace_name FROM SYS.SYSFILE ORDER BY dbspace_name";

	static final String QUERY_DBSPACE_FILENAME = "SELECT TRIM(file_name) as file_name FROM SYS.SYSFILE WHERE dbspace_name = ?";

	// database info
	static final String QUERY_DB_INFO1 = "SELECT db_property('File'), db_property('LogName'), db_property('LogMirrorName'), db_property( 'PageSize'), db_property( 'Encryption'), db_property( 'CaseSensitive'), db_property( 'BlankPadding'), db_property( 'Collation' ), db_property( 'CheckSum' ), db_property( 'CaseSensitivePasswords' )";

	static final String QUERY_DB_INFO2 = "SELECT db_property( 'JDKVersion' ), IF EXISTS( SELECT jar_file FROM SYS.SYSJAR WHERE jar_name = 'ASASystem' ) THEN 'Y' ELSE 'N' ENDIF, IF EXISTS( SELECT OBJECT_ID('dbo.sp_mda')) THEN 'Y' ELSE 'N' ENDIF, IF EXISTS( SELECT OBJECT_ID('SYS.SYSCOLUMNS')) THEN 'N' ELSE 'Y' ENDIF";

	//TABLE
	static final String QUERY_TABLES = "SELECT TRIM(T.table_name) as 'TABLE_NAME', if (T.existing_obj IS NOT NULL) THEN 'PROXY TABLE' ELSE  T.table_type  endif as 'TABLE_TYPE', T.existing_obj as 'EXISTING' FROM SYS.SYSTABLE T JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator where U.user_name = ? AND T.table_type<>'JVT' order by 1";
	static final String QUERY_TABLE_INFOS = "SELECT TRIM(T.table_name) as 'TABLE_NAME', if (T.existing_obj IS NOT NULL) THEN 'PROXY TABLE' ELSE  T.table_type  endif as 'TABLE_TYPE', T.existing_obj as 'EXISTING', TRIM(F.dbspace_name) as dbspace_name FROM SYS.SYSTABLE T JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator JOIN SYS.SYSFILE F ON F.file_id = T.file_id LEFT OUTER JOIN SYS.SYSATTRIBUTE AC ON AC.object_type = 'T' AND AC.object_id = T.table_id AND AC.attribute_id = 2 AND AC.attribute_value = 0 LEFT OUTER JOIN SYS.SYSATTRIBUTE AP ON AP.object_type = 'T' AND AP.object_id = T.table_id AND AP.attribute_id = 1 where U.user_name = ? AND T.table_type<>'JVT' order by 1";

	static final String QUERY_TABLE_COLLUMNS = "SELECT DISTINCT C.column_id, TRIM(C.column_name) as 'COLUMN_NAME' FROM SYS.SYSCOLUMN C JOIN SYS.SYSTABLE T ON T.table_id = C.table_id JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator WHERE U.user_name = ? AND T.table_name = ? ORDER BY C.column_id";
	
	//ROUTINE
	static final String QUERY_ROUTINES = "SELECT TRIM(P.proc_name) as 'PROCEDURE_NAME', IFNULL( R.parm_id, 1, 2 ) AS 'PROCEDURE_TYPE', S.srvname AS 'REMOTE SERVER' FROM SYS.SYSPROCEDURE P JOIN SYS.SYSUSERPERMS U ON U.user_id = P.creator AND U.user_name = ? LEFT OUTER JOIN SYS.SYSPROCPARM R ON R.proc_id = P.proc_id AND R.parm_type = 4 LEFT OUTER JOIN SYS.SYSSERVERS S ON S.srvid = P.srvid ORDER BY P.proc_name, U.user_name";
	//ASA 10
	static final String QUERY_ROUTINES_ASA10 = "SELECT TRIM(P.proc_name) AS 'PROCEDURE_NAME', IFNULL( M.parm_id, 1, 2 ) AS 'PROCEDURE_TYPE', S.srvname AS 'REMOTE SERVER' FROM SYS.SYSPROCEDURE P JOIN SYS.SYSUSER U ON U.user_id = P.creator LEFT OUTER JOIN SYS.SYSPROCPARM M ON M.proc_id = P.proc_id AND M.parm_type = 4 LEFT OUTER JOIN SYS.SYSSERVER S ON S.srvid = P.srvid where U.user_name = ? ORDER BY P.proc_name, U.user_name";	
	
	//UDT
	static final String QUERY_UDTS = "select 2001 as 'DATA_TYPE', TRIM(type_name) as 'TYPE_NAME' from sys.sysusertype where creator=suser_id(?) and domain_id <> 25 ORDER BY 2";

	static final String QUERY_CONSTRAINTS = "select TRIM(P.constraint_name) as constraint_name from SYS.SYSCONSTRAINT P, SYS.SYSTABLE T, sys.sysuserperms U WHERE P.table_id = T.table_id AND P.constraint_type = ? AND T.table_name = ? and T.creator = U.user_id and U.user_name = ? ORDER BY 1";
	static final String QUERY_CONSTRAINTS_ASA10 = "select TRIM(P.constraint_name) as constraint_name from SYS.SYSCONSTRAINT P, SYS.SYSTABLE T, sys.sysuserperms U WHERE P.table_object_id = T.object_id AND P.constraint_type = ? AND T.table_name = ? and T.creator = U.user_id and U.user_name = ? ORDER BY 1";

	//static final String QUERY_TABLE_INDICES = "SELECT I.index_name FROM SYS.SYSINDEX I JOIN SYS.SYSTABLE T ON T.table_id = I.table_id JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator WHERE I.\"unique\" <> 'U' AND T.table_name like ? AND U.user_name like ?";
	//remove I.\"unique\" <> 'U' in where clause to retrieve system gened index for unique constraints 
	static final String QUERY_TABLE_INDICES = "SELECT TRIM(I.index_name), I.\"unique\" FROM SYS.SYSINDEX I JOIN SYS.SYSTABLE T ON T.table_id = I.table_id JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator WHERE U.user_name = ? AND T.table_name = ? ORDER BY 1";
	static final String QUERY_TABLE_INDICES_ASA10 = "select TRIM(I.index_name), I.\"unique\", I.index_category from sys.sysidx I, sys.systable T, sys.sysuserperms U where I.table_id = T.table_id and U.user_id = T.creator and U.user_name = ? and T.table_name = ? ORDER BY 1"; 
	static final String BATCH_QUERY_TABLE_INDICES = "SELECT TRIM(I.index_name), TRIM(T.table_name), I.\"unique\" FROM SYS.SYSINDEX I JOIN SYS.SYSTABLE T ON T.table_id = I.table_id JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator WHERE U.user_name = ? ORDER BY 1";
	static final String BATCH_QUERY_TABLE_INDICES_ASA10 = "select TRIM(I.index_name),TRIM(T.table_name), I.\"unique\", I.index_category from sys.sysidx I, sys.systable T, sys.sysuserperms U where I.table_id = T.table_id and U.user_id = T.creator and U.user_name = ? ORDER BY 1"; 

	static final String QUERY_TABLE_TRIGGERS = "SELECT TRIM(G.trigger_name) as trigger_name FROM SYS.SYSTRIGGER G JOIN SYS.SYSTABLE T ON T.table_id = G.table_id JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator WHERE G.foreign_key_id IS NULL AND U.user_name = ? AND T.table_name = ? ORDER BY 1";
	static final String BATCH_QUERY_TABLE_TRIGGERS = "SELECT TRIM(G.trigger_name) as trigger_name, TRIM(T.table_name) FROM SYS.SYSTRIGGER G JOIN SYS.SYSTABLE T ON T.table_id = G.table_id JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator WHERE G.foreign_key_id IS NULL AND U.user_name = ? ORDER BY 1"; 

	//TODO:we can reduce redundant load work of index_info and constraint_info in the future  
	//for ASA9 
	//static final String QUERY_INDEX_INFO = "SELECT I.index_name, F.dbspace_name, LIST( C.column_name, '" + COLUMN_NAME_DELIMITER + "' ORDER BY X.sequence ) AS column_list, I.\"unique\", IFNULL( A.attribute_value, 'N', 'Y' ) AS \"clustered\", I.remarks, LIST(X.\"order\", '" + COLUMN_ORDER_DELIMITER + "' ORDER BY X.sequence ) AS column_order FROM SYS.SYSINDEX I JOIN SYS.SYSTABLE T ON T.table_id = I.table_id JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator JOIN SYS.SYSIXCOL X ON X.table_id = I.table_id AND X.index_id = I.index_id JOIN SYS.SYSCOLUMN C ON C.table_id = X.table_id AND C.column_id = X.column_id JOIN SYS.SYSFILE F ON F.file_id = I.file_id LEFT OUTER JOIN SYS.SYSATTRIBUTE A ON A.object_type = 'T' AND A.object_id = T.table_id AND A.attribute_id = 2 AND A.attribute_value = I.index_id WHERE I.\"unique\" <> 'U' AND U.user_name like ? AND I.index_name like ? AND T.table_name like ? GROUP BY I.index_name, F.dbspace_name, \"unique\", \"clustered\", I.remarks ORDER BY I.index_name";
	static final String QUERY_INDEX_INFO = "SELECT TRIM(I.index_name) AS INDEX_NAME, TRIM(F.dbspace_name) AS DBSPACE_NAME, LIST( TRIM(C.column_name), '" + COLUMN_NAME_DELIMITER + "' ORDER BY X.sequence ) AS COLUMN_LIST, I.\"unique\" AS \"UNIQUE\", IFNULL( A.attribute_value, 'N', 'Y' ) AS \"CLUSTERED\", I.remarks AS REMARKS, LIST(X.\"order\", '" + COLUMN_ORDER_DELIMITER + "' ORDER BY X.sequence ) AS COLUMN_ORDER FROM SYS.SYSINDEX I JOIN SYS.SYSTABLE T ON T.table_id = I.table_id JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator JOIN SYS.SYSIXCOL X ON X.table_id = I.table_id AND X.index_id = I.index_id JOIN SYS.SYSCOLUMN C ON C.table_id = X.table_id AND C.column_id = X.column_id JOIN SYS.SYSFILE F ON F.file_id = I.file_id LEFT OUTER JOIN SYS.SYSATTRIBUTE A ON A.object_type = 'T' AND A.object_id = T.table_id AND A.attribute_id = 2 AND A.attribute_value = I.index_id WHERE U.user_name = ? AND T.table_name = ? AND I.index_name = ? GROUP BY I.index_name, F.dbspace_name, \"unique\", \"clustered\", I.remarks ORDER BY I.index_name";
	//for ASA10
	static final String QUERY_INDEX_INFO_ASA10 = "SELECT TRIM(I.index_name) AS INDEX_NAME, TRIM(D.dbspace_name) AS DBSPACE_NAME, LIST( TRIM(C.column_name), '" + COLUMN_NAME_DELIMITER + "' ORDER BY X.sequence ) AS COLUMN_LIST, I.\"unique\" AS \"UNIQUE\", IF T.clustered_index_id IS NOT NULL AND T.clustered_index_id = I.index_id THEN 'Y' ELSE 'N' ENDIF AS \"CLUSTERED\", R.remarks AS REMARKS, LIST(X.\"order\", '" + COLUMN_ORDER_DELIMITER + "' order by x.sequence) as COLUMN_ORDER , I.phys_index_id AS PHYS_ID, N.constraint_name AS CONSTRAINT_NAME, T.table_name AS TABLE_NAME, U.user_name AS OWNER, T.table_type AS TABLE_TYPE, P.existing_obj AS EXISTING_OBJ, I.index_category AS INDEX_CATEGORY FROM SYS.SYSIDX I LEFT OUTER JOIN SYS.SYSCONSTRAINT N ON N.ref_object_id = I.object_id JOIN SYS.SYSTAB T ON T.table_id = I.table_id JOIN SYS.SYSUSER U ON U.user_id = T.creator LEFT OUTER JOIN SYS.SYSPROXYTAB P ON P.table_object_id = T.object_id JOIN SYS.SYSIDXCOL X ON X.table_id = I.table_id AND X.index_id = I.index_id JOIN SYS.SYSTABCOL C ON C.table_id = X.table_id AND C.column_id = X.column_id LEFT OUTER JOIN SYS.SYSFILE D ON D.file_id = I.file_id AND I.phys_index_id IS NOT NULL LEFT OUTER JOIN SYS.SYSREMARK R ON R.object_id = I.object_id WHERE U.user_name = ? AND T.table_name = ? AND I.index_name = ? GROUP BY I.table_id, I.index_id, I.object_id, I.phys_index_id, N.constraint_id, I.index_name, N.constraint_name, T.table_name, U.user_name, T.table_type, P.existing_obj, I.index_category, I.\"unique\", D.dbspace_name, \"clustered\", R.remarks";
	
	static final String QUERY_TRIGGER_INFO = "SELECT TRIM(G.trigger_name) as trigger_name, G.\"event\", G.trigger_time, G.trigger_order, G.trigger_defn, G.remarks, G.source FROM SYS.SYSTRIGGER G JOIN SYS.SYSTABLE T ON T.table_id = G.table_id JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator WHERE G.foreign_key_id IS NULL AND U.user_name like ? AND T.table_name like ? AND G.trigger_name like ?";
	
	static final String QUERY_PK_INFO = "SELECT TRIM(P.constraint_name) AS pkey_name, LIST( TRIM(C.column_name), '" + COLUMN_NAME_DELIMITER + "' ORDER BY C.column_id ) AS pkey_column_list, IFNULL( AC.attribute_value, 'N', 'Y' ) AS pkey_clustered, '' AS INDEX_NAME FROM SYS.SYSTABLE T JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator LEFT OUTER JOIN SYS.SYSCONSTRAINT P ON P.table_id = T.table_id AND P.constraint_type = 'P' LEFT OUTER JOIN SYS.SYSCOLUMN C ON C.table_id = T.table_id AND C.pkey = 'Y' LEFT OUTER JOIN SYS.SYSATTRIBUTE AC ON AC.object_type = 'T' AND AC.object_id = T.table_id AND AC.attribute_id = 2 AND AC.attribute_value = 0 LEFT OUTER JOIN SYS.SYSATTRIBUTE AP ON AP.object_type = 'T' AND AP.object_id = T.table_id AND AP.attribute_id = 1 WHERE T.table_type <> 'VIEW' AND U.user_name = ? AND T.table_name = ? AND P.constraint_name = ? group by pkey_name, pkey_clustered";
	static final String QUERY_UC_INFO = "SELECT TRIM(I.index_name) as INDEX_NAME, LIST(TRIM(C.column_name), '" + COLUMN_NAME_DELIMITER + "' ORDER BY X.sequence ) AS column_list, IFNULL( A.attribute_value, 'N', 'Y' ) AS \"clustered\" FROM SYS.SYSINDEX I JOIN SYS.SYSIXCOL X ON X.table_id = I.table_id AND X.index_id = I.index_id JOIN SYS.SYSCOLUMN C ON C.table_id = X.table_id AND C.column_id = X.column_id JOIN SYS.SYSTABLE T ON T.table_id = I.table_id JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator LEFT OUTER JOIN SYS.SYSATTRIBUTE A ON A.object_type = 'T' AND A.object_id = T.table_id AND A.attribute_id = 2 AND A.attribute_value = I.index_id JOIN SYS.SYSCONSTRAINT R ON I.index_id = R.index_id and I.table_id = R.table_id WHERE I.\"unique\" = 'U' AND U.user_name = ? AND T.table_name = ? AND R.constraint_name = ? GROUP BY I.index_name, \"clustered\"";	
    static final String QUERY_FK_INFO = "SELECT TRIM(F.role) AS CONSTRAINT_NAME, LIST( TRIM(FC.column_name), '" + COLUMN_NAME_DELIMITER +  "' ORDER BY K.foreign_column_id ) AS FOREIGN_COLUMN_LIST, IFNULL( A.attribute_value, 'N', 'Y' ) AS \"CLUSTERED\", F.remarks AS REMARKS, TRIM(PT.table_name) AS PRIM_TABLE_NAME, TRIM(PU.user_name) AS PRIM_TABLE_OWNER, LIST( TRIM(PC.column_name), '" + COLUMN_NAME_DELIMITER + "' ORDER BY K.foreign_column_id ) AS PRIM_COLUMN_LIST, TRIM(UT.referential_action) AS UPDATE_ACTION, TRIM(DT.referential_action) AS DELETE_ACTION, F.check_on_commit AS CHECK_ON_COMMIT, F.\"NULLS\" AS \"NULLS\" FROM SYS.SYSFOREIGNKEY F JOIN SYS.SYSFKCOL K ON K.foreign_table_id = F.foreign_table_id AND K.foreign_key_id = F.foreign_key_id JOIN SYS.SYSCOLUMN FC ON FC.table_id = F.foreign_table_id AND FC.column_id = K.foreign_column_id JOIN SYS.SYSCOLUMN PC ON PC.table_id = F.primary_table_id AND PC.column_id = K.primary_column_id JOIN SYS.SYSTABLE FT ON FT.table_id = F.foreign_table_id JOIN SYS.SYSUSERPERMS FU ON FU.user_id = FT.creator LEFT OUTER JOIN SYS.SYSATTRIBUTE A ON A.object_type = 'T' AND A.object_id = FT.table_id AND A.attribute_id = 2 AND A.attribute_value = F.foreign_key_id JOIN SYS.SYSTABLE PT ON PT.table_id = F.primary_table_id JOIN SYS.SYSUSERPERMS PU ON PU.user_id = PT.creator LEFT OUTER JOIN SYS.SYSTRIGGER UT ON UT.foreign_table_id = F.foreign_table_id AND UT.foreign_key_id = F.foreign_key_id AND UT.\"event\" = 'C' LEFT OUTER JOIN SYS.SYSTRIGGER DT ON DT.foreign_table_id = F.foreign_table_id AND DT.foreign_key_id = F.foreign_key_id AND DT.\"event\" = 'D' WHERE FU.user_name = ? AND FT.table_name = ? AND F.role like ? GROUP BY F.foreign_key_id, F.role, PT.table_name, PU.user_name, F.check_on_commit, F.\"nulls\", UT.referential_action, DT.referential_action, \"clustered\",  F.remarks";
	static final String QUERY_CC_INFO = "SELECT TRIM(C.column_name) as column_name, K.check_defn FROM SYS.SYSCONSTRAINT S LEFT OUTER JOIN SYS.SYSCOLUMN C ON C.table_id = S.table_id AND C.column_id = S.column_id JOIN SYS.SYSCHECK K ON K.check_id = S.constraint_id JOIN SYS.SYSTABLE T ON T.table_id = S.table_id JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator WHERE U.user_name like ? AND T.table_name LIKE ? AND S.constraint_name like ?";
	//ASA10 for CC
	static final String QUERY_CC_INFO_ASA10 = "SELECT TRIM(S.constraint_name), K.check_defn, S.constraint_type, C.column_name FROM SYS.SYSCONSTRAINT S LEFT OUTER JOIN SYS.SYSTABCOL C ON C.object_id = S.ref_object_id JOIN SYS.SYSCHECK K ON K.check_id = S.constraint_id JOIN SYS.SYSTAB T ON T.object_id = S.table_object_id JOIN SYS.SYSUSER U ON U.user_id = T.creator WHERE S.constraint_type IN ( 'T', 'C' ) AND U.user_name = ? AND T.table_name = ? AND S.constraint_name = ?";
	//ASA10 for PK��FK��UC
	static final String QUERY_CONSTRAINT_INDEX_INFO_ASA10 = "SELECT TRIM(N.constraint_name) AS CONSTRAINT_NAME, LIST( TRIM(C.column_name), '" + COLUMN_NAME_DELIMITER + "' ORDER BY X.sequence ) AS COLUMN_LIST, IF T.clustered_index_id IS NOT NULL AND T.clustered_index_id = I.index_id THEN 'Y' ELSE 'N' ENDIF AS \"CLUSTERED\", R.remarks AS REMARKS, PT.table_name AS PRIM_TABLE_NAME, TRIM(PU.user_name) AS PRIM_TABLE_OWNER, LIST( TRIM(PC.column_name), '" + COLUMN_NAME_DELIMITER + "' ) AS PRIM_COLUMN_LIST,TRIM(UT.referential_action) AS UPDATE_ACTION, TRIM(DT.referential_action) AS DELETE_ACTION, F.check_on_commit AS CHECK_ON_COMMIT, F.\"nulls\" AS \"NULLS\", F.match_type AS MATCH_TYPE, TRIM(D.dbspace_name) AS \"DBSPACE\", LIST(X.\"order\", '" + COLUMN_ORDER_DELIMITER + "' order by x.sequence) as ORDERS, I.phys_index_id AS PHYS_INDEX_ID, I.index_name AS INDEX_NAME, T.table_type AS TABLE_TYPE, P.existing_obj AS EXISTING_OBJ, I.index_category AS INDEX_CATEGORY, I.\"unique\" AS INDEX_UNIQUE, PI.index_name AS PRIM_INDEX_NAME, PI.index_category AS PRIM_INDEX_CATEGORY, PI.\"unique\" AS PRIM_INDEX_UNIQUUE, PN.constraint_name AS PRIM_CONSTRAINT_NAME FROM SYS.SYSIDX I LEFT OUTER JOIN SYS.SYSCONSTRAINT N ON N.ref_object_id = I.object_id JOIN SYS.SYSTAB T ON T.table_id = I.table_id JOIN SYS.SYSUSER U ON U.user_id = T.creator LEFT OUTER JOIN SYS.SYSPROXYTAB P ON P.table_object_id = T.object_id JOIN SYS.SYSIDXCOL X ON X.table_id = I.table_id AND X.index_id = I.index_id JOIN SYS.SYSTABCOL C ON C.table_id = X.table_id AND C.column_id = X.column_id LEFT OUTER JOIN ( SYS.SYSFKEY F JOIN SYS.SYSIDX PI ON PI.table_id = F.primary_table_id AND PI.index_id = F.primary_index_id LEFT OUTER JOIN SYS.SYSCONSTRAINT PN ON PN.ref_object_id = PI.object_id JOIN SYS.SYSTAB PT ON PT.table_id = PI.table_id JOIN SYS.SYSUSER PU ON PU.user_id = PT.creator JOIN SYS.SYSTABCOL PC ON PC.table_id = F.primary_table_id LEFT OUTER JOIN SYS.SYSTRIGGER UT ON UT.foreign_table_id = F.foreign_table_id AND UT.foreign_key_id = F.foreign_index_id AND UT.\"event\" = 'C' LEFT OUTER JOIN SYS.SYSTRIGGER DT ON DT.foreign_table_id = F.foreign_table_id AND DT.foreign_key_id = F.foreign_index_id AND DT.\"event\" = 'D' ) ON F.foreign_table_id = I.table_id AND F.foreign_index_id = I.index_id AND PC.column_id = X.primary_column_id LEFT OUTER JOIN SYS.SYSFILE D ON D.file_id = I.file_id AND I.phys_index_id IS NOT NULL LEFT OUTER JOIN SYS.SYSREMARK R ON R.object_id = I.object_id WHERE U.user_name = ? AND T.table_name = ? AND N.constraint_name = ? GROUP BY I.table_id, I.index_id, I.object_id, I.phys_index_id, N.constraint_id, I.index_name, N.constraint_name, T.table_name, U.user_name, T.table_type, P.existing_obj, I.index_category, I.\"unique\", PI.table_id, PI.index_id, PI.index_name, PN.constraint_name, PT.table_name, PU.user_name, PI.index_category, PI.\"unique\", F.check_on_commit, F.\"nulls\", F.match_type, UT.referential_action, DT.referential_action, D.dbspace_name, \"clustered\", R.remarks";
	
	static final String QUERY_COLUMN_INFO = "SELECT DISTINCT C.column_id, TRIM(C.column_name) as column_name, TRIM(D.domain_name) as domain_name, C.width, C.scale, TRIM(Y.type_name), TRIM(UT.user_name), C.\"nulls\", IFNULL( I.index_id, 'N', 'Y' ) AS \"unique\",C.column_type, C.\"default\", C.remarks FROM SYS.SYSCOLUMN C JOIN SYS.SYSDOMAIN D ON D.domain_id = C.domain_id LEFT OUTER JOIN SYS.SYSUSERTYPE Y ON Y.type_id = C.user_type LEFT OUTER JOIN SYS.SYSINDEX I ON I.table_id = C.table_id AND I.\"unique\" = 'U' AND (SELECT COUNT(*) FROM SYS.SYSIXCOL XA WHERE XA.table_id = I.table_id AND XA.index_id = I.index_id AND XA.column_id = C.column_id) = 1 AND (SELECT COUNT(*) FROM SYS.SYSIXCOL XB WHERE XB.table_id = I.table_id AND XB.index_id = I.index_id AND XB.column_id <> C.column_id) = 0 JOIN SYS.SYSTABLE T ON T.table_id = C.table_id JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator LEFT JOIN SYS.SYSUSERPERMS UT ON UT.user_id = Y.creator WHERE U.user_name like ? AND T.table_name like ?";// AND C.column_name like ?
	static final String QUERY_UDT_INFO     = "SELECT TRIM(T.type_name) as type_name, TRIM(U.user_name) as user_name, TRIM(D.domain_name), T.width, T.scale, T.\"nulls\", T.\"default\", T.\"check\" FROM SYS.SYSUSERTYPE T JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator JOIN SYS.SYSDOMAIN D ON D.domain_id = T.domain_id WHERE D.domain_name <> 'java.lang.Object' AND T.type_name = ?";
	static final String QUERY_ALL_UDT_INFO = "SELECT TRIM(T.type_name) as type_name, TRIM(U.user_name) as user_name, TRIM(D.domain_name), T.width, T.scale, T.\"nulls\", T.\"default\", T.\"check\" FROM SYS.SYSUSERTYPE T JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator JOIN SYS.SYSDOMAIN D ON D.domain_id = T.domain_id WHERE D.domain_name <> 'java.lang.Object'";
	static final String QUERY_ROUTINE_INFO = "SELECT TRIM(S.srvname) as srvname, TRIM(P.remarks) as remarks, P.source, P.proc_defn FROM SYS.SYSPROCEDURE P JOIN SYS.SYSUSERPERMS U ON U.user_id = P.creator LEFT OUTER JOIN SYS.SYSPROCPARM R ON R.proc_id = P.proc_id AND R.parm_type = 4 LEFT OUTER JOIN SYS.SYSSERVERS S ON S.srvid = P.srvid WHERE U.user_name = ? AND P.proc_name = ?";
    //ASA10
    static final String QUERY_ROUTINE_INFO_ASA10 = "SELECT TRIM(S.srvname), R.remarks ,P.source,P.proc_defn,IF LENGTH( P.proc_defn ) <= 65535 THEN SQLDIALECT( P.proc_defn ) ENDIF AS dialect FROM SYS.SYSPROCEDURE P JOIN SYS.SYSUSER U ON U.user_id = P.creator LEFT OUTER JOIN SYS.SYSPROCPARM M ON M.proc_id = P.proc_id AND M.parm_type = 4 LEFT OUTER JOIN SYS.SYSSERVER S ON S.srvid = P.srvid LEFT OUTER JOIN SYS.SYSREMARK R ON R.object_id = P.object_id WHERE U.user_name = ? AND P.proc_name = ?";
    
	static final String QUERY_PARAMETER_INFO = "SELECT R.parm_id, TRIM(R.parm_name) as parm_name, TRIM(D.domain_name) as domain_name, R.width, R.scale, Y.type_name, R.parm_type, R.parm_mode_in, R.parm_mode_out FROM SYS.SYSPROCPARM R JOIN SYS.SYSDOMAIN D ON D.domain_id = R.domain_id LEFT OUTER JOIN SYS.SYSUSERTYPE Y ON Y.type_id = R.user_type JOIN SYS.SYSPROCEDURE P ON P.proc_id = R.proc_id JOIN SYS.SYSUSERPERMS U ON U.user_id = P.creator WHERE U.user_name like ? AND P.proc_name like ? AND R.parm_name like ? ORDER by R.parm_id";
	static final String QUERY_VIEW_DEFN = "SELECT T.source, T.view_def, T.remarks FROM SYS.SYSTABLE T JOIN SYS.SYSUSERPERMS U ON T.creator = U.user_id WHERE T.table_type = 'VIEW' AND U.user_name like ? AND T.table_name like ?";
	static final String QUERY_ALL_TABLE_INFO = "SELECT TRIM(F.dbspace_name) as dbspace_name, T.table_name FROM SYS.SYSTABLE T JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator JOIN SYS.SYSFILE F ON F.file_id = T.file_id LEFT OUTER JOIN SYS.SYSATTRIBUTE AC ON AC.object_type = 'T' AND AC.object_id = T.table_id AND AC.attribute_id = 2 AND AC.attribute_value = 0 LEFT OUTER JOIN SYS.SYSATTRIBUTE AP ON AP.object_type = 'T' AND AP.object_id = T.table_id AND AP.attribute_id = 1 WHERE T.table_type <> 'VIEW' AND U.user_name = ?";
	static final String QUERY_ALL_TABLE_INFO_ASA10 = "SELECT TRIM(F.dbspace_name) AS DBSPACE_NAME, T.table_name FROM SYS.SYSTAB T JOIN SYS.SYSUSER U ON U.user_id = T.creator LEFT OUTER JOIN SYS.SYSPROXYTAB P ON P.table_object_id = T.object_id LEFT OUTER JOIN SYS.SYSSERVER S ON S.srvid = P.srvid LEFT OUTER JOIN SYS.SYSFILE F ON F.file_id = T.file_id AND T.table_type = 1 AND P.existing_obj IS NULL LEFT OUTER JOIN SYS.SYSREMARK R ON R.object_id = T.object_id WHERE T.table_type NOT IN ( 2, 21 ) AND U.user_name = ?";
	static final String QUERY_TABLE_INFO = "SELECT TRIM(F.dbspace_name) as dbspace_name, T.last_page, T.existing_obj, T.remote_location, T.\"replicate\", AP.attribute_value AS \"pctfree\", T.remarks FROM SYS.SYSTABLE T JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator JOIN SYS.SYSFILE F ON F.file_id = T.file_id LEFT OUTER JOIN SYS.SYSATTRIBUTE AC ON AC.object_type = 'T' AND AC.object_id = T.table_id AND AC.attribute_id = 2 AND AC.attribute_value = 0 LEFT OUTER JOIN SYS.SYSATTRIBUTE AP ON AP.object_type = 'T' AND AP.object_id = T.table_id AND AP.attribute_id = 1 WHERE T.table_type <> 'VIEW' AND U.user_name like ? AND T.table_name like ? ";
    static final String QUERY_TABLE_INFO_ASA10 = "SELECT TRIM(F.dbspace_name) AS DBSPACE_NAME, T.commit_action AS COMMIT_ACTION, P.existing_obj AS EXISTING_OBJ, P.remote_location AS REMOTE_LOC, T.\"replicate\" AS \"REPLICATE\", T.pct_free AS \"PCTFREE\", R.remarks AS REMARKS, S.srvclass, T.\"encrypted\", T.count, T.table_type, T.share_type FROM SYS.SYSTAB T JOIN SYS.SYSUSER U ON U.user_id = T.creator LEFT OUTER JOIN SYS.SYSPROXYTAB P ON P.table_object_id = T.object_id LEFT OUTER JOIN SYS.SYSSERVER S ON S.srvid = P.srvid LEFT OUTER JOIN SYS.SYSFILE F ON F.file_id = T.file_id AND T.table_type = 1 AND P.existing_obj IS NULL LEFT OUTER JOIN SYS.SYSREMARK R ON R.object_id = T.object_id WHERE T.table_type NOT IN ( 2, 21 ) AND U.user_name = ? AND T.table_name = ?";
    

    static final String QUERY_TABLE_PERMISSIONS = "SELECT TRIM(T.table_name) as table_name, TRIM(U.user_name) AS Table_Owner, P.selectauth, P.insertauth, P.deleteauth, P.updateauth, P.alterauth, P.referenceauth, G.user_name as Grantor, P.updatecols, P.stable_id FROM SYS.SYSTABLE T JOIN SYS.SYSUSERPERMS U ON U.user_id = T.creator LEFT OUTER JOIN SYS.SYSUSERPERMS E ON E.user_name = ? LEFT OUTER JOIN SYS.SYSTABLEPERM P ON P.stable_id = T.table_id AND P.grantee = E.user_id LEFT JOIN SYS.SYSUSERPERMS G ON P.grantor = G.user_id where P.stable_id <> null ORDER BY T.table_name, U.user_name";
	static final String QUERY_ROUTINE_PERMISSIONS = "SELECT TRIM(F.proc_name) as proc_name, TRIM(U.user_name) as user_name, IFNULL( R.parm_id, 'N', 'Y' ) AS \"function\", IFNULL( P.grantee, 'N', 'Y' ) AS executeauth FROM SYS.SYSPROCEDURE F JOIN SYS.SYSUSERPERMS U ON U.user_id = F.creator LEFT OUTER JOIN SYS.SYSPROCPARM R ON R.proc_id = F.proc_id AND R.parm_type = 4 LEFT OUTER JOIN SYS.SYSUSERPERMS E ON E.user_name = ? LEFT OUTER JOIN SYS.SYSPROCPERM P ON P.proc_id = F.proc_id AND P.grantee = E.user_id ORDER BY F.proc_name, U.user_name";
	static final String QUERY_COLUMN_PERMISSIONS = "SELECT TRIM(T.table_name) as table_name, TRIM(U.user_name) as Table_Owner, TRIM(C.column_name), P.privilege_type, P.is_grantable, G.user_name as Grantor FROM sys.systable T join sys.SYSUSERPERMS U on T.creator = U.user_id join sys.syscolperm P on P.table_id = T.table_id join sys.sysuserperms E on P.grantee = E.user_id and E.user_name = ? join sys.syscolumn C on  C.table_id = T.table_id and C.column_id = P.column_id join sysuserperms G on G.user_id = P.grantor";
	static final String QUERY_TABLE_PERMISSIONS_BY_OBJ = "select P.selectauth,P.insertauth,P.deleteauth,P.updateauth,P.alterauth,P.referenceauth,Grantor=G.user_name,P.updatecols,P.stable_id,Grantee=E.user_name from SYS.SYSTABLEPERM as P,SYS.SYSUSERPERMS as E,SYS.SYSTABLE as T,SYS.SYSUSERPERMS as G,SYS.SYSUSERPERMS as U where U.user_name = ? and T.table_name = ? and P.stable_id = T.table_id and P.grantee = E.user_id and P.grantor = G.user_id and U.user_id = T.creator order by E.user_name asc";
	static final String QUERY_COLUMN_PERMISSIONS_BY_OBJ = "select P.privilege_type,P.is_grantable,Grantor=G.user_name,Grantee=E.user_name, trim(C.column_name) from sys.systable as T,sys.SYSUSERPERMS as U,sys.syscolperm as P,sys.sysuserperms as E,sys.syscolumn as C,sysuserperms as G where U.user_name = ? and T.table_name = ? and T.creator = U.user_id and G.user_id = P.grantor and C.column_id = P.column_id and P.table_id = T.table_id and P.grantee = E.user_id and C.table_id = T.table_id";
	static final String QUERY_ROUTINE_PERMISSIONS_BY_OBJ = "select executeauth=IFNULL(P.grantee,'N','Y'),Grantee=E.user_name from SYS.SYSPROCEDURE as F,SYS.SYSUSERPERMS as U,SYS.SYSUSERPERMS as E,SYS.SYSPROCPERM as P where U.user_name = ? and F.proc_name = ? and U.user_id = F.creator and P.proc_id = F.proc_id and P.grantee = E.user_id order by E.user_name asc";
    static final String QUERY_GROUP_MEMBERS = "select TRIM(U.user_name) as user_name from sys.sysuserperms U1 left join sys.sysgroup G on G.group_id = U1.user_id join sys.sysuserperms U on U.user_id = G.group_member where U1.user_name = ?";
    static final String QUERY_AUTHID_INFO = "select remarks from sysuserperms where user_name = ?";
	
	static final String QUERY_COLUMN_CONSTRAINTS = "select TRIM(constraint_name) as constraint_name, TRIM(C.column_name) from sys.sysconstraint R, sys.syscolumn C, sys.systable T, sys.sysuserperms U where R.constraint_type = 'C' and R.table_id = C.table_id and R.column_id = C.column_id and C.table_id = T.table_id and T.creator = U.user_id and U.user_name = ? and T.table_name = ?";
	static final String QUERY_COLUMN_CONSTRAINTS_ASA10 = "select TRIM(constraint_name), TRIM(C.column_name) from sys.sysconstraint R, sys.syscolumn C, sys.systable T, sys.sysuserperms U where R.constraint_type = 'C' and R.table_object_id = T.object_id and R.ref_object_id = C.object_id and C.table_id = T.table_id and T.creator = U.user_id and U.user_name = ? and T.table_name = ?"; 

    // Schema
    static final String QUERY_SCHEMAS = "SELECT TRIM(user_name) TABLE_SCHEM from SYS.SYSUSERPERMS ORDER BY user_name";

	// for ASA connection option set
    static final String SET_TEMPORARY_OPTION = "set temporary OPTION ";
    
    static final String QUOTED_IDENTIFIER = "quoted_identifier";
    
    // for IQ connection option set
    static final String QUERY_NAME = "query_name";
    static final String QUERY_PLAN = "query_plan";
    static final String QUERY_DETAIL = "query_detail";
    static final String QUERY_PLAN_AFTER_RUN = "query_plan_after_run";
    static final String QUERY_PLAN_AS_HTML = "query_plan_as_html";
    static final String ASE_BINARY_DISPLAY = "ase_binary_display";
    static final String ISOLATION_LEVEL = "ISOLATION_LEVEL";
}
