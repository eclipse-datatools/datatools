/**
 * <copyright>
 * </copyright>
 *
 * $Id: QuerySelectImpl.java,v 1.10 2005/10/22 01:35:23 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.GroupingSpecification;
import org.eclipse.datatools.modelbase.sql.query.PredicateExists;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
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
 * An implementation of the model object '<em><b>Select</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectImpl#isDistinct <em>Distinct</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectImpl#getHavingClause <em>Having Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectImpl#getWhereClause <em>Where Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectImpl#getGroupByClause <em>Group By Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectImpl#getSelectClause <em>Select Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectImpl#getFromClause <em>From Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectImpl#getIntoClause <em>Into Clause</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QuerySelectImpl extends QueryExpressionBodyImpl implements QuerySelect {
	/**
	 * The default value of the '{@link #isDistinct() <em>Distinct</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isDistinct()
	 * @generated
	 * @ordered
	 */
    protected static final boolean DISTINCT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDistinct() <em>Distinct</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isDistinct()
	 * @generated
	 * @ordered
	 */
    protected boolean distinct = DISTINCT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHavingClause() <em>Having Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getHavingClause()
	 * @generated
	 * @ordered
	 */
    protected QuerySearchCondition havingClause = null;

	/**
	 * This is true if the Having Clause containment reference has been set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    protected boolean havingClauseESet = false;

	/**
	 * The cached value of the '{@link #getWhereClause() <em>Where Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getWhereClause()
	 * @generated
	 * @ordered
	 */
    protected QuerySearchCondition whereClause = null;

	/**
	 * This is true if the Where Clause containment reference has been set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    protected boolean whereClauseESet = false;

	/**
	 * The cached value of the '{@link #getGroupByClause() <em>Group By Clause</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGroupByClause()
	 * @generated
	 * @ordered
	 */
    protected EList groupByClause = null;

	/**
	 * The cached value of the '{@link #getSelectClause() <em>Select Clause</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSelectClause()
	 * @generated
	 * @ordered
	 */
    protected EList selectClause = null;

	/**
	 * The cached value of the '{@link #getFromClause() <em>From Clause</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFromClause()
	 * @generated
	 * @ordered
	 */
    protected EList fromClause = null;

	/**
	 * The cached value of the '{@link #getIntoClause() <em>Into Clause</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getIntoClause()
	 * @generated
	 * @ordered
	 */
    protected EList intoClause = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected QuerySelectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getQuerySelect();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isDistinct() {
		return distinct;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDistinct(boolean newDistinct) {
		boolean oldDistinct = distinct;
		distinct = newDistinct;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SELECT__DISTINCT, oldDistinct, distinct));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QuerySearchCondition getHavingClause() {
		return havingClause;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetHavingClause(QuerySearchCondition newHavingClause, NotificationChain msgs) {
		QuerySearchCondition oldHavingClause = havingClause;
		havingClause = newHavingClause;
		boolean oldHavingClauseESet = havingClauseESet;
		havingClauseESet = true;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE, oldHavingClause, newHavingClause, !oldHavingClauseESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setHavingClause(QuerySearchCondition newHavingClause) {
		if (newHavingClause != havingClause) {
			NotificationChain msgs = null;
			if (havingClause != null)
				msgs = ((InternalEObject)havingClause).eInverseRemove(this, SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING, QuerySearchCondition.class, msgs);
			if (newHavingClause != null)
				msgs = ((InternalEObject)newHavingClause).eInverseAdd(this, SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING, QuerySearchCondition.class, msgs);
			msgs = basicSetHavingClause(newHavingClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldHavingClauseESet = havingClauseESet;
			havingClauseESet = true;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE, newHavingClause, newHavingClause, !oldHavingClauseESet));
    	}
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicUnsetHavingClause(NotificationChain msgs) {
		QuerySearchCondition oldHavingClause = havingClause;
		havingClause = null;
		boolean oldHavingClauseESet = havingClauseESet;
		havingClauseESet = false;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE, oldHavingClause, null, oldHavingClauseESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void unsetHavingClause() {
		if (havingClause != null) {
			NotificationChain msgs = null;
			msgs = ((InternalEObject)havingClause).eInverseRemove(this, SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING, QuerySearchCondition.class, msgs);
			msgs = basicUnsetHavingClause(msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldHavingClauseESet = havingClauseESet;
			havingClauseESet = false;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.UNSET, SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE, null, null, oldHavingClauseESet));
    	}
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSetHavingClause() {
		return havingClauseESet;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QuerySearchCondition getWhereClause() {
		return whereClause;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetWhereClause(QuerySearchCondition newWhereClause, NotificationChain msgs) {
		QuerySearchCondition oldWhereClause = whereClause;
		whereClause = newWhereClause;
		boolean oldWhereClauseESet = whereClauseESet;
		whereClauseESet = true;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE, oldWhereClause, newWhereClause, !oldWhereClauseESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setWhereClause(QuerySearchCondition newWhereClause) {
		if (newWhereClause != whereClause) {
			NotificationChain msgs = null;
			if (whereClause != null)
				msgs = ((InternalEObject)whereClause).eInverseRemove(this, SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE, QuerySearchCondition.class, msgs);
			if (newWhereClause != null)
				msgs = ((InternalEObject)newWhereClause).eInverseAdd(this, SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE, QuerySearchCondition.class, msgs);
			msgs = basicSetWhereClause(newWhereClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldWhereClauseESet = whereClauseESet;
			whereClauseESet = true;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE, newWhereClause, newWhereClause, !oldWhereClauseESet));
    	}
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicUnsetWhereClause(NotificationChain msgs) {
		QuerySearchCondition oldWhereClause = whereClause;
		whereClause = null;
		boolean oldWhereClauseESet = whereClauseESet;
		whereClauseESet = false;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE, oldWhereClause, null, oldWhereClauseESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void unsetWhereClause() {
		if (whereClause != null) {
			NotificationChain msgs = null;
			msgs = ((InternalEObject)whereClause).eInverseRemove(this, SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE, QuerySearchCondition.class, msgs);
			msgs = basicUnsetWhereClause(msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldWhereClauseESet = whereClauseESet;
			whereClauseESet = false;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.UNSET, SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE, null, null, oldWhereClauseESet));
    	}
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSetWhereClause() {
		return whereClauseESet;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getGroupByClause() {
		if (groupByClause == null) {
			groupByClause = new EObjectContainmentWithInverseEList(GroupingSpecification.class, this, SQLQueryPackage.QUERY_SELECT__GROUP_BY_CLAUSE, SQLQueryPackage.GROUPING_SPECIFICATION__QUERY_SELECT);
		}
		return groupByClause;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getSelectClause() {
		if (selectClause == null) {
			selectClause = new EObjectContainmentWithInverseEList(QueryResultSpecification.class, this, SQLQueryPackage.QUERY_SELECT__SELECT_CLAUSE, SQLQueryPackage.QUERY_RESULT_SPECIFICATION__QUERY_SELECT);
		}
		return selectClause;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getFromClause() {
		if (fromClause == null) {
			fromClause = new EObjectContainmentWithInverseEList(TableReference.class, this, SQLQueryPackage.QUERY_SELECT__FROM_CLAUSE, SQLQueryPackage.TABLE_REFERENCE__QUERY_SELECT);
		}
		return fromClause;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getIntoClause() {
		if (intoClause == null) {
			intoClause = new EObjectContainmentWithInverseEList(ValueExpressionVariable.class, this, SQLQueryPackage.QUERY_SELECT__INTO_CLAUSE, SQLQueryPackage.VALUE_EXPRESSION_VARIABLE__QUERY_SELECT);
		}
		return intoClause;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.QUERY_SELECT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_LEFT, msgs);
				case SQLQueryPackage.QUERY_SELECT__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SELECT__QUERY_SELECT, msgs);
				case SQLQueryPackage.QUERY_SELECT__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SELECT__NEST, msgs);
				case SQLQueryPackage.QUERY_SELECT__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__TABLE_CORRELATION:
					if (tableCorrelation != null)
						msgs = ((InternalEObject)tableCorrelation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.QUERY_SELECT__TABLE_CORRELATION, null, msgs);
					return basicSetTableCorrelation((TableCorrelation)otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__VALUE_EXPR_COLUMNS:
					return ((InternalEList)getValueExprColumns()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__QUERY_EXPRESSION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SELECT__QUERY_EXPRESSION, msgs);
				case SQLQueryPackage.QUERY_SELECT__COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SELECT__COMBINED_LEFT, msgs);
				case SQLQueryPackage.QUERY_SELECT__COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SELECT__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.QUERY_SELECT__PREDICATE_EXISTS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SELECT__PREDICATE_EXISTS, msgs);
				case SQLQueryPackage.QUERY_SELECT__UPDATE_SOURCE_QUERY:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SELECT__UPDATE_SOURCE_QUERY, msgs);
				case SQLQueryPackage.QUERY_SELECT__WITH_TABLE_SPECIFICATION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SELECT__WITH_TABLE_SPECIFICATION, msgs);
				case SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE:
					if (havingClause != null)
						msgs = ((InternalEObject)havingClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE, null, msgs);
					return basicSetHavingClause((QuerySearchCondition)otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE:
					if (whereClause != null)
						msgs = ((InternalEObject)whereClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE, null, msgs);
					return basicSetWhereClause((QuerySearchCondition)otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__GROUP_BY_CLAUSE:
					return ((InternalEList)getGroupByClause()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__SELECT_CLAUSE:
					return ((InternalEList)getSelectClause()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__FROM_CLAUSE:
					return ((InternalEList)getFromClause()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__INTO_CLAUSE:
					return ((InternalEList)getIntoClause()).basicAdd(otherEnd, msgs);
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
				case SQLQueryPackage.QUERY_SELECT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_LEFT, msgs);
				case SQLQueryPackage.QUERY_SELECT__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SELECT__QUERY_SELECT, msgs);
				case SQLQueryPackage.QUERY_SELECT__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SELECT__NEST, msgs);
				case SQLQueryPackage.QUERY_SELECT__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__TABLE_CORRELATION:
					return basicSetTableCorrelation(null, msgs);
				case SQLQueryPackage.QUERY_SELECT__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__VALUE_EXPR_COLUMNS:
					return ((InternalEList)getValueExprColumns()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__QUERY_EXPRESSION:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SELECT__QUERY_EXPRESSION, msgs);
				case SQLQueryPackage.QUERY_SELECT__COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SELECT__COMBINED_LEFT, msgs);
				case SQLQueryPackage.QUERY_SELECT__COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SELECT__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.QUERY_SELECT__PREDICATE_EXISTS:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SELECT__PREDICATE_EXISTS, msgs);
				case SQLQueryPackage.QUERY_SELECT__UPDATE_SOURCE_QUERY:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SELECT__UPDATE_SOURCE_QUERY, msgs);
				case SQLQueryPackage.QUERY_SELECT__WITH_TABLE_SPECIFICATION:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SELECT__WITH_TABLE_SPECIFICATION, msgs);
				case SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE:
					return basicUnsetHavingClause(msgs);
				case SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE:
					return basicUnsetWhereClause(msgs);
				case SQLQueryPackage.QUERY_SELECT__GROUP_BY_CLAUSE:
					return ((InternalEList)getGroupByClause()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__SELECT_CLAUSE:
					return ((InternalEList)getSelectClause()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__FROM_CLAUSE:
					return ((InternalEList)getFromClause()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT__INTO_CLAUSE:
					return ((InternalEList)getIntoClause()).basicRemove(otherEnd, msgs);
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
			case SQLQueryPackage.QUERY_SELECT__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.QUERY_SELECT__NAME:
				return getName();
			case SQLQueryPackage.QUERY_SELECT__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.QUERY_SELECT__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.QUERY_SELECT__LABEL:
				return getLabel();
			case SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_RIGHT:
				return getTableJoinedRight();
			case SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_LEFT:
				return getTableJoinedLeft();
			case SQLQueryPackage.QUERY_SELECT__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryPackage.QUERY_SELECT__NEST:
				return getNest();
			case SQLQueryPackage.QUERY_SELECT__COLUMN_LIST:
				return getColumnList();
			case SQLQueryPackage.QUERY_SELECT__TABLE_CORRELATION:
				return getTableCorrelation();
			case SQLQueryPackage.QUERY_SELECT__RESULT_TABLE_ALL_COLUMNS:
				return getResultTableAllColumns();
			case SQLQueryPackage.QUERY_SELECT__VALUE_EXPR_COLUMNS:
				return getValueExprColumns();
			case SQLQueryPackage.QUERY_SELECT__QUERY_EXPRESSION:
				return getQueryExpression();
			case SQLQueryPackage.QUERY_SELECT__COMBINED_LEFT:
				return getCombinedLeft();
			case SQLQueryPackage.QUERY_SELECT__COMBINED_RIGHT:
				return getCombinedRight();
			case SQLQueryPackage.QUERY_SELECT__PREDICATE_EXISTS:
				return getPredicateExists();
			case SQLQueryPackage.QUERY_SELECT__UPDATE_SOURCE_QUERY:
				return getUpdateSourceQuery();
			case SQLQueryPackage.QUERY_SELECT__WITH_TABLE_SPECIFICATION:
				return getWithTableSpecification();
			case SQLQueryPackage.QUERY_SELECT__DISTINCT:
				return isDistinct() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE:
				return getHavingClause();
			case SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE:
				return getWhereClause();
			case SQLQueryPackage.QUERY_SELECT__GROUP_BY_CLAUSE:
				return getGroupByClause();
			case SQLQueryPackage.QUERY_SELECT__SELECT_CLAUSE:
				return getSelectClause();
			case SQLQueryPackage.QUERY_SELECT__FROM_CLAUSE:
				return getFromClause();
			case SQLQueryPackage.QUERY_SELECT__INTO_CLAUSE:
				return getIntoClause();
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
			case SQLQueryPackage.QUERY_SELECT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__NEST:
				setNest((TableNested)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__COLUMN_LIST:
				getColumnList().clear();
				getColumnList().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				getResultTableAllColumns().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__VALUE_EXPR_COLUMNS:
				getValueExprColumns().clear();
				getValueExprColumns().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__QUERY_EXPRESSION:
				setQueryExpression((QueryExpressionRoot)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__COMBINED_LEFT:
				setCombinedLeft((QueryCombined)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__COMBINED_RIGHT:
				setCombinedRight((QueryCombined)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__PREDICATE_EXISTS:
				setPredicateExists((PredicateExists)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__UPDATE_SOURCE_QUERY:
				setUpdateSourceQuery((UpdateSourceQuery)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__WITH_TABLE_SPECIFICATION:
				setWithTableSpecification((WithTableSpecification)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__DISTINCT:
				setDistinct(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE:
				setHavingClause((QuerySearchCondition)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE:
				setWhereClause((QuerySearchCondition)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__GROUP_BY_CLAUSE:
				getGroupByClause().clear();
				getGroupByClause().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__SELECT_CLAUSE:
				getSelectClause().clear();
				getSelectClause().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__FROM_CLAUSE:
				getFromClause().clear();
				getFromClause().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT__INTO_CLAUSE:
				getIntoClause().clear();
				getIntoClause().addAll((Collection)newValue);
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
			case SQLQueryPackage.QUERY_SELECT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.QUERY_SELECT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_SELECT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.QUERY_SELECT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_SELECT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)null);
				return;
			case SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)null);
				return;
			case SQLQueryPackage.QUERY_SELECT__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryPackage.QUERY_SELECT__NEST:
				setNest((TableNested)null);
				return;
			case SQLQueryPackage.QUERY_SELECT__COLUMN_LIST:
				getColumnList().clear();
				return;
			case SQLQueryPackage.QUERY_SELECT__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)null);
				return;
			case SQLQueryPackage.QUERY_SELECT__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				return;
			case SQLQueryPackage.QUERY_SELECT__VALUE_EXPR_COLUMNS:
				getValueExprColumns().clear();
				return;
			case SQLQueryPackage.QUERY_SELECT__QUERY_EXPRESSION:
				setQueryExpression((QueryExpressionRoot)null);
				return;
			case SQLQueryPackage.QUERY_SELECT__COMBINED_LEFT:
				setCombinedLeft((QueryCombined)null);
				return;
			case SQLQueryPackage.QUERY_SELECT__COMBINED_RIGHT:
				setCombinedRight((QueryCombined)null);
				return;
			case SQLQueryPackage.QUERY_SELECT__PREDICATE_EXISTS:
				setPredicateExists((PredicateExists)null);
				return;
			case SQLQueryPackage.QUERY_SELECT__UPDATE_SOURCE_QUERY:
				setUpdateSourceQuery((UpdateSourceQuery)null);
				return;
			case SQLQueryPackage.QUERY_SELECT__WITH_TABLE_SPECIFICATION:
				setWithTableSpecification((WithTableSpecification)null);
				return;
			case SQLQueryPackage.QUERY_SELECT__DISTINCT:
				setDistinct(DISTINCT_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE:
				unsetHavingClause();
				return;
			case SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE:
				unsetWhereClause();
				return;
			case SQLQueryPackage.QUERY_SELECT__GROUP_BY_CLAUSE:
				getGroupByClause().clear();
				return;
			case SQLQueryPackage.QUERY_SELECT__SELECT_CLAUSE:
				getSelectClause().clear();
				return;
			case SQLQueryPackage.QUERY_SELECT__FROM_CLAUSE:
				getFromClause().clear();
				return;
			case SQLQueryPackage.QUERY_SELECT__INTO_CLAUSE:
				getIntoClause().clear();
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
			case SQLQueryPackage.QUERY_SELECT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.QUERY_SELECT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.QUERY_SELECT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.QUERY_SELECT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.QUERY_SELECT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_RIGHT:
				return getTableJoinedRight() != null;
			case SQLQueryPackage.QUERY_SELECT__TABLE_JOINED_LEFT:
				return getTableJoinedLeft() != null;
			case SQLQueryPackage.QUERY_SELECT__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryPackage.QUERY_SELECT__NEST:
				return getNest() != null;
			case SQLQueryPackage.QUERY_SELECT__COLUMN_LIST:
				return columnList != null && !columnList.isEmpty();
			case SQLQueryPackage.QUERY_SELECT__TABLE_CORRELATION:
				return tableCorrelation != null;
			case SQLQueryPackage.QUERY_SELECT__RESULT_TABLE_ALL_COLUMNS:
				return resultTableAllColumns != null && !resultTableAllColumns.isEmpty();
			case SQLQueryPackage.QUERY_SELECT__VALUE_EXPR_COLUMNS:
				return valueExprColumns != null && !valueExprColumns.isEmpty();
			case SQLQueryPackage.QUERY_SELECT__QUERY_EXPRESSION:
				return getQueryExpression() != null;
			case SQLQueryPackage.QUERY_SELECT__COMBINED_LEFT:
				return getCombinedLeft() != null;
			case SQLQueryPackage.QUERY_SELECT__COMBINED_RIGHT:
				return getCombinedRight() != null;
			case SQLQueryPackage.QUERY_SELECT__PREDICATE_EXISTS:
				return getPredicateExists() != null;
			case SQLQueryPackage.QUERY_SELECT__UPDATE_SOURCE_QUERY:
				return getUpdateSourceQuery() != null;
			case SQLQueryPackage.QUERY_SELECT__WITH_TABLE_SPECIFICATION:
				return getWithTableSpecification() != null;
			case SQLQueryPackage.QUERY_SELECT__DISTINCT:
				return distinct != DISTINCT_EDEFAULT;
			case SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE:
				return isSetHavingClause();
			case SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE:
				return isSetWhereClause();
			case SQLQueryPackage.QUERY_SELECT__GROUP_BY_CLAUSE:
				return groupByClause != null && !groupByClause.isEmpty();
			case SQLQueryPackage.QUERY_SELECT__SELECT_CLAUSE:
				return selectClause != null && !selectClause.isEmpty();
			case SQLQueryPackage.QUERY_SELECT__FROM_CLAUSE:
				return fromClause != null && !fromClause.isEmpty();
			case SQLQueryPackage.QUERY_SELECT__INTO_CLAUSE:
				return intoClause != null && !intoClause.isEmpty();
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
		result.append(" (distinct: ");
		result.append(distinct);
		result.append(')');
		return result.toString();
	}

} //SQLQuerySelectImpl
