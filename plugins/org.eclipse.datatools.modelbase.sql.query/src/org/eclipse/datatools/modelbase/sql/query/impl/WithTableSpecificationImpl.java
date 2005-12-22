/**
 * <copyright>
 * </copyright>
 *
 * $Id: WithTableSpecificationImpl.java,v 1.2 2005/12/17 01:46:20 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.ColumnName;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.WithTableReference;
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
 * An implementation of the model object '<em><b>With Table Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.WithTableSpecificationImpl#getQueryExpressionRoot <em>Query Expression Root</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.WithTableSpecificationImpl#getWithTableQueryExpr <em>With Table Query Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.WithTableSpecificationImpl#getWithTableReferences <em>With Table References</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.WithTableSpecificationImpl#getColumnNameList <em>Column Name List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WithTableSpecificationImpl extends SQLQueryObjectImpl implements WithTableSpecification {
	/**
	 * The cached value of the '{@link #getWithTableQueryExpr() <em>With Table Query Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getWithTableQueryExpr()
	 * @generated
	 * @ordered
	 */
    protected QueryExpressionBody withTableQueryExpr = null;

	/**
	 * The cached value of the '{@link #getWithTableReferences() <em>With Table References</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getWithTableReferences()
	 * @generated
	 * @ordered
	 */
    protected EList withTableReferences = null;

	/**
	 * The cached value of the '{@link #getColumnNameList() <em>Column Name List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getColumnNameList()
	 * @generated
	 * @ordered
	 */
    protected EList columnNameList = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected WithTableSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryModelPackage.eINSTANCE.getWithTableSpecification();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryExpressionRoot getQueryExpressionRoot() {
		if (eContainerFeatureID != SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT) return null;
		return (QueryExpressionRoot)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setQueryExpressionRoot(QueryExpressionRoot newQueryExpressionRoot) {
		if (newQueryExpressionRoot != eContainer || (eContainerFeatureID != SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT && newQueryExpressionRoot != null)) {
			if (EcoreUtil.isAncestor(this, newQueryExpressionRoot))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQueryExpressionRoot != null)
				msgs = ((InternalEObject)newQueryExpressionRoot).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE, QueryExpressionRoot.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newQueryExpressionRoot, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT, newQueryExpressionRoot, newQueryExpressionRoot));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryExpressionBody getWithTableQueryExpr() {
		return withTableQueryExpr;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetWithTableQueryExpr(QueryExpressionBody newWithTableQueryExpr, NotificationChain msgs) {
		QueryExpressionBody oldWithTableQueryExpr = withTableQueryExpr;
		withTableQueryExpr = newWithTableQueryExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR, oldWithTableQueryExpr, newWithTableQueryExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setWithTableQueryExpr(QueryExpressionBody newWithTableQueryExpr) {
		if (newWithTableQueryExpr != withTableQueryExpr) {
			NotificationChain msgs = null;
			if (withTableQueryExpr != null)
				msgs = ((InternalEObject)withTableQueryExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION, QueryExpressionBody.class, msgs);
			if (newWithTableQueryExpr != null)
				msgs = ((InternalEObject)newWithTableQueryExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION, QueryExpressionBody.class, msgs);
			msgs = basicSetWithTableQueryExpr(newWithTableQueryExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR, newWithTableQueryExpr, newWithTableQueryExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getWithTableReferences() {
		if (withTableReferences == null) {
			withTableReferences = new EObjectWithInverseResolvingEList(WithTableReference.class, this, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES, SQLQueryModelPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION);
		}
		return withTableReferences;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getColumnNameList() {
		if (columnNameList == null) {
			columnNameList = new EObjectContainmentWithInverseEList(ColumnName.class, this, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST, SQLQueryModelPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION);
		}
		return columnNameList;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT, msgs);
				case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR:
					if (withTableQueryExpr != null)
						msgs = ((InternalEObject)withTableQueryExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR, null, msgs);
					return basicSetWithTableQueryExpr((QueryExpressionBody)otherEnd, msgs);
				case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES:
					return ((InternalEList)getWithTableReferences()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST:
					return ((InternalEList)getColumnNameList()).basicAdd(otherEnd, msgs);
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
				case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT:
					return eBasicSetContainer(null, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT, msgs);
				case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR:
					return basicSetWithTableQueryExpr(null, msgs);
				case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES:
					return ((InternalEList)getWithTableReferences()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST:
					return ((InternalEList)getColumnNameList()).basicRemove(otherEnd, msgs);
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
				case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE, QueryExpressionRoot.class, msgs);
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
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__NAME:
				return getName();
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__LABEL:
				return getLabel();
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT:
				return getQueryExpressionRoot();
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR:
				return getWithTableQueryExpr();
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES:
				return getWithTableReferences();
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST:
				return getColumnNameList();
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
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT:
				setQueryExpressionRoot((QueryExpressionRoot)newValue);
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR:
				setWithTableQueryExpr((QueryExpressionBody)newValue);
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES:
				getWithTableReferences().clear();
				getWithTableReferences().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST:
				getColumnNameList().clear();
				getColumnNameList().addAll((Collection)newValue);
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
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT:
				setQueryExpressionRoot((QueryExpressionRoot)null);
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR:
				setWithTableQueryExpr((QueryExpressionBody)null);
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES:
				getWithTableReferences().clear();
				return;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST:
				getColumnNameList().clear();
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
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT:
				return getQueryExpressionRoot() != null;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR:
				return withTableQueryExpr != null;
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES:
				return withTableReferences != null && !withTableReferences.isEmpty();
			case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST:
				return columnNameList != null && !columnNameList.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //WithTableSpecificationImpl
