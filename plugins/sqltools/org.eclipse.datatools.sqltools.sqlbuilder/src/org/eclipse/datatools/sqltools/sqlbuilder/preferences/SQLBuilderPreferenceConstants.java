/***********************************************************************************************************************
 * Copyright (c) 2007 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.preferences;

/**
 * Constants for SQL Builder preferences.
 * 
 * @author Jeremy Lindop
 */
public interface SQLBuilderPreferenceConstants {
    public static final String OMIT_CURRENT_SCHEMA_IN_SQL = "omit.current.schema.in.sql";
    public static final boolean DEFAULT_OMIT_CURRENT_SCHEMA_IN_SQL = false;

    public static final String OMIT_CURRENT_SCHEMA_USE_AUID = "omit.current.schema.use.auid";
    public static final boolean DEFAULT_OMIT_CURRENT_SCHEMA_USE_AUID = true;

    public static final String OMIT_CURRENT_SCHEMA_CURRENT_SCHEMA = "omit.current.schema.current.schema";
    public static final String DEFAULT_OMIT_CURRENT_SCHEMA_CURRENT_SCHEMA = "";

}
