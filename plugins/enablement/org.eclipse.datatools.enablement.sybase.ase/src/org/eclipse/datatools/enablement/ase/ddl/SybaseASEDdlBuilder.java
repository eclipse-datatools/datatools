/*******************************************************************************
 * Copyright (c) 2007-8 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase
 ******************************************************************************/
package org.eclipse.datatools.enablement.ase.ddl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.ase.ISybaseASEDdlConstants;
import org.eclipse.datatools.enablement.ase.SybaseASESQLUtil;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.AccessRuleType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheStrategyType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECheckConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUser;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEViewTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEPartition;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERangePartition;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERoundrobinPartition;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.enablement.sybase.parser.QuickSQLParser;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.IncrementType;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.MatchType;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.routines.DataAccess;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

public class SybaseASEDdlBuilder extends SybaseDdlBuilder implements ISybaseASEDdlConstants
{
    
    final public static String      ASE_PROCEDURE_WITH_TEMP_TABLE_ANNOTATION = "Warning: This annotation was generated by temporary table wizard\\. Do not edit it\\.";
    final public static String      ASE_PROCEDURE_WITH_TEMP_TABLE_NAME       = "@TemporaryTableName";
    final static public String      PATTERN                                  = ".*"
                                                                                     + ASE_PROCEDURE_WITH_TEMP_TABLE_ANNOTATION
                                                                                     + "[\\r|\\n|\\s]+"
                                                                                     + ASE_PROCEDURE_WITH_TEMP_TABLE_NAME
                                                                                     + " (#[^\\r\\n]+)(([^-/])*(?=(\\*\\/|--)))";
    
    private static SybaseDdlBuilder builder;

    protected SybaseASEDdlBuilder()
    {
    }

