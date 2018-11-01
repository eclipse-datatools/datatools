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

import org.eclipse.datatools.sqltools.sql.parser.ast.IASTStart;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IAnnotationModel;

public class ASTStart extends SimpleNode implements IASTStart {

	private IDocument _document;

	private IAnnotationModel _model;

	public ASTStart(int id) {
		super(id);
	}

	public ASTStart(GenericSQLParser p, int id) {
		super(p, id);
	}

	/** Accept the visitor. * */
	public Object jjtAccept(GenericSQLParserVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.IASTStart#doSetDocument(org.eclipse.jface.text.IDocument)
	 */
	public void doSetDocument(IDocument document) {
		_document = document;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.IASTStart#doGetDocument()
	 */
	public IDocument doGetDocument() {
		return _document;
	}

}
