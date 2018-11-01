/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.design.ui.manifest;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;

/**
 * Represents the customizable behavior defined by an ODA UI Extension
 * for its data set wizard that allows an user to create 
 * a new ODA data set design instance. 
 * It encapsulates the content of the <i>dataSetWizard</i> element
 * defined in the ODA Design UI extension point.
 */
public class DataSetWizardInfo
{
    public static final String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$
    
    private String m_className;
    private String m_windowTitle;
    
    DataSetWizardInfo( IConfigurationElement dataSetWizardElement ) 
        throws OdaException
    {
        m_className = dataSetWizardElement.getAttribute( CLASS_ATTRIBUTE );
        if( m_className == null || m_className.length() == 0 )
            throw new OdaException( 
                    Messages.bind( Messages.manifest_missingAttributeValue,
                                    CLASS_ATTRIBUTE ));

        m_windowTitle = dataSetWizardElement.getAttribute( "windowTitle" );         //$NON-NLS-1$
    }

    /**
     * Returns the full class name of the data set wizard.
     * <br>The wizard class must either use or extend from the ODA 
     * framework's wizard base class.  See plugin schema for details.
     * @return the full class name of the data set wizard
     */
    public String getClassName()
    {
        return m_className;
    }

    /**
     * Returns the customized title of the data set wizard window.
     * @return  customized window title; may be null, if none is specified
     */
    public String getWindowTitle()
    {
        return m_windowTitle;
    }

}
