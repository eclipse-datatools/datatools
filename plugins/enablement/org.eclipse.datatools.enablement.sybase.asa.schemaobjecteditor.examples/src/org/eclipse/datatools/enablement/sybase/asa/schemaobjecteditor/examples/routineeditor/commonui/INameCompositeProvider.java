/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui;

import org.eclipse.swt.widgets.Text;

/**
 * 
 * @author Hui Cao
 * 
 */
public interface INameCompositeProvider extends ICompositeProvider
{

    /**
     * Style constant for the basic layout (value is 0).
     */
    public static final int NONE          = 0;
    /**
     * Style constant to show database name (value is 1&lt;&lt;1).
     */
    public static final int SHOW_DATABASE = 1 << 1;
    /**
     * Style constant to show comment (value is 1&lt;&lt;2).
     */
    public static final int SHOW_COMMENT  = 1 << 2;

    public abstract void setValues(String dbName, String owner, String name, String tableName, String tableOwner);

    public abstract Text getNameControl();

}