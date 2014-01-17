package org.eclipse.datatools.enablement.ibm.db2.model.impl;


import org.eclipse.datatools.enablement.ibm.db2.model.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class DB2ModelFactoryImpl extends EFactoryImpl implements DB2ModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DB2ModelFactory init() {
		try {
			DB2ModelFactory theDB2ModelFactory = (DB2ModelFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org.eclipse.datatools.enablement.ibm.db2.model/db2.ecore"); //$NON-NLS-1$ 
			if (theDB2ModelFactory != null) {
				return theDB2ModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DB2ModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2ModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case DB2ModelPackage.DB2_DATABASE: return createDB2Database();
			case DB2ModelPackage.DB2_PACKAGE: return createDB2Package();
			case DB2ModelPackage.DB2_TABLE: return createDB2Table();
			case DB2ModelPackage.DB2_TRIGGER: return createDB2Trigger();
			case DB2ModelPackage.DB2_PROCEDURE: return createDB2Procedure();
			case DB2ModelPackage.DB2_SCHEMA: return createDB2Schema();
			case DB2ModelPackage.DB2_DATABASE_MANAGER: return createDB2DatabaseManager();
			case DB2ModelPackage.DB2_VIEW: return createDB2View();
			case DB2ModelPackage.DB2_APPLICATION_PROCESS: return createDB2ApplicationProcess();
			case DB2ModelPackage.DB2_TRANSACTION: return createDB2Transaction();
			case DB2ModelPackage.DB2_SYSTEM_SCHEMA: return createDB2SystemSchema();
			case DB2ModelPackage.DB2_SOURCE: return createDB2Source();
			case DB2ModelPackage.DB2_USER_DEFINED_FUNCTION: return createDB2UserDefinedFunction();
			case DB2ModelPackage.DB2_METHOD: return createDB2Method();
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS: return createDB2ExtendedOptions();
			case DB2ModelPackage.DB2_ALIAS: return createDB2Alias();
			case DB2ModelPackage.DB2_INDEX: return createDB2Index();
			case DB2ModelPackage.DB2_MULTIDIMENSIONAL_INDEX: return createDB2MultidimensionalIndex();
			case DB2ModelPackage.DB2_JAVA_OPTIONS: return createDB2JavaOptions();
			case DB2ModelPackage.DB2_PROCEDURE_DEPLOY: return createDB2ProcedureDeploy();
			case DB2ModelPackage.DB2OLAP_OBJECT: return createDB2OLAPObject();
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER: return createDB2IdentitySpecifier();
			case DB2ModelPackage.DB2_JAR: return createDB2Jar();
			case DB2ModelPackage.DB2_COLUMN: return createDB2Column();
			case DB2ModelPackage.DB2XSR_OBJECT: return createDB2XSRObject();
			case DB2ModelPackage.DB2XML_SCHEMA: return createDB2XMLSchema();
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT: return createDB2XMLSchemaDocument();
			case DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES: return createDB2XMLSchemaDocProperties();
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT: return createDB2PackageStatement();
			case DB2ModelPackage.DB2_PERIOD: return createDB2Period();
			case DB2ModelPackage.DB2_CLUSTER: return createDB2Cluster();
			case DB2ModelPackage.DB2_MEMBER: return createDB2Member();
			case DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION: return createDB2UniqueConstraintExtension();
			case DB2ModelPackage.DB2_MASK: return createDB2Mask();
			case DB2ModelPackage.DB2_PERMISSION: return createDB2Permission();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case DB2ModelPackage.ISOLATION_LEVEL_TYPE:
				return createIsolationLevelTypeFromString(eDataType, initialValue);
			case DB2ModelPackage.DB2_INDEX_TYPE:
				return createDB2IndexTypeFromString(eDataType, initialValue);
			case DB2ModelPackage.DATA_CAPTURE_TYPE:
				return createDataCaptureTypeFromString(eDataType, initialValue);
			case DB2ModelPackage.UNIT_TYPE:
				return createUnitTypeFromString(eDataType, initialValue);
			case DB2ModelPackage.GENERATE_TYPE:
				return createGenerateTypeFromString(eDataType, initialValue);
			case DB2ModelPackage.DB2XML_SCHEMA_DECOMPOSITION:
				return createDB2XMLSchemaDecompositionFromString(eDataType, initialValue);
			case DB2ModelPackage.DB2XML_SCHEMA_STATUS:
				return createDB2XMLSchemaStatusFromString(eDataType, initialValue);
			case DB2ModelPackage.ORIGIN_TYPE:
				return createOriginTypeFromString(eDataType, initialValue);
			case DB2ModelPackage.REOPT_TYPE:
				return createReoptTypeFromString(eDataType, initialValue);
			case DB2ModelPackage.SOURCE_DIALECT:
				return createSourceDialectFromString(eDataType, initialValue);
			case DB2ModelPackage.DB2_PERIOD_TYPE:
				return createDB2PeriodTypeFromString(eDataType, initialValue);
			case DB2ModelPackage.DB2_TABLE_ORGANIZATION:
				return createDB2TableOrganizationFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case DB2ModelPackage.ISOLATION_LEVEL_TYPE:
				return convertIsolationLevelTypeToString(eDataType, instanceValue);
			case DB2ModelPackage.DB2_INDEX_TYPE:
				return convertDB2IndexTypeToString(eDataType, instanceValue);
			case DB2ModelPackage.DATA_CAPTURE_TYPE:
				return convertDataCaptureTypeToString(eDataType, instanceValue);
			case DB2ModelPackage.UNIT_TYPE:
				return convertUnitTypeToString(eDataType, instanceValue);
			case DB2ModelPackage.GENERATE_TYPE:
				return convertGenerateTypeToString(eDataType, instanceValue);
			case DB2ModelPackage.DB2XML_SCHEMA_DECOMPOSITION:
				return convertDB2XMLSchemaDecompositionToString(eDataType, instanceValue);
			case DB2ModelPackage.DB2XML_SCHEMA_STATUS:
				return convertDB2XMLSchemaStatusToString(eDataType, instanceValue);
			case DB2ModelPackage.ORIGIN_TYPE:
				return convertOriginTypeToString(eDataType, instanceValue);
			case DB2ModelPackage.REOPT_TYPE:
				return convertReoptTypeToString(eDataType, instanceValue);
			case DB2ModelPackage.SOURCE_DIALECT:
				return convertSourceDialectToString(eDataType, instanceValue);
			case DB2ModelPackage.DB2_PERIOD_TYPE:
				return convertDB2PeriodTypeToString(eDataType, instanceValue);
			case DB2ModelPackage.DB2_TABLE_ORGANIZATION:
				return convertDB2TableOrganizationToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Database createDB2Database() {
		DB2DatabaseImpl db2Database = new DB2DatabaseImpl();
		return db2Database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Package createDB2Package() {
		DB2PackageImpl db2Package = new DB2PackageImpl();
		return db2Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Table createDB2Table() {
		DB2TableImpl db2Table = new DB2TableImpl();
		return db2Table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Trigger createDB2Trigger() {
		DB2TriggerImpl db2Trigger = new DB2TriggerImpl();
		return db2Trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Procedure createDB2Procedure() {
		DB2ProcedureImpl db2Procedure = new DB2ProcedureImpl();
		return db2Procedure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Schema createDB2Schema() {
		DB2SchemaImpl db2Schema = new DB2SchemaImpl();
		return db2Schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2DatabaseManager createDB2DatabaseManager() {
		DB2DatabaseManagerImpl db2DatabaseManager = new DB2DatabaseManagerImpl();
		return db2DatabaseManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2View createDB2View() {
		DB2ViewImpl db2View = new DB2ViewImpl();
		return db2View;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2ApplicationProcess createDB2ApplicationProcess() {
		DB2ApplicationProcessImpl db2ApplicationProcess = new DB2ApplicationProcessImpl();
		return db2ApplicationProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Transaction createDB2Transaction() {
		DB2TransactionImpl db2Transaction = new DB2TransactionImpl();
		return db2Transaction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2SystemSchema createDB2SystemSchema() {
		DB2SystemSchemaImpl db2SystemSchema = new DB2SystemSchemaImpl();
		return db2SystemSchema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Source createDB2Source() {
		DB2SourceImpl db2Source = new DB2SourceImpl();
		return db2Source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2UserDefinedFunction createDB2UserDefinedFunction() {
		DB2UserDefinedFunctionImpl db2UserDefinedFunction = new DB2UserDefinedFunctionImpl();
		return db2UserDefinedFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Method createDB2Method() {
		DB2MethodImpl db2Method = new DB2MethodImpl();
		return db2Method;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2ExtendedOptions createDB2ExtendedOptions() {
		DB2ExtendedOptionsImpl db2ExtendedOptions = new DB2ExtendedOptionsImpl();
		return db2ExtendedOptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Alias createDB2Alias() {
		DB2AliasImpl db2Alias = new DB2AliasImpl();
		return db2Alias;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Index createDB2Index() {
		DB2IndexImpl db2Index = new DB2IndexImpl();
		return db2Index;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2MultidimensionalIndex createDB2MultidimensionalIndex() {
		DB2MultidimensionalIndexImpl db2MultidimensionalIndex = new DB2MultidimensionalIndexImpl();
		return db2MultidimensionalIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2JavaOptions createDB2JavaOptions() {
		DB2JavaOptionsImpl db2JavaOptions = new DB2JavaOptionsImpl();
		return db2JavaOptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2ProcedureDeploy createDB2ProcedureDeploy() {
		DB2ProcedureDeployImpl db2ProcedureDeploy = new DB2ProcedureDeployImpl();
		return db2ProcedureDeploy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2OLAPObject createDB2OLAPObject() {
		DB2OLAPObjectImpl db2OLAPObject = new DB2OLAPObjectImpl();
		return db2OLAPObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2IdentitySpecifier createDB2IdentitySpecifier() {
		DB2IdentitySpecifierImpl db2IdentitySpecifier = new DB2IdentitySpecifierImpl();
		return db2IdentitySpecifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Jar createDB2Jar() {
		DB2JarImpl db2Jar = new DB2JarImpl();
		return db2Jar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Column createDB2Column() {
		DB2ColumnImpl db2Column = new DB2ColumnImpl();
		return db2Column;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2XSRObject createDB2XSRObject() {
		DB2XSRObjectImpl db2XSRObject = new DB2XSRObjectImpl();
		return db2XSRObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2XMLSchema createDB2XMLSchema() {
		DB2XMLSchemaImpl db2XMLSchema = new DB2XMLSchemaImpl();
		return db2XMLSchema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2XMLSchemaDocument createDB2XMLSchemaDocument() {
		DB2XMLSchemaDocumentImpl db2XMLSchemaDocument = new DB2XMLSchemaDocumentImpl();
		return db2XMLSchemaDocument;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2XMLSchemaDocProperties createDB2XMLSchemaDocProperties() {
		DB2XMLSchemaDocPropertiesImpl db2XMLSchemaDocProperties = new DB2XMLSchemaDocPropertiesImpl();
		return db2XMLSchemaDocProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2PackageStatement createDB2PackageStatement() {
		DB2PackageStatementImpl db2PackageStatement = new DB2PackageStatementImpl();
		return db2PackageStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Period createDB2Period() {
		DB2PeriodImpl db2Period = new DB2PeriodImpl();
		return db2Period;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Cluster createDB2Cluster() {
		DB2ClusterImpl db2Cluster = new DB2ClusterImpl();
		return db2Cluster;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Member createDB2Member() {
		DB2MemberImpl db2Member = new DB2MemberImpl();
		return db2Member;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2UniqueConstraintExtension createDB2UniqueConstraintExtension() {
		DB2UniqueConstraintExtensionImpl db2UniqueConstraintExtension = new DB2UniqueConstraintExtensionImpl();
		return db2UniqueConstraintExtension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Mask createDB2Mask() {
		DB2MaskImpl db2Mask = new DB2MaskImpl();
		return db2Mask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Permission createDB2Permission() {
		DB2PermissionImpl db2Permission = new DB2PermissionImpl();
		return db2Permission;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IsolationLevelType createIsolationLevelTypeFromString(EDataType eDataType, String initialValue) {
		IsolationLevelType result = IsolationLevelType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIsolationLevelTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2IndexType createDB2IndexTypeFromString(EDataType eDataType, String initialValue) {
		DB2IndexType result = DB2IndexType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDB2IndexTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataCaptureType createDataCaptureTypeFromString(EDataType eDataType, String initialValue) {
		DataCaptureType result = DataCaptureType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDataCaptureTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnitType createUnitTypeFromString(EDataType eDataType, String initialValue) {
		UnitType result = UnitType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUnitTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenerateType createGenerateTypeFromString(EDataType eDataType, String initialValue) {
		GenerateType result = GenerateType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertGenerateTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2XMLSchemaDecomposition createDB2XMLSchemaDecompositionFromString(EDataType eDataType, String initialValue) {
		DB2XMLSchemaDecomposition result = DB2XMLSchemaDecomposition.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDB2XMLSchemaDecompositionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2XMLSchemaStatus createDB2XMLSchemaStatusFromString(EDataType eDataType, String initialValue) {
		DB2XMLSchemaStatus result = DB2XMLSchemaStatus.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDB2XMLSchemaStatusToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OriginType createOriginTypeFromString(EDataType eDataType, String initialValue) {
		OriginType result = OriginType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOriginTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReoptType createReoptTypeFromString(EDataType eDataType, String initialValue) {
		ReoptType result = ReoptType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertReoptTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceDialect createSourceDialectFromString(EDataType eDataType, String initialValue) {
		SourceDialect result = SourceDialect.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSourceDialectToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2PeriodType createDB2PeriodTypeFromString(EDataType eDataType, String initialValue) {
		DB2PeriodType result = DB2PeriodType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDB2PeriodTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2TableOrganization createDB2TableOrganizationFromString(EDataType eDataType, String initialValue) {
		DB2TableOrganization result = DB2TableOrganization.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDB2TableOrganizationToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2ModelPackage getDB2ModelPackage() {
		return (DB2ModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static DB2ModelPackage getPackage() {
		return DB2ModelPackage.eINSTANCE;
	}

} //DB2ModelFactoryImpl
