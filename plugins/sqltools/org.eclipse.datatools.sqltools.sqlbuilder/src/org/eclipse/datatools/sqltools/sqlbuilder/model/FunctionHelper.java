/*******************************************************************************
 * Copyright © 2005, 2007IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;


/**
 * This class provides helpful services for dealing with SQL functions.  The fields and
 * methods in this class are generic for databases.  Subclasses provide database-specific
 * services.
 *  
 * @author bpayton
 */
public class FunctionHelper {

    /* Define UI strings (labels) for the function categories.  Database-specific helpers can provide
     * additional categories. */
    
    /** All functions category label. */
    public static final String CAT_LABEL_ALL_FUNCTIONS          = Messages._UI_FCN_ALL;
    /** Aggregate (AKA column, grouping, summary) functions category label. */
    public static final String CAT_LABEL_AGGREGATE_FUNCTIONS       = Messages._UI_FCN_COLUMN;
    /** Conversion functions category label. */
    public static final String CAT_LABEL_CONVERSION_FUNCTIONS   = Messages._UI_FCN_CONVERSION;
    /** Date, time, timestamp functions category label. */
    public static final String CAT_LABEL_DATETIME_FUNCTIONS    = Messages._UI_FCN_DATE_TIME;
    /** Math functions category label. */
    public static final String CAT_LABEL_NUMERIC_FUNCTIONS         = Messages._UI_FCN_MATH;
    /** Scalar functions category label. */
    public static final String CAT_LABEL_SCALAR_FUNCTIONS       = Messages._UI_FCN_SCALAR;
    /** Special Registers category label. */
    public static final String CAT_LABEL_SPECIAL_REGISTERS      = Messages._UI_FCN_SPECIAL_REG;
    /** Text functions category label. */
    public static final String CAT_LABEL_STRING_FUNCTIONS         = Messages._UI_FCN_TEXT;
    /** User-Defined Functions (UDF) category label. */
    public static final String CAT_LABEL_USER_DEFINED_FUNCTIONS = Messages._UI_FCN_UDF;

    /** "All functions" label should be omitted from category list. */
    public static final int ALL_LOCATION_OMITTED = 0;
    /** "All functions" label should appear at top of category list. */
    public static final int ALL_LOCATION_TOP = 1;
    /** "All functions" label should appear at bottom of category list. */
    public static final int ALL_LOCATION_BOTTOM = 2;
    /** "All functions" label should appear sorted with other category labels in the
     * category list. */
    public static final int ALL_LOCATION_SORTED = 3;
    
    /** 
     * A static <code>Map</code> used to associate function helpers with databases.
     */
    private static Map gDBToFuncHelperMap = Collections.synchronizedMap( new HashMap() );

    /** The database with which this function helper is associated. */
    private Database fDB;
    /** A DB version helper to helper determine the database product and version. */
    private DBVersionHelper fVersionHelper;
    
    /* Define list of functions names in each category.  Subclasses can add additional lists. */ 
    
    /** All built-in function names. */
    protected List fAllBuiltinFunctionNamesList;
    /** Aggregate (AKA column, grouping, summary) function names. */
    protected List fAggregateFunctionNamesList;
    /** Conversion (Cast) function names. */
    protected List fConversionFunctionNamesList;
    /** Date, time, and timestamp function names. */
    protected List fDatetimeFunctionNamesList;
    /** Numeric function names. */
    protected List fNumericFunctionNamesList;
    /** Scalar function names. */
    protected List fScalarFunctionNamesList;
    /** Special register names.  (These act as functions but are not followed by parens.) */
    protected List fSpecialRegisterNamesList;
    /** String processing function names. */
    protected List fStringFunctionNamesList;
    /** User-defined function (UDF) names. */
    protected List fUserDefinedFunctionNamesList;
    /** User-defined function (UDF) objects from the database. */
    protected List fUserDefinedFunctionObjectsList;

    /** Map of function names to signature lists. */
    protected Map fFuncNameToSigListMap;    

    /* Define indexes that we will use to describe function signatures lists.
     * The same signature list can be used with more than one function when their
     * signatures are the same. */
    protected static final int SIGLIST_000 = 0;
    protected static final int SIGLIST_010 = 10;
    protected static final int SIGLIST_020 = 20;
    protected static final int SIGLIST_030 = 30;
    protected static final int SIGLIST_040 = 40;
    protected static final int SIGLIST_050 = 50;
    protected static final int SIGLIST_060 = 60;
    protected static final int SIGLIST_070 = 70;
    protected static final int SIGLIST_080 = 80;
    protected static final int SIGLIST_090 = 90;
    protected static final int SIGLIST_100 = 100;
    protected static final int SIGLIST_110 = 110;
    protected static final int SIGLIST_120 = 120;
    protected static final int SIGLIST_130 = 130;
    protected static final int SIGLIST_140 = 140;
    protected static final int SIGLIST_150 = 150;
    protected static final int SIGLIST_160 = 160;
    protected static final int SIGLIST_170 = 170;
    protected static final int SIGLIST_180 = 180;
    protected static final int SIGLIST_190 = 190;
    protected static final int SIGLIST_200 = 200;
    protected static final int SIGLIST_210 = 210;
    protected static final int SIGLIST_220 = 220;
    protected static final int SIGLIST_230 = 230;
    protected static final int SIGLIST_240 = 240;
    protected static final int SIGLIST_250 = 250;
    protected static final int SIGLIST_260 = 260;
    protected static final int SIGLIST_270 = 270;
    protected static final int SIGLIST_280 = 280;
    protected static final int SIGLIST_290 = 290;
    protected static final int SIGLIST_300 = 300;
    protected static final int SIGLIST_310 = 310;
    protected static final int SIGLIST_320 = 320;
    protected static final int SIGLIST_330 = 330;
    protected static final int SIGLIST_340 = 340;
    protected static final int SIGLIST_350 = 350;
    protected static final int SIGLIST_360 = 360;
    protected static final int SIGLIST_370 = 370;
    protected static final int SIGLIST_380 = 380;
    protected static final int SIGLIST_390 = 390;
    protected static final int SIGLIST_400 = 400;
    protected static final int SIGLIST_410 = 410;
    protected static final int SIGLIST_420 = 420;
    protected static final int SIGLIST_430 = 430;
    protected static final int SIGLIST_440 = 440;
    protected static final int SIGLIST_450 = 450;
    protected static final int SIGLIST_460 = 460;
    protected static final int SIGLIST_470 = 470;
    protected static final int SIGLIST_480 = 480;
    protected static final int SIGLIST_490 = 490;
    protected static final int SIGLIST_500 = 500;
    protected static final int SIGLIST_510 = 510;
    protected static final int SIGLIST_520 = 520;
    protected static final int SIGLIST_530 = 530;
    protected static final int SIGLIST_540 = 540;
    protected static final int SIGLIST_550 = 550;
    protected static final int SIGLIST_560 = 560;
    protected static final int SIGLIST_570 = 570;
    protected static final int SIGLIST_580 = 580;
    protected static final int SIGLIST_590 = 590;
    protected static final int SIGLIST_600 = 600;
    protected static final int SIGLIST_610 = 610;
    protected static final int SIGLIST_620 = 620;
    protected static final int SIGLIST_630 = 630;
    protected static final int SIGLIST_640 = 640;
    protected static final int SIGLIST_650 = 650;
    protected static final int SIGLIST_660 = 660;
    protected static final int SIGLIST_670 = 670;
    protected static final int SIGLIST_680 = 680;
    protected static final int SIGLIST_690 = 690;
    protected static final int SIGLIST_700 = 700;
    protected static final int SIGLIST_710 = 710;
    protected static final int SIGLIST_720 = 720;
    protected static final int SIGLIST_730 = 730;
    protected static final int SIGLIST_740 = 740;
    protected static final int SIGLIST_750 = 750;
    protected static final int SIGLIST_760 = 760;
    protected static final int SIGLIST_770 = 770;
    protected static final int SIGLIST_780 = 780;
    protected static final int SIGLIST_790 = 790;
    protected static final int SIGLIST_800 = 800;
    protected static final int SIGLIST_810 = 810;
    protected static final int SIGLIST_820 = 820;
    protected static final int SIGLIST_830 = 830;
    protected static final int SIGLIST_840 = 840;
    protected static final int SIGLIST_850 = 850;
    protected static final int SIGLIST_860 = 860;
    protected static final int SIGLIST_870 = 870;
    protected static final int SIGLIST_880 = 880;
    protected static final int SIGLIST_890 = 890;
    protected static final int SIGLIST_900 = 900;
    protected static final int SIGLIST_910 = 910;
    protected static final int SIGLIST_920 = 920;
    protected static final int SIGLIST_930 = 930;
    protected static final int SIGLIST_940 = 940;
    protected static final int SIGLIST_950 = 950;
    protected static final int SIGLIST_960 = 960;
    protected static final int SIGLIST_970 = 970;
    protected static final int SIGLIST_980 = 980;
    protected static final int SIGLIST_990 = 990;
    protected static final int SIGLIST_1000 = 1000;
    protected static final int SIGLIST_1010 = 1010;
    protected static final int SIGLIST_1020 = 1020;
    protected static final int SIGLIST_1030 = 1030;
    protected static final int SIGLIST_1040 = 1040;
    protected static final int SIGLIST_1050 = 1050;
    protected static final int SIGLIST_1060 = 1060;
    protected static final int SIGLIST_1070 = 1070;
    protected static final int SIGLIST_1080 = 1080;
    protected static final int SIGLIST_1090 = 1090;
    protected static final int SIGLIST_1100 = 1100;
    protected static final int SIGLIST_1110 = 1110;
    protected static final int SIGLIST_1120 = 1120;
    protected static final int SIGLIST_1130 = 1130;
    protected static final int SIGLIST_1140 = 1140;
    protected static final int SIGLIST_1150 = 1150;
    protected static final int SIGLIST_1160 = 1160;
    protected static final int SIGLIST_1170 = 1170;
    protected static final int SIGLIST_1180 = 1180;
    protected static final int SIGLIST_1190 = 1190;
    protected static final int SIGLIST_1200 = 1200;
    protected static final int SIGLIST_1210 = 1210;
    protected static final int SIGLIST_1220 = 1220;
    protected static final int SIGLIST_1230 = 1230;
    protected static final int SIGLIST_1240 = 1240;
    protected static final int SIGLIST_1250 = 1250;
    protected static final int SIGLIST_1260 = 1260;
    protected static final int SIGLIST_1270 = 1270;
    protected static final int SIGLIST_1280 = 1280;
    protected static final int SIGLIST_1290 = 1290;
    protected static final int SIGLIST_1300 = 1300;
    protected static final int SIGLIST_1310 = 1310;
    protected static final int SIGLIST_1320 = 1320;
    protected static final int SIGLIST_1330 = 1330;
    protected static final int SIGLIST_1340 = 1340;
    protected static final int SIGLIST_1350 = 1350;
    protected static final int SIGLIST_1360 = 1360;
    protected static final int SIGLIST_1370 = 1370;
    protected static final int SIGLIST_1380 = 1380;
    protected static final int SIGLIST_1390 = 1390;
    protected static final int SIGLIST_1400 = 1400;
    protected static final int SIGLIST_1410 = 1410;    

