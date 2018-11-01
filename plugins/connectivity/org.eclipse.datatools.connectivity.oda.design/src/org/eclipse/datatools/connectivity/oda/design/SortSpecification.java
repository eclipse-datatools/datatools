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
 * $Id: SortSpecification.java,v 1.2 2009/07/23 21:43:17 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sort Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Specification of zero or more sort keys.  An empty collection explicitly indicates that the result set 
 * has no sort keys defined.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.SortSpecification#getSortKeys <em>Sort Keys</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getSortSpecification()
 * @since 3.3 (DTP 1.8)
 * @model extendedMetaData="name='SortSpecification' kind='elementOnly'"
 * @generated
 */
public interface SortSpecification extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Sort Keys</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.connectivity.oda.design.SortKey}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sort Keys</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sort Keys</em>' containment reference list.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getSortSpecification_SortKeys()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='sortKeys' namespace='##targetNamespace'"
     * @generated
     */
    EList<SortKey> getSortKeys();

} // SortSpecification
