/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateElementNameImpl.java,v 1.5 2008/07/07 19:55:14 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementName;
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
 * An implementation of the model object '<em><b>XML Value Function Validate Element Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementNameImpl#getValidateElement <em>Validate Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionValidateElementNameImpl extends SQLQueryObjectImpl implements XMLValueFunctionValidateElementName {
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLValueFunctionValidateElementNameImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionValidateElement getValidateElement() {
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT) return null;
        return (XMLValueFunctionValidateElement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValidateElement(XMLValueFunctionValidateElement newValidateElement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValidateElement, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValidateElement(XMLValueFunctionValidateElement newValidateElement) {
        if (newValidateElement != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT && newValidateElement != null)) {
            if (EcoreUtil.isAncestor(this, newValidateElement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValidateElement != null)
                msgs = ((InternalEObject)newValidateElement).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME, XMLValueFunctionValidateElement.class, msgs);
            msgs = basicSetValidateElement(newValidateElement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT, newValidateElement, newValidateElement));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT:
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT:
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME, XMLValueFunctionValidateElement.class, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT:
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT:
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT:
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT:
                return getValidateElement() != null;
        }
        return super.eIsSet(featureID);
    }

} //XMLValueFunctionValidateElementNameImpl
