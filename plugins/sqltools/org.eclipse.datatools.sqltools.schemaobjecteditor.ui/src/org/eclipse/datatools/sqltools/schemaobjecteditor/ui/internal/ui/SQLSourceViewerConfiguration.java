/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.ui;

import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.ISQLPartitions;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLCodeScanner;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLDoubleClickStrategy;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLPartitionScanner;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

/**
 * This is a common implementation of <code>SourceViewerConfiguration </code> for DMP. This configuraion is used for SQL
 * Statements preview which supports: <li>Syntax high lighting <li>Double click strategy
 * <p>
 * If user wants to support content assistant and autoEditStrategy, user needs to subclass this class with overridding
 * some motheds or subclass <code>SourceViewerConfiguration </code> directly.
 * 
 * @author Shifeng Yu
 * 
 */
public class SQLSourceViewerConfiguration extends SourceViewerConfiguration
{
    private ITokenScanner _SQLCodeScanner;
    private ITokenScanner _SQLStringScanner;
    private ITokenScanner _multilineCommentScanner;
    private ITokenScanner _singlelineCommentScanner;

    /**
     * The SQL identifier which is enclosed in double quotes scanner.
     */
    private ITokenScanner _SQLDoubleQuotesIdentifierScanner;

    private String        _dbType = null;

    /**
     * Instantiates a new SQLSourceViewerConfiguration with the parameter:database type.
     * 
     * @param dbType
     */
    public SQLSourceViewerConfiguration(String dbType)
    {
        this._dbType = dbType;
        initializeScanners();
    }

    /**
     * Initializes the scanners.
     * 
     */
    private void initializeScanners()
    {
        _SQLCodeScanner = new SQLCodeScanner(SQLEditorPlugin.getDefault().getSQLColorProvider());
        ((SQLCodeScanner) _SQLCodeScanner).setSQLSyntax(SQLToolsFacade.getSQLSyntax(this._dbType));
        _multilineCommentScanner = new SQLCommentScanner(ISQLPartitions.SQL_MULTILINE_COMMENT);
        _singlelineCommentScanner = new SQLCommentScanner(ISQLPartitions.SQL_COMMENT);
        _SQLDoubleQuotesIdentifierScanner = new SingleTokenSQLScanner(ISQLPartitions.SQL_DOUBLE_QUOTES_IDENTIFIER);
        _SQLStringScanner = new SingleTokenSQLScanner(ISQLPartitions.SQL_STRING);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.text.source.SourceViewerConfiguration#getPresentationReconciler(org.eclipse.jface.text.source
     * .ISourceViewer)
     */
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer)
    {
        PresentationReconciler reconciler = new PresentationReconciler();
        reconciler.setDocumentPartitioning(ISQLPartitions.SQL_PARTITIONING);

        // rule for default text
        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(this._SQLCodeScanner);
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

        // rule for multiline comments
        dr = new DefaultDamagerRepairer(this._multilineCommentScanner);
        reconciler.setDamager(dr, ISQLPartitions.SQL_MULTILINE_COMMENT);
        reconciler.setRepairer(dr, ISQLPartitions.SQL_MULTILINE_COMMENT);

        // rule for singleline comments
        dr = new DefaultDamagerRepairer(this._singlelineCommentScanner);
        reconciler.setDamager(dr, ISQLPartitions.SQL_COMMENT);
        reconciler.setRepairer(dr, ISQLPartitions.SQL_COMMENT);

        // rule for SQL Strings
        dr = new DefaultDamagerRepairer(this._SQLStringScanner);
        reconciler.setDamager(dr, ISQLPartitions.SQL_STRING);
        reconciler.setRepairer(dr, ISQLPartitions.SQL_STRING);

        // rule for double quoted identifiers
        dr = new DefaultDamagerRepairer(this._SQLDoubleQuotesIdentifierScanner);
        reconciler.setDamager(dr, ISQLPartitions.SQL_DOUBLE_QUOTES_IDENTIFIER);
        reconciler.setRepairer(dr, ISQLPartitions.SQL_DOUBLE_QUOTES_IDENTIFIER);

        dr = new DefaultDamagerRepairer(this._SQLCodeScanner);
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_CODE);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_CODE);
        return reconciler;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.text.source.SourceViewerConfiguration#getDoubleClickStrategy(org.eclipse.jface.text.source.
     * ISourceViewer, java.lang.String)
     */
    public ITextDoubleClickStrategy getDoubleClickStrategy(ISourceViewer sourceViewer, String contentType)
    {
        return new SQLDoubleClickStrategy();
    }
}
