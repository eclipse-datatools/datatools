package org.eclipse.datatools.enablement.sybase.ddl;

import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlBuilder;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.enablement.sybase.parser.QuickSQLParser;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

public abstract class SybaseDdlBuilder extends GenericDdlBuilder implements ISybaseDdlConstants
{
    public String[] createTable(BaseTable table, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        if (table instanceof TemporaryTable)
        {
            return createTempoeryTable((TemporaryTable) table, quoteIdentifiers, qualifyNames, fullSyntax);
        }
        else if (table instanceof PersistentTable)
        {
            return createPersistentTable((PersistentTable) table, quoteIdentifiers, qualifyNames, fullSyntax);
        }
        else
        {
            throw new UnsupportedOperationException();
        }
    }

    abstract protected String[] createTempoeryTable(TemporaryTable table, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax);

    abstract protected String[] createPersistentTable(PersistentTable table, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax);

    abstract public String[] createView(ViewTable table, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax);

    abstract public String[] createEvent(Event event, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax);

    abstract public String[] createProcedure(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax);

    abstract public String[] createUserDefinedFunction(UserDefinedFunction function, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax);

    abstract public String[] createTrigger(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax);

    abstract public String[] createDatabase(Database database, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax);

    abstract public String[] createCatalogs(Catalog catalog, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax);

    abstract public String[] createUserDefinedType(UserDefinedType udt, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax);

    abstract public String[] createAuthorizationId(AuthorizationIdentifier authId, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax);

    abstract public String[] createColumn(Column col, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax);

    abstract public String[] addUniqueConstraint(UniqueConstraint constraint, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax);

    abstract public String[] addForeignKey(ForeignKey foreignKey, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax);

    abstract public String dropForeignKey(ForeignKey foreignKey, boolean quoteIdentifiers, boolean qualifyNames);

    abstract public String dropCheckConstraint(CheckConstraint constraint, boolean quoteIdentifiers,
            boolean qualifyNames);

    abstract public String dropUniqueConstraint(UniqueConstraint constraint, boolean quoteIdentifiers,
            boolean qualifyNames);

    abstract public String dropUserDefinedType(UserDefinedType udt, boolean quoteIdentifiers, boolean qualifyNames);

    abstract public String dropAuthorizationId(AuthorizationIdentifier authId, boolean quoteIdentifiers,
            boolean qualifyNames);
    
    /**
     * <pre>
     *     DROP VIEW
     *     [ owner.]view-name 
     * </pre>
     * 
     */
    public String dropView(ViewTable view, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer dropView = new StringBuffer("");
        dropView.append(DROP).append(SPACE).append(VIEW).append(SPACE).append(
                getName(view, quoteIdentifiers, qualifyNames));

        return dropView.toString();
    }

    public String dropEvent(Event event, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer sb = new StringBuffer(128);
        sb.append(DROP).append(SPACE).append(EVENT).append(SPACE)
                .append(getName(event, quoteIdentifiers, qualifyNames));
        return sb.toString();
    }

    public String dropProcedure(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer sb = new StringBuffer(128);
        sb.append(DROP).append(SPACE).append(PROCEDURE).append(SPACE).append(
                getName(procedure, quoteIdentifiers, qualifyNames));
        return sb.toString();
    }

    public String dropFunction(UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer sb = new StringBuffer(128);
        sb.append(DROP).append(SPACE).append(FUNCTION).append(SPACE).append(
                getName(function, quoteIdentifiers, qualifyNames));
        return sb.toString();
    }

    public String dropColumn(Column col, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer sb = new StringBuffer(128);
        sb.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(getName(col.getTable(), quoteIdentifiers, qualifyNames))
                .append(SPACE).append(DROP).append(SPACE).append(getName(col, quoteIdentifiers, false));
        return sb.toString();
    }

