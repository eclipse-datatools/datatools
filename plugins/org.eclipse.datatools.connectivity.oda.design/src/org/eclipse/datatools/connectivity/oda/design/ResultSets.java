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
 * A collection of result sets' definition and metadata. If the metadata can be derived, i.e. can be obtained by an ODA driver in each design session, an ODA host designer is not required to include the derived metadata in the next design session request.  An ODA designer may ignore such metadata in a Request.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ResultSets#getResultSetDefinitions <em>Result Set Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ResultSets#isDerivedMetaData <em>Derived Meta Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSets()
 * @model 
 * @generated
 */
public interface ResultSets extends EObject{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Result Set Definitions</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Result Set Definitions</em>' containment reference list.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSets_ResultSetDefinitions()
     * @model type="org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition" containment="true" resolveProxies="false" required="true"
     * @generated
     */
    EList getResultSetDefinitions();

    /**
     * Returns the value of the '<em><b>Derived Meta Data</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * If the metadata can be derived, i.e. can be obtained by an ODA driver in each design session, an ODA host designer is not required to include the derived metadata in the next design session request.  An ODA designer may ignore such metadata in a Request.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Derived Meta Data</em>' attribute.
     * @see #isSetDerivedMetaData()
     * @see #unsetDerivedMetaData()
     * @see #setDerivedMetaData(boolean)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSets_DerivedMetaData()
     * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     * @generated
     */
    boolean isDerivedMetaData();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResultSets#isDerivedMetaData <em>Derived Meta Data</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Derived Meta Data</em>' attribute.
     * @see #isSetDerivedMetaData()
     * @see #unsetDerivedMetaData()
     * @see #isDerivedMetaData()
     * @generated
     */
    void setDerivedMetaData( boolean value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResultSets#isDerivedMetaData <em>Derived Meta Data</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetDerivedMetaData()
     * @see #isDerivedMetaData()
     * @see #setDerivedMetaData(boolean)
     * @generated
     */
    void unsetDerivedMetaData();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResultSets#isDerivedMetaData <em>Derived Meta Data</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Derived Meta Data</em>' attribute is set.
     * @see #unsetDerivedMetaData()
     * @see #isDerivedMetaData()
     * @see #setDerivedMetaData(boolean)
     * @generated
     */
    boolean isSetDerivedMetaData();

} // ResultSets
