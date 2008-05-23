package org.eclipse.datatools.enablement.sybase.asa.providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.IndexNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.SchemaNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.StoredProcedureNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.TableNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.UDFNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.UDTNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.ViewNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.loading.ILoadingService;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.enablement.sybase.Messages;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAProfileMessages;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseSchema;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTempTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase;
import org.eclipse.datatools.enablement.sybase.asa.virtual.DBEventsFolder;
import org.eclipse.datatools.enablement.sybase.asa.virtual.DataTypesFolder;
import org.eclipse.datatools.enablement.sybase.asa.virtual.ProxyTableNode;
import org.eclipse.datatools.enablement.sybase.asa.virtual.SybaseASACheckConstraintNode;
import org.eclipse.datatools.enablement.sybase.asa.virtual.SybaseASAForeignKeyNode;
import org.eclipse.datatools.enablement.sybase.asa.virtual.SybaseASAIndexNode;
import org.eclipse.datatools.enablement.sybase.asa.virtual.SybaseASAPrimaryKeyNode;
import org.eclipse.datatools.enablement.sybase.asa.virtual.SybaseASAStoredProcedureNode;
import org.eclipse.datatools.enablement.sybase.asa.virtual.SybaseASATableNode;
import org.eclipse.datatools.enablement.sybase.asa.virtual.SybaseASAUDFNode;
import org.eclipse.datatools.enablement.sybase.asa.virtual.SybaseASAUDTNode;
import org.eclipse.datatools.enablement.sybase.asa.virtual.SybaseASAUniqueConstraintNode;
import org.eclipse.datatools.enablement.sybase.asa.virtual.SybaseASAViewNode;
import org.eclipse.datatools.enablement.sybase.asa.virtual.WebServicesFolder;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.ui.filter.EventFilterProvider;
import org.eclipse.datatools.enablement.sybase.ui.filter.SchemaObjectFilterProvider;
import org.eclipse.datatools.enablement.sybase.ui.util.DSEContentProviderUtil;
import org.eclipse.datatools.enablement.sybase.ui.util.DSEUtil;
import org.eclipse.datatools.enablement.sybase.ui.util.ShowSysTableUtil;
import org.eclipse.datatools.enablement.sybase.virtual.CheckConstraintNode;
import org.eclipse.datatools.enablement.sybase.virtual.ForeignKeyNode;
import org.eclipse.datatools.enablement.sybase.virtual.ParametersNode;
import org.eclipse.datatools.enablement.sybase.virtual.PrimaryKeyNode;
import org.eclipse.datatools.enablement.sybase.virtual.UniqueConstraintNode;
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
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;
import org.eclipse.ui.navigator.IPipelinedTreeContentProvider;
import org.eclipse.ui.navigator.PipelinedShapeModification;
import org.eclipse.ui.navigator.PipelinedViewerUpdate;

public class SybaseASAContentProvider /*extends ServerExplorerContentProviderNav*/ implements ICommonContentProvider, IPipelinedTreeContentProvider, ILoadingService
{
    private static final ContainmentService         containmentService  = RDBCorePlugin.getDefault()
                                                                                .getContainmentService();
    private static final IVirtualNodeServiceFactory factory             = IVirtualNodeServiceFactory.INSTANCE;
    private static final String                     EVENTS_FOLDER       = JDBCASAProfileMessages
                                                                                .getString("events.folder.name");                             //$NON-NLS-1$
    private static final String                     WEB_SERVICES_FOLDER = JDBCASAProfileMessages
                                                                                .getString("webservices.folder.name");                        //$NON-NLS-1$
    private static final String                     DATA_TYPES_FOLDER   = JDBCASAProfileMessages
                                                                                .getString("datatypes.folder.name");                          //$NON-NLS-1$
    private static final String                     SCHEMA              = ResourceLoader.INSTANCE
                                                                                .queryString("DATATOOLS.SERVER.UI.EXPLORER.SCHEMA");          //$NON-NLS-1$
    private static final String                     TABLE               = ResourceLoader.INSTANCE
                                                                                .queryString("DATATOOLS.SERVER.UI.EXPLORER.TABLE");           //$NON-NLS-1$
    private static final String                     UDT                 = JDBCASAProfileMessages
                                                                                .getString("udt.folder.name");
    private static final String                     DEPENDENCY          = ResourceLoader.INSTANCE
                                                                                .queryString("DATATOOLS.SERVER.UI.EXPLORER.DEPENDENCY");      //$NON-NLS-1$
    private static final String                     STORED_PROCEDURE    = ResourceLoader.INSTANCE
                                                                                .queryString("DATATOOLS.SERVER.UI.EXPLORER.STORED_PROCEDURE"); //$NON-NLS-1$
    private static final String                     UDF                 = JDBCASAProfileMessages
                                                                                .getString("udf.folder.name");
    private static final String                     VIEW                = ResourceLoader.INSTANCE
                                                                                .queryString("DATATOOLS.SERVER.UI.EXPLORER.VIEW");            //$NON-NLS-1$
    private static final String                     PARAMETERS_FOLDER   = Messages.getString("Parameters_folder_name");

