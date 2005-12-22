/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLAggregateSortSpecificationImpl.java,v 1.1 2005/12/16 13:16:51 bpayton Exp $
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
    protected OrderBySpecification orderBySpec = null;

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
		return SQLXMLQueryModelPackage.eINSTANCE.getXMLAggregateSortSpecification();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLAggregateFunction getAggregateFunction() {
		if (eContainerFeatureID != SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION) return null;
		return (XMLAggregateFunction)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setAggregateFunction(XMLAggregateFunction newAggregateFunction) {
		if (newAggregateFunction != eContainer || (eContainerFeatureID != SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION && newAggregateFunction != null)) {
			if (EcoreUtil.isAncestor(this, newAggregateFunction))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAggregateFunction != null)
				msgs = ((InternalEObject)newAggregateFunction).eInverseAdd(this, SQLXMLQueryModelPackage.XML_AGGREGATE_FUNCTION__SORT_SPEC_LIST, XMLAggregateFunction.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newAggregateFunction, SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION, msgs);
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION:
					return eBasicSetContainer(null, SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION, msgs);
				case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC:
					return basicSetOrderBySpec(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION:
					return eContainer.eInverseRemove(this, SQLXMLQueryModelPackage.XML_AGGREGATE_FUNCTION__SORT_SPEC_LIST, XMLAggregateFunction.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__NAME:
				return getName();
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__LABEL:
				return getLabel();
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION:
				return getAggregateFunction();
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC:
				return getOrderBySpec();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION:
				setAggregateFunction((XMLAggregateFunction)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC:
				setOrderBySpec((OrderBySpecification)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION:
				setAggregateFunction((XMLAggregateFunction)null);
				return;
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC:
				setOrderBySpec((OrderBySpecification)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION:
				return getAggregateFunction() != null;
			case SQLXMLQueryModelPackage.XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC:
				return orderBySpec != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //XMLAggregateSortSpecificationImpl
