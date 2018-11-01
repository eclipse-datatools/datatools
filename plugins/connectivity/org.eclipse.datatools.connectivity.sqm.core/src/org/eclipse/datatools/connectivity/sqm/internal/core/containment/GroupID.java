/*******************************************************************************
 * Copyright (c) 2001, 2004, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.containment;

public interface GroupID {
	public static final String CORE_PREFIX = "core.";  //$NON-NLS-1$
	public static final String COLUMN = "core.sql.tables.Column";  //$NON-NLS-1$
	public static final String CONSTRAINT = "core.sql.constraints.Constraint";  //$NON-NLS-1$
	public static final String COMMENT = "core.sql.schema.Comment"; //$NON-NLS-1$
	public static final String DATABASE = "core.sql.schema.Database";  //$NON-NLS-1$
	public static final String DEPENDENCY = "core.sql.schema.Dependency";  //$NON-NLS-1$
	public static final String INDEX = "core.sql.constraints.Index";  //$NON-NLS-1$
	public static final String PROCEDURE = "core.sql.routines.Procedure";  //$NON-NLS-1$
	public static final String FUNCTION = "core.sql.routines.Function";  //$NON-NLS-1$
	public static final String SCHEMA = "core.sql.schema.Schema";  //$NON-NLS-1$
	public static final String SEQUENCE = "core.sql.schema.Sequence";  //$NON-NLS-1$
	public static final String TABLE = "core.sql.tables.BaseTable";  //$NON-NLS-1$
	public static final String TRIGGER = "core.sql.tables.Trigger";  //$NON-NLS-1$
	public static final String USER_DEFINED_TYPE = "core.sql.datatypes.UserDefinedType";  //$NON-NLS-1$
	public static final String VIEW = "core.sql.tables.ViewTable";  //$NON-NLS-1$
	public static final String CATALOG = "core.sql.schema.Catalog";  //$NON-NLS-1$
	public static final String GROUP = "core.sql.schema.Group"; //$NON-NLS-1$
	public static final String USER = "core.sql.schema.User"; //$NON-NLS-1$
	public static final String ROLE = "core.sql.schema.Role"; //$NON-NLS-1$
	public static final String PRIVILEGE = "core.sql.accesscontrol.Privilege"; //$NON-NLS-1$
}
