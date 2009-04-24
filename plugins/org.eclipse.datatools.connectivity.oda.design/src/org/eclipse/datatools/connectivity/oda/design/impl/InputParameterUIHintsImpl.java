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
 * $Id: InputParameterUIHintsImpl.java,v 1.2 2007/04/11 02:59:52 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints;
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
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

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

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getGroupPromptDisplayName()
    {
        return m_groupPromptDisplayName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGroupPromptDisplayName( String newGroupPromptDisplayName )
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
        case DesignPackage.INPUT_PARAMETER_UI_HINTS__GROUP_PROMPT_DISPLAY_NAME:
            return getGroupPromptDisplayName();
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
        case DesignPackage.INPUT_PARAMETER_UI_HINTS__GROUP_PROMPT_DISPLAY_NAME:
            setGroupPromptDisplayName( (String) newValue );
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
        case DesignPackage.INPUT_PARAMETER_UI_HINTS__GROUP_PROMPT_DISPLAY_NAME:
            setGroupPromptDisplayName( GROUP_PROMPT_DISPLAY_NAME_EDEFAULT );
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
