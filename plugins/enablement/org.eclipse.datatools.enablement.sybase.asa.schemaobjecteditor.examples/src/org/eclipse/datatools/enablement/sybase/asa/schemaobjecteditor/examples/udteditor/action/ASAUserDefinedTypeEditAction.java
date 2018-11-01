/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.udteditor.action;

import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.udteditor.model.ASAUserDefinedTypeObjectEditModel;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.udteditor.model.ASAUserDefinedTypeObjectImmutableModel;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.action.EditSchemaObjectAction;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IEditorPart;


/**
 * @author renj
 */
public class ASAUserDefinedTypeEditAction extends EditSchemaObjectAction
{
    protected SybaseASABaseUserDefinedType _udd;

    private static final String            EDIT                 = "&View";
    private static final String            ObjectTypeID         = "org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType";
    private static final String            VENDOR_ASA           = "Sybase_ASA";
    private static final String            VENDOR_ASIQ          = "Sybase_IQ";
    private static final String            VERSION              = "x";

    private static final String            MONITOR_OPENING      = "Opening user defined type editor...";
    private static final String            MONITOR_LONDING_INFO = "Reading objects from database";
    private static final String            MONITOR_INITIALIZING = "Initializing editor";

    public ASAUserDefinedTypeEditAction()
    {
        super();
        setText(EDIT);
    }
    
    public ASAUserDefinedTypeEditAction(SybaseASABaseUserDefinedType udd)
    {
        super();
        setSQLObject(udd);
        setText(EDIT);
    }

    private void init()
    {
        if (getSQLObject() != null && this.getSQLObject() instanceof SybaseASABaseUserDefinedType)
        {
            _udd = (SybaseASABaseUserDefinedType) getSQLObject();

            String uddVendor = _udd.getSchema().getCatalog().getDatabase().getVendor();
            if (uddVendor != null && uddVendor.indexOf("ASA") > 0)
            {
                setVendorName(VENDOR_ASA);
            }
            else
            {
                setVendorName(VENDOR_ASIQ);
            }

            setVersion(VERSION);
            setObjectTypeId(ObjectTypeID);
        }

    }

    public void run(IProgressMonitor monitor)
    {
        init();
        monitor.beginTask(MONITOR_OPENING, IProgressMonitor.UNKNOWN);
        monitor.subTask(MONITOR_LONDING_INFO);
        Database db = ModelUtil.getDatabase(_udd.getSchema());
        if (db != null)
        {
            ConnectionInfo connInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(db);
            if (connInfo instanceof ConnectionInfoImpl)
            {
                IConnectionProfile cp = ((ConnectionInfoImpl) connInfo).getConnectionProfile();
                DatabaseIdentifier databaseIdentifier = new DatabaseIdentifier(cp.getName(), ModelUtil
                        .getDatabaseName((EObject) _udd));

                setDatabaseIdentifier(databaseIdentifier);
            }
        }
        ASAUserDefinedTypeObjectImmutableModel imodel = new ASAUserDefinedTypeObjectImmutableModel(_udd, new HashMap());
        ASAUserDefinedTypeObjectEditModel emodel = new ASAUserDefinedTypeObjectEditModel(imodel, getDatabaseIdentifier());
        setModelObj(emodel);

        if (monitor.isCanceled())
        {
            return;
        }

        monitor.subTask(MONITOR_INITIALIZING);
        ExamplePlugin.getStandardDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                checkAndOpenEditor();
                ISchemaObjectEditModel obj = (ISchemaObjectEditModel) getModelObj();
                IEditorPart part = getPart();
                if (part != null)
                {
                    ((ISchemaObjectEditor) part).setEditorPartName(obj.getMainSQLObject().getName());
                }
                setActivePage();
            }
        });
        monitor.done();
    }

}