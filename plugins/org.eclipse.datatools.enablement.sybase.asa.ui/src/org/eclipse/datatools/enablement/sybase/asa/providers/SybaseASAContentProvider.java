package org.eclipse.datatools.enablement.sybase.asa.providers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.IndexNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.SchemaNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.StoredProcedureNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.TableNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.TriggerNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.UDTNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.impl.ServerExplorerContentProviderNav;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.enablement.sybase.Messages;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAProfileMessages;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseSchema;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase;
import org.eclipse.datatools.enablement.sybase.asa.virtual.DBEventsFolder;
import org.eclipse.datatools.enablement.sybase.asa.virtual.DataTypesFolder;
import org.eclipse.datatools.enablement.sybase.asa.virtual.ProxyTableNode;
import org.eclipse.datatools.enablement.sybase.asa.virtual.WebServicesFolder;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.ui.filter.EventFilterProvider;
import org.eclipse.datatools.enablement.sybase.ui.filter.SchemaFilterProvider;
import org.eclipse.datatools.enablement.sybase.ui.filter.SchemaObjectFilterProvider;
import org.eclipse.datatools.enablement.sybase.util.DSEContentProviderUtil;
import org.eclipse.datatools.enablement.sybase.util.DSEUtil;
import org.eclipse.datatools.enablement.sybase.util.ShowSysTableUtil;
import org.eclipse.datatools.enablement.sybase.virtual.CheckConstraintNode;
import org.eclipse.datatools.enablement.sybase.virtual.ForeignKeyNode;
import org.eclipse.datatools.enablement.sybase.virtual.ParametersNode;
import org.eclipse.datatools.enablement.sybase.virtual.PrimaryKeyNode;
import org.eclipse.datatools.enablement.sybase.virtual.SybaseUDFNode;
import org.eclipse.datatools.enablement.sybase.virtual.SybaseViewNode;
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
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

public class SybaseASAContentProvider extends ServerExplorerContentProviderNav implements ICommonContentProvider
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

