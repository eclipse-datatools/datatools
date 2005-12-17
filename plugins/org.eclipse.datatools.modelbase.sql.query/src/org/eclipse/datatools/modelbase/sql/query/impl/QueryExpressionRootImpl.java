/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryExpressionRootImpl.java,v 1.1 2005/12/16 13:11:11 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getInsertStatement <em>Insert Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getSelectStatement <em>Select Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getWithClause <em>With Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getQuery <em>Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getInValueRowSelectRight <em>In Value Row Select Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getInValueSelectRight <em>In Value Select Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getQuantifiedRowSelectRight <em>Quantified Row Select Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getQuantifiedValueSelectRight <em>Quantified Value Select Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getValueExprScalarSelects <em>Value Expr Scalar Selects</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryExpressionRootImpl extends SQLQueryObjectImpl implements QueryExpressionRoot {
	/**
	 * The cached value of the '{@link #getWithClause() <em>With Clause</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getWithClause()
	 * @generated
	 * @ordered
	 */
    protected EList withClause = null;

	/**
	 * The cached value of the '{@link #getQuery() <em>Query</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getQuery()
	 * @generated
	 * @ordered
	 */
    protected QueryExpressionBody query = null;

	/**
	 * The cached value of the '{@link #getValueExprScalarSelects() <em>Value Expr Scalar Selects</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValueExprScalarSelects()
	 * @generated
	 * @ordered
	 */
    protected EList valueExprScalarSelects = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected QueryExpressionRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getQueryExpressionRoot();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryInsertStatement getInsertStatement() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT) return null;
		return (QueryInsertStatement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setInsertStatement(QueryInsertStatement newInsertStatement) {
		if (newInsertStatement != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT && newInsertStatement != null)) {
			if (EcoreUtil.isAncestor(this, newInsertStatement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInsertStatement != null)
				msgs = ((InternalEObject)newInsertStatement).eInverseAdd(this, SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY, QueryInsertStatement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newInsertStatement, SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT, newInsertStatement, newInsertStatement));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QuerySelectStatement getSelectStatement() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT) return null;
		return (QuerySelectStatement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSelectStatement(QuerySelectStatement newSelectStatement) {
		if (newSelectStatement != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT && newSelectStatement != null)) {
			if (EcoreUtil.isAncestor(this, newSelectStatement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSelectStatement != null)
				msgs = ((InternalEObject)newSelectStatement).eInverseAdd(this, SQLQueryPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR, QuerySelectStatement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newSelectStatement, SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT, newSelectStatement, newSelectStatement));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getWithClause() {
		if (withClause == null) {
			withClause = new EObjectContainmentWithInverseEList(WithTableSpecification.class, this, SQLQueryPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE, SQLQueryPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT);
		}
		return withClause;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryExpressionBody getQuery() {
		return query;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetQuery(QueryExpressionBody newQuery, NotificationChain msgs) {
		QueryExpressionBody oldQuery = query;
		query = newQuery;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUERY, oldQuery, newQuery);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setQuery(QueryExpressionBody newQuery) {
		if (newQuery != query) {
			NotificationChain msgs = null;
			if (query != null)
				msgs = ((InternalEObject)query).eInverseRemove(this, SQLQueryPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION, QueryExpressionBody.class, msgs);
			if (newQuery != null)
				msgs = ((InternalEObject)newQuery).eInverseAdd(this, SQLQueryPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION, QueryExpressionBody.class, msgs);
			msgs = basicSetQuery(newQuery, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUERY, newQuery, newQuery));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateInValueRowSelect getInValueRowSelectRight() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT) return null;
		return (PredicateInValueRowSelect)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setInValueRowSelectRight(PredicateInValueRowSelect newInValueRowSelectRight) {
		if (newInValueRowSelectRight != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT && newInValueRowSelectRight != null)) {
			if (EcoreUtil.isAncestor(this, newInValueRowSelectRight))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInValueRowSelectRight != null)
				msgs = ((InternalEObject)newInValueRowSelectRight).eInverseAdd(this, SQLQueryPackage.PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR, PredicateInValueRowSelect.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newInValueRowSelectRight, SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT, newInValueRowSelectRight, newInValueRowSelectRight));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateInValueSelect getInValueSelectRight() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT) return null;
		return (PredicateInValueSelect)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setInValueSelectRight(PredicateInValueSelect newInValueSelectRight) {
		if (newInValueSelectRight != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT && newInValueSelectRight != null)) {
			if (EcoreUtil.isAncestor(this, newInValueSelectRight))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInValueSelectRight != null)
				msgs = ((InternalEObject)newInValueSelectRight).eInverseAdd(this, SQLQueryPackage.PREDICATE_IN_VALUE_SELECT__QUERY_EXPR, PredicateInValueSelect.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newInValueSelectRight, SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT, newInValueSelectRight, newInValueSelectRight));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateQuantifiedRowSelect getQuantifiedRowSelectRight() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT) return null;
		return (PredicateQuantifiedRowSelect)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setQuantifiedRowSelectRight(PredicateQuantifiedRowSelect newQuantifiedRowSelectRight) {
		if (newQuantifiedRowSelectRight != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT && newQuantifiedRowSelectRight != null)) {
			if (EcoreUtil.isAncestor(this, newQuantifiedRowSelectRight))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQuantifiedRowSelectRight != null)
				msgs = ((InternalEObject)newQuantifiedRowSelectRight).eInverseAdd(this, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR, PredicateQuantifiedRowSelect.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newQuantifiedRowSelectRight, SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT, newQuantifiedRowSelectRight, newQuantifiedRowSelectRight));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateQuantifiedValueSelect getQuantifiedValueSelectRight() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT) return null;
		return (PredicateQuantifiedValueSelect)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setQuantifiedValueSelectRight(PredicateQuantifiedValueSelect newQuantifiedValueSelectRight) {
		if (newQuantifiedValueSelectRight != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT && newQuantifiedValueSelectRight != null)) {
			if (EcoreUtil.isAncestor(this, newQuantifiedValueSelectRight))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQuantifiedValueSelectRight != null)
				msgs = ((InternalEObject)newQuantifiedValueSelectRight).eInverseAdd(this, SQLQueryPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR, PredicateQuantifiedValueSelect.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newQuantifiedValueSelectRight, SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT, newQuantifiedValueSelectRight, newQuantifiedValueSelectRight));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getValueExprScalarSelects() {
		if (valueExprScalarSelects == null) {
			valueExprScalarSelects = new EObjectWithInverseResolvingEList(ValueExpressionScalarSelect.class, this, SQLQueryPackage.QUERY_EXPRESSION_ROOT__VALUE_EXPR_SCALAR_SELECTS, SQLQueryPackage.VALUE_EXPRESSION_SCALAR_SELECT__QUERY_EXPR);
		}
		return valueExprScalarSelects;
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE:
					return ((InternalEList)getWithClause()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUERY:
					if (query != null)
						msgs = ((InternalEObject)query).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUERY, null, msgs);
					return basicSetQuery((QueryExpressionBody)otherEnd, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__VALUE_EXPR_SCALAR_SELECTS:
					return ((InternalEList)getValueExprScalarSelects()).basicAdd(otherEnd, msgs);
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
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE:
					return ((InternalEList)getWithClause()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUERY:
					return basicSetQuery(null, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__VALUE_EXPR_SCALAR_SELECTS:
					return ((InternalEList)getValueExprScalarSelects()).basicRemove(otherEnd, msgs);
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
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY, QueryInsertStatement.class, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR, QuerySelectStatement.class, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR, PredicateInValueRowSelect.class, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_IN_VALUE_SELECT__QUERY_EXPR, PredicateInValueSelect.class, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR, PredicateQuantifiedRowSelect.class, msgs);
				case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR, PredicateQuantifiedValueSelect.class, msgs);
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
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__NAME:
				return getName();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__LABEL:
				return getLabel();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT:
				return getInsertStatement();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT:
				return getSelectStatement();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE:
				return getWithClause();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUERY:
				return getQuery();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT:
				return getInValueRowSelectRight();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT:
				return getInValueSelectRight();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT:
				return getQuantifiedRowSelectRight();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT:
				return getQuantifiedValueSelectRight();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__VALUE_EXPR_SCALAR_SELECTS:
				return getValueExprScalarSelects();
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
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT:
				setInsertStatement((QueryInsertStatement)newValue);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT:
				setSelectStatement((QuerySelectStatement)newValue);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE:
				getWithClause().clear();
				getWithClause().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUERY:
				setQuery((QueryExpressionBody)newValue);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT:
				setInValueRowSelectRight((PredicateInValueRowSelect)newValue);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT:
				setInValueSelectRight((PredicateInValueSelect)newValue);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT:
				setQuantifiedRowSelectRight((PredicateQuantifiedRowSelect)newValue);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT:
				setQuantifiedValueSelectRight((PredicateQuantifiedValueSelect)newValue);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__VALUE_EXPR_SCALAR_SELECTS:
				getValueExprScalarSelects().clear();
				getValueExprScalarSelects().addAll((Collection)newValue);
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
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT:
				setInsertStatement((QueryInsertStatement)null);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT:
				setSelectStatement((QuerySelectStatement)null);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE:
				getWithClause().clear();
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUERY:
				setQuery((QueryExpressionBody)null);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT:
				setInValueRowSelectRight((PredicateInValueRowSelect)null);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT:
				setInValueSelectRight((PredicateInValueSelect)null);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT:
				setQuantifiedRowSelectRight((PredicateQuantifiedRowSelect)null);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT:
				setQuantifiedValueSelectRight((PredicateQuantifiedValueSelect)null);
				return;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__VALUE_EXPR_SCALAR_SELECTS:
				getValueExprScalarSelects().clear();
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
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT:
				return getInsertStatement() != null;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT:
				return getSelectStatement() != null;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE:
				return withClause != null && !withClause.isEmpty();
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUERY:
				return query != null;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT:
				return getInValueRowSelectRight() != null;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT:
				return getInValueSelectRight() != null;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT:
				return getQuantifiedRowSelectRight() != null;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT:
				return getQuantifiedValueSelectRight() != null;
			case SQLQueryPackage.QUERY_EXPRESSION_ROOT__VALUE_EXPR_SCALAR_SELECTS:
				return valueExprScalarSelects != null && !valueExprScalarSelects.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLQueryExpressionImpl
