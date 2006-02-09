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
package org.eclipse.datatools.sqltools.sqleditor.internal.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceRuleFactory;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.datatools.sqltools.core.IDBFactory;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.sql.parser.ParseException;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.Token;
import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.texteditor.MarkerAnnotation;

/**
 * This class will listen to the documentChanged event of the SQLEditor and update the outline view( if there is one )
 * and the annotations.
 * 
 * @author Hui Cao
 *  
 */
public class SQLUpdater implements Runnable, IDocumentListener, IPropertyChangeListener
{
    private SQLEditor          _editor                 = null;
    private SQLOutlinePage     _fOutlinePage           = null;
    private IEditorInput       _input                  = null;
    private IAnnotationModel   _annotationModel;
    private IResource          _resource;
    private String             _portableTarget         = "";
    private ArrayList          _staleAnnotations       = new ArrayList();
    private boolean            _remembered             = false;
	private ParsingResult _result;

    /**
     * @param editor
     */
    public SQLUpdater(SQLEditor editor)
    {
        this._editor = editor;
    }

    /////////////////////////////////////////////////////////////////////////////////
    //  IDocumentListener Interface
    /////////////////////////////////////////////////////////////////////////////////
    public void documentAboutToBeChanged(DocumentEvent event)
    {
        setInput(_editor.getEditorInput());
        rememberAnnotations();
    }

    public void documentChanged(DocumentEvent event)
    {
        _editor.getSite().getShell().getDisplay().timerExec(1000, this);
    }

    /////////////////////////////////////////////////////////////////////////////////
    //  IPropertyChangeListener Interface
    /////////////////////////////////////////////////////////////////////////////////
    public void propertyChange(PropertyChangeEvent event)
    {
        if (event.getProperty().equals(PreferenceConstants.EDITOR_PORTABILITY_CHECK_TARGET))
        {
            rememberAnnotations();
            run();
        }
    }

    public void setOutlinePage(SQLOutlinePage outlinePage)
    {
        _fOutlinePage = outlinePage;
        if (_fOutlinePage != null && _result != null)
        {
            _fOutlinePage.setInput(_result.getRootNode());
        }
        
    }

    public void run()
    {
        setInput(_editor.getEditorInput());
        try
        {
            //use ISchedulingRule to avoid locking the whole workspace
            IResourceRuleFactory ruleFactory = ResourcesPlugin.getWorkspace().getRuleFactory();
            ISchedulingRule rule = ruleFactory.markerRule(_resource);
            //TODO: Here I tried to use WorkspaceJob to let the update operation run in background, 
            //but I failed because SQLParser isn't thread safe. (by Hui Cao) 
            _resource.getWorkspace().run(new IWorkspaceRunnable()
            {
                public void run(IProgressMonitor monitor) throws CoreException
                {
                    update();
                }
            }
            , rule, IResource.NONE, new NullProgressMonitor());
        }
        catch (CoreException e)
        {
            SQLEditorPlugin.getDefault().log(SQLEditorResources.getString("SQLUpdater.error.annotation"), e);
        }

        // XXX: There seemed to have a bug in eclipse's jface text. When in the AnnotationModel
        // an annotation is removed, AnnotationPainter seemed not to handle it correctly. 
        // So we are forcing a repaint here, to work around the problem
        if (_editor != null)
        {
            ISourceViewer sv = _editor.getSV();
            if (sv != null)
            {
                StyledText text = sv.getTextWidget();
                if (text != null && !text.isDisposed())
                {
                    text.redraw();
                }
            }
        }
    }

