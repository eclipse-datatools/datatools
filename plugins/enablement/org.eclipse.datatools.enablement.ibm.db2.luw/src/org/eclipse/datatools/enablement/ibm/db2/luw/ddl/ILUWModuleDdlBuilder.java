/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.ddl;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

public interface ILUWModuleDdlBuilder {
	
	public String buildCreateStatement(SQLObject object,boolean quoteIdentifiers,boolean qualifyNames);
	public String buildDropStatement(SQLObject object,boolean quoteIdentifiers,boolean qualifyNames);
	public String buildCommentStatement(SQLObject object,boolean quoteIdentifiers,boolean qualifyNames);
 }
