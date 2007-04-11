/**
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
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
 * $Id: InputElementUIHintsImpl.java,v 1.2 2006/01/27 02:37:40 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.InputElementUIHints;
import org.eclipse.datatools.connectivity.oda.design.InputPromptControlStyle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Element UI Hints</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementUIHintsImpl#getPromptStyle <em>Prompt Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputElementUIHintsImpl extends EObjectImpl implements
        InputElementUIHints
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getPromptStyle() <em>Prompt Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPromptStyle()
     * @generated
     * @ordered
     */
    protected static final InputPromptControlStyle PROMPT_STYLE_EDEFAULT = InputPromptControlStyle.TEXT_FIELD_LITERAL;

    /**
     * The cached value of the '{@link #getPromptStyle() <em>Prompt Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPromptStyle()
     * @generated
     * @ordered
     */
    protected InputPromptControlStyle m_promptStyle = PROMPT_STYLE_EDEFAULT;

    /**
     * This is true if the Prompt Style attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_promptStyleESet = false;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InputElementUIHintsImpl()
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
        return DesignPackage.Literals.INPUT_ELEMENT_UI_HINTS;
    }

    /**
     * Returns the prompt style if set, or defaults to
     * to TextField if no static and dynamic value choices are 
     * defined in container element, InputElementAttributes; 
     * otherwise, defaults to SelectableList.
     */
    public InputPromptControlStyle getPromptStyle()
    {
        if( isSetPromptStyle() )
            return getPromptStyleGen();

        // not set, applies default based on settings of
        // the container element, InputElementAttributes
        assert (eContainer() instanceof InputElementAttributes);
        InputElementAttributes parent = (InputElementAttributes) eContainer();
        return parent.hasValueChoices() ? InputPromptControlStyle.SELECTABLE_LIST_LITERAL
                : InputPromptControlStyle.TEXT_FIELD_LITERAL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputPromptControlStyle getPromptStyleGen()
    {
        return m_promptStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPromptStyle( InputPromptControlStyle newPromptStyle )
    {
        InputPromptControlStyle oldPromptStyle = m_promptStyle;
        m_promptStyle = newPromptStyle == null ? PROMPT_STYLE_EDEFAULT
                : newPromptStyle;
        boolean oldPromptStyleESet = m_promptStyleESet;
        m_promptStyleESet = true;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.INPUT_ELEMENT_UI_HINTS__PROMPT_STYLE,
                    oldPromptStyle, m_promptStyle, !oldPromptStyleESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetPromptStyle()
    {
        InputPromptControlStyle oldPromptStyle = m_promptStyle;
        boolean oldPromptStyleESet = m_promptStyleESet;
        m_promptStyle = PROMPT_STYLE_EDEFAULT;
        m_promptStyleESet = false;
        if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.UNSET,
                    DesignPackage.INPUT_ELEMENT_UI_HINTS__PROMPT_STYLE,
                    oldPromptStyle, PROMPT_STYLE_EDEFAULT, oldPromptStyleESet ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetPromptStyle()
    {
        return m_promptStyleESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet( int featureID, boolean resolve, boolean coreType )
    {
        switch( featureID )
        {
        case DesignPackage.INPUT_ELEMENT_UI_HINTS__PROMPT_STYLE:
            return getPromptStyle();
        }
        return super.eGet( featureID, resolve, coreType );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet( int featureID, Object newValue )
    {
        switch( featureID )
        {
        case DesignPackage.INPUT_ELEMENT_UI_HINTS__PROMPT_STYLE:
            setPromptStyle( (InputPromptControlStyle) newValue );
            return;
        }
        super.eSet( featureID, newValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset( int featureID )
    {
        switch( featureID )
        {
        case DesignPackage.INPUT_ELEMENT_UI_HINTS__PROMPT_STYLE:
            unsetPromptStyle();
            return;
        }
        super.eUnset( featureID );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet( int featureID )
    {
        switch( featureID )
        {
        case DesignPackage.INPUT_ELEMENT_UI_HINTS__PROMPT_STYLE:
            return isSetPromptStyle();
        }
        return super.eIsSet( featureID );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString()
    {
        if( eIsProxy() )
            return super.toString();

        StringBuffer result = new StringBuffer( super.toString() );
        result.append( " (promptStyle: " ); //$NON-NLS-1$
        if( m_promptStyleESet )
            result.append( m_promptStyle );
        else
            result.append( "<unset>" ); //$NON-NLS-1$
        result.append( ')' );
        return result.toString();
    }

} //InputElementUIHintsImpl