    public String dropIndex(Index index, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer stmt = new StringBuffer();
        String name;
        
        if(quoteIdentifiers)
        {
            name = SQLUtil.quote(index.getTable().getName(), DOUBLE_QUOTE)+DOT+index.getName();
        }
        else
        {
            name = index.getTable().getName()+DOT+index.getName();
        }
        String useSetUserStr = SybaseASESQLUtil.getSetNewUserStatement(index.getTable().getSchema());
        stmt.append(useSetUserStr).append(NEWLINE);
        stmt.append(DROP).append(SPACE).append(INDEX).append(SPACE).append(name).append(NEWLINE);
        stmt.append(SybaseASESQLUtil.getSetUserDBOStatement(index.getTable().getSchema()));

        String dropStatement = stmt.toString();
        Table table = index.getTable();
        String tableName = getName(table, quoteIdentifiers, true);
        String indexName = index.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_INDEX, new Object[]{indexName, tableName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }

    static public SybaseDdlBuilder getInstance()
    {
        if (builder == null)
        {
            builder = new SybaseASEDdlBuilder();
        }

        return builder;
    }

    /*
     * Create database script <pre>
     * 
     * create [temporary] database database_name [on {default | database_device} [= size] [, database_device [=
     * size]]...] [log on database_device [= size] [, database_device [= size]]...] [with {override | default_location =
     * "pathname"}] [for {load | proxy_update}] </pre>
     * 
     */
    public String[] createCatalogs(Catalog catalog, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        SybaseASECatalog aseCatalog = (SybaseASECatalog) catalog;
        StringBuffer statement = new StringBuffer();
        statement.append(CREATE + SPACE);
        // temporary
        if (aseCatalog.getCatalogType().equals(SybaseASECatalogType.TEMPCATALOG_LITERAL))
        {
            statement.append(TEMPORARY + SPACE);
        }
        statement.append(DATABASE + SPACE);
        String name = getName(aseCatalog, quoteIdentifiers, qualifyNames);
        statement.append(name);
        if (aseCatalog.getDataDevices().size() > 0)
        {
            statement.append(NEWLINE + TAB + ON + SPACE);
            EList devices = aseCatalog.getDataDevices();
            for (int i = 0; i < devices.size(); i++)
            {
                if (i > 0)
                {
                    statement.append(NEWLINE + TAB + COMMA + SPACE);
                }
                DeviceItem item = (DeviceItem) devices.get(i);
                statement.append(item.getDeviceName());
                statement.append(SPACE + EQUAL + SPACE);
                statement.append(getSingleQuotedString(String.valueOf(item.getSize())));
            }
        }
        if (aseCatalog.getLogDevices().size() > 0)
        {
            statement.append(NEWLINE + TAB + LOG + SPACE + ON + SPACE);
            EList devices = aseCatalog.getLogDevices();
            for (int i = 0; i < devices.size(); i++)
            {
                if (i > 0)
                {
                    statement.append(NEWLINE + TAB + COMMA + SPACE);
                }
                DeviceItem item = (DeviceItem) devices.get(i);
                statement.append(item.getDeviceName());
                statement.append(SPACE + EQUAL + SPACE);
                statement.append(getSingleQuotedString(String.valueOf(item.getSize())));
            }
        }
        if (aseCatalog.isOverride())
        {
            statement.append(NEWLINE + TAB + WITH + SPACE + OVERRIDE);
        }
        if (aseCatalog.getDefaultLocation() != null)
        {
            statement.append(NEWLINE + TAB + WITH + SPACE + DEFAULT_LOCATION + SPACE + EQUAL + SPACE);
            statement.append(getSingleQuotedString(aseCatalog.getDefaultLocation()));
        }
        if (aseCatalog.isForLoad())
        {
            statement.append(NEWLINE + TAB + FOR + SPACE + LOAD);
        }
        if (aseCatalog.isForLoad())
        {
            statement.append(NEWLINE + TAB + FOR + SPACE + PROXY_UPDATE);
        }
        
        String useMaseter = new StringBuffer().append(USE).append(SPACE).append(master).toString();
        String useDatabase = new StringBuffer().append(USE).append(SPACE).append(name).toString();
        
        return new String[]
        {
            useMaseter,
            statement.toString(),
            useDatabase,
        };
    }

    // 
    public String[] createDatabase(Database database, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        return new String[]
        {
            EMPTY_STRING
        };
    }

    /**
     * Returns the SQL Statement for creating user defined type.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *  sp_addtype typename,
     *           phystype [(length) | (precision [, scale])]
     *           [, &quot;identity&quot; | nulltype]
     *  sp_bindefault defname, objname [, futureonly]
     *  sp_bindrule rulename, objname [, futureonly]
     *   
     * </pre>
     * 
     * @param aseUDT
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @return
     * 
     * @author renj
     */
    public String[] createUserDefinedType(UserDefinedType udt, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        SybaseASEUserDefinedType aseUDT = (SybaseASEUserDefinedType) udt;
        String udtName = "";
        StringBuffer sb_schema = new StringBuffer(128);
        StringBuffer sb_setuser = new StringBuffer(128);
        StringBuffer sb_type = new StringBuffer(128);
        StringBuffer sb_default = new StringBuffer(128);
        StringBuffer sb_rule = new StringBuffer(128);

        sb_schema.append(SybaseASESQLUtil.getSetNewUserStatement(aseUDT.getSchema()));
        sb_setuser.append(SybaseASESQLUtil.getSetUserDBOStatement(aseUDT.getSchema()));
        
        sb_type.append(EXEC).append(SPACE).append(SP_ADDTYPE).append(SPACE);
        if (!quoteIdentifiers) {
            udtName = SQLUtil.quote(aseUDT.getName(), SINGLE_QUOTE);
        }
        else
        {
            udtName = SQLDevToolsUtil.quoteWhenNecessary(aseUDT.getName(), getDatabaseIdentifier(aseUDT));
            if (udtName.equals(aseUDT.getName()) && !aseUDT.getName().startsWith("\"") &&
                    !aseUDT.getName().endsWith("\""))
            {
                udtName = SQLUtil.quote(aseUDT.getName(), SINGLE_QUOTE);
            }
        }
        sb_type.append(udtName).append(COMMA);

        DatabaseDefinitionRegistry dbRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
        DatabaseDefinition definition = dbRegistry.getDefinition((Database) ContainmentServiceImpl.INSTANCE
                .getRootElement(aseUDT));
        PredefinedDataType pdt = aseUDT.getPredefinedRepresentation();

        sb_type.append(getSingleQuotedString(definition.getPredefinedDataTypeFormattedName(pdt)));

        sb_type.append(COMMA);
        if (aseUDT.isIdentity())
        {
            sb_type.append(getSingleQuotedString(IDENTITY));
        }
        else
        {
            if (aseUDT.isAllowNulls())
            {
                sb_type.append(NULL);
            }
            else
            {
                sb_type.append(NO).append(NULL);
            }
        }

        // generate default
        SybaseASEDefault aseDefault = aseUDT.getBoundDefault();
        if (aseDefault != null)
        {
            sb_default.append(EXEC).append(SPACE).append(SP_BINDEFAULT).append(SPACE).append(
                    SQLUtil.quote(getName(aseDefault, quoteIdentifiers, true), SINGLE_QUOTE)).append(COMMA).append(udtName);

            if (aseUDT.isBindDefaultInFutureOnly())
            {
                sb_default.append(COMMA).append(FUTUREONLY);
            }
        }

        // generate rule
        SybaseASERule aseRule = aseUDT.getBoundRule();
        if (aseRule != null)
        {
            sb_rule.append(EXEC).append(SPACE).append(SP_BINDRULE).append(SPACE).append(
                    SQLUtil.quote(getName(aseRule, quoteIdentifiers, true), SINGLE_QUOTE)).append(COMMA).append(udtName);

            if (aseUDT.isBindRuleInFutureOnly())
            {
                sb_rule.append(COMMA).append(FUTUREONLY);
            }
        }
        
        ArrayList result = new ArrayList();
        if(!sb_schema.toString().equals(EMPTY_STRING))
        {
            result.add(sb_schema.toString());
        }
        result.add(sb_type.toString());
        if (aseDefault != null)
        {
            result.add(sb_default.toString());
        }
        if (aseRule != null)
        {
            result.add(sb_rule.toString());
        }
        if(!sb_setuser.toString().trim().equals(EMPTY_STRING))
        {
            result.add(sb_setuser.toString());
        }

        return (String[]) result.toArray(new String[result.size()]);
    }

    /**
     * Returns the SQL Statement for creating default.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *  create default [owner.]default_name
     *           as constant_expression
     *   
     * </pre>
     * 
     * @param aseDefault
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @return
     * 
     * @author renj
     */
    public String[] createDefault(SybaseASEDefault aseDefault, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        StringBuffer sb = new StringBuffer(128);
        sb.append(CREATE).append(SPACE).append(DEFAULT).append(SPACE).append(
                getName(aseDefault, quoteIdentifiers, qualifyNames)).append(SPACE).append(AS).append(SPACE);

        String statement = aseDefault.getStatement();
        if (statement != null && !statement.trim().equals(""))
        {
            sb.append(statement);
        }

        return new String[]
        {
            sb.toString()
        };
    }

    /**
     * Returns the SQL Statement for creating rule.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *  create [ [ and | or ] access]] rule
     *           [owner.]rule_name
     *           as condition_expression
     *   
     * </pre>
     * 
     * @param aseRule
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @return
     * 
     * @author renj
     */
    public String[] createRule(SybaseASERule aseRule, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        StringBuffer sb = new StringBuffer(128);
        sb.append(CREATE).append(SPACE);

        if (aseRule.isAccessRule())
        {
            AccessRuleType accessRuleType = aseRule.getAccessType();
            if (accessRuleType.getValue() == AccessRuleType.AND)
            {
                sb.append(AND).append(SPACE);
            }
            else if (accessRuleType.getValue() == AccessRuleType.OR)
            {
                sb.append(OR).append(SPACE);
            }
            sb.append(ACCESS).append(SPACE);
        }

        sb.append(RULE).append(SPACE).append(getName(aseRule, quoteIdentifiers, qualifyNames)).append(SPACE).append(AS)
                .append(SPACE);

        String statement = aseRule.getStatement();
        if (statement != null && !statement.trim().equals(""))
        {
            sb.append(NEWLINE).append(statement);
        }

        return new String[]
        {
            sb.toString()
        };
    }

    /**
     * TSQL procedure:
     * 
     * <pre>
     *       create procedure [owner.]procedure_name[;number]
     *       [[(]@parameter_name
     *       datatype [(length ) | (precision [, scale ])]
     *       [= default][output]
     *       [, @parameter_name
     *       datatype [(length ) | (precision [, scale ])]
     *       [= default][output]]...[)]]
     *       [with recompile]
     *       as {SQL_statements | external name dll_name}
     * </pre>
     * 
     * <p>
     * SQLJ
     * </p>
     * 
     * <pre>
     *        [modifies sql data ]
     *        [dynamic result sets integer]
     *        [deterministic | not deterministic]
     *        language java
     *        parameter style java
     *        external name 'java_method_name
     *        [ ( [java_datatype[, java_datatype
     *        ...]] ) ]'
     * </pre>
     */
    public String[] createProcedureBody(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        SybaseASEProcedure proc = (SybaseASEProcedure) procedure;
        Source source = procedure.getSource();
        String body = "";
        if (source != null && source.getBody() != null)
        {
            body = source.getBody();
        }

        if (QuickSQLParser.getInstance().match(body, QuickSQLParser.CREATE_PROC_HEADER_PATTERN))
        {
            // body already contains the header, which happens when the Procedure is loaded from database
            return new String[]
            {
                body
            };
        }

        // this happens when user creates the model from scratch
        StringBuffer sb = new StringBuffer();
        sb.append(CREATE).append(SPACE).append(PROCEDURE).append(SPACE).append(
                getName(procedure, quoteIdentifiers, qualifyNames)).append(NEWLINE);

        String param = getTSQLParameters(procedure);
        if (param != null && param.length() > 0)
        {
            sb.append(LEFT_PARENTHESIS).append(param).append(RIGHT_PARENTHESIS).append(NEWLINE);
        }
        if (proc.isWithRecompile())
        {
            sb.append(WITH_RECOMPILE).append(NEWLINE);
        }

        if (proc.getExternalName() != null && proc.getExternalName().trim().length() > 0)
        {
            // SQLJ
            if (proc.getSqlDataAccess().getValue() == DataAccess.MODIFIES_SQL_DATA)
            {
                sb.append(getSQLAccess(proc));
            }

            if (proc.getMaxResultSets() > 0)
            {
                sb.append(TAB).append(DYNAMIC_RESULT_SETS).append(SPACE).append(proc.getMaxResultSets())
                        .append(NEWLINE);
            }

            sb.append(TAB).append(getDeterministic(proc));

            sb.append(getLanguage(proc));
            sb.append(getParameterStyleOption(proc));
            // TODO shall we quote external name?
            sb.append(getExternalName(proc));
        }
        else
        {
            sb.append(AS).append(NEWLINE).append(body);
        }

        return new String[]
        {
            sb.toString()
        };
    }
    
    
    public String[] createProcedure(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        String body = createProcedureBody(procedure, quoteIdentifiers, qualifyNames, fullSyntax)[0];
        return createProcedureObject(body, quoteIdentifiers, qualifyNames, fullSyntax);
    }

    /**
     * add temp table creation support for stored procedure and trigger
     */
    public String[] createProcedureObject(String body, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(body);
        List tempTableNames = new ArrayList();
        List createTempTable = new ArrayList();
        while (matcher.find())
        {
            tempTableNames.add(matcher.group(1).trim());
            createTempTable.add(matcher.group(2).trim());
        }
        
        String[] createTempTableStatements = new String[tempTableNames.size()]; 
        for(int i = 0; i<tempTableNames.size(); i++)
        {
            createTempTableStatements[i] = (String)createTempTable.get(i);
        }
        
        String[] dropTempTableStatements = new String[tempTableNames.size()];
        for(int i = 0; i<tempTableNames.size(); i++)
        {
            String tempTableName = (String)tempTableNames.get(i);
            dropTempTableStatements[i] = DROP + SPACE + TABLE + SPACE + tempTableName;
        }
        
        String[] results = new String[createTempTableStatements.length * 2 + 1];
        System.arraycopy(createTempTableStatements, 0, results, 0, createTempTableStatements.length);
        results[createTempTableStatements.length] = body;
        System.arraycopy(dropTempTableStatements, 0, results, createTempTableStatements.length + 1, dropTempTableStatements.length);
        return results;
    }

    /**
     * <pre>
     *    create function [owner.]sql_function_name
     *       ( [ sql_parameter_name sql_datatype
     *       [( length)| (precision[, scale ]) ]
     *       [ [, sql_parameter_name sql_datatype
     *       [( length )| ( precision[, scale ]) ]]
     *       ... ] ] )
     *       returns sql_datatype
     *       [ ( length)| (precision[, scale ]) ]
     *       [modifies sql data]
     *       [returns null on null input |
     *       called on null input]
     *       [deterministic | not deterministic]
     *       [exportable]
     *       language java
     *       parameter style java
     *       external name 'java_method_name
     *       [ ( [java_datatype[, java_datatype
     *       ...] ] ) ] '
     * </pre>
     */
    public String[] createUserDefinedFunction(UserDefinedFunction function, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax)
    {
        Source source = function.getSource();
        String body = "";
        if (source != null && source.getBody() != null)
        {
            body = source.getBody();
        }

        if (QuickSQLParser.getInstance().match(body, QuickSQLParser.CREATE_FUNC_HEADER_PATTERN))
        {
            // body already contains the header, which happens when the function is loaded from database
            return new String[]
            {
                body
            };
        }

        // this happens when user creates the model from scratch
        StringBuffer sb = new StringBuffer();
        sb.append(CREATE).append(SPACE).append(FUNCTION).append(SPACE).append(
                getName(function, quoteIdentifiers, qualifyNames)).append(NEWLINE);

        String param = getTSQLParameters(function);
        if (param != null && param.length() > 0)
        {
            sb.append(LEFT_PARENTHESIS).append(param).append(RIGHT_PARENTHESIS).append(NEWLINE);
        }
        sb.append(getFunctionReturnsClause(function));

        // the only possible value
        if (fullSyntax)
        {
            sb.append(getSQLAccess(function));
        }

        if (function.isNullCall())
        {
            sb.append(RETURNS_NULL_ON_NULL_INPUT);
        }
        else
        {
            sb.append(CALLED_ON_NULL_INPUT);
        }

        sb.append(TAB).append(getDeterministic(function));
        // TODO define exportable in model
        sb.append(getLanguage(function));
        sb.append(getParameterStyleOption(function));
        // TODO shall we quote external name?
        sb.append(getExternalName(function));

        return new String[]
        {
            sb.toString()
        };
    }

    /**
     * <pre>
     *       create trigger [owner .]trigger_name
     *       on [owner .]table_name
     *       for {insert , update , delete}
     *       as SQL_statements
     * </pre>
     */
    public String[] createTrigger(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        String body = createTSQLTrigger(trigger, quoteIdentifiers, qualifyNames);
        return createProcedureObject(body, quoteIdentifiers, qualifyNames, fullSyntax);
    }

    public String[] createEvent(Event event, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        return new String[]
        {};
    }

    
    protected String[] createPersistentTable(PersistentTable table, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        if(table instanceof SybaseASEProxyTable)
        {
            return createProxyTable((SybaseASEProxyTable)table, quoteIdentifiers, qualifyNames, fullSyntax);
        }
        else if(table instanceof SybaseASETable)
        {
            return createBaseTable(table, quoteIdentifiers, qualifyNames, fullSyntax);
        }
        else
        {
            throw new UnsupportedOperationException();
        }
    }

    
    protected String[] createTempoeryTable(TemporaryTable table, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        return createBaseTable(table, quoteIdentifiers, qualifyNames, fullSyntax);
    }
    
    // TODO: support fullsyntax, and other attributes
    /**
     * @author sfyu
     * 
     */
    protected String[] createBaseTable(BaseTable table, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        List stmts = new ArrayList();
        String setUserStr = EMPTY_STRING;
        Schema creator = table.getSchema();
        if(creator!=null)
    	{
    		setUserStr = SybaseASESQLUtil.getSetNewUserStatement(creator);;
    	}
        if(!setUserStr.equals(EMPTY_STRING))
        {
            stmts.add(setUserStr);
        }
        
        StringBuffer ddl = new StringBuffer();
        ddl.append(CREATE).append(SPACE).append(TABLE).append(SPACE).append(
                getName(table, quoteIdentifiers, qualifyNames)).append(SPACE).append(LEFT_PARENTHESIS).append(NEWLINE);
        Iterator it = table.getColumns().iterator();
        while (it.hasNext())
        {
            Column column = (Column) it.next();
            ddl.append(TAB).append(TAB).append(getColumnString(column, quoteIdentifiers));
            if (it.hasNext())
            {
                ddl.append(COMMA);
            }
            ddl.append(NEWLINE);
        }
        ddl.append(TAB).append(RIGHT_PARENTHESIS).append(NEWLINE);
        String lockSchema = getLockSchemaStatement(table);
        String withClause = getWithClause(table);
        String segmentStmt = getSegmentStatement(table, quoteIdentifiers);
        String partitionStmt = getPartitionStatement(table, quoteIdentifiers);
        if (lockSchema != null)
        {
            ddl.append(lockSchema).append(SPACE).append(NEWLINE);
        }
        if (withClause != null)
        {
            ddl.append(withClause).append(SPACE).append(NEWLINE);
        }
        if (segmentStmt != null)
        {
            ddl.append(segmentStmt).append(SPACE).append(NEWLINE);
        }
        if (partitionStmt != null)
        {
            ddl.append(partitionStmt).append(SPACE).append(NEWLINE);
        }
        
        stmts.add(ddl);
        
        List cacheInfoStmt = getCacheInfoStmt(table,quoteIdentifiers,qualifyNames,true);
        stmts.addAll(cacheInfoStmt);
        
        List ruleBindingStmt = getRuleBindingStmt(table,quoteIdentifiers,qualifyNames,false);
        
        stmts.addAll(ruleBindingStmt);
        
        List defaultBindingStmt = getDefaultBindingStmt(table,quoteIdentifiers,qualifyNames,false);
        stmts.addAll(defaultBindingStmt);
        String setUserDbo = SybaseASESQLUtil.getSetUserDBOStatement(creator);
        if(!setUserDbo.equals(EMPTY_STRING))
        {
            stmts.add(setUserDbo);
        }
        String[] result = new String[stmts.size()];
        for(int i=0;i<stmts.size();i++)
        {
            result[i]=stmts.get(i).toString();
        }        
        return result;
    }

    //TODO:while column UDT have bound default, column can not bind default any more. 
    private List getDefaultBindingStmt(BaseTable table, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        SybaseASETable sybTable = (SybaseASETable)table;
        EList columns = sybTable.getColumns();
        List stmts = new ArrayList();
        for(int i=0;i<columns.size();i++)
        {
            SybaseASEColumn col = (SybaseASEColumn)columns.get(i);
            if(col.getBoundDefault()!=null)
            {
                StringBuffer stmt = new StringBuffer();
                stmt.append(EXEC).append(SPACE).append(SP_BINDEFAULT).append(SPACE).append(
                        SQLUtil.quote(getName(col.getBoundDefault(), false, true), SINGLE_QUOTE)).append(COMMA)
                        .append(SQLUtil.quote(col.getTable().getName()+DOT+col.getName(),SINGLE_QUOTE));
                stmts.add(stmt);
            }
        }
        return stmts;
    }

    public List getRuleBindingStmt(BaseTable table, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        SybaseASETable sybTable = (SybaseASETable)table;
        EList columns = sybTable.getColumns();
        List stmts = new ArrayList();
        for(int i=0;i<columns.size();i++)
        {
            SybaseASEColumn col = (SybaseASEColumn)columns.get(i);
            if(col.getBoundRule()!=null)
            {
                StringBuffer stmt = new StringBuffer();
                stmt.append(EXEC).append(SPACE).append(SP_BINDRULE).append(SPACE).append(
                        SQLUtil.quote(getName(col.getBoundRule(), false, true), SINGLE_QUOTE)).append(COMMA).append(
                                SQLUtil.quote(col.getTable().getName()+DOT+col.getName(),SINGLE_QUOTE));
                stmts.add(stmt);
            }
        }
        return stmts;
    }
    
    

    private List getCacheInfoStmt(BaseTable table, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        List stmts = new ArrayList();
        SybaseASECatalog catalog = (SybaseASECatalog) table.getSchema().getCatalog();
        SybaseASETable aseTable = (SybaseASETable) table;
        if (aseTable.getTableOnlyCacheInfo() == null)
        {
            return stmts;
        }
        int strategy = aseTable.getTableOnlyCacheInfo().getCacheStrategy();

        StringBuffer prefetchOnStmt = null;

        prefetchOnStmt = new StringBuffer();
        prefetchOnStmt.append(EXEC).append(SPACE).append(SP_CACHESTRATEGY).append(SPACE).append(
                SQLUtil.quote(catalog.getName(), SINGLE_QUOTE)).append(COMMA).append(
                SQLUtil.quote(table.getSchema().getName() + DOT + table.getName(), SINGLE_QUOTE)).append(COMMA).append(
                SQLUtil.quote(TABLE_ONLY, SINGLE_QUOTE)).append(COMMA).append(CacheStrategyType.PREFETCH_LITERAL)
                .append(COMMA).append(SQLUtil.quote(ON, SINGLE_QUOTE));

        StringBuffer mruOffStmt = new StringBuffer();
        mruOffStmt.append(EXEC).append(SPACE).append(SP_CACHESTRATEGY).append(SPACE).append(
                SQLUtil.quote(catalog.getName(), SINGLE_QUOTE)).append(COMMA).append(
                SQLUtil.quote(table.getSchema().getName() + DOT + table.getName(), SINGLE_QUOTE)).append(COMMA).append(
                SQLUtil.quote(TABLE_ONLY, SINGLE_QUOTE)).append(COMMA).append(CacheStrategyType.MRU_LITERAL).append(
                COMMA).append(SQLUtil.quote(OFF, SINGLE_QUOTE));

        StringBuffer prefetchOffStmt = new StringBuffer();
        prefetchOffStmt.append(EXEC).append(SPACE).append(SP_CACHESTRATEGY).append(SPACE).append(
                SQLUtil.quote(catalog.getName(), SINGLE_QUOTE)).append(COMMA).append(
                SQLUtil.quote(table.getSchema().getName() + DOT + table.getName(), SINGLE_QUOTE)).append(COMMA).append(
                SQLUtil.quote(TABLE_ONLY, SINGLE_QUOTE)).append(COMMA).append(CacheStrategyType.PREFETCH_LITERAL)
                .append(COMMA).append(SQLUtil.quote(OFF, SINGLE_QUOTE));

        StringBuffer mruOnStmt = null;

        mruOnStmt = new StringBuffer();
        mruOnStmt.append(EXEC).append(SPACE).append(SP_CACHESTRATEGY).append(SPACE).append(
                SQLUtil.quote(catalog.getName(), SINGLE_QUOTE)).append(COMMA).append(
                SQLUtil.quote(table.getSchema().getName() + DOT + table.getName(), SINGLE_QUOTE)).append(COMMA).append(
                SQLUtil.quote(TABLE_ONLY, SINGLE_QUOTE)).append(COMMA).append(CacheStrategyType.MRU_LITERAL).append(
                COMMA).append(SQLUtil.quote(ON, SINGLE_QUOTE));
        
        
        if((strategy&CacheStrategyType.MRU)!=0)
        {
            stmts.add(mruOnStmt);
        }
        else if((strategy&CacheStrategyType.MRU_SERVER_DEFAULT)==0)
        {
            stmts.add(mruOffStmt);
        }
        
        if((strategy&CacheStrategyType.PREFETCH)!=0)
        {
            stmts.add(prefetchOnStmt);
        }
        else if((strategy&CacheStrategyType.PREFETCH_SERVER_DEFAULT)==0)
        {
            stmts.add(prefetchOffStmt);
        }
        return stmts;
    }

    public String[] grantPrivilege(Privilege privilege, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        String[] stat = super.grantPrivilege(privilege, quoteIdentifiers, qualifyNames, fullSyntax);
        if (stat == null || stat.length == 0)
        {
            return new String[]
            {
                ""
            };
        }
        StringBuffer sb = new StringBuffer(stat[0]);
        if (privilege.isGrantable())
        {
            sb.append(SPACE).append(WITH_GRANT_OPTION);
        }
        if((privilege instanceof SybasePrivilege) && ((SybasePrivilege)privilege).isRevoked())
        {
           String stmt = super.revokePrivilege(privilege, quoteIdentifiers, qualifyNames); 
           sb = new StringBuffer(stmt);
        }
        return new String[]
        {
            sb.toString()
        };
    }
    
    public String revokePrivilege(Privilege privilege, boolean quoteIdentifiers, boolean qualifyNames)
    {
        if((privilege instanceof SybasePrivilege) && ((SybasePrivilege)privilege).isRevoked())
        {
            ((SybasePrivilege)privilege).setRevoked(false);
            return grantPrivilege(privilege, quoteIdentifiers, qualifyNames, true)[0];
        }
        return super.revokePrivilege(privilege, quoteIdentifiers, qualifyNames);
    }


    /**
     * Returns the SQL Statement for adding unique constraint/primary key clause.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *    alter table [[database.][owner].table_name
     *       add {[constraint constraint_name]
     *       { unique | primary key}
     *       [clustered | nonclustered]
     *       (column_name [asc | desc]
     *       [, column_name [asc | desc]...])
     *       [with { fillfactor = pct,
     *       max_rows_per_page = num_rows,
     *       reservepagegap = num_pages}]
     *       [on segment_name]
     * </pre>
     * 
     * @param constraint
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @return String[]
     */
    
    public String[] addUniqueConstraint(UniqueConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        if (constraint == null)
        {
            return null;
        }
        SybaseASEUniqueConstraint uniqueConstraint = null;
        if (constraint instanceof PrimaryKey)
        {
            uniqueConstraint = ((SybaseASEPrimaryKey) constraint).getAseUniqueConstraint();
            if (uniqueConstraint == null)
            {
                return null;
            }
        }
        else
        {
            uniqueConstraint = (SybaseASEUniqueConstraint) constraint;
        }
        SybaseASEIndex index = (SybaseASEIndex) uniqueConstraint.getSystemGenedIndex();
        if (index == null)
        {
            return null;
        }
        StringBuffer statement = new StringBuffer(128);
        statement.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                getName(constraint.getBaseTable(), quoteIdentifiers, qualifyNames)).append(SPACE);
        statement.append(NEWLINE).append(TAB).append(ADD).append(SPACE);
        statement.append(this.getAddUniqueConstraintClause(constraint, index, quoteIdentifiers));
        String sql1 = statement.toString();
        statement = new StringBuffer(128);
        if (index.getFillFactor() > 0)
        {
            String objectName = constraint.getBaseTable().getName() + DOT + constraint.getName();
            statement.append(EXEC).append(SPACE).append(SP_CHGATTRIBUTE).append(SPACE).append(
                    getSingleQuotedString(objectName)).append(COMMA).append(getSingleQuotedString(FILLFACTOR)).append(
                    COMMA).append(index.getFillFactor());
            String sql2 = statement.toString();
            Schema creator = constraint.getBaseTable().getSchema();
            String setUserStr = "";
            if(creator!=null)
        	{
        		setUserStr = SybaseASESQLUtil.getSetNewUserStatement(creator);
        	}            
            String setUserDbo = SybaseASESQLUtil.getSetUserDBOStatement(creator);
            if(!setUserStr.equals(""))
            {
            	return new String[] {sql1,setUserStr,sql2,setUserDbo };
            }
            else
            {
            	return new String[] {sql1,sql2};
            }
        }
        return new String[] {sql1};
    }

