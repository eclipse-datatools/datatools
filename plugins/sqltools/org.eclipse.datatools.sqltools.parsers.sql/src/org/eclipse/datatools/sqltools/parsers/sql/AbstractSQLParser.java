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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import lpg.lpgjavaruntime.BacktrackingParser;
import lpg.lpgjavaruntime.BadParseException;
import lpg.lpgjavaruntime.BadParseSymFileException;
import lpg.lpgjavaruntime.DiagnoseParser;
import lpg.lpgjavaruntime.IToken;
import lpg.lpgjavaruntime.LexStream;
import lpg.lpgjavaruntime.NotBacktrackParseTableException;
import lpg.lpgjavaruntime.NullExportedSymbolsException;
import lpg.lpgjavaruntime.NullTerminalSymbolsException;
import lpg.lpgjavaruntime.ParseTable;
import lpg.lpgjavaruntime.PrsStream;
import lpg.lpgjavaruntime.RuleAction;
import lpg.lpgjavaruntime.Token;
import lpg.lpgjavaruntime.UndefinedEofSymbolException;
import lpg.lpgjavaruntime.UnimplementedTerminalsException;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.parsers.sql.lexer.AbstractSQLLexer;

//DOCME
public abstract class AbstractSQLParser extends PrsStream implements RuleAction
{
    /** Constant for no error diagnosing. */
    protected static final int ERROR_DIAGNOSING_NONE = 0;

    /** Constant for unlimited error diagnosing. */
    protected static final int ERROR_DIAGNOSING_UNLIMITED = Integer.MAX_VALUE;

    /** Time in ms spent on diagnosing all parser errors, 1.5 sec */
    protected static final int ERROR_DIAGNOSING_TIME = 1500;

    /** Maximum number of parse errors diagnosed, 10 */
    protected static final int ERROR_DIAGNOSING_MAX_NUMBER = 10;

    /**
     * Time in ms spent maximum on diagnosing at least the minimum number of
     * parser errors {@link #ERROR_DIAGNOSING_MIN_NUMBER}exceeding the normal
     * error diagnosing time {@link #ERROR_DIAGNOSING_TIME}, 3 sec.
     */
    protected static final int ERROR_DIAGNOSING_TIME_LIMIT = 3000;

    /** Minimum number of parse errors diagnosed, 1 */
    protected static final int ERROR_DIAGNOSING_MIN_NUMBER = 1;
    
    /**
     * @see lpg.lpgjavaruntime.RuleAction#ruleAction(int)
     */
    public abstract void ruleAction(int rule);
    public abstract String[] orderedTerminalSymbols();
    ParseTable prs;
	protected BacktrackingParser btParser;
	// If true, only BeginStatement-actions will be executed
	/**
	 * If true, no AST but the the top - the Statement list - will
	 * be created.
	 */
	protected boolean checkStmtOnly = false;

	/**List of AST objects in the timely order of their creation,
	 * @see setSym1(Object) */
	protected List astElementList;
	protected Object lastASTElement = null;
	
