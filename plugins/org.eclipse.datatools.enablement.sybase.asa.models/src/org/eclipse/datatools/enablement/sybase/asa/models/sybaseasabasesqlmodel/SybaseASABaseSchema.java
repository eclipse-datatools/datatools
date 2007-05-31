/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseSchema.java,v 1.1 2007/03/05 15:52:14 jgraham Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import java.util.List;

import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Base Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseSchema()
 * @model
 * @generated
 */
public interface SybaseASABaseSchema extends Schema {
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
    List getTempTables();

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
    List getProxyTables();

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @model dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false"
	 * @generated
	 */
    List getViewTables(boolean systemFlag);

} // SybaseASABaseSchema
