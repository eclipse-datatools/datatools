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
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.FunctionImpl#getReturnScaler <em>Return Scaler</em>}</li>
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
	protected RoutineResultTable returnTable = null;

	/**
	 * The cached value of the '{@link #getReturnScaler() <em>Return Scaler</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnScaler()
	 * @generated
	 * @ordered
	 */
	protected Parameter returnScaler = null;

	/**
	 * The cached value of the '{@link #getReturnCast() <em>Return Cast</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnCast()
	 * @generated
	 * @ordered
	 */
	protected Parameter returnCast = null;

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
		return SQLRoutinesPackage.eINSTANCE.getFunction();
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
	public Parameter getReturnScaler() {
		return returnScaler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReturnScaler(Parameter newReturnScaler, NotificationChain msgs) {
		Parameter oldReturnScaler = returnScaler;
		returnScaler = newReturnScaler;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.FUNCTION__RETURN_SCALER, oldReturnScaler, newReturnScaler);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnScaler(Parameter newReturnScaler) {
		if (newReturnScaler != returnScaler) {
			NotificationChain msgs = null;
			if (returnScaler != null)
				msgs = ((InternalEObject)returnScaler).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLRoutinesPackage.FUNCTION__RETURN_SCALER, null, msgs);
			if (newReturnScaler != null)
				msgs = ((InternalEObject)newReturnScaler).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLRoutinesPackage.FUNCTION__RETURN_SCALER, null, msgs);
			msgs = basicSetReturnScaler(newReturnScaler, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.FUNCTION__RETURN_SCALER, newReturnScaler, newReturnScaler));
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLRoutinesPackage.FUNCTION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLRoutinesPackage.FUNCTION__PARAMETERS:
					return ((InternalEList)getParameters()).basicAdd(otherEnd, msgs);
				case SQLRoutinesPackage.FUNCTION__SCHEMA:
					if (schema != null)
						msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__ROUTINES, Schema.class, msgs);
					return basicSetSchema((Schema)otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLRoutinesPackage.FUNCTION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLRoutinesPackage.FUNCTION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLRoutinesPackage.FUNCTION__PARAMETERS:
					return ((InternalEList)getParameters()).basicRemove(otherEnd, msgs);
				case SQLRoutinesPackage.FUNCTION__SOURCE:
					return basicSetSource(null, msgs);
				case SQLRoutinesPackage.FUNCTION__SCHEMA:
					return basicSetSchema(null, msgs);
				case SQLRoutinesPackage.FUNCTION__RETURN_TABLE:
					return basicSetReturnTable(null, msgs);
				case SQLRoutinesPackage.FUNCTION__RETURN_SCALER:
					return basicSetReturnScaler(null, msgs);
				case SQLRoutinesPackage.FUNCTION__RETURN_CAST:
					return basicSetReturnCast(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLRoutinesPackage.FUNCTION__EANNOTATIONS:
				return getEAnnotations();
			case SQLRoutinesPackage.FUNCTION__NAME:
				return getName();
			case SQLRoutinesPackage.FUNCTION__DEPENDENCIES:
				return getDependencies();
			case SQLRoutinesPackage.FUNCTION__DESCRIPTION:
				return getDescription();
			case SQLRoutinesPackage.FUNCTION__LABEL:
				return getLabel();
			case SQLRoutinesPackage.FUNCTION__SPECIFIC_NAME:
				return getSpecificName();
			case SQLRoutinesPackage.FUNCTION__LANGUAGE:
				return getLanguage();
			case SQLRoutinesPackage.FUNCTION__PARAMETER_STYLE:
				return getParameterStyle();
			case SQLRoutinesPackage.FUNCTION__DETERMINISTIC:
				return isDeterministic() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.FUNCTION__SQL_DATA_ACCESS:
				return getSqlDataAccess();
			case SQLRoutinesPackage.FUNCTION__CREATION_TS:
				return getCreationTS();
			case SQLRoutinesPackage.FUNCTION__LAST_ALTERED_TS:
				return getLastAlteredTS();
			case SQLRoutinesPackage.FUNCTION__AUTHORIZATION_ID:
				return getAuthorizationID();
			case SQLRoutinesPackage.FUNCTION__SECURITY:
				return getSecurity();
			case SQLRoutinesPackage.FUNCTION__EXTERNAL_NAME:
				return getExternalName();
			case SQLRoutinesPackage.FUNCTION__PARAMETERS:
				return getParameters();
			case SQLRoutinesPackage.FUNCTION__SOURCE:
				return getSource();
			case SQLRoutinesPackage.FUNCTION__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
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
			case SQLRoutinesPackage.FUNCTION__RETURN_SCALER:
				return getReturnScaler();
			case SQLRoutinesPackage.FUNCTION__RETURN_CAST:
				return getReturnCast();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLRoutinesPackage.FUNCTION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__NAME:
				setName((String)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__SPECIFIC_NAME:
				setSpecificName((String)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__LANGUAGE:
				setLanguage((String)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__PARAMETER_STYLE:
				setParameterStyle((String)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__DETERMINISTIC:
				setDeterministic(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.FUNCTION__SQL_DATA_ACCESS:
				setSqlDataAccess((DataAccess)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__CREATION_TS:
				setCreationTS((String)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__LAST_ALTERED_TS:
				setLastAlteredTS((String)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__AUTHORIZATION_ID:
				setAuthorizationID((String)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__SECURITY:
				setSecurity((String)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__EXTERNAL_NAME:
				setExternalName((String)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__SOURCE:
				setSource((Source)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__SCHEMA:
				setSchema((Schema)newValue);
				return;
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
			case SQLRoutinesPackage.FUNCTION__RETURN_SCALER:
				setReturnScaler((Parameter)newValue);
				return;
			case SQLRoutinesPackage.FUNCTION__RETURN_CAST:
				setReturnCast((Parameter)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLRoutinesPackage.FUNCTION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLRoutinesPackage.FUNCTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLRoutinesPackage.FUNCTION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__SPECIFIC_NAME:
				setSpecificName(SPECIFIC_NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__LANGUAGE:
				setLanguage(LANGUAGE_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__PARAMETER_STYLE:
				setParameterStyle(PARAMETER_STYLE_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__DETERMINISTIC:
				setDeterministic(DETERMINISTIC_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__SQL_DATA_ACCESS:
				setSqlDataAccess(SQL_DATA_ACCESS_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__CREATION_TS:
				setCreationTS(CREATION_TS_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__LAST_ALTERED_TS:
				setLastAlteredTS(LAST_ALTERED_TS_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__AUTHORIZATION_ID:
				setAuthorizationID(AUTHORIZATION_ID_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__SECURITY:
				setSecurity(SECURITY_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__EXTERNAL_NAME:
				setExternalName(EXTERNAL_NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.FUNCTION__PARAMETERS:
				getParameters().clear();
				return;
			case SQLRoutinesPackage.FUNCTION__SOURCE:
				setSource((Source)null);
				return;
			case SQLRoutinesPackage.FUNCTION__SCHEMA:
				setSchema((Schema)null);
				return;
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
			case SQLRoutinesPackage.FUNCTION__RETURN_SCALER:
				setReturnScaler((Parameter)null);
				return;
			case SQLRoutinesPackage.FUNCTION__RETURN_CAST:
				setReturnCast((Parameter)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLRoutinesPackage.FUNCTION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLRoutinesPackage.FUNCTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLRoutinesPackage.FUNCTION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLRoutinesPackage.FUNCTION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLRoutinesPackage.FUNCTION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLRoutinesPackage.FUNCTION__SPECIFIC_NAME:
				return SPECIFIC_NAME_EDEFAULT == null ? specificName != null : !SPECIFIC_NAME_EDEFAULT.equals(specificName);
			case SQLRoutinesPackage.FUNCTION__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
			case SQLRoutinesPackage.FUNCTION__PARAMETER_STYLE:
				return PARAMETER_STYLE_EDEFAULT == null ? parameterStyle != null : !PARAMETER_STYLE_EDEFAULT.equals(parameterStyle);
			case SQLRoutinesPackage.FUNCTION__DETERMINISTIC:
				return deterministic != DETERMINISTIC_EDEFAULT;
			case SQLRoutinesPackage.FUNCTION__SQL_DATA_ACCESS:
				return sqlDataAccess != SQL_DATA_ACCESS_EDEFAULT;
			case SQLRoutinesPackage.FUNCTION__CREATION_TS:
				return CREATION_TS_EDEFAULT == null ? creationTS != null : !CREATION_TS_EDEFAULT.equals(creationTS);
			case SQLRoutinesPackage.FUNCTION__LAST_ALTERED_TS:
				return LAST_ALTERED_TS_EDEFAULT == null ? lastAlteredTS != null : !LAST_ALTERED_TS_EDEFAULT.equals(lastAlteredTS);
			case SQLRoutinesPackage.FUNCTION__AUTHORIZATION_ID:
				return AUTHORIZATION_ID_EDEFAULT == null ? authorizationID != null : !AUTHORIZATION_ID_EDEFAULT.equals(authorizationID);
			case SQLRoutinesPackage.FUNCTION__SECURITY:
				return SECURITY_EDEFAULT == null ? security != null : !SECURITY_EDEFAULT.equals(security);
			case SQLRoutinesPackage.FUNCTION__EXTERNAL_NAME:
				return EXTERNAL_NAME_EDEFAULT == null ? externalName != null : !EXTERNAL_NAME_EDEFAULT.equals(externalName);
			case SQLRoutinesPackage.FUNCTION__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case SQLRoutinesPackage.FUNCTION__SOURCE:
				return source != null;
			case SQLRoutinesPackage.FUNCTION__SCHEMA:
				return schema != null;
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
			case SQLRoutinesPackage.FUNCTION__RETURN_SCALER:
				return returnScaler != null;
			case SQLRoutinesPackage.FUNCTION__RETURN_CAST:
				return returnCast != null;
		}
		return eDynamicIsSet(eFeature);
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
