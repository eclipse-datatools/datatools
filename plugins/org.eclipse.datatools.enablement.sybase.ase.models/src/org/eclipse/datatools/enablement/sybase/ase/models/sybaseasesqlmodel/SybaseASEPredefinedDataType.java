/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEPredefinedDataType.java,v 1.6 2007/07/06 08:40:16 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Predefined Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPredefinedDataType#getDatabase <em>Database</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEPredefinedDataType()
 * @model
 * @generated
 */
public interface SybaseASEPredefinedDataType extends PredefinedDataType, SQLDataType, DataType, SQLObject {
	/**
     * Returns the value of the '<em><b>Database</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#getDataTypes <em>Data Types</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Database</em>' reference.
     * @see #setDatabase(SybaseASEDatabase)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEPredefinedDataType_Database()
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#getDataTypes
     * @model opposite="dataTypes"
     * @generated
     */
	SybaseASEDatabase getDatabase();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPredefinedDataType#getDatabase <em>Database</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Database</em>' reference.
     * @see #getDatabase()
     * @generated
     */
	void setDatabase(SybaseASEDatabase value);

} // SybaseASEPredefinedDataType
