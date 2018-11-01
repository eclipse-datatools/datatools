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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.DefaultPartitioner;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.ui.texteditor.AbstractDocumentProvider;

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;

public class SQLEditorDocumentProvider extends AbstractDocumentProvider
{

  private final static String[] TYPES= new String[] {
      SQLPartitionScanner.SQL_COMMENT,
      // DML
      SQLPartitionScanner.SQL_SELECT,
      SQLPartitionScanner.SQL_INSERT,
      SQLPartitionScanner.SQL_UPDATE,
      SQLPartitionScanner.SQL_DELETE,
      // DDL
      SQLPartitionScanner.SQL_CREATE,
      SQLPartitionScanner.SQL_DROP,
      SQLPartitionScanner.SQL_ALTER,
      // PRIVILEGES
      SQLPartitionScanner.SQL_GRANT,
      SQLPartitionScanner.SQL_REVOKE,
      // TRANSACTIONS
      SQLPartitionScanner.SQL_COMMIT,
      SQLPartitionScanner.SQL_ROLLBACK,
      SQLPartitionScanner.SQL_SET    
    };

  protected IDocument createDocument(Object element) throws CoreException
  {
    Document document = new Document(inputToStringHelper(element));
    IDocumentPartitioner partitioner = createDocumentPartitioner();

    if (document != null)
    {
      partitioner.connect(document);
      document.setDocumentPartitioner(partitioner);
    }
    return document;
  }
  
  // RATLC01136221 bbn 10Jan2007 - new method
  public static IDocument createDocument(String element) throws CoreException
  {
    Document document = new Document(element);
    IDocumentPartitioner partitioner = createDocumentPartitioner();

    if (document != null)
    {
      partitioner.connect(document);
      document.setDocumentPartitioner(partitioner);
    }
    return document;
  }

  /**
   * Create a document partitioner
   */
  public static IDocumentPartitioner createDocumentPartitioner() 
  {
    SQLPartitionScanner partitionScanner = new SQLPartitionScanner();

    return new DefaultPartitioner(partitionScanner, TYPES);
  }

  public String inputToStringHelper(Object object)
  {
    if (object instanceof QueryStatement)
      return ((QueryStatement)object).getSQL();
    else if (object instanceof SQLQueryObject )
      return ((SQLQueryObject)object).getSQL();
    else
      return ""; //$NON-NLS-1$
  }

  // to implement AbstractDocumentProvider
  protected void doSaveDocument(IProgressMonitor monitor,
                                java.lang.Object element,
                                IDocument document,
                                boolean overwrite)
    throws CoreException
  {

  }

  public long getModificationStamp(java.lang.Object element)
  {
    return 0;  
  }

  public long getSynchronizationStamp(java.lang.Object element)
  {
    return 0;
  }

  public boolean isDeleted(java.lang.Object element)
  {
    return false;
  }

  // to implement AbstractDocumentProvider
  protected IAnnotationModel createAnnotationModel(java.lang.Object element)
                                                   throws CoreException  
  {
    return null;
  }
  
  protected IRunnableContext getOperationRunner(IProgressMonitor monitor)
  {
      return null;
  }
}

