/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseUniqueConstraint.java,v 1.3 2007/02/08 01:41:33 linsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Base Unique Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint#isClustered <em>Clustered</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseUniqueConstraint()
 * @model
 * @generated
 */
public interface SybaseASABaseUniqueConstraint extends UniqueConstraint
{
    /**
     * Returns the value of the '<em><b>Clustered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Clustered</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Clustered</em>' attribute.
     * @see #setClustered(boolean)
     * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseUniqueConstraint_Clustered()
     * @model
     * @generated
     */
	boolean isClustered();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint#isClustered <em>Clustered</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Clustered</em>' attribute.
     * @see #isClustered()
     * @generated
     */
	void setClustered(boolean value);

} // SybaseASABaseUniqueConstraint