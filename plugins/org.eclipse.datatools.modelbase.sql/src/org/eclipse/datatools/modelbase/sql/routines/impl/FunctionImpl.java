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
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.FunctionImpl#isNullCall <em>Null Call</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.FunctionImpl#isStatic <em>Static</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.FunctionImpl#getTransformGroup <em>Transform Group</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.FunctionImpl#isTypePreserving <em>Type Preserving</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.FunctionImpl#isMutator <em>Mutator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.FunctionImpl#getReturnTable <em>Return Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.FunctionImpl#getReturnScalar <em>Return Scalar</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.FunctionImpl#getReturnCast <em>Return Cast</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionImpl extends RoutineImpl implements Function {
	/**
	 * The default value of the '{@link #isNullCall() <em>Null Call</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullCall()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NULL_CALL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNullCall() <em>Null Call</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullCall()
	 * @generated
	 * @ordered
	 */
	protected boolean nullCall = NULL_CALL_EDEFAULT;

	/**
	 * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STATIC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected boolean static_ = STATIC_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransformGroup() <em>Transform Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformGroup()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSFORM_GROUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTransformGroup() <em>Transform Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformGroup()
	 * @generated
	 * @ordered
	 */
	protected String transformGroup = TRANSFORM_GROUP_EDEFAULT;

	/**
	 * The default value of the '{@link #isTypePreserving() <em>Type Preserving</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTypePreserving()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TYPE_PRESERVING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTypePreserving() <em>Type Preserving</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTypePreserving()
	 * @generated
	 * @ordered
	 */
	protected boolean typePreserving = TYPE_PRESERVING_EDEFAULT;

	/**
	 * The default value of the '{@link #isMutator() <em>Mutator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMutator()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MUTATOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMutator() <em>Mutator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMutator()
	 * @generated
	 * @ordered
	 */
	protected boolean mutator = MUTATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReturnTable() <em>Return Table</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnTable()
	 * @generated
	 * @ordered
	 */
	protected RoutineResultTable returnTable;

	/**
	 * The cached value of the '{@link #getReturnScalar() <em>Return Scalar</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnScalar()
	 * @generated
	 * @ordered
	 */
	protected Parameter returnScalar;

	/**
	 * The cached value of the '{@link #getReturnCast() <em>Return Cast</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnCast()
	 * @generated
	 * @ordered
	 */
	protected Parameter returnCast;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FunctionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLRoutinesPackage.Literals.FUNCTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNullCall() {
		return nullCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNullCall(boolean newNullCall) {
		boolean oldNullCall = nullCall;
		nullCall = newNullCall;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.FUNCTION__NULL_CALL, oldNullCall, nullCall));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStatic() {
		return static_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatic(boolean newStatic) {
		boolean oldStatic = static_;
		static_ = newStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.FUNCTION__STATIC, oldStatic, static_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTransformGroup() {
		return transformGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransformGroup(String newTransformGroup) {
		String oldTransformGroup = transformGroup;
		transformGroup = newTransformGroup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.FUNCTION__TRANSFORM_GROUP, oldTransformGroup, transformGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTypePreserving() {
		return typePreserving;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypePreserving(boolean newTypePreserving) {
		boolean oldTypePreserving = typePreserving;
		typePreserving = newTypePreserving;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.FUNCTION__TYPE_PRESERVING, oldTypePreserving, typePreserving));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMutator() {
		return mutator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMutator(boolean newMutator) {
		boolean oldMutator = mutator;
		mutator = newMutator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.FUNCTION__MUTATOR, oldMutator, mutator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoutineResultTable getReturnTable() {
		return returnTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReturnTable(RoutineResultTable newReturnTable, NotificationChain msgs) {
		RoutineResultTable oldReturnTable = returnTable;
		returnTable = newReturnTable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.FUNCTION__RETURN_TABLE, oldReturnTable, newReturnTable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnTable(RoutineResultTable newReturnTable) {
		if (newReturnTable != returnTable) {
			NotificationChain msgs = null;
			if (returnTable != null)
				msgs = ((InternalEObject)returnTable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLRoutinesPackage.FUNCTION__RETURN_TABLE, null, msgs);
			if (newReturnTable != null)
				msgs = ((InternalEObject)newReturnTable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLRoutinesPackage.FUNCTION__RETURN_TABLE, null, msgs);
			msgs = basicSetReturnTable(newReturnTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.FUNCTION__RETURN_TABLE, newReturnTable, newReturnTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter getReturnScalar() {
		return returnScalar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReturnScalar(Parameter newReturnScalar, NotificationChain msgs) {
		Parameter oldReturnScalar = returnScalar;
		returnScalar = newReturnScalar;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.FUNCTION__RETURN_SCALAR, oldReturnScalar, newReturnScalar);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnScalar(Parameter newReturnScalar) {
		if (newReturnScalar != returnScalar) {
			NotificationChain msgs = null;
			if (returnScalar != null)
				msgs = ((InternalEObject)returnScalar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLRoutinesPackage.FUNCTION__RETURN_SCALAR, null, msgs);
			if (newReturnScalar != null)
				msgs = ((InternalEObject)newReturnScalar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLRoutinesPackage.FUNCTION__RETURN_SCALAR, null, msgs);
			msgs = basicSetReturnScalar(newReturnScalar, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.FUNCTION__RETURN_SCALAR, newReturnScalar, newReturnScalar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter getReturnCast() {
		return returnCast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReturnCast(Parameter newReturnCast, NotificationChain msgs) {
		Parameter oldReturnCast = returnCast;
		returnCast = newReturnCast;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.FUNCTION__RETURN_CAST, oldReturnCast, newReturnCast);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnCast(Parameter newReturnCast) {
		if (newReturnCast != returnCast) {
			NotificationChain msgs = null;
			if (returnCast != null)
				msgs = ((InternalEObject)returnCast).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLRoutinesPackage.FUNCTION__RETURN_CAST, null, msgs);
			if (newReturnCast != null)
				msgs = ((InternalEObject)newReturnCast).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLRoutinesPackage.FUNCTION__RETURN_CAST, null, msgs);
			msgs = basicSetReturnCast(newReturnCast, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.FUNCTION__RETURN_CAST, newReturnCast, newReturnCast));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLRoutinesPackage.FUNCTION__RETURN_TABLE:
				return basicSetReturnTable(null, msgs);
			case SQLRoutinesPackage.FUNCTION__RETURN_SCALAR:
				return basicSetReturnScalar(null, msgs);
			case SQLRoutinesPackage.FUNCTION__RETURN_CAST:
				return basicSetReturnCast(null, msgs);
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
			case SQLRoutinesPackage.FUNCTION__NULL_CALL:
				return isNullCall() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.FUNCTION__STATIC:
				return isStatic() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.FUNCTION__TRANSFORM_GROUP:
				return getTransformGroup();
			case SQLRoutinesPackage.FUNCTION__TYPE_PRESERVING:
				return isTypePreserving() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.FUNCTION__MUTATOR:
				return isMutator() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.FUNCTION__RETURN_TABLE:
				return getReturnTable();
			case SQLRoutinesPackage.FUNCTION__RETURN_SCALAR:
				return getReturnScalar();
			case SQLRoutinesPackage.FUNCTION__RETURN_CAST:
				return getReturnCast();
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
			case SQLRoutinesPackage.FUNCTION__NULL_CALL:
				setNullCall(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.FUNCTION__STATIC:
				setStatic(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.FUNCTION__TRANSFORM_GROUP:
				setTransformGroup((String)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__TYPE_PRESERVING:
				setTypePreserving(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.FUNCTION__MUTATOR:
				setMutator(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.FUNCTION__RETURN_TABLE:
				setReturnTable((RoutineResultTable)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__RETURN_SCALAR:
				setReturnScalar((Parameter)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__RETURN_CAST:
				setReturnCast((Parameter)newValue);
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
			case SQLRoutinesPackage.FUNCTION__NULL_CALL:
				setNullCall(NULL_CALL_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__STATIC:
				setStatic(STATIC_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__TRANSFORM_GROUP:
				setTransformGroup(TRANSFORM_GROUP_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__TYPE_PRESERVING:
				setTypePreserving(TYPE_PRESERVING_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__MUTATOR:
				setMutator(MUTATOR_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__RETURN_TABLE:
				setReturnTable((RoutineResultTable)null);
				return;
			case SQLRoutinesPackage.FUNCTION__RETURN_SCALAR:
				setReturnScalar((Parameter)null);
				return;
			case SQLRoutinesPackage.FUNCTION__RETURN_CAST:
				setReturnCast((Parameter)null);
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
			case SQLRoutinesPackage.FUNCTION__NULL_CALL:
				return nullCall != NULL_CALL_EDEFAULT;
			case SQLRoutinesPackage.FUNCTION__STATIC:
				return static_ != STATIC_EDEFAULT;
			case SQLRoutinesPackage.FUNCTION__TRANSFORM_GROUP:
				return TRANSFORM_GROUP_EDEFAULT == null ? transformGroup != null : !TRANSFORM_GROUP_EDEFAULT.equals(transformGroup);
			case SQLRoutinesPackage.FUNCTION__TYPE_PRESERVING:
				return typePreserving != TYPE_PRESERVING_EDEFAULT;
			case SQLRoutinesPackage.FUNCTION__MUTATOR:
				return mutator != MUTATOR_EDEFAULT;
			case SQLRoutinesPackage.FUNCTION__RETURN_TABLE:
				return returnTable != null;
			case SQLRoutinesPackage.FUNCTION__RETURN_SCALAR:
				return returnScalar != null;
			case SQLRoutinesPackage.FUNCTION__RETURN_CAST:
				return returnCast != null;
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
		result.append(" (nullCall: "); //$NON-NLS-1$
		result.append(nullCall);
		result.append(", static: "); //$NON-NLS-1$
		result.append(static_);
		result.append(", transformGroup: "); //$NON-NLS-1$
		result.append(transformGroup);
		result.append(", typePreserving: "); //$NON-NLS-1$
		result.append(typePreserving);
		result.append(", mutator: "); //$NON-NLS-1$
		result.append(mutator);
		result.append(')');
		return result.toString();
	}

} //FunctionImpl
