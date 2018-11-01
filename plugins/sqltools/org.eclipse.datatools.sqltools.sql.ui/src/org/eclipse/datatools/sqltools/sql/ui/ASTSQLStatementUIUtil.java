/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sql.ui;

import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.swt.graphics.Image;

public class ASTSQLStatementUIUtil {

    public static Image getImage(int type)
    {
        switch (type)
        {
            case SQLParserConstants.TYPE_SQL_OTHERS:
                return SQLImages.getImage(SQLImages.IMG_EDT_SQL);
            case SQLParserConstants.TYPE_SQL_SELECT:
                return SQLImages.getImage(SQLImages.IMG_EDT_SELECT);
            case SQLParserConstants.TYPE_SQL_INSERT:
                return SQLImages.getImage(SQLImages.IMG_EDT_INSERT);
            case SQLParserConstants.TYPE_SQL_DELETE:
                return SQLImages.getImage(SQLImages.IMG_EDT_DELETE);
            case SQLParserConstants.TYPE_SQL_UPDATE:
                return SQLImages.getImage(SQLImages.IMG_EDT_UPDATE);
            case SQLParserConstants.TYPE_SQL_CREATE_DATABASE:
            case SQLParserConstants.TYPE_SQL_ALTER_DATABASE:
                return SQLImages.getImage(SQLImages.IMG_EDT_DATABASE);
            case SQLParserConstants.TYPE_SQL_CREATE_TABLE:
            case SQLParserConstants.TYPE_SQL_ALTER_TABLE:
                return SQLImages.getImage(SQLImages.IMG_EDT_TABLE);
            case SQLParserConstants.TYPE_SQL_CREATE_VIEW:
                return SQLImages.getImage(SQLImages.IMG_EDT_VIEW);
            case SQLParserConstants.TYPE_SQL_ALTER_VIEW:
                return SQLImages.getImage(SQLImages.IMG_EDT_VIEW);
            case SQLParserConstants.TYPE_SQL_DROP_VIEW:
                return SQLImages.getImage(SQLImages.IMG_EDT_VIEW);
            case SQLParserConstants.TYPE_SQL_CREATE_PROCEDURE:
            case SQLParserConstants.TYPE_SQL_ALTER_PROCEDURE:
                return SQLImages.getImage(SQLImages.IMG_EDT_PROCEDURE);
            case SQLParserConstants.TYPE_SQL_CREATE_FUNCTION:
                return SQLImages.getImage(SQLImages.IMG_EDT_FUNCTION);
            case SQLParserConstants.TYPE_SQL_ALTER_FUNCTION:
                return SQLImages.getImage(SQLImages.IMG_EDT_FUNCTION);  
            case SQLParserConstants.TYPE_SQL_CREATE_EVENT:
                return SQLImages.getImage(SQLImages.IMG_EDT_EVENT);
            case SQLParserConstants.TYPE_SQL_ALTER_EVENT:
                return SQLImages.getImage(SQLImages.IMG_EDT_EVENT);
            case SQLParserConstants.TYPE_SQL_CREATE_TRIGGER:
                return SQLImages.getImage(SQLImages.IMG_EDT_TRIGGER);
            case SQLParserConstants.TYPE_SQL_ALTER_TRIGGER:
                return SQLImages.getImage(SQLImages.IMG_EDT_TRIGGER);
            case SQLParserConstants.TYPE_SQL_CREATE_DEFAULT:
                return SQLImages.getImage(SQLImages.IMG_EDT_DEFAULT);
            case SQLParserConstants.TYPE_SQL_DECLARE:
                return SQLImages.getImage(SQLImages.IMG_EDT_DATATYPE);
            default:
                return SQLImages.getImage(SQLImages.IMG_EDT_SQL);
        }

    }
}
