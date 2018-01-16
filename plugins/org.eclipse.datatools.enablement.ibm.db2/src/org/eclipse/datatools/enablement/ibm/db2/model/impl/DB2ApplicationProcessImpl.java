/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Transaction;
import org.eclipse.datatools.enablement.ibm.db2.model.IsolationLevelType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Application Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ApplicationProcessImpl#getIsolationLevel <em>Isolation Level</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ApplicationProcessImpl#getUnitOfWork <em>Unit Of Work</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2ApplicationProcessImpl extends SQLObjectImpl implements DB2ApplicationProcess {
	/**
	 * The default value of the '{@link #getIsolationLevel() <em>Isolation Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsolationLevel()
	 * @generated
	 * @ordered
	 */
	protected static final IsolationLevelType ISOLATION_LEVEL_EDEFAULT = IsolationLevelType.REPEATABLE_READ_LITERAL;

	/**
	 * The cached value of the '{@link #getIsolationLevel() <em>Isolation Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsolationLevel()
	 * @generated
	 * @ordered
	 */
	protected IsolationLevelType isolationLevel = ISOLATION_LEVEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getUnitOfWork() <em>Unit Of Work</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnitOfWork()
	 * @generated
	 * @ordered
	 */
	protected DB2Transaction unitOfWork;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2ApplicationProcessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_APPLICATION_PROCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IsolationLevelType getIsolationLevel() {
		return isolationLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsolationLevel(IsolationLevelType newIsolationLevel) {
		IsolationLevelType oldIsolationLevel = isolationLevel;
		isolationLevel = newIsolationLevel == null ? ISOLATION_LEVEL_EDEFAULT : newIsolationLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_APPLICATION_PROCESS__ISOLATION_LEVEL, oldIsolationLevel, isolationLevel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Transaction getUnitOfWork() {
		return unitOfWork;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUnitOfWork(DB2Transaction newUnitOfWork, NotificationChain msgs) {
		DB2Transaction oldUnitOfWork = unitOfWork;
		unitOfWork = newUnitOfWork;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_APPLICATION_PROCESS__UNIT_OF_WORK, oldUnitOfWork, newUnitOfWork);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnitOfWork(DB2Transaction newUnitOfWork) {
		if (newUnitOfWork != unitOfWork) {
			NotificationChain msgs = null;
			if (unitOfWork != null)
				msgs = ((InternalEObject)unitOfWork).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DB2ModelPackage.DB2_APPLICATION_PROCESS__UNIT_OF_WORK, null, msgs);
			if (newUnitOfWork != null)
				msgs = ((InternalEObject)newUnitOfWork).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DB2ModelPackage.DB2_APPLICATION_PROCESS__UNIT_OF_WORK, null, msgs);
			msgs = basicSetUnitOfWork(newUnitOfWork, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_APPLICATION_PROCESS__UNIT_OF_WORK, newUnitOfWork, newUnitOfWork));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_APPLICATION_PROCESS__UNIT_OF_WORK:
				return basicSetUnitOfWork(null, msgs);
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
			case DB2ModelPackage.DB2_APPLICATION_PROCESS__ISOLATION_LEVEL:
				return getIsolationLevel();
			case DB2ModelPackage.DB2_APPLICATION_PROCESS__UNIT_OF_WORK:
				return getUnitOfWork();
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
			case DB2ModelPackage.DB2_APPLICATION_PROCESS__ISOLATION_LEVEL:
				setIsolationLevel((IsolationLevelType)newValue);
				return;
			case DB2ModelPackage.DB2_APPLICATION_PROCESS__UNIT_OF_WORK:
				setUnitOfWork((DB2Transaction)newValue);
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
			case DB2ModelPackage.DB2_APPLICATION_PROCESS__ISOLATION_LEVEL:
				setIsolationLevel(ISOLATION_LEVEL_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_APPLICATION_PROCESS__UNIT_OF_WORK:
				setUnitOfWork((DB2Transaction)null);
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
			case DB2ModelPackage.DB2_APPLICATION_PROCESS__ISOLATION_LEVEL:
				return isolationLevel != ISOLATION_LEVEL_EDEFAULT;
			case DB2ModelPackage.DB2_APPLICATION_PROCESS__UNIT_OF_WORK:
				return unitOfWork != null;
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
		result.append(" (isolationLevel: "); //$NON-NLS-1$
		result.append(isolationLevel);
		result.append(')');
		return result.toString();
	}

} //DB2ApplicationProcessImpl
