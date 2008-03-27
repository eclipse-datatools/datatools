/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEColumn.java,v 1.10 2007/07/06 08:40:18 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import java.util.List;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;

import org.eclipse.datatools.modelbase.sql.tables.Column;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * ASE column's defaultValue attribute will hold either inline default 
 * or bound default value. Judge boundDefault is null or not to determine
 * the defaultValue's holder(inline or bound defualt). -- To be consistent
 * with the standard SQL model
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getColumnCheck <em>Column Check</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getBoundDefault <em>Bound Default</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getBoundRule <em>Bound Rule</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isMaterialized <em>Materialized</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getEncryptionKey <em>Encryption Key</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isBindDefaultInFutureOnly <em>Bind Default In Future Only</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isBindRuleInFutureOnly <em>Bind Rule In Future Only</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isHidden <em>Hidden</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEColumn()
 * @model
 * @generated
 */
public interface SybaseASEColumn extends Column, SybaseAuthorizedObject {
	/**
     * Returns the value of the '<em><b>Column Check</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint#getColumn <em>Column</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Check</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Column Check</em>' reference.
     * @see #setColumnCheck(SybaseASEColumnCheckConstraint)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEColumn_ColumnCheck()
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint#getColumn
     * @model opposite="column"
     * @generated
     */
	SybaseASEColumnCheckConstraint getColumnCheck();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getColumnCheck <em>Column Check</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column Check</em>' reference.
     * @see #getColumnCheck()
     * @generated
     */
	void setColumnCheck(SybaseASEColumnCheckConstraint value);

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
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEColumn_BoundDefault()
     * @model
     * @generated
     */
	SybaseASEDefault getBoundDefault();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getBoundDefault <em>Bound Default</em>}' reference.
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
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEColumn_BoundRule()
     * @model
     * @generated
     */
	SybaseASERule getBoundRule();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getBoundRule <em>Bound Rule</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bound Rule</em>' reference.
     * @see #getBoundRule()
     * @generated
     */
	void setBoundRule(SybaseASERule value);

	/**
     * Returns the value of the '<em><b>Materialized</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Materialized</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Materialized</em>' attribute.
     * @see #setMaterialized(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEColumn_Materialized()
     * @model
     * @generated
     */
	boolean isMaterialized();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isMaterialized <em>Materialized</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Materialized</em>' attribute.
     * @see #isMaterialized()
     * @generated
     */
	void setMaterialized(boolean value);

	/**
     * Returns the value of the '<em><b>Encryption Key</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Encryption Key</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Encryption Key</em>' reference.
     * @see #setEncryptionKey(SybaseASEEncryptionKey)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEColumn_EncryptionKey()
     * @model
     * @generated
     */
	SybaseASEEncryptionKey getEncryptionKey();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getEncryptionKey <em>Encryption Key</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Encryption Key</em>' reference.
     * @see #getEncryptionKey()
     * @generated
     */
	void setEncryptionKey(SybaseASEEncryptionKey value);

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
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEColumn_BindDefaultInFutureOnly()
     * @model
     * @generated
     */
	boolean isBindDefaultInFutureOnly();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isBindDefaultInFutureOnly <em>Bind Default In Future Only</em>}' attribute.
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
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEColumn_BindRuleInFutureOnly()
     * @model
     * @generated
     */
	boolean isBindRuleInFutureOnly();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isBindRuleInFutureOnly <em>Bind Rule In Future Only</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bind Rule In Future Only</em>' attribute.
     * @see #isBindRuleInFutureOnly()
     * @generated
     */
	void setBindRuleInFutureOnly(boolean value);

	/**
     * Returns the value of the '<em><b>Hidden</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Hidden</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Hidden</em>' attribute.
     * @see #setHidden(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEColumn_Hidden()
     * @model default="false"
     * @generated
     */
    boolean isHidden();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isHidden <em>Hidden</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Hidden</em>' attribute.
     * @see #isHidden()
     * @generated
     */
    void setHidden(boolean value);

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
	boolean isComputedColumn();

} // SybaseASEColumn
