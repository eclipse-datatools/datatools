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
 * $Id: ExpressionParameterDefinitionImpl.java,v 1.1 2009/03/03 07:42:07 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.ParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.StaticValues;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression Parameter Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ExpressionParameterDefinitionImpl#getStaticValues <em>Static Values</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ExpressionParameterDefinitionImpl#getDynamicInputParameter <em>Dynamic Input Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 3.3 (DTP 1.8)
 */
public class ExpressionParameterDefinitionImpl extends EObjectImpl implements
        ExpressionParameterDefinition
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getStaticValues() <em>Static Values</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStaticValues()
     * @generated
     * @ordered
     */
    protected StaticValues m_staticValues;

    /**
     * The cached value of the '{@link #getDynamicInputParameter() <em>Dynamic Input Parameter</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDynamicInputParameter()
     * @generated
     * @ordered
     */
    protected ParameterDefinition m_dynamicInputParameter;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExpressionParameterDefinitionImpl()
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
        return DesignPackage.Literals.EXPRESSION_PARAMETER_DEFINITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StaticValues getStaticValues()
    {
        return m_staticValues;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStaticValues(
            StaticValues newStaticValues, NotificationChain msgs )
    {
        StaticValues oldStaticValues = m_staticValues;
        m_staticValues = newStaticValues;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.EXPRESSION_PARAMETER_DEFINITION__STATIC_VALUES,
                    oldStaticValues, newStaticValues );
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
    public void setStaticValues( StaticValues newStaticValues )
    {
        if( newStaticValues != m_staticValues )
        {
            NotificationChain msgs = null;
            if( m_staticValues != null )
                msgs = ((InternalEObject) m_staticValues)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.EXPRESSION_PARAMETER_DEFINITION__STATIC_VALUES,
                                null, msgs );
            if( newStaticValues != null )
                msgs = ((InternalEObject) newStaticValues)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.EXPRESSION_PARAMETER_DEFINITION__STATIC_VALUES,
                                null, msgs );
            msgs = basicSetStaticValues( newStaticValues, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.EXPRESSION_PARAMETER_DEFINITION__STATIC_VALUES,
                    newStaticValues, newStaticValues ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParameterDefinition getDynamicInputParameter()
    {
        return m_dynamicInputParameter;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDynamicInputParameter(
            ParameterDefinition newDynamicInputParameter, NotificationChain msgs )
    {
        ParameterDefinition oldDynamicInputParameter = m_dynamicInputParameter;
        m_dynamicInputParameter = newDynamicInputParameter;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.EXPRESSION_PARAMETER_DEFINITION__DYNAMIC_INPUT_PARAMETER,
                    oldDynamicInputParameter, newDynamicInputParameter );
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
    public void setDynamicInputParameter(
            ParameterDefinition newDynamicInputParameter )
    {
        if( newDynamicInputParameter != m_dynamicInputParameter )
        {
            NotificationChain msgs = null;
            if( m_dynamicInputParameter != null )
                msgs = ((InternalEObject) m_dynamicInputParameter)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.EXPRESSION_PARAMETER_DEFINITION__DYNAMIC_INPUT_PARAMETER,
                                null, msgs );
            if( newDynamicInputParameter != null )
                msgs = ((InternalEObject) newDynamicInputParameter)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.EXPRESSION_PARAMETER_DEFINITION__DYNAMIC_INPUT_PARAMETER,
                                null, msgs );
            msgs = basicSetDynamicInputParameter( newDynamicInputParameter,
                    msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.EXPRESSION_PARAMETER_DEFINITION__DYNAMIC_INPUT_PARAMETER,
                    newDynamicInputParameter, newDynamicInputParameter ) );
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
        case DesignPackage.EXPRESSION_PARAMETER_DEFINITION__STATIC_VALUES:
            return basicSetStaticValues( null, msgs );
        case DesignPackage.EXPRESSION_PARAMETER_DEFINITION__DYNAMIC_INPUT_PARAMETER:
            return basicSetDynamicInputParameter( null, msgs );
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
        case DesignPackage.EXPRESSION_PARAMETER_DEFINITION__STATIC_VALUES:
            return getStaticValues();
        case DesignPackage.EXPRESSION_PARAMETER_DEFINITION__DYNAMIC_INPUT_PARAMETER:
            return getDynamicInputParameter();
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
        case DesignPackage.EXPRESSION_PARAMETER_DEFINITION__STATIC_VALUES:
            setStaticValues( (StaticValues) newValue );
            return;
        case DesignPackage.EXPRESSION_PARAMETER_DEFINITION__DYNAMIC_INPUT_PARAMETER:
            setDynamicInputParameter( (ParameterDefinition) newValue );
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
        case DesignPackage.EXPRESSION_PARAMETER_DEFINITION__STATIC_VALUES:
            setStaticValues( (StaticValues) null );
            return;
        case DesignPackage.EXPRESSION_PARAMETER_DEFINITION__DYNAMIC_INPUT_PARAMETER:
            setDynamicInputParameter( (ParameterDefinition) null );
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
        case DesignPackage.EXPRESSION_PARAMETER_DEFINITION__STATIC_VALUES:
            return m_staticValues != null;
        case DesignPackage.EXPRESSION_PARAMETER_DEFINITION__DYNAMIC_INPUT_PARAMETER:
            return m_dynamicInputParameter != null;
        }
        return super.eIsSet( featureID );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition#hasDynamicInputParameter()
     * @generated NOT
     */
    public boolean hasDynamicInputParameter()
    {
        return getDynamicInputParameter() != null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition#hasEffectiveStaticValues()
     * @generated NOT
     */
    public boolean hasEffectiveStaticValues()
    {
        return ( hasStaticValues() && ! hasDynamicInputParameter() );
    }

    private boolean hasStaticValues()
    {
        return ( getStaticValues() != null && ! getStaticValues().isEmpty() );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition#getEffectiveStaticValueCount()
     * @generated NOT
     */
    public int getEffectiveStaticValueCount()
    {
        if( ! hasEffectiveStaticValues() )
            return 0;
        return getStaticValues().count();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition#addStaticValue(java.lang.Object)
     * @generated NOT
     */
    public void addStaticValue( Object aValue )
    {
        StaticValues staticValueList = getStaticValues();
        if( staticValueList == null )
        {
            staticValueList = DesignFactory.eINSTANCE.createStaticValues();
            setStaticValues( staticValueList );
        }
        staticValueList.add( aValue );
    }

} //ExpressionParameterDefinitionImpl
