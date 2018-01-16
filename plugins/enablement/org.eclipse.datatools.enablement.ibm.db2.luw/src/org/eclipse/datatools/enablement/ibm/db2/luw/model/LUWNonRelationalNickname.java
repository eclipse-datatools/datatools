/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Non Relational Nickname</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalNickname#getNonRelServer <em>Non Rel Server</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWNonRelationalNickname()
 * @model abstract="true"
 * @generated
 */
public interface LUWNonRelationalNickname extends LUWNickname{
	/**
	 * Returns the value of the '<em><b>Non Rel Server</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer#getNonRelNicknames <em>Non Rel Nicknames</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Non Rel Server</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Non Rel Server</em>' reference.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWNonRelationalNickname_NonRelServer()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer#getNonRelNicknames
	 * @model opposite="nonRelNicknames" required="true" transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	LUWNonRelationalServer getNonRelServer();

} // LUWNonRelationalNickname
