package org.eclipse.datatools.enablement.sybase.ase.providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.CatalogNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.IndexNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.StoredProcedureNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.TableNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.UDTNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.ViewNode;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObjectListener;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.loading.ILoadingService;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServicesManager;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.enablement.ase.JDBCASEProfileMessages;
import org.eclipse.datatools.enablement.sybase.Messages;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable;
import org.eclipse.datatools.enablement.sybase.ase.virtual.DataTypesFolder;
import org.eclipse.datatools.enablement.sybase.ase.virtual.DefaultNode;
import org.eclipse.datatools.enablement.sybase.ase.virtual.ProxyTableNode;
import org.eclipse.datatools.enablement.sybase.ase.virtual.RuleNode;
import org.eclipse.datatools.enablement.sybase.ase.virtual.SybaseASECheckConstraintNode;
import org.eclipse.datatools.enablement.sybase.ase.virtual.SybaseASEForeignKeyNode;
import org.eclipse.datatools.enablement.sybase.ase.virtual.SybaseASEIndexNode;
import org.eclipse.datatools.enablement.sybase.ase.virtual.SybaseASEPrimaryKeyNode;
import org.eclipse.datatools.enablement.sybase.ase.virtual.SybaseASEStoredProcedureNode;
import org.eclipse.datatools.enablement.sybase.ase.virtual.SybaseASETableNode;
import org.eclipse.datatools.enablement.sybase.ase.virtual.SybaseASEUDTNode;
import org.eclipse.datatools.enablement.sybase.ase.virtual.SybaseASEUniqueConstraintNode;
import org.eclipse.datatools.enablement.sybase.ase.virtual.SybaseASEViewNode;
import org.eclipse.datatools.enablement.sybase.ase.virtual.WebServicesAsTableFolder;
import org.eclipse.datatools.enablement.sybase.ase.virtual.WebServicesFolder;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.ui.filter.CatalogFilterProvider;
import org.eclipse.datatools.enablement.sybase.ui.filter.SchemaFilterProvider;
import org.eclipse.datatools.enablement.sybase.ui.filter.SchemaObjectFilterProvider;
import org.eclipse.datatools.enablement.sybase.ui.util.DSEContentProviderUtil;
import org.eclipse.datatools.enablement.sybase.ui.util.DSEUtil;
import org.eclipse.datatools.enablement.sybase.ui.util.ShowSysTableUtil;
import org.eclipse.datatools.enablement.sybase.virtual.CheckConstraintNode;
import org.eclipse.datatools.enablement.sybase.virtual.ForeignKeyNode;
import org.eclipse.datatools.enablement.sybase.virtual.ParametersNode;
import org.eclipse.datatools.enablement.sybase.virtual.PrimaryKeyNode;
import org.eclipse.datatools.enablement.sybase.virtual.UniqueConstraintNode;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

