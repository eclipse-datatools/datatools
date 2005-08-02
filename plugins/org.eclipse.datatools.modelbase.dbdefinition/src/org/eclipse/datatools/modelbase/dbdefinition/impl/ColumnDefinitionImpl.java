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
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ColumnDefinitionImpl#isIdentitySupported <em>Identity Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ColumnDefinitionImpl#isComputedSupported <em>Computed Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ColumnDefinitionImpl#isIdentityStartValueSupported <em>Identity Start Value Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ColumnDefinitionImpl#isIdentityIncrementSupported <em>Identity Increment Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ColumnDefinitionImpl#isIdentityMinimumSupported <em>Identity Minimum Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ColumnDefinitionImpl#isIdentityMaximumSupported <em>Identity Maximum Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ColumnDefinitionImpl#isIdentityCycleSupported <em>Identity Cycle Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColumnDefinitionImpl extends EObjectImpl implements ColumnDefinition {
	/**
	 * The default value of the '{@link #isIdentitySupported() <em>Identity Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIdentitySupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IDENTITY_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIdentitySupported() <em>Identity Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIdentitySupported()
	 * @generated
	 * @ordered
	 */
	protected boolean identitySupported = IDENTITY_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isComputedSupported() <em>Computed Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isComputedSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COMPUTED_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isComputedSupported() <em>Computed Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isComputedSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean computedSupported = COMPUTED_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isIdentityStartValueSupported() <em>Identity Start Value Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIdentityStartValueSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IDENTITY_START_VALUE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIdentityStartValueSupported() <em>Identity Start Value Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIdentityStartValueSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean identityStartValueSupported = IDENTITY_START_VALUE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isIdentityIncrementSupported() <em>Identity Increment Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIdentityIncrementSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IDENTITY_INCREMENT_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIdentityIncrementSupported() <em>Identity Increment Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIdentityIncrementSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean identityIncrementSupported = IDENTITY_INCREMENT_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isIdentityMinimumSupported() <em>Identity Minimum Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIdentityMinimumSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IDENTITY_MINIMUM_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIdentityMinimumSupported() <em>Identity Minimum Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIdentityMinimumSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean identityMinimumSupported = IDENTITY_MINIMUM_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isIdentityMaximumSupported() <em>Identity Maximum Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIdentityMaximumSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IDENTITY_MAXIMUM_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIdentityMaximumSupported() <em>Identity Maximum Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIdentityMaximumSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean identityMaximumSupported = IDENTITY_MAXIMUM_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isIdentityCycleSupported() <em>Identity Cycle Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIdentityCycleSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IDENTITY_CYCLE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIdentityCycleSupported() <em>Identity Cycle Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIdentityCycleSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean identityCycleSupported = IDENTITY_CYCLE_SUPPORTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ColumnDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.eINSTANCE.getColumnDefinition();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIdentitySupported() {
		return identitySupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentitySupported(boolean newIdentitySupported) {
		boolean oldIdentitySupported = identitySupported;
		identitySupported = newIdentitySupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_SUPPORTED, oldIdentitySupported, identitySupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isComputedSupported() {
		return computedSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComputedSupported(boolean newComputedSupported) {
		boolean oldComputedSupported = computedSupported;
		computedSupported = newComputedSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.COLUMN_DEFINITION__COMPUTED_SUPPORTED, oldComputedSupported, computedSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIdentityStartValueSupported() {
		return identityStartValueSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentityStartValueSupported(boolean newIdentityStartValueSupported) {
		boolean oldIdentityStartValueSupported = identityStartValueSupported;
		identityStartValueSupported = newIdentityStartValueSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_START_VALUE_SUPPORTED, oldIdentityStartValueSupported, identityStartValueSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIdentityIncrementSupported() {
		return identityIncrementSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentityIncrementSupported(boolean newIdentityIncrementSupported) {
		boolean oldIdentityIncrementSupported = identityIncrementSupported;
		identityIncrementSupported = newIdentityIncrementSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_INCREMENT_SUPPORTED, oldIdentityIncrementSupported, identityIncrementSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIdentityMinimumSupported() {
		return identityMinimumSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentityMinimumSupported(boolean newIdentityMinimumSupported) {
		boolean oldIdentityMinimumSupported = identityMinimumSupported;
		identityMinimumSupported = newIdentityMinimumSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_MINIMUM_SUPPORTED, oldIdentityMinimumSupported, identityMinimumSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIdentityMaximumSupported() {
		return identityMaximumSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentityMaximumSupported(boolean newIdentityMaximumSupported) {
		boolean oldIdentityMaximumSupported = identityMaximumSupported;
		identityMaximumSupported = newIdentityMaximumSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_MAXIMUM_SUPPORTED, oldIdentityMaximumSupported, identityMaximumSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIdentityCycleSupported() {
		return identityCycleSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentityCycleSupported(boolean newIdentityCycleSupported) {
		boolean oldIdentityCycleSupported = identityCycleSupported;
		identityCycleSupported = newIdentityCycleSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_CYCLE_SUPPORTED, oldIdentityCycleSupported, identityCycleSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_SUPPORTED:
				return isIdentitySupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__COMPUTED_SUPPORTED:
				return isComputedSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_START_VALUE_SUPPORTED:
				return isIdentityStartValueSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_INCREMENT_SUPPORTED:
				return isIdentityIncrementSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_MINIMUM_SUPPORTED:
				return isIdentityMinimumSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_MAXIMUM_SUPPORTED:
				return isIdentityMaximumSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_CYCLE_SUPPORTED:
				return isIdentityCycleSupported() ? Boolean.TRUE : Boolean.FALSE;
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
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_SUPPORTED:
				setIdentitySupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__COMPUTED_SUPPORTED:
				setComputedSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_START_VALUE_SUPPORTED:
				setIdentityStartValueSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_INCREMENT_SUPPORTED:
				setIdentityIncrementSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_MINIMUM_SUPPORTED:
				setIdentityMinimumSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_MAXIMUM_SUPPORTED:
				setIdentityMaximumSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_CYCLE_SUPPORTED:
				setIdentityCycleSupported(((Boolean)newValue).booleanValue());
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
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_SUPPORTED:
				setIdentitySupported(IDENTITY_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__COMPUTED_SUPPORTED:
				setComputedSupported(COMPUTED_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_START_VALUE_SUPPORTED:
				setIdentityStartValueSupported(IDENTITY_START_VALUE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_INCREMENT_SUPPORTED:
				setIdentityIncrementSupported(IDENTITY_INCREMENT_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_MINIMUM_SUPPORTED:
				setIdentityMinimumSupported(IDENTITY_MINIMUM_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_MAXIMUM_SUPPORTED:
				setIdentityMaximumSupported(IDENTITY_MAXIMUM_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_CYCLE_SUPPORTED:
				setIdentityCycleSupported(IDENTITY_CYCLE_SUPPORTED_EDEFAULT);
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
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_SUPPORTED:
				return identitySupported != IDENTITY_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__COMPUTED_SUPPORTED:
				return computedSupported != COMPUTED_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_START_VALUE_SUPPORTED:
				return identityStartValueSupported != IDENTITY_START_VALUE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_INCREMENT_SUPPORTED:
				return identityIncrementSupported != IDENTITY_INCREMENT_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_MINIMUM_SUPPORTED:
				return identityMinimumSupported != IDENTITY_MINIMUM_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_MAXIMUM_SUPPORTED:
				return identityMaximumSupported != IDENTITY_MAXIMUM_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.COLUMN_DEFINITION__IDENTITY_CYCLE_SUPPORTED:
				return identityCycleSupported != IDENTITY_CYCLE_SUPPORTED_EDEFAULT;
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
		result.append(" (identitySupported: "); //$NON-NLS-1$
		result.append(identitySupported);
		result.append(", computedSupported: "); //$NON-NLS-1$
		result.append(computedSupported);
		result.append(", identityStartValueSupported: "); //$NON-NLS-1$
		result.append(identityStartValueSupported);
		result.append(", identityIncrementSupported: "); //$NON-NLS-1$
		result.append(identityIncrementSupported);
		result.append(", identityMinimumSupported: "); //$NON-NLS-1$
		result.append(identityMinimumSupported);
		result.append(", identityMaximumSupported: "); //$NON-NLS-1$
		result.append(identityMaximumSupported);
		result.append(", identityCycleSupported: "); //$NON-NLS-1$
		result.append(identityCycleSupported);
		result.append(')');
		return result.toString();
	}

} //ColumnDefinitionImpl
