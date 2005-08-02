/**
 * <copyright>
 * </copyright>
 *
 * $Id: NicknameDefinition.java,v 1.2 2005/06/15 18:16:00 ledunnel Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Nickname Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition#isConstraintSupported <em>Constraint Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition#isIndexSupported <em>Index Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getNicknameDefinition()
 * @model 
 * @generated
 */
public interface NicknameDefinition extends EObject {
	/**
	 * Returns the value of the '<em><b>Constraint Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint Supported</em>' attribute.
	 * @see #setConstraintSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getNicknameDefinition_ConstraintSupported()
	 * @model 
	 * @generated
	 */
	boolean isConstraintSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition#isConstraintSupported <em>Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint Supported</em>' attribute.
	 * @see #isConstraintSupported()
	 * @generated
	 */
	void setConstraintSupported(boolean value);

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getNicknameDefinition_IndexSupported()
	 * @model 
	 * @generated
	 */
	boolean isIndexSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition#isIndexSupported <em>Index Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index Supported</em>' attribute.
	 * @see #isIndexSupported()
	 * @generated
	 */
	void setIndexSupported(boolean value);

} // NicknameDefinition
