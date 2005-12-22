/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryExpressionBodyImpl.java,v 1.2 2005/12/17 01:46:20 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateExists;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression Body</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getQueryExpression <em>Query Expression</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getCombinedLeft <em>Combined Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getCombinedRight <em>Combined Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getPredicateExists <em>Predicate Exists</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getUpdateSourceQuery <em>Update Source Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getWithTableSpecification <em>With Table Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class QueryExpressionBodyImpl extends TableExpressionImpl implements QueryExpressionBody {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected QueryExpressionBodyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryModelPackage.eINSTANCE.getQueryExpressionBody();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryExpressionRoot getQueryExpression() {
		if (eContainerFeatureID != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION) return null;
		return (QueryExpressionRoot)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setQueryExpression(QueryExpressionRoot newQueryExpression) {
		if (newQueryExpression != eContainer || (eContainerFeatureID != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION && newQueryExpression != null)) {
			if (EcoreUtil.isAncestor(this, newQueryExpression))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQueryExpression != null)
				msgs = ((InternalEObject)newQueryExpression).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUERY, QueryExpressionRoot.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newQueryExpression, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION, newQueryExpression, newQueryExpression));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryCombined getCombinedLeft() {
		if (eContainerFeatureID != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT) return null;
		return (QueryCombined)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCombinedLeft(QueryCombined newCombinedLeft) {
		if (newCombinedLeft != eContainer || (eContainerFeatureID != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT && newCombinedLeft != null)) {
			if (EcoreUtil.isAncestor(this, newCombinedLeft))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCombinedLeft != null)
				msgs = ((InternalEObject)newCombinedLeft).eInverseAdd(this, SQLQueryModelPackage.QUERY_COMBINED__LEFT_QUERY, QueryCombined.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newCombinedLeft, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT, newCombinedLeft, newCombinedLeft));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryCombined getCombinedRight() {
		if (eContainerFeatureID != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT) return null;
		return (QueryCombined)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCombinedRight(QueryCombined newCombinedRight) {
		if (newCombinedRight != eContainer || (eContainerFeatureID != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT && newCombinedRight != null)) {
			if (EcoreUtil.isAncestor(this, newCombinedRight))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCombinedRight != null)
				msgs = ((InternalEObject)newCombinedRight).eInverseAdd(this, SQLQueryModelPackage.QUERY_COMBINED__RIGHT_QUERY, QueryCombined.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newCombinedRight, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT, newCombinedRight, newCombinedRight));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateExists getPredicateExists() {
		if (eContainerFeatureID != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS) return null;
		return (PredicateExists)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPredicateExists(PredicateExists newPredicateExists) {
		if (newPredicateExists != eContainer || (eContainerFeatureID != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS && newPredicateExists != null)) {
			if (EcoreUtil.isAncestor(this, newPredicateExists))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPredicateExists != null)
				msgs = ((InternalEObject)newPredicateExists).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_EXISTS__QUERY_EXPR, PredicateExists.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newPredicateExists, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS, newPredicateExists, newPredicateExists));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public UpdateSourceQuery getUpdateSourceQuery() {
		if (eContainerFeatureID != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY) return null;
		return (UpdateSourceQuery)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setUpdateSourceQuery(UpdateSourceQuery newUpdateSourceQuery) {
		if (newUpdateSourceQuery != eContainer || (eContainerFeatureID != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY && newUpdateSourceQuery != null)) {
			if (EcoreUtil.isAncestor(this, newUpdateSourceQuery))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUpdateSourceQuery != null)
				msgs = ((InternalEObject)newUpdateSourceQuery).eInverseAdd(this, SQLQueryModelPackage.UPDATE_SOURCE_QUERY__QUERY_EXPR, UpdateSourceQuery.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newUpdateSourceQuery, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY, newUpdateSourceQuery, newUpdateSourceQuery));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public WithTableSpecification getWithTableSpecification() {
		if (eContainerFeatureID != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION) return null;
		return (WithTableSpecification)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setWithTableSpecification(WithTableSpecification newWithTableSpecification) {
		if (newWithTableSpecification != eContainer || (eContainerFeatureID != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION && newWithTableSpecification != null)) {
			if (EcoreUtil.isAncestor(this, newWithTableSpecification))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWithTableSpecification != null)
				msgs = ((InternalEObject)newWithTableSpecification).eInverseAdd(this, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR, WithTableSpecification.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newWithTableSpecification, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION, newWithTableSpecification, newWithTableSpecification));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_LEFT, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_SELECT, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__NEST, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_CORRELATION:
					if (tableCorrelation != null)
						msgs = ((InternalEObject)tableCorrelation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_CORRELATION, null, msgs);
					return basicSetTableCorrelation((TableCorrelation)otherEnd, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__VALUE_EXPR_COLUMNS:
					return ((InternalEList)getValueExprColumns()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION, msgs);
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
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_LEFT, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_SELECT, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__NEST:
					return eBasicSetContainer(null, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__NEST, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_CORRELATION:
					return basicSetTableCorrelation(null, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__VALUE_EXPR_COLUMNS:
					return ((InternalEList)getValueExprColumns()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION:
					return eBasicSetContainer(null, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS:
					return eBasicSetContainer(null, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY:
					return eBasicSetContainer(null, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION:
					return eBasicSetContainer(null, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION, msgs);
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
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_RIGHT, TableJoined.class, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_LEFT, TableJoined.class, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__FROM_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__NEST:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF, TableNested.class, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUERY, QueryExpressionRoot.class, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_COMBINED__LEFT_QUERY, QueryCombined.class, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_COMBINED__RIGHT_QUERY, QueryCombined.class, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.PREDICATE_EXISTS__QUERY_EXPR, PredicateExists.class, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.UPDATE_SOURCE_QUERY__QUERY_EXPR, UpdateSourceQuery.class, msgs);
				case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR, WithTableSpecification.class, msgs);
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
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__NAME:
				return getName();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__LABEL:
				return getLabel();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_RIGHT:
				return getTableJoinedRight();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_LEFT:
				return getTableJoinedLeft();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__NEST:
				return getNest();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COLUMN_LIST:
				return getColumnList();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_CORRELATION:
				return getTableCorrelation();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__RESULT_TABLE_ALL_COLUMNS:
				return getResultTableAllColumns();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__VALUE_EXPR_COLUMNS:
				return getValueExprColumns();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION:
				return getQueryExpression();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT:
				return getCombinedLeft();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT:
				return getCombinedRight();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS:
				return getPredicateExists();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY:
				return getUpdateSourceQuery();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION:
				return getWithTableSpecification();
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
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__NEST:
				setNest((TableNested)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COLUMN_LIST:
				getColumnList().clear();
				getColumnList().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				getResultTableAllColumns().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__VALUE_EXPR_COLUMNS:
				getValueExprColumns().clear();
				getValueExprColumns().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION:
				setQueryExpression((QueryExpressionRoot)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT:
				setCombinedLeft((QueryCombined)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT:
				setCombinedRight((QueryCombined)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS:
				setPredicateExists((PredicateExists)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY:
				setUpdateSourceQuery((UpdateSourceQuery)newValue);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION:
				setWithTableSpecification((WithTableSpecification)newValue);
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
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)null);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)null);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__NEST:
				setNest((TableNested)null);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COLUMN_LIST:
				getColumnList().clear();
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)null);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__VALUE_EXPR_COLUMNS:
				getValueExprColumns().clear();
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION:
				setQueryExpression((QueryExpressionRoot)null);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT:
				setCombinedLeft((QueryCombined)null);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT:
				setCombinedRight((QueryCombined)null);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS:
				setPredicateExists((PredicateExists)null);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY:
				setUpdateSourceQuery((UpdateSourceQuery)null);
				return;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION:
				setWithTableSpecification((WithTableSpecification)null);
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
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_RIGHT:
				return getTableJoinedRight() != null;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_JOINED_LEFT:
				return getTableJoinedLeft() != null;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__NEST:
				return getNest() != null;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COLUMN_LIST:
				return columnList != null && !columnList.isEmpty();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__TABLE_CORRELATION:
				return tableCorrelation != null;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__RESULT_TABLE_ALL_COLUMNS:
				return resultTableAllColumns != null && !resultTableAllColumns.isEmpty();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__VALUE_EXPR_COLUMNS:
				return valueExprColumns != null && !valueExprColumns.isEmpty();
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION:
				return getQueryExpression() != null;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT:
				return getCombinedLeft() != null;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT:
				return getCombinedRight() != null;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS:
				return getPredicateExists() != null;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY:
				return getUpdateSourceQuery() != null;
			case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION:
				return getWithTableSpecification() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLQueryExpressionBodyImpl
