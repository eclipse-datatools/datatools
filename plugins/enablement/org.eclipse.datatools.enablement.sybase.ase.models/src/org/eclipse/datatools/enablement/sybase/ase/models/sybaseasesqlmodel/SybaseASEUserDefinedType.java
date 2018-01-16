/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEUserDefinedType.java,v 1.7 2007/07/06 08:40:17 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE User Defined Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#getBoundDefault <em>Bound Default</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#getBoundRule <em>Bound Rule</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isBindDefaultInFutureOnly <em>Bind Default In Future Only</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isBindRuleInFutureOnly <em>Bind Rule In Future Only</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isAllowNulls <em>Allow Nulls</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isIdentity <em>Identity</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEUserDefinedType()
 * @model
 * @generated
 */
public interface SybaseASEUserDefinedType extends DistinctUserDefinedType {
	/**
     * Returns the value of the '<em><b>Bound Default</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bound Default</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bound Default</em>' reference.
     * @see #setBoundDefault(SybaseASEDefault)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEUserDefinedType_BoundDefault()
     * @model
     * @generated
     */
    SybaseASEDefault getBoundDefault();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#getBoundDefault <em>Bound Default</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bound Default</em>' reference.
     * @see #getBoundDefault()
     * @generated
     */
    void setBoundDefault(SybaseASEDefault value);

	/**
     * Returns the value of the '<em><b>Bound Rule</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bound Rule</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bound Rule</em>' reference.
     * @see #setBoundRule(SybaseASERule)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEUserDefinedType_BoundRule()
     * @model
     * @generated
     */
    SybaseASERule getBoundRule();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#getBoundRule <em>Bound Rule</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bound Rule</em>' reference.
     * @see #getBoundRule()
     * @generated
     */
    void setBoundRule(SybaseASERule value);

	/**
     * Returns the value of the '<em><b>Bind Default In Future Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bind Default In Future Only</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Bind Default In Future Only</em>' attribute.
     * @see #setBindDefaultInFutureOnly(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEUserDefinedType_BindDefaultInFutureOnly()
     * @model
     * @generated
     */
	boolean isBindDefaultInFutureOnly();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isBindDefaultInFutureOnly <em>Bind Default In Future Only</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bind Default In Future Only</em>' attribute.
     * @see #isBindDefaultInFutureOnly()
     * @generated
     */
	void setBindDefaultInFutureOnly(boolean value);

	/**
     * Returns the value of the '<em><b>Bind Rule In Future Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bind Rule In Future Only</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Bind Rule In Future Only</em>' attribute.
     * @see #setBindRuleInFutureOnly(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEUserDefinedType_BindRuleInFutureOnly()
     * @model
     * @generated
     */
	boolean isBindRuleInFutureOnly();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isBindRuleInFutureOnly <em>Bind Rule In Future Only</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bind Rule In Future Only</em>' attribute.
     * @see #isBindRuleInFutureOnly()
     * @generated
     */
	void setBindRuleInFutureOnly(boolean value);

	/**
     * Returns the value of the '<em><b>Allow Nulls</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allow Nulls</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Allow Nulls</em>' attribute.
     * @see #setAllowNulls(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEUserDefinedType_AllowNulls()
     * @model
     * @generated
     */
	boolean isAllowNulls();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isAllowNulls <em>Allow Nulls</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Allow Nulls</em>' attribute.
     * @see #isAllowNulls()
     * @generated
     */
	void setAllowNulls(boolean value);

	/**
     * Returns the value of the '<em><b>Identity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Identity</em>' attribute.
     * @see #setIdentity(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEUserDefinedType_Identity()
     * @model
     * @generated
     */
	boolean isIdentity();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isIdentity <em>Identity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Identity</em>' attribute.
     * @see #isIdentity()
     * @generated
     */
	void setIdentity(boolean value);

} // SybaseASEUserDefinedType
