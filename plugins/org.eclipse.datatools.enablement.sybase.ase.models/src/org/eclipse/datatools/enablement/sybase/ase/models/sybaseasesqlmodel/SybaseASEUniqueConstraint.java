/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEUniqueConstraint.java,v 1.7 2007/07/06 08:40:16 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Unique Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint#getSystemGenedIndex <em>System Gened Index</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint#isSystemGenedName <em>System Gened Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEUniqueConstraint()
 * @model
 * @generated
 */
public interface SybaseASEUniqueConstraint extends UniqueConstraint {
	/**
     * Returns the value of the '<em><b>System Gened Index</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * SybaseASEUniqueConstraint wrap SybaseASEIndex as reference to provide isClustered, segment, marrowsize, members and other index related info.
     * <!-- end-model-doc -->
     * @return the value of the '<em>System Gened Index</em>' containment reference.
     * @see #setSystemGenedIndex(SybaseASEIndex)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEUniqueConstraint_SystemGenedIndex()
     * @model containment="true" required="true"
     * @generated
     */
	SybaseASEIndex getSystemGenedIndex();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint#getSystemGenedIndex <em>System Gened Index</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>System Gened Index</em>' containment reference.
     * @see #getSystemGenedIndex()
     * @generated
     */
	void setSystemGenedIndex(SybaseASEIndex value);

	/**
     * Returns the value of the '<em><b>System Gened Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Gened Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>System Gened Name</em>' attribute.
     * @see #setSystemGenedName(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEUniqueConstraint_SystemGenedName()
     * @model
     * @generated
     */
	boolean isSystemGenedName();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint#isSystemGenedName <em>System Gened Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>System Gened Name</em>' attribute.
     * @see #isSystemGenedName()
     * @generated
     */
	void setSystemGenedName(boolean value);

} // SybaseASEUniqueConstraint
