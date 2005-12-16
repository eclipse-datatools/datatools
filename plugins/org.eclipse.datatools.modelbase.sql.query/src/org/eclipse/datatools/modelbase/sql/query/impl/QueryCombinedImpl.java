/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryCombinedImpl.java,v 1.10 2005/10/22 01:35:22 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateExists;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Combined</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryCombinedImpl#getCombinedOperator <em>Combined Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryCombinedImpl#getLeftQuery <em>Left Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryCombinedImpl#getRightQuery <em>Right Query</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryCombinedImpl extends QueryExpressionBodyImpl implements QueryCombined {
	/**
	 * The default value of the '{@link #getCombinedOperator() <em>Combined Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCombinedOperator()
	 * @generated
	 * @ordered
	 */
    protected static final QueryCombinedOperator COMBINED_OPERATOR_EDEFAULT = QueryCombinedOperator.UNION_LITERAL;

	/**
	 * The cached value of the '{@link #getCombinedOperator() <em>Combined Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCombinedOperator()
	 * @generated
	 * @ordered
	 */
    protected QueryCombinedOperator combinedOperator = COMBINED_OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLeftQuery() <em>Left Query</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLeftQuery()
	 * @generated
	 * @ordered
	 */
    protected QueryExpressionBody leftQuery = null;

	/**
	 * The cached value of the '{@link #getRightQuery() <em>Right Query</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRightQuery()
	 * @generated
	 * @ordered
	 */
    protected QueryExpressionBody rightQuery = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected QueryCombinedImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getQueryCombined();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryCombinedOperator getCombinedOperator() {
		return combinedOperator;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCombinedOperator(QueryCombinedOperator newCombinedOperator) {
		QueryCombinedOperator oldCombinedOperator = combinedOperator;
		combinedOperator = newCombinedOperator == null ? COMBINED_OPERATOR_EDEFAULT : newCombinedOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_COMBINED__COMBINED_OPERATOR, oldCombinedOperator, combinedOperator));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryExpressionBody getLeftQuery() {
		return leftQuery;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetLeftQuery(QueryExpressionBody newLeftQuery, NotificationChain msgs) {
		QueryExpressionBody oldLeftQuery = leftQuery;
		leftQuery = newLeftQuery;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_COMBINED__LEFT_QUERY, oldLeftQuery, newLeftQuery);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLeftQuery(QueryExpressionBody newLeftQuery) {
		if (newLeftQuery != leftQuery) {
			NotificationChain msgs = null;
			if (leftQuery != null)
				msgs = ((InternalEObject)leftQuery).eInverseRemove(this, SQLQueryPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT, QueryExpressionBody.class, msgs);
			if (newLeftQuery != null)
				msgs = ((InternalEObject)newLeftQuery).eInverseAdd(this, SQLQueryPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT, QueryExpressionBody.class, msgs);
			msgs = basicSetLeftQuery(newLeftQuery, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_COMBINED__LEFT_QUERY, newLeftQuery, newLeftQuery));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryExpressionBody getRightQuery() {
		return rightQuery;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetRightQuery(QueryExpressionBody newRightQuery, NotificationChain msgs) {
		QueryExpressionBody oldRightQuery = rightQuery;
		rightQuery = newRightQuery;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_COMBINED__RIGHT_QUERY, oldRightQuery, newRightQuery);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setRightQuery(QueryExpressionBody newRightQuery) {
		if (newRightQuery != rightQuery) {
			NotificationChain msgs = null;
			if (rightQuery != null)
				msgs = ((InternalEObject)rightQuery).eInverseRemove(this, SQLQueryPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT, QueryExpressionBody.class, msgs);
			if (newRightQuery != null)
				msgs = ((InternalEObject)newRightQuery).eInverseAdd(this, SQLQueryPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT, QueryExpressionBody.class, msgs);
			msgs = basicSetRightQuery(newRightQuery, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_COMBINED__RIGHT_QUERY, newRightQuery, newRightQuery));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.QUERY_COMBINED__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_LEFT, msgs);
				case SQLQueryPackage.QUERY_COMBINED__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_COMBINED__QUERY_SELECT, msgs);
				case SQLQueryPackage.QUERY_COMBINED__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_COMBINED__NEST, msgs);
				case SQLQueryPackage.QUERY_COMBINED__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_COMBINED__TABLE_CORRELATION:
					if (tableCorrelation != null)
						msgs = ((InternalEObject)tableCorrelation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.QUERY_COMBINED__TABLE_CORRELATION, null, msgs);
					return basicSetTableCorrelation((TableCorrelation)otherEnd, msgs);
				case SQLQueryPackage.QUERY_COMBINED__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_COMBINED__VALUE_EXPR_COLUMNS:
					return ((InternalEList)getValueExprColumns()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_COMBINED__QUERY_EXPRESSION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_COMBINED__QUERY_EXPRESSION, msgs);
				case SQLQueryPackage.QUERY_COMBINED__COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_COMBINED__COMBINED_LEFT, msgs);
				case SQLQueryPackage.QUERY_COMBINED__COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_COMBINED__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.QUERY_COMBINED__PREDICATE_EXISTS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_COMBINED__PREDICATE_EXISTS, msgs);
				case SQLQueryPackage.QUERY_COMBINED__UPDATE_SOURCE_QUERY:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_COMBINED__UPDATE_SOURCE_QUERY, msgs);
				case SQLQueryPackage.QUERY_COMBINED__WITH_TABLE_SPECIFICATION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_COMBINED__WITH_TABLE_SPECIFICATION, msgs);
				case SQLQueryPackage.QUERY_COMBINED__LEFT_QUERY:
					if (leftQuery != null)
						msgs = ((InternalEObject)leftQuery).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.QUERY_COMBINED__LEFT_QUERY, null, msgs);
					return basicSetLeftQuery((QueryExpressionBody)otherEnd, msgs);
				case SQLQueryPackage.QUERY_COMBINED__RIGHT_QUERY:
					if (rightQuery != null)
						msgs = ((InternalEObject)rightQuery).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.QUERY_COMBINED__RIGHT_QUERY, null, msgs);
					return basicSetRightQuery((QueryExpressionBody)otherEnd, msgs);
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
				case SQLQueryPackage.QUERY_COMBINED__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_COMBINED__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_LEFT, msgs);
				case SQLQueryPackage.QUERY_COMBINED__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_COMBINED__QUERY_SELECT, msgs);
				case SQLQueryPackage.QUERY_COMBINED__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_COMBINED__NEST, msgs);
				case SQLQueryPackage.QUERY_COMBINED__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_COMBINED__TABLE_CORRELATION:
					return basicSetTableCorrelation(null, msgs);
				case SQLQueryPackage.QUERY_COMBINED__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_COMBINED__VALUE_EXPR_COLUMNS:
					return ((InternalEList)getValueExprColumns()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_COMBINED__QUERY_EXPRESSION:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_COMBINED__QUERY_EXPRESSION, msgs);
				case SQLQueryPackage.QUERY_COMBINED__COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_COMBINED__COMBINED_LEFT, msgs);
				case SQLQueryPackage.QUERY_COMBINED__COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_COMBINED__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.QUERY_COMBINED__PREDICATE_EXISTS:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_COMBINED__PREDICATE_EXISTS, msgs);
				case SQLQueryPackage.QUERY_COMBINED__UPDATE_SOURCE_QUERY:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_COMBINED__UPDATE_SOURCE_QUERY, msgs);
				case SQLQueryPackage.QUERY_COMBINED__WITH_TABLE_SPECIFICATION:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_COMBINED__WITH_TABLE_SPECIFICATION, msgs);
				case SQLQueryPackage.QUERY_COMBINED__LEFT_QUERY:
					return basicSetLeftQuery(null, msgs);
				case SQLQueryPackage.QUERY_COMBINED__RIGHT_QUERY:
					return basicSetRightQuery(null, msgs);
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
			case SQLQueryPackage.QUERY_COMBINED__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.QUERY_COMBINED__NAME:
				return getName();
			case SQLQueryPackage.QUERY_COMBINED__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.QUERY_COMBINED__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.QUERY_COMBINED__LABEL:
				return getLabel();
			case SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_RIGHT:
				return getTableJoinedRight();
			case SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_LEFT:
				return getTableJoinedLeft();
			case SQLQueryPackage.QUERY_COMBINED__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryPackage.QUERY_COMBINED__NEST:
				return getNest();
			case SQLQueryPackage.QUERY_COMBINED__COLUMN_LIST:
				return getColumnList();
			case SQLQueryPackage.QUERY_COMBINED__TABLE_CORRELATION:
				return getTableCorrelation();
			case SQLQueryPackage.QUERY_COMBINED__RESULT_TABLE_ALL_COLUMNS:
				return getResultTableAllColumns();
			case SQLQueryPackage.QUERY_COMBINED__VALUE_EXPR_COLUMNS:
				return getValueExprColumns();
			case SQLQueryPackage.QUERY_COMBINED__QUERY_EXPRESSION:
				return getQueryExpression();
			case SQLQueryPackage.QUERY_COMBINED__COMBINED_LEFT:
				return getCombinedLeft();
			case SQLQueryPackage.QUERY_COMBINED__COMBINED_RIGHT:
				return getCombinedRight();
			case SQLQueryPackage.QUERY_COMBINED__PREDICATE_EXISTS:
				return getPredicateExists();
			case SQLQueryPackage.QUERY_COMBINED__UPDATE_SOURCE_QUERY:
				return getUpdateSourceQuery();
			case SQLQueryPackage.QUERY_COMBINED__WITH_TABLE_SPECIFICATION:
				return getWithTableSpecification();
			case SQLQueryPackage.QUERY_COMBINED__COMBINED_OPERATOR:
				return getCombinedOperator();
			case SQLQueryPackage.QUERY_COMBINED__LEFT_QUERY:
				return getLeftQuery();
			case SQLQueryPackage.QUERY_COMBINED__RIGHT_QUERY:
				return getRightQuery();
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
			case SQLQueryPackage.QUERY_COMBINED__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__NEST:
				setNest((TableNested)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__COLUMN_LIST:
				getColumnList().clear();
				getColumnList().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				getResultTableAllColumns().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__VALUE_EXPR_COLUMNS:
				getValueExprColumns().clear();
				getValueExprColumns().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__QUERY_EXPRESSION:
				setQueryExpression((QueryExpressionRoot)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__COMBINED_LEFT:
				setCombinedLeft((QueryCombined)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__COMBINED_RIGHT:
				setCombinedRight((QueryCombined)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__PREDICATE_EXISTS:
				setPredicateExists((PredicateExists)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__UPDATE_SOURCE_QUERY:
				setUpdateSourceQuery((UpdateSourceQuery)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__WITH_TABLE_SPECIFICATION:
				setWithTableSpecification((WithTableSpecification)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__COMBINED_OPERATOR:
				setCombinedOperator((QueryCombinedOperator)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__LEFT_QUERY:
				setLeftQuery((QueryExpressionBody)newValue);
				return;
			case SQLQueryPackage.QUERY_COMBINED__RIGHT_QUERY:
				setRightQuery((QueryExpressionBody)newValue);
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
			case SQLQueryPackage.QUERY_COMBINED__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.QUERY_COMBINED__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_COMBINED__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.QUERY_COMBINED__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_COMBINED__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)null);
				return;
			case SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)null);
				return;
			case SQLQueryPackage.QUERY_COMBINED__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryPackage.QUERY_COMBINED__NEST:
				setNest((TableNested)null);
				return;
			case SQLQueryPackage.QUERY_COMBINED__COLUMN_LIST:
				getColumnList().clear();
				return;
			case SQLQueryPackage.QUERY_COMBINED__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)null);
				return;
			case SQLQueryPackage.QUERY_COMBINED__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				return;
			case SQLQueryPackage.QUERY_COMBINED__VALUE_EXPR_COLUMNS:
				getValueExprColumns().clear();
				return;
			case SQLQueryPackage.QUERY_COMBINED__QUERY_EXPRESSION:
				setQueryExpression((QueryExpressionRoot)null);
				return;
			case SQLQueryPackage.QUERY_COMBINED__COMBINED_LEFT:
				setCombinedLeft((QueryCombined)null);
				return;
			case SQLQueryPackage.QUERY_COMBINED__COMBINED_RIGHT:
				setCombinedRight((QueryCombined)null);
				return;
			case SQLQueryPackage.QUERY_COMBINED__PREDICATE_EXISTS:
				setPredicateExists((PredicateExists)null);
				return;
			case SQLQueryPackage.QUERY_COMBINED__UPDATE_SOURCE_QUERY:
				setUpdateSourceQuery((UpdateSourceQuery)null);
				return;
			case SQLQueryPackage.QUERY_COMBINED__WITH_TABLE_SPECIFICATION:
				setWithTableSpecification((WithTableSpecification)null);
				return;
			case SQLQueryPackage.QUERY_COMBINED__COMBINED_OPERATOR:
				setCombinedOperator(COMBINED_OPERATOR_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_COMBINED__LEFT_QUERY:
				setLeftQuery((QueryExpressionBody)null);
				return;
			case SQLQueryPackage.QUERY_COMBINED__RIGHT_QUERY:
				setRightQuery((QueryExpressionBody)null);
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
			case SQLQueryPackage.QUERY_COMBINED__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.QUERY_COMBINED__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.QUERY_COMBINED__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.QUERY_COMBINED__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.QUERY_COMBINED__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_RIGHT:
				return getTableJoinedRight() != null;
			case SQLQueryPackage.QUERY_COMBINED__TABLE_JOINED_LEFT:
				return getTableJoinedLeft() != null;
			case SQLQueryPackage.QUERY_COMBINED__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryPackage.QUERY_COMBINED__NEST:
				return getNest() != null;
			case SQLQueryPackage.QUERY_COMBINED__COLUMN_LIST:
				return columnList != null && !columnList.isEmpty();
			case SQLQueryPackage.QUERY_COMBINED__TABLE_CORRELATION:
				return tableCorrelation != null;
			case SQLQueryPackage.QUERY_COMBINED__RESULT_TABLE_ALL_COLUMNS:
				return resultTableAllColumns != null && !resultTableAllColumns.isEmpty();
			case SQLQueryPackage.QUERY_COMBINED__VALUE_EXPR_COLUMNS:
				return valueExprColumns != null && !valueExprColumns.isEmpty();
			case SQLQueryPackage.QUERY_COMBINED__QUERY_EXPRESSION:
				return getQueryExpression() != null;
			case SQLQueryPackage.QUERY_COMBINED__COMBINED_LEFT:
				return getCombinedLeft() != null;
			case SQLQueryPackage.QUERY_COMBINED__COMBINED_RIGHT:
				return getCombinedRight() != null;
			case SQLQueryPackage.QUERY_COMBINED__PREDICATE_EXISTS:
				return getPredicateExists() != null;
			case SQLQueryPackage.QUERY_COMBINED__UPDATE_SOURCE_QUERY:
				return getUpdateSourceQuery() != null;
			case SQLQueryPackage.QUERY_COMBINED__WITH_TABLE_SPECIFICATION:
				return getWithTableSpecification() != null;
			case SQLQueryPackage.QUERY_COMBINED__COMBINED_OPERATOR:
				return combinedOperator != COMBINED_OPERATOR_EDEFAULT;
			case SQLQueryPackage.QUERY_COMBINED__LEFT_QUERY:
				return leftQuery != null;
			case SQLQueryPackage.QUERY_COMBINED__RIGHT_QUERY:
				return rightQuery != null;
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

} //SQLQueryCombinedImpl
