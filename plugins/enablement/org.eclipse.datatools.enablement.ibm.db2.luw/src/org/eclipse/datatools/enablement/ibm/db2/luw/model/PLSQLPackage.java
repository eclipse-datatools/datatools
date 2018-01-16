/**
 * <copyright>
 * </copyright>
 *
 * $Id: PLSQLPackage.java,v 1.2 2009/03/06 22:38:09 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PLSQL Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackage#getPackageBody <em>Package Body</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getPLSQLPackage()
 * @model
 * @generated
 */
public interface PLSQLPackage extends LUWModule, DB2Routine {
	/**
	 * Returns the value of the '<em><b>Package Body</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackageBody#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package Body</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Body</em>' containment reference.
	 * @see #setPackageBody(PLSQLPackageBody)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getPLSQLPackage_PackageBody()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackageBody#getPackage
	 * @model opposite="package" containment="true"
	 * @generated
	 */
	PLSQLPackageBody getPackageBody();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackage#getPackageBody <em>Package Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Body</em>' containment reference.
	 * @see #getPackageBody()
	 * @generated
	 */
	void setPackageBody(PLSQLPackageBody value);

} // PLSQLPackage
