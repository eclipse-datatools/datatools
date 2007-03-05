/**
 * Created on 2006-8-23
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.enablement.sybase.ddl;

import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlScript;

public class SybaseDdlScript extends GenericDdlScript
{
    public String[] getStatements()
    {
        Vector scriptVec = new Vector();

        scriptVec.addAll(dropOtherStatements);
        scriptVec.addAll(dropEventStatements);
        scriptVec.addAll(dropTriggerStatements);
        scriptVec.addAll(dropRoutineStatements);
        scriptVec.addAll(dropForeignKeyStatements);
        scriptVec.addAll(dropConstraintStatements);
        scriptVec.addAll(dropIndexStatements);
        scriptVec.addAll(dropViewStatements);
        scriptVec.addAll(dropTableStatements);
        scriptVec.addAll(dropUDDStatements);
        scriptVec.addAll(dropRuleStatements);
        scriptVec.addAll(dropDefaultStatements);
        scriptVec.addAll(dropAuthIdStatements);
        scriptVec.addAll(dropDatabaseObjectsStatements);
        scriptVec.addAll(dropDatabaseStatements);
        scriptVec.addAll(createDatabaseStatements);
        scriptVec.addAll(createDatabaseObjectsStatements);
        scriptVec.addAll(createAuthIdStatements);
        scriptVec.addAll(createRuleStatements);
        scriptVec.addAll(createDefaultStatements);
        scriptVec.addAll(createUDDStatements);
        scriptVec.addAll(createTableStatements);
        scriptVec.addAll(alterTableStatements);
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
        scriptVec.addAll(revokePrivilegesStatements);
        scriptVec.addAll(alterTableRenameConstraintStatements);
        String[] scripts = new String[scriptVec.size()];
        scriptVec.copyInto(scripts);
        return scripts;
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
    
    public void addCreateAuthIdStatements(String statement)
    {
        createAuthIdStatements.add(statement);
    }
    
    public void addDropAuthIdStatements(String statement)
    {
        dropDatabaseStatements.add(statement);
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
    
    protected Vector dropAuthIdStatements = new Vector();
    
    protected Vector createAuthIdStatements = new Vector();
    
    protected Vector dropDatabaseObjectsStatements = new Vector();
    
    protected Vector createDatabaseObjectsStatements = new Vector();
    
    protected Vector alterTableColumnStatements = new Vector();
   
}
