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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategory;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategoryID;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe.GenericDdlGenerationOptions;

/**
 * SybaseDdlGenerationOptions maintains a set of global ddl options for preference page, and provide specified cloned
 * options set APIs for ddl or delta ddl genration.
 * 
 * @author linsong
 * 
 */
public class SybaseDdlGenerationOptions extends GenericDdlGenerationOptions
{

    // generate object types
    // for Sybase
    public final static byte           GENERATE_STORED_PROCEDURES    = 8;
    public final static byte           GENERATE_USER_FUNCTION        = 9;
    public final static byte           GENERATE_USER_DEFINED_TYPES   = 10;
    public final static byte           GENERATE_PRIMARY_KEYS         = 11;
    public final static byte           GENERATE_UNIQUE_CONSTRAINTS   = 12;
    public final static byte           GENERATE_FOREIGN_KEYS         = 13;
    public final static byte           GENERATE_CHECK_CONSTRAINTS    = 14;
    public final static byte           GENERATE_USERS_GROUPS         = 15;
    public final static byte           GENERATE_PROXY_TABLES         = 16;

    public final static byte           GENERATE_DATABASE             = 18;
    public static final int            GENERATE_CATALOG              = 19;

    // for ASE
    public final static byte           GENERATE_RULES                = 20;
    public final static byte           GENERATE_DEFAULTS             = 21;
    public final static byte           GENERATE_SEGMENTS             = 22;
    public final static byte           GENERATE_EVENTS               = 23;

    // for ASA/IQ
    public final static byte           GENERATE_DBSPACE              = 24;
    public final static byte           GENERATE_ROUTINE              = 28;
    public final static byte           GENERATE_JOIN_INDEX           = 29;

    // extra code includes extra properties setting etc.
    public final static byte           GENERATE_EXTRACODE            = 25;

    // extra generation options
    public final static byte           GENERATE_FULL_SYNTAX          = 26;
    public final static byte           GENERATE_COMMENTS             = 27;
    public final static byte           GENERATE_PRIVILEGE            = 17;
    public final static byte           GENERATE_SETUSER              = 30;
    
    public final static byte           GENERATE_USEDATABASE          = 31;

    // global options
    private static EngineeringOption[] SYBASE_DDL_GENERATION_OPTIONS = new EngineeringOption[0];
    static
    {
        SYBASE_DDL_GENERATION_OPTIONS = createDDLGenerationOptions();
    }

    /**
     * 
     * @return all global ddl generation options
     */
    public static EngineeringOption[] getGlobalSybaseDDLGenerationOptions()
    {
        return SYBASE_DDL_GENERATION_OPTIONS;
    }