	/**
	 * Subclasses may override this method to add comments to the generated AST
	 * @param commentTokens list of <code>IToken</code>
	 */
	protected void addCommentsToAST(List commentTokens)
	{
	    // to be implemented here or in extension
	}
	
	
	/** List of <code>SQLParseErrorInfo</code>s in that will be populated
	 * in case of an parse error. */
	protected List errorInfoList = null;
	protected SQLQuerySourceFormat sourceFormat; //TODO: parser should have standard format to be associated with SQLQuerySourceInfo objects

    
    /** Time limit for error diagnosing in ms, default value: {@link #ERROR_DIAGNOSING_TIME} */
	private int errorDiagnosingTimeMax = ERROR_DIAGNOSING_TIME;
    /** Limit for number of errors diagnosed, default value: {@link #ERROR_DIAGNOSING_MAX_NUMBER}  */
    private int errorDiagnosingNumberMax = ERROR_DIAGNOSING_MAX_NUMBER;

    
	public static final int K_SUFFIX = 1000;
	public static final int M_SUFFIX = 1000000;
	public static final int G_SUFFIX = 1000000000;

    
	protected AbstractSQLParser (LexStream lexStream, ParseTable prs, int EOFTsymbol, 
			SQLQuerySourceFormat p_sourceFormat, boolean checkStmtOnly)throws SQLParserInternalException
	{
	    super(lexStream);
		this.prs = prs;

		this.sourceFormat = p_sourceFormat;
		this.astElementList = new ArrayList();

		this.checkStmtOnly = checkStmtOnly;
		
        
        try
        {
            //long timeBeforeBtParserInit = System.currentTimeMillis();
            this.btParser = new BacktrackingParser(this, prs, this);
            //long timeConsumed = System.currentTimeMillis() - timeBeforeBtParserInit;
            //System.out.println("  - time consumed to init btParser: "+timeConsumed);
        }
        catch (NotBacktrackParseTableException e)
        {
            String errorMsg = "****Error: Regenerate SQLQueryParserprs.java with -BACKTRACK option"; //$NON-NLS-1$
            SQLParserLogger.getLogger().writeInfo(errorMsg);
            throw new SQLParserInternalException(e, errorMsg);
            //System.exit(1);
        }
        catch (BadParseSymFileException e)
        {
            String errorMsg = "****Error: Bad Parser Symbol File -- SQLQueryParsersym.java"; //$NON-NLS-1$
            SQLParserLogger.getLogger().writeInfo(errorMsg);
            throw new SQLParserInternalException(e, errorMsg);
            //System.exit(1);
        }


        
		try
	    {
	        //prsStream.remapTerminalSymbols(orderedTerminalSymbols(), EOFTsymbol);
	        remapTerminalSymbols(orderedTerminalSymbols(), EOFTsymbol);
	    }
	    catch(NullExportedSymbolsException e) {
	    }
	    catch(NullTerminalSymbolsException e) {
	    }
	    catch(UnimplementedTerminalsException e)
	    {
	        java.util.ArrayList unimplemented_symbols = e.getSymbols();
	        SQLParserLogger.getLogger().writeInfo("The Lexer will not scan the following token(s):"); //$NON-NLS-1$
	        for (int i = 0; i < unimplemented_symbols.size(); i++)
	        {
	            Integer id = (Integer) unimplemented_symbols.get(i);
	            SQLParserLogger.getLogger().writeInfo("    " + orderedTerminalSymbols()[id.intValue()]); //$NON-NLS-1$
	        }
	        SQLParserLogger.getLogger().writeInfo("\n"); //$NON-NLS-1$
	    }
	    catch(UndefinedEofSymbolException e)
	    {
	        SQLParserLogger.getLogger().writeInfo("The Lexer does not implement the Eof symbol " + //$NON-NLS-1$
	                           orderedTerminalSymbols()[EOFTsymbol]);
	        System.exit(12);
	    }
	}

	AbstractSQLParser(LexStream lexStream, ParseTable prs, int EOFTsymbol, boolean checkStmtOnly)
		throws SQLParserInternalException
	{
		this(lexStream, prs, EOFTsymbol, SQLQuerySourceFormat.copyDefaultFormat(), checkStmtOnly);
	}

	AbstractSQLParser(LexStream lexStream, ParseTable prs, int EOFTsymbol, SQLQuerySourceFormat sourceFormat)
		throws SQLParserInternalException
	{
		this(lexStream, prs, EOFTsymbol, sourceFormat, false);
	}






//just for DataTypes here changed return type of getSym() to SQLObject, maybe getSym should return Object, check import and template SQLQueryObject
/*		protected SQLQueryObject getSym(int p_sym) {
	    Object sym1 = btParser.getSym(p_sym);
	    try {
			return (SQLQueryObject)sym1;
		} catch (ClassCastException e) {
			throw new ClassCastException("Expected " + SQLQueryObject.class
				+ " but got " + sym1.getClass().getName() + " on token " + p_sym);
		}
	}
*/
	protected Object getSym(int p_sym) {
	    return btParser.getSym(p_sym);
	}

	//protected Object getSym(int p_sym) { return btParser.getSym(p_sym); }


	   protected List getList(int p_sym) {
	        Object sym = null;
	        sym = btParser.getSym(p_sym);
	        if (sym instanceof List) {
	            return (List) sym;
	        }
	        else if (sym == null) {
	            return (List) null;
	        }
	        else {
	            SQLParserLogger.getLogger().writeInfo("Error in getList.  Expected " + List.class //$NON-NLS-1$
	                   + " but got " + sym.getClass() + " on token " + p_sym); //$NON-NLS-1$  //$NON-NLS-2$
	            return (List) null;
	        }
	    }

	protected String getString(int p_sym) {
	    Object sym = btParser.getSym(p_sym);
	    if (sym != null) {
	        return sym.toString();
	    } else {
	        return null;
	    }
	}

