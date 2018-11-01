/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.export;

import org.eclipse.datatools.sqltools.result.export.AbstractOutputter;

/**
 * An instance of this class describes a result outputter
 * 
 * @author Dafan Yang
 */
public interface IOutputterDescriptor
{
    /**
     * Returns the type id of the outputter
     * 
     * @return the type id
     */
    public String getTypeId();

    /**
     * Returns the type name of the outputter
     * 
     * @return the type name
     */
    public String getTypeName();

    /**
     * Returns the file extension which the outputter supports
     * 
     * @return the file extension
     */
    public String getFileExtension();

    /**
     * Returns whether the outputter support user-defined delimiter
     * 
     * @return whether the outputter support user-defined delimiter
     */
    public boolean supportDelimiter();
    
    /**
     * Returns whether the outputter support XML result set
     * 
     * @return whether the outputter support XML result set
     */
    public boolean supportXMLResult();

    /**
     * Returns the outputter
     * 
     * @return the outputter
     */
    public AbstractOutputter getOutputter();
    
    /**
     * Returns the display string of this outputter
     * @return the display string of this outputter
     */
    public String getDisplayString();
    
    /**
     * Returns the display string for the extension fitler
     * @return the display string for the extension fitler
     */
    public String getExtensionFilterDisplayString();
    
    /**
     * Returns the file filter string 
     * @return
     */
    public String getExtFilterString();
}
