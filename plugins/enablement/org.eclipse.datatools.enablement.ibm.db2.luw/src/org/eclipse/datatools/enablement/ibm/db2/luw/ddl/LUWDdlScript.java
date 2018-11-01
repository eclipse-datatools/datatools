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
package org.eclipse.datatools.enablement.ibm.db2.luw.ddl;

import java.util.Vector;

import org.eclipse.datatools.enablement.ibm.ddl.CoreDdlScriptVector;
import org.eclipse.datatools.enablement.ibm.ddl.OrderingDdlScript;

public class LUWDdlScript extends OrderingDdlScript {
    public void addDropTablespaceStatement(String statement) {
        this.dropTablespaceStatements.add(statement);
    }
    
    public void addCreateTablespaceStatement(String statement) {
        this.createTablespaceStatements.add(statement);
    }

    public void addDropBufferPoolStatement(String statement) {
        this.dropBufferPoolStatements.add(statement);
    }
    
    public void addCreateBufferPoolStatement(String statement) {
        this.createBufferPoolStatements.add(statement);
    }

    public void addCommentOnStatement(String statement) {
        this.commentOnStatements.add(statement);
    }

    public void addDropNicknameStatement(String statement) {
        this.dropNicknameStatements.add(statement);
    }
    
    public void addCreateNicknameStatement(String statement) {
        this.createNicknameStatements.add(statement);
    }
    
    // fsp - pyl
    public void addCreateFederatedProcedureStatement(String statement) {
        this.createFederatedProcedureStatements.add(statement);
    }
    
    public void addDropPackageStatement(String statement) {
        this.dropPackageStatements.add(statement);
    }

    public void addDropRemoteServerStatement(String statement) {
        this.dropRemoteServerStatements.add(statement);
    }
    
    public void addCreateRemoteServerStatement(String statement) {
        this.createRemoteServerStatements.add(statement);
    }
    
    public void addDropWrapperStatement(String statement) {
        this.dropWrapperStatements.add(statement);
    }
    
    public void addCreateWrapperStatement(String statement) {
        this.createWrapperStatements.add(statement);
    }

    public void addDropUserMappingStatement(String statement) {
        this.dropUserMappingStatements.add(statement);
    }
    
    public void addCreateUserMappingStatement(String statement) {
        this.createUserMappingStatements.add(statement);
    }    

//    xml schema drop done via stored procedure now 
//    public void addDropXMLSchemaStatement(String statement){
//    	this.dropXMLSchemaStatements.add(statement);
//    }

    public void addDropPartitionStatement(String statement) {
        this.dropPartitionGroupStatements.add(statement);
    }
    
    public void addCreatePartitionGroupStatement(String statement) {
        this.createPartitionGroupStatements.add(statement);
    }

    public void addUpdateStatisticsStatement(String statement) {
        this.updateStatisticsStatements.add(statement);
    }

    public void addDropModuleStatement(String statement) {
        this.dropModuleStatements.add(statement);
    }
    
    public void addCreateModuleStatement(String statement) {
        this.createModuleStatements.add(statement);
    }    

    public void addDropModuleConditionStatement(String statement) {
        this.dropModuleConditionStatements.add(statement);
    }
    
    public void addCreateModuleConditionStatement(String statement) {
        this.createModuleConditionStatements.add(statement);
    }    

    public void addDropModuleGlobalVariableStatement(String statement) {
        this.dropModuleGlobalVariableStatements.add(statement);
    }
    
    public void addCreateModuleGlobalVariableStatement(String statement) {
        this.createModuleGlobalVariableStatements.add(statement);
    }    

    public void addDropGlobalVariableStatement(String statement) {
        this.dropModuleGlobalVariableStatements.add(statement);
    }
    
    public void addCreateGlobalVariableStatement(String statement) {
        this.createModuleGlobalVariableStatements.add(statement);
    }    

    public void addDropModuleTypeStatement(String statement) {
        this.dropModuleTypeStatements.add(statement);
    }
    
    public void addCreateModuleTypeStatement(String statement) {
        this.createModuleTypeStatements.add(statement);
    }    

    public void addDropModuleRoutineStatement(String statement) {
        this.dropModuleRoutineStatements.add(statement);
    }
    
    public void addCreateModuleRoutineStatement(String statement) {
        this.createModuleRoutineStatements.add(statement);
    }    

    public void addDropPlsqlPackageStatement(String statement) {
        this.dropPlsqlPackageStatements.add(statement);
    }
    
    public void addCreatePlsqlPackageStatement(String statement) {
        this.createPlsqlPackageStatements.add(statement);
    }    

    public void addDropPlsqlPackageBodyStatement(String statement) {
        this.dropPlsqlPackageBodyStatements.add(statement);
    }
    
    public void addCreatePlsqlPackageBodyStatement(String statement) {
        this.createPlsqlPackageBodyStatements.add(statement);
    }    

