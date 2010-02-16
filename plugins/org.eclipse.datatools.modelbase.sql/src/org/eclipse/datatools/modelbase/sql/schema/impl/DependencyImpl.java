/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.schema.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dependency</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.DependencyImpl#getTargetEnd <em>Target End</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.DependencyImpl#getDependencyType <em>Dependency Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DependencyImpl extends SQLObjectImpl implements Dependency {
	/**
	 * The cached value of the '{@link #getTargetEnd() <em>Target End</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetEnd()
	 * @generated
	 * @ordered
	 */
	protected EObject targetEnd;

	/**
	 * The default value of the '{@link #getDependencyType() <em>Dependency Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencyType()
	 * @generated
	 * @ordered
	 */
	protected static final String DEPENDENCY_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDependencyType() <em>Dependency Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencyType()
	 * @generated
	 * @ordered
	 */
	protected String dependencyType = DEPENDENCY_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DependencyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLSchemaPackage.Literals.DEPENDENCY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getTargetEnd() {
		if (targetEnd != null && targetEnd.eIsProxy()) {
			InternalEObject oldTargetEnd = (InternalEObject)targetEnd;
			targetEnd = eResolveProxy(oldTargetEnd);
			if (targetEnd != oldTargetEnd) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLSchemaPackage.DEPENDENCY__TARGET_END, oldTargetEnd, targetEnd));
			}
		}
		return targetEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetTargetEnd() {
		return targetEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetEnd(EObject newTargetEnd) {
		EObject oldTargetEnd = targetEnd;
		targetEnd = newTargetEnd;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.DEPENDENCY__TARGET_END, oldTargetEnd, targetEnd));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDependencyType() {
		return dependencyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDependencyType(String newDependencyType) {
		String oldDependencyType = dependencyType;
		dependencyType = newDependencyType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.DEPENDENCY__DEPENDENCY_TYPE, oldDependencyType, dependencyType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SQLSchemaPackage.DEPENDENCY__TARGET_END:
				if (resolve) return getTargetEnd();
				return basicGetTargetEnd();
			case SQLSchemaPackage.DEPENDENCY__DEPENDENCY_TYPE:
				return getDependencyType();
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
			case SQLSchemaPackage.DEPENDENCY__TARGET_END:
				setTargetEnd((EObject)newValue);
				return;
			case SQLSchemaPackage.DEPENDENCY__DEPENDENCY_TYPE:
				setDependencyType((String)newValue);
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
			case SQLSchemaPackage.DEPENDENCY__TARGET_END:
				setTargetEnd((EObject)null);
				return;
			case SQLSchemaPackage.DEPENDENCY__DEPENDENCY_TYPE:
				setDependencyType(DEPENDENCY_TYPE_EDEFAULT);
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
			case SQLSchemaPackage.DEPENDENCY__TARGET_END:
				return targetEnd != null;
			case SQLSchemaPackage.DEPENDENCY__DEPENDENCY_TYPE:
				return DEPENDENCY_TYPE_EDEFAULT == null ? dependencyType != null : !DEPENDENCY_TYPE_EDEFAULT.equals(dependencyType);
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
		result.append(" (dependencyType: "); //$NON-NLS-1$
		result.append(dependencyType);
		result.append(')');
		return result.toString();
	}

} //DependencyImpl
