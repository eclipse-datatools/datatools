/**
 * Created on 2006-11-16
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.core.modelvalidity;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Validation item for attribute and un-containment feature, also for syntax validation item
 * 
 * @author Idull
 */
public class ValidationItem implements IValidationItem
{
    protected int    _type;
    protected int    _identityId;

    /**
     * The validation context for this validation item, consumer can put any data in it
     */
    protected Map    _context;

    public ValidationItem(int identityId, int type)
    {
        super();
        this._identityId = identityId;
        this._type = type;
        this._context = new HashMap();
    }

    public ValidationItem(int identityId)
    {
        this(identityId, ATTRIBUTE_TYPE);
    }

    public int getType()
    {
        return _type;
    }

    public int getIdentityId()
    {
        return _identityId;
    }

    public EStructuralFeature getFeature(EClass eClass)
    {
        if (_type == SEMANTIC_TYPE || eClass == null)
        {
            return null;
        }
        return eClass.getEStructuralFeature(_identityId);
    }

    public Map getContext()
    {
        return _context;
    }

    public void setContext(Map context)
    {
        _context = context;
    }
    
    public int hashCode()
    {
        return _identityId * 10 + _type;
    }
    
    public boolean equals(Object obj)
    {
        ValidationItem o = (ValidationItem)obj;
        return o != null && o._identityId == this._identityId && o._type == this._type;
    }
}
