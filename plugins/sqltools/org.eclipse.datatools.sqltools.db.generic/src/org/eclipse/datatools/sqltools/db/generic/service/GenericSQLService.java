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
package org.eclipse.datatools.sqltools.db.generic.service;


import org.eclipse.datatools.sqltools.core.services.SQLService;
import org.eclipse.datatools.sqltools.db.generic.parser.GenericSQLParser;
import org.eclipse.datatools.sqltools.db.generic.sql.GenericSQLSyntax;
import org.eclipse.datatools.sqltools.sql.ISQLSyntax;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;

/**
 * @author Hui Cao
 * 
 */
public class GenericSQLService extends SQLService{


	public SQLParser getSQLParser() {
		return GenericSQLParser.getInstance();
	}

	public ISQLSyntax getSQLSyntax() {
		return new GenericSQLSyntax();
	}
	
}
