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
package org.eclipse.datatools.sqltools.parsers.sql;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.datatools.modelbase.sql.query.util.SQLLogUtil;


/**
 * The <code>SQLParserLogger</code> is a slim wrapper around the eclipse
 * logging, with the intention to be refactored in future to adopt the
 * common logging strategy, preferably {@link java.util.logging.Logger}.
 * 
 * @author ckadner
 */
public class SQLParserLogger extends SQLLogUtil {

    private static SQLLogUtil logger = null;
    

    /**
     * @param plugin
     */
    public SQLParserLogger(Plugin plugin) {
        super(plugin);
    }

    
    public static SQLLogUtil getLogger() {
        if (SQLParserLogger.logger == null) {
            SQLParserLogger.logger = new SQLParserLogger(SQLParserPlugin.getDefault());
        }
        return SQLParserLogger.logger;
    }

    public static void setLogger(SQLLogUtil logger) {
        SQLParserLogger.logger = logger;
    }


}
