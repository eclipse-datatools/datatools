/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ddl;

import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlScript;

public class SybaseDdlScript extends GenericDdlScript
{
    public void merge(SybaseDdlScript script)
    {
        useDatabaseDropStatements.addAll(script.useDatabaseDropStatements);
        useDatabaseCreateStatements.addAll(script.useDatabaseCreateStatements);
        alterTableRenameStatements.addAll(script.alterTableRenameStatements);
        alterTableDropColumnStatements.addAll(script.alterTableDropColumnStatements);
        createRuleStatements.addAll(script.createRuleStatements);
        createDefaultStatements.addAll(script.createDefaultStatements);
        createUDDStatements.addAll(script.createUDDStatements);
        createOtherStatements.addAll(script.createOtherStatements);
        createRoutineStatements.addAll(script.createRoutineStatements);
        createEventStatements.addAll(script.createEventStatements);
        dropRuleStatements.addAll(script.dropRuleStatements);
        dropDefaultStatements.addAll(script.dropDefaultStatements);
        dropUDDStatements.addAll(script.dropUDDStatements);
        dropOtherStatements.addAll(script.dropOtherStatements);
        dropEventStatements.addAll(script.dropEventStatements);
        dropRoutineStatements.addAll(script.dropRoutineStatements);
        alterOtherStatements.addAll(script.alterOtherStatements);
        grantPrivilegesStatements.addAll(script.grantPrivilegesStatements);
        revokePrivilegesStatements.addAll(script.revokePrivilegesStatements);
        createDatabaseStatements.addAll(script.createDatabaseStatements);
        alterTableRenameConstraintStatements.addAll(script.alterTableRenameConstraintStatements);
        dropDatabaseStatements.addAll(script.dropDatabaseStatements);
        dropUserStatements.addAll(script.dropUserStatements);
        dropGroupStatements.addAll(script.dropGroupStatements);
        createGroupStatements.addAll(script.createGroupStatements);
        createUserStatements.addAll(script.createUserStatements);
        dropDatabaseObjectsStatements.addAll(script.dropDatabaseObjectsStatements);
        createDatabaseObjectsStatements.addAll(script.createDatabaseObjectsStatements);
        alterTableColumnStatements.addAll(script.alterTableColumnStatements);
        alterIndexNonclusteredStatements.addAll(script.alterIndexNonclusteredStatements);
        alterIndexClusteredStatements.addAll(script.alterIndexClusteredStatements);
        dropForeignKeyStatements.addAll(script.dropForeignKeyStatements);
        dropTableStatements.addAll(script.dropTableStatements);
        dropViewStatements.addAll(script.dropViewStatements);
        dropConstraintStatements.addAll(script.dropConstraintStatements);
        dropTriggerStatements.addAll(script.dropTriggerStatements);
        dropIndexStatements.addAll(script.dropIndexStatements);
        createTableStatements.addAll(script.createTableStatements);
        alterTableStatements.addAll(script.alterTableStatements);
        createViewStatements.addAll(script.createViewStatements);
        addForeignKeyStatements.addAll(script.addForeignKeyStatements);
        createIndexStatements.addAll(script.createIndexStatements);
        createTriggerStatements.addAll(script.createTriggerStatements);
        addConstraintStatements.addAll(script.addConstraintStatements);
        alterTableModifyColumnTypeStatements.addAll(script.alterTableModifyColumnTypeStatements);
        alterTableRenameColumnStatements.addAll(script.alterTableRenameColumnStatements);
        alterTableAlterConstraintStatements.addAll(script.alterTableAlterConstraintStatements);
        commentOnStatements.addAll(script.commentOnStatements);
    }
    
