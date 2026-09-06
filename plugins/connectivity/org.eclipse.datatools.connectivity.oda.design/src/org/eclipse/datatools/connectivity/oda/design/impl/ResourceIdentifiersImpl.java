/**
 *************************************************************************
 * Copyright (c) 2008, 2009 Actuate Corporation.
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
 * $Id: ResourceIdentifiersImpl.java,v 1.1 2008/07/23 04:12:27 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Identifiers</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ResourceIdentifiersImpl#getApplResourceBaseURIString <em>Appl Resource Base URI String</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ResourceIdentifiersImpl#getDesignResourceBaseURIString <em>Design Resource Base URI String</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceIdentifiersImpl extends EObjectImpl implements ResourceIdentifiers {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getApplResourceBaseURIString() <em>Appl Resource Base URI String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplResourceBaseURIString()
	 * @generated
	 * @ordered
	 */
	protected static final String APPL_RESOURCE_BASE_URI_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getApplResourceBaseURIString() <em>Appl Resource Base URI String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplResourceBaseURIString()
	 * @generated
	 * @ordered
	 */
	protected String applResourceBaseURIString = APPL_RESOURCE_BASE_URI_STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getDesignResourceBaseURIString() <em>Design Resource Base URI String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDesignResourceBaseURIString()
	 * @generated
	 * @ordered
	 */
	protected static final String DESIGN_RESOURCE_BASE_URI_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDesignResourceBaseURIString() <em>Design Resource Base URI String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDesignResourceBaseURIString()
	 * @generated
	 * @ordered
	 */
	protected String designResourceBaseURIString = DESIGN_RESOURCE_BASE_URI_STRING_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceIdentifiersImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DesignPackage.Literals.RESOURCE_IDENTIFIERS;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers#getApplResourceBaseURI()
	 * @generated NOT
	 */
	@Override
	public URI getApplResourceBaseURI() {
		String uriString = getApplResourceBaseURIString();
		return convertToURI(uriString);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers#setApplResourceBaseURI(java.net.URI)
	 */
	@Override
	public void setApplResourceBaseURI(URI baseURI) {
		String baseURIString = (baseURI != null) ? baseURI.toString() : null;
		setApplResourceBaseURIString(baseURIString);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getApplResourceBaseURIString() {
		return applResourceBaseURIString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setApplResourceBaseURIString(String newApplResourceBaseURIString) {
		String oldApplResourceBaseURIString = applResourceBaseURIString;
		applResourceBaseURIString = newApplResourceBaseURIString;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					DesignPackage.RESOURCE_IDENTIFIERS__APPL_RESOURCE_BASE_URI_STRING, oldApplResourceBaseURIString,
					applResourceBaseURIString));
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers#getDesignResourceBaseURI()
	 * @generated NOT
	 */
	@Override
	public URI getDesignResourceBaseURI() {
		String uriString = getDesignResourceBaseURIString();
		return convertToURI(uriString);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers#setDesignResourceBaseURI(java.net.URI)
	 */
	@Override
	public void setDesignResourceBaseURI(URI baseURI) {
		String baseURIString = (baseURI != null) ? baseURI.toString() : null;
		setDesignResourceBaseURIString(baseURIString);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDesignResourceBaseURIString() {
		return designResourceBaseURIString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDesignResourceBaseURIString(String newDesignResourceBaseURIString) {
		String oldDesignResourceBaseURIString = designResourceBaseURIString;
		designResourceBaseURIString = newDesignResourceBaseURIString;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					DesignPackage.RESOURCE_IDENTIFIERS__DESIGN_RESOURCE_BASE_URI_STRING, oldDesignResourceBaseURIString,
					designResourceBaseURIString));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case DesignPackage.RESOURCE_IDENTIFIERS__APPL_RESOURCE_BASE_URI_STRING:
			return getApplResourceBaseURIString();
		case DesignPackage.RESOURCE_IDENTIFIERS__DESIGN_RESOURCE_BASE_URI_STRING:
			return getDesignResourceBaseURIString();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case DesignPackage.RESOURCE_IDENTIFIERS__APPL_RESOURCE_BASE_URI_STRING:
			setApplResourceBaseURIString((String) newValue);
			return;
		case DesignPackage.RESOURCE_IDENTIFIERS__DESIGN_RESOURCE_BASE_URI_STRING:
			setDesignResourceBaseURIString((String) newValue);
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
		case DesignPackage.RESOURCE_IDENTIFIERS__APPL_RESOURCE_BASE_URI_STRING:
			setApplResourceBaseURIString(APPL_RESOURCE_BASE_URI_STRING_EDEFAULT);
			return;
		case DesignPackage.RESOURCE_IDENTIFIERS__DESIGN_RESOURCE_BASE_URI_STRING:
			setDesignResourceBaseURIString(DESIGN_RESOURCE_BASE_URI_STRING_EDEFAULT);
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
		case DesignPackage.RESOURCE_IDENTIFIERS__APPL_RESOURCE_BASE_URI_STRING:
			return APPL_RESOURCE_BASE_URI_STRING_EDEFAULT == null ? applResourceBaseURIString != null
					: !APPL_RESOURCE_BASE_URI_STRING_EDEFAULT.equals(applResourceBaseURIString);
		case DesignPackage.RESOURCE_IDENTIFIERS__DESIGN_RESOURCE_BASE_URI_STRING:
			return DESIGN_RESOURCE_BASE_URI_STRING_EDEFAULT == null ? designResourceBaseURIString != null
					: !DESIGN_RESOURCE_BASE_URI_STRING_EDEFAULT.equals(designResourceBaseURIString);
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
		result.append(" (applResourceBaseURIString: "); //$NON-NLS-1$
		result.append(applResourceBaseURIString);
		result.append(", designResourceBaseURIString: "); //$NON-NLS-1$
		result.append(designResourceBaseURIString);
		result.append(')');
		return result.toString();
	}

	/**
	 * @generated NOT
	 */
	private URI convertToURI(String uriString) {
		if (uriString == null) {
			return null;
		}

		try {
			return new URI(uriString);
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
		}

		return null;
	}

} //ResourceIdentifiersImpl