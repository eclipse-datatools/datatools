/*******************************************************************************
 * Copyright (c) 2005, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.util.Set;

import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;

 /**
 * @author chetabha
 *
 */
 
public class EngineeringOptionID extends org.eclipse.datatools.connectivity.sqm.core.definition.EngineeringOptionID {
    public final static String GENERATE_LABELS                 = "GENERATE_LABELS"; //$NON-NLS-1$
    public final static String GENERATE_SYNONYM                = "GENERATE_SYNONYM"; //$NON-NLS-1$
    public final static String GENERATE_DATABASE               = "GENERATE_DATABASE"; //$NON-NLS-1$
    public final static String GENERATE_MQT                    = "GENERATE_MQT"; //$NON-NLS-1$
    public final static String GENERATE_ALIAS                  = "GENERATE_ALIAS"; //$NON-NLS-1$
    public final static String GENERATE_CK_CONSTRAINTS         = "GENERATE_CK_CONSTRAINTS"; //$NON-NLS-1$
    public final static String GENERATE_BUFFERPOOL             = "GENERATE_BUFFERPOOL"; //$NON-NLS-1$
    public final static String GENERATE_PARTITIONGROUP         = "GENERATE_PARTITIONGROUP"; //$NON-NLS-1$
    public final static String GENERATE_STORAGEGROUP           = "GENERATE_STORAGEGROUP"; //$NON-NLS-1$
    public final static String GENERATE_SCHEMAS                = "GENERATE_SCHEMAS"; //$NON-NLS-1$
    public final static String GENERATE_WRAPPER                = "GENERATE_WRAPPER"; //$NON-NLS-1$    
    public final static String GENERATE_REMOTESERVER           = "GENERATE_REMOTESERVER"; //$NON-NLS-1$
    public final static String GENERATE_USERMAPPING            = "GENERATE_USERMAPPING"; //$NON-NLS-1$
    public final static String GENERATE_NICKNAME               = "GENERATE_NICKNAME"; //$NON-NLS-1$
    public final static String GENERATE_OLAP                   = "GENERATE_OLAP"; //$NON-NLS-1$
    // fsp - pyl
    public final static String GENERATE_FEDERATEDPROCEDURES    = "GENERATE_FEDERATEDPROCEDURES"; //$NON-NLS-1$  
    public final static String GENERATE_PACKAGE				   = "GENERATE_PACKAGE"; //$NON-NLS-1$  
    public final static String GENERATE_PACKAGE_BODY		   = "GENERATE_PACKAGE_BODY"; //$NON-NLS-1$  
    public final static String GENERATE_BACKUP_TABLE		   = "GENERATE_BACKUP_TABLE"; //$NON-NLS-1$  
    public final static String GENERATE_STATISTICS		       = "GENERATE_STATISTICS"; //$NON-NLS-1$  
    public final static String GENERATE_ROLE		       	   = "GENERATE_ROLE"; //$NON-NLS-1$  
    public final static String GENERATE_USER		       	   = "GENERATE_USER"; //$NON-NLS-1$  
    public final static String GENERATE_GROUP		       	   = "GENERATE_GROUP"; //$NON-NLS-1$  
    public final static String GENERATE_XMLSCHEMA              = "GENERATE_XMLSCHEMA"; //$NON-NLS-1$
    public final static String XMLSCHEMA_CONNECTION_NAME       = "XMLSCHEMA_CONNECTION_NAME"; //$NON-NLS-1$
    public final static String XMLSCHEMA_DIRECTORY             = "XMLSCHEMA_DIRECTORY"; //$NON-NLS-1$
    public final static String GENERATE_SECURITY_POLICY        = "GENERATE_SECURITY_POLICY"; //$NON-NLS-1$
    
