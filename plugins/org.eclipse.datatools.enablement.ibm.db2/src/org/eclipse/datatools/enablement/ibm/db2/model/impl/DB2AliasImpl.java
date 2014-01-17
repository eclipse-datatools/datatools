/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Alias;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Alias</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2AliasImpl#getAliasedTable <em>Aliased Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2AliasImpl extends TableImpl implements DB2Alias {
	/**
	 * The cached value of the '{@link #getAliasedTable() <em>Aliased Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAliasedTable()
	 * @generated
	 * @ordered
	 */
	protected Table aliasedTable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2AliasImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_ALIAS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getAliasedTable() {
		if (aliasedTable != null && aliasedTable.eIsProxy()) {
			InternalEObject oldAliasedTable = (InternalEObject)aliasedTable;
			aliasedTable = (Table)eResolveProxy(oldAliasedTable);
			if (aliasedTable != oldAliasedTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_ALIAS__ALIASED_TABLE, oldAliasedTable, aliasedTable));
			}
		}
		return aliasedTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table basicGetAliasedTable() {
		return aliasedTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAliasedTable(Table newAliasedTable) {
		Table oldAliasedTable = aliasedTable;
		aliasedTable = newAliasedTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_ALIAS__ALIASED_TABLE, oldAliasedTable, aliasedTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DB2ModelPackage.DB2_ALIAS__ALIASED_TABLE:
				if (resolve) return getAliasedTable();
				return basicGetAliasedTable();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DB2ModelPackage.DB2_ALIAS__ALIASED_TABLE:
				setAliasedTable((Table)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case DB2ModelPackage.DB2_ALIAS__ALIASED_TABLE:
				setAliasedTable((Table)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DB2ModelPackage.DB2_ALIAS__ALIASED_TABLE:
				return aliasedTable != null;
		}
		return super.eIsSet(featureID);
	}
	
	public EList getColumns() {
		if (getAliasedTable() != null) {
				return aliasedTable.getColumns();
			} else return super.getColumns();
		
		}

} //DB2AliasImpl
