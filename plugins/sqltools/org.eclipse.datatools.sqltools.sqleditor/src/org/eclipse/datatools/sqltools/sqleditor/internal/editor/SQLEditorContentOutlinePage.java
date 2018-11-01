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

import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLSegment;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

/**
 * This class provides a content outline page for the SQL Editor.
 */
public class SQLEditorContentOutlinePage extends ContentOutlinePage {

    protected Object fInput;
    protected IDocumentProvider fDocumentProvider;
    protected SQLEditor fSQLEditor;

    // TODO: fix this...it's just an example, hard-coded to have 10 segments
//	/**
//	 * Divides the editor's document into ten segments and provides elements for them.
//	 */
//	protected class ContentProvider implements ITreeContentProvider {
//
//		protected final static String SEGMENTS= "__sql_segments"; //$NON-NLS-1$
//		protected IPositionUpdater fPositionUpdater= new DefaultPositionUpdater( SEGMENTS );
//		protected List fContent= new ArrayList(10);
//
//        
//        /**
//         * Parses the given document into segments.
//         * @param document the document to parse
//         */
//		protected void parse( IDocument document ) {
//
//			int lines = document.getNumberOfLines();
//			int increment = Math.max(Math.round(lines / 10), 10);
//
//			for (int line = 0; line < lines; line += increment) {
//
//				int length = increment;
//				if (line + increment > lines)
//					length = lines - line;
//
//				try {
//					int offset = document.getLineOffset( line );
//					int end = document.getLineOffset( line + length );
//					length = end - offset;
//					Position p = new Position( offset, length );
//					document.addPosition( SEGMENTS, p );
//                    fContent.add( new Segment( MessageFormat.format("position {0}", new Object[] { new Integer(offset) }), p)); //$NON-NLS-1$
//					//fContent.add( new Segment( MessageFormat.format(SQLEditorPlugin.getResourceString( "OutlinePage.segment.title_pattern" ), new Object[] { new Integer(offset) }), p)); //$NON-NLS-1$
//
//				} catch (BadPositionCategoryException x) {
//				} catch (BadLocationException x) {
//				}
//			}
//		}
//
//		/**
//         * Notifies this content provider that the given viewer's input
//         * has been switched to a different element.
//		 * @see IContentProvider#inputChanged(Viewer, Object, Object)
//		 */
//		public void inputChanged( Viewer viewer, Object oldInput, Object newInput ) {
//			if (oldInput != null) {
//				IDocument document= fDocumentProvider.getDocument( oldInput );
//				if (document != null) {
//					try {
//						document.removePositionCategory( SEGMENTS );
//					} catch (BadPositionCategoryException x) {
//					}
//					document.removePositionUpdater( fPositionUpdater );
//				}
//			}
//
//			fContent.clear();
//
//			if (newInput != null) {
//				IDocument document= fDocumentProvider.getDocument( newInput );
//				if (document != null) {
//					document.addPositionCategory( SEGMENTS );
//					document.addPositionUpdater( fPositionUpdater );
//
//					parse( document );
//				}
//			}
//		}
//
//		/**
//         * Disposes of this content provider.  
//         * This is called by the viewer when it is disposed.
//		 * @see IContentProvider#dispose
//		 */
//		public void dispose() {
//			if (fContent != null) {
//				fContent.clear();
//				fContent= null;
//			}
//		}
//
//        /**
//         * Returns the child elements of the given parent element.
//         * @see ITreeContentProvider#getChildren(Object)
//         */
//        public Object[] getChildren( Object element ) {
//            if (element == fInput)
//                return fContent.toArray();
//            return new Object[0];
//        }
//
//		/**
//         * Returns the elements to display in the viewer when its input is set 
//         * to the given element.
//		 * @see IStructuredContentProvider#getElements(Object)
//		 */
//		public Object[] getElements( Object element ) {
//			return fContent.toArray();
//		}
//
//		/**
//         * Returns the parent for the given element, or <code>null</code> 
//         * indicating that the parent can't be computed.
//		 * @see ITreeContentProvider#getParent(Object)
//		 */
//		public Object getParent( Object element ) {
//			if (element instanceof Segment)
//				return fInput;
//			return null;
//		}
//
//        /**
//         * Returns whether the given element has children.
//         * @see ITreeContentProvider#hasChildren(Object)
//         */
//        public boolean hasChildren( Object element ) {
//            return element == fInput;
//        }
//
//	} // end inner class ContentProvider

