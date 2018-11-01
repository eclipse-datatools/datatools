/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.ddl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Column;
import org.eclipse.datatools.enablement.ibm.db2.model.GenerateType;
import org.eclipse.datatools.enablement.ibm.ddl.DdlGenerationUtility;
import org.eclipse.datatools.enablement.ibm.util.SimpleColumnDetails;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;

public class DB2DataPreservationDdlBuilder extends DB2DdlBuilder {

	protected static final String COMMENT_DELIMITER    = "--"; //$NON-NLS-1$
	protected final static String RENAME           	   = "RENAME"; //$NON-NLS-1$
	protected final static String INSERT           	   = "INSERT"; //$NON-NLS-1$
	protected final static String INTO             	   = "INTO"; //$NON-NLS-1$
	protected final static String SELECT           	   = "SELECT"; //$NON-NLS-1$
    protected final static String BACKUP_SUFFIX    	   = "_BU"; //$NON-NLS-1$
    protected static final String DECIMAL              = "DECIMAL"; //$NON-NLS-1$
    protected static final String REAL                 = "REAL"; //$NON-NLS-1$
    protected static final String INTEGER              = "INTEGER"; //$NON-NLS-1$
    protected static final String BIGINT               = "BIGINT"; //$NON-NLS-1$
    protected static final String SMALLINT             = "SMALLINT"; //$NON-NLS-1$
    protected static final String GRAPHIC              = "GRAPHIC"; //$NON-NLS-1$
    protected static final String CHAR                 = "CHAR"; //$NON-NLS-1$
	protected static final String TIMESTAMP            = "TIMESTAMP"; //$NON-NLS-1$
	protected static final String TIME                 = "TIME"; //$NON-NLS-1$
	protected static final String DATE                 = "DATE"; //$NON-NLS-1$
	protected static final String DOUBLE               = "DOUBLE"; //$NON-NLS-1$
	protected static final String XML                  = "XML"; //$NON-NLS-1$
	protected static final String RENAME_TABLE_COMMENT = "Renaming table to maintain a backup"; //$NON-NLS-1$
	protected static final String POPULATE_COMMENT     = "Populating table from backup using optimistic transformations"; //$NON-NLS-1$
	protected static final String RESTART_IDENTITY_COMMENT = "Replace <restart value placeholder> with an appropriate value"; //$NON-NLS-1$
	protected static final String DROP_BACKUP_COMMENT  = "Dropping backup table"; //$NON-NLS-1$
	protected static final String UPDATE_NULL_COLUMNS_COMMENT = "Update columns changed to NOT NULL with default"; //$NON-NLS-1$

	protected Map backupTableMap = new HashMap();
	
