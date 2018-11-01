/*******************************************************************************
 * Copyright (c) 2005, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.ddl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.core.rte.IEngineeringCallBack;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategory;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategoryID;
import org.eclipse.datatools.enablement.ibm.db2.ddl.DB2DeltaDdlGenerator;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMaterializedQueryTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.ManagementType;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Alias;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Index;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Table;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2UserDefinedFunction;
import org.eclipse.datatools.enablement.ibm.ddl.CoreDdlScriptVector;
import org.eclipse.datatools.enablement.ibm.ddl.DdlGenerationUtility;
import org.eclipse.datatools.enablement.ibm.util.EngineeringOptionID;
import org.eclipse.datatools.enablement.ibm.util.SimpleColumnDetails;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.impl.ChangeDescriptionImpl;

public class LUWDeltaDdlGenerator extends DB2DeltaDdlGenerator implements IExecutableExtension {
	// FOR COLUMNS
	protected static final int DATA_TYPE         = 64;
	protected static final int SCOPE             = 128;
	protected static final int IDENTITY          = 256;
	protected static final int COMPRESS          = 512;
	protected static final int DEFAULT_VALUE     = 1024;
	protected static final int NULLABLE          = 2048;

	// FOR TABLES
	protected static final int LOGGED            = 64;
	protected static final int DATA_CAPTURE      = 128;
	protected static final int VOLATILE          = 256;
	protected static final int COMPRESSION       = 512;
	protected static final int APPEND            = 1024;
	protected static final int LOCK_SIZE         = 2048;
	protected static final int RESTRICT          = 4096;
	protected static final int VALUE_COMPRESSION = 8192;
	protected static final int PARTITION_KEY     = 16384;
	// FOR TABLE DATA PRESERVATION
	protected static final int BACKUP            = 32768;
	
	// FOR SCHEMA
	private static final int SCHEMA_OWNERSHIP 			= 64;
	
	// FOR TABLE SPACES
	private static final int TABLESPACE_CONTAINERS 			= 64;
	private static final int TABLESPACE_AUTORESIZE 			= 128;
	private static final int TABLESPACE_BUFFERPOOL 			= 256;
	private static final int TABLESPACE_INCREASESIZE 		= 512;
	private static final int TABLESPACE_MAXSIZE 			= 1024;
	private static final int TABLESPACE_PREFETCHSIZE 		= 2048;
	private static final int TABLESPACE_OVERHEAD 			= 4096;
	private static final int TABLESPACE_DROPTABLERECOVERY 	= 8192;
	private static final int TABLESPACE_XFERRATE 			= 16384;
	private static final int TABLESPACE_CONTAINER_SIZE = 32768;


	protected LUWDdlBuilder builder;
	protected Map columnMap = new LinkedHashMap();
	protected String product;
	protected String version;
	protected IEngineeringCallBack callback;

	public LUWDeltaDdlGenerator() {
		builder = new LUWDdlBuilder();
	}

	protected  void initDPDdlBuilder() {
		dPDdlBuilder = new LUWDataPreservationDdlBuilder();
	}

	//@Override
	protected void setEngineeringCallback(IEngineeringCallBack callback) {
		this.callback = callback;
		this.builder.setEngineeringCallBack(callback);
	}

	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		this.product = config.getAttribute("product"); //$NON-NLS-1$
		this.version = config.getAttribute("version"); //$NON-NLS-1$
		float v = Float.parseFloat(version.substring(1));
	}
	
	public EngineeringOption[] getOptions(){
        if(this.getEngineeringOption() == null) {
            ResourceBundle resource = ResourceBundle.getBundle("org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe.GenericDdlGeneration"); //$NON-NLS-1$

            EngineeringOptionCategory general_options =null;
            EngineeringOptionCategory[] categories = this.getOptionCategories();
            for (int i = 0; i < categories.length; i++) {
            	if (categories[i].getId().equals(EngineeringOptionCategoryID.GENERATE_OPTIONS)){
            		general_options = categories[i];
            	}
            }
            
            Vector optionVec = new Vector();
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME,resource.getString("GENERATE_FULLY_QUALIFIED_NAME"), resource.getString("GENERATE_FULLY_QUALIFIED_NAME_DES"),DdlGenerationUtility.getQualifyNamesDefault(),general_options)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER,resource.getString("GENERATE_QUOTED_IDENTIFIER"), resource.getString("GENERATE_QUOTED_IDENTIFIER_DES"),DdlGenerationUtility.getQuoteIdentifiersDefault(),general_options)); //$NON-NLS-1$ //$NON-NLS-2$
            
            EngineeringOption[] options = new EngineeringOption[optionVec.size()];
            optionVec.copyInto(options);
            this.setEngineeringOption(options);
        }
        
        return this.getEngineeringOption();
	}
		
	//@Override
	public EngineeringOption[] getDdlGeneratorOptionsForDeltaDdl(
			DDLGenerator ddlGenerator, SQLObject[] sqlObjectsArg) {
		SQLObject[] sqlObjects = new SQLObject[sqlObjectsArg.length];
		for (int i = 0; i < sqlObjectsArg.length; i++) {
			if (sqlObjectsArg[i] instanceof PrimaryKey) {
				sqlObjects[i] = ((PrimaryKey)sqlObjectsArg[i]).getBaseTable();
			}
			else if (sqlObjectsArg[i] instanceof Column) {
				sqlObjects[i] = ((Column)sqlObjectsArg[i]).getTable();
			}
			else {
				sqlObjects[i] = sqlObjectsArg[i];
			}
		}
		boolean hasTableSpace = false;
		for (int i = 0; i < sqlObjects.length; i++) {
			if (LUWPackage.eINSTANCE.getLUWTableSpace().isSuperTypeOf(sqlObjects[i].eClass())) {
				hasTableSpace = true;
				break;			
			}
			if (sqlObjects[i] instanceof LUWDatabase) {
				hasTableSpace = true;
				break;			
			}
		}
		EngineeringOption[] ddlGenOptions = ddlGenerator.getOptions(sqlObjects);
    	for (int i = 0; i < ddlGenOptions.length; i++){
            EngineeringOption option = (EngineeringOption) ddlGenOptions[i];
            if (option == null) {
           	 	continue;
            }
            if (option.getId().equals(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME)) { 
                option.setBoolean(DdlGenerationUtility.getQualifyNamesDefault());
            }
            else if (option.getId().equals(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER)) {
            	option.setBoolean(DdlGenerationUtility.getQuoteIdentifiersDefault());
            }
            else if (option.getId().equals(EngineeringOptionID.GENERATE_TABLESPACES)) {
           	 	option.setBoolean(hasTableSpace);
            }
        }
    	return ddlGenOptions;
	}
	
	@Override
	protected boolean shouldExcludeElement(EObject element) {
		if (element instanceof LUWMaterializedQueryTable)
		{
		  if (!EngineeringOptionID.generateMQTs(selectedOptions))
				  return true;
		} else if (element instanceof DB2Table)
		{
			if (((DB2Table) element).getTemporalTable() != null)
			{
				if (!EngineeringOptionID.generateHistoryTable( selectedOptions))
					return true;
			}
		} else if (element instanceof DB2Alias)
		{
			if (!EngineeringOptionID.generateAliases( selectedOptions ))
				return true;
		}  else if (element instanceof LUWDatabase)
		{
			if (!EngineeringOptionID.generateDatabase(selectedOptions))
				return true;
		}
		return super.shouldExcludeElement(element);
	}

	protected int getChangeFlag(EObject element, EObject changed, EStructuralFeature feature, FeatureChange setting) {
    	if (isChangeAnnotationRelated(element, changed, feature)) return 0;
		if(element != changed) {
			if (SQLTablesPackage.eINSTANCE.getColumn().isSuperTypeOf(element.eClass())) {
				if (SQLDataTypesPackage.eINSTANCE.getPredefinedDataType().isSuperTypeOf(changed.eClass())) {
					if(canAlter((PredefinedDataType) changed)) 
						return getColumnDatatypeChangeFlag((Column)element);
					return MODIFIED;
				}
//				if(changed instanceof IdentitySpecifier) {
//					return IDENTITY;
//				}
			}
			if (SQLTablesPackage.eINSTANCE.getTable().isSuperTypeOf(element.eClass())) {
				if (LUWPackage.eINSTANCE.getLUWPartitionKey().isSuperTypeOf(changed.eClass())) {
					return PARTITION_KEY;
				}
			}
	        if (SQLSchemaPackage.eINSTANCE.getSchema().isSuperTypeOf(element.eClass()) && 
	        		!(SQLSchemaPackage.eINSTANCE.getSQLObject().isSuperTypeOf(changed.eClass()))) {
	                        	return 0;
            }
			if (LUWPackage.eINSTANCE.getLUWTableSpace().isSuperTypeOf(element.eClass())) {
				if (LUWPackage.eINSTANCE.getLUWDatabaseContainer().isSuperTypeOf(changed.eClass())) {
					if (!((LUWTableSpace)element).getManagementType().equals(ManagementType.DATABASE_MANAGED_LITERAL))
						return MODIFIED;
					if (feature == LUWPackage.eINSTANCE.getLUWDatabaseContainer_SizeInPages() ||
							feature == LUWPackage.eINSTANCE.getLUWDatabaseContainer_Size() ||
							feature == LUWPackage.eINSTANCE.getLUWDatabaseContainer_SizeUnits()) {
						return TABLESPACE_CONTAINER_SIZE;
					}
				}
			}
			return MODIFIED; 
		}
		if(feature == EcorePackage.eINSTANCE.getENamedElement_Name()) return RENAME;
		if(feature == SQLSchemaPackage.eINSTANCE.getSQLObject_Description()) return COMMENT;
		if(feature == SQLSchemaPackage.eINSTANCE.getSQLObject_Label()) return LABEL;
		//(RATLC00398029)if the "partitions" of a LUW partition group is changed, it is
		//reasonable to think the partition group is modified
		if (feature == LUWPackage.eINSTANCE.getLUWPartitionGroup_Partitions()) return MODIFIED;
		if (feature == LUWPackage.eINSTANCE.getLUWBufferPool_PartitionGroup())
			return MODIFIED;
		if (feature == LUWPackage.eINSTANCE.getLUWBufferPool_SizeException())
			return MODIFIED;
		if (element instanceof LUWTableSpace) {
			if (feature == LUWPackage.eINSTANCE.getLUWTableSpace_Containers()) {
				EList oldContainers = (EList)getOldValue(feature, element);
				EList newContainers = ((LUWTableSpace)element).getContainers();				
				if (((LUWTableSpace)element).getManagementType().equals(ManagementType.DATABASE_MANAGED_LITERAL) && newContainers != null && oldContainers != null) {
					return TABLESPACE_CONTAINERS;
				}
			}
			if (feature == LUWPackage.eINSTANCE.getLUWTableSpace_AutoResize()) {
				if (((LUWTableSpace)element).isAutoResize())
					return TABLESPACE_AUTORESIZE | TABLESPACE_INCREASESIZE | TABLESPACE_MAXSIZE;
				return TABLESPACE_AUTORESIZE;
			}
			if (feature == LUWPackage.eINSTANCE.getLUWTableSpace_BufferPool()) 
				return TABLESPACE_BUFFERPOOL;
			if (feature == LUWPackage.eINSTANCE.getLUWTableSpace_IncreaseSize() ||
					feature == LUWPackage.eINSTANCE.getLUWTableSpace_IncreasePercent() ||
					feature == LUWPackage.eINSTANCE.getLUWTableSpace_IncreaseSizeUnit()) 
				return TABLESPACE_INCREASESIZE;
			if (feature == LUWPackage.eINSTANCE.getLUWTableSpace_MaximumSize() ||
					feature == LUWPackage.eINSTANCE.getLUWTableSpace_MaximumSizeUnit()) 
				return TABLESPACE_MAXSIZE;
			if (feature == LUWPackage.eINSTANCE.getLUWTableSpace_Overhead()) 
				return TABLESPACE_OVERHEAD;
			if (feature == LUWPackage.eINSTANCE.getLUWTableSpace_PreFetchSize()) 
				return TABLESPACE_PREFETCHSIZE;
			if (feature == LUWPackage.eINSTANCE.getLUWTableSpace_RecoverDroppedTableOn()) 
				return TABLESPACE_DROPTABLERECOVERY;
			if (feature == LUWPackage.eINSTANCE.getLUWTableSpace_TransferRate()) 
				return TABLESPACE_XFERRATE;
			return MODIFIED;
			
		}
		if(SQLTablesPackage.eINSTANCE.getColumn().isSuperTypeOf(element.eClass())) {
			Column column = (Column) element;
			if(feature == SQLTablesPackage.eINSTANCE.getColumn_DefaultValue()) {
			return DEFAULT_VALUE;
			}
			if(feature == SQLTablesPackage.eINSTANCE.getColumn_Nullable()) {
				return getColumnNullableChangeFlag(column);
			}
//			if(feature == SQLTablesPackage.eINSTANCE.getColumn_IdentitySpecifier()) {
//			return IDENTITY;
//		}
			if(feature == SQLSchemaPackage.eINSTANCE.getTypedElement_ContainedType()) {
				PredefinedDataType type2 = (PredefinedDataType) column.getContainedType();
				if(type2 == null) return MODIFIED;
				PredefinedDataType type1 = (PredefinedDataType) getOldValue(SQLSchemaPackage.eINSTANCE.getTypedElement_ContainedType(), column);
				if(type1 == null) return MODIFIED;
				if(canAlter(type1, type2)) 
					return getColumnDatatypeChangeFlag(column);
				return MODIFIED;
			}
		}
		if (SQLTablesPackage.eINSTANCE.getTable().isSuperTypeOf(element.eClass())) {
			if (feature == LUWPackage.eINSTANCE.getLUWStorageTable_PartitionKey())
				return PARTITION_KEY;
			if (feature == DB2ModelPackage.eINSTANCE.getDB2Table_DataCapture())
				return DATA_CAPTURE;
			if (feature == LUWPackage.eINSTANCE.getLUWStorageTable_RowCompression())
				return COMPRESSION;
			if (feature == LUWPackage.eINSTANCE.getLUWStorageTable_ValueCompression())
				return VALUE_COMPRESSION;
			if(feature == LUWPackage.eINSTANCE.getLUWTable_Volatile()) 
				return VOLATILE;
			int columnsFlag = getColumnsChangeFlag(element, feature);
			if (columnsFlag != 0) 
				return columnsFlag;
		}
		if (SQLSchemaPackage.eINSTANCE.getSchema().isSuperTypeOf(element.eClass())) {
			if (feature == SQLSchemaPackage.eINSTANCE.getSchema_Owner() && 
					isTransferOwnershipSupported())
				return SCHEMA_OWNERSHIP;
		}
		// For issue wsdbu00259503, if it is table, and its dimension changes, mark it as MODIFIED
		if(changed instanceof DB2Table && feature == SQLTablesPackage.eINSTANCE.getTable_Index()){
			if(isDimensionsChanged((List)changed.eGet(feature), (List)setting.getValue()))
				return MODIFIED;			
		}

		if(feature instanceof EReference && feature.isMany() && ((EReference) feature).getEOpposite() != null) return 0;
		return MODIFIED;
	}
	
	// For issue wsdbu00259503, compute the dimension modification.
	private boolean isDimensionsChanged(List curIndexList, List preIndexList){
		Set curDimensions = new HashSet();
		Set preDimensions = new HashSet();
		for(Object e : curIndexList)
			if(e instanceof DB2Index && 
					((DB2Index)e).getIndexType() == DB2IndexType.DIMENSION_LITERAL)
				curDimensions.add(e);
		for(Object e : preIndexList)
			if(e instanceof DB2Index && 
					((DB2Index)e).getIndexType() == DB2IndexType.DIMENSION_LITERAL)
				preDimensions.add(e);
		if(curDimensions.size() == preDimensions.size() && curDimensions.containsAll(preDimensions))
			return false;
		return true;
	}


	protected boolean isTransferOwnershipSupported() {
		// Support added in v9
		return false;
	}

	protected int getColumnDatatypeChangeFlag(Column column) {
		return DATA_TYPE;
	}
	
	protected int getColumnNullableChangeFlag(Column column) {
		return MODIFIED;
	}
	
	protected boolean containsXMLColumn(LUWTable table) {
		return false;
	}
	
	protected boolean needRecreate(EObject e, int flag) {
		if (SQLTablesPackage.eINSTANCE.getPersistentTable().isSuperTypeOf(e.eClass()))
			return (flag & (MODIFIED)) != 0;
		return (flag & (MODIFIED | RENAME)) != 0;
	}
	
	protected void analyze(Map changeMap) {
		List columns = new LinkedList();
		Iterator it = changeMap.keySet().iterator();
		while(it.hasNext()) {
			Object e = it.next();
			if(e instanceof Column) columns.add(e);
		}

		it = columns.iterator();
		while(it.hasNext()) {
			Column column = (Column) it.next();
			int flag = ((Integer) changeMap.get(column)).intValue();
			if(flag == DROP) {
				if (analyzeDropColumnAndContinue(column, changeMap, it)) continue;						
			}
			else {
				Table table = column.getTable();
				if(LUWPackage.eINSTANCE.getLUWTable().isSuperTypeOf(table.eClass()) && 
						!(LUWPackage.eINSTANCE.getLUWNickname().isSuperTypeOf(table.eClass()))) {
					if((flag & (RENAME | MODIFIED)) != 0) {
						changeMap.remove(column);
						it.remove();
						flag = 0;
						if(changeMap.containsKey(table)) {
							flag = ((Integer) changeMap.get(table)).intValue();						
						}
						flag = flag | MODIFIED;
						changeMap.put(table, new Integer(flag));
					}
				}
			}
		}
		
		it = columns.iterator();
		while(it.hasNext()) {
			Column column = (Column) it.next();
			if(isAncestorModified(changeMap, column)) {
				changeMap.remove(column);				
			}
			else {
				int flag = ((Integer) changeMap.get(column)).intValue();
				changeMap.remove(column);
				columnMap.put(column, new Integer(flag));
			}
		}

		//TODO (jyeh) the super call wipes out alias' comment flag if there was also table change
		super.analyze(changeMap);

		//analyze dimension indexes
		it = changeMap.keySet().iterator();
		List indexes = new LinkedList();
		while(it.hasNext()) {
			Object e = it.next();
			int flag = ((Integer) changeMap.get(e)).intValue();
			if (e instanceof DB2Index && 
					((DB2Index)e).getIndexType() == DB2IndexType.DIMENSION_LITERAL && 
					(flag & (CREATE | DROP)) != 0) {
				indexes.add(e);
			}
		}

		it = indexes.iterator();
		while(it.hasNext()) {
			DB2Index index = (DB2Index) it.next();
			int flag = ((Integer)changeMap.get(index)).intValue();
			Table table= null;
			if (flag == DROP) {
				undo();
				table = index.getTable();
				redo();
			}
			else {
				table = index.getTable();	
			}			
			changeMap.put(table, new Integer(CREATE | DROP));
			changeMap.remove(index);
		}		

		// Data Preservation
		it = changeMap.keySet().iterator();
		while(it.hasNext()) {
			Object e = it.next();
			if(e instanceof PersistentTable) {
				int flag = ((Integer) changeMap.get(e)).intValue();
				if ((flag & (DROP | CREATE)) == (DROP | CREATE)) {
					flag = flag & ~DROP;
					flag = flag | BACKUP;
					changeMap.put(e, new Integer(flag));
				}
			}
		}
		// End Data Preservation

		removeRedundantColumnChange(changeMap,columnMap);
	}

	protected void removeRedundantColumnChange(Map changeMap, Map colMap) {
		int flag;
	    Iterator it = colMap.keySet().iterator();
		Table table = null;
        while (it.hasNext()) {
        	Column column = (Column)it.next();
        	flag = ((Integer) colMap.get(column)).intValue();
        	if (flag == DROP) {
        		EObject obj = ((ChangeDescriptionImpl) changeDescription).getOldContainer(column);
        		if (obj != null && 
        				SQLTablesPackage.eINSTANCE.getTable().isSuperTypeOf(obj.eClass())) {
        			table = (Table)((ChangeDescriptionImpl) changeDescription).getOldContainer(column);
        		} else {
        			table = column.getTable();
        		}
        	} else {
        		table = column.getTable();
        	}
        	if (table == null)
        		table = column.getTable();
        	if (table != null && 
        			LUWPackage.eINSTANCE.getLUWTable().isSuperTypeOf(table.eClass()) &&
        			!(LUWPackage.eINSTANCE.getLUWNickname().isSuperTypeOf(table.eClass()))) {
        		if(changeMap.containsKey(table)) {
        			flag = ((Integer) changeMap.get(table)).intValue();
        			if ((flag & (DROP | BACKUP)) != 0) {
        				it.remove();
        			}
        		}
        	}
        }
	}

	protected boolean isRenameTableSupported() {
		return true;
	}
	
	protected boolean analyzeDropColumnAndContinue(Column column, Map changeMap, Iterator it) {
		int flag;
		Table table = (Table) ((ChangeDescriptionImpl)changeDescription).getOldContainer(column);
		if (LUWPackage.eINSTANCE.getLUWTable().isSuperTypeOf(table.eClass()) &&
				!(LUWPackage.eINSTANCE.getLUWNickname().isSuperTypeOf(table.eClass()))) {
			changeMap.remove(column);
			it.remove();
			flag = 0;
			if(changeMap.containsKey(table)) {
				flag = ((Integer) changeMap.get(table)).intValue();						
				if(flag == DROP) return true;
			}
			flag = flag | MODIFIED;
			changeMap.put(table, new Integer(flag));
			return true;
		}
		return false;
	}

	protected String[] processChangeMap(Map changeMap, IProgressMonitor monitor) {
//<bgp		String[] olapStatements = null;
//		String[] olapDropStatements = null;
//		OlapDeltaDdlBuilder olapDeltaDdlbuilder = getOlapDeltaDdlBuilder(this.product,this.version); 
//		if (olapDeltaDdlbuilder != null) {
//			olapStatements = olapDeltaDdlbuilder.getDeltaDdlStatements(changeMap);
//			this.undo();
//			olapDropStatements = olapDeltaDdlbuilder.getDeltaDropDdlStatements(changeMap);
//			this.redo();
//bgp>		}

        DDLGenerator ddlGenerator = getDdlGeneratorWithDeltaDDLOptions();
        this.undo();
        String[] drops = new String[0]; 
        if (EngineeringOptionID.generateDropStatement(selectedOptions))
        	drops = getDropStatements(ddlGenerator, changeMap, monitor,callback);
        String[] backups = getBackupStatements(ddlGenerator, changeMap, monitor);
        // Collect metadata for DML to transform and move data from backups
        Map backupTables = getBackupTableMetadata(changeMap);
        this.redo();
        if (!backupTables.isEmpty()) dataPreservationRequired  = true;
        String [] tableAlters = new String[0];
        if (EngineeringOptionID.generateTables(selectedOptions))
          tableAlters = getTableAlterStatements(ddlGenerator, changeMap, monitor);
//        String [] tableAlters = getTableAlterStatements(ddlGenerator, changeMap, columnMap, monitor);
        String[] renames = getRenameStatements(ddlGenerator, changeMap, monitor);
        String[] creates = new String[0];
        if (EngineeringOptionID.generateCreateStatement(selectedOptions))
          creates = getCreateStatements(ddlGenerator, changeMap, monitor,callback);
        // Create DML to transform and move data from backups to new tables
        String[] pops = getPopulateStatements(ddlGenerator, changeMap, backupTables, monitor);
        
        String[] dropbackups = getDropBackupStatements(ddlGenerator, changeMap, backupTables, monitor);
        
		String[] xferO = getTransferOwnershipStatements(changeMap);
		
		String[] ats = new String[0];
		if (EngineeringOptionID.generateTablespaces(selectedOptions))
		   ats = getAlterTablespaceStatements(changeMap);
		String[] ac = new String[0];
		if (EngineeringOptionID.generateTables(selectedOptions))
		  ac = getAlterColumnStatements(columnMap);
        this.changeDescription = null;
        
        //(jyeh) need to move columns which have comment changes from columnMap to changeMap so that
        //getCommentOnStatements can generate DDL for that. (RATLC01146091)
        try {
            Iterator it = columnMap.keySet().iterator();
    		while(it.hasNext()) {
    			Object e = it.next();
    			int flag = ((Integer) columnMap.get(e)).intValue();
    			if (e instanceof Column && ((flag & COMMENT) != 0)) {
    				changeMap.put(e, new Integer(COMMENT));
    			}
    		}        	
        }
		finally {
			columnMap.clear();	
		}
		
		String[] comments = new String[0];
		if (EngineeringOptionID.generateCommentStatement(selectedOptions))
		  comments = getCommentOnStatements(changeMap);
		String[] script = merge(merge(merge(merge(merge(merge(merge(merge(merge(merge(drops, backups), renames), ac), tableAlters), ats), xferO), creates), pops), dropbackups), comments);
		
//<bgp		if (olapStatements != null) {
//			script = merge(script,olapStatements);
//		}
//		if (olapDropStatements != null) {
//			script = merge(script,olapDropStatements);
//bgp>		}
		
		return script;
	}

	private String[] getTransferOwnershipStatements(Map changeMap) {
		boolean quoteIdentifiers = EngineeringOptionID.generateQuotedIdentifiers(this.getSelectedOptions());
		boolean qualifyNames = EngineeringOptionID.generateFullyQualifiedNames(this.getSelectedOptions());
    	
        Vector stmts = new Vector();        
        Iterator it = changeMap.keySet().iterator();        
        while(it.hasNext()) {
        	EObject changed = (EObject)it.next();
        	if (changed instanceof Schema && EngineeringOptionID.generateSchemas(selectedOptions)) {
        		int flag = ((Integer) changeMap.get(changed)).intValue();
        		if ((flag & SCHEMA_OWNERSHIP) == 0) continue;
        		String statement = builder.transferSchemaOwnership((Schema)changed,quoteIdentifiers);
                if(statement != null) {
                    stmts.add(statement);
                }        			        			        				
        	}
        }

        Vector sorter = new CoreDdlScriptVector();
        sorter.addAll(stmts);
        String[] s = new String[sorter.size()];
        sorter.copyInto(s);
        return s;
	}

	private String[] getAlterTablespaceStatements(Map changeMap) {
		boolean quoteIdentifiers = EngineeringOptionID.generateQuotedIdentifiers(this.getSelectedOptions());
		boolean qualifyNames = EngineeringOptionID.generateFullyQualifiedNames(this.getSelectedOptions());
    	
        Vector alts = new Vector();        
        Iterator it = changeMap.keySet().iterator();        
        while(it.hasNext()) {
        	EObject changed = (EObject)it.next();
        	if (changed instanceof LUWTableSpace) {
        		int flag = ((Integer) changeMap.get(changed)).intValue();
        		if ((flag & ~(RENAME  | DROP | CREATE)) == 0) continue;
        		String statement = builder.alterTablespaceProlog((LUWTableSpace)changed,quoteIdentifiers,qualifyNames);
        		if ((flag & TABLESPACE_CONTAINERS) != 0) {
        			LUWTableSpace tablespace = (LUWTableSpace)changed;
        			EStructuralFeature feature = LUWPackage.eINSTANCE.getLUWTableSpace_Containers();
    				EList oldContainers = (EList)getOldValue(feature, tablespace);
    				EList newContainers = tablespace.getContainers();

    				statement += builder.alterTablespaceContainers((LUWTableSpace)changed, newContainers, oldContainers, quoteIdentifiers, qualifyNames);
        		}
        		if ((flag & TABLESPACE_CONTAINER_SIZE) != 0) {
        			LUWTableSpace tablespace = (LUWTableSpace)changed;
        			EStructuralFeature feature = LUWPackage.eINSTANCE.getLUWTableSpace_Containers();
    				EList oldContainers = (EList)getOldValue(feature, tablespace);
    				EList newContainers = tablespace.getContainers();

    				statement += builder.alterTablespaceContainersSize((LUWTableSpace)changed, newContainers, oldContainers, quoteIdentifiers, qualifyNames,this);
        		}
        		if ((flag & TABLESPACE_PREFETCHSIZE) != 0) {
    				statement += builder.tablespacePrefetchSizeClause((LUWTableSpace)changed);
        		}
        		if ((flag & TABLESPACE_BUFFERPOOL) != 0) {
    				statement += builder.tablespaceBufferPoolClause((LUWTableSpace)changed,quoteIdentifiers);
        		}
        		if ((flag & TABLESPACE_OVERHEAD) != 0) {
    				statement += builder.tablespaceOverheadClause((LUWTableSpace)changed);
        		}
        		if ((flag & TABLESPACE_XFERRATE) != 0) {
    				statement += builder.tablespaceXferRateClause((LUWTableSpace)changed);
        		}
        		if ((flag & TABLESPACE_DROPTABLERECOVERY) != 0) {
    				statement += builder.tablespaceDroppedTableRecoveryClause((LUWTableSpace)changed);
        		}
        		if ((flag & TABLESPACE_AUTORESIZE) != 0) {
    				statement += builder.tablespaceAutoResizeClause((LUWTableSpace)changed);
        		}
        		if ((flag & TABLESPACE_INCREASESIZE) != 0) {
    				statement += builder.tablespaceIncreaseSizeClause((LUWTableSpace)changed);
        		}
        		if ((flag & TABLESPACE_MAXSIZE) != 0) {
    				statement += builder.tablespaceMaxSizeClause((LUWTableSpace)changed);
        		}
                if(statement != null) {
                    alts.add(statement);
                }        			        			        				
        	}
        }

        String[] s = new String[alts.size()];
        alts.copyInto(s);
        return s;
	}
	
	//@Override
	protected String[] getDropStatements(DDLGenerator gen, Map changeMap,
			IProgressMonitor monitor, IEngineeringCallBack callback) {
		Iterator it = changeMap.keySet().iterator();
		while(it.hasNext()) {
			EObject key = (EObject) it.next();
			int flag = ((Integer) changeMap.get(key)).intValue();
			if((flag & (DROP)) != 0) {
				if (((flag & (CREATE)) != 0) && 
						LUWPackage.eINSTANCE.getLUWTableSpace().isSuperTypeOf(key.eClass())) 
					setDestructive(true);
			}
		}

		return super.getDropStatements(gen, changeMap, monitor, callback);
	}

	protected final String[] getBackupStatements(DDLGenerator gen, Map changeMap, IProgressMonitor monitor) {
		if (!(gen instanceof LUWDdlGenerator)) return new String[0];
		// DDLGenerator generator = this.getDDLGenerator();
		Vector elements = new Vector();
		Iterator it = changeMap.keySet().iterator();
		while(it.hasNext()) {
			EObject key = (EObject) it.next();
			int flag = ((Integer) changeMap.get(key)).intValue();
			if((flag & (BACKUP)) != 0) {
				elements.add(key);
			}
		}
		if(elements.size() > 0) {
			SQLObject[] d = new SQLObject[elements.size()];
			elements.copyInto(d);
			// generator.getOptions(d);
			return backupTableStatements(d,EngineeringOptionID.generateQuotedIdentifiers(this.getSelectedOptions()), EngineeringOptionID.generateFullyQualifiedNames(this.getSelectedOptions()), monitor, 100);
		}
		else {
			return new String[0];
		}
	}
	
	protected final String[] getRenameStatements(DDLGenerator gen, Map changeMap, IProgressMonitor monitor) {
		if (!(gen instanceof LUWDdlGenerator)) return new String[0];
		// DDLGenerator generator = this.getDDLGenerator();
		Vector elements = new Vector();
		Iterator it = changeMap.keySet().iterator();
		while(it.hasNext()) {
			EObject key = (EObject) it.next();
			int flag = ((Integer) changeMap.get(key)).intValue();
			if(((flag & RENAME) != 0) &&
					((flag & DROP) == 0) && !shouldExcludeElement(key)) {
				elements.add(key);
			}
		}
		if(elements.size() > 0) {
			SQLObject[] d = new SQLObject[elements.size()];
			elements.copyInto(d);
			// generator.getOptions(d);
			String[] renames = renameTableStatements(d,EngineeringOptionID.generateQuotedIdentifiers(this.getSelectedOptions()), EngineeringOptionID.generateFullyQualifiedNames(this.getSelectedOptions()), monitor, 100);
			return merge(renames,renameTableSpaceStatements(d,EngineeringOptionID.generateQuotedIdentifiers(this.getSelectedOptions()), EngineeringOptionID.generateFullyQualifiedNames(this.getSelectedOptions()), monitor, 100));
		}
		else {
			return new String[0];
		}
	}
	
	protected Map getBackupTableMetadata(Map changeMap) {
		Map ret = new HashMap();
		Iterator it = changeMap.keySet().iterator();
		while(it.hasNext()) {
			EObject key = (EObject) it.next();
			if (!(SQLTablesPackage.eINSTANCE.getPersistentTable().isSuperTypeOf(key.eClass()))) continue;
			int flag = ((Integer) changeMap.get(key)).intValue();
			if((flag & (BACKUP)) != 0) {
				Map columnMap = new HashMap();
				Iterator it1 = ((Table)key).getColumns().iterator();
				while (it1.hasNext()) {
					Column col = (Column)it1.next();
					SimpleColumnDetails details = new SimpleColumnDetails(col);
					columnMap.put(col, details);
				}
				ret.put(key,columnMap);
			}
		}
		return ret;
	}
	
	protected final String[] getPopulateStatements(DDLGenerator gen, Map changeMap, Map backupMetadata, IProgressMonitor monitor) {
		if (!(gen instanceof LUWDdlGenerator)) return new String[0];
//		LUWDdlGenerator myGen = (LUWDdlGenerator)gen;
		Vector newTables = new Vector();
		Vector oldTableNames = new Vector();
		Vector oldTableColumns = new Vector();
		Iterator it = changeMap.keySet().iterator();
		while(it.hasNext()) {
			EObject key = (EObject) it.next();
			if (!(SQLTablesPackage.eINSTANCE.getPersistentTable().isSuperTypeOf(key.eClass()))) continue;
			String oldTableName = (String)getOldValue(key.eClass().getEStructuralFeature("name"), key);
			int flag = ((Integer) changeMap.get(key)).intValue();
			if((flag & (BACKUP)) != 0) {
				boolean canPopulate = true;
				Map oldColumns = (Map)backupMetadata.get(key);
// Remove this test, since DB2 does not require an explicit default value for NOT NULL columns
				// Ensure that every column in the new table has a source in the backup
//				Iterator it1 = ((Table)key).getColumns().iterator();
//				while (it1.hasNext()) {
//					Column newCol = (Column)it1.next();
//					if (!oldColumns.containsKey(newCol)
//							&& (newCol.getIdentitySpecifier() == null)
//							&& (newCol.getGenerateExpression() == null)
//							&& (newCol.getDefaultValue() == null)
//							&& (!newCol.isNullable())) {
//						canPopulate = false;
//						break;
//					}
//				}
				if (canPopulate) {
					newTables.add(key);
					oldTableNames.add(oldTableName);
					oldTableColumns.add(oldColumns);
				}
			}
		}
		if(newTables.size() > 0) {
			Table[] newTableArray = new Table[newTables.size()];
			newTables.copyInto(newTableArray);
			
			String[] oldTableNameArray = new String[oldTableNames.size()];
			oldTableNames.copyInto(oldTableNameArray);
			
			Map[] oldTableColumnsArray = new Map[oldTableColumns.size()];
			oldTableColumns.copyInto(oldTableColumnsArray);
			return populateFromBackupTableStatements(newTableArray,oldTableNameArray,oldTableColumnsArray,EngineeringOptionID.generateQuotedIdentifiers(this.getSelectedOptions()), EngineeringOptionID.generateFullyQualifiedNames(this.getSelectedOptions()), monitor, 100);
		}
		else {
			return new String[0];
		}
	}
	
	protected String[] backupTableStatements(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor, int task) {
        LUWDdlScript script = new LUWDdlScript();
        LUWDataPreservationDdlBuilder dpBuilder = (LUWDataPreservationDdlBuilder)getDPDdlBuilder();
        Iterator it = DdlGenerationUtility.getAllContainedDisplayableElementSet(elements).iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(o instanceof DB2Table) {
                String statement = dpBuilder.renameTableToBackup((DB2Table) o, quoteIdentifiers, qualifyNames);
                if(statement != null) {
                	script.addBackupTableStatement(dpBuilder.getRenameComment());
                	script.addBackupTableStatement(statement);
                }
            }
        }
        return script.getStatements();
    }

	protected String[] renameTableStatements(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor, int task) {
        LUWDdlScript script = new LUWDdlScript();
        Iterator it = DdlGenerationUtility.getAllContainedDisplayableElementSet(elements).iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(o instanceof DB2Table) {
    			Object oldName = getOldValue(EcorePackage.eINSTANCE.getENamedElement_Name(),(EObject)o);
                String statement = builder.renameTable((DB2Table) o, (String)oldName, quoteIdentifiers, qualifyNames);
                if(statement != null) {
                	script.addRenameTableStatement(statement);
                }
            }
        }
        return script.getStatements();
    }

	protected String[] renameTableSpaceStatements(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor, int task) {
        LUWDdlScript script = new LUWDdlScript();
        Iterator it = DdlGenerationUtility.getAllContainedDisplayableElementSet(elements).iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(o instanceof LUWTableSpace) {
    			Object oldName = getOldValue(EcorePackage.eINSTANCE.getENamedElement_Name(),(EObject)o);
                String statement = builder.alterTablespaceRename((LUWTableSpace)o,(String)oldName,quoteIdentifiers,qualifyNames);
                if(statement != null) {
                 	script.addRenameTableStatement(statement);
        		}
            }
        }
        return script.getStatements();
    }

    public String[] populateFromBackupTableStatements(Table[] tables, String[] oldTableNames, Map[] oldColumnMaps, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor, int task) {
        LUWDdlScript script = new LUWDdlScript();
        LUWDataPreservationDdlBuilder dpBuilder = (LUWDataPreservationDdlBuilder)getDPDdlBuilder();
        String statement;
        for (int i=0;i<tables.length;i++) {
        	ArrayList genColumns = dpBuilder.getAlwaysGeneratedColumns(tables[i]);
        	Iterator it = genColumns.iterator();
        	while (it.hasNext()) {
        		Column genColumn = (Column)it.next();
        		// If the gen'd always column is new, we don't need to suspend and re-enable it
        		if (oldColumnMaps[i].get(genColumn) == null) {
        			it.remove();
        			continue;
        		}
       			script.addPopulateTableStatement(dpBuilder.getSuspendGenerateAlwaysStatement(tables[i],genColumn,quoteIdentifiers,qualifyNames));
        	}
            statement = dpBuilder.populateTableFromBackup(tables[i], oldTableNames[i], oldColumnMaps[i], quoteIdentifiers, qualifyNames);
            if(statement != null) {
            	script.addPopulateTableStatement(dpBuilder.getPopulateComment());
            	script.addPopulateTableStatement(statement);
            }
        	it = genColumns.iterator();
        	while (it.hasNext()) {
        		Column genColumn = (Column)it.next();
        		if (genColumn.getIdentitySpecifier() != null) {
        			script.addPopulateTableStatement(dpBuilder.getRestartIdentityComment());
        			script.addPopulateTableStatement(dpBuilder.getRestartIdentityStatement(tables[i],genColumn,quoteIdentifiers,qualifyNames));
        		}
        		script.addPopulateTableStatement(dpBuilder.getSetGenerateAlwaysStatement(tables[i],genColumn,quoteIdentifiers,qualifyNames));
        	}
        }
        return script.getStatements();
    }

	protected final String[] getDropBackupStatements(DDLGenerator gen, Map changeMap, Map backupMetadata, IProgressMonitor monitor) {
		if (!(gen instanceof LUWDdlGenerator)) return new String[0];
		// DDLGenerator generator = this.getDDLGenerator();
		Vector elements = new Vector();
		Iterator it = changeMap.keySet().iterator();
		while(it.hasNext()) {
			EObject key = (EObject) it.next();
			int flag = ((Integer) changeMap.get(key)).intValue();
			if((flag & (BACKUP)) != 0) {
				elements.add(key);
			}
		}
		if(elements.size() > 0) {
			SQLObject[] d = new SQLObject[elements.size()];
			elements.copyInto(d);
			//could call getDdlGeneratorOptionsForDeltaDdl(generator, d); if the options needs to be customized
			// generator.getOptions(d);
			return dropBackupTableStatements(d,EngineeringOptionID.generateQuotedIdentifiers(this.getSelectedOptions()), EngineeringOptionID.generateFullyQualifiedNames(this.getSelectedOptions()), monitor, 100);
		}
		else {
			return new String[0];
		}	
	}
	
	protected String[] dropBackupTableStatements(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor, int task) {
        LUWDdlScript script = new LUWDdlScript();
        LUWDataPreservationDdlBuilder dpBuilder = (LUWDataPreservationDdlBuilder)getDPDdlBuilder();
        Iterator it = DdlGenerationUtility.getAllContainedDisplayableElementSet(elements).iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(o instanceof DB2Table) {
            	//Need to use the old table name as the name may have been changed
            	Schema oldSchema = (Schema)getOldValue(((DB2Table)o).eClass().getEStructuralFeature("schema"), (DB2Table)o);
            	String oldSchemaName = oldSchema != null ? oldSchema.getName() : "";
            	String oldTableName = (String)getOldValue(((DB2Table)o).eClass().getEStructuralFeature("name"), (DB2Table)o);
                String statement = dpBuilder.dropBackupTable(oldSchemaName, oldTableName, quoteIdentifiers, qualifyNames);
                if(statement != null) {
                	script.addDropBackupTableStatement(dpBuilder.getDropBackupComment());
                	script.addDropBackupTableStatement(statement);
                }
            }
        }
        return script.getStatements();		
	}
	
    protected String[] getTableAlterStatements(DDLGenerator gen, Map changeMap, IProgressMonitor monitor) {
		boolean quoteIdentifiers = EngineeringOptionID.generateQuotedIdentifiers(this.getSelectedOptions());
		boolean qualifyNames = EngineeringOptionID.generateFullyQualifiedNames(this.getSelectedOptions());

		Vector alts = new Vector();
		Iterator it = changeMap.keySet().iterator();
		while(it.hasNext()) {
			Object obj = it.next();
			if (!(obj instanceof Table)) continue;
			Table table = (Table)obj;
			int flag = ((Integer) changeMap.get(table)).intValue();
			if(flag == PARTITION_KEY) {
				String statement = builder.alterTableAlterPartitionKey((LUWStorageTable)table, quoteIdentifiers, qualifyNames);
				if(statement != null) alts.add(statement);
			}
			//To generate alter statement, the BACKUP flag should not have been set
			if((flag & BACKUP) == 0 
					&& (flag & (DATA_CAPTURE | COMPRESSION | VALUE_COMPRESSION | VOLATILE)) != 0) {
				String statement = builder.alterTableAlterProperties(table, flag, quoteIdentifiers, qualifyNames);
				if(statement != null) alts.add(statement);
			}
			// TODO other changes
		}
   	
        Vector sorter = new CoreDdlScriptVector();
        sorter.addAll(alts);
        String[] s = new String[sorter.size()];
        sorter.copyInto(s);
        return s;
    }
    
	protected String[] getAlterColumnStatements(Map changeMap) {
		boolean quoteIdentifiers = EngineeringOptionID.generateQuotedIdentifiers(this.getSelectedOptions());
		boolean qualifyNames = EngineeringOptionID.generateFullyQualifiedNames(this.getSelectedOptions());
		Map<String,Vector<String>> statementsByTable = new TreeMap<String,Vector<String>>();
		Map<Table,Integer> reorgMap = new HashMap<Table,Integer>();

//		Vector alts = new Vector();
		Iterator it = changeMap.keySet().iterator();
		while(it.hasNext()) {
			Column column = (Column) it.next();
			int flag = ((Integer) changeMap.get(column)).intValue();
			if(flag == CREATE && EngineeringOptionID.generateCreateStatement(selectedOptions)) {				
				if (((LUWColumn)column).getGenerateExpression() != null) {
					String statement1 = builder.setIntegrityForTableBeforeAlter(column, quoteIdentifiers, qualifyNames);
					if (statement1 != null) 
						addStatementToTableSortedMap(statementsByTable,column,statement1,false);
				}
				String statement2 = builder.alterTableAddColumn(column, quoteIdentifiers, qualifyNames);
				if (statement2 != null) 
					addStatementToTableSortedMap(statementsByTable,column,statement2,false);
				if (((LUWColumn)column).getGenerateExpression() != null) {
					String statement3 = builder.setIntegrityForTableAfterAlter(column, quoteIdentifiers, qualifyNames);
					if (statement3 != null) 
						addStatementToTableSortedMap(statementsByTable,column,statement3,false);
				}
			}
			else if (flag == DROP && EngineeringOptionID.generateDropStatement(selectedOptions)) {
				String statement = builder.alterTableDropColumn(column, quoteIdentifiers, qualifyNames, this);
				if(statement != null) {
					addStatementToTableSortedMap(statementsByTable,column,statement,true);
					Table table = getOldContainer(column);
					if (table != null) {
						statement = reorgIfRequired(reorgMap,table,quoteIdentifiers,qualifyNames);
						if (statement != null) 
							addStatementToTableSortedMap(statementsByTable,column,statement,true);
					}
				}
			}
			else {
				if((flag & DATA_TYPE) != 0) {
					String statement = builder.alterTableAlterColumnDataType(column, quoteIdentifiers, qualifyNames);
					if(statement != null) {
						addStatementToTableSortedMap(statementsByTable,column,statement,false);
						Table table = column.getTable();
						if (table != null) {
							statement = reorgIfRequired(reorgMap,table,quoteIdentifiers,qualifyNames);
							if(statement != null)
								addStatementToTableSortedMap(statementsByTable,column,statement,false);
						}
					}
				}
				if((flag & DEFAULT_VALUE) != 0) {
					String statement = builder.alterTableAlterColumnDefaultValue(column, quoteIdentifiers, qualifyNames);
					if(statement != null)
						addStatementToTableSortedMap(statementsByTable,column,statement,false);
				}
				if((flag & NULLABLE) != 0) {
					String statement = builder.alterTableAlterColumnNullable(column, quoteIdentifiers, qualifyNames);
					if(statement != null) {
						addStatementToTableSortedMap(statementsByTable,column,statement,false);
						Table table = column.getTable();
						if (table != null) {
							statement = reorgIfRequired(reorgMap,table,quoteIdentifiers,qualifyNames);
							if(statement != null)
								addStatementToTableSortedMap(statementsByTable,column,statement,false);
						}
					}
				}
				// TODO other changes
			}
		}
		// Address any outstanding reorg requirements
		Set tablesToReorg = reorgMap.keySet();
		Iterator iter = tablesToReorg.iterator();
		while (iter.hasNext()) {
			Table table = (Table)iter.next();
    		String statement = builder.reorgTable(table, quoteIdentifiers, qualifyNames);
    		if (statement != null) 
    			addStatementToTableSortedMap(statementsByTable,table,statement);

		}
		reorgMap.clear();
//        Remove sorting to avoid problem with > 3 ALTERs without a REORG	   	
//        Vector sorter = new CoreDdlScriptVector();
//        sorter.addAll(alts);
//        String[] s = new String[sorter.size()];
//        sorter.copyInto(s);
//        return s;
		
//        Try to sort by table		
		Vector alts = new Vector();
		Iterator iterator = statementsByTable.keySet().iterator();
		while (iterator.hasNext()) {
			Vector stmts = statementsByTable.get((String)iterator.next());
			alts.addAll(stmts);
			stmts.clear();
		}
		statementsByTable.clear();
        String[] s = new String[alts.size()];
        alts.copyInto(s);
        return s;
	}

	protected void addStatementToTableSortedMap(
			Map<String,Vector<String>> statementsByTable,Column column,
			String statement,boolean getOldTable) {
		if(statement != null) {
			Table table = null;
			if (getOldTable)
				table = (Table)((ChangeDescriptionImpl) changeDescription).getOldContainer(column);
			if (table == null)
				table = column.getTable();
			addStatementToTableSortedMap(statementsByTable,table,statement);
		}
	}
	
	protected void addStatementToTableSortedMap(
			Map<String,Vector<String>> statementsByTable,Table table,
			String statement) {
		String schemaName;
		Schema schema = table.getSchema();
		if (schema == null)
			schemaName = "";
		else 
			schemaName = table.getSchema().getName();
		String tableName = schemaName + "." + table.getName();
		if(statement != null) {
			if (!statementsByTable.containsKey(tableName)) {
				statementsByTable.put(tableName,new Vector<String>());
			}
			Vector<String> stmts = statementsByTable.get(tableName);
			stmts.add(statement);
			statementsByTable.put(tableName,stmts);
		}
	}
	
	private String reorgIfRequired(Map<Table, Integer> reorgMap, Table table,
			boolean quoteIdentifiers, boolean qualifyNames) {
		int cnt = 1;
        if (reorgMap.containsKey(table)) {
        	cnt = reorgMap.get(table);
        	if (++cnt >= 3) {
        		reorgMap.remove(table);
        		return builder.reorgTable(table, quoteIdentifiers, qualifyNames);
        	}
        }
    	reorgMap.put(table,cnt);
    	return null;
	}

	protected String[] getCommentOnStatements(Map changeMap) {
		boolean quoteIdentifiers = EngineeringOptionID.generateQuotedIdentifiers(this.getSelectedOptions());
		boolean qualifyNames = EngineeringOptionID.generateFullyQualifiedNames(this.getSelectedOptions());

		Vector comments = new Vector();
		Iterator it = changeMap.keySet().iterator();
		while(it.hasNext()) {
			EObject o = (EObject) it.next();
			int flag = ((Integer) changeMap.get(o)).intValue();
			if ((!shouldExcludeElement(o)) && (((flag & (COMMENT | CREATE)) != 0) ||
			        (SQLSchemaPackage.eINSTANCE.getSchema().isSuperTypeOf(o.eClass()) && 
			        		((flag & RENAME) != 0)))) {
				String statement = null;
	            if(LUWPackage.eINSTANCE.getLUWNickname().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((LUWNickname) o, quoteIdentifiers, qualifyNames);
	            }
                else if(LUWPackage.eINSTANCE.getLUWServer().isSuperTypeOf(o.eClass())) {
                    statement = builder.commentOn((LUWServer) o, quoteIdentifiers, qualifyNames);
                }               
                else if(LUWPackage.eINSTANCE.getLUWWrapper().isSuperTypeOf(o.eClass())) {
                    statement = builder.commentOn((LUWWrapper)o, quoteIdentifiers, qualifyNames);
                }
                else if(LUWPackage.eINSTANCE.getLUWUserMapping().isSuperTypeOf(o.eClass())) {
                    statement = builder.commentOn((LUWUserMapping)o, quoteIdentifiers, qualifyNames);
                }                               
//	            else if(o instanceof LUWCatalogFederatedServer) {
//	                statement = builder.commentOn((LUWCatalogFederatedServer) o, true, true);
//	            }
	            else if(LUWPackage.eINSTANCE.getLUWMaterializedQueryTable().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((LUWMaterializedQueryTable) o, quoteIdentifiers, qualifyNames);
	            }
	            else if(LUWPackage.eINSTANCE.getLUWTable().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((LUWTable) o, quoteIdentifiers, qualifyNames);
	            }
	            else if(LUWPackage.eINSTANCE.getLUWTableSpace().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((LUWTableSpace) o, quoteIdentifiers);
	            }
	            else if(LUWPackage.eINSTANCE.getLUWView().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((LUWView) o, quoteIdentifiers, qualifyNames);
	            }
	            else if(DB2ModelPackage.eINSTANCE.getDB2Alias().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((DB2Alias) o, quoteIdentifiers, qualifyNames);
	            }
	            else if(DB2ModelPackage.eINSTANCE.getDB2Procedure().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((DB2Procedure) o, quoteIdentifiers, qualifyNames);
	            }
	            else if(DB2ModelPackage.eINSTANCE.getDB2UserDefinedFunction().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((DB2UserDefinedFunction) o, quoteIdentifiers, qualifyNames);
	            }
	            else if(DB2ModelPackage.eINSTANCE.getDB2Trigger().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((DB2Trigger) o, quoteIdentifiers, qualifyNames);
	            }
	            else if(SQLConstraintsPackage.eINSTANCE.getCheckConstraint().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((CheckConstraint) o, quoteIdentifiers, qualifyNames);
	            }
	            else if(SQLConstraintsPackage.eINSTANCE.getUniqueConstraint().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((UniqueConstraint) o, quoteIdentifiers, qualifyNames);
	            }
	            else if(SQLConstraintsPackage.eINSTANCE.getForeignKey().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((ForeignKey) o, quoteIdentifiers, qualifyNames);
	            }
	            else if(DB2ModelPackage.eINSTANCE.getDB2Index().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((DB2Index) o, quoteIdentifiers, qualifyNames);
	            }
	            else if(SQLDataTypesPackage.eINSTANCE.getDistinctUserDefinedType().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((DistinctUserDefinedType) o, quoteIdentifiers, qualifyNames);
	            }
	            else if(SQLTablesPackage.eINSTANCE.getColumn().isSuperTypeOf(o.eClass())) {
	                statement = builder.commentOn((Column) o, quoteIdentifiers, qualifyNames);
	            }
	            else if (LUWPackage.eINSTANCE.getLUWPartitionGroup().isSuperTypeOf(o.eClass())) {
	            	statement = builder.commentOn((LUWPartitionGroup)o, quoteIdentifiers);
	            }
	            else if (SQLSchemaPackage.eINSTANCE.getSchema().isSuperTypeOf(o.eClass())) {
	            	statement = builder.commentOn((Schema)o, quoteIdentifiers, qualifyNames);
	            }
	            else if (SQLAccessControlPackage.eINSTANCE.getRole().isSuperTypeOf(o.eClass())) {
	            	statement = builder.commentOn((Role)o, quoteIdentifiers, qualifyNames);
	            }
	            else if (LUWPackage.eINSTANCE.getLUWModule().isSuperTypeOf(o.eClass())) {
	            	statement = builder.commentOn((LUWModule)o, quoteIdentifiers, qualifyNames);
	            }
	            if(statement != null) comments.add(statement);
			}
		}
	   	
        Vector sorter = new CoreDdlScriptVector();
        sorter.addAll(comments);
        String[] s = new String[sorter.size()];
        sorter.copyInto(s);
        return s;
	}

	protected boolean canAlter(String type1, String type2, int length1, int length2, int scale1, int scale2) {
		if(type1.equals("SMALLINT")) { //$NON-NLS-1$
			if(type2.equals("SMALLINT")) return true; //$NON-NLS-1$
			if(type2.equals("INTEGER")) return true; //$NON-NLS-1$
			if(type2.equals("BIGINT")) return true; //$NON-NLS-1$
			if(type2.equals("FLOAT")) return true; //$NON-NLS-1$
			if(type2.equals("DOUBLE")) return true; //$NON-NLS-1$
			if(type2.equals("DECIMAL") && (length2-scale2) > 4) return true; //$NON-NLS-1$
		}
		else if(type1.equals("INTEGER")) { //$NON-NLS-1$
			if(type2.equals("INTEGER")) return true; //$NON-NLS-1$
			if(type2.equals("BIGINT")) return true; //$NON-NLS-1$
			if(type2.equals("FLOAT")) return true; //$NON-NLS-1$
			if(type2.equals("DOUBLE")) return true; //$NON-NLS-1$
			if(type2.equals("DECIMAL") && (length2-scale2) > 9) return true;			 //$NON-NLS-1$
		}
		else if(type1.equals("DECIMAL")) { //$NON-NLS-1$
			if(type2.equals("SMALLINT") && length1 < 5 && scale1 == 0) return true; //$NON-NLS-1$
			if(type2.equals("INTEGER") && length1 < 10 && scale1 == 0) return true; //$NON-NLS-1$
			if(type2.equals("BIGINT") && length1 < 19 && scale1 == 0) return true; //$NON-NLS-1$
			if(type2.equals("FLOAT") && length1 < 7) return true; //$NON-NLS-1$
			if(type2.equals("DOUBLE") && length1 < 16) return true; //$NON-NLS-1$
			if(type2.equals("DECIMAL") && length2 >= length1 && scale2 >= scale1 && (length2-scale2) >= (length1 - scale1)) return true;			 //$NON-NLS-1$
		}
		else if(type1.equals("FLOAT")) { //$NON-NLS-1$
			if(type2.equals("FLOAT")) return true; //$NON-NLS-1$
			if(type2.equals("DOUBLE")) return true;			 //$NON-NLS-1$
		}
		else if(type1.equals("CHAR") || type1.equals("VARCHAR")) { //$NON-NLS-1$ //$NON-NLS-2$
			if(type2.equals("CHAR") && length2 >= length1) return true; //$NON-NLS-1$
			if(type2.equals("VARCHAR") && length2 >= length1) return true; //$NON-NLS-1$
		}
		else if(type1.equals("GRAPHIC") || type1.equals("VARGRAPHIC")) { //$NON-NLS-1$ //$NON-NLS-2$
			if(type2.equals("GRAPHIC") && length2 >= length1) return true; //$NON-NLS-1$
			if(type2.equals("VARGRAPHIC") && length2 >= length1) return true;			 //$NON-NLS-1$
		}
 
		return false;
	}
	
//<bpg	public static OlapDeltaDdlBuilder getOlapDeltaDdlBuilder(String dbProduct, String dbVersion) {
//		if (olapDletaDdlBuilder != null) return olapDletaDdlBuilder;
//
//		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
//		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.enablement.core", "olapDdlBuilder"); //$NON-NLS-1$ //$NON-NLS-2$
//		IExtension[] extensions = extensionPoint.getExtensions();
//		for(int i=0; i<extensions.length; ++i) {
//			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
//			for(int j=0; j<configElements.length; ++j) {
//				if(configElements[j].getName().equals("delta")) { //$NON-NLS-1$
//					String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
//					if(!product.equals(dbProduct)) continue;
//					try {
//						olapDletaDdlBuilder = (OlapDeltaDdlBuilder) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
//					}
//					catch(CoreException e) {
//					    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getDefault().getBundle().getSymbolicName(), IStatus.ERROR,
//					            "The error was detected when creating the olap delta ddl builder for " + product, e); //$NON-NLS-1$
//						DataToolsPlugin.getDefault().getLog().log(status);
//					}
//					break;
//				}
//			}
//		}
//		return olapDletaDdlBuilder;
//bgp>	}
	
//bgp	protected static OlapDeltaDdlBuilder olapDletaDdlBuilder = null;

	public Object getOldContainerValue(EStructuralFeature feature,
			LUWDatabaseContainer container) {
		return getOldValue(feature,container);
	}

}
