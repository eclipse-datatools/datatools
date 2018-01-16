/**
 * Created on 2005-10-28
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.indent;

import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;


/**
 * Auto indent strategy for SQL strings
 * 
 */
public class SQLStringAutoIndentStrategy extends DefaultIndentLineAutoEditStrategy
{

    /**
     * Creates a new SQL string auto indent strategy for the given document partitioning.
     * 
     * @param partitioning the document partitioning
     */
    public SQLStringAutoIndentStrategy(String partitioning)
    {
        super();
    }

    /*
     * @see org.eclipse.jface.text.IAutoIndentStrategy#customizeDocumentCommand(IDocument, DocumentCommand)
     */
    public void customizeDocumentCommand(IDocument document, DocumentCommand command)
    {

    }
}
