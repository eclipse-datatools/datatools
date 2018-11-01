/**
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
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
 * $Id: OdaDesignSession.java,v 1.8 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition of an ODA design session on a Data Access Design.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getRequest <em>Request</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getResponse <em>Response</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOdaDesignSession()
 * @model extendedMetaData="name='OdaDesignSession' kind='elementOnly'"
 * @generated
 */
public interface OdaDesignSession extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Request</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Request</em>' containment reference.
     * @see #setRequest(DesignSessionRequest)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOdaDesignSession_Request()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='request' namespace='##targetNamespace'"
     * @generated
     */
    DesignSessionRequest getRequest();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getRequest <em>Request</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Request</em>' containment reference.
     * @see #getRequest()
     * @generated
     */
    void setRequest( DesignSessionRequest value );

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getRequest <em>Request</em>}' containment reference
     * with a new request associated with the given data source design. 
     * @param dataSourceDesign
     * @see #setRequest(DesignSessionRequest)
     * @generated NOT
     */
    void setNewRequest( DataSourceDesign dataSourceDesign );


    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getRequest <em>Request</em>}' containment reference
     * with a new request associated with the given data set design. 
     * @param dataSetDesign
     * @since 3.0.4
     * @see #setRequest(DesignSessionRequest)
     * @generated NOT
     */
    void setNewRequest( DataSetDesign dataSetDesign );

    /**
     * Returns the value of the '<em><b>Response</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Response</em>' containment reference.
     * @see #setResponse(DesignSessionResponse)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOdaDesignSession_Response()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='response' namespace='##targetNamespace'"
     * @generated
     */
    DesignSessionResponse getResponse();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getResponse <em>Response</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Response</em>' containment reference.
     * @see #getResponse()
     * @generated
     */
    void setResponse( DesignSessionResponse value );

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getResponse <em>Response</em>}' containment reference
     * with a new response that contains the given session status
     * and DataSourceDesign.
     * <br>This method may be used by an ODA driver's design UI extension
     * to update this design session with a response that contains
     * the edited data source design definition.
     * @param dataSourceDesign
     * @see #setResponse(DesignSessionResponse)
     * @generated NOT
     */
    void setNewResponse( boolean isSessionOk, DataSourceDesign dataSourceDesign );

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getResponse <em>Response</em>}' containment reference
     * with a new response that contains the given session status
     * and DataSetDesign instance.
     * <br>This method may be used by an ODA driver's design UI extension
     * to update this design session with a response that contains
     * the edited data set design definition.
     * @param dataSetDesign
     * @see #setResponse(DesignSessionResponse)
     * @generated NOT
     */
    void setNewResponse( boolean isSessionOk, DataSetDesign dataSetDesign );

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getResponse <em>Response</em>}' containment reference
     * with a response in a Cancelled session state.
     * @see #setResponse(DesignSessionResponse)
     * @generated NOT
     */
    void setResponseInCancelledState();

    /**
     * Obtains the Data Source Design associated with the
     * top-level Data Set in the Request session.
     * @return the value of the '<em>Data Source Design</em>' containment reference;
     *          may be null if none is specified.
     * @see #getRequest()
     * @generated NOT
     */
    DataSourceDesign getRequestDataSourceDesign();

    /**
     * Obtains the top-level Data Set Design associated with the
     * Request session.
     * @return the value of the '<em>Data Set Design</em>' containment reference;
     *          may be null if none is specified.
     * @see #getRequest()
     * @generated NOT
     */
    DataSetDesign getRequestDataSetDesign();

    /**
     * Gets the Data Source Design associated with the
     * top level Data Set in the Response session.
     * @return the value of the '<em>Data Source Design</em>' containment reference;
     *          could be null if none is specified.
     * @see #getResponse()
     * @generated NOT
     */
    DataSourceDesign getResponseDataSourceDesign();

    /**
     * Gets the Data Set Design associated with the
     * Response session.
     * @return the value of the '<em>Data Set Design</em>' containment reference;
     *          could be null if none is specified.
     * @see #getResponse()
     * @generated NOT
     */
    DataSetDesign getResponseDataSetDesign();

} // OdaDesignSession