	protected int getInt(int p_sym) {
	    Object sym = btParser.getSym(p_sym);
	    if (sym instanceof Integer) {
	        return ((Integer)sym).intValue();
	    } else if (sym instanceof String) {
            try {
                return Integer.parseInt((String) sym);
            }
            catch (NumberFormatException nfe) {
                // no exception handling here
            }
	    }
        return 0;
	}

	protected boolean getBoolean(int p_sym) {
	    Object sym = btParser.getSym(p_sym);
	    if (sym != null && sym instanceof Boolean) {
	        return ((Boolean) sym).booleanValue();
	    } else {
	        SQLParserLogger.getLogger().writeLog(
                    "Error in SQLQueryParser#getBoolean(int)." +  //$NON-NLS-1$
	        		" Couldn't retrieve boolean value, returned false."); //$NON-NLS-1$
	        return false;
	    }
	}

	/**
	 * Set the span by defining the beginning and ending tokens
	 * @param p_firstToken First token in span
	 * @param p_lastToken Last token in span
	 */
	protected void setSpan( SQLQueryObject p_stmt) {
	    p_stmt.getSourceInfo().setSourceSnippet(getSpan());
	    p_stmt.getSourceInfo().setSpanStartOffset(getSpanStartOffset());
	    p_stmt.getSourceInfo().setSpanEndOffset(getSpanEndOffset());
	    int i = btParser.getFirstToken();
	    p_stmt.getSourceInfo().setColumnNumberStart( getColumn(i) );
	    p_stmt.getSourceInfo().setLineNumberStart( getLine(i) );
	    i = btParser.getLastToken();
	    p_stmt.getSourceInfo().setColumnNumberEnd( getEndColumn(i) );
	    p_stmt.getSourceInfo().setLineNumberEnd( getEndLine(i) );
	}

	protected String getSpan() {
	    int beg = getSpanStartOffset();
		int end = getSpanEndOffset();
		return getSpan(beg, end);
	}

	protected String getSpan(int beg, int end) {
	    return String.valueOf(getInputChars(),beg, end-beg+1);
	}

	protected int getSpanStartOffset() {
		//return prsStream.getStartOffset(protected int getToken(1));
		return getStartOffset(btParser.getFirstToken());
	}

	protected int getSpanEndOffset() {
		//return prsStream.getEndOffset(prsStream.peek()-1);
		return getEndOffset(btParser.getLastToken());
	}
	
	protected IToken getFollowingToken() {
	    // btParser.getLastToken(int) gives last Token of a local rule symbol like in getSym(2)
	    return getTokenAt( getNext(btParser.getLastToken()) );
	}
	
//	protected boolean isFollowingTokenStmtTerm(int stmtTermTokenKind) {
//	    return getFollowingToken().getKind() == stmtTermTokenKind; 
//	}
	

	protected int getSpanStartOffset(int token) {
		return getStartOffset(btParser.getToken(token));
	}

	protected int getSpanEndOffset(int token) {
		return getEndOffset(btParser.getToken(token));
	}

	protected void setSym1(Object p_obj) {
		btParser.setSym1(p_obj);
		if (p_obj != null) {
			if (lastASTElement != p_obj && p_obj instanceof SQLObject) {  // consider working with a HashSet as already recorded AST element could have been tracked before the previous symbol
				astElementList.add(p_obj);  // consider not putting Strings onto AST stack
				lastASTElement = p_obj;
			}
//			if(p_obj instanceof SQLQueryObject) {
//				SQLQueryObject stmt = (SQLQueryObject)p_obj;
//				if (stmt.getSourceInfo() == null) {
//				    stmt.setSourceInfo(new SQLQuerySourceInfo());
//				}
//				setSpan(stmt);
//
//				if (sourceFormat != null) {
//				    stmt.getSourceInfo().setSqlFormat(sourceFormat);
//				}
//			}
		}
	}

	protected void setInt1(int p_int) {
		// don't catch NumberFormatException here, will be wrapped by ParseException
	    btParser.setSym1(new Integer(p_int));
	}

	protected void setBoolean1(boolean p_boolean) {
		btParser.setSym1(new Boolean(p_boolean));
	}

	protected List getASTElementList() {
	    return astElementList;
	}

	/** */
	public int getToken(int p_tok) { return btParser.getToken(p_tok); }

