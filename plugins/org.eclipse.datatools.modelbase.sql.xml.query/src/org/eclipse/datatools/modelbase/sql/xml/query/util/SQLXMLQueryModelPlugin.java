/*
 * Licensed Materials - Property of IBM
 * org.eclipse.datatools.modelbase.sql.xml.query
 * (C) Copyright IBM Corporation 2005. All Rights Reserved.
 *
 * Note to U.S. Government Users Restricted Rights:
 *   Use, duplication or disclosure restricted 
 *   by GSA ADP Schedule Contract with IBM Corp.
 */
package org.eclipse.datatools.modelbase.sql.xml.query.util;

import org.eclipse.datatools.modelbase.sql.query.util.SQLQueryModelPlugin;

/**
 * This is the main plugin class for the SQL/XML Query Model. 
 * 
 * @author bpayton
 */
public class SQLXMLQueryModelPlugin extends SQLQueryModelPlugin {

    /**
     * Constructs an instance of this class.  This is the default constructor.
     * The super constructor is invoked to perform plugin start-up registrations.
     * (These are needed for the SQL Source Writer to work properly.)
     */
    public SQLXMLQueryModelPlugin() {
        super();
    }

}
