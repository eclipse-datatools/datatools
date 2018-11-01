/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.ui;

import org.eclipse.datatools.sqltools.common.ui.sqlstatementarea.ISQLSourceViewerService;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.ISQLPartitions;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLPartitionScanner;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.rules.FastPartitioner;

/**
 * This is a common implementation of <code>ISQLSourceViewerService </code> . In this implementation, the document is
 * divided into following partitions: <li>SQL code <li>SQL single line comment <li>SQL muli-line comment <li>SQL String
 * <li>SQL double quoted identifier
 * <p>
 * If user wants to support other partitions, user needs to subclass <code>ISQLSourceViewerService</code> directly and
 * implement the method: setUpDocument().
 * 
 * @author Shi-feng Yu
 */
public class SQLSourceViewerService implements ISQLSourceViewerService
{

    /*
     * (non-Javadoc)
     * 
     * @see testDDLPage.actions.ISQLSourceViewerService#setUpDocument(org.eclipse.jface.text.IDocument,
     * java.lang.String)
     */
    public void setUpDocument(IDocument document, String dbType)
    {
        SQLPartitionScanner _sqlPartitionSanner = new SQLPartitionScanner(SQLToolsFacade.getSQLSyntax(dbType));
        if (document instanceof IDocumentExtension3)
        {
            IDocumentExtension3 extension3 = (IDocumentExtension3) document;
            FastPartitioner _partitioner = new FastPartitioner(_sqlPartitionSanner, new String[]
            {
                SQLPartitionScanner.SQL_CODE, SQLPartitionScanner.SQL_COMMENT,
                SQLPartitionScanner.SQL_MULTILINE_COMMENT, SQLPartitionScanner.SQL_STRING,
                SQLPartitionScanner.SQL_DOUBLE_QUOTES_IDENTIFIER
            });
            _partitioner.connect(document);
            extension3.setDocumentPartitioner(ISQLPartitions.SQL_PARTITIONING, _partitioner);
        }
    }
}
