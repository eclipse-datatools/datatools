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
 * $Id: DynamicFilterExpressionImpl.java,v 1.4 2009/10/23 20:17:26 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression;
import org.eclipse.datatools.connectivity.oda.design.FilterExpressionType;
import org.eclipse.datatools.connectivity.oda.design.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.design.ExpressionVariable;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dynamic Filter Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DynamicFilterExpressionImpl#getContext <em>Context</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DynamicFilterExpressionImpl#getDefaultType <em>Default Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 3.3 (DTP 1.8)
 */
public class DynamicFilterExpressionImpl extends FilterExpressionImpl implements
        DynamicFilterExpression
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getContext() <em>Context</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContext()
     * @generated
     * @ordered
     */
    protected AtomicExpressionContext m_context;

    /**
     * The cached value of the '{@link #getDefaultType() <em>Default Type</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultType()
     * @generated
     * @ordered
     */
    protected FilterExpressionType m_defaultType;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DynamicFilterExpressionImpl()
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
        return DesignPackage.Literals.DYNAMIC_FILTER_EXPRESSION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AtomicExpressionContext getContextGen()
    {
        return m_context;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression#getContext()
     * @generated NOT
     */
    public AtomicExpressionContext getContext()
    {
        if( getContextGen() == null )
            setContext( DesignFactory.eINSTANCE.createAtomicExpressionContext() );
        return getContextGen();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetContext(
            AtomicExpressionContext newContext, NotificationChain msgs )
    {
        AtomicExpressionContext oldContext = m_context;
        m_context = newContext;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.DYNAMIC_FILTER_EXPRESSION__CONTEXT,
                    oldContext, newContext );
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
    public void setContext( AtomicExpressionContext newContext )
    {
        if( newContext != m_context )
        {
            NotificationChain msgs = null;
            if( m_context != null )
                msgs = ((InternalEObject) m_context)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DYNAMIC_FILTER_EXPRESSION__CONTEXT,
                                null, msgs );
            if( newContext != null )
                msgs = ((InternalEObject) newContext)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DYNAMIC_FILTER_EXPRESSION__CONTEXT,
                                null, msgs );
            msgs = basicSetContext( newContext, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DYNAMIC_FILTER_EXPRESSION__CONTEXT,
                    newContext, newContext ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FilterExpressionType getDefaultType()
    {
        return m_defaultType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDefaultType(
            FilterExpressionType newDefaultType, NotificationChain msgs )
    {
        FilterExpressionType oldDefaultType = m_defaultType;
        m_defaultType = newDefaultType;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.DYNAMIC_FILTER_EXPRESSION__DEFAULT_TYPE,
                    oldDefaultType, newDefaultType );
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
    public void setDefaultType( FilterExpressionType newDefaultType )
    {
        if( newDefaultType != m_defaultType )
        {
            NotificationChain msgs = null;
            if( m_defaultType != null )
                msgs = ((InternalEObject) m_defaultType)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DYNAMIC_FILTER_EXPRESSION__DEFAULT_TYPE,
                                null, msgs );
            if( newDefaultType != null )
                msgs = ((InternalEObject) newDefaultType)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.DYNAMIC_FILTER_EXPRESSION__DEFAULT_TYPE,
                                null, msgs );
            msgs = basicSetDefaultType( newDefaultType, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DYNAMIC_FILTER_EXPRESSION__DEFAULT_TYPE,
                    newDefaultType, newDefaultType ) );
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
        case DesignPackage.DYNAMIC_FILTER_EXPRESSION__CONTEXT:
            return basicSetContext( null, msgs );
        case DesignPackage.DYNAMIC_FILTER_EXPRESSION__DEFAULT_TYPE:
            return basicSetDefaultType( null, msgs );
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
        case DesignPackage.DYNAMIC_FILTER_EXPRESSION__CONTEXT:
            return getContext();
        case DesignPackage.DYNAMIC_FILTER_EXPRESSION__DEFAULT_TYPE:
            return getDefaultType();
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
        case DesignPackage.DYNAMIC_FILTER_EXPRESSION__CONTEXT:
            setContext( (AtomicExpressionContext) newValue );
            return;
        case DesignPackage.DYNAMIC_FILTER_EXPRESSION__DEFAULT_TYPE:
            setDefaultType( (FilterExpressionType) newValue );
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
        case DesignPackage.DYNAMIC_FILTER_EXPRESSION__CONTEXT:
            setContext( (AtomicExpressionContext) null );
            return;
        case DesignPackage.DYNAMIC_FILTER_EXPRESSION__DEFAULT_TYPE:
            setDefaultType( (FilterExpressionType) null );
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
        case DesignPackage.DYNAMIC_FILTER_EXPRESSION__CONTEXT:
            return m_context != null;
        case DesignPackage.DYNAMIC_FILTER_EXPRESSION__DEFAULT_TYPE:
            return m_defaultType != null;
        }
        return super.eIsSet( featureID );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression#isOptional()
     * @generated NOT
     */
    public boolean isOptional()
    {
        return getContext().isOptional();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression#setIsOptional(boolean)
     * @generated NOT
     */
    public void setIsOptional( boolean isOptional )
    {
        getContext().setOptional( isOptional );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression#getContextVariable()
     * @generated NOT
     */
    public ExpressionVariable getContextVariable()
    {
        return getContext().getVariable();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression#setContextVariable(org.eclipse.datatools.connectivity.oda.design.ExpressionVariable)
     * @generated NOT
     */
    public void setContextVariable( ExpressionVariable variable )
    {
        getContext().setVariable( variable );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression#getContextArguments()
     * @generated NOT
     */
    public ExpressionArguments getContextArguments()
    {
        return getContext().getArguments();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression#setContextArguments(org.eclipse.datatools.connectivity.oda.design.ExpressionArguments)
     * @generated NOT
     */
    public void setContextArguments( ExpressionArguments arguments )
    {
        getContext().setArguments( arguments );
    }

} //DynamicFilterExpressionImpl
