/**
 * <copyright>
 * </copyright>
 *
 * $Id: RelationalRemoteDataSetImpl.java,v 1.10 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteDataSet;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteDataSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Remote Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RelationalRemoteDataSetImpl#getNickname <em>Nickname</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RelationalRemoteDataSetImpl#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RelationalRemoteDataSetImpl extends SQLObjectImpl implements RelationalRemoteDataSet {
	/**
	 * The cached value of the '{@link #getNickname() <em>Nickname</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNickname()
	 * @generated
	 * @ordered
	 */
	protected EList nickname;

	/**
	 * The cached value of the '{@link #getTable() <em>Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTable()
	 * @generated
	 * @ordered
	 */
	protected BaseTable table;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationalRemoteDataSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.RELATIONAL_REMOTE_DATA_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getNickname() {
		if (nickname == null) {
			nickname = new EObjectWithInverseResolvingEList(LUWNickname.class, this, LUWPackage.RELATIONAL_REMOTE_DATA_SET__NICKNAME, LUWPackage.LUW_NICKNAME__REMOTE_DATA_SET);
		}
		return nickname;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseTable getTable() {
		if (table != null && table.eIsProxy()) {
			InternalEObject oldTable = (InternalEObject)table;
			table = (BaseTable)eResolveProxy(oldTable);
			if (table != oldTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.RELATIONAL_REMOTE_DATA_SET__TABLE, oldTable, table));
			}
		}
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseTable basicGetTable() {
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(BaseTable newTable) {
		BaseTable oldTable = table;
		table = newTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.RELATIONAL_REMOTE_DATA_SET__TABLE, oldTable, table));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.RELATIONAL_REMOTE_DATA_SET__NICKNAME:
				return ((InternalEList)getNickname()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.RELATIONAL_REMOTE_DATA_SET__NICKNAME:
				return ((InternalEList)getNickname()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.RELATIONAL_REMOTE_DATA_SET__NICKNAME:
				return getNickname();
			case LUWPackage.RELATIONAL_REMOTE_DATA_SET__TABLE:
				if (resolve) return getTable();
				return basicGetTable();
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
			case LUWPackage.RELATIONAL_REMOTE_DATA_SET__NICKNAME:
				getNickname().clear();
				getNickname().addAll((Collection)newValue);
				return;
			case LUWPackage.RELATIONAL_REMOTE_DATA_SET__TABLE:
				setTable((BaseTable)newValue);
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
			case LUWPackage.RELATIONAL_REMOTE_DATA_SET__NICKNAME:
				getNickname().clear();
				return;
			case LUWPackage.RELATIONAL_REMOTE_DATA_SET__TABLE:
				setTable((BaseTable)null);
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
			case LUWPackage.RELATIONAL_REMOTE_DATA_SET__NICKNAME:
				return nickname != null && !nickname.isEmpty();
			case LUWPackage.RELATIONAL_REMOTE_DATA_SET__TABLE:
				return table != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == RemoteDataSet.class) {
			switch (derivedFeatureID) {
				case LUWPackage.RELATIONAL_REMOTE_DATA_SET__NICKNAME: return LUWPackage.REMOTE_DATA_SET__NICKNAME;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == RemoteDataSet.class) {
			switch (baseFeatureID) {
				case LUWPackage.REMOTE_DATA_SET__NICKNAME: return LUWPackage.RELATIONAL_REMOTE_DATA_SET__NICKNAME;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //RemoteTableImpl
