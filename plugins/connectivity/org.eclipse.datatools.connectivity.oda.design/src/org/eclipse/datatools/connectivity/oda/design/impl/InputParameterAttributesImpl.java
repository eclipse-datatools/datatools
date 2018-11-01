/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: InputParameterAttributesImpl.java,v 1.3 2007/04/11 02:59:52 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.InputParameterAttributes;
import org.eclipse.datatools.connectivity.oda.design.InputParameterUIHints;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
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
public class InputParameterAttributesImpl extends EObjectImpl implements
        InputParameterAttributes
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getElementAttributes() <em>Element Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getElementAttributes()
     * @generated
     * @ordered
     */
    protected InputElementAttributes m_elementAttributes;

    /**
     * The cached value of the '{@link #getUiHints() <em>Ui Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUiHints()
     * @generated
     * @ordered
     */
    protected InputParameterUIHints m_uiHints;

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
    @Override
    protected EClass eStaticClass()
    {
        return DesignPackage.Literals.INPUT_PARAMETER_ATTRIBUTES;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.InputParameterAttributes#setUiGroupPromptDisplayName(java.lang.String)
     * @generated NOT
     */
    public void setUiGroupPromptDisplayName( String value )
    {
        // sets attribute in current UIHints, if exists;
        // otherwise, creates a new one
        InputParameterUIHints uiHints = getUiHints();
        boolean hasNoUIHints = (uiHints == null);
        if( hasNoUIHints )
            uiHints = DesignFactory.eINSTANCE.createInputParameterUIHints();
        uiHints.setGroupPromptDisplayName( value );

        if( hasNoUIHints )
            setUiHints( uiHints );
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
    public NotificationChain basicSetElementAttributes(
            InputElementAttributes newElementAttributes, NotificationChain msgs )
    {
        InputElementAttributes oldElementAttributes = m_elementAttributes;
        m_elementAttributes = newElementAttributes;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES,
                    oldElementAttributes, newElementAttributes );
            if( msgs == null )
                msgs = notification;
            else
                msgs.add( notification );
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setElementAttributes(
            InputElementAttributes newElementAttributes )
    {
        if( newElementAttributes != m_elementAttributes )
        {
            NotificationChain msgs = null;
            if( m_elementAttributes != null )
                msgs = ((InternalEObject) m_elementAttributes)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES,
                                null, msgs );
            if( newElementAttributes != null )
                msgs = ((InternalEObject) newElementAttributes)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES,
                                null, msgs );
            msgs = basicSetElementAttributes( newElementAttributes, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl(
                    this,
                    Notification.SET,
                    DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES,
                    newElementAttributes, newElementAttributes ) );
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
    public NotificationChain basicSetUiHints( InputParameterUIHints newUiHints,
            NotificationChain msgs )
    {
        InputParameterUIHints oldUiHints = m_uiHints;
        m_uiHints = newUiHints;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS,
                    oldUiHints, newUiHints );
            if( msgs == null )
                msgs = notification;
            else
                msgs.add( notification );
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
        if( newUiHints != m_uiHints )
        {
            NotificationChain msgs = null;
            if( m_uiHints != null )
                msgs = ((InternalEObject) m_uiHints)
                        .eInverseRemove(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS,
                                null, msgs );
            if( newUiHints != null )
                msgs = ((InternalEObject) newUiHints)
                        .eInverseAdd(
                                this,
                                EOPPOSITE_FEATURE_BASE
                                        - DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS,
                                null, msgs );
            msgs = basicSetUiHints( newUiHints, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS,
                    newUiHints, newUiHints ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove( InternalEObject otherEnd,
            int featureID, NotificationChain msgs )
    {
        switch( featureID )
        {
        case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES:
            return basicSetElementAttributes( null, msgs );
        case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS:
            return basicSetUiHints( null, msgs );
        }
        return super.eInverseRemove( otherEnd, featureID, msgs );
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
        case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES:
            return getElementAttributes();
        case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS:
            return getUiHints();
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
        case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES:
            setElementAttributes( (InputElementAttributes) newValue );
            return;
        case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS:
            setUiHints( (InputParameterUIHints) newValue );
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
        case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES:
            setElementAttributes( (InputElementAttributes) null );
            return;
        case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS:
            setUiHints( (InputParameterUIHints) null );
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
        case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__ELEMENT_ATTRIBUTES:
            return m_elementAttributes != null;
        case DesignPackage.INPUT_PARAMETER_ATTRIBUTES__UI_HINTS:
            return m_uiHints != null;
        }
        return super.eIsSet( featureID );
    }

} //InputParameterAttributesImpl
