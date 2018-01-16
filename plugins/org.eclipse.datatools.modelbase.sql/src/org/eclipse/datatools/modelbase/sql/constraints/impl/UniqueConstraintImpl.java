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
package org.eclipse.datatools.modelbase.sql.constraints.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unique Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.UniqueConstraintImpl#isClustered <em>Clustered</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.UniqueConstraintImpl#getForeignKey <em>Foreign Key</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UniqueConstraintImpl extends ReferenceConstraintImpl implements UniqueConstraint {
	/**
	 * The default value of the '{@link #isClustered() <em>Clustered</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isClustered()
	 * @generated
	 * @ordered
	 */
    protected static final boolean CLUSTERED_EDEFAULT = true;
    /**
	 * The cached value of the '{@link #isClustered() <em>Clustered</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isClustered()
	 * @generated
	 * @ordered
	 */
    protected boolean clustered = CLUSTERED_EDEFAULT;
    /**
	 * The cached value of the '{@link #getForeignKey() <em>Foreign Key</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForeignKey()
	 * @generated
	 * @ordered
	 */
	protected EList foreignKey;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UniqueConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLConstraintsPackage.Literals.UNIQUE_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isClustered() {
		return clustered;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setClustered(boolean newClustered) {
		boolean oldClustered = clustered;
		clustered = newClustered;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.UNIQUE_CONSTRAINT__CLUSTERED, oldClustered, clustered));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getForeignKey() {
		if (foreignKey == null) {
			foreignKey = new EObjectWithInverseResolvingEList(ForeignKey.class, this, SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY, SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_CONSTRAINT);
		}
		return foreignKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY:
				return ((InternalEList)getForeignKey()).basicAdd(otherEnd, msgs);
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
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY:
				return ((InternalEList)getForeignKey()).basicRemove(otherEnd, msgs);
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
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__CLUSTERED:
				return isClustered() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY:
				return getForeignKey();
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
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__CLUSTERED:
				setClustered(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY:
				getForeignKey().clear();
				getForeignKey().addAll((Collection)newValue);
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
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__CLUSTERED:
				setClustered(CLUSTERED_EDEFAULT);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY:
				getForeignKey().clear();
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
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__CLUSTERED:
				return clustered != CLUSTERED_EDEFAULT;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY:
				return foreignKey != null && !foreignKey.isEmpty();
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
		result.append(" (clustered: "); //$NON-NLS-1$
		result.append(clustered);
		result.append(')');
		return result.toString();
	}

} //UniqueConstraintImpl
