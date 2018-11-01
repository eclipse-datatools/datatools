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
package org.eclipse.datatools.sqltools.core.dbitem;

/**
 * Represents a SQL Event object.
 * @author Hui Cao
 *
 */
public interface IEvent extends IDBItem
{
    /**
     * 
     * @return event type defined in this interface
     */
    public int getEventType();

    /**
     * Retrieves the supported parameter list for a given event type
     * @param eventType event type defined in this interface
     * @return supported parameter array
     */
    public String[] getSupportedParameter(int eventType);

    /**
     * Retrieves the valid parameter values for a given parameter name.
     * @param parameter parameter name
     * @return parameter value array
     */
    public String[] getValidValues(String parameter);
}
