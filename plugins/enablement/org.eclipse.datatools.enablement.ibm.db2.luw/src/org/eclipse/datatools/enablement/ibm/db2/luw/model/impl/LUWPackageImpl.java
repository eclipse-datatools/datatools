package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;


import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.ArrayIndexElementType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.AverageTableSizeType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.BufferPoolType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.CheckOptionType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.CursorBlockType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.DataPartitionMethod;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.ExplainSnaphotType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.FileSystemCachingType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAdminServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAttributeDefinition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWContainerType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWCursorDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFactory;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFederatedDataSource;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericUserMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGlobalVariable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexCompressType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexPageSplitType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMaterializedQueryTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMemberType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleArrayDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCondition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCursorDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleDistinctType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleFunction;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleGlobalVariable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleProcedure;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleRowDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionEveryClauseElement;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRowDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelNotAuthorizedWriteAction;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTableCompressionMode;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTableLoggingOption;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.MaintenanceType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.ManagementType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.MemberStateType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackageBody;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PageSizeType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PartitionMethod;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RefreshType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteDataSet;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteDataSet;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.SystemType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.TableSpaceType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.WrapperType;
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
import org.eclipse.emf.ecore.EOperation;
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
public class LUWPackageImpl extends EPackageImpl implements LUWPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwPartitionGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwTableSpaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwDatabasePartitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwDatabaseContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwAdminServerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwBufferPoolEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwViewEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwPartitionKeyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwNicknameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwFunctionMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwWrapperEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwNonRelationalNicknameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwNonRelationalServerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwNonRelationalWrapperEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwRelationalNicknameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwGenericUserMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwRelationalWrapperEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwServerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwTypeMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwUserMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwOptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwRelationalServerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwDatabaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwColumnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwGenericNicknameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwGenericServerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwMaterializedQueryTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwGenericWrapperEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwStorageTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationalRemoteServerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationalRemoteDataSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass remoteServerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass remoteDataSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwIndexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwAttributeDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass federatedProcedureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass federatedParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwPartitionExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwPartitionElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwDataPartitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwDataPartitionKeyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwDatabasePackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwModuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwModuleObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwModuleFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwModuleProcedureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwModuleConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwGlobalVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwModuleTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwModuleRowDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwModuleArrayDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwModuleDistinctTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwModuleGlobalVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwArrayDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwRowDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass plsqlPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass plsqlPackageBodyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwCursorDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwModuleCursorDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwBufferPoolSizeExceptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwMemberEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwSecurityPolicyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwSecurityLabelComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwSecurityLabelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwSecurityLabelComponentElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwStorageGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwTemporaryStorageTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwTemporaryTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayIndexElementTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luwPartitionEveryClauseElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum luwContainerTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum pageSizeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum bufferPoolTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tableSpaceTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum managementTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum checkOptionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum partitionMethodEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum maintenanceTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum refreshTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum wrapperTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dataPartitionMethodEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum cursorBlockTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum explainSnaphotTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum fileSystemCachingTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum luwIndexPageSplitTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum luwIndexCompressTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum systemTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum averageTableSizeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum luwStorageTableCompressionModeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum luwMemberTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum memberStateTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum luwSecurityLabelComponentTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum luwSecurityLabelNotAuthorizedWriteActionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum luwFederatedDataSourceEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum luwTemporaryTableLoggingOptionEEnum = null;

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
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private LUWPackageImpl() {
		super(eNS_URI, LUWFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link LUWPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static LUWPackage init() {
		if (isInited) return (LUWPackage)EPackage.Registry.INSTANCE.getEPackage(LUWPackage.eNS_URI);

		// Obtain or create and register package
		LUWPackageImpl theLUWPackage = (LUWPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof LUWPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new LUWPackageImpl());

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

		// Obtain or create and register interdependencies
		DB2ModelPackageImpl theDB2ModelPackage = (DB2ModelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DB2ModelPackage.eNS_URI) instanceof DB2ModelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DB2ModelPackage.eNS_URI) : DB2ModelPackage.eINSTANCE);

		// Create package meta-data objects
		theLUWPackage.createPackageContents();
		theDB2ModelPackage.createPackageContents();

		// Initialize created meta-data
		theLUWPackage.initializePackageContents();
		theDB2ModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theLUWPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(LUWPackage.eNS_URI, theLUWPackage);
		return theLUWPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWPartitionGroup() {
		return luwPartitionGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWPartitionGroup_Partitions() {
		return (EReference)luwPartitionGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWPartitionGroup_TableSpaces() {
		return (EReference)luwPartitionGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWPartitionGroup_Database() {
		return (EReference)luwPartitionGroupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWPartitionGroup_BufferPool() {
		return (EReference)luwPartitionGroupEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWTableSpace() {
		return luwTableSpaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTableSpace_TemporaryStorageTables() {
		return (EReference)luwTableSpaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_TablespaceType() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_ManagementType() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_ExtentSize() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_PreFetchSize() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_Overhead() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_TransferRate() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_RecoverDroppedTableOn() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_PageSize() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_Size() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_AutoResize() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_InitialSize() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_IncreaseSize() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_MaximumSize() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_InitialSizeUnit() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_MaximumSizeUnit() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_IncreaseSizeUnit() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_IncreasePercent() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_FileSystemCaching() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_AverageSeekTime() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_RotationSpeed() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_Transfer() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_SystemType() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_AverageTableSize() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_ExternalContainerCount() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_InheritOverhead() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_InheritTransferate() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_Rebalance() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_DataTag() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_SuspendRebalance() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTableSpace_ResumeRebalance() {
		return (EAttribute)luwTableSpaceEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTableSpace_Group() {
		return (EReference)luwTableSpaceEClass.getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTableSpace_Containers() {
		return (EReference)luwTableSpaceEClass.getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTableSpace_BufferPool() {
		return (EReference)luwTableSpaceEClass.getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTableSpace_IndexDataTables() {
		return (EReference)luwTableSpaceEClass.getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTableSpace_LOBDataTables() {
		return (EReference)luwTableSpaceEClass.getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTableSpace_RegularDataTables() {
		return (EReference)luwTableSpaceEClass.getEStructuralFeatures().get(36);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTableSpace_Database() {
		return (EReference)luwTableSpaceEClass.getEStructuralFeatures().get(37);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTableSpace_LOBDataPartition() {
		return (EReference)luwTableSpaceEClass.getEStructuralFeatures().get(38);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTableSpace_RegularDataPartition() {
		return (EReference)luwTableSpaceEClass.getEStructuralFeatures().get(39);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTableSpace_Indexes() {
		return (EReference)luwTableSpaceEClass.getEStructuralFeatures().get(40);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTableSpace_IndexDataPartition() {
		return (EReference)luwTableSpaceEClass.getEStructuralFeatures().get(41);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTableSpace_StorageGroup() {
		return (EReference)luwTableSpaceEClass.getEStructuralFeatures().get(42);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWDatabasePartition() {
		return luwDatabasePartitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabasePartition_Number() {
		return (EAttribute)luwDatabasePartitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabasePartition_PortNumber() {
		return (EAttribute)luwDatabasePartitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabasePartition_HostName() {
		return (EAttribute)luwDatabasePartitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabasePartition_SwitchName() {
		return (EAttribute)luwDatabasePartitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabasePartition_CatalogPartition() {
		return (EAttribute)luwDatabasePartitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabasePartition_Group() {
		return (EReference)luwDatabasePartitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabasePartition_BufferPool() {
		return (EReference)luwDatabasePartitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabasePartition_Containers() {
		return (EReference)luwDatabasePartitionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabasePartition_SizeException() {
		return (EReference)luwDatabasePartitionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWDatabaseContainer() {
		return luwDatabaseContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabaseContainer_ContainerType() {
		return (EAttribute)luwDatabaseContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabaseContainer_SizeInPages() {
		return (EAttribute)luwDatabaseContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabaseContainer_Size() {
		return (EAttribute)luwDatabaseContainerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabaseContainer_SizeUnits() {
		return (EAttribute)luwDatabaseContainerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabaseContainer_TableSpace() {
		return (EReference)luwDatabaseContainerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabaseContainer_Partitions() {
		return (EReference)luwDatabaseContainerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWAdminServer() {
		return luwAdminServerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWAdminServer_Instances() {
		return (EReference)luwAdminServerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWBufferPool() {
		return luwBufferPoolEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWBufferPool_CreateType() {
		return (EAttribute)luwBufferPoolEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWBufferPool_Size() {
		return (EAttribute)luwBufferPoolEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWBufferPool_PageSize() {
		return (EAttribute)luwBufferPoolEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWBufferPool_BlockSize() {
		return (EAttribute)luwBufferPoolEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWBufferPool_NumBlockPages() {
		return (EAttribute)luwBufferPoolEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWBufferPool_ExtendedStorage() {
		return (EAttribute)luwBufferPoolEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWBufferPool_Automatic() {
		return (EAttribute)luwBufferPoolEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWBufferPool_TableSpaces() {
		return (EReference)luwBufferPoolEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWBufferPool_Partitions() {
		return (EReference)luwBufferPoolEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWBufferPool_PartitionGroup() {
		return (EReference)luwBufferPoolEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWBufferPool_Database() {
		return (EReference)luwBufferPoolEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWBufferPool_SizeException() {
		return (EReference)luwBufferPoolEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWTable() {
		return luwTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTable_PCTFree() {
		return (EAttribute)luwTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTable_RestrictOnDrop() {
		return (EAttribute)luwTableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTable_PartitionMode() {
		return (EAttribute)luwTableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTable_AppendMode() {
		return (EAttribute)luwTableEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTable_LogMode() {
		return (EAttribute)luwTableEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTable_LockSizeRow() {
		return (EAttribute)luwTableEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTable_Volatile() {
		return (EAttribute)luwTableEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTable_SecurityPolicy() {
		return (EReference)luwTableEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTable_Options() {
		return (EReference)luwTableEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWView() {
		return luwViewEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWView_Federated() {
		return (EAttribute)luwViewEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWView_OptimizeQuery() {
		return (EAttribute)luwViewEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWPartitionKey() {
		return luwPartitionKeyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWPartitionKey_TemporaryStorageTable() {
		return (EReference)luwPartitionKeyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWPartitionKey_PartitionMethod() {
		return (EAttribute)luwPartitionKeyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWPartitionKey_Table() {
		return (EReference)luwPartitionKeyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWPartitionKey_Columns() {
		return (EReference)luwPartitionKeyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWNickname() {
		return luwNicknameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWNickname_RemoteDataSet() {
		return (EReference)luwNicknameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWNickname_Server() {
		return (EReference)luwNicknameEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWFunctionMapping() {
		return luwFunctionMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWFunctionMapping_ServerType() {
		return (EAttribute)luwFunctionMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWFunctionMapping_ServerVersion() {
		return (EAttribute)luwFunctionMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWFunctionMapping_ServerName() {
		return (EAttribute)luwFunctionMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWFunctionMapping_CreationTime() {
		return (EAttribute)luwFunctionMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWFunctionMapping_Disabled() {
		return (EAttribute)luwFunctionMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWFunctionMapping_InstsPerInvoc() {
		return (EAttribute)luwFunctionMappingEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWFunctionMapping_InstsPerArgByte() {
		return (EAttribute)luwFunctionMappingEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWFunctionMapping_IosPerInvoc() {
		return (EAttribute)luwFunctionMappingEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWFunctionMapping_IosPerArgByte() {
		return (EAttribute)luwFunctionMappingEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWFunctionMapping_Options() {
		return (EReference)luwFunctionMappingEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWFunctionMapping_LocalFunction() {
		return (EReference)luwFunctionMappingEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWFunctionMapping_RemoteFunction() {
		return (EReference)luwFunctionMappingEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWFunctionMapping_LUWDatabase() {
		return (EReference)luwFunctionMappingEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWWrapper() {
		return luwWrapperEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWWrapper_Version() {
		return (EAttribute)luwWrapperEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWWrapper_Library() {
		return (EAttribute)luwWrapperEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWWrapper_Fenced() {
		return (EAttribute)luwWrapperEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWWrapper_WrapperType() {
		return (EAttribute)luwWrapperEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWWrapper_DataSource() {
		return (EAttribute)luwWrapperEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWWrapper_DiscoveredLibraries() {
		return (EAttribute)luwWrapperEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWWrapper_Servers() {
		return (EReference)luwWrapperEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWWrapper_LUWDatabase() {
		return (EReference)luwWrapperEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWWrapper_Options() {
		return (EReference)luwWrapperEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWNonRelationalNickname() {
		return luwNonRelationalNicknameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWNonRelationalNickname_NonRelServer() {
		return (EReference)luwNonRelationalNicknameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWNonRelationalServer() {
		return luwNonRelationalServerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWNonRelationalServer_NonRelWrapper() {
		return (EReference)luwNonRelationalServerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWNonRelationalServer_NonRelNicknames() {
		return (EReference)luwNonRelationalServerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWNonRelationalWrapper() {
		return luwNonRelationalWrapperEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWNonRelationalWrapper_NonRelServers() {
		return (EReference)luwNonRelationalWrapperEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWRelationalNickname() {
		return luwRelationalNicknameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWRelationalNickname_RelServer() {
		return (EReference)luwRelationalNicknameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWGenericUserMapping() {
		return luwGenericUserMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWGenericUserMapping_RemoteUser() {
		return (EAttribute)luwGenericUserMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWGenericUserMapping_RemotePassword() {
		return (EAttribute)luwGenericUserMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWRelationalWrapper() {
		return luwRelationalWrapperEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWRelationalWrapper_RelServers() {
		return (EReference)luwRelationalWrapperEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWServer() {
		return luwServerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWServer_ServerType() {
		return (EAttribute)luwServerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWServer_ServerVersion() {
		return (EAttribute)luwServerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWServer_UserMappings() {
		return (EReference)luwServerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWServer_Wrapper() {
		return (EReference)luwServerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWServer_Nicknames() {
		return (EReference)luwServerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWServer_LUWDatabase() {
		return (EReference)luwServerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWServer_Options() {
		return (EReference)luwServerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWServer_RemoteServer() {
		return (EReference)luwServerEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWTypeMapping() {
		return luwTypeMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTypeMapping_ServerType() {
		return (EAttribute)luwTypeMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTypeMapping_ServerVesion() {
		return (EAttribute)luwTypeMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTypeMapping_ServerName() {
		return (EAttribute)luwTypeMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTypeMapping_CreationTime() {
		return (EAttribute)luwTypeMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTypeMapping_LocalType() {
		return (EReference)luwTypeMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTypeMapping_RemoteType() {
		return (EReference)luwTypeMappingEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWUserMapping() {
		return luwUserMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWUserMapping_LocalAuthId() {
		return (EAttribute)luwUserMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWUserMapping_Server() {
		return (EReference)luwUserMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWUserMapping_Options() {
		return (EReference)luwUserMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWOption() {
		return luwOptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWOption_Value() {
		return (EAttribute)luwOptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWRelationalServer() {
		return luwRelationalServerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWRelationalServer_CpuRatio() {
		return (EAttribute)luwRelationalServerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWRelationalServer_IoRatio() {
		return (EAttribute)luwRelationalServerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWRelationalServer_CommRate() {
		return (EAttribute)luwRelationalServerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWRelationalServer_FoldId() {
		return (EAttribute)luwRelationalServerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWRelationalServer_FoldPW() {
		return (EAttribute)luwRelationalServerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWRelationalServer_CollatingSequence() {
		return (EAttribute)luwRelationalServerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWRelationalServer_Pushdown() {
		return (EAttribute)luwRelationalServerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWRelationalServer_Node() {
		return (EAttribute)luwRelationalServerEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWRelationalServer_DbName() {
		return (EAttribute)luwRelationalServerEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWRelationalServer_IudAppSvptEnforce() {
		return (EAttribute)luwRelationalServerEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWRelationalServer_Password() {
		return (EAttribute)luwRelationalServerEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWRelationalServer_RelNicknames() {
		return (EReference)luwRelationalServerEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWRelationalServer_RelWrapper() {
		return (EReference)luwRelationalServerEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWDatabase() {
		return luwDatabaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabase_Federated() {
		return (EAttribute)luwDatabaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabase_Groups() {
		return (EReference)luwDatabaseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabase_Wrappers() {
		return (EReference)luwDatabaseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabase_Servers() {
		return (EReference)luwDatabaseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabase_FunctionMappings() {
		return (EReference)luwDatabaseEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabase_TypeMappings() {
		return (EReference)luwDatabaseEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabase_ReverseTypeMappings() {
		return (EReference)luwDatabaseEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabase_Bufferpools() {
		return (EReference)luwDatabaseEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabase_Tablespaces() {
		return (EReference)luwDatabaseEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabase_StorageGroups() {
		return (EReference)luwDatabaseEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDatabase_DefaultStorageGroup() {
		return (EReference)luwDatabaseEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWColumn() {
		return luwColumnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWColumn_LobLogged() {
		return (EAttribute)luwColumnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWColumn_LobCompacted() {
		return (EAttribute)luwColumnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWColumn_Compression() {
		return (EAttribute)luwColumnEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWColumn_InlineLength() {
		return (EAttribute)luwColumnEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWColumn_Hidden() {
		return (EAttribute)luwColumnEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWColumn_SecurityLabel() {
		return (EAttribute)luwColumnEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWColumn_Options() {
		return (EReference)luwColumnEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWGenericNickname() {
		return luwGenericNicknameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWGenericNickname_GenericServer() {
		return (EReference)luwGenericNicknameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWGenericServer() {
		return luwGenericServerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWGenericServer_GenericNicknames() {
		return (EReference)luwGenericServerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWGenericServer_GenericWrapper() {
		return (EReference)luwGenericServerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWMaterializedQueryTable() {
		return luwMaterializedQueryTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWGenericWrapper() {
		return luwGenericWrapperEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWGenericWrapper_GenericServers() {
		return (EReference)luwGenericWrapperEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWStorageTable() {
		return luwStorageTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWStorageTable_ValueCompression() {
		return (EAttribute)luwStorageTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWStorageTable_RowCompression() {
		return (EAttribute)luwStorageTableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWStorageTable_RowCompressionEmpty() {
		return (EAttribute)luwStorageTableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWStorageTable_CompressionMode() {
		return (EAttribute)luwStorageTableEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWStorageTable_PartitionKey() {
		return (EReference)luwStorageTableEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWStorageTable_IndexDataTableSpace() {
		return (EReference)luwStorageTableEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWStorageTable_LOBDataTableSpace() {
		return (EReference)luwStorageTableEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWStorageTable_RegularDataTableSpace() {
		return (EReference)luwStorageTableEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWStorageTable_DataPartitions() {
		return (EReference)luwStorageTableEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWStorageTable_DataPartitionKey() {
		return (EReference)luwStorageTableEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelationalRemoteServer() {
		return relationalRemoteServerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelationalRemoteServer_Database() {
		return (EReference)relationalRemoteServerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelationalRemoteDataSet() {
		return relationalRemoteDataSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelationalRemoteDataSet_Table() {
		return (EReference)relationalRemoteDataSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRemoteServer() {
		return remoteServerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRemoteServer_LUWServer() {
		return (EReference)remoteServerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRemoteDataSet() {
		return remoteDataSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRemoteDataSet_Nickname() {
		return (EReference)remoteDataSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWIndex() {
		return luwIndexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_PCTFree() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_MinPCTFree() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_ReverseScan() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_NotPartitioned() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_XmlPattern() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWIndex_AsSQLDataType() {
		return (EReference)luwIndexEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_AsSQLDataTypeHashed() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_SystemRequired() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_PageSplitType() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_Level2PctFree() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_MinPctUsed() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_Compress() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_CollectStats() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_SampledStats() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_DetailedStats() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_IgnoreInvalidValues() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWIndex_ExcludeNullKeys() {
		return (EAttribute)luwIndexEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWIndex_Tablespace() {
		return (EReference)luwIndexEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWAttributeDefinition() {
		return luwAttributeDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWAttributeDefinition_LOBLogged() {
		return (EAttribute)luwAttributeDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWAttributeDefinition_LOBCompacted() {
		return (EAttribute)luwAttributeDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFederatedProcedure() {
		return federatedProcedureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFederatedProcedure_RemoteUniqueId() {
		return (EAttribute)federatedProcedureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFederatedProcedure_RemoteServer() {
		return (EAttribute)federatedProcedureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFederatedProcedure_RemoteSchema() {
		return (EAttribute)federatedProcedureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFederatedProcedure_RemotePackage() {
		return (EAttribute)federatedProcedureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFederatedProcedure_RemoteProcedureName() {
		return (EAttribute)federatedProcedureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFederatedProcedure_NumberOfParameters() {
		return (EAttribute)federatedProcedureEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFederatedProcedure_ResultSetsToClient() {
		return (EAttribute)federatedProcedureEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFederatedProcedure_NumberOfRefCursors() {
		return (EAttribute)federatedProcedureEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFederatedProcedure_AllResultSetsToCaller() {
		return (EAttribute)federatedProcedureEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFederatedProcedure_FederatedProcedure() {
		return (EReference)federatedProcedureEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFederatedProcedure_RemoteProcedure() {
		return (EReference)federatedProcedureEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFederatedProcedure_FederatedParameter() {
		return (EReference)federatedProcedureEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFederatedParameter() {
		return federatedParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFederatedParameter_RemoteCodePage() {
		return (EAttribute)federatedParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFederatedParameter_RemoteParamTypeID() {
		return (EAttribute)federatedParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFederatedParameter_FederatedProcedure() {
		return (EReference)federatedParameterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFederatedParameter_RemoteParameter() {
		return (EReference)federatedParameterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWPartitionExpression() {
		return luwPartitionExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWPartitionExpression_NullsLast() {
		return (EAttribute)luwPartitionExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWPartitionExpression_Key() {
		return (EReference)luwPartitionExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWPartitionExpression_Column() {
		return (EReference)luwPartitionExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWPartitionExpression_PartitionElements() {
		return (EReference)luwPartitionExpressionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWPartitionElement() {
		return luwPartitionElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWPartitionElement_Starting() {
		return (EAttribute)luwPartitionElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWPartitionElement_Ending() {
		return (EAttribute)luwPartitionElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWPartitionElement_LUWPartitionExpression() {
		return (EReference)luwPartitionElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWPartitionElement_Partition() {
		return (EReference)luwPartitionElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWPartitionElement_EveryClause() {
		return (EReference)luwPartitionElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWDataPartition() {
		return luwDataPartitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDataPartition_Id() {
		return (EAttribute)luwDataPartitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDataPartition_LowInclusive() {
		return (EAttribute)luwDataPartitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDataPartition_HighInclusive() {
		return (EAttribute)luwDataPartitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDataPartition_LOBDataTableSpace() {
		return (EReference)luwDataPartitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDataPartition_RegularDataTableSpace() {
		return (EReference)luwDataPartitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDataPartition_PartitionElements() {
		return (EReference)luwDataPartitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDataPartition_Table() {
		return (EReference)luwDataPartitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDataPartition_IndexDataTableSpace() {
		return (EReference)luwDataPartitionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWDataPartitionKey() {
		return luwDataPartitionKeyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDataPartitionKey_PartitionMethod() {
		return (EAttribute)luwDataPartitionKeyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDataPartitionKey_PartitionExpressions() {
		return (EReference)luwDataPartitionKeyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWDataPartitionKey_Table() {
		return (EReference)luwDataPartitionKeyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWDatabasePackage() {
		return luwDatabasePackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabasePackage_Creator() {
		return (EAttribute)luwDatabasePackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabasePackage_Binder() {
		return (EAttribute)luwDatabasePackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabasePackage_CursorBlock() {
		return (EAttribute)luwDatabasePackageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabasePackage_NumberOfSections() {
		return (EAttribute)luwDatabasePackageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabasePackage_OptimizationClass() {
		return (EAttribute)luwDatabasePackageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWDatabasePackage_ExplainSnapshot() {
		return (EAttribute)luwDatabasePackageEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWModule() {
		return luwModuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWModule_Dialect() {
		return (EAttribute)luwModuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWModule_OwningSchema() {
		return (EReference)luwModuleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWModule_ModuleObjects() {
		return (EReference)luwModuleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWModuleObject() {
		return luwModuleObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWModuleObject_Published() {
		return (EAttribute)luwModuleObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWModuleObject_Module() {
		return (EReference)luwModuleObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWModuleFunction() {
		return luwModuleFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWModuleFunction_Implemented() {
		return (EAttribute)luwModuleFunctionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWModuleProcedure() {
		return luwModuleProcedureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWModuleProcedure_Implemented() {
		return (EAttribute)luwModuleProcedureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWModuleCondition() {
		return luwModuleConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWModuleCondition_Sqlstate() {
		return (EAttribute)luwModuleConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWGlobalVariable() {
		return luwGlobalVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWGlobalVariable_DefaultValue() {
		return (EAttribute)luwGlobalVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWGlobalVariable_IsConstant() {
		return (EAttribute)luwGlobalVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWGlobalVariable_Schema() {
		return (EReference)luwGlobalVariableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWModuleType() {
		return luwModuleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWModuleRowDataType() {
		return luwModuleRowDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWModuleArrayDataType() {
		return luwModuleArrayDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWModuleDistinctType() {
		return luwModuleDistinctTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWModuleGlobalVariable() {
		return luwModuleGlobalVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWArrayDataType() {
		return luwArrayDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWArrayDataType_ArrayIndexElementType() {
		return (EReference)luwArrayDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWRowDataType() {
		return luwRowDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPLSQLPackage() {
		return plsqlPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPLSQLPackage_PackageBody() {
		return (EReference)plsqlPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPLSQLPackageBody() {
		return plsqlPackageBodyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPLSQLPackageBody_Package() {
		return (EReference)plsqlPackageBodyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWCursorDataType() {
		return luwCursorDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWCursorDataType_RowType() {
		return (EReference)luwCursorDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWModuleCursorDataType() {
		return luwModuleCursorDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWBufferPoolSizeException() {
		return luwBufferPoolSizeExceptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWBufferPoolSizeException_Size() {
		return (EAttribute)luwBufferPoolSizeExceptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWBufferPoolSizeException_BufferPool() {
		return (EReference)luwBufferPoolSizeExceptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWBufferPoolSizeException_Partitions() {
		return (EReference)luwBufferPoolSizeExceptionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWMember() {
		return luwMemberEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWMember_Type() {
		return (EAttribute)luwMemberEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWMember_Alert() {
		return (EAttribute)luwMemberEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWMember_DbPartitionNum() {
		return (EAttribute)luwMemberEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWMember_LogicalPort() {
		return (EAttribute)luwMemberEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWMember_NetName() {
		return (EAttribute)luwMemberEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWSecurityPolicy() {
		return luwSecurityPolicyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWSecurityPolicy_NotAuthorizedWrite() {
		return (EAttribute)luwSecurityPolicyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWSecurityPolicy_Components() {
		return (EReference)luwSecurityPolicyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWSecurityPolicy_Labels() {
		return (EReference)luwSecurityPolicyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWSecurityPolicy_Table() {
		return (EReference)luwSecurityPolicyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWSecurityLabelComponent() {
		return luwSecurityLabelComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWSecurityLabelComponent_Type() {
		return (EAttribute)luwSecurityLabelComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWSecurityLabelComponent_LUWSecurityPolicy() {
		return (EReference)luwSecurityLabelComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWSecurityLabelComponent_Elements() {
		return (EReference)luwSecurityLabelComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWSecurityLabel() {
		return luwSecurityLabelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWSecurityLabel_SecurityLabel() {
		return (EAttribute)luwSecurityLabelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWSecurityLabel_Policy() {
		return (EReference)luwSecurityLabelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWSecurityLabelComponentElement() {
		return luwSecurityLabelComponentElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWSecurityLabelComponentElement_Value() {
		return (EAttribute)luwSecurityLabelComponentElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWSecurityLabelComponentElement_ParentValue() {
		return (EAttribute)luwSecurityLabelComponentElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWSecurityLabelComponentElement_LUWSecurityLabelComponent() {
		return (EReference)luwSecurityLabelComponentElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWStorageGroup() {
		return luwStorageGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWStorageGroup_StoragePaths() {
		return (EAttribute)luwStorageGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWStorageGroup_Overhead() {
		return (EAttribute)luwStorageGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWStorageGroup_DeviceReadRate() {
		return (EAttribute)luwStorageGroupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWStorageGroup_DataTag() {
		return (EAttribute)luwStorageGroupEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWStorageGroup_Default() {
		return (EAttribute)luwStorageGroupEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWStorageGroup_Database() {
		return (EReference)luwStorageGroupEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWStorageGroup_TableSpaces() {
		return (EReference)luwStorageGroupEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWTemporaryStorageTable() {
		return luwTemporaryStorageTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTemporaryStorageTable_PartitionKey() {
		return (EReference)luwTemporaryStorageTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTemporaryStorageTable_UserTemporaryTableSpace() {
		return (EReference)luwTemporaryStorageTableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWTemporaryTable() {
		return luwTemporaryTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWTemporaryTable_Table() {
		return (EReference)luwTemporaryTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWTemporaryTable_LogOption() {
		return (EAttribute)luwTemporaryTableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayIndexElementType() {
		return arrayIndexElementTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayIndexElementType_LUWArrayDataType() {
		return (EReference)arrayIndexElementTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLUWPartitionEveryClauseElement() {
		return luwPartitionEveryClauseElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWPartitionEveryClauseElement_Value() {
		return (EAttribute)luwPartitionEveryClauseElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLUWPartitionEveryClauseElement_Duration() {
		return (EAttribute)luwPartitionEveryClauseElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLUWPartitionEveryClauseElement_LUWPartitionElement() {
		return (EReference)luwPartitionEveryClauseElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLUWContainerType() {
		return luwContainerTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPageSizeType() {
		return pageSizeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBufferPoolType() {
		return bufferPoolTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTableSpaceType() {
		return tableSpaceTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getManagementType() {
		return managementTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCheckOptionType() {
		return checkOptionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPartitionMethod() {
		return partitionMethodEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getMaintenanceType() {
		return maintenanceTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getRefreshType() {
		return refreshTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getWrapperType() {
		return wrapperTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDataPartitionMethod() {
		return dataPartitionMethodEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCursorBlockType() {
		return cursorBlockTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getExplainSnaphotType() {
		return explainSnaphotTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getFileSystemCachingType() {
		return fileSystemCachingTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLUWIndexPageSplitType() {
		return luwIndexPageSplitTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLUWIndexCompressType() {
		return luwIndexCompressTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSystemType() {
		return systemTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAverageTableSizeType() {
		return averageTableSizeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLUWStorageTableCompressionMode() {
		return luwStorageTableCompressionModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLUWMemberType() {
		return luwMemberTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getMemberStateType() {
		return memberStateTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLUWSecurityLabelComponentType() {
		return luwSecurityLabelComponentTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLUWSecurityLabelNotAuthorizedWriteAction() {
		return luwSecurityLabelNotAuthorizedWriteActionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLUWFederatedDataSource() {
		return luwFederatedDataSourceEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLUWTemporaryTableLoggingOption() {
		return luwTemporaryTableLoggingOptionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWFactory getLUWFactory() {
		return (LUWFactory)getEFactoryInstance();
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
		luwPartitionGroupEClass = createEClass(LUW_PARTITION_GROUP);
		createEReference(luwPartitionGroupEClass, LUW_PARTITION_GROUP__PARTITIONS);
		createEReference(luwPartitionGroupEClass, LUW_PARTITION_GROUP__TABLE_SPACES);
		createEReference(luwPartitionGroupEClass, LUW_PARTITION_GROUP__DATABASE);
		createEReference(luwPartitionGroupEClass, LUW_PARTITION_GROUP__BUFFER_POOL);

		luwTableSpaceEClass = createEClass(LUW_TABLE_SPACE);
		createEReference(luwTableSpaceEClass, LUW_TABLE_SPACE__TEMPORARY_STORAGE_TABLES);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__TABLESPACE_TYPE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__MANAGEMENT_TYPE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__EXTENT_SIZE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__PRE_FETCH_SIZE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__OVERHEAD);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__TRANSFER_RATE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__RECOVER_DROPPED_TABLE_ON);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__PAGE_SIZE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__SIZE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__AUTO_RESIZE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__INITIAL_SIZE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__INCREASE_SIZE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__MAXIMUM_SIZE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__INITIAL_SIZE_UNIT);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__MAXIMUM_SIZE_UNIT);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__INCREASE_SIZE_UNIT);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__INCREASE_PERCENT);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__FILE_SYSTEM_CACHING);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__AVERAGE_SEEK_TIME);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__ROTATION_SPEED);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__TRANSFER);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__SYSTEM_TYPE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__AVERAGE_TABLE_SIZE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__EXTERNAL_CONTAINER_COUNT);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__INHERIT_OVERHEAD);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__INHERIT_TRANSFERATE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__REBALANCE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__DATA_TAG);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__SUSPEND_REBALANCE);
		createEAttribute(luwTableSpaceEClass, LUW_TABLE_SPACE__RESUME_REBALANCE);
		createEReference(luwTableSpaceEClass, LUW_TABLE_SPACE__GROUP);
		createEReference(luwTableSpaceEClass, LUW_TABLE_SPACE__CONTAINERS);
		createEReference(luwTableSpaceEClass, LUW_TABLE_SPACE__BUFFER_POOL);
		createEReference(luwTableSpaceEClass, LUW_TABLE_SPACE__INDEX_DATA_TABLES);
		createEReference(luwTableSpaceEClass, LUW_TABLE_SPACE__LOB_DATA_TABLES);
		createEReference(luwTableSpaceEClass, LUW_TABLE_SPACE__REGULAR_DATA_TABLES);
		createEReference(luwTableSpaceEClass, LUW_TABLE_SPACE__DATABASE);
		createEReference(luwTableSpaceEClass, LUW_TABLE_SPACE__LOB_DATA_PARTITION);
		createEReference(luwTableSpaceEClass, LUW_TABLE_SPACE__REGULAR_DATA_PARTITION);
		createEReference(luwTableSpaceEClass, LUW_TABLE_SPACE__INDEXES);
		createEReference(luwTableSpaceEClass, LUW_TABLE_SPACE__INDEX_DATA_PARTITION);
		createEReference(luwTableSpaceEClass, LUW_TABLE_SPACE__STORAGE_GROUP);

		luwDatabasePartitionEClass = createEClass(LUW_DATABASE_PARTITION);
		createEAttribute(luwDatabasePartitionEClass, LUW_DATABASE_PARTITION__NUMBER);
		createEAttribute(luwDatabasePartitionEClass, LUW_DATABASE_PARTITION__PORT_NUMBER);
		createEAttribute(luwDatabasePartitionEClass, LUW_DATABASE_PARTITION__HOST_NAME);
		createEAttribute(luwDatabasePartitionEClass, LUW_DATABASE_PARTITION__SWITCH_NAME);
		createEAttribute(luwDatabasePartitionEClass, LUW_DATABASE_PARTITION__CATALOG_PARTITION);
		createEReference(luwDatabasePartitionEClass, LUW_DATABASE_PARTITION__GROUP);
		createEReference(luwDatabasePartitionEClass, LUW_DATABASE_PARTITION__BUFFER_POOL);
		createEReference(luwDatabasePartitionEClass, LUW_DATABASE_PARTITION__CONTAINERS);
		createEReference(luwDatabasePartitionEClass, LUW_DATABASE_PARTITION__SIZE_EXCEPTION);

		luwDatabaseContainerEClass = createEClass(LUW_DATABASE_CONTAINER);
		createEAttribute(luwDatabaseContainerEClass, LUW_DATABASE_CONTAINER__CONTAINER_TYPE);
		createEAttribute(luwDatabaseContainerEClass, LUW_DATABASE_CONTAINER__SIZE_IN_PAGES);
		createEAttribute(luwDatabaseContainerEClass, LUW_DATABASE_CONTAINER__SIZE);
		createEAttribute(luwDatabaseContainerEClass, LUW_DATABASE_CONTAINER__SIZE_UNITS);
		createEReference(luwDatabaseContainerEClass, LUW_DATABASE_CONTAINER__TABLE_SPACE);
		createEReference(luwDatabaseContainerEClass, LUW_DATABASE_CONTAINER__PARTITIONS);

		luwAdminServerEClass = createEClass(LUW_ADMIN_SERVER);
		createEReference(luwAdminServerEClass, LUW_ADMIN_SERVER__INSTANCES);

		luwBufferPoolEClass = createEClass(LUW_BUFFER_POOL);
		createEAttribute(luwBufferPoolEClass, LUW_BUFFER_POOL__CREATE_TYPE);
		createEAttribute(luwBufferPoolEClass, LUW_BUFFER_POOL__SIZE);
		createEAttribute(luwBufferPoolEClass, LUW_BUFFER_POOL__PAGE_SIZE);
		createEAttribute(luwBufferPoolEClass, LUW_BUFFER_POOL__BLOCK_SIZE);
		createEAttribute(luwBufferPoolEClass, LUW_BUFFER_POOL__NUM_BLOCK_PAGES);
		createEAttribute(luwBufferPoolEClass, LUW_BUFFER_POOL__EXTENDED_STORAGE);
		createEAttribute(luwBufferPoolEClass, LUW_BUFFER_POOL__AUTOMATIC);
		createEReference(luwBufferPoolEClass, LUW_BUFFER_POOL__TABLE_SPACES);
		createEReference(luwBufferPoolEClass, LUW_BUFFER_POOL__PARTITIONS);
		createEReference(luwBufferPoolEClass, LUW_BUFFER_POOL__PARTITION_GROUP);
		createEReference(luwBufferPoolEClass, LUW_BUFFER_POOL__DATABASE);
		createEReference(luwBufferPoolEClass, LUW_BUFFER_POOL__SIZE_EXCEPTION);

		luwTableEClass = createEClass(LUW_TABLE);
		createEAttribute(luwTableEClass, LUW_TABLE__PCT_FREE);
		createEAttribute(luwTableEClass, LUW_TABLE__RESTRICT_ON_DROP);
		createEAttribute(luwTableEClass, LUW_TABLE__PARTITION_MODE);
		createEAttribute(luwTableEClass, LUW_TABLE__APPEND_MODE);
		createEAttribute(luwTableEClass, LUW_TABLE__LOG_MODE);
		createEAttribute(luwTableEClass, LUW_TABLE__LOCK_SIZE_ROW);
		createEAttribute(luwTableEClass, LUW_TABLE__VOLATILE);
		createEReference(luwTableEClass, LUW_TABLE__OPTIONS);
		createEReference(luwTableEClass, LUW_TABLE__SECURITY_POLICY);

		luwViewEClass = createEClass(LUW_VIEW);
		createEAttribute(luwViewEClass, LUW_VIEW__FEDERATED);
		createEAttribute(luwViewEClass, LUW_VIEW__OPTIMIZE_QUERY);

		luwPartitionKeyEClass = createEClass(LUW_PARTITION_KEY);
		createEReference(luwPartitionKeyEClass, LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE);
		createEAttribute(luwPartitionKeyEClass, LUW_PARTITION_KEY__PARTITION_METHOD);
		createEReference(luwPartitionKeyEClass, LUW_PARTITION_KEY__TABLE);
		createEReference(luwPartitionKeyEClass, LUW_PARTITION_KEY__COLUMNS);

		luwNicknameEClass = createEClass(LUW_NICKNAME);
		createEReference(luwNicknameEClass, LUW_NICKNAME__REMOTE_DATA_SET);
		createEReference(luwNicknameEClass, LUW_NICKNAME__SERVER);

		luwFunctionMappingEClass = createEClass(LUW_FUNCTION_MAPPING);
		createEAttribute(luwFunctionMappingEClass, LUW_FUNCTION_MAPPING__SERVER_TYPE);
		createEAttribute(luwFunctionMappingEClass, LUW_FUNCTION_MAPPING__SERVER_VERSION);
		createEAttribute(luwFunctionMappingEClass, LUW_FUNCTION_MAPPING__SERVER_NAME);
		createEAttribute(luwFunctionMappingEClass, LUW_FUNCTION_MAPPING__CREATION_TIME);
		createEAttribute(luwFunctionMappingEClass, LUW_FUNCTION_MAPPING__DISABLED);
		createEAttribute(luwFunctionMappingEClass, LUW_FUNCTION_MAPPING__INSTS_PER_INVOC);
		createEAttribute(luwFunctionMappingEClass, LUW_FUNCTION_MAPPING__INSTS_PER_ARG_BYTE);
		createEAttribute(luwFunctionMappingEClass, LUW_FUNCTION_MAPPING__IOS_PER_INVOC);
		createEAttribute(luwFunctionMappingEClass, LUW_FUNCTION_MAPPING__IOS_PER_ARG_BYTE);
		createEReference(luwFunctionMappingEClass, LUW_FUNCTION_MAPPING__OPTIONS);
		createEReference(luwFunctionMappingEClass, LUW_FUNCTION_MAPPING__LOCAL_FUNCTION);
		createEReference(luwFunctionMappingEClass, LUW_FUNCTION_MAPPING__REMOTE_FUNCTION);
		createEReference(luwFunctionMappingEClass, LUW_FUNCTION_MAPPING__LUW_DATABASE);

		luwWrapperEClass = createEClass(LUW_WRAPPER);
		createEAttribute(luwWrapperEClass, LUW_WRAPPER__VERSION);
		createEAttribute(luwWrapperEClass, LUW_WRAPPER__LIBRARY);
		createEAttribute(luwWrapperEClass, LUW_WRAPPER__FENCED);
		createEAttribute(luwWrapperEClass, LUW_WRAPPER__WRAPPER_TYPE);
		createEAttribute(luwWrapperEClass, LUW_WRAPPER__DATA_SOURCE);
		createEAttribute(luwWrapperEClass, LUW_WRAPPER__DISCOVERED_LIBRARIES);
		createEReference(luwWrapperEClass, LUW_WRAPPER__SERVERS);
		createEReference(luwWrapperEClass, LUW_WRAPPER__LUW_DATABASE);
		createEReference(luwWrapperEClass, LUW_WRAPPER__OPTIONS);

		luwNonRelationalNicknameEClass = createEClass(LUW_NON_RELATIONAL_NICKNAME);
		createEReference(luwNonRelationalNicknameEClass, LUW_NON_RELATIONAL_NICKNAME__NON_REL_SERVER);

		luwNonRelationalServerEClass = createEClass(LUW_NON_RELATIONAL_SERVER);
		createEReference(luwNonRelationalServerEClass, LUW_NON_RELATIONAL_SERVER__NON_REL_WRAPPER);
		createEReference(luwNonRelationalServerEClass, LUW_NON_RELATIONAL_SERVER__NON_REL_NICKNAMES);

		luwNonRelationalWrapperEClass = createEClass(LUW_NON_RELATIONAL_WRAPPER);
		createEReference(luwNonRelationalWrapperEClass, LUW_NON_RELATIONAL_WRAPPER__NON_REL_SERVERS);

		luwRelationalNicknameEClass = createEClass(LUW_RELATIONAL_NICKNAME);
		createEReference(luwRelationalNicknameEClass, LUW_RELATIONAL_NICKNAME__REL_SERVER);

		luwGenericUserMappingEClass = createEClass(LUW_GENERIC_USER_MAPPING);
		createEAttribute(luwGenericUserMappingEClass, LUW_GENERIC_USER_MAPPING__REMOTE_USER);
		createEAttribute(luwGenericUserMappingEClass, LUW_GENERIC_USER_MAPPING__REMOTE_PASSWORD);

		luwRelationalWrapperEClass = createEClass(LUW_RELATIONAL_WRAPPER);
		createEReference(luwRelationalWrapperEClass, LUW_RELATIONAL_WRAPPER__REL_SERVERS);

		luwServerEClass = createEClass(LUW_SERVER);
		createEAttribute(luwServerEClass, LUW_SERVER__SERVER_TYPE);
		createEAttribute(luwServerEClass, LUW_SERVER__SERVER_VERSION);
		createEReference(luwServerEClass, LUW_SERVER__USER_MAPPINGS);
		createEReference(luwServerEClass, LUW_SERVER__WRAPPER);
		createEReference(luwServerEClass, LUW_SERVER__NICKNAMES);
		createEReference(luwServerEClass, LUW_SERVER__LUW_DATABASE);
		createEReference(luwServerEClass, LUW_SERVER__OPTIONS);
		createEReference(luwServerEClass, LUW_SERVER__REMOTE_SERVER);

		luwTypeMappingEClass = createEClass(LUW_TYPE_MAPPING);
		createEAttribute(luwTypeMappingEClass, LUW_TYPE_MAPPING__SERVER_TYPE);
		createEAttribute(luwTypeMappingEClass, LUW_TYPE_MAPPING__SERVER_VESION);
		createEAttribute(luwTypeMappingEClass, LUW_TYPE_MAPPING__SERVER_NAME);
		createEAttribute(luwTypeMappingEClass, LUW_TYPE_MAPPING__CREATION_TIME);
		createEReference(luwTypeMappingEClass, LUW_TYPE_MAPPING__LOCAL_TYPE);
		createEReference(luwTypeMappingEClass, LUW_TYPE_MAPPING__REMOTE_TYPE);

		luwUserMappingEClass = createEClass(LUW_USER_MAPPING);
		createEAttribute(luwUserMappingEClass, LUW_USER_MAPPING__LOCAL_AUTH_ID);
		createEReference(luwUserMappingEClass, LUW_USER_MAPPING__SERVER);
		createEReference(luwUserMappingEClass, LUW_USER_MAPPING__OPTIONS);

		luwOptionEClass = createEClass(LUW_OPTION);
		createEAttribute(luwOptionEClass, LUW_OPTION__VALUE);

		luwRelationalServerEClass = createEClass(LUW_RELATIONAL_SERVER);
		createEAttribute(luwRelationalServerEClass, LUW_RELATIONAL_SERVER__CPU_RATIO);
		createEAttribute(luwRelationalServerEClass, LUW_RELATIONAL_SERVER__IO_RATIO);
		createEAttribute(luwRelationalServerEClass, LUW_RELATIONAL_SERVER__COMM_RATE);
		createEAttribute(luwRelationalServerEClass, LUW_RELATIONAL_SERVER__FOLD_ID);
		createEAttribute(luwRelationalServerEClass, LUW_RELATIONAL_SERVER__FOLD_PW);
		createEAttribute(luwRelationalServerEClass, LUW_RELATIONAL_SERVER__COLLATING_SEQUENCE);
		createEAttribute(luwRelationalServerEClass, LUW_RELATIONAL_SERVER__PUSHDOWN);
		createEAttribute(luwRelationalServerEClass, LUW_RELATIONAL_SERVER__NODE);
		createEAttribute(luwRelationalServerEClass, LUW_RELATIONAL_SERVER__DB_NAME);
		createEAttribute(luwRelationalServerEClass, LUW_RELATIONAL_SERVER__IUD_APP_SVPT_ENFORCE);
		createEAttribute(luwRelationalServerEClass, LUW_RELATIONAL_SERVER__PASSWORD);
		createEReference(luwRelationalServerEClass, LUW_RELATIONAL_SERVER__REL_NICKNAMES);
		createEReference(luwRelationalServerEClass, LUW_RELATIONAL_SERVER__REL_WRAPPER);

		luwDatabaseEClass = createEClass(LUW_DATABASE);
		createEAttribute(luwDatabaseEClass, LUW_DATABASE__FEDERATED);
		createEReference(luwDatabaseEClass, LUW_DATABASE__GROUPS);
		createEReference(luwDatabaseEClass, LUW_DATABASE__WRAPPERS);
		createEReference(luwDatabaseEClass, LUW_DATABASE__SERVERS);
		createEReference(luwDatabaseEClass, LUW_DATABASE__FUNCTION_MAPPINGS);
		createEReference(luwDatabaseEClass, LUW_DATABASE__TYPE_MAPPINGS);
		createEReference(luwDatabaseEClass, LUW_DATABASE__REVERSE_TYPE_MAPPINGS);
		createEReference(luwDatabaseEClass, LUW_DATABASE__BUFFERPOOLS);
		createEReference(luwDatabaseEClass, LUW_DATABASE__TABLESPACES);
		createEReference(luwDatabaseEClass, LUW_DATABASE__STORAGE_GROUPS);
		createEReference(luwDatabaseEClass, LUW_DATABASE__DEFAULT_STORAGE_GROUP);

		luwColumnEClass = createEClass(LUW_COLUMN);
		createEAttribute(luwColumnEClass, LUW_COLUMN__LOB_LOGGED);
		createEAttribute(luwColumnEClass, LUW_COLUMN__LOB_COMPACTED);
		createEAttribute(luwColumnEClass, LUW_COLUMN__COMPRESSION);
		createEAttribute(luwColumnEClass, LUW_COLUMN__INLINE_LENGTH);
		createEAttribute(luwColumnEClass, LUW_COLUMN__HIDDEN);
		createEAttribute(luwColumnEClass, LUW_COLUMN__SECURITY_LABEL);
		createEReference(luwColumnEClass, LUW_COLUMN__OPTIONS);

		luwGenericNicknameEClass = createEClass(LUW_GENERIC_NICKNAME);
		createEReference(luwGenericNicknameEClass, LUW_GENERIC_NICKNAME__GENERIC_SERVER);

		luwGenericServerEClass = createEClass(LUW_GENERIC_SERVER);
		createEReference(luwGenericServerEClass, LUW_GENERIC_SERVER__GENERIC_NICKNAMES);
		createEReference(luwGenericServerEClass, LUW_GENERIC_SERVER__GENERIC_WRAPPER);

		luwMaterializedQueryTableEClass = createEClass(LUW_MATERIALIZED_QUERY_TABLE);

		luwGenericWrapperEClass = createEClass(LUW_GENERIC_WRAPPER);
		createEReference(luwGenericWrapperEClass, LUW_GENERIC_WRAPPER__GENERIC_SERVERS);

		luwStorageTableEClass = createEClass(LUW_STORAGE_TABLE);
		createEAttribute(luwStorageTableEClass, LUW_STORAGE_TABLE__VALUE_COMPRESSION);
		createEAttribute(luwStorageTableEClass, LUW_STORAGE_TABLE__ROW_COMPRESSION);
		createEAttribute(luwStorageTableEClass, LUW_STORAGE_TABLE__ROW_COMPRESSION_EMPTY);
		createEAttribute(luwStorageTableEClass, LUW_STORAGE_TABLE__COMPRESSION_MODE);
		createEReference(luwStorageTableEClass, LUW_STORAGE_TABLE__PARTITION_KEY);
		createEReference(luwStorageTableEClass, LUW_STORAGE_TABLE__INDEX_DATA_TABLE_SPACE);
		createEReference(luwStorageTableEClass, LUW_STORAGE_TABLE__LOB_DATA_TABLE_SPACE);
		createEReference(luwStorageTableEClass, LUW_STORAGE_TABLE__REGULAR_DATA_TABLE_SPACE);
		createEReference(luwStorageTableEClass, LUW_STORAGE_TABLE__DATA_PARTITIONS);
		createEReference(luwStorageTableEClass, LUW_STORAGE_TABLE__DATA_PARTITION_KEY);

		relationalRemoteServerEClass = createEClass(RELATIONAL_REMOTE_SERVER);
		createEReference(relationalRemoteServerEClass, RELATIONAL_REMOTE_SERVER__DATABASE);

		relationalRemoteDataSetEClass = createEClass(RELATIONAL_REMOTE_DATA_SET);
		createEReference(relationalRemoteDataSetEClass, RELATIONAL_REMOTE_DATA_SET__TABLE);

		remoteServerEClass = createEClass(REMOTE_SERVER);
		createEReference(remoteServerEClass, REMOTE_SERVER__LUW_SERVER);

		remoteDataSetEClass = createEClass(REMOTE_DATA_SET);
		createEReference(remoteDataSetEClass, REMOTE_DATA_SET__NICKNAME);

		luwIndexEClass = createEClass(LUW_INDEX);
		createEAttribute(luwIndexEClass, LUW_INDEX__PCT_FREE);
		createEAttribute(luwIndexEClass, LUW_INDEX__MIN_PCT_FREE);
		createEAttribute(luwIndexEClass, LUW_INDEX__REVERSE_SCAN);
		createEAttribute(luwIndexEClass, LUW_INDEX__NOT_PARTITIONED);
		createEAttribute(luwIndexEClass, LUW_INDEX__XML_PATTERN);
		createEReference(luwIndexEClass, LUW_INDEX__AS_SQL_DATA_TYPE);
		createEAttribute(luwIndexEClass, LUW_INDEX__AS_SQL_DATA_TYPE_HASHED);
		createEAttribute(luwIndexEClass, LUW_INDEX__SYSTEM_REQUIRED);
		createEAttribute(luwIndexEClass, LUW_INDEX__PAGE_SPLIT_TYPE);
		createEAttribute(luwIndexEClass, LUW_INDEX__LEVEL2_PCT_FREE);
		createEAttribute(luwIndexEClass, LUW_INDEX__MIN_PCT_USED);
		createEAttribute(luwIndexEClass, LUW_INDEX__COMPRESS);
		createEAttribute(luwIndexEClass, LUW_INDEX__COLLECT_STATS);
		createEAttribute(luwIndexEClass, LUW_INDEX__SAMPLED_STATS);
		createEAttribute(luwIndexEClass, LUW_INDEX__DETAILED_STATS);
		createEAttribute(luwIndexEClass, LUW_INDEX__IGNORE_INVALID_VALUES);
		createEAttribute(luwIndexEClass, LUW_INDEX__EXCLUDE_NULL_KEYS);
		createEReference(luwIndexEClass, LUW_INDEX__TABLESPACE);

		luwAttributeDefinitionEClass = createEClass(LUW_ATTRIBUTE_DEFINITION);
		createEAttribute(luwAttributeDefinitionEClass, LUW_ATTRIBUTE_DEFINITION__LOB_LOGGED);
		createEAttribute(luwAttributeDefinitionEClass, LUW_ATTRIBUTE_DEFINITION__LOB_COMPACTED);

		federatedProcedureEClass = createEClass(FEDERATED_PROCEDURE);
		createEAttribute(federatedProcedureEClass, FEDERATED_PROCEDURE__REMOTE_UNIQUE_ID);
		createEAttribute(federatedProcedureEClass, FEDERATED_PROCEDURE__REMOTE_SERVER);
		createEAttribute(federatedProcedureEClass, FEDERATED_PROCEDURE__REMOTE_SCHEMA);
		createEAttribute(federatedProcedureEClass, FEDERATED_PROCEDURE__REMOTE_PACKAGE);
		createEAttribute(federatedProcedureEClass, FEDERATED_PROCEDURE__REMOTE_PROCEDURE_NAME);
		createEAttribute(federatedProcedureEClass, FEDERATED_PROCEDURE__NUMBER_OF_PARAMETERS);
		createEAttribute(federatedProcedureEClass, FEDERATED_PROCEDURE__RESULT_SETS_TO_CLIENT);
		createEAttribute(federatedProcedureEClass, FEDERATED_PROCEDURE__NUMBER_OF_REF_CURSORS);
		createEAttribute(federatedProcedureEClass, FEDERATED_PROCEDURE__ALL_RESULT_SETS_TO_CALLER);
		createEReference(federatedProcedureEClass, FEDERATED_PROCEDURE__FEDERATED_PROCEDURE);
		createEReference(federatedProcedureEClass, FEDERATED_PROCEDURE__REMOTE_PROCEDURE);
		createEReference(federatedProcedureEClass, FEDERATED_PROCEDURE__FEDERATED_PARAMETER);

		federatedParameterEClass = createEClass(FEDERATED_PARAMETER);
		createEAttribute(federatedParameterEClass, FEDERATED_PARAMETER__REMOTE_CODE_PAGE);
		createEAttribute(federatedParameterEClass, FEDERATED_PARAMETER__REMOTE_PARAM_TYPE_ID);
		createEReference(federatedParameterEClass, FEDERATED_PARAMETER__FEDERATED_PROCEDURE);
		createEReference(federatedParameterEClass, FEDERATED_PARAMETER__REMOTE_PARAMETER);

		luwPartitionExpressionEClass = createEClass(LUW_PARTITION_EXPRESSION);
		createEAttribute(luwPartitionExpressionEClass, LUW_PARTITION_EXPRESSION__NULLS_LAST);
		createEReference(luwPartitionExpressionEClass, LUW_PARTITION_EXPRESSION__KEY);
		createEReference(luwPartitionExpressionEClass, LUW_PARTITION_EXPRESSION__COLUMN);
		createEReference(luwPartitionExpressionEClass, LUW_PARTITION_EXPRESSION__PARTITION_ELEMENTS);

		luwPartitionElementEClass = createEClass(LUW_PARTITION_ELEMENT);
		createEAttribute(luwPartitionElementEClass, LUW_PARTITION_ELEMENT__STARTING);
		createEAttribute(luwPartitionElementEClass, LUW_PARTITION_ELEMENT__ENDING);
		createEReference(luwPartitionElementEClass, LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION);
		createEReference(luwPartitionElementEClass, LUW_PARTITION_ELEMENT__PARTITION);
		createEReference(luwPartitionElementEClass, LUW_PARTITION_ELEMENT__EVERY_CLAUSE);

		luwDataPartitionEClass = createEClass(LUW_DATA_PARTITION);
		createEAttribute(luwDataPartitionEClass, LUW_DATA_PARTITION__ID);
		createEAttribute(luwDataPartitionEClass, LUW_DATA_PARTITION__LOW_INCLUSIVE);
		createEAttribute(luwDataPartitionEClass, LUW_DATA_PARTITION__HIGH_INCLUSIVE);
		createEReference(luwDataPartitionEClass, LUW_DATA_PARTITION__LOB_DATA_TABLE_SPACE);
		createEReference(luwDataPartitionEClass, LUW_DATA_PARTITION__REGULAR_DATA_TABLE_SPACE);
		createEReference(luwDataPartitionEClass, LUW_DATA_PARTITION__PARTITION_ELEMENTS);
		createEReference(luwDataPartitionEClass, LUW_DATA_PARTITION__TABLE);
		createEReference(luwDataPartitionEClass, LUW_DATA_PARTITION__INDEX_DATA_TABLE_SPACE);

		luwDataPartitionKeyEClass = createEClass(LUW_DATA_PARTITION_KEY);
		createEAttribute(luwDataPartitionKeyEClass, LUW_DATA_PARTITION_KEY__PARTITION_METHOD);
		createEReference(luwDataPartitionKeyEClass, LUW_DATA_PARTITION_KEY__PARTITION_EXPRESSIONS);
		createEReference(luwDataPartitionKeyEClass, LUW_DATA_PARTITION_KEY__TABLE);

		luwDatabasePackageEClass = createEClass(LUW_DATABASE_PACKAGE);
		createEAttribute(luwDatabasePackageEClass, LUW_DATABASE_PACKAGE__CREATOR);
		createEAttribute(luwDatabasePackageEClass, LUW_DATABASE_PACKAGE__BINDER);
		createEAttribute(luwDatabasePackageEClass, LUW_DATABASE_PACKAGE__CURSOR_BLOCK);
		createEAttribute(luwDatabasePackageEClass, LUW_DATABASE_PACKAGE__NUMBER_OF_SECTIONS);
		createEAttribute(luwDatabasePackageEClass, LUW_DATABASE_PACKAGE__OPTIMIZATION_CLASS);
		createEAttribute(luwDatabasePackageEClass, LUW_DATABASE_PACKAGE__EXPLAIN_SNAPSHOT);

		luwModuleEClass = createEClass(LUW_MODULE);
		createEAttribute(luwModuleEClass, LUW_MODULE__DIALECT);
		createEReference(luwModuleEClass, LUW_MODULE__OWNING_SCHEMA);
		createEReference(luwModuleEClass, LUW_MODULE__MODULE_OBJECTS);

		luwModuleObjectEClass = createEClass(LUW_MODULE_OBJECT);
		createEAttribute(luwModuleObjectEClass, LUW_MODULE_OBJECT__PUBLISHED);
		createEReference(luwModuleObjectEClass, LUW_MODULE_OBJECT__MODULE);

		luwModuleFunctionEClass = createEClass(LUW_MODULE_FUNCTION);
		createEAttribute(luwModuleFunctionEClass, LUW_MODULE_FUNCTION__IMPLEMENTED);

		luwModuleProcedureEClass = createEClass(LUW_MODULE_PROCEDURE);
		createEAttribute(luwModuleProcedureEClass, LUW_MODULE_PROCEDURE__IMPLEMENTED);

		luwModuleConditionEClass = createEClass(LUW_MODULE_CONDITION);
		createEAttribute(luwModuleConditionEClass, LUW_MODULE_CONDITION__SQLSTATE);

		luwGlobalVariableEClass = createEClass(LUW_GLOBAL_VARIABLE);
		createEAttribute(luwGlobalVariableEClass, LUW_GLOBAL_VARIABLE__DEFAULT_VALUE);
		createEAttribute(luwGlobalVariableEClass, LUW_GLOBAL_VARIABLE__IS_CONSTANT);
		createEReference(luwGlobalVariableEClass, LUW_GLOBAL_VARIABLE__SCHEMA);

		luwModuleTypeEClass = createEClass(LUW_MODULE_TYPE);

		luwModuleRowDataTypeEClass = createEClass(LUW_MODULE_ROW_DATA_TYPE);

		luwModuleArrayDataTypeEClass = createEClass(LUW_MODULE_ARRAY_DATA_TYPE);

		luwModuleDistinctTypeEClass = createEClass(LUW_MODULE_DISTINCT_TYPE);

		luwModuleGlobalVariableEClass = createEClass(LUW_MODULE_GLOBAL_VARIABLE);

		luwArrayDataTypeEClass = createEClass(LUW_ARRAY_DATA_TYPE);
		createEReference(luwArrayDataTypeEClass, LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE);

		luwRowDataTypeEClass = createEClass(LUW_ROW_DATA_TYPE);

		plsqlPackageEClass = createEClass(PLSQL_PACKAGE);
		createEReference(plsqlPackageEClass, PLSQL_PACKAGE__PACKAGE_BODY);

		plsqlPackageBodyEClass = createEClass(PLSQL_PACKAGE_BODY);
		createEReference(plsqlPackageBodyEClass, PLSQL_PACKAGE_BODY__PACKAGE);

		luwCursorDataTypeEClass = createEClass(LUW_CURSOR_DATA_TYPE);
		createEReference(luwCursorDataTypeEClass, LUW_CURSOR_DATA_TYPE__ROW_TYPE);

		luwModuleCursorDataTypeEClass = createEClass(LUW_MODULE_CURSOR_DATA_TYPE);

		luwBufferPoolSizeExceptionEClass = createEClass(LUW_BUFFER_POOL_SIZE_EXCEPTION);
		createEAttribute(luwBufferPoolSizeExceptionEClass, LUW_BUFFER_POOL_SIZE_EXCEPTION__SIZE);
		createEReference(luwBufferPoolSizeExceptionEClass, LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL);
		createEReference(luwBufferPoolSizeExceptionEClass, LUW_BUFFER_POOL_SIZE_EXCEPTION__PARTITIONS);

		luwMemberEClass = createEClass(LUW_MEMBER);
		createEAttribute(luwMemberEClass, LUW_MEMBER__TYPE);
		createEAttribute(luwMemberEClass, LUW_MEMBER__ALERT);
		createEAttribute(luwMemberEClass, LUW_MEMBER__DB_PARTITION_NUM);
		createEAttribute(luwMemberEClass, LUW_MEMBER__LOGICAL_PORT);
		createEAttribute(luwMemberEClass, LUW_MEMBER__NET_NAME);

		luwSecurityPolicyEClass = createEClass(LUW_SECURITY_POLICY);
		createEAttribute(luwSecurityPolicyEClass, LUW_SECURITY_POLICY__NOT_AUTHORIZED_WRITE);
		createEReference(luwSecurityPolicyEClass, LUW_SECURITY_POLICY__COMPONENTS);
		createEReference(luwSecurityPolicyEClass, LUW_SECURITY_POLICY__LABELS);
		createEReference(luwSecurityPolicyEClass, LUW_SECURITY_POLICY__TABLE);

		luwSecurityLabelComponentEClass = createEClass(LUW_SECURITY_LABEL_COMPONENT);
		createEAttribute(luwSecurityLabelComponentEClass, LUW_SECURITY_LABEL_COMPONENT__TYPE);
		createEReference(luwSecurityLabelComponentEClass, LUW_SECURITY_LABEL_COMPONENT__LUW_SECURITY_POLICY);
		createEReference(luwSecurityLabelComponentEClass, LUW_SECURITY_LABEL_COMPONENT__ELEMENTS);

		luwSecurityLabelEClass = createEClass(LUW_SECURITY_LABEL);
		createEAttribute(luwSecurityLabelEClass, LUW_SECURITY_LABEL__SECURITY_LABEL);
		createEReference(luwSecurityLabelEClass, LUW_SECURITY_LABEL__POLICY);

		luwSecurityLabelComponentElementEClass = createEClass(LUW_SECURITY_LABEL_COMPONENT_ELEMENT);
		createEAttribute(luwSecurityLabelComponentElementEClass, LUW_SECURITY_LABEL_COMPONENT_ELEMENT__VALUE);
		createEAttribute(luwSecurityLabelComponentElementEClass, LUW_SECURITY_LABEL_COMPONENT_ELEMENT__PARENT_VALUE);
		createEReference(luwSecurityLabelComponentElementEClass, LUW_SECURITY_LABEL_COMPONENT_ELEMENT__LUW_SECURITY_LABEL_COMPONENT);

		luwStorageGroupEClass = createEClass(LUW_STORAGE_GROUP);
		createEAttribute(luwStorageGroupEClass, LUW_STORAGE_GROUP__STORAGE_PATHS);
		createEAttribute(luwStorageGroupEClass, LUW_STORAGE_GROUP__OVERHEAD);
		createEAttribute(luwStorageGroupEClass, LUW_STORAGE_GROUP__DEVICE_READ_RATE);
		createEAttribute(luwStorageGroupEClass, LUW_STORAGE_GROUP__DATA_TAG);
		createEAttribute(luwStorageGroupEClass, LUW_STORAGE_GROUP__DEFAULT);
		createEReference(luwStorageGroupEClass, LUW_STORAGE_GROUP__DATABASE);
		createEReference(luwStorageGroupEClass, LUW_STORAGE_GROUP__TABLE_SPACES);

		luwTemporaryStorageTableEClass = createEClass(LUW_TEMPORARY_STORAGE_TABLE);
		createEReference(luwTemporaryStorageTableEClass, LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY);
		createEReference(luwTemporaryStorageTableEClass, LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE);

		luwTemporaryTableEClass = createEClass(LUW_TEMPORARY_TABLE);
		createEReference(luwTemporaryTableEClass, LUW_TEMPORARY_TABLE__TABLE);
		createEAttribute(luwTemporaryTableEClass, LUW_TEMPORARY_TABLE__LOG_OPTION);

		arrayIndexElementTypeEClass = createEClass(ARRAY_INDEX_ELEMENT_TYPE);
		createEReference(arrayIndexElementTypeEClass, ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE);

		luwPartitionEveryClauseElementEClass = createEClass(LUW_PARTITION_EVERY_CLAUSE_ELEMENT);
		createEAttribute(luwPartitionEveryClauseElementEClass, LUW_PARTITION_EVERY_CLAUSE_ELEMENT__VALUE);
		createEAttribute(luwPartitionEveryClauseElementEClass, LUW_PARTITION_EVERY_CLAUSE_ELEMENT__DURATION);
		createEReference(luwPartitionEveryClauseElementEClass, LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT);

		// Create enums
		luwContainerTypeEEnum = createEEnum(LUW_CONTAINER_TYPE);
		pageSizeTypeEEnum = createEEnum(PAGE_SIZE_TYPE);
		bufferPoolTypeEEnum = createEEnum(BUFFER_POOL_TYPE);
		tableSpaceTypeEEnum = createEEnum(TABLE_SPACE_TYPE);
		managementTypeEEnum = createEEnum(MANAGEMENT_TYPE);
		checkOptionTypeEEnum = createEEnum(CHECK_OPTION_TYPE);
		partitionMethodEEnum = createEEnum(PARTITION_METHOD);
		maintenanceTypeEEnum = createEEnum(MAINTENANCE_TYPE);
		refreshTypeEEnum = createEEnum(REFRESH_TYPE);
		wrapperTypeEEnum = createEEnum(WRAPPER_TYPE);
		dataPartitionMethodEEnum = createEEnum(DATA_PARTITION_METHOD);
		cursorBlockTypeEEnum = createEEnum(CURSOR_BLOCK_TYPE);
		explainSnaphotTypeEEnum = createEEnum(EXPLAIN_SNAPHOT_TYPE);
		fileSystemCachingTypeEEnum = createEEnum(FILE_SYSTEM_CACHING_TYPE);
		luwIndexPageSplitTypeEEnum = createEEnum(LUW_INDEX_PAGE_SPLIT_TYPE);
		luwIndexCompressTypeEEnum = createEEnum(LUW_INDEX_COMPRESS_TYPE);
		systemTypeEEnum = createEEnum(SYSTEM_TYPE);
		averageTableSizeTypeEEnum = createEEnum(AVERAGE_TABLE_SIZE_TYPE);
		luwStorageTableCompressionModeEEnum = createEEnum(LUW_STORAGE_TABLE_COMPRESSION_MODE);
		luwMemberTypeEEnum = createEEnum(LUW_MEMBER_TYPE);
		memberStateTypeEEnum = createEEnum(MEMBER_STATE_TYPE);
		luwSecurityLabelComponentTypeEEnum = createEEnum(LUW_SECURITY_LABEL_COMPONENT_TYPE);
		luwSecurityLabelNotAuthorizedWriteActionEEnum = createEEnum(LUW_SECURITY_LABEL_NOT_AUTHORIZED_WRITE_ACTION);
		luwFederatedDataSourceEEnum = createEEnum(LUW_FEDERATED_DATA_SOURCE);
		luwTemporaryTableLoggingOptionEEnum = createEEnum(LUW_TEMPORARY_TABLE_LOGGING_OPTION);
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
		DB2ModelPackage theDB2ModelPackage = (DB2ModelPackage)EPackage.Registry.INSTANCE.getEPackage(DB2ModelPackage.eNS_URI);
		SQLTablesPackage theSQLTablesPackage = (SQLTablesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLTablesPackage.eNS_URI);
		SQLRoutinesPackage theSQLRoutinesPackage = (SQLRoutinesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLRoutinesPackage.eNS_URI);
		SQLDataTypesPackage theSQLDataTypesPackage = (SQLDataTypesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLDataTypesPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Add supertypes to classes
		luwPartitionGroupEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwTableSpaceEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwDatabasePartitionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwDatabaseContainerEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwAdminServerEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwBufferPoolEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwTableEClass.getESuperTypes().add(theDB2ModelPackage.getDB2Table());
		luwTableEClass.getESuperTypes().add(this.getLUWStorageTable());
		luwViewEClass.getESuperTypes().add(theDB2ModelPackage.getDB2View());
		luwPartitionKeyEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwNicknameEClass.getESuperTypes().add(this.getLUWTable());
		luwFunctionMappingEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwWrapperEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwNonRelationalNicknameEClass.getESuperTypes().add(this.getLUWNickname());
		luwNonRelationalServerEClass.getESuperTypes().add(this.getLUWServer());
		luwNonRelationalWrapperEClass.getESuperTypes().add(this.getLUWWrapper());
		luwRelationalNicknameEClass.getESuperTypes().add(this.getLUWNickname());
		luwGenericUserMappingEClass.getESuperTypes().add(this.getLUWUserMapping());
		luwRelationalWrapperEClass.getESuperTypes().add(this.getLUWWrapper());
		luwServerEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwTypeMappingEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwUserMappingEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwOptionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwRelationalServerEClass.getESuperTypes().add(this.getLUWServer());
		luwDatabaseEClass.getESuperTypes().add(theDB2ModelPackage.getDB2Database());
		luwColumnEClass.getESuperTypes().add(theDB2ModelPackage.getDB2Column());
		luwGenericNicknameEClass.getESuperTypes().add(this.getLUWNickname());
		luwGenericServerEClass.getESuperTypes().add(this.getLUWServer());
		luwMaterializedQueryTableEClass.getESuperTypes().add(theDB2ModelPackage.getDB2MaterializedQueryTable());
		luwMaterializedQueryTableEClass.getESuperTypes().add(this.getLUWStorageTable());
		luwGenericWrapperEClass.getESuperTypes().add(this.getLUWWrapper());
		relationalRemoteServerEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		relationalRemoteServerEClass.getESuperTypes().add(this.getRemoteServer());
		relationalRemoteDataSetEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		relationalRemoteDataSetEClass.getESuperTypes().add(this.getRemoteDataSet());
		luwIndexEClass.getESuperTypes().add(theDB2ModelPackage.getDB2Index());
		luwAttributeDefinitionEClass.getESuperTypes().add(theSQLDataTypesPackage.getAttributeDefinition());
		federatedProcedureEClass.getESuperTypes().add(theDB2ModelPackage.getDB2Procedure());
		federatedParameterEClass.getESuperTypes().add(theSQLRoutinesPackage.getParameter());
		luwPartitionExpressionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwPartitionElementEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwDataPartitionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwDatabasePackageEClass.getESuperTypes().add(theDB2ModelPackage.getDB2Package());
		luwModuleEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwModuleFunctionEClass.getESuperTypes().add(theDB2ModelPackage.getDB2UserDefinedFunction());
		luwModuleFunctionEClass.getESuperTypes().add(this.getLUWModuleObject());
		luwModuleProcedureEClass.getESuperTypes().add(theDB2ModelPackage.getDB2Procedure());
		luwModuleProcedureEClass.getESuperTypes().add(this.getLUWModuleObject());
		luwModuleConditionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwModuleConditionEClass.getESuperTypes().add(this.getLUWModuleObject());
		luwGlobalVariableEClass.getESuperTypes().add(theSQLSchemaPackage.getTypedElement());
		luwModuleTypeEClass.getESuperTypes().add(this.getLUWModuleObject());
		luwModuleRowDataTypeEClass.getESuperTypes().add(this.getLUWRowDataType());
		luwModuleRowDataTypeEClass.getESuperTypes().add(this.getLUWModuleType());
		luwModuleArrayDataTypeEClass.getESuperTypes().add(this.getLUWArrayDataType());
		luwModuleArrayDataTypeEClass.getESuperTypes().add(this.getLUWModuleType());
		luwModuleDistinctTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getDistinctUserDefinedType());
		luwModuleDistinctTypeEClass.getESuperTypes().add(this.getLUWModuleType());
		luwModuleGlobalVariableEClass.getESuperTypes().add(this.getLUWGlobalVariable());
		luwModuleGlobalVariableEClass.getESuperTypes().add(this.getLUWModuleObject());
		luwArrayDataTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getArrayDataType());
		luwArrayDataTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getUserDefinedType());
		luwRowDataTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getUserDefinedType());
		luwRowDataTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getRowDataType());
		plsqlPackageEClass.getESuperTypes().add(this.getLUWModule());
		plsqlPackageEClass.getESuperTypes().add(theDB2ModelPackage.getDB2Routine());
		plsqlPackageBodyEClass.getESuperTypes().add(theSQLRoutinesPackage.getSource());
		luwCursorDataTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getUserDefinedType());
		luwCursorDataTypeEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwModuleCursorDataTypeEClass.getESuperTypes().add(this.getLUWCursorDataType());
		luwModuleCursorDataTypeEClass.getESuperTypes().add(this.getLUWModuleType());
		luwBufferPoolSizeExceptionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwMemberEClass.getESuperTypes().add(theDB2ModelPackage.getDB2Member());
		luwSecurityPolicyEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwSecurityLabelComponentEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwSecurityLabelEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwSecurityLabelComponentElementEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwStorageGroupEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		luwTemporaryTableEClass.getESuperTypes().add(theSQLTablesPackage.getTemporaryTable());
		luwTemporaryTableEClass.getESuperTypes().add(this.getLUWTemporaryStorageTable());
		arrayIndexElementTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getElementType());

		// Initialize classes and features; add operations and parameters
		initEClass(luwPartitionGroupEClass, LUWPartitionGroup.class, "LUWPartitionGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWPartitionGroup_Partitions(), this.getLUWDatabasePartition(), this.getLUWDatabasePartition_Group(), "partitions", null, 1, -1, LUWPartitionGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWPartitionGroup_TableSpaces(), this.getLUWTableSpace(), this.getLUWTableSpace_Group(), "tableSpaces", null, 0, -1, LUWPartitionGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWPartitionGroup_Database(), this.getLUWDatabase(), this.getLUWDatabase_Groups(), "database", null, 1, 1, LUWPartitionGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWPartitionGroup_BufferPool(), this.getLUWBufferPool(), this.getLUWBufferPool_PartitionGroup(), "bufferPool", null, 0, -1, LUWPartitionGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwTableSpaceEClass, LUWTableSpace.class, "LUWTableSpace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWTableSpace_TemporaryStorageTables(), this.getLUWTemporaryStorageTable(), this.getLUWTemporaryStorageTable_UserTemporaryTableSpace(), "temporaryStorageTables", null, 0, -1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_TablespaceType(), this.getTableSpaceType(), "tablespaceType", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_ManagementType(), this.getManagementType(), "managementType", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_ExtentSize(), ecorePackage.getEInt(), "extentSize", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_PreFetchSize(), ecorePackage.getEInt(), "preFetchSize", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_Overhead(), ecorePackage.getEDouble(), "overhead", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_TransferRate(), ecorePackage.getEDouble(), "transferRate", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_RecoverDroppedTableOn(), ecorePackage.getEBoolean(), "recoverDroppedTableOn", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_PageSize(), this.getPageSizeType(), "pageSize", "FOUR_K", 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWTableSpace_Size(), ecorePackage.getELong(), "size", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_AutoResize(), ecorePackage.getEBoolean(), "autoResize", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_InitialSize(), ecorePackage.getELong(), "initialSize", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_IncreaseSize(), ecorePackage.getELong(), "increaseSize", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_MaximumSize(), ecorePackage.getELong(), "maximumSize", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_InitialSizeUnit(), theDB2ModelPackage.getUnitType(), "initialSizeUnit", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_MaximumSizeUnit(), theDB2ModelPackage.getUnitType(), "maximumSizeUnit", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_IncreaseSizeUnit(), theDB2ModelPackage.getUnitType(), "increaseSizeUnit", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_IncreasePercent(), ecorePackage.getEInt(), "increasePercent", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_FileSystemCaching(), this.getFileSystemCachingType(), "fileSystemCaching", "NONE", 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWTableSpace_AverageSeekTime(), ecorePackage.getEDouble(), "averageSeekTime", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_RotationSpeed(), ecorePackage.getEInt(), "rotationSpeed", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_Transfer(), ecorePackage.getEDouble(), "transfer", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_SystemType(), this.getSystemType(), "systemType", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_AverageTableSize(), this.getAverageTableSizeType(), "averageTableSize", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_ExternalContainerCount(), ecorePackage.getEInt(), "externalContainerCount", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_InheritOverhead(), ecorePackage.getEBoolean(), "inheritOverhead", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_InheritTransferate(), ecorePackage.getEBoolean(), "inheritTransferate", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_Rebalance(), ecorePackage.getEBoolean(), "rebalance", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_DataTag(), ecorePackage.getEString(), "dataTag", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_SuspendRebalance(), ecorePackage.getEBoolean(), "suspendRebalance", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTableSpace_ResumeRebalance(), ecorePackage.getEBoolean(), "resumeRebalance", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTableSpace_Group(), this.getLUWPartitionGroup(), this.getLUWPartitionGroup_TableSpaces(), "group", null, 1, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTableSpace_Containers(), this.getLUWDatabaseContainer(), this.getLUWDatabaseContainer_TableSpace(), "containers", null, 1, -1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTableSpace_BufferPool(), this.getLUWBufferPool(), this.getLUWBufferPool_TableSpaces(), "bufferPool", null, 1, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTableSpace_IndexDataTables(), this.getLUWStorageTable(), this.getLUWStorageTable_IndexDataTableSpace(), "indexDataTables", null, 0, -1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTableSpace_LOBDataTables(), this.getLUWStorageTable(), this.getLUWStorageTable_LOBDataTableSpace(), "LOBDataTables", null, 0, -1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTableSpace_RegularDataTables(), this.getLUWStorageTable(), this.getLUWStorageTable_RegularDataTableSpace(), "regularDataTables", null, 0, -1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTableSpace_Database(), this.getLUWDatabase(), this.getLUWDatabase_Tablespaces(), "database", null, 1, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTableSpace_LOBDataPartition(), this.getLUWDataPartition(), this.getLUWDataPartition_LOBDataTableSpace(), "LOBDataPartition", null, 0, -1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTableSpace_RegularDataPartition(), this.getLUWDataPartition(), this.getLUWDataPartition_RegularDataTableSpace(), "regularDataPartition", null, 0, -1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTableSpace_Indexes(), this.getLUWIndex(), this.getLUWIndex_Tablespace(), "indexes", null, 0, -1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTableSpace_IndexDataPartition(), this.getLUWDataPartition(), this.getLUWDataPartition_IndexDataTableSpace(), "indexDataPartition", null, 0, -1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTableSpace_StorageGroup(), this.getLUWStorageGroup(), this.getLUWStorageGroup_TableSpaces(), "storageGroup", null, 0, 1, LUWTableSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(luwTableSpaceEClass, theSQLSchemaPackage.getList(), "getTables", 0, 1); //$NON-NLS-1$

		initEClass(luwDatabasePartitionEClass, LUWDatabasePartition.class, "LUWDatabasePartition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWDatabasePartition_Number(), ecorePackage.getEInt(), "number", null, 0, 1, LUWDatabasePartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWDatabasePartition_PortNumber(), ecorePackage.getEInt(), "portNumber", null, 0, 1, LUWDatabasePartition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWDatabasePartition_HostName(), ecorePackage.getEString(), "hostName", null, 0, 1, LUWDatabasePartition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWDatabasePartition_SwitchName(), ecorePackage.getEString(), "switchName", null, 0, 1, LUWDatabasePartition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWDatabasePartition_CatalogPartition(), ecorePackage.getEBoolean(), "catalogPartition", null, 0, 1, LUWDatabasePartition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabasePartition_Group(), this.getLUWPartitionGroup(), this.getLUWPartitionGroup_Partitions(), "group", null, 1, 1, LUWDatabasePartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabasePartition_BufferPool(), this.getLUWBufferPool(), this.getLUWBufferPool_Partitions(), "bufferPool", null, 1, 1, LUWDatabasePartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabasePartition_Containers(), this.getLUWDatabaseContainer(), this.getLUWDatabaseContainer_Partitions(), "containers", null, 0, -1, LUWDatabasePartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabasePartition_SizeException(), this.getLUWBufferPoolSizeException(), this.getLUWBufferPoolSizeException_Partitions(), "sizeException", null, 0, 1, LUWDatabasePartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwDatabaseContainerEClass, LUWDatabaseContainer.class, "LUWDatabaseContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWDatabaseContainer_ContainerType(), this.getLUWContainerType(), "containerType", null, 0, 1, LUWDatabaseContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWDatabaseContainer_SizeInPages(), ecorePackage.getEInt(), "sizeInPages", null, 0, 1, LUWDatabaseContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWDatabaseContainer_Size(), ecorePackage.getEInt(), "size", null, 0, 1, LUWDatabaseContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWDatabaseContainer_SizeUnits(), theDB2ModelPackage.getUnitType(), "sizeUnits", null, 0, 1, LUWDatabaseContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabaseContainer_TableSpace(), this.getLUWTableSpace(), this.getLUWTableSpace_Containers(), "tableSpace", null, 1, 1, LUWDatabaseContainer.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabaseContainer_Partitions(), this.getLUWDatabasePartition(), this.getLUWDatabasePartition_Containers(), "partitions", null, 0, -1, LUWDatabaseContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwAdminServerEClass, LUWAdminServer.class, "LUWAdminServer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWAdminServer_Instances(), theDB2ModelPackage.getDB2DatabaseManager(), theDB2ModelPackage.getDB2DatabaseManager_Server(), "instances", null, 0, -1, LUWAdminServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwBufferPoolEClass, LUWBufferPool.class, "LUWBufferPool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWBufferPool_CreateType(), this.getBufferPoolType(), "createType", null, 0, 1, LUWBufferPool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWBufferPool_Size(), ecorePackage.getEInt(), "size", null, 0, 1, LUWBufferPool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWBufferPool_PageSize(), this.getPageSizeType(), "pageSize", "FOUR_K", 0, 1, LUWBufferPool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWBufferPool_BlockSize(), ecorePackage.getEInt(), "blockSize", "32", 0, 1, LUWBufferPool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWBufferPool_NumBlockPages(), ecorePackage.getEInt(), "numBlockPages", null, 0, 1, LUWBufferPool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWBufferPool_ExtendedStorage(), ecorePackage.getEBoolean(), "extendedStorage", null, 0, 1, LUWBufferPool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWBufferPool_Automatic(), ecorePackage.getEBoolean(), "automatic", "true", 0, 1, LUWBufferPool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getLUWBufferPool_TableSpaces(), this.getLUWTableSpace(), this.getLUWTableSpace_BufferPool(), "tableSpaces", null, 0, -1, LUWBufferPool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWBufferPool_Partitions(), this.getLUWDatabasePartition(), this.getLUWDatabasePartition_BufferPool(), "partitions", null, 0, -1, LUWBufferPool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWBufferPool_PartitionGroup(), this.getLUWPartitionGroup(), this.getLUWPartitionGroup_BufferPool(), "partitionGroup", null, 0, -1, LUWBufferPool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWBufferPool_Database(), this.getLUWDatabase(), this.getLUWDatabase_Bufferpools(), "database", null, 1, 1, LUWBufferPool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWBufferPool_SizeException(), this.getLUWBufferPoolSizeException(), this.getLUWBufferPoolSizeException_BufferPool(), "sizeException", null, 0, -1, LUWBufferPool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwTableEClass, LUWTable.class, "LUWTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWTable_PCTFree(), ecorePackage.getEInt(), "PCTFree", "-1", 0, 1, LUWTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWTable_RestrictOnDrop(), ecorePackage.getEBoolean(), "restrictOnDrop", null, 0, 1, LUWTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTable_PartitionMode(), ecorePackage.getEString(), "partitionMode", null, 0, 1, LUWTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTable_AppendMode(), ecorePackage.getEBoolean(), "appendMode", null, 0, 1, LUWTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTable_LogMode(), ecorePackage.getEString(), "logMode", null, 0, 1, LUWTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTable_LockSizeRow(), ecorePackage.getEBoolean(), "lockSizeRow", null, 0, 1, LUWTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTable_Volatile(), ecorePackage.getEBoolean(), "volatile", null, 0, 1, LUWTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTable_Options(), this.getLUWOption(), null, "options", null, 0, -1, LUWTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTable_SecurityPolicy(), this.getLUWSecurityPolicy(), this.getLUWSecurityPolicy_Table(), "securityPolicy", null, 1, 1, LUWTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwViewEClass, LUWView.class, "LUWView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWView_Federated(), ecorePackage.getEBoolean(), "federated", null, 0, 1, LUWView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWView_OptimizeQuery(), ecorePackage.getEBoolean(), "optimizeQuery", null, 0, 1, LUWView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwPartitionKeyEClass, LUWPartitionKey.class, "LUWPartitionKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWPartitionKey_TemporaryStorageTable(), this.getLUWTemporaryStorageTable(), this.getLUWTemporaryStorageTable_PartitionKey(), "temporaryStorageTable", null, 1, 1, LUWPartitionKey.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWPartitionKey_PartitionMethod(), this.getPartitionMethod(), "partitionMethod", null, 0, 1, LUWPartitionKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWPartitionKey_Table(), this.getLUWStorageTable(), this.getLUWStorageTable_PartitionKey(), "table", null, 1, 1, LUWPartitionKey.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWPartitionKey_Columns(), theSQLTablesPackage.getColumn(), null, "columns", null, 1, -1, LUWPartitionKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwNicknameEClass, LUWNickname.class, "LUWNickname", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWNickname_RemoteDataSet(), this.getRemoteDataSet(), this.getRemoteDataSet_Nickname(), "remoteDataSet", null, 1, 1, LUWNickname.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWNickname_Server(), this.getLUWServer(), this.getLUWServer_Nicknames(), "server", null, 1, 1, LUWNickname.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwFunctionMappingEClass, LUWFunctionMapping.class, "LUWFunctionMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWFunctionMapping_ServerType(), ecorePackage.getEString(), "serverType", null, 0, 1, LUWFunctionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWFunctionMapping_ServerVersion(), ecorePackage.getEString(), "serverVersion", null, 0, 1, LUWFunctionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWFunctionMapping_ServerName(), ecorePackage.getEString(), "serverName", null, 0, 1, LUWFunctionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWFunctionMapping_CreationTime(), theSQLSchemaPackage.getDate(), "creationTime", null, 0, 1, LUWFunctionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWFunctionMapping_Disabled(), ecorePackage.getEBoolean(), "disabled", "false", 0, 1, LUWFunctionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWFunctionMapping_InstsPerInvoc(), ecorePackage.getEInt(), "instsPerInvoc", null, 0, 1, LUWFunctionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWFunctionMapping_InstsPerArgByte(), ecorePackage.getEInt(), "instsPerArgByte", null, 0, 1, LUWFunctionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWFunctionMapping_IosPerInvoc(), ecorePackage.getEInt(), "iosPerInvoc", null, 0, 1, LUWFunctionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWFunctionMapping_IosPerArgByte(), ecorePackage.getEInt(), "iosPerArgByte", null, 0, 1, LUWFunctionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWFunctionMapping_Options(), this.getLUWOption(), null, "options", null, 0, -1, LUWFunctionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWFunctionMapping_LocalFunction(), theDB2ModelPackage.getDB2Function(), null, "localFunction", null, 1, 1, LUWFunctionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWFunctionMapping_RemoteFunction(), theSQLRoutinesPackage.getFunction(), null, "remoteFunction", null, 1, 1, LUWFunctionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWFunctionMapping_LUWDatabase(), this.getLUWDatabase(), this.getLUWDatabase_FunctionMappings(), "LUWDatabase", null, 1, 1, LUWFunctionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwWrapperEClass, LUWWrapper.class, "LUWWrapper", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWWrapper_Version(), ecorePackage.getEString(), "version", null, 0, 1, LUWWrapper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWWrapper_Library(), ecorePackage.getEString(), "library", null, 0, 1, LUWWrapper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWWrapper_Fenced(), ecorePackage.getEBoolean(), "fenced", "true", 0, 1, LUWWrapper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWWrapper_WrapperType(), this.getWrapperType(), "wrapperType", null, 0, 1, LUWWrapper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWWrapper_DataSource(), this.getLUWFederatedDataSource(), "dataSource", null, 0, 1, LUWWrapper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWWrapper_DiscoveredLibraries(), ecorePackage.getEString(), "DiscoveredLibraries", null, 0, -1, LUWWrapper.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWWrapper_Servers(), this.getLUWServer(), this.getLUWServer_Wrapper(), "servers", null, 0, -1, LUWWrapper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWWrapper_LUWDatabase(), this.getLUWDatabase(), this.getLUWDatabase_Wrappers(), "LUWDatabase", null, 1, 1, LUWWrapper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWWrapper_Options(), this.getLUWOption(), null, "options", null, 0, -1, LUWWrapper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwNonRelationalNicknameEClass, LUWNonRelationalNickname.class, "LUWNonRelationalNickname", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWNonRelationalNickname_NonRelServer(), this.getLUWNonRelationalServer(), this.getLUWNonRelationalServer_NonRelNicknames(), "nonRelServer", null, 1, 1, LUWNonRelationalNickname.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwNonRelationalServerEClass, LUWNonRelationalServer.class, "LUWNonRelationalServer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWNonRelationalServer_NonRelWrapper(), this.getLUWNonRelationalWrapper(), this.getLUWNonRelationalWrapper_NonRelServers(), "nonRelWrapper", null, 1, 1, LUWNonRelationalServer.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWNonRelationalServer_NonRelNicknames(), this.getLUWNonRelationalNickname(), this.getLUWNonRelationalNickname_NonRelServer(), "nonRelNicknames", null, 0, -1, LUWNonRelationalServer.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwNonRelationalWrapperEClass, LUWNonRelationalWrapper.class, "LUWNonRelationalWrapper", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWNonRelationalWrapper_NonRelServers(), this.getLUWNonRelationalServer(), this.getLUWNonRelationalServer_NonRelWrapper(), "nonRelServers", null, 0, -1, LUWNonRelationalWrapper.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwRelationalNicknameEClass, LUWRelationalNickname.class, "LUWRelationalNickname", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWRelationalNickname_RelServer(), this.getLUWRelationalServer(), this.getLUWRelationalServer_RelNicknames(), "relServer", null, 1, 1, LUWRelationalNickname.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwGenericUserMappingEClass, LUWGenericUserMapping.class, "LUWGenericUserMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWGenericUserMapping_RemoteUser(), ecorePackage.getEString(), "remoteUser", null, 0, 1, LUWGenericUserMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWGenericUserMapping_RemotePassword(), ecorePackage.getEString(), "remotePassword", null, 0, 1, LUWGenericUserMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwRelationalWrapperEClass, LUWRelationalWrapper.class, "LUWRelationalWrapper", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWRelationalWrapper_RelServers(), this.getLUWRelationalServer(), this.getLUWRelationalServer_RelWrapper(), "relServers", null, 0, -1, LUWRelationalWrapper.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwServerEClass, LUWServer.class, "LUWServer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWServer_ServerType(), ecorePackage.getEString(), "serverType", null, 0, 1, LUWServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWServer_ServerVersion(), ecorePackage.getEString(), "serverVersion", null, 0, 1, LUWServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWServer_UserMappings(), this.getLUWUserMapping(), this.getLUWUserMapping_Server(), "userMappings", null, 0, -1, LUWServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWServer_Wrapper(), this.getLUWWrapper(), this.getLUWWrapper_Servers(), "wrapper", null, 1, 1, LUWServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWServer_Nicknames(), this.getLUWNickname(), this.getLUWNickname_Server(), "nicknames", null, 0, -1, LUWServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWServer_LUWDatabase(), this.getLUWDatabase(), this.getLUWDatabase_Servers(), "LUWDatabase", null, 1, 1, LUWServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWServer_Options(), this.getLUWOption(), null, "options", null, 0, -1, LUWServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWServer_RemoteServer(), this.getRemoteServer(), this.getRemoteServer_LUWServer(), "remoteServer", null, 1, 1, LUWServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwTypeMappingEClass, LUWTypeMapping.class, "LUWTypeMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWTypeMapping_ServerType(), ecorePackage.getEString(), "serverType", null, 0, 1, LUWTypeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTypeMapping_ServerVesion(), ecorePackage.getEString(), "serverVesion", null, 0, 1, LUWTypeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTypeMapping_ServerName(), ecorePackage.getEString(), "serverName", null, 0, 1, LUWTypeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTypeMapping_CreationTime(), theSQLSchemaPackage.getDate(), "creationTime", null, 0, 1, LUWTypeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTypeMapping_LocalType(), theSQLDataTypesPackage.getPredefinedDataType(), null, "localType", null, 1, 1, LUWTypeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTypeMapping_RemoteType(), theSQLDataTypesPackage.getPredefinedDataType(), null, "remoteType", null, 1, 1, LUWTypeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwUserMappingEClass, LUWUserMapping.class, "LUWUserMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWUserMapping_LocalAuthId(), ecorePackage.getEString(), "localAuthId", null, 0, 1, LUWUserMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWUserMapping_Server(), this.getLUWServer(), this.getLUWServer_UserMappings(), "server", null, 1, 1, LUWUserMapping.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWUserMapping_Options(), this.getLUWOption(), null, "options", null, 0, -1, LUWUserMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwOptionEClass, LUWOption.class, "LUWOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWOption_Value(), ecorePackage.getEString(), "value", null, 0, 1, LUWOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwRelationalServerEClass, LUWRelationalServer.class, "LUWRelationalServer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWRelationalServer_CpuRatio(), ecorePackage.getELong(), "cpuRatio", null, 0, 1, LUWRelationalServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWRelationalServer_IoRatio(), ecorePackage.getELong(), "ioRatio", null, 0, 1, LUWRelationalServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWRelationalServer_CommRate(), ecorePackage.getELong(), "commRate", null, 0, 1, LUWRelationalServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWRelationalServer_FoldId(), ecorePackage.getEBoolean(), "foldId", null, 0, 1, LUWRelationalServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWRelationalServer_FoldPW(), ecorePackage.getEBoolean(), "foldPW", null, 0, 1, LUWRelationalServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWRelationalServer_CollatingSequence(), ecorePackage.getEBoolean(), "collatingSequence", null, 0, 1, LUWRelationalServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWRelationalServer_Pushdown(), ecorePackage.getEBoolean(), "pushdown", null, 0, 1, LUWRelationalServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWRelationalServer_Node(), ecorePackage.getEString(), "node", null, 0, 1, LUWRelationalServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWRelationalServer_DbName(), ecorePackage.getEString(), "dbName", null, 0, 1, LUWRelationalServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWRelationalServer_IudAppSvptEnforce(), ecorePackage.getEBoolean(), "iudAppSvptEnforce", null, 0, 1, LUWRelationalServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWRelationalServer_Password(), ecorePackage.getEString(), "password", null, 0, 1, LUWRelationalServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWRelationalServer_RelNicknames(), this.getLUWRelationalNickname(), this.getLUWRelationalNickname_RelServer(), "relNicknames", null, 0, -1, LUWRelationalServer.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWRelationalServer_RelWrapper(), this.getLUWRelationalWrapper(), this.getLUWRelationalWrapper_RelServers(), "relWrapper", null, 1, 1, LUWRelationalServer.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(luwRelationalServerEClass, theEcorePackage.getEEList(), "getFunctionMappings", 0, 1); //$NON-NLS-1$

		addEOperation(luwRelationalServerEClass, theEcorePackage.getEEList(), "getTypeMappings", 0, 1); //$NON-NLS-1$

		addEOperation(luwRelationalServerEClass, theEcorePackage.getEEList(), "getReverseTypeMappings", 0, 1); //$NON-NLS-1$

		initEClass(luwDatabaseEClass, LUWDatabase.class, "LUWDatabase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWDatabase_Federated(), ecorePackage.getEBoolean(), "federated", null, 0, 1, LUWDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabase_Groups(), this.getLUWPartitionGroup(), this.getLUWPartitionGroup_Database(), "groups", null, 0, -1, LUWDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabase_Wrappers(), this.getLUWWrapper(), this.getLUWWrapper_LUWDatabase(), "wrappers", null, 0, -1, LUWDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabase_Servers(), this.getLUWServer(), this.getLUWServer_LUWDatabase(), "servers", null, 0, -1, LUWDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabase_FunctionMappings(), this.getLUWFunctionMapping(), this.getLUWFunctionMapping_LUWDatabase(), "functionMappings", null, 0, -1, LUWDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabase_TypeMappings(), this.getLUWTypeMapping(), null, "typeMappings", null, 0, -1, LUWDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabase_ReverseTypeMappings(), this.getLUWTypeMapping(), null, "reverseTypeMappings", null, 0, -1, LUWDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabase_Bufferpools(), this.getLUWBufferPool(), this.getLUWBufferPool_Database(), "bufferpools", null, 1, -1, LUWDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabase_Tablespaces(), this.getLUWTableSpace(), this.getLUWTableSpace_Database(), "tablespaces", null, 0, -1, LUWDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabase_StorageGroups(), this.getLUWStorageGroup(), this.getLUWStorageGroup_Database(), "storageGroups", null, 0, -1, LUWDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDatabase_DefaultStorageGroup(), this.getLUWStorageGroup(), null, "defaultStorageGroup", null, 0, 1, LUWDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwColumnEClass, LUWColumn.class, "LUWColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWColumn_LobLogged(), ecorePackage.getEBoolean(), "lobLogged", "true", 0, 1, LUWColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWColumn_LobCompacted(), ecorePackage.getEBoolean(), "lobCompacted", null, 0, 1, LUWColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWColumn_Compression(), ecorePackage.getEString(), "compression", null, 0, 1, LUWColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWColumn_InlineLength(), ecorePackage.getEInt(), "inlineLength", null, 0, 1, LUWColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWColumn_Hidden(), ecorePackage.getEBoolean(), "hidden", "false", 0, 1, LUWColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWColumn_SecurityLabel(), ecorePackage.getEString(), "securityLabel", null, 0, 1, LUWColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWColumn_Options(), this.getLUWOption(), null, "options", null, 0, -1, LUWColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwGenericNicknameEClass, LUWGenericNickname.class, "LUWGenericNickname", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWGenericNickname_GenericServer(), this.getLUWGenericServer(), this.getLUWGenericServer_GenericNicknames(), "genericServer", null, 1, 1, LUWGenericNickname.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		EOperation op = addEOperation(luwGenericNicknameEClass, null, "setServer"); //$NON-NLS-1$
		addEParameter(op, this.getLUWServer(), "newGenericServer", 0, 1); //$NON-NLS-1$

		initEClass(luwGenericServerEClass, LUWGenericServer.class, "LUWGenericServer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWGenericServer_GenericNicknames(), this.getLUWGenericNickname(), this.getLUWGenericNickname_GenericServer(), "genericNicknames", null, 0, -1, LUWGenericServer.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWGenericServer_GenericWrapper(), this.getLUWGenericWrapper(), this.getLUWGenericWrapper_GenericServers(), "genericWrapper", null, 1, 1, LUWGenericServer.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(luwGenericServerEClass, theEcorePackage.getEEList(), "getNicknames", 0, 1); //$NON-NLS-1$

		op = addEOperation(luwGenericServerEClass, null, "setWrapper"); //$NON-NLS-1$
		addEParameter(op, this.getLUWWrapper(), "newGenericWrapper", 0, 1); //$NON-NLS-1$

		initEClass(luwMaterializedQueryTableEClass, LUWMaterializedQueryTable.class, "LUWMaterializedQueryTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(luwGenericWrapperEClass, LUWGenericWrapper.class, "LUWGenericWrapper", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWGenericWrapper_GenericServers(), this.getLUWGenericServer(), this.getLUWGenericServer_GenericWrapper(), "genericServers", null, 0, -1, LUWGenericWrapper.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(luwGenericWrapperEClass, theEcorePackage.getEEList(), "getServers", 0, 1); //$NON-NLS-1$

		initEClass(luwStorageTableEClass, LUWStorageTable.class, "LUWStorageTable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWStorageTable_ValueCompression(), ecorePackage.getEBoolean(), "valueCompression", null, 0, 1, LUWStorageTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWStorageTable_RowCompression(), ecorePackage.getEBoolean(), "rowCompression", null, 0, 1, LUWStorageTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWStorageTable_RowCompressionEmpty(), ecorePackage.getEBoolean(), "rowCompressionEmpty", "True", 0, 1, LUWStorageTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWStorageTable_CompressionMode(), this.getLUWStorageTableCompressionMode(), "compressionMode", null, 0, 1, LUWStorageTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWStorageTable_PartitionKey(), this.getLUWPartitionKey(), this.getLUWPartitionKey_Table(), "partitionKey", null, 0, 1, LUWStorageTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWStorageTable_IndexDataTableSpace(), this.getLUWTableSpace(), this.getLUWTableSpace_IndexDataTables(), "indexDataTableSpace", null, 0, 1, LUWStorageTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWStorageTable_LOBDataTableSpace(), this.getLUWTableSpace(), this.getLUWTableSpace_LOBDataTables(), "LOBDataTableSpace", null, 0, 1, LUWStorageTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWStorageTable_RegularDataTableSpace(), this.getLUWTableSpace(), this.getLUWTableSpace_RegularDataTables(), "regularDataTableSpace", null, 1, 1, LUWStorageTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWStorageTable_DataPartitions(), this.getLUWDataPartition(), this.getLUWDataPartition_Table(), "dataPartitions", null, 0, -1, LUWStorageTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWStorageTable_DataPartitionKey(), this.getLUWDataPartitionKey(), this.getLUWDataPartitionKey_Table(), "dataPartitionKey", null, 0, 1, LUWStorageTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(luwStorageTableEClass, theSQLSchemaPackage.getList(), "getTableSpaces", 0, 1); //$NON-NLS-1$

		initEClass(relationalRemoteServerEClass, RelationalRemoteServer.class, "RelationalRemoteServer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRelationalRemoteServer_Database(), theSQLSchemaPackage.getDatabase(), null, "database", null, 0, 1, RelationalRemoteServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(relationalRemoteDataSetEClass, RelationalRemoteDataSet.class, "RelationalRemoteDataSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRelationalRemoteDataSet_Table(), theSQLTablesPackage.getBaseTable(), null, "table", null, 0, 1, RelationalRemoteDataSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(remoteServerEClass, RemoteServer.class, "RemoteServer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRemoteServer_LUWServer(), this.getLUWServer(), this.getLUWServer_RemoteServer(), "LUWServer", null, 1, 1, RemoteServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(remoteDataSetEClass, RemoteDataSet.class, "RemoteDataSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRemoteDataSet_Nickname(), this.getLUWNickname(), this.getLUWNickname_RemoteDataSet(), "nickname", null, 0, -1, RemoteDataSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwIndexEClass, LUWIndex.class, "LUWIndex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWIndex_PCTFree(), ecorePackage.getEInt(), "PCTFree", "-1", 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWIndex_MinPCTFree(), ecorePackage.getEInt(), "minPCTFree", null, 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWIndex_ReverseScan(), ecorePackage.getEBoolean(), "reverseScan", null, 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWIndex_NotPartitioned(), ecorePackage.getEBoolean(), "notPartitioned", null, 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWIndex_XmlPattern(), ecorePackage.getEString(), "xmlPattern", null, 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWIndex_AsSQLDataType(), theSQLDataTypesPackage.getPredefinedDataType(), null, "asSQLDataType", null, 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWIndex_AsSQLDataTypeHashed(), ecorePackage.getEBoolean(), "asSQLDataTypeHashed", null, 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWIndex_SystemRequired(), ecorePackage.getEBoolean(), "systemRequired", null, 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWIndex_PageSplitType(), this.getLUWIndexPageSplitType(), "pageSplitType", "SYMMETRIC", 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWIndex_Level2PctFree(), ecorePackage.getEInt(), "level2PctFree", "-1", 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWIndex_MinPctUsed(), ecorePackage.getEInt(), "minPctUsed", null, 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWIndex_Compress(), this.getLUWIndexCompressType(), "compress", "NO", 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWIndex_CollectStats(), ecorePackage.getEBoolean(), "collectStats", "true", 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWIndex_SampledStats(), ecorePackage.getEBoolean(), "sampledStats", "true", 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWIndex_DetailedStats(), ecorePackage.getEBoolean(), "detailedStats", "true", 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWIndex_IgnoreInvalidValues(), ecorePackage.getEBoolean(), "ignoreInvalidValues", "true", 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWIndex_ExcludeNullKeys(), ecorePackage.getEBoolean(), "excludeNullKeys", "false", 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getLUWIndex_Tablespace(), this.getLUWTableSpace(), this.getLUWTableSpace_Indexes(), "tablespace", null, 0, 1, LUWIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwAttributeDefinitionEClass, LUWAttributeDefinition.class, "LUWAttributeDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWAttributeDefinition_LOBLogged(), ecorePackage.getEBoolean(), "LOBLogged", "true", 0, 1, LUWAttributeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLUWAttributeDefinition_LOBCompacted(), ecorePackage.getEBoolean(), "LOBCompacted", null, 0, 1, LUWAttributeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(federatedProcedureEClass, FederatedProcedure.class, "FederatedProcedure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFederatedProcedure_RemoteUniqueId(), ecorePackage.getEString(), "remoteUniqueId", null, 0, 1, FederatedProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFederatedProcedure_RemoteServer(), ecorePackage.getEString(), "remoteServer", null, 0, 1, FederatedProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFederatedProcedure_RemoteSchema(), ecorePackage.getEString(), "remoteSchema", null, 0, 1, FederatedProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFederatedProcedure_RemotePackage(), ecorePackage.getEString(), "remotePackage", null, 0, 1, FederatedProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFederatedProcedure_RemoteProcedureName(), ecorePackage.getEString(), "remoteProcedureName", null, 0, 1, FederatedProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFederatedProcedure_NumberOfParameters(), ecorePackage.getEInt(), "numberOfParameters", null, 0, 1, FederatedProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFederatedProcedure_ResultSetsToClient(), ecorePackage.getEString(), "resultSetsToClient", null, 0, 1, FederatedProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFederatedProcedure_NumberOfRefCursors(), ecorePackage.getEInt(), "numberOfRefCursors", null, 0, 1, FederatedProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFederatedProcedure_AllResultSetsToCaller(), ecorePackage.getEBoolean(), "allResultSetsToCaller", null, 0, 1, FederatedProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFederatedProcedure_FederatedProcedure(), this.getFederatedProcedure(), null, "FederatedProcedure", null, 0, -1, FederatedProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFederatedProcedure_RemoteProcedure(), theSQLRoutinesPackage.getProcedure(), null, "remoteProcedure", null, 0, -1, FederatedProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFederatedProcedure_FederatedParameter(), this.getFederatedParameter(), this.getFederatedParameter_FederatedProcedure(), "federatedParameter", null, 0, -1, FederatedProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(federatedParameterEClass, FederatedParameter.class, "FederatedParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFederatedParameter_RemoteCodePage(), ecorePackage.getEInt(), "remoteCodePage", null, 0, 1, FederatedParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFederatedParameter_RemoteParamTypeID(), ecorePackage.getEInt(), "remoteParamTypeID", null, 0, 1, FederatedParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFederatedParameter_FederatedProcedure(), this.getFederatedProcedure(), this.getFederatedProcedure_FederatedParameter(), "federatedProcedure", null, 1, 1, FederatedParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFederatedParameter_RemoteParameter(), theSQLRoutinesPackage.getParameter(), null, "remoteParameter", null, 0, -1, FederatedParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwPartitionExpressionEClass, LUWPartitionExpression.class, "LUWPartitionExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWPartitionExpression_NullsLast(), ecorePackage.getEBoolean(), "nullsLast", "true", 0, 1, LUWPartitionExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getLUWPartitionExpression_Key(), this.getLUWDataPartitionKey(), this.getLUWDataPartitionKey_PartitionExpressions(), "key", null, 1, 1, LUWPartitionExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWPartitionExpression_Column(), theSQLTablesPackage.getColumn(), null, "column", null, 1, 1, LUWPartitionExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWPartitionExpression_PartitionElements(), this.getLUWPartitionElement(), this.getLUWPartitionElement_LUWPartitionExpression(), "partitionElements", null, 1, -1, LUWPartitionExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwPartitionElementEClass, LUWPartitionElement.class, "LUWPartitionElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWPartitionElement_Starting(), ecorePackage.getEString(), "starting", null, 0, 1, LUWPartitionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWPartitionElement_Ending(), ecorePackage.getEString(), "ending", null, 0, 1, LUWPartitionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWPartitionElement_LUWPartitionExpression(), this.getLUWPartitionExpression(), this.getLUWPartitionExpression_PartitionElements(), "LUWPartitionExpression", null, 1, 1, LUWPartitionElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWPartitionElement_Partition(), this.getLUWDataPartition(), this.getLUWDataPartition_PartitionElements(), "partition", null, 1, 1, LUWPartitionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWPartitionElement_EveryClause(), this.getLUWPartitionEveryClauseElement(), this.getLUWPartitionEveryClauseElement_LUWPartitionElement(), "everyClause", null, 0, 1, LUWPartitionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(luwPartitionElementEClass, ecorePackage.getEBooleanObject(), "hasEveryClause", 0, 1); //$NON-NLS-1$

		initEClass(luwDataPartitionEClass, LUWDataPartition.class, "LUWDataPartition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWDataPartition_Id(), ecorePackage.getEInt(), "id", null, 0, 1, LUWDataPartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWDataPartition_LowInclusive(), ecorePackage.getEBoolean(), "lowInclusive", null, 0, 1, LUWDataPartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWDataPartition_HighInclusive(), ecorePackage.getEBoolean(), "highInclusive", null, 0, 1, LUWDataPartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDataPartition_LOBDataTableSpace(), this.getLUWTableSpace(), this.getLUWTableSpace_LOBDataPartition(), "LOBDataTableSpace", null, 0, 1, LUWDataPartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDataPartition_RegularDataTableSpace(), this.getLUWTableSpace(), this.getLUWTableSpace_RegularDataPartition(), "regularDataTableSpace", null, 0, 1, LUWDataPartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDataPartition_PartitionElements(), this.getLUWPartitionElement(), this.getLUWPartitionElement_Partition(), "partitionElements", null, 1, -1, LUWDataPartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDataPartition_Table(), this.getLUWStorageTable(), this.getLUWStorageTable_DataPartitions(), "table", null, 1, 1, LUWDataPartition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDataPartition_IndexDataTableSpace(), this.getLUWTableSpace(), this.getLUWTableSpace_IndexDataPartition(), "indexDataTableSpace", null, 0, 1, LUWDataPartition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwDataPartitionKeyEClass, LUWDataPartitionKey.class, "LUWDataPartitionKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWDataPartitionKey_PartitionMethod(), this.getDataPartitionMethod(), "partitionMethod", null, 0, 1, LUWDataPartitionKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDataPartitionKey_PartitionExpressions(), this.getLUWPartitionExpression(), this.getLUWPartitionExpression_Key(), "partitionExpressions", null, 1, -1, LUWDataPartitionKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWDataPartitionKey_Table(), this.getLUWStorageTable(), this.getLUWStorageTable_DataPartitionKey(), "table", null, 1, 1, LUWDataPartitionKey.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwDatabasePackageEClass, LUWDatabasePackage.class, "LUWDatabasePackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWDatabasePackage_Creator(), ecorePackage.getEString(), "creator", null, 0, 1, LUWDatabasePackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWDatabasePackage_Binder(), ecorePackage.getEString(), "binder", null, 0, 1, LUWDatabasePackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWDatabasePackage_CursorBlock(), this.getCursorBlockType(), "cursorBlock", null, 0, 1, LUWDatabasePackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWDatabasePackage_NumberOfSections(), ecorePackage.getEInt(), "numberOfSections", null, 0, 1, LUWDatabasePackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWDatabasePackage_OptimizationClass(), ecorePackage.getEInt(), "optimizationClass", null, 0, 1, LUWDatabasePackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWDatabasePackage_ExplainSnapshot(), this.getExplainSnaphotType(), "explainSnapshot", null, 0, 1, LUWDatabasePackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwModuleEClass, LUWModule.class, "LUWModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWModule_Dialect(), theDB2ModelPackage.getSourceDialect(), "dialect", null, 0, 1, LUWModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWModule_OwningSchema(), theDB2ModelPackage.getDB2Schema(), theDB2ModelPackage.getDB2Schema_Modules(), "owningSchema", null, 1, 1, LUWModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWModule_ModuleObjects(), this.getLUWModuleObject(), this.getLUWModuleObject_Module(), "moduleObjects", null, 0, -1, LUWModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwModuleObjectEClass, LUWModuleObject.class, "LUWModuleObject", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWModuleObject_Published(), ecorePackage.getEBoolean(), "published", null, 0, 1, LUWModuleObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWModuleObject_Module(), this.getLUWModule(), this.getLUWModule_ModuleObjects(), "module", null, 1, 1, LUWModuleObject.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwModuleFunctionEClass, LUWModuleFunction.class, "LUWModuleFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWModuleFunction_Implemented(), ecorePackage.getEBoolean(), "implemented", null, 0, 1, LUWModuleFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwModuleProcedureEClass, LUWModuleProcedure.class, "LUWModuleProcedure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWModuleProcedure_Implemented(), ecorePackage.getEBoolean(), "implemented", null, 0, 1, LUWModuleProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwModuleConditionEClass, LUWModuleCondition.class, "LUWModuleCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWModuleCondition_Sqlstate(), ecorePackage.getEString(), "sqlstate", null, 0, 1, LUWModuleCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwGlobalVariableEClass, LUWGlobalVariable.class, "LUWGlobalVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWGlobalVariable_DefaultValue(), ecorePackage.getEString(), "defaultValue", null, 0, 1, LUWGlobalVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWGlobalVariable_IsConstant(), ecorePackage.getEBoolean(), "isConstant", null, 0, 1, LUWGlobalVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWGlobalVariable_Schema(), theDB2ModelPackage.getDB2Schema(), theDB2ModelPackage.getDB2Schema_GlobalVariables(), "schema", null, 1, 1, LUWGlobalVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwModuleTypeEClass, LUWModuleType.class, "LUWModuleType", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(luwModuleRowDataTypeEClass, LUWModuleRowDataType.class, "LUWModuleRowDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(luwModuleArrayDataTypeEClass, LUWModuleArrayDataType.class, "LUWModuleArrayDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(luwModuleDistinctTypeEClass, LUWModuleDistinctType.class, "LUWModuleDistinctType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(luwModuleGlobalVariableEClass, LUWModuleGlobalVariable.class, "LUWModuleGlobalVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(luwArrayDataTypeEClass, LUWArrayDataType.class, "LUWArrayDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWArrayDataType_ArrayIndexElementType(), this.getArrayIndexElementType(), this.getArrayIndexElementType_LUWArrayDataType(), "arrayIndexElementType", null, 0, 1, LUWArrayDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwRowDataTypeEClass, LUWRowDataType.class, "LUWRowDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(plsqlPackageEClass, PLSQLPackage.class, "PLSQLPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getPLSQLPackage_PackageBody(), this.getPLSQLPackageBody(), this.getPLSQLPackageBody_Package(), "packageBody", null, 0, 1, PLSQLPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(plsqlPackageBodyEClass, PLSQLPackageBody.class, "PLSQLPackageBody", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getPLSQLPackageBody_Package(), this.getPLSQLPackage(), this.getPLSQLPackage_PackageBody(), "package", null, 1, 1, PLSQLPackageBody.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwCursorDataTypeEClass, LUWCursorDataType.class, "LUWCursorDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWCursorDataType_RowType(), this.getLUWRowDataType(), null, "rowType", null, 0, 1, LUWCursorDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwModuleCursorDataTypeEClass, LUWModuleCursorDataType.class, "LUWModuleCursorDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(luwBufferPoolSizeExceptionEClass, LUWBufferPoolSizeException.class, "LUWBufferPoolSizeException", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWBufferPoolSizeException_Size(), ecorePackage.getEInt(), "size", null, 0, 1, LUWBufferPoolSizeException.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWBufferPoolSizeException_BufferPool(), this.getLUWBufferPool(), this.getLUWBufferPool_SizeException(), "bufferPool", null, 1, 1, LUWBufferPoolSizeException.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWBufferPoolSizeException_Partitions(), this.getLUWDatabasePartition(), this.getLUWDatabasePartition_SizeException(), "partitions", null, 0, -1, LUWBufferPoolSizeException.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwMemberEClass, LUWMember.class, "LUWMember", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWMember_Type(), this.getLUWMemberType(), "type", null, 0, 1, LUWMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWMember_Alert(), ecorePackage.getEString(), "alert", null, 0, 1, LUWMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWMember_DbPartitionNum(), ecorePackage.getEInt(), "dbPartitionNum", null, 0, 1, LUWMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWMember_LogicalPort(), ecorePackage.getEInt(), "logicalPort", null, 0, 1, LUWMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWMember_NetName(), ecorePackage.getEString(), "netName", null, 0, 1, LUWMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwSecurityPolicyEClass, LUWSecurityPolicy.class, "LUWSecurityPolicy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWSecurityPolicy_NotAuthorizedWrite(), this.getLUWSecurityLabelNotAuthorizedWriteAction(), "notAuthorizedWrite", null, 0, 1, LUWSecurityPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWSecurityPolicy_Components(), this.getLUWSecurityLabelComponent(), this.getLUWSecurityLabelComponent_LUWSecurityPolicy(), "components", null, 0, -1, LUWSecurityPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWSecurityPolicy_Labels(), this.getLUWSecurityLabel(), this.getLUWSecurityLabel_Policy(), "labels", null, 0, -1, LUWSecurityPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWSecurityPolicy_Table(), this.getLUWTable(), this.getLUWTable_SecurityPolicy(), "table", null, 1, 1, LUWSecurityPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwSecurityLabelComponentEClass, LUWSecurityLabelComponent.class, "LUWSecurityLabelComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWSecurityLabelComponent_Type(), this.getLUWSecurityLabelComponentType(), "type", null, 0, 1, LUWSecurityLabelComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWSecurityLabelComponent_LUWSecurityPolicy(), this.getLUWSecurityPolicy(), this.getLUWSecurityPolicy_Components(), "LUWSecurityPolicy", null, 0, -1, LUWSecurityLabelComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWSecurityLabelComponent_Elements(), this.getLUWSecurityLabelComponentElement(), this.getLUWSecurityLabelComponentElement_LUWSecurityLabelComponent(), "elements", null, 0, -1, LUWSecurityLabelComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwSecurityLabelEClass, LUWSecurityLabel.class, "LUWSecurityLabel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWSecurityLabel_SecurityLabel(), ecorePackage.getEString(), "securityLabel", null, 0, 1, LUWSecurityLabel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWSecurityLabel_Policy(), this.getLUWSecurityPolicy(), this.getLUWSecurityPolicy_Labels(), "policy", null, 1, 1, LUWSecurityLabel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwSecurityLabelComponentElementEClass, LUWSecurityLabelComponentElement.class, "LUWSecurityLabelComponentElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWSecurityLabelComponentElement_Value(), ecorePackage.getEString(), "value", null, 0, 1, LUWSecurityLabelComponentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWSecurityLabelComponentElement_ParentValue(), ecorePackage.getEString(), "parentValue", null, 0, 1, LUWSecurityLabelComponentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWSecurityLabelComponentElement_LUWSecurityLabelComponent(), this.getLUWSecurityLabelComponent(), this.getLUWSecurityLabelComponent_Elements(), "LUWSecurityLabelComponent", null, 0, -1, LUWSecurityLabelComponentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwStorageGroupEClass, LUWStorageGroup.class, "LUWStorageGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWStorageGroup_StoragePaths(), ecorePackage.getEString(), "storagePaths", null, 1, -1, LUWStorageGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWStorageGroup_Overhead(), ecorePackage.getEDouble(), "overhead", null, 0, 1, LUWStorageGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWStorageGroup_DeviceReadRate(), ecorePackage.getEDouble(), "deviceReadRate", null, 0, 1, LUWStorageGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWStorageGroup_DataTag(), ecorePackage.getEString(), "dataTag", null, 0, 1, LUWStorageGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWStorageGroup_Default(), ecorePackage.getEBoolean(), "default", null, 0, 1, LUWStorageGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWStorageGroup_Database(), this.getLUWDatabase(), this.getLUWDatabase_StorageGroups(), "database", null, 1, 1, LUWStorageGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWStorageGroup_TableSpaces(), this.getLUWTableSpace(), this.getLUWTableSpace_StorageGroup(), "tableSpaces", null, 0, -1, LUWStorageGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwTemporaryStorageTableEClass, LUWTemporaryStorageTable.class, "LUWTemporaryStorageTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWTemporaryStorageTable_PartitionKey(), this.getLUWPartitionKey(), this.getLUWPartitionKey_TemporaryStorageTable(), "partitionKey", null, 0, 1, LUWTemporaryStorageTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWTemporaryStorageTable_UserTemporaryTableSpace(), this.getLUWTableSpace(), this.getLUWTableSpace_TemporaryStorageTables(), "userTemporaryTableSpace", null, 0, 1, LUWTemporaryStorageTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwTemporaryTableEClass, LUWTemporaryTable.class, "LUWTemporaryTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLUWTemporaryTable_Table(), theSQLTablesPackage.getTable(), null, "table", null, 0, 1, LUWTemporaryTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWTemporaryTable_LogOption(), this.getLUWTemporaryTableLoggingOption(), "logOption", null, 0, 1, LUWTemporaryTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(arrayIndexElementTypeEClass, ArrayIndexElementType.class, "ArrayIndexElementType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getArrayIndexElementType_LUWArrayDataType(), this.getLUWArrayDataType(), this.getLUWArrayDataType_ArrayIndexElementType(), "LUWArrayDataType", null, 1, 1, ArrayIndexElementType.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(luwPartitionEveryClauseElementEClass, LUWPartitionEveryClauseElement.class, "LUWPartitionEveryClauseElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLUWPartitionEveryClauseElement_Value(), ecorePackage.getEDoubleObject(), "value", null, 0, 1, LUWPartitionEveryClauseElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLUWPartitionEveryClauseElement_Duration(), ecorePackage.getEString(), "duration", null, 0, 1, LUWPartitionEveryClauseElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLUWPartitionEveryClauseElement_LUWPartitionElement(), this.getLUWPartitionElement(), this.getLUWPartitionElement_EveryClause(), "LUWPartitionElement", null, 1, 1, LUWPartitionEveryClauseElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(luwContainerTypeEEnum, LUWContainerType.class, "LUWContainerType"); //$NON-NLS-1$
		addEEnumLiteral(luwContainerTypeEEnum, LUWContainerType.DEVICE_LITERAL);
		addEEnumLiteral(luwContainerTypeEEnum, LUWContainerType.DIRECTORY_LITERAL);
		addEEnumLiteral(luwContainerTypeEEnum, LUWContainerType.FILE_LITERAL);

		initEEnum(pageSizeTypeEEnum, PageSizeType.class, "PageSizeType"); //$NON-NLS-1$
		addEEnumLiteral(pageSizeTypeEEnum, PageSizeType.FOUR_K_LITERAL);
		addEEnumLiteral(pageSizeTypeEEnum, PageSizeType.EIGHT_K_LITERAL);
		addEEnumLiteral(pageSizeTypeEEnum, PageSizeType.SIXTEEN_K_LITERAL);
		addEEnumLiteral(pageSizeTypeEEnum, PageSizeType.THIRTY_TWO_K_LITERAL);
		addEEnumLiteral(pageSizeTypeEEnum, PageSizeType.FOUR_KB_LITERAL);
		addEEnumLiteral(pageSizeTypeEEnum, PageSizeType.EIGHT_KB_LITERAL);
		addEEnumLiteral(pageSizeTypeEEnum, PageSizeType.SIXTEEN_KB_LITERAL);
		addEEnumLiteral(pageSizeTypeEEnum, PageSizeType.THIRTY_TWO_KB_LITERAL);

		initEEnum(bufferPoolTypeEEnum, BufferPoolType.class, "BufferPoolType"); //$NON-NLS-1$
		addEEnumLiteral(bufferPoolTypeEEnum, BufferPoolType.IMMEDIATE_LITERAL);
		addEEnumLiteral(bufferPoolTypeEEnum, BufferPoolType.DEFERRED_LITERAL);

		initEEnum(tableSpaceTypeEEnum, TableSpaceType.class, "TableSpaceType"); //$NON-NLS-1$
		addEEnumLiteral(tableSpaceTypeEEnum, TableSpaceType.REGULAR_LITERAL);
		addEEnumLiteral(tableSpaceTypeEEnum, TableSpaceType.LARGE_LITERAL);
		addEEnumLiteral(tableSpaceTypeEEnum, TableSpaceType.SYSTEM_TEMP_LITERAL);
		addEEnumLiteral(tableSpaceTypeEEnum, TableSpaceType.USER_TEMP_LITERAL);

		initEEnum(managementTypeEEnum, ManagementType.class, "ManagementType"); //$NON-NLS-1$
		addEEnumLiteral(managementTypeEEnum, ManagementType.SYSTEM_MANAGED_LITERAL);
		addEEnumLiteral(managementTypeEEnum, ManagementType.DATABASE_MANAGED_LITERAL);
		addEEnumLiteral(managementTypeEEnum, ManagementType.AUTOMATIC_STORAGE_LITERAL);

		initEEnum(checkOptionTypeEEnum, CheckOptionType.class, "CheckOptionType"); //$NON-NLS-1$
		addEEnumLiteral(checkOptionTypeEEnum, CheckOptionType.NONE_LITERAL);
		addEEnumLiteral(checkOptionTypeEEnum, CheckOptionType.CASCADED_LITERAL);
		addEEnumLiteral(checkOptionTypeEEnum, CheckOptionType.LOCAL_LITERAL);

		initEEnum(partitionMethodEEnum, PartitionMethod.class, "PartitionMethod"); //$NON-NLS-1$
		addEEnumLiteral(partitionMethodEEnum, PartitionMethod.HASHING_LITERAL);
		addEEnumLiteral(partitionMethodEEnum, PartitionMethod.TABLE_REPLICATED_LITERAL);

		initEEnum(maintenanceTypeEEnum, MaintenanceType.class, "MaintenanceType"); //$NON-NLS-1$
		addEEnumLiteral(maintenanceTypeEEnum, MaintenanceType.SYSTEM_LITERAL);
		addEEnumLiteral(maintenanceTypeEEnum, MaintenanceType.USER_LITERAL);

		initEEnum(refreshTypeEEnum, RefreshType.class, "RefreshType"); //$NON-NLS-1$
		addEEnumLiteral(refreshTypeEEnum, RefreshType.DEFERRED_LITERAL);
		addEEnumLiteral(refreshTypeEEnum, RefreshType.IMMEDIATE_LITERAL);

		initEEnum(wrapperTypeEEnum, WrapperType.class, "WrapperType"); //$NON-NLS-1$
		addEEnumLiteral(wrapperTypeEEnum, WrapperType.RELATIONAL_LITERAL);
		addEEnumLiteral(wrapperTypeEEnum, WrapperType.NON_RELATIONAL_LITERAL);

		initEEnum(dataPartitionMethodEEnum, DataPartitionMethod.class, "DataPartitionMethod"); //$NON-NLS-1$
		addEEnumLiteral(dataPartitionMethodEEnum, DataPartitionMethod.RANGE_LITERAL);

		initEEnum(cursorBlockTypeEEnum, CursorBlockType.class, "CursorBlockType"); //$NON-NLS-1$
		addEEnumLiteral(cursorBlockTypeEEnum, CursorBlockType.BLOCK_UNAMBIGUOUS_CURSORS_LITERAL);
		addEEnumLiteral(cursorBlockTypeEEnum, CursorBlockType.BLOCK_ALL_CURSORS_LITERAL);
		addEEnumLiteral(cursorBlockTypeEEnum, CursorBlockType.NO_BLOCKING_LITERAL);

		initEEnum(explainSnaphotTypeEEnum, ExplainSnaphotType.class, "ExplainSnaphotType"); //$NON-NLS-1$
		addEEnumLiteral(explainSnaphotTypeEEnum, ExplainSnaphotType.ALL_LITERAL);
		addEEnumLiteral(explainSnaphotTypeEEnum, ExplainSnaphotType.NO_LITERAL);
		addEEnumLiteral(explainSnaphotTypeEEnum, ExplainSnaphotType.REOPT_LITERAL);
		addEEnumLiteral(explainSnaphotTypeEEnum, ExplainSnaphotType.YES_LITERAL);

		initEEnum(fileSystemCachingTypeEEnum, FileSystemCachingType.class, "FileSystemCachingType"); //$NON-NLS-1$
		addEEnumLiteral(fileSystemCachingTypeEEnum, FileSystemCachingType.NONE_LITERAL);
		addEEnumLiteral(fileSystemCachingTypeEEnum, FileSystemCachingType.FILE_CACHING_LITERAL);
		addEEnumLiteral(fileSystemCachingTypeEEnum, FileSystemCachingType.NO_FILE_CACHING_LITERAL);

		initEEnum(luwIndexPageSplitTypeEEnum, LUWIndexPageSplitType.class, "LUWIndexPageSplitType"); //$NON-NLS-1$
		addEEnumLiteral(luwIndexPageSplitTypeEEnum, LUWIndexPageSplitType.NO_SELECTION_LITERAL);
		addEEnumLiteral(luwIndexPageSplitTypeEEnum, LUWIndexPageSplitType.SYMMETRIC_LITERAL);
		addEEnumLiteral(luwIndexPageSplitTypeEEnum, LUWIndexPageSplitType.HIGH_LITERAL);
		addEEnumLiteral(luwIndexPageSplitTypeEEnum, LUWIndexPageSplitType.LOW_LITERAL);

		initEEnum(luwIndexCompressTypeEEnum, LUWIndexCompressType.class, "LUWIndexCompressType"); //$NON-NLS-1$
		addEEnumLiteral(luwIndexCompressTypeEEnum, LUWIndexCompressType.NO_SELECTION_LITERAL);
		addEEnumLiteral(luwIndexCompressTypeEEnum, LUWIndexCompressType.NO_LITERAL);
		addEEnumLiteral(luwIndexCompressTypeEEnum, LUWIndexCompressType.YES_LITERAL);

		initEEnum(systemTypeEEnum, SystemType.class, "SystemType"); //$NON-NLS-1$
		addEEnumLiteral(systemTypeEEnum, SystemType.SATA_LITERAL);
		addEEnumLiteral(systemTypeEEnum, SystemType.SAS_10K_LITERAL);
		addEEnumLiteral(systemTypeEEnum, SystemType.SAS_15K_LITERAL);
		addEEnumLiteral(systemTypeEEnum, SystemType.SSD_LITERAL);

		initEEnum(averageTableSizeTypeEEnum, AverageTableSizeType.class, "AverageTableSizeType"); //$NON-NLS-1$
		addEEnumLiteral(averageTableSizeTypeEEnum, AverageTableSizeType.LESS_THAN_50MB_LITERAL);
		addEEnumLiteral(averageTableSizeTypeEEnum, AverageTableSizeType.BETWEEN_50MB_AND_500MB_LITERAL);
		addEEnumLiteral(averageTableSizeTypeEEnum, AverageTableSizeType.BETWEEN_500MB_AND_5GB_LITERAL);
		addEEnumLiteral(averageTableSizeTypeEEnum, AverageTableSizeType.GREATER_THAN_5GB_LITERAL);

		initEEnum(luwStorageTableCompressionModeEEnum, LUWStorageTableCompressionMode.class, "LUWStorageTableCompressionMode"); //$NON-NLS-1$
		addEEnumLiteral(luwStorageTableCompressionModeEEnum, LUWStorageTableCompressionMode.NO_SELECTION_LITERAL);
		addEEnumLiteral(luwStorageTableCompressionModeEEnum, LUWStorageTableCompressionMode.ADAPTIVE_LITERAL);
		addEEnumLiteral(luwStorageTableCompressionModeEEnum, LUWStorageTableCompressionMode.STATIC_LITERAL);

		initEEnum(luwMemberTypeEEnum, LUWMemberType.class, "LUWMemberType"); //$NON-NLS-1$
		addEEnumLiteral(luwMemberTypeEEnum, LUWMemberType.MEMBER_LITERAL);
		addEEnumLiteral(luwMemberTypeEEnum, LUWMemberType.CF_LITERAL);

		initEEnum(memberStateTypeEEnum, MemberStateType.class, "MemberStateType"); //$NON-NLS-1$
		addEEnumLiteral(memberStateTypeEEnum, MemberStateType.STARTED_LITERAL);
		addEEnumLiteral(memberStateTypeEEnum, MemberStateType.STOPPED_LITERAL);
		addEEnumLiteral(memberStateTypeEEnum, MemberStateType.RESTARTING_LITERAL);
		addEEnumLiteral(memberStateTypeEEnum, MemberStateType.WAITING_FOR_FAILBACK_LITERAL);
		addEEnumLiteral(memberStateTypeEEnum, MemberStateType.ERROR_LITERAL);
		addEEnumLiteral(memberStateTypeEEnum, MemberStateType.UNKNOWN_LITERAL);
		addEEnumLiteral(memberStateTypeEEnum, MemberStateType.BECOMING_PRIMARY_LITERAL);
		addEEnumLiteral(memberStateTypeEEnum, MemberStateType.PRIMARY_LITERAL);
		addEEnumLiteral(memberStateTypeEEnum, MemberStateType.CATCHUP_LITERAL);
		addEEnumLiteral(memberStateTypeEEnum, MemberStateType.PEER_LITERAL);

		initEEnum(luwSecurityLabelComponentTypeEEnum, LUWSecurityLabelComponentType.class, "LUWSecurityLabelComponentType"); //$NON-NLS-1$
		addEEnumLiteral(luwSecurityLabelComponentTypeEEnum, LUWSecurityLabelComponentType.SET_LITERAL);
		addEEnumLiteral(luwSecurityLabelComponentTypeEEnum, LUWSecurityLabelComponentType.ARRAY_LITERAL);
		addEEnumLiteral(luwSecurityLabelComponentTypeEEnum, LUWSecurityLabelComponentType.TREE_LITERAL);

		initEEnum(luwSecurityLabelNotAuthorizedWriteActionEEnum, LUWSecurityLabelNotAuthorizedWriteAction.class, "LUWSecurityLabelNotAuthorizedWriteAction"); //$NON-NLS-1$
		addEEnumLiteral(luwSecurityLabelNotAuthorizedWriteActionEEnum, LUWSecurityLabelNotAuthorizedWriteAction.OVERRIDE_LITERAL);
		addEEnumLiteral(luwSecurityLabelNotAuthorizedWriteActionEEnum, LUWSecurityLabelNotAuthorizedWriteAction.RESTRICT_LITERAL);

		initEEnum(luwFederatedDataSourceEEnum, LUWFederatedDataSource.class, "LUWFederatedDataSource"); //$NON-NLS-1$
		addEEnumLiteral(luwFederatedDataSourceEEnum, LUWFederatedDataSource.DB2_LITERAL);
		addEEnumLiteral(luwFederatedDataSourceEEnum, LUWFederatedDataSource.INFORMIX_LITERAL);
		addEEnumLiteral(luwFederatedDataSourceEEnum, LUWFederatedDataSource.ORACLE_LITERAL);
		addEEnumLiteral(luwFederatedDataSourceEEnum, LUWFederatedDataSource.SQL_SERVER_LITERAL);
		addEEnumLiteral(luwFederatedDataSourceEEnum, LUWFederatedDataSource.TERADATA_LITERAL);
		addEEnumLiteral(luwFederatedDataSourceEEnum, LUWFederatedDataSource.SYBASE_LITERAL);
		addEEnumLiteral(luwFederatedDataSourceEEnum, LUWFederatedDataSource.ODBC_LITERAL);
		addEEnumLiteral(luwFederatedDataSourceEEnum, LUWFederatedDataSource.JDBC_LITERAL);

		initEEnum(luwTemporaryTableLoggingOptionEEnum, LUWTemporaryTableLoggingOption.class, "LUWTemporaryTableLoggingOption"); //$NON-NLS-1$
		addEEnumLiteral(luwTemporaryTableLoggingOptionEEnum, LUWTemporaryTableLoggingOption.NOT_LOGGED_DELETE_ROWS_LITERAL);
		addEEnumLiteral(luwTemporaryTableLoggingOptionEEnum, LUWTemporaryTableLoggingOption.NOT_LOGGED_PRESERVE_ROWS_LITERAL);
		addEEnumLiteral(luwTemporaryTableLoggingOptionEEnum, LUWTemporaryTableLoggingOption.LOGGED_LITERAL);
	}

} //LUWPackageImpl
