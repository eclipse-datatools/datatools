/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ddl.DDLGeneratorWrapper;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ddl.IDDLGeneratorWrapper;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ddl.IDDLProvider;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.ChangeFactory;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

/**
 * The class is resoponsible for cloning SQL objects for edit purpose.<br>
 * 
 * @author Idull
 */
public abstract class AbstractSchemaObjectEditModel implements ISchemaObjectEditModel
{
    protected SQLObject                   _mainObject;
    protected Map                         _additionalObjects;
    protected Copier                      _copier;
    protected ISchemaObjectImmutableModel _immutableModel;

    protected ChangeDescription           _changeDescription;
    protected ChangeRecorder              _changeRecorder;
    protected DatabaseIdentifier          _dIdentifier;
    protected IDDLGeneratorWrapper        _dGeneratorWrapper;

    /**
     * to backup the DDL for the case that when the main SQLObject is lost.
     */
    protected String                      _backupedDDL;

    public AbstractSchemaObjectEditModel(ISchemaObjectImmutableModel model, DatabaseIdentifier databaseIdentifier)
    {
        super();
        _immutableModel = model;
        _dIdentifier = databaseIdentifier;
        _dGeneratorWrapper = new DDLGeneratorWrapper(_dIdentifier);
        _additionalObjects = new HashMap();
        generateEditModel();
    }

    public Map getAdditionalSQLObjects()
    {
        return _additionalObjects;
    }

    public Copier getCopier()
    {
        return _copier;
    }

    public SQLObject getMainSQLObject()
    {
        return _mainObject;
    }

    public ISchemaObjectImmutableModel getSchemaObjectImmutableModel()
    {
        return _immutableModel;
    }

    /**
     * After the model is refreshed, should re-clone these objects
     */
    public int refreshFromDB()
    {
        if (_immutableModel != null && _mainObject != null)
        {
            _immutableModel.refreshFromDB(_mainObject.getName());
        }
        if (_immutableModel.getMainSQLObject() == null)
        {
            /*
             * Should not happen, if happens, popup dialog, stop editing.
             */
            promptObjectLost();

            return FATAL_ERROR_MAIN_OBJ_LOST;
        }
        generateEditModel();
        return REFRESH_SUCCESSFUL;
    }

    public void revert()
    {
        generateEditModel();
    }

    public boolean checkModelExistence()
    {
        boolean isExist = _immutableModel.isModelExist();

        if (!isExist)
        {
            promptObjectLost();
        }

        return isExist;
    }

    private void promptObjectLost()
    {
        try
        {
            IDDLProvider provider = (IDDLProvider) getAdapter(IDDLProvider.class);
            if (provider != null)
            {
                _backupedDDL = provider.getDDL();
            }
        }
        catch (Exception e)
        {
            // if any exception occurs in the getDDL process, ignore it, set backup ddl to null
            _backupedDDL = null;
        }

        _mainObject = null;
    }

    protected void generateEditModel()
    {
        cloneSQLObjects();
        createAndAttachNecessaryTempObjects();

        // Automatically start logging after the model is successfully cloned
        startLogging();
    }

    protected void cloneSQLObjects()
    {
        _copier = createCopier();

        _copier.copy(_immutableModel.getMainSQLObject());

        Map additionalObjs = _immutableModel.getAdditionalSQLObjects();
        if (additionalObjs != null)
        {
            Iterator iter = additionalObjs.keySet().iterator();
            while (iter.hasNext())
            {
                Object key = iter.next();
                Object value = additionalObjs.get(key);
                if (value instanceof Collection)
                {
                    Collection col = (Collection) value;
                    if (col == null)
                    {
                        continue;
                    }

                    boolean itemsValid = true;
                    Iterator colIter = col.iterator();
                    while (colIter.hasNext())
                    {
                        if (!(colIter.next() instanceof EObject))
                        {
                            itemsValid = false;
                            continue;
                        }
                    }
                    if (!itemsValid)
                    {
                        continue;
                    }

                    Collection duplicates = _copier.copyAll(col);
                    _additionalObjects.put(key, duplicates);
                }
                else if (value instanceof EObject)
                {
                    EObject obj = _copier.copy((EObject) value);
                    _additionalObjects.put(key, obj);
                }
            }
        }

        _copier.copyReferences();
        _mainObject = (SQLObject) _copier.get(_immutableModel.getMainSQLObject());
    }

