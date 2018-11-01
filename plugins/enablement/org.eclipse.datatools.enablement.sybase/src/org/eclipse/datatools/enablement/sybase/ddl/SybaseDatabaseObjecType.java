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
package org.eclipse.datatools.enablement.sybase.ddl;

/**
 * @author linsong
 */
public class SybaseDatabaseObjecType
{

    // private final int value;
    // private final String name;
    // private final String label;
    //
    // private SybaseDatabaseObjecType(int value, String name, String label)
    // {
    // this.value = value;
    // this.name = name;
    // this.label = label;
    // }

    public static final int DATABASE              = SybaseDdlGenerationOptions.GENERATE_DATABASE;
    public static final int TABLE                 = SybaseDdlGenerationOptions.GENERATE_TABLES;
    public static final int USER_DEFINED_FUNCTION = SybaseDdlGenerationOptions.GENERATE_USER_FUNCTION;
    public static final int ROUTINE               = SybaseDdlGenerationOptions.GENERATE_ROUTINE;
    public static final int PROXY_TABLE           = SybaseDdlGenerationOptions.GENERATE_PROXY_TABLES;
    public static final int VIEW                  = SybaseDdlGenerationOptions.GENERATE_VIEWS;
    public static final int USER_DEFINED_DATATYPE = SybaseDdlGenerationOptions.GENERATE_USER_DEFINED_TYPES;
    public static final int EVENT                 = SybaseDdlGenerationOptions.GENERATE_EVENTS;
    public static final int CHECK_CONSTRAINT      = SybaseDdlGenerationOptions.GENERATE_CHECK_CONSTRAINTS;
    public static final int UNIQUE_CONSTRAINT     = SybaseDdlGenerationOptions.GENERATE_UNIQUE_CONSTRAINTS;
    public static final int PRIMARY_KEY           = SybaseDdlGenerationOptions.GENERATE_PRIMARY_KEYS;
    public static final int FOREIGN_KEY           = SybaseDdlGenerationOptions.GENERATE_FOREIGN_KEYS;
    public static final int INDEX                 = SybaseDdlGenerationOptions.GENERATE_INDICES;
    public static final int TRIGGER               = SybaseDdlGenerationOptions.GENERATE_TRIGGERS;
    public static final int DEFAULT               = SybaseDdlGenerationOptions.GENERATE_DEFAULTS;
    public static final int RULE                  = SybaseDdlGenerationOptions.GENERATE_RULES;
    public static final int JOIN_INDEX            = SybaseDdlGenerationOptions.GENERATE_JOIN_INDEX;
    public static final int DBSPACE               = SybaseDdlGenerationOptions.GENERATE_DBSPACE;
    public static final int PROCEDURE             = SybaseDdlGenerationOptions.GENERATE_STORED_PROCEDURES;
    public static final int SEGMENT               = SybaseDdlGenerationOptions.GENERATE_SEGMENTS;
    public static final int USER_GROUP            = SybaseDdlGenerationOptions.GENERATE_USERS_GROUPS;

