/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2UniqueConstraintExtension;

import org.eclipse.datatools.modelbase.sql.schema.ObjectExtension;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Unique Constraint Extension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2UniqueConstraintExtensionImpl#getSQLObject <em>SQL Object</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2UniqueConstraintExtensionImpl#isBusPeriodWithoutOverlap <em>Bus Period Without Overlap</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2UniqueConstraintExtensionImpl extends SQLObjectImpl implements DB2UniqueConstraintExtension {
	/**
	 * The default value of the '{@link #isBusPeriodWithoutOverlap() <em>Bus Period Without Overlap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBusPeriodWithoutOverlap()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BUS_PERIOD_WITHOUT_OVERLAP_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBusPeriodWithoutOverlap() <em>Bus Period Without Overlap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBusPeriodWithoutOverlap()
	 * @generated
	 * @ordered
	 */
	protected boolean busPeriodWithoutOverlap = BUS_PERIOD_WITHOUT_OVERLAP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2UniqueConstraintExtensionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_UNIQUE_CONSTRAINT_EXTENSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLObject getSQLObject() {
		if (eContainerFeatureID() != DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__SQL_OBJECT) return null;
		return (SQLObject)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSQLObject(SQLObject newSQLObject, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newSQLObject, DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__SQL_OBJECT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSQLObject(SQLObject newSQLObject) {
		if (newSQLObject != eInternalContainer() || (eContainerFeatureID() != DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__SQL_OBJECT && newSQLObject != null)) {
			if (EcoreUtil.isAncestor(this, newSQLObject))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSQLObject != null)
				msgs = ((InternalEObject)newSQLObject).eInverseAdd(this, SQLSchemaPackage.SQL_OBJECT__EXTENSIONS, SQLObject.class, msgs);
			msgs = basicSetSQLObject(newSQLObject, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__SQL_OBJECT, newSQLObject, newSQLObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBusPeriodWithoutOverlap() {
		return busPeriodWithoutOverlap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBusPeriodWithoutOverlap(boolean newBusPeriodWithoutOverlap) {
		boolean oldBusPeriodWithoutOverlap = busPeriodWithoutOverlap;
		busPeriodWithoutOverlap = newBusPeriodWithoutOverlap;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__BUS_PERIOD_WITHOUT_OVERLAP, oldBusPeriodWithoutOverlap, busPeriodWithoutOverlap));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__SQL_OBJECT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSQLObject((SQLObject)otherEnd, msgs);
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
			case DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__SQL_OBJECT:
				return basicSetSQLObject(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__SQL_OBJECT:
				return eInternalContainer().eInverseRemove(this, SQLSchemaPackage.SQL_OBJECT__EXTENSIONS, SQLObject.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__SQL_OBJECT:
				return getSQLObject();
			case DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__BUS_PERIOD_WITHOUT_OVERLAP:
				return isBusPeriodWithoutOverlap() ? Boolean.TRUE : Boolean.FALSE;
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
			case DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__SQL_OBJECT:
				setSQLObject((SQLObject)newValue);
				return;
			case DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__BUS_PERIOD_WITHOUT_OVERLAP:
				setBusPeriodWithoutOverlap(((Boolean)newValue).booleanValue());
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
			case DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__SQL_OBJECT:
				setSQLObject((SQLObject)null);
				return;
			case DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__BUS_PERIOD_WITHOUT_OVERLAP:
				setBusPeriodWithoutOverlap(BUS_PERIOD_WITHOUT_OVERLAP_EDEFAULT);
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
			case DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__SQL_OBJECT:
				return getSQLObject() != null;
			case DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__BUS_PERIOD_WITHOUT_OVERLAP:
				return busPeriodWithoutOverlap != BUS_PERIOD_WITHOUT_OVERLAP_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == ObjectExtension.class) {
			switch (derivedFeatureID) {
				case DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__SQL_OBJECT: return SQLSchemaPackage.OBJECT_EXTENSION__SQL_OBJECT;
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
		if (baseClass == ObjectExtension.class) {
			switch (baseFeatureID) {
				case SQLSchemaPackage.OBJECT_EXTENSION__SQL_OBJECT: return DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION__SQL_OBJECT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (busPeriodWithoutOverlap: "); //$NON-NLS-1$
		result.append(busPeriodWithoutOverlap);
		result.append(')');
		return result.toString();
	}

} //DB2UniqueConstraintExtensionImpl
