/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.containment;

public interface ConstraintGroupID
{
    public static final String PRIMARYKEY = "core.sybase.PrimaryKey";
    public static final String FOREIGNKEY = "core.sybase.ForeignKey";
    public static final String UNIQUECONSTRAINT = "core.sybase.UniqueConstraint";
    public static final String CHECKCONSTRAINT = "core.sybase.CheckConstraint";
    public static final String PARAMETER = "core.sybase.routine.parameter"; //$NON-NLS-1$
}