	/*
	 * Protected token convenience accessors
	 */
	protected char[] data() {
		return getInputChars();
	}

	/**
	 * Takes production relative token position
	 * i.e. calls getToken(p_tok)
	 */
	protected Token getTokenObject(int p_tok) {
		return (Token)getTokens().get(getToken(p_tok));
	}

	/** Token text */
	protected String getTokenName(int p_tok) {
		Token token = getTokenObject(p_tok);
		return String.valueOf(data(),token.getStartOffset(), token.getEndOffset()-token.getStartOffset()+1);
	}


	
	
	protected List parser() throws SQLParserException, SQLParserInternalException
	{
		try
		{
		    //long timeBeforeParse = System.currentTimeMillis();
			
            List stmtList = (List) btParser.parse();
			
            //long timeConsumed = System.currentTimeMillis() - timeBeforeParse;
			//System.out.println("  -- btParser: time consumed: "+timeConsumed);
			
			// work in comments
		    if (hasComments())
		    {
		        List commentTokens = ((AbstractSQLLexer) getLexStream()).getCommentTokens();
		        if (commentTokens != null && !checkStmtOnly
                        && (sourceFormat == null || sourceFormat.isPreserveComments()))
		        {
		            addCommentsToAST(commentTokens);
		        }
		    }
		    return stmtList;

		}
		catch (BadParseException e)
		{
				reset(e.error_token); // point to error token
                
                if (errorDiagnosingNumberMax > ERROR_DIAGNOSING_NONE
                        && errorDiagnosingTimeMax > ERROR_DIAGNOSING_NONE)
                {
                    //diagnoseParseErrors(e);   // in separate timed thread
                    diagnose(e);                // limited by LPG
                }
                
				//TODO: wrap error message to present user
				throw new SQLParserException(e, this, prs);
		}
		catch (NumberFormatException e)
		{
		    //TODO: get Span of Sym (rule) that was constructed while
		    //      number caused Exception
			throw new SQLParserException(e.getMessage(),e);
		}

	}

    public void diagnose(BadParseException bpe) {
        DiagnoseParser diagnoseParser = null;
        if (errorDiagnosingNumberMax == ERROR_DIAGNOSING_UNLIMITED
                || errorDiagnosingTimeMax == ERROR_DIAGNOSING_UNLIMITED) {
            diagnoseParser = new DiagnoseParser(this, prs);
        } else {
            diagnoseParser = new DiagnoseParser(this, prs, errorDiagnosingNumberMax, errorDiagnosingTimeMax);
        }
        diagnoseParser.diagnose(bpe.error_token);
    }
    
    private class DiagnoseParsingThread extends Thread {
        private BadParseException badParseException = null;
        private AbstractSQLParser sqlParser = null;
        
        protected DiagnoseParsingThread(BadParseException e, AbstractSQLParser parser) {
            super();
            badParseException = e;
            sqlParser = parser;
        }
        
        public void run() {
            sqlParser.diagnose(badParseException);
        }
    }

	/**
     * Diagnoses the syntax errors that caused the
     * <code>BadParseException</code> in a separate <code>Thread</code> with
     * a specified time limit. The {@link DiagnoseParsingThread} will
     * <code>run</code> until one of the following conditions is fulfilled:
     * <ul>
     *  <li>the {@link DiagnoseParser} finished diagnosing the maximum number of
     *      parse errors {@link #ERROR_DIAGNOSING_MAX_NUMBER}
     *  <li>the {@link DiagnoseParser} found the minimum number of parse errors
     *      {@link #ERROR_DIAGNOSING_MIN_NUMBER} or more, and the time limit
     *      {@link #ERROR_DIAGNOSING_TIME} is up
     *  <li>the {@link DiagnoseParser} found the minimum number of parse errors
     *      {@link #ERROR_DIAGNOSING_MIN_NUMBER} before the extended time limit
     *      {@link #ERROR_DIAGNOSING_TIME_LIMIT} is up
     *  <li>the extended time limit {@link #ERROR_DIAGNOSING_TIME_LIMIT} is up,
     *      independent of how many parse errors were diagnosed
     * </ul>
     * 
     * @param e
     */
    private void diagnoseParseErrors(BadParseException e) {
        
        // TODO: use new LPG feature to limit time and errors
        
        final int waitTime = 50;
        final int waitTimeInc = waitTime * 2;
        
        synchronized (this) {
            // start a separate threat for the parse error diagnosing
            Thread diagnoseParseError = new DiagnoseParsingThread(e, this);
            diagnoseParseError.start();
            
            try {
                long currentTime = System.currentTimeMillis();
                long endTime = currentTime + errorDiagnosingTimeMax;
                long exceededEndTime = currentTime + ERROR_DIAGNOSING_TIME_LIMIT;
                
                while (diagnoseParseError.isAlive() && (endTime > currentTime)) {
                    // check if the diagnosing reached the maximum number of errors
                    if (errorInfoList != null && errorInfoList.size() >= errorDiagnosingNumberMax) {
                        // get out of the wait loop
                        break;
                    }
                    wait(waitTime);
                    currentTime = System.currentTimeMillis();
                    // extend time: if time's up, but we did not diagnose minimum number of errors
                    if ((currentTime > endTime) && (currentTime < exceededEndTime)
                            && (errorInfoList == null
                                    || errorInfoList.size() < ERROR_DIAGNOSING_MIN_NUMBER)) {
                        endTime += waitTimeInc;
                    }
                }
                
                if (diagnoseParseError.isAlive()) {
                    // TODO: work out with LPG to support a terminate diagnose method in DiagnoseParser, rather than stopping the thread
                    diagnoseParseError.stop();
                }
            }
            catch (InterruptedException e1) {
                System.err.println("\n\nDiagnoseParsingThread "+diagnoseParseError+" interupted!\n\n");
                e1.printStackTrace();
            }
        }
        
    }
    
