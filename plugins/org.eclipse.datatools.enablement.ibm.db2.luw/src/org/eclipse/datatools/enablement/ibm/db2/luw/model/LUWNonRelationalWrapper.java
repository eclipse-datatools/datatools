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
 * A representation of the model object '<em><b>Non Relational Wrapper</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalWrapper#getNonRelServers <em>Non Rel Servers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWNonRelationalWrapper()
 * @model abstract="true"
 * @generated
 */
public interface LUWNonRelationalWrapper extends LUWWrapper{
	/**
	 * Returns the value of the '<em><b>Non Rel Servers</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer#getNonRelWrapper <em>Non Rel Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Non Rel Servers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Non Rel Servers</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWNonRelationalWrapper_NonRelServers()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer#getNonRelWrapper
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer" opposite="nonRelWrapper" transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	EList getNonRelServers();

} // LUWNonRelationalWrapper
