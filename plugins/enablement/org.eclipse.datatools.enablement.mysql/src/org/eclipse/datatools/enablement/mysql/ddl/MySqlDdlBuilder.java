 /*******************************************************************************
  * Copyright (c) 2005, 2008. Versant Corporation and others.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License 2.0
  * which accompanies this distribution, and is available at
  * https://www.eclipse.org/legal/epl-2.0/
  * 
  * Contributors:
  *     Versant Corporation - initial API and implementation
  *     IBM Corporation - fix for 237964
  *******************************************************************************/
 package org.eclipse.datatools.enablement.mysql.ddl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.enablement.mysql.catalog.MySqlCatalogTable;
import org.eclipse.datatools.enablement.mysql.ddl.shared.MySqlDdlBuilderHelper;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType;
import org.eclipse.datatools.modelbase.sql.tables.ActionTimeType;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.CheckType;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;

/**
 * This class generates the actual sql statements for MySql
 */
public class MySqlDdlBuilder extends MySqlDdlBuilderHelper{

    public String dropIndex(Index index, boolean quoteIdentifiers,
			boolean qualifyNames) {
		/*
		 * ALTER [IGNORE] TABLE tbl_name DROP INDEX index_name
		 */
		Table table = index.getTable();
		return ALTER + SPACE + TABLE + SPACE
				+ getName(table, quoteIdentifiers, qualifyNames) + SPACE + DROP
				+ SPACE + INDEX + SPACE
				+ getName(index, quoteIdentifiers, qualifyNames);
	}

	public String createTable(BaseTable table, boolean quoteIdentifiers,
			boolean qualifyNames, boolean generatePk) {
		PrimaryKey pk = table.getPrimaryKey();
		boolean hasPK = false;
		if (pk != null && pk.getMembers() != null && !pk.getMembers().isEmpty()){
			hasPK = true;
		}
		String statement = CREATE + SPACE + TABLE + SPACE
				+ getName(table, quoteIdentifiers, qualifyNames) + SPACE
				+ LEFT_PARENTHESIS + NEWLINE;
		Iterator it = table.getColumns().iterator();
		while (it.hasNext()) {
			Column column = (Column) it.next();
			statement += TAB + getColumnString(column, quoteIdentifiers,
                    generatePk);
			if (it.hasNext()) {
				statement += COMMA;
			} else if (hasPK && generatePk){
				statement += COMMA;
			}
			statement += NEWLINE;
		}
		if (hasPK && generatePk) {
            setCreateDone(pk);
            String pkStatement = TAB + PRIMARY_KEY + SPACE + LEFT_PARENTHESIS;
			// PRIMARY KEY  (`class_a_id`)
			ArrayList colList = new ArrayList();
			Iterator iter = pk.getMembers().iterator();
			while (iter.hasNext()){
				Column c = (Column) iter.next();
				if (c.getIdentitySpecifier() != null) {
					colList.add(c);
				}
			}
			iter = pk.getMembers().iterator();
			while (iter.hasNext()){
				Column c = (Column) iter.next();
				if (c.getIdentitySpecifier() == null) {
					colList.add(c);
				}
			}
			iter = colList.iterator();
			while (iter.hasNext()){
				Column c = (Column) iter.next();
				String columnName = c.getName();
				if (quoteIdentifiers) {
					pkStatement += this.getQuotedString(columnName);
				} else {
					pkStatement += columnName;
				}
				if (iter.hasNext()) {
					pkStatement += COMMA;
				}
			}
			pkStatement += RIGHT_PARENTHESIS + NEWLINE;
			statement += pkStatement;
		}
		statement += RIGHT_PARENTHESIS;
		if (table instanceof MySqlCatalogTable) {
			MySqlCatalogTable mySqlTable = (MySqlCatalogTable) table;
			String tableType = mySqlTable.getTableType();
			if (tableType != null) {
				statement += SPACE + ENGINE + "=" + tableType; //$NON-NLS-1$
			}
		}

		return statement;
	}