//    private ServerExplorerViewer                    viewer;  

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement)
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
                    new TableNode(TABLE, TABLE, parentElement), new SybaseViewNode(VIEW, VIEW, parentElement),
                    new ProxyTableNode(PROXY_TABLE_FOLDER, PROXY_TABLE_FOLDER, parentElement),
                    new StoredProcedureNode(STORED_PROCEDURE, STORED_PROCEDURE, parentElement),
                    new SybaseUDFNode(UDF, UDF, parentElement), new UDTNode(UDT, UDT, parentElement)
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
                        new TableNode(TABLE, TABLE, parentElement), new SybaseViewNode(VIEW, VIEW, parentElement),
                        new ProxyTableNode(PROXY_TABLE_FOLDER, PROXY_TABLE_FOLDER, parentElement),
                        new StoredProcedureNode(STORED_PROCEDURE, STORED_PROCEDURE, parentElement),
                        new SybaseUDFNode(UDF, UDF, parentElement), new UDTNode(UDT, UDT, parentElement),
                        loadDataTypes((SybaseASABaseDatabase) parentElement),
                        loadEvents((SybaseASABaseDatabase) parentElement)
                    };
                }
            }
            else if (parentElement instanceof SchemaNode)
            {
                SchemaNode sn = (SchemaNode) parentElement;
                SybaseASABaseDatabase sac = (SybaseASABaseDatabase) sn.getParent();
                SchemaFilterProvider provider = new SchemaFilterProvider();
                ConnectionFilter filter = provider.getConnectionFilter((Catalog) sac.getCatalogs().get(0));
                List<Schema> schemas = sac.getSchemas();
                if (sn.getChildrenArray().length == 0)
                {
                    sn.addChildren(getFilteredObjects(filter, schemas));
                }
                return sn.getChildrenArray();
            }
            else if (parentElement instanceof DataTypesFolder)
            {
                DataTypesFolder dtf = (DataTypesFolder) parentElement;
                SybaseASABaseDatabase sad = (SybaseASABaseDatabase) dtf.getParent();
                if (dtf.getChildrenArray().length == 0)
                {
                    dtf.addChildren(sad.getDataTypes());
                }
                return dtf.getChildrenArray();
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
                        dspTables.addAll(this.appendOwnerToLabel(schema.getOwner().getName(), schema
                                .getSystemAndNormalTables(),isShowOwner));
                    }
                    else
                    {
                        dspTables
                                .addAll(this.appendOwnerToLabel(schema.getOwner().getName(), schema.getNormalTables(), isShowOwner));
                    }
                    dspTables.addAll(this.appendOwnerToLabel(schema.getOwner().getName(), schema.getTempTables(), isShowOwner));
                    return getFilteredObjects(filter, dspTables).toArray();
                }
                else
                {
                    SybaseASABaseDatabase database = (SybaseASABaseDatabase) tableNode.getParent();
                    List tables = new ArrayList();
                    List<SybaseASABaseSchema> schemas = database.getSchemas();
                    List dspTables = new ArrayList();
                    for (SybaseASABaseSchema schema : schemas)
                    {
                        ConnectionFilter filter = provider.getConnectionFilter(schema);

                        boolean isShowAll = ShowSysTableUtil.isShowSysTableOn(schema);
                        if (isShowAll)
                        {
                            dspTables.addAll(this.appendOwnerToLabel(schema.getOwner().getName(), schema
                                    .getSystemAndNormalTables(), isShowOwner));
                        }
                        else
                        {
                            dspTables.addAll(this.appendOwnerToLabel(schema.getOwner().getName(), schema
                                    .getNormalTables(), isShowOwner));
                        }
                        dspTables.addAll(this.appendOwnerToLabel(schema.getOwner().getName(), schema.getTempTables(), isShowOwner));
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
                    return getFilteredObjects(filter,
                            this.appendOwnerToLabel(schema.getOwner().getName(), schema.getProxyTables(), isShowOwner)).toArray();
                }
                else
                {
                    SybaseASABaseDatabase database = (SybaseASABaseDatabase) tableNode.getParent();
                    List proxyTables = new ArrayList();
                    List<SybaseASABaseSchema> schemas = database.getSchemas();
                    for (SybaseASABaseSchema schema : schemas)
                    {
                        ConnectionFilter filter = provider.getConnectionFilter(schema);
                        proxyTables.addAll(getFilteredObjects(filter, this.appendOwnerToLabel(schema.getOwner()
                                .getName(), schema.getProxyTables(), isShowOwner)));
                    }
                    return proxyTables.toArray();
                }
            }
            else if (parentElement instanceof SybaseViewNode)
            {
                SybaseViewNode tableNode = (SybaseViewNode) parentElement;
                SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(ConnectionFilter.VIEW_FILTER);
                if (isShowSchema)
                {
                    SybaseASABaseSchema schema = (SybaseASABaseSchema) tableNode.getParent();
                    ConnectionFilter filter = provider.getConnectionFilter(schema);
                    return getFilteredObjects(filter,
                            this.appendOwnerToLabel(schema.getOwner().getName(), schema.getViewTables(true), isShowOwner)).toArray();
                }
                else
                {
                    SybaseASABaseDatabase database = (SybaseASABaseDatabase) tableNode.getParent();
                    List views = new ArrayList();
                    List<SybaseASABaseSchema> schemas = database.getSchemas();
                    for (SybaseASABaseSchema schema : schemas)
                    {
                        ConnectionFilter filter = provider.getConnectionFilter(schema);
                        views.addAll(getFilteredObjects(filter, this.appendOwnerToLabel(schema.getOwner().getName(),
                                schema.getViewTables(true), isShowOwner)));
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
                    return getFilteredObjects(filter,
                            this.appendOwnerToLabel(schema.getOwner().getName(), schema.getProcedures(), isShowOwner)).toArray();
                }
                else
                {
                    SybaseASABaseDatabase database = (SybaseASABaseDatabase) spNode.getParent();
                    List sps = new ArrayList();
                    List<SybaseASABaseSchema> schemas = database.getSchemas();
                    for (SybaseASABaseSchema schema : schemas)
                    {
                        ConnectionFilter filter = provider.getConnectionFilter(schema);
                        sps.addAll(getFilteredObjects(filter, this.appendOwnerToLabel(schema.getOwner().getName(),
                                schema.getProcedures(), isShowOwner)));
                    }
                    return sps.toArray();
                }
            }
            else if (parentElement instanceof SybaseUDFNode)
            {
                SybaseUDFNode udfNode = (SybaseUDFNode) parentElement;
                SchemaObjectFilterProvider provider = new SchemaObjectFilterProvider(
                        ConnectionFilter.USER_DEFINED_FUNCTION_FILTER);
                if (isShowSchema)
                {
                    SybaseASABaseSchema schema = (SybaseASABaseSchema) udfNode.getParent();
                    ConnectionFilter filter = provider.getConnectionFilter(schema);
                    return getFilteredObjects(filter,
                            this.appendOwnerToLabel(schema.getOwner().getName(), schema.getUDFs(), isShowOwner)).toArray();
                }
                else
                {
                    SybaseASABaseDatabase database = (SybaseASABaseDatabase) udfNode.getParent();
                    List udfs = new ArrayList();
                    List<SybaseASABaseSchema> schemas = database.getSchemas();
                    for (SybaseASABaseSchema schema : schemas)
                    {
                        ConnectionFilter filter = provider.getConnectionFilter(schema);
                        udfs.addAll(getFilteredObjects(filter, this.appendOwnerToLabel(schema.getOwner().getName(),
                                schema.getUDFs(), isShowOwner)));
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
                    return getFilteredObjects(filter,
                            this.appendOwnerToLabel(schema.getOwner().getName(), schema.getUserDefinedTypes(), isShowOwner))
                            .toArray();
                }
                else
                {
                    SybaseASABaseDatabase database = (SybaseASABaseDatabase) spNode.getParent();
                    List udts = new ArrayList();
                    List<SybaseASABaseSchema> schemas = database.getSchemas();
                    for (SybaseASABaseSchema schema : schemas)
                    {
                        ConnectionFilter filter = provider.getConnectionFilter(schema);
                        udts.addAll(getFilteredObjects(filter, this.appendOwnerToLabel(schema.getOwner().getName(),
                                schema.getUserDefinedTypes(), isShowOwner)));
                    }
                    return udts.toArray();
                }
            }
            else if (parentElement instanceof WebServicesFolder)
            {
                WebServicesFolder wsf = (WebServicesFolder) parentElement;
                SybaseASABaseDatabase sad = (SybaseASABaseDatabase) wsf.getParent();
                if (wsf.getChildrenArray().length == 0)
                {
                    wsf.addChildren(sad.getWebServices());
                }
                return wsf.getChildrenArray();
            }
            else if (parentElement instanceof DBEventsFolder)
            {
                DBEventsFolder dbef = (DBEventsFolder) parentElement;
                SybaseASABaseDatabase sad = (SybaseASABaseDatabase) dbef.getParent();

                EventFilterProvider provider = new EventFilterProvider();
                ConnectionFilter filter = provider.getConnectionFilter((Catalog) sad.getCatalogs().get(0));

                // if (dbef.getChildrenArray().length == 0)
                {
                    dbef.addChildren(getFilteredObjects(filter, sad.getEvents()));
                }
                return dbef.getChildrenArray();
            }
            else if (parentElement instanceof IndexNode)
            {
                IndexNode indexNode = (IndexNode) parentElement;
                BaseTable table = (BaseTable) indexNode.getParent();
                if (indexNode.getChildrenArray().length == 0)
                {
                    List indices = table.getIndex();
                    for (int i = 0; i < indices.size(); i++)
                    {
                        Index index = (Index) indices.get(i);
                        if (!index.isSystemGenerated())
                            indexNode.addChildren(index);
                    }
                }
                return this.appendOwnerToLabel(table.getSchema().getName(),
                        convertArrayToSQLObjectList(indexNode.getChildrenArray()),isShowOwner).toArray();
            }
            else if (parentElement instanceof ViewTable)
            {
                return DSEContentProviderUtil.getViewTableChildren(parentElement);
            }
            else if (parentElement instanceof Table)
            {
                return DSEContentProviderUtil.getTableChildren(parentElement);
            }
            else if (parentElement instanceof PrimaryKeyNode || parentElement instanceof UniqueConstraintNode
                    || parentElement instanceof CheckConstraintNode || parentElement instanceof ForeignKeyNode)
            {
                BaseTable table = (BaseTable) ((IVirtualNode) parentElement).getParent();
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
                    SybaseASABaseParameter obj = (SybaseASABaseParameter) parameters.get(i);
                    if (obj.getParmType().getName().equals(ParameterType.RESULT_LITERAL.getName()))
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
            else if (parentElement instanceof TriggerNode)
            {
                // if(((IVirtualNode)parentElement).getParent() instanceof BaseTable){
                BaseTable table = (BaseTable) ((IVirtualNode) parentElement).getParent();
                return this.appendOwnerToLabel(table.getSchema().getOwner().getName(),
                        convertArrayToSQLObjectList(super.getChildren(parentElement)),isShowOwner).toArray();
                // }
            }
        }
        return super.getChildren(parentElement);
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
     * @see org.eclipse.ui.views.navigator.INavigatorContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
//    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
//    {
//        if (viewer instanceof ServerExplorerViewer && this.viewer == null)
//        {
//            this.viewer = (ServerExplorerViewer) viewer;
//        }
//    }

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
        if (element instanceof IVirtualNode && ((IVirtualNode) element).getParent() instanceof Table)
        {
            return getChildren(element).length > 0;
        }
        return super.hasChildren(element);
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

    protected List<SQLObject> appendOwnerToLabel(String owner, List<SQLObject> sqlObjects, boolean isShowOwner)
    {
        return DSEContentProviderUtil.appendOwnerToLabel(sqlObjects, owner, isShowOwner);
    }

    protected List<SQLObject> convertArrayToSQLObjectList(Object[] objs)
    {
        if (objs == null)
            return new ArrayList<SQLObject>();
        List<SQLObject> sqlObjs = new ArrayList<SQLObject>(objs.length);
        for (int i = 0; i < objs.length; i++)
        {
            sqlObjs.add((SQLObject) objs[i]);
        }
        return sqlObjs;
    }

}
