/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateAccordingToIdentifierImpl.java,v 1.2 2005/12/17 01:52:31 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Function Validate According To Identifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToIdentifierImpl#getSchemaName <em>Schema Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToIdentifierImpl#getRegisteredXMLSchemaName <em>Registered XML Schema Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionValidateAccordingToIdentifierImpl extends XMLValueFunctionValidateAccordingToImpl implements XMLValueFunctionValidateAccordingToIdentifier {
	/**
	 * The default value of the '{@link #getSchemaName() <em>Schema Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSchemaName()
	 * @generated
	 * @ordered
	 */
    protected static final String SCHEMA_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSchemaName() <em>Schema Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSchemaName()
	 * @generated
	 * @ordered
	 */
    protected String schemaName = SCHEMA_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getRegisteredXMLSchemaName() <em>Registered XML Schema Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRegisteredXMLSchemaName()
	 * @generated
	 * @ordered
	 */
    protected static final String REGISTERED_XML_SCHEMA_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRegisteredXMLSchemaName() <em>Registered XML Schema Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRegisteredXMLSchemaName()
	 * @generated
	 * @ordered
	 */
    protected String registeredXMLSchemaName = REGISTERED_XML_SCHEMA_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLValueFunctionValidateAccordingToIdentifierImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryPackage.eINSTANCE.getXMLValueFunctionValidateAccordingToIdentifier();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getSchemaName() {
		return schemaName;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSchemaName(String newSchemaName) {
		String oldSchemaName = schemaName;
		schemaName = newSchemaName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__SCHEMA_NAME, oldSchemaName, schemaName));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getRegisteredXMLSchemaName() {
		return registeredXMLSchemaName;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setRegisteredXMLSchemaName(String newRegisteredXMLSchemaName) {
		String oldRegisteredXMLSchemaName = registeredXMLSchemaName;
		registeredXMLSchemaName = newRegisteredXMLSchemaName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__REGISTERED_XML_SCHEMA_NAME, oldRegisteredXMLSchemaName, registeredXMLSchemaName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALUE_FUNCTION_VALIDATE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALUE_FUNCTION_VALIDATE, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALIDATE_ELEMENT:
					if (validateElement != null)
						msgs = ((InternalEObject)validateElement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALIDATE_ELEMENT, null, msgs);
					return basicSetValidateElement((XMLValueFunctionValidateElement)otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALUE_FUNCTION_VALIDATE:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALUE_FUNCTION_VALIDATE, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALIDATE_ELEMENT:
					return basicSetValidateElement(null, msgs);
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALUE_FUNCTION_VALIDATE:
					return eContainer.eInverseRemove(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_ACCORDING_TO, XMLValueFunctionValidate.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__NAME:
				return getName();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__LABEL:
				return getLabel();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALUE_FUNCTION_VALIDATE:
				return getValueFunctionValidate();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALIDATE_ELEMENT:
				return getValidateElement();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__SCHEMA_NAME:
				return getSchemaName();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__REGISTERED_XML_SCHEMA_NAME:
				return getRegisteredXMLSchemaName();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALUE_FUNCTION_VALIDATE:
				setValueFunctionValidate((XMLValueFunctionValidate)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALIDATE_ELEMENT:
				setValidateElement((XMLValueFunctionValidateElement)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__SCHEMA_NAME:
				setSchemaName((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__REGISTERED_XML_SCHEMA_NAME:
				setRegisteredXMLSchemaName((String)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALUE_FUNCTION_VALIDATE:
				setValueFunctionValidate((XMLValueFunctionValidate)null);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALIDATE_ELEMENT:
				setValidateElement((XMLValueFunctionValidateElement)null);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__SCHEMA_NAME:
				setSchemaName(SCHEMA_NAME_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__REGISTERED_XML_SCHEMA_NAME:
				setRegisteredXMLSchemaName(REGISTERED_XML_SCHEMA_NAME_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALUE_FUNCTION_VALIDATE:
				return getValueFunctionValidate() != null;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALIDATE_ELEMENT:
				return validateElement != null;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__SCHEMA_NAME:
				return SCHEMA_NAME_EDEFAULT == null ? schemaName != null : !SCHEMA_NAME_EDEFAULT.equals(schemaName);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__REGISTERED_XML_SCHEMA_NAME:
				return REGISTERED_XML_SCHEMA_NAME_EDEFAULT == null ? registeredXMLSchemaName != null : !REGISTERED_XML_SCHEMA_NAME_EDEFAULT.equals(registeredXMLSchemaName);
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (schemaName: ");
		result.append(schemaName);
		result.append(", registeredXMLSchemaName: ");
		result.append(registeredXMLSchemaName);
		result.append(')');
		return result.toString();
	}

} //XMLValueFunctionValidateAccordingToIdentifierImpl
