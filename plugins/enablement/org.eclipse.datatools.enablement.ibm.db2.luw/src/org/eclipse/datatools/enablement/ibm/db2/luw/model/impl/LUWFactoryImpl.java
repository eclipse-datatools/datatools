package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;


import org.eclipse.datatools.enablement.ibm.db2.luw.model.*;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.util.DatabaseToRemoteServerHelper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.util.TableToRemoteDataSetHelper;

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
public class LUWFactoryImpl extends EFactoryImpl implements LUWFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWFactory init() {
		try {
			LUWFactory theLUWFactory = (LUWFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org.eclipse.datatools.enablement.ibm.db2.luw.model.ecore"); //$NON-NLS-1$ 
			if (theLUWFactory != null) {
				return theLUWFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new LUWFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case LUWPackage.LUW_PARTITION_GROUP: return createLUWPartitionGroup();
			case LUWPackage.LUW_TABLE_SPACE: return createLUWTableSpace();
			case LUWPackage.LUW_DATABASE_PARTITION: return createLUWDatabasePartition();
			case LUWPackage.LUW_DATABASE_CONTAINER: return createLUWDatabaseContainer();
			case LUWPackage.LUW_ADMIN_SERVER: return createLUWAdminServer();
			case LUWPackage.LUW_BUFFER_POOL: return createLUWBufferPool();
			case LUWPackage.LUW_TABLE: return createLUWTable();
			case LUWPackage.LUW_VIEW: return createLUWView();
			case LUWPackage.LUW_PARTITION_KEY: return createLUWPartitionKey();
			case LUWPackage.LUW_FUNCTION_MAPPING: return createLUWFunctionMapping();
			case LUWPackage.LUW_GENERIC_USER_MAPPING: return createLUWGenericUserMapping();
			case LUWPackage.LUW_TYPE_MAPPING: return createLUWTypeMapping();
			case LUWPackage.LUW_USER_MAPPING: return createLUWUserMapping();
			case LUWPackage.LUW_OPTION: return createLUWOption();
			case LUWPackage.LUW_DATABASE: return createLUWDatabase();
			case LUWPackage.LUW_COLUMN: return createLUWColumn();
			case LUWPackage.LUW_GENERIC_NICKNAME: return createLUWGenericNickname();
			case LUWPackage.LUW_GENERIC_SERVER: return createLUWGenericServer();
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE: return createLUWMaterializedQueryTable();
			case LUWPackage.LUW_GENERIC_WRAPPER: return createLUWGenericWrapper();
			case LUWPackage.RELATIONAL_REMOTE_SERVER: return createRelationalRemoteServer();
			case LUWPackage.RELATIONAL_REMOTE_DATA_SET: return createRelationalRemoteDataSet();
			case LUWPackage.REMOTE_SERVER: return createRemoteServer();
			case LUWPackage.REMOTE_DATA_SET: return createRemoteDataSet();
			case LUWPackage.LUW_INDEX: return createLUWIndex();
			case LUWPackage.LUW_ATTRIBUTE_DEFINITION: return createLUWAttributeDefinition();
			case LUWPackage.FEDERATED_PROCEDURE: return createFederatedProcedure();
			case LUWPackage.FEDERATED_PARAMETER: return createFederatedParameter();
			case LUWPackage.LUW_PARTITION_EXPRESSION: return createLUWPartitionExpression();
			case LUWPackage.LUW_PARTITION_ELEMENT: return createLUWPartitionElement();
			case LUWPackage.LUW_DATA_PARTITION: return createLUWDataPartition();
			case LUWPackage.LUW_DATA_PARTITION_KEY: return createLUWDataPartitionKey();
			case LUWPackage.LUW_DATABASE_PACKAGE: return createLUWDatabasePackage();
			case LUWPackage.LUW_MODULE: return createLUWModule();
			case LUWPackage.LUW_MODULE_FUNCTION: return createLUWModuleFunction();
			case LUWPackage.LUW_MODULE_PROCEDURE: return createLUWModuleProcedure();
			case LUWPackage.LUW_MODULE_CONDITION: return createLUWModuleCondition();
			case LUWPackage.LUW_GLOBAL_VARIABLE: return createLUWGlobalVariable();
			case LUWPackage.LUW_MODULE_ROW_DATA_TYPE: return createLUWModuleRowDataType();
			case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE: return createLUWModuleArrayDataType();
			case LUWPackage.LUW_MODULE_DISTINCT_TYPE: return createLUWModuleDistinctType();
			case LUWPackage.LUW_MODULE_GLOBAL_VARIABLE: return createLUWModuleGlobalVariable();
			case LUWPackage.LUW_ARRAY_DATA_TYPE: return createLUWArrayDataType();
			case LUWPackage.LUW_ROW_DATA_TYPE: return createLUWRowDataType();
			case LUWPackage.PLSQL_PACKAGE: return createPLSQLPackage();
			case LUWPackage.PLSQL_PACKAGE_BODY: return createPLSQLPackageBody();
			case LUWPackage.LUW_CURSOR_DATA_TYPE: return createLUWCursorDataType();
			case LUWPackage.LUW_MODULE_CURSOR_DATA_TYPE: return createLUWModuleCursorDataType();
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION: return createLUWBufferPoolSizeException();
			case LUWPackage.LUW_MEMBER: return createLUWMember();
			case LUWPackage.LUW_SECURITY_POLICY: return createLUWSecurityPolicy();
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT: return createLUWSecurityLabelComponent();
			case LUWPackage.LUW_SECURITY_LABEL: return createLUWSecurityLabel();
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT: return createLUWSecurityLabelComponentElement();
			case LUWPackage.LUW_STORAGE_GROUP: return createLUWStorageGroup();
			case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE: return createLUWTemporaryStorageTable();
			case LUWPackage.LUW_TEMPORARY_TABLE: return createLUWTemporaryTable();
			case LUWPackage.ARRAY_INDEX_ELEMENT_TYPE: return createArrayIndexElementType();
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT: return createLUWPartitionEveryClauseElement();
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
			case LUWPackage.LUW_CONTAINER_TYPE:
				return createLUWContainerTypeFromString(eDataType, initialValue);
			case LUWPackage.PAGE_SIZE_TYPE:
				return createPageSizeTypeFromString(eDataType, initialValue);
			case LUWPackage.BUFFER_POOL_TYPE:
				return createBufferPoolTypeFromString(eDataType, initialValue);
			case LUWPackage.TABLE_SPACE_TYPE:
				return createTableSpaceTypeFromString(eDataType, initialValue);
			case LUWPackage.MANAGEMENT_TYPE:
				return createManagementTypeFromString(eDataType, initialValue);
			case LUWPackage.CHECK_OPTION_TYPE:
				return createCheckOptionTypeFromString(eDataType, initialValue);
			case LUWPackage.PARTITION_METHOD:
				return createPartitionMethodFromString(eDataType, initialValue);
			case LUWPackage.MAINTENANCE_TYPE:
				return createMaintenanceTypeFromString(eDataType, initialValue);
			case LUWPackage.REFRESH_TYPE:
				return createRefreshTypeFromString(eDataType, initialValue);
			case LUWPackage.WRAPPER_TYPE:
				return createWrapperTypeFromString(eDataType, initialValue);
			case LUWPackage.DATA_PARTITION_METHOD:
				return createDataPartitionMethodFromString(eDataType, initialValue);
			case LUWPackage.CURSOR_BLOCK_TYPE:
				return createCursorBlockTypeFromString(eDataType, initialValue);
			case LUWPackage.EXPLAIN_SNAPHOT_TYPE:
				return createExplainSnaphotTypeFromString(eDataType, initialValue);
			case LUWPackage.FILE_SYSTEM_CACHING_TYPE:
				return createFileSystemCachingTypeFromString(eDataType, initialValue);
			case LUWPackage.LUW_INDEX_PAGE_SPLIT_TYPE:
				return createLUWIndexPageSplitTypeFromString(eDataType, initialValue);
			case LUWPackage.LUW_INDEX_COMPRESS_TYPE:
				return createLUWIndexCompressTypeFromString(eDataType, initialValue);
			case LUWPackage.SYSTEM_TYPE:
				return createSystemTypeFromString(eDataType, initialValue);
			case LUWPackage.AVERAGE_TABLE_SIZE_TYPE:
				return createAverageTableSizeTypeFromString(eDataType, initialValue);
			case LUWPackage.LUW_STORAGE_TABLE_COMPRESSION_MODE:
				return createLUWStorageTableCompressionModeFromString(eDataType, initialValue);
			case LUWPackage.LUW_MEMBER_TYPE:
				return createLUWMemberTypeFromString(eDataType, initialValue);
			case LUWPackage.MEMBER_STATE_TYPE:
				return createMemberStateTypeFromString(eDataType, initialValue);
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_TYPE:
				return createLUWSecurityLabelComponentTypeFromString(eDataType, initialValue);
			case LUWPackage.LUW_SECURITY_LABEL_NOT_AUTHORIZED_WRITE_ACTION:
				return createLUWSecurityLabelNotAuthorizedWriteActionFromString(eDataType, initialValue);
			case LUWPackage.LUW_FEDERATED_DATA_SOURCE:
				return createLUWFederatedDataSourceFromString(eDataType, initialValue);
			case LUWPackage.LUW_TEMPORARY_TABLE_LOGGING_OPTION:
				return createLUWTemporaryTableLoggingOptionFromString(eDataType, initialValue);
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
			case LUWPackage.LUW_CONTAINER_TYPE:
				return convertLUWContainerTypeToString(eDataType, instanceValue);
			case LUWPackage.PAGE_SIZE_TYPE:
				return convertPageSizeTypeToString(eDataType, instanceValue);
			case LUWPackage.BUFFER_POOL_TYPE:
				return convertBufferPoolTypeToString(eDataType, instanceValue);
			case LUWPackage.TABLE_SPACE_TYPE:
				return convertTableSpaceTypeToString(eDataType, instanceValue);
			case LUWPackage.MANAGEMENT_TYPE:
				return convertManagementTypeToString(eDataType, instanceValue);
			case LUWPackage.CHECK_OPTION_TYPE:
				return convertCheckOptionTypeToString(eDataType, instanceValue);
			case LUWPackage.PARTITION_METHOD:
				return convertPartitionMethodToString(eDataType, instanceValue);
			case LUWPackage.MAINTENANCE_TYPE:
				return convertMaintenanceTypeToString(eDataType, instanceValue);
			case LUWPackage.REFRESH_TYPE:
				return convertRefreshTypeToString(eDataType, instanceValue);
			case LUWPackage.WRAPPER_TYPE:
				return convertWrapperTypeToString(eDataType, instanceValue);
			case LUWPackage.DATA_PARTITION_METHOD:
				return convertDataPartitionMethodToString(eDataType, instanceValue);
			case LUWPackage.CURSOR_BLOCK_TYPE:
				return convertCursorBlockTypeToString(eDataType, instanceValue);
			case LUWPackage.EXPLAIN_SNAPHOT_TYPE:
				return convertExplainSnaphotTypeToString(eDataType, instanceValue);
			case LUWPackage.FILE_SYSTEM_CACHING_TYPE:
				return convertFileSystemCachingTypeToString(eDataType, instanceValue);
			case LUWPackage.LUW_INDEX_PAGE_SPLIT_TYPE:
				return convertLUWIndexPageSplitTypeToString(eDataType, instanceValue);
			case LUWPackage.LUW_INDEX_COMPRESS_TYPE:
				return convertLUWIndexCompressTypeToString(eDataType, instanceValue);
			case LUWPackage.SYSTEM_TYPE:
				return convertSystemTypeToString(eDataType, instanceValue);
			case LUWPackage.AVERAGE_TABLE_SIZE_TYPE:
				return convertAverageTableSizeTypeToString(eDataType, instanceValue);
			case LUWPackage.LUW_STORAGE_TABLE_COMPRESSION_MODE:
				return convertLUWStorageTableCompressionModeToString(eDataType, instanceValue);
			case LUWPackage.LUW_MEMBER_TYPE:
				return convertLUWMemberTypeToString(eDataType, instanceValue);
			case LUWPackage.MEMBER_STATE_TYPE:
				return convertMemberStateTypeToString(eDataType, instanceValue);
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_TYPE:
				return convertLUWSecurityLabelComponentTypeToString(eDataType, instanceValue);
			case LUWPackage.LUW_SECURITY_LABEL_NOT_AUTHORIZED_WRITE_ACTION:
				return convertLUWSecurityLabelNotAuthorizedWriteActionToString(eDataType, instanceValue);
			case LUWPackage.LUW_FEDERATED_DATA_SOURCE:
				return convertLUWFederatedDataSourceToString(eDataType, instanceValue);
			case LUWPackage.LUW_TEMPORARY_TABLE_LOGGING_OPTION:
				return convertLUWTemporaryTableLoggingOptionToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWPartitionGroup createLUWPartitionGroup() {
		LUWPartitionGroupImpl luwPartitionGroup = new LUWPartitionGroupImpl();
		return luwPartitionGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTableSpace createLUWTableSpace() {
		LUWTableSpaceImpl luwTableSpace = new LUWTableSpaceImpl();
		return luwTableSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDatabasePartition createLUWDatabasePartition() {
		LUWDatabasePartitionImpl luwDatabasePartition = new LUWDatabasePartitionImpl();
		return luwDatabasePartition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDatabaseContainer createLUWDatabaseContainer() {
		LUWDatabaseContainerImpl luwDatabaseContainer = new LUWDatabaseContainerImpl();
		return luwDatabaseContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWAdminServer createLUWAdminServer() {
		LUWAdminServerImpl luwAdminServer = new LUWAdminServerImpl();
		return luwAdminServer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWBufferPool createLUWBufferPool() {
		LUWBufferPoolImpl luwBufferPool = new LUWBufferPoolImpl();
		return luwBufferPool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTable createLUWTable() {
		LUWTableImpl luwTable = new LUWTableImpl();
		return luwTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWView createLUWView() {
		LUWViewImpl luwView = new LUWViewImpl();
		return luwView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWPartitionKey createLUWPartitionKey() {
		LUWPartitionKeyImpl luwPartitionKey = new LUWPartitionKeyImpl();
		return luwPartitionKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWFunctionMapping createLUWFunctionMapping() {
		LUWFunctionMappingImpl luwFunctionMapping = new LUWFunctionMappingImpl();
		return luwFunctionMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWGenericUserMapping createLUWGenericUserMapping() {
		LUWGenericUserMappingImpl luwGenericUserMapping = new LUWGenericUserMappingImpl();
		return luwGenericUserMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTypeMapping createLUWTypeMapping() {
		LUWTypeMappingImpl luwTypeMapping = new LUWTypeMappingImpl();
		return luwTypeMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWUserMapping createLUWUserMapping() {
		LUWUserMappingImpl luwUserMapping = new LUWUserMappingImpl();
		return luwUserMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWOption createLUWOption() {
		LUWOptionImpl luwOption = new LUWOptionImpl();
		return luwOption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDatabase createLUWDatabase() {
		LUWDatabaseImpl luwDatabase = new LUWDatabaseImpl();
		return luwDatabase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWColumn createLUWColumn() {
		LUWColumnImpl luwColumn = new LUWColumnImpl();
		return luwColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWGenericNickname createLUWGenericNickname() {
		LUWGenericNicknameImpl luwGenericNickname = new LUWGenericNicknameImpl();
		return luwGenericNickname;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWGenericServer createLUWGenericServer() {
		LUWGenericServerImpl luwGenericServer = new LUWGenericServerImpl();
		return luwGenericServer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWMaterializedQueryTable createLUWMaterializedQueryTable() {
		LUWMaterializedQueryTableImpl luwMaterializedQueryTable = new LUWMaterializedQueryTableImpl();
		return luwMaterializedQueryTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWGenericWrapper createLUWGenericWrapper() {
		LUWGenericWrapperImpl luwGenericWrapper = new LUWGenericWrapperImpl();
		return luwGenericWrapper;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelationalRemoteServer createRelationalRemoteServerGen() {
		RelationalRemoteServerImpl relationalRemoteServer = new RelationalRemoteServerImpl();
		return relationalRemoteServer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public RelationalRemoteServer createRelationalRemoteServer() {
		RelationalRemoteServer relationalRemoteServer = createRelationalRemoteServerGen();
		relationalRemoteServer.eAdapters().add(DatabaseToRemoteServerHelper.INVERSE_DATABASE_ADAPTER);

		// A side effect of the add is to set the target of the INVERSE_DATABASE_ADAPTER 
		// to point back to relationalRemoteServer. This causes garbage collection problems because 
		// INVERSE_DATABASE_ADAPTER is a singleton. Need to unset the target reference...
		DatabaseToRemoteServerHelper.INVERSE_DATABASE_ADAPTER.setTarget(null);
		
		return relationalRemoteServer;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelationalRemoteDataSet createRelationalRemoteDataSetGen() {
		RelationalRemoteDataSetImpl relationalRemoteDataSet = new RelationalRemoteDataSetImpl();
		return relationalRemoteDataSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public RelationalRemoteDataSet createRelationalRemoteDataSet() {
		RelationalRemoteDataSet relationalRemoteDataSet = createRelationalRemoteDataSetGen();
		relationalRemoteDataSet.eAdapters().add(TableToRemoteDataSetHelper.INVERSE_TABLE_ADAPTER);

		// A side effect of the add is to set the target of the INVERSE_TABLE_ADAPTER 
		// to point back to relationalRemoteDataSet. This causes garbage collection problems because 
		// INVERSE_TABLE_ADAPTER is a singleton. Need to unset the target reference...
		TableToRemoteDataSetHelper.INVERSE_TABLE_ADAPTER.setTarget(null);

		return relationalRemoteDataSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemoteServer createRemoteServer() {
		RemoteServerImpl remoteServer = new RemoteServerImpl();
		return remoteServer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemoteDataSet createRemoteDataSet() {
		RemoteDataSetImpl remoteDataSet = new RemoteDataSetImpl();
		return remoteDataSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWIndex createLUWIndex() {
		LUWIndexImpl luwIndex = new LUWIndexImpl();
		return luwIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWAttributeDefinition createLUWAttributeDefinition() {
		LUWAttributeDefinitionImpl luwAttributeDefinition = new LUWAttributeDefinitionImpl();
		return luwAttributeDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FederatedProcedure createFederatedProcedure() {
		FederatedProcedureImpl federatedProcedure = new FederatedProcedureImpl();
		return federatedProcedure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FederatedParameter createFederatedParameter() {
		FederatedParameterImpl federatedParameter = new FederatedParameterImpl();
		return federatedParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWPartitionExpression createLUWPartitionExpression() {
		LUWPartitionExpressionImpl luwPartitionExpression = new LUWPartitionExpressionImpl();
		return luwPartitionExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWPartitionElement createLUWPartitionElement() {
		LUWPartitionElementImpl luwPartitionElement = new LUWPartitionElementImpl();
		return luwPartitionElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDataPartition createLUWDataPartition() {
		LUWDataPartitionImpl luwDataPartition = new LUWDataPartitionImpl();
		return luwDataPartition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDataPartitionKey createLUWDataPartitionKey() {
		LUWDataPartitionKeyImpl luwDataPartitionKey = new LUWDataPartitionKeyImpl();
		return luwDataPartitionKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDatabasePackage createLUWDatabasePackage() {
		LUWDatabasePackageImpl luwDatabasePackage = new LUWDatabasePackageImpl();
		return luwDatabasePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWModule createLUWModule() {
		LUWModuleImpl luwModule = new LUWModuleImpl();
		return luwModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWModuleFunction createLUWModuleFunction() {
		LUWModuleFunctionImpl luwModuleFunction = new LUWModuleFunctionImpl();
		return luwModuleFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWModuleProcedure createLUWModuleProcedure() {
		LUWModuleProcedureImpl luwModuleProcedure = new LUWModuleProcedureImpl();
		return luwModuleProcedure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWModuleCondition createLUWModuleCondition() {
		LUWModuleConditionImpl luwModuleCondition = new LUWModuleConditionImpl();
		return luwModuleCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWGlobalVariable createLUWGlobalVariable() {
		LUWGlobalVariableImpl luwGlobalVariable = new LUWGlobalVariableImpl();
		return luwGlobalVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWModuleRowDataType createLUWModuleRowDataType() {
		LUWModuleRowDataTypeImpl luwModuleRowDataType = new LUWModuleRowDataTypeImpl();
		return luwModuleRowDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWModuleArrayDataType createLUWModuleArrayDataType() {
		LUWModuleArrayDataTypeImpl luwModuleArrayDataType = new LUWModuleArrayDataTypeImpl();
		return luwModuleArrayDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWModuleDistinctType createLUWModuleDistinctType() {
		LUWModuleDistinctTypeImpl luwModuleDistinctType = new LUWModuleDistinctTypeImpl();
		return luwModuleDistinctType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWModuleGlobalVariable createLUWModuleGlobalVariable() {
		LUWModuleGlobalVariableImpl luwModuleGlobalVariable = new LUWModuleGlobalVariableImpl();
		return luwModuleGlobalVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWArrayDataType createLUWArrayDataType() {
		LUWArrayDataTypeImpl luwArrayDataType = new LUWArrayDataTypeImpl();
		return luwArrayDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWRowDataType createLUWRowDataType() {
		LUWRowDataTypeImpl luwRowDataType = new LUWRowDataTypeImpl();
		return luwRowDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PLSQLPackage createPLSQLPackage() {
		PLSQLPackageImpl plsqlPackage = new PLSQLPackageImpl();
		return plsqlPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PLSQLPackageBody createPLSQLPackageBody() {
		PLSQLPackageBodyImpl plsqlPackageBody = new PLSQLPackageBodyImpl();
		return plsqlPackageBody;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWCursorDataType createLUWCursorDataType() {
		LUWCursorDataTypeImpl luwCursorDataType = new LUWCursorDataTypeImpl();
		return luwCursorDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWModuleCursorDataType createLUWModuleCursorDataType() {
		LUWModuleCursorDataTypeImpl luwModuleCursorDataType = new LUWModuleCursorDataTypeImpl();
		return luwModuleCursorDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWBufferPoolSizeException createLUWBufferPoolSizeException() {
		LUWBufferPoolSizeExceptionImpl luwBufferPoolSizeException = new LUWBufferPoolSizeExceptionImpl();
		return luwBufferPoolSizeException;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWMember createLUWMember() {
		LUWMemberImpl luwMember = new LUWMemberImpl();
		return luwMember;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWSecurityPolicy createLUWSecurityPolicy() {
		LUWSecurityPolicyImpl luwSecurityPolicy = new LUWSecurityPolicyImpl();
		return luwSecurityPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWSecurityLabelComponent createLUWSecurityLabelComponent() {
		LUWSecurityLabelComponentImpl luwSecurityLabelComponent = new LUWSecurityLabelComponentImpl();
		return luwSecurityLabelComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWSecurityLabel createLUWSecurityLabel() {
		LUWSecurityLabelImpl luwSecurityLabel = new LUWSecurityLabelImpl();
		return luwSecurityLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWSecurityLabelComponentElement createLUWSecurityLabelComponentElement() {
		LUWSecurityLabelComponentElementImpl luwSecurityLabelComponentElement = new LUWSecurityLabelComponentElementImpl();
		return luwSecurityLabelComponentElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWStorageGroup createLUWStorageGroup() {
		LUWStorageGroupImpl luwStorageGroup = new LUWStorageGroupImpl();
		return luwStorageGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTemporaryStorageTable createLUWTemporaryStorageTable() {
		LUWTemporaryStorageTableImpl luwTemporaryStorageTable = new LUWTemporaryStorageTableImpl();
		return luwTemporaryStorageTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTemporaryTable createLUWTemporaryTable() {
		LUWTemporaryTableImpl luwTemporaryTable = new LUWTemporaryTableImpl();
		return luwTemporaryTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayIndexElementType createArrayIndexElementType() {
		ArrayIndexElementTypeImpl arrayIndexElementType = new ArrayIndexElementTypeImpl();
		return arrayIndexElementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWPartitionEveryClauseElement createLUWPartitionEveryClauseElement() {
		LUWPartitionEveryClauseElementImpl luwPartitionEveryClauseElement = new LUWPartitionEveryClauseElementImpl();
		return luwPartitionEveryClauseElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWContainerType createLUWContainerTypeFromString(EDataType eDataType, String initialValue) {
		LUWContainerType result = LUWContainerType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLUWContainerTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PageSizeType createPageSizeTypeFromString(EDataType eDataType, String initialValue) {
		PageSizeType result = PageSizeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPageSizeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BufferPoolType createBufferPoolTypeFromString(EDataType eDataType, String initialValue) {
		BufferPoolType result = BufferPoolType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBufferPoolTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableSpaceType createTableSpaceTypeFromString(EDataType eDataType, String initialValue) {
		TableSpaceType result = TableSpaceType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTableSpaceTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManagementType createManagementTypeFromString(EDataType eDataType, String initialValue) {
		ManagementType result = ManagementType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertManagementTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckOptionType createCheckOptionTypeFromString(EDataType eDataType, String initialValue) {
		CheckOptionType result = CheckOptionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCheckOptionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartitionMethod createPartitionMethodFromString(EDataType eDataType, String initialValue) {
		PartitionMethod result = PartitionMethod.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPartitionMethodToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MaintenanceType createMaintenanceTypeFromString(EDataType eDataType, String initialValue) {
		MaintenanceType result = MaintenanceType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMaintenanceTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefreshType createRefreshTypeFromString(EDataType eDataType, String initialValue) {
		RefreshType result = RefreshType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRefreshTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WrapperType createWrapperTypeFromString(EDataType eDataType, String initialValue) {
		WrapperType result = WrapperType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWrapperTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataPartitionMethod createDataPartitionMethodFromString(EDataType eDataType, String initialValue) {
		DataPartitionMethod result = DataPartitionMethod.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDataPartitionMethodToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CursorBlockType createCursorBlockTypeFromString(EDataType eDataType, String initialValue) {
		CursorBlockType result = CursorBlockType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCursorBlockTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplainSnaphotType createExplainSnaphotTypeFromString(EDataType eDataType, String initialValue) {
		ExplainSnaphotType result = ExplainSnaphotType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertExplainSnaphotTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileSystemCachingType createFileSystemCachingTypeFromString(EDataType eDataType, String initialValue) {
		FileSystemCachingType result = FileSystemCachingType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFileSystemCachingTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWIndexPageSplitType createLUWIndexPageSplitTypeFromString(EDataType eDataType, String initialValue) {
		LUWIndexPageSplitType result = LUWIndexPageSplitType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLUWIndexPageSplitTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWIndexCompressType createLUWIndexCompressTypeFromString(EDataType eDataType, String initialValue) {
		LUWIndexCompressType result = LUWIndexCompressType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLUWIndexCompressTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemType createSystemTypeFromString(EDataType eDataType, String initialValue) {
		SystemType result = SystemType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSystemTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AverageTableSizeType createAverageTableSizeTypeFromString(EDataType eDataType, String initialValue) {
		AverageTableSizeType result = AverageTableSizeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAverageTableSizeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWStorageTableCompressionMode createLUWStorageTableCompressionModeFromString(EDataType eDataType, String initialValue) {
		LUWStorageTableCompressionMode result = LUWStorageTableCompressionMode.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLUWStorageTableCompressionModeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWMemberType createLUWMemberTypeFromString(EDataType eDataType, String initialValue) {
		LUWMemberType result = LUWMemberType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLUWMemberTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MemberStateType createMemberStateTypeFromString(EDataType eDataType, String initialValue) {
		MemberStateType result = MemberStateType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMemberStateTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWSecurityLabelComponentType createLUWSecurityLabelComponentTypeFromString(EDataType eDataType, String initialValue) {
		LUWSecurityLabelComponentType result = LUWSecurityLabelComponentType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLUWSecurityLabelComponentTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWSecurityLabelNotAuthorizedWriteAction createLUWSecurityLabelNotAuthorizedWriteActionFromString(EDataType eDataType, String initialValue) {
		LUWSecurityLabelNotAuthorizedWriteAction result = LUWSecurityLabelNotAuthorizedWriteAction.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLUWSecurityLabelNotAuthorizedWriteActionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWFederatedDataSource createLUWFederatedDataSourceFromString(EDataType eDataType, String initialValue) {
		LUWFederatedDataSource result = LUWFederatedDataSource.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLUWFederatedDataSourceToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTemporaryTableLoggingOption createLUWTemporaryTableLoggingOptionFromString(EDataType eDataType, String initialValue) {
		LUWTemporaryTableLoggingOption result = LUWTemporaryTableLoggingOption.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLUWTemporaryTableLoggingOptionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWPackage getLUWPackage() {
		return (LUWPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static LUWPackage getPackage() {
		return LUWPackage.eINSTANCE;
	}

} //LUWFactoryImpl
