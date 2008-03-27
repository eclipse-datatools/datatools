/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASADatabase.java,v 1.7 2007/06/05 14:41:04 hcao Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Database</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase#isASECompatible <em>ASE Compatible</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage#getSybaseASADatabase()
 * @model
 * @generated
 */
public interface SybaseASADatabase extends SybaseASABaseDatabase
{
    /**
	 * Returns the value of the '<em><b>ASE Compatible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ASE Compatible</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ASE Compatible</em>' attribute.
	 * @see #setASECompatible(boolean)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage#getSybaseASADatabase_ASECompatible()
	 * @model
	 * @generated
	 */
	boolean isASECompatible();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase#isASECompatible <em>ASE Compatible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ASE Compatible</em>' attribute.
	 * @see #isASECompatible()
	 * @generated
	 */
	void setASECompatible(boolean value);

} // SybaseASADatabase