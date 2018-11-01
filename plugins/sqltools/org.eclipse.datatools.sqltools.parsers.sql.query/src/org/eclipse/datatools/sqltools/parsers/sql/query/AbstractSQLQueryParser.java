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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import lpg.lpgjavaruntime.IToken;
import lpg.lpgjavaruntime.LexStream;
import lpg.lpgjavaruntime.ParseTable;

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.util.SQLComment;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceInfo;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParser;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserLogger;
import org.eclipse.datatools.sqltools.parsers.sql.lexer.SQLParsersym;

/**
 * @author ckadner
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class AbstractSQLQueryParser extends SQLParser
{

    private boolean debug; // developer debug flag for this class

    /**
     * @param lexStream
     * @param prs
     * @param EOFTsymbol
     * @param checkStmtOnly
     */
    public AbstractSQLQueryParser(LexStream lexStream, ParseTable prs, int EOFTsymbol,
                     boolean checkStmtOnly) throws SQLParserInternalException
    {
        super(lexStream, prs, EOFTsymbol, checkStmtOnly);
    }
    /**
     * @param lexStream
     * @param prs
     * @param EOFTsymbol
     * @param sourceFormat
     */
    public AbstractSQLQueryParser(LexStream lexStream, ParseTable prs, int EOFTsymbol,
                     SQLQuerySourceFormat sourceFormat)throws SQLParserInternalException
    {
        super(lexStream, prs, EOFTsymbol, sourceFormat);
    }
    /**
     * @param lexStream
     * @param prs
     * @param EOFTsymbol
     * @param p_sourceFormat
     * @param checkStmtOnly
     */
    public AbstractSQLQueryParser(LexStream lexStream,  ParseTable prs, int EOFTsymbol,
    		SQLQuerySourceFormat p_sourceFormat, boolean checkStmtOnly) throws SQLParserInternalException
    {
        super(lexStream, prs, EOFTsymbol, p_sourceFormat, checkStmtOnly);
    }
    
    
    
    /*
-- first line comnt    
select col1 as c1 -- com1
  from tab1 as -- tbl1
              t1 -- better name it t1
 where col1 = 5
-- and col2 = 5
   and col2 = 10 -- muss 10 sein
;


select col1 as c1 -- com1
-- comment anfang 2
  from tab1 t1;
      
      
select col1 as c1 -- com_ln1
                  -- com_ln2
 from tab1 as t1 -- com_ln3
 where col1 = 5
-- ln begin
  and col2 = 10
-- last line comnt    
;
     */
    
    
	protected void addCommentsToAST(List commentTokens)
	{
        
	    Map stmtToSortedASTNodesMap = getSortedASTNodesForStmtMap();

	    List comments = createCommentObjectsSorted(commentTokens);
	    
	    List stmts = getStmtsInASTOrdered();
	    
	    for (Iterator stmtIt = stmts.iterator(); stmtIt.hasNext();)
        {
            QueryStatement stmt = (QueryStatement) stmtIt.next();
            SortedSet astNodes = (SortedSet) stmtToSortedASTNodesMap.get(stmt);
            addCommentsToStatement(stmt, astNodes, comments);
            
    	    // append the rest of the comments to the last stmt in the list
            if (!stmtIt.hasNext())
            {
        	    for (Iterator commentIt = comments.iterator(); commentIt.hasNext();)
                {
                    SQLComment comment = (SQLComment) commentIt.next();
                    comment.setRelativePosition(SQLComment.COMMENT_POSITION_NEXT_LINE);
                }
                if (stmt.getSourceInfo().getComments() == null)
                {
                    stmt.getSourceInfo().setComments(new ArrayList());
                }
                stmt.getSourceInfo().getComments().addAll(comments);
                comments.clear();
            }
        }
	}
	
	/**
	 * @param stmt
	 * @param astNodes Sorted set of the given stmt's AST nodes (<code>SQLObject</code>s)
	 * @param allStmtsComments will be modified, the comments associated to the given stmt will be removed from list
	 */
	private void addCommentsToStatement( QueryStatement stmt,
	                                     SortedSet astNodes,
	                                     List allStmtsComments)
	{
	    Object[] astArray = astNodes.toArray();
	    
        logASTNodes(astArray);

	    // attach comment to the most inclusive element that starts and ends at the line of the comment
	    // ... done by sorting
	    // if comment is on a new line it might belong to the next element (but check for stmt scope)
	    // consider multiple succeeding comment lines
	    
	    SQLQueryObject lastAST = null;
	    int currentStmtStart = stmt.getSourceInfo().getSpanStartOffset();
	    int currentStmtEnd = stmt.getSourceInfo().getSpanEndOffset();
	    int currentStmtLastLine = stmt.getSourceInfo().getLineNumberEnd();
	    int astIndex = 0;
	    int lastLineEndCommentIndent = 0;
	    
	    for (Iterator commentIt = allStmtsComments.iterator(); commentIt.hasNext();)
        {
            SQLComment comment = (SQLComment) commentIt.next();
            
            int commentStartIndex = comment.getSourceInfo().getSpanStartOffset();
            int commentStartLine = comment.getSourceInfo().getLineNumberStart();
            
            
            // we include only comments inside the Span of the Stmt or on the
            // last line of the Stmt even after the Stmt terminator
            if (commentStartLine > currentStmtLastLine)
            {
                break;
            }
            
            // go through ast list to get closest to startSpan of comment
            // once over go back one that's the most inclusive on same line
            
            for (; astIndex < astArray.length; astIndex++)
            {
                SQLObject astNode = (SQLObject) astArray[astIndex];
                
                if (astNode instanceof SQLQueryObject)
                {
                    SQLQueryObject sqo = (SQLQueryObject) astNode;
                    SQLQuerySourceInfo si = sqo.getSourceInfo();
                    
                    int astEnd = si.getSpanEndOffset();
                    int astStart = si.getSpanStartOffset();

//                    printASTNode(astIndex, sqo);
                    
                    // the current AST is too far, take previous, previous is null attach it for statement
                    // or if we have the last AST node, we must attach the comment
                    if (astEnd > commentStartIndex || astIndex == astArray.length-1)
                    {
                        SQLQueryObject sqoToAppendComment = null;
                        
                        if (astStart < commentStartIndex && ((lastAST != null && astStart > lastAST.getSourceInfo().getSpanEndOffset()) || lastAST == null)) // comment contained w/in span of AST node but the AST node does not contain the last AST node also
                        {
                            sqoToAppendComment = sqo;
                        }
                        else if (lastAST != null && commentStartIndex < currentStmtEnd) // comment before the span of current AST node, get the previous AST or leave comment 
                        {
                            sqoToAppendComment = lastAST;
                        }
                        else if (astStart < commentStartIndex) // comment contained w/in span of AST node
                        {
                            sqoToAppendComment = sqo;
                        }
                        else if (lastAST == null && commentStartIndex < currentStmtEnd) // comment before the stmt's first AST node
                        {
                            if (commentStartIndex > currentStmtStart) // comment before the first AST node but after the first keyword in the stmt, attach comment to first AST node as preceeding comment
                            {
                                sqoToAppendComment = sqo;
                            }
                            else // else attach comment to stmt
                            {
                                sqoToAppendComment = stmt;
                            }
                        }
                        else if (astIndex == astArray.length-1)  // comment on last line probably after the stmt term attached to last AST node
                        {
                            sqoToAppendComment = sqo;
                            
                            // we don't extend the stmt's and all AST nodes' span for that
                        }
                        
                        if (sqoToAppendComment != null)
                        {
                            
                            if (sqoToAppendComment.getSourceInfo().getComments() == null)
                            {
                                sqoToAppendComment.getSourceInfo().setComments(new ArrayList());
                            }
                        	sqoToAppendComment.getSourceInfo().getComments().add(comment);
                            
                        	
                            if (sqoToAppendComment.getSourceInfo().getLineNumberEnd()
                                            < comment.getSourceInfo().getLineNumberStart())
                            {
                                // if the comment is on new line but was indented as previous end-of-line comment, we regard it as as end-of-line comment as well
                                if (!(lastLineEndCommentIndent > 0
                                                && comment.getSourceInfo().getColumnNumberStart() == lastLineEndCommentIndent))
                                {
                                    comment.setRelativePosition(SQLComment.COMMENT_POSITION_NEXT_LINE);
                                }
                            }
                            else if (sqoToAppendComment.getSourceInfo().getLineNumberStart()
                                            > comment.getSourceInfo().getLineNumberEnd())
                            {
                                // should only be the case for statements
                                comment.setRelativePosition(SQLComment.COMMENT_POSITION_PREV_LINE);
                            }
                            
                            commentIt.remove();
                            if (astIndex > 0)
                            {
                                astIndex--; //check for more comments on same AST, role back index if we are not at index 0 already 
                            }
                            break;
                        }
                        else
                        {
                            // save as previous and go on
                            lastAST = sqo;
                        }
                        // break AST loop as we want to get the next comment
                        break;
                    }
                   
                    // save previous
                    lastAST = sqo;
                }
            }
            
            lastLineEndCommentIndent = 0;
            if (comment.getRelativePosition()
                            == SQLComment.COMMENT_POSITION_LINE_END)
            {
                lastLineEndCommentIndent = comment.getSourceInfo().getColumnNumberStart();
            }
        }
	    
	    
	    // extend the span of the stmt for firstline comments
	    if (stmt.getSourceInfo().getComments() != null
	                    && !stmt.getSourceInfo().getComments().isEmpty())
	    {
	        SQLComment firstComment =
	            (SQLComment) stmt.getSourceInfo().getComments().get(0);
	        
	        if (firstComment.getSourceInfo().getSpanStartOffset() <
	                        stmt.getSourceInfo().getSpanStartOffset())
	        {
	            //firstComment.setRelativePosition(SQLComment.COMMENT_POSITION_PREV_LINE);
                // extend the span including comment
                SQLQuerySourceInfo stmtSI = stmt.getSourceInfo();
                SQLQuerySourceInfo cmntSI = firstComment.getSourceInfo();
                
                int beg = cmntSI.getSpanStartOffset();
                int end = stmtSI.getSpanEndOffset();   
                String source = getSpan(beg, end);
                int lineBeg = cmntSI.getLineNumberStart();
                int colBeg = cmntSI.getColumnNumberStart();
                
                stmtSI.setSpanStartOffset(beg);
                stmtSI.setColumnNumberStart(colBeg);
                stmtSI.setLineNumberStart(lineBeg);
                stmtSI.setSourceSnippet(source);
	        }
	    }
	}
	
    /**
     * @param astArray
     */
    private void logASTNodes(Object[] astArray) {
        for (int astIndex = 0; astIndex < astArray.length; astIndex++)
        {
            SQLObject astNode = (SQLObject) astArray[astIndex];
            
            if (astNode instanceof SQLQueryObject)
            {
                SQLQueryObject sqo = (SQLQueryObject) astNode;
                logASTNode(astIndex, sqo);
            }
        }
    }
    
    /**
     * This method is generated by LPG compile and invoked by LPG runtime.
     * @param rule generated number associated to grammar rules
     */
    public abstract void ruleAction(int rule);

	
    /**
     * @param astIndex
     * @param sqo
     */
    private void logASTNode(int astIndex, SQLQueryObject sqo)
    {
        if (debug) {
            SQLQuerySourceInfo si = sqo.getSourceInfo();
            
            int astEnd = si.getSpanEndOffset();
            int astLine = si.getLineNumberStart();
            int astStart = si.getSpanStartOffset();
            
            SQLParserLogger.getLogger().writeInfo(astIndex+" " +                //$NON-NLS-1$
                            "end: " + astEnd + "\t" +                           //$NON-NLS-1$ //$NON-NLS-2$
                            "line: " + astLine + "\t" +                         //$NON-NLS-1$ //$NON-NLS-2$
                            "start: " + astStart + "\t" +                       //$NON-NLS-1$ //$NON-NLS-2$
                            si.getSourceSnippet().replace('\n', '/'));
        }
    }
    
    /**
     * @return all <code>QueryStatement</code>s in parsed AST list in parse order
     */
    private List getStmtsInASTOrdered()
    {
        List stmtList = new ArrayList();
	    for (Iterator astIt = getASTElementList().iterator(); astIt.hasNext();)
        {
            SQLObject astNode = (SQLObject) astIt.next();
            
            if (astNode instanceof QueryStatement)
            {
               stmtList.add(astNode); 
            }
        }
	    return stmtList;
    }

    /**
     * Returns a Map of <code>QueryStatement</code>s mapped to their
     * AST nodes (<code>SQLQueryObject</code>s).
     * AST nodes are sorted ccording to the end of their span.
     * Key: QueryStatement, value: Collection of SQLObjects (AST nodes).
     * @return
     */
    private Map getSortedASTNodesForStmtMap()
    {
        HashMap stmtToASTNodesMap = new HashMap();
        
	    Comparator astSpanComparator = new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                int compare = 0;
                if (o1 instanceof SQLQueryObject && o2 instanceof SQLQueryObject)
                {
                    SQLQueryObject sqo1 = (SQLQueryObject) o1;
                    SQLQueryObject sqo2 = (SQLQueryObject) o2;
                    
                    int endO1 = sqo1.getSourceInfo().getSpanEndOffset();
                    int endO2 = sqo2.getSourceInfo().getSpanEndOffset();
                                    
                    compare = endO1 - endO2;
                    
                    if (compare == 0)
                    {
                        int lineO1 = sqo1.getSourceInfo().getLineNumberStart();
                        int lineO2 = sqo2.getSourceInfo().getLineNumberStart();
                        
                        compare = lineO1 - lineO2; // prefer the same line or closer line
                        
                        if (compare == 0)
                        {
                            int startO1 = sqo1.getSourceInfo().getSpanStartOffset();
                            int startO2 = sqo2.getSourceInfo().getSpanStartOffset();
                                
                            compare = startO2 - startO1; // prefer the longer span
                        }
                    }
                }
                
//                // don't throw away SELECT stmt because it takes the same span as QuerySelecte
//                if (compare == 0)
//                {
//                    if (o1 instanceof QueryStatement)
//                    {
//                        compare = -1;
//                    }
//                    else if (o2 instanceof QueryStatement)
//                    {
//                        compare = 1;
//                    }
//                }
                return compare;
            }
        };
	    
	    TreeSet astSpanSet = new TreeSet(astSpanComparator); // maybe only sort the list

	    for (Iterator astIt = getASTElementList().iterator(); astIt.hasNext();)
        {
            SQLObject astNode = (SQLObject) astIt.next();
            
            if (astNode instanceof QueryStatement)
            {
                // Stmt node should always be the last of all stmt's AST nodes
                stmtToASTNodesMap.put(astNode, astSpanSet);
                // not astSpanSet.clear(); .. that would affect the set we just stored away
                astSpanSet = new TreeSet(astSpanComparator);
            }
            else
            {
                astSpanSet.add(astNode);
            }
        }
        return stmtToASTNodesMap;
    }
 
    
    List createCommentObjectsSorted(List commentTokens) {
	    List resultCommentList = new ArrayList();
	    boolean toBeSorted = false;
	    int lastCommentStartIndex = 0;
	    
	    if (commentTokens != null)
	    {
	        for (Iterator iter = commentTokens.iterator(); iter.hasNext();)
            {
                IToken cmntTok = (IToken) iter.next();
                int tokenKind = cmntTok.getKind();
                SQLComment commentObj = createCommentObject(cmntTok);
                resultCommentList.add(commentObj);
                
                toBeSorted |= cmntTok.getStartOffset() < lastCommentStartIndex;
                lastCommentStartIndex = cmntTok.getStartOffset();
            }
	    }
	    
	    if (toBeSorted)
	    {
	        Comparator commentComparator = new Comparator()
            {
                public int compare(Object o1, Object o2)
                {
                    SQLComment c1 = (SQLComment) o1;
                    SQLComment c2 = (SQLComment) o2;
                    
                    int c1start = c1.getSourceInfo().getSpanStartOffset();
                    int c2start = c2.getSourceInfo().getSpanStartOffset();
                    
                    return c1start - c2start;
                }
            };
            Collections.sort(resultCommentList, commentComparator);
	    }
	    
	    return resultCommentList;
	}
	
	/**
     * @param cmntTok
     * @return
     */
    private SQLComment createCommentObject(IToken cmntTok)
    {
        SQLComment comment = new SQLComment();
        SQLQuerySourceInfo sourceInfo = new SQLQuerySourceInfo();
        
        int startOffset = cmntTok.getStartOffset();
        int endOffset = cmntTok.getEndOffset();
        int startColumn = getLexStream().getColumnOfCharAt(startOffset);
        int endColumn = getLexStream().getColumnOfCharAt(endOffset);
        int startLine = getLexStream().getLineNumberOfCharAt(startOffset);
        int endLine = getLexStream().getLineNumberOfCharAt(endOffset);
        
        sourceInfo.setSpanStartOffset(startOffset);
        sourceInfo.setSpanEndOffset(endOffset);
        sourceInfo.setColumnNumberStart(startColumn);
        sourceInfo.setColumnNumberEnd(endColumn);
        sourceInfo.setLineNumberStart(startLine);
        sourceInfo.setLineNumberEnd(endLine);
        
        sourceInfo.setSourceSnippet(cmntTok.getValue(getInputChars()));
        
        comment.setSourceInfo(sourceInfo);
        
        comment.setText(sourceInfo.getSourceSnippet());
        
        int tokenKind = cmntTok.getKind();
        if (tokenKind == SQLParsersym.TK_MULTILINE_COMMENT) {
            comment.setMultiLineComment(true);
        }
        
        return comment;
    }
    
    
    
    protected void setSym1(Object p_obj) {
		btParser.setSym1(p_obj);
		if (p_obj != null) {
			if (lastASTElement != p_obj && p_obj instanceof SQLObject) {  // consider working with a HashSet as already recorded AST element could have been tracked before the previous symbol
				astElementList.add(p_obj);  // consider not putting Strings onto AST stack
				lastASTElement = p_obj;
			}
			if(p_obj instanceof SQLQueryObject) {
				SQLQueryObject sqo = (SQLQueryObject)p_obj;
				sqo.setSourceInfo(new SQLQuerySourceInfo(sqo));
				setSpan(sqo);

				if (sourceFormat != null) {
				    sqo.getSourceInfo().setSqlFormat(sourceFormat);
				}
			}
		}
	}


	
	protected void setSym1_keepSpan(Object p_obj) {
		btParser.setSym1(p_obj);
		if (p_obj != null) {
			if (lastASTElement != p_obj && p_obj instanceof SQLObject) {
				astElementList.add(p_obj);
				lastASTElement = p_obj;
			}
		}
	}

	/**
     * Expand the span of the given <code>SQLQueryObject</code> to the end of
     * the span of the given symbol number.
     * 
     * for example in a rule: <A>::= <B>c <D>span of symbol <B>should
     * include the span of (terminal) symbol c, symbol c has the number 2 and
     * the method call would be
     * <code>extendSpan( (SQLQueryObject) getSym(1), 2)</code> where the
     * <code>SQLQueryObject</code> object already reduced for <B>, is
     * retrieved by calling <code>getSym(1)</code>,
	 * 
	 * @param sqo the <code>SQLQueryObject</code> we want to expand the span for
     * @param toSym
     *            the number of the symbol within the right-hand-side of the
     *            current rule
     */
	protected void extendSpan( SQLQueryObject sqo, int toSym) {
	    int tokenIndex = btParser.getLastToken(toSym);
	    
	    int startOffset = sqo.getSourceInfo().getSpanStartOffset();
	    int endOffset = getSpanEndOffset(toSym);
	    String extendedSpan = getSpan(startOffset, endOffset);
	    sqo.getSourceInfo().setSourceSnippet(extendedSpan);
	    sqo.getSourceInfo().setSpanEndOffset(endOffset);
	    sqo.getSourceInfo().setColumnNumberEnd( getEndColumn(tokenIndex) );
	    sqo.getSourceInfo().setLineNumberEnd( getEndLine(tokenIndex) );
	}

	protected void extendSpanToFollowingToken( SQLQueryObject sqo, int tokenKind) {
	    IToken followingToken = getFollowingToken();
	    
        if (followingToken.getKind() == tokenKind) {
            int tokenIndex = btParser.getLastToken() + 1;

            int startOffset = sqo.getSourceInfo().getSpanStartOffset();
    	    int endOffset = followingToken.getStartOffset()-1;
    	    String extendedSpan = getSpan(startOffset, endOffset);
    	    sqo.getSourceInfo().setSourceSnippet(extendedSpan);
    	    sqo.getSourceInfo().setSpanEndOffset(endOffset);
    	    sqo.getSourceInfo().setColumnNumberEnd( getEndColumn(tokenIndex) );
    	    sqo.getSourceInfo().setLineNumberEnd( getEndLine(tokenIndex) );
	    }
	}
	
}
