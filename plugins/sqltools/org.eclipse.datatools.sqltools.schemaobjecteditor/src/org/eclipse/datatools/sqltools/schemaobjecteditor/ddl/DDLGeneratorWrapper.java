/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ddl;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.DeltaDDLGenerator;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;

public class DDLGeneratorWrapper implements IDDLGeneratorWrapper
{
    protected DDLGenerator       _ddlGen;
    protected DeltaDDLGenerator  _deltaDdlGen;

    protected DatabaseIdentifier _databaseIdentifier;
    protected DatabaseDefinition _databaseDefn;
    protected boolean            _fullQualified;
    protected boolean            _quotedIdentifier;
    protected boolean            _setUser;

    public DDLGeneratorWrapper(DatabaseIdentifier identifier)
    {
        super();
        _databaseIdentifier = identifier;
        _databaseDefn = ProfileUtil.getDatabaseDefinition(_databaseIdentifier.getProfileName());
        _ddlGen = _databaseDefn.getDDLGenerator();
        _deltaDdlGen = _databaseDefn.getDeltaDDLGenerator();
        _quotedIdentifier = SQLDevToolsUtil.isQuotedIdentifierOn(_databaseIdentifier);
        _fullQualified = true;
    }

    public String[] getCreateStatementsDDL(SQLObject[] objs)
    {
        if (_ddlGen == null)
        {
            return new String[] {};
        }

        String ddl[];
        ddl = _ddlGen.createSQLObjects(objs, _quotedIdentifier, _fullQualified, null);
        return ddl == null ? new String[] {} : ddl;
    }

    public String[] generateDeltaDDL(EObject rootObject, ChangeDescription changeDescription, IProgressMonitor monitor)
    {
        if (_deltaDdlGen == null)
        {
            return new String[] {};
        }
        return _deltaDdlGen.generateDeltaDDL(rootObject, changeDescription, monitor);
    }

    public void setFullQualified(boolean qualified)
    {
        _fullQualified = qualified;
    }

    public void setGenSetUser(boolean setUser)
    {
        _setUser = setUser;
    }

}
