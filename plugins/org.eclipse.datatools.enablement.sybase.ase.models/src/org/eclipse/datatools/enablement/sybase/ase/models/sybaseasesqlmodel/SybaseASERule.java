/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASERule.java,v 1.7 2007/08/06 08:29:24 renj Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#isAccessRule <em>Access Rule</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#getAccessType <em>Access Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASERule()
 * @model
 * @generated
 */
public interface SybaseASERule extends SQLObject {
	/**
     * Returns the value of the '<em><b>Schema</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema#getRules <em>Rules</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Schema</em>' reference.
     * @see #setSchema(SybaseASESchema)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASERule_Schema()
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema#getRules
     * @model opposite="rules" required="true"
     * @generated
     */
	SybaseASESchema getSchema();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#getSchema <em>Schema</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Schema</em>' reference.
     * @see #getSchema()
     * @generated
     */
	void setSchema(SybaseASESchema value);

	/**
     * Returns the value of the '<em><b>Statement</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * SQL scripts of creation statement of this Rule
     * <!-- end-model-doc -->
     * @return the value of the '<em>Statement</em>' attribute.
     * @see #setStatement(String)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASERule_Statement()
     * @model
     * @generated
     */
	String getStatement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#getStatement <em>Statement</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Statement</em>' attribute.
     * @see #getStatement()
     * @generated
     */
	void setStatement(String value);

	/**
     * Returns the value of the '<em><b>Access Rule</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access Rule</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Access Rule</em>' attribute.
     * @see #setAccessRule(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASERule_AccessRule()
     * @model
     * @generated
     */
	boolean isAccessRule();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#isAccessRule <em>Access Rule</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Access Rule</em>' attribute.
     * @see #isAccessRule()
     * @generated
     */
	void setAccessRule(boolean value);

	/**
     * Returns the value of the '<em><b>Access Type</b></em>' attribute.
     * The default value is <code>"DEF"</code>.
     * The literals are from the enumeration {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.AccessRuleType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Access Type</em>' attribute.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.AccessRuleType
     * @see #setAccessType(AccessRuleType)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASERule_AccessType()
     * @model default="DEF"
     * @generated
     */
	AccessRuleType getAccessType();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#getAccessType <em>Access Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Access Type</em>' attribute.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.AccessRuleType
     * @see #getAccessType()
     * @generated
     */
	void setAccessType(AccessRuleType value);

} // SybaseASERule
