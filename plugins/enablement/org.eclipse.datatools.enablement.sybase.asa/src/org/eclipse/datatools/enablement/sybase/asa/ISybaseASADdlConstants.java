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
package org.eclipse.datatools.enablement.sybase.asa;

import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlConstants;

public interface ISybaseASADdlConstants extends ISybaseDdlConstants
{
    public final static String   VIRTUAL                  = "VIRTUAL";                      //$NON-NLS-1$
    public final static String   RESULT                   = "RESULT";                       //$NON-NLS-1$
    public final static String   ONEXCEPTIONRESUME        = "ON EXCEPTION RESUME";          //$NON-NLS-1$
    public final static String   ORDER                    = "ORDER";                        //$NON-NLS-1$
    public static final String   REMOTE                   = "REMOTE";                       //$NON-NLS-1$
    public static final String   WHERE                    = "WHERE";                        //$NON-NLS-1$
    public static final String   EVERY                    = "EVERY";                        //$NON-NLS-1$
    public static final String   START                    = "START";                        //$NON-NLS-1$
    public static final String   TIME                     = "TIME";                         //$NON-NLS-1$
    public static final String   DATE                     = "DATE";                         //$NON-NLS-1$
    public static final String   SCHEDULE                 = "SCHEDULE";                     //$NON-NLS-1$
    public static final String   ENABLE                   = "ENABLE";                       //$NON-NLS-1$
    public static final String   DISABLE                  = "DISABLE";                      //$NON-NLS-1$
    public static final String   HANDLER                  = "HANDLER";                      //$NON-NLS-1$
    public static final String   BETWEEN                  = "BETWEEN";                      //$NON-NLS-1$
    public static final String   AND                      = "AND";                          //$NON-NLS-1$
    public final static String[] DAYS_OF_WEEK             =
                                                          {
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
                                                          };

    public final static int      SYNTAX_TYPE_TSQL         = 0;
    public final static int      SYNTAX_TYPE_WATCOM1      = 1;
    public final static int      SYNTAX_TYPE_WATCOM2      = 2;
    public final static int      SYNTAX_TYPE_SQLJ         = 3;
    public final static int      SYNTAX_TYPE_LIBRARY_CALL = 4;
    public final static int      SYNTAX_TYPE_PROXY        = 5;

    public final static String   MIRROR                   = "MIRROR";                       //$NON-NLS-1$
    public final static String   CASE                     = "CASE";                         //$NON-NLS-1$
    public final static String   RESPECT                  = "RESPECT";                      //$NON-NLS-1$
    public final static String   IGNORE                   = "IGNORE";                       //$NON-NLS-1$
    public final static String   PAGE                     = "PAGE";                         //$NON-NLS-1$
    public final static String   SIZE                     = "SIZE";                         //$NON-NLS-1$
    public final static String   COLLATION                = "COLLATION";                    //$NON-NLS-1$
    public final static String   ENCRYPTED                = "ENCRYPTED";                    //$NON-NLS-1$
    public final static String   BLANK                    = "BLANK";                        //$NON-NLS-1$
    public final static String   PADDING                  = "PADDING";                      //$NON-NLS-1$
    public final static String   ASE                      = "ASE";                          //$NON-NLS-1$
    public final static String   COMPATIBLE               = "COMPATIBLE";                   //$NON-NLS-1$
    public final static String   JDK                      = "JDK";                          //$NON-NLS-1$
    public final static String   JCONNECT                 = "JCONNECT";                     //$NON-NLS-1$
    public final static String   PASSWORD                 = "PASSWORD";                     //$NON-NLS-1$
    public final static String   CHECKSUM                 = "CHECKSUM";                     //$NON-NLS-1$
    public final static String   KEY                      = "KEY";                          //$NON-NLS-1$
    public final static String   OFF                      = "OFF";                          //$NON-NLS-1$
//    public final static String   DOMAIN                   = "DOMAIN";                       //$NON-NLS-1$

    public final static String   COMPUTE                  = "COMPUTE";                      //$NON-NLS-1$
    public final static String   MODIFY                   = "MODIFY";                       //$NON-NLS-1$

    public final static String   DECLARELOCALTEMP         = "DECLARE LOCAL TEMPORARY TABLE"; //$NON-NLS-1$ 
    public final static String   NOTTRANSACTIONAL         = "NOT TRANSACTIONAL";            //$NON-NLS-1$
    public final static String   ONCOMMITPRESERVE         = "ON COMMIT PRESERVE ROWS";      //$NON-NLS-1$
    public final static String   ONCOMMITDELETE           = "ON COMMIT DELETE ROWS";        //$NON-NLS-1$
    public final static String   GLOBALTEMPTABLE          = "GLOBAL TEMPORARY TABLE";       //$NON-NLS-1$
    public final static String   USER                     = "USER";                         //$NON-NLS-1$
    public final static String   SERVICE                  = "SERVICE";                      //$NON-NLS-1$

    public final static String   DBSPACE                  = "DBSPACE";                      //$NON-NLS-1$
    public final static String   CONNECT                  = "CONNECT";                      //$NON-NLS-1$
    public final static String   IDENTIFIED               = "IDENTIFIED";                   //$NON-NLS-1$
    public final static String   BY                       = "BY";                           //$NON-NLS-1$
    public final static String   TEMP_PASSWORD            = "'******'";                     //$NON-NLS-1$
    public final static String   GROUP                    = "GROUP";                        //$NON-NLS-1$
    public final static String   MEMBERSHIP               = "MEMBERSHIP";                   //$NON-NLS-1$
    public final static String   PCTFREE                  = "PCTFREE";                      //$NON-NLS-1$
    public final static String   EVENT_CONDITION          = "EVENT_CONDITION";                      //$NON-NLS-1$
    public final static String   EXISTING                 = "EXISTING";                     //$NON-NLS-1$
}
