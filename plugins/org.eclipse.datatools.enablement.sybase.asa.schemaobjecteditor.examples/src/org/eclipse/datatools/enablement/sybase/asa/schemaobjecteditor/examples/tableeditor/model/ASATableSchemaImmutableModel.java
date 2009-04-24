/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.Constants;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.AbstractSchemaObjectImmutableModel;
import org.eclipse.emf.common.util.EList;


/**
 * Immutable model for ASA table schema editor
 * 
 * @author Idull
 */
public class ASATableSchemaImmutableModel extends AbstractSchemaObjectImmutableModel
{
    public ASATableSchemaImmutableModel(BaseTable object, Map ojbects)
    {
        super(object, ojbects);
    }

    public void refreshFromDB(final String newName)
    {
        // The schema might be lost
        if (!isModelExist())
        {
            _mainObject = null;
            return ;
        }
        
        BaseTable table = (BaseTable) getMainSQLObject();
        Schema schema = (Schema) table.getSchema();
        
        //If name was not changed, just refresh the mainObject itself.
        if(newName.equals(_mainObject.getName()))
        {
            if(_mainObject instanceof ICatalogObject)
            {
                ((ICatalogObject)_mainObject).refresh();
                refreshAdditionalObjects();
                return;
            }
        }
        
        //If name was changed, refresh the schema and find the SqlObject according to immutable model first.
        //If SQLObject is not found, search with the new name 
//        DSEUtil.refreshObjectBySchema(schema, _mainObject);

        boolean foundTable = false;
        EList tables = schema.getTables();
        if (tables != null)
        {
            Iterator iter = tables.iterator();
            while (iter.hasNext())
            {
                Object obj = iter.next();
                if (!(obj instanceof BaseTable))
                {
                    continue;
                }
                BaseTable t = (BaseTable) obj;
                if (t.getName().equals(_mainObject.getName()))
                {
                    _mainObject = t;
                    if (_mainObject instanceof ICatalogObject)
                    {
                        ICatalogObject cataObj = (ICatalogObject) _mainObject;
                        cataObj.refresh();
                    }
                    foundTable = true;
                    break;
                }
            }
        }
        if (!foundTable)
        {
            if(tables !=null)
            {
                Iterator iter = tables.iterator();
                while (iter.hasNext())
                {
                    Object obj = iter.next();
                    if (!(obj instanceof BaseTable))
                    {
                        continue;
                    }
                    BaseTable t = (BaseTable) obj;
                    if (t.getName().equals(newName))
                    {
                        _mainObject = t;
                        if (_mainObject instanceof ICatalogObject)
                        {
                            ICatalogObject cataObj = (ICatalogObject) _mainObject;
                            cataObj.refresh();
                        }
                        foundTable = true;
                        break;
                    }
                }
            }
        }
        if(!foundTable)
        {
            _mainObject = null;
        }
        else
        {
            refreshAdditionalObjects();
        }
    }

    private void refreshAdditionalObjects()
    {
        refreshCatalogObjects();
        reloadPrimaryTables();
        _additionalObjects.put(Constants.INDEXES, ((BaseTable) _mainObject).getIndex());
    }

    private void reloadPrimaryTables()
    {
        clearOldPrimaryTables();
        
        BaseTable table = (BaseTable) _mainObject;
        Iterator iter = table.getForeignKeys().iterator();
        while (iter.hasNext())
        {
            // Force to load
            Object obj = iter.next();
            if (obj instanceof ForeignKey)
            {
                ForeignKey fk = (ForeignKey) obj;
                fk.getReferencedTable();
                fk.getUniqueConstraint();
                fk.getUniqueIndex();
                fk.getReferencedMembers();
            }
        }
        iter = table.getForeignKeys().iterator();
        int i = 0;
        while (iter.hasNext())
        {
            ForeignKey fk = (ForeignKey) iter.next();
            _additionalObjects.put(Constants.PRIMARY_TABLE + Integer.toString(i), fk.getReferencedTable());
            i++;
        }
    }

    private void clearOldPrimaryTables()
    {
        List primaryTablesToBeRemoved = new ArrayList();
        Iterator keyIter = _additionalObjects.keySet().iterator();
        while(keyIter.hasNext())
        {
            String key = (String)keyIter.next();
            if(key.startsWith(Constants.PRIMARY_TABLE))
            {
                primaryTablesToBeRemoved.add(key);
            }
        }
        Iterator primaryTablesToBeRemovedIter = primaryTablesToBeRemoved.iterator();
        while(primaryTablesToBeRemovedIter.hasNext())
        {
            _additionalObjects.remove((String)primaryTablesToBeRemovedIter.next());
        }
    }

    private void refreshCatalogObjects()
    {
        for (Iterator iter = _additionalObjects.values().iterator(); iter.hasNext();)
        {
            Object element = (Object) iter.next();
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
}
