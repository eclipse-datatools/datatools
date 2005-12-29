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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A design-time data source definition.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getOdaExtensionId <em>Oda Extension Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getOdaExtensionDataSourceId <em>Oda Extension Data Source Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getPublicProperties <em>Public Properties</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getPrivateProperties <em>Private Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSourceDesign()
 * @model 
 * @generated
 */
public interface DataSourceDesign extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The unique name that identifies an instance of dataSourceDesign.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSourceDesign_Name()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName( String value );

    /**
     * Returns the value of the '<em><b>Oda Extension Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The extension ID defined in an ODA extension plugin manifest.  It identifies the supporting ODA driver's extension plug-in that implements the ODA run-time extension point.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Oda Extension Id</em>' attribute.
     * @see #setOdaExtensionId(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSourceDesign_OdaExtensionId()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * @generated
     */
    String getOdaExtensionId();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getOdaExtensionId <em>Oda Extension Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Oda Extension Id</em>' attribute.
     * @see #getOdaExtensionId()
     * @generated
     */
    void setOdaExtensionId( String value );

    /**
     * Returns the value of the '<em><b>Oda Extension Data Source Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The data source element ID defined in an ODA extension plugin manifest.  Optional, defaults to be same as the ODA extension id if only one dataSource element is defined in the manifest.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Oda Extension Data Source Id</em>' attribute.
     * @see #setOdaExtensionDataSourceId(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSourceDesign_OdaExtensionDataSourceId()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getOdaExtensionDataSourceId();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getOdaExtensionDataSourceId <em>Oda Extension Data Source Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Oda Extension Data Source Id</em>' attribute.
     * @see #getOdaExtensionDataSourceId()
     * @generated
     */
    void setOdaExtensionDataSourceId( String value );

    /**
     * Returns the value of the '<em><b>Display Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Display Name</em>' attribute.
     * @see #setDisplayName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSourceDesign_DisplayName()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getDisplayName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getDisplayName <em>Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Name</em>' attribute.
     * @see #getDisplayName()
     * @generated
     */
    void setDisplayName( String value );

    /**
     * Returns the value of the '<em><b>Public Properties</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Properties specific to the underlying data source connection.  Public property values can be visible and editable in the host designer.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Public Properties</em>' containment reference.
     * @see #setPublicProperties(Properties)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSourceDesign_PublicProperties()
     * @model containment="true" resolveProxies="false"
     * @generated
     */
    Properties getPublicProperties();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getPublicProperties <em>Public Properties</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Public Properties</em>' containment reference.
     * @see #getPublicProperties()
     * @generated
     */
    void setPublicProperties( Properties value );

    /**
     * Returns the value of the '<em><b>Private Properties</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Private properties specific to the underlying data source connection.  Their definitions are private to the ODA designer, i.e. must not be visible nor editable by the host designer.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Private Properties</em>' containment reference.
     * @see #setPrivateProperties(Properties)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSourceDesign_PrivateProperties()
     * @model containment="true" resolveProxies="false"
     * @generated
     */
    Properties getPrivateProperties();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getPrivateProperties <em>Private Properties</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Private Properties</em>' containment reference.
     * @see #getPrivateProperties()
     * @generated
     */
    void setPrivateProperties( Properties value );

} // DataSourceDesign
