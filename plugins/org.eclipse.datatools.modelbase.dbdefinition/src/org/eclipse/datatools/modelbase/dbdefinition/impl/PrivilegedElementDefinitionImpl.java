/**
 * <copyright>
 * </copyright>
 *
 * $Id: PrivilegedElementDefinitionImpl.java,v 1.1 2007/05/31 00:29:10 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.PrivilegeDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Privileged Element Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PrivilegedElementDefinitionImpl#getPrivilegeDefinitions <em>Privilege Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PrivilegedElementDefinitionImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PrivilegedElementDefinitionImpl extends EObjectImpl implements PrivilegedElementDefinition {
	/**
	 * The cached value of the '{@link #getPrivilegeDefinitions() <em>Privilege Definitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivilegeDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList privilegeDefinitions;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrivilegedElementDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.PRIVILEGED_ELEMENT_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPrivilegeDefinitions() {
		if (privilegeDefinitions == null) {
			privilegeDefinitions = new EObjectContainmentEList(PrivilegeDefinition.class, this, DatabaseDefinitionPackage.PRIVILEGED_ELEMENT_DEFINITION__PRIVILEGE_DEFINITIONS);
		}
		return privilegeDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PRIVILEGED_ELEMENT_DEFINITION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DatabaseDefinitionPackage.PRIVILEGED_ELEMENT_DEFINITION__PRIVILEGE_DEFINITIONS:
				return ((InternalEList)getPrivilegeDefinitions()).basicRemove(otherEnd, msgs);
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
			case DatabaseDefinitionPackage.PRIVILEGED_ELEMENT_DEFINITION__PRIVILEGE_DEFINITIONS:
				return getPrivilegeDefinitions();
			case DatabaseDefinitionPackage.PRIVILEGED_ELEMENT_DEFINITION__NAME:
				return getName();
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
			case DatabaseDefinitionPackage.PRIVILEGED_ELEMENT_DEFINITION__PRIVILEGE_DEFINITIONS:
				getPrivilegeDefinitions().clear();
				getPrivilegeDefinitions().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.PRIVILEGED_ELEMENT_DEFINITION__NAME:
				setName((String)newValue);
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
			case DatabaseDefinitionPackage.PRIVILEGED_ELEMENT_DEFINITION__PRIVILEGE_DEFINITIONS:
				getPrivilegeDefinitions().clear();
				return;
			case DatabaseDefinitionPackage.PRIVILEGED_ELEMENT_DEFINITION__NAME:
				setName(NAME_EDEFAULT);
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
			case DatabaseDefinitionPackage.PRIVILEGED_ELEMENT_DEFINITION__PRIVILEGE_DEFINITIONS:
				return privilegeDefinitions != null && !privilegeDefinitions.isEmpty();
			case DatabaseDefinitionPackage.PRIVILEGED_ELEMENT_DEFINITION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //PrivilegedElementDefinitionImpl
