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
 * $Id: AtomicExpressionContextImpl.java,v 1.3 2009/04/14 02:13:18 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.design.ExpressionVariable;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Atomic Expression Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.AtomicExpressionContextImpl#isOptional <em>Optional</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.AtomicExpressionContextImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.AtomicExpressionContextImpl#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 3.3 (DTP 1.8)
 */
public class AtomicExpressionContextImpl extends EObjectImpl implements
        AtomicExpressionContext
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOptional()
     * @generated
     * @ordered
     */
    protected static final boolean OPTIONAL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOptional()
     * @generated
     * @ordered
     */
    protected boolean m_optional = OPTIONAL_EDEFAULT;

    /**
     * This is true if the Optional attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_optionalESet;

    /**
     * The cached value of the '{@link #getVariable() <em>Variable</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariable()
     * @generated
     * @ordered
     */
    protected ExpressionVariable m_variable;

    /**
     * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArguments()
     * @generated
     * @ordered
     */
    protected ExpressionArguments m_arguments;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AtomicExpressionContextImpl()
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
        return DesignPackage.Literals.ATOMIC_EXPRESSION_CONTEXT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isOptional()
    {
        return m_optional;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOptional( boolean newOptional )
    {
        boolean oldOptional = m_optional;
        m_optional = newOptional;
        boolean oldOptionalESet = m_optionalESet;
        m_optionalESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.ATOMIC_EXPRESSION_CONTEXT__OPTIONAL,
                    oldOptional, m_optional, !oldOptionalESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetOptional()
    {
        boolean oldOptional = m_optional;
        boolean oldOptionalESet = m_optionalESet;
        m_optional = OPTIONAL_EDEFAULT;
        m_optionalESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.ATOMIC_EXPRESSION_CONTEXT__OPTIONAL,
                    oldOptional, OPTIONAL_EDEFAULT, oldOptionalESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetOptional()
    {
        return m_optionalESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExpressionVariable getVariable()
    {
        return m_variable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetVariable( ExpressionVariable newVariable,
            NotificationChain msgs )
    {
        ExpressionVariable oldVariable = m_variable;
        m_variable = newVariable;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE,
                    oldVariable, newVariable );
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
    public void setVariable( ExpressionVariable newVariable )
    {
        if( newVariable != m_variable )
        {
            NotificationChain msgs = null;
            if( m_variable != null )
                msgs = ((InternalEObject) m_variable)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE,
                                null, msgs );
            if( newVariable != null )
                msgs = ((InternalEObject) newVariable)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE,
                                null, msgs );
            msgs = basicSetVariable( newVariable, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE,
                    newVariable, newVariable ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExpressionArguments getArguments()
    {
        return m_arguments;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetArguments(
            ExpressionArguments newArguments, NotificationChain msgs )
    {
        ExpressionArguments oldArguments = m_arguments;
        m_arguments = newArguments;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS,
                    oldArguments, newArguments );
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
    public void setArguments( ExpressionArguments newArguments )
    {
        if( newArguments != m_arguments )
        {
            NotificationChain msgs = null;
            if( m_arguments != null )
                msgs = ((InternalEObject) m_arguments)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS,
                                null, msgs );
            if( newArguments != null )
                msgs = ((InternalEObject) newArguments)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS,
                                null, msgs );
            msgs = basicSetArguments( newArguments, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS,
                    newArguments, newArguments ) );
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
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE:
            return basicSetVariable( null, msgs );
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS:
            return basicSetArguments( null, msgs );
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
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__OPTIONAL:
            return isOptional() ? Boolean.TRUE : Boolean.FALSE;
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE:
            return getVariable();
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS:
            return getArguments();
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
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__OPTIONAL:
            setOptional( ((Boolean) newValue).booleanValue() );
            return;
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE:
            setVariable( (ExpressionVariable) newValue );
            return;
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS:
            setArguments( (ExpressionArguments) newValue );
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
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__OPTIONAL:
            unsetOptional();
            return;
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE:
            setVariable( (ExpressionVariable) null );
            return;
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS:
            setArguments( (ExpressionArguments) null );
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
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__OPTIONAL:
            return isSetOptional();
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE:
            return m_variable != null;
        case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS:
            return m_arguments != null;
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
        result.append( " (optional: " ); //$NON-NLS-1$
        if( m_optionalESet )
            result.append( m_optional );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ')' );
        return result.toString();
    }

} //AtomicExpressionContextImpl
