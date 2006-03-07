/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.derby;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.derby.DerbyModelPackage
 * @generated
 */
public interface DerbyModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DerbyModelFactory eINSTANCE = new org.eclipse.datatools.modelbase.derby.impl.DerbyModelFactoryImpl();

	/**
	 * Returns a new object of class '<em>Synonym</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Synonym</em>'.
	 * @generated
	 */
	Synonym createSynonym();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DerbyModelPackage getDerbyModelPackage();

} //DerbyModelFactory
