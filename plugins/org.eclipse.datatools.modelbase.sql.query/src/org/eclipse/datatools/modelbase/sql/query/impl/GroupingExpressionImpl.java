/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingExpressionImpl.java,v 1.6 2005/10/22 01:35:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression;
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
 * An implementation of the model object '<em><b>SQL Grouping Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingExpressionImpl#getValueExpr <em>Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingExpressionImpl#getSuperGroupElementExpr <em>Super Group Element Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupingExpressionImpl extends GroupingImpl implements GroupingExpression {
	/**
	 * The cached value of the '{@link #getValueExpr() <em>Value Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValueExpr()
	 * @generated
	 * @ordered
	 */
    protected QueryValueExpression valueExpr = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected GroupingExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getGroupingExpression();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryValueExpression getValueExpr() {
		return valueExpr;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetValueExpr(QueryValueExpression newValueExpr, NotificationChain msgs) {
		QueryValueExpression oldValueExpr = valueExpr;
		valueExpr = newValueExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.GROUPING_EXPRESSION__VALUE_EXPR, oldValueExpr, newValueExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueExpr(QueryValueExpression newValueExpr) {
		if (newValueExpr != valueExpr) {
			NotificationChain msgs = null;
			if (valueExpr != null)
				msgs = ((InternalEObject)valueExpr).eInverseRemove(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR, QueryValueExpression.class, msgs);
			if (newValueExpr != null)
				msgs = ((InternalEObject)newValueExpr).eInverseAdd(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR, QueryValueExpression.class, msgs);
			msgs = basicSetValueExpr(newValueExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.GROUPING_EXPRESSION__VALUE_EXPR, newValueExpr, newValueExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SuperGroupElementExpression getSuperGroupElementExpr() {
		if (eContainerFeatureID != SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR) return null;
		return (SuperGroupElementExpression)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSuperGroupElementExpr(SuperGroupElementExpression newSuperGroupElementExpr) {
		if (newSuperGroupElementExpr != eContainer || (eContainerFeatureID != SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR && newSuperGroupElementExpr != null)) {
			if (EcoreUtil.isAncestor(this, newSuperGroupElementExpr))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSuperGroupElementExpr != null)
				msgs = ((InternalEObject)newSuperGroupElementExpr).eInverseAdd(this, SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR, SuperGroupElementExpression.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newSuperGroupElementExpr, SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR, newSuperGroupElementExpr, newSuperGroupElementExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.GROUPING_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.GROUPING_EXPRESSION__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.GROUPING_EXPRESSION__QUERY_SELECT, msgs);
				case SQLQueryPackage.GROUPING_EXPRESSION__GROUPING_SETS_ELEMENT_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.GROUPING_EXPRESSION__GROUPING_SETS_ELEMENT_EXPR, msgs);
				case SQLQueryPackage.GROUPING_EXPRESSION__VALUE_EXPR:
					if (valueExpr != null)
						msgs = ((InternalEObject)valueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.GROUPING_EXPRESSION__VALUE_EXPR, null, msgs);
					return basicSetValueExpr((QueryValueExpression)otherEnd, msgs);
				case SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR, msgs);
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
				case SQLQueryPackage.GROUPING_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.GROUPING_EXPRESSION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.GROUPING_EXPRESSION__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryPackage.GROUPING_EXPRESSION__QUERY_SELECT, msgs);
				case SQLQueryPackage.GROUPING_EXPRESSION__GROUPING_SETS_ELEMENT_EXPR:
					return eBasicSetContainer(null, SQLQueryPackage.GROUPING_EXPRESSION__GROUPING_SETS_ELEMENT_EXPR, msgs);
				case SQLQueryPackage.GROUPING_EXPRESSION__VALUE_EXPR:
					return basicSetValueExpr(null, msgs);
				case SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR:
					return eBasicSetContainer(null, SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR, msgs);
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
				case SQLQueryPackage.GROUPING_EXPRESSION__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__GROUP_BY_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.GROUPING_EXPRESSION__GROUPING_SETS_ELEMENT_EXPR:
					return eContainer.eInverseRemove(this, SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, GroupingSetsElementExpression.class, msgs);
				case SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR:
					return eContainer.eInverseRemove(this, SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR, SuperGroupElementExpression.class, msgs);
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
			case SQLQueryPackage.GROUPING_EXPRESSION__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.GROUPING_EXPRESSION__NAME:
				return getName();
			case SQLQueryPackage.GROUPING_EXPRESSION__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.GROUPING_EXPRESSION__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.GROUPING_EXPRESSION__LABEL:
				return getLabel();
			case SQLQueryPackage.GROUPING_EXPRESSION__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryPackage.GROUPING_EXPRESSION__GROUPING_SETS_ELEMENT_EXPR:
				return getGroupingSetsElementExpr();
			case SQLQueryPackage.GROUPING_EXPRESSION__VALUE_EXPR:
				return getValueExpr();
			case SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR:
				return getSuperGroupElementExpr();
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
			case SQLQueryPackage.GROUPING_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__GROUPING_SETS_ELEMENT_EXPR:
				setGroupingSetsElementExpr((GroupingSetsElementExpression)newValue);
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__VALUE_EXPR:
				setValueExpr((QueryValueExpression)newValue);
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR:
				setSuperGroupElementExpr((SuperGroupElementExpression)newValue);
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
			case SQLQueryPackage.GROUPING_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__GROUPING_SETS_ELEMENT_EXPR:
				setGroupingSetsElementExpr((GroupingSetsElementExpression)null);
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__VALUE_EXPR:
				setValueExpr((QueryValueExpression)null);
				return;
			case SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR:
				setSuperGroupElementExpr((SuperGroupElementExpression)null);
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
			case SQLQueryPackage.GROUPING_EXPRESSION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.GROUPING_EXPRESSION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.GROUPING_EXPRESSION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.GROUPING_EXPRESSION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.GROUPING_EXPRESSION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.GROUPING_EXPRESSION__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryPackage.GROUPING_EXPRESSION__GROUPING_SETS_ELEMENT_EXPR:
				return getGroupingSetsElementExpr() != null;
			case SQLQueryPackage.GROUPING_EXPRESSION__VALUE_EXPR:
				return valueExpr != null;
			case SQLQueryPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR:
				return getSuperGroupElementExpr() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLGroupingExpressionImpl
