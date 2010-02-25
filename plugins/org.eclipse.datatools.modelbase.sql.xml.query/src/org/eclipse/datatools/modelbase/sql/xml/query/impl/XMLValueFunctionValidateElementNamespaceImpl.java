/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateElementNamespaceImpl.java,v 1.5 2008/07/07 19:55:14 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
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
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NO_NAMESPACE, oldNoNamespace, noNamespace));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAMESPACE_URI, oldNamespaceURI, namespaceURI));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionValidateElement getValidateElement() {
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT) return null;
        return (XMLValueFunctionValidateElement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValidateElement(XMLValueFunctionValidateElement newValidateElement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValidateElement, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValidateElement(XMLValueFunctionValidateElement newValidateElement) {
        if (newValidateElement != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT && newValidateElement != null)) {
            if (EcoreUtil.isAncestor(this, newValidateElement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValidateElement != null)
                msgs = ((InternalEObject)newValidateElement).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE, XMLValueFunctionValidateElement.class, msgs);
            msgs = basicSetValidateElement(newValidateElement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT, newValidateElement, newValidateElement));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValidateElement((XMLValueFunctionValidateElement)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT:
                return basicSetValidateElement(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE, XMLValueFunctionValidateElement.class, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NO_NAMESPACE:
                return isNoNamespace() ? Boolean.TRUE : Boolean.FALSE;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAMESPACE_URI:
                return getNamespaceURI();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT:
                return getValidateElement();
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NO_NAMESPACE:
                setNoNamespace(((Boolean)newValue).booleanValue());
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAMESPACE_URI:
                setNamespaceURI((String)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT:
                setValidateElement((XMLValueFunctionValidateElement)newValue);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NO_NAMESPACE:
                setNoNamespace(NO_NAMESPACE_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAMESPACE_URI:
                setNamespaceURI(NAMESPACE_URI_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT:
                setValidateElement((XMLValueFunctionValidateElement)null);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NO_NAMESPACE:
                return noNamespace != NO_NAMESPACE_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAMESPACE_URI:
                return NAMESPACE_URI_EDEFAULT == null ? namespaceURI != null : !NAMESPACE_URI_EDEFAULT.equals(namespaceURI);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT:
                return getValidateElement() != null;
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
        result.append(" (noNamespace: ");
        result.append(noNamespace);
        result.append(", namespaceURI: ");
        result.append(namespaceURI);
        result.append(')');
        return result.toString();
    }

} //XMLValueFunctionValidateElementNamespaceImpl
