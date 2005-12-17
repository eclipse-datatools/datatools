/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryInsertStatementImpl.java,v 1.1 2005/12/16 13:11:12 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
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
 * An implementation of the model object '<em><b>SQL Insert Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryInsertStatementImpl#getSourceQuery <em>Source Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryInsertStatementImpl#getSourceValuesRowList <em>Source Values Row List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryInsertStatementImpl#getTargetTable <em>Target Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryInsertStatementImpl#getTargetColumnList <em>Target Column List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryInsertStatementImpl extends QueryChangeStatementImpl implements QueryInsertStatement {
	/**
	 * The cached value of the '{@link #getSourceQuery() <em>Source Query</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSourceQuery()
	 * @generated
	 * @ordered
	 */
    protected QueryExpressionRoot sourceQuery = null;

	/**
	 * The cached value of the '{@link #getSourceValuesRowList() <em>Source Values Row List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSourceValuesRowList()
	 * @generated
	 * @ordered
	 */
    protected EList sourceValuesRowList = null;

	/**
	 * The cached value of the '{@link #getTargetTable() <em>Target Table</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTargetTable()
	 * @generated
	 * @ordered
	 */
    protected TableInDatabase targetTable = null;

	/**
	 * This is true if the Target Table containment reference has been set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    protected boolean targetTableESet = false;

	/**
	 * The cached value of the '{@link #getTargetColumnList() <em>Target Column List</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTargetColumnList()
	 * @generated
	 * @ordered
	 */
    protected EList targetColumnList = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected QueryInsertStatementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getQueryInsertStatement();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryExpressionRoot getSourceQuery() {
		return sourceQuery;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetSourceQuery(QueryExpressionRoot newSourceQuery, NotificationChain msgs) {
		QueryExpressionRoot oldSourceQuery = sourceQuery;
		sourceQuery = newSourceQuery;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY, oldSourceQuery, newSourceQuery);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSourceQuery(QueryExpressionRoot newSourceQuery) {
		if (newSourceQuery != sourceQuery) {
			NotificationChain msgs = null;
			if (sourceQuery != null)
				msgs = ((InternalEObject)sourceQuery).eInverseRemove(this, SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT, QueryExpressionRoot.class, msgs);
			if (newSourceQuery != null)
				msgs = ((InternalEObject)newSourceQuery).eInverseAdd(this, SQLQueryPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT, QueryExpressionRoot.class, msgs);
			msgs = basicSetSourceQuery(newSourceQuery, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY, newSourceQuery, newSourceQuery));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getSourceValuesRowList() {
		if (sourceValuesRowList == null) {
			sourceValuesRowList = new EObjectContainmentWithInverseEList(ValuesRow.class, this, SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST, SQLQueryPackage.VALUES_ROW__INSERT_STATEMENT);
		}
		return sourceValuesRowList;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TableInDatabase getTargetTable() {
		return targetTable;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetTargetTable(TableInDatabase newTargetTable, NotificationChain msgs) {
		TableInDatabase oldTargetTable = targetTable;
		targetTable = newTargetTable;
		boolean oldTargetTableESet = targetTableESet;
		targetTableESet = true;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE, oldTargetTable, newTargetTable, !oldTargetTableESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setTargetTable(TableInDatabase newTargetTable) {
		if (newTargetTable != targetTable) {
			NotificationChain msgs = null;
			if (targetTable != null)
				msgs = ((InternalEObject)targetTable).eInverseRemove(this, SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT, TableInDatabase.class, msgs);
			if (newTargetTable != null)
				msgs = ((InternalEObject)newTargetTable).eInverseAdd(this, SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT, TableInDatabase.class, msgs);
			msgs = basicSetTargetTable(newTargetTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldTargetTableESet = targetTableESet;
			targetTableESet = true;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE, newTargetTable, newTargetTable, !oldTargetTableESet));
    	}
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicUnsetTargetTable(NotificationChain msgs) {
		TableInDatabase oldTargetTable = targetTable;
		targetTable = null;
		boolean oldTargetTableESet = targetTableESet;
		targetTableESet = false;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE, oldTargetTable, null, oldTargetTableESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void unsetTargetTable() {
		if (targetTable != null) {
			NotificationChain msgs = null;
			msgs = ((InternalEObject)targetTable).eInverseRemove(this, SQLQueryPackage.TABLE_IN_DATABASE__INSERT_STATEMENT, TableInDatabase.class, msgs);
			msgs = basicUnsetTargetTable(msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldTargetTableESet = targetTableESet;
			targetTableESet = false;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.UNSET, SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE, null, null, oldTargetTableESet));
    	}
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSetTargetTable() {
		return targetTableESet;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getTargetColumnList() {
		if (targetColumnList == null) {
			targetColumnList = new EObjectWithInverseResolvingEList.ManyInverse(ValueExpressionColumn.class, this, SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST, SQLQueryPackage.VALUE_EXPRESSION_COLUMN__INSERT_STATEMENT);
		}
		return targetColumnList;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.QUERY_INSERT_STATEMENT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY:
					if (sourceQuery != null)
						msgs = ((InternalEObject)sourceQuery).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY, null, msgs);
					return basicSetSourceQuery((QueryExpressionRoot)otherEnd, msgs);
				case SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST:
					return ((InternalEList)getSourceValuesRowList()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE:
					if (targetTable != null)
						msgs = ((InternalEObject)targetTable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE, null, msgs);
					return basicSetTargetTable((TableInDatabase)otherEnd, msgs);
				case SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST:
					return ((InternalEList)getTargetColumnList()).basicAdd(otherEnd, msgs);
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
				case SQLQueryPackage.QUERY_INSERT_STATEMENT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_INSERT_STATEMENT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY:
					return basicSetSourceQuery(null, msgs);
				case SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST:
					return ((InternalEList)getSourceValuesRowList()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE:
					return basicUnsetTargetTable(msgs);
				case SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST:
					return ((InternalEList)getTargetColumnList()).basicRemove(otherEnd, msgs);
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
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__NAME:
				return getName();
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__LABEL:
				return getLabel();
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY:
				return getSourceQuery();
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST:
				return getSourceValuesRowList();
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE:
				return getTargetTable();
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST:
				return getTargetColumnList();
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
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY:
				setSourceQuery((QueryExpressionRoot)newValue);
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST:
				getSourceValuesRowList().clear();
				getSourceValuesRowList().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE:
				setTargetTable((TableInDatabase)newValue);
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST:
				getTargetColumnList().clear();
				getTargetColumnList().addAll((Collection)newValue);
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
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY:
				setSourceQuery((QueryExpressionRoot)null);
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST:
				getSourceValuesRowList().clear();
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE:
				unsetTargetTable();
				return;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST:
				getTargetColumnList().clear();
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
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY:
				return sourceQuery != null;
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST:
				return sourceValuesRowList != null && !sourceValuesRowList.isEmpty();
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE:
				return isSetTargetTable();
			case SQLQueryPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST:
				return targetColumnList != null && !targetColumnList.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLInsertStatementImpl
