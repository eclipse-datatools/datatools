/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionElementContentListImpl.java,v 1.1 2005/12/16 13:16:51 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNullHandlingType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Function Element Content List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementContentListImpl#getNullHandlingOption <em>Null Handling Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementContentListImpl#getValueFunctionElement <em>Value Function Element</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementContentListImpl#getElementContentListChildren <em>Element Content List Children</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionElementContentListImpl extends SQLQueryObjectImpl implements XMLValueFunctionElementContentList {
	/**
	 * The default value of the '{@link #getNullHandlingOption() <em>Null Handling Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNullHandlingOption()
	 * @generated
	 * @ordered
	 */
    protected static final XMLNullHandlingType NULL_HANDLING_OPTION_EDEFAULT = XMLNullHandlingType.ABSENT_ON_NULL_LITERAL;

	/**
	 * The cached value of the '{@link #getNullHandlingOption() <em>Null Handling Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNullHandlingOption()
	 * @generated
	 * @ordered
	 */
    protected XMLNullHandlingType nullHandlingOption = NULL_HANDLING_OPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getElementContentListChildren() <em>Element Content List Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getElementContentListChildren()
	 * @generated
	 * @ordered
	 */
    protected EList elementContentListChildren = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLValueFunctionElementContentListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryModelPackage.eINSTANCE.getXMLValueFunctionElementContentList();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLNullHandlingType getNullHandlingOption() {
		return nullHandlingOption;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNullHandlingOption(XMLNullHandlingType newNullHandlingOption) {
		XMLNullHandlingType oldNullHandlingOption = nullHandlingOption;
		nullHandlingOption = newNullHandlingOption == null ? NULL_HANDLING_OPTION_EDEFAULT : newNullHandlingOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NULL_HANDLING_OPTION, oldNullHandlingOption, nullHandlingOption));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionElement getValueFunctionElement() {
		if (eContainerFeatureID != SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT) return null;
		return (XMLValueFunctionElement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueFunctionElement(XMLValueFunctionElement newValueFunctionElement) {
		if (newValueFunctionElement != eContainer || (eContainerFeatureID != SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT && newValueFunctionElement != null)) {
			if (EcoreUtil.isAncestor(this, newValueFunctionElement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueFunctionElement != null)
				msgs = ((InternalEObject)newValueFunctionElement).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST, XMLValueFunctionElement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueFunctionElement, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT, newValueFunctionElement, newValueFunctionElement));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getElementContentListChildren() {
		if (elementContentListChildren == null) {
			elementContentListChildren = new EObjectContainmentWithInverseEList(XMLValueFunctionElementContentItem.class, this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__ELEMENT_CONTENT_LIST_CHILDREN, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__ELEMENT_CONTENT_LIST);
		}
		return elementContentListChildren;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT, msgs);
				case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__ELEMENT_CONTENT_LIST_CHILDREN:
					return ((InternalEList)getElementContentListChildren()).basicAdd(otherEnd, msgs);
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
				case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT:
					return eBasicSetContainer(null, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT, msgs);
				case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__ELEMENT_CONTENT_LIST_CHILDREN:
					return ((InternalEList)getElementContentListChildren()).basicRemove(otherEnd, msgs);
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
				case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT:
					return eContainer.eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST, XMLValueFunctionElement.class, msgs);
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
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NAME:
				return getName();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__LABEL:
				return getLabel();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NULL_HANDLING_OPTION:
				return getNullHandlingOption();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT:
				return getValueFunctionElement();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__ELEMENT_CONTENT_LIST_CHILDREN:
				return getElementContentListChildren();
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
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NULL_HANDLING_OPTION:
				setNullHandlingOption((XMLNullHandlingType)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT:
				setValueFunctionElement((XMLValueFunctionElement)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__ELEMENT_CONTENT_LIST_CHILDREN:
				getElementContentListChildren().clear();
				getElementContentListChildren().addAll((Collection)newValue);
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
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NULL_HANDLING_OPTION:
				setNullHandlingOption(NULL_HANDLING_OPTION_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT:
				setValueFunctionElement((XMLValueFunctionElement)null);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__ELEMENT_CONTENT_LIST_CHILDREN:
				getElementContentListChildren().clear();
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
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NULL_HANDLING_OPTION:
				return nullHandlingOption != NULL_HANDLING_OPTION_EDEFAULT;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT:
				return getValueFunctionElement() != null;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__ELEMENT_CONTENT_LIST_CHILDREN:
				return elementContentListChildren != null && !elementContentListChildren.isEmpty();
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
		result.append(" (nullHandlingOption: ");
		result.append(nullHandlingOption);
		result.append(')');
		return result.toString();
	}

} //XMLValueFunctionElementContentListImpl
