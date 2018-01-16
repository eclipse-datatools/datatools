/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseForeignKey.java,v 1.3 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Base Foreign Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey#getRoleName <em>Role Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey#isClustered <em>Clustered</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseForeignKey()
 * @model
 * @generated
 */
public interface SybaseASABaseForeignKey extends ForeignKey
{
    /**
	 * Returns the value of the '<em><b>Role Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Name</em>' attribute.
	 * @see #setRoleName(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseForeignKey_RoleName()
	 * @model
	 * @generated
	 */
	String getRoleName();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey#getRoleName <em>Role Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Name</em>' attribute.
	 * @see #getRoleName()
	 * @generated
	 */
	void setRoleName(String value);

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
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseForeignKey_Clustered()
	 * @model
	 * @generated
	 */
	boolean isClustered();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey#isClustered <em>Clustered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clustered</em>' attribute.
	 * @see #isClustered()
	 * @generated
	 */
	void setClustered(boolean value);

} // SybaseASABaseForeignKey