    /**
     * add generate UDD, default, rule, user & group, proxy table, segment, check constraint, unique constraint, primary
     * key, foreign key and extra code options support
     * 
     * @param categories
     * @return
     */
    private static EngineeringOption[] createDDLGenerationOptions()
    {
        EngineeringOptionCategory general_options = null;
        EngineeringOptionCategory additional_element = null;
        EngineeringOptionCategory[] categories = GenericDdlGenerationOptions.createDDLGenerationOptionCategories();
        for (int i = 0; i < categories.length; i++)
        {
            if (categories[i].getId().equals(EngineeringOptionCategoryID.GENERATE_OPTIONS))
            {
                general_options = categories[i];
            }
            else if (categories[i].getId().equals(EngineeringOptionCategoryID.GENERATE_ELEMENTS))
            {
                additional_element = categories[i];
            }
        }

        EngineeringOption[] genericOptions = GenericDdlGenerationOptions.createDDLGenerationOptions(categories);
        ResourceBundle sybaseResource = ResourceBundle
                .getBundle("org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlGeneration"); //$NON-NLS-1$
        ResourceBundle genericResource = ResourceBundle
                .getBundle("org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe.GenericDdlGeneration"); //$NON-NLS-1$
        List optionList = new ArrayList();
        optionList.addAll(Arrays.asList(genericOptions));
        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_STOREDPROCEDURES,
                        genericResource.getString("GENERATE_STOREDPROCEDURE"), genericResource.getString("GENERATE_STOREDPROCEDURE_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_FUNCTIONS,
                        sybaseResource.getString("GENERATE_FUNCTION"), sybaseResource.getString("GENERATE_FUNCTION_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_USER_DEFINED_TYPE,
                        sybaseResource.getString("GENERATE_USER_DEFINED_TYPES"), sybaseResource.getString("GENERATE_USER_DEFINED_TYPES_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_PK_CONSTRAINTS,
                        genericResource.getString("GENERATE_PK_CONSTRAINTS"), genericResource.getString("GENERATE_PK_CONSTRAINTS_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_UNIQUE_CONSTRAINTS,
                        sybaseResource.getString("GENERATE_UNIQUE_CONSTRAINTS"), sybaseResource.getString("GENERATE_UNIQUE_CONSTRAINTS_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_FK_CONSTRAINTS,
                        sybaseResource.getString("GENERATE_FOREIGN_KEYS"), sybaseResource.getString("GENERATE_FOREIGN_KEYS_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_CK_CONSTRAINTS,
                        sybaseResource.getString("GENERATE_CHECK_CONSTRAINTS"), sybaseResource.getString("GENERATE_CHECK_CONSTRAINTS_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_USERS_GROUPS,
                        sybaseResource.getString("GENERATE_USERS_GROUPS"), sybaseResource.getString("GENERATE_USERS_GROUPS_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_PROXY_TABLES,
                        sybaseResource.getString("GENERATE_PROXY_TABLES"), sybaseResource.getString("GENERATE_PROXY_TABLES_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_PRIVILEGE,
                        sybaseResource.getString("GENERATE_PRIVILEGE"), sybaseResource.getString("GENERATE_PRIVILEGE_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_DATABASE,
                        sybaseResource.getString("GENERATE_DATABASE"), sybaseResource.getString("GENERATE_DATABASE_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_CATALOGS,
                        sybaseResource.getString("GENERATE_CATALOGS"), sybaseResource.getString("GENERATE_CATALOGS_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_RULES,
                        sybaseResource.getString("GENERATE_RULES"), sybaseResource.getString("GENERATE_RULES_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_DEFAULTS,
                        sybaseResource.getString("GENERATE_DEFAULTS"), sybaseResource.getString("GENERATE_DEFAULTS_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_SEGMENTS,
                        sybaseResource.getString("GENERATE_SEGMENTS"), sybaseResource.getString("GENERATE_SEGMENTS_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_EVENTS,
                        sybaseResource.getString("GENERATE_EVENTS"), sybaseResource.getString("GENERATE_EVENTS_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_DBSPACES,
                        sybaseResource.getString("GENERATE_DBSPACES"), sybaseResource.getString("GENERATE_DBSPACES_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
        
        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_EXTRA_CODE,
                        sybaseResource.getString("GENERATE_EXTRA_CODE"), sybaseResource.getString("GENERATE_EXTRA_CODE_DES"), false, general_options)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_FULL_SYNTAX,
                        sybaseResource.getString("GENERATE_FULL_SYNTAX"), sybaseResource.getString("GENERATE_FULL_SYNTAX_DES"), false, general_options)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_COMMENTS,
                        sybaseResource.getString("GENERATE_COMMENTS"), sybaseResource.getString("GENERATE_COMMENTS_DES"), true, general_options)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_ROUTINES,
                        sybaseResource.getString("GENERATE_ROUTINE"), sybaseResource.getString("GENERATE_ROUTINE_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_JOIN_INDICES,
                        sybaseResource.getString("GENERATE_JOIN_INDICES"), sybaseResource.getString("GENERATE_JOIN_INDICES_DES"), true, additional_element)); //$NON-NLS-1$ //$NON-NLS-2$

        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_SETUSER,
                        sybaseResource.getString("GENERATE_SETUSER"), sybaseResource.getString("GENERATE_SETUSER_DES"), false, general_options)); //$NON-NLS-1$ //$NON-NLS-2$
        
        optionList
                .add(new EngineeringOption(
                        SybaseEngineeringOptionID.GENERATE_USEDATABASE,
                        sybaseResource.getString("GENERATE_USEDATABASE"), sybaseResource.getString("GENERATE_USEDATABASE_DES"), true, general_options)); //$NON-NLS-1$ //$NON-NLS-2$
        
        EngineeringOption[] options = new EngineeringOption[optionList.size()];
        optionList.toArray(options);
        return options;
    }

}