    /**
     *  
     */
    private void update()
    {
        //both outline and annotation depend on the same parsing process
        IDBFactory f =SQLToolsFacade.getDBFactoryByVendorIdentifier(_editor.getConnectionInfo().getDatabaseVendorDefinitionId());
        SQLParser p = f.getSQLService().getSQLParser();
        ISourceViewer viewer = _editor.getSV();
        if (viewer == null)
        {
            return;
        }
        String content = viewer.getDocument().get();
        //Add '\n' to avoid parser to throw exception when last line is single line comment.
        content = content + "\n";
        if (content == null || p == null)
        {
            removeMarkers(false);
            return;
        }
                
        _result = p.parse(content);
		_result.getRootNode().setEditorInput(_input);
        IDocument document = _editor.getDocumentProvider().getDocument(_input);
        _result.getRootNode().setDocument(document);
        _result.getRootNode().setAnnotationModel(_annotationModel);
        _editor.setParsingResult(_result);

        removeMarkers(false);

        SQLParser pp = null;
        _portableTarget = SQLEditorPlugin.getDefault().getPreferenceStore().getString(
            PreferenceConstants.EDITOR_PORTABILITY_CHECK_TARGET);
        if (_portableTarget != null)
        {
        	IDBFactory pf =SQLToolsFacade.getDBFactoryByDBDefName(_portableTarget);
        	pp = pf.getSQLService().getSQLParser();
        	if (pp != null && !(pp.getClass().equals(p.getClass())))
        	{
        		ParsingResult presult = pp.parse(content);
        		updatePortableAnnotation(presult.getExceptions());
        	}
        }

        //update error markers
        ArrayList exceptions = _result.getExceptions();
        updateErrorAnnotation(exceptions);

        if (_fOutlinePage != null)
        {
            _fOutlinePage.setInput(_result.getRootNode());
        }
    }

    /**
     * create markers according to the parse exceptions
     * 
     * @param exceptions Collections of ParseException
     * @throws CoreException
     */
    private void updateErrorAnnotation(ArrayList exceptions)
    {
        try
        {
            for (Iterator iter = exceptions.iterator(); iter.hasNext();)
            {
                //create markers according to the parse exceptions
                ParseException ex = (ParseException) iter.next();
                HashMap attrs = new HashMap();
                attrs.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
                String storageName = "";//$NON-NLS-1$
                if (!(_input instanceof IFileEditorInput)) {
					storageName = _input.getName() + ": ";//$NON-NLS-1$
				}
                attrs.put(IMarker.MESSAGE, storageName + ex.getMessage());
                updateAnnotation(ex, EditorConstants.SYNTAX_MARKER_TYPE, attrs);
            }
        }
        catch (CoreException e)
        {
            SQLEditorPlugin.getDefault().log(SQLEditorResources.getString("SQLUpdater.error.annotation"), e); //$NON-NLS-1$
        }
    }

    /**
     * create portable task markers according to the parse exceptions
     * 
     * @param exceptions Collections of ParseException
     * @throws CoreException
     */
    private void updatePortableAnnotation(ArrayList exceptions)
    {
        try
        {
            for (Iterator iter = exceptions.iterator(); iter.hasNext();)
            {
                //create markers according to the parse exceptions
                ParseException ex = (ParseException) iter.next();
                HashMap attrs = new HashMap();
                attrs.put(IMarker.USER_EDITABLE, Boolean.FALSE);
                String storageName = "";//$NON-NLS-1$
                if (!(_input instanceof IFileEditorInput)) {
					storageName = _input.getName() + ": ";//$NON-NLS-1$
				}
                attrs.put(IMarker.MESSAGE, storageName
                    + SQLEditorResources.getString("SQLUpdater.nonportable", new String[]{_portableTarget}) + ex.getMessage()); //$NON-NLS-1$
                updateAnnotation(ex, EditorConstants.PORTABILITY_MARKER_TYPE, attrs);
            }
        }
        catch (CoreException e)
        {
            SQLEditorPlugin.getDefault().log(SQLEditorResources.getString("SQLUpdater.error.annotation"), e); //$NON-NLS-1$
        }
    }

    /**
     * @param ex
     * @param markerType see IMarker.TASK ...
     * @param attributes String Object pairs for the new marker
     * @throws CoreException
     */
    private void updateAnnotation(ParseException ex, String markerType, Map attributes) throws CoreException
    {
        IMarker marker = _resource.createMarker(markerType);
        marker.setAttributes(attributes);
        //since we will always refresh the task markers each time we parse the sql text, there's no
        //need to persist them.
        marker.setAttribute(IMarker.TRANSIENT, true);
        Token errorToken = ex.currentToken.next;
        int start = 0;
        int end = 0;
        int line = 0;
        try
        {
            IDocument document = _editor.getSV().getDocument();
            if (ex.currentToken.beginLine < 1)
            {
                return;
            }
            if (errorToken.image == null || "".equals(errorToken.image))
            {
                //if the errorToken is not visible (<EOF>), select the current token
                start = document.getLineOffset(ex.currentToken.beginLine - 1) + ex.currentToken.beginColumn - 1;
                end = document.getLineOffset(ex.currentToken.endLine - 1) + ex.currentToken.endColumn;
                line = ex.currentToken.beginLine;
            }
            else
            {
                start = document.getLineOffset(errorToken.beginLine - 1) + errorToken.beginColumn - 1;
                end = document.getLineOffset(errorToken.endLine - 1) + errorToken.endColumn;
                line = errorToken.beginLine;
            }
            if (start < 0 || end - start < 0)
            {
                return;
            }
        }
        catch (BadLocationException e1)
        {
            SQLEditorPlugin.getDefault().log(SQLEditorResources.getString("SQLUpdater.error.location"), e1); //$NON-NLS-1$
        }
        marker.setAttributes(new String[] 
        {
            IMarker.CHAR_START, IMarker.CHAR_END, IMarker.LINE_NUMBER
        }
        , new Object[] 
        {
            new Integer(start), new Integer(end), new Integer(line)
        }
        );
        MarkerAnnotation anno = new MarkerAnnotation(marker);
        Position position = new Position(start, end - start);
        //once the annotation is added to the annotation model, the platform knows how to present it
        _annotationModel.addAnnotation(anno, position);
    }