    /**
     * @return <code>true</code> if the parsed input contained comments.
     */
    protected boolean hasComments()
    {
        if (!checkStmtOnly && getLexStream() instanceof AbstractSQLLexer) {
            List commentTokens = ((AbstractSQLLexer) getLexStream()).getCommentTokens();
            if (commentTokens != null && commentTokens.size() > 0) {
                return true;
            }
        }
        return false;
    }




    private final static String ERROR_LOCATION_STRING_SEPARATOR = ":"; //$NON-NLS-1$
	private final static String ERROR_TEXT_QUOTE = "\""; //$NON-NLS-1$
	
    public void reportError(int errorCode, String locationInfo, int leftToken, int rightToken, String tokenText)
    {
        if (errorCode == DELETION_CODE ||
            errorCode == MISPLACED_CODE) tokenText = ""; //$NON-NLS-1$
        
        if (errorInfoList == null)
        {
            errorInfoList = new ArrayList();
        }
        
        if (tokenText.startsWith(ERROR_TEXT_QUOTE) && tokenText.endsWith(ERROR_TEXT_QUOTE))
        {
            tokenText = tokenText.substring(1, tokenText.length()-1);
        }
        
        // locationInfo is of format "filename:start_line:start_46:1:48:", filename can be null or empty
        // e.g. "filename:1:45:3:67:", "null:1:45:3:67:", or ":1:45:3:67:"

        String[] errorPosition = locationInfo.split(ERROR_LOCATION_STRING_SEPARATOR);
        int errorLineBegin = 0;
        int errorColBegin = 0;
        int errorLineEnd = 0;
        int errorColEnd = 0;
        
        if (errorPosition != null && errorPosition.length > 4 )
        {
	        errorLineBegin 	= Integer.parseInt( errorPosition[1].trim() );
	        errorColBegin 	= Integer.parseInt( errorPosition[2].trim() );
	        errorLineEnd 	= Integer.parseInt( errorPosition[3].trim() );
	        errorColEnd 	= Integer.parseInt( errorPosition[4].trim() );
        }
        
        String input = getTokenText(leftToken);
        String expectedInput = tokenText;
        errorInfoList.add( new SQLParseErrorInfo(
                        errorLineBegin, errorColBegin, errorLineEnd, errorColEnd,
                        input, expectedInput,
                        errorMsgText[errorCode], "LPG-ERROR-CODE:"+errorCode) ); //$NON-NLS-1$
        
    }

    
    
    
    
    
    
    
    
    
    /* ************************************************************************
        - the following code is purely for performance optimization and it
        - might be found that it can be ommited entirely if one parser instance
        - is maintained for one parserManager and not one parser is created for
        - every parse method invokation on the parserManager
        - in that case remove: 
                > static field: parserClassToLexerClassToKindMapping
                > static methods: getSpecificKindMap, setSpecificKindMap
                > private field: kindMap
                > inherited method: remapTerminalSymbols, makeToken
                               
       performance optimization, in LPG PrsStream remap terminal symbols
       only once per "class load" for Lexer class associated, not for every parse!
       don't use a static variable (inheritance runtime problems), make a parser
       class (action class) specific method that returnes the mapping for a
       specific parser/action class to a specific Lexer class, if a certain
       Lexer class was not yet used with the specific parser class, create the
       remapping
       @see #remapTerminalSymbols(String[], int)
     ************************************************************************ */

