package org.eclipse.datatools.enablement.sybase.ddl;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlGenerator;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.ecore.EObject;

/**
 * provide sckeletal implementation for database specified DdlGenrator, such as ASEDdlGenerator, 
 * ASADdlGenerator, IQDdlGenerator
 * 
 * @author linsong
 */
public abstract class SybaseDdlGenerator extends GenericDdlGenerator implements ISybaseDdlGenerator, DDLGenerator
{
    public final static byte GENERATE_CREATE_STATEMENTS_GEN_OPTION_INDEX  = 0; 
    public final static byte GENERATE_DROP_STATEMENTS_GEN_OPTION_INDEX  = 1;
    public final static byte GENERATE_FULLY_QUALIFIED_NAME_GEN_OPTION_INDEX = 2;
    public final static byte GENERATE_QUOTED_IDENTIFIER_GEN_OPTION_INDEX = 3;
    public final static byte GENERATE_FULL_SYNTAX_GEN_OPTION_INDEX = 4;
    public final static byte GENERATE_PRIVILEGES_GEN_OPTION_INDEX = 5;
    public final static byte GENERATE_COMMENTS_GEN_OPTION_INDEX = 6;
    public final static byte GENERATE_SETUSER_GEN_OPTION_INDEX = 7;
    public final static byte GENERATE_USEDATABASE_GEN_OPTION_INDEX = 8;
    
    
    protected SybaseDdlBuilder sybaseDdlBuilder;

    protected Object           _parameter;
    
    /**
     * @return all global EngineeringOption array
     */
    public EngineeringOption[] getOptions()
    {
        return SybaseDdlGenerationOptions.getGlobalSybaseDDLGenerationOptions();
    }
    
//    /**
//     * 
//     * @return global generate options array
//     */
//    public EngineeringOption[] getGenrerationOptions()
//    {
//        byte[] optionIndices = getCommonGenerateOptionIndices();
//        EngineeringOption[] results = new EngineeringOption[optionIndices.length];
//        for(int i = 0; i<optionIndices.length; i++)
//        {
//            results[i] = this.getOptions()[optionIndices[i]];
//        }
//        return results;
//    }
    
    /**
     * @return cloned generate options array
     */
    public EngineeringOption[] createGenerationOptions()
    {
        byte[] generateionIndices = getCommonGenerateOptionIndices();
    	return createOptions(generateionIndices);
    }
    
    ///for ddlgen wizard
    public EngineeringOption[] getGenerationOptions(int objectType)
    {
        byte[] genOptionIndices = getGenerationOptionIndices(objectType);
        return createOptions(genOptionIndices);
    } 
    
    public EngineeringOption[] getAdditionalOptions(int objectType)
    {
        byte[] additionOptionIndices = getAdditionalOptionIndices(objectType);
        return createOptions(additionOptionIndices);
    }
    
    /**
     * 
     * @param optionIndices
     * @return cloned specified option index options
     */
    static public EngineeringOption[] createOptions(byte[] optionIndices)
    {
        EngineeringOption[] results = new EngineeringOption[optionIndices.length];
        for(int i = 0; i<optionIndices.length; i++)
        {
            results[i] = createEngineeringOption(SybaseDdlGenerationOptions.getGlobalSybaseDDLGenerationOptions()[optionIndices[i]]);
        }
        return results;
    }
    
    static public EngineeringOption createEngineeringOption(EngineeringOption oldOption)
    {
        EngineeringOption newOption = null;
        switch(oldOption.getOptionType())
        {
            case EngineeringOption.BOOLEAN_OPTION:
                newOption = new EngineeringOption(oldOption.getId(), oldOption.getOptionName(), oldOption
                        .getOptionDescription(), oldOption.getBoolean(), oldOption.getCategory());
                break;
            case EngineeringOption.STRING_OPTION:
                newOption = new EngineeringOption(oldOption.getId(), oldOption.getOptionName(), oldOption
                        .getOptionDescription(), oldOption.getString(), oldOption.getCategory());
                break;
            case EngineeringOption.INTEGER_OPTION:
                newOption = new EngineeringOption(oldOption.getId(), oldOption.getOptionName(), oldOption
                        .getOptionDescription(), oldOption.getInt(), oldOption.getCategory());
                break;
            case EngineeringOption.ENUM_OPTION:
                newOption = new EngineeringOption(oldOption.getId(), oldOption.getOptionName(), oldOption
                        .getOptionDescription(), oldOption.getInt(), oldOption.getEnumLiterals(), oldOption
                        .getCategory());
                break;
        }
        return newOption;
    }

