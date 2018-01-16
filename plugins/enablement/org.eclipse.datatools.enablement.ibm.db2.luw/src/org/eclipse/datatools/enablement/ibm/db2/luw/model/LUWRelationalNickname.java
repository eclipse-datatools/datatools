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
 * A representation of the model object '<em><b>Relational Nickname</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalNickname#getRelServer <em>Rel Server</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWRelationalNickname()
 * @model abstract="true"
 * @generated
 */
public interface LUWRelationalNickname extends LUWNickname{
	/**
	 * Returns the value of the '<em><b>Rel Server</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getRelNicknames <em>Rel Nicknames</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rel Server</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rel Server</em>' reference.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWRelationalNickname_RelServer()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getRelNicknames
	 * @model opposite="relNicknames" required="true" transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	LUWRelationalServer getRelServer();

} // LUWRelationalNickname
