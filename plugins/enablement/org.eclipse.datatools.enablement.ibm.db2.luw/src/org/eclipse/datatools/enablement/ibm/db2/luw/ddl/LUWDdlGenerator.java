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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.core.rte.IEngineeringCallBack;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategory;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategoryID;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.LUWCatalogDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.LUWCatalogFederatedProcedure;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWCursorDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGlobalVariable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMaterializedQueryTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleArrayDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCondition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCursorDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleDistinctType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleFunction;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleGlobalVariable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleProcedure;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleRowDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRowDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackageBody;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Alias;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Index;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2OLAPObject;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2UserDefinedFunction;
import org.eclipse.datatools.enablement.ibm.ddl.CoreDdlGenerator;
import org.eclipse.datatools.enablement.ibm.ddl.DdlBuilder;
import org.eclipse.datatools.enablement.ibm.ddl.DdlGenerationUtility;
import org.eclipse.datatools.enablement.ibm.ddl.ExtendDdlBuilder;
import org.eclipse.datatools.enablement.ibm.ddl.OlapDdlBuilder;
import org.eclipse.datatools.enablement.ibm.util.DependencyImpactAnalyst;
import org.eclipse.datatools.enablement.ibm.util.DependencyImpactDescription;
import org.eclipse.datatools.enablement.ibm.util.EngineeringOptionID;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.ecore.EObject;

public final class LUWDdlGenerator extends CoreDdlGenerator implements IExecutableExtension {
	
	private static ILUWModuleDdlBuilder moduleBuilder = null;
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		this.product = config.getAttribute("product"); //$NON-NLS-1$
		this.version = config.getAttribute("version"); //$NON-NLS-1$

		if ("V8.1".equals(this.version)
			||"V8.2".equals(this.version)	){
			builder = new LUWDdlBuilder(this);			
		} else if ("V9.1".equals(this.version)) {
			builder = new LUWDdlBuilder9(this);
		} else if ("V9.5".equals(this.version)) {
			builder = new LUWDdlBuilder95(this);
		} else {
			builder = new LUWDdlBuilder97(this);
		}
		
	}
	
	//@d00058820gs: use new structure/methods
    public String[] generateDDL(SQLObject[] elements, IProgressMonitor progressMonitor, IEngineeringCallBack callback) {
    	this.builder.setEngineeringCallBack(callback);
    	String[] statements = new String[0];
        String[] create = new String[0];
        String[] drop = new String[0];
        String[] comment = new String[0];
        String[] statistics = new String[0];
    	EngineeringOption[] options = this.getSelectedOptions(elements);
    	
        if (!this.checkModel(elements, options)) {
        	this.builder.getEngineeringCallBack().writeMessage(LUWDdlMessages.FE_INVALID_MODEL);
        	return statements;
        }
        
//    	if (EngineeringOptionID.generateCreateStatement(options)) {
    			create = this.createSQLObjects(elements, EngineeringOptionID.generateQuotedIdentifiers(options),
                EngineeringOptionID.generateFullyQualifiedNames(options), progressMonitor,callback);
//    	}
        if(EngineeringOptionID.generateDropStatement(options)) {
            drop = this.dropSQLObjects(elements, EngineeringOptionID.generateQuotedIdentifiers(options),
                    EngineeringOptionID.generateFullyQualifiedNames(options), progressMonitor,callback);
        }
        //@d00058820gs
        if (EngineeringOptionID.generateCommentStatement(options)) {
            comment = this.commentSQLObjects(elements, EngineeringOptionID.generateQuotedIdentifiers(options),
            EngineeringOptionID.generateFullyQualifiedNames(options), progressMonitor,callback);
        }
        
        if (EngineeringOptionID.generateStatistics(options)) {
            statistics = this.updateStatisticsStatements(elements, EngineeringOptionID.generateQuotedIdentifiers(options),
                    EngineeringOptionID.generateFullyQualifiedNames(options), progressMonitor,callback);
        }
        
        statements = new String[create.length + drop.length + comment.length + statistics.length];
        int i=0;
        for(int j=0; j<drop.length; ++i,++j) {
            statements[i] = drop[j];
        }
        for(int j=0; j<create.length; ++i,++j) {
            statements[i] = create[j];
        }
        for(int j=0; j<comment.length; ++i,++j) {
            statements[i] = comment[j];
        }
        for(int j=0; j<statistics.length; ++i,++j) {
            statements[i] = statistics[j];
        }
        
        return statements;
    }

    public String[] createSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor,IEngineeringCallBack callback) {
    	this.builder.setEngineeringCallBack(callback);
    	String[] statements = this.createStatements(elements, quoteIdentifiers,
        		qualifyNames, progressMonitor, 100);

        if (EngineeringOptionID.generateCreateStatement(options)) {
	        OlapDdlBuilder olapBuilder = DdlGenerationUtility.getOlapDdlBuilder(this.product, this.version);
	        if (olapBuilder != null && EngineeringOptionID.getOptionValue(olapBuilder.getOption(),this.getSelectedOptions(elements))) {
	        	String[] olap = olapBuilder.buildCreateStatement(elements,quoteIdentifiers,qualifyNames);
	
	        	String[] temp = statements;
	            statements = new String[temp.length + olap.length];
	            for(int i=0; i<temp.length; ++i) {
	                statements[i] = temp[i];
	            }
	            for(int i=0; i<olap.length; ++i) {
	                statements[i+temp.length] = olap[i];
	            }
	
	        }
        }
        return statements;
    }
    
    public String[] dropSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor,IEngineeringCallBack callback) {
    	this.builder.setEngineeringCallBack(callback);
        String[] statements = this.dropStatements(elements, quoteIdentifiers,
        		qualifyNames, progressMonitor, 100);
        
        String[] olap = null;
        
        OlapDdlBuilder olapBuilder = DdlGenerationUtility.getOlapDdlBuilder(this.product, this.version);
        if (olapBuilder != null && EngineeringOptionID.getOptionValue(olapBuilder.getOption(),this.getSelectedOptions(elements))) {
          olap = olapBuilder.buildDropStatement(elements,quoteIdentifiers,qualifyNames);

          if (olap != null) {
            String[] temp = statements;
            statements = new String[temp.length + olap.length];
            for(int i=0; i<olap.length; ++i) {
              statements[i] = olap[i];
            }
          
            for(int i=0; i<temp.length; ++i) {
              statements[i+olap.length] = temp[i];
            }
          }
        }
        
        return statements;
    }

    //@d00058820gs: use new structure/methods
    public String[] commentSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor,IEngineeringCallBack callback) {
    	this.builder.setEngineeringCallBack(callback);
        String[] statements = this.commentStatements(elements, quoteIdentifiers,
                qualifyNames, progressMonitor, 100);
        
//        OlapDdlBuilder olapBuilder = DdlGenerationUtility.getOlapDdlBuilder(this.product, this.version);
//        if (olapBuilder != null && EngineeringOptionID.getOptionValue(olapBuilder.getOption(),this.getSelectedOptions(elements))) {
//            String[] olap = olapBuilder.buildCommentStatement(elements,quoteIdentifiers,qualifyNames);
//
//            String[] temp = statements;
//            statements = new String[temp.length + olap.length];
//            for(int i=0; i<temp.length; ++i) {
//                statements[i] = temp[i];
//            }
//            for(int i=0; i<olap.length; ++i) {
//                statements[i+temp.length] = olap[i];
//            }
//        }
        return statements;
    }

    //@d00058820gs: use new structure/methods
    private String[] createStatements(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor, int task) {
    	Map patternMap = DdlGenerationUtility.getCodetemplatePatterns();

    	long exclusiveType = 0;
    	for (int i = 0; i< elements.length; i++){
    		if (!(elements[i] instanceof Column)) {
    			exclusiveType = exclusiveType | EngineeringOptionID.COLUMN;
    			break;
    		}
    	}
    	
    	for (int i = 0; i< elements.length; i++){
    		if ((elements[i] instanceof User) || (elements[i] instanceof Group))
    		{
    		  ((AuthorizationIdentifier)elements[i]).getDatabase().getPrivileges();
    			break;
    		}
    	}
    	
    	Vector processedTablespace = new Vector();
    	LUWDdlScript script = new LUWDdlScript();
        
        Set set = null;
        if (optionDependency != null)
            set = optionDependency.getElementsToUse();

        Iterator it = null;
        if (set != null && set.size() > 0)
            it = optionDependency.getElementsToUse().iterator();
        else
            it = LUWDdlGenerator.getAllContainedDisplayableElementSet(elements).iterator(); 
        
        EngineeringOption[] options = this.getSelectedOptions(elements);   
        List<UserDefinedType> userDefinedTypes = new ArrayList<UserDefinedType>();
		List<UserDefinedType> sortedUserDefinedTypes = new ArrayList<UserDefinedType>();
		
        while(it.hasNext()) {
            Object o = it.next();

            if(o instanceof LUWDatabasePackage) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
                if (EngineeringOptionID.generateGrantStatement(options)) {
            		String[] grantStatements = builder.grantOn((LUWDatabasePackage) o, quoteIdentifiers, qualifyNames);
            		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
            			script.addGrantStatement(grantStatements[igrant]);
            		}
            	}
            }
            else if(o instanceof LUWNickname) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
            	ExtendDdlBuilder extendDdlBuilder = DdlGenerationUtility.getNicknameDdlBuilder(this.product, this.version);
                if (extendDdlBuilder != null && EngineeringOptionID.getOptionValue(extendDdlBuilder.getOption(),options)) {
	                String statement = builder.createNickname((LUWNickname) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) script.addCreateNicknameStatement(statement);
                }
                if (EngineeringOptionID.generateGrantStatement(options)) {
            		String[] grantStatements = builder.grantOn((LUWNickname) o, quoteIdentifiers, qualifyNames);
            		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
            			script.addGrantStatement(grantStatements[igrant]);
            		}
            	}

            }
            else if(o instanceof LUWServer) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
                LUWServer luwServer = (LUWServer)o;
            	ExtendDdlBuilder extendDdlBuilder = DdlGenerationUtility.getRemoteServerDdlBuilder(this.product, this.version);
                if (extendDdlBuilder != null && EngineeringOptionID.getOptionValue(extendDdlBuilder.getOption(),options)) {
                	String statement = builder.createRemoteServer(luwServer, quoteIdentifiers, qualifyNames);                
	                if(statement != null) script.addCreateRemoteServerStatement(statement);
                }
            }
            else if(o instanceof LUWWrapper) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
                LUWWrapper wrapper = (LUWWrapper) o;                    
                ExtendDdlBuilder extendDdlBuilder = DdlGenerationUtility.getWrapperDdlBuilder(this.product, this.version);
                if (extendDdlBuilder != null && EngineeringOptionID.getOptionValue(extendDdlBuilder.getOption(),options)) {
                    String statement = builder.createWrapper(wrapper, quoteIdentifiers, qualifyNames);                
                    if(statement != null) script.addCreateWrapperStatement(statement);
                }
            }
            else if(o instanceof LUWUserMapping) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
                ExtendDdlBuilder extendDdlBuilder = DdlGenerationUtility.getUserMappingDdlBuilder(this.product, this.version);
                if (extendDdlBuilder != null && EngineeringOptionID.getOptionValue(extendDdlBuilder.getOption(),options)) {
                    String statement = builder.createUserMapping((LUWUserMapping) o, quoteIdentifiers, qualifyNames);                
                    if(statement != null) script.addCreateUserMappingStatement(statement);
                }
            }            
            else if(o instanceof LUWMaterializedQueryTable) {
                if (!EngineeringOptionID.generateMQTs(options)) continue;
            	if (EngineeringOptionID.generateCreateStatement(options)) {
	                String statement = builder.createMQT((LUWMaterializedQueryTable) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) script.addCreateViewStatement(statement);
            	}
                if (EngineeringOptionID.generateGrantStatement(options)) {
            		String[] grantStatements = builder.grantOn((LUWMaterializedQueryTable) o, quoteIdentifiers, qualifyNames);
            		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
            			script.addGrantStatement(grantStatements[igrant]);
            		}
            	}
            }
            else if(o instanceof LUWTable) {
            	if (!EngineeringOptionID.generateTables(options)) continue;
            	if (EngineeringOptionID.generateCreateStatement(options)) {
	            	String statement = builder.createTable((LUWTable) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) {
	                	String prolog = builder.createTableCodetemplate((LUWTable)o, DdlGenerationUtility.GENERATE_CREATE_PATTERN, true,patternMap);
	                	String postlog = builder.createTableCodetemplate((LUWTable)o, DdlGenerationUtility.GENERATE_CREATE_PATTERN, false,patternMap);
	            		script.addCreateTableStatements(statement,
	            				script.combineTemplateStatements(prolog,statement,postlog));
	                }
            	}
                if (EngineeringOptionID.generateGrantStatement(options)) {
            		String[] grantStatements = builder.grantOn((LUWTable) o, quoteIdentifiers, qualifyNames);
            		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
            			script.addGrantStatement(grantStatements[igrant]);
            		}
            	}
            }
            else if(o instanceof LUWTableSpace) {
            	if (!EngineeringOptionID.generateTablespaces(options)) continue;
            	if (EngineeringOptionID.generateCreateStatement(options)) {
            		String statement = builder.createTablespace((LUWTableSpace) o, quoteIdentifiers);
            		if(statement != null) script.addCreateTablespaceStatement(statement);
                }
            	if (EngineeringOptionID.generateGrantStatement(options)) {
            		String[] grantStatements = builder.grantOn((LUWTableSpace)  o, quoteIdentifiers);
            		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
            			script.addGrantStatement(grantStatements[igrant]);
            		}
            	}
            }
            else if(o instanceof LUWView) {
            	if (!EngineeringOptionID.generateViews(options)) continue;
            	if (EngineeringOptionID.generateCreateStatement(options)) {
	                String statement = builder.createView((LUWView) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) {
	                	String prolog = builder.createViewCodetemplate((LUWView)o, DdlGenerationUtility.GENERATE_CREATE_PATTERN, true,patternMap);
	                	String postlog = builder.createViewCodetemplate((LUWView)o, DdlGenerationUtility.GENERATE_CREATE_PATTERN, false,patternMap);
	            		script.addCreateViewStatements(statement,
	            				script.combineTemplateStatements(prolog,statement,postlog));
	                }
            	}
            	if (EngineeringOptionID.generateGrantStatement(options)) {
            		String[] grantStatements = builder.grantOn((LUWView)  o, quoteIdentifiers, qualifyNames);
            		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
            			script.addGrantStatement(grantStatements[igrant]);
            		}
            	}

            }
            else if(o instanceof DB2Alias) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
                if (!EngineeringOptionID.generateAliases(options)) continue;
                String statement = builder.createAlias((DB2Alias) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCreateViewStatement(statement);
            }
            else if(o instanceof DB2Procedure) {
            	String statement = "";
            	if ( o instanceof FederatedProcedure)  // fsp - pyl
            	{
                	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
                   	ExtendDdlBuilder extendDdlBuilder = DdlGenerationUtility.getFederatedProcedureDdlBuilder(this.product, this.version);
                    if (extendDdlBuilder != null && EngineeringOptionID.getOptionValue(extendDdlBuilder.getOption(),options)) {
                        statement = builder.createFederatedProcedure((FederatedProcedure) o, quoteIdentifiers, qualifyNames);
    	                if(statement != null) script.addCreateFederatedProcedureStatement(statement);
                    }
            	} else if (o instanceof LUWModuleProcedure){
               		if (!EngineeringOptionID.generateStoredProcedures(options)) continue;
                	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
            		statement = builder.createProcedure((DB2Procedure) o, quoteIdentifiers, qualifyNames);
            		if (statement != null) script.addCreateModuleRoutineStatement(statement);
            	}else
            	{
               		if (!EngineeringOptionID.generateStoredProcedures(options)) continue;
                	if (EngineeringOptionID.generateCreateStatement(options)) {
	            		statement = builder.createProcedure((DB2Procedure) o, quoteIdentifiers, qualifyNames);
	            	    if(statement != null) {
	                    	String prolog = builder.createRoutineCodetemplate((DB2Procedure)o, DdlGenerationUtility.GENERATE_CREATE_PATTERN, true,patternMap);
	                    	String postlog = builder.createRoutineCodetemplate((DB2Procedure)o, DdlGenerationUtility.GENERATE_CREATE_PATTERN, false,patternMap);
	                		script.addCreateRoutineStatements(statement,
		            				script.combineTemplateStatements(prolog,statement,postlog));
		    	            	    }
                	}
                	if (EngineeringOptionID.generateGrantStatement(options)) {
                		String[] grantStatements = builder.grantOn((DB2Procedure)  o, quoteIdentifiers, qualifyNames);
                		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
                			script.addGrantStatement(grantStatements[igrant]);
                		}
                	}
            	}
            }
            else if(o instanceof DB2UserDefinedFunction) {
            	if (!EngineeringOptionID.generateFunctions(options)) continue;
            	
            	if (o instanceof LUWModuleFunction) {
	            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
	                String statement = builder.createUserDefinedFunction((DB2UserDefinedFunction) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) script.addCreateModuleRoutineStatement(statement);
            	}  else {
	            	if (EngineeringOptionID.generateCreateStatement(options)) {
		                String statement = builder.createUserDefinedFunction((DB2UserDefinedFunction) o, quoteIdentifiers, qualifyNames);
		                if(statement != null) {
		                	String prolog = builder.createRoutineCodetemplate((DB2UserDefinedFunction)o, DdlGenerationUtility.GENERATE_CREATE_PATTERN, true,patternMap);
		                	String postlog = builder.createRoutineCodetemplate((DB2UserDefinedFunction)o, DdlGenerationUtility.GENERATE_CREATE_PATTERN, false,patternMap);
		            		script.addCreateRoutineStatements(statement,
		            				script.combineTemplateStatements(prolog,statement,postlog));
		                }
	            	}
	            	if (EngineeringOptionID.generateGrantStatement(options)) {
	            		String[] grantStatements = builder.grantOn((DB2UserDefinedFunction)  o, quoteIdentifiers, qualifyNames);
	            		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
	            			script.addGrantStatement(grantStatements[igrant]);
	            		}
	            	}
            	}
            }
            else if(o instanceof DB2Trigger) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
            	if (!EngineeringOptionID.generateTriggers(options)) continue;
                String statement = builder.createTrigger((DB2Trigger) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCreateTriggerStatement(statement);
            }
            else if(o instanceof CheckConstraint) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
                if(!EngineeringOptionID.generateCKConstraints(options)) continue;
                String statement = builder.addCheckConstraint((CheckConstraint) o, quoteIdentifiers, qualifyNames);
                if(statement != null) {
                	String prolog = builder.createConstraintCodetemplate((CheckConstraint)o, DdlGenerationUtility.GENERATE_CREATE_PATTERN, true,patternMap);
                	String postlog = builder.createConstraintCodetemplate((CheckConstraint)o, DdlGenerationUtility.GENERATE_CREATE_PATTERN, false,patternMap);
            		script.addAlterTableAddConstraintStatements(statement,
            				script.combineTemplateStatements(prolog,statement,postlog));
                }
            }
            else if(o instanceof UniqueConstraint) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
            	if(!EngineeringOptionID.generatePKConstraints(options) || builder.isImplicitPK((UniqueConstraint)o)) continue;
                String statement = builder.addUniqueConstraint((UniqueConstraint) o, quoteIdentifiers, qualifyNames);
                if(statement != null) {
                	String prolog = builder.createConstraintCodetemplate((UniqueConstraint)o, DdlGenerationUtility.GENERATE_CREATE_PATTERN, true,patternMap);
                	String postlog = builder.createConstraintCodetemplate((UniqueConstraint)o, DdlGenerationUtility.GENERATE_CREATE_PATTERN, false,patternMap);
            		script.addAlterTableAddConstraintStatements(statement,
            				script.combineTemplateStatements(prolog,statement,postlog));
                }
            }
            else if(o instanceof ForeignKey) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
            	if(!EngineeringOptionID.generateFKConstraints(options)) continue;
                String statement = builder.addForeignKey((ForeignKey) o, quoteIdentifiers, qualifyNames);
                if (statement != null) {
                	String prolog = builder.createConstraintCodetemplate((ForeignKey)o, DdlGenerationUtility.GENERATE_CREATE_PATTERN, true,patternMap);
                	String postlog = builder.createConstraintCodetemplate((ForeignKey)o, DdlGenerationUtility.GENERATE_CREATE_PATTERN, false,patternMap);
            		script.addAlterTableAddForeignKeyStatements(statement,
            				script.combineTemplateStatements(prolog,statement,postlog));
                }
            }
            else if(o instanceof DB2Index) {
            	if (!EngineeringOptionID.generateIndexes(options) || ((DB2Index)o).isSystemGenerated() || ((DB2Index)o).getIndexType() != DB2IndexType.REGULAR_LITERAL) continue;
            	if (EngineeringOptionID.generateCreateStatement(options)) {
	            	String statement = builder.createIndex((DB2Index) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) {
	                    if (((DB2Index)o).getTable() instanceof LUWTable) {
	                       	script.addCreateIndexStatement(statement);
	                    } else {
	                       	script.addCreateViewIndexStatement(statement);
	                    }
	                }
            	}
            	if (EngineeringOptionID.generateGrantStatement(options)) {
            		String[] grantStatements = builder.grantOn((DB2Index)  o, quoteIdentifiers, qualifyNames);
            		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
            			script.addGrantStatement(grantStatements[igrant]);
            		}
            	}
            }
            else if(o instanceof Sequence) {
            	if (!EngineeringOptionID.generateSequences(options)) continue;
            	if (EngineeringOptionID.generateCreateStatement(options)) {
            		String statement = builder.createSequence((Sequence) o, quoteIdentifiers, qualifyNames);
                	if(statement != null) script.addCreateSequenceStatement(statement);
                }
            	if (EngineeringOptionID.generateGrantStatement(options)) {
            		String[] grantStatements = builder.grantOn((Sequence)  o, quoteIdentifiers, qualifyNames);
            		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
            			script.addGrantStatement(grantStatements[igrant]);
            		}
            	}
            }
//            else if(o instanceof DistinctUserDefinedType) {
//            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
//            	if (!EngineeringOptionID.generateUserDefinedTypes(options)) continue;
//                if (o instanceof LUWModuleDistinctType) {
//                    String statement = builder.createModuleDistinctType((LUWModuleDistinctType) o, quoteIdentifiers, qualifyNames);
//                    if(statement != null) script.addCreateModuleTypeStatement(statement);
//                } else { 
//	                String statement = builder.createDistinctUserDefinedType((DistinctUserDefinedType) o, quoteIdentifiers, qualifyNames);
//	                if(statement != null) script.addCreateUserDefinedTypeStatement(statement);
//                }
//            }
//            else if (o instanceof StructuredUserDefinedType){
//            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
//            	if (!EngineeringOptionID.generateUserDefinedTypes(options)) continue;
//                String statement = builder.createStructuredUserDefinedType((StructuredUserDefinedType) o, quoteIdentifiers, qualifyNames);
//                if(statement != null) script.addCreateUserDefinedTypeStatement(statement);
//            }
            else if(o instanceof Column) {
            	if (!EngineeringOptionID.generateTables(options)) continue;
            	if (EngineeringOptionID.generateCreateStatement(options)) {
            	if ((exclusiveType & EngineeringOptionID.COLUMN) == EngineeringOptionID.COLUMN) continue;
	                String statement = builder.alterTableAddColumn((Column)o, quoteIdentifiers, qualifyNames);
	                if(statement != null) script.addAlterTableAddColumnStatement(statement);
            	}
            }
            else if(o instanceof LUWBufferPool) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
                if (!EngineeringOptionID.generateBufferPool(options)) continue;
                String statement = builder.createBufferPool((LUWBufferPool) o, quoteIdentifiers);
                if(statement != null) script.addCreateBufferPoolStatement(statement);
            }
            else if(o instanceof LUWPartitionGroup) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
                if (!EngineeringOptionID.generatePartitionGroup(options)) continue;
                String statement = builder.createPartitionGroup((LUWPartitionGroup) o, quoteIdentifiers);
                if(statement != null) script.addCreatePartitionGroupStatement(statement);
            }
            else if(o instanceof Schema) {
            	//if(!EngineeringOptionID.generateFullyQualifiedNames()) continue;
                if(!EngineeringOptionID.generateSchemas(options)) continue;
            	if (EngineeringOptionID.generateCreateStatement(options)) {
	                String statement = builder.createSchema((Schema) o, quoteIdentifiers,qualifyNames);
	                if(statement != null) script.addCreateSchemaStatement(statement);
            	}
            	if (EngineeringOptionID.generateGrantStatement(options)) {
            		String[] grantStatements = builder.grantOn((Schema)  o, quoteIdentifiers, qualifyNames);
            		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
            			script.addGrantStatement(grantStatements[igrant]);
            		}
            	}
            }
            else if(o instanceof Database) {
                if(!EngineeringOptionID.generateDatabase(options)) continue;
            	if (EngineeringOptionID.generateGrantStatement(options)) {
            		String[] grantStatements = builder.grantOn((Database)  o, quoteIdentifiers);
            		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
            			script.addGrantStatement(grantStatements[igrant]);
            		}
            	}
            }
            else if(o instanceof Privilege) {
            	//if(!this.generateFullyQualifiedNames()) continue;
                if(!EngineeringOptionID.generateGrantStatement(options)) continue;
                String statement = builder.getGrantPrivilegeStatement((Privilege)o,quoteIdentifiers,qualifyNames);
                if(statement != null) script.addGrantStatement(statement);
            }
            else if(o instanceof Role) {
                if (!EngineeringOptionID.generateRoles(options)) continue;
            	if (EngineeringOptionID.generateCreateStatement(options)) {
	                String statement = builder.createRole((Role)o, quoteIdentifiers);
	                if(statement != null) script.addCreateRoleStatement(statement);
            	}
//                if (EngineeringOptionID.generateGrantStatement(options)) {
//            		String[] grantStatements = builder.grantOn((Role) o, quoteIdentifiers);
//            		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
//            			script.addGrantStatement(grantStatements[igrant]);
//            		}
//            	}
            }
            else if(o instanceof RoleAuthorization) {
                if (!EngineeringOptionID.generateGrantStatement(options)) continue;
                String statement = builder.getGrantRoleAuthorizationStatement((RoleAuthorization)o,quoteIdentifiers);
                if(statement != null) script.addGrantStatement(statement);
            }
            else if (o instanceof PLSQLPackage) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
            	if (!EngineeringOptionID.generatePacakges(options)) continue;
                String statement = builder.createPlsqlPackage((PLSQLPackage) o, quoteIdentifiers, qualifyNames);
                if (statement != null) script.addCreatePlsqlPackageStatement(statement);

                if (EngineeringOptionID.generateGrantStatement(options)) {
            		String[] grantStatements = builder.grantOn((PLSQLPackage) o, quoteIdentifiers, qualifyNames);
            		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
            			script.addGrantStatement(grantStatements[igrant]);
            		}
            	}
            }
            else if (o instanceof PLSQLPackageBody) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
            	if (!EngineeringOptionID.generatePacakgeBodys(options)) continue;
                String statement = builder.createPlsqlPackageBody((PLSQLPackageBody)o, quoteIdentifiers, qualifyNames);
                if (statement != null) script.addCreatePlsqlPackageBodyStatement(statement);
            }
            else if(o instanceof LUWModule) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
                if (!EngineeringOptionID.generateModules(options)) continue;
                String statement = builder.createModule((LUWModule) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCreateModuleStatement(statement);
                
                if (EngineeringOptionID.generateGrantStatement(options)) {
            		String[] grantStatements = builder.grantOn((LUWModule) o, quoteIdentifiers, qualifyNames);
            		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
            			script.addGrantStatement(grantStatements[igrant]);
            		}
            	}

            }
            else if(o instanceof LUWModuleCondition) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
                if (!EngineeringOptionID.generateModuleConditions(options)) continue;
                String statement = builder.createModuleCondition((LUWModuleCondition) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCreateModuleConditionStatement(statement);
            }
            else if(o instanceof LUWGlobalVariable) {
            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
                if (!EngineeringOptionID.generateGlobalVariables(options)) continue;
                
                if (o instanceof LUWModuleGlobalVariable) {
                    String statement = builder.createModuleGlobalVariable((LUWModuleGlobalVariable) o, quoteIdentifiers, qualifyNames);
                    if(statement != null) script.addCreateModuleGlobalVariableStatement(statement);
                } else {
                    String statement = builder.createGlobalVariable((LUWGlobalVariable) o, quoteIdentifiers, qualifyNames);
                    if(statement != null) script.addCreateGlobalVariableStatement(statement);
                    if (EngineeringOptionID.generateGrantStatement(options)) {
                		String[] grantStatements = builder.grantOn((LUWGlobalVariable) o, quoteIdentifiers, qualifyNames);
                		for (int igrant = 0; igrant <grantStatements.length; igrant ++) {
                			script.addGrantStatement(grantStatements[igrant]);
                		}
                	}

                }
            }
