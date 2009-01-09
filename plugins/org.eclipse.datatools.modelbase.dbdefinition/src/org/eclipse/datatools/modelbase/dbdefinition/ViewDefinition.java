/**
 * <copyright>
 * </copyright>
 *
 * $Id: ViewDefinition.java,v 1.2 2007/11/01 23:08:39 dpchou Exp $
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
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#isCheckOptionSupported <em>Check Option Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#isCheckOptionLevelsSupported <em>Check Option Levels Supported</em>}</li>
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

	/**
	 * Returns the value of the '<em><b>Check Option Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Check Option Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Check Option Supported</em>' attribute.
	 * @see #setCheckOptionSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getViewDefinition_CheckOptionSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isCheckOptionSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#isCheckOptionSupported <em>Check Option Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Check Option Supported</em>' attribute.
	 * @see #isCheckOptionSupported()
	 * @generated
	 */
	void setCheckOptionSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Check Option Levels Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Check Option Levels Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Check Option Levels Supported</em>' attribute.
	 * @see #setCheckOptionLevelsSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getViewDefinition_CheckOptionLevelsSupported()
	 * @model
	 * @generated
	 */
	boolean isCheckOptionLevelsSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#isCheckOptionLevelsSupported <em>Check Option Levels Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Check Option Levels Supported</em>' attribute.
	 * @see #isCheckOptionLevelsSupported()
	 * @generated
	 */
	void setCheckOptionLevelsSupported(boolean value);

} // ViewDefinition
