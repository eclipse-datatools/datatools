/**
 * Created on Jun 10, 2006
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.sqlstatementarea;

import org.eclipse.jface.text.IDocument;


/**
 * This interface defines some help methods which are needed by <code>SQLStatementArea</code>.
 * The consumer of <code>SQLStatementArea</code> must create an instance implementing this interface
 * and pass it in when creating instance of <code>SQLStatementArea</code>.
 * <p>
 * @author Shi-feng Yu
 */
public interface ISQLSourceViewerService
{
    /**
     * This method is used for user to customize the document setting.
     * <p>
     * The document used by <code>SQLStatementArea</code> needs a PartitionSanner and Partitioner to 
     * compute the partitions. Through this method, user can set up customized PartitionSanner with
     * customized rules.
     * <p>
     * Sample code for setting up a document
     * <pre>
     * XXXPartitionScanner _xxxPartitionSanner = new XXXPartitionScanner();
     *   _xxxPartitionSanner.setCommentsScanner(SQLToolsFacade.getSQLSyntax(dbType));
     *   if (document instanceof IDocumentExtension3)
     *   {
     *       IDocumentExtension3 extension3 = (IDocumentExtension3) document;
     *       FastPartitioner _partitioner = new FastPartitioner(_sqlPartitionSanner, new String[]
     *       {
     *           XXXPartitionScanner.SQL_CODE, XXXPartitionScanner.SQL_COMMENT,
     *           XXXPartitionScanner.SQL_MULTILINE_COMMENT, XXXPartitionScanner.SQL_STRING,
     *           XXXPartitionScanner.SQL_DOUBLE_QUOTES_IDENTIFIER
     *       });
     *       _partitioner.connect(document);
     *       extension3.setDocumentPartitioner(ISQLPartitions.SQL_PARTITIONING, _partitioner);
     *   }
     * </pre>
     * @param doc  the Document to be set up
     * @param dbType  the Database type of the SQL Statement which is contained in the Document.
     */
    public void setUpDocument(IDocument doc, String dbType);
}
