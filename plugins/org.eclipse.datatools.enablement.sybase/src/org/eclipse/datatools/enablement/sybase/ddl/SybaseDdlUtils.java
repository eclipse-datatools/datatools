/**
 * Created on 2007-1-22
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.enablement.sybase.ddl;

import org.eclipse.datatools.enablement.sybase.IGenericDdlConstants;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;

public class SybaseDdlUtils implements IGenericDdlConstants, ISybaseDdlConstants
{

    /**
     * Get SQL object name
     * 
     * @param obj
     * @param quoteIdentifiers quoted identifier option
     * @param qualifyNames qualify
     * @return
     */
    public static String getSQLObjectName(SQLObject obj, boolean quoteIdentifiers, boolean qualifyNames)
    {
        if (obj == null)
        {
            return "";
        }
        if (obj instanceof Table)
        {
            return getSQLObjectName((Table) obj, quoteIdentifiers, qualifyNames);
        }
        else if (obj instanceof Routine)
        {
            return getSQLObjectName((Routine) obj, quoteIdentifiers, qualifyNames);
        }
        else if (obj instanceof SQLObject)
        {
            if (quoteIdentifiers)
            {
                return SQLUtil.quote(((SQLObject) obj).getName(), DOUBLE_QUOTE);
            }
            else
            {
                return ((SQLObject) obj).getName();
            }
        }
        return "";
    }
}
