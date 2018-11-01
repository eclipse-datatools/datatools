/**
 *************************************************************************
 * Copyright (c) 2010 Actuate Corporation.
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

import org.eclipse.datatools.connectivity.oda.design.CustomData;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.CustomDataImpl#getProviderId <em>Provider Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.CustomDataImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.CustomDataImpl#getDisplayValue <em>Display Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomDataImpl extends EObjectImpl implements CustomData
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getProviderId() <em>Provider Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProviderId()
     * @generated
     * @ordered
     */
    protected static final String PROVIDER_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getProviderId() <em>Provider Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProviderId()
     * @generated
     * @ordered
     */
    protected String m_providerId = PROVIDER_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected static final Object VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected Object m_value = VALUE_EDEFAULT;

    /**
     * The default value of the '{@link #getDisplayValue() <em>Display Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplayValue()
     * @generated
     * @ordered
     */
    protected static final String DISPLAY_VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDisplayValue() <em>Display Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplayValue()
     * @generated
     * @ordered
     */
    protected String m_displayValue = DISPLAY_VALUE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CustomDataImpl()
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
        return DesignPackage.Literals.CUSTOM_DATA;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getProviderId()
    {
        return m_providerId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setProviderId( String newProviderId )
    {
        String oldProviderId = m_providerId;
        m_providerId = newProviderId;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.CUSTOM_DATA__PROVIDER_ID, oldProviderId,
                    m_providerId ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getValue()
    {
        return m_value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValue( Object newValue )
    {
        Object oldValue = m_value;
        m_value = newValue;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.CUSTOM_DATA__VALUE, oldValue, m_value ) );
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.CustomData#getDisplayValue()
     * @generated NOT
     */
    public String getDisplayValue()
    {
        // if the optional display value is not specified, use the String representation of the value
        String displayValue = getDisplayValueGen();
        if( displayValue == null && getValue() != null )
            return getValue().toString();
        return displayValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDisplayValueGen()
    {
        return m_displayValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDisplayValue( String newDisplayValue )
    {
        String oldDisplayValue = m_displayValue;
        m_displayValue = newDisplayValue;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.CUSTOM_DATA__DISPLAY_VALUE, oldDisplayValue,
                    m_displayValue ) );
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
        case DesignPackage.CUSTOM_DATA__PROVIDER_ID:
            return getProviderId();
        case DesignPackage.CUSTOM_DATA__VALUE:
            return getValue();
        case DesignPackage.CUSTOM_DATA__DISPLAY_VALUE:
            return getDisplayValue();
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
        case DesignPackage.CUSTOM_DATA__PROVIDER_ID:
            setProviderId( (String) newValue );
            return;
        case DesignPackage.CUSTOM_DATA__VALUE:
            setValue( newValue );
            return;
        case DesignPackage.CUSTOM_DATA__DISPLAY_VALUE:
            setDisplayValue( (String) newValue );
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
        case DesignPackage.CUSTOM_DATA__PROVIDER_ID:
            setProviderId( PROVIDER_ID_EDEFAULT );
            return;
        case DesignPackage.CUSTOM_DATA__VALUE:
            setValue( VALUE_EDEFAULT );
            return;
        case DesignPackage.CUSTOM_DATA__DISPLAY_VALUE:
            setDisplayValue( DISPLAY_VALUE_EDEFAULT );
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
        case DesignPackage.CUSTOM_DATA__PROVIDER_ID:
            return PROVIDER_ID_EDEFAULT == null ? m_providerId != null
                    : !PROVIDER_ID_EDEFAULT.equals( m_providerId );
        case DesignPackage.CUSTOM_DATA__VALUE:
            return VALUE_EDEFAULT == null ? m_value != null : !VALUE_EDEFAULT
                    .equals( m_value );
        case DesignPackage.CUSTOM_DATA__DISPLAY_VALUE:
            return DISPLAY_VALUE_EDEFAULT == null ? m_displayValue != null
                    : !DISPLAY_VALUE_EDEFAULT.equals( m_displayValue );
        }
        return super.eIsSet( featureID );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public String toString()
    {
        if( eIsProxy() )
            return super.toString();

        //        StringBuffer result = new StringBuffer( super.toString() );
        //        result.append( " (value: " ); //$NON-NLS-1$
        StringBuffer result = new StringBuffer( "CustomData value: " ); //$NON-NLS-1$
        result.append( m_value );
        result.append( ", displayValue: " ); //$NON-NLS-1$
        result.append( m_displayValue );
        //        result.append( ')' );
        return result.toString();
    }

} //CustomDataImpl
