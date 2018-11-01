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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategory;
import org.eclipse.datatools.enablement.ibm.IBMPluginActivator;
import org.eclipse.datatools.enablement.ibm.util.EngineeringOptionCategoryID;
import org.eclipse.datatools.enablement.ibm.util.EngineeringOptionID;
import org.eclipse.datatools.enablement.ibm.util.ModelHelper;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceInfo;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.parsers.sql.postparse.PostParseProcessor;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParseResult;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManagerProvider;
import org.eclipse.datatools.sqltools.parsers.sql.query.postparse.DataTypeResolver;
import org.eclipse.datatools.sqltools.parsers.sql.query.postparse.TableReferenceResolver;
import org.eclipse.emf.common.util.EList;
import org.osgi.service.prefs.Preferences;

 /**
 * @author chetabha
 *
 */
 
public class DdlGenerationUtility {
    private static OlapDdlBuilder olapDdlBuilder = null;
    private static ExtendDdlBuilder nicknameDdlBuilder = null;
    private static ExtendDdlBuilder federatedProcedureDdlBuilder = null; // fsp - pyl
    private static ExtendDdlBuilder remoteServerDdlBuilder = null;
    private static ExtendDdlBuilder wrapperDdlBuilder = null;
    private static ExtendDdlBuilder userMappingDdlBuilder = null;
    private static TypedElementLogicalDomainProvider elementDomainProvider= null;
    private static ModelValidationProvider modelValidationProvider = null;

    public static final String QUOTE_IDENTIFIERS_DEFAULT = "quoteIdentifiersDefault" ; //$NON-NLS-1$
    public static final String QUALIFY_NAMES_DEFAULT = "qualifyNamesDefault" ; //$NON-NLS-1$
    public static final boolean DEFAULT_QUOTE_IDENTIFIERS_DEFAULT_VALUE = true;
    public static final boolean DEFAULT_QUALIFY_NAMES_DEFAULT_VALUE = true;
    protected static boolean quoteIdentifiersDefault = DEFAULT_QUOTE_IDENTIFIERS_DEFAULT_VALUE;
    protected static boolean qualifyNamesDefault = DEFAULT_QUALIFY_NAMES_DEFAULT_VALUE;
    public static final String SPUFI_RUNSQLSTM_FORMAT = "spufi_runsqlstm" ; //$NON-NLS-1$
    public static final boolean SPUFI_RUNSQLSTM_FORMAT_VALUE = false;
    protected static boolean spufi_runsqlstm_format = SPUFI_RUNSQLSTM_FORMAT_VALUE;

    private final static String CREATE_STATEMENT ="CREATE";
    private final static String DROP_STATEMENT ="DROP";
    private final static String STATEMENT_TYPE_SEPARATOR = ";";
    private final static String CONTEXT_SEPARATOR = ",";
    private final static String CONTEXT_APPLY_FLAG = "?";
    public final static String PATTERN_KEY ="codetemplate::";
    public static String CREATE_PROLOG ="C_PRO";
    public static String CREATE_POSTLOG ="C_POST";
    public static String DROP_PROLOG ="D_PRO";
    public static String DROP_POSTLOG ="D_POST";

    public final static byte GENERATE_CREATE_PATTERN =2;
    public final static byte GENERATE_DROP_PATTERN=4;
    
    public final static String DATABASE_NAME_PROPERTY = "org.eclipse.datatools.connectivity.db.databaseName";
    
//bgp    public static Preferences instanceNode = new InstanceScope().getNode("com.ibm.datatools.core.ui"); //$NON-NLS-1$
    public static Preferences instanceNode = InstanceScope.INSTANCE.getNode("com.ibm.datatools.core.ui"); //$NON-NLS-1$
    
    static {
        initDdlGenerationPreferences();
    }
    
    public static EngineeringOptionCategory[] createDDLGenerationOptionCategories(String product, String version) {        

        Vector categoryVec = new Vector();
        categoryVec.add(new EngineeringOptionCategory(EngineeringOptionCategoryID.GENERATE_OPTIONS,DdlGenerationMessages.GENERATION_OPTIONS, DdlGenerationMessages.GENERATION_OPTIONS_DES)); 
        categoryVec.add(new EngineeringOptionCategory(EngineeringOptionCategoryID.GENERATE_ELEMENTS,DdlGenerationMessages.ADDITIONAL_ELEMENTS, DdlGenerationMessages.ADDITIONAL_ELEMENTS_DES));
//bgp        categoryVec.add(new EngineeringOptionCategory(EngineeringOptionCategoryID.XMLSCHEMA_CONTROLS, EngineeringOptionCategoryID.XMLSCHEMA_CONTROLS, ""));
    
        EngineeringOptionCategory[] categories = new EngineeringOptionCategory[categoryVec.size()];
        categoryVec.copyInto(categories);
        return categories;
    }
    
    public static Set getAllContainedDisplayableElementSetPlus(SQLObject[] elements) {
        Set s = new HashSet();
        for(int i=0; i<elements.length; ++i) {
            s.add(elements[i]);
            if (elements[i] instanceof AuthorizationIdentifier) 
                s.addAll(ContainmentServiceImpl.INSTANCE.getAllContainedElements(elements[i]));
            else s.addAll(ContainmentServiceImpl.INSTANCE.getAllContainedDisplayableElements(elements[i]));
        }
        return s;
    }
    
    public static Set getAllContainedDisplayableElementSet(SQLObject[] elements) {
        LinkedHashSet s = new LinkedHashSet();
        for(int i=0; i<elements.length; ++i) {
            s.add(elements[i]);
            s.addAll(ContainmentServiceImpl.INSTANCE.getAllContainedDisplayableElements(elements[i]));
        }
        return s;
    }
 
    public static void initDdlGenerationPreferences() {
        Preferences instanceNode = new InstanceScope().getNode("com.ibm.datatools.core.ui");//$NON-NLS-1$
        quoteIdentifiersDefault = instanceNode.getBoolean(QUOTE_IDENTIFIERS_DEFAULT, DEFAULT_QUOTE_IDENTIFIERS_DEFAULT_VALUE);
        qualifyNamesDefault = instanceNode.getBoolean(QUALIFY_NAMES_DEFAULT, DEFAULT_QUALIFY_NAMES_DEFAULT_VALUE);
        spufi_runsqlstm_format =instanceNode.getBoolean(SPUFI_RUNSQLSTM_FORMAT, SPUFI_RUNSQLSTM_FORMAT_VALUE);
    }
    
    public static boolean getQualifyNamesDefault() {
        return qualifyNamesDefault;
    }
    
    public static boolean getQuoteIdentifiersDefault() {
        return quoteIdentifiersDefault;
    }
    
    public static void setQualifyNamesDefault(boolean newValue) {
        qualifyNamesDefault = newValue;
    }
    
    public static void setQuoteIdentifiersDefault(boolean newValue) {
        quoteIdentifiersDefault = newValue;
    }

    public static boolean getSpufiRunsqlstmFormat() {
        return spufi_runsqlstm_format;
    }
    
    public static void setSpufiRunsqlstmFormat(boolean newValue) {
        spufi_runsqlstm_format = newValue;
    }
    

