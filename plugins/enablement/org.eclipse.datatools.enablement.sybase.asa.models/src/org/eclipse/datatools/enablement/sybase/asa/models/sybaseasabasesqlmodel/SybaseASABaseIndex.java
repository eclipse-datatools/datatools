/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseIndex.java,v 1.3 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import org.eclipse.datatools.modelbase.sql.constraints.Index;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Base Index</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex#getDbSpace <em>Db Space</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseIndex()
 * @model
 * @generated
 */
public interface SybaseASABaseIndex extends Index
{
    /**
	 * Returns the value of the '<em><b>Db Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Db Space</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Db Space</em>' reference.
	 * @see #setDbSpace(SybaseASABaseDBSpace)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseIndex_DbSpace()
	 * @model required="true"
	 * @generated
	 */
	SybaseASABaseDBSpace getDbSpace();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex#getDbSpace <em>Db Space</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Db Space</em>' reference.
	 * @see #getDbSpace()
	 * @generated
	 */
	void setDbSpace(SybaseASABaseDBSpace value);

} // SybaseASABaseIndex