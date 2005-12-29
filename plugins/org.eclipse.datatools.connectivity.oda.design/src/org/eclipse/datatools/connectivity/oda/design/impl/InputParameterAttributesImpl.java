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
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.InputParameterAttributes;
import org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Parameter Attributes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputParameterAttributesImpl#getElementAttributes <em>Element Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputParameterAttributesImpl#getUiHints <em>Ui Hints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputParameterAttributesImpl extends EObjectImpl implements InputParameterAttributes
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getElementAttributes() <em>Element Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getElementAttributes()
     * @generated
     * @ordered
     */
    protected InputElementAttributes m_elementAttributes = null;

    /**
     * The cached value of the '{@link #getUiHints() <em>Ui Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUiHints()
     * @generated
     * @ordered
     */
    protected InputParameterUIHints m_uiHints = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InputParameterAttributesImpl()
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
        return DesignPackage.eINSTANCE.getInputParameterAttributes();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputElementAttributes getElementAttributes()
    {
        return m_elementAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetElementAttributes( InputElementAttributes newElementAttributes, NotificationChain msgs )
    {
        InputElementAttributes oldElementAttributes = m_elementAttributes;
        m_elementAttributes = newElementAttributes;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES, oldElementAttributes, newElementAttributes);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setElementAttributes( InputElementAttributes newElementAttributes )
    {
        if (newElementAttributes != m_elementAttributes)
        {
            NotificationChain msgs = null;
            if (m_elementAttributes != null)
                msgs = ((InternalEObject)m_elementAttributes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES, null, msgs);
            if (newElementAttributes != null)
                msgs = ((InternalEObject)newElementAttributes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES, null, msgs);
            msgs = basicSetElementAttributes(newElementAttributes, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES, newElementAttributes, newElementAttributes));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputParameterUIHints getUiHints()
    {
        return m_uiHints;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUiHints( InputParameterUIHints newUiHints, NotificationChain msgs )
    {
        InputParameterUIHints oldUiHints = m_uiHints;
        m_uiHints = newUiHints;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS, oldUiHints, newUiHints);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUiHints( InputParameterUIHints newUiHints )
    {
        if (newUiHints != m_uiHints)
        {
            NotificationChain msgs = null;
            if (m_uiHints != null)
                msgs = ((InternalEObject)m_uiHints).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS, null, msgs);
            if (newUiHints != null)
                msgs = ((InternalEObject)newUiHints).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS, null, msgs);
            msgs = basicSetUiHints(newUiHints, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS, newUiHints, newUiHints));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove( InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs )
    {
        if (featureID >= 0)
        {
            switch (eDerivedStructuralFeatureID(featureID, baseClass))
            {
                case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES:
                    return basicSetElementAttributes(null, msgs);
                case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS:
                    return basicSetUiHints(null, msgs);
                default:
                    return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
            }
        }
        return eBasicSetContainer(null, featureID, msgs);
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
            case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES:
                return getElementAttributes();
            case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS:
                return getUiHints();
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
            case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES:
                setElementAttributes((InputElementAttributes)newValue);
                return;
            case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS:
                setUiHints((InputParameterUIHints)newValue);
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
            case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES:
                setElementAttributes((InputElementAttributes)null);
                return;
            case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS:
                setUiHints((InputParameterUIHints)null);
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
            case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES:
                return m_elementAttributes != null;
            case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS:
                return m_uiHints != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //InputParameterAttributesImpl
