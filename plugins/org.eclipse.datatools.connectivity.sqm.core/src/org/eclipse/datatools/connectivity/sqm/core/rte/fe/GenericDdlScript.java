package org.eclipse.datatools.connectivity.sqm.core.rte.fe;

import java.util.Vector;

public class GenericDdlScript {
    public void addDropTableStatement(String statement) {
    	dropTableStatements.addElement(statement);
    }
	
    public void addCreateTableStatement(String statement) {
    	createTableStatements.addElement(statement);
    }
	
    public void addAlterTableStatement(String statement) {
    	createTableStatements.addElement(statement);
    }
	
    public void addDropViewStatement(String statement) {
    	dropViewStatements.addElement(statement);
    }

    public void addCreateViewStatement(String statement) {
    	createViewStatements.addElement(statement);
    }

    public void addAlterTableDropForeignKeyStatement(String statement) {
    	dropForeignKeyStatements.addElement(statement);
    }

    public void addAlterTableAddForeignKeyStatement(String statement) {
    	addForeignKeyStatements.addElement(statement);
    }
	
    public void addDropIndexStatement(String statement) {
    	dropIndexStatements.addElement(statement);
    }

    public void addCreateIndexStatement(String statement) {
    	createIndexStatements.addElement(statement);
    }
	
    public void addDropTriggerStatement(String statement) {
    	dropTriggerStatements.addElement(statement);
    }
	
    public void addCreateTriggerStatement(String statement) {
    	createTriggerStatements.addElement(statement);
    }
	
    public void addAlterTableDropConstraintStatement(String statement) {
    	dropConstraintStatements.addElement(statement);
    }

    public void addAlterTableAddConstraintStatement(String statement) {
    	addConstraintStatements.addElement(statement);
    }
	
	public String[] getStatements(){
		Vector scriptVec = new Vector();
		scriptVec.addAll(dropTriggerStatements);
		scriptVec.addAll(dropForeignKeyStatements);
		scriptVec.addAll(dropConstraintStatements);
		scriptVec.addAll(dropIndexStatements);
		scriptVec.addAll(dropViewStatements);
		scriptVec.addAll(dropTableStatements);
		scriptVec.addAll(createTableStatements);
		scriptVec.addAll(alterTableStatements);
		scriptVec.addAll(createViewStatements);
		scriptVec.addAll(createIndexStatements);
		scriptVec.addAll(addConstraintStatements);
		scriptVec.addAll(addForeignKeyStatements);
		scriptVec.addAll(createTriggerStatements);
		
		String[] scripts = new String[scriptVec.size()];
		scriptVec.copyInto(scripts);
		return scripts;
	}

    protected Vector dropForeignKeyStatements               = new Vector();
    protected Vector dropTableStatements                    = new Vector();
    protected Vector dropViewStatements                     = new Vector();
    protected Vector dropConstraintStatements               = new Vector();
    protected Vector dropTriggerStatements                  = new Vector();
    protected Vector dropIndexStatements                    = new Vector();
    protected Vector createTableStatements                  = new Vector();
    protected Vector alterTableStatements                   = new Vector();
    protected Vector createViewStatements                   = new Vector();
    protected Vector addForeignKeyStatements                = new Vector();
    protected Vector createIndexStatements                  = new Vector();
    protected Vector createTriggerStatements                = new Vector();
    protected Vector addConstraintStatements                = new Vector();
}
