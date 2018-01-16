/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLPredicateExistsImpl.java,v 1.5 2007/02/08 17:04:21 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Predicate Exists</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateExistsImpl#getXqueryExpr <em>Xquery Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateExistsImpl#getXqueryArgList <em>Xquery Arg List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLPredicateExistsImpl extends XMLPredicateImpl implements XMLPredicateExists {
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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLPredicateExistsImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_PREDICATE_EXISTS;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR, oldXqueryExpr, newXqueryExpr);
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
                msgs = ((InternalEObject)xqueryExpr).eInverseRemove(this, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS, XMLQueryExpression.class, msgs);
            if (newXqueryExpr != null)
                msgs = ((InternalEObject)newXqueryExpr).eInverseAdd(this, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS, XMLQueryExpression.class, msgs);
            msgs = basicSetXqueryExpr(newXqueryExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR, newXqueryExpr, newXqueryExpr));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST, oldXqueryArgList, newXqueryArgList);
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
                msgs = ((InternalEObject)xqueryArgList).eInverseRemove(this, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS, XMLQueryArgumentList.class, msgs);
            if (newXqueryArgList != null)
                msgs = ((InternalEObject)newXqueryArgList).eInverseAdd(this, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS, XMLQueryArgumentList.class, msgs);
            msgs = basicSetXqueryArgList(newXqueryArgList, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST, newXqueryArgList, newXqueryArgList));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR:
                if (xqueryExpr != null)
                    msgs = ((InternalEObject)xqueryExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR, null, msgs);
                return basicSetXqueryExpr((XMLQueryExpression)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST:
                if (xqueryArgList != null)
                    msgs = ((InternalEObject)xqueryArgList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST, null, msgs);
                return basicSetXqueryArgList((XMLQueryArgumentList)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR:
                return basicSetXqueryExpr(null, msgs);
            case SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST:
                return basicSetXqueryArgList(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR:
                return getXqueryExpr();
            case SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST:
                return getXqueryArgList();
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
            case SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR:
                setXqueryExpr((XMLQueryExpression)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST:
                setXqueryArgList((XMLQueryArgumentList)newValue);
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
            case SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR:
                setXqueryExpr((XMLQueryExpression)null);
                return;
            case SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST:
                setXqueryArgList((XMLQueryArgumentList)null);
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
            case SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR:
                return xqueryExpr != null;
            case SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST:
                return xqueryArgList != null;
        }
        return super.eIsSet(featureID);
    }

} //XMLPredicateExistsImpl
