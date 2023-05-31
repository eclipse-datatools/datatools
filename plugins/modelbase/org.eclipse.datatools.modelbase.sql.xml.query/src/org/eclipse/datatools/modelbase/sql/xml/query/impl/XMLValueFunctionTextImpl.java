/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionTextImpl.java,v 1.5 2007/02/08 17:04:21 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionText;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionTextContent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Function Text</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionTextImpl#getReturningOption <em>Returning Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionTextImpl#getTextContent <em>Text Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionTextImpl extends XMLValueFunctionImpl implements XMLValueFunctionText {
	/**
     * The default value of the '{@link #getReturningOption() <em>Returning Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReturningOption()
     * @generated
     * @ordered
     */
    protected static final XMLReturningType RETURNING_OPTION_EDEFAULT = XMLReturningType.RETURNING_CONTENT_LITERAL;

	/**
     * The cached value of the '{@link #getReturningOption() <em>Returning Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReturningOption()
     * @generated
     * @ordered
     */
    protected XMLReturningType returningOption = RETURNING_OPTION_EDEFAULT;

	/**
     * The cached value of the '{@link #getTextContent() <em>Text Content</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTextContent()
     * @generated
     * @ordered
     */
    protected XMLValueFunctionTextContent textContent;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLValueFunctionTextImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION_TEXT;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLReturningType getReturningOption() {
        return returningOption;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReturningOption(XMLReturningType newReturningOption) {
        XMLReturningType oldReturningOption = returningOption;
        returningOption = newReturningOption == null ? RETURNING_OPTION_EDEFAULT : newReturningOption;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT__RETURNING_OPTION, oldReturningOption, returningOption));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionTextContent getTextContent() {
        return textContent;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTextContent(XMLValueFunctionTextContent newTextContent, NotificationChain msgs) {
        XMLValueFunctionTextContent oldTextContent = textContent;
        textContent = newTextContent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT__TEXT_CONTENT, oldTextContent, newTextContent);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTextContent(XMLValueFunctionTextContent newTextContent) {
        if (newTextContent != textContent) {
            NotificationChain msgs = null;
            if (textContent != null)
                msgs = ((InternalEObject)textContent).eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_FUNCTION_TEXT, XMLValueFunctionTextContent.class, msgs);
            if (newTextContent != null)
                msgs = ((InternalEObject)newTextContent).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_FUNCTION_TEXT, XMLValueFunctionTextContent.class, msgs);
            msgs = basicSetTextContent(newTextContent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT__TEXT_CONTENT, newTextContent, newTextContent));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT__TEXT_CONTENT:
                if (textContent != null)
                    msgs = ((InternalEObject)textContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT__TEXT_CONTENT, null, msgs);
                return basicSetTextContent((XMLValueFunctionTextContent)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT__TEXT_CONTENT:
                return basicSetTextContent(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT__RETURNING_OPTION:
                return getReturningOption();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT__TEXT_CONTENT:
                return getTextContent();
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT__RETURNING_OPTION:
                setReturningOption((XMLReturningType)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT__TEXT_CONTENT:
                setTextContent((XMLValueFunctionTextContent)newValue);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT__RETURNING_OPTION:
                setReturningOption(RETURNING_OPTION_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT__TEXT_CONTENT:
                setTextContent((XMLValueFunctionTextContent)null);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT__RETURNING_OPTION:
                return returningOption != RETURNING_OPTION_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_TEXT__TEXT_CONTENT:
                return textContent != null;
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
        result.append(" (returningOption: ");
        result.append(returningOption);
        result.append(')');
        return result.toString();
    }

} //XMLValueFunctionTextImpl
