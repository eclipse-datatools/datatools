/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEProcedure.java,v 1.7 2007/07/06 08:40:17 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;

import org.eclipse.datatools.modelbase.sql.routines.Procedure;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Procedure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#getGroupNumber <em>Group Number</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#getTransactionMode <em>Transaction Mode</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#isSystemProcedure <em>System Procedure</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#isWithRecompile <em>With Recompile</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEProcedure()
 * @model
 * @generated
 */
public interface SybaseASEProcedure extends Procedure, SybaseRoutine {
	/**
     * Returns the value of the '<em><b>Group Number</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Group Number</em>' attribute.
     * @see #setGroupNumber(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEProcedure_GroupNumber()
     * @model default="-1"
     * @generated
     */
	int getGroupNumber();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#getGroupNumber <em>Group Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Group Number</em>' attribute.
     * @see #getGroupNumber()
     * @generated
     */
	void setGroupNumber(int value);

	/**
     * Returns the value of the '<em><b>Transaction Mode</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.TransactionModeType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transaction Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Transaction Mode</em>' attribute.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.TransactionModeType
     * @see #setTransactionMode(TransactionModeType)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEProcedure_TransactionMode()
     * @model
     * @generated
     */
	TransactionModeType getTransactionMode();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#getTransactionMode <em>Transaction Mode</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Transaction Mode</em>' attribute.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.TransactionModeType
     * @see #getTransactionMode()
     * @generated
     */
	void setTransactionMode(TransactionModeType value);

	/**
     * Returns the value of the '<em><b>System Procedure</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Procedure</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>System Procedure</em>' attribute.
     * @see #setSystemProcedure(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEProcedure_SystemProcedure()
     * @model
     * @generated
     */
	boolean isSystemProcedure();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#isSystemProcedure <em>System Procedure</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>System Procedure</em>' attribute.
     * @see #isSystemProcedure()
     * @generated
     */
	void setSystemProcedure(boolean value);

	/**
     * Returns the value of the '<em><b>With Recompile</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>With Recompile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>With Recompile</em>' attribute.
     * @see #setWithRecompile(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEProcedure_WithRecompile()
     * @model
     * @generated
     */
	boolean isWithRecompile();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#isWithRecompile <em>With Recompile</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>With Recompile</em>' attribute.
     * @see #isWithRecompile()
     * @generated
     */
	void setWithRecompile(boolean value);

} // SybaseASEProcedure
