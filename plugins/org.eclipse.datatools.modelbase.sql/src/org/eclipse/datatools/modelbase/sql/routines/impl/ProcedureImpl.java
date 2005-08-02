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
	protected EList resultSet = null;

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
		return SQLRoutinesPackage.eINSTANCE.getProcedure();
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLRoutinesPackage.PROCEDURE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLRoutinesPackage.PROCEDURE__PARAMETERS:
					return ((InternalEList)getParameters()).basicAdd(otherEnd, msgs);
				case SQLRoutinesPackage.PROCEDURE__SCHEMA:
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
				case SQLRoutinesPackage.PROCEDURE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLRoutinesPackage.PROCEDURE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLRoutinesPackage.PROCEDURE__PARAMETERS:
					return ((InternalEList)getParameters()).basicRemove(otherEnd, msgs);
				case SQLRoutinesPackage.PROCEDURE__SOURCE:
					return basicSetSource(null, msgs);
				case SQLRoutinesPackage.PROCEDURE__SCHEMA:
					return basicSetSchema(null, msgs);
				case SQLRoutinesPackage.PROCEDURE__RESULT_SET:
					return ((InternalEList)getResultSet()).basicRemove(otherEnd, msgs);
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
			case SQLRoutinesPackage.PROCEDURE__EANNOTATIONS:
				return getEAnnotations();
			case SQLRoutinesPackage.PROCEDURE__NAME:
				return getName();
			case SQLRoutinesPackage.PROCEDURE__DEPENDENCIES:
				return getDependencies();
			case SQLRoutinesPackage.PROCEDURE__DESCRIPTION:
				return getDescription();
			case SQLRoutinesPackage.PROCEDURE__LABEL:
				return getLabel();
			case SQLRoutinesPackage.PROCEDURE__SPECIFIC_NAME:
				return getSpecificName();
			case SQLRoutinesPackage.PROCEDURE__LANGUAGE:
				return getLanguage();
			case SQLRoutinesPackage.PROCEDURE__PARAMETER_STYLE:
				return getParameterStyle();
			case SQLRoutinesPackage.PROCEDURE__DETERMINISTIC:
				return isDeterministic() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.PROCEDURE__SQL_DATA_ACCESS:
				return getSqlDataAccess();
			case SQLRoutinesPackage.PROCEDURE__CREATION_TS:
				return getCreationTS();
			case SQLRoutinesPackage.PROCEDURE__LAST_ALTERED_TS:
				return getLastAlteredTS();
			case SQLRoutinesPackage.PROCEDURE__AUTHORIZATION_ID:
				return getAuthorizationID();
			case SQLRoutinesPackage.PROCEDURE__SECURITY:
				return getSecurity();
			case SQLRoutinesPackage.PROCEDURE__EXTERNAL_NAME:
				return getExternalName();
			case SQLRoutinesPackage.PROCEDURE__PARAMETERS:
				return getParameters();
			case SQLRoutinesPackage.PROCEDURE__SOURCE:
				return getSource();
			case SQLRoutinesPackage.PROCEDURE__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case SQLRoutinesPackage.PROCEDURE__MAX_RESULT_SETS:
				return new Integer(getMaxResultSets());
			case SQLRoutinesPackage.PROCEDURE__OLD_SAVE_POINT:
				return isOldSavePoint() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.PROCEDURE__RESULT_SET:
				return getResultSet();
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
			case SQLRoutinesPackage.PROCEDURE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__NAME:
				setName((String)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__SPECIFIC_NAME:
				setSpecificName((String)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__LANGUAGE:
				setLanguage((String)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__PARAMETER_STYLE:
				setParameterStyle((String)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__DETERMINISTIC:
				setDeterministic(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.PROCEDURE__SQL_DATA_ACCESS:
				setSqlDataAccess((DataAccess)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__CREATION_TS:
				setCreationTS((String)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__LAST_ALTERED_TS:
				setLastAlteredTS((String)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__AUTHORIZATION_ID:
				setAuthorizationID((String)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__SECURITY:
				setSecurity((String)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__EXTERNAL_NAME:
				setExternalName((String)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__SOURCE:
				setSource((Source)newValue);
				return;
			case SQLRoutinesPackage.PROCEDURE__SCHEMA:
				setSchema((Schema)newValue);
				return;
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
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLRoutinesPackage.PROCEDURE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLRoutinesPackage.PROCEDURE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLRoutinesPackage.PROCEDURE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__SPECIFIC_NAME:
				setSpecificName(SPECIFIC_NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__LANGUAGE:
				setLanguage(LANGUAGE_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__PARAMETER_STYLE:
				setParameterStyle(PARAMETER_STYLE_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__DETERMINISTIC:
				setDeterministic(DETERMINISTIC_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__SQL_DATA_ACCESS:
				setSqlDataAccess(SQL_DATA_ACCESS_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__CREATION_TS:
				setCreationTS(CREATION_TS_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__LAST_ALTERED_TS:
				setLastAlteredTS(LAST_ALTERED_TS_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__AUTHORIZATION_ID:
				setAuthorizationID(AUTHORIZATION_ID_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__SECURITY:
				setSecurity(SECURITY_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__EXTERNAL_NAME:
				setExternalName(EXTERNAL_NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.PROCEDURE__PARAMETERS:
				getParameters().clear();
				return;
			case SQLRoutinesPackage.PROCEDURE__SOURCE:
				setSource((Source)null);
				return;
			case SQLRoutinesPackage.PROCEDURE__SCHEMA:
				setSchema((Schema)null);
				return;
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
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLRoutinesPackage.PROCEDURE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLRoutinesPackage.PROCEDURE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLRoutinesPackage.PROCEDURE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLRoutinesPackage.PROCEDURE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLRoutinesPackage.PROCEDURE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLRoutinesPackage.PROCEDURE__SPECIFIC_NAME:
				return SPECIFIC_NAME_EDEFAULT == null ? specificName != null : !SPECIFIC_NAME_EDEFAULT.equals(specificName);
			case SQLRoutinesPackage.PROCEDURE__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
			case SQLRoutinesPackage.PROCEDURE__PARAMETER_STYLE:
				return PARAMETER_STYLE_EDEFAULT == null ? parameterStyle != null : !PARAMETER_STYLE_EDEFAULT.equals(parameterStyle);
			case SQLRoutinesPackage.PROCEDURE__DETERMINISTIC:
				return deterministic != DETERMINISTIC_EDEFAULT;
			case SQLRoutinesPackage.PROCEDURE__SQL_DATA_ACCESS:
				return sqlDataAccess != SQL_DATA_ACCESS_EDEFAULT;
			case SQLRoutinesPackage.PROCEDURE__CREATION_TS:
				return CREATION_TS_EDEFAULT == null ? creationTS != null : !CREATION_TS_EDEFAULT.equals(creationTS);
			case SQLRoutinesPackage.PROCEDURE__LAST_ALTERED_TS:
				return LAST_ALTERED_TS_EDEFAULT == null ? lastAlteredTS != null : !LAST_ALTERED_TS_EDEFAULT.equals(lastAlteredTS);
			case SQLRoutinesPackage.PROCEDURE__AUTHORIZATION_ID:
				return AUTHORIZATION_ID_EDEFAULT == null ? authorizationID != null : !AUTHORIZATION_ID_EDEFAULT.equals(authorizationID);
			case SQLRoutinesPackage.PROCEDURE__SECURITY:
				return SECURITY_EDEFAULT == null ? security != null : !SECURITY_EDEFAULT.equals(security);
			case SQLRoutinesPackage.PROCEDURE__EXTERNAL_NAME:
				return EXTERNAL_NAME_EDEFAULT == null ? externalName != null : !EXTERNAL_NAME_EDEFAULT.equals(externalName);
			case SQLRoutinesPackage.PROCEDURE__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case SQLRoutinesPackage.PROCEDURE__SOURCE:
				return source != null;
			case SQLRoutinesPackage.PROCEDURE__SCHEMA:
				return schema != null;
			case SQLRoutinesPackage.PROCEDURE__MAX_RESULT_SETS:
				return maxResultSets != MAX_RESULT_SETS_EDEFAULT;
			case SQLRoutinesPackage.PROCEDURE__OLD_SAVE_POINT:
				return oldSavePoint != OLD_SAVE_POINT_EDEFAULT;
			case SQLRoutinesPackage.PROCEDURE__RESULT_SET:
				return resultSet != null && !resultSet.isEmpty();
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
		result.append(" (maxResultSets: "); //$NON-NLS-1$
		result.append(maxResultSets);
		result.append(", oldSavePoint: "); //$NON-NLS-1$
		result.append(oldSavePoint);
		result.append(')');
		return result.toString();
	}

} //ProcedureImpl
