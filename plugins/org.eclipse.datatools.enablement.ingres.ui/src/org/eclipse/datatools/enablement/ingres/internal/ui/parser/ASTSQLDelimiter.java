/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.parser;


import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLDelimiter;

public class ASTSQLDelimiter extends SimpleNode implements IASTSQLDelimiter{
  public ASTSQLDelimiter(int id) {
    super(id);
  }

  public ASTSQLDelimiter(IngresSQLParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(IngresSQLParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
