/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLQueryExpressionImpl.java,v 1.4 2008/07/07 19:55:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery;
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
 * An implementation of the model object '<em><b>XML Query Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryExpressionImpl#getXqueryExprContent <em>Xquery Expr Content</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryExpressionImpl#getPredicateExists <em>Predicate Exists</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryExpressionImpl#getValueFunctionQuery <em>Value Function Query</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLQueryExpressionImpl extends SQLQueryObjectImpl implements XMLQueryExpression {
	/**
     * The default value of the '{@link #getXqueryExprContent() <em>Xquery Expr Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXqueryExprContent()
     * @generated
     * @ordered
     */
    protected static final String XQUERY_EXPR_CONTENT_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getXqueryExprContent() <em>Xquery Expr Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXqueryExprContent()
     * @generated
     * @ordered
     */
    protected String xqueryExprContent = XQUERY_EXPR_CONTENT_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLQueryExpressionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_QUERY_EXPRESSION;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getXqueryExprContent() {
        return xqueryExprContent;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setXqueryExprContent(String newXqueryExprContent) {
        String oldXqueryExprContent = xqueryExprContent;
        xqueryExprContent = newXqueryExprContent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__XQUERY_EXPR_CONTENT, oldXqueryExprContent, xqueryExprContent));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLPredicateExists getPredicateExists() {
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS) return null;
        return (XMLPredicateExists)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetPredicateExists(XMLPredicateExists newPredicateExists, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newPredicateExists, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPredicateExists(XMLPredicateExists newPredicateExists) {
        if (newPredicateExists != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS && newPredicateExists != null)) {
            if (EcoreUtil.isAncestor(this, newPredicateExists))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newPredicateExists != null)
                msgs = ((InternalEObject)newPredicateExists).eInverseAdd(this, SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR, XMLPredicateExists.class, msgs);
            msgs = basicSetPredicateExists(newPredicateExists, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS, newPredicateExists, newPredicateExists));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionQuery getValueFunctionQuery() {
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY) return null;
        return (XMLValueFunctionQuery)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueFunctionQuery(XMLValueFunctionQuery newValueFunctionQuery, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueFunctionQuery, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueFunctionQuery(XMLValueFunctionQuery newValueFunctionQuery) {
        if (newValueFunctionQuery != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY && newValueFunctionQuery != null)) {
            if (EcoreUtil.isAncestor(this, newValueFunctionQuery))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueFunctionQuery != null)
                msgs = ((InternalEObject)newValueFunctionQuery).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR, XMLValueFunctionQuery.class, msgs);
            msgs = basicSetValueFunctionQuery(newValueFunctionQuery, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY, newValueFunctionQuery, newValueFunctionQuery));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetPredicateExists((XMLPredicateExists)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueFunctionQuery((XMLValueFunctionQuery)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS:
                return basicSetPredicateExists(null, msgs);
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY:
                return basicSetValueFunctionQuery(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_EXPR, XMLPredicateExists.class, msgs);
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR, XMLValueFunctionQuery.class, msgs);
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
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__XQUERY_EXPR_CONTENT:
                return getXqueryExprContent();
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS:
                return getPredicateExists();
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY:
                return getValueFunctionQuery();
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
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__XQUERY_EXPR_CONTENT:
                setXqueryExprContent((String)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS:
                setPredicateExists((XMLPredicateExists)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY:
                setValueFunctionQuery((XMLValueFunctionQuery)newValue);
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
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__XQUERY_EXPR_CONTENT:
                setXqueryExprContent(XQUERY_EXPR_CONTENT_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS:
                setPredicateExists((XMLPredicateExists)null);
                return;
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY:
                setValueFunctionQuery((XMLValueFunctionQuery)null);
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
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__XQUERY_EXPR_CONTENT:
                return XQUERY_EXPR_CONTENT_EDEFAULT == null ? xqueryExprContent != null : !XQUERY_EXPR_CONTENT_EDEFAULT.equals(xqueryExprContent);
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__PREDICATE_EXISTS:
                return getPredicateExists() != null;
            case SQLXMLQueryModelPackage.XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY:
                return getValueFunctionQuery() != null;
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
        result.append(" (xqueryExprContent: ");
        result.append(xqueryExprContent);
        result.append(')');
        return result.toString();
    }

} //XMLQueryExpressionImpl
