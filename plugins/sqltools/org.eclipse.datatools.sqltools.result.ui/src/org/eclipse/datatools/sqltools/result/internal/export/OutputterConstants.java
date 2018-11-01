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
package org.eclipse.datatools.sqltools.result.internal.export;

import org.eclipse.datatools.sqltools.result.internal.ui.Messages;

/**
 * 
 * @author Dafan Yang
 */
public interface OutputterConstants
{
    public static String EXTENSION_POINT_NAME        = "resultSetOutputter";

    public static String OUTPUTTER_ID                = "typeId";
    public static String OUTPUTTER_NAME              = "typeName";
    public static String OUTPUTTER_FILE_EXT          = "fileExtension";
    public static String OUTPUTTER_SUPPORT_DELIMITER = "supportDelimiter";
    public static String OUTPUTTER_CLASS             = "class";
    public static String OUTPUTTER_SUPPORT_XML       = "supportXMLResult";
    public static String OUTPUTTER_EXTENSION_FILTER  = "extensionFilter";
    
    //Four types of built-in outputters
    public static String PLAIN_TXT_OUTPUTTER_ID      = "org.eclipse.datatools.sqltools.result.textOutputter";
    public static String XML_OUTPUTTER_ID            = "org.eclipse.datatools.sqltools.result.xmloutputter";
    public static String HTML_OUTPUTTER_ID           = "org.eclipse.datatools.sqltools.result.htmloutputter";
    public static String CSV_OUTPUTTER_ID            = "org.eclipse.datatools.sqltools.result.csvoutputter";
    
  /** five type of delimiters * */
  public static String         COLUMN_ALIGNED                 = Messages.ExportFormatPage_columndelimiter_items_columnaligned; //$NON-NLS-1$
  public static String         COMMA_SEPARATED                = Messages.ExportFormatPage_columndelimiter_items_commaseparated; //$NON-NLS-1$
  public static String         TAB_DELIMITED                  = Messages.ExportFormatPage_columndelimiter_items_tabdelimited;  //$NON-NLS-1$
  public static String         USER_DEFINED                   = Messages.ExportFormatPage_columndelimiter_items_userdefined;   //$NON-NLS-1$
  public static String         CSV_SEPARATED                  = Messages.ExportFormatPage_columndelimiter_items_csvseparated;  //$NON-NLS-1$

  // the default delimiter for user-defined delimiter, it's ';'
  public static String         DEFAULT_USER_DEFINED_DEMILITER = Messages.ExportFormatPage_columndelimiter_userdefineddelimiter; //$NON-NLS-1$

//   the max number of characters of user defined delimter
  public static int            USER_DEFINED_DELIMITER_COUNT   = 5;

  // delimiter strings for display
  public static String         DELIMITER_COLUMN               = "<column>";                                                    //$NON-NLS-1$
  public static String         DELIMITER_COMMA                = ",";                                                           //$NON-NLS-1$
  public static String         DELIMITER_TAB                  = "<tab>";                                                       //$NON-NLS-1$

  static final public String[] OUTPUT_FORMATS                 = new String[]
                                                              {
      COLUMN_ALIGNED, COMMA_SEPARATED, TAB_DELIMITED, USER_DEFINED
                                                              };

  public static final String   RESOURCE_TYPE                  = "resource";                                                    //$NON-NLS-1$
}
