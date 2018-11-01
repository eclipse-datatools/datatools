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
package org.eclipse.datatools.sqltools.parsers.sql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.datatools.modelbase.sql.query.CallStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.sqltools.parsers.sql.lexer.AbstractSQLLexer;
import org.eclipse.datatools.sqltools.parsers.sql.lexer.SQLCharacterKindMap;
import org.eclipse.datatools.sqltools.parsers.sql.lexer.SQLVariableSymbols;
import org.eclipse.datatools.sqltools.parsers.sql.postparse.PostParseProcessor;
import org.eclipse.datatools.sqltools.parsers.sql.postparse.PostParseProcessorConfiguration;
import org.eclipse.datatools.sqltools.parsers.sql.util.EObjectPrinter;


/**
 * @author ckadner
 *
 * DOCME
 * 
 */
public abstract class SQLParserManager {
    
    public int statementCount = 0; 
    public int astElementCount = 0;
    public int byteCount = 0;
    public int timeCount = 0;
    
        
    
    boolean debug = false;
    public boolean debugPerformance = false;
    
    
    private SQLQuerySourceFormat sourceFormat = SQLQuerySourceFormat.copyDefaultFormat();
    
    private List postParseProcessors = getInternalDefaultPostParseProcessorList();
    
    /** Constant to supress error diagnosing. */
    public static final int ERROR_DIAGNOSING_NONE = AbstractSQLParser.ERROR_DIAGNOSING_NONE;

    /** Constant for unlimited error diagnosing. */
    public static final int ERROR_DIAGNOSING_UNLIMITED = AbstractSQLParser.ERROR_DIAGNOSING_UNLIMITED;

    /** Default time limit for error diagnosing in ms, value: 1500  */
    public static final int ERROR_DIAGNOSING_TIME_MAX_DEFAULT = 1500;
    
    /** Default limit for number of errors diagnosed, value: 5  */
    public static final int ERROR_DIAGNOSING_NUMBER_MAX_DEFAULT = 5;
    
    
    private int errorDiagnosingTimeMax = ERROR_DIAGNOSING_TIME_MAX_DEFAULT;
    private int errorDiagnosingNumberMax = ERROR_DIAGNOSING_NUMBER_MAX_DEFAULT;
    
	
    /**
     * Returns the static list of default
     * <code>{@link PostParseProcessor}</code>s that can be used
     * with <code>{@link #parse(String)}</code> or
     * <code>{@link #parseScript(String)}</code>. Returned List at least consists of:
     * <ol>
     *   <li>{@link TableReferenceResolver}</li>
     *   <li>{@link DataTypeResolver}</li>
     * </ol>
     * <p>
     * <b>Note: </b>
     * Modifications to the properties of the <code>PostParseProcessor</code>
     * s contained in the returned list, will affect post-parse-process behaviour of
     * <code>{@link #parse(String)}</code> or
     * <code>{@link #parseScript(String)}</code> on a different instance of
     * <code>SQLParserManager</code> using the List of
     * <code>PostParseProcessor</code>s returned by invoking this method on
     * that instance, as the List of default <code>PostParseProcessor</code>s
     * is hold by a static reference in the specific <code>SQLParserManager</code>.
     * 
     * @see #copyDefaultPostParseProcessorList()
     * @return List of <code>PostParseProcessor</code> s
     */
    public abstract List getDefaultPostParseProcessorList();
    
    /**
     * Returns a value-copy of the static list of default
     * <code>{@link PostParseProcessor}</code> s returned by
     * <code>{@link #getDefaultPostParseProcessorList()}</code>. That list
     * can be used or be extended for use with
     * <code>{@link #parse(String)}</code> or
     * <code>{@link #parseScript(String)}</code>. Returned List consists at
     * least of:
     * <ol>
     * <li>{@link TableReferenceResolver}</li>
     * <li>{@link DataTypeResolver}</li>
     * </ol>
     * <p>
     * Modifications to the properties of the <code>PostParseProcessor</code>
     * s contained in this list, will not affect post-parse-process behaviour of
     * <code>{@link #parse(String)}</code> or
     * <code>{@link #parseScript(String)}</code> on a different instance of
     * <code>SQLParserManager</code> using the List of
     * <code>PostParseProcessor</code>s returned by invoking this method on
     * that instance.
     * 
     * @return List of <code>PostParseProcessor</code>s
     */
    public abstract List copyDefaultPostParseProcessorList();
    
    
    
    /** will be used as hidden internal postparse processors, to 
     * distinguish between:
     * - no postparseprocessing required, therefore hidden
     * post parse processing
     *  and
     * - explicitly required postparseprocessing using the
     * {@link #getDefaultPostParseProcessorList()}. That changes what we return
     * and if we raise Exceptions for postparseprocessing,
     * we do want to hide exceptions for internal post parse processing, right!
     * @see #makeAST(String, SQLQuerySourceFormat, boolean, List, boolean) */
    protected abstract List getInternalDefaultPostParseProcessorList();
    
    
    private SQLCharacterKindMap characterKindMap = new SQLCharacterKindMap();

