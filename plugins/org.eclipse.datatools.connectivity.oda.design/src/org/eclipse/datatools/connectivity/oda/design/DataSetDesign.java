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
 * @model 
 * @generated
 */
public interface DataSetDesign extends EObject
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
     * The unique name that identifies an instance of dataSetDesign.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign_Name()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
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
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
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
     * @model containment="true" resolveProxies="false" required="true"
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
     * @model containment="true" resolveProxies="false" required="true"
     * @generated
     */
    DataSetQuery getQuery();

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
     * Returns the value of the '<em><b>Display Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Display Name</em>' attribute.
     * @see #setDisplayName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign_DisplayName()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getDisplayName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetDesign#getDisplayName <em>Display Name</em>}' attribute.
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
     * Properties specific to the underlying data set query.  Public property values can be visible and editable in the host designer.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Public Properties</em>' containment reference.
     * @see #setPublicProperties(Properties)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign_PublicProperties()
     * @model containment="true" resolveProxies="false"
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
     * @model containment="true" resolveProxies="false"
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
     * @model containment="true" resolveProxies="false"
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
     * Returns the value of the '<em><b>Primary Result Set Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Identifies the primary result set if more than one result sets are available, and can be identified by name.  If results sets are identified by sequence, the first result set is the primary one.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Primary Result Set Name</em>' attribute.
     * @see #setPrimaryResultSetName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetDesign_PrimaryResultSetName()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
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
     * @model containment="true" resolveProxies="false"
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
