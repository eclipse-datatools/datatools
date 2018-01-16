/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEPrimaryKey.java,v 1.7 2007/07/06 08:40:16 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Primary Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey#getAseUniqueConstraint <em>Ase Unique Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEPrimaryKey()
 * @model
 * @generated
 */
public interface SybaseASEPrimaryKey extends PrimaryKey {
	/**
     * Returns the value of the '<em><b>Ase Unique Constraint</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ase Unique Constraint</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Ase Unique Constraint</em>' containment reference.
     * @see #setAseUniqueConstraint(SybaseASEUniqueConstraint)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEPrimaryKey_AseUniqueConstraint()
     * @model containment="true" required="true"
     * @generated
     */
	SybaseASEUniqueConstraint getAseUniqueConstraint();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey#getAseUniqueConstraint <em>Ase Unique Constraint</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ase Unique Constraint</em>' containment reference.
     * @see #getAseUniqueConstraint()
     * @generated
     */
	void setAseUniqueConstraint(SybaseASEUniqueConstraint value);

} // SybaseASEPrimaryKey