    protected void setCharacterKindMap(SQLCharacterKindMap characterKindMap)
    {
        if (characterKindMap == null) {
            characterKindMap = new SQLCharacterKindMap();
        }
        this.characterKindMap = characterKindMap;
        setupCharacterKindMap(getSourceFormat());
    }
    
    protected SQLCharacterKindMap getCharacterKindMap()
    {
        if (this.characterKindMap == null) {
            setCharacterKindMap(new SQLCharacterKindMap());
        }
        return this.characterKindMap;
    }
    
    final protected void setVariableCharacterKind(char character, int charKind)
    {
        getCharacterKindMap().setTokenKind(character, charKind);
    }
    
    /**
     * Modifies the given Token kind map <code>SQLCharacterKindMap</code>
     * so it reflects the character mappings in the given
     * <code>SQLQuerySourceFormat</code>. 
     * @param p_format
     * @param p_charKindMap
     */
    protected void setupCharacterKindMap(SQLQuerySourceFormat p_format)
    {
        //TODO: interface the Lexersym.Char_KIND so that for Lexer modifications
        //      we know how to resolve the compile error!
        
        setVariableCharacterKind(p_format.getStatementTerminator(), SQLVariableSymbols.STMT_TERM);
        setVariableCharacterKind(p_format.getHostVariablePrefix(), SQLVariableSymbols.HOST_VAR_PREFIX);
        setVariableCharacterKind(p_format.getParameterMarker(), SQLVariableSymbols.PARAM_MARK);
        setVariableCharacterKind(p_format.getDelimitedIdentifierQuote(), SQLVariableSymbols.DELIM_ID_QT);
        
        // more.... like host variable prefix, identifier delimiter,...
    }
    
    
    public final List getPostParseProcessors()
    {
        // we always want to post parse process, at least the basic stuff! so
        // postParseProcessors == null really means
        // postParseProcessors == getInternalDefaultPostParseProcessorList()
        // but we want to hide that from the public
        if (postParseProcessors == getInternalDefaultPostParseProcessorList()) {
            return null;
        } else {
            return postParseProcessors;
        }
    }
    
    /**
     * Sets the post parse processors used for semantical checks and resolvings
     * after successful syntactical parsing.
     * 
     * @param postParseProcessors
     */
    public final void setPostParseProcessors(List postParseProcessors)
    {
        // we always want to post parse process, at least the basic stuff! so
        // postParseProcessors == null really means
        // postParseProcessors == getInternalDefaultPostParseProcessorList()
        // but we want to hide that from the public
        if (postParseProcessors == null || postParseProcessors.size() == 0) {
            postParseProcessors = getInternalDefaultPostParseProcessorList();
        }
        this.postParseProcessors = postParseProcessors;
    }
    
    
    public SQLQuerySourceFormat getSourceFormat()
    {
        if (sourceFormat == SQLQuerySourceFormat.SQL_SOURCE_FORMAT_DEFAULT
                        || sourceFormat == null)
        {
            sourceFormat = SQLQuerySourceFormat.copyDefaultFormat();
        }
        return sourceFormat;
    }

    /**
     * Sets the SQL source format options for the following parses.
     * <p>
     * <b>Note: </b> referential modifications on the given
     * <code>sourceFormat</code> will not be reflected in the parse results,
     * unless this method is called after modifying the
     * <code>SQLQuerySourceFormat</code> and before the parse.
     * 
     * @param sourceFormat
     */
    public void setSourceFormat(SQLQuerySourceFormat sourceFormat)
    {
        if (sourceFormat == SQLQuerySourceFormat.SQL_SOURCE_FORMAT_DEFAULT
                        || sourceFormat == null)
        {
            sourceFormat = SQLQuerySourceFormat.copyDefaultFormat();
        }
        this.sourceFormat = sourceFormat;
        setupCharacterKindMap(sourceFormat);
    }

    
    
    /**
     * Configures this <code>SQLParserManager</code>  for the following parses.
     * 
     * The result of a call to this method is equal to the result of calls to
     * the methods
     * {@link #setSourceFormat(SQLQuerySourceFormat)} and 
     * {@link #setPostParseProcessors(List)}.
     * 
     * @param sourceFormat
     * @param postParseProcessors
     */
    public void configParser(SQLQuerySourceFormat sourceFormat, List postParseProcessors)
    {
        setSourceFormat(sourceFormat);
        setPostParseProcessors(postParseProcessors);
    }
   
    /**
     * Configures this <code>SQLParserManager</code>'s
     * <code>PostParseProcessor</code>s with the arguments given in the
     * <code>PostParseProcessorConfiguration</code> <code>config</code>. If
     * this <code>SQLParserManager</code> was not yet setup for
     * post-parse-processing, it herewith will be setup with the list of default
     * <code>PostParseProcessor</code> s returned by
     * <code>{@link #copyDefaultPostParseProcessorList()}</code> and
     * configured according to the given
     * <code>PostParseProcessorConfiguration</code> <code>config</code>.
     * That method is especially useful when the runtime type of
     * <code>PostParseProcessor</code>s is unknown and specific setter methods
     * therefore can not be invoked.
     * 
     * @param config the <code>PostParseProcessorConfiguration</code>
     *        providing the configuration arguments for this
     *        <code>SQLParserManager</code>'s <code>PostParseProcessor</code>s
     */
    public void configPostParseProcessors(PostParseProcessorConfiguration config)
    {
        // if no postParseProcessors are set, we set 
        if (postParseProcessors == null
                || postParseProcessors == getInternalDefaultPostParseProcessorList())
        {
            // use setter to allow overwriting methods
            setPostParseProcessors( copyDefaultPostParseProcessorList() );
        }
        
        List p3s = getPostParseProcessors();
        
        for (Iterator iter = p3s.iterator(); iter.hasNext();) {
            PostParseProcessor p3 = (PostParseProcessor) iter.next();
            p3.config(config);
        }
    }
    
    // *************************************************************************
    // protected methods, to be implemented! design pattern: template method
    // *************************************************************************
    
    protected abstract SQLParser createParser(AbstractSQLLexer lexer, boolean syntaxCheckOnly)
    	throws SQLParserInternalException;
    
    protected abstract AbstractSQLLexer createLexer(String input);
    
    //protected abstract SQLParserFactory getParserFactory();
    
    protected abstract SQLParseResult createParseResult(SQLStatement stmt, List errorList);
    
    protected abstract SQLParseResult createControlParseResult(SQLStatement stmt, List errorList);

    public SQLParserManager()
    {
        configParser(SQLQuerySourceFormat.copyDefaultFormat(),
                        getInternalDefaultPostParseProcessorList());
    }
    
    public SQLParserManager(SQLQuerySourceFormat sourceFormat, List postParseProcessors)
    {
        configParser(sourceFormat, postParseProcessors);
    }

    protected AbstractSQLLexer lexer = null;
    protected AbstractSQLParser parser = null;
    
    protected AbstractSQLLexer getLexer(String input)
    {
        // we still have to create a new parser for each call, work out with
        // Gerry to keep one parser instance
//        if (lexer == null)
        {
            lexer = createLexer(input);
        }
//        else
//        {
//            lexer.initialize(input.toCharArray(),"");
//            lexer.resetCommentTokens();
//        }
        return lexer;
    }
    
    protected AbstractSQLParser getParser(AbstractSQLLexer lexer, boolean syntaxCheckOnly)throws SQLParserInternalException
    {
        // we still have to create a new parser for each call, work out with
        // Gerry to keep one parser instance
        if (parser == null)
        {
            parser = createParser(lexer, syntaxCheckOnly);
        }
        else
        {
            parser.resetParser(lexer);
            parser.setCheckStmtOnly(syntaxCheckOnly);
        }
        return parser;
    }
    

    
	/**
	 * <b>Note:</b> If <code>syntaxCheckOnly == false</code> 
	 * List of SQLQueryParseResult objects will be returned, if
	 * <code>syntaxCheckOnly == true</code> List of QueryStatements will be returned
	 * 
	 * <b>Note:</b> this method is synchronized to avoid concurencies on the
	 * internal parser state for shared instances of this <code>SQLParserManager</code>
	 * 
	 * TODO: what if null given? null return?
	 * 
	 * @param syntaxCheckOnly TODO
	 * @param input
	 *  
	 * @return if <code>syntaxCheckOnly == false</code> List of SQLQueryParseResult objects,
	 *   if <code>syntaxCheckOnly == true</code> List of QueryStatements
	 * 
	 * @throws SQLParserException
	 * @throws SQLParserInternalException
	 * 
	 * @throws java.io.IOException
	 */
	protected synchronized List makeAST(String input, boolean syntaxCheckOnly) 
		throws SQLParserException, SQLParserInternalException 
	{
	    List resultList = new ArrayList(); // either of ParseResults (if p_postParseProcessors != null)
	                            // or of QueryStatements (if p_postParseProcessors == null)
	    
	    if (input == null || input.trim().length() == 0) {
	        return resultList;
	    }
	    
        
	    // TODO: this is a workaround for an LPG bug,
	    // see TestSQLQueryPasrer.testSqlDmlParser000_no_ending_semicolon_single_stmt
	    input = input.concat( String.valueOf(sourceFormat.getStatementTerminator()) );

        if (debugPerformance) timeBeforeLexerInit = System.currentTimeMillis();
        AbstractSQLLexer lexer = getLexer(input); // Create the input stream
        if (debugPerformance) timeAfterLexerInit = System.currentTimeMillis();
        
        if (debugPerformance) timeBeforeParserInit = timeAfterLexerInit;
        AbstractSQLParser sqlmodelParser = getParser(lexer,syntaxCheckOnly);
        if (debugPerformance) timeAfterParserInit = System.currentTimeMillis();
        
        if (debugPerformance) {
            timeLexerInit = timeBeforeParserInit - timeBeforeLexerInit;
            timeParserInit = timeAfterParserInit - timeBeforeParserInit;
            System.out.println("[init time Lexer: "+timeLexerInit+", Parser: "+timeParserInit+"]");
        }
		
	    //TODO: statistics - remove that gimmik
	    //byteCount += p_inputChars.length;
	    byteCount += input.length();
	    
        
        sqlmodelParser.setErrorDiagnosingTimeMax(getErrorDiagnosingTimeMax());
        sqlmodelParser.setErrorDiagnosingNumberMax(getErrorDiagnosingNumberMax());
        
	    
	    try
	    {
			
			
		    timeBeforeLexing = timeAfterParserInit;
            // TODO: get rid of second time referring to parser here lexer.lexer() is enough
			lexer.lexer(sqlmodelParser); // Lex the stream to produce the token stream
            
            if (debug) parser.dumpTokens();
            
            if (debugPerformance) timeAfterLexing = System.currentTimeMillis();
            if (debugPerformance) timeLexing = timeAfterLexing - timeBeforeLexing;
            
            if (debugPerformance) timeBeforeParsing = timeAfterLexing;
            List script = sqlmodelParser.parser(); // Parse the token stream to produce an AST
            
            long time = 0;
            if (debugPerformance) {
                timeAfterParsing = System.currentTimeMillis();
                timeParsing = timeAfterParsing - timeBeforeParsing;
                System.out.println("[time lexing: "+timeLexing + ", parsing: "+timeParsing+ ", input size (byte): "+input.length()+"]");
    			time = timeAfterParsing - timeBeforeLexing;
    			timeCount += time;
    		    statementCount += script.size();
            }
		    
            
		    
			if (debug) {
				SQLParserLogger.getLogger().writeInfo(
                        "Successful parse of:                     " + //$NON-NLS-1$
						"                       "+((debugPerformance)?"("+time+"ms)":"")); //$NON-NLS-1$ //$NON-NLS-2$
				if (script!=null) printAST(script);
			}
			
			
			
			resultList = script;
			
			if (syntaxCheckOnly)
			{
			    //resultList = wrapQueryStmtListInParseResults(script);
			    return script;
			}
			else
			{
				List queryElements = sqlmodelParser.getASTElementList();
				
			    //TODO: statistics - remove that gimmik
			    astElementCount += queryElements.size();
				
			    // if we have gotten a List of PostParseProcessors different from
			    // the internal list getInternalDefaultPostParseProcessorList() we 
			    // expose all the errors we found as exception or at least log 'em
			    // if not PostParseProcessors were set, we at least do the basic
			    // post parse processing to fix up column-table-references, but we
			    // don't throw/expose Exceptions
			    if (postParseProcessors != null) 
			    {
			        try {
			        	resultList = executePostParseProcessorList(script, queryElements, postParseProcessors);
			        }
			        catch (Exception e1) 
			        {
					    // check if we had the hidden postparseprocessing (to fix at
			            // least refernces and simple types), if so we don't want to
			            // throw a SQLParserException or log the exception
			            if (postParseProcessors != getInternalDefaultPostParseProcessorList()) 
			            {
				            // only throw the exception we expect, as post parse
			                // processing is not crucial
			                if (e1 instanceof SQLParserException)
			                {
			                    throw (SQLParserException) e1;
			                }
			                //TODO: log the exception not console print it,
			                // we don't wanna throw the exception, as post
			                // parse processsing is not vital to the whole!
			                e1.printStackTrace();
			            }
		                // still, we need to return a List of SQLQueryParseResult objects!
		                resultList = wrapQueryStmtListInParseResults(script);
			        }
			    } 
			}
	        
			
			return resultList;
			
		
//TODO: work out the ExceptionHandling!
// null input
// empty String as input
		}
	    catch (Exception e)
	    {
		    if (e instanceof SQLParserException)
		    {
                throw (SQLParserException)e;
		    } 
		    else if (e instanceof SQLParserInternalException)
		    {
		        throw (SQLParserInternalException)e;
		    } 
		    else
		    {
		        SQLParserInternalException pre = new SQLParserInternalException();
		        pre.initCause(e);
		        throw pre;
		    }
	    }
	}
	
	
	


    /**
	 * <code>SQLQueryParseResult</code>s will have <code>errorList</code> == <code>null</code>
	 * 
     * @param script
     * @return 
     */
    private List wrapQueryStmtListInParseResults(List script)
    {
        List resultList = new ArrayList();
        
        if (script != null)
        {
            for (Iterator it = script.iterator(); it.hasNext();)
            {
                SQLStatement stmt = (SQLStatement) it.next();
                SQLParseResult parseResult = null;
                if (stmt instanceof QueryStatement) {
                    parseResult = createParseResult(stmt, null);
                }
                else if (stmt instanceof CallStatement) {
                    parseResult = createControlParseResult(stmt, null);
                }
                if (parseResult != null) {
                    resultList.add( parseResult );
                }
                
            }
        }
        
        return resultList;
    }


    /**
	 * Executes the List of PostParseProcessors for a QueryStatement
	 * @param queryStmtList
	 * @param queryElements the List of the <code>SQLQueryObject</code>
	 * 		components of the <code>QueryStatement</code>s in the given
	 * 		<code>queryStmtList</code>
	 * @param postParseProcessors
	 * @return List of <code>SQLQueryParseResult</code>s
	 * @throws SQLParserException
	 */
	private List executePostParseProcessorList(List queryStmtList,
	                                                  List queryElements,
	                                                  List postParseProcessors) 
											   throws SQLParserException 
	{
	    List parseResultList = new ArrayList();
	    
        if (postParseProcessors != null) 
        {
            for (Iterator scriptIt = queryStmtList.iterator(); scriptIt.hasNext();)
            {
                SQLQueryObject queryStmt = (SQLQueryObject) scriptIt.next();
                List errorList = postParseProcess(queryStmt, queryElements, postParseProcessors);
                // TODO: think about how to go with internal P3s
                if (postParseProcessors == getInternalDefaultPostParseProcessorList())
                {
                    errorList = null;
                }
                if (queryStmt instanceof QueryStatement)
                {  
                    parseResultList.add(createParseResult((QueryStatement)queryStmt,errorList));
                }
                else if (queryStmt instanceof CallStatement)
                {
                    parseResultList.add(createControlParseResult((CallStatement)queryStmt,errorList));
                }
            }
        } 
        return parseResultList;
	}

	
	/**
	 * Post parse processes a <code>QueryStatement</code> by its very elements.
	 * The given List <code>queryElements</code> must contain the
	 * <code>SQLQueryObject</code>-elements of the given
	 * <code>queryStatement</code>, beginning with the first
	 * element in the List. The order of the elements reflects the order in
	 * which the parser reduced the rules of the SQL grammar and created the
	 * <code>SQLQueryObject</code>-elements and built up the
	 * <code>QueryStatement</code>. The List <code>queryElements</code> can
	 * contain elements of more than only one <code>QueryStatement</code>,
	 * however it must begin only with <code>SQLQueryObject</code>s that are
	 * elements of the given <code>queryStatement</code>.
	 * The given <code>postParseProcessors</code> will be invoked in order,
	 * one after another, each being invoked for every of its candidate
	 * types contained in the list <code>queryElements</code>, that are part
	 * of the given <code>queryStatement</code>.
	 * <p><b>Note:</b> 
	 * After processing the <code>queryStatement</code> with all of the given
	 * <code>postParseProcessor</code>s, the List <code>queryElements</code>
	 * will get removed all the elements the are also elements of the given
	 * <code>queryStatement</code> to have the List ready for processing the
	 * next <code>QueryStatement</code>. That means especially that this method
	 * can only be called once per <code>QueryStatement</code> using the same
	 * <code>queryElements</code> List! 
	 * </p>
     * @param queryStatement <code>QueryStatement</code> to be processed
	 * @param queryElements List of <code>SQLQueryObject</code>s containing the
	 * 			elements of the given <code>queryStatement</code>
	 * @param postParseProcessors List of <code>PostParseProcessor</code>s
	 * @return list of <code>SQLParseErrorInfo</code> objects
     */
    private List postParseProcess(SQLQueryObject queryStatement,  
                                         List queryElements, 
                                         List postParseProcessors) throws SQLParserException
    {
        // the list of AST elements contains more than just the elements of one
        // statement! so stop when the queryStatement is reached,
        // referentially remove elements from list before returning after last
        // postparseprocessor was run,
        // reset the postparse processors after stmt completed
        List errorList = new ArrayList();
        
        for (Iterator processIt = postParseProcessors.iterator(); processIt.hasNext();)
        {
            PostParseProcessor ppp = (PostParseProcessor) processIt.next();
            boolean isLastProcessor = !processIt.hasNext();
            
            
            for (Iterator queryIt = queryElements.iterator();queryIt.hasNext();)
            {
                Object queryElement = queryIt.next();
                
                if (queryElement instanceof SQLQueryObject) 
                {
                    SQLQueryObject queryObject = (SQLQueryObject) queryElement;
                    
                    if (isPostParseCandidate(queryObject, ppp))
	                {
                        List elementErrors = ppp.process(queryObject);
                        
                        if (elementErrors != null)
                        {
                            errorList.addAll(elementErrors);
                        }
	                }
                }
                
                
                // remove this QueryStatement's elements from the queryElements
                // list, if no more post parse processes are to be done for the
                // context of the currently processed statement
                if (isLastProcessor)
                {
                    queryIt.remove();
                }
                
                // don't process elements that are not part of the given QueryStatement
                if (queryElement == queryStatement)
                {
                    break;
                }
                
            }

            // update the queryElements list, if the postParseProcessor modified
            // or deleted elements of the queryElements list
            Map queryElemSubstMap = ppp.getParsedObjectsReplacementMap();
            if (queryElemSubstMap != null)
            {
                Set elementsToSubstituteMappings = queryElemSubstMap.entrySet();
                for (Iterator replaceIt = elementsToSubstituteMappings
                                .iterator(); replaceIt.hasNext();)
                {
                    Map.Entry elementToSubstitute = (Map.Entry) replaceIt.next();
                    Object oldElement = elementToSubstitute.getKey();
                    Object substitute = elementToSubstitute.getValue();
                    Collections.replaceAll(queryElements,oldElement,substitute);
                }
            }
            
            // reset the PostParseProcessor for the next QueryStatement
            ppp.resetState();
            
        }
        
        return errorList;
    }

