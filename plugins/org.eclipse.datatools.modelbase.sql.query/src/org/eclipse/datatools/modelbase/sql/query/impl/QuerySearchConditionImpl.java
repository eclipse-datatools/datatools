/**
 * <copyright>
 * </copyright>
 *
 * $Id: QuerySearchConditionImpl.java,v 1.2 2005/12/17 01:46:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Search Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#isNegatedCondition <em>Negated Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getUpdateStatement <em>Update Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getDeleteStatement <em>Delete Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getTableJoined <em>Table Joined</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getCombinedLeft <em>Combined Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getCombinedRight <em>Combined Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getQuerySelectHaving <em>Query Select Having</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getQuerySelectWhere <em>Query Select Where</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getValueExprCaseSearchContent <em>Value Expr Case Search Content</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getNest <em>Nest</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class QuerySearchConditionImpl extends SQLQueryObjectImpl implements QuerySearchCondition {
	/**
	 * The default value of the '{@link #isNegatedCondition() <em>Negated Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isNegatedCondition()
	 * @generated
	 * @ordered
	 */
    protected static final boolean NEGATED_CONDITION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNegatedCondition() <em>Negated Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isNegatedCondition()
	 * @generated
	 * @ordered
	 */
    protected boolean negatedCondition = NEGATED_CONDITION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected QuerySearchConditionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getQuerySearchCondition();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isNegatedCondition() {
		return negatedCondition;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNegatedCondition(boolean newNegatedCondition) {
		boolean oldNegatedCondition = negatedCondition;
		negatedCondition = newNegatedCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SEARCH_CONDITION__NEGATED_CONDITION, oldNegatedCondition, negatedCondition));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryUpdateStatement getUpdateStatement() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT) return null;
		return (QueryUpdateStatement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUpdateStatement(QueryUpdateStatement newUpdateStatement) {
		if (newUpdateStatement != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT && newUpdateStatement != null)) {
			if (EcoreUtil.isAncestor(this, newUpdateStatement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUpdateStatement != null)
				msgs = ((InternalEObject)newUpdateStatement).eInverseAdd(this, SQLQueryPackage.QUERY_UPDATE_STATEMENT__WHERE_CLAUSE, QueryUpdateStatement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newUpdateStatement, SQLQueryPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT, newUpdateStatement, newUpdateStatement));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryDeleteStatement getDeleteStatement() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT) return null;
		return (QueryDeleteStatement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDeleteStatement(QueryDeleteStatement newDeleteStatement) {
		if (newDeleteStatement != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT && newDeleteStatement != null)) {
			if (EcoreUtil.isAncestor(this, newDeleteStatement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDeleteStatement != null)
				msgs = ((InternalEObject)newDeleteStatement).eInverseAdd(this, SQLQueryPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, QueryDeleteStatement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newDeleteStatement, SQLQueryPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT, newDeleteStatement, newDeleteStatement));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TableJoined getTableJoined() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED) return null;
		return (TableJoined)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTableJoined(TableJoined newTableJoined) {
		if (newTableJoined != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED && newTableJoined != null)) {
			if (EcoreUtil.isAncestor(this, newTableJoined))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTableJoined != null)
				msgs = ((InternalEObject)newTableJoined).eInverseAdd(this, SQLQueryPackage.TABLE_JOINED__JOIN_CONDITION, TableJoined.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newTableJoined, SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED, newTableJoined, newTableJoined));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SearchConditionCombined getCombinedLeft() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT) return null;
		return (SearchConditionCombined)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCombinedLeft(SearchConditionCombined newCombinedLeft) {
		if (newCombinedLeft != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT && newCombinedLeft != null)) {
			if (EcoreUtil.isAncestor(this, newCombinedLeft))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCombinedLeft != null)
				msgs = ((InternalEObject)newCombinedLeft).eInverseAdd(this, SQLQueryPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION, SearchConditionCombined.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newCombinedLeft, SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT, newCombinedLeft, newCombinedLeft));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SearchConditionCombined getCombinedRight() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT) return null;
		return (SearchConditionCombined)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCombinedRight(SearchConditionCombined newCombinedRight) {
		if (newCombinedRight != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT && newCombinedRight != null)) {
			if (EcoreUtil.isAncestor(this, newCombinedRight))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCombinedRight != null)
				msgs = ((InternalEObject)newCombinedRight).eInverseAdd(this, SQLQueryPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION, SearchConditionCombined.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newCombinedRight, SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT, newCombinedRight, newCombinedRight));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QuerySelect getQuerySelectHaving() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING) return null;
		return (QuerySelect)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setQuerySelectHaving(QuerySelect newQuerySelectHaving) {
		if (newQuerySelectHaving != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING && newQuerySelectHaving != null)) {
			if (EcoreUtil.isAncestor(this, newQuerySelectHaving))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQuerySelectHaving != null)
				msgs = ((InternalEObject)newQuerySelectHaving).eInverseAdd(this, SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE, QuerySelect.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newQuerySelectHaving, SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING, newQuerySelectHaving, newQuerySelectHaving));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QuerySelect getQuerySelectWhere() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE) return null;
		return (QuerySelect)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setQuerySelectWhere(QuerySelect newQuerySelectWhere) {
		if (newQuerySelectWhere != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE && newQuerySelectWhere != null)) {
			if (EcoreUtil.isAncestor(this, newQuerySelectWhere))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQuerySelectWhere != null)
				msgs = ((InternalEObject)newQuerySelectWhere).eInverseAdd(this, SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE, QuerySelect.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newQuerySelectWhere, SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE, newQuerySelectWhere, newQuerySelectWhere));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValueExpressionCaseSearchContent getValueExprCaseSearchContent() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT) return null;
		return (ValueExpressionCaseSearchContent)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueExprCaseSearchContent(ValueExpressionCaseSearchContent newValueExprCaseSearchContent) {
		if (newValueExprCaseSearchContent != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT && newValueExprCaseSearchContent != null)) {
			if (EcoreUtil.isAncestor(this, newValueExprCaseSearchContent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueExprCaseSearchContent != null)
				msgs = ((InternalEObject)newValueExprCaseSearchContent).eInverseAdd(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION, ValueExpressionCaseSearchContent.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueExprCaseSearchContent, SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT, newValueExprCaseSearchContent, newValueExprCaseSearchContent));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public SearchConditionNested getNest() {
		if (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST) return null;
		return (SearchConditionNested)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setNest(SearchConditionNested newNest) {
		if (newNest != eContainer || (eContainerFeatureID != SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST && newNest != null)) {
			if (EcoreUtil.isAncestor(this, newNest))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newNest != null)
				msgs = ((InternalEObject)newNest).eInverseAdd(this, SQLQueryPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, SearchConditionNested.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newNest, SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST, newNest, newNest));
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
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST, msgs);
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
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST, msgs);
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
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_UPDATE_STATEMENT__WHERE_CLAUSE, QueryUpdateStatement.class, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, QueryDeleteStatement.class, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_JOINED__JOIN_CONDITION, TableJoined.class, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION, SearchConditionCombined.class, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION, SearchConditionCombined.class, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION, ValueExpressionCaseSearchContent.class, msgs);
				case SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST:
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
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__NAME:
				return getName();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__LABEL:
				return getLabel();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__NEGATED_CONDITION:
				return isNegatedCondition() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT:
				return getUpdateStatement();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT:
				return getDeleteStatement();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED:
				return getTableJoined();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT:
				return getCombinedLeft();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT:
				return getCombinedRight();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING:
				return getQuerySelectHaving();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE:
				return getQuerySelectWhere();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST:
				return getNest();
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
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__NEGATED_CONDITION:
				setNegatedCondition(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)newValue);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)newValue);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED:
				setTableJoined((TableJoined)newValue);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)newValue);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)newValue);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)newValue);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)newValue);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST:
				setNest((SearchConditionNested)newValue);
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
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__NEGATED_CONDITION:
				setNegatedCondition(NEGATED_CONDITION_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)null);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)null);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED:
				setTableJoined((TableJoined)null);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)null);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)null);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)null);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)null);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST:
				setNest((SearchConditionNested)null);
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
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__NEGATED_CONDITION:
				return negatedCondition != NEGATED_CONDITION_EDEFAULT;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT:
				return getUpdateStatement() != null;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT:
				return getDeleteStatement() != null;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED:
				return getTableJoined() != null;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT:
				return getCombinedLeft() != null;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT:
				return getCombinedRight() != null;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING:
				return getQuerySelectHaving() != null;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE:
				return getQuerySelectWhere() != null;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLQueryPackage.QUERY_SEARCH_CONDITION__NEST:
				return getNest() != null;
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
		result.append(" (negatedCondition: ");
		result.append(negatedCondition);
		result.append(')');
		return result.toString();
	}

} //SQLSearchConditionImpl
