/**
 * <copyright>
 * </copyright>
 *
 * $Id: ViewDefinition.java,v 1.1 2006/03/09 23:48:17 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>View Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#isIndexSupported <em>Index Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getViewDefinition()
 * @model
 * @generated
 */
public interface ViewDefinition extends EObject {
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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getViewDefinition_MaximumIdentifierLength()
	 * @model
	 * @generated
	 */
	int getMaximumIdentifierLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Identifier Length</em>' attribute.
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 */
	void setMaximumIdentifierLength(int value);

	/**
	 * Returns the value of the '<em><b>Index Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index Supported</em>' attribute.
	 * @see #setIndexSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getViewDefinition_IndexSupported()
	 * @model
	 * @generated
	 */
	boolean isIndexSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#isIndexSupported <em>Index Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index Supported</em>' attribute.
	 * @see #isIndexSupported()
	 * @generated
	 */
	void setIndexSupported(boolean value);

} // ViewDefinition
