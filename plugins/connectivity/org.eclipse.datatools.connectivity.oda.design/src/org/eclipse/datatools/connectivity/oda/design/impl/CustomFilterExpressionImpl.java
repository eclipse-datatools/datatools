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
 * $Id: CustomFilterExpressionImpl.java,v 1.4 2009/10/23 20:17:26 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext;
import org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
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
 * An implementation of the model object '<em><b>Custom Filter Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.CustomFilterExpressionImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.CustomFilterExpressionImpl#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 3.3 (DTP 1.8)
 */
public class CustomFilterExpressionImpl extends FilterExpressionImpl implements
        CustomFilterExpression
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected FilterExpressionType m_type;

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CustomFilterExpressionImpl()
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
        return DesignPackage.Literals.CUSTOM_FILTER_EXPRESSION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FilterExpressionType getType()
    {
        return m_type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetType( FilterExpressionType newType,
            NotificationChain msgs )
    {
        FilterExpressionType oldType = m_type;
        m_type = newType;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.CUSTOM_FILTER_EXPRESSION__TYPE, oldType,
                    newType );
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
    public void setType( FilterExpressionType newType )
    {
        if( newType != m_type )
        {
            NotificationChain msgs = null;
            if( m_type != null )
                msgs = ((InternalEObject) m_type).eInverseRemove( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.CUSTOM_FILTER_EXPRESSION__TYPE,
                        null, msgs );
            if( newType != null )
                msgs = ((InternalEObject) newType).eInverseAdd( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.CUSTOM_FILTER_EXPRESSION__TYPE,
                        null, msgs );
            msgs = basicSetType( newType, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.CUSTOM_FILTER_EXPRESSION__TYPE, newType,
                    newType ) );
    }

    /**
     * Backward compatible method.
     * @generated NOT
     */
    public String getDeclaringExtensionId()
    {
        return ( getType() != null ) ? getType().getDeclaringExtensionId() : null;
    }

    /**
     * Backward compatible method.
     * @generated NOT
     */
    public void setDeclaringExtensionId( String newDeclaringExtensionId )
    {
        if( getType() == null )
            setType( DesignFactory.eINSTANCE.createFilterExpressionType() );
        
        getType().setDeclaringExtensionId( newDeclaringExtensionId );
    }

    /**
     * Backward compatible method to get the custom expression id 
     * in the contained instance of expression type.
     * @generated NOT
     */
    public String getId()
    {
        return ( getType() != null ) ? getType().getId() : null;
    }

    /**
     * Backward compatible method to set the custom expression id 
     * in the contained instance of expression type.
     * @generated NOT
     */
    public void setId( String newId )
    {
        if( getType() == null )
            setType( DesignFactory.eINSTANCE.createFilterExpressionType() );
        
        getType().setId( newId );
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
     * @see org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression#getContext()
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
                    DesignPackage.CUSTOM_FILTER_EXPRESSION__CONTEXT,
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
                                        - DesignPackage.CUSTOM_FILTER_EXPRESSION__CONTEXT,
                                null, msgs );
            if( newContext != null )
                msgs = ((InternalEObject) newContext)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.CUSTOM_FILTER_EXPRESSION__CONTEXT,
                                null, msgs );
            msgs = basicSetContext( newContext, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.CUSTOM_FILTER_EXPRESSION__CONTEXT,
                    newContext, newContext ) );
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
        case DesignPackage.CUSTOM_FILTER_EXPRESSION__TYPE:
            return basicSetType( null, msgs );
        case DesignPackage.CUSTOM_FILTER_EXPRESSION__CONTEXT:
            return basicSetContext( null, msgs );
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
        case DesignPackage.CUSTOM_FILTER_EXPRESSION__TYPE:
            return getType();
        case DesignPackage.CUSTOM_FILTER_EXPRESSION__CONTEXT:
            return getContext();
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
        case DesignPackage.CUSTOM_FILTER_EXPRESSION__TYPE:
            setType( (FilterExpressionType) newValue );
            return;
        case DesignPackage.CUSTOM_FILTER_EXPRESSION__CONTEXT:
            setContext( (AtomicExpressionContext) newValue );
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
        case DesignPackage.CUSTOM_FILTER_EXPRESSION__TYPE:
            setType( (FilterExpressionType) null );
            return;
        case DesignPackage.CUSTOM_FILTER_EXPRESSION__CONTEXT:
            setContext( (AtomicExpressionContext) null );
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
        case DesignPackage.CUSTOM_FILTER_EXPRESSION__TYPE:
            return m_type != null;
        case DesignPackage.CUSTOM_FILTER_EXPRESSION__CONTEXT:
            return m_context != null;
        }
        return super.eIsSet( featureID );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression#isOptional()
     * @generated NOT
     */
    public boolean isOptional()
    {
        return getContext().isOptional();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression#setIsOptional(boolean)
     * @generated NOT
     */
    public void setIsOptional( boolean isOptional )
    {
        getContext().setOptional( isOptional );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression#getContextVariable()
     * @generated NOT
     */
    public ExpressionVariable getContextVariable()
    {
        return getContext().getVariable();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression#setContextVariable(org.eclipse.datatools.connectivity.oda.design.ExpressionVariable)
     * @generated NOT
     */
    public void setContextVariable( ExpressionVariable variable )
    {
        getContext().setVariable( variable );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression#getContextArguments()
     * @generated NOT
     */
    public ExpressionArguments getContextArguments()
    {
        return getContext().getArguments();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression#setContextArguments(org.eclipse.datatools.connectivity.oda.design.ExpressionArguments)
     * @generated NOT
     */
    public void setContextArguments( ExpressionArguments arguments )
    {
        getContext().setArguments( arguments );
    }

} //CustomFilterExpressionImpl
