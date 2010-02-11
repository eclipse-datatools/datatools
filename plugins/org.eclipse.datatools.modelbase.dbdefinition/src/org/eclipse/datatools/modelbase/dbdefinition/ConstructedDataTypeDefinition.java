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
 * A representation of the model object '<em><b>Constructed Data Type Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isArrayDatatypeSupported <em>Array Datatype Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isMultisetDatatypeSupported <em>Multiset Datatype Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isRowDatatypeSupported <em>Row Datatype Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isReferenceDatatypeSupported <em>Reference Datatype Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isCursorDatatypeSupported <em>Cursor Datatype Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstructedDataTypeDefinition()
 * @model
 * @generated
 */
public interface ConstructedDataTypeDefinition extends EObject {
	/**
	 * Returns the value of the '<em><b>Array Datatype Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Array Datatype Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Array Datatype Supported</em>' attribute.
	 * @see #setArrayDatatypeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstructedDataTypeDefinition_ArrayDatatypeSupported()
	 * @model
	 * @generated
	 */
	boolean isArrayDatatypeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isArrayDatatypeSupported <em>Array Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Array Datatype Supported</em>' attribute.
	 * @see #isArrayDatatypeSupported()
	 * @generated
	 */
	void setArrayDatatypeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Multiset Datatype Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiset Datatype Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiset Datatype Supported</em>' attribute.
	 * @see #setMultisetDatatypeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstructedDataTypeDefinition_MultisetDatatypeSupported()
	 * @model
	 * @generated
	 */
	boolean isMultisetDatatypeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isMultisetDatatypeSupported <em>Multiset Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiset Datatype Supported</em>' attribute.
	 * @see #isMultisetDatatypeSupported()
	 * @generated
	 */
	void setMultisetDatatypeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Row Datatype Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row Datatype Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row Datatype Supported</em>' attribute.
	 * @see #setRowDatatypeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstructedDataTypeDefinition_RowDatatypeSupported()
	 * @model
	 * @generated
	 */
	boolean isRowDatatypeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isRowDatatypeSupported <em>Row Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row Datatype Supported</em>' attribute.
	 * @see #isRowDatatypeSupported()
	 * @generated
	 */
	void setRowDatatypeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Reference Datatype Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Datatype Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Datatype Supported</em>' attribute.
	 * @see #setReferenceDatatypeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstructedDataTypeDefinition_ReferenceDatatypeSupported()
	 * @model
	 * @generated
	 */
	boolean isReferenceDatatypeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isReferenceDatatypeSupported <em>Reference Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference Datatype Supported</em>' attribute.
	 * @see #isReferenceDatatypeSupported()
	 * @generated
	 */
	void setReferenceDatatypeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Cursor Datatype Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cursor Datatype Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cursor Datatype Supported</em>' attribute.
	 * @see #setCursorDatatypeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstructedDataTypeDefinition_CursorDatatypeSupported()
	 * @model
	 * @generated
	 */
	boolean isCursorDatatypeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isCursorDatatypeSupported <em>Cursor Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cursor Datatype Supported</em>' attribute.
	 * @see #isCursorDatatypeSupported()
	 * @generated
	 */
	void setCursorDatatypeSupported(boolean value);

} // ConstructedDataTypeDefinition
