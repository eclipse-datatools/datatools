/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.definition;

import java.sql.Connection;
import java.util.Iterator;

import org.eclipse.datatools.modelbase.sql.schema.Database;

public interface DatabaseDefinitionRegistry {
	public Iterator getProducts();
	public Iterator getConnectibleProducts(); 
	public Iterator getVersions(String product);
	public Iterator getConnectibleVersions(String product);
	public DatabaseDefinition getDefinition(String product, String version);
	public DatabaseDefinition getDefinition(Database database);
	public DatabaseDefinition recognize(Connection connection);
}
