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
package org.eclipse.datatools.sqltools.parsers.sql.xml.query;

import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParser;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserFactory;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.parsers.sql.lexer.SQLLexer;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager;

/**
 * @author ckadner
 *
 */
public class SQLXMLQueryParserManager extends SQLQueryParserManager {    

    /**
     * 
     */
    public SQLXMLQueryParserManager() {
        super();
    }

    /**
     * @param sourceFormat
     * @param postParseProcessors
     */
    public SQLXMLQueryParserManager(SQLQuerySourceFormat sourceFormat, List postParseProcessors) {
        super(sourceFormat, postParseProcessors);
    }

    
    protected SQLParser createParser(SQLLexer sqlLexer, boolean syntaxCheckOnly) throws SQLParserInternalException
    {
        return new SQLXMLQueryParser(sqlLexer,(SQLXMLQueryParserFactory)getParserFactory(), getSourceFormat(), syntaxCheckOnly);
    }
    
    /**
     * Returns this <code>SQLXMLQueryParserManager</code>'s
     * <code>SQLXMLQueryParserFactory</code>. If this
     * <code>SQLXMLQueryParserManager</code>'s <code>parserFactory</code> is
     * <code>null</code>, a new <code>SQLXMLQueryParserFactory</code> will be
     * created for this <code>SQLXMLQueryParserManager</code> and returned.
     * That is, this method never returns <code>null</code>!
     * 
     * @return <code>SQLXMLQueryParserFactory</code>
     */
    public SQLParserFactory createParserFactory()
    {
        return new SQLXMLQueryParserFactory(getSourceFormat());
    }

}
