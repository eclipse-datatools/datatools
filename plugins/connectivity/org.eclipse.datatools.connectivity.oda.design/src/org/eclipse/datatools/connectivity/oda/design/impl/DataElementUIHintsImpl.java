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
 * $Id: DataElementUIHintsImpl.java,v 1.4 2010/02/17 02:20:38 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DataElementUIHints;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
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
    public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

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
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementUIHints#getDisplayName()
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
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementUIHints#setDisplayName(java.lang.String)
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
                    DesignPackage.DATA_ELEMENT_UI_HINTS__DISPLAY_NAME,
                    oldDisplayName, m_displayName ) );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementUIHints#getDisplayNameKey()
     * @generated NOT
     */
    public String getDisplayNameKey()
    {
        return DesignUtil.getResourceKey( getDisplayNameGen() );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementUIHints#setDisplayNameKey(java.lang.String)
     * @generated NOT
     */
    public void setDisplayNameKey( String newDisplayNameKey )
    {
        String newAttrValue = DesignUtil.addKeyToResourceAttribute( newDisplayNameKey, getDisplayNameGen() );
        setDisplayNameGen( newAttrValue );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementUIHints#getDescription()
     * @generated NOT
     */
    public String getDescription()
    {
        return DesignUtil.getDefaultResourceString( getDescriptionGen() );
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getDescriptionGen()
    {
        return m_description;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementUIHints#setDescription(java.lang.String)
     * @generated NOT
     */
    public void setDescription( String newDescription )
    {
        String newAttrValue = DesignUtil.addDefaultToResourceAttribute( newDescription, getDescriptionGen() );
        setDescriptionGen( newAttrValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setDescriptionGen( String newDescription )
    {
        String oldDescription = m_description;
        m_description = newDescription;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.DATA_ELEMENT_UI_HINTS__DESCRIPTION,
                    oldDescription, m_description ) );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementUIHints#getDescriptionKey()
     * @generated NOT
     */
    public String getDescriptionKey()
    {
        return DesignUtil.getResourceKey( getDescriptionGen() );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.DataElementUIHints#setDescriptionKey(java.lang.String)
     * @generated NOT
     */
    public void setDescriptionKey( String newDescriptionKey )
    {
        String newAttrValue = DesignUtil.addKeyToResourceAttribute( newDescriptionKey, getDescriptionGen() );
        setDescriptionGen( newAttrValue );
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
        case DesignPackage.DATA_ELEMENT_UI_HINTS__DISPLAY_NAME:
            return getDisplayNameGen();
        case DesignPackage.DATA_ELEMENT_UI_HINTS__DESCRIPTION:
            return getDescriptionGen();
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
        case DesignPackage.DATA_ELEMENT_UI_HINTS__DISPLAY_NAME:
            setDisplayNameGen( (String) newValue );
            return;
        case DesignPackage.DATA_ELEMENT_UI_HINTS__DESCRIPTION:
            setDescriptionGen( (String) newValue );
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
        case DesignPackage.DATA_ELEMENT_UI_HINTS__DISPLAY_NAME:
            setDisplayNameGen( DISPLAY_NAME_EDEFAULT );
            return;
        case DesignPackage.DATA_ELEMENT_UI_HINTS__DESCRIPTION:
            setDescriptionGen( DESCRIPTION_EDEFAULT );
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
