/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLNamespaceDeclarationItemImpl.java,v 1.1 2005/12/16 13:16:51 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration;
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
 * An implementation of the model object '<em><b>XML Namespace Declaration Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationItemImpl#getUri <em>Uri</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationItemImpl#getNamespacesDecl <em>Namespaces Decl</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLNamespaceDeclarationItemImpl extends SQLQueryObjectImpl implements XMLNamespaceDeclarationItem {
	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
    protected static final String URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
    protected String uri = URI_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLNamespaceDeclarationItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryModelPackage.eINSTANCE.getXMLNamespaceDeclarationItem();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getUri() {
		return uri;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUri(String newUri) {
		String oldUri = uri;
		uri = newUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__URI, oldUri, uri));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLNamespacesDeclaration getNamespacesDecl() {
		if (eContainerFeatureID != SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL) return null;
		return (XMLNamespacesDeclaration)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNamespacesDecl(XMLNamespacesDeclaration newNamespacesDecl) {
		if (newNamespacesDecl != eContainer || (eContainerFeatureID != SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL && newNamespacesDecl != null)) {
			if (EcoreUtil.isAncestor(this, newNamespacesDecl))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newNamespacesDecl != null)
				msgs = ((InternalEObject)newNamespacesDecl).eInverseAdd(this, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST, XMLNamespacesDeclaration.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newNamespacesDecl, SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL, newNamespacesDecl, newNamespacesDecl));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL, msgs);
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
				case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL:
					return eBasicSetContainer(null, SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL, msgs);
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
				case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL:
					return eContainer.eInverseRemove(this, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST, XMLNamespacesDeclaration.class, msgs);
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
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAME:
				return getName();
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__LABEL:
				return getLabel();
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__URI:
				return getUri();
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL:
				return getNamespacesDecl();
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
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__URI:
				setUri((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL:
				setNamespacesDecl((XMLNamespacesDeclaration)newValue);
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
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__URI:
				setUri(URI_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL:
				setNamespacesDecl((XMLNamespacesDeclaration)null);
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
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL:
				return getNamespacesDecl() != null;
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
		result.append(" (uri: ");
		result.append(uri);
		result.append(')');
		return result.toString();
	}

} //XMLNamespaceDeclarationItemImpl
