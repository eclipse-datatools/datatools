/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask;
import org.eclipse.datatools.modelbase.sql.tables.impl.DerivedTableImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission;
import java.util.Collection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Materialized Query Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaterializedQueryTableImpl#getRefresh <em>Refresh</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaterializedQueryTableImpl#isOptimizeQuery <em>Optimize Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaterializedQueryTableImpl#getMaintainedBy <em>Maintained By</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaterializedQueryTableImpl#isActivateRowAccessControl <em>Activate Row Access Control</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaterializedQueryTableImpl#isActivateColumnAccessControl <em>Activate Column Access Control</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaterializedQueryTableImpl#getMasks <em>Masks</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaterializedQueryTableImpl#getPermissions <em>Permissions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DB2MaterializedQueryTableImpl extends DerivedTableImpl implements DB2MaterializedQueryTable {
	/**
	 * The default value of the '{@link #isOptimizeQuery() <em>Optimize Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptimizeQuery()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPTIMIZE_QUERY_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isOptimizeQuery() <em>Optimize Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptimizeQuery()
	 * @generated
	 * @ordered
	 */
	protected boolean optimizeQuery = OPTIMIZE_QUERY_EDEFAULT;

	/**
	 * The default value of the '{@link #isActivateRowAccessControl() <em>Activate Row Access Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActivateRowAccessControl()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVATE_ROW_ACCESS_CONTROL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isActivateRowAccessControl() <em>Activate Row Access Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActivateRowAccessControl()
	 * @generated
	 * @ordered
	 */
	protected boolean activateRowAccessControl = ACTIVATE_ROW_ACCESS_CONTROL_EDEFAULT;

	/**
	 * The default value of the '{@link #isActivateColumnAccessControl() <em>Activate Column Access Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActivateColumnAccessControl()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVATE_COLUMN_ACCESS_CONTROL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isActivateColumnAccessControl() <em>Activate Column Access Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActivateColumnAccessControl()
	 * @generated
	 * @ordered
	 */
	protected boolean activateColumnAccessControl = ACTIVATE_COLUMN_ACCESS_CONTROL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMasks() <em>Masks</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMasks()
	 * @generated
	 * @ordered
	 */
	protected EList masks;

	/**
	 * The cached value of the '{@link #getPermissions() <em>Permissions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPermissions()
	 * @generated
	 * @ordered
	 */
	protected EList permissions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2MaterializedQueryTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_MATERIALIZED_QUERY_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOptimizeQuery() {
		return optimizeQuery;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOptimizeQuery(boolean newOptimizeQuery) {
		boolean oldOptimizeQuery = optimizeQuery;
		optimizeQuery = newOptimizeQuery;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__OPTIMIZE_QUERY, oldOptimizeQuery, optimizeQuery));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActivateRowAccessControl() {
		return activateRowAccessControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivateRowAccessControl(boolean newActivateRowAccessControl) {
		boolean oldActivateRowAccessControl = activateRowAccessControl;
		activateRowAccessControl = newActivateRowAccessControl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_ROW_ACCESS_CONTROL, oldActivateRowAccessControl, activateRowAccessControl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActivateColumnAccessControl() {
		return activateColumnAccessControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivateColumnAccessControl(boolean newActivateColumnAccessControl) {
		boolean oldActivateColumnAccessControl = activateColumnAccessControl;
		activateColumnAccessControl = newActivateColumnAccessControl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL, oldActivateColumnAccessControl, activateColumnAccessControl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMasks() {
		if (masks == null) {
			masks = new EObjectWithInverseResolvingEList(DB2Mask.class, this, DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__MASKS, DB2ModelPackage.DB2_MASK__SUBJECT_MQT);
		}
		return masks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPermissions() {
		if (permissions == null) {
			permissions = new EObjectWithInverseResolvingEList(DB2Permission.class, this, DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__PERMISSIONS, DB2ModelPackage.DB2_PERMISSION__SUBJECT_MQT);
		}
		return permissions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__MASKS:
				return ((InternalEList)getMasks()).basicAdd(otherEnd, msgs);
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__PERMISSIONS:
				return ((InternalEList)getPermissions()).basicAdd(otherEnd, msgs);
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
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__MASKS:
				return ((InternalEList)getMasks()).basicRemove(otherEnd, msgs);
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__PERMISSIONS:
				return ((InternalEList)getPermissions()).basicRemove(otherEnd, msgs);
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
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__OPTIMIZE_QUERY:
				return isOptimizeQuery() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_ROW_ACCESS_CONTROL:
				return isActivateRowAccessControl() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL:
				return isActivateColumnAccessControl() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__MASKS:
				return getMasks();
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__PERMISSIONS:
				return getPermissions();
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
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__OPTIMIZE_QUERY:
				setOptimizeQuery(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_ROW_ACCESS_CONTROL:
				setActivateRowAccessControl(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL:
				setActivateColumnAccessControl(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__MASKS:
				getMasks().clear();
				getMasks().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__PERMISSIONS:
				getPermissions().clear();
				getPermissions().addAll((Collection)newValue);
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
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__OPTIMIZE_QUERY:
				setOptimizeQuery(OPTIMIZE_QUERY_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_ROW_ACCESS_CONTROL:
				setActivateRowAccessControl(ACTIVATE_ROW_ACCESS_CONTROL_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL:
				setActivateColumnAccessControl(ACTIVATE_COLUMN_ACCESS_CONTROL_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__MASKS:
				getMasks().clear();
				return;
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__PERMISSIONS:
				getPermissions().clear();
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
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__OPTIMIZE_QUERY:
				return optimizeQuery != OPTIMIZE_QUERY_EDEFAULT;
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_ROW_ACCESS_CONTROL:
				return activateRowAccessControl != ACTIVATE_ROW_ACCESS_CONTROL_EDEFAULT;
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL:
				return activateColumnAccessControl != ACTIVATE_COLUMN_ACCESS_CONTROL_EDEFAULT;
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__MASKS:
				return masks != null && !masks.isEmpty();
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__PERMISSIONS:
				return permissions != null && !permissions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(", optimizeQuery: "); //$NON-NLS-1$
		result.append(optimizeQuery);
		result.append(", activateRowAccessControl: "); //$NON-NLS-1$
		result.append(activateRowAccessControl);
		result.append(", activateColumnAccessControl: "); //$NON-NLS-1$
		result.append(activateColumnAccessControl);
		result.append(')');
		return result.toString();
	}

} //DB2MaterializedQueryTableImpl
