/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ddl;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;

public interface IDDLGeneratorWrapper
{

    /**
     * Returns the create statements DDL for the given SQL objects
     * 
     * @param objs the SQL objects
     * @return the create statements of the SQL objects
     */
    public String[] getCreateStatementsDDL(SQLObject[] objs);

    /**
     * Returns the DDL for the given SQL objects
     * 
     * @param rootObjs
     * @param allObjs
     * @param options
     * @param progressMonitor
     * @return
     * @author linsong
     */
    public String[] generateDeltaDDL(EObject rootObject, ChangeDescription changeDescription, IProgressMonitor monitor);

    /**
     * Set qualified
     * 
     * @param qualified
     */
    public void setFullQualified(boolean qualified);

    /**
     * Set current user who does generation operation.
     * 
     * @param setUser
     */
    public void setGenSetUser(boolean setUser);
}
