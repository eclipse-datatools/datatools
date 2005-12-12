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
package org.eclipse.datatools.sqltools.sqleditor.internal.editor;

import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.BestMatchHover;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.ISQLDBProposalsService;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLAnnotationHover;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLAutoIndentStrategy;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLCompletionProcessor;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLDoubleClickStrategy;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLPartitionScanner;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLUpperCaseFormattingStrategy;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.SQLColorProvider;
import org.eclipse.jface.text.DefaultAutoIndentStrategy;
import org.eclipse.jface.text.IAutoIndentStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.formatter.ContentFormatter;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.IFormattingStrategy;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.graphics.RGB;

/**
 * This class defines the editor add-ons; content assist, content formatter,
 * highlighting, auto-indent strategy, double click strategy. 
 */
public class SQLSourceViewerConfiguration extends SourceViewerConfiguration {

    /** The editor with which this configuration is associated. */
    private SQLEditor fEditor;
    /** The completion processor for completing the user's typing. */
    private SQLCompletionProcessor fCompletionProcessor;
    /** The content assist proposal service for database objects. */
    private ISQLDBProposalsService fDBProposalsService;
    
    /**
     * This class implements a single token scanner.
     */
    static class SingleTokenScanner extends BufferedRuleBasedScanner {
        public SingleTokenScanner( TextAttribute attribute ) {
            setDefaultReturnToken( new Token( attribute ));
        }
    }

    /**
     * Constructs an instance of this class with the given SQLEditor to
     * configure.
     * 
     * @param editor the SQLEditor to configure
     */
    public SQLSourceViewerConfiguration( SQLEditor editor ) {
        fEditor = editor;
        fCompletionProcessor = new SQLCompletionProcessor(); 
    }
    
    /**
     * Returns the annotation hover which will provide the information to be
     * shown in a hover popup window when requested for the given
     * source viewer.
     * 
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getAnnotationHover(org.eclipse.jface.text.source.ISourceViewer)
     */
    public IAnnotationHover getAnnotationHover( ISourceViewer sourceViewer ) {
        return new SQLAnnotationHover(getSQLEditor());
    }
    
    /**
     * Returns the auto indentation strategy ready to be used with the given source viewer
     * when manipulating text of the given content type.
     * 
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getAutoIndentStrategy(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
     */
    public IAutoIndentStrategy getAutoIndentStrategy( ISourceViewer sourceViewer, String contentType ) {
        return (IDocument.DEFAULT_CONTENT_TYPE.equals( contentType ) ? new SQLAutoIndentStrategy() : new DefaultAutoIndentStrategy());
    }
    
    /**
     * Returns the configured partitioning for the given source viewer. The partitioning is
     * used when the querying content types from the source viewer's input document.
     * 
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getConfiguredDocumentPartitioning(org.eclipse.jface.text.source.ISourceViewer)
     */
    public String getConfiguredDocumentPartitioning( ISourceViewer sourceViewer ) {
        return SQLEditorPlugin.SQL_PARTITIONING;
    }

    /**
     * Creates, initializes, and returns the ContentAssistant to use with this editor.
     * 
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getContentAssistant(ISourceViewer)
     */
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        ContentAssistant assistant = new ContentAssistant();
    
        assistant.setDocumentPartitioning( getConfiguredDocumentPartitioning( sourceViewer ));
        
        // Set content assist processors for various content types.
        fCompletionProcessor = new SQLCompletionProcessor();
        assistant.setContentAssistProcessor( fCompletionProcessor, IDocument.DEFAULT_CONTENT_TYPE);
        ISQLDBProposalsService proposalsService = getDBProposalsService();
        if (proposalsService != null) {
            fCompletionProcessor.setDBProposalsService( proposalsService );
        }
    
        // Configure how content assist information will appear.
        assistant.enableAutoActivation( true );
        assistant.setAutoActivationDelay( 500 );
        assistant.setProposalPopupOrientation( IContentAssistant.PROPOSAL_STACKED );
        assistant.setContextInformationPopupOrientation( IContentAssistant.CONTEXT_INFO_ABOVE );
        assistant.setContextInformationPopupBackground( SQLEditorPlugin.getDefault().getSQLColorProvider().getColor( new RGB( 150, 150, 0 )));
        //Set to Carolina blue
//        assistant.setContextInformationPopupBackground( SQLEditorPlugin.getDefault().getSQLColorProvider().getColor( new RGB( 0, 191, 255 )));
        
