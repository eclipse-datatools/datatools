/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEPartitionImpl.java,v 1.6 2007/07/06 08:40:15 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEPartition;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Partition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class SybaseASEPartitionImpl extends SQLObjectImpl implements SybaseASEPartition 
{
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEPartitionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return PartitionPackage.Literals.SYBASE_ASE_PARTITION;
    }

} //SybaseASEPartitionImpl
