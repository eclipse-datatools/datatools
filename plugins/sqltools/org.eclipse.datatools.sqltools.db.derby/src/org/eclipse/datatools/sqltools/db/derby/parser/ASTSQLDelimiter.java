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

package org.eclipse.datatools.sqltools.db.derby.parser;


import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLDelimiter;

public class ASTSQLDelimiter extends SimpleNode implements IASTSQLDelimiter{
  public ASTSQLDelimiter(int id) {
    super(id);
  }

  public ASTSQLDelimiter(DerbySQLParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DerbySQLParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
