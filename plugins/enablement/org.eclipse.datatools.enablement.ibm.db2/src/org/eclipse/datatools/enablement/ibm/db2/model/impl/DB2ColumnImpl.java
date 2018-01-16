/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2ColumnImpl.java,v 1.9 2008/04/28 20:54:22 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Column;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Period;
import org.eclipse.datatools.enablement.ibm.db2.model.GenerateType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ColumnImpl#getGenerationType <em>Generation Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ColumnImpl#isRowChangeTimestamp <em>Row Change Timestamp</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ColumnImpl#isRowBegin <em>Row Begin</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ColumnImpl#isRowEnd <em>Row End</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ColumnImpl#isTransStartID <em>Trans Start ID</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ColumnImpl#getBeginPeriod <em>Begin Period</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ColumnImpl#getEndPeriod <em>End Period</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2ColumnImpl extends ColumnImpl implements DB2Column {
	/**
	 * The default value of the '{@link #getGenerationType() <em>Generation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenerationType()
	 * @generated
	 * @ordered
	 */
	protected static final GenerateType GENERATION_TYPE_EDEFAULT = GenerateType.ALWAYS_LITERAL;

	/**
	 * The cached value of the '{@link #getGenerationType() <em>Generation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenerationType()
	 * @generated
	 * @ordered
	 */
	protected GenerateType generationType = GENERATION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isRowChangeTimestamp() <em>Row Change Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowChangeTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ROW_CHANGE_TIMESTAMP_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRowChangeTimestamp() <em>Row Change Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowChangeTimestamp()
	 * @generated
	 * @ordered
	 */
	protected boolean rowChangeTimestamp = ROW_CHANGE_TIMESTAMP_EDEFAULT;

	/**
	 * The default value of the '{@link #isRowBegin() <em>Row Begin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowBegin()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ROW_BEGIN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRowBegin() <em>Row Begin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowBegin()
	 * @generated
	 * @ordered
	 */
	protected boolean rowBegin = ROW_BEGIN_EDEFAULT;

	/**
	 * The default value of the '{@link #isRowEnd() <em>Row End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowEnd()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ROW_END_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRowEnd() <em>Row End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowEnd()
	 * @generated
	 * @ordered
	 */
	protected boolean rowEnd = ROW_END_EDEFAULT;

	/**
	 * The default value of the '{@link #isTransStartID() <em>Trans Start ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTransStartID()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRANS_START_ID_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTransStartID() <em>Trans Start ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTransStartID()
	 * @generated
	 * @ordered
	 */
	protected boolean transStartID = TRANS_START_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBeginPeriod() <em>Begin Period</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBeginPeriod()
	 * @generated
	 * @ordered
	 */
	protected DB2Period beginPeriod;

	/**
	 * The cached value of the '{@link #getEndPeriod() <em>End Period</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndPeriod()
	 * @generated
	 * @ordered
	 */
	protected DB2Period endPeriod;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2ColumnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_COLUMN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenerateType getGenerationType() {
		return generationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGenerationType(GenerateType newGenerationType) {
		GenerateType oldGenerationType = generationType;
		generationType = newGenerationType == null ? GENERATION_TYPE_EDEFAULT : newGenerationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_COLUMN__GENERATION_TYPE, oldGenerationType, generationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRowChangeTimestamp() {
		return rowChangeTimestamp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRowChangeTimestamp(boolean newRowChangeTimestamp) {
		boolean oldRowChangeTimestamp = rowChangeTimestamp;
		rowChangeTimestamp = newRowChangeTimestamp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_COLUMN__ROW_CHANGE_TIMESTAMP, oldRowChangeTimestamp, rowChangeTimestamp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRowBegin() {
		return rowBegin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRowBegin(boolean newRowBegin) {
		boolean oldRowBegin = rowBegin;
		rowBegin = newRowBegin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_COLUMN__ROW_BEGIN, oldRowBegin, rowBegin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRowEnd() {
		return rowEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRowEnd(boolean newRowEnd) {
		boolean oldRowEnd = rowEnd;
		rowEnd = newRowEnd;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_COLUMN__ROW_END, oldRowEnd, rowEnd));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTransStartID() {
		return transStartID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransStartID(boolean newTransStartID) {
		boolean oldTransStartID = transStartID;
		transStartID = newTransStartID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_COLUMN__TRANS_START_ID, oldTransStartID, transStartID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Period getBeginPeriod() {
		if (beginPeriod != null && beginPeriod.eIsProxy()) {
			InternalEObject oldBeginPeriod = (InternalEObject)beginPeriod;
			beginPeriod = (DB2Period)eResolveProxy(oldBeginPeriod);
			if (beginPeriod != oldBeginPeriod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_COLUMN__BEGIN_PERIOD, oldBeginPeriod, beginPeriod));
			}
		}
		return beginPeriod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Period basicGetBeginPeriod() {
		return beginPeriod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBeginPeriod(DB2Period newBeginPeriod, NotificationChain msgs) {
		DB2Period oldBeginPeriod = beginPeriod;
		beginPeriod = newBeginPeriod;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_COLUMN__BEGIN_PERIOD, oldBeginPeriod, newBeginPeriod);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBeginPeriod(DB2Period newBeginPeriod) {
		if (newBeginPeriod != beginPeriod) {
			NotificationChain msgs = null;
			if (beginPeriod != null)
				msgs = ((InternalEObject)beginPeriod).eInverseRemove(this, DB2ModelPackage.DB2_PERIOD__BEGIN_COLUMN, DB2Period.class, msgs);
			if (newBeginPeriod != null)
				msgs = ((InternalEObject)newBeginPeriod).eInverseAdd(this, DB2ModelPackage.DB2_PERIOD__BEGIN_COLUMN, DB2Period.class, msgs);
			msgs = basicSetBeginPeriod(newBeginPeriod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_COLUMN__BEGIN_PERIOD, newBeginPeriod, newBeginPeriod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Period getEndPeriod() {
		if (endPeriod != null && endPeriod.eIsProxy()) {
			InternalEObject oldEndPeriod = (InternalEObject)endPeriod;
			endPeriod = (DB2Period)eResolveProxy(oldEndPeriod);
			if (endPeriod != oldEndPeriod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_COLUMN__END_PERIOD, oldEndPeriod, endPeriod));
			}
		}
		return endPeriod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Period basicGetEndPeriod() {
		return endPeriod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEndPeriod(DB2Period newEndPeriod, NotificationChain msgs) {
		DB2Period oldEndPeriod = endPeriod;
		endPeriod = newEndPeriod;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_COLUMN__END_PERIOD, oldEndPeriod, newEndPeriod);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndPeriod(DB2Period newEndPeriod) {
		if (newEndPeriod != endPeriod) {
			NotificationChain msgs = null;
			if (endPeriod != null)
				msgs = ((InternalEObject)endPeriod).eInverseRemove(this, DB2ModelPackage.DB2_PERIOD__END_COLUMN, DB2Period.class, msgs);
			if (newEndPeriod != null)
				msgs = ((InternalEObject)newEndPeriod).eInverseAdd(this, DB2ModelPackage.DB2_PERIOD__END_COLUMN, DB2Period.class, msgs);
			msgs = basicSetEndPeriod(newEndPeriod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_COLUMN__END_PERIOD, newEndPeriod, newEndPeriod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_COLUMN__BEGIN_PERIOD:
				if (beginPeriod != null)
					msgs = ((InternalEObject)beginPeriod).eInverseRemove(this, DB2ModelPackage.DB2_PERIOD__BEGIN_COLUMN, DB2Period.class, msgs);
				return basicSetBeginPeriod((DB2Period)otherEnd, msgs);
			case DB2ModelPackage.DB2_COLUMN__END_PERIOD:
				if (endPeriod != null)
					msgs = ((InternalEObject)endPeriod).eInverseRemove(this, DB2ModelPackage.DB2_PERIOD__END_COLUMN, DB2Period.class, msgs);
				return basicSetEndPeriod((DB2Period)otherEnd, msgs);
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
			case DB2ModelPackage.DB2_COLUMN__BEGIN_PERIOD:
				return basicSetBeginPeriod(null, msgs);
			case DB2ModelPackage.DB2_COLUMN__END_PERIOD:
				return basicSetEndPeriod(null, msgs);
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
			case DB2ModelPackage.DB2_COLUMN__GENERATION_TYPE:
				return getGenerationType();
			case DB2ModelPackage.DB2_COLUMN__ROW_CHANGE_TIMESTAMP:
				return isRowChangeTimestamp() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_COLUMN__ROW_BEGIN:
				return isRowBegin() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_COLUMN__ROW_END:
				return isRowEnd() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_COLUMN__TRANS_START_ID:
				return isTransStartID() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_COLUMN__BEGIN_PERIOD:
				if (resolve) return getBeginPeriod();
				return basicGetBeginPeriod();
			case DB2ModelPackage.DB2_COLUMN__END_PERIOD:
				if (resolve) return getEndPeriod();
				return basicGetEndPeriod();
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
			case DB2ModelPackage.DB2_COLUMN__GENERATION_TYPE:
				setGenerationType((GenerateType)newValue);
				return;
			case DB2ModelPackage.DB2_COLUMN__ROW_CHANGE_TIMESTAMP:
				setRowChangeTimestamp(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_COLUMN__ROW_BEGIN:
				setRowBegin(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_COLUMN__ROW_END:
				setRowEnd(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_COLUMN__TRANS_START_ID:
				setTransStartID(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_COLUMN__BEGIN_PERIOD:
				setBeginPeriod((DB2Period)newValue);
				return;
			case DB2ModelPackage.DB2_COLUMN__END_PERIOD:
				setEndPeriod((DB2Period)newValue);
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
			case DB2ModelPackage.DB2_COLUMN__GENERATION_TYPE:
				setGenerationType(GENERATION_TYPE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_COLUMN__ROW_CHANGE_TIMESTAMP:
				setRowChangeTimestamp(ROW_CHANGE_TIMESTAMP_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_COLUMN__ROW_BEGIN:
				setRowBegin(ROW_BEGIN_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_COLUMN__ROW_END:
				setRowEnd(ROW_END_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_COLUMN__TRANS_START_ID:
				setTransStartID(TRANS_START_ID_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_COLUMN__BEGIN_PERIOD:
				setBeginPeriod((DB2Period)null);
				return;
			case DB2ModelPackage.DB2_COLUMN__END_PERIOD:
				setEndPeriod((DB2Period)null);
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
			case DB2ModelPackage.DB2_COLUMN__GENERATION_TYPE:
				return generationType != GENERATION_TYPE_EDEFAULT;
			case DB2ModelPackage.DB2_COLUMN__ROW_CHANGE_TIMESTAMP:
				return rowChangeTimestamp != ROW_CHANGE_TIMESTAMP_EDEFAULT;
			case DB2ModelPackage.DB2_COLUMN__ROW_BEGIN:
				return rowBegin != ROW_BEGIN_EDEFAULT;
			case DB2ModelPackage.DB2_COLUMN__ROW_END:
				return rowEnd != ROW_END_EDEFAULT;
			case DB2ModelPackage.DB2_COLUMN__TRANS_START_ID:
				return transStartID != TRANS_START_ID_EDEFAULT;
			case DB2ModelPackage.DB2_COLUMN__BEGIN_PERIOD:
				return beginPeriod != null;
			case DB2ModelPackage.DB2_COLUMN__END_PERIOD:
				return endPeriod != null;
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
		result.append(" (generationType: "); //$NON-NLS-1$
		result.append(generationType);
		result.append(", rowChangeTimestamp: "); //$NON-NLS-1$
		result.append(rowChangeTimestamp);
		result.append(", rowBegin: "); //$NON-NLS-1$
		result.append(rowBegin);
		result.append(", rowEnd: "); //$NON-NLS-1$
		result.append(rowEnd);
		result.append(", transStartID: "); //$NON-NLS-1$
		result.append(transStartID);
		result.append(')');
		return result.toString();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isGenerated() {
		if ( this.isRowChangeTimestamp() ||
			 this.isRowBegin()			 ||
			 this.isRowEnd()			 ||
			 this.isTransStartID()		 ||
			 this.getGenerateExpression() != null ||
			 this.getIdentitySpecifier() != null )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

} //DB2ColumnImpl
