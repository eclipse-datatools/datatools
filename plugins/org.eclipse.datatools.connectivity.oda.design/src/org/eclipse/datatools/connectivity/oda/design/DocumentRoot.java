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

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DocumentRoot#getOdaDesignSession <em>Oda Design Session</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDocumentRoot()
 * @model 
 * @generated
 */
public interface DocumentRoot extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mixed</em>' attribute list.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDocumentRoot_Mixed()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     * @generated
     */
    FeatureMap getMixed();

    /**
     * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>XMLNS Prefix Map</em>' map.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDocumentRoot_XMLNSPrefixMap()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry" keyType="java.lang.String" valueType="java.lang.String" transient="true"
     * @generated
     */
    EMap getXMLNSPrefixMap();

    /**
     * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>XSI Schema Location</em>' map.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDocumentRoot_XSISchemaLocation()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry" keyType="java.lang.String" valueType="java.lang.String" transient="true"
     * @generated
     */
    EMap getXSISchemaLocation();

    /**
     * Returns the value of the '<em><b>Oda Design Session</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Definition of an ODA design session on a Data Access Design.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Oda Design Session</em>' containment reference.
     * @see #setOdaDesignSession(OdaDesignSession)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDocumentRoot_OdaDesignSession()
     * @model containment="true" resolveProxies="false" transient="true" volatile="true" derived="true"
     * @generated
     */
    OdaDesignSession getOdaDesignSession();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DocumentRoot#getOdaDesignSession <em>Oda Design Session</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Oda Design Session</em>' containment reference.
     * @see #getOdaDesignSession()
     * @generated
     */
    void setOdaDesignSession( OdaDesignSession value );

} // DocumentRoot
