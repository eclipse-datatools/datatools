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
package org.eclipse.datatools.modelbase.sql.routines.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.routines.DataAccess;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Procedure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.ProcedureImpl#getMaxResultSets <em>Max Result Sets</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.ProcedureImpl#isOldSavePoint <em>Old Save Point</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.ProcedureImpl#getResultSet <em>Result Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcedureImpl extends RoutineImpl implements Procedure {
	/**
	 * The default value of the '{@link #getMaxResultSets() <em>Max Result Sets</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxResultSets()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_RESULT_SETS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaxResultSets() <em>Max Result Sets</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxResultSets()
	 * @generated
	 * @ordered
	 */
	protected int maxResultSets = MAX_RESULT_SETS_EDEFAULT;

	/**
	 * The default value of the '{@link #isOldSavePoint() <em>Old Save Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOldSavePoint()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OLD_SAVE_POINT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOldSavePoint() <em>Old Save Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOldSavePoint()
	 * @generated
	 * @ordered
	 */
	protected boolean oldSavePoint = OLD_SAVE_POINT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getResultSet() <em>Result Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultSet()
	 * @generated
	 * @ordered
	 */
	protected EList resultSet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcedureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLRoutinesPackage.Literals.PROCEDURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxResultSets() {
		return maxResultSets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxResultSets(int newMaxResultSets) {
		int oldMaxResultSets = maxResultSets;
		maxResultSets = newMaxResultSets;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.PROCEDURE__MAX_RESULT_SETS, oldMaxResultSets, maxResultSets));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOldSavePoint() {
		return oldSavePoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOldSavePoint(boolean newOldSavePoint) {
		boolean oldOldSavePoint = oldSavePoint;
		oldSavePoint = newOldSavePoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.PROCEDURE__OLD_SAVE_POINT, oldOldSavePoint, oldSavePoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getResultSet() {
		if (resultSet == null) {
			resultSet = new EObjectContainmentEList(RoutineResultTable.class, this, SQLRoutinesPackage.PROCEDURE__RESULT_SET);
		}
		return resultSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLRoutinesPackage.PROCEDURE__RESULT_SET:
				return ((InternalEList)getResultSet()).basicRemove(otherEnd, msgs);
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
			case SQLRoutinesPackage.PROCEDURE__MAX_RESULT_SETS:
				return new Integer(getMaxResultSets());
			case SQLRoutinesPackage.PROCEDURE__OLD_SAVE_POINT:
				return isOldSavePoint() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.PROCEDURE__RESULT_SET:
				return getResultSet();
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
			case SQLRoutinesPackage.PROCEDURE__MAX_RESULT_SETS:
				setMaxResultSets(((Integer)newValue).intValue());
				return;
			case SQLRoutinesPackage.PROCEDURE__OLD_SAVE_POINT:
				setOldSavePoint(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.PROCEDURE__RESULT_SET:
				getResultSet().clear();
				getResultSet().addAll((Collection)newValue);
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
			case SQLRoutinesPackage.PROCEDURE__MAX_RESULT_SETS:
				setMaxResultSets(MAX_RESULT_SETS_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__OLD_SAVE_POINT:
				setOldSavePoint(OLD_SAVE_POINT_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__RESULT_SET:
				getResultSet().clear();
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
			case SQLRoutinesPackage.PROCEDURE__MAX_RESULT_SETS:
				return maxResultSets != MAX_RESULT_SETS_EDEFAULT;
			case SQLRoutinesPackage.PROCEDURE__OLD_SAVE_POINT:
				return oldSavePoint != OLD_SAVE_POINT_EDEFAULT;
			case SQLRoutinesPackage.PROCEDURE__RESULT_SET:
				return resultSet != null && !resultSet.isEmpty();
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
		result.append(" (maxResultSets: "); //$NON-NLS-1$
		result.append(maxResultSets);
		result.append(", oldSavePoint: "); //$NON-NLS-1$
		result.append(oldSavePoint);
		result.append(')');
		return result.toString();
	}

} //ProcedureImpl
