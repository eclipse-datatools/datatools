/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionParseImpl.java,v 1.5 2007/02/08 17:04:21 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLWhitespaceHandlingType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Function Parse</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionParseImpl#getContentOption <em>Content Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionParseImpl#getWhitespaceHandlingOption <em>Whitespace Handling Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionParseImpl#getParseContent <em>Parse Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionParseImpl extends XMLValueFunctionImpl implements XMLValueFunctionParse {
	/**
     * The default value of the '{@link #getContentOption() <em>Content Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContentOption()
     * @generated
     * @ordered
     */
    protected static final XMLContentType CONTENT_OPTION_EDEFAULT = XMLContentType.CONTENT_LITERAL;

	/**
     * The cached value of the '{@link #getContentOption() <em>Content Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContentOption()
     * @generated
     * @ordered
     */
    protected XMLContentType contentOption = CONTENT_OPTION_EDEFAULT;

	/**
     * The default value of the '{@link #getWhitespaceHandlingOption() <em>Whitespace Handling Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWhitespaceHandlingOption()
     * @generated
     * @ordered
     */
    protected static final XMLWhitespaceHandlingType WHITESPACE_HANDLING_OPTION_EDEFAULT = XMLWhitespaceHandlingType.PRESERE_WHITESPACE_LITERAL;

	/**
     * The cached value of the '{@link #getWhitespaceHandlingOption() <em>Whitespace Handling Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWhitespaceHandlingOption()
     * @generated
     * @ordered
     */
    protected XMLWhitespaceHandlingType whitespaceHandlingOption = WHITESPACE_HANDLING_OPTION_EDEFAULT;

	/**
     * The cached value of the '{@link #getParseContent() <em>Parse Content</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseContent()
     * @generated
     * @ordered
     */
    protected XMLValueFunctionParseContent parseContent;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLValueFunctionParseImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION_PARSE;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLContentType getContentOption() {
        return contentOption;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setContentOption(XMLContentType newContentOption) {
        XMLContentType oldContentOption = contentOption;
        contentOption = newContentOption == null ? CONTENT_OPTION_EDEFAULT : newContentOption;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__CONTENT_OPTION, oldContentOption, contentOption));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLWhitespaceHandlingType getWhitespaceHandlingOption() {
        return whitespaceHandlingOption;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWhitespaceHandlingOption(XMLWhitespaceHandlingType newWhitespaceHandlingOption) {
        XMLWhitespaceHandlingType oldWhitespaceHandlingOption = whitespaceHandlingOption;
        whitespaceHandlingOption = newWhitespaceHandlingOption == null ? WHITESPACE_HANDLING_OPTION_EDEFAULT : newWhitespaceHandlingOption;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__WHITESPACE_HANDLING_OPTION, oldWhitespaceHandlingOption, whitespaceHandlingOption));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionParseContent getParseContent() {
        return parseContent;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParseContent(XMLValueFunctionParseContent newParseContent, NotificationChain msgs) {
        XMLValueFunctionParseContent oldParseContent = parseContent;
        parseContent = newParseContent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__PARSE_CONTENT, oldParseContent, newParseContent);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParseContent(XMLValueFunctionParseContent newParseContent) {
        if (newParseContent != parseContent) {
            NotificationChain msgs = null;
            if (parseContent != null)
                msgs = ((InternalEObject)parseContent).eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_FUNCTION_PARSE, XMLValueFunctionParseContent.class, msgs);
            if (newParseContent != null)
                msgs = ((InternalEObject)newParseContent).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_FUNCTION_PARSE, XMLValueFunctionParseContent.class, msgs);
            msgs = basicSetParseContent(newParseContent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__PARSE_CONTENT, newParseContent, newParseContent));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__PARSE_CONTENT:
                if (parseContent != null)
                    msgs = ((InternalEObject)parseContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__PARSE_CONTENT, null, msgs);
                return basicSetParseContent((XMLValueFunctionParseContent)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__PARSE_CONTENT:
                return basicSetParseContent(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__CONTENT_OPTION:
                return getContentOption();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__WHITESPACE_HANDLING_OPTION:
                return getWhitespaceHandlingOption();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__PARSE_CONTENT:
                return getParseContent();
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__CONTENT_OPTION:
                setContentOption((XMLContentType)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__WHITESPACE_HANDLING_OPTION:
                setWhitespaceHandlingOption((XMLWhitespaceHandlingType)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__PARSE_CONTENT:
                setParseContent((XMLValueFunctionParseContent)newValue);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__CONTENT_OPTION:
                setContentOption(CONTENT_OPTION_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__WHITESPACE_HANDLING_OPTION:
                setWhitespaceHandlingOption(WHITESPACE_HANDLING_OPTION_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__PARSE_CONTENT:
                setParseContent((XMLValueFunctionParseContent)null);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__CONTENT_OPTION:
                return contentOption != CONTENT_OPTION_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__WHITESPACE_HANDLING_OPTION:
                return whitespaceHandlingOption != WHITESPACE_HANDLING_OPTION_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_PARSE__PARSE_CONTENT:
                return parseContent != null;
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
        result.append(" (contentOption: ");
        result.append(contentOption);
        result.append(", whitespaceHandlingOption: ");
        result.append(whitespaceHandlingOption);
        result.append(')');
        return result.toString();
    }

} //XMLValueFunctionParseImpl
