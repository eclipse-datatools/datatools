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
	protected EObject targetEnd = null;

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
		return SQLSchemaPackage.eINSTANCE.getDependency();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getTargetEnd() {
		if (targetEnd != null && targetEnd.eIsProxy()) {
			EObject oldTargetEnd = targetEnd;
			targetEnd = (EObject)eResolveProxy((InternalEObject)targetEnd);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLSchemaPackage.DEPENDENCY__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLSchemaPackage.DEPENDENCY__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.DEPENDENCY__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLSchemaPackage.DEPENDENCY__EANNOTATIONS:
				return getEAnnotations();
			case SQLSchemaPackage.DEPENDENCY__NAME:
				return getName();
			case SQLSchemaPackage.DEPENDENCY__DEPENDENCIES:
				return getDependencies();
			case SQLSchemaPackage.DEPENDENCY__DESCRIPTION:
				return getDescription();
			case SQLSchemaPackage.DEPENDENCY__LABEL:
				return getLabel();
			case SQLSchemaPackage.DEPENDENCY__TARGET_END:
				if (resolve) return getTargetEnd();
				return basicGetTargetEnd();
			case SQLSchemaPackage.DEPENDENCY__DEPENDENCY_TYPE:
				return getDependencyType();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLSchemaPackage.DEPENDENCY__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.DEPENDENCY__NAME:
				setName((String)newValue);
				return;
			case SQLSchemaPackage.DEPENDENCY__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.DEPENDENCY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLSchemaPackage.DEPENDENCY__LABEL:
				setLabel((String)newValue);
				return;
			case SQLSchemaPackage.DEPENDENCY__TARGET_END:
				setTargetEnd((EObject)newValue);
				return;
			case SQLSchemaPackage.DEPENDENCY__DEPENDENCY_TYPE:
				setDependencyType((String)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLSchemaPackage.DEPENDENCY__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLSchemaPackage.DEPENDENCY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLSchemaPackage.DEPENDENCY__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLSchemaPackage.DEPENDENCY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLSchemaPackage.DEPENDENCY__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLSchemaPackage.DEPENDENCY__TARGET_END:
				setTargetEnd((EObject)null);
				return;
			case SQLSchemaPackage.DEPENDENCY__DEPENDENCY_TYPE:
				setDependencyType(DEPENDENCY_TYPE_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLSchemaPackage.DEPENDENCY__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLSchemaPackage.DEPENDENCY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLSchemaPackage.DEPENDENCY__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLSchemaPackage.DEPENDENCY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLSchemaPackage.DEPENDENCY__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLSchemaPackage.DEPENDENCY__TARGET_END:
				return targetEnd != null;
			case SQLSchemaPackage.DEPENDENCY__DEPENDENCY_TYPE:
				return DEPENDENCY_TYPE_EDEFAULT == null ? dependencyType != null : !DEPENDENCY_TYPE_EDEFAULT.equals(dependencyType);
		}
		return eDynamicIsSet(eFeature);
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
