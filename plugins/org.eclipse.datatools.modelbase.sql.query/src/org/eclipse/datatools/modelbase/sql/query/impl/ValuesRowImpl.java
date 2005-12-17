/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValuesRowImpl.java,v 1.1 2005/12/16 13:11:12 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Values Row</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValuesRowImpl#getInsertStatement <em>Insert Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValuesRowImpl#getExprList <em>Expr List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValuesRowImpl#getQueryValues <em>Query Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValuesRowImpl extends SQLQueryObjectImpl implements ValuesRow {
	/**
	 * The cached value of the '{@link #getExprList() <em>Expr List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getExprList()
	 * @generated
	 * @ordered
	 */
    protected EList exprList = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ValuesRowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getValuesRow();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryInsertStatement getInsertStatement() {
		if (eContainerFeatureID != SQLQueryPackage.VALUES_ROW__INSERT_STATEMENT) return null;
		return (QueryInsertStatement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setInsertStatement(QueryInsertStatement newInsertStatement) {
		if (newInsertStatement != eContainer || (eContainerFeatureID != SQLQueryPackage.VALUES_ROW__INSERT_STATEMENT && newInsertStatement != null)) {
			if (EcoreUtil.isAncestor(this, newInsertStatement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInsertStatement != null)
				msgs = ((InternalEObject)newInsertStatement).eInverseAdd(this, SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST, QueryInsertStatement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newInsertStatement, SQLQueryPackage.VALUES_ROW__INSERT_STATEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.VALUES_ROW__INSERT_STATEMENT, newInsertStatement, newInsertStatement));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getExprList() {
		if (exprList == null) {
			exprList = new EObjectContainmentWithInverseEList(QueryValueExpression.class, this, SQLQueryPackage.VALUES_ROW__EXPR_LIST, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW);
		}
		return exprList;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryValues getQueryValues() {
		if (eContainerFeatureID != SQLQueryPackage.VALUES_ROW__QUERY_VALUES) return null;
		return (QueryValues)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setQueryValues(QueryValues newQueryValues) {
		if (newQueryValues != eContainer || (eContainerFeatureID != SQLQueryPackage.VALUES_ROW__QUERY_VALUES && newQueryValues != null)) {
			if (EcoreUtil.isAncestor(this, newQueryValues))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQueryValues != null)
				msgs = ((InternalEObject)newQueryValues).eInverseAdd(this, SQLQueryPackage.QUERY_VALUES__VALUES_ROW_LIST, QueryValues.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newQueryValues, SQLQueryPackage.VALUES_ROW__QUERY_VALUES, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.VALUES_ROW__QUERY_VALUES, newQueryValues, newQueryValues));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.VALUES_ROW__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.VALUES_ROW__INSERT_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUES_ROW__INSERT_STATEMENT, msgs);
				case SQLQueryPackage.VALUES_ROW__EXPR_LIST:
					return ((InternalEList)getExprList()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.VALUES_ROW__QUERY_VALUES:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUES_ROW__QUERY_VALUES, msgs);
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
				case SQLQueryPackage.VALUES_ROW__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.VALUES_ROW__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.VALUES_ROW__INSERT_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.VALUES_ROW__INSERT_STATEMENT, msgs);
				case SQLQueryPackage.VALUES_ROW__EXPR_LIST:
					return ((InternalEList)getExprList()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.VALUES_ROW__QUERY_VALUES:
					return eBasicSetContainer(null, SQLQueryPackage.VALUES_ROW__QUERY_VALUES, msgs);
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
				case SQLQueryPackage.VALUES_ROW__INSERT_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST, QueryInsertStatement.class, msgs);
				case SQLQueryPackage.VALUES_ROW__QUERY_VALUES:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_VALUES__VALUES_ROW_LIST, QueryValues.class, msgs);
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
			case SQLQueryPackage.VALUES_ROW__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.VALUES_ROW__NAME:
				return getName();
			case SQLQueryPackage.VALUES_ROW__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.VALUES_ROW__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.VALUES_ROW__LABEL:
				return getLabel();
			case SQLQueryPackage.VALUES_ROW__INSERT_STATEMENT:
				return getInsertStatement();
			case SQLQueryPackage.VALUES_ROW__EXPR_LIST:
				return getExprList();
			case SQLQueryPackage.VALUES_ROW__QUERY_VALUES:
				return getQueryValues();
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
			case SQLQueryPackage.VALUES_ROW__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.VALUES_ROW__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.VALUES_ROW__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.VALUES_ROW__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.VALUES_ROW__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.VALUES_ROW__INSERT_STATEMENT:
				setInsertStatement((QueryInsertStatement)newValue);
				return;
			case SQLQueryPackage.VALUES_ROW__EXPR_LIST:
				getExprList().clear();
				getExprList().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.VALUES_ROW__QUERY_VALUES:
				setQueryValues((QueryValues)newValue);
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
			case SQLQueryPackage.VALUES_ROW__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.VALUES_ROW__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.VALUES_ROW__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.VALUES_ROW__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.VALUES_ROW__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.VALUES_ROW__INSERT_STATEMENT:
				setInsertStatement((QueryInsertStatement)null);
				return;
			case SQLQueryPackage.VALUES_ROW__EXPR_LIST:
				getExprList().clear();
				return;
			case SQLQueryPackage.VALUES_ROW__QUERY_VALUES:
				setQueryValues((QueryValues)null);
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
			case SQLQueryPackage.VALUES_ROW__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.VALUES_ROW__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.VALUES_ROW__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.VALUES_ROW__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.VALUES_ROW__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.VALUES_ROW__INSERT_STATEMENT:
				return getInsertStatement() != null;
			case SQLQueryPackage.VALUES_ROW__EXPR_LIST:
				return exprList != null && !exprList.isEmpty();
			case SQLQueryPackage.VALUES_ROW__QUERY_VALUES:
				return getQueryValues() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLValuesRowImpl
