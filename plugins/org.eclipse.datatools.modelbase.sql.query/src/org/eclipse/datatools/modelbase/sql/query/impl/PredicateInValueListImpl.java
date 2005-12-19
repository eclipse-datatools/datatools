/**
 * </copyright>
 *
 * $Id: PredicateInValueListImpl.java,v 1.2 2005/12/17 01:46:20 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Predicate In Value List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueListImpl#getValueExprList <em>Value Expr List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueListImpl#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PredicateInValueListImpl extends PredicateInImpl implements PredicateInValueList {
	/**
	 * The cached value of the '{@link #getValueExprList() <em>Value Expr List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValueExprList()
	 * @generated
	 * @ordered
	 */
    protected EList valueExprList = null;

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
    protected PredicateInValueListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getPredicateInValueList();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getValueExprList() {
		if (valueExprList == null) {
			valueExprList = new EObjectContainmentWithInverseEList(QueryValueExpression.class, this, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT);
		}
		return valueExprList;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR, oldValueExpr, newValueExpr);
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
				msgs = ((InternalEObject)valueExpr).eInverseRemove(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT, QueryValueExpression.class, msgs);
			if (newValueExpr != null)
				msgs = ((InternalEObject)newValueExpr).eInverseAdd(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT, QueryValueExpression.class, msgs);
			msgs = basicSetValueExpr(newValueExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR, newValueExpr, newValueExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__UPDATE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DELETE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__TABLE_JOINED:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__TABLE_JOINED, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_LEFT, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_HAVING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_HAVING, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_WHERE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_WHERE, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEST, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST:
					return ((InternalEList)getValueExprList()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR:
					if (valueExpr != null)
						msgs = ((InternalEObject)valueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR, null, msgs);
					return basicSetValueExpr((QueryValueExpression)otherEnd, msgs);
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
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__UPDATE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DELETE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__TABLE_JOINED:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__TABLE_JOINED, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_LEFT, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_HAVING:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_HAVING, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_WHERE:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_WHERE, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEST, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST:
					return ((InternalEList)getValueExprList()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR:
					return basicSetValueExpr(null, msgs);
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
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__UPDATE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_UPDATE_STATEMENT__WHERE_CLAUSE, QueryUpdateStatement.class, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DELETE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, QueryDeleteStatement.class, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__TABLE_JOINED:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_JOINED__JOIN_CONDITION, TableJoined.class, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION, SearchConditionCombined.class, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION, SearchConditionCombined.class, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_HAVING:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_WHERE:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION, ValueExpressionCaseSearchContent.class, msgs);
				case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEST:
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
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NAME:
				return getName();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__LABEL:
				return getLabel();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEGATED_CONDITION:
				return isNegatedCondition() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__UPDATE_STATEMENT:
				return getUpdateStatement();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DELETE_STATEMENT:
				return getDeleteStatement();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__TABLE_JOINED:
				return getTableJoined();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_LEFT:
				return getCombinedLeft();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_RIGHT:
				return getCombinedRight();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_HAVING:
				return getQuerySelectHaving();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_WHERE:
				return getQuerySelectWhere();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEST:
				return getNest();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEGATED_PREDICATE:
				return isNegatedPredicate() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__HAS_SELECTIVITY:
				return isHasSelectivity() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__SELECTIVITY_VALUE:
				return getSelectivityValue();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NOT_IN:
				return isNotIn() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST:
				return getValueExprList();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR:
				return getValueExpr();
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
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEGATED_CONDITION:
				setNegatedCondition(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__TABLE_JOINED:
				setTableJoined((TableJoined)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEST:
				setNest((SearchConditionNested)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEGATED_PREDICATE:
				setNegatedPredicate(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__HAS_SELECTIVITY:
				setHasSelectivity(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__SELECTIVITY_VALUE:
				setSelectivityValue((Integer)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NOT_IN:
				setNotIn(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST:
				getValueExprList().clear();
				getValueExprList().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR:
				setValueExpr((QueryValueExpression)newValue);
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
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEGATED_CONDITION:
				setNegatedCondition(NEGATED_CONDITION_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)null);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)null);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__TABLE_JOINED:
				setTableJoined((TableJoined)null);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)null);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)null);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)null);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)null);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEST:
				setNest((SearchConditionNested)null);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEGATED_PREDICATE:
				setNegatedPredicate(NEGATED_PREDICATE_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__HAS_SELECTIVITY:
				setHasSelectivity(HAS_SELECTIVITY_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__SELECTIVITY_VALUE:
				setSelectivityValue(SELECTIVITY_VALUE_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NOT_IN:
				setNotIn(NOT_IN_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST:
				getValueExprList().clear();
				return;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR:
				setValueExpr((QueryValueExpression)null);
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
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEGATED_CONDITION:
				return negatedCondition != NEGATED_CONDITION_EDEFAULT;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__UPDATE_STATEMENT:
				return getUpdateStatement() != null;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__DELETE_STATEMENT:
				return getDeleteStatement() != null;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__TABLE_JOINED:
				return getTableJoined() != null;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_LEFT:
				return getCombinedLeft() != null;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__COMBINED_RIGHT:
				return getCombinedRight() != null;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_HAVING:
				return getQuerySelectHaving() != null;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__QUERY_SELECT_WHERE:
				return getQuerySelectWhere() != null;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEST:
				return getNest() != null;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NEGATED_PREDICATE:
				return negatedPredicate != NEGATED_PREDICATE_EDEFAULT;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__HAS_SELECTIVITY:
				return hasSelectivity != HAS_SELECTIVITY_EDEFAULT;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__SELECTIVITY_VALUE:
				return SELECTIVITY_VALUE_EDEFAULT == null ? selectivityValue != null : !SELECTIVITY_VALUE_EDEFAULT.equals(selectivityValue);
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__NOT_IN:
				return notIn != NOT_IN_EDEFAULT;
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST:
				return valueExprList != null && !valueExprList.isEmpty();
			case SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR:
				return valueExpr != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLPredicateInValueListImpl
