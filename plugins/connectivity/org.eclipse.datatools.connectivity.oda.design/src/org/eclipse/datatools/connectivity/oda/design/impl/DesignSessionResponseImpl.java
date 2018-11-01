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
 * $Id: DesignSessionResponseImpl.java,v 1.4 2007/04/11 02:59:52 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DataAccessDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse;
import org.eclipse.datatools.connectivity.oda.design.DesignerState;
import org.eclipse.datatools.connectivity.oda.design.SessionStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Session Response</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DesignSessionResponseImpl#getSessionStatus <em>Session Status</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DesignSessionResponseImpl#getDataAccessDesign <em>Data Access Design</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DesignSessionResponseImpl#getDesignerState <em>Designer State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DesignSessionResponseImpl extends EObjectImpl implements
        DesignSessionResponse
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getSessionStatus() <em>Session Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSessionStatus()
     * @generated
     * @ordered
     */
    protected static final SessionStatus SESSION_STATUS_EDEFAULT = SessionStatus.OK_LITERAL;

    /**
     * The cached value of the '{@link #getSessionStatus() <em>Session Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSessionStatus()
     * @generated
     * @ordered
     */
    protected SessionStatus m_sessionStatus = SESSION_STATUS_EDEFAULT;

    /**
     * This is true if the Session Status attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_sessionStatusESet;

    /**
     * The cached value of the '{@link #getDataAccessDesign() <em>Data Access Design</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataAccessDesign()
     * @generated
     * @ordered
     */
    protected DataAccessDesign m_dataAccessDesign;

    /**
     * The cached value of the '{@link #getDesignerState() <em>Designer State</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDesignerState()
     * @generated
     * @ordered
     */
    protected DesignerState m_designerState;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DesignSessionResponseImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return DesignPackage.Literals.DESIGN_SESSION_RESPONSE;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse#setNewDataAccessDesign(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
     * @generated NOT
     */
    public void setNewDataAccessDesign( DataSourceDesign dataSourceDesign )
    {
        DataAccessDesign newAccessDesign = DesignFactory.eINSTANCE
                .createDataAccessDesign();
        newAccessDesign.setNewDataSetDesign( dataSourceDesign );

        setDataAccessDesign( newAccessDesign );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse#getDataSourceDesign()
     * @generated NOT
     */
    public DataSourceDesign getDataSourceDesign()
    {
        if( getDataSetDesign() == null )
            return null;

        return getDataSetDesign().getDataSourceDesign();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse#getDataSetDesign()
     * @generated NOT
     */
    public DataSetDesign getDataSetDesign()
    {
        if( getDataAccessDesign() == null )
            return null;

        return getDataAccessDesign().getDataSetDesign();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SessionStatus getSessionStatus()
    {
        return m_sessionStatus;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSessionStatus( SessionStatus newSessionStatus )
    {
        SessionStatus oldSessionStatus = m_sessionStatus;
        m_sessionStatus = newSessionStatus == null ? SESSION_STATUS_EDEFAULT
                : newSessionStatus;
        boolean oldSessionStatusESet = m_sessionStatusESet;
        m_sessionStatusESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DESIGN_SESSION_RESPONSE__SESSION_STATUS,
                    oldSessionStatus, m_sessionStatus, !oldSessionStatusESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetSessionStatus()
    {
        SessionStatus oldSessionStatus = m_sessionStatus;
        boolean oldSessionStatusESet = m_sessionStatusESet;
        m_sessionStatus = SESSION_STATUS_EDEFAULT;
        m_sessionStatusESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.DESIGN_SESSION_RESPONSE__SESSION_STATUS,
                    oldSessionStatus, SESSION_STATUS_EDEFAULT,
                    oldSessionStatusESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetSessionStatus()
    {
        return m_sessionStatusESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataAccessDesign getDataAccessDesign()
    {
        return m_dataAccessDesign;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataAccessDesign(
            DataAccessDesign newDataAccessDesign, NotificationChain msgs )
    {
        DataAccessDesign oldDataAccessDesign = m_dataAccessDesign;
        m_dataAccessDesign = newDataAccessDesign;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.DESIGN_SESSION_RESPONSE__DATA_ACCESS_DESIGN,
                    oldDataAccessDesign, newDataAccessDesign );
            if( msgs == null )
                msgs = notification;
            else
                msgs.add( notification );
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataAccessDesign( DataAccessDesign newDataAccessDesign )
    {
        if( newDataAccessDesign != m_dataAccessDesign )
        {
            NotificationChain msgs = null;
            if( m_dataAccessDesign != null )
                msgs = ((InternalEObject) m_dataAccessDesign)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DESIGN_SESSION_RESPONSE__DATA_ACCESS_DESIGN,
                                null, msgs );
            if( newDataAccessDesign != null )
                msgs = ((InternalEObject) newDataAccessDesign)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DESIGN_SESSION_RESPONSE__DATA_ACCESS_DESIGN,
                                null, msgs );
            msgs = basicSetDataAccessDesign( newDataAccessDesign, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DESIGN_SESSION_RESPONSE__DATA_ACCESS_DESIGN,
                    newDataAccessDesign, newDataAccessDesign ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DesignerState getDesignerState()
    {
        return m_designerState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDesignerState(
            DesignerState newDesignerState, NotificationChain msgs )
    {
        DesignerState oldDesignerState = m_designerState;
        m_designerState = newDesignerState;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.DESIGN_SESSION_RESPONSE__DESIGNER_STATE,
                    oldDesignerState, newDesignerState );
            if( msgs == null )
                msgs = notification;
            else
                msgs.add( notification );
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDesignerState( DesignerState newDesignerState )
    {
        if( newDesignerState != m_designerState )
        {
            NotificationChain msgs = null;
            if( m_designerState != null )
                msgs = ((InternalEObject) m_designerState)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DESIGN_SESSION_RESPONSE__DESIGNER_STATE,
                                null, msgs );
            if( newDesignerState != null )
                msgs = ((InternalEObject) newDesignerState)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DESIGN_SESSION_RESPONSE__DESIGNER_STATE,
                                null, msgs );
            msgs = basicSetDesignerState( newDesignerState, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DESIGN_SESSION_RESPONSE__DESIGNER_STATE,
                    newDesignerState, newDesignerState ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove( InternalEObject otherEnd,
            int featureID, NotificationChain msgs )
    {
        switch( featureID )
        {
        case DesignPackage.DESIGN_SESSION_RESPONSE__DATA_ACCESS_DESIGN:
            return basicSetDataAccessDesign( null, msgs );
        case DesignPackage.DESIGN_SESSION_RESPONSE__DESIGNER_STATE:
            return basicSetDesignerState( null, msgs );
        }
        return super.eInverseRemove( otherEnd, featureID, msgs );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet( int featureID, boolean resolve, boolean coreType )
    {
        switch( featureID )
        {
        case DesignPackage.DESIGN_SESSION_RESPONSE__SESSION_STATUS:
            return getSessionStatus();
        case DesignPackage.DESIGN_SESSION_RESPONSE__DATA_ACCESS_DESIGN:
            return getDataAccessDesign();
        case DesignPackage.DESIGN_SESSION_RESPONSE__DESIGNER_STATE:
            return getDesignerState();
        }
        return super.eGet( featureID, resolve, coreType );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet( int featureID, Object newValue )
    {
        switch( featureID )
        {
        case DesignPackage.DESIGN_SESSION_RESPONSE__SESSION_STATUS:
            setSessionStatus( (SessionStatus) newValue );
            return;
        case DesignPackage.DESIGN_SESSION_RESPONSE__DATA_ACCESS_DESIGN:
            setDataAccessDesign( (DataAccessDesign) newValue );
            return;
        case DesignPackage.DESIGN_SESSION_RESPONSE__DESIGNER_STATE:
            setDesignerState( (DesignerState) newValue );
            return;
        }
        super.eSet( featureID, newValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset( int featureID )
    {
        switch( featureID )
        {
        case DesignPackage.DESIGN_SESSION_RESPONSE__SESSION_STATUS:
            unsetSessionStatus();
            return;
        case DesignPackage.DESIGN_SESSION_RESPONSE__DATA_ACCESS_DESIGN:
            setDataAccessDesign( (DataAccessDesign) null );
            return;
        case DesignPackage.DESIGN_SESSION_RESPONSE__DESIGNER_STATE:
            setDesignerState( (DesignerState) null );
            return;
        }
        super.eUnset( featureID );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet( int featureID )
    {
        switch( featureID )
        {
        case DesignPackage.DESIGN_SESSION_RESPONSE__SESSION_STATUS:
            return isSetSessionStatus();
        case DesignPackage.DESIGN_SESSION_RESPONSE__DATA_ACCESS_DESIGN:
            return m_dataAccessDesign != null;
        case DesignPackage.DESIGN_SESSION_RESPONSE__DESIGNER_STATE:
            return m_designerState != null;
        }
        return super.eIsSet( featureID );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString()
    {
        if( eIsProxy() )
            return super.toString();

        StringBuffer result = new StringBuffer( super.toString() );
        result.append( " (sessionStatus: " ); //$NON-NLS-1$
        if( m_sessionStatusESet )
            result.append( m_sessionStatus );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ')' );
        return result.toString();
    }

} //DesignSessionResponseImpl
