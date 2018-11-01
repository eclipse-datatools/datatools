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
package org.eclipse.datatools.sqltools.sql.parser.ast;

/**
 * Interface for a parameter definition/local variable definition
 * 
 * @author Hui Cao
 *
 */
public interface IASTSQLParam
{
    public static final int INPUT  = 0;

    public static final int OUTPUT = 1;

    public static final int INOUT  = 2;

    public abstract String getDefaultValue();

    public abstract void setDefaultValue(String defaultValue);

    public abstract int getDirection();

    public abstract void setDirection(int direction);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getType();

    public abstract void setType(String type);

    public abstract IASTSQLDataType getTypeObject();

    public abstract void setTypeObject(IASTSQLDataType type);


}
