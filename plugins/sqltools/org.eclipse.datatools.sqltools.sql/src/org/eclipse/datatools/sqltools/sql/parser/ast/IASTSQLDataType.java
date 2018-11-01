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
 * Represents a SQL data type.
 * @author Hui Cao
 *
 */
public interface IASTSQLDataType
{
    public abstract int getLength();

    public abstract void setLength(int length);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract int getScale();

    public abstract void setScale(int scale);
}
