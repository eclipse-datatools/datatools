/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.export;

import org.eclipse.datatools.sqltools.result.internal.utils.Messages;

/**
 * The constants for save/export result set(s) function
 * 
 * @author Dafan Yang
 */
public interface IResultConstants
{
    /** export properties for resultset * */
    // Delimiter type
    public static String         DELIMITER                      = "delimiter";                                                   //$NON-NLS-1$
    // If the delimiter type is user-defined deliemiter, use this to store the delimiter
    public static String         USERDEFINED_DELIMITER          = "userdefined_delimiter";                                       //$NON-NLS-1$
    // Encoding
    public static String         ENCODING                       = "encoding";                                                    //$NON-NLS-1$

//    /** five type of delimiters * */
//    public static String         COLUMN_ALIGNED                 = Messages.ExportFormatPage_columndelimiter_items_columnaligned; //$NON-NLS-1$
//    public static String         COMMA_SEPARATED                = Messages.ExportFormatPage_columndelimiter_items_commaseparated; //$NON-NLS-1$
//    public static String         TAB_DELIMITED                  = Messages.ExportFormatPage_columndelimiter_items_tabdelimited;  //$NON-NLS-1$
//    public static String         USER_DEFINED                   = Messages.ExportFormatPage_columndelimiter_items_userdefined;   //$NON-NLS-1$
//    public static String         CSV_SEPARATED                  = Messages.ExportFormatPage_columndelimiter_items_csvseparated;  //$NON-NLS-1$
//
//    // the default delimiter for user-defined delimiter, it's ';'
//    public static String         DEFAULT_USER_DEFINED_DEMILITER = Messages.ExportFormatPage_columndelimiter_userdefineddelimiter; //$NON-NLS-1$

    // the max number of characters of user defined delimter
//    public static int            USER_DEFINED_DELIMITER_COUNT   = 5;

    // delimiter strings for display
//    public static String         DELIMITER_COLUMN               = "<column>";                                                    //$NON-NLS-1$
//    public static String         DELIMITER_COMMA                = ",";                                                           //$NON-NLS-1$
//    public static String         DELIMITER_TAB                  = "<tab>";                                                       //$NON-NLS-1$

//    static final public String[] OUTPUT_FORMATS                 = new String[]
//                                                                {
//        COLUMN_ALIGNED, COMMA_SEPARATED, TAB_DELIMITED, USER_DEFINED
//                                                                };

//    public static final String   RESOURCE_TYPE                  = "resource";                                                    //$NON-NLS-1$
}
