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
 * A representation of the model object '<em><b>Debugger Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DebuggerDefinition#isConditionSupported <em>Condition Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDebuggerDefinition()
 * @model
 * @generated
 */
public interface DebuggerDefinition extends EObject {
	/**
	 * Returns the value of the '<em><b>Condition Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition Supported</em>' attribute.
	 * @see #setConditionSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDebuggerDefinition_ConditionSupported()
	 * @model
	 * @generated
	 */
	boolean isConditionSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DebuggerDefinition#isConditionSupported <em>Condition Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition Supported</em>' attribute.
	 * @see #isConditionSupported()
	 * @generated
	 */
	void setConditionSupported(boolean value);

} // DebuggerDefinition