    //@bd00058820gs
    public static EngineeringOption getEngineeringOption(String id, String product, String version, EngineeringOptionCategory general_options, EngineeringOptionCategory additional_element)
    {

        try {
            if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_FULLY_QUALIFIED_NAME, DdlGenerationMessages.GENERATE_FULLY_QUALIFIED_NAME_DES,qualifyNamesDefault,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_QUOTED_IDENTIFIER, DdlGenerationMessages.GENERATE_QUOTED_IDENTIFIER_DES,quoteIdentifiersDefault,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_DROP_STATEMENTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_DROP_STATEMENTS, DdlGenerationMessages.GENERATE_DROP_STATEMENTS_DES,false,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CREATE_STATEMENTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_CREATE_STATEMENTS, DdlGenerationMessages.GENERATE_CREATE_STATEMENTS_DES,true,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PRIVILEGE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PRIVILEGE, DdlGenerationMessages.GENERATE_PRIVILEGE_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_COMMENTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_COMMENTS, DdlGenerationMessages.GENERATE_COMMENTS_DES,true,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_LABELS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_LABELS, DdlGenerationMessages.GENERATE_LABELS_DES,true,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_IN_TABLESPACE_CLAUSE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_IN_TABLESPACE_CLAUSE, DdlGenerationMessages.GENERATE_IN_TABLESPACE_CLAUSE_DES,true,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ON_FILEGROUP_CLAUSE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_ON_FILEGROUP_CLAUSE, DdlGenerationMessages.GENERATE_ON_FILEGROUP_CLAUSE_DES,true,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USE_DOMAIN_IF_EXIST))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_USE_DOMAIN_IF_EXIST, DdlGenerationMessages.GENERATE_USE_DOMAIN_IF_EXIST_DES,false,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CREATE_OR_REPLACE) && ModelHelper.getVersionAsFloat(version) >= 9.7f)
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_CREATE_OR_REPLACE, DdlGenerationMessages.GENERATE_CREATE_OR_REPLACE_DES,false,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TABLES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TABLES, DdlGenerationMessages.GENERATE_TABLES_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TABLESPACES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TABLESPACES, DdlGenerationMessages.GENERATE_TABLESPACES_DES,false,additional_element);
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FILEGROUPS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_FILEGROUPS, DdlGenerationMessages.GENERATE_FILEGROUPS_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_INDICES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_INDEX, DdlGenerationMessages.GENERATE_INDEX_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_STOREDPROCEDURES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_STOREDPROCEDURE, DdlGenerationMessages.GENERATE_STOREDPROCEDURE_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FUNCTIONS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_FUNCTION, DdlGenerationMessages.GENERATE_FUNCTION_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_VIEWS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_VIEW, DdlGenerationMessages.GENERATE_VIEW_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TRIGGERS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TRIGGER, DdlGenerationMessages.GENERATE_TRIGGER_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SEQUENCES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_SEQUENCE, DdlGenerationMessages.GENERATE_SEQUENCE_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USER_DEFINED_TYPE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_USER_DEFINED_TYPE, DdlGenerationMessages.GENERATE_USER_DEFINED_TYPE_DES,true,additional_element); 
    //      else if(o instanceof DistinctUserDefinedType)
    //      else if (o instanceof StructuredUserDefinedType)
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PK_CONSTRAINTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PK_CONSTRAINTS, DdlGenerationMessages.GENERATE_PK_CONSTRAINTS_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CK_CONSTRAINTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_CK_CONSTRAINTS, DdlGenerationMessages.GENERATE_CK_CONSTRAINTS_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FK_CONSTRAINTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_FK_CONSTRAINTS, DdlGenerationMessages.GENERATE_FK_CONSTRAINTS_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TABLESPACES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TABLESPACES, DdlGenerationMessages.GENERATE_TABLESPACES_DES,true,additional_element); 
    //      else if(id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SYNONYM)
    //        setOption(new EngineeringOption(id,Messages.getString("GENERATE_SYNONYM"), Messages.getString("GENERATE_SYNONYM_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
    //      }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_DATABASE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_DATABASE, DdlGenerationMessages.GENERATE_DATABASE_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_MQT))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_MQT, DdlGenerationMessages.GENERATE_MQT_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ALIAS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_ALIAS, DdlGenerationMessages.GENERATE_ALIAS_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SYNONYM))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_SYNONYM, DdlGenerationMessages.GENERATE_SYNONYM_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_BUFFERPOOL))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_BUFFERPOOL, DdlGenerationMessages.GENERATE_BUFFERPOOL_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_STORAGEGROUP))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_STORAGEGROUP, DdlGenerationMessages.GENERATE_STORAGEGROUP_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PARTITIONGROUP))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PARTITIONGROUP, DdlGenerationMessages.GENERATE_PARTITIONGROUP_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SCHEMAS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_SCHEMA, DdlGenerationMessages.GENERATE_SCHEMA_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_NICKNAME)) {
              ExtendDdlBuilder nicknameDdlBuilder = DdlGenerationUtility.getNicknameDdlBuilder(product, version);
              if (nicknameDdlBuilder != null) {
                return new EngineeringOption(null,nicknameDdlBuilder.getOption(),nicknameDdlBuilder.getOptionDescription(),true,additional_element);
              }
            }
            //fsp - pyl
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FEDERATEDPROCEDURES)) {
                ExtendDdlBuilder federatedProcedureDdlBuilder = DdlGenerationUtility.getFederatedProcedureDdlBuilder(product, version);
                if (federatedProcedureDdlBuilder != null) {
                  return new EngineeringOption(null,federatedProcedureDdlBuilder.getOption(),federatedProcedureDdlBuilder.getOptionDescription(),true,additional_element);
                }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_REMOTESERVER)) {
              ExtendDdlBuilder remoteServerDdlBuilder = DdlGenerationUtility.getRemoteServerDdlBuilder(product, version);
              if (remoteServerDdlBuilder != null) {
                return new EngineeringOption(null,remoteServerDdlBuilder.getOption(),remoteServerDdlBuilder.getOptionDescription(),true,additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_WRAPPER)) {
              ExtendDdlBuilder wrapperDdlBuilder = DdlGenerationUtility.getWrapperDdlBuilder(product, version);
              if (wrapperDdlBuilder != null) {
                return new EngineeringOption(null,wrapperDdlBuilder.getOption(),wrapperDdlBuilder.getOptionDescription(),true,additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USERMAPPING)) {
              ExtendDdlBuilder userMappingDdlBuilder = DdlGenerationUtility.getUserMappingDdlBuilder(product, version);
              if (userMappingDdlBuilder != null) {
                return new EngineeringOption(null,userMappingDdlBuilder.getOption(),userMappingDdlBuilder.getOptionDescription(),true,additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_OLAP)) {
              OlapDdlBuilder olapDdlBuilder = DdlGenerationUtility.getOlapDdlBuilder(product, version);
              if (olapDdlBuilder != null) {
                return new EngineeringOption(null,olapDdlBuilder.getOption(),olapDdlBuilder.getOptionDescription(),true,additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PACKAGE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PACKAGE, DdlGenerationMessages.GENERATE_PACKAGE_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PACKAGE_BODY)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PACKAGE_BODY, DdlGenerationMessages.GENERATE_PACKAGE_BODY_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_BACKUP_TABLE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_BACKUP_TABLE, DdlGenerationMessages.GENERATE_BACKUP_TABLE_DES,true,general_options); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_STATISTICS)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_STATISTICS, DdlGenerationMessages.GENERATE_STATISTICS_DES,false,general_options); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ROLE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_ROLE, DdlGenerationMessages.GENERATE_ROLE_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USER)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_USER, DdlGenerationMessages.GENERATE_USER_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_GROUP)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_GROUP, DdlGenerationMessages.GENERATE_GROUP_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.CHECK_MODEL)) {
                return new EngineeringOption(id,DdlGenerationMessages.CHECK_MODEL, DdlGenerationMessages.CHECK_MODEL_DES,false,general_options); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_MODULE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_MODULE, DdlGenerationMessages.GENERATE_MODULE_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_MODULE_CONDITION)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_MODULE_CONDITION, DdlGenerationMessages.GENERATE_MODULE_CONDITION_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_GLOBAL_VARIABLE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_GLOBAL_VARIABLE, DdlGenerationMessages.GENERATE_GLOBAL_VARIABLE_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_HISTORY_TABLE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_HISTORY_TABLE, DdlGenerationMessages.GENERATE_HISTORY_TABLE_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TEMPORARY_TABLES)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TEMPORARY_TABLE, DdlGenerationMessages.GENERATE_TEMPORARY_TABLE_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_XMLSCHEMA)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_XMLSCHEMA, DdlGenerationMessages.GENERATE_XMLSCHEMA_DES,false,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.XMLSCHEMA_CONNECTION_NAME)) {
                return new EngineeringOption(EngineeringOptionID.XMLSCHEMA_CONNECTION_NAME, EngineeringOptionID.XMLSCHEMA_CONNECTION_NAME, "");
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.XMLSCHEMA_DIRECTORY)) {
                return new EngineeringOption(EngineeringOptionID.XMLSCHEMA_DIRECTORY, EngineeringOptionID.XMLSCHEMA_DIRECTORY, "");
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SECURITY_POLICY)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_SECURITY_POLICY, DdlGenerationMessages.GENERATE_SECURITY_POLICY_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.DROP_RESTRICT)) {
                return new EngineeringOption(id,DdlGenerationMessages.DROP_RESTRICT, DdlGenerationMessages.DROP_RESTRICT_DES,false,general_options); 
        	}
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ROW_PERMISSIONS)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_ROW_PERMISSIONS, DdlGenerationMessages.GENERATE_ROW_PERMISSIONS_DES,true,additional_element); 
            } 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_COLUMN_MASKS)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_COLUMN_MASKS, DdlGenerationMessages.GENERATE_COLUMN_MASKS_DES,true,additional_element); 
            } 
        } catch (Exception e) {
            //The resource was not found
            e.printStackTrace();
        }

        return null;
    }
    
    public static EngineeringOption getDefaultEngineeringOption(String id, String product, String version, EngineeringOptionCategory general_options, EngineeringOptionCategory additional_element)
    {

        try {
            if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_FULLY_QUALIFIED_NAME, DdlGenerationMessages.GENERATE_FULLY_QUALIFIED_NAME_DES,qualifyNamesDefault,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_QUOTED_IDENTIFIER, DdlGenerationMessages.GENERATE_QUOTED_IDENTIFIER_DES,quoteIdentifiersDefault,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_DROP_STATEMENTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_DROP_STATEMENTS, DdlGenerationMessages.GENERATE_DROP_STATEMENTS_DES,false,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CREATE_STATEMENTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_CREATE_STATEMENTS, DdlGenerationMessages.GENERATE_CREATE_STATEMENTS_DES,true,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PRIVILEGE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PRIVILEGE, DdlGenerationMessages.GENERATE_PRIVILEGE_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_COMMENTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_COMMENTS, DdlGenerationMessages.GENERATE_COMMENTS_DES,true,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_LABELS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_LABELS, DdlGenerationMessages.GENERATE_LABELS_DES,true,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_IN_TABLESPACE_CLAUSE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_IN_TABLESPACE_CLAUSE, DdlGenerationMessages.GENERATE_IN_TABLESPACE_CLAUSE_DES,true,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ON_FILEGROUP_CLAUSE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_ON_FILEGROUP_CLAUSE, DdlGenerationMessages.GENERATE_ON_FILEGROUP_CLAUSE_DES,true,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USE_DOMAIN_IF_EXIST))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_USE_DOMAIN_IF_EXIST, DdlGenerationMessages.GENERATE_USE_DOMAIN_IF_EXIST_DES,false,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CREATE_OR_REPLACE) && ModelHelper.getVersionAsFloat(version) >= 9.7f)
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_CREATE_OR_REPLACE, DdlGenerationMessages.GENERATE_CREATE_OR_REPLACE_DES,false,general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TABLES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TABLES, DdlGenerationMessages.GENERATE_TABLES_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TABLESPACES)){
            	if(product.equals("Oracle"))
            		return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TABLESPACES, DdlGenerationMessages.GENERATE_TABLESPACES_DES,true,additional_element);
            	else
            		return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TABLESPACES, DdlGenerationMessages.GENERATE_TABLESPACES_DES,false,additional_element);
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FILEGROUPS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_FILEGROUPS, DdlGenerationMessages.GENERATE_FILEGROUPS_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_INDICES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_INDEX, DdlGenerationMessages.GENERATE_INDEX_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_STOREDPROCEDURES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_STOREDPROCEDURE, DdlGenerationMessages.GENERATE_STOREDPROCEDURE_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FUNCTIONS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_FUNCTION, DdlGenerationMessages.GENERATE_FUNCTION_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_VIEWS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_VIEW, DdlGenerationMessages.GENERATE_VIEW_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TRIGGERS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TRIGGER, DdlGenerationMessages.GENERATE_TRIGGER_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SEQUENCES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_SEQUENCE, DdlGenerationMessages.GENERATE_SEQUENCE_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USER_DEFINED_TYPE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_USER_DEFINED_TYPE, DdlGenerationMessages.GENERATE_USER_DEFINED_TYPE_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_DOMAIN))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_DOMAIN, DdlGenerationMessages.GENERATE_DOMAIN_TYPE_DES,true,additional_element); 
    //      else if(o instanceof DistinctUserDefinedType)
    //      else if (o instanceof StructuredUserDefinedType)
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PK_CONSTRAINTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PK_CONSTRAINTS, DdlGenerationMessages.GENERATE_PK_CONSTRAINTS_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CK_CONSTRAINTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_CK_CONSTRAINTS, DdlGenerationMessages.GENERATE_CK_CONSTRAINTS_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FK_CONSTRAINTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_FK_CONSTRAINTS, DdlGenerationMessages.GENERATE_FK_CONSTRAINTS_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TABLESPACES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TABLESPACES, DdlGenerationMessages.GENERATE_TABLESPACES_DES,true,additional_element); 
    //      else if(id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SYNONYM)
    //        setOption(new EngineeringOption(id,Messages.getString("GENERATE_SYNONYM"), Messages.getString("GENERATE_SYNONYM_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
    //      }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_DATABASE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_DATABASE, DdlGenerationMessages.GENERATE_DATABASE_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_MQT))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_MQT, DdlGenerationMessages.GENERATE_MQT_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ALIAS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_ALIAS, DdlGenerationMessages.GENERATE_ALIAS_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SYNONYM))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_SYNONYM, DdlGenerationMessages.GENERATE_SYNONYM_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_BUFFERPOOL))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_BUFFERPOOL, DdlGenerationMessages.GENERATE_BUFFERPOOL_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_STORAGEGROUP))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_STORAGEGROUP, DdlGenerationMessages.GENERATE_STORAGEGROUP_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PARTITIONGROUP))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PARTITIONGROUP, DdlGenerationMessages.GENERATE_PARTITIONGROUP_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SCHEMAS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_SCHEMA, DdlGenerationMessages.GENERATE_SCHEMA_DES,true,additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_NICKNAME)) {
              ExtendDdlBuilder nicknameDdlBuilder = DdlGenerationUtility.getNicknameDdlBuilder(product, version);
              if (nicknameDdlBuilder != null) {
                return new EngineeringOption(id,nicknameDdlBuilder.getOption(),nicknameDdlBuilder.getOptionDescription(),true,additional_element);
              }
            }
            //fsp - pyl
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FEDERATEDPROCEDURES)) {
                ExtendDdlBuilder federatedProcedureDdlBuilder = DdlGenerationUtility.getFederatedProcedureDdlBuilder(product, version);
                if (federatedProcedureDdlBuilder != null) {
                  return new EngineeringOption(id,federatedProcedureDdlBuilder.getOption(),federatedProcedureDdlBuilder.getOptionDescription(),true,additional_element);
                }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_REMOTESERVER)) {
              ExtendDdlBuilder remoteServerDdlBuilder = DdlGenerationUtility.getRemoteServerDdlBuilder(product, version);
              if (remoteServerDdlBuilder != null) {
                return new EngineeringOption(id,remoteServerDdlBuilder.getOption(),remoteServerDdlBuilder.getOptionDescription(),true,additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_WRAPPER)) {
              ExtendDdlBuilder wrapperDdlBuilder = DdlGenerationUtility.getWrapperDdlBuilder(product, version);
              if (wrapperDdlBuilder != null) {
                return new EngineeringOption(id,wrapperDdlBuilder.getOption(),wrapperDdlBuilder.getOptionDescription(),true,additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USERMAPPING)) {
              ExtendDdlBuilder userMappingDdlBuilder = DdlGenerationUtility.getUserMappingDdlBuilder(product, version);
              if (userMappingDdlBuilder != null) {
                return new EngineeringOption(id,userMappingDdlBuilder.getOption(),userMappingDdlBuilder.getOptionDescription(),true,additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_OLAP)) {
              OlapDdlBuilder olapDdlBuilder = DdlGenerationUtility.getOlapDdlBuilder(product, version);
              if (olapDdlBuilder != null) {
                return new EngineeringOption(id,olapDdlBuilder.getOption(),olapDdlBuilder.getOptionDescription(),true,additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PACKAGE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PACKAGE, DdlGenerationMessages.GENERATE_PACKAGE_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PACKAGE_BODY)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PACKAGE_BODY, DdlGenerationMessages.GENERATE_PACKAGE_BODY_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_BACKUP_TABLE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_BACKUP_TABLE, DdlGenerationMessages.GENERATE_BACKUP_TABLE_DES,true,general_options); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_STATISTICS)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_STATISTICS, DdlGenerationMessages.GENERATE_STATISTICS_DES,false,general_options); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ROLE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_ROLE, DdlGenerationMessages.GENERATE_ROLE_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USER)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_USER, DdlGenerationMessages.GENERATE_USER_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_GROUP)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_GROUP, DdlGenerationMessages.GENERATE_GROUP_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.CHECK_MODEL)) {
                return new EngineeringOption(id,DdlGenerationMessages.CHECK_MODEL, DdlGenerationMessages.CHECK_MODEL_DES,false,general_options); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_MODULE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_MODULE, DdlGenerationMessages.GENERATE_MODULE_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_MODULE_CONDITION)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_MODULE_CONDITION, DdlGenerationMessages.GENERATE_MODULE_CONDITION_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_GLOBAL_VARIABLE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_GLOBAL_VARIABLE, DdlGenerationMessages.GENERATE_GLOBAL_VARIABLE_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_HISTORY_TABLE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_HISTORY_TABLE, DdlGenerationMessages.GENERATE_HISTORY_TABLE_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TEMPORARY_TABLES)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TEMPORARY_TABLE, DdlGenerationMessages.GENERATE_TEMPORARY_TABLE_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_XMLSCHEMA)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_XMLSCHEMA, DdlGenerationMessages.GENERATE_XMLSCHEMA_DES,false,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.XMLSCHEMA_CONNECTION_NAME)) {
                return new EngineeringOption(EngineeringOptionID.XMLSCHEMA_CONNECTION_NAME, EngineeringOptionID.XMLSCHEMA_CONNECTION_NAME, "");
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.XMLSCHEMA_DIRECTORY)) {
                return new EngineeringOption(EngineeringOptionID.XMLSCHEMA_DIRECTORY, EngineeringOptionID.XMLSCHEMA_DIRECTORY, "");
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SECURITY_POLICY)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_SECURITY_POLICY, DdlGenerationMessages.GENERATE_SECURITY_POLICY_DES,true,additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.DROP_RESTRICT)) {
                return new EngineeringOption(id,DdlGenerationMessages.DROP_RESTRICT, DdlGenerationMessages.DROP_RESTRICT_DES,false,general_options); 
        	}
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ROW_PERMISSIONS)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_ROW_PERMISSIONS, DdlGenerationMessages.GENERATE_ROW_PERMISSIONS_DES,true,additional_element); 
            } 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_COLUMN_MASKS)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_COLUMN_MASKS, DdlGenerationMessages.GENERATE_COLUMN_MASKS_DES,true,additional_element); 
            } 
        } catch (Exception e) {
            //The resource was not found
            e.printStackTrace();
        }

        return null;
    }
    
    public static EngineeringOption getEngineeringOption(String id, String product, String version, EngineeringOptionCategory general_options, EngineeringOptionCategory additional_element, SQLObject[] elements)
    {

        try {
            if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_FULLY_QUALIFIED_NAME, DdlGenerationMessages.GENERATE_FULLY_QUALIFIED_NAME_DES,getOptionPreference(id,product,version,elements,qualifyNamesDefault),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_QUOTED_IDENTIFIER, DdlGenerationMessages.GENERATE_QUOTED_IDENTIFIER_DES,getOptionPreference(id,product,version,elements,quoteIdentifiersDefault),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_DROP_STATEMENTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_DROP_STATEMENTS, DdlGenerationMessages.GENERATE_DROP_STATEMENTS_DES,getOptionPreference(id,product,version,elements,false),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CREATE_STATEMENTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_CREATE_STATEMENTS, DdlGenerationMessages.GENERATE_CREATE_STATEMENTS_DES,getOptionPreference(id,product,version,elements,true),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PRIVILEGE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PRIVILEGE, DdlGenerationMessages.GENERATE_PRIVILEGE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_COMMENTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_COMMENTS, DdlGenerationMessages.GENERATE_COMMENTS_DES,getOptionPreference(id,product,version,elements,true),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_LABELS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_LABELS, DdlGenerationMessages.GENERATE_LABELS_DES,getOptionPreference(id,product,version,elements,true),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_IN_TABLESPACE_CLAUSE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_IN_TABLESPACE_CLAUSE, DdlGenerationMessages.GENERATE_IN_TABLESPACE_CLAUSE_DES,getOptionPreference(id,product,version,elements,true),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ON_FILEGROUP_CLAUSE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_ON_FILEGROUP_CLAUSE, DdlGenerationMessages.GENERATE_ON_FILEGROUP_CLAUSE_DES,getOptionPreference(id,product,version,elements,true),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USE_DOMAIN_IF_EXIST))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_USE_DOMAIN_IF_EXIST, DdlGenerationMessages.GENERATE_USE_DOMAIN_IF_EXIST_DES,getOptionPreference(id,product,version,elements,false),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CREATE_OR_REPLACE) && ModelHelper.getVersionAsFloat(version) >= 9.7f)
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_CREATE_OR_REPLACE, DdlGenerationMessages.GENERATE_CREATE_OR_REPLACE_DES,getOptionPreference(id,product,version,elements,false),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TABLES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TABLES, DdlGenerationMessages.GENERATE_TABLES_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TABLESPACES)){
            	if(product.equals("Oracle"))
            		return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TABLESPACES, DdlGenerationMessages.GENERATE_TABLESPACES_DES,getOptionPreference(id,product,version,elements,true),additional_element);
            	else
            		return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TABLESPACES, DdlGenerationMessages.GENERATE_TABLESPACES_DES,getOptionPreference(id,product,version,elements,false),additional_element);
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FILEGROUPS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_FILEGROUPS, DdlGenerationMessages.GENERATE_FILEGROUPS_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_INDICES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_INDEX, DdlGenerationMessages.GENERATE_INDEX_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_STOREDPROCEDURES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_STOREDPROCEDURE, DdlGenerationMessages.GENERATE_STOREDPROCEDURE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FUNCTIONS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_FUNCTION, DdlGenerationMessages.GENERATE_FUNCTION_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_VIEWS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_VIEW, DdlGenerationMessages.GENERATE_VIEW_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TRIGGERS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TRIGGER, DdlGenerationMessages.GENERATE_TRIGGER_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SEQUENCES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_SEQUENCE, DdlGenerationMessages.GENERATE_SEQUENCE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USER_DEFINED_TYPE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_USER_DEFINED_TYPE, DdlGenerationMessages.GENERATE_USER_DEFINED_TYPE_DES,getOptionPreference(id,product,version,elements,true),additional_element);
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_DOMAIN))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_DOMAIN, DdlGenerationMessages.GENERATE_DOMAIN_TYPE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
    //      else if(o instanceof DistinctUserDefinedType)
    //      else if (o instanceof StructuredUserDefinedType)
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PK_CONSTRAINTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PK_CONSTRAINTS, DdlGenerationMessages.GENERATE_PK_CONSTRAINTS_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CK_CONSTRAINTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_CK_CONSTRAINTS, DdlGenerationMessages.GENERATE_CK_CONSTRAINTS_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FK_CONSTRAINTS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_FK_CONSTRAINTS, DdlGenerationMessages.GENERATE_FK_CONSTRAINTS_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TABLESPACES))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TABLESPACES, DdlGenerationMessages.GENERATE_TABLESPACES_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
    //      else if(id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SYNONYM)
    //        setOption(new EngineeringOption(id,Messages.getString("GENERATE_SYNONYM"), Messages.getString("GENERATE_SYNONYM_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
    //      }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_DATABASE))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_DATABASE, DdlGenerationMessages.GENERATE_DATABASE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_MQT))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_MQT, DdlGenerationMessages.GENERATE_MQT_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ALIAS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_ALIAS, DdlGenerationMessages.GENERATE_ALIAS_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SYNONYM))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_SYNONYM, DdlGenerationMessages.GENERATE_SYNONYM_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_BUFFERPOOL))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_BUFFERPOOL, DdlGenerationMessages.GENERATE_BUFFERPOOL_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_STORAGEGROUP))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_STORAGEGROUP, DdlGenerationMessages.GENERATE_STORAGEGROUP_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PARTITIONGROUP))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PARTITIONGROUP, DdlGenerationMessages.GENERATE_PARTITIONGROUP_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SCHEMAS))
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_SCHEMA, DdlGenerationMessages.GENERATE_SCHEMA_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_NICKNAME)) {
              ExtendDdlBuilder nicknameDdlBuilder = DdlGenerationUtility.getNicknameDdlBuilder(product, version);
              if (nicknameDdlBuilder != null) {
                return new EngineeringOption(id,nicknameDdlBuilder.getOption(),nicknameDdlBuilder.getOptionDescription(),getOptionPreference(id,product,version,elements,true),additional_element);
              }
            }
            //fsp - pyl
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FEDERATEDPROCEDURES)) {
                ExtendDdlBuilder federatedProcedureDdlBuilder = DdlGenerationUtility.getFederatedProcedureDdlBuilder(product, version);
                if (federatedProcedureDdlBuilder != null) {
                  return new EngineeringOption(id,federatedProcedureDdlBuilder.getOption(),federatedProcedureDdlBuilder.getOptionDescription(),getOptionPreference(id,product,version,elements,true),additional_element);
                }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_REMOTESERVER)) {
              ExtendDdlBuilder remoteServerDdlBuilder = DdlGenerationUtility.getRemoteServerDdlBuilder(product, version);
              if (remoteServerDdlBuilder != null) {
                return new EngineeringOption(id,remoteServerDdlBuilder.getOption(),remoteServerDdlBuilder.getOptionDescription(),getOptionPreference(id,product,version,elements,true),additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_WRAPPER)) {
              ExtendDdlBuilder wrapperDdlBuilder = DdlGenerationUtility.getWrapperDdlBuilder(product, version);
              if (wrapperDdlBuilder != null) {
                return new EngineeringOption(id,wrapperDdlBuilder.getOption(),wrapperDdlBuilder.getOptionDescription(),getOptionPreference(id,product,version,elements,true),additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USERMAPPING)) {
              ExtendDdlBuilder userMappingDdlBuilder = DdlGenerationUtility.getUserMappingDdlBuilder(product, version);
              if (userMappingDdlBuilder != null) {
                return new EngineeringOption(id,userMappingDdlBuilder.getOption(),userMappingDdlBuilder.getOptionDescription(),getOptionPreference(id,product,version,elements,true),additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_OLAP)) {
              OlapDdlBuilder olapDdlBuilder = DdlGenerationUtility.getOlapDdlBuilder(product, version);
              if (olapDdlBuilder != null) {
                return new EngineeringOption(id,olapDdlBuilder.getOption(),olapDdlBuilder.getOptionDescription(),getOptionPreference(id,product,version,elements,true),additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PACKAGE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PACKAGE, DdlGenerationMessages.GENERATE_PACKAGE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PACKAGE_BODY)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_PACKAGE_BODY, DdlGenerationMessages.GENERATE_PACKAGE_BODY_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_BACKUP_TABLE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_BACKUP_TABLE, DdlGenerationMessages.GENERATE_BACKUP_TABLE_DES,getOptionPreference(id,product,version,elements,true),general_options); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_STATISTICS)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_STATISTICS, DdlGenerationMessages.GENERATE_STATISTICS_DES,getOptionPreference(id,product,version,elements,false),general_options); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ROLE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_ROLE, DdlGenerationMessages.GENERATE_ROLE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USER)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_USER, DdlGenerationMessages.GENERATE_USER_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_GROUP)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_GROUP, DdlGenerationMessages.GENERATE_GROUP_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.CHECK_MODEL)) {
                return new EngineeringOption(id,DdlGenerationMessages.CHECK_MODEL, DdlGenerationMessages.CHECK_MODEL_DES,getOptionPreference(id,product,version,elements,false),general_options); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_MODULE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_MODULE, DdlGenerationMessages.GENERATE_MODULE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_MODULE_CONDITION)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_MODULE_CONDITION, DdlGenerationMessages.GENERATE_MODULE_CONDITION_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_GLOBAL_VARIABLE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_GLOBAL_VARIABLE, DdlGenerationMessages.GENERATE_GLOBAL_VARIABLE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_HISTORY_TABLE)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_HISTORY_TABLE, DdlGenerationMessages.GENERATE_HISTORY_TABLE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TEMPORARY_TABLES)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_TEMPORARY_TABLE, DdlGenerationMessages.GENERATE_TEMPORARY_TABLE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_XMLSCHEMA)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_XMLSCHEMA, DdlGenerationMessages.GENERATE_XMLSCHEMA_DES,getOptionPreference(id,product,version,elements,false),additional_element); 
            }
