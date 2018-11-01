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

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;


/**
 * All the statements that can be deploied to db as a executable unit (Routine) should implement this interface
 * @author Hui Cao
 *
 */
public interface IASTDeployable extends Node
{

    /**
     * 
     * @return the SP/fuction/trigger/event name
     */
    public String getDBObjectName();

    /**
     * whether it's SP/fuction/trigger/event. See SQLParserConstants
     * @return
     */
    public int getType();
    
    /**
     * Returns the corresponding procedural object sql model, such as <code>Routine</code>,<code>Trigger</code> or
     * <code>Event</code>. Might be null if the parser didn't set the model first.
     * 
     * @return
     */
    public SQLObject getSQLModel();
    
    /**
     * Associates a SQLObject with this AST node. Only intended for parser to call.
     * @param model
     */
    public void setSQLModel(SQLObject model);

    public Node getNameNode();

    public void setNameNode(Node nameNode);
}
