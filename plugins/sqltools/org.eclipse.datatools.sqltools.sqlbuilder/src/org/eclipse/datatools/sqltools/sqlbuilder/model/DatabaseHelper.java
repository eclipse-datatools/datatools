/*******************************************************************************
 * Copyright © 2000, 2013 IBM Corporation and others.
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
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * Provides utility functions related to database.
 */
public class DatabaseHelper {

    /**
     * Returns the list of tables for the given database.
     * @return the List of tables
     */
    static public List getTableList(Database database) {
        List tempList = new ArrayList();
        
        if (database != null) {
            List sList = DatabaseHelper.getSchemaList(database);
            Iterator schemaIter = sList.iterator();
            while (schemaIter.hasNext()) {
                Schema lSchema = (Schema) schemaIter.next();
                tempList.addAll(getTableList(lSchema));
            }
        }
        
        return tempList;
    }

    /**
     * Returns the List of tables belonging to the given schema. 
     * @param schema the Schema for which tables are needed
     * @return the List of tables 
     */
    static public List getTableList(Schema schema) {
        List tempList = new ArrayList();
        if (schema != null) {
            List tList = schema.getTables();
            Iterator tableIter = tList.iterator();
            while (tableIter.hasNext()) {
                Table table = (Table) tableIter.next();
                tempList.add(table);
            }
        }
        return tempList;
    }


    /**
     * Returns the list of schemas for the given database.
     * @return the List of schemas
     */
    static public List getSchemaList(Database database) {
        List tempList = new ArrayList();
        
        if (database != null && database.getSchemas() != null && database.getSchemas().size() > 0) {
        	tempList.addAll(database.getSchemas());
        }
        else if (database != null && database.getCatalogs() != null
        		&& database.getCatalogs().size() > 0) {
        	List catalogs = database.getCatalogs();
        	Iterator itCatalogs = catalogs.iterator();
        	while (itCatalogs.hasNext()){
        		Catalog catalog = (Catalog) itCatalogs.next();
        		String catName = catalog.getName();
                // avoid repeated calls that could re-trigger load schemas if it has an exception
                List catalogSchemas = catalog.getSchemas();   
                if ( catalogSchemas != null && catalogSchemas.size() > 0 ){
                    tempList.addAll( catalogSchemas );
        		}
        	}
        }
        
        return tempList;
    }

}