/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Index;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2MultidimensionalIndex;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Index</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IndexImpl#getIndexType <em>Index Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IndexImpl#isBusPeriodWithoutOverlap <em>Bus Period Without Overlap</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IndexImpl#isEncodedVector <em>Encoded Vector</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IndexImpl#getDB2MultidimensionalIndex <em>DB2 Multidimensional Index</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2IndexImpl extends IndexImpl implements DB2Index {
	/**
	 * The default value of the '{@link #getIndexType() <em>Index Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexType()
	 * @generated
	 * @ordered
	 */
	protected static final DB2IndexType INDEX_TYPE_EDEFAULT = DB2IndexType.REGULAR_LITERAL;

	/**
	 * The cached value of the '{@link #getIndexType() <em>Index Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexType()
	 * @generated
	 * @ordered
	 */
	protected DB2IndexType indexType = INDEX_TYPE_EDEFAULT;

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
	 * The default value of the '{@link #isEncodedVector() <em>Encoded Vector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEncodedVector()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENCODED_VECTOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEncodedVector() <em>Encoded Vector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEncodedVector()
	 * @generated
	 * @ordered
	 */
	protected boolean encodedVector = ENCODED_VECTOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDB2MultidimensionalIndex() <em>DB2 Multidimensional Index</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDB2MultidimensionalIndex()
	 * @generated
	 * @ordered
	 */
	protected DB2MultidimensionalIndex db2MultidimensionalIndex;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2IndexImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_INDEX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2IndexType getIndexType() {
		return indexType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndexType(DB2IndexType newIndexType) {
		DB2IndexType oldIndexType = indexType;
		indexType = newIndexType == null ? INDEX_TYPE_EDEFAULT : newIndexType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_INDEX__INDEX_TYPE, oldIndexType, indexType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_INDEX__BUS_PERIOD_WITHOUT_OVERLAP, oldBusPeriodWithoutOverlap, busPeriodWithoutOverlap));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEncodedVector() {
		return encodedVector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEncodedVector(boolean newEncodedVector) {
		boolean oldEncodedVector = encodedVector;
		encodedVector = newEncodedVector;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_INDEX__ENCODED_VECTOR, oldEncodedVector, encodedVector));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2MultidimensionalIndex getDB2MultidimensionalIndex() {
		if (db2MultidimensionalIndex != null && db2MultidimensionalIndex.eIsProxy()) {
			InternalEObject oldDB2MultidimensionalIndex = (InternalEObject)db2MultidimensionalIndex;
			db2MultidimensionalIndex = (DB2MultidimensionalIndex)eResolveProxy(oldDB2MultidimensionalIndex);
			if (db2MultidimensionalIndex != oldDB2MultidimensionalIndex) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_INDEX__DB2_MULTIDIMENSIONAL_INDEX, oldDB2MultidimensionalIndex, db2MultidimensionalIndex));
			}
		}
		return db2MultidimensionalIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2MultidimensionalIndex basicGetDB2MultidimensionalIndex() {
		return db2MultidimensionalIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDB2MultidimensionalIndex(DB2MultidimensionalIndex newDB2MultidimensionalIndex, NotificationChain msgs) {
		DB2MultidimensionalIndex oldDB2MultidimensionalIndex = db2MultidimensionalIndex;
		db2MultidimensionalIndex = newDB2MultidimensionalIndex;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_INDEX__DB2_MULTIDIMENSIONAL_INDEX, oldDB2MultidimensionalIndex, newDB2MultidimensionalIndex);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDB2MultidimensionalIndex(DB2MultidimensionalIndex newDB2MultidimensionalIndex) {
		if (newDB2MultidimensionalIndex != db2MultidimensionalIndex) {
			NotificationChain msgs = null;
			if (db2MultidimensionalIndex != null)
				msgs = ((InternalEObject)db2MultidimensionalIndex).eInverseRemove(this, DB2ModelPackage.DB2_MULTIDIMENSIONAL_INDEX__DIMENSION_INDEXES, DB2MultidimensionalIndex.class, msgs);
			if (newDB2MultidimensionalIndex != null)
				msgs = ((InternalEObject)newDB2MultidimensionalIndex).eInverseAdd(this, DB2ModelPackage.DB2_MULTIDIMENSIONAL_INDEX__DIMENSION_INDEXES, DB2MultidimensionalIndex.class, msgs);
			msgs = basicSetDB2MultidimensionalIndex(newDB2MultidimensionalIndex, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_INDEX__DB2_MULTIDIMENSIONAL_INDEX, newDB2MultidimensionalIndex, newDB2MultidimensionalIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_INDEX__DB2_MULTIDIMENSIONAL_INDEX:
				if (db2MultidimensionalIndex != null)
					msgs = ((InternalEObject)db2MultidimensionalIndex).eInverseRemove(this, DB2ModelPackage.DB2_MULTIDIMENSIONAL_INDEX__DIMENSION_INDEXES, DB2MultidimensionalIndex.class, msgs);
				return basicSetDB2MultidimensionalIndex((DB2MultidimensionalIndex)otherEnd, msgs);
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
			case DB2ModelPackage.DB2_INDEX__DB2_MULTIDIMENSIONAL_INDEX:
				return basicSetDB2MultidimensionalIndex(null, msgs);
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
			case DB2ModelPackage.DB2_INDEX__INDEX_TYPE:
				return getIndexType();
			case DB2ModelPackage.DB2_INDEX__BUS_PERIOD_WITHOUT_OVERLAP:
				return isBusPeriodWithoutOverlap() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_INDEX__ENCODED_VECTOR:
				return isEncodedVector() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_INDEX__DB2_MULTIDIMENSIONAL_INDEX:
				if (resolve) return getDB2MultidimensionalIndex();
				return basicGetDB2MultidimensionalIndex();
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
			case DB2ModelPackage.DB2_INDEX__INDEX_TYPE:
				setIndexType((DB2IndexType)newValue);
				return;
			case DB2ModelPackage.DB2_INDEX__BUS_PERIOD_WITHOUT_OVERLAP:
				setBusPeriodWithoutOverlap(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_INDEX__ENCODED_VECTOR:
				setEncodedVector(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_INDEX__DB2_MULTIDIMENSIONAL_INDEX:
				setDB2MultidimensionalIndex((DB2MultidimensionalIndex)newValue);
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
			case DB2ModelPackage.DB2_INDEX__INDEX_TYPE:
				setIndexType(INDEX_TYPE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_INDEX__BUS_PERIOD_WITHOUT_OVERLAP:
				setBusPeriodWithoutOverlap(BUS_PERIOD_WITHOUT_OVERLAP_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_INDEX__ENCODED_VECTOR:
				setEncodedVector(ENCODED_VECTOR_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_INDEX__DB2_MULTIDIMENSIONAL_INDEX:
				setDB2MultidimensionalIndex((DB2MultidimensionalIndex)null);
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
			case DB2ModelPackage.DB2_INDEX__INDEX_TYPE:
				return indexType != INDEX_TYPE_EDEFAULT;
			case DB2ModelPackage.DB2_INDEX__BUS_PERIOD_WITHOUT_OVERLAP:
				return busPeriodWithoutOverlap != BUS_PERIOD_WITHOUT_OVERLAP_EDEFAULT;
			case DB2ModelPackage.DB2_INDEX__ENCODED_VECTOR:
				return encodedVector != ENCODED_VECTOR_EDEFAULT;
			case DB2ModelPackage.DB2_INDEX__DB2_MULTIDIMENSIONAL_INDEX:
				return db2MultidimensionalIndex != null;
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
		result.append(" (indexType: "); //$NON-NLS-1$
		result.append(indexType);
		result.append(", busPeriodWithoutOverlap: "); //$NON-NLS-1$
		result.append(busPeriodWithoutOverlap);
		result.append(", encodedVector: "); //$NON-NLS-1$
		result.append(encodedVector);
		result.append(')');
		return result.toString();
	}

} //DB2IndexImpl
