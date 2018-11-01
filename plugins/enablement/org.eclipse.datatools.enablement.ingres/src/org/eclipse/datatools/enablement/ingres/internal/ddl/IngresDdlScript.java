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

import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlScript;

/**
 * DDL script for use with Ingres databases.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresDdlScript extends GenericDdlScript {

	protected Vector createProcedureStatements = new Vector();
	protected Vector createEmptyProcedureStatements = new Vector();
	protected Vector dropProcedureStatements = new Vector();
	protected Vector createSynonymStatements = new Vector();
	protected Vector dropSynonymStatements = new Vector();
	protected Vector createDBEventStatements = new Vector();
	protected Vector dropDBEventStatements = new Vector();
	protected Vector createSequencesStatements = new Vector();
	protected Vector dropSequencesStatements = new Vector();

	public void addCreateProcedureStatement(String stmtCreate) {
		createProcedureStatements.add(stmtCreate);
	}

	public void addDropProcedureStatement(String statement) {
		dropProcedureStatements.add(statement);
	}

	public void addCreateSynonymStatement(String statement) {
		createSynonymStatements.add(statement);
	}

	public void addDropSynonymStatement(String statement) {
		dropSynonymStatements.add(statement);
	}

	public void addDropDBEventStatement(String statement) {
		dropDBEventStatements.add(statement);
	}

	public void addCreateDBEventStatement(String statement) {
		createDBEventStatements.add(statement);
	}

	public void addDropSequenceStatement(String statement) {
		dropSequencesStatements.add(statement);
	}

	public void addCreateSequenceStatement(String statement) {
		createSequencesStatements.add(statement);
	}

	public String[] getStatements() {
		Vector scriptVec = new Vector();

		scriptVec.addAll(dropForeignKeyStatements);
		scriptVec.addAll(dropConstraintStatements);
		scriptVec.addAll(dropIndexStatements);
		scriptVec.addAll(dropTriggerStatements);
		scriptVec.addAll(dropProcedureStatements);
		scriptVec.addAll(dropDBEventStatements);
		scriptVec.addAll(dropSynonymStatements);
		scriptVec.addAll(dropViewStatements);
		scriptVec.addAll(dropTableStatements);
		scriptVec.addAll(dropSequencesStatements);

		scriptVec.addAll(createSequencesStatements);
		scriptVec.addAll(createTableStatements);
		scriptVec.addAll(createViewStatements);
		scriptVec.addAll(createSynonymStatements);
		scriptVec.addAll(createDBEventStatements);

		scriptVec.addAll(createEmptyProcedureStatements);
		scriptVec.addAll(createProcedureStatements);

		scriptVec.addAll(createTriggerStatements);
		scriptVec.addAll(alterTableStatements);
		scriptVec.addAll(createIndexStatements);
		scriptVec.addAll(addConstraintStatements);
		scriptVec.addAll(addForeignKeyStatements);

		String[] scripts = new String[scriptVec.size()];
		scriptVec.copyInto(scripts);
		return scripts;
	}

	public void addCreateEmptyProcedureStatement(String statement) {
		createEmptyProcedureStatements.add(statement);
	}

}
