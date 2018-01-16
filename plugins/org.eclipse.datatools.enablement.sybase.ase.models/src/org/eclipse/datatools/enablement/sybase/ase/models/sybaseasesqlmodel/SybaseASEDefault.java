/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEDefault.java,v 1.6 2007/07/06 08:40:17 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Default</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault#getStatement <em>Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEDefault()
 * @model
 * @generated
 */
public interface SybaseASEDefault extends SQLObject {
	/**
     * Returns the value of the '<em><b>Schema</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema#getDefaults <em>Defaults</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Schema</em>' reference.
     * @see #setSchema(SybaseASESchema)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEDefault_Schema()
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema#getDefaults
     * @model opposite="defaults" required="true"
     * @generated
     */
	SybaseASESchema getSchema();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault#getSchema <em>Schema</em>}' reference.
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
     * SQL scripts of creation statement of this Default
     * <!-- end-model-doc -->
     * @return the value of the '<em>Statement</em>' attribute.
     * @see #setStatement(String)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEDefault_Statement()
     * @model
     * @generated
     */
	String getStatement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault#getStatement <em>Statement</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Statement</em>' attribute.
     * @see #getStatement()
     * @generated
     */
	void setStatement(String value);

} // SybaseASEDefault
