/**
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A collection of scalar values defined for user selection.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ScalarValueChoices#getScalarValues <em>Scalar Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getScalarValueChoices()
 * @model 
 * @generated
 */
public interface ScalarValueChoices extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Scalar Values</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Scalar Values</em>' containment reference list.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getScalarValueChoices_ScalarValues()
     * @model type="org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition" containment="true" resolveProxies="false" required="true"
     * @generated
     */
    EList getScalarValues();

} // ScalarValueChoices
