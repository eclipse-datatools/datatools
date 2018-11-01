/**
 *************************************************************************
 * Copyright (c) 2010 Actuate Corporation.
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
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Element Identifiers</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A collection of data element identifiers.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataElementIdentifiers#getIdentifiers <em>Identifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementIdentifiers()
 * @model extendedMetaData="name='DataElementIdentifiers' kind='elementOnly'"
 * @generated
 * @since 3.3.2
 */
public interface DataElementIdentifiers extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Identifiers</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.connectivity.oda.design.DataElementIdentifier}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Identifiers</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Identifiers</em>' containment reference list.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementIdentifiers_Identifiers()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='identifiers' namespace='##targetNamespace'"
     * @generated
     */
    EList<DataElementIdentifier> getIdentifiers();

} // DataElementIdentifiers
