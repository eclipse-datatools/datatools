/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.services;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ASAContextType;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.ASARoutineSourceUpdater;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.ASATriggerSourceUpdater;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.ASAIdentifierValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.ASASQLSyntax;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.services.SQLService;
import org.eclipse.datatools.sqltools.db.generic.parser.GenericSQLParser;
import org.eclipse.datatools.sqltools.editor.template.GenericSQLContextType;
import org.eclipse.datatools.sqltools.sql.ISQLSyntax;
import org.eclipse.datatools.sqltools.sql.identifier.IIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.updater.ProceduralObjectSourceUpdater;


/**
 * @author hcao
 *
 */
public class ASASQLService extends SQLService
{
    public GenericSQLContextType getSQLContextType()
    {
        return new ASAContextType();
    }

    public SQLParser getSQLParser()
    {
        return GenericSQLParser.getInstance();
    }

    public ISQLSyntax getSQLSyntax()
    {
        return new ASASQLSyntax();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.datatools.sqltools.core.services.SQLService#getIdentifierValidator()
     */
    public IIdentifierValidator getIdentifierValidator()
    {
        return ASAIdentifierValidator.getInstance();
    }

    public ProceduralObjectSourceUpdater getProceduralObjectSourceUpdater(SQLObject object, DatabaseDefinition dbDefinition)
    {
        if (object instanceof SybaseRoutine)
        {
            return new ASARoutineSourceUpdater((SybaseRoutine) object, dbDefinition);
        }
        if (object instanceof Trigger)
        {
            return new ASATriggerSourceUpdater((Trigger) object, dbDefinition);
        }
        return null;
    }
}
