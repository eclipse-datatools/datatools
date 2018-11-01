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

package org.eclipse.datatools.sqltools.db.derby.parser;


import org.eclipse.datatools.sqltools.sql.parser.ast.IASTDeclareKeyword;

public class ASTDeclareKeyword extends SimpleNode implements IASTDeclareKeyword {
  public ASTDeclareKeyword(int id) {
    super(id);
  }

  public ASTDeclareKeyword(DerbySQLParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DerbySQLParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