    /**
     * @param queryElement
     * @param ppp
     * @return
     */
    private boolean isPostParseCandidate(Object queryElement, PostParseProcessor ppp)
    {
        boolean isCandidate = false;
        Class[] candidateTypes = ppp.getProcessCandidateTypes();
        
        for (int i = 0; i < candidateTypes.length; i++)
        {
            Class candidate = candidateTypes[i];
            
            isCandidate = candidate.isAssignableFrom(queryElement.getClass());
            
            if (isCandidate) break;
        }
        
        return isCandidate;
    }

    
	
	/** 
	 * Parses the given String representation of a SQL statement into an
	 * instance of the {@link SQLQueryObject} model. Returns the
	 * instance of the <code>SQLQueryObject</code> model and a List of
	 * {@link SQLParseErrorInfo} objects wraped in a {@link SQLQueryParseResult}.
	 * For the parameter <code>format</code> you can use a copy of
	 * the default <code>SQLQuerySourceFormat</code> returned by
	 * {@link SQLQuerySourceFormat#copyDefaultFormat()}.
	 * For the parameter <code>postParseProcessors</code> you can use the
	 * default List of <code>PostParseProcessor</code>s of the
	 * <code>SQLParserManager</code>
	 * {@link #getDefaultPostParseProcessorList()}.
	 * <p><b>Note:</b> During the post parse processing, no
	 * <code>Exception</code>s other than <code>SQLParserException</code> will
	 * be thrown, as post parse processing is not considered to be crucial for
	 * the success of a parse. Any other type of <code>Exception</code> that
	 * shall be trown during post parse processing has to be wrapped within a
	 * <code>SQLParserException</code>. For that purpose you might subclass an
	 * existing <code>PostParseProcessor</code> and overwrite its
	 * <code>process(SQLQueryObject)</code> method, catching the desired
	 * <code>Exception</code> and throwing it wrapped into a new
	 * <code>SQLParserException</code>.
	 * </p>
	 * TODO: doc behaviour
	 * If this <code>SQLParserManager</code> 
	 * a very basic standard post parse processing will be done, using the
	 * {@link #getDefaultPostParseProcessorList()} .
	 * 
     * <b>Note:</b> <code>SQLParserException</code> will be thrown,
     *             if the given <code>stmt</code> contains more than one SQL
     *             statement.
	 * 
	 * @param stmt the SQL text input to parse
	 * @param format optional the source format options, you can explicitly use
	 *       the default {@link SQLQuerySourceFormat#copyDefaultFormat()}, that
	 * 		 will be used if <code>format</code> is not specified 
	 * @param postParseProcessors list of <code>PostParseProcessor</code>s to be
	 * 		 invoked in the post parse phase, ordering is important as the first
	 * 		 <code>PostParseProcessor</code> in the list will get invoked first,
	 *       you can use {@link #getDefaultPostParseProcessorList()}
	 * 
     * @return a {@link SQLQueryParseResult}s containing the
     * 		 parsed input as <code>QueryStatement</code> and a List of
     * 		 {@link SQLParseErrorInfo} objects containing sementical errors
     * 		 found during the post parse process
	 * 
	 * @throws SQLParserException if the input is not accepted by the parser
     * @throws SQLParserException
     *             if the given <code>stmt</code> contains more than one SQL
     *             statement
	 * @throws SQLParserInternalException if an internal error occurs
	 */
	public SQLParseResult parse(String stmt)
			throws SQLParserException, SQLParserInternalException 
	{
	    List resultList = null;
	    
	    //TODO return resultList as opposed to querystmt list
        resultList = makeAST(stmt, false);
        
        // as we parsed a single statement we get back a one-element SQLQueryParseResult list
        if (resultList.isEmpty()){
	        return null;
	    }
	    else {
	        return (SQLParseResult) resultList.get(0);
	    }
    }

	
	
