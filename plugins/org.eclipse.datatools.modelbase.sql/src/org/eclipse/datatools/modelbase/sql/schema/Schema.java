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
package org.eclipse.datatools.modelbase.sql.schema;

import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.20 SQL-schemas
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getIndices <em>Indices</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getTables <em>Tables</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getSequences <em>Sequences</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getDatabase <em>Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getCatalog <em>Catalog</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getAssertions <em>Assertions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getUserDefinedTypes <em>User Defined Types</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getCharSets <em>Char Sets</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getRoutines <em>Routines</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getOwner <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSchema()
 * @model
 * @generated
 */
public interface Schema extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Triggers</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.tables.Trigger}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc --> 
	 * <p>
	 * If the meaning of the '<em>Triggers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Triggers</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSchema_Triggers()
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#getSchema
	 * @model type="org.eclipse.datatools.modelbase.sql.tables.Trigger" opposite="schema"
	 * @generated
	 */
	EList getTriggers();

	/**
	 * Returns the value of the '<em><b>Indices</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.constraints.Index}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indices</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indices</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSchema_Indices()
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Index#getSchema
	 * @model type="org.eclipse.datatools.modelbase.sql.constraints.Index" opposite="Schema"
	 * @generated
	 */
	EList getIndices();

	/**
	 * Returns the value of the '<em><b>Tables</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.tables.Table}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tables</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSchema_Tables()
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#getSchema
	 * @model type="org.eclipse.datatools.modelbase.sql.tables.Table" opposite="schema"
	 * @generated
	 */
	EList getTables();

	/**
	 * Returns the value of the '<em><b>Sequences</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.schema.Sequence}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Sequence#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequences</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequences</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSchema_Sequences()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Sequence#getSchema
	 * @model type="org.eclipse.datatools.modelbase.sql.schema.Sequence" opposite="schema"
	 * @generated
	 */
	EList getSequences();

	/**
	 * Returns the value of the '<em><b>Database</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Database#getSchemas <em>Schemas</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database</em>' reference.
	 * @see #setDatabase(Database)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSchema_Database()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Database#getSchemas
	 * @model opposite="schemas" required="true"
	 * @generated
	 */
	Database getDatabase();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getDatabase <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database</em>' reference.
	 * @see #getDatabase()
	 * @generated
	 */
	void setDatabase(Database value);

	/**
	 * Returns the value of the '<em><b>Catalog</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Catalog#getSchemas <em>Schemas</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Catalog</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Catalog</em>' reference.
	 * @see #setCatalog(Catalog)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSchema_Catalog()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Catalog#getSchemas
	 * @model opposite="schemas" required="true"
	 * @generated
	 */
	Catalog getCatalog();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getCatalog <em>Catalog</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Catalog</em>' reference.
	 * @see #getCatalog()
	 * @generated
	 */
	void setCatalog(Catalog value);

	/**
	 * Returns the value of the '<em><b>Assertions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.constraints.Assertion}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.constraints.Assertion#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assertions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assertions</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSchema_Assertions()
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Assertion#getSchema
	 * @model type="org.eclipse.datatools.modelbase.sql.constraints.Assertion" opposite="schema"
	 * @generated
	 */
	EList getAssertions();

	/**
	 * Returns the value of the '<em><b>User Defined Types</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Defined Types</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Defined Types</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSchema_UserDefinedTypes()
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType#getSchema
	 * @model type="org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType" opposite="schema"
	 * @generated
	 */
	EList getUserDefinedTypes();

	/**
	 * Returns the value of the '<em><b>Char Sets</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Char Sets</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Char Sets</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSchema_CharSets()
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getSchema
	 * @model type="org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet" opposite="schema"
	 * @generated
	 */
	EList getCharSets();

	/**
	 * Returns the value of the '<em><b>Routines</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.routines.Routine}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Routines</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Routines</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSchema_Routines()
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#getSchema
	 * @model type="org.eclipse.datatools.modelbase.sql.routines.Routine" opposite="schema"
	 * @generated
	 */
	EList getRoutines();

   /**
    * Get a list of procedures belonging to this schema.
    * <p>
    * @return the procedures contained within the schema. If 
    * there are none, then an empty list is returned.
    */
   public EList getProcedures();

   /**
    * Get a list of user-defined functions belonging to this schema.
    * <p>
    * @return the user-defined functions contained within the schema.  If 
    * there are none, then an empty list is returned.
    */
   public EList getUDFs();

   /**
    * Get a list of built-in (aka system) functions belonging to this schema.
    * <p>
    * @return the built-in functions contained within the schema. If 
    * there are none, then an empty list is returned.
    */
   public EList getBuiltInFunctions();

	/**
	 * Returns the value of the '<em><b>Owner</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getOwnedSchema <em>Owned Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' reference.
	 * @see #setOwner(AuthorizationIdentifier)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSchema_Owner()
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getOwnedSchema
	 * @model opposite="ownedSchema" required="true"
	 * @generated
	 */
	AuthorizationIdentifier getOwner();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getOwner <em>Owner</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(AuthorizationIdentifier value);

} // Schema
