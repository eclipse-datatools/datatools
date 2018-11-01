/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.editor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.texteditor.IDocumentProvider;

/**
 * This class implements a content provider for the SQL Editor outline page.
 */
public class SQLEditorOutlineContentProvider implements ITreeContentProvider {

    /** The outline page associated with this outline content provider. */
    private SQLEditorContentOutlinePage fOutlinePage = null;
    
    /** A list of segments. */
    private List fContent = null;
    
    /** A category identifier for SQL segments. */
    public final static String SQL_SEGMENTS = "__sql_segments__"; //$NON-NLS-1$

    /** A position updater for document changes. */
    private IPositionUpdater fPositionUpdater = null;

    /**
     * This class implements a simple segment for the SQL Editor outline view.
     */
    protected static class SQLSegment {
        public String name;
        public Position position;

        public SQLSegment( String name, Position position ) {
            this.name= name;
            this.position= position;
        }

        public String toString() {
            return name;
        }
    } // end inner class

    /**
     * Constructs an instance of this class with the given outline page.
     * 
     * @param outlinePage associated outline page
     */
    public SQLEditorOutlineContentProvider( SQLEditorContentOutlinePage outlinePage ) {
        super();

        fOutlinePage = outlinePage;
        fContent = new ArrayList();
        fPositionUpdater = new DefaultPositionUpdater( SQL_SEGMENTS );
    }

    /**
     * Disposes of this content provider.  This is called by the viewer when 
     * it is disposed.
     */
    public void dispose() {
        // do nothing
    }

    /**
     * Returns the child elements of the given parent element.
     * <p>
     * The difference between this method and
     * <code>IStructuredContentProvider.getElements</code> is that
     * <code>getElements</code> is called to obtain the tree viewer's root
     * elements, whereas <code>getChildren</code> is used to obtain the
     * children of a given parent element in the tree (including a root).
     * </p>
     * The result is not modified by the viewer.
     * 
     * @param parentElement the parent element
     * @return an array of child elements
     */
    public Object[] getChildren( Object element ) {
        Object[] children = new Object[0];
        if (hasChildren( element )) {
            children = fContent.toArray();
        }

        return children;
    }

    /**
     * Returns the elements to display in the viewer when its input is set to
     * the given element. These elements can be presented as rows in a table,
     * items in a list, etc. The result is not modified by the viewer.
     * 
     * @param inputElement the input element
     * @return the array of elements to display in the viewer
     */
    public Object[] getElements( Object inputElement ) {
        return fContent.toArray();
    }

    /**
     * Returns the parent for the given element, or <code>null</code>
     * indicating that the parent can't be computed. In this case the
     * tree-structured viewer can't expand a given node correctly if requested.
     * 
     * @param element the element
     * @return the parent element, or <code>null</code> if it has none or if
     *         the parent cannot be computed
     */
    public Object getParent(Object element) {
        return null;
    }

    /**
     * Returns whether the given element has children.
     * <p>
     * Intended as an optimization for when the viewer does not need the actual
     * children. Clients may be able to implement this more efficiently than
     * <code>getChildren</code>.
     * </p>
     * 
     * @param element the element
     * @return <code>true</code> if the given element has children, and
     *         <code>false</code> if it has no children
     */
    public boolean hasChildren( Object element ) {
        boolean value = false;
        if (element == fOutlinePage.getInput()) {
            value = true;
        }
        return value;
    }

    /**
     * Notifies this content provider that the given viewer's input has been
     * switched to a different element.
     * <p>
     * A typical use for this method is registering the content provider as a
     * listener to changes on the new input (using model-specific means), and
     * deregistering the viewer from the old input. In response to these change
     * notifications, the content provider propagates the changes to the viewer.
     * </p>
     * 
     * @param viewer the viewer
     * @param oldInput the old input element, or <code>null</code> if the viewer
     *            did not previously have an input
     * @param newInput the new input element, or <code>null</code> if the viewer
     *            does not have an input
     */
    public void inputChanged( Viewer viewer, Object oldInput, Object newInput ) {

        if (newInput != null && newInput != oldInput) {
            IDocumentProvider docProvider = fOutlinePage.getDocumentProvider();
            if (oldInput != null) {
                IDocument document = docProvider.getDocument( oldInput );
                if (document != null) {
                    try {
                        document.removePositionCategory( SQL_SEGMENTS );
                    } catch (BadPositionCategoryException x) {
                    }
                    document.removePositionUpdater( fPositionUpdater );
                    // document.removeDocumentListener( fOutlinePage.getDocumentListener() );
                    // document.removeDocumentPartitioningListener( this );
                }
            }
            
            fContent.clear();
            
            if (newInput != null) {
                IDocument document = docProvider.getDocument( newInput );
                document.addPositionCategory( SQL_SEGMENTS );
                document.addPositionUpdater( fPositionUpdater );
                // document.addDocumentListener( fOutlinePage.getDocumentListener() );
                // document.addDocumentPartitioningListener( this );
               
                parse( document );
            }
        }

        if (newInput == null) {
            fContent.clear();
        }
    }

