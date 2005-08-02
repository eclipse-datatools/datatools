/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal;

import java.util.ResourceBundle;

import org.eclipse.datatools.sqltools.sqleditor.internal.actions.SQLConnectAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.SQLDisconnectAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.SQLRunAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.SQLSetStatementTerminatorAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.SQLStatementTerminatorSupport;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.editors.text.TextEditorActionContributor;
import org.eclipse.ui.part.EditorActionBarContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.RetargetTextEditorAction;

/**
 * This class installs and manages actions for the SQL Editor. 
 */
public class SQLEditorActionContributor extends TextEditorActionContributor {

    protected RetargetTextEditorAction fContentAssistProposalAction;
    protected RetargetTextEditorAction fContentAssistTipAction;
    protected RetargetTextEditorAction fContentFormatAction;
    private IPropertyChangeListener    fConnectActionListener;
    private IPropertyChangeListener    fSetStatementTerminatorActionListener;

    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public SQLEditorActionContributor() {
        super();
        ResourceBundle bundle = SQLEditorResources.getResourceBundle();

        fContentAssistProposalAction = new RetargetTextEditorAction( bundle, "ContentAssistProposal." ); // $NON-NLS-1$
        fContentAssistTipAction =  new RetargetTextEditorAction( bundle, "ContentAssistTip." ); // $NON-NLS-1$
        fContentFormatAction = new RetargetTextEditorAction( bundle, "ContentFormat." ); // $NON-NLS-1$
    }

    /**
     * Contributes items to the Workbench Edit menu.
     * 
     * @param mm the MenuManager to use
     */
    public void contributeToMenu( IMenuManager mm ) {
        IMenuManager editMenu = mm.findMenuUsingPath( IWorkbenchActionConstants.M_EDIT );
        if (editMenu != null) {
            editMenu.add( new Separator() );
            editMenu.add( fContentAssistProposalAction );
            editMenu.add( fContentFormatAction );
            editMenu.add( fContentAssistTipAction );
        }
    }
    
    /**
     * Sets the active editor to this contributor.
     * This updates the actions to reflect the current editor.
     * 
     * @see org.eclipse.ui.part.EditorActionBarContributor#setActiveEditor(org.eclipse.ui.IEditorPart)
     * @see EditorActionBarContributor#editorChanged
     */
    public void setActiveEditor( IEditorPart targetEditor ) {
        super.setActiveEditor( targetEditor );

        ITextEditor textEditor = null;
        if (targetEditor instanceof ITextEditor) {
            textEditor = (ITextEditor) targetEditor;
        }
        
        // Set up the standard text editor actions.  These actions each have an
        // "retargetable action" associated with them.  The action needs to be
        // "retargeted" to associate it with the active editor whenever the active
        // editor changes.
        fContentAssistProposalAction.setAction( getAction( textEditor, "ContentAssistProposal" )); // $NON-NLS-1$
        fContentAssistTipAction.setAction( getAction( textEditor, "ContentAssistTip" )); // $NON-NLS-1$
        fContentFormatAction.setAction( getAction( textEditor, "ContentFormat" )); // $NON-NLS-1$

        // Get and set up the other actions associated with this editor.
        SQLConnectAction connectAction = (SQLConnectAction) getAction( textEditor, "SQLEditor.connectAction" ); // $NON-NLS-1$
        SQLDisconnectAction disconnectAction = (SQLDisconnectAction) getAction( textEditor, "SQLEditor.disconnectAction" ); // $NON-NLS-1$
        SQLRunAction runAction = (SQLRunAction) getAction( textEditor, "SQLEditor.runAction" ); // $NON-NLS-1$
        SQLSetStatementTerminatorAction setStatementTerminatorAction = 
            (SQLSetStatementTerminatorAction) getAction( textEditor, "SQLEditor.setStatementTerminatorAction" ); // $NON-NLS-1$

        if (targetEditor instanceof SQLEditor) {
            SQLEditor sqlEditor = (SQLEditor) targetEditor;
            
            // Set up the Connect action.
            if (connectAction != null) {
                connectAction.setSQLEditor( sqlEditor );
            
                // Set the SQL editor as a listener on the connect action so that it
                // will be informed if the connection changes.  First remove any
                // existing listener.
                if (fConnectActionListener != null) {
                    connectAction.removePropertyChangeListener( fConnectActionListener ); 
                }
                fConnectActionListener = sqlEditor; 
                connectAction.addPropertyChangeListener( fConnectActionListener );
            }
            
            // Set up the Disconnect action.
            if (disconnectAction != null) {
                disconnectAction.setSQLEditor( sqlEditor );
            
                // Set the SQL editor as a listener on the disconnect action so that it
                // will be informed if the connection changes.  First remove any
                // existing listener.
                if (fConnectActionListener != null) {
                    disconnectAction.removePropertyChangeListener( fConnectActionListener ); 
                }
                fConnectActionListener = sqlEditor; 
                disconnectAction.addPropertyChangeListener( fConnectActionListener );
            }            
            
            // Set up the Run action.
            if (runAction != null) {
                runAction.setSQLEditor( sqlEditor );
            }
            
            // Set up the Set Statement Terminator action.
            if (setStatementTerminatorAction != null) {
                setStatementTerminatorAction.setSQLEditor( sqlEditor );
                
                // Set up the SQL editor as a listener on the set statement 
                // terminator action so that it will be informed when the 
                // statement terminator changes.  First remove any existing
                // listener.
                if (fSetStatementTerminatorActionListener != null) {
                    setStatementTerminatorAction.removePropertyChangeListener( fSetStatementTerminatorActionListener ); 
                }
                SQLStatementTerminatorSupport statementTerminatorSupport = sqlEditor.getSQLStatementTerminatorSupport();
                fSetStatementTerminatorActionListener = statementTerminatorSupport;
                setStatementTerminatorAction.addPropertyChangeListener( fSetStatementTerminatorActionListener );
            }
        }
    }
    
} // end class
