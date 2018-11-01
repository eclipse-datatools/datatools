/*******************************************************************************
 * Copyright © 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.sqlbuilder.model.DatabaseHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;


/**
 * 
 * @author nbhatia
 *
 * The class contains static function to populate UDF name.
 * It populates the name base on the assumption that Model 
 * is already update.  It gets the meta data from the modal.
 */

public class UDFNamesAndSignatures {

    private static Vector udfList;

    public static Object[][] getUDFParams(String functionName) {
        int totalPrototypes = 0;
        int totalPrameters = 0;
        
        Object list[][] = new Object[1][2];
        list[0][0] = "???";
        list[0][1] = "???";
        
        if (udfList != null) {
            for (int i = 0; i < udfList.size(); i++) {
                UserDefinedFunction lUDF = (UserDefinedFunction) udfList.elementAt(i);
                if (functionName.equals(lUDF.getSchema().toString().concat(".").concat(lUDF.getName().toString()))) {
                    totalPrameters = totalPrameters > lUDF.getParameters().size() ? totalPrameters : lUDF.getParameters().size();
                    totalPrototypes++;
                }
            }
            
            if (totalPrototypes > 0) {
                list = new Object[totalPrototypes][totalPrameters + 1];
                int lPro = 0;
                for (int i = 0; i < udfList.size(); i++) {
                    int lParam = 0;
                    
                    UserDefinedFunction lUDF = (UserDefinedFunction) udfList.elementAt(i);
                    if (functionName.equals(lUDF.getSchema().toString().concat(".").concat(lUDF.getName().toString()))) {
                        list[lPro][lParam] = lUDF.getReturnScalar().getName();
                        lParam++;
                        Iterator iterator = lUDF.getParameters().iterator();
                        while (iterator.hasNext()) {
                            Object obj = iterator.next();
                            if (obj instanceof Parameter) {
                                Parameter lParameter = (Parameter) obj;
                                list[lPro][lParam] = lParameter.getDataType().getName();
                                lParam++;
                            }
                        }
                        lPro++;
                    }
                }
            }
        }
        
        return list;
    }

    public static final String[] getUDFNames(SQLDomainModel domainModel) {
        Vector lsUDFList = new Vector();
        udfList = new Vector();
        String strUDFList[];
        //  	Schema schema = null;
        Database database = domainModel.getDatabase();
        Iterator iterator = getAllUDFsForDatabase(database).iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (obj instanceof UserDefinedFunction) {
                UserDefinedFunction lUDF = (UserDefinedFunction) obj;
                udfList.addElement(lUDF);
                if (!lsUDFList.contains(lUDF.getSchema().getName().concat(".").concat(lUDF.getName())))
                    lsUDFList.addElement(lUDF.getSchema().getName().concat(".").concat(lUDF.getName()));
            }
        }

        strUDFList = new String[lsUDFList.size()];
        for (int i = 0; i < lsUDFList.size(); i++) {
            strUDFList[i] = lsUDFList.elementAt(i).toString();
        }
        return strUDFList;
    }

    /**
     * Returns List of all the UDFs for the given database.
     * @param database the Database for which all the UDFs is needed
     * @return list of UDFs
     */
    public static List getAllUDFsForDatabase(Database database) {
        List allUDFList = new ArrayList();
        
        if (database != null) {
            List schemas = DatabaseHelper.getSchemaList(database);
            for (Iterator itr = schemas.iterator(); itr.hasNext();) {
                Schema sch = (Schema) itr.next();
                if (!sch.getName().toUpperCase().startsWith("SYS")) {
                    allUDFList.addAll(sch.getUDFs());
                }
            }
        }
        
        return allUDFList;
    }

    public static final String[] mergeTwoArrays(String arr1[], String arr2[]) {
        String arr3[];
        int i = 0;
        arr3 = new String[arr1.length + arr2.length];
        for (i = 0; i < arr1.length; i++) {
            arr3[i] = arr1[i];
        }
        for (int j = 0; j < arr2.length; j++) {
            arr3[i] = arr2[j];
            i++;
        }
        return arr3;
    }

}