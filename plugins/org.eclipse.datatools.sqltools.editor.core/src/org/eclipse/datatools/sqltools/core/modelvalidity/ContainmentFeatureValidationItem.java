/**
 * Created on 2006-11-17
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.core.modelvalidity;

/**
 * The validation item for containment feature. The context can not be null if it's a contaiment validation
 * item,otherwise the validation can not proceed. It's either specified by the client or cloned from its parent object's
 * validation context
 * 
 * @author Idull
 */
public class ContainmentFeatureValidationItem extends ValidationItem
{
    public ContainmentFeatureValidationItem(int identityId)
    {
        super(identityId);
        _type = REFERENCE_TYPE;
    }

    public ContainmentFeatureValidationItem(int identityId, int type)
    {
        super(identityId, type);
    }

    public int getDepth()
    {
        int depth = 0;
        try
        {
            depth = ((Integer) getContext().get(SQLModelValidationDelegate.VALIDATION_DEPTH_KEY)).intValue();
        }
        catch (Exception e)
        {
        }
        return depth;
    }
}
