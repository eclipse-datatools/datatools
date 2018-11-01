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
package org.eclipse.datatools.sqltools.internal.sqlscrapbook;
/**
 * 
 * @author Hui Cao
 * 
 */
public class SQLFilePreferenceConstants {

	//whether to show error dialog when failed to connect
	public static final String FAIL_TO_CONNECT_DATABASE                = "sqlfile.fail.to.connect.database";
	public static final String DEFAULT_OPEN							   = SqlscrapbookPlugin.PLUGIN_ID + "preference.defaultopen";
	//select commit mode, automatic or manual
    public static final String CONNECTION_COMMIT_MODE                  = SqlscrapbookPlugin.PLUGIN_ID + "preference.commitmode";
}
