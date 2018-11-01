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
 * $Id: ExpressionArgumentsImpl.java,v 1.1 2009/03/03 07:42:07 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.ExpressionParameters;
import org.eclipse.datatools.connectivity.oda.design.ParameterDefinition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression Arguments</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ExpressionArgumentsImpl#getExpressionParameters <em>Expression Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 3.3 (DTP 1.8)
 */
public class ExpressionArgumentsImpl extends EObjectImpl implements
        ExpressionArguments
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getExpressionParameters() <em>Expression Parameters</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExpressionParameters()
     * @generated
     * @ordered
     */
    protected ExpressionParameters m_expressionParameters;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExpressionArgumentsImpl()
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
        return DesignPackage.Literals.EXPRESSION_ARGUMENTS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExpressionParameters getExpressionParametersGen()
    {
        return m_expressionParameters;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ExpressionArguments#getExpressionParameters()
     * @generated NOT
     */
    public ExpressionParameters getExpressionParameters()
    {
        if( getExpressionParametersGen() == null )
            setExpressionParameters( DesignFactory.eINSTANCE.createExpressionParameters() );

        return getExpressionParametersGen();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetExpressionParameters(
            ExpressionParameters newExpressionParameters, NotificationChain msgs )
    {
        ExpressionParameters oldExpressionParameters = m_expressionParameters;
        m_expressionParameters = newExpressionParameters;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.EXPRESSION_ARGUMENTS__EXPRESSION_PARAMETERS,
                    oldExpressionParameters, newExpressionParameters );
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
    public void setExpressionParameters(
            ExpressionParameters newExpressionParameters )
    {
        if( newExpressionParameters != m_expressionParameters )
        {
            NotificationChain msgs = null;
            if( m_expressionParameters != null )
                msgs = ((InternalEObject) m_expressionParameters)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.EXPRESSION_ARGUMENTS__EXPRESSION_PARAMETERS,
                                null, msgs );
            if( newExpressionParameters != null )
                msgs = ((InternalEObject) newExpressionParameters)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.EXPRESSION_ARGUMENTS__EXPRESSION_PARAMETERS,
                                null, msgs );
            msgs = basicSetExpressionParameters( newExpressionParameters, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.EXPRESSION_ARGUMENTS__EXPRESSION_PARAMETERS,
                    newExpressionParameters, newExpressionParameters ) );
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
        case DesignPackage.EXPRESSION_ARGUMENTS__EXPRESSION_PARAMETERS:
            return basicSetExpressionParameters( null, msgs );
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
        case DesignPackage.EXPRESSION_ARGUMENTS__EXPRESSION_PARAMETERS:
            return getExpressionParameters();
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
        case DesignPackage.EXPRESSION_ARGUMENTS__EXPRESSION_PARAMETERS:
            setExpressionParameters( (ExpressionParameters) newValue );
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
        case DesignPackage.EXPRESSION_ARGUMENTS__EXPRESSION_PARAMETERS:
            setExpressionParameters( (ExpressionParameters) null );
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
        case DesignPackage.EXPRESSION_ARGUMENTS__EXPRESSION_PARAMETERS:
            return m_expressionParameters != null;
        }
        return super.eIsSet( featureID );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ExpressionArguments#getExpressionParameterDefinitions()
     * @generated NOT
     */
    public EList<ExpressionParameterDefinition> getExpressionParameterDefinitions()
    {
        return getExpressionParameters().getParameterDefinitions();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ExpressionArguments#addStaticParameter(java.lang.Object)
     * @generated NOT
     */
    public ExpressionParameterDefinition addStaticParameter( Object aStaticValue )
    {
        ExpressionParameterDefinition exprParam = DesignFactory.eINSTANCE.createExpressionParameterDefinition();
        exprParam.addStaticValue( aStaticValue );
        
        getExpressionParameterDefinitions().add( exprParam );
        return exprParam;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ExpressionArguments#addDynamicParameter(org.eclipse.datatools.connectivity.oda.design.ParameterDefinition)
     * @generated NOT
     */
    public ExpressionParameterDefinition addDynamicParameter( ParameterDefinition inputParam )
    {
        ExpressionParameterDefinition exprParam = DesignFactory.eINSTANCE.createExpressionParameterDefinition();
        exprParam.setDynamicInputParameter( inputParam );

        getExpressionParameterDefinitions().add( exprParam );
        return exprParam;
    }

} //ExpressionArgumentsImpl
