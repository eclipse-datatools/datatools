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
    	alterTableStatements.addElement(statement);
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
	
    public void addCreateStoredProcedureStatement(String statement) {
    	createStoredProcedureStatements.addElement(statement);
    }
	
    public void addDropStoredProcedureStatement(String statement) {
    	dropStoredProcedureStatements.addElement(statement);
    }
	
    public void addCreateUserDefinedFunctionStatement(String statement) {
    	createUserDefinedFunctionStatements.addElement(statement);
    }
	
    public void addDropUserDefinedFunctionStatement(String statement) {
    	dropUserDefinedFunctionStatements.addElement(statement);
    }
	
    public void addCreateSchemaStatement(String statement) {
    	createSchemaStatements.addElement(statement);
    }
	
    public void addDropSchemaStatement(String statement) {
    	dropSchemaStatements.addElement(statement);
    }
	
    public void addCreateUserDefinedTypeStatement(String statement) {
    	createUserDefinedTypeStatements.addElement(statement);
    }
	
    public void addDropUserDefinedTypeStatement(String statement) {
    	dropUserDefinedTypeStatements.addElement(statement);
    }
	
    public void addCreateAssertionStatement(String statement) {
    	createAssertionStatements.addElement(statement);
    }
	
    public void addDropAssertionStatement(String statement) {
    	dropAssertionStatements.addElement(statement);
    }
	
	public String[] getStatements(){
		Vector scriptVec = new Vector();
		scriptVec.addAll(dropTriggerStatements);
		scriptVec.addAll(dropForeignKeyStatements);
		scriptVec.addAll(dropAssertionStatements);
		scriptVec.addAll(dropConstraintStatements);
		scriptVec.addAll(dropIndexStatements);
		scriptVec.addAll(dropViewStatements);
		scriptVec.addAll(dropTableStatements);
		scriptVec.addAll(dropStoredProcedureStatements);
		scriptVec.addAll(dropUserDefinedFunctionStatements);
		scriptVec.addAll(dropUserDefinedTypeStatements);
		scriptVec.addAll(dropSchemaStatements);
		scriptVec.addAll(createSchemaStatements);
		scriptVec.addAll(createTableStatements);
		scriptVec.addAll(alterTableStatements);
		scriptVec.addAll(createViewStatements);
		scriptVec.addAll(createIndexStatements);
		scriptVec.addAll(addConstraintStatements);
		scriptVec.addAll(addForeignKeyStatements);
		scriptVec.addAll(createTriggerStatements);
		scriptVec.addAll(createStoredProcedureStatements);
		scriptVec.addAll(createUserDefinedFunctionStatements);
		scriptVec.addAll(createUserDefinedTypeStatements);
		scriptVec.addAll(createAssertionStatements);
		
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
    protected Vector createStoredProcedureStatements        = new Vector();
    protected Vector createUserDefinedFunctionStatements    = new Vector();
    protected Vector createSchemaStatements                 = new Vector();
    protected Vector createUserDefinedTypeStatements        = new Vector();
    protected Vector createAssertionStatements              = new Vector();
    protected Vector dropStoredProcedureStatements          = new Vector();
    protected Vector dropUserDefinedFunctionStatements      = new Vector();
    protected Vector dropSchemaStatements                   = new Vector();
    protected Vector dropUserDefinedTypeStatements          = new Vector();
    protected Vector dropAssertionStatements                = new Vector();
}
