/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.core.services;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.editor.template.GenericSQLContextType;
import org.eclipse.datatools.sqltools.sql.updater.ProceduralObjectSourceUpdater;

/**
 * A SQL language related UI service specific to a database definition.
 * @author linsong
 *
 */
public class SQLUIService 
{
    
    /**
     * Returns the ProceduralObjectSourceUpdater object used to update the source of the given sql object 
     * @return
     */
    public ProceduralObjectSourceUpdater getProceduralObjectSourceUpdater(SQLObject object, DatabaseDefinition dbDefinition)
    {
        return null;
    }
    
    /**
     * Returns a specific <code>GenericSQLContextType</code> object which identifies the context type of templates
     * used in SQL editor.
     * 
     * @return a <code>GenericSQLContextType</code> object
     */
    public GenericSQLContextType getSQLContextType()
    {
        // TODO Auto-generated method stub
    	   return new GenericSQLContextType();
    }
}
