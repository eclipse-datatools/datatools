/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableExpressionImpl.java,v 1.2 2005/12/17 01:46:19 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Table Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableExpressionImpl#getColumnList <em>Column List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableExpressionImpl#getTableCorrelation <em>Table Correlation</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableExpressionImpl#getResultTableAllColumns <em>Result Table All Columns</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableExpressionImpl#getValueExprColumns <em>Value Expr Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TableExpressionImpl extends TableReferenceImpl implements TableExpression {
	/**
	 * The cached value of the '{@link #getColumnList() <em>Column List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getColumnList()
	 * @generated
	 * @ordered
	 */
    protected EList columnList = null;

	/**
	 * The cached value of the '{@link #getTableCorrelation() <em>Table Correlation</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTableCorrelation()
	 * @generated
	 * @ordered
	 */
    protected TableCorrelation tableCorrelation = null;

	/**
	 * The cached value of the '{@link #getResultTableAllColumns() <em>Result Table All Columns</em>}' reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getResultTableAllColumns()
	 * @generated
	 * @ordered
	 */
  protected EList resultTableAllColumns = null;

	/**
	 * The cached value of the '{@link #getValueExprColumns() <em>Value Expr Columns</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValueExprColumns()
	 * @generated
	 * @ordered
	 */
    protected EList valueExprColumns = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected TableExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getTableExpression();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getColumnList() {
		if (columnList == null) {
			columnList = new EObjectContainmentWithInverseEList(ValueExpressionColumn.class, this, SQLQueryPackage.TABLE_EXPRESSION__COLUMN_LIST, SQLQueryPackage.VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR);
		}
		return columnList;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TableCorrelation getTableCorrelation() {
		return tableCorrelation;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetTableCorrelation(TableCorrelation newTableCorrelation, NotificationChain msgs) {
		TableCorrelation oldTableCorrelation = tableCorrelation;
		tableCorrelation = newTableCorrelation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_EXPRESSION__TABLE_CORRELATION, oldTableCorrelation, newTableCorrelation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTableCorrelation(TableCorrelation newTableCorrelation) {
		if (newTableCorrelation != tableCorrelation) {
			NotificationChain msgs = null;
			if (tableCorrelation != null)
				msgs = ((InternalEObject)tableCorrelation).eInverseRemove(this, SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR, TableCorrelation.class, msgs);
			if (newTableCorrelation != null)
				msgs = ((InternalEObject)newTableCorrelation).eInverseAdd(this, SQLQueryPackage.TABLE_CORRELATION__TABLE_EXPR, TableCorrelation.class, msgs);
			msgs = basicSetTableCorrelation(newTableCorrelation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_EXPRESSION__TABLE_CORRELATION, newTableCorrelation, newTableCorrelation));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EList getResultTableAllColumns() {
		if (resultTableAllColumns == null) {
			resultTableAllColumns = new EObjectWithInverseResolvingEList(ResultTableAllColumns.class, this, SQLQueryPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS, SQLQueryPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR);
		}
		return resultTableAllColumns;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getValueExprColumns() {
		if (valueExprColumns == null) {
			valueExprColumns = new EObjectWithInverseResolvingEList(ValueExpressionColumn.class, this, SQLQueryPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS, SQLQueryPackage.VALUE_EXPRESSION_COLUMN__TABLE_EXPR);
		}
		return valueExprColumns;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.TABLE_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_LEFT, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_EXPRESSION__QUERY_SELECT, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_EXPRESSION__NEST, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__TABLE_CORRELATION:
					if (tableCorrelation != null)
						msgs = ((InternalEObject)tableCorrelation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.TABLE_EXPRESSION__TABLE_CORRELATION, null, msgs);
					return basicSetTableCorrelation((TableCorrelation)otherEnd, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS:
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
				case SQLQueryPackage.TABLE_EXPRESSION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_LEFT, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_EXPRESSION__QUERY_SELECT, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_EXPRESSION__NEST, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__TABLE_CORRELATION:
					return basicSetTableCorrelation(null, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS:
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
				case SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT, TableJoined.class, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT, TableJoined.class, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__FROM_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.TABLE_EXPRESSION__NEST:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_NESTED__NESTED_TABLE_REF, TableNested.class, msgs);
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
			case SQLQueryPackage.TABLE_EXPRESSION__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.TABLE_EXPRESSION__NAME:
				return getName();
			case SQLQueryPackage.TABLE_EXPRESSION__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.TABLE_EXPRESSION__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.TABLE_EXPRESSION__LABEL:
				return getLabel();
			case SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_RIGHT:
				return getTableJoinedRight();
			case SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_LEFT:
				return getTableJoinedLeft();
			case SQLQueryPackage.TABLE_EXPRESSION__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryPackage.TABLE_EXPRESSION__NEST:
				return getNest();
			case SQLQueryPackage.TABLE_EXPRESSION__COLUMN_LIST:
				return getColumnList();
			case SQLQueryPackage.TABLE_EXPRESSION__TABLE_CORRELATION:
				return getTableCorrelation();
			case SQLQueryPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS:
				return getResultTableAllColumns();
			case SQLQueryPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS:
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
			case SQLQueryPackage.TABLE_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)newValue);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)newValue);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__NEST:
				setNest((TableNested)newValue);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__COLUMN_LIST:
				getColumnList().clear();
				getColumnList().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)newValue);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				getResultTableAllColumns().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS:
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
			case SQLQueryPackage.TABLE_EXPRESSION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)null);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)null);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__NEST:
				setNest((TableNested)null);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__COLUMN_LIST:
				getColumnList().clear();
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)null);
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				return;
			case SQLQueryPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS:
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
			case SQLQueryPackage.TABLE_EXPRESSION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.TABLE_EXPRESSION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.TABLE_EXPRESSION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.TABLE_EXPRESSION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.TABLE_EXPRESSION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_RIGHT:
				return getTableJoinedRight() != null;
			case SQLQueryPackage.TABLE_EXPRESSION__TABLE_JOINED_LEFT:
				return getTableJoinedLeft() != null;
			case SQLQueryPackage.TABLE_EXPRESSION__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryPackage.TABLE_EXPRESSION__NEST:
				return getNest() != null;
			case SQLQueryPackage.TABLE_EXPRESSION__COLUMN_LIST:
				return columnList != null && !columnList.isEmpty();
			case SQLQueryPackage.TABLE_EXPRESSION__TABLE_CORRELATION:
				return tableCorrelation != null;
			case SQLQueryPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS:
				return resultTableAllColumns != null && !resultTableAllColumns.isEmpty();
			case SQLQueryPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS:
				return valueExprColumns != null && !valueExprColumns.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLTableExpressionImpl
