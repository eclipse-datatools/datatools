package org.eclipse.datatools.sqltools.sqlbuilder.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;


/**
 * This class describes the functions supported by DB2.  It provides services to get
 * lists of function names in various categories, plus function signature information   
 * for functions.
 */
public class FunctionHelperDB2 extends FunctionHelper{    
    /* UI strings for additional function categories supported by this database. */
    
    /** Datalink functions category label. */ 
    public static final String CAT_LABEL_DATALINK_FUNCTIONS      = Messages._UI_FCN_DATALINK;
    /** Miscellaneous special DB2 functions category label. */
    public static final String CAT_LABEL_DB2_FUNCTIONS           = Messages._UI_FCN_DB2;
    /** Multi-Media Database Extender category functions. */
    public static final String CAT_LABEL_MMDB_EXTENDER_FUNCTIONS = Messages._UI_FCN_AIV_EXTENDERS;
    /** Message Queue (MQSeries) Extender functions category label. */ 
    public static final String CAT_LABEL_MQ_EXTENDER_FUNCTIONS   = Messages._UI_FCN_MQ_EXTENDER;    
    /** XML functions category label. */
    public static final String CAT_LABEL_SQLXML_FUNCTIONS        = Messages._UI_FCN_XMLFUNCTIONS;
    /** Text Extender category functions. */
    public static final String CAT_LABEL_TEXT_EXTENDER_FUNCTIONS = Messages._UI_FCN_TEXT_EXTENDERS;

    /* Lists of functions of the additional categories supported by this database. */
    
    /** Datalink function names. */
    protected List fDatalinkFunctionNamesList;
    /** Miscellaneous special DB2 function names. */
    protected List fDB2FunctionNamesList;
    /** DB2 for LUW Multi-Media DB Extender function names. */
    protected List fMMDBExtenderFunctionNamesList;
    /** Message Queue (MQSeries) Extender function names. */
    protected List fMQExtenderFunctionNamesList;
    /** SQL/XML function names. */
    protected List fSQLXMLFunctionNamesList;
    /** DB2 for LUW Text Extender function names. */
    protected List fTextExtenderFunctionNamesList;
  
    /**
     * Constructs and instance of this class.  This is the default constructor.
     * This constructor is protected to ensure that this class is constructed with a
     * database object.
     */
    protected FunctionHelperDB2() {
        super();
    }
    
    /**
     * Constructs and instance of this class with an association to the given database.
     * 
     * @param db the <code>Database</code> for which function help is needed
     */
     public FunctionHelperDB2( Database db ) {
         super( db );
     }
    
     /**
      * Gets a list of labels for the built-in function categories supported by the a
      * ssociated database.
      * 
      * @return the list of built-in function categories
      */
     public List getBuiltinFunctionCategories() {
         List catList = super.getBuiltinFunctionCategories();

         /* Add the function categories that are specific to DB2. */
         DBVersionHelper versionHelper = getVersionHelper();
         
         if (versionHelper.isDB2_LUW() == true) {
             catList.add(CAT_LABEL_DATALINK_FUNCTIONS);
             catList.add(CAT_LABEL_MMDB_EXTENDER_FUNCTIONS);
             catList.add(CAT_LABEL_TEXT_EXTENDER_FUNCTIONS);
         }
         catList.add(CAT_LABEL_DB2_FUNCTIONS);
         //catList.add(CAT_LABEL_MQ_EXTENDER_FUNCTIONS);
         if ( (versionHelper.isDB2_LUW() && versionHelper.isAtLeast(8,2))
           || (versionHelper.isDB2_zOS() && versionHelper.isAtLeast(9))
            ) {
            catList.add(CAT_LABEL_SQLXML_FUNCTIONS);
         }
         
         return catList;
     }

     /**
      * Gets a list of function names supported by the associated database in the 
      * given function category.
      * 
      * @param category the function category for which functions are needed
      * @return the list of the function names, or an empty list if the category is not
      * supported by the database
      */
     public List getFunctionNames( String category ) {
         List funcNames = super.getFunctionNames( category );

         if (funcNames.size() == 0) {
             if (category.equals(CAT_LABEL_DATALINK_FUNCTIONS)) {
                 funcNames = getDatalinkFunctionNames();
             }
             else if (category.equals(CAT_LABEL_DB2_FUNCTIONS)) {
                 funcNames = getDB2FunctionNames();
             }
             else if (category.equals(CAT_LABEL_MMDB_EXTENDER_FUNCTIONS)) {
                 funcNames = getMMDBExtenderFunctionNames();
             }
             else if (category.equals(CAT_LABEL_MQ_EXTENDER_FUNCTIONS)) {
                 funcNames = getMQExtenderFunctionNames();
             }
             else if (category.equals(CAT_LABEL_SQLXML_FUNCTIONS)) {
                 funcNames = getSQLXMLFunctionNames();
             }
             else if (category.equals(CAT_LABEL_TEXT_EXTENDER_FUNCTIONS)) {
                 funcNames = getTextExtenderFunctionNames();
             }
         }

         return funcNames;
     }

     /**
      * Gets a list of names of the Datalink functions supported by the database.
      * 
      * @return the list of Datalink functions
      */
     public List getDatalinkFunctionNames() {
         if (fDatalinkFunctionNamesList == null) {
             initDatalinkFunctions();
         }
         return fDatalinkFunctionNamesList;
     }

     /**
      * Gets a list of names of the special DB2 functions supported by the database.
      * 
      * @return the list of DB2 functions
      */
     public List getDB2FunctionNames() {
         if (fDB2FunctionNamesList == null) {
             initDB2Functions();
         }
         return fDB2FunctionNamesList;
     }
     
     /**
      * Gets a list of the names of the MMDB Extender function supported by the database.
      * 
      * @return the list of MMDB Extender functions
      */
     public List getMMDBExtenderFunctionNames() {
         if (fMMDBExtenderFunctionNamesList == null) {
             initMMDBExtenderFunctions();
         }
         
         return fMMDBExtenderFunctionNamesList;
     }
     
     /**
      * Gets a list of names of the MQSeries functions supported by the database.
      * 
      * @return the list of MQSeries functions
      */
     public List getMQExtenderFunctionNames() {
         if (fMQExtenderFunctionNamesList == null) {
             initMQExtenderFunctions();
         }
         return fMQExtenderFunctionNamesList;
     }

     /**
      * Gets a list of the names of the SQL/XML function supported by the database.
      * 
      * @return the list of SQL/XML functions
      */
     public List getSQLXMLFunctionNames() {
         if (fSQLXMLFunctionNamesList == null) {
             initSQLXMLFunctions();
         }
         
         return fSQLXMLFunctionNamesList;        
     }

     /**
      * Gets a list of the names of the Text Extender function supported by the database.
      * 
      * @return the list of Text Extender functions
      */
     public List getTextExtenderFunctionNames() {
         if (fTextExtenderFunctionNamesList == null) {
             initTextExtenderFunctions();
         }
         
         return fTextExtenderFunctionNamesList;
     }
     
