/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
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

public class DdlScript {
    public void addDropTableStatement(String statement) {
    	dropTableStatements.addElement(statement);
    }
	
    public void addCreateTableStatement(String statement) {
    	createTableStatements.addElement(statement);
    }
	
    public void addRenameTableStatement(String statement) {
    	renameTableStatements.addElement(statement);
    }
	
    public void addRenameColumnStatement(String statement) {
    	renameColumnStatements.addElement(statement);
    }
	
    public void addAlterTableStatement(String statement) {
    	alterTableStatements.addElement(statement);
    }

    public void addAlterTableAddColumnStatement(String statement) {
    	alterTableAddColumnStatements.addElement(statement);
    }

    public void addAlterTableDropColumnStatement(String statement) {
    	alterTableDropColumnStatements.addElement(statement);
    }

    public void addReorgTableStatement(String statement) {
    	reorgStatements.addElement(statement);
    }

	// Data Preservation	
    public void addBackupTableStatement(String statement) {
    	backupTableStatements.addElement(statement);
    }

    public void addPopulateTableStatement(String statement) {
    	populateTableStatements.addElement(statement);
    }
    public void addDropBackupTableStatement(String statement) {
    	dropBackupTableStatements.addElement(statement);
    }
    // End Data Presrevation

    public void addDropViewStatement(String statement) {
    	dropViewStatements.addElement(statement);
    }

    public void addCreateViewStatement(String statement) {
    	createViewStatements.addElement(statement);
    }

    public void addDropUserDefinedTypeStatement(String statement) {
    	dropUserDefinedTypeStatements.addElement(statement);
    }
 
    public void addCreateUserDefinedTypeStatement(String statement) {
    	createUserDefinedTypeStatements.addElement(statement);
    }

    public void addDropSequenceStatement(String statement){
    	dropSequenceStatements.addElement(statement);
    }
    
    public void addCreateSequenceStatement(String statement){
    	createSequenceStatements.addElement(statement);
    }

    public void addDropRoutineStatement(String statement) {
    	dropRoutineStatements.addElement(statement);
    }
    
