/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.query;

import java.util.Arrays;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.CallStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParseResult;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParser;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserFactory;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserManager;
import org.eclipse.datatools.sqltools.parsers.sql.lexer.AbstractSQLLexer;
import org.eclipse.datatools.sqltools.parsers.sql.lexer.SQLLexer;
import org.eclipse.datatools.sqltools.parsers.sql.postparse.PostParseProcessor;
import org.eclipse.datatools.sqltools.parsers.sql.query.postparse.DataTypeResolver;
import org.eclipse.datatools.sqltools.parsers.sql.query.postparse.RoutineReferenceResolver;
import org.eclipse.datatools.sqltools.parsers.sql.query.postparse.TableReferenceResolver;

/**
 * @author ckadner
 *
 * Parse Fascade class providing methods to parse <code>String</code>
 * representations of SQL statements into a structured
 * <code>SQLQueryObject</code> model representations.
 * 
 * Options regarding the SQL source code can be provided with a
 * <code>SQLQuerySourceFormat</code>, otherwise the default settings in
 * <code>SQLQuerySourceFormat.SQL_SOURCE_FORMAT_DEFAULT</code> will be used.
 * 
 * The whole parsing process can be seen as a two phase process with first the
 * syntactical phase, the actual parse, and second the optional semantical phase,
 * for further, higher level validation and resolving of the parse result. 
 * The result of the syntactical phase is an instance of the
 * <code>SQLQueryObject</code> model, if parsing was successful, or a
 * <code>SQLParserException</code>, if the given SQL statement
 * <code>String</code> did not conform to the parser's syntax definition.
 * If the first syntactical phase succeeded into an instance of the
 * <code>SQLQueryObject</code> model, the second semantical phase is started, if
 * the parser is provided a list of <code>PostParseProcessor</code>s. The result
 * of the semantical post parse processing is a List of
 * <code>SQLParseErrorInfo</code> objects, in case of less severe semantical
 * errors, or can be a <code>SQLParserException</code>, if a semantical error
 * encountered justifies to discard the <code>SQLQueryObject</code> model as
 * invalid.
 * 
 * @see org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat
 * @see org.eclipse.datatools.sqltools.parsers.sql.postparse.PostParseProcessor
 * 
 */
public class SQLQueryParserManager extends SQLParserManager {
    