	/**
     * Get the name of the backup table.
     * @param table
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     */
    protected String getNewBackupName(Table table, boolean quoteIdentifiers, boolean qualifyNames) {
        String tableNamePrefix = table.getName() + BACKUP_SUFFIX;
        Schema schema = table.getSchema();
        String schemaName = schema.getName();

        String tableName = DdlGenerationUtility.getUniqueTableName(schema,tableNamePrefix);
        backupTableMap.put(tableNamePrefix,tableName);
        
        if(quoteIdentifiers) {
            tableName = this.getDoubleQuotedString(tableName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if(qualifyNames) {
            tableName = schemaName + DOT + tableName;
        }
    
        return tableName;
    }

    protected String getExistingBackupName(String schemaName,String tablename,boolean quoteIdentifiers, boolean qualifyNames) {
        String tableNamePrefix = tablename + BACKUP_SUFFIX;
        String tableName = (String)backupTableMap.get(tableNamePrefix);
        if(quoteIdentifiers) {
            tableName = this.getDoubleQuotedString(tableName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }
        if(qualifyNames) {
            tableName = schemaName + DOT + tableName;
        }
        return tableName;
    }

    public String renameTableToBackup(Table table, boolean quoteIdentifiers, boolean qualifyNames) {
        String statement = RENAME + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames) + SPACE + TO + SPACE;
        String backupTableName = getNewBackupName(table,quoteIdentifiers,false);
        return (statement + backupTableName);
    }

    public String getRenameComment() {
    	return NEWLINE + COMMENT_DELIMITER + SPACE + RENAME_TABLE_COMMENT;
    }

    public String getUpdateComment() {
    	return NEWLINE + COMMENT_DELIMITER + SPACE + UPDATE_NULL_COLUMNS_COMMENT;
    }

    public String getSuspendGenerateAlwaysStatement(Table newTable,Column idColumn,boolean quoteIdentifiers, boolean qualifyNames) {
    	String statement = null;
    	if (idColumn.getIdentitySpecifier() != null) {
    		statement = ALTER + SPACE + TABLE + SPACE + getName(newTable, quoteIdentifiers, qualifyNames)
    				+ SPACE + ALTER + SPACE + COLUMN + SPACE + getName(idColumn, quoteIdentifiers,false)
    				+ " SET GENERATED BY DEFAULT";
    	}
    	else {
    		statement = ALTER + SPACE + TABLE + SPACE + getName(newTable, quoteIdentifiers, qualifyNames)
			+ SPACE + ALTER + SPACE + COLUMN + SPACE + getName(idColumn, quoteIdentifiers,false)
			+ " DROP EXPRESSION";
    	}
    	return statement;
    }
    
    public String getSetGenerateAlwaysStatement(Table newTable,Column idColumn,boolean quoteIdentifiers, boolean qualifyNames) {
    	String statement = ALTER + SPACE + TABLE + SPACE + getName(newTable, quoteIdentifiers, qualifyNames)
    			+ SPACE + ALTER + SPACE + COLUMN + SPACE + getName(idColumn, quoteIdentifiers,false)
    			+ " SET GENERATED ALWAYS";
    	ValueExpression genExp = idColumn.getGenerateExpression();
    	if (genExp != null) {
    		statement += SPACE + AS + SPACE + LEFT_PARENTHESIS + genExp.getSQL() + RIGHT_PARENTHESIS;
    	}
    	return statement;
    }
    
    public String getRestartIdentityComment() {
    	return NEWLINE + COMMENT_DELIMITER + SPACE + RESTART_IDENTITY_COMMENT;
    }
    
    public String getRestartIdentityStatement(Table newTable,Column idColumn,boolean quoteIdentifiers, boolean qualifyNames) {
    	String statement = COMMENT_DELIMITER + SPACE + ALTER + SPACE + TABLE + SPACE + getName(newTable, quoteIdentifiers, qualifyNames)
    			+ SPACE + ALTER + SPACE + COLUMN + SPACE + getName(idColumn, quoteIdentifiers,false)
    			+ " RESTART WITH " + "<restart value placeholder>";
    	return statement;
    }

    public ArrayList getAlwaysGeneratedColumns(Table table) {
    	ArrayList genColumns = new ArrayList();
    	Iterator it = table.getColumns().iterator();
    	while (it.hasNext()) {
    		DB2Column column = (DB2Column)it.next();
    		// A column is not really generate ALWAYS unless an expression 
    		// or identity specifier is provided
    		if ((column.getGenerationType() == GenerateType.ALWAYS_LITERAL)
    				&&((column.getGenerateExpression() != null) || //
    						column.getIdentitySpecifier() != null)) {
    			genColumns.add(column);
    		}
    	}
    	return genColumns;
    }
    
    public String populateTableFromBackup(Table newTable, String oldTableName, Map oldColumns, boolean quoteIdentifiers, boolean qualifyNames) {
    	StringBuffer statement = new StringBuffer(COMMENT_DELIMITER + SPACE + INSERT + SPACE + INTO + SPACE + getName(newTable, quoteIdentifiers, qualifyNames) + LEFT_PARENTHESIS);
    	Iterator it = newTable.getColumns().iterator();
    	boolean firstColumn = true;
    	while (it.hasNext()) {
    		Column newColumn = (Column)it.next();
    		SimpleColumnDetails oldColDetails = (SimpleColumnDetails)oldColumns.get(newColumn);
    		if (oldColDetails == null) continue;
    		if (!firstColumn) statement.append(COMMA);
			firstColumn = false;
    		statement.append(getName(newColumn, quoteIdentifiers, false));
    	}
    	statement.append(RIGHT_PARENTHESIS + SPACE + SELECT + SPACE);
    	it = newTable.getColumns().iterator();
    	firstColumn = true;
    	while (it.hasNext()) {
    		Column newColumn = (Column)it.next();
    		SimpleColumnDetails newColDetails = new SimpleColumnDetails(newColumn);
    		SimpleColumnDetails oldColDetails = (SimpleColumnDetails)oldColumns.get(newColumn);
    		if (oldColDetails == null) continue;
    		if (!firstColumn) statement.append(COMMA);
			firstColumn = false;
    		String xformedColumn = getXformBackupColumn(
    				quoteIdentifiers?getDoubleQuotedString(oldColDetails.name):oldColDetails.name,
    				oldColDetails.type,newColDetails.type,
    				oldColDetails.length,newColDetails.length,
    				oldColDetails.scale,newColDetails.scale);
    		statement.append(xformedColumn);
    	}
    	statement.append(SPACE + FROM + SPACE + 
    			getExistingBackupName(newTable.getSchema().getName(),oldTableName,quoteIdentifiers,qualifyNames));
    	return statement.toString();
    }

    public String getPopulateComment() {
    	return NEWLINE + COMMENT_DELIMITER + SPACE + POPULATE_COMMENT;
    }
    
    public String dropBackupTable(Table oldTable, boolean quoteIdentifiers, boolean qualifyNames) {
    	StringBuffer statement = new StringBuffer(COMMENT_DELIMITER + SPACE + DROP + SPACE + TABLE + SPACE);
    	statement.append(getExistingBackupName(oldTable.getSchema().getName(),oldTable.getName(),quoteIdentifiers,qualifyNames));
    	return statement.toString();
    }
    
    public String dropBackupTable(String oldSchema, String oldName, boolean quoteIdentifiers, boolean qualifyNames) {
    	StringBuffer statement = new StringBuffer(COMMENT_DELIMITER + SPACE + DROP + SPACE + TABLE + SPACE);
    	statement.append(getExistingBackupName(oldSchema, oldName, quoteIdentifiers, qualifyNames));
    	return statement.toString();
    }
    
    public String getDropBackupComment() {
    	return NEWLINE + COMMENT_DELIMITER + SPACE + DROP_BACKUP_COMMENT;
    }
    
	/* 
	 * Get the substring which transforms a changed column for the DML
	 * used to move data from the backed up table to the newly created table.
	 * The intent here is not to ensure that the transformation will work for
	 * all values in the backed up table, but to provide a solution which
	 * will move the data assuming that all the original data can be
	 * presented without compromise into the new version of the table.
	 */
	public String getXformBackupColumn(String backupColumnName, String type1, String type2, int length1, int length2, int scale1, int scale2) {
		if(type1.equals(SMALLINT)) {
			// SMALLINT range: -32 768 to +32 767
			//
			// Any character string of length 6 or greater
			if (isCharacterStringType(type2)) {
				return getGeneratedNumberToStringXformExpression(backupColumnName,type2,length2);
			}
			// DECIMAL (precision less than 15)
			else if (type2.equals(DECIMAL)) {
				return getGeneratedDecimalFunctionExpression(backupColumnName,length2,scale2);
			}
		}
		else if(type1.equals(INTEGER)) {
			// INTEGER range: -2 147 483 648 to +2 147 483 647
			//
			// Any character string of length 11 or greater
			if (isCharacterStringType(type2)) {
				return getGeneratedNumberToStringXformExpression(backupColumnName,type2,length2);
			}
			// SMALLINT, REAL
			else if ((type2.equals(SMALLINT)) 
					|| (type2.equals(REAL))) {
				return getGeneratedScalarFunctionExpression(type2,backupColumnName);
			}
			// DECIMAL
			else if (type2.equals(DECIMAL)) {
				return getGeneratedDecimalFunctionExpression(backupColumnName,length2,scale2);
			}
		}
		else if(type1.equals(BIGINT)) {
			// BIGINT range: -9 223 372 036 854 775 80  to +9 223 372 036 854 775 807
			//
			// Any character string of length 11 or greater
			if (isCharacterStringType(type2)) {
				return getGeneratedNumberToStringXformExpression(backupColumnName,type2,length2);
			}
			// SMALLINT, REAL
			else if ((type2.equals(SMALLINT)) 
					|| (type2.equals(INTEGER))
					|| (type2.equals(REAL))) {
				return getGeneratedScalarFunctionExpression(type2,backupColumnName);
			}
			// DECIMAL
			else if (type2.equals(DECIMAL)) {
				return getGeneratedDecimalFunctionExpression(backupColumnName,length2,scale2);
			}
		}
		else if(type1.equals(REAL)) {
			// 32 bit floating point number; 
			//   range -7.2E+75 to 7.2E+75
			//         -5.4E-79 to 5.4E-79
			//
			// Character string, minimum length 8
			//   4 characters for exponent representation (i.e., 'E-79')
			//   4 characters minimum for mantissa representation  (i.e., '-5.4')
			if (isCharacterStringType(type2)) {
				return getGeneratedNumberToStringXformExpression(backupColumnName,type2,length2);
			}
			// SMALLINT, INTEGER, BIGINT
			else if ((type2.equals(SMALLINT))
					|| (type2.equals(INTEGER))
					|| (type2.equals(BIGINT))) {
				return getGeneratedScalarFunctionExpression(type2,backupColumnName);
			}
			// DECIMAL
			else if (type2.equals(DECIMAL)) {
				return getGeneratedDecimalFunctionExpression(backupColumnName,length2,scale2);
			}
		}
		else if(type1.equals(DOUBLE)) {
			// 64 bit floating point number; 
			//   range -7.2E+75 to 7.2E+75
			//         -5.4E-79 to 5.4E-79
			//
			// Character string, minimum length 8
			//   4 characters for exponent representation (i.e., 'E-79')
			//   4 characters minimum for mantissa representation  (i.e., '-5.4')
			if (isCharacterStringType(type2)) {
				return getGeneratedNumberToStringXformExpression(backupColumnName,type2,length2);
			}
			// REAL, SMALLINT, INTEGER, BIGINT
			else if ((type2.equals(REAL)) 
					|| (type2.equals(SMALLINT)) 
					|| (type2.equals(INTEGER)) 
					|| (type2.equals(BIGINT))) {
				return getGeneratedScalarFunctionExpression(type2,backupColumnName);
			}
			// DECIMAL
			else if (type2.equals(DECIMAL)) {
				return getGeneratedDecimalFunctionExpression(backupColumnName,length2,scale2);
			}
		}
		else if(type1.equals(DECIMAL)) {
			// 32 bit floating point number; 
			//   range (1-(10**31)) to ((10**31)-1)
			//         scale >=0 and scale <= precision
			//         precision <= 31
			//
			// Any character string of length (precision + 2) or greater
			if (isCharacterStringType(type2)) {
				return getGeneratedNumberToStringXformExpression(backupColumnName,type2,length2);
			}
			// REAL, SMALLINT, INTEGER, BIGINT
			else if ((type2.equals(REAL)) 
					|| (type2.equals(SMALLINT)) 
					|| (type2.equals(INTEGER)) 
					|| (type2.equals(BIGINT))) {
				return getGeneratedScalarFunctionExpression(type2,backupColumnName);
			}
			// DECIMAL (different precision, scale)
			else if ((type2.equals(DECIMAL))
					&& ((length1 != length2)
							|| (scale1 != scale2))) {
				return getGeneratedDecimalFunctionExpression(backupColumnName,length2,scale2);
			}
		}
		else if(type1.equals(DATE)) {
			// 4 byte date; 
			//   range 0001-01-01 to 9999-12-31
			//
			// Any character string of length greater than 10 and less than 256
			if (isCharacterStringType(type2)) {
				return getGeneratedNumberToStringXformExpression(backupColumnName,type2,length2);
			}
		}
		else if(type1.equals(TIME)) {
			// 3 byte time; 
			//   range 00:00:01 to 24:00:00
			//
			// Any character string of length greater than 8 and less than 256
			if (isCharacterStringType(type2)) {
				return getGeneratedNumberToStringXformExpression(backupColumnName,type2,length2);
			}
		}
		else if(type1.equals(TIMESTAMP)) {
			// 10 byte timestamp; 
			//   range 0001-01-01Z00:00:00.000001 to 9999-12-31Z24:00:00.000000
			//
			// Any character string of length greater than 26 and less than 256
			if (isCharacterStringType(type2)) {
				return getGeneratedNumberToStringXformExpression(backupColumnName,type2,length2);
			}
			if ((type2.equals(DATE))
					|| (type2.equals(TIME))) {
				return getGeneratedScalarFunctionExpression(type2,backupColumnName);
			}
		}
		else if (type1.equals(CHAR)) {
			if ((type2.equals(CHAR)
					&& (length2 < length1))
					|| (type2.equals(VARCHAR))) {
				if (length2 < length1) {
					// Assume we must trim the original value to fit into the new column
					return getGeneratedScalarFunctionExpression("RTRIM",backupColumnName); //$NON-NLS-1$
				}
			}
			if ((type2.equals(GRAPHIC)) 
					|| (type2.equals(VARGRAPHIC))) {
				// Assume we must trim the original value to fit into the new column
				return getGeneratedScalarFunctionExpression(GRAPHIC,
						getGeneratedScalarFunctionExpression("RTRIM",backupColumnName)); //$NON-NLS-1$
			}
			if ((type2.equals(CLOB)) 
					|| (type2.equals(DBCLOB))) {
				// Assume we must trim the original value to fit into the new column
				return getGeneratedScalarFunctionExpression(type2,
						getGeneratedScalarFunctionExpression("RTRIM",backupColumnName)); //$NON-NLS-1$
			}
			if ((type2.equals(SMALLINT)) 
					|| (type2.equals(INTEGER)) 
					|| (type2.equals(BIGINT)) 
					|| (type2.equals(REAL)) 
					|| (type2.equals(DOUBLE))) {
				return getGeneratedScalarFunctionExpression(type2,backupColumnName);
			}
			if (type2.equals(DECIMAL)) {
				return getGeneratedDecimalFunctionExpression(backupColumnName,
						length2, scale2);
			}
			if ((type2.equals(DATE)) 
					|| (type2.equals(TIME)) 
					|| (type2.equals(TIMESTAMP))) {
				return getGeneratedScalarFunctionExpression(type2,
						getGeneratedScalarFunctionExpression("RTRIM",backupColumnName)); //$NON-NLS-1$
			}
		}
		else if (type1.equals(VARCHAR)) {
			if ((type2.equals(CLOB)) 
					|| (type2.equals(DBCLOB))) {
				return getGeneratedScalarFunctionExpression(type2,backupColumnName);
			}
			if ((type2.equals(SMALLINT)) 
					|| (type2.equals(INTEGER)) 
					|| (type2.equals(BIGINT)) 
					|| (type2.equals(REAL)) 
					|| (type2.equals(DOUBLE))) {
				return getGeneratedScalarFunctionExpression(type2,backupColumnName);
			}
			if (type2.equals(DECIMAL)) {
				return getGeneratedDecimalFunctionExpression(backupColumnName,
						length2, scale2);
			}
			if ((type2.equals(DATE)) 
					|| (type2.equals(TIME)) 
					|| (type2.equals(TIMESTAMP))) {
				return getGeneratedScalarFunctionExpression(type2,backupColumnName);
			}
		}
		else if (type1.equals(GRAPHIC)) {
			if ((type2.equals(CHAR)) 
					|| (type2.equals(VARCHAR))) {
				// Assume we must trim the original value to fit into the new column
				return getGeneratedScalarFunctionExpression(CHAR,
						getGeneratedScalarFunctionExpression("RTRIM",backupColumnName)); //$NON-NLS-1$
			}
			if ((type2.equals(GRAPHIC) 
					&& (length2 < length1))
					|| (type2.equals(VARGRAPHIC))) {
				// Assume we must trim the original value to fit into the new column
				return getGeneratedScalarFunctionExpression("RTRIM",backupColumnName); //$NON-NLS-1$
			}
			if ((type2.equals(CLOB)) 
					|| (type2.equals(DBCLOB))) {
				// Assume we must trim the original value to fit into the new column
				return getGeneratedScalarFunctionExpression(type2,
						getGeneratedScalarFunctionExpression("RTRIM",backupColumnName)); //$NON-NLS-1$
			}
			if ((type2.equals(SMALLINT)) 
					|| (type2.equals(INTEGER)) 
					|| (type2.equals(BIGINT)) 
					|| (type2.equals(REAL)) 
					|| (type2.equals(DOUBLE))) {
				return getGeneratedScalarFunctionExpression(type2,
						getGeneratedScalarFunctionExpression(CHAR,backupColumnName));
			}
			if (type2.equals(DECIMAL)) {
				return getGeneratedDecimalFunctionExpression(
						getGeneratedScalarFunctionExpression(CHAR,backupColumnName),
						length2, scale2);
			}
			if ((type2.equals(DATE)) 
					|| (type2.equals(TIME)) 
					|| (type2.equals(TIMESTAMP))) {
				return getGeneratedScalarFunctionExpression(type2,
						getGeneratedScalarFunctionExpression("RTRIM",backupColumnName)); //$NON-NLS-1$
			}
		}
		else if (type1.equals(VARGRAPHIC)) {
			if ((type2.equals(CLOB)) 
					|| (type2.equals(DBCLOB))) {
				return getGeneratedScalarFunctionExpression(type2,backupColumnName);
			}
			if ((type2.equals(SMALLINT)) 
					|| (type2.equals(INTEGER)) 
					|| (type2.equals(BIGINT)) 
					|| (type2.equals(REAL)) 
					|| (type2.equals(DOUBLE))) {
				return getGeneratedScalarFunctionExpression(type2,
						getGeneratedScalarFunctionExpression(CHAR,backupColumnName));
			}
			if (type2.equals(DECIMAL)) {
				return getGeneratedDecimalFunctionExpression(
						getGeneratedScalarFunctionExpression(CHAR,backupColumnName),
						length2, scale2);
			}
			if ((type2.equals(DATE)) 
					|| (type2.equals(TIME)) 
					|| (type2.equals(TIMESTAMP))) {
				return getGeneratedScalarFunctionExpression(type2,backupColumnName);
			}
		}
		else if (type1.equals(XML)) {
			return getGeneratedXmlXformExpression(backupColumnName,type2);
		}
		return backupColumnName;
	}

	private String getGeneratedNumberToStringXformExpression(String colName, String type, int length) {
		if (isFixedLengthStringType(type)) {
			return getGeneratedScalarFunctionExpression(type,
					getGeneratedScalarFunctionExpression(type,colName,String.valueOf(length)));
		}
		else if (isLOBType(type)) {
			if (isDoubleByteType(type)) {
				return getGeneratedScalarFunctionExpression(type,
						getGeneratedScalarFunctionExpression(VARGRAPHIC,colName));
			}
			else {
				return getGeneratedScalarFunctionExpression(type,
						getGeneratedScalarFunctionExpression(VARCHAR,colName));
			}
		}
		return getGeneratedScalarFunctionExpression(type,colName);
	}
	
	private String getGeneratedScalarFunctionExpression(String function, String parm1, String parm2) {
		return function + LEFT_PARENTHESIS + parm1 + COMMA + parm2 + RIGHT_PARENTHESIS;
	}
	
	private String getGeneratedDecimalFunctionExpression(String parm,int precision, int scale) {
		return DECIMAL + LEFT_PARENTHESIS + parm + COMMA + String.valueOf(precision) + COMMA + String.valueOf(scale) + RIGHT_PARENTHESIS;
	}
	
	private String getGeneratedScalarFunctionExpression(String function,String parm) {
		return function + LEFT_PARENTHESIS + parm + RIGHT_PARENTHESIS;
	}

	protected String getGeneratedXmlXformExpression(String backupColName, String type2) {
		if (isCharacterStringType(type2) && !isDoubleByteType(type2)) {
			return "XMLSERIALIZE (" + backupColName + " AS " + type2 + ")"; //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$
		}
		return backupColName;
	}
	
	private boolean isCharacterStringType(String type) {
		if (type.equals(CHAR)
				|| type.equals(VARCHAR)
				|| type.equals(GRAPHIC)
				|| type.equals(VARGRAPHIC)
				|| type.equals(CLOB)
				|| type.equals(DBCLOB)) return true;
		return false;
	}
	
	private boolean isDoubleByteType(String type) {
		if (type.equals(GRAPHIC)
				|| type.equals(VARGRAPHIC)
				|| type.equals(DBCLOB)) return true;
		return false;
	}
	
	private boolean isLOBType(String type) {
		if (type.equals(CLOB)
				|| type.equals(BLOB)
				|| type.equals(DBCLOB)) return true;
		return false;
	}
	
	private boolean isFixedLengthStringType(String type) {
		if (type.equals(CHAR)
				|| type.equals(GRAPHIC)) return true;
		return false;
	}
}