    /**
     * Gets an instance of <code>FunctionHelper</code> that is the best match for the
     * database represented by the given <code>Database</code> object.  
     * 
     * @return the function helper appropriate for the given database or null if an
     * appropriate function helper can't be found
     */
    public static FunctionHelper getInstance( Database db ) {
        FunctionHelper funcHelper = null;
        
        if (db != null) {
            /* See if we've already initialized a function helper for the given DB. 
             * If yes, return it. */
            synchronized( gDBToFuncHelperMap ) {
                funcHelper = (FunctionHelper) gDBToFuncHelperMap.get( db );
                
                /* If we don't already have a function helper for the database,
                 * find the best function helper match for the database, intialize it,
                 * and return it. */
                if (funcHelper == null) {
                    DBVersionHelper versionHelper = new DBVersionHelper( db );
                    if (versionHelper.isDB2()) {
                        funcHelper = new FunctionHelperDB2( db );
                    }
                    /* Associate the function helper with the DB so we can get it next time . */
                    if (funcHelper != null) {
                        gDBToFuncHelperMap.put( db, funcHelper );
                    }
                }
            }
        }
            
        return funcHelper;
    }

    /**
     * Returns a new array which is formed by appending the second given array to the 
     * first given array.
     * 
     * @param strArray1 the first string array to join
     * @param strArray2 the second string array to join
     * @return the new array
     */
    protected static final String[] joinArrays(String strArray1[], String strArray2[]) {
        String strArray3[];
        
        int i = 0;
        strArray3 = new String[strArray1.length + strArray2.length];
        for (i = 0; i < strArray1.length; i++) {
            strArray3[i] = strArray1[i];
        }
        for (int j = 0; j < strArray2.length; j++) {
            strArray3[i] = strArray2[j];
            i++;
        }
        return strArray3;
    }

