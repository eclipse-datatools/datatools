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
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Routine</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl#getSpecificName <em>Specific Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl#getParameterStyle <em>Parameter Style</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl#isDeterministic <em>Deterministic</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl#getSqlDataAccess <em>Sql Data Access</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl#getCreationTS <em>Creation TS</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl#getLastAlteredTS <em>Last Altered TS</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl#getAuthorizationID <em>Authorization ID</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl#getSecurity <em>Security</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl#getExternalName <em>External Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class RoutineImpl extends SQLObjectImpl implements Routine {
	/**
	 * The default value of the '{@link #getSpecificName() <em>Specific Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecificName()
	 * @generated
	 * @ordered
	 */
	protected static final String SPECIFIC_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSpecificName() <em>Specific Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecificName()
	 * @generated
	 * @ordered
	 */
	protected String specificName = SPECIFIC_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected String language = LANGUAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getParameterStyle() <em>Parameter Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterStyle()
	 * @generated
	 * @ordered
	 */
	protected static final String PARAMETER_STYLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParameterStyle() <em>Parameter Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterStyle()
	 * @generated
	 * @ordered
	 */
	protected String parameterStyle = PARAMETER_STYLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isDeterministic() <em>Deterministic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeterministic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DETERMINISTIC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDeterministic() <em>Deterministic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeterministic()
	 * @generated
	 * @ordered
	 */
	protected boolean deterministic = DETERMINISTIC_EDEFAULT;

	/**
	 * The default value of the '{@link #getSqlDataAccess() <em>Sql Data Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSqlDataAccess()
	 * @generated
	 * @ordered
	 */
	protected static final DataAccess SQL_DATA_ACCESS_EDEFAULT = DataAccess.NO_SQL_LITERAL;

	/**
	 * The cached value of the '{@link #getSqlDataAccess() <em>Sql Data Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSqlDataAccess()
	 * @generated
	 * @ordered
	 */
	protected DataAccess sqlDataAccess = SQL_DATA_ACCESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreationTS() <em>Creation TS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationTS()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATION_TS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreationTS() <em>Creation TS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationTS()
	 * @generated
	 * @ordered
	 */
	protected String creationTS = CREATION_TS_EDEFAULT;

	/**
	 * The default value of the '{@link #getLastAlteredTS() <em>Last Altered TS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastAlteredTS()
	 * @generated
	 * @ordered
	 */
	protected static final String LAST_ALTERED_TS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLastAlteredTS() <em>Last Altered TS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastAlteredTS()
	 * @generated
	 * @ordered
	 */
	protected String lastAlteredTS = LAST_ALTERED_TS_EDEFAULT;

	/**
	 * The default value of the '{@link #getAuthorizationID() <em>Authorization ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthorizationID()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHORIZATION_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAuthorizationID() <em>Authorization ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthorizationID()
	 * @generated
	 * @ordered
	 */
	protected String authorizationID = AUTHORIZATION_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getSecurity() <em>Security</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurity()
	 * @generated
	 * @ordered
	 */
	protected static final String SECURITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSecurity() <em>Security</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurity()
	 * @generated
	 * @ordered
	 */
	protected String security = SECURITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getExternalName() <em>External Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalName()
	 * @generated
	 * @ordered
	 */
	protected static final String EXTERNAL_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExternalName() <em>External Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalName()
	 * @generated
	 * @ordered
	 */
	protected String externalName = EXTERNAL_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList parameters;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Source source;

	/**
	 * The cached value of the '{@link #getSchema() <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
	protected Schema schema;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoutineImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLRoutinesPackage.Literals.ROUTINE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSpecificName() {
		return specificName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpecificName(String newSpecificName) {
		String oldSpecificName = specificName;
		specificName = newSpecificName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.ROUTINE__SPECIFIC_NAME, oldSpecificName, specificName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLanguage(String newLanguage) {
		String oldLanguage = language;
		language = newLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.ROUTINE__LANGUAGE, oldLanguage, language));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getParameterStyle() {
		return parameterStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterStyle(String newParameterStyle) {
		String oldParameterStyle = parameterStyle;
		parameterStyle = newParameterStyle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.ROUTINE__PARAMETER_STYLE, oldParameterStyle, parameterStyle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeterministic() {
		return deterministic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeterministic(boolean newDeterministic) {
		boolean oldDeterministic = deterministic;
		deterministic = newDeterministic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.ROUTINE__DETERMINISTIC, oldDeterministic, deterministic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataAccess getSqlDataAccess() {
		return sqlDataAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSqlDataAccess(DataAccess newSqlDataAccess) {
		DataAccess oldSqlDataAccess = sqlDataAccess;
		sqlDataAccess = newSqlDataAccess == null ? SQL_DATA_ACCESS_EDEFAULT : newSqlDataAccess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.ROUTINE__SQL_DATA_ACCESS, oldSqlDataAccess, sqlDataAccess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreationTS() {
		return creationTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreationTS(String newCreationTS) {
		String oldCreationTS = creationTS;
		creationTS = newCreationTS;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.ROUTINE__CREATION_TS, oldCreationTS, creationTS));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLastAlteredTS() {
		return lastAlteredTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastAlteredTS(String newLastAlteredTS) {
		String oldLastAlteredTS = lastAlteredTS;
		lastAlteredTS = newLastAlteredTS;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.ROUTINE__LAST_ALTERED_TS, oldLastAlteredTS, lastAlteredTS));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAuthorizationID() {
		return authorizationID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthorizationID(String newAuthorizationID) {
		String oldAuthorizationID = authorizationID;
		authorizationID = newAuthorizationID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.ROUTINE__AUTHORIZATION_ID, oldAuthorizationID, authorizationID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSecurity() {
		return security;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecurity(String newSecurity) {
		String oldSecurity = security;
		security = newSecurity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.ROUTINE__SECURITY, oldSecurity, security));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExternalName() {
		return externalName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalName(String newExternalName) {
		String oldExternalName = externalName;
		externalName = newExternalName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.ROUTINE__EXTERNAL_NAME, oldExternalName, externalName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentWithInverseEList(Parameter.class, this, SQLRoutinesPackage.ROUTINE__PARAMETERS, SQLRoutinesPackage.PARAMETER__ROUTINE);
		}
		return parameters;
	}

   /**
    * Gets a list of IN and INOUT parameters associated with this routine.
    * <p>
    * @return a list of IN and INOUT parameters associated with this routine.
    */
   public EList getInputParameters() {
      return getParameters(ParameterMode.IN);
   }

   /**
    * Gets a list of OUT and INOUT parameters associated with this routine.
    * <p>
    * @return a list of OUT and INOUT parameters associated with this routine.
    */
   public EList getOutputParameters() {
      return getParameters(ParameterMode.OUT);
   }

   /**
    * Gets a list of IN and INOUT parameters associated with this routine. If
    * the input parameter mode is <code>ParameterMode.IN</code>, then we
    * return parameters that are both IN and INOUT. Similarly, if the input
    * parameter mode is <code>ParameterMode.OUT</code>, then we return
    * parameters that are both OUT and INOUT.
    * <p>
    * @param aMode Either IN or OUT, INOUT is returned for either case.
    * @return A list of parameters according to the specified parameter
    * @see Parameter
    */
   protected EList getParameters(int aMode) {
      Parameter parm;
      EList allList = getParameters();
      EList filteredList = new BasicEList();
      for (int i = 0, parmCnt = allList.size(); i < parmCnt; i++) {
         parm = (Parameter)allList.get(i);
         if ((aMode == ParameterMode.IN) && (parm.getMode().getValue() != ParameterMode.OUT))
            filteredList.add(parm);
         else if ((aMode == ParameterMode.OUT) && (parm.getMode().getValue() != ParameterMode.IN))
            filteredList.add(parm);
      }
      return filteredList;
   }

   
   
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Source getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public NotificationChain basicSetSource(Source newSource, NotificationChain msgs) {
		Source oldSource = source;
		source = newSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.ROUTINE__SOURCE, oldSource, newSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setSource(Source newSource) {
		if (newSource != source) {
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject)source).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLRoutinesPackage.ROUTINE__SOURCE, null, msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLRoutinesPackage.ROUTINE__SOURCE, null, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.ROUTINE__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema getSchema() {
		if (schema != null && schema.eIsProxy()) {
			InternalEObject oldSchema = (InternalEObject)schema;
			schema = (Schema)eResolveProxy(oldSchema);
			if (schema != oldSchema) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLRoutinesPackage.ROUTINE__SCHEMA, oldSchema, schema));
			}
		}
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema basicGetSchema() {
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSchema(Schema newSchema, NotificationChain msgs) {
		Schema oldSchema = schema;
		schema = newSchema;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.ROUTINE__SCHEMA, oldSchema, newSchema);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchema(Schema newSchema) {
		if (newSchema != schema) {
			NotificationChain msgs = null;
			if (schema != null)
				msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__ROUTINES, Schema.class, msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, SQLSchemaPackage.SCHEMA__ROUTINES, Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.ROUTINE__SCHEMA, newSchema, newSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLRoutinesPackage.ROUTINE__PARAMETERS:
				return ((InternalEList)getParameters()).basicAdd(otherEnd, msgs);
			case SQLRoutinesPackage.ROUTINE__SCHEMA:
				if (schema != null)
					msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__ROUTINES, Schema.class, msgs);
				return basicSetSchema((Schema)otherEnd, msgs);
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
			case SQLRoutinesPackage.ROUTINE__PARAMETERS:
				return ((InternalEList)getParameters()).basicRemove(otherEnd, msgs);
			case SQLRoutinesPackage.ROUTINE__SOURCE:
				return basicSetSource(null, msgs);
			case SQLRoutinesPackage.ROUTINE__SCHEMA:
				return basicSetSchema(null, msgs);
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
			case SQLRoutinesPackage.ROUTINE__SPECIFIC_NAME:
				return getSpecificName();
			case SQLRoutinesPackage.ROUTINE__LANGUAGE:
				return getLanguage();
			case SQLRoutinesPackage.ROUTINE__PARAMETER_STYLE:
				return getParameterStyle();
			case SQLRoutinesPackage.ROUTINE__DETERMINISTIC:
				return isDeterministic() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.ROUTINE__SQL_DATA_ACCESS:
				return getSqlDataAccess();
			case SQLRoutinesPackage.ROUTINE__CREATION_TS:
				return getCreationTS();
			case SQLRoutinesPackage.ROUTINE__LAST_ALTERED_TS:
				return getLastAlteredTS();
			case SQLRoutinesPackage.ROUTINE__AUTHORIZATION_ID:
				return getAuthorizationID();
			case SQLRoutinesPackage.ROUTINE__SECURITY:
				return getSecurity();
			case SQLRoutinesPackage.ROUTINE__EXTERNAL_NAME:
				return getExternalName();
			case SQLRoutinesPackage.ROUTINE__PARAMETERS:
				return getParameters();
			case SQLRoutinesPackage.ROUTINE__SOURCE:
				return getSource();
			case SQLRoutinesPackage.ROUTINE__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
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
			case SQLRoutinesPackage.ROUTINE__SPECIFIC_NAME:
				setSpecificName((String)newValue);
				return;
			case SQLRoutinesPackage.ROUTINE__LANGUAGE:
				setLanguage((String)newValue);
				return;
			case SQLRoutinesPackage.ROUTINE__PARAMETER_STYLE:
				setParameterStyle((String)newValue);
				return;
			case SQLRoutinesPackage.ROUTINE__DETERMINISTIC:
				setDeterministic(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.ROUTINE__SQL_DATA_ACCESS:
				setSqlDataAccess((DataAccess)newValue);
				return;
			case SQLRoutinesPackage.ROUTINE__CREATION_TS:
				setCreationTS((String)newValue);
				return;
			case SQLRoutinesPackage.ROUTINE__LAST_ALTERED_TS:
				setLastAlteredTS((String)newValue);
				return;
			case SQLRoutinesPackage.ROUTINE__AUTHORIZATION_ID:
				setAuthorizationID((String)newValue);
				return;
			case SQLRoutinesPackage.ROUTINE__SECURITY:
				setSecurity((String)newValue);
				return;
			case SQLRoutinesPackage.ROUTINE__EXTERNAL_NAME:
				setExternalName((String)newValue);
				return;
			case SQLRoutinesPackage.ROUTINE__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.ROUTINE__SOURCE:
				setSource((Source)newValue);
				return;
			case SQLRoutinesPackage.ROUTINE__SCHEMA:
				setSchema((Schema)newValue);
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
			case SQLRoutinesPackage.ROUTINE__SPECIFIC_NAME:
				setSpecificName(SPECIFIC_NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.ROUTINE__LANGUAGE:
				setLanguage(LANGUAGE_EDEFAULT);
				return;
			case SQLRoutinesPackage.ROUTINE__PARAMETER_STYLE:
				setParameterStyle(PARAMETER_STYLE_EDEFAULT);
				return;
			case SQLRoutinesPackage.ROUTINE__DETERMINISTIC:
				setDeterministic(DETERMINISTIC_EDEFAULT);
				return;
			case SQLRoutinesPackage.ROUTINE__SQL_DATA_ACCESS:
				setSqlDataAccess(SQL_DATA_ACCESS_EDEFAULT);
				return;
			case SQLRoutinesPackage.ROUTINE__CREATION_TS:
				setCreationTS(CREATION_TS_EDEFAULT);
				return;
			case SQLRoutinesPackage.ROUTINE__LAST_ALTERED_TS:
				setLastAlteredTS(LAST_ALTERED_TS_EDEFAULT);
				return;
			case SQLRoutinesPackage.ROUTINE__AUTHORIZATION_ID:
				setAuthorizationID(AUTHORIZATION_ID_EDEFAULT);
				return;
			case SQLRoutinesPackage.ROUTINE__SECURITY:
				setSecurity(SECURITY_EDEFAULT);
				return;
			case SQLRoutinesPackage.ROUTINE__EXTERNAL_NAME:
				setExternalName(EXTERNAL_NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.ROUTINE__PARAMETERS:
				getParameters().clear();
				return;
			case SQLRoutinesPackage.ROUTINE__SOURCE:
				setSource((Source)null);
				return;
			case SQLRoutinesPackage.ROUTINE__SCHEMA:
				setSchema((Schema)null);
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
			case SQLRoutinesPackage.ROUTINE__SPECIFIC_NAME:
				return SPECIFIC_NAME_EDEFAULT == null ? specificName != null : !SPECIFIC_NAME_EDEFAULT.equals(specificName);
			case SQLRoutinesPackage.ROUTINE__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
			case SQLRoutinesPackage.ROUTINE__PARAMETER_STYLE:
				return PARAMETER_STYLE_EDEFAULT == null ? parameterStyle != null : !PARAMETER_STYLE_EDEFAULT.equals(parameterStyle);
			case SQLRoutinesPackage.ROUTINE__DETERMINISTIC:
				return deterministic != DETERMINISTIC_EDEFAULT;
			case SQLRoutinesPackage.ROUTINE__SQL_DATA_ACCESS:
				return sqlDataAccess != SQL_DATA_ACCESS_EDEFAULT;
			case SQLRoutinesPackage.ROUTINE__CREATION_TS:
				return CREATION_TS_EDEFAULT == null ? creationTS != null : !CREATION_TS_EDEFAULT.equals(creationTS);
			case SQLRoutinesPackage.ROUTINE__LAST_ALTERED_TS:
				return LAST_ALTERED_TS_EDEFAULT == null ? lastAlteredTS != null : !LAST_ALTERED_TS_EDEFAULT.equals(lastAlteredTS);
			case SQLRoutinesPackage.ROUTINE__AUTHORIZATION_ID:
				return AUTHORIZATION_ID_EDEFAULT == null ? authorizationID != null : !AUTHORIZATION_ID_EDEFAULT.equals(authorizationID);
			case SQLRoutinesPackage.ROUTINE__SECURITY:
				return SECURITY_EDEFAULT == null ? security != null : !SECURITY_EDEFAULT.equals(security);
			case SQLRoutinesPackage.ROUTINE__EXTERNAL_NAME:
				return EXTERNAL_NAME_EDEFAULT == null ? externalName != null : !EXTERNAL_NAME_EDEFAULT.equals(externalName);
			case SQLRoutinesPackage.ROUTINE__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case SQLRoutinesPackage.ROUTINE__SOURCE:
				return source != null;
			case SQLRoutinesPackage.ROUTINE__SCHEMA:
				return schema != null;
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
		result.append(" (specificName: "); //$NON-NLS-1$
		result.append(specificName);
		result.append(", language: "); //$NON-NLS-1$
		result.append(language);
		result.append(", parameterStyle: "); //$NON-NLS-1$
		result.append(parameterStyle);
		result.append(", deterministic: "); //$NON-NLS-1$
		result.append(deterministic);
		result.append(", sqlDataAccess: "); //$NON-NLS-1$
		result.append(sqlDataAccess);
		result.append(", creationTS: "); //$NON-NLS-1$
		result.append(creationTS);
		result.append(", lastAlteredTS: "); //$NON-NLS-1$
		result.append(lastAlteredTS);
		result.append(", authorizationID: "); //$NON-NLS-1$
		result.append(authorizationID);
		result.append(", security: "); //$NON-NLS-1$
		result.append(security);
		result.append(", externalName: "); //$NON-NLS-1$
		result.append(externalName);
		result.append(')');
		return result.toString();
	}

} //RoutineImpl
