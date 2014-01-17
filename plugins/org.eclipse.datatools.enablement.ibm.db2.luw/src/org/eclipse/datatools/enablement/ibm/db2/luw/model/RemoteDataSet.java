/**
 * <copyright>
 * </copyright>
 *
 * $Id: RemoteDataSet.java,v 1.8 2008/01/29 00:04:56 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Remote Data Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteDataSet#getNickname <em>Nickname</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getRemoteDataSet()
 * @model
 * @generated
 */
public interface RemoteDataSet extends EObject {
	/**
	 * Returns the value of the '<em><b>Nickname</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname#getRemoteDataSet <em>Remote Data Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nickname</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nickname</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getRemoteDataSet_Nickname()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname#getRemoteDataSet
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname" opposite="remoteDataSet"
	 * @generated
	 */
	EList getNickname();

} // RemoteDataSet
