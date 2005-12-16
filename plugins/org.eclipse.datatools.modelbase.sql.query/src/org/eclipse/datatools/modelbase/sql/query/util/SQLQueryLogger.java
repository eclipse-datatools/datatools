/*
 * Licensed Materials - Property of IBM
 * com.ibm.db.models.sql.query
 * (C) Copyright IBM Corporation 2005. All Rights Reserved.
 *
 * Note to U.S. Government Users Restricted Rights:
 *   Use, duplication or disclosure restricted 
 *   by GSA ADP Schedule Contract with IBM Corp.
 */
package org.eclipse.datatools.modelbase.sql.query.util;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPlugin;



/**
 * The <code>SQLQueryLogger</code> is a slim wrapper around the eclipse
 * logging, with the intention to be refactored in future to adopt the
 * common logging strategy, preferably {@link java.util.logging.Logger}.
 * 
 * @author <a href="mailto:ckadner@us.ibm.com">ckadner</a>
 */
public class SQLQueryLogger extends SQLLogUtil {

    private static SQLLogUtil logger = null;
    

    /**
     * @param plugin
     */
    public SQLQueryLogger(Plugin plugin) {
        super(plugin);
    }

    
    public static SQLLogUtil getLogger() {
        if (SQLQueryLogger.logger == null) {
            SQLQueryLogger.logger = new SQLQueryLogger(SQLQueryModelPlugin.getDefault());
        }
        return SQLQueryLogger.logger;
    }

    public static void setLogger(SQLLogUtil logger) {
        SQLQueryLogger.logger = logger;
    }


}
