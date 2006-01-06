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
package org.eclipse.datatools.sqltools.db.generic.service;


import org.eclipse.datatools.sqltools.core.services.DefaultSQLService;
import org.eclipse.datatools.sqltools.db.generic.parser.GenericSQLParser;
import org.eclipse.datatools.sqltools.db.generic.sql.GenericSQLSyntax;
import org.eclipse.datatools.sqltools.editor.template.GenericSQLContextType;
import org.eclipse.datatools.sqltools.sql.parser.ISQLSyntax;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;

/**
 * @author Hui Cao
 * 
 */
public class GenericSQLService extends DefaultSQLService{

	public GenericSQLContextType getSQLContextType() {
		return super.getSQLContextType();
	}

	public SQLParser getSQLParser() {
		return GenericSQLParser.getInstance();
	}

	public ISQLSyntax getSQLSyntax() {
		return new GenericSQLSyntax();
	}
	
}
