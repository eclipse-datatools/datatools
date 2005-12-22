/**
 * </copyright>
 *
 * $Id: OrderBySpecificationImpl.java,v 1.2 2005/12/17 01:46:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.NullOrderingType;
import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.OrderingSpecType;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
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
 * An implementation of the model object '<em><b>SQL Order By Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderBySpecificationImpl#isDescending <em>Descending</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderBySpecificationImpl#getOrderingSpecOption <em>Ordering Spec Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderBySpecificationImpl#getNullOrderingOption <em>Null Ordering Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderBySpecificationImpl#getSelectStatement <em>Select Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class OrderBySpecificationImpl extends SQLQueryObjectImpl implements OrderBySpecification {
	/**
	 * The default value of the '{@link #isDescending() <em>Descending</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isDescending()
	 * @generated
	 * @ordered
	 */
    protected static final boolean DESCENDING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDescending() <em>Descending</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isDescending()
	 * @generated
	 * @ordered
	 */
    protected boolean descending = DESCENDING_EDEFAULT;

	/**
	 * The default value of the '{@link #getOrderingSpecOption() <em>Ordering Spec Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getOrderingSpecOption()
	 * @generated
	 * @ordered
	 */
    protected static final OrderingSpecType ORDERING_SPEC_OPTION_EDEFAULT = OrderingSpecType.NONE_LITERAL;

	/**
	 * The cached value of the '{@link #getOrderingSpecOption() <em>Ordering Spec Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getOrderingSpecOption()
	 * @generated
	 * @ordered
	 */
    protected OrderingSpecType orderingSpecOption = ORDERING_SPEC_OPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getNullOrderingOption() <em>Null Ordering Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNullOrderingOption()
	 * @generated
	 * @ordered
	 */
    protected static final NullOrderingType NULL_ORDERING_OPTION_EDEFAULT = NullOrderingType.NONE_LITERAL;

	/**
	 * The cached value of the '{@link #getNullOrderingOption() <em>Null Ordering Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNullOrderingOption()
	 * @generated
	 * @ordered
	 */
    protected NullOrderingType nullOrderingOption = NULL_ORDERING_OPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected OrderBySpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryModelPackage.eINSTANCE.getOrderBySpecification();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isDescending() {
		return descending;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDescending(boolean newDescending) {
		boolean oldDescending = descending;
		descending = newDescending;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DESCENDING, oldDescending, descending));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public OrderingSpecType getOrderingSpecOption() {
		return orderingSpecOption;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setOrderingSpecOption(OrderingSpecType newOrderingSpecOption) {
		OrderingSpecType oldOrderingSpecOption = orderingSpecOption;
		orderingSpecOption = newOrderingSpecOption == null ? ORDERING_SPEC_OPTION_EDEFAULT : newOrderingSpecOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION, oldOrderingSpecOption, orderingSpecOption));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NullOrderingType getNullOrderingOption() {
		return nullOrderingOption;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNullOrderingOption(NullOrderingType newNullOrderingOption) {
		NullOrderingType oldNullOrderingOption = nullOrderingOption;
		nullOrderingOption = newNullOrderingOption == null ? NULL_ORDERING_OPTION_EDEFAULT : newNullOrderingOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION, oldNullOrderingOption, nullOrderingOption));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QuerySelectStatement getSelectStatement() {
		if (eContainerFeatureID != SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT) return null;
		return (QuerySelectStatement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSelectStatement(QuerySelectStatement newSelectStatement) {
		if (newSelectStatement != eContainer || (eContainerFeatureID != SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT && newSelectStatement != null)) {
			if (EcoreUtil.isAncestor(this, newSelectStatement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSelectStatement != null)
				msgs = ((InternalEObject)newSelectStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE, QuerySelectStatement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newSelectStatement, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT, newSelectStatement, newSelectStatement));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT, msgs);
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
				case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT:
					return eBasicSetContainer(null, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT, msgs);
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
				case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE, QuerySelectStatement.class, msgs);
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
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__NAME:
				return getName();
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__LABEL:
				return getLabel();
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DESCENDING:
				return isDescending() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION:
				return getOrderingSpecOption();
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION:
				return getNullOrderingOption();
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT:
				return getSelectStatement();
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
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DESCENDING:
				setDescending(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION:
				setOrderingSpecOption((OrderingSpecType)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION:
				setNullOrderingOption((NullOrderingType)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT:
				setSelectStatement((QuerySelectStatement)newValue);
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
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DESCENDING:
				setDescending(DESCENDING_EDEFAULT);
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION:
				setOrderingSpecOption(ORDERING_SPEC_OPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION:
				setNullOrderingOption(NULL_ORDERING_OPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT:
				setSelectStatement((QuerySelectStatement)null);
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
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DESCENDING:
				return descending != DESCENDING_EDEFAULT;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION:
				return orderingSpecOption != ORDERING_SPEC_OPTION_EDEFAULT;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION:
				return nullOrderingOption != NULL_ORDERING_OPTION_EDEFAULT;
			case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT:
				return getSelectStatement() != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (descending: ");
		result.append(descending);
		result.append(", OrderingSpecOption: ");
		result.append(orderingSpecOption);
		result.append(", NullOrderingOption: ");
		result.append(nullOrderingOption);
		result.append(')');
		return result.toString();
	}

} //SQLOrderBySpecificationImpl