    protected Copier createCopier()
    {
        return new EcoreUtil.Copier();
    }

    /**
     * Since the cloned objects may not be enough for delta ddl generator to generate the delta ddl, some temporary SQL
     * objects need to be created and attached with the cloned objects.<br>
     * For example, if a <code>Table</code> object is cloned, should create a temporary <code>Schema</code> object and
     * attach them such that the delta ddl generator can work properly.
     * 
     */
    protected abstract void createAndAttachNecessaryTempObjects();

    protected Resource _resource;

    public void startLogging()
    {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());

        ResourceSet resourceSet = new ResourceSetImpl();
        _resource = resourceSet.createResource(URI.createURI("test.ecore"));
        _resource.getContents().add(_mainObject);

        _changeDescription = ChangeFactory.eINSTANCE.createChangeDescription();
        _changeRecorder = new ChangeRecorder();

        _changeRecorder.beginRecording(_changeDescription, Collections.singletonList(_mainObject));
    }

    public void stopLogging()
    {
        _changeRecorder.endRecording();
    }

    /**
     * Sub class should override this method to return the delta ddl
     */
    public String getDeltaDDL()
    {
        return getDeltaDDL(_mainObject, (ChangeDescription) _changeDescription);
    }

    protected String getDeltaDDL(EObject eObject, ChangeDescription _changeSummary)
    {
        String[] deltaDDL;
        if (_dGeneratorWrapper == null)
        {
            _dGeneratorWrapper = new DDLGeneratorWrapper(_dIdentifier);
        }

        String currentUser = ProfileUtil.getProfileUserName(_dIdentifier, false);
        String targetUser = ModelUtil.getSchemaName(eObject);
        _dGeneratorWrapper.setGenSetUser(currentUser != null && !currentUser.equals(targetUser));

        deltaDDL = _dGeneratorWrapper.generateDeltaDDL(eObject, _changeSummary, null);

        return formatDeltaDDL(deltaDDL);
    }

    protected String formatDeltaDDL(String[] deltaDDL)
    {
        if (deltaDDL != null)
        {
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < deltaDDL.length; i++)
            {
                // In case the delta ddl gen return empty statement
                if (deltaDDL[i] == null || deltaDDL[i].trim().length() == 0 || deltaDDL[i].equals("null"))
                {
                    continue;
                }
                sb.append(deltaDDL[i]);
                sb.append(System.getProperty("line.separator"));
                String delimiter = getSQLDelimiter();
                if (delimiter == null)
                {
                    delimiter = "";
                }
                sb.append(getSQLDelimiter());
                sb.append(System.getProperty("line.separator"));
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * Sub class should override this method to return the statement delimiter
     * 
     * @return
     */
    protected abstract String getSQLDelimiter();

    public String getEditorTooltipText()
    {
        StringBuffer sb = new StringBuffer("");
        sb.append("(").append(_dIdentifier.getProfileName()).append(")");

        // In case the model object is null
        if (_immutableModel == null || _immutableModel.getMainSQLObject() == null)
        {
            return "";
        }
        Object root = ContainmentServiceImpl.INSTANCE.getRootElement(_immutableModel.getMainSQLObject());
        if (root != null && root instanceof Database)
        {
            sb.append(ModelUtil.getDatabaseName(_immutableModel.getMainSQLObject())).append(".");

            String schemaName = new String();
            if (_mainObject instanceof Table)
            {
                schemaName = ((Table) _mainObject).getSchema().getName();
            }
            else if (_mainObject instanceof Routine)
            {
                schemaName = ((Routine) _mainObject).getSchema().getName();
            }
            sb.append(schemaName);
        }
        sb.append(_immutableModel.getMainSQLObject().getName());

        return sb.toString();
    }

    /**
     * if the Main SQLObject is lost, to call the method to get the backuped DDL.
     * 
     * @return
     */
    public String getBackupedDDL()
    {
        return _backupedDDL;
    }
}
