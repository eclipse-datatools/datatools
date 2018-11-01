/*
 *************************************************************************
 * Copyright (c) 2006, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.test.impl;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.services.impl.PropertyProviderImpl;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;

public class ExternalizedPropertyProvider extends PropertyProviderImpl
{
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.consumer.services.impl.PropertyProviderImpl#getExternalizedProperties(java.lang.String, java.lang.Object)
     */
    protected Properties getExternalizedProperties( String configId, Object appContext ) 
        throws OdaException
    {
        Properties testProps = new Properties();
        if( appContext != null && appContext instanceof String )
        {
            testProps.setProperty( CommonConstants.CONN_HOME_DIR_PROP, 
                                    (String) appContext );
            testProps.setProperty( "D", "configD" ); 
        }
        return testProps;
    }
}
