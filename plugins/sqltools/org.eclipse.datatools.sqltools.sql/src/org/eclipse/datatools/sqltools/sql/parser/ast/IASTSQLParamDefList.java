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
 * Interface for the parameter definition list section of a stored procedure
 * 
 * @author Hui Cao
 *
 */
public interface IASTSQLParamDefList
{
    IASTSQLParam getParameter(int index);

    int getParameterCount();
}
