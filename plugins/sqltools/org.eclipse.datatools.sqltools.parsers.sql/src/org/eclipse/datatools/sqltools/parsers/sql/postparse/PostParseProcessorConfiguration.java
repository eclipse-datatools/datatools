/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.postparse;

import org.eclipse.datatools.modelbase.sql.schema.Database;


/**
 * The <code>PostParseProcessorConfiguration</code> provides information to
 * configure a <code>PostParseProcessor</code> (P3). That is especially, if a
 * dynamic set of P3s is unknown to the client but needs to be initialized.
 * 
 * @see PostParseProcessor#config(PostParseProcessorConfiguration) 
 * @author ckadner
 *
 */
public class PostParseProcessorConfiguration {

    private Database database = null;
    private String defaultSchemaName = null;

    // TODO: DatabaseDefinition, too?

    
    /**
     * @param database
     * @param defaultSchemaName
     */
    public PostParseProcessorConfiguration(Database database, String defaultSchemaName) {
        super();
        this.database = database;
        this.defaultSchemaName = defaultSchemaName;
    }
    
    
    
    /**
     * Default constructor.
     */
    public PostParseProcessorConfiguration() {
        super();
    }

    
    
    public Database getDatabase() {
        return database;
    }
    
    public void setDatabase(Database database) {
        this.database = database;
    }
    
    public String getDefaultSchemaName() {
        return defaultSchemaName;
    }
    
    public void setDefaultSchemaName(String defaultSchemaName) {
        this.defaultSchemaName = defaultSchemaName;
    }
}