    /**
     * initialize _input, _resource and _annotaionModel . Since the editor input may change during a SQL 
     * Editor's lifecycle, this method will be called everytime before update.
     * 
     * @param input
     */
    void setInput(IEditorInput input)
    {
        _input = input;
        _resource = input instanceof IFileEditorInput ? (IResource) ((IFileEditorInput) input).getFile()
            : (IResource) ResourcesPlugin.getWorkspace().getRoot();
        // the annotationModel is created in Editor.doSetInput(input) by the platform
        _annotationModel = _editor.getDocumentProvider().getAnnotationModel(input);
    }

    /**
     *  
     */
    private void rememberAnnotations()
    {
        //we must record the stale annotations before the document is changed so that there will be no dangling markers
        _staleAnnotations = new ArrayList();
        for (Iterator iter = _annotationModel.getAnnotationIterator(); iter.hasNext();)
        {
            Annotation anno = (Annotation) iter.next();
            if (anno instanceof MarkerAnnotation)
            {
                IMarker marker = ((MarkerAnnotation) anno).getMarker();
                String type = "";
                try
                {
                    type = marker.getType();
                }
                catch (CoreException e2)
                {
                    //marker does not exist
                    _staleAnnotations.add(anno);
                    continue;
                }
                //preserve user editable tasks
                if (type.equals(EditorConstants.SYNTAX_MARKER_TYPE) || type.equals(EditorConstants.PORTABILITY_MARKER_TYPE))
                {
                    _staleAnnotations.add(anno);
                }
            }
        }
        _remembered = true;
    }

    /**
     * remove all the problem markers. Remember to call it when the file is closing
     * 
     * @see removeMarkers(boolean group), this is a shortcut to removeMarkers(true)
     */
    public void removeMarkers()
    {
        removeMarkers(true);
    }

    /**
     * remove all the problem markers. Remember to call it when the file is closing
     * 
     * @param group whether to use IWorkspaceRunnable to group the changes
     */
    public void removeMarkers(boolean group)
    {
        if (!_remembered)
        {
            rememberAnnotations();
        }
        if (group)
        {
            try
            {
                IResourceRuleFactory ruleFactory = ResourcesPlugin.getWorkspace().getRuleFactory();
                ISchedulingRule rule = ruleFactory.markerRule(_resource);
                _resource.getWorkspace().run(new IWorkspaceRunnable()
                {
                    public void run(IProgressMonitor monitor) throws CoreException
                    {
                        removeRememberedMarkers();
                    }
                }
                , rule, IResource.NONE, new NullProgressMonitor());
            }
            catch (CoreException e)
            {
            }
        }
        else
        {
            removeRememberedMarkers();
        }
    }

    /**
     *  
     */
    private void removeRememberedMarkers()
    {
        for (Iterator iter = _staleAnnotations.iterator(); iter.hasNext();)
        {
            Annotation anno = (Annotation) iter.next();
            if (anno instanceof MarkerAnnotation)
            {
                IMarker marker = ((MarkerAnnotation) anno).getMarker();
                try
                {
                    //after marker is removed, it disappeared in the problem view
                    marker.delete();
                    anno.markDeleted(true);
                }
                catch (CoreException e2)
                {
                    SQLEditorPlugin.getDefault().log(SQLEditorResources.getString("SQLUpdater.error.removemarker"), e2); //$NON-NLS-1$
                }
            }
            //after annotation is removed, it disappeared from the side bar
            _annotationModel.removeAnnotation(anno);
        }
        _remembered = false;
    }

}