    /**
     * Returns the SQL Statement for adding foreign key clause.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *    alter table [[database.][owner].table_name
     *       add {[constraint constraint_name]
     *       foreign key (column_name [{, column_name}...])
     *       references [[database.]owner.]ref_table
     *       [(ref_column [{, ref_column}...])]
     *       [match full]
     * </pre>
     * 
     * @param constraint
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @return String[]
     */
    public String[] addForeignKey(ForeignKey foreignKey, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        if (foreignKey == null)
        {
            return null;
        }
        UniqueConstraint uniqueConstraint = foreignKey.getUniqueConstraint();
        PrimaryKey primKey = null;
        /////////////////////////////////////

        Index index = foreignKey.getUniqueIndex();
        if (index == null)
        {
            if (uniqueConstraint instanceof PrimaryKey)
            {
                primKey =(SybaseASEPrimaryKey) uniqueConstraint;
                uniqueConstraint = ((SybaseASEPrimaryKey) uniqueConstraint).getAseUniqueConstraint();
                
            }
            else
            {
                uniqueConstraint = (SybaseASEUniqueConstraint) uniqueConstraint;
            }
            if (uniqueConstraint == null)
            {
                return null;
            }
            index = ((SybaseASEUniqueConstraint)uniqueConstraint).getSystemGenedIndex();
        }
        ////////////////////////////////////
        Table parentTable = null;
        String parentKey = null;
        if(primKey != null)
        {
            parentTable = primKey.getBaseTable();
            parentKey = super.getKeyColumns(primKey, quoteIdentifiers);
        }
        else if (uniqueConstraint != null)
        {
            parentTable = uniqueConstraint.getBaseTable();
            parentKey = super.getKeyColumns(uniqueConstraint, quoteIdentifiers);
        }
        else if (index != null)
        {
            parentKey = super.getParentKeyColumns(index, quoteIdentifiers);
        }
        if (parentTable == null)
        {
            // TODO report error
            return null;
        }
        
        Catalog catalog = foreignKey.getBaseTable().getSchema().getCatalog();
        String catalogName = getName(catalog, quoteIdentifiers, qualifyNames);
        Catalog parentCatalog = parentTable.getSchema().getCatalog();
        String parentCatalogName = getName(parentCatalog, quoteIdentifiers, qualifyNames);
        String tableName=null;
        String parentTableName=null;
        boolean catalogNameQuoted = !catalogName.equals(catalog.getName());
        if(quoteIdentifiers)
        {
        	if (catalogNameQuoted)
        	{
            	tableName = SQLDevToolsUtil.quoteWhenNecessary(foreignKey.getBaseTable().getSchema().getName(), getDatabaseIdentifier(foreignKey.getBaseTable()))
                + DOT + SQLUtil.quote(foreignKey.getBaseTable().getName(), DOUBLE_QUOTE);
                parentTableName = SQLDevToolsUtil.quoteWhenNecessary(parentTable.getSchema().getName(), getDatabaseIdentifier(parentTable))
                + DOT + SQLUtil.quote(parentTable.getName(), DOUBLE_QUOTE);
        	}
        	else
        	{
	        	tableName = getName(foreignKey.getBaseTable(),quoteIdentifiers, qualifyNames);
	            parentTableName = getName(parentTable,quoteIdentifiers, qualifyNames);
        	}
        }
        else
        {
        	tableName = getName(foreignKey.getBaseTable(),quoteIdentifiers, qualifyNames);
        	parentTableName = getName(parentTable,quoteIdentifiers, qualifyNames);
        }
        

        if(catalog!=parentCatalog)
        {
            //table name format is like this: database.owner.table_name or database..table_name
            if(qualifyNames)
            {
                parentTableName = parentCatalogName+DOT+parentTableName;
            }
            else
            {
                parentTableName = parentCatalogName+DOT+DOT+parentTableName;
            }
        }
        StringBuffer statement = new StringBuffer(128);
        statement.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(tableName)
                .append(SPACE).append(ADD).append(SPACE);
        String name = foreignKey.getName();
        if (name != null && name.trim().length() != 0)
        {
            if (quoteIdentifiers)
            {
                name = super.getDoubleQuotedString(name);
            }
            statement.append(CONSTRAINT).append(SPACE).append(name).append(SPACE);
        }
        statement.append(FOREIGN_KEY).append(SPACE).append(LEFT_PARENTHESIS).append(
                getKeyColumns(foreignKey, quoteIdentifiers)).append(RIGHT_PARENTHESIS).append(NEWLINE);
        statement.append(TAB).append(REFERENCES).append(SPACE).append(parentTableName)
                .append(SPACE).append(LEFT_PARENTHESIS)
                .append(parentKey).append(RIGHT_PARENTHESIS);
        statement.append(this.getFKMatchType(foreignKey));
        return new String[]
        {
            statement.toString()
        };
    }

