/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateAccordingToURIImpl.java,v 1.2 2005/12/17 01:52:31 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI;
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
 * An implementation of the model object '<em><b>XML Value Function Validate According To URI</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToURIImpl#isNoNamespace <em>No Namespace</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToURIImpl#getTargetNamespaceURI <em>Target Namespace URI</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToURIImpl#getSchemaLocationURI <em>Schema Location URI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionValidateAccordingToURIImpl extends XMLValueFunctionValidateAccordingToImpl implements XMLValueFunctionValidateAccordingToURI {
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
	 * The default value of the '{@link #getTargetNamespaceURI() <em>Target Namespace URI</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTargetNamespaceURI()
	 * @generated
	 * @ordered
	 */
    protected static final String TARGET_NAMESPACE_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetNamespaceURI() <em>Target Namespace URI</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTargetNamespaceURI()
	 * @generated
	 * @ordered
	 */
    protected String targetNamespaceURI = TARGET_NAMESPACE_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getSchemaLocationURI() <em>Schema Location URI</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSchemaLocationURI()
	 * @generated
	 * @ordered
	 */
    protected static final String SCHEMA_LOCATION_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSchemaLocationURI() <em>Schema Location URI</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSchemaLocationURI()
	 * @generated
	 * @ordered
	 */
    protected String schemaLocationURI = SCHEMA_LOCATION_URI_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLValueFunctionValidateAccordingToURIImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryPackage.eINSTANCE.getXMLValueFunctionValidateAccordingToURI();
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
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NO_NAMESPACE, oldNoNamespace, noNamespace));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getTargetNamespaceURI() {
		return targetNamespaceURI;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTargetNamespaceURI(String newTargetNamespaceURI) {
		String oldTargetNamespaceURI = targetNamespaceURI;
		targetNamespaceURI = newTargetNamespaceURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__TARGET_NAMESPACE_URI, oldTargetNamespaceURI, targetNamespaceURI));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getSchemaLocationURI() {
		return schemaLocationURI;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSchemaLocationURI(String newSchemaLocationURI) {
		String oldSchemaLocationURI = schemaLocationURI;
		schemaLocationURI = newSchemaLocationURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__SCHEMA_LOCATION_URI, oldSchemaLocationURI, schemaLocationURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALUE_FUNCTION_VALIDATE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALUE_FUNCTION_VALIDATE, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALIDATE_ELEMENT:
					if (validateElement != null)
						msgs = ((InternalEObject)validateElement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALIDATE_ELEMENT, null, msgs);
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
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALUE_FUNCTION_VALIDATE:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALUE_FUNCTION_VALIDATE, msgs);
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALIDATE_ELEMENT:
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
				case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALUE_FUNCTION_VALIDATE:
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NAME:
				return getName();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__LABEL:
				return getLabel();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALUE_FUNCTION_VALIDATE:
				return getValueFunctionValidate();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALIDATE_ELEMENT:
				return getValidateElement();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NO_NAMESPACE:
				return isNoNamespace() ? Boolean.TRUE : Boolean.FALSE;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__TARGET_NAMESPACE_URI:
				return getTargetNamespaceURI();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__SCHEMA_LOCATION_URI:
				return getSchemaLocationURI();
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALUE_FUNCTION_VALIDATE:
				setValueFunctionValidate((XMLValueFunctionValidate)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALIDATE_ELEMENT:
				setValidateElement((XMLValueFunctionValidateElement)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NO_NAMESPACE:
				setNoNamespace(((Boolean)newValue).booleanValue());
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__TARGET_NAMESPACE_URI:
				setTargetNamespaceURI((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__SCHEMA_LOCATION_URI:
				setSchemaLocationURI((String)newValue);
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALUE_FUNCTION_VALIDATE:
				setValueFunctionValidate((XMLValueFunctionValidate)null);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALIDATE_ELEMENT:
				setValidateElement((XMLValueFunctionValidateElement)null);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NO_NAMESPACE:
				setNoNamespace(NO_NAMESPACE_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__TARGET_NAMESPACE_URI:
				setTargetNamespaceURI(TARGET_NAMESPACE_URI_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__SCHEMA_LOCATION_URI:
				setSchemaLocationURI(SCHEMA_LOCATION_URI_EDEFAULT);
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
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALUE_FUNCTION_VALIDATE:
				return getValueFunctionValidate() != null;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALIDATE_ELEMENT:
				return validateElement != null;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NO_NAMESPACE:
				return noNamespace != NO_NAMESPACE_EDEFAULT;
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__TARGET_NAMESPACE_URI:
				return TARGET_NAMESPACE_URI_EDEFAULT == null ? targetNamespaceURI != null : !TARGET_NAMESPACE_URI_EDEFAULT.equals(targetNamespaceURI);
			case SQLXMLQueryPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__SCHEMA_LOCATION_URI:
				return SCHEMA_LOCATION_URI_EDEFAULT == null ? schemaLocationURI != null : !SCHEMA_LOCATION_URI_EDEFAULT.equals(schemaLocationURI);
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
		result.append(", targetNamespaceURI: ");
		result.append(targetNamespaceURI);
		result.append(", schemaLocationURI: ");
		result.append(schemaLocationURI);
		result.append(')');
		return result.toString();
	}

} //XMLValueFunctionValidateAccordingToURIImpl
