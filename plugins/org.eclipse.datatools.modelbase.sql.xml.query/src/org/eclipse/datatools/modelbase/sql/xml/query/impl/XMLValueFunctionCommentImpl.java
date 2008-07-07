/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionCommentImpl.java,v 1.5 2007/02/08 17:04:20 bpayton Exp $
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
import org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Function Comment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionCommentImpl#getReturningOption <em>Returning Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionCommentImpl#getCommentContent <em>Comment Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionCommentImpl extends XMLValueFunctionImpl implements XMLValueFunctionComment {
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
     * The cached value of the '{@link #getCommentContent() <em>Comment Content</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCommentContent()
     * @generated
     * @ordered
     */
    protected XMLValueFunctionCommentContent commentContent;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLValueFunctionCommentImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION_COMMENT;
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
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT__RETURNING_OPTION, oldReturningOption, returningOption));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionCommentContent getCommentContent() {
        return commentContent;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCommentContent(XMLValueFunctionCommentContent newCommentContent, NotificationChain msgs) {
        XMLValueFunctionCommentContent oldCommentContent = commentContent;
        commentContent = newCommentContent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT__COMMENT_CONTENT, oldCommentContent, newCommentContent);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCommentContent(XMLValueFunctionCommentContent newCommentContent) {
        if (newCommentContent != commentContent) {
            NotificationChain msgs = null;
            if (commentContent != null)
                msgs = ((InternalEObject)commentContent).eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_FUNCTION_COMMENT, XMLValueFunctionCommentContent.class, msgs);
            if (newCommentContent != null)
                msgs = ((InternalEObject)newCommentContent).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_FUNCTION_COMMENT, XMLValueFunctionCommentContent.class, msgs);
            msgs = basicSetCommentContent(newCommentContent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT__COMMENT_CONTENT, newCommentContent, newCommentContent));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT__COMMENT_CONTENT:
                if (commentContent != null)
                    msgs = ((InternalEObject)commentContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT__COMMENT_CONTENT, null, msgs);
                return basicSetCommentContent((XMLValueFunctionCommentContent)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT__COMMENT_CONTENT:
                return basicSetCommentContent(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT__RETURNING_OPTION:
                return getReturningOption();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT__COMMENT_CONTENT:
                return getCommentContent();
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT__RETURNING_OPTION:
                setReturningOption((XMLReturningType)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT__COMMENT_CONTENT:
                setCommentContent((XMLValueFunctionCommentContent)newValue);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT__RETURNING_OPTION:
                setReturningOption(RETURNING_OPTION_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT__COMMENT_CONTENT:
                setCommentContent((XMLValueFunctionCommentContent)null);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT__RETURNING_OPTION:
                return returningOption != RETURNING_OPTION_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_COMMENT__COMMENT_CONTENT:
                return commentContent != null;
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

} //XMLValueFunctionCommentImpl
