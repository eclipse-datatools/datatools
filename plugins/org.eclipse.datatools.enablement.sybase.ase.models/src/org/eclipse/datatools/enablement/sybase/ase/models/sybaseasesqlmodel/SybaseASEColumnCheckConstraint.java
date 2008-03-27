/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEColumnCheckConstraint.java,v 1.8 2007/07/06 08:40:16 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Column Check Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint#getColumn <em>Column</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEColumnCheckConstraint()
 * @model
 * @generated
 */
public interface SybaseASEColumnCheckConstraint extends SybaseASECheckConstraint {
	/**
     * Returns the value of the '<em><b>Column</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getColumnCheck <em>Column Check</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Column</em>' reference.
     * @see #setColumn(SybaseASEColumn)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEColumnCheckConstraint_Column()
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getColumnCheck
     * @model opposite="columnCheck" required="true"
     * @generated
     */
	SybaseASEColumn getColumn();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint#getColumn <em>Column</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column</em>' reference.
     * @see #getColumn()
     * @generated
     */
	void setColumn(SybaseASEColumn value);

} // SybaseASEColumnCheckConstraint
