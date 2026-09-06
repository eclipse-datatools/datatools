/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseUniqueConstraint.java,v 1.3 2008/03/27 07:35:07 lsong Exp $
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
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint#getSystemGenIndex <em>System Gen Index</em>}</li>
 * </ul>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseUniqueConstraint()
 * @model
 * @generated
 */
public interface SybaseASABaseUniqueConstraint extends UniqueConstraint
{
    /**
	 * Returns the value of the '<em><b>System Gen Index</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>System Gen Index</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>System Gen Index</em>' reference.
	 * @see #setSystemGenIndex(SybaseASABaseIndex)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseUniqueConstraint_SystemGenIndex()
	 * @model
	 * @generated
	 */
    SybaseASABaseIndex getSystemGenIndex();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint#getSystemGenIndex <em>System Gen Index</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Gen Index</em>' reference.
	 * @see #getSystemGenIndex()
	 * @generated
	 */
    void setSystemGenIndex(SybaseASABaseIndex value);

} // SybaseASABaseUniqueConstraint