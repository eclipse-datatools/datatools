/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLAggregateSortSpecificationImpl.java,v 1.4 2008/07/07 19:55:14 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;




import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification;
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
 * An implementation of the model object '<em><b>XML Aggregate Sort Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAggregateSortSpecificationImpl#getAggregateFunction <em>Aggregate Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAggregateSortSpecificationImpl#getOrderBySpec <em>Order By Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLAggregateSortSpecificationImpl extends SQLQueryObjectImpl implements XMLAggregateSortSpecification {
	/**
     * The cached value of the '{@link #getOrderBySpec() <em>Order By Spec</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOrderBySpec()
     * @generated
     * @ordered
     */
    protected OrderBySpecification orderBySpec;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLAggregateSortSpecificationImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_AGGREGATE_SORT_SPECIFICATION;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLAggregateFunction getAggregateFunction() {
        if (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION) return null;
        return (XMLAggregateFunction)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetAggregateFunction(XMLAggregateFunction newAggregateFunction, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newAggregateFunction, SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAggregateFunction(XMLAggregateFunction newAggregateFunction) {
        if (newAggregateFunction != eInternalContainer() || (eContainerFeatureID() != SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION && newAggregateFunction != null)) {
            if (EcoreUtil.isAncestor(this, newAggregateFunction))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newAggregateFunction != null)
                msgs = ((InternalEObject)newAggregateFunction).eInverseAdd(this, SQLXMLQueryModelPackage.XML_AGGREGATE_FUNCTION__SORT_SPEC_LIST, XMLAggregateFunction.class, msgs);
            msgs = basicSetAggregateFunction(newAggregateFunction, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION, newAggregateFunction, newAggregateFunction));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OrderBySpecification getOrderBySpec() {
        return orderBySpec;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOrderBySpec(OrderBySpecification newOrderBySpec, NotificationChain msgs) {
        OrderBySpecification oldOrderBySpec = orderBySpec;
        orderBySpec = newOrderBySpec;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC, oldOrderBySpec, newOrderBySpec);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOrderBySpec(OrderBySpecification newOrderBySpec) {
        if (newOrderBySpec != orderBySpec) {
            NotificationChain msgs = null;
            if (orderBySpec != null)
                msgs = ((InternalEObject)orderBySpec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC, null, msgs);
            if (newOrderBySpec != null)
                msgs = ((InternalEObject)newOrderBySpec).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC, null, msgs);
            msgs = basicSetOrderBySpec(newOrderBySpec, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC, newOrderBySpec, newOrderBySpec));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetAggregateFunction((XMLAggregateFunction)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION:
                return basicSetAggregateFunction(null, msgs);
            case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC:
                return basicSetOrderBySpec(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION:
                return eInternalContainer().eInverseRemove(this, SQLXMLQueryModelPackage.XML_AGGREGATE_FUNCTION__SORT_SPEC_LIST, XMLAggregateFunction.class, msgs);
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
            case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION:
                return getAggregateFunction();
            case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC:
                return getOrderBySpec();
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
            case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION:
                setAggregateFunction((XMLAggregateFunction)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC:
                setOrderBySpec((OrderBySpecification)newValue);
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
            case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION:
                setAggregateFunction((XMLAggregateFunction)null);
                return;
            case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC:
                setOrderBySpec((OrderBySpecification)null);
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
            case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION:
                return getAggregateFunction() != null;
            case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC:
                return orderBySpec != null;
        }
        return super.eIsSet(featureID);
    }

} //XMLAggregateSortSpecificationImpl
