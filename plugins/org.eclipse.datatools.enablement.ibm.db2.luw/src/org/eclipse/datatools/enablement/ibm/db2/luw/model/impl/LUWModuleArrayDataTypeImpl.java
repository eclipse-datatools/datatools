/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWModuleArrayDataTypeImpl.java,v 1.2 2009/02/16 19:01:34 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleArrayDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

import org.eclipse.datatools.modelbase.sql.datatypes.impl.ArrayDataTypeImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module Array Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleArrayDataTypeImpl#isPublished <em>Published</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleArrayDataTypeImpl#getModule <em>Module</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWModuleArrayDataTypeImpl extends LUWArrayDataTypeImpl implements LUWModuleArrayDataType {
	/**
	 * The default value of the '{@link #isPublished() <em>Published</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPublished()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PUBLISHED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPublished() <em>Published</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPublished()
	 * @generated
	 * @ordered
	 */
	protected boolean published = PUBLISHED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWModuleArrayDataTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_MODULE_ARRAY_DATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPublished() {
		return published;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPublished(boolean newPublished) {
		boolean oldPublished = published;
		published = newPublished;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__PUBLISHED, oldPublished, published));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWModule getModule() {
		if (eContainerFeatureID() != LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__MODULE) return null;
		return (LUWModule)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModule(LUWModule newModule, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModule, LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__MODULE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModule(LUWModule newModule) {
		if (newModule != eInternalContainer() || (eContainerFeatureID() != LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__MODULE && newModule != null)) {
			if (EcoreUtil.isAncestor(this, newModule))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModule != null)
				msgs = ((InternalEObject)newModule).eInverseAdd(this, LUWPackage.LUW_MODULE__MODULE_OBJECTS, LUWModule.class, msgs);
			msgs = basicSetModule(newModule, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__MODULE, newModule, newModule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__MODULE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModule((LUWModule)otherEnd, msgs);
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
			case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__MODULE:
				return basicSetModule(null, msgs);
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
			case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__MODULE:
				return eInternalContainer().eInverseRemove(this, LUWPackage.LUW_MODULE__MODULE_OBJECTS, LUWModule.class, msgs);
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
			case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__PUBLISHED:
				return isPublished() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__MODULE:
				return getModule();
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
			case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__PUBLISHED:
				setPublished(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__MODULE:
				setModule((LUWModule)newValue);
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
			case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__PUBLISHED:
				setPublished(PUBLISHED_EDEFAULT);
				return;
			case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__MODULE:
				setModule((LUWModule)null);
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
			case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__PUBLISHED:
				return published != PUBLISHED_EDEFAULT;
			case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__MODULE:
				return getModule() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == LUWModuleObject.class) {
			switch (derivedFeatureID) {
				case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__PUBLISHED: return LUWPackage.LUW_MODULE_OBJECT__PUBLISHED;
				case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__MODULE: return LUWPackage.LUW_MODULE_OBJECT__MODULE;
				default: return -1;
			}
		}
		if (baseClass == LUWModuleType.class) {
			switch (derivedFeatureID) {
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
		if (baseClass == LUWModuleObject.class) {
			switch (baseFeatureID) {
				case LUWPackage.LUW_MODULE_OBJECT__PUBLISHED: return LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__PUBLISHED;
				case LUWPackage.LUW_MODULE_OBJECT__MODULE: return LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE__MODULE;
				default: return -1;
			}
		}
		if (baseClass == LUWModuleType.class) {
			switch (baseFeatureID) {
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
		result.append(" (published: "); //$NON-NLS-1$
		result.append(published);
		result.append(')');
		return result.toString();
	}

} //LUWModuleArrayDataTypeImpl
