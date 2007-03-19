/**
 * Created on 2006-9-5
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.enablement.sybase.ddl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author  linsong
 */
public class SybaseDatabaseObjecType
{

    private final int    value;
    private final String name;
    private final String label;

    private SybaseDatabaseObjecType(int value, String name, String label)
    {
        this.value = value;
        this.name = name;
        this.label = label;
    }

    public static final int                     DATABASE                   = 0;
    public static final int                     TABLE                      = 1;
    public static final int                     PROCEDURE                  = 2;
    public static final int                     USER_DEFINED_FUNCTION      = 3;
    public static final int                     VIEW                       = 4;
    public static final int                     USER_DEFINED_DATATYPE      = 5;
    public static final int                     EVENT                      = 6;
    public static final int                     CHECK_CONSTRAINT           = 7;
    public static final int                     UNIQUE_CONSTRAINT          = 8;
    public static final int                     PRIMARY_KEY                = 9;
    public static final int                     FOREIGN_KEY                = 10;
    public static final int                     INDEX                      = 11;
    public static final int                     TRIGGER                    = 12;
    public static final int                     DEFAULT                    = 13;
    public static final int                     RULE                       = 14;

    static ResourceBundle                       dmpresrce                  = ResourceBundle
                                                                                   .getBundle("org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlGeneration"); //$NON-NLS-1$

    public static final SybaseDatabaseObjecType    DATABASE_TYPE              = new SybaseDatabaseObjecType(DATABASE,
                                                                                   "Database", dmpresrce //$NON-NLS-1$
                                                                                           .getString("DATABASE_LABEL")); //$NON-NLS-1$
    public static final SybaseDatabaseObjecType    TABLE_TYPE                 = new SybaseDatabaseObjecType(TABLE, "Table", //$NON-NLS-1$
                                                                                   dmpresrce.getString("TABLE_LABEL")); //$NON-NLS-1$
    public static final SybaseDatabaseObjecType    PROCEDURE_TYPE             = new SybaseDatabaseObjecType(
                                                                                   PROCEDURE,
                                                                                   "Procedure", //$NON-NLS-1$
                                                                                   dmpresrce
                                                                                           .getString("PROCEDURE_LABEL")); //$NON-NLS-1$
    public static final SybaseDatabaseObjecType    USER_DEFINED_FUNCTION_TYPE = new SybaseDatabaseObjecType(
                                                                                   USER_DEFINED_FUNCTION,
                                                                                   "User Defined function", //$NON-NLS-1$
                                                                                   dmpresrce
                                                                                           .getString("USER_DEFINED_FUNCTION_LABEL")); //$NON-NLS-1$
    public static final SybaseDatabaseObjecType    VIEW_TYPE                  = new SybaseDatabaseObjecType(VIEW, "View", //$NON-NLS-1$
                                                                                   dmpresrce.getString("VIEW_LABEL")); //$NON-NLS-1$
    public static final SybaseDatabaseObjecType    USER_DEFINED_DATATYPE_TYPE = new SybaseDatabaseObjecType(
                                                                                   USER_DEFINED_DATATYPE,
                                                                                   "User Defined Datatype", //$NON-NLS-1$
                                                                                   dmpresrce
                                                                                           .getString("USER_DEFINED_DATATYPE_LABEL")); //$NON-NLS-1$
    public static final SybaseDatabaseObjecType    EVENT_TYPE                 = new SybaseDatabaseObjecType(EVENT, "Event", //$NON-NLS-1$
                                                                                   dmpresrce.getString("EVENT_LABEL")); //$NON-NLS-1$
    public static final SybaseDatabaseObjecType    CHECK_CONSTRAINT_TYPE      = new SybaseDatabaseObjecType(
                                                                                   CHECK_CONSTRAINT,
                                                                                   "Check Constraint", //$NON-NLS-1$
                                                                                   dmpresrce
                                                                                           .getString("CHECK_CONSTRAINT_LABEL")); //$NON-NLS-1$
    public static final SybaseDatabaseObjecType    UNIQUE_CONSTRAINT_TYPE     = new SybaseDatabaseObjecType(
                                                                                   UNIQUE_CONSTRAINT,
                                                                                   "Unique Constraint", //$NON-NLS-1$
                                                                                   dmpresrce
                                                                                           .getString("UNIQUE_CONSTRAINT_LABEL")); //$NON-NLS-1$
    public static final SybaseDatabaseObjecType    PRIMARY_KEY_TYPE           = new SybaseDatabaseObjecType(
                                                                                   PRIMARY_KEY,
                                                                                   "Primary Key", //$NON-NLS-1$
                                                                                   dmpresrce
                                                                                           .getString("PRIMARY_KEY_LABEL")); //$NON-NLS-1$
    public static final SybaseDatabaseObjecType    FOREIGN_KEY_TYPE           = new SybaseDatabaseObjecType(
                                                                                   FOREIGN_KEY,
                                                                                   "Foreign Key", //$NON-NLS-1$
                                                                                   dmpresrce
                                                                                           .getString("FOREIGN_KEY_LABEL")); //$NON-NLS-1$
    public static final SybaseDatabaseObjecType    INDEX_TYPE                 = new SybaseDatabaseObjecType(INDEX, "Index", //$NON-NLS-1$
                                                                                   dmpresrce.getString("INDEX_LABEL")); //$NON-NLS-1$
    public static final SybaseDatabaseObjecType    TRIGGER_TYPE               = new SybaseDatabaseObjecType(TRIGGER,
                                                                                   "Trigger", dmpresrce //$NON-NLS-1$
                                                                                           .getString("TRIGGER_LABEL")); //$NON-NLS-1$
    public static final SybaseDatabaseObjecType    DEFAULT_TYPE               = new SybaseDatabaseObjecType(DEFAULT,
                                                                                   "Default", dmpresrce //$NON-NLS-1$
                                                                                           .getString("DEFAULT_LABEL")); //$NON-NLS-1$
    public static final SybaseDatabaseObjecType    RULE_TYPE                  = new SybaseDatabaseObjecType(RULE, "Rule", //$NON-NLS-1$
                                                                                   dmpresrce.getString("RULE_LABEL")); //$NON-NLS-1$

    /**
     * @uml.property  name="dATABASE_OBJECTYPYE_ARRAY"
     * @uml.associationEnd  multiplicity="(0 -1)"
     */
    private static final SybaseDatabaseObjecType[] DATABASE_OBJECTYPYE_ARRAY  = new SybaseDatabaseObjecType[]
                                                                           {
        DATABASE_TYPE, TABLE_TYPE, PROCEDURE_TYPE, USER_DEFINED_FUNCTION_TYPE, VIEW_TYPE, USER_DEFINED_DATATYPE_TYPE,
        EVENT_TYPE, CHECK_CONSTRAINT_TYPE, UNIQUE_CONSTRAINT_TYPE, PRIMARY_KEY_TYPE, FOREIGN_KEY_TYPE, INDEX_TYPE,
        TRIGGER_TYPE, DEFAULT_TYPE, RULE_TYPE,
                                                                           };
    
    public static final List DATABASE_OBJECTTYPE_LIST = Collections.unmodifiableList(Arrays.asList(DATABASE_OBJECTYPYE_ARRAY));

    /**
     * @return  Returns the value.
     * @uml.property  name="value"
     */
    public final int getValue()
    {
        return value;
    }

    /**
     * @return  Returns the name.
     * @uml.property  name="name"
     */
    public final String getName()
    {
        return name;
    }

    /**
     * @return  Returns the label.
     * @uml.property  name="label"
     */
    public final String getLabel()
    {
        return label;
    }
}
