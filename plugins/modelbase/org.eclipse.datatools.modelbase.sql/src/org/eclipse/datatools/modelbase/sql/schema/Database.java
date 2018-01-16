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

import java.util.List;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Database</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Database#getVendor <em>Vendor</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Database#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Database#getSchemas <em>Schemas</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Database#getEvents <em>Events</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Database#getCatalogs <em>Catalogs</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Database#getAuthorizationIds <em>Authorization Ids</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getDatabase()
 * @model
 * @generated
 */
public interface Database extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vendor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vendor</em>' attribute.
	 * @see #setVendor(String)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getDatabase_Vendor()
	 * @model
	 * @generated
	 */
	String getVendor();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Database#getVendor <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vendor</em>' attribute.
	 * @see #getVendor()
	 * @generated
	 */
	void setVendor(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getDatabase_Version()
	 * @model
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Database#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Schemas</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.schema.Schema}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schemas</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schemas</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getDatabase_Schemas()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getDatabase
	 * @model type="org.eclipse.datatools.modelbase.sql.schema.Schema" opposite="database"
	 * @generated
	 */
	EList getSchemas();

	/**
	 * Returns the value of the '<em><b>Events</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.schema.Event}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Event#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Events</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Events</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getDatabase_Events()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Event#getDatabase
	 * @model type="org.eclipse.datatools.modelbase.sql.schema.Event" opposite="Database"
	 * @generated
	 */
	EList getEvents();

	/**
	 * Returns the value of the '<em><b>Catalogs</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.schema.Catalog}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Catalog#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Catalogs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Catalogs</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getDatabase_Catalogs()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Catalog#getDatabase
	 * @model type="org.eclipse.datatools.modelbase.sql.schema.Catalog" opposite="Database"
	 * @generated
	 */
	EList getCatalogs();

	/**
	 * Returns the value of the '<em><b>Authorization Ids</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Authorization Ids</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Authorization Ids</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getDatabase_AuthorizationIds()
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getDatabase
	 * @model type="org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier" opposite="Database"
	 * @generated
	 */
	EList getAuthorizationIds();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false"
	 * @generated
	 */
	List getUserDefinedTypes();

} // Database