public class SybaseASEContentProvider /*extends ServerExplorerContentProviderNav*/ implements ICommonContentProvider,
        ICatalogObjectListener, ILoadingService
{

    private static final ContainmentService         containmentService            = RDBCorePlugin.getDefault()
                                                                                          .getContainmentService();
    private static final IVirtualNodeServiceFactory factory                       = IVirtualNodeServiceFactory.INSTANCE;
    private static final Object[]                   EMPTY_ELEMENT_ARRAY           = new Object[0];
    private static final String                     DATABASES_FOLDER              = JDBCASEProfileMessages
                                                                                          .getString("databases.folder.name");                          //$NON-NLS-1$
    private static final String                     WEB_SERVICES_FOLDER           = JDBCASEProfileMessages
                                                                                          .getString("webservices.folder.name");                        //$NON-NLS-1$
    private static final String                     DATA_TYPES_FOLDER             = JDBCASEProfileMessages
                                                                                          .getString("datatypes.folder.name");                          //$NON-NLS-1$
    private static final String                     SCHEMA                        = ResourceLoader.INSTANCE
                                                                                          .queryString("DATATOOLS.SERVER.UI.EXPLORER.SCHEMA");          //$NON-NLS-1$
    private static final String                     TABLE                         = ResourceLoader.INSTANCE
                                                                                          .queryString("DATATOOLS.SERVER.UI.EXPLORER.TABLE");           //$NON-NLS-1$
    private static final String                     VIEW                          = ResourceLoader.INSTANCE
                                                                                          .queryString("DATATOOLS.SERVER.UI.EXPLORER.VIEW");            //$NON-NLS-1$
    private static final String                     UDT                           = JDBCASEProfileMessages
                                                                                          .getString("udt.folder.name");
    private static final String                     DEPENDENCY                    = ResourceLoader.INSTANCE
                                                                                          .queryString("DATATOOLS.SERVER.UI.EXPLORER.DEPENDENCY");      //$NON-NLS-1$
    private static final String                     STORED_PROCEDURE              = ResourceLoader.INSTANCE
                                                                                          .queryString("DATATOOLS.SERVER.UI.EXPLORER.STORED_PROCEDURE"); //$NON-NLS-1$
    private static final String                     WEB_SERVICES_AS_TABLES_FOLDER = JDBCASEProfileMessages
                                                                                          .getString("WebServiceAsTables.folder.name");

    private static final String                     PROXY_TABLE_FOLDER            = JDBCASEProfileMessages
                                                                                          .getString("ProxyTable.folder.name");
    private static final String                     DEFAULT_FOLDER                = JDBCASEProfileMessages
                                                                                          .getString("default.folder.name");
    private static final String                     RULE_FOLDER                   = JDBCASEProfileMessages
                                                                                          .getString("rule.folder.name");
    private static final String                     PARAMETERS_FOLDER             = Messages
                                                                                          .getString("Parameters_folder_name");
    
    private static final String                     INDEX               		  = ResourceLoader.INSTANCE
    																					  .queryString("DATATOOLS.SERVER.UI.EXPLORER.INDEX");           //$NON-NLS-1$
//    private TreeViewer                              viewer;
    private CommonViewer viewer;
       
//    /**
//     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
//     */
    public Object[] load(Object parentElement)
    {
//        String databaseName = null;
        boolean isShowSchema = false;
//        boolean isShowOwner = true;        
//    	if(parentElement instanceof IVirtualNode){
//    		Object parent = ((IVirtualNode)parentElement).getParent(); 
//    		if(parent instanceof SybaseASESchema){
//    			databaseName = ((SybaseASESchema)parent).getCatalog().getName();
//    		}
//    		else if(parent instanceof SybaseASECatalog){
//    			databaseName = ((SybaseASECatalog)parent).getName();
//    		}
//    	}
//    	else{
//    	    databaseName = DSEUtil.findDatabaseNameByChild(parentElement);
//    	}
    	
   		isShowSchema = DSEUtil.checkIsShowSchema(parentElement);
//   		isShowOwner = DSEUtil.checkIsShowOwner(parentElement);
   		
        if (parentElement instanceof ConnectionInfo)
        {
            // Database should be these virtual nodes' invisible parent
            Database db = ((ConnectionInfo) parentElement).getSharedDatabase();
            return new Object[]
            {
                new CatalogNode(DATABASES_FOLDER, DATABASES_FOLDER, db),
                new DataTypesFolder(DATA_TYPES_FOLDER, DATA_TYPES_FOLDER, db)
            };
            
        }
        if (parentElement instanceof SybaseASEDatabase)
        {
            // this happens when user uses generic jdbc profile with ASE driver
            Database db = (Database) parentElement;
            return new Object[]
                              {
                new CatalogNode(DATABASES_FOLDER, DATABASES_FOLDER, db),
                new DataTypesFolder(DATA_TYPES_FOLDER, DATA_TYPES_FOLDER, db)
                              };
        }
        else if(parentElement instanceof CatalogNode)
        {
            CatalogNode catalogNode = (CatalogNode) parentElement;
            Database db = (Database)catalogNode.getParent();
            
            CatalogFilterProvider provider = new CatalogFilterProvider();
            ConnectionFilter filter = provider.getConnectionFilter(db);
            
            return getFilteredObjects(filter, db.getCatalogs()).toArray();
        }
        else if (parentElement instanceof SybaseASECatalog)
        {
        	SybaseASECatalog catalog = ((SybaseASECatalog) parentElement);
//        	databaseName = catalog.getName();

        	if(isShowSchema)
        	{
    			SchemaFilterProvider provider = new SchemaFilterProvider();
                ConnectionFilter filter = provider.getConnectionFilter(catalog);
                
                EList schemas = catalog.getSchemas();
                EList authIds = catalog.getAuthorizationIds();
                List filteredSchemas = new ArrayList();
                if (authIds != null)
                {
                    List authIdNames = new ArrayList();
                    for (int i = 0; i < authIds.size(); i++)
                    {
                        Object obj = authIds.get(i);
                        if (obj instanceof User)
                        {
                            authIdNames.add(((SQLObject) obj).getName());
                        }
                    }
                    for (Iterator iterator = schemas.iterator(); iterator.hasNext();)
                    {
                        Schema schema = (Schema) iterator.next();
                        if (authIdNames.contains(schema.getName()))
                        {
                            filteredSchemas.add(schema);
                        }
                    }
                }

                return getFilteredObjects(filter, filteredSchemas).toArray();
        	}   		
        	else
        	{
                return new Object[]
                                  {
                                      new SybaseASETableNode(TABLE, TABLE, parentElement),
                                      new SybaseASEViewNode(VIEW, VIEW, parentElement),
                                      new ProxyTableNode(PROXY_TABLE_FOLDER, PROXY_TABLE_FOLDER, parentElement),
                                      new SybaseASEStoredProcedureNode(STORED_PROCEDURE, STORED_PROCEDURE, parentElement),
                                      new SybaseASEUDTNode(UDT, UDT, parentElement),
                                      new WebServicesAsTableFolder(WEB_SERVICES_AS_TABLES_FOLDER, WEB_SERVICES_AS_TABLES_FOLDER,
                                              parentElement), 
                                      new DefaultNode(DEFAULT_FOLDER, DEFAULT_FOLDER, parentElement),
                                      new RuleNode(RULE_FOLDER, RULE_FOLDER, parentElement)
                                  };        		
        	}                                       		
        }
        else if (parentElement instanceof SybaseASESchema)
        {
            return new Object[]
            {
                new SybaseASETableNode(TABLE, TABLE, parentElement),
                new SybaseASEViewNode(VIEW, VIEW, parentElement),
                new ProxyTableNode(PROXY_TABLE_FOLDER, PROXY_TABLE_FOLDER, parentElement),
                new SybaseASEStoredProcedureNode(STORED_PROCEDURE, STORED_PROCEDURE, parentElement),
                new SybaseASEUDTNode(UDT, UDT, parentElement),
                new WebServicesAsTableFolder(WEB_SERVICES_AS_TABLES_FOLDER, WEB_SERVICES_AS_TABLES_FOLDER,
                        parentElement), 
                new DefaultNode(DEFAULT_FOLDER, DEFAULT_FOLDER, parentElement),
                new RuleNode(RULE_FOLDER, RULE_FOLDER, parentElement)
            };
        }
        else if (parentElement instanceof DataTypesFolder)
        {
            DataTypesFolder dtf = (DataTypesFolder) parentElement;
            SybaseASEDatabase sad = (SybaseASEDatabase) dtf.getParent();
            return sad.getDataTypes().toArray();
        }
        else if (parentElement instanceof WebServicesFolder)
        {
            WebServicesFolder wsf = (WebServicesFolder) parentElement;
            SybaseASEDatabase sad = (SybaseASEDatabase) wsf.getParent();
            return sad.getWebServices().toArray();
        }
        // Web Services As Tables
        else if (parentElement instanceof WebServicesAsTableFolder)
        {
        	WebServicesAsTableFolder webServicesAsTableFolder = (WebServicesAsTableFolder) parentElement;
        	SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(WebServicesAsTableFolder.WEBSERVERICE_AS_TABLE_FILTER);
        	if(isShowSchema){
                SybaseASESchema schema = (SybaseASESchema) webServicesAsTableFolder.getParent();
                ConnectionFilter filter = provider.getConnectionFilter(schema);          
                return getFilteredObjects(filter, schema.getWebServicesAsTables()).toArray();        		
        	}
        	else{
        		List ws = new ArrayList();
            	SybaseASECatalog catalog = (SybaseASECatalog)webServicesAsTableFolder.getParent();
            	List schemas = catalog.getSchemas();
            	for (Iterator iterator = schemas.iterator(); iterator.hasNext();) {
					SybaseASESchema schema = (SybaseASESchema) iterator.next();
					ConnectionFilter filter = provider.getConnectionFilter(schema);
            		ws.addAll(getFilteredObjects(filter, schema.getWebServicesAsTables()));
				}
            	return ws.toArray();        		
        	}
        }
        else if (parentElement instanceof TableNode)
        {
            TableNode tableNode = (TableNode)parentElement;
            SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(ConnectionFilter.TABLE_FILTER);
            if(isShowSchema){
                SybaseASESchema schema = (SybaseASESchema) tableNode.getParent();
                ConnectionFilter filter = provider.getConnectionFilter(schema);
                
                List tableList = null;
                
                boolean isShowAll = ShowSysTableUtil.isShowSysTableOn(schema);
                if (isShowAll)
                {
                    tableList = schema.getSystemAndNormalTable();
                }
                else
                {
                    tableList = schema.getNormalTables();
                }
                
                return getFilteredObjects(filter, tableList).toArray();            	
            }
            else{
            	List tables = new ArrayList();
            	SybaseASECatalog catalog = (SybaseASECatalog)tableNode.getParent();
            	List schemas = catalog.getSchemas();
            	for (Iterator iterator = schemas.iterator(); iterator.hasNext();) {
					SybaseASESchema schema = (SybaseASESchema) iterator.next();
					List tableList = null;
            	    ConnectionFilter filter = provider.getConnectionFilter(schema);                   
                    boolean isShowAll = ShowSysTableUtil.isShowSysTableOn(schema);
                    if (isShowAll)
                    {
                        tableList = schema.getSystemAndNormalTable();
                    }
                    else
                    {
                        tableList = schema.getNormalTables();
                    }
                    tables.addAll(getFilteredObjects(filter, tableList));
				}
            	return tables.toArray();
            }
        }
        else if (parentElement instanceof ViewNode)
        {
        	ViewNode viewNode = (ViewNode) parentElement;
        	SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(ConnectionFilter.VIEW_FILTER);
        	if(isShowSchema){
                ViewNode tableNode = (ViewNode) parentElement;
                SybaseASESchema schema = (SybaseASESchema) tableNode.getParent();

                ConnectionFilter filter = provider.getConnectionFilter(schema);
                
                return getFilteredObjects(filter, schema.getViewTables()).toArray();        		
        	}
        	else{
        		List views = new ArrayList();
            	SybaseASECatalog catalog = (SybaseASECatalog)viewNode.getParent();
            	List schemas = catalog.getSchemas();
            	for (Iterator iterator = schemas.iterator(); iterator.hasNext();) {
					SybaseASESchema schema = (SybaseASESchema) iterator.next();
					ConnectionFilter filter = provider.getConnectionFilter(schema);
            		views.addAll(getFilteredObjects(filter, schema.getViewTables()));
				}
            	return views.toArray();
        	}
        }
        else if(parentElement instanceof StoredProcedureNode)
        {
            StoredProcedureNode spNode = (StoredProcedureNode)parentElement;
            SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(ConnectionFilter.STORED_PROCEDURE_FILTER);
            if(isShowSchema){
                SybaseASESchema schema = (SybaseASESchema)spNode.getParent();
                ConnectionFilter filter = provider.getConnectionFilter(schema);
                
                return getFilteredObjects(filter, schema.getProcedures()).toArray();            	
            }
            else{
            	SybaseASECatalog catalog = (SybaseASECatalog)spNode.getParent();
            	List sps = new ArrayList();
            	List schemas = catalog.getSchemas();
            	for (Iterator iterator = schemas.iterator(); iterator.hasNext();) {
					SybaseASESchema schema = (SybaseASESchema) iterator.next();
					ConnectionFilter filter = provider.getConnectionFilter(schema);
            		sps.addAll(getFilteredObjects(filter, schema.getProcedures()));
				}
            	return sps.toArray();
            }
        }
        else if(parentElement instanceof UDTNode)
        {
            UDTNode spNode = (UDTNode)parentElement;
            SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(ConnectionFilter.USER_DEFINED_TYPE_FILTER);
        	if(isShowSchema){
                SybaseASESchema schema = (SybaseASESchema)spNode.getParent();
                ConnectionFilter filter = provider.getConnectionFilter(schema);            
                return getFilteredObjects(filter, schema.getUserDefinedTypes()).toArray();        		
        	}
        	else{
            	SybaseASECatalog catalog = (SybaseASECatalog)spNode.getParent();
            	List udts = new ArrayList();
            	List schemas = catalog.getSchemas();
            	for (Iterator iterator = schemas.iterator(); iterator.hasNext();) {
					SybaseASESchema schema = (SybaseASESchema) iterator.next();
					ConnectionFilter filter = provider.getConnectionFilter(schema);
            		udts.addAll(getFilteredObjects(filter, schema.getUserDefinedTypes()));
				}
            	return udts.toArray();        		
        	}

        }
        else if (parentElement instanceof ProxyTableNode)
        {
            ProxyTableNode tableNode = (ProxyTableNode) parentElement;
            SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(ConnectionFilter.REMOTE_TABLE_FILTER);
            if(isShowSchema){
                SybaseASESchema schema = (SybaseASESchema) tableNode.getParent();
                ConnectionFilter filter = provider.getConnectionFilter(schema);         
                return getFilteredObjects(filter, schema.getProxyTables()).toArray();            	
            }
            else{
            	SybaseASECatalog catalog = (SybaseASECatalog)tableNode.getParent();
            	List tables = new ArrayList();
            	List schemas = catalog.getSchemas();
            	for (Iterator iterator = schemas.iterator(); iterator.hasNext();) {
					SybaseASESchema schema = (SybaseASESchema) iterator.next();
					ConnectionFilter filter = provider.getConnectionFilter(schema);
            		tables.addAll(getFilteredObjects(filter, schema.getProxyTables()));
				}
            	return tables.toArray();            	
            }
        }
        else if (parentElement instanceof DefaultNode)
        {
        	DefaultNode defaultNode = (DefaultNode) parentElement;
        	SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(DefaultNode.DEFAULT_FILTER);
        	if(isShowSchema){
                SybaseASESchema schema = (SybaseASESchema) defaultNode.getParent();
                ConnectionFilter filter = provider.getConnectionFilter(schema);          
                return getFilteredObjects(filter, schema.getDefaults()).toArray();        		
        	}
        	else{
            	SybaseASECatalog catalog = (SybaseASECatalog)defaultNode.getParent();
            	List defaults = new ArrayList();
            	List schemas = catalog.getSchemas();
            	for (Iterator iterator = schemas.iterator(); iterator.hasNext();) {
					SybaseASESchema schema = (SybaseASESchema) iterator.next();
					ConnectionFilter filter = provider.getConnectionFilter(schema);
            		defaults.addAll(getFilteredObjects(filter, schema.getDefaults()));
				}
            	return defaults.toArray();        		
        	}

        }
        else if (parentElement instanceof RuleNode)
        {
        	RuleNode ruleNode = (RuleNode) parentElement;
        	SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(RuleNode.RULE_FILTER);
        	if(isShowSchema){
        		SybaseASESchema schema = (SybaseASESchema) ruleNode.getParent();       
                ConnectionFilter filter = provider.getConnectionFilter(schema);
                return getFilteredObjects(filter, schema.getRules()).toArray();        		
        	}
        	else{
            	SybaseASECatalog catalog = (SybaseASECatalog)ruleNode.getParent();
            	List rules = new ArrayList();
            	List schemas = catalog.getSchemas();
            	for (Iterator iterator = schemas.iterator(); iterator.hasNext();) {
					SybaseASESchema schema = (SybaseASESchema) iterator.next();
					ConnectionFilter filter = provider.getConnectionFilter(schema);
            		rules.addAll(getFilteredObjects(filter, schema.getRules()));
				}

            	return rules.toArray();        		
        	}
        }
        else if (parentElement instanceof Procedure)
        {
            return new Object[]
            {
                new ParametersNode(PARAMETERS_FOLDER, PARAMETERS_FOLDER, parentElement)
            };
        }
        else if (parentElement instanceof ParametersNode)
        {
            ParametersNode parametersNode = (ParametersNode) parentElement;
            Routine routine =  (Routine)parametersNode.getParent();
            
            return routine.getParameters().toArray();
        }
        else if (parentElement instanceof IndexNode)
        {
            IndexNode indexNode = (IndexNode) parentElement;
            BaseTable table = (BaseTable) indexNode.getParent();
            List result = new ArrayList();
            List indices = table.getIndex();
            for (int i = 0; i < indices.size(); i++)
            {
                Index index = (Index) indices.get(i);
                if (!index.isSystemGenerated())
                {
                    result.add(index);
                }
            }
            return result.toArray();
        }
        else if (parentElement instanceof ViewTable)
        {
            return DSEContentProviderUtil.getViewTableChildren(parentElement);
        }
        else if (parentElement instanceof SybaseASEProxyTable)
        {
        	List children = new ArrayList(Arrays.asList(DSEContentProviderUtil.getProxyTableChildren(parentElement)));
        	children.add(new SybaseASEIndexNode(INDEX, INDEX, parentElement));
        	children.add(new SybaseASEPrimaryKeyNode(DSEContentProviderUtil.PRIMARY_KEY_FOLDER,DSEContentProviderUtil.PRIMARY_KEY_FOLDER,parentElement));
        	children.add(new SybaseASEUniqueConstraintNode(DSEContentProviderUtil.UNIQUE_CONSTRAINT_FOLDER, DSEContentProviderUtil.UNIQUE_CONSTRAINT_FOLDER, parentElement));
        	children.add(new SybaseASECheckConstraintNode(DSEContentProviderUtil.CHECK_CONSTRAINT_FOLDER, DSEContentProviderUtil.CHECK_CONSTRAINT_FOLDER, parentElement));
        	children.add(new SybaseASEForeignKeyNode(DSEContentProviderUtil.FOREIGN_KEY_FOLDER, DSEContentProviderUtil.FOREIGN_KEY_FOLDER, parentElement));
        	return children.toArray();
        }
        else if (parentElement instanceof SybaseASEWebServiceTable)
        {
        	return ASEContentProviderUtil.getWebServiceTableChildren(parentElement);
        }
        else if (parentElement instanceof Table)
        {
            List children = new ArrayList(Arrays.asList(DSEContentProviderUtil.getTableChildren(parentElement)));
        	children.add(new SybaseASEIndexNode(INDEX, INDEX, parentElement));
        	children.add(new SybaseASEPrimaryKeyNode(DSEContentProviderUtil.PRIMARY_KEY_FOLDER,DSEContentProviderUtil.PRIMARY_KEY_FOLDER,parentElement));
        	children.add(new SybaseASEUniqueConstraintNode(DSEContentProviderUtil.UNIQUE_CONSTRAINT_FOLDER, DSEContentProviderUtil.UNIQUE_CONSTRAINT_FOLDER, parentElement));
        	children.add(new SybaseASECheckConstraintNode(DSEContentProviderUtil.CHECK_CONSTRAINT_FOLDER, DSEContentProviderUtil.CHECK_CONSTRAINT_FOLDER, parentElement));
        	children.add(new SybaseASEForeignKeyNode(DSEContentProviderUtil.FOREIGN_KEY_FOLDER, DSEContentProviderUtil.FOREIGN_KEY_FOLDER, parentElement));
        	return children.toArray();
        }
        else if (parentElement instanceof PrimaryKeyNode || parentElement instanceof UniqueConstraintNode
                || parentElement instanceof CheckConstraintNode || parentElement instanceof ForeignKeyNode)
        {
       		return DSEContentProviderUtil.getConstraintNodeChildren(parentElement);
        }
        else if (parentElement instanceof CheckConstraint)
        {
            return null;
        }
        else if (parentElement instanceof Column)
        {
            return null;
        }
        else if (parentElement instanceof ForeignKey)
        {
            return null;
        }
        else if (parentElement instanceof Index)
        {
            return null;
        }
        else if (parentElement instanceof PrimaryKey)
        {
            return null;
        }
        else if (parentElement instanceof Trigger)
        {
            return null;
        }
        else if (parentElement instanceof UniqueConstraint)
        {
            return null;
        }
        else if (parentElement instanceof UserDefinedFunction)
        {
            return null;
        }
//        else if(parentElement instanceof TriggerNode)
//        {
//    		BaseTable table = (BaseTable)((IVirtualNode)parentElement).getParent();
//    		return table.getTriggers().toArray();
//      	
//        }
        return EMPTY_ELEMENT_ARRAY;
    }

    private List getFilteredObjects(ConnectionFilter filter, List oldList)
    {
        List result = new ArrayList();
        for (Iterator iterator = oldList.iterator(); iterator.hasNext();)
        {
            SQLObject sqlObj = (SQLObject) iterator.next();
            if(filter == null || !filter.isFiltered(sqlObj.getName()))
            {
                result.add(sqlObj);
            }
        }
        return result;
    }
   
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element)
    {
        if (element instanceof IVirtualNode)
        {
            return ((IVirtualNode) element).getParent();
        }
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element)
    {
        if (element instanceof CheckConstraint)
        {
            return false;
        }
        if (element instanceof Column)
        {
            return false;
        }
        if (element instanceof ForeignKey)
        {
            return false;
        }
        if (element instanceof Index)
        {
            return false;
        }
        if (element instanceof PrimaryKey)
        {
            return false;
        }
        if (element instanceof Trigger)
        {
            return false;
        }
        if (element instanceof UniqueConstraint)
        {
            return false;
        }
        if (element instanceof UserDefinedFunction)
        {
            return false;
        }
        if (element instanceof UserDefinedType)
        {
            return false;
        }
        if (element instanceof PredefinedDataType)
        {
            return false;
        }
        if (element instanceof SybaseASEDefault)
        {
            return false;
        }
        if (element instanceof SybaseASERule)
        {
            return false;
        }
        if (element instanceof SybaseParameter)
        {
            return false;
        }
        if (element instanceof Catalog)
        {
            return true;
        }
        //471852-1: forces folders under Table to load early to avoid infinite loop
        //but the REAL solution is to async refresh tree items (ServerExplorerContentProviderNav)
//        if (element instanceof IVirtualNode && ((IVirtualNode)element).getParent() instanceof Table)
//        {
//            return getChildren(element).length>0;
//        }
        return true;
    }

    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement)
    {
        return getChildren(inputElement);
    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose()
    {
        RefreshManager.getInstance().removeListener(null, this);
    }

    public void init(ICommonContentExtensionSite aConfig)
    {
    }

    public void restoreState(IMemento aMemento)
    {
    }

    public void saveState(IMemento aMemento)
    {
    }

    /**
     * Will get notified whenever the Server Explorer needs to be refreshed
     */
    public void notifyChanged(final ICatalogObject dmElement, int eventType)
    {
    	/*
    	 * When refresh a 'Database' virtual node, it's MODEL parent is SybaseASEDatabase,
    	 * but it's UI parent is actually connection profile,
    	 * so we have to make a judgement here for refreshing connection profile when dmElement is SybaseASEDatabase
    	 */ 

//        final TreeViewer viewer = super.getViewer();
        if (eventType == ICatalogObjectListener.EventTypeEnumeration.ELEMENT_REFRESH 
                && dmElement instanceof SybaseASEDatabase)
        {
            //TODO:
            IServicesManager.INSTANCE.getServerExplorerContentService().refreshNode(dmElement);
            
//            super.getViewer().getControl().getDisplay().syncExec(new Runnable()
//            {
//                public void run()
//                {
//                    ConnectionInfo conn = DatabaseConnectionRegistry.getConnectionForDatabase((Database) dmElement);
//                    viewer.refresh(conn.getConnectionProfile(), true);
//                }
//            });	
        }
    }
    
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (viewer instanceof CommonViewer && this.viewer == null)
    	{
    		this.viewer = (CommonViewer) viewer;
    	}	
	}

	public Object[] getChildren(Object parent) 
	{
		return new Loading ().getChildren(this.viewer, parent, this);
	}    
	
	private static final ResourceLoader resourceLoader = ResourceLoader.INSTANCE;

    private static final String DESCRIPTION = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.DESCRIPTION"); //$NON-NLS-1$
	
	public String getLoadingDescription() 
	{
		return DESCRIPTION;
	}
}
