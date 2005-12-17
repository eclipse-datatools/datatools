/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionCombinedImpl.java,v 1.1 2005/12/16 13:11:12 bpayton Exp $
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
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator;
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
 * An implementation of the model object '<em><b>SQL Value Expression Combined</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCombinedImpl#getCombinedOperator <em>Combined Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCombinedImpl#getLeftValueExpr <em>Left Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCombinedImpl#getRightValueExpr <em>Right Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueExpressionCombinedImpl extends QueryValueExpressionImpl implements ValueExpressionCombined {
	/**
	 * The default value of the '{@link #getCombinedOperator() <em>Combined Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCombinedOperator()
	 * @generated
	 * @ordered
	 */
    protected static final ValueExpressionCombinedOperator COMBINED_OPERATOR_EDEFAULT = ValueExpressionCombinedOperator.ADD_LITERAL;

	/**
	 * The cached value of the '{@link #getCombinedOperator() <em>Combined Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCombinedOperator()
	 * @generated
	 * @ordered
	 */
    protected ValueExpressionCombinedOperator combinedOperator = COMBINED_OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLeftValueExpr() <em>Left Value Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLeftValueExpr()
	 * @generated
	 * @ordered
	 */
    protected QueryValueExpression leftValueExpr = null;

	/**
	 * The cached value of the '{@link #getRightValueExpr() <em>Right Value Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRightValueExpr()
	 * @generated
	 * @ordered
	 */
    protected QueryValueExpression rightValueExpr = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ValueExpressionCombinedImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getValueExpressionCombined();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValueExpressionCombinedOperator getCombinedOperator() {
		return combinedOperator;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCombinedOperator(ValueExpressionCombinedOperator newCombinedOperator) {
		ValueExpressionCombinedOperator oldCombinedOperator = combinedOperator;
		combinedOperator = newCombinedOperator == null ? COMBINED_OPERATOR_EDEFAULT : newCombinedOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__COMBINED_OPERATOR, oldCombinedOperator, combinedOperator));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryValueExpression getLeftValueExpr() {
		return leftValueExpr;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetLeftValueExpr(QueryValueExpression newLeftValueExpr, NotificationChain msgs) {
		QueryValueExpression oldLeftValueExpr = leftValueExpr;
		leftValueExpr = newLeftValueExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR, oldLeftValueExpr, newLeftValueExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLeftValueExpr(QueryValueExpression newLeftValueExpr) {
		if (newLeftValueExpr != leftValueExpr) {
			NotificationChain msgs = null;
			if (leftValueExpr != null)
				msgs = ((InternalEObject)leftValueExpr).eInverseRemove(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT, QueryValueExpression.class, msgs);
			if (newLeftValueExpr != null)
				msgs = ((InternalEObject)newLeftValueExpr).eInverseAdd(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT, QueryValueExpression.class, msgs);
			msgs = basicSetLeftValueExpr(newLeftValueExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR, newLeftValueExpr, newLeftValueExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryValueExpression getRightValueExpr() {
		return rightValueExpr;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetRightValueExpr(QueryValueExpression newRightValueExpr, NotificationChain msgs) {
		QueryValueExpression oldRightValueExpr = rightValueExpr;
		rightValueExpr = newRightValueExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR, oldRightValueExpr, newRightValueExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setRightValueExpr(QueryValueExpression newRightValueExpr) {
		if (newRightValueExpr != rightValueExpr) {
			NotificationChain msgs = null;
			if (rightValueExpr != null)
				msgs = ((InternalEObject)rightValueExpr).eInverseRemove(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT, QueryValueExpression.class, msgs);
			if (newRightValueExpr != null)
				msgs = ((InternalEObject)newRightValueExpr).eInverseAdd(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT, QueryValueExpression.class, msgs);
			msgs = basicSetRightValueExpr(newRightValueExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR, newRightValueExpr, newRightValueExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUES_ROW:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUES_ROW, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__ORDER_BY_VALUE_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__ORDER_BY_VALUE_EXPR, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RESULT_COLUMN:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RESULT_COLUMN, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_RIGHT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_PATTERN:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_PATTERN, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_MATCHING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_MATCHING, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__PREDICATE_NULL:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__PREDICATE_NULL, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_RIGHT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_ROW_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_ROW_SELECT_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_SELECT_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_ROW_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_ROW_SELECT_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_VALUE_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_VALUE_SELECT_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT1:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT1, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT2:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT2, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CAST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CAST, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_FUNCTION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_FUNCTION, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_RIGHT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__GROUPING_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__GROUPING_EXPR, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_ELSE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_ELSE, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_ESCAPE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_ESCAPE, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_LABELED_DURATION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_LABELED_DURATION, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__NEST, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__UPDATE_SOURCE_EXPR_LIST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__UPDATE_SOURCE_EXPR_LIST, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR:
					if (leftValueExpr != null)
						msgs = ((InternalEObject)leftValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR, null, msgs);
					return basicSetLeftValueExpr((QueryValueExpression)otherEnd, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR:
					if (rightValueExpr != null)
						msgs = ((InternalEObject)rightValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR, null, msgs);
					return basicSetRightValueExpr((QueryValueExpression)otherEnd, msgs);
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
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__DATA_TYPE:
					return basicSetDataType(null, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUES_ROW:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUES_ROW, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__ORDER_BY_VALUE_EXPR:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__ORDER_BY_VALUE_EXPR, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RESULT_COLUMN:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RESULT_COLUMN, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_RIGHT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_PATTERN:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_PATTERN, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_MATCHING:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_MATCHING, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__PREDICATE_NULL:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__PREDICATE_NULL, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_RIGHT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_ROW_SELECT_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_ROW_SELECT_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_SELECT_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_SELECT_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_ROW_SELECT_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_ROW_SELECT_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_VALUE_SELECT_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_VALUE_SELECT_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT1:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT1, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT2:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT2, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CAST:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CAST, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_FUNCTION:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_FUNCTION, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_LEFT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_RIGHT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__GROUPING_EXPR:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__GROUPING_EXPR, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_ELSE:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_ELSE, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_ESCAPE:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_ESCAPE, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_LABELED_DURATION:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_LABELED_DURATION, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__NEST, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__UPDATE_SOURCE_EXPR_LIST:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__UPDATE_SOURCE_EXPR_LIST, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR:
					return basicSetLeftValueExpr(null, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR:
					return basicSetRightValueExpr(null, msgs);
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
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__NAME:
				return getName();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LABEL:
				return getLabel();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__UNARY_OPERATOR:
				return getUnaryOperator();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__DATA_TYPE:
				return getDataType();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUES_ROW:
				return getValuesRow();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__ORDER_BY_VALUE_EXPR:
				return getOrderByValueExpr();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RESULT_COLUMN:
				return getResultColumn();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_RIGHT:
				return getBasicRight();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_LEFT:
				return getBasicLeft();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_PATTERN:
				return getLikePattern();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_MATCHING:
				return getLikeMatching();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__PREDICATE_NULL:
				return getPredicateNull();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_RIGHT:
				return getInValueListRight();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_LEFT:
				return getInValueListLeft();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_ROW_SELECT_LEFT:
				return getInValueRowSelectLeft();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_SELECT_LEFT:
				return getInValueSelectLeft();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_ROW_SELECT_LEFT:
				return getQuantifiedRowSelectLeft();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_VALUE_SELECT_LEFT:
				return getQuantifiedValueSelectLeft();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_LEFT:
				return getBetweenLeft();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT1:
				return getBetweenRight1();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT2:
				return getBetweenRight2();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CAST:
				return getValueExprCast();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_FUNCTION:
				return getValueExprFunction();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_LEFT:
				return getValueExprCombinedLeft();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_RIGHT:
				return getValueExprCombinedRight();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__GROUPING_EXPR:
				return getGroupingExpr();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_ELSE:
				return getValueExprCaseElse();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE:
				return getValueExprCaseSimple();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				return getValueExprCaseSimpleContentWhen();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				return getValueExprCaseSimpleContentResult();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_ESCAPE:
				return getLikeEscape();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_LABELED_DURATION:
				return getValueExprLabeledDuration();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__NEST:
				return getNest();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__UPDATE_SOURCE_EXPR_LIST:
				return getUpdateSourceExprList();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__COMBINED_OPERATOR:
				return getCombinedOperator();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR:
				return getLeftValueExpr();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR:
				return getRightValueExpr();
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
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__UNARY_OPERATOR:
				setUnaryOperator((ValueExpressionUnaryOperator)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__DATA_TYPE:
				setDataType((DataType)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUES_ROW:
				setValuesRow((ValuesRow)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__ORDER_BY_VALUE_EXPR:
				setOrderByValueExpr((OrderByValueExpression)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RESULT_COLUMN:
				setResultColumn((ResultColumn)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_RIGHT:
				setBasicRight((PredicateBasic)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_LEFT:
				setBasicLeft((PredicateBasic)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_PATTERN:
				setLikePattern((PredicateLike)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_MATCHING:
				setLikeMatching((PredicateLike)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__PREDICATE_NULL:
				setPredicateNull((PredicateIsNull)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_RIGHT:
				setInValueListRight((PredicateInValueList)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_LEFT:
				setInValueListLeft((PredicateInValueList)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_ROW_SELECT_LEFT:
				setInValueRowSelectLeft((PredicateInValueRowSelect)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_SELECT_LEFT:
				setInValueSelectLeft((PredicateInValueSelect)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_ROW_SELECT_LEFT:
				setQuantifiedRowSelectLeft((PredicateQuantifiedRowSelect)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_VALUE_SELECT_LEFT:
				setQuantifiedValueSelectLeft((PredicateQuantifiedValueSelect)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_LEFT:
				setBetweenLeft((PredicateBetween)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT1:
				setBetweenRight1((PredicateBetween)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT2:
				setBetweenRight2((PredicateBetween)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CAST:
				setValueExprCast((ValueExpressionCast)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_FUNCTION:
				setValueExprFunction((ValueExpressionFunction)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_LEFT:
				setValueExprCombinedLeft((ValueExpressionCombined)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_RIGHT:
				setValueExprCombinedRight((ValueExpressionCombined)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__GROUPING_EXPR:
				setGroupingExpr((GroupingExpression)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_ELSE:
				setValueExprCaseElse((ValueExpressionCaseElse)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE:
				setValueExprCaseSimple((ValueExpressionCaseSimple)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				setValueExprCaseSimpleContentWhen((ValueExpressionCaseSimpleContent)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				setValueExprCaseSimpleContentResult((ValueExpressionCaseSimpleContent)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_ESCAPE:
				setLikeEscape((PredicateLike)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_LABELED_DURATION:
				setValueExprLabeledDuration((ValueExpressionLabeledDuration)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__NEST:
				setNest((ValueExpressionNested)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__UPDATE_SOURCE_EXPR_LIST:
				setUpdateSourceExprList((UpdateSourceExprList)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__COMBINED_OPERATOR:
				setCombinedOperator((ValueExpressionCombinedOperator)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR:
				setLeftValueExpr((QueryValueExpression)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR:
				setRightValueExpr((QueryValueExpression)newValue);
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
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__UNARY_OPERATOR:
				setUnaryOperator(UNARY_OPERATOR_EDEFAULT);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__DATA_TYPE:
				setDataType((DataType)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUES_ROW:
				setValuesRow((ValuesRow)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__ORDER_BY_VALUE_EXPR:
				setOrderByValueExpr((OrderByValueExpression)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RESULT_COLUMN:
				setResultColumn((ResultColumn)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_RIGHT:
				setBasicRight((PredicateBasic)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_LEFT:
				setBasicLeft((PredicateBasic)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_PATTERN:
				setLikePattern((PredicateLike)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_MATCHING:
				setLikeMatching((PredicateLike)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__PREDICATE_NULL:
				setPredicateNull((PredicateIsNull)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_RIGHT:
				setInValueListRight((PredicateInValueList)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_LEFT:
				setInValueListLeft((PredicateInValueList)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_ROW_SELECT_LEFT:
				setInValueRowSelectLeft((PredicateInValueRowSelect)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_SELECT_LEFT:
				setInValueSelectLeft((PredicateInValueSelect)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_ROW_SELECT_LEFT:
				setQuantifiedRowSelectLeft((PredicateQuantifiedRowSelect)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_VALUE_SELECT_LEFT:
				setQuantifiedValueSelectLeft((PredicateQuantifiedValueSelect)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_LEFT:
				setBetweenLeft((PredicateBetween)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT1:
				setBetweenRight1((PredicateBetween)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT2:
				setBetweenRight2((PredicateBetween)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CAST:
				setValueExprCast((ValueExpressionCast)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_FUNCTION:
				setValueExprFunction((ValueExpressionFunction)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_LEFT:
				setValueExprCombinedLeft((ValueExpressionCombined)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_RIGHT:
				setValueExprCombinedRight((ValueExpressionCombined)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__GROUPING_EXPR:
				setGroupingExpr((GroupingExpression)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_ELSE:
				setValueExprCaseElse((ValueExpressionCaseElse)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE:
				setValueExprCaseSimple((ValueExpressionCaseSimple)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				setValueExprCaseSimpleContentWhen((ValueExpressionCaseSimpleContent)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				setValueExprCaseSimpleContentResult((ValueExpressionCaseSimpleContent)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_ESCAPE:
				setLikeEscape((PredicateLike)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_LABELED_DURATION:
				setValueExprLabeledDuration((ValueExpressionLabeledDuration)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__NEST:
				setNest((ValueExpressionNested)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__UPDATE_SOURCE_EXPR_LIST:
				setUpdateSourceExprList((UpdateSourceExprList)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__COMBINED_OPERATOR:
				setCombinedOperator(COMBINED_OPERATOR_EDEFAULT);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR:
				setLeftValueExpr((QueryValueExpression)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR:
				setRightValueExpr((QueryValueExpression)null);
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
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__UNARY_OPERATOR:
				return unaryOperator != UNARY_OPERATOR_EDEFAULT;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__DATA_TYPE:
				return dataType != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUES_ROW:
				return getValuesRow() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__ORDER_BY_VALUE_EXPR:
				return getOrderByValueExpr() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RESULT_COLUMN:
				return getResultColumn() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_RIGHT:
				return getBasicRight() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BASIC_LEFT:
				return getBasicLeft() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_PATTERN:
				return getLikePattern() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_MATCHING:
				return getLikeMatching() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__PREDICATE_NULL:
				return getPredicateNull() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_RIGHT:
				return getInValueListRight() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_LEFT:
				return getInValueListLeft() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_ROW_SELECT_LEFT:
				return getInValueRowSelectLeft() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__IN_VALUE_SELECT_LEFT:
				return getInValueSelectLeft() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_ROW_SELECT_LEFT:
				return getQuantifiedRowSelectLeft() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__QUANTIFIED_VALUE_SELECT_LEFT:
				return getQuantifiedValueSelectLeft() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_LEFT:
				return getBetweenLeft() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT1:
				return getBetweenRight1() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT2:
				return getBetweenRight2() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CAST:
				return getValueExprCast() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_FUNCTION:
				return getValueExprFunction() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_LEFT:
				return getValueExprCombinedLeft() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_RIGHT:
				return getValueExprCombinedRight() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__GROUPING_EXPR:
				return getGroupingExpr() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_ELSE:
				return getValueExprCaseElse() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE:
				return getValueExprCaseSimple() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				return getValueExprCaseSimpleContentWhen() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				return getValueExprCaseSimpleContentResult() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LIKE_ESCAPE:
				return getLikeEscape() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__VALUE_EXPR_LABELED_DURATION:
				return getValueExprLabeledDuration() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__NEST:
				return getNest() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__UPDATE_SOURCE_EXPR_LIST:
				return getUpdateSourceExprList() != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__COMBINED_OPERATOR:
				return combinedOperator != COMBINED_OPERATOR_EDEFAULT;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR:
				return leftValueExpr != null;
			case SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR:
				return rightValueExpr != null;
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
		result.append(" (combinedOperator: ");
		result.append(combinedOperator);
		result.append(')');
		return result.toString();
	}

} //SQLValueExpressionCombinedImpl
