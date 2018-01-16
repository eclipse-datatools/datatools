/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.udteditor.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaFactoryImpl;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ddl.DDLGeneratorWrapper;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ddl.IDDLGeneratorWrapper;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ddl.IDDLProvider;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.AbstractSchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectImmutableModel;

/**
 * @author renj
 */
public class ASAUserDefinedTypeObjectEditModel extends AbstractSchemaObjectEditModel
{
    protected static final String LINE       = System.getProperty("line.separator");

    private String                _delimiter = ";";

    public ASAUserDefinedTypeObjectEditModel(ISchemaObjectImmutableModel model, DatabaseIdentifier databaseIdentifier)
    {
        super(model, databaseIdentifier);
    }

    protected void createAndAttachNecessaryTempObjects()
    {
        SQLObject mainObject = getSchemaObjectImmutableModel().getMainSQLObject();
        if (mainObject instanceof SybaseASABaseUserDefinedType)
        {
            Schema schema = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
            Schema oldSchema = ((SybaseASABaseUserDefinedType) mainObject).getSchema();
            schema.setName(oldSchema.getName());
            ((SybaseASABaseUserDefinedType) getCopier().get(mainObject)).setSchema(schema);

            Catalog catalog = SQLSchemaFactoryImpl.eINSTANCE.createCatalog();
            Catalog oldCatalog = oldSchema.getCatalog();
            catalog.setName(oldCatalog.getName());
            schema.setCatalog(catalog);

            Database db = SQLSchemaFactoryImpl.eINSTANCE.createDatabase();
            Database oldDb = oldCatalog.getDatabase();
            db.setName(oldDb.getName());
            db.setVendor(oldDb.getVendor());
            db.setVersion(oldDb.getVersion());
            catalog.setDatabase(db);
        }
    }

    protected String getSQLDelimiter()
    {
        return _delimiter;
    }

    public Object getAdapter(Class adapter)
    {
        if (adapter.equals(IDDLProvider.class))
        {
            return new IDDLProvider()
            {
                public String getDDL()
                {
                    IDDLGeneratorWrapper wrapper = new DDLGeneratorWrapper(_dIdentifier);
                    String[] ddl = null;
                    if (wrapper != null)
                    {
                        List objects = new ArrayList();
                        objects.add(_mainObject);

                        ddl = wrapper.getCreateStatementsDDL((SQLObject[]) objects
                                .toArray(new SQLObject[objects.size()]));
                        if (ddl != null)
                        {
                            StringBuffer sb = new StringBuffer();
                            for (int i = 0; i < ddl.length; i++)
                            {
                                sb.append(ddl[i]).append(LINE).append(getSQLDelimiter()).append(LINE);
                            }
                            return sb.toString();
                        }
                    }
                    return "";
                }
            };
        }
        return null;
    }
    
    public String getEditorTooltipText()
    {
        StringBuffer sb = new StringBuffer(128);
        SQLObject mainObject = getSchemaObjectImmutableModel().getMainSQLObject();
        if (mainObject instanceof SybaseASABaseUserDefinedType)
        {
            Schema schema = ((SybaseASABaseUserDefinedType) mainObject).getSchema();

            Catalog catalog = schema.getCatalog();

            Database db = catalog.getDatabase();
            ConnectionInfo connInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(db);
            if (connInfo instanceof ConnectionInfoImpl)
            {
                IConnectionProfile cp = ((ConnectionInfoImpl) connInfo).getConnectionProfile();
                sb.append("(").append(cp.getName()).append(")");
            }
            
            sb.append(catalog.getName()).append(".").append(schema.getName()).append(".").append(mainObject.getName());
        }

        return sb.toString();
    }
}
