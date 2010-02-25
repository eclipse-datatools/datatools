/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionElementContentListImpl.java,v 1.4 2008/07/07 19:55:15 bpayton Exp $
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
    protected EList elementContentListChildren;

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
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST;
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
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT) return null;
        return (XMLValueFunctionElement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueFunctionElement(XMLValueFunctionElement newValueFunctionElement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueFunctionElement, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueFunctionElement(XMLValueFunctionElement newValueFunctionElement) {
        if (newValueFunctionElement != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT && newValueFunctionElement != null)) {
            if (EcoreUtil.isAncestor(this, newValueFunctionElement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueFunctionElement != null)
                msgs = ((InternalEObject)newValueFunctionElement).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST, XMLValueFunctionElement.class, msgs);
            msgs = basicSetValueFunctionElement(newValueFunctionElement, msgs);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueFunctionElement((XMLValueFunctionElement)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__ELEMENT_CONTENT_LIST_CHILDREN:
                return ((InternalEList)getElementContentListChildren()).basicAdd(otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT:
                return basicSetValueFunctionElement(null, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__ELEMENT_CONTENT_LIST_CHILDREN:
                return ((InternalEList)getElementContentListChildren()).basicRemove(otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST, XMLValueFunctionElement.class, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NULL_HANDLING_OPTION:
                return getNullHandlingOption();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT:
                return getValueFunctionElement();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__ELEMENT_CONTENT_LIST_CHILDREN:
                return getElementContentListChildren();
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
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eUnset(int featureID) {
        switch (featureID) {
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
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NULL_HANDLING_OPTION:
                return nullHandlingOption != NULL_HANDLING_OPTION_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT:
                return getValueFunctionElement() != null;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__ELEMENT_CONTENT_LIST_CHILDREN:
                return elementContentListChildren != null && !elementContentListChildren.isEmpty();
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
        result.append(" (nullHandlingOption: ");
        result.append(nullHandlingOption);
        result.append(')');
        return result.toString();
    }

} //XMLValueFunctionElementContentListImpl
