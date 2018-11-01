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

import org.eclipse.datatools.sqltools.sql.parser.ast.IASTDeclareComma;

public class ASTDeclareComma extends SimpleNode implements IASTDeclareComma{
  public ASTDeclareComma(int id) {
    super(id);
  }

  public ASTDeclareComma(GenericSQLParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(GenericSQLParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
