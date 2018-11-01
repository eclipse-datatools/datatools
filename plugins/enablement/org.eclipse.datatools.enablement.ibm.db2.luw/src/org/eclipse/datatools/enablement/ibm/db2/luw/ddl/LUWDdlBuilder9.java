/*******************************************************************************
 * Copyright (c) 2009, 2014 IBM Corporation and others.
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
import java.util.Iterator;

import org.eclipse.datatools.enablement.ibm.db2.ddl.DB2DdlMessages;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMaterializedQueryTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.ManagementType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PartitionMethod;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.TableSpaceType;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger;
import org.eclipse.datatools.enablement.ibm.db2.model.UnitType;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType;
import org.eclipse.datatools.modelbase.sql.tables.ActionTimeType;
import org.eclipse.datatools.modelbase.sql.tables.Column;

public class LUWDdlBuilder9 extends LUWDdlBuilder {
	protected final static String DISTRIBUTE_BY = "DISTRIBUTE BY"; //$NON-NLS-1$
	protected final static String PARTITION_BY = "PARTITION BY"; //$NON-NLS-1$
	protected final static String PARTITION = "PARTITION"; //$NON-NLS-1$
	protected final static String HASH = "HASH"; //$NON-NLS-1$
	protected final static String REPLICATION = "REPLICATION"; //$NON-NLS-1$
	protected final static String RANGE = "RANGE"; //$NON-NLS-1$
	protected final static String NULLS_LAST = "NULLS LAST"; //$NON-NLS-1$
	protected final static String STARTING = "STARTING"; //$NON-NLS-1$
	protected final static String ENDING = "ENDING"; //$NON-NLS-1$
	protected final static String AT = "AT"; //$NON-NLS-1$
	protected final static String COMPRESS = "COMPRESS"; //$NON-NLS-1$
	protected final static String YES = "YES"; //$NON-NLS-1$
	protected final static String EXCLUSIVE = "EXCLUSIVE"; //$NON-NLS-1$

	public LUWDdlBuilder9(){
	}
	public LUWDdlBuilder9(LUWDdlGenerator generator){
		super(generator);
	}

	protected String getPartitionKey(LUWStorageTable table, boolean quoteIdentifiers) {
	    StringBuffer buffer = new StringBuffer();

	    LUWPartitionKey partitionKey = table.getPartitionKey();
	    if (partitionKey != null) {
	    	StringBuffer partKeyBuf = new StringBuffer();
		    PartitionMethod partMethod = partitionKey.getPartitionMethod();
		    if (partMethod == PartitionMethod.HASHING_LITERAL) {
		    	partKeyBuf.append(NEWLINE).append(TAB).append(DISTRIBUTE_BY).append(SPACE).append(HASH).append(SPACE).append(LEFT_PARENTHESIS);
			    boolean hasKey = false;
		    	Iterator it = partitionKey.getColumns().iterator();
		        while(it.hasNext()) {
		        	hasKey = true;
		            Column column = (Column) it.next();
		            partKeyBuf.append(column.getName());
		            if(it.hasNext()) {
		            	partKeyBuf.append(", ");                 //$NON-NLS-1$
		            }
		        }
		        partKeyBuf.append(RIGHT_PARENTHESIS); //$NON-NLS-1$
		        
		        if (hasKey) {
		        	buffer.append(partKeyBuf);
		        }
		    } else if (partMethod == PartitionMethod.TABLE_REPLICATED_LITERAL) {   //can not apply replication to table 
		    	if (table instanceof LUWMaterializedQueryTable) {
		    		partKeyBuf.append(NEWLINE).append(TAB).append(DISTRIBUTE_BY).append(SPACE).append(REPLICATION);
		        	buffer.append(partKeyBuf);
		    	} else {
		    		partKeyBuf.append(NEWLINE).append(TAB).append(DISTRIBUTE_BY).append(SPACE).append(HASH).append(SPACE).append(LEFT_PARENTHESIS);
				    boolean hasKey = false;
			    	Iterator it = partitionKey.getColumns().iterator();
			        while(it.hasNext()) {
			        	hasKey = true;
			            Column column = (Column) it.next();
			            partKeyBuf.append(column.getName());
			            if(it.hasNext()) {
			            	partKeyBuf.append(", ");                 //$NON-NLS-1$
			            }
			        }
			        partKeyBuf.append(RIGHT_PARENTHESIS); //$NON-NLS-1$
			        
			        if (hasKey) {
			        	buffer.append(partKeyBuf);
			        }
		    	}
		    }

	    }

	    LUWDataPartitionKey dataPartitionKey = table.getDataPartitionKey();
	    if (dataPartitionKey != null) {
	    	buffer.append(this.getRangePartitions(dataPartitionKey));	    
	    }

	    return buffer.toString();
    }
	
    private String getRangePartitions(LUWDataPartitionKey partitionKey) {
    	StringBuffer buffer = new StringBuffer();

    	boolean hasKey = false;
    	StringBuffer keyBuffer = new StringBuffer();
    	keyBuffer.append(NEWLINE).append(TAB).append(PARTITION_BY).append(SPACE).append(RANGE).append(LEFT_PARENTHESIS);

	    Collection keyCols = new ArrayList();
	    Iterator it = partitionKey.getPartitionExpressions().iterator();
        while(it.hasNext()) {
        	hasKey = true;
        	LUWPartitionExpression expression = (LUWPartitionExpression) it.next();
            Column column = expression.getColumn();
            keyBuffer.append(column.getName());
            keyCols.add(column);
            
            if (expression.isNullsLast()) {
            	keyBuffer.append(SPACE).append(NULLS_LAST);
            }
            
            if(it.hasNext()) {
            	keyBuffer.append(", ");                 //$NON-NLS-1$
            }
        }

        keyBuffer.append(RIGHT_PARENTHESIS); //$NON-NLS-1$

    	if (hasKey) {
    		buffer.append(keyBuffer);
    	}
    	
    	
    	boolean hasDataPartition = false;
    	StringBuffer datapartBuffer = new StringBuffer();
    	datapartBuffer.append(NEWLINE).append(TAB).append(LEFT_PARENTHESIS);
        LUWStorageTable table = partitionKey.getTable();
    	for (Iterator iter = table.getDataPartitions().iterator(); iter.hasNext();) {
    		hasDataPartition = true;
    		LUWDataPartition partition = (LUWDataPartition) iter.next();
    		String partName = partition.getName();
    		if (partName != null && !partName.equals("")){
    			datapartBuffer.append(PARTITION).append(SPACE).append(partName);
    		}

    		datapartBuffer.append(this.getRange(partition,keyCols));

    		LUWTableSpace tsp = partition.getRegularDataTableSpace();
    		if (tsp != null) {
    			datapartBuffer.append(SPACE).append(IN).append(SPACE).append(tsp.getName());
    		}
    		tsp = partition.getLOBDataTableSpace();
    		if (tsp != null) {
    			datapartBuffer.append(SPACE).append(LONG).append(SPACE).append(IN).append(SPACE).append(tsp.getName());
    		}
    		
            if(iter.hasNext()) {
            	datapartBuffer.append(", ").append(NEWLINE).append(TAB);                 //$NON-NLS-1$
            }
    	}
    	datapartBuffer.append(RIGHT_PARENTHESIS);

    	if (hasDataPartition) {
    		buffer.append(datapartBuffer);
    	}
    	
    	return buffer.toString();
    }

    
    private String getRange(LUWDataPartition partition, Collection keyCols) {
    	StringBuffer buffer = new StringBuffer();
    	String startStr= "";
    	String endStr= "";
    	
    	for (Iterator keyIter = keyCols.iterator(); keyIter.hasNext();) {
    		Column keyCol = (Column) keyIter.next();
		   	for (Iterator iter = partition.getPartitionElements().iterator();iter.hasNext();){
		    		LUWPartitionElement element = (LUWPartitionElement) iter.next();
		    		Column column = element.getLUWPartitionExpression().getColumn();
		    		
		    		if (column != keyCol) continue;
		    		DataType type = element.getLUWPartitionExpression().getColumn().getDataType();
		    		if (type instanceof CharacterStringDataType
		    			|| type instanceof DateDataType || type instanceof TimeDataType){
		    			String rangeValue = element.getStarting();
		    			if (rangeValue.equalsIgnoreCase("MINVALUE") || rangeValue.equalsIgnoreCase("MAXVALUE")) {
			    			startStr += rangeValue;
		    			} else {
			    			startStr += this.getSingleQuotedString(rangeValue);
		    			}
		    			
		    			rangeValue = element.getEnding();
		    			if (rangeValue.equalsIgnoreCase("MINVALUE") || rangeValue.equalsIgnoreCase("MAXVALUE")) {
		    				endStr += rangeValue;
		    			} else {
		    				endStr += this.getSingleQuotedString(rangeValue);
		    			}
		    		} else {
			    		startStr += element.getStarting();
			    		endStr += element.getEnding();
		    		}
		    		
		    		if (keyIter.hasNext()) {
		    			startStr += ",";
		    			endStr += ",";
		    		}
		  	}
	   	
    	}
	   	buffer.append(SPACE).append(STARTING).append(SPACE).append(FROM).append(SPACE).append(LEFT_PARENTHESIS).append(startStr).append(RIGHT_PARENTHESIS).append(SPACE);
	   	if (!partition.isLowInclusive()) {
	   		buffer.append(SPACE).append(EXCLUSIVE).append(SPACE);
	   	}
	   	
	   	buffer.append(ENDING).append(SPACE).append(AT).append(SPACE).append(LEFT_PARENTHESIS).append(endStr).append(RIGHT_PARENTHESIS);
	   	if (!partition.isHighInclusive()) {
	   		buffer.append(SPACE).append(EXCLUSIVE).append(SPACE);
	   	}
	   	
	   	return buffer.toString();
    }
    
    protected String getCompressionValue(LUWStorageTable table) {
        StringBuffer text = new StringBuffer();
        if (table.isRowCompression()) {
        	text.append(NEWLINE).append(TAB).append(COMPRESS).append(SPACE).append(YES);
        }
      
        text.append(super.getCompressionValue(table));

        return text.toString();
    }

    protected boolean isTablespaceContainersRequried(LUWTableSpace tablespace) {
    	if (tablespace.getManagementType() == ManagementType.AUTOMATIC_STORAGE_LITERAL) {
        	return false;
    	} else {
    		return true;
    	}
    }
    
    protected String getAlterTableAlterRowCompressionString(LUWTable table) {
    	String compressionStr = NEWLINE + TAB + COMPRESS;
    	if (table.isRowCompression()) {
    		compressionStr += SPACE + YES;
    	} else {
    		compressionStr += SPACE + NO;
    	}
        return compressionStr;
    }

    protected String getDistributeKeyword() {
    	return this.DISTRIBUTE_BY;
    }
    
	protected String getBufferPoolSize(LUWBufferPool bufferpool) {
		return SPACE+ "SIZE " + bufferpool.getSize(); 
	}

	protected String getDataPartitionOptions( LUWIndex index )
	{
		String ddl = "";
        
        if (index != null) {
        	String pmode = ((LUWTable)index.getTable()).getPartitionMode();
        	
        	if (pmode != null && !pmode.trim().equals( "" )) { //$NON-NLS-1$
        		if (index.isNotPartitioned()) {
        			ddl += NEWLINE + "NOT PARTITIONED ";
        		}

        		LUWTableSpace luwtbspace = index.getTablespace();

               	if (luwtbspace != null) {
               		ddl += NEWLINE + "IN " + luwtbspace.getName();
               	}
        	}
        }
		
		return ddl;
	}
	
    protected String RecoverDroppedTableString(LUWTableSpace tablespace) {
    	String text="";
        //only available for regular and large tablespace  for V9
        if (tablespace.getTablespaceType() == TableSpaceType.REGULAR_LITERAL
        	||tablespace.getTablespaceType() == TableSpaceType.LARGE_LITERAL ) {
        	if (!tablespace.isRecoverDroppedTableOn()) {   //default is ON
        		text += NEWLINE + TAB + "DROPPED TABLE RECOVERY " + OFF;
        	}
        }
        return text;
    }
    
    @SuppressWarnings("unchecked")
	public String createTrigger(DB2Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames) {
        String statement = CREATE + SPACE + TRIGGER + SPACE + getName(trigger, quoteIdentifiers, qualifyNames) + SPACE;

        final ActionTimeType actionTime = trigger.getActionTime();
        if(actionTime == ActionTimeType.AFTER_LITERAL) {
            statement += NEWLINE  + TAB + AFTER;
        }
        else if(actionTime == ActionTimeType.BEFORE_LITERAL) {
            //statement += NEWLINE + TAB + NO + SPACE + CASCADE + SPACE + BEFORE;
        	statement += NEWLINE + TAB + BEFORE;
        }
        else if(actionTime == ActionTimeType.INSTEADOF_LITERAL) {
            statement += NEWLINE + TAB + INSTEAD_OF;
        }
	    statement += SPACE;

        if(trigger.isDeleteType()) {
    	    statement += DELETE;
    	}
    	else if(trigger.isInsertType()) {
    	    statement += INSERT;
    	}
    	else if(trigger.isUpdateType()) {
    	    statement += UPDATE;
    	    Collection updateColumns = trigger.getTriggerColumn();
            if(!updateColumns.isEmpty()) {
                statement += SPACE + OF + SPACE ;
                Iterator it = updateColumns.iterator();
                while(it.hasNext()) {
                    Column column = (Column) it.next();
                    statement += column.getName();
                    if(it.hasNext()) {
                        statement += COMMA + SPACE;
                    }
                }
            }
    	}
    	
        statement += SPACE + ON + SPACE + getName(trigger.getSubjectTable(), quoteIdentifiers, qualifyNames) + NEWLINE;

        final String newRow = trigger.getNewRow();
        final String oldRow = trigger.getOldRow();
        final String newTable = trigger.getNewTable();
        final String oldTable = trigger.getOldTable();

        String referenceStr = EMPTY_STRING;
        if(newRow != null && newRow.length() != 0 && !trigger.isDeleteType()) {
        	referenceStr += (referenceStr.equals(EMPTY_STRING)? SPACE: NEWLINE + TAB + TAB) + NEW + SPACE + AS + SPACE + newRow;
        }
        if(oldRow != null && oldRow.length() != 0 && !trigger.isInsertType()) {
        	referenceStr += (referenceStr.equals(EMPTY_STRING)? SPACE: NEWLINE +TAB + TAB) + OLD + SPACE + AS + SPACE + oldRow;
        }
        if(newTable != null && newTable.length() != 0) {
        	referenceStr += (referenceStr.equals(EMPTY_STRING)? SPACE: NEWLINE +TAB + TAB) + this.getTriggerReferenceNewTable() + SPACE + AS + SPACE + newTable;
        }
        if(oldTable != null && oldTable.length() != 0 && !trigger.isInsertType()) {
        	referenceStr += (referenceStr.equals(EMPTY_STRING)? SPACE: NEWLINE +TAB + TAB) + this.getTriggerReferenceOldTable() + SPACE + AS + SPACE + oldTable;
        }

        if (!referenceStr.equals(EMPTY_STRING)) {
        	statement += TAB + REFERENCING + SPACE + referenceStr + NEWLINE;
        }

        if(trigger.getActionGranularity() == ActionGranularityType.ROW_LITERAL) {
            statement += TAB + FOR + SPACE + EACH + SPACE + ROW;
    	}
        else {
            statement += TAB + FOR + SPACE + EACH + SPACE + STATEMENT;
    	}

        statement += NEWLINE;
        
        SearchCondition condition = trigger.getWhen();
        if(condition != null) {
        	String c = condition.getSQL();
        	if(c != null && c.trim().length() != 0) {
        		statement += WHEN + SPACE + LEFT_PARENTHESIS + condition.getSQL() + RIGHT_PARENTHESIS + NEWLINE;
        	}
        }

        String sqlBody = EMPTY_STRING;
        Iterator it = trigger.getActionStatement().iterator();
        while(it.hasNext()) {
            SQLStatement s = (SQLStatement) it.next();
            sqlBody += s.getSQL();
        }

        if (sqlBody.equals(EMPTY_STRING)) {
        	this.getEngineeringCallBack().writeMessage(MessageFormat.format(
					DB2DdlMessages.FE_TRIGGER_ACTION_EMPTY, new Object[] { getName(trigger, quoteIdentifiers, qualifyNames)}));
        	return null;
        }
        
        statement += sqlBody;
        
        return statement;
    }

	public String tablespaceAutoResizeClause(LUWTableSpace tablespace) {
		return NEWLINE + TAB + AUTORESIZE + SPACE + (tablespace.isAutoResize()?ON:OFF);
	}

	public String tablespaceIncreaseSizeClause(LUWTableSpace tablespace) {
    	long incrsize = tablespace.getIncreaseSize();
    	UnitType incrunit = tablespace.getIncreaseSizeUnit();
    	int incrpct = tablespace.getIncreasePercent();
    	if (incrsize != 0) 
    		return NEWLINE + TAB + INCREASESIZE + SPACE + incrsize + 
    		((incrunit != null)?(SPACE + incrunit.getLiteral()):EMPTY_STRING);
    	if (incrpct != 0) 
    		return NEWLINE + TAB + INCREASESIZE + SPACE + incrpct + SPACE + PERCENT;
    	return EMPTY_STRING;
	}

	public String tablespaceMaxSizeClause(LUWTableSpace tablespace) {
    	long maxsize = tablespace.getMaximumSize();
    	UnitType maxunit = tablespace.getMaximumSizeUnit();
    	if (maxsize != 0) {
    		return NEWLINE + TAB + MAXSIZE + SPACE + maxsize + 
    		((maxunit != null)?(SPACE + maxunit.getLiteral()):EMPTY_STRING);
    	}
    	return NEWLINE + TAB + MAXSIZE + SPACE + NONE;
	}
	
	protected boolean supportsBufferpoolExtendedStorage() {
		//not supported from v9 onwards
		//see http://publib.boulder.ibm.com/infocenter/db2luw/v9/index.jsp?topic=/com.ibm.db2.udb.admin.doc/doc/r0001032.htm
		return false;
	}
}
