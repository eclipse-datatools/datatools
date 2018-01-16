/**
 * <copyright>
 * </copyright>
 *
 * $Id: PLSQLPackageBody.java,v 1.1 2009/02/25 19:05:46 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.routines.Source;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PLSQL Package Body</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackageBody#getPackage <em>Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getPLSQLPackageBody()
 * @model
 * @generated
 */
public interface PLSQLPackageBody extends Source {
	/**
	 * Returns the value of the '<em><b>Package</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackage#getPackageBody <em>Package Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package</em>' container reference.
	 * @see #setPackage(PLSQLPackage)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getPLSQLPackageBody_Package()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackage#getPackageBody
	 * @model opposite="packageBody" required="true"
	 * @generated
	 */
	PLSQLPackage getPackage();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackageBody#getPackage <em>Package</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package</em>' container reference.
	 * @see #getPackage()
	 * @generated
	 */
	void setPackage(PLSQLPackage value);

} // PLSQLPackageBody
