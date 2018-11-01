/*******************************************************************************
 * Copyright (c) 2008 NexB Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Anton Safonov and Ahti Kitsik - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.msft.internal.sqlserver.ddl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlBuilder;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.GenericCatalogMessages;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;

import com.ibm.icu.text.MessageFormat;

public class SqlServer2000DdlBuilder extends GenericDdlBuilder {

	protected static final String ACTION = "ACTION"; //$NON-NLS-1$
	protected static final String ANSI_NULLS = "ANSI_NULLS"; //$NON-NLS-1$
	protected static final String ANSI_PADDING = "ANSI_PADDING"; //$NON-NLS-1$
	protected static final String OFF = "OFF";//$NON-NLS-1$
	protected static final String OUT = "OUT";//$NON-NLS-1$
	protected static final String QUOTED_IDENTIFIER = "QUOTED_IDENTIFIER"; //$NON-NLS-1$

	private Set elements;

	public String createTable(BaseTable table, boolean quoteIdentifiers, boolean qualifyNames) {
		StringBuffer statement = new StringBuffer();
		boolean isTemp = table instanceof TemporaryTable;

		statement.append(CREATE).append(SPACE);
		if (isTemp) {
			if (((TemporaryTable) table).isLocal()) {
				statement.append(LOCAL).append(SPACE);
			} else {
				statement.append(GLOBAL).append(SPACE);
			}
			statement.append(TEMPORARY).append(SPACE);
		}
		statement.append(TABLE).append(SPACE).append(getName(table, quoteIdentifiers, qualifyNames)).append(SPACE);

		statement.append(LEFT_PARENTHESIS).append(NEWLINE);

		List items = new ArrayList();
		items.addAll(table.getColumns());
		items.addAll(getTableConstraints(table));
		Iterator it = items.iterator();
		boolean first = true;
		while (it.hasNext()) {
			Object item = it.next();
			String clause;
			if (item instanceof Column) {
				clause = getColumnString((Column) item, quoteIdentifiers);
			} else if (item instanceof CheckConstraint) {
				clause = getCheckConstraintClause((CheckConstraint) item, quoteIdentifiers);
			} else if (item instanceof UniqueConstraint) {
				clause = getUniqueConstraintClause((UniqueConstraint) item, quoteIdentifiers);
			} else {
				continue;
			}

			if (!first) {
				statement.append(COMMA);
				statement.append(NEWLINE);
			} else {
				first = false;
			}

			statement.append(TAB).append(clause);
		}

		statement.append(NEWLINE).append(RIGHT_PARENTHESIS);

		if (isTemp) {
			statement.append(NEWLINE).append(TAB).append(ON_COMMIT).append(SPACE);
			if (((TemporaryTable) table).isDeleteOnCommit()) {
				statement.append(DELETE).append(SPACE);
			} else {
				statement.append(PRESERVE).append(SPACE);
			}
			statement.append(ROWS);
		}

		return statement.append(NEWLINE).toString();
	}

	private Set getTableConstraints(Table table) {
		Set constraints = new HashSet(elements.size());
		Iterator it = elements.iterator();
		while (it.hasNext()) {
			Object elem = it.next();
			if (elem instanceof TableConstraint && ((TableConstraint) elem).getBaseTable() == table) {
				constraints.add(elem);
			}
		}
		return constraints;
	}

	public String createProcedure(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames) {
		StringBuffer statement = new StringBuffer();

		// CREATE PROCEDURE procedure_name
		statement.append(CREATE).append(SPACE).append(PROCEDURE).append(SPACE).append(getName(procedure, quoteIdentifiers, qualifyNames)).append(SPACE);

		// parameters
		// SQL server doesn't like " as quotation identifier.
		statement.append(getParameterListClause(procedure, false));

		// begin characteristics
		// language
		if (procedure.getLanguage() != null && procedure.getLanguage().length() > 0) {
			statement.append(TAB).append(LANGUAGE).append(SPACE).append(procedure.getLanguage()).append(NEWLINE);
		}

		// parameter style
		if (procedure.getParameterStyle() != null && procedure.getParameterStyle().length() > 0) {
			statement.append(TAB).append(PARAMETER_STYLE).append(SPACE).append(procedure.getParameterStyle()).append(NEWLINE);
		}

		// dynamic result sets
		if (procedure.getMaxResultSets() > 0) {
			statement.append(TAB).append(DYNAMIC_RESULT_SETS).append(SPACE).append(procedure.getMaxResultSets()).append(NEWLINE);
		}
		// end characteristics

		// body
		if (procedure.getSource() != null) {
			String body = procedure.getSource().getBody();
			if (body != null && body.length() > 0) {
				statement.append(AS).append(NEWLINE).append(body).append(NEWLINE);
			}
		}

		return statement.append(NEWLINE).toString();
	}

	protected String getParameterListClause(Routine routine, boolean quoteIdentifiers) {
		StringBuffer statement = new StringBuffer();
		statement.append(NEWLINE);
		for (Iterator it = routine.getParameters().iterator(); it.hasNext();) {
			Parameter param = (Parameter) it.next();
			String name = param.getName();
			if (name.equals("@RETURN_VALUE"))
				continue;
			ParameterMode mode = param.getMode();

			// formatting
			statement.append(TAB).append(TAB);

			// name
			if (name != null && name.length() > 0) {
				statement.append(quoteIdentifiers ? getQuotedIdentifierString(param) : name).append(SPACE);
			}

			// type
			statement.append(getDataTypeString(param, routine.getSchema()));

			// mode (IN, INOUT, OUT)
			if (mode.getName().toLowerCase().equals("inout") || mode.getName().toLowerCase().equals("out")) {
				statement.append(SPACE).append(OUT).append(SPACE);
			}

			// TODO: default value

			// locator
			// TODO: anything?

			if (it.hasNext()) {
				// prepare for the next parameter
				statement.append(COMMA);
				statement.append(NEWLINE);
			}
		}
		statement.append(NEWLINE);

		return statement.toString();
	}

	public String dropIndex(Index index, boolean quoteIdentifiers, boolean qualifyNames) {
		return DROP + SPACE + INDEX + SPACE + getName(index.getTable(), quoteIdentifiers, qualifyNames) + DOT + getName(index, quoteIdentifiers, false)
				+ NEWLINE;
	}

	public String createIndex(Index index, boolean quoteIdentifiers, boolean qualifyNames) {
		String statement = CREATE + SPACE;
		if (index.isUnique()) {
			statement += UNIQUE + SPACE;
		}
		statement += INDEX + SPACE + getName(index, quoteIdentifiers, false) + SPACE + ON + SPACE + getName(index.getTable(), quoteIdentifiers, qualifyNames)
				+ SPACE + LEFT_PARENTHESIS + getIndexKeyColumns(index, quoteIdentifiers) + RIGHT_PARENTHESIS;

		return statement + NEWLINE;
	}

	
	public final String asOnOff(final boolean flag) {
		return flag ? "ON" : "OFF";
	}

	public void setElements(Set elements) {
		this.elements = elements;
	}

	public final void clear() {
		this.elements = null;
	}

	protected String getAddUniqueConstraintClause(UniqueConstraint constraint, boolean quoteIdentifiers) {
		return ADD + SPACE + getUniqueConstraintClause(constraint, quoteIdentifiers) + NEWLINE;
	}

	protected String getUniqueConstraintClause(UniqueConstraint constraint, boolean quoteIdentifiers) {
		String text = CONSTRAINT + SPACE + getName(constraint, quoteIdentifiers) + SPACE + getUniqueConstraintType(constraint) + SPACE + LEFT_PARENTHESIS
				+ this.getKeyColumns(constraint, quoteIdentifiers) + RIGHT_PARENTHESIS;

		Index index = findIndexByConstraint(constraint);

		// TODO is it for MS SQL Server?
		if (constraint.isDeferrable()) {
			text += SPACE + getDeferrableClause(constraint);
		}
		return text + NEWLINE;
	}

	public ReferenceConstraint findConstraintByIndex(Index index) {
		Iterator it = this.elements.iterator();
		while (it.hasNext()) {
			Object elem = it.next();
			if (elem instanceof ReferenceConstraint && ((ReferenceConstraint) elem).getName().equals(index.getName())) {
				return (ReferenceConstraint) elem;
			}
		}
		return null;
	}

	public Index findIndexByConstraint(ReferenceConstraint constraint) {
		Iterator it = this.elements.iterator();
		while (it.hasNext()) {
			Object elem = it.next();
			if (elem instanceof Index && ((Index) elem).getName().equals(constraint.getName())) {
				return (Index) elem;
			}
		}
		return null;
	}

	public String createUserDefinedType(UserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames) {
		return super.createUserDefinedType(type, quoteIdentifiers, qualifyNames);
	}

	public String addForeignKey(ForeignKey foreignKey, boolean quoteIdentifiers, boolean qualifyNames) {
		UniqueConstraint uniqueConstraint = foreignKey.getUniqueConstraint();
		Index index = foreignKey.getUniqueIndex();
		Table parentTable = null;
		String parentKey = null;
		if (uniqueConstraint != null) {
			parentTable = uniqueConstraint.getBaseTable();
			parentKey = this.getKeyColumns(uniqueConstraint, quoteIdentifiers);
		} else if (index != null) {
			parentTable = index.getTable();
			parentKey = this.getParentKeyColumns(index, quoteIdentifiers);
		}
		if (parentTable == null) {
			this.getEngineeringCallBack().writeMessage(
					MessageFormat.format(GenericCatalogMessages.FE_PARENT_TABLLE_OR_KEY_DO_NOT_EXIST, new Object[] { foreignKey.getName() }));
			return null;
		}

		String statement = ALTER + SPACE + TABLE + SPACE + getName(foreignKey.getBaseTable(), quoteIdentifiers, qualifyNames) + SPACE + ADD + SPACE
				+ CONSTRAINT + SPACE + getName(foreignKey, quoteIdentifiers) + SPACE + FOREIGN_KEY + SPACE + LEFT_PARENTHESIS
				+ this.getKeyColumns(foreignKey, quoteIdentifiers) + RIGHT_PARENTHESIS + NEWLINE;
		statement += TAB + REFERENCES + SPACE + getName(parentTable, quoteIdentifiers, qualifyNames) + SPACE + LEFT_PARENTHESIS + parentKey + RIGHT_PARENTHESIS;

		ReferentialActionType action = foreignKey.getOnDelete();
		if (action != ReferentialActionType.RESTRICT_LITERAL && action != ReferentialActionType.NO_ACTION_LITERAL) {
			statement += NEWLINE + TAB + ON + SPACE + DELETE + SPACE;
			statement += getReferentialAction(action);
		}

		action = foreignKey.getOnUpdate();
		if (action != ReferentialActionType.RESTRICT_LITERAL && action != ReferentialActionType.NO_ACTION_LITERAL) {
			statement += NEWLINE + TAB + ON + SPACE + UPDATE + SPACE;
			statement += getReferentialAction(action);
		}

		if (foreignKey.isDeferrable()) {
			statement += NEWLINE + TAB + getDeferrableClause(foreignKey);
		}
		return statement + NEWLINE;
	}

	public String addUniqueConstraint(Index constraint, boolean quoteIdentifiers, boolean qualifyNames) {
		String statement = ALTER + SPACE + TABLE + SPACE + getName(constraint.getTable(), quoteIdentifiers, qualifyNames) + SPACE;
		statement += this.getAddUniqueConstraintClause(constraint, quoteIdentifiers);

		return statement + NEWLINE;
	}

	protected String getAddUniqueConstraintClause(Index constraint, boolean quoteIdentifiers) {
		return ADD + SPACE + getUniqueConstraintClause(constraint, quoteIdentifiers) + NEWLINE;
	}

	protected String getUniqueConstraintClause(Index constraint, boolean quoteIdentifiers) {
		String text = CONSTRAINT + SPACE + getName(constraint, quoteIdentifiers, false) + SPACE + UNIQUE + SPACE + LEFT_PARENTHESIS
				+ this.getIndexKeyColumns(constraint, quoteIdentifiers) + RIGHT_PARENTHESIS;


		return text + NEWLINE;
	}

	public String dropTable(BaseTable table, boolean quoteIdentifiers, boolean qualifyNames) {
		return DROP + SPACE + TABLE + SPACE + getName(table, quoteIdentifiers, qualifyNames) + NEWLINE;
	}

	public String dropTableConstraint(Index uniqueIndex, boolean quoteIdentifiers, boolean qualifyNames) {
		return ALTER + SPACE + TABLE + SPACE + getName(uniqueIndex.getTable(), quoteIdentifiers, qualifyNames)
				+ " DROP CONSTRAINT " + getName(uniqueIndex, quoteIdentifiers, false) + NEWLINE; //$NON-NLS-1$
	}

	public String dropTableConstraint(TableConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames) {
		return super.dropTableConstraint(constraint, quoteIdentifiers, qualifyNames) + NEWLINE;
	}

	/**
	 * http://msdn.microsoft.com/en-us/library/ms191203.aspx
	 * http://msdn.microsoft.com/en-us/library/ms190707.aspx
	 * http://msdn.microsoft.com/en-us/library/ms190356.aspx
	 */
	public String[] createSetOptions(SQLObject[] elements) {
		String[] setOptions = new String[3];

		setOptions[0] = SET + SPACE + ANSI_NULLS + SPACE + ON + NEWLINE;
		setOptions[1] = SET + SPACE + QUOTED_IDENTIFIER + SPACE + ON + NEWLINE;
		setOptions[2] = SET + SPACE + ANSI_PADDING + SPACE + ON + NEWLINE;

		return setOptions;
	}

	public String[] createUnsetOptions(SQLObject[] elements) {
		String[] unsetOptions = new String[1];

		unsetOptions[0] = SET + SPACE + ANSI_PADDING + SPACE + OFF + NEWLINE;

		return unsetOptions;
	}

}
