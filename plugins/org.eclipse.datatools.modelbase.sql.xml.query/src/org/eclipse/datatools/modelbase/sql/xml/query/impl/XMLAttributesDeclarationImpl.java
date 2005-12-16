/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLAttributesDeclarationImpl.java,v 1.2 2005/10/22 01:40:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Attributes Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAttributesDeclarationImpl#getValueFunctionElement <em>Value Function Element</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAttributesDeclarationImpl#getAttributeDeclItem <em>Attribute Decl Item</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLAttributesDeclarationImpl extends EObjectImpl implements XMLAttributesDeclaration {
	/**
	 * The cached value of the '{@link #getAttributeDeclItem() <em>Attribute Decl Item</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAttributeDeclItem()
	 * @generated
	 * @ordered
	 */
    protected EList attributeDeclItem = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLAttributesDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryPackage.eINSTANCE.getXMLAttributesDeclaration();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionElement getValueFunctionElement() {
		if (eContainerFeatureID != SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT) return null;
		return (XMLValueFunctionElement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueFunctionElement(XMLValueFunctionElement newValueFunctionElement) {
		if (newValueFunctionElement != eContainer || (eContainerFeatureID != SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT && newValueFunctionElement != null)) {
			if (EcoreUtil.isAncestor(this, newValueFunctionElement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueFunctionElement != null)
				msgs = ((InternalEObject)newValueFunctionElement).eInverseAdd(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_ELEMENT__ATTRIBUTES_DECL, XMLValueFunctionElement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueFunctionElement, SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT, newValueFunctionElement, newValueFunctionElement));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getAttributeDeclItem() {
		if (attributeDeclItem == null) {
			attributeDeclItem = new EObjectContainmentWithInverseEList(XMLAttributeDeclarationItem.class, this, SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__ATTRIBUTE_DECL_ITEM, SQLXMLQueryPackage.XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL);
		}
		return attributeDeclItem;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT, msgs);
				case SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__ATTRIBUTE_DECL_ITEM:
					return ((InternalEList)getAttributeDeclItem()).basicAdd(otherEnd, msgs);
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
				case SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT, msgs);
				case SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__ATTRIBUTE_DECL_ITEM:
					return ((InternalEList)getAttributeDeclItem()).basicRemove(otherEnd, msgs);
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
				case SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT:
					return eContainer.eInverseRemove(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_ELEMENT__ATTRIBUTES_DECL, XMLValueFunctionElement.class, msgs);
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
			case SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT:
				return getValueFunctionElement();
			case SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__ATTRIBUTE_DECL_ITEM:
				return getAttributeDeclItem();
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
			case SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT:
				setValueFunctionElement((XMLValueFunctionElement)newValue);
				return;
			case SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__ATTRIBUTE_DECL_ITEM:
				getAttributeDeclItem().clear();
				getAttributeDeclItem().addAll((Collection)newValue);
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
			case SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT:
				setValueFunctionElement((XMLValueFunctionElement)null);
				return;
			case SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__ATTRIBUTE_DECL_ITEM:
				getAttributeDeclItem().clear();
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
			case SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT:
				return getValueFunctionElement() != null;
			case SQLXMLQueryPackage.XML_ATTRIBUTES_DECLARATION__ATTRIBUTE_DECL_ITEM:
				return attributeDeclItem != null && !attributeDeclItem.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //XMLAttributesDeclarationImpl
