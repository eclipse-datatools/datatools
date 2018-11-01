/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder;

/**
 * This interface defines the ResourceSet help id constants.
 */

//TODO remove commented out code after ID folks provide the help and it is tested 

public interface SQLBuilderContextIds 
{
  public static final String PLUGIN_NAME = "org.eclipse.datatools.sqltools.sqlbuilder";

    
   public static final String SQLD_NEW_STMT_DIALOG = PLUGIN_NAME + ".sqld0100";
  
  /* CONTEXT_ID sqlb0010 for Insert Design View */
  public static final String SQLB_INSERT_VIEW = PLUGIN_NAME + ".sqlb0010";

  /* CONTEXT_ID sqlb0100 for Update Design View */
  public static final String SQLB_UPDATE_VIEW = PLUGIN_NAME + ".sqlb0100";

  /* CONTEXT_ID sqlb0200 for Delete Design View */
  public static final String SQLB_DELETE_VIEW = PLUGIN_NAME + ".sqlb0200";

  /* CONTEXT_ID sqlb0300 for Select Design View */
  public static final String SQLB_SELECT_VIEW = PLUGIN_NAME + ".sqlb0300";

  /* CONTEXT_ID sqlb0400 for Full Select Design View */
  public static final String SQLB_FULL_SELECT_VIEW = PLUGIN_NAME + ".sqlb0400";

  /* CONTEXT_ID sqlb0500 for With Statement Design View */
  public static final String SQLB_WITH_STATEMENT_VIEW = PLUGIN_NAME + ".sqlb0500";

  /* CONTEXT_ID sqlb0600 for Values Clause Design View */
  public static final String SQLB_VALUES_CLAUSE_VIEW = PLUGIN_NAME + ".sqlb0600";

  /* CONTEXT_ID sqlb0700 for Values Row Design View */
  public static final String SQLB_VALUES_ROW_VIEW = PLUGIN_NAME + ".sqlb0700";

  /* CONTEXT_ID sqlb0800 for With Table Design View */
  public static final String SQLB_WITH_TABLE_VIEW = PLUGIN_NAME + ".sqlb0800";

  /* CONTEXT_ID sqlb1000 for Execute Query Page */
  //public static final String SQLB_EXECUTE_QUERY_PAGE = PLUGIN_NAME + ".sqlb1000";

  /* CONTEXT_ID sqlb1100 for Add Table Dialog */
  public static final String SQLB_ADD_TABLE_DIALOG = PLUGIN_NAME + ".sqlb1100";

  /* CONTEXT_ID sqlb1200 for SQL Builder Preferences Page */
  public static final String SQLB_PREFERENCES_PAGE =  PLUGIN_NAME + ".sqlb1200";

  /* CONTEXT_ID sqlb1300 for Specify Join Type Dialog */
  //public static final String SQLB_SPECIFY_JOIN_DIALOG =  PLUGIN_NAME + ".sqlb1300";

    /* CONTEXT_ID sqlb1400 for Specify Join Type Dialog */
  //public static final String SQLB_CREATE_JOIN_DIALOG =  PLUGIN_NAME + ".sqlb1400";

  /* CONTEXT_IDs for Expression Builder follow the sqlexxxx context IDS */

  /* CONTEXT_ID sqle0010 for Expression Builder Select type of expression Page */
  public static final String SQLE_SELECT_TYPE_PAGE = PLUGIN_NAME + ".sqle0010";
  
  /* CONTEXT_ID sqle0100 for Function Builder Page */
  public static final String SQLE_FUNCTION_BUILDER_PAGE = PLUGIN_NAME + ".sqle0100";

  /* CONTEXT_ID sqle0200 for Case Options Page */
  public static final String SQLE_CASE_OPTIONS_PAGE = PLUGIN_NAME + ".sqle0200";

  /* CONTEXT_ID sqle0300 for Search Type Case Page */
  public static final String SQLE_SEARCH_TYPE_PAGE = PLUGIN_NAME + ".sqle0300";

