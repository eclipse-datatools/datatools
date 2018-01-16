/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASAForeignKey.java,v 1.4 2007/06/05 14:41:04 hcao Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Foreign Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey#isCheckOnCommit <em>Check On Commit</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey#isNullable <em>Nullable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage#getSybaseASAForeignKey()
 * @model
 * @generated
 */
public interface SybaseASAForeignKey extends SybaseASABaseForeignKey
{
    /**
	 * Returns the value of the '<em><b>Check On Commit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Check On Commit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Check On Commit</em>' attribute.
	 * @see #setCheckOnCommit(boolean)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage#getSybaseASAForeignKey_CheckOnCommit()
	 * @model
	 * @generated
	 */
	boolean isCheckOnCommit();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey#isCheckOnCommit <em>Check On Commit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Check On Commit</em>' attribute.
	 * @see #isCheckOnCommit()
	 * @generated
	 */
	void setCheckOnCommit(boolean value);

    /**
	 * Returns the value of the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nullable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nullable</em>' attribute.
	 * @see #setNullable(boolean)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage#getSybaseASAForeignKey_Nullable()
	 * @model
	 * @generated
	 */
	boolean isNullable();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey#isNullable <em>Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nullable</em>' attribute.
	 * @see #isNullable()
	 * @generated
	 */
	void setNullable(boolean value);

} // SybaseASAForeignKey