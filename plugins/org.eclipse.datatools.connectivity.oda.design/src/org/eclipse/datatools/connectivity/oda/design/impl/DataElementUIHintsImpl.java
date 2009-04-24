/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: DataElementUIHintsImpl.java,v 1.2 2007/04/11 02:59:52 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DataElementUIHints;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Element UI Hints</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataElementUIHintsImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataElementUIHintsImpl#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataElementUIHintsImpl extends EObjectImpl implements
        DataElementUIHints
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

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
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String m_description = DESCRIPTION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DataElementUIHintsImpl()
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
        return DesignPackage.Literals.DATA_ELEMENT_UI_HINTS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDisplayName()
    {
        return m_displayName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDisplayName( String newDisplayName )
    {
        String oldDisplayName = m_displayName;
        m_displayName = newDisplayName;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_ELEMENT_UI_HINTS__DISPLAY_NAME,
                    oldDisplayName, m_displayName ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDescription()
    {
        return m_description;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDescription( String newDescription )
    {
        String oldDescription = m_description;
        m_description = newDescription;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_ELEMENT_UI_HINTS__DESCRIPTION,
                    oldDescription, m_description ) );
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
        case DesignPackage.DATA_ELEMENT_UI_HINTS__DISPLAY_NAME:
            return getDisplayName();
        case DesignPackage.DATA_ELEMENT_UI_HINTS__DESCRIPTION:
            return getDescription();
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
        case DesignPackage.DATA_ELEMENT_UI_HINTS__DISPLAY_NAME:
            setDisplayName( (String) newValue );
            return;
        case DesignPackage.DATA_ELEMENT_UI_HINTS__DESCRIPTION:
            setDescription( (String) newValue );
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
        case DesignPackage.DATA_ELEMENT_UI_HINTS__DISPLAY_NAME:
            setDisplayName( DISPLAY_NAME_EDEFAULT );
            return;
        case DesignPackage.DATA_ELEMENT_UI_HINTS__DESCRIPTION:
            setDescription( DESCRIPTION_EDEFAULT );
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
        case DesignPackage.DATA_ELEMENT_UI_HINTS__DISPLAY_NAME:
            return DISPLAY_NAME_EDEFAULT == null ? m_displayName != null
                    : !DISPLAY_NAME_EDEFAULT.equals( m_displayName );
        case DesignPackage.DATA_ELEMENT_UI_HINTS__DESCRIPTION:
            return DESCRIPTION_EDEFAULT == null ? m_description != null
                    : !DESCRIPTION_EDEFAULT.equals( m_description );
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
        result.append( " (displayName: " ); //$NON-NLS-1$
        result.append( m_displayName );
        result.append( ", description: " ); //$NON-NLS-1$
        result.append( m_description );
        result.append( ')' );
        return result.toString();
    }

} //DataElementUIHintsImpl
