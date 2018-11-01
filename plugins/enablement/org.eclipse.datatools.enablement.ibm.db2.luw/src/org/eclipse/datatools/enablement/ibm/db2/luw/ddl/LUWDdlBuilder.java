/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.ddl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogStatistics;
import org.eclipse.datatools.enablement.ibm.db2.ddl.DB2DdlBuilder;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.BufferPoolType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAttributeDefinition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWContainerType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWCursorDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGlobalVariable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMaterializedQueryTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleArrayDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCondition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCursorDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleDistinctType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleGlobalVariable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleRowDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRowDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.ManagementType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackageBody;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PageSizeType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PartitionMethod;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.TableSpaceType;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Alias;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Index;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2UserDefinedFunction;
import org.eclipse.datatools.enablement.ibm.db2.model.GenerateType;
import org.eclipse.datatools.enablement.ibm.db2.model.UnitType;
import org.eclipse.datatools.enablement.ibm.ddl.DdlGenerationUtility;
import org.eclipse.datatools.enablement.ibm.ddl.ExtendDdlBuilder;
import org.eclipse.datatools.enablement.ibm.util.EngineeringOptionID;
import org.eclipse.datatools.enablement.ibm.util.ModelHelper;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.Field;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.routines.Method;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.CheckType;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWDdlBuilder extends DB2DdlBuilder {
	
	// Currently XMLSupport is limited to LUW. Once it is extended to i/zSeries,
	// this should move upwards to parent class 
	protected final static String OPTIMIZATION = "OPTIMIZATION"; //$NON-NLS-1$
	protected final static String QUERY = "QUERY"; //$NON-NLS-1$
	protected final static String ENABLE = "ENABLE"; //$NON-NLS-1$
	protected final static String ENFORCED = "ENFORCED"; //$NON-NLS-1$
	protected final static String XSROBJECT = "XSROBJECT"; //$NON-NLS-1$
	protected final static String PARTITION_KEY = "PARTITIONING KEY"; //$NON-NLS-1$
	protected final static String PARTITION_GROUP = "PARTITION GROUP"; //$NON-NLS-1$
    protected final static String BUFFER_POOL = "BUFFER POOL"; //$NON-NLS-1$
	protected final static String UNDER = "UNDER"; //$NON-NLS-1$
	protected final static String RESTRICT = "RESTRICT"; //$NON-NLS-1$
	protected final static String DIMENSION_BY = "ORGANIZE BY DIMENSIONS"; //$NON-NLS-1$
	protected final static String VALUE_COMPRESSION = "VALUE COMPRESSION"; //$NON-NLS-1$
	protected final static String USE_HASH = "USING HASHING"; //$NON-NLS-1$
	protected final static String INTEGRITY = "INTEGRITY"; //$NON-NLS-1$
	protected final static String ADMIN_CMD = "SYSPROC.ADMIN_CMD"; //$NON-NLS-1$
    protected final static String REORG = "REORG"; //$NON-NLS-1$
    protected final static String CALL = "CALL"; //$NON-NLS-1$
    protected final static String VARIABLE = "VARIABLE";
    protected final static String CONSTANT	= "CONSTANT";
    protected final static String ARRAY	= "ARRAY";
    protected final static String CURSOR = "CURSOR";
    protected final static String MODULE = "MODULE";
    protected final static String OFF = "OFF";
	protected static final String NOT_LOGGED = " NOT LOGGED"; //$NON-NLS-1$
	protected static final String IBM = "IBM"; //$NON-NLS-1$
	protected static final String SYS = "SYS"; //$NON-NLS-1$
    protected final static String HEX_LITERAL_PREFIX   = "X"; // $NON-NLS-1$
    
    protected final static int GB = 2<<(30-1);
	protected final static String FILE = "FILE"; // $NON-NLS-1$
	protected final static String DEVICE = "DEVICE"; // $NON-NLS-1$
	protected static final String BUFFERPOOL = "BUFFERPOOL"; // $NON-NLS-1$
	protected static final String OVERHEAD = "OVERHEAD"; // $NON-NLS-1$
	protected static final String XFER_RATE = "TRANSFERRATE"; // $NON-NLS-1$
	protected static final String DROPPED_TABLE_RECOVERY = "DROPPED TABLE RECOVERY"; // $NON-NLS-1$
	protected static final String AUTORESIZE = "AUTORESIZE"; // $NON-NLS-1$
	protected static final String MAXSIZE = "MAXSIZE"; // $NON-NLS-1$
	protected static final String PERCENT = "PERCENT"; // $NON-NLS-1$
	protected static final String INCREASESIZE = "INCREASESIZE"; // $NON-NLS-1$
	protected static final String NONE = "NONE"; // $NON-NLS-1$
	protected static final String AUTOMATIC = "AUTOMATIC"; // $NON-NLS-1$
	protected static final String PREFETCHSIZE = "PREFETCHSIZE"; // $NON-NLS-1$
	protected static final String XFER_OWNERSHIP = "TRANSFER OWNERSHIP OF"; // $NON-NLS-1$
	protected static final String PRESERVE_PRIVILEGES = "PRESERVE PRIVILEGES"; // $NON-NLS-1$
	protected static final String RESIZE = "RESIZE"; // $NON-NLS-1$
	
	public LUWDdlBuilder(){
	}
	
	public LUWDdlBuilder(LUWDdlGenerator generator) {
		this.generator = generator;
	}
	
    public String createSchema(Schema schema, boolean quoteIdentifiers,boolean qualifyNames) {
    	String text = CREATE + SPACE + SCHEMA + SPACE + getName(schema, quoteIdentifiers,qualifyNames);
    	AuthorizationIdentifier auth=schema.getOwner();
    	if (auth != null) {
    		text +=  SPACE + AUTHORIZATION + SPACE+ getName(auth, quoteIdentifiers);
    	}
    	return text;
    }

    public String createTablespace(LUWTableSpace tablespace, boolean quoteIdentifiers) {
        if(tablespace.getContainers().isEmpty() && this.isTablespaceContainersRequried(tablespace)) {
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					LUWDdlMessages.FE_TABLESPACE_NO_CONTAINER, new Object[] { tablespace.getName()}));
        	return null;
        }
		
        String tablespaceName = tablespace.getName();
        if(quoteIdentifiers) {
            tablespaceName = this.getDoubleQuotedString(tablespaceName);
        }
    
        String text = "CREATE "; //$NON-NLS-1$
        TableSpaceType type = tablespace.getTablespaceType();
        if(type == TableSpaceType.REGULAR_LITERAL) {
            text += "REGULAR"; //$NON-NLS-1$
        }
        else if(type == TableSpaceType.USER_TEMP_LITERAL) {
            text += "USER TEMPORARY"; //$NON-NLS-1$
        }
        else if(type == TableSpaceType.SYSTEM_TEMP_LITERAL) {
            text += "SYSTEM TEMPORARY"; //$NON-NLS-1$
        }
        else if(type == TableSpaceType.LARGE_LITERAL) {
            text += "LARGE"; //$NON-NLS-1$
	    }
        text += " TABLESPACE " + tablespaceName; //$NON-NLS-1$
        
        LUWPartitionGroup pg = tablespace.getGroup();
        if (pg != null){
        	String pgName = pg.getName();
            if(quoteIdentifiers) {
            	pgName = this.getDoubleQuotedString(pgName);
            }

        	text += NEWLINE + TAB + IN + SPACE + DATABASE + SPACE + PARTITION_GROUP + SPACE + pgName;
        }
        
        text += NEWLINE + TAB + "PAGESIZE "; //$NON-NLS-1$
        //First get the page size from the buffer pool
        PageSizeType pageSize = null;
        if (tablespace.getBufferPool() != null) {
        	pageSize = tablespace.getBufferPool().getPageSize();	
        }
        else {
        	pageSize = tablespace.getPageSize();
        }
        if(pageSize == PageSizeType.FOUR_K_LITERAL) {
            text += "4 K"; //$NON-NLS-1$
        }
        else if(pageSize == PageSizeType.EIGHT_K_LITERAL) {
            text += "8 K"; //$NON-NLS-1$
        }
        else if(pageSize == PageSizeType.SIXTEEN_K_LITERAL) {
            text += "16 K"; //$NON-NLS-1$
        }
        else if(pageSize == PageSizeType.THIRTY_TWO_K_LITERAL) {
            text += "32 K"; //$NON-NLS-1$
        }
        else if (pageSize == PageSizeType.FOUR_KB_LITERAL) {
        	text += "4096";
        }
        else if (pageSize == PageSizeType.EIGHT_KB_LITERAL) {
        	text += "8192";
        }
        else if (pageSize == PageSizeType.SIXTEEN_KB_LITERAL) {
        	text += "16384";
        }
        else if (pageSize == PageSizeType.THIRTY_TWO_KB_LITERAL) {
        	text += "32768";
        }

        text += NEWLINE + TAB + "MANAGED BY "; //$NON-NLS-1$
        if (tablespace.getManagementType() == ManagementType.DATABASE_MANAGED_LITERAL) {
            text += "DATABASE"; //$NON-NLS-1$
            if (hasContainers( tablespace )) {
            	text += this.getContainers(tablespace);
            }
        }
        else if (tablespace.getManagementType() == ManagementType.SYSTEM_MANAGED_LITERAL) {
            text += "SYSTEM"; //$NON-NLS-1$
            if (hasContainers( tablespace )) {
            	text += this.getContainers(tablespace);
            }
        }
        else {
            text += "AUTOMATIC STORAGE"; //$NON-NLS-1$
        }

        if (tablespace.getManagementType() != ManagementType.SYSTEM_MANAGED_LITERAL) {
        	long initsize = tablespace.getInitialSize();
        	UnitType initunit = tablespace.getInitialSizeUnit();
        	long incrsize = tablespace.getIncreaseSize();
        	UnitType incrunit = tablespace.getIncreaseSizeUnit();
        	int incrpct = tablespace.getIncreasePercent();
        	long maxsize = tablespace.getMaximumSize();
        	UnitType maxunit = tablespace.getMaximumSizeUnit();
        	
        	if (initsize != 0 || incrsize != 0 || incrpct != 0 || maxsize != 0) {
        		text += NEWLINE + "AUTORESIZE YES"; //$NON-NLS-1$
        	}

        	if (tablespace.getManagementType() == ManagementType.AUTOMATIC_STORAGE_LITERAL
        			&& initsize != 0) {
        		text += NEWLINE + "INITIALSIZE " + initsize; //$NON-NLS-1$
        		if (initunit != null) {
        			text += " " + initunit.getLiteral(); //$NON-NLS-1$
        		}
        	}

        	if (incrsize != 0) {
        		text += NEWLINE + "INCREASESIZE " + incrsize; //$NON-NLS-1$
        		if (incrunit != null) {
        			text += " " + incrunit.getLiteral(); //$NON-NLS-1$
        		}
        	}
        	else if (incrpct != 0) {
        		text += NEWLINE + "INCREASESIZE " //$NON-NLS-1$
        				+ incrpct + " PERCENT"; //$NON-NLS-1$
        	}

        	if (maxsize != 0) {
        		text += NEWLINE + "MAXSIZE " + maxsize; //$NON-NLS-1$
        		if (maxunit != null) {
        			text += " " + maxunit.getLiteral(); //$NON-NLS-1$
        		}
        	}
        }

        final int extentsize = tablespace.getExtentSize();
        if (extentsize > 0) {
            text += NEWLINE + TAB + "EXTENTSIZE " + extentsize; //$NON-NLS-1$
        }
    
        final int prefetchsize = tablespace.getPreFetchSize();
        if (prefetchsize > 0) {
            text += NEWLINE + TAB + "PREFETCHSIZE " + prefetchsize; //$NON-NLS-1$
        }

        LUWBufferPool bufferpool = tablespace.getBufferPool();
        if(bufferpool != null) {
        	String bufferpoolName = bufferpool.getName();
            if(quoteIdentifiers) {
            	bufferpoolName = this.getDoubleQuotedString(bufferpoolName);
            }

            text += NEWLINE + TAB + "BUFFERPOOL " + bufferpoolName; //$NON-NLS-1$
        }

        Double overhead = tablespace.getOverhead();
        if (overhead > 0 ){
        	text += NEWLINE + TAB + "OVERHEAD " + overhead;
        }
        
        if (tablespace.getTransferRate() > 0) {
        	text += NEWLINE + TAB + "TRANSFERRATE " + tablespace.getTransferRate(); //$NON-NLS-1$
        }

        text += RecoverDroppedTableString(tablespace);
        	
        return text;    
    }

    public String dropTablespace(LUWTableSpace tablespace, boolean quoteIdentifiers) {
        String tablespaceName = tablespace.getName();
        if(quoteIdentifiers) {
            tablespaceName = this.getDoubleQuotedString(tablespaceName);
        }

        return "DROP TABLESPACE " + tablespaceName; //$NON-NLS-1$
    }

    public String createBufferPool(LUWBufferPool bufferpool, boolean quoteIdentifiers) {
        String bufferpoolName = bufferpool.getName();
        if(quoteIdentifiers) {
        	bufferpoolName = this.getDoubleQuotedString(bufferpoolName);
        }
    
        String text = "CREATE BUFFERPOOL " + bufferpoolName; //$NON-NLS-1$
        
        if (bufferpool.getCreateType() ==BufferPoolType.DEFERRED_LITERAL ) {
        	text += TAB + "DEFERRED"; //$NON-NLS-1$
        }
        
        EList pgs = bufferpool.getPartitionGroup();
        if (pgs.isEmpty()) {
        	text += NEWLINE + TAB + "ALL DBPARTITIONNUMS ";  //$NON-NLS-1$
        } else {
        	StringBuilder pgtext = new StringBuilder();

        	for ( Object pgobj : pgs )
        	{
        		LUWPartitionGroup pg = (LUWPartitionGroup)pgobj;
        		
        		if (pgtext.length() > 0) {
        			pgtext.append( ", " );
        		}
        		pgtext.append( pg.getName() );
        	}
        	
        	text += NEWLINE + TAB + "DATABASE PARTITION GROUP " //$NON-NLS-1$
        		+ pgtext.toString();
        }
        
        String sizeStr = this.getBufferPoolSize(bufferpool);
        if (sizeStr == null) {
        	return null;     //not right size
        } else {
        	text += sizeStr;
        }
        int numBlockPages = bufferpool.getNumBlockPages();
        text += NEWLINE + TAB +"NUMBLOCKPAGES " + bufferpool.getNumBlockPages(); //$NON-NLS-1$

        if (numBlockPages >0) {   //0 mean disable blockSize
	        int blocksize = bufferpool.getBlockSize();
	        if (blocksize >1) {
	        	text += SPACE + "BLOCKSIZE " + blocksize; //$NON-NLS-1$
	        }
        }
        text += NEWLINE + TAB + "PAGESIZE "; //$NON-NLS-1$
        PageSizeType pageSize = bufferpool.getPageSize();
        if(pageSize == PageSizeType.FOUR_K_LITERAL) {
            text += "4 K"; //$NON-NLS-1$
        }
        else if(pageSize == PageSizeType.EIGHT_K_LITERAL) {
            text += "8 K"; //$NON-NLS-1$
        }
        else if(pageSize == PageSizeType.SIXTEEN_K_LITERAL) {
            text += "16 K"; //$NON-NLS-1$
        }
        else if(pageSize == PageSizeType.THIRTY_TWO_K_LITERAL) {
            text += "32 K"; //$NON-NLS-1$
        }

        List sizeexeptions = bufferpool.getSizeException();

        if (!sizeexeptions.isEmpty()) {
        	StringBuilder parttext = new StringBuilder( NEWLINE + "EXCEPT ON DBPARTITIONNUMS (" );
        	boolean first = true;
        	
	        for (Object o : sizeexeptions) {
	        	LUWBufferPoolSizeException sizeex = (LUWBufferPoolSizeException)o;
	        	
	        	String size = " SIZE " + sizeex.getSize();
	        	List partns = sizeex.getPartitions();
	        	
	        	for ( Object partobj : partns ) {
	        		LUWDatabasePartition part = (LUWDatabasePartition)partobj;
	        		
	        		if (!first) {
	        			parttext.append(", ");
	        		}
	        		else {
	        			first = false;
	        		}
	        		
	        		parttext.append( part.getNumber() );
	        		parttext.append( size );
	        	}
	        }

	        parttext.append( ")" );
	        
	        text += parttext.toString();
	    }
        
        if (bufferpool.isExtendedStorage() && supportsBufferpoolExtendedStorage()){
        	text += NEWLINE + "EXTENDED STORAGE"; //$NON-NLS-1$
        }
        
        return text;    
    }

    /**
	 * @return
	 */
	protected boolean supportsBufferpoolExtendedStorage() {
		//V8 and before support EXTENDED STORAGE
		return true;
	}

	public String dropBufferPool(LUWBufferPool bufferpool, boolean quoteIdentifiers) {
        String bufferpoolName = bufferpool.getName();
        if(quoteIdentifiers) {
        	bufferpoolName = this.getDoubleQuotedString(bufferpoolName);
        }

        return "DROP BUFFERPOOL " + bufferpoolName; //$NON-NLS-1$
    }
    
    
    public String createSequence(Sequence sequence, boolean quoteIdentifiers, boolean qualifyNames) {
        String sequenceName = sequence.getName();
        String schemaName = sequence.getSchema().getName();

        if(quoteIdentifiers) {
            sequenceName = this.getDoubleQuotedString(sequenceName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }
        
        if(qualifyNames) {
            sequenceName = schemaName + "." + sequenceName; //$NON-NLS-1$
        }

        String statement = "CREATE SEQUENCE " + sequenceName + " AS "; //$NON-NLS-1$ //$NON-NLS-2$

        // String dataType = this.getDataTypeString(sequence,sequence.getSchema(),qualifyNames);
        // fix for issue wsdbu00985096
        String dataType = this.getDataTypeString(sequence,sequence.getSchema(),quoteIdentifiers,qualifyNames); 
        
        if (dataType == null) {
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					LUWDdlMessages.FE_TABLESPACE_NO_CONTAINER, new Object[] { sequenceName}));
        	return null;
        }
        
        statement += dataType;
        
        DB2IdentitySpecifier identity = (DB2IdentitySpecifier)sequence.getIdentity();
        statement += " " + this.getIdentityString(identity); //$NON-NLS-1$
        
        return statement;
    }

    public String createMQT(LUWMaterializedQueryTable table, boolean quoteIdentifiers, boolean qualifyNames){
        String tableName = table.getName();
        String schemaName = table.getSchema().getName();

        if(quoteIdentifiers) {
            tableName = this.getDoubleQuotedString(tableName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }
        
        if(qualifyNames) {
            tableName = schemaName + "." + tableName; //$NON-NLS-1$
        }

        String statement = "CREATE TABLE " + tableName + " ("; //$NON-NLS-1$ //$NON-NLS-2$

        Iterator it = table.getColumns().iterator();
        while(it.hasNext()) {
            Column column = (Column) it.next();
            statement += column.getName();
            if(it.hasNext()) {
                statement += ", ";                 //$NON-NLS-1$
            }
        }
        statement += ")" + NEWLINE + "AS (";                 //$NON-NLS-1$ //$NON-NLS-2$
        
        QueryExpression expression = table.getQueryExpression();
        if (expression == null) {
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					LUWDdlMessages.FE_MQT_HAS_NO_BODY, new Object[] { schemaName+"."+tableName}));
        	return null;
        }
        
        statement += expression.getSQL() + ")" +NEWLINE; //$NON-NLS-1$

