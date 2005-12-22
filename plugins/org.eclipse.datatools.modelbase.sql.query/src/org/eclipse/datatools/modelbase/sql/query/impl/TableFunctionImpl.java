/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableFunctionImpl.java,v 1.3 2005/12/19 20:56:37 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableFunction;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Table Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class TableFunctionImpl extends TableExpressionImpl implements TableFunction {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected TableFunctionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryModelPackage.eINSTANCE.getTableFunction();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.TABLE_FUNCTION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_LEFT, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.TABLE_FUNCTION__QUERY_SELECT, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.TABLE_FUNCTION__NEST, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_CORRELATION:
					if (tableCorrelation != null)
						msgs = ((InternalEObject)tableCorrelation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.TABLE_FUNCTION__TABLE_CORRELATION, null, msgs);
					return basicSetTableCorrelation((TableCorrelation)otherEnd, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__VALUE_EXPR_COLUMNS:
					return ((InternalEList)getValueExprColumns()).basicAdd(otherEnd, msgs);
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
				case SQLQueryModelPackage.TABLE_FUNCTION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_LEFT, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryModelPackage.TABLE_FUNCTION__QUERY_SELECT, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__NEST:
					return eBasicSetContainer(null, SQLQueryModelPackage.TABLE_FUNCTION__NEST, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_CORRELATION:
					return basicSetTableCorrelation(null, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__VALUE_EXPR_COLUMNS:
					return ((InternalEList)getValueExprColumns()).basicRemove(otherEnd, msgs);
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
				case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_RIGHT, TableJoined.class, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_LEFT, TableJoined.class, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__FROM_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryModelPackage.TABLE_FUNCTION__NEST:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF, TableNested.class, msgs);
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
			case SQLQueryModelPackage.TABLE_FUNCTION__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.TABLE_FUNCTION__NAME:
				return getName();
			case SQLQueryModelPackage.TABLE_FUNCTION__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.TABLE_FUNCTION__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.TABLE_FUNCTION__LABEL:
				return getLabel();
			case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_RIGHT:
				return getTableJoinedRight();
			case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_LEFT:
				return getTableJoinedLeft();
			case SQLQueryModelPackage.TABLE_FUNCTION__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryModelPackage.TABLE_FUNCTION__NEST:
				return getNest();
			case SQLQueryModelPackage.TABLE_FUNCTION__COLUMN_LIST:
				return getColumnList();
			case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_CORRELATION:
				return getTableCorrelation();
			case SQLQueryModelPackage.TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS:
				return getResultTableAllColumns();
			case SQLQueryModelPackage.TABLE_FUNCTION__VALUE_EXPR_COLUMNS:
				return getValueExprColumns();
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
			case SQLQueryModelPackage.TABLE_FUNCTION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)newValue);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)newValue);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__NEST:
				setNest((TableNested)newValue);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__COLUMN_LIST:
				getColumnList().clear();
				getColumnList().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)newValue);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				getResultTableAllColumns().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__VALUE_EXPR_COLUMNS:
				getValueExprColumns().clear();
				getValueExprColumns().addAll((Collection)newValue);
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
			case SQLQueryModelPackage.TABLE_FUNCTION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)null);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)null);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__NEST:
				setNest((TableNested)null);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__COLUMN_LIST:
				getColumnList().clear();
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)null);
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				return;
			case SQLQueryModelPackage.TABLE_FUNCTION__VALUE_EXPR_COLUMNS:
				getValueExprColumns().clear();
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
			case SQLQueryModelPackage.TABLE_FUNCTION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.TABLE_FUNCTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.TABLE_FUNCTION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.TABLE_FUNCTION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.TABLE_FUNCTION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_RIGHT:
				return getTableJoinedRight() != null;
			case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_LEFT:
				return getTableJoinedLeft() != null;
			case SQLQueryModelPackage.TABLE_FUNCTION__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryModelPackage.TABLE_FUNCTION__NEST:
				return getNest() != null;
			case SQLQueryModelPackage.TABLE_FUNCTION__COLUMN_LIST:
				return columnList != null && !columnList.isEmpty();
			case SQLQueryModelPackage.TABLE_FUNCTION__TABLE_CORRELATION:
				return tableCorrelation != null;
			case SQLQueryModelPackage.TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS:
				return resultTableAllColumns != null && !resultTableAllColumns.isEmpty();
			case SQLQueryModelPackage.TABLE_FUNCTION__VALUE_EXPR_COLUMNS:
				return valueExprColumns != null && !valueExprColumns.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLTableFunctionImpl
