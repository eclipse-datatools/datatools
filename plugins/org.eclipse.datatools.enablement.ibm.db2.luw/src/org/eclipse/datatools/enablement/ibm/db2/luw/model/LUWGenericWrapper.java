/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWGenericWrapper.java,v 1.7 2007/01/15 18:40:05 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Wrapper</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericWrapper#getGenericServers <em>Generic Servers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWGenericWrapper()
 * @model
 * @generated
 */
public interface LUWGenericWrapper extends LUWWrapper {
	/**
	 * Returns the value of the '<em><b>Generic Servers</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer#getGenericWrapper <em>Generic Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generic Servers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generic Servers</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWGenericWrapper_GenericServers()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer#getGenericWrapper
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer" opposite="genericWrapper" transient="true" volatile="true"
	 * @generated
	 */
	EList getGenericServers();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList getServers();

} // LUWGenericWrapper
