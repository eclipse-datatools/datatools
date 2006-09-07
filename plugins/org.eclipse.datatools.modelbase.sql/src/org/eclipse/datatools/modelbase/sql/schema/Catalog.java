/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.schema;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Catalog</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Catalog#getDatabase <em>Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Catalog#getSchemas <em>Schemas</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getCatalog()
 * @model
 * @generated
 */
public interface Catalog extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Database</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Database#getCatalogs <em>Catalogs</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database</em>' reference.
	 * @see #setDatabase(Database)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getCatalog_Database()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Database#getCatalogs
	 * @model opposite="catalogs" required="true"
	 * @generated
	 */
	Database getDatabase();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Catalog#getDatabase <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database</em>' reference.
	 * @see #getDatabase()
	 * @generated
	 */
	void setDatabase(Database value);

	/**
	 * Returns the value of the '<em><b>Schemas</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.schema.Schema}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getCatalog <em>Catalog</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schemas</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schemas</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getCatalog_Schemas()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getCatalog
	 * @model type="org.eclipse.datatools.modelbase.sql.schema.Schema" opposite="Catalog"
	 * @generated
	 */
	EList getSchemas();

} // Catalog