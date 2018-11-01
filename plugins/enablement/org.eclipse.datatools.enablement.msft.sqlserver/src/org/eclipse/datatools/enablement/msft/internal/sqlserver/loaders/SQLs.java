/*******************************************************************************
 * Copyright (c) 2008 NexB Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Anton Safonov and Ahti Kitsik - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders;

public class SQLs {

    public static final String QUERY_TABLE_TYPE_INFO = "SELECT o.id, o.type FROM dbo.sysobjects AS o, dbo.sysusers AS u WHERE o.uid = u.uid AND u.name = ? AND o.name = ? AND (o.type = 'U' OR o.type = 'S')";
	
	public static final String INDEX_INFO_QUERY = "select i.is_padded, s.no_recompute, i.ignore_dup_key, i.allow_row_locks, i.allow_page_locks, i.is_unique_constraint, i.is_primary_key from sys.indexes as i, sys.stats as s where i.object_id = ? and i.name = ? AND i.object_id=s.object_id AND i.name=s.name";

	public static final String SELECT_OBJ_BY_NAME = "SELECT OBJ.name, sys.sql_modules.definition FROM sys.all_objects AS OBJ JOIN sys.sql_modules ON OBJ.object_id = sys.sql_modules.object_id where OBJ.name = ?";
	
	public static final String SELECT_OBJ_BY_NAME_2000 = "execute sp_helptext ?";

	public static final String QUERY_TRIGGERS = "SELECT t.*, m.*, o.* FROM sys.triggers t INNER JOIN sys.sql_modules m ON t.object_id = m.object_id	inner join sys.objects o on o.object_id = t.parent_id WHERE t.type = 'TR' AND t.parent_class = 1 and o.name= ?";
	
	public static final String TRIGGER_SCHEMA = "select b.name from sys.all_objects a inner join sys.schemas b on a.schema_id=b.schema_id where object_id = ?";
	
	public static final String TRIGGER_EVENTS = "select b.type from sys.all_objects a inner join sys.trigger_events b on a.object_id=b.object_id where a.object_id = ?";
		
	public static final String QUERY_TRIGGERS_2000 = "execute sp_helptrigger ?";
	
	
	
	
}