    public String dropCatalog(Catalog db, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer sb = new StringBuffer(128);
        sb.append(DROP).append(SPACE).append(DATABASE).append(SPACE)
                .append(getName(db, quoteIdentifiers, qualifyNames));
        return sb.toString();
    }
    
    public String dropDatabase(Database db, boolean quoteIdentifiers, boolean qualifyNames)
    {
        return EMPTY_STRING;
    }
    
    public String getName(ENamedElement element, boolean quoteIdentifiers, boolean qualifyNames)
    {
        if (element instanceof Event)
        {
            return getName((Event) element, quoteIdentifiers, qualifyNames);
        }
        else if (element instanceof Index)
        {
            return getName((Index) element, quoteIdentifiers, qualifyNames);
        }
        else if (element instanceof Procedure)
        {
            return getName((Procedure) element, quoteIdentifiers, qualifyNames);
        }
        else if (element instanceof UserDefinedFunction)
        {
            return getName((UserDefinedFunction) element, quoteIdentifiers, qualifyNames);
        }
        else if (element instanceof Column)
        {
            return getName((Column) element, quoteIdentifiers, qualifyNames);
        }
        else if (element instanceof Table)
        {
            return getName((Table) element, quoteIdentifiers, qualifyNames);
        }
        else if (element instanceof TableConstraint)
        {
            return getName((TableConstraint) element, quoteIdentifiers, qualifyNames);
        }
        else if (element instanceof Trigger)
        {
            return getName((Trigger) element, quoteIdentifiers, qualifyNames);
        }
        else if (element instanceof UserDefinedType)
        {
            return getName((UserDefinedType) element, quoteIdentifiers, qualifyNames);
        }
        else if (element instanceof Catalog)
        {
        	return SQLDevToolsUtil.quoteWhenNecessary(element.getName(), getDatabaseIdentifier(element));
        }
        else
        {
            if (quoteIdentifiers)
            {
                return getDoubleQuotedString(element.getName());
            }
            else
            {
                return element.getName();
            }
        }
    }

    protected String getName(TableConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames)
    {
    	String name = constraint.getName();
        if (quoteIdentifiers)
        {
        	name = this.getDoubleQuotedString(name);
        }
        return name;
    }
    
    protected String getName(TableConstraint constraint, boolean quoteIdentifiers)
    {
    	String name = constraint.getName();
        if (quoteIdentifiers)
        {
        	name = this.getDoubleQuotedString(name);
        }
        return name;
    }
    
    protected String getName(Event event, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String typeName = event.getName();

        if (quoteIdentifiers)
        {
            typeName = this.getDoubleQuotedString(typeName);
        }
        return typeName;
    }

    // For ASE, will override this method to add group number as part of name.
    protected String getName(Procedure proc, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String name = proc.getName();
        String schemaName = proc.getSchema().getName();

        if (quoteIdentifiers)
        {
            name = this.getDoubleQuotedString(name);
        }

        if (qualifyNames)
        {
            name = schemaName + DOT + name;
        }

        return name;
    }
    
    protected String getName(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames) {
        String name = trigger.getName();
        String schemaName = trigger.getSchema().getName();
    
        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
        }
        if(qualifyNames) {
            name = schemaName + DOT + name;
        }
        
