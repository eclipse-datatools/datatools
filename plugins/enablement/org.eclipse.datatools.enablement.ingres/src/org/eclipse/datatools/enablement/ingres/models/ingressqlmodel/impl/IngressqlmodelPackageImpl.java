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

import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSchema;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresTrigger;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresViewTable;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelFactory;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;

import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;

import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;

import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;

import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;

import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsPackage;

import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IngressqlmodelPackageImpl extends EPackageImpl implements IngressqlmodelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2008 Ingres Corporation and others.\r\nAll rights reserved. This program and the accompanying materials\r\nare made available under the terms of the Eclipse Public License 2.0\r\nwhich accompanies this distribution, and is available at\r\nhttps://www.eclipse.org/legal/epl-2.0/\r\n\r\nContributors:\r\n  Ingres Corporation - initial API and implementation";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ingresSynonymEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ingresDBEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ingresSchemaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ingresViewTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ingresTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ingresIdentitySpecifierEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private IngressqlmodelPackageImpl() {
		super(eNS_URI, IngressqlmodelFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static IngressqlmodelPackage init() {
		if (isInited) return (IngressqlmodelPackage)EPackage.Registry.INSTANCE.getEPackage(IngressqlmodelPackage.eNS_URI);

		// Obtain or create and register package
		IngressqlmodelPackageImpl theIngressqlmodelPackage = (IngressqlmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof IngressqlmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new IngressqlmodelPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();
		SQLSchemaPackage.eINSTANCE.eClass();
		SQLConstraintsPackage.eINSTANCE.eClass();
		SQLDataTypesPackage.eINSTANCE.eClass();
		SQLExpressionsPackage.eINSTANCE.eClass();
		SQLRoutinesPackage.eINSTANCE.eClass();
		SQLStatementsPackage.eINSTANCE.eClass();
		SQLTablesPackage.eINSTANCE.eClass();
		SQLAccessControlPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theIngressqlmodelPackage.createPackageContents();

		// Initialize created meta-data
		theIngressqlmodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theIngressqlmodelPackage.freeze();

		return theIngressqlmodelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIngresSynonym() {
		return ingresSynonymEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIngresSynonym_Schema() {
		return (EReference)ingresSynonymEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIngresSynonym_TableName() {
		return (EAttribute)ingresSynonymEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIngresDBEvent() {
		return ingresDBEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIngresDBEvent_Schema() {
		return (EReference)ingresDBEventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIngresSchema() {
		return ingresSchemaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIngresSchema_DBEvents() {
		return (EReference)ingresSchemaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIngresSchema_Synonyms() {
		return (EReference)ingresSchemaEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIngresViewTable() {
		return ingresViewTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIngresViewTable_Source() {
		return (EReference)ingresViewTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIngresTrigger() {
		return ingresTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIngresTrigger_Source() {
		return (EReference)ingresTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIngresIdentitySpecifier() {
		return ingresIdentitySpecifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIngresIdentitySpecifier_DataType() {
		return (EAttribute)ingresIdentitySpecifierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIngresIdentitySpecifier_SeqLength() {
		return (EAttribute)ingresIdentitySpecifierEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIngresIdentitySpecifier_SeqPrecision() {
		return (EAttribute)ingresIdentitySpecifierEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIngresIdentitySpecifier_MaximumOption() {
		return (EAttribute)ingresIdentitySpecifierEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIngresIdentitySpecifier_MinimumOption() {
		return (EAttribute)ingresIdentitySpecifierEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIngresIdentitySpecifier_CacheSize() {
		return (EAttribute)ingresIdentitySpecifierEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIngresIdentitySpecifier_CacheOption() {
		return (EAttribute)ingresIdentitySpecifierEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIngresIdentitySpecifier_OrderOption() {
		return (EAttribute)ingresIdentitySpecifierEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IngressqlmodelFactory getIngressqlmodelFactory() {
		return (IngressqlmodelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		ingresSynonymEClass = createEClass(INGRES_SYNONYM);
		createEReference(ingresSynonymEClass, INGRES_SYNONYM__SCHEMA);
		createEAttribute(ingresSynonymEClass, INGRES_SYNONYM__TABLE_NAME);

		ingresDBEventEClass = createEClass(INGRES_DB_EVENT);
		createEReference(ingresDBEventEClass, INGRES_DB_EVENT__SCHEMA);

		ingresSchemaEClass = createEClass(INGRES_SCHEMA);
		createEReference(ingresSchemaEClass, INGRES_SCHEMA__DB_EVENTS);
		createEReference(ingresSchemaEClass, INGRES_SCHEMA__SYNONYMS);

		ingresViewTableEClass = createEClass(INGRES_VIEW_TABLE);
		createEReference(ingresViewTableEClass, INGRES_VIEW_TABLE__SOURCE);

		ingresTriggerEClass = createEClass(INGRES_TRIGGER);
		createEReference(ingresTriggerEClass, INGRES_TRIGGER__SOURCE);

		ingresIdentitySpecifierEClass = createEClass(INGRES_IDENTITY_SPECIFIER);
		createEAttribute(ingresIdentitySpecifierEClass, INGRES_IDENTITY_SPECIFIER__DATA_TYPE);
		createEAttribute(ingresIdentitySpecifierEClass, INGRES_IDENTITY_SPECIFIER__SEQ_LENGTH);
		createEAttribute(ingresIdentitySpecifierEClass, INGRES_IDENTITY_SPECIFIER__SEQ_PRECISION);
		createEAttribute(ingresIdentitySpecifierEClass, INGRES_IDENTITY_SPECIFIER__MAXIMUM_OPTION);
		createEAttribute(ingresIdentitySpecifierEClass, INGRES_IDENTITY_SPECIFIER__MINIMUM_OPTION);
		createEAttribute(ingresIdentitySpecifierEClass, INGRES_IDENTITY_SPECIFIER__CACHE_SIZE);
		createEAttribute(ingresIdentitySpecifierEClass, INGRES_IDENTITY_SPECIFIER__CACHE_OPTION);
		createEAttribute(ingresIdentitySpecifierEClass, INGRES_IDENTITY_SPECIFIER__ORDER_OPTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		SQLSchemaPackage theSQLSchemaPackage = (SQLSchemaPackage)EPackage.Registry.INSTANCE.getEPackage(SQLSchemaPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		SQLTablesPackage theSQLTablesPackage = (SQLTablesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLTablesPackage.eNS_URI);
		SQLRoutinesPackage theSQLRoutinesPackage = (SQLRoutinesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLRoutinesPackage.eNS_URI);

		// Add supertypes to classes
		ingresSynonymEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		ingresDBEventEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		ingresSchemaEClass.getESuperTypes().add(theSQLSchemaPackage.getSchema());
		ingresViewTableEClass.getESuperTypes().add(theSQLTablesPackage.getViewTable());
		ingresTriggerEClass.getESuperTypes().add(theSQLTablesPackage.getTrigger());
		ingresIdentitySpecifierEClass.getESuperTypes().add(theSQLSchemaPackage.getIdentitySpecifier());

		// Initialize classes and features; add operations and parameters
		initEClass(ingresSynonymEClass, IngresSynonym.class, "IngresSynonym", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIngresSynonym_Schema(), this.getIngresSchema(), this.getIngresSchema_Synonyms(), "schema", null, 1, 1, IngresSynonym.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIngresSynonym_TableName(), theEcorePackage.getEString(), "tableName", null, 0, 1, IngresSynonym.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ingresDBEventEClass, IngresDBEvent.class, "IngresDBEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIngresDBEvent_Schema(), this.getIngresSchema(), this.getIngresSchema_DBEvents(), "schema", null, 1, 1, IngresDBEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ingresSchemaEClass, IngresSchema.class, "IngresSchema", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIngresSchema_DBEvents(), this.getIngresDBEvent(), this.getIngresDBEvent_Schema(), "dBEvents", null, 0, -1, IngresSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIngresSchema_Synonyms(), this.getIngresSynonym(), this.getIngresSynonym_Schema(), "synonyms", null, 0, -1, IngresSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ingresViewTableEClass, IngresViewTable.class, "IngresViewTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIngresViewTable_Source(), theSQLRoutinesPackage.getSource(), null, "source", null, 0, 1, IngresViewTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ingresTriggerEClass, IngresTrigger.class, "IngresTrigger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIngresTrigger_Source(), theSQLRoutinesPackage.getSource(), null, "source", null, 0, 1, IngresTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ingresIdentitySpecifierEClass, IngresIdentitySpecifier.class, "IngresIdentitySpecifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIngresIdentitySpecifier_DataType(), theEcorePackage.getEString(), "dataType", null, 0, 1, IngresIdentitySpecifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIngresIdentitySpecifier_SeqLength(), theEcorePackage.getEBigInteger(), "seqLength", null, 0, 1, IngresIdentitySpecifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIngresIdentitySpecifier_SeqPrecision(), theEcorePackage.getEBigInteger(), "seqPrecision", null, 0, 1, IngresIdentitySpecifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIngresIdentitySpecifier_MaximumOption(), theEcorePackage.getEBooleanObject(), "maximumOption", null, 0, 1, IngresIdentitySpecifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIngresIdentitySpecifier_MinimumOption(), theEcorePackage.getEBooleanObject(), "minimumOption", null, 0, 1, IngresIdentitySpecifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIngresIdentitySpecifier_CacheSize(), theEcorePackage.getEBigInteger(), "cacheSize", null, 0, 1, IngresIdentitySpecifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIngresIdentitySpecifier_CacheOption(), theEcorePackage.getEBooleanObject(), "cacheOption", null, 0, 1, IngresIdentitySpecifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIngresIdentitySpecifier_OrderOption(), theEcorePackage.getEBooleanObject(), "orderOption", null, 0, 1, IngresIdentitySpecifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //IngressqlmodelPackageImpl
