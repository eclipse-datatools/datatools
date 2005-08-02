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
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;


import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;

/** 
 * This class provides the hover (tooltip) support for the SQL editor.
 */
 public class SQLAnnotationHover implements IAnnotationHover {

	/**
     * Gets the text which should be presented in a hover popup window for
     * the given line number in the given source.
     * 
     * @see org.eclipse.jface.text.source.IAnnotationHover#getHoverInfo(org.eclipse.jface.text.source.ISourceViewer, int)
	 */
	public String getHoverInfo( ISourceViewer sourceViewer, int lineNumber ) {
		IDocument document = sourceViewer.getDocument();

		try {
			IRegion info = document.getLineInformation( lineNumber );
			return document.get( info.getOffset(), info.getLength() );
		} catch (BadLocationException x) {
		}

		return null;
	}
}