	/**
	 * Constructs an instance of this class using the given provider and the given editor.
     * 
     * @param provider the document provider to use
     * @param sqlEditor the SQL text editor associated with this view
	 */
	public SQLEditorContentOutlinePage( IDocumentProvider provider, SQLEditor sqlEditor ) {
		super();
		fDocumentProvider = provider;
		fSQLEditor = sqlEditor;
	}
	
	/**
     * Creates the SWT control for this page under the given parent control.
     * The <code>ContentOutlinePage</code> implementation of this 
     * <code>IContentOutlinePage</code> method creates a tree viewer.
     * 
	 * @see org.eclipse.ui.views.contentoutline.ContentOutlinePage#createControl(org.eclipse.swt.widgets.Composite)
     * @see org.eclipse.ui.part.IPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {
		super.createControl( parent );

		TreeViewer viewer = getTreeViewer();
		viewer.setContentProvider( new SQLEditorOutlineContentProvider( this ));
		viewer.setLabelProvider( new LabelProvider() );
		viewer.addSelectionChangedListener( this );

		if (fInput != null)
			viewer.setInput( fInput );
	}
	
    /**
     * Gets the document provider associated with this outline page.
     * 
     * @return the document provider
     */
    public IDocumentProvider getDocumentProvider() {
        return fDocumentProvider;
    }
    
    /**
     * Gets the input for this outline page.
     * 
     * @return the input for this outline page
     */
    public Object getInput() {
        return fInput;
    }

    /**
     * Gets the SQL Editor associated with this outline page.
     * 
     * @return the associated SQL Editor
     */
    public SQLEditor getSQLEditor() {
        return fSQLEditor;
    }

	/**
     * Notifies that the selection has changed, using the given SelectionChangedEvent.
     * 
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged( SelectionChangedEvent event ) {

		super.selectionChanged( event );

		ISelection selection = event.getSelection();
		if (selection.isEmpty())
			fSQLEditor.resetHighlightRange();
		else {
			SQLSegment segment = (SQLSegment) ((IStructuredSelection) selection).getFirstElement();
			int start = segment.getPosition().getOffset();
			int length = segment.getPosition().getLength();
			try {
				fSQLEditor.setHighlightRange( start, length, true );
			} catch (IllegalArgumentException x) {
				fSQLEditor.resetHighlightRange();
			}
		}
	}

    /**
     * Sets the document provider associated with this outline page to the given
     * document provider.
     * 
     * @param docProvider the document provider to use
     */
    public void setDocumentProvider( IDocumentProvider docProvider ) {
        fDocumentProvider = docProvider;
    }
    
	/**
	 * Sets the input of this outline page to the given object. 
     * 
     * @param input the input to set
	 */
	public void setInput( Object input ) {
		fInput = input;
		update();
	}
	
    /**
     * Sets the SQL Editor associated with this outline page to the given editor.
     * 
     * @param sqlEditor the associated SQL Editor
     */
    public void setSQLEditor( SQLEditor sqlEditor ) {
        fSQLEditor = sqlEditor;
    }
    
	/**
	 * Updates the outline page.
	 */
	public void update() {
		TreeViewer viewer = getTreeViewer();

		if (viewer != null) {
			Control control = viewer.getControl();
			if (control != null && !control.isDisposed()) {
				control.setRedraw( false );
				viewer.setInput( fInput );
				viewer.expandAll();
				control.setRedraw( true );
			}
		}
	}
    
} // end class
