/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.schema.helper;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * @author ckadner
 *
 * Helper to provide convenience methods that are related to 
 * {@link org.eclipse.datatools.modelbase.sql.schema.Database}.
 */
public class DatabaseHelper
{
    
    /**
     * Finds a <code>Schema</code> by its name in a given <code>Database</code>.
     * If the given <code>schemaName</code> is not delimited, the
     * search is not case sensitive.  
     * 
     * @param database the <code>Database</code> to find <code>Schema</code>s in
     * @param schemaName the String name of the <code>Schema</code> to find
     * @return the <code>Schema</code> found or <code>null</code> if no 
     * 		corresponding <code>Schema</code> was found
     * @throws NullPointerException if given <code>database</code> or given
     *         <code>schemaName</code> is <code>null</code>
     */
    public static Schema findSchema(Database database, String schemaName) {
        Schema returnSchema = null;
        
        // TODO perf: optimize performance by loading only the schema needed as
        //            opposed to all of them
        for (Iterator it = database.getSchemas().iterator(); it.hasNext();)
        {
            Schema schema = (Schema) it.next();
            if (compareIdentifiers(database,schema.getName(),schemaName) == 0)
{
                returnSchema = schema;
                break;
            }
        }
        if (returnSchema == null){
        	if (database.getCatalogs() != null) {
        		List catalogs = database.getCatalogs();
                Iterator itCatalogs = catalogs.iterator();
                while (itCatalogs.hasNext()){
                	Catalog catalog = (Catalog) itCatalogs.next();
                    if (catalog.getSchemas()  != null && catalog.getSchemas().size() > 0){
                    	for (Iterator it = catalog.getSchemas().iterator(); it.hasNext();)
                        {
                    		Schema schema = (Schema) it.next();
                            if (compareIdentifiers(database,schema.getName(),schemaName) == 0) {
                            	returnSchema = schema;
                                break;
                            }
                        }// for
                    }
                }
        	}
        }


        return returnSchema;
    }
    
    
    /**
     * Finds all <code>Table</code>s with the name <code>tableName</code> in all
     * <code>Schema</code>'s of the given <code>database</code>.
     * Returns a list of <code>Table</code>s with associated <code>Schema</code>.
     * If the given <code>tableName</code> is not delimited, the
     * search is not case sensitive.  
     * 
     * @param database the <code>Database</code> to search in
     * @param tableName the String name of the <code>Table</code> to find
     * @return List of <code>Table</code>s with associated <code>Schema</code>
     * @throws NullPointerException if given <code>schema</code> or given
     *         <code>tableName</code> is <code>null</code>
     */
    public static List findTables(Database database, String tableName) {
        List returnTables = new Vector();
        
        for (Iterator schemaIt = database.getSchemas().iterator(); schemaIt.hasNext();)
        {
            Schema schema = (Schema) schemaIt.next();
            
	        for (Iterator tableIt = schema.getTables().iterator(); tableIt.hasNext();)
	        {
	            Table table = (Table) tableIt.next();
	            
	            if (compareIdentifiers(database,table.getName(),tableName) == 0) {
	                returnTables.add(table);
	            }
	        }
        }
        

        return returnTables;
    }


    /**
     * Compares an existing identifier in a Database with the lookup name 
     * respecting delimited identifiers otherwise ignoring case.
     * 
     * @param database the Database to determin the delimiter for identifiers
     * @param identifier a String identifier already existing in the Database
     * @param lookupName the String name to compare to a existing identifier
     * @return 0 if lookupName is equal to identifier
     * @see String#compareTo(java.lang.String)
     */
    public static int compareIdentifiers(Database database, String identifier, String lookupName)
    {
        int compResult = -1;
        
        // TODO: get delimiter from Database
        // ask Hemant how to commonly treat identifiers (Thomas Sharp), 
        // Tanja knows where to put it?
        String delimiter = "\""; //$NON-NLS-1$
        
        
        // e.g. "TaBlE1"
        if (lookupName.startsWith(delimiter)) {
            
            // e.g. "TaBlE1" == "TaBLeInDB" ?
            if (identifier.startsWith(delimiter))
            {
                compResult = identifier.compareTo(lookupName);
            } 
            // e.g. TaBlE1 == TaBLeInDB ?
            else if (lookupName.endsWith(delimiter))
            {
                String lookupNameContent = lookupName.substring(1, lookupName.length()-1);
                
                compResult = identifier.compareTo(lookupNameContent);
            }
            
        }
        // e.g. TABLE1 == TABLEINDB ?
        else
        {
            compResult = identifier.compareToIgnoreCase(lookupName);
        }
        
        return compResult;
    }
    

}
