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
package org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl;

import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IngressqlmodelFactoryImpl extends EFactoryImpl implements IngressqlmodelFactory {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2008 Ingres Corporation and others.\r\nAll rights reserved. This program and the accompanying materials\r\nare made available under the terms of the Eclipse Public License 2.0\r\nwhich accompanies this distribution, and is available at\r\nhttps://www.eclipse.org/legal/epl-2.0/\r\n\r\nContributors:\r\n  Ingres Corporation - initial API and implementation";

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IngressqlmodelFactory init() {
		try {
			IngressqlmodelFactory theIngressqlmodelFactory = (IngressqlmodelFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/eclipse/datatools/enablement/ingres/ingressqlmodel.ecore"); 
			if (theIngressqlmodelFactory != null) {
				return theIngressqlmodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new IngressqlmodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IngressqlmodelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case IngressqlmodelPackage.INGRES_SYNONYM: return createIngresSynonym();
			case IngressqlmodelPackage.INGRES_DB_EVENT: return createIngresDBEvent();
			case IngressqlmodelPackage.INGRES_SCHEMA: return createIngresSchema();
			case IngressqlmodelPackage.INGRES_VIEW_TABLE: return createIngresViewTable();
			case IngressqlmodelPackage.INGRES_TRIGGER: return createIngresTrigger();
			case IngressqlmodelPackage.INGRES_IDENTITY_SPECIFIER: return createIngresIdentitySpecifier();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IngresSynonym createIngresSynonym() {
		IngresSynonymImpl ingresSynonym = new IngresSynonymImpl();
		return ingresSynonym;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IngresDBEvent createIngresDBEvent() {
		IngresDBEventImpl ingresDBEvent = new IngresDBEventImpl();
		return ingresDBEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IngresSchema createIngresSchema() {
		IngresSchemaImpl ingresSchema = new IngresSchemaImpl();
		return ingresSchema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IngresViewTable createIngresViewTable() {
		IngresViewTableImpl ingresViewTable = new IngresViewTableImpl();
		return ingresViewTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IngresTrigger createIngresTrigger() {
		IngresTriggerImpl ingresTrigger = new IngresTriggerImpl();
		return ingresTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IngresIdentitySpecifier createIngresIdentitySpecifier() {
		IngresIdentitySpecifierImpl ingresIdentitySpecifier = new IngresIdentitySpecifierImpl();
		return ingresIdentitySpecifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IngressqlmodelPackage getIngressqlmodelPackage() {
		return (IngressqlmodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static IngressqlmodelPackage getPackage() {
		return IngressqlmodelPackage.eINSTANCE;
	}

} //IngressqlmodelFactoryImpl
