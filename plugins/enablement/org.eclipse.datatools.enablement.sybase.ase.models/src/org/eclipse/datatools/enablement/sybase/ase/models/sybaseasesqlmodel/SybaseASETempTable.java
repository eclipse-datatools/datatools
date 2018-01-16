/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASETempTable.java,v 1.3 2007/02/08 01:41:10 linsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEPartition;

import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Temp Table</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASETempTable()
 * @model annotation="GenModel documentation='We don\'t want the temporary table to extends persistent table, as a result\r\nit can not inherit SybaseASETable. It\'s only used for nonsharable temp table.'"
 * @generated
 */
public interface SybaseASETempTable extends TemporaryTable, SybaseASEBaseTable {
} // SybaseASETempTable
