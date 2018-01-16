/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASESchema.java,v 1.9 2007/07/06 08:40:17 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import java.util.List;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema#getDefaults <em>Defaults</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema#getRules <em>Rules</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema#getEncryptionKeys <em>Encryption Keys</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASESchema()
 * @model
 * @generated
 */
public interface SybaseASESchema extends Schema, SQLObject {
	/**
     * Returns the value of the '<em><b>Defaults</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault#getSchema <em>Schema</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Defaults</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Defaults</em>' reference list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASESchema_Defaults()
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault#getSchema
     * @model type="org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault" opposite="schema"
     * @generated
     */
	EList getDefaults();

	/**
     * Returns the value of the '<em><b>Rules</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#getSchema <em>Schema</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rules</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Rules</em>' reference list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASESchema_Rules()
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#getSchema
     * @model type="org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule" opposite="schema"
     * @generated
     */
	EList getRules();

	/**
     * Returns the value of the '<em><b>Encryption Keys</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey#getSchema <em>Schema</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Encryption Keys</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Encryption Keys</em>' reference list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASESchema_EncryptionKeys()
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey#getSchema
     * @model type="org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey" opposite="schema"
     * @generated
     */
	EList getEncryptionKeys();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation" dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false"
     * @generated
     */
	List getWebServicesAsTables();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation" dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false"
     * @generated
     */
	List getNormalTables();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation" dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false"
     * @generated
     */
	List getProxyTables();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation" dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false"
     * @generated
     */
	List getViewTables();

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation" dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false"
     * @generated
     */
    List getSystemProcedures();

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation" dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false"
     * @generated
     */
    List getSystemTables();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation" dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false"
     * @generated
     */
    List getSystemAndNormalTable();

} // SybaseASESchema} // SybaseASESchema
