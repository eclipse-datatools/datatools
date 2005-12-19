/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryValueExpressionImpl.java,v 1.2 2005/12/17 01:46:21 bpayton Exp $
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Value Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getUnaryOperator <em>Unary Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValuesRow <em>Values Row</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getOrderByValueExpr <em>Order By Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getResultColumn <em>Result Column</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getBasicRight <em>Basic Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getBasicLeft <em>Basic Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getLikePattern <em>Like Pattern</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getLikeMatching <em>Like Matching</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getPredicateNull <em>Predicate Null</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getInValueListRight <em>In Value List Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getInValueListLeft <em>In Value List Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getInValueRowSelectLeft <em>In Value Row Select Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getInValueSelectLeft <em>In Value Select Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getQuantifiedRowSelectLeft <em>Quantified Row Select Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getQuantifiedValueSelectLeft <em>Quantified Value Select Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getBetweenLeft <em>Between Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getBetweenRight1 <em>Between Right1</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getBetweenRight2 <em>Between Right2</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCast <em>Value Expr Cast</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprFunction <em>Value Expr Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCombinedLeft <em>Value Expr Combined Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCombinedRight <em>Value Expr Combined Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getGroupingExpr <em>Grouping Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCaseElse <em>Value Expr Case Else</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCaseSimple <em>Value Expr Case Simple</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCaseSimpleContentWhen <em>Value Expr Case Simple Content When</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCaseSimpleContentResult <em>Value Expr Case Simple Content Result</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCaseSearchContent <em>Value Expr Case Search Content</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getLikeEscape <em>Like Escape</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprLabeledDuration <em>Value Expr Labeled Duration</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getNest <em>Nest</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getUpdateSourceExprList <em>Update Source Expr List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class QueryValueExpressionImpl extends SQLQueryObjectImpl implements QueryValueExpression {
	/**
	 * The default value of the '{@link #getUnaryOperator() <em>Unary Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUnaryOperator()
	 * @generated
	 * @ordered
	 */
    protected static final ValueExpressionUnaryOperator UNARY_OPERATOR_EDEFAULT = ValueExpressionUnaryOperator.NONE_LITERAL;

	/**
	 * The cached value of the '{@link #getUnaryOperator() <em>Unary Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUnaryOperator()
	 * @generated
	 * @ordered
	 */
    protected ValueExpressionUnaryOperator unaryOperator = UNARY_OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDataType() <em>Data Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
    protected DataType dataType = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected QueryValueExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getQueryValueExpression();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValueExpressionUnaryOperator getUnaryOperator() {
		return unaryOperator;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUnaryOperator(ValueExpressionUnaryOperator newUnaryOperator) {
		ValueExpressionUnaryOperator oldUnaryOperator = unaryOperator;
		unaryOperator = newUnaryOperator == null ? UNARY_OPERATOR_EDEFAULT : newUnaryOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR, oldUnaryOperator, unaryOperator));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DataType getDataType() {
		return dataType;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetDataType(DataType newDataType, NotificationChain msgs) {
		DataType oldDataType = dataType;
		dataType = newDataType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE, oldDataType, newDataType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDataType(DataType newDataType) {
		if (newDataType != dataType) {
			NotificationChain msgs = null;
			if (dataType != null)
				msgs = ((InternalEObject)dataType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE, null, msgs);
			if (newDataType != null)
				msgs = ((InternalEObject)newDataType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE, null, msgs);
			msgs = basicSetDataType(newDataType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE, newDataType, newDataType));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValuesRow getValuesRow() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW) return null;
		return (ValuesRow)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValuesRow(ValuesRow newValuesRow) {
		if (newValuesRow != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW && newValuesRow != null)) {
			if (EcoreUtil.isAncestor(this, newValuesRow))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValuesRow != null)
				msgs = ((InternalEObject)newValuesRow).eInverseAdd(this, SQLQueryPackage.VALUES_ROW__EXPR_LIST, ValuesRow.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValuesRow, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW, newValuesRow, newValuesRow));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public OrderByValueExpression getOrderByValueExpr() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR) return null;
		return (OrderByValueExpression)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setOrderByValueExpr(OrderByValueExpression newOrderByValueExpr) {
		if (newOrderByValueExpr != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR && newOrderByValueExpr != null)) {
			if (EcoreUtil.isAncestor(this, newOrderByValueExpr))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOrderByValueExpr != null)
				msgs = ((InternalEObject)newOrderByValueExpr).eInverseAdd(this, SQLQueryPackage.ORDER_BY_VALUE_EXPRESSION__VALUE_EXPR, OrderByValueExpression.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newOrderByValueExpr, SQLQueryPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR, newOrderByValueExpr, newOrderByValueExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ResultColumn getResultColumn() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN) return null;
		return (ResultColumn)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setResultColumn(ResultColumn newResultColumn) {
		if (newResultColumn != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN && newResultColumn != null)) {
			if (EcoreUtil.isAncestor(this, newResultColumn))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newResultColumn != null)
				msgs = ((InternalEObject)newResultColumn).eInverseAdd(this, SQLQueryPackage.RESULT_COLUMN__VALUE_EXPR, ResultColumn.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newResultColumn, SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN, newResultColumn, newResultColumn));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateBasic getBasicRight() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT) return null;
		return (PredicateBasic)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBasicRight(PredicateBasic newBasicRight) {
		if (newBasicRight != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT && newBasicRight != null)) {
			if (EcoreUtil.isAncestor(this, newBasicRight))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBasicRight != null)
				msgs = ((InternalEObject)newBasicRight).eInverseAdd(this, SQLQueryPackage.PREDICATE_BASIC__RIGHT_VALUE_EXPR, PredicateBasic.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newBasicRight, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT, newBasicRight, newBasicRight));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateBasic getBasicLeft() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT) return null;
		return (PredicateBasic)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBasicLeft(PredicateBasic newBasicLeft) {
		if (newBasicLeft != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT && newBasicLeft != null)) {
			if (EcoreUtil.isAncestor(this, newBasicLeft))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBasicLeft != null)
				msgs = ((InternalEObject)newBasicLeft).eInverseAdd(this, SQLQueryPackage.PREDICATE_BASIC__LEFT_VALUE_EXPR, PredicateBasic.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newBasicLeft, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT, newBasicLeft, newBasicLeft));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateLike getLikePattern() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN) return null;
		return (PredicateLike)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLikePattern(PredicateLike newLikePattern) {
		if (newLikePattern != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN && newLikePattern != null)) {
			if (EcoreUtil.isAncestor(this, newLikePattern))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLikePattern != null)
				msgs = ((InternalEObject)newLikePattern).eInverseAdd(this, SQLQueryPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR, PredicateLike.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newLikePattern, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN, newLikePattern, newLikePattern));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateLike getLikeMatching() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING) return null;
		return (PredicateLike)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLikeMatching(PredicateLike newLikeMatching) {
		if (newLikeMatching != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING && newLikeMatching != null)) {
			if (EcoreUtil.isAncestor(this, newLikeMatching))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLikeMatching != null)
				msgs = ((InternalEObject)newLikeMatching).eInverseAdd(this, SQLQueryPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR, PredicateLike.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newLikeMatching, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING, newLikeMatching, newLikeMatching));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateIsNull getPredicateNull() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL) return null;
		return (PredicateIsNull)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPredicateNull(PredicateIsNull newPredicateNull) {
		if (newPredicateNull != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL && newPredicateNull != null)) {
			if (EcoreUtil.isAncestor(this, newPredicateNull))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPredicateNull != null)
				msgs = ((InternalEObject)newPredicateNull).eInverseAdd(this, SQLQueryPackage.PREDICATE_IS_NULL__VALUE_EXPR, PredicateIsNull.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newPredicateNull, SQLQueryPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL, newPredicateNull, newPredicateNull));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateInValueList getInValueListRight() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT) return null;
		return (PredicateInValueList)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setInValueListRight(PredicateInValueList newInValueListRight) {
		if (newInValueListRight != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT && newInValueListRight != null)) {
			if (EcoreUtil.isAncestor(this, newInValueListRight))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInValueListRight != null)
				msgs = ((InternalEObject)newInValueListRight).eInverseAdd(this, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST, PredicateInValueList.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newInValueListRight, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT, newInValueListRight, newInValueListRight));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateInValueList getInValueListLeft() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT) return null;
		return (PredicateInValueList)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setInValueListLeft(PredicateInValueList newInValueListLeft) {
		if (newInValueListLeft != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT && newInValueListLeft != null)) {
			if (EcoreUtil.isAncestor(this, newInValueListLeft))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInValueListLeft != null)
				msgs = ((InternalEObject)newInValueListLeft).eInverseAdd(this, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR, PredicateInValueList.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newInValueListLeft, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT, newInValueListLeft, newInValueListLeft));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateInValueRowSelect getInValueRowSelectLeft() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT) return null;
		return (PredicateInValueRowSelect)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setInValueRowSelectLeft(PredicateInValueRowSelect newInValueRowSelectLeft) {
		if (newInValueRowSelectLeft != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT && newInValueRowSelectLeft != null)) {
			if (EcoreUtil.isAncestor(this, newInValueRowSelectLeft))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInValueRowSelectLeft != null)
				msgs = ((InternalEObject)newInValueRowSelectLeft).eInverseAdd(this, SQLQueryPackage.PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST, PredicateInValueRowSelect.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newInValueRowSelectLeft, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT, newInValueRowSelectLeft, newInValueRowSelectLeft));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateInValueSelect getInValueSelectLeft() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT) return null;
		return (PredicateInValueSelect)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setInValueSelectLeft(PredicateInValueSelect newInValueSelectLeft) {
		if (newInValueSelectLeft != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT && newInValueSelectLeft != null)) {
			if (EcoreUtil.isAncestor(this, newInValueSelectLeft))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInValueSelectLeft != null)
				msgs = ((InternalEObject)newInValueSelectLeft).eInverseAdd(this, SQLQueryPackage.PREDICATE_IN_VALUE_SELECT__VALUE_EXPR, PredicateInValueSelect.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newInValueSelectLeft, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT, newInValueSelectLeft, newInValueSelectLeft));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateQuantifiedRowSelect getQuantifiedRowSelectLeft() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT) return null;
		return (PredicateQuantifiedRowSelect)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setQuantifiedRowSelectLeft(PredicateQuantifiedRowSelect newQuantifiedRowSelectLeft) {
		if (newQuantifiedRowSelectLeft != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT && newQuantifiedRowSelectLeft != null)) {
			if (EcoreUtil.isAncestor(this, newQuantifiedRowSelectLeft))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQuantifiedRowSelectLeft != null)
				msgs = ((InternalEObject)newQuantifiedRowSelectLeft).eInverseAdd(this, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST, PredicateQuantifiedRowSelect.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newQuantifiedRowSelectLeft, SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT, newQuantifiedRowSelectLeft, newQuantifiedRowSelectLeft));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateQuantifiedValueSelect getQuantifiedValueSelectLeft() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT) return null;
		return (PredicateQuantifiedValueSelect)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setQuantifiedValueSelectLeft(PredicateQuantifiedValueSelect newQuantifiedValueSelectLeft) {
		if (newQuantifiedValueSelectLeft != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT && newQuantifiedValueSelectLeft != null)) {
			if (EcoreUtil.isAncestor(this, newQuantifiedValueSelectLeft))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQuantifiedValueSelectLeft != null)
				msgs = ((InternalEObject)newQuantifiedValueSelectLeft).eInverseAdd(this, SQLQueryPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR, PredicateQuantifiedValueSelect.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newQuantifiedValueSelectLeft, SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT, newQuantifiedValueSelectLeft, newQuantifiedValueSelectLeft));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateBetween getBetweenLeft() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT) return null;
		return (PredicateBetween)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBetweenLeft(PredicateBetween newBetweenLeft) {
		if (newBetweenLeft != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT && newBetweenLeft != null)) {
			if (EcoreUtil.isAncestor(this, newBetweenLeft))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBetweenLeft != null)
				msgs = ((InternalEObject)newBetweenLeft).eInverseAdd(this, SQLQueryPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR, PredicateBetween.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newBetweenLeft, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT, newBetweenLeft, newBetweenLeft));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateBetween getBetweenRight1() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1) return null;
		return (PredicateBetween)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBetweenRight1(PredicateBetween newBetweenRight1) {
		if (newBetweenRight1 != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1 && newBetweenRight1 != null)) {
			if (EcoreUtil.isAncestor(this, newBetweenRight1))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBetweenRight1 != null)
				msgs = ((InternalEObject)newBetweenRight1).eInverseAdd(this, SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1, PredicateBetween.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newBetweenRight1, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1, newBetweenRight1, newBetweenRight1));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateBetween getBetweenRight2() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2) return null;
		return (PredicateBetween)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBetweenRight2(PredicateBetween newBetweenRight2) {
		if (newBetweenRight2 != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2 && newBetweenRight2 != null)) {
			if (EcoreUtil.isAncestor(this, newBetweenRight2))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBetweenRight2 != null)
				msgs = ((InternalEObject)newBetweenRight2).eInverseAdd(this, SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2, PredicateBetween.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newBetweenRight2, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2, newBetweenRight2, newBetweenRight2));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValueExpressionCast getValueExprCast() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST) return null;
		return (ValueExpressionCast)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueExprCast(ValueExpressionCast newValueExprCast) {
		if (newValueExprCast != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST && newValueExprCast != null)) {
			if (EcoreUtil.isAncestor(this, newValueExprCast))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueExprCast != null)
				msgs = ((InternalEObject)newValueExprCast).eInverseAdd(this, SQLQueryPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR, ValueExpressionCast.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueExprCast, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST, newValueExprCast, newValueExprCast));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValueExpressionFunction getValueExprFunction() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION) return null;
		return (ValueExpressionFunction)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueExprFunction(ValueExpressionFunction newValueExprFunction) {
		if (newValueExprFunction != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION && newValueExprFunction != null)) {
			if (EcoreUtil.isAncestor(this, newValueExprFunction))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueExprFunction != null)
				msgs = ((InternalEObject)newValueExprFunction).eInverseAdd(this, SQLQueryPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST, ValueExpressionFunction.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueExprFunction, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION, newValueExprFunction, newValueExprFunction));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValueExpressionCombined getValueExprCombinedLeft() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT) return null;
		return (ValueExpressionCombined)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueExprCombinedLeft(ValueExpressionCombined newValueExprCombinedLeft) {
		if (newValueExprCombinedLeft != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT && newValueExprCombinedLeft != null)) {
			if (EcoreUtil.isAncestor(this, newValueExprCombinedLeft))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueExprCombinedLeft != null)
				msgs = ((InternalEObject)newValueExprCombinedLeft).eInverseAdd(this, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR, ValueExpressionCombined.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueExprCombinedLeft, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT, newValueExprCombinedLeft, newValueExprCombinedLeft));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValueExpressionCombined getValueExprCombinedRight() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT) return null;
		return (ValueExpressionCombined)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueExprCombinedRight(ValueExpressionCombined newValueExprCombinedRight) {
		if (newValueExprCombinedRight != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT && newValueExprCombinedRight != null)) {
			if (EcoreUtil.isAncestor(this, newValueExprCombinedRight))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueExprCombinedRight != null)
				msgs = ((InternalEObject)newValueExprCombinedRight).eInverseAdd(this, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR, ValueExpressionCombined.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueExprCombinedRight, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT, newValueExprCombinedRight, newValueExprCombinedRight));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GroupingExpression getGroupingExpr() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR) return null;
		return (GroupingExpression)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGroupingExpr(GroupingExpression newGroupingExpr) {
		if (newGroupingExpr != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR && newGroupingExpr != null)) {
			if (EcoreUtil.isAncestor(this, newGroupingExpr))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGroupingExpr != null)
				msgs = ((InternalEObject)newGroupingExpr).eInverseAdd(this, SQLQueryPackage.GROUPING_EXPRESSION__VALUE_EXPR, GroupingExpression.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newGroupingExpr, SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR, newGroupingExpr, newGroupingExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValueExpressionCaseElse getValueExprCaseElse() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE) return null;
		return (ValueExpressionCaseElse)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueExprCaseElse(ValueExpressionCaseElse newValueExprCaseElse) {
		if (newValueExprCaseElse != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE && newValueExprCaseElse != null)) {
			if (EcoreUtil.isAncestor(this, newValueExprCaseElse))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueExprCaseElse != null)
				msgs = ((InternalEObject)newValueExprCaseElse).eInverseAdd(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR, ValueExpressionCaseElse.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueExprCaseElse, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE, newValueExprCaseElse, newValueExprCaseElse));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValueExpressionCaseSimple getValueExprCaseSimple() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE) return null;
		return (ValueExpressionCaseSimple)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueExprCaseSimple(ValueExpressionCaseSimple newValueExprCaseSimple) {
		if (newValueExprCaseSimple != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE && newValueExprCaseSimple != null)) {
			if (EcoreUtil.isAncestor(this, newValueExprCaseSimple))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueExprCaseSimple != null)
				msgs = ((InternalEObject)newValueExprCaseSimple).eInverseAdd(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR, ValueExpressionCaseSimple.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueExprCaseSimple, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE, newValueExprCaseSimple, newValueExprCaseSimple));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValueExpressionCaseSimpleContent getValueExprCaseSimpleContentWhen() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN) return null;
		return (ValueExpressionCaseSimpleContent)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueExprCaseSimpleContentWhen(ValueExpressionCaseSimpleContent newValueExprCaseSimpleContentWhen) {
		if (newValueExprCaseSimpleContentWhen != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN && newValueExprCaseSimpleContentWhen != null)) {
			if (EcoreUtil.isAncestor(this, newValueExprCaseSimpleContentWhen))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueExprCaseSimpleContentWhen != null)
				msgs = ((InternalEObject)newValueExprCaseSimpleContentWhen).eInverseAdd(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR, ValueExpressionCaseSimpleContent.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueExprCaseSimpleContentWhen, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN, newValueExprCaseSimpleContentWhen, newValueExprCaseSimpleContentWhen));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValueExpressionCaseSimpleContent getValueExprCaseSimpleContentResult() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT) return null;
		return (ValueExpressionCaseSimpleContent)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueExprCaseSimpleContentResult(ValueExpressionCaseSimpleContent newValueExprCaseSimpleContentResult) {
		if (newValueExprCaseSimpleContentResult != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT && newValueExprCaseSimpleContentResult != null)) {
			if (EcoreUtil.isAncestor(this, newValueExprCaseSimpleContentResult))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueExprCaseSimpleContentResult != null)
				msgs = ((InternalEObject)newValueExprCaseSimpleContentResult).eInverseAdd(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR, ValueExpressionCaseSimpleContent.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueExprCaseSimpleContentResult, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT, newValueExprCaseSimpleContentResult, newValueExprCaseSimpleContentResult));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValueExpressionCaseSearchContent getValueExprCaseSearchContent() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT) return null;
		return (ValueExpressionCaseSearchContent)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueExprCaseSearchContent(ValueExpressionCaseSearchContent newValueExprCaseSearchContent) {
		if (newValueExprCaseSearchContent != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT && newValueExprCaseSearchContent != null)) {
			if (EcoreUtil.isAncestor(this, newValueExprCaseSearchContent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueExprCaseSearchContent != null)
				msgs = ((InternalEObject)newValueExprCaseSearchContent).eInverseAdd(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR, ValueExpressionCaseSearchContent.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueExprCaseSearchContent, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT, newValueExprCaseSearchContent, newValueExprCaseSearchContent));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateLike getLikeEscape() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE) return null;
		return (PredicateLike)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLikeEscape(PredicateLike newLikeEscape) {
		if (newLikeEscape != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE && newLikeEscape != null)) {
			if (EcoreUtil.isAncestor(this, newLikeEscape))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLikeEscape != null)
				msgs = ((InternalEObject)newLikeEscape).eInverseAdd(this, SQLQueryPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR, PredicateLike.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newLikeEscape, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE, newLikeEscape, newLikeEscape));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValueExpressionLabeledDuration getValueExprLabeledDuration() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION) return null;
		return (ValueExpressionLabeledDuration)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueExprLabeledDuration(ValueExpressionLabeledDuration newValueExprLabeledDuration) {
		if (newValueExprLabeledDuration != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION && newValueExprLabeledDuration != null)) {
			if (EcoreUtil.isAncestor(this, newValueExprLabeledDuration))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueExprLabeledDuration != null)
				msgs = ((InternalEObject)newValueExprLabeledDuration).eInverseAdd(this, SQLQueryPackage.VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR, ValueExpressionLabeledDuration.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueExprLabeledDuration, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION, newValueExprLabeledDuration, newValueExprLabeledDuration));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ValueExpressionNested getNest() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__NEST) return null;
		return (ValueExpressionNested)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setNest(ValueExpressionNested newNest) {
		if (newNest != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__NEST && newNest != null)) {
			if (EcoreUtil.isAncestor(this, newNest))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newNest != null)
				msgs = ((InternalEObject)newNest).eInverseAdd(this, SQLQueryPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR, ValueExpressionNested.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newNest, SQLQueryPackage.QUERY_VALUE_EXPRESSION__NEST, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__NEST, newNest, newNest));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public UpdateSourceExprList getUpdateSourceExprList() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST) return null;
		return (UpdateSourceExprList)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setUpdateSourceExprList(UpdateSourceExprList newUpdateSourceExprList) {
		if (newUpdateSourceExprList != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST && newUpdateSourceExprList != null)) {
			if (EcoreUtil.isAncestor(this, newUpdateSourceExprList))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUpdateSourceExprList != null)
				msgs = ((InternalEObject)newUpdateSourceExprList).eInverseAdd(this, SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST, UpdateSourceExprList.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newUpdateSourceExprList, SQLQueryPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST, newUpdateSourceExprList, newUpdateSourceExprList));
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getSQL() {
        return super.getSQL();
    }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSQL(String sqlText) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__NEST, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST, msgs);
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
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE:
					return basicSetDataType(null, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__NEST, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST, msgs);
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
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUES_ROW__EXPR_LIST, ValuesRow.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR:
					return eContainer.eInverseRemove(this, SQLQueryPackage.ORDER_BY_VALUE_EXPRESSION__VALUE_EXPR, OrderByValueExpression.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN:
					return eContainer.eInverseRemove(this, SQLQueryPackage.RESULT_COLUMN__VALUE_EXPR, ResultColumn.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_BASIC__RIGHT_VALUE_EXPR, PredicateBasic.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_BASIC__LEFT_VALUE_EXPR, PredicateBasic.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR, PredicateLike.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR, PredicateLike.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_IS_NULL__VALUE_EXPR, PredicateIsNull.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST, PredicateInValueList.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR, PredicateInValueList.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST, PredicateInValueRowSelect.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_IN_VALUE_SELECT__VALUE_EXPR, PredicateInValueSelect.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST, PredicateQuantifiedRowSelect.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR, PredicateQuantifiedValueSelect.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR, PredicateBetween.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1, PredicateBetween.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2, PredicateBetween.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR, ValueExpressionCast.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST, ValueExpressionFunction.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR, ValueExpressionCombined.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR, ValueExpressionCombined.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR:
					return eContainer.eInverseRemove(this, SQLQueryPackage.GROUPING_EXPRESSION__VALUE_EXPR, GroupingExpression.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR, ValueExpressionCaseElse.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR, ValueExpressionCaseSimple.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR, ValueExpressionCaseSimpleContent.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR, ValueExpressionCaseSimpleContent.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR, ValueExpressionCaseSearchContent.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR, PredicateLike.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR, ValueExpressionLabeledDuration.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__NEST:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR, ValueExpressionNested.class, msgs);
				case SQLQueryPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST:
					return eContainer.eInverseRemove(this, SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST, UpdateSourceExprList.class, msgs);
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
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__NAME:
				return getName();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LABEL:
				return getLabel();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR:
				return getUnaryOperator();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE:
				return getDataType();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW:
				return getValuesRow();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR:
				return getOrderByValueExpr();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN:
				return getResultColumn();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT:
				return getBasicRight();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT:
				return getBasicLeft();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN:
				return getLikePattern();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING:
				return getLikeMatching();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL:
				return getPredicateNull();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT:
				return getInValueListRight();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT:
				return getInValueListLeft();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT:
				return getInValueRowSelectLeft();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT:
				return getInValueSelectLeft();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT:
				return getQuantifiedRowSelectLeft();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT:
				return getQuantifiedValueSelectLeft();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT:
				return getBetweenLeft();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1:
				return getBetweenRight1();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2:
				return getBetweenRight2();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST:
				return getValueExprCast();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION:
				return getValueExprFunction();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT:
				return getValueExprCombinedLeft();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT:
				return getValueExprCombinedRight();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR:
				return getGroupingExpr();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE:
				return getValueExprCaseElse();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE:
				return getValueExprCaseSimple();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				return getValueExprCaseSimpleContentWhen();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				return getValueExprCaseSimpleContentResult();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE:
				return getLikeEscape();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION:
				return getValueExprLabeledDuration();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__NEST:
				return getNest();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST:
				return getUpdateSourceExprList();
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
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR:
				setUnaryOperator((ValueExpressionUnaryOperator)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE:
				setDataType((DataType)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW:
				setValuesRow((ValuesRow)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR:
				setOrderByValueExpr((OrderByValueExpression)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN:
				setResultColumn((ResultColumn)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT:
				setBasicRight((PredicateBasic)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT:
				setBasicLeft((PredicateBasic)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN:
				setLikePattern((PredicateLike)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING:
				setLikeMatching((PredicateLike)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL:
				setPredicateNull((PredicateIsNull)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT:
				setInValueListRight((PredicateInValueList)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT:
				setInValueListLeft((PredicateInValueList)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT:
				setInValueRowSelectLeft((PredicateInValueRowSelect)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT:
				setInValueSelectLeft((PredicateInValueSelect)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT:
				setQuantifiedRowSelectLeft((PredicateQuantifiedRowSelect)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT:
				setQuantifiedValueSelectLeft((PredicateQuantifiedValueSelect)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT:
				setBetweenLeft((PredicateBetween)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1:
				setBetweenRight1((PredicateBetween)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2:
				setBetweenRight2((PredicateBetween)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST:
				setValueExprCast((ValueExpressionCast)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION:
				setValueExprFunction((ValueExpressionFunction)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT:
				setValueExprCombinedLeft((ValueExpressionCombined)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT:
				setValueExprCombinedRight((ValueExpressionCombined)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR:
				setGroupingExpr((GroupingExpression)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE:
				setValueExprCaseElse((ValueExpressionCaseElse)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE:
				setValueExprCaseSimple((ValueExpressionCaseSimple)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				setValueExprCaseSimpleContentWhen((ValueExpressionCaseSimpleContent)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				setValueExprCaseSimpleContentResult((ValueExpressionCaseSimpleContent)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE:
				setLikeEscape((PredicateLike)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION:
				setValueExprLabeledDuration((ValueExpressionLabeledDuration)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__NEST:
				setNest((ValueExpressionNested)newValue);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST:
				setUpdateSourceExprList((UpdateSourceExprList)newValue);
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
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR:
				setUnaryOperator(UNARY_OPERATOR_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE:
				setDataType((DataType)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW:
				setValuesRow((ValuesRow)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR:
				setOrderByValueExpr((OrderByValueExpression)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN:
				setResultColumn((ResultColumn)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT:
				setBasicRight((PredicateBasic)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT:
				setBasicLeft((PredicateBasic)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN:
				setLikePattern((PredicateLike)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING:
				setLikeMatching((PredicateLike)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL:
				setPredicateNull((PredicateIsNull)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT:
				setInValueListRight((PredicateInValueList)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT:
				setInValueListLeft((PredicateInValueList)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT:
				setInValueRowSelectLeft((PredicateInValueRowSelect)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT:
				setInValueSelectLeft((PredicateInValueSelect)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT:
				setQuantifiedRowSelectLeft((PredicateQuantifiedRowSelect)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT:
				setQuantifiedValueSelectLeft((PredicateQuantifiedValueSelect)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT:
				setBetweenLeft((PredicateBetween)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1:
				setBetweenRight1((PredicateBetween)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2:
				setBetweenRight2((PredicateBetween)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST:
				setValueExprCast((ValueExpressionCast)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION:
				setValueExprFunction((ValueExpressionFunction)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT:
				setValueExprCombinedLeft((ValueExpressionCombined)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT:
				setValueExprCombinedRight((ValueExpressionCombined)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR:
				setGroupingExpr((GroupingExpression)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE:
				setValueExprCaseElse((ValueExpressionCaseElse)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE:
				setValueExprCaseSimple((ValueExpressionCaseSimple)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				setValueExprCaseSimpleContentWhen((ValueExpressionCaseSimpleContent)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				setValueExprCaseSimpleContentResult((ValueExpressionCaseSimpleContent)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE:
				setLikeEscape((PredicateLike)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION:
				setValueExprLabeledDuration((ValueExpressionLabeledDuration)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__NEST:
				setNest((ValueExpressionNested)null);
				return;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST:
				setUpdateSourceExprList((UpdateSourceExprList)null);
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
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR:
				return unaryOperator != UNARY_OPERATOR_EDEFAULT;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE:
				return dataType != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW:
				return getValuesRow() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR:
				return getOrderByValueExpr() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN:
				return getResultColumn() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT:
				return getBasicRight() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT:
				return getBasicLeft() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN:
				return getLikePattern() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING:
				return getLikeMatching() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL:
				return getPredicateNull() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT:
				return getInValueListRight() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT:
				return getInValueListLeft() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT:
				return getInValueRowSelectLeft() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT:
				return getInValueSelectLeft() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT:
				return getQuantifiedRowSelectLeft() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT:
				return getQuantifiedValueSelectLeft() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT:
				return getBetweenLeft() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1:
				return getBetweenRight1() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2:
				return getBetweenRight2() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST:
				return getValueExprCast() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION:
				return getValueExprFunction() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT:
				return getValueExprCombinedLeft() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT:
				return getValueExprCombinedRight() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR:
				return getGroupingExpr() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE:
				return getValueExprCaseElse() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE:
				return getValueExprCaseSimple() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				return getValueExprCaseSimpleContentWhen() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				return getValueExprCaseSimpleContentResult() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE:
				return getLikeEscape() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION:
				return getValueExprLabeledDuration() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__NEST:
				return getNest() != null;
			case SQLQueryPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST:
				return getUpdateSourceExprList() != null;
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
		result.append(" (unaryOperator: ");
		result.append(unaryOperator);
		result.append(')');
		return result.toString();
	}

} //SQLValueExpressionImpl