    public byte[] getCommonGenerateOptionIndices()
    {
        return new byte[]
        {
            SybaseDdlGenerationOptions.GENERATE_CREATE_STATEMENTS,
            SybaseDdlGenerationOptions.GENERATE_DROP_STATEMENTS, 
            SybaseDdlGenerationOptions.GENERATE_FULLY_QUALIFIED_NAME,
            SybaseDdlGenerationOptions.GENERATE_QUOTED_IDENTIFIER,
            SybaseDdlGenerationOptions.GENERATE_FULL_SYNTAX,
            SybaseDdlGenerationOptions.GENERATE_PRIVILEGE,
            SybaseDdlGenerationOptions.GENERATE_COMMENTS,
            SybaseDdlGenerationOptions.GENERATE_SETUSER,
            SybaseDdlGenerationOptions.GENERATE_USEDATABASE,
        };
    }
    
    /**
     * 
     * @param elements
     * @param progressMonitor
     * @param tableSet all tables contained in the tableSet will generate contained objects' ddl
     * @param options
     * @return 
     */
    public String[] generateDDL(SQLObject[] elements, EngineeringOption[] options, IProgressMonitor progressMonitor)
    {
        String[] statements;
        String[] creations = new String[0];
        String[] drops = new String[0];

        if (generateCreateStatement(options))
        {
            creations = createSQLObjects(elements, options, progressMonitor);
        }
        if (generateDropStatement(options))
        {
            drops = dropSQLObjects(elements, options, progressMonitor);
        }
        statements = new String[creations.length + drops.length];
        System.arraycopy(drops, 0, statements, 0, drops.length);
        System.arraycopy(creations, 0, statements, drops.length, creations.length);

        return statements;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlGenerator#generateDDL(org.eclipse.datatools.modelbase.sql.schema.SQLObject[], org.eclipse.core.runtime.IProgressMonitor)
     */
    public String[] generateDDL(SQLObject[] elements, IProgressMonitor progressMonitor)
    {
        return generateDDL(elements, getOptions(), progressMonitor);
    }

    public String[] createSQLObjects(SQLObject[] elements, EngineeringOption[] options,
            IProgressMonitor progressMonitor)
    {
        SybaseDdlScript script = new SybaseDdlScript();
        createStatements(elements, options, script, progressMonitor, 100);
        return script.getStatements(); 
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlGenerator#createSQLObjects(org.eclipse.datatools.modelbase.sql.schema.SQLObject[], boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)
     */
    public String[] createSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames,
            IProgressMonitor progressMonitor)
    {
        return createSQLObjects(elements, quoteIdentifiers, qualifyNames, true, progressMonitor);
    }
    
    public String[] createSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax, IProgressMonitor progressMonitor)
    {
        SybaseDdlScript script = new SybaseDdlScript();
        createStatements(elements, quoteIdentifiers, qualifyNames, fullSyntax, script, progressMonitor, 100);
        return script.getStatements();
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlGenerator#dropSQLObjects(org.eclipse.datatools.modelbase.sql.schema.SQLObject[], boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)
     */
    public String[] dropSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames,
            IProgressMonitor progressMonitor)
    {
        SybaseDdlScript script = new SybaseDdlScript();
        dropStatements(elements, quoteIdentifiers, qualifyNames, script, progressMonitor, 100);
        return script.getStatements();
    }
    
