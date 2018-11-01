/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.editor.ISQLEditorMarker;
import org.eclipse.datatools.sqltools.sqleditor.sql.AbstractSQLEditorTextHover;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.MarkerAnnotation;


/**
 * This class provides annotation hover (tooltip) support for SQL syntax error
 * and portability target annotations.
 * 
 * @author Hui Cao
 * 
 */
public class SQLAnnotationHover extends AbstractSQLEditorTextHover implements ITextHover, IAnnotationHover,
    ITextHoverExtension
{
    private ArrayList     _fAnnotations = new ArrayList();
    private IEditorPart   fEditor;

    public SQLAnnotationHover(IEditorPart editor)
    {
        setEditor(editor);
    }

    /**
     * Returns the information which should be presented when a hover popup is shown for the specified hover region. The
     * hover region has the same semantics as the region returned by <code>getHoverRegion</code>. If the returned
     * information is <code>null</code> or empty no hover popup will be shown.
     * 
     * @param textViewer the viewer on which the hover popup should be shown
     * @param hoverRegion the text range in the viewer which is used to determine the hover display information
     * @return the hover popup display information
     */
    public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion)
    {
        IAnnotationModel model = null;
        model = textViewer instanceof ISourceViewer ? ((ISourceViewer) textViewer).getAnnotationModel() : null;
        //avoids finding annotations again
        if (_fAnnotations.size() == 0)
        {
            findAnnotations(hoverRegion.getOffset(), model, null, 0);
        }

        String text = getHoverInfo();

        return text != null ? text.toString() : null;

    }

    /**
     * Returns the text region which should serve as the source of information to compute the hover popup display
     * information. The popup has been requested for the given offset.
     * <p>
     * For example, if hover information can be provided on a per method basis in a source viewer, the offset should be
     * used to find the enclosing method and the source range of the method should be returned.
     * 
     * @param textViewer the viewer on which the hover popup should be shown
     * @param offset the offset for which the hover request has been issued
     * @return the hover region used to compute the hover display information
     */
    public IRegion getHoverRegion(ITextViewer textViewer, int offset)
    {
        IDocument document = textViewer.getDocument();
        int start = 0;
        int end = 0;
        int lineNumber = 0;
        try
        {
            lineNumber = document.getLineOfOffset(offset);
        }
        catch (BadLocationException e)
        {
            SQLEditorPlugin.getDefault().log(e); //$NON-NLS-1$
        }

        findAnnotations(offset, textViewer instanceof ISourceViewer ? ((ISourceViewer) textViewer).getAnnotationModel()
            : null, textViewer.getDocument(), lineNumber);
        for (int i = 0; i < _fAnnotations.size(); i++)
        {
            Annotation annotation = (Annotation) _fAnnotations.get(i);
            if (annotation instanceof MarkerAnnotation)
            {
                MarkerAnnotation markerAnnotation = (MarkerAnnotation) annotation;
                try
                {
                    start = ((Integer) markerAnnotation.getMarker().getAttribute(IMarker.CHAR_START)).intValue();
                    end = ((Integer) markerAnnotation.getMarker().getAttribute(IMarker.CHAR_END)).intValue();
                    if (start <= offset && end >= offset)
                    {
                        return new Region(offset, 0);
                    }
                }
                catch (CoreException e1)
                {
                	SQLEditorPlugin.getDefault().log(e1);
                }
            }
        }

        return null;
    }

    /**
     * Returns the text which should be presented in the a hover popup window. This information is requested based on
     * the specified line number.
     * 
     * @param sourceViewer the source viewer this hover is registered with
     * @param lineNumber the line number for which information is requested
     * @return the requested information or <code>null</code> if no such information exists
     */
    public String getHoverInfo(ISourceViewer sourceViewer, int lineNumber)
    {
        if (_fAnnotations.size() == 0)
        {
            findAnnotations(-1, sourceViewer.getAnnotationModel(), sourceViewer.getDocument(), lineNumber);
        }

        String text = getHoverInfo();

        return text != null ? text.toString() : null;
    }

    /**
     * Finds annotations either by offset or by lineNumber
     * 
     * @param offset
     * @param model
     * @param document
     * @param lineNumber
     */
    private void findAnnotations(int offset, IAnnotationModel model, IDocument document, int lineNumber)
    {
        _fAnnotations.clear();
        if (model == null)
        {
            if (fEditor instanceof ITextEditor)
            {
                ITextEditor editor = (ITextEditor) fEditor;
                model = editor.getDocumentProvider().getAnnotationModel(editor.getEditorInput());
            }
        }
        if (model == null)
        {
            return;
        }
        for (Iterator it = model.getAnnotationIterator(); it.hasNext();)
        {
            Annotation annotation = (Annotation) it.next();
            Position position = model.getPosition(annotation);

            //if position is null, just return.
            if (position == null)
            {
                return;
            }
            try
            {
                if (position.overlapsWith(offset, 1) || document != null
                && document.getLineOfOffset(position.offset) == lineNumber)
                {
                    _fAnnotations.add(annotation);
                }
            }
            catch (BadLocationException e)
            {
            	SQLEditorPlugin.getDefault().log(e);
            }
        }
    }

    private String getHoverInfo()
    {
        String text = null;
        IPreferenceStore store = SQLEditorPlugin.getDefault().getPreferenceStore();
        for (int i = 0; i < _fAnnotations.size(); i++)
        {
            Annotation annotation = (Annotation) _fAnnotations.get(i);
            if (annotation instanceof MarkerAnnotation)
            {
                try
                {
                    IMarker marker = ((MarkerAnnotation) annotation).getMarker();
                    if (marker.getType().equals(EditorConstants.SYNTAX_MARKER_TYPE)
                    || marker.getType().equals(EditorConstants.PORTABILITY_MARKER_TYPE))
                    {
                        if (store.getBoolean(PreferenceConstants.SHOW_SYNTAX_ERROR_DETAIL))
                        {
                            text = (String) marker.getAttribute(IMarker.MESSAGE);
                        }
                        else
                        {
                            text = (String) marker.getAttribute(ISQLEditorMarker.SHORT_MESSAGE);
                        }
                        //TODO: consider combine multiple annotations
                        break;
                    }

                }
                catch (CoreException e)
                {
                	SQLEditorPlugin.getDefault().log(e);
                }
            }
        }
        _fAnnotations.clear();
        return text;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.AbstractSQLEditorTextHover#setEditor(org.eclipse.ui.IEditorPart)
     */
    public void setEditor(IEditorPart editor)
    {
        fEditor = editor;
    }

}