	public String createView(ViewTable view, boolean quoteIdentifiers,
			boolean qualifyNames) {
		String viewDefinition = CREATE + SPACE;
		viewDefinition += VIEW + SPACE
				+ getName(view, quoteIdentifiers, qualifyNames) + SPACE;

		String columns = getViewColumnList(view);
		if (columns != null) {
			viewDefinition += LEFT_PARENTHESIS + columns + RIGHT_PARENTHESIS
					+ SPACE;
		}
		viewDefinition += AS + NEWLINE;
		viewDefinition += view.getQueryExpression().getSQL();
		CheckType checkType = view.getCheckType();
		if (checkType == CheckType.CASCADED_LITERAL) {
			viewDefinition += NEWLINE + WITH + SPACE + CASCADED + SPACE + CHECK
					+ SPACE + OPTION;
		} else if (checkType == CheckType.LOCAL_LITERAL) {
			viewDefinition += NEWLINE + WITH + SPACE + LOCAL + SPACE + CHECK
					+ SPACE + OPTION;
		}
		return viewDefinition;
	}

	public String createIndex(Index index, boolean quoteIdentifiers,
			boolean qualifyNames) {
		String statement = CREATE + SPACE;
		if (index.isUnique()) {
			statement += UNIQUE + SPACE;
		}
		statement += INDEX + SPACE
				+ getName(index, quoteIdentifiers, false) + SPACE + ON
				+ SPACE
				+ getName(index.getTable(), quoteIdentifiers, qualifyNames)
				+ SPACE + LEFT_PARENTHESIS
				+ getIndexKeyColumns(index, quoteIdentifiers)
				+ RIGHT_PARENTHESIS;

		return statement;
	}

	public String createTrigger(Trigger trigger, boolean quoteIdentifiers,
			boolean qualifyNames) {
		String statement = CREATE + SPACE + TRIGGER + SPACE
				+ getName(trigger, quoteIdentifiers, qualifyNames) + SPACE;

		final ActionTimeType actionTime = trigger.getActionTime();
		if (actionTime == ActionTimeType.AFTER_LITERAL) {
			statement += AFTER;
		} else if (actionTime == ActionTimeType.BEFORE_LITERAL) {
			statement += BEFORE;
		} else if (actionTime == ActionTimeType.INSTEADOF_LITERAL) {
			statement += INSTEAD_OF;
		}
		statement += SPACE;

		if (trigger.isDeleteType()) {
			statement += DELETE;
		} else if (trigger.isInsertType()) {
			statement += INSERT;
		} else if (trigger.isUpdateType()) {
			statement += UPDATE;
			Collection updateColumns = trigger.getTriggerColumn();
			if (!updateColumns.isEmpty()) {
				statement += SPACE + OF + SPACE;
				Iterator it = updateColumns.iterator();
				while (it.hasNext()) {
					Column column = (Column) it.next();
					statement += column.getName();
					if (it.hasNext()) {
						statement += COMMA + SPACE;
					}
				}
			}
		}

		statement += SPACE
				+ ON
				+ SPACE
				+ getName(trigger.getSubjectTable(), quoteIdentifiers,
						qualifyNames) + NEWLINE;

		final String newRow = trigger.getNewRow();
		final String oldRow = trigger.getOldRow();
//		final String newTable = trigger.getNewTable();
//		final String oldTable = trigger.getOldTable();

		if (newRow != null && newRow.length() != 0) {
			statement += REFERENCING + SPACE + NEW + SPACE + AS + SPACE
					+ newRow + NEWLINE;
		}
		if (oldRow != null && oldRow.length() != 0) {
			statement += REFERENCING + SPACE + OLD + SPACE + AS + SPACE
					+ oldRow + NEWLINE;
		}

		if (trigger.getActionGranularity() == ActionGranularityType.ROW_LITERAL) {
			statement += FOR + SPACE + EACH + SPACE + ROW;
		} else {
			statement += FOR + SPACE + EACH + SPACE + STATEMENT;
		}

		Iterator it = trigger.getActionStatement().iterator();
		while (it.hasNext()) {
			SQLStatement s = (SQLStatement) it.next();
			statement += s.getSQL();
		}

		return statement;
	}

	protected String getName(Index index, boolean quoteIdentifiers,
			boolean qualifyNames) {
		String indexName = index.getName();
		String dbName = null;
		if (quoteIdentifiers) {
			indexName = this.getQuotedString(indexName);
			if (qualifyNames) {
				dbName = index.getSchema().getDatabase().getName();
				dbName = this.getQuotedString(dbName);
				indexName = dbName + DOT + indexName;
			}
		}
		return indexName;
	}

}