    public String[] getStatements(){
    	CoreDdlScriptVector scriptVec = new CoreDdlScriptVector();
		scriptVec.addAll(revokeStatements, false);
		scriptVec.addAll(dropRoleStatements);
		scriptVec.addAll(dropTriggerStatements);
		scriptVec.addAll(dropModuleRoutineStatements);
		scriptVec.addAll(dropModuleConditionStatements);
		scriptVec.addAll(dropModuleGlobalVariableStatements);
		scriptVec.addAll(dropModuleTypeStatements);
		scriptVec.addAll(dropModuleStatements);
		scriptVec.addAll(dropPlsqlPackageBodyStatements);
		scriptVec.addAll(dropPlsqlPackageStatements);
		scriptVec.addAll(orderedDropRoutineStatements);
		scriptVec.addAll(orderedDropForeignKeyStatements);
		scriptVec.addAll(orderedDropConstraintStatements);
		scriptVec.addAll(dropViewIndexStatements);
		scriptVec.addAll(dropIndexStatements);
		scriptVec.addAll(orderedDropViewStatements);
		scriptVec.addAll(alterTableDropColumnStatements);
		scriptVec.addAll(backupTableStatements); // Data Preservation
		scriptVec.addAll(orderedDropTableStatements);
		scriptVec.addAll(dropSequenceStatements);
		scriptVec.addAll(dropUserDefinedTypeStatements);
		scriptVec.addAll(dropNicknameStatements);
		scriptVec.addAll(dropFederatedProcedureStatements);
		scriptVec.addAll(dropPackageStatements);
        scriptVec.addAll(dropUserMappingStatements);        
        scriptVec.addAll(dropRemoteServerStatements);
        scriptVec.addAll(dropWrapperStatements);
//		scriptVec.addAll(dropXMLSchemaStatements);
		scriptVec.addAll(dropGlobaleVariableStatements);
		scriptVec.addAll(dropSchemaStatements);
		scriptVec.addAll(dropTablespaceStatements);
		scriptVec.addAll(dropBufferPoolStatements);
		scriptVec.addAll(dropPartitionGroupStatements);
        scriptVec.addAll(dropDatabaseStatements); //@d00058820gs
        scriptVec.addAll(createDatabaseStatements); //@d00058820gs
        scriptVec.addAll(createRoleStatements); 
		scriptVec.addAll(createPartitionGroupStatements);
		scriptVec.addAll(createBufferPoolStatements);
		scriptVec.addAll(createTablespaceStatements);
		scriptVec.addAll(createSchemaStatements);
		scriptVec.addAll(createGlobaleVariableStatements);
		scriptVec.addAll(renameTableStatements);
//		wsdbu00240410
	//	resortUDTStatements();
		scriptVec.addAll(createUserDefinedTypeStatements,false);
		scriptVec.addAll(createSequenceStatements);
		scriptVec.addAll(orderedCreateTableStatements);
		scriptVec.addAll(alterTableAddColumnStatements);
		scriptVec.addAll(populateTableStatements); // Data Preservation
		scriptVec.addAll(dropBackupTableStatements); // Data Preservation
        scriptVec.addAll(createWrapperStatements);        
		scriptVec.addAll(createRemoteServerStatements);
        scriptVec.addAll(createUserMappingStatements);        
		scriptVec.addAll(createNicknameStatements);
		scriptVec.addAll(createFederatedProcedureStatements);
		scriptVec.addAll(createIndexStatements);
		scriptVec.addAll(orderedCreateConstraintStatements);
		scriptVec.addAll(orderedCreateForeignKeyStatements);
		scriptVec.addAll(orderedCreateViewStatements);
		scriptVec.addAll(createViewIndexStatements);
		scriptVec.addAll(orderedCreateRoutineStatements);
		scriptVec.addAll(createPlsqlPackageStatements);
		scriptVec.addAll(createPlsqlPackageBodyStatements);
		scriptVec.addAll(createModuleStatements);
		scriptVec.addAll(createModuleTypeStatements);
		scriptVec.addAll(createModuleGlobalVariableStatements);
		scriptVec.addAll(createModuleConditionStatements);
		scriptVec.addAll(createModuleRoutineStatements);
		scriptVec.addAll(createTriggerStatements);
		scriptVec.addAll(grantStatements);
		scriptVec.addAll(commentOnStatements);
		scriptVec.addAll(reorgStatements);
		scriptVec.addAll(updateStatisticsStatements);
		
		String[] scripts = new String[scriptVec.size()];
		scriptVec.copyInto(scripts);
		return scripts;
	}
    protected Vector dropTablespaceStatements               = new Vector();
    protected Vector createTablespaceStatements             = new Vector();
    protected Vector dropBufferPoolStatements               = new Vector();
    protected Vector createBufferPoolStatements             = new Vector();
    protected Vector commentOnStatements                    = new Vector();
    protected Vector createNicknameStatements               = new Vector();
    protected Vector dropNicknameStatements                 = new Vector();
    protected Vector createRemoteServerStatements           = new Vector();
    protected Vector dropRemoteServerStatements             = new Vector();
    protected Vector createWrapperStatements                = new Vector();
    protected Vector dropWrapperStatements                  = new Vector();
    protected Vector createUserMappingStatements            = new Vector();
    protected Vector dropUserMappingStatements              = new Vector();    
//    protected Vector dropXMLSchemaStatements                = new Vector();
    protected Vector dropPartitionGroupStatements           = new Vector();
    protected Vector createPartitionGroupStatements         = new Vector();
    // fsp - pyl
    protected Vector createFederatedProcedureStatements     = new Vector();
    protected Vector dropFederatedProcedureStatements       = new Vector();
    protected Vector dropPackageStatements       			= new Vector();
    protected Vector updateStatisticsStatements      		 = new Vector();
    
    
    protected Vector dropGlobaleVariableStatements       	= new Vector();
    protected Vector createGlobaleVariableStatements  	    = new Vector();
    
