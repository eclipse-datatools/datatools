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
package org.eclipse.datatools.sqltools.db.derby.core.services;

import org.eclipse.datatools.sqltools.db.derby.parser.DerbySQLParser;
import org.eclipse.datatools.sqltools.db.derby.sql.DerbySQLSyntax;
import org.eclipse.datatools.sqltools.db.generic.service.GenericSQLService;
import org.eclipse.datatools.sqltools.sql.ISQLSyntax;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;

/**
 * @author Hui Cao
 * 
 */
public class DerbySQLService extends GenericSQLService {

	public SQLParser getSQLParser() {
		return DerbySQLParser.getInstance();
	}

	public ISQLSyntax getSQLSyntax() {
		return new DerbySQLSyntax();
	}

	
}
