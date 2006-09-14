/**
 * Created on 2004-10-18
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import org.eclipse.datatools.sqltools.sqleditor.internal.sql.BestMatchHover;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.IInformationProviderExtension2;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * @author Hui Cao
 *  
 */
public class SQLInformationProvider implements IInformationProvider, IInformationProviderExtension2
{

    class EditorWatcher implements IPartListener
    {

        /**
         * @see IPartListener#partOpened(IWorkbenchPart)
         */
        public void partOpened(IWorkbenchPart part)
        {
        }

        /**
         * @see IPartListener#partDeactivated(IWorkbenchPart)
         */
        public void partDeactivated(IWorkbenchPart part)
        {
        }

        /**
         * @see IPartListener#partClosed(IWorkbenchPart)
         */
        public void partClosed(IWorkbenchPart part)
        {
            if (part == _fEditor)
            {
                _fEditor.getSite().getWorkbenchWindow().getPartService().removePartListener(_fPartListener);
                _fPartListener = null;
            }
        }

        /**
         * @see IPartListener#partActivated(IWorkbenchPart)
         */
        public void partActivated(IWorkbenchPart part)
        {
            update();
        }

        public void partBroughtToTop(IWorkbenchPart part)
        {
            update();
        }
    }

    protected IEditorPart                _fEditor;
    protected IPartListener              _fPartListener;

    protected String                     _fCurrentPerspective;
    protected BestMatchHover             _fImplementation = new BestMatchHover(null);
    protected IInformationControlCreator _informationControlCreator;

    public SQLInformationProvider(IEditorPart editor)
    {

        _fEditor = editor;

        if (_fEditor != null)
        {

            _fPartListener = new EditorWatcher();
            IWorkbenchWindow window = _fEditor.getSite().getWorkbenchWindow();
            window.getPartService().addPartListener(_fPartListener);

            update();
        }
    }

    protected void update()
    {

        IWorkbenchWindow window = _fEditor.getSite().getWorkbenchWindow();
        IWorkbenchPage page = window.getActivePage();
        if (page != null)
        {

            IPerspectiveDescriptor perspective = page.getPerspective();
            if (perspective != null)
            {
                String perspectiveId = perspective.getId();

                if (_fCurrentPerspective == null || _fCurrentPerspective != perspectiveId)
                {
                    _fCurrentPerspective = perspectiveId;

                    _fImplementation.setEditor(_fEditor);
                }
            }
        }
    }

    /*
     * @see IInformationProvider#getSubject(ITextViewer, int)
     */
    public IRegion getSubject(ITextViewer textViewer, int offset)
    {

        if (_fImplementation != null)
        {
            IRegion r = _fImplementation.getHoverRegion(textViewer, offset);
            return r;
        }

        return null;
    }

    /*
     * @see IInformationProvider#getInformation(ITextViewer, IRegion)
     */
    public String getInformation(ITextViewer textViewer, IRegion subject)
    {
        if (_fImplementation != null)
        {
            String s = _fImplementation.getHoverInfo(textViewer, subject);
            if (s != null && s.trim().length() > 0)
            return s;
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.text.information.IInformationProviderExtension2#getInformationPresenterControlCreator()
     */
    public IInformationControlCreator getInformationPresenterControlCreator()
    {
        IInformationControlCreator controlCreator = null;
        if (_fImplementation instanceof IInformationProviderExtension2)
        {
            controlCreator = ((IInformationProviderExtension2) _fImplementation)
                .getInformationPresenterControlCreator();
        }
        if (controlCreator != null)
        {
            return controlCreator;
        }

        if (_informationControlCreator == null)
        {
            _informationControlCreator = new IInformationControlCreator()
            {
                public IInformationControl createInformationControl(Shell shell)
                {
                    boolean cutDown = false;
                    int style = cutDown ? SWT.NONE : (SWT.V_SCROLL | SWT.H_SCROLL);
                    return new DefaultInformationControl(shell, SWT.RESIZE, style, null);
                }
            }
            ;
        }
        return _informationControlCreator;
    }

}
