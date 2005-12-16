/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableInDatabaseImpl.java,v 1.10 2005/10/22 01:35:22 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;



import org.eclipse.datatools.modelbase.sql.tables.Table;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
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

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQLRDB Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableInDatabaseImpl#getUpdateStatement <em>Update Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableInDatabaseImpl#getDeleteStatement <em>Delete Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableInDatabaseImpl#getInsertStatement <em>Insert Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableInDatabaseImpl#getDatabaseTable <em>Database Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableInDatabaseImpl#getDerivedColumnList <em>Derived Column List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableInDatabaseImpl extends TableExpressionImpl implements TableInDatabase {
	/**
	 * The cached value of the '{@link #getDatabaseTable() <em>Database Table</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDatabaseTable()
	 * @generated
	 * @ordered
	 */
    protected Table databaseTable = null;

	/**
	 * The cached value of the '{@link #getDerivedColumnList() <em>Derived Column List</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDerivedColumnList()
	 * @generated
	 * @ordered
	 */
    protected EList derivedColumnList = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected TableInDatabaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getTableInDatabase();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public QueryUpdateStatement getUpdateStatement() {
		if (eContainerFeatureID != SQLQueryPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT) return null;
		return (QueryUpdateStatement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setUpdateStatement(QueryUpdateStatement newUpdateStatement) {
		if (newUpdateStatement != eContainer || (eContainerFeatureID != SQLQueryPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT && newUpdateStatement != null)) {
			if (EcoreUtil.isAncestor(this, newUpdateStatement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUpdateStatement != null)
				msgs = ((InternalEObject)newUpdateStatement).eInverseAdd(this, SQLQueryPackage.QUERY_UPDATE_STATEMENT__TARGET_TABLE, QueryUpdateStatement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newUpdateStatement, SQLQueryPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT, newUpdateStatement, newUpdateStatement));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public QueryDeleteStatement getDeleteStatement() {
		if (eContainerFeatureID != SQLQueryPackage.TABLE_IN_DATABASE__DELETE_STATEMENT) return null;
		return (QueryDeleteStatement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setDeleteStatement(QueryDeleteStatement newDeleteStatement) {
		if (newDeleteStatement != eContainer || (eContainerFeatureID != SQLQueryPackage.TABLE_IN_DATABASE__DELETE_STATEMENT && newDeleteStatement != null)) {
			if (EcoreUtil.isAncestor(this, newDeleteStatement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDeleteStatement != null)
				msgs = ((InternalEObject)newDeleteStatement).eInverseAdd(this, SQLQueryPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE, QueryDeleteStatement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newDeleteStatement, SQLQueryPackage.TABLE_IN_DATABASE__DELETE_STATEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_IN_DATABASE__DELETE_STATEMENT, newDeleteStatement, newDeleteStatement));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public QueryInsertStatement getInsertStatement() {
		if (eContainerFeatureID != SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT) return null;
		return (QueryInsertStatement)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setInsertStatement(QueryInsertStatement newInsertStatement) {
		if (newInsertStatement != eContainer || (eContainerFeatureID != SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT && newInsertStatement != null)) {
			if (EcoreUtil.isAncestor(this, newInsertStatement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInsertStatement != null)
				msgs = ((InternalEObject)newInsertStatement).eInverseAdd(this, SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE, QueryInsertStatement.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newInsertStatement, SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT, newInsertStatement, newInsertStatement));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Table getDatabaseTable() {
		if (databaseTable != null && databaseTable.eIsProxy()) {
			Table oldDatabaseTable = databaseTable;
			databaseTable = (Table)eResolveProxy((InternalEObject)databaseTable);
			if (databaseTable != oldDatabaseTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLQueryPackage.TABLE_IN_DATABASE__DATABASE_TABLE, oldDatabaseTable, databaseTable));
			}
		}
		return databaseTable;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Table basicGetDatabaseTable() {
		return databaseTable;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDatabaseTable(Table newDatabaseTable) {
		Table oldDatabaseTable = databaseTable;
		databaseTable = newDatabaseTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.TABLE_IN_DATABASE__DATABASE_TABLE, oldDatabaseTable, databaseTable));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getDerivedColumnList() {
		if (derivedColumnList == null) {
			derivedColumnList = new EObjectWithInverseResolvingEList(ValueExpressionColumn.class, this, SQLQueryPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST, SQLQueryPackage.VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE);
		}
		return derivedColumnList;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.TABLE_IN_DATABASE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_LEFT, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_IN_DATABASE__QUERY_SELECT, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_IN_DATABASE__NEST, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_CORRELATION:
					if (tableCorrelation != null)
						msgs = ((InternalEObject)tableCorrelation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.TABLE_IN_DATABASE__TABLE_CORRELATION, null, msgs);
					return basicSetTableCorrelation((TableCorrelation)otherEnd, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__VALUE_EXPR_COLUMNS:
					return ((InternalEList)getValueExprColumns()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__DELETE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_IN_DATABASE__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST:
					return ((InternalEList)getDerivedColumnList()).basicAdd(otherEnd, msgs);
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
				case SQLQueryPackage.TABLE_IN_DATABASE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_RIGHT, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_LEFT, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_IN_DATABASE__QUERY_SELECT, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_IN_DATABASE__NEST, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_CORRELATION:
					return basicSetTableCorrelation(null, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__VALUE_EXPR_COLUMNS:
					return ((InternalEList)getValueExprColumns()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__DELETE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_IN_DATABASE__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST:
					return ((InternalEList)getDerivedColumnList()).basicRemove(otherEnd, msgs);
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
				case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_JOINED__TABLE_REF_RIGHT, TableJoined.class, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_JOINED__TABLE_REF_LEFT, TableJoined.class, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__FROM_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__NEST:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_NESTED__NESTED_TABLE_REF, TableNested.class, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_UPDATE_STATEMENT__TARGET_TABLE, QueryUpdateStatement.class, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__DELETE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE, QueryDeleteStatement.class, msgs);
				case SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE, QueryInsertStatement.class, msgs);
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
			case SQLQueryPackage.TABLE_IN_DATABASE__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.TABLE_IN_DATABASE__NAME:
				return getName();
			case SQLQueryPackage.TABLE_IN_DATABASE__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.TABLE_IN_DATABASE__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.TABLE_IN_DATABASE__LABEL:
				return getLabel();
			case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_RIGHT:
				return getTableJoinedRight();
			case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_LEFT:
				return getTableJoinedLeft();
			case SQLQueryPackage.TABLE_IN_DATABASE__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryPackage.TABLE_IN_DATABASE__NEST:
				return getNest();
			case SQLQueryPackage.TABLE_IN_DATABASE__COLUMN_LIST:
				return getColumnList();
			case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_CORRELATION:
				return getTableCorrelation();
			case SQLQueryPackage.TABLE_IN_DATABASE__RESULT_TABLE_ALL_COLUMNS:
				return getResultTableAllColumns();
			case SQLQueryPackage.TABLE_IN_DATABASE__VALUE_EXPR_COLUMNS:
				return getValueExprColumns();
			case SQLQueryPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT:
				return getUpdateStatement();
			case SQLQueryPackage.TABLE_IN_DATABASE__DELETE_STATEMENT:
				return getDeleteStatement();
			case SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT:
				return getInsertStatement();
			case SQLQueryPackage.TABLE_IN_DATABASE__DATABASE_TABLE:
				if (resolve) return getDatabaseTable();
				return basicGetDatabaseTable();
			case SQLQueryPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST:
				return getDerivedColumnList();
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
			case SQLQueryPackage.TABLE_IN_DATABASE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__NEST:
				setNest((TableNested)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__COLUMN_LIST:
				getColumnList().clear();
				getColumnList().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				getResultTableAllColumns().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__VALUE_EXPR_COLUMNS:
				getValueExprColumns().clear();
				getValueExprColumns().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT:
				setInsertStatement((QueryInsertStatement)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__DATABASE_TABLE:
				setDatabaseTable((Table)newValue);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST:
				getDerivedColumnList().clear();
				getDerivedColumnList().addAll((Collection)newValue);
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
			case SQLQueryPackage.TABLE_IN_DATABASE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)null);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)null);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__NEST:
				setNest((TableNested)null);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__COLUMN_LIST:
				getColumnList().clear();
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)null);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__VALUE_EXPR_COLUMNS:
				getValueExprColumns().clear();
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)null);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)null);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT:
				setInsertStatement((QueryInsertStatement)null);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__DATABASE_TABLE:
				setDatabaseTable((Table)null);
				return;
			case SQLQueryPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST:
				getDerivedColumnList().clear();
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
			case SQLQueryPackage.TABLE_IN_DATABASE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.TABLE_IN_DATABASE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.TABLE_IN_DATABASE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.TABLE_IN_DATABASE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.TABLE_IN_DATABASE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_RIGHT:
				return getTableJoinedRight() != null;
			case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_JOINED_LEFT:
				return getTableJoinedLeft() != null;
			case SQLQueryPackage.TABLE_IN_DATABASE__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryPackage.TABLE_IN_DATABASE__NEST:
				return getNest() != null;
			case SQLQueryPackage.TABLE_IN_DATABASE__COLUMN_LIST:
				return columnList != null && !columnList.isEmpty();
			case SQLQueryPackage.TABLE_IN_DATABASE__TABLE_CORRELATION:
				return tableCorrelation != null;
			case SQLQueryPackage.TABLE_IN_DATABASE__RESULT_TABLE_ALL_COLUMNS:
				return resultTableAllColumns != null && !resultTableAllColumns.isEmpty();
			case SQLQueryPackage.TABLE_IN_DATABASE__VALUE_EXPR_COLUMNS:
				return valueExprColumns != null && !valueExprColumns.isEmpty();
			case SQLQueryPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT:
				return getUpdateStatement() != null;
			case SQLQueryPackage.TABLE_IN_DATABASE__DELETE_STATEMENT:
				return getDeleteStatement() != null;
			case SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT:
				return getInsertStatement() != null;
			case SQLQueryPackage.TABLE_IN_DATABASE__DATABASE_TABLE:
				return databaseTable != null;
			case SQLQueryPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST:
				return derivedColumnList != null && !derivedColumnList.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLRDBTableImpl
