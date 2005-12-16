/**
 * <copyright>
 * </copyright>
 *
 * $Id: SearchConditionNestedImpl.java,v 1.6 2005/10/22 01:35:22 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
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
 * An implementation of the model object '<em><b>SQL Search Condition Nested</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SearchConditionNestedImpl#getNestedCondition <em>Nested Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SearchConditionNestedImpl extends QuerySearchConditionImpl implements SearchConditionNested {
	/**
	 * The cached value of the '{@link #getNestedCondition() <em>Nested Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getNestedCondition()
	 * @generated
	 * @ordered
	 */
  protected QuerySearchCondition nestedCondition = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected SearchConditionNestedImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getSearchConditionNested();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public QuerySearchCondition getNestedCondition() {
		return nestedCondition;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetNestedCondition(QuerySearchCondition newNestedCondition, NotificationChain msgs) {
		QuerySearchCondition oldNestedCondition = nestedCondition;
		nestedCondition = newNestedCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, oldNestedCondition, newNestedCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setNestedCondition(QuerySearchCondition newNestedCondition) {
		if (newNestedCondition != nestedCondition) {
			NotificationChain msgs = null;
			if (nestedCondition != null)
				msgs = ((InternalEObject)nestedCondition).eInverseRemove(this, SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST, QuerySearchCondition.class, msgs);
			if (newNestedCondition != null)
				msgs = ((InternalEObject)newNestedCondition).eInverseAdd(this, SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST, QuerySearchCondition.class, msgs);
			msgs = basicSetNestedCondition(newNestedCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, newNestedCondition, newNestedCondition));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SEARCH_CONDITION_NESTED__NEST, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
					if (nestedCondition != null)
						msgs = ((InternalEObject)nestedCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, null, msgs);
					return basicSetNestedCondition((QuerySearchCondition)otherEnd, msgs);
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
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED:
					return eBasicSetContainer(null, SQLQueryPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING:
					return eBasicSetContainer(null, SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE:
					return eBasicSetContainer(null, SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLQueryPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.SEARCH_CONDITION_NESTED__NEST, msgs);
				case SQLQueryPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
					return basicSetNestedCondition(null, msgs);
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
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NAME:
				return getName();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__LABEL:
				return getLabel();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NEGATED_CONDITION:
				return isNegatedCondition() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT:
				return getUpdateStatement();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT:
				return getDeleteStatement();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED:
				return getTableJoined();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT:
				return getCombinedLeft();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT:
				return getCombinedRight();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING:
				return getQuerySelectHaving();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE:
				return getQuerySelectWhere();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NEST:
				return getNest();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
				return getNestedCondition();
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
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NEGATED_CONDITION:
				setNegatedCondition(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)newValue);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)newValue);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED:
				setTableJoined((TableJoined)newValue);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)newValue);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)newValue);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)newValue);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)newValue);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NEST:
				setNest((SearchConditionNested)newValue);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
				setNestedCondition((QuerySearchCondition)newValue);
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
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NEGATED_CONDITION:
				setNegatedCondition(NEGATED_CONDITION_EDEFAULT);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)null);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)null);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED:
				setTableJoined((TableJoined)null);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)null);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)null);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)null);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)null);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NEST:
				setNest((SearchConditionNested)null);
				return;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
				setNestedCondition((QuerySearchCondition)null);
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
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NEGATED_CONDITION:
				return negatedCondition != NEGATED_CONDITION_EDEFAULT;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT:
				return getUpdateStatement() != null;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT:
				return getDeleteStatement() != null;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED:
				return getTableJoined() != null;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT:
				return getCombinedLeft() != null;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT:
				return getCombinedRight() != null;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING:
				return getQuerySelectHaving() != null;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE:
				return getQuerySelectWhere() != null;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NEST:
				return getNest() != null;
			case SQLQueryPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
				return nestedCondition != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLSearchConditionNestedImpl
