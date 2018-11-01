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
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IAbstractTextEditorHelpContextIds;

/**
 * @author Hui Cao
 *  
 */
class CopyToClipboardAction extends SelectionDispatchAction
{

    private final static int        _MAX_REPEAT_COUNT = 10;

    private final Clipboard         _fClipboard;
    private SelectionDispatchAction _fPasteAction;          //may be null

    public CopyToClipboardAction(IWorkbenchSite site, Clipboard clipboard, SelectionDispatchAction pasteAction)
    {
        super(site);

        setText(Messages.CopyAction_label); 
        setToolTipText(Messages.CopyAction_tooltip); 

        _fClipboard = clipboard;
        _fPasteAction = pasteAction;

        ISharedImages workbenchImages = PlatformUI.getWorkbench().getSharedImages();
        setDisabledImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
        setImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));

        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IAbstractTextEditorHelpContextIds.COPY_ACTION);

        update(getSelection());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jdt.ui.actions.SelectionDispatchAction#selectionChanged(org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void selectionChanged(IStructuredSelection selection)
    {
        setEnabled(canEnable(selection));
    }

    private boolean canEnable(IStructuredSelection selection)
    {
        //TODO: add multiple copy support
        return (selection.size() == 1 && selection.getFirstElement() instanceof Node);
    }

    public void run(IStructuredSelection selection)
    {
        if (selection.size() > 1 || selection.size() <= 0)
        {
            return;
        }
        String text = "";
        if (selection.getFirstElement() instanceof Node)
        {
            text = ((Node) selection.getFirstElement()).getSQLText();
        }
        else
        {
            return;
        }
        copyToClipboard(text, 0);
        // update the enablement of the paste action
        // workaround since the clipboard does not support callbacks
        if (_fPasteAction != null && _fPasteAction.getSelection() != null)
        _fPasteAction.update(_fPasteAction.getSelection());

    }

    private void copyToClipboard(String text, int repeatCount)
    {
        try
        {
            _fClipboard.setContents(new String[] 
            {
                text
            }
            , new Transfer[] 
            {
                TextTransfer.getInstance()
            }
            );
        }
        catch (SWTError e)
        {
            if (e.code != DND.ERROR_CANNOT_SET_CLIPBOARD || repeatCount >= _MAX_REPEAT_COUNT)
            throw e;

            if (MessageDialog
            .openQuestion(
                getShell(),
                Messages.CopyToClipboard_error_title, Messages.CopyToClipboard_error_message)) 
                copyToClipboard(text, repeatCount + 1);
        }
    }
}
