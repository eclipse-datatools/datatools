/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableCorrelationImpl.java,v 1.6 2005/10/22 01:35:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.ColumnName;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
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
 * An implementation of the model object '<em><b>SQL Table Correlation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableCorrelationImpl#getTableExpr <em>Table Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableCorrelationImpl#getColumnNameList <em>Column Name List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableCorrelationImpl extends SQLQueryObjectImpl implements TableCorrelation {
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
    protected TableCorrelationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getTableCorrelation();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TableExpression getTableExpr() {
		if (eContainerFeatureID != SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR) return null;
		return (TableExpression)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTableExpr(TableExpression newTableExpr) {
		if (newTableExpr != eContainer || (eContainerFeatureID != SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR && newTableExpr != null)) {
			if (EcoreUtil.isAncestor(this, newTableExpr))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTableExpr != null)
				msgs = ((InternalEObject)newTableExpr).eInverseAdd(this, SQLQueryPackage.TABLE_EXPRESSION__TABLE_CORRELATION, TableExpression.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newTableExpr, SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR, newTableExpr, newTableExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getColumnNameList() {
		if (columnNameList == null) {
			columnNameList = new EObjectContainmentWithInverseEList(ColumnName.class, this, SQLQueryPackage.TABLE_CORRELATION__COLUMN_NAME_LIST, SQLQueryPackage.COLUMN_NAME__TABLE_CORRELATION);
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
				case SQLQueryPackage.TABLE_CORRELATION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR, msgs);
				case SQLQueryPackage.TABLE_CORRELATION__COLUMN_NAME_LIST:
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
				case SQLQueryPackage.TABLE_CORRELATION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_CORRELATION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR, msgs);
				case SQLQueryPackage.TABLE_CORRELATION__COLUMN_NAME_LIST:
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
				case SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_EXPRESSION__TABLE_CORRELATION, TableExpression.class, msgs);
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
			case SQLQueryPackage.TABLE_CORRELATION__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.TABLE_CORRELATION__NAME:
				return getName();
			case SQLQueryPackage.TABLE_CORRELATION__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.TABLE_CORRELATION__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.TABLE_CORRELATION__LABEL:
				return getLabel();
			case SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR:
				return getTableExpr();
			case SQLQueryPackage.TABLE_CORRELATION__COLUMN_NAME_LIST:
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
			case SQLQueryPackage.TABLE_CORRELATION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_CORRELATION__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.TABLE_CORRELATION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_CORRELATION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.TABLE_CORRELATION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR:
				setTableExpr((TableExpression)newValue);
				return;
			case SQLQueryPackage.TABLE_CORRELATION__COLUMN_NAME_LIST:
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
			case SQLQueryPackage.TABLE_CORRELATION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.TABLE_CORRELATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_CORRELATION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.TABLE_CORRELATION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_CORRELATION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR:
				setTableExpr((TableExpression)null);
				return;
			case SQLQueryPackage.TABLE_CORRELATION__COLUMN_NAME_LIST:
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
			case SQLQueryPackage.TABLE_CORRELATION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.TABLE_CORRELATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.TABLE_CORRELATION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.TABLE_CORRELATION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.TABLE_CORRELATION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR:
				return getTableExpr() != null;
			case SQLQueryPackage.TABLE_CORRELATION__COLUMN_NAME_LIST:
				return columnNameList != null && !columnNameList.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLTableCorrelationImpl
