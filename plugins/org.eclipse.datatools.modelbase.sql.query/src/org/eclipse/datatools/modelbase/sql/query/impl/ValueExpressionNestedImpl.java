/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionNestedImpl.java,v 1.3 2005/12/19 20:56:36 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Value Expression Nested</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionNestedImpl#getNestedValueExpr <em>Nested Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueExpressionNestedImpl extends QueryValueExpressionImpl implements ValueExpressionNested {
	/**
	 * The cached value of the '{@link #getNestedValueExpr() <em>Nested Value Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getNestedValueExpr()
	 * @generated
	 * @ordered
	 */
  protected QueryValueExpression nestedValueExpr = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ValueExpressionNestedImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryModelPackage.eINSTANCE.getValueExpressionNested();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public QueryValueExpression getNestedValueExpr() {
		return nestedValueExpr;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetNestedValueExpr(QueryValueExpression newNestedValueExpr, NotificationChain msgs) {
		QueryValueExpression oldNestedValueExpr = nestedValueExpr;
		nestedValueExpr = newNestedValueExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR, oldNestedValueExpr, newNestedValueExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setNestedValueExpr(QueryValueExpression newNestedValueExpr) {
		if (newNestedValueExpr != nestedValueExpr) {
			NotificationChain msgs = null;
			if (nestedValueExpr != null)
				msgs = ((InternalEObject)nestedValueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST, QueryValueExpression.class, msgs);
			if (newNestedValueExpr != null)
				msgs = ((InternalEObject)newNestedValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST, QueryValueExpression.class, msgs);
			msgs = basicSetNestedValueExpr(newNestedValueExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR, newNestedValueExpr, newNestedValueExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUES_ROW:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUES_ROW, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__ORDER_BY_VALUE_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__ORDER_BY_VALUE_EXPR, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__RESULT_COLUMN:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__RESULT_COLUMN, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_RIGHT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_PATTERN:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_PATTERN, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_MATCHING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_MATCHING, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__PREDICATE_NULL:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__PREDICATE_NULL, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_RIGHT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_ROW_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_ROW_SELECT_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_SELECT_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_ROW_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_ROW_SELECT_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_VALUE_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_VALUE_SELECT_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT1:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT1, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT2:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT2, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CAST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CAST, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_FUNCTION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_FUNCTION, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_RIGHT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__GROUPING_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__GROUPING_EXPR, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_ELSE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_ELSE, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_ESCAPE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_ESCAPE, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_LABELED_DURATION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_LABELED_DURATION, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NEST, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__UPDATE_SOURCE_EXPR_LIST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__UPDATE_SOURCE_EXPR_LIST, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR:
					if (nestedValueExpr != null)
						msgs = ((InternalEObject)nestedValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR, null, msgs);
					return basicSetNestedValueExpr((QueryValueExpression)otherEnd, msgs);
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
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__DATA_TYPE:
					return basicSetDataType(null, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUES_ROW:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUES_ROW, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__ORDER_BY_VALUE_EXPR:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__ORDER_BY_VALUE_EXPR, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__RESULT_COLUMN:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__RESULT_COLUMN, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_RIGHT:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_RIGHT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_PATTERN:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_PATTERN, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_MATCHING:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_MATCHING, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__PREDICATE_NULL:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__PREDICATE_NULL, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_RIGHT:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_RIGHT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_ROW_SELECT_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_ROW_SELECT_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_SELECT_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_SELECT_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_ROW_SELECT_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_ROW_SELECT_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_VALUE_SELECT_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_VALUE_SELECT_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT1:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT1, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT2:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT2, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CAST:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CAST, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_FUNCTION:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_FUNCTION, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_LEFT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_RIGHT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__GROUPING_EXPR:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__GROUPING_EXPR, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_ELSE:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_ELSE, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_ESCAPE:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_ESCAPE, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_LABELED_DURATION:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_LABELED_DURATION, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NEST:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NEST, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__UPDATE_SOURCE_EXPR_LIST:
					return eBasicSetContainer(null, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__UPDATE_SOURCE_EXPR_LIST, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR:
					return basicSetNestedValueExpr(null, msgs);
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
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUES_ROW:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUES_ROW__EXPR_LIST, ValuesRow.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__ORDER_BY_VALUE_EXPR:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.ORDER_BY_VALUE_EXPRESSION__VALUE_EXPR, OrderByValueExpression.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__RESULT_COLUMN:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.RESULT_COLUMN__VALUE_EXPR, ResultColumn.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_BASIC__RIGHT_VALUE_EXPR, PredicateBasic.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_BASIC__LEFT_VALUE_EXPR, PredicateBasic.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_PATTERN:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR, PredicateLike.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_MATCHING:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR, PredicateLike.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__PREDICATE_NULL:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_IS_NULL__VALUE_EXPR, PredicateIsNull.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST, PredicateInValueList.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR, PredicateInValueList.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_ROW_SELECT_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST, PredicateInValueRowSelect.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_SELECT_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_SELECT__VALUE_EXPR, PredicateInValueSelect.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_ROW_SELECT_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST, PredicateQuantifiedRowSelect.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_VALUE_SELECT_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR, PredicateQuantifiedValueSelect.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR, PredicateBetween.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT1:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1, PredicateBetween.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT2:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2, PredicateBetween.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CAST:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR, ValueExpressionCast.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_FUNCTION:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST, ValueExpressionFunction.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR, ValueExpressionCombined.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR, ValueExpressionCombined.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__GROUPING_EXPR:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.GROUPING_EXPRESSION__VALUE_EXPR, GroupingExpression.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_ELSE:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR, ValueExpressionCaseElse.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR, ValueExpressionCaseSimple.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR, ValueExpressionCaseSimpleContent.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR, ValueExpressionCaseSimpleContent.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR, ValueExpressionCaseSearchContent.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_ESCAPE:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR, PredicateLike.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_LABELED_DURATION:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR, ValueExpressionLabeledDuration.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NEST:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR, ValueExpressionNested.class, msgs);
				case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__UPDATE_SOURCE_EXPR_LIST:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST, UpdateSourceExprList.class, msgs);
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
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NAME:
				return getName();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LABEL:
				return getLabel();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__UNARY_OPERATOR:
				return getUnaryOperator();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__DATA_TYPE:
				return getDataType();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUES_ROW:
				return getValuesRow();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__ORDER_BY_VALUE_EXPR:
				return getOrderByValueExpr();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__RESULT_COLUMN:
				return getResultColumn();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_RIGHT:
				return getBasicRight();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_LEFT:
				return getBasicLeft();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_PATTERN:
				return getLikePattern();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_MATCHING:
				return getLikeMatching();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__PREDICATE_NULL:
				return getPredicateNull();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_RIGHT:
				return getInValueListRight();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_LEFT:
				return getInValueListLeft();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_ROW_SELECT_LEFT:
				return getInValueRowSelectLeft();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_SELECT_LEFT:
				return getInValueSelectLeft();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_ROW_SELECT_LEFT:
				return getQuantifiedRowSelectLeft();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_VALUE_SELECT_LEFT:
				return getQuantifiedValueSelectLeft();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_LEFT:
				return getBetweenLeft();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT1:
				return getBetweenRight1();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT2:
				return getBetweenRight2();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CAST:
				return getValueExprCast();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_FUNCTION:
				return getValueExprFunction();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_LEFT:
				return getValueExprCombinedLeft();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_RIGHT:
				return getValueExprCombinedRight();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__GROUPING_EXPR:
				return getGroupingExpr();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_ELSE:
				return getValueExprCaseElse();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE:
				return getValueExprCaseSimple();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				return getValueExprCaseSimpleContentWhen();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				return getValueExprCaseSimpleContentResult();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_ESCAPE:
				return getLikeEscape();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_LABELED_DURATION:
				return getValueExprLabeledDuration();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NEST:
				return getNest();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__UPDATE_SOURCE_EXPR_LIST:
				return getUpdateSourceExprList();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR:
				return getNestedValueExpr();
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
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__UNARY_OPERATOR:
				setUnaryOperator((ValueExpressionUnaryOperator)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__DATA_TYPE:
				setDataType((DataType)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUES_ROW:
				setValuesRow((ValuesRow)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__ORDER_BY_VALUE_EXPR:
				setOrderByValueExpr((OrderByValueExpression)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__RESULT_COLUMN:
				setResultColumn((ResultColumn)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_RIGHT:
				setBasicRight((PredicateBasic)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_LEFT:
				setBasicLeft((PredicateBasic)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_PATTERN:
				setLikePattern((PredicateLike)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_MATCHING:
				setLikeMatching((PredicateLike)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__PREDICATE_NULL:
				setPredicateNull((PredicateIsNull)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_RIGHT:
				setInValueListRight((PredicateInValueList)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_LEFT:
				setInValueListLeft((PredicateInValueList)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_ROW_SELECT_LEFT:
				setInValueRowSelectLeft((PredicateInValueRowSelect)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_SELECT_LEFT:
				setInValueSelectLeft((PredicateInValueSelect)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_ROW_SELECT_LEFT:
				setQuantifiedRowSelectLeft((PredicateQuantifiedRowSelect)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_VALUE_SELECT_LEFT:
				setQuantifiedValueSelectLeft((PredicateQuantifiedValueSelect)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_LEFT:
				setBetweenLeft((PredicateBetween)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT1:
				setBetweenRight1((PredicateBetween)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT2:
				setBetweenRight2((PredicateBetween)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CAST:
				setValueExprCast((ValueExpressionCast)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_FUNCTION:
				setValueExprFunction((ValueExpressionFunction)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_LEFT:
				setValueExprCombinedLeft((ValueExpressionCombined)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_RIGHT:
				setValueExprCombinedRight((ValueExpressionCombined)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__GROUPING_EXPR:
				setGroupingExpr((GroupingExpression)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_ELSE:
				setValueExprCaseElse((ValueExpressionCaseElse)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE:
				setValueExprCaseSimple((ValueExpressionCaseSimple)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				setValueExprCaseSimpleContentWhen((ValueExpressionCaseSimpleContent)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				setValueExprCaseSimpleContentResult((ValueExpressionCaseSimpleContent)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_ESCAPE:
				setLikeEscape((PredicateLike)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_LABELED_DURATION:
				setValueExprLabeledDuration((ValueExpressionLabeledDuration)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NEST:
				setNest((ValueExpressionNested)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__UPDATE_SOURCE_EXPR_LIST:
				setUpdateSourceExprList((UpdateSourceExprList)newValue);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR:
				setNestedValueExpr((QueryValueExpression)newValue);
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
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__UNARY_OPERATOR:
				setUnaryOperator(UNARY_OPERATOR_EDEFAULT);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__DATA_TYPE:
				setDataType((DataType)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUES_ROW:
				setValuesRow((ValuesRow)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__ORDER_BY_VALUE_EXPR:
				setOrderByValueExpr((OrderByValueExpression)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__RESULT_COLUMN:
				setResultColumn((ResultColumn)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_RIGHT:
				setBasicRight((PredicateBasic)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_LEFT:
				setBasicLeft((PredicateBasic)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_PATTERN:
				setLikePattern((PredicateLike)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_MATCHING:
				setLikeMatching((PredicateLike)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__PREDICATE_NULL:
				setPredicateNull((PredicateIsNull)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_RIGHT:
				setInValueListRight((PredicateInValueList)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_LEFT:
				setInValueListLeft((PredicateInValueList)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_ROW_SELECT_LEFT:
				setInValueRowSelectLeft((PredicateInValueRowSelect)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_SELECT_LEFT:
				setInValueSelectLeft((PredicateInValueSelect)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_ROW_SELECT_LEFT:
				setQuantifiedRowSelectLeft((PredicateQuantifiedRowSelect)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_VALUE_SELECT_LEFT:
				setQuantifiedValueSelectLeft((PredicateQuantifiedValueSelect)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_LEFT:
				setBetweenLeft((PredicateBetween)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT1:
				setBetweenRight1((PredicateBetween)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT2:
				setBetweenRight2((PredicateBetween)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CAST:
				setValueExprCast((ValueExpressionCast)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_FUNCTION:
				setValueExprFunction((ValueExpressionFunction)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_LEFT:
				setValueExprCombinedLeft((ValueExpressionCombined)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_RIGHT:
				setValueExprCombinedRight((ValueExpressionCombined)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__GROUPING_EXPR:
				setGroupingExpr((GroupingExpression)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_ELSE:
				setValueExprCaseElse((ValueExpressionCaseElse)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE:
				setValueExprCaseSimple((ValueExpressionCaseSimple)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				setValueExprCaseSimpleContentWhen((ValueExpressionCaseSimpleContent)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				setValueExprCaseSimpleContentResult((ValueExpressionCaseSimpleContent)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_ESCAPE:
				setLikeEscape((PredicateLike)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_LABELED_DURATION:
				setValueExprLabeledDuration((ValueExpressionLabeledDuration)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NEST:
				setNest((ValueExpressionNested)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__UPDATE_SOURCE_EXPR_LIST:
				setUpdateSourceExprList((UpdateSourceExprList)null);
				return;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR:
				setNestedValueExpr((QueryValueExpression)null);
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
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__UNARY_OPERATOR:
				return unaryOperator != UNARY_OPERATOR_EDEFAULT;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__DATA_TYPE:
				return dataType != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUES_ROW:
				return getValuesRow() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__ORDER_BY_VALUE_EXPR:
				return getOrderByValueExpr() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__RESULT_COLUMN:
				return getResultColumn() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_RIGHT:
				return getBasicRight() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BASIC_LEFT:
				return getBasicLeft() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_PATTERN:
				return getLikePattern() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_MATCHING:
				return getLikeMatching() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__PREDICATE_NULL:
				return getPredicateNull() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_RIGHT:
				return getInValueListRight() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_LEFT:
				return getInValueListLeft() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_ROW_SELECT_LEFT:
				return getInValueRowSelectLeft() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__IN_VALUE_SELECT_LEFT:
				return getInValueSelectLeft() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_ROW_SELECT_LEFT:
				return getQuantifiedRowSelectLeft() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__QUANTIFIED_VALUE_SELECT_LEFT:
				return getQuantifiedValueSelectLeft() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_LEFT:
				return getBetweenLeft() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT1:
				return getBetweenRight1() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT2:
				return getBetweenRight2() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CAST:
				return getValueExprCast() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_FUNCTION:
				return getValueExprFunction() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_LEFT:
				return getValueExprCombinedLeft() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_RIGHT:
				return getValueExprCombinedRight() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__GROUPING_EXPR:
				return getGroupingExpr() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_ELSE:
				return getValueExprCaseElse() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE:
				return getValueExprCaseSimple() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				return getValueExprCaseSimpleContentWhen() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				return getValueExprCaseSimpleContentResult() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__LIKE_ESCAPE:
				return getLikeEscape() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__VALUE_EXPR_LABELED_DURATION:
				return getValueExprLabeledDuration() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NEST:
				return getNest() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__UPDATE_SOURCE_EXPR_LIST:
				return getUpdateSourceExprList() != null;
			case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR:
				return nestedValueExpr != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLValueExpressionNestedImpl
