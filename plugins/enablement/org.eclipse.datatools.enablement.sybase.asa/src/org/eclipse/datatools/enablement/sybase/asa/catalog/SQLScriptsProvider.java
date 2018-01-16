package org.eclipse.datatools.enablement.sybase.asa.catalog;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.modelbase.sql.schema.Database;

public class SQLScriptsProvider implements ASASQLs 
{
	static public String getQueryConstraintsScript(Database db)
	{
		assert(db instanceof SybaseASABaseDatabase);
		
		SybaseASABaseDatabase database = (SybaseASABaseDatabase)db;
		
		if(database.isBaseOnASA10())
		{
			return QUERY_CONSTRAINTS_ASA10;
		}
		else
		{
			return QUERY_CONSTRAINTS;
		}
	}
	
	static public String getQueryColumnConstraintsScript(Database db)
	{
		assert(db instanceof SybaseASABaseDatabase);
		
		SybaseASABaseDatabase database = (SybaseASABaseDatabase)db;
		
		if(database.isBaseOnASA10())
		{
			return QUERY_COLUMN_CONSTRAINTS_ASA10;
		}
		else
		{
			return QUERY_COLUMN_CONSTRAINTS;
		}
	}
	
	static public String getQueryTableIndex(Database db)
	{
		assert(db instanceof SybaseASABaseDatabase);
		
		SybaseASABaseDatabase database = (SybaseASABaseDatabase)db;
		
		if(database.isBaseOnASA10())
		{
			return QUERY_TABLE_INDICES_ASA10;
		}
		else
		{
			return QUERY_TABLE_INDICES;
		}
	}
	
	static public String getBatchQueryTableIndex(Database db)
    {
        assert(db instanceof SybaseASABaseDatabase);
        
        SybaseASABaseDatabase database = (SybaseASABaseDatabase)db;
        
        if(database.isBaseOnASA10())
        {
            return BATCH_QUERY_TABLE_INDICES_ASA10;
        }
        else
        {
            return BATCH_QUERY_TABLE_INDICES;
        }
    }
	
	static public String getQueryIndexInfo(Database db)
	{
		assert(db instanceof SybaseASABaseDatabase);
		
		SybaseASABaseDatabase database = (SybaseASABaseDatabase)db;
		
		if(database.isBaseOnASA10())
		{
			return QUERY_INDEX_INFO_ASA10;
		}
		else
		{
			return QUERY_INDEX_INFO;
		}
	}
	
	static public String getQueryPrimaryKeyInfo(Database db)
	{
		assert(db instanceof SybaseASABaseDatabase);
		
		SybaseASABaseDatabase database = (SybaseASABaseDatabase)db;
		
		if(database.isBaseOnASA10())
		{
			return QUERY_CONSTRAINT_INDEX_INFO_ASA10;
		}
		else
		{
			return QUERY_PK_INFO;
		}
	}
	
	static public String getQueryUnqiueConstraintInfo(Database db)
	{
		assert(db instanceof SybaseASABaseDatabase);
		
		SybaseASABaseDatabase database = (SybaseASABaseDatabase)db;
		
		if(database.isBaseOnASA10())
		{
			return QUERY_CONSTRAINT_INDEX_INFO_ASA10;
		}
		else
		{
			return QUERY_UC_INFO;
		}
	}
	
	static public String getQueryForeignKeyInfo(Database db)
	{
		assert(db instanceof SybaseASABaseDatabase);
		
		SybaseASABaseDatabase database = (SybaseASABaseDatabase)db;
		
		if(database.isBaseOnASA10())
		{
			return QUERY_CONSTRAINT_INDEX_INFO_ASA10;
		}
		else
		{
			return QUERY_FK_INFO;
		}
	}
	
	static public String getQueryCheckConstraintInfo(Database db)
	{
		assert(db instanceof SybaseASABaseDatabase);
		
		SybaseASABaseDatabase database = (SybaseASABaseDatabase)db;
		
		if(database.isBaseOnASA10())
		{
			return QUERY_CC_INFO_ASA10;
		}
		else
		{
			return QUERY_CC_INFO;
		}
	}
	
	static public String getQueryTableInfo(Database db)
	{
		assert(db instanceof SybaseASABaseDatabase);
		
		SybaseASABaseDatabase database = (SybaseASABaseDatabase)db;
		
		if(database.isBaseOnASA10())
		{
			return QUERY_TABLE_INFO_ASA10;
		}
		else
		{
			return QUERY_TABLE_INFO;
		}
	}
	
	static public String getQueryAllTableInfo(Database db)
	{
	    assert(db instanceof SybaseASABaseDatabase);
	    
	    SybaseASABaseDatabase database = (SybaseASABaseDatabase)db;
	    
	    if(database.isBaseOnASA10())
	    {
	        return QUERY_ALL_TABLE_INFO_ASA10;
	    }
	    else
	    {
	        return QUERY_ALL_TABLE_INFO;
	    }
	}
	
	static public String getQueryRoutines(Database db)
	{
		assert(db instanceof SybaseASABaseDatabase);
		
		SybaseASABaseDatabase database = (SybaseASABaseDatabase)db;
		
		if(database.isBaseOnASA10())
		{
			return QUERY_ROUTINES_ASA10;
		}
		else
		{
			return QUERY_ROUTINES;
		}
	}
	
	static public String getQueryRoutineInfo(Database db)
	{
		assert(db instanceof SybaseASABaseDatabase);
		
		SybaseASABaseDatabase database = (SybaseASABaseDatabase)db;
		
		if(database.isBaseOnASA10())
		{
			return QUERY_ROUTINE_INFO_ASA10;
		}
		else
		{
			return QUERY_ROUTINE_INFO;
		}
	}
}
