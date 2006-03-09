/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Schema Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.SchemaDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSchemaDefinition()
 * @model
 * @generated
 */
public interface SchemaDefinition extends EObject {
	/**
	 * Returns the value of the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Identifier Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Identifier Length</em>' attribute.
	 * @see #setMaximumIdentifierLength(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSchemaDefinition_MaximumIdentifierLength()
	 * @model
	 * @generated
	 */
	int getMaximumIdentifierLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.SchemaDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Identifier Length</em>' attribute.
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 */
	void setMaximumIdentifierLength(int value);

} // SchemaDefinition
