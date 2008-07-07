/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateImpl.java,v 1.5 2007/02/08 17:04:20 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType2;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateContent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Function Validate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateImpl#getContentOption <em>Content Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateImpl#getValidateContent <em>Validate Content</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateImpl#getValidateAccordingTo <em>Validate According To</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionValidateImpl extends XMLValueFunctionImpl implements XMLValueFunctionValidate {
	/**
     * The default value of the '{@link #getContentOption() <em>Content Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContentOption()
     * @generated
     * @ordered
     */
    protected static final XMLContentType2 CONTENT_OPTION_EDEFAULT = XMLContentType2.CONTENT_LITERAL;

	/**
     * The cached value of the '{@link #getContentOption() <em>Content Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContentOption()
     * @generated
     * @ordered
     */
    protected XMLContentType2 contentOption = CONTENT_OPTION_EDEFAULT;

	/**
     * The cached value of the '{@link #getValidateContent() <em>Validate Content</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValidateContent()
     * @generated
     * @ordered
     */
    protected XMLValueFunctionValidateContent validateContent;

	/**
     * The cached value of the '{@link #getValidateAccordingTo() <em>Validate According To</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValidateAccordingTo()
     * @generated
     * @ordered
     */
    protected XMLValueFunctionValidateAccordingTo validateAccordingTo;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLValueFunctionValidateImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION_VALIDATE;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLContentType2 getContentOption() {
        return contentOption;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setContentOption(XMLContentType2 newContentOption) {
        XMLContentType2 oldContentOption = contentOption;
        contentOption = newContentOption == null ? CONTENT_OPTION_EDEFAULT : newContentOption;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__CONTENT_OPTION, oldContentOption, contentOption));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionValidateContent getValidateContent() {
        return validateContent;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetValidateContent(XMLValueFunctionValidateContent newValidateContent, NotificationChain msgs) {
        XMLValueFunctionValidateContent oldValidateContent = validateContent;
        validateContent = newValidateContent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_CONTENT, oldValidateContent, newValidateContent);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValidateContent(XMLValueFunctionValidateContent newValidateContent) {
        if (newValidateContent != validateContent) {
            NotificationChain msgs = null;
            if (validateContent != null)
                msgs = ((InternalEObject)validateContent).eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_FUNCTION_VALIDATE, XMLValueFunctionValidateContent.class, msgs);
            if (newValidateContent != null)
                msgs = ((InternalEObject)newValidateContent).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_FUNCTION_VALIDATE, XMLValueFunctionValidateContent.class, msgs);
            msgs = basicSetValidateContent(newValidateContent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_CONTENT, newValidateContent, newValidateContent));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionValidateAccordingTo getValidateAccordingTo() {
        return validateAccordingTo;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetValidateAccordingTo(XMLValueFunctionValidateAccordingTo newValidateAccordingTo, NotificationChain msgs) {
        XMLValueFunctionValidateAccordingTo oldValidateAccordingTo = validateAccordingTo;
        validateAccordingTo = newValidateAccordingTo;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_ACCORDING_TO, oldValidateAccordingTo, newValidateAccordingTo);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValidateAccordingTo(XMLValueFunctionValidateAccordingTo newValidateAccordingTo) {
        if (newValidateAccordingTo != validateAccordingTo) {
            NotificationChain msgs = null;
            if (validateAccordingTo != null)
                msgs = ((InternalEObject)validateAccordingTo).eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE, XMLValueFunctionValidateAccordingTo.class, msgs);
            if (newValidateAccordingTo != null)
                msgs = ((InternalEObject)newValidateAccordingTo).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE, XMLValueFunctionValidateAccordingTo.class, msgs);
            msgs = basicSetValidateAccordingTo(newValidateAccordingTo, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_ACCORDING_TO, newValidateAccordingTo, newValidateAccordingTo));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_CONTENT:
                if (validateContent != null)
                    msgs = ((InternalEObject)validateContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_CONTENT, null, msgs);
                return basicSetValidateContent((XMLValueFunctionValidateContent)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_ACCORDING_TO:
                if (validateAccordingTo != null)
                    msgs = ((InternalEObject)validateAccordingTo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_ACCORDING_TO, null, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_CONTENT:
                return basicSetValidateContent(null, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_ACCORDING_TO:
                return basicSetValidateAccordingTo(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__CONTENT_OPTION:
                return getContentOption();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_CONTENT:
                return getValidateContent();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_ACCORDING_TO:
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__CONTENT_OPTION:
                setContentOption((XMLContentType2)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_CONTENT:
                setValidateContent((XMLValueFunctionValidateContent)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_ACCORDING_TO:
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__CONTENT_OPTION:
                setContentOption(CONTENT_OPTION_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_CONTENT:
                setValidateContent((XMLValueFunctionValidateContent)null);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_ACCORDING_TO:
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__CONTENT_OPTION:
                return contentOption != CONTENT_OPTION_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_CONTENT:
                return validateContent != null;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE__VALIDATE_ACCORDING_TO:
                return validateAccordingTo != null;
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
        result.append(')');
        return result.toString();
    }

} //XMLValueFunctionValidateImpl
