/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWModuleCondition.java,v 1.1 2009/01/31 00:22:40 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCondition#getSqlstate <em>Sqlstate</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWModuleCondition()
 * @model
 * @generated
 */
public interface LUWModuleCondition extends SQLObject, LUWModuleObject {
	/**
	 * Returns the value of the '<em><b>Sqlstate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sqlstate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sqlstate</em>' attribute.
	 * @see #setSqlstate(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWModuleCondition_Sqlstate()
	 * @model
	 * @generated
	 */
	String getSqlstate();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCondition#getSqlstate <em>Sqlstate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sqlstate</em>' attribute.
	 * @see #getSqlstate()
	 * @generated
	 */
	void setSqlstate(String value);

} // LUWModuleCondition