    public String[] dropSQLObjects(SQLObject[] elements, EngineeringOption[] options, IProgressMonitor progressMonitor)
    {
        SybaseDdlScript script = new SybaseDdlScript();
        dropStatements(elements, options, script, progressMonitor, 100);
        return script.getStatements();
    }

    /**
     * 
     * @param elements
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @param options
     * @param script
     * @param progressMonitor
     * @param task
     */
    protected void createStatements(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax, SybaseDdlScript script, IProgressMonitor progressMonitor,
            int task) 
    {
        if (this.sybaseDdlBuilder == null)
        {
            this.sybaseDdlBuilder = (SybaseDdlBuilder) createBuilder();
        }
        for (int i = 0; i < elements.length; i++)
        {
            createStatement(elements[i], quoteIdentifiers, qualifyNames, fullSyntax, script, progressMonitor,
                    task);
        }
    }
    
    public void createStatement(SQLObject element, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax,
            SybaseDdlScript script, IProgressMonitor progressMonitor, int task)
    {
        if (this.sybaseDdlBuilder == null)
        {
            this.sybaseDdlBuilder = (SybaseDdlBuilder) createBuilder();
        }

        if (element instanceof BaseTable)
        {
            String[] statements = sybaseDdlBuilder.createTable((BaseTable) element, quoteIdentifiers, qualifyNames, fullSyntax);
            for(int j = 0; j<statements.length; j++)
            {
                script.addCreateTableStatement(statements[j]);
            }
        }
//        else if(element instanceof TemporaryTable)
//        {
//            String[] statements = sybaseDdlBuilder.createTable((TemporaryTable) element, quoteIdentifiers, qualifyNames, fullSyntax);
//            for(int j = 0; j<statements.length; j++)
//            {
//                script.addCreateTableStatement(statements[j]);
//            }
//        }
        else if (element instanceof ViewTable)
        {
            String[] statements = sybaseDdlBuilder.createView((ViewTable) element, quoteIdentifiers, qualifyNames, fullSyntax);
            for(int j = 0; j<statements.length; j++)
            {
                script.addCreateViewStatement(statements[j]);
            }
        }
        else if (element instanceof Trigger)
        {
            String[] statement = sybaseDdlBuilder.createTrigger((Trigger) element, quoteIdentifiers, qualifyNames, fullSyntax);
            if (statement != null)
            {
                for (int j = 0; j < statement.length; j++)
                {
                    script.addCreateTriggerStatement(statement[j]);
                }
            }
        }
        else if (element instanceof CheckConstraint)
        {
            String[] statement = sybaseDdlBuilder.addCheckConstraint((CheckConstraint) element, quoteIdentifiers,
                    qualifyNames,fullSyntax);
            if (statement != null && statement.length>0)
            {
                for (int j = 0; j < statement.length; j++)
                {
                    script.addAlterTableAddConstraintStatement(statement[j]);
                }
            }
        }
        else if (element instanceof PrimaryKey)
        {
            String[] statement = sybaseDdlBuilder.addUniqueConstraint((UniqueConstraint) element, quoteIdentifiers,
                    qualifyNames,fullSyntax);
            if (statement != null && statement.length>0)
            {
                for (int j = 0; j < statement.length; j++)
                {
                    script.addAlterTableAddConstraintStatement(statement[j]);
                }
            }
        }
        else if (element instanceof UniqueConstraint)
        {
            String[] statement = sybaseDdlBuilder.addUniqueConstraint((UniqueConstraint) element, quoteIdentifiers,
                    qualifyNames,fullSyntax);
            if (statement != null && statement.length>0)
            {
                for (int j = 0; j < statement.length; j++)
                {
                    script.addAlterTableAddConstraintStatement(statement[j]);
                }
            }
        }
        else if (element instanceof ForeignKey)
        {
            String[] statement = sybaseDdlBuilder.addForeignKey((ForeignKey) element, quoteIdentifiers, qualifyNames,fullSyntax);
            if (statement != null && statement.length>0)
            {
                for (int j = 0; j < statement.length; j++)
                {
                    script.addAlterTableAddForeignKeyStatement(statement[j]);
                }
            }
        }
        else if (element instanceof Index)
        {
            String[] statement = sybaseDdlBuilder.createIndex((Index) element, quoteIdentifiers, qualifyNames, fullSyntax);
            if (statement != null)
            {
                for (int j = 0; j < statement.length; j++)
                {
                    script.addCreateIndexStatement(statement[j]);
                }
            }
        }
        else if (element instanceof Column)
        {
            Table table = ((Column)element).getTable();
            if (! (table instanceof RoutineResultTable))
            {
                String[] statement = sybaseDdlBuilder.createColumn((Column) element, quoteIdentifiers, qualifyNames, fullSyntax);
                for (int j = 0; j < statement.length; j++)
                {
                    script.addAlterTableStatement(statement[j]);
                }
            }
        }
        else if (element instanceof Event)
        {
            String[] statement = sybaseDdlBuilder.createEvent((Event) element, quoteIdentifiers, qualifyNames, fullSyntax);
            if (statement != null)
            {
                for (int j = 0; j < statement.length; j++)
                {
                    script.addCreateEventStatements(statement[j]);
                }
            }
        }
        else if (element instanceof Procedure)
        {
            String[] statement = sybaseDdlBuilder.createProcedure((Procedure) element, quoteIdentifiers, qualifyNames, fullSyntax);
            if (statement != null)
            {
                for (int j = 0; j < statement.length; j++)
                {
                    script.addCreateRoutineStatements(statement[j]);
                }
            }
        }
        else if (element instanceof UserDefinedFunction)
        {
            String[] statement = sybaseDdlBuilder.createUserDefinedFunction((UserDefinedFunction) element, quoteIdentifiers,
                    qualifyNames, fullSyntax);
            if (statement != null)
            {
                for (int j = 0; j < statement.length; j++)
                {
                    script.addCreateRoutineStatements(statement[j]);
                }
            }
        }
        else if (element instanceof Database)
        {
            String[] statement = sybaseDdlBuilder.createDatabase((Database) element, quoteIdentifiers, qualifyNames, fullSyntax);
            if (statement != null)
            {
                for (int j = 0; j < statement.length; j++)
                {
                    script.addCreateDatabaseStatements(statement[j]);
                }
            }

        }
        else if (element instanceof Catalog)
        {
            String[] statement = sybaseDdlBuilder.createCatalogs((Catalog) element, quoteIdentifiers, qualifyNames, fullSyntax);
            if (statement != null)
            {
                for (int j = 0; j < statement.length; j++)
                {
                    script.addCreateDatabaseStatements(statement[j]);
                }
            }
        }
        else if(element instanceof UserDefinedType)
        {
            String[] statement = sybaseDdlBuilder.createUserDefinedType((UserDefinedType) element, quoteIdentifiers,
                    qualifyNames, fullSyntax);
            if (statement != null)
            {
                for (int j = 0; j < statement.length; j++)
                {
                    script.addCreateUDDStatements(statement[j]);
                }
            }
        }
        else if(element instanceof AuthorizationIdentifier)
        {
            boolean isUser = !(element instanceof Group);
            String[] statement = sybaseDdlBuilder.createAuthorizationId((AuthorizationIdentifier) element, quoteIdentifiers,
                    qualifyNames, fullSyntax);
            if (statement != null)
            {
                for (int j = 0; j < statement.length; j++)
                {
                    if(isUser)
                    {
                        script.addCreateUserStatements(statement[j]);
                    }
                    else
                    {
                        script.addCreateGroupStatements(statement[j]);
                    }
                }
            }
        }
        else if(element instanceof Privilege)
        {
            String[] statement = sybaseDdlBuilder.grantPrivilege((Privilege) element, quoteIdentifiers, qualifyNames,
                    fullSyntax);
            if (statement != null)
            {
                for (int j = 0; j < statement.length; j++)
                {
                    script.addGrantPrivilegeStatement(statement[j]);
                }
            } 
        }
    }
    