    public String[] createIndex(Index index, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        SybaseASEIndex aseIndex = (SybaseASEIndex) index;
        ArrayList statements = new ArrayList();
        StringBuffer statement = new StringBuffer();
        statement.append(CREATE + SPACE);

        EClass clazz = index.eClass();
        if (index.eIsSet(clazz.getEStructuralFeature(SQLConstraintsPackage.INDEX__UNIQUE)) && index.isUnique())
        {
            statement.append(UNIQUE + SPACE);
        }

        if (index.eIsSet(clazz.getEStructuralFeature(SQLConstraintsPackage.INDEX__CLUSTERED)))
        {
            if (index.isClustered())
            {
                statement.append(CLUSTERED + SPACE);
            }
            else
            {
                statement.append(NONCLUSTERED + SPACE);
            }
        }
        statement.append(INDEX + SPACE + getName(index, quoteIdentifiers, qualifyNames) + SPACE + ON + SPACE
                + getName(index.getTable(), quoteIdentifiers, qualifyNames) + SPACE + LEFT_PARENTHESIS
                + getIndexKeyColumns(index, quoteIdentifiers) + RIGHT_PARENTHESIS);

        // With clause start
        boolean needComma = false;
        // fillfactor
        if (aseIndex.getFillFactor() > 0)
        {
            addComma(needComma, statement);
            statement.append(getIndexWithClause(needComma) + FILLFACTOR + SPACE + EQUAL + SPACE
                    + aseIndex.getFillFactor());
            needComma = true;
        }
        // maxrowperpage
        if (aseIndex.getMaxRowPerPage() >= 0)
        {
            addComma(needComma, statement);
            statement.append(getIndexWithClause(needComma) + MAX_ROWS_PER_PAGE + SPACE + EQUAL + SPACE
                    + aseIndex.getMaxRowPerPage());
            needComma = true;
        }
        // reserverpagegap
        if (aseIndex.getReversePageGap() >= 0)
        {
            addComma(needComma, statement);
            statement.append(getIndexWithClause(needComma) + RESERVEPAGEGAP + SPACE + EQUAL + SPACE
                    + aseIndex.getReversePageGap());
            needComma = true;
        }
        // consumer number
        if (aseIndex.getConsumerNum() >= 0)
        {
            addComma(needComma, statement);
            statement.append(getIndexWithClause(needComma) + CONSUMERS + SPACE + EQUAL + SPACE
                    + aseIndex.getConsumerNum());
            needComma = true;
        }
        // duplicate key CREATE INDEX options nonunique and ignore_dup_key are mutually exclusive
        if (aseIndex.eIsSet(clazz.getEStructuralFeature(SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__IGNORE_DUPLICATE_KEY))
                && aseIndex.isIgnoreDuplicateKey() && aseIndex.isUnique())
        {
            addComma(needComma, statement);
            statement.append(getIndexWithClause(needComma) + IGNORE_DUPLICATE_KEY);
            needComma = true;
        }
        // sorted data
        if (aseIndex.eIsSet(clazz.getEStructuralFeature(SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SORTED_DATA)) && aseIndex.isSortedData())
        {
            addComma(needComma, statement);
            statement.append(getIndexWithClause(needComma) + SORTED_DATA);
            needComma = true;
        }
        // Duplicate row
        if (aseIndex.eIsSet(clazz.getEStructuralFeature(SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__IGNORE_DUPLICATE_ROW))
                && aseIndex.isIgnoreDuplicateRow() && aseIndex.isClustered())
        {
            addComma(needComma, statement);
            statement.append(getIndexWithClause(needComma) + IGNORE_DUPLICATE_ROW);
            needComma = true;
        }
        // allow_duplicate row
        if (aseIndex.eIsSet(clazz.getEStructuralFeature(SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__ALLOW_DUPLICATE_ROW))
                && aseIndex.isAllowDuplicateRow() && aseIndex.isClustered())
        {
            addComma(needComma, statement);
            statement.append(getIndexWithClause(needComma) + ALLOW_DUPLICATE_ROW);
            needComma = true;
        }
        // statistics
        if (aseIndex.getStatisticsStep() >= 0)
        {
            addComma(needComma, statement);
            statement.append(getIndexWithClause(needComma) + STATISTICS_USING + SPACE + aseIndex.getStatisticsStep()
                    + SPACE + VALUES.toLowerCase());
            needComma = true;
        }
        // With clause end
        // segment
        if (aseIndex.eIsSet(clazz.getEStructuralFeature(SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SEGMENT)) && aseIndex.getSegment() != null)
        {
            statement.append(NEWLINE + TAB + ON + SPACE + getSingleQuotedString(aseIndex.getSegment().getName()));
        }
        // partitions
        if (aseIndex.eIsSet(clazz.getEStructuralFeature(SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__PARTITIONS))
                && aseIndex.getPartitions().size() > 0)
        {
            statement.append(NEWLINE + TAB);
            if (aseIndex.isLocalIndex())
            {
                statement.append(LOCAL + SPACE + INDEX + SPACE);
            }
            statement.append(ASEDdlUtils.getPartitionName(aseIndex, quoteIdentifiers, qualifyNames));
        }
        // Add create index statment
        statements.add(statement.toString()); 
        String[] cacheStatments = ASEDdlUtils.getAllCacheStatement(aseIndex, qualifyNames, fullSyntax, getDatabaseIdentifier(aseIndex));
        if (cacheStatments.length > 0 || aseIndex.getFillFactor() > 0)
        {
            String setNewUserStr = SybaseASESQLUtil.getSetNewUserStatement(index.getTable().getSchema());
            if(!setNewUserStr.equals(EMPTY_STRING))
            {
                statements.add(setNewUserStr);
            }
        }
        for (int j = 0; j < cacheStatments.length; j++)
        {
            statements.add(cacheStatments[j]);
        }        
        String indexNameWithTableName = index.getTable().getName()   + DOT + index.getName();
        if (quoteIdentifiers)
        {
            indexNameWithTableName = index.getTable().getName() + DOT + SQLDevToolsUtil.quoteWhenNecessary(index.getName(),getDatabaseIdentifier(index));
        }
        // fill factor
        if (aseIndex.getFillFactor() > 0)
        {
            statements.add(ASEDdlUtils.getChangeAttributeStatement(getSingleQuotedString(indexNameWithTableName),
                    getSingleQuotedString(FILLFACTOR), String.valueOf(aseIndex.getFillFactor())));
        }
        if (cacheStatments.length > 0 || aseIndex.getFillFactor() > 0)
        {
            String setUserDbo = SybaseASESQLUtil.getSetUserDBOStatement(index.getTable().getSchema());
            if(!setUserDbo.equals(""))
            {
                statements.add(setUserDbo);
            }
        }
        
        return (String[]) statements.toArray(new String[statements.size()]);
    }

    /**
     * <pre>
     *    CREATE VIEW
     *    [ owner.]view-name [ ( column-name, . . . ) ]
     *    AS select [distinct]select-statement
     *    [ WITH CHECK OPTION ]
     * </pre>
     * 
     * @author hfeng
     */
    public String[] createView(ViewTable view, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        if (!(view instanceof SybaseASEViewTable))
        {
            return null;
        }
        StringBuffer viewDefinition = new StringBuffer("");
        viewDefinition.append(CREATE).append(SPACE).append(VIEW).append(SPACE).append(
                getName(view, quoteIdentifiers, qualifyNames)).append(SPACE);

        String columns = getViewColumnList(view, quoteIdentifiers);
        if (columns != null)
        {
            viewDefinition.append(LEFT_PARENTHESIS).append(columns).append(RIGHT_PARENTHESIS).append(SPACE);
        }
        viewDefinition.append(AS).append(NEWLINE);
        viewDefinition.append(view.getQueryExpression().getSQL());

        if (((SybaseASEViewTable) view).isWithCheckOption())
        {
            viewDefinition.append(NEWLINE).append(CHECKOPTION);
        }

        return new String[]
        {
            viewDefinition.toString()
        };
    }

    /**
     * @author triston
     * @param table
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     */
    protected String[] createProxyTable(SybaseASEProxyTable table, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        if (table.isExisting() == true)
        {
            return createExistingProxyTable(table, quoteIdentifiers, qualifyNames,fullSyntax);
        }
        else
        {
            return createNonExistingTable(table, quoteIdentifiers, qualifyNames,fullSyntax);
        }
    }

    /**
     * Returns the SQL Statement for creating a webservice table.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *  sp_webservices 'add','wsdl_uri'[,sds_name]
     *  [,'method_name=proxy_table'
     *  [,method_name=proxy_table]*']
     *  
     * </pre>
     * 
     * @param ws
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     */
    public String[] createWebServiceTable(SybaseASEWebServiceTable ws, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        StringBuffer methodClause = new StringBuffer(EMPTY_STRING);
        if (ws.getName() != null && ws.getMethod() != null)
        {
            methodClause.append(SINGLE_QUOTE).append(ws.getMethod()).append(SybaseDdlBuilder.EQUAL).append(
                    getName(ws, quoteIdentifiers, qualifyNames)).append(SINGLE_QUOTE);
        }
        StringBuffer stmt = new StringBuffer();
        stmt.append(SP_WEBSERVICES).append(SPACE).append(SINGLE_QUOTE).append(ADD).append(SINGLE_QUOTE).append(COMMA)
                .append(SINGLE_QUOTE).append(ws.getExternalPath()).append(SINGLE_QUOTE).append(COMMA).append(NEWLINE)
                .append(methodClause);

        return new String[]
        {
            stmt.toString()
        };
    }

    /**
     * Returns the SQL Statement for droping a webservice table.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *  sp_webservices 'remove','wsdl_uri'[,sds_name]
     * </pre>
     * 
     * @param ws
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     */
    public String dropWebServiceTable(SybaseASEWebServiceTable ws, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer result = new StringBuffer();
        result.append(SP_WEBSERVICES).append(SPACE).append(SINGLE_QUOTE).append(SybaseDdlBuilder.REMOVE).append(
                SINGLE_QUOTE).append(COMMA).append(SINGLE_QUOTE).append(ws.getExternalPath()).append(SINGLE_QUOTE);
        return result.toString();
    }
    
