/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ddl;

import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlBuilder;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCPrimaryKey;
import org.eclipse.datatools.enablement.ingres.internal.catalog.IngresCatalogCheckConstraint;
import org.eclipse.datatools.enablement.ingres.internal.catalog.IngresCatalogView;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresTrigger;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;

/**
 * DDL builder for use with Ingres databases.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresDdlBuilder extends GenericDdlBuilder {

	protected static final String RULE = "RULE";

	protected static final String SYNONYM = "SYNONYM";

	protected static final String DB_EVENT = "DBEVENT";

	protected static final String SEQUENCE = "SEQUENCE";

	private static final String START = "START";

	private static final String BY = "BY";

	private static final String INCREMENT = "INCREMENT";

	private static final String MAXVALUE = "MAXVALUE";

	private static final String MINVALUE = "MINVALUE";

	private static final String CACHE = "CACHE";

	private static final String CYCLE = "CYCLE";

	private static final String ORDER = "ORDER";

	private static final String BEGIN = "BEGIN";

	private static final String SEMICOLON = ";";

	private static final String END = "END";

	private static final String RETURN = "RETURN";

	public String createProcedures(Procedure procedure,
			boolean quoteIdentifiers, boolean qualifyNames) {
		return procedure.getSource().getBody();
	}

	public String createProcedureEmptyBody(Procedure procedure,
			boolean quoteIdentifiers, boolean qualifyNames) {
		StringBuffer stmtBuf = new StringBuffer();
		stmtBuf.append(CREATE).append(SPACE).append(PROCEDURE).append(SPACE);
		stmtBuf.append(getName(procedure, quoteIdentifiers, qualifyNames));
		stmtBuf.append(SPACE).append(AS).append(SPACE).append(BEGIN).append(
				SPACE);
		stmtBuf.append(RETURN).append(SEMICOLON).append(SPACE).append(END);
		return stmtBuf.toString();
	}

	public String dropProcedures(Procedure trigger, boolean quoteIdentifiers,
			boolean qualifyNames) {
		return DROP + SPACE + PROCEDURE + SPACE
				+ getName(trigger, quoteIdentifiers, qualifyNames);
	}

	public String createTrigger(Trigger trigger, boolean quoteIdentifiers,
			boolean qualifyNames) {
		if (trigger instanceof IngresTrigger) {
			return ((IngresTrigger) trigger).getSource().getBody();
		}
		return super.createTrigger(trigger, quoteIdentifiers, qualifyNames);
	}

	public String dropTrigger(Trigger trigger, boolean quoteIdentifiers,
			boolean qualifyNames) {
		return DROP + SPACE + RULE + SPACE
				+ getName(trigger, quoteIdentifiers, qualifyNames);
	}

	public String createView(ViewTable view, boolean quoteIdentifiers,
			boolean qualifyNames) {
		if (view instanceof IngresCatalogView) {
			return ((IngresCatalogView) view).getSource().getBody();
		}
		return super.createView(view, quoteIdentifiers, qualifyNames);
	}

	protected String getIndexKeyColumns(Index index, boolean quoteIdentifiers) {
		StringBuffer columns = new StringBuffer();
		Iterator it = index.getIncludedMembers().iterator();
		if (it.hasNext()) {
			IndexMember m = (IndexMember) it.next();
			String columnName = m.getColumn().getName();
			if (quoteIdentifiers) {
				columnName = this.getDoubleQuotedString(columnName);
			}
			columns.append(columnName);
			columns.append(SPACE);
			columns.append(m.getIncrementType().getName());
		} else {
			// TODO report error
			return null;
		}

		while (it.hasNext()) {
			IndexMember m = (IndexMember) it.next();
			String columnName = m.getColumn().getName();
			if (quoteIdentifiers) {
				columnName = this.getDoubleQuotedString(columnName);
			}
			columns.append(COMMA);
			columns.append(SPACE);
			columns.append(columnName);
			columns.append(SPACE);
			columns.append(m.getIncrementType().getName());
		}
		return columns.toString();
	}

	public String createIndex(Index index, boolean quoteIdentifiers,
			boolean qualifyNames) {
		String result = null;
		if (!index.getName().startsWith("$")) {
			result = super.createIndex(index, quoteIdentifiers, qualifyNames);
		}
		return result;
	}

	public String dropIndex(Index index, boolean quoteIdentifiers,
			boolean qualifyNames) {
		String result = null;
		if (!index.getName().startsWith("$")) {
			result = super.dropIndex(index, quoteIdentifiers, qualifyNames);
		}
		return result;
	}

	public String addForeignKey(ForeignKey foreignKey,
			boolean quoteIdentifiers, boolean qualifyNames) {
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
			// this.getEngineeringCallBack().writeMessage(MessageFormat.format(
			// GenericCatalogMessages.FE_PARENT_TABLLE_OR_KEY_DO_NOT_EXIST, new
			// Object[] { foreignKey.getName()}));
			return null;
		}

		String statement = ALTER
				+ SPACE
				+ TABLE
				+ SPACE
				+ getName(foreignKey.getBaseTable(), quoteIdentifiers,
						qualifyNames) + SPACE + ADD + SPACE
				+ getConstraintName(foreignKey, quoteIdentifiers) + SPACE
				+ FOREIGN_KEY + SPACE + LEFT_PARENTHESIS
				+ this.getKeyColumns(foreignKey, quoteIdentifiers)
				+ RIGHT_PARENTHESIS + NEWLINE;
		statement += TAB + REFERENCES + SPACE
				+ getName(parentTable, quoteIdentifiers, qualifyNames) + SPACE
				+ LEFT_PARENTHESIS + parentKey + RIGHT_PARENTHESIS;

		ReferentialActionType action = foreignKey.getOnDelete();
		if (action != ReferentialActionType.NO_ACTION_LITERAL) {
			statement += NEWLINE + TAB + ON + SPACE + DELETE + SPACE;
		}
		statement += getReferentialAction(action);

		action = foreignKey.getOnUpdate();
		if (action != ReferentialActionType.NO_ACTION_LITERAL) {
			statement += NEWLINE + TAB + ON + SPACE + UPDATE + SPACE;
		}
		statement += getReferentialAction(action);

		if (foreignKey.isDeferrable()) {
			statement += NEWLINE + TAB + getDeferrableClause(foreignKey);
		}
		return statement;
	}

	public String dropTableConstraint(TableConstraint constraint,
			boolean quoteIdentifiers, boolean qualifyNames) {
		if (constraint.getName().startsWith("$")) {
			return null;
		}
		return super.dropTableConstraint(constraint, quoteIdentifiers,
				qualifyNames);
	}

	public String createSequence(Sequence sequence, boolean quoteIdentifiers,
			boolean qualifyNames) {
		StringBuffer stmtBuf = new StringBuffer();
		stmtBuf.append(CREATE).append(SPACE).append(SEQUENCE).append(SPACE);
		stmtBuf.append(getName(sequence, quoteIdentifiers)).append(SPACE);
		if (sequence.getIdentity() instanceof IngresIdentitySpecifier) {
			IngresIdentitySpecifier options = (IngresIdentitySpecifier) sequence
					.getIdentity();

			// AS data type
			stmtBuf.append(AS).append(SPACE).append(options.getDataType())
					.append(SPACE);
			if ("decimal".equalsIgnoreCase(options.getDataType())) {
				stmtBuf.append(LEFT_PARENTHESIS);
				stmtBuf.append(options.getSeqPrecision());
				stmtBuf.append(COMMA);
				stmtBuf.append("0");
				stmtBuf.append(RIGHT_PARENTHESIS);
				stmtBuf.append(SPACE);
			}

			// START WITH number
			stmtBuf.append(START).append(SPACE).append(WITH).append(SPACE);
			stmtBuf.append(options.getStartValue()).append(SPACE);

			// INCREMENT BY number
			stmtBuf.append(INCREMENT).append(SPACE).append(BY).append(SPACE);
			stmtBuf.append(options.getIncrement()).append(SPACE);

			// MAXVALUE number | NO MAXVALUE
			if (options.getMaximumOption() != null && options.getMaximumOption().booleanValue()) {
				stmtBuf.append(MAXVALUE).append(SPACE);
				stmtBuf.append(options.getMaximum()).append(SPACE);
			} else {
				stmtBuf.append(NO).append(SPACE).append(MAXVALUE).append(SPACE);
			}

			// MINVALUE number | NO MINVALUE
			if (options.getMinimumOption() != null && options.getMinimumOption().booleanValue()) {
				stmtBuf.append(MINVALUE).append(SPACE);
				stmtBuf.append(options.getMinimum()).append(SPACE);
			} else {
				stmtBuf.append(NO).append(SPACE).append(MINVALUE).append(SPACE);
			}

			// CACHE number | NO CACHE
			if (options.getCacheOption() != null && options.getCacheOption().booleanValue()) {
				stmtBuf.append(CACHE).append(SPACE);
				stmtBuf.append(options.getCacheSize()).append(SPACE);
			} else {
				stmtBuf.append(NO).append(SPACE).append(CACHE).append(SPACE);
			}

			// CYCLE | NO CYCLE
			if (options.isCycleOption()) {
				stmtBuf.append(CYCLE).append(SPACE);
			} else {
				stmtBuf.append(NO).append(SPACE).append(CYCLE).append(SPACE);
			}

			// ORDER | NO ORDER
			if (options.getOrderOption() != null && options.getOrderOption().booleanValue()) {
				stmtBuf.append(ORDER).append(SPACE);
			} else {
				stmtBuf.append(NO).append(SPACE).append(ORDER).append(SPACE);
			}

		}

		return stmtBuf.toString();
	}

	public String dropSequence(Sequence sequence, boolean quoteIdentifiers,
			boolean qualifyNames) {
		StringBuffer stmtBuf = new StringBuffer();
		stmtBuf.append(DROP).append(SPACE).append(SEQUENCE).append(SPACE)
				.append(getName(sequence, quoteIdentifiers));
		return stmtBuf.toString();
	}

	protected String getAddUniqueConstraintClause(UniqueConstraint constraint,
			boolean quoteIdentifiers) {
		String constraintName = getConstraintName(constraint, quoteIdentifiers);

		String text = ADD + SPACE + constraintName + SPACE
				+ getUniqueConstraintType(constraint) + SPACE
				+ LEFT_PARENTHESIS
				+ this.getKeyColumns(constraint, quoteIdentifiers)
				+ RIGHT_PARENTHESIS;

		if (constraint.isDeferrable()) {
			text += SPACE + getDeferrableClause(constraint);
		}
		return text;
	}

	protected String getUniqueConstraintType(UniqueConstraint constraint) {
        // XXX Workaround: JDBCUniqueConstraint extends PrimaryKeyImpl
		if(constraint instanceof JDBCPrimaryKey) {
            return PRIMARY_KEY;
        }
        return UNIQUE;
	}

	protected String getCheckConstraintClause(CheckConstraint constraint,
			boolean quoteIdentifiers) {
		String constraintName = getConstraintName(constraint, quoteIdentifiers);

		String text = constraintName + SPACE;

		if (constraint instanceof IngresCatalogCheckConstraint) {
			text += constraint.getSearchCondition().getSQL() + SPACE;
		} else {
			text += CHECK + SPACE + LEFT_PARENTHESIS
					+ constraint.getSearchCondition().getSQL()
					+ RIGHT_PARENTHESIS + SPACE;
		}

		if (constraint.isDeferrable()) {
			text += getDeferrableClause(constraint);
		}
		return text;
	}

	public String createSynonym(IngresSynonym synonym,
			boolean quoteIdentifiers, boolean qualifyNames) {
		StringBuffer stmtBuf = new StringBuffer();
		stmtBuf.append(CREATE).append(SPACE).append(SYNONYM).append(SPACE);
		stmtBuf.append(getName(synonym, quoteIdentifiers));
		stmtBuf.append(SPACE).append(FOR).append(SPACE);
		stmtBuf.append(getSynonymTableName(synonym, quoteIdentifiers,
				qualifyNames));

		return stmtBuf.toString();
	}

	public String dropSynonym(IngresSynonym synonym, boolean quoteIdentifiers,
			boolean qualifyNames) {
		StringBuffer stmtBuf = new StringBuffer();
		stmtBuf.append(DROP).append(SPACE).append(SYNONYM).append(SPACE);
		stmtBuf.append(getName(synonym, quoteIdentifiers));

		return stmtBuf.toString();
	}

	public String createDBEvent(IngresDBEvent event, boolean quoteIdentifiers,
			boolean qualifyNames) {
		StringBuffer stmtBuf = new StringBuffer();
		stmtBuf.append(CREATE).append(SPACE).append(DB_EVENT).append(SPACE);
		stmtBuf.append(getName(event, quoteIdentifiers));
		return stmtBuf.toString();
	}

	public String dropDBEvent(IngresDBEvent event, boolean quoteIdentifiers,
			boolean qualifyNames) {
		StringBuffer stmtBuf = new StringBuffer();
		stmtBuf.append(DROP).append(SPACE).append(DB_EVENT).append(SPACE);
		stmtBuf.append(getName(event, quoteIdentifiers));
		return stmtBuf.toString();
	}

	protected String getSynonymTableName(IngresSynonym synonym,
			boolean quoteIdentifiers, boolean qualifyNames) {
		String tableName = synonym.getTableName();
		String schemaName = synonym.getSchema().getName();

		if (quoteIdentifiers) {
			tableName = this.getDoubleQuotedString(tableName);
			schemaName = this.getQuotedIdentifierString(synonym.getSchema());
		}

		if (qualifyNames) {
			tableName = schemaName + DOT + tableName;
		}

		return tableName;
	}

	protected String getName(IngresSynonym synonym, boolean quoteIdentifiers) {
		String name = synonym.getName();

		if (quoteIdentifiers) {
			name = this.getQuotedIdentifierString(synonym);
		}

		return name;
	}

	protected String getName(IngresDBEvent event, boolean quoteIdentifiers) {
		String name = event.getName();

		if (quoteIdentifiers) {
			name = this.getQuotedIdentifierString(event);
		}

		return name;
	}

	protected String getName(Sequence sequence, boolean quoteIdentifiers) {
		String name = sequence.getName();

		if (quoteIdentifiers) {
			name = this.getQuotedIdentifierString(sequence);
		}

		return name;
	}

	protected String getName(TableConstraint constraint,
			boolean quoteIdentifiers) {
		String name = constraint.getName();

		if (name.startsWith("$")) {
			name = "";
		} else {
			if (quoteIdentifiers) {
				name = this.getDoubleQuotedString(name);
			}
		}

		return name;
	}

	protected String getConstraintName(TableConstraint constraint,
			boolean quoteIdentifiers) {
		String constraintName = getName(constraint, quoteIdentifiers);
		if (constraintName != null && constraintName.trim().length() > 0) {
			constraintName = CONSTRAINT + SPACE + constraintName;
		}

		return constraintName;
	}

}
