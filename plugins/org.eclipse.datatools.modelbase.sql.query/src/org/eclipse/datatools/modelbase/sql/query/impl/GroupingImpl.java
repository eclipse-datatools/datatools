/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingImpl.java,v 1.2 2005/12/17 01:46:20 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.Grouping;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
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
 * An implementation of the model object '<em><b>SQL Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingImpl#getGroupingSetsElementExpr <em>Grouping Sets Element Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GroupingImpl extends GroupingSpecificationImpl implements Grouping {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected GroupingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryModelPackage.eINSTANCE.getGrouping();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GroupingSetsElementExpression getGroupingSetsElementExpr() {
		if (eContainerFeatureID != SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR) return null;
		return (GroupingSetsElementExpression)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGroupingSetsElementExpr(GroupingSetsElementExpression newGroupingSetsElementExpr) {
		if (newGroupingSetsElementExpr != eContainer || (eContainerFeatureID != SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR && newGroupingSetsElementExpr != null)) {
			if (EcoreUtil.isAncestor(this, newGroupingSetsElementExpr))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGroupingSetsElementExpr != null)
				msgs = ((InternalEObject)newGroupingSetsElementExpr).eInverseAdd(this, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, GroupingSetsElementExpression.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newGroupingSetsElementExpr, SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR, newGroupingSetsElementExpr, newGroupingSetsElementExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.GROUPING__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.GROUPING__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.GROUPING__QUERY_SELECT, msgs);
				case SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR, msgs);
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
				case SQLQueryModelPackage.GROUPING__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.GROUPING__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.GROUPING__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryModelPackage.GROUPING__QUERY_SELECT, msgs);
				case SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR:
					return eBasicSetContainer(null, SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR, msgs);
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
				case SQLQueryModelPackage.GROUPING__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__GROUP_BY_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, GroupingSetsElementExpression.class, msgs);
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
			case SQLQueryModelPackage.GROUPING__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.GROUPING__NAME:
				return getName();
			case SQLQueryModelPackage.GROUPING__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.GROUPING__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.GROUPING__LABEL:
				return getLabel();
			case SQLQueryModelPackage.GROUPING__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR:
				return getGroupingSetsElementExpr();
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
			case SQLQueryModelPackage.GROUPING__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.GROUPING__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.GROUPING__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.GROUPING__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.GROUPING__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.GROUPING__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR:
				setGroupingSetsElementExpr((GroupingSetsElementExpression)newValue);
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
			case SQLQueryModelPackage.GROUPING__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.GROUPING__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.GROUPING__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.GROUPING__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.GROUPING__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.GROUPING__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR:
				setGroupingSetsElementExpr((GroupingSetsElementExpression)null);
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
			case SQLQueryModelPackage.GROUPING__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.GROUPING__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.GROUPING__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.GROUPING__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.GROUPING__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.GROUPING__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR:
				return getGroupingSetsElementExpr() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLGroupImpl
