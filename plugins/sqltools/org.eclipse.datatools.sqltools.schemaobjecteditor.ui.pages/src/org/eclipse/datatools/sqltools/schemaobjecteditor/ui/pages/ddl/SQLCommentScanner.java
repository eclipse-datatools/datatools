/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.ddl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.SOEUIPagePlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.ColorProvider;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.AbstractSQLScanner;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.ISQLPartitions;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.Token;

/**
 * 
 * @author Li Huang
 */
public class SQLCommentScanner extends AbstractSQLScanner
{

    ColorProvider _provider = SOEUIPagePlugin.getDefault().getColorProvider();
    String        _commentKey;

    public SQLCommentScanner(String commentKey)
    {
        _commentKey = commentKey;
        initialize();
    }

    protected Token getToken()
    {

        Token token = new Token(null);
        if (_commentKey.equals(ISQLPartitions.SQL_COMMENT))
        {
            token = new Token(new TextAttribute(_provider.getColor(ColorProvider.SINGLE_LINE_COMMENT)));
        }
        else if (_commentKey.equals(ISQLPartitions.SQL_MULTILINE_COMMENT))
        {
            token = new Token(new TextAttribute(_provider.getColor(ColorProvider.MULTI_LINE_COMMENT)));
        }
        return token;
    }

    /*
     * @see AbstractSQLScanner#createRules()
     */
    protected List createRules()
    {
        List list = new ArrayList();
        Token defaultToken = getToken();
        setDefaultReturnToken(defaultToken);

        return list;
    }

}
