/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.label;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.LabelSelector;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.datatools.modelbase.sql.schema.Database;

public class UserSelector implements LabelSelector {
	private static final DatabaseDefinitionRegistry REGISTRY = RDBCorePlugin
			.getDefault().getDatabaseDefinitionRegistry();

	public boolean select(Object element) {
		Database database = ((User) element).getDatabase();
		return database != null ? REGISTRY.getDefinition(database)
				.isUserSupported() : false;
	}
}
