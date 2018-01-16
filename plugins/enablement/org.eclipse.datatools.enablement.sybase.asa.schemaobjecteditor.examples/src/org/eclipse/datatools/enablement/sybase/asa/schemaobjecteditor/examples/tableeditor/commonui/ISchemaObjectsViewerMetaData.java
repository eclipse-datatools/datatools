/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.commonui;

/**
 * Meta-data for schema objects table viewer
 * 
 * @author Idull
 */
public interface ISchemaObjectsViewerMetaData
{
    /**
     * Columns number to be displayed
     * 
     * @return
     */
    public int getColumnCount();

    /**
     * Column name
     * 
     * @param colIndex
     * @return
     */
    public String getColumnName(int colIndex);

    /**
     * Column width
     * 
     * @param colIndex
     * @return
     */
    public int getColumnWidth(int colIndex);
}
