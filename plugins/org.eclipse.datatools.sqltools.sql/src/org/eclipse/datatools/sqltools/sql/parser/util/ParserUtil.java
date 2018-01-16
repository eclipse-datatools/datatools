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
package org.eclipse.datatools.sqltools.sql.parser.util;

import org.eclipse.datatools.sqltools.sql.parser.Token;

/**
 * @author Hui Cao
 * 
 */
public class ParserUtil {

    /**
     * Assemble the syntax error message for the SQL parser.
     * 
     * @param currentToken the current token that has been successfully consumed by the parser
     * @return the syntax error message 
     */
    public static String getErrorMessage(Token currentToken)
    {
        Token token = currentToken;
        while (token.image == null)
        {
            token = token.next;
        }

        return Messages.getString("SQLParser.syntax.error", Integer.toString(token.beginLine), Integer.toString(token.beginColumn), token.image);
    }

}
