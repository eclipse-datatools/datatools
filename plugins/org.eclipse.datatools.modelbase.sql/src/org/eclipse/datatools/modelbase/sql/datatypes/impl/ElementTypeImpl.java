/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.datatypes.impl;

import org.eclipse.datatools.modelbase.sql.datatypes.CollectionDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.ElementType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.TypedElementImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ElementTypeImpl#getCollectionDataType <em>Collection Data Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementTypeImpl extends TypedElementImpl implements ElementType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLDataTypesPackage.Literals.ELEMENT_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionDataType getCollectionDataType() {
		if (eContainerFeatureID != SQLDataTypesPackage.ELEMENT_TYPE__COLLECTION_DATA_TYPE) return null;
		return (CollectionDataType)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCollectionDataType(CollectionDataType newCollectionDataType, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCollectionDataType, SQLDataTypesPackage.ELEMENT_TYPE__COLLECTION_DATA_TYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCollectionDataType(CollectionDataType newCollectionDataType) {
		if (newCollectionDataType != eInternalContainer() || (eContainerFeatureID != SQLDataTypesPackage.ELEMENT_TYPE__COLLECTION_DATA_TYPE && newCollectionDataType != null)) {
			if (EcoreUtil.isAncestor(this, newCollectionDataType))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCollectionDataType != null)
				msgs = ((InternalEObject)newCollectionDataType).eInverseAdd(this, SQLDataTypesPackage.COLLECTION_DATA_TYPE__ELEMENT_TYPE, CollectionDataType.class, msgs);
			msgs = basicSetCollectionDataType(newCollectionDataType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.ELEMENT_TYPE__COLLECTION_DATA_TYPE, newCollectionDataType, newCollectionDataType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLDataTypesPackage.ELEMENT_TYPE__COLLECTION_DATA_TYPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCollectionDataType((CollectionDataType)otherEnd, msgs);
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
			case SQLDataTypesPackage.ELEMENT_TYPE__COLLECTION_DATA_TYPE:
				return basicSetCollectionDataType(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case SQLDataTypesPackage.ELEMENT_TYPE__COLLECTION_DATA_TYPE:
				return eInternalContainer().eInverseRemove(this, SQLDataTypesPackage.COLLECTION_DATA_TYPE__ELEMENT_TYPE, CollectionDataType.class, msgs);
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
			case SQLDataTypesPackage.ELEMENT_TYPE__COLLECTION_DATA_TYPE:
				return getCollectionDataType();
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
			case SQLDataTypesPackage.ELEMENT_TYPE__COLLECTION_DATA_TYPE:
				setCollectionDataType((CollectionDataType)newValue);
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
			case SQLDataTypesPackage.ELEMENT_TYPE__COLLECTION_DATA_TYPE:
				setCollectionDataType((CollectionDataType)null);
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
			case SQLDataTypesPackage.ELEMENT_TYPE__COLLECTION_DATA_TYPE:
				return getCollectionDataType() != null;
		}
		return super.eIsSet(featureID);
	}

} //ElementTypeImpl