    // static ResourceBundle dmpresrce = ResourceBundle
    // .getBundle("org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlGeneration"); //$NON-NLS-1$
    //
    // public static final SybaseDatabaseObjecType DATABASE_TYPE = new SybaseDatabaseObjecType(DATABASE,
    // "Database", dmpresrce
    // .getString("DATABASE_LABEL"));
    // public static final SybaseDatabaseObjecType TABLE_TYPE = new SybaseDatabaseObjecType(TABLE, "Table",
    // dmpresrce.getString("TABLE_LABEL"));
    // public static final SybaseDatabaseObjecType PROCEDURE_TYPE = new SybaseDatabaseObjecType(
    // PROCEDURE,
    // "Procedure",
    // dmpresrce
    // .getString("PROCEDURE_LABEL"));
    // public static final SybaseDatabaseObjecType USER_DEFINED_FUNCTION_TYPE = new SybaseDatabaseObjecType(
    // USER_DEFINED_FUNCTION,
    // "User Defined function",
    // dmpresrce
    // .getString("USER_DEFINED_FUNCTION_LABEL"));
    // public static final SybaseDatabaseObjecType VIEW_TYPE = new SybaseDatabaseObjecType(VIEW, "View",
    // dmpresrce.getString("VIEW_LABEL"));
    // public static final SybaseDatabaseObjecType USER_DEFINED_DATATYPE_TYPE = new SybaseDatabaseObjecType(
    // USER_DEFINED_DATATYPE,
    // "User Defined Datatype",
    // dmpresrce
    // .getString("USER_DEFINED_DATATYPE_LABEL"));
    // public static final SybaseDatabaseObjecType EVENT_TYPE = new SybaseDatabaseObjecType(EVENT, "Event",
    // dmpresrce.getString("EVENT_LABEL"));
    // public static final SybaseDatabaseObjecType CHECK_CONSTRAINT_TYPE = new SybaseDatabaseObjecType(
    // CHECK_CONSTRAINT,
    // "Check Constraint",
    // dmpresrce
    // .getString("CHECK_CONSTRAINT_LABEL"));
    // public static final SybaseDatabaseObjecType UNIQUE_CONSTRAINT_TYPE = new SybaseDatabaseObjecType(
    // UNIQUE_CONSTRAINT,
    // "Unique Constraint",
    // dmpresrce
    // .getString("UNIQUE_CONSTRAINT_LABEL"));
    // public static final SybaseDatabaseObjecType PRIMARY_KEY_TYPE = new SybaseDatabaseObjecType(
    // PRIMARY_KEY,
    // "Primary Key",
    // dmpresrce
    // .getString("PRIMARY_KEY_LABEL"));
    // public static final SybaseDatabaseObjecType FOREIGN_KEY_TYPE = new SybaseDatabaseObjecType(
    // FOREIGN_KEY,
    // "Foreign Key",
    // dmpresrce
    // .getString("FOREIGN_KEY_LABEL"));
    // public static final SybaseDatabaseObjecType INDEX_TYPE = new SybaseDatabaseObjecType(INDEX, "Index",
    // dmpresrce.getString("INDEX_LABEL"));
    // public static final SybaseDatabaseObjecType TRIGGER_TYPE = new SybaseDatabaseObjecType(TRIGGER,
    // "Trigger", dmpresrce
    // .getString("TRIGGER_LABEL"));
    // public static final SybaseDatabaseObjecType DEFAULT_TYPE = new SybaseDatabaseObjecType(DEFAULT,
    // "Default", dmpresrce
    // .getString("DEFAULT_LABEL"));
    // public static final SybaseDatabaseObjecType RULE_TYPE = new SybaseDatabaseObjecType(RULE, "Rule",
    // dmpresrce.getString("RULE_LABEL"));
    //
    // /**
    // * @uml.property name="dATABASE_OBJECTYPYE_ARRAY"
    // * @uml.associationEnd multiplicity="(0 -1)"
    // */
    // private static final SybaseDatabaseObjecType[] DATABASE_OBJECTYPYE_ARRAY = new SybaseDatabaseObjecType[]
    // {
    // DATABASE_TYPE, TABLE_TYPE, PROCEDURE_TYPE, USER_DEFINED_FUNCTION_TYPE, VIEW_TYPE, USER_DEFINED_DATATYPE_TYPE,
    // EVENT_TYPE, CHECK_CONSTRAINT_TYPE, UNIQUE_CONSTRAINT_TYPE, PRIMARY_KEY_TYPE, FOREIGN_KEY_TYPE, INDEX_TYPE,
    // TRIGGER_TYPE, DEFAULT_TYPE, RULE_TYPE,
    // };
    //    
    // public static final List DATABASE_OBJECTTYPE_LIST =
    // Collections.unmodifiableList(Arrays.asList(DATABASE_OBJECTYPYE_ARRAY));
    //
    // /**
    // * @return Returns the value.
    // * @uml.property name="value"
    // */
    // public final int getValue()
    // {
    // return value;
    // }
    //
    // /**
    // * @return Returns the name.
    // * @uml.property name="name"
    // */
    // public final String getName()
    // {
    // return name;
    // }
    //
    // /**
    // * @return Returns the label.
    // * @uml.property name="label"
    // */
    // public final String getLabel()
    // {
    // return label;
    // }
}