        return name;
    }

    protected String getName(UserDefinedFunction proc, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String name = proc.getName();
        String schemaName = proc.getSchema().getName();

        if (quoteIdentifiers)
        {
            name = this.getDoubleQuotedString(name);
        }

        if (qualifyNames)
        {
            name = schemaName + DOT + name;
        }

        return name;
    }

    protected String getName(Index index, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String name = index.getName(); 
        name = SQLDevToolsUtil.quoteWhenNecessary(index.getName(), getDatabaseIdentifier(index));      
        return name;
    }

    protected String getName(Column col, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String name = col.getName();
        String tableName = col.getTable().getName();
        String schemaName = col.getTable().getSchema().getName();

        if (quoteIdentifiers)
        {
            name = this.getDoubleQuotedString(name);
            tableName = this.getDoubleQuotedString(tableName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }

        if (qualifyNames)
        {
            name = schemaName + DOT + tableName + DOT + name;
        }

        return name;
    }

    protected String getAddCheckConstraintClause(CheckConstraint constraint, boolean quoteIdentifiers)
    {
        StringBuffer statement = new StringBuffer(128);
        String name = constraint.getName();
        if (name != null && name.trim().length() != 0)
        {
            if (quoteIdentifiers)
            {
                name = super.getDoubleQuotedString(name);
            }
            statement.append(CONSTRAINT).append(SPACE).append(name).append(SPACE);
        }
        SearchCondition condition = constraint.getSearchCondition();
        String conditionSql = EMPTY_STRING;
        if (condition != null)
        {
            conditionSql = condition.getSQL();
        }
        statement.append(CHECK).append(SPACE).append(LEFT_PARENTHESIS).append(conditionSql).append(RIGHT_PARENTHESIS);
        return statement.toString();
    }

    /**
     * Returns the SQL Statement for adding check constraint clause.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *   alter table [[database.][owner].table_name
     *      add {[constraint constraint_name]
     *      check (search_condition)}
     * </pre>
     * 
     * @param constraint
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @return String[]
     */
    public String[] addCheckConstraint(CheckConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        StringBuffer statement = new StringBuffer(128);
        statement.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                getName(constraint.getBaseTable(), quoteIdentifiers, qualifyNames)).append(SPACE);
        statement.append(NEWLINE).append(TAB).append(ADD).append(SPACE);
        statement.append(this.getAddCheckConstraintClause(constraint, quoteIdentifiers));
        return new String[]
        {
            statement.toString()
        };
    }

    /**
     * Assemble SQL statement to grant privilege.<br>
     * One thing needs to be pointed out is, a privilege instance can be referenced by an instance of
     * <code>AuthorizationIdentifier</code> (The grantor), it can also be contained by an instance of
     * <code>AuthorizationIdentifier</code> (The grantee).
     * <p>
     * In this case, to avoid generating a same grant statement two times, we only generate the statement for a
     * contained privilege.
     * 
     * <p>
     * Syntax:<br>
     * Adaptive Server Anywhere 9.x
     * 
     * <pre>
     *        GRANT {
     *            ALL [ PRIVILEGES ],
     *            ALTER,
     *            DELETE,
     *            INSERT,
     *            REFERENCES [ ( column-name, ... ) ],
     *            SELECT [ ( column-name, ... ) ],
     *            UPDATE [ ( column-name, ... ) ],
     *            }
     *            ON [ owner.]table-name
     *            TO userid, ...
     *            [ WITH GRANT OPTION ]
     *            [ FROM userid  ]
     * </pre>
     * <pre>
     *   GRANT EXECUTE ON [ owner.]procedure-name TO userid, ...
     * </pre>
     * 
     * <p>
     * Adaptive Server Enterprise 12.5.x & 15.x
     * 
     * <pre>
     *        grant {all [privileges]| permission_list}
     *            on { table_name [(column_list)]
     *            | view_name[(column_list)]
     *            | stored_procedure_name}
     *            to {public | name_list | role_name}
     *            [with grant option]
     * </pre>
     * <pre>
     *           Object              permission_list
     *           ------------------------------------------------------
     *           Table               select, insert, delete, update, references
     *           View                select, insert, delete, update
     *           Column              select, update, references
     *           Stored procedure    execute
     * </pre>
     * 
     * TODO: For ASE15, there're new types of permissions, I'll do research to find out if we should support them.
     * 
     * The "[FROM userid]" sub clause won't be used here.<br>
     * Generally speaking, the "[ALL PRIVILEGES]" won't appear since in system table there is no "[ALL PRIVILEGES]"
     * type.
     * 
     * @param privilege the privilege instance
     * @param quoteIdentifiers the status of "quoted_identifier"
     * @param qualifyNames if qulified name should be used
     * @author Idull
     */
    public String[] grantPrivilege(Privilege privilege, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        /**
         * if privilege not instaneof SybasePrivilege then throw exception
         */
        if(!(privilege instanceof SybasePrivilege))
            throw new IllegalArgumentException("Only SybasePrivilege supported");
        
        SybasePrivilege sybPrivilege = (SybasePrivilege)privilege;
        if(sybPrivilege.getGrantee() == null)
            throw new IllegalArgumentException("Privilege without grantee!");
        
//        /**
//         * If the container is null, then it's in the granted privileges list.<br>
//         * Otherwise, it's in the received privileges list.
//         */
//        if (privilege == null || privilege.eContainer() == null)
//        {
//            return new String[]
//            {};
//        }

        StringBuffer sb = new StringBuffer("");
        String permissionString = assemblePermissionList(sybPrivilege, quoteIdentifiers, qualifyNames);
        StringBuffer objectName = new StringBuffer("");
        if (sybPrivilege.getObject() == null)
        {
            objectName.append("");
        }
        else if (sybPrivilege.getObject() instanceof Column)
        {
            Column col = (Column) sybPrivilege.getObject();
            objectName.append(getName((SQLObject) col.getTable(), quoteIdentifiers, qualifyNames));
        }
        else
        {
            objectName.append(getName((SQLObject) sybPrivilege.getObject(), quoteIdentifiers, qualifyNames));
        }

        AuthorizationIdentifier grantee = getAuthorizationIdentifier(sybPrivilege);
        sb.append(GRANT).append(SPACE).append(permissionString).append(SPACE).append(ON).append(SPACE).append(
                objectName).append(SPACE).append(TO).append(SPACE).append(
                grantee.getName());//never need to quote authorization identifier
        return new String[]
        {
            sb.toString()
        };
    }

    /**
     * Assemble SQL statement to revoke privilege. Syntax:<br>
     * Adaptive Server Anywhere 9.x
     * 
     * <pre>
     *       REVOKE table-priv, ...
     *           ON [ owner.]table-name
     *           FROM userid, ...
     *      
     *           table-priv :
     *           ALL [PRIVILEGES]
     *           | ALTER
     *           | DELETE
     *           | INSERT
     *           | REFERENCES [ ( column-name, ...) ]
     *           | SELECT [ ( column-name, ...) ]
     *           | UPDATE [ ( column-name, ...) ]
     *      
     * </pre>
     * 
     * <pre>
     *      REVOKE EXECUTE
     *          ON [ owner.]procedure-name
     *          FROM userid, ...
     * </pre>
     * 
     * Adaptive Server Enterprise 12.5.x & 15.x
     * 
     * <pre>
     *   revoke [grant option for]
     *       {all [privileges] | permission_list}
     *       on { table_name [(column_list)]
     *       | view_name [(column_list)]
     *       | stored_procedure_name}
     *       from {public | name_list | role_name}
     *       [cascade]
     * </pre>
     * 
     * Generally speaking, the "[ALL PRIVILEGES]" won't appear since in system table there is no "[ALL PRIVILEGES]"
     * type.<br>
     * "[cascade]" is to control whether we should revoke the privilege granted by the revokee, thus won't used here.<br>
     * "[grant option for]" will be used in delta ddl gen provider.
     * 
     * @param privilege the privilege instance
     * @param quoteIdentifiers the status of "quoted_identifier"
     * @param qualifyNames if qulified name should be used
     * @author Idull
     */
    public String revokePrivilege(Privilege privilege, boolean quoteIdentifiers, boolean qualifyNames)
    {
        /**
         * If the container is null, then it's in the granted privileges list.<br>
         * Otherwise, it's in the received privileges list.
         */
//        if (privilege.eContainer() == null)
//        {
//            return "";
//        }
        /**
         * if privilege not instaneof SybasePrivilege then throw exception
         */
        if(!(privilege instanceof SybasePrivilege))
            throw new IllegalArgumentException("Only SybasePrivilege supported");
        
        SybasePrivilege sybPrivilege = (SybasePrivilege)privilege;
        if(sybPrivilege.getGrantee() == null)
            throw new IllegalArgumentException("Privilege without grantee!");
        
        if (privilege.getObject() instanceof Column)
        {
            Column col = (Column) privilege.getObject();
            // This happens when the column is already removed from the table
            if (col.getTable() == null)
            {
                return "";
            }
        }
        
        StringBuffer sb = new StringBuffer("");
        String permissionString = assemblePermissionList(privilege, quoteIdentifiers, qualifyNames);
        StringBuffer objectName = new StringBuffer("");
        if (privilege.getObject() == null)
        {
            objectName.append("");
        }
        else if (privilege.getObject() instanceof Column)
        {
            Column col = (Column) privilege.getObject();
            objectName.append(getName((SQLObject) col.getTable(), quoteIdentifiers, qualifyNames));
        }
        else
        {
            objectName.append(getName((SQLObject) privilege.getObject(), quoteIdentifiers, qualifyNames));
        }
        AuthorizationIdentifier grantee = getAuthorizationIdentifier(privilege);
        sb.append(REVOKE).append(SPACE).append(permissionString).append(SPACE).append(ON).append(SPACE).append(
                objectName).append(SPACE).append(FROM).append(SPACE).append(
                getName(grantee, false, qualifyNames));

        return sb.toString();
    }

    private AuthorizationIdentifier getAuthorizationIdentifier(Privilege privilege)
    {
        if (privilege instanceof SybasePrivilege)
        {
            return ((SybasePrivilege)privilege).getGrantee();
        }
        else
        {
            return (AuthorizationIdentifier) privilege.eContainer();
        }
    }
    private String assemblePermissionList(Privilege privilege, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer permissionList = new StringBuffer("");
        if (privilege.getObject() == null)
        {
            return "";
        }
        else if (privilege.getObject() instanceof Column)
        {
            Column col = (Column) privilege.getObject();
            permissionList.append(privilege.getAction() == null ? "" : privilege.getAction()).append(LEFT_PARENTHESIS)
                    .append(getName(col, quoteIdentifiers, false)).append(RIGHT_PARENTHESIS);
        }
        else
        {
            permissionList.append(privilege.getAction() == null ? "" : privilege.getAction());
        }
        return permissionList.toString();
    }

    protected String getViewColumnList(ViewTable view, boolean quoteIdentifiers)
    {
        StringBuffer columnsStr = new StringBuffer("");
        Iterator it = view.getColumns().iterator();

        if (view.getColumns().size() == 0)
        {
            return null;
        }

        while (it.hasNext())
        {
            if (columnsStr.length() != 0)
            {
                columnsStr.append(COMMA).append(SPACE);
            }
            Column c = (Column) it.next();
            if (quoteIdentifiers)
            {
                columnsStr.append(this.getDoubleQuotedString(c.getName()));
            }
            else
            {
                columnsStr.append(c.getName());
            }
        }

        return columnsStr.toString();
    }

    /**
     * Generates tsql style parameters statement without parenthesis
     * 
     * @param routine
     * @return
     */
    protected String getTSQLParameters(Routine routine)
    {
        StringBuffer sb = new StringBuffer();
        Iterator it = routine.getParameters().iterator();
        while(it.hasNext()) {
            Parameter p = (Parameter) it.next();
            sb.append(getTSQLParameter(routine, p));
            
            if(it.hasNext()) {
                sb.append(COMMA).append(SPACE);
            }

        }
        return sb.toString();
    }

    /**
     * Returns the TSQL parameter declaration.
     * @param routine the container of the parameter. Must have containing schema and database.
     * @param p
     * @return
     */
    public String getTSQLParameter(Routine routine, Parameter p)
    {
        String name = p.getName();
        String type = this.getDataTypeString(p, routine.getSchema());
        String dft = null;
        if (p instanceof SybaseParameter)
        {
            dft = ((SybaseParameter)p).getDefaultValue();
        }
        ParameterMode mode = p.getMode();
        String modeLiteral = mode.getLiteral();
        return getTSQLParameter(name, type, dft, modeLiteral);
    }

    public String getTSQLParameter(String name, String type, String defValue, String mode)
    {
        StringBuffer sb = new StringBuffer();
            sb.append(name).append(SPACE);
        sb.append(type);
        if (defValue != null)
        {
            sb.append("=").append(defValue);
        }
        if(mode != null && (mode.equalsIgnoreCase(ParameterMode.INOUT_LITERAL.getLiteral()) || mode.equalsIgnoreCase(ParameterMode.OUT_LITERAL.getLiteral()))) {
            sb.append(SPACE).append(OUTPUT);
        }
        return sb.toString();
    }
    
    protected String getDeterministic(Routine routine)
    {
        StringBuffer sb = new StringBuffer();
        if (routine.isDeterministic())
        {
            sb.append(DETERMINISTIC).append(NEWLINE);
        }
        else
        {
            sb.append(NOT).append(SPACE).append(DETERMINISTIC).append(NEWLINE);
        }
        return sb.toString();
    }

    public String[] createIndex(Index index, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        return new String[]
        {
            createIndex(index, quoteIdentifiers, qualifyNames)
        };
    }

    protected String getKeyColumns(ReferenceConstraint constraint, boolean quoteIdentifiers)
    {
        StringBuffer statement = new StringBuffer(128);
        Iterator it = constraint.getMembers().iterator();
        if (it.hasNext())
        {
            Column c = (Column) it.next();
            if (quoteIdentifiers)
            {
                statement.append(getDoubleQuotedString(c.getName()));
            }
            else
            {
                statement.append(c.getName());
            }
        }
        else
        {
            // TODO report error
            return SPACE;
        }

        while (it.hasNext())
        {
            Column c = (Column) it.next();
            if (quoteIdentifiers)
            {
                statement.append(COMMA).append(SPACE).append(getDoubleQuotedString(c.getName()));
            }
            else
            {
                statement.append(COMMA).append(SPACE).append(c.getName());
            }

        }

        return statement.toString();
    }

    public String getTriggerEvents(Trigger trigger)
    {
        StringBuffer sb = new StringBuffer();
        //can be muliple action types
        if (trigger.isUpdateType())
        {
            sb.append(UPDATE);
            if (trigger.isInsertType() || trigger.isDeleteType())
            {
                sb.append(COMMA);
            }
            sb.append(SPACE);
        }
        if (trigger.isInsertType())
        {
            sb.append(INSERT);
            if (trigger.isDeleteType())
            {
                sb.append(COMMA);
            }
            sb.append(SPACE);
        }
        if (trigger.isDeleteType())
        {
            sb.append(DELETE).append(SPACE);
        }
        return sb.toString();
    }

    public String getTriggerBody(Trigger trigger)
    {
        EList actions = trigger.getActionStatement();
        StringBuffer body = new StringBuffer();
        if (actions != null && actions.size() > 0)
        {
            for (Iterator iter = actions.iterator(); iter.hasNext();)
            {
                SQLStatement stmt = (SQLStatement) iter.next();
                body.append(stmt.getSQL()).append(NEWLINE);
            }
        }
    
        String bodyString = body.toString();
        return bodyString;
    }

    public String createTSQLTrigger(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String bodyString = getTriggerBody(trigger);
        if (QuickSQLParser.getInstance().match(bodyString, QuickSQLParser.CREATE_TRIGGER_HEADER_PATTERN))
        {
            // body already contains the header, which happens when the function is loaded from database
            return bodyString;
        }
    
        StringBuffer sb = new StringBuffer();
        sb.append(CREATE).append(SPACE).append(TRIGGER).append(SPACE).append(
                getName(trigger, quoteIdentifiers, qualifyNames)).append(NEWLINE);
        Table subjectTable = trigger.getSubjectTable();
        if (subjectTable != null)
        {
            sb.append(ON).append(SPACE);
            Schema triggerOwner = trigger.getSchema();
            Schema tableOwner = subjectTable.getSchema();
            if (tableOwner != null && (qualifyNames || tableOwner.equals(triggerOwner)))
            {
                sb.append(tableOwner.getName()).append(DOT);
            }
            sb.append(trigger.getName()).append(NEWLINE);
            sb.append(FOR).append(SPACE);
            
            sb.append(getTriggerEvents(trigger));
            sb.append(NEWLINE);
        }
        sb.append(AS).append(NEWLINE);
        sb.append(bodyString);
        return sb.toString();
    }
    
    public String getIndexKeyColumns(Index index, boolean quoteIdentifiers)
    {
        return SybaseDdlUtils.getIndexMemberKeys(index.getMembers(), quoteIdentifiers);        
    }   
    
    protected String getDataTypeString(TypedElement typedElement, Schema schema)
    {
        //TODO temparory solution, should use DataTypeProvider.getDataTypeString in the future
        boolean needQuote = false;
        if (typedElement.getDataType().getName().indexOf("\"") >= 0
                || typedElement.getDataType().getName().indexOf(" ") >= 0)
        {
            needQuote = true;
        }
        return getDataTypeString(typedElement, schema, needQuote);
    }
    
    protected String getDataTypeString(TypedElement typedElement, Schema schema, boolean quotedIdentifier) {
        SQLDataType containedType = typedElement.getContainedType();
        if(containedType != null) {
            if(containedType instanceof PredefinedDataType) {
                EObject root = ContainmentServiceImpl.INSTANCE.getRootElement(typedElement);
                if(root instanceof Database) {
                    DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition((Database) root);
                    return def.getPredefinedDataTypeFormattedName((PredefinedDataType) containedType);
                }
            }
        }
        else {
            UserDefinedType referencedType = typedElement.getReferencedType();
            if(referencedType != null) {
                if (referencedType.getSchema() != schema) {
                    return this.getName(referencedType,quotedIdentifier, false);
                } else {
                    if(quotedIdentifier)
                    {
                        return getDoubleQuotedString(referencedType.getName());
                    }
                    else
                    {
                        return referencedType.getName();
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * super implementation is wrong
     */
    protected String getDoubleQuotedString(String original)
    {
        return SQLUtil.quote(original, "\"");
    }
    
    /**
     * super implementation is wrong
     */
    protected String getSingleQuotedString(String original)
    {
        return SQLUtil.quote(original, "'");
    }
    
    protected String getDropPreconditionPattern() 
    {
        StringBuffer sb = new StringBuffer();
        
        sb.append(IF).append(SPACE).append(EXISTS).append(SPACE).append(LEFT_PARENTHESIS).append(NEWLINE);
        sb.append(TAB).append("{0}").append(RIGHT_PARENTHESIS).append(NEWLINE).append(THEN).
            append(NEWLINE).append(BEGIN).append(NEWLINE);
        sb.append(TAB).append("{1}").append(NEWLINE).append(END).append(NEWLINE).append(END_IF);
        return sb.toString();
    }

    public DatabaseIdentifier getDatabaseIdentifier(EObject ojb) {
        EObject root = ContainmentServiceImpl.INSTANCE.getRootElement(ojb);
        if(root instanceof Database) {
            DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition((Database) root);
            DDLGenerator ddlgen = def.getDDLGenerator();
            if (ddlgen instanceof SybaseDdlGenerator) {
                if (((SybaseDdlGenerator) ddlgen).getParameter() instanceof DatabaseIdentifier)
                {
                    return (DatabaseIdentifier) ((SybaseDdlGenerator) ddlgen).getParameter();
                }
            }
        }
        return null;
    }    
}
