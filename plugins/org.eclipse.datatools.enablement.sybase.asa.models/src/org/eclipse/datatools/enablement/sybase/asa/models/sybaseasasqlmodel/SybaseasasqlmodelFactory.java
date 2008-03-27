/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasasqlmodelFactory.java,v 1.8 2007/06/05 14:41:04 hcao Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage
 * @generated
 */
public interface SybaseasasqlmodelFactory extends EFactory
{
    /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SybaseasasqlmodelFactory eINSTANCE = org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseasasqlmodelFactoryImpl.init();

    /**
	 * Returns a new object of class '<em>Sybase ASA Database</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sybase ASA Database</em>'.
	 * @generated
	 */
	SybaseASADatabase createSybaseASADatabase();

    /**
	 * Returns a new object of class '<em>Sybase ASA Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sybase ASA Table</em>'.
	 * @generated
	 */
	SybaseASATable createSybaseASATable();

    /**
	 * Returns a new object of class '<em>Sybase ASA Foreign Key</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sybase ASA Foreign Key</em>'.
	 * @generated
	 */
	SybaseASAForeignKey createSybaseASAForeignKey();

    /**
	 * Returns a new object of class '<em>Sybase ASA Index</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sybase ASA Index</em>'.
	 * @generated
	 */
	SybaseASAIndex createSybaseASAIndex();

    /**
	 * Returns a new object of class '<em>Sybase ASA Temp Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sybase ASA Temp Table</em>'.
	 * @generated
	 */
	SybaseASATempTable createSybaseASATempTable();

    /**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SybaseasasqlmodelPackage getSybaseasasqlmodelPackage();

} //SybaseasasqlmodelFactory