//            else if (o instanceof LUWArrayDataType){
//            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
//            	if (!EngineeringOptionID.generateUserDefinedTypes(options)) continue;
//            	
//                String statement = builder.createArrayDataType((LUWArrayDataType) o, quoteIdentifiers, qualifyNames);
//            	if (o instanceof LUWModuleArrayDataType) {
//	                if(statement != null) script.addCreateModuleTypeStatement(statement);
//            	} else {
//	                if(statement != null) script.addCreateUserDefinedTypeStatement(statement);
//            	}
//            }
//            else if (o instanceof LUWRowDataType){
//            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
//            	if (!EngineeringOptionID.generateUserDefinedTypes(options)) continue;
//            	
//                String statement = builder.createRowDataType((LUWRowDataType) o, quoteIdentifiers, qualifyNames);
//            	if (o instanceof LUWModuleRowDataType) {
//	                if(statement != null) script.addCreateModuleTypeStatement(statement);
//            	} else {
//	                if(statement != null) script.addCreateUserDefinedTypeStatement(statement);
//            	}
//            }
//            else if (o instanceof LUWCursorDataType){
//            	if (!EngineeringOptionID.generateCreateStatement(options)) continue;
//            	if (!EngineeringOptionID.generateUserDefinedTypes(options)) continue;
//            	
//                String statement = builder.createCursorDataType((LUWCursorDataType) o, quoteIdentifiers, qualifyNames);
//            	if (o instanceof LUWModuleCursorDataType) {
//	                if(statement != null) script.addCreateModuleTypeStatement(statement);
//            	} else {
//	                if(statement != null) script.addCreateUserDefinedTypeStatement(statement);
//            	}
//            }

            else if (o instanceof UserDefinedType) {
				userDefinedTypes.add((UserDefinedType)o);
			}
        }
        
        //sort the UserDefinedTypes	
		sortUDTS(sortedUserDefinedTypes,userDefinedTypes) ;
		
		// generated the ddl
		for (int j = 0; j < sortedUserDefinedTypes.size(); j++) {
			UserDefinedType type = (UserDefinedType) sortedUserDefinedTypes.toArray()[j];
			if (type instanceof StructuredUserDefinedType) {
				if (!EngineeringOptionID.generateCreateStatement(options)) continue;
				if (!EngineeringOptionID.generateUserDefinedTypes(options)) continue;
				String statement = builder.createStructuredUserDefinedType( 
						(StructuredUserDefinedType) type, quoteIdentifiers, qualifyNames);
				if (statement != null)
					script.addCreateUserDefinedTypeStatement(statement);
			} 
			else if(type instanceof DistinctUserDefinedType) {
				 if (!EngineeringOptionID.generateCreateStatement(options)) continue;
				 if (!EngineeringOptionID.generateUserDefinedTypes(options))  continue;
				 if (type instanceof LUWModuleDistinctType) {
					 String statement = builder.createModuleDistinctType((LUWModuleDistinctType) type,quoteIdentifiers, qualifyNames);
					 if(statement != null) 
						 script.addCreateModuleTypeStatement(statement);
				 	}
				 else {
				 	String statement = builder.createDistinctUserDefinedType((DistinctUserDefinedType)type, quoteIdentifiers, qualifyNames);
				 	if(statement != null)
				 		script.addCreateUserDefinedTypeStatement(statement);
				 }
			}
			else if (type instanceof LUWArrayDataType) {
				if (!EngineeringOptionID.generateCreateStatement(options)) continue;
				if (!EngineeringOptionID.generateUserDefinedTypes(options)) continue;
				String statement = builder
						.createArrayDataType((LUWArrayDataType) type, quoteIdentifiers, qualifyNames);
				if (type instanceof LUWModuleArrayDataType) {
					if (statement != null)
						script.addCreateModuleTypeStatement(statement);
				} else {
					if (statement != null)
						script.addCreateUserDefinedTypeStatement(statement);
				}
			} else if (type instanceof LUWRowDataType) {
				if (!EngineeringOptionID.generateCreateStatement(options)) continue;
				if (!EngineeringOptionID.generateUserDefinedTypes(options)) continue;

				String statement = builder.createRowDataType((LUWRowDataType) type, quoteIdentifiers, qualifyNames);
				if (type instanceof LUWModuleRowDataType) {
					if (statement != null)
						script.addCreateModuleTypeStatement(statement);
				} else {
					if (statement != null)
						script.addCreateUserDefinedTypeStatement(statement);
				}
			} else if (type instanceof LUWCursorDataType) {
				if (!EngineeringOptionID.generateCreateStatement(options)) continue;
				if (!EngineeringOptionID.generateUserDefinedTypes(options)) continue;

				String statement = builder.createCursorDataType(
						(LUWCursorDataType) type, quoteIdentifiers, qualifyNames);
				if (type instanceof LUWModuleCursorDataType) {
					if (statement != null)
						script.addCreateModuleTypeStatement(statement);
				} else {
					if (statement != null)
						script.addCreateUserDefinedTypeStatement(statement);
				}
			}
		}
		
        return script.getStatements();
    }
    
    private void sortUDTS(final List<UserDefinedType> sortedUserDefinedTypes,final List<UserDefinedType> selectedUserDefinedTypes){
		for (int i = 0; i < selectedUserDefinedTypes.size(); i++) {
			UserDefinedType type = selectedUserDefinedTypes.get(i);
			if (type instanceof DistinctUserDefinedType) {
				if(!sortedUserDefinedTypes.contains(type)){
					sortedUserDefinedTypes.add(type);
				}
				
			} else {
				List<UserDefinedType> dependency = new ArrayList<UserDefinedType>();
				dependency = getDependency(type, selectedUserDefinedTypes);
				if (dependency != null && dependency.size() > 0) {
					for(int j = 0; j < dependency.size(); j++){
						UserDefinedType dependType = (UserDefinedType)dependency.get(j);
						if(!sortedUserDefinedTypes.contains(dependType))
							sortedUserDefinedTypes.add(dependType);
					}
				}
				if(!sortedUserDefinedTypes.contains(type))
					sortedUserDefinedTypes.add(type);
			}
		}
	}
	
	private List<UserDefinedType> getDependency(UserDefinedType type, List<UserDefinedType> selectTypes) {
		List<UserDefinedType> dependency = new ArrayList<UserDefinedType>();
		List<UserDefinedType> dependencyAll = new ArrayList<UserDefinedType>();
		List<UserDefinedType> accessList = new ArrayList<UserDefinedType>();
		
		getDependency(type, dependencyAll,accessList);
		
		for (int i = 0; i < dependencyAll.size(); i++) {
			UserDefinedType dependType = dependencyAll.get(i);
			if (selectTypes.contains(dependType)) {
				dependency.add(dependType);
			}
		}
		return dependency;
	}

	private void getDependency(UserDefinedType type, List<UserDefinedType> dependencyList, List<UserDefinedType> accessList){
		if(type != null && !accessList.contains(type)) {
			accessList.add(type);
	    	if(type instanceof StructuredUserDefinedType){
	    		StructuredUserDefinedType structureType = (StructuredUserDefinedType)type;
	    		if(structureType.getSuper()!=null){
	    			this.getDependency(structureType.getSuper(), dependencyList, accessList);
	    		} 		
	    	}
	    	
	    	DependencyImpactAnalyst m_DepAnalayst = DependencyImpactAnalyst.getInstance();
	    	DependencyImpactDescription[] array = m_DepAnalayst.getDirectDependencies( type, -1 );
	    
	    	if(array!=null && array.length > 0){
	    		for(int i = array.length-1;i >= 0;i--){
	    			DependencyImpactDescription depend = (DependencyImpactDescription)array[i];
	    			EObject targetType = depend.getTarget();
	    			if(!accessList.contains(targetType)) {
		    			if(targetType instanceof UserDefinedType) {
		    				if(targetType instanceof StructuredUserDefinedType){
		    					StructuredUserDefinedType superType = ((StructuredUserDefinedType)targetType).getSuper();
		    					    if(targetType!=null && superType!= null)
		    					        this.getDependency(((StructuredUserDefinedType)depend.getTarget()).getSuper(), dependencyList, accessList);
		    				}
		    				dependencyList.add((UserDefinedType)targetType);
		    			}
	    			}
	    		}
	    	}  	    	
	    	if(!dependencyList.contains(type))
	    		dependencyList.add(type);
		}
    }
    
    
    //@d00058820gs: use new structure/methods
	private String[] dropStatements(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor, int task) {
    	Map patternMap = DdlGenerationUtility.getCodetemplatePatterns();
    	LUWDdlScript script = new LUWDdlScript();
        EngineeringOption[] options = this.getSelectedOptions(elements);
        //Iterator it = DdlGenerationUtility.getAllContainedDisplayableElementSet(elements).iterator();
        Iterator it = getAllContainedDisplayableElements(elements).iterator();
        while(it.hasNext()) {
            Object o = it.next();
            
            if(o instanceof LUWNickname) {
            	ExtendDdlBuilder extendDdlBuilder = DdlGenerationUtility.getNicknameDdlBuilder(this.product, this.version);
                if (extendDdlBuilder != null && EngineeringOptionID.getOptionValue(extendDdlBuilder.getOption(),options)) {
	                String statement = builder.dropNickname((LUWNickname) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) script.addDropNicknameStatement(statement);
                }
            }
            else if(o instanceof LUWServer) {
            	ExtendDdlBuilder extendDdlBuilder = DdlGenerationUtility.getRemoteServerDdlBuilder(this.product, this.version);
                if (extendDdlBuilder != null && EngineeringOptionID.getOptionValue(extendDdlBuilder.getOption(),options)) {
                	String statement = builder.dropRemoteServer((LUWServer) o, quoteIdentifiers, qualifyNames);                	
	                if(statement != null) script.addDropRemoteServerStatement(statement);
                }
            }
            else if(o instanceof LUWWrapper) {
                ExtendDdlBuilder extendDdlBuilder = DdlGenerationUtility.getWrapperDdlBuilder(this.product, this.version);
                if (extendDdlBuilder != null && EngineeringOptionID.getOptionValue(extendDdlBuilder.getOption(),options)) {
                    String statement = builder.dropWrapper((LUWWrapper) o, quoteIdentifiers, qualifyNames);                  
                    if(statement != null) script.addDropWrapperStatement(statement);
                }
            }            
            else if(o instanceof LUWUserMapping) {
                ExtendDdlBuilder extendDdlBuilder = DdlGenerationUtility.getUserMappingDdlBuilder(this.product, this.version);
                if (extendDdlBuilder != null && EngineeringOptionID.getOptionValue(extendDdlBuilder.getOption(),options)) {
                    String statement = builder.dropUserMapping((LUWUserMapping) o, quoteIdentifiers, qualifyNames);                  
                    if(statement != null) script.addDropUserMappingStatement(statement);
                }
            }            
            else if(o instanceof LUWMaterializedQueryTable) {
                if (!EngineeringOptionID.generateMQTs(options)) continue;
                String statement = builder.dropMQT((LUWMaterializedQueryTable) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addDropViewStatement(statement);
            }
            else if(o instanceof LUWDatabasePackage) {
                if (!EngineeringOptionID.generatePacakges(options)) continue;
                String statement = builder.dropPackage((LUWDatabasePackage) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addDropPackageStatement(statement);
            }
            else if(o instanceof LUWTable) {
            	if (!EngineeringOptionID.generateTables(options)) continue;
                String statement = builder.dropTable((LUWTable) o, quoteIdentifiers, qualifyNames);
                if(statement != null) {
                	String prolog = builder.createTableCodetemplate((LUWTable)o, DdlGenerationUtility.GENERATE_DROP_PATTERN, true,patternMap);
                	String postlog = builder.createTableCodetemplate((LUWTable)o, DdlGenerationUtility.GENERATE_DROP_PATTERN, false,patternMap);
            		script.addDropTableStatements(statement,
            				script.combineTemplateStatements(prolog,statement,postlog));
                }

            }
            else if(o instanceof LUWView) {
            	if (!EngineeringOptionID.generateViews(options)) continue;
                String statement = builder.dropView((LUWView) o, quoteIdentifiers, qualifyNames);
                if(statement != null) {
                	String prolog = builder.createViewCodetemplate((LUWView)o, DdlGenerationUtility.GENERATE_DROP_PATTERN, true,patternMap);
                	String postlog = builder.createViewCodetemplate((LUWView)o, DdlGenerationUtility.GENERATE_DROP_PATTERN, false,patternMap);
            		script.addDropViewStatements(statement,
            				script.combineTemplateStatements(prolog,statement,postlog));
                }

            }
            else if(o instanceof DB2Alias) {
            	//if (!EngineeringOptionID.generateTables()) continue;
                if (!EngineeringOptionID.generateAliases(options)) continue;
                String statement = builder.dropAlias((DB2Alias) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addDropViewStatement(statement);
            }
            else if(o instanceof DB2Procedure) {
        		if (!EngineeringOptionID.generateStoredProcedures(options)) continue;
               	String statement = "";
            	if ( o instanceof FederatedProcedure)  // fsp - pyl @wsdbu00079822 
            	{
                    statement = builder.dropProcedure((DB2Procedure) o, quoteIdentifiers, qualifyNames);
                	if (statement != null) script.addDropRoutineStatement(statement);
                } else if (o instanceof LUWModuleProcedure){
                    statement = builder.dropProcedure((DB2Procedure) o, quoteIdentifiers, qualifyNames);
                	if (statement != null) script.addDropModuleRoutineStatement(statement);
                }
            	else
            	{
            		statement = builder.dropProcedure((DB2Procedure) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) {
	                	String prolog = builder.createRoutineCodetemplate((DB2Procedure)o, DdlGenerationUtility.GENERATE_DROP_PATTERN, true,patternMap);
	                	String postlog = builder.createRoutineCodetemplate((DB2Procedure)o, DdlGenerationUtility.GENERATE_DROP_PATTERN, false,patternMap);
	               		script.addDropRoutineStatements(statement,
	               				script.combineTemplateStatements(prolog,statement,postlog));
	                }
            	}
            }
            else if(o instanceof DB2UserDefinedFunction) {
            	if (!EngineeringOptionID.generateFunctions(options)) continue;
                String statement = builder.dropFunction((DB2UserDefinedFunction) o, quoteIdentifiers, qualifyNames);
                if(statement != null) {
                	if (o instanceof LUWModuleFunction){
                		statement = builder.dropFunction((DB2UserDefinedFunction) o, quoteIdentifiers, qualifyNames);
                		script.addDropModuleRoutineStatement(statement);
                	} else {
	                	String prolog = builder.createRoutineCodetemplate((DB2UserDefinedFunction)o, DdlGenerationUtility.GENERATE_DROP_PATTERN, true,patternMap);
	                	String postlog = builder.createRoutineCodetemplate((DB2UserDefinedFunction)o, DdlGenerationUtility.GENERATE_DROP_PATTERN, false,patternMap);
	               		script.addDropRoutineStatements(statement,
	               				script.combineTemplateStatements(prolog,statement,postlog));
                	}
                }
            }
            else if(o instanceof DB2Trigger) {
            	if (!EngineeringOptionID.generateTriggers(options)) continue;
                String statement = builder.dropTrigger((DB2Trigger) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addDropTriggerStatement(statement);
            }
            else if(o instanceof CheckConstraint) {
                if(!EngineeringOptionID.generateCKConstraints(options)) continue;
                String statement = builder.dropTableConstraint((CheckConstraint) o, quoteIdentifiers, qualifyNames);
                if(statement != null) {
                	String prolog = builder.createConstraintCodetemplate((CheckConstraint)o, DdlGenerationUtility.GENERATE_DROP_PATTERN, true,patternMap);
                	String postlog = builder.createConstraintCodetemplate((CheckConstraint)o, DdlGenerationUtility.GENERATE_DROP_PATTERN, false,patternMap);
               		script.addAlterTableDropConstraintStatements(statement,
            				script.combineTemplateStatements(prolog,statement,postlog));
                }
            }
            else if(o instanceof UniqueConstraint) {
            	if(!EngineeringOptionID.generatePKConstraints(options) || builder.isImplicitPK((UniqueConstraint)o)) continue;
                String statement = builder.dropTableConstraint((UniqueConstraint) o, quoteIdentifiers, qualifyNames);
                if(statement != null){
                	String prolog = builder.createConstraintCodetemplate((UniqueConstraint)o, DdlGenerationUtility.GENERATE_DROP_PATTERN, true,patternMap);
                	String postlog = builder.createConstraintCodetemplate((UniqueConstraint)o, DdlGenerationUtility.GENERATE_DROP_PATTERN, false,patternMap);
               		script.addAlterTableDropConstraintStatements(statement,
            				script.combineTemplateStatements(prolog,statement,postlog));
                }
            }
            else if(o instanceof ForeignKey) {
            	if(!EngineeringOptionID.generateFKConstraints(options)) continue;
                String statement = builder.dropTableConstraint((ForeignKey) o, quoteIdentifiers, qualifyNames);
                if(statement != null) {
                	String prolog = builder.createConstraintCodetemplate((ForeignKey)o, DdlGenerationUtility.GENERATE_DROP_PATTERN, true,patternMap);
                	String postlog = builder.createConstraintCodetemplate((ForeignKey)o, DdlGenerationUtility.GENERATE_DROP_PATTERN, false,patternMap);
            		script.addAlterTableDropForeignKeyStatements(statement,
            				script.combineTemplateStatements(prolog,statement,postlog));
                }
            }
            else if(o instanceof DB2Index) {
            	if (!EngineeringOptionID.generateIndexes(options) || ((DB2Index)o).isSystemGenerated() || ((DB2Index)o).getIndexType() != DB2IndexType.REGULAR_LITERAL) continue;
                String statement = builder.dropIndex((DB2Index) o, quoteIdentifiers, qualifyNames);
                if(statement != null) {
                    if (((DB2Index)o).getTable() instanceof LUWTable) {
                    	script.addDropIndexStatement(statement);
                    } else {
                    	script.addDropViewIndexStatement(statement);
                    }
                }
            }
            else if(o instanceof LUWTableSpace) {
            	if (!EngineeringOptionID.generateTablespaces(options)) continue;
                String statement = builder.dropTablespace((LUWTableSpace) o, quoteIdentifiers);
                if(statement != null) script.addDropTablespaceStatement(statement);
            }
            else if(o instanceof Sequence) {
            	if (!EngineeringOptionID.generateSequences(options)) continue;
                String statement = builder.dropSequence((Sequence) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addDropSequenceStatement(statement);
            }
            else if(o instanceof UserDefinedType) {
            	if (!EngineeringOptionID.generateUserDefinedTypes(options)) continue;
                String statement = builder.dropUserDefinedType((UserDefinedType) o, quoteIdentifiers, qualifyNames);
                if (o instanceof LUWModuleObject) {
                	if(statement != null) script.addDropModuleTypeStatement(statement);
                } else {
                	if(statement != null) script.addDropUserDefinedTypeStatement(statement);
                }
            }
            else if(o instanceof LUWBufferPool) {
                if(!EngineeringOptionID.generateBufferPool(options)) continue;
                String statement = builder.dropBufferPool((LUWBufferPool) o, quoteIdentifiers);
                if(statement != null) script.addDropBufferPoolStatement(statement);
            }
//            xml schema drop done via stored procedure now          
//            else if(o instanceof LUWCatalogXmlSchema) {
//            	String statement = ((LUWDdlBuilder)builder).dropXMLSchema((LUWCatalogXmlSchema)o, quoteIdentifiers, qualifyNames);
//            	if (statement != null) script.addDropXMLSchemaStatement(statement);
//            }
            else if(o instanceof LUWPartitionGroup) {
                if(!EngineeringOptionID.generatePartitionGroup(options)) continue;
                String statement = builder.dropPartitionGroup((LUWPartitionGroup) o, quoteIdentifiers);
                if(statement != null) script.addDropPartitionStatement(statement);
            }
            else if(o instanceof Schema) {
            	//if(!this.generateFullyQualifiedNames()) continue;
                if(!EngineeringOptionID.generateSchemas(options)) continue;
                String statement = builder.dropSchema((Schema) o, quoteIdentifiers,qualifyNames);
                if(statement != null) script.addDropSchemaStatement(statement);
            }
            else if(o instanceof Privilege) {
            	//if(!this.generateFullyQualifiedNames()) continue;
                if(!EngineeringOptionID.generateRevokeStatement(options)) continue;
                String statement = builder.getRevokePrivilegeStatement((Privilege)o,quoteIdentifiers,qualifyNames);
                if(statement != null) script.addRevokeStatement(statement);
            }
            else if(o instanceof RoleAuthorization) {
                if (!EngineeringOptionID.generateRevokeStatement(options)) continue;
                String statement = builder.getRevokeRoleAuthorizationStatement((RoleAuthorization)o,quoteIdentifiers);
                if(statement != null) script.addRevokeStatement(statement);
            }
            else if (o instanceof AuthorizationIdentifier) {
            	((AuthorizationIdentifier) o).getDatabase().getPrivileges();
                if(EngineeringOptionID.generateRevokeStatement(options)) {
                	Iterator pIt = ((AuthorizationIdentifier)o).getReceivedPrivilege().iterator();
                	while (pIt.hasNext()) {
                		String statement = builder.getRevokePrivilegeStatement((Privilege)pIt.next(),quoteIdentifiers,qualifyNames);
                		if (statement != null) {
                			script.addRevokeStatement(statement);	
                		}                		
                	}
//                	//Delete Role Authorizations for User
//                	Iterator pRolesIt = ((AuthorizationIdentifier)o).getReceivedRoleAuthorization().iterator();
//                	while (pRolesIt.hasNext()) {
//                		script.addRevokeStatement(builder.getRevokeRoleAuthorizationStatement((RoleAuthorization) pRolesIt.next(),quoteIdentifiers));
//                	}
                }
                if(o instanceof Role) {
                    if (!EngineeringOptionID.generateRoles(options)) continue;
                    String statement = builder.dropRole((Role)o, quoteIdentifiers);
                    if(statement != null) script.addDropRoleStatement(statement);
                }
            }
            else if (o instanceof PLSQLPackage) {
            	if (!EngineeringOptionID.generatePacakges(options)) continue;
                String statement = builder.dropPlsqlPackage((PLSQLPackage) o, quoteIdentifiers, qualifyNames);
                if (statement != null) script.addDropPlsqlPackageStatement(statement);
            }
            else if (o instanceof PLSQLPackageBody) {
            	if (!EngineeringOptionID.generatePacakgeBodys(options)) continue;
                String statement = builder.dropPlsqlPackageBody((PLSQLPackageBody)o, quoteIdentifiers, qualifyNames);
                if (statement != null) script.addDropPlsqlPackageBodyStatement(statement);
            }
            else if(o instanceof LUWModule) {
                if (!EngineeringOptionID.generateModules(options)) continue;
                String statement = builder.dropModule((LUWModule)o,quoteIdentifiers,qualifyNames);
                if(statement != null) script.addDropModuleStatement(statement);
            }
            else if(o instanceof LUWModuleCondition) {
                if (!EngineeringOptionID.generateModuleConditions(options)) continue;
                String statement = builder.dropModuleCondition((LUWModuleCondition)o,quoteIdentifiers,qualifyNames);
                if(statement != null) script.addDropModuleConditionStatement(statement);
            }
            else if(o instanceof LUWGlobalVariable) {
                if (!EngineeringOptionID.generateGlobalVariables(options)) continue;
                
                if (o instanceof LUWModuleGlobalVariable) {
                    String statement = builder.dropModuleGlobalVariable((LUWModuleGlobalVariable) o, quoteIdentifiers, qualifyNames);
                    if(statement != null) script.addDropModuleGlobalVariableStatement(statement);
                } else {
                    String statement = builder.dropGlobalVariable((LUWGlobalVariable) o, quoteIdentifiers, qualifyNames);
                    if(statement != null) script.addDropGlobalVariableStatement(statement);
                }
            }
        }
        return script.getStatements();
    }

	//Fix for wsdbu00241490 ODA2201: ODE UI should not allow user to drop schema nullid.
	//This is a modified version of the DdlGenerationUtility.getAllContainedDisplayableElementSet(elements)
	//method. Its purpose is to avoid dropping the contained elements when dropping NULLID schema.
	//The effect is the DDL for dropping NULLID will still be generated but it will fail 
	//to execute because DB2 will find out there are dependents still depending on the NULLID.
	protected Collection getAllContainedDisplayableElements(SQLObject[] elements) {
		ArrayList<SQLObject> temp = new ArrayList<SQLObject>();
		// Is there a NULLID schema in the input array?
		SQLObject nullid = null;
		for (SQLObject sqlobj: elements) {
			if (!(sqlobj instanceof Schema)
					|| !"NULLID".equals(sqlobj.getName())) {
				// collect all non-NULLID schema objects into the temp list
				temp.add(sqlobj);
			} else {
				nullid = sqlobj;
			}
		}

		if (nullid == null) {
			// there is no NULLID schema in the input array, business as usual
			return DdlGenerationUtility
					.getAllContainedDisplayableElementSet(elements);
		} else {
			// found the NULLID schema in the input array
			SQLObject[] elementsWithoutNullId = new SQLObject[temp.size()];
			temp.toArray(elementsWithoutNullId);
			ArrayList temp2 = new ArrayList();
			temp2.addAll(DdlGenerationUtility
					.getAllContainedDisplayableElementSet(elementsWithoutNullId));
			temp2.add(nullid);
			return temp2;
		}
	}
	
    //@Override
	public String[] alterTableDropColumn(SQLObject[] columns, SQLObject[] impacts, IProgressMonitor progressMonitor, IEngineeringCallBack callback) {
		LUWDdlScript script = new LUWDdlScript();
		for (int col = 0; col < columns.length; ++col) {
			Column o = (Column)columns[col];
        	String statement = builder.alterTableDropColumn((Column)o, true, true);
            if(statement != null) {
            	script.addAlterTableDropColumnStatement(statement);
            	script.addAlterTableDropColumnStatement(builder.reorgTable(o,true,true));
            }
		}
		return script.getStatements();
	}

	private String[] commentStatements(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor, int task) {
        Vector processedTablespace = new Vector();
        LUWDdlScript script = new LUWDdlScript();
        
        Iterator it = LUWDdlGenerator.getAllContainedDisplayableElementSet(elements).iterator();        
        EngineeringOption[] options = this.getSelectedOptions(elements);        
        while(it.hasNext()) {
            Object o = it.next();
            
            if(o instanceof LUWNickname) {
                ExtendDdlBuilder extendDdlBuilder = DdlGenerationUtility.getNicknameDdlBuilder(this.product, this.version);
                if (extendDdlBuilder != null && EngineeringOptionID.getOptionValue(extendDdlBuilder.getOption(),options)) {
                     String statement = builder.commentOn((LUWNickname) o, quoteIdentifiers, qualifyNames);
                     if(statement != null) script.addCommentOnStatement(statement);
                }
            }
            else if(o instanceof LUWServer) {
                LUWServer luwServer = (LUWServer)o;
                ExtendDdlBuilder extendDdlBuilder = DdlGenerationUtility.getRemoteServerDdlBuilder(this.product, this.version);
                if (extendDdlBuilder != null && EngineeringOptionID.getOptionValue(extendDdlBuilder.getOption(),options)) {
                    String statement = builder.commentOn(luwServer, quoteIdentifiers, qualifyNames);                       
                    if(statement != null) script.addCommentOnStatement(statement);
                }
            }
            else if(o instanceof LUWWrapper) {
                LUWWrapper wrapper = (LUWWrapper) o;                    
                ExtendDdlBuilder extendDdlBuilder = DdlGenerationUtility.getWrapperDdlBuilder(this.product, this.version);
                if (extendDdlBuilder != null && EngineeringOptionID.getOptionValue(extendDdlBuilder.getOption(),options)) {
                    String statement = builder.commentOn(wrapper, quoteIdentifiers, qualifyNames);                        
                    if(statement != null) script.addCommentOnStatement(statement);
                }
            }
            else if(o instanceof LUWMaterializedQueryTable) {
                if (!EngineeringOptionID.generateMQTs(options)) continue;
                String statement = builder.commentOn((LUWMaterializedQueryTable) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof LUWTable) {
                if (!EngineeringOptionID.generateTables(options)) continue;
                String statement = builder.commentOn((LUWTable) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof LUWTableSpace) {
                if (!EngineeringOptionID.generateTablespaces(options)) continue;
                String statement = builder.commentOn((LUWTableSpace) o, quoteIdentifiers);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof LUWView) {
                if (!EngineeringOptionID.generateViews(options)) continue;
                String statement = builder.commentOn((LUWView) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof DB2Alias) {
                if (!EngineeringOptionID.generateAliases(options)) continue;
                String statement = builder.commentOn((DB2Alias) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof DB2Procedure) {
                if (!EngineeringOptionID.generateStoredProcedures(options)) continue;
                String statement = builder.commentOn((DB2Procedure) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof DB2UserDefinedFunction) {
                if (!EngineeringOptionID.generateFunctions(options)) continue;
                String statement = builder.commentOn((DB2UserDefinedFunction) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof DB2Trigger) {
                if (!EngineeringOptionID.generateTriggers(options)) continue;
                String statement = builder.commentOn((DB2Trigger) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof CheckConstraint) {
                if(!EngineeringOptionID.generateCKConstraints(options)) continue;
                String statement = builder.commentOn((CheckConstraint) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof UniqueConstraint) {
                if(!EngineeringOptionID.generatePKConstraints(options) || builder.isImplicitPK((UniqueConstraint)o)) continue;
                String statement = builder.commentOn((UniqueConstraint) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof ForeignKey) {
                if(!EngineeringOptionID.generateFKConstraints(options)) continue;
                String statement = builder.commentOn((ForeignKey) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof DB2Index) {
                if (!EngineeringOptionID.generateIndexes(options) || ((DB2Index)o).isSystemGenerated() || ((DB2Index)o).getIndexType() != DB2IndexType.REGULAR_LITERAL) continue;
                String statement = builder.commentOn((DB2Index) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof DistinctUserDefinedType) {
                if (!EngineeringOptionID.generateUserDefinedTypes(options)) continue;
                String statement = builder.commentOn((DistinctUserDefinedType) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if (o instanceof StructuredUserDefinedType){
                if (!EngineeringOptionID.generateUserDefinedTypes(options)) continue;
                String statement = builder.commentOn((StructuredUserDefinedType) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof Column) {
                if (!EngineeringOptionID.generateTables(options)) continue;
                String statement = builder.commentOn((Column) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof LUWPartitionGroup) {
                if (!EngineeringOptionID.generatePartitionGroup(options)) continue;
                String statement = builder.commentOn((LUWPartitionGroup) o, quoteIdentifiers);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof Schema) {
                //if(!EngineeringOptionID.generateFullyQualifiedNames()) continue;
                if(!EngineeringOptionID.generateSchemas(options)) continue;
                String statement = builder.commentOn((Schema) o, quoteIdentifiers,qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof Role) {
                if (!EngineeringOptionID.generateRoles(options)) continue;
                String statement = builder.commentOn((Role)o, quoteIdentifiers,qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof LUWModule) {
                if (!EngineeringOptionID.generateModules(options)) continue;
                String statement = builder.commentOn((LUWModule)o, quoteIdentifiers,qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof LUWModuleCondition) {
                if (!EngineeringOptionID.generateModuleConditions(options)) continue;
                String statement = builder.commentOn((LUWModuleCondition)o, quoteIdentifiers,qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
            else if(o instanceof Sequence) {
                if (!EngineeringOptionID.generateSequences(options)) continue;
                String statement = builder.commentOn((Sequence)o, quoteIdentifiers,qualifyNames);
                if(statement != null) script.addCommentOnStatement(statement);
            }
        }
        return script.getStatements();
    }

    private String[] updateStatisticsStatements(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor,IEngineeringCallBack callback) {
        Vector processedTablespace = new Vector();
        LUWDdlScript script = new LUWDdlScript();
        
        Iterator it = LUWDdlGenerator.getAllContainedDisplayableElementSet(elements).iterator();        
        EngineeringOption[] options = this.getSelectedOptions(elements);        
        while(it.hasNext()) {
            Object o = it.next();
        	if(o instanceof LUWNickname) {
        		String[] statements = builder.updateStatistics((LUWNickname) o, quoteIdentifiers, qualifyNames);
            	if (statements  != null) {
	        		for (int iStat = 0; iStat <statements.length; iStat ++) {
	        			script.addUpdateStatisticsStatement(statements[iStat]);
	        		}
            	}
        	} else if(o instanceof LUWMaterializedQueryTable) {
                if (!EngineeringOptionID.generateMQTs(options)) continue;
            	String[] statements = builder.updateStatistics((LUWMaterializedQueryTable) o, quoteIdentifiers, qualifyNames);
            	if (statements  != null) {
            		for (int iStat = 0; iStat <statements.length; iStat ++) {
            			script.addUpdateStatisticsStatement(statements[iStat]);
            	        
            		}
            	}
            }else if(o instanceof LUWTable) {
                if (!EngineeringOptionID.generateTables(options)) continue;
            	String[] statements = builder.updateStatistics((LUWTable) o, quoteIdentifiers, qualifyNames);
            	if (statements  != null) {
	        		for (int iStat = 0; iStat <statements.length; iStat ++) {
	        			script.addUpdateStatisticsStatement(statements[iStat]);
	        		}
            	}
            }else if(o instanceof DB2Procedure) {
                if (!EngineeringOptionID.generateStoredProcedures(options)) continue;
            	String[] statements = builder.updateStatistics((DB2Procedure) o, quoteIdentifiers, qualifyNames);
            	if (statements  != null) {
	        		for (int iStat = 0; iStat <statements.length; iStat ++) {
	        			script.addUpdateStatisticsStatement(statements[iStat]);
	        		}
            	}
            }
            else if(o instanceof DB2UserDefinedFunction) {
                if (!EngineeringOptionID.generateFunctions(options)) continue;
            	String[] statements = builder.updateStatistics((DB2UserDefinedFunction) o, quoteIdentifiers, qualifyNames);
            	if (statements  != null) {
	        		for (int iStat = 0; iStat <statements.length; iStat ++) {
	        			script.addUpdateStatisticsStatement(statements[iStat]);
	        		}
            	}
            }
            else if(o instanceof DB2Index) {
                if (!EngineeringOptionID.generateIndexes(options)) continue;
            	String[] statements = builder.updateStatistics((DB2Index) o, quoteIdentifiers, qualifyNames);
            	if (statements  != null) {
	        		for (int iStat = 0; iStat <statements.length; iStat ++) {
	        			script.addUpdateStatisticsStatement(statements[iStat]);
	        		}
            	}
            }
            else if(o instanceof Column) {
                if (!EngineeringOptionID.generateTables(options)) continue;
            	String[] statements = builder.updateStatistics((Column) o, quoteIdentifiers, qualifyNames);
            	if (statements  != null) {
	        		for (int iStat = 0; iStat <statements.length; iStat ++) {
	        			script.addUpdateStatisticsStatement(statements[iStat]);
	        		}
            	}
            }
        }
        return script.getStatements();
    }

    
    //@bd00058820gs
	private static Set getAllContainedDisplayableElementSet(SQLObject[] elements) {
        Set s = new LinkedHashSet();
        for(int i=0; i<elements.length; ++i) {
            if (!s.contains(elements[i])) s.add(elements[i]);
            if (elements[i] instanceof LUWNickname) {
                //Server Dependency
                LUWServer luwServer = ((LUWNickname)elements[i]).getServer();
                if (luwServer != null) {
                    s.add(luwServer);
                    //Wrapper Dependency
                    LUWWrapper wrapper = luwServer.getWrapper();
                    if (wrapper != null) {
                        s.add(wrapper);
                    }
                    //User Mapping Dependency
                    for (Iterator itUserMapping = luwServer.getUserMappings().iterator(); itUserMapping.hasNext(); ) {
                         LUWUserMapping userMapping = (LUWUserMapping)itUserMapping.next();
                         s.add(userMapping);
                         break; //just ask one time for the authorization dialog
                    }
                }
            }
            // fsp - pyl
            else if (elements[i] instanceof FederatedProcedure) {
                    //Server Dependency
                    LUWServer luwServer = ((LUWCatalogFederatedProcedure)elements[i]).getFederatedServer();                   
                    if (luwServer != null) {
                        s.add(luwServer);
                        //Wrapper Dependency
                        LUWWrapper wrapper = luwServer.getWrapper();
                        if (wrapper != null) {
                            s.add(wrapper);
                        }
                        //User Mapping Dependency
                        for (Iterator itUserMapping = luwServer.getUserMappings().iterator(); itUserMapping.hasNext(); ) {
                             LUWUserMapping userMapping = (LUWUserMapping)itUserMapping.next();
                             s.add(userMapping);
                             break; //just ask one time for the authorization dialog
                        }
                    }
            } else if (elements[i] instanceof LUWStorageTable){
            	// wsdbu00273864 and wsdbu00652103 : To read Data Partition Table Space info
            	LUWStorageTable lst=(LUWStorageTable)elements[i];
            	if(!s.contains(lst.getLOBDataTableSpace())) s.add(lst.getLOBDataTableSpace());
            	if(!s.contains(lst.getIndexDataTableSpace())) s.add(lst.getIndexDataTableSpace());
            	if(!s.contains(lst.getRegularDataTableSpace())) s.add(lst.getRegularDataTableSpace());
            	if (lst.getDataPartitions().size() != 0){	// For DB2 version <9, DataPartitions size will be 0
        			Iterator it = lst.getDataPartitions().iterator();
        			while(it.hasNext()){
        				LUWDataPartition dp = (LUWDataPartition)it.next();
        				if(!s.contains(dp.getRegularDataTableSpace()))
        					s.add(dp.getRegularDataTableSpace());
        				if(!s.contains(dp.getLOBDataTableSpace()))
        					s.add(dp.getLOBDataTableSpace());
        			}
        		}
            } else if (elements[i] instanceof LUWServer) {
                LUWServer luwServer = (LUWServer)elements[i];
                //Wrapper Dependency
                LUWWrapper wrapper = luwServer.getWrapper();
                if (wrapper != null) {
                    s.add(wrapper);
                }
                //User Mapping Dependency
                for (Iterator itUserMapping = luwServer.getUserMappings().iterator(); itUserMapping.hasNext(); ) {
                     LUWUserMapping userMapping = (LUWUserMapping)itUserMapping.next();
                     s.add(userMapping);
                     break; //just ask one time for the authorization dialog
                }
                //Nickname Dependency
                for (Iterator itNickname = luwServer.getNicknames().iterator(); itNickname.hasNext(); ) {
                    LUWNickname nickame = (LUWNickname)itNickname.next();
                    s.add(nickame);
                }
            }
            else if (elements[i] instanceof LUWWrapper) {
                LUWWrapper luwWrapper = (LUWWrapper)elements[i];
                //Remote Servers Dependency
                for (Iterator itServer = luwWrapper.getServers().iterator(); itServer.hasNext(); ) {
                    LUWServer luwServer = (LUWServer)itServer.next();
                    s.add(luwServer);
                                
                    //User Mapping Dependency
                    for (Iterator itUserMapping = luwServer.getUserMappings().iterator(); itUserMapping.hasNext(); ) {
                         LUWUserMapping userMapping = (LUWUserMapping)itUserMapping.next();
                         s.add(userMapping);
                         break; //just ask one time for the authorization dialog
                    }
                    //Nickname Dependency
                    for (Iterator itNickname = luwServer.getNicknames().iterator(); itNickname.hasNext(); ) {
                        LUWNickname nickame = (LUWNickname)itNickname.next();
                        s.add(nickame);
                    }
                }
            }
            else if (elements[i] instanceof PLSQLPackage) {
            	PLSQLPackage plsqlPkg = (PLSQLPackage) elements[i];
            	PLSQLPackageBody pkgBody = plsqlPkg.getPackageBody();
            	if (pkgBody != null) s.add(pkgBody);
            }
            Iterator iter = null;
            if (elements[i] instanceof AuthorizationIdentifier) 
            	iter = ContainmentServiceImpl.INSTANCE.getAllContainedElements(elements[i]).iterator();
            else iter = ContainmentServiceImpl.INSTANCE.getAllContainedDisplayableElements(elements[i]).iterator();
//          s.addAll(ContainmentServiceImpl.INSTANCE.getAllContainedDisplayableElements(elements[i]));
            while (iter.hasNext()){

            	Object element = iter.next();
            	if (! s.contains(element)) s.add(element);
                if (element instanceof LUWNickname) {
                    //Server Dependency
                    LUWServer luwServer = ((LUWNickname)element).getServer();
                    if (luwServer != null) {
                        s.add(luwServer);
                        //Wrapper Dependency
                        LUWWrapper wrapper = luwServer.getWrapper();
                        if (wrapper != null) {
                            s.add(wrapper);
                        }
                        //User Mapping Dependency
                        for (Iterator itUserMapping = luwServer.getUserMappings().iterator(); itUserMapping.hasNext(); ) {
                             LUWUserMapping userMapping = (LUWUserMapping)itUserMapping.next();
                             s.add(userMapping);
                             break; //just ask one time for the authorization dialog
                        }
                    }
                } else if (element instanceof LUWStorageTable){
                	// wsdbu00273864 and wsdbu00652103 : To read Data Partition Table Space info
                	LUWStorageTable lst=(LUWStorageTable)element;
                	if(!s.contains(lst.getLOBDataTableSpace())) s.add(lst.getLOBDataTableSpace());
                	if(!s.contains(lst.getIndexDataTableSpace())) s.add(lst.getIndexDataTableSpace());
                	if(!s.contains(lst.getRegularDataTableSpace())) s.add(lst.getRegularDataTableSpace());
                	if (lst.getDataPartitions().size() != 0){	// For DB2 version <9, DataPartitions size will be 0
            			Iterator it = lst.getDataPartitions().iterator();
            			while(it.hasNext()){
            				LUWDataPartition dp = (LUWDataPartition)it.next();
            				if(!s.contains(dp.getRegularDataTableSpace()))
            					s.add(dp.getRegularDataTableSpace());
            				if(!s.contains(dp.getLOBDataTableSpace()))
            					s.add(dp.getLOBDataTableSpace());
            			}
            		}
                } else if (element instanceof LUWServer) {
                    LUWServer luwServer = (LUWServer)element;
                    //Wrapper Dependency
                    LUWWrapper wrapper = luwServer.getWrapper();
                    if (wrapper != null) {
                        s.add(wrapper);
                    }
                    //User Mapping Dependency
                    for (Iterator itUserMapping = luwServer.getUserMappings().iterator(); itUserMapping.hasNext(); ) {
                         LUWUserMapping userMapping = (LUWUserMapping)itUserMapping.next();
                         s.add(userMapping);
                         break; //just ask one time for the authorization dialog
                    }
                    //Nickname Dependency
                    for (Iterator itNickname = luwServer.getNicknames().iterator(); itNickname.hasNext(); ) {
                        LUWNickname nickame = (LUWNickname)itNickname.next();
                        s.add(nickame);
                    }
                }
                else if (element instanceof LUWWrapper) {
                    LUWWrapper luwWrapper = (LUWWrapper)element;
                    //Remote Servers Dependency
                    for (Iterator itServer = luwWrapper.getServers().iterator(); itServer.hasNext(); ) {
                        LUWServer luwServer = (LUWServer)itServer.next();
                        s.add(luwServer);
                                    
                        //User Mapping Dependency
                        for (Iterator itUserMapping = luwServer.getUserMappings().iterator(); itUserMapping.hasNext(); ) {
                             LUWUserMapping userMapping = (LUWUserMapping)itUserMapping.next();
                             s.add(userMapping);
                             break; //just ask one time for the authorization dialog
                        }
                        //Nickname Dependency
                        for (Iterator itNickname = luwServer.getNicknames().iterator(); itNickname.hasNext(); ) {
                            LUWNickname nickame = (LUWNickname)itNickname.next();
                            s.add(nickame);
                        }
                    }
                }                
            }
        }
        return s;
    }
    
    private static void discover(Set result, Data element) {
    	Set childs = element.childs;
        while (childs != null && childs.size() > 0) {
        	result.addAll(element.sOptions);
        	for (Iterator it = childs.iterator(); it.hasNext(); ) {
        		discover(result, (Data)it.next());
        	}
        }
    }
    
    private static Set getAllContainedDisplayableElementSetDepedency(SQLObject[] elements) {
        SingletonOptionDependency sod = SingletonOptionDependency.getSingletonObject();
        
        //Set s = new LinkedHashSet();
        Set s = new TreeSet();
        for(int i=0; i<elements.length; ++i) {
            Class key = null;
            if(elements[i] instanceof LUWDatabase) {
                key = Database.class;
            } else if(elements[i] instanceof Schema) {
                key = Schema.class;
            } else if (elements[i] instanceof LUWTable) {
                if (elements[i] instanceof LUWNickname) {
                    key = LUWNickname.class;
                    isNickname = true; //@d00062627gs
                    //TODO: make sure to remove the columns, or leave LUWColumn line commented out
                } else
                    key = Table.class;
            } else if (elements[i] instanceof LUWServer) {
                key = LUWServer.class;
            } else if (elements[i] instanceof LUWWrapper) {
                key = LUWWrapper.class;
            } else if (elements[i] instanceof LUWUserMapping) { 
                key = LUWUserMapping.class;
            } else if (elements[i] instanceof DB2OLAPObject) {
                key = DB2OLAPObject.class;
            } else if (elements[i] instanceof LUWTableSpace) {
                key = LUWTableSpace.class;
            } else if (elements[i] instanceof DB2Index) {
                key = Index.class;
            } else if (elements[i] instanceof DB2Procedure) {
            	// fsp - pyl
            	if (elements[i] instanceof FederatedProcedure)
            		key = FederatedProcedure.class;
            	else
            		key = Procedure.class;
            } else if (elements[i] instanceof DB2UserDefinedFunction) {
                key = UserDefinedFunction.class;
            } else if (elements[i] instanceof LUWView) {
                key = ViewTable.class;
            } else if (elements[i] instanceof DB2Trigger) {
                key = Trigger.class;
            } else if (elements[i] instanceof Sequence) {
                key = Sequence.class;
            } else if (elements[i] instanceof UserDefinedType) {
                key = UserDefinedType.class;
//               } else if(elements[i] instanceof DistinctUserDefinedType)
//                } else if (elements[i] instanceof StructuredUserDefinedType)
            } else if (elements[i] instanceof UniqueConstraint) {
                key = UniqueConstraint.class;
//                } else if(elements[i] instanceof LUWSynonym)
//                    key = LUWSynonym.class;
            } else if(elements[i] instanceof LUWMaterializedQueryTable) {
                key = LUWMaterializedQueryTable.class;
            } else if(elements[i] instanceof DB2Alias) {
                key = DB2Alias.class;
            } else if(elements[i] instanceof CheckConstraint) {
                key = CheckConstraint.class;
            } else if(elements[i] instanceof ForeignKey) {
                key = ForeignKey.class;
            } else if(elements[i] instanceof LUWBufferPool) {
                key = LUWBufferPool.class;
            } else if(elements[i] instanceof LUWPartitionGroup) {
                key = LUWPartitionGroup.class;
	        } else if(elements[i] instanceof Column) {
	            key = Column.class;
            } else if(elements[i] instanceof Role) {
                key = Role.class;
            } else if(elements[i] instanceof RoleAuthorization) {
                key = RoleAuthorization.class;
            } else if(elements[i] instanceof User) {
                key = User.class;
            } else if(elements[i] instanceof Group) {
                key = Group.class;
            } else if(elements[i] instanceof Privilege) {
                key = Privilege.class;
            } else if(elements[i] instanceof LUWDatabasePackage) {
            	key = LUWDatabasePackage.class;
	        } else if(elements[i] instanceof PLSQLPackage) {
	        	key = PLSQLPackage.class;
	        } else if(elements[i] instanceof PLSQLPackageBody) {
	        	key = PLSQLPackageBody.class;
	        } else if(elements[i] instanceof LUWModule) {
	        	key = LUWModule.class;
	        } else if(elements[i] instanceof LUWModuleCondition) {
	        	key = LUWModuleCondition.class;
	        } else if(elements[i] instanceof LUWGlobalVariable) {
	        	key = LUWGlobalVariable.class;
	        }
            try {
                Data d = sod.getData(key); //Data d = sod.getData(elements[i].getClass());
                EngineeringOptionID.populateOptions(s, d.mask);
                //discover(s, d);
            } catch (Exception e) {
//                System.err.println("Missing definition for: " + elements[i].getClass().toString());
//               e.printStackTrace();
            }
        }
        return s;
    }
    //@ed00058820gs

    public EngineeringOption[] getOptions() {
        if(this.options == null) {
            ResourceBundle resource = ResourceBundle.getBundle("org.eclipse.datatools.enablement.internal.core.util.DdlGeneration"); //$NON-NLS-1$

            EngineeringOptionCategory[] categories = this.getOptionCategories();
            
            EngineeringOptionCategory general_options =null;
            EngineeringOptionCategory additional_element =null;
            for (int i = 0; i < categories.length; i++) {
            	if (categories[i].getId().equals(EngineeringOptionCategoryID.GENERATE_OPTIONS)){
            		general_options = categories[i];
            	} else if (categories[i].getId().equals(EngineeringOptionCategoryID.GENERATE_ELEMENTS)){
            		additional_element = categories[i];
            	}
            }
            
            Vector optionVec = new Vector();
            //DATABASE, SCHEMAS, MQT
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME,resource.getString("GENERATE_FULLY_QUALIFIED_NAME"), resource.getString("GENERATE_FULLY_QUALIFIED_NAME_DES"),DdlGenerationUtility.getQualifyNamesDefault(),general_options)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER,resource.getString("GENERATE_QUOTED_IDENTIFIER"), resource.getString("GENERATE_QUOTED_IDENTIFIER_DES"),DdlGenerationUtility.getQuoteIdentifiersDefault(),general_options)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_DROP_STATEMENTS,resource.getString("GENERATE_DROP_STATEMENTS"), resource.getString("GENERATE_DROP_STATEMENTS_DES"),false,general_options)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_CREATE_STATEMENTS,resource.getString("GENERATE_CREATE_STATEMENTS"), resource.getString("GENERATE_CREATE_STATEMENTS_DES"),true,general_options)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_COMMENTS,resource.getString("GENERATE_COMMENTS"), resource.getString("GENERATE_COMMENTS_DES"),true,general_options)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_PRIVILEGE,resource.getString("GENERATE_PRIVILEGE"), resource.getString("GENERATE_PRIVILEGE_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_IN_TABLESPACE_CLAUSE,resource.getString("GENERATE_IN_TABLESPACE_CLAUSE"), resource.getString("GENERATE_IN_TABLESPACE_CLAUSE_DES"),true,general_options)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_USE_DOMAIN_IF_EXIST,resource.getString("GENERATE_USE_DOMAIN_IF_EXIST"), resource.getString("GENERATE_USE_DOMAIN_IF_EXIST_DES"),false,general_options)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_TABLES,resource.getString("GENERATE_TABLES"), resource.getString("GENERATE_TABLES_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_TABLESPACES,resource.getString("GENERATE_TABLESPACES"), resource.getString("GENERATE_TABLESPACES_DES"),false,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_INDICES,resource.getString("GENERATE_INDEX"), resource.getString("GENERATE_INDEX_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_STOREDPROCEDURES,resource.getString("GENERATE_STOREDPROCEDURE"), resource.getString("GENERATE_STOREDPROCEDURE_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_FUNCTIONS,resource.getString("GENERATE_FUNCTION"), resource.getString("GENERATE_FUNCTION_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_VIEWS,resource.getString("GENERATE_VIEW"), resource.getString("GENERATE_VIEW_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_TRIGGERS,resource.getString("GENERATE_TRIGGER"), resource.getString("GENERATE_TRIGGER_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_PACKAGE,resource.getString("GENERATE_PACKAGE"), resource.getString("GENERATE_PACKAGE_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_SEQUENCES,resource.getString("GENERATE_SEQUENCE"), resource.getString("GENERATE_SEQUENCE_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_USER_DEFINED_TYPE,resource.getString("GENERATE_USER_DEFINED_TYPE"), resource.getString("GENERATE_USER_DEFINED_TYPE_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_PK_CONSTRAINTS,resource.getString("GENERATE_PK_CONSTRAINTS"), resource.getString("GENERATE_PK_CONSTRAINTS_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_FK_CONSTRAINTS,resource.getString("GENERATE_FK_CONSTRAINTS"), resource.getString("GENERATE_FK_CONSTRAINTS_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$

            OlapDdlBuilder olapDdlBuilder = DdlGenerationUtility.getOlapDdlBuilder(product, version);
            if (olapDdlBuilder != null) {
            	optionVec.add(new EngineeringOption(null,olapDdlBuilder.getOption(),olapDdlBuilder.getOptionDescription(),true,additional_element));
            }
            
            ExtendDdlBuilder nicknameDdlBuilder = DdlGenerationUtility.getNicknameDdlBuilder(product, version);
            if (nicknameDdlBuilder != null) {
            	optionVec.add(new EngineeringOption(null,nicknameDdlBuilder.getOption(),nicknameDdlBuilder.getOptionDescription(),true,additional_element));
            }

            // fsp - pyl 
            ExtendDdlBuilder federatedProcedureDdlBuilder = DdlGenerationUtility.getFederatedProcedureDdlBuilder(product, version);
            if (federatedProcedureDdlBuilder != null) {
            	optionVec.add(new EngineeringOption(null,federatedProcedureDdlBuilder.getOption(),federatedProcedureDdlBuilder.getOptionDescription(),true,additional_element));
            }
            
            ExtendDdlBuilder remoteServerDdlBuilder = DdlGenerationUtility.getRemoteServerDdlBuilder(product, version);
            if (remoteServerDdlBuilder != null) {
            	optionVec.add(new EngineeringOption(null,remoteServerDdlBuilder.getOption(),remoteServerDdlBuilder.getOptionDescription(),true,additional_element));
            }
            
            ExtendDdlBuilder wrapperDdlBuilder = DdlGenerationUtility.getWrapperDdlBuilder(product, version);
            if (wrapperDdlBuilder != null) {
                optionVec.add(new EngineeringOption(null,wrapperDdlBuilder.getOption(),wrapperDdlBuilder.getOptionDescription(),true,additional_element));
            }

            ExtendDdlBuilder userMappingDdlBuilder = DdlGenerationUtility.getUserMappingDdlBuilder(product, version);
            if (userMappingDdlBuilder != null) {
                optionVec.add(new EngineeringOption(null,userMappingDdlBuilder.getOption(),userMappingDdlBuilder.getOptionDescription(),true,additional_element));
            }
            
            this.options = new EngineeringOption[optionVec.size()];
            optionVec.copyInto(this.options);
        }
        
        return this.options;
    }

    //@bd00058820gs
    public EngineeringOption[] getOptions(SQLObject[] elements) {
        return getOptions(elements, false);
    }
    
    public EngineeringOption[] getOptions(SQLObject[] elements, boolean autoDiscovery) {
	    isNickname = false; //@d00062627gs
        EngineeringOptionCategory[] categories_new = this.getOptionCategories();
        
        EngineeringOptionCategory general_options =null;
        EngineeringOptionCategory additional_element =null;
        for (int i = 0; i < categories_new.length; i++) {
          if (categories_new[i].getId().equals(EngineeringOptionCategoryID.GENERATE_OPTIONS)){
              general_options = categories_new[i];
          } else if (categories_new[i].getId().equals(EngineeringOptionCategoryID.GENERATE_ELEMENTS)){
              additional_element = categories_new[i];
          }
        }           

        //Get the main object, the ones that do no have parents.
        //Look for dependency
        optionDependency = new OptionDependency(elements, autoDiscovery);
        Set sOptions = optionDependency.getOptions();
        int size = 0;
        EngineeringOption currentOption = null;
        Vector optionVec = new Vector();
        for (Iterator it=sOptions.iterator(); it.hasNext(); ) {
        	EngineeringOption ddlOption = DdlGenerationUtility.getEngineeringOption((String)it.next(), product, version, general_options, additional_element, elements);
            if (ddlOption != null) {
                optionVec.add(ddlOption);
            	if (ddlOption.getCategory().getId().equals(EngineeringOptionCategoryID.GENERATE_ELEMENTS)) {
            		size++;
            		currentOption = ddlOption;
	            	//Disable indices, primary key, check and foreign key constraints by default if Nickname is selected
	                if (isNickname) {
	                	String id = ddlOption.getId();
	                	if (id != null && (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_INDICES) ||
	                		id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PK_CONSTRAINTS) ||
	                		id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CK_CONSTRAINTS) ||
	                		id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FK_CONSTRAINTS))
						   )
	                		ddlOption.setBoolean(false);
	                }
            	}
            }
        }
        if (size == 1) currentOption.setBoolean(true);
        this.options = new EngineeringOption[optionVec.size()];
        optionVec.copyInto(this.options);
        return this.options;
    }
    
    public EngineeringOptionCategory[] getOptionCategories() {
        if(this.categories == null) {
            this.categories = DdlGenerationUtility.createDDLGenerationOptionCategories(this.product, this.version);
        }
        return this.categories;
    }
    
    //@bd00058820gs
    public EngineeringOption[] getSelectedOptions() {
        if (options == null)
            this.getOptions();
        return options;
    }

    public EngineeringOption[] getSelectedOptions(SQLObject[] elements) {
        if (options == null)
            if (optionDependency == null)
            	this.getOptions(elements);
            else
                this.getOptions();
        return options;
    }
    //@ed00058820gs
    
	private String product;
	private String version;
    private EngineeringOption[] options = null;
    private EngineeringOption[] defaultOptions = null;
    private EngineeringOptionCategory[] categories = null;
	private LUWDdlBuilder builder = null;
	private static boolean isNickname = false; //@d00062627gs
	
	//@bd00058820gs
    private OptionDependency optionDependency = null;
    
    public class OptionDependency {
        private SQLObject[] elements = null;
        private Set sOptions = new LinkedHashSet();
        private Set sElementsToUse = null;        

        public OptionDependency(SQLObject[] elements, boolean autoDiscovery)
        {
            this.elements = elements;
            sElementsToUse = new LinkedHashSet();
            doDiscovery(autoDiscovery);
        }

        private void doDiscovery(boolean autoDiscovery)
        {
        	  boolean hasStorage = (LUWCatalogDatabase.getCatalogStorageProvider() != null)? true : false; 

              setOption(EngineeringOptionID.CHECK_MODEL);
              setOption(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME);
              setOption(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER);
              setOption(EngineeringOptionID.GENERATE_DROP_STATEMENTS);
              setOption(EngineeringOptionID.GENERATE_CREATE_STATEMENTS);
              setOption(EngineeringOptionID.GENERATE_COMMENTS);
//              setOption(EngineeringOptionID.GENERATE_PRIVILEGE);
              setOption(EngineeringOptionID.GENERATE_STATISTICS);

              Set additionalOptions = new TreeSet();
              if (autoDiscovery) {
                  Iterator it = LUWDdlGenerator.getAllContainedDisplayableElementSet(elements).iterator();
                  while(it.hasNext()) {
                      Object o = it.next();
    
                      sElementsToUse.add(o);
                      if (o instanceof LUWTable) {
                        if (o instanceof LUWNickname) {
                            additionalOptions.add(EngineeringOptionID.GENERATE_NICKNAME);
                            //TODO: make sure to remove the columns, or leave LUWColumn line commented out
                        } else {
                            additionalOptions.add(EngineeringOptionID.GENERATE_TABLES);
                        }
                      } else if (o instanceof LUWServer) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_REMOTESERVER);
                      } else if (o instanceof LUWWrapper) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_WRAPPER);
                      } else if (o instanceof LUWUserMapping) { 
                        additionalOptions.add(EngineeringOptionID.GENERATE_USERMAPPING);
                      } else if (o instanceof DB2OLAPObject) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_OLAP);
                      } else if (o instanceof LUWTableSpace) {
                          additionalOptions.add(EngineeringOptionID.GENERATE_TABLESPACES);
                      } else if (o instanceof LUWDatabasePackage) {
                          additionalOptions.add(EngineeringOptionID.GENERATE_PACKAGE);
                      } else if (o instanceof DB2Index) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_INDICES);
                      } else if (o instanceof DB2Procedure) {
                    	  if ( o instanceof FederatedProcedure)
                    		  additionalOptions.add(EngineeringOptionID.GENERATE_FEDERATEDPROCEDURES);
                    	  else
                    		  additionalOptions.add(EngineeringOptionID.GENERATE_STOREDPROCEDURES);
                      } else if (o instanceof DB2UserDefinedFunction) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_FUNCTIONS);
                      } else if (o instanceof LUWView) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_VIEWS);
                      } else if (o instanceof DB2Trigger) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_TRIGGERS);
                      } else if (o instanceof Sequence) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_SEQUENCES);
                      } else if (o instanceof UserDefinedType) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_USER_DEFINED_TYPE);
    //                } else if(o instanceof DistinctUserDefinedType)
    //                } else if (o instanceof StructuredUserDefinedType)
                      } else if (o instanceof UniqueConstraint) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_PK_CONSTRAINTS);
    //                  } else if(o instanceof LUWSynonym)
    //                    additionalOptions.add(new EngineeringOption(EngineeringOptionID.GENERATE_SYNONYM);
                      } else if(o instanceof LUWDatabase) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_DATABASE);
                      } else if(o instanceof LUWMaterializedQueryTable) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_MQT);
                      } else if(o instanceof DB2Alias) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_ALIAS);
                      } else if(o instanceof CheckConstraint) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_CK_CONSTRAINTS);
                      } else if(o instanceof ForeignKey) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_FK_CONSTRAINTS);
                      } else if(o instanceof LUWBufferPool) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_BUFFERPOOL);
                      } else if(o instanceof LUWPartitionGroup) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_PARTITIONGROUP);
                      } else if(o instanceof Schema) {
                        additionalOptions.add(EngineeringOptionID.GENERATE_SCHEMAS);
                      } else if(o instanceof Column) {
                    	  if (!additionalOptions.contains(EngineeringOptionID.GENERATE_TABLES)) {
                    		  additionalOptions.add(EngineeringOptionID.GENERATE_TABLES);
                    	  }
                      } else if(o instanceof Role) {
                          additionalOptions.add(EngineeringOptionID.GENERATE_ROLE);
                      } else if(o instanceof RoleAuthorization) {
                    	  additionalOptions.add(EngineeringOptionID.GENERATE_PRIVILEGE);
                      } else if(o instanceof Privilege) {
                    	  additionalOptions.add(EngineeringOptionID.GENERATE_PRIVILEGE);
                      }
                  }
                  for (it = additionalOptions.iterator(); it.hasNext(); )
                    setOption((String)it.next());
              } else { //autoDiscovery == FALSE
                sOptions.addAll(LUWDdlGenerator.getAllContainedDisplayableElementSetDepedency(elements));
              }
              
              if (hasStorage) {
            	  setOption(EngineeringOptionID.GENERATE_IN_TABLESPACE_CLAUSE);
              }
              setOption(EngineeringOptionID.GENERATE_USE_DOMAIN_IF_EXIST);

        }
        /**
         * @return Returns the option.
         */
        public Set getOptions() {
            return sOptions;
        }
        /**
         * @param option The option to set.
         */
        public void setOption(String option) {
            this.sOptions.add(option);
        }

		/**
		 * @return Returns the sElementsToUse.
		 */
		public Set getElementsToUse() {
			return sElementsToUse;
		}
    }

    public static class Data {
        private Class selectedObject = null;
        private Set parents = new HashSet();
        private Set childs = new HashSet();
        private long mask = 0;
        private ArrayList sOptions = new ArrayList();
    }
    
    public static class SingletonOptionDependency {

        private Map data = new HashMap();
        private static SingletonOptionDependency ref;
        
        private SingletonOptionDependency()
        {
        }

		public static SingletonOptionDependency getSingletonObject()
        {
        	if (ref == null) {
            	boolean hasStorage = (LUWCatalogDatabase.getCatalogStorageProvider() != null)? true : false; 
          	    boolean hasModule = LUWDdlGenerator.getModuleDdlBuilder() != null ? true :false;

        		// it's ok, we can call this constructor
        		ref = new SingletonOptionDependency();

                //Database
                Data d = new Data();
                d.selectedObject = LUWDatabase.class;
                d.parents = null;
                d.childs.add(Schema.class);
                d.childs.add(LUWTableSpace.class);
                d.childs.add(LUWBufferPool.class);
                d.childs.add(LUWPartitionGroup.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_DATABASE);
                d.mask = EngineeringOptionID.DATABASE | EngineeringOptionID.TABLE | EngineeringOptionID.NICKNAME | EngineeringOptionID.SERVER | EngineeringOptionID.WRAPPER | EngineeringOptionID.USER_MAPPING | EngineeringOptionID.OLAP_OBJECT | EngineeringOptionID.TABLESPACE | 
                         EngineeringOptionID.INDEX | EngineeringOptionID.PROCEDURE | EngineeringOptionID.USER_DEFINED_FUNCTION | EngineeringOptionID.VIEW | EngineeringOptionID.TRIGGER | EngineeringOptionID.SEQUENCE | 
                         EngineeringOptionID.UNIQUE_CONSTRAINT | EngineeringOptionID.MATERIALIZED_QUERY_TABLE | EngineeringOptionID.ALIAS | EngineeringOptionID.CHECK_CONSTRAINT | EngineeringOptionID.FOREIGN_KEY | 
                         EngineeringOptionID.SCHEMA | EngineeringOptionID.DISTINCT_USER_DEFINED_TYPE | 
                         EngineeringOptionID.STRUCTURED_USER_DEFINED_TYPE | EngineeringOptionID.ROLE | EngineeringOptionID.PRIVILEGE;
    			if (hasStorage) {
    				d.mask = d.mask | EngineeringOptionID.TABLESPACE | EngineeringOptionID.BUFFER_POOL | EngineeringOptionID.PARTITION_GROUP; 
    			}
    			if (hasModule){
    				d.mask = d.mask | EngineeringOptionID.MODULE | EngineeringOptionID.MODULE_CONDITION
    						| EngineeringOptionID.PACKAGE
    						| EngineeringOptionID.PACKAGE_BODY;
    			}

    			ref.data.put(Database.class,d);

                //Schema
                d = new Data();
                d.selectedObject = Schema.class;
                d.parents.add(Database.class);
                d.childs.add(LUWTable.class);
                d.childs.add(DB2Alias.class);
                d.childs.add(LUWView.class);
                d.childs.add(LUWWrapper.class);
                d.childs.add(LUWMaterializedQueryTable.class);
                d.childs.add(DB2UserDefinedFunction.class);
                d.childs.add(DB2Procedure.class);
                d.childs.add(UserDefinedType.class);
                d.childs.add(Sequence.class);
                d.childs.add(LUWModule.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_SCHEMAS);
                d.mask = EngineeringOptionID.TABLE | EngineeringOptionID.NICKNAME | EngineeringOptionID.SERVER | EngineeringOptionID.WRAPPER | EngineeringOptionID.USER_MAPPING | EngineeringOptionID.OLAP_OBJECT |
                         EngineeringOptionID.INDEX | EngineeringOptionID.PROCEDURE | EngineeringOptionID.USER_DEFINED_FUNCTION | EngineeringOptionID.VIEW | EngineeringOptionID.TRIGGER | EngineeringOptionID.SEQUENCE |
                         EngineeringOptionID.UNIQUE_CONSTRAINT | EngineeringOptionID.MATERIALIZED_QUERY_TABLE | EngineeringOptionID.ALIAS | EngineeringOptionID.CHECK_CONSTRAINT | EngineeringOptionID.FOREIGN_KEY | 
                         EngineeringOptionID.SCHEMA | EngineeringOptionID.DISTINCT_USER_DEFINED_TYPE | EngineeringOptionID.STRUCTURED_USER_DEFINED_TYPE | EngineeringOptionID.PRIVILEGE;
    			if (hasStorage) {
    				d.mask = d.mask | EngineeringOptionID.TABLESPACE;
    			}
    			if (hasModule){
    				d.mask = d.mask | EngineeringOptionID.MODULE | EngineeringOptionID.MODULE_CONDITION
    						| EngineeringOptionID.PACKAGE
    						| EngineeringOptionID.PACKAGE_BODY;
    			}
                ref.data.put(Schema.class,d);
               
                //Table
                d = new Data();
                d.selectedObject = LUWTable.class;
                d.parents.add(Schema.class);
                d.childs.add(DB2Index.class);
                d.childs.add(DB2Trigger.class);
                d.childs.add(LUWTableSpace.class);
                d.childs.add(UniqueConstraint.class);
                d.childs.add(ForeignKey.class);
                d.childs.add(CheckConstraint.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_TABLES);
                d.mask = EngineeringOptionID.TABLE | EngineeringOptionID.OLAP_OBJECT |  EngineeringOptionID.INDEX | EngineeringOptionID.TRIGGER | EngineeringOptionID.UNIQUE_CONSTRAINT | 
                         EngineeringOptionID.CHECK_CONSTRAINT | EngineeringOptionID.FOREIGN_KEY | EngineeringOptionID.PRIVILEGE;
    			if (hasStorage) {
    				d.mask = d.mask | EngineeringOptionID.TABLESPACE;
    			}
                ref.data.put(Table.class,d);
                
                //LUWStorageTable
                d = new Data();
                d.selectedObject = LUWStorageTable.class;
                d.parents.add(Schema.class);
                d.childs.add(LUWTableSpace.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_TABLES);
                d.mask = EngineeringOptionID.TABLE | EngineeringOptionID.OLAP_OBJECT | EngineeringOptionID.TABLESPACE | EngineeringOptionID.INDEX | EngineeringOptionID.TRIGGER | EngineeringOptionID.UNIQUE_CONSTRAINT | 
                         EngineeringOptionID.CHECK_CONSTRAINT | EngineeringOptionID.FOREIGN_KEY | EngineeringOptionID.PRIVILEGE;
                ref.data.put(LUWStorageTable.class,d);
                
                //LUWWrapper
                d = new Data();
                d.selectedObject = LUWWrapper.class;
                d.parents.add(Schema.class);
                d.childs.add(LUWServer.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_WRAPPER);
                d.mask = EngineeringOptionID.NICKNAME | EngineeringOptionID.SERVER | EngineeringOptionID.WRAPPER | 
				         EngineeringOptionID.USER_MAPPING | EngineeringOptionID.INDEX | 
                         EngineeringOptionID.UNIQUE_CONSTRAINT | EngineeringOptionID.CHECK_CONSTRAINT | 
                         EngineeringOptionID.FOREIGN_KEY;
                ref.data.put(LUWWrapper.class,d);

                //LUWServer
                d = new Data();
                d.selectedObject = LUWServer.class;
                d.parents.add(LUWWrapper.class);
                d.childs.add(LUWUserMapping.class);
                d.childs.add(LUWNickname.class);
                d.childs.add(LUWWrapper.class); //add dependency (watch out recurssion)                
                d.sOptions.add(EngineeringOptionID.GENERATE_REMOTESERVER);
                d.mask = EngineeringOptionID.NICKNAME | EngineeringOptionID.SERVER | EngineeringOptionID.WRAPPER | 
                         EngineeringOptionID.USER_MAPPING | EngineeringOptionID.INDEX | 
                         EngineeringOptionID.UNIQUE_CONSTRAINT | EngineeringOptionID.CHECK_CONSTRAINT | 
                         EngineeringOptionID.FOREIGN_KEY;
                ref.data.put(LUWServer.class,d);
                
                //LUWUserMapping
                d = new Data();
                d.selectedObject = LUWUserMapping.class;
                d.parents.add(LUWServer.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_USERMAPPING);
                d.mask = EngineeringOptionID.SERVER | EngineeringOptionID.WRAPPER | EngineeringOptionID.USER_MAPPING;
                ref.data.put(LUWUserMapping.class,d);

                //LUWNickname
                d = new Data();
                d.selectedObject = LUWNickname.class;
                d.parents.add(LUWServer.class);
                d.childs.add(UniqueConstraint.class);
                d.childs.add(ForeignKey.class);
                d.childs.add(CheckConstraint.class);
                d.childs.add(LUWServer.class); //add dependency (watch out recurssion)
                d.sOptions.add(EngineeringOptionID.GENERATE_NICKNAME);
                d.mask = EngineeringOptionID.NICKNAME | EngineeringOptionID.SERVER | EngineeringOptionID.WRAPPER | 
                         EngineeringOptionID.USER_MAPPING | EngineeringOptionID.INDEX | 
                         EngineeringOptionID.UNIQUE_CONSTRAINT | EngineeringOptionID.CHECK_CONSTRAINT | 
                         EngineeringOptionID.FOREIGN_KEY | EngineeringOptionID.PRIVILEGE;
                ref.data.put(LUWNickname.class,d);

                //DB2OLAPObject
                d = new Data();
                d.selectedObject = DB2OLAPObject.class;
                d.parents.add(Schema.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_OLAP);
                d.mask = EngineeringOptionID.OLAP_OBJECT;
                ref.data.put(DB2OLAPObject.class,d);

                //LUWTableSpace
                d = new Data();
                d.selectedObject = LUWTableSpace.class;
                d.parents.add(Schema.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_TABLESPACES);
                d.mask = EngineeringOptionID.TABLESPACE | EngineeringOptionID.PRIVILEGE;
                ref.data.put(LUWTableSpace.class,d);

                //Index
                d = new Data();
                d.selectedObject = DB2Index.class;
                d.parents.add(Schema.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_INDICES);
                d.mask = EngineeringOptionID.INDEX | EngineeringOptionID.PRIVILEGE;
                ref.data.put(Index.class,d);

                //Procedure
                d = new Data();
                d.selectedObject = DB2Procedure.class;
                d.parents.add(Schema.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_STOREDPROCEDURES);
                d.mask = EngineeringOptionID.PROCEDURE | EngineeringOptionID.PRIVILEGE;
                ref.data.put(Procedure.class,d);

                //Federated Procedure - pyl
                d = new Data();
                d.selectedObject = FederatedProcedure.class;
                d.parents.add(Schema.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_FEDERATEDPROCEDURES);
                d.mask = EngineeringOptionID.FEDERATEDPROCEDURE | EngineeringOptionID.SERVER | EngineeringOptionID.WRAPPER |
                         EngineeringOptionID.USER_MAPPING;
                ref.data.put(FederatedProcedure.class,d);  // pyl @wsdbu00073899
                
                //DB2UserDefinedFunction
                d = new Data();
                d.selectedObject = DB2UserDefinedFunction.class;
                d.parents.add(Schema.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_FUNCTIONS);
                d.mask = EngineeringOptionID.USER_DEFINED_FUNCTION | EngineeringOptionID.PRIVILEGE;
                ref.data.put(UserDefinedFunction.class,d);
 
                //ViewTable
                d = new Data();
                d.selectedObject = LUWView.class;
                d.parents.add(Schema.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_VIEWS);
                d.mask = EngineeringOptionID.VIEW | EngineeringOptionID.TRIGGER | EngineeringOptionID.PRIVILEGE;
                ref.data.put(ViewTable.class,d);

                //Trigger
                d = new Data();
                d.selectedObject = DB2Trigger.class;
                d.parents.add(Schema.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_TRIGGERS);
                d.mask = EngineeringOptionID.TRIGGER;
                ref.data.put(Trigger.class,d);

                //Sequence
                d = new Data();
                d.selectedObject = Sequence.class;
                d.parents.add(Schema.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_SEQUENCES);
                d.mask = EngineeringOptionID.SEQUENCE | EngineeringOptionID.PRIVILEGE;
                ref.data.put(Sequence.class,d);

                //UserDefinedType
                d = new Data();
                d.selectedObject = UserDefinedType.class;
                d.parents.add(Schema.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_USER_DEFINED_TYPE);
                d.mask = EngineeringOptionID.USER_DEFINED_TYPE;
                ref.data.put(UserDefinedType.class,d);

                //UniqueConstraint
                d = new Data();
                d.selectedObject = UniqueConstraint.class;
                d.parents.add(Schema.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_PK_CONSTRAINTS);
                d.mask = EngineeringOptionID.UNIQUE_CONSTRAINT;
                ref.data.put(UniqueConstraint.class,d);

                //ForeignKey
                d = new Data();
                d.selectedObject = ForeignKey.class;
                d.parents.add(Schema.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_FK_CONSTRAINTS);
                d.mask = EngineeringOptionID.FOREIGN_KEY;
                ref.data.put(ForeignKey.class,d);

                //CheckConstraint
                d = new Data();
                d.selectedObject = CheckConstraint.class;
                d.parents.add(Schema.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_CK_CONSTRAINTS);
                d.mask = EngineeringOptionID.CHECK_CONSTRAINT;
                ref.data.put(CheckConstraint.class,d);

                //LUWMaterializedQueryTable
                d = new Data();
                d.selectedObject = LUWMaterializedQueryTable.class;
                d.parents.add(Schema.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_MQT);
                d.mask = EngineeringOptionID.INDEX | EngineeringOptionID.MATERIALIZED_QUERY_TABLE | EngineeringOptionID.PRIVILEGE;
                ref.data.put(LUWMaterializedQueryTable.class,d);

                //DB2Alias
                d = new Data();
                d.selectedObject = DB2Alias.class;
                d.parents.add(Schema.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_ALIAS);
                d.mask = EngineeringOptionID.ALIAS;
                ref.data.put(DB2Alias.class,d);

                //LUWBufferPool
                d = new Data();
                d.selectedObject = LUWBufferPool.class;
                d.parents.add(Database.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_BUFFERPOOL);
                d.mask = EngineeringOptionID.BUFFER_POOL;
                ref.data.put(LUWBufferPool.class,d);

                //LUWPartitionGroup
                d = new Data();
                d.selectedObject = LUWPartitionGroup.class;
                d.parents.add(Database.class);
                //d.child.add(DB2Index.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_PARTITIONGROUP);
                d.mask = EngineeringOptionID.PARTITION_GROUP;
                ref.data.put(LUWPartitionGroup.class,d);
                
                //Column
                d = new Data();
                d.selectedObject = Column.class;
                d.parents.add(LUWTable.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_TABLES);
                d.mask = EngineeringOptionID.TABLE;
                ref.data.put(Column.class,d);

                //Role
                d = new Data();
                d.selectedObject = Role.class;
                d.parents.add(Database.class);
                d.childs.add(Privilege.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_ROLE);
                d.mask = EngineeringOptionID.ROLE | EngineeringOptionID.PRIVILEGE;
                ref.data.put(Role.class,d);

                //User
                d = new Data();
                d.selectedObject = User.class;
                d.parents.add(Database.class);
                d.childs.add(Privilege.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_PRIVILEGE);
                d.mask = EngineeringOptionID.PRIVILEGE;
                ref.data.put(User.class,d);

                //Group
                d = new Data();
                d.selectedObject = Group.class;
                d.parents.add(Database.class);
                d.childs.add(Privilege.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_PRIVILEGE);
                d.mask = EngineeringOptionID.PRIVILEGE;
                ref.data.put(Group.class,d);

                //RoleAuthorization
                d = new Data();
                d.selectedObject = RoleAuthorization.class;
                d.parents.add(AuthorizationIdentifier.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_PRIVILEGE);
                d.mask = EngineeringOptionID.PRIVILEGE;
                ref.data.put(RoleAuthorization.class,d);

                //Privilege
                d = new Data();
                d.selectedObject = Privilege.class;
                d.parents.add(AuthorizationIdentifier.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_PRIVILEGE);
                d.mask = EngineeringOptionID.PRIVILEGE;
                ref.data.put(Privilege.class,d);

                //Package
                d = new Data();
                d.selectedObject = LUWDatabasePackage.class;
                d.parents.add(Schema.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_PACKAGE);
                d.mask = EngineeringOptionID.PACKAGE | EngineeringOptionID.PRIVILEGE;
                ref.data.put(LUWDatabasePackage.class,d);

    			if (hasModule){
                    //Module
                    d = new Data();
                    d.selectedObject = LUWModule.class;
                    d.parents.add(Schema.class);
                    d.sOptions.add(EngineeringOptionID.GENERATE_MODULE);
                    d.mask = EngineeringOptionID.MODULE | EngineeringOptionID.MODULE_CONDITION 
                    		| EngineeringOptionID.GLOBAL_VARIABLE
                    		| EngineeringOptionID.USER_DEFINED_TYPE
                    		| EngineeringOptionID.PROCEDURE | EngineeringOptionID.USER_DEFINED_FUNCTION
                    		| EngineeringOptionID.PRIVILEGE;
                    ref.data.put(LUWModule.class,d);

                    //Module condition
                    d = new Data();
                    d.selectedObject = LUWModuleCondition.class;
                    d.parents.add(LUWModule.class);
                    d.sOptions.add(EngineeringOptionID.GENERATE_MODULE_CONDITION);
                    d.mask =  EngineeringOptionID.MODULE_CONDITION;
                    ref.data.put(LUWModuleCondition.class,d);

                    //PL/SQL Package
                    d = new Data();
                    d.selectedObject = PLSQLPackage.class;
                    d.parents.add(Schema.class);
                    d.sOptions.add(EngineeringOptionID.GENERATE_PACKAGE);
                    d.mask = EngineeringOptionID.PACKAGE | EngineeringOptionID.PACKAGE_BODY 
                    		| EngineeringOptionID.PRIVILEGE;
                    ref.data.put(PLSQLPackage.class,d);

                    //PL/SQL PackageBody
                    d = new Data();
                    d.selectedObject = PLSQLPackageBody.class;
                    d.parents.add(Schema.class);
                    d.sOptions.add(EngineeringOptionID.GENERATE_PACKAGE_BODY);
                    d.mask =  EngineeringOptionID.PACKAGE_BODY;
                    ref.data.put(PLSQLPackageBody.class,d);
    			}

                //Global Variable
                d = new Data();
                d.selectedObject = LUWGlobalVariable.class;
                d.parents.add(Schema.class);
                d.sOptions.add(EngineeringOptionID.GENERATE_GLOBAL_VARIABLE);
                d.mask =  EngineeringOptionID.GLOBAL_VARIABLE | EngineeringOptionID.PRIVILEGE;
                ref.data.put(LUWGlobalVariable.class,d);

        	}
          return ref;
        }        
       
        /**
         * @return Returns the data.
         */
        public Data getData(Class key) {
            return (Data)data.get(key);
        }
    }
    //@ed00058820gs    
    
	public static ILUWModuleDdlBuilder getModuleDdlBuilder() {
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.db2.luw", "moduleDdlBuilder"); //$NON-NLS-1$ //$NON-NLS-2$
		if (extensionPoint != null) {
		    IExtension[] extensions = extensionPoint.getExtensions();
		    if(extensions.length == 1) {
		        IConfigurationElement[] configElements = extensions[0].getConfigurationElements();
		        try {
		            moduleBuilder = (ILUWModuleDdlBuilder) configElements[0].createExecutableExtension("class"); //$NON-NLS-1$
		        }
		        catch(Exception e) {
		        }
		    }
		}
		return moduleBuilder;
	}

	//@Override
	public DdlBuilder getDdlBuilder() {
		return this.builder;
	}

