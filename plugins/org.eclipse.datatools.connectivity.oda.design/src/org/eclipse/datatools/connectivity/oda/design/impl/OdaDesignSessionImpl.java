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
 * $Id: OdaDesignSessionImpl.java,v 1.2 2006/02/03 04:16:15 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.SessionStatus;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Oda Design Session</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.OdaDesignSessionImpl#getRequest <em>Request</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.OdaDesignSessionImpl#getResponse <em>Response</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OdaDesignSessionImpl extends EObjectImpl implements OdaDesignSession
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getRequest() <em>Request</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRequest()
     * @generated
     * @ordered
     */
    protected DesignSessionRequest m_request = null;

    /**
     * The cached value of the '{@link #getResponse() <em>Response</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResponse()
     * @generated
     * @ordered
     */
    protected DesignSessionResponse m_response = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OdaDesignSessionImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass()
    {
        return DesignPackage.eINSTANCE.getOdaDesignSession();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#setNewRequest(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
     */
    public void setNewRequest( DataSourceDesign dataSourceDesign )
    {
        DesignSessionRequest newRequest =
            DesignFactory.eINSTANCE.createDesignSessionRequest();
        newRequest.setNewDataAccessDesign( dataSourceDesign );

        setRequest( newRequest );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#setNewResponse(boolean, org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
     */
    public void setNewResponse( boolean isSessionOk, 
                                DataSourceDesign dataSourceDesign )
    {
        DesignSessionResponse newResponse =
            DesignFactory.eINSTANCE.createDesignSessionResponse();
        
        if( isSessionOk )
            newResponse.setSessionStatus( SessionStatus.OK_LITERAL );
        else
            newResponse.setSessionStatus( SessionStatus.ERROR_LITERAL );

        newResponse.setNewDataAccessDesign( dataSourceDesign );
        
        setResponse( newResponse );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#setResponseInCancelledState()
     */
    public void setResponseInCancelledState()
    {
        DesignSessionResponse newResponse =
            DesignFactory.eINSTANCE.createDesignSessionResponse();
        newResponse.setSessionStatus( SessionStatus.USER_CANCELLED_LITERAL );
        setResponse( newResponse );        
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getRequestDataSourceDesign()
     */
    public DataSourceDesign getRequestDataSourceDesign()
    {
        if( getRequest() == null )
            return null;
        
        return getRequest().getDataAccessDesign().getDataSourceDesign();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getResponseDataSourceDesign()
     */
    public DataSourceDesign getResponseDataSourceDesign()
    {
        if( getResponse() == null )
            return null;
        
        return getResponse().getDataAccessDesign().getDataSourceDesign();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DesignSessionRequest getRequest()
    {
        return m_request;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRequest( DesignSessionRequest newRequest, NotificationChain msgs )
    {
        DesignSessionRequest oldRequest = m_request;
        m_request = newRequest;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DesignPackage.ODA_DESIGN_SESSION__REQUEST, oldRequest, newRequest);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRequest( DesignSessionRequest newRequest )
    {
        if (newRequest != m_request)
        {
            NotificationChain msgs = null;
            if (m_request != null)
                msgs = ((InternalEObject)m_request).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DesignPackage.ODA_DESIGN_SESSION__REQUEST, null, msgs);
            if (newRequest != null)
                msgs = ((InternalEObject)newRequest).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DesignPackage.ODA_DESIGN_SESSION__REQUEST, null, msgs);
            msgs = basicSetRequest(newRequest, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.ODA_DESIGN_SESSION__REQUEST, newRequest, newRequest));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DesignSessionResponse getResponse()
    {
        return m_response;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetResponse( DesignSessionResponse newResponse, NotificationChain msgs )
    {
        DesignSessionResponse oldResponse = m_response;
        m_response = newResponse;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DesignPackage.ODA_DESIGN_SESSION__RESPONSE, oldResponse, newResponse);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResponse( DesignSessionResponse newResponse )
    {
        if (newResponse != m_response)
        {
            NotificationChain msgs = null;
            if (m_response != null)
                msgs = ((InternalEObject)m_response).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DesignPackage.ODA_DESIGN_SESSION__RESPONSE, null, msgs);
            if (newResponse != null)
                msgs = ((InternalEObject)newResponse).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DesignPackage.ODA_DESIGN_SESSION__RESPONSE, null, msgs);
            msgs = basicSetResponse(newResponse, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.ODA_DESIGN_SESSION__RESPONSE, newResponse, newResponse));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove( InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs )
    {
        if (featureID >= 0)
        {
            switch (eDerivedStructuralFeatureID(featureID, baseClass))
            {
                case DesignPackage.ODA_DESIGN_SESSION__REQUEST:
                    return basicSetRequest(null, msgs);
                case DesignPackage.ODA_DESIGN_SESSION__RESPONSE:
                    return basicSetResponse(null, msgs);
                default:
                    return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
            }
        }
        return eBasicSetContainer(null, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet( EStructuralFeature eFeature, boolean resolve )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.ODA_DESIGN_SESSION__REQUEST:
                return getRequest();
            case DesignPackage.ODA_DESIGN_SESSION__RESPONSE:
                return getResponse();
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet( EStructuralFeature eFeature, Object newValue )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.ODA_DESIGN_SESSION__REQUEST:
                setRequest((DesignSessionRequest)newValue);
                return;
            case DesignPackage.ODA_DESIGN_SESSION__RESPONSE:
                setResponse((DesignSessionResponse)newValue);
                return;
        }
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset( EStructuralFeature eFeature )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.ODA_DESIGN_SESSION__REQUEST:
                setRequest((DesignSessionRequest)null);
                return;
            case DesignPackage.ODA_DESIGN_SESSION__RESPONSE:
                setResponse((DesignSessionResponse)null);
                return;
        }
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet( EStructuralFeature eFeature )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.ODA_DESIGN_SESSION__REQUEST:
                return m_request != null;
            case DesignPackage.ODA_DESIGN_SESSION__RESPONSE:
                return m_response != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //OdaDesignSessionImpl