//<bgp            else if (id.equalsIgnoreCase(EngineeringOptionID.XMLSCHEMA_CONNECTION_NAME)) {
//                return new EngineeringOption(id,EngineeringOptionID.XMLSCHEMA_CONNECTION_NAME, EngineeringOptionID.XMLSCHEMA_CONNECTION_NAME, getOptionPreference(id,product,version,elements,""));
//            }
//            else if (id.equalsIgnoreCase(EngineeringOptionID.XMLSCHEMA_DIRECTORY)) {
//                return new EngineeringOption(id,EngineeringOptionID.XMLSCHEMA_DIRECTORY, EngineeringOptionID.XMLSCHEMA_DIRECTORY, getOptionPreference(id,product,version,elements,""));
//bgp>            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SECURITY_POLICY)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_SECURITY_POLICY, DdlGenerationMessages.GENERATE_SECURITY_POLICY_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.DROP_RESTRICT)) {
                return new EngineeringOption(id,DdlGenerationMessages.DROP_RESTRICT, DdlGenerationMessages.DROP_RESTRICT_DES,getOptionPreference(id,product,version,elements,false),general_options); 
        	}
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ROW_PERMISSIONS)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_ROW_PERMISSIONS, DdlGenerationMessages.GENERATE_ROW_PERMISSIONS_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            } 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_COLUMN_MASKS)) {
                return new EngineeringOption(id,DdlGenerationMessages.GENERATE_COLUMN_MASKS, DdlGenerationMessages.GENERATE_COLUMN_MASKS_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            } 
        } catch (Exception e) {
            //The resource was not found
            e.printStackTrace();
        }

        return null;
    }
    
    public static EngineeringOption getEngineeringOption(String id, String product, String version, EngineeringOptionCategory general_options, EngineeringOptionCategory additional_element, SQLObject[] elements, String descriptorName)
    {

        try {
            if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_FULLY_QUALIFIED_NAME_DES,getOptionPreference(id,product,version,elements,qualifyNamesDefault),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_QUOTED_IDENTIFIER_DES,getOptionPreference(id,product,version,elements,quoteIdentifiersDefault),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_DROP_STATEMENTS))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_DROP_STATEMENTS_DES,getOptionPreference(id,product,version,elements,false),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CREATE_STATEMENTS))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_CREATE_STATEMENTS_DES,getOptionPreference(id,product,version,elements,true),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PRIVILEGE))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_PRIVILEGE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_COMMENTS))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_COMMENTS_DES,getOptionPreference(id,product,version,elements,true),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_LABELS))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_LABELS_DES,getOptionPreference(id,product,version,elements,true),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_IN_TABLESPACE_CLAUSE))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_IN_TABLESPACE_CLAUSE_DES,getOptionPreference(id,product,version,elements,true),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ON_FILEGROUP_CLAUSE))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_ON_FILEGROUP_CLAUSE_DES,getOptionPreference(id,product,version,elements,true),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USE_DOMAIN_IF_EXIST))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_USE_DOMAIN_IF_EXIST_DES,getOptionPreference(id,product,version,elements,false),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CREATE_OR_REPLACE) && ModelHelper.getVersionAsFloat(version) >= 9.7f)
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_CREATE_OR_REPLACE_DES,getOptionPreference(id,product,version,elements,false),general_options); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TABLES))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_TABLES_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TABLESPACES)){
            	if(product.equals("Oracle"))
            		return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_TABLESPACES_DES,getOptionPreference(id,product,version,elements,true),additional_element);
            	else
            		return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_TABLESPACES_DES,getOptionPreference(id,product,version,elements,false),additional_element);
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FILEGROUPS))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_FILEGROUPS_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_INDICES))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_INDEX_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_STOREDPROCEDURES))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_STOREDPROCEDURE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FUNCTIONS))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_FUNCTION_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_VIEWS))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_VIEW_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TRIGGERS))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_TRIGGER_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SEQUENCES))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_SEQUENCE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USER_DEFINED_TYPE))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_USER_DEFINED_TYPE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
    //      else if(o instanceof DistinctUserDefinedType)
    //      else if (o instanceof StructuredUserDefinedType)
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PK_CONSTRAINTS))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_PK_CONSTRAINTS_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CK_CONSTRAINTS))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_CK_CONSTRAINTS_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FK_CONSTRAINTS))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_FK_CONSTRAINTS_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TABLESPACES))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_TABLESPACES_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
    //      else if(id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SYNONYM)
    //        setOption(new EngineeringOption(id,Messages.getString("GENERATE_SYNONYM"), Messages.getString("GENERATE_SYNONYM_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
    //      }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_DATABASE))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_DATABASE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_MQT))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_MQT_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ALIAS))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_ALIAS_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SYNONYM))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_SYNONYM_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_BUFFERPOOL))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_BUFFERPOOL_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_STORAGEGROUP))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_STORAGEGROUP_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PARTITIONGROUP))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_PARTITIONGROUP_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SCHEMAS))
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_SCHEMA_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_NICKNAME)) {
              ExtendDdlBuilder nicknameDdlBuilder = DdlGenerationUtility.getNicknameDdlBuilder(product, version);
              if (nicknameDdlBuilder != null) {
                return new EngineeringOption(id,nicknameDdlBuilder.getOption(),nicknameDdlBuilder.getOptionDescription(),getOptionPreference(id,product,version,elements,true),additional_element);
              }
            }
            //fsp - pyl
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FEDERATEDPROCEDURES)) {
                ExtendDdlBuilder federatedProcedureDdlBuilder = DdlGenerationUtility.getFederatedProcedureDdlBuilder(product, version);
                if (federatedProcedureDdlBuilder != null) {
                  return new EngineeringOption(id,federatedProcedureDdlBuilder.getOption(),federatedProcedureDdlBuilder.getOptionDescription(),getOptionPreference(id,product,version,elements,true),additional_element);
                }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_REMOTESERVER)) {
              ExtendDdlBuilder remoteServerDdlBuilder = DdlGenerationUtility.getRemoteServerDdlBuilder(product, version);
              if (remoteServerDdlBuilder != null) {
                return new EngineeringOption(id,remoteServerDdlBuilder.getOption(),remoteServerDdlBuilder.getOptionDescription(),getOptionPreference(id,product,version,elements,true),additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_WRAPPER)) {
              ExtendDdlBuilder wrapperDdlBuilder = DdlGenerationUtility.getWrapperDdlBuilder(product, version);
              if (wrapperDdlBuilder != null) {
                return new EngineeringOption(id,wrapperDdlBuilder.getOption(),wrapperDdlBuilder.getOptionDescription(),getOptionPreference(id,product,version,elements,true),additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USERMAPPING)) {
              ExtendDdlBuilder userMappingDdlBuilder = DdlGenerationUtility.getUserMappingDdlBuilder(product, version);
              if (userMappingDdlBuilder != null) {
                return new EngineeringOption(id,userMappingDdlBuilder.getOption(),userMappingDdlBuilder.getOptionDescription(),getOptionPreference(id,product,version,elements,true),additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_OLAP)) {
              OlapDdlBuilder olapDdlBuilder = DdlGenerationUtility.getOlapDdlBuilder(product, version);
              if (olapDdlBuilder != null) {
                return new EngineeringOption(id,olapDdlBuilder.getOption(),olapDdlBuilder.getOptionDescription(),getOptionPreference(id,product,version,elements,true),additional_element);
              }
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PACKAGE)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_PACKAGE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PACKAGE_BODY)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_PACKAGE_BODY_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_BACKUP_TABLE)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_BACKUP_TABLE_DES,getOptionPreference(id,product,version,elements,true),general_options); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_STATISTICS)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_STATISTICS_DES,getOptionPreference(id,product,version,elements,false),general_options); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ROLE)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_ROLE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USER)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_USER_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_GROUP)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_GROUP_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.CHECK_MODEL)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.CHECK_MODEL_DES,getOptionPreference(id,product,version,elements,false),general_options); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_MODULE)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_MODULE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_MODULE_CONDITION)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_MODULE_CONDITION_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_GLOBAL_VARIABLE)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_GLOBAL_VARIABLE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_HISTORY_TABLE)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_HISTORY_TABLE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TEMPORARY_TABLES)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_TEMPORARY_TABLE_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_XMLSCHEMA)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_XMLSCHEMA_DES,getOptionPreference(id,product,version,elements,false),additional_element); 
            }