    /**
     * Returns a new array which merges, sorts, and removes duplicates from the elements of 
     * the arrays contained in the given array (of arrays).
     * 
     * @param arrayArray the array containing the set of arrays to merge
     * @return the new merged, sorted array
     */
    protected static final String[] mergeSortArrays(String[][] arrayArray) {
        String[] mergedArray = null;

        int arrayCount = arrayArray.length;
        if (arrayCount > 0) {
            
            /* Combine all the input arrays into one list. */
            String[] tempArray = arrayArray[0];
            List tempList = Arrays.asList(tempArray);
            
            /* The array returned as the result of the asList method is not an ArrayList,
             * and it doesn't support the "addAll" method.  So create a new ArrayList so
             * we can do the merge. */
            List mergedList = new ArrayList( tempList );

            for (int i = 1; i < arrayCount; i++) {
                tempArray = arrayArray[i];
                tempList = Arrays.asList(tempArray);
                mergedList.addAll(tempList);
            }

            /* Sort the list, then remove duplicates. Note that the LinkedHashSet is
             * used because it maintains the insertion order of the elements. (A
             * regular HashSet would lose the sorted order.) */
            Collections.sort(mergedList);
            Set mergedListNoDups = new LinkedHashSet(mergedList);

            /* Create the result array from the list. */
            int listLen = mergedListNoDups.size();
            mergedArray = (String[]) mergedListNoDups.toArray( new String[listLen] );
        }

        return mergedArray;
    }

    /**
     * Constructs an instance of this class.  This is the default constructor.
     * This constructor is protected to ensure that this class is constructed with a
     * database object.
     */
    protected FunctionHelper() {
        // do nothing
    }
    
    /**
     * Constructs an instance of this class with an association to the given database.
     * 
     * @param db the <code>Database</code> for which function help is needed
     */
    public FunctionHelper( Database db ) {
        fDB = db;
        fVersionHelper = new DBVersionHelper( db );
        initialize();
    }    
    
    /**
     * Gets the <code>Database</code> object with which this helper is associated.
     * 
     * @return the database for which function help is needed
     */
    public Database getDatabase() {
        return fDB;
    }
    
    /**
     * Gets a list of all function categories supported by the database.  The list is
     * sorted so it will look better when the labels are translated.  A special category
     * label "All Categories" can optionally be added to the list, at one of three positions
     * in the list (top, bottom, or sorted).  See ALL_LOCATION_xxx constants defined in
     * this class.
     * 
     * @param allCatLocation indicates if, and where, the "All categories" category
     * label should appear in the category list 
     * @return the list of category labels
     */
    public List getFunctionCategories( int allCatLocation ) {
        List catList = new ArrayList();
        
        /* Build up the list of categories and sort it. */
        catList.addAll( getBuiltinFunctionCategories() );
        catList.add( CAT_LABEL_USER_DEFINED_FUNCTIONS );
        if (allCatLocation == ALL_LOCATION_SORTED) {
            catList.add( CAT_LABEL_ALL_FUNCTIONS );
        }
        Collections.sort( catList );
        
        /* Add the "All Functions" label at the front of the list if needed. */
        if (allCatLocation == ALL_LOCATION_TOP) {
            catList.add(0, CAT_LABEL_ALL_FUNCTIONS);
        }
        else if (allCatLocation == ALL_LOCATION_BOTTOM) {
            catList.add( CAT_LABEL_ALL_FUNCTIONS );
        }
        
        return catList;
    }
    
    /**
     * Gets a list of labels for the built-in function categories supported by the 
     * associated database.
     * 
     * Subclasses can override this method to provide additional or different 
     * categories.
     * 
     * @return the list of built-in function categories
     */
    protected List getBuiltinFunctionCategories() {
        List catList = new ArrayList();

        catList.add(CAT_LABEL_AGGREGATE_FUNCTIONS);
        catList.add(CAT_LABEL_CONVERSION_FUNCTIONS);
        catList.add(CAT_LABEL_DATETIME_FUNCTIONS);
        catList.add(CAT_LABEL_NUMERIC_FUNCTIONS);
        catList.add(CAT_LABEL_SCALAR_FUNCTIONS);
        catList.add(CAT_LABEL_SPECIAL_REGISTERS);
        catList.add(CAT_LABEL_STRING_FUNCTIONS);
        
        return catList;
    }

    /**
     * Gets a list of function names supported by the associated database in the 
     * given function category.
     * 
     * Subclasses can override this method to support additional categories.
     *  
     * @param category the function category for which functions are needed
     * @return the list of the function names, or an empty list if the category is not
     * supported by the database
     */
    public List getFunctionNames( String category ) {
        List funcNames = new ArrayList();

        if (category.equals(CAT_LABEL_ALL_FUNCTIONS)) {
            funcNames = getAllBuiltinFunctionNames();
        }
        else if (category.equals(CAT_LABEL_AGGREGATE_FUNCTIONS)) {
            funcNames = getAggregateFunctionNames();
        }
        else if (category.equals(CAT_LABEL_CONVERSION_FUNCTIONS)) {
            funcNames = getConversionFunctionNames();
        }
        else if (category.equals(CAT_LABEL_DATETIME_FUNCTIONS)) {
            funcNames = getDatetimeFunctionNames();
        }
        else if (category.equals(CAT_LABEL_NUMERIC_FUNCTIONS)) {
            funcNames = getNumericFunctionNames();
        }
        else if (category.equals(CAT_LABEL_SCALAR_FUNCTIONS)) {
            funcNames = getScalarFunctionNames();
        }
        else if (category.equals(CAT_LABEL_SPECIAL_REGISTERS)) {
            funcNames = getSpecialRegisterNames();
        }
        else if (category.equals(CAT_LABEL_STRING_FUNCTIONS)) {
            funcNames = getStringFunctionNames();
        }
        else if(category.equals(CAT_LABEL_USER_DEFINED_FUNCTIONS)) {
            funcNames = getUserDefinedFunctionNames();
        }

        return funcNames;
    }

    /**
     * Gets a list of names of all the built-in functions supported by the database.  
     * The list is sorted with no duplicates.
     * 
     * @return the list of all built-in functions
     */
    public List getAllBuiltinFunctionNames() {
        if (fAllBuiltinFunctionNamesList == null) {
            initAllBuiltinFunctions();
        }
        
        return fAllBuiltinFunctionNamesList;
    }
    
    /**
     * Gets a list of names of the aggregate (AKA column, grouping, summary) functions
     * supported by the database.
     * 
     * @return the list of aggregate functions 
     */
    public List getAggregateFunctionNames() {
        if (fAggregateFunctionNamesList == null) {
            initAggregateFunctions();
        }
        return fAggregateFunctionNamesList;
    }
    
    /**
     * Gets a list of names of the conversion (cast) functions supported by the database.
     * 
     * @return the list of conversion functions
     */
    public List getConversionFunctionNames() {
        if (fConversionFunctionNamesList == null) {
            initConversionFunctions();
        }
        return fConversionFunctionNamesList;
    }

    /**
     * Gets a list of the names of datetime (date, time, timestamp) functions supported 
     * by the database.
     * 
     * @return the list of datetime functions
     */
    public List getDatetimeFunctionNames() {
        if (fDatetimeFunctionNamesList == null) {
            initDatetimeFunctions();
        }
        return fDatetimeFunctionNamesList;        
    }

    /**
     * Gets a list of the names of numeric (math) functions supported by the database.
     * 
     * @return the list of numeric functions
     */
    public List getNumericFunctionNames() {
        if (fNumericFunctionNamesList == null) {
            initNumericFunctions();
        }
        return fNumericFunctionNamesList;                
    }

