/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.catalog;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogStatistics;
import org.eclipse.datatools.enablement.ibm.db2.catalog.JavaProcedureInfo;
import org.eclipse.datatools.enablement.ibm.db2.catalog.util.JavaProcedureProviderRegistry;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.DatabaseREProvider;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWCatalogMessages;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelFactory;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Source;
import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl;
import org.eclipse.datatools.enablement.ibm.util.ModelHelper;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.DataAccess;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogProcedure extends DB2ProcedureImpl implements ICatalogObject,IDatabaseObject  {
	public void refresh() {
		this.loaded = false;
		if (this.parameterLoaded){
			this.parameters.clear();
			this.parameterLoaded = false;
		}
		
		if (this.dependencyLoaded){
			this.dependencies.clear();
			this.dependencyLoaded = false;
		}
		if (this.sourceLoaded) {
			this.source = null;
			this.extendedOptions.clear();
			this.sourceLoaded = false;
		}
		
		this.javaProcedureLoaded = false;
		this.privilegeLoaded = false;
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((LUWCatalogDatabase) database).getConnection();
	}
	
	public Database getCatalogDatabase() {
		return this.getSchema().getDatabase();		
	}

	public void refresh(int refreshType){
		if ((IDatabaseObject.IMPACTS & refreshType)  == IDatabaseObject.IMPACTS) {
			this.impacts.clear();
			this.impactsLoaded = false;
		}
		if ((IDatabaseObject.STATISTICS & refreshType)  == IDatabaseObject.STATISTICS) {
			this.statistics.clear();
			this.statisticsLoaded = false;
		}
	}

	public String getLanguage() {
		//if(!this.loaded) this.load();
		return super.getLanguage();
	}
	
	public boolean isDeterministic() {
		//if(!this.loaded) this.load();
		return super.isDeterministic();
	}
	
	public String getParameterStyle() {
		//if(!this.loaded) this.load();
		return super.getParameterStyle();
	}
	
	public String getFenced() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getFenced();
		} else {
			if(!this.loaded) this.load();
			return super.getFenced();
		}
	}
	
	public boolean isFederated() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.isFederated();
		} else {
			if(!this.loaded) this.load();
			return super.isFederated();
		}
	}
	
	public int getMaxResultSets() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getMaxResultSets();
		} else {
			if(!this.loaded) this.load();
			return super.getMaxResultSets();
		}
	}
	
	public boolean isImplicitSchema() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.isImplicitSchema();
		} else {
			if ( !this.loaded )  this.load();
			return super.implicitSchema;
		}
	}


	public Source getSource() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getSource();
		} else {
		   if(!this.loaded) this.load();
		   loadSource();
			return super.getSource();
		}
	}

	public DB2JavaOptions getJavaOptions() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getJavaOptions();
		} else {
	        loadJavaProcedure();        
			return super.getJavaOptions();
		}
	}
	
    /**
     * Get db2 package name from the Java procedure info provider.
     * @return db2 package name if the language is JAVA.
     */
    private String getJavaSPDb2Package() {
        String javaSPDb2Package = "";
        if ("JAVA".equals(getLanguage())) { //$NON-NLS-1$
            JavaProcedureInfo javaProcedureInfo = getJavaProcedureInfo();
            if (javaProcedureInfo != null) {
                javaSPDb2Package = javaProcedureInfo.getDB2Package();
            }
            else {
                javaSPDb2Package = "";
            }               
        }
        return javaSPDb2Package;    
    }

    /**
     * This method is not in the super. It is provided
     * here as an alternative to finding the db2Package
     * name from the source list through getSource()
     * which would trigger a download of the source from the catalog.
     * @return The DB2 package name if the language is JAVA.
     */
    public String getDb2Package() {
        if (!db2PackageLoaded) {
            if ("JAVA".equals(getLanguage())) { //$NON-NLS-1$
                JavaProcedureInfo javaProcedureInfo = getJavaProcedureInfo();
                if (javaProcedureInfo != null) {
                    db2Package = javaProcedureInfo.getDB2Package();    
                }
                else {
                    db2Package = "";   
                }
            }
            else {
                load();   
            }
            db2PackageLoaded = true;
        }
        return db2Package;
    }
    
	public EList getDependencies() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getDependencies();
		} else {
			if(!this.dependencyLoaded) this.loadDependencies();
			return this.dependencies;
		}
	}

	public EList getParameters() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getParameters();
		} else {
			if(!this.parameterLoaded) this.loadParameters();
			return this.parameters;
		}
	}
	
	public DataAccess getSqlDataAccess() {
		//if(!this.loaded) this.load();
		return this.sqlDataAccess;
	}
	 
	public String getThreadsafe() {
		if(!this.loaded) this.load();
		return this.threadsafe;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.wst.rdb.internal.models.sql.routines.Routine#getExternalName()
	 */
	public String getExternalName() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getExternalName();
		} else {
			if("JAVA".equals(this.getLanguage()) && !this.externalNameLoaded) this.loadExternalName(); //$NON-NLS-1$
			return super.getExternalName();
		}
	}
    
	public EList getPrivileges() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getPrivileges();
		} else {
			if (!this.privilegeLoaded) this.loadPrivileges();
			return this.privileges;
		}
	}

	public EList getExtendedOptions() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getExtendedOptions();
		} else {
		   if(!this.loaded) this.load();
		   loadSource();
			return super.extendedOptions;
		}
	}

	public ICatalogObject[] getImpacted(){
		if (!this.impactsLoaded) {
			this.impacts = this.getImpactedObjects();
			this.impactsLoaded = true;
		}
		ICatalogObject[] objs = new ICatalogObject[impacts.size()];
		impacts.toArray(objs);
		return objs;
	}

	public Collection getStatistics(){
		if (!this.statisticsLoaded) {
			this.statistics = LUWCatalogProcedure.getStatistics(this.getConnection(), this);
			this.statisticsLoaded = true;
		} 
		return this.statistics;
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == DB2ModelPackage.DB2_PROCEDURE__LANGUAGE) {
			this.getLanguage();
		}
		else if(id == DB2ModelPackage.DB2_PROCEDURE__FEDERATED) {
			this.isFederated();
		}
		else if(id == DB2ModelPackage.DB2_PROCEDURE__FENCED) {
			this.getFenced();
		}
		else if(id == DB2ModelPackage.DB2_PROCEDURE__MAX_RESULT_SETS) {
			this.getMaxResultSets();
		}
		else if(id == DB2ModelPackage.DB2_PROCEDURE__SOURCE) {
			this.getSource();
		}
		else if(id == DB2ModelPackage.DB2_PROCEDURE__JAVA_OPTIONS) {
			this.getJavaOptions();
		}
		else if(id == DB2ModelPackage.DB2_PROCEDURE__PARAMETER_STYLE) {
			this.getParameterStyle();
		}
		else if(id == DB2ModelPackage.DB2_PROCEDURE__DETERMINISTIC) {
			this.isDeterministic();
		}
		else if(id == DB2ModelPackage.DB2_PROCEDURE__DEPENDENCIES) {
			this.getDependencies();
		}
		else if(id == DB2ModelPackage.DB2_PROCEDURE__PARAMETERS) {
			this.getParameters();
		}
		else if(id == DB2ModelPackage.DB2_PROCEDURE__SQL_DATA_ACCESS) {
			this.getSqlDataAccess();
		}
		else if(id == DB2ModelPackage.DB2_PROCEDURE__THREADSAFE) {
			this.getThreadsafe();
		}
		else if(id == DB2ModelPackage.DB2_PROCEDURE__PRIVILEGES) {
			this.getPrivileges();
		}
		else if(id == DB2ModelPackage.DB2_PROCEDURE__EXTENDED_OPTIONS) {
			this.getExtendedOptions();
		}
		
		return super.eIsSet(eFeature);
	}
	
	private synchronized void load() {
		if(this.loaded) return;
		this.loaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		Connection connection = this.getConnection();
        final DatabaseDefinition definition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase());
        String version = definition.getVersion();
        float ver = 8.0f;
        try {
            ver = Float.parseFloat(version.substring(1));
        }
        catch (NumberFormatException e) {            
        }

		final DataModelElementFactory factory = definition.getDataModelElementFactory();
		
        //this query is for LUW V8. it is taken from DBAPIUNOImpl and a few are added
        //the outer join is for obtaining the DB2 package name for SQL SP.
        final String describe_sp_query_v8 = "WITH PP(PROCSCHEMA, PROCNAME, SPECIFICNAME, DEFINER, " + //$NON-NLS-1$
           "PARM_COUNT, DETERMINISTIC, FENCED, LANGUAGE, IMPLEMENTATION, " + //$NON-NLS-1$
           "CLASS, JAR_ID, JAR_SCHEMA, PARM_STYLE, CONTAINS_SQL, " + //$NON-NLS-1$
           "PROGRAM_TYPE, RESULT_SETS, THREADSAFE, FEDERATED, REMARKS) AS " + //$NON-NLS-1$
           "(SELECT ROUTINESCHEMA, ROUTINENAME, SPECIFICNAME, DEFINER, " + //$NON-NLS-1$
           "PARM_COUNT, DETERMINISTIC, FENCED, LANGUAGE, IMPLEMENTATION, " + //$NON-NLS-1$
           "CLASS, JAR_ID, JARSCHEMA, PARAMETER_STYLE, SQL_DATA_ACCESS, " + //$NON-NLS-1$
           "PROGRAMTYPE, RESULT_SETS, THREADSAFE, FEDERATED, REMARKS FROM SYSCAT.ROUTINES WHERE " + //$NON-NLS-1$
           "ROUTINETYPE = 'P') " + //$NON-NLS-1$
           "SELECT PROCSCHEMA, PROCNAME, SPECIFICNAME, DEFINER, " + //$NON-NLS-1$
           "PARM_COUNT, DETERMINISTIC, FENCED, LANGUAGE, IMPLEMENTATION, " + //$NON-NLS-1$
           "CLASS, JAR_ID, JAR_SCHEMA, PARM_STYLE, CONTAINS_SQL, " + //$NON-NLS-1$
           "PROGRAM_TYPE, RESULT_SETS, THREADSAFE, FEDERATED, REMARKS, BNAME AS PACKAGENAME " + //$NON-NLS-1$
           "FROM PP LEFT OUTER JOIN SYSIBM.SYSDEPENDENCIES PD ON " + //$NON-NLS-1$
           "(PD.DSCHEMA = PP.PROCSCHEMA AND PD.DNAME = PP.SPECIFICNAME " + //$NON-NLS-1$
           "AND PD.BTYPE = 'K') "; //$NON-NLS-1$
        
		try {
			Statement s = connection.createStatement();
            //LUW V7
			//ResultSet r = s.executeQuery("SELECT LANGUAGE, DETERMINISTIC, SQL_DATA_ACCESS, PARAMETER_STYLE, FENCED, THREADSAFE, FEDERATED, RESULT_SETS, TEXT"
            //                  + " FROM SYSCAT.ROUTINES"
            //                  + " WHERE ROUTINESCHEMA='" + this.getSchema().getName() + "' AND ROUTINENAME='" + this.getName() + "'"
            //                  + " AND SPECIFICNAME='" + this.getSpecificName() + "'");
            
            ResultSet r = s.executeQuery(describe_sp_query_v8
					+ " WHERE PROCSCHEMA='" + LUWUtil.getIdentifier(this.getSchema().getName()) + "' AND PROCNAME='" + LUWUtil.getIdentifier(this.getName()) + "'" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					+ " AND SPECIFICNAME='" + LUWUtil.getIdentifier(this.getSpecificName()) + "'"); //$NON-NLS-1$ //$NON-NLS-2$
			while(r.next()) {
				
				final String deterministic = r.getString("DETERMINISTIC"); //$NON-NLS-1$
				if(deterministic.equals("Y")) this.setDeterministic(true); //$NON-NLS-1$
				else this.setDeterministic(false);
				final String sql_data_access = r.getString("CONTAINS_SQL"); //$NON-NLS-1$
                // 'C' - CONTAINS SQL, 'M' - MODIFIES SQL DATA, 'N' - No SQL, 'R' - READS SQL DATA
                if (sql_data_access.equals("C")) this.sqlDataAccess = DataAccess.CONTAINS_SQL_LITERAL; //$NON-NLS-1$
                else if (sql_data_access.equals("M")) this.sqlDataAccess = DataAccess.MODIFIES_SQL_DATA_LITERAL; //$NON-NLS-1$
                else if (sql_data_access.equals("N")) this.sqlDataAccess = DataAccess.NO_SQL_LITERAL; //$NON-NLS-1$
                else this.sqlDataAccess = DataAccess.READS_SQL_DATA_LITERAL;

				final String paramStyle =  r.getString("PARM_STYLE"); //$NON-NLS-1$
				if (paramStyle.equals("DB2SQL")) {
					this.setParameterStyle("DB2SQL");
				} else if (paramStyle.equals("DB2GENRL")) {
					this.setParameterStyle("DB2GENRAL");
				} else if (paramStyle.equals("GENERAL")) {
					this.setParameterStyle("GENERAL");
				} else if (paramStyle.equals("GNRLNULL")) {
					this.setParameterStyle("GENERAL WITH NULLS");
				} else if (paramStyle.equals("JAVA")) {
					this.setParameterStyle("JAVA");
				} else  {
					this.setParameterStyle("SQL");
				}
				this.parameterStyle = paramStyle.trim();
				
// fenced in metamodel should be boolean - wait for the fix				
				final String fenced = r.getString("FENCED"); //$NON-NLS-1$
				//if(fenced.equals("Y")) this.fenced = true;
                //TODO: hard-coded the constants for now. 
                //if the method is not changed, we need to define the string constants
                if (fenced.equals("Y")) //$NON-NLS-1$
                    this.fenced = "FENCED"; //$NON-NLS-1$
                else
                    this.fenced = "NOT FENCED"; //$NON-NLS-1$

                //TODO: hard-coded the constants for now. 
                //if the method is not changed, we need to define the string constants
                final String threadsafe = r.getString("THREADSAFE"); //$NON-NLS-1$
                if (threadsafe.equals("Y")) //$NON-NLS-1$
                    this.threadsafe = "THREADSAFE"; //$NON-NLS-1$
                else
                    this.threadsafe = "NOT THREADSAFE"; //$NON-NLS-1$
				
				final String federated = r.getString("FEDERATED"); //$NON-NLS-1$
				if(federated.equals("Y")) this.federated = true; //$NON-NLS-1$
				else this.federated = false;
				
				final int resultSets = r.getInt("RESULT_SETS"); //$NON-NLS-1$
				this.maxResultSets = resultSets;
			
				final String packagename = r.getString("PACKAGENAME"); //$NON-NLS-1$
				if (!"JAVA".equals(getLanguage())) { //$NON-NLS-1$
				   db2Package = packagename;
				   db2PackageLoaded = true;
				}
                
                final String externalName = r.getString("IMPLEMENTATION"); //$NON-NLS-1$
                if (!"JAVA".equals(getLanguage()) && !"SQL".equals(getLanguage()) ) { //$NON-NLS-1$
                   setExternalName(externalName);
                }

				updateImplicitSchemaFlag();
//				final String text = r.getString("TEXT");
//				if (text != null) {
//					//Source source = (Source) factory.create(SQLRoutinesPackage.eINSTANCE.getSource());
//                    //The data development project needs DB2Source.
//                    DB2Source source = DB2ModelFactory.eINSTANCE.createDB2Source();
//					source.setBody(text);
//                    if (packagename != null) {
//                        source.setDb2PackageName(packagename);       
//                    }                    
//					super.getSource().add(source);	
//				}                
			}
			r.close();
			s.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        //Tt's appropriate to load source and java sp so that the object is completely loaded.
        //There are situations (drop sp) when getJavaSP is called after the JAVA SP has
        //been dropped from the DB and the query does not return any information. -- yehsh
        //loadSource() needs to know the jar schema and jar id 
        //call loadJavaProcedure() first to avoid querying the catalog twice. -- yehsh
//        loadJavaProcedure();
//        loadSource();
		this.eSetDeliver(deliver);
	}

    private synchronized JavaProcedureInfo getJavaProcedureInfo() {
        if (myJavaProcedureInfo != null) return myJavaProcedureInfo;
        final Database database = this.getSchema().getDatabase();
		final DatabaseDefinition databaseDefinition = RDBCorePlugin
				.getDefault().getDatabaseDefinitionRegistry().getDefinition(
						database);
        
		myJavaProcedureInfo = JavaProcedureProviderRegistry.INSTANCE.getProvider(
				databaseDefinition).getProviderInstance(this,
				this.getConnection());
        return myJavaProcedureInfo;        
    }
    
	private synchronized void loadSource() {
		if(this.sourceLoaded) return;
		this.sourceLoaded = true;        
		
        EList extList = super.getExtendedOptions();

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	

		try {
			String lang = getLanguage();
			if("JAVA".equals(lang)) { //$NON-NLS-1$
                JavaProcedureInfo javaProcedureInfo = getJavaProcedureInfo();
                Source s = null;
                if (javaProcedureInfo != null) {
				    s = javaProcedureInfo.getSource();
                }
                else {
                    s = DB2ModelFactory.eINSTANCE.createDB2Source();   
                }
                this.setSource(s);
                //create extended options
                DB2ExtendedOptions extOpts =DB2ModelFactory.eINSTANCE.createDB2ExtendedOptions();
                if (javaProcedureInfo != null) {
                    extOpts.setColid(javaProcedureInfo.getCollectionName());
                }
                extList.add(extOpts);                        
			}
            else if ("SQL".equals(lang)) { //$NON-NLS-1$
                final String query = "SELECT TEXT, DEBUG_MODE FROM SYSCAT.ROUTINES "; //$NON-NLS-1$
                Connection connection = this.getConnection();
                Statement s = connection.createStatement();
                ResultSet r = s.executeQuery(query 
                        + " WHERE ROUTINESCHEMA='" + LUWUtil.getIdentifier(this.getSchema().getName()) + "' AND ROUTINENAME='" + LUWUtil.getIdentifier(this.getName()) + "'" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        + " AND SPECIFICNAME='" + LUWUtil.getIdentifier(this.getSpecificName()) + "'"); //$NON-NLS-1$ //$NON-NLS-2$
                String text = "";
                String debugMode = "";
                while (r.next()) {
					String expression = r.getString("TEXT"); //$NON-NLS-1$
					if (expression != null) {
						text += expression.trim()+" ";
					}
	                debugMode = r.getString("DEBUG_MODE");
				}

				//remove un-readable character
				text = text.replaceAll("[\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\u0008\u000b\u000c\u000e\u000f" +
	               		"\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f]", " ");
				DB2Source source = DB2ModelFactory.eINSTANCE.createDB2Source();
				source.setBody(text);
				if (getDb2Package() != null) {
					source.setDb2PackageName(getDb2Package());
				}
				this.setSource(source);
				r.close();
                s.close();
                
                //create extended options
                DB2ExtendedOptions extOpts =DB2ModelFactory.eINSTANCE.createDB2ExtendedOptions(); 
                if ("ALLOW".equalsIgnoreCase(debugMode.trim())) {
                	extOpts.setForDebug(true);
                } else {
                	extOpts.setForDebug(false);
                }
                
                extList.add(extOpts);              
            }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);				
	}
	
	private synchronized void loadJavaProcedure() {
		if(this.javaProcedureLoaded) return;
        this.javaProcedureLoaded = true;        
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			String lang = getLanguage();
			if("JAVA".equals(lang)) { //$NON-NLS-1$
                JavaProcedureInfo javaProcedureInfo = getJavaProcedureInfo();
                if (javaProcedureInfo != null) {
				    setJavaOptions(javaProcedureInfo.getJavaProcedure());
                }
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);				
	}

    private synchronized void loadExternalName() {
        if (this.externalNameLoaded) return;
        this.externalNameLoaded = true;
//        loadSource();
//        loadJavaProcedure();
        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        JavaProcedureInfo javaProcedureInfo = getJavaProcedureInfo();
        if (javaProcedureInfo != null) {
            this.setExternalName(javaProcedureInfo.getExternalName());    
        }        
        this.eSetDeliver(deliver);
    }
    
	private synchronized void loadDependencies() {
		if(this.dependencyLoaded) return;
		this.dependencyLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			LUWCatalogProcedure.loadDependencies(this.getConnection(), super.getDependencies(), this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);		
	}

	private synchronized void loadParameters() {
		if(this.parameterLoaded) return;
		this.parameterLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			LUWCatalogProcedure.loadParameters(this.getConnection(), super.getParameters(), this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);		
	}
	
	private synchronized void loadPrivileges() {
		if(this.privilegeLoaded) return;
		this.privilegeLoaded = true;

		EList privileges = super.getPrivileges();
		for (Iterator iter= privileges.iterator(); iter.hasNext();){
			Privilege privilege = (Privilege) iter.next();
			privilege.setGrantor(null);
			privilege.setGrantee(null);
		}
		
		privileges.clear();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogProcedure.loadPrivileges(this.getConnection(),privileges, this,"");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	
	public static void loadDependencies(Connection connection, EList dependencyList, Routine routine) throws SQLException {
		final Database database = routine.getSchema().getDatabase();
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();
			
		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery("SELECT BSCHEMA, BNAME, BTYPE FROM SYSCAT.ROUTINEDEP WHERE ROUTINESCHEMA='" //$NON-NLS-1$
					+ LUWUtil.getIdentifier(routine.getSchema().getName()) + "' AND ROUTINENAME='" + LUWUtil.getIdentifier(routine.getSpecificName()) + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		try {
			while(r.next()) {
				final String bschema = r.getString(1).trim();
				final String bname   = r.getString(2);
				final String btype   = r.getString(3);
				SQLObject obj = null;
				if(btype.equals("A")) {	//alias //$NON-NLS-1$
					obj = LUWCatalogProcedure.getTable(routine, bschema, bname);
				}
				else if(btype.equals("F")) {	//function //$NON-NLS-1$
					obj = LUWCatalogProcedure.getRountine(routine, bschema, bname);					
				}
				else if(btype.equals("O")) {	//privilege //$NON-NLS-1$
					continue;
				}
				else if(btype.equals("R")) {	//structure type //$NON-NLS-1$
					obj = LUWCatalogProcedure.getUserDefinedType(routine, bschema, bname);										
				}
				else if(btype.equals("S")) {	//MQT //$NON-NLS-1$
					obj = LUWCatalogProcedure.getTable(routine, bschema, bname);					
				}
				else if(btype.equals("T")) {	//table //$NON-NLS-1$
					obj = LUWCatalogProcedure.getTable(routine, bschema, bname);					
				}
				else if(btype.equals("U")) {	//typed table //$NON-NLS-1$
					obj = LUWCatalogProcedure.getTable(routine, bschema, bname);
				}
				else if(btype.equals("V")) {	//view //$NON-NLS-1$
					obj = LUWCatalogProcedure.getTable(routine, bschema, bname);					
				}
				else if(btype.equals("W")) {	//typed view //$NON-NLS-1$
					obj = LUWCatalogProcedure.getTable(routine, bschema, bname);					
				}
				else if(btype.equals("X")) {	//index extension //$NON-NLS-1$
					continue;
				}
				else if(btype.equals("N")) {	//Alias//$NON-NLS-1$
					obj = LUWCatalogProcedure.getTable(routine, bschema, bname);					
				}
				else {
					continue;
				}
				
				if(obj == null) continue;
				Dependency dep = (Dependency) factory.create(SQLSchemaPackage.eINSTANCE.getDependency());
				dep.setTargetEnd(obj);
				dependencyList.add(dep);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			
		r.close();
		s.close();
	}

	public static void loadParameters(Connection connection, EList parameterList, Routine routine) throws SQLException {
		final Database database = routine.getSchema().getDatabase();
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();
			
		String query="SELECT PARMNAME,ROWTYPE,LOCATOR,TYPESCHEMA,TYPENAME,LENGTH,SCALE,CODEPAGE,REMARKS,TARGET_TYPESCHEMA,TARGET_TYPENAME" + //$NON-NLS-1$
				" FROM SYSCAT.ROUTINEPARMS" + //$NON-NLS-1$
				" WHERE ROUTINESCHEMA='" + LUWUtil.getIdentifier(routine.getSchema().getName()) + "'" +  //$NON-NLS-1$ //$NON-NLS-2$
				" AND ROUTINENAME='" + LUWUtil.getIdentifier(routine.getName()) + "'" + //$NON-NLS-1$ //$NON-NLS-2$
				" AND SPECIFICNAME='" + LUWUtil.getIdentifier(routine.getSpecificName()) + "'" + //$NON-NLS-1$ //$NON-NLS-2$
				" ORDER BY ORDINAL"; //$NON-NLS-1$

		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);
		try {
			while(r.next()) {
				
				LUWCatalogParameter parameter = new LUWCatalogParameter();
				final String name = r.getString("PARMNAME"); //$NON-NLS-1$
				parameter.setName(name);

				final String rowtype = r.getString("ROWTYPE").trim(); //$NON-NLS-1$
				if (rowtype.equals("B")){ //$NON-NLS-1$
					parameter.setMode(ParameterMode.INOUT_LITERAL);
				} else if (rowtype.equals("O")) { //$NON-NLS-1$
					parameter.setMode(ParameterMode.OUT_LITERAL);
				} else if (rowtype.equals("P")) { //$NON-NLS-1$
					parameter.setMode(ParameterMode.IN_LITERAL);
				}

				final String locator = r.getString("LOCATOR"); //$NON-NLS-1$
				if (locator.equals("Y")) //$NON-NLS-1$
					parameter.setLocator(true);
				else 
					parameter.setLocator(false);

				String typeName = r.getString("TYPENAME"); //$NON-NLS-1$
				if (typeName.equalsIgnoreCase("FLOAT")){ //$NON-NLS-1$
					int length = r.getInt("LENGTH"); //$NON-NLS-1$
					if (length ==4) typeName="REAL"; //$NON-NLS-1$
					else typeName="DOUBLE"; //$NON-NLS-1$
				}
				PredefinedDataTypeDefinition typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition(typeName);
				if(typeDefinition != null) {
					
					if (typeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER) {
						final int codePage = r.getInt(8);
						if(codePage == 0) {
							typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("CHAR () FOR BIT DATA"); //$NON-NLS-1$
						}
					}
					else if (typeDefinition.getPrimitiveType().getValue() == PrimitiveType.CHARACTER_VARYING) {
						final int codePage = r.getInt(8);
						if(codePage == 0) {
							if (typeName.equals("LONG VARCHAR")){
								typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("LONG VARCHAR FOR BIT DATA"); //$NON-NLS-1$
							} else {
								typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition("VARCHAR () FOR BIT DATA"); //$NON-NLS-1$
							}
						}

					}

					
					PredefinedDataType 	type = databaseDefinition.getPredefinedDataType(typeDefinition);
					if(typeDefinition.isLengthSupported()) {
						final int length = r.getInt("LENGTH"); //$NON-NLS-1$
						EStructuralFeature feature = type.eClass().getEStructuralFeature("length");  //$NON-NLS-1$
						type.eSet(feature, new Integer(length));
					}
					else if(typeDefinition.isPrecisionSupported()) {
						if (typeName.equals("TIMESTAMP")) {
							int length = r.getInt("SCALE");
							EStructuralFeature feature = type.eClass().getEStructuralFeature("fractionalSecondsPrecision"); //$NON-NLS-1$
							type.eSet(feature, new Integer(length));
						}
						else {
							int length = r.getInt("LENGTH");
							if (typeName.equals("DECFLOAT")) {
								if (length == 8) length = 16;
								else length = 34;
							}
							EStructuralFeature feature = type.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
							type.eSet(feature, new Integer(length));
						}
					}
					
					if(typeDefinition.isScaleSupported()) {
						final int length = r.getInt("SCALE"); //$NON-NLS-1$
						EStructuralFeature feature = type.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
						type.eSet(feature, new Integer(length));
					}
					
					parameter.setContainedType(type);
				}
				else {
					if (typeName.equals("REFERENCE")){ //$NON-NLS-1$
						final String typeSchemaName = r.getString("TARGET_TYPESCHEMA").trim(); //$NON-NLS-1$
						final String udtName = r.getString("TARGET_TYPENAME"); //$NON-NLS-1$
						parameter.setReferencedType(LUWCatalogProcedure.getUserDefinedType(routine,typeSchemaName,udtName));
					} else {
						final String typeSchemaName = r.getString("TYPESCHEMA").trim(); //$NON-NLS-1$
						parameter.setReferencedType(LUWCatalogProcedure.getUserDefinedType(routine,typeSchemaName,typeName));
					}
				}
				
				parameter.setDescription(r.getString("REMARKS")); //$NON-NLS-1$
				
				parameterList.add(parameter);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			
		r.close();
		s.close();
	}
	
	public static void loadPrivileges(Connection connection, EList privilegeList,Routine routine, String granteeFilter) throws SQLException {
		final Schema schema = routine.getSchema();
		final Database database = ModelHelper.getDatabase(schema);

		int options = ((LUWCatalogDatabase)database).getLoadOptions();
		if ((options & DatabaseREProvider.EXCLUDE_ACCESS_CONTROL)== DatabaseREProvider.EXCLUDE_ACCESS_CONTROL) return;

		Statement s = connection.createStatement();
		String query = "SELECT GRANTOR,GRANTEE,GRANTEETYPE" +
					",EXECUTEAUTH" +
					" FROM SYSCAT.ROUTINEAUTH" + 
					" WHERE SCHEMA='" + LUWUtil.getIdentifier(schema.getName()) + "'" +
					" AND SPECIFICNAME='" + LUWUtil.getIdentifier(routine.getSpecificName()) + "'" ;
		if (granteeFilter != null && !"".equals(granteeFilter)) query += " AND " + granteeFilter;

		ResultSet r = s.executeQuery(query);
		try {
			String userName = connection.getMetaData().getUserName();
			while(r.next()) {
				final String grantorId = r.getString("GRANTOR").trim();
				AuthorizationIdentifier grantor = LUWCatalogDatabase.getAuthorizationId(database, grantorId,null);
				final String granteeId = r.getString("GRANTEE").trim();
				AuthorizationIdentifier grantee = null;
				final String granteeType = r.getString("GRANTEETYPE");
				if ("G".equals(granteeType)) {
					grantee = LUWCatalogDatabase.getAuthorizationId(database, granteeId, "G");
				} else if ("R".equals(granteeType)) {
					grantee = LUWCatalogDatabase.getAuthorizationId(database, granteeId, "R");
				} else {
					grantee = LUWCatalogDatabase.getAuthorizationId(database, granteeId, "U");
				}
				boolean isSystemGranted = granteeId.equalsIgnoreCase(userName);
				final String alterAuth = r.getString("EXECUTEAUTH");
				if (alterAuth.equals("N")) {
				} else {
					LUWCatalogPrivilege privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_EXECUTE);
					if (alterAuth.equals("G")) {
						privilege.setGrantable(true);
					}
					privilegeList.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		r.close();
		s.close();
	}


	public static Schema getSchema(Routine routine, String schemaName) {
		Schema s = routine.getSchema();
		if(s.getName().equals(schemaName)) return s;
		Database db = s.getDatabase();
		if (db instanceof LUWCatalogDatabase){
			s = ((LUWCatalogDatabase)db).getSchema(schemaName);
			if (s != null) return s;
		} 
		Iterator it = db.getSchemas().iterator();
		while(it.hasNext()) {
			s = (Schema) it.next();
			if(s.getName().equals(schemaName)) return s;			
		}
		
		Schema schema = new LUWCatalogSchema();
		schema.setName(schemaName);
		schema.setDatabase(db);

		if (db instanceof LUWCatalogDatabase){
			((LUWCatalogDatabase)db).cacheSchema(schema);
		}
		
		return schema;
		
		
	}
	
	public static Table getTable(Routine routine, String schemaName, String tableName) {
		Schema schema = LUWCatalogProcedure.getSchema(routine, schemaName);
		if(schema instanceof LUWCatalogSchema){
			Table t = ((LUWCatalogSchema)schema).getTable(schemaName,tableName);
			if (t != null) return t;
		} 
		
		Iterator it = schema.getTables().iterator();
		while(it.hasNext()) {
			Table table = (Table) it.next();
			if(table.getName().equals(tableName)) return table;			
		}

		return null;
	
	}
		
	public static Routine getRountine(Routine routine, String schemaName, String specificName) {
		Schema schema = LUWCatalogProcedure.getSchema(routine, schemaName);
		Iterator it = schema.getRoutines().iterator();
		while(it.hasNext()) {
			Routine r = (Routine) it.next();
			if(specificName.equals(r.getSpecificName())) return r;			
		}
		return null;
	}

	public static UserDefinedType getUserDefinedType(Routine routine, String schemaName, String userDefinedTypeName) {
		Schema schema = LUWCatalogProcedure.getSchema(routine, schemaName);
		Iterator it = schema.getUserDefinedTypes().iterator();
		while(it.hasNext()) {
			UserDefinedType userDefinedType = (UserDefinedType) it.next();
			if(userDefinedType.getName().equals(userDefinedTypeName)) return userDefinedType;			
		}
		return null;		
	}
	
	
	public static Trigger getTrigger(Routine routine, String schemaName, String tabName, String triggerName) {
		Table table = LUWCatalogProcedure.getTable(routine, schemaName,tabName);
		if (table != null) {
			Iterator it = table.getTriggers().iterator();
			while(it.hasNext()) {
				Trigger r = (Trigger) it.next();
				if(r.getName().equals(triggerName)) return r;			
			}
		}
		return null;
	}
	
	public static TableConstraint getTableConstraint(Routine routine, String schemaName,String  tableName, String constName) {
		Table t = LUWCatalogProcedure.getTable(routine, schemaName,tableName);
		if (!(t instanceof BaseTable)) return null;
		Iterator it = ((BaseTable)t).getConstraints().iterator();
		while(it.hasNext()) {
			TableConstraint constraint = (TableConstraint) it.next();
			if(constraint.getName().equals(constName)) return constraint;			
		}

		return null;
	}

	public static DB2Package getDb2Package(Routine routine, String schemaName, String pkgName, String pkgUniqueID) {
		Schema schema = LUWCatalogProcedure.getSchema(routine, schemaName);
		if(schema instanceof LUWCatalogSchema){
			return  ((LUWCatalogSchema)schema).getDB2Package(pkgName, pkgUniqueID);
		} 
		
		return null;
	}
    
    /**
    * If the routine is SQL, discover if the routine has been created without
    * a schema qualifier. 
    * <p>
    * @param aRoutine The routine to analyze
    * @return <code>true</code> if the routine was created without a schema qualifier.
    */
   private void updateImplicitSchemaFlag() {
      boolean b = false;
      if (this != null) {
         b = this.isImplicitSchema();
         if (!b && this.getLanguage().equalsIgnoreCase("SQL")) { //$NON-NLS-1$
            Source src = this.getSource();
            String body = src.getBody();
            if (body != null) {
               String[] tokens = body.split(" "); //$NON-NLS-1$
               for (int i = 0; i < tokens.length; i++) {
                  if (tokens[i].indexOf('.') > -1) {
                     break; // the implicit flag is false by default
                  }
                  else if (tokens[i].indexOf('(') > -1) {
                     b = true;
                     break;
                  }
               }
            }
         }
      }
      setImplicitSchema(b);
   }
    
	private Collection getImpactedObjects(){
		Collection impacts = new ArrayList();
		Connection connection = this.getConnection();

		impacts.addAll(LUWCatalogProcedure.getImpactedTriggers(connection, this));
		impacts.addAll(LUWCatalogProcedure.getImpactedTables(connection, this));
		impacts.addAll(LUWCatalogProcedure.getImpactedConstraints(connection, this));
		impacts.addAll(LUWCatalogProcedure.getImpactedRoutines(connection, this));
		impacts.addAll(LUWCatalogProcedure.getImpactedPackages(connection, this));

		return impacts;
	}

	protected static Collection getImpactedTriggers(Connection connection, Routine routine) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT DISTINCT A.TABSCHEMA, A.TABNAME, A.TRIGNAME" +
					" FROM SYSCAT.TRIGGERS A, SYSCAT.TRIGDEP B" +
					" WHERE (B.BNAME='" + LUWUtil.getIdentifier(routine.getSpecificName()) + "'" +
					" AND B.BSCHEMA='" + LUWUtil.getIdentifier(routine.getSchema().getName()) + "'" +
					" AND B.BTYPE='F'" +
					" AND A.TRIGNAME=B.TRIGNAME" +
					" AND A.TRIGSCHEMA=B.TRIGSCHEMA)" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String trigName = r.getString("TRIGNAME").trim();
				final String tabName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				Trigger trigger = LUWCatalogProcedure.getTrigger(routine, schemaName, tabName, trigName);
				if (trigger !=  null) {
					impacts.add(trigger);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
//			e.printStackTrace();
		}
		return impacts;
	}

	protected static Collection getImpactedTables(Connection connection, Routine routine) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT DISTINCT TABNAME, TABSCHEMA" +
					" FROM SYSCAT.TABDEP" +
					" WHERE BTYPE='F'" +
					" AND BNAME ='" + LUWUtil.getIdentifier(routine.getSpecificName()) + "'" +
					" AND BSCHEMA = '" + LUWUtil.getIdentifier(routine.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String tabName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				Table t = LUWCatalogProcedure.getTable(routine, schemaName, tabName);
				if (t !=  null) {
					impacts.add(t);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
//			e.printStackTrace();
		}
		return impacts;
	}

	protected static Collection getImpactedConstraints(Connection connection, Routine routine) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT CONSTNAME,TABNAME,TABSCHEMA" +
					" FROM SYSCAT.CONSTDEP" +
					" WHERE BTYPE='F'" +
					" AND BNAME='" + LUWUtil.getIdentifier(routine.getSpecificName()) + "'" +
					" AND BSCHEMA='" + LUWUtil.getIdentifier(routine.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String constName = r.getString("CONSTNAME").trim();
				final String tabName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				TableConstraint constraint = LUWCatalogProcedure.getTableConstraint(routine, schemaName, tabName, constName);
				if (constraint !=  null) {
					impacts.add(constraint);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
//			e.printStackTrace();
		}
		return impacts;
	}

	protected static Collection getImpactedRoutines(Connection connection, Routine routine) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT ROUTINENAME,ROUTINESCHEMA" +
					" FROM SYSCAT.ROUTINEDEP" +
					" WHERE BTYPE='F'" +
					" AND BNAME='" + LUWUtil.getIdentifier(routine.getSpecificName()) + "'" +
					" AND BSCHEMA='" + LUWUtil.getIdentifier(routine.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String routineName = r.getString("ROUTINENAME").trim();
				final String schemaName = r.getString("ROUTINESCHEMA").trim();
				Routine p = LUWCatalogProcedure.getRountine(routine, schemaName, routineName);
				if (p !=  null) {
					impacts.add(p);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
//			e.printStackTrace();
		}
		return impacts;
	}
	
	protected static Collection getImpactedPackages(Connection connection, Routine routine) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT PKGNAME, PKGSCHEMA, HEX(UNIQUE_ID) AS UID" +
					" FROM SYSCAT.PACKAGEDEP" +
					" WHERE BTYPE = 'F'" +
					" AND BNAME='" + LUWUtil.getIdentifier(routine.getSpecificName()) + "'" +
					" AND BSCHEMA='" + LUWUtil.getIdentifier(routine.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String pkgName = r.getString("PKGNAME").trim();
				final String schemaName = r.getString("PKGSCHEMA").trim();
				final String pkgUniqueID = r.getString("UID").trim();
				DB2Package p = LUWCatalogProcedure.getDb2Package(routine, schemaName, pkgName, pkgUniqueID);
				if (p !=  null) {
					impacts.add(p);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			//e.printStackTrace();
		}
		return impacts;
	}
	
	public static Collection getStatistics(Connection connection, Routine routine){
		Collection statistics = new ArrayList();
		try {
			String query = "SELECT IOS_PER_INVOC,INSTS_PER_INVOC,IOS_PER_ARGBYTE,INSTS_PER_ARGBYTE" +
				",PERCENT_ARGBYTES,INITIAL_IOS,INITIAL_INSTS,CARDINALITY,SELECTIVITY" +
				" FROM SYSSTAT.ROUTINES" + 
				" WHERE ROUTINESCHEMA='" + LUWUtil.getIdentifier(routine.getSchema().getName()) + "'" +
				" AND ROUTINENAME ='" + LUWUtil.getIdentifier(routine.getName()) + "'" +
				" AND SPECIFICNAME ='" + LUWUtil.getIdentifier(routine.getSpecificName()) + "'" +
				" FOR FETCH ONLY";
			
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				CatalogStatistics stats = null;

				final float ios_per_Invoc = r.getFloat("IOS_PER_INVOC");
				if (ios_per_Invoc != -1) {
					stats = new CatalogStatistics("IOS_PER_INVOC",LUWCatalogMessages.STAT_IOS_PER_INVOC,LUWCatalogMessages.STAT_IOS_PER_INVOC_DES,ios_per_Invoc,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}

				final float insts_per_Invoc = r.getFloat("INSTS_PER_INVOC");
				if (insts_per_Invoc != -1) {
					stats = new CatalogStatistics("INSTS_PER_INVOC",LUWCatalogMessages.STAT_INSTS_PER_INVOC,LUWCatalogMessages.STAT_INSTS_PER_INVOC_DES,insts_per_Invoc,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}

				final float ios_per_Argbyte = r.getFloat("IOS_PER_ARGBYTE");
				if (ios_per_Argbyte != -1) {
					stats = new CatalogStatistics("IOS_PER_ARGBYTE",LUWCatalogMessages.STAT_IOS_PER_ARGBYTE,LUWCatalogMessages.STAT_IOS_PER_ARGBYTE_DES,ios_per_Argbyte,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}

				final float insts_per_Argbyte = r.getFloat("INSTS_PER_ARGBYTE");
				if (insts_per_Argbyte != -1) {
					stats = new CatalogStatistics("INSTS_PER_ARGBYTE",LUWCatalogMessages.STAT_INSTS_PER_ARGBYTE,LUWCatalogMessages.STAT_INSTS_PER_ARGBYTE_DES,insts_per_Argbyte,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}
				
				final int percent_Argbyte = r.getInt("PERCENT_ARGBYTES");
				if (percent_Argbyte != -1) {
					stats = new CatalogStatistics("PERCENT_ARGBYTES",LUWCatalogMessages.STAT_PERCENT_ARGBYTE,LUWCatalogMessages.STAT_PERCENT_ARGBYTE_DES,percent_Argbyte,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}


				final float initial_ios = r.getFloat("INITIAL_IOS");
				if (initial_ios != -1) {
					stats = new CatalogStatistics("INITIAL_IOS",LUWCatalogMessages.STAT_INITIAL_IOS,LUWCatalogMessages.STAT_INITIAL_IOS_DES,initial_ios,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}
				
				final float initial_insts = r.getFloat("INITIAL_INSTS");
				if (initial_insts != -1) {
					stats = new CatalogStatistics("INITIAL_INSTS",LUWCatalogMessages.STAT_INITIAL_INSTS,LUWCatalogMessages.STAT_INITIAL_INSTS_DES,initial_insts,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}
				
				final BigInteger card = r.getBigDecimal("CARDINALITY").toBigInteger();
				if (card.intValue() != -1) {
					stats = new CatalogStatistics("CARDINALITY",LUWCatalogMessages.STAT_ROUTINE_CARDINALITY,LUWCatalogMessages.STAT_ROUTINE_CARDINALITY_DES,card,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}

				final float selectivity= r.getFloat("SELECTIVITY");
				if (selectivity != -1) {
					stats = new CatalogStatistics("SELECTIVITY",LUWCatalogMessages.STAT_SELECTIVITY,LUWCatalogMessages.STAT_SELECTIVITY_DES,selectivity,"SYSSTAT.ROUTINES");
					statistics.add(stats);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			//e.printStackTrace();
		}
		return statistics;
	}

	protected void getPrivilegesWithFilter(String granteeFilter) throws SQLException {
		if (this.privilegeLoaded) return;
		EList privileges = super.getPrivileges();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogProcedure.loadPrivileges(this.getConnection(), privileges, this,granteeFilter);
		}catch( Exception e){
		}
		this.eSetDeliver(deliver);
	}

	private boolean loaded = false;
	private boolean dependencyLoaded = false;
	private boolean parameterLoaded = false;
	private boolean sourceLoaded = false;
	private boolean javaProcedureLoaded = false;
    private boolean externalNameLoaded = false;
    private boolean privilegeLoaded = false;
    private JavaProcedureInfo myJavaProcedureInfo;
    private boolean db2PackageLoaded = false;
    protected String db2Package;
	private boolean statisticsLoaded = false;
	private boolean impactsLoaded = false;

	private Collection impacts = new ArrayList();
	private Collection statistics = new ArrayList();


}
