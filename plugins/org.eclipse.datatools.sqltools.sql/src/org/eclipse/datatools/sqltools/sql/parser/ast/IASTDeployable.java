/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.parser.ast;

import org.eclipse.swt.graphics.Image;

/**
 * All the statements that can be deploied to db as a executable unit (Routine) should implement this interface
 * @author Hui Cao
 *
 */
public interface IASTDeployable extends Node
{
    /**
     * 
     * @return image representing this node
     */
    public Image getImage();

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
}
