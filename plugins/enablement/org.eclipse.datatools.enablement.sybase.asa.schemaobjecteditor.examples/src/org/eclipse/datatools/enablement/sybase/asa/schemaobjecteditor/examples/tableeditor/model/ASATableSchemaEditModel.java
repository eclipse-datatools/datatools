/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ASADDLGeneratorWrapper;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.CustomizedCopier;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesConstants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.Constants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.PrivilegesUtil;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaFactory;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ddl.IDDLGeneratorWrapper;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ddl.IDDLProvider;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.AbstractSchemaObjectEditModel;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.ChangeFactory;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

/**
 * Edit model for ASA table schema editor
 * 
 * @author Idull
 */
public class ASATableSchemaEditModel extends AbstractSchemaObjectEditModel
{
    ChangeDescription[] _authIDsChangeDescription;
    ChangeDescription[] _indexesChangeDescription;
    ChangeRecorder[]    _authIDsChangeRecoreder;
    ChangeRecorder[]    _indexesChangeRecoreder;

    public ASATableSchemaEditModel(ASATableSchemaImmutableModel model, DatabaseIdentifier databaseIdentifier)
    {
        super(model, databaseIdentifier);
        _dGeneratorWrapper = new ASADDLGeneratorWrapper(_dIdentifier);
    }

    protected Copier createCopier()
    {
        return new CustomizedCopier();
    }

    public Object getAdapter(Class adapter)
    {
        if (adapter.equals(IDDLProvider.class))
        {
            return new IDDLProvider()
            {

                public String getDDL()
                {
                    IDDLGeneratorWrapper wrapper = new ASADDLGeneratorWrapper(_dIdentifier);
                    String ddl = "";
                    if (wrapper != null)
                    {
                        List objTobeGenDDL = new ArrayList();
                        objTobeGenDDL.add(_mainObject);
                        Iterator iter = ((BaseTable) _mainObject).getConstraints().iterator();
                        while (iter.hasNext())
                        {
                            objTobeGenDDL.add(iter.next());
                        }

                        iter = ((List) getAdditionalSQLObjects().get(PrivilegesConstants.AUTH_ID_ITEMS)).iterator();
                        while (iter.hasNext())
                        {
                            AuthorizationIdentifier authid = (AuthorizationIdentifier) iter.next();
                            List privileges = PrivilegesUtil.getReceivedSQLObjectPrivileges(authid, _mainObject);
                            Iterator piter = privileges.iterator();
                            while (piter.hasNext())
                            {
                                Privilege p = (Privilege) (piter.next());
                                if (p != null)
                                {
                                    objTobeGenDDL.add(p);
                                }
                            }
                        }

                        Iterator citer = ((Table) _mainObject).getColumns().iterator();
                        while (citer.hasNext())
                        {
                            Column col = (Column) (citer.next());
                            iter = ((List) getAdditionalSQLObjects().get(PrivilegesConstants.AUTH_ID_ITEMS)).iterator();
                            while (iter.hasNext())
                            {
                                AuthorizationIdentifier authid = (AuthorizationIdentifier) iter.next();
                                List privileges = PrivilegesUtil.getReceivedSQLObjectPrivileges(authid, col);
                                Iterator piter = privileges.iterator();
                                while (piter.hasNext())
                                {
                                    Privilege p = (Privilege) (piter.next());
                                    if (p != null)
                                    {
                                        objTobeGenDDL.add(p);
                                    }
                                }
                            }
                        }

                        String[] ddls = wrapper.getCreateStatementsDDL((SQLObject[]) objTobeGenDDL
                                .toArray(new SQLObject[objTobeGenDDL.size()]));

                        if (ddls == null)
                        {
                            return "";
                        }
                        StringBuffer sb = new StringBuffer("");
                        for (int i = 0; i < ddls.length; i++)
                        {
                            sb.append(ddls[i]);
                            sb.append(System.getProperty("line.separator"));
                            sb.append(getSQLDelimiter());
                            sb.append(System.getProperty("line.separator"));
                        }
                        ddl = sb.toString();
                    }
                    return ddl;
                }
            };

        }
        return null;
    }