    private static SQLQueryParserManager INSTANCE = null;
    public static SQLQueryParserManager getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE =
                new SQLQueryParserManager(
                            SQLQuerySourceFormat.SQL_SOURCE_FORMAT_DEFAULT,
                            DEFAULT_POST_PARSE_PROCESSOR_LIST);
        }
        return INSTANCE;
    }
    protected SQLParserFactory parserFactory = null;
    
	
    /**
     * Default list of <code>{@link PostParseProcessor}</code>s that can be
     * copied and extended for use with
     * {@link #parse(String, SQLQuerySourceFormat, List)} or
     * {@link #parseScript(String, SQLQuerySourceFormat, List)}, 
     * 
     * <code>DEFAULT_POST_PARSE_PROCESSOR_LIST</code> consists of:
     * <ol>
     * 	<li>{@link TableReferenceResolver}</li>
     * 	<li>{@link DataTypeResolver}</li>
     *  <li>{@link RoutineReferenceResolver}</li>
     * </ol>
     * 
     */
    public static final List DEFAULT_POST_PARSE_PROCESSOR_LIST = 
        Arrays.asList( new PostParseProcessor[] {
                                        new TableReferenceResolver(true),
                                        new DataTypeResolver(true),
                                        new RoutineReferenceResolver()
                                        });
    
    /* will be used as hidden internal postparse processors, to 
     * distinguish between no postparseprocessing required hidden
     * post parse processing and explicitly required postparseprocessing
     * using the DEFAULT_POST_PARSE_PROCESSOR_LIST, that changes what we return
     * and if we raise Exceptions for postparseprocessing,
     * we do want to hide it, right
     * @see makeAST */
    private static final List INTERNAL_DEFAULT_POST_PARSE_PROCESSOR_LIST =
        Arrays.asList( new PostParseProcessor[] {
                        new TableReferenceResolver(false),
                        new DataTypeResolver(false),
                        new RoutineReferenceResolver()
                        });
    
    
    public SQLQueryParserManager()
    {
        super();
//        this.lexer = new SQLLexer(getCharacterKindMap());
//        this.parser = new SQLQueryParser(lexer);
    }
    
    
    /**
     * @param sourceFormat
     * @param postParseProcessors
     */
    public SQLQueryParserManager(SQLQuerySourceFormat sourceFormat,
                                 List postParseProcessors)
    {
        super(sourceFormat, postParseProcessors);
//        this.lexer = new SQLLexer(getCharacterKindMap());
//        this.parser = new SQLQueryParser(lexer);
    }
    
    
    /**************************************************************************
     *                protected template methods
     **************************************************************************/
    
    protected List getInternalDefaultPostParseProcessorList()
    {
        return INTERNAL_DEFAULT_POST_PARSE_PROCESSOR_LIST;
    }
    
    protected AbstractSQLLexer createLexer(String input)
    {
        return new SQLLexer(input.toCharArray(),false,getCharacterKindMap());
    }
    
    protected SQLParser createParser(AbstractSQLLexer lexer, boolean syntaxCheckOnly)throws SQLParserInternalException
    {
        return new SQLQueryParser(lexer,
                (SQLQueryParserFactory) getParserFactory(),
                getSourceFormat(),
                syntaxCheckOnly);
    }    	

    
    /**
     * Returns this <code>SQLQueryParserManager</code>'s
     * <code>SQLQueryParserFactory</code>. If this
     * <code>SQLQueryParserManager</code>'s <code>parserFactory</code> is
     * <code>null</code>, a new <code>SQLQueryParserFactory</code> will be
     * created for this <code>SQLQueryParserManager</code> and returned.
     * That is, this method never returns <code>null</code>!
     * 
     * @return <code>SQLQueryParserFactory</code>
     */
    public SQLParserFactory getParserFactory()
    {
        if (parserFactory == null) {
            parserFactory = createParserFactory();
        }
        return parserFactory;
    }
    
    /**
     * @param sourceFormat
     * @return
     */
    protected SQLParserFactory createParserFactory() {
        return new SQLQueryParserFactory(getSourceFormat());
    }


    /**
     * Sets this <code>SQLQueryParserManager</code>'s
     * <code>parserFactory</code>.
     * <p>
     * <b>Note: </b> the given <code>parserFactory</code> must be an
     * appropriate instance of <code>SQLQueryParserFactory</code> for the
     * runtime-type of this <code>SQLQueryParserManager</code>.
     * 
     * @throws IllegalArgumentException,
     *             if given <code>parserFactory</code> is <code>null</code>
     *             or not of the appropriate runtime-type
     *             <code>SQLQueryParserFactory</code>
     */
    public void setParserFactory(SQLQueryParserFactory parserFactory)
    {
        if (parserFactory != null)
        {
            this.parserFactory = parserFactory;
        }
        else
        {
            throw new IllegalArgumentException(this.getClass().getName()
                            + "#setParserFactory(SQLQueryParserFactory)" //$NON-NLS-1$
                            + " only accepts non-null argument: " //$NON-NLS-1$
                            + SQLQueryParserFactory.class.getName() + "." //$NON-NLS-1$
                            + " Given argument: " + parserFactory); //$NON-NLS-1$
        }
    }
    
    /** Creates a parse result for the given Query (DML) statement.
     * 
     * @param stmt the statement for which the parse result is needed
     * @param errorList the error list to include in the parse result
     * @return the parse result object
     */
    protected SQLParseResult createParseResult(SQLStatement stmt, List errorList)
    {
        return new SQLQueryParseResult((QueryStatement)stmt, errorList);
    }

    /**
     * Creates a parse result object for the given "control" (that is, CALL) statement model.  
     * (The CALL and RETURN statements are "control" statements in ISO SQL terms.)
     * 
     * @param stmt the statement for which the parse result is needed
     * @param errorList the error list to include in the parse result
     * @return the parse result object
     */
    protected SQLControlParseResult createControlParseResult(SQLStatement stmt, List errorList)
    {
        return new SQLControlParseResult((CallStatement)stmt,errorList);
    }
    
    /**************************************************************************
     *                overwritten protected methods
     **************************************************************************/

    protected void setupCharacterKindMap(SQLQuerySourceFormat p_format)
    {
        super.setupCharacterKindMap(p_format);
        // for example like:
        //setVariableCharacterKind('A', getCharacterKindMap().Char_A);
    }

    
    
    /**************************************************************************
     *                public configuration methods
     **************************************************************************/
    
    /**
     * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.SQLParserManager#getDefaultPostParseProcessorList()
     */
    public List getDefaultPostParseProcessorList()
    {
        return DEFAULT_POST_PARSE_PROCESSOR_LIST;
    }
    
    /**
     * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.SQLParserManager#copyDefaultPostParseProcessorList()
     */
    public List copyDefaultPostParseProcessorList()
    {
        return Arrays.asList( new PostParseProcessor[] {
                new TableReferenceResolver(true),
                new DataTypeResolver(true),
                new RoutineReferenceResolver()
                });
    }
    
    public void setSourceFormat(SQLQuerySourceFormat sourceFormat)
    {
        super.setSourceFormat(sourceFormat);
        getParserFactory().setSQLSourceFormat(sourceFormat);
    }

    
    /**************************************************************************
     *                overwritten parse methods
     **************************************************************************/

    
    
    
    /**
     * Convenience proxy method for
     * {@link SQLParserManager#checkSyntax(String)}
     * casting the returned <code>SQLStatement</code> to
     * <code>QueryStatement</code>.
     * @see SQLParserManager#checkSyntax(String)
     * @param stmt the SQL DML statement
     * @return <code>(QueryStatement) super.checkSyntax(stmt);</code>
     * @throws SQLParserException
     * @throws SQLParserInternalException
     */
    public QueryStatement checkSyntaxQuery(String stmt) throws SQLParserException,
                    SQLParserInternalException
    {
        return (QueryStatement) super.checkSyntax(stmt);
    }
    
    /**
     * Convenience proxy method for
     * {@link SQLParserManager#checkSyntaxScript(String)}
     * casting the returned <code>SQLStatement</code>s to
     * <code>QueryStatement</code>s.
     * @see SQLParserManager#checkSyntaxScript(String)
     * @param script the SQL DML statements
     * @return List of <code>QueryStatement</code>s
     * @throws SQLParserException
     * @throws SQLParserInternalException
     */
    public List checkSyntaxQueryScript(String script) throws SQLParserException,
                    SQLParserInternalException
    {
        return super.checkSyntaxScript(script);
    }
    
    /**
     * Convenience proxy method for {@link SQLParserManager#parse(String)}
     * casting the returned <code>SQLParseResult</code> to
     * <code>SQLQueryParseResult</code>.
     * @see SQLParserManager#parse(String)
     * @param stmt the SQL DML statement
     * @return <code>(SQLQueryParseResult) super.parse(stmt);</code>
     * @throws SQLParserException
     * @throws SQLParserInternalException
     */
    public SQLQueryParseResult parseQuery(String stmt) throws SQLParserException,
                    SQLParserInternalException
    {
        return (SQLQueryParseResult) super.parse(stmt);
    }
    
    /**
     * Parses the given control statement string and returns the result of the parse.  (In ISO SQL,
     * the CALL statement is a "control" statement.)
     * 
     * @param stmt the statement string to parse
     * @return the parse result containing the generated control model or error information
     * @throws SQLParserException when the statement can't be parsed
     * @throws SQLParserInternalException when a parser internal error occurs
     */
    public SQLControlParseResult parseControlStatement(String stmt) throws SQLParserException, 
    SQLParserInternalException
    {
        SQLParseResult parseResult= super.parse(stmt);
        return (SQLControlParseResult) parseResult;   
    }
    
    /**
     * Convenience proxy method for {@link SQLParserManager#parseScript(String)}
     * casting the returned <code>SQLParseResult</code>s to
     * <code>SQLQueryParseResult</code>s.
     * @see SQLParserManager#parseScript(String)
     * @param script the SQL DML statements
     * @return List of <code>SQLQueryParseResult</code>s
     * @throws SQLParserException
     * @throws SQLParserInternalException
     */
    public List parseQueryScript(String script) throws SQLParserException,
                    SQLParserInternalException
    {
        return super.parseScript(script);
    }
}
