/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sequence Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SequenceDefinitionImpl#getPredefinedDataTypeDefinitions <em>Predefined Data Type Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SequenceDefinitionImpl#getDefaultDataTypeDefinition <em>Default Data Type Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SequenceDefinitionImpl#isTypeEnumerationSupported <em>Type Enumeration Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SequenceDefinitionImpl#isCacheSupported <em>Cache Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SequenceDefinitionImpl#isOrderSupported <em>Order Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SequenceDefinitionImpl#getNoMaximumValueString <em>No Maximum Value String</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SequenceDefinitionImpl#getNoMinimumValueString <em>No Minimum Value String</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SequenceDefinitionImpl#getNoCacheString <em>No Cache String</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SequenceDefinitionImpl#getCacheDefaultValue <em>Cache Default Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SequenceDefinitionImpl extends EObjectImpl implements SequenceDefinition {
	/**
	 * The cached value of the '{@link #getPredefinedDataTypeDefinitions() <em>Predefined Data Type Definitions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredefinedDataTypeDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList predefinedDataTypeDefinitions;

	/**
	 * The cached value of the '{@link #getDefaultDataTypeDefinition() <em>Default Data Type Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultDataTypeDefinition()
	 * @generated
	 * @ordered
	 */
	protected PredefinedDataTypeDefinition defaultDataTypeDefinition;

	/**
	 * The default value of the '{@link #isTypeEnumerationSupported() <em>Type Enumeration Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTypeEnumerationSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TYPE_ENUMERATION_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTypeEnumerationSupported() <em>Type Enumeration Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTypeEnumerationSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean typeEnumerationSupported = TYPE_ENUMERATION_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isCacheSupported() <em>Cache Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCacheSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CACHE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCacheSupported() <em>Cache Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCacheSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean cacheSupported = CACHE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isOrderSupported() <em>Order Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrderSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ORDER_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOrderSupported() <em>Order Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrderSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean orderSupported = ORDER_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getNoMaximumValueString() <em>No Maximum Value String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNoMaximumValueString()
	 * @generated
	 * @ordered
	 */
	protected static final String NO_MAXIMUM_VALUE_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNoMaximumValueString() <em>No Maximum Value String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNoMaximumValueString()
	 * @generated
	 * @ordered
	 */
	protected String noMaximumValueString = NO_MAXIMUM_VALUE_STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getNoMinimumValueString() <em>No Minimum Value String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNoMinimumValueString()
	 * @generated
	 * @ordered
	 */
	protected static final String NO_MINIMUM_VALUE_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNoMinimumValueString() <em>No Minimum Value String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNoMinimumValueString()
	 * @generated
	 * @ordered
	 */
	protected String noMinimumValueString = NO_MINIMUM_VALUE_STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getNoCacheString() <em>No Cache String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNoCacheString()
	 * @generated
	 * @ordered
	 */
	protected static final String NO_CACHE_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNoCacheString() <em>No Cache String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNoCacheString()
	 * @generated
	 * @ordered
	 */
	protected String noCacheString = NO_CACHE_STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getCacheDefaultValue() <em>Cache Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCacheDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected static final int CACHE_DEFAULT_VALUE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCacheDefaultValue() <em>Cache Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCacheDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected int cacheDefaultValue = CACHE_DEFAULT_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SequenceDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.SEQUENCE_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPredefinedDataTypeDefinitions() {
		if (predefinedDataTypeDefinitions == null) {
			predefinedDataTypeDefinitions = new EObjectResolvingEList(PredefinedDataTypeDefinition.class, this, DatabaseDefinitionPackage.SEQUENCE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS);
		}
		return predefinedDataTypeDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTypeEnumerationSupported() {
		return typeEnumerationSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeEnumerationSupported(boolean newTypeEnumerationSupported) {
		boolean oldTypeEnumerationSupported = typeEnumerationSupported;
		typeEnumerationSupported = newTypeEnumerationSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.SEQUENCE_DEFINITION__TYPE_ENUMERATION_SUPPORTED, oldTypeEnumerationSupported, typeEnumerationSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCacheSupported() {
		return cacheSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCacheSupported(boolean newCacheSupported) {
		boolean oldCacheSupported = cacheSupported;
		cacheSupported = newCacheSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.SEQUENCE_DEFINITION__CACHE_SUPPORTED, oldCacheSupported, cacheSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOrderSupported() {
		return orderSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderSupported(boolean newOrderSupported) {
		boolean oldOrderSupported = orderSupported;
		orderSupported = newOrderSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.SEQUENCE_DEFINITION__ORDER_SUPPORTED, oldOrderSupported, orderSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNoMaximumValueString() {
		return noMaximumValueString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNoMaximumValueString(String newNoMaximumValueString) {
		String oldNoMaximumValueString = noMaximumValueString;
		noMaximumValueString = newNoMaximumValueString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_MAXIMUM_VALUE_STRING, oldNoMaximumValueString, noMaximumValueString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNoMinimumValueString() {
		return noMinimumValueString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNoMinimumValueString(String newNoMinimumValueString) {
		String oldNoMinimumValueString = noMinimumValueString;
		noMinimumValueString = newNoMinimumValueString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_MINIMUM_VALUE_STRING, oldNoMinimumValueString, noMinimumValueString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNoCacheString() {
		return noCacheString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNoCacheString(String newNoCacheString) {
		String oldNoCacheString = noCacheString;
		noCacheString = newNoCacheString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_CACHE_STRING, oldNoCacheString, noCacheString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCacheDefaultValue() {
		return cacheDefaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCacheDefaultValue(int newCacheDefaultValue) {
		int oldCacheDefaultValue = cacheDefaultValue;
		cacheDefaultValue = newCacheDefaultValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.SEQUENCE_DEFINITION__CACHE_DEFAULT_VALUE, oldCacheDefaultValue, cacheDefaultValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS:
				return getPredefinedDataTypeDefinitions();
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__DEFAULT_DATA_TYPE_DEFINITION:
				if (resolve) return getDefaultDataTypeDefinition();
				return basicGetDefaultDataTypeDefinition();
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__TYPE_ENUMERATION_SUPPORTED:
				return isTypeEnumerationSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__CACHE_SUPPORTED:
				return isCacheSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__ORDER_SUPPORTED:
				return isOrderSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_MAXIMUM_VALUE_STRING:
				return getNoMaximumValueString();
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_MINIMUM_VALUE_STRING:
				return getNoMinimumValueString();
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_CACHE_STRING:
				return getNoCacheString();
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__CACHE_DEFAULT_VALUE:
				return new Integer(getCacheDefaultValue());
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
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS:
				getPredefinedDataTypeDefinitions().clear();
				getPredefinedDataTypeDefinitions().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__DEFAULT_DATA_TYPE_DEFINITION:
				setDefaultDataTypeDefinition((PredefinedDataTypeDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__TYPE_ENUMERATION_SUPPORTED:
				setTypeEnumerationSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__CACHE_SUPPORTED:
				setCacheSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__ORDER_SUPPORTED:
				setOrderSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_MAXIMUM_VALUE_STRING:
				setNoMaximumValueString((String)newValue);
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_MINIMUM_VALUE_STRING:
				setNoMinimumValueString((String)newValue);
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_CACHE_STRING:
				setNoCacheString((String)newValue);
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__CACHE_DEFAULT_VALUE:
				setCacheDefaultValue(((Integer)newValue).intValue());
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
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS:
				getPredefinedDataTypeDefinitions().clear();
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__DEFAULT_DATA_TYPE_DEFINITION:
				setDefaultDataTypeDefinition((PredefinedDataTypeDefinition)null);
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__TYPE_ENUMERATION_SUPPORTED:
				setTypeEnumerationSupported(TYPE_ENUMERATION_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__CACHE_SUPPORTED:
				setCacheSupported(CACHE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__ORDER_SUPPORTED:
				setOrderSupported(ORDER_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_MAXIMUM_VALUE_STRING:
				setNoMaximumValueString(NO_MAXIMUM_VALUE_STRING_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_MINIMUM_VALUE_STRING:
				setNoMinimumValueString(NO_MINIMUM_VALUE_STRING_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_CACHE_STRING:
				setNoCacheString(NO_CACHE_STRING_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__CACHE_DEFAULT_VALUE:
				setCacheDefaultValue(CACHE_DEFAULT_VALUE_EDEFAULT);
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
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS:
				return predefinedDataTypeDefinitions != null && !predefinedDataTypeDefinitions.isEmpty();
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__DEFAULT_DATA_TYPE_DEFINITION:
				return defaultDataTypeDefinition != null;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__TYPE_ENUMERATION_SUPPORTED:
				return typeEnumerationSupported != TYPE_ENUMERATION_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__CACHE_SUPPORTED:
				return cacheSupported != CACHE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__ORDER_SUPPORTED:
				return orderSupported != ORDER_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_MAXIMUM_VALUE_STRING:
				return NO_MAXIMUM_VALUE_STRING_EDEFAULT == null ? noMaximumValueString != null : !NO_MAXIMUM_VALUE_STRING_EDEFAULT.equals(noMaximumValueString);
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_MINIMUM_VALUE_STRING:
				return NO_MINIMUM_VALUE_STRING_EDEFAULT == null ? noMinimumValueString != null : !NO_MINIMUM_VALUE_STRING_EDEFAULT.equals(noMinimumValueString);
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__NO_CACHE_STRING:
				return NO_CACHE_STRING_EDEFAULT == null ? noCacheString != null : !NO_CACHE_STRING_EDEFAULT.equals(noCacheString);
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION__CACHE_DEFAULT_VALUE:
				return cacheDefaultValue != CACHE_DEFAULT_VALUE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PredefinedDataTypeDefinition getDefaultDataTypeDefinition() {
		if (defaultDataTypeDefinition != null && defaultDataTypeDefinition.eIsProxy()) {
			InternalEObject oldDefaultDataTypeDefinition = (InternalEObject)defaultDataTypeDefinition;
			defaultDataTypeDefinition = (PredefinedDataTypeDefinition)eResolveProxy(oldDefaultDataTypeDefinition);
			if (defaultDataTypeDefinition != oldDefaultDataTypeDefinition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatabaseDefinitionPackage.SEQUENCE_DEFINITION__DEFAULT_DATA_TYPE_DEFINITION, oldDefaultDataTypeDefinition, defaultDataTypeDefinition));
			}
		}
		return defaultDataTypeDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PredefinedDataTypeDefinition basicGetDefaultDataTypeDefinition() {
		return defaultDataTypeDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultDataTypeDefinition(PredefinedDataTypeDefinition newDefaultDataTypeDefinition) {
		PredefinedDataTypeDefinition oldDefaultDataTypeDefinition = defaultDataTypeDefinition;
		defaultDataTypeDefinition = newDefaultDataTypeDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.SEQUENCE_DEFINITION__DEFAULT_DATA_TYPE_DEFINITION, oldDefaultDataTypeDefinition, defaultDataTypeDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (typeEnumerationSupported: "); //$NON-NLS-1$
		result.append(typeEnumerationSupported);
		result.append(", cacheSupported: "); //$NON-NLS-1$
		result.append(cacheSupported);
		result.append(", orderSupported: "); //$NON-NLS-1$
		result.append(orderSupported);
		result.append(", noMaximumValueString: "); //$NON-NLS-1$
		result.append(noMaximumValueString);
		result.append(", noMinimumValueString: "); //$NON-NLS-1$
		result.append(noMinimumValueString);
		result.append(", noCacheString: "); //$NON-NLS-1$
		result.append(noCacheString);
		result.append(", cacheDefaultValue: "); //$NON-NLS-1$
		result.append(cacheDefaultValue);
		result.append(')');
		return result.toString();
	}

} //SequenceDefinitionImpl
