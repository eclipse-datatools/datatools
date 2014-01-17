/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWModuleConditionImpl.java,v 1.1 2009/01/31 00:22:40 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCondition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleConditionImpl#isPublished <em>Published</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleConditionImpl#getModule <em>Module</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleConditionImpl#getSqlstate <em>Sqlstate</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWModuleConditionImpl extends SQLObjectImpl implements LUWModuleCondition {
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
	 * The default value of the '{@link #getSqlstate() <em>Sqlstate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSqlstate()
	 * @generated
	 * @ordered
	 */
	protected static final String SQLSTATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSqlstate() <em>Sqlstate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSqlstate()
	 * @generated
	 * @ordered
	 */
	protected String sqlstate = SQLSTATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWModuleConditionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_MODULE_CONDITION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MODULE_CONDITION__PUBLISHED, oldPublished, published));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWModule getModule() {
		if (eContainerFeatureID() != LUWPackage.LUW_MODULE_CONDITION__MODULE) return null;
		return (LUWModule)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModule(LUWModule newModule, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModule, LUWPackage.LUW_MODULE_CONDITION__MODULE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModule(LUWModule newModule) {
		if (newModule != eInternalContainer() || (eContainerFeatureID() != LUWPackage.LUW_MODULE_CONDITION__MODULE && newModule != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MODULE_CONDITION__MODULE, newModule, newModule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSqlstate() {
		return sqlstate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSqlstate(String newSqlstate) {
		String oldSqlstate = sqlstate;
		sqlstate = newSqlstate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MODULE_CONDITION__SQLSTATE, oldSqlstate, sqlstate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_MODULE_CONDITION__MODULE:
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
			case LUWPackage.LUW_MODULE_CONDITION__MODULE:
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
			case LUWPackage.LUW_MODULE_CONDITION__MODULE:
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
			case LUWPackage.LUW_MODULE_CONDITION__PUBLISHED:
				return isPublished() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_MODULE_CONDITION__MODULE:
				return getModule();
			case LUWPackage.LUW_MODULE_CONDITION__SQLSTATE:
				return getSqlstate();
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
			case LUWPackage.LUW_MODULE_CONDITION__PUBLISHED:
				setPublished(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_MODULE_CONDITION__MODULE:
				setModule((LUWModule)newValue);
				return;
			case LUWPackage.LUW_MODULE_CONDITION__SQLSTATE:
				setSqlstate((String)newValue);
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
			case LUWPackage.LUW_MODULE_CONDITION__PUBLISHED:
				setPublished(PUBLISHED_EDEFAULT);
				return;
			case LUWPackage.LUW_MODULE_CONDITION__MODULE:
				setModule((LUWModule)null);
				return;
			case LUWPackage.LUW_MODULE_CONDITION__SQLSTATE:
				setSqlstate(SQLSTATE_EDEFAULT);
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
			case LUWPackage.LUW_MODULE_CONDITION__PUBLISHED:
				return published != PUBLISHED_EDEFAULT;
			case LUWPackage.LUW_MODULE_CONDITION__MODULE:
				return getModule() != null;
			case LUWPackage.LUW_MODULE_CONDITION__SQLSTATE:
				return SQLSTATE_EDEFAULT == null ? sqlstate != null : !SQLSTATE_EDEFAULT.equals(sqlstate);
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
				case LUWPackage.LUW_MODULE_CONDITION__PUBLISHED: return LUWPackage.LUW_MODULE_OBJECT__PUBLISHED;
				case LUWPackage.LUW_MODULE_CONDITION__MODULE: return LUWPackage.LUW_MODULE_OBJECT__MODULE;
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
				case LUWPackage.LUW_MODULE_OBJECT__PUBLISHED: return LUWPackage.LUW_MODULE_CONDITION__PUBLISHED;
				case LUWPackage.LUW_MODULE_OBJECT__MODULE: return LUWPackage.LUW_MODULE_CONDITION__MODULE;
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
		result.append(", sqlstate: "); //$NON-NLS-1$
		result.append(sqlstate);
		result.append(')');
		return result.toString();
	}

} //LUWModuleConditionImpl
