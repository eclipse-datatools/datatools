/**
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
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
 * $Id: DataSourceDesign.java,v 1.2 2006/02/08 08:06:17 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import java.io.File;

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
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getLinkedProfileName <em>Linked Profile Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getLinkedProfileStoreFilePath <em>Linked Profile Store File Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSourceDesign()
 * @model extendedMetaData="name='DataSourceDesign' kind='elementOnly'"
 * @generated
 */
public interface DataSourceDesign extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

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
     *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
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
     *        extendedMetaData="kind='element' name='odaExtensionId' namespace='##targetNamespace'"
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
     *        extendedMetaData="kind='element' name='odaExtensionDataSourceId' namespace='##targetNamespace'"
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
     *        extendedMetaData="kind='element' name='displayName' namespace='##targetNamespace'"
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
     * @model containment="true"
     *        extendedMetaData="kind='element' name='publicProperties' namespace='##targetNamespace'"
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
     * @model containment="true"
     *        extendedMetaData="kind='element' name='privateProperties' namespace='##targetNamespace'"
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

    /**
     * Returns the value of the '<em><b>Linked Profile Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The name of a linked connection profile.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Linked Profile Name</em>' attribute.
     * @see #setLinkedProfileName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSourceDesign_LinkedProfileName()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='linkedProfileName' namespace='##targetNamespace'"
     * @generated
     */
    String getLinkedProfileName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getLinkedProfileName <em>Linked Profile Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Linked Profile Name</em>' attribute.
     * @see #getLinkedProfileName()
     * @generated
     */
    void setLinkedProfileName( String value );

    /**
     * Returns the value of the '<em><b>Linked Profile Store File Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The full path name of a linked connection profiles storage file where the linked profile is to be located at run-time.  A null value indicates to use the default DTP profiles storage file.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Linked Profile Store File Path</em>' attribute.
     * @see #setLinkedProfileStoreFilePath(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSourceDesign_LinkedProfileStoreFilePath()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='linkedProfileStoreFilePath' namespace='##targetNamespace'"
     * @generated
     */
    String getLinkedProfileStoreFilePath();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getLinkedProfileStoreFilePath <em>Linked Profile Store File Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Linked Profile Store File Path</em>' attribute.
     * @see #getLinkedProfileStoreFilePath()
     * @generated
     */
    void setLinkedProfileStoreFilePath( String value );

    /**
     * Returns the profile storage file
     * where the linked profile is stored.
     * @return  the linked profile storage file,
     *          or null value if no profile store is set
     * @see #getLinkedProfileStoreFilePath()
     * @see #setLinkedProfileStoreFile(File)
     * @generated NOT
     */
    File getLinkedProfileStoreFile();

    /**
     * Sets the profile storage file
     * where the linked profile is stored.
     * @param storageFile the profile storage file;
     *                  null value unsets any linked profile store
     * @see #getLinkedProfileStoreFile()
     * @see #getLinkedProfileStoreFilePath()
     * @generated NOT
     */
    void setLinkedProfileStoreFile( File storageFile );

    /**
     * Indicates whether the data source design has a link
     * to an external connection profile in a profile store.
     * @return  true if a link is specified; false otherwise
     * @generated NOT
     */
    boolean hasLinkToProfile();

} // DataSourceDesign