    public String[] getStatements()
    {
        Vector scriptVec = new Vector();

        /**
         * 1.drop contraint 2.drop column 3.rename table 4.add columnn 5.add constraint 6.alter table ...
         */
        scriptVec.addAll(useDatabaseDropStatements);
        scriptVec.addAll(revokePrivilegesStatements);
        scriptVec.addAll(dropConstraintStatements);
        scriptVec.addAll(alterTableDropColumnStatements);
        scriptVec.addAll(alterTableRenameStatements);
        scriptVec.addAll(alterIndexNonclusteredStatements);
        scriptVec.addAll(alterIndexClusteredStatements);
        scriptVec.addAll(dropOtherStatements);
        scriptVec.addAll(dropEventStatements);
        scriptVec.addAll(dropTriggerStatements);
        scriptVec.addAll(dropRoutineStatements);
        scriptVec.addAll(dropForeignKeyStatements);
        
        scriptVec.addAll(dropIndexStatements);
        scriptVec.addAll(dropViewStatements);
        scriptVec.addAll(dropTableStatements);
        scriptVec.addAll(dropUDDStatements);
        scriptVec.addAll(dropRuleStatements);
        scriptVec.addAll(dropDefaultStatements);
        scriptVec.addAll(dropUserStatements);
        scriptVec.addAll(dropGroupStatements);
        scriptVec.addAll(dropDatabaseObjectsStatements);
        scriptVec.addAll(dropDatabaseStatements);
        scriptVec.addAll(createDatabaseStatements);
        scriptVec.addAll(useDatabaseCreateStatements);
        scriptVec.addAll(createDatabaseObjectsStatements);
        scriptVec.addAll(createUserStatements);
        scriptVec.addAll(createGroupStatements);
        scriptVec.addAll(createRuleStatements);
        scriptVec.addAll(createDefaultStatements);
        scriptVec.addAll(createUDDStatements);
        scriptVec.addAll(createTableStatements);
        scriptVec.addAll(alterTableStatements);
        scriptVec.addAll(alterTableRenameColumnStatements);
        scriptVec.addAll(alterTableModifyColumnTypeStatements);
        scriptVec.addAll(alterTableColumnStatements);
        
        scriptVec.addAll(createViewStatements);
        scriptVec.addAll(createIndexStatements);
        scriptVec.addAll(addConstraintStatements);
        scriptVec.addAll(addForeignKeyStatements);
        scriptVec.addAll(createRoutineStatements);
        scriptVec.addAll(createTriggerStatements);
        scriptVec.addAll(createEventStatements);
        scriptVec.addAll(createOtherStatements);
        scriptVec.addAll(alterOtherStatements);
        scriptVec.addAll(grantPrivilegesStatements);
        scriptVec.addAll(alterTableRenameConstraintStatements);
        scriptVec.addAll(alterTableAlterConstraintStatements);
        scriptVec.addAll(cleanUpStatements);
        scriptVec.addAll(commentOnStatements);
        String[] scripts = new String[scriptVec.size()];
        scriptVec.copyInto(scripts);
        return scripts;
    }

    public void addAlterIndexNonclusteredStatements(String statement)
    {
        alterIndexNonclusteredStatements.add(statement);
    }
    
    public void addAlterIndexClusteredStatements(String statement)
    {
        alterIndexClusteredStatements.add(statement);
    }
    
    public void addAlterTableRenameStatements(String statement)
    {
        alterTableRenameStatements.add(statement);
    }
    
    public void addAlterTableDropColumnStatements(String statement)
    {
        alterTableDropColumnStatements.add(statement);
    }
    
    public void addCreateRuleStatements(String statement)
    {
        createRuleStatements.add(statement);
    }
    
    public void addCreateDefaultStatements(String statement)
    {
        createDefaultStatements.add(statement);
    }
    
    public void addCreateUDDStatements(String statement)
    {
        createUDDStatements.add(statement);
    }
    
    public void addCreateOtherStatements(String statement)
    {
        createOtherStatements.add(statement);
    }
    
    public void addDropRuleStatements(String statement)
    {
        dropRuleStatements.add(statement);
    }
    
    public void addDropDefaultStatements(String statement)
    {
        dropDefaultStatements.add(statement);
    }
    
    public void addDropUDDStatements(String statement)
    {
        dropUDDStatements.add(statement);
    }
    
    public void addDropOtherStatements(String statement)
    {
        dropOtherStatements.add(statement);
    }

    public void addAlterOtherStatements(String statement)
    {
        alterOtherStatements.add(statement);
    }
    
    public void addDropEventStatements(String statement)
    {
    	dropEventStatements.add(statement);
    }
    
    public void addCreateEventStatements(String statement)
    {
    	createEventStatements.add(statement);
    }
    
    public void addCreateRoutineStatements(String statement)
    {
    	createRoutineStatements.add(statement);
    }
    
    public void addDropRoutineStatement(String statement)
    {
    	dropRoutineStatements.add(statement);
    }

    public void addGrantPrivilegeStatement(String statement)
    {
        grantPrivilegesStatements.add(statement);
    }

