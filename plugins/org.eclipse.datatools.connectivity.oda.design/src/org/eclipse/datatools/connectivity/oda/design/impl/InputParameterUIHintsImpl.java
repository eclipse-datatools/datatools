/**
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
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
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

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
public class InputParameterUIHintsImpl extends EObjectImpl implements InputParameterUIHints
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

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
    protected EClass eStaticClass()
    {
        return DesignPackage.eINSTANCE.getInputParameterUIHints();
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
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_PARAMETER_UI_HINTS__GROUP_PROMPT_DISPLAY_NAME, oldGroupPromptDisplayName, m_groupPromptDisplayName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet( EStructuralFeature eFeature, boolean resolve )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.INPUT_PARAMETER_UI_HINTS__GROUP_PROMPT_DISPLAY_NAME:
                return getGroupPromptDisplayName();
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet( EStructuralFeature eFeature, Object newValue )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.INPUT_PARAMETER_UI_HINTS__GROUP_PROMPT_DISPLAY_NAME:
                setGroupPromptDisplayName((String)newValue);
                return;
        }
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset( EStructuralFeature eFeature )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.INPUT_PARAMETER_UI_HINTS__GROUP_PROMPT_DISPLAY_NAME:
                setGroupPromptDisplayName(GROUP_PROMPT_DISPLAY_NAME_EDEFAULT);
                return;
        }
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet( EStructuralFeature eFeature )
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
            case DesignPackage.INPUT_PARAMETER_UI_HINTS__GROUP_PROMPT_DISPLAY_NAME:
                return GROUP_PROMPT_DISPLAY_NAME_EDEFAULT == null ? m_groupPromptDisplayName != null : !GROUP_PROMPT_DISPLAY_NAME_EDEFAULT.equals(m_groupPromptDisplayName);
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString()
    {
        if ( eIsProxy() ) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (groupPromptDisplayName: "); //$NON-NLS-1$
        result.append(m_groupPromptDisplayName);
        result.append(')');
        return result.toString();
    }

} //InputParameterUIHintsImpl
