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
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import org.eclipse.datatools.sqltools.editor.contentassist.ISQLDBProposalsService;
import org.eclipse.datatools.sqltools.editor.contentassist.SQLDBProposalsRequest;
import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import com.ibm.icu.util.StringTokenizer;

/**
 * This class computes proposals for text completion.
 * 
 * @author Hetty Dougherty
 *  
 */
public class SQLCompletionEngine implements ISQLCompletionEngine {

    private SQLCompletionProposalFactory fProposalFactory;
    private ISQLDBProposalsService fDBProposalsService;

    /**
     * Constructs an instance of this class. This is the default constructor.
     */
    public SQLCompletionEngine() {
        super();
        fProposalFactory = new SQLCompletionProposalFactory();
        fDBProposalsService = null;
    }

    /**
     * Computes and returns an array of text completion proposals based on the
     * given document, document region, and location in the document.
     * 
     * @param doc the current document
     * @param partition a document partion region. A region consists of offset,
     *            length, and type.
     * @param documentOffset the current offset in the document
     * @see ISQLCompletionEngine#computeProposals(IDocument, ITypedRegion, int,
     *      IDBProposalsService)
     */
    public ICompletionProposal[] computeProposals(IDocument doc, ITypedRegion partition, int documentOffset, Point selection ) {
        ICompletionProposal[] result = null;

        if (doc != null && partition != null && documentOffset >= 0) {

            List proposalList = null;
            String replacementStr = null;
            String displayStr = null;
            String sortingString = null;
            char docOffsetChar;
            
            // Get document offset to the start of "word" where content assist
            // is requested.
            int offset = getPartitionOffset( doc, partition, documentOffset, partition.getOffset() );

            
            try {
                sortingString = doc.get( offset, documentOffset - offset );            
           
                docOffsetChar = doc.getChar( documentOffset - 1 );
                
                //TODO should get DB objects proposal based on context type from parser, instead of the '.' char
                if (docOffsetChar == '.') {
                    StringTokenizer tokenizer = new StringTokenizer(sortingString, "."); //$NON-NLS-1$
                    List tokenList = new ArrayList();
                    while (tokenizer.hasMoreTokens()) {
                        tokenList.add(tokenizer.nextToken());
                    }
                   SQLDBProposalsRequest request = new SQLDBProposalsRequest(sortingString, SQLParserConstants.SCOPE_DEFAULT , null, null);
                    proposalList = fProposalFactory.getDBObjectProposals( request);
                      
                }

            }
            catch (BadLocationException exc) {
            }

            //Sort proposals            
            Vector pList = sortProposals( proposalList, sortingString, false );

            result = new ICompletionProposal[pList.size()];
            int resultIdx = 0;
            for (int i = 0; i < pList.size(); i++) {
                displayStr = pList.get( i ).toString();
                replacementStr = displayStr;
                Image image = null;
                String additionalInfo = null;
                int relevance = SQLCompletionProposal.OTHER;
                if (pList.get( i ) instanceof SQLDBProposal) {
                    SQLDBProposal dbProposal = (SQLDBProposal) pList.get( i );
                    String parentNameUC = dbProposal.getParentName().toUpperCase();
                    String sortingStringUC = sortingString.toUpperCase();
                    StringTokenizer strTokens = new StringTokenizer( parentNameUC, "." ); //$NON-NLS-1$

                    /* For an ambiguous case where user enters "SCHEMA." and
                     * there is also a table called SCHEMA then the list of proposals
                     * also includes all columns in the SCHEMA.SCHEMA table.
                     * If user select COL1, then we want to append SCHEMA.COL1
                     * following user's input "SCHEMA."
                     */
                    if (strTokens.countTokens() > 1 && parentNameUC.startsWith( sortingStringUC )) {
                        String preceedingNames = parentNameUC.replaceFirst( sortingStringUC, "" ); //$NON-NLS-1$
                        replacementStr = preceedingNames + "." + dbProposal.toString(); //$NON-NLS-1$
                    }

                    image = ((SQLDBProposal) pList.get( i )).getImage();
                    additionalInfo = ((SQLDBProposal) pList.get( i )).getParentName() + "." //$NON-NLS-1$
                            + ((SQLDBProposal) pList.get( i )).getName();
                    
                    relevance = getRelevance(dbProposal.getType());
                }
                
               	if (pList.size() <= proposalList.size()) {   
               		if (replacementStr != null &&  documentOffset >= 0) { 
               			result[resultIdx] = new SQLCompletionProposal( replacementStr, documentOffset,
                           0, replacementStr.length(), image, displayStr, null, additionalInfo, relevance);
               			resultIdx++;
               		}	
               	}
               
            }
        }
        
        return result;
    }

