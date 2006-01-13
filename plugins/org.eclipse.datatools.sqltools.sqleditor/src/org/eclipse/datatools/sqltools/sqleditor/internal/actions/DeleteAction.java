/**
 * Created on 2004-10-25
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import java.util.List;

import org.eclipse.datatools.sqltools.sql.parser.Token;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTDeclareComma;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTDeclareKeyword;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParam;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorPlugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IAbstractTextEditorHelpContextIds;

/**
 * @author Hui Cao
 *  
 */
public class DeleteAction extends SelectionDispatchAction
{

    /**
     *  
     */
    public DeleteAction(IWorkbenchSite site)
    {
        super(site);

        setText(Messages.getString("DeleteAction.label")); //$NON-NLS-1$
        setToolTipText(Messages.getString("DeleteAction.tooltip")); //$NON-NLS-1$

        ISharedImages workbenchImages = PlatformUI.getWorkbench().getSharedImages();
        setDisabledImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
        setImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));

        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IAbstractTextEditorHelpContextIds.DELETE_ACTION);

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

        return (selection.size() == 1);
    }

    /**
     * This method is used for removing declare statement and variable definition statement.
     * 
     * @param currentNode
     * @param document
     */
    private static void removeSQLParam(Node currentNode)
    {
        //get the previous node
        IDocument document = currentNode.getDocument();
        Node pnode = currentNode.getPreviousNode();

        //The next token after the current token. This token is used for declare statement.
        Token nextToken = currentNode.getLastToken().next;
        int offset = 0;
        int length = 0;
        String text = ""; 

        // declare a int, b int, c int;
        if (nextToken.image.equals(",")) 
        {
            offset = currentNode.getStartOffset();
            length = currentNode.getNextTokenOffset() - currentNode.getStartOffset();
        }
        else
        {
            //get offset
            if ((pnode instanceof IASTDeclareComma) || (pnode instanceof IASTDeclareKeyword))
            {
                offset = pnode.getStartOffset();
            }
            else
            {
                offset = currentNode.getStartOffset();
            }
            //get length
            if (nextToken.image.equals(";")) 
            {
                length = currentNode.getNextTokenOffset() - offset;
            }
            else
            {
                length = currentNode.getEndOffset() - offset;
            }
        }
        try
        {
            document.replace(offset, length, text);
        }
        catch (BadLocationException e)
        {
        	SQLEditorPlugin.getDefault().log( Messages.getString("DeleteAction.0"), e); //$NON-NLS-1$
        }
    }

    public static void removeNode(Node node)
    {
        //the current node
        IDocument document = node.getDocument();
        try
        {
            //judge declare, variable definition statement
            if (node instanceof IASTSQLParam)
            {
                removeSQLParam(node);
            }
            else
            {
                // except declare statement
                if (node.getLastToken().next.image.equals(";")) 
                {
                    document.replace(node.getStartOffset(), node.getNextTokenOffset() - node.getStartOffset(), ""); 
                }
                else
                {
                    document.replace(node.getStartOffset(), node.getEndOffset() - node.getStartOffset(), ""); 
                }
            }
        }
        catch (BadLocationException e)
        {
        	SQLEditorPlugin.getDefault().log( Messages.getString("DeleteAction.0"), e); //$NON-NLS-1$
        }

    }

    public void run(IStructuredSelection selection)
    {
        if (selection.size() <= 0 || selection.size() > 1) 
        {
            return; 
        }
        List nodes = selection.toList();
        for (int i = 0; i < nodes.size(); i++)
        {
            Object obj = nodes.get(i);
            if (obj instanceof Node)
            {
                Node node = (Node) obj;
                if ( confirm() == SWT.YES)
                {
                    removeNode(node);
                }
            }
            else
            {
                continue;
            }
        }
    }

    /**
     * Confirm if the use want to delete.
     * @return
     */
    private int confirm()
    {
        MessageBox messageBox = new MessageBox(getShell(), SWT.YES | SWT.NO |SWT.ICON_QUESTION);
        messageBox.setText(Messages.getString("DeleteAction.confirm.title"));  //$NON-NLS-1$
        messageBox.setMessage(Messages.getString("DeleteAction.confirm.message") );  //$NON-NLS-1$
        return messageBox.open();
    }
}