//bgp	@Override
	public EngineeringOption[] getDefaultOptions(SQLObject[] elements) {
		 isNickname = false; //@d00062627gs
	        EngineeringOptionCategory[] categories_new = this.getOptionCategories();
	        
	        EngineeringOptionCategory general_options =null;
	        EngineeringOptionCategory additional_element =null;
	        for (int i = 0; i < categories_new.length; i++) {
	          if (categories_new[i].getId().equals(EngineeringOptionCategoryID.GENERATE_OPTIONS)){
	              general_options = categories_new[i];
	          } else if (categories_new[i].getId().equals(EngineeringOptionCategoryID.GENERATE_ELEMENTS)){
	              additional_element = categories_new[i];
	          }
	        }           

	        //Get the main object, the ones that do no have parents.
	        //Look for dependency
	        optionDependency = new OptionDependency(elements, false);
	        Set sOptions = optionDependency.getOptions();
	        int size = 0;
	        EngineeringOption currentOption = null;
	        Vector defaultOptionVec = new Vector();
	        for (Iterator it=sOptions.iterator(); it.hasNext(); ) {
	        	EngineeringOption ddlOption = DdlGenerationUtility.getDefaultEngineeringOption((String)it.next(), product, version, general_options, additional_element);
	            if (ddlOption != null) {
	            	defaultOptionVec.add(ddlOption);
	            	if (ddlOption.getCategory().getId().equals(EngineeringOptionCategoryID.GENERATE_ELEMENTS)) {
	            		size++;
	            		currentOption = ddlOption;
		            	//Disable indices, primary key, check and foreign key constraints by default if Nickname is selected
		                if (isNickname) {
		                	String id = ddlOption.getId();
		                	if (id != null && (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_INDICES) ||
		                		id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PK_CONSTRAINTS) ||
		                		id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CK_CONSTRAINTS) ||
		                		id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FK_CONSTRAINTS))
							   )
		                		ddlOption.setBoolean(false);
		                }
	            	}
	            }
	        }
	        if (size == 1) currentOption.setBoolean(true);
	        this.defaultOptions = new EngineeringOption[defaultOptionVec.size()];
	        defaultOptionVec.copyInto(this.defaultOptions);
	        return this.defaultOptions;
	}
}
