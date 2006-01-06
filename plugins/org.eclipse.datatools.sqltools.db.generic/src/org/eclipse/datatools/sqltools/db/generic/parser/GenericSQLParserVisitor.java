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

public interface GenericSQLParserVisitor
{
  public Object visit(SimpleNode node, Object data);
  public Object visit(ASTStart node, Object data);
  public Object visit(ASTSQLDelimiter node, Object data);
  public Object visit(ASTSQLStatement node, Object data);
  public Object visit(ASTSQLDataType node, Object data);
  public Object visit(ASTDeclareKeyword node, Object data);
  public Object visit(ASTDeclareComma node, Object data);
  public Object visit(ASTSQLParam node, Object data);
  public Object visit(ASTExpression node, Object data);
}
