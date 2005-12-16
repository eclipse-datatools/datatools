/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateElementNamespaceImpl.java,v 1.4 2005/10/22 01:40:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement;
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
 * An implementation of the model object '<em><b>XML Value Function Validate Element Namespace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementNamespaceImpl#isNoNamespace <em>No Namespace</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementNamespaceImpl#getNamespaceURI <em>Namespace URI</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementNamespaceImpl#getValidateElement <em>Validate Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionValidateElementNamespaceImpl extends SQLQueryObjectImpl implements XMLValueFunctionValidateElementNamespace {
	/**
	 * The default value of the '{@link #isNoNamespace() <em>No Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isNoNamespace()
	 * @generated
	 * @ordered
	 */
    protected static final boolean NO_NAMESPACE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNoNamespace() <em>No Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isNoNamespace()
	 * @generated
	 * @ordered
	 */
    protected boolean noNamespace = NO_NAMESPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getNamespaceURI() <em>Namespace URI</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNamespaceURI()
	 * @generated
	 * @ordered
	 */
    protected static final String NAMESPACE_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNamespaceURI() <em>Namespace URI</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNamespaceURI()
	 * @generated
	 * @ordered
	 */
    protected String namespaceURI = NAMESPACE_URI_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLValueFunctionValidateElementNamespaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryPackage.eINSTANCE.getXMLValueFunctionValidateElementNamespace();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isNoNamespace() {
		return noNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNoNamespace(boolean newNoNamespace) {
		boolean oldNoNamespace = noNamespace;
		noNamespace = newNoNamespace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NO_NAMESPACE, oldNoNamespace, noNamespace));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getNamespaceURI() {
		return namespaceURI;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNamespaceURI(String newNamespaceURI) {
		String oldNamespaceURI = namespaceURI;
		namespaceURI = newNamespaceURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAMESPACE_URI, oldNamespaceURI, namespaceURI));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionValidateElement getValidateElement() {
		if (eContainerFeatureID != SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT) return null;
		return (XMLValueFunctionValidateElement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValidateElement(XMLValueFunctionValidateElement newValidateElement) {
		if (newValidateElement != eContainer || (eContainerFeatureID != SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT && newValidateElement != null)) {
			if (EcoreUtil.isAncestor(this, newValidateElement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValidateElement != null)
				msgs = ((InternalEObject)newValidateElement).eInverseAdd(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE, XMLValueFunctionValidateElement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValidateElement, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT, newValidateElement, newValidateElement));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT, msgs);
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
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT, msgs);
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
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT:
					return eContainer.eInverseRemove(this, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE, XMLValueFunctionValidateElement.class, msgs);
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAME:
				return getName();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__LABEL:
				return getLabel();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NO_NAMESPACE:
				return isNoNamespace() ? Boolean.TRUE : Boolean.FALSE;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAMESPACE_URI:
				return getNamespaceURI();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT:
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NO_NAMESPACE:
				setNoNamespace(((Boolean)newValue).booleanValue());
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAMESPACE_URI:
				setNamespaceURI((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT:
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NO_NAMESPACE:
				setNoNamespace(NO_NAMESPACE_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAMESPACE_URI:
				setNamespaceURI(NAMESPACE_URI_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT:
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NO_NAMESPACE:
				return noNamespace != NO_NAMESPACE_EDEFAULT;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAMESPACE_URI:
				return NAMESPACE_URI_EDEFAULT == null ? namespaceURI != null : !NAMESPACE_URI_EDEFAULT.equals(namespaceURI);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT:
				return getValidateElement() != null;
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
		result.append(" (noNamespace: ");
		result.append(noNamespace);
		result.append(", namespaceURI: ");
		result.append(namespaceURI);
		result.append(')');
		return result.toString();
	}

} //XMLValueFunctionValidateElementNamespaceImpl