    //module
    protected Vector dropModuleStatements       		    = new Vector();
    protected Vector createModuleStatements  	       		= new Vector();
    protected Vector dropModuleConditionStatements       	= new Vector();
    protected Vector createModuleConditionStatements  	    = new Vector();
    protected Vector dropModuleGlobalVariableStatements 	= new Vector();
    protected Vector createModuleGlobalVariableStatements  	 = new Vector();
    protected Vector dropModuleTypeStatements 				 = new Vector();
    protected Vector createModuleTypeStatements  			 = new Vector();
    protected Vector dropModuleRoutineStatements 			 = new Vector();
    protected Vector createModuleRoutineStatements  		 = new Vector();
    protected Vector dropPlsqlPackageStatements       		    = new Vector();
    protected Vector createPlsqlPackageStatements  	       		= new Vector();
    protected Vector dropPlsqlPackageBodyStatements       	    = new Vector();
    protected Vector createPlsqlPackageBodyStatements  	   		= new Vector();
    
    //fix for wsdbu00240410
//    protected void resortUDTStatements(){
//    	Vector oldList = new Vector();
//    	oldList.addAll(createUserDefinedTypeStatements);
//    	Vector newList = new Vector();
//    	ArrayList seperateTypes = new ArrayList();
//    	
//    	for(int i = 0; i < createUserDefinedTypeStatements.size();i++){
//    		String statement = createUserDefinedTypeStatements.get(i).toString();
//    		if(!statement.contains(" depends on")){
//    			String typeName = getSeperateTypeName(statement);
//    			seperateTypes.add(typeName);
//    			newList.add(statement);
//    			oldList.remove(statement);
//    		}
//    	}
//    	
//    	while(oldList.size()>0){
//    		for(int i = 0;i<oldList.size();i++){
//    			String statement = oldList.get(i).toString();
//    			String[] dependantTypes = getDependantTypes(statement);
//    			String typeName = getSeperateTypeName(statement);
//    			boolean canAdd = true;
//    			for(int j=0;j<dependantTypes.length;j++){
//    				if(seperateTypes.indexOf(dependantTypes[j].trim())<0){
//    					canAdd = false;
//    					break;
//    				}
//    			}
//    			if(canAdd){
//    				seperateTypes.add(typeName);
//    				newList.add(statement.substring(0, statement.indexOf(" depends on")));
//    				oldList.remove(i);
//    			}
//    		}
//    		this.createUserDefinedTypeStatements = newList;
//    	}
//    	
//    }
    
//    private String[] getDependantTypes(String userDefineTypeStatement){
// 	    	String[] types;
// 	    	String dependStatement = userDefineTypeStatement.substring(userDefineTypeStatement.indexOf(" depends on")+" depends on".length());
// 	    	if(dependStatement.split(",").length ==0){
// 	    		types = new String[1];
// 	    		types[0] = dependStatement;
// 	    	}else{
// 	    		types = dependStatement.split(",");
// 	    	}
// 	    	return types;
// 	    }
//    
//    private String getSeperateTypeName(String userDefineTypeStatement){
//    	String typeName = "";
//    	int end = 0;
//    	int start = userDefineTypeStatement.indexOf(" TYPE ",0);
//    	if(userDefineTypeStatement.contains(" UNDER ")){
//    		//CREATE TYPE type-name UNDER supertype AS(
//    		end = userDefineTypeStatement.indexOf(" UNDER ",0);
//    	}else if(userDefineTypeStatement.contains(" AS(")){
//    		//CREATE TYPE type-name AS( 
//    		end = userDefineTypeStatement.indexOf(" AS(",0);  		
//    	}else{
//    		//CREATE DISTINCT TYPE distinct-type-name AS source_type
//    		//CREATE TYPE TYPE1 AS ..
//    		end = userDefineTypeStatement.indexOf(" AS ",0); 
//    	}
//    	if(end != -1)
//    		typeName = userDefineTypeStatement.substring(start+6,end);
//    	return typeName;
//    }

    
}
