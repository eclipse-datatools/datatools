/**
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionEveryClauseElement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Partition Every Clause Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionEveryClauseElementImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionEveryClauseElementImpl#getDuration <em>Duration</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionEveryClauseElementImpl#getLUWPartitionElement <em>LUW Partition Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWPartitionEveryClauseElementImpl extends EObjectImpl implements LUWPartitionEveryClauseElement {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final Double VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected Double value = VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected static final String DURATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected String duration = DURATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWPartitionEveryClauseElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_PARTITION_EVERY_CLAUSE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(Double newValue) {
		Double oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(String newDuration) {
		String oldDuration = duration;
		duration = newDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__DURATION, oldDuration, duration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWPartitionElement getLUWPartitionElement() {
		if (eContainerFeatureID() != LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT) return null;
		return (LUWPartitionElement)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLUWPartitionElement(LUWPartitionElement newLUWPartitionElement, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLUWPartitionElement, LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLUWPartitionElement(LUWPartitionElement newLUWPartitionElement) {
		if (newLUWPartitionElement != eInternalContainer() || (eContainerFeatureID() != LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT && newLUWPartitionElement != null)) {
			if (EcoreUtil.isAncestor(this, newLUWPartitionElement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLUWPartitionElement != null)
				msgs = ((InternalEObject)newLUWPartitionElement).eInverseAdd(this, LUWPackage.LUW_PARTITION_ELEMENT__EVERY_CLAUSE, LUWPartitionElement.class, msgs);
			msgs = basicSetLUWPartitionElement(newLUWPartitionElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT, newLUWPartitionElement, newLUWPartitionElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLUWPartitionElement((LUWPartitionElement)otherEnd, msgs);
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
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT:
				return basicSetLUWPartitionElement(null, msgs);
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
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT:
				return eInternalContainer().eInverseRemove(this, LUWPackage.LUW_PARTITION_ELEMENT__EVERY_CLAUSE, LUWPartitionElement.class, msgs);
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
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__VALUE:
				return getValue();
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__DURATION:
				return getDuration();
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT:
				return getLUWPartitionElement();
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
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__VALUE:
				setValue((Double)newValue);
				return;
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__DURATION:
				setDuration((String)newValue);
				return;
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT:
				setLUWPartitionElement((LUWPartitionElement)newValue);
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
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__DURATION:
				setDuration(DURATION_EDEFAULT);
				return;
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT:
				setLUWPartitionElement((LUWPartitionElement)null);
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
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__DURATION:
				return DURATION_EDEFAULT == null ? duration != null : !DURATION_EDEFAULT.equals(duration);
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT:
				return getLUWPartitionElement() != null;
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
		result.append(" (value: "); //$NON-NLS-1$
		result.append(value);
		result.append(", duration: "); //$NON-NLS-1$
		result.append(duration);
		result.append(')');
		return result.toString();
	}

} //LUWPartitionEveryClauseElementImpl