    private static final String                     PROXY_TABLE_FOLDER  = JDBCASAProfileMessages
                                                                                .getString("ProxyTable.folder.name");
    private static final String                     INDEX               = ResourceLoader.INSTANCE
    																			.queryString("DATATOOLS.SERVER.UI.EXPLORER.INDEX");           //$NON-NLS-1$
    

    private static final Object[] EMPTY_ELEMENT_ARRAY = new Object[0];
 
    private CommonViewer viewer;

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] load(Object parentElement)
    {
        String databaseName = null;
        boolean isShowSchema = false;
        boolean isShowOwner = true;        
        // if parentElement is Subgrouped node, databaseName need to be set here.
        if (parentElement instanceof IVirtualNode)
        {
            Object parent = ((IVirtualNode) parentElement).getParent();
            if (parent instanceof Schema)
            {
                databaseName = ((Schema) parent).getDatabase().getName();
            }
            else if (parent instanceof SybaseASABaseDatabase)
            {
                databaseName = ((SybaseASABaseDatabase) parent).getName();
            }
        }

        isShowSchema = DSEUtil.checkIsShowSchema(parentElement);
        isShowOwner = DSEUtil.checkIsShowOwner(parentElement);
        if (parentElement != null)
        {
            if (parentElement instanceof Schema)
            {
                return new Object[]
                {
                		new SybaseASATableNode(TABLE, TABLE, parentElement), 
                        new SybaseASAViewNode(VIEW, VIEW, parentElement),
                        new ProxyTableNode(PROXY_TABLE_FOLDER, PROXY_TABLE_FOLDER, parentElement),
                        new SybaseASAStoredProcedureNode(STORED_PROCEDURE, STORED_PROCEDURE, parentElement),
                        new SybaseASAUDFNode(UDF, UDF, parentElement), 
                        new SybaseASAUDTNode(UDT, UDT, parentElement),
                };
            }
            else if (parentElement instanceof SybaseASABaseDatabase)
            {
                databaseName = ((SybaseASABaseDatabase) parentElement).getName();
//                this.checkIsShowSchema(databaseName, parentElement);
//                this.checkIsShowOwner(databaseName, parentElement);
                if (isShowSchema)
                {
                    return new Object[]
                    {
                        new SchemaNode(SCHEMA, SCHEMA, parentElement),
                        loadDataTypes((SybaseASABaseDatabase) parentElement),
                        loadEvents((SybaseASABaseDatabase) parentElement)
                    };
                }
                else
                {
                    return new Object[]
                    {
                        new SybaseASATableNode(TABLE, TABLE, parentElement), 
                        new SybaseASAViewNode(VIEW, VIEW, parentElement),
                        new ProxyTableNode(PROXY_TABLE_FOLDER, PROXY_TABLE_FOLDER, parentElement),
                        new SybaseASAStoredProcedureNode(STORED_PROCEDURE, STORED_PROCEDURE, parentElement),
                        new SybaseASAUDFNode(UDF, UDF, parentElement), 
                        new SybaseASAUDTNode(UDT, UDT, parentElement),
                        loadDataTypes((SybaseASABaseDatabase) parentElement),
                        loadEvents((SybaseASABaseDatabase) parentElement)
                    };
                }
            }
            else if (parentElement instanceof DataTypesFolder)
            {
                DataTypesFolder dtf = (DataTypesFolder) parentElement;
                SybaseASABaseDatabase sad = (SybaseASABaseDatabase) dtf.getParent();
                return sad.getDataTypes().toArray();
            }
            else if (parentElement instanceof TableNode)
            {
                TableNode tableNode = (TableNode) parentElement;
                SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(ConnectionFilter.TABLE_FILTER);
                if (isShowSchema)
                {
                    SybaseASABaseSchema schema = (SybaseASABaseSchema) tableNode.getParent();
                    ConnectionFilter filter = provider.getConnectionFilter(schema);

                    List dspTables = new ArrayList();
                    boolean isShowAll = ShowSysTableUtil.isShowSysTableOn(schema);
                    if (isShowAll)
                    {
                    	dspTables.addAll( schema.getSystemAndNormalTables() );
                    }
                    else
                    {
                    	dspTables.addAll(schema.getNormalTables());
                    }
                    dspTables.addAll(schema.getTempTables());
                    return getFilteredObjects(filter, dspTables).toArray();
                }
                else
                {
                    SybaseASABaseDatabase database = (SybaseASABaseDatabase) tableNode.getParent();
                    List tables = new ArrayList();
                    List schemas = database.getSchemas();
                    List dspTables = new ArrayList();
                    for (Iterator iterator = schemas.iterator(); iterator
							.hasNext();) 
                    {
						SybaseASABaseSchema schema = (SybaseASABaseSchema) iterator.next();
						ConnectionFilter filter = provider.getConnectionFilter(schema);

                        boolean isShowAll = ShowSysTableUtil.isShowSysTableOn(schema);
                        if (isShowAll)
                        {
                        	dspTables.addAll(schema.getSystemAndNormalTables());
                        }
                        else
                        {
                        	dspTables.addAll(schema.getNormalTables());
                        }
                        dspTables.addAll(schema.getTempTables());
                        tables.addAll(getFilteredObjects(filter, dspTables));
                        dspTables.clear();
					}

                    return tables.toArray();
                }
            }
            else if (parentElement instanceof ProxyTableNode)
            {
                ProxyTableNode tableNode = (ProxyTableNode) parentElement;
                SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(
                        ConnectionFilter.REMOTE_TABLE_FILTER);
                if (isShowSchema)
                {
                    SybaseASABaseSchema schema = (SybaseASABaseSchema) tableNode.getParent();
                    ConnectionFilter filter = provider.getConnectionFilter(schema);
                    return getFilteredObjects(filter, schema.getProxyTables()).toArray();
                }
                else
                {
                    SybaseASABaseDatabase database = (SybaseASABaseDatabase) tableNode.getParent();
                    List proxyTables = new ArrayList();
                    List schemas = database.getSchemas();
                    for (Iterator iterator = schemas.iterator(); iterator
							.hasNext();) {
						SybaseASABaseSchema schema = (SybaseASABaseSchema) iterator.next();
						ConnectionFilter filter = provider.getConnectionFilter(schema);
						proxyTables.addAll(getFilteredObjects(filter, schema.getProxyTables())); 
					}

                    return proxyTables.toArray();
                }
            }
            else if (parentElement instanceof ViewNode)
            {
                ViewNode tableNode = (ViewNode) parentElement;
                SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(ConnectionFilter.VIEW_FILTER);
                if (isShowSchema)
                {
                    SybaseASABaseSchema schema = (SybaseASABaseSchema) tableNode.getParent();
                    ConnectionFilter filter = provider.getConnectionFilter(schema);
                    return getFilteredObjects(filter, schema.getViewTables(true)).toArray();
                }
                else
                {
                    SybaseASABaseDatabase database = (SybaseASABaseDatabase) tableNode.getParent();
                    List views = new ArrayList();
                    List schemas = database.getSchemas();
                    for (Iterator iterator = schemas.iterator(); iterator
							.hasNext();) {
						SybaseASABaseSchema schema = (SybaseASABaseSchema) iterator.next();
						ConnectionFilter filter = provider.getConnectionFilter(schema);
						views.addAll(getFilteredObjects(filter, schema.getViewTables(true))); 
					}

                    return views.toArray();
                }
            }
            else if (parentElement instanceof StoredProcedureNode)
            {
                StoredProcedureNode spNode = (StoredProcedureNode) parentElement;
                SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(
                        ConnectionFilter.STORED_PROCEDURE_FILTER);
                if (isShowSchema)
                {
                    SybaseASABaseSchema schema = (SybaseASABaseSchema) spNode.getParent();
                    ConnectionFilter filter = provider.getConnectionFilter(schema);
                    return getFilteredObjects(filter, schema.getProcedures()).toArray();
                    
                }
                else
                {
                    SybaseASABaseDatabase database = (SybaseASABaseDatabase) spNode.getParent();
                    List sps = new ArrayList();
                    List schemas = database.getSchemas();
                    for (Iterator iterator = schemas.iterator(); iterator
							.hasNext();) {
						SybaseASABaseSchema schema = (SybaseASABaseSchema) iterator.next();
						ConnectionFilter filter = provider.getConnectionFilter(schema);
						sps.addAll(getFilteredObjects(filter,schema.getProcedures())); 
					}
                    return sps.toArray();
                }
            }
            else if (parentElement instanceof UDFNode)
            {
                UDFNode udfNode = (UDFNode) parentElement;
                SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(
                        ConnectionFilter.USER_DEFINED_FUNCTION_FILTER);
                if (isShowSchema)
                {
                    SybaseASABaseSchema schema = (SybaseASABaseSchema) udfNode.getParent();
                    ConnectionFilter filter = provider.getConnectionFilter(schema);
                    return getFilteredObjects(filter, schema.getUDFs()).toArray();
                }
                else
                {
                    SybaseASABaseDatabase database = (SybaseASABaseDatabase) udfNode.getParent();
                    List udfs = new ArrayList();
                    List schemas = database.getSchemas();
                    for (Iterator iterator = schemas.iterator(); iterator
							.hasNext();) {
						SybaseASABaseSchema schema = (SybaseASABaseSchema) iterator.next();
						ConnectionFilter filter = provider.getConnectionFilter(schema);
						udfs.addAll(getFilteredObjects(filter, schema.getUDFs())); 
					}
                    
                    return udfs.toArray();
                }
            }
            else if (parentElement instanceof UDTNode)
            {
                UDTNode spNode = (UDTNode) parentElement;
                SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(
                        ConnectionFilter.USER_DEFINED_TYPE_FILTER);
                if (isShowSchema)
                {
                    SybaseASABaseSchema schema = (SybaseASABaseSchema) spNode.getParent();
                    ConnectionFilter filter = provider.getConnectionFilter(schema);
                    return getFilteredObjects(filter,schema.getUserDefinedTypes()).toArray();
                }
                else
                {
                    SybaseASABaseDatabase database = (SybaseASABaseDatabase) spNode.getParent();
                    List udts = new ArrayList();
                    List schemas = database.getSchemas();
                    for (Iterator iterator = schemas.iterator(); iterator
							.hasNext();) {
						SybaseASABaseSchema schema = (SybaseASABaseSchema) iterator.next();
						ConnectionFilter filter = provider.getConnectionFilter(schema);
						udts.addAll(getFilteredObjects(filter, schema.getUserDefinedTypes())); 
					}

                    return udts.toArray();
                }
            }
            else if (parentElement instanceof WebServicesFolder)
            {
                WebServicesFolder wsf = (WebServicesFolder) parentElement;
                SybaseASABaseDatabase sad = (SybaseASABaseDatabase) wsf.getParent();
                return sad.getWebServices().toArray();
            }
            else if (parentElement instanceof DBEventsFolder)
            {
                DBEventsFolder dbef = (DBEventsFolder) parentElement;
                SybaseASABaseDatabase sad = (SybaseASABaseDatabase) dbef.getParent();

                EventFilterProvider provider = new EventFilterProvider();
                ConnectionFilter filter = provider.getConnectionFilter((Catalog) sad.getCatalogs().get(0));

                return getFilteredObjects(filter, sad.getEvents()).toArray();
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
//            else if (parentElement instanceof ViewTable)
//            {
//                return DSEContentProviderUtil.getViewTableChildren(parentElement);
//            }
            else if (parentElement instanceof SybaseASABaseProxyTable)
            {
            	List children = new ArrayList(Arrays.asList(DSEContentProviderUtil.getProxyTableChildren(parentElement)));;
            	
            	children.add(new SybaseASAIndexNode(INDEX, INDEX, parentElement));
            	children.add(new SybaseASAPrimaryKeyNode(DSEContentProviderUtil.PRIMARY_KEY_FOLDER,DSEContentProviderUtil.PRIMARY_KEY_FOLDER,parentElement));
            	children.add(new SybaseASAUniqueConstraintNode(DSEContentProviderUtil.UNIQUE_CONSTRAINT_FOLDER, DSEContentProviderUtil.UNIQUE_CONSTRAINT_FOLDER, parentElement));
            	children.add(new SybaseASACheckConstraintNode(DSEContentProviderUtil.CHECK_CONSTRAINT_FOLDER, DSEContentProviderUtil.CHECK_CONSTRAINT_FOLDER, parentElement));
            	children.add(new SybaseASAForeignKeyNode(DSEContentProviderUtil.FOREIGN_KEY_FOLDER, DSEContentProviderUtil.FOREIGN_KEY_FOLDER, parentElement));
            	return children.toArray();
            }
            else if (parentElement instanceof Table)
            {
            	List children = new ArrayList(Arrays.asList(DSEContentProviderUtil.getTableChildren(parentElement)));
            	children.add(new SybaseASAIndexNode(INDEX, INDEX, parentElement));
            	children.add(new SybaseASAPrimaryKeyNode(DSEContentProviderUtil.PRIMARY_KEY_FOLDER,DSEContentProviderUtil.PRIMARY_KEY_FOLDER,parentElement));
            	children.add(new SybaseASAUniqueConstraintNode(DSEContentProviderUtil.UNIQUE_CONSTRAINT_FOLDER, DSEContentProviderUtil.UNIQUE_CONSTRAINT_FOLDER, parentElement));
            	children.add(new SybaseASACheckConstraintNode(DSEContentProviderUtil.CHECK_CONSTRAINT_FOLDER, DSEContentProviderUtil.CHECK_CONSTRAINT_FOLDER, parentElement));
            	children.add(new SybaseASAForeignKeyNode(DSEContentProviderUtil.FOREIGN_KEY_FOLDER, DSEContentProviderUtil.FOREIGN_KEY_FOLDER, parentElement));
            	return children.toArray();
            }
            else if (parentElement instanceof PrimaryKeyNode || parentElement instanceof UniqueConstraintNode
                    || parentElement instanceof CheckConstraintNode || parentElement instanceof ForeignKeyNode)
            {
            	return DSEContentProviderUtil.getConstraintNodeChildren(parentElement);
            }
            else if (parentElement instanceof Procedure || parentElement instanceof UserDefinedFunction)
            {
                return new Object[]
                {
                    new ParametersNode(PARAMETERS_FOLDER, PARAMETERS_FOLDER, parentElement)
                };
            }
            else if (parentElement instanceof ParametersNode)
            {
                ParametersNode parametersNode = (ParametersNode) parentElement;
                Routine routine = (Routine) parametersNode.getParent();
                EList parameters = routine.getParameters();
                List result = new ArrayList();
                for (int i = 0; i < parameters.size(); i++)
                {
                	Object obj = parameters.get(i);
                    if (!(obj instanceof SybaseASABaseParameter) || 
                    		((SybaseASABaseParameter)obj).getParmType().getName().equals(ParameterType.RESULT_LITERAL.getName()))
                    {
                        continue;
                    }
                    result.add(obj);
                }
                return result.toArray();
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
//            else if (parentElement instanceof TriggerNode)
//            {
//                BaseTable table = (BaseTable) ((IVirtualNode) parentElement).getParent();
//                return table.getTriggers().toArray();
//            }
        }
        return EMPTY_ELEMENT_ARRAY;
    }

    private List getFilteredObjects(ConnectionFilter filter, List oldList)
    {
        List result = new ArrayList();
        for (Iterator iterator = oldList.iterator(); iterator.hasNext();)
        {
            SQLObject sqlObj = (SQLObject) iterator.next();
            if (filter == null || !filter.isFiltered(sqlObj.getName()))
            {
                result.add(sqlObj);
            }
        }
        return result;
    }

    private IWebServicesFolder loadWebServices(SybaseASABaseDatabase db)
    {
        IWebServicesFolder folder = factory.makeWebServicesFolder(WEB_SERVICES_FOLDER, WEB_SERVICES_FOLDER, db);
        if (db instanceof SybaseASADatabase)
        {
            SybaseASADatabase asaDatabase = (SybaseASADatabase) db;
            EList list = asaDatabase.getWebServices();
            for (int i = 0, n = list.size(); i < n; i++)
            {
                Object current = list.get(i);
                if (current instanceof SybaseASAWebService)
                {
                    String id = containmentService.getGroupId((EObject) current);
                    if (id != null && id.equals(folder.getGroupID()))
                    {
                        folder.addChildren(current);
                    }
                }
            }
        }
        else
        {
            EList list = db.getWebServices();
            for (int i = 0, n = list.size(); i < n; i++)
            {
                Object current = list.get(i);
                if (current instanceof SybaseASAWebService)
                {
                    String id = containmentService.getGroupId((EObject) current);
                    if (id != null && id.equals(folder.getGroupID()))
                    {
                        folder.addChildren(current);
                    }
                }
            }
        }
        return folder;
    }

    private IDataTypesFolder loadDataTypes(SybaseASABaseDatabase db)
    {
        IDataTypesFolder folder = factory.makeDataTypesFolder(DATA_TYPES_FOLDER, DATA_TYPES_FOLDER, db);
        if (db instanceof SybaseASADatabase)
        {
            SybaseASABaseDatabase asaDatabase = (SybaseASABaseDatabase) db;
            EList list = asaDatabase.getDataTypes();
            for (int i = 0, n = list.size(); i < n; i++)
            {
                Object current = list.get(i);
                if (current instanceof SybaseASABasePredefinedDataType)
                {
                    String id = containmentService.getGroupId((EObject) current);
                    if (id != null && id.equals(folder.getGroupID()))
                    {
                        folder.addChildren(current);
                    }
                }
            }
        }
        else
        {
            EList list = db.getDataTypes();
            for (int i = 0, n = list.size(); i < n; i++)
            {
                Object current = list.get(i);
                if (current instanceof SybaseASABasePredefinedDataType)
                {
                    String id = containmentService.getGroupId((EObject) current);
                    if (id != null && id.equals(folder.getGroupID()))
                    {
                        folder.addChildren(current);
                    }
                }
            }
        }
        return folder;
    }

    private IDBEventsFolder loadEvents(SybaseASABaseDatabase db)
    {
        IDBEventsFolder folder = factory.makeDBEventsFolder(EVENTS_FOLDER, EVENTS_FOLDER, db);
        if (db instanceof SybaseASADatabase)
        {
            SybaseASABaseDatabase asaDB = (SybaseASABaseDatabase) db;
            EList list = asaDB.getEvents();
            for (int i = 0, n = list.size(); i < n; i++)
            {
                Object current = list.get(i);
                if (current instanceof SybaseASABaseEvent)
                {
                    String id = containmentService.getGroupId((EObject) current);
                    if (id != null && id.equals(folder.getGroupID()))
                    {
                        folder.addChildren(current);
                    }
                }
            }
        }
        else
        {
            EList list = db.getEvents();
            for (int i = 0, n = list.size(); i < n; i++)
            {
                Object current = list.get(i);
                if (current instanceof SybaseASABaseEvent)
                {
                    String id = containmentService.getGroupId((EObject) current);
                    if (id != null && id.equals(folder.getGroupID()))
                    {
                        folder.addChildren(current);
                    }
                }
            }
        }
        return folder;
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
        if (element instanceof SybaseParameter)
        {
            return false;
        }
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
        if (element instanceof UserDefinedType)
        {
            return false;
        }
        if (element instanceof PredefinedDataType)
        {
            return false;
        }
        if (element instanceof Event)
        {
            return false;
        }
        // to avoid dead loop
//        if (element instanceof IVirtualNode && ((IVirtualNode) element).getParent() instanceof Table)
//        {
//            return load(element).length > 0;
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

	public void getPipelinedChildren(Object parent, Set theCurrentChildren) 
	{
		theCurrentChildren.clear();
		Object[] children = getChildren(parent);
		for(int i=0; i< children.length; i++)
		{
			theCurrentChildren.add(children[i]);
		}		
	}

	public void getPipelinedElements(Object anInput, Set theCurrentElements) {
		// TODO Auto-generated method stub
		
	}

	public Object getPipelinedParent(Object anObject, Object suggestedParent) {
		// TODO Auto-generated method stub
		return null;
	}

	public PipelinedShapeModification interceptAdd(
			PipelinedShapeModification anAddModification) 
	{
		Object parent = anAddModification.getParent();
		
		if(parent instanceof EObject || (parent instanceof IVirtualNode && ((IVirtualNode)parent).getParent() instanceof EObject))
		{
			EObject eObj = parent instanceof EObject ? (EObject)parent : (EObject)((IVirtualNode)parent).getParent();
			
			Database database = DSEUtil.findDatabaseByChild(eObj);
			if(!(database instanceof SybaseASABaseDatabase))
			{
				return anAddModification;
			}
		}
		
		List removing = new ArrayList();
		Set children = anAddModification.getChildren();
		for (Iterator iterator = children.iterator(); iterator.hasNext();) 
		{
			Object obj = (Object) iterator.next();
			if(obj instanceof VirtualNode)
			{
				String groupId = ((VirtualNode)obj).getGroupID();
				if(groupId.equals(GroupID.PROCEDURE)||groupId.equals(GroupID.VIEW)
						||groupId.equals(GroupID.TABLE)||groupId.equals(GroupID.USER_DEFINED_TYPE)||groupId.equals(GroupID.ROLE)
						||groupId.equals(GroupID.FUNCTION) || groupId.equals(GroupID.DEPENDENCY) || groupId.equals(GroupID.CONSTRAINT)
						||groupId.equals(GroupID.INDEX))
				{
					removing.add(obj);					
				}
			}
		}
		anAddModification.getChildren().removeAll(removing);
		return anAddModification;
	}

	public boolean interceptRefresh(PipelinedViewerUpdate refreshSynchronization) {
		// TODO Auto-generated method stub
		return false;
	}

	public PipelinedShapeModification interceptRemove(
			PipelinedShapeModification removeModification) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean interceptUpdate(PipelinedViewerUpdate anUpdateSynchronization) {
		// TODO Auto-generated method stub
		return false;
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) 
	{
		if (viewer instanceof CommonViewer && this.viewer == null)
    	{
    		this.viewer = (CommonViewer) viewer;
    	}		
	}

	public Object[] getChildren(Object parentElement) 
	{
		return new Loading ().getChildren(this.viewer, parentElement, this);
//		return load(parentElement);
	}

	private static final ResourceLoader resourceLoader = ResourceLoader.INSTANCE;

    private static final String DESCRIPTION = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.DESCRIPTION"); //$NON-NLS-1$
	
	public String getLoadingDescription() 
	{
		return DESCRIPTION;
	}


}
