/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseProcedure.java,v 1.3 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;

import org.eclipse.datatools.modelbase.sql.routines.Procedure;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Base Procedure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProcedure#isOnExceptionResume <em>On Exception Resume</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseProcedure()
 * @model
 * @generated
 */
public interface SybaseASABaseProcedure extends Procedure, SybaseRoutine
{
    /**
	 * Returns the value of the '<em><b>On Exception Resume</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Exception Resume</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Exception Resume</em>' attribute.
	 * @see #setOnExceptionResume(boolean)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseProcedure_OnExceptionResume()
	 * @model
	 * @generated
	 */
	boolean isOnExceptionResume();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProcedure#isOnExceptionResume <em>On Exception Resume</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Exception Resume</em>' attribute.
	 * @see #isOnExceptionResume()
	 * @generated
	 */
	void setOnExceptionResume(boolean value);

} // SybaseASABaseProcedure