    /**
     * will not generate table contained objects(PK/UC/CC/FK/INDEX/TRIGGER) which not in tableSet.
     * 
     * @param elements
     * @param script
     * @param progressMonitor
     * @param task
     */
    protected void createStatements(SQLObject[] elements, EngineeringOption[] options, SybaseDdlScript script, IProgressMonitor progressMonitor, int task)
    {
        boolean quoteIdentifiers = generateQuotedIdentifiers(options);
        boolean qualifyNames = generateFullyQualifiedNames(options);
        boolean fullSyntax = generateFullSyntax(options);
        
        createStatements(elements, quoteIdentifiers, qualifyNames, fullSyntax, script, progressMonitor, task);
    }
    
    protected void dropStatements(SQLObject[] elements, boolean quoteIdentifiers,
            boolean qualifyNames, SybaseDdlScript script, IProgressMonitor progressMonitor, int task)
    {
        if (this.sybaseDdlBuilder == null)
        {
            this.sybaseDdlBuilder = (SybaseDdlBuilder) createBuilder();
        }
        for (int i = 0; i < elements.length; i++)
        {
            dropStatement(elements[i], quoteIdentifiers, qualifyNames, script, progressMonitor, task);
        }
    }
    
    /**
     * Generate Table, View, Trigger, Check Constraint, Unique Constraint, Foreign Key, Primary Key, Index, Event,
     * Procedure, Function drop script. Subclass can add more database specified object type.
     * 
     * @param elements
     * @param options
     * @param script contains all generated scripts.
     * @param progressMonitor
     * @param task
     */
    protected void dropStatements(SQLObject[] elements, EngineeringOption[] options, SybaseDdlScript script,
            IProgressMonitor progressMonitor, int task)
    {
        boolean quoteIdentifiers = generateQuotedIdentifiers(options);
        boolean qualifyNames = generateFullyQualifiedNames(options);
        
    	dropStatements(elements, quoteIdentifiers, qualifyNames, script, progressMonitor, task);
    }
    
