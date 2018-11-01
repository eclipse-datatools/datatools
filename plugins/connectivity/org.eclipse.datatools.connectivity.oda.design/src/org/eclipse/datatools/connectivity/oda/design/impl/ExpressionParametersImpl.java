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
 * $Id: ExpressionParametersImpl.java,v 1.1 2009/03/03 07:42:07 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import java.util.Collection;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.ExpressionParameters;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression Parameters</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ExpressionParametersImpl#getParameterDefinitions <em>Parameter Definitions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 3.3 (DTP 1.8)
 */
public class ExpressionParametersImpl extends EObjectImpl implements
        ExpressionParameters
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getParameterDefinitions() <em>Parameter Definitions</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameterDefinitions()
     * @generated
     * @ordered
     */
    protected EList<ExpressionParameterDefinition> m_parameterDefinitions;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExpressionParametersImpl()
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
        return DesignPackage.Literals.EXPRESSION_PARAMETERS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ExpressionParameterDefinition> getParameterDefinitions()
    {
        if( m_parameterDefinitions == null )
        {
            m_parameterDefinitions = new EObjectContainmentEList<ExpressionParameterDefinition>(
                    ExpressionParameterDefinition.class, this,
                    DesignPackage.EXPRESSION_PARAMETERS__PARAMETER_DEFINITIONS );
        }
        return m_parameterDefinitions;
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
        case DesignPackage.EXPRESSION_PARAMETERS__PARAMETER_DEFINITIONS:
            return ((InternalEList<?>) getParameterDefinitions()).basicRemove(
                    otherEnd, msgs );
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
        case DesignPackage.EXPRESSION_PARAMETERS__PARAMETER_DEFINITIONS:
            return getParameterDefinitions();
        }
        return super.eGet( featureID, resolve, coreType );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet( int featureID, Object newValue )
    {
        switch( featureID )
        {
        case DesignPackage.EXPRESSION_PARAMETERS__PARAMETER_DEFINITIONS:
            getParameterDefinitions().clear();
            getParameterDefinitions()
                    .addAll(
                            (Collection<? extends ExpressionParameterDefinition>) newValue );
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
        case DesignPackage.EXPRESSION_PARAMETERS__PARAMETER_DEFINITIONS:
            getParameterDefinitions().clear();
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
        case DesignPackage.EXPRESSION_PARAMETERS__PARAMETER_DEFINITIONS:
            return m_parameterDefinitions != null
                    && !m_parameterDefinitions.isEmpty();
        }
        return super.eIsSet( featureID );
    }

} //ExpressionParametersImpl
