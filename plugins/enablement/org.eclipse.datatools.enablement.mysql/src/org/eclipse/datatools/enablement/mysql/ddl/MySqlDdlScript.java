 /*******************************************************************************
  * Copyright (c) 2005 Versant Corporation and others.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License 2.0
  * which accompanies this distribution, and is available at
  * https://www.eclipse.org/legal/epl-2.0/
  * 
  * Contributors:
  *     Versant Corporation - initial API and implementation
  *******************************************************************************/
 package org.eclipse.datatools.enablement.mysql.ddl;

import java.util.ArrayList;

/**
 * This class takes all the sql scripts and puts them in the right order, for
 * execution.
 */
public class MySqlDdlScript {
	protected ArrayList dropForeignKeyStatements = new ArrayList();
	protected ArrayList dropTableStatements = new ArrayList();
	protected ArrayList dropViewStatements = new ArrayList();
	protected ArrayList dropConstraintStatements = new ArrayList();
	protected ArrayList dropTriggerStatements = new ArrayList();
	protected ArrayList dropIndexStatements = new ArrayList();
	protected ArrayList createTableStatements = new ArrayList();
	protected ArrayList alterTableStatements = new ArrayList();
	protected ArrayList createViewStatements = new ArrayList();
	protected ArrayList addForeignKeyStatements = new ArrayList();
	protected ArrayList createIndexStatements = new ArrayList();
	protected ArrayList createTriggerStatements = new ArrayList();
	protected ArrayList addConstraintStatements = new ArrayList();

	public void addDropTableStatement(String statement) {
		dropTableStatements.add(statement);
	}

	public void addCreateTableStatement(String statement) {
		createTableStatements.add(statement);
	}

	public void addAlterTableStatement(String statement) {
		createTableStatements.add(statement);
	}

	public void addDropViewStatement(String statement) {
		dropViewStatements.add(statement);
	}

	public void addCreateViewStatement(String statement) {
		createViewStatements.add(statement);
	}

	public void addAlterTableDropForeignKeyStatement(String statement) {
		dropForeignKeyStatements.add(statement);
	}

	public void addAlterTableAddForeignKeyStatement(String statement) {
		addForeignKeyStatements.add(statement);
	}

	public void addDropIndexStatement(String statement) {
		dropIndexStatements.add(statement);
	}

	public void addCreateIndexStatement(String statement) {
		createIndexStatements.add(statement);
	}

	public void addDropTriggerStatement(String statement) {
		dropTriggerStatements.add(statement);
	}

	public void addCreateTriggerStatement(String statement) {
		createTriggerStatements.add(statement);
	}

	public void addAlterTableDropConstraintStatement(String statement) {
		dropConstraintStatements.add(statement);
	}

	public void addAlterTableAddConstraintStatement(String statement) {
		addConstraintStatements.add(statement);
	}

	public String[] getStatements() {
		ArrayList script = new ArrayList();
		script.addAll(dropTriggerStatements);
		script.addAll(dropForeignKeyStatements);
		script.addAll(dropConstraintStatements);
		script.addAll(dropIndexStatements);
		script.addAll(dropViewStatements);
		script.addAll(dropTableStatements);
		script.addAll(createTableStatements);
		script.addAll(alterTableStatements);
		script.addAll(createViewStatements);
		script.addAll(createIndexStatements);
		script.addAll(addConstraintStatements);
		script.addAll(addForeignKeyStatements);
		script.addAll(createTriggerStatements);

		String[] scripts = new String[script.size()];
		script.toArray(scripts);
		return scripts;
	}

}
