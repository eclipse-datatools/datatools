/**
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: CompositeFilterExpression.java,v 1.1 2009/01/30 00:23:57 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite Filter Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A composite of one or multiple child filter expressions.  Its child expressions are ordered, but their relationships are not defined.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.CompositeFilterExpression#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getCompositeFilterExpression()
 * @since 3.3 (DTP 1.8)
 * @model extendedMetaData="name='CompositeFilterExpression' kind='elementOnly'"
 * @generated
 */
public interface CompositeFilterExpression extends FilterExpression
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Children</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.connectivity.oda.design.FilterExpression}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Children</em>' containment reference list.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getCompositeFilterExpression_Children()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='children' namespace='##targetNamespace'"
     * @generated
     */
    EList<FilterExpression> getChildren();

    /**
     * Appends the specified FilterExpression to its '<em><b>Children</b></em>' containment reference list.
     * @param childExpr a child filter expression
     * @generated NOT
     */
    void add( FilterExpression childExpr );
    
} // CompositeFilterExpression
