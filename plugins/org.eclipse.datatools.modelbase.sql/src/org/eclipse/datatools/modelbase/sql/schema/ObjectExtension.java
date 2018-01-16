/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.schema;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.ObjectExtension#getSQLObject <em>SQL Object</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getObjectExtension()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ObjectExtension extends EObject {
	/**
	 * Returns the value of the '<em><b>SQL Object</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SQL Object</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>SQL Object</em>' container reference.
	 * @see #setSQLObject(SQLObject)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getObjectExtension_SQLObject()
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLObject#getExtensions
	 * @model opposite="extensions" required="true"
	 * @generated
	 */
	SQLObject getSQLObject();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.ObjectExtension#getSQLObject <em>SQL Object</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SQL Object</em>' container reference.
	 * @see #getSQLObject()
	 * @generated
	 */
	void setSQLObject(SQLObject value);

} // ObjectExtension