    /** 
	 * Parses the given String representation of a SQL statement or a list of
	 * SQL statements separated by the statement terminator provided by the
	 * given <code>format</code>
	 * ({@link SQLQuerySourceFormat#getStatementTerminator()}). Returns an
	 * instance of the {@link SQLQueryObject} model and a List of
	 * {@link SQLParseErrorInfo} objects wraped in a {@link SQLQueryParseResult} for
	 * each SQL statement contained in the given SQL <code>script</code>.
	 * For the parameter <code>format</code> you can use a copy of
	 * the default <code>SQLQuerySourceFormat</code> returned by
	 * {@link SQLQuerySourceFormat#copyDefaultFormat()}.
	 * For the parameter <code>postParseProcessors</code> you can use the
	 * default List of <code>PostParseProcessor</code>s of the
	 * <code>SQLParserManager</code>
	 * {@link #getDefaultPostParseProcessorList()}.
	 * <p><b>Note:</b> During the post parse processing, no
	 * <code>Exception</code>s other than <code>SQLParserException</code> will
	 * be thrown, as post parse processing is not considered to be crucial for
	 * the success of a parse. Any other type of <code>Exception</code> that
	 * shall be trown during post parse processing has to be wrapped within a
	 * <code>SQLParserException</code>. For that purpose you might subclass an
	 * existing <code>PostParseProcessor</code> and overwrite its
	 * <code>process(SQLQueryObject)</code> method, catching the desired
	 * <code>Exception</code> and throwing it wrapped into a new
	 * <code>SQLParserException</code>.
	 * </p>
	 * 
     * @param script the SQL script (separated list of statements) to be parsed
	 * @param format optional the source format options, you can explicitly use
	 *       the default {@link SQLQuerySourceFormat#copyDefaultFormat()}, that
	 * 		 will be used if <code>format</code> is not specified 
     * @param postParseProcessors List of 
     * 		{@link org.eclipse.datatools.sqltools.parsers.sql.query.postparse.PostParseProcessor}s
     * 		 to be invoked in the post parse phase, 
     * 		 ordering is important as the first
	 * 		 <code>PostParseProcessor</code> in the list will get invoked first,
	 *       you can use {@link #getDefaultPostParseProcessorList()}
	 * 
     * @return a List of {@link SQLQueryParseResult}s for each
     * 		 parsed SQL statement, containing the parsed input as
     * 		 <code>QueryStatement</code> and a List of
     * 		 {@link SQLParseErrorInfo} objects containing sementical errors
     * 		 found during the post parse process
     * 
     * @throws SQLParserException
     *             if an SQL syntactical (or semantical) error occurs in the
     *             given <code>stmt</code>
     * @throws SQLParserInternalException
     *             if an internal error occurs
	 * 
	 * @see SQLQuerySourceFormat
	 * @see PostParseProcessor
	 */
	public List parseScript(String script) 
			throws SQLParserException, SQLParserInternalException 
	{
	    return makeAST(script, false);
	}
	
	
    /**
     * Parses the given String representation of a SQL statement and returns an
     * instance of the <code>@link SQLQueryObject</code> model
     * ({@link QueryStatement}) which will have no references and contain no
     * structural information about the given SQL statement.
     * 
     * <b>Note:</b> <code>SQLParserException</code> will be thrown,
     *             if the given <code>stmt</code> contains more than one SQL
     *             statement.
     * 
     * @param stmt
     *            the SQL statement to be parsed
	 * @param format optional the source format options, you can explicitly use
	 *       the default {@link SQLQuerySourceFormat#copyDefaultFormat()}, that
	 * 		 will be used if <code>format</code> is not specified 
     * 
     * @return an empty <code>QueryStatement</code> object only to indicate the
     *          type of SQL statement given
     * 
     * @throws SQLParserException
     *             if an SQL syntactical error occurs in the
     *             given <code>stmt</code>
     * @throws SQLParserException
     *             if the given <code>stmt</code> contains more than one SQL
     *             statement
     * @throws SQLParserInternalException
     *             if an internal error occurs
     * 
     */
	public QueryStatement checkSyntax(String stmt) 
			throws SQLParserException, SQLParserInternalException 
	{
	    List resultsList = makeAST(stmt, true);
        if(resultsList.isEmpty()){
            return null;
        }
        else{
            return (QueryStatement)resultsList.get(0);
        }
	}
	
	
    /**
     * Parses the given SQL script and returns a List of
     * {@link QueryStatement}s, which will have no references and contain no
     * structural information about the given SQL statement. 
     * Only syntax checking is performed.
     * 
     * @param script
     *            the SQL script (separated list of statements) to be parsed
     * 
     * @return a List of empty <code>QueryStatement</code>s only to indicate the
     *          types of SQL statements given
     * 
	 * @param format optional the source format options, you can explicitly use
	 *       the default {@link SQLQuerySourceFormat#copyDefaultFormat()}, that
	 * 		 will be used if <code>format</code> is not specified 
     * 
     * @throws SQLParserException
     *             if an SQL syntactical error occurs in the
     *             given <code>stmt</code>
     * @throws SQLParserInternalException
     *             if an internal error occurs
     * 
     */
	public List checkSyntaxScript(String script) 
			throws SQLParserException, SQLParserInternalException 
	{
	    return makeAST(script, true);
	}
	
    
    
    
    /**
     * @return Returns the errorDiagnosingNumberMax.
     */
    public int getErrorDiagnosingNumberMax() {
        return errorDiagnosingNumberMax;
    }
    /**
     * @param errorDiagnosingNumberMax The errorDiagnosingNumberMax to set.
     */
    public void setErrorDiagnosingNumberMax(int errorDiagnosingNumberMax) {
        this.errorDiagnosingNumberMax = errorDiagnosingNumberMax;
    }
    /**
     * @return Returns the errorDiagnosingTimeMax.
     */
    public int getErrorDiagnosingTimeMax() {
        return errorDiagnosingTimeMax;
    }
    /**
     * @param errorDiagnosingTimeMax The errorDiagnosingTimeMax to set.
     */
    public void setErrorDiagnosingTimeMax(int errorDiagnosingTimeMax) {
        this.errorDiagnosingTimeMax = errorDiagnosingTimeMax;
    }

    
	
