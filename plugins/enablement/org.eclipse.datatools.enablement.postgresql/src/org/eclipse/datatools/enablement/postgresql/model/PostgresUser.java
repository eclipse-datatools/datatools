/*******************************************************************************
 * Copyright (c) 2011 Zenika
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: queinnec - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.postgresql.model;

import org.eclipse.datatools.modelbase.sql.accesscontrol.User;

/**
 * A PostgreSQL user definition.
 * 
 * @author pierre.queinnec@zenika.com
 */
public interface PostgresUser extends User {

	// TODO add usecreatedb | usesuper | usecatupd | valuntil | useconfig

}
