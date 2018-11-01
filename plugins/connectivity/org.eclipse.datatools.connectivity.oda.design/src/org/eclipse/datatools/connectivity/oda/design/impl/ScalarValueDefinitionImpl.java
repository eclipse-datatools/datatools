/**
 *************************************************************************
 * Copyright (c) 2005, 2010 Actuate Corporation.
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
 * $Id: ScalarValueDefinitionImpl.java,v 1.4 2010/02/17 02:20:38 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scalar Value Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ScalarValueDefinitionImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ScalarValueDefinitionImpl#getDisplayName <em>Display Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScalarValueDefinitionImpl extends EObjectImpl implements
        ScalarValueDefinition
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected static final String VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected String m_value = VALUE_EDEFAULT;

    /**
     * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplayName()
     * @generated
     * @ordered
     */
    protected static final String DISPLAY_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplayName()
     * @generated
     * @ordered
     */
    protected String m_displayName = DISPLAY_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ScalarValueDefinitionImpl()
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
        return DesignPackage.Literals.SCALAR_VALUE_DEFINITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getValue()
    {
        return m_value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValue( String newValue )
    {
        String oldValue = m_value;
        m_value = newValue;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.SCALAR_VALUE_DEFINITION__VALUE, oldValue,
                    m_value ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition#getDisplayName()
     * @generated NOT
     */
    public String getDisplayName()
    {
        return DesignUtil.getDefaultResourceString( getDisplayNameGen() );
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getDisplayNameGen()
    {
        return m_displayName;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition#setDisplayName(java.lang.String)
     * @generated NOT
     */
    public void setDisplayName( String newDisplayName )
    {
        String newAttrValue = DesignUtil.addDefaultToResourceAttribute( newDisplayName, getDisplayNameGen() );
        setDisplayNameGen( newAttrValue );
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setDisplayNameGen( String newDisplayName )
    {
        String oldDisplayName = m_displayName;
        m_displayName = newDisplayName;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.SCALAR_VALUE_DEFINITION__DISPLAY_NAME,
                    oldDisplayName, m_displayName ) );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition#getDisplayNameKey()
     * @generated NOT
     */
    public String getDisplayNameKey()
    {
        return DesignUtil.getResourceKey( getDisplayNameGen() );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition#setDisplayNameKey(java.lang.String)
     * @generated NOT
     */
    public void setDisplayNameKey( String newDisplayNameKey )
    {
        String newAttrValue = DesignUtil.addKeyToResourceAttribute( newDisplayNameKey, getDisplayNameGen() );
        setDisplayNameGen( newAttrValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public Object eGet( int featureID, boolean resolve, boolean coreType )
    {
        switch( featureID )
        {
        case DesignPackage.SCALAR_VALUE_DEFINITION__VALUE:
            return getValue();
        case DesignPackage.SCALAR_VALUE_DEFINITION__DISPLAY_NAME:
            return getDisplayNameGen();
        }
        return super.eGet( featureID, resolve, coreType );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public void eSet( int featureID, Object newValue )
    {
        switch( featureID )
        {
        case DesignPackage.SCALAR_VALUE_DEFINITION__VALUE:
            setValue( (String) newValue );
            return;
        case DesignPackage.SCALAR_VALUE_DEFINITION__DISPLAY_NAME:
            setDisplayNameGen( (String) newValue );
            return;
        }
        super.eSet( featureID, newValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public void eUnset( int featureID )
    {
        switch( featureID )
        {
        case DesignPackage.SCALAR_VALUE_DEFINITION__VALUE:
            setValue( VALUE_EDEFAULT );
            return;
        case DesignPackage.SCALAR_VALUE_DEFINITION__DISPLAY_NAME:
            setDisplayNameGen( DISPLAY_NAME_EDEFAULT );
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
        case DesignPackage.SCALAR_VALUE_DEFINITION__VALUE:
            return VALUE_EDEFAULT == null ? m_value != null : !VALUE_EDEFAULT
                    .equals( m_value );
        case DesignPackage.SCALAR_VALUE_DEFINITION__DISPLAY_NAME:
            return DISPLAY_NAME_EDEFAULT == null ? m_displayName != null
                    : !DISPLAY_NAME_EDEFAULT.equals( m_displayName );
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
        result.append( " (value: " ); //$NON-NLS-1$
        result.append( m_value );
        result.append( ", displayName: " ); //$NON-NLS-1$
        result.append( m_displayName );
        result.append( ')' );
        return result.toString();
    }

} //ScalarValueDefinitionImpl
