/*******************************************************************************
 * Copyright © 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLPartitionScanner;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

/**
 * This class provides helpful methods for working with one or more SQL statements
 * contained within a SQL script document.
 */
public class SQLStatementSupport implements IPropertyChangeListener {
    /** A property name the the statement terminator property for use in 
     * property change notification. */
    public static final String CURRENT_STATEMENT_TERMINATOR = "currentStatementTerminator"; //$NON-NLS-1$  
    /** The default statement terminator string. */
    public static final String DEFAULT_STATEMENT_TERMINATOR = ";"; //$NON-NLS-1$
    /** Defines the leading part of a tag to use to imbed the statement terminator
     *  string in the document. */
    private static final String STMT_TERM_TAG_1 = "-- <ScriptOptions statementTerminator=\""; //$NON-NLS-1$
    /** Defines the trailing part of a tag to use to imbed the statement terminator
     *  string in the document. */
    private static final String STMT_TERM_TAG_2 = "\" />\r"; //$NON-NLS-1$
    /** 
     * Defines a regular expression pattern so that we can locate the statement 
     * terminator string in an XML-like tag within a document.
     * This pattern can be interpreted as follows: 
     *   from the beginning of the input, 
     *   followed by zero or more spaces, 
     *   followed by "--", 
     *   followed by zero or more spaces, 
     *   followed by the string "<ScriptOptions", 
     *   followed by one or more spaces, 
     *   followed by the string "statementTerminator", 
     *   followed by zero or more spaces, 
     *   followed by "=",
     *   followed by zero or more spaces, 
     *   followed by a string of one or more characters surrounded by double-quote
     *     characters (which is the statement terminator), 
     *   followed by any number of characters, 
     *   followed by zero or more "whitespace" characters (including \r and \n).
     * The pattern is not case-sensitive.
     * The statement terminator string pattern itself is in parens in the pattern so that
     * it will be a "group" that we can extract from the matcher.
     */
    private static final Pattern STMT_TERM_TAG_PATTERN = Pattern.compile( "\\A[ ]*--[ ]*<ScriptOptions[ ]+statementTerminator[ ]*=[ ]*\"(.+)\".*[\\s]*", Pattern.CASE_INSENSITIVE ); //$NON-NLS-1$
    
    /** The SQL script document for which services are provided. */
    private IDocument fDoc;
    
    /**
     * This inner class contains information for locating and updating a statement
     * terminator tag within a document.
     */
    private class StatementTerminatorTagInfo {
        IDocument doc;
        ITypedRegion tagRegion;
        Matcher tagMatcher;
    } // end inner class
    
    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public SQLStatementSupport() {
        this( null );
    }

    /**
     * Constructs an instance of this class with the given document.
     * 
     * @param doc a <code>IDocument</code> for which statement support is needed
     */
    public SQLStatementSupport( IDocument doc ) {
        super();
        fDoc = doc;
    }
    
    /**
     * Gets the default statement terminator string for the current document.
     *  
     * @return the default statement terminator
     */
    public String getDefaultStatementTerminator() {
        return DEFAULT_STATEMENT_TERMINATOR;
    }

    /**
     * Gets the current document for which statement services are being provided.
     * 
     * @return the current document or null if there is no current document 
     */
    protected IDocument getDocument() {
        return fDoc;
    }
    
