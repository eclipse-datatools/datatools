/**
 * Created on 2006-11-16
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.core.modelvalidity;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Item to be validated
 * 
 * @author Idull
 */
public interface IValidationItem
{
    /**
     * It's an attribute in the model
     */
    public static final int ATTRIBUTE_TYPE = 0;

    /**
     * It's a reference in the model
     */
    public static final int REFERENCE_TYPE = 1;

    /**
     * The semantic validation item is to valid some semantic related issue cross features, for example, if a column is
     * the column in primary key, it should not be nullable
     */
    public static final int SEMANTIC_TYPE  = 2;

    /**
     * Returns the type of this validation item.
     * 
     * @return
     */
    public int getType();

    /**
     * Returns the identity id of this validation item
     * <p>
     * If it's a feature validation item, this should be feature id
     * <p>
     * If it's a syntax validation item, this should be the syntax validation item id
     * 
     * @return
     */
    public int getIdentityId();

    /**
     * Returns the feature of this validation item. If its type is SYNTAX_TYPE, null will be returned
     * 
     * @return
     */
    public EStructuralFeature getFeature(EClass eClass);

    /**
     * Returns the validation context for this item
     * 
     * @return
     */
    public Map getContext();

    /**
     * Sets the validation context for this item
     * 
     * @param context
     */
    public void setContext(Map context);
}
