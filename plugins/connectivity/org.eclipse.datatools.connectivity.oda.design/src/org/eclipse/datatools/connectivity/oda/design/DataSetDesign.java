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
 * $Id: DataSetDesign.java,v 1.8 2010/02/17 02:20:40 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A design-time data set definition.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getOdaExtensionDataSetId <em>Oda Extension Data Set Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getDataSourceDesign <em>Data Source Design</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getQuery <em>Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getPublicProperties <em>Public Properties</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getPrivateProperties <em>Private Properties</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getResultSets <em>Result Sets</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getPrimaryResultSetName <em>Primary Result Set Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign()
 * @model extendedMetaData="name='DataSetDesign' kind='elementOnly'"
 * @generated
 */
public interface DataSetDesign extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2011 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the ODA data source element ID of the
     * dataSourceDesign that supports this type of data set.  
     * Could be null, if nested within another dataSetDesign, 
     * to share the container's dataSourceDesign.
     * @see #getDataSourceDesign()
     * @generated NOT
     */
    public String getOdaExtensionDataSourceId();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The unique name that identifies an instance of dataSetDesign.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName( String value );

    /**
     * Returns the value of the '<em><b>Oda Extension Data Set Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The dataSet element ID defined in an ODA extension plugin manifest.  It identifies the type of data set design supported by the ODA extension.  Optional if only one dataSet element is defined in the manifest.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Oda Extension Data Set Id</em>' attribute.
     * @see #setOdaExtensionDataSetId(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign_OdaExtensionDataSetId()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='odaExtensionDataSetId' namespace='##targetNamespace'"
     * @generated
     */
    String getOdaExtensionDataSetId();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getOdaExtensionDataSetId <em>Oda Extension Data Set Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Oda Extension Data Set Id</em>' attribute.
     * @see #getOdaExtensionDataSetId()
     * @generated
     */
    void setOdaExtensionDataSetId( String value );

    /**
     * Returns the value of the '<em><b>Data Source Design</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The instance of dataSourceDesign that supports this type of data set.  Could be null, if nested within another dataSetDesign, to share the container's dataSourceDesign.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Data Source Design</em>' containment reference.
     * @see #setDataSourceDesign(DataSourceDesign)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign_DataSourceDesign()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='dataSourceDesign' namespace='##targetNamespace'"
     * @generated
     */
    DataSourceDesign getDataSourceDesign();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getDataSourceDesign <em>Data Source Design</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Source Design</em>' containment reference.
     * @see #getDataSourceDesign()
     * @generated
     */
    void setDataSourceDesign( DataSourceDesign value );

    /**
     * Returns the value of the '<em><b>Query</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query</em>' containment reference.
     * @see #setQuery(DataSetQuery)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign_Query()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='query' namespace='##targetNamespace'"
     * @generated
     */
    DataSetQuery getQuery();

    /**
     * Returns the value of the '<em><b>Query Text</b></em>' attribute
     * in the associated '<em><b>Query</b></em>' containment reference.
     * The query command text to execute at runtime to retrieve data for this data set.  
     * The query syntax is specific to a data source; could be an empty string.
     * @return the value of the '<em>Query Text</em>' attribute;
     *          may be null if no Query is defined
     * @see #getQuery
     * @generated NOT
     */
    String getQueryText();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getQuery <em>Query</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query</em>' containment reference.
     * @see #getQuery()
     * @generated
     */
    void setQuery( DataSetQuery value );

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getQuery <em>Query</em>}' containment reference
     * with the given query text attribute.
     * @param queryText the value of the '<em>Query Text</em>' attribute
     *                  of the '<em>Query</em>' containment reference
     * @see #setQuery()
     * @generated NOT
     */
    void setQueryText( String queryText );

    /**
     * Returns the value of the '<em><b>Display Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Text can be localized with a resource key.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Display Name</em>' attribute.
     * @see #setDisplayName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign_DisplayName()
     * @see #getDisplayNameKey()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='displayName' namespace='##targetNamespace'"
     * @generated
     */
    String getDisplayName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getDisplayName <em>Display Name</em>}' attribute.
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
     * @see DataSourceDesign#getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    String getDisplayNameKey();

    /**
     * Sets the resource key of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getDisplayName <em>Display Name</em>}' attribute.
     * @param value  the new resource key of the '<em>Display Name</em>' attribute;
     *              may be null to reset
     * @see #getDisplayNameKey()
     * @see #setDisplayName(String)
     * @see DataSourceDesign#getResourceFile()
     * @generated NOT
     * @since 3.2.3
     */
    void setDisplayNameKey( String value );

    /**
     * Returns the value of the '<em><b>Public Properties</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Properties specific to the underlying data set query.  Public property values can be visible and editable in the host designer.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Public Properties</em>' containment reference.
     * @see #setPublicProperties(Properties)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign_PublicProperties()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='publicProperties' namespace='##targetNamespace'"
     * @generated
     */
    Properties getPublicProperties();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getPublicProperties <em>Public Properties</em>}' containment reference.
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
     * Private properties specific to the underlying data set query.  Their definitions are private to the ODA designer, i.e. must not be visible nor editable by the host designer.  The "private" nature only applies to the design-time behavior of an ODA host designer.  At run-time, both public and private properties are combined and passed to the ODA runtime driver as a single set of runtime properties.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Private Properties</em>' containment reference.
     * @see #setPrivateProperties(Properties)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign_PrivateProperties()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='privateProperties' namespace='##targetNamespace'"
     * @generated
     */
    Properties getPrivateProperties();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getPrivateProperties <em>Private Properties</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Private Properties</em>' containment reference.
     * @see #getPrivateProperties()
     * @generated
     */
    void setPrivateProperties( Properties value );

    /**
     * Returns the value of the '<em><b>Result Sets</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Result Sets</em>' containment reference.
     * @see #setResultSets(ResultSets)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign_ResultSets()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='resultSets' namespace='##targetNamespace'"
     * @generated
     */
    ResultSets getResultSets();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getResultSets <em>Result Sets</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Result Sets</em>' containment reference.
     * @see #getResultSets()
     * @generated
     */
    void setResultSets( ResultSets value );

    /**
     * Returns the primary result set's definition in the 
     * '<em><b>Result Sets</b></em>' containment reference.
     * @return the primary result set's definition
     * @see #getPrimaryResultSetName()
     * @see #getResultSets()
     * @generated NOT
     */
    ResultSetDefinition getPrimaryResultSet();

    /**
     * Sets the primary result set's definition in the 
     * '<em><b>Result Sets</b></em>' containment reference.
     * @param resultSet the primary result set's definition;
     *                  a null value is ignored
     * @see #setPrimaryResultSetName(String)
     * @see #setResultSets(ResultSets)
     * @generated NOT
     */
    void setPrimaryResultSet( ResultSetDefinition resultSetDefn );

    /**
     * Returns the value of the '<em><b>Primary Result Set Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Identifies the primary result set if more than one result sets are available, and can be identified by name.  If results sets are identified by sequence, the first result set is the primary one.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Primary Result Set Name</em>' attribute.
     * @see #setPrimaryResultSetName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign_PrimaryResultSetName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='primaryResultSetName' namespace='##targetNamespace'"
     * @generated
     */
    String getPrimaryResultSetName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getPrimaryResultSetName <em>Primary Result Set Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Primary Result Set Name</em>' attribute.
     * @see #getPrimaryResultSetName()
     * @generated
     */
    void setPrimaryResultSetName( String value );

    /**
     * Returns the value of the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameters</em>' containment reference.
     * @see #setParameters(DataSetParameters)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign_Parameters()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='parameters' namespace='##targetNamespace'"
     * @generated
     */
    DataSetParameters getParameters();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getParameters <em>Parameters</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parameters</em>' containment reference.
     * @see #getParameters()
     * @generated
     */
    void setParameters( DataSetParameters value );

} // DataSetDesign
