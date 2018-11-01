/**
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
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
 * $Id: Locale.java,v 1.3 2006/02/18 00:08:56 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

import com.ibm.icu.util.ULocale;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a specific geographical, political, or cultural region.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.Locale#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.Locale#getCountry <em>Country</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.Locale#getVariant <em>Variant</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getLocale()
 * @model extendedMetaData="name='Locale' kind='elementOnly'"
 * @generated
 */
public interface Locale extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the locale value as an ULocale object.
     * @return	a com.ibm.icu.util.ULocale object
     * @generated NOT
     */
    ULocale getLocale();

    /**
     * Sets all the locale values.
     * @param   a ULocale object
     * @generated NOT
     */
    void setLocale( ULocale locale );

    /**
     * Getter for the programmatic name of the entire locale, 
     * with the language and country separated by underbars. 
     * Language is always lower case, and country is always 
     * upper case. If the required language is missing, 
     * this function will return the default locale's value.
     * The variant value, if any, is ignored.
     * Examples: "en", "de", "de_DE".
     * @return	a string representation of the locale's language and country.
     * @generated NOT
     */
    String toLanguageCountryString();

    /**
     * Returns the value of the '<em><b>Language</b></em>' attribute.
     * The default value is <code>"en"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * An ISO Language Code; lower-case, two-letter codes as defined by ISO-639.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Language</em>' attribute.
     * @see #isSetLanguage()
     * @see #unsetLanguage()
     * @see #setLanguage(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getLocale_Language()
     * @model default="en" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='language' namespace='##targetNamespace'"
     * @generated
     */
    String getLanguage();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.Locale#getLanguage <em>Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Language</em>' attribute.
     * @see #isSetLanguage()
     * @see #unsetLanguage()
     * @see #getLanguage()
     * @generated
     */
    void setLanguage( String value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.Locale#getLanguage <em>Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetLanguage()
     * @see #getLanguage()
     * @see #setLanguage(String)
     * @generated
     */
    void unsetLanguage();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.Locale#getLanguage <em>Language</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Language</em>' attribute is set.
     * @see #unsetLanguage()
     * @see #getLanguage()
     * @see #setLanguage(String)
     * @generated
     */
    boolean isSetLanguage();

    /**
     * Returns the value of the '<em><b>Country</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * An ISO Country Code; upper-case, two-letter codes as defined by ISO-3166.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Country</em>' attribute.
     * @see #setCountry(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getLocale_Country()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='country' namespace='##targetNamespace'"
     * @generated
     */
    String getCountry();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.Locale#getCountry <em>Country</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Country</em>' attribute.
     * @see #getCountry()
     * @generated
     */
    void setCountry( String value );

    /**
     * Returns the value of the '<em><b>Variant</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A vendor or browser-specific code.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Variant</em>' attribute.
     * @see #setVariant(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getLocale_Variant()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='variant' namespace='##targetNamespace'"
     * @generated
     */
    String getVariant();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.Locale#getVariant <em>Variant</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Variant</em>' attribute.
     * @see #getVariant()
     * @generated
     */
    void setVariant( String value );

} // Locale
