/**
 * Created on 2006-9-4
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
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