    public void addRevokePrivilegeStatement(String statement)
    {
        revokePrivilegesStatements.add(statement);
    }
    
    public void addAlterTableRenameConstraintStatement(String statement)
    {
        alterTableRenameConstraintStatements.add(statement);
    }
    
    public void addCreateDatabaseStatements(String statement)
    {
        createDatabaseStatements.add(statement);        
    }
    
    public void addDropDatabaseStatements(String statement)
    {
        dropDatabaseStatements.add(statement);
    }
    
    public void addCreateUserStatements(String statement)
    {
        createUserStatements.add(statement);
    }
    
    public void addCreateGroupStatements(String statement)
    {
        createGroupStatements.add(statement);
    }
    
    public void addDropUserStatements(String statement)
    {
        dropUserStatements.add(statement);
    }
    
    public void addDropGroupStatements(String statement)
    {
        dropGroupStatements.add(statement);
    }
    
    //database objects includes segment, dbspace, encripted key, etc.
    public void addDropDatabaseObjectStatements(String statement)
    {
        dropDatabaseObjectsStatements.add(statement);
    }
    
    public void addCreateDatabaseObjectStatements(String statement)
    {
        createDatabaseObjectsStatements.add(statement);
    }
    
    public void addAlterTableColumnStatements(String statement)
    {
        alterTableColumnStatements.add(statement);
    }
    
    public void addAlterTableModifyColumnTypeStatements(String statement)
    {
        alterTableModifyColumnTypeStatements.add(statement);
    }
    
    public void addAlterTableRenameColumnStatements(String statement)
    {
        alterTableRenameColumnStatements.add(statement);
    }
    
    public void addCleanUpStatements(String statement)
    {
        cleanUpStatements.add(statement);
    }
    
    public void addUseDatabaseDropStatements(String statement)
    {
        useDatabaseDropStatements.add(statement);
    }
    
    public void addUseDatabaseCreateStatements(String statement)
    {
        useDatabaseCreateStatements.add(statement);
    }
    
    public void addAlterTableAlterConstraintStatements(String statement)
    {
        alterTableAlterConstraintStatements.add(statement);
    }
    
    public void addCommentOnStatements(String statement)
    {
        commentOnStatements.add(statement);
    }
    
    protected Vector alterTableRenameStatements = new Vector();
    
    protected Vector alterTableDropColumnStatements = new Vector();
    
    protected Vector createRuleStatements = new Vector();

	protected Vector createDefaultStatements = new Vector();

	protected Vector createUDDStatements = new Vector();

	protected Vector createOtherStatements = new Vector();

	protected Vector createRoutineStatements = new Vector();
	
	protected Vector createEventStatements = new Vector();
	
	protected Vector dropRuleStatements = new Vector();

	protected Vector dropDefaultStatements = new Vector();

	protected Vector dropUDDStatements = new Vector();

	protected Vector dropOtherStatements = new Vector();

	protected Vector dropEventStatements = new Vector();

	protected Vector dropRoutineStatements = new Vector();
    
    protected Vector alterOtherStatements    = new Vector();
    
    protected Vector grantPrivilegesStatements = new Vector();
    
    protected Vector revokePrivilegesStatements = new Vector();
    
    protected Vector createDatabaseStatements = new Vector();

    protected Vector alterTableRenameConstraintStatements = new Vector();
    
    protected Vector dropDatabaseStatements = new Vector();
    
    protected Vector dropUserStatements = new Vector();
    
    protected Vector dropGroupStatements = new Vector();
    
    protected Vector createUserStatements = new Vector();
    
    protected Vector createGroupStatements = new Vector();
    
    protected Vector dropDatabaseObjectsStatements = new Vector();
    
    protected Vector createDatabaseObjectsStatements = new Vector();
    
    protected Vector alterTableColumnStatements = new Vector();
    
    protected Vector alterTableModifyColumnTypeStatements = new Vector();
    
    protected Vector alterTableRenameColumnStatements = new Vector();

    protected Vector cleanUpStatements = new Vector();
   
    protected Vector alterIndexNonclusteredStatements = new Vector();
    
    protected Vector alterIndexClusteredStatements = new Vector();
    
    protected Vector useDatabaseDropStatements = new Vector();
    
    protected Vector useDatabaseCreateStatements = new Vector();
    
    protected Vector alterTableAlterConstraintStatements = new Vector();
    
    protected Vector commentOnStatements = new Vector();
}
