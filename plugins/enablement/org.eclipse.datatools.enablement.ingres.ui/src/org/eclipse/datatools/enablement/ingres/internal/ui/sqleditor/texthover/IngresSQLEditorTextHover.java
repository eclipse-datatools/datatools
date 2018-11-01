/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.sqleditor.texthover;

import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLStatement;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.datatools.sqltools.sql.parser.ast.SimpleNode;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.sql.AbstractSQLEditorTextHover;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;

/**
 * Provides hovering information for a given sql document. Uses results from sql
 * parser, so the sql needs to be valid.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresSQLEditorTextHover extends AbstractSQLEditorTextHover {

	public static final String VENDOR_INGRES = "INGRES";

	private transient IEditorPart mEditor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.sqltools.sqleditor.sql.AbstractSQLEditorTextHover#setEditor(org.eclipse.ui.IEditorPart)
	 */
	public void setEditor(final IEditorPart editor) {
		mEditor = editor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.ITextHover#getHoverInfo(org.eclipse.jface.text.ITextViewer,
	 *      org.eclipse.jface.text.IRegion)
	 */
	public String getHoverInfo(final ITextViewer textViewer,
			final IRegion hoverRegion) {
		String result = null;
		if (canHandle()) {
			// get covering node of the given hoverRegion, finds the covering
			// statement node and translates the statement node to a help
			// text
			result = HoverInfoBuilder
					.getHoverInfoString(findStatement(findNode(hoverRegion
							.getOffset())));
		}

		return result;
	}

	/**
	 * Determines if the class can handle the given input. It is required that
	 * the editor is an SQLEditor and the vendor of the assigned connection info
	 * is "Ingres".
	 * 
	 * @return
	 */
	private boolean canHandle() {
		boolean result = false;

		if (mEditor instanceof SQLEditor) {
			ISQLEditorConnectionInfo connInfo = ((SQLEditor) mEditor)
					.getConnectionInfo();
			result = canHandle(connInfo);
		}

		return result;
	}

	/**
	 * Determines if the class can handle the given input. Checks that the
	 * vendor of the given connection info is "Ingres".
	 * 
	 * @param connInfo
	 * @return
	 */
	private boolean canHandle(final ISQLEditorConnectionInfo connInfo) {
		boolean result = false;
		if (connInfo != null) {
			DatabaseVendorDefinitionId vendorDefId = connInfo
					.getDatabaseVendorDefinitionId();
			result = canHandle(vendorDefId);
		}
		return result;
	}

	/**
	 * Determines if the class can handle the given input. Checks that the
	 * vendor of the given vendor id is "Ingres".
	 * 
	 * @param vendorDefId
	 * @return
	 */
	private boolean canHandle(final DatabaseVendorDefinitionId vendorDefId) {
		boolean result = false;
		if (vendorDefId != null
				&& VENDOR_INGRES.equalsIgnoreCase(vendorDefId.getProductName())) {
			result = true;
		}
		return result;
	}

	/**
	 * Searches and returns the node (from the parsing result) that covers the
	 * offset in the document of the editor.
	 * 
	 * @param offset
	 * @return
	 */
	private Node findNode(final int offset) {
		Node result = null;

		if (mEditor instanceof SQLEditor) {
			IDocument document = ((SQLEditor) mEditor).getDocumentProvider()
					.getDocument(mEditor.getEditorInput());
			if (((SQLEditor) mEditor).getParsingResult() != null) {
				Node rootNode = ((SQLEditor) mEditor).getParsingResult()
						.getRootNode();
				result = findNode(document, offset, rootNode, true);
			}
		}

		return result;
	}

	private Node findStatement(Node node) {
		if (node == null || node instanceof IASTSQLStatement) {
			return node;
		} else {
			return findStatement(node.jjtGetParent());
		}
	}

	// the following methods are copied from
	// org.eclipse.datatools.sqltools.sql.parser.ParsingResult
	// there was just a small change in contains(IDocument, int, Node, boolean)
	// necessary to enable recognize the containing nodes (the hovering failed
	// on the first keyword of a statement)

	public static Node findNode(final IDocument document, final int offset,
			final Node rootNode, final boolean inclusive) {
		if (!contains(document, offset, rootNode, inclusive)) {
			return null;
		}

		// now we are sure the root contains offset
		SimpleNode root = (SimpleNode) rootNode;
		for (int i = root.jjtGetNumChildren() - 1; i >= 0; i--) {
			SimpleNode node = (SimpleNode) root.jjtGetChild(i);
			if (contains(document, offset, node, inclusive)) {
				return findNode(document, offset, node, inclusive);
			}
		}
		return root;
	}

	private static boolean contains(final IDocument document, final int offset,
			final Node node, final boolean inclusive) {
		SimpleNode simpleNode = (SimpleNode) node;
		if (offset > document.getLength()) {
			return false;
		}

		// I assume that both offsets are zero based (@see
		// Token.getStartOffset(IDocument)), thats why the offset is part of the
		// region if it equals to the start offset
		// boolean in = simpleNode.getStartOffset(document) < offset ;
		boolean in = simpleNode.getStartOffset(document) <= offset;
		if (inclusive) {
			in = in && simpleNode.getGreatestEndOffset(document) >= offset;
		} else {
			in = in && simpleNode.getEndOffset(document) >= offset;
		}
		return in;

	}

}