        return assistant;
    }

    /**
     * Creates, configures, and returns the ContentFormatter to use.
     * 
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getContentFormatter(ISourceViewer)
     */
    public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
        ContentFormatter formatter = new ContentFormatter();
        formatter.setDocumentPartitioning( SQLEditorPlugin.SQL_PARTITIONING );
        
        IFormattingStrategy formattingStrategy = new SQLUpperCaseFormattingStrategy();
        formatter.setFormattingStrategy( formattingStrategy, IDocument.DEFAULT_CONTENT_TYPE );
        
        return formatter;
    }

    /**
     * Gets the <code>DBProposalsService</code> object that provides content
     * assist services for this editor.
     * 
     * @return the current <code>DBProposalsService</code> object
     */
    public ISQLDBProposalsService getDBProposalsService() {
        return fDBProposalsService;
    }
    
    /**
     * Returns the double-click strategy ready to be used in this viewer when double clicking
     * onto text of the given content type.  (Note: the same double-click strategy
     * object is used for all content types.)
     * 
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getDoubleClickStrategy(ISourceViewer, String)
     */
    public ITextDoubleClickStrategy getDoubleClickStrategy( ISourceViewer sourceViewer, String contentType) {
        return new SQLDoubleClickStrategy();
    }

    /**
     * Creates, configures, and returns a presentation reconciler to help with 
     * document changes.
     * 
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getPresentationReconciler(ISourceViewer)
     */
    public IPresentationReconciler getPresentationReconciler( ISourceViewer sourceViewer ) {

        // Get the color provider.
        SQLColorProvider colorProvider = getSQLEditor().getSQLColorProvider();
        
        // Create a presentation reconciler to handle handle document changes.
        PresentationReconciler reconciler = new PresentationReconciler();
        String docPartitioning = getConfiguredDocumentPartitioning( sourceViewer );
        reconciler.setDocumentPartitioning( docPartitioning );

        // Add a "damager-repairer" for changes in default text (SQL code).
        DefaultDamagerRepairer dr = new DefaultDamagerRepairer( getSQLEditor().getSQLCodeScanner() );
        reconciler.setDamager( dr, IDocument.DEFAULT_CONTENT_TYPE );
        reconciler.setRepairer( dr, IDocument.DEFAULT_CONTENT_TYPE );
        
        // Add a "damager-repairer" for changes witin one-line SQL comments.
        dr = new DefaultDamagerRepairer( new SingleTokenScanner( new TextAttribute( colorProvider.getColor( SQLColorProvider.SQL_COMMENT_COLOR ))));
        reconciler.setDamager( dr, SQLPartitionScanner.SQL_COMMENT );
        reconciler.setRepairer( dr, SQLPartitionScanner.SQL_COMMENT );

        // Add a "damager-repairer" for changes witin quoted literals.
        dr = new DefaultDamagerRepairer( new SingleTokenScanner( new TextAttribute( colorProvider.getColor( SQLColorProvider.SQL_QUOTED_LITERAL_COLOR ))));
        reconciler.setDamager( dr, SQLPartitionScanner.SQL_QUOTED_LITERAL );
        reconciler.setRepairer( dr, SQLPartitionScanner.SQL_QUOTED_LITERAL );

        // Add a "damager-repairer" for changes witin delimited identifiers.
        dr = new DefaultDamagerRepairer( new SingleTokenScanner( new TextAttribute( colorProvider.getColor( SQLColorProvider.SQL_DELIMITED_IDENTIFIER_COLOR ))));
        reconciler.setDamager( dr, SQLPartitionScanner.SQL_DELIMITED_IDENTIFIER );
        reconciler.setRepairer( dr, SQLPartitionScanner.SQL_DELIMITED_IDENTIFIER );

        return reconciler;
    }

    /**
     * Returns the SQLEditor associated with this object.
     * 
     * @return the SQLEditor that this object configures
     */
    public SQLEditor getSQLEditor() {
        return fEditor;
    }
    
    /**
     * Returns the text hover which will provide the information to be shown
     * in a text hover popup window when requested for the given source viewer and
     * the given content type.
     * 
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getTextHover(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
     */
    public ITextHover getTextHover( ISourceViewer sourceViewer, String contentType ) {
        return new BestMatchHover(this.getSQLEditor());
    }

    /**
     * Sets the <code>ISQLDBProposalsService</code> object that provides content
     * assist services for this viewer to the given object.
     * 
     * @param the <code>ISQLDBProposalsService</code> object to set
     */
    public void setDBProposalsService( ISQLDBProposalsService dbProposalsService ) {
        fDBProposalsService = dbProposalsService;
        if (fCompletionProcessor != null) {
            fCompletionProcessor.setDBProposalsService( dbProposalsService );
        }
    }
    
} // end class
