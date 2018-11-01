/**
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *   Ingres Corporation - initial API and implementation
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ingres.models.ingressqlmodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage
 * @generated
 */
public interface IngressqlmodelFactory extends EFactory {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2008 Ingres Corporation and others.\r\nAll rights reserved. This program and the accompanying materials\r\nare made available under the terms of the Eclipse Public License 2.0\r\nwhich accompanies this distribution, and is available at\r\nhttps://www.eclipse.org/legal/epl-2.0/\r\n\r\nContributors:\r\n  Ingres Corporation - initial API and implementation";

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IngressqlmodelFactory eINSTANCE = org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngressqlmodelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Ingres Synonym</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ingres Synonym</em>'.
	 * @generated
	 */
	IngresSynonym createIngresSynonym();

	/**
	 * Returns a new object of class '<em>Ingres DB Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ingres DB Event</em>'.
	 * @generated
	 */
	IngresDBEvent createIngresDBEvent();

	/**
	 * Returns a new object of class '<em>Ingres Schema</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ingres Schema</em>'.
	 * @generated
	 */
	IngresSchema createIngresSchema();

	/**
	 * Returns a new object of class '<em>Ingres View Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ingres View Table</em>'.
	 * @generated
	 */
	IngresViewTable createIngresViewTable();

	/**
	 * Returns a new object of class '<em>Ingres Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ingres Trigger</em>'.
	 * @generated
	 */
	IngresTrigger createIngresTrigger();

	/**
	 * Returns a new object of class '<em>Ingres Identity Specifier</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ingres Identity Specifier</em>'.
	 * @generated
	 */
	IngresIdentitySpecifier createIngresIdentitySpecifier();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	IngressqlmodelPackage getIngressqlmodelPackage();

} //IngressqlmodelFactory
