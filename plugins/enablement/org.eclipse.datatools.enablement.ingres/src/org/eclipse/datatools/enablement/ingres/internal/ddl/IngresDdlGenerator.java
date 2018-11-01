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
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlGenerator;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;

/**
 * DDL generator for use with Ingres databases.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresDdlGenerator extends GenericDdlGenerator {

	private IngresDdlBuilder builder;

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlGenerator#dropStatements(org.eclipse.datatools.modelbase.sql.schema.SQLObject[], boolean, boolean, org.eclipse.core.runtime.IProgressMonitor, int)
	 */
	protected String[] dropStatements(SQLObject[] elements,
			boolean quoteIdentifiers, boolean qualifyNames,
			IProgressMonitor progressMonitor, int task) {
		IngresDdlScript script = new IngresDdlScript();
		if (this.builder == null) {
			this.builder = new IngresDdlBuilder();
		}

		EngineeringOption[] options = this.getSelectedOptions(elements);

		Iterator it = this.getAllContainedDisplayableElementSet(elements)
				.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			if (o instanceof PersistentTable) {
				if (!this.generateTables(options))
					continue;
				String statement = builder.dropTable((PersistentTable) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addDropTableStatement(statement);
			} else if (o instanceof ViewTable) {
				if (!this.generateViews(options))
					continue;
				String statement = builder.dropView((ViewTable) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addDropViewStatement(statement);
			} else if (o instanceof Trigger) {
				if (!this.generateTriggers(options))
					continue;
				String statement = builder.dropTrigger((Trigger) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addDropTriggerStatement(statement);
			} else if (o instanceof Procedure) {
				// Handle procedures
				// TODO include procedures in ddl gen dialog
				// if (!this.generateProcedures(options))
				// continue;
				String statement = builder.dropProcedures((Procedure) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addDropProcedureStatement(statement);
			} else if (o instanceof CheckConstraint) {
				if (!this.generateCKConstraints(options))
					continue;
				String statement = builder.dropTableConstraint(
						(CheckConstraint) o, quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addAlterTableDropConstraintStatement(statement);
			} else if (o instanceof UniqueConstraint) {
				if (!this.generatePKConstraints(options))
					continue;
				String statement = builder.dropTableConstraint(
						(UniqueConstraint) o, quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addAlterTableDropConstraintStatement(statement);
			} else if (o instanceof ForeignKey) {
				if (!this.generateFKConstraints(options))
					continue;
				String statement = builder.dropTableConstraint((ForeignKey) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addAlterTableDropForeignKeyStatement(statement);
			} else if (o instanceof Index) {
				if (!this.generateIndexes(options))
					continue;
				String statement = builder.dropIndex((Index) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addDropIndexStatement(statement);
			} else if (o instanceof IngresSynonym) {
				// TODO include synonyms in ddl gen dialog
				// if (!this.generateViews(options))
				// continue;
				String statement = builder.dropSynonym((IngresSynonym) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addDropSynonymStatement(statement);
			} else if (o instanceof IngresDBEvent) {
				// TODO include db events in ddl gen dialog
				// if (!this.generateViews(options))
				// continue;
				String statement = builder.dropDBEvent((IngresDBEvent) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addDropDBEventStatement(statement);
			} else if (o instanceof Sequence) {
				// TODO include db events in ddl gen dialog
				// if (!this.generateViews(options))
				// continue;
				String statement = builder.dropSequence((Sequence) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addDropSequenceStatement(statement);
			}
		}
		return script.getStatements();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlGenerator#createStatements(org.eclipse.datatools.modelbase.sql.schema.SQLObject[],
	 *      boolean, boolean, org.eclipse.core.runtime.IProgressMonitor, int)
	 */
	protected String[] createStatements(SQLObject[] elements,
			boolean quoteIdentifiers, boolean qualifyNames,
			IProgressMonitor progressMonitor, int task) {
		IngresDdlScript script = new IngresDdlScript();
		if (this.builder == null) {
			this.builder = new IngresDdlBuilder();
		}

		EngineeringOption[] options = this.getSelectedOptions(elements);

		Set allObjectss = getAllContainedDisplayableElementSet(elements);

		// it's a single statement or a script?
		boolean singleObject = true;
		if (allObjectss.size() > 1) {
			singleObject = false;
		}

		Iterator it = allObjectss.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			if (o instanceof PersistentTable) {
				if (!this.generateTables(options))
					continue;
				String statement = builder.createTable((PersistentTable) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addCreateTableStatement(statement);
			} else if (o instanceof ViewTable) {
				if (!this.generateViews(options))
					continue;
				String statement = builder.createView((ViewTable) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addCreateViewStatement(statement);
			} else if (o instanceof Trigger) {
				if (!this.generateTriggers(options))
					continue;
				String statement = builder.createTrigger((Trigger) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addCreateTriggerStatement(statement);
			} else if (o instanceof Procedure) {
				// Handle procedures
				// TODO include procedures in ddl gen dialog
				// if (!this.generateProcedures(options))
				// continue;

				// create all procedures with an empty body, afterwards recreate
				// every procedure (avoids dependency errors)

				String stmtEmpty = null;
				String stmtDrop = null;
				if (!singleObject) {
					stmtEmpty = builder.createProcedureEmptyBody((Procedure) o,
							quoteIdentifiers, qualifyNames);
					stmtDrop = builder.dropProcedure((Procedure) o,
							quoteIdentifiers, qualifyNames);
				}
				String statement = builder.createProcedures((Procedure) o,
						quoteIdentifiers, qualifyNames);
				if (stmtEmpty != null) {
					script.addCreateEmptyProcedureStatement(stmtEmpty);
				}
				if (stmtDrop != null) {
					script.addCreateProcedureStatement(stmtDrop);
				}
				if (statement != null) {
					script.addCreateProcedureStatement(statement);
				}
			} else if (o instanceof CheckConstraint) {
				if (!this.generateCKConstraints(options))
					continue;
				String statement = builder.addCheckConstraint(
						(CheckConstraint) o, quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addAlterTableAddConstraintStatement(statement);
			} else if (o instanceof UniqueConstraint) {
				if (!this.generatePKConstraints(options))
					continue;
				String statement = builder.addUniqueConstraint(
						(UniqueConstraint) o, quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addAlterTableAddConstraintStatement(statement);
			} else if (o instanceof ForeignKey) {
				if (!this.generateFKConstraints(options))
					continue;
				String statement = builder.addForeignKey((ForeignKey) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addAlterTableAddForeignKeyStatement(statement);
			} else if (o instanceof Index) {
				if (!this.generateIndexes(options))
					continue;
				String statement = builder.createIndex((Index) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addCreateIndexStatement(statement);
			} else if (o instanceof IngresSynonym) {
				// TODO include synonyms in ddl gen dialog
				// if (!this.generateViews(options))
				// continue;
				String statement = builder.createSynonym((IngresSynonym) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addCreateSynonymStatement(statement);
			} else if (o instanceof IngresDBEvent) {
				// TODO include db events in ddl gen dialog
				// if (!this.generateViews(options))
				// continue;
				String statement = builder.createDBEvent((IngresDBEvent) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addCreateDBEventStatement(statement);
			} else if (o instanceof Sequence) {
				// TODO include sequences in ddl gen dialog
				// if (!this.generateViews(options))
				// continue;
				String statement = builder.createSequence((Sequence) o,
						quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addCreateSequenceStatement(statement);
			}
		}
		return script.getStatements();
	}

}