    private static WeakHashMap parserClassToLexerClassToKindMapping = new WeakHashMap();
    
    protected final static int[] getSpecificKindMap(Class parserClass, Class lexerClass)
    {
        Map lexerTerminalSymbolRemapping =
            (Map) parserClassToLexerClassToKindMapping.get(parserClass);
        
        if (lexerTerminalSymbolRemapping != null)
        {
            return (int[]) lexerTerminalSymbolRemapping.get(lexerClass);
        }
        return null;
    }

    protected final static void setSpecificKindMap(Class parserClass, Class lexerClass, int[] kindRemapping)
    {
        Map lexerTerminalSymbolRemapping =
            (Map) parserClassToLexerClassToKindMapping.get(parserClass);
        
        if (lexerTerminalSymbolRemapping == null)
        {
            lexerTerminalSymbolRemapping = new WeakHashMap();
            parserClassToLexerClassToKindMapping.put(parserClass, lexerTerminalSymbolRemapping);
        }
        lexerTerminalSymbolRemapping.put(lexerClass, kindRemapping);
    }
    
    private int[] kindMap = null;

    /** @see lpg.lpgjavaruntime.PrsStream#remapTerminalSymbols(java.lang.String[], int)*/
    public void remapTerminalSymbols(String[] ordered_parser_symbols, int eof_symbol)
                    throws UndefinedEofSymbolException,
                    NullExportedSymbolsException, NullTerminalSymbolsException,
                    UnimplementedTerminalsException
    {
        // TODO Gerry: reduce method visibility to protected in PrsStream
        kindMap = getSpecificKindMap(this.getClass(), getLexStream().getClass());
        
        if (kindMap == null)
        {
            //long timeBefore = System.currentTimeMillis();
            
            String[] ordered_lexer_symbols = getLexStream().orderedExportedSymbols();
            if (ordered_lexer_symbols == null) throw new NullExportedSymbolsException();
            if (ordered_parser_symbols == null) throw new NullTerminalSymbolsException();
            ArrayList unimplemented_symbols = new ArrayList();
            if (ordered_lexer_symbols != ordered_parser_symbols)
            {
                kindMap = new int[ordered_lexer_symbols.length];

                HashMap terminal_map = new HashMap();
                for (int i = 0; i < ordered_lexer_symbols.length; i++) {
                    terminal_map.put(ordered_lexer_symbols[i], new Integer(i));
                }
                for (int i = 0; i < ordered_parser_symbols.length; i++)
                {
                    Integer k = (Integer) terminal_map
                                    .get(ordered_parser_symbols[i]);
                    if (k != null) {
                        kindMap[k.intValue()] = i;
                    }
                    else
                    {
                        if (i == eof_symbol) {
                            throw new UndefinedEofSymbolException();
                        }
                        unimplemented_symbols.add(new Integer(i));
                    }
                }
            }

            if (unimplemented_symbols.size() > 0) {
                throw new UnimplementedTerminalsException(
                                unimplemented_symbols);
            }
            
            setSpecificKindMap(this.getClass(), getLexStream().getClass(), kindMap);
        }
    }
    
    public void makeToken(int startLoc, int endLoc, int kind)
    {
        Token t = new Token(this, startLoc, endLoc, (kindMap == null ? kind : kindMap[kind]));
        getTokens().add(t);
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

    
    
    /**
     * Resets the state of this <code>AbstractSQLParser</code>.
     */
    public void resetParser(LexStream lexStream) {
        resetTokenStream();
        this.astElementList = new ArrayList();
        this.lastASTElement = null;
        this.errorInfoList = new ArrayList();
        resetLexStream(lexStream);
     }

    /**
     * @return Returns the checkStmtOnly.
     */
    public boolean isCheckStmtOnly() {
        return checkStmtOnly;
    }
    /**
     * @param checkStmtOnly The checkStmtOnly to set.
     */
    public void setCheckStmtOnly(boolean checkStmtOnly) {
        this.checkStmtOnly = checkStmtOnly;
    }
}
