/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ddl;

import java.util.List;
import java.util.Map;

/**
 * Used in NewDdlGenWizard selection page.
 * @author linsong
 */
public interface IDdlGenWizardProvider
{
    /**
     * 
     * @return list of supported DatabaseObjectType 
     */
    List getSupportedObjectTypes();
    
    /**
     * 
     * @return map of specified object type name and wizard class name
     */
    Map getObjectTypeDdlGenWizardMap();
}