	public void printParseRuntimeException(SQLParserException e1) {
		e1.printParseRuntimeException();
	}

    
    /**
     * @param errorList
     * @param queryStmt TODO
     */
    public void printErrorList(SQLParseResult parseResult)
    {
        if (parseResult == null) {return;}

        List errorList = parseResult.getErrorList();
        SQLStatement sqlStmt = parseResult.getSQLStatement();
        
        
        //QueryStatement queryStmt = (QueryStatement) sqlStmt;
        
        if (errorList == null || errorList.isEmpty()) {return;}
        
        SQLParserLogger.getLogger().writeLog("Errors encountered during post parse processing of statement:"); //$NON-NLS-1$
        SQLParserLogger.getLogger().writeLog(sqlStmt.getSQL());
        for (Iterator errorIt = errorList.iterator(); errorIt.hasNext();)
        {
            SQLParseErrorInfo errorInfo = (SQLParseErrorInfo) errorIt.next();
            SQLParserLogger.getLogger().writeLog("   "+errorInfo.getParserErrorMessage()+ //$NON-NLS-1$
                            " at line:column "+ //$NON-NLS-1$
                            errorInfo.getLineNumberStart()+":"+ //$NON-NLS-1$
                            errorInfo.getColumnNumberStart()+
                            " to line:column "+ //$NON-NLS-1$
                            errorInfo.getLineNumberEnd()+":"+ //$NON-NLS-1$
                            errorInfo.getColumnNumberEnd()+
                            " \""+ //$NON-NLS-1$
                            errorInfo.getErrorSourceText()+"\""+ //$NON-NLS-1$
                            ", expected: \""+ //$NON-NLS-1$
                            errorInfo.getExpectedText()+"\"" //$NON-NLS-1$
                            );
        }
    }


	
	/** 
	 * Prints the SQL source and the abstract syntax tree elements (SQLObject)
	 * in the given List of SQLStatements. 
	 */
	public void printAST(List sqlStmts) {
		StringBuffer buff = new StringBuffer();
		int i=0;
		for(Iterator iter = sqlStmts.iterator(); iter.hasNext(); ++i) {
		    SQLStatement next = (SQLStatement)iter.next();
		    SQLParserLogger.getLogger().writeInfo("\nStatement "+i+": "+ next.getSQL()); //$NON-NLS-1$ //$NON-NLS-2$
		    printAST(next); 
		}
		
	}
	
	/** 
	 * Prints the SQL source and the abstract syntax tree elements (SQLQueryObject)
	 * in the given List of SQLQueryStatements. 
	 */
	public void printAST(SQLStatement p_Stmt) {
		PRINTER.printEObjectReferenceTree(p_Stmt);							
		
	}
	
	/** 
	 * Prints the SQL source of the SQLQueryStatements in the given List
	 * by calling {@link SQLQueryObject#getSQL()}. 
	 */
	public void printSQL(List sqlQueryStmts) {
		StringBuffer buff = new StringBuffer();
		int i=0;
		for(Iterator iter=sqlQueryStmts.iterator();iter.hasNext();++i) {
		    SQLQueryObject next = (SQLQueryObject)iter.next();
		    
		    SQLParserLogger.getLogger().writeInfo("\n"+next.getClass().getName()+" "+i+":\n"+next.getSQL()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		
	}
	
	public EObjectPrinter PRINTER = new EObjectPrinter();
    
    
    
    
    private long timeBeforeLexerInit;
    private long timeBeforeParserInit;
    private long timeParserInit;
    private long timeLexerInit;
    private long timeAfterParserInit;
    private long timeAfterLexerInit;
    private long timeBeforeLexing;
    private long timeAfterLexing;
    private long timeLexing;
    private long timeBeforeParsing;
    private long timeAfterParsing;
    private long timeParsing;
    
	
}
