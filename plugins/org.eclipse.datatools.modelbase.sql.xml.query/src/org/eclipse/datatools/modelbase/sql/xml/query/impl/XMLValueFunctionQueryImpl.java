/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionQueryImpl.java,v 1.5 2007/02/08 17:04:21 bpayton Exp $
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
import org.eclipse.datatools.modelbase.sql.xml.query.XMLEmptyHandlingType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Function Query</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryImpl#getEmptyHandlingOption <em>Empty Handling Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryImpl#getXqueryExpr <em>Xquery Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryImpl#getXqueryArgList <em>Xquery Arg List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryImpl#getQueryReturning <em>Query Returning</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionQueryImpl extends XMLValueFunctionImpl implements XMLValueFunctionQuery {
	/**
     * The default value of the '{@link #getEmptyHandlingOption() <em>Empty Handling Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEmptyHandlingOption()
     * @generated
     * @ordered
     */
    protected static final XMLEmptyHandlingType EMPTY_HANDLING_OPTION_EDEFAULT = XMLEmptyHandlingType.EMPTY_ON_EMPTY_LITERAL;

	/**
     * The cached value of the '{@link #getEmptyHandlingOption() <em>Empty Handling Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEmptyHandlingOption()
     * @generated
     * @ordered
     */
    protected XMLEmptyHandlingType emptyHandlingOption = EMPTY_HANDLING_OPTION_EDEFAULT;

	/**
     * The cached value of the '{@link #getXqueryExpr() <em>Xquery Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXqueryExpr()
     * @generated
     * @ordered
     */
    protected XMLQueryExpression xqueryExpr;

	/**
     * The cached value of the '{@link #getXqueryArgList() <em>Xquery Arg List</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXqueryArgList()
     * @generated
     * @ordered
     */
    protected XMLQueryArgumentList xqueryArgList;

	/**
     * The cached value of the '{@link #getQueryReturning() <em>Query Returning</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQueryReturning()
     * @generated
     * @ordered
     */
    protected XMLValueFunctionQueryReturning queryReturning;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLValueFunctionQueryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION_QUERY;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLEmptyHandlingType getEmptyHandlingOption() {
        return emptyHandlingOption;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEmptyHandlingOption(XMLEmptyHandlingType newEmptyHandlingOption) {
        XMLEmptyHandlingType oldEmptyHandlingOption = emptyHandlingOption;
        emptyHandlingOption = newEmptyHandlingOption == null ? EMPTY_HANDLING_OPTION_EDEFAULT : newEmptyHandlingOption;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__EMPTY_HANDLING_OPTION, oldEmptyHandlingOption, emptyHandlingOption));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLQueryExpression getXqueryExpr() {
        return xqueryExpr;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetXqueryExpr(XMLQueryExpression newXqueryExpr, NotificationChain msgs) {
        XMLQueryExpression oldXqueryExpr = xqueryExpr;
        xqueryExpr = newXqueryExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR, oldXqueryExpr, newXqueryExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setXqueryExpr(XMLQueryExpression newXqueryExpr) {
        if (newXqueryExpr != xqueryExpr) {
            NotificationChain msgs = null;
            if (xqueryExpr != null)
                msgs = ((InternalEObject)xqueryExpr).eInverseRemove(this, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY, XMLQueryExpression.class, msgs);
            if (newXqueryExpr != null)
                msgs = ((InternalEObject)newXqueryExpr).eInverseAdd(this, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY, XMLQueryExpression.class, msgs);
            msgs = basicSetXqueryExpr(newXqueryExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR, newXqueryExpr, newXqueryExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLQueryArgumentList getXqueryArgList() {
        return xqueryArgList;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetXqueryArgList(XMLQueryArgumentList newXqueryArgList, NotificationChain msgs) {
        XMLQueryArgumentList oldXqueryArgList = xqueryArgList;
        xqueryArgList = newXqueryArgList;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST, oldXqueryArgList, newXqueryArgList);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setXqueryArgList(XMLQueryArgumentList newXqueryArgList) {
        if (newXqueryArgList != xqueryArgList) {
            NotificationChain msgs = null;
            if (xqueryArgList != null)
                msgs = ((InternalEObject)xqueryArgList).eInverseRemove(this, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY, XMLQueryArgumentList.class, msgs);
            if (newXqueryArgList != null)
                msgs = ((InternalEObject)newXqueryArgList).eInverseAdd(this, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY, XMLQueryArgumentList.class, msgs);
            msgs = basicSetXqueryArgList(newXqueryArgList, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST, newXqueryArgList, newXqueryArgList));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionQueryReturning getQueryReturning() {
        return queryReturning;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetQueryReturning(XMLValueFunctionQueryReturning newQueryReturning, NotificationChain msgs) {
        XMLValueFunctionQueryReturning oldQueryReturning = queryReturning;
        queryReturning = newQueryReturning;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__QUERY_RETURNING, oldQueryReturning, newQueryReturning);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQueryReturning(XMLValueFunctionQueryReturning newQueryReturning) {
        if (newQueryReturning != queryReturning) {
            NotificationChain msgs = null;
            if (queryReturning != null)
                msgs = ((InternalEObject)queryReturning).eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY, XMLValueFunctionQueryReturning.class, msgs);
            if (newQueryReturning != null)
                msgs = ((InternalEObject)newQueryReturning).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY, XMLValueFunctionQueryReturning.class, msgs);
            msgs = basicSetQueryReturning(newQueryReturning, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__QUERY_RETURNING, newQueryReturning, newQueryReturning));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR:
                if (xqueryExpr != null)
                    msgs = ((InternalEObject)xqueryExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR, null, msgs);
                return basicSetXqueryExpr((XMLQueryExpression)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST:
                if (xqueryArgList != null)
                    msgs = ((InternalEObject)xqueryArgList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST, null, msgs);
                return basicSetXqueryArgList((XMLQueryArgumentList)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__QUERY_RETURNING:
                if (queryReturning != null)
                    msgs = ((InternalEObject)queryReturning).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__QUERY_RETURNING, null, msgs);
                return basicSetQueryReturning((XMLValueFunctionQueryReturning)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR:
                return basicSetXqueryExpr(null, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST:
                return basicSetXqueryArgList(null, msgs);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__QUERY_RETURNING:
                return basicSetQueryReturning(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__EMPTY_HANDLING_OPTION:
                return getEmptyHandlingOption();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR:
                return getXqueryExpr();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST:
                return getXqueryArgList();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__QUERY_RETURNING:
                return getQueryReturning();
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__EMPTY_HANDLING_OPTION:
                setEmptyHandlingOption((XMLEmptyHandlingType)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR:
                setXqueryExpr((XMLQueryExpression)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST:
                setXqueryArgList((XMLQueryArgumentList)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__QUERY_RETURNING:
                setQueryReturning((XMLValueFunctionQueryReturning)newValue);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__EMPTY_HANDLING_OPTION:
                setEmptyHandlingOption(EMPTY_HANDLING_OPTION_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR:
                setXqueryExpr((XMLQueryExpression)null);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST:
                setXqueryArgList((XMLQueryArgumentList)null);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__QUERY_RETURNING:
                setQueryReturning((XMLValueFunctionQueryReturning)null);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__EMPTY_HANDLING_OPTION:
                return emptyHandlingOption != EMPTY_HANDLING_OPTION_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR:
                return xqueryExpr != null;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST:
                return xqueryArgList != null;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__QUERY_RETURNING:
                return queryReturning != null;
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
        result.append(" (emptyHandlingOption: ");
        result.append(emptyHandlingOption);
        result.append(')');
        return result.toString();
    }

} //XMLValueFunctionQueryImpl
