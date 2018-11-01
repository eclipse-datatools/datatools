/*******************************************************************************
 * Copyright (c) 2005, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.catalog;

import org.eclipse.datatools.modelbase.sql.routines.Source;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions;

/**
 * @author yehsh
 */
public interface JavaProcedureInfo {
    public DB2JavaOptions getJavaProcedure(); 
    public Source getSource();
    public String getExternalName();
    /** Get the root package name associated with the SQLJ stored procedure */
    public String getDB2Package();
    /** Get the collection name associated with the SQLJ stored procedure */
    public String getCollectionName();
}
