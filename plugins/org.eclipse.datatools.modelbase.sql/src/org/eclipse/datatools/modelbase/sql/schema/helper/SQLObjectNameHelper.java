/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.schema.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * This class implements the ISQLObjectNameHelper interface to provide 
 * a naming service for SQL Model objects. 
 */
public class SQLObjectNameHelper implements ISQLObjectNameHelper {

    /** The identifier quote string used to delimit identifiers. */
    private String fQuoteString = "\"";
    
    /**
     * Gets the name of the given column, delimited as needed to make it suitable
     * for use in constructing an SQL statement.
     * 
     * @param table the SQL Model column object for which the name is wanted
     * @return the (possibly) delimited SQL format name of the column
     */
    public String getNameInSQLFormat(Column column) {
        String sqlFormatName = null;
        
        if (column != null) {
            String quoteString = getIdentifierQuoteString();
            
            /* Get the object name and delimit it if necessary. */
            String name = column.getName();
            sqlFormatName = convertCatalogIdentifierToSQLFormat(name, quoteString);
        }
        
        return sqlFormatName;
    }
    /**
     * Gets the name of the given table, delimited as needed to make it suitable
     * for use in constructing an SQL statement.
     * 
     * @param table the SQL Model table object for which the name is wanted
     * @return the (possibly) delimited SQL format name of the table
     */
    public String getNameInSQLFormat(Table table) {
        String sqlFormatName = null;
        
        if (table != null) {
            String quoteString = getIdentifierQuoteString();
            
            /* Get the object name and delimit it if necessary. */
            String name = table.getName();
            sqlFormatName = convertCatalogIdentifierToSQLFormat(name, quoteString);
        }
        
        return sqlFormatName;
    }
    
    /**
     * Gets the name of the given table, qualified and delimited as needed to make 
     * it suitable for use in constructing an SQL statement.  
     * 
     * @param table the SQL Model table object for which the name is wanted
     * @return the qualified and delimited name of the table
     */
    public String getQualifiedNameInSQLFormat(Column column) {
        String qualifiedSQLName = null;
        
        if (column != null) {
            String quoteString = getIdentifierQuoteString();
            
            /* Get the object name and delimit it if necessary. */
            String name = column.getName();
            String sqlFormatName = convertCatalogIdentifierToSQLFormat(name, quoteString);
            qualifiedSQLName = sqlFormatName;
            
            /* Get and add the table name. */
            String tableName = null;
            Table table = column.getTable();
            if (table != null) {
                tableName = table.getName();
                String sqlFormatTableName = convertCatalogIdentifierToSQLFormat(tableName, quoteString);
                qualifiedSQLName = sqlFormatTableName + "." + sqlFormatName;
            }
        }
        
        return qualifiedSQLName;
    }

    /**
     * Gets the name of the given table, qualified and delimited as needed to make 
     * it suitable for use in constructing an SQL statement.  
     * 
     * @param table the SQL Model table object for which the name is wanted
     * @return the qualified and delimited name of the table
     */
    public String getQualifiedNameInSQLFormat(Table table) {
        String qualifiedSQLName = null;
        
        if (table != null) {
            String quoteString = getIdentifierQuoteString();
            
            /* Get the table name and delimit it if necessary. */
            String tableName = table.getName();
            String sqlFormatTableName = convertCatalogIdentifierToSQLFormat(tableName, quoteString);
            qualifiedSQLName = sqlFormatTableName;
            
            /* Get and add the schema name. */
            String schemaName = null;
            Schema schema = table.getSchema();
            if (schema != null) {
                schemaName = schema.getName();
                String sqlFormatSchemaName = convertCatalogIdentifierToSQLFormat(schemaName, quoteString);
                qualifiedSQLName = sqlFormatSchemaName + "." + sqlFormatTableName;
            }
        }
        
        return qualifiedSQLName;
    }

    /**
     * Gets the identifier quote string being used to delimit identifiers.  The default
     * is " (double-quote character).
     * 
     * @return the current identifier quote string
     */
    public String getIdentifierQuoteString() {
        return fQuoteString;
    }

    /**
     * Sets the identifier quote string to use to delimit identifiers.
     * 
     * @param quoteString the identifier quote string to use
     */
    public void setIdentifierQuoteString(String quoteString) {
        fQuoteString = quoteString;
    }