    public void addCreateRoutineStatement(String statement) {
    	createRoutineStatements.addElement(statement);
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

    public void addCreateViewIndexStatement(String statement) {
    	createViewIndexStatements.addElement(statement);
    }
	
    public void addDropViewIndexStatement(String statement) {
    	dropViewIndexStatements.addElement(statement);
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
	
    public void addDropSchemaStatement(String statement) {
    	dropSchemaStatements.addElement(statement);
    }
    
   	//@bd00058820gs
    public void addDropDatabaseStatement(String statement) {
        dropDatabaseStatements.addElement(statement);
    }    
   	//@ed00058820gs
	
    public void addCreateSchemaStatement(String statement) {
    	createSchemaStatements.addElement(statement);
    }
    
   	//@bd00058820gs
    public void addCreateDatabaseStatement(String statement) {
        createDatabaseStatements.addElement(statement);
    }    
	//@ed00058820gs
	    
    public void addRevokeStatement(String statement) {
        revokeStatements.addElement(statement);
    }    
	    
    public void addGrantStatement(String statement) {
        grantStatements.addElement(statement);
    }    

    public void addCreateRoleStatement(String statement) {
    	createRoleStatements.addElement(statement);
    }
    
    public void addCreateUserStatement(String statement) {
    	createUserStatements.addElement(statement);
    }
    
    public void addCreateGroupStatement(String statement) {
    	createGroupStatements.addElement(statement);
    }
    public void addDropRoleStatement(String statement) {
        dropRoleStatements.addElement(statement);
    }  
    public void addDropUserStatement(String statement) {
        dropUserStatements.addElement(statement);
    }  
    public void addDropGroupStatement(String statement) {
        dropGroupStatements.addElement(statement);
    }  
    
	public String[] getStatements(){
		Vector scriptVec = new Vector();
		scriptVec.addAll(revokeStatements);
		scriptVec.addAll(dropTriggerStatements);
		scriptVec.addAll(dropRoutineStatements);
		scriptVec.addAll(dropForeignKeyStatements);
		scriptVec.addAll(dropConstraintStatements);
		scriptVec.addAll(dropViewIndexStatements);
		scriptVec.addAll(dropIndexStatements);
		scriptVec.addAll(dropViewStatements);
		scriptVec.addAll(backupTableStatements); // Data Preservation
		scriptVec.addAll(dropTableStatements);
		scriptVec.addAll(dropSequenceStatements);
		scriptVec.addAll(dropUserDefinedTypeStatements);
		scriptVec.addAll(dropSchemaStatements);
        scriptVec.addAll(dropRoleStatements); 
        scriptVec.addAll(dropUserStatements); 
        scriptVec.addAll(dropGroupStatements); 
        scriptVec.addAll(dropDatabaseStatements); //@d00058820gs
        scriptVec.addAll(createDatabaseStatements); //@d00058820gs
        scriptVec.addAll(createRoleStatements); 
        scriptVec.addAll(createUserStatements); 
        scriptVec.addAll(createGroupStatements); 
		scriptVec.addAll(createSchemaStatements);
		scriptVec.addAll(renameTableStatements);
		scriptVec.addAll(renameColumnStatements);
		scriptVec.addAll(createUserDefinedTypeStatements);
		scriptVec.addAll(createSequenceStatements);
		scriptVec.addAll(createTableStatements);
		scriptVec.addAll(alterTableStatements);
		scriptVec.addAll(alterTableDropColumnStatements);
		scriptVec.addAll(alterTableAddColumnStatements);
		scriptVec.addAll(reorgStatements);
		scriptVec.addAll(populateTableStatements); // Data Preservation
		scriptVec.addAll(dropBackupTableStatements); // Data Preservation
		scriptVec.addAll(createIndexStatements);
		scriptVec.addAll(addConstraintStatements);
		scriptVec.addAll(addForeignKeyStatements);
		scriptVec.addAll(createViewStatements);
		scriptVec.addAll(createViewIndexStatements);
		scriptVec.addAll(createRoutineStatements);
		scriptVec.addAll(createTriggerStatements);
		scriptVec.addAll(grantStatements);
		
		String[] scripts = new String[scriptVec.size()];
		scriptVec.copyInto(scripts);
		return scripts;
	}

    protected Vector revokeStatements                       = new Vector();
    protected Vector dropRoutineStatements                  = new Vector();
    protected Vector dropForeignKeyStatements               = new Vector();
	protected Vector backupTableStatements                  = new Vector();  // Data Preservation
	protected Vector populateTableStatements                = new Vector();  // Data Preservation
	protected Vector dropBackupTableStatements				= new Vector();	 // Data Preservation
    protected Vector dropTableStatements                    = new Vector();
    protected Vector renameTableStatements                  = new Vector();
    protected Vector renameColumnStatements                 = new Vector();
    protected Vector dropViewStatements                     = new Vector();
    protected Vector dropUserDefinedTypeStatements          = new Vector();
    protected Vector dropSequenceStatements                 = new Vector();
    protected Vector dropConstraintStatements               = new Vector();
    protected Vector dropTriggerStatements                  = new Vector();
    protected Vector dropIndexStatements                    = new Vector();
    protected Vector dropViewIndexStatements                 = new Vector();
    protected Vector dropDatabaseStatements                 = new Vector(); //@bd00058820gs
    protected Vector dropSchemaStatements                   = new Vector();
    protected Vector dropRoleStatements                     = new Vector();
    protected Vector dropUserStatements                     = new Vector();
    protected Vector dropGroupStatements                    = new Vector();
    protected Vector createUserDefinedTypeStatements        = new Vector();
    protected Vector createRoutineStatements                = new Vector();
    protected Vector createTableStatements                  = new Vector();
    protected Vector alterTableStatements                   = new Vector();
    protected Vector alterTableDropColumnStatements         = new Vector();
    protected Vector alterTableAddColumnStatements          = new Vector();
    protected Vector reorgStatements                        = new Vector();
    protected Vector createViewStatements                   = new Vector();
    protected Vector createSequenceStatements               = new Vector();
    protected Vector addForeignKeyStatements                = new Vector();
    protected Vector createIndexStatements                  = new Vector();
    protected Vector createViewIndexStatements                  = new Vector();
    protected Vector createTriggerStatements                = new Vector();
    protected Vector addConstraintStatements                = new Vector();
    protected Vector createSchemaStatements                 = new Vector();
    protected Vector createDatabaseStatements               = new Vector(); //@bd00058820gs
    protected Vector grantStatements                        = new Vector(); 
    protected Vector createRoleStatements                   = new Vector();
    protected Vector createUserStatements                   = new Vector();
    protected Vector createGroupStatements                  = new Vector();
}
