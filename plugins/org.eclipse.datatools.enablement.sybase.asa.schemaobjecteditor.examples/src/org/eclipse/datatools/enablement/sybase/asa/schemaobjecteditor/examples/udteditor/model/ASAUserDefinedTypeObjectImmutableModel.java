/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.udteditor.model;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.AbstractSchemaObjectImmutableModel;
import org.eclipse.emf.common.util.EList;

/**
 * @author renj
 */
public class ASAUserDefinedTypeObjectImmutableModel extends AbstractSchemaObjectImmutableModel
{

    public ASAUserDefinedTypeObjectImmutableModel(SQLObject object, Map ojbects)
    {
        super(object, ojbects);
    }

    public void refreshFromDB(String newName)
    {
        if (_mainObject.getName().equals(newName))
        {
            if (_mainObject instanceof ICatalogObject)
            {
                if (_mainObject instanceof SybaseASABaseUserDefinedType)
                {
                    Schema schema = ((UserDefinedType) _mainObject).getSchema();
                    // schema may lost
                    if (schema == null)
                    {
                        _mainObject = null;
                        return;
                    }
                }
                ((ICatalogObject) _mainObject).refresh();
                return;
            }
        }
        
        if (!isModelExist()) 
        {
            _mainObject = null;
            return;
        }

        boolean isFound = false;
        if (_mainObject instanceof SybaseASABaseUserDefinedType)
        {
            Schema schema = ((SybaseASABaseUserDefinedType) _mainObject).getSchema();
            // schema may lost
            if (schema != null)
            {
//                DSEUtil.refreshObjectBySchema(schema, _mainObject);

                EList udds = schema.getUserDefinedTypes();
                for (Iterator iter = udds.iterator(); iter.hasNext();)
                {
                    SybaseASABaseUserDefinedType asaUDD = (SybaseASABaseUserDefinedType) iter.next();
                    if (asaUDD.getName().equals(_mainObject.getName()))
                    {
                        _mainObject = asaUDD;
                        isFound = true;
                        break;
                    }
                }

                if (!isFound)
                {
                    for (Iterator iter = udds.iterator(); iter.hasNext();)
                    {
                        SybaseASABaseUserDefinedType asaUDD = (SybaseASABaseUserDefinedType) iter.next();
                        if (asaUDD.getName().equals(newName))
                        {
                            _mainObject = asaUDD;
                            isFound = true;
                            break;
                        }
                    }
                }
            }
        }
        if (!isFound)
        {
            _mainObject = null;
        }
    }

}
