/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.ddl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.sybase.asa.ISybaseASADdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.BaseTableASABaseLoader;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.AllowNullType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseFunction;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProcedure;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseRemoteProcedure;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TransactionOption;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAIndex;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATempTable;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.parser.QuickSQLParser;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.common.util.EList;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

public class SybaseASADdlBuilder extends SybaseDdlBuilder implements ISybaseASADdlConstants{
    /**
     * Used in event schedule formatting
     */
    public static final DateFormat          DATE_FORMAT         = DateFormat.getDateInstance(DateFormat.DEFAULT);
    public static final DateFormat          TIME_FORMAT         = new SimpleDateFormat("HH:mm:ss");

    protected static SybaseASADdlBuilder builder;
    
    protected SybaseASADdlBuilder()
    {
    }
    
    public static SybaseASADdlBuilder getInstance()
    {
        if(builder == null)
        {
            builder = new SybaseASADdlBuilder();
        }
        
        return builder;
    }
    
   
    public String[] createCatalogs(Catalog catalog, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        return new String[]
        {
            EMPTY_STRING
        };
    }

    public String dropIndex(Index index, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String dropStatement = DROP + SPACE + INDEX + SPACE + getName(index.getTable(), quoteIdentifiers, qualifyNames) + DOT
                + getName(index, quoteIdentifiers, qualifyNames);
        
        String ownerName = index.getSchema().getName();
        String indexName = index.getName();
        String tableName = index.getTable().getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_INDEX, new Object[]
        {
            ownerName, tableName, indexName
        });
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }
    
    public String dropDatabase(Database db, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer sb = new StringBuffer(128);
        SybaseASABaseDatabase database = (SybaseASABaseDatabase)db;
        sb.append(DROP).append(SPACE).append(DATABASE).append(SPACE)
                .append(SQLUtil.quote(database.getDatabaseFileName(), SINGLE_QUOTE));
        return sb.toString();
    }

    public String dropTrigger(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames) {
        //do not call getName(Trigger) because during drop, we have to include the table name as well
        String name = trigger.getName();
    
        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
        }
        if(qualifyNames) {
            String schemaName = trigger.getSchema().getName();
            String tableName = trigger.getSubjectTable().getName();
            if(quoteIdentifiers) {
                tableName = this.getDoubleQuotedString(tableName);
            }
            name = schemaName + DOT + tableName + DOT + name;
        }

        String dropStatement = DROP + SPACE + TRIGGER + SPACE + name;
        
        String ownerName = trigger.getSchema().getName();
        String triggerName = trigger.getName();
        String tableName = trigger.getSubjectTable().getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_TRIGGER, new Object[]
        {
            ownerName, tableName, triggerName
        });
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
        
    }


    /**
     * @author huiwan 
     */
    public String[] createDatabase(Database database, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        SybaseASADatabase asaDatabase = (SybaseASADatabase) database;
        StringBuffer statement = new StringBuffer();
        statement.append(createSybaseASABaseDatabase((SybaseASABaseDatabase) database, quoteIdentifiers, qualifyNames));

        // ASE compatiable, default is COMPATIBLE
        if (asaDatabase.isASECompatible() && fullSyntax)
        {
            statement.append(NEWLINE + TAB + ASE + SPACE + COMPATIBLE);
        }
        else
        {
            statement.append(NEWLINE + TAB + ASE);
        }
        // Java support
        if (!asaDatabase.isBaseOnASA10() && asaDatabase.getJavaSupport() != null)
        {
            statement.append(NEWLINE + TAB + JAVA + SPACE);
            statement.append(asaDatabase.getJavaSupport().getLiteral());
        }
        // Checksum, default value is ON
        if (asaDatabase.isCheckSumOn() && fullSyntax)
        {
            statement.append(NEWLINE + TAB + CHECKSUM + SPACE + ON);
        }
        else
        {
            statement.append(NEWLINE + TAB + CHECKSUM + SPACE + OFF);
        }
        return new String[]
        {
            statement.toString()
        };
    }
    
    protected String createSybaseASABaseDatabase(SybaseASABaseDatabase asaDatabase, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer statement = new StringBuffer();
        statement.append(CREATE + SPACE + DATABASE + SPACE);
        statement.append(getSingleQuotedString(asaDatabase.getDatabaseFileName()));
        if (asaDatabase.getMirrorFileName() != null)
        {
            statement.append(NEWLINE + TAB + MIRROR + SPACE);
            statement.append(getSingleQuotedString(asaDatabase.getMirrorFileName()));
        }
        // Case sensitive
        if (asaDatabase.isCaseSensitive())
        {
            statement.append(NEWLINE + TAB + CASE + SPACE + RESPECT);
        }
        else
        {
            statement.append(NEWLINE + TAB + CASE + SPACE + IGNORE);
        }
        // Page size
        if (asaDatabase.getPageSize() > -1)
        {
            statement.append(NEWLINE + TAB + PAGE + SPACE + SIZE + SPACE);
            statement.append(asaDatabase.getPageSize());
        }
        // Collation
        if (asaDatabase.getCollation() != null)
        {
            statement.append(NEWLINE + TAB + COLLATION + SPACE);
            statement.append(getSingleQuotedString(asaDatabase.getCollation()));
        }
        // Encryption info
        if (asaDatabase.getEncryptionInfo() != null && asaDatabase.getEncryptionInfo().isEncryptedTable())
        {
            statement.append(NEWLINE + TAB + ENCRYPTED + SPACE + ON);
            if (asaDatabase.getEncryptionInfo().getEncryptionKey() != null)
            {
                statement.append(SPACE + KEY + SPACE);
                statement.append(getSingleQuotedString(asaDatabase.getEncryptionInfo().getEncryptionKey()));
            }
        }
        else
        {
            statement.append(NEWLINE + TAB + ENCRYPTED + SPACE + OFF);
        }
        // Blank Padding
        if (asaDatabase.isBlankPaddingOn())
        {
            statement.append(NEWLINE + TAB + BLANK + SPACE + PADDING + SPACE + ON);
        }
        else
        {
            statement.append(NEWLINE + TAB + BLANK + SPACE + PADDING + SPACE + OFF);
        }
//      Jconnect
        if (asaDatabase.isJConnectOn())
        {
            statement.append(NEWLINE + TAB + JCONNECT + SPACE + ON);
        }
        else
        {
            statement.append(NEWLINE + TAB + JCONNECT + SPACE + OFF);
        }        
        // Password sensitive
        if(asaDatabase.getPasswordCaseSensitive() == null)
        {
            //do nothing
        }
        else if (asaDatabase.getPasswordCaseSensitive().booleanValue())
        {
            statement.append(NEWLINE + TAB + PASSWORD + SPACE + CASE + SPACE + RESPECT);
        }
        else
        {
            statement.append(NEWLINE + TAB + PASSWORD + SPACE + CASE + SPACE + IGNORE);
        }
        return statement.toString();
    }
    
    /**
     * <pre>
     *      CREATE EVENT event-name
     *      [ TYPE event-type
     *          [ WHERE trigger-condition [ AND trigger-condition ] ... ]
     *      | SCHEDULE schedule-spec, ... ]
     *      [ ENABLE | DISABLE ]
     *      [ AT { CONSOLIDATED | REMOTE | ALL } ]
     *      [ HANDLER
     *      BEGIN
     *      ...
     *      END ]
     * 
     *      event-type :
     *      BackupEnd   | &quot;Connect&quot;
     *      | ConnectFailed  | DatabaseStart
     *      | DBDiskSpace | &quot;Disconnect&quot;
     *      | GlobalAutoincrement | GrowDB
     *      | GrowLog     | GrowTemp
     *      | LogDiskSpace   | &quot;RAISERROR&quot;
     *      | ServerIdle     | TempDiskSpace
     * 
     *      trigger-condition :
     *      event_condition( condition-name ) { = | &lt; | &gt; | != | &lt;= | &gt;= } value
     * 
     *      schedule-spec :
     *      [ schedule-name ]
     *      { START TIME start-time | BETWEEN start-time AND end-time }
     *      [ EVERY period { HOURS | MINUTES | SECONDS } ]
     *      [ ON { ( day-of-week, ... ) | ( day-of-month, ... ) } ]
     *      [ START DATE start-date ]
     * 
     *      event-name | schedule-name : identifier
     * 
     *      day-of-week :        string
     * 
     *      day-of-month | value | period :   integer
     * 
     *      start-time | end-time :      time
     * 
     *      start-date :         date
     * 
     * </pre>
     * TODO check the format of the ASA debugger
     */
    public String[] createEvent(Event event, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax) {
        return getEventSource(event, quoteIdentifiers, qualifyNames, fullSyntax, false);
           
    }

    /**
     * 
     * @param event
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @param debugFormat Whether the generated ddl should be used for debugging
     * @return
     */
    public String[] getEventSource(Event event, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, boolean debugFormat) {
        StringBuffer sb = new StringBuffer();
        String comment = createComment(event, quoteIdentifiers, qualifyNames);

        String body = event.getAction();
        if (QuickSQLParser.getInstance().match(body, QuickSQLParser.CREATE_EVENT_HEADER_PATTERN))
        {
            //body already contains the header, which happens when the Procedure is loaded from database
            if(comment != null && !comment.trim().equals(""))
            {
                //remove header comment as it's included in comment statement
                int start = body.toLowerCase().indexOf("create");
                if (start >=0 )
                {
                    body = body.substring(start);
                }
            
                return new String[]{body, comment};
            }
            else
            {
                return new String[]{body};
            }
        }
        
        String eventHeader = getEventHeader(event, quoteIdentifiers, qualifyNames, fullSyntax, debugFormat);
        sb.append(eventHeader);
        // If eventBody is empty, add the block "BEGIN \r\nEND" to avoid syntax
        // error.
        if (body == null || body.equals(""))
        {
            body = "BEGIN" + NEWLINE + "END";
        }
        sb.append(body);
        
        //create description (not the comments inside source)
        if(!debugFormat && (comment != null && !comment.trim().equals("")))
        {
            return new String[]{sb.toString(), comment};
        }

        return new String[]{sb.toString()};
    }

    public String getEventHeader(Event event, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax,
            boolean debugFormat)
    {
        SybaseASABaseEvent evt = (SybaseASABaseEvent)event;

        StringBuffer sb = new StringBuffer();
        if (debugFormat && (event.getDescription() != null && event.getDescription().trim().length()>0))
        {
            sb.append("/*").append(NEWLINE).append(event.getDescription()).append(NEWLINE).append("*/" ).append(NEWLINE);
        }
        //this happens when user creates the model from scratch
        sb.append(CREATE).append(SPACE).append(EVENT).append(SPACE).append(getName(event, quoteIdentifiers, qualifyNames)).append(NEWLINE);
        
        sb.append(getEventScheduleOrType(evt));
        
        sb.append(getEventStatus(evt));
        
        sb.append(getEventLocation(fullSyntax, evt));
        
        sb.append(HANDLER).append(NEWLINE);
        
        return sb.toString();
    }
    
    public String getEventStatus(SybaseASABaseEvent evt)
    {
        StringBuffer sb = new StringBuffer();
        if (evt.isEnabled())
        {
            sb.append(ENABLE).append(NEWLINE);
        }
        else
        {
            sb.append(DISABLE).append(NEWLINE);
        }
        return sb.toString();
    }

    public String getEventLocation(boolean fullSyntax, SybaseASABaseEvent evt)
    {
        StringBuffer sb = new StringBuffer();
        if (fullSyntax || evt.eIsSet(evt.eClass().getEStructuralFeature(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT__LOCATION)))
        {
            sb.append(AT).append(SPACE).append(evt.getLocation().getLiteral()).append(NEWLINE);
        }
        return sb.toString();
    }

    public String getEventScheduleOrType(SybaseASABaseEvent evt)
    {
        StringBuffer sb = new StringBuffer();
        EventType type = evt.getEventType();
        String schedule = getEventSchedules(evt);
        if (schedule != null && !schedule.trim().equals(""))
        {
            sb.append(schedule);
        }
        else if (type != null && !type.equals(EventType.NOEVENTTYPE_LITERAL))
        {
            sb.append(TYPE).append(SPACE).append(type.getLiteral()).append(NEWLINE);
            String condition = evt.getCondition();
            EList details = evt.getConditionDetails();
            if (condition != null && !condition.trim().equals(""))
            {
                sb.append(WHERE).append(SPACE).append(condition).append(NEWLINE);
            }
            else if (details != null && !details.isEmpty())
            {
                sb.append(getEventConditions(details));
            }
        }
        return sb.toString();
    }
    
    
    public String getEventConditions(List details)
    {
        StringBuffer sb = new StringBuffer();
        for (Iterator it = details.iterator(); it.hasNext();)
        {
            EventCondition cond = (EventCondition) it.next();
            sb.append(EVENT_CONDITION).append("(").append("'").append(cond.getName()).append("'").append(")").append(SPACE).append(cond.getOperator()).append(SPACE).append(cond.getValue()).append(SPACE);
            if (it.hasNext())
            {
                sb.append(AND).append(SPACE);
            }
        }
        return sb.toString();
    }

    /**
     * <p>
     * TSQL:
     * </p>
     * <pre>
     *      CREATE PROCEDURE [owner.]procedure_name
     *      [ [ ( ] @parameter_name data-type [ = default ] [ OUTPUT ], ... [ ) ] ]
     *      [ WITH RECOMPILE ] AS statement-list
     * </pre>
     * 
     * <p>
     * Watcom1:
     * </p>
     * <pre>
     *       CREATE PROCEDURE [ owner.]procedure-name ( [ parameter, ... ] )
     *       {   [ RESULT ( result-column, ... ) ]
     *       [ ON EXCEPTION RESUME ]
     *       compound-statement
     *       | AT location-string
     *       | EXTERNAL NAME library-call
     *       | [ DYNAMIC RESULT SETS integer-expression ]
     *       [ EXTERNAL NAME java-call LANGUAGE JAVA ]
     *       }
     * </pre>
     * <p>
     * Watcom2:
     * </p>
     * <pre>
     *      CREATE PROCEDURE [ owner.]procedure-name ( [ parameter, ... ] )
     *      compound-statement
     * </pre>
     */
    public String[] createProcedure(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax) {
        //create description (not the comments inside source)
        String comment = createComment(procedure, quoteIdentifiers, qualifyNames);
        
        SybaseASABaseProcedure proc = (SybaseASABaseProcedure)procedure;
        Source source = procedure.getSource();
        String body = "";
        if (source != null && source.getBody() != null) {
            body = source.getBody();
        }

        if (QuickSQLParser.getInstance().match(body, QuickSQLParser.CREATE_PROC_HEADER_PATTERN))
        {
            //body already contains the header, which happens when the Procedure is loaded from database
            if(comment != null && !comment.trim().equals(""))
            {
                return new String[]{body, comment};
            }
            else
            {
                return new String[]{body};
            }
        }

        
        //this happens when user creates the model from scratch
        StringBuffer sb = new StringBuffer();
        sb.append(CREATE).append(SPACE).append(PROCEDURE).append(SPACE).append(getName(procedure, quoteIdentifiers, qualifyNames)).append(NEWLINE);
        
        int syntaxType = getSyntaxType(procedure);
        String param = "";
        if (syntaxType == SYNTAX_TYPE_TSQL)
        {
            param = getTSQLParameters(procedure);
        }
        else
        {
            param = getWatcomParameters(procedure, fullSyntax);
        }
        
        if (param != null && param.length() > 0)
        {
            sb.append(LEFT_PARENTHESIS).append(param).append(RIGHT_PARENTHESIS).append(NEWLINE);
        }
        
        if (syntaxType == SYNTAX_TYPE_TSQL)
        {
            sb.append(AS).append(NEWLINE).append(body);
        }
        else
        {
            sb.append(TAB).append(getRoutineResults(procedure));
            sb.append(TAB).append(getCharacteristics(proc));
            
            if (proc instanceof SybaseASABaseRemoteProcedure)
            {
                //Remote
                sb.append(TAB).append(AT).append(SPACE).append(((SybaseASABaseRemoteProcedure)proc).getLocation()).append(NEWLINE);
            }
            else if (proc.getExternalName() != null)
            {
                sb.append(getExternalNameOption(proc, quoteIdentifiers, qualifyNames));
            }
            else if (body.length() > 0)
            {
                //watcom SQL
                sb.append(body);
            }

        }
        
        if(comment != null && !comment.trim().equals(""))
        {
            return new String[]{sb.toString(), comment};
        }

        return new String[]{sb.toString()};
    }

    public String getRoutineResults(Procedure procedure)
    {
        StringBuffer sb = new StringBuffer();
        EList resultSet = procedure.getResultSet();
        boolean hasColumn = false;
        if(resultSet != null && !resultSet.isEmpty()) {
            sb.append(RESULT).append(SPACE).append(LEFT_PARENTHESIS);
            for (Iterator iter = resultSet.iterator(); iter.hasNext();)
            {
                RoutineResultTable resultTable = (RoutineResultTable)iter.next();
                Iterator it = resultTable.getColumns().iterator();
                while(it.hasNext()) {
                    Column c = (Column) it.next();
                    if (c.getName() != null && c.getDataType() != null)
                    {
                        hasColumn = true;
                    }
                    else
                    {
                        continue;
                    }
                    sb.append(c.getName()).append(SPACE).append(getDataTypeString(c,procedure.getSchema()));
                    if(it.hasNext()) {
                        sb.append(COMMA).append(SPACE);
                    }
                }
            }
            if (sb.charAt(sb.length()-2) == ',')
            {
                sb.delete(sb.length()-2, sb.length());
            }
            sb.append(RIGHT_PARENTHESIS).append(NEWLINE);
        }
        if (hasColumn)
        {
            return sb.toString();
        }
        else
        {
            return "";
        }
    }

    /**
     * <pre>
     *      CREATE FUNCTION [ owner.]function-name ( [ parameter, ... ] )
     *      RETURNS data-type routine-characteristics
     *      { compound-statement
     *      | AS tsql-compound-statement
     *      | external-name }
     * 
     *      parameter :
     *      [ IN ] parameter-name data-type
     * 
     *      routine-characteristics
     *      ON EXCEPTION RESUME | [ NOT ] DETERMINISTIC
     * 
     *      tsql-compound-statement:
     *      sql-statement
     *      sql-statement
     *      ...
     * 
     *      external-name:
     *      EXTERNAL NAME library-call
     *      | EXTERNAL NAME java-call LANGUAGE JAVA
     * 
     *      library-call :
     *      '[operating-system:]function-name@library; ...'
     * 
     *      operating-system :
     *      Windows95 | WindowsNT | NetWare | UNIX
     * 
     *      java-call :
     *      '[package-name.]class-name.method-name method-signature'
     * 
     * 
     *      method-signature :
     *      ([field-descriptor, ... ]) return-descriptor
     * 
     *      field-descriptor | return-descriptor :
     *      Z | B | S | I | J | F | D | C | V | [descriptor | Lclass-name;
     * </pre>
     */
    public String[] createUserDefinedFunction(UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax) {
      //create description (not the comments inside source)
        String comment = createComment(function, quoteIdentifiers, qualifyNames);
        
        Source source = function.getSource();
        String body = "";
        if (source != null && source.getBody() != null) {
            body = source.getBody();
        }

        if (QuickSQLParser.getInstance().match(body, QuickSQLParser.CREATE_FUNC_HEADER_PATTERN))
        {
            //body already contains the header, which happens when the function is loaded from database
            if(comment != null && !comment.trim().equals(""))
            {
                return new String[]{body, comment};
            }
            else
            {
                return new String[]{body};
            }
        }

        //this happens when user creates the model from scratch
        StringBuffer sb = new StringBuffer();
        sb.append(CREATE).append(SPACE).append(FUNCTION).append(SPACE).append(getName(function, quoteIdentifiers, qualifyNames)).append(NEWLINE);
        
        String param = getFuncParameters(function, fullSyntax);
        if (param != null && param.length() > 0)
        {
            sb.append(LEFT_PARENTHESIS).append(param).append(RIGHT_PARENTHESIS).append(NEWLINE);
        }
        sb.append(getFunctionReturnsClause(function));
        
        sb.append(TAB).append(getCharacteristics(function));
        if (function.getExternalName() != null)
        {
            // external dll or java
            sb.append(getExternalNameOption(function, quoteIdentifiers, qualifyNames));
        }
        else if (body.length() > 0)
        {
            //SQL
            sb.append(body);
        }
        
        if(comment != null && !comment.trim().equals(""))
        {
            return new String[]{sb.toString(), comment};
        }
        
        return new String[]{sb.toString()};
    }

    public String getCharacteristics(Routine routine)
    {
        StringBuffer sb = new StringBuffer();
        if (routine instanceof SybaseASABaseProcedure && ((SybaseASABaseProcedure)routine).isOnExceptionResume())
        {
            sb.append(ONEXCEPTIONRESUME).append(NEWLINE);
        }
        else if (routine instanceof SybaseASABaseFunction )
        {
            if (((SybaseASABaseFunction)routine).isOnExceptionResume())
            {
                sb.append(ONEXCEPTIONRESUME).append(SPACE);
            }
            sb.append(getDeterministic((SybaseASABaseFunction)routine));
        }
        return sb.toString();
    }
    
    /**
     * <pre>
     *      CREATE TRIGGER trigger-name trigger-time { trigger-event-list | UPDATE OF column-list }
     *      [ ORDER integer ] ON table-name
     *      [ REFERENCING [ OLD AS old-name ]
     *                      [ NEW AS new-name ] 
     *                      [ REMOTE AS remote-name ] ]
     *      [ FOR EACH { ROW | STATEMENT } ]
     *      [ WHEN ( search-condition ) ]
     *      compound-statement
     * 
     *      trigger-time : BEFORE | AFTER | RESOLVE
     * 
     *      trigger-event-list : trigger-event [ , trigger-event ]
     * 
     *      trigger-event :
     *      DELETE | INSERT | UPDATE
     * </pre>
     * TODO TSQL
     */
    public String[] createTrigger(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax) {
        if (! (trigger instanceof SybaseASABaseTrigger))
        {
            return new String[]{super.createTrigger(trigger, quoteIdentifiers, qualifyNames)};
        }
        
        //create description (not the comments inside source)
        String comment = createComment(trigger, quoteIdentifiers, qualifyNames);
        
        SybaseASABaseTrigger trig = ((SybaseASABaseTrigger)trigger);
        String bodyString = getTriggerBody(trigger);
        
        if (QuickSQLParser.getInstance().match(bodyString, QuickSQLParser.CREATE_TRIGGER_HEADER_PATTERN))
        {
            //body already contains the header, which happens when the trigger source is loaded from database
            if(comment != null && !comment.trim().equals(""))
            {
                return new String[]{bodyString, comment};
            }
            return new String[]{bodyString};
        }
        
        if (trig.getSybaseASABaseActionTime().getValue() == SybaseASABaseActionTime.ASE)
        {
            String tsqltrigger = createTSQLTrigger(trigger, quoteIdentifiers, qualifyNames);
            if(comment != null && !comment.trim().equals(""))
            {
                return new String[]{tsqltrigger, comment};
            }
            return new String[]{tsqltrigger};
        }
        
        StringBuffer sb = new StringBuffer();
        sb.append(CREATE).append(SPACE).append(TRIGGER).append(SPACE).append(getName(trigger, quoteIdentifiers, qualifyNames)).append(NEWLINE);
        sb.append(getFullTriggerEvents(trig));
        
        sb.append(getTriggerOrder(fullSyntax, trig));
        
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
        }
        sb.append(getTriggerReference(trig));
        sb.append(getTriggerGranularity(trig));

        SearchCondition when = trig.getWhen();
        if (when != null && when.getSQL() != null)
        {
            sb.append(WHEN).append(SPACE).append(when.getSQL()).append(NEWLINE);
        }
        sb.append(bodyString);
        
        
        if(comment != null && !comment.trim().equals(""))
        {
            return new String[]{sb.toString(), comment};
        }
        
        return new String[]{sb.toString()};
    }
    
    private boolean notEmpty(String text)
    {
        return text != null && !text.trim().equals("");
    }

    public String getTriggerReference(SybaseASABaseTrigger trig)
    {
        StringBuffer sb = new StringBuffer();
        if (notEmpty(trig.getOldName()) || notEmpty(trig.getNewName()) || notEmpty(trig.getRemoteName()))
        {
            sb.append(REFERENCING).append(SPACE);
        }
        if (notEmpty(trig.getOldName()))
        {
            sb.append(OLD).append(SPACE).append(AS).append(SPACE).append(trig.getOldName()).append(SPACE);
        }
        if (notEmpty(trig.getNewName()))
        {
            sb.append(NEW).append(SPACE).append(AS).append(SPACE).append(trig.getNewName()).append(SPACE);
        }
        if (notEmpty(trig.getRemoteName()))
        {
            sb.append(REMOTE).append(SPACE).append(AS).append(SPACE).append(trig.getRemoteName()).append(SPACE);
        }
        return sb.toString();
    }

    public String getTriggerGranularity(SybaseASABaseTrigger trig)
    {
        StringBuffer sb = new StringBuffer();
        switch (trig.getActionGranularity().getValue())
        {
            case ActionGranularityType.ROW:
                sb.append(FOR).append(SPACE).append(EACH).append(SPACE).append(ROW).append(NEWLINE);
                break;
            case ActionGranularityType.STATEMENT:
                sb.append(FOR).append(SPACE).append(EACH).append(SPACE).append(STATEMENT).append(NEWLINE);
                break;
        }
        return sb.toString();
    }
    
    public String getTriggerOrder(boolean fullSyntax, SybaseASABaseTrigger trig)
    {
        StringBuffer sb = new StringBuffer();
        if (fullSyntax || trig.eIsSet(trig.eClass().getEStructuralFeature(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__ORDER))) //default is 1
        {
            int order = trig.getOrder();
            sb.append(ORDER).append(SPACE).append(order).append(SPACE);
        }
        return sb.toString();
        
    }

    public String getFullTriggerEvents(SybaseASABaseTrigger trig)
    {
        StringBuffer sb = new StringBuffer();
        SybaseASABaseActionTime eventTime = trig.getSybaseASABaseActionTime();
        if(eventTime.equals(SybaseASABaseActionTime.ASE_LITERAL))
        {
            eventTime = SybaseASABaseActionTime.AFTER_LITERAL;
        }
        sb.append(eventTime.getLiteral()).append(SPACE);
        EList triggerColumn = trig.getTriggerColumn();
        if (trig.isUpdateColumnType())
        {
            if (triggerColumn != null && triggerColumn.size() > 0 )
            {
                sb.append(UPDATE).append(SPACE).append(OF).append(SPACE);
                for (Iterator iter = triggerColumn.iterator(); iter.hasNext();)
                {
                    Column column = (Column) iter.next();
                    sb.append(column.getName());
                    if (iter.hasNext())
                    {
                        sb.append(COMMA);
                    }
                }
                sb.append(SPACE);
            }
            else
            {
                sb.append(UPDATE).append(SPACE);//this is the workaround for the model error
            }
        }
        else
        {
            sb.append(getTriggerEvents(trig));
        }
        return sb.toString();
    }

    /**
     * Returns the SQL Statement for creating domain.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *  CREATE { DOMAIN | DATATYPE } [ AS ] domain-name data-type
     *           [ [ NOT ] NULL ]
     *           [ DEFAULT default-value ]
     *           [ CHECK ( condition ) ]
     *   
     * </pre>
     * 
     * @param asaUDT
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     * 
     * @author renj
     */
    public String[] createUserDefinedType(UserDefinedType udt, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        SybaseASABaseUserDefinedType asaUDT = (SybaseASABaseUserDefinedType)udt;
        StringBuffer sb = new StringBuffer(128);
        if (quoteIdentifiers) {
            sb.append(CREATE).append(SPACE).append(DOMAIN).append(SPACE).append(SQLUtil.quote(asaUDT.getName(), DOUBLE_QUOTE)).append(SPACE);
        } else {
            sb.append(CREATE).append(SPACE).append(DOMAIN).append(SPACE).append(asaUDT.getName()).append(SPACE);
        }
        
        // datatype
        DatabaseDefinitionRegistry dbRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
        DatabaseDefinition definition = dbRegistry.getDefinition((Database) ContainmentServiceImpl.INSTANCE
                .getRootElement(asaUDT));
        PredefinedDataType pdt = asaUDT.getPredefinedRepresentation();
        sb.append(definition.getPredefinedDataTypeFormattedName(pdt));

        // allow null type
        AllowNullType allowNullType = asaUDT.getNullable();
        if (allowNullType.getValue() == AllowNullType.NULLABLE) {
            sb.append(NEWLINE).append(NULL).append(SPACE);
        } else if (allowNullType.getValue() == AllowNullType.NOT_NULLABLE) {
            sb.append(NEWLINE).append(NOT).append(SPACE).append(NULL).append(SPACE);
        }

        //default value
        String defaultValue = asaUDT.getDefaultValue();
        if (defaultValue != null)
        {
            sb.append(NEWLINE).append(DEFAULT).append(SPACE).append(defaultValue);
        }

        // check constraints
        EList constraints = asaUDT.getConstraint();
        if (constraints != null && constraints.size() > 0)
        {
            CheckConstraint checkConstraint = (CheckConstraint) constraints.get(0);
            if (checkConstraint.getSearchCondition().getSQL() != null
                    && !checkConstraint.getSearchCondition().getSQL().trim().equals(""))
            {
                sb.append(NEWLINE).append(checkConstraint.getSearchCondition().getSQL());
            }
        }

        return new String[]{sb.toString()};
    }
    
    /**
     * @author dfyang
     */
    public String[] grantPrivilege(Privilege privilege, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        String[] stat = super.grantPrivilege(privilege, quoteIdentifiers, qualifyNames, fullSyntax);
        if(stat == null || stat.length == 0)
        {
            return new String[]{""};
        }
        StringBuffer sb = new StringBuffer(stat[0]);
        if (privilege.isGrantable())
        {
            if (privilege.getAction() == null)
            {
                sb.append(SPACE).append(WITH_GRANT_OPTION);
            }
            else if (!privilege.getAction().equals(EXEC_PRIVILEGE))
            {
                sb.append(SPACE).append(WITH_GRANT_OPTION);
            }
        }

        return new String[] {sb.toString()};
    }
    
    /**
     * <pre>
     *   CREATE VIEW
     *   [ owner.]view-name [ ( column-name, . . . ) ]
     *   AS select-statement
     *   [ WITH CHECK OPTION ]
     * </pre>
     * 
     * @author hfeng
     */
    public String[] createView(ViewTable view, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
    	if (!(view instanceof SybaseASABaseViewTable))
        {
            return null;
        }
    	
        List stats = new ArrayList();
        
        StringBuffer viewDefinition = new StringBuffer("");
        viewDefinition.append(CREATE).append(SPACE).append(VIEW).append(SPACE)
                .append(getName(view, quoteIdentifiers, qualifyNames)).append(SPACE);
               
        String columns = getViewColumnList(view,quoteIdentifiers);
        if(columns != null) 
        {
            viewDefinition.append(LEFT_PARENTHESIS).append(columns).append(RIGHT_PARENTHESIS).append(NEWLINE);
        }
        viewDefinition.append(AS).append(NEWLINE);
        viewDefinition.append(view.getQueryExpression().getSQL());
        
        if(((SybaseASABaseViewTable) view).isWithCheckOption())
        {
            viewDefinition.append(NEWLINE).append(CHECKOPTION); 
        }
        stats.add(viewDefinition.toString());
        
        //add comment for view
        String comment = createComment(view, quoteIdentifiers, qualifyNames);
        if(comment != null && !comment.trim().equals(""))
        {
            stats.add(comment);
        }
        //add comment for columns
        Iterator i = (view.getColumns().iterator());
        while(i.hasNext()) {
            Column c = (Column) i.next();
            comment = createComment(c, quoteIdentifiers, qualifyNames);
            if(comment != null && !comment.trim().equals(""))
            {
                stats.add(comment);
            }
        }
        
        return (String[])stats.toArray(new String[stats.size()]);
    }
    
    /**
     * <pre>
     *   COMMENT ON
     *   {
     *      COLUMN [ owner.]table-name.column-name
     *      | EVENT event-name
     *      | FOREIGN KEY [ owner.]table-name.role-name
     *      | INDEX [ [ owner.] table.]index-name
     *      | JAVA CLASS java-class-name
     *      | JAVA JAR java-jar-name
     *      | LOGIN integrated_login_id
     *      | PROCEDURE [ owner.]procedure-name
     *      | SERVICE web-service-name
     *      | TABLE [ owner.]table-name
     *      | TRIGGER [ [ owner.]tablename.]trigger-name
     *      | USER userid
     *      | VIEW [ owner.]view-name
     *   }
     *   IS comment
     * </pre>
     * 
     * For ASA, when creating a view, there may be comments on the view or 
     * its columns, so COMMENT ON statement is needed for the view creating.
     * 
     * @author hfeng
     */
    public String createComment(SQLObject obj, boolean quoteIdentifiers, boolean qualifyNames)
    {
        return createComment(obj, quoteIdentifiers, qualifyNames, false);
    }
    
    /**
     * 
     * @param obj
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param removeComment whether to create sql statement to remove the comment if it's null. 
     * @return
     */
    public String createComment(SQLObject obj, boolean quoteIdentifiers, boolean qualifyNames, boolean removeComment)
    {
        String description = obj.getDescription();
        if(description == null || description.trim().length()==0)
        {
            if (removeComment)
            {
                description = "NULL";
            }
            else
            {
                return null;
            }
        }
        else
        {
            description = SQLUtil.quote(description, SINGLE_QUOTE);
        }

        String objectType = null;
        String objectName = getName(obj,quoteIdentifiers, qualifyNames);
        if(obj instanceof Event)
        {
            objectType = EVENT;
        }
        else if(obj instanceof ForeignKey)
        {
            objectType = FOREIGN_KEY;
        }
        else if(obj instanceof Index)
        {
            objectType = INDEX;
        }
        else if(obj instanceof ViewTable)
        {
            objectType = VIEW;
        }
        else if(obj instanceof Column)
        {
            objectType = COLUMN;
        }
        else if(obj instanceof Routine)
        {
            objectType = PROCEDURE;
        }
        else if(obj instanceof SybaseASAWebService)
        {
            objectType = SERVICE;
        }
        else if(obj instanceof Table)
        {
            objectType = TABLE;
        }
        else if(obj instanceof Trigger)
        {
            objectType = TRIGGER;
        }
        else if(obj instanceof Schema)
        {
            objectType = USER;
        }
        
        if (objectType != null)
        {
            StringBuffer comment = new StringBuffer("");
            comment.append(COMMENT).append(SPACE).append(ON).append(SPACE).append(objectType).append(SPACE);
            if (obj instanceof Index || obj instanceof Column || obj instanceof ForeignKey || obj instanceof Trigger)
            {
                objectName = getTableSubObjectCommentName(obj, quoteIdentifiers);
            }
            comment.append(objectName).append(SPACE).append(IS).append(SPACE).append(description);
            
            return comment.toString();
        }
        return null;
    }
    

    /**
     * Returns the embeded comment for Event
     * @param obj
     * @return
     */
    public String getEventComment(Event obj)
    {
        StringBuffer sb = new StringBuffer();
        String desc = obj.getDescription();
        if (desc != null)
        {
            sb.append("/*").append(NEWLINE).append(desc).append(NEWLINE).append("*/").append(NEWLINE);
        }
        return sb.toString();
    }

    private String getTableSubObjectCommentName(SQLObject obj, boolean quoteIdentifiers)
    {
        Table table = (Table)ContainmentServiceImpl.INSTANCE.getContainer(obj);
        String indexName = obj.getName();
        String schemaName = table.getSchema().getName();
        String tableName = table.getName();

        if (quoteIdentifiers)
        {
            indexName = this.getDoubleQuotedString(indexName);
            schemaName = this.getDoubleQuotedString(schemaName);
            tableName = this.getDoubleQuotedString(tableName);
    }
    
        return schemaName + DOT + tableName + DOT + indexName;
    }

    
    protected String[] createPersistentTable(PersistentTable table, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        if(table instanceof SybaseASABaseTable &&!(table instanceof SybaseASABaseProxyTable))
        {
            return createBaseTable((SybaseASABaseTable)table, quoteIdentifiers, qualifyNames, fullSyntax);
        }
        else if(table instanceof SybaseASABaseProxyTable)
        {
            return createProxyTable((SybaseASABaseProxyTable)table, quoteIdentifiers, qualifyNames, fullSyntax);
        }
        else
        {
            throw new UnsupportedOperationException();
        }
    }
    
    /**
     * get ASA table syntax.
     * @author lihuang
     */
    protected String[] createBaseTable(SybaseASABaseTable table, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax) 
    {
        SybaseASABaseTable baseTable = (SybaseASABaseTable) table;
        
        StringBuffer statement = createTableBaseParts(quoteIdentifiers,
				baseTable);
        
        //DBSPACE
        if (getDBSpace(baseTable) != null)
        {
            statement.append(getDBSpace(baseTable));
        }
        
        List results = new ArrayList();
        results.add(statement.toString());
        //remark (table + columns)
        String tableComment = createComment(table, quoteIdentifiers, qualifyNames);
        if(tableComment != null && !tableComment.equals(""))
        {
            results.add(tableComment);
        }
        for(int i = 0; i<table.getColumns().size(); i++)
        {
            Column column = (Column)table.getColumns().get(i);
            String colComment = createComment(column, quoteIdentifiers, qualifyNames);
            if(colComment != null && !colComment.equals(""))
            {
                results.add(colComment);
            }
        }
        
        return (String[])results.toArray(new String[results.size()]);
    }

    /**
     * Generate DDL for table name and columns
     * @param quoteIdentifiers
     * @param baseTable
     * @return
     */
	private StringBuffer createTableBaseParts(boolean quoteIdentifiers,
			SybaseASABaseTable baseTable) 
	{
		String schemaName = baseTable.getSchema().getName();
        String tableName = baseTable.getName();
        if (quoteIdentifiers) 
        {
            schemaName = getDoubleQuotedString(schemaName);
            tableName = getDoubleQuotedString(tableName);
        }
        
        
        StringBuffer statement = new StringBuffer();
        
        //CREATE TABLE header
        statement.append(CREATE);
        statement.append(SPACE);
        
        //Deal with the proxy table created by "create existing table..." commands.
        if(baseTable instanceof SybaseASABaseProxyTable && ((SybaseASABaseProxyTable)baseTable).isExisting())
        {
            statement.append(EXISTING).append(SPACE);	
        }
        statement.append(TABLE);
        statement.append(SPACE);
        statement.append(schemaName);
        statement.append(DOT);
        statement.append(tableName);
        statement.append(SPACE);
        
        //Column and Pctfree
        statement.append(getColumnAndPctfree(baseTable, quoteIdentifiers));
		return statement;
	}
    
    /**
     * Delegate to creatTable() and append 'at location' to the end 
     * @param table
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     * @author triston
     */
    protected String[] createProxyTable(SybaseASABaseProxyTable table, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        StringBuffer stmt = new StringBuffer(512);
        stmt.append(createTableBaseParts(quoteIdentifiers,table).toString());
        stmt.append(NEWLINE).append(AT).append(SPACE).append(SINGLE_QUOTE).append(table.getRemoteObjectLocation()).append(SINGLE_QUOTE);
        return new String[] {stmt.toString()};
    }
    
    /**
     * For local temp table:
     * 
     * <pre>
     *  DECLARE LOCAL TEMPORARY TABLE table-name
     *  ( { column-definition [ column-constraint . . . ] | table-constraint | pctfree
     *  }, . . . )
     *  [ ON COMMIT { DELETE | PRESERVE } ROWS
     *  | NOT TRANSACTIONAL ]
     * </pre>
     * 
     * For global temp table:
     * 
     * <pre>
     *  CREATE GLOBAL TEMPORARY TABLE [ owner.]table-name
     *  ( { column-definition | table-constraint | pctfree }, . . . )
     *  [ ON COMMIT { DELETE | PRESERVE } ROWS
     *  | NOT TRANSACTIONAL]
     * </pre>
     * 
     */
    protected String[] createTempoeryTable(TemporaryTable tempTable, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        if (!(tempTable instanceof SybaseASATempTable))
        {
            return null;
        }
        StringBuffer stmt = new StringBuffer();
        if (tempTable.isLocal())
        {
            // create Local Temp Table header
            stmt.append(DECLARELOCALTEMP).append(SPACE);
            String tableName = tempTable.getName();
            if (quoteIdentifiers)
            {
                tableName = getDoubleQuotedString(tableName);
            }
            stmt.append(tableName).append(SPACE);
        }
        else
        {
            // create Global Temp Table header
            stmt.append(CREATE).append(SPACE).append(GLOBALTEMPTABLE).append(SPACE).append(
                    getName(tempTable, quoteIdentifiers, qualifyNames)).append(SPACE);
        }
        // Column and Pctfree
        stmt.append(getColumnAndPctfree(tempTable, quoteIdentifiers)).append(NEWLINE);

        if (((SybaseASATempTable)tempTable).getTransactionOption() == TransactionOption.NOT_TRANSACTION_LITERAL)
        {
            stmt.append(NOTTRANSACTIONAL);
        }

        if (((SybaseASATempTable)tempTable).getTransactionOption() == TransactionOption.PRESERVE_LITERAL)
        {

            stmt.append(ONCOMMITPRESERVE);
        }

        if (((SybaseASATempTable)tempTable).getTransactionOption() == TransactionOption.DELETE_LITERAL)
        {

            stmt.append(ONCOMMITDELETE);
        }
        
        List results = new ArrayList();
        results.add(stmt.toString());
        
        String tableComment = createComment(tempTable, quoteIdentifiers, qualifyNames);
        if(tableComment != null && !tableComment.equals(""))
        {
            results.add(tableComment);
        }
        for(int i = 0; i<tempTable.getColumns().size(); i++)
        {
            Column column = (Column)tempTable.getColumns().get(i);
            String colComment = createComment(column, quoteIdentifiers, qualifyNames);
            if(colComment != null && !colComment.equals(""))
            {
                results.add(colComment);
            }
        }
        
        return (String[])results.toArray(new String[results.size()]);
    }
    
    /**
     * @author huiwan
     */
    public String[] createIndex(Index index, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
    	
        SybaseASABaseIndex asaIndex = (SybaseASABaseIndex) index;
        StringBuffer statement = new StringBuffer();
        statement.append(CREATE + SPACE);
        // virtual
        if (asaIndex instanceof SybaseASAIndex && ((SybaseASAIndex)asaIndex).isVirtual())
        {
            statement.append(VIRTUAL + SPACE);
        }
        // Unique
        if (index.isUnique())
        {
            statement.append(UNIQUE + SPACE);
        }
        // Clustered
        if (index.isClustered())
        {
            statement.append(CLUSTERED + SPACE);
        }      
        // index name and column
        statement.append(INDEX + SPACE + getName(index, quoteIdentifiers, qualifyNames) + SPACE + NEWLINE + TAB + ON
                + SPACE + getName(index.getTable(), quoteIdentifiers, qualifyNames) + SPACE + NEWLINE + TAB
                + LEFT_PARENTHESIS + getIndexKeyColumns(index, quoteIdentifiers) + RIGHT_PARENTHESIS);
        // dbspace
        if (asaIndex.getDbSpace() != null)
        {
            statement.append(NEWLINE + TAB + ON + SPACE
                    + getName(asaIndex.getDbSpace(), quoteIdentifiers, qualifyNames));
        }
        //create description (not the comments inside source)
        String comment = createComment(index, quoteIdentifiers, qualifyNames);
        if (comment != null && !comment.trim().equals("") && !(asaIndex instanceof SybaseASAIndex && ((SybaseASAIndex)asaIndex).isVirtual()))
        {
            return new String[]
            {
                statement.toString(), comment
            };
        }
        return new String[]
        {
            statement.toString()
        };
    }


    /**
     * Returns the SQL Statement for adding unique constraint/primary key clause.<p>
     * See following SQL Syntax: 
     * <pre>
     * alter table [[database.][owner].table_name
     *    add CONSTRAINT constraint-name ] { UNIQUE ( column-name, . . . )
     *      | PRIMARY KEY [ CLUSTERED ] ( column-name, . . . )
     * </pre>
     * @param constraint
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @return String[]
     */
    public String[] addUniqueConstraint(UniqueConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        if(constraint==null)
        {
            return null;
        }
        StringBuffer statement = new StringBuffer(128);
        statement.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                getName(constraint.getBaseTable(), quoteIdentifiers, qualifyNames)).append(SPACE);
        statement.append(NEWLINE).append(TAB).append(ADD).append(SPACE);
        statement.append(this.getAddUniqueConstraintClause(constraint, quoteIdentifiers));
        return new String[]{statement.toString()};
    }

    protected String getReferenceMembersString(EList members, boolean quoteIdentifiers)
    {
        StringBuffer statement = new StringBuffer(128);
        Iterator it = members.iterator();
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
    
    /**
     * Returns the SQL Statement for adding foreign key clause.<p>
     * See following SQL Syntax: 
     * <pre>
     * alter table [[database.][owner].table_name
     *    add {[constraint constraint_name]
     *    [ NOT NULL ] FOREIGN KEY [ role-name ] [ (column-name, . . . ) ]
     *      REFERENCES table-name [ (column-name, . . . ) ] [ CLUSTERED ]
     *       [ actions ] [ CHECK ON COMMIT ]
     *  actions :
     *   [ ON UPDATE action ] [ ON DELETE action ]
     *  action :
     *   CASCADE | SET NULL | SET DEFAULT | RESTRICT
     * </pre>
     * @param constraint
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @return String[]
     */
    public String[] addForeignKey(ForeignKey foreignKey, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        if(foreignKey==null)
        {
            return null;
        }
        SybaseASAForeignKey asaForeignKey = (SybaseASAForeignKey)foreignKey;
        UniqueConstraint uniqueConstraint = foreignKey.getUniqueConstraint();
        Table parentTable = null;
        String parentKey = null;
        if (uniqueConstraint != null)
        {
            parentTable = uniqueConstraint.getBaseTable();
        }
        
        EList refMembers = foreignKey.getReferencedMembers();
        if (parentTable == null || refMembers==null || refMembers.size()==0)
        {
            // TODO report error
            return null;
        }
        parentKey = getReferenceMembersString(refMembers, quoteIdentifiers);
        StringBuffer statement = new StringBuffer(128);
        statement.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                super.getName(foreignKey.getBaseTable(), quoteIdentifiers, qualifyNames)).append(SPACE).append(ADD).append(SPACE);
        String name = foreignKey.getName();
        if (name != null && name.trim().length() != 0)
        {
            if (quoteIdentifiers)
            {
                name = super.getDoubleQuotedString(name);
            }
            statement.append(CONSTRAINT).append(SPACE).append(name).append(SPACE);
        }
        if(asaForeignKey.isNullable())
        {
            statement.append(SPACE);
        }
        else
        {
            statement.append(NOT).append(SPACE).append(NULL).append(SPACE);
        }
        statement.append(FOREIGN_KEY).append(SPACE).append(LEFT_PARENTHESIS).append(getKeyColumns(foreignKey,quoteIdentifiers))
                .append(RIGHT_PARENTHESIS).append(NEWLINE);
        statement.append(TAB).append(REFERENCES).append(SPACE).append(
                super.getName(parentTable, quoteIdentifiers, qualifyNames)).append(SPACE).append(LEFT_PARENTHESIS)
                .append(parentKey).append(RIGHT_PARENTHESIS);
        ReferentialActionType action = foreignKey.getOnDelete();
        if(action != ReferentialActionType.NO_ACTION_LITERAL) {
            statement.append(NEWLINE).append(TAB).append(ON).append(SPACE).append(DELETE).append(SPACE);            
        }
        statement.append(getReferentialAction(action));
        
        action = foreignKey.getOnUpdate();
        if(action != ReferentialActionType.NO_ACTION_LITERAL) {
            statement.append(NEWLINE).append(TAB).append(ON).append(SPACE).append(UPDATE).append(SPACE);     
        }
        statement.append(getReferentialAction(action));
        if(asaForeignKey.isCheckOnCommit())
        {
            statement.append(NEWLINE).append(TAB).append(CHECK).append(SPACE).append(ON).append(SPACE).append(COMMIT);
        }
        if(asaForeignKey.isClustered())
        {
            statement.append(NEWLINE).append(TAB).append(CLUSTERED);
        }
        else if(!asaForeignKey.isClustered())
        {
            statement.append(NEWLINE).append(TAB).append(NONCLUSTERED);
        }
        String addSql = statement.toString();
        
        String comments = createComment(foreignKey, quoteIdentifiers, qualifyNames);
        if(comments!=null)
        {
            return new String[]{addSql,comments};
        }
        return new String[]{addSql};
    }
    
    /**
     * <pre>
     *   ALTER TABLE [ owner.]table-name
     *   ADD column-name data-type [ NOT NULL ] [ DEFAULT default-value ] [ column-constraint ... ]
     * </pre>
     * 
     * @author linsong
     */
    public String[] createColumn(Column col, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax) {
        
        StringBuffer sb = new StringBuffer(256);
        sb.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                getName(col.getTable(), quoteIdentifiers, qualifyNames))
                .append(SPACE).append(ADD).append(SPACE).append(getColumnString(col, quoteIdentifiers));
        String comment = "";
        if (col.getDescription() != null && col.getDescription().trim().length() != 0)
        {
            comment = createComment(col, quoteIdentifiers, true);
            return new String[]{sb.toString(), comment};
        }
        return new String[]{sb.toString()};
    }
    
    /**
     * <pre>
     *  CREATE DBSPACE dbspace-name AS filename
     * </pre>
     * @author linsong
     */
    public String[] createDBSpace(SybaseASABaseDBSpace dbSpace, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        StringBuffer sb = new StringBuffer(64);
        sb.append(CREATE).append(SPACE).append(DBSPACE).append(SPACE).append(
                getName(dbSpace, quoteIdentifiers, qualifyNames)).append(SPACE).append(AS).append(SPACE).append(
                        getSingleQuotedString(dbSpace.getFileName()));
        return new String[]{sb.toString()};
    }
    
    /**
     * <pre>
     * GRANT CONNECT TO userid, ...
     *  IDENTIFIED BY password, ...
     * ;
     * GRANT {
     *   GROUP,
     *   MEMBERSHIP IN GROUP userid, ...,
     *   [ RESOURCE | ALL ]
     * }
     * TO userid, ...
     * </pre>
     * @author linsong
     */
    public String[] createAuthorizationId(AuthorizationIdentifier authId, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        List results = new ArrayList();
        StringBuffer sb1 = new StringBuffer(128);
        String name = getName(authId, quoteIdentifiers, qualifyNames); 
        sb1.append(GRANT).append(SPACE).append(CONNECT).append(SPACE).append(TO).append(SPACE).append(name).append(
                NEWLINE).append(TAB).append(IDENTIFIED).append(SPACE).append(BY).append(SPACE).append(TEMP_PASSWORD);
        results.add(sb1.toString());
        if(authId instanceof Group)
        {
            //set user as group
            StringBuffer sb2 = new StringBuffer(128);
            sb2.append(GRANT).append(SPACE).append(GROUP).append(SPACE).append(TO).append(SPACE).append(name);
            results.add(sb2.toString());
            
            //set user into this group
            EList users = ((Group)authId).getUser();
            StringBuffer strUserNames = new StringBuffer(128);
            for(int i = 0; i<users.size(); i++)
            {
                User user = (User)users.get(i);
                strUserNames.append(getName(user, quoteIdentifiers, qualifyNames));
                if(i != users.size() - 1)
                    strUserNames.append(COMMA).append(SPACE);
            }
            if(users.size() > 0)
            {
                StringBuffer sb3 = new StringBuffer(128);
                sb3.append(GRANT).append(SPACE).append(MEMBERSHIP).append(SPACE).append(IN).append(SPACE).append(GROUP)
                .append(SPACE).append(name).append(NEWLINE).append(TO).append(SPACE).append(strUserNames);
                results.add(sb3.toString());
            }
        }
        return (String[])results.toArray(new String[results.size()]);
    }
    
    public String dropEvent(Event event, boolean quoteIdentifiers, boolean qualifyNames) 
    {
        String dropStatement = DROP + SPACE + EVENT + SPACE + getName(event, quoteIdentifiers, qualifyNames);
        String eventName = event.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_EVENT, new Object[]
        {
            eventName
        });
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]
        {
            existingCheck, dropStatement
        });
    }

    public String dropProcedure(Procedure procedure, boolean quoteIdentifiers, boolean qualifyNames) 
    {
        String dropStatement = DROP + SPACE + PROCEDURE + SPACE + getName(procedure, quoteIdentifiers, qualifyNames);
        String ownerName = procedure.getSchema().getName();
        String procName = procedure.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_PROCEDURE, new Object[]
        {
            ownerName, procName
        });
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]
        {
            existingCheck, dropStatement
        });
    }
        
    public String dropFunction(UserDefinedFunction function, boolean quoteIdentifiers, boolean qualifyNames) 
    {
        String dropStatement = DROP + SPACE + FUNCTION + SPACE + getName(function, quoteIdentifiers, qualifyNames);
        
        String ownerName = function.getSchema().getName();
        String procName = function.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_PROCEDURE, new Object[]{ownerName, procName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }
    
    public String dropTable(BaseTable table, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String dropStatement = super.dropTable(table, quoteIdentifiers, qualifyNames);
        
        String ownerName = table.getSchema().getName();
        String tableName = table.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_TABLE, new Object[]{ownerName, tableName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }
    
    public String dropView(ViewTable view, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String dropStatement = super.dropView(view, quoteIdentifiers, qualifyNames);
        
        String ownerName = view.getSchema().getName();
        String viewName = view.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_VIEW, new Object[]{ownerName, viewName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }
    
    public String dropForeignKey(ForeignKey foreignKey, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer statement = dropConstraint(foreignKey, quoteIdentifiers, qualifyNames);
        statement.append(SPACE).append(getName(foreignKey, quoteIdentifiers));
        String dropStatement =  statement.toString();
        return getConstraintDropStatement(foreignKey, dropStatement);
    }
    
    public String dropCheckConstraint(CheckConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames)
    {
        String dropStatement = dropTableConstraint(constraint,quoteIdentifiers,qualifyNames);
        return getConstraintDropStatement(constraint, dropStatement);
    }
    
    public String dropUniqueConstraint(UniqueConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer statement = dropConstraint(constraint, quoteIdentifiers, qualifyNames);
        
        String dropStatement = null;
        
        if(constraint instanceof PrimaryKey)
        {
            dropStatement = statement.toString();
        }
        else
        {
        	statement = new StringBuffer(128);
            statement.append(ALTER).append(SPACE).append(TABLE).append(SPACE)
                .append(getName(constraint.getBaseTable(), quoteIdentifiers, qualifyNames)).append(SPACE);
            statement.append(DROP).append(SPACE).append(CONSTRAINT).append(SPACE).append(getName(constraint, quoteIdentifiers));
            dropStatement = statement.toString();
        }

        return getConstraintDropStatement(constraint, dropStatement);
    }
    
    protected String getConstraintDropStatement(TableConstraint constraint, String dropStatement)
    {
        Table table = constraint.getBaseTable();
        Schema schema = table.getSchema();
        String ownerName = schema.getName();
        String tableName = table.getName();
        String constraintName = constraint.getName();
        String type = "";
        if(constraint instanceof PrimaryKey)
        {
            type = BaseTableASABaseLoader.PRIMARY_KEY_TYPE;
        }
        else if(constraint instanceof UniqueConstraint)
        {
            type = BaseTableASABaseLoader.UNIQUE_CONSTRAINT_TYPE;
        }
        else if(constraint instanceof ForeignKey)
        {
            type = BaseTableASABaseLoader.FOREIGN_KEY_TYPE;
        }
        else if(constraint instanceof SybaseASABaseColumnCheckConstraint)
        {
            type = BaseTableASABaseLoader.COLUMN_CHECK_CONSTRAINT_TYPE;
        }
        else 
        {
            type = BaseTableASABaseLoader.TABLE_CHECK_CONSTRAINT_TYPE;
        }
        
        SybaseASABaseDatabase database = (SybaseASABaseDatabase)schema.getDatabase();
        String queryPattern = QueryObjectsSQL.QUERY_CONSTRAINT;
        if(database.isBaseOnASA10())
        {
            queryPattern = QueryObjectsSQL.QUERY_CONSTRAINT_FOR_ASA10;
        }
        String existingCheck = MessageFormat.format(queryPattern, new Object[]
        {
            ownerName, tableName, constraintName, type
        });
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }
    
    /**
     * Returns the SQL Statement for droping domain.
     * <p>
     * See following SQL Syntax:
     * 
     * <pre>
     *  DROP { DATATYPE | DOMAIN } datatype-name
     *  
     * </pre>
     * 
     * @param asaUDT
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     * 
     * @author renj
     */
    public String dropUserDefinedType(UserDefinedType udt, boolean quoteIdentifiers, boolean qualifyNames)
    {
        SybaseASABaseUserDefinedType asaUDT = (SybaseASABaseUserDefinedType)udt;
        StringBuffer sb = new StringBuffer(128);
        if (quoteIdentifiers) {
            sb.append(DROP).append(SPACE).append(DOMAIN).append(SPACE).append(SQLUtil.quote(asaUDT.getName(), DOUBLE_QUOTE));
        } else {
            sb.append(DROP).append(SPACE).append(DOMAIN).append(SPACE).append(asaUDT.getName());
        }
        String dropStatement = sb.toString();
        
        String udtName = udt.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_UDT, new Object[]{udtName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }
    
    /**
     * 
     * @param dbspace
     * @param quoteIdentifiers
     * @param qualifyNames
     * @return
     * @author linsong
     */
    public String dropDBSpace(SybaseASABaseDBSpace dbspace, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer sb = new StringBuffer(128);
        sb.append(DROP).append(SPACE).append(DBSPACE).append(SPACE).append(dbspace.getName());

        String dropStatement = sb.toString();
        
        String dbspaceName = dbspace.getName();
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_DBSPACE, new Object[]{dbspaceName});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }
    
    /**
     * <pre>
     * REVOKE CONNECT FROM userid, ...
     * </pre>
     * @author linsong
     */
    public String dropAuthorizationId(AuthorizationIdentifier authId, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer sb = new StringBuffer(128);
        sb.append(REVOKE).append(SPACE).append(CONNECT).append(SPACE).append(FROM).append(SPACE).append(
                getName(authId, quoteIdentifiers, qualifyNames));
        String dropStatement = sb.toString();
        
        String authIdName = authId.getName();
        String userOrGroup = (authId instanceof Group) ? "Y" : "N";
        String existingCheck = MessageFormat.format(QueryObjectsSQL.QUERY_AUTHID, new Object[]{authIdName, userOrGroup});
        return MessageFormat.format(getDropPreconditionPattern(), new Object[]{existingCheck, dropStatement});
    }
    
    protected String getColumnString(Column column, boolean quoteIdentifiers) {
        
    	SybaseASABaseColumn baseColumn = (SybaseASABaseColumn) column;
    	
    	StringBuffer statement = new StringBuffer();
        
    	String columnName = baseColumn.getName();
    	String dataTypeName = getDataTypeString(column, column.getTable().getSchema(), quoteIdentifiers);//baseColumn.getDataType().getName();
    	if (quoteIdentifiers)
    	{
    		columnName = getDoubleQuotedString(columnName);
    	}
    	
		statement.append(columnName);
		statement.append(SPACE);
		statement.append(dataTypeName);
		
		// [ NOT ] NULL
		if (baseColumn.isNullable())
		{
			statement.append(SPACE);
			statement.append(NULL);
		}
		else
		{
			statement.append(SPACE);
			statement.append(NOT);
			statement.append(SPACE);
			statement.append(NULL);
		}
		
		// DEFAULT default-value 
		if (baseColumn.getDefaultValue() != null)
		{
			String defaultName = column.getDefaultValue();
			
			statement.append(SPACE);
            statement.append(baseColumn.isIsComputedColumn() ? COMPUTE : DEFAULT);
			statement.append(SPACE);
            String defaultValueStr = (String)defaultName;
            if(baseColumn.isIsComputedColumn())
            {
                defaultValueStr = "(" + (String)defaultName + ")";
            }
            statement.append(defaultValueStr);
		}
		
		// CHECK CONSTRAINT
        //since column may have more than one columncheckconstraint
        //we treat column check constraint as table check 
//		if (baseColumn.getColumnConstraint() != null)
//		{
//			statement.append(SPACE);
//			statement.append(getAddCheckConstraintClause(baseColumn.getColumnConstraint(), quoteIdentifiers));
//		}

        return statement.toString();
    }
	
    protected String getIdentityString(IdentitySpecifier identitySpecifier) {
        String clause = "START WITH " + identitySpecifier.getStartValue() //$NON-NLS-1$
        	+ " ,INCREMENT BY " + identitySpecifier.getIncrement(); //$NON-NLS-1$
        return clause;
    }
    
    /**
     * Generates watcom style parameters without parenthesis
     * 
     * <pre>
     *      parameter_mode parameter-name data-type [ DEFAULT expression ]
     *      | SQLCODE
     *      | SQLSTATE
     * </pre>
     */
    public String getWatcomParameters(Routine routine, boolean fullSyntax) {

        StringBuffer sb = new StringBuffer();
        Iterator it = routine.getParameters().iterator();
        while(it.hasNext()) {
            Parameter p = (Parameter) it.next();
            sb.append(getWatcomParameter(routine, p));
            if(it.hasNext()) {
                sb.append(COMMA).append(SPACE);
            }

        }
        return sb.toString();
    }

    public String getWatcomParameter(Routine routine,  Parameter p)
    {
        ParameterType paramType = null;
        if (p instanceof SybaseASABaseParameter)
        {
            paramType = ((SybaseASABaseParameter)p).getParmType();
        }

        ParameterMode mode = p.getMode();
        String name = p.getName();
        String type = this.getDataTypeString(p, routine.getSchema());
        String dft = null;
        if (p instanceof SybaseParameter)
        {
            dft = ((SybaseParameter)p).getDefaultValue();
        }
        return getWatcomParameter(name, type, dft, mode.getLiteral(), paramType);
    }
    

    public String getWatcomParameter(String name, String type, String defValue, String mode, ParameterType paramType)
    {
        if (paramType != null && (paramType.getValue() == ParameterType.SQLCODE || paramType.getValue() == ParameterType.SQLSTATE))
        {
            return paramType.getLiteral();
        }

        StringBuffer sb = new StringBuffer();
        sb.append(mode).append(SPACE);
        if(name != null && name.length() != 0) {
            sb.append(name).append(SPACE);
        }
        sb.append(type);
        if(defValue != null && defValue.length() != 0) {
            sb.append(SPACE).append(DEFAULT).append(SPACE).append(defValue).append(SPACE);
        }
        return sb.toString();
    }
    
    protected String getFuncParameters(UserDefinedFunction function, boolean fullSyntax) {
        
        StringBuffer sb = new StringBuffer();
        Iterator it = function.getParameters().iterator();
        while(it.hasNext()) {
            Parameter p = (Parameter) it.next();
            
            ParameterMode mode = p.getMode();
            if(mode == ParameterMode.IN_LITERAL && fullSyntax ) {
                sb.append(IN).append(SPACE);
            }
            //no out parameters
            String name = p.getName();
            if(name != null && name.length() != 0) {
                sb.append(p.getName()).append(SPACE);
            }
            sb.append(this.getDataTypeString(p, function.getSchema()));
            if(it.hasNext()) {
                sb.append(COMMA).append(SPACE);
            }
        }
        return sb.toString();
    }
    
    protected String getExternalNameOption(Routine routine,boolean quoteIdentifiers, boolean qualifyNames) {
        StringBuffer sb = new StringBuffer();
        if (JAVA.equalsIgnoreCase(routine.getLanguage()))
        {
            //SQLJ
            if (routine instanceof Procedure)
            {
                if (((Procedure)routine).getMaxResultSets() > 0)
                {
                    sb.append(TAB).append(DYNAMIC_RESULT_SETS).append(SPACE).append(((Procedure)routine).getMaxResultSets()).append(NEWLINE);
                }
            }
            sb.append(TAB).append(EXTERNAL_NAME).append(SPACE).append(routine.getExternalName()).append(SPACE);
            sb.append(LANGUAGE).append(SPACE).append(JAVA).append(NEWLINE);
        }
        else
        {
            //dll
            sb.append(TAB).append(EXTERNAL_NAME).append(SPACE).append(routine.getExternalName()).append(NEWLINE);
        }

        return sb.toString();
    }
 
    protected String getFunctionReturnsClause(UserDefinedFunction function) {
        if(function.getReturnScalar() != null) {
            Parameter scaler = function.getReturnScalar();
            StringBuffer sb = new StringBuffer();
            sb.append(RETURNS).append(SPACE).append(getDataTypeString(scaler,function.getSchema())).append(NEWLINE);
            return sb.toString();
        }
        return EMPTY_STRING;
    }
    
    
    public int getSyntaxType(Procedure routine)
    {
        if (routine instanceof SybaseASABaseRemoteProcedure)
        {
            return SYNTAX_TYPE_PROXY;
        }
        if (routine.getExternalName() != null)
        {
            if ("Java".equalsIgnoreCase(routine.getLanguage()))
            {
                return SYNTAX_TYPE_SQLJ;
            }
            else
            {
                return SYNTAX_TYPE_LIBRARY_CALL;
            }
        }
        //watcom1:result, on exception resume,no @ in parameter name/watcom2:sqlcode, sqlstate/tsql:others
        boolean watcom1 = false;
        EList params = routine.getParameters();
        if (routine instanceof SybaseASABaseProcedure)
        {
            watcom1 = ((SybaseASABaseProcedure)routine).isOnExceptionResume();
        }
        
        if ( !watcom1 && params != null && params.size() >= 1)
        {
            for (Iterator it = params.iterator(); it.hasNext();)
            {
                Parameter param = (Parameter)it.next();
                if (param instanceof SybaseASABaseParameter)
                {
                    SybaseASABaseParameter sybParam = ((SybaseASABaseParameter)param);
                    if (sybParam.getParmType().getValue() == ParameterType.VARIABLE)
                    {
                        watcom1 |= (param.getName() != null && !param.getName().startsWith("@"));
                    }
                    else if (sybParam.getParmType().getValue() == ParameterType.RESULT)
                    {
                        if (routine.getSource().getBody() != null)
                        {
                            int[] index = QuickSQLParser.getInstance().find(routine.getSource().getBody(), new String[] {"result", "("});
                            if (index != null && index.length > 0 && index[0] > 0)
                            {
                                return SYNTAX_TYPE_WATCOM1;
                            }
                        }
                    }
                    else if (sybParam.getParmType() == ParameterType.SQLSTATE_LITERAL || sybParam.getParmType() == ParameterType.SQLCODE_LITERAL)
                    {
                        return SYNTAX_TYPE_WATCOM2;
                    }
                }
            }
        }
        if (watcom1)
        {
            return SYNTAX_TYPE_WATCOM1;
        }
        if (routine.getSource().getBody() != null)
        {
            int[] index = QuickSQLParser.getInstance().find(routine.getSource().getBody(), new String[] {"as"});
            if (index == null || index.length == 0 || index[0] < 0)
            {
                return SYNTAX_TYPE_WATCOM1;
            }
        }        
        return SYNTAX_TYPE_TSQL;
    }
	

	/**
	 * get ASA table column definition and pctfree syntax.
	 * Syntax: ( { column-definition | pctfree }, . . . )
	 * 
	 * @param baseTable
	 * @param quoteIdentifiers
	 * @return column definition and pctfree syntax.
	 */
	protected String getColumnAndPctfree(BaseTable baseTable, boolean quoteIdentifiers)
	{
		StringBuffer statement = new StringBuffer();
		
		statement.append(LEFT_PARENTHESIS);
		statement.append(NEWLINE);
		
		EList list = baseTable.getColumns();
		String pctFREE = getPctfree(baseTable, quoteIdentifiers);
		//size is used to decide if add COMMA.
		int size = list.size();
		if (pctFREE != null)
		{
			size++;
		}
				
		//column-definition
		for (int i = 0; i < list.size(); i++)
		{
			// column-name data-type 
			// [ DEFAULT default-value ] [ column-constraint . . . ]
			SybaseASABaseColumn column = (SybaseASABaseColumn) list.get(i);
			
			statement.append(getColumnString(column, quoteIdentifiers));

			if (size > 1 && i < (size - 1))
			{
				statement.append(COMMA);
				statement.append(SPACE);
				statement.append(NEWLINE);
			}
			
		}
		
		//PCTFREE
		if (pctFREE != null)
		{
			statement.append(pctFREE);
		}
		
		statement.append(RIGHT_PARENTHESIS);
		
		return statement.toString();
	}
	/**
	 * 
	 * get ASA table dbspace syntax.
	 * Syntax:[ { IN | ON } dbspace-name ]
	 *  
	 * @param baseTable
	 * @return dbspace syntax
	 * 
	 */
	protected String getDBSpace(SybaseASABaseTable baseTable)
	{
		StringBuffer statement = null;
		
		if (baseTable.getDbSpace() != null && baseTable.getDbSpace().getName() != null)
		{
			statement = new StringBuffer();
			
			statement.append(NEWLINE);
			statement.append(IN);
			statement.append(SPACE);
			statement.append(baseTable.getDbSpace().getName());
		}	
		
		if (statement == null)
		{
			return null;
		}
		else
		{
			return statement.toString();
		}
	}
	
	
	/**
	 * Get ASA table PCTFREE syntax.
	 * Syntax : PCTFREE percent-free-space
	 * 
	 * @param table
	 * @param quoteIdentifiers
	 * @param qualifyNames
	 * @return pctfree syntax
	 */
	protected String getPctfree(BaseTable table, boolean quoteIdentifiers)
	{
		StringBuffer statement = null;
		
		if (table instanceof SybaseASATable)
		{
			statement = new StringBuffer();
			SybaseASATable sybaseASATable = (SybaseASATable) table;
			
			//protected static final int PCTFREE_EDEFAULT = -1;??????????
			if ( sybaseASATable.getPctfree() != -1)
			{
				statement.append(PCTFREE);
				statement.append(SPACE);
				statement.append(sybaseASATable.getPctfree());
			}
		}
		
		if (table instanceof SybaseASATempTable)
        {
            statement = new StringBuffer();
            SybaseASATempTable sybaseASATable = (SybaseASATempTable) table;
            // protected static final int PCTFREE_EDEFAULT = -1;??????????
            if (sybaseASATable.getPctfree() != -1)
            {
                statement.append(PCTFREE);
                statement.append(SPACE);
                statement.append(sybaseASATable.getPctfree());
            }
        }
		
		if (statement == null)
		{
			return null;
		}
		else
		{
			return statement.toString();
		}
	}

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlBuilder#getAddUniqueConstraintClause(org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint,
     *      boolean)
     */
    protected String getAddUniqueConstraintClause(UniqueConstraint constraint, boolean quoteIdentifiers)
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
        statement.append(getUniqueConstraintType(constraint)).append(SPACE);
        SybaseASABaseUniqueConstraint asaUniqueConstraint = (SybaseASABaseUniqueConstraint) constraint;
        if(asaUniqueConstraint.isClustered())
        {
                statement.append(CLUSTERED).append(SPACE);
            }
        else if(!asaUniqueConstraint.isClustered())
        {
            statement.append(NONCLUSTERED).append(SPACE);
        }
        String keyColumns = getKeyColumns(constraint,quoteIdentifiers);
        statement.append(LEFT_PARENTHESIS).append(keyColumns).append(RIGHT_PARENTHESIS);
        return statement.toString();
    }
    

    protected String getKeyColumns(SybaseASAIndex index,boolean quoteIdentifiers)
    {
        StringBuffer statement = new StringBuffer(128);
        Iterator it = index.getMembers().iterator();
        if (it.hasNext())
        {
            IndexMember idxMember = (IndexMember) it.next();
            Column c = idxMember.getColumn();
            if(quoteIdentifiers)
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
            return null;
        }

        while (it.hasNext())
        {
            IndexMember idxMember = (IndexMember) it.next();
            Column c = idxMember.getColumn();
            if(quoteIdentifiers)
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

    public String getEventSchedules(SybaseASABaseEvent evt)
    {
        StringBuffer sb = new StringBuffer();
        EList schedules = evt.getSchedules();

        for (Iterator iter = schedules.iterator(); iter.hasNext();)
        {
            Schedule schedule = (Schedule) iter.next();
            sb.append(getEventSchedule(schedule));
            if (iter.hasNext())
            {
                sb.append(COMMA).append(NEWLINE);
            }
            else
            {
                sb.append(NEWLINE);
            }
        }
        return sb.toString();
    }

    public String getEventSchedule( Schedule schedule)
    {
        StringBuffer sb = new StringBuffer();
        String name = schedule.getName();
        Date startDate = schedule.getStartDate();
        Date startTime = schedule.getStartTime();
        Date stopTime = schedule.getStopTime();
        int daysOfWeek = schedule.getDaysOfWeek();
        int daysOfMonth = schedule.getDaysOfMonth();
        sb.append(SCHEDULE).append(SPACE);
        if (name != null)
        {
            sb.append(name).append(SPACE);
        }
        if (startTime != null)
        {
            if (stopTime != null)
            {
                sb.append(BETWEEN).append(SPACE).append(SINGLE_QUOTE).append(toTimeString(startTime)).append(SINGLE_QUOTE).append(SPACE);
                sb.append(AND).append(SPACE).append(SINGLE_QUOTE).append(toTimeString(stopTime)).append(SINGLE_QUOTE).append(NEWLINE);
            }
            else
            {
                sb.append(START).append(SPACE).append(TIME).append(SPACE).append(SINGLE_QUOTE).append(toTimeString(startTime)).append(SINGLE_QUOTE).append(NEWLINE);
            }
        }
        boolean recurring = schedule.isRecurring();
        if (recurring)
        {
            sb.append(EVERY).append(SPACE).append(schedule.getIntervalMount()).append(SPACE).append(schedule.getIntervalUnit().getLiteral()).append(SPACE);
        }
        
        if (daysOfWeek > 0)
        {
            sb.append(ON).append(SPACE).append(LEFT_PARENTHESIS).append(parseDaysOfWeek(daysOfWeek)).append(RIGHT_PARENTHESIS).append(SPACE);
        }
        else if (daysOfMonth != 0)//might be negative
        {
            sb.append(ON).append(SPACE).append(LEFT_PARENTHESIS).append(parseDaysOfMonth(daysOfMonth)).append(RIGHT_PARENTHESIS).append(SPACE);
        }
        
        if (startDate != null)
        {
            sb.append(START).append(SPACE).append(DATE).append(SPACE).append(SINGLE_QUOTE).append(toDateString(startDate)).append(SINGLE_QUOTE).append(SPACE);
        }
        
        return sb.toString();
    }
    
    //make it public to test
    public String parseDaysOfWeek(int daysOfWeek)
    {
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<7;i++)
        {
            if ((daysOfWeek & 1<<i) > 0)
            {
                sb.append(SINGLE_QUOTE).append(DAYS_OF_WEEK[i]).append(SINGLE_QUOTE).append(COMMA);
            }
        }
        if (sb.length() > 0)
        {
            //remove last comma
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }
    
    //make it public to test
    public String parseDaysOfMonth(int daysOfMonth)
    {
        StringBuffer sb = new StringBuffer();
        if ((daysOfMonth & 1<<31) != 0)
        {
            sb.append(0).append(COMMA);
        }
        for (int i=0;i<31;i++)
        {
            if ((daysOfMonth & 1<<i) != 0)
            {
                sb.append(i+1).append(COMMA);
            }
        }
        if (sb.length() > 0)
        {
            //remove last comma
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }
    
    
    /**
     * Formats the date object to String according to the default formatting style
     * TODO check locale
     * @param date
     * @return
     */
    protected String toDateString(Date date)
    {
        if (date != null)
        {
            return DATE_FORMAT.format(date);
        }
        return EMPTY_STRING;
    }
    
    /**
     * Formats the date object to String according to the the default formatting style 
     * TODO check locale
     * @param date
     * @return
     */
    protected String toTimeString(Date time)
    {
        if (time != null)
        {
            return TIME_FORMAT.format(time);
        }
        return EMPTY_STRING;
    }
    
    protected StringBuffer dropConstraint(TableConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames)
    {
        StringBuffer statement = new StringBuffer(128);
        statement.append(ALTER).append(SPACE).append(TABLE).append(SPACE)
            .append(getName(constraint.getBaseTable(), quoteIdentifiers, qualifyNames)).append(SPACE);
        statement.append(DROP).append(SPACE);
        if(constraint instanceof PrimaryKey)
        {
            statement.append(PRIMARY_KEY);
        }
        else if(constraint instanceof CheckConstraint)
        {
            statement.append(CHECK);
        }
        else if(constraint instanceof ForeignKey)
        {
            statement.append(FOREIGN_KEY);
        }
        else if(constraint instanceof UniqueConstraint)
        {
            statement.append(UNIQUE);
        }
        return statement;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder#addCheckConstraint(org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint, boolean, boolean, boolean)
     */
    
    public String[] addCheckConstraint(CheckConstraint constraint, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        if(constraint instanceof SybaseASABaseColumnCheckConstraint)
        {
            StringBuffer statement = new StringBuffer(128);
            BaseTable table = constraint.getBaseTable();
            SybaseASABaseColumnCheckConstraint columnCheckConstraint = (SybaseASABaseColumnCheckConstraint)constraint;
            SybaseASABaseColumn column = columnCheckConstraint.getColumn();
            if(table==null)
            {
                if(column!=null)
                {
                    table = (BaseTable)column.getTable();
                }
                else
                {
                    return new String[]{statement.toString()};
                }
            }
            if(table == null)
            {
                return new String[]{statement.toString()};
            }
            statement.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                    getName(table, quoteIdentifiers, qualifyNames)).append(SPACE);
            if(column != null)
            {
                String columnName = column.getName();
                if(quoteIdentifiers)
                {
                    columnName = getDoubleQuotedString(columnName);
                }
                statement.append(ALTER).append(SPACE).append(columnName).append(SPACE);
            }
            statement.append(NEWLINE).append(TAB).append(ADD).append(SPACE);
            statement.append(getAddCheckConstraintClause(constraint, quoteIdentifiers));
            return new String[]{statement.toString()};
        }
        return super.addCheckConstraint(constraint, quoteIdentifiers, qualifyNames, fullSyntax);
    }
    
    protected String getName(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames) {
        String name = trigger.getName();  
        //Changed for CR470798-2
        String tableName = trigger.getSubjectTable().getName();
        String schemaName = trigger.getSchema().getName();
    
        if(quoteIdentifiers) {
            name = this.getDoubleQuotedString(name);
            tableName = this.getDoubleQuotedString(tableName);
            schemaName = this.getDoubleQuotedString(schemaName);
        }
        if(qualifyNames) {
            name = schemaName + DOT + tableName + DOT + name;
        }
        
        return name;
    }
}