    /**
     * Gets a list of names of scalar functions supported by the database.  The scalar 
     * functions are all the functions in the conversion, datetime, numeric, and string 
     * categories.  The list is sorted with no duplicates.
     *
     * @return the list of scalar functions
     */
    public List getScalarFunctionNames() {
        if (fScalarFunctionNamesList == null) {
            initScalarFunctions();
        }
        
        return fScalarFunctionNamesList;
    }
    
    /**
     * Gets a list of special registers supported by the database.
     * 
     * @return the list of special registers
     */
    public List getSpecialRegisterNames() {
        if (fSpecialRegisterNamesList == null) {
            initSpecialRegisters();
        }
        return fSpecialRegisterNamesList;                                
    }
    
    /**
     * Gets a list of string (text) functions supported by the database.
     * 
     * @return the list of string functions
     */
    public List getStringFunctionNames() {
        if (fStringFunctionNamesList == null) {
            initStringFunctions();
        }
        return fStringFunctionNamesList;                        
    }
    
    /**
     * Gets a list of user-defined functions supported by the database.
     * 
     * @return the list
     */
    public List getUserDefinedFunctionNames() {
        if (fUserDefinedFunctionNamesList == null) {
            initUserDefinedFunctions();
        }
        return fUserDefinedFunctionNamesList;
    }
    
    /**
     * Gets an array of arrays (ie, a table) containing the function signatures specified 
     * by the given named function.  Each "row" in the "table" describes a
     * function signature.  In each row, the first "column" contains the return type, 
     * and the remaning columns describe the input parameter types.  
     *
     * It shouldn't be necessary for subclasses to override this method.
     *
     * @param functionName the name of the function for which the parameter formats are wanted
     * @return the table of function signatures
     */
    public String[][] getFunctionSignatures(String functionName) {
        String[][] sigArray = new String[0][0];
        
        /* Try to get the function signature index from the signature map.  All built-in
         * functions should have their signature index in the map.
         */
        Integer sigListIndex = (Integer) fFuncNameToSigListMap.get(functionName);
        if (sigListIndex != null) {
            int sigListIndexInt = sigListIndex.intValue();
            sigArray = getFunctionSignatures( sigListIndexInt );
        }
        /* If the function name is not in the map, it might be a user-defined
         * function. */
        else {
            sigArray = getUserDefinedFunctionSignatures(functionName);
        }
        
        return sigArray;
    }

    /** 
     * Gets an array of arrays (ie, a table) containing the function signatures specified 
     * by the given function signature list number.  Each "row" in the "table" 
     * describes a function signature.  In each row, the first "column" contains the 
     * return type, and the remaning columns describe the input parameter types.  
     *
     * Subclasses should override this function to fill in the function signatures  
     * appropriate for their associated database. 
     *  
     * @param sigListNum the index of the signature list wanted
     * @return the signature table 
     */
    protected String[][] getFunctionSignatures( int sigListNum ) {
        String[][] list = new String[0][0];
        
        switch( sigListNum ) {
            // subclasses should implement this
        }
        
        return list;
    }

    /**
     * Gets an array of arrays (ie, a table) containing the function signatures specified 
     * by the given user-defined function name.  Each "row" in the "table" 
     * describes a function signature.  In each row, the first "column" contains the 
     * return type, and the remaning columns describe the input parameter types.
     * 
     * @param functionName the function for which the signatures are wanted
     * @return the function signatures table
     */
    protected String[][] getUserDefinedFunctionSignatures( String functionName ) {
        String[][] list = new String[0][0];
        
        /* Find out if the UDF name is in our list of UDF names. If so, get its signatures. */
        int listIndex = fUserDefinedFunctionNamesList.indexOf(functionName);
        if (listIndex >= 0) {
            //UserDefinedFunction udf = (UserDefinedFunction) fUserDefinedFunctionObjectsList.get(listIndex);
        }
        /* NOTE:  the commented out code below implies that the same UDF with different
         * signatures appear as multiple UDFs. So might have multiple of the same name
         * in the UDF names list. (Or handle this some other way.) */

        
//        int totalPrototypes = 0;
//        int totalPrameters = 0;
//        for (int i = 0; i < fUserDefinedFunctionList.size(); i++) {
//            UserDefinedFunction lUDF = (UserDefinedFunction) fUserDefinedFunctionList.elementAt(i);
//            if (functionName.equals(lUDF.getSchema().toString().concat(".").concat(lUDF.getName().toString()))) {
//                totalPrameters = totalPrameters > lUDF.getParameters().size() ? totalPrameters : lUDF.getParameters().size();
//                totalPrototypes++;
//            }
//        }
//        if (totalPrototypes <= 0) {
//            list = new String[1][2];
//            list[0][0] = "???";
//            list[0][1] = "???";
//        }
//        else {
//            list = new String[totalPrototypes][totalPrameters + 1];
//            int lPro = 0;
//            for (int i = 0; i < udfList.size(); i++) {
//                int lParam = 0;
//                
//                UserDefinedFunction lUDF = (UserDefinedFunction) udfList.elementAt(i);
//                if (functionName.equals(lUDF.getSchema().toString().concat(".").concat(lUDF.getName().toString()))) {
//                    list[lPro][lParam] = lUDF.getReturnScaler().getName();
//                    lParam++;
//                    Iterator iterator = lUDF.getParameters().iterator();
//                    while (iterator.hasNext()) {
//                        Object obj = iterator.next();
//                        if (obj instanceof Parameter) {
//                            Parameter lParameter = (Parameter) obj;
//                            list[lPro][lParam] = lParameter.getDataType().getName();
//                            lParam++;
//                        }
//                    }
//                    lPro++;
//                }
//            }
//        }
        return list;
    }
    
