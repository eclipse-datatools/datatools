/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.definition;

import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;



public class EngineeringOptionID {
	public final static String GENERATE_FULLY_QUALIFIED_NAME = "GENERATE_FULLY_QUALIFIED_NAME";  //$NON-NLS-1$
	public final static String GENERATE_QUOTED_IDENTIFIER= "GENERATE_QUOTED_IDENTIFIER"; //$NON-NLS-1$ 
	public final static String GENERATE_DROP_STATEMENTS ="GENERATE_DROP_STATEMENTS"; //$NON-NLS-1$ 
	public final static String GENERATE_CREATE_STATEMENTS = "GENERATE_CREATE_STATEMENTS"; //$NON-NLS-1$ 
	public final static String GENERATE_COMMENTS = "GENERATE_COMMENTS"; //$NON-NLS-1$
	public final static String GENERATE_DATABASE  = "GENERATE_DATABASE"; //$NON-NLS-1$
	public final static String GENERATE_SCHEMAS = "GENERATE_SCHEMAS"; //$NON-NLS-1$
	public final static String GENERATE_TABLES = "GENERATE_TABLES"; //$NON-NLS-1$ 
	public final static String GENERATE_INDICES = "GENERATE_INDICES"; //$NON-NLS-1$
	public final static String GENERATE_STOREDPROCEDURES = "GENERATE_STOREDPROCEDURES"; //$NON-NLS-1$
	public final static String GENERATE_FUNCTIONS = "GENERATE_FUNCTIONS"; //$NON-NLS-1$
	public final static String GENERATE_VIEWS = "GENERATE_VIEWS"; //$NON-NLS-1$
	public final static String GENERATE_TRIGGERS = "GENERATE_TRIGGERS"; //$NON-NLS-1$
	public final static String GENERATE_SEQUENCES = "GENERATE_SEQUENCES"; //$NON-NLS-1$
	public final static String GENERATE_USER_DEFINED_TYPE = "GENERATE_USER_DEFINED_TYPE"; //$NON-NLS-1$
	public final static String GENERATE_PK_CONSTRAINTS  = "GENERATE_PK_CONSTRAINTS"; //$NON-NLS-1$
	public final static String GENERATE_FK_CONSTRAINTS  = "GENERATE_FK_CONSTRAINTS"; //$NON-NLS-1$
	public final static String GENERATE_CK_CONSTRAINTS  = "GENERATE_CK_CONSTRAINTS"; //$NON-NLS-1$
	public final static String GENERATE_ASSERTIONS  = "GENERATE_ASSERTIONS"; //$NON-NLS-1$
		
    public static final int DATABASE = 1;
    public static final int SCHEMA = 2;
    public static final int TABLE = 4;
    public static final int PROCEDURE = 8;
    public static final int USER_DEFINED_FUNCTION = 16;
    public static final int VIEW = 32;
    public static final int TRIGGER = 64;
    public static final int INDEX = 128;
    public static final int SEQUENCE = 256;
    public static final int USER_DEFINED_TYPE = 512;
    public static final int UNIQUE_CONSTRAINT = 1024;
    public static final int FOREIGN_KEY = 2048;
    public static final int CHECK_CONSTRAINT = 4096; 
    
    
    public static boolean getOptionValue(String optionName, EngineeringOption[] options){
        boolean ret = false;
        for (int i = 0; i < options.length; i++){
            EngineeringOption option = (EngineeringOption) options[i];
            if (option != null && option.getOptionName().equals(optionName)) { 
            	ret = option.getBoolean();
                break;
            }
        }
        return ret;
    }
    
    public static boolean getOptionValueByID(String optionID, EngineeringOption[] options){
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

    public static boolean setOptionValueByID(String optionID, EngineeringOption[] options, boolean value){
        boolean ret = false;
        for (int i = 0; i < options.length; i++){
            EngineeringOption option = (EngineeringOption) options[i];
            if (option !=null && option.getId().equals(optionID)) { 
            	option.setBoolean(value);
                break;
            }
        }
        return ret;
    }

    
}
