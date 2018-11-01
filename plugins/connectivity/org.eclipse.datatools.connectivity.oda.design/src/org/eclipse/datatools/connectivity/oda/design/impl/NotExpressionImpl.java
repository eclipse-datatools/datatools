/**
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
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
 * $Id: NotExpressionImpl.java,v 1.1 2009/01/30 00:23:57 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.FilterExpression;
import org.eclipse.datatools.connectivity.oda.design.NotExpression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Not Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.NotExpressionImpl#getNegatingExpression <em>Negating Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 3.3 (DTP 1.8)
 */
public class NotExpressionImpl extends FilterExpressionImpl implements
        NotExpression
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getNegatingExpression() <em>Negating Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNegatingExpression()
     * @generated
     * @ordered
     */
    protected FilterExpression m_negatingExpression;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NotExpressionImpl()
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
        return DesignPackage.Literals.NOT_EXPRESSION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FilterExpression getNegatingExpression()
    {
        return m_negatingExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNegatingExpression(
            FilterExpression newNegatingExpression, NotificationChain msgs )
    {
        FilterExpression oldNegatingExpression = m_negatingExpression;
        m_negatingExpression = newNegatingExpression;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.NOT_EXPRESSION__NEGATING_EXPRESSION,
                    oldNegatingExpression, newNegatingExpression );
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
    public void setNegatingExpression( FilterExpression newNegatingExpression )
    {
        if( newNegatingExpression != m_negatingExpression )
        {
            NotificationChain msgs = null;
            if( m_negatingExpression != null )
                msgs = ((InternalEObject) m_negatingExpression)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.NOT_EXPRESSION__NEGATING_EXPRESSION,
                                null, msgs );
            if( newNegatingExpression != null )
                msgs = ((InternalEObject) newNegatingExpression)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.NOT_EXPRESSION__NEGATING_EXPRESSION,
                                null, msgs );
            msgs = basicSetNegatingExpression( newNegatingExpression, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.NOT_EXPRESSION__NEGATING_EXPRESSION,
                    newNegatingExpression, newNegatingExpression ) );
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
        case DesignPackage.NOT_EXPRESSION__NEGATING_EXPRESSION:
            return basicSetNegatingExpression( null, msgs );
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
        case DesignPackage.NOT_EXPRESSION__NEGATING_EXPRESSION:
            return getNegatingExpression();
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
        case DesignPackage.NOT_EXPRESSION__NEGATING_EXPRESSION:
            setNegatingExpression( (FilterExpression) newValue );
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
        case DesignPackage.NOT_EXPRESSION__NEGATING_EXPRESSION:
            setNegatingExpression( (FilterExpression) null );
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
        case DesignPackage.NOT_EXPRESSION__NEGATING_EXPRESSION:
            return m_negatingExpression != null;
        }
        return super.eIsSet( featureID );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.impl.FilterExpressionImpl#isNegatable()
     * @generated NOT
     */
    @Override
    public boolean isNegatable()
    {
        return true;
    }

} //NotExpressionImpl
