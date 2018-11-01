/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.debugger.editorext;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.sqltools.common.ui.util.HTMLTextPresenter;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.debugger.core.ui.DebuggerCoreUIPlugin;
import org.eclipse.datatools.sqltools.debugger.model.SPStackFrame;
import org.eclipse.datatools.sqltools.sqleditor.sql.AbstractSQLEditorTextHover;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.information.IInformationProviderExtension2;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Used to show SP local variable information when debugging
 * 
 * @author Hui Cao
 *  
 */
public class SQLDebugHover extends AbstractSQLEditorTextHover implements ITextHoverExtension, ISelectionListener,
    IPartListener, IInformationProviderExtension2
{
    protected IInformationControlCreator _informationControlCreator;


    protected IEditorPart                _fEditor;
    protected ISelection                 _fSelection = null;


    public SQLDebugHover()
    {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener#partActivated(org.eclipse.ui.IWorkbenchPart)
     */
    public void partActivated(IWorkbenchPart part)
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener#partBroughtToTop(org.eclipse.ui.IWorkbenchPart)
     */
    public void partBroughtToTop(IWorkbenchPart part)
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener#partClosed(org.eclipse.ui.IWorkbenchPart)
     */
    public void partClosed(IWorkbenchPart part)
    {
        if (part.equals(_fEditor))
        {
            IWorkbenchPage page = _fEditor.getSite().getPage();
            page.removeSelectionListener(IDebugUIConstants.ID_DEBUG_VIEW, this);
            page.removePartListener(this);
            _fSelection = null;
            _fEditor = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener#partDeactivated(org.eclipse.ui.IWorkbenchPart)
     */
    public void partDeactivated(IWorkbenchPart part)
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener#partOpened(org.eclipse.ui.IWorkbenchPart)
     */
    public void partOpened(IWorkbenchPart part)
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IWorkbenchPart part, ISelection selection)
    {
        _fSelection = selection;
    }

    public SQLDebugHover(IEditorPart editor)
    {
        super();
        setEditor(editor);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jdt.ui.text.java.hover.IJavaEditorTextHover#setEditor(org.eclipse.ui.IEditorPart)
     */
    public void setEditor(IEditorPart editor)
    {
        if (editor != null)
        {
            _fEditor = editor;
            final IWorkbenchPage page = editor.getSite().getPage();
            page.addSelectionListener(IDebugUIConstants.ID_DEBUG_VIEW, this);
            page.addPartListener(this);
            // initialize selection
            Runnable r = new Runnable()
            {
                public void run()
                {
                    _fSelection = page.getSelection(IDebugUIConstants.ID_DEBUG_VIEW);
                }
            }
            ;
            DebuggerCoreUIPlugin.getDisplay().asyncExec(r);
        }
    }

    /**
     * Returns the stack frame in which to search for variables, or <code>null</code> if none.
     * 
     * @return the stack frame in which to search for variables, or <code>null</code> if none
     */
    protected SPStackFrame getFrame()
    {
        if (_fSelection instanceof IStructuredSelection)
        {
            IStructuredSelection selection = (IStructuredSelection) _fSelection;
            if (selection.size() == 1)
            {
                Object el = selection.getFirstElement();
                if (el instanceof IAdaptable)
                {
                    return (SPStackFrame) ((IAdaptable) el).getAdapter(SPStackFrame.class);
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.text.ITextHover#getHoverInfo(org.eclipse.jface.text.ITextViewer,
     *      org.eclipse.jface.text.IRegion)
     */
    public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion)
    {
        SPStackFrame frame = getFrame();
        if (frame != null)
        {
            try
            {

                IDocument document = textViewer.getDocument();
                if (document == null)
                {
                    return null;
                }

                String variableName = document.get(hoverRegion.getOffset(), hoverRegion.getLength());

                StringBuffer buffer = new StringBuffer();
                IVariable[] variables = frame.getVariables();
                for (int i = 0; i < variables.length; i++)
                {
                    IVariable localvar = variables[i];
                    if (localvar.getName().equals(variableName))
                    {
                        appendVariable(buffer, localvar);
                        break;
                    }
                }

                if (buffer.length() > 0)
                {
                    return buffer.toString();
                }

            }
            catch (Exception x)
            {
                DebuggerCorePlugin.getDefault().log(Messages.SQLDebugHover_error_getHoverInfo, x);
            }
        }

        return null;
    }

    /**
     * Append HTML for the given variable to the given buffer
     */
    private static void appendVariable(StringBuffer buffer, IVariable variable) throws DebugException
    {

        buffer.append("<p>"); //$NON-NLS-1$
        buffer.append("<pre>").append(variable.getName()).append("</pre>"); //$NON-NLS-1$ //$NON-NLS-2$
        buffer.append(" ="); //$NON-NLS-1$

        String type = getTypeName(variable);
        String varValue = variable.getValue().getValueString();
        if (varValue != null)
        {
            varValue = varValue.replaceAll("<", "&lt;");
            varValue = varValue.replaceAll(">", "&gt;");
        }
        String value = "<b><pre>" + varValue + "</pre></b>"; //$NON-NLS-1$ //$NON-NLS-2$

        if (type == null)
        {
            buffer.append(" null"); //$NON-NLS-1$
        }
        else
        {
            buffer.append(" ("); //$NON-NLS-1$
            buffer.append("<pre>").append(type).append("</pre>"); //$NON-NLS-1$ //$NON-NLS-2$
            buffer.append(") "); //$NON-NLS-1$
            buffer.append(value);
        }
        buffer.append("</p>"); //$NON-NLS-1$
    }

    private static String getTypeName(IVariable variable) throws DebugException
    {
        IValue value = variable.getValue();
        return value.getReferenceTypeName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.text.ITextHoverExtension#getHoverControlCreator()
     */
    public IInformationControlCreator getHoverControlCreator()
    {
        return new IInformationControlCreator()
        {
            public IInformationControl createInformationControl(Shell parent)
            {

                int style = SWT.NONE;

                DefaultInformationControl control = new DefaultInformationControl(parent, SWT.RESIZE, style,
                    new HTMLTextPresenter(true), getTooltipAffordanceString());
                control.setSizeConstraints(60, 10);
                return control;
            }
        }
        ;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.text.information.IInformationProviderExtension2#getInformationPresenterControlCreator()
     */
    public IInformationControlCreator getInformationPresenterControlCreator()
    {
        IInformationControlCreator controlCreator = null;

        if (_informationControlCreator == null)
        {
            _informationControlCreator = new IInformationControlCreator()
            {
                public IInformationControl createInformationControl(Shell shell)
                {
                    boolean cutDown = false;
                    int style = cutDown ? SWT.NONE : (SWT.V_SCROLL | SWT.H_SCROLL);
                    return new DefaultInformationControl(shell, SWT.RESIZE, style, new HTMLTextPresenter(true));
                }
            }
            ;
        }
        return _informationControlCreator;
    }

}
