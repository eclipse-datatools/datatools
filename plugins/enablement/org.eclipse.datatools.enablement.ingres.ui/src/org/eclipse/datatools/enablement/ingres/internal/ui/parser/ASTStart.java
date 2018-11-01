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
package org.eclipse.datatools.enablement.ingres.internal.ui.parser;

import org.eclipse.datatools.sqltools.sql.parser.ast.IASTStart;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.ui.IEditorInput;

public class ASTStart extends SimpleNode implements IASTStart {

	private IEditorInput _input;

	private IDocument _document;

	private IAnnotationModel _model;

	public ASTStart(int id) {
		super(id);
	}

	public ASTStart(IngresSQLParser p, int id) {
		super(p, id);
	}

	/** Accept the visitor. * */
	public Object jjtAccept(IngresSQLParserVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.Node#setEditorInput(org.eclipse.ui.IEditorInput)
	 */
	public void doSetEditorInput(IEditorInput input) {
		_input = input;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.Node#getEditorInput()
	 */
	public IEditorInput doGetEditorInput() {
		return _input;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.IASTStart#doSetAnnotationModel(org.eclipse.jface.text.source.IAnnotationModel)
	 */
	public void doSetAnnotationModel(IAnnotationModel model) {
		_model = model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.IASTStart#doGetAnnotationModel()
	 */
	public IAnnotationModel doGetAnnotationModel() {
		return _model;
	}

}
