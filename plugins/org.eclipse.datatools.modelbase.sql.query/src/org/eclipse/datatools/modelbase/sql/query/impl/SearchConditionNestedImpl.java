/**
 * <copyright>
 * </copyright>
 *
 * $Id: SearchConditionNestedImpl.java,v 1.3 2005/12/19 20:56:37 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
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
		return SQLQueryModelPackage.eINSTANCE.getSearchConditionNested();
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, oldNestedCondition, newNestedCondition);
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
				msgs = ((InternalEObject)nestedCondition).eInverseRemove(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST, QuerySearchCondition.class, msgs);
			if (newNestedCondition != null)
				msgs = ((InternalEObject)newNestedCondition).eInverseAdd(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST, QuerySearchCondition.class, msgs);
			msgs = basicSetNestedCondition(newNestedCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, newNestedCondition, newNestedCondition));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NEST, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
					if (nestedCondition != null)
						msgs = ((InternalEObject)nestedCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, null, msgs);
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
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED:
					return eBasicSetContainer(null, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING:
					return eBasicSetContainer(null, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE:
					return eBasicSetContainer(null, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NEST:
					return eBasicSetContainer(null, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NEST, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__WHERE_CLAUSE, QueryUpdateStatement.class, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, QueryDeleteStatement.class, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.TABLE_JOINED__JOIN_CONDITION, TableJoined.class, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION, SearchConditionCombined.class, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION, SearchConditionCombined.class, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__HAVING_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__WHERE_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION, ValueExpressionCaseSearchContent.class, msgs);
				case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NEST:
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
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NAME:
				return getName();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__LABEL:
				return getLabel();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NEGATED_CONDITION:
				return isNegatedCondition() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT:
				return getUpdateStatement();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT:
				return getDeleteStatement();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED:
				return getTableJoined();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT:
				return getCombinedLeft();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT:
				return getCombinedRight();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING:
				return getQuerySelectHaving();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE:
				return getQuerySelectWhere();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NEST:
				return getNest();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
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
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NEGATED_CONDITION:
				setNegatedCondition(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)newValue);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)newValue);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED:
				setTableJoined((TableJoined)newValue);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)newValue);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)newValue);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)newValue);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)newValue);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NEST:
				setNest((SearchConditionNested)newValue);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
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
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NEGATED_CONDITION:
				setNegatedCondition(NEGATED_CONDITION_EDEFAULT);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)null);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)null);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED:
				setTableJoined((TableJoined)null);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)null);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)null);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)null);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)null);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NEST:
				setNest((SearchConditionNested)null);
				return;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
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
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NEGATED_CONDITION:
				return negatedCondition != NEGATED_CONDITION_EDEFAULT;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__UPDATE_STATEMENT:
				return getUpdateStatement() != null;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__DELETE_STATEMENT:
				return getDeleteStatement() != null;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__TABLE_JOINED:
				return getTableJoined() != null;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_LEFT:
				return getCombinedLeft() != null;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__COMBINED_RIGHT:
				return getCombinedRight() != null;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING:
				return getQuerySelectHaving() != null;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE:
				return getQuerySelectWhere() != null;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NEST:
				return getNest() != null;
			case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
				return nestedCondition != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLSearchConditionNestedImpl
