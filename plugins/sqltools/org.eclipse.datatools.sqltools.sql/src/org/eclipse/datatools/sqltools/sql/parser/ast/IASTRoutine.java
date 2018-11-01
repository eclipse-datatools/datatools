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
 * 
 * @author Hui Cao
 * 
 */
public interface IASTRoutine extends IASTDeployable
{

    public abstract org.eclipse.datatools.sqltools.sql.parser.ast.Node getWithNode();

    public abstract void setWithNode(org.eclipse.datatools.sqltools.sql.parser.ast.Node with);

    public abstract IASTSQLParamDefList getParameDefList();

}