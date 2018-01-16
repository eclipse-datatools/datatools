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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.ENamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The base object to be used for any Relation Database concept, representing the placeholder for the name and alias.  This object will extend ENamedElement in the generated code.  The string attribute name will be inherited
 * from ENamedElement.
 * 
 * Note: In the RDB sense, only table and view objects can be represented by aliases, but DB2 defines also database and network aliases, and even for nicknames that refer to data tables or views located on federated systems.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject#getComments <em>Comments</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject#getPrivileges <em>Privileges</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSQLObject()
 * @model abstract="true"
 * @generated
 */
public interface SQLObject extends ENamedElement {
	/**
	 * Returns the value of the '<em><b>Dependencies</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.schema.Dependency}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependencies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependencies</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSQLObject_Dependencies()
	 * @model type="org.eclipse.datatools.modelbase.sql.schema.Dependency" containment="true"
	 * @generated
	 */
	EList getDependencies();

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
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSQLObject_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSQLObject_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Comments</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.schema.Comment}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Comment#getSQLObject <em>SQL Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSQLObject_Comments()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Comment#getSQLObject
	 * @model type="org.eclipse.datatools.modelbase.sql.schema.Comment" opposite="SQLObject"
	 * @generated
	 */
	EList getComments();

	/**
	 * Returns the value of the '<em><b>Extensions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.schema.ObjectExtension}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.ObjectExtension#getSQLObject <em>SQL Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extensions</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSQLObject_Extensions()
	 * @see org.eclipse.datatools.modelbase.sql.schema.ObjectExtension#getSQLObject
	 * @model type="org.eclipse.datatools.modelbase.sql.schema.ObjectExtension" opposite="SQLObject" containment="true"
	 * @generated
	 */
	EList getExtensions();

	/**
	 * Returns the value of the '<em><b>Privileges</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getObject <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Privileges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Privileges</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getSQLObject_Privileges()
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getObject
	 * @model type="org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege" opposite="object"
	 * @generated
	 */
	EList getPrivileges();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EAnnotation addEAnnotation(String source);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void addEAnnotationDetail(EAnnotation eAnnotation, String key, String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String getEAnnotationDetail(EAnnotation eAnnotation, String key);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setAnnotationDetail(EAnnotation eAnnotation, String key, String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void removeEAnnotationDetail(EAnnotation eAnnotation, String key);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EAnnotation getEAnnotation(String source);

} // SQLObject
