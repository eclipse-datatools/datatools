/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.parser.ast;

import org.eclipse.jface.text.IDocument;

/**
 * Represents the root node of the parsing result. Contains
 * <code>IASTDeployable</code>, or <code>IASTSQLStatement</code>
 * 
 * @author Hui Cao
 * 
 */
public interface IASTStart extends Node
{
    /**
     * IASTStart will always be supposed to be the root node of a parsing result, and only here
     * the document information is really stored. Calling child nodes' setDocument or getDocument
     * will eventually goes to doSetDocument and doGetDocument.
     * @param input
     */
    public void doSetDocument(IDocument document);

    /**
     * @return
     */
    public IDocument doGetDocument();
}