  /* CONTEXT_ID sqle0400 for Search Type Case Page */
  public static final String SQLE_SIMPLE_TYPE_PAGE = PLUGIN_NAME + ".sqle0400";

  /* CONTEXT_ID sqle0500 for CAST Builder Page */
  public static final String SQLE_CAST_BUILDER_PAGE = PLUGIN_NAME + ".sqle0500";

  /* CONTEXT_ID sqle0600 for Constant Options Page */
  public static final String SQLE_CONSTANT_OPTIONS_PAGE = PLUGIN_NAME + ".sqle0600";

  /* CONTEXT_ID sqle0700 for Numeric Constant Builder Page */
  public static final String SQLE_NUMERIC_CONSTANT_PAGE = PLUGIN_NAME + ".sqle0700";

  /* CONTEXT_ID sqle0800 for String Constant Builder Page */
  public static final String SQLE_STRING_CONSTANT_PAGE = PLUGIN_NAME + ".sqle0800";

  /* CONTEXT_ID sqle0900 for Subquery Page */
  public static final String SQLE_SUBQUERY_PAGE = PLUGIN_NAME + ".sqle0900";

  /* CONTEXT_ID sqle1000 for Expressions by Operators Page */
  public static final String SQLE_EXPRESSIONS_BY_OPERATOR_PAGE = PLUGIN_NAME + ".sqle1000";

  /* CONTEXT_IDs for Select DB Table Page Infopops follow the sdtpxxxx context IDS */
  
  /* CONTEXT_ID sdtp0010 for Select DB Table Page */
  /* CONTEXT_ID Select DB Table Page is used in many places, keep description generic */
  /* CONTEXT_ID this is used in the NewRMXWizard and NewSQLtoXMLWizard as well as in other pages */
  public static final String SDTP_SELECT_DB_TABLE_PAGE = PLUGIN_NAME + ".sdtp0010";


  /* CONTEXT_ID. These are used in the design view of the sqlbuilder when working with a SELECT statement */
  /* Also used in the sqlwizard pages when creating a SELECT statement. */
  public static final String SQDS_SELECT_GROUPS_WHITE_FIELD    = PLUGIN_NAME + ".sqds0050";
  //public static final String SQDS_SELECT_GROUPS_TYPE_DROPDOWN  = PLUGIN_NAME + ".sqds0060";
  //public static final String SQDS_SELECT_GROUPS_COLUMN_GRID    = PLUGIN_NAME + ".sqds0070";

  public static final String SQSS_SHARED_SEL_UP_DEL_GRID       = PLUGIN_NAME + ".sqss0010";
  //public static final String SQSS_SHARED_INS_UP_DEL_STMT_FIELD = PLUGIN_NAME + ".sqss0020";


  /* CONTEXT_ID for the Design view (SELECT statement).  Not shared with sqlwizard pages */
  //public static final String SQDS_STATEMENT_TEXT_FIELD      = PLUGIN_NAME + ".sqds0010";
//  public static final String SQDS_DISTINCT_CHECKBOX         = PLUGIN_NAME + ".sqds0020";
  //public static final String SQDS_GROUP_CONDITIONS_TAB_GRID = PLUGIN_NAME + ".sqds0030";  //Current not used.  Using a shared
  public static final String SQDS_COLUMNS_TAB_GRID          = PLUGIN_NAME + ".sqds0040";
  //public static final String SQDS_SELECT_TABS               = PLUGIN_NAME + ".sqds0090";


  /* CONTEXT_ID for the Design view (INSERT statement) */
  //public static final String SQDI_VALUES_RADIO_BUTTON   = PLUGIN_NAME + ".sqdi0010";
  //public static final String SQDI_SUBQUERY_RADIO_BUTTON = PLUGIN_NAME + ".sqdi0020";
  //public static final String SQDI_QUERY_NAME_DROPDOWN   = PLUGIN_NAME + ".sqdi0030";
  //public static final String SQDI_COLUMN_VALUE_GRID     = PLUGIN_NAME + ".sqdi0040";

