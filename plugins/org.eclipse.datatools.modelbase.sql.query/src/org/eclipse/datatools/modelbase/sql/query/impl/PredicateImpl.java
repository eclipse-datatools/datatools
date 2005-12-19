/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateImpl.java,v 1.2 2005/12/17 01:46:20 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Predicate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateImpl#isNegatedPredicate <em>Negated Predicate</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateImpl#isHasSelectivity <em>Has Selectivity</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateImpl#getSelectivityValue <em>Selectivity Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PredicateImpl extends QuerySearchConditionImpl implements Predicate {
	/**
	 * The default value of the '{@link #isNegatedPredicate() <em>Negated Predicate</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isNegatedPredicate()
	 * @generated
	 * @ordered
	 */
    protected static final boolean NEGATED_PREDICATE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNegatedPredicate() <em>Negated Predicate</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isNegatedPredicate()
	 * @generated
	 * @ordered
	 */
    protected boolean negatedPredicate = NEGATED_PREDICATE_EDEFAULT;

	/**
	 * The default value of the '{@link #isHasSelectivity() <em>Has Selectivity</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isHasSelectivity()
	 * @generated
	 * @ordered
	 */
    protected static final boolean HAS_SELECTIVITY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasSelectivity() <em>Has Selectivity</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isHasSelectivity()
	 * @generated
	 * @ordered
	 */
    protected boolean hasSelectivity = HAS_SELECTIVITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getSelectivityValue() <em>Selectivity Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSelectivityValue()
	 * @generated
	 * @ordered
	 */
    protected static final Integer SELECTIVITY_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSelectivityValue() <em>Selectivity Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSelectivityValue()
	 * @generated
	 * @ordered
	 */
    protected Integer selectivityValue = SELECTIVITY_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected PredicateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getPredicate();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isNegatedPredicate() {
		return negatedPredicate;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNegatedPredicate(boolean newNegatedPredicate) {
		boolean oldNegatedPredicate = negatedPredicate;
		negatedPredicate = newNegatedPredicate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE__NEGATED_PREDICATE, oldNegatedPredicate, negatedPredicate));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isHasSelectivity() {
		return hasSelectivity;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setHasSelectivity(boolean newHasSelectivity) {
		boolean oldHasSelectivity = hasSelectivity;
		hasSelectivity = newHasSelectivity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE__HAS_SELECTIVITY, oldHasSelectivity, hasSelectivity));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Integer getSelectivityValue() {
		return selectivityValue;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSelectivityValue(Integer newSelectivityValue) {
		Integer oldSelectivityValue = selectivityValue;
		selectivityValue = newSelectivityValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE__SELECTIVITY_VALUE, oldSelectivityValue, selectivityValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.PREDICATE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE__UPDATE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE__DELETE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE__TABLE_JOINED:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE__TABLE_JOINED, msgs);
				case SQLQueryPackage.PREDICATE__COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE__COMBINED_LEFT, msgs);
				case SQLQueryPackage.PREDICATE__COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.PREDICATE__QUERY_SELECT_HAVING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE__QUERY_SELECT_HAVING, msgs);
				case SQLQueryPackage.PREDICATE__QUERY_SELECT_WHERE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE__QUERY_SELECT_WHERE, msgs);
				case SQLQueryPackage.PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.PREDICATE__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE__NEST, msgs);
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
				case SQLQueryPackage.PREDICATE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE__UPDATE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE__DELETE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE__TABLE_JOINED:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE__TABLE_JOINED, msgs);
				case SQLQueryPackage.PREDICATE__COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE__COMBINED_LEFT, msgs);
				case SQLQueryPackage.PREDICATE__COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.PREDICATE__QUERY_SELECT_HAVING:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE__QUERY_SELECT_HAVING, msgs);
				case SQLQueryPackage.PREDICATE__QUERY_SELECT_WHERE:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE__QUERY_SELECT_WHERE, msgs);
				case SQLQueryPackage.PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.PREDICATE__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE__NEST, msgs);
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
				case SQLQueryPackage.PREDICATE__UPDATE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_UPDATE_STATEMENT__WHERE_CLAUSE, QueryUpdateStatement.class, msgs);
				case SQLQueryPackage.PREDICATE__DELETE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, QueryDeleteStatement.class, msgs);
				case SQLQueryPackage.PREDICATE__TABLE_JOINED:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_JOINED__JOIN_CONDITION, TableJoined.class, msgs);
				case SQLQueryPackage.PREDICATE__COMBINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION, SearchConditionCombined.class, msgs);
				case SQLQueryPackage.PREDICATE__COMBINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION, SearchConditionCombined.class, msgs);
				case SQLQueryPackage.PREDICATE__QUERY_SELECT_HAVING:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.PREDICATE__QUERY_SELECT_WHERE:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION, ValueExpressionCaseSearchContent.class, msgs);
				case SQLQueryPackage.PREDICATE__NEST:
					return eContainer.eInverseRemove(this, SQLQueryPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, SearchConditionNested.class, msgs);
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
			case SQLQueryPackage.PREDICATE__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.PREDICATE__NAME:
				return getName();
			case SQLQueryPackage.PREDICATE__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.PREDICATE__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.PREDICATE__LABEL:
				return getLabel();
			case SQLQueryPackage.PREDICATE__NEGATED_CONDITION:
				return isNegatedCondition() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE__UPDATE_STATEMENT:
				return getUpdateStatement();
			case SQLQueryPackage.PREDICATE__DELETE_STATEMENT:
				return getDeleteStatement();
			case SQLQueryPackage.PREDICATE__TABLE_JOINED:
				return getTableJoined();
			case SQLQueryPackage.PREDICATE__COMBINED_LEFT:
				return getCombinedLeft();
			case SQLQueryPackage.PREDICATE__COMBINED_RIGHT:
				return getCombinedRight();
			case SQLQueryPackage.PREDICATE__QUERY_SELECT_HAVING:
				return getQuerySelectHaving();
			case SQLQueryPackage.PREDICATE__QUERY_SELECT_WHERE:
				return getQuerySelectWhere();
			case SQLQueryPackage.PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLQueryPackage.PREDICATE__NEST:
				return getNest();
			case SQLQueryPackage.PREDICATE__NEGATED_PREDICATE:
				return isNegatedPredicate() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE__HAS_SELECTIVITY:
				return isHasSelectivity() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE__SELECTIVITY_VALUE:
				return getSelectivityValue();
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
			case SQLQueryPackage.PREDICATE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.PREDICATE__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.PREDICATE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE__NEGATED_CONDITION:
				setNegatedCondition(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)newValue);
				return;
			case SQLQueryPackage.PREDICATE__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)newValue);
				return;
			case SQLQueryPackage.PREDICATE__TABLE_JOINED:
				setTableJoined((TableJoined)newValue);
				return;
			case SQLQueryPackage.PREDICATE__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)newValue);
				return;
			case SQLQueryPackage.PREDICATE__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)newValue);
				return;
			case SQLQueryPackage.PREDICATE__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)newValue);
				return;
			case SQLQueryPackage.PREDICATE__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)newValue);
				return;
			case SQLQueryPackage.PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLQueryPackage.PREDICATE__NEST:
				setNest((SearchConditionNested)newValue);
				return;
			case SQLQueryPackage.PREDICATE__NEGATED_PREDICATE:
				setNegatedPredicate(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE__HAS_SELECTIVITY:
				setHasSelectivity(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE__SELECTIVITY_VALUE:
				setSelectivityValue((Integer)newValue);
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
			case SQLQueryPackage.PREDICATE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.PREDICATE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.PREDICATE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE__NEGATED_CONDITION:
				setNegatedCondition(NEGATED_CONDITION_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)null);
				return;
			case SQLQueryPackage.PREDICATE__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)null);
				return;
			case SQLQueryPackage.PREDICATE__TABLE_JOINED:
				setTableJoined((TableJoined)null);
				return;
			case SQLQueryPackage.PREDICATE__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)null);
				return;
			case SQLQueryPackage.PREDICATE__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)null);
				return;
			case SQLQueryPackage.PREDICATE__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)null);
				return;
			case SQLQueryPackage.PREDICATE__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)null);
				return;
			case SQLQueryPackage.PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLQueryPackage.PREDICATE__NEST:
				setNest((SearchConditionNested)null);
				return;
			case SQLQueryPackage.PREDICATE__NEGATED_PREDICATE:
				setNegatedPredicate(NEGATED_PREDICATE_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE__HAS_SELECTIVITY:
				setHasSelectivity(HAS_SELECTIVITY_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE__SELECTIVITY_VALUE:
				setSelectivityValue(SELECTIVITY_VALUE_EDEFAULT);
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
			case SQLQueryPackage.PREDICATE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.PREDICATE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.PREDICATE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.PREDICATE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.PREDICATE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.PREDICATE__NEGATED_CONDITION:
				return negatedCondition != NEGATED_CONDITION_EDEFAULT;
			case SQLQueryPackage.PREDICATE__UPDATE_STATEMENT:
				return getUpdateStatement() != null;
			case SQLQueryPackage.PREDICATE__DELETE_STATEMENT:
				return getDeleteStatement() != null;
			case SQLQueryPackage.PREDICATE__TABLE_JOINED:
				return getTableJoined() != null;
			case SQLQueryPackage.PREDICATE__COMBINED_LEFT:
				return getCombinedLeft() != null;
			case SQLQueryPackage.PREDICATE__COMBINED_RIGHT:
				return getCombinedRight() != null;
			case SQLQueryPackage.PREDICATE__QUERY_SELECT_HAVING:
				return getQuerySelectHaving() != null;
			case SQLQueryPackage.PREDICATE__QUERY_SELECT_WHERE:
				return getQuerySelectWhere() != null;
			case SQLQueryPackage.PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLQueryPackage.PREDICATE__NEST:
				return getNest() != null;
			case SQLQueryPackage.PREDICATE__NEGATED_PREDICATE:
				return negatedPredicate != NEGATED_PREDICATE_EDEFAULT;
			case SQLQueryPackage.PREDICATE__HAS_SELECTIVITY:
				return hasSelectivity != HAS_SELECTIVITY_EDEFAULT;
			case SQLQueryPackage.PREDICATE__SELECTIVITY_VALUE:
				return SELECTIVITY_VALUE_EDEFAULT == null ? selectivityValue != null : !SELECTIVITY_VALUE_EDEFAULT.equals(selectivityValue);
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
		result.append(" (negatedPredicate: ");
		result.append(negatedPredicate);
		result.append(", hasSelectivity: ");
		result.append(hasSelectivity);
		result.append(", selectivityValue: ");
		result.append(selectivityValue);
		result.append(')');
		return result.toString();
	}

} //SQLPredicateImpl
