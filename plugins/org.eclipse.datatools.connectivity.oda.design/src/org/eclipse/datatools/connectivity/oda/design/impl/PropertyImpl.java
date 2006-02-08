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
 * $Id: PropertyImpl.java,v 1.2 2006/02/03 04:16:15 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.NameValuePair;
import org.eclipse.datatools.connectivity.oda.design.Property;

import org.eclipse.datatools.connectivity.oda.design.PropertyAttributes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.PropertyImpl#getNameValue <em>Name Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.PropertyImpl#getDesignAttributes <em>Design Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertyImpl extends EObjectImpl implements Property
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getNameValue() <em>Name Value</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNameValue()
     * @generated
     * @ordered
     */
    protected NameValuePair m_nameValue = null;

    /**
     * The cached value of the '{@link #getDesignAttributes() <em>Design Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDesignAttributes()
     * @generated
     * @ordered
     */
    protected PropertyAttributes m_designAttributes = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PropertyImpl()
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
        return DesignPackage.eINSTANCE.getProperty();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.Property#getName()
     */
    public String getName()
    {
        if( getNameValue() == null )
            return null;
        return getNameValue().getName();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.Property#getValue()
     */
    public String getValue()
    {
        if( getNameValue() == null )
            return null;
        return getNameValue().getValue();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.Property#setNameValue(java.lang.String, java.lang.String)
     */
    public void setNameValue( String name, String value )
    {
        NameValuePair newPair = DesignFactory.eINSTANCE.createNameValuePair();
        newPair.setName( name );
        newPair.setValue( value );

        setNameValue( newPair );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NameValuePair getNameValue()
    {
        return m_nameValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNameValue( NameValuePair newNameValue,
            NotificationChain msgs )
    {
        NameValuePair oldNameValue = m_nameValue;
        m_nameValue = newNameValue;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET, DesignPackage.PROPERTY__NAME_VALUE,
                    oldNameValue, newNameValue );
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
    public void setNameValue( NameValuePair newNameValue )
    {
        if( newNameValue != m_nameValue )
        {
            NotificationChain msgs = null;
            if( m_nameValue != null )
                msgs = ((InternalEObject) m_nameValue).eInverseRemove( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.PROPERTY__NAME_VALUE, null,
                        msgs );
            if( newNameValue != null )
                msgs = ((InternalEObject) newNameValue).eInverseAdd( this,
                        EOPPOSITE_FEATURE_BASE
                                - DesignPackage.PROPERTY__NAME_VALUE, null,
                        msgs );
            msgs = basicSetNameValue( newNameValue, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.PROPERTY__NAME_VALUE, newNameValue,
                    newNameValue ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PropertyAttributes getDesignAttributes()
    {
        return m_designAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDesignAttributes(
            PropertyAttributes newDesignAttributes, NotificationChain msgs )
    {
        PropertyAttributes oldDesignAttributes = m_designAttributes;
        m_designAttributes = newDesignAttributes;
        if( eNotificationRequired() )
        {
            ENotificationImpl notification = new ENotificationImpl( this,
                    Notification.SET,
                    DesignPackage.PROPERTY__DESIGN_ATTRIBUTES,
                    oldDesignAttributes, newDesignAttributes );
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
    public void setDesignAttributes( PropertyAttributes newDesignAttributes )
    {
        if( newDesignAttributes != m_designAttributes )
        {
            NotificationChain msgs = null;
            if( m_designAttributes != null )
                msgs = ((InternalEObject) m_designAttributes).eInverseRemove(
                        this, EOPPOSITE_FEATURE_BASE
                                - DesignPackage.PROPERTY__DESIGN_ATTRIBUTES,
                        null, msgs );
            if( newDesignAttributes != null )
                msgs = ((InternalEObject) newDesignAttributes).eInverseAdd(
                        this, EOPPOSITE_FEATURE_BASE
                                - DesignPackage.PROPERTY__DESIGN_ATTRIBUTES,
                        null, msgs );
            msgs = basicSetDesignAttributes( newDesignAttributes, msgs );
            if( msgs != null )
                msgs.dispatch();
        }
        else if( eNotificationRequired() )
            eNotify( new ENotificationImpl( this, Notification.SET,
                    DesignPackage.PROPERTY__DESIGN_ATTRIBUTES,
                    newDesignAttributes, newDesignAttributes ) );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove( InternalEObject otherEnd,
            int featureID, Class baseClass, NotificationChain msgs )
    {
        if( featureID >= 0 )
        {
            switch( eDerivedStructuralFeatureID( featureID, baseClass ) )
            {
            case DesignPackage.PROPERTY__NAME_VALUE:
                return basicSetNameValue( null, msgs );
            case DesignPackage.PROPERTY__DESIGN_ATTRIBUTES:
                return basicSetDesignAttributes( null, msgs );
            default:
                return eDynamicInverseRemove( otherEnd, featureID, baseClass,
                        msgs );
            }
        }
        return eBasicSetContainer( null, featureID, msgs );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet( EStructuralFeature eFeature, boolean resolve )
    {
        switch( eDerivedStructuralFeatureID( eFeature ) )
        {
        case DesignPackage.PROPERTY__NAME_VALUE:
            return getNameValue();
        case DesignPackage.PROPERTY__DESIGN_ATTRIBUTES:
            return getDesignAttributes();
        }
        return eDynamicGet( eFeature, resolve );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet( EStructuralFeature eFeature, Object newValue )
    {
        switch( eDerivedStructuralFeatureID( eFeature ) )
        {
        case DesignPackage.PROPERTY__NAME_VALUE:
            setNameValue( (NameValuePair) newValue );
            return;
        case DesignPackage.PROPERTY__DESIGN_ATTRIBUTES:
            setDesignAttributes( (PropertyAttributes) newValue );
            return;
        }
        eDynamicSet( eFeature, newValue );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset( EStructuralFeature eFeature )
    {
        switch( eDerivedStructuralFeatureID( eFeature ) )
        {
        case DesignPackage.PROPERTY__NAME_VALUE:
            setNameValue( (NameValuePair) null );
            return;
        case DesignPackage.PROPERTY__DESIGN_ATTRIBUTES:
            setDesignAttributes( (PropertyAttributes) null );
            return;
        }
        eDynamicUnset( eFeature );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet( EStructuralFeature eFeature )
    {
        switch( eDerivedStructuralFeatureID( eFeature ) )
        {
        case DesignPackage.PROPERTY__NAME_VALUE:
            return m_nameValue != null;
        case DesignPackage.PROPERTY__DESIGN_ATTRIBUTES:
            return m_designAttributes != null;
        }
        return eDynamicIsSet( eFeature );
    }

} //PropertyImpl