 // public static final String SQDI_SUBQUERY_SOURCE_FIELD     = PLUGIN_NAME + ".sqdi0051";
  //public static final String SQDI_SUBQUERY_ADD_BUTTON       = PLUGIN_NAME + ".sqdi0052";
  //public static final String SQDI_SUBQUERY_REMOVE_BUTTON    = PLUGIN_NAME + ".sqdi0053";
  //public static final String SQDI_SUBQUERY_ADDALL_BUTTON    = PLUGIN_NAME + ".sqdi0054";
  //public static final String SQDI_SUBQUERY_REMOVEALL_BUTTON = PLUGIN_NAME + ".sqdi0055";
  //public static final String SQDI_SUBQUERY_DEST_COLUMN_GRID = PLUGIN_NAME + ".sqdi0056";


  /* CONTEXT_ID for the Design view (UDPATE statement) */
  //public static final String SQDU_SET_WHERE_TABS       = PLUGIN_NAME + ".sqdu0010";
  public static final String SQDU_SET_TAB    = PLUGIN_NAME + ".sqdu0020";
  // public static final String SQDU_ADD_PUSHBUTTON       = PLUGIN_NAME + ".sqdu0030";
  // public static final String SQDU_REMOVE_PUSHBUTTON    = PLUGIN_NAME + ".sqdu0040";
  // public static final String SQDU_ADD_GROUP_PUSHBUTTON = PLUGIN_NAME + ".sqdu0050";
  // public static final String SQDU_RIGHT_DEST_GRID      = PLUGIN_NAME + ".sqdu0060";


  /* CONTEXT_ID for the Design view (FULLSELECT statement) */
  public static final String SQDF_STMT_OPER_GRID       = PLUGIN_NAME + ".sqdf0010";
  public static final String SQDF_COL_SORTT_SORTO_GRID = PLUGIN_NAME + ".sqdf0020";


  /* CONTEXT_ID for the Design view (WITH statement) */
  public static final String SQDW_STMT_NAME_FIELD       = PLUGIN_NAME + ".sqdw0010";


  /* CONTEXT_ID for the Data Definition view */
  /* Right-click on the Statements folder */

 // public static final String SQLD_NEW_UPDATE_STMT_FIELD = PLUGIN_NAME + ".sqld0200";
 // public static final String SQLD_NEW_DELETE_STMT_FIELD = PLUGIN_NAME + ".sqld0300";
 // public static final String SQLD_NEW_SELECT_STMT_FIELD = PLUGIN_NAME + ".sqld0400";
//  public static final String SQLD_NEW_FULLSELECT_STMT_FIELD = PLUGIN_NAME + ".sqld0500";
 // public static final String SQLD_NEW_WITH_STMT_FIELD       = PLUGIN_NAME + ".sqld0600";


  /* CONTEXT_ID for the Graph view. Create join */
  //public static final String SQLG_CREATE_JOIN_SRC_COL_DROPDOWN   = PLUGIN_NAME + ".sqlg0020";
  //public static final String SQLG_CREATE_JOIN_TAR_ALIAS_DROPDOWN = PLUGIN_NAME + ".sqlg0021";
  //public static final String SQLG_CREATE_JOIN_TAR_COL_DROPDOWN   = PLUGIN_NAME + ".sqlg0022";
  //public static final String SQLG_CREATE_JOIN_SRC_ALIAS_DROPDOWN = PLUGIN_NAME + ".sqlg0023";

  public static final String SQLG_UPDATE_ALIAS_TEXT = PLUGIN_NAME + ".sqlg0030";


  /* CONTEXT_ID for create / change  Join Dialog */
  public static final String SQLG_CREATE_JOIN_DIALOG       = PLUGIN_NAME + ".sqlg0022";
  public static final String SQLG_DEFINE_JOINTYPE_DIALOG  = PLUGIN_NAME + ".sqlg0011";
  //public static final String SQLG_RIGHT_OUTER_JOIN_RADIO = PLUGIN_NAME + ".sqlg0012";
  //public static final String SQLG_FULL_OUTER_JOIN_RADIO  = PLUGIN_NAME + ".sqlg0013";

}