    /**
     * Gets the SQL format form of the given catalog format identifier.  See table below for examples.
     * 
     * <table border='1' width='250'>
     * <tr>
     * <th><b>Catalog format</b></th>
     * <th><b>SQL format</b></th>
     * </tr>
     * <tr>
     * <td>MY_TABLE</td>
     * <td>MY_TABLE</td>
     * </tr>
     * <tr>
     * <td>MyTable</td>
     * <td>&quot;MyTable&quot;</td>
     * </tr>
     * <tr>
     * <td>My Table</td>
     * <td>&quot;My Table&quot;</td>
     * </tr>
     * <tr>
     * <td>My &quot;Table&quot;</td>
     * <td>&quot;My &quot;&quot;Table&quot;&quot;&quot;</td>
     * </tr>
     * </table>
     * 
     * @param catIdentifier the identifier in catalog format
     * @return the identifier in SQL format
     */
    protected String convertCatalogIdentifierToSQLFormat(String catIdentifier, String quoteString) {
        String sqlIdentifier = catIdentifier;

        if (catIdentifier != null && quoteString != null && quoteString.length() > 0 && !(quoteString.equals(" "))) {
            boolean containsDelimiters = catIdentifier.indexOf(quoteString) > -1;
            boolean containsSpace = catIdentifier.indexOf(' ') > -1;
            boolean containsDot = catIdentifier.indexOf('.') > -1;
            boolean isLowerOrMixedCase = !catIdentifier.toUpperCase().equals(catIdentifier);
            
            /* Handle the case when the identifier contains all digits (0-9). */
            boolean allDigits = false;
            try {
                Integer.parseInt(catIdentifier);
                // If no exception, then it contains all digits.
                allDigits = true;
            }
            catch (NumberFormatException ex) {
                // ignore
            }
            
            /* The Unicode General Category classes Lu, Ll, Lt, Lm, Lo, and Nl
             * are assigned upper-case letters, lower-case letters, title-case
             * letters, modifier letters, other letters, and letter numbers.
             * All identifiers that have a non-word character (i.e not any of a-z A-Z _ 0-9 ) needs to be delimited
             */
            boolean containsNonAlpha = false;
            
            /* The following pattern is "all non-word characters (\W) except the chars $, #, and @" */
            String nonAlphaRegex = "[\\W&&[^$#@]]";  //$NON-NLS-1$
            Pattern patern = Pattern.compile(nonAlphaRegex);
            Matcher matcher = patern.matcher(catIdentifier);
            while(!containsNonAlpha &&  matcher.find()){
                containsNonAlpha = true;
            }

            if (containsDelimiters || containsSpace || containsDot || containsNonAlpha || isLowerOrMixedCase
                    || allDigits) {
                /* Need to check for and double internal quotes. */
                if (containsDelimiters) {
                    /* If already have delimiters at each end then we'll assume its already properly quoted. */ 
                    if (sqlIdentifier.startsWith(quoteString) == true
                        && sqlIdentifier.endsWith(quoteString) == true) {
                        // do nothing
                    }
                    else {
                        /* We only double the quotes for quote strings that are a single character.*/
                        if (quoteString.length() == 1) {
                            char quoteChar = quoteString.charAt(0);
                            StringBuffer sqlIdentSB = new StringBuffer(sqlIdentifier);
                            for (int i = 0; i < sqlIdentSB.length(); i++) {
                                if (sqlIdentSB.charAt(i) == quoteChar) {
                                    sqlIdentSB.insert(i, quoteChar);
                                    i++;
                                }
                            }
                            sqlIdentifier = sqlIdentSB.toString();
                        }
                        sqlIdentifier = quoteString + sqlIdentifier + quoteString;
                    }
                }
                else {
                    sqlIdentifier = quoteString + sqlIdentifier + quoteString;
                }
            }
        }

        return sqlIdentifier;
    }

    /**
     * Gets the database object associated with the given schema object.
     * 
     * @param schema the schema object for which the database is needed
     * @return the database object associated with the schema
     */
    protected Database getDatabase (Schema schema) {
        Database database = null;
        
        if (schema != null) {
           database = schema.getCatalog() == null ? schema.getDatabase() : schema.getCatalog().getDatabase();
        }
        
        return database;
    }
    
}
