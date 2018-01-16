/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Privileged Element Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition#getPrivilegeDefinitions <em>Privilege Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPrivilegedElementDefinition()
 * @model
 * @generated
 */
public interface PrivilegedElementDefinition extends EObject {
	/**
	 * Returns the value of the '<em><b>Privilege Definitions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.PrivilegeDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Privilege Definitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Privilege Definitions</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPrivilegedElementDefinition_PrivilegeDefinitions()
	 * @model type="org.eclipse.datatools.modelbase.dbdefinition.PrivilegeDefinition" containment="true"
	 * @generated
	 */
	EList getPrivilegeDefinitions();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPrivilegedElementDefinition_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // PrivilegedElementDefinition
