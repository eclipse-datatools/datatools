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


import org.eclipse.datatools.sqltools.sql.parser.ast.IASTDeclareComma;

public class ASTDeclareComma extends SimpleNode implements IASTDeclareComma{
  public ASTDeclareComma(int id) {
    super(id);
  }

  public ASTDeclareComma(IngresSQLParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(IngresSQLParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
