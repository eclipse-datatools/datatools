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
import org.eclipse.datatools.modelbase.sql.routines.Method;
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
 * An implementation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.MethodImpl#isOverriding <em>Overriding</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.MethodImpl#isConstructor <em>Constructor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodImpl extends FunctionImpl implements Method {
	/**
	 * The default value of the '{@link #isOverriding() <em>Overriding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverriding()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OVERRIDING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOverriding() <em>Overriding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverriding()
	 * @generated
	 * @ordered
	 */
	protected boolean overriding = OVERRIDING_EDEFAULT;

	/**
	 * The default value of the '{@link #isConstructor() <em>Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstructor()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONSTRUCTOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConstructor() <em>Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstructor()
	 * @generated
	 * @ordered
	 */
	protected boolean constructor = CONSTRUCTOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLRoutinesPackage.eINSTANCE.getMethod();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOverriding() {
		return overriding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverriding(boolean newOverriding) {
		boolean oldOverriding = overriding;
		overriding = newOverriding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.METHOD__OVERRIDING, oldOverriding, overriding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConstructor() {
		return constructor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstructor(boolean newConstructor) {
		boolean oldConstructor = constructor;
		constructor = newConstructor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.METHOD__CONSTRUCTOR, oldConstructor, constructor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLRoutinesPackage.METHOD__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLRoutinesPackage.METHOD__PARAMETERS:
					return ((InternalEList)getParameters()).basicAdd(otherEnd, msgs);
				case SQLRoutinesPackage.METHOD__SCHEMA:
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
				case SQLRoutinesPackage.METHOD__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLRoutinesPackage.METHOD__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLRoutinesPackage.METHOD__PARAMETERS:
					return ((InternalEList)getParameters()).basicRemove(otherEnd, msgs);
				case SQLRoutinesPackage.METHOD__SOURCE:
					return basicSetSource(null, msgs);
				case SQLRoutinesPackage.METHOD__SCHEMA:
					return basicSetSchema(null, msgs);
				case SQLRoutinesPackage.METHOD__RETURN_TABLE:
					return basicSetReturnTable(null, msgs);
				case SQLRoutinesPackage.METHOD__RETURN_SCALER:
					return basicSetReturnScaler(null, msgs);
				case SQLRoutinesPackage.METHOD__RETURN_CAST:
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
			case SQLRoutinesPackage.METHOD__EANNOTATIONS:
				return getEAnnotations();
			case SQLRoutinesPackage.METHOD__NAME:
				return getName();
			case SQLRoutinesPackage.METHOD__DEPENDENCIES:
				return getDependencies();
			case SQLRoutinesPackage.METHOD__DESCRIPTION:
				return getDescription();
			case SQLRoutinesPackage.METHOD__LABEL:
				return getLabel();
			case SQLRoutinesPackage.METHOD__SPECIFIC_NAME:
				return getSpecificName();
			case SQLRoutinesPackage.METHOD__LANGUAGE:
				return getLanguage();
			case SQLRoutinesPackage.METHOD__PARAMETER_STYLE:
				return getParameterStyle();
			case SQLRoutinesPackage.METHOD__DETERMINISTIC:
				return isDeterministic() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.METHOD__SQL_DATA_ACCESS:
				return getSqlDataAccess();
			case SQLRoutinesPackage.METHOD__CREATION_TS:
				return getCreationTS();
			case SQLRoutinesPackage.METHOD__LAST_ALTERED_TS:
				return getLastAlteredTS();
			case SQLRoutinesPackage.METHOD__AUTHORIZATION_ID:
				return getAuthorizationID();
			case SQLRoutinesPackage.METHOD__SECURITY:
				return getSecurity();
			case SQLRoutinesPackage.METHOD__EXTERNAL_NAME:
				return getExternalName();
			case SQLRoutinesPackage.METHOD__PARAMETERS:
				return getParameters();
			case SQLRoutinesPackage.METHOD__SOURCE:
				return getSource();
			case SQLRoutinesPackage.METHOD__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case SQLRoutinesPackage.METHOD__NULL_CALL:
				return isNullCall() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.METHOD__STATIC:
				return isStatic() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.METHOD__TRANSFORM_GROUP:
				return getTransformGroup();
			case SQLRoutinesPackage.METHOD__TYPE_PRESERVING:
				return isTypePreserving() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.METHOD__MUTATOR:
				return isMutator() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.METHOD__RETURN_TABLE:
				return getReturnTable();
			case SQLRoutinesPackage.METHOD__RETURN_SCALER:
				return getReturnScaler();
			case SQLRoutinesPackage.METHOD__RETURN_CAST:
				return getReturnCast();
			case SQLRoutinesPackage.METHOD__OVERRIDING:
				return isOverriding() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.METHOD__CONSTRUCTOR:
				return isConstructor() ? Boolean.TRUE : Boolean.FALSE;
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
			case SQLRoutinesPackage.METHOD__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.METHOD__NAME:
				setName((String)newValue);
				return;
			case SQLRoutinesPackage.METHOD__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.METHOD__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLRoutinesPackage.METHOD__LABEL:
				setLabel((String)newValue);
				return;
			case SQLRoutinesPackage.METHOD__SPECIFIC_NAME:
				setSpecificName((String)newValue);
				return;
			case SQLRoutinesPackage.METHOD__LANGUAGE:
				setLanguage((String)newValue);
				return;
			case SQLRoutinesPackage.METHOD__PARAMETER_STYLE:
				setParameterStyle((String)newValue);
				return;
			case SQLRoutinesPackage.METHOD__DETERMINISTIC:
				setDeterministic(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.METHOD__SQL_DATA_ACCESS:
				setSqlDataAccess((DataAccess)newValue);
				return;
			case SQLRoutinesPackage.METHOD__CREATION_TS:
				setCreationTS((String)newValue);
				return;
			case SQLRoutinesPackage.METHOD__LAST_ALTERED_TS:
				setLastAlteredTS((String)newValue);
				return;
			case SQLRoutinesPackage.METHOD__AUTHORIZATION_ID:
				setAuthorizationID((String)newValue);
				return;
			case SQLRoutinesPackage.METHOD__SECURITY:
				setSecurity((String)newValue);
				return;
			case SQLRoutinesPackage.METHOD__EXTERNAL_NAME:
				setExternalName((String)newValue);
				return;
			case SQLRoutinesPackage.METHOD__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.METHOD__SOURCE:
				setSource((Source)newValue);
				return;
			case SQLRoutinesPackage.METHOD__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case SQLRoutinesPackage.METHOD__NULL_CALL:
				setNullCall(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.METHOD__STATIC:
				setStatic(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.METHOD__TRANSFORM_GROUP:
				setTransformGroup((String)newValue);
				return;
			case SQLRoutinesPackage.METHOD__TYPE_PRESERVING:
				setTypePreserving(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.METHOD__MUTATOR:
				setMutator(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.METHOD__RETURN_TABLE:
				setReturnTable((RoutineResultTable)newValue);
				return;
			case SQLRoutinesPackage.METHOD__RETURN_SCALER:
				setReturnScaler((Parameter)newValue);
				return;
			case SQLRoutinesPackage.METHOD__RETURN_CAST:
				setReturnCast((Parameter)newValue);
				return;
			case SQLRoutinesPackage.METHOD__OVERRIDING:
				setOverriding(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.METHOD__CONSTRUCTOR:
				setConstructor(((Boolean)newValue).booleanValue());
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
			case SQLRoutinesPackage.METHOD__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLRoutinesPackage.METHOD__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLRoutinesPackage.METHOD__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__SPECIFIC_NAME:
				setSpecificName(SPECIFIC_NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__LANGUAGE:
				setLanguage(LANGUAGE_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__PARAMETER_STYLE:
				setParameterStyle(PARAMETER_STYLE_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__DETERMINISTIC:
				setDeterministic(DETERMINISTIC_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__SQL_DATA_ACCESS:
				setSqlDataAccess(SQL_DATA_ACCESS_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__CREATION_TS:
				setCreationTS(CREATION_TS_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__LAST_ALTERED_TS:
				setLastAlteredTS(LAST_ALTERED_TS_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__AUTHORIZATION_ID:
				setAuthorizationID(AUTHORIZATION_ID_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__SECURITY:
				setSecurity(SECURITY_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__EXTERNAL_NAME:
				setExternalName(EXTERNAL_NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__PARAMETERS:
				getParameters().clear();
				return;
			case SQLRoutinesPackage.METHOD__SOURCE:
				setSource((Source)null);
				return;
			case SQLRoutinesPackage.METHOD__SCHEMA:
				setSchema((Schema)null);
				return;
			case SQLRoutinesPackage.METHOD__NULL_CALL:
				setNullCall(NULL_CALL_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__STATIC:
				setStatic(STATIC_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__TRANSFORM_GROUP:
				setTransformGroup(TRANSFORM_GROUP_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__TYPE_PRESERVING:
				setTypePreserving(TYPE_PRESERVING_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__MUTATOR:
				setMutator(MUTATOR_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__RETURN_TABLE:
				setReturnTable((RoutineResultTable)null);
				return;
			case SQLRoutinesPackage.METHOD__RETURN_SCALER:
				setReturnScaler((Parameter)null);
				return;
			case SQLRoutinesPackage.METHOD__RETURN_CAST:
				setReturnCast((Parameter)null);
				return;
			case SQLRoutinesPackage.METHOD__OVERRIDING:
				setOverriding(OVERRIDING_EDEFAULT);
				return;
			case SQLRoutinesPackage.METHOD__CONSTRUCTOR:
				setConstructor(CONSTRUCTOR_EDEFAULT);
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
			case SQLRoutinesPackage.METHOD__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLRoutinesPackage.METHOD__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLRoutinesPackage.METHOD__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLRoutinesPackage.METHOD__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLRoutinesPackage.METHOD__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLRoutinesPackage.METHOD__SPECIFIC_NAME:
				return SPECIFIC_NAME_EDEFAULT == null ? specificName != null : !SPECIFIC_NAME_EDEFAULT.equals(specificName);
			case SQLRoutinesPackage.METHOD__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
			case SQLRoutinesPackage.METHOD__PARAMETER_STYLE:
				return PARAMETER_STYLE_EDEFAULT == null ? parameterStyle != null : !PARAMETER_STYLE_EDEFAULT.equals(parameterStyle);
			case SQLRoutinesPackage.METHOD__DETERMINISTIC:
				return deterministic != DETERMINISTIC_EDEFAULT;
			case SQLRoutinesPackage.METHOD__SQL_DATA_ACCESS:
				return sqlDataAccess != SQL_DATA_ACCESS_EDEFAULT;
			case SQLRoutinesPackage.METHOD__CREATION_TS:
				return CREATION_TS_EDEFAULT == null ? creationTS != null : !CREATION_TS_EDEFAULT.equals(creationTS);
			case SQLRoutinesPackage.METHOD__LAST_ALTERED_TS:
				return LAST_ALTERED_TS_EDEFAULT == null ? lastAlteredTS != null : !LAST_ALTERED_TS_EDEFAULT.equals(lastAlteredTS);
			case SQLRoutinesPackage.METHOD__AUTHORIZATION_ID:
				return AUTHORIZATION_ID_EDEFAULT == null ? authorizationID != null : !AUTHORIZATION_ID_EDEFAULT.equals(authorizationID);
			case SQLRoutinesPackage.METHOD__SECURITY:
				return SECURITY_EDEFAULT == null ? security != null : !SECURITY_EDEFAULT.equals(security);
			case SQLRoutinesPackage.METHOD__EXTERNAL_NAME:
				return EXTERNAL_NAME_EDEFAULT == null ? externalName != null : !EXTERNAL_NAME_EDEFAULT.equals(externalName);
			case SQLRoutinesPackage.METHOD__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case SQLRoutinesPackage.METHOD__SOURCE:
				return source != null;
			case SQLRoutinesPackage.METHOD__SCHEMA:
				return schema != null;
			case SQLRoutinesPackage.METHOD__NULL_CALL:
				return nullCall != NULL_CALL_EDEFAULT;
			case SQLRoutinesPackage.METHOD__STATIC:
				return static_ != STATIC_EDEFAULT;
			case SQLRoutinesPackage.METHOD__TRANSFORM_GROUP:
				return TRANSFORM_GROUP_EDEFAULT == null ? transformGroup != null : !TRANSFORM_GROUP_EDEFAULT.equals(transformGroup);
			case SQLRoutinesPackage.METHOD__TYPE_PRESERVING:
				return typePreserving != TYPE_PRESERVING_EDEFAULT;
			case SQLRoutinesPackage.METHOD__MUTATOR:
				return mutator != MUTATOR_EDEFAULT;
			case SQLRoutinesPackage.METHOD__RETURN_TABLE:
				return returnTable != null;
			case SQLRoutinesPackage.METHOD__RETURN_SCALER:
				return returnScaler != null;
			case SQLRoutinesPackage.METHOD__RETURN_CAST:
				return returnCast != null;
			case SQLRoutinesPackage.METHOD__OVERRIDING:
				return overriding != OVERRIDING_EDEFAULT;
			case SQLRoutinesPackage.METHOD__CONSTRUCTOR:
				return constructor != CONSTRUCTOR_EDEFAULT;
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
		result.append(" (overriding: "); //$NON-NLS-1$
		result.append(overriding);
		result.append(", constructor: "); //$NON-NLS-1$
		result.append(constructor);
		result.append(')');
		return result.toString();
	}

} //MethodImpl
