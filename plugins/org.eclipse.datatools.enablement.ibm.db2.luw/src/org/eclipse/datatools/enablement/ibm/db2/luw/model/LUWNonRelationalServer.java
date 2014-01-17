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
 * A representation of the model object '<em><b>Non Relational Server</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer#getNonRelWrapper <em>Non Rel Wrapper</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer#getNonRelNicknames <em>Non Rel Nicknames</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWNonRelationalServer()
 * @model abstract="true"
 * @generated
 */
public interface LUWNonRelationalServer extends LUWServer{
	/**
	 * Returns the value of the '<em><b>Non Rel Wrapper</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalWrapper#getNonRelServers <em>Non Rel Servers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Non Rel Wrapper</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Non Rel Wrapper</em>' reference.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWNonRelationalServer_NonRelWrapper()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalWrapper#getNonRelServers
	 * @model opposite="nonRelServers" required="true" transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	LUWNonRelationalWrapper getNonRelWrapper();

	/**
	 * Returns the value of the '<em><b>Non Rel Nicknames</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalNickname}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalNickname#getNonRelServer <em>Non Rel Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Non Rel Nicknames</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Non Rel Nicknames</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWNonRelationalServer_NonRelNicknames()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalNickname#getNonRelServer
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalNickname" opposite="nonRelServer" transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	EList getNonRelNicknames();

} // LUWNonRelationalServer
