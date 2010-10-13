/**
 * <copyright>
 * </copyright>
 *
 * $Id: PartitionPackageImpl.java,v 1.1 2008/03/27 07:41:12 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionFactory;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEPartition;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERangePartition;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERoundrobinPartition;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;

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
public class PartitionPackageImpl extends EPackageImpl implements PartitionPackage 
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseASEPartitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseASERangePartitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseASEHashPartitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseASEListPartitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseASERoundrobinPartitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass partitionSegmentPairEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass partitionNumInSegmentsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listRangePartitionItemEClass = null;

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
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PartitionPackageImpl() {
		super(eNS_URI, PartitionFactory.eINSTANCE);
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
	public static PartitionPackage init() {
		if (isInited) return (PartitionPackage)EPackage.Registry.INSTANCE.getEPackage(PartitionPackage.eNS_URI);

		// Obtain or create and register package
		PartitionPackageImpl thePartitionPackage = (PartitionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof PartitionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new PartitionPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		SybasesqlmodelPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		SybaseasesqlmodelPackageImpl theSybaseasesqlmodelPackage = (SybaseasesqlmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SybaseasesqlmodelPackage.eNS_URI) instanceof SybaseasesqlmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SybaseasesqlmodelPackage.eNS_URI) : SybaseasesqlmodelPackage.eINSTANCE);

		// Create package meta-data objects
		thePartitionPackage.createPackageContents();
		theSybaseasesqlmodelPackage.createPackageContents();

		// Initialize created meta-data
		thePartitionPackage.initializePackageContents();
		theSybaseasesqlmodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePartitionPackage.freeze();

		return thePartitionPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseASEPartition() {
		return sybaseASEPartitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseASERangePartition() {
		return sybaseASERangePartitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSybaseASERangePartition_Columns() {
		return (EReference)sybaseASERangePartitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSybaseASERangePartition_RangePartitionItems() {
		return (EReference)sybaseASERangePartitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseASEHashPartition() {
		return sybaseASEHashPartitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSybaseASEHashPartition_Columns() {
		return (EReference)sybaseASEHashPartitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSybaseASEHashPartition_PartitionSegmentPairs() {
		return (EReference)sybaseASEHashPartitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSybaseASEHashPartition_PartitionNumInSegments() {
		return (EReference)sybaseASEHashPartitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseASEListPartition() {
		return sybaseASEListPartitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSybaseASEListPartition_Column() {
		return (EReference)sybaseASEListPartitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSybaseASEListPartition_ListPartitionItems() {
		return (EReference)sybaseASEListPartitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseASERoundrobinPartition() {
		return sybaseASERoundrobinPartitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSybaseASERoundrobinPartition_PartitionSegmentPairs() {
		return (EReference)sybaseASERoundrobinPartitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSybaseASERoundrobinPartition_PartitionNumInSegments() {
		return (EReference)sybaseASERoundrobinPartitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPartitionSegmentPair() {
		return partitionSegmentPairEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPartitionSegmentPair_PartitionName() {
		return (EAttribute)partitionSegmentPairEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPartitionSegmentPair_Segment() {
		return (EReference)partitionSegmentPairEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPartitionNumInSegments() {
		return partitionNumInSegmentsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPartitionNumInSegments_PartitionNumb() {
		return (EAttribute)partitionNumInSegmentsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPartitionNumInSegments_Segment() {
		return (EReference)partitionNumInSegmentsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getListRangePartitionItem() {
		return listRangePartitionItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getListRangePartitionItem_PartitionName() {
		return (EAttribute)listRangePartitionItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getListRangePartitionItem_Values() {
		return (EAttribute)listRangePartitionItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListRangePartitionItem_Segment() {
		return (EReference)listRangePartitionItemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartitionFactory getPartitionFactory() {
		return (PartitionFactory)getEFactoryInstance();
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
		sybaseASEPartitionEClass = createEClass(SYBASE_ASE_PARTITION);

		sybaseASERangePartitionEClass = createEClass(SYBASE_ASE_RANGE_PARTITION);
		createEReference(sybaseASERangePartitionEClass, SYBASE_ASE_RANGE_PARTITION__COLUMNS);
		createEReference(sybaseASERangePartitionEClass, SYBASE_ASE_RANGE_PARTITION__RANGE_PARTITION_ITEMS);

		sybaseASEHashPartitionEClass = createEClass(SYBASE_ASE_HASH_PARTITION);
		createEReference(sybaseASEHashPartitionEClass, SYBASE_ASE_HASH_PARTITION__COLUMNS);
		createEReference(sybaseASEHashPartitionEClass, SYBASE_ASE_HASH_PARTITION__PARTITION_SEGMENT_PAIRS);
		createEReference(sybaseASEHashPartitionEClass, SYBASE_ASE_HASH_PARTITION__PARTITION_NUM_IN_SEGMENTS);

		sybaseASEListPartitionEClass = createEClass(SYBASE_ASE_LIST_PARTITION);
		createEReference(sybaseASEListPartitionEClass, SYBASE_ASE_LIST_PARTITION__COLUMN);
		createEReference(sybaseASEListPartitionEClass, SYBASE_ASE_LIST_PARTITION__LIST_PARTITION_ITEMS);

		sybaseASERoundrobinPartitionEClass = createEClass(SYBASE_ASE_ROUNDROBIN_PARTITION);
		createEReference(sybaseASERoundrobinPartitionEClass, SYBASE_ASE_ROUNDROBIN_PARTITION__PARTITION_SEGMENT_PAIRS);
		createEReference(sybaseASERoundrobinPartitionEClass, SYBASE_ASE_ROUNDROBIN_PARTITION__PARTITION_NUM_IN_SEGMENTS);

		partitionSegmentPairEClass = createEClass(PARTITION_SEGMENT_PAIR);
		createEAttribute(partitionSegmentPairEClass, PARTITION_SEGMENT_PAIR__PARTITION_NAME);
		createEReference(partitionSegmentPairEClass, PARTITION_SEGMENT_PAIR__SEGMENT);

		partitionNumInSegmentsEClass = createEClass(PARTITION_NUM_IN_SEGMENTS);
		createEAttribute(partitionNumInSegmentsEClass, PARTITION_NUM_IN_SEGMENTS__PARTITION_NUMB);
		createEReference(partitionNumInSegmentsEClass, PARTITION_NUM_IN_SEGMENTS__SEGMENT);

		listRangePartitionItemEClass = createEClass(LIST_RANGE_PARTITION_ITEM);
		createEAttribute(listRangePartitionItemEClass, LIST_RANGE_PARTITION_ITEM__PARTITION_NAME);
		createEAttribute(listRangePartitionItemEClass, LIST_RANGE_PARTITION_ITEM__VALUES);
		createEReference(listRangePartitionItemEClass, LIST_RANGE_PARTITION_ITEM__SEGMENT);
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
		SQLTablesPackage theSQLTablesPackage = (SQLTablesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLTablesPackage.eNS_URI);
		SybaseasesqlmodelPackage theSybaseasesqlmodelPackage = (SybaseasesqlmodelPackage)EPackage.Registry.INSTANCE.getEPackage(SybaseasesqlmodelPackage.eNS_URI);

		// Add supertypes to classes
		sybaseASEPartitionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASERangePartitionEClass.getESuperTypes().add(this.getSybaseASEPartition());
		sybaseASERangePartitionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASEHashPartitionEClass.getESuperTypes().add(this.getSybaseASEPartition());
		sybaseASEHashPartitionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASEListPartitionEClass.getESuperTypes().add(this.getSybaseASEPartition());
		sybaseASEListPartitionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASERoundrobinPartitionEClass.getESuperTypes().add(this.getSybaseASEPartition());
		sybaseASERoundrobinPartitionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		partitionSegmentPairEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		partitionNumInSegmentsEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		listRangePartitionItemEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());

		// Initialize classes and features; add operations and parameters
		initEClass(sybaseASEPartitionEClass, SybaseASEPartition.class, "SybaseASEPartition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(sybaseASERangePartitionEClass, SybaseASERangePartition.class, "SybaseASERangePartition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASERangePartition_Columns(), theSQLTablesPackage.getColumn(), null, "columns", null, 1, -1, SybaseASERangePartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASERangePartition_RangePartitionItems(), this.getListRangePartitionItem(), null, "rangePartitionItems", null, 0, -1, SybaseASERangePartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASEHashPartitionEClass, SybaseASEHashPartition.class, "SybaseASEHashPartition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASEHashPartition_Columns(), theSQLTablesPackage.getColumn(), null, "columns", null, 1, -1, SybaseASEHashPartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEHashPartition_PartitionSegmentPairs(), this.getPartitionSegmentPair(), null, "partitionSegmentPairs", null, 0, -1, SybaseASEHashPartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEHashPartition_PartitionNumInSegments(), this.getPartitionNumInSegments(), null, "partitionNumInSegments", null, 0, 1, SybaseASEHashPartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASEListPartitionEClass, SybaseASEListPartition.class, "SybaseASEListPartition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASEListPartition_Column(), theSybaseasesqlmodelPackage.getSybaseASEColumn(), null, "column", null, 1, 1, SybaseASEListPartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEListPartition_ListPartitionItems(), this.getListRangePartitionItem(), null, "listPartitionItems", null, 1, -1, SybaseASEListPartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASERoundrobinPartitionEClass, SybaseASERoundrobinPartition.class, "SybaseASERoundrobinPartition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASERoundrobinPartition_PartitionSegmentPairs(), this.getPartitionSegmentPair(), null, "partitionSegmentPairs", null, 0, -1, SybaseASERoundrobinPartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASERoundrobinPartition_PartitionNumInSegments(), this.getPartitionNumInSegments(), null, "partitionNumInSegments", null, 0, 1, SybaseASERoundrobinPartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(partitionSegmentPairEClass, PartitionSegmentPair.class, "PartitionSegmentPair", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getPartitionSegmentPair_PartitionName(), ecorePackage.getEString(), "partitionName", null, 0, 1, PartitionSegmentPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getPartitionSegmentPair_Segment(), theSybaseasesqlmodelPackage.getSybaseASESegment(), null, "segment", null, 1, 1, PartitionSegmentPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(partitionNumInSegmentsEClass, PartitionNumInSegments.class, "PartitionNumInSegments", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getPartitionNumInSegments_PartitionNumb(), ecorePackage.getEInt(), "partitionNumb", null, 0, 1, PartitionNumInSegments.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getPartitionNumInSegments_Segment(), theSybaseasesqlmodelPackage.getSybaseASESegment(), null, "segment", null, 1, -1, PartitionNumInSegments.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(listRangePartitionItemEClass, ListRangePartitionItem.class, "ListRangePartitionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getListRangePartitionItem_PartitionName(), ecorePackage.getEString(), "partitionName", null, 0, 1, ListRangePartitionItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getListRangePartitionItem_Values(), ecorePackage.getEString(), "values", "", 1, -1, ListRangePartitionItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getListRangePartitionItem_Segment(), theSybaseasesqlmodelPackage.getSybaseASESegment(), null, "segment", null, 0, 1, ListRangePartitionItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	}

} //PartitionPackageImpl