    // [b157408] bgp 14Sep2006 - new method
    /**
     * Gets the next position of the given terminator string in the given source string,
     * starting the scan at the given position in the source string.  If the terminator
     * string contains only basic alphanumeric characters (example: "GO") then the terminator 
     * must be surrounded by "whitespace" in order to be considered a terminator string.
     * (This prevents a hit on GO in CATEGORY, for example.)
     * 
     * @param source the string to search
     * @param target the string to search for
     * @param start the starting offset in the source string
     * @return the next position of the target in the source, or -1 if not found
     */
    protected int getNextTerminatorPosition(String source, String target, int start ) {
        int terminatorPos = -1;
        
        if (source != null && target != null) {
            /* Find out whether the target is composed of only basic alphanumeric
             * ("word") characters, as you might have in a language keyword or identifier. */
            boolean isAlphaOnly = false;
            if (target.matches("[\\w]+")) {
                isAlphaOnly = true;
            }

            /* Search for the target within the source. */
            int candidatePos = source.indexOf( target, start );
            /* If the target contains characters other than letters, such that it 
             * can't be part of a keyword or identifier, then we've found it,
             * and we're done. */
            if (isAlphaOnly == false) {
                terminatorPos = candidatePos;
            }
            /* Otherwise we need to make sure that there is some kind of "whitespace"
             * around the target, so that we can make sure we haven't found part
             * of a keyword or identifier. */
            else {
                while (terminatorPos == -1 && candidatePos != -1) {
                    boolean leadOK = true;
                    boolean followOK = true;
                    /* Check if the character preceding the found target is a "word" character.
                     * If so, the target isn't what we're looking for. */
                    if (candidatePos > 0) {
                        String lead = source.substring(candidatePos - 1, candidatePos);
                        if (lead.matches("[\\w]")) {
                            leadOK = false;
                        }
                    }
                    if (leadOK == true) {
                        /* Check the character following the found target as well. */
                        int followPos = candidatePos + target.length();
                        if (followPos < source.length()) {
                            String follow = source.substring(followPos, followPos + 1);
                            if (follow.matches("[\\w]")) {
                                followOK = false;
                            }
                        }
                    }
                    /* If the context of the found target is OK, then we're done. */
                    if (leadOK == true && followOK == true) {
                        terminatorPos = candidatePos;
                    }
                    /* Otherwise scan again, starting after the previous hit.*/
                    else {
                        start = candidatePos + target.length();
                        candidatePos = source.indexOf( target, start );
                    }
                }
            }
        }
        
        return terminatorPos;
    }
    
    /**
     * Gets the SQL statements contained in the given document, as a list of
     * strings.  
     * 
     * @return the list of SQL statements
     */
    public List getSQLStatementList( ) {
        List stmtList = new ArrayList();
 
        // Get the current statement terminator.  The statement terminator to use
        // might be imbedded in the document itself as an XML-like tag.
        String stmtTerminator = getStatementTerminator();

        // Get the current document.
        IDocument doc = getDocument();
        
        // Get an array of document regions.
        ITypedRegion[] regions = SQLPartitionScanner.getDocumentRegions( doc );

        // Process the regions.  Build up a statement string until we find a
        // statement terminator, whereupon we add that statement string to the
        // list of statements and start another statement string.
        if (regions != null) {
            StringBuffer stmtBuf = new StringBuffer();
            ITypedRegion region = null;
            
            // Process each region in turn.
            for (int i=0; i<regions.length; i++) {
                // Get the next region.
                region = regions[ i ];
                
                // Ignore it if it's a comment regions, otherwise process it.
                if (!(region.getType().equals( SQLPartitionScanner.SQL_COMMENT ))) {
                    // Get the text of the region from the document.
                    int regionOffset = region.getOffset();
                    int regionLength = region.getLength();
                    String stmtPart = "";
                    try {
                       stmtPart = doc.get( regionOffset, regionLength );
                    }
                    catch (BadLocationException e ) {
                        // ignore, shouldn't happen
                    }
                    
                    // For quoted literals and delimited identifiers, simply add 
                    // them to the current statement string.
                    if ( (region.getType().equals( SQLPartitionScanner.SQL_QUOTED_LITERAL ))
                      || (region.getType().equals( SQLPartitionScanner.SQL_DELIMITED_IDENTIFIER ))
                       ) {
                        stmtBuf.append( stmtPart );
                    }
                    // Otherwise it must be a SQL code region.  Scan it for statement
                    // terminators and break it up accordingly.
                    else {
                        // Look for a terminator.
                        int scanStart = 0;
                        int terminatorPos = getNextTerminatorPosition( stmtPart, stmtTerminator, scanStart ); // [b157408] bgp 14Sep2006
                        while (terminatorPos != -1 && scanStart < stmtPart.length()) {
                            // Add this part of the statement to the buffer, add the statement
                            // to the statement list, clear the buffer, and set up to scan again.
                            stmtBuf.append( stmtPart.substring( scanStart, terminatorPos ));
                            String stmt = stmtBuf.toString();
                            stmt = stmt.trim();
                            if (stmt.length() > 0) {
                                stmtList.add( stmt );
                            }
                            stmtBuf.setLength( 0 );
                            
                            // Look for another terminator.
                            scanStart = terminatorPos + stmtTerminator.length();
                            if (scanStart < stmtPart.length()) {
                                terminatorPos = getNextTerminatorPosition( stmtPart, stmtTerminator, scanStart );  // [b157408] bgp 14Sep2006
                            }
                        }
                        // Append any leftover text to the statement buffer.
                        if (scanStart < stmtPart.length()) {
                            stmtBuf.append( stmtPart.substring( scanStart, stmtPart.length() ));
                        }
                    }
                }
            }
            
            // Make sure the last statement buffer is added to the list.
            if (stmtBuf.length() > 0) {
                String stmt = stmtBuf.toString();
                stmt = stmt.trim();
                if (stmt.length() > 0) {
                    stmtList.add( stmt );
                }
            }
        }
        
        return stmtList;
    }

