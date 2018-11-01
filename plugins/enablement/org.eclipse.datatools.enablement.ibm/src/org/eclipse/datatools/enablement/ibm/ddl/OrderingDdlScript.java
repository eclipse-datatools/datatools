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
package org.eclipse.datatools.enablement.ibm.ddl;

import java.util.Vector;

import org.eclipse.datatools.enablement.ibm.util.CompoundStatementsMap;

public abstract class OrderingDdlScript extends DdlScript {

    public void addDropTableStatements(String statement,Vector<String> statements) {
        this.orderedDropTableStatements.put(statement,statements);
    }

    public void addDropTableStatement(String statement) {
    	addDropTableStatements(statement,null);
    }

    public void addCreateTableStatements(String statement,Vector<String> statements) {
        this.orderedCreateTableStatements.put(statement,statements);
    }

    public void addCreateTableStatement(String statement) {
    	addCreateTableStatements(statement,null);
    }

    public void addDropViewStatements(String statement,Vector<String> statements) {
        this.orderedDropViewStatements.put(statement,statements);
    }

    public void addDropViewStatement(String statement) {
    	addDropViewStatements(statement,null);
    }

    public void addCreateViewStatements(String statement,Vector<String> statements) {
        this.orderedCreateViewStatements.put(statement,statements);
    }

    public void addCreateViewStatement(String statement) {
    	addCreateViewStatements(statement,null);
    }

    public void addDropRoutineStatements(String statement,Vector<String> statements) {
        this.orderedDropRoutineStatements.put(statement,statements);
    }

    public void addDropRoutineStatement(String statement) {
    	addDropRoutineStatements(statement,null);
    }

    public void addCreateRoutineStatements(String statement,Vector<String> statements) {
        this.orderedCreateRoutineStatements.put(statement,statements);
    }

    public void addCreateRoutineStatement(String statement) {
    	addCreateRoutineStatements(statement,null);
    }

    public void addAlterTableDropConstraintStatements(String statement,Vector<String> statements) {
        this.orderedDropConstraintStatements.put(statement,statements);
    }

    public void addAlterTableDropConstraintStatement(String statement) {
    	addAlterTableDropConstraintStatements(statement,null);
    }

    public void addAlterTableAddConstraintStatements(String statement,Vector<String> statements) {
        this.orderedCreateConstraintStatements.put(statement,statements);
    }

    public void addAlterTableAddConstraintStatement(String statement) {
    	addAlterTableAddConstraintStatements(statement,null);
    }

    public void addAlterTableDropForeignKeyStatements(String statement,Vector<String> statements) {
        this.orderedDropForeignKeyStatements.put(statement,statements);
    }

    public void addAlterTableDropForeignKeyStatement(String statement) {
    	addAlterTableDropForeignKeyStatements(statement,null);
    }

    public void addAlterTableAddForeignKeyStatements(String statement,Vector<String> statements) {
        this.orderedCreateForeignKeyStatements.put(statement,statements);
    }

    public void addAlterTableAddForeignKeyStatement(String statement) {
    	addAlterTableAddForeignKeyStatements(statement,null);
    }

    public Vector<String> combineTemplateStatements(String prolog,String statement,String postlog) {
		Vector<String> ret = null;
    	if (prolog != null || postlog != null) {
    		ret = new Vector<String>();
    		if (prolog != null) ret.add(prolog);
    		ret.add(statement);
    		if (postlog != null) ret.add(postlog);
    	}
    	return ret;
    }
    
    abstract public String[] getStatements();

    protected CompoundStatementsMap orderedDropTableStatements    = new CompoundStatementsMap();
    protected CompoundStatementsMap orderedCreateTableStatements  = new CompoundStatementsMap();
    protected CompoundStatementsMap orderedDropViewStatements    = new CompoundStatementsMap();
    protected CompoundStatementsMap orderedCreateViewStatements  = new CompoundStatementsMap();
    protected CompoundStatementsMap orderedDropRoutineStatements    = new CompoundStatementsMap();
    protected CompoundStatementsMap orderedCreateRoutineStatements  = new CompoundStatementsMap();
    protected CompoundStatementsMap orderedDropConstraintStatements    = new CompoundStatementsMap();
    protected CompoundStatementsMap orderedCreateConstraintStatements  = new CompoundStatementsMap();
    protected CompoundStatementsMap orderedDropForeignKeyStatements    = new CompoundStatementsMap();
    protected CompoundStatementsMap orderedCreateForeignKeyStatements  = new CompoundStatementsMap();

}