    /**
     * Gets whether or not the given function allows "*" as a parameter (ie, COUNT(*) ).
     * 
     * @param funcName the name of the function to check
     * @return true when the function supports the "*" parameter format, otherwise false
     */
    public boolean getIsFunctionAllowingStar(String funcName) {
        boolean isStarFunc = false;
        
        if (funcName.equalsIgnoreCase("COUNT") || funcName.equalsIgnoreCase("COUNT_BIG")) {
            isStarFunc = true;
        }
        
        return isStarFunc;
    }

    /**
     * Gets whether or not the given <code>Schema</code> object represents a
     * system schema (ie, SYSIBM, SYSCAT).
     * 
     * Subclasses should override this method if the system schemas do not 
     * start with "SYS".
     * 
     * @param schema the schema to check
     * @return true when the schema is a system schema, otherwise false
     */
    protected boolean getIsSystemSchema( Schema schema ) {
        boolean isSysSchema = false;
        
        String schemaName = schema.getName().toUpperCase();
        if (schemaName.startsWith("SYS")) {
            isSysSchema = true;
        }
        
        return isSysSchema;
    }
    
    /** 
     * Gets the <code>VendorHelper</code> associated with the database. 
     * 
     * @return the vendor helper for the database
     */
    protected DBVersionHelper getVersionHelper() {
        return fVersionHelper;
    }
    
    /**
     * Intializes the data structures used by this class.  Subclasses may override this
     * method if necessary.
     */
    protected void initialize() {
        /* Initialize the master signature map. */
        fFuncNameToSigListMap = new HashMap();
    }

    /**
     * Initializes all builtin function information for the database.  The list of all
     * function names is created by combining the lists of names in each function
     * category.  The list is sorted with no duplicates.  Note that we don't include 
     * user-defined functions in the list to avoid loading all the database schemas.  The
     * UDF name list is populated only when getUserDefinedFunctionNames is called.
     *   
     * Subclasses shouldn't have to override this method.
     */
    protected void initAllBuiltinFunctions() {
        fAllBuiltinFunctionNamesList = new ArrayList();
        
        /* First get a list of all the function categories. Don't include the 
         * "All functions" category label. */
        List allCatList = getFunctionCategories( ALL_LOCATION_OMITTED );
        
        /* Build up a master list of all the function names in each category. */
        Iterator allCatListIter = allCatList.iterator();
        while (allCatListIter.hasNext()) {
            String catLabel = (String) allCatListIter.next();
            if (!catLabel.equals(CAT_LABEL_USER_DEFINED_FUNCTIONS)) {
                List nameList = getFunctionNames( catLabel );
                fAllBuiltinFunctionNamesList.addAll( nameList );
            }
        }
        
        /* Sort the list, then remove duplicates. Note that the LinkedHashSet is
         * used because it maintains the insertion order of the elements. (A
         * regular HashSet would lose the sorted order.) */
        Collections.sort(fAllBuiltinFunctionNamesList);
        Set allFuncNameSet = new LinkedHashSet(fAllBuiltinFunctionNamesList);

        /* Put the function names back into the list.*/
        fAllBuiltinFunctionNamesList.clear();
        fAllBuiltinFunctionNamesList.addAll(allFuncNameSet);
    }
    
