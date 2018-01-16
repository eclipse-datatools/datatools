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
package org.eclipse.datatools.modelbase.sql.routines;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Routine</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.27 SQL-invoked routines
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSpecificName <em>Specific Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getParameterStyle <em>Parameter Style</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Routine#isDeterministic <em>Deterministic</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSqlDataAccess <em>Sql Data Access</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getCreationTS <em>Creation TS</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getLastAlteredTS <em>Last Altered TS</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getAuthorizationID <em>Authorization ID</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSecurity <em>Security</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getExternalName <em>External Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getRoutine()
 * @model abstract="true"
 * @generated
 */
public interface Routine extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From 5WD-02-Foundation-2002-12
	 * 4.27 SQL-invoked routines
	 * 
	 * An SQL-invoked routine is uniquely identified by a <specific name> , called the specific name of the SQLinvoked
	 * routine.
	 * 
	 * Typically qualified with a schema name. 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Specific Name</em>' attribute.
	 * @see #setSpecificName(String)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getRoutine_SpecificName()
	 * @model
	 * @generated
	 */
	String getSpecificName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSpecificName <em>Specific Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specific Name</em>' attribute.
	 * @see #getSpecificName()
	 * @generated
	 */
	void setSpecificName(String value);

	/**
	 * Returns the value of the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Language</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Since there are many languages, rather than constrain ourselves to an enumerated list which is difficult to subclass in the EMF environment, we have chosen to use strings.
	 * 
	 * JAVA
	 * SQL
	 * PERL
	 * C
	 * ADA
	 * COBOL
	 * FORTRAN
	 * MUMPS
	 * PASCAL
	 * PLI
	 * and so on...
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Language</em>' attribute.
	 * @see #setLanguage(String)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getRoutine_Language()
	 * @model
	 * @generated
	 */
	String getLanguage();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getLanguage <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language</em>' attribute.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(String value);

	/**
	 * Returns the value of the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Style</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Paraphrased from 5WD-02-Foundation-2002-12 
	 * The spec only calls for two. Though it seems like an easy choice for enumeration as in the language attribute, other vendors have different styles and since enumeration is not good to subclass within the EMF tooling framework, a string representation is used.
	 * 
	 * 11.50 <SQL-invoked routine>
	 * SQL 
	 * GENERAL.
	 * 
	 * Used in stored procedures, user defined functions, and methods.
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parameter Style</em>' attribute.
	 * @see #setParameterStyle(String)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getRoutine_ParameterStyle()
	 * @model
	 * @generated
	 */
	String getParameterStyle();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getParameterStyle <em>Parameter Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Style</em>' attribute.
	 * @see #getParameterStyle()
	 * @generated
	 */
	void setParameterStyle(String value);

	/**
	 * Returns the value of the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From 5WD-02-Foundation-2002-12
	 * 4.27 SQL-invoked routines
	 * 
	 * An SQL-invoked routine is either deterministic or possibly non-deterministic. An SQL-invoked function that
	 * is deterministic always returns the identical return value for a given list of SQL argument values. An SQLinvoked
	 * procedure that is deterministic always returns the identical values in its output and inout SQL parameters
	 * for a given list of SQL argument values. An SQL-invoked routine is possibly non-deterministic if it might
	 * produce nonidentical results when invoked with the identical list of SQL argument values.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deterministic</em>' attribute.
	 * @see #setDeterministic(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getRoutine_Deterministic()
	 * @model
	 * @generated
	 */
	boolean isDeterministic();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#isDeterministic <em>Deterministic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deterministic</em>' attribute.
	 * @see #isDeterministic()
	 * @generated
	 */
	void setDeterministic(boolean value);

	/**
	 * Returns the value of the '<em><b>Sql Data Access</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.routines.DataAccess}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sql Data Access</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 5WD-02-Foundation-2002-12
	 * 11.50 <SQL-invoked routine>
	 * 
	 * <SQL-data access indication> ::= NO SQL | CONTAINS SQL | READS SQL DATA | MODIFIES SQL DATA
	 * 
	 * (see DataAccess enumeration class)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sql Data Access</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.routines.DataAccess
	 * @see #setSqlDataAccess(DataAccess)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getRoutine_SqlDataAccess()
	 * @model
	 * @generated
	 */
	DataAccess getSqlDataAccess();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSqlDataAccess <em>Sql Data Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sql Data Access</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.routines.DataAccess
	 * @see #getSqlDataAccess()
	 * @generated
	 */
	void setSqlDataAccess(DataAccess value);

	/**
	 * Returns the value of the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From 5WD-02-Foundation-2002-12
	 * 4.27 SQL-invoked routines
	 * 
	 * An SQL-invoked routine is described by a routine descriptor. A routine descriptor includes:
	 * - The creation timestamp.
	 * 
	 * Because there is no provision in the spec as to the format of the timestamp, it is left to the individual implementers to determine the format.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Creation TS</em>' attribute.
	 * @see #setCreationTS(String)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getRoutine_CreationTS()
	 * @model
	 * @generated
	 */
	String getCreationTS();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getCreationTS <em>Creation TS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation TS</em>' attribute.
	 * @see #getCreationTS()
	 * @generated
	 */
	void setCreationTS(String value);

	/**
	 * Returns the value of the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From 5WD-02-Foundation-2002-12
	 * 4.27 SQL-invoked routines
	 * 
	 * An SQL-invoked routine is described by a routine descriptor. A routine descriptor includes:
	 * - The last-altered timestamp.
	 * 
	 * Because there is no provision in the spec as to the format of the timestamp, it is left to the individual implementers to determine the format.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Last Altered TS</em>' attribute.
	 * @see #setLastAlteredTS(String)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getRoutine_LastAlteredTS()
	 * @model
	 * @generated
	 */
	String getLastAlteredTS();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getLastAlteredTS <em>Last Altered TS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Altered TS</em>' attribute.
	 * @see #getLastAlteredTS()
	 * @generated
	 */
	void setLastAlteredTS(String value);

	/**
	 * Returns the value of the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From 5WD-02-Foundation-2002-12
	 * 4.27 SQL-invoked routines
	 * 
	 * An SQL-invoked routine has a routine authorization identifier, which is (directly or indirectly) the authorization
	 * identifier of the owner of the schema that contains the SQL-invoked routine at the time that the SQL-invoked
	 * routine is created.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Authorization ID</em>' attribute.
	 * @see #setAuthorizationID(String)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getRoutine_AuthorizationID()
	 * @model
	 * @generated
	 */
	String getAuthorizationID();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getAuthorizationID <em>Authorization ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Authorization ID</em>' attribute.
	 * @see #getAuthorizationID()
	 * @generated
	 */
	void setAuthorizationID(String value);

	/**
	 * Returns the value of the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From the below, typical values would be: "DEFINER", "INVOKER", "IMPLEMENTATION DEFINED"
	 * Since there are several implementation defined security designations, it is best to go with the string.
	 * 
	 * From 5WD-02-Foundation-2002-12
	 * 11.50 <SQL-invoked routine>
	 * 
	 * <external security clause> ::=
	 *   EXTERNAL SECURITY DEFINER
	 *   | EXTERNAL SECURITY INVOKER
	 *   | EXTERNAL SECURITY IMPLEMENTATION DEFINED
	 * 
	 * 4.27 SQL-invoked routines
	 * If the SQL-invoked routine is an SQL routine, then the identifiers are determined according to the SQL security
	 * characteristic of the SQL-invoked routine:
	 * - If the SQL security characteristic is DEFINER , then:
	 *   * If the routine authorization identifier is a user identifier, the user identifier is set to the routine authorization
	 *     identifier and the role name is set to null.
	 *   * Otherwise, the role name is set to the routine authorization identifier and the user identifier is set to
	 *     null.
	 * - If the SQL security characteristic is INVOKER , then the identifiers remain unchanged.      
	 * If the SQL-invoked routine is an external routine, then the identifiers are determined according to the external
	 * security characteristic of the SQL-invoked routine:
	 * - If the external security characteristic is DEFINER , then:
	 *   * If the routine authorization identifier is a user identifier, then the user identifier is set to the routine
	 *     authorization identifier and the role name is set to the null value.
	 *   * Otherwise, the role name is set to the routine authorization identifier and the user identifier is set to the
	 *     null value.
	 * - If the external security characteristic is INVOKER , then the identifiers remain unchanged.
	 * - If the external security characteristic is IMPLEMENTATION DEFINED , then the identifiers are set to
	 * implementation-defined values.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Security</em>' attribute.
	 * @see #setSecurity(String)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getRoutine_Security()
	 * @model
	 * @generated
	 */
	String getSecurity();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSecurity <em>Security</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Security</em>' attribute.
	 * @see #getSecurity()
	 * @generated
	 */
	void setSecurity(String value);

	/**
	 * Returns the value of the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From 5WD-02-Foundation-2002-12
	 * from 11.50 SQL-invoked routine:
	 * 
	 * The external name of the routine descriptor is <external routine name> .
	 * <external body reference> ::= EXTERNAL [ NAME <external routine name> ] [ <parameter
	 * style clause> ] [ <transform group specification> ] [ <external security clause> ]
	 * 
	 * from 4.27 SQL-invoked routines
	 * <external routine name> identifies a program written in some
	 * standard programming language other than SQL .
	 * 
	 * from 5.4 Names and identifiers
	 * <external routine name> ::= <identifier> | <character string literal>
	 * An <external routine name> identifies an external routine.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>External Name</em>' attribute.
	 * @see #setExternalName(String)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getRoutine_ExternalName()
	 * @model
	 * @generated
	 */
	String getExternalName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getExternalName <em>External Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Name</em>' attribute.
	 * @see #getExternalName()
	 * @generated
	 */
	void setExternalName(String value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.routines.Parameter}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.routines.Parameter#getRoutine <em>Routine</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getRoutine_Parameters()
	 * @see org.eclipse.datatools.modelbase.sql.routines.Parameter#getRoutine
	 * @model type="org.eclipse.datatools.modelbase.sql.routines.Parameter" opposite="routine" containment="true"
	 * @generated
	 */
	EList getParameters();

   /**
    * Gets a list of IN and INOUT parameters associated with this routine.
    * <p>
    * @return a list of IN and INOUT parameters associated with this routine.
    */
   public EList getInputParameters();

   /**
    * Gets a list of OUT and INOUT parameters associated with this routine.
    * <p>
    * @return a list of OUT and INOUT parameters associated with this routine.
    */
   public EList getOutputParameters();

	/**
	 * Returns the value of the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' containment reference.
	 * @see #setSource(Source)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getRoutine_Source()
	 * @model containment="true"
	 * @generated
	 */
	Source getSource();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSource <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' containment reference.
	 * @see #getSource()
	 * @generated
	 */
   void setSource(Source value);

	/**
	 * Returns the value of the '<em><b>Schema</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getRoutines <em>Routines</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema</em>' reference.
	 * @see #setSchema(Schema)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getRoutine_Schema()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getRoutines
	 * @model opposite="routines" required="true"
	 * @generated
	 */
	Schema getSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSchema <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' reference.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(Schema value);

} // Routine
