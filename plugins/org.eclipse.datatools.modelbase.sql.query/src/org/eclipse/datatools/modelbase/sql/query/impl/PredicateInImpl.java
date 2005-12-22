/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateInImpl.java,v 1.3 2005/12/19 20:56:37 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateIn;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
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
 * An implementation of the model object '<em><b>SQL Predicate In</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateInImpl#isNotIn <em>Not In</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PredicateInImpl extends PredicateImpl implements PredicateIn {
	/**
	 * The default value of the '{@link #isNotIn() <em>Not In</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isNotIn()
	 * @generated
	 * @ordered
	 */
    protected static final boolean NOT_IN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNotIn() <em>Not In</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isNotIn()
	 * @generated
	 * @ordered
	 */
    protected boolean notIn = NOT_IN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected PredicateInImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryModelPackage.eINSTANCE.getPredicateIn();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isNotIn() {
		return notIn;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNotIn(boolean newNotIn) {
		boolean oldNotIn = notIn;
		notIn = newNotIn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_IN__NOT_IN, oldNotIn, notIn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.PREDICATE_IN__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__UPDATE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_IN__UPDATE_STATEMENT, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__DELETE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_IN__DELETE_STATEMENT, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__TABLE_JOINED:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_IN__TABLE_JOINED, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_IN__COMBINED_LEFT, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_IN__COMBINED_RIGHT, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_HAVING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_HAVING, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_WHERE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_WHERE, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_IN__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_IN__NEST, msgs);
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
				case SQLQueryModelPackage.PREDICATE_IN__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__UPDATE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_IN__UPDATE_STATEMENT, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__DELETE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_IN__DELETE_STATEMENT, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__TABLE_JOINED:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_IN__TABLE_JOINED, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_IN__COMBINED_LEFT, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_IN__COMBINED_RIGHT, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_HAVING:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_HAVING, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_WHERE:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_WHERE, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_IN__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__NEST:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_IN__NEST, msgs);
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
				case SQLQueryModelPackage.PREDICATE_IN__UPDATE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__WHERE_CLAUSE, QueryUpdateStatement.class, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__DELETE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, QueryDeleteStatement.class, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__TABLE_JOINED:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.TABLE_JOINED__JOIN_CONDITION, TableJoined.class, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__COMBINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION, SearchConditionCombined.class, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__COMBINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION, SearchConditionCombined.class, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_HAVING:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__HAVING_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_WHERE:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__WHERE_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION, ValueExpressionCaseSearchContent.class, msgs);
				case SQLQueryModelPackage.PREDICATE_IN__NEST:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, SearchConditionNested.class, msgs);
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
			case SQLQueryModelPackage.PREDICATE_IN__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.PREDICATE_IN__NAME:
				return getName();
			case SQLQueryModelPackage.PREDICATE_IN__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.PREDICATE_IN__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.PREDICATE_IN__LABEL:
				return getLabel();
			case SQLQueryModelPackage.PREDICATE_IN__NEGATED_CONDITION:
				return isNegatedCondition() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryModelPackage.PREDICATE_IN__UPDATE_STATEMENT:
				return getUpdateStatement();
			case SQLQueryModelPackage.PREDICATE_IN__DELETE_STATEMENT:
				return getDeleteStatement();
			case SQLQueryModelPackage.PREDICATE_IN__TABLE_JOINED:
				return getTableJoined();
			case SQLQueryModelPackage.PREDICATE_IN__COMBINED_LEFT:
				return getCombinedLeft();
			case SQLQueryModelPackage.PREDICATE_IN__COMBINED_RIGHT:
				return getCombinedRight();
			case SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_HAVING:
				return getQuerySelectHaving();
			case SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_WHERE:
				return getQuerySelectWhere();
			case SQLQueryModelPackage.PREDICATE_IN__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLQueryModelPackage.PREDICATE_IN__NEST:
				return getNest();
			case SQLQueryModelPackage.PREDICATE_IN__NEGATED_PREDICATE:
				return isNegatedPredicate() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryModelPackage.PREDICATE_IN__HAS_SELECTIVITY:
				return isHasSelectivity() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryModelPackage.PREDICATE_IN__SELECTIVITY_VALUE:
				return getSelectivityValue();
			case SQLQueryModelPackage.PREDICATE_IN__NOT_IN:
				return isNotIn() ? Boolean.TRUE : Boolean.FALSE;
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
			case SQLQueryModelPackage.PREDICATE_IN__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__NEGATED_CONDITION:
				setNegatedCondition(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryModelPackage.PREDICATE_IN__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__TABLE_JOINED:
				setTableJoined((TableJoined)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__NEST:
				setNest((SearchConditionNested)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__NEGATED_PREDICATE:
				setNegatedPredicate(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryModelPackage.PREDICATE_IN__HAS_SELECTIVITY:
				setHasSelectivity(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryModelPackage.PREDICATE_IN__SELECTIVITY_VALUE:
				setSelectivityValue((Integer)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__NOT_IN:
				setNotIn(((Boolean)newValue).booleanValue());
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
			case SQLQueryModelPackage.PREDICATE_IN__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.PREDICATE_IN__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.PREDICATE_IN__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__NEGATED_CONDITION:
				setNegatedCondition(NEGATED_CONDITION_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)null);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)null);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__TABLE_JOINED:
				setTableJoined((TableJoined)null);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)null);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)null);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)null);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)null);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__NEST:
				setNest((SearchConditionNested)null);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__NEGATED_PREDICATE:
				setNegatedPredicate(NEGATED_PREDICATE_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__HAS_SELECTIVITY:
				setHasSelectivity(HAS_SELECTIVITY_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__SELECTIVITY_VALUE:
				setSelectivityValue(SELECTIVITY_VALUE_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_IN__NOT_IN:
				setNotIn(NOT_IN_EDEFAULT);
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
			case SQLQueryModelPackage.PREDICATE_IN__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.PREDICATE_IN__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.PREDICATE_IN__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.PREDICATE_IN__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.PREDICATE_IN__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.PREDICATE_IN__NEGATED_CONDITION:
				return negatedCondition != NEGATED_CONDITION_EDEFAULT;
			case SQLQueryModelPackage.PREDICATE_IN__UPDATE_STATEMENT:
				return getUpdateStatement() != null;
			case SQLQueryModelPackage.PREDICATE_IN__DELETE_STATEMENT:
				return getDeleteStatement() != null;
			case SQLQueryModelPackage.PREDICATE_IN__TABLE_JOINED:
				return getTableJoined() != null;
			case SQLQueryModelPackage.PREDICATE_IN__COMBINED_LEFT:
				return getCombinedLeft() != null;
			case SQLQueryModelPackage.PREDICATE_IN__COMBINED_RIGHT:
				return getCombinedRight() != null;
			case SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_HAVING:
				return getQuerySelectHaving() != null;
			case SQLQueryModelPackage.PREDICATE_IN__QUERY_SELECT_WHERE:
				return getQuerySelectWhere() != null;
			case SQLQueryModelPackage.PREDICATE_IN__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLQueryModelPackage.PREDICATE_IN__NEST:
				return getNest() != null;
			case SQLQueryModelPackage.PREDICATE_IN__NEGATED_PREDICATE:
				return negatedPredicate != NEGATED_PREDICATE_EDEFAULT;
			case SQLQueryModelPackage.PREDICATE_IN__HAS_SELECTIVITY:
				return hasSelectivity != HAS_SELECTIVITY_EDEFAULT;
			case SQLQueryModelPackage.PREDICATE_IN__SELECTIVITY_VALUE:
				return SELECTIVITY_VALUE_EDEFAULT == null ? selectivityValue != null : !SELECTIVITY_VALUE_EDEFAULT.equals(selectivityValue);
			case SQLQueryModelPackage.PREDICATE_IN__NOT_IN:
				return notIn != NOT_IN_EDEFAULT;
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
		result.append(" (notIn: ");
		result.append(notIn);
		result.append(')');
		return result.toString();
	}

} //SQLPredicateInImpl