    public void dropStatement(SQLObject element, boolean quoteIdentifiers,
            boolean qualifyNames, SybaseDdlScript script, IProgressMonitor progressMonitor, int task)
    {
        if (element instanceof BaseTable)
        {
            String statement = sybaseDdlBuilder.dropTable((BaseTable) element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addDropTableStatement(statement);
        }
        else if (element instanceof ViewTable)
        {
            String statement = sybaseDdlBuilder.dropView((ViewTable) element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addDropViewStatement(statement);
        }
        else if (element instanceof Trigger)
        {
            String statement = sybaseDdlBuilder.dropTrigger((Trigger) element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addDropTriggerStatement(statement);
        }
        //TODO:handle column check constraint here
        else if (element instanceof CheckConstraint)
        {
            String statement = sybaseDdlBuilder.dropCheckConstraint((CheckConstraint) element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addAlterTableDropConstraintStatement(statement);
        }
        else if (element instanceof PrimaryKey)
        {
            String statement = sybaseDdlBuilder.dropUniqueConstraint((UniqueConstraint) element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addAlterTableDropConstraintStatement(statement);
        }
        else if (element instanceof UniqueConstraint)
        {
            String statement = sybaseDdlBuilder.dropUniqueConstraint((UniqueConstraint) element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addAlterTableDropConstraintStatement(statement);
        }
        else if (element instanceof ForeignKey)
        {
            String statement = sybaseDdlBuilder.dropForeignKey((ForeignKey) element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addAlterTableDropForeignKeyStatement(statement);
        }
        else if (element instanceof Index)
        {
            String statement = sybaseDdlBuilder.dropIndex((Index) element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addDropIndexStatement(statement);
        }
        else if (element instanceof Event)
        {
            String statement = sybaseDdlBuilder.dropEvent((Event) element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addDropEventStatements(statement);
        }
        else if (element instanceof Procedure)
        {
            String statement = sybaseDdlBuilder.dropProcedure((Procedure) element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addDropRoutineStatement(statement);
        }
        else if (element instanceof UserDefinedFunction)
        {
            String statement = sybaseDdlBuilder.dropFunction((UserDefinedFunction) element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addDropRoutineStatement(statement);
        }
        else if(element instanceof UserDefinedType)
        {
            String statement = sybaseDdlBuilder.dropUserDefinedType((UserDefinedType) element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addDropUDDStatements(statement);
        }
        else if(element instanceof AuthorizationIdentifier)
        {
            boolean isUser = element instanceof User;
            String statement = sybaseDdlBuilder.dropAuthorizationId((AuthorizationIdentifier) element, quoteIdentifiers, qualifyNames);
            if (statement != null)
            {
                if(isUser)
                {
                    script.addDropUserStatements(statement);
                }
                else
                {
                    script.addDropGroupStatements(statement);
                }
            }
        }
        else if(element instanceof Privilege)
        {
            String statement = sybaseDdlBuilder.revokePrivilege((Privilege)element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addRevokePrivilegeStatement(statement);
        }
        else if(element instanceof Database)
        {
            String statement = sybaseDdlBuilder.dropDatabase((Database)element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addDropDatabaseStatements(statement);
        }
        else if(element instanceof Catalog)
        {
            String statement = sybaseDdlBuilder.dropCatalog((Catalog)element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addDropDatabaseStatements(statement);
        }
        else if(element instanceof Column)
        {
            Table table = ((Column)element).getTable();
            if (! (table instanceof RoutineResultTable))
            {
                String statement = sybaseDdlBuilder.dropColumn((Column)element, quoteIdentifiers, qualifyNames);
                if (statement != null)
                    script.addAlterTableDropColumnStatements(statement);
            }
        }
    }

    /**
     * generate full syntax or not
     * 
     * @return
     */
    public boolean generateFullSyntax(EngineeringOption[] options)
    {
        return getOptionValueByID(SybaseEngineeringOptionID.GENERATE_FULL_SYNTAX, options);
    }

    /**
     * generate setuser statement or not
     * 
     * @return
     */
    public boolean generateSetUser(EngineeringOption[] options)
    {
        return getOptionValueByID(SybaseEngineeringOptionID.GENERATE_SETUSER, options);
    }
    
    /**
     * generate use database statement or not
     * 
     * @return
     */
    public boolean generateUseDatabase(EngineeringOption[] options)
    {
        return getOptionValueByID(SybaseEngineeringOptionID.GENERATE_USEDATABASE, options);
    }
//    protected boolean generatePrivilegeStatement(EngineeringOption[] options)
//    {
//        return getOptionValueByID(SybaseEngineeringOptionID.GENERATE_PRIVILEGE, options);
//    }
//
//    protected boolean generateExtraCode(EngineeringOption[] options)
//    {
//        return getOptionValueByID(SybaseEngineeringOptionID.GENERATE_EXTRA_CODE, options);
//    }
//    
//
//    protected boolean generateUniqueKey(EngineeringOption[] options)
//    {
//        return getOptionValueByID(SybaseEngineeringOptionID.GENERATE_UNIQUE_CONSTRAINTS, options);
//    }
//
//    protected boolean generateRule(EngineeringOption[] options)
//    {
//        return getOptionValueByID(SybaseEngineeringOptionID.GENERATE_RULES, options);
//    }
//
//    protected boolean generateDefault(EngineeringOption[] options)
//    {
//        return getOptionValueByID(SybaseEngineeringOptionID.GENERATE_DEFAULTS, options);
//    }
//
//    protected boolean generateUserGroup(EngineeringOption[] options)
//    {
//        return getOptionValueByID(SybaseEngineeringOptionID.GENERATE_USERS_GROUPS, options);
//    }
//
//    protected boolean generateSegment(EngineeringOption[] options)
//    {
//        return getOptionValueByID(SybaseEngineeringOptionID.GENERATE_SEGMENTS, options);
//    }
//
//    protected boolean generateDBSpace(EngineeringOption[] options)
//    {
//        return getOptionValueByID(SybaseEngineeringOptionID.GENERATE_DBSPACES, options);
//    }
//
//    protected boolean generateProxyTable(EngineeringOption[] options)
//    {
//        return getOptionValueByID(SybaseEngineeringOptionID.GENERATE_PROXY_TABLES, options);
//    }
//
//    protected boolean generateEvents(EngineeringOption[] options)
//    {
//        return getOptionValueByID(SybaseEngineeringOptionID.GENERATE_EVENTS, options);
//    }
//    
//    protected boolean generateCatalogs(EngineeringOption[] options)
//    {
//        return getOptionValueByID(SybaseEngineeringOptionID.GENERATE_CATALOGS, options);
//    }
//    
    protected boolean getOptionValueByID(String optionID, EngineeringOption[] options){
        boolean ret = false;
        for (int i = 0; i < options.length; i++){
            EngineeringOption option = (EngineeringOption) options[i];
            if (option !=null && option.getId().equals(optionID)) { 
                ret = option.getBoolean();
                break;
            }
        }
        return ret;
    } 
    
    public EngineeringOption getOptionByID(String optionID, EngineeringOption[] options)
    {
        for (int i = 0; i < options.length; i++)
        {
            EngineeringOption option = (EngineeringOption) options[i];
            if (option != null && option.getId().equals(optionID))
            {
                return option;
            }
        }
        
        return null;
    }
    
//    protected Set getAllContainedDisplayableElementSet(SQLObject element)
//    {
//        Set s = new HashSet();
//        s.add(element);
//        s.addAll(ContainmentServiceImpl.INSTANCE.getAllContainedDisplayableElements(element));
//        return s;
//    }

    /**
     * @return additional database option indices
     * @param objectType database object type
     */
    abstract public byte[] getAdditionalOptionIndices(int objectType);
    
    abstract public byte[] getExclusiveAdditionalOptionIndices(int objectType);
    
    abstract public byte[] getGenerationOptionIndices(int objectType);
    
    abstract protected SybaseDdlBuilder createBuilder();

    public byte[] getCommonAdditionalOptionIndices(int objectType)
    {
        if(objectType == SybaseDatabaseObjecType.DATABASE)
        {
            return new byte[]{
                SybaseDdlGenerationOptions.GENERATE_STORED_PROCEDURES,
                SybaseDdlGenerationOptions.GENERATE_TABLES,
                SybaseDdlGenerationOptions.GENERATE_PROXY_TABLES,
                SybaseDdlGenerationOptions.GENERATE_VIEWS,
                SybaseDdlGenerationOptions.GENERATE_USER_DEFINED_TYPES,
                SybaseDdlGenerationOptions.GENERATE_INDICES,
                SybaseDdlGenerationOptions.GENERATE_TRIGGERS,
                SybaseDdlGenerationOptions.GENERATE_USERS_GROUPS,
            };
        }
        else if(objectType == SybaseDatabaseObjecType.TABLE || objectType == SybaseDatabaseObjecType.PROXY_TABLE)
        {
            return new byte[]{
                SybaseDdlGenerationOptions.GENERATE_PRIMARY_KEYS,
                SybaseDdlGenerationOptions.GENERATE_UNIQUE_CONSTRAINTS,
                SybaseDdlGenerationOptions.GENERATE_FOREIGN_KEYS,
                SybaseDdlGenerationOptions.GENERATE_CHECK_CONSTRAINTS,
                SybaseDdlGenerationOptions.GENERATE_INDICES,
//                SybaseDdlGenerationOptions.GENERATE_TRIGGERS,
                SybaseDdlGenerationOptions.GENERATE_USER_DEFINED_TYPES,
            };
        }
        else
        {
            return new byte[]{};
        }
    }
    
    public SybaseDdlBuilder getSybaseDdlBuilder()
    {
        if(sybaseDdlBuilder == null)
        {
            createBuilder();
        }
        return sybaseDdlBuilder;
    }
    
    public void setParameter(Object obj) {
        _parameter = obj;
    }

    public Object getParameter() {
        return _parameter;
    }
}
