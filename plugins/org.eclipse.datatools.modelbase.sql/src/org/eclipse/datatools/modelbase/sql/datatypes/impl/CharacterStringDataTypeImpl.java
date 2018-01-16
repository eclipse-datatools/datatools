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
package org.eclipse.datatools.modelbase.sql.datatypes.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CoercibilityType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Character String Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterStringDataTypeImpl#getLength <em>Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterStringDataTypeImpl#getCoercibility <em>Coercibility</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterStringDataTypeImpl#isFixedLength <em>Fixed Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterStringDataTypeImpl#getCollationName <em>Collation Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterStringDataTypeImpl#getCharacterSet <em>Character Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CharacterStringDataTypeImpl extends PredefinedDataTypeImpl implements CharacterStringDataType {
	/**
	 * The default value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected static final int LENGTH_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected int length = LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getCoercibility() <em>Coercibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoercibility()
	 * @generated
	 * @ordered
	 */
	protected static final CoercibilityType COERCIBILITY_EDEFAULT = CoercibilityType.IMPLICIT_LITERAL;

	/**
	 * The cached value of the '{@link #getCoercibility() <em>Coercibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoercibility()
	 * @generated
	 * @ordered
	 */
	protected CoercibilityType coercibility = COERCIBILITY_EDEFAULT;

	/**
	 * The default value of the '{@link #isFixedLength() <em>Fixed Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFixedLength()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FIXED_LENGTH_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFixedLength() <em>Fixed Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFixedLength()
	 * @generated
	 * @ordered
	 */
	protected boolean fixedLength = FIXED_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getCollationName() <em>Collation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCollationName()
	 * @generated
	 * @ordered
	 */
	protected static final String COLLATION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCollationName() <em>Collation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCollationName()
	 * @generated
	 * @ordered
	 */
	protected String collationName = COLLATION_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCharacterSet() <em>Character Set</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharacterSet()
	 * @generated
	 * @ordered
	 */
	protected CharacterSet characterSet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CharacterStringDataTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLDataTypesPackage.Literals.CHARACTER_STRING_DATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLength() {
		return length;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLength(int newLength) {
		int oldLength = length;
		length = newLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__LENGTH, oldLength, length));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoercibilityType getCoercibility() {
		return coercibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoercibility(CoercibilityType newCoercibility) {
		CoercibilityType oldCoercibility = coercibility;
		coercibility = newCoercibility == null ? COERCIBILITY_EDEFAULT : newCoercibility;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__COERCIBILITY, oldCoercibility, coercibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFixedLength() {
		return fixedLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCollationName() {
		return collationName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCollationName(String newCollationName) {
		String oldCollationName = collationName;
		collationName = newCollationName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__COLLATION_NAME, oldCollationName, collationName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharacterSet getCharacterSet() {
		if (characterSet != null && characterSet.eIsProxy()) {
			InternalEObject oldCharacterSet = (InternalEObject)characterSet;
			characterSet = (CharacterSet)eResolveProxy(oldCharacterSet);
			if (characterSet != oldCharacterSet) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__CHARACTER_SET, oldCharacterSet, characterSet));
			}
		}
		return characterSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharacterSet basicGetCharacterSet() {
		return characterSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCharacterSet(CharacterSet newCharacterSet, NotificationChain msgs) {
		CharacterSet oldCharacterSet = characterSet;
		characterSet = newCharacterSet;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__CHARACTER_SET, oldCharacterSet, newCharacterSet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharacterSet(CharacterSet newCharacterSet) {
		if (newCharacterSet != characterSet) {
			NotificationChain msgs = null;
			if (characterSet != null)
				msgs = ((InternalEObject)characterSet).eInverseRemove(this, SQLDataTypesPackage.CHARACTER_SET__CHARACTER_STRING_DATA_TYPE, CharacterSet.class, msgs);
			if (newCharacterSet != null)
				msgs = ((InternalEObject)newCharacterSet).eInverseAdd(this, SQLDataTypesPackage.CHARACTER_SET__CHARACTER_STRING_DATA_TYPE, CharacterSet.class, msgs);
			msgs = basicSetCharacterSet(newCharacterSet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__CHARACTER_SET, newCharacterSet, newCharacterSet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__CHARACTER_SET:
				if (characterSet != null)
					msgs = ((InternalEObject)characterSet).eInverseRemove(this, SQLDataTypesPackage.CHARACTER_SET__CHARACTER_STRING_DATA_TYPE, CharacterSet.class, msgs);
				return basicSetCharacterSet((CharacterSet)otherEnd, msgs);
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
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__CHARACTER_SET:
				return basicSetCharacterSet(null, msgs);
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
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__LENGTH:
				return new Integer(getLength());
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__COERCIBILITY:
				return getCoercibility();
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__FIXED_LENGTH:
				return isFixedLength() ? Boolean.TRUE : Boolean.FALSE;
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__COLLATION_NAME:
				return getCollationName();
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__CHARACTER_SET:
				if (resolve) return getCharacterSet();
				return basicGetCharacterSet();
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
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__LENGTH:
				setLength(((Integer)newValue).intValue());
				return;
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__COERCIBILITY:
				setCoercibility((CoercibilityType)newValue);
				return;
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__COLLATION_NAME:
				setCollationName((String)newValue);
				return;
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__CHARACTER_SET:
				setCharacterSet((CharacterSet)newValue);
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
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__LENGTH:
				setLength(LENGTH_EDEFAULT);
				return;
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__COERCIBILITY:
				setCoercibility(COERCIBILITY_EDEFAULT);
				return;
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__COLLATION_NAME:
				setCollationName(COLLATION_NAME_EDEFAULT);
				return;
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__CHARACTER_SET:
				setCharacterSet((CharacterSet)null);
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
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__LENGTH:
				return length != LENGTH_EDEFAULT;
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__COERCIBILITY:
				return coercibility != COERCIBILITY_EDEFAULT;
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__FIXED_LENGTH:
				return fixedLength != FIXED_LENGTH_EDEFAULT;
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__COLLATION_NAME:
				return COLLATION_NAME_EDEFAULT == null ? collationName != null : !COLLATION_NAME_EDEFAULT.equals(collationName);
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE__CHARACTER_SET:
				return characterSet != null;
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
		result.append(" (length: "); //$NON-NLS-1$
		result.append(length);
		result.append(", coercibility: "); //$NON-NLS-1$
		result.append(coercibility);
		result.append(", fixedLength: "); //$NON-NLS-1$
		result.append(fixedLength);
		result.append(", collationName: "); //$NON-NLS-1$
		result.append(collationName);
		result.append(')');
		return result.toString();
	}

} //CharacterStringDataTypeImpl
