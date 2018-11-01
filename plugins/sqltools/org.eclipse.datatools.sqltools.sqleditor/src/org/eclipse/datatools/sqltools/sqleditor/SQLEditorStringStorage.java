/*******************************************************************************
 * Copyright (c) 2012 Teradata Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Teradata Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor;

/**
 * This class is used when an in-memory String is the input
 * to an editor. Because the string is not (yet) destined to
 * be written to the file system it doesn't need to have the
 * string encoded in the workbench's file system encoding.
 * 
 * The {@link #getCharset()} method overrides the base class
 * to return UTF-8.
 * 
 * @author Charles Eutsler, Teradata Corporation
 *
 */
public class SQLEditorStringStorage extends SQLEditorStorage {

    /**
     * Creates an instance of this class with the given string as the storage
     * source content. The new storage object has a default name.
     * 
     * @param source the source content for this object
     */
    public SQLEditorStringStorage(String source) {
        super(source);
    }

    /**
     * Creates an instance of this class with the given name and the given
     * string as the storage content.
     * 
     * @param name the name for this storage object
     * @param source the content source for this object
     */
    public SQLEditorStringStorage(String name, String source) {
        super(name, source);
    }

    /**
     * Returns UTF-8 as the charset rather than the base class's workbench encoding.
     * @return UTF-8
     * 
     * @see org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorage#getCharset()
     */
    @Override
    public String getCharset() {
        return "UTF-8";
    }
}
