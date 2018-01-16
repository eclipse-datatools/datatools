/**
 * <copyright>
 * </copyright>
 *
 * $Id: Comment.java,v 1.1 2006/09/07 00:19:47 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.sql.schema;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Comment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Comment#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Comment#getSQLObject <em>SQL Object</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getComment()
 * @model
 * @generated
 */
public interface Comment extends EObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getComment_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Comment#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>SQL Object</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject#getComments <em>Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SQL Object</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>SQL Object</em>' reference.
	 * @see #setSQLObject(SQLObject)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getComment_SQLObject()
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLObject#getComments
	 * @model opposite="comments" required="true"
	 * @generated
	 */
	SQLObject getSQLObject();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Comment#getSQLObject <em>SQL Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SQL Object</em>' reference.
	 * @see #getSQLObject()
	 * @generated
	 */
	void setSQLObject(SQLObject value);

} // Comment