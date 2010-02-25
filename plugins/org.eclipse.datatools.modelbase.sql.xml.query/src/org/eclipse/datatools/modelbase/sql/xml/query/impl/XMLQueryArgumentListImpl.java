/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLQueryArgumentListImpl.java,v 1.4 2008/07/07 19:55:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery;
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
 * An implementation of the model object '<em><b>XML Query Argument List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentListImpl#getPassingMechanism <em>Passing Mechanism</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentListImpl#getPredicateExists <em>Predicate Exists</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentListImpl#getXqueryArgListChildren <em>Xquery Arg List Children</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentListImpl#getValueFunctionQuery <em>Value Function Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentListImpl#getTableFunction <em>Table Function</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLQueryArgumentListImpl extends SQLQueryObjectImpl implements XMLQueryArgumentList {
	/**
     * The default value of the '{@link #getPassingMechanism() <em>Passing Mechanism</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPassingMechanism()
     * @generated
     * @ordered
     */
    protected static final XMLPassingType PASSING_MECHANISM_EDEFAULT = XMLPassingType.BY_REF_LITERAL;

	/**
     * The cached value of the '{@link #getPassingMechanism() <em>Passing Mechanism</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPassingMechanism()
     * @generated
     * @ordered
     */
    protected XMLPassingType passingMechanism = PASSING_MECHANISM_EDEFAULT;

	/**
     * The cached value of the '{@link #getXqueryArgListChildren() <em>Xquery Arg List Children</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXqueryArgListChildren()
     * @generated
     * @ordered
     */
    protected EList xqueryArgListChildren;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLQueryArgumentListImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_QUERY_ARGUMENT_LIST;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLPassingType getPassingMechanism() {
        return passingMechanism;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPassingMechanism(XMLPassingType newPassingMechanism) {
        XMLPassingType oldPassingMechanism = passingMechanism;
        passingMechanism = newPassingMechanism == null ? PASSING_MECHANISM_EDEFAULT : newPassingMechanism;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PASSING_MECHANISM, oldPassingMechanism, passingMechanism));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLPredicateExists getPredicateExists() {
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS) return null;
        return (XMLPredicateExists)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetPredicateExists(XMLPredicateExists newPredicateExists, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newPredicateExists, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPredicateExists(XMLPredicateExists newPredicateExists) {
        if (newPredicateExists != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS && newPredicateExists != null)) {
            if (EcoreUtil.isAncestor(this, newPredicateExists))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newPredicateExists != null)
                msgs = ((InternalEObject)newPredicateExists).eInverseAdd(this, SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST, XMLPredicateExists.class, msgs);
            msgs = basicSetPredicateExists(newPredicateExists, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS, newPredicateExists, newPredicateExists));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getXqueryArgListChildren() {
        if (xqueryArgListChildren == null) {
            xqueryArgListChildren = new EObjectContainmentWithInverseEList(XMLQueryArgumentItem.class, this, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_ITEM__XQUERY_ARG_LIST);
        }
        return xqueryArgListChildren;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLValueFunctionQuery getValueFunctionQuery() {
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY) return null;
        return (XMLValueFunctionQuery)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueFunctionQuery(XMLValueFunctionQuery newValueFunctionQuery, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueFunctionQuery, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueFunctionQuery(XMLValueFunctionQuery newValueFunctionQuery) {
        if (newValueFunctionQuery != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY && newValueFunctionQuery != null)) {
            if (EcoreUtil.isAncestor(this, newValueFunctionQuery))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueFunctionQuery != null)
                msgs = ((InternalEObject)newValueFunctionQuery).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST, XMLValueFunctionQuery.class, msgs);
            msgs = basicSetValueFunctionQuery(newValueFunctionQuery, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY, newValueFunctionQuery, newValueFunctionQuery));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTableFunction getTableFunction() {
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION) return null;
        return (XMLTableFunction)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTableFunction(XMLTableFunction newTableFunction, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTableFunction, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableFunction(XMLTableFunction newTableFunction) {
        if (newTableFunction != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION && newTableFunction != null)) {
            if (EcoreUtil.isAncestor(this, newTableFunction))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTableFunction != null)
                msgs = ((InternalEObject)newTableFunction).eInverseAdd(this, SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST, XMLTableFunction.class, msgs);
            msgs = basicSetTableFunction(newTableFunction, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION, newTableFunction, newTableFunction));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetPredicateExists((XMLPredicateExists)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN:
                return ((InternalEList)getXqueryArgListChildren()).basicAdd(otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueFunctionQuery((XMLValueFunctionQuery)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetTableFunction((XMLTableFunction)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS:
                return basicSetPredicateExists(null, msgs);
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN:
                return ((InternalEList)getXqueryArgListChildren()).basicRemove(otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY:
                return basicSetValueFunctionQuery(null, msgs);
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION:
                return basicSetTableFunction(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_PREDICATE_EXISTS__XQUERY_ARG_LIST, XMLPredicateExists.class, msgs);
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST, XMLValueFunctionQuery.class, msgs);
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST, XMLTableFunction.class, msgs);
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
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PASSING_MECHANISM:
                return getPassingMechanism();
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS:
                return getPredicateExists();
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN:
                return getXqueryArgListChildren();
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY:
                return getValueFunctionQuery();
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION:
                return getTableFunction();
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
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PASSING_MECHANISM:
                setPassingMechanism((XMLPassingType)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS:
                setPredicateExists((XMLPredicateExists)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN:
                getXqueryArgListChildren().clear();
                getXqueryArgListChildren().addAll((Collection)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY:
                setValueFunctionQuery((XMLValueFunctionQuery)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION:
                setTableFunction((XMLTableFunction)newValue);
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
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PASSING_MECHANISM:
                setPassingMechanism(PASSING_MECHANISM_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS:
                setPredicateExists((XMLPredicateExists)null);
                return;
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN:
                getXqueryArgListChildren().clear();
                return;
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY:
                setValueFunctionQuery((XMLValueFunctionQuery)null);
                return;
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION:
                setTableFunction((XMLTableFunction)null);
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
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PASSING_MECHANISM:
                return passingMechanism != PASSING_MECHANISM_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS:
                return getPredicateExists() != null;
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN:
                return xqueryArgListChildren != null && !xqueryArgListChildren.isEmpty();
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY:
                return getValueFunctionQuery() != null;
            case SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION:
                return getTableFunction() != null;
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
        result.append(" (passingMechanism: ");
        result.append(passingMechanism);
        result.append(')');
        return result.toString();
    }

} //XMLQueryArgumentListImpl
