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

import java.util.Collection;

/**
 * The common interface for all SQL statements.
 * 
 * @author Hui Cao
 *  
 */
public interface IASTSQLStatement extends Node
{
    /**
     * see SQLParserConstants
     * @return 
     */
    public int getType();

    /**
     * What database objects does this statement operate on. 
     * This is mainly used to describe the statement in UI, such as outline view. 
     * @return Collections of object name Strings
     */
    public Collection getObjectIdentifiers();

    /**
     * Adds an object identifier to represent this statement.
     * @param name the object name
     */
    public void addObjectIdentifier(String name);

}
