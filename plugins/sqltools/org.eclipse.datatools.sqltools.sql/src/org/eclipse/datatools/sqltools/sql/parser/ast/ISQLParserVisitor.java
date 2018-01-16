/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.parser.ast;

/**
 * Super interface for all visitors used to visit the AST nodes created by the
 * SQL parser after parsing.
 * 
 * @author Hui Cao
 * 
 */
public interface ISQLParserVisitor {
	
	/**
	 * Visits the node with the optional data information
	 * @param node the AST node to be visited 
	 * @param data accessorial data
	 * @return accessorial data for subsequent visiting
	 */
	public Object visit(SimpleNode node, Object data);

}
