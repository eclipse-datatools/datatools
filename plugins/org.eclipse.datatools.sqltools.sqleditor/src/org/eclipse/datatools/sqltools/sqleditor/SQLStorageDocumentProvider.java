/**
 * Created on 2005-2-17
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.ui.editors.text.StorageDocumentProvider;



/**
 * @author Hui Cao
 *  
 */
public class SQLStorageDocumentProvider extends StorageDocumentProvider 
{

    /**
     *  
     */
    public SQLStorageDocumentProvider()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#createDocument(java.lang.Object)
     */
    protected IDocument createDocument(Object element) throws CoreException
    {
        IDocument document;

        if (element instanceof SQLEditorStorageEditorInput)
        {
            SQLEditorStorage storage = (SQLEditorStorage)((SQLEditorStorageEditorInput)element).getStorage();
            document = new Document(storage.getContentsString());
        }
        else
        {
            // is FileEditorInput, delegate to super.
            document = super.createDocument(element);
        }
        //moved to SQLDocumentSetupParticipant
        //        if (document != null)
        //        {
        //            IDocumentPartitioner partitioner = new DefaultPartitioner(new SQLPartitionScanner(), new String[]
        //            {
        //                SQLPartitionScanner.SQL_CODE, SQLPartitionScanner.SQL_COMMENT,
        //                    SQLPartitionScanner.SQL_MULTILINE_COMMENT
        //            }
        //            );
        //            partitioner.connect(document);
        //            document.setDocumentPartitioner(partitioner);
        //        }
        return document;
    }

    /*
     * @see IDocumentProviderExtension#isModifiable(Object)
     */
    public boolean isModifiable(Object element)
    {
        return super.isModifiable(element);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#createAnnotationModel(java.lang.Object)
     */
    protected IAnnotationModel createAnnotationModel(Object element) throws CoreException
    {
        if (element instanceof SQLEditorStorageEditorInput)
        {
            return new SQLStorageAnnotationModel((SQLEditorStorage)((SQLEditorStorageEditorInput)element).getStorage() );
        }
        return super.createAnnotationModel(element);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#doSaveDocument(org.eclipse.core.runtime.IProgressMonitor,
     *      java.lang.Object, org.eclipse.jface.text.IDocument, boolean)
     */

    protected void doSaveDocument(IProgressMonitor monitor, Object element, IDocument document, boolean overwrite)
        throws CoreException
    {
        super.doSaveDocument(monitor, element, document, overwrite);
    }

}
