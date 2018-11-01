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
 * $Id: InputParameterUIHintsImpl.java,v 1.4 2010/02/17 02:20:38 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Parameter UI Hints</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputParameterUIHintsImpl#getGroupPromptDisplayName <em>Group Prompt Display Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputParameterUIHintsImpl extends EObjectImpl implements
        InputParameterUIHints
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getGroupPromptDisplayName() <em>Group Prompt Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroupPromptDisplayName()
     * @generated
     * @ordered
     */
    protected static final String GROUP_PROMPT_DISPLAY_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getGroupPromptDisplayName() <em>Group Prompt Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroupPromptDisplayName()
     * @generated
     * @ordered
     */
    protected String m_groupPromptDisplayName = GROUP_PROMPT_DISPLAY_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InputParameterUIHintsImpl()
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
        return DesignPackage.Literals.INPUT_PARAMETER_UI_HINTS;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints#getGroupPromptDisplayName()
     * @generated NOT
     */
    public String getGroupPromptDisplayName()
    {
        return DesignUtil.getDefaultResourceString( getGroupPromptDisplayNameGen() );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getGroupPromptDisplayNameGen()
    {
        return m_groupPromptDisplayName;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints#setGroupPromptDisplayName(java.lang.String)
     * @generated NOT
     */
    public void setGroupPromptDisplayName( String newGroupPromptDisplayName )
    {
        String newAttrValue = 
            DesignUtil.addDefaultToResourceAttribute( newGroupPromptDisplayName, getGroupPromptDisplayNameGen() );
        setGroupPromptDisplayNameGen( newAttrValue );
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setGroupPromptDisplayNameGen( String newGroupPromptDisplayName )
    {
        String oldGroupPromptDisplayName = m_groupPromptDisplayName;
        m_groupPromptDisplayName = newGroupPromptDisplayName;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.INPUT_PARAMETER_UI_HINTS__GROUP_PROMPT_DISPLAY_NAME,
                    oldGroupPromptDisplayName, m_groupPromptDisplayName ) );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints#getGroupPromptDisplayNameKey()
     * @generated NOT
     */
    public String getGroupPromptDisplayNameKey()
    {
        return DesignUtil.getResourceKey( getGroupPromptDisplayNameGen() );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints#setGroupPromptDisplayNameKey(java.lang.String)
     * @generated NOT
     */
    public void setGroupPromptDisplayNameKey( String newGroupPromptDisplayNameKey )
    {
        String newAttrValue = 
            DesignUtil.addKeyToResourceAttribute( newGroupPromptDisplayNameKey, getGroupPromptDisplayNameGen() );
        setGroupPromptDisplayNameGen( newAttrValue );
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
        case DesignPackage.INPUT_PARAMETER_UI_HINTS__GROUP_PROMPT_DISPLAY_NAME:
            return getGroupPromptDisplayNameGen();
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
        case DesignPackage.INPUT_PARAMETER_UI_HINTS__GROUP_PROMPT_DISPLAY_NAME:
            setGroupPromptDisplayNameGen( (String) newValue );
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
        case DesignPackage.INPUT_PARAMETER_UI_HINTS__GROUP_PROMPT_DISPLAY_NAME:
            setGroupPromptDisplayNameGen( GROUP_PROMPT_DISPLAY_NAME_EDEFAULT );
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
        case DesignPackage.INPUT_PARAMETER_UI_HINTS__GROUP_PROMPT_DISPLAY_NAME:
            return GROUP_PROMPT_DISPLAY_NAME_EDEFAULT == null ? m_groupPromptDisplayName != null
                    : !GROUP_PROMPT_DISPLAY_NAME_EDEFAULT
                            .equals( m_groupPromptDisplayName );
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
        result.append( " (groupPromptDisplayName: " ); //$NON-NLS-1$
        result.append( m_groupPromptDisplayName );
        result.append( ')' );
        return result.toString();
    }

} //InputParameterUIHintsImpl
