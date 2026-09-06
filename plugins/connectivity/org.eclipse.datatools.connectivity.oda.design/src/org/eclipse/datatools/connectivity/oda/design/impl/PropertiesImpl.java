/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *
 *************************************************************************
 *
 * $Id: PropertiesImpl.java,v 1.6 2007/04/11 02:59:52 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import java.util.Collection;

import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.datatools.connectivity.oda.design.Property;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Properties</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.PropertiesImpl#getProperties <em>Properties</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PropertiesImpl extends EObjectImpl implements Properties {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertiesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DesignPackage.Literals.PROPERTIES;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.Properties#findProperty(java.lang.String)
	 * @generated NOT
	 */
	@Override
	public Property findProperty(String propName) {
		if (isEmpty()) {
			return null;
		}

		for (Property prop : getProperties()) {
			if (propName.equalsIgnoreCase(prop.getName())) {
				return prop; // matching property name
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.Properties#getProperty(java.lang.String)
	 * @generated NOT
	 */
	@Override
	public String getProperty(String propName) {
		Property prop = findProperty(propName);
		if (prop != null) {
			return prop.getValue();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.Properties#setProperty(java.lang.String, java.lang.String)
	 * @generated NOT
	 */
	@Override
	public void setProperty(String propName, String propValue) {
		Property prop = findProperty(propName);
		if (prop != null) {
			prop.setNameValue(propName, propValue);
			return; // done
		}

		// no existing property with given name, add a new one
		prop = DesignFactory.eINSTANCE.createProperty();
		prop.setNameValue(propName, propValue);
		getProperties().add(prop);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.Properties#unsetProperty(java.lang.String)
	 * @generated NOT
	 */
	@Override
	public void unsetProperty(String propName) {
		Property prop = findProperty(propName);
		if (prop == null) {
			return; // done, nothing to remove
		}
		getProperties().remove(prop);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.Properties#isEmpty()
	 * @generated NOT
	 */
	@Override
	public boolean isEmpty() {
		return properties == null || properties.isEmpty();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Property> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<>(Property.class, this,
					DesignPackage.PROPERTIES__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case DesignPackage.PROPERTIES__PROPERTIES:
			return ((InternalEList<?>) getProperties()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case DesignPackage.PROPERTIES__PROPERTIES:
			return getProperties();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case DesignPackage.PROPERTIES__PROPERTIES:
			getProperties().clear();
			getProperties().addAll((Collection<? extends Property>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case DesignPackage.PROPERTIES__PROPERTIES:
			getProperties().clear();
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
		case DesignPackage.PROPERTIES__PROPERTIES:
			return properties != null && !properties.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PropertiesImpl