//<bgp            else if (id.equalsIgnoreCase(EngineeringOptionID.XMLSCHEMA_CONNECTION_NAME)) {
//                return new EngineeringOption(id,EngineeringOptionID.XMLSCHEMA_CONNECTION_NAME, EngineeringOptionID.XMLSCHEMA_CONNECTION_NAME, getOptionPreference(id,product,version,elements,""));
//            }
//            else if (id.equalsIgnoreCase(EngineeringOptionID.XMLSCHEMA_DIRECTORY)) {
//                return new EngineeringOption(id,EngineeringOptionID.XMLSCHEMA_DIRECTORY, EngineeringOptionID.XMLSCHEMA_DIRECTORY, getOptionPreference(id,product,version,elements,""));
//bgp>            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SECURITY_POLICY)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_SECURITY_POLICY_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            }
            else if (id.equalsIgnoreCase(EngineeringOptionID.DROP_RESTRICT)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.DROP_RESTRICT_DES,getOptionPreference(id,product,version,elements,false),general_options); 
        	}
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ROW_PERMISSIONS)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_ROW_PERMISSIONS_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            } 
            else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_COLUMN_MASKS)) {
                return new EngineeringOption(id,descriptorName, DdlGenerationMessages.GENERATE_COLUMN_MASKS_DES,getOptionPreference(id,product,version,elements,true),additional_element); 
            } 
        } catch (Exception e) {
            //The resource was not found
            e.printStackTrace();
        }

        return null;
    }
    //@ed00058820gs    
    
    public static boolean getOptionPreference(String id, String product, String version, SQLObject[] elements, boolean defaultValue){
    	return Boolean.parseBoolean(instanceNode.get(PreferenceConstants.PREF_GENERATE_DDL_OPTION + product + version + elements[0].eClass().getName() + id, String.valueOf(defaultValue)));
    }
    
    public static String getOptionPreference(String id, String product, String version, SQLObject[] elements, String defaultValue){
    	return instanceNode.get(PreferenceConstants.PREF_GENERATE_DDL_OPTION + product + version + elements[0].eClass().getName() + id, defaultValue);
    }
    
    public static OlapDdlBuilder getOlapDdlBuilder(String dbProduct, String dbVersion) {
        if (olapDdlBuilder != null) return olapDdlBuilder;
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.core", "olapDdlBuilder"); //$NON-NLS-1$ //$NON-NLS-2$
        if (extensionPoint != null) {
            IExtension[] extensions = extensionPoint.getExtensions();
            for(int i=0; i<extensions.length; ++i) {
                IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
                for(int j=0; j<configElements.length; ++j) {
                    if(configElements[j].getName().equals("builder")) { //$NON-NLS-1$
                        String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
                        if(!product.equals(dbProduct)) continue;
                        try {
                            olapDdlBuilder = (OlapDdlBuilder) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
                        }
                        catch(CoreException e) {
                            IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getDefault().getBundle().getSymbolicName(), IStatus.ERROR,
                                    "The error was detected when creating the olap ddl builder for " + product, e); //$NON-NLS-1$
                            IBMPluginActivator.getInstance().getLog().log(status);
                        }
                        break;
                    }
                }
            }
        }
        return olapDdlBuilder;
    }
    
    public static ExtendDdlBuilder getNicknameDdlBuilder(String dbProduct, String dbVersion) {
        if (nicknameDdlBuilder != null) return nicknameDdlBuilder;
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.core", "nicknameDdlBuilder"); //$NON-NLS-1$ //$NON-NLS-2$
        if (extensionPoint != null) {
            IExtension[] extensions = extensionPoint.getExtensions();
            for(int i=0; i<extensions.length; ++i) {
                IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
                for(int j=0; j<configElements.length; ++j) {
                    if(configElements[j].getName().equals("builder")) { //$NON-NLS-1$
                        String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
                        if(!product.equals(dbProduct)) continue;
                        try {
                            nicknameDdlBuilder = (ExtendDdlBuilder) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
                        }
                        catch(CoreException e) {
                            IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getDefault().getBundle().getSymbolicName(), IStatus.ERROR,
                                    "The error was detected when creating the nickname ddl builder for " + product, e); //$NON-NLS-1$
                            IBMPluginActivator.getInstance().getLog().log(status);
                        }
                        break;
                    }
                }
            }
        }
        return nicknameDdlBuilder;
    }
    
    // fsp - pyl
    public static ExtendDdlBuilder getFederatedProcedureDdlBuilder(String dbProduct, String dbVersion) {
        if (federatedProcedureDdlBuilder != null) return federatedProcedureDdlBuilder;
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.core", "federatedProcedureDdlBuilder"); //$NON-NLS-1$ //$NON-NLS-2$
        if (extensionPoint != null) {
            IExtension[] extensions = extensionPoint.getExtensions();
            for(int i=0; i<extensions.length; ++i) {
                IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
                for(int j=0; j<configElements.length; ++j) {
                    if(configElements[j].getName().equals("builder")) { //$NON-NLS-1$
                        String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
                        if(!product.equals(dbProduct)) continue;
                        try {
                            federatedProcedureDdlBuilder = (ExtendDdlBuilder) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
                        }
                        catch(CoreException e) {
                            IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getDefault().getBundle().getSymbolicName(), IStatus.ERROR,
                                    "The error was detected when creating the federated stored procedure ddl builder for " + product, e); //$NON-NLS-1$
                            IBMPluginActivator.getInstance().getLog().log(status);
                        }
                        break;
                    }
                }
            }
        }
        return federatedProcedureDdlBuilder;
    }
    
    public static ExtendDdlBuilder getRemoteServerDdlBuilder(String dbProduct, String dbVersion) {
        if (remoteServerDdlBuilder != null) return remoteServerDdlBuilder;
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.core", "remoteServerDdlBuilder"); //$NON-NLS-1$ //$NON-NLS-2$
        if (extensionPoint != null) {
            IExtension[] extensions = extensionPoint.getExtensions();
            for(int i=0; i<extensions.length; ++i) {
                IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
                for(int j=0; j<configElements.length; ++j) {
                    if(configElements[j].getName().equals("builder")) { //$NON-NLS-1$
                        String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
                        if(!product.equals(dbProduct)) continue;
                        try {
                            remoteServerDdlBuilder = (ExtendDdlBuilder) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
                        }
                        catch(CoreException e) {
                            IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getDefault().getBundle().getSymbolicName(), IStatus.ERROR,
                                    "The error was detected when creating the remote Server ddl builder for " + product, e); //$NON-NLS-1$
                            IBMPluginActivator.getInstance().getLog().log(status);
                        }
                        break;
                    }
                }
            }
        }
        return remoteServerDdlBuilder;
    }

    public static ExtendDdlBuilder getWrapperDdlBuilder(String dbProduct, String dbVersion) {
        if (wrapperDdlBuilder != null) return wrapperDdlBuilder;
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.core", "wrapperDdlBuilder"); //$NON-NLS-1$ //$NON-NLS-2$
        if (extensionPoint != null) {
            IExtension[] extensions = extensionPoint.getExtensions();
            for(int i=0; i<extensions.length; ++i) {
                IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
                for(int j=0; j<configElements.length; ++j) {
                    if(configElements[j].getName().equals("builder")) { //$NON-NLS-1$
                        String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
                        if(!product.equals(dbProduct)) continue;
                        try {
                            wrapperDdlBuilder = (ExtendDdlBuilder) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
                        }
                        catch(CoreException e) {
                            IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getDefault().getBundle().getSymbolicName(), IStatus.ERROR,
                                    "The error was detected when creating the Wrapper ddl builder for " + product, e); //$NON-NLS-1$
                            IBMPluginActivator.getInstance().getLog().log(status);
                        }
                        break;
                    }
                }
            }
        }
        return wrapperDdlBuilder;
    }
    
    public static ExtendDdlBuilder getUserMappingDdlBuilder(String dbProduct, String dbVersion) {
        if (userMappingDdlBuilder != null) return userMappingDdlBuilder;
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.core", "userMappingDdlBuilder"); //$NON-NLS-1$ //$NON-NLS-2$
        if (extensionPoint != null) {
            IExtension[] extensions = extensionPoint.getExtensions();
            for(int i=0; i<extensions.length; ++i) {
                IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
                for(int j=0; j<configElements.length; ++j) {
                    if(configElements[j].getName().equals("builder")) { //$NON-NLS-1$
                        String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
                        if(!product.equals(dbProduct)) continue;
                        try {
                            userMappingDdlBuilder = (ExtendDdlBuilder) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
                        }
                        catch(CoreException e) {
                            IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getDefault().getBundle().getSymbolicName(), IStatus.ERROR,
                                    "The error was detected when creating the User Mapping ddl builder for " + product, e); //$NON-NLS-1$
                            IBMPluginActivator.getInstance().getLog().log(status);
                        }
                        break;
                    }
                }
            }
        }
        return userMappingDdlBuilder;
    }
    
    public static TypedElementLogicalDomainProvider getElementDomainProvider(){
        if (elementDomainProvider != null) return elementDomainProvider;
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.core", "typedElementLogicalDomainProvider"); //$NON-NLS-1$ //$NON-NLS-2$
        if (extensionPoint != null) {
            IExtension[] extensions = extensionPoint.getExtensions();
            for(int i=0; i<extensions.length; ++i) {
                IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
                for(int j=0; j<configElements.length; ++j) {
                    if(configElements[j].getName().equals("provider")) { //$NON-NLS-1$
                        try {
                            elementDomainProvider = (TypedElementLogicalDomainProvider) configElements[j].createExecutableExtension("typeProvider"); //$NON-NLS-1$
                        }
                        catch(CoreException e) {
                            IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getDefault().getBundle().getSymbolicName(), IStatus.ERROR,
                                    "The error was detected when creating the element domain provider ", e); //$NON-NLS-1$
                            IBMPluginActivator.getInstance().getLog().log(status);
                        }
                        break;
                    }
                }
            }
        }
        return elementDomainProvider;
        
    }

    public static ModelValidationProvider getModelValidationProvider(){
        if (modelValidationProvider != null) return modelValidationProvider;
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.core", "checkModelProvider"); //$NON-NLS-1$ //$NON-NLS-2$
        if (extensionPoint != null) {
            IExtension[] extensions = extensionPoint.getExtensions();
            for(int i=0; i<extensions.length; ++i) {
                IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
                for(int j=0; j<configElements.length; ++j) {
                    if(configElements[j].getName().equals("checkModel")) { //$NON-NLS-1$
                        try {
                            modelValidationProvider = (ModelValidationProvider) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
                        }
                        catch(CoreException e) {
                            IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getDefault().getBundle().getSymbolicName(), IStatus.ERROR,
                                    "The error was detected when creating the model validation provider ", e); //$NON-NLS-1$
                            IBMPluginActivator.getInstance().getLog().log(status);
                        }
                        break;
                    }
                }
            }
        }
        return modelValidationProvider;
    }

    
    public static Map getCodetemplatePatterns(){
        Map patterns = new HashMap();
        Preferences instanceNode = InstanceScope.INSTANCE.getNode("com.ibm.datatools.core.ui");//$NON-NLS-1$
        String text="";
        text = instanceNode.get(PreferenceConstants.CODETEMPLATE_APPLY_CONTEXT_KEY,text);
        if (text != null && !text.equals("")) {
            DdlGenerationUtility.getCodetemplateContext(patterns, CREATE_STATEMENT, text);
            DdlGenerationUtility.getCodetemplateContext(patterns, DROP_STATEMENT, text);
        }
        
        text = instanceNode.get(PreferenceConstants.CODETEMPLATE_TABLE_PATTERN_KEY,"");
        DdlGenerationUtility.getCodetemplatePattern(patterns, text);
    
        text = instanceNode.get(PreferenceConstants.CODETEMPLATE_ROUTINE_PATTERN_KEY,"");
        DdlGenerationUtility.getCodetemplatePattern(patterns, text);
    
        text = instanceNode.get(PreferenceConstants.CODETEMPLATE_VIEW_PATTERN_KEY,"");
        DdlGenerationUtility.getCodetemplatePattern(patterns, text);

        text = instanceNode.get(PreferenceConstants.CODETEMPLATE_CONSTRAINT_PATTERN_KEY,"");
        DdlGenerationUtility.getCodetemplatePattern(patterns, text);
        
        return patterns;
    }
    
    
    
    private static void getCodetemplateContext(Map map,String statementType, String data){
        StringTokenizer token = new StringTokenizer(data,DdlGenerationUtility.STATEMENT_TYPE_SEPARATOR);
        while (token.hasMoreTokens()) {
            String str = token.nextToken();
            int pos = str.indexOf(statementType);
            if (pos == -1) continue;
            StringTokenizer contextToken = new StringTokenizer(str.substring(statementType.length()+1),DdlGenerationUtility.CONTEXT_SEPARATOR);
            while (contextToken.hasMoreTokens()) {
                String str1 = contextToken.nextToken();
                String name =str1;
                boolean applied = false;
                if (str1.endsWith(DdlGenerationUtility.CONTEXT_APPLY_FLAG)) {
                    name = str1.substring(0, str1.length()-1);
                    applied = true;
                }

                CodeTemplateContextPattern context = null;
                if (map.containsKey(name)) {
                    context = (CodeTemplateContextPattern)map.get(name);
                }else {
                    context = new CodeTemplateContextPattern();
                    map.put(name,context);
                }
                
                if (applied){
                    if (statementType.equals(CREATE_STATEMENT)) {
                        context.setAppliedType(DdlGenerationUtility.GENERATE_CREATE_PATTERN);
                    } else if (statementType.equals(DROP_STATEMENT)) {
                        context.setAppliedType(DdlGenerationUtility.GENERATE_DROP_PATTERN);
                    }
                }
                
            }
        }
    }
    
    private static void getCodetemplatePattern(Map map, String codeTemplate){
        if (codeTemplate == null) return;

        Pattern pattern = Pattern.compile(DdlGenerationUtility.PATTERN_KEY,Pattern.DOTALL); 
        String[] result = pattern.split(codeTemplate);
        for (int i = 0; i < result.length; i++) {
            parserPattern(map,result[i]);
        }
        
    }

    private static void parserPattern(Map map, String pattern){
        int pos = pattern.indexOf("::");
        if (pos == -1 ) return;
        
        String context = pattern.substring(0,pos);
        String text = pattern.substring(pos+2);
        
        if (!map.containsKey(context)) {
            return;
        }

        CodeTemplateContextPattern contextPattern = (CodeTemplateContextPattern)map.get(context);
        if (contextPattern == null) return;
        pos = text.indexOf("::");
        if (pos == -1 ) return;
        
        String type = text.substring(0,pos);
        if (type.equals(CREATE_PROLOG)){
            contextPattern.setCreateProlog(text.substring(pos +2));
        } else if (type.equals(CREATE_POSTLOG)){
            contextPattern.setCreatePostlog(text.substring(pos +2));
        } else if (type.equals(DROP_PROLOG)) {
            contextPattern.setDropProlog(text.substring(pos +2));
        } else if (type.equals(DROP_POSTLOG)) {
            contextPattern.setDropPostlog(text.substring(pos +2));
        }

    }

    private static int tableNameSuffix = 0;

    private static String getTableNameSuffix() {
        return String.valueOf(tableNameSuffix++);
    }

    private static String iterateUntilUnique(ArrayList<String> tables,String prefix) {
        String retString = prefix + getTableNameSuffix();
        if (tables.size() > 0) {
            for (String table: tables) {
                if (table.equals(retString)) return iterateUntilUnique(tables,prefix);
            }
        }
        return retString;
    }
    
    private static String createUniqueTableName(List<Table> existing,String prefix) {
        int prefixLength = prefix.length();
        ArrayList<String> tableNames = new ArrayList<String>();
        // Eliminate all the tables with different prefix first
        Iterator<Table> it = existing.iterator();
        while (it.hasNext()) {
            String tableName = it.next().getName();
            if ((tableName.length() >= prefixLength) &&
                    tableName.substring(0,prefixLength).equals(prefix)) tableNames.add(tableName);
        }
        // Find a unique name from the remaining index names
        return iterateUntilUnique(tableNames,prefix);
    }
    
    public static String getUniqueTableName(Schema schema,String prefix) {
        Object collection = schema.eGet(SQLSchemaPackage.eINSTANCE.getSchema_Tables());
        if (collection instanceof EList) {
            return createUniqueTableName((EList)collection,prefix);
        }
        return null;
    }

    // Determine if the unique constraint is supported by the index
    public static boolean isSameMembership(Index o, UniqueConstraint o1) {
        EList idxMembers = o.getMembers();
        EList ucMembers = o1.getMembers();
        if (idxMembers.size() != ucMembers.size()) return false;
        for (int i=0;i<idxMembers.size();i++) {
            if (ucMembers.get(i) != ((IndexMember)(idxMembers.get(i))).getColumn()) return false;
        }
        return true;
    }

    /**
     * Get the character to use as the data object identifier delimiter
     * (typically the double quote character).
     * @param object
     * @return
     */
    public static char getSqlIdentifierDelimiterCharacter(SQLObject object) {
        // For now, we are always returning the default delimiter, but in the future
        //   we may want to make use of the object to tailor this to the database 
        //   vendor/version or user-defined delimiter
        SQLQuerySourceFormat sourceFormat = SQLQuerySourceFormat.copyDefaultFormat();
        return sourceFormat.getDelimitedIdentifierQuote();
    }
    
    /**
     * Get the character to use as the DDL escape character.  There is currently
     * no support for this in the SQL parser, so we assume it will be the same as
     * the data object identifier delimiter (hence, when a delimiter character
     * is embedded in a data object name, it will be doubled up to escape it).
     * @param object
     * @return
     */
    public static char getSqlEscapeCharacter(SQLObject object) {
        // For now, there is no separate escape character definition, so we just
        //   return the indentifier delimiter.  In future this may be implemented and
        //   dependent on vendor-version
        return getSqlIdentifierDelimiterCharacter(object);
    }

    /**
     * Return true if the name of the object can be used in SQL without delimiting.
     * @param object The data object whose name is to be considered.
     * @return
     */
    public static boolean isValidUnquotedDataObjectIdentifier(SQLObject object) {
        String allowedFirstCharSymbols = "@$#";
        String name = object.getName();
        if (name == null || name.length() < 1) {
            return false;
        }
        char firstChar = name.charAt(0);
        if (!Character.isLetter(firstChar) &&
                !allowedFirstCharSymbols.contains(new String(new char[] {firstChar}))) {
            return false;
        }
        for (int i=1;i<name.length();i++) {
            char c = name.charAt(i);
            if (!Character.isLetterOrDigit(c) &&
                !allowedFirstCharSymbols.contains(new String(new char[] {c})) &&
                c != '_') {
                return false;
            }
        }
        return true;
    }
    
    public static List<Integer> getConstraintColumnReferencePointers(CheckConstraint constraint,Column col) {
        List<Integer> occurrenceIdxs = new ArrayList<Integer>();
        if(col == null) {
            return occurrenceIdxs;
        }
        String name = col.getName();
        if(name == null || (name.length()==0)) {
            return occurrenceIdxs;
        }
        SearchCondition cond = constraint.getSearchCondition();
        if(cond == null) {
            return occurrenceIdxs;
        }
        String sql = cond.getSQL();
        if((sql==null) || (sql.length()==0)) {
            return occurrenceIdxs;
        }

        // Looks like we have all the essentials
        Table table = constraint.getBaseTable();
        String tableName = table.getName();
        Schema schema = table.getSchema();
        String schemaName = schema.getName();
        
        Set<ValueExpressionColumn> vECs = getConstraintValueExpressionColumns(schema,table,constraint);

        // get the SQL source format options and set at least the current schema
        // that is omited but implicit for any unqualified table references
        // important for later resolving of table references!
        SQLQuerySourceFormat sourceFormat = SQLQuerySourceFormat.copyDefaultFormat();
        sourceFormat.setOmitSchema(schemaName);
        char nameDelimiter = sourceFormat.getDelimitedIdentifierQuote();
        String selectProlog = "SELECT * FROM " + nameDelimiter + tableName + nameDelimiter + " WHERE ";//$NON-NLS-1$ //$NON-NLS-2$
        
        for (ValueExpressionColumn vEC:vECs) {
            // Ignore the column references that do not reference the current
            //   column of interest
            if (isValidUnquotedDataObjectIdentifier(col)) {
                // Identifier does not need to be quoted - ignore case
                if (!((ValueExpressionColumn)vEC).getName().equalsIgnoreCase(col.getName())) {
                    continue;
                }
            }
            else {
                // Identifier must be quoted - case must be respected
                if (!((ValueExpressionColumn)vEC).getName().equals(col.getName())) {
                    continue;
                }
            }
            SQLQuerySourceInfo info = ((ValueExpressionColumn)vEC).getSourceInfo();
            int idx = info.getSpanStartOffset();
            String snippet = info.getSourceSnippet();
            // If the column name is delimited in the constraint expression, the
            //   index will pointing to the first delimiter instead of to the first
            //   character of the name -- so add 1 in this case to the index
            if ((snippet.charAt(0) == nameDelimiter) &&
                    (snippet.length() > name.length())) {
                idx = idx + 1;
            }
            occurrenceIdxs.add(idx - selectProlog.length());
        }
        return occurrenceIdxs;
    }

    public static Set<ValueExpressionColumn> getConstraintValueExpressionColumns(Schema schema,Table table,CheckConstraint constraint) {
        Set<ValueExpressionColumn> cols = new HashSet<ValueExpressionColumn>();

        SearchCondition cond = constraint.getSearchCondition();

        if(cond == null) {
            return cols;
        }
        String sql = cond.getSQL();
        if((sql==null) || (sql.length()==0)) {
            return cols;
        }

        // Looks like we have all the essentials
        String tableName = table.getName();
        String schemaName = schema.getName();
        Database database = schema.getDatabase();
        
        try {
            // create the post parse processors
            PostParseProcessor tableRefResolver = new TableReferenceResolver(database,schemaName);
            PostParseProcessor dataTypeResolver = new DataTypeResolver();
            
            // ordering is important for post parse processing! first we need to fill
            // in the database information for table references and column types
            List postParseProcessors = new ArrayList();
            postParseProcessors.add(0, tableRefResolver);
            postParseProcessors.add(1, dataTypeResolver);
        
            // get the SQL source format options and set at least the current schema
            // that is omited but implicit for any unqualified table references
            // important for later resolving of table references!
            SQLQuerySourceFormat sourceFormat = SQLQuerySourceFormat.copyDefaultFormat();
            sourceFormat.setOmitSchema(schemaName);
            char nameDelimiter = sourceFormat.getDelimitedIdentifierQuote();

            String selectProlog = "SELECT * FROM " + nameDelimiter + tableName + nameDelimiter + " WHERE ";//$NON-NLS-1$ //$NON-NLS-2$
            String selectStatement = selectProlog + sql; // construct a select statement

            // create an instance of the ParserManager
            SQLQueryParserManager parserManager = 
                SQLQueryParserManagerProvider.getInstance().
                getParserManager(database.getVendor(),database.getVersion());
            parserManager.configParser(sourceFormat,postParseProcessors);
            
            // parse the SQL statement
            SQLQueryParseResult parseResult = (SQLQueryParseResult) parserManager.parseQuery(selectStatement);

            // get the parsed SQLQueryObject model instance
            QueryStatement qS = parseResult.getQueryStatement();
            QuerySearchCondition qSC = StatementHelper.getSearchCondition(qS);
            Set colSet = TableHelper.findColumnReferencesInSearchCondition(qSC);
            for (Object vEC:colSet) {
                if (vEC instanceof ValueExpressionColumn) {
                    cols.add((ValueExpressionColumn)vEC);
                }
            }
        }
        catch(Exception ex) {
        }
        return cols;
    }

    public static Column getFirstColumnInCheckConstraintSearchCondition(Schema schema,Table table,CheckConstraint constraint) {
        Set<ValueExpressionColumn> vECs = getConstraintValueExpressionColumns(schema,table,constraint);
        List<Column> cols = table.getColumns();
        for (ValueExpressionColumn vEC:vECs) {
            for (Column col:cols) {
                if (isValidUnquotedDataObjectIdentifier(col)) {
                    // Identifier does not need to be quoted - ignore case
                    if (!((ValueExpressionColumn)vEC).getName().equalsIgnoreCase(col.getName())) {
                        continue;
                    }
                }
                else {
                    // Identifier must be quoted - case must be respected
                    if (!((ValueExpressionColumn)vEC).getName().equals(col.getName())) {
                        continue;
                    }
                }
                // Have a match
                return col;
            }
        }
        return null;
    }
    
    public static String convertLineEndings(String str, String lineEnding) {
        // Replace line endings with system line ending
        BufferedReader reader = new BufferedReader(new StringReader(str));
        StringBuffer buf = new StringBuffer();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                buf.append(line + lineEnding);
            }
            reader.close();
        } catch (IOException e) {
            return null;
        }
        return buf.toString();
    }

	public static boolean filterOutComments( String sql )
	{
		String[] statements = sql.split( "\n" );
		for ( String s : statements )
		{
			if ( s.startsWith( "--" ) )
			{
				continue;
			}
			else if ( s.trim().length() > 0 )
			{
				return true;
			}
		}
		return false;
	}
}