    /**
     * Gets the current statement terminator string for the current document.
     *  
     * @return the current statement terminator
     */
    public String getStatementTerminator() {
        /* Get the default statement terminator. */
        String statementTerminator = getDefaultStatementTerminator();
        
        /* Determine if there is any statement terminator tag in the document.  If
         * there is, get the statement terminator string from that. */
        StatementTerminatorTagInfo stmtTermTagInfo = getStatementTerminatorTagInfo();
        if (stmtTermTagInfo != null) {
            int groupCount = stmtTermTagInfo.tagMatcher.groupCount();
            if (groupCount == 1) {
                statementTerminator = stmtTermTagInfo.tagMatcher.group( 1 );
            }
        }
        
        return statementTerminator;
    }
    
    /**
     * Gets statement terminator information for the current document.
     * 
     * @return the statement terminator string imbedded in the document, or 
     * <code>null</code> if none
     */
    protected StatementTerminatorTagInfo getStatementTerminatorTagInfo() {
       StatementTerminatorTagInfo stmtTermTagInfo = null;
       
       /* Get the current document. */
       IDocument doc = getDocument();
       
       /* Get an array of the document's regions. */
       ITypedRegion[] regions = SQLPartitionScanner.getDocumentRegions( doc );

       /* Process each region in turn, looking for the special XML-like tag that contains 
        * a statement terminator. */
       ITypedRegion region = null;
       int regionCount = regions.length;
       for (int i=0; i < regionCount && stmtTermTagInfo == null; i++) {
           /* Get the next region. */
           region = regions[ i ];
           
           /* We're only interested in comment regions. */
           if (region.getType().equals( SQLPartitionScanner.SQL_COMMENT )) {
               /* Get the string represented by the region. */
               int regionOffset = region.getOffset();
               int regionLength = region.getLength();
               String regionStr = ""; // $NON-NLS-1$
               try {
                  regionStr = doc.get( regionOffset, regionLength );
               }
               catch (BadLocationException e ) {
                   // ignore, shouldn't happen 
               }
               
               /* Match the region string against the pattern. */
               Matcher stmtTermTagMatcher = STMT_TERM_TAG_PATTERN.matcher( regionStr );
               boolean matches = stmtTermTagMatcher.matches(); 
               
               /* If we have a match, get the "group" from the matched pattern
                * that should be the statement terminator string. */
               if (matches == true) {
                   stmtTermTagInfo = new StatementTerminatorTagInfo();
                   stmtTermTagInfo.doc = doc;
                   stmtTermTagInfo.tagRegion = region;
                   stmtTermTagInfo.tagMatcher = stmtTermTagMatcher;
               }
           }
       }

       return stmtTermTagInfo;
   }
        
