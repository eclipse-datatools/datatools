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
 * $Id: PropertiesImpl.java,v 1.2 2006/02/03 04:16:15 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.datatools.connectivity.oda.design.Property;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Properties</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.PropertiesImpl#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertiesImpl extends EObjectImpl implements Properties
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProperties()
     * @generated
     * @ordered
     */
    protected EList m_properties = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PropertiesImpl()
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
        return DesignPackage.eINSTANCE.getProperties();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.Properties#findProperty(java.lang.String)
     */
    public Property findProperty( String propName )
    {
        Iterator iter = getProperties().iterator();
        while( iter.hasNext() )
        {
            Property prop = (Property) iter.next();
            if( propName.equalsIgnoreCase( prop.getName() ) )
                return prop; // matching property name
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.Properties#setProperty(java.lang.String, java.lang.String)
     */
    public void setProperty( String propName, String propValue )
    {
        Property prop = findProperty( propName );
        if( prop != null )
        {
            prop.setNameValue( propName, propValue );
            return; // done
        }

        // no existing property with given name, add a new one
        prop = DesignFactory.eINSTANCE.createProperty();
        prop.setNameValue( propName, propValue );
        getProperties().add( prop );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.Properties#unsetProperty(java.lang.String)
     */
    public void unsetProperty( String propName )
    {
        Property prop = findProperty( propName );
        if( prop == null )
            return; // done, nothing to remove
        getProperties().remove( prop );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getProperties()
    {
        if( m_properties == null )
        {
            m_properties = new EObjectContainmentEList( Property.class, this,
                    DesignPackage.PROPERTIES__PROPERTIES );
        }
        return m_properties;
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
            case DesignPackage.PROPERTIES__PROPERTIES:
                return ((InternalEList) getProperties()).basicRemove( otherEnd,
                        msgs );
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
        case DesignPackage.PROPERTIES__PROPERTIES:
            return getProperties();
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
        case DesignPackage.PROPERTIES__PROPERTIES:
            getProperties().clear();
            getProperties().addAll( (Collection) newValue );
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
        case DesignPackage.PROPERTIES__PROPERTIES:
            getProperties().clear();
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
        case DesignPackage.PROPERTIES__PROPERTIES:
            return m_properties != null && !m_properties.isEmpty();
        }
        return eDynamicIsSet( eFeature );
    }

} //PropertiesImpl
