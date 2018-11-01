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
 * $Id: FilterExpressionTypeImpl.java,v 1.1 2009/10/23 20:17:26 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.FilterExpressionType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Filter Expression Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.FilterExpressionTypeImpl#getDeclaringExtensionId <em>Declaring Extension Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.FilterExpressionTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 3.3 (DTP 1.8)
 */
public class FilterExpressionTypeImpl extends EObjectImpl implements
        FilterExpressionType
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getDeclaringExtensionId() <em>Declaring Extension Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDeclaringExtensionId()
     * @generated
     * @ordered
     */
    protected static final String DECLARING_EXTENSION_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDeclaringExtensionId() <em>Declaring Extension Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDeclaringExtensionId()
     * @generated
     * @ordered
     */
    protected String m_declaringExtensionId = DECLARING_EXTENSION_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String m_id = ID_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FilterExpressionTypeImpl()
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
        return DesignPackage.Literals.FILTER_EXPRESSION_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDeclaringExtensionId()
    {
        return m_declaringExtensionId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDeclaringExtensionId( String newDeclaringExtensionId )
    {
        String oldDeclaringExtensionId = m_declaringExtensionId;
        m_declaringExtensionId = newDeclaringExtensionId;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.FILTER_EXPRESSION_TYPE__DECLARING_EXTENSION_ID,
                    oldDeclaringExtensionId, m_declaringExtensionId ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getId()
    {
        return m_id;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setId( String newId )
    {
        String oldId = m_id;
        m_id = newId;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.FILTER_EXPRESSION_TYPE__ID, oldId, m_id ) );
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
        case DesignPackage.FILTER_EXPRESSION_TYPE__DECLARING_EXTENSION_ID:
            return getDeclaringExtensionId();
        case DesignPackage.FILTER_EXPRESSION_TYPE__ID:
            return getId();
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
        case DesignPackage.FILTER_EXPRESSION_TYPE__DECLARING_EXTENSION_ID:
            setDeclaringExtensionId( (String) newValue );
            return;
        case DesignPackage.FILTER_EXPRESSION_TYPE__ID:
            setId( (String) newValue );
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
        case DesignPackage.FILTER_EXPRESSION_TYPE__DECLARING_EXTENSION_ID:
            setDeclaringExtensionId( DECLARING_EXTENSION_ID_EDEFAULT );
            return;
        case DesignPackage.FILTER_EXPRESSION_TYPE__ID:
            setId( ID_EDEFAULT );
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
        case DesignPackage.FILTER_EXPRESSION_TYPE__DECLARING_EXTENSION_ID:
            return DECLARING_EXTENSION_ID_EDEFAULT == null ? m_declaringExtensionId != null
                    : !DECLARING_EXTENSION_ID_EDEFAULT
                            .equals( m_declaringExtensionId );
        case DesignPackage.FILTER_EXPRESSION_TYPE__ID:
            return ID_EDEFAULT == null ? m_id != null : !ID_EDEFAULT
                    .equals( m_id );
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
        result.append( " (declaringExtensionId: " ); //$NON-NLS-1$
        result.append( m_declaringExtensionId );
        result.append( ", id: " ); //$NON-NLS-1$
        result.append( m_id );
        result.append( ')' );
        return result.toString();
    }

} //FilterExpressionTypeImpl
