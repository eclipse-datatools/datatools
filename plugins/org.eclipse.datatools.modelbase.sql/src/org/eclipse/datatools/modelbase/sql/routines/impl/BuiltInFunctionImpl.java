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

import org.eclipse.datatools.modelbase.sql.routines.BuiltInFunction;
import org.eclipse.datatools.modelbase.sql.routines.DataAccess;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Built In Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class BuiltInFunctionImpl extends FunctionImpl implements BuiltInFunction {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BuiltInFunctionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLRoutinesPackage.eINSTANCE.getBuiltInFunction();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLRoutinesPackage.BUILT_IN_FUNCTION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLRoutinesPackage.BUILT_IN_FUNCTION__PARAMETERS:
					return ((InternalEList)getParameters()).basicAdd(otherEnd, msgs);
				case SQLRoutinesPackage.BUILT_IN_FUNCTION__SCHEMA:
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
				case SQLRoutinesPackage.BUILT_IN_FUNCTION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLRoutinesPackage.BUILT_IN_FUNCTION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLRoutinesPackage.BUILT_IN_FUNCTION__PARAMETERS:
					return ((InternalEList)getParameters()).basicRemove(otherEnd, msgs);
				case SQLRoutinesPackage.BUILT_IN_FUNCTION__SOURCE:
					return basicSetSource(null, msgs);
				case SQLRoutinesPackage.BUILT_IN_FUNCTION__SCHEMA:
					return basicSetSchema(null, msgs);
				case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_TABLE:
					return basicSetReturnTable(null, msgs);
				case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_SCALER:
					return basicSetReturnScaler(null, msgs);
				case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_CAST:
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
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__EANNOTATIONS:
				return getEAnnotations();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__NAME:
				return getName();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__DEPENDENCIES:
				return getDependencies();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__DESCRIPTION:
				return getDescription();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__LABEL:
				return getLabel();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SPECIFIC_NAME:
				return getSpecificName();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__LANGUAGE:
				return getLanguage();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__PARAMETER_STYLE:
				return getParameterStyle();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__DETERMINISTIC:
				return isDeterministic() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SQL_DATA_ACCESS:
				return getSqlDataAccess();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__CREATION_TS:
				return getCreationTS();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__LAST_ALTERED_TS:
				return getLastAlteredTS();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__AUTHORIZATION_ID:
				return getAuthorizationID();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SECURITY:
				return getSecurity();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__EXTERNAL_NAME:
				return getExternalName();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__PARAMETERS:
				return getParameters();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SOURCE:
				return getSource();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__NULL_CALL:
				return isNullCall() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__STATIC:
				return isStatic() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__TRANSFORM_GROUP:
				return getTransformGroup();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__TYPE_PRESERVING:
				return isTypePreserving() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__MUTATOR:
				return isMutator() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_TABLE:
				return getReturnTable();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_SCALER:
				return getReturnScaler();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_CAST:
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
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__NAME:
				setName((String)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SPECIFIC_NAME:
				setSpecificName((String)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__LANGUAGE:
				setLanguage((String)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__PARAMETER_STYLE:
				setParameterStyle((String)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__DETERMINISTIC:
				setDeterministic(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SQL_DATA_ACCESS:
				setSqlDataAccess((DataAccess)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__CREATION_TS:
				setCreationTS((String)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__LAST_ALTERED_TS:
				setLastAlteredTS((String)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__AUTHORIZATION_ID:
				setAuthorizationID((String)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SECURITY:
				setSecurity((String)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__EXTERNAL_NAME:
				setExternalName((String)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SOURCE:
				setSource((Source)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__NULL_CALL:
				setNullCall(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__STATIC:
				setStatic(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__TRANSFORM_GROUP:
				setTransformGroup((String)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__TYPE_PRESERVING:
				setTypePreserving(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__MUTATOR:
				setMutator(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_TABLE:
				setReturnTable((RoutineResultTable)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_SCALER:
				setReturnScaler((Parameter)newValue);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_CAST:
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
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SPECIFIC_NAME:
				setSpecificName(SPECIFIC_NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__LANGUAGE:
				setLanguage(LANGUAGE_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__PARAMETER_STYLE:
				setParameterStyle(PARAMETER_STYLE_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__DETERMINISTIC:
				setDeterministic(DETERMINISTIC_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SQL_DATA_ACCESS:
				setSqlDataAccess(SQL_DATA_ACCESS_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__CREATION_TS:
				setCreationTS(CREATION_TS_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__LAST_ALTERED_TS:
				setLastAlteredTS(LAST_ALTERED_TS_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__AUTHORIZATION_ID:
				setAuthorizationID(AUTHORIZATION_ID_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SECURITY:
				setSecurity(SECURITY_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__EXTERNAL_NAME:
				setExternalName(EXTERNAL_NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__PARAMETERS:
				getParameters().clear();
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SOURCE:
				setSource((Source)null);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SCHEMA:
				setSchema((Schema)null);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__NULL_CALL:
				setNullCall(NULL_CALL_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__STATIC:
				setStatic(STATIC_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__TRANSFORM_GROUP:
				setTransformGroup(TRANSFORM_GROUP_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__TYPE_PRESERVING:
				setTypePreserving(TYPE_PRESERVING_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__MUTATOR:
				setMutator(MUTATOR_EDEFAULT);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_TABLE:
				setReturnTable((RoutineResultTable)null);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_SCALER:
				setReturnScaler((Parameter)null);
				return;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_CAST:
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
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SPECIFIC_NAME:
				return SPECIFIC_NAME_EDEFAULT == null ? specificName != null : !SPECIFIC_NAME_EDEFAULT.equals(specificName);
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__PARAMETER_STYLE:
				return PARAMETER_STYLE_EDEFAULT == null ? parameterStyle != null : !PARAMETER_STYLE_EDEFAULT.equals(parameterStyle);
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__DETERMINISTIC:
				return deterministic != DETERMINISTIC_EDEFAULT;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SQL_DATA_ACCESS:
				return sqlDataAccess != SQL_DATA_ACCESS_EDEFAULT;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__CREATION_TS:
				return CREATION_TS_EDEFAULT == null ? creationTS != null : !CREATION_TS_EDEFAULT.equals(creationTS);
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__LAST_ALTERED_TS:
				return LAST_ALTERED_TS_EDEFAULT == null ? lastAlteredTS != null : !LAST_ALTERED_TS_EDEFAULT.equals(lastAlteredTS);
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__AUTHORIZATION_ID:
				return AUTHORIZATION_ID_EDEFAULT == null ? authorizationID != null : !AUTHORIZATION_ID_EDEFAULT.equals(authorizationID);
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SECURITY:
				return SECURITY_EDEFAULT == null ? security != null : !SECURITY_EDEFAULT.equals(security);
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__EXTERNAL_NAME:
				return EXTERNAL_NAME_EDEFAULT == null ? externalName != null : !EXTERNAL_NAME_EDEFAULT.equals(externalName);
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SOURCE:
				return source != null;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__SCHEMA:
				return schema != null;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__NULL_CALL:
				return nullCall != NULL_CALL_EDEFAULT;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__STATIC:
				return static_ != STATIC_EDEFAULT;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__TRANSFORM_GROUP:
				return TRANSFORM_GROUP_EDEFAULT == null ? transformGroup != null : !TRANSFORM_GROUP_EDEFAULT.equals(transformGroup);
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__TYPE_PRESERVING:
				return typePreserving != TYPE_PRESERVING_EDEFAULT;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__MUTATOR:
				return mutator != MUTATOR_EDEFAULT;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_TABLE:
				return returnTable != null;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_SCALER:
				return returnScaler != null;
			case SQLRoutinesPackage.BUILT_IN_FUNCTION__RETURN_CAST:
				return returnCast != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //BuiltInFunctionImpl
