/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.modelbase.sql.schema.impl.DatabaseImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Database;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Database</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseImpl#isPartitioned <em>Partitioned</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseImpl#isDefaultOrganizeByRow <em>Default Organize By Row</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2DatabaseImpl extends DatabaseImpl implements DB2Database {
	/**
	 * The default value of the '{@link #isPartitioned() <em>Partitioned</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPartitioned()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PARTITIONED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPartitioned() <em>Partitioned</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPartitioned()
	 * @generated
	 * @ordered
	 */
	protected boolean partitioned = PARTITIONED_EDEFAULT;

	/**
	 * The default value of the '{@link #isDefaultOrganizeByRow() <em>Default Organize By Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefaultOrganizeByRow()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEFAULT_ORGANIZE_BY_ROW_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isDefaultOrganizeByRow() <em>Default Organize By Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefaultOrganizeByRow()
	 * @generated
	 * @ordered
	 */
	protected boolean defaultOrganizeByRow = DEFAULT_ORGANIZE_BY_ROW_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2DatabaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_DATABASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPartitioned() {
		return partitioned;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPartitioned(boolean newPartitioned) {
		boolean oldPartitioned = partitioned;
		partitioned = newPartitioned;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_DATABASE__PARTITIONED, oldPartitioned, partitioned));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDefaultOrganizeByRow() {
		return defaultOrganizeByRow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultOrganizeByRow(boolean newDefaultOrganizeByRow) {
		boolean oldDefaultOrganizeByRow = defaultOrganizeByRow;
		defaultOrganizeByRow = newDefaultOrganizeByRow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_DATABASE__DEFAULT_ORGANIZE_BY_ROW, oldDefaultOrganizeByRow, defaultOrganizeByRow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DB2ModelPackage.DB2_DATABASE__PARTITIONED:
				return isPartitioned() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_DATABASE__DEFAULT_ORGANIZE_BY_ROW:
				return isDefaultOrganizeByRow() ? Boolean.TRUE : Boolean.FALSE;
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
			case DB2ModelPackage.DB2_DATABASE__PARTITIONED:
				setPartitioned(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_DATABASE__DEFAULT_ORGANIZE_BY_ROW:
				setDefaultOrganizeByRow(((Boolean)newValue).booleanValue());
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
			case DB2ModelPackage.DB2_DATABASE__PARTITIONED:
				setPartitioned(PARTITIONED_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_DATABASE__DEFAULT_ORGANIZE_BY_ROW:
				setDefaultOrganizeByRow(DEFAULT_ORGANIZE_BY_ROW_EDEFAULT);
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
			case DB2ModelPackage.DB2_DATABASE__PARTITIONED:
				return partitioned != PARTITIONED_EDEFAULT;
			case DB2ModelPackage.DB2_DATABASE__DEFAULT_ORGANIZE_BY_ROW:
				return defaultOrganizeByRow != DEFAULT_ORGANIZE_BY_ROW_EDEFAULT;
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
		result.append(" (partitioned: "); //$NON-NLS-1$
		result.append(partitioned);
		result.append(", defaultOrganizeByRow: "); //$NON-NLS-1$
		result.append(defaultOrganizeByRow);
		result.append(')');
		return result.toString();
	}

} //DB2DatabaseImpl
