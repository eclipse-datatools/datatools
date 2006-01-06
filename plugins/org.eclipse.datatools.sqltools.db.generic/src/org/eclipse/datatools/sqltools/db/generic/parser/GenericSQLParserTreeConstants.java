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

public interface GenericSQLParserTreeConstants
{
  public int JJTVOID = 0;
  public int JJTSTART = 1;
  public int JJTSQLDELIMITER = 2;
  public int JJTSQLSTATEMENT = 3;
  public int JJTSQLDATATYPE = 4;
  public int JJTDECLAREKEYWORD = 5;
  public int JJTDECLARECOMMA = 6;
  public int JJTSQLPARAM = 7;
  public int JJTEXPRESSION = 8;


  public String[] jjtNodeName = {
    "void",
    "Start",
    "SQLDelimiter",
    "SQLStatement",
    "SQLDataType",
    "DeclareKeyword",
    "DeclareComma",
    "SQLParam",
    "Expression",
  };
}
