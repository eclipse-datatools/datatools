/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: DataSetParameters.java,v 1.2 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A collection of top-level parameters defined for a data set.  If the metadata can be derived, i.e. can be obtained by an ODA driver in each design session, an ODA host designer is not required to include the derived metadata in the next design session request.  An ODA designer may ignore such metadata in a Request.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSetParameters#getParameterDefinitions <em>Parameter Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSetParameters#isDerivedMetaData <em>Derived Meta Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetParameters()
 * @model extendedMetaData="name='DataSetParameters' kind='elementOnly'"
 * @generated
 */
public interface DataSetParameters extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Parameter Definitions</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.connectivity.oda.design.ParameterDefinition}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameter Definitions</em>' containment reference list.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetParameters_ParameterDefinitions()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='parameterDefinitions' namespace='##targetNamespace'"
     * @generated
     */
    EList<ParameterDefinition> getParameterDefinitions();

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
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetParameters_DerivedMetaData()
     * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='derivedMetaData'"
     * @generated
     */
    boolean isDerivedMetaData();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetParameters#isDerivedMetaData <em>Derived Meta Data</em>}' attribute.
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
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetParameters#isDerivedMetaData <em>Derived Meta Data</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetDerivedMetaData()
     * @see #isDerivedMetaData()
     * @see #setDerivedMetaData(boolean)
     * @generated
     */
    void unsetDerivedMetaData();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetParameters#isDerivedMetaData <em>Derived Meta Data</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Derived Meta Data</em>' attribute is set.
     * @see #unsetDerivedMetaData()
     * @see #isDerivedMetaData()
     * @see #setDerivedMetaData(boolean)
     * @generated
     */
    boolean isSetDerivedMetaData();

} // DataSetParameters
