/*******************************************************************************
 * Copyright (c) 2005 Exadel Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Exadel Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.util;

public class NodeSQLPage {
	private String nameConnection;
	private String statementSQL;

	public String getStatementSQL() {
		return statementSQL;
	}
	
	void setStatementSQL(String statementSQL) {
		this.statementSQL = statementSQL;
	}

	public String getNameConnection() {
		return nameConnection;
	}
	

	void setNameConnection(String nameConnection) {
		this.nameConnection = nameConnection;
	}
	

}
