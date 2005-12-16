/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateAccordingToImpl.java,v 1.4 2005/10/22 01:40:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;




import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementName;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Function Validate According To</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToImpl#getValueFunctionValidate <em>Value Function Validate</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToImpl#getValidateElement <em>Validate Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionValidateAccordingToImpl extends SQLQueryObjectImpl implements XMLValueFunctionValidateAccordingTo {
	/**
	 * The cached value of the '{@link #getValidateElement() <em>Validate Element</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValidateElement()
	 * @generated
	 * @ordered
	 */
    protected XMLValueFunctionValidateElement validateElement = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLValueFunctionValidateAccordingToImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryPackage.eINSTANCE.getXMLValueFunctionValidateAccordingTo();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionValidate getValueFunctionValidate() {
		if (eContainerFeatureID != SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE) return null;
		return (XMLValueFunctionValidate)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueFunctionValidate(XMLValueFunctionValidate newValueFunctionValidate) {
		if (newValueFunctionValidate != eContainer || (eContainerFeatureID != SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE && newValueFunctionValidate != null)) {
			if (EcoreUtil.isAncestor(this, newValueFunctionValidate))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueFunctionValidate != null)
				msgs = ((InternalEObject)newValueFunctionValidate).eInverseAdd(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_ACCORDING_TO, XMLValueFunctionValidate.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueFunctionValidate, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE, newValueFunctionValidate, newValueFunctionValidate));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionValidateElement getValidateElement() {
		return validateElement;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetValidateElement(XMLValueFunctionValidateElement newValidateElement, NotificationChain msgs) {
		XMLValueFunctionValidateElement oldValidateElement = validateElement;
		validateElement = newValidateElement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT, oldValidateElement, newValidateElement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValidateElement(XMLValueFunctionValidateElement newValidateElement) {
		if (newValidateElement != validateElement) {
			NotificationChain msgs = null;
			if (validateElement != null)
				msgs = ((InternalEObject)validateElement).eInverseRemove(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO, XMLValueFunctionValidateElement.class, msgs);
			if (newValidateElement != null)
				msgs = ((InternalEObject)newValidateElement).eInverseAdd(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO, XMLValueFunctionValidateElement.class, msgs);
			msgs = basicSetValidateElement(newValidateElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT, newValidateElement, newValidateElement));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT:
					if (validateElement != null)
						msgs = ((InternalEObject)validateElement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT, null, msgs);
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
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT:
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
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE:
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__NAME:
				return getName();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__LABEL:
				return getLabel();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE:
				return getValueFunctionValidate();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT:
				return getValidateElement();
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE:
				setValueFunctionValidate((XMLValueFunctionValidate)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT:
				setValidateElement((XMLValueFunctionValidateElement)newValue);
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE:
				setValueFunctionValidate((XMLValueFunctionValidate)null);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT:
				setValidateElement((XMLValueFunctionValidateElement)null);
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE:
				return getValueFunctionValidate() != null;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT:
				return validateElement != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //XMLValueFunctionValidateAccordingToImpl
