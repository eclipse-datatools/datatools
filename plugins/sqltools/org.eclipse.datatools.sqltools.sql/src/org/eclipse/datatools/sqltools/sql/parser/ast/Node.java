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

import org.eclipse.datatools.sqltools.sql.parser.Token;
import org.eclipse.jface.text.IDocument;

/**
 * All AST nodes must implement this interface. It provides basic machinery for
 * constructing the parent and child relationships between nodes.
 * <p>
 * Note: some methods defined in the interface begin with "jjt", which is a
 * common convention of JavaCC, since we use it as the default parser generator.
 * </p>
 */

public interface Node {

	/**
	 * This method is called after the node has been made the current node. It
	 * indicates that child nodes can now be added to it.
	 */
	public void jjtOpen();

	/**
	 * This method is called after all the child nodes have been added.
	 */
	public void jjtClose();

	/**
	 * This pair of methods are used to inform the node of its parent.
	 */
	public void jjtSetParent(Node n);

	public Node jjtGetParent();

	/**
	 * This method tells the node to add its argument to the node's list of
	 * children.
	 */
	public void jjtAddChild(Node n, int i);

	/**
	 * This method returns a child node. The children are numbered from zero,
	 * left to right.
	 */
	public Node jjtGetChild(int i);

	/** Return the number of children the node has. */
	public int jjtGetNumChildren();

	public Token getLastToken();

	public void setLastToken(Token token);

	public Token getFirstToken();

	public void setFirstToken(Token token);

	/**
	 * 
	 * @param viewer
	 *            where the node is displayed
	 * @see getStartOffset()
	 */
	public int getStartOffset(IDocument document);

	/**
	 * 
	 * @param viewer
	 *            where the node is displayed
	 * @see getGreatestEndOffset
	 * @see getEndOffset()
	 */
	public int getEndOffset(IDocument document);

	/**
	 * this method differs with getEndOffset in that it takes the trailing
	 * spaces into account
	 * 
	 * @param viewer
	 *            where the node is displayed
	 * @see getGreatestEndOffset()
	 */
	public int getGreatestEndOffset(IDocument document);

	/**
	 * Associate this Node with the IDocument
	 * 
	 * @param input
	 */
	public void setDocument(IDocument document);

	public IDocument getDocument();

	/**
	 * Before you call this method, make sure setDocument has been called.
	 * 
	 * @return
	 */
	public int getStartOffset();

	/**
	 * Before you call this method, make sure setDocument has been called.
	 * 
	 * @return
	 * @see getGreatestEndOffset
	 */
	public int getEndOffset();

	/**
	 * this method differs with getEndOffset in that it takes the trailing
	 * spaces into account. Before you call this method, make sure setDocument
	 * has been called.
	 * 
	 * @return
	 */
	public int getGreatestEndOffset();

	/**
	 * Before you call this method, make sure setDocument has been called.
	 * 
	 * @return the text representation of this Node
	 */
	public String getSQLText();

	public int getNextTokenOffset();

	public int getNextTokenOffset(IDocument document);

	/**
	 * Gets previous node
	 * 
	 * @param document
	 * @return
	 */
	public Node getPreviousNode();

    /**
     * Accepts the visitor. 
	 * @param visitor the visitor to be accepted 
	 * @param data accessorial data
	 * @return accessorial data for subsequent visiting
     */
	public Object jjtAccept(ISQLParserVisitor visitor, Object data);

    public boolean exists();

}
