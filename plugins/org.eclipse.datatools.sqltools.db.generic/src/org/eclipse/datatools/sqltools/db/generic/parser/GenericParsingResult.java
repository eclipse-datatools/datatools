/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
    public GenericParsingResult(Node rootNode, ArrayList exceptions)
    {
        super(rootNode, exceptions);
    }


	protected ArrayList findCursorNames(IDocument document, int offset,
			SimpleNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	protected HashMap findVariables(IDocument document, int offset,
			SimpleNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	protected HashMap findCursors(IDocument document, int offset,
			SimpleNode node) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList getUnSharableTempTables(IDocument document, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

}
