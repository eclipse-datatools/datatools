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
 * $Id: DesignerStateImpl.java,v 1.4 2007/04/11 02:59:52 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.DesignerState;
import org.eclipse.datatools.connectivity.oda.design.DesignerStateContent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>er State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DesignerStateImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DesignerStateImpl#getStateContent <em>State Content</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DesignerStateImpl extends EObjectImpl implements DesignerState {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStateContent() <em>State Content</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateContent()
	 * @generated
	 * @ordered
	 */
	protected DesignerStateContent stateContent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DesignerStateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DesignPackage.Literals.DESIGNER_STATE;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DesignerState#setStateContentAsString(java.lang.String)
	 * @generated NOT
	 */
	@Override
	public void setNewStateContentAsString(String value) {
		// creates a new state content with the given value,
		// overrides any existing content
		DesignerStateContent content = DesignFactory.eINSTANCE.createDesignerStateContent();
		content.setStateContentAsString(value);

		setStateContent(content);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DesignerState#setStateContentAsBlob(byte[])
	 * @generated NOT
	 */
	@Override
	public void setNewStateContentAsBlob(byte[] value) {
		// creates a new state content with the given value,
		// overrides any existing content
		DesignerStateContent content = DesignFactory.eINSTANCE.createDesignerStateContent();
		content.setStateContentAsBlob(value);

		setStateContent(content);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DESIGNER_STATE__VERSION, oldVersion,
					version));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DesignerStateContent getStateContent() {
		return stateContent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStateContent(DesignerStateContent newStateContent, NotificationChain msgs) {
		DesignerStateContent oldStateContent = stateContent;
		stateContent = newStateContent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					DesignPackage.DESIGNER_STATE__STATE_CONTENT, oldStateContent, newStateContent);
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
	public void setStateContent(DesignerStateContent newStateContent) {
		if (newStateContent != stateContent) {
			NotificationChain msgs = null;
			if (stateContent != null) {
				msgs = ((InternalEObject) stateContent).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.DESIGNER_STATE__STATE_CONTENT, null, msgs);
			}
			if (newStateContent != null) {
				msgs = ((InternalEObject) newStateContent).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.DESIGNER_STATE__STATE_CONTENT, null, msgs);
			}
			msgs = basicSetStateContent(newStateContent, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DESIGNER_STATE__STATE_CONTENT,
					newStateContent, newStateContent));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case DesignPackage.DESIGNER_STATE__STATE_CONTENT:
			return basicSetStateContent(null, msgs);
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
		case DesignPackage.DESIGNER_STATE__VERSION:
			return getVersion();
		case DesignPackage.DESIGNER_STATE__STATE_CONTENT:
			return getStateContent();
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
		case DesignPackage.DESIGNER_STATE__VERSION:
			setVersion((String) newValue);
			return;
		case DesignPackage.DESIGNER_STATE__STATE_CONTENT:
			setStateContent((DesignerStateContent) newValue);
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
		case DesignPackage.DESIGNER_STATE__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case DesignPackage.DESIGNER_STATE__STATE_CONTENT:
			setStateContent((DesignerStateContent) null);
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
		case DesignPackage.DESIGNER_STATE__VERSION:
			return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
		case DesignPackage.DESIGNER_STATE__STATE_CONTENT:
			return stateContent != null;
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
		result.append(" (version: "); //$NON-NLS-1$
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //DesignerStateImpl