    /**
     * Initializes the aggregate (column, grouping, summary) function information.
     *
     * Subclasses should override this method to store the mapping of function names
     * to signatures in the master signature map and to initialize the list of 
     * function names.
     */
    protected void initAggregateFunctions() {
        fAggregateFunctionNamesList = new ArrayList();
    }

    /**
     * Initializes the conversion (cast) function information.
     *
     * Subclasses should override this method to store the mapping of function names
     * to signatures in the master signature map and to initialize the list of 
     * function names.
     */
    protected void initConversionFunctions() {
        fConversionFunctionNamesList = new ArrayList();
    }
    
    /**
     * Initializes the datetime (date, time, timestamp) function information.
     *
     * Subclasses should override this method to store the mapping of function names
     * to signatures in the master signature map and to initialize the list of 
     * function names.
     */
    protected void initDatetimeFunctions() {
        fDatetimeFunctionNamesList = new ArrayList();
    }
    
    /**
     * Initializes the numeric (math) function information.
     *
     * Subclasses should override this method to store the mapping of function names
     * to signatures in the master signature map and to initialize the list of 
     * function names.
     */
    protected void initNumericFunctions() {
        fNumericFunctionNamesList = new ArrayList();
    }
    
    /**
     * Initializes the scalar function information.  The scalar function category is 
     * actually a combination of other categories.  In the base implementation the 
     * combination is conversion, datetime, numeric, and string.
     *
     * Subclasses should override this method to add names from additional categories
     * to this category.
     */
    protected void initScalarFunctions() {
        fScalarFunctionNamesList = new ArrayList();
        
        fScalarFunctionNamesList.addAll( getConversionFunctionNames() );
        fScalarFunctionNamesList.addAll( getDatetimeFunctionNames() );
        fScalarFunctionNamesList.addAll( getNumericFunctionNames() );
        fScalarFunctionNamesList.addAll( getStringFunctionNames() );

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
     *
     * Subclasses should override this method to store the mapping of function names
     * to signatures in the master signature map and to initialize the list of 
     * function names.
     */    
    protected void initSpecialRegisters() {
        fSpecialRegisterNamesList = new ArrayList();
    }
    
    /**
     * Initializes the string (text) function information.
     *
     * Subclasses should override this method to store the mapping of function names
     * to signatures in the master signature map and to initialize the list of 
     * function names.
     */
    protected void initStringFunctions() {
        fStringFunctionNamesList = new ArrayList();
    }
    
    /**
     * Initializes the user-defined function information.
     *
     * Subclasses should override this method to store the mapping of function names
     * to signatures in the master signature map and to initialize the list of 
     * function names.
     */
    protected void initUserDefinedFunctions() {
        fUserDefinedFunctionNamesList = new ArrayList();
        fUserDefinedFunctionObjectsList = new ArrayList();

        /* Build up a list of UDF objects. */
        Database database = getDatabase();
        if (database != null) {
            /* Get a list of schemas. */
            List schemaList = DatabaseHelper.getSchemaList(database);
            
            /* From each schema get a list of UDF objects.  Omit UDFs associated
             * with the system schemas. */
            Iterator schemaListIter = schemaList.iterator();
            while (schemaListIter.hasNext()) {
                Schema schema = (Schema) schemaListIter.next();
                if (!getIsSystemSchema(schema)) {
                    fUserDefinedFunctionObjectsList.addAll(schema.getUDFs());
                }
            }
        }

        /* Go through the UDFs and add the name of each to our UDF name list. */
        Iterator udfListIter = fUserDefinedFunctionObjectsList.iterator();
        while (udfListIter.hasNext()) {
            UserDefinedFunction udf = (UserDefinedFunction) udfListIter.next();
            Schema schema = udf.getSchema();
            String qualUDFName = schema.getName() + "." + udf.getName();
            fUserDefinedFunctionNamesList.add(qualUDFName);
        }
    }
        
} // end class