    protected void createAndAttachNecessaryTempObjects()
    {
        // Only create the necessary information
        BaseTable table = (BaseTable) _immutableModel.getMainSQLObject();
        Database db = table.getSchema().getDatabase();

        // Create the tmp db
        SybaseASABaseDatabase database = (SybaseASABaseDatabase) EcoreUtil.create(db.eClass());
        database.setName(table.getSchema().getDatabase().getName());
        database.setVendor(table.getSchema().getDatabase().getVendor());
        database.setVersion(table.getSchema().getDatabase().getVersion());

        Schema belongedSchema = SQLSchemaFactory.eINSTANCE.createSchema();
        belongedSchema.setName(table.getSchema().getName());

        // Create tmp schemas
        Iterator sIter = db.getSchemas().iterator();
        while (sIter.hasNext())
        {
            Schema s = (Schema) sIter.next();

            Schema tmpSchema = SQLSchemaFactory.eINSTANCE.createSchema();
            tmpSchema.setName(s.getName());
            tmpSchema.setDatabase(database);
        }

        ((BaseTable) _mainObject).setSchema(belongedSchema);

        belongedSchema.setDatabase(database);
        Catalog catalog = SQLSchemaFactory.eINSTANCE.createCatalog();
        catalog.setName(database.getName());
        belongedSchema.setCatalog(catalog);
        catalog.setDatabase(database);

        // link the privileges for the table
        List privileges = ((SybaseAuthorizedObject) _mainObject).getPrivileges();

        for (Iterator it = privileges.iterator(); it.hasNext();)
        {
            SybasePrivilege p = (SybasePrivilege) it.next();
            AuthorizationIdentifier grantee = p.getGrantee();
            grantee.getReceivedPrivilege().add(p);
        }
        // link the privileges for the columns of the table
        Iterator citer = ((Table) _mainObject).getColumns().iterator();
        while (citer.hasNext())
        {
            Column col = (Column) citer.next();
            List colPrivileges = ((SybaseAuthorizedObject) col).getPrivileges();
            for (Iterator it = colPrivileges.iterator(); it.hasNext();)
            {
                SybasePrivilege p = (SybasePrivilege) it.next();
                AuthorizationIdentifier grantee = p.getGrantee();
                grantee.getReceivedPrivilege().add(p);
            }

        }

        // For tables referenced by FK
        Iterator iter = _immutableModel.getAdditionalSQLObjects().values().iterator();
        while (iter.hasNext())
        {
            Object obj = iter.next();
            if (obj instanceof BaseTable)
            {
                BaseTable originalTable = (BaseTable) obj;
                BaseTable copiedTable = (BaseTable) _copier.get(obj);
                if (copiedTable == _mainObject)
                {
                    continue;
                }

                Schema schema = SQLSchemaFactory.eINSTANCE.createSchema();
                schema.setDatabase(database);
                schema.setName(originalTable.getSchema().getName());
                copiedTable.setSchema(schema);
            }
        }

        ((BaseTable) _mainObject).getIndex().addAll((Collection) _additionalObjects.get(Constants.INDEXES));
    }

    protected String getSQLDelimiter()
    {
        return ";";
    }

    public String getDeltaDDL()
    {
        StringBuffer sb = new StringBuffer("");

        sb.append(getIndexDeltaDDL());

        _changeRecorder.summarize();

        sb.append(super.formatDeltaDDL(_dGeneratorWrapper.generateDeltaDDL(_mainObject, _changeDescription, null)));

        sb.append(getPrivilegeDeltaDDL());

        return sb.toString();
    }

