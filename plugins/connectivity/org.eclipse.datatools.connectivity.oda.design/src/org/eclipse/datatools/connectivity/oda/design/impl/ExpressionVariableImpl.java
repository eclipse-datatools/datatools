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
 * $Id: ExpressionVariableImpl.java,v 1.1 2009/03/03 07:42:07 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.design.ExpressionVariableType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ExpressionVariableImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ExpressionVariableImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ExpressionVariableImpl#getNativeDataTypeCode <em>Native Data Type Code</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 3.3 (DTP 1.8)
 */
public class ExpressionVariableImpl extends EObjectImpl implements
        ExpressionVariable
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final ExpressionVariableType TYPE_EDEFAULT = ExpressionVariableType.RESULT_SET_COLUMN;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected ExpressionVariableType m_type = TYPE_EDEFAULT;

    /**
     * This is true if the Type attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_typeESet;

    /**
     * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIdentifier()
     * @generated
     * @ordered
     */
    protected static final String IDENTIFIER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIdentifier()
     * @generated
     * @ordered
     */
    protected String m_identifier = IDENTIFIER_EDEFAULT;

    /**
     * The default value of the '{@link #getNativeDataTypeCode() <em>Native Data Type Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNativeDataTypeCode()
     * @generated
     * @ordered
     */
    protected static final int NATIVE_DATA_TYPE_CODE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getNativeDataTypeCode() <em>Native Data Type Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNativeDataTypeCode()
     * @generated
     * @ordered
     */
    protected int m_nativeDataTypeCode = NATIVE_DATA_TYPE_CODE_EDEFAULT;

    /**
     * This is true if the Native Data Type Code attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_nativeDataTypeCodeESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExpressionVariableImpl()
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
        return DesignPackage.Literals.EXPRESSION_VARIABLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExpressionVariableType getType()
    {
        return m_type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType( ExpressionVariableType newType )
    {
        ExpressionVariableType oldType = m_type;
        m_type = newType == null ? TYPE_EDEFAULT : newType;
        boolean oldTypeESet = m_typeESet;
        m_typeESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.EXPRESSION_VARIABLE__TYPE, oldType, m_type,
                    !oldTypeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetType()
    {
        ExpressionVariableType oldType = m_type;
        boolean oldTypeESet = m_typeESet;
        m_type = TYPE_EDEFAULT;
        m_typeESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.EXPRESSION_VARIABLE__TYPE, oldType,
                    TYPE_EDEFAULT, oldTypeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetType()
    {
        return m_typeESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getIdentifier()
    {
        return m_identifier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIdentifier( String newIdentifier )
    {
        String oldIdentifier = m_identifier;
        m_identifier = newIdentifier;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.EXPRESSION_VARIABLE__IDENTIFIER,
                    oldIdentifier, m_identifier ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getNativeDataTypeCode()
    {
        return m_nativeDataTypeCode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNativeDataTypeCode( int newNativeDataTypeCode )
    {
        int oldNativeDataTypeCode = m_nativeDataTypeCode;
        m_nativeDataTypeCode = newNativeDataTypeCode;
        boolean oldNativeDataTypeCodeESet = m_nativeDataTypeCodeESet;
        m_nativeDataTypeCodeESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.EXPRESSION_VARIABLE__NATIVE_DATA_TYPE_CODE,
                    oldNativeDataTypeCode, m_nativeDataTypeCode,
                    !oldNativeDataTypeCodeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetNativeDataTypeCode()
    {
        int oldNativeDataTypeCode = m_nativeDataTypeCode;
        boolean oldNativeDataTypeCodeESet = m_nativeDataTypeCodeESet;
        m_nativeDataTypeCode = NATIVE_DATA_TYPE_CODE_EDEFAULT;
        m_nativeDataTypeCodeESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.EXPRESSION_VARIABLE__NATIVE_DATA_TYPE_CODE,
                    oldNativeDataTypeCode, NATIVE_DATA_TYPE_CODE_EDEFAULT,
                    oldNativeDataTypeCodeESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetNativeDataTypeCode()
    {
        return m_nativeDataTypeCodeESet;
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
        case DesignPackage.EXPRESSION_VARIABLE__TYPE:
            return getType();
        case DesignPackage.EXPRESSION_VARIABLE__IDENTIFIER:
            return getIdentifier();
        case DesignPackage.EXPRESSION_VARIABLE__NATIVE_DATA_TYPE_CODE:
            return new Integer( getNativeDataTypeCode() );
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
        case DesignPackage.EXPRESSION_VARIABLE__TYPE:
            setType( (ExpressionVariableType) newValue );
            return;
        case DesignPackage.EXPRESSION_VARIABLE__IDENTIFIER:
            setIdentifier( (String) newValue );
            return;
        case DesignPackage.EXPRESSION_VARIABLE__NATIVE_DATA_TYPE_CODE:
            setNativeDataTypeCode( ((Integer) newValue).intValue() );
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
        case DesignPackage.EXPRESSION_VARIABLE__TYPE:
            unsetType();
            return;
        case DesignPackage.EXPRESSION_VARIABLE__IDENTIFIER:
            setIdentifier( IDENTIFIER_EDEFAULT );
            return;
        case DesignPackage.EXPRESSION_VARIABLE__NATIVE_DATA_TYPE_CODE:
            unsetNativeDataTypeCode();
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
        case DesignPackage.EXPRESSION_VARIABLE__TYPE:
            return isSetType();
        case DesignPackage.EXPRESSION_VARIABLE__IDENTIFIER:
            return IDENTIFIER_EDEFAULT == null ? m_identifier != null
                    : !IDENTIFIER_EDEFAULT.equals( m_identifier );
        case DesignPackage.EXPRESSION_VARIABLE__NATIVE_DATA_TYPE_CODE:
            return isSetNativeDataTypeCode();
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
        result.append( " (type: " ); //$NON-NLS-1$
        if( m_typeESet )
            result.append( m_type );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ", identifier: " ); //$NON-NLS-1$
        result.append( m_identifier );
        result.append( ", nativeDataTypeCode: " ); //$NON-NLS-1$
        if( m_nativeDataTypeCodeESet )
            result.append( m_nativeDataTypeCode );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ')' );
        return result.toString();
    }

} //ExpressionVariableImpl
