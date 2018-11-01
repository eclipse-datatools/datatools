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
package org.eclipse.datatools.sqltools.parsers.sql.query;



import org.eclipse.core.runtime.Plugin;
import org.eclipse.datatools.modelbase.sql.query.util.SQLLogUtil;


/**
 * The <code>SQLQueryParserLogger</code> is a slim wrapper around the eclipse
 * logging, with the intention to be refactored in future to adopt the
 * common logging strategy, preferably {@link java.util.logging.Logger}.
 * 
 * @author ckadner
 *
 */
public class SQLQueryParserLogger extends SQLLogUtil {

    private static SQLLogUtil logger = null;
    
    /**
     * @param plugin
     */
    private SQLQueryParserLogger(Plugin plugin) {
        super(plugin);
    }

    
    public static SQLLogUtil getLogger() {
        if (logger == null) {
            logger = new SQLQueryParserLogger(SQLQueryParserPlugin.getDefault());
        }
        return logger;
    }

    public static void setLogger(SQLLogUtil logger) {
        SQLQueryParserLogger.logger = logger;
    }
    
    
    
}
