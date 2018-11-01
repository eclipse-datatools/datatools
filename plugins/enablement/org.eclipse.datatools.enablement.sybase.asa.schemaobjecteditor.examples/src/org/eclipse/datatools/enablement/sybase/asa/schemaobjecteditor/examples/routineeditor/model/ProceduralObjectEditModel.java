/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ASADDLGeneratorWrapper;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.CustomizedCopier;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesConstants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.PrivilegesUtil;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelFactory;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaFactoryImpl;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.debugger.model.SPDebugModelUtil;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.routineeditor.launching.LaunchHelper;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ddl.DDLGeneratorWrapper;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ddl.IDDLGeneratorWrapper;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ddl.IDDLProvider;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.AbstractSchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectImmutableModel;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.ChangeFactory;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

/**
 * TODO use a factory to create the edit model
 * 
 * @author Hui Cao
 * 
 */
public class ProceduralObjectEditModel extends AbstractSchemaObjectEditModel
{
    protected static final String LINE               = System.getProperty("line.separator");
    protected ChangeDescription[] _privilegeChangeSummary;
    private String                _terminator        = ";";
    private ProcIdentifier        _procIdentifier;
    // whether to append privilege statements
    private boolean               _reservePrivileges = false;

    ChangeDescription[]           _authIDsChangeDescription;
    ChangeRecorder[]              _authIDsChangeRecoreder;

    public ProceduralObjectEditModel(ISchemaObjectImmutableModel model, DatabaseIdentifier databaseIdentifier)
    {
        super(model, databaseIdentifier);
        _procIdentifier = SQLDevToolsUtil.getProcIdentifier(databaseIdentifier, getMainSQLObject());
        _dGeneratorWrapper = new ASADDLGeneratorWrapper(_dIdentifier);
    }

    protected Copier createCopier()
    {
        return new CustomizedCopier();
    }

    protected void createAndAttachNecessaryTempObjects()
    {
        SQLObject mainObject = getSchemaObjectImmutableModel().getMainSQLObject();
        if (mainObject instanceof SybaseRoutine)
        {
            Schema schema = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
            Schema oldSchema = ((Routine) mainObject).getSchema();
            schema.setName(oldSchema.getName());
            ((Routine) getCopier().get(mainObject)).setSchema(schema);
            // TODO check ASA & IQ catalog support
            Catalog catalog = SQLSchemaFactoryImpl.eINSTANCE.createCatalog();
            Catalog oldCatalog = oldSchema.getCatalog();

            Database db = SQLSchemaFactoryImpl.eINSTANCE.createDatabase();
            Database oldDb = oldCatalog.getDatabase();

            catalog.setName(oldCatalog.getName());
            schema.setCatalog(catalog);

            db.setName(oldDb.getName());
            db.setVendor(oldDb.getVendor());
            db.setVersion(oldDb.getVersion());
            catalog.setDatabase(db);

            // after clone, we have one way reference from privilege to auth; now we need to add the other way reference
            List privileges = ((SybaseAuthorizedObject) _mainObject).getPrivileges();
            // grantee.getReceivedPrivilege() is containment feature, while SybaseAuthorizedObject.getPrivileges is not
            // so here we won't get concurrent modification error
            for (Iterator it = privileges.iterator(); it.hasNext();)
            {
                SybasePrivilege p = (SybasePrivilege) it.next();
                AuthorizationIdentifier grantee = p.getGrantee();
                grantee.getReceivedPrivilege().add(p);
            }

        }
        else if (mainObject instanceof Trigger)
        {
            Schema schema = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
            Schema oldSchema = ((Trigger) mainObject).getSchema();
            schema.setName(oldSchema.getName());
            ((Trigger) getCopier().get(mainObject)).setSchema(schema);
            // TODO check ASA & IQ catalog support
            Catalog catalog = SQLSchemaFactoryImpl.eINSTANCE.createCatalog();
            Catalog oldCatalog = oldSchema.getCatalog();

            Database db = SQLSchemaFactoryImpl.eINSTANCE.createDatabase();
            Database oldDb = oldCatalog.getDatabase();

            catalog.setName(oldCatalog.getName());
            schema.setCatalog(catalog);

            db.setName(oldDb.getName());
            db.setVendor(oldDb.getVendor());
            db.setVersion(oldDb.getVersion());
            catalog.setDatabase(db);

            Table oldTable = ((Trigger) mainObject).getSubjectTable();
            // create a view if mainObject is an instead of trigger, otherwise, create a table
            Table table = SybasesqlmodelFactory.eINSTANCE.createSybaseBaseTable();
            table.setName(oldTable.getName());
            // editor will get columns info from the immutable model
            Schema oldTableSchema = oldTable.getSchema();
            if (oldTableSchema != null && oldTableSchema != oldSchema)
            {
                Schema tableSchema = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
                tableSchema.setName(oldTableSchema.getName());
                tableSchema.setCatalog(catalog);
                table.setSchema(tableSchema);
            }
            else
            {
                table.setSchema(schema);
            }
            ((Trigger) _mainObject).setSubjectTable(table);

        }
        else if (mainObject instanceof SybaseASABaseEvent)
        {
            SybaseASABaseEvent event = (SybaseASABaseEvent) mainObject;
            Schema schema = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
            Schema oldSchema = event.getEventCreator();
            schema.setName(oldSchema.getName());
            ((SybaseASABaseEvent) getCopier().get(mainObject)).setEventCreator(schema);

            // TODO check ASA & IQ catalog support
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

            ((SybaseASABaseEvent) getCopier().get(mainObject)).setDatabase(db);

        }
    }