     /** 
     * Gets an array of arrays (ie, a table) containing the function signatures specified 
     * by the given signature list number.  Each "row" in the "table" describes a
     * function signature.  In each row, the first "column" contains the return type, 
     * and the remaning columns describe the input parameter types.  
     * 
     * @param sigListNum the index of the signature list wanted
     * @return the signature table 
     */
    protected String[][] getFunctionSignatures( int sigListNum ) {
      String[][] list = new String[0][0];
  
      switch( sigListNum ) {
        // ==========> ABS, ABSVAL, CEILING, CEIL, FLOOR, SIGN
        case SIGLIST_000:
          list = new String[4][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "DOUBLE";
          list[1][0] = "INTEGER";        list[1][1] = "INTEGER";
          list[2][0] = "SMALLINT";       list[2][1] = "SMALLINT";
          list[3][0] = "BIGINT";         list[3][1] = "BIGINT";
          break;
        // ==========> DATE  (added (lee457))
        case SIGLIST_010:
          list = new String[4][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DATE";           list[0][1] = "DATE";
          list[1][0] = "DATE";           list[1][1] = "TIMESTAMP";
          list[2][0] = "DATE";           list[2][1] = "DOUBLE";
          list[3][0] = "DATE";           list[3][1] = "VARCHAR";
          break;
        // ==========> DAY, MONTH, YEAR   (added (lee457))
        case SIGLIST_020:
          list = new String[4][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "VARCHAR";
          list[1][0] = "INTEGER";        list[1][1] = "DATE";
          list[2][0] = "INTEGER";        list[2][1] = "TIMESTAMP";
          list[3][0] = "INTEGER";        list[3][1] = "DECIMAL";
          break;
        // ==========> HOUR, MINUTE, SECOND  (added (lee457))
        case SIGLIST_030:
          list = new String[4][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "VARCHAR";
          list[1][0] = "INTEGER";        list[1][1] = "TIME";
          list[2][0] = "INTEGER";        list[2][1] = "TIMESTAMP";
          list[3][0] = "INTEGER";        list[3][1] = "DECIMAL";
          break;
        // ==========> ACOS, ASIN, ATAN, (ATAN2 moved to signature= output:DOUBLE,
        // ==========> input:DOUBLE,DOUBLE), COS, COT, DEGREES, EXP, LOG, LN,
        // ==========> LOG10, RADIANS, SIN, SQRT, STDDEV, TAN, VAR, VARIANCE
        case SIGLIST_040:
          list = new String[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "DOUBLE";
          break;
        // ==========> ATAN2 (added (lee457))
        case SIGLIST_050:
          list = new String[1][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "DOUBLE";        list[0][2] = "DOUBLE";
          break;
        // ==========> MOD
        case SIGLIST_060:
          list = new String[3][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "INTEGER";       list[0][2] = "INTEGER";
          list[1][0] = "SMALLINT";       list[1][1] = "SMALLINT";      list[1][2] = "SMALLINT";
          list[2][0] = "BIGINT";         list[2][1] = "BIGINT";        list[2][2] = "BIGINT";
          break;
        // ==========> POWER
        case SIGLIST_070:
          list = new String[4][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "DOUBLE";        list[0][2] = "DOUBLE";
          list[1][0] = "DOUBLE";         list[1][1] = "DOUBLE";        list[1][2] = "INTEGER";
          list[2][0] = "INTEGER";        list[2][1] = "INTEGER";       list[2][2] = "INTEGER";
          list[3][0] = "BIGINT";         list[3][1] = "BIGINT";        list[3][2] = "BIGINT";
          break;
        // ==========> RAND    (How does this work? lee457)
        case SIGLIST_080:
          list = new String[2][];

          list[0] = new String[1];
          list[1] = new String[2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";
          list[1][0] = "DOUBLE";         list[1][1] = "INTEGER";
          break;
        // ==========> GENERATE_UNIQUE    (How does this work? lee457)
        case SIGLIST_090:
          list = new String[1][];
          list[0] = new String[1];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CHARACTER";
          break;
        // ==========> TABLE_NAME      (added (lee457)) (How does this work? lee457)
        // ==========> TABLE_SCHEMA    (added (lee457)) (How does this work? lee457)
        case SIGLIST_100:
          list = new String[2][];

          list[0] = new String[2];
          list[1] = new String[3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "VARCHAR";
          list[1][0] = "VARCHAR";        list[1][1] = "VARCHAR";       list[1][2] = "VARCHAR";
          break;
        // ==========> DLVALUE      (added (lee457)) (How does this work? lee457)
        case SIGLIST_110:
          list = new String[3][];

          list[0] = new String[2];
          list[1] = new String[3];
          list[2] = new String[4];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DATALINK";       list[0][1] = "VARCHAR";
          list[1][0] = "DATALINK";       list[1][1] = "VARCHAR";       list[1][2] = "VARCHAR";
          list[2][0] = "DATALINK";       list[2][1] = "VARCHAR";       list[2][2] = "VARCHAR"; list[2][3] = "VARCHAR";
          break;
        // ==========> DECIMAL      (added (lee457)) (How does this work? lee457)
        // ==========> DEC          (added (lee457)) (How does this work? lee457)
        case SIGLIST_120:
          list = new String[22][];

          list[0] = new String[2];
          list[1] = new String[3];
          list[2] = new String[4];
          list[3] = new String[5];
          list[4] = new String[2];
          list[5] = new String[2];
          list[6] = new String[2];
          list[7] = new String[2];
          list[8] = new String[2];
          list[9] = new String[2];
          list[10] = new String[3];
          list[11] = new String[3];
          list[12] = new String[3];
          list[13] = new String[3];
          list[14] = new String[3];
          list[15] = new String[3];
          list[16] = new String[4];
          list[17] = new String[4];
          list[18] = new String[4];
          list[19] = new String[4];
          list[20] = new String[4];
          list[21] = new String[4];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DECIMAL";        list[0][1] = "VARCHAR";
          list[1][0] = "DECIMAL";        list[1][1] = "VARCHAR";       list[1][2] = "INTEGER";
          list[2][0] = "DECIMAL";        list[2][1] = "VARCHAR";       list[2][2] = "INTEGER";       list[2][3] = "INTEGER";
          list[3][0] = "DECIMAL";        list[3][1] = "VARCHAR";       list[3][2] = "INTEGER";       list[3][3] = "INTEGER"; list[3][4] = "VARCHAR";

          list[4][0] = "DECIMAL";        list[4][1] = "SMALLINT";
          list[5][0] = "DECIMAL";        list[5][1] = "INTEGER";
          list[6][0] = "DECIMAL";        list[6][1] = "BIGINT";
          list[7][0] = "DECIMAL";        list[7][1] = "DECIMAL";
          list[8][0] = "DECIMAL";        list[8][1] = "REAL";
          list[9][0] = "DECIMAL";        list[9][1] = "DOUBLE";

          list[10][0] = "DECIMAL";       list[10][1] = "SMALLINT";     list[10][2] = "INTEGER";
          list[11][0] = "DECIMAL";       list[11][1] = "INTEGER";      list[11][2] = "INTEGER";
          list[12][0] = "DECIMAL";       list[12][1] = "BIGINT";       list[12][2] = "INTEGER";
          list[13][0] = "DECIMAL";       list[13][1] = "DECIMAL";      list[13][2] = "INTEGER";
          list[14][0] = "DECIMAL";       list[14][1] = "REAL";         list[14][2] = "INTEGER";
          list[15][0] = "DECIMAL";       list[15][1] = "DOUBLE";       list[15][2] = "INTEGER";

          list[16][0] = "DECIMAL";       list[16][1] = "SMALLINT";     list[16][2] = "INTEGER";      list[16][3] = "INTEGER";
          list[17][0] = "DECIMAL";       list[17][1] = "INTEGER";      list[17][2] = "INTEGER";      list[17][3] = "INTEGER";
          list[18][0] = "DECIMAL";       list[18][1] = "BIGINT";       list[18][2] = "INTEGER";      list[18][3] = "INTEGER";
          list[19][0] = "DECIMAL";       list[19][1] = "DECIMAL";      list[19][2] = "INTEGER";      list[19][3] = "INTEGER";
          list[20][0] = "DECIMAL";       list[20][1] = "REAL";         list[20][2] = "INTEGER";      list[20][3] = "INTEGER";
          list[21][0] = "DECIMAL";       list[21][1] = "DOUBLE";       list[21][2] = "INTEGER";      list[21][3] = "INTEGER";
          break;
        // ==========> TIMESTAMP      (added (lee457)) (How does this work? lee457)
        case SIGLIST_130:
          list = new String[6][];

          list[0] = new String[2];
          list[1] = new String[2];
          list[2] = new String[3];
          list[3] = new String[3];
          list[4] = new String[3];
          list[5] = new String[3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "TIMESTAMP";      list[0][1] = "TIMESTAMP";
          list[1][0] = "TIMESTAMP";      list[1][1] = "VARCHAR";
          list[2][0] = "TIMESTAMP";      list[2][1] = "VARCHAR";       list[2][2] = "VARCHAR";
          list[3][0] = "TIMESTAMP";      list[3][1] = "VARCHAR";       list[3][2] = "TIME";
          list[4][0] = "TIMESTAMP";      list[4][1] = "DATE";          list[4][2] = "VARCHAR";
          list[5][0] = "TIMESTAMP";      list[5][1] = "DATE";          list[5][2] = "TIME";
          break;
        // ==========> TRANSLATE      (added (lee457)) (How does this work? lee457)
        case SIGLIST_140:
          list = new String[10][];

          list[0] = new String[2];
          list[1] = new String[2];
          list[2] = new String[4];
          list[3] = new String[4];
          list[4] = new String[5];
          list[5] = new String[5];
          list[6] = new String[4];
          list[7] = new String[4];
          list[8] = new String[5];
          list[9] = new String[5];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CHARACTER";      list[0][1] = "CHARACTER";
          list[1][0] = "VARCHAR";        list[1][1] = "VARCHAR";
          list[2][0] = "CHARACTER";      list[2][1] = "CHARACTER";     list[2][2] = "VARCHAR";       list[2][3] = "VARCHAR";
          list[3][0] = "VARCHAR";        list[3][1] = "VARCHAR";       list[3][2] = "VARCHAR";       list[3][3] = "VARCHAR";
          list[4][0] = "CHARACTER";      list[4][1] = "CHARACTER";     list[4][2] = "VARCHAR";       list[4][3] = "VARCHAR";       list[4][4] = "VARCHAR";
          list[5][0] = "VARCHAR";        list[5][1] = "VARCHAR";       list[5][2] = "VARCHAR";       list[5][3] = "VARCHAR";       list[5][4] = "VARCHAR";
          list[6][0] = "GRAPHIC";        list[6][1] = "GRAPHIC";       list[6][2] = "VARGRAPHIC";    list[6][3] = "VARGRAPHIC";
          list[7][0] = "VARGRAPHIC";     list[7][1] = "VARGRAPHIC";    list[7][2] = "VARGRAPHIC";    list[7][3] = "VARGRAPHIC";
          list[8][0] = "GRAPHIC";        list[8][1] = "GRAPHIC";       list[8][2] = "VARGRAPHIC";    list[8][3] = "VARGRAPHIC";    list[8][4] = "VARGRAPHIC";
          list[9][0] = "VARGRAPHIC";     list[9][1] = "VARGRAPHIC";    list[9][2] = "VARGRAPHIC";    list[9][3] = "VARGRAPHIC";    list[9][4] = "VARGRAPHIC";
          break;
        // ==========> ROUND, TRUNCATE, TRUNC
        case SIGLIST_150:
          list = new String[3][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "DOUBLE";        list[0][2] = "INTEGER";
          list[1][0] = "INTEGER";        list[1][1] = "INTEGER";        list[1][2] = "INTEGER";
          list[2][0] = "BIGINT";         list[2][1] = "BIGINT";        list[2][2] = "INTEGER";
          break;
        // ==========> DAYNAME, MONTHNAME
        case SIGLIST_160:
          list = new String[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "DATE";
          list[1][0] = "VARCHAR";        list[1][1] = "VARCHAR";
          list[2][0] = "VARCHAR";        list[2][1] = "TIMESTAMP";
          break;
        // ==========> TIMESTAMP_ISO
        case SIGLIST_170:
          list = new String[4][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "TIMESTAMP";      list[0][1] = "DATE";
          list[1][0] = "TIMESTAMP";      list[1][1] = "TIME";
          list[2][0] = "TIMESTAMP";      list[2][1] = "TIMESTAMP";
          list[3][0] = "TIMESTAMP";      list[3][1] = "VARCHAR";
          break;
        // ==========> DAYOFWEEK,
        // ==========> DAYOFWEEK_ISO, (added (lee457))
        // ==========> DAYOFYEAR,
        // ==========> DAYS, (added (lee457))
        // ==========> JULIAN_DAY, QUARTER, WEEK,
        // ==========> WEEK_ISO, (added (lee457))
        case SIGLIST_180:
          list = new String[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "DATE";
          list[1][0] = "INTEGER";        list[1][1] = "VARCHAR";
          list[2][0] = "INTEGER";        list[2][1] = "TIMESTAMP";
          break;
        // ==========> TIMESTAMPDIFF
        case SIGLIST_190:
          list = new String[1][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "INTEGER";       list[0][2] = "CHARACTER";
          break;
        // ==========> ASCII
        case SIGLIST_200:
          list = new String[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "CHARACTER";
          list[1][0] = "INTEGER";        list[1][1] = "VARCHAR";
          list[2][0] = "INTEGER";        list[2][1] = "CLOB";
          break;
        // ==========> CHR
        case SIGLIST_210:
          list = new String[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CHARACTER";      list[0][1] = "INTEGER";
          break;
        // ==========> INSERT
        case SIGLIST_220:
          list = new String[3][5];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "VARCHAR";       list[0][2] = "INTEGER";       list[0][3] = "INTEGER";       list[0][4] = "VARCHAR";
          list[1][0] = "CLOB";           list[1][1] = "CLOB";          list[1][2] = "INTEGER";       list[1][3] = "INTEGER";       list[1][4] = "CLOB";
          list[2][0] = "BLOB";           list[2][1] = "BLOB";          list[2][2] = "INTEGER";       list[2][3] = "INTEGER";       list[2][4] = "BLOB";
          break;
        // ==========> LTRIM, RTRIM, TRIM
        case SIGLIST_230:
          list = new String[5][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CLOB";         list[0][1] = "CLOB";
          list[1][0] = "VARCHAR";      list[1][1] = "VARCHAR";
          list[2][0] = "VARCHAR";      list[2][1] = "CHARACTER";
          list[3][0] = "VARGRAPHIC";   list[3][1] = "GRAPHIC";
          list[4][0] = "VARGRAPHIC";   list[4][1] = "VARGRAPHIC";
          break;
        // ==========> LCASE  (added (lee457))
        case SIGLIST_240:
          list = new String[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CLOB";         list[0][1] = "CLOB";
          list[1][0] = "VARCHAR";      list[1][1] = "VARCHAR";
          list[2][0] = "CHARACTER";    list[2][1] = "CHARACTER";
          break;
        // ==========> LOWER  (added (lee457))
        case SIGLIST_250:
          list = new String[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CLOB";         list[0][1] = "CLOB";
          list[1][0] = "VARCHAR";      list[1][1] = "VARCHAR";
          list[2][0] = "CHARACTER";    list[2][1] = "CHARACTER";
          break;
        // ==========> LOCATE     (How does this work? (lee457))
        case SIGLIST_260:
          list = new String[6][];

          list[0] = new String[4];
          list[1] = new String[4];
          list[2] = new String[4];
          list[3] = new String[3];
          list[4] = new String[3];
          list[5] = new String[3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";          list[0][1] = "VARCHAR";       list[0][2] = "VARCHAR";       list[0][3] = "INTEGER";
          list[1][0] = "INTEGER";        list[1][1] = "CLOB";          list[1][2] = "CLOB";          list[1][3] = "INTEGER";
          list[2][0] = "INTEGER";        list[2][1] = "BLOB";          list[2][2] = "BLOB";          list[2][3] = "INTEGER";
          list[3][0] = "INTEGER";        list[3][1] = "VARCHAR";       list[3][2] = "VARCHAR";
          list[4][0] = "INTEGER";        list[4][1] = "CLOB";          list[4][2] = "CLOB";
          list[5][0] = "INTEGER";        list[5][1] = "BLOB";          list[5][2] = "BLOB";
          break;
        // ==========> SOUNDEX
        case SIGLIST_270:
          list = new String[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CHARACTER";      list[0][1] = "VARCHAR";
          break;
        // ==========> DIFFERENCE
        case SIGLIST_280:
          list = new String[1][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "VARCHAR";       list[0][2] = "VARCHAR";
          break;
        // ==========> REPEAT, RIGHT, LEFT
        case SIGLIST_290:
          list = new String[3][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "VARCHAR";       list[0][2] = "INTEGER";
          list[1][0] = "CLOB";           list[1][1] = "CLOB";          list[1][2] = "INTEGER";
          list[2][0] = "BLOB";           list[2][1] = "BLOB";          list[2][2] = "INTEGER";
          break;
        // ==========> SPACE
        case SIGLIST_300:
          list = new String[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "INTEGER";
          break;
        // ==========> REPLACE
        case SIGLIST_310:
          list = new String[3][4];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "VARCHAR";       list[0][2] = "VARCHAR";       list[0][3] = "VARCHAR";
          list[1][0] = "CLOB";           list[1][1] = "CLOB";          list[1][2] = "CLOB";          list[1][3] = "CLOB";
          list[2][0] = "BLOB";           list[2][1] = "BLOB";          list[2][2] = "BLOB";          list[2][3] = "BLOB";
          break;
        // ==========> CHAR   (added (lee457))
        case SIGLIST_320:
          list = new String[32][2];

          list[0] = new String[2];
          list[1] = new String[2];
          list[2] = new String[2];
          list[3] = new String[2];

          list[4] = new String[3];
          list[5] = new String[3];
          list[6] = new String[3];
          list[7] = new String[3];

          list[8] = new String[2];
          list[9] = new String[2];
          list[10] = new String[2];

          list[11] = new String[3];
          list[12] = new String[3];
          list[13] = new String[3];
          list[14] = new String[3];
          list[15] = new String[3];
          list[16] = new String[3];
          list[17] = new String[3];
          list[18] = new String[3];
          list[19] = new String[3];
          list[20] = new String[3];
          list[21] = new String[3];
          list[22] = new String[3];
          list[23] = new String[3];
          list[24] = new String[3];
          list[25] = new String[3];

          list[26] = new String[2];
          list[27] = new String[2];
          list[28] = new String[2];
          list[29] = new String[2];

          list[30] = new String[3];

          list[31] = new String[2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CHARACTER";      list[0][1] = "CHARACTER";
          list[1][0] = "CHARACTER";      list[1][1] = "VARCHAR";
          list[2][0] = "CHARACTER";      list[2][1] = "LONG VARCHAR";
          list[3][0] = "CHARACTER";      list[3][1] = "CLOB";

          list[4][0] = "CHARACTER";      list[4][1] = "CHARACTER";     list[4][2] = "INTEGER";
          list[5][0] = "CHARACTER";      list[5][1] = "VARCHAR";       list[5][2] = "INTEGER";
          list[6][0] = "CHARACTER";      list[6][1] = "LONG VARCHAR";  list[6][2] = "INTEGER";
          list[7][0] = "CHARACTER";      list[7][1] = "CLOB";          list[7][2] = "INTEGER";

          list[8][0] = "CHARACTER";      list[8][1] = "DATE";
          list[9][0] = "CHARACTER";      list[9][1] = "TIME";
          list[10][0] = "CHARACTER";     list[10][1] = "TIMESTAMP";

          list[11][0] = "CHARACTER";     list[11][1] = "DATE";         list[11][2] = "ISO";
          list[12][0] = "CHARACTER";     list[12][1] = "DATE";         list[12][2] = "USA";
          list[13][0] = "CHARACTER";     list[13][1] = "DATE";         list[13][2] = "EUR";
          list[14][0] = "CHARACTER";     list[14][1] = "DATE";         list[14][2] = "JIS";
          list[15][0] = "CHARACTER";     list[15][1] = "DATE";         list[15][2] = "LOCAL";
          list[16][0] = "CHARACTER";     list[16][1] = "TIME";         list[16][2] = "ISO";
          list[17][0] = "CHARACTER";     list[17][1] = "TIME";         list[17][2] = "USA";
          list[18][0] = "CHARACTER";     list[18][1] = "TIME";         list[18][2] = "EUR";
          list[19][0] = "CHARACTER";     list[19][1] = "TIME";         list[19][2] = "JIS";
          list[20][0] = "CHARACTER";     list[20][1] = "TIME";         list[20][2] = "LOCAL";
          list[21][0] = "CHARACTER";     list[21][1] = "TIMESTAMP";    list[21][2] = "ISO";
          list[22][0] = "CHARACTER";     list[22][1] = "TIMESTAMP";    list[22][2] = "USA";
          list[23][0] = "CHARACTER";     list[23][1] = "TIMESTAMP";    list[23][2] = "EUR";
          list[24][0] = "CHARACTER";     list[24][1] = "TIMESTAMP";    list[24][2] = "JIS";
          list[25][0] = "CHARACTER";     list[25][1] = "TIMESTAMP";    list[25][2] = "LOCAL";

          list[26][0] = "CHARACTER";     list[26][1] = "SMALLINT";
          list[27][0] = "CHARACTER";     list[27][1] = "INTEGER";
          list[28][0] = "CHARACTER";     list[28][1] = "BIGINT";
          list[29][0] = "CHARACTER";     list[29][1] = "DECIMAL";

          list[30][0] = "CHARACTER";     list[30][1] = "DECIMAL";      list[30][2] = "VARCHAR";

          list[31][0] = "CHARACTER";     list[31][1] = "DOUBLE";
          break;
        // ==========> DOUBLE  (added (lee457))
        case SIGLIST_330:
          list = new String[7][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "SMALLINT";
          list[1][0] = "DOUBLE";         list[1][1] = "INTEGER";
          list[2][0] = "DOUBLE";         list[2][1] = "BIGINT";
          list[3][0] = "DOUBLE";         list[3][1] = "DECIMAL";
          list[4][0] = "DOUBLE";         list[4][1] = "REAL";
          list[5][0] = "DOUBLE";         list[5][1] = "DOUBLE";
          list[6][0] = "DOUBLE";         list[6][1] = "VARCHAR";
          break;
        // ==========> DOUBLE_PRECISION  (added (lee457))
        // ==========> FLOAT  (added (lee457))
        case SIGLIST_340:
          list = new String[6][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "SMALLINT";
          list[1][0] = "DOUBLE";         list[1][1] = "INTEGER";
          list[2][0] = "DOUBLE";         list[2][1] = "BIGINT";
          list[3][0] = "DOUBLE";         list[3][1] = "DECIMAL";
          list[4][0] = "DOUBLE";         list[4][1] = "REAL";
          list[5][0] = "DOUBLE";         list[5][1] = "DOUBLE";
          break;
        // ==========> DIGITS (added lee457)
        case SIGLIST_350:
          list = new String[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CHARACTER";      list[0][1] = "DECIMAL";
          break;
        // ==========> EVENT_MON_STATE (added lee457)
        case SIGLIST_360:
          list = new String[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "VARCHAR";
          break;
        // ==========> UCASE (added lee457)
        // ==========> UPPER (added lee457)
        case SIGLIST_370:
          list = new String[2][2];

          //Function Returns               Function Input Parm Type
          list[0][0] = "VARCHAR";         list[0][1] = "VARCHAR";
          list[1][0] = "CHARACTER";       list[1][1] = "CHARACTER";
          break;
        // ==========> DLCOMMENT, (added lee457)
        // ==========> DLLINKTYPE, (added lee457)
        // ==========> DLURLCOMPLETE, (added lee457)
        // ==========> DLURLPATH, (added lee457)
        // ==========> DLURLPATHONLY, (added lee457)
        // ==========> DLURLSCHEME, (added lee457)
        // ==========> DLURLSERVER, (added lee457)
        case SIGLIST_380:
          list = new String[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "DATALINK";
          break;
        // ==========> MICROSECOND  (added (lee457))
        case SIGLIST_390:
          list = new String[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "VARCHAR";
          list[1][0] = "INTEGER";        list[1][1] = "TIMESTAMP";
          list[2][0] = "INTEGER";        list[2][1] = "DECIMAL";
          break;
        // ==========> TIME  (added (lee457))
        case SIGLIST_400:
          list = new String[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "TIME";           list[0][1] = "TIME";
          list[1][0] = "TIME";           list[1][1] = "TIMESTAMP";
          list[2][0] = "TIME";           list[2][1] = "VARCHAR";
          break;
        // ==========> MIDNIGHT_SECONDS
        case SIGLIST_410:
          list = new String[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "TIME";
          list[1][0] = "INTEGER";        list[1][1] = "TIMESTAMP";
          list[2][0] = "INTEGER";        list[2][1] = "VARCHAR";
          break;
        // ==========> AVG  (added (lee457))
        case SIGLIST_420:
          list = new String[6][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "SMALLINT";
          list[1][0] = "*";              list[1][1] = "INTEGER";   // various
          list[2][0] = "*";              list[2][1] = "BIGINT";    // various
          list[3][0] = "*";              list[3][1] = "DECIMAL";   // various
          list[4][0] = "DOUBLE";         list[4][1] = "REAL";
          list[5][0] = "*";              list[5][1] = "DOUBLE";    // various
          break;
        // ==========> BIGINT  (added (lee457))
        case SIGLIST_430:
          list = new String[7][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "BIGINT";         list[0][1] = "SMALLINT";
          list[1][0] = "BIGINT";         list[1][1] = "INTEGER";
          list[2][0] = "BIGINT";         list[2][1] = "BIGINT";
          list[3][0] = "BIGINT";         list[3][1] = "DECIMAL";
          list[4][0] = "BIGINT";         list[4][1] = "REAL";
          list[5][0] = "BIGINT";         list[5][1] = "DOUBLE";
          list[6][0] = "BIGINT";         list[6][1] = "VARCHAR";
          break;
        // ==========> INT  (added (lee457))
        // ==========> INTEGER  (added (lee457))
        case SIGLIST_440:
          list = new String[7][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "SMALLINT";
          list[1][0] = "INTEGER";        list[1][1] = "INTEGER";
          list[2][0] = "INTEGER";        list[2][1] = "BIGINT";
          list[3][0] = "INTEGER";        list[3][1] = "DECIMAL";
          list[4][0] = "INTEGER";        list[4][1] = "REAL";
          list[5][0] = "INTEGER";        list[5][1] = "DOUBLE";
          list[6][0] = "INTEGER";        list[6][1] = "VARCHAR";
          break;
        // ==========> SMALLINT  (added (lee457))
        case SIGLIST_450:
          list = new String[7][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "SMALLINT";       list[0][1] = "SMALLINT";
          list[1][0] = "SMALLINT";       list[1][1] = "INTEGER";
          list[2][0] = "SMALLINT";       list[2][1] = "BIGINT";
          list[3][0] = "SMALLINT";       list[3][1] = "DECIMAL";
          list[4][0] = "SMALLINT";       list[4][1] = "REAL";
          list[5][0] = "SMALLINT";       list[5][1] = "DOUBLE";
          list[6][0] = "SMALLINT";       list[6][1] = "VARCHAR";
          break;
        // ==========> SUM  (added (lee457))
        case SIGLIST_460:
          list = new String[6][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "SMALLINT";
          list[1][0] = "INTEGER";        list[1][1] = "INTEGER";
          list[2][0] = "BIGINT";         list[2][1] = "BIGINT";
          list[3][0] = "DECIMAL";        list[3][1] = "DECIMAL";
          list[4][0] = "DOUBLE";         list[4][1] = "REAL";
          list[5][0] = "DOUBLE";         list[5][1] = "DOUBLE";
          break;
        // ==========> REAL  (added (lee457))
        case SIGLIST_470:
          list = new String[6][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "REAL";           list[0][1] = "SMALLINT";
          list[1][0] = "REAL";           list[1][1] = "INTEGER";
          list[2][0] = "REAL";           list[2][1] = "BIGINT";
          list[3][0] = "REAL";           list[3][1] = "DECIMAL";
          list[4][0] = "REAL";           list[4][1] = "REAL";
          list[5][0] = "REAL";           list[5][1] = "DOUBLE";
          break;
        // ==========> NODENUMBER  (added (lee457))
        // ==========> PARTITION  (added (lee457))
        case SIGLIST_480:
          list = new String[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "*";   // any
          break;
        // ==========> GROUPING  (added (lee457))
        case SIGLIST_490:
          list = new String[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "SMALLINT";       list[0][1] = "*";   // any
          break;
        // ==========> COUNT_BIG  (added (lee457))
        case SIGLIST_500:
          list = new String[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DECIMAL";        list[0][1] = "*";  // various
          break;
        // ==========> HEX  (added (lee457))
        // ==========> TYPE_NAME  (added (lee457))
        // ==========> TYPE_SCHEMA  (added (lee457))
        case SIGLIST_510:
          list = new String[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "*";  // various
          break;
        // ==========> LENGTH  (added (lee457))
        // ==========> COUNT  (added (lee457))
        // ==========> TYPE_ID  (added (lee457))
        case SIGLIST_520:
          list = new String[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "*";  // various
          break;
        // ==========> MAX  (added (lee457))
        // ==========> MIN  (added (lee457))
        case SIGLIST_530:
          list = new String[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "*";              list[0][1] = "*";  // various, various
          break;
        // ==========> NULLIF  (added (lee457))
        case SIGLIST_540:
          list = new String[1][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "*";              list[0][1] = "*";             list[0][2] = "*";  // various, various, various
          break;
        // ==========> CONCAT  (added (lee457))
        case SIGLIST_550:
          list = new String[9][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "*";              list[0][1] = "CHARACTER";     list[0][2] = "*";  // all "*" are various in this section
          list[1][0] = "*";              list[1][1] = "VARCHAR";       list[1][2] = "*";
          list[2][0] = "*";              list[2][1] = "LONG VARCHAR";  list[2][2] = "*";
          list[3][0] = "*";              list[3][1] = "CLOB";          list[3][2] = "*";
          list[4][0] = "*";              list[4][1] = "GRAPHIC";       list[4][2] = "*";
          list[5][0] = "*";              list[5][1] = "VARGRAPHIC";    list[5][2] = "*";
          list[6][0] = "*";              list[6][1] = "LONG VARGRAPHIC"; list[6][2] = "*";
          list[7][0] = "*";              list[7][1] = "DBCLOB";        list[7][2] = "*";
          list[8][0] = "BLOB";           list[8][1] = "BLOB";          list[8][2] = "*";
          break;
        // ==========> RAISE_ERROR   (added (lee457))
        case SIGLIST_560:
          list = new String[1][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "*";              list[0][1] = "VARCHAR";       list[0][2] = "VARCHAR";
          break;
        // ==========> POSSTR  (added (lee457))
        case SIGLIST_570:
          list = new String[9][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "CHARACTER";     list[0][2] = "*";  // all "*" are various in this section
          list[1][0] = "INTEGER";        list[1][1] = "VARCHAR";       list[1][2] = "*";
          list[2][0] = "INTEGER";        list[2][1] = "LONG VARCHAR";  list[2][2] = "*";
          list[3][0] = "INTEGER";        list[3][1] = "CLOB";          list[3][2] = "*";
          list[4][0] = "INTEGER";        list[4][1] = "GRAPHIC";       list[4][2] = "*";
          list[5][0] = "INTEGER";        list[5][1] = "VARGRAPHIC";    list[5][2] = "*";
          list[6][0] = "INTEGER";        list[6][1] = "LONG VARGRAPHIC"; list[6][2] = "*";
          list[7][0] = "INTEGER";        list[7][1] = "DBCLOB";        list[7][2] = "*";
          list[8][0] = "INTEGER";        list[8][1] = "BLOB";          list[8][2] = "*";
          break;
        // ==========> LONG_VARCHAR  (added (lee457))
        case SIGLIST_580:
          list = new String[4][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "LONG VARCHAR";   list[0][1] = "CHARACTER";
          list[1][0] = "LONG VARCHAR";   list[1][1] = "VARCHAR";
          list[2][0] = "LONG VARCHAR";   list[2][1] = "LONG VARCHAR";
          list[3][0] = "LONG VARCHAR";   list[3][1] = "CLOB";
          break;
        // ==========> LONG_VARGRAPHIC  (added (lee457))
        case SIGLIST_590:
          list = new String[4][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "LONG VARGRAPHIC";  list[0][1] = "GRAPHIC";
          list[1][0] = "LONG VARGRAPHIC";  list[1][1] = "VARGRAPHIC";
          list[2][0] = "LONG VARGRAPHIC";  list[2][1] = "LONG VARGRAPHIC";
          list[3][0] = "LONG VARGRAPHIC";  list[3][1] = "DBCLOB";
          break;
        // ==========> COALESCE  (added (lee457))
        // ==========> VALUE  (added (lee457))
        case SIGLIST_600:
          list = new String[1][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "*";              list[0][1] = "*";             list[0][2] = "*";
          break;
        // ==========> SUBSTR  (added (lee457))
        case SIGLIST_610:
          list = new String[18][];

          list[0] = new String[3];
          list[1] = new String[3];
          list[2] = new String[3];
          list[3] = new String[3];
          list[4] = new String[3];
          list[5] = new String[3];
          list[6] = new String[3];
          list[7] = new String[3];
          list[8] = new String[3];

          list[9] = new String[4];
          list[10] = new String[4];
          list[11] = new String[4];
          list[12] = new String[4];
          list[13] = new String[4];
          list[14] = new String[4];
          list[15] = new String[4];
          list[16] = new String[4];
          list[17] = new String[4];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CHARACTER";      list[0][1] = "CHARACTER";     list[0][2] = "INTEGER";
          list[1][0] = "VARCHAR";        list[1][1] = "VARCHAR";       list[1][2] = "INTEGER";
          list[2][0] = "LONG VARCHAR";   list[2][1] = "LONG VARCHAR";  list[2][2] = "INTEGER";
          list[3][0] = "CLOB";           list[3][1] = "CLOB";          list[3][2] = "INTEGER";
          list[4][0] = "GRAPHIC";        list[4][1] = "GRAPHIC";       list[4][2] = "INTEGER";
          list[5][0] = "VARGRAPHIC";     list[5][1] = "VARGRAPHIC";    list[5][2] = "INTEGER";
          list[6][0] = "LONG VARGRAPHIC";  list[6][1] = "LONG VARGRAPHIC";  list[6][2] = "INTEGER";
          list[7][0] = "DBCLOB";         list[7][1] = "DBCLOB";        list[7][2] = "INTEGER";
          list[8][0] = "BLOB";          list[8][1] = "BLOB";            list[8][2] = "INTEGER";

          list[9][0] =  "CHARACTER";     list[9][1] = "CHARACTER";     list[9][2] = "INTEGER";       list[9][3] = "INTEGER";
          list[10][0] = "VARCHAR";       list[10][1] = "VARCHAR";      list[10][2] = "INTEGER";      list[10][3] = "INTEGER";
          list[11][0] = "LONG VARCHAR";  list[11][1] = "LONG VARCHAR";  list[11][2] = "INTEGER";      list[11][3] = "INTEGER";
          list[12][0] = "CLOB";          list[12][1] = "CLOB";         list[12][2] = "INTEGER";      list[12][3] = "INTEGER";
          list[13][0] = "GRAPHIC";       list[13][1] = "GRAPHIC";      list[13][2] = "INTEGER";      list[13][3] = "INTEGER";
          list[14][0] = "VARGRAPHIC";    list[14][1] = "VARGRAPHIC";   list[14][2] = "INTEGER";      list[14][3] = "INTEGER";
          list[15][0] = "LONG VARGRAPHIC";  list[15][1] = "LONG VARGRAPHIC";  list[15][2] = "INTEGER";  list[15][3] = "INTEGER";
          list[16][0] = "DBCLOB";        list[16][1] = "DBCLOB";       list[16][2] = "INTEGER";      list[16][3] = "INTEGER";
          list[17][0] = "BLOB";             list[17][1] = "BLOB";         list[17][2] = "INTEGER";      list[17][3] = "INTEGER";
          break;
        // ==========> BLOB  (added (lee457))
        case SIGLIST_620:
          list = new String[18][];

          list[0] = new String[2];
          list[1] = new String[2];
          list[2] = new String[2];
          list[3] = new String[2];
          list[4] = new String[2];
          list[5] = new String[2];
          list[6] = new String[2];
          list[7] = new String[2];
          list[8] = new String[2];

          list[9] = new String[3];
          list[10] = new String[3];
          list[11] = new String[3];
          list[12] = new String[3];
          list[13] = new String[3];
          list[14] = new String[3];
          list[15] = new String[3];
          list[16] = new String[3];
          list[17] = new String[3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "BLOB";           list[0][1] = "CHARACTER";
          list[1][0] = "BLOB";           list[1][1] = "VARCHAR";
          list[2][0] = "BLOB";           list[2][1] = "LONG VARCHAR";
          list[3][0] = "BLOB";           list[3][1] = "CLOB";
          list[4][0] = "BLOB";           list[4][1] = "GRAPHIC";
          list[5][0] = "BLOB";           list[5][1] = "VARGRAPHIC";
          list[6][0] = "BLOB";           list[6][1] = "LONG VARGRAPHIC";
          list[7][0] = "BLOB";           list[7][1] = "DBCLOB";
          list[8][0] = "BLOB";           list[8][1] = "BLOB";

          list[9][0] = "BLOB";           list[9][1] = "CHARACTER";     list[9][2] = "INTEGER";
          list[10][0] = "BLOB";          list[10][1] = "VARCHAR";      list[10][2] = "INTEGER";
          list[11][0] = "BLOB";          list[11][1] = "LONG VARCHAR"; list[11][2] = "INTEGER";
          list[12][0] = "BLOB";          list[12][1] = "CLOB";         list[12][2] = "INTEGER";
          list[13][0] = "BLOB";          list[13][1] = "GRAPHIC";      list[13][2] = "INTEGER";
          list[14][0] = "BLOB";          list[14][1] = "VARGRAPHIC";   list[14][2] = "INTEGER";
          list[15][0] = "BLOB";          list[15][1] = "LONG VARGRAPHIC";  list[15][2] = "INTEGER";
          list[16][0] = "BLOB";          list[16][1] = "DBCLOB";       list[16][2] = "INTEGER";
          list[17][0] = "BLOB";          list[17][1] = "BLOB";         list[17][2] = "INTEGER";
          break;
        // ==========> CLOB  (added (lee457))
        case SIGLIST_630:
          list = new String[8][];

          list[0] = new String[2];
          list[1] = new String[2];
          list[2] = new String[2];
          list[3] = new String[2];

          list[4] = new String[3];
          list[5] = new String[3];
          list[6] = new String[3];
          list[7] = new String[3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CLOB";           list[0][1] = "CHARACTER";
          list[1][0] = "CLOB";           list[1][1] = "VARCHAR";
          list[2][0] = "CLOB";           list[2][1] = "LONG VARCHAR";
          list[3][0] = "CLOB";           list[3][1] = "CLOB";
          list[4][0] = "CLOB";           list[4][1] = "CHARACTER";     list[4][2] = "INTEGER";
          list[5][0] = "CLOB";           list[5][1] = "VARCHAR";       list[5][2] = "INTEGER";
          list[6][0] = "CLOB";           list[6][1] = "LONG VARCHAR";  list[6][2] = "INTEGER";
          list[7][0] = "CLOB";           list[7][1] = "CLOB";          list[7][2] = "INTEGER";
          break;
        // ==========> CORR  (added (lee457))
        // ==========> CORRELATION  (added (lee457))
         // ==========> COVAR  (added (lee457))
         // ==========> COVARIANCE  (added (lee457))
         // ==========> REGR_AVGX  (added (lee457))
         // ==========> REGR_AVGY  (added (lee457))
         // ==========> REGR_ICPT  (added (lee457))
         // ==========> REGR_INTERCEPT  (added (lee457))
         // ==========> REGR_R2  (added (lee457))
         // ==========> REGR_SLOPE  (added (lee457))
         // ==========> REGR_SXX  (added (lee457))
         // ==========> REGR_SXY  (added (lee457))
         // ==========> REGR_SYY  (added (lee457))
        case SIGLIST_640:
          list = new String[36][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "SMALLINT";      list[0][2] = "SMALLINT";
          list[1][0] = "DOUBLE";         list[1][1] = "SMALLINT";      list[1][2] = "INTEGER";
          list[2][0] = "DOUBLE";         list[2][1] = "SMALLINT";      list[2][2] = "BIGINT";
          list[3][0] = "DOUBLE";         list[3][1] = "SMALLINT";      list[3][2] = "DECIMAL";
          list[4][0] = "DOUBLE";         list[4][1] = "SMALLINT";      list[4][2] = "REAL";
          list[5][0] = "DOUBLE";         list[5][1] = "SMALLINT";      list[5][2] = "DOUBLE";

          list[6][0] = "DOUBLE";         list[6][1] = "INTEGER";       list[6][2] = "SMALLINT";
          list[7][0] = "DOUBLE";         list[7][1] = "INTEGER";       list[7][2] = "INTEGER";
          list[8][0] = "DOUBLE";         list[8][1] = "INTEGER";       list[8][2] = "BIGINT";
          list[9][0] = "DOUBLE";         list[9][1] = "INTEGER";       list[9][2] = "DECIMAL";
          list[10][0] = "DOUBLE";        list[10][1] = "INTEGER";      list[10][2] = "REAL";
          list[11][0] = "DOUBLE";        list[11][1] = "INTEGER";      list[11][2] = "DOUBLE";

          list[12][0] = "DOUBLE";        list[12][1] = "BIGINT";       list[12][2] = "SMALLINT";
          list[13][0] = "DOUBLE";        list[13][1] = "BIGINT";       list[13][2] = "INTEGER";
          list[14][0] = "DOUBLE";        list[14][1] = "BIGINT";       list[14][2] = "BIGINT";
          list[15][0] = "DOUBLE";        list[15][1] = "BIGINT";       list[15][2] = "DECIMAL";
          list[16][0] = "DOUBLE";        list[16][1] = "BIGINT";       list[16][2] = "REAL";
          list[17][0] = "DOUBLE";        list[17][1] = "BIGINT";       list[17][2] = "DOUBLE";

          list[18][0] = "DOUBLE";        list[18][1] = "DECIMAL";      list[18][2] = "SMALLINT";
          list[19][0] = "DOUBLE";        list[19][1] = "DECIMAL";      list[19][2] = "INTEGER";
          list[20][0] = "DOUBLE";        list[20][1] = "DECIMAL";      list[20][2] = "BIGINT";
          list[21][0] = "DOUBLE";        list[21][1] = "DECIMAL";      list[21][2] = "DECIMAL";
          list[22][0] = "DOUBLE";        list[22][1] = "DECIMAL";      list[22][2] = "REAL";
          list[23][0] = "DOUBLE";        list[23][1] = "DECIMAL";      list[23][2] = "DOUBLE";

          list[24][0] = "DOUBLE";        list[24][1] = "REAL";         list[24][2] = "SMALLINT";
          list[25][0] = "DOUBLE";        list[25][1] = "REAL";         list[25][2] = "INTEGER";
          list[26][0] = "DOUBLE";        list[26][1] = "REAL";         list[26][2] = "BIGINT";
          list[27][0] = "DOUBLE";        list[27][1] = "REAL";         list[27][2] = "DECIMAL";
          list[28][0] = "DOUBLE";        list[28][1] = "REAL";         list[28][2] = "REAL";
          list[29][0] = "DOUBLE";        list[29][1] = "REAL";         list[29][2] = "DOUBLE";

          list[30][0] = "DOUBLE";        list[30][1] = "DOUBLE";       list[30][2] = "SMALLINT";
          list[31][0] = "DOUBLE";        list[31][1] = "DOUBLE";       list[31][2] = "INTEGER";
          list[32][0] = "DOUBLE";        list[32][1] = "DOUBLE";       list[32][2] = "BIGINT";
          list[33][0] = "DOUBLE";        list[33][1] = "DOUBLE";       list[33][2] = "DECIMAL";
          list[34][0] = "DOUBLE";        list[34][1] = "DOUBLE";       list[34][2] = "REAL";
          list[35][0] = "DOUBLE";        list[35][1] = "DOUBLE";       list[35][2] = "DOUBLE";
          break;
        // ==========> REGR_COUNT  (added (lee457))
        case SIGLIST_650:
          list = new String[36][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "SMALLINT";      list[0][2] = "SMALLINT";
          list[1][0] = "INTEGER";        list[1][1] = "SMALLINT";      list[1][2] = "INTEGER";
          list[2][0] = "INTEGER";        list[2][1] = "SMALLINT";      list[2][2] = "BIGINT";
          list[3][0] = "INTEGER";        list[3][1] = "SMALLINT";      list[3][2] = "DECIMAL";
          list[4][0] = "INTEGER";        list[4][1] = "SMALLINT";      list[4][2] = "REAL";
          list[5][0] = "INTEGER";        list[5][1] = "SMALLINT";      list[5][2] = "DOUBLE";

          list[6][0] = "INTEGER";        list[6][1] = "INTEGER";       list[6][2] = "SMALLINT";
          list[7][0] = "INTEGER";        list[7][1] = "INTEGER";       list[7][2] = "INTEGER";
          list[8][0] = "INTEGER";        list[8][1] = "INTEGER";       list[8][2] = "BIGINT";
          list[9][0] = "INTEGER";        list[9][1] = "INTEGER";       list[9][2] = "DECIMAL";
          list[10][0] = "INTEGER";       list[10][1] = "INTEGER";      list[10][2] = "REAL";
          list[11][0] = "INTEGER";       list[11][1] = "INTEGER";      list[11][2] = "DOUBLE";

          list[12][0] = "INTEGER";       list[12][1] = "BIGINT";       list[12][2] = "SMALLINT";
          list[13][0] = "INTEGER";       list[13][1] = "BIGINT";       list[13][2] = "INTEGER";
          list[14][0] = "INTEGER";       list[14][1] = "BIGINT";       list[14][2] = "BIGINT";
          list[15][0] = "INTEGER";       list[15][1] = "BIGINT";       list[15][2] = "DECIMAL";
          list[16][0] = "INTEGER";       list[16][1] = "BIGINT";       list[16][2] = "REAL";
          list[17][0] = "INTEGER";       list[17][1] = "BIGINT";       list[17][2] = "DOUBLE";

          list[18][0] = "INTEGER";       list[18][1] = "DECIMAL";      list[18][2] = "SMALLINT";
          list[19][0] = "INTEGER";       list[19][1] = "DECIMAL";      list[19][2] = "INTEGER";
          list[20][0] = "INTEGER";       list[20][1] = "DECIMAL";      list[20][2] = "BIGINT";
          list[21][0] = "INTEGER";       list[21][1] = "DECIMAL";      list[21][2] = "DECIMAL";
          list[22][0] = "INTEGER";       list[22][1] = "DECIMAL";      list[22][2] = "REAL";
          list[23][0] = "INTEGER";       list[23][1] = "DECIMAL";      list[23][2] = "DOUBLE";

          list[24][0] = "INTEGER";       list[24][1] = "REAL";         list[24][2] = "SMALLINT";
          list[25][0] = "INTEGER";       list[25][1] = "REAL";         list[25][2] = "INTEGER";
          list[26][0] = "INTEGER";       list[26][1] = "REAL";         list[26][2] = "BIGINT";
          list[27][0] = "INTEGER";       list[27][1] = "REAL";         list[27][2] = "DECIMAL";
          list[28][0] = "INTEGER";       list[28][1] = "REAL";         list[28][2] = "REAL";
          list[29][0] = "INTEGER";       list[29][1] = "REAL";         list[29][2] = "DOUBLE";

          list[30][0] = "INTEGER";       list[30][1] = "DOUBLE";       list[30][2] = "SMALLINT";
          list[31][0] = "INTEGER";       list[31][1] = "DOUBLE";       list[31][2] = "INTEGER";
          list[32][0] = "INTEGER";       list[32][1] = "DOUBLE";       list[32][2] = "BIGINT";
          list[33][0] = "INTEGER";       list[33][1] = "DOUBLE";       list[33][2] = "DECIMAL";
          list[34][0] = "INTEGER";       list[34][1] = "DOUBLE";       list[34][2] = "REAL";
          list[35][0] = "INTEGER";       list[35][1] = "DOUBLE";       list[35][2] = "DOUBLE";
          break;
        // ==========> DBCLOB  (added (lee457))
        case SIGLIST_660:
          list = new String[8][];

          list[0] = new String[2];
          list[1] = new String[2];
          list[2] = new String[2];
          list[3] = new String[2];

          list[4] = new String[3];
          list[5] = new String[3];
          list[6] = new String[3];
          list[7] = new String[3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DBCLOB";         list[0][1] = "GRAPHIC";
          list[1][0] = "DBCLOB";         list[1][1] = "VARGRAPHIC";
          list[2][0] = "DBCLOB";         list[2][1] = "LONG VARGRAPHIC";
          list[3][0] = "DBCLOB";         list[3][1] = "DBCLOB";

          list[4][0] = "DBCLOB";         list[4][1] = "GRAPHIC";       list[4][2] = "INTEGER";
          list[5][0] = "DBCLOB";         list[5][1] = "VARGRAPHIC";    list[5][2] = "INTEGER";
          list[6][0] = "DBCLOB";         list[6][1] = "LONG VARGRAPHIC";  list[6][2] = "INTEGER";
          list[7][0] = "DBCLOB";         list[7][1] = "DBCLOB";        list[7][2] = "INTEGER";
          break;
        // ==========> GRAPHIC  (added (lee457))
        case SIGLIST_670:
          list = new String[8][];

          list[0] = new String[2];
          list[1] = new String[2];
          list[2] = new String[2];
          list[3] = new String[2];

          list[4] = new String[3];
          list[5] = new String[3];
          list[6] = new String[3];
          list[7] = new String[3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "GRAPHIC";        list[0][1] = "GRAPHIC";
          list[1][0] = "GRAPHIC";        list[1][1] = "VARGRAPHIC";
          list[2][0] = "GRAPHIC";        list[2][1] = "LONG VARGRAPHIC";
          list[3][0] = "GRAPHIC";        list[3][1] = "DBCLOB";

          list[4][0] = "GRAPHIC";        list[4][1] = "GRAPHIC";       list[4][2] = "INTEGER";
          list[5][0] = "GRAPHIC";        list[5][1] = "VARGRAPHIC";    list[5][2] = "INTEGER";
          list[6][0] = "GRAPHIC";        list[6][1] = "LONG VARGRAPHIC";  list[6][2] = "INTEGER";
          list[7][0] = "GRAPHIC";        list[7][1] = "DBCLOB";        list[7][2] = "INTEGER";
          break;
        // ==========> VARCHAR  (added (lee457))
        case SIGLIST_680:
          list = new String[11][];

          list[0] = new String[2];
          list[1] = new String[2];
          list[2] = new String[2];
          list[3] = new String[2];

          list[4] = new String[3];
          list[5] = new String[3];
          list[6] = new String[3];
          list[7] = new String[3];

          list[8] = new String[2];
          list[9] = new String[2];
          list[10] = new String[2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "CHARACTER";
          list[1][0] = "VARCHAR";        list[1][1] = "VARCHAR";
          list[2][0] = "VARCHAR";        list[2][1] = "LONG VARCHAR";
          list[3][0] = "VARCHAR";        list[3][1] = "CLOB";

          list[4][0] = "VARCHAR";        list[4][1] = "CHARACTER";     list[4][2] = "INTEGER";
          list[5][0] = "VARCHAR";        list[5][1] = "VARCHAR";       list[5][2] = "INTEGER";
          list[6][0] = "VARCHAR";        list[6][1] = "LONG VARCHAR";  list[6][2] = "INTEGER";
          list[7][0] = "VARCHAR";        list[7][1] = "CLOB";          list[7][2] = "INTEGER";

          list[8][0] = "VARCHAR";        list[8][1] = "DATE";
          list[9][0] = "VARCHAR";        list[9][1] = "TIME";
          list[10][0] = "VARCHAR";       list[10][1] = "TIMESTAMP";
          break;
        // ==========> VARGRAPHIC  (added (lee457))
        case SIGLIST_690:
          list = new String[9][];

          list[0] = new String[2];
          list[1] = new String[2];
          list[2] = new String[2];
          list[3] = new String[2];

          list[4] = new String[3];
          list[5] = new String[3];
          list[6] = new String[3];
          list[7] = new String[3];

          list[8] = new String[2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARGRAPHIC";     list[0][1] = "GRAPHIC";
          list[1][0] = "VARGRAPHIC";     list[1][1] = "VARGRAPHIC";
          list[2][0] = "VARGRAPHIC";     list[2][1] = "LONG VARGRAPHIC";
          list[3][0] = "VARGRAPHIC";     list[3][1] = "DBCLOB";

          list[4][0] = "VARGRAPHIC";     list[4][1] = "GRAPHIC";       list[4][2] = "INTEGER";
          list[5][0] = "VARGRAPHIC";     list[5][1] = "VARGRAPHIC";    list[5][2] = "INTEGER";
          list[6][0] = "VARGRAPHIC";     list[6][1] = "LONG VARGRAPHIC"; list[6][2] = "INTEGER";
          list[7][0] = "VARGRAPHIC";     list[7][1] = "DBCLOB";        list[7][2] = "INTEGER";

          list[8][0] = "VARGRAPHIC";     list[8][1] = "VARCHAR";
          break;
        // ==========> VEBLOB_CP_SMALL, VEBLOB_CP_LARGE  (Where is this documented? (lee457))
        case SIGLIST_700:
          list = new String[1][5];

          //Function Returns             Function Input Parm Type
          list[0][0] = "BLOB";           list[0][1] = "BLOB";          list[0][2] = "INTEGER";       list[0][3] = "INTEGER";       list[0][4] = "INTEGER";
          break;
        // ==========> Special Registers
        case SIGLIST_710:
          list = new String[1][];

          list[0] = new String[1];

          //Function Returns             Function Input Parm Type
          list[0][0] = "STRING";
          break;
        case SIGLIST_720:
          // "DB2TX.CCSID",
          list = new String[2][2];
          list[0][0] = "SMALLINT";           list[0][1] = "DB2TEXTFH";
          list[1][0] = "SMALLINT";           list[1][1] = "DB2TEXTH";
          break;
        case SIGLIST_730:
          // "DB2TX.CONTAINS",
          list = new String[2][3];
          list[0][0] = "INTEGER";            list[0][1] = "DB2TEXTFH";             list[0][2] = "LONG VARCHAR";
          list[1][0] = "INTEGER";            list[1][1] = "DB2TEXTH";              list[1][2] = "LONG VARCHAR";
          break;
        case SIGLIST_740:
          // "DB2TX.FILE",
          list = new String[2][];
          list[0] = new String[2];
          list[1] = new String[3];

          list[0][0] = "DB2TEXTFH";            list[0][1] = "DB2TEXTFH";
          list[1][0] = "DB2TEXTFH";            list[1][1] = "DB2TEXTFH";             list[1][2] = "VARCHAR(150)";
          break;
        case SIGLIST_750:
          // "DB2TX.FORMAT",
          list = new String[6][];
          list[0] = new String[2];
          list[1] = new String[2];
          list[2] = new String[3];
          list[3] = new String[3];
          list[4] = new String[3];
          list[5] = new String[3];

          list[0][0] = "VARCHAR(30)";          list[0][1] = "DB2TEXTFH";
          list[1][0] = "VARCHAR(30)";          list[1][1] = "DB2TEXTH";
          list[2][0] = "DB2TEXTFH";            list[2][1] = "DB2TEXTFH";             list[2][2] = "VARCHAR(30)";
          list[3][0] = "DB2TEXTH";             list[3][1] = "DB2TEXTFH";             list[3][2] = "VARCHAR(30)";
          list[4][0] = "DB2TEXTFH";            list[4][1] = "DB2TEXTH";              list[4][2] = "VARCHAR(30)";
          list[5][0] = "DB2TEXTH";             list[5][1] = "DB2TEXTH";              list[5][2] = "VARCHAR(30)";
          break;
        case SIGLIST_760:
          // "DB2TX.HANDLE",
          list = new String[4][3];
          list[0][0] = "DB2TEXTFH";         list[0][1] = "DB2TEXTHLISTP";      list[0][2] = "INTEGER";
          list[1][0] = "DB2TEXTFH";         list[1][1] = "DB2TEXTFHLISTP";     list[1][2] = "INTEGER";
          list[2][0] = "DB2TEXTH";          list[2][1] = "DB2TEXTHLISTP";      list[2][2] = "INTEGER";
          list[3][0] = "DB2TEXTH";          list[3][1] = "DB2TEXTFHLISTP";     list[3][2] = "INTEGER";
          break;
        case SIGLIST_770:
          // "DB2TX.HANDLE_LIST",
          list = new String[4][3];
          list[0][0] = "DB2TEXTFHLISTP";        list[0][1] = "DB2TEXTH";       list[0][2] = "LONG VARCHAR";
          list[1][0] = "DB2TEXTHLISTP";         list[1][1] = "DB2TEXTH";       list[1][2] = "LONG VARCHAR";
          list[2][0] = "DB2TEXTFHLISTP";        list[2][1] = "DB2TEXTFH";      list[2][2] = "LONG VARCHAR";
          list[3][0] = "DB2TEXTHLISTP";         list[3][1] = "DB2TEXTFH";      list[3][2] = "LONG VARCHAR";
          break;
        case SIGLIST_780:
          // "DB2TX.INIT_TEXT_HANDLE",
          list = new String[2][];
          list[0] = new String[3];
          list[1] = new String[5];

          list[0][0] = "DB2TEXTH";        list[0][1] = "VARCHAR(30)";       list[0][2] = "VARCHAR(30)";
          list[1][0] = "DB2TEXTFH";       list[1][1] = "CCSID";             list[1][2] = "VARCHAR(30)";  list[1][3] = "VARCHAR(30)";  list[1][4] = "VARCHAR(150)";
          break;
        case SIGLIST_790:
          // "DB2TX.LANGUAGE",
          list = new String[6][];
          list[0] = new String[2];
          list[1] = new String[2];
          list[2] = new String[3];
          list[3] = new String[3];
          list[4] = new String[3];
          list[5] = new String[3];

          list[0][0] = "VARCHAR(30)";        list[0][1] = "DB2TEXTFH";
          list[1][0] = "VARCHAR(30)";        list[1][1] = "DB2TEXTH";
          list[2][0] = "DB2TEXTFH";          list[2][1] = "DB2TEXTH";      list[2][2] = "VARCHAR(30)";
          list[3][0] = "DB2TEXTH";           list[3][1] = "DB2TEXTH";      list[3][2] = "VARCHAR(30)";
          list[4][0] = "DB2TEXTFH";          list[4][1] = "DB2TEXTFH";     list[4][2] = "VARCHAR(30)";
          list[5][0] = "DB2TEXTH";           list[5][1] = "DB2TEXTFH";     list[5][2] = "VARCHAR(30)";
          break;
        case SIGLIST_800:
          // "DB2TX.NO_OF_DOCUMENTS",
          list = new String[2][2];
          list[0][0] = "INTEGER";         list[0][1] = "DB2TEXTHLISTP";
          list[1][0] = "INTEGER";         list[1][1] = "DB2TEXTFLISTP";
          break;
        case SIGLIST_810:
          // "DB2TX.NO_OF_MATCHES",
          list = new String[2][3];
          list[0][0] = "INTEGER";         list[0][1] = "DB2TEXTFH";       list[0][2] = "LONG VARCHAR";
          list[1][0] = "INTEGER";         list[1][1] = "DB2TEXTH";        list[1][2] = "LONG VARCHAR";
          break;
        case SIGLIST_820:
          // "DB2TX.RANK",
          list = new String[2][3];
          list[0][0] = "DOUBLE";         list[0][1] = "DB2TEXTFH";       list[0][2] = "LONG VARCHAR";
          list[1][0] = "DOUBLE";         list[1][1] = "DB2TEXTH";        list[1][2] = "LONG VARCHAR";
          break;
        case SIGLIST_830:
          // "DB2TX.REFINE",
          list = new String[1][3];
          list[0][0] = "LONG VARCHAR";         list[0][1] = "LONG VARCHAR";       list[0][2] = "LONG VARCHAR";
          break;
        case SIGLIST_840:
          //"DB2TX.SEARCH_RESULT"
          list = new String[1][5];
          list[0][0] = "LONG VARCHAR";         list[0][1] = "schema";       list[0][2] = "table";   list[0][3] = "handle";  list[0][4] = "LONG VARCHAR";
          break;
        case SIGLIST_850:
          //"MMDBSYS.AlignValue",
          list = new String[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle";
          break;
        case SIGLIST_860:
          //"MMDBSYS.AspectRatio",
          list = new String[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle";
          break;
        case SIGLIST_870:
          //"MMDBSYS.BitsPerSample",
          list = new String[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle";
          break;
        case SIGLIST_880:
          //"MMDBSYS.BytesPerSec",
          list = new String[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle";
          break;
        case SIGLIST_890:
          //"MMDBSYS.Comment",
          list = new String[2][];
          list[0] = new String[2];
          list[1] = new String[3];

          list[0][0] = "LONG VARCHAR";      list[0][1] = "handle";
          list[1][0] = "handle";            list[1][1] = "handle";      list[1][2] = "LONG VARCHAR";

          break;
        case SIGLIST_900:
          //"MMDBSYS.CompressType",
          list = new String[1][2];

          list[0][0] = "VARCHAR(8)";      list[0][1] = "handle";
          break;
        case SIGLIST_910:
          //"MMDBSYS.Content",
          list = new String[15][];
          list[0] = new String[2];
          list[1] = new String[4];
          list[2] = new String[4];
          list[3] = new String[3];
          list[4] = new String[5];
          list[5] = new String[4];
          list[6] = new String[6];
          list[7] = new String[5];
          list[8] = new String[5];
          list[9] = new String[6];
          list[10] = new String[6];
          list[11] = new String[6];
          list[12] = new String[6];
          list[13] = new String[7];
          list[14] = new String[7];

          list[0][0] = "content";        list[0][1] = "handle";
          list[1][0] = "content";        list[1][1] = "handle";     list[1][2] = "offset";        list[1][3] = "size";
          list[2][0] = "content";        list[2][1] = "handle";     list[2][2] = "target_file";   list[2][3] = "overwrite";
          list[3][0] = "content";        list[3][1] = "handle";     list[3][2] = "target_format";
          list[4][0] = "content";        list[4][1] = "handle";     list[4][2] = "target_file";   list[4][3] = "overwrite";       list[4][4] = "target_format";
          list[5][0] = "content";        list[5][1] = "handle";     list[5][2] = "target_format"; list[5][3] = "conversion_options";
          list[6][0] = "content";        list[6][1] = "handle";     list[6][2] = "target_file";   list[6][3] = "overwrite";       list[6][4] = "target_format";   list[6][5] = "conversion_options";
          list[7][0] = "handle";         list[7][1] = "handle";     list[7][2] = "content";       list[7][3] = "source_format";   list[7][4] = "target_file";
          list[8][0] = "handle";         list[8][1] = "handle";     list[8][2] = "source_file";   list[8][3] = "source_format";   list[8][4] = "stortype";
          list[9][0] = "handle";         list[9][1] = "handle";     list[9][2] = "content";       list[9][3] = "target_file";     list[9][4] = "attrs";           list[9][5] = "thumbnail";
          list[10][0] = "handle";        list[10][1] = "handle";    list[10][2] = "source_file";  list[10][3] = "stortype";       list[10][4] = "attrs";          list[10][5] = "thumbnail";
          list[11][0] = "handle";        list[11][1] = "handle";    list[11][2] = "content";      list[11][3] = "source_format";  list[11][4] = "target_format";  list[11][5] = "target_file";
          list[12][0] = "handle";        list[12][1] = "handle";    list[12][2] = "source_file";  list[12][3] = "source_format";  list[12][4] = "target_format";  list[12][5] = "target_file";
          list[13][0] = "handle";        list[13][1] = "handle";    list[13][2] = "content";      list[13][3] = "source_format";  list[13][4] = "target_format";  list[13][5] = "conversion_options";   list[13][6] = "target_file";
          list[14][0] = "handle";        list[14][1] = "handle";    list[14][2] = "source_file";  list[14][3] = "source_format";  list[14][4] = "target_format";  list[14][5] = "conversion_options";   list[14][6] = "target_file";

          break;
        case SIGLIST_920:
          //"MMDBSYS.DB2Audio",
          list = new String[4][6];

          list[0][0] = "handle";    list[0][1] = "dbname";     list[0][2] = "content";       list[0][3] = "format";       list[0][4] = "target_file";   list[0][5] = "comment";
          list[1][0] = "handle";    list[1][1] = "dbname";     list[1][2] = "source_file";   list[1][3] = "format";       list[1][4] = "stortype";      list[1][5] = "comment";
          list[2][0] = "handle";    list[2][1] = "dbname";     list[2][2] = "content";       list[2][3] = "target_file";  list[2][4] = "comment";       list[2][5] = "attrs";
          list[3][0] = "handle";    list[3][1] = "dbname";     list[3][2] = "source_file";   list[3][3] = "stortype";     list[3][4] = "comment";       list[3][5] = "attrs";

          break;
        case SIGLIST_930:
          //"MMDBSYS.DB2Image",
          list = new String[8][];
          list[0] = new String[6];
          list[1] = new String[6];
          list[2] = new String[7];
          list[3] = new String[7];
          list[4] = new String[7];
          list[5] = new String[7];
          list[6] = new String[8];
          list[7] = new String[8];

          list[0][0] = "handle";    list[0][1] = "dbname";     list[0][2] = "content";       list[0][3] = "source_format";       list[0][4] = "target_file";   list[0][5] = "comment";
          list[1][0] = "handle";    list[1][1] = "dbname";     list[1][2] = "source_file";   list[1][3] = "source_format";       list[1][4] = "stortype";      list[1][5] = "comment";
          list[2][0] = "handle";    list[2][1] = "dbname";     list[2][2] = "content";       list[2][3] = "target_file";         list[2][4] = "comment";       list[2][5] = "attrs";              list[2][6] = "thumbnail";
          list[3][0] = "handle";    list[3][1] = "dbname";     list[3][2] = "source_file";   list[3][3] = "stortype";            list[3][4] = "comment";       list[3][5] = "attrs";              list[3][6] = "thumbnail";
          list[4][0] = "handle";    list[4][1] = "dbname";     list[4][2] = "content";       list[4][3] = "source_format";       list[4][4] = "target_format"; list[4][5] = "target_file";        list[4][6] = "comment";
          list[5][0] = "handle";    list[5][1] = "dbname";     list[5][2] = "source_file";   list[5][3] = "source_format";       list[5][4] = "target_format"; list[5][5] = "target_file";        list[5][6] = "comment";
          list[6][0] = "handle";    list[6][1] = "dbname";     list[6][2] = "content";       list[6][3] = "source_format";       list[6][4] = "target_format"; list[6][5] = "conversion_options"; list[6][6] = "target_file";  list[6][7] = "comment";
          list[7][0] = "handle";    list[7][1] = "dbname";     list[7][2] = "source_file";   list[7][3] = "source_format";       list[7][4] = "target_format"; list[7][5] = "conversion_options"; list[7][6] = "target_file";  list[7][7] = "comment";

          break;
        case SIGLIST_940:
          //"MMDBSYS.DB2Video",
          list = new String[4][];
          list[0] = new String[6];
          list[1] = new String[6];
          list[2] = new String[7];
          list[3] = new String[7];

          list[0][0] = "handle";    list[0][1] = "dbname";     list[0][2] = "content";       list[0][3] = "format";       list[0][4] = "target_file";   list[0][5] = "comment";
          list[1][0] = "handle";    list[1][1] = "dbname";     list[1][2] = "source_file";   list[1][3] = "format";       list[1][4] = "stortype";      list[1][5] = "comment";
          list[2][0] = "handle";    list[2][1] = "dbname";     list[2][2] = "content";       list[2][3] = "target_file";  list[2][4] = "comment";       list[2][5] = "attrs";              list[2][6] = "thumbnail";
          list[3][0] = "handle";    list[3][1] = "dbname";     list[3][2] = "source_file";   list[3][3] = "stortype";     list[3][4] = "comment";       list[3][5] = "attrs";              list[3][6] = "thumbnail";

          break;
        case SIGLIST_950:
          //"MMDBSYS.Duration",
          list = new String[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle";
          break;
        case SIGLIST_960:
          //"MMDBSYS.Filename",
          list = new String[1][2];

          list[0][0] = "VARCHAR(250)";      list[0][1] = "handle";
          break;
        case SIGLIST_970:
          //"MMDBSYS.FindInstrument",
          list = new String[1][3];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2AUDIO)";    list[0][2] = "instrument(VARCHAR(255))";
          break;
        case SIGLIST_980:
          //"MMDBSYS.FindTrackName",
          list = new String[1][3];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2AUDIO)";    list[0][2] = "trackname(VARCHAR(255))";
          break;
        case SIGLIST_990:
          //"MMDBSYS.Format",
          list = new String[1][2];

          list[0][0] = "VARCHAR(8)";      list[0][1] = "handle";
          break;
        case SIGLIST_1000:
          //"MMDBSYS.FrameRate",
          list = new String[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2VIDEO)";
          break;
        case SIGLIST_1010:
          //"MMDBSYS.GetInstruments",
          list = new String[1][2];

          list[0][0] = "VARCHAR(1536)";      list[0][1] = "handle(DB2AUDIO)";
          break;
        case SIGLIST_1020:
          //"MMDBSYS.GetTrackNames",
          list = new String[1][2];

          list[0][0] = "VARCHAR(1536)";      list[0][1] = "handle(DB2AUDIO)";
          break;
        case SIGLIST_1030:
          //"MMDBSYS.Height",
          list = new String[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle(DB2IMAGE|DB2VIDEO)";
          break;
        case SIGLIST_1040:
          //"MMDBSYS.Importer",
          list = new String[1][2];

          list[0][0] = "CHAR(8)";      list[0][1] = "handle(DB2IMAGE|DB2AUDIO|DB2VIDEO)";
          break;
        case SIGLIST_1050:
          //"MMDBSYS.ImportTime",
          list = new String[1][2];

          list[0][0] = "TIMESTAMP";      list[0][1] = "handle(DB2IMAGE|DB2AUDIO|DB2VIDEO)";
          break;
        case SIGLIST_1060:
          //"MMDBSYS.MaxBytesPerSec",
          list = new String[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle(DB2VIDEO)";
          break;
        case SIGLIST_1070:
          //"MMDBSYS.NumAudioTracks",
          list = new String[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2AUDIO|DB2VIDEO)";
          break;
        case SIGLIST_1080:
          //"MMDBSYS.NumChannels",
          list = new String[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2AUDIO|DB2VIDEO)";
          break;
        case SIGLIST_1090:
          //"MMDBSYS.NumColors",
          list = new String[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle(DB2IMAGE)";
          break;
        case SIGLIST_1100:
          //"MMDBSYS.NumFrames",
          list = new String[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle(DB2VIDEO)";
          break;
        case SIGLIST_1110:
          //"MMDBSYS.NumVideoTracks",
          list = new String[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2VIDEO)";
          break;
        case SIGLIST_1120:
          //"MMDBSYS.QbScoreFromName",  // EEE only.  deprecated
          list = new String[1][3];

          list[0][0] = "DOUBLE";      list[0][1] = "imgHandle(DB2IMAGE)";    list[0][2] = "VARCHAR(18)";
          break;
        case SIGLIST_1130:
          //"MMDBSYS.QbScoreFromStr",
          list = new String[1][3];

          list[0][0] = "DOUBLE";      list[0][1] = "imgHandle(DB2IMAGE)";    list[0][2] = "VARCHAR(1024)";
          break;
        case SIGLIST_1140:
          //"MMDBSYS.QbScoreTBFromName",  // EEE only
//          list = new String[1][3];
//
//          list[0][0] = "DOUBLE";      list[0][1] = "imgHandle(DB2IMAGE)";    list[0][2] = "VARCHAR(18)";

//          list = new String[1][1];
//          list[0][0] = db2NotSupported;
          break;
        case SIGLIST_1150:
          //"MMDBSYS.QbScoreTBFromStr",
//          list = new String[2][];
//          list[0] = new String[4];
//          list[1] = new String[5];
//
//          list[0][0] = "DOUBLE";      list[0][1] = "query(VARCHAR(1024))";    list[0][2] = "table(CHAR(18))";    list[0][3] = "column(CHAR(18))";
//          list[1][0] = "DOUBLE";      list[1][1] = "query(VARCHAR(1024))";    list[1][2] = "table(CHAR(18))";    list[1][3] = "column(CHAR(18))";    list[1][4] = "maxReturns(INTEGER)";

//          list = new String[1][1];
//          list[0][0] = db2NotSupported;
          break;
        case SIGLIST_1160:
          //"MMDBSYS.Replace",
          list = new String[8][];
          list[0] = new String[7];
          list[1] = new String[6];
          list[2] = new String[7];
          list[3] = new String[7];
          list[4] = new String[7];
          list[5] = new String[7];
          list[6] = new String[8];
          list[7] = new String[8];

          list[0][0] = "handle";      list[0][1] = "handle";    list[0][2] = "content";       list[0][3] = "source_format";    list[0][4] = "target_file";    list[0][5] = "attrs";               list[0][6] = "comment";
          list[1][0] = "handle";      list[1][1] = "handle";    list[1][2] = "source_file";   list[1][3] = "source_format";    list[1][4] = "stortype";       list[1][5] = "comment";
          list[2][0] = "handle";      list[2][1] = "handle";    list[2][2] = "content";       list[2][3] = "target_file";      list[2][4] = "comment";        list[2][5] = "attrs";               list[2][6] = "thumbnail";
          list[3][0] = "handle";      list[3][1] = "handle";    list[3][2] = "source_file";   list[3][3] = "stortype";         list[3][4] = "comment";        list[3][5] = "attrs";               list[3][6] = "thumbnail";
          list[4][0] = "handle";      list[4][1] = "handle";    list[4][2] = "content";       list[4][3] = "source_format";    list[4][4] = "target_format";  list[4][5] = "target_file";         list[4][6] = "comment";
          list[5][0] = "handle";      list[5][1] = "handle";    list[5][2] = "source_file";   list[5][3] = "source_format";    list[5][4] = "target_format";  list[5][5] = "target_file";         list[5][6] = "comment";
          list[6][0] = "handle";      list[6][1] = "handle";    list[6][2] = "content";       list[6][3] = "source_format";    list[6][4] = "target_format";  list[6][5] = "target_file";         list[6][6] = "conversion_options";   list[6][7] = "comment";
          list[7][0] = "handle";      list[7][1] = "handle";    list[7][2] = "source_file";   list[7][3] = "source_format";    list[7][4] = "target_format";  list[7][5] = "conversion_options";  list[7][6] = "target_file";          list[7][7] = "comment";

          break;
        case SIGLIST_1170:
          //"MMDBSYS.SamplingRate",
          list = new String[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle(DB2AUDIO|DB2VIDEO)";
          break;
        case SIGLIST_1180:
          //"MMDBSYS.Size",
          list = new String[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle(DB2IMAGE|DB2AUDIO|DB2VIDEO)";
          break;
        case SIGLIST_1190:
          //"MMDBSYS.Thumbnail",
          list = new String[2][];
          list[0] = new String[2];
          list[1] = new String[3];

          list[0][0] = "LONG VARCHAR FOR BIT DATA";      list[0][1] = "handle(DB2IMAGE|DB2VIDEO)";
          list[1][0] = "handle(DB2IMAGE|DB2VIDEO)";      list[1][1] = "handle(DB2IMAGE|DB2VIDEO)";   list[1][2] = "new_thumbnail(LONG VARCHAR FOR BIT DATA)";
          break;
        case SIGLIST_1200:
          //"MMDBSYS.TicksPerQNote",
          list = new String[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2AUDIO)";
          break;
        case SIGLIST_1210:
          //"MMDBSYS.TicksPerSec",
          list = new String[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2AUDIO)";
          break;
        case SIGLIST_1220:
          //"MMDBSYS.Updater",
          list = new String[1][2];

          list[0][0] = "CHAR(8)";      list[0][1] = "handle(DB2IMAGE|DB2AUDIO|DB2VIDEO)";
          break;
        case SIGLIST_1230:
          //"MMDBSYS.UpdateTime",
          list = new String[1][2];

          list[0][0] = "TIMESTAMP";      list[0][1] = "handle(DB2IMAGE|DB2AUDIO|DB2VIDEO)";
          break;
        case SIGLIST_1240:
          //"MMDBSYS.Width"
          list = new String[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle(DB2IMAGE|DB2VIDEO)";
          break;
        case SIGLIST_1250:
          //"IDENTITY_VAL_LOCAL"
          list = new String[1][1];
          
          list[0][0] = "DECIMAL";
          break;          
        case SIGLIST_1260:
          //"DEREF"
          list = new String[1][2];
          
          list[0][0] = "instance";    list[0][1] = "REF";
          break;
          
        case SIGLIST_1270:
            //"XMLAGG"
            list = new String[3][];
            list[0] = new String[2];
            list[1] = new String[2];
            list[2] = new String[3];
            
            list[0][0] = "XML";    list[0][1] = "xml-expression";
            list[1][0] = "XML";    list[1][1] = "xml-expression ORDER BY sort-key";
            list[2][0] = "XML";    list[2][1] = "xml-expression ORDER BY sort-key1";  list[2][2] = "sort-key2";
            break;
          
        case SIGLIST_1280:
            //"XMLATTRIBUTES"
            list = new String[4][];
            list[0] = new String[2];
            list[1] = new String[2];
            list[2] = new String[3];
            list[3] = new String[4];
            
            list[0][0] = "XML(SEQUENCE)";    list[0][1] = "attribute-value";
            list[1][0] = "XML(SEQUENCE)";    list[1][1] = "attribute-value AS attribute-name";
            list[2][0] = "XML(SEQUENCE)";    list[2][1] = "attribute-value1 AS attribute-name1"; list[2][2] = "attribute-value2 AS attribute-name2";
            list[3][0] = "XML(SEQUENCE)";    list[3][1] = "attribute-value1";  list[3][2] = "attribute-value2";  list[3][3] = "attribute-value3";
            break;
          
        case SIGLIST_1290:
            // "XMLCONCAT"
            // "XMLDOCUMENT"
            list = new String[3][];
            list[0] = new String[2];
            list[1] = new String[3];
            list[2] = new String[4];

            list[0][0] = "XML";    list[0][1] = "xml-expression";
            list[1][0] = "XML";    list[1][1] = "xml-expression1";  list[1][2] = "xml-expression2";
            list[2][0] = "XML";    list[2][1] = "xml-expression1";  list[2][2] = "xml-expression2";  list[2][3] = "xml-expression3";
          break;
            
        case SIGLIST_1300:
            // "XMLELEMENT"
            // [wsdbu00075169] bgp 26Apr2006 - added more signatures for XMLELEMENT 
        	list = new String[15][];
            list[0] = new String[2];
            list[1] = new String[3];
            list[2] = new String[3];
            list[3] = new String[3];
            list[4] = new String[4];
            list[5] = new String[5];
            list[6] = new String[4];
            list[7] = new String[5];
            list[8] = new String[4];
            list[9] = new String[5];
            list[10] = new String[4];
            list[11] = new String[5];
            list[12] = new String[6];
            list[13] = new String[6];
            list[14] = new String[6];
            
            list[0][0] = "XML";    list[0][1] = "NAME element-name";
            list[1][0] = "XML";    list[1][1] = "NAME element-name";  list[1][2] = "xmlnamespaces-function";
            list[2][0] = "XML";    list[2][1] = "NAME element-name";  list[2][2] = "xmlattributes-function";
            list[3][0] = "XML";	   list[3][1] = "NAME element-name";  list[3][2] = "xml-element-content";
            list[4][0] = "XML";    list[4][1] = "NAME element-name";  list[4][2] = "xml-element-content1";    list[4][3] = "xml-element-content2";
            list[5][0] = "XML";    list[5][1] = "NAME element-name";  list[5][2] = "xml-element-content1";    list[5][3] = "xml-element-content2";    list[5][4] = "xml-element-content3";
            list[6][0] = "XML";    list[6][1] = "NAME element-name";  list[6][2] = "xmlnamespaces-function";  list[6][3] = "xml-element-content";
            list[7][0] = "XML";    list[7][1] = "NAME element-name";  list[7][2] = "xmlnamespaces-function";  list[7][3] = "xml-element-content1";    list[7][4] = "xml-element-content2";
            list[8][0] = "XML";    list[8][1] = "NAME element-name";  list[8][2] = "xmlattributes-function";  list[8][3] = "xml-element-content";
            list[9][0] = "XML";    list[9][1] = "NAME element-name";  list[9][2] = "xmlattributes-function";  list[9][3] = "xml-element-content1";    list[9][4] = "xml-element-content2";
            list[10][0] = "XML";   list[10][1] = "NAME element-name"; list[10][2] = "xmlnamespaces-function"; list[10][3] = "xmlattributes-function";
            list[11][0] = "XML";   list[11][1] = "NAME element-name"; list[11][2] = "xmlnamespaces-function"; list[11][3] = "xmlattributes-function"; list[11][4] = "xml-element-content";
            list[12][0] = "XML";   list[12][1] = "NAME element-name"; list[12][2] = "xmlnamespaces-function"; list[12][3] = "xmlattributes-function"; list[12][4] = "xml-element-content1";  list[12][5] = "xml-element-content2";
            list[13][0] = "XML";   list[13][1] = "NAME element-name"; list[13][2] = "xmlnamespaces-function"; list[13][3] = "xmlattributes-function"; list[13][4] = "xml-element-content1";  list[13][5] = "xml-element-content2 OPTION NULL ON NULL";
            list[14][0] = "XML";   list[14][1] = "NAME element-name"; list[14][2] = "xmlnamespaces-function"; list[14][3] = "xmlattributes-function"; list[14][4] = "xml-element-content1";  list[14][5] = "xml-element-content2 OPTION EMPTY ON NULL";
            break;          

        case SIGLIST_1310:
            // "XMLFOREST"   
            list = new String[6][];
            list[0] = new String[2];
            list[1] = new String[3];
            list[2] = new String[3];
            list[3] = new String[4];
            list[4] = new String[4];
            list[5] = new String[4];

            list[0][0] = "XML";    list[0][1] = "xml-element-content";
            list[1][0] = "XML";    list[1][1] = "xml-element-content1";  list[1][2] = "xml-element-content2";
            list[2][0] = "XML";    list[2][1] = "xml-element-content1 AS xml-element-name1";  list[2][2] = "xml-element-content2 AS xml-element-name2";
            list[3][0] = "XML";    list[3][1] = "xmlnamespaces-function";  list[3][2] = "xml-element-content1";  list[3][3] = "xml-element-content2";
            list[4][0] = "XML";    list[4][1] = "xmlnamespaces-function";  list[4][2] = "xml-element-content1";  list[4][3] = "xml-element-content2 OPTION EMPTY ON NULL";
            list[5][0] = "XML";    list[5][1] = "xmlnamespaces-function";  list[5][2] = "xml-element-content1";  list[5][3] = "xml-element-content2 OPTION NULL ON NULL";
            break;

        case SIGLIST_1320:
            // "XMLNAMESPACES"
            list = new String[4][];
            list[0] = new String[2];
            list[1] = new String[3];
            list[2] = new String[3];
            list[3] = new String[3];
            // The XMLNAMESPACES function is a pseudo-function that doesn't have a function return type
            list[0][0] = "*";    list[0][1] = "namespace-uri AS namespace-prefix";
            list[1][0] = "*";    list[1][1] = "namespace-uri1 AS namespace-prefix1";  list[1][2] = "namespace-uri2 AS namespace-prefix2";
            list[2][0] = "*";    list[2][1] = "namespace-uri1 AS namespace-prefix1";  list[2][2] = "DEFAULT namespace-uri2";
            list[3][0] = "*";    list[3][1] = "namespace-uri AS namespace-prefix";  list[3][2] = "NO DEFAULT";
            break;

        case SIGLIST_1330:
            // "XMLSERIALIZE"
            list = new String[5][];
            list[0] = new String[2];
            list[1] = new String[2];
            list[2] = new String[2];
            list[3] = new String[2];
            list[4] = new String[2];

            list[0][0] = "*";    list[0][1] = "xml-expression AS data-type";
            list[1][0] = "*";    list[1][1] = "CONTENT xml-expression AS data-type";
            list[2][0] = "*";    list[2][1] = "CONTENT xml-expression AS data-type INCLUDING XMLDECLARATION";
            list[3][0] = "*";    list[3][1] = "CONTENT xml-expression AS data-type EXCLUDING XMLDECLARATION";
            list[4][0] = "*";    list[4][1] = "CONTENT xml-expression AS data-type INCLUDING XMLDECLARATION VERSION '1.0'";
            break;

        case SIGLIST_1340:
            // "XMLCAST"   
            list = new String[3][];
            list[0] = new String[2];
            list[1] = new String[2];
            list[2] = new String[2];

            list[0][0] = "*";    list[0][1] = "expression AS data-type";
            list[1][0] = "*";    list[1][1] = "NULL AS data-type";
            list[2][0] = "*";    list[2][1] = "parameter-marker AS data-type";
            break;

        case SIGLIST_1350:
            // "XMLCOMMENT"
            // "XMLTEXT"
            list = new String[1][];
            list[0] = new String[2];

            list[0][0] = "XML";    list[0][1] = "string-expression";
            break;

        case SIGLIST_1360:
            // "XMLEXISTS"
            list = new String[4][];
            list[0] = new String[2];
            list[1] = new String[2];
            list[2] = new String[2];
            list[3] = new String[3];
            
            list[0][0] = "BOOLEAN";    list[0][1] = "xquery-expression-constant";
            list[1][0] = "BOOLEAN";    list[1][1] = "xquery-expression-constant PASSING xquery-variable-expression AS identifier";
            list[2][0] = "BOOLEAN";    list[2][1] = "xquery-expression-constant PASSING BY REF xquery-variable-expression AS identifier";
            list[3][0] = "BOOLEAN";    list[3][1] = "xquery-expression-constant PASSING BY REF xquery-variable-expression1 AS identifier1";  list[3][2] = "xquery-variable-expression2 AS identifier2";
            break;

        case SIGLIST_1370:
            // "XMLPARSE"
            list = new String[3][];
            list[0] = new String[2];
            list[1] = new String[2];
            list[2] = new String[2];

            list[0][0] = "XML";    list[0][1] = "DOCUMENT string-expression";
            list[1][0] = "XML";    list[1][1] = "DOCUMENT string-expression STRIP WHITESPACE";
            list[2][0] = "XML";    list[2][1] = "DOCUMENT string-expression PRESERVE WHITESPACE";
            break;

        case SIGLIST_1380:
            // "XMLPI"
            list = new String[2][];
            list[0] = new String[2];
            list[1] = new String[3];

            list[0][0] = "XML";    list[0][1] = "NAME identifier";
            list[1][0] = "XML";    list[1][1] = "NAME identifier";  list[1][2] = "string-expression";
            break;

        case SIGLIST_1390:
            // "XMLQUERY"
            list = new String[4][];
            list[0] = new String[2];
            list[1] = new String[2];
            list[2] = new String[2];
            list[3] = new String[3];
            
            list[0][0] = "XML";    list[0][1] = "xquery-expression-constant";
            list[1][0] = "XML";    list[1][1] = "xquery-expression-constant PASSING xquery-variable-expression AS identifier";
            list[2][0] = "XML";    list[2][1] = "xquery-expression-constant PASSING BY REF xquery-variable-expression AS identifier";
            list[3][0] = "XML";    list[3][1] = "xquery-expression-constant PASSING BY REF xquery-variable-expression1 AS identifier1";  list[3][2] = "xquery-variable-expression2 AS identifier2";
            break;

        case SIGLIST_1400:
            // "XMLTABLE"
            list = new String[7][];
            list[0] = new String[2];
            list[1] = new String[3];
            list[2] = new String[4];
            list[3] = new String[4];
            list[4] = new String[5];
            list[5] = new String[5];
            list[6] = new String[6];
            // This function returns a table expression
            list[0][0] = "*";    list[0][1] = "row-xquery-expression-constant COLUMNS column-name data-type";
            list[1][0] = "*";    list[1][1] = "row-xquery-expression-constant COLUMNS column-name1 data-type1";  list[1][2] = "column-name2 data-type2";
            list[2][0] = "*";    list[2][1] = "row-xquery-expression-constant COLUMNS column-name1 data-type1";  list[2][2] = "column-name2 data-type2";  list[2][3] = "column-name3 data-type3";
            list[3][0] = "*";    list[3][1] = "row-xquery-expression-constant PASSING xquery-variable-expression AS identifier COLUMNS column-name1 data-type1";  list[3][2] = "column-name2 data-type2";  list[3][3] = "column-name3 data-type3";
            list[4][0] = "*";    list[4][1] = "xmlnamespaces-function";  list[4][2] = "row-xquery-expression-constant PASSING xquery-variable-expression AS identifier COLUMNS column-name1 data-type1";  list[4][3] = "column-name2 data-type2";  list[4][4] = "column-name3 data-type3";
            list[5][0] = "*";    list[5][1] = "xmlnamespaces-function";  list[5][2] = "row-xquery-expression-constant PASSING BY REF xquery-variable-expression AS identifier COLUMNS column-name1 data-type1";  list[5][3] = "column-name2 data-type2";  list[5][4] = "column-name3 data-type3";
            list[6][0] = "*";    list[6][1] = "xmlnamespaces-function";  list[6][2] = "row-xquery-expression-constant PASSING xquery-variable-expression1 AS identifier1";  list[6][3] = "xquery-variable-expression2 AS identifier2 COLUMNS column-name1 data-type1";  list[6][4] = "column-name2 data-type2";  list[6][5] = "column-name3 data-type3";
            break;

        case SIGLIST_1410:
            // "XMLVALIDATE"   
            list = new String[9][];
            list[0] = new String[2];
            list[1] = new String[2];
            list[2] = new String[2];
            list[3] = new String[2];
            list[4] = new String[2];
            list[5] = new String[2];
            list[6] = new String[2];
            list[7] = new String[2];
            list[8] = new String[2];

            list[0][0] = "XML";    list[0][1] = "xml-expression";
            list[1][0] = "XML";    list[1][1] = "DOCUMENT xml-expression";
            list[2][0] = "XML";    list[2][1] = "DOCUMENT xml-expression ACCORDING TO XMLSCHEMA ID registered-xml-schema-name";
            list[3][0] = "XML";    list[3][1] = "DOCUMENT xml-expression ACCORDING TO XMLSCHEMA URI xml-uri";
            list[4][0] = "XML";    list[4][1] = "DOCUMENT xml-expression ACCORDING TO XMLSCHEMA NO NAMESPACE";
            list[5][0] = "XML";    list[5][1] = "DOCUMENT xml-expression ACCORDING TO XMLSCHEMA URI xml-uri1 LOCATION xml-uri2";
            list[6][0] = "XML";    list[6][1] = "DOCUMENT xml-expression ACCORDING TO XMLSCHEMA URI xml-uri1 LOCATION xml-uri2 ELEMENT xml-element-name";
            list[7][0] = "XML";    list[7][1] = "DOCUMENT xml-expression ACCORDING TO XMLSCHEMA URI xml-uri1 LOCATION xml-uri2 NAMESPACE xml-uri3 ELEMENT xml-element-name";
            list[8][0] = "XML";    list[8][1] = "DOCUMENT xml-expression ACCORDING TO XMLSCHEMA URI xml-uri1 LOCATION xml-uri2 NO NAMESPACE ELEMENT xml-element-name";
            break;

        } // end switch
    
        return list;
    }

    /**
     * Initializes the aggregate function information.
     */
    protected void initAggregateFunctions() {
        super.initAggregateFunctions();
        
        /* Create a mapping between the function names and their signature types. */
        Map tempMap = new HashMap();
        tempMap.put("AVG",            new Integer(SIGLIST_420));
        tempMap.put("CORR",           new Integer(SIGLIST_640));
        tempMap.put("CORRELATION",    new Integer(SIGLIST_640));
        tempMap.put("COUNT",          new Integer(SIGLIST_520));
        tempMap.put("COUNT_BIG",      new Integer(SIGLIST_500));
        tempMap.put("COVAR",          new Integer(SIGLIST_640));
        tempMap.put("COVARIANCE",     new Integer(SIGLIST_640));
        tempMap.put("GROUPING",       new Integer(SIGLIST_490));
        tempMap.put("MAX",            new Integer(SIGLIST_530));
        tempMap.put("MIN",            new Integer(SIGLIST_530));
        tempMap.put("REGR_AVGX",      new Integer(SIGLIST_640));
        tempMap.put("REGR_AVGY",      new Integer(SIGLIST_640));
        tempMap.put("REGR_COUNT",     new Integer(SIGLIST_650));
        tempMap.put("REGR_ICPT",      new Integer(SIGLIST_640));
        tempMap.put("REGR_INTERCEPT", new Integer(SIGLIST_640));
        tempMap.put("REGR_R2",        new Integer(SIGLIST_640));
        tempMap.put("REGR_SLOPE",     new Integer(SIGLIST_640));
        tempMap.put("REGR_SXX",       new Integer(SIGLIST_640));
        tempMap.put("REGR_SXY",       new Integer(SIGLIST_640));
        tempMap.put("REGR_SYY",       new Integer(SIGLIST_640));
        tempMap.put("STDDEV",         new Integer(SIGLIST_040));
        tempMap.put("SUM",            new Integer(SIGLIST_460));
        tempMap.put("VAR",            new Integer(SIGLIST_040));
        tempMap.put("VARIANCE",       new Integer(SIGLIST_040));
        
        /*  Create the list of function names by getting the map keys and sorting them. */
        Set keyset = tempMap.keySet();
        fAggregateFunctionNamesList.addAll(keyset);
        Collections.sort(fAggregateFunctionNamesList);
        
        /* Copy the signature data into the master signature map. */
        fFuncNameToSigListMap.putAll(tempMap);
    }
    
    /**
     * Initializes the conversion (cast) function information.
     */
    protected void initConversionFunctions() {
        super.initConversionFunctions();
        
        /* Create a mapping between the function names and their signature types. */
        Map tempMap = new HashMap();
        tempMap.put("BIGINT",           new Integer(SIGLIST_430));
        tempMap.put("BLOB",             new Integer(SIGLIST_620));
        tempMap.put("CHAR",             new Integer(SIGLIST_320));
        tempMap.put("CLOB",             new Integer(SIGLIST_630));
        tempMap.put("DATE",             new Integer(SIGLIST_010));
        tempMap.put("DBCLOB",           new Integer(SIGLIST_660));
        tempMap.put("DEC",              new Integer(SIGLIST_120));
        tempMap.put("DECIMAL",          new Integer(SIGLIST_120));
        tempMap.put("DOUBLE",           new Integer(SIGLIST_330));
        tempMap.put("DOUBLE_PRECISION", new Integer(SIGLIST_340));
        tempMap.put("FLOAT",            new Integer(SIGLIST_340));
        tempMap.put("GRAPHIC",          new Integer(SIGLIST_670));
        tempMap.put("INT",              new Integer(SIGLIST_440));
        tempMap.put("INTEGER",          new Integer(SIGLIST_440));
        tempMap.put("LONG_VARCHAR",     new Integer(SIGLIST_580));
        tempMap.put("LONG_VARGRAPHIC",  new Integer(SIGLIST_590));
        tempMap.put("REAL",             new Integer(SIGLIST_470));
        tempMap.put("SMALLINT",         new Integer(SIGLIST_450));
        tempMap.put("TIME",             new Integer(SIGLIST_400));
        tempMap.put("TIMESTAMP",        new Integer(SIGLIST_130));
        tempMap.put("VARCHAR",          new Integer(SIGLIST_680));
        tempMap.put("VARGRAPHIC",       new Integer(SIGLIST_690));        

        /*  Create the list of function names by getting the map keys and sorting them. */
        Set keyset = tempMap.keySet();
        fConversionFunctionNamesList.addAll(keyset);
        Collections.sort(fConversionFunctionNamesList);
        
        /* Copy the signature data into the master signature map. */
        fFuncNameToSigListMap.putAll(tempMap);
    }
    
    /**
     * Initializes the datalink function information.
     */
    protected void initDatalinkFunctions() {
        fDatalinkFunctionNamesList = new ArrayList();
        
        /* Create a mapping between the function names and their signature types. */
        Map tempMap = new HashMap();
        tempMap.put("DLCOMMENT",          new Integer(SIGLIST_380));
        tempMap.put("DLLINKTYPE",         new Integer(SIGLIST_380));
        //tempMap.put("DLNEWCOPY",          new Integer(SIGLIST_???));
        //tempMap.put("DLPREVIOUSCOPY",     new Integer(SIGLIST_???));
        //tempMap.put("DLREPLACECONTENT",   new Integer(SIGLIST_???));
        tempMap.put("DLURLCOMPLETE",      new Integer(SIGLIST_380));
        //tempMap.put("DLURLCOMPLETEONLY",  new Integer(SIGLIST_???));
        //tempMap.put("DLURLCOMPLETEWRITE", new Integer(SIGLIST_???));
        tempMap.put("DLURLPATH",          new Integer(SIGLIST_380));
        tempMap.put("DLURLPATHONLY",      new Integer(SIGLIST_380));
        //tempMap.put("DLURLPATHWRITE",     new Integer(SIGLIST_???));
        tempMap.put("DLURLSCHEME",        new Integer(SIGLIST_380));
        tempMap.put("DLURLSERVER",        new Integer(SIGLIST_380));
        tempMap.put("DLVALUE",            new Integer(SIGLIST_110));

        /*  Create the list of function names by getting the map keys and sorting them. */
        Set keyset = tempMap.keySet();
        fDatalinkFunctionNamesList.addAll(keyset);
        Collections.sort(fDatalinkFunctionNamesList);
        
        /* Copy the signature data into the master signature map. */
        fFuncNameToSigListMap.putAll(tempMap);
    }

    /**
     * Initializes the datetime (date, time, timestamp) function information.
     */
    protected void initDatetimeFunctions() {
        super.initDatetimeFunctions();
        
        /* Create a mapping between the function names and their signature types. */
        Map tempMap = new HashMap();
        tempMap.put("DAY",              new Integer(SIGLIST_020));
        tempMap.put("DAYOFWEEK",        new Integer(SIGLIST_180));
        tempMap.put("DAYOFWEEK_ISO",    new Integer(SIGLIST_180));
        tempMap.put("DAYOFYEAR",        new Integer(SIGLIST_180));
        tempMap.put("DAYNAME",          new Integer(SIGLIST_160));
        tempMap.put("DAYS",             new Integer(SIGLIST_180));
        tempMap.put("HOUR",             new Integer(SIGLIST_030));
        tempMap.put("JULIAN_DAY",       new Integer(SIGLIST_180));
        tempMap.put("MICROSECOND",      new Integer(SIGLIST_390));
        tempMap.put("MIDNIGHT_SECONDS", new Integer(SIGLIST_410));
        tempMap.put("MINUTE",           new Integer(SIGLIST_030));
        tempMap.put("MONTH",            new Integer(SIGLIST_020));
        tempMap.put("MONTHNAME",        new Integer(SIGLIST_160));
        tempMap.put("QUARTER",          new Integer(SIGLIST_180));
        tempMap.put("SECOND",           new Integer(SIGLIST_030));
        tempMap.put("TIMESTAMPDIFF",    new Integer(SIGLIST_190));
        //tempMap.put("TIMESTAMP_FORMAT", new Integer(SIGLIST_???));
        //tempMap.put("TO_CHAR",          new Integer(SIGLIST_???));
        //tempMap.put("TO_DATE",          new Integer(SIGLIST_???));
        tempMap.put("TIMESTAMP_ISO",    new Integer(SIGLIST_170));
        //tempMap.put("VARCHAR_FORMAT",   new Integer(SIGLIST_???));
        tempMap.put("WEEK",             new Integer(SIGLIST_180));
        tempMap.put("WEEK_ISO",         new Integer(SIGLIST_180));
        tempMap.put("YEAR",             new Integer(SIGLIST_020));

        /*  Create the list of function names by getting the map keys and sorting them. */
        Set keyset = tempMap.keySet();
        fDatetimeFunctionNamesList.addAll(keyset);
        Collections.sort(fDatetimeFunctionNamesList);
        
        /* Copy the signature data into the master signature map. */
        fFuncNameToSigListMap.putAll(tempMap);        
    }
    
    /**
     * Initializes the special DB2 function information.
     */
    protected void initDB2Functions() {
        fDB2FunctionNamesList = new ArrayList();
        
        /* Create a mapping between the function names and their signature types. */
        Map tempMap = new HashMap();
        tempMap.put("COALESCE",           new Integer(SIGLIST_600));
        tempMap.put("DEREF",              new Integer(SIGLIST_1260));
        tempMap.put("EVENT_MON_STATE",    new Integer(SIGLIST_360));
        tempMap.put("IDENTITY_VAL_LOCAL", new Integer(SIGLIST_1250));
        tempMap.put("HEX",                new Integer(SIGLIST_510));
        tempMap.put("LENGTH",             new Integer(SIGLIST_520));
        tempMap.put("NULLIF",             new Integer(SIGLIST_540));
        tempMap.put("RAISE_ERROR",        new Integer(SIGLIST_560));
        //tempMap.put("REC2XML",            new Integer(SIGLIST_???));
        tempMap.put("TABLE_NAME",         new Integer(SIGLIST_100));
        tempMap.put("TABLE_SCHEMA",       new Integer(SIGLIST_100));
        tempMap.put("TYPE_ID",            new Integer(SIGLIST_520));
        tempMap.put("TYPE_NAME",          new Integer(SIGLIST_510));
        tempMap.put("TYPE_SCHEMA",        new Integer(SIGLIST_510));
        tempMap.put("VALUE",              new Integer(SIGLIST_600));

        /*  Create the list of function names by getting the map keys and sorting them. */
        Set keyset = tempMap.keySet();
        fDB2FunctionNamesList.addAll(keyset);
        Collections.sort(fDB2FunctionNamesList);
        
        /* Copy the signature data into the master signature map. */
        fFuncNameToSigListMap.putAll(tempMap);
    }

    /**
     * Initializes the MMDB Extender function information.
     */
    protected void initMMDBExtenderFunctions() {
        fMMDBExtenderFunctionNamesList = new ArrayList();
        
        /* Create a mapping between the function names and their signature types. */
        Map tempMap = new HashMap();
        tempMap.put("MMDBSYS.ALIGNVALUE",        new Integer(SIGLIST_850));
        tempMap.put("MMDBSYS.ASPECTRATIO",       new Integer(SIGLIST_860));
        tempMap.put("MMDBSYS.BITSPERSAMPLE",     new Integer(SIGLIST_870));
        tempMap.put("MMDBSYS.BYTESPERSEC",       new Integer(SIGLIST_880));
        tempMap.put("MMDBSYS.COMMENT",           new Integer(SIGLIST_890));
        tempMap.put("MMDBSYS.COMPRESSTYPE",      new Integer(SIGLIST_900));
        tempMap.put("MMDBSYS.CONTENT",           new Integer(SIGLIST_910));
        tempMap.put("MMDBSYS.DB2AUDIO",          new Integer(SIGLIST_920));
        tempMap.put("MMDBSYS.DB2IMAGE",          new Integer(SIGLIST_930));
        tempMap.put("MMDBSYS.DB2VIDEO",          new Integer(SIGLIST_940));
        tempMap.put("MMDBSYS.DURATION",          new Integer(SIGLIST_950));
        tempMap.put("MMDBSYS.FILENAME",          new Integer(SIGLIST_960));
        tempMap.put("MMDBSYS.FINDINSTRUMENT",    new Integer(SIGLIST_970));
        tempMap.put("MMDBSYS.FINDTRACKNAME",     new Integer(SIGLIST_980));
        tempMap.put("MMDBSYS.FORMAT",            new Integer(SIGLIST_990));
        tempMap.put("MMDBSYS.FRAMERATE",         new Integer(SIGLIST_1000));
        tempMap.put("MMDBSYS.GETINSTRUMENTS",    new Integer(SIGLIST_1010));
        tempMap.put("MMDBSYS.GETTRACKNAMES",     new Integer(SIGLIST_1020));
        tempMap.put("MMDBSYS.HEIGHT",            new Integer(SIGLIST_1030));
        tempMap.put("MMDBSYS.IMPORTER",          new Integer(SIGLIST_1040));
        tempMap.put("MMDBSYS.IMPORTTIME",        new Integer(SIGLIST_1050));
        tempMap.put("MMDBSYS.MAXBYTESPERSEC",    new Integer(SIGLIST_1060));
        tempMap.put("MMDBSYS.NUMAUDIOTRACKS",    new Integer(SIGLIST_1070));
        tempMap.put("MMDBSYS.NUMCHANNELS",       new Integer(SIGLIST_1080));
        tempMap.put("MMDBSYS.NUMCOLORS",         new Integer(SIGLIST_1090));
        tempMap.put("MMDBSYS.NUMFRAMES",         new Integer(SIGLIST_1100));
        tempMap.put("MMDBSYS.NUMVIDEOTRACKS",    new Integer(SIGLIST_1110));
        tempMap.put("MMDBSYS.QBSCOREFROMNAME",   new Integer(SIGLIST_1120));
        tempMap.put("MMDBSYS.QBSCOREFROMSTR",    new Integer(SIGLIST_1130));
        tempMap.put("MMDBSYS.QBSCORETBFROMNAME", new Integer(SIGLIST_1140));
        tempMap.put("MMDBSYS.QBSCORETBFROMSTR",  new Integer(SIGLIST_1150));
        tempMap.put("MMDBSYS.REPLACE",           new Integer(SIGLIST_1160));
        tempMap.put("MMDBSYS.SAMPLINGRATE",      new Integer(SIGLIST_1170));
        tempMap.put("MMDBSYS.SIZE",              new Integer(SIGLIST_1180));
        tempMap.put("MMDBSYS.THUMBNAIL",         new Integer(SIGLIST_1190));
        tempMap.put("MMDBSYS.TICKSPERQNOTE",     new Integer(SIGLIST_1200));
        tempMap.put("MMDBSYS.TICKSPERSEC",       new Integer(SIGLIST_1210));
        tempMap.put("MMDBSYS.UPDATER",           new Integer(SIGLIST_1220));
        tempMap.put("MMDBSYS.UPDATETIME",        new Integer(SIGLIST_1230));
        tempMap.put("MMDBSYS.WIDTH",             new Integer(SIGLIST_1240));

        /*  Create the list of function names by getting the map keys and sorting them. */
        Set keyset = tempMap.keySet();
        fMMDBExtenderFunctionNamesList.addAll(keyset);
        Collections.sort(fMMDBExtenderFunctionNamesList);
        
        /* Copy the signature data into the master signature map. */
        fFuncNameToSigListMap.putAll(tempMap);
    }
    
    /**
     * Initializes the MQSeries function information.
     */
    protected void initMQExtenderFunctions() {
        fMQExtenderFunctionNamesList = new ArrayList();
        
        /* Create a mapping between the function names and their signature types. */
        Map tempMap = new HashMap();
        // TODO: add MQ function signatures

        /*  Create the list of function names by getting the map keys and sorting them. */
        Set keyset = tempMap.keySet();
        fMQExtenderFunctionNamesList.addAll(keyset);
        Collections.sort(fMQExtenderFunctionNamesList);
        
        /* Copy the signature data into the master signature map. */
        fFuncNameToSigListMap.putAll(tempMap);
    }

    /**
     * Initializes the numeric (math) function information.
     */
    protected void initNumericFunctions() {
        super.initNumericFunctions();
        
        /* Create a mapping between the function names and their signature types. */
        Map tempMap = new HashMap();
        tempMap.put("ABS",      new Integer(SIGLIST_000));
        tempMap.put("ABSVAL",   new Integer(SIGLIST_000));
        tempMap.put("ACOS",     new Integer(SIGLIST_040));
        tempMap.put("ASIN",     new Integer(SIGLIST_040));
        tempMap.put("ATAN",     new Integer(SIGLIST_040));
        //tempMap.put("ATANH",    new Integer(SIGLIST_???));
        tempMap.put("ATAN2",    new Integer(SIGLIST_050));
        tempMap.put("CEIL",     new Integer(SIGLIST_000));
        tempMap.put("CEILING",  new Integer(SIGLIST_000));
        tempMap.put("COS",      new Integer(SIGLIST_040));
        //tempMap.put("COSH",     new Integer(SIGLIST_???));
        tempMap.put("COT",      new Integer(SIGLIST_040));
        tempMap.put("DEGREES",  new Integer(SIGLIST_040));
        tempMap.put("DIGITS",   new Integer(SIGLIST_350));
        tempMap.put("EXP",      new Integer(SIGLIST_040));
        tempMap.put("FLOOR",    new Integer(SIGLIST_000));
        tempMap.put("LOG",      new Integer(SIGLIST_040));
        tempMap.put("LN",       new Integer(SIGLIST_040));
        tempMap.put("LOG10",    new Integer(SIGLIST_040));
        tempMap.put("MOD",      new Integer(SIGLIST_060));
        //tempMap.put("MULTIPLY_ALT", new Integer(SIGLIST_???));
        tempMap.put("POWER",    new Integer(SIGLIST_070));
        tempMap.put("RADIANS",  new Integer(SIGLIST_040));
        tempMap.put("RAND",     new Integer(SIGLIST_080));
        tempMap.put("ROUND",    new Integer(SIGLIST_150));
        tempMap.put("SIGN",     new Integer(SIGLIST_000));
        tempMap.put("SIN",      new Integer(SIGLIST_040));
        //tempMap.put("SINH",     new Integer(SIGLIST_???));
        tempMap.put("SQRT",     new Integer(SIGLIST_040));
        tempMap.put("TAN",      new Integer(SIGLIST_040));
        //tempMap.put("TANH",     new Integer(SIGLIST_???));
        tempMap.put("TRUNCATE", new Integer(SIGLIST_150));
        tempMap.put("TRUNC",    new Integer(SIGLIST_150));
        tempMap.put("VAR",      new Integer(SIGLIST_040));
        tempMap.put("VARIANCE", new Integer(SIGLIST_040));

        /*  Create the list of function names by getting the map keys and sorting them. */
        Set keyset = tempMap.keySet();
        fNumericFunctionNamesList.addAll(keyset);
        Collections.sort(fNumericFunctionNamesList);
        
        /* Copy the signature data into the master signature map. */
        fFuncNameToSigListMap.putAll(tempMap);
    }
    
    /**
     * Gets a list of names of scalar functions supported by the database.  The scalar 
     * functions are all the functions in the conversion, datetime, numeric, string, 
     * and XML categories.
     * 
     * @return the list of scalar functions
     */
    protected void initScalarFunctions() {
        /* Get all the scalar functions in the basic categories. */
        super.initScalarFunctions();

        /* Add function the names for categories added for this database. */
        DBVersionHelper versionHelper = getVersionHelper();
        
        if (versionHelper.isDB2_LUW() == true) {
            fScalarFunctionNamesList.addAll( getDatalinkFunctionNames() );
            fScalarFunctionNamesList.addAll( getMMDBExtenderFunctionNames() );
            fScalarFunctionNamesList.addAll( getTextExtenderFunctionNames() );
        }
        
        //fScalarFunctionNamesList.addAll( getMQExtenderFunctionNames() );
        fScalarFunctionNamesList.addAll( getDB2FunctionNames() );
        
        if ( (versionHelper.isDB2_LUW() && versionHelper.isAtLeast(8,2))
          || (versionHelper.isDB2_zOS() && versionHelper.isAtLeast(9))
           ) {
            fScalarFunctionNamesList.addAll( getSQLXMLFunctionNames() );
        }
                
        /* Sort the list, then remove duplicates. Note that the LinkedHashSet is
         * used because it maintains the insertion order of the elements. (A
         * regular HashSet would lose the sorted order.) */
        Collections.sort( fScalarFunctionNamesList );
        Set scalarFuncNameSet = new LinkedHashSet(fScalarFunctionNamesList);

        /* Put the function names back into the list.*/
        fScalarFunctionNamesList.clear();
        fScalarFunctionNamesList.addAll(scalarFuncNameSet);
    }
    
    /**
     * Initializes the special register information.
     */    
    protected void initSpecialRegisters() {
        super.initSpecialRegisters();
        
        /* Create a mapping between the function names and their signature types. */
        Map tempMap = new HashMap();
        /* NOTE: there are many special register names missing from this list. */
        tempMap.put("CURRENT DATE",                    new Integer(SIGLIST_710));
        tempMap.put("CURRENT DEFAULT TRANSFORM GROUP", new Integer(SIGLIST_710));
        tempMap.put("CURRENT DEGREE",                  new Integer(SIGLIST_710));
        tempMap.put("CURRENT EXPLAIN MODE",            new Integer(SIGLIST_710));
        tempMap.put("CURRENT EXPLAIN SNAPSHOT",        new Integer(SIGLIST_710));
        tempMap.put("CURRENT NODE",                    new Integer(SIGLIST_710));
        tempMap.put("CURRENT PATH",                    new Integer(SIGLIST_710));
        tempMap.put("CURRENT QUERY OPTIMIZATION",      new Integer(SIGLIST_710));
        tempMap.put("CURRENT REFRESH AGE",             new Integer(SIGLIST_710));
        tempMap.put("CURRENT SCHEMA",                  new Integer(SIGLIST_710));
        tempMap.put("CURRENT SERVER",                  new Integer(SIGLIST_710));
        tempMap.put("CURRENT TIME",                    new Integer(SIGLIST_710));
        tempMap.put("CURRENT TIMESTAMP",               new Integer(SIGLIST_710));
        tempMap.put("CURRENT TIMEZONE",                new Integer(SIGLIST_710));
        tempMap.put("USER",                            new Integer(SIGLIST_710));        

        /*  Create the list of function names by getting the map keys and sorting them. */
        Set keyset = tempMap.keySet();
        fSpecialRegisterNamesList.addAll(keyset);
        Collections.sort(fSpecialRegisterNamesList);
        
        /* Copy the signature data into the master signature map. */
        fFuncNameToSigListMap.putAll(tempMap);
    }
    
    /**
     * Initializes the SQL/XML function information.
     *
     * Subclasses should override this method to store the mapping of function names
     * to signatures in the master signature map and to initialize the list of 
     * function names.
     */    
    protected void initSQLXMLFunctions() {
        fSQLXMLFunctionNamesList = new ArrayList();
        
        /* Create a mapping between the function names and their signature types. */
        Map tempMap = new HashMap();
        
        /* Fill in the SQL/XML function list.  The list of XML functions depends on the 
         * database platform and version. */
        DBVersionHelper versionHelper = getVersionHelper();
        if (versionHelper.isDB2_LUW()) {
            if (versionHelper.isAtLeast(8,2)) {
                /* SQL/XML functions added in DB2 for LUW v8 */
                tempMap.put("XMLAGG",        new Integer(SIGLIST_1270));
                tempMap.put("XMLATTRIBUTES", new Integer(SIGLIST_1280));
                tempMap.put("XMLCONCAT",     new Integer(SIGLIST_1290));
                tempMap.put("XMLELEMENT",    new Integer(SIGLIST_1300));
                tempMap.put("XMLFOREST",     new Integer(SIGLIST_1310));
                tempMap.put("XMLNAMESPACES", new Integer(SIGLIST_1320));
                tempMap.put("XMLSERIALIZE",  new Integer(SIGLIST_1330));
            }
            if (versionHelper.isAtLeast(9)) {
                /* SQL/XML functions added in DB2 for LUW v9 */
                tempMap.put("XMLCAST",       new Integer(SIGLIST_1340));
                tempMap.put("XMLCOMMENT",    new Integer(SIGLIST_1350));
                tempMap.put("XMLDOCUMENT",   new Integer(SIGLIST_1290)); // same as XMLCONCAT
                //tempMap.put("XMLEXISTS",     new Integer(SIGLIST_1360));
                tempMap.put("XMLPARSE",      new Integer(SIGLIST_1370));
                tempMap.put("XMLPI",         new Integer(SIGLIST_1380));
                tempMap.put("XMLQUERY",      new Integer(SIGLIST_1390));
                tempMap.put("XMLTABLE",      new Integer(SIGLIST_1400));
                tempMap.put("XMLTEXT",       new Integer(SIGLIST_1350)); // same as XMLCOMMENT
                tempMap.put("XMLVALIDATE",   new Integer(SIGLIST_1410));
            }
        }
        else if (versionHelper.isDB2_zOS()) {
            if (versionHelper.isAtLeast(9)) {
                tempMap.put("XMLAGG",        new Integer(SIGLIST_1270));
                tempMap.put("XMLATTRIBUTES", new Integer(SIGLIST_1280));
                tempMap.put("XMLCOMMENT",    new Integer(SIGLIST_1350));
                tempMap.put("XMLCONCAT",     new Integer(SIGLIST_1290));
                tempMap.put("XMLDOCUMENT",   new Integer(SIGLIST_1290)); // same as XMLCONCAT
                tempMap.put("XMLELEMENT",    new Integer(SIGLIST_1300));
                //tempMap.put("XMLEXISTS",     new Integer(SIGLIST_1360));
                tempMap.put("XMLFOREST",     new Integer(SIGLIST_1310));
                tempMap.put("XMLNAMESPACES", new Integer(SIGLIST_1320));
                tempMap.put("XMLPARSE",      new Integer(SIGLIST_1370));
                tempMap.put("XMLPI",         new Integer(SIGLIST_1380));
                tempMap.put("XMLQUERY",      new Integer(SIGLIST_1390));
                tempMap.put("XMLSERIALIZE",  new Integer(SIGLIST_1330));
                tempMap.put("XMLTEXT",       new Integer(SIGLIST_1350)); // same as XMLCOMMENT
            }
        }
 
        /*  Create the list of function names by getting the map keys and sorting them. */
        Set keyset = tempMap.keySet();
        fSQLXMLFunctionNamesList.addAll(keyset);
        Collections.sort(fSQLXMLFunctionNamesList);
        
        /* Copy the signature data into the master signature map. */
        fFuncNameToSigListMap.putAll(tempMap);
    }
    
    /**
     * Initializes the string (text) function information.
     */
    protected void initStringFunctions() {
        super.initStringFunctions();
        
        /* Create a mapping between the function names and their signature types. */
        Map tempMap = new HashMap();
        tempMap.put("ASCII",           new Integer(SIGLIST_200));
        tempMap.put("CHR",             new Integer(SIGLIST_210));
        //tempMap.put("DECRYPT_BIN",     new Integer(SIGLIST_???));
        //tempMap.put("DECRYPT_CHAR",    new Integer(SIGLIST_???));
        tempMap.put("DIFFERENCE",      new Integer(SIGLIST_280));
        //tempMap.put("ENCRYPT",         new Integer(SIGLIST_???));
        tempMap.put("GENERATE_UNIQUE", new Integer(SIGLIST_090));
        //tempMap.put("GETHINT",         new Integer(SIGLIST_???));
        tempMap.put("INSERT",          new Integer(SIGLIST_220));        
        tempMap.put("LCASE",           new Integer(SIGLIST_240));
        tempMap.put("LEFT",            new Integer(SIGLIST_290));
        tempMap.put("LOCATE",          new Integer(SIGLIST_260));
        tempMap.put("LOWER",           new Integer(SIGLIST_250));
        tempMap.put("LTRIM",           new Integer(SIGLIST_230));
        tempMap.put("POSSTR",          new Integer(SIGLIST_570));
        tempMap.put("REPEAT",          new Integer(SIGLIST_290));
        tempMap.put("REPLACE",         new Integer(SIGLIST_310));
        tempMap.put("RIGHT",           new Integer(SIGLIST_290));
        tempMap.put("RTRIM",           new Integer(SIGLIST_230));
        tempMap.put("SOUNDEX",         new Integer(SIGLIST_270));
        tempMap.put("SPACE",           new Integer(SIGLIST_300));
        tempMap.put("SUBSTR",          new Integer(SIGLIST_610));
        tempMap.put("TRANSLATE",       new Integer(SIGLIST_140));
        tempMap.put("UCASE",           new Integer(SIGLIST_370));
        tempMap.put("UPPER",           new Integer(SIGLIST_370));

        /*  Create the list of function names by getting the map keys and sorting them. */
        Set keyset = tempMap.keySet();
        fStringFunctionNamesList.addAll(keyset);
        Collections.sort(fStringFunctionNamesList);
        
        /* Copy the signature data into the master signature map. */
        fFuncNameToSigListMap.putAll(tempMap);
    }
    
    /**
     * Initializes the Text Extender function information.
     */
    protected void initTextExtenderFunctions() {
        fTextExtenderFunctionNamesList = new ArrayList();
        
        /* Create a mapping between the function names and their signature types. */
        Map tempMap = new HashMap();
        tempMap.put("DB2TX.CCSID",            new Integer(SIGLIST_720));
        tempMap.put("DB2TX.CONTAINS",         new Integer(SIGLIST_730));
        tempMap.put("DB2TX.FILE",             new Integer(SIGLIST_740));
        tempMap.put("DB2TX.FORMAT",           new Integer(SIGLIST_750));
        tempMap.put("DB2TX.HANDLE",           new Integer(SIGLIST_760));
        tempMap.put("DB2TX.HANDLE_LIST",      new Integer(SIGLIST_770));
        tempMap.put("DB2TX.INIT_TEXT_HANDLE", new Integer(SIGLIST_780));
        tempMap.put("DB2TX.LANGUAGE",         new Integer(SIGLIST_790));
        tempMap.put("DB2TX.NO_OF_DOCUMENTS",  new Integer(SIGLIST_800));
        tempMap.put("DB2TX.NO_OF_MATCHES",    new Integer(SIGLIST_810));
        tempMap.put("DB2TX.RANK",             new Integer(SIGLIST_820));
        tempMap.put("DB2TX.REFINE",           new Integer(SIGLIST_830));
        tempMap.put("DB2TX.SEARCH_RESULT",    new Integer(SIGLIST_840));

        /*  Create the list of function names by getting the map keys and sorting them. */
        Set keyset = tempMap.keySet();
        fTextExtenderFunctionNamesList.addAll(keyset);
        Collections.sort(fTextExtenderFunctionNamesList);
        
        /* Copy the signature data into the master signature map. */
        fFuncNameToSigListMap.putAll(tempMap);
    }
    
    /**
     * Initializes the user-defined function information.
     */
    protected void initUserDefinedFunctions() {
        super.initUserDefinedFunctions();
    }

   
} // end class
 