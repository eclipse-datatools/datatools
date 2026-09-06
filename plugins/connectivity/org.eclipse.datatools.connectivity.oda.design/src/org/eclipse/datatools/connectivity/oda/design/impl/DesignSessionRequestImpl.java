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
 * $Id: DesignSessionRequestImpl.java,v 1.5 2007/04/11 02:59:52 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DataAccessDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.DesignerState;
import org.eclipse.datatools.connectivity.oda.design.Locale;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.ibm.icu.util.ULocale;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Session Request</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DesignSessionRequestImpl#getDataAccessDesign <em>Data Access Design</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DesignSessionRequestImpl#isEditable <em>Editable</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DesignSessionRequestImpl#getSessionLocale <em>Session Locale</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DesignSessionRequestImpl#getDesignerState <em>Designer State</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DesignSessionRequestImpl extends EObjectImpl implements DesignSessionRequest {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getDataAccessDesign() <em>Data Access Design</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataAccessDesign()
	 * @generated
	 * @ordered
	 */
	protected DataAccessDesign dataAccessDesign;

	/**
	 * The default value of the '{@link #isEditable() <em>Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEditable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EDITABLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEditable() <em>Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEditable()
	 * @generated
	 * @ordered
	 */
	protected boolean editable = EDITABLE_EDEFAULT;

	/**
	 * This is true if the Editable attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean editableESet;

	/**
	 * The cached value of the '{@link #getSessionLocale() <em>Session Locale</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSessionLocale()
	 * @generated
	 * @ordered
	 */
	protected Locale sessionLocale;

	/**
	 * The cached value of the '{@link #getDesignerState() <em>Designer State</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDesignerState()
	 * @generated
	 * @ordered
	 */
	protected DesignerState designerState;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DesignSessionRequestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DesignPackage.Literals.DESIGN_SESSION_REQUEST;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#setNewDataAccessDesign(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
	 * @generated NOT
	 */
	@Override
	public void setNewDataAccessDesign(DataSourceDesign dataSourceDesign) {
		setSessionLocale(createDefaultLocale());

		DataAccessDesign newAccessDesign = DesignFactory.eINSTANCE.createDataAccessDesign();
		newAccessDesign.setNewDataSetDesign(dataSourceDesign);

		setDataAccessDesign(newAccessDesign);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#setNewDataAccessDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 * @generated NOT
	 */
	@Override
	public void setNewDataAccessDesign(DataSetDesign dataSetDesign) {
		setSessionLocale(createDefaultLocale());

		DataAccessDesign newAccessDesign = DesignFactory.eINSTANCE.createDataAccessDesign();
		newAccessDesign.setDataSetDesign(dataSetDesign);

		setDataAccessDesign(newAccessDesign);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#getDataSourceDesign()
	 * @generated NOT
	 */
	@Override
	public DataSourceDesign getDataSourceDesign() {
		if (getDataSetDesign() == null) {
			return null;
		}

		return getDataSetDesign().getDataSourceDesign();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#getDataSetDesign()
	 * @generated NOT
	 */
	@Override
	public DataSetDesign getDataSetDesign() {
		if (getDataAccessDesign() == null) {
			return null;
		}

		return getDataAccessDesign().getDataSetDesign();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DataAccessDesign getDataAccessDesign() {
		return dataAccessDesign;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataAccessDesign(DataAccessDesign newDataAccessDesign, NotificationChain msgs) {
		DataAccessDesign oldDataAccessDesign = dataAccessDesign;
		dataAccessDesign = newDataAccessDesign;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					DesignPackage.DESIGN_SESSION_REQUEST__DATA_ACCESS_DESIGN, oldDataAccessDesign, newDataAccessDesign);
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
	public void setDataAccessDesign(DataAccessDesign newDataAccessDesign) {
		if (newDataAccessDesign != dataAccessDesign) {
			NotificationChain msgs = null;
			if (dataAccessDesign != null) {
				msgs = ((InternalEObject) dataAccessDesign).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.DESIGN_SESSION_REQUEST__DATA_ACCESS_DESIGN, null, msgs);
			}
			if (newDataAccessDesign != null) {
				msgs = ((InternalEObject) newDataAccessDesign).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.DESIGN_SESSION_REQUEST__DATA_ACCESS_DESIGN, null, msgs);
			}
			msgs = basicSetDataAccessDesign(newDataAccessDesign, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					DesignPackage.DESIGN_SESSION_REQUEST__DATA_ACCESS_DESIGN, newDataAccessDesign,
					newDataAccessDesign));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isEditable() {
		return editable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEditable(boolean newEditable) {
		boolean oldEditable = editable;
		editable = newEditable;
		boolean oldEditableESet = editableESet;
		editableESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DESIGN_SESSION_REQUEST__EDITABLE,
					oldEditable, editable, !oldEditableESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetEditable() {
		boolean oldEditable = editable;
		boolean oldEditableESet = editableESet;
		editable = EDITABLE_EDEFAULT;
		editableESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, DesignPackage.DESIGN_SESSION_REQUEST__EDITABLE,
					oldEditable, EDITABLE_EDEFAULT, oldEditableESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetEditable() {
		return editableESet;
	}

	/**
	 * Returns the current session's locale.
	 * If none is specified, returns the system default locale.
	 * @generated NOT
	 */
	@Override
	public Locale getSessionLocale() {
		Locale sessionLocale = getSessionLocaleGen();
		if (sessionLocale != null) {
			return sessionLocale;
		}

		sessionLocale = DesignFactory.eINSTANCE.createLocale();
		sessionLocale.setLocale(ULocale.getDefault());
		return sessionLocale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Locale getSessionLocaleGen() {
		return sessionLocale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSessionLocale(Locale newSessionLocale, NotificationChain msgs) {
		Locale oldSessionLocale = sessionLocale;
		sessionLocale = newSessionLocale;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					DesignPackage.DESIGN_SESSION_REQUEST__SESSION_LOCALE, oldSessionLocale, newSessionLocale);
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
	public void setSessionLocale(Locale newSessionLocale) {
		if (newSessionLocale != sessionLocale) {
			NotificationChain msgs = null;
			if (sessionLocale != null) {
				msgs = ((InternalEObject) sessionLocale).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.DESIGN_SESSION_REQUEST__SESSION_LOCALE, null, msgs);
			}
			if (newSessionLocale != null) {
				msgs = ((InternalEObject) newSessionLocale).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.DESIGN_SESSION_REQUEST__SESSION_LOCALE, null, msgs);
			}
			msgs = basicSetSessionLocale(newSessionLocale, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DESIGN_SESSION_REQUEST__SESSION_LOCALE,
					newSessionLocale, newSessionLocale));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DesignerState getDesignerState() {
		return designerState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDesignerState(DesignerState newDesignerState, NotificationChain msgs) {
		DesignerState oldDesignerState = designerState;
		designerState = newDesignerState;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					DesignPackage.DESIGN_SESSION_REQUEST__DESIGNER_STATE, oldDesignerState, newDesignerState);
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
	public void setDesignerState(DesignerState newDesignerState) {
		if (newDesignerState != designerState) {
			NotificationChain msgs = null;
			if (designerState != null) {
				msgs = ((InternalEObject) designerState).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.DESIGN_SESSION_REQUEST__DESIGNER_STATE, null, msgs);
			}
			if (newDesignerState != null) {
				msgs = ((InternalEObject) newDesignerState).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.DESIGN_SESSION_REQUEST__DESIGNER_STATE, null, msgs);
			}
			msgs = basicSetDesignerState(newDesignerState, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DESIGN_SESSION_REQUEST__DESIGNER_STATE,
					newDesignerState, newDesignerState));
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
		case DesignPackage.DESIGN_SESSION_REQUEST__DATA_ACCESS_DESIGN:
			return basicSetDataAccessDesign(null, msgs);
		case DesignPackage.DESIGN_SESSION_REQUEST__SESSION_LOCALE:
			return basicSetSessionLocale(null, msgs);
		case DesignPackage.DESIGN_SESSION_REQUEST__DESIGNER_STATE:
			return basicSetDesignerState(null, msgs);
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
		case DesignPackage.DESIGN_SESSION_REQUEST__DATA_ACCESS_DESIGN:
			return getDataAccessDesign();
		case DesignPackage.DESIGN_SESSION_REQUEST__EDITABLE:
			return isEditable();
		case DesignPackage.DESIGN_SESSION_REQUEST__SESSION_LOCALE:
			return getSessionLocale();
		case DesignPackage.DESIGN_SESSION_REQUEST__DESIGNER_STATE:
			return getDesignerState();
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
		case DesignPackage.DESIGN_SESSION_REQUEST__DATA_ACCESS_DESIGN:
			setDataAccessDesign((DataAccessDesign) newValue);
			return;
		case DesignPackage.DESIGN_SESSION_REQUEST__EDITABLE:
			setEditable((Boolean) newValue);
			return;
		case DesignPackage.DESIGN_SESSION_REQUEST__SESSION_LOCALE:
			setSessionLocale((Locale) newValue);
			return;
		case DesignPackage.DESIGN_SESSION_REQUEST__DESIGNER_STATE:
			setDesignerState((DesignerState) newValue);
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
		case DesignPackage.DESIGN_SESSION_REQUEST__DATA_ACCESS_DESIGN:
			setDataAccessDesign((DataAccessDesign) null);
			return;
		case DesignPackage.DESIGN_SESSION_REQUEST__EDITABLE:
			unsetEditable();
			return;
		case DesignPackage.DESIGN_SESSION_REQUEST__SESSION_LOCALE:
			setSessionLocale((Locale) null);
			return;
		case DesignPackage.DESIGN_SESSION_REQUEST__DESIGNER_STATE:
			setDesignerState((DesignerState) null);
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
		case DesignPackage.DESIGN_SESSION_REQUEST__DATA_ACCESS_DESIGN:
			return dataAccessDesign != null;
		case DesignPackage.DESIGN_SESSION_REQUEST__EDITABLE:
			return isSetEditable();
		case DesignPackage.DESIGN_SESSION_REQUEST__SESSION_LOCALE:
			return sessionLocale != null;
		case DesignPackage.DESIGN_SESSION_REQUEST__DESIGNER_STATE:
			return designerState != null;
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
		result.append(" (editable: "); //$NON-NLS-1$
		if (editableESet) {
			result.append(editable);
		} else {
			result.append("<unset>"); //$NON-NLS-1$
		}
		result.append(')');
		return result.toString();
	}

	/**
	 * Creates and returns a Locale instance with the
	 * default system locale settings.
	 * @return  a Locale instance with the system default locale settings
	 * @generated NOT
	 */
	protected Locale createDefaultLocale() {
		Locale sessionLocale = DesignFactory.eINSTANCE.createLocale();
		sessionLocale.setLocale(ULocale.getDefault());
		return sessionLocale;
	}

} //DesignSessionRequestImpl