//<bgp        statement += "DATA INITIALLY DEFERRED REFRESH "; //$NON-NLS-1$
//        if(table.getRefresh() == RefreshType.IMMEDIATE_LITERAL) {
//            statement += "IMMEDIATE"; //$NON-NLS-1$
//        }
//        else {
//            statement += "DEFERRED"; //$NON-NLS-1$
//bgp>        }

        if(table.isOptimizeQuery()) {
            statement += " ENABLE"; //$NON-NLS-1$
        }
        else {
            statement += " DISABLE"; //$NON-NLS-1$
        }
        statement += " QUERY OPTIMIZATION" + NEWLINE; //$NON-NLS-1$

//<bgp	    statement += "MAINTAINED BY "; //$NON-NLS-1$
//		if(table.getMaintainedBy() == MaintenanceType.SYSTEM_LITERAL) {
//		    statement += "SYSTEM"; //$NON-NLS-1$
//		}
//		else {
//		    statement += "USER"; //$NON-NLS-1$
//bgp>        }
		
		if (this.generator != null && !EngineeringOptionID.generateInTablespaceClause(this.generator.getSelectedOptions())){ //@d00058820gs
		} else {
			statement += this.getTablespaceString(table, quoteIdentifiers);
		}
		
		statement += this.getPartitionKey(table,quoteIdentifiers);

		statement += this.getCompressionValue(table);
        return statement;
    }

    public String dropMQT(LUWMaterializedQueryTable table, boolean quoteIdentifiers, boolean qualifyNames) {
        String tableName = table.getName();
        String schemaName = table.getSchema().getName();

        if(quoteIdentifiers) {
            tableName = this.getDoubleQuotedString(tableName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if(qualifyNames) {
            tableName = schemaName + "." + tableName; //$NON-NLS-1$
        }
    
        return "DROP TABLE " + tableName; //$NON-NLS-1$
    }

    public String dropPackage(LUWDatabasePackage dbpackage, boolean quoteIdentifiers, boolean qualifyNames) {
    	String schemaName = dbpackage.getSchema().getName();
    	String packageName = dbpackage.getName();
    	String versionName = null;

        if(quoteIdentifiers) {
            schemaName = this.getDoubleQuotedString(schemaName);
            packageName = this.getDoubleQuotedString(packageName);
        }

        if(qualifyNames) {
            packageName = schemaName + "." + packageName; //$NON-NLS-1$
        }
        
        if (dbpackage.getVersion() != null && dbpackage.getVersion().trim().length() > 0) {
        	versionName = this.getDoubleQuotedString(dbpackage.getVersion().trim());
        }
    
        return "DROP PACKAGE " + packageName + ((versionName != null) ? " VERSION " + versionName : ""); //$NON-NLS-1$
    }
    
    public String createTable(BaseTable table, boolean quoteIdentifiers, boolean qualifyNames) {
        String tableDefinition = super.createTable(table, quoteIdentifiers, qualifyNames);
        
        LUWTable t = (LUWTable) table;

        tableDefinition += this.getTableDimension(t, quoteIdentifiers);
        tableDefinition += this.getDataCapture(t);

        if (this.generator != null && !EngineeringOptionID.generateInTablespaceClause(this.generator.getSelectedOptions())){ //@d00058820gs
        } else {
            tableDefinition += this.getTablespaceString(t, quoteIdentifiers);
        }
        tableDefinition += this.getPartitionKey(t,quoteIdentifiers);
        
        tableDefinition += this.getCompressionValue(t);
        
        return tableDefinition;
    }
    
    public String createView(LUWView view, boolean quoteIdentifiers, boolean qualifyNames) {
    	QueryExpression expression = view.getQueryExpression();
    	if (expression == null) {
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					LUWDdlMessages.FE_VIEW_HAS_NO_BODY, new Object[] { getName(view, false, true)}));
        	return null;
    	}

    	String sql = expression.getSQL();
    	
        String viewDefinition = CREATE + SPACE;
        if(view.isFederated()) {
            viewDefinition += "FEDERATED "; //$NON-NLS-1$
        }        
        viewDefinition += VIEW + SPACE + getName(view, quoteIdentifiers, qualifyNames) + SPACE;
        
        String columns = getViewColumnList(view,quoteIdentifiers);
        if(columns != null) {
            viewDefinition += LEFT_PARENTHESIS + columns + RIGHT_PARENTHESIS + SPACE;
        }
        viewDefinition += AS + NEWLINE;
        viewDefinition += sql;
        CheckType checkType = view.getCheckType();
        if(checkType == CheckType.CASCADED_LITERAL) {
            viewDefinition += NEWLINE + WITH + SPACE + CASCADED + SPACE + CHECK + SPACE + OPTION;
        }
        else if(checkType == CheckType.LOCAL_LITERAL) {
            viewDefinition += NEWLINE + WITH + SPACE + LOCAL + SPACE + CHECK + SPACE + OPTION;
        }
        return viewDefinition;
    }
    
    public String createIndex(Index index, boolean quoteIdentifiers, boolean qualifyNames) {
    	if (getIndexKeyColumns(index, quoteIdentifiers) == null) {
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					LUWDdlMessages.FE_INDEX_MEMBERS_NOT_SPECIFIED, new Object[] { index.getName()}));
            return null;
    	}
        String indexDefinition = super.createIndex(index, quoteIdentifiers, qualifyNames);

        if (index instanceof LUWIndex)
        {
        	indexDefinition += getDataPartitionOptions( (LUWIndex)index );
        }
        
        DB2Index ind = (DB2Index) index;

        if(ind.getTable() instanceof LUWNickname) {
            indexDefinition += " SPECIFICATION ONLY"; //$NON-NLS-1$
        }
        else {
            String included = this.getIndexIncludedColumns(ind, quoteIdentifiers);
            if(included != null) {
                indexDefinition += NEWLINE + "INCLUDE (" + included + ")" + NEWLINE; //$NON-NLS-1$ //$NON-NLS-2$
            }
            
            if(ind.isClustered()) {
                indexDefinition += SPACE + "CLUSTER ";                                 //$NON-NLS-1$
            }
            
            int i = ind.getFillFactor();
            indexDefinition += SPACE + "PCTFREE " + i; //$NON-NLS-1$

//            DB2IndexType indexType = ind.getIndexType();
//            if(indexType == DB2IndexType.REGULAR_LITERAL) {
//                
//            }
//            else if(indexType == DB2IndexType.DIMENSION_LITERAL) {
//                
//            }
//            else if(indexType == DB2IndexType.BLOCK_LITERAL) {
//                
//            }
            indexDefinition += NEWLINE + "ALLOW REVERSE SCANS";
        }
        
        return indexDefinition;
    }

	protected String getDataPartitionOptions( LUWIndex index )
	{
		return ""; //$NON-NLS-1$
	}

    //Nickname
    public String createNickname(LUWNickname nickname, boolean quoteIdentifiers, boolean qualifyNames) {
        if (nickname != null) {        
        	Database database = nickname.getSchema().getDatabase();
        	ExtendDdlBuilder nicknameDdlBuilder = DdlGenerationUtility.getNicknameDdlBuilder(database.getVendor(),database.getVersion());
        	if (nicknameDdlBuilder != null) {
        		String ddl = nicknameDdlBuilder.buildCreateStatement(nickname,quoteIdentifiers,qualifyNames);
        		if (ddl != null) return ddl;
        	}
        }
   		return null;
    }
    
    public String dropNickname(LUWNickname nickname, boolean quoteIdentifiers, boolean qualifyNames) {
        if (nickname != null) {      
        	Database database = nickname.getSchema().getDatabase();
        	ExtendDdlBuilder nicknameDdlBuilder = DdlGenerationUtility.getNicknameDdlBuilder(database.getVendor(),database.getVersion());
        	if (nicknameDdlBuilder != null) {
        		String ddl = nicknameDdlBuilder.buildDropStatement(nickname,quoteIdentifiers,qualifyNames);
        		if (ddl != null) return ddl;
        	}
        }
   		return null;
    }
    
    public String commentOn(LUWNickname nickname, boolean quoteIdentifiers, boolean qualifyNames) {
        if (nickname != null) {        
        	Database database = nickname.getSchema().getDatabase();
        	ExtendDdlBuilder nicknameDdlBuilder = DdlGenerationUtility.getNicknameDdlBuilder(database.getVendor(),database.getVersion());
        	if (nicknameDdlBuilder != null) {
        		String ddl = nicknameDdlBuilder.buildCommentOnStatement(nickname,quoteIdentifiers,qualifyNames);
        		if (ddl != null) return ddl;
        	}
        }
    	return null;
    }
   
    //Federated Procedure - pyl
    public String createFederatedProcedure(FederatedProcedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {
        if (procedure != null) {        
        	Database database = procedure.getSchema().getDatabase();
        	ExtendDdlBuilder federatedProcedureDdlBuilder = DdlGenerationUtility.getFederatedProcedureDdlBuilder(database.getVendor(),database.getVersion());
        	if (federatedProcedureDdlBuilder != null) {
        		String ddl = federatedProcedureDdlBuilder.buildCreateStatement(procedure,quoteIdentifiers,qualifyNames);
        		if (ddl != null) return ddl;
        	}
        }
   		return null;
    }
    
    public String dropFederatedProcedure(FederatedProcedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {
        if (procedure != null) {      
        	Database database = procedure.getSchema().getDatabase();
        	ExtendDdlBuilder federatedProcedureDdlBuilder = DdlGenerationUtility.getFederatedProcedureDdlBuilder(database.getVendor(),database.getVersion());
        	if (federatedProcedureDdlBuilder != null) {
        		String ddl = federatedProcedureDdlBuilder.buildDropStatement(procedure,quoteIdentifiers,qualifyNames);
        		if (ddl != null) return ddl;
        	}
        }
   		return null;
    }
    
    public String commentOn(FederatedProcedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {
        if (procedure != null) {        
        	Database database = procedure.getSchema().getDatabase();
        	ExtendDdlBuilder federatedProcedureDdlBuilder = DdlGenerationUtility.getFederatedProcedureDdlBuilder(database.getVendor(),database.getVersion());
        	if (federatedProcedureDdlBuilder != null) {
        		String ddl = federatedProcedureDdlBuilder.buildCommentOnStatement(procedure,quoteIdentifiers,qualifyNames);
        		if (ddl != null) return ddl;
        	}
        }
    	return null;
    }
    
    //Remote Server
    public String createRemoteServer(LUWServer remoteServer, boolean quoteIdentifiers, boolean qualifyNames) {
        if (remoteServer != null) {        
        	Database database = remoteServer.getLUWDatabase();
        	ExtendDdlBuilder remoteServerDdlBuilder = DdlGenerationUtility.getRemoteServerDdlBuilder(database.getVendor(),database.getVersion());
        	if (remoteServerDdlBuilder != null) {
        		String ddl = remoteServerDdlBuilder.buildCreateStatement(remoteServer,quoteIdentifiers,qualifyNames);
        		if (ddl != null) return ddl;
        	}
        }
   		return null;
    }
   
    public String dropRemoteServer(LUWServer remoteServer, boolean quoteIdentifiers, boolean qualifyNames) {
        if (remoteServer != null) {
        	Database database = remoteServer.getLUWDatabase();
        	ExtendDdlBuilder remoteServerDdlBuilder = DdlGenerationUtility.getRemoteServerDdlBuilder(database.getVendor(),database.getVersion());
        	if (remoteServerDdlBuilder != null) {
        		String ddl = remoteServerDdlBuilder.buildDropStatement(remoteServer,quoteIdentifiers,qualifyNames);
        		if (ddl != null) return ddl;
        	}
        }
   		return null;   		
    }

    public String commentOn(LUWServer remoteServer, boolean quoteIdentifiers, boolean qualifyNames) {
        if (remoteServer != null) {        
            Database database = remoteServer.getLUWDatabase();
            ExtendDdlBuilder remoteServerDdlBuilder = DdlGenerationUtility.getRemoteServerDdlBuilder(database.getVendor(),database.getVersion());
            if (remoteServerDdlBuilder != null) {
                String ddl = remoteServerDdlBuilder.buildCommentOnStatement(remoteServer,quoteIdentifiers,qualifyNames);
                if (ddl != null) return ddl;
            }
        }
        return null;    	
    }
    
    //Wrapper
    public String createWrapper(LUWWrapper wrapper, boolean quoteIdentifiers, boolean qualifyNames) {
        if (wrapper != null) {
            Database database = wrapper.getLUWDatabase();
            ExtendDdlBuilder wrapperDdlBuilder = DdlGenerationUtility.getWrapperDdlBuilder(database.getVendor(),database.getVersion());
            if (wrapperDdlBuilder != null) {
                String ddl = wrapperDdlBuilder.buildCreateStatement(wrapper,quoteIdentifiers,qualifyNames);
                if (ddl != null) return ddl;
            }
        }
        return null;
    }
   
    public String dropWrapper(LUWWrapper wrapper, boolean quoteIdentifiers, boolean qualifyNames) {
        if (wrapper != null) {        
            Database database = wrapper.getLUWDatabase();
            ExtendDdlBuilder wrapperDdlBuilder = DdlGenerationUtility.getWrapperDdlBuilder(database.getVendor(),database.getVersion());
            if (wrapperDdlBuilder != null) {
                String ddl = wrapperDdlBuilder.buildDropStatement(wrapper, quoteIdentifiers,qualifyNames);
                if (ddl != null) return ddl;
            }
        }
        return null;
    }    

    public String commentOn(LUWWrapper wrapper, boolean quoteIdentifiers, boolean qualifyNames) {
        if (wrapper != null) {        
            Database database = wrapper.getLUWDatabase();
            ExtendDdlBuilder wrapperDdlBuilder = DdlGenerationUtility.getWrapperDdlBuilder(database.getVendor(),database.getVersion());
            if (wrapperDdlBuilder != null) {
                String ddl = wrapperDdlBuilder.buildCommentOnStatement(wrapper,quoteIdentifiers,qualifyNames);
                if (ddl != null) return ddl;
            }
        }
        return null;
    }    

    //User Mapping
    public String createUserMapping(LUWUserMapping userMapping, boolean quoteIdentifiers, boolean qualifyNames) {
        if (userMapping != null) {
            LUWServer luwServer = userMapping.getServer();
            if (luwServer != null) {
                Database database = luwServer.getLUWDatabase();
                ExtendDdlBuilder userMappingDdlBuilder = DdlGenerationUtility.getUserMappingDdlBuilder(database.getVendor(),database.getVersion());
                if (userMappingDdlBuilder != null) {
                    String ddl = userMappingDdlBuilder.buildCreateStatement(userMapping,quoteIdentifiers,qualifyNames);
                    if (ddl != null) return ddl;
                }
            }
        }
        return null;
    }
   
    public String dropUserMapping(LUWUserMapping userMapping, boolean quoteIdentifiers, boolean qualifyNames) {
        if (userMapping != null) {
            LUWServer luwServer = userMapping.getServer();
            if (luwServer != null) {
                Database database = luwServer.getLUWDatabase();
                ExtendDdlBuilder userMappingDdlBuilder = DdlGenerationUtility.getUserMappingDdlBuilder(database.getVendor(),database.getVersion());
                if (userMappingDdlBuilder != null) {
                    String ddl = userMappingDdlBuilder.buildDropStatement(userMapping, quoteIdentifiers,qualifyNames);
                    if (ddl != null) return ddl;
                }
            }
        }
        return null;
    }    

    public String commentOn(LUWUserMapping userMapping, boolean quoteIdentifiers, boolean qualifyNames) {
        if (userMapping != null) {        
            LUWServer luwServer = userMapping.getServer();
            if (luwServer != null) {
                Database database = luwServer.getLUWDatabase();
                ExtendDdlBuilder userMappingDdlBuilder = DdlGenerationUtility.getUserMappingDdlBuilder(database.getVendor(),database.getVersion());
                if (userMappingDdlBuilder != null) {
                    String ddl = userMappingDdlBuilder.buildCommentOnStatement(userMapping,quoteIdentifiers,qualifyNames);
                    if (ddl != null) return ddl;
                }
            }
        }
        return null;
    }    
    
    /* //    xml schema drop done via stored procedure now 
     * 	Currently XMLSupport is limited to LUW. Once it is extended to i/zSeries,
	 *  this should be implemented by parent class. 
     * @param xmlSchema the XMLSchemaobject to be dropped
     * @param quoteIdentifiers 
     * @param qualifyNames
     * @return the DDL drop statement of the XMLSchema 
     
    public String dropXMLSchema(LUWCatalogXmlSchema xmlSchema, boolean quoteIdentifiers, boolean qualifyNames){
    	String dropStatement = null;
    	// construct the actual drop statement
    	dropStatement = DROP + SPACE + XSROBJECT + SPACE;

    	
    	String schemaName = xmlSchema.getName();
    	String dbSchemaName = xmlSchema.getSchema().getName();
        if(quoteIdentifiers) {
            schemaName = this.getDoubleQuotedString(schemaName);
            dbSchemaName = this.getDoubleQuotedString(dbSchemaName);
        }
        
        if(qualifyNames) {
            dropStatement += dbSchemaName + "." + schemaName; //$NON-NLS-1$
        }else{
        	dropStatement += schemaName;
        }
        return dropStatement;
    }
*/
    public String commentOn(LUWTableSpace tablespace, boolean quoteIdentifiers) {
        String comment = tablespace.getDescription();
        if(comment == null || comment.equals("")) { //$NON-NLS-1$
            return null;
        }

        String tablespaceName = tablespace.getName();

        if(quoteIdentifiers) {
            tablespaceName = this.getDoubleQuotedString(tablespaceName);
        }

        return "COMMENT ON TABLESPACE " + tablespaceName + " IS" + NEWLINE + this.getSingleQuotedString(comment); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public String createPartitionGroup(LUWPartitionGroup pg, boolean quoteIdentifiers) {
        String pgName = pg.getName();
        if (pgName.startsWith(IBM) ||
        		pgName.startsWith(SYS)) return null;

        if(quoteIdentifiers) {
            pgName = this.getDoubleQuotedString(pgName);
        }

        String statement = CREATE + SPACE + DATABASE + SPACE + PARTITION_GROUP + SPACE + pgName + " ON " ; //$NON-NLS-1$ //$NON-NLS-2$

        String partitions = ""; //$NON-NLS-1$
        boolean isAllPartition = true;
        Iterator it = pg.getPartitions().iterator();
        while(it.hasNext()) {
            LUWDatabasePartition partition = (LUWDatabasePartition) it.next();
            int partitionNum = partition.getNumber();
            if (partitionNum >=0 ) {
                partitions += partition.getNumber();
	            if(it.hasNext()) {
	            	partitions += ", ";                 //$NON-NLS-1$
	            }
	            isAllPartition = false;
            } else {
            	break;
            }
        }
        
        if (isAllPartition) {
        	statement += "ALL DBPARTITIONNUMS"; //$NON-NLS-1$
        } else {
        	statement += "DBPARTITIONNUMS " + LEFT_PARENTHESIS + partitions + RIGHT_PARENTHESIS;
        }
        return statement;
    }
    
    public String dropPartitionGroup(LUWPartitionGroup pg, boolean quoteIdentifiers) {
        String pgName = pg.getName();
        if (pgName.startsWith(IBM) ||
        		pgName.startsWith(SYS)) return null;

        if(quoteIdentifiers) {
            pgName = this.getDoubleQuotedString(pgName);
        }
        return DROP + SPACE + DATABASE + SPACE + PARTITION_GROUP + SPACE + pgName; //$NON-NLS-1$
    }

    public String commentOn(LUWPartitionGroup pg, boolean quoteIdentifiers) {
        String comment = pg.getDescription();
        if(comment == null || comment.equals("")) { //$NON-NLS-1$
            return null;
        }

        String pgName = pg.getName();

        if(quoteIdentifiers) {
            pgName = this.getDoubleQuotedString(pgName);
        }

        return COMMENT + SPACE + ON + SPACE + DATABASE + SPACE + PARTITION_GROUP + SPACE + pgName + " IS" + NEWLINE + this.getSingleQuotedString(comment); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    public String createStructuredUserDefinedType(StructuredUserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames) {
    	EList attrs = type.getAttributes();
    	//wsdbu00240410
//    	String dependStatement = " depends on ";
//    	boolean depends = false;

        EObject root = ContainmentServiceImpl.INSTANCE.getRootElement(type);
        Schema schema = type.getSchema();
        Database database = ModelHelper.getDatabase(schema);
        DatabaseDefinition def = null;
        if(root instanceof Database) {
            def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition((Database) root);
        }
        else {
            //TODO report error
            return null;
        }
    	
        String statement = CREATE + SPACE + TYPE + SPACE + getName(type, quoteIdentifiers, qualifyNames);
    	
        StructuredUserDefinedType superType = type.getSuper();
        if (superType != null) {
        	statement += SPACE + UNDER + SPACE + getName(superType,quoteIdentifiers,qualifyNames);
        	//wsdbu00240410
//        	depends = true;
//        	dependStatement += getName(superType,quoteIdentifiers,qualifyNames);

        }
        
        
        if (!attrs.isEmpty()) {
	        statement += SPACE + AS + LEFT_PARENTHESIS;
	        
	        Iterator it = attrs.iterator();
	        while(it.hasNext()) {
	        	LUWAttributeDefinition attr = (LUWAttributeDefinition) it.next();
	        	String typeName = getDataTypeString(attr,schema,quoteIdentifiers,qualifyNames);
	            statement += NEWLINE + TAB + attr.getName() + SPACE + typeName;
	            if (typeName.indexOf("CLOB") !=-1 ||typeName.indexOf("BLOB") != -1) {
	            	statement += getLOBLoggedClause(attr);
	    	        if (attr.isLOBCompacted()) statement += " COMPACT";
	            }

	            if(it.hasNext()) {
	            	statement += ", ";                 //$NON-NLS-1$
	            }
	            //wsdbu00240410
//	            if(attr.getDataType() instanceof StructuredUserDefinedType){
//	               if(!depends){
//	                   depends = true;
//	                   dependStatement += typeName;
//	                }else{
//	                    dependStatement += ","+ typeName;
//	                }
//	            }
	        }
	        statement += RIGHT_PARENTHESIS;
    	
        }
        
        if (!type.isInstantiable()) {
        	statement += NEWLINE + TAB + "NOT INSTANTIABLE"; //$NON-NLS-1$
        }

        if (type.isFinal()) {
        	statement += NEWLINE + TAB + "FINAL"; //$NON-NLS-1$
        }
        
        statement += NEWLINE + TAB + "MODE DB2SQL"; //$NON-NLS-1$
        //wsdbu00240410
//        if(depends)
//           statement += dependStatement;
        return statement;
    }
    
    public String commentOn(StructuredUserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames) {
        String comment = type.getDescription();
        if(comment == null || comment.length() == 0) {
            return null;
        }

        String name = getName(type, quoteIdentifiers, qualifyNames);

        return COMMENT + SPACE + ON + SPACE + TYPE + SPACE + name + SPACE + IS
        	+ NEWLINE + this.getSingleQuotedString(comment);
    }
    
    public String createModule(LUWModule module,boolean quoteIdentifiers, boolean qualifyNames ){
    	ILUWModuleDdlBuilder moduleBuilder = LUWDdlGenerator.getModuleDdlBuilder();
    	if (moduleBuilder != null) {
    		return moduleBuilder.buildCreateStatement(module, quoteIdentifiers, qualifyNames);
    	}
    	return null;
    }

    public String dropModule(LUWModule module,boolean quoteIdentifiers, boolean qualifyNames ){
    	ILUWModuleDdlBuilder moduleBuilder = LUWDdlGenerator.getModuleDdlBuilder();
    	if (moduleBuilder != null) {
    		return moduleBuilder.buildDropStatement(module, quoteIdentifiers, qualifyNames);
    	}
    	return null;
    }

    public String commentOn(LUWModule module,boolean quoteIdentifiers, boolean qualifyNames ){
    	ILUWModuleDdlBuilder moduleBuilder = LUWDdlGenerator.getModuleDdlBuilder();
    	if (moduleBuilder != null) {
    		return moduleBuilder.buildCommentStatement(module, quoteIdentifiers, qualifyNames);
    	}
    	return null;
    }

    public String createModuleCondition(LUWModuleCondition condtion, boolean quoteIdentifiers, boolean qualifyNames ){
    	return this.createModuleObject(condtion, quoteIdentifiers, qualifyNames);
    }

    public String dropModuleCondition(LUWModuleCondition condtion,boolean quoteIdentifiers, boolean qualifyNames ){
    	return this.dropModuleObject(condtion, quoteIdentifiers, qualifyNames);
    }

    public String createModuleGlobalVariable(LUWModuleGlobalVariable variable, boolean quoteIdentifiers, boolean qualifyNames ){
    	return this.createModuleObject(variable, quoteIdentifiers, qualifyNames);
    }

    public String dropModuleGlobalVariable(LUWModuleGlobalVariable condtion,boolean quoteIdentifiers, boolean qualifyNames ){
    	return this.dropModuleObject(condtion, quoteIdentifiers, qualifyNames);
    }

    public String createGlobalVariable(LUWGlobalVariable variable, boolean quoteIdentifiers, boolean qualifyNames ){
    	StringBuffer text = new StringBuffer();
    	text.append(CREATE).append(SPACE).append(VARIABLE).append(SPACE)
    		.append(getName(variable, quoteIdentifiers,qualifyNames)).append(SPACE)
    		.append(getDataTypeString(variable, variable.getSchema(), quoteIdentifiers,qualifyNames));
    	
    	String expression = variable.getDefaultValue();
    	if (expression != null && !"".equals(expression)) {
    		if (variable.isIsConstant()) {
    			text.append(NEWLINE).append(TAB).append(CONSTANT);
    		}else {
    			text.append(NEWLINE).append(TAB).append(DEFAULT);
    		}
    			
    		text.append(SPACE).append(LEFT_PARENTHESIS).append(expression).append(RIGHT_PARENTHESIS);
    		
    	}
    	return text.toString();
    }

    public String dropGlobalVariable(LUWGlobalVariable variable ,boolean quoteIdentifiers, boolean qualifyNames ){
    	StringBuffer text = new StringBuffer();
    	text.append(DROP).append(SPACE).append(VARIABLE).append(SPACE)
    		.append(getName(variable, quoteIdentifiers,qualifyNames));
    	return text.toString();
    }

    public String createArrayDataType(LUWArrayDataType type, boolean quoteIdentifiers, boolean qualifyNames ){
    	if (type instanceof LUWModuleArrayDataType) {
        	return this.createModuleObject((LUWModuleArrayDataType)type, quoteIdentifiers, qualifyNames);
    	}
    	
    	StringBuffer text = new StringBuffer();
    	text.append(CREATE).append(SPACE).append(TYPE).append(SPACE)
    		.append(getName(type, quoteIdentifiers,qualifyNames)).append(SPACE).append(AS).append(SPACE)
    		.append(getDataTypeString(type.getElementType(), type.getSchema(),quoteIdentifiers, qualifyNames)).append(SPACE)
    		.append(ARRAY).append(SPACE).append("[").append(type.getMaxCardinality()>0?type.getMaxCardinality():EMPTY_STRING).append("]");
    		
    	return text.toString();
    }

    public String createRowDataType(LUWRowDataType type, boolean quoteIdentifiers, boolean qualifyNames ){
    	if (type instanceof LUWModuleRowDataType) {
        	return this.createModuleObject((LUWModuleRowDataType)type, quoteIdentifiers, qualifyNames);
    	}
    	
    	StringBuffer text = new StringBuffer();
    	text.append(CREATE).append(SPACE).append(TYPE).append(SPACE)
    		.append(getName(type, quoteIdentifiers,qualifyNames)).append(SPACE).append(AS).append(SPACE).append(ROW);

    	EList fields = type.getFields();
        if (!fields.isEmpty()) {
	        text.append(LEFT_PARENTHESIS);
	        
	        Iterator it = fields.iterator();
	        while(it.hasNext()) {
	        	Field field = (Field) it.next();
	        	String typeName = getDataTypeString(field,type.getSchema(),quoteIdentifiers,qualifyNames);
	            text.append(NEWLINE).append(TAB).append(field.getName()).append(SPACE).append(typeName);

	            if(it.hasNext()) {
	            	text.append(", ");                 //$NON-NLS-1$
	            }
	        }
	        text.append(RIGHT_PARENTHESIS);
    	
        }
    	
    	return text.toString();
    }

    public String createCursorDataType(LUWCursorDataType type, boolean quoteIdentifiers, boolean qualifyNames ){
    	if (type instanceof LUWModuleCursorDataType) {
        	return this.createModuleObject((LUWModuleCursorDataType)type, quoteIdentifiers, qualifyNames);
    	}
    	
    	StringBuffer text = new StringBuffer();
    	text.append(CREATE).append(SPACE).append(TYPE).append(SPACE)
    		.append(getName(type, quoteIdentifiers,qualifyNames)).append(SPACE).append(AS).append(SPACE);

    	LUWRowDataType rowType = type.getRowType();
    	if (rowType != null) {
    		text.append(this.getName(rowType, quoteIdentifiers,qualifyNames)).append(SPACE);
    		
    	}
    	text.append(CURSOR);

    	return text.toString();
    }

    public String createModuleDistinctType(LUWModuleDistinctType type, boolean quoteIdentifiers, boolean qualifyNames ){
    	return this.createModuleObject(type, quoteIdentifiers, qualifyNames);
    }

    public String dropUserDefinedType(UserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames) {
    	if (type instanceof LUWModuleObject) {
        	return this.dropModuleObject((LUWModuleObject)type, quoteIdentifiers, qualifyNames);
    	}
    	return super.dropUserDefinedType(type, quoteIdentifiers, qualifyNames);
    }

    public String commentOn(LUWModuleCondition condtion,boolean quoteIdentifiers, boolean qualifyNames ){
    	return this.commentOnModuleObject(condtion, quoteIdentifiers, qualifyNames);
    }

    public String dropPlsqlPackage(PLSQLPackage plPackage,boolean quoteIdentifiers, boolean qualifyNames) {
    	ILUWModuleDdlBuilder moduleBuilder = LUWDdlGenerator.getModuleDdlBuilder();
    	if (moduleBuilder != null) {
    		return moduleBuilder.buildDropStatement(plPackage, quoteIdentifiers, qualifyNames);
    	}
    	return null;
    }

    public String createPlsqlPackage(PLSQLPackage plPackage,boolean quoteIdentifiers, boolean qualifyNames) {
    	ILUWModuleDdlBuilder moduleBuilder = LUWDdlGenerator.getModuleDdlBuilder();
    	if (moduleBuilder != null) {
    		return moduleBuilder.buildCreateStatement(plPackage, quoteIdentifiers, qualifyNames);
    	}
    	return null;
    }
    
    public String dropPlsqlPackageBody(PLSQLPackageBody packageBody,boolean quoteIdentifiers, boolean qualifyNames) {
    	ILUWModuleDdlBuilder moduleBuilder = LUWDdlGenerator.getModuleDdlBuilder();
    	if (moduleBuilder != null) {
    		return moduleBuilder.buildDropStatement(packageBody, quoteIdentifiers, qualifyNames);
    	}
    	return null;
    }

    public String createPlsqlPackageBody(PLSQLPackageBody packageBody,boolean quoteIdentifiers, boolean qualifyNames) {
    	ILUWModuleDdlBuilder moduleBuilder = LUWDdlGenerator.getModuleDdlBuilder();
    	if (moduleBuilder != null) {
    		return moduleBuilder.buildCreateStatement(packageBody, quoteIdentifiers, qualifyNames);
    	}
    	return null;
    }

    public String createProcedure(DB2Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {
    	if (procedure instanceof LUWModuleObject) {
        	return this.createModuleObject((LUWModuleObject)procedure, quoteIdentifiers, qualifyNames);
    	}
    	return super.createProcedure(procedure, quoteIdentifiers, qualifyNames);
    }
    
    public String dropProcedure(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {
    	if (procedure instanceof LUWModuleObject) {
        	return this.dropModuleObject((LUWModuleObject)procedure, quoteIdentifiers, qualifyNames);
    	}
    	return super.dropProcedure(procedure, quoteIdentifiers, qualifyNames);
    	
    }

    public String commentOn(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {
    	if (procedure instanceof LUWModuleObject) {
        	return this.commentOnModuleObject((LUWModuleObject)procedure, quoteIdentifiers, qualifyNames);
    	}
    	return super.commentOn(procedure, quoteIdentifiers, qualifyNames);
    }
    
    public String createUserDefinedFunction(DB2UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames) {
    	if (function instanceof LUWModuleObject) {
        	return this.createModuleObject((LUWModuleObject)function, quoteIdentifiers, qualifyNames);
    	}
    	return super.createUserDefinedFunction(function, quoteIdentifiers, qualifyNames);
    }
    
    public String dropFunction(UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames) {
    	if (function instanceof LUWModuleObject) {
        	return this.dropModuleObject((LUWModuleObject)function, quoteIdentifiers, qualifyNames);
    	}
    	return super.dropFunction(function, quoteIdentifiers, qualifyNames);
    }

    public String commentOn(UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames) {
    	if (function instanceof LUWModuleObject) {
        	return this.commentOnModuleObject((LUWModuleObject)function, quoteIdentifiers, qualifyNames);
    	}
    	return super.commentOn(function, quoteIdentifiers, qualifyNames);
    }

    public String commentOn(Role role, boolean quoteIdentifiers, boolean qualifyNames) {
    	return null;   //only LUW 9.5 and above version support it.
    }
    
    public String alterCommentOn(SQLObject sqlObject, boolean quoteIdentifiers, boolean qualifyNames) {
        String comment = sqlObject.getDescription();
        if(comment == null || comment.length() == 0) {
        	comment =SINGLE_QUOTE + SINGLE_QUOTE;
        }
        String commentType = getCommentType(sqlObject,quoteIdentifiers,qualifyNames);
        return commentType==null? "":COMMENT + SPACE + ON + SPACE + commentType + SPACE + IS + SPACE
                + this.getSingleQuotedString(comment);
    }
    
    public String commentOn(Sequence sequence, boolean quoteIdentifiers, boolean qualifyNames) {
    	return null;   //only LUW 9.7 and above version support it.
    }

    protected String getCommentType(SQLObject sqlObject,boolean quoteIdentifiers, boolean qualifyNames) {
        String comments = "";
        if (sqlObject instanceof LUWTable) {
        	comments += TABLE + SPACE + getName((Table)sqlObject,quoteIdentifiers,qualifyNames);
        } else if (sqlObject instanceof LUWView) {
        	comments += TABLE + SPACE + getName((Table)sqlObject,quoteIdentifiers,qualifyNames);
    	} else if (sqlObject instanceof LUWMaterializedQueryTable) {
    		comments += TABLE + SPACE + getName((Table)sqlObject,quoteIdentifiers,qualifyNames);
    	} else if (sqlObject instanceof LUWTableSpace) {
    		comments += TABLESPACE + SPACE + getName((LUWTableSpace)sqlObject,quoteIdentifiers);
    	} else if (sqlObject instanceof DB2Alias) {
    		comments += ALIAS + SPACE + getName((DB2Alias)sqlObject,quoteIdentifiers,qualifyNames);
    	} else if (sqlObject instanceof Procedure) {
    		comments += PROCEDURE + SPACE + getName((Procedure)sqlObject,quoteIdentifiers,qualifyNames);
    	} else if (sqlObject instanceof Function) {
    		comments += FUNCTION + SPACE + getName((Function)sqlObject,quoteIdentifiers,qualifyNames);
    	} else if (sqlObject instanceof Trigger) {
    		comments += TRIGGER + SPACE + getName((Trigger)sqlObject,quoteIdentifiers,qualifyNames);
    	} else if (sqlObject instanceof TableConstraint) {
    		comments += CONSTRAINT + SPACE + getTableConstraintName((TableConstraint)sqlObject,quoteIdentifiers,qualifyNames);
    	} else if (sqlObject instanceof Index) {
    		comments += INDEX + SPACE + getName((Index)sqlObject,quoteIdentifiers,qualifyNames);
    	} else if (sqlObject instanceof DistinctUserDefinedType) {
    		comments += DISTINCT + SPACE + TYPE + SPACE + getName((DistinctUserDefinedType)sqlObject,quoteIdentifiers,qualifyNames);
    	} else if (sqlObject instanceof StructuredUserDefinedType ) {
    		comments += TYPE + SPACE + getName((StructuredUserDefinedType )sqlObject,quoteIdentifiers,qualifyNames);
    	} else if (sqlObject instanceof Column ) {
    		comments += COLUMN + SPACE + getName((Column )sqlObject,quoteIdentifiers,qualifyNames);
    	} else if (sqlObject instanceof LUWPartitionGroup ) {
    		comments += DATABASE + SPACE + PARTITION_GROUP + SPACE + getName((LUWPartitionGroup )sqlObject,quoteIdentifiers);
    	} else if (sqlObject instanceof Schema ) {
    		comments += SCHEMA + SPACE +  getName((Schema )sqlObject,quoteIdentifiers,qualifyNames);
    	}
        return comments;
    }
    
    public String alterObject(LUWTable element) {
        String statement = createTable(element, true, true);
        if(statement != null) {
	        statement = "CALL SYSPROC.ALTOBJ(" + NEWLINE  //$NON-NLS-1$
	        	+ "\t'APPLY_CONTINUE_ON_ERROR'," + NEWLINE //$NON-NLS-1$
				+ "\t'" + statement + "'," + NEWLINE //$NON-NLS-1$ //$NON-NLS-2$
				+ "\t-1, ?)"; //$NON-NLS-1$
        }
        return statement;
	}
    
    //@bd00062627gs
    public String addUniqueConstraint(UniqueConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames) {
    	String statement = super.addUniqueConstraint(constraint, quoteIdentifiers, qualifyNames);

    	if (constraint.eContainer() instanceof LUWNickname) {
    		if (statement == null) return null; //report error
    		//statement += this.getEnforcedOption(constraint);
    		statement += SPACE + NOT + SPACE + ENFORCED;// + SPACE + ENABLE + SPACE + QUERY + SPACE + OPTIMIZATION;
    	}

    	return statement;
    }
    //@ed00062627gs

    //@bd00062627gs
    public String addCheckConstraint(CheckConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames) {
    	String statement = super.addCheckConstraint(constraint,quoteIdentifiers,qualifyNames);
    	if (constraint.eContainer() instanceof LUWNickname) {
    		if (statement == null) return null; //report error
        	if (constraint.isEnforced()) //if NOT ENFORCED was not already added, add it
    		   statement += SPACE + NOT + SPACE + ENFORCED;// + SPACE + ENABLE + SPACE + QUERY + SPACE + OPTIMIZATION;
    	}    	
    	return statement;
    } 
    //@ed00062627gs

    //@bd00062627gs
    public String addForeignKey(ForeignKey foreignKey, boolean quoteIdentifiers, boolean qualifyNames) {
        UniqueConstraint uniqueConstraint = foreignKey.getUniqueConstraint();
        Index index = foreignKey.getUniqueIndex();
        Table parentTable = null;
        String parentKey = null;
        if(uniqueConstraint != null) {
            parentTable = uniqueConstraint.getBaseTable();
            parentKey = this.getKeyColumns(uniqueConstraint,quoteIdentifiers);
            if (parentKey == null) {
            	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
    					LUWDdlMessages.FE_REFERENCED_PARENT_KEY_DOES_NOT_EXIST, new Object[] { foreignKey.getName()}));
                return null;
            }
        }
        else if(index != null) {
        	parentTable = index.getTable();
            parentKey = this.getParentKeyColumns(index, quoteIdentifiers);
            if (parentKey == null) {
            	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
    					LUWDdlMessages.FE_REFERENCED_PARENT_KEY_DOES_NOT_EXIST, new Object[] { foreignKey.getName()}));
                return null;
            }
        }
        if(parentTable == null) {
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					LUWDdlMessages.FE_REFERENCED_PARENT_TABLE_DOES_NOT_EXIST, new Object[] { foreignKey.getName()}));
            return null;
        }

        String statement = ALTER + SPACE + this.getOwnerType(foreignKey) + SPACE + getName(foreignKey.getBaseTable(), quoteIdentifiers, qualifyNames)
        	+ SPACE + ADD + SPACE + CONSTRAINT + SPACE + getName(foreignKey, quoteIdentifiers) + SPACE + FOREIGN_KEY 
        	+ this.getKeyColumns(foreignKey,quoteIdentifiers);
        statement += NEWLINE + TAB + REFERENCES + SPACE + getName(parentTable, quoteIdentifiers, qualifyNames) 	+ parentKey;
		
        if (foreignKey.eContainer() instanceof LUWNickname) {
        	statement += SPACE + NOT + SPACE + ENFORCED;// + SPACE + ENABLE + SPACE + QUERY + SPACE + OPTIMIZATION;
        } else {
	        ReferentialActionType action = foreignKey.getOnDelete();
	        if(action != ReferentialActionType.NO_ACTION_LITERAL) {
	            statement += NEWLINE + TAB + ON + SPACE + DELETE + SPACE;            
	        }
	        statement += getReferentialAction(action);
	        
	        action = foreignKey.getOnUpdate();
	        if(action != ReferentialActionType.NO_ACTION_LITERAL) {
	            statement += NEWLINE + TAB + ON + SPACE + UPDATE + SPACE;            
	        }
	        statement += getReferentialAction(action);
	
	        if(foreignKey.isDeferrable()) {
	            statement += NEWLINE + TAB + getDeferrableClause(foreignKey);
	        }
	        
	        statement += this.getEnforcedOption(foreignKey);
        }

        return statement;
    }
    //@ed00062627gs

    public String[] grantOn(LUWTableSpace tablespace, boolean quoteIdentifiers) {
        String tablespaceName = tablespace.getName();
        if(quoteIdentifiers) {
            tablespaceName = this.getDoubleQuotedString(tablespaceName);
        }
    	return getGrantString(tablespace,quoteIdentifiers, false);
    }

    public String[] revokeFrom(LUWTableSpace tablespace, boolean quoteIdentifiers) {
        String tablespaceName = tablespace.getName();
        if(quoteIdentifiers) {
            tablespaceName = this.getDoubleQuotedString(tablespaceName);
        }
    	return getRevokeString(tablespace,quoteIdentifiers, false);
    }
    
    public String alterTableAlterProperties(Table table, int propertyType, boolean quoteIdentifiers, boolean qualifyNames) {
    	if(!(table instanceof LUWTable)) return null;

        String proString = "";
        if ((propertyType & LUWDeltaDdlGenerator.DATA_CAPTURE) == LUWDeltaDdlGenerator.DATA_CAPTURE) {
        	proString += this.getDataCapture((LUWTable)table);
        }
        
        if ((propertyType & LUWDeltaDdlGenerator.VALUE_COMPRESSION) == LUWDeltaDdlGenerator.VALUE_COMPRESSION) {
        	proString += this.getAlterTableAlterValueCompressionString((LUWTable)table);
        }

        if ((propertyType & LUWDeltaDdlGenerator.VOLATILE) == LUWDeltaDdlGenerator.VOLATILE) {
        	proString += this.getAlterTableAlterVolatileString((LUWTable)table);
        }

        if ((propertyType & LUWDeltaDdlGenerator.COMPRESSION) == LUWDeltaDdlGenerator.COMPRESSION) {
        	proString += this.getAlterTableAlterRowCompressionString((LUWTable)table);
        }
        
        if (!proString.equals("")) { //$NON-NLS-1$
            String statement = ALTER + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames) 
            + proString;
            return statement;
        }
        return null;
    }

    public String alterTableAlterPartitionKey(LUWStorageTable table,  boolean quoteIdentifiers, boolean qualifyNames) {
    	String statement = ALTER + SPACE + TABLE + SPACE + getName((Table)table, quoteIdentifiers, qualifyNames);
    	LUWPartitionKey partitionKey = table.getPartitionKey();
    	if ((partitionKey == null) || partitionKey.getColumns().isEmpty()) {   //drop
    		statement += " DROP PARTITIONING KEY"; //$NON-NLS-1$
    	} else {					// modify
    		statement += SPACE + ADD + SPACE + getPartitionKeyDefinition(partitionKey,quoteIdentifiers);
    	}
    	return statement;
    }

    
	public String getGrantPrivilegeStatement(Privilege privilege,boolean quoteIdentifiers,boolean qualifyNames) {
		if (this.isDDLSupressable(privilege)) {
			if (this.getEngineeringCallBack() != null) {
				String message = getSystemGrantMessage(GRANT,privilege,quoteIdentifiers,qualifyNames);
				this.getEngineeringCallBack().writeMessage(message);
			}
			return null;
		}
		AuthorizationIdentifier authId = privilege.getGrantee();
		String granteeType = "";
		if (authId.getName().equals("PUBLIC")) granteeType="";
		else if (authId instanceof User) granteeType = USER;
		else if ((authId instanceof Group) && (!authId.getName().equals("PUBLIC"))) granteeType = GROUP;
		else if (authId instanceof Role) granteeType = ROLE;
		String prep = ON;
		if (privilege.getObject() instanceof LUWTableSpace) prep = OF;
		String ret = GRANT + SPACE + privilege.getAction() + SPACE + prep + SPACE 
				+ getPrivilegedObjectTypeString(privilege) + SPACE
				+ getPrivilegedObjectName(privilege, quoteIdentifiers, qualifyNames) 
				+ SPACE + TO + SPACE + granteeType + SPACE + this.getName(privilege.getGrantee(), quoteIdentifiers);
		if (privilege.isGrantable()) {
			ret += SPACE + WITH + SPACE + GRANT + SPACE + OPTION;
		}
		return ret;
	}

	public String getRevokePrivilegeStatement(Privilege privilege,boolean quoteIdentifiers,boolean qualifyNames) {
		if (this.isDDLSupressable(privilege)) {
			if (this.getEngineeringCallBack() != null) {
				String message = getSystemGrantMessage(REVOKE,privilege,quoteIdentifiers,qualifyNames);
				this.getEngineeringCallBack().writeMessage(message);
			}
			return null;
		}
		AuthorizationIdentifier authId = privilege.getGrantee();
		String granteeType = "";
		if (authId instanceof User) granteeType = USER;
		else if ((authId instanceof Group) && (!authId.getName().equals("PUBLIC"))) granteeType = GROUP;
		else if (authId instanceof Role) granteeType = ROLE;
		String prep = ON;
		if (privilege.getObject() instanceof LUWTableSpace) prep = OF;
		String ret = REVOKE + SPACE + privilege.getAction() + SPACE + prep + SPACE 
		+ getPrivilegedObjectTypeString(privilege) + SPACE
		+ getPrivilegedObjectName(privilege, quoteIdentifiers, qualifyNames) 
		+ SPACE + FROM + SPACE + granteeType + SPACE + this.getName(privilege.getGrantee(), quoteIdentifiers);
		
		if (privilege.getObject() instanceof Routine) {
			ret += SPACE + RESTRICT;
		}
		return ret;
	}
	
    public String[] updateStatistics(Table table,boolean quoteIdentifiers, boolean qualifyNames){
    	if (!(table instanceof IDatabaseObject)) return null;
    	Collection stats = ((IDatabaseObject)table).getStatistics();
    	if (stats.isEmpty()) return null;
    	String whereClause = "WHERE TABNAME ='" + table.getName() + "'";
    	if (qualifyNames) {
    		whereClause += " AND TABSCHEMA='" + table.getSchema().getName() + "'";
    	}

//        return statVector.toArray(new String [statVector.size()]);
    	Collection statements = this.getAssignmentClause(stats,"SYSSTAT.TABLES",whereClause);
        return (String[] )statements.toArray(new String[statements.size()]);
    }

    public String[] updateStatistics(Column column,boolean quoteIdentifiers, boolean qualifyNames){
    	if (!(column instanceof IDatabaseObject)) return null;
    	Collection stats = ((IDatabaseObject)column).getStatistics();
    	if (stats.isEmpty()) return null;
    	String whereClause = "WHERE TABNAME ='" + column.getTable().getName() + "'" + " AND COLNAME = '" + column.getName() + "'";
    	if (qualifyNames) {
    		whereClause += " AND TABSCHEMA='" + column.getTable().getSchema().getName() + "'";
    	}
    	
    	
    	Collection statements = this.getAssignmentClause(stats,"SYSSTAT.COLUMNS",whereClause);
    	statements.addAll(this.getAssignmentClause(stats,"SYSSTAT.COLDIST",whereClause));
        return (String[] )statements.toArray(new String[statements.size()]);
//    	return this.getAssignmentClause(stats,"SYSSTAT.COLUMNS",whereClause);
    }

    public String[] updateStatistics(Index index,boolean quoteIdentifiers, boolean qualifyNames){
    	if (!(index instanceof IDatabaseObject)) return null;
    	Collection stats = ((IDatabaseObject)index).getStatistics();
    	if (stats.isEmpty()) return null;
    	String whereClause = "WHERE TABNAME ='" + index.getTable().getName() + "'" + " AND INDNAME = '" + index.getName() + "'";
    	if (qualifyNames) {
    		whereClause += " AND TABSCHEMA='" + index.getTable().getSchema().getName() + "'";
    	}
//    	return this.getAssignmentClause(stats,"SYSSTAT.INDEXES",whereClause);
    	Collection statements = this.getAssignmentClause(stats,"SYSSTAT.INDEXES",whereClause);
        return (String[] )statements.toArray(new String[statements.size()]);
    }

    public String[] updateStatistics(Routine routine,boolean quoteIdentifiers, boolean qualifyNames){
    	if (!(routine instanceof IDatabaseObject)) return null;
    	Collection stats = ((IDatabaseObject)routine).getStatistics();
    	if (stats.isEmpty()) return null;
    	String whereClause = "WHERE ROUTINENAME ='" + routine.getName() + "'" ;
    	if (qualifyNames) {
    		whereClause += " AND ROUTINESCHEMA='" + routine.getSchema().getName() + "'";
    	}
    	Collection statements = this.getAssignmentClause(stats,"SYSSTAT.ROUTINES",whereClause);
        return (String[] )statements.toArray(new String[statements.size()]);
//    	return this.getAssignmentClause(stats,"SYSSTAT.ROUTINES",whereClause);
    }
    
    public String setIntegrityForTableBeforeAlter(Column column,  boolean quoteIdentifiers, boolean qualifyNames) {
    	Table table = column.getTable();
    	if(table instanceof BaseTable) {
	        String statement = SET + SPACE + INTEGRITY + SPACE + FOR + SPACE 
	        	+ getName(table, quoteIdentifiers, qualifyNames)
	    		+ " OFF"; //$NON-NLS-1$
	        return statement;
    	}
    	return null;    	
    }

    public String setIntegrityForTableAfterAlter(Column column,  boolean quoteIdentifiers, boolean qualifyNames) {
    	Table table = column.getTable();
    	if(table instanceof BaseTable) {
	        String statement = SET + SPACE + INTEGRITY + SPACE + FOR + SPACE
	        	+ getName(table, quoteIdentifiers, qualifyNames)
	    		+ " IMMEDIATE CHECKED FORCE GENERATED"; //$NON-NLS-1$
	        return statement;
    	}
    	return null;
    }

    private Collection getAssignmentClause (Collection stats, String syscatalog, String whereClause){
    	Vector statVector = new Vector();
    	String statStr = "";
    	Iterator iter = stats.iterator();
    	while (iter.hasNext()) {
    		CatalogStatistics stat = (CatalogStatistics) iter.next();
    		if (!stat.getSyscatalog().equals(syscatalog) ) continue;
    		if (stat.getType() == CatalogStatistics.COLLECTION_TYPE) {
    			String statClause = this.getStatWhereClause(stat.getCollection(), syscatalog, whereClause);
    			Collection statstrs = this.getAssignmentClause(stat.getCollection(), syscatalog, statClause);
   				statVector.addAll(statstrs);
    		} else {
	    		if (statStr.length() > 0) {
	    			statStr +=  "," + NEWLINE+ TAB + TAB;
	    		}
	    		statStr += stat.getId() + "=" + stat.toString();
    		}
    	}
    	
    	if (!statStr.equals("")) {
    		statStr = UPDATE + SPACE + syscatalog + SPACE 
    		+ NEWLINE + TAB + SET + SPACE + statStr
    		+ NEWLINE + TAB + whereClause;
    	}
    	
    	statVector.add(statStr);
    	return statVector;
    }

    private String getStatWhereClause(Collection stats,String syscatalog,String whereClause) {
    	String str = whereClause;
    	if (syscatalog.equals("SYSSTAT.COLDIST")) {
        	Iterator iter = stats.iterator();
        	while (iter.hasNext()) {
        		CatalogStatistics stat = (CatalogStatistics) iter.next();
        		String id = stat.getId();
        		if (id.equals("TYPE")) {
        			str += NEWLINE + TAB +"AND " + stat.getId() + "='" + stat.toString() + "'";
        		} else if (id.equals("SEQNO")) {
        			str += NEWLINE + TAB +"AND " + stat.getId() + "=" + stat.toString();
        		}
        	}
        	
    		
    	}
    	return str;
    }
    protected String getIndexIncludedColumns(DB2Index index, boolean quoteIdentifiers) {
        String columns;
        Iterator it = index.getIncludedMembers().iterator();
        if(it.hasNext()) {
            IndexMember m = (IndexMember) it.next();
            String columnName = m.getColumn().getName();
            if (quoteIdentifiers) {
                columnName = this.getDoubleQuotedString(columnName);
            }
            columns = columnName;
        }
        else {
            return null;
        }
        
        while(it.hasNext()) {
            IndexMember m = (IndexMember) it.next();
            String columnName = m.getColumn().getName();
            if(quoteIdentifiers) {
                columnName = this.getDoubleQuotedString(columnName);
            }
            columns += ", "; //$NON-NLS-1$
            columns += columnName;
        }
        return columns;
    }

    protected String getAlterTableAddColumnColumnString(Column column, boolean quoteIdentifiers, boolean qualifyNames) {
    	return getColumnString(column,quoteIdentifiers,qualifyNames,true);
    }
    	 
    protected String getColumnString(Column column, boolean quoteIdentifiers, boolean qualifyNames) {
    	return getColumnString(column,quoteIdentifiers,qualifyNames,false);
    }
    	 
    protected String getColumnString(Column column, boolean quoteIdentifiers, boolean qualifyNames, boolean isAddColumn) {
    	LUWColumn db2Column = (LUWColumn) column;
        //String columnDefinition =  super.getColumnString(db2Column, quoteIdentifiers,qualifyNames);

        String columnName = column.getName();
        if(quoteIdentifiers) {
            columnName = this.getDoubleQuotedString(columnName);
        }
        
        String columnString = columnName + SPACE + getDataTypeString(column, column.getTable().getSchema(),qualifyNames);
        String defaultValue = this.getDefaultValue(column);
        DB2IdentitySpecifier spec = (DB2IdentitySpecifier)db2Column.getIdentitySpecifier();
        if(!column.isNullable()) {
            columnString = columnString + SPACE + NOT + SPACE + NULL;
        }

        // String typeName = getDataTypeString(column, column.getTable().getSchema(),qualifyNames);
        // fix for issue wsdbu00985096
        String typeName = getDataTypeString(column,column.getTable().getSchema(),quoteIdentifiers,qualifyNames);

        if(spec == null && 
        		!typeName.equalsIgnoreCase("XML")) { //$NON-NLS-1$
            if(defaultValue != null && !defaultValue.equals(EMPTY_STRING)) 
            	columnString = columnString + SPACE + DEFAULT + SPACE + defaultValue;
            else if (isAddColumn && !column.isNullable())
            	columnString = columnString + SPACE + DEFAULT;
        }
        
        if (typeName.indexOf("CLOB") !=-1 ||typeName.indexOf("BLOB") != -1) {
        	columnString += getLOBLoggedClause(db2Column);
	        if (db2Column.isLobCompacted()) columnString += " COMPACT";
        }
        ValueExpression v = db2Column.getGenerateExpression();
        if(spec != null) {
            GenerateType generateType = db2Column.getGenerationType();
            if(generateType == GenerateType.ALWAYS_LITERAL) {
            	columnString += " GENERATED ALWAYS AS IDENTITY "; //$NON-NLS-1$
            }
            else {
            	columnString += " GENERATED BY DEFAULT AS IDENTITY ";                 //$NON-NLS-1$
            }
            if (!spec.isSystemGenerated()){
            	columnString +=  LEFT_PARENTHESIS + this.getIdentityString(spec) + RIGHT_PARENTHESIS;
            }
        }
        else if(v != null){
        	if (v.getSQL() == null || v.getSQL().trim().length() == 0) {
        		//report error: must have a generation expression
            	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
    					LUWDdlMessages.FE_GENERATED_COLUMN_HAS_NO_EXPRESSION, new Object[] { getName(column, false, true)}));
        	} else {
        		String generationExpression = v.getSQL().trim();
        		if ( generationExpression.startsWith("(") && generationExpression.endsWith(")")){
        		} else {
        			generationExpression = "(" + generationExpression + ")";
        		}

        		columnString += " GENERATED ALWAYS AS (" + generationExpression + ")";             //$NON-NLS-1$	       	
        	}
        }
        else if (typeName.equalsIgnoreCase("TIMESTAMP") && db2Column.isRowChangeTimestamp()) {
            GenerateType generateType = db2Column.getGenerationType();
            if(generateType == GenerateType.ALWAYS_LITERAL) {
            	columnString += " GENERATED ALWAYS FOR EACH ROW ON UPDATE AS ROW CHANGE TIMESTAMP "; //$NON-NLS-1$
            }
            else {
            	columnString += " GENERATED BY DEFAULT FOR EACH ROW ON UPDATE AS ROW CHANGE TIMESTAMP ";                 //$NON-NLS-1$
            }
        }

        return columnString;
    }

    protected String getLOBLoggedClause(LUWColumn db2Column) {
        if (!db2Column.isLobLogged()) return NOT_LOGGED;
        return getLOBLoggedClause(db2Column.getDataType());
    }

    protected String getLOBLoggedClause(LUWAttributeDefinition db2Attr) {
        if (!db2Attr.isLOBLogged()) return NOT_LOGGED;
        return getLOBLoggedClause(db2Attr.getDataType());
    }

    protected String getLOBLoggedClause(DataType dt) {
    	int length = 0;
    	if (SQLDataTypesPackage.eINSTANCE.getCharacterStringDataType().isSuperTypeOf(dt.eClass())) {
    		CharacterStringDataType cdt = (CharacterStringDataType)dt;
    		length = cdt.getLength();
    		if (cdt.getPrimitiveType() == PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT_LITERAL)
    			length = length * 2;
    	}
    	else if (SQLDataTypesPackage.eINSTANCE.getBinaryStringDataType().isSuperTypeOf(dt.eClass())) 
    		length = ((BinaryStringDataType)dt).getLength();
    	if (length > GB) return NOT_LOGGED;
    	return EMPTY_STRING;
    }

	private String getTablespaceString(LUWStorageTable table, boolean quoteIdentifiers) {
	    LUWTableSpace tableSpace = table.getRegularDataTableSpace();
	    String tablespaceClause = "";
        if(tableSpace != null) {
            String regularTableSpaceName = tableSpace.getName();
            if(quoteIdentifiers) {
                regularTableSpaceName = this.getDoubleQuotedString(regularTableSpaceName);
            }
            tablespaceClause = NEWLINE + "\tIN " + regularTableSpaceName; //$NON-NLS-1$
        }

        tableSpace = table.getIndexDataTableSpace();
        if(tableSpace != null) {
            String indexTableSpaceName = tableSpace.getName();
            if(quoteIdentifiers) {
                indexTableSpaceName = this.getDoubleQuotedString(indexTableSpaceName);
            }
            tablespaceClause += " INDEX IN " + indexTableSpaceName; //$NON-NLS-1$
	    }

        tableSpace = table.getLOBDataTableSpace();
        if(tableSpace != null) {
            String lobTableSpaceName = tableSpace.getName();
            if(quoteIdentifiers) {
                lobTableSpaceName = this.getDoubleQuotedString(lobTableSpaceName);
            }
            tablespaceClause += " LONG IN " + lobTableSpaceName; //$NON-NLS-1$
	    }

        return tablespaceClause;
    }

	private boolean hasContainers( LUWTableSpace tablespace ) {
		return !tablespace.getContainers().isEmpty();
	}

    private String getContainers(LUWTableSpace tablespace) {
        EList containers = tablespace.getContainers();
        if (containers.isEmpty()) return null;
        
        // Map distinct partition sets to associated containers
        Map<Set<Integer>, List<LUWDatabaseContainer>> contMap = new HashMap<Set<Integer>, List<LUWDatabaseContainer>>();
        List<LUWDatabaseContainer> nopartn = null;
        boolean partnsPresent = false;

        for ( Object cobj : tablespace.getContainers() ) {
        	LUWDatabaseContainer cont = (LUWDatabaseContainer)cobj;
        	List partns = cont.getPartitions();

        	if ( partns.isEmpty() ) {
        		if (nopartn == null) {
        			nopartn = new ArrayList<LUWDatabaseContainer>();
        			contMap.put( null, nopartn );
        		}
        		
        		nopartn.add( cont );
        	}
        	else
        	{
         		Set<Integer> cpartns = new HashSet<Integer>();

        		for ( Object pobj : partns ) {
        			LUWDatabasePartition partn = (LUWDatabasePartition)pobj;
        			
        			cpartns.add( new Integer(partn.getNumber()) );
        		}
        		
        		List<LUWDatabaseContainer> clist = null;
        		
        		for ( Set<Integer> s : contMap.keySet() ) {
        			if ( s.size() == cpartns.size() && s.containsAll( cpartns )) {
        				clist = contMap.get( s );
        				break;
        			}
        		}
        		
        		if (clist == null) {
        			clist = new ArrayList<LUWDatabaseContainer>();
        			contMap.put( cpartns, clist );
        		}
        		
    			clist.add( cont );
        	}
        }
        
        // TODO error checking, if we care to do it
        // all containers must specify partitions, or none can
        // partitions for each container group must be unique
        StringBuilder txt = new StringBuilder();
        boolean database_managed =  tablespace.getManagementType() == ManagementType.DATABASE_MANAGED_LITERAL;
       
        for ( Map.Entry<Set<Integer>, List<LUWDatabaseContainer>> entry : contMap.entrySet() ) {
        	txt.append( NEWLINE + TAB + "USING ("); //$NON-NLS-1$

        	boolean first = true;
        	
        	for ( LUWDatabaseContainer container : entry.getValue() ) {
        		if (!first) {
        			txt.append( ", " ); //$NON-NLS-1$
        		}
        		else {
        			first = false;
        		}

                LUWContainerType type = container.getContainerType();
                if (database_managed) {
        	        if(type == LUWContainerType.DEVICE_LITERAL) {
        	            txt.append( "DEVICE ") ; //$NON-NLS-1$
        	        }
        	        else if(type == LUWContainerType.FILE_LITERAL) {
        	            txt.append( "FILE " ); //$NON-NLS-1$
        	        }
                }

                txt.append(  this.getSingleQuotedString(container.getName()) );

                if (database_managed) {
                	if ( container.getSizeInPages() > 0 ) {
                		txt.append( SPACE + container.getSizeInPages() );
                	}
                	else {
                		txt.append( SPACE + container.getSize() );
                		txt.append( SPACE + container.getSizeUnits().getName() );
                	}
                }        		
        	}
        	
        	txt.append( ")" ); //$NON-NLS-2$
            
        	Set<Integer> partnums = entry.getKey();
        	
        	if ( partnums != null && !partnums.isEmpty() ) {
        		txt.append( " ON DBPARTITIONNUM (" ); //$NON-NLS-1$
        		boolean first2 = true;
        		
        		for ( Integer partnum : partnums ) {
        			if (!first2) {
        				txt.append( ", " ); //$NON-NLS-1$
        			}
        			else {
        				first2 = false;
        			}
        			
        			txt.append( partnum );
        		}
        		
        		txt.append( ")" ); //$NON-NLS-1$
        	}
        }
 
        return txt.toString();
    }
 
    protected String getOwnerType(TableConstraint constraint){
    	BaseTable table = constraint.getBaseTable();
    	if (table instanceof LUWNickname) {
    		return NICKNAME;
    	} else {
    		return TABLE;
    	}
    }
    
	protected String getPartitionKey(LUWStorageTable table, boolean quoteIdentifiers) {
	    LUWPartitionKey partitionKey = table.getPartitionKey();
	    if (partitionKey == null) return ""; //$NON-NLS-1$
	    
	    if (partitionKey.getColumns().size() <1) return "";

	    String statement = NEWLINE + TAB;
	    statement += getPartitionKeyDefinition(partitionKey, quoteIdentifiers);
	    
        return statement;
    }

	private String getPartitionKeyDefinition(LUWPartitionKey partitionKey, boolean quoteIdentifiers) {
		String definition = PARTITION_KEY + LEFT_PARENTHESIS;
        Iterator it = partitionKey.getColumns().iterator();
        while(it.hasNext()) {
            String columnName = ((Column)it.next()).getName();
            if(quoteIdentifiers) {
                columnName = this.getDoubleQuotedString(columnName);
            }
            definition += columnName;
            if(it.hasNext()) {
                definition += ", ";                 //$NON-NLS-1$
            }
        }
	    definition += RIGHT_PARENTHESIS;
	    if (partitionKey.getPartitionMethod() == PartitionMethod.get(PartitionMethod.HASHING))
	    	definition += SPACE + USE_HASH;
		return definition;
	}
 
    protected String getDataTypeString(TypedElement typedElement, Schema schema,boolean qualifyNames) {
    	String type = null;
    	if (this.generator != null && EngineeringOptionID.useDomain(this.generator.getSelectedOptions())) { //@d00058820gs
    		type = this.getDomainTypeString(typedElement,schema,qualifyNames);
    	}
    	
    	if (type == null) {
    		type = super.getDataTypeString(typedElement,schema,qualifyNames);
    	}
    	
    	return type;
    }
    
    protected String getDataTypeString(TypedElement typedElement, Schema schema, boolean quoteIdentifiers,boolean qualifyNames) {
    	String type = null;
    	if (this.generator != null && EngineeringOptionID.useDomain(this.generator.getSelectedOptions())) { //@d00058820gs
    		type = this.getDomainTypeString(typedElement,schema,qualifyNames);
    	}
    	
    	if (type == null) {
    		type = super.getDataTypeString(typedElement,schema,quoteIdentifiers,qualifyNames);
    	}
    	
    	return type;
    }
    
    protected String getName(LUWWrapper wrapper, boolean quoteIdentifiers) {
        String name = wrapper.getName();

        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
        }
    
        return name;
    }
    
    protected String getName(LUWServer server, boolean quoteIdentifiers) {
        String name = server.getName();

        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
        }
    
        return name;
    }

    protected String getName(LUWUserMapping userMapping, boolean quoteIdentifiers) {
        String name = userMapping.getName();

        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
        }
        return name;
    }

    protected String getName(LUWTableSpace tablespace, boolean quoteIdentifiers) {
        String name = tablespace.getName();

        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
        }
        return name;
    }

    protected String getName(LUWDatabasePackage pkg, boolean quoteIdentifiers,boolean qualifyNames) {
        String name = pkg.getName();
        String schemaName = pkg.getSchema().getName();

        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
            schemaName  = this.getDoubleQuotedString(schemaName);
        }
        if(qualifyNames) {
            name = schemaName + DOT + name;
        }

        return name;
    }

    protected String getName(LUWGlobalVariable variable, boolean quoteIdentifiers,boolean qualifyNames) {
        String name = variable.getName();
        String schemaName = variable.getSchema().getName();

        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
            schemaName  = this.getDoubleQuotedString(schemaName);
        }
        if(qualifyNames) {
            name = schemaName + DOT + name;
        }

        return name;
    }

    protected String getName(LUWModule module, boolean quoteIdentifiers,boolean qualifyNames) {
        String name = module.getName();
        String schemaName = module.getOwningSchema().getName();

        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
            schemaName  = this.getDoubleQuotedString(schemaName);
        }
        if(qualifyNames) {
            name = schemaName + DOT + name;
        }

        return name;
    }

    protected String getIdentityString(DB2IdentitySpecifier identitySpecifier) {
    	StringBuffer sb = new StringBuffer();
    	if (identitySpecifier.getStartValue() != null)
    		sb.append(SPACE + START_WITH + identitySpecifier.getStartValue());
    	if (identitySpecifier.getIncrement() != null)
    		sb.append(SPACE + INCREMENT_BY + identitySpecifier.getIncrement());
    	if (identitySpecifier.getMinimum() != null)
    		sb.append(SPACE + MINVALUE + identitySpecifier.getMinimum());
    	if (identitySpecifier.getMaximum() != null)
    		sb.append(SPACE + MAXVALUE + identitySpecifier.getMaximum());
    	if(identitySpecifier.isCycleOption()) {
    		sb.append(SPACE + CYCLE);
    	}
    	else {
    		sb.append(SPACE + NO + SPACE + CYCLE);
    	}
    	if (identitySpecifier.getCache() > 1) {
    		sb.append(SPACE + CACHE + SPACE +  identitySpecifier.getCache());
    	} else {
    		sb.append(SPACE + NO + SPACE + CACHE);
    	}
        return sb.toString();
    }
 
    protected String getIdentityAlterationString(DB2IdentitySpecifier identitySpecifier) {
    	StringBuffer sb = new StringBuffer();
    	if (identitySpecifier.getStartValue() != null)
    		sb.append(SPACE + RESTART_WITH + identitySpecifier.getStartValue());
    	if (identitySpecifier.getIncrement() != null)
    		sb.append(SPACE + SET_INCREMENT_BY + identitySpecifier.getIncrement());
    	if (identitySpecifier.getMinimum() != null)
    		sb.append(SPACE + SET_MINVALUE + identitySpecifier.getMinimum());
    	if (identitySpecifier.getMaximum() != null)
    		sb.append(SPACE + SET_MAXVALUE + identitySpecifier.getMaximum());
    	if(identitySpecifier.isCycleOption()) {
    		sb.append(SPACE + SET + SPACE + CYCLE);
    	}
    	else {
    		sb.append(SPACE + SET + SPACE + NO + SPACE + CYCLE);
    	}
    	if (identitySpecifier.getCache() > 1) {
    		sb.append(SPACE + SET + SPACE + CACHE + SPACE +  identitySpecifier.getCache());
    	} else {
    		sb.append(SPACE + SET + SPACE + NO + SPACE + CACHE);
    	}
        return sb.toString();
    }

    
    protected String getTableDimension(LUWTable table, boolean quotedIdentifier) {
        String text = ""; 
        for (Iterator iter = table.getIndex().iterator(); iter.hasNext();){
        	DB2Index index = (DB2Index) iter.next();
        	if (index.getIndexType() == DB2IndexType.DIMENSION_LITERAL){
        		String dimensionPart = getParentKeyColumns(index,quotedIdentifier);
        		if (!text.equals("")) text += COMMA + SPACE;

        		text +=dimensionPart;
        	}
        }
        
        if (!text.equals("")) {
        	text = NEWLINE + TAB + DIMENSION_BY + SPACE + LEFT_PARENTHESIS + text + RIGHT_PARENTHESIS ;
        }
        
        return text;
    }
    
    protected String getDefaultValue(Column column) {
    	String defaultValue = column.getDefaultValue();
    	if (defaultValue == null || defaultValue.equals(EMPTY_STRING)) return null;
    	SQLDataType type = column.getContainedType();
    	if (type == null) return null;
    	if ((type instanceof CharacterStringDataType 
    			&& ! defaultValue.equalsIgnoreCase("USER") 
    			&& ! defaultValue.equalsIgnoreCase("CURRENT USER")
    			&& ! defaultValue.equalsIgnoreCase("CURRENT_USER")
    			&& ! defaultValue.equalsIgnoreCase("SESSION_USER")
    			&& ! defaultValue.equalsIgnoreCase("SESSION USER")
    			&& ! defaultValue.equalsIgnoreCase("SYSTEM_USER")
    			&& ! defaultValue.equalsIgnoreCase("NULL"))
    		|| ((type instanceof DateDataType || type instanceof TimeDataType)
        			&& ! defaultValue.equals("NULL")
   				&& ! defaultValue.toUpperCase().matches(".*SYSDATE.*") 
   				&& ! defaultValue.toUpperCase().matches(".*CURRENT DATE.*") 
   				&& ! defaultValue.toUpperCase().matches(".*CURRENT_DATE.*") 
       			&& ! defaultValue.toUpperCase().matches(".*CURRENT TIME.*")
       			&& ! defaultValue.toUpperCase().matches(".*CURRENT_TIME.*")
       			&& ! defaultValue.toUpperCase().matches(".*CURRENT TIMESTAMP.*")
       			&& ! defaultValue.toUpperCase().matches(".*CURRENT_TIMESTAMP.*"))){
    		return this.ensureSingleQuotedString(defaultValue);
    	}
    	else if (type instanceof BinaryStringDataType
    			&& ! defaultValue.equalsIgnoreCase("NULL")) {
    		if (!(defaultValue.startsWith(HEX_LITERAL_PREFIX + SINGLE_QUOTE) && defaultValue.endsWith(SINGLE_QUOTE)))
    			return HEX_LITERAL_PREFIX + this.getSingleQuotedString(defaultValue);
    	}
    	return defaultValue;
    }

    protected String getCompressionValue(LUWStorageTable table) {
        StringBuffer text = new StringBuffer();
        if (table.isValueCompression()) {
        	text.append(NEWLINE).append(TAB).append(VALUE_COMPRESSION);
        }
        return text.toString();
    }
    
    
    protected boolean isTablespaceContainersRequried(LUWTableSpace tablespace) {
  		return true;
    }
    
	protected String getPrivilegedObjectName(Privilege privilege, boolean quoteIdentifiers, boolean qualifyNames) {
		SQLObject obj = privilege.getObject();
		String name = null;
		if (obj instanceof Table) name = getName((Table)obj, quoteIdentifiers, qualifyNames);
		else if (obj instanceof Index) name = getName((Index)obj, quoteIdentifiers, qualifyNames);
		else if (obj instanceof Sequence) name = getName((Sequence)obj, quoteIdentifiers, qualifyNames);
		else if (obj instanceof Schema) name = getName((Schema)obj, quoteIdentifiers, qualifyNames);
		else if (obj instanceof LUWModule) name = getName((LUWModule)obj, quoteIdentifiers,qualifyNames);
		else if (obj instanceof LUWGlobalVariable) name = getName((LUWGlobalVariable)obj, quoteIdentifiers,qualifyNames);
		else if (obj instanceof Routine) name = getRoutineSpecifier(obj, quoteIdentifiers, qualifyNames);
		else if (obj instanceof UserDefinedType) name = getName((UserDefinedType)obj, quoteIdentifiers, qualifyNames);
		else if (obj instanceof LUWTableSpace) name = getName((LUWTableSpace)obj, quoteIdentifiers);
		else if (obj instanceof LUWDatabasePackage) name = getName((LUWDatabasePackage)obj, quoteIdentifiers,qualifyNames);
		else if (obj instanceof Database) name = ""; //$NON-NLS-1$
		return name;
	}

	protected String getPrivilegedObjectTypeString(Privilege privilege) {
		SQLObject obj = privilege.getObject();
		if (obj instanceof BaseTable) return TABLE;
		if (obj instanceof Method) return METHOD;
		if (obj instanceof Function) return FUNCTION;
		if (obj instanceof Procedure) return PROCEDURE;
		if (obj instanceof ViewTable) return TABLE;
		if (obj instanceof Index) return INDEX;
		if (obj instanceof Schema) return SCHEMA;
		if (obj instanceof Sequence) return SEQUENCE;
		if (obj instanceof Database) return DATABASE;
		if (obj instanceof DB2Package) return PACKAGE;
		if (obj instanceof LUWTableSpace) return TABLESPACE;
		if (obj instanceof LUWModule) return MODULE;
		if (obj instanceof LUWGlobalVariable) return VARIABLE;
		return EMPTY_STRING;
	}

	protected String getGrantRoleAuthorizationString(RoleAuthorization roleAuth, boolean quoteIdentifiers) {
	    AuthorizationIdentifier grantee = roleAuth.getGrantee();
	    
		String authStr = NEWLINE + GRANT  + SPACE + this.getName(roleAuth.getRole(), quoteIdentifiers) 
						+ SPACE + TO + this.getGranteeType(grantee) + SPACE + this.getName(roleAuth.getGrantee(), quoteIdentifiers);
		if (roleAuth.isGrantable()) {
			authStr += SPACE + WITH + SPACE + ADMIN + SPACE + OPTION;
		}
		return authStr;
	}
	
    protected String getAlterTableAlterValueCompressionString(LUWTable table) {
    	String compressionStr = NEWLINE + TAB;
    	if (table.isValueCompression()) {
    		compressionStr += "ACTIVATE"; //$NON-NLS-1$
    	} else {
    		compressionStr += "DEACTIVATE"; //$NON-NLS-1$
    	}
    	compressionStr+= SPACE + VALUE_COMPRESSION;
        return compressionStr;
    }
    
    protected String getAlterTableAlterVolatileString(LUWTable table) {
    	if (table instanceof LUWTable) {
        	String volatileStr = NEWLINE + TAB;
    		if (((LUWTable)table).isVolatile()) return volatileStr + "VOLATILE"; //$NON-NLS-1$
    		else return volatileStr + "NOT VOLATILE"; //$NON-NLS-1$
    	}
    	return ""; //$NON-NLS-1$
    }

    protected String getAlterTableAlterRowCompressionString(LUWTable table) {
    	if (table instanceof LUWStorageTable) {
        	String compressionStr = NEWLINE + TAB;
    		if (((LUWStorageTable)table).isRowCompression()) return compressionStr + "COMPRESS YES"; //$NON-NLS-1$
    		else return compressionStr + "COMPRESS NO"; //$NON-NLS-1$
    	}
    	return ""; //$NON-NLS-1$
    }
    
    //@Override
	public String alterTableAddColumn(Column column, boolean quoteIdentifiers, boolean qualifyNames) {
		DB2IdentitySpecifier spec = (DB2IdentitySpecifier)column.getIdentitySpecifier();
		if (spec != null) {
    		//report error: alter table add column cannot have identity options
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					LUWDdlMessages.FE_ALTER_TABLE_ADD_COLUMN_IDENTITY_OPTIONS, new Object[] { getName(column, false, true)}));			
		}
		return super.alterTableAddColumn(column, quoteIdentifiers, qualifyNames);
	}

    public String alterTableDropColumn(Column column,  boolean quoteIdentifiers, boolean qualifyNames) {
    	Table table = (Table)ContainmentServiceImpl.INSTANCE.getContainer(column);
    	if(table != null && table instanceof BaseTable) {
	        String statement = ALTER + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames)
	    		+ " DROP COLUMN " + getColumnNameString(column, quoteIdentifiers,qualifyNames); //$NON-NLS-1$
	        return statement;
    	}
    	return null;
    }

	protected String getDistributeKeyword() {
    	return PARTITION_KEY;
    }

	protected String getBufferPoolSize(LUWBufferPool bufferpool) {
		String text = null;
		int size = bufferpool.getSize();
        if (size <=0) {
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					LUWDdlMessages.FE_BUFFERPOOL_INVAILD_SIZE_VALUE, new Object[] { bufferpool.getName()}));
        	return null;
        } else {
        	text = SPACE+ "SIZE " + bufferpool.getSize(); //$NON-NLS-1$
        }
        return text;
	}
	
	public String reorgTable(Column col, boolean quoteIdentifiers, boolean qualifyNames) {
		return reorgTable(col.getTable(), quoteIdentifiers, qualifyNames);
	}
	
	public String reorgTable(Table table, boolean quoteIdentifiers, boolean qualifyNames) {
        String tableName = table.getName();
        String schemaName = table.getSchema().getName();
        if(quoteIdentifiers) {
            tableName = this.getDoubleQuotedString(tableName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if(qualifyNames) {
            tableName = schemaName + DOT + tableName;
        }
		return CALL + SPACE + ADMIN_CMD + LEFT_PARENTHESIS + SINGLE_QUOTE 
				+ REORG + SPACE + TABLE + SPACE + tableName + SINGLE_QUOTE + RIGHT_PARENTHESIS;
	}
	
    public String getName(LUWPartitionGroup pg, boolean quoteIdentifiers) {
        String pgName = pg.getName();
        if(quoteIdentifiers) {
            pgName = this.getDoubleQuotedString(pgName);
        }
        return pgName;
    }

	protected String getGranteeType(AuthorizationIdentifier authID){
		if (authID instanceof Group)
			return SPACE + GROUP;
		else if (authID instanceof Role)
			return SPACE + ROLE;
		else if (authID instanceof User)
			return SPACE + USER;
		return "";
	}


    public String[] grantOn(LUWDatabasePackage dbPackage, boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getGrantString(dbPackage,quoteIdentifiers,qualifyNames);
    }
    
    public String[] grantOn(LUWModule module, boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getGrantString(module,quoteIdentifiers,qualifyNames);
    }

    public String[] grantOn(LUWGlobalVariable variable, boolean quoteIdentifiers, boolean qualifyNames) {
    	return this.getGrantString(variable,quoteIdentifiers,qualifyNames);
    }

    private String createModuleObject(LUWModuleObject moduleObject, boolean quoteIdentifiers, boolean qualifyNames ){
    	ILUWModuleDdlBuilder moduleBuilder = LUWDdlGenerator.getModuleDdlBuilder();
    	if (moduleBuilder != null) {
    		return moduleBuilder.buildCreateStatement((SQLObject)moduleObject, quoteIdentifiers, qualifyNames);
    	}
    	return null;
    }

    private String dropModuleObject(LUWModuleObject moduleObject,boolean quoteIdentifiers, boolean qualifyNames ){
    	ILUWModuleDdlBuilder moduleBuilder = LUWDdlGenerator.getModuleDdlBuilder();
    	if (moduleBuilder != null) {
    		return moduleBuilder.buildDropStatement((SQLObject)moduleObject, quoteIdentifiers, qualifyNames);
    	}
    	return null;
    }

    private String commentOnModuleObject(LUWModuleObject moduleObject,boolean quoteIdentifiers, boolean qualifyNames ){
    	ILUWModuleDdlBuilder moduleBuilder = LUWDdlGenerator.getModuleDdlBuilder();
    	if (moduleBuilder != null) {
    		return moduleBuilder.buildCommentStatement((SQLObject)moduleObject, quoteIdentifiers, qualifyNames);
    	}
    	return null;
    }
    
    protected String getName(UserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames) {
    	boolean isModuleObject = type instanceof LUWModuleObject;
    	String typeName = quoteIdentifiers? this.getDoubleQuotedString(type.getName()):type.getName();
    	if (isModuleObject) {
    		String moduleName = quoteIdentifiers? this.getDoubleQuotedString(((LUWModuleObject)type).getModule().getName())
    											:((LUWModuleObject)type).getModule().getName();
    		String schemaName = quoteIdentifiers? this.getDoubleQuotedString(((LUWModuleObject)type).getModule().getOwningSchema().getName())
    											:((LUWModuleObject)type).getModule().getOwningSchema().getName();
            typeName = moduleName + DOT + typeName ;
    		if(qualifyNames) {
                typeName = schemaName + DOT +  typeName;
            }

    	} else {
    		String schemaName = quoteIdentifiers? this.getDoubleQuotedString(type.getSchema().getName())
    											: type.getSchema().getName();
            if(qualifyNames) {
                typeName = schemaName + DOT + typeName;
            }
    	}
        return typeName;
        
        
    }

    protected String RecoverDroppedTableString(LUWTableSpace tablespace) {
    	String text="";
        //only available for regular tablespace for V8
        if (tablespace.getTablespaceType() == TableSpaceType.REGULAR_LITERAL) {
        	if (!tablespace.isRecoverDroppedTableOn()) {   //default is ON
        		text += NEWLINE + TAB + "DROPPED TABLE RECOVERY " + OFF;
        	}
        }
        return text;
    }

	private String getName(String oldname, boolean quoteIdentifiers) {
        if(quoteIdentifiers) {
        	oldname = this.getDoubleQuotedString(oldname);
        }
        return oldname;
	}

	public String alterTablespaceRename(LUWTableSpace tablespace, String oldname,
			boolean quoteIdentifiers, boolean qualifyNames) {
		return RENAME + SPACE + TABLESPACE + SPACE + getName(oldname, quoteIdentifiers) + SPACE + 
        	TO + SPACE + getName(tablespace, quoteIdentifiers);
	}

	public String alterTablespaceProlog(LUWTableSpace tablespace,boolean quoteIdentifiers,boolean qualifyNames) {
    	String tablespaceName = tablespace.getName();
        if(quoteIdentifiers) {
            tablespaceName = this.getDoubleQuotedString(tablespaceName);
        }
        return ALTER + SPACE + TABLESPACE + SPACE + tablespaceName;
	}
	
	public String alterTablespaceContainers(LUWTableSpace tablespace,
			EList newContainers, EList oldContainers, boolean quoteIdentifiers, boolean qualifyNames) {
    	String text="";

    	boolean first = true;
    	outerAdd:for (Iterator iter = newContainers.iterator(); iter.hasNext();) {
    		LUWDatabaseContainer container = (LUWDatabaseContainer)iter.next();
    		String newName = container.getName();
    		LUWContainerType newType = container.getContainerType();
    		Iterator it1 = oldContainers.iterator();
    		while (it1.hasNext()) {
        		LUWDatabaseContainer oldContainer = (LUWDatabaseContainer)it1.next();
        		if (oldContainer.getName().equals(newName) &&
        				oldContainer.getContainerType().equals(newType))
        			continue outerAdd;
    		}
    		if (first) text += NEWLINE + TAB + ADD + SPACE + "(";
    		else text += ",";
    		if (LUWContainerType.DEVICE_LITERAL.equals(newType)) {
    			text += DEVICE + SPACE;
    		}
    		else if (LUWContainerType.FILE_LITERAL.equals(newType)) {
    			text += FILE + SPACE;	
    		}    		    		
    		text += this.getSingleQuotedString(newName);
        	if ( container.getSizeInPages() > 0 ) {
        		text += SPACE +container.getSizeInPages();
        	}
        	else {
        		text += SPACE + container.getSize();
        		text += SPACE + container.getSizeUnits().getName();
        	}

    		first = false;
    	}
    	if (!first) text += ")";

    	first = true;
    	outerDrop:for (Iterator iter = oldContainers.iterator(); iter.hasNext();) {
    		LUWDatabaseContainer container = (LUWDatabaseContainer)iter.next();
    		String oldName = container.getName();
    		LUWContainerType oldType = container.getContainerType();
    		Iterator it1 = newContainers.iterator();
    		while (it1.hasNext()) {
        		LUWDatabaseContainer newContainer = (LUWDatabaseContainer)it1.next();
        		if (newContainer.getName().equals(oldName) &&
        				newContainer.getContainerType().equals(oldType))
        			continue outerDrop;
    		}
    		if (first) text += NEWLINE + TAB + DROP + SPACE + "(";
    		else text += ",";
    		if (LUWContainerType.DEVICE_LITERAL.equals(oldType)) {
    			text += DEVICE + SPACE;
    		}
    		else if (LUWContainerType.FILE_LITERAL.equals(oldType)) {
    			text += FILE + SPACE;	
    		}    		    		
    		text += this.getSingleQuotedString(oldName);

    		first = false;
    	}
    	if (!first) text += ")";

        return text;
	}

	public String alterTablespaceContainersSize(LUWTableSpace tablespace,
			EList newContainers, EList oldContainers, boolean quoteIdentifiers,
			boolean qualifyNames,LUWDeltaDdlGenerator delta) {
    	String text="";
    	boolean first = true;
    	Iterator iter = newContainers.iterator();
    	while (iter.hasNext()) {
    		LUWDatabaseContainer container = (LUWDatabaseContainer)iter.next();
    		String newName = container.getName();
    		LUWContainerType newType = container.getContainerType();
    		Iterator it1 = oldContainers.iterator();
    		while (it1.hasNext()) {
        		LUWDatabaseContainer oldContainer = (LUWDatabaseContainer)it1.next();
        		if (oldContainer.getName().equals(newName) &&
        				oldContainer.getContainerType().equals(newType) &&
        				!newType.equals(LUWContainerType.DIRECTORY_LITERAL)) {
        			EStructuralFeature feature = LUWPackage.eINSTANCE.getLUWDatabaseContainer_SizeInPages();
        			int oldSizeInPages = (Integer)delta.getOldContainerValue(feature,container);
        			feature = LUWPackage.eINSTANCE.getLUWDatabaseContainer_Size();
        			int oldSize = (Integer)delta.getOldContainerValue(feature,container);
        			feature = LUWPackage.eINSTANCE.getLUWDatabaseContainer_SizeUnits();
        			UnitType oldSizeUnits = (UnitType)delta.getOldContainerValue(feature,container);
        			if (((oldSizeInPages != container.getSizeInPages()) ||
        					(oldSize != container.getSize()) ||
        					(!oldSizeUnits.equals(container.getSizeUnits())))) {
        				if (first) text += NEWLINE + TAB + RESIZE + SPACE + "(";
        				else text += ",";
        				if (LUWContainerType.DEVICE_LITERAL.equals(newType)) {
        					text += DEVICE + SPACE;
        				}
        				else if (LUWContainerType.FILE_LITERAL.equals(newType)) {
        					text += FILE + SPACE;	
        				}    		    		
        				text += this.getSingleQuotedString(newName);
        				if ((container.getSizeInPages() > 0) &&
        						(oldSizeInPages != container.getSizeInPages())) {
        					text += SPACE +container.getSizeInPages();
        				}
        				else {
        					text += SPACE + container.getSize();
        					text += SPACE + container.getSizeUnits().getName();
        				}
        				first = false;
        			}
        		}
    		}
    	}
    	if (!first) text += ")";
        return text;
	}
	
	public String tablespaceBufferPoolClause(LUWTableSpace tablespace,boolean quoteIdentifiers) {
        LUWBufferPool bufferpool = tablespace.getBufferPool();
        if(bufferpool != null) {
        	String bufferpoolName = bufferpool.getName();
            if(quoteIdentifiers) {
            	bufferpoolName = this.getDoubleQuotedString(bufferpoolName);
            }
    		return NEWLINE + TAB + BUFFERPOOL + SPACE + bufferpoolName;
        }
		return "";
	}

	public String tablespaceOverheadClause(LUWTableSpace tablespace) {
		return NEWLINE + TAB + OVERHEAD + SPACE + String.valueOf(tablespace.getOverhead());
	}

	public String tablespaceXferRateClause(LUWTableSpace tablespace) {
		return NEWLINE + TAB + XFER_RATE + SPACE + String.valueOf(tablespace.getTransferRate());
	}

	public String tablespaceDroppedTableRecoveryClause(LUWTableSpace tablespace) {
		return NEWLINE + TAB + DROPPED_TABLE_RECOVERY + SPACE + (tablespace.isRecoverDroppedTableOn()?ON:OFF);
	}

	public String tablespaceAutoResizeClause(LUWTableSpace tablespace) {
		// Support for alter in version 9
    	return EMPTY_STRING;
	}

	public String tablespaceIncreaseSizeClause(LUWTableSpace tablespace) {
		// Support for alter in version 9
    	return EMPTY_STRING;
	}

	public String tablespaceMaxSizeClause(LUWTableSpace tablespace) {
		// Support for alter in version 9
    	return EMPTY_STRING;
	}

	public String tablespacePrefetchSizeClause(LUWTableSpace tablespace) {
		int prefetchsize = tablespace.getPreFetchSize();
		return NEWLINE + TAB + PREFETCHSIZE + SPACE + 
			((prefetchsize > 0)?String.valueOf(prefetchsize):AUTOMATIC);
	}

	public String transferSchemaOwnership(Schema changed, boolean quoteIdentifiers) {
    	AuthorizationIdentifier auth=changed.getOwner();
    	if (auth != null)
    		return XFER_OWNERSHIP + SPACE + SCHEMA + SPACE + 
				getName(changed.getName(),quoteIdentifiers) + SPACE + TO +
				SPACE + USER + SPACE + getName(auth,quoteIdentifiers) + 
				SPACE + PRESERVE_PRIVILEGES;
		return null;
	}

    private LUWDdlGenerator generator;
}