	public final static String GENERATE_FULLY_QUALIFIED_NAME   = "GENERATE_FULLY_QUALIFIED_NAME";  //$NON-NLS-1$
	public final static String GENERATE_QUOTED_IDENTIFIER      = "GENERATE_QUOTED_IDENTIFIER"; //$NON-NLS-1$ 
	public final static String GENERATE_DROP_STATEMENTS        = "GENERATE_DROP_STATEMENTS"; //$NON-NLS-1$ 
	public final static String GENERATE_CREATE_STATEMENTS      = "GENERATE_CREATE_STATEMENTS"; //$NON-NLS-1$ 
	public final static String GENERATE_COMMENTS               = "GENERATE_COMMENTS"; //$NON-NLS-1$
	public final static String GENERATE_IN_TABLESPACE_CLAUSE   = "GENERATE_IN_TABLESPACE_CLAUSE"; //$NON-NLS-1$
	public final static String GENERATE_ON_FILEGROUP_CLAUSE    = "GENERATE_ON_FILEGROUP_CLAUSE"; //$NON-NLS-1$
	public final static String GENERATE_USE_DOMAIN_IF_EXIST    = "USE_DOMAIN_IF_EXIST"; //$NON-NLS-1$
	public final static String GENERATE_CREATE_OR_REPLACE      = "CREATE_OR_REPLACE"; //$NON-NLS-1$
	public final static String GENERATE_TABLES                 = "GENERATE_TABLES"; //$NON-NLS-1$ 
	public final static String GENERATE_TABLESPACES            = "GENERATE_TABLESPACES"; //$NON-NLS-1$
	public final static String GENERATE_FILEGROUPS            = "GENERATE_FILEGROUPS"; //$NON-NLS-1$
	public final static String GENERATE_INDICES                = "GENERATE_INDICES"; //$NON-NLS-1$
	public final static String GENERATE_STOREDPROCEDURES       = "GENERATE_STOREDPROCEDURES"; //$NON-NLS-1$
	public final static String GENERATE_FUNCTIONS              = "GENERATE_FUNCTIONS"; //$NON-NLS-1$
	public final static String GENERATE_VIEWS                  = "GENERATE_VIEWS"; //$NON-NLS-1$
	public final static String GENERATE_TRIGGERS               = "GENERATE_TRIGGERS"; //$NON-NLS-1$
	public final static String GENERATE_SEQUENCES              = "GENERATE_SEQUENCES"; //$NON-NLS-1$
	public final static String GENERATE_USER_DEFINED_TYPE      = "GENERATE_USER_DEFINED_TYPE"; //$NON-NLS-1$
	public final static String GENERATE_ENFORCED_CONSTRAINTS   = "GENERATE_ENFORCED_CONSTRAINTS"; //$NON-NLS-1$
	public final static String GENERATE_PK_CONSTRAINTS         = "GENERATE_PK_CONSTRAINTS"; //$NON-NLS-1$
	public final static String GENERATE_FK_CONSTRAINTS         = "GENERATE_FK_CONSTRAINTS"; //$NON-NLS-1$
	public final static String GENERATE_PRIVILEGE              = "GENERATE_PRIVILEGE"; //$NON-NLS-1$
	public final static String CHECK_MODEL              		= "CHECK_MODEL"; //$NON-NLS-1$
	public final static String GENERATE_MODULE              		= "GENERATE_MODULE"; //$NON-NLS-1$
	public final static String GENERATE_MODULE_CONDITION              		= "GENERATE_CONDITION"; //$NON-NLS-1$
	public final static String GENERATE_GLOBAL_VARIABLE              = "GENERATE_GLOBAL_VARIABLE"; //$NON-NLS-1$
	public final static String GENERATE_HISTORY_TABLE          = "GENERATE_HISTORY_TABLE"; //$NON-NLS-1$
	public final static String DROP_RESTRICT                   = "DROP_RESTRICT"; //$NON-NLS-1$
	public final static String GENERATE_ROW_PERMISSIONS        = "GENERATE_ROW_PERMISSIONS"; //$NON-NLS-1$
	public final static String GENERATE_COLUMN_MASKS	       = "GENERATE_COLUMN_MASKS"; //$NON-NLS-1$
	public final static String GENERATE_TEMPORARY_TABLES	       = "GENERATE_TEMPORARY_TABLES"; //$NON-NLS-1$
	public final static String GENERATE_DOMAIN	       = "GENERATE_DOMAIN"; //$NON-NLS-1$
	
