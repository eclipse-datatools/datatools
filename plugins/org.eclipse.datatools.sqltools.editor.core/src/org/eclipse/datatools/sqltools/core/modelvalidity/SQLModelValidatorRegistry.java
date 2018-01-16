/**
 * Created on 2007-01-17
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.core.modelvalidity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.emf.ecore.EClass;

/**
 * Registry for SQL model validators, there can be two kinds of validator registered:<br>
 * <ul>
 * <li>Validator for one type of model class of the specific database
 * <li>Validator for one type of model class for common use
 * </ul>
 * 
 * @author Hao-Yue
 */
public class SQLModelValidatorRegistry
{
    /**
     * Key for the SQL model validator registry, this kind of validator is for the model class of a specific database
     * type
     * 
     */
    class ValidatorKey
    {
        public DatabaseVendorDefinitionId _vendorDefId;
        public EClass                     _class;

        public ValidatorKey(DatabaseVendorDefinitionId vendorDefId, EClass c)
        {
            super();
            _vendorDefId = vendorDefId;
            this._class = c;
        }

        public boolean equals(Object arg0)
        {
            if (!(arg0 instanceof ValidatorKey))
            {
                return false;
            }
            ValidatorKey key = (ValidatorKey) arg0;
            if ((key._vendorDefId == null && this._vendorDefId != null)
            || (key._vendorDefId != null && this._vendorDefId == null))
            {
                return false;
            }
            if (((key._vendorDefId == null && this._vendorDefId == null) || (key._vendorDefId.equals(this._vendorDefId)))
            && (key._class == this._class))
            {
                return true;
            }
            return false;
        }

        public int hashCode()
        {
            StringBuffer sb = new StringBuffer("");
            if (_vendorDefId != null)
            {
                sb.append(_vendorDefId.toString());
            }
            sb.append(_class.getName());
            return sb.toString().hashCode();
        }
    }

    private Map _validatorMap;
    private SQLModelValidator _defaultValidator = new DefaultSQLModelValidator();

    public SQLModelValidatorRegistry()
    {
        _validatorMap = new HashMap();
        // a generic validator for SQL Object
        _defaultValidator = new DefaultSQLModelValidator();
        registerValidator(SQLSchemaPackage.eINSTANCE.getSQLObject(), _defaultValidator);
    }

    /**
     * Returns the common validator for the given class object
     * 
     * @param c the model class object
     * @return the validator registered
     */
    public SQLModelValidator getValidator(EClass c)
    {
        if (c == null)
        {
            return _defaultValidator;
        }
        Object obj = _validatorMap.get(c);
        if (obj == null)
        {
            List list = new ArrayList();
            list.addAll(c.getESuperTypes());
            return getSuperValidator(list);
        }
        return (SQLModelValidator) obj;

    }

    private SQLModelValidator getSuperValidator(List list)
    {
        if (list.size() == 0)
        {
            // Should not happen
            return _defaultValidator;
        }
        Iterator iter = list.iterator();
        while (iter.hasNext())
        {
            EClass c = (EClass)iter.next();
            Object obj = _validatorMap.get(c);
            if (obj != null)
            {
                return (SQLModelValidator) obj;
            }
        }
        iter = list.iterator();
        List superList = new ArrayList();
        while (iter.hasNext())
        {
            superList.addAll(((EClass) iter.next()).getESuperTypes());
        }
        return getSuperValidator(superList);
    }

    /**
     * Returns the validator for the given model object of the given database type
     * 
     * @param c the model class object
     * @param vendorDefId the vendor id
     * @return the validator registered
     */
    public SQLModelValidator getValidator(EClass c, DatabaseVendorDefinitionId vendorDefId)
    {
        if (c == null)
        {
            return _defaultValidator;
        }
        if (vendorDefId == null)
        {
            return getValidator(c);
        }
        ValidatorKey key = new ValidatorKey(vendorDefId, c);
        Object obj = _validatorMap.get(key);
        if (obj == null)
        {
            // Tries to get the validator of the super type with database type specified
            if (obj == null)
            {
                List list = new ArrayList();
                list.addAll(c.getESuperTypes());
                obj = getSuperValidator(list, vendorDefId);
            }
            
            if (obj == null || obj == _defaultValidator)
            {
                obj = _validatorMap.get(c);
            }

            // Tries to get the common validator of the super type
            if (obj == null || obj == _defaultValidator)
            {
                List list = new ArrayList();
                list.addAll(c.getESuperTypes());
                obj = getSuperValidator(list);
            }
        }
        return (SQLModelValidator) obj;
    }

    private SQLModelValidator getSuperValidator(List list, DatabaseVendorDefinitionId vendorDefId)
    {
        if (list.size() == 0)
        {
            return _defaultValidator;
        }
        Iterator iter = list.iterator();
        while (iter.hasNext())
        {
            EClass c = (EClass) iter.next();
            ValidatorKey key = new ValidatorKey(vendorDefId, c);
            Object obj = _validatorMap.get(key);
            if (obj == null)
            {
                obj = _validatorMap.get(c);
            }
            if (obj != null)
            {
                return (SQLModelValidator) obj;
            }
        }
        iter = list.iterator();
        List superList = new ArrayList();
        while (iter.hasNext())
        {
            superList.addAll(((EClass) iter.next()).getESuperTypes());
        }
        return getSuperValidator(superList, vendorDefId);
    }

    /**
     * Registers a validator for the model class of the given database type
     * 
     * @param c the model class for which the validator is registered
     * @param vendorDefId the vendor id for which the validator is registered
     * @param validator the validator
     */
    public void registerValidator(EClass c, DatabaseVendorDefinitionId vendorDefId, SQLModelValidator validator)
    {
        if (c == null || validator == null)
        {
            return;
        }
        if (vendorDefId == null)
        {
            _validatorMap.put(c, validator);
        }
        else
        {
            _validatorMap.put(new ValidatorKey(vendorDefId, c), validator);
        }
    }

    /**
     * Registers a common validator for the given model class
     * 
     * @param c the model class for which the validator is registered
     * @param validator the validator
     */
    public void registerValidator(EClass c, SQLModelValidator validator)
    {
        if (c == null || validator == null)
        {
            return;
        }
        _validatorMap.put(c, validator);
    }
}
