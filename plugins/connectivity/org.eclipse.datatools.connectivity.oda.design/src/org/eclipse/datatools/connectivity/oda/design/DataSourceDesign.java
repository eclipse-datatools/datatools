/**
 *************************************************************************
 * Copyright (c) 2005, 2011 Actuate Corporation.
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
 * $Id: DataSourceDesign.java,v 1.5 2010/02/17 02:20:39 lchan Exp $
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
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getEffectiveOdaExtensionId <em>Effective Oda Extension Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getOdaExtensionDataSourceId <em>Oda Extension Data Source Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getPublicProperties <em>Public Properties</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getPrivateProperties <em>Private Properties</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getLinkedProfileName <em>Linked Profile Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getLinkedProfileStoreFilePath <em>Linked Profile Store File Path</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getHostResourceIdentifiers <em>Host Resource Identifiers</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getResourceFile <em>Resource File</em>}</li>
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
    String copyright = "Copyright (c) 2005, 2011 Actuate Corporation"; //$NON-NLS-1$

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
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
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
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
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
     * Returns the value of the '<em><b>Effective Oda Extension Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The ID of the effective oda.datasource extension, which will be consumed by the ODA consumer framework at runtime.  This could be another extension that overrides this ODA extension at runtime.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Effective Oda Extension Id</em>' attribute.
     * @see #setEffectiveOdaExtensionId(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSourceDesign_EffectiveOdaExtensionId()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='effectiveOdaExtensionId' namespace='##targetNamespace'"
     * @generated
     * @since 3.3.3
     */
    String getEffectiveOdaExtensionId();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getEffectiveOdaExtensionId <em>Effective Oda Extension Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Effective Oda Extension Id</em>' attribute.
     * @see #getEffectiveOdaExtensionId()
     * @generated
     * @since 3.3.3
     */
    void setEffectiveOdaExtensionId( String value );

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
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
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
     * <!-- begin-model-doc -->
     * Text can be localized with a resource key.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Display Name</em>' attribute.
     * @see #setDisplayName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSourceDesign_DisplayName()
     * @see #getDisplayNameKey()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
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
     * @see #setDisplayNameKey(String)
     * @generated
     */
    void setDisplayName( String value );

    /**
     * Returns the resource key of the '<em><b>Display Name</b></em>' attribute.
     * @return  the resource key of the '<em>Display Name</em>' attribute; may be null if none is available
     * @see #setDisplayNameKey(String)
     * @see #getDisplayName()
     * @see #getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    String getDisplayNameKey();

    /**
     * Sets the resource key of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getDisplayName <em>Display Name</em>}' attribute.
     * @param value  the new resource key of the '<em>Display Name</em>' attribute;
     *              may be null to reset
     * @see #getDisplayNameKey()
     * @see #setDisplayName(String)
     * @see #getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    void setDisplayNameKey( String value );

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
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
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
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
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
     * Returns the value of the '<em><b>Host Resource Identifiers</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The resource identifiers of the ODA host application that initiates the ODA design session request.  An ODA host application may optionally specify its resource identifiers for reference by an ODA designer.   Its support and usage by a custom ODA designer is optional and implementation-dependent.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Host Resource Identifiers</em>' containment reference.
     * @see #setHostResourceIdentifiers(ResourceIdentifiers)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSourceDesign_HostResourceIdentifiers()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='hostResourceIdentifiers' namespace='##targetNamespace'"
     * @generated
     * @since DTP 1.6.1
     */
    ResourceIdentifiers getHostResourceIdentifiers();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getHostResourceIdentifiers <em>Host Resource Identifiers</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Host Resource Identifiers</em>' containment reference.
     * @see #getHostResourceIdentifiers()
     * @generated
     * @since DTP 1.6.1
     */
    void setHostResourceIdentifiers( ResourceIdentifiers value );

    /**
     * Returns the value of the '<em><b>Resource File</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The base name of a resource file for all localizable design attributes. The file name must end with .properties, and must locate relative to one of the host resource identifiers.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Resource File</em>' attribute.
     * @see #setResourceFile(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSourceDesign_ResourceFile()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='resourceFile' namespace='##targetNamespace'"
     * @generated
     * @since 3.2.3
     */
    String getResourceFile();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSourceDesign#getResourceFile <em>Resource File</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resource File</em>' attribute.
     * @see #getResourceFile()
     * @generated
     * @since 3.2.3
     */
    void setResourceFile( String value );

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
