/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.ArrayIndexElementType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

import org.eclipse.datatools.modelbase.sql.datatypes.impl.ElementTypeImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array Index Element Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.ArrayIndexElementTypeImpl#getLUWArrayDataType <em>LUW Array Data Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArrayIndexElementTypeImpl extends ElementTypeImpl implements ArrayIndexElementType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayIndexElementTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.ARRAY_INDEX_ELEMENT_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWArrayDataType getLUWArrayDataType() {
		if (eContainerFeatureID() != LUWPackage.ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE) return null;
		return (LUWArrayDataType)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLUWArrayDataType(LUWArrayDataType newLUWArrayDataType, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLUWArrayDataType, LUWPackage.ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLUWArrayDataType(LUWArrayDataType newLUWArrayDataType) {
		if (newLUWArrayDataType != eInternalContainer() || (eContainerFeatureID() != LUWPackage.ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE && newLUWArrayDataType != null)) {
			if (EcoreUtil.isAncestor(this, newLUWArrayDataType))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLUWArrayDataType != null)
				msgs = ((InternalEObject)newLUWArrayDataType).eInverseAdd(this, LUWPackage.LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE, LUWArrayDataType.class, msgs);
			msgs = basicSetLUWArrayDataType(newLUWArrayDataType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE, newLUWArrayDataType, newLUWArrayDataType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLUWArrayDataType((LUWArrayDataType)otherEnd, msgs);
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
			case LUWPackage.ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE:
				return basicSetLUWArrayDataType(null, msgs);
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
			case LUWPackage.ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE:
				return eInternalContainer().eInverseRemove(this, LUWPackage.LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE, LUWArrayDataType.class, msgs);
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
			case LUWPackage.ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE:
				return getLUWArrayDataType();
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
			case LUWPackage.ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE:
				setLUWArrayDataType((LUWArrayDataType)newValue);
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
			case LUWPackage.ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE:
				setLUWArrayDataType((LUWArrayDataType)null);
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
			case LUWPackage.ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE:
				return getLUWArrayDataType() != null;
		}
		return super.eIsSet(featureID);
	}

} //ArrayIndexElementTypeImpl