    /**
     * Handles notifications to the object that a property has changed.
     * 
     * @param event the property change event object describing which property
     *            changed and how
     */
    public void propertyChange( PropertyChangeEvent event ) {
        /* Handle a change for the current statement terminator property.
         * When the statement terminator changes, the current document will be
         * updated to remember the statement terminator (if it's not the default). */
        if (event.getProperty().equals( CURRENT_STATEMENT_TERMINATOR )) {
            String statementTerminator = (String) event.getNewValue();
            setStatementTerminator( statementTerminator );
        }
    }

    /**
     * Sets the document associated with this object to the given SQL script document.
     * 
     * @param doc the <code>IDocument</code> for which support is needed
     */
    public void setDocument( IDocument doc ) {
        fDoc = doc;
    }
    
    /**
     * Sets the current statement terminator string for this editor to the given
     * string.  If the given statement terminator is different from the default,
     * an XML-like tag containing the statement terminator will be added to the
     * beginning of the document.  If there already is such a tag, it will be updated
     * with the new statement terminator value.
     *  
     * @param newStatementTerminator the statement terminator to use for this editor
     */
    public void setStatementTerminator( String newStatementTerminator ) {
        /* Get the default statement terminator. */
        String defaultStatementTerminator = getDefaultStatementTerminator();
        
        /* Determine if there is any statement terminator tag in the document.  If
         * there is, get the statement terminator string from that. */
        String docStatementTerminator = null;
        StatementTerminatorTagInfo stmtTermTagInfo = getStatementTerminatorTagInfo();
        if (stmtTermTagInfo != null) {
            int groupCount = stmtTermTagInfo.tagMatcher.groupCount();
            if (groupCount == 1) {
                docStatementTerminator = stmtTermTagInfo.tagMatcher.group( 1 );
            }
        }

        IDocument doc = getDocument();
        if (doc != null) {
            /* If we have a statement terminator tag in the document, and the new
             * statement terminator being set is different from the one in the tag,  
             * then update the tag. */
            if (docStatementTerminator != null && !newStatementTerminator.equals( docStatementTerminator )) {
                int tagOffset = stmtTermTagInfo.tagRegion.getOffset();
                int tagLength = stmtTermTagInfo.tagRegion.getLength();
                
                /* If the new statement terminator is the default terminator, remove
                 * the tag from the document. */
                if (newStatementTerminator.equals( defaultStatementTerminator)) {
                    try {
                        doc.replace( tagOffset, tagLength, "" );
                    }
                    catch(BadLocationException e) {
                        // ignore
                    }
                }
                /* Otherwise change the tag in the document to contain the new 
                 * statement terminator. */
                else {
                    int stmtTermStartIndex = stmtTermTagInfo.tagMatcher.start( 1 );
                    int stmtTermEndIndex = stmtTermTagInfo.tagMatcher.end( 1 );
                    int stmtTermLength = stmtTermEndIndex - stmtTermStartIndex;
                    try {
                        doc.replace( tagOffset + stmtTermStartIndex, stmtTermLength, newStatementTerminator );
                    }
                    catch(BadLocationException e) {
                        // ignore
                    }
                }
            }
            /* We don't have a statement terminator tag in the document.  If the
             * new tag is not the default terminator, add a tag to the document
             * containing the new statement terminator at the start of the document. */
            else if (!newStatementTerminator.equals( defaultStatementTerminator)) {
                String stmtTermTag = STMT_TERM_TAG_1 + newStatementTerminator + STMT_TERM_TAG_2;
                
                try {
                    doc.replace( 0, 0, stmtTermTag );
                }
                catch(BadLocationException e) {
                    // ignore
                }
            }
        }
    }

} // end class
