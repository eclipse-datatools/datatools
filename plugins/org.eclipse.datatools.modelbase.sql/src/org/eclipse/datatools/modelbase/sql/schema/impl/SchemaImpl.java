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
package org.eclipse.datatools.modelbase.sql.schema.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.constraints.Assertion;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.BuiltInFunction;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Schema</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl#getIndices <em>Indices</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl#getTables <em>Tables</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl#getSequences <em>Sequences</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl#getDatabase <em>Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl#getAssertions <em>Assertions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl#getUserDefinedTypes <em>User Defined Types</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl#getCharSets <em>Char Sets</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl#getRoutines <em>Routines</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl#getOwner <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SchemaImpl extends SQLObjectImpl implements Schema {
	/**
	 * The cached value of the '{@link #getTriggers() <em>Triggers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTriggers()
	 * @generated
	 * @ordered
	 */
	protected EList triggers = null;

	/**
	 * The cached value of the '{@link #getIndices() <em>Indices</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndices()
	 * @generated
	 * @ordered
	 */
	protected EList indices = null;

	/**
	 * The cached value of the '{@link #getTables() <em>Tables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTables()
	 * @generated
	 * @ordered
	 */
	protected EList tables = null;

	/**
	 * The cached value of the '{@link #getSequences() <em>Sequences</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequences()
	 * @generated
	 * @ordered
	 */
	protected EList sequences = null;

	/**
	 * The cached value of the '{@link #getDatabase() <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabase()
	 * @generated
	 * @ordered
	 */
	protected Database database = null;

	/**
	 * The cached value of the '{@link #getAssertions() <em>Assertions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssertions()
	 * @generated
	 * @ordered
	 */
	protected EList assertions = null;

	/**
	 * The cached value of the '{@link #getUserDefinedTypes() <em>User Defined Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserDefinedTypes()
	 * @generated
	 * @ordered
	 */
	protected EList userDefinedTypes = null;

	/**
	 * The cached value of the '{@link #getCharSets() <em>Char Sets</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharSets()
	 * @generated
	 * @ordered
	 */
	protected EList charSets = null;

	/**
	 * The cached value of the '{@link #getRoutines() <em>Routines</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoutines()
	 * @generated
	 * @ordered
	 */
	protected EList routines = null;

	/**
	 * The cached value of the '{@link #getOwner() <em>Owner</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwner()
	 * @generated
	 * @ordered
	 */
	protected AuthorizationIdentifier owner = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SchemaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLSchemaPackage.eINSTANCE.getSchema();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTriggers() {
		if (triggers == null) {
			triggers = new EObjectWithInverseResolvingEList(Trigger.class, this, SQLSchemaPackage.SCHEMA__TRIGGERS, SQLTablesPackage.TRIGGER__SCHEMA);
		}
		return triggers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIndices() {
		if (indices == null) {
			indices = new EObjectWithInverseResolvingEList(Index.class, this, SQLSchemaPackage.SCHEMA__INDICES, SQLConstraintsPackage.INDEX__SCHEMA);
		}
		return indices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTables() {
		if (tables == null) {
			tables = new EObjectWithInverseResolvingEList(Table.class, this, SQLSchemaPackage.SCHEMA__TABLES, SQLTablesPackage.TABLE__SCHEMA);
		}
		return tables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSequences() {
		if (sequences == null) {
			sequences = new EObjectWithInverseResolvingEList(Sequence.class, this, SQLSchemaPackage.SCHEMA__SEQUENCES, SQLSchemaPackage.SEQUENCE__SCHEMA);
		}
		return sequences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Database getDatabase() {
		if (database != null && database.eIsProxy()) {
			Database oldDatabase = database;
			database = (Database)eResolveProxy((InternalEObject)database);
			if (database != oldDatabase) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLSchemaPackage.SCHEMA__DATABASE, oldDatabase, database));
			}
		}
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Database basicGetDatabase() {
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDatabase(Database newDatabase, NotificationChain msgs) {
		Database oldDatabase = database;
		database = newDatabase;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.SCHEMA__DATABASE, oldDatabase, newDatabase);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatabase(Database newDatabase) {
		if (newDatabase != database) {
			NotificationChain msgs = null;
			if (database != null)
				msgs = ((InternalEObject)database).eInverseRemove(this, SQLSchemaPackage.DATABASE__SCHEMAS, Database.class, msgs);
			if (newDatabase != null)
				msgs = ((InternalEObject)newDatabase).eInverseAdd(this, SQLSchemaPackage.DATABASE__SCHEMAS, Database.class, msgs);
			msgs = basicSetDatabase(newDatabase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.SCHEMA__DATABASE, newDatabase, newDatabase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAssertions() {
		if (assertions == null) {
			assertions = new EObjectWithInverseResolvingEList(Assertion.class, this, SQLSchemaPackage.SCHEMA__ASSERTIONS, SQLConstraintsPackage.ASSERTION__SCHEMA);
		}
		return assertions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getUserDefinedTypes() {
		if (userDefinedTypes == null) {
			userDefinedTypes = new EObjectWithInverseResolvingEList(UserDefinedType.class, this, SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES, SQLDataTypesPackage.USER_DEFINED_TYPE__SCHEMA);
		}
		return userDefinedTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCharSets() {
		if (charSets == null) {
			charSets = new EObjectWithInverseResolvingEList(CharacterSet.class, this, SQLSchemaPackage.SCHEMA__CHAR_SETS, SQLDataTypesPackage.CHARACTER_SET__SCHEMA);
		}
		return charSets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRoutines() {
		if (routines == null) {
			routines = new EObjectWithInverseResolvingEList(Routine.class, this, SQLSchemaPackage.SCHEMA__ROUTINES, SQLRoutinesPackage.ROUTINE__SCHEMA);
		}
		return routines;
	}

   /**
    * Get a list of procedures belonging to this schema.
    */
   public EList getProcedures() {
      return getSpecializedRoutines(Procedure.class);
   }

   /**
    * Get a list of user-defined functions belonging to this schema.
    */
   public EList getUDFs() {
      return getSpecializedRoutines(UserDefinedFunction.class);
   }

   /**
    * Get a list of built-in (aka system) functions belonging to this schema.
    */
   public EList getBuiltInFunctions() {
      return getSpecializedRoutines(BuiltInFunction.class);
   }

   /**
    * Get a list of Routine subclasses that match the specified argument.
    * @param aDesiredClass any subclass of Routine
    * @return a list of Routine subclasses that match the specified argument.
    */
   protected EList getSpecializedRoutines(Class aDesiredClass) {
      EList specializedRoutines = new BasicEList();
      EList routines = getRoutines();
      for (int i = 0, routineCnt = routines.size(); i < routineCnt; i++) {
         Object obj = routines.get(i);
         if (aDesiredClass.isAssignableFrom(obj.getClass())) {
            specializedRoutines.add(obj);
         }
      }
      return specializedRoutines;
   }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AuthorizationIdentifier getOwner() {
		if (owner != null && owner.eIsProxy()) {
			AuthorizationIdentifier oldOwner = owner;
			owner = (AuthorizationIdentifier)eResolveProxy((InternalEObject)owner);
			if (owner != oldOwner) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLSchemaPackage.SCHEMA__OWNER, oldOwner, owner));
			}
		}
		return owner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AuthorizationIdentifier basicGetOwner() {
		return owner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwner(AuthorizationIdentifier newOwner, NotificationChain msgs) {
		AuthorizationIdentifier oldOwner = owner;
		owner = newOwner;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.SCHEMA__OWNER, oldOwner, newOwner);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(AuthorizationIdentifier newOwner) {
		if (newOwner != owner) {
			NotificationChain msgs = null;
			if (owner != null)
				msgs = ((InternalEObject)owner).eInverseRemove(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA, AuthorizationIdentifier.class, msgs);
			if (newOwner != null)
				msgs = ((InternalEObject)newOwner).eInverseAdd(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA, AuthorizationIdentifier.class, msgs);
			msgs = basicSetOwner(newOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.SCHEMA__OWNER, newOwner, newOwner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLSchemaPackage.SCHEMA__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__TRIGGERS:
					return ((InternalEList)getTriggers()).basicAdd(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__INDICES:
					return ((InternalEList)getIndices()).basicAdd(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__TABLES:
					return ((InternalEList)getTables()).basicAdd(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__SEQUENCES:
					return ((InternalEList)getSequences()).basicAdd(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__DATABASE:
					if (database != null)
						msgs = ((InternalEObject)database).eInverseRemove(this, SQLSchemaPackage.DATABASE__SCHEMAS, Database.class, msgs);
					return basicSetDatabase((Database)otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__ASSERTIONS:
					return ((InternalEList)getAssertions()).basicAdd(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES:
					return ((InternalEList)getUserDefinedTypes()).basicAdd(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__CHAR_SETS:
					return ((InternalEList)getCharSets()).basicAdd(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__ROUTINES:
					return ((InternalEList)getRoutines()).basicAdd(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__OWNER:
					if (owner != null)
						msgs = ((InternalEObject)owner).eInverseRemove(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA, AuthorizationIdentifier.class, msgs);
					return basicSetOwner((AuthorizationIdentifier)otherEnd, msgs);
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
				case SQLSchemaPackage.SCHEMA__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__TRIGGERS:
					return ((InternalEList)getTriggers()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__INDICES:
					return ((InternalEList)getIndices()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__TABLES:
					return ((InternalEList)getTables()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__SEQUENCES:
					return ((InternalEList)getSequences()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__DATABASE:
					return basicSetDatabase(null, msgs);
				case SQLSchemaPackage.SCHEMA__ASSERTIONS:
					return ((InternalEList)getAssertions()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES:
					return ((InternalEList)getUserDefinedTypes()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__CHAR_SETS:
					return ((InternalEList)getCharSets()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__ROUTINES:
					return ((InternalEList)getRoutines()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.SCHEMA__OWNER:
					return basicSetOwner(null, msgs);
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
			case SQLSchemaPackage.SCHEMA__EANNOTATIONS:
				return getEAnnotations();
			case SQLSchemaPackage.SCHEMA__NAME:
				return getName();
			case SQLSchemaPackage.SCHEMA__DEPENDENCIES:
				return getDependencies();
			case SQLSchemaPackage.SCHEMA__DESCRIPTION:
				return getDescription();
			case SQLSchemaPackage.SCHEMA__LABEL:
				return getLabel();
			case SQLSchemaPackage.SCHEMA__TRIGGERS:
				return getTriggers();
			case SQLSchemaPackage.SCHEMA__INDICES:
				return getIndices();
			case SQLSchemaPackage.SCHEMA__TABLES:
				return getTables();
			case SQLSchemaPackage.SCHEMA__SEQUENCES:
				return getSequences();
			case SQLSchemaPackage.SCHEMA__DATABASE:
				if (resolve) return getDatabase();
				return basicGetDatabase();
			case SQLSchemaPackage.SCHEMA__ASSERTIONS:
				return getAssertions();
			case SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES:
				return getUserDefinedTypes();
			case SQLSchemaPackage.SCHEMA__CHAR_SETS:
				return getCharSets();
			case SQLSchemaPackage.SCHEMA__ROUTINES:
				return getRoutines();
			case SQLSchemaPackage.SCHEMA__OWNER:
				if (resolve) return getOwner();
				return basicGetOwner();
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
			case SQLSchemaPackage.SCHEMA__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SCHEMA__NAME:
				setName((String)newValue);
				return;
			case SQLSchemaPackage.SCHEMA__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SCHEMA__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLSchemaPackage.SCHEMA__LABEL:
				setLabel((String)newValue);
				return;
			case SQLSchemaPackage.SCHEMA__TRIGGERS:
				getTriggers().clear();
				getTriggers().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SCHEMA__INDICES:
				getIndices().clear();
				getIndices().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SCHEMA__TABLES:
				getTables().clear();
				getTables().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SCHEMA__SEQUENCES:
				getSequences().clear();
				getSequences().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SCHEMA__DATABASE:
				setDatabase((Database)newValue);
				return;
			case SQLSchemaPackage.SCHEMA__ASSERTIONS:
				getAssertions().clear();
				getAssertions().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES:
				getUserDefinedTypes().clear();
				getUserDefinedTypes().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SCHEMA__CHAR_SETS:
				getCharSets().clear();
				getCharSets().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SCHEMA__ROUTINES:
				getRoutines().clear();
				getRoutines().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SCHEMA__OWNER:
				setOwner((AuthorizationIdentifier)newValue);
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
			case SQLSchemaPackage.SCHEMA__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLSchemaPackage.SCHEMA__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLSchemaPackage.SCHEMA__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLSchemaPackage.SCHEMA__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLSchemaPackage.SCHEMA__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLSchemaPackage.SCHEMA__TRIGGERS:
				getTriggers().clear();
				return;
			case SQLSchemaPackage.SCHEMA__INDICES:
				getIndices().clear();
				return;
			case SQLSchemaPackage.SCHEMA__TABLES:
				getTables().clear();
				return;
			case SQLSchemaPackage.SCHEMA__SEQUENCES:
				getSequences().clear();
				return;
			case SQLSchemaPackage.SCHEMA__DATABASE:
				setDatabase((Database)null);
				return;
			case SQLSchemaPackage.SCHEMA__ASSERTIONS:
				getAssertions().clear();
				return;
			case SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES:
				getUserDefinedTypes().clear();
				return;
			case SQLSchemaPackage.SCHEMA__CHAR_SETS:
				getCharSets().clear();
				return;
			case SQLSchemaPackage.SCHEMA__ROUTINES:
				getRoutines().clear();
				return;
			case SQLSchemaPackage.SCHEMA__OWNER:
				setOwner((AuthorizationIdentifier)null);
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
			case SQLSchemaPackage.SCHEMA__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLSchemaPackage.SCHEMA__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLSchemaPackage.SCHEMA__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLSchemaPackage.SCHEMA__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLSchemaPackage.SCHEMA__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLSchemaPackage.SCHEMA__TRIGGERS:
				return triggers != null && !triggers.isEmpty();
			case SQLSchemaPackage.SCHEMA__INDICES:
				return indices != null && !indices.isEmpty();
			case SQLSchemaPackage.SCHEMA__TABLES:
				return tables != null && !tables.isEmpty();
			case SQLSchemaPackage.SCHEMA__SEQUENCES:
				return sequences != null && !sequences.isEmpty();
			case SQLSchemaPackage.SCHEMA__DATABASE:
				return database != null;
			case SQLSchemaPackage.SCHEMA__ASSERTIONS:
				return assertions != null && !assertions.isEmpty();
			case SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES:
				return userDefinedTypes != null && !userDefinedTypes.isEmpty();
			case SQLSchemaPackage.SCHEMA__CHAR_SETS:
				return charSets != null && !charSets.isEmpty();
			case SQLSchemaPackage.SCHEMA__ROUTINES:
				return routines != null && !routines.isEmpty();
			case SQLSchemaPackage.SCHEMA__OWNER:
				return owner != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SchemaImpl