    public static boolean checkModel(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.CHECK_MODEL, options);
    }
    
    public static boolean generateDropStatement(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_DROP_STATEMENTS, options);
    }

    public static boolean generateTemporaryTables(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_TEMPORARY_TABLES, options);
    }

 	// Data Preservation   
    public static boolean generateBackupStatement(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_BACKUP_TABLE, options);
    }
 	// End Data Preservation   
    
    public static boolean generateCreateStatement(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_CREATE_STATEMENTS, options);
    }

    public static boolean generateCommentStatement(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_COMMENTS, options);
    }

    public static boolean generateLabelStatement(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_LABELS, options);
    }

    public static boolean generateGrantStatement(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_PRIVILEGE, options);
    }
    
    public static boolean generateRevokeStatement(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_PRIVILEGE, options);
    }

    public static boolean generateDatabase(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_DATABASE, options);
    }
    
    public static boolean generateSchemas(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_SCHEMAS, options);
    }

    public static boolean generateInTablespaceClause(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_IN_TABLESPACE_CLAUSE, options);
    }

    public static boolean generateOnFileGroupClause(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_ON_FILEGROUP_CLAUSE, options);
    }
    
    public static boolean useDomain(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_USE_DOMAIN_IF_EXIST, options);
    }
    
    public static boolean generateCreateOrReplace(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_CREATE_OR_REPLACE, options);
    }
    
    public static boolean generateQuotedIdentifiers(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER, options);
    }

    public static boolean generateFullyQualifiedNames(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME, options);
    }

    public static boolean generatePartitionGroup(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_PARTITIONGROUP, options);
    }

    public static boolean generateStorageGroups(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_STORAGEGROUP, options);
    }
    
    public static boolean generateBufferPool(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_BUFFERPOOL, options);
    }
    
    public static boolean generateAliases(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_ALIAS, options);
    }

    public static boolean generateSynonyms(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_SYNONYM, options);
    }
    
    public static boolean generateTables(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_TABLES, options);
    }
    
    public static boolean generateMQTs(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_MQT, options);
    }

    public static boolean generateTablespaces(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_TABLESPACES, options);
    }
    public static boolean generateFileGroups(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_FILEGROUPS, options);
    }
    
    public static boolean generateIndexes(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_INDICES, options);
    }
    
    public static boolean generateStoredProcedures(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_STOREDPROCEDURES, options);
    }

    // fsp - pyl
    public static boolean generateFederatedProcedures(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_FEDERATEDPROCEDURES, options);
    }
    
    public static boolean generateViews(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_VIEWS, options);
    }

    public static boolean generateTriggers(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_TRIGGERS, options);
    }

    public static boolean generateSequences(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_SEQUENCES, options);
    }
    
    public static boolean generateFunctions(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_FUNCTIONS, options);
    }
    
    public static boolean generateUserDefinedTypes(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_USER_DEFINED_TYPE, options);
    }

    public static boolean generateDomains(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_DOMAIN, options);
    }

    public static boolean generateCKConstraints(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_CK_CONSTRAINTS, options);
    }
    public static boolean generatePKConstraints(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_PK_CONSTRAINTS, options);
    }
    public static boolean generateFKConstraints(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_FK_CONSTRAINTS, options);
    }
    public static boolean generatePacakges(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_PACKAGE, options);
    }
    public static boolean generatePacakgeBodys(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_PACKAGE_BODY, options);
    }
    public static boolean generateStatistics(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_STATISTICS, options);
    }
    public static boolean generateRoles(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_ROLE, options);
    }
    public static boolean generateUsers(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_USER, options);
    }
    public static boolean generateGroups(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_GROUP, options);
    }
    public static boolean generateModules(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_MODULE, options);
    }
    public static boolean generateModuleConditions(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_MODULE_CONDITION, options);
    }
    public static boolean generateGlobalVariables(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_GLOBAL_VARIABLE, options);
    }
    public static boolean generateHistoryTable(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_HISTORY_TABLE, options);
    }
    public static boolean generateXMLSchema(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_XMLSCHEMA, options);
    }
    public static String xmlSchemaConnectionName(EngineeringOption[] options){
        return getStringOptionByName(EngineeringOptionID.XMLSCHEMA_CONNECTION_NAME, options);
    }
    public static String xmlSchemaDirectory(EngineeringOption[] options){
        return getStringOptionByName(EngineeringOptionID.XMLSCHEMA_DIRECTORY, options);
    }
    public static boolean generateSecurityPolicy(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_SECURITY_POLICY, options);
    }
    public static boolean dropRestrict(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.DROP_RESTRICT, options);
    }   
    public static boolean generateRowPermissions(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_ROW_PERMISSIONS, options);
    }
    public static boolean generateColumnMasks(EngineeringOption[] options){
        return getOptionValueByID(EngineeringOptionID.GENERATE_COLUMN_MASKS, options);
    }
    public static String getStringOptionByName(String optionName, EngineeringOption[] options) {
    	String ret = null;
        for (int i = 0; i < options.length; i++){
            EngineeringOption option = options[i];
            if (option != null && option.getOptionName().equals(optionName)) {
                ret = option.getString();
                break;
            }
        }
        return ret;
	}
    
    public static boolean getOptionValue(String optionName, EngineeringOption[] options){
        boolean ret = false;
        for (int i = 0; i < options.length; i++){
            EngineeringOption option = (EngineeringOption) options[i];
            if (option != null && option.getOptionName().equals(optionName)) { //@d00058820gs
                ret = option.getBoolean();
                break;
            }
        }
        return ret;
    }
    
    public static final long DATABASE = 1;
    public static final long TABLE = 2;
    public static final long NICKNAME = 4;
    public static final long SERVER = 8;
    public static final long WRAPPER = 16;
    public static final long USER_MAPPING = 32;
    public static final long OLAP_OBJECT = 64;
    public static final long TABLESPACE = 128;
    public static final long INDEX = 256;
    public static final long PROCEDURE = 512;
    public static final long USER_DEFINED_FUNCTION = 1024;
    public static final long VIEW = 2048;
    public static final long TRIGGER = 4096;
    public static final long SEQUENCE = 8192; 
    public static final long USER_DEFINED_TYPE = 16384;
    public static final long UNIQUE_CONSTRAINT = 32768;
    public static final long MATERIALIZED_QUERY_TABLE = 65536;
    public static final long ALIAS = 131072;
    public static final long CHECK_CONSTRAINT = 262144;
    public static final long FOREIGN_KEY = 524288;
    public static final long BUFFER_POOL = 1048576;
    public static final long PARTITION_GROUP = 2097152;
    public static final long SCHEMA = 4194304;
    public static final long DISTINCT_USER_DEFINED_TYPE = 8388608;
    public static final long STRUCTURED_USER_DEFINED_TYPE = 16777216;
    public static final long SYNONYM = 33554432;
    public static final long STORAGE_GROUP = 67108864;
    public static final long PRIVILEGE  = 134217728; 
    public static final long FEDERATEDPROCEDURE = 268435456;    // fsp - pyl
    public static final long PACKAGE  = 536870912; 
    public static final long PACKAGE_BODY = 1073741824;
    public static final long BACKUP_TABLE = new Long("2147483648").longValue(); // Data Preservation
    public static final long COLUMN = new Long("4294967296").longValue();
    public static final long STATISTICS = new Long("8589934592").longValue(); 
    public static final long ROLE = new Long("17179869184").longValue(); 
    public static final long MODULE = new Long("34359738368").longValue(); 
    public static final long MODULE_CONDITION = new Long("68719476736").longValue(); 
    public static final long GLOBAL_VARIABLE = new Long("137438953472").longValue(); 
    public static final long HISTORY_TABLE = new Long("274877906944").longValue(); 
    public static final long XMLSCHEMA = new Long("549755813888").longValue();
    public static final long FILEGROUP = new Long("1099511627776").longValue();
    public static final long SECURITY_POLICY = new Long("2199023255552").longValue();
    public static final long ROW_PERMISSIONS = new Long("4398046511104").longValue();
    public static final long COLUMN_MASKS = new Long("8796093022208").longValue();
    public static final long USER = new Long("17592186044416").longValue();
    public static final long GROUP = new Long("35184372088832").longValue();
    public static final long TEMPORARY_TABLE = new Long("70368744177664").longValue();
    public static final long DOMAIN = new Long("140737488355328").longValue();
    
    /**
     * @param s
     * @param mask
     */
    public static void populateOptions(Set s, long mask) {
        if ((mask & EngineeringOptionID.DATABASE) == EngineeringOptionID.DATABASE)
            s.add(EngineeringOptionID.GENERATE_DATABASE);
        if ((mask & EngineeringOptionID.TABLE) == EngineeringOptionID.TABLE)
            s.add(EngineeringOptionID.GENERATE_TABLES);
        if ((mask & EngineeringOptionID.NICKNAME) == EngineeringOptionID.NICKNAME)
            s.add(EngineeringOptionID.GENERATE_NICKNAME);
        // fsp - pyl
        if ((mask & EngineeringOptionID.FEDERATEDPROCEDURE) == EngineeringOptionID.FEDERATEDPROCEDURE)
            s.add(EngineeringOptionID.GENERATE_FEDERATEDPROCEDURES);
        if ((mask & EngineeringOptionID.SERVER) == EngineeringOptionID.SERVER)
            s.add(EngineeringOptionID.GENERATE_REMOTESERVER);
        if ((mask & EngineeringOptionID.WRAPPER) == EngineeringOptionID.WRAPPER)
            s.add(EngineeringOptionID.GENERATE_WRAPPER);
        if ((mask & EngineeringOptionID.USER_MAPPING) == EngineeringOptionID.USER_MAPPING)
            s.add(EngineeringOptionID.GENERATE_USERMAPPING);
        if ((mask & EngineeringOptionID.OLAP_OBJECT) == EngineeringOptionID.OLAP_OBJECT)
            s.add(EngineeringOptionID.GENERATE_OLAP);
        if ((mask & EngineeringOptionID.TABLESPACE) == EngineeringOptionID.TABLESPACE)
            s.add(EngineeringOptionID.GENERATE_TABLESPACES);
        if ((mask & EngineeringOptionID.FILEGROUP) == EngineeringOptionID.FILEGROUP) {
            s.add(EngineeringOptionID.GENERATE_FILEGROUPS);
        }
        if ((mask & EngineeringOptionID.INDEX) == EngineeringOptionID.INDEX)
            s.add(EngineeringOptionID.GENERATE_INDICES);
        if ((mask & EngineeringOptionID.PROCEDURE) == EngineeringOptionID.PROCEDURE)
            s.add(EngineeringOptionID.GENERATE_STOREDPROCEDURES);
        if ((mask & EngineeringOptionID.USER_DEFINED_FUNCTION) == EngineeringOptionID.USER_DEFINED_FUNCTION)
            s.add(EngineeringOptionID.GENERATE_FUNCTIONS);
        if ((mask & EngineeringOptionID.VIEW) == EngineeringOptionID.VIEW)
            s.add(EngineeringOptionID.GENERATE_VIEWS);
        if ((mask & EngineeringOptionID.TRIGGER) == EngineeringOptionID.TRIGGER)
            s.add(EngineeringOptionID.GENERATE_TRIGGERS);
        if ((mask & EngineeringOptionID.SEQUENCE) == EngineeringOptionID.SEQUENCE)
            s.add(EngineeringOptionID.GENERATE_SEQUENCES);
        if ((mask & EngineeringOptionID.USER_DEFINED_TYPE) == EngineeringOptionID.USER_DEFINED_TYPE)
            s.add(EngineeringOptionID.GENERATE_USER_DEFINED_TYPE);
        if ((mask & EngineeringOptionID.DOMAIN) == EngineeringOptionID.DOMAIN)
            s.add(EngineeringOptionID.GENERATE_DOMAIN);
        if ((mask & EngineeringOptionID.UNIQUE_CONSTRAINT) == EngineeringOptionID.UNIQUE_CONSTRAINT)
            s.add(EngineeringOptionID.GENERATE_PK_CONSTRAINTS);
        if ((mask & EngineeringOptionID.MATERIALIZED_QUERY_TABLE) == EngineeringOptionID.MATERIALIZED_QUERY_TABLE)
            s.add(EngineeringOptionID.GENERATE_MQT);
        if ((mask & EngineeringOptionID.ALIAS) == EngineeringOptionID.ALIAS)
            s.add(EngineeringOptionID.GENERATE_ALIAS);
        if ((mask & EngineeringOptionID.CHECK_CONSTRAINT) == EngineeringOptionID.CHECK_CONSTRAINT)
            s.add(EngineeringOptionID.GENERATE_CK_CONSTRAINTS);
        if ((mask & EngineeringOptionID.FOREIGN_KEY) == EngineeringOptionID.FOREIGN_KEY)
            s.add(EngineeringOptionID.GENERATE_FK_CONSTRAINTS);
        if ((mask & EngineeringOptionID.BUFFER_POOL) == EngineeringOptionID.BUFFER_POOL)
            s.add(EngineeringOptionID.GENERATE_BUFFERPOOL);
        if ((mask & EngineeringOptionID.SCHEMA) == EngineeringOptionID.SCHEMA)
            s.add(EngineeringOptionID.GENERATE_SCHEMAS);
        if ((mask & EngineeringOptionID.DISTINCT_USER_DEFINED_TYPE) == EngineeringOptionID.DISTINCT_USER_DEFINED_TYPE)
            s.add(EngineeringOptionID.GENERATE_USER_DEFINED_TYPE);
        if ((mask & EngineeringOptionID.STRUCTURED_USER_DEFINED_TYPE) == EngineeringOptionID.STRUCTURED_USER_DEFINED_TYPE)
            s.add(EngineeringOptionID.GENERATE_USER_DEFINED_TYPE);
        if ((mask & EngineeringOptionID.SYNONYM) == EngineeringOptionID.SYNONYM)
            s.add(EngineeringOptionID.GENERATE_SYNONYM);
        if ((mask & EngineeringOptionID.PARTITION_GROUP) == EngineeringOptionID.PARTITION_GROUP)
            s.add(EngineeringOptionID.GENERATE_PARTITIONGROUP);
        if ((mask & EngineeringOptionID.STORAGE_GROUP) == EngineeringOptionID.STORAGE_GROUP)
            s.add(EngineeringOptionID.GENERATE_STORAGEGROUP);
        if ((mask & EngineeringOptionID.PACKAGE) == EngineeringOptionID.PACKAGE)
            s.add(EngineeringOptionID.GENERATE_PACKAGE);
        if ((mask & EngineeringOptionID.PACKAGE_BODY) == EngineeringOptionID.PACKAGE_BODY)
            s.add(EngineeringOptionID.GENERATE_PACKAGE_BODY);
        if ((mask & EngineeringOptionID.BACKUP_TABLE) == EngineeringOptionID.BACKUP_TABLE)
            s.add(EngineeringOptionID.GENERATE_BACKUP_TABLE);  // Data Preservation
        if ((mask & EngineeringOptionID.STATISTICS) == EngineeringOptionID.STATISTICS)
            s.add(EngineeringOptionID.GENERATE_STATISTICS); 
        if ((mask & EngineeringOptionID.ROLE) == EngineeringOptionID.ROLE)
            s.add(EngineeringOptionID.GENERATE_ROLE); 
        if ((mask & EngineeringOptionID.USER) == EngineeringOptionID.USER)
            s.add(EngineeringOptionID.GENERATE_USER); 
        if ((mask & EngineeringOptionID.GROUP) == EngineeringOptionID.GROUP)
            s.add(EngineeringOptionID.GENERATE_GROUP); 
        if ((mask & EngineeringOptionID.PRIVILEGE) == EngineeringOptionID.PRIVILEGE)
            s.add(EngineeringOptionID.GENERATE_PRIVILEGE); 
        if ((mask & EngineeringOptionID.MODULE) == EngineeringOptionID.MODULE)
            s.add(EngineeringOptionID.GENERATE_MODULE); 
        if ((mask & EngineeringOptionID.MODULE_CONDITION) == EngineeringOptionID.MODULE_CONDITION)
            s.add(EngineeringOptionID.GENERATE_MODULE_CONDITION); 
        if ((mask & EngineeringOptionID.GLOBAL_VARIABLE) == EngineeringOptionID.GLOBAL_VARIABLE)
            s.add(EngineeringOptionID.GENERATE_GLOBAL_VARIABLE); 
        if ((mask & EngineeringOptionID.HISTORY_TABLE) == EngineeringOptionID.HISTORY_TABLE)
            s.add(EngineeringOptionID.GENERATE_HISTORY_TABLE); 
        if ((mask & EngineeringOptionID.XMLSCHEMA) == EngineeringOptionID.XMLSCHEMA) {
            s.add(EngineeringOptionID.GENERATE_XMLSCHEMA);
            s.add(EngineeringOptionID.XMLSCHEMA_CONNECTION_NAME);
            s.add(EngineeringOptionID.XMLSCHEMA_DIRECTORY);
        }
        if ((mask & EngineeringOptionID.SECURITY_POLICY) == EngineeringOptionID.SECURITY_POLICY)
            s.add(EngineeringOptionID.GENERATE_SECURITY_POLICY); 
        if ((mask & EngineeringOptionID.ROW_PERMISSIONS) == EngineeringOptionID.ROW_PERMISSIONS)
            s.add(EngineeringOptionID.GENERATE_ROW_PERMISSIONS); 
        if ((mask & EngineeringOptionID.COLUMN_MASKS) == EngineeringOptionID.COLUMN_MASKS)
            s.add(EngineeringOptionID.GENERATE_COLUMN_MASKS);
        if ((mask & EngineeringOptionID.TEMPORARY_TABLE) == EngineeringOptionID.TEMPORARY_TABLE)
            s.add(EngineeringOptionID.GENERATE_TEMPORARY_TABLES);

    }    
}


