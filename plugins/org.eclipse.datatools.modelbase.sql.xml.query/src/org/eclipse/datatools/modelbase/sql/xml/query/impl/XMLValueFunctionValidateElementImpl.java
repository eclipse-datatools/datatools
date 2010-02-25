/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateElementImpl.java,v 1.4 2008/07/07 19:55:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
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
    protected XMLValueFunctionValidateElementNamespace validateElementNamespace;

	/**
     * The cached value of the '{@link #getValidateElementName() <em>Validate Element Name</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValidateElementName()
     * @generated
     * @ordered
     */
    protected XMLValueFunctionValidateElementName validateElementName;

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
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION_VALIDATE_ELEMENT;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE, oldValidateElementNamespace, newValidateElementNamespace);
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
                msgs = ((InternalEObject)validateElementNamespace).eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT, XMLValueFunctionValidateElementNamespace.class, msgs);
            if (newValidateElementNamespace != null)
                msgs = ((InternalEObject)newValidateElementNamespace).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT, XMLValueFunctionValidateElementNamespace.class, msgs);
            msgs = basicSetValidateElementNamespace(newValidateElementNamespace, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE, newValidateElementNamespace, newValidateElementNamespace));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME, oldValidateElementName, newValidateElementName);
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
                msgs = ((InternalEObject)validateElementName).eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT, XMLValueFunctionValidateElementName.class, msgs);
            if (newValidateElementName != null)
                msgs = ((InternalEObject)newValidateElementName).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT, XMLValueFunctionValidateElementName.class, msgs);
            msgs = basicSetValidateElementName(newValidateElementName, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME, newValidateElementName, newValidateElementName));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionValidateAccordingTo getValidateAccordingTo() {
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO) return null;
        return (XMLValueFunctionValidateAccordingTo)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValidateAccordingTo(XMLValueFunctionValidateAccordingTo newValidateAccordingTo, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValidateAccordingTo, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValidateAccordingTo(XMLValueFunctionValidateAccordingTo newValidateAccordingTo) {
        if (newValidateAccordingTo != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO && newValidateAccordingTo != null)) {
            if (EcoreUtil.isAncestor(this, newValidateAccordingTo))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValidateAccordingTo != null)
                msgs = ((InternalEObject)newValidateAccordingTo).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT, XMLValueFunctionValidateAccordingTo.class, msgs);
            msgs = basicSetValidateAccordingTo(newValidateAccordingTo, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO, newValidateAccordingTo, newValidateAccordingTo));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE:
                if (validateElementNamespace != null)
                    msgs = ((InternalEObject)validateElementNamespace).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE, null, msgs);
                return basicSetValidateElementNamespace((XMLValueFunctionValidateElementNamespace)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME:
                if (validateElementName != null)
                    msgs = ((InternalEObject)validateElementName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME, null, msgs);
                return basicSetValidateElementName((XMLValueFunctionValidateElementName)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValidateAccordingTo((XMLValueFunctionValidateAccordingTo)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE:
                return basicSetValidateElementNamespace(null, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME:
                return basicSetValidateElementName(null, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO:
                return basicSetValidateAccordingTo(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT, XMLValueFunctionValidateAccordingTo.class, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE:
                return getValidateElementNamespace();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME:
                return getValidateElementName();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO:
                return getValidateAccordingTo();
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE:
                setValidateElementNamespace((XMLValueFunctionValidateElementNamespace)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME:
                setValidateElementName((XMLValueFunctionValidateElementName)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO:
                setValidateAccordingTo((XMLValueFunctionValidateAccordingTo)newValue);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE:
                setValidateElementNamespace((XMLValueFunctionValidateElementNamespace)null);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME:
                setValidateElementName((XMLValueFunctionValidateElementName)null);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO:
                setValidateAccordingTo((XMLValueFunctionValidateAccordingTo)null);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE:
                return validateElementNamespace != null;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME:
                return validateElementName != null;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO:
                return getValidateAccordingTo() != null;
        }
        return super.eIsSet(featureID);
    }

} //XMLValueFunctionValidateElementImpl
