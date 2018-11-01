/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views.source;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.ICompletionListener;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class SQLSourceViewerConfiguration extends SourceViewerConfiguration {

    private SQLCompletionProcessor fCompletionProcessor = null;
    private IContentAssistant fContentAssistant = null;
    private boolean fIsContentAssistActive = false;
    
    // [RATLC01118321] bgp 18Aug2006 - begin
    /*
     * Create a CompetionListener so that we will get notified when the completion popup is 
     * opening and closing.  We need to turn off query validation while the popup is open or
     * we get spurious parse error messages. 
     */
    private class ContentAssistantCompletionListener implements ICompletionListener {
        
    	public ContentAssistantCompletionListener() {} 
    	
    	public void assistSessionStarted(ContentAssistEvent event) { setIsContentAssistActive(true); }
    	
    	public void assistSessionEnded(ContentAssistEvent event) { setIsContentAssistActive(false); }
    	
    	public void selectionChanged(ICompletionProposal proposal, boolean smartToggle) {}
    }
    private ContentAssistantCompletionListener fContentAssistantCompletionListener;
    // [RATLC01118321] bgp 18Aug2006 - end

    /**
     * Constructor.
     */
    public SQLSourceViewerConfiguration() {
        fCompletionProcessor = new SQLCompletionProcessor();
        // [wsdbu00055322] bgp 04May2006
        fContentAssistant = createAndInitContentAssistant();
    }

    /**
     * 
     * @param context
     *            org.eclipse.datatools.sqltools.sqlbuilder.views.source.IDBContext
     */
    public void addDBContext(IDBContext context) {
        // Update the processor
        fCompletionProcessor.setDBContext(context);
    }

    // [wsdbu00055322] bgp 04May2006
    /**
     * Creates and initializes the content assistant to be used with with
     * source viewer.
     */
    protected IContentAssistant createAndInitContentAssistant() {
        ContentAssistant assistant = new ContentAssistant();
    
        assistant.setContentAssistProcessor(fCompletionProcessor, IDocument.DEFAULT_CONTENT_TYPE);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_SELECT);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_INSERT);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_UPDATE);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_DELETE);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_CREATE);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_DROP);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_ALTER);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_GRANT);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_REVOKE);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_COMMIT);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_ROLLBACK);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_SET);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_CONNECT);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_DISCONNECT);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_COMMENT_ST);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_TERMINATE);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_CATALOG);
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_UNCATALOG);
    
        // for unknown SQL commands
        assistant.setContentAssistProcessor(fCompletionProcessor, SQLPartitionScanner.SQL_UNKNOWNSQL);
    
        assistant.enableAutoActivation(true);
        assistant.setAutoActivationDelay(500);
        assistant.setProposalPopupOrientation(ContentAssistant.PROPOSAL_OVERLAY);
        assistant.setContextInformationPopupOrientation(ContentAssistant.CONTEXT_INFO_ABOVE);

        // [RATLC01118321] bgp 18Aug2006 - begin
        if (fContentAssistantCompletionListener == null) {
            fContentAssistantCompletionListener = new ContentAssistantCompletionListener();
        }
        assistant.addCompletionListener(fContentAssistantCompletionListener);
        // [RATLC01118321] bgp 18Aug2006 - end
        
        return assistant;
    }

    // [wsdbu00055322] bgp 04May2006
    /**
     * Gets the SQL Completion Processor (content assist proposal
     * processor) associated with this configuration.
     * 
     * @return the SQL Completion Processor object
     */
    public SQLCompletionProcessor getCompletionProcessor() {
        return fCompletionProcessor;
    }
    
    /**
     * 
     * @return java.lang.String[]
     * @param sourceViewer
     *            java.lang.Object
     */
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return new String[] { IDocument.DEFAULT_CONTENT_TYPE, SQLPartitionScanner.SQL_COMMENT, SQLPartitionScanner.SQL_SELECT, SQLPartitionScanner.SQL_INSERT,
                SQLPartitionScanner.SQL_UPDATE, SQLPartitionScanner.SQL_DELETE, SQLPartitionScanner.SQL_CREATE, SQLPartitionScanner.SQL_ALTER,
                SQLPartitionScanner.SQL_DROP, SQLPartitionScanner.SQL_GRANT, SQLPartitionScanner.SQL_REVOKE, SQLPartitionScanner.SQL_SET,
                SQLPartitionScanner.SQL_COMMIT, SQLPartitionScanner.SQL_ROLLBACK, SQLPartitionScanner.SQL_CONNECT, SQLPartitionScanner.SQL_DISCONNECT,
                SQLPartitionScanner.SQL_COMMENT_ST, SQLPartitionScanner.SQL_TERMINATE, SQLPartitionScanner.SQL_CATALOG, SQLPartitionScanner.SQL_UNCATALOG,

                SQLPartitionScanner.SQL_UNKNOWNSQL, };
    }

    // [wsdbu00055322] bgp 04May2006
    /**
     * 
     * @return org.eclipse.jface.text.contentassist.IContentAssist
     * @param sourcePart
     *            java.lang.Object
     */
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        return fContentAssistant;
    }

    /**
     * 
     * @return org.eclipse.jface.text.presentation.IPresentationReconciler
     * @param sourceViewer
     *            java.lang.Object
     */
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {

        //SQLColourProvider provider = SQLSourceEditingEnvironment.getSQLColourProvider();
        PresentationReconciler reconciler = new PresentationReconciler();

        // Default content
        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

        // SQL_COMMENT
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLCommentScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_COMMENT);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_COMMENT);

        // SQL_SELECT
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_SELECT);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_SELECT);

        // SQL_INSERT
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_INSERT);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_INSERT);

        // SQL_UPDATE
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_UPDATE);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_UPDATE);

        // SQL_DELETE
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_DELETE);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_DELETE);

        // SQL_CREATE
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_CREATE);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_CREATE);

        // SQL_DROP
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_DROP);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_DROP);

        // SQL_ALTER
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_ALTER);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_ALTER);

        // SQL_GRANT
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_GRANT);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_GRANT);

        // SQL_REVOKE
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_REVOKE);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_REVOKE);

        // SQL_COMMIT
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_COMMIT);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_COMMIT);

        // SQL_ROLLBACK
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_ROLLBACK);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_ROLLBACK);

        // SQL_SET
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_SET);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_SET);

        // SQL_CONNECT
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_CONNECT);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_CONNECT);

        // SQL_DISCONNECT
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_DISCONNECT);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_DISCONNECT);

        // SQL_COMMENTS_ST
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_COMMENT_ST);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_COMMENT_ST);

        // SQL_TERMINATE
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_TERMINATE);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_TERMINATE);

        // SQL_CATALOG
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_CATALOG);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_CATALOG);

        // SQL_UNCATALOG
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_UNCATALOG);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_UNCATALOG);

        // SQL_UNKNOWNSQL
        dr = new DefaultDamagerRepairer(SQLSourceEditingEnvironment.getSQLSourceScanner());
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_UNKNOWNSQL);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_UNKNOWNSQL);

        return reconciler;
    }
    
    // [RATLC01118321] bgp 18Aug2006
    /**
     * Gets whether or not content assist is active.  This is part of
     * a mechanism to handle focus lost events in the SQL source viewer.
     * We don't want the SQL source to be validated (parsed) when the user
     * triggers the content assistant (since the SQL is likely to be incomplete).
     * 
     * @return true when content assist is active, otherwise false
     */
    public boolean getIsContentAssistActive() {
        return fIsContentAssistActive;
    }

    // [RATLC01118321] bgp 18Aug2006
    /**
     * Sets whether or not content assist is active.  This is part of
     * a mechanism to handle focus lost events in the SQL source viewer.
     * We don't want the SQL source to be validated (parsed) when the user
     * triggers the content assistant (since the SQL is likely to be incomplete).
     * 
     * @param true when content assist is active, otherwise false
     */
    public void setIsContentAssistActive(boolean isContentAssistActive) {
        fIsContentAssistActive = isContentAssistActive;
    }

    /**
     *
     */
    public void removeDBContext() {
        // Update the completion processor
        fCompletionProcessor.setDBContext(null);
    }

}
