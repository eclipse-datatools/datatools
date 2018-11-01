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
package org.eclipse.datatools.enablement.ingres.internal.ui.core;

import org.eclipse.datatools.enablement.ingres.internal.ui.parser.IngresSQLParser;
import org.eclipse.datatools.enablement.ingres.internal.ui.sql.IngresSQLSyntax;
import org.eclipse.datatools.sqltools.core.services.SQLService;
import org.eclipse.datatools.sqltools.sql.ISQLSyntax;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;

/**
 * An Ingres related SQL service implementation.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresSQLService extends SQLService {

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.core.services.SQLService#getSQLParser()
	 */
	public SQLParser getSQLParser() {
		return IngresSQLParser.getInstance();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.core.services.SQLService#getSQLSyntax()
	 */
	public ISQLSyntax getSQLSyntax() {
		return new IngresSQLSyntax();
	}

}
