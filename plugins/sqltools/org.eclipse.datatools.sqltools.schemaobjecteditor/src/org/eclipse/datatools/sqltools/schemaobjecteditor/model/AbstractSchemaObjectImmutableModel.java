/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;

/**
 * 
 * @author Idull
 */
public abstract class AbstractSchemaObjectImmutableModel implements ISchemaObjectImmutableModel
{
    protected SQLObject _mainObject;
    protected Map       _additionalObjects;

    public AbstractSchemaObjectImmutableModel(SQLObject object, Map ojbects)
    {
        super();
        _mainObject = object;
        _additionalObjects = ojbects;
    }

    public Map getAdditionalSQLObjects()
    {
        if (_additionalObjects == null)
        {
            _additionalObjects = new HashMap();
        }
        return _additionalObjects;
    }

    public SQLObject getMainSQLObject()
    {
        return _mainObject;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof ISchemaObjectImmutableModel))
        {
            return false;
        }
        boolean isEqual = true;
        ISchemaObjectImmutableModel otherModel = (ISchemaObjectImmutableModel) obj;
        if (otherModel.getMainSQLObject() == null)
        {
            if (_mainObject != null)
            {
                return false;
            }
        }
        else if (_mainObject == null)
        {
            return false;
        }
        try
        {
            // In case the name has been changed
            if (!otherModel.getMainSQLObject().getName().equals(_mainObject.getName()))
            {
                return false;
            }
            isEqual &= otherModel.getMainSQLObject().equals(_mainObject);

            // FIX472567-1: Do not compare additional object
            // isEqual &= otherModel.getAdditionalSQLObjects().equals(_additionalObjects);
        }
        catch (Exception e)
        {
            // in case NullPointerException occurs
            return false;
        }

        return isEqual;
    }

    /**
     * Subclass should override this method
     */
    public void refreshFromDB(String newName)
    {

    }

    public boolean isModelExist()
    {
        Schema schema = ModelUtil.getSchema(_mainObject);
        if (schema == null)
        {
            return false;
        }
        else
            return true;
    }

}
