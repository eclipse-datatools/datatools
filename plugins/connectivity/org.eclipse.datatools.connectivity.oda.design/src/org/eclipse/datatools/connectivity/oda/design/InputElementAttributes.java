/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
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
 * $Id: InputElementAttributes.java,v 1.5 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Common attributes for a data element defined with the input mode.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#getDefaultScalarValue <em>Default Scalar Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#getDefaultValues <em>Default Values</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#isEditable <em>Editable</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#isOptional <em>Optional</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#isMasksValue <em>Masks Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#getStaticValueChoices <em>Static Value Choices</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#getDynamicValueChoices <em>Dynamic Value Choices</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#getUiHints <em>Ui Hints</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputElementAttributes()
 * @model extendedMetaData="name='InputElementAttributes' kind='elementOnly'"
 * @generated
 */
public interface InputElementAttributes extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Indicates whether this input element has either
     * static or dynamic value choices defined.
     * @return  true if any type of value choices is defined
     * @see #getStaticValueChoices()
     * @see #getDynamicValueChoices()
     * @generated NOT
     */
    boolean hasValueChoices();

    /**
     * Returns the value of the '<em><b>Default Scalar Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The literal string value to be used as the data element's default input value. Only applies to a scalar input data element.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Default Scalar Value</em>' attribute.
     * @see #setDefaultScalarValue(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputElementAttributes_DefaultScalarValue()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='defaultScalarValue' namespace='##targetNamespace'"
     * @generated
     * @deprecated  replaced by {@link #getDefaultValues()} as of 3.2 (DTP 1.7)
     */
    String getDefaultScalarValue();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#getDefaultScalarValue <em>Default Scalar Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Scalar Value</em>' attribute.
     * @see #getDefaultScalarValue()
     * @generated
     * @deprecated  replaced by {@link #setDefaultValues(StaticValues)} as of 3.2 (DTP 1.7)
     */
    void setDefaultScalarValue( String value );

    /**
     * Returns the value of the '<em><b>Default Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * An optional collection of default input values.  If defined, this element overrides the deprecated defaultScalarValue element.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Default Values</em>' containment reference.
     * @see #setDefaultValues(StaticValues)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputElementAttributes_DefaultValues()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='defaultValues' namespace='##targetNamespace'"
     * @since 3.2 (DTP 1.7)
     * @generated
     */
    StaticValues getDefaultValues();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#getDefaultValues <em>Default Values</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Values</em>' containment reference.
     * @see #getDefaultValues()
     * @since 3.2 (DTP 1.7)
     * @generated
     */
    void setDefaultValues( StaticValues value );

    /**
     * Gets the number of default values specified.
     * @return  number of default values
     * @since 3.2 (DTP 1.7)
     * @generated NOT
     */
    int getDefaultValueCount();

    /**
     * Appends the specified value to the list of default values.
     * It is the responsibility of the caller to ensure compatible type of value object
     * is added to the list.
     * @param aValue    a value to add; may be null
     * @since 3.2 (DTP 1.7)
     * @generated NOT
     */
    void addDefaultValue( Object aValue );

    /**
     * Returns the value of the '<em><b>Editable</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Indicates whether this element value is editable or read-only in the host designer.  A host designer can further design whether to hide this attribute.  Applicable only if its container is visible, e.g. for public properties and input parameters.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Editable</em>' attribute.
     * @see #isSetEditable()
     * @see #unsetEditable()
     * @see #setEditable(boolean)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputElementAttributes_Editable()
     * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='editable' namespace='##targetNamespace'"
     * @generated
     */
    boolean isEditable();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#isEditable <em>Editable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Editable</em>' attribute.
     * @see #isSetEditable()
     * @see #unsetEditable()
     * @see #isEditable()
     * @generated
     */
    void setEditable( boolean value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#isEditable <em>Editable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetEditable()
     * @see #isEditable()
     * @see #setEditable(boolean)
     * @generated
     */
    void unsetEditable();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#isEditable <em>Editable</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Editable</em>' attribute is set.
     * @see #unsetEditable()
     * @see #isEditable()
     * @see #setEditable(boolean)
     * @generated
     */
    boolean isSetEditable();

    /**
     * Returns the value of the '<em><b>Optional</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Indicates whether the element requires an input value.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Optional</em>' attribute.
     * @see #isSetOptional()
     * @see #unsetOptional()
     * @see #setOptional(boolean)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputElementAttributes_Optional()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='optional' namespace='##targetNamespace'"
     * @generated
     */
    boolean isOptional();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#isOptional <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Optional</em>' attribute.
     * @see #isSetOptional()
     * @see #unsetOptional()
     * @see #isOptional()
     * @generated
     */
    void setOptional( boolean value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#isOptional <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetOptional()
     * @see #isOptional()
     * @see #setOptional(boolean)
     * @generated
     */
    void unsetOptional();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#isOptional <em>Optional</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Optional</em>' attribute is set.
     * @see #unsetOptional()
     * @see #isOptional()
     * @see #setOptional(boolean)
     * @generated
     */
    boolean isSetOptional();

    /**
     * Returns the value of the '<em><b>Masks Value</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Indicates whether input value should be masked or encrypted in persistent store and any UI display.  Applies to default value as well.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Masks Value</em>' attribute.
     * @see #isSetMasksValue()
     * @see #unsetMasksValue()
     * @see #setMasksValue(boolean)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputElementAttributes_MasksValue()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='masksValue' namespace='##targetNamespace'"
     * @generated
     */
    boolean isMasksValue();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#isMasksValue <em>Masks Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Masks Value</em>' attribute.
     * @see #isSetMasksValue()
     * @see #unsetMasksValue()
     * @see #isMasksValue()
     * @generated
     */
    void setMasksValue( boolean value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#isMasksValue <em>Masks Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetMasksValue()
     * @see #isMasksValue()
     * @see #setMasksValue(boolean)
     * @generated
     */
    void unsetMasksValue();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#isMasksValue <em>Masks Value</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Masks Value</em>' attribute is set.
     * @see #unsetMasksValue()
     * @see #isMasksValue()
     * @see #setMasksValue(boolean)
     * @generated
     */
    boolean isSetMasksValue();

    /**
     * Returns the value of the '<em><b>Static Value Choices</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Static Value Choices</em>' containment reference.
     * @see #setStaticValueChoices(ScalarValueChoices)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputElementAttributes_StaticValueChoices()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='staticValueChoices' namespace='##targetNamespace'"
     * @generated
     */
    ScalarValueChoices getStaticValueChoices();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#getStaticValueChoices <em>Static Value Choices</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Static Value Choices</em>' containment reference.
     * @see #getStaticValueChoices()
     * @generated
     */
    void setStaticValueChoices( ScalarValueChoices value );

    /**
     * Returns the value of the '<em><b>Dynamic Value Choices</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dynamic Value Choices</em>' containment reference.
     * @see #setDynamicValueChoices(DynamicValuesQuery)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputElementAttributes_DynamicValueChoices()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='dynamicValueChoices' namespace='##targetNamespace'"
     * @generated
     */
    DynamicValuesQuery getDynamicValueChoices();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#getDynamicValueChoices <em>Dynamic Value Choices</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dynamic Value Choices</em>' containment reference.
     * @see #getDynamicValueChoices()
     * @generated
     */
    void setDynamicValueChoices( DynamicValuesQuery value );

    /**
     * Returns the value of the '<em><b>Ui Hints</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ui Hints</em>' containment reference.
     * @see #setUiHints(InputElementUIHints)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputElementAttributes_UiHints()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='uiHints' namespace='##targetNamespace'"
     * @generated
     */
    InputElementUIHints getUiHints();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.InputElementAttributes#getUiHints <em>Ui Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ui Hints</em>' containment reference.
     * @see #getUiHints()
     * @generated
     */
    void setUiHints( InputElementUIHints value );

    /**
     * Sets the value of the <em>Prompt Style</em> attribute
     * in the element's UI hints.
     * @param value the new value of the '<em>Prompt Style</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.InputPromptControlStyle
     * @see #setUiHints(InputElementUIHints)
     * @generated NOT
     */
    void setUiPromptStyle( InputPromptControlStyle value );

} // InputElementAttributes
