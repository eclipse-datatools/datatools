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
import org.eclipse.datatools.connectivity.oda.design.Locale;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Locale</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.LocaleImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.LocaleImpl#getCountry <em>Country</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.LocaleImpl#getVariant <em>Variant</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LocaleImpl extends EObjectImpl implements Locale
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLanguage()
     * @generated
     * @ordered
     */
    protected static final String LANGUAGE_EDEFAULT = "en"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLanguage()
     * @generated
     * @ordered
     */
    protected String m_language = LANGUAGE_EDEFAULT;

    /**
     * This is true if the Language attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean m_languageESet = false;

    /**
     * The default value of the '{@link #getCountry() <em>Country</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCountry()
     * @generated
     * @ordered
     */
    protected static final String COUNTRY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCountry() <em>Country</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCountry()
     * @generated
     * @ordered
     */
    protected String m_country = COUNTRY_EDEFAULT;

    /**
     * The default value of the '{@link #getVariant() <em>Variant</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariant()
     * @generated
     * @ordered
     */
    protected static final String VARIANT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVariant() <em>Variant</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariant()
     * @generated
     * @ordered
     */
    protected String m_variant = VARIANT_EDEFAULT;

    protected static final String EMPTY_STRING = "";
    protected static final String UNDERBAR = "_";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LocaleImpl()
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
        return DesignPackage.eINSTANCE.getLocale();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.Locale#getLocale()
     */
    public java.util.Locale getLocale()
    {
        // if none is explicitly set, or the required language is missing,
        // returns JVM locale default
        if( ! hasLanguage() )
            return java.util.Locale.getDefault();
        
        return new java.util.Locale( getLanguageGen(), 
			                		toNonNullString( getCountry() ), 
			                        toNonNullString( getVariant() ));
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.Locale#setLocale(java.util.Locale)
     */
    public void setLocale( java.util.Locale locale )
    {
        // util Locale does not return null attribute
        if( locale.getLanguage().length() == 0 )
            unsetLanguage();
        else
            setLanguage( locale.getLanguage() );

        if( locale.getCountry().length() == 0 )
            setCountry( COUNTRY_EDEFAULT );
        else
            setCountry( locale.getCountry() );

        if( locale.getVariant().length() == 0 )
            setVariant( VARIANT_EDEFAULT );
        else
            setVariant( locale.getVariant() );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.Locale#toLanguageCountryString()
     */
    public String toLanguageCountryString()
    {
        // gets the util Locale, which could be the JVM default
        java.util.Locale theLocale = getLocale();
        
        String strValue = theLocale.getLanguage();
        if( theLocale.getCountry().length() != 0 )
        {
            strValue += UNDERBAR + theLocale.getCountry();
        }
        return strValue;
    }
    
    /* 
     * General utility method to convert a null String object 
     * to an empty string.
     */
    private String toNonNullString( String strValue )
    {
        if ( strValue == null )
            return EMPTY_STRING;
        return strValue;
    }

    private boolean hasLanguage()
    {
        return( isSetLanguage() &&
                getLanguageGen() != null &&
                getLanguageGen().length() != 0 );
    }
    
    /** 
     * Extends generated method to return JVM default language
     * if none is explicitly set.
     * @see org.eclipse.datatools.connectivity.oda.design.Locale#getLanguage()
     * @generated NOT
     */
    public String getLanguage()
    {
        return getLocale().getLanguage();
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLanguageGen()
    {
        return m_language;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLanguage( String newLanguage )
    {
        String oldLanguage = m_language;
        m_language = newLanguage;
        boolean oldLanguageESet = m_languageESet;
        m_languageESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.LOCALE__LANGUAGE, oldLanguage, m_language, !oldLanguageESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetLanguage()
    {
        String oldLanguage = m_language;
        boolean oldLanguageESet = m_languageESet;
        m_language = LANGUAGE_EDEFAULT;
        m_languageESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, DesignPackage.LOCALE__LANGUAGE, oldLanguage, LANGUAGE_EDEFAULT, oldLanguageESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetLanguage()
    {
        return m_languageESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCountry()
    {
        return m_country;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCountry( String newCountry )
    {
        String oldCountry = m_country;
        m_country = newCountry;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.LOCALE__COUNTRY, oldCountry, m_country));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getVariant()
    {
        return m_variant;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVariant( String newVariant )
    {
        String oldVariant = m_variant;
        m_variant = newVariant;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.LOCALE__VARIANT, oldVariant, m_variant));
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
            case DesignPackage.LOCALE__LANGUAGE:
                return getLanguage();
            case DesignPackage.LOCALE__COUNTRY:
                return getCountry();
            case DesignPackage.LOCALE__VARIANT:
                return getVariant();
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
            case DesignPackage.LOCALE__LANGUAGE:
                setLanguage((String)newValue);
                return;
            case DesignPackage.LOCALE__COUNTRY:
                setCountry((String)newValue);
                return;
            case DesignPackage.LOCALE__VARIANT:
                setVariant((String)newValue);
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
            case DesignPackage.LOCALE__LANGUAGE:
                unsetLanguage();
                return;
            case DesignPackage.LOCALE__COUNTRY:
                setCountry(COUNTRY_EDEFAULT);
                return;
            case DesignPackage.LOCALE__VARIANT:
                setVariant(VARIANT_EDEFAULT);
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
            case DesignPackage.LOCALE__LANGUAGE:
                return isSetLanguage();
            case DesignPackage.LOCALE__COUNTRY:
                return COUNTRY_EDEFAULT == null ? m_country != null : !COUNTRY_EDEFAULT.equals(m_country);
            case DesignPackage.LOCALE__VARIANT:
                return VARIANT_EDEFAULT == null ? m_variant != null : !VARIANT_EDEFAULT.equals(m_variant);
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
        result.append(" (language: "); //$NON-NLS-1$
        if (m_languageESet) result.append(m_language); else result.append("<unset>"); //$NON-NLS-1$
        result.append(", country: "); //$NON-NLS-1$
        result.append(m_country);
        result.append(", variant: "); //$NON-NLS-1$
        result.append(m_variant);
        result.append(')');
        return result.toString();
    }

} //LocaleImpl
