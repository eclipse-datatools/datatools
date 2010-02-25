/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLNamespaceDeclarationItemImpl.java,v 1.4 2008/07/07 19:55:14 bpayton Exp $
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
        return SQLXMLQueryModelPackage.Literals.XML_NAMESPACE_DECLARATION_ITEM;
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
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL) return null;
        return (XMLNamespacesDeclaration)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetNamespacesDecl(XMLNamespacesDeclaration newNamespacesDecl, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newNamespacesDecl, SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNamespacesDecl(XMLNamespacesDeclaration newNamespacesDecl) {
        if (newNamespacesDecl != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL && newNamespacesDecl != null)) {
            if (EcoreUtil.isAncestor(this, newNamespacesDecl))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newNamespacesDecl != null)
                msgs = ((InternalEObject)newNamespacesDecl).eInverseAdd(this, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST, XMLNamespacesDeclaration.class, msgs);
            msgs = basicSetNamespacesDecl(newNamespacesDecl, msgs);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetNamespacesDecl((XMLNamespacesDeclaration)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL:
                return basicSetNamespacesDecl(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST, XMLNamespacesDeclaration.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__URI:
                return getUri();
            case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL:
                return getNamespacesDecl();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__URI:
                setUri((String)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL:
                setNamespacesDecl((XMLNamespacesDeclaration)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eUnset(int featureID) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__URI:
                setUri(URI_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL:
                setNamespacesDecl((XMLNamespacesDeclaration)null);
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__URI:
                return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
            case SQLXMLQueryModelPackage.XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL:
                return getNamespacesDecl() != null;
        }
        return super.eIsSet(featureID);
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