    protected String getDeltaDDL(Object[] eObjects, ChangeDescription[] changeDescription)
    {
        if (eObjects.length != changeDescription.length)
        {
            return null;
        }

        String[] deltaDDL =
        {};

        if (_dGeneratorWrapper == null)
        {
            _dGeneratorWrapper = new ASADDLGeneratorWrapper(_dIdentifier);
        }
        String currentUser = ProfileUtil.getProfileUserName(_dIdentifier, false);

        StringBuffer sb = new StringBuffer("");
        if (changeDescription != null)
        {
            for (int index = 0; index < changeDescription.length; index++)
            {
                if (eObjects[index] instanceof EObject)
                {
                    String targetUser = ModelUtil.getSchemaName((EObject) eObjects[index]);
                    _dGeneratorWrapper.setGenSetUser(currentUser != null && !currentUser.equals(targetUser));
                    deltaDDL = _dGeneratorWrapper.generateDeltaDDL((EObject) eObjects[index], changeDescription[index],
                            null);

                    sb.append(super.formatDeltaDDL(deltaDDL));
                }
            }
        }
        return sb.toString();
    }

    private String getPrivilegeDeltaDDL()
    {
        Collection authIds = (Collection) _additionalObjects.get(PrivilegesConstants.AUTH_ID_ITEMS);
        if (authIds == null)
        {
            return "";
        }

        Object[] authIdObjects = authIds.toArray();

        for (int i = 0; _authIDsChangeRecoreder != null && i < _authIDsChangeRecoreder.length; i++)
        {
            _authIDsChangeRecoreder[i].summarize();
        }

        return getDeltaDDL(authIdObjects, _authIDsChangeDescription);
    }

    private String getIndexDeltaDDL()
    {
        Collection indexes = (Collection) _additionalObjects.get(Constants.INDEXES);
        if (indexes == null)
        {
            return "";
        }

        Object[] indexObjects = indexes.toArray();

        for (int i = 0; _indexesChangeRecoreder != null && i < _indexesChangeRecoreder.length; i++)
        {
            _indexesChangeRecoreder[i].summarize();
        }

        return getDeltaDDL(indexObjects, _indexesChangeDescription);
    }

    public void startLogging()
    {
        super.startLogging();

        Collection authIds = (Collection) _additionalObjects.get(PrivilegesConstants.AUTH_ID_ITEMS);
        if (authIds != null)
        {
            _authIDsChangeDescription = new ChangeDescription[authIds.size()];
            _authIDsChangeRecoreder = new ChangeRecorder[authIds.size()];
            Object[] authIdObjects = authIds.toArray();

            for (int i = 0; i < authIds.size(); i++)
            {
                _resource.getContents().add((EObject) authIdObjects[i]);
                _authIDsChangeDescription[i] = ChangeFactory.eINSTANCE.createChangeDescription();
                _authIDsChangeRecoreder[i] = new ChangeRecorder();
                _authIDsChangeRecoreder[i].beginRecording(_authIDsChangeDescription[i], Collections
                        .singletonList(authIdObjects[i]));
            }
        }

        Collection indexes = (Collection) _additionalObjects.get(Constants.INDEXES);
        if (indexes != null)
        {
            _indexesChangeDescription = new ChangeDescription[indexes.size()];
            _indexesChangeRecoreder = new ChangeRecorder[indexes.size()];
            Object[] indexObjects = indexes.toArray();
            for (int i = 0; i < indexes.size(); i++)
            {
                _resource.getContents().add((EObject) indexObjects[i]);
                _indexesChangeDescription[i] = ChangeFactory.eINSTANCE.createChangeDescription();
                _indexesChangeRecoreder[i] = new ChangeRecorder();
                _indexesChangeRecoreder[i].beginRecording(_indexesChangeDescription[i], Collections
                        .singletonList(indexObjects[i]));
            }
        }

    }

    public void stopLogging()
    {
        super.stopLogging();

        for (int i = 0; _authIDsChangeDescription != null && i < _authIDsChangeDescription.length; i++)
        {
            _authIDsChangeRecoreder[i].endRecording();
        }

        for (int i = 0; _indexesChangeDescription != null && i < _indexesChangeDescription.length; i++)
        {
            _indexesChangeRecoreder[i].endRecording();
        }
    }

    protected SybaseDdlScript getDeltaDDLScript(EObject rootObject, ChangeDescription _changeSummary)
    {
        if (_dGeneratorWrapper instanceof ASADDLGeneratorWrapper)
        {
            return ((ASADDLGeneratorWrapper) _dGeneratorWrapper).getDeltaDDLScript(rootObject, _changeSummary, null,
                    true);
        }
        return null;
    }
}
