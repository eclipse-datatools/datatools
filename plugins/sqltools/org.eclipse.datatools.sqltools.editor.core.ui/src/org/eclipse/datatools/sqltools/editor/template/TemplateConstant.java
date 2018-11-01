/***********************************************************************************************************************
 * Copyright (c) 2007 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.editor.template;

/**
 * 
 * @author lihuang
 */
public interface TemplateConstant
{
    public final static String SPACE             = " ";
    public final static String NEWLINE           = System.getProperty("line.separator");
    public final static String SELECT            = "SELECT";
    public final static String COMMA             = ",";
    public final static String ASTERISK          = "*";
    public final static String FROM              = "FROM";
    public final static String WHERE             = "WHERE";
    public final static String EQUAL             = "=";
    public final static String INSERT            = "INSERT";
    public final static String LEFT_PARENTHESIS  = "(";
    public final static String RIGHT_PARENTHESIS = ")";
    public final static String VALUES            = "VALUES";
    public final static String DECLARE           = "DECLARE";
    public final static String UPDATE            = "UPDATE";
    public final static String SET               = "SET";
    public final static String DELETE            = "DELETE";
    public final static String AND               = "AND";
    public final static String EXECUTE           = "EXECUTE";
    public final static String INT               = "INT";
    public final static String DOT               = ".";
    public final static String BEGIN             = "BEGIN";
    public final static String END               = "END";
    public final static String SEMICOLON         = ";";
    public final static String TAB               = "\t";
    public final static String OUTPUT            = "OUTPUT";
    public final static String VAR               = "@var";
    public final static String DOUBLE_QUOTE      = "\"";

    public final static int    NEWLINENUMBER     = 8;
    public final static int    UPDATELINENUMBER     = 4;
    
    public final static String INTELLIGENT_TEMPLATE = "intelligence.template";
}
