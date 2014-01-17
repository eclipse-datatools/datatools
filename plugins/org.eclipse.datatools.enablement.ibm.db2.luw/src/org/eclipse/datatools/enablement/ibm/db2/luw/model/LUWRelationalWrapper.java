/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relational Wrapper</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalWrapper#getRelServers <em>Rel Servers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWRelationalWrapper()
 * @model abstract="true"
 * @generated
 */
public interface LUWRelationalWrapper extends LUWWrapper{
	/**
	 * Returns the value of the '<em><b>Rel Servers</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getRelWrapper <em>Rel Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rel Servers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rel Servers</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWRelationalWrapper_RelServers()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getRelWrapper
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer" opposite="relWrapper" transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	EList getRelServers();

} // LUWRelationalWrapper
