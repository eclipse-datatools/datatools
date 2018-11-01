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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IAbstractTextEditorHelpContextIds;


/**
 * @author Hui Cao
 *  
 */
public class CutAction extends SelectionDispatchAction
{

    private CopyToClipboardAction _fCopyToClipboardAction;

    /**
     *  
     */
    public CutAction(IWorkbenchSite site, Clipboard clipboard, SelectionDispatchAction pasteAction)
    {
        super(site);

        setText(Messages.CutAction_label); 
        setToolTipText(Messages.CutAction_tooltip); 

        _fCopyToClipboardAction = new CopyToClipboardAction(site, clipboard, pasteAction);

        ISharedImages workbenchImages = PlatformUI.getWorkbench().getSharedImages();
        setDisabledImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
        setImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));

        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IAbstractTextEditorHelpContextIds.CUT_ACTION);

        update(getSelection());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jdt.ui.actions.SelectionDispatchAction#selectionChanged(org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void selectionChanged(IStructuredSelection selection)
    {
        _fCopyToClipboardAction.selectionChanged(selection);
        setEnabled(_fCopyToClipboardAction.isEnabled());
    }



    public void run(IStructuredSelection selection)
    {
        selectionChanged(selection);
        if (isEnabled())
        {
            _fCopyToClipboardAction.run(selection);
            Object obj = selection.getFirstElement();
            if (obj instanceof Node)
            {
                Node node = (Node) obj;
                DeleteAction.removeNode(node);
            }
        }

    }

}
