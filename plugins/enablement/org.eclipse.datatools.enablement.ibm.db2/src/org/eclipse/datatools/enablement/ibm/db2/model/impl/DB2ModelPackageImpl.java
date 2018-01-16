package org.eclipse.datatools.enablement.ibm.db2.model.impl;


import org.eclipse.datatools.enablement.ibm.db2.model.DB2AccessPlan;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Alias;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Cluster;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Column;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Database;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2DistinctUserDefinedType;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Function;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Index;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Member;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Method;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelFactory;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2MultidimensionalIndex;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2OLAPObject;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Period;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2PeriodType;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ProcedureDeploy;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2RoutineExtension;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Source;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2SystemSchema;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Table;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2TableOrganization;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Transaction;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2UniqueConstraintExtension;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2UserDefinedFunction;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2View;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDecomposition;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaStatus;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XSRObject;
import org.eclipse.datatools.enablement.ibm.db2.model.DataCaptureType;
import org.eclipse.datatools.enablement.ibm.db2.model.GenerateType;
import org.eclipse.datatools.enablement.ibm.db2.model.IsolationLevelType;
import org.eclipse.datatools.enablement.ibm.db2.model.OriginType;
import org.eclipse.datatools.enablement.ibm.db2.model.ReoptType;
import org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect;
import org.eclipse.datatools.enablement.ibm.db2.model.UnitType;
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
import org.eclipse.emf.ecore.EEnum;
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
public class DB2ModelPackageImpl extends EPackageImpl implements DB2ModelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2DatabaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2PackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2TableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2TriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2ProcedureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2SchemaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2RoutineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2DatabaseManagerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2ViewEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2ApplicationProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2TransactionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2SystemSchemaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2SourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2AccessPlanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2UserDefinedFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2MethodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2ExtendedOptionsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2AliasEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2MaterializedQueryTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2IndexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2MultidimensionalIndexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2FunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2JavaOptionsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2ProcedureDeployEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2OLAPObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2RoutineExtensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2IdentitySpecifierEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2JarEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2ColumnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2XSRObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2XMLSchemaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2XMLSchemaDocumentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2XMLSchemaDocPropertiesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2PackageStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2DistinctUserDefinedTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2PeriodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2ClusterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2MemberEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2UniqueConstraintExtensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2MaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass db2PermissionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum isolationLevelTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum db2IndexTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dataCaptureTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum unitTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum generateTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum db2XMLSchemaDecompositionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum db2XMLSchemaStatusEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum originTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum reoptTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum sourceDialectEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum db2PeriodTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum db2TableOrganizationEEnum = null;

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
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DB2ModelPackageImpl() {
		super(eNS_URI, DB2ModelFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link DB2ModelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DB2ModelPackage init() {
		if (isInited) return (DB2ModelPackage)EPackage.Registry.INSTANCE.getEPackage(DB2ModelPackage.eNS_URI);

		// Obtain or create and register package
		DB2ModelPackageImpl theDB2ModelPackage = (DB2ModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DB2ModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DB2ModelPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		SQLSchemaPackage.eINSTANCE.eClass();
		SQLConstraintsPackage.eINSTANCE.eClass();
		SQLDataTypesPackage.eINSTANCE.eClass();
		SQLExpressionsPackage.eINSTANCE.eClass();
		SQLRoutinesPackage.eINSTANCE.eClass();
		SQLStatementsPackage.eINSTANCE.eClass();
		SQLTablesPackage.eINSTANCE.eClass();
		SQLAccessControlPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theDB2ModelPackage.createPackageContents();

		// Initialize created meta-data
		theDB2ModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDB2ModelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DB2ModelPackage.eNS_URI, theDB2ModelPackage);
		return theDB2ModelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Database() {
		return db2DatabaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Database_Partitioned() {
		return (EAttribute)db2DatabaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Database_DefaultOrganizeByRow() {
		return (EAttribute)db2DatabaseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Package() {
		return db2PackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Package_Operative() {
		return (EAttribute)db2PackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Package_Valid() {
		return (EAttribute)db2PackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Package_Version() {
		return (EAttribute)db2PackageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Package_DefaultSchema() {
		return (EAttribute)db2PackageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Package_SqlPath() {
		return (EAttribute)db2PackageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Package_ReoptVar() {
		return (EAttribute)db2PackageEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Package_Isolation() {
		return (EAttribute)db2PackageEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Package_UniqueID() {
		return (EAttribute)db2PackageEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Package_LastBindTS() {
		return (EAttribute)db2PackageEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Package_Schema() {
		return (EReference)db2PackageEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Package_Statements() {
		return (EReference)db2PackageEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Table() {
		return db2TableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Table_DataCapture() {
		return (EAttribute)db2TableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Table_ActivateRowAccessControl() {
		return (EAttribute)db2TableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Table_ActivateColumnAccessControl() {
		return (EAttribute)db2TableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Table_OrganizeBy() {
		return (EAttribute)db2TableEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Table_Packages() {
		return (EReference)db2TableEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Table_Periods() {
		return (EReference)db2TableEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Table_HistoryTable() {
		return (EReference)db2TableEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Table_TemporalTable() {
		return (EReference)db2TableEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Table_Masks() {
		return (EReference)db2TableEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Table_Permissions() {
		return (EReference)db2TableEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Trigger() {
		return db2TriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Trigger_Operative() {
		return (EAttribute)db2TriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Trigger_Secured() {
		return (EAttribute)db2TriggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Procedure() {
		return db2ProcedureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Procedure_ModelResultSets() {
		return (EAttribute)db2ProcedureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Procedure_NullInput() {
		return (EAttribute)db2ProcedureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Procedure_Version() {
		return (EAttribute)db2ProcedureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Procedure_Dialect() {
		return (EAttribute)db2ProcedureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Procedure_ExternalAction() {
		return (EAttribute)db2ProcedureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Procedure_Return() {
		return (EReference)db2ProcedureEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Procedure_JavaOptions() {
		return (EReference)db2ProcedureEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Procedure_Deploy() {
		return (EReference)db2ProcedureEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Schema() {
		return db2SchemaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Schema_AccessPlans() {
		return (EReference)db2SchemaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Schema_OlapObjects() {
		return (EReference)db2SchemaEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Schema_Jars() {
		return (EReference)db2SchemaEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Schema_XsrObjects() {
		return (EReference)db2SchemaEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Schema_Packages() {
		return (EReference)db2SchemaEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Schema_Masks() {
		return (EReference)db2SchemaEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Schema_Permissions() {
		return (EReference)db2SchemaEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Schema_Modules() {
		return (EReference)db2SchemaEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Schema_GlobalVariables() {
		return (EReference)db2SchemaEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Routine() {
		return db2RoutineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Routine_Fenced() {
		return (EAttribute)db2RoutineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Routine_Threadsafe() {
		return (EAttribute)db2RoutineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Routine_DbInfo() {
		return (EAttribute)db2RoutineEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Routine_ImplicitSchema() {
		return (EAttribute)db2RoutineEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Routine_Federated() {
		return (EAttribute)db2RoutineEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Routine_ParmCcsid() {
		return (EAttribute)db2RoutineEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Routine_SpecialRegister() {
		return (EAttribute)db2RoutineEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Routine_ChangeState() {
		return (EAttribute)db2RoutineEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Routine_DebugId() {
		return (EAttribute)db2RoutineEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Routine_ProgramType() {
		return (EAttribute)db2RoutineEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Routine_OrigSchemaName() {
		return (EAttribute)db2RoutineEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Routine_OrigParmSig() {
		return (EAttribute)db2RoutineEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Routine_ExtendedOptions() {
		return (EReference)db2RoutineEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Routine_RoutineExtensions() {
		return (EReference)db2RoutineEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2DatabaseManager() {
		return db2DatabaseManagerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2DatabaseManager_Databases() {
		return (EReference)db2DatabaseManagerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2DatabaseManager_Db2Process() {
		return (EReference)db2DatabaseManagerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2DatabaseManager_Server() {
		return (EReference)db2DatabaseManagerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2DatabaseManager_Cluster() {
		return (EReference)db2DatabaseManagerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2View() {
		return db2ViewEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2View_Operative() {
		return (EAttribute)db2ViewEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2ApplicationProcess() {
		return db2ApplicationProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2ApplicationProcess_IsolationLevel() {
		return (EAttribute)db2ApplicationProcessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2ApplicationProcess_UnitOfWork() {
		return (EReference)db2ApplicationProcessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Transaction() {
		return db2TransactionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2SystemSchema() {
		return db2SystemSchemaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Source() {
		return db2SourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Source_FileName() {
		return (EAttribute)db2SourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Source_PackageName() {
		return (EAttribute)db2SourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Source_Db2PackageName() {
		return (EAttribute)db2SourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Source_LastModified() {
		return (EAttribute)db2SourceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Source_Supporting() {
		return (EReference)db2SourceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Source_Primary() {
		return (EReference)db2SourceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2AccessPlan() {
		return db2AccessPlanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2UserDefinedFunction() {
		return db2UserDefinedFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Method() {
		return db2MethodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Method_ReturnsSelfAsResult() {
		return (EAttribute)db2MethodEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Method_Implemented() {
		return (EAttribute)db2MethodEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2ExtendedOptions() {
		return db2ExtendedOptionsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2ExtendedOptions_ClasspathCompileJars() {
		return (EAttribute)db2ExtendedOptionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2ExtendedOptions_PreCompileOpts() {
		return (EAttribute)db2ExtendedOptionsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2ExtendedOptions_ForDebug() {
		return (EAttribute)db2ExtendedOptionsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2ExtendedOptions_Built() {
		return (EAttribute)db2ExtendedOptionsEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2ExtendedOptions_CompileOpts() {
		return (EAttribute)db2ExtendedOptionsEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2ExtendedOptions_LinkOpts() {
		return (EAttribute)db2ExtendedOptionsEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2ExtendedOptions_BindOpts() {
		return (EAttribute)db2ExtendedOptionsEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2ExtendedOptions_Colid() {
		return (EAttribute)db2ExtendedOptionsEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Alias() {
		return db2AliasEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Alias_AliasedTable() {
		return (EReference)db2AliasEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2MaterializedQueryTable() {
		return db2MaterializedQueryTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2MaterializedQueryTable_Refresh() {
		return (EAttribute)db2MaterializedQueryTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2MaterializedQueryTable_OptimizeQuery() {
		return (EAttribute)db2MaterializedQueryTableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2MaterializedQueryTable_MaintainedBy() {
		return (EAttribute)db2MaterializedQueryTableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2MaterializedQueryTable_ActivateRowAccessControl() {
		return (EAttribute)db2MaterializedQueryTableEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2MaterializedQueryTable_ActivateColumnAccessControl() {
		return (EAttribute)db2MaterializedQueryTableEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2MaterializedQueryTable_Masks() {
		return (EReference)db2MaterializedQueryTableEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2MaterializedQueryTable_Permissions() {
		return (EReference)db2MaterializedQueryTableEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Index() {
		return db2IndexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Index_IndexType() {
		return (EAttribute)db2IndexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Index_BusPeriodWithoutOverlap() {
		return (EAttribute)db2IndexEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Index_EncodedVector() {
		return (EAttribute)db2IndexEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Index_DB2MultidimensionalIndex() {
		return (EReference)db2IndexEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2MultidimensionalIndex() {
		return db2MultidimensionalIndexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2MultidimensionalIndex_DimensionIndexes() {
		return (EReference)db2MultidimensionalIndexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Function() {
		return db2FunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_FinalCall() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_ScratchPad() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_ScratchPadLength() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_FunctionType() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_Predicate() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_ExternalAction() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_Cardinality() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_AllowParallel() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_ReturnClause() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_Origin() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_InheritLockRequest() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_Dialect() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_Inline() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_Version() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Function_Secured() {
		return (EAttribute)db2FunctionEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2JavaOptions() {
		return db2JavaOptionsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2JavaOptions_ClassName() {
		return (EAttribute)db2JavaOptionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2JavaOptions_MethodName() {
		return (EAttribute)db2JavaOptionsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2JavaOptions_Sqlj() {
		return (EAttribute)db2JavaOptionsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2JavaOptions_Procedure() {
		return (EReference)db2JavaOptionsEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2JavaOptions_Jar() {
		return (EReference)db2JavaOptionsEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2ProcedureDeploy() {
		return db2ProcedureDeployEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2ProcedureDeploy_FileName() {
		return (EAttribute)db2ProcedureDeployEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2OLAPObject() {
		return db2OLAPObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2OLAPObject_Schema() {
		return (EReference)db2OLAPObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2RoutineExtension() {
		return db2RoutineExtensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2IdentitySpecifier() {
		return db2IdentitySpecifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2IdentitySpecifier_Cache() {
		return (EAttribute)db2IdentitySpecifierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2IdentitySpecifier_Order() {
		return (EAttribute)db2IdentitySpecifierEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2IdentitySpecifier_SystemGenerated() {
		return (EAttribute)db2IdentitySpecifierEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2IdentitySpecifier_RestartValue() {
		return (EAttribute)db2IdentitySpecifierEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Jar() {
		return db2JarEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Jar_FileName() {
		return (EAttribute)db2JarEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Jar_Path() {
		return (EAttribute)db2JarEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Jar_Owner() {
		return (EAttribute)db2JarEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Jar_CreatedTS() {
		return (EAttribute)db2JarEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Jar_AlteredTS() {
		return (EAttribute)db2JarEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Jar_Procedures() {
		return (EReference)db2JarEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Jar_Schema() {
		return (EReference)db2JarEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Column() {
		return db2ColumnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Column_GenerationType() {
		return (EAttribute)db2ColumnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Column_RowChangeTimestamp() {
		return (EAttribute)db2ColumnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Column_RowBegin() {
		return (EAttribute)db2ColumnEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Column_RowEnd() {
		return (EAttribute)db2ColumnEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Column_TransStartID() {
		return (EAttribute)db2ColumnEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Column_BeginPeriod() {
		return (EReference)db2ColumnEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Column_EndPeriod() {
		return (EReference)db2ColumnEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2XSRObject() {
		return db2XSRObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2XSRObject_Schema() {
		return (EReference)db2XSRObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2XMLSchema() {
		return db2XMLSchemaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2XMLSchema_Decomposition() {
		return (EAttribute)db2XMLSchemaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2XMLSchema_Status() {
		return (EAttribute)db2XMLSchemaEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2XMLSchema_XmlSchemaDocs() {
		return (EReference)db2XMLSchemaEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2XMLSchemaDocument() {
		return db2XMLSchemaDocumentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2XMLSchemaDocument_FileName() {
		return (EAttribute)db2XMLSchemaDocumentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2XMLSchemaDocument_SchemaLocation() {
		return (EAttribute)db2XMLSchemaDocumentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2XMLSchemaDocument_TargetNamespace() {
		return (EAttribute)db2XMLSchemaDocumentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2XMLSchemaDocument_Primary() {
		return (EAttribute)db2XMLSchemaDocumentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2XMLSchemaDocument_XmlSchema() {
		return (EReference)db2XMLSchemaDocumentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2XMLSchemaDocument_XmlSchemaDocProperties() {
		return (EReference)db2XMLSchemaDocumentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2XMLSchemaDocProperties() {
		return db2XMLSchemaDocPropertiesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2XMLSchemaDocProperties_Value() {
		return (EAttribute)db2XMLSchemaDocPropertiesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2XMLSchemaDocProperties_XmlSchemaDoc() {
		return (EReference)db2XMLSchemaDocPropertiesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2PackageStatement() {
		return db2PackageStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2PackageStatement_StatementNumber() {
		return (EAttribute)db2PackageStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2PackageStatement_SectionNumber() {
		return (EAttribute)db2PackageStatementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2PackageStatement_Package() {
		return (EReference)db2PackageStatementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2PackageStatement_SqlStatement() {
		return (EReference)db2PackageStatementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2DistinctUserDefinedType() {
		return db2DistinctUserDefinedTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Period() {
		return db2PeriodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Period_Type() {
		return (EAttribute)db2PeriodEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Period_BeginColumn() {
		return (EReference)db2PeriodEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Period_EndColumn() {
		return (EReference)db2PeriodEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Period_Table() {
		return (EReference)db2PeriodEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Cluster() {
		return db2ClusterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Cluster_Level() {
		return (EAttribute)db2ClusterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Cluster_Instance() {
		return (EReference)db2ClusterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Cluster_Members() {
		return (EReference)db2ClusterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Member() {
		return db2MemberEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Member_Id() {
		return (EAttribute)db2MemberEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Member_HomeHost() {
		return (EAttribute)db2MemberEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Member_CurrentHost() {
		return (EAttribute)db2MemberEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Member_State() {
		return (EAttribute)db2MemberEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Member_Cluster() {
		return (EReference)db2MemberEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2UniqueConstraintExtension() {
		return db2UniqueConstraintExtensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2UniqueConstraintExtension_BusPeriodWithoutOverlap() {
		return (EAttribute)db2UniqueConstraintExtensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Mask() {
		return db2MaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Mask_CorrelationName() {
		return (EAttribute)db2MaskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Mask_CaseExpression() {
		return (EReference)db2MaskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Mask_Enable() {
		return (EAttribute)db2MaskEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Mask_Schema() {
		return (EReference)db2MaskEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Mask_SubjectTable() {
		return (EReference)db2MaskEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Mask_SubjectMQT() {
		return (EReference)db2MaskEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Mask_SubjectColumn() {
		return (EReference)db2MaskEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDB2Permission() {
		return db2PermissionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Permission_CorrelationName() {
		return (EAttribute)db2PermissionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Permission_SearchCondition() {
		return (EReference)db2PermissionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDB2Permission_Enable() {
		return (EAttribute)db2PermissionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Permission_Schema() {
		return (EReference)db2PermissionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Permission_SubjectTable() {
		return (EReference)db2PermissionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDB2Permission_SubjectMQT() {
		return (EReference)db2PermissionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getIsolationLevelType() {
		return isolationLevelTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDB2IndexType() {
		return db2IndexTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDataCaptureType() {
		return dataCaptureTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getUnitType() {
		return unitTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getGenerateType() {
		return generateTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDB2XMLSchemaDecomposition() {
		return db2XMLSchemaDecompositionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDB2XMLSchemaStatus() {
		return db2XMLSchemaStatusEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getOriginType() {
		return originTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getReoptType() {
		return reoptTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSourceDialect() {
		return sourceDialectEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDB2PeriodType() {
		return db2PeriodTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDB2TableOrganization() {
		return db2TableOrganizationEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2ModelFactory getDB2ModelFactory() {
		return (DB2ModelFactory)getEFactoryInstance();
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
		db2DatabaseEClass = createEClass(DB2_DATABASE);
		createEAttribute(db2DatabaseEClass, DB2_DATABASE__PARTITIONED);
		createEAttribute(db2DatabaseEClass, DB2_DATABASE__DEFAULT_ORGANIZE_BY_ROW);

		db2PackageEClass = createEClass(DB2_PACKAGE);
		createEAttribute(db2PackageEClass, DB2_PACKAGE__OPERATIVE);
		createEAttribute(db2PackageEClass, DB2_PACKAGE__VALID);
		createEAttribute(db2PackageEClass, DB2_PACKAGE__VERSION);
		createEAttribute(db2PackageEClass, DB2_PACKAGE__DEFAULT_SCHEMA);
		createEAttribute(db2PackageEClass, DB2_PACKAGE__SQL_PATH);
		createEAttribute(db2PackageEClass, DB2_PACKAGE__REOPT_VAR);
		createEAttribute(db2PackageEClass, DB2_PACKAGE__ISOLATION);
		createEAttribute(db2PackageEClass, DB2_PACKAGE__UNIQUE_ID);
		createEAttribute(db2PackageEClass, DB2_PACKAGE__LAST_BIND_TS);
		createEReference(db2PackageEClass, DB2_PACKAGE__SCHEMA);
		createEReference(db2PackageEClass, DB2_PACKAGE__STATEMENTS);

		db2TableEClass = createEClass(DB2_TABLE);
		createEAttribute(db2TableEClass, DB2_TABLE__DATA_CAPTURE);
		createEAttribute(db2TableEClass, DB2_TABLE__ACTIVATE_ROW_ACCESS_CONTROL);
		createEAttribute(db2TableEClass, DB2_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL);
		createEAttribute(db2TableEClass, DB2_TABLE__ORGANIZE_BY);
		createEReference(db2TableEClass, DB2_TABLE__PACKAGES);
		createEReference(db2TableEClass, DB2_TABLE__PERIODS);
		createEReference(db2TableEClass, DB2_TABLE__HISTORY_TABLE);
		createEReference(db2TableEClass, DB2_TABLE__TEMPORAL_TABLE);
		createEReference(db2TableEClass, DB2_TABLE__MASKS);
		createEReference(db2TableEClass, DB2_TABLE__PERMISSIONS);

		db2TriggerEClass = createEClass(DB2_TRIGGER);
		createEAttribute(db2TriggerEClass, DB2_TRIGGER__OPERATIVE);
		createEAttribute(db2TriggerEClass, DB2_TRIGGER__SECURED);

		db2ProcedureEClass = createEClass(DB2_PROCEDURE);
		createEAttribute(db2ProcedureEClass, DB2_PROCEDURE__MODEL_RESULT_SETS);
		createEAttribute(db2ProcedureEClass, DB2_PROCEDURE__NULL_INPUT);
		createEAttribute(db2ProcedureEClass, DB2_PROCEDURE__VERSION);
		createEAttribute(db2ProcedureEClass, DB2_PROCEDURE__DIALECT);
		createEAttribute(db2ProcedureEClass, DB2_PROCEDURE__EXTERNAL_ACTION);
		createEReference(db2ProcedureEClass, DB2_PROCEDURE__RETURN);
		createEReference(db2ProcedureEClass, DB2_PROCEDURE__JAVA_OPTIONS);
		createEReference(db2ProcedureEClass, DB2_PROCEDURE__DEPLOY);

		db2SchemaEClass = createEClass(DB2_SCHEMA);
		createEReference(db2SchemaEClass, DB2_SCHEMA__ACCESS_PLANS);
		createEReference(db2SchemaEClass, DB2_SCHEMA__OLAP_OBJECTS);
		createEReference(db2SchemaEClass, DB2_SCHEMA__JARS);
		createEReference(db2SchemaEClass, DB2_SCHEMA__XSR_OBJECTS);
		createEReference(db2SchemaEClass, DB2_SCHEMA__PACKAGES);
		createEReference(db2SchemaEClass, DB2_SCHEMA__MASKS);
		createEReference(db2SchemaEClass, DB2_SCHEMA__PERMISSIONS);
		createEReference(db2SchemaEClass, DB2_SCHEMA__MODULES);
		createEReference(db2SchemaEClass, DB2_SCHEMA__GLOBAL_VARIABLES);

		db2RoutineEClass = createEClass(DB2_ROUTINE);
		createEAttribute(db2RoutineEClass, DB2_ROUTINE__FENCED);
		createEAttribute(db2RoutineEClass, DB2_ROUTINE__THREADSAFE);
		createEAttribute(db2RoutineEClass, DB2_ROUTINE__DB_INFO);
		createEAttribute(db2RoutineEClass, DB2_ROUTINE__IMPLICIT_SCHEMA);
		createEAttribute(db2RoutineEClass, DB2_ROUTINE__FEDERATED);
		createEAttribute(db2RoutineEClass, DB2_ROUTINE__PARM_CCSID);
		createEAttribute(db2RoutineEClass, DB2_ROUTINE__SPECIAL_REGISTER);
		createEAttribute(db2RoutineEClass, DB2_ROUTINE__CHANGE_STATE);
		createEAttribute(db2RoutineEClass, DB2_ROUTINE__DEBUG_ID);
		createEAttribute(db2RoutineEClass, DB2_ROUTINE__PROGRAM_TYPE);
		createEAttribute(db2RoutineEClass, DB2_ROUTINE__ORIG_SCHEMA_NAME);
		createEAttribute(db2RoutineEClass, DB2_ROUTINE__ORIG_PARM_SIG);
		createEReference(db2RoutineEClass, DB2_ROUTINE__EXTENDED_OPTIONS);
		createEReference(db2RoutineEClass, DB2_ROUTINE__ROUTINE_EXTENSIONS);

		db2DatabaseManagerEClass = createEClass(DB2_DATABASE_MANAGER);
		createEReference(db2DatabaseManagerEClass, DB2_DATABASE_MANAGER__DATABASES);
		createEReference(db2DatabaseManagerEClass, DB2_DATABASE_MANAGER__DB2_PROCESS);
		createEReference(db2DatabaseManagerEClass, DB2_DATABASE_MANAGER__SERVER);
		createEReference(db2DatabaseManagerEClass, DB2_DATABASE_MANAGER__CLUSTER);

		db2ViewEClass = createEClass(DB2_VIEW);
		createEAttribute(db2ViewEClass, DB2_VIEW__OPERATIVE);

		db2ApplicationProcessEClass = createEClass(DB2_APPLICATION_PROCESS);
		createEAttribute(db2ApplicationProcessEClass, DB2_APPLICATION_PROCESS__ISOLATION_LEVEL);
		createEReference(db2ApplicationProcessEClass, DB2_APPLICATION_PROCESS__UNIT_OF_WORK);

		db2TransactionEClass = createEClass(DB2_TRANSACTION);

		db2SystemSchemaEClass = createEClass(DB2_SYSTEM_SCHEMA);

		db2SourceEClass = createEClass(DB2_SOURCE);
		createEAttribute(db2SourceEClass, DB2_SOURCE__FILE_NAME);
		createEAttribute(db2SourceEClass, DB2_SOURCE__PACKAGE_NAME);
		createEAttribute(db2SourceEClass, DB2_SOURCE__DB2_PACKAGE_NAME);
		createEAttribute(db2SourceEClass, DB2_SOURCE__LAST_MODIFIED);
		createEReference(db2SourceEClass, DB2_SOURCE__SUPPORTING);
		createEReference(db2SourceEClass, DB2_SOURCE__PRIMARY);

		db2AccessPlanEClass = createEClass(DB2_ACCESS_PLAN);

		db2UserDefinedFunctionEClass = createEClass(DB2_USER_DEFINED_FUNCTION);

		db2MethodEClass = createEClass(DB2_METHOD);
		createEAttribute(db2MethodEClass, DB2_METHOD__RETURNS_SELF_AS_RESULT);
		createEAttribute(db2MethodEClass, DB2_METHOD__IMPLEMENTED);

		db2ExtendedOptionsEClass = createEClass(DB2_EXTENDED_OPTIONS);
		createEAttribute(db2ExtendedOptionsEClass, DB2_EXTENDED_OPTIONS__CLASSPATH_COMPILE_JARS);
		createEAttribute(db2ExtendedOptionsEClass, DB2_EXTENDED_OPTIONS__PRE_COMPILE_OPTS);
		createEAttribute(db2ExtendedOptionsEClass, DB2_EXTENDED_OPTIONS__FOR_DEBUG);
		createEAttribute(db2ExtendedOptionsEClass, DB2_EXTENDED_OPTIONS__BUILT);
		createEAttribute(db2ExtendedOptionsEClass, DB2_EXTENDED_OPTIONS__COMPILE_OPTS);
		createEAttribute(db2ExtendedOptionsEClass, DB2_EXTENDED_OPTIONS__LINK_OPTS);
		createEAttribute(db2ExtendedOptionsEClass, DB2_EXTENDED_OPTIONS__BIND_OPTS);
		createEAttribute(db2ExtendedOptionsEClass, DB2_EXTENDED_OPTIONS__COLID);

		db2AliasEClass = createEClass(DB2_ALIAS);
		createEReference(db2AliasEClass, DB2_ALIAS__ALIASED_TABLE);

		db2MaterializedQueryTableEClass = createEClass(DB2_MATERIALIZED_QUERY_TABLE);
		createEAttribute(db2MaterializedQueryTableEClass, DB2_MATERIALIZED_QUERY_TABLE__REFRESH);
		createEAttribute(db2MaterializedQueryTableEClass, DB2_MATERIALIZED_QUERY_TABLE__OPTIMIZE_QUERY);
		createEAttribute(db2MaterializedQueryTableEClass, DB2_MATERIALIZED_QUERY_TABLE__MAINTAINED_BY);
		createEAttribute(db2MaterializedQueryTableEClass, DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_ROW_ACCESS_CONTROL);
		createEAttribute(db2MaterializedQueryTableEClass, DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL);
		createEReference(db2MaterializedQueryTableEClass, DB2_MATERIALIZED_QUERY_TABLE__MASKS);
		createEReference(db2MaterializedQueryTableEClass, DB2_MATERIALIZED_QUERY_TABLE__PERMISSIONS);

		db2IndexEClass = createEClass(DB2_INDEX);
		createEAttribute(db2IndexEClass, DB2_INDEX__INDEX_TYPE);
		createEAttribute(db2IndexEClass, DB2_INDEX__BUS_PERIOD_WITHOUT_OVERLAP);
		createEAttribute(db2IndexEClass, DB2_INDEX__ENCODED_VECTOR);
		createEReference(db2IndexEClass, DB2_INDEX__DB2_MULTIDIMENSIONAL_INDEX);

		db2MultidimensionalIndexEClass = createEClass(DB2_MULTIDIMENSIONAL_INDEX);
		createEReference(db2MultidimensionalIndexEClass, DB2_MULTIDIMENSIONAL_INDEX__DIMENSION_INDEXES);

		db2FunctionEClass = createEClass(DB2_FUNCTION);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__FINAL_CALL);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__SCRATCH_PAD);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__SCRATCH_PAD_LENGTH);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__FUNCTION_TYPE);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__PREDICATE);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__EXTERNAL_ACTION);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__CARDINALITY);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__ALLOW_PARALLEL);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__RETURN_CLAUSE);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__ORIGIN);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__INHERIT_LOCK_REQUEST);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__DIALECT);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__INLINE);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__VERSION);
		createEAttribute(db2FunctionEClass, DB2_FUNCTION__SECURED);

		db2JavaOptionsEClass = createEClass(DB2_JAVA_OPTIONS);
		createEAttribute(db2JavaOptionsEClass, DB2_JAVA_OPTIONS__CLASS_NAME);
		createEAttribute(db2JavaOptionsEClass, DB2_JAVA_OPTIONS__METHOD_NAME);
		createEAttribute(db2JavaOptionsEClass, DB2_JAVA_OPTIONS__SQLJ);
		createEReference(db2JavaOptionsEClass, DB2_JAVA_OPTIONS__PROCEDURE);
		createEReference(db2JavaOptionsEClass, DB2_JAVA_OPTIONS__JAR);

		db2ProcedureDeployEClass = createEClass(DB2_PROCEDURE_DEPLOY);
		createEAttribute(db2ProcedureDeployEClass, DB2_PROCEDURE_DEPLOY__FILE_NAME);

		db2OLAPObjectEClass = createEClass(DB2OLAP_OBJECT);
		createEReference(db2OLAPObjectEClass, DB2OLAP_OBJECT__SCHEMA);

		db2RoutineExtensionEClass = createEClass(DB2_ROUTINE_EXTENSION);

		db2IdentitySpecifierEClass = createEClass(DB2_IDENTITY_SPECIFIER);
		createEAttribute(db2IdentitySpecifierEClass, DB2_IDENTITY_SPECIFIER__CACHE);
		createEAttribute(db2IdentitySpecifierEClass, DB2_IDENTITY_SPECIFIER__ORDER);
		createEAttribute(db2IdentitySpecifierEClass, DB2_IDENTITY_SPECIFIER__SYSTEM_GENERATED);
		createEAttribute(db2IdentitySpecifierEClass, DB2_IDENTITY_SPECIFIER__RESTART_VALUE);

		db2JarEClass = createEClass(DB2_JAR);
		createEAttribute(db2JarEClass, DB2_JAR__FILE_NAME);
		createEAttribute(db2JarEClass, DB2_JAR__PATH);
		createEAttribute(db2JarEClass, DB2_JAR__OWNER);
		createEAttribute(db2JarEClass, DB2_JAR__CREATED_TS);
		createEAttribute(db2JarEClass, DB2_JAR__ALTERED_TS);
		createEReference(db2JarEClass, DB2_JAR__PROCEDURES);
		createEReference(db2JarEClass, DB2_JAR__SCHEMA);

		db2ColumnEClass = createEClass(DB2_COLUMN);
		createEAttribute(db2ColumnEClass, DB2_COLUMN__GENERATION_TYPE);
		createEAttribute(db2ColumnEClass, DB2_COLUMN__ROW_CHANGE_TIMESTAMP);
		createEAttribute(db2ColumnEClass, DB2_COLUMN__ROW_BEGIN);
		createEAttribute(db2ColumnEClass, DB2_COLUMN__ROW_END);
		createEAttribute(db2ColumnEClass, DB2_COLUMN__TRANS_START_ID);
		createEReference(db2ColumnEClass, DB2_COLUMN__BEGIN_PERIOD);
		createEReference(db2ColumnEClass, DB2_COLUMN__END_PERIOD);

		db2XSRObjectEClass = createEClass(DB2XSR_OBJECT);
		createEReference(db2XSRObjectEClass, DB2XSR_OBJECT__SCHEMA);

		db2XMLSchemaEClass = createEClass(DB2XML_SCHEMA);
		createEAttribute(db2XMLSchemaEClass, DB2XML_SCHEMA__DECOMPOSITION);
		createEAttribute(db2XMLSchemaEClass, DB2XML_SCHEMA__STATUS);
		createEReference(db2XMLSchemaEClass, DB2XML_SCHEMA__XML_SCHEMA_DOCS);

		db2XMLSchemaDocumentEClass = createEClass(DB2XML_SCHEMA_DOCUMENT);
		createEAttribute(db2XMLSchemaDocumentEClass, DB2XML_SCHEMA_DOCUMENT__FILE_NAME);
		createEAttribute(db2XMLSchemaDocumentEClass, DB2XML_SCHEMA_DOCUMENT__SCHEMA_LOCATION);
		createEAttribute(db2XMLSchemaDocumentEClass, DB2XML_SCHEMA_DOCUMENT__TARGET_NAMESPACE);
		createEAttribute(db2XMLSchemaDocumentEClass, DB2XML_SCHEMA_DOCUMENT__PRIMARY);
		createEReference(db2XMLSchemaDocumentEClass, DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA);
		createEReference(db2XMLSchemaDocumentEClass, DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA_DOC_PROPERTIES);

		db2XMLSchemaDocPropertiesEClass = createEClass(DB2XML_SCHEMA_DOC_PROPERTIES);
		createEAttribute(db2XMLSchemaDocPropertiesEClass, DB2XML_SCHEMA_DOC_PROPERTIES__VALUE);
		createEReference(db2XMLSchemaDocPropertiesEClass, DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC);

		db2PackageStatementEClass = createEClass(DB2_PACKAGE_STATEMENT);
		createEAttribute(db2PackageStatementEClass, DB2_PACKAGE_STATEMENT__STATEMENT_NUMBER);
		createEAttribute(db2PackageStatementEClass, DB2_PACKAGE_STATEMENT__SECTION_NUMBER);
		createEReference(db2PackageStatementEClass, DB2_PACKAGE_STATEMENT__PACKAGE);
		createEReference(db2PackageStatementEClass, DB2_PACKAGE_STATEMENT__SQL_STATEMENT);

		db2DistinctUserDefinedTypeEClass = createEClass(DB2_DISTINCT_USER_DEFINED_TYPE);

		db2PeriodEClass = createEClass(DB2_PERIOD);
		createEAttribute(db2PeriodEClass, DB2_PERIOD__TYPE);
		createEReference(db2PeriodEClass, DB2_PERIOD__BEGIN_COLUMN);
		createEReference(db2PeriodEClass, DB2_PERIOD__END_COLUMN);
		createEReference(db2PeriodEClass, DB2_PERIOD__TABLE);

		db2ClusterEClass = createEClass(DB2_CLUSTER);
		createEAttribute(db2ClusterEClass, DB2_CLUSTER__LEVEL);
		createEReference(db2ClusterEClass, DB2_CLUSTER__INSTANCE);
		createEReference(db2ClusterEClass, DB2_CLUSTER__MEMBERS);

		db2MemberEClass = createEClass(DB2_MEMBER);
		createEAttribute(db2MemberEClass, DB2_MEMBER__ID);
		createEAttribute(db2MemberEClass, DB2_MEMBER__HOME_HOST);
		createEAttribute(db2MemberEClass, DB2_MEMBER__CURRENT_HOST);
		createEAttribute(db2MemberEClass, DB2_MEMBER__STATE);
		createEReference(db2MemberEClass, DB2_MEMBER__CLUSTER);

		db2UniqueConstraintExtensionEClass = createEClass(DB2_UNIQUE_CONSTRAINT_EXTENSION);
		createEAttribute(db2UniqueConstraintExtensionEClass, DB2_UNIQUE_CONSTRAINT_EXTENSION__BUS_PERIOD_WITHOUT_OVERLAP);

		db2MaskEClass = createEClass(DB2_MASK);
		createEAttribute(db2MaskEClass, DB2_MASK__CORRELATION_NAME);
		createEReference(db2MaskEClass, DB2_MASK__CASE_EXPRESSION);
		createEAttribute(db2MaskEClass, DB2_MASK__ENABLE);
		createEReference(db2MaskEClass, DB2_MASK__SCHEMA);
		createEReference(db2MaskEClass, DB2_MASK__SUBJECT_TABLE);
		createEReference(db2MaskEClass, DB2_MASK__SUBJECT_MQT);
		createEReference(db2MaskEClass, DB2_MASK__SUBJECT_COLUMN);

		db2PermissionEClass = createEClass(DB2_PERMISSION);
		createEAttribute(db2PermissionEClass, DB2_PERMISSION__CORRELATION_NAME);
		createEReference(db2PermissionEClass, DB2_PERMISSION__SEARCH_CONDITION);
		createEAttribute(db2PermissionEClass, DB2_PERMISSION__ENABLE);
		createEReference(db2PermissionEClass, DB2_PERMISSION__SCHEMA);
		createEReference(db2PermissionEClass, DB2_PERMISSION__SUBJECT_TABLE);
		createEReference(db2PermissionEClass, DB2_PERMISSION__SUBJECT_MQT);

		// Create enums
		isolationLevelTypeEEnum = createEEnum(ISOLATION_LEVEL_TYPE);
		db2IndexTypeEEnum = createEEnum(DB2_INDEX_TYPE);
		dataCaptureTypeEEnum = createEEnum(DATA_CAPTURE_TYPE);
		unitTypeEEnum = createEEnum(UNIT_TYPE);
		generateTypeEEnum = createEEnum(GENERATE_TYPE);
		db2XMLSchemaDecompositionEEnum = createEEnum(DB2XML_SCHEMA_DECOMPOSITION);
		db2XMLSchemaStatusEEnum = createEEnum(DB2XML_SCHEMA_STATUS);
		originTypeEEnum = createEEnum(ORIGIN_TYPE);
		reoptTypeEEnum = createEEnum(REOPT_TYPE);
		sourceDialectEEnum = createEEnum(SOURCE_DIALECT);
		db2PeriodTypeEEnum = createEEnum(DB2_PERIOD_TYPE);
		db2TableOrganizationEEnum = createEEnum(DB2_TABLE_ORGANIZATION);
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
		SQLRoutinesPackage theSQLRoutinesPackage = (SQLRoutinesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLRoutinesPackage.eNS_URI);
		SQLDataTypesPackage theSQLDataTypesPackage = (SQLDataTypesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLDataTypesPackage.eNS_URI);
		SQLConstraintsPackage theSQLConstraintsPackage = (SQLConstraintsPackage)EPackage.Registry.INSTANCE.getEPackage(SQLConstraintsPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		SQLStatementsPackage theSQLStatementsPackage = (SQLStatementsPackage)EPackage.Registry.INSTANCE.getEPackage(SQLStatementsPackage.eNS_URI);
		SQLExpressionsPackage theSQLExpressionsPackage = (SQLExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage(SQLExpressionsPackage.eNS_URI);


		// Add supertypes to classes
		db2DatabaseEClass.getESuperTypes().add(theSQLSchemaPackage.getDatabase());
		db2PackageEClass.getESuperTypes().add(this.getDB2AccessPlan());
		db2TableEClass.getESuperTypes().add(theSQLTablesPackage.getPersistentTable());
		db2TriggerEClass.getESuperTypes().add(theSQLTablesPackage.getTrigger());
		db2TriggerEClass.getESuperTypes().add(this.getDB2AccessPlan());
		db2ProcedureEClass.getESuperTypes().add(theSQLRoutinesPackage.getProcedure());
		db2ProcedureEClass.getESuperTypes().add(this.getDB2Routine());
		db2SchemaEClass.getESuperTypes().add(theSQLSchemaPackage.getSchema());
		db2RoutineEClass.getESuperTypes().add(theSQLRoutinesPackage.getRoutine());
		db2RoutineEClass.getESuperTypes().add(this.getDB2AccessPlan());
		db2DatabaseManagerEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2ViewEClass.getESuperTypes().add(theSQLTablesPackage.getViewTable());
		db2ApplicationProcessEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2TransactionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2SystemSchemaEClass.getESuperTypes().add(this.getDB2Schema());
		db2SourceEClass.getESuperTypes().add(theSQLRoutinesPackage.getSource());
		db2AccessPlanEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2UserDefinedFunctionEClass.getESuperTypes().add(theSQLRoutinesPackage.getUserDefinedFunction());
		db2UserDefinedFunctionEClass.getESuperTypes().add(this.getDB2Function());
		db2MethodEClass.getESuperTypes().add(theSQLRoutinesPackage.getMethod());
		db2MethodEClass.getESuperTypes().add(this.getDB2Function());
		db2ExtendedOptionsEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2AliasEClass.getESuperTypes().add(theSQLTablesPackage.getTable());
		db2MaterializedQueryTableEClass.getESuperTypes().add(theSQLTablesPackage.getDerivedTable());
		db2IndexEClass.getESuperTypes().add(theSQLConstraintsPackage.getIndex());
		db2MultidimensionalIndexEClass.getESuperTypes().add(theSQLConstraintsPackage.getIndex());
		db2FunctionEClass.getESuperTypes().add(this.getDB2Routine());
		db2JavaOptionsEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2ProcedureDeployEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2OLAPObjectEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2RoutineExtensionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2IdentitySpecifierEClass.getESuperTypes().add(theSQLSchemaPackage.getIdentitySpecifier());
		db2JarEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2ColumnEClass.getESuperTypes().add(theSQLTablesPackage.getColumn());
		db2XSRObjectEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2XMLSchemaEClass.getESuperTypes().add(this.getDB2XSRObject());
		db2XMLSchemaDocumentEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2XMLSchemaDocPropertiesEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2PackageStatementEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2DistinctUserDefinedTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getDistinctUserDefinedType());
		db2ClusterEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2MemberEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2UniqueConstraintExtensionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2UniqueConstraintExtensionEClass.getESuperTypes().add(theSQLSchemaPackage.getObjectExtension());
		db2MaskEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		db2PermissionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());

		// Initialize classes and features; add operations and parameters
		initEClass(db2DatabaseEClass, DB2Database.class, "DB2Database", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Database_Partitioned(), ecorePackage.getEBoolean(), "partitioned", null, 0, 1, DB2Database.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Database_DefaultOrganizeByRow(), ecorePackage.getEBoolean(), "defaultOrganizeByRow", "True", 0, 1, DB2Database.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(db2PackageEClass, DB2Package.class, "DB2Package", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Package_Operative(), ecorePackage.getEBoolean(), "operative", "true", 0, 1, DB2Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDB2Package_Valid(), ecorePackage.getEString(), "valid", null, 0, 1, DB2Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Package_Version(), ecorePackage.getEString(), "version", null, 0, 1, DB2Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Package_DefaultSchema(), ecorePackage.getEString(), "defaultSchema", null, 0, 1, DB2Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Package_SqlPath(), ecorePackage.getEString(), "sqlPath", null, 0, 1, DB2Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Package_ReoptVar(), this.getReoptType(), "reoptVar", null, 0, 1, DB2Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Package_Isolation(), this.getIsolationLevelType(), "isolation", null, 0, 1, DB2Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Package_UniqueID(), ecorePackage.getEString(), "uniqueID", null, 0, 1, DB2Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Package_LastBindTS(), ecorePackage.getEString(), "lastBindTS", null, 0, 1, DB2Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Package_Schema(), this.getDB2Schema(), this.getDB2Schema_Packages(), "schema", null, 1, 1, DB2Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Package_Statements(), this.getDB2PackageStatement(), this.getDB2PackageStatement_Package(), "statements", null, 0, -1, DB2Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2TableEClass, DB2Table.class, "DB2Table", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Table_DataCapture(), this.getDataCaptureType(), "dataCapture", null, 0, 1, DB2Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Table_ActivateRowAccessControl(), ecorePackage.getEBoolean(), "activateRowAccessControl", "False", 0, 1, DB2Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDB2Table_ActivateColumnAccessControl(), ecorePackage.getEBoolean(), "activateColumnAccessControl", "False", 0, 1, DB2Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDB2Table_OrganizeBy(), this.getDB2TableOrganization(), "organizeBy", null, 0, 1, DB2Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Table_Packages(), this.getDB2Package(), null, "packages", null, 0, -1, DB2Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Table_Periods(), this.getDB2Period(), this.getDB2Period_Table(), "periods", null, 0, -1, DB2Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Table_HistoryTable(), this.getDB2Table(), this.getDB2Table_TemporalTable(), "historyTable", null, 0, 1, DB2Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Table_TemporalTable(), this.getDB2Table(), this.getDB2Table_HistoryTable(), "temporalTable", null, 0, 1, DB2Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Table_Masks(), this.getDB2Mask(), this.getDB2Mask_SubjectTable(), "masks", null, 0, -1, DB2Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Table_Permissions(), this.getDB2Permission(), this.getDB2Permission_SubjectTable(), "permissions", null, 0, -1, DB2Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2TriggerEClass, DB2Trigger.class, "DB2Trigger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Trigger_Operative(), ecorePackage.getEBoolean(), "operative", "true", 0, 1, DB2Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDB2Trigger_Secured(), ecorePackage.getEBoolean(), "secured", "False", 0, 1, DB2Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(db2ProcedureEClass, DB2Procedure.class, "DB2Procedure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Procedure_ModelResultSets(), ecorePackage.getEBoolean(), "modelResultSets", "false", 0, 1, DB2Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDB2Procedure_NullInput(), ecorePackage.getEBoolean(), "nullInput", null, 0, 1, DB2Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Procedure_Version(), ecorePackage.getEString(), "version", null, 0, 1, DB2Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Procedure_Dialect(), this.getSourceDialect(), "dialect", null, 0, 1, DB2Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Procedure_ExternalAction(), ecorePackage.getEBoolean(), "externalAction", "true", 0, 1, DB2Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getDB2Procedure_Return(), theSQLDataTypesPackage.getIntegerDataType(), null, "return", null, 0, 1, DB2Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Procedure_JavaOptions(), this.getDB2JavaOptions(), this.getDB2JavaOptions_Procedure(), "javaOptions", null, 0, 1, DB2Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Procedure_Deploy(), this.getDB2ProcedureDeploy(), null, "deploy", null, 0, 1, DB2Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2SchemaEClass, DB2Schema.class, "DB2Schema", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDB2Schema_AccessPlans(), this.getDB2AccessPlan(), null, "accessPlans", null, 0, -1, DB2Schema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Schema_OlapObjects(), this.getDB2OLAPObject(), this.getDB2OLAPObject_Schema(), "olapObjects", null, 0, -1, DB2Schema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Schema_Jars(), this.getDB2Jar(), this.getDB2Jar_Schema(), "jars", null, 0, -1, DB2Schema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Schema_XsrObjects(), this.getDB2XSRObject(), this.getDB2XSRObject_Schema(), "xsrObjects", null, 0, -1, DB2Schema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Schema_Packages(), this.getDB2Package(), this.getDB2Package_Schema(), "packages", null, 0, -1, DB2Schema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Schema_Masks(), this.getDB2Mask(), this.getDB2Mask_Schema(), "masks", null, 0, -1, DB2Schema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Schema_Permissions(), this.getDB2Permission(), this.getDB2Permission_Schema(), "permissions", null, 0, -1, DB2Schema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2RoutineEClass, DB2Routine.class, "DB2Routine", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Routine_Fenced(), ecorePackage.getEString(), "fenced", null, 0, 1, DB2Routine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Routine_Threadsafe(), ecorePackage.getEString(), "threadsafe", null, 0, 1, DB2Routine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Routine_DbInfo(), ecorePackage.getEBoolean(), "dbInfo", null, 0, 1, DB2Routine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Routine_ImplicitSchema(), ecorePackage.getEBoolean(), "implicitSchema", null, 0, 1, DB2Routine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Routine_Federated(), ecorePackage.getEBoolean(), "federated", null, 0, 1, DB2Routine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Routine_ParmCcsid(), ecorePackage.getEString(), "parmCcsid", null, 0, 1, DB2Routine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Routine_SpecialRegister(), ecorePackage.getEString(), "specialRegister", null, 0, 1, DB2Routine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Routine_ChangeState(), ecorePackage.getEInt(), "changeState", "0", 0, 1, DB2Routine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDB2Routine_DebugId(), ecorePackage.getEString(), "debugId", null, 0, 1, DB2Routine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Routine_ProgramType(), ecorePackage.getEString(), "programType", null, 0, 1, DB2Routine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Routine_OrigSchemaName(), ecorePackage.getEString(), "origSchemaName", null, 0, 1, DB2Routine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Routine_OrigParmSig(), ecorePackage.getEString(), "origParmSig", null, 0, 1, DB2Routine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Routine_ExtendedOptions(), this.getDB2ExtendedOptions(), null, "extendedOptions", null, 1, -1, DB2Routine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Routine_RoutineExtensions(), this.getDB2RoutineExtension(), null, "routineExtensions", null, 0, -1, DB2Routine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2DatabaseManagerEClass, DB2DatabaseManager.class, "DB2DatabaseManager", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDB2DatabaseManager_Databases(), this.getDB2Database(), null, "databases", null, 0, -1, DB2DatabaseManager.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2DatabaseManager_Db2Process(), this.getDB2ApplicationProcess(), null, "db2Process", null, 0, -1, DB2DatabaseManager.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2DatabaseManager_Cluster(), this.getDB2Cluster(), this.getDB2Cluster_Instance(), "cluster", null, 0, 1, DB2DatabaseManager.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2ViewEClass, DB2View.class, "DB2View", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2View_Operative(), ecorePackage.getEBoolean(), "operative", null, 0, 1, DB2View.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2ApplicationProcessEClass, DB2ApplicationProcess.class, "DB2ApplicationProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2ApplicationProcess_IsolationLevel(), this.getIsolationLevelType(), "isolationLevel", null, 0, 1, DB2ApplicationProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2ApplicationProcess_UnitOfWork(), this.getDB2Transaction(), null, "unitOfWork", null, 0, 1, DB2ApplicationProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2TransactionEClass, DB2Transaction.class, "DB2Transaction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(db2SystemSchemaEClass, DB2SystemSchema.class, "DB2SystemSchema", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(db2SourceEClass, DB2Source.class, "DB2Source", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Source_FileName(), ecorePackage.getEString(), "fileName", null, 0, 1, DB2Source.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Source_PackageName(), ecorePackage.getEString(), "packageName", null, 0, 1, DB2Source.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Source_Db2PackageName(), ecorePackage.getEString(), "db2PackageName", null, 0, 1, DB2Source.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Source_LastModified(), ecorePackage.getEString(), "lastModified", null, 0, 1, DB2Source.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Source_Supporting(), this.getDB2Source(), this.getDB2Source_Primary(), "supporting", null, 0, -1, DB2Source.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Source_Primary(), this.getDB2Source(), this.getDB2Source_Supporting(), "primary", null, 1, 1, DB2Source.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2AccessPlanEClass, DB2AccessPlan.class, "DB2AccessPlan", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(db2UserDefinedFunctionEClass, DB2UserDefinedFunction.class, "DB2UserDefinedFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(db2MethodEClass, DB2Method.class, "DB2Method", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Method_ReturnsSelfAsResult(), ecorePackage.getEBoolean(), "returnsSelfAsResult", null, 0, 1, DB2Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Method_Implemented(), ecorePackage.getEBoolean(), "implemented", null, 0, 1, DB2Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2ExtendedOptionsEClass, DB2ExtendedOptions.class, "DB2ExtendedOptions", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2ExtendedOptions_ClasspathCompileJars(), ecorePackage.getEString(), "classpathCompileJars", null, 0, 1, DB2ExtendedOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2ExtendedOptions_PreCompileOpts(), ecorePackage.getEString(), "preCompileOpts", null, 0, 1, DB2ExtendedOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2ExtendedOptions_ForDebug(), ecorePackage.getEBoolean(), "forDebug", null, 0, 1, DB2ExtendedOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2ExtendedOptions_Built(), ecorePackage.getEBoolean(), "built", null, 0, 1, DB2ExtendedOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2ExtendedOptions_CompileOpts(), ecorePackage.getEString(), "compileOpts", null, 0, 1, DB2ExtendedOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2ExtendedOptions_LinkOpts(), ecorePackage.getEString(), "linkOpts", null, 0, 1, DB2ExtendedOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2ExtendedOptions_BindOpts(), ecorePackage.getEString(), "bindOpts", null, 0, 1, DB2ExtendedOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2ExtendedOptions_Colid(), ecorePackage.getEString(), "colid", null, 0, 1, DB2ExtendedOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2AliasEClass, DB2Alias.class, "DB2Alias", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDB2Alias_AliasedTable(), theSQLTablesPackage.getTable(), null, "aliasedTable", null, 1, 1, DB2Alias.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2MaterializedQueryTableEClass, DB2MaterializedQueryTable.class, "DB2MaterializedQueryTable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2MaterializedQueryTable_OptimizeQuery(), ecorePackage.getEBoolean(), "optimizeQuery", "true", 0, 1, DB2MaterializedQueryTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDB2MaterializedQueryTable_ActivateRowAccessControl(), ecorePackage.getEBoolean(), "activateRowAccessControl", "False", 0, 1, DB2MaterializedQueryTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDB2MaterializedQueryTable_ActivateColumnAccessControl(), ecorePackage.getEBoolean(), "activateColumnAccessControl", "False", 0, 1, DB2MaterializedQueryTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getDB2MaterializedQueryTable_Masks(), this.getDB2Mask(), this.getDB2Mask_SubjectMQT(), "masks", null, 0, -1, DB2MaterializedQueryTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2MaterializedQueryTable_Permissions(), this.getDB2Permission(), this.getDB2Permission_SubjectMQT(), "permissions", null, 0, -1, DB2MaterializedQueryTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2IndexEClass, DB2Index.class, "DB2Index", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Index_IndexType(), this.getDB2IndexType(), "indexType", null, 0, 1, DB2Index.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Index_BusPeriodWithoutOverlap(), ecorePackage.getEBoolean(), "busPeriodWithoutOverlap", "False", 0, 1, DB2Index.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDB2Index_EncodedVector(), ecorePackage.getEBoolean(), "encodedVector", "False", 0, 1, DB2Index.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getDB2Index_DB2MultidimensionalIndex(), this.getDB2MultidimensionalIndex(), this.getDB2MultidimensionalIndex_DimensionIndexes(), "DB2MultidimensionalIndex", null, 1, 1, DB2Index.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2MultidimensionalIndexEClass, DB2MultidimensionalIndex.class, "DB2MultidimensionalIndex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDB2MultidimensionalIndex_DimensionIndexes(), this.getDB2Index(), this.getDB2Index_DB2MultidimensionalIndex(), "dimensionIndexes", null, 1, -1, DB2MultidimensionalIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2FunctionEClass, DB2Function.class, "DB2Function", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Function_FinalCall(), ecorePackage.getEBoolean(), "finalCall", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Function_ScratchPad(), ecorePackage.getEBoolean(), "scratchPad", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Function_ScratchPadLength(), ecorePackage.getEInt(), "scratchPadLength", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Function_FunctionType(), ecorePackage.getEString(), "functionType", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Function_Predicate(), ecorePackage.getEString(), "predicate", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Function_ExternalAction(), ecorePackage.getEBoolean(), "externalAction", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Function_Cardinality(), ecorePackage.getEInt(), "cardinality", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Function_AllowParallel(), ecorePackage.getEBoolean(), "allowParallel", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Function_ReturnClause(), ecorePackage.getEString(), "returnClause", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Function_Origin(), this.getOriginType(), "origin", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Function_InheritLockRequest(), ecorePackage.getEBoolean(), "inheritLockRequest", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Function_Dialect(), this.getSourceDialect(), "dialect", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Function_Inline(), ecorePackage.getEBoolean(), "inline", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Function_Version(), ecorePackage.getEString(), "version", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Function_Secured(), ecorePackage.getEBoolean(), "secured", null, 0, 1, DB2Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2JavaOptionsEClass, DB2JavaOptions.class, "DB2JavaOptions", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2JavaOptions_ClassName(), ecorePackage.getEString(), "className", null, 0, 1, DB2JavaOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2JavaOptions_MethodName(), ecorePackage.getEString(), "methodName", null, 0, 1, DB2JavaOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2JavaOptions_Sqlj(), ecorePackage.getEBoolean(), "sqlj", null, 0, 1, DB2JavaOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2JavaOptions_Procedure(), this.getDB2Procedure(), this.getDB2Procedure_JavaOptions(), "procedure", null, 1, 1, DB2JavaOptions.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2JavaOptions_Jar(), this.getDB2Jar(), this.getDB2Jar_Procedures(), "jar", null, 0, 1, DB2JavaOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2ProcedureDeployEClass, DB2ProcedureDeploy.class, "DB2ProcedureDeploy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2ProcedureDeploy_FileName(), ecorePackage.getEString(), "fileName", null, 0, 1, DB2ProcedureDeploy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2OLAPObjectEClass, DB2OLAPObject.class, "DB2OLAPObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDB2OLAPObject_Schema(), this.getDB2Schema(), this.getDB2Schema_OlapObjects(), "schema", null, 1, 1, DB2OLAPObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2RoutineExtensionEClass, DB2RoutineExtension.class, "DB2RoutineExtension", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(db2IdentitySpecifierEClass, DB2IdentitySpecifier.class, "DB2IdentitySpecifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2IdentitySpecifier_Cache(), ecorePackage.getEInt(), "cache", "20", 0, 1, DB2IdentitySpecifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDB2IdentitySpecifier_Order(), ecorePackage.getEBoolean(), "order", null, 0, 1, DB2IdentitySpecifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2IdentitySpecifier_SystemGenerated(), ecorePackage.getEBoolean(), "systemGenerated", null, 0, 1, DB2IdentitySpecifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2IdentitySpecifier_RestartValue(), theEcorePackage.getEBigInteger(), "restartValue", null, 0, 1, DB2IdentitySpecifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2JarEClass, DB2Jar.class, "DB2Jar", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Jar_FileName(), ecorePackage.getEString(), "fileName", null, 0, 1, DB2Jar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Jar_Path(), ecorePackage.getEString(), "path", null, 0, 1, DB2Jar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Jar_Owner(), ecorePackage.getEString(), "owner", null, 0, 1, DB2Jar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Jar_CreatedTS(), ecorePackage.getEString(), "createdTS", null, 0, 1, DB2Jar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Jar_AlteredTS(), ecorePackage.getEString(), "alteredTS", null, 0, 1, DB2Jar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Jar_Procedures(), this.getDB2JavaOptions(), this.getDB2JavaOptions_Jar(), "procedures", null, 0, -1, DB2Jar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Jar_Schema(), this.getDB2Schema(), this.getDB2Schema_Jars(), "schema", null, 1, 1, DB2Jar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2ColumnEClass, DB2Column.class, "DB2Column", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Column_GenerationType(), this.getGenerateType(), "generationType", null, 0, 1, DB2Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Column_RowChangeTimestamp(), ecorePackage.getEBoolean(), "rowChangeTimestamp", null, 0, 1, DB2Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Column_RowBegin(), ecorePackage.getEBoolean(), "rowBegin", "False", 0, 1, DB2Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDB2Column_RowEnd(), ecorePackage.getEBoolean(), "rowEnd", "False", 0, 1, DB2Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDB2Column_TransStartID(), ecorePackage.getEBoolean(), "transStartID", "False", 0, 1, DB2Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getDB2Column_BeginPeriod(), this.getDB2Period(), this.getDB2Period_BeginColumn(), "beginPeriod", null, 0, 1, DB2Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Column_EndPeriod(), this.getDB2Period(), this.getDB2Period_EndColumn(), "endPeriod", null, 0, 1, DB2Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2XSRObjectEClass, DB2XSRObject.class, "DB2XSRObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDB2XSRObject_Schema(), this.getDB2Schema(), this.getDB2Schema_XsrObjects(), "schema", null, 1, 1, DB2XSRObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2XMLSchemaEClass, DB2XMLSchema.class, "DB2XMLSchema", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2XMLSchema_Decomposition(), this.getDB2XMLSchemaDecomposition(), "decomposition", null, 0, 1, DB2XMLSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2XMLSchema_Status(), this.getDB2XMLSchemaStatus(), "status", null, 0, 1, DB2XMLSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2XMLSchema_XmlSchemaDocs(), this.getDB2XMLSchemaDocument(), this.getDB2XMLSchemaDocument_XmlSchema(), "xmlSchemaDocs", null, 1, -1, DB2XMLSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2XMLSchemaDocumentEClass, DB2XMLSchemaDocument.class, "DB2XMLSchemaDocument", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2XMLSchemaDocument_FileName(), ecorePackage.getEString(), "fileName", null, 0, 1, DB2XMLSchemaDocument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2XMLSchemaDocument_SchemaLocation(), ecorePackage.getEString(), "schemaLocation", null, 0, 1, DB2XMLSchemaDocument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2XMLSchemaDocument_TargetNamespace(), ecorePackage.getEString(), "targetNamespace", null, 0, 1, DB2XMLSchemaDocument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2XMLSchemaDocument_Primary(), ecorePackage.getEBoolean(), "primary", null, 0, 1, DB2XMLSchemaDocument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2XMLSchemaDocument_XmlSchema(), this.getDB2XMLSchema(), this.getDB2XMLSchema_XmlSchemaDocs(), "xmlSchema", null, 1, 1, DB2XMLSchemaDocument.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2XMLSchemaDocument_XmlSchemaDocProperties(), this.getDB2XMLSchemaDocProperties(), this.getDB2XMLSchemaDocProperties_XmlSchemaDoc(), "xmlSchemaDocProperties", null, 1, -1, DB2XMLSchemaDocument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2XMLSchemaDocPropertiesEClass, DB2XMLSchemaDocProperties.class, "DB2XMLSchemaDocProperties", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2XMLSchemaDocProperties_Value(), ecorePackage.getEString(), "value", null, 0, 1, DB2XMLSchemaDocProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2XMLSchemaDocProperties_XmlSchemaDoc(), this.getDB2XMLSchemaDocument(), this.getDB2XMLSchemaDocument_XmlSchemaDocProperties(), "xmlSchemaDoc", null, 1, 1, DB2XMLSchemaDocProperties.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2PackageStatementEClass, DB2PackageStatement.class, "DB2PackageStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2PackageStatement_StatementNumber(), ecorePackage.getEInt(), "statementNumber", null, 0, 1, DB2PackageStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2PackageStatement_SectionNumber(), ecorePackage.getEInt(), "sectionNumber", null, 0, 1, DB2PackageStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2PackageStatement_Package(), this.getDB2Package(), this.getDB2Package_Statements(), "package", null, 1, 1, DB2PackageStatement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2PackageStatement_SqlStatement(), theSQLStatementsPackage.getSQLStatement(), null, "sqlStatement", null, 0, 1, DB2PackageStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2DistinctUserDefinedTypeEClass, DB2DistinctUserDefinedType.class, "DB2DistinctUserDefinedType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(db2PeriodEClass, DB2Period.class, "DB2Period", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Period_Type(), this.getDB2PeriodType(), "type", null, 0, 1, DB2Period.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Period_BeginColumn(), this.getDB2Column(), this.getDB2Column_BeginPeriod(), "beginColumn", null, 1, 1, DB2Period.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Period_EndColumn(), this.getDB2Column(), this.getDB2Column_EndPeriod(), "endColumn", null, 1, 1, DB2Period.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Period_Table(), this.getDB2Table(), this.getDB2Table_Periods(), "table", null, 1, 1, DB2Period.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2ClusterEClass, DB2Cluster.class, "DB2Cluster", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Cluster_Level(), ecorePackage.getEString(), "level", null, 0, 1, DB2Cluster.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Cluster_Instance(), this.getDB2DatabaseManager(), this.getDB2DatabaseManager_Cluster(), "instance", null, 1, 1, DB2Cluster.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Cluster_Members(), this.getDB2Member(), this.getDB2Member_Cluster(), "members", null, 0, -1, DB2Cluster.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2MemberEClass, DB2Member.class, "DB2Member", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Member_Id(), ecorePackage.getEInt(), "id", null, 0, 1, DB2Member.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Member_HomeHost(), ecorePackage.getEString(), "homeHost", null, 0, 1, DB2Member.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Member_CurrentHost(), ecorePackage.getEString(), "currentHost", null, 0, 1, DB2Member.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1
		initEReference(getDB2Member_Cluster(), this.getDB2Cluster(), this.getDB2Cluster_Members(), "cluster", null, 1, 1, DB2Member.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2UniqueConstraintExtensionEClass, DB2UniqueConstraintExtension.class, "DB2UniqueConstraintExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2UniqueConstraintExtension_BusPeriodWithoutOverlap(), ecorePackage.getEBoolean(), "busPeriodWithoutOverlap", "False", 0, 1, DB2UniqueConstraintExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(db2MaskEClass, DB2Mask.class, "DB2Mask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Mask_CorrelationName(), ecorePackage.getEString(), "correlationName", null, 0, 1, DB2Mask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Mask_CaseExpression(), theSQLExpressionsPackage.getQueryExpression(), null, "caseExpression", null, 0, 1, DB2Mask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Mask_Enable(), ecorePackage.getEBoolean(), "enable", "true", 0, 1, DB2Mask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getDB2Mask_Schema(), this.getDB2Schema(), this.getDB2Schema_Masks(), "schema", null, 1, 1, DB2Mask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Mask_SubjectTable(), this.getDB2Table(), this.getDB2Table_Masks(), "subjectTable", null, 1, 1, DB2Mask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Mask_SubjectMQT(), this.getDB2MaterializedQueryTable(), this.getDB2MaterializedQueryTable_Masks(), "subjectMQT", null, 1, 1, DB2Mask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Mask_SubjectColumn(), this.getDB2Column(), null, "subjectColumn", null, 1, 1, DB2Mask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(db2PermissionEClass, DB2Permission.class, "DB2Permission", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDB2Permission_CorrelationName(), ecorePackage.getEString(), "correlationName", null, 0, 1, DB2Permission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Permission_SearchCondition(), theSQLExpressionsPackage.getQueryExpression(), null, "searchCondition", null, 0, 1, DB2Permission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDB2Permission_Enable(), ecorePackage.getEBoolean(), "enable", "true", 0, 1, DB2Permission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getDB2Permission_Schema(), this.getDB2Schema(), this.getDB2Schema_Permissions(), "schema", null, 1, 1, DB2Permission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Permission_SubjectTable(), this.getDB2Table(), this.getDB2Table_Permissions(), "subjectTable", null, 1, 1, DB2Permission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDB2Permission_SubjectMQT(), this.getDB2MaterializedQueryTable(), this.getDB2MaterializedQueryTable_Permissions(), "subjectMQT", null, 1, 1, DB2Permission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(isolationLevelTypeEEnum, IsolationLevelType.class, "IsolationLevelType"); //$NON-NLS-1$
		addEEnumLiteral(isolationLevelTypeEEnum, IsolationLevelType.REPEATABLE_READ_LITERAL);
		addEEnumLiteral(isolationLevelTypeEEnum, IsolationLevelType.READ_STABILITY_LITERAL);
		addEEnumLiteral(isolationLevelTypeEEnum, IsolationLevelType.CURSOR_STABILITY_LITERAL);
		addEEnumLiteral(isolationLevelTypeEEnum, IsolationLevelType.UNCOMMITTED_READ_LITERAL);

		initEEnum(db2IndexTypeEEnum, DB2IndexType.class, "DB2IndexType"); //$NON-NLS-1$
		addEEnumLiteral(db2IndexTypeEEnum, DB2IndexType.REGULAR_LITERAL);
		addEEnumLiteral(db2IndexTypeEEnum, DB2IndexType.BLOCK_LITERAL);
		addEEnumLiteral(db2IndexTypeEEnum, DB2IndexType.DIMENSION_LITERAL);
		addEEnumLiteral(db2IndexTypeEEnum, DB2IndexType.XMLPATH_LITERAL);
		addEEnumLiteral(db2IndexTypeEEnum, DB2IndexType.XMLREGION_LITERAL);
		addEEnumLiteral(db2IndexTypeEEnum, DB2IndexType.XMLCOLUMN_LOGICAL_LITERAL);
		addEEnumLiteral(db2IndexTypeEEnum, DB2IndexType.XMLCOLUMN_PHYSICAL_LITERAL);

		initEEnum(dataCaptureTypeEEnum, DataCaptureType.class, "DataCaptureType"); //$NON-NLS-1$
		addEEnumLiteral(dataCaptureTypeEEnum, DataCaptureType.NONE_LITERAL);
		addEEnumLiteral(dataCaptureTypeEEnum, DataCaptureType.CHANGES_LITERAL);

		initEEnum(unitTypeEEnum, UnitType.class, "UnitType"); //$NON-NLS-1$
		addEEnumLiteral(unitTypeEEnum, UnitType.K_LITERAL);
		addEEnumLiteral(unitTypeEEnum, UnitType.M_LITERAL);
		addEEnumLiteral(unitTypeEEnum, UnitType.G_LITERAL);

		initEEnum(generateTypeEEnum, GenerateType.class, "GenerateType"); //$NON-NLS-1$
		addEEnumLiteral(generateTypeEEnum, GenerateType.ALWAYS_LITERAL);
		addEEnumLiteral(generateTypeEEnum, GenerateType.BY_DEFAULT_LITERAL);

		initEEnum(db2XMLSchemaDecompositionEEnum, DB2XMLSchemaDecomposition.class, "DB2XMLSchemaDecomposition"); //$NON-NLS-1$
		addEEnumLiteral(db2XMLSchemaDecompositionEEnum, DB2XMLSchemaDecomposition.ENABLED_LITERAL);
		addEEnumLiteral(db2XMLSchemaDecompositionEEnum, DB2XMLSchemaDecomposition.NOT_ENABLED_LITERAL);
		addEEnumLiteral(db2XMLSchemaDecompositionEEnum, DB2XMLSchemaDecomposition.INOPERATIVE_LITERAL);

		initEEnum(db2XMLSchemaStatusEEnum, DB2XMLSchemaStatus.class, "DB2XMLSchemaStatus"); //$NON-NLS-1$
		addEEnumLiteral(db2XMLSchemaStatusEEnum, DB2XMLSchemaStatus.COMPLETE_LITERAL);
		addEEnumLiteral(db2XMLSchemaStatusEEnum, DB2XMLSchemaStatus.INCOMPLETE_LITERAL);
		addEEnumLiteral(db2XMLSchemaStatusEEnum, DB2XMLSchemaStatus.REPLACE_LITERAL);
		addEEnumLiteral(db2XMLSchemaStatusEEnum, DB2XMLSchemaStatus.TEMPORARY_LITERAL);

		initEEnum(originTypeEEnum, OriginType.class, "OriginType"); //$NON-NLS-1$
		addEEnumLiteral(originTypeEEnum, OriginType.NONE_LITERAL);
		addEEnumLiteral(originTypeEEnum, OriginType.TEMPLATE_LITERAL);
		addEEnumLiteral(originTypeEEnum, OriginType.SOURCE_LITERAL);

		initEEnum(reoptTypeEEnum, ReoptType.class, "ReoptType"); //$NON-NLS-1$
		addEEnumLiteral(reoptTypeEEnum, ReoptType.NONE_LITERAL);
		addEEnumLiteral(reoptTypeEEnum, ReoptType.ONCE_LITERAL);
		addEEnumLiteral(reoptTypeEEnum, ReoptType.ALWAYS_LITERAL);
		addEEnumLiteral(reoptTypeEEnum, ReoptType.AUTO_LITERAL);

		initEEnum(sourceDialectEEnum, SourceDialect.class, "SourceDialect"); //$NON-NLS-1$
		addEEnumLiteral(sourceDialectEEnum, SourceDialect.UNKNOWN_LITERAL);
		addEEnumLiteral(sourceDialectEEnum, SourceDialect.PLSQL_LITERAL);
		addEEnumLiteral(sourceDialectEEnum, SourceDialect.DB2SQLPL_LITERAL);

		initEEnum(db2PeriodTypeEEnum, DB2PeriodType.class, "DB2PeriodType"); //$NON-NLS-1$
		addEEnumLiteral(db2PeriodTypeEEnum, DB2PeriodType.SYSTEM_TIME_LITERAL);
		addEEnumLiteral(db2PeriodTypeEEnum, DB2PeriodType.BUSINESS_TIME_LITERAL);

		initEEnum(db2TableOrganizationEEnum, DB2TableOrganization.class, "DB2TableOrganization"); //$NON-NLS-1$
		addEEnumLiteral(db2TableOrganizationEEnum, DB2TableOrganization.UNSPECIFIED_LITERAL);
		addEEnumLiteral(db2TableOrganizationEEnum, DB2TableOrganization.ROW_LITERAL);
		addEEnumLiteral(db2TableOrganizationEEnum, DB2TableOrganization.COLUMN_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}

} //DB2ModelPackageImpl
