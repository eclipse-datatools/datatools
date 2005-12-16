/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLNamespaceDeclarationDefaultImpl.java,v 1.4 2005/10/22 01:40:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationDefault;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Namespace Declaration Default</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationDefaultImpl#isNoDefault <em>No Default</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLNamespaceDeclarationDefaultImpl extends XMLNamespaceDeclarationItemImpl implements XMLNamespaceDeclarationDefault {
	/**
	 * The default value of the '{@link #isNoDefault() <em>No Default</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isNoDefault()
	 * @generated
	 * @ordered
	 */
    protected static final boolean NO_DEFAULT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNoDefault() <em>No Default</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isNoDefault()
	 * @generated
	 * @ordered
	 */
    protected boolean noDefault = NO_DEFAULT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLNamespaceDeclarationDefaultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryPackage.eINSTANCE.getXMLNamespaceDeclarationDefault();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isNoDefault() {
		return noDefault;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNoDefault(boolean newNoDefault) {
		boolean oldNoDefault = noDefault;
		noDefault = newNoDefault;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__NO_DEFAULT, oldNoDefault, noDefault));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__NAME:
				return getName();
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__LABEL:
				return getLabel();
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__URI:
				return getUri();
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__NAMESPACES_DECL:
				return getNamespacesDecl();
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__NO_DEFAULT:
				return isNoDefault() ? Boolean.TRUE : Boolean.FALSE;
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
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__URI:
				setUri((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__NAMESPACES_DECL:
				setNamespacesDecl((XMLNamespacesDeclaration)newValue);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__NO_DEFAULT:
				setNoDefault(((Boolean)newValue).booleanValue());
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
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__URI:
				setUri(URI_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__NAMESPACES_DECL:
				setNamespacesDecl((XMLNamespacesDeclaration)null);
				return;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__NO_DEFAULT:
				setNoDefault(NO_DEFAULT_EDEFAULT);
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
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__NAMESPACES_DECL:
				return getNamespacesDecl() != null;
			case SQLXMLQueryPackage.XML_NAMESPACE_DECLARATION_DEFAULT__NO_DEFAULT:
				return noDefault != NO_DEFAULT_EDEFAULT;
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
		result.append(" (noDefault: ");
		result.append(noDefault);
		result.append(')');
		return result.toString();
	}

} //XMLNamespaceDeclarationDefaultImpl
