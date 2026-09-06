/**
 *************************************************************************
 * Copyright (c) 2005, 2010 Actuate Corporation.
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
 * $Id: AxisAttributesImpl.java,v 1.3 2009/04/24 03:20:26 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.AxisAttributes;
import org.eclipse.datatools.connectivity.oda.design.AxisType;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ResultSubset;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Axis Attributes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.AxisAttributesImpl#getAxisType <em>Axis Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.AxisAttributesImpl#isOnColumnLayout <em>On Column Layout</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.AxisAttributesImpl#getRelatedColumns <em>Related Columns</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AxisAttributesImpl extends EObjectImpl implements AxisAttributes {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getAxisType() <em>Axis Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAxisType()
	 * @generated
	 * @ordered
	 */
	protected static final AxisType AXIS_TYPE_EDEFAULT = AxisType.MEASURE_LITERAL;

	/**
	 * The cached value of the '{@link #getAxisType() <em>Axis Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAxisType()
	 * @generated
	 * @ordered
	 */
	protected AxisType axisType = AXIS_TYPE_EDEFAULT;

	/**
	 * This is true if the Axis Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean axisTypeESet;

	/**
	 * The default value of the '{@link #isOnColumnLayout() <em>On Column Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOnColumnLayout()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ON_COLUMN_LAYOUT_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isOnColumnLayout() <em>On Column Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOnColumnLayout()
	 * @generated
	 * @ordered
	 */
	protected boolean onColumnLayout = ON_COLUMN_LAYOUT_EDEFAULT;

	/**
	 * This is true if the On Column Layout attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean onColumnLayoutESet;

	/**
	 * The cached value of the '{@link #getRelatedColumns() <em>Related Columns</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelatedColumns()
	 * @generated
	 * @ordered
	 */
	protected ResultSubset relatedColumns;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AxisAttributesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DesignPackage.Literals.AXIS_ATTRIBUTES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AxisType getAxisType() {
		return axisType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAxisType(AxisType newAxisType) {
		AxisType oldAxisType = axisType;
		axisType = newAxisType == null ? AXIS_TYPE_EDEFAULT : newAxisType;
		boolean oldAxisTypeESet = axisTypeESet;
		axisTypeESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.AXIS_ATTRIBUTES__AXIS_TYPE, oldAxisType,
					axisType, !oldAxisTypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetAxisType() {
		AxisType oldAxisType = axisType;
		boolean oldAxisTypeESet = axisTypeESet;
		axisType = AXIS_TYPE_EDEFAULT;
		axisTypeESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, DesignPackage.AXIS_ATTRIBUTES__AXIS_TYPE,
					oldAxisType, AXIS_TYPE_EDEFAULT, oldAxisTypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetAxisType() {
		return axisTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isOnColumnLayout() {
		return onColumnLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOnColumnLayout(boolean newOnColumnLayout) {
		boolean oldOnColumnLayout = onColumnLayout;
		onColumnLayout = newOnColumnLayout;
		boolean oldOnColumnLayoutESet = onColumnLayoutESet;
		onColumnLayoutESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.AXIS_ATTRIBUTES__ON_COLUMN_LAYOUT,
					oldOnColumnLayout, onColumnLayout, !oldOnColumnLayoutESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetOnColumnLayout() {
		boolean oldOnColumnLayout = onColumnLayout;
		boolean oldOnColumnLayoutESet = onColumnLayoutESet;
		onColumnLayout = ON_COLUMN_LAYOUT_EDEFAULT;
		onColumnLayoutESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, DesignPackage.AXIS_ATTRIBUTES__ON_COLUMN_LAYOUT,
					oldOnColumnLayout, ON_COLUMN_LAYOUT_EDEFAULT, oldOnColumnLayoutESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetOnColumnLayout() {
		return onColumnLayoutESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResultSubset getRelatedColumns() {
		return relatedColumns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRelatedColumns(ResultSubset newRelatedColumns, NotificationChain msgs) {
		ResultSubset oldRelatedColumns = relatedColumns;
		relatedColumns = newRelatedColumns;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					DesignPackage.AXIS_ATTRIBUTES__RELATED_COLUMNS, oldRelatedColumns, newRelatedColumns);
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
	public void setRelatedColumns(ResultSubset newRelatedColumns) {
		if (newRelatedColumns != relatedColumns) {
			NotificationChain msgs = null;
			if (relatedColumns != null) {
				msgs = ((InternalEObject) relatedColumns).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.AXIS_ATTRIBUTES__RELATED_COLUMNS, null, msgs);
			}
			if (newRelatedColumns != null) {
				msgs = ((InternalEObject) newRelatedColumns).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.AXIS_ATTRIBUTES__RELATED_COLUMNS, null, msgs);
			}
			msgs = basicSetRelatedColumns(newRelatedColumns, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.AXIS_ATTRIBUTES__RELATED_COLUMNS,
					newRelatedColumns, newRelatedColumns));
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
		case DesignPackage.AXIS_ATTRIBUTES__RELATED_COLUMNS:
			return basicSetRelatedColumns(null, msgs);
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
		case DesignPackage.AXIS_ATTRIBUTES__AXIS_TYPE:
			return getAxisType();
		case DesignPackage.AXIS_ATTRIBUTES__ON_COLUMN_LAYOUT:
			return isOnColumnLayout();
		case DesignPackage.AXIS_ATTRIBUTES__RELATED_COLUMNS:
			return getRelatedColumns();
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
		case DesignPackage.AXIS_ATTRIBUTES__AXIS_TYPE:
			setAxisType((AxisType) newValue);
			return;
		case DesignPackage.AXIS_ATTRIBUTES__ON_COLUMN_LAYOUT:
			setOnColumnLayout((Boolean) newValue);
			return;
		case DesignPackage.AXIS_ATTRIBUTES__RELATED_COLUMNS:
			setRelatedColumns((ResultSubset) newValue);
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
		case DesignPackage.AXIS_ATTRIBUTES__AXIS_TYPE:
			unsetAxisType();
			return;
		case DesignPackage.AXIS_ATTRIBUTES__ON_COLUMN_LAYOUT:
			unsetOnColumnLayout();
			return;
		case DesignPackage.AXIS_ATTRIBUTES__RELATED_COLUMNS:
			setRelatedColumns((ResultSubset) null);
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
		case DesignPackage.AXIS_ATTRIBUTES__AXIS_TYPE:
			return isSetAxisType();
		case DesignPackage.AXIS_ATTRIBUTES__ON_COLUMN_LAYOUT:
			return isSetOnColumnLayout();
		case DesignPackage.AXIS_ATTRIBUTES__RELATED_COLUMNS:
			return relatedColumns != null;
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
		result.append(" (axisType: "); //$NON-NLS-1$
		if (axisTypeESet) {
			result.append(axisType);
		} else {
			result.append("<unset>"); //$NON-NLS-1$
		}
		result.append(", onColumnLayout: "); //$NON-NLS-1$
		if (onColumnLayoutESet) {
			result.append(onColumnLayout);
		} else {
			result.append("<unset>"); //$NON-NLS-1$
		}
		result.append(')');
		return result.toString();
	}

} //AxisAttributesImpl
