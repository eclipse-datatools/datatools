/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLSyntaxDefinition.java,v 1.3 2005/06/15 18:16:00 ledunnel Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Syntax Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition#getKeywords <em>Keywords</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition#getOperators <em>Operators</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSQLSyntaxDefinition()
 * @model 
 * @generated
 */
public interface SQLSyntaxDefinition extends EObject {
	/**
	 * Returns the value of the '<em><b>Keywords</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Keywords</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Keywords</em>' attribute list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSQLSyntaxDefinition_Keywords()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getKeywords();

	/**
	 * Returns the value of the '<em><b>Operators</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operators</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operators</em>' attribute list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSQLSyntaxDefinition_Operators()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getOperators();

} // SQLSyntaxDefinition
