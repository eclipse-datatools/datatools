/**
 *************************************************************************
 * Copyright (c) 2005, 2010 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *
 *************************************************************************
 *
 * $Id: PropertyAttributesImpl.java,v 1.4 2010/02/17 02:20:38 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.PropertyAttributes;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Attributes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.PropertyAttributesImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.PropertyAttributesImpl#getElementAttributes <em>Element Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.PropertyAttributesImpl#isDerivedMetaData <em>Derived Meta Data</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PropertyAttributesImpl extends EObjectImpl implements PropertyAttributes {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected static final String DISPLAY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected String displayName = DISPLAY_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getElementAttributes() <em>Element Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementAttributes()
	 * @generated
	 * @ordered
	 */
	protected InputElementAttributes elementAttributes;

	/**
	 * The default value of the '{@link #isDerivedMetaData() <em>Derived Meta Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDerivedMetaData()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DERIVED_META_DATA_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isDerivedMetaData() <em>Derived Meta Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDerivedMetaData()
	 * @generated
	 * @ordered
	 */
	protected boolean derivedMetaData = DERIVED_META_DATA_EDEFAULT;

	/**
	 * This is true if the Derived Meta Data attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean derivedMetaDataESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyAttributesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DesignPackage.Literals.PROPERTY_ATTRIBUTES;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.PropertyAttributes#getDisplayName()
	 * @generated NOT
	 */
	@Override
	public String getDisplayName() {
		return DesignUtil.getDefaultResourceString(getDisplayNameGen());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected String getDisplayNameGen() {
		return displayName;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.PropertyAttributes#setDisplayName(java.lang.String)
	 * @generated NOT
	 */
	@Override
	public void setDisplayName(String newDisplayName) {
		String newAttrValue = DesignUtil.addDefaultToResourceAttribute(newDisplayName, getDisplayNameGen());
		setDisplayNameGen(newAttrValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setDisplayNameGen(String newDisplayName) {
		String oldDisplayName = displayName;
		displayName = newDisplayName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.PROPERTY_ATTRIBUTES__DISPLAY_NAME,
					oldDisplayName, displayName));
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.PropertyAttributes#getDisplayNameKey()
	 * @generated NOT
	 */
	@Override
	public String getDisplayNameKey() {
		return DesignUtil.getResourceKey(getDisplayNameGen());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.PropertyAttributes#setDisplayNameKey(java.lang.String)
	 * @generated NOT
	 */
	@Override
	public void setDisplayNameKey(String newDisplayNameKey) {
		String newAttrValue = DesignUtil.addKeyToResourceAttribute(newDisplayNameKey, getDisplayNameGen());
		setDisplayNameGen(newAttrValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InputElementAttributes getElementAttributes() {
		return elementAttributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElementAttributes(InputElementAttributes newElementAttributes,
			NotificationChain msgs) {
		InputElementAttributes oldElementAttributes = elementAttributes;
		elementAttributes = newElementAttributes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					DesignPackage.PROPERTY_ATTRIBUTES__ELEMENT_ATTRIBUTES, oldElementAttributes, newElementAttributes);
			if (msgs == null) {
				msgs = notification;
			} else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setElementAttributes(InputElementAttributes newElementAttributes) {
		if (newElementAttributes != elementAttributes) {
			NotificationChain msgs = null;
			if (elementAttributes != null) {
				msgs = ((InternalEObject) elementAttributes).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.PROPERTY_ATTRIBUTES__ELEMENT_ATTRIBUTES, null, msgs);
			}
			if (newElementAttributes != null) {
				msgs = ((InternalEObject) newElementAttributes).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.PROPERTY_ATTRIBUTES__ELEMENT_ATTRIBUTES, null, msgs);
			}
			msgs = basicSetElementAttributes(newElementAttributes, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.PROPERTY_ATTRIBUTES__ELEMENT_ATTRIBUTES,
					newElementAttributes, newElementAttributes));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDerivedMetaData() {
		return derivedMetaData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDerivedMetaData(boolean newDerivedMetaData) {
		boolean oldDerivedMetaData = derivedMetaData;
		derivedMetaData = newDerivedMetaData;
		boolean oldDerivedMetaDataESet = derivedMetaDataESet;
		derivedMetaDataESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.PROPERTY_ATTRIBUTES__DERIVED_META_DATA,
					oldDerivedMetaData, derivedMetaData, !oldDerivedMetaDataESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetDerivedMetaData() {
		boolean oldDerivedMetaData = derivedMetaData;
		boolean oldDerivedMetaDataESet = derivedMetaDataESet;
		derivedMetaData = DERIVED_META_DATA_EDEFAULT;
		derivedMetaDataESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET,
					DesignPackage.PROPERTY_ATTRIBUTES__DERIVED_META_DATA, oldDerivedMetaData,
					DERIVED_META_DATA_EDEFAULT, oldDerivedMetaDataESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetDerivedMetaData() {
		return derivedMetaDataESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case DesignPackage.PROPERTY_ATTRIBUTES__ELEMENT_ATTRIBUTES:
			return basicSetElementAttributes(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case DesignPackage.PROPERTY_ATTRIBUTES__DISPLAY_NAME:
			return getDisplayNameGen();
		case DesignPackage.PROPERTY_ATTRIBUTES__ELEMENT_ATTRIBUTES:
			return getElementAttributes();
		case DesignPackage.PROPERTY_ATTRIBUTES__DERIVED_META_DATA:
			return isDerivedMetaData() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case DesignPackage.PROPERTY_ATTRIBUTES__DISPLAY_NAME:
			setDisplayNameGen((String) newValue);
			return;
		case DesignPackage.PROPERTY_ATTRIBUTES__ELEMENT_ATTRIBUTES:
			setElementAttributes((InputElementAttributes) newValue);
			return;
		case DesignPackage.PROPERTY_ATTRIBUTES__DERIVED_META_DATA:
			setDerivedMetaData(((Boolean) newValue).booleanValue());
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case DesignPackage.PROPERTY_ATTRIBUTES__DISPLAY_NAME:
			setDisplayNameGen(DISPLAY_NAME_EDEFAULT);
			return;
		case DesignPackage.PROPERTY_ATTRIBUTES__ELEMENT_ATTRIBUTES:
			setElementAttributes((InputElementAttributes) null);
			return;
		case DesignPackage.PROPERTY_ATTRIBUTES__DERIVED_META_DATA:
			unsetDerivedMetaData();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case DesignPackage.PROPERTY_ATTRIBUTES__DISPLAY_NAME:
			return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
		case DesignPackage.PROPERTY_ATTRIBUTES__ELEMENT_ATTRIBUTES:
			return elementAttributes != null;
		case DesignPackage.PROPERTY_ATTRIBUTES__DERIVED_META_DATA:
			return isSetDerivedMetaData();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (displayName: "); //$NON-NLS-1$
		result.append(displayName);
		result.append(", derivedMetaData: "); //$NON-NLS-1$
		if (derivedMetaDataESet) {
			result.append(derivedMetaData);
		} else {
			result.append("<unset>"); //$NON-NLS-1$
		}
		result.append(')');
		return result.toString();
	}

} //PropertyAttributesImpl
