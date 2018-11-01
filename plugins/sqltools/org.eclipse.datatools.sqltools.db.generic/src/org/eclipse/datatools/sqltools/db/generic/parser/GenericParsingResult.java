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
package org.eclipse.datatools.sqltools.db.generic.parser;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.datatools.sqltools.sql.parser.ast.SimpleNode;
import org.eclipse.jface.text.IDocument;

/**
 * @author Hui Cao
 * 
 */
public class GenericParsingResult extends ParsingResult {
	/**
	 * @param rootNode
	 * @param exceptions
	 */
	public GenericParsingResult()
	{
	}
	
    /**
     * @param rootNode
     * @param exceptions
     */
    public GenericParsingResult(Node rootNode, ArrayList exceptions)
    {
        super(rootNode, exceptions);
    }


	protected ArrayList findCursorNames(IDocument document, int offset,
			SimpleNode node) {
		return new ArrayList();
	}

	protected HashMap findVariables(IDocument document, int offset,
			SimpleNode node) {
		return new HashMap();
	}

	protected HashMap findCursors(IDocument document, int offset,
			SimpleNode node) {
		return new HashMap();
	}

	public boolean isValidObjectsFormats(ArrayList tokenLists) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isVarOrPara(ArrayList tokenLists) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList getPrefix(ArrayList tokenLists, Node node) {
		return new ArrayList();
	}

	public ArrayList getUnSharableTempTables(IDocument document, int offset) {
		return new ArrayList();
	}

}