    /**
     * Parses the given document into segments.  The segments are added to 
     * fContent.
     * 
     * @param document the document to parse
     */
    public void parse( IDocument document ) {
        // Determine all partitions, and their positions, convert to segments
        // and add the segments to the outline.
        
        int lines = document.getNumberOfLines();
        int increment = Math.max( Math.round(lines / 10), 10 );

        for (int line= 0; line < lines; line += increment) {

            int length = increment;
            if (line + increment > lines)
                length = lines - line;

            try {
                int offset = document.getLineOffset( line );
                int end = document.getLineOffset( line + length );
                length = end - offset;
                Position p = new Position( offset, length );
                document.addPosition( SQL_SEGMENTS, p );
                String segmentLabel = MessageFormat.format( SQLEditorResources.SQLEditor_outlinePage_sqlSegment_titlePattern, 
                        new Object[] { new Integer( offset )}); 
                fContent.add( new SQLSegment( segmentLabel, p)); //$NON-NLS-1$

            } catch (BadPositionCategoryException x) {
            } catch (BadLocationException x) {
            }
        }

//        int offset = 0;
//        while (offset < document.getLength()) {
//            try {
//                ITypedRegion region = document.getPartition( offset );
//                Position p = null;
//                // Create a segment only if it is not a comment.
//                String partitionId = region.getType();
//                if (partitionId == IDocument.DEFAULT_CONTENT_TYPE) {
//                    String docSegment = document.get( region.getOffset(), region.getLength() );
//                    String statement = docSegment.trim();
//                    
//                    // Get rid of leading and trailing whitespace
//                    // If contains non-blank characters - invalid
//                    if (statement.length() > 0) {
//                        int relativeOffset = 0;
//                        int length = 0;
//                        // Find the position of a broken string within segment.
//                        for (int i = 0; i < (docSegment.length() - statement.length() + 1); i++) {
//                            if (docSegment.regionMatches(i, statement, 0,
//                                    statement.length())) {
//                                relativeOffset = i;
//                                break;
//                            }
//                        }
//                        // Error - non-whitespace characters.
//                        SQLSegment partSegment = new SQLSegment();
//                        p = new Position( region.getOffset() + relativeOffset, statement.length() );
//                        partSegment.setValid( false );
//                        partSegment.setType( IDocument.DEFAULT_CONTENT_TYPE );
//                        partSegment.setPartitionLabel( SQLEditorResources.getString( "STR_INVALID_STATEMENT" ));
//                        // Prepare the statement for display in task list.  First trim
//                        // it down if it's too long to display nicely.
//                        if (statement.length() > 175) {
//                            statement = statement.substring(0, 175);
//                        }
//                        statement.replace( 'n', ' ' );
//                        partSegment.setMessage( SQLEditorResources.getString( "STR_INVALID_STATEMENT") + " " + statement );
//                        partSegment.setPosition( p );
//                        fContent.add( partSegment );
//                        document.addPosition( SQL_SEGMENTS, p );
//                    }
//                } else if (partitionId == SQLPartitionScanner.SQL_UNKNOWNSQL) {
//                    p = new Position( region.getOffset(), region.getLength() );
//                    SQLSegment partSegment = new SQLSegment();
//                    String docSegment = document.get( region.getOffset(), region.getLength() );
//                    partSegment.setValid( true );
//                    partSegment.setType( SQLPartitionScanner.SQL_UNKNOWNSQL );
//                    //String label = SQLEditorContentOutlinePage.getRenderStringFromDoc( docSegment.trim() );
//                    String label = "temporary label";
//                    partSegment.setPartitionLabel( label + " ..." );
//                    partSegment.setMessage( SQLEditorResources.getString( "STR_INVALID_STATEMENT" ) + label + " ..." );
//                    partSegment.setPosition( p );
//                    fContent.add( partSegment );
//                    document.addPosition( SQL_SEGMENTS, p );
//                } else if (partitionId != SQLPartitionScanner.SQL_COMMENT) {
//                    p = new Position( region.getOffset(), region.getLength() );
//                    SQLSegment partSegment;
//                    String docSegment = document.get( region.getOffset(), region.getLength() );
////                    partSegment = SegmentFactory.createSegment( docSegment, p, region.getType(), fVendorType );
//                    partSegment = new SQLSegment();
//                    fContent.add( partSegment );
//                    document.addPosition( SQL_SEGMENTS, p );
//                }
//                offset += region.getLength();
//            } catch (BadPositionCategoryException x) {
//            } catch (BadLocationException x) {
//            }
//        }
//
//        try {
//            Position[] category = document.getPositions( SQL_SEGMENTS );
//        } catch (BadPositionCategoryException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Gets the content of this outline view, as a list. 
     * 
     * @return the outline content
     */

    public List getContent() {
        return fContent;
    }

    /**
     * Clears the outline content. 
     */
    public void clearContent() {
        fContent.clear();
    }

    /**
     * Gets the position udater.
     * 
     * @return the position updater
     */
    public IPositionUpdater getPositionUpdater() {
        return fPositionUpdater;
    }

}