/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract SQL parser. Vendor developers should extend this parser to support
 * their own dialect in content assist and syntax validation features.
 * 
 * @see ParserParameters
 * @see ParsingResult
 * @author Hui Cao
 * 
 */
public abstract class SQLParser implements SQLParserConstants
{
    /**
     * All concrete parsers should include this special invalid token to enable the content assist feature
     */
    public static final String SPECIAL_TOKEN = "!%^&";

    /**
     * The prameter used during parsing.
     */
    protected ParserParameters   _parameters             = new ParserParameters(false, SQLParserConstants.TYPE_SQL_ROOT);

    protected int fScope;

    /**
	 * Expected token list can be retrieved from ParseException, while expected
	 * unreserved keywords should be retrieved from this list, which is
	 * populated by the parser. 
	 */
    protected List              _expectedUnreservedKeywords  = new ArrayList();
    
    /**
     * Parses the given sql text using the default parameter. Records the abstract syntax tree nodes and accumulates
     * <code>ParseException</code> in the <code>ParsingResult</code>.
     * 
     * @param text sql text to be parsed
     * @return <code>ParsingResult</code> containing root AST node and <code>ParseException</code>s.
     */
    public ParsingResult parse(String text)
    {
        ParserParameters param = new ParserParameters(false, SQLParserConstants.TYPE_SQL_ROOT);
        return parse(text, param);
    }

    /**
     * Parses a sql text and try to construct the AST by ignoring all the parsing exceptions and moving to the start
     * token of the next possible statement;
     * 
     * @param text sql text to be parsed
     * @param parameters parameter used by parser
     * @return <code>ParsingResult</code> containing root AST node and <code>ParseException</code>s.
     */
    public ParsingResult parse(String text, ParserParameters parameters)
    {
        ParserParameters oldParameters = _parameters;
        setParameters(parameters);
        ParsingResult result = doParse(text);
        setParameters(oldParameters);
        return result;
    }
    
    /**
     * Returns the expected unreserved keywords, used in content assist.
     * @return String list
     */
    public List getExpectedUnreservedKeywords()
    {
    	return Collections.unmodifiableList(_expectedUnreservedKeywords);
    }

    /**
	 * Returns the statement terminator array. Different vendors will have their
	 * own terminators defined, so we just leave this method as abstract here.
	 * 
	 * @return statement terminator array
	 */
    public abstract String[] getStatementTerminators();
    
    /**
     * Returns the token strings that can be used to begin a SQL statement. 
     * 
     * @return statement start token array
     */
    public abstract String[] getStatementStartTokens();
    
    /**
     * Gets the current scope at the position where content assist is invoked. This is used by content assist processor
     * to determine what database meta info should be retrieved.
     * 
     * @see SQLParserConstants
     * @return scope constants defined in <code>SQLParserConstants</code> 
     */
    public int getScope()
    {
        return fScope;
    }

    /**
     * Gets the parameter.
     * @return
     */
    protected ParserParameters getParameters()
    {
        return _parameters;
    }

    /**
     * Sets the parameter.
     * @param parameters
     */
    protected void setParameters(ParserParameters parameters)
    {
        this._parameters = parameters;
    }
    
    /**
     * Concrete parsers must imlement this method to do the real parsing.
     * @param text sql text to be parsed.
     * @return <code>ParsingResult</code> containing root AST node and <code>ParseException</code>s.
     */
    protected abstract ParsingResult doParse(String text);
    
    final protected int setScope(int scope)
    {
		return setScope("", scope);
    }

	/**
	 * Sets the current scope
	 * @return the original scope for restore
	 */
    final protected int setScope(String name, int scope)
    {
		int oldScope = fScope;
        fScope = scope;
        return oldScope;
    }

}
