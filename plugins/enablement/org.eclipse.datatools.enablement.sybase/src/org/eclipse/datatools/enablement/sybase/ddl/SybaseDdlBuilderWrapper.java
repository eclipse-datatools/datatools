/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ddl;

import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SybaseDdlBuilderWrapper extends SybaseDdlBuilder
{

    private static SybaseDdlBuilder builder;
    
    protected SybaseDdlBuilderWrapper()
    {
    }
    
    public static SybaseDdlBuilder getInstance()
    {
        if(builder == null)
        {
            builder = new SybaseDdlBuilderWrapper();
        }
        return builder;
    }
    
    public String[] addForeignKey(ForeignKey foreignKey, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public String[] addUniqueConstraint(UniqueConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String[] createAuthorizationId(AuthorizationIdentifier authId, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String[] createCatalogs(Catalog catalog, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String[] createColumn(Column col, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String[] createDatabase(Database database, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String[] createEvent(Event event, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    protected String[] createPersistentTable(PersistentTable table, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String[] createProcedure(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    protected String[] createTempoeryTable(TemporaryTable table, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String[] createTrigger(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String[] createUserDefinedFunction(UserDefinedFunction function, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String[] createUserDefinedType(UserDefinedType udt, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String[] createView(ViewTable table, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String dropAuthorizationId(AuthorizationIdentifier authId, boolean quoteIdentifiers, boolean qualifyNames)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String dropCheckConstraint(CheckConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String dropForeignKey(ForeignKey foreignKey, boolean quoteIdentifiers, boolean qualifyNames)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String dropUniqueConstraint(UniqueConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    public String dropUserDefinedType(UserDefinedType udt, boolean quoteIdentifiers, boolean qualifyNames)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