    /**
     * Converts SQLDBProposal db object type to SQLCompletionProposal 
     * @param dbObjectType
     * @return
     */
    private int getRelevance(int dbObjectType)
    {
    	switch (dbObjectType) {
		case SQLDBProposal.SCHEMA_OBJTYPE:
			return SQLCompletionProposal.OTHER;
		case SQLDBProposal.TABLE_OBJTYPE:
			return SQLCompletionProposal.TABLE;
		case SQLDBProposal.TABLECOLUMN_OBJTYPE:
			return SQLCompletionProposal.COLUMN;
		default:
			return SQLCompletionProposal.OTHER;
		}
    }
    /**
	 * Returns the document offset to the start of the "word" where content
	 * assist is requested.
	 * 
	 * @param doc
	 *            the current document
	 * @param partition
	 *            document partition region. A region consists of offset,
	 *            length, and type.
	 * @param documentOffset
	 *            offset into the document
	 * @param offset
	 *            offset in the document to start of the name preceeding the
	 *            activation character
	 */
    public int getPartitionOffset( IDocument doc, ITypedRegion partition, int documentOffset, int offset ) {
        boolean loop = true;

        int offset1 = documentOffset - 1;
        try {
            while (loop && offset <= offset1) {
                switch (doc.getChar( offset1 )) {
                    case 10: // linefeed
                    case 32: // space
                    case 13: // return
                    case 9: { // tab
                        loop = false;
                    }
                        break;
                    default: {
                        offset1--;
                    }
                        break;
                }
            }
        }
        catch (BadLocationException x) {
        }

        offset = offset1 + 1;

        return offset;
    }

    /**
     * Returns the document offset to the start of the "word" where content
     * assist is requested.
     * 
     * @param doc the current document
     * @param partition document partition region. A region consists of offset,
     *            length, and type.
     * @param documentOffset offset into the document
     * @param leadingString
     * @param position <code>1</code> from current position <code>0</code>
     *            after leadingString
     * @return the partition offset
     */
    public int getPartitionOffset( IDocument doc, ITypedRegion partition, int documentOffset, String leadingString,
            int position ) {
        int offset = partition.getOffset() + leadingString.length();

        if (documentOffset <= offset)
            return offset;

        switch (position) {
            case 0: {
            }
                break;
            default: {
                boolean loop = true;
                int offset1 = documentOffset - 1;
                try {
                    while (loop && offset <= offset1) {
                        switch (doc.getChar( offset1 )) {
                            case 10: // linefeed
                            case 32: // space
                            case 13: // return
                            case 9: { // tab
                                loop = false;
                            }
                                break;
                            default: {
                                offset1--;
                            }
                                break;
                        }
                    }
                }
                catch (BadLocationException x) {
                }
                ;

                offset = offset1 + 1;
            }
                break;
        }

        return getPartitionOffset( doc, partition, documentOffset, offset );
    }

    /**
     * Sorts the given list of proposals.
     * 
     * @param proposals list of proposals to be sorted
     * @param sortingString string to be used as sorting "seed"
     * @param showAll
     * @return the sorted list of proposals
     */
    public Vector sortProposals( List proposals, String sortingString, boolean showAll ) {

        Vector aList = new Vector();
        if (proposals == null)
            return aList;

        if (sortingString != null && sortingString.length() > 0) {
            char triggerChar = sortingString.charAt( sortingString.length() - 1 );
            String precedingName = null;
            if (triggerChar == '.') {
                precedingName = sortingString.substring( 0, sortingString.length() - 1 );
                if (!precedingName.equals( "" )) { //$NON-NLS-1$

                    ListIterator listIterator = proposals.listIterator();
                    while (listIterator.hasNext()) {
                        Object proposal = listIterator.next();
                        if (proposal instanceof SQLDBProposal) {
                            String parentNameUC = ((SQLDBProposal) proposal).getParentName().toUpperCase();
                            String precedingNameUC = precedingName.toUpperCase();
                            if (parentNameUC.endsWith( precedingNameUC )) {
                                aList.add( proposal );
                            }
                        }
                    }
                }
            }
            else {
                for (int i = 0; i < proposals.size(); i++) {
                    if (!(proposals.get( i ) instanceof SQLDBProposal)) {
                        if (proposals.get( i ).toString().toUpperCase().trim().indexOf( sortingString.toUpperCase() ) != 0)
                            continue;
                        aList.add( proposals.get( i ) );
                    }
                }
            }
        }

        if (showAll && aList.size() == 0) {
            aList.addAll( proposals );
        }

        return aList;
    }

    /**
     * Sets the <code>DBProposalsService</code> to use.
     * 
     * @param dbProposalsService the <code>DBProposalsService</code> to use
     */
    public void setDBProposalsService( ISQLDBProposalsService dbProposalsService ) {
        fDBProposalsService = dbProposalsService;
        fProposalFactory.setFactoryDBContext( dbProposalsService );
    }

    /**
     * Gets the current <code>DBProposalsService</code>.
     * 
     * @param dbProposalsService the current <code>DBProposalsService</code>.
     */
    public ISQLDBProposalsService getDBProposalsService() {
        return fDBProposalsService;
    }

    public IContextInformation[] computeContextInformation(IDocument doc, ITypedRegion partition, int documentOffset, Point selection)
    {
        return null;
    }

}