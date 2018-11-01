/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.core.editor;


public class TableDataSaveStatus {

    public int inserted = 0;
    public int updated = 0;
    public int deleted = 0;
    
    public boolean duplicateRow = false;
    
    public void reset()
    {
        inserted = 0;
        updated = 0;
        deleted = 0;
        duplicateRow = false;
    }
}
