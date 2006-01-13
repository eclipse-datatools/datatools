/**
 * Created on 2004-10-25
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import org.eclipse.datatools.sqltools.sql.parser.Token;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTDeclareKeyword;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParam;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorPlugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IAbstractTextEditorHelpContextIds;

/**
 * @author Hui Cao
 *  
 */
public class PasteAction extends SelectionDispatchAction
{
    private final Clipboard _fClipboard;

    /**
     *  
     */
    public PasteAction(IWorkbenchSite site, Clipboard clipboard)
    {
        super(site);

        setText(Messages.getString("PasteAction.label")); //$NON-NLS-1$
        setToolTipText(Messages.getString("PasteAction.tooltip")); //$NON-NLS-1$

        _fClipboard = clipboard;

        ISharedImages workbenchImages = PlatformUI.getWorkbench().getSharedImages();
        setDisabledImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
        setImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));

        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IAbstractTextEditorHelpContextIds.PASTE_ACTION);

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
        if (selection.size() != 1 || !(selection.getFirstElement() instanceof Node))
        {
            return false;
        }

        TransferData[] availableDataTypes = _fClipboard.getAvailableTypes();

        for (int i = 0; i < availableDataTypes.length; i++)
        {
            if (TextTransfer.getInstance().isSupportedType(availableDataTypes[i]))
            {
                return true;
            }
        }
        return false;
    }

    public void run(IStructuredSelection selection)
    {
        if (selection.size() > 1 || selection.size() <= 0)
        {
            return;
        }

        StringBuffer text = new StringBuffer((String) getContents(_fClipboard, TextTransfer.getInstance(), getShell()));
        if (selection.getFirstElement() instanceof Node)
        {

            Node node = (Node) selection.getFirstElement();
            //if the selected text is variable definition
            if (node instanceof IASTSQLParam)
            {
                text = pasteText(node, text);
            }
            else
            {
                text.insert(0, System.getProperty("line.separator"));
            }
            int offset = node.getEndOffset();

            //if the next token is ";" for watcom sql of asa
            if (node.getLastToken().next.image.equals(";"))
            {
                offset = node.getNextTokenOffset();
                text.append(";");
            }

            IDocument doc = node.getDocument();
            try
            {
                doc.replace(offset, 0, text.toString());
            }
            catch (BadLocationException e)
            {
                SQLEditorPlugin.getDefault().log(Messages.getString("PasteAction.1"), e); //$NON-NLS-1$
            }
        }

    }

    /**
     * add the required text for variable definition
     * 
     * @param node
     * @param sqlText
     * @return
     */
    private StringBuffer pasteText(Node node, StringBuffer sqlText)
    {
        StringBuffer text = sqlText;
        //if the next token is ",", add "," before text.
        Token nextToken = node.getLastToken().next;
        if ((node.getPreviousNode() instanceof IASTDeclareKeyword) && !nextToken.image.equals(","))
        {

            text.insert(0, "DECLARE ");
            text.insert(0, System.getProperty("line.separator"));
            //if the next token is ";" for watcom sql of asa
            if (nextToken.image.equals(";"))
            {
                text.append(";");
            }
        }
        else
        {
            text.insert(0, ", ");
        }
        return text;
    }

    private static Object getContents(final Clipboard clipboard, final Transfer transfer, Shell shell)
    {
        //see bug 33028 for explanation why we need this
        final Object[] result = new Object[1];
        shell.getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                result[0] = clipboard.getContents(transfer);
            }
        }
        );
        return result[0];
    }

}
