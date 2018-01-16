/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEViewTable.java,v 1.6 2007/07/06 08:40:18 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseViewTable;

import org.eclipse.datatools.modelbase.sql.tables.ViewTable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE View Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEViewTable#isWithCheckOption <em>With Check Option</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEViewTable()
 * @model
 * @generated
 */
public interface SybaseASEViewTable extends SybaseViewTable {
	/**
     * Returns the value of the '<em><b>With Check Option</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>With Check Option</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>With Check Option</em>' attribute.
     * @see #setWithCheckOption(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEViewTable_WithCheckOption()
     * @model
     * @generated
     */
	boolean isWithCheckOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEViewTable#isWithCheckOption <em>With Check Option</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>With Check Option</em>' attribute.
     * @see #isWithCheckOption()
     * @generated
     */
	void setWithCheckOption(boolean value);

} // SybaseASEViewTable
