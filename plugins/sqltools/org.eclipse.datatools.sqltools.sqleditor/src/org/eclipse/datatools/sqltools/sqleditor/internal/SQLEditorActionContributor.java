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
package org.eclipse.datatools.sqltools.sqleditor.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionContributorExtension;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.SQLConnectAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.SQLDisconnectAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.editors.text.TextEditorActionContributor;
import org.eclipse.ui.part.EditorActionBarContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.RetargetTextEditorAction;

/**
 * This class installs and manages actions for the SQL Editor. 
 */
public class SQLEditorActionContributor extends TextEditorActionContributor {

    protected RetargetTextEditorAction fContentAssistProposalAction;
    protected RetargetTextEditorAction fContentAssistTipAction;
    protected RetargetTextEditorAction fContentFormatAction;
    private IPropertyChangeListener    fConnectActionListener;
    private Collection fExtensions = new ArrayList();
    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public SQLEditorActionContributor() {
        super();
        ResourceBundle bundle = SQLEditorResources.getResourceBundle();

        fContentAssistProposalAction = new RetargetTextEditorAction( bundle, "ContentAssistProposal." ); // $NON-NLS-1$
        fContentAssistTipAction =  new RetargetTextEditorAction( bundle, "ContentAssistTip." ); // $NON-NLS-1$
        fContentFormatAction = new RetargetTextEditorAction( bundle, "ContentFormat." ); // $NON-NLS-1$
        fExtensions = SQLEditorPlugin.getSQLEditorActionContributorExtension();
        for (Iterator iter = fExtensions.iterator(); iter.hasNext();) {
        	ISQLEditorActionContributorExtension ext = (ISQLEditorActionContributorExtension) iter.next();
        	ext.setParent(this);
		}
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
        for (Iterator iter = fExtensions.iterator(); iter.hasNext();) {
        	ISQLEditorActionContributorExtension ext = (ISQLEditorActionContributorExtension) iter.next();
        	ext.contributeToMenu(mm);
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
        if (targetEditor == null)
        {
            return;
        }
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
            
            for (Iterator iter = fExtensions.iterator(); iter.hasNext();) {
            	ISQLEditorActionContributorExtension ext = (ISQLEditorActionContributorExtension) iter.next();
            	ext.setActiveEditor(sqlEditor);
            }
        }
        
    }
    
    /**
     * Contributes to the given status line.
     * <p>
     * The <code>EditorActionBarContributor</code> implementation of this method
     * does nothing. Subclasses may reimplement to add to the status line portion of
     * this contribution.
     * </p>
     *
     * @param statusLineManager the manager of the status line
     */
    public void contributeToStatusLine(IStatusLineManager statusLineManager) {
    	super.contributeToStatusLine(statusLineManager);
    	for (Iterator iter = fExtensions.iterator(); iter.hasNext();) {
        	ISQLEditorActionContributorExtension ext = (ISQLEditorActionContributorExtension) iter.next();
        	ext.contributeToStatusLine(statusLineManager);
        }
    }

	public void contributeToCoolBar(ICoolBarManager coolBarManager) {
		super.contributeToCoolBar(coolBarManager);
		for (Iterator iter = fExtensions.iterator(); iter.hasNext();) {
        	ISQLEditorActionContributorExtension ext = (ISQLEditorActionContributorExtension) iter.next();
        	ext.contributeToCoolBar(coolBarManager);
        }
	}

	public void contributeToToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(new Separator(ITextEditorActionConstants.GROUP_UNDO));
        toolBarManager.add(new Separator(ISQLEditorActionConstants.GROUP_OPEN));
        toolBarManager.add(new Separator(ITextEditorActionConstants.GROUP_COPY));
        toolBarManager.add(new Separator(ISQLEditorActionConstants.GROUP_SQLEDITOR_SOURCE));
        toolBarManager.add(new Separator(ISQLEditorActionConstants.GROUP_SQLEDITOR_EXECUTE));
        toolBarManager.add(new Separator(ISQLEditorActionConstants.GROUP_SQLEDITOR_WIZARD));
        toolBarManager.add(new Separator(ISQLEditorActionConstants.GROUP_SQLEDITOR_SAVE));
        toolBarManager.add(new Separator(ISQLEditorActionConstants.GROUP_SQLEDITOR_ADDITION));
        toolBarManager.add(new Separator(ITextEditorActionConstants.MB_ADDITIONS));
		super.contributeToToolBar(toolBarManager);
		for (Iterator iter = fExtensions.iterator(); iter.hasNext();) {
        	ISQLEditorActionContributorExtension ext = (ISQLEditorActionContributorExtension) iter.next();
        	ext.contributeToToolBar(toolBarManager);
        }
	}

	public void init(IActionBars bars, IWorkbenchPage page) {
		super.init(bars, page);
		for (Iterator iter = fExtensions.iterator(); iter.hasNext();) {
        	ISQLEditorActionContributorExtension ext = (ISQLEditorActionContributorExtension) iter.next();
        	ext.init(bars, page);
        }
	}

	public void dispose() {
		super.dispose();
		for (Iterator iter = fExtensions.iterator(); iter.hasNext();) {
        	ISQLEditorActionContributorExtension ext = (ISQLEditorActionContributorExtension) iter.next();
        	ext.dispose();
        }
	}
	
    public void update(boolean isSQLEditorPage)
    {
        for (Iterator iter = fExtensions.iterator(); iter.hasNext();) {
            ISQLEditorActionContributorExtension ext = (ISQLEditorActionContributorExtension) iter.next();
            ext.updateAction(isSQLEditorPage);
        }
    }
    
} // end class