    public String dropCatalog(Catalog db, boolean quoteIdentifiers, boolean qualifyNames) 
    {
    	String useMaseter = new StringBuffer().append(USE).append(SPACE).append(master).toString();
    	String dropStatement = useMaseter + NEWLINE + TAB + super.dropCatalog(db, quoteIdentifiers, qualifyNames);
    	String dbName = db.getName();
    	String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_CATALOG, new Object[]{dbName});
    	return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    } 

	public String dropProcedure(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String dropStatement = super.dropProcedure(procedure, quoteIdentifiers, qualifyNames);
        String ownerName = procedure.getSchema().getName();
        String procName = procedure.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_PROCEDURE, new Object[]{ownerName, procName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }

    /**
     * Returns the SQL Statement for droping user defined type.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *  sp_droptype typename
     *   
     * </pre>
     * 
     * @param aseUDT
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     * 
     * @author renj
     */
    public String dropUserDefinedType(UserDefinedType udt, boolean quoteIdentifiers, boolean qualifyNames)
    {
        SybaseASEUserDefinedType aseUDT = (SybaseASEUserDefinedType) udt;
        StringBuffer sb = new StringBuffer(128);
        sb.append(EXEC).append(SPACE).append(SP_DROPTYPE).append(SPACE).append(SQLUtil.quote(aseUDT.getName(),SINGLE_QUOTE));

        String dropStatement = sb.toString();
        String udtName = udt.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_UDT, new Object[]{udtName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }

    /**
     * Returns the SQL Statement for droping default.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *  drop default [owner.]default_name
     *           [, [owner.]default_name] ...
     *   
     * </pre>
     * 
     * @param aseDefault
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     * 
     * @author renj
     */
    public String dropDefault(SybaseASEDefault aseDefault, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer sb = new StringBuffer(128);
        sb.append(DROP).append(SPACE).append(DEFAULT).append(SPACE).append(
                getName(aseDefault, quoteIdentifiers, qualifyNames)).toString();
        String dropStatement = sb.toString();
        
        String ownerName = aseDefault.getSchema().getName();
        String defaultName = aseDefault.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_DEFAULT, new Object[]{ownerName, defaultName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }

    /**
     * Returns the SQL Statement for droping rule.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *  drop rule [owner.]rule_name [, [owner.]rule_name] ...
     *   
     * </pre>
     * 
     * @param aseRule
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     * 
     * @author renj
     */
    public String dropRule(SybaseASERule aseRule, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer sb = new StringBuffer(128);
        sb.append(DROP).append(SPACE).append(RULE).append(SPACE).append(
                getName(aseRule, quoteIdentifiers, qualifyNames)).toString();

        String dropStatement = sb.toString();
        
        String ownerName = aseRule.getSchema().getName();
        String ruleName = aseRule.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_RULE, new Object[]{ownerName, ruleName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }
    
    public String dropTrigger(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String dropStatement = super.dropTrigger(trigger, quoteIdentifiers, qualifyNames);
        
        String creatorName = trigger.getSchema().getName();
        String triggerName = trigger.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_TRIGGER, new Object[]{creatorName, triggerName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }
    
    public String dropTable(BaseTable table, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String dropStatement = super.dropTable(table, quoteIdentifiers, qualifyNames);
        
        if (table instanceof SybaseASEProxyTable)
        {
            StringBuffer sb = new StringBuffer();
            sb.append(dropStatement).append(NEWLINE).append(
                    dropObjectdef((SybaseASEProxyTable) table, quoteIdentifiers));
            dropStatement = sb.toString();
        }
        
        String ownerName = table.getSchema().getName();
        String tableName = table.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_TABLE, new Object[]{ownerName, tableName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }
    
    private String dropObjectdef(SybaseASEProxyTable table, boolean quoteIdentifiers)
    {
        StringBuffer sb = new StringBuffer();
        String setUserStr = EMPTY_STRING;
        Schema creator = table.getSchema();
        if(creator!=null)
        {
            setUserStr = SybaseASESQLUtil.getSetNewUserStatement(creator);;
        }
        if(!setUserStr.equals(EMPTY_STRING))
        {
            sb.append(setUserStr).append(NEWLINE);
        }
        //sp_dropobjectdef can not use the qualify name
        sb.append(TAB).append(EXEC).append(SPACE).append(SP_DROPOBJECTDEF).append(SPACE).append(
                getName(table, quoteIdentifiers, false));
        String setUserDbo = SybaseASESQLUtil.getSetUserDBOStatement(creator);
        if(!setUserDbo.equals(EMPTY_STRING))
        {
            sb.append(NEWLINE).append(TAB).append(setUserDbo);
        }
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_OBJECT_DEF, new Object[]
        {
            table.getName()
        });
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]
        {
            existingCheck, sb.toString()
        });
    }
    
    public String dropView(ViewTable view, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String dropStatement =  super.dropView(view, quoteIdentifiers, qualifyNames);
        
        String ownerName = view.getSchema().getName();
        String viewName = view.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_VIEW, new Object[]{ownerName, viewName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }
    
    
    protected String getColumnString(Column column, boolean quoteIdentifiers)
    {
        StringBuffer columnDefinition = new StringBuffer();
        SybaseASEColumn aseColumn = (SybaseASEColumn) column;
        String columnName = column.getName();
        if (quoteIdentifiers)
        {
            columnName = this.getDoubleQuotedString(columnName);
        }
        columnDefinition.append(columnName).append(SPACE);
        ValueExpression v = column.getGenerateExpression();
        
        if (aseColumn.isComputedColumn() && v != null)
        {
            columnDefinition.append(SPACE).append(AS).append(SPACE).append(v.getSQL()).append(SPACE);
            if (aseColumn.isMaterialized())
            {
                columnDefinition.append(MATERIALIZED).append(SPACE);
            }
            else
            {
                columnDefinition.append(NOT).append(SPACE).append(MATERIALIZED).append(SPACE);
            }
//            return columnDefinition.toString();
        }
        if (!aseColumn.isComputedColumn())
        {
            columnDefinition.append(getDataTypeString(column, column.getTable().getSchema())).append(SPACE);
            String defaultValue = column.getDefaultValue();
            
            //Only generate for inline default value.
            if (defaultValue != null&&aseColumn.getBoundDefault()==null)
            {
                columnDefinition.append(DEFAULT).append(SPACE).append(defaultValue);
            }
            IdentitySpecifier spec = column.getIdentitySpecifier();
            if (spec != null)
            {
                columnDefinition.append(SPACE).append(IDENTITY).append(SPACE);
            }
            else
            {
                if (!column.isNullable())
                {
                    columnDefinition.append(SPACE).append(NOT).append(SPACE).append(NULL);
                } 
                else
                {
                	columnDefinition.append(SPACE).append(NULL);
                }
            }
        }

        SybaseASEColumnCheckConstraint checkConstraint = aseColumn.getColumnCheck();
        if (checkConstraint != null)
        {
            columnDefinition.append(SPACE).append(getAddCheckConstraintClause(checkConstraint, quoteIdentifiers))
                    .append(SPACE);
        }
        return columnDefinition.toString();
    }

    protected String getIdentityString(IdentitySpecifier identitySpecifier)
    {
        String clause = "START WITH " + identitySpecifier.getStartValue() //$NON-NLS-1$
                + " ,INCREMENT BY " + identitySpecifier.getIncrement(); //$NON-NLS-1$
        return clause;
    }

    protected String getParameterStyleOption(Routine routine)
    {
        StringBuffer sb = new StringBuffer();
        return sb.append(TAB).append(PARAMETER_STYLE).append(SPACE).append(JAVA).append(NEWLINE).toString();
    }

    protected String getLanguage(Routine routine)
    {
        StringBuffer sb = new StringBuffer();
        return sb.append(TAB).append(LANGUAGE).append(SPACE).append(JAVA).append(NEWLINE).toString();
    }

    protected String getSQLAccess(Routine routine)
    {
        StringBuffer sb = new StringBuffer();
        return sb.append(TAB).append(MODIFIES_SQL_DATA).append(NEWLINE).toString();
    }

    protected String getExternalName(Routine routine)
    {
        StringBuffer sb = new StringBuffer();
        return sb.append(TAB).append(EXTERNAL_NAME).append(SPACE).append(routine.getExternalName()).append(NEWLINE)
                .toString();
    }

    protected String getFunctionReturnsClause(UserDefinedFunction function)
    {
        if (function.getReturnScalar() != null)
        {
            Parameter scaler = function.getReturnScalar();
            StringBuffer sb = new StringBuffer();
            sb.append(RETURNS).append(SPACE).append(getDataTypeString(scaler, function.getSchema())).append(NEWLINE);
            return sb.toString();
        }
        return EMPTY_STRING;
    }

    /**
     * Returns the SQL Statement for LOCKING SCHEMA [lock {datarows | datapages | allpages }]
     * 
     * @param table
     * @return
     */
    protected String getLockSchemaStatement(BaseTable table)
    {
        SybaseASEBaseTable aseTable = (SybaseASEBaseTable) table;
        LockingSchemaType schemaType = aseTable.getLockSchema();
        if (schemaType == null)
        {
            return null;
        }
        switch (schemaType.getValue())
        {
            case LockingSchemaType.LOCKALLPAGES:
                return LOCK + SPACE + ALLPAGES;
            case LockingSchemaType.LOCKDATAPAGES:
                return LOCK + SPACE + DATAPAGES;
            case LockingSchemaType.LOCKDATAROWS:
                return LOCK + SPACE + DATAROWS;
        }
        return null;
    }

    /**
     * Returns the SQL Statement for WITH clause.
     * 
     * <pre>
     *  
     *  [with { max_rows_per_page = num_rows,
     *          exp_row_size = num_bytes,
     *          reservepagegap = num_pages, 
     *          identity_gap = value } ] 
     * </pre>
     * 
     * @param table
     * @return
     */
    protected String getWithClause(BaseTable table)
    {
        SybaseASEBaseTable aseTable = (SybaseASEBaseTable) table;
        int maxRowPerPage = aseTable.getMaxRowPerPage();
        int expRowSize = aseTable.getExpRowSize();
        int reservePageGap = aseTable.getReservePageGap();
        int identityGap = aseTable.getIdentityGap();
        if (maxRowPerPage < 0 && expRowSize < 0 && reservePageGap < 0 && identityGap < 0)
        {
            return null;
        }
        else
        {
            StringBuffer with = new StringBuffer();
            boolean start = true;
            if (maxRowPerPage >= 0)
            {
                with.append(WITH).append(SPACE).append(MAX_ROWS_PER_PAGE).append("=").append(maxRowPerPage);
                start = false;
            }
            if (expRowSize >= 0)
            {
                if (start)
                {
                    with.append(WITH).append(SPACE).append(EXP_ROW_SIZE).append("=").append(expRowSize);
                    start = false;
                }
                else
                {
                    with.append(",").append(SPACE).append(EXP_ROW_SIZE).append("=").append(expRowSize);
                }

            }
            if (reservePageGap >= 0)
            {
                if (start)
                {
                    with.append(WITH).append(SPACE).append(RESERVEPAGEGAP).append("=").append(reservePageGap);
                    start = false;
                }
                else
                {
                    with.append(",").append(SPACE).append(RESERVEPAGEGAP).append("=").append(reservePageGap);
                }

            }
            if (identityGap >= 0)
            {
                if (start)
                {
                    with.append(WITH).append(SPACE).append(IDENTITY_GAP).append("=").append(identityGap);
                    start = false;
                }
                else
                {
                    with.append(",").append(SPACE).append(IDENTITY_GAP).append("=").append(identityGap);
                }

            }
            if (with.length() > 0)
            {
                return with.toString();
            }
            else
            {
                return null;
            }
        }
    }

    /**
     * Returns the SQL Statement for segment clause [on segment_name]
     * 
     * @param table
     * @param quoteIdentifiers
     * @return
     */
    protected String getSegmentStatement(BaseTable table, boolean quoteIdentifiers)
    {
        SybaseASEBaseTable aseTable = (SybaseASEBaseTable) table;
        SybaseASESegment segment = aseTable.getSegment();
        if (segment == null)
        {
            return null;
        }
        else
        {
            String segmentName = segment.getName();
            segmentName = SQLUtil.quote(segmentName, "'");
            return ON + SPACE + segmentName;

        }
    }

	/**
     * Returns the SQL Statement for partition clause.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *  partition_clause:::= partition by range 
     *     ( column_name[, column_name ]...) ( [ partition_name ] values &lt;= ( { constant | MAX }
     *      [, { constant | MAX } ] ...) [ on segment_name ] 
     *      [, [ partition_name ] values &lt;= ( { constant | MAX }
     *         [, { constant | MAX } ] ...) [ on segment_name ] ]...) 
     *         
     *  | partition by hash (column_name[, column_name ]...) 
     *      { ( partition_name [ on segment_name ] 
     *         [,partition_name [ on segment_name ] ]...)
     *      | number_of_partitions 
     *         [ on (segment_name[, segment_name ] ...) ] } 
     *         
     *  | partition by list (column_name) 
     *      ( [ partition_name ] values ( constant[, constant ] ...)
     *           [ on segment_name ] 
     *           [, [partition_name ] values ( constant[, constant ] ...) 
     *              [ on segment_name ] ] ...) 
     *              
     *  | partition by roundrobin 
     *       { ( partition_name [ on segment_name ] 
     *            [, partition_name [ on segment_name ] ]...) 
     *       | number_of_partitions 
     *            [ on (segment_name [, segment_name ]...) ] }
     * </pre>
     * 
     * @param table
     * @param quoteIdentifiers
     * @return
     */
    protected String getPartitionStatement(BaseTable table, boolean quoteIdentifiers)
    {
        SybaseASEDatabase db = (SybaseASEDatabase) table.getSchema().getCatalog().getDatabase();
        if (!db.isPartitionsApplicable())
        {
            return null;
        }
        SybaseASEBaseTable aseTable = (SybaseASEBaseTable) table;
        SybaseASEPartition partition = aseTable.getPartitionCondition();
        if (partition == null)
        {
            return null;
        }
        StringBuffer stmt = new StringBuffer();
        stmt.append(PARTITION).append(SPACE).append(BY).append(SPACE);

        if (partition instanceof SybaseASEHashPartition)
        {
            SybaseASEHashPartition hPartition = (SybaseASEHashPartition) partition;
            stmt.append(HASH).append(SPACE);

            boolean start = true;
            for (Iterator iter = hPartition.getColumns().iterator(); iter.hasNext();)
            {
                Column element = (Column) iter.next();
                if (start)
                {
                    stmt.append(LEFT_PARENTHESIS).append(
                            quoteIdentifiers ? getDoubleQuotedString(element.getName()) : element.getName());
                    start = false;
                }
                else
                {
                    stmt.append(COMMA).append(
                            quoteIdentifiers ? getDoubleQuotedString(element.getName()) : element.getName());
                }

            }
            if (!start)
            {
                stmt.append(RIGHT_PARENTHESIS).append(NEWLINE);
            }

            PartitionNumInSegments partitionNumSeg = hPartition.getPartitionNumInSegments();
            start = true;
            if (partitionNumSeg != null)
            {
                stmt.append(partitionNumSeg.getPartitionNumb()).append(SPACE).append(ON).append(NEWLINE);
                stmt.append(LEFT_PARENTHESIS);
                for (Iterator iter = partitionNumSeg.getSegment().iterator(); iter.hasNext();)
                {
                    SybaseASESegment element = (SybaseASESegment) iter.next();
                    if (start)
                    {
                        stmt.append(SQLUtil.quote(element.getName(), "'"));
                        start = false;
                    }
                    else
                    {
                        stmt.append(COMMA).append(SQLUtil.quote(element.getName(), "'"));
                    }

                }
                stmt.append(RIGHT_PARENTHESIS).append(NEWLINE);
            }
            else
            {
                start = true;
                for (Iterator iter = hPartition.getPartitionSegmentPairs().iterator(); iter.hasNext();)
                {
                    PartitionSegmentPair element = (PartitionSegmentPair) iter.next();
                    if (start)
                    {
                        stmt.append(LEFT_PARENTHESIS).append(
                                quoteIdentifiers ? getDoubleQuotedString(element.getPartitionName()) : element
                                        .getPartitionName());
                        start = false;
                    }
                    else
                    {
                        stmt.append(COMMA).append(NEWLINE).append(
                                quoteIdentifiers ? getDoubleQuotedString(element.getPartitionName()) : element
                                        .getPartitionName());
                    }

                    SybaseASESegment segment = element.getSegment();
                    if (segment != null && segment.getName() != null)
                    {
                        stmt.append(SPACE).append(ON).append(SPACE).append(SQLUtil.quote(segment.getName(), "'"));
                    }
                }

                if (!start)
                {
                    stmt.append(RIGHT_PARENTHESIS).append(NEWLINE);
                }
            }
        }
        if (partition instanceof SybaseASERoundrobinPartition)
        {
            SybaseASERoundrobinPartition rPartition = (SybaseASERoundrobinPartition) partition;
            stmt.append(ROUNDROBIN).append(SPACE);

            PartitionNumInSegments partitionNumSeg = rPartition.getPartitionNumInSegments();
            boolean start = true;
            if (partitionNumSeg != null)
            {
                stmt.append(partitionNumSeg.getPartitionNumb()).append(SPACE).append(ON).append(NEWLINE);
                stmt.append(LEFT_PARENTHESIS);
                for (Iterator iter = partitionNumSeg.getSegment().iterator(); iter.hasNext();)
                {
                    SybaseASESegment element = (SybaseASESegment) iter.next();
                    if (start)
                    {
                        stmt.append(SQLUtil.quote(element.getName(), "'"));
                        start = false;
                    }
                    else
                    {
                        stmt.append(COMMA).append(SQLUtil.quote(element.getName(), "'"));
                    }

                }
                stmt.append(RIGHT_PARENTHESIS).append(NEWLINE);
            }
            else
            {
                start = true;
                for (Iterator iter = rPartition.getPartitionSegmentPairs().iterator(); iter.hasNext();)
                {
                    PartitionSegmentPair element = (PartitionSegmentPair) iter.next();
                    if (start)
                    {
                        stmt.append(LEFT_PARENTHESIS).append(
                                quoteIdentifiers ? getDoubleQuotedString(element.getPartitionName()) : element
                                        .getPartitionName());
                        start = false;
                    }
                    else
                    {
                        stmt.append(COMMA).append(NEWLINE).append(
                                quoteIdentifiers ? getDoubleQuotedString(element.getPartitionName()) : element
                                        .getPartitionName());
                    }

                    SybaseASESegment segment = element.getSegment();
                    if (segment != null && segment.getName() != null)
                    {
                        stmt.append(SPACE).append(ON).append(SPACE).append(SQLUtil.quote(segment.getName(), "'"));
                    }
                }

                if (!start)
                {
                    stmt.append(RIGHT_PARENTHESIS).append(NEWLINE);
                }
            }
        }

        if (partition instanceof SybaseASEListPartition)
        {
            SybaseASEListPartition lPartition = (SybaseASEListPartition) partition;
            stmt.append(LIST).append(SPACE);
            SybaseASEColumn column = lPartition.getColumn();
            stmt.append(LEFT_PARENTHESIS).append(
                    quoteIdentifiers ? getDoubleQuotedString(column.getName()) : column.getName()).append(
                    RIGHT_PARENTHESIS).append(NEWLINE);
            boolean start = true;

            for (Iterator iter = lPartition.getListPartitionItems().iterator(); iter.hasNext();)
            {
                ListRangePartitionItem element = (ListRangePartitionItem) iter.next();
                if (start)
                {
                    stmt.append(LEFT_PARENTHESIS).append(
                            quoteIdentifiers ? getDoubleQuotedString(element.getPartitionName()) : element
                                    .getPartitionName());
                    start = false;
                }
                else
                {
                    stmt.append(COMMA).append(NEWLINE).append(
                            quoteIdentifiers ? getDoubleQuotedString(element.getPartitionName()) : element
                                    .getPartitionName());
                }

                stmt.append(SPACE).append(VALUES).append(SPACE).append(LEFT_PARENTHESIS);
                boolean valueStart = true;
                for (Iterator iterator = element.getValues().iterator(); iterator.hasNext();)
                {
                    String value = (String) iterator.next();
                    if (valueStart)
                    {
                        stmt.append(value);
                        valueStart = false;
                    }
                    else
                    {
                        stmt.append(COMMA).append(value);
                    }
                }
                stmt.append(RIGHT_PARENTHESIS).append(SPACE);

                SybaseASESegment segment = element.getSegment();
                if (segment != null && segment.getName() != null)
                {
                    stmt.append(ON).append(SPACE).append(SQLUtil.quote(segment.getName(), "'"));
                }
            }
            if (!start)
            {
                stmt.append(RIGHT_PARENTHESIS).append(NEWLINE);
            }

        }
        if (partition instanceof SybaseASERangePartition)
        {
            SybaseASERangePartition rPartition = (SybaseASERangePartition) partition;
            stmt.append(RANGE).append(SPACE);
            boolean start = true;
            for (Iterator iter = rPartition.getColumns().iterator(); iter.hasNext();)
            {
                Column element = (Column) iter.next();
                if (start)
                {
                    stmt.append(LEFT_PARENTHESIS).append(
                            quoteIdentifiers ? getDoubleQuotedString(element.getName()) : element.getName());
                    start = false;
                }
                else
                {
                    stmt.append(COMMA).append(
                            quoteIdentifiers ? getDoubleQuotedString(element.getName()) : element.getName());
                }

            }
            if (!start)
            {
                stmt.append(RIGHT_PARENTHESIS).append(NEWLINE);
            }

            start = true;
            for (Iterator iter = rPartition.getRangePartitionItems().iterator(); iter.hasNext();)
            {
                ListRangePartitionItem element = (ListRangePartitionItem) iter.next();
                if (start)
                {
                    stmt.append(LEFT_PARENTHESIS).append(
                            quoteIdentifiers ? getDoubleQuotedString(element.getPartitionName()) : element
                                    .getPartitionName());
                    start = false;
                }
                else
                {
                    stmt.append(COMMA).append(NEWLINE).append(
                            quoteIdentifiers ? getDoubleQuotedString(element.getPartitionName()) : element
                                    .getPartitionName());
                }

                stmt.append(SPACE).append(VALUES).append(SPACE).append(LESSEQUAL).append(SPACE)
                        .append(LEFT_PARENTHESIS);
                boolean valueStart = true;
                for (Iterator iterator = element.getValues().iterator(); iterator.hasNext();)
                {
                    String value = (String) iterator.next();
                    if (valueStart)
                    {
                        stmt.append(value);
                        valueStart = false;
                    }
                    else
                    {
                        stmt.append(COMMA).append(value);
                    }
                }
                stmt.append(RIGHT_PARENTHESIS).append(SPACE);

                SybaseASESegment segment = element.getSegment();
                if (segment != null && segment.getName() != null)
                {
                    stmt.append(ON).append(SPACE).append(SQLUtil.quote(segment.getName(), "'"));
                }
            }
            if (!start)
            {
                stmt.append(RIGHT_PARENTHESIS).append(NEWLINE);
            }
        }

        if (stmt.length() > 0)
        {
            return stmt.toString();
        }
        else
        {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlBuilder#getAddUniqueConstraintClause(org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint,
     *      boolean)
     */
    protected String getAddUniqueConstraintClause(UniqueConstraint constraint, SybaseASEIndex index,
            boolean quoteIdentifiers)
    {
        StringBuffer statement = new StringBuffer(128);
        // After talk with Samuel, regard isSystemGenedName() as a reserved method,
        // we just judge by a constraint name is null(or length=0) or not now
        // if(!aseConstraint.isSystemGenedName())
        // {
        // String constraintName = getName(constraint, quoteIdentifiers);
        // sb.append(constraintName).append(SPACE);
        // }
        String name = constraint.getName();
        if (name != null && name.trim().length() != 0)
        {
            if (quoteIdentifiers)
            {
                name = super.getDoubleQuotedString(name);
            }
            statement.append(CONSTRAINT).append(SPACE).append(name).append(SPACE);
        }
        statement.append(getUniqueConstraintType(constraint)).append(SPACE);
        if (index.isClustered())
        {
            statement.append(CLUSTERED).append(SPACE);
        }
        else if (!index.isClustered())
        {
            statement.append(NONCLUSTERED).append(SPACE);
        }
        statement.append(LEFT_PARENTHESIS).append(getKeyColumns(index, quoteIdentifiers)).append(RIGHT_PARENTHESIS);
        if (index.getFillFactor() > 0 || index.getReversePageGap() > 0 || index.getMaxRowPerPage() > 0)
        {
            statement.append(NEWLINE).append(TAB).append(WITH).append(SPACE).append(getIndexParameterClause(index));
        }
        SybaseASESegment segment = index.getSegment();
        if (segment != null)
        {
            String segmentName = segment.getName();
            segmentName = super.getSingleQuotedString(segmentName);
            
            statement.append(NEWLINE).append(TAB).append(ON).append(SPACE).append(segmentName);
        }
        return statement.toString();
    }

    protected String getKeyColumns(SybaseASEIndex index, boolean quoteIdentifiers)
    {
        StringBuffer statement = new StringBuffer(128);
        Iterator it = index.getMembers().iterator();
        if (it.hasNext())
        {
            IndexMember idxMember = (IndexMember) it.next();
            Column c = idxMember.getColumn();
            if (quoteIdentifiers)
            {
                statement.append(getDoubleQuotedString(c.getName()));
            }
            else
            {
                statement.append(c.getName());
            }
            statement.append(getIncrementTypeOfColumn(idxMember));
        }
        else
        {
            // TODO report error
            return null;
        }

        while (it.hasNext())
        {
            IndexMember idxMember = (IndexMember) it.next();
            Column c = idxMember.getColumn();
            if (quoteIdentifiers)
            {
                statement.append(COMMA).append(SPACE).append(getDoubleQuotedString(c.getName()));
            }
            else
            {
                statement.append(COMMA).append(SPACE).append(c.getName());
            }
            statement.append(SPACE).append(getIncrementTypeOfColumn(idxMember));
        }
        return statement.toString();
    }

    private String getIncrementTypeOfColumn(IndexMember idxMember)
    {
        if (idxMember.getIncrementType().getName().equals(IncrementType.DESC_LITERAL.getName()))
        {
            return SPACE + DESC;
        }
        return SPACE + ASC;
    }

    protected String getIndexParameterClause(SybaseASEIndex index)
    {
        StringBuffer statement = new StringBuffer(128);
        boolean startFlag = false;
        if (index.getFillFactor() > 0)
        {
            statement.append(FILLFACTOR).append(EQUAL).append(index.getFillFactor());
            startFlag = true;
        }
        if (index.getMaxRowPerPage() > 0)
        {
            if (!startFlag)
            {
                statement.append(MAX_ROWS_PER_PAGE).append(EQUAL).append(index.getMaxRowPerPage());
            }
            else
            {
                statement.append(COMMA).append(MAX_ROWS_PER_PAGE).append(EQUAL).append(index.getMaxRowPerPage());
            }
            startFlag = true;
        }
        if (index.getReversePageGap() > 0)
        {
            if (!startFlag)
            {
                statement.append(RESERVEPAGEGAP).append(EQUAL).append(index.getReversePageGap());
            }
            else
            {
                statement.append(COMMA).append(RESERVEPAGEGAP).append(EQUAL).append(index.getReversePageGap());
            }
        }
        return statement.toString();
    }

    private String getFKMatchType(ForeignKey foreignKey)
    {
        if (foreignKey.getMatch().getName().equals(MatchType.MATCH_FULL_LITERAL.getName()))
        {
            return SPACE + MATCHFULL;
        }
        return ""; //$NON-NLS-1$
    }

    /**
     * Delegate to createTable(), and append the external path to the end.
     * 
     * @param table
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     */
    private String[] createNonExistingTable(SybaseASEProxyTable table, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyn)
    {
        // TODO Auto-generated method stub
        String[] stmts = createBaseTable(table,quoteIdentifiers,qualifyNames,fullSyn);
        StringBuffer stmt =new StringBuffer(stmts[0]);
//        StringBuffer stmt = new StringBuffer(createTable(table,quoteIdentifiers,qualifyNames,fullSyn));
        String externalPath = getExternalPathClause((SybaseASEProxyTable)table);
        if(externalPath!=null)
        {
           stmt.append(externalPath);
        }
        stmts[0]=stmt.toString();
        
        return stmts;
    }

    /**
     * Returns the SQL Statement for creating proxy table.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *  create existing table table_name(column_list)
     *  [on segment_name]
     *  [[external{table|procedure|file}]at pathname
     *  [column delimiter &quot;string&quot;]
     *  
     * </pre>
     * 
     * @param table
     * @param quoteIdentifiers
     * @return
     * @author tristion
     */
    private String[] createExistingProxyTable(SybaseASEProxyTable table, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
     // first map the local table to a remote server
        List stmts = addObjectDef(table, quoteIdentifiers);
        // table name clause
        // should like this:
        // CREATE EXISTING TABLE table_name
        StringBuffer tableNameClause = new StringBuffer(EMPTY_STRING);
        tableNameClause.append(CREATE).append(SPACE).append(EXISTING).append(SPACE).append(TABLE).append(SPACE).append(
                getName(table, quoteIdentifiers, qualifyNames)).append(NEWLINE);

        // column clause
        StringBuffer columnClause = new StringBuffer(EMPTY_STRING);
        EList columns = table.getColumns();
        columnClause.append(LEFT_PARENTHESIS).append(NEWLINE);
        for (int i = 0; i < columns.size(); i++)
        {
            columnClause.append(TAB).append(
                    getColumnString((SybaseASEColumn) columns.get(i), quoteIdentifiers));
            if (i != columns.size() - 1)
            {
                columnClause.append(COMMA);
            }
            columnClause.append(NEWLINE);
        }
        columnClause.append(RIGHT_PARENTHESIS).append(NEWLINE);

        // segment clause
        SybaseASESegment segment = table.getSegment();
        StringBuffer segmentClause = new StringBuffer(EMPTY_STRING);
        if (segment != null)
        {
            segmentClause.append(ON).append(SPACE).append(SQLUtil.quote(segment.getName(), SINGLE_QUOTE)).append(NEWLINE);
        }

        // externaltype clause
        StringBuffer externalTypeClause = new StringBuffer(EMPTY_STRING);
        if (table.getExternalType() != null)
        {
            int type = table.getExternalType().getValue();
            switch (type)
            {
                case ProxyTableExternalType.FILE: {
                    externalTypeClause.append(EXTERNAL).append(SPACE).append(SybaseDdlBuilder.FILE);
                    break;
                }
                case ProxyTableExternalType.PROCEDURE: {
                    externalTypeClause.append(EXTERNAL).append(SPACE).append(PROCEDURE);
                    break;
                }
                default: {
                    externalTypeClause.append(EXTERNAL).append(SPACE).append(TABLE);
                    break;
                }
            }
            externalTypeClause.append(NEWLINE);
        }

        // external path clause
        String externalPathClause = getExternalPathClause(table);

        // column delimiter clause
        StringBuffer colDelimClause = new StringBuffer(EMPTY_STRING);
        if (table.getColumnDelimiter() != null)
        {
            colDelimClause.append(COLUMN_DELIMITER).append(SPACE).append(DOUBLE_QUOTE).append(
                    table.getColumnDelimiter()).append(DOUBLE_QUOTE);
        }

        StringBuffer stmt = new StringBuffer();
        stmt.append(tableNameClause).append(columnClause).append(segmentClause).append(externalTypeClause).append(
                externalPathClause).append(colDelimClause);
        
        stmts.add(stmt.toString());
        
        String[] result = new String[stmts.size()];
        for(int i=0;i<stmts.size();i++)
        {
            result[i]=stmts.get(i).toString();
        }  
        
        return result;
    }

    private String getExternalPathClause(SybaseASEProxyTable table)
    {
        if (table.getExternalPath() != null)
        {
            StringBuffer externalPathClause = new StringBuffer();
//            externalPathClause.append(AT).append(SPACE).append(DOUBLE_QUOTE).append(table.getExternalPath()).append(
//                    DOUBLE_QUOTE).append(NEWLINE);
          externalPathClause.append(AT).append(SPACE).append(SQLUtil.quote(table.getExternalPath(), "\'")).append(NEWLINE);
            return externalPathClause.toString();
        }
        return null;
    }
    
    /**
     * Returns the SQL Statement for calling sp_addobjectdef.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *  sp_addobject tablename, objectdef [,"objecttype"]
     *  
     * </pre>
     * 
     * @param table
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     */
    private List addObjectDef(SybaseASEProxyTable table, boolean quoteIdentifiers)
    {
        List stmts = new ArrayList();
        StringBuffer addObjectDef = new StringBuffer();
        String setUserStr = EMPTY_STRING;
        Schema creator = table.getSchema();
        if(creator!=null)
        {
            setUserStr = SybaseASESQLUtil.getSetNewUserStatement(creator);;
        }
        if(!setUserStr.equals(EMPTY_STRING))
        {
            stmts.add(setUserStr);
        }
        //sp_addobjectdef can not use the qualifyName 
        addObjectDef.append(SP_ADDOBJECTDEF).append(SPACE).append(getName(table, quoteIdentifiers, false))
                .append(COMMA).append(SPACE).append(SQLUtil.quote(table.getExternalPath(), "\'")).append(NEWLINE);
        stmts.add(addObjectDef.toString());
        String setUserDbo = SybaseASESQLUtil.getSetUserDBOStatement(creator);
        if(!setUserDbo.equals(EMPTY_STRING))
        {
            stmts.add(setUserDbo);
        }
        return stmts;
    }


    /**
     * Get index with words
     * 
     * @param needWithClause
     * @return
     */
    private String getIndexWithClause(boolean needWithClause)
    {
        return needWithClause ? TAB : NEWLINE + TAB + WITH + SPACE;
    }

    /**
     * Add comma for index caluse
     * 
     * @param needComma
     * @param statement
     */
    private void addComma(boolean needComma, StringBuffer statement)
    {
        if (needComma)
        {
            statement.append(COMMA + NEWLINE + TAB);
        }
    }

    public String createColumn(Column col, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer stmt = new StringBuffer();
        stmt.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                getName(col.getTable(), quoteIdentifiers, qualifyNames)).append(NEWLINE).append(SPACE).append(ADD)
                .append(SPACE).append(getColumnString(col, quoteIdentifiers));
        return stmt.toString();
    }

    public String dropForeignKey(ForeignKey foreignKey, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String dropStatement = dropTableConstraint(foreignKey, quoteIdentifiers, qualifyNames);
        String setUserStr = "";
        Schema creator = foreignKey.getBaseTable().getSchema();
        setUserStr = SybaseASESQLUtil.getSetNewUserStatement(creator);
        dropStatement = setUserStr+NEWLINE+TAB+dropStatement+NEWLINE+TAB+SybaseASESQLUtil.getSetUserDBOStatement(creator);
        Table table = foreignKey.getBaseTable();
        String tableName = getName(table, quoteIdentifiers, true);
        String constrName = foreignKey.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_FOREIGNKEY, new Object[]{tableName, constrName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }

    public String dropCheckConstraint(CheckConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames)
    {
        if(!(constraint instanceof SybaseASECheckConstraint))
            return EMPTY_STRING;
        
        String dropStatement = dropTableConstraint(constraint, quoteIdentifiers, qualifyNames);
        String setUserStr = "";

    	Schema creator = ((SybaseASECheckConstraint)constraint).getCreator();
    	if(creator!=null)
    	{
    		setUserStr = SybaseASESQLUtil.getSetNewUserStatement(creator);
    	}
    	
        dropStatement = setUserStr+NEWLINE+TAB+dropStatement+NEWLINE+TAB+SybaseASESQLUtil.getSetUserDBOStatement(creator);
        Table table = constraint.getBaseTable();
        String tableName = getName(table, quoteIdentifiers, true);
        String constrName = constraint.getName();
        String existingCheck = "";
        if(constraint instanceof SybaseASEColumnCheckConstraint)
        {
            existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_COLUMN_CHECK_CONSTRAINT, new Object[]{tableName, constrName});
        }
        else
        {
            existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_CHECK_CONSTRAINT, new Object[]{tableName, tableName, constrName});
        }
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }

    public String dropUniqueConstraint(UniqueConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String dropStatement = dropTableConstraint(constraint, quoteIdentifiers, qualifyNames);
        Table table = constraint.getBaseTable();
        String tableName = getName(table, quoteIdentifiers, true);
        String constrName = constraint.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_PK_UNIQUE, new Object[]{tableName, constrName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }

    public String getName(ENamedElement element, boolean quoteIdentifiers, boolean qualifyNames)
    {
        if(element instanceof SybaseASEDefault)
        {
            return getName((SybaseASEDefault)element, quoteIdentifiers, qualifyNames);
        }
        else if(element instanceof SybaseASERule)
        {
            return getName((SybaseASERule)element, quoteIdentifiers, qualifyNames);
        }
        else
        {
            return super.getName(element, quoteIdentifiers, qualifyNames);
        }
    }
    
    public String getName(Table table, boolean quoteIdentifiers, boolean qualifyNames)
    {
    	String tableName = table.getName();
        String schemaName = table.getSchema().getName();

        if(quoteIdentifiers) {
            tableName = SQLDevToolsUtil.quoteWhenNecessary(table.getName(),getDatabaseIdentifier(table));
            schemaName = SQLDevToolsUtil.quoteWhenNecessary(table.getSchema().getName(),getDatabaseIdentifier(table));
        }

        if(qualifyNames) {
            tableName = schemaName + DOT + tableName;
        }
    
        return tableName;
    }
    
    /**
     * Get SybaseASE Default name
     * 
     * @param aseDefault
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     * 
     * @author renj
     */
    protected String getName(SybaseASEDefault aseDefault, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String name = aseDefault.getName();
        String schemaName = aseDefault.getSchema().getName();

        if (quoteIdentifiers)
        {
            schemaName = SQLDevToolsUtil.quoteWhenNecessary(schemaName, getDatabaseIdentifier(aseDefault));
            name = SQLDevToolsUtil.quoteWhenNecessary(name, getDatabaseIdentifier(aseDefault));
        }
        if (qualifyNames)
        {
            name = schemaName + DOT + name;
        }

        return name;
    }

    /**
     * Get SybaseASE Rule name
     * 
     * @param aseRule
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     * 
     * @author renj
     */
    protected String getName(SybaseASERule aseRule, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String name = aseRule.getName();
        String schemaName = aseRule.getSchema().getName();

        if (quoteIdentifiers)
        {
            schemaName = SQLDevToolsUtil.quoteWhenNecessary(schemaName, getDatabaseIdentifier(aseRule));
            name = SQLDevToolsUtil.quoteWhenNecessary(name, getDatabaseIdentifier(aseRule));
        }
        if (qualifyNames)
        {
            name = schemaName + DOT + name;
        }

        return name;
    }

    // 
//    protected String getName(Table table, boolean quoteIdentifiers, boolean qualifyNames)
//    {
//        Catalog catalog = table.getSchema().getCatalog();
//        String catalogName = getName(catalog, quoteIdentifiers, qualifyNames);
//
//        if (qualifyNames)
//        {
//            return catalogName + DOT + super.getName(table, quoteIdentifiers, qualifyNames);
//        }
//
//        return super.getName(table, quoteIdentifiers, qualifyNames);
//    }

    
//    protected String getName(UserDefinedType type, boolean quoteIdentifiers, boolean qualifyNames)
//    {
//        Catalog catalog = type.getSchema().getCatalog();
//        String catalogName = getName(catalog, quoteIdentifiers, qualifyNames);
//
//        if (qualifyNames)
//        {
//            return catalogName + DOT + super.getName(type, quoteIdentifiers, qualifyNames);
//        }
//
//        return super.getName(type, quoteIdentifiers, qualifyNames);
//    }

    
    // TODO: not support role yet
    /**
     * <pre>
     *  sp_adduser loginame [, name_in_db [, grpname]]
     *  
     *  sp_addgroup grpname
     *  
     *  sp_changegroup grpname, username
     * </pre>
     */
    public String[] createAuthorizationId(AuthorizationIdentifier authId, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax)
    {
        String name = authId.getName();
        if (name.equals("dbo") || name.equals("public"))
        {
            // ignore system generated user/group
            return new String[0];
        }

        List results = new ArrayList();
        if (authId instanceof SybaseASEUser)
        {
            StringBuffer sb = new StringBuffer(128);
            SybaseASEUser aseUser = (SybaseASEUser) authId;
            // special handle while user name is 'guest', no login name needed.
            if (!aseUser.getName().equals("guest"))
            {
                sb.append(SP_ADDUSER).append(SPACE).append(SQLUtil.quote(aseUser.getLoginName(), SINGLE_QUOTE)).append(
                        COMMA).append(SPACE).append(SQLUtil.quote(authId.getName(), SINGLE_QUOTE));
            }
            else
            {
                sb.append(SP_ADDUSER).append(SPACE).append(SQLUtil.quote(authId.getName(), SINGLE_QUOTE));
            }

            results.add(sb.toString());
        }
        else if (authId instanceof Group)
        {
            StringBuffer sb = new StringBuffer(128);
            sb.append(SP_ADDGROUP).append(SPACE).append(SQLUtil.quote(authId.getName(), SINGLE_QUOTE));
            results.add(sb.toString());
            List users = ((Group) authId).getUser();
            for (int i = 0; i < users.size(); i++)
            {
                User user = (User) users.get(i);
                StringBuffer sb1 = new StringBuffer(64);
                sb1.append(SP_CHANGEGROUP).append(SPACE).append(SQLUtil.quote(authId.getName(), SINGLE_QUOTE)).append(
                        COMMA).append(SPACE).append(SQLUtil.quote(user.getName(), SINGLE_QUOTE));
                results.add(sb1.toString());
            }
        }

        return (String[]) results.toArray(new String[results.size()]);
    }

    
    /**
     * <pre>
     *  alter table [[database.][owner].table_name
     *  add column_name
     *   datatype
     *   [default {constant_expression | user | null}]
     *   {identity | null | not null}
     *   [off row | in row]
     * </pre>
     */
    public String[] createColumn(Column col, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
    	List stmts = new ArrayList();
        StringBuffer sb = new StringBuffer(128);
        String setUserStr = "";
        Schema creator = col.getTable().getSchema();
        if(creator!=null)
    	{
    		setUserStr = SybaseASESQLUtil.getSetNewUserStatement(creator);;
    	}
        sb.append(setUserStr).append(NEWLINE);
        sb.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                getName(col.getTable(), quoteIdentifiers, qualifyNames)).append(SPACE).append(ADD).append(SPACE)
                .append(getColumnString(col, quoteIdentifiers)).append(NEWLINE);
        stmts.add(sb.toString());
        String defaultBoundStmt = createBoundDefault(col,quoteIdentifiers,qualifyNames,fullSyntax);
        if(defaultBoundStmt!=null)
        {
        	stmts.add(defaultBoundStmt);
        }
        String ruleBoundStmt = creatBoundRule((SybaseASEColumn)col,quoteIdentifiers,qualifyNames,fullSyntax);
        if(ruleBoundStmt!=null)
        {
        	stmts.add(ruleBoundStmt);
        }
        String[] result = new String[stmts.size()];
        return (String[]) stmts.toArray(result);
    }
    
   private String creatBoundRule(SybaseASEColumn col, boolean quoteIdentifiers,
			boolean qualifyNames, boolean fullSyntax) {
	   if(((SybaseASEColumn)col).getBoundRule()!=null)
	   {
		   StringBuffer stmt = new StringBuffer();
           String setUserStr = "";
           Schema creator = col.getTable().getSchema();
           if (creator != null) 
           {
				setUserStr = SybaseASESQLUtil.getSetNewUserStatement(creator);
		   }
           stmt.append(setUserStr).append(NEWLINE);
           stmt.append(EXEC).append(SPACE).append(SP_BINDRULE).append(SPACE).append(
                   SQLUtil.quote(getName(col.getBoundRule(), false, true), SINGLE_QUOTE)).append(COMMA).append(
                           SQLUtil.quote(col.getTable().getName()+DOT+col.getName(),SINGLE_QUOTE));
           return stmt.toString();
	   }
		return null;
	}

 public String createBoundDefault(Column col, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
    	if(((SybaseASEColumn)col).getBoundDefault()!=null)
        {
            StringBuffer stmt = new StringBuffer();
            String setUserStr = "";
            Schema creator = col.getTable().getSchema();
            if(creator!=null)
        	{
        		setUserStr = SybaseASESQLUtil.getSetNewUserStatement(creator);;
        	}
            stmt.append(setUserStr).append(NEWLINE);
            stmt.append(EXEC).append(SPACE).append(SP_BINDEFAULT).append(SPACE).append(
                    SQLUtil.quote(getName(((SybaseASEColumn)col).getBoundDefault(), false, true), SINGLE_QUOTE)).append(COMMA)
                    .append(SQLUtil.quote(col.getTable().getName()+DOT+col.getName(),SINGLE_QUOTE)).append(NEWLINE);
            stmt.append(SybaseASESQLUtil.getSetUserDBOStatement(creator)).append(NEWLINE);
            return stmt.toString();
        }
    	return null;
    }

    
    /**
     * <pre>
     *  sp_dropuser name_in_db
     *  sp_dropgroup grpname
     * </pre>
     */
    public String dropAuthorizationId(AuthorizationIdentifier authId, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer sb = new StringBuffer(64);
        if (authId instanceof User)
        {
            sb.append(SP_DROPUSER).append(SPACE).append(SQLUtil.quote(authId.getName(), SINGLE_QUOTE));
        }
        else if (authId instanceof Group)
        {
            sb.append(SP_DROPGROUP).append(SPACE).append(SQLUtil.quote(authId.getName(), SINGLE_QUOTE));
        }
        String dropStatement = sb.toString();
        
        String authidName = authId.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_AUTHID, new Object[]{authidName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }

    /**
     * <pre>
     *  sp_addsegment segname, dbname, devname
     *  sp_extendsegment segname, dbname, devname
     * </pre>
     */
    public String[] createSegment(SybaseASESegment segment, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        List results = new ArrayList();
        String dbName = segment.getCatalog().getName();
        StringBuffer sb = new StringBuffer(128);
        List devices = segment.getDeviceNames();
        sb.append(SP_ADDSEGMENT).append(SPACE).append(SQLUtil.quote(segment.getName(), SINGLE_QUOTE)).append(COMMA)
                .append(SPACE).append(SQLUtil.quote(dbName, SINGLE_QUOTE)).append(COMMA).append(SPACE).append(
                        SQLUtil.quote((String) devices.get(0), SINGLE_QUOTE));

        for (int i = 1; i < devices.size(); i++)
        {
            StringBuffer sb1 = new StringBuffer(128);
            String devName = (String) devices.get(i);
            sb1.append(SP_EXTENDSEGMENT).append(SPACE).append(SQLUtil.quote(segment.getName(), SINGLE_QUOTE)).append(
                    COMMA).append(SPACE).append(SQLUtil.quote(dbName, SINGLE_QUOTE)).append(COMMA).append(SPACE)
                    .append(SQLUtil.quote(devName, SINGLE_QUOTE));
            results.add(sb1.toString());
        }
        return new String[]
        {
            sb.toString()
        };
    }

    public String dropSegment(SybaseASESegment segment, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String name = segment.getName();
        if(name.equals("default") || name.equals("system") || name.equals("logsegment"))
            return "";
        StringBuffer sb = new StringBuffer(128);
        sb.append(SP_DROPSEGMENT).append(SPACE).append(SQLUtil.quote(segment.getName(), SINGLE_QUOTE)).append(COMMA)
                .append(SPACE).append(SQLUtil.quote(segment.getCatalog().getName(), SINGLE_QUOTE));
        
        String dropStatement = sb.toString();
        
        String segmentName = segment.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_SEGMENT, new Object[]{segmentName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }
    
    /**
     * In ASE, if a UDT name contains double quote, we should quote it twice with double quote.
     * <p>
     * For example:
     * A UDT named "a b", then in DDL, the statements should like this: """a b"""
     */
    protected String getDataTypeString(TypedElement typedElement, Schema schema) {
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
                String udtName;
                if (referencedType.getSchema() != schema) {
                    udtName = getName(referencedType,false, false);
                } else {
                    udtName = referencedType.getName();
                }
                udtName = SQLUtil.quote(udtName, DOUBLE_QUOTE);
                return udtName;
            }
        }
        return null;
    }

    /**
     * 
     */
    public String getIndexKeyColumns(Index index, boolean quoteIdentifiers)
    {
        return ASEDdlUtils.getIndexMemberKeys(index.getMembers(), quoteIdentifiers);        
    }

    protected String getDropPreconditionPattern() 
    {
        StringBuffer sb = new StringBuffer();
        
        sb.append(IF).append(SPACE).append(EXISTS).append(SPACE).append(LEFT_PARENTHESIS).append(NEWLINE);
        sb.append(TAB).append("{0}").append(RIGHT_PARENTHESIS).
            append(NEWLINE).append(BEGIN).append(NEWLINE);
        sb.append(TAB).append("{1}").append(NEWLINE).append(END);
        return sb.toString();
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
        String setUserStr = "";
        Schema creator = constraint.getBaseTable().getSchema();
        if(constraint instanceof SybaseASECheckConstraint)
        {
            creator = ((SybaseASECheckConstraint)constraint).getCreator();
        }
        if(creator!=null)
    	{
    		setUserStr = SybaseASESQLUtil.getSetNewUserStatement(creator);
    	}
        
        String setUserDbo = SybaseASESQLUtil.getSetUserDBOStatement(creator);
        if(setUserStr.equals(""))
        {
            return new String[] { statement.toString() };
        }
        else
        {
            return new String[] { setUserStr, statement.toString(), setUserDbo};
        }
    }
    
//    protected String getCreateProcedureTempTablePreconditionPattern()
//    {
//        StringBuffer sb = new StringBuffer();
//        sb.append(IF).append(SPACE).append(NOT).append(SPACE).append(EXISTS).append(LEFT_PARENTHESIS).append(NEWLINE)
//            .append(TAB).append("SELECT 1 FROM tempdb..sysobjects WHERE name LIKE ''{0}%''").append(NEWLINE)
//            .append(RIGHT_PARENTHESIS).append(NEWLINE).append(BEGIN).append(NEWLINE);
//        sb.append(TAB).append(EXEC).append(LEFT_PARENTHESIS).append("{1}").append(RIGHT_PARENTHESIS).append(NEWLINE).append(END);
//        return sb.toString();
//    }
    
     
    

}
