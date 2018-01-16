/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask;
import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.tables.impl.PersistentTableImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Period;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Table;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2TableOrganization;
import org.eclipse.datatools.enablement.ibm.db2.model.DataCaptureType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TableImpl#getDataCapture <em>Data Capture</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TableImpl#isActivateRowAccessControl <em>Activate Row Access Control</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TableImpl#isActivateColumnAccessControl <em>Activate Column Access Control</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TableImpl#getOrganizeBy <em>Organize By</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TableImpl#getPackages <em>Packages</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TableImpl#getPeriods <em>Periods</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TableImpl#getHistoryTable <em>History Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TableImpl#getTemporalTable <em>Temporal Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TableImpl#getMasks <em>Masks</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TableImpl#getPermissions <em>Permissions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2TableImpl extends PersistentTableImpl implements DB2Table {
	/**
	 * The default value of the '{@link #getDataCapture() <em>Data Capture</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataCapture()
	 * @generated
	 * @ordered
	 */
	protected static final DataCaptureType DATA_CAPTURE_EDEFAULT = DataCaptureType.NONE_LITERAL;

	/**
	 * The cached value of the '{@link #getDataCapture() <em>Data Capture</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataCapture()
	 * @generated
	 * @ordered
	 */
	protected DataCaptureType dataCapture = DATA_CAPTURE_EDEFAULT;

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
	 * The default value of the '{@link #getOrganizeBy() <em>Organize By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrganizeBy()
	 * @generated
	 * @ordered
	 */
	protected static final DB2TableOrganization ORGANIZE_BY_EDEFAULT = DB2TableOrganization.UNSPECIFIED_LITERAL;

	/**
	 * The cached value of the '{@link #getOrganizeBy() <em>Organize By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrganizeBy()
	 * @generated
	 * @ordered
	 */
	protected DB2TableOrganization organizeBy = ORGANIZE_BY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPackages() <em>Packages</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackages()
	 * @generated
	 * @ordered
	 */
	protected EList packages;

	/**
	 * The cached value of the '{@link #getPeriods() <em>Periods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeriods()
	 * @generated
	 * @ordered
	 */
	protected EList periods;

	/**
	 * The cached value of the '{@link #getHistoryTable() <em>History Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHistoryTable()
	 * @generated
	 * @ordered
	 */
	protected DB2Table historyTable;

	/**
	 * The cached value of the '{@link #getTemporalTable() <em>Temporal Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemporalTable()
	 * @generated
	 * @ordered
	 */
	protected DB2Table temporalTable;

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
	protected DB2TableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataCaptureType getDataCapture() {
		return dataCapture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataCapture(DataCaptureType newDataCapture) {
		DataCaptureType oldDataCapture = dataCapture;
		dataCapture = newDataCapture == null ? DATA_CAPTURE_EDEFAULT : newDataCapture;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_TABLE__DATA_CAPTURE, oldDataCapture, dataCapture));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_TABLE__ACTIVATE_ROW_ACCESS_CONTROL, oldActivateRowAccessControl, activateRowAccessControl));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL, oldActivateColumnAccessControl, activateColumnAccessControl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2TableOrganization getOrganizeBy() {
		return organizeBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrganizeBy(DB2TableOrganization newOrganizeBy) {
		DB2TableOrganization oldOrganizeBy = organizeBy;
		organizeBy = newOrganizeBy == null ? ORGANIZE_BY_EDEFAULT : newOrganizeBy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_TABLE__ORGANIZE_BY, oldOrganizeBy, organizeBy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPackages() {
		if (packages == null) {
			packages = new EObjectResolvingEList(DB2Package.class, this, DB2ModelPackage.DB2_TABLE__PACKAGES);
		}
		return packages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPeriods() {
		if (periods == null) {
			periods = new EObjectContainmentWithInverseEList(DB2Period.class, this, DB2ModelPackage.DB2_TABLE__PERIODS, DB2ModelPackage.DB2_PERIOD__TABLE);
		}
		return periods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Table getHistoryTable() {
		if (historyTable != null && historyTable.eIsProxy()) {
			InternalEObject oldHistoryTable = (InternalEObject)historyTable;
			historyTable = (DB2Table)eResolveProxy(oldHistoryTable);
			if (historyTable != oldHistoryTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_TABLE__HISTORY_TABLE, oldHistoryTable, historyTable));
			}
		}
		return historyTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Table basicGetHistoryTable() {
		return historyTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHistoryTable(DB2Table newHistoryTable, NotificationChain msgs) {
		DB2Table oldHistoryTable = historyTable;
		historyTable = newHistoryTable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_TABLE__HISTORY_TABLE, oldHistoryTable, newHistoryTable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHistoryTable(DB2Table newHistoryTable) {
		if (newHistoryTable != historyTable) {
			NotificationChain msgs = null;
			if (historyTable != null)
				msgs = ((InternalEObject)historyTable).eInverseRemove(this, DB2ModelPackage.DB2_TABLE__TEMPORAL_TABLE, DB2Table.class, msgs);
			if (newHistoryTable != null)
				msgs = ((InternalEObject)newHistoryTable).eInverseAdd(this, DB2ModelPackage.DB2_TABLE__TEMPORAL_TABLE, DB2Table.class, msgs);
			msgs = basicSetHistoryTable(newHistoryTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_TABLE__HISTORY_TABLE, newHistoryTable, newHistoryTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Table getTemporalTable() {
		if (temporalTable != null && temporalTable.eIsProxy()) {
			InternalEObject oldTemporalTable = (InternalEObject)temporalTable;
			temporalTable = (DB2Table)eResolveProxy(oldTemporalTable);
			if (temporalTable != oldTemporalTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_TABLE__TEMPORAL_TABLE, oldTemporalTable, temporalTable));
			}
		}
		return temporalTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Table basicGetTemporalTable() {
		return temporalTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTemporalTable(DB2Table newTemporalTable, NotificationChain msgs) {
		DB2Table oldTemporalTable = temporalTable;
		temporalTable = newTemporalTable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_TABLE__TEMPORAL_TABLE, oldTemporalTable, newTemporalTable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTemporalTable(DB2Table newTemporalTable) {
		if (newTemporalTable != temporalTable) {
			NotificationChain msgs = null;
			if (temporalTable != null)
				msgs = ((InternalEObject)temporalTable).eInverseRemove(this, DB2ModelPackage.DB2_TABLE__HISTORY_TABLE, DB2Table.class, msgs);
			if (newTemporalTable != null)
				msgs = ((InternalEObject)newTemporalTable).eInverseAdd(this, DB2ModelPackage.DB2_TABLE__HISTORY_TABLE, DB2Table.class, msgs);
			msgs = basicSetTemporalTable(newTemporalTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_TABLE__TEMPORAL_TABLE, newTemporalTable, newTemporalTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMasks() {
		if (masks == null) {
			masks = new EObjectWithInverseResolvingEList(DB2Mask.class, this, DB2ModelPackage.DB2_TABLE__MASKS, DB2ModelPackage.DB2_MASK__SUBJECT_TABLE);
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
			permissions = new EObjectWithInverseResolvingEList(DB2Permission.class, this, DB2ModelPackage.DB2_TABLE__PERMISSIONS, DB2ModelPackage.DB2_PERMISSION__SUBJECT_TABLE);
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
			case DB2ModelPackage.DB2_TABLE__PERIODS:
				return ((InternalEList)getPeriods()).basicAdd(otherEnd, msgs);
			case DB2ModelPackage.DB2_TABLE__HISTORY_TABLE:
				if (historyTable != null)
					msgs = ((InternalEObject)historyTable).eInverseRemove(this, DB2ModelPackage.DB2_TABLE__TEMPORAL_TABLE, DB2Table.class, msgs);
				return basicSetHistoryTable((DB2Table)otherEnd, msgs);
			case DB2ModelPackage.DB2_TABLE__TEMPORAL_TABLE:
				if (temporalTable != null)
					msgs = ((InternalEObject)temporalTable).eInverseRemove(this, DB2ModelPackage.DB2_TABLE__HISTORY_TABLE, DB2Table.class, msgs);
				return basicSetTemporalTable((DB2Table)otherEnd, msgs);
			case DB2ModelPackage.DB2_TABLE__MASKS:
				return ((InternalEList)getMasks()).basicAdd(otherEnd, msgs);
			case DB2ModelPackage.DB2_TABLE__PERMISSIONS:
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
			case DB2ModelPackage.DB2_TABLE__PERIODS:
				return ((InternalEList)getPeriods()).basicRemove(otherEnd, msgs);
			case DB2ModelPackage.DB2_TABLE__HISTORY_TABLE:
				return basicSetHistoryTable(null, msgs);
			case DB2ModelPackage.DB2_TABLE__TEMPORAL_TABLE:
				return basicSetTemporalTable(null, msgs);
			case DB2ModelPackage.DB2_TABLE__MASKS:
				return ((InternalEList)getMasks()).basicRemove(otherEnd, msgs);
			case DB2ModelPackage.DB2_TABLE__PERMISSIONS:
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
			case DB2ModelPackage.DB2_TABLE__DATA_CAPTURE:
				return getDataCapture();
			case DB2ModelPackage.DB2_TABLE__ACTIVATE_ROW_ACCESS_CONTROL:
				return isActivateRowAccessControl() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL:
				return isActivateColumnAccessControl() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_TABLE__ORGANIZE_BY:
				return getOrganizeBy();
			case DB2ModelPackage.DB2_TABLE__PACKAGES:
				return getPackages();
			case DB2ModelPackage.DB2_TABLE__PERIODS:
				return getPeriods();
			case DB2ModelPackage.DB2_TABLE__HISTORY_TABLE:
				if (resolve) return getHistoryTable();
				return basicGetHistoryTable();
			case DB2ModelPackage.DB2_TABLE__TEMPORAL_TABLE:
				if (resolve) return getTemporalTable();
				return basicGetTemporalTable();
			case DB2ModelPackage.DB2_TABLE__MASKS:
				return getMasks();
			case DB2ModelPackage.DB2_TABLE__PERMISSIONS:
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
			case DB2ModelPackage.DB2_TABLE__DATA_CAPTURE:
				setDataCapture((DataCaptureType)newValue);
				return;
			case DB2ModelPackage.DB2_TABLE__ACTIVATE_ROW_ACCESS_CONTROL:
				setActivateRowAccessControl(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL:
				setActivateColumnAccessControl(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_TABLE__ORGANIZE_BY:
				setOrganizeBy((DB2TableOrganization)newValue);
				return;
			case DB2ModelPackage.DB2_TABLE__PACKAGES:
				getPackages().clear();
				getPackages().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_TABLE__PERIODS:
				getPeriods().clear();
				getPeriods().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_TABLE__HISTORY_TABLE:
				setHistoryTable((DB2Table)newValue);
				return;
			case DB2ModelPackage.DB2_TABLE__TEMPORAL_TABLE:
				setTemporalTable((DB2Table)newValue);
				return;
			case DB2ModelPackage.DB2_TABLE__MASKS:
				getMasks().clear();
				getMasks().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_TABLE__PERMISSIONS:
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
			case DB2ModelPackage.DB2_TABLE__DATA_CAPTURE:
				setDataCapture(DATA_CAPTURE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_TABLE__ACTIVATE_ROW_ACCESS_CONTROL:
				setActivateRowAccessControl(ACTIVATE_ROW_ACCESS_CONTROL_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL:
				setActivateColumnAccessControl(ACTIVATE_COLUMN_ACCESS_CONTROL_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_TABLE__ORGANIZE_BY:
				setOrganizeBy(ORGANIZE_BY_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_TABLE__PACKAGES:
				getPackages().clear();
				return;
			case DB2ModelPackage.DB2_TABLE__PERIODS:
				getPeriods().clear();
				return;
			case DB2ModelPackage.DB2_TABLE__HISTORY_TABLE:
				setHistoryTable((DB2Table)null);
				return;
			case DB2ModelPackage.DB2_TABLE__TEMPORAL_TABLE:
				setTemporalTable((DB2Table)null);
				return;
			case DB2ModelPackage.DB2_TABLE__MASKS:
				getMasks().clear();
				return;
			case DB2ModelPackage.DB2_TABLE__PERMISSIONS:
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
			case DB2ModelPackage.DB2_TABLE__DATA_CAPTURE:
				return dataCapture != DATA_CAPTURE_EDEFAULT;
			case DB2ModelPackage.DB2_TABLE__ACTIVATE_ROW_ACCESS_CONTROL:
				return activateRowAccessControl != ACTIVATE_ROW_ACCESS_CONTROL_EDEFAULT;
			case DB2ModelPackage.DB2_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL:
				return activateColumnAccessControl != ACTIVATE_COLUMN_ACCESS_CONTROL_EDEFAULT;
			case DB2ModelPackage.DB2_TABLE__ORGANIZE_BY:
				return organizeBy != ORGANIZE_BY_EDEFAULT;
			case DB2ModelPackage.DB2_TABLE__PACKAGES:
				return packages != null && !packages.isEmpty();
			case DB2ModelPackage.DB2_TABLE__PERIODS:
				return periods != null && !periods.isEmpty();
			case DB2ModelPackage.DB2_TABLE__HISTORY_TABLE:
				return historyTable != null;
			case DB2ModelPackage.DB2_TABLE__TEMPORAL_TABLE:
				return temporalTable != null;
			case DB2ModelPackage.DB2_TABLE__MASKS:
				return masks != null && !masks.isEmpty();
			case DB2ModelPackage.DB2_TABLE__PERMISSIONS:
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
		result.append(" (dataCapture: "); //$NON-NLS-1$
		result.append(dataCapture);
		result.append(", activateRowAccessControl: "); //$NON-NLS-1$
		result.append(activateRowAccessControl);
		result.append(", activateColumnAccessControl: "); //$NON-NLS-1$
		result.append(activateColumnAccessControl);
		result.append(", organizeBy: "); //$NON-NLS-1$
		result.append(organizeBy);
		result.append(')');
		return result.toString();
	}

} //DB2TableImpl