    protected String getSQLDelimiter()
    {
        return _terminator;
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
                    String[] ddl = null;
                    if (wrapper != null)
                    {
                        List objects = new ArrayList();
                        objects.add(_mainObject);

                        Collection authIds = (Collection) _additionalObjects.get(PrivilegesConstants.AUTH_ID_ITEMS);
                        if (authIds != null)
                        {
                            for (Iterator iterator = authIds.iterator(); iterator.hasNext();)
                            {
                                AuthorizationIdentifier authId = (AuthorizationIdentifier) iterator.next();
                                List privs = PrivilegesUtil.getReceivedSQLObjectPrivileges(authId, _mainObject);
                                objects.addAll(privs);
                            }
                        }
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
    }

    public void stopLogging()
    {
        super.stopLogging();

        for (int i = 0; _authIDsChangeDescription != null && i < _authIDsChangeDescription.length; i++)
        {
            _authIDsChangeDescription[i] = _authIDsChangeRecoreder[i].endRecording();
        }
    }

    public String getDeltaDDL()
    {
        String mainDDL = super.getDeltaDDL();
        StringBuffer sb = new StringBuffer(mainDDL);
        String mainDDL1 = mainDDL;
        if (mainDDL != null)
        {
            mainDDL1 = mainDDL.trim().toUpperCase();
        }
        if (mainDDL != null && mainDDL.length() > 0 && (mainDDL1.startsWith("DROP") || _reservePrivileges))
        {
            // if user changes the procedure(ASE: any modification/ IQ: rename), we must preserve all the privileges
            IDDLGeneratorWrapper wrapper = new DDLGeneratorWrapper(_dIdentifier);
            String[] ddl = null;
            if (wrapper != null)
            {
                List objects = new ArrayList();

                Collection authIds = (Collection) _additionalObjects.get(PrivilegesConstants.AUTH_ID_ITEMS);
                if (authIds != null)
                {
                    for (Iterator iterator = authIds.iterator(); iterator.hasNext();)
                    {
                        AuthorizationIdentifier authId = (AuthorizationIdentifier) iterator.next();
                        List privs = PrivilegesUtil.getReceivedSQLObjectPrivileges(authId, _mainObject);
                        objects.addAll(privs);
                    }
                }
                ddl = wrapper.getCreateStatementsDDL((SQLObject[]) objects.toArray(new SQLObject[objects.size()]));
                if (ddl != null)
                {
                    for (int i = 0; i < ddl.length; i++)
                    {
                        sb.append(ddl[i]).append(LINE).append(getSQLDelimiter()).append(LINE);
                    }
                }
            }
        }
        else
        {
            sb.append(getPrivilegeDeltaDDL());
        }
        return sb.toString();
    }

    protected String getDeltaDDL(Object[] eObjects, ChangeDescription[] changeDescription)
    {
        if (eObjects.length != changeDescription.length)
        {
            return null;
        }

        String[] deltaDDL;
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

                    if (deltaDDL != null)
                    {
                        sb.append(super.formatDeltaDDL(deltaDDL));
                    }
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

    public ProcIdentifier getProcIdentifier()
    {
        return _procIdentifier;
    }

    public int refreshFromDB()
    {
        ProcIdentifier oldProc = _procIdentifier;
        super.refreshFromDB();
        if (getMainSQLObject() == null)
        {
            return ISchemaObjectEditModel.FATAL_ERROR_MAIN_OBJ_LOST;
        }
        _procIdentifier = SQLDevToolsUtil.getProcIdentifier(_dIdentifier, getMainSQLObject());
        // if user only updates the name in editor but didn't save, we don't need to refactor
        if (!oldProc.equals(_procIdentifier))
        {
            SPDebugModelUtil.changeAllBreakpointForSP(oldProc, _procIdentifier);
            LaunchHelper.renameAllConfigurations(oldProc, _procIdentifier);
        }
        return REFRESH_SUCCESSFUL;
    }

    public boolean isReservePrivileges()
    {
        return _reservePrivileges;
    }

    public void setReservePrivileges(boolean reservePrivileges)
    {
        this._reservePrivileges = reservePrivileges;
    }

}
