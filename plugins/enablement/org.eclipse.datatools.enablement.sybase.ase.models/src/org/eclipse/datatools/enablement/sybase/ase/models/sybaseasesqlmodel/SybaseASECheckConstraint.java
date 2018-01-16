/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASECheckConstraint.java,v 1.2 2007/07/06 08:40:17 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Check Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECheckConstraint#getCreator <em>Creator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECheckConstraint()
 * @model
 * @generated
 */
public interface SybaseASECheckConstraint extends CheckConstraint, TableConstraint, Constraint, SQLObject {
	/**
     * Returns the value of the '<em><b>Creator</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creator</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Creator</em>' reference.
     * @see #setCreator(Schema)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECheckConstraint_Creator()
     * @model required="true"
     * @generated
     */
	Schema getCreator();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECheckConstraint#getCreator <em>Creator</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Creator</em>' reference.
     * @see #getCreator()
     * @generated
     */
	void setCreator(Schema value);

} // SybaseASECheckConstraint
