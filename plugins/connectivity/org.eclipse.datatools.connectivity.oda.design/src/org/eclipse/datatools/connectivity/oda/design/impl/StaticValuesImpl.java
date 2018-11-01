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
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import java.util.Collection;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.StaticValues;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Static Values</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.StaticValuesImpl#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 3.2 (DTP 1.7)
 */
public class StaticValuesImpl extends EObjectImpl implements StaticValues
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getValues() <em>Values</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValues()
     * @generated
     * @ordered
     */
    protected EList<Object> m_values;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected StaticValuesImpl()
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
        return DesignPackage.Literals.STATIC_VALUES;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Object> getValues()
    {
        if( m_values == null )
        {
            m_values = new EDataTypeEList<Object>( Object.class, this,
                    DesignPackage.STATIC_VALUES__VALUES );
        }
        return m_values;
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
        case DesignPackage.STATIC_VALUES__VALUES:
            return getValues();
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
        case DesignPackage.STATIC_VALUES__VALUES:
            getValues().clear();
            getValues().addAll( (Collection<? extends Object>) newValue );
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
        case DesignPackage.STATIC_VALUES__VALUES:
            getValues().clear();
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
        case DesignPackage.STATIC_VALUES__VALUES:
            return m_values != null && !m_values.isEmpty();
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
        result.append( " (values: " ); //$NON-NLS-1$
        result.append( m_values );
        result.append( ')' );
        return result.toString();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.StaticValuesType#isEmpty()
     * @generated NOT
     */
    public boolean isEmpty()
    {
        return getValues().isEmpty();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.StaticValuesType#count()
     * @generated NOT
     */
    public int count()
    {
        return getValues().size();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.StaticValuesType#add(java.lang.Object)
     * @generated NOT
     */
    public void add( Object value )
    {
        getValues().add( value );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.StaticValuesType#clear()
     * @generated NOT
     */
    public void clear()
    {
        getValues().clear();
    }

} //StaticValuesImpl
