/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views.source;

import org.eclipse.jface.text.rules.RuleBasedScanner;

public class SQLSourceEditingEnvironment {

    private static SQLColourProvider fSQLColourProvider;
    private static SQLSourceScanner fSQLSourceScanner;
    private static int fgRefCount = 0;
    private static SQLCommentScanner fSQLCommentScanner;
    private static SQLEntityScanner fSQLEntityScanner;

    public static void connect() {
        if (++fgRefCount == 1) {
            fSQLColourProvider = new SQLColourProvider();
            fSQLSourceScanner = new SQLSourceScanner(fSQLColourProvider);
            fSQLCommentScanner = new SQLCommentScanner(fSQLColourProvider);
            fSQLEntityScanner = new SQLEntityScanner(fSQLColourProvider);
        }
    }

    public static void disconnect() {
        if (--fgRefCount == 0) {
            fSQLSourceScanner = null;
            fSQLColourProvider.dispose();
            fSQLColourProvider = null;
        }
    }

    public static SQLColourProvider getSQLColourProvider() {
        return fSQLColourProvider;
    }

    /**
     * 
     * @return org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLCommentScanner
     */
    public static SQLCommentScanner getSQLCommentScanner() {
        return fSQLCommentScanner;
    }

    /**
     * 
     * @return org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLEntityScanner
     */
    public static SQLEntityScanner getSQLEntityScanner() {
        return fSQLEntityScanner;
    }

    public static RuleBasedScanner getSQLSourceScanner() {
        return fSQLSourceScanner;
    }
}