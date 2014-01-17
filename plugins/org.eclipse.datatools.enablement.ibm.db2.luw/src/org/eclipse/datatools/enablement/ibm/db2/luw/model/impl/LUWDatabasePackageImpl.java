/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWDatabasePackageImpl.java,v 1.4 2009/03/16 21:08:36 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.CursorBlockType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.ExplainSnaphotType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Database Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePackageImpl#getCreator <em>Creator</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePackageImpl#getBinder <em>Binder</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePackageImpl#getCursorBlock <em>Cursor Block</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePackageImpl#getNumberOfSections <em>Number Of Sections</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePackageImpl#getOptimizationClass <em>Optimization Class</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePackageImpl#getExplainSnapshot <em>Explain Snapshot</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWDatabasePackageImpl extends DB2PackageImpl implements LUWDatabasePackage {
	/**
	 * The default value of the '{@link #getCreator() <em>Creator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreator()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATOR_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getCreator() <em>Creator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreator()
	 * @generated
	 * @ordered
	 */
	protected String creator = CREATOR_EDEFAULT;
	/**
	 * The default value of the '{@link #getBinder() <em>Binder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinder()
	 * @generated
	 * @ordered
	 */
	protected static final String BINDER_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getBinder() <em>Binder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinder()
	 * @generated
	 * @ordered
	 */
	protected String binder = BINDER_EDEFAULT;
	/**
	 * The default value of the '{@link #getCursorBlock() <em>Cursor Block</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCursorBlock()
	 * @generated
	 * @ordered
	 */
	protected static final CursorBlockType CURSOR_BLOCK_EDEFAULT = CursorBlockType.BLOCK_UNAMBIGUOUS_CURSORS_LITERAL;
	/**
	 * The cached value of the '{@link #getCursorBlock() <em>Cursor Block</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCursorBlock()
	 * @generated
	 * @ordered
	 */
	protected CursorBlockType cursorBlock = CURSOR_BLOCK_EDEFAULT;
	/**
	 * The default value of the '{@link #getNumberOfSections() <em>Number Of Sections</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfSections()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_OF_SECTIONS_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getNumberOfSections() <em>Number Of Sections</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfSections()
	 * @generated
	 * @ordered
	 */
	protected int numberOfSections = NUMBER_OF_SECTIONS_EDEFAULT;
	/**
	 * The default value of the '{@link #getOptimizationClass() <em>Optimization Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptimizationClass()
	 * @generated
	 * @ordered
	 */
	protected static final int OPTIMIZATION_CLASS_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getOptimizationClass() <em>Optimization Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptimizationClass()
	 * @generated
	 * @ordered
	 */
	protected int optimizationClass = OPTIMIZATION_CLASS_EDEFAULT;
	/**
	 * The default value of the '{@link #getExplainSnapshot() <em>Explain Snapshot</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExplainSnapshot()
	 * @generated
	 * @ordered
	 */
	protected static final ExplainSnaphotType EXPLAIN_SNAPSHOT_EDEFAULT = ExplainSnaphotType.ALL_LITERAL;
	/**
	 * The cached value of the '{@link #getExplainSnapshot() <em>Explain Snapshot</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExplainSnapshot()
	 * @generated
	 * @ordered
	 */
	protected ExplainSnaphotType explainSnapshot = EXPLAIN_SNAPSHOT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWDatabasePackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_DATABASE_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreator(String newCreator) {
		String oldCreator = creator;
		creator = newCreator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PACKAGE__CREATOR, oldCreator, creator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBinder() {
		return binder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinder(String newBinder) {
		String oldBinder = binder;
		binder = newBinder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PACKAGE__BINDER, oldBinder, binder));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CursorBlockType getCursorBlock() {
		return cursorBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCursorBlock(CursorBlockType newCursorBlock) {
		CursorBlockType oldCursorBlock = cursorBlock;
		cursorBlock = newCursorBlock == null ? CURSOR_BLOCK_EDEFAULT : newCursorBlock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PACKAGE__CURSOR_BLOCK, oldCursorBlock, cursorBlock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumberOfSections() {
		return numberOfSections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumberOfSections(int newNumberOfSections) {
		int oldNumberOfSections = numberOfSections;
		numberOfSections = newNumberOfSections;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PACKAGE__NUMBER_OF_SECTIONS, oldNumberOfSections, numberOfSections));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOptimizationClass() {
		return optimizationClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOptimizationClass(int newOptimizationClass) {
		int oldOptimizationClass = optimizationClass;
		optimizationClass = newOptimizationClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PACKAGE__OPTIMIZATION_CLASS, oldOptimizationClass, optimizationClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplainSnaphotType getExplainSnapshot() {
		return explainSnapshot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExplainSnapshot(ExplainSnaphotType newExplainSnapshot) {
		ExplainSnaphotType oldExplainSnapshot = explainSnapshot;
		explainSnapshot = newExplainSnapshot == null ? EXPLAIN_SNAPSHOT_EDEFAULT : newExplainSnapshot;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PACKAGE__EXPLAIN_SNAPSHOT, oldExplainSnapshot, explainSnapshot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_DATABASE_PACKAGE__CREATOR:
				return getCreator();
			case LUWPackage.LUW_DATABASE_PACKAGE__BINDER:
				return getBinder();
			case LUWPackage.LUW_DATABASE_PACKAGE__CURSOR_BLOCK:
				return getCursorBlock();
			case LUWPackage.LUW_DATABASE_PACKAGE__NUMBER_OF_SECTIONS:
				return new Integer(getNumberOfSections());
			case LUWPackage.LUW_DATABASE_PACKAGE__OPTIMIZATION_CLASS:
				return new Integer(getOptimizationClass());
			case LUWPackage.LUW_DATABASE_PACKAGE__EXPLAIN_SNAPSHOT:
				return getExplainSnapshot();
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
			case LUWPackage.LUW_DATABASE_PACKAGE__CREATOR:
				setCreator((String)newValue);
				return;
			case LUWPackage.LUW_DATABASE_PACKAGE__BINDER:
				setBinder((String)newValue);
				return;
			case LUWPackage.LUW_DATABASE_PACKAGE__CURSOR_BLOCK:
				setCursorBlock((CursorBlockType)newValue);
				return;
			case LUWPackage.LUW_DATABASE_PACKAGE__NUMBER_OF_SECTIONS:
				setNumberOfSections(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_DATABASE_PACKAGE__OPTIMIZATION_CLASS:
				setOptimizationClass(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_DATABASE_PACKAGE__EXPLAIN_SNAPSHOT:
				setExplainSnapshot((ExplainSnaphotType)newValue);
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
			case LUWPackage.LUW_DATABASE_PACKAGE__CREATOR:
				setCreator(CREATOR_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE_PACKAGE__BINDER:
				setBinder(BINDER_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE_PACKAGE__CURSOR_BLOCK:
				setCursorBlock(CURSOR_BLOCK_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE_PACKAGE__NUMBER_OF_SECTIONS:
				setNumberOfSections(NUMBER_OF_SECTIONS_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE_PACKAGE__OPTIMIZATION_CLASS:
				setOptimizationClass(OPTIMIZATION_CLASS_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE_PACKAGE__EXPLAIN_SNAPSHOT:
				setExplainSnapshot(EXPLAIN_SNAPSHOT_EDEFAULT);
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
			case LUWPackage.LUW_DATABASE_PACKAGE__CREATOR:
				return CREATOR_EDEFAULT == null ? creator != null : !CREATOR_EDEFAULT.equals(creator);
			case LUWPackage.LUW_DATABASE_PACKAGE__BINDER:
				return BINDER_EDEFAULT == null ? binder != null : !BINDER_EDEFAULT.equals(binder);
			case LUWPackage.LUW_DATABASE_PACKAGE__CURSOR_BLOCK:
				return cursorBlock != CURSOR_BLOCK_EDEFAULT;
			case LUWPackage.LUW_DATABASE_PACKAGE__NUMBER_OF_SECTIONS:
				return numberOfSections != NUMBER_OF_SECTIONS_EDEFAULT;
			case LUWPackage.LUW_DATABASE_PACKAGE__OPTIMIZATION_CLASS:
				return optimizationClass != OPTIMIZATION_CLASS_EDEFAULT;
			case LUWPackage.LUW_DATABASE_PACKAGE__EXPLAIN_SNAPSHOT:
				return explainSnapshot != EXPLAIN_SNAPSHOT_EDEFAULT;
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
		result.append(" (creator: "); //$NON-NLS-1$
		result.append(creator);
		result.append(", binder: "); //$NON-NLS-1$
		result.append(binder);
		result.append(", cursorBlock: "); //$NON-NLS-1$
		result.append(cursorBlock);
		result.append(", numberOfSections: "); //$NON-NLS-1$
		result.append(numberOfSections);
		result.append(", optimizationClass: "); //$NON-NLS-1$
		result.append(optimizationClass);
		result.append(", explainSnapshot: "); //$NON-NLS-1$
		result.append(explainSnapshot);
		result.append(')');
		return result.toString();
	}

} //LUWDatabasePackageImpl
