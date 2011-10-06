/*******************************************************************************
 * Copyright (c) 2011 Zenika
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
