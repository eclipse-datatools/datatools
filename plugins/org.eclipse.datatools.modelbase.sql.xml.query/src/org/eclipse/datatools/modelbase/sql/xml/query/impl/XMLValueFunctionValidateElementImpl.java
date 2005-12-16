/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateElementImpl.java,v 1.2 2005/10/22 01:40:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage;
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
 * An implementation of the model object '<em><b>XML Value Function Validate Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementImpl#getValidateElementNamespace <em>Validate Element Namespace</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementImpl#getValidateElementName <em>Validate Element Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementImpl#getValidateAccordingTo <em>Validate According To</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionValidateElementImpl extends SQLQueryObjectImpl implements XMLValueFunctionValidateElement {
	/**
	 * The cached value of the '{@link #getValidateElementNamespace() <em>Validate Element Namespace</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValidateElementNamespace()
	 * @generated
	 * @ordered
	 */
    protected XMLValueFunctionValidateElementNamespace validateElementNamespace = null;

	/**
	 * The cached value of the '{@link #getValidateElementName() <em>Validate Element Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValidateElementName()
	 * @generated
	 * @ordered
	 */
    protected XMLValueFunctionValidateElementName validateElementName = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLValueFunctionValidateElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryPackage.eINSTANCE.getXMLValueFunctionValidateElement();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionValidateElementNamespace getValidateElementNamespace() {
		return validateElementNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetValidateElementNamespace(XMLValueFunctionValidateElementNamespace newValidateElementNamespace, NotificationChain msgs) {
		XMLValueFunctionValidateElementNamespace oldValidateElementNamespace = validateElementNamespace;
		validateElementNamespace = newValidateElementNamespace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE, oldValidateElementNamespace, newValidateElementNamespace);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValidateElementNamespace(XMLValueFunctionValidateElementNamespace newValidateElementNamespace) {
		if (newValidateElementNamespace != validateElementNamespace) {
			NotificationChain msgs = null;
			if (validateElementNamespace != null)
				msgs = ((InternalEObject)validateElementNamespace).eInverseRemove(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT, XMLValueFunctionValidateElementNamespace.class, msgs);
			if (newValidateElementNamespace != null)
				msgs = ((InternalEObject)newValidateElementNamespace).eInverseAdd(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT, XMLValueFunctionValidateElementNamespace.class, msgs);
			msgs = basicSetValidateElementNamespace(newValidateElementNamespace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE, newValidateElementNamespace, newValidateElementNamespace));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionValidateElementName getValidateElementName() {
		return validateElementName;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetValidateElementName(XMLValueFunctionValidateElementName newValidateElementName, NotificationChain msgs) {
		XMLValueFunctionValidateElementName oldValidateElementName = validateElementName;
		validateElementName = newValidateElementName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME, oldValidateElementName, newValidateElementName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValidateElementName(XMLValueFunctionValidateElementName newValidateElementName) {
		if (newValidateElementName != validateElementName) {
			NotificationChain msgs = null;
			if (validateElementName != null)
				msgs = ((InternalEObject)validateElementName).eInverseRemove(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT, XMLValueFunctionValidateElementName.class, msgs);
			if (newValidateElementName != null)
				msgs = ((InternalEObject)newValidateElementName).eInverseAdd(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT, XMLValueFunctionValidateElementName.class, msgs);
			msgs = basicSetValidateElementName(newValidateElementName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME, newValidateElementName, newValidateElementName));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionValidateAccordingTo getValidateAccordingTo() {
		if (eContainerFeatureID != SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO) return null;
		return (XMLValueFunctionValidateAccordingTo)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValidateAccordingTo(XMLValueFunctionValidateAccordingTo newValidateAccordingTo) {
		if (newValidateAccordingTo != eContainer || (eContainerFeatureID != SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO && newValidateAccordingTo != null)) {
			if (EcoreUtil.isAncestor(this, newValidateAccordingTo))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValidateAccordingTo != null)
				msgs = ((InternalEObject)newValidateAccordingTo).eInverseAdd(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT, XMLValueFunctionValidateAccordingTo.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValidateAccordingTo, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO, newValidateAccordingTo, newValidateAccordingTo));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE:
					if (validateElementNamespace != null)
						msgs = ((InternalEObject)validateElementNamespace).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE, null, msgs);
					return basicSetValidateElementNamespace((XMLValueFunctionValidateElementNamespace)otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME:
					if (validateElementName != null)
						msgs = ((InternalEObject)validateElementName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME, null, msgs);
					return basicSetValidateElementName((XMLValueFunctionValidateElementName)otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO, msgs);
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
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE:
					return basicSetValidateElementNamespace(null, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME:
					return basicSetValidateElementName(null, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO, msgs);
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
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO:
					return eContainer.eInverseRemove(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT, XMLValueFunctionValidateAccordingTo.class, msgs);
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__NAME:
				return getName();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__LABEL:
				return getLabel();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE:
				return getValidateElementNamespace();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME:
				return getValidateElementName();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO:
				return getValidateAccordingTo();
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE:
				setValidateElementNamespace((XMLValueFunctionValidateElementNamespace)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME:
				setValidateElementName((XMLValueFunctionValidateElementName)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO:
				setValidateAccordingTo((XMLValueFunctionValidateAccordingTo)newValue);
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE:
				setValidateElementNamespace((XMLValueFunctionValidateElementNamespace)null);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME:
				setValidateElementName((XMLValueFunctionValidateElementName)null);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO:
				setValidateAccordingTo((XMLValueFunctionValidateAccordingTo)null);
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE:
				return validateElementNamespace != null;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME:
				return validateElementName != null;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO:
				return getValidateAccordingTo() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //XMLValueFunctionValidateElementImpl
