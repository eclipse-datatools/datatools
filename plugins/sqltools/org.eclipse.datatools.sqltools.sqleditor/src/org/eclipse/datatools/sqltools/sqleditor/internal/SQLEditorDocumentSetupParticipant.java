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

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLPartitionScanner;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.DefaultPartitioner;

/**
 * This class implements the <code>IDocumentSetupParticipant</code> interface in
 * order to setup document partitioning for SQL documents.
 */
public class SQLEditorDocumentSetupParticipant implements IDocumentSetupParticipant {
	
	/**
     * Sets up the document to be ready for use by a text file buffer.
     * 
	 * @see org.eclipse.core.filebuffers.IDocumentSetupParticipant#setup(org.eclipse.jface.text.IDocument)
	 */
	public void setup( IDocument document ) {
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 extension3 = (IDocumentExtension3) document;
			IDocumentPartitioner partitioner = new DefaultPartitioner( SQLEditorPlugin.getDefault().getSQLPartitionScanner(), SQLPartitionScanner.SQL_PARTITION_TYPES );
            partitioner.connect( document );
			extension3.setDocumentPartitioner( SQLPartitionScanner.SQL_PARTITIONING, partitioner );
		}
	}
}
