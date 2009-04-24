/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesConstants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SQLUtil;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.AbstractSchemaObjectImmutableModel;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.common.util.EList;


/**
 * 
 * @author Hui Cao
 * 
 */
public class ProceduralObjectImmutableModel extends AbstractSchemaObjectImmutableModel
{

    public ProceduralObjectImmutableModel(SQLObject object, Map ojbects)
    {
        super(object, ojbects);
        if (_mainObject instanceof Routine)
        {
            Collection authIds = (Collection) _additionalObjects.get(PrivilegesConstants.AUTH_ID_ITEMS);
            if (authIds == null || authIds.isEmpty())
            {
                authIds = ModelUtil.getAuthorizationIdentifiers(_mainObject);
                _additionalObjects.put(PrivilegesConstants.AUTH_ID_ITEMS, authIds);
            }
        }
    }

    private void refreshAdditionalObjects()
    {
        for (Iterator iter = _additionalObjects.values().iterator(); iter.hasNext();)
        {
            Object element = (Object) iter.next();
            // no need to refresh privilege infos
            if (_additionalObjects.get(PrivilegesConstants.AUTH_ID_ITEMS).equals(element))
            {
                break;
            }
            if (element instanceof ICatalogObject)
            {
                ((ICatalogObject) element).refresh();
            }
            else if (element instanceof Collection)
            {
                for (Iterator i = ((Collection) element).iterator(); i.hasNext();)
                {
                    Object e = (Object) i.next();
                    if (e instanceof ICatalogObject)
                    {
                        ((ICatalogObject) e).refresh();
                    }
                }
            }
        }
    }

    public void refreshFromDB(String newName)
    {
        if (!isModelExist())
        {
            _mainObject = null;
            return;
        }
        
        if (SQLUtil.unquote(_mainObject.getName()).equals(SQLUtil.unquote(newName))
                && !(_mainObject instanceof Trigger))
        {
            if (_mainObject instanceof ICatalogObject)
            {
                ((ICatalogObject) _mainObject).refresh();
                refreshAdditionalObjects();
                return;
            }
        }
        boolean isFound = false;
        if (_mainObject instanceof Routine)
        {
            Schema schema = ((Routine) _mainObject).getSchema();
            if (schema != null)
            {
//                DSEUtil.refreshObjectBySchema(schema, _mainObject);

                if (_mainObject instanceof Procedure)
                {
                    EList procs = schema.getProcedures();
                    for (Iterator iter = procs.iterator(); iter.hasNext();)
                    {
                        Procedure proc = (Procedure) iter.next();
                        if (proc.getName().equals(_mainObject.getName()))
                        {
                            _mainObject = proc;
                            isFound = true;
                            break;
                        }
                    }
                    if (!isFound)
                    {
                        for (Iterator iter = procs.iterator(); iter.hasNext();)
                        {
                            Procedure proc = (Procedure) iter.next();
                            if (SQLUtil.unquote(proc.getName()).equals(SQLUtil.unquote(newName)))
                            {
                                _mainObject = proc;
                                isFound = true;
                                break;
                            }
                        }
                    }
                }
                else if (_mainObject instanceof UserDefinedFunction)
                {
                    EList procs = schema.getUDFs();
                    for (Iterator iter = procs.iterator(); iter.hasNext();)
                    {
                        UserDefinedFunction proc = (UserDefinedFunction) iter.next();
                        if (proc.getName().equals(_mainObject.getName()))
                        {
                            _mainObject = proc;
                            isFound = true;
                            break;
                        }
                    }
                    if (!isFound)
                    {
                        for (Iterator iter = procs.iterator(); iter.hasNext();)
                        {
                            UserDefinedFunction proc = (UserDefinedFunction) iter.next();
                            if (SQLUtil.unquote(proc.getName()).equals(SQLUtil.unquote(newName)))
                            {
                                _mainObject = proc;
                                isFound = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if (_mainObject instanceof Trigger)
        {
            Table table = ((Trigger) _mainObject).getSubjectTable();

            if (table != null)
            {

                if (table instanceof ICatalogObject)
                {
                    ((ICatalogObject) table).refresh();
                }
                EList triggers = table.getTriggers();
                for (Iterator iter = triggers.iterator(); iter.hasNext();)
                {
                    Trigger trig = (Trigger) iter.next();
                    if (trig.getName().equals(_mainObject.getName()))
                    {
                        _mainObject = trig;
                        isFound = true;
                        break;
                    }
                }

                if (!isFound)
                {
                    for (Iterator iter = triggers.iterator(); iter.hasNext();)
                    {
                        Trigger trig = (Trigger) iter.next();
                        if (SQLUtil.unquote(trig.getName()).equals(SQLUtil.unquote(newName)))
                        {
                            _mainObject = trig;
                            isFound = true;
                            break;
                        }
                    }
                }
            }
        }
        else if (_mainObject instanceof Event)
        {
            Database db = ((Event) _mainObject).getDatabase();

            if (db != null)
            {
                if (db instanceof ICatalogObject)
                {
                    ((ICatalogObject) db).refresh();
                }
                EList events = db.getEvents();
                for (Iterator iter = events.iterator(); iter.hasNext();)
                {
                    Event event = (Event) iter.next();
                    if (event.getName().equals(_mainObject.getName()))
                    {
                        _mainObject = event;
                        isFound = true;
                        break;
                    }
                }
                if (!isFound)
                {
                    for (Iterator iter = events.iterator(); iter.hasNext();)
                    {
                        Event event = (Event) iter.next();
                        if (SQLUtil.unquote(event.getName()).equals(SQLUtil.unquote(newName)))
                        {
                            _mainObject = event;
                            isFound = true;
                            break;
                        }
                    }
                }
            }
        }
        if (isFound)
        {
            refreshAdditionalObjects();
        }
        else
        {
            _mainObject = null;
        }
    }

    public boolean isModelExist()
    {
    	boolean isModelExist = false;
    	boolean isSchemaExist = (ModelUtil.getSchema(_mainObject) != null);

    	if (isSchemaExist) 
    	{
	        if (_mainObject instanceof Routine)
	        {
	            Schema schema = ((Routine) _mainObject).getSchema();
	            if (schema != null)
	            {
	            	isModelExist = true;
	            }
	        }
	        else if (_mainObject instanceof Trigger)
	        {
	            Table table = ((Trigger) _mainObject).getSubjectTable();
	            if (table != null)
	            {
	                isModelExist =  true;
	            }
	        }
    	}
    	else if (_mainObject instanceof Event)
        {
            Database database = ((Event) _mainObject).getDatabase();
            if (database != null)
            {
                isModelExist = true;
            }
        }
    	
        return isModelExist;
    }

}
