/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.security;

import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.spec.KeySpec;

import org.eclipse.datatools.connectivity.internal.security.DefaultCipherProvider;

/**
 * [Provisional]<br>
 * A base class implementation of the {@link ICipherProvider} interface.
 * It reads a secret (symmetric) key specification ({@link KeySpec})
 * from a bundled resource.
 * A custom ICipherProvider class implementation may optionally extend from this, 
 * to provide own instance of KeySpec.  
 * @since 1.2.4 (DTP 1.9.2)
 */
public class CipherProviderBase extends DefaultCipherProvider implements
        ICipherProvider
{
    public CipherProviderBase()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.security.DefaultCipherProvider#getKeySpec()
     */
    @Override
    protected KeySpec getKeySpec() throws GeneralSecurityException
    {
        // sub-class may override
        return super.getKeySpec();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.security.DefaultCipherProvider#getKeyResource()
     */
    @Override
    protected URL getKeyResource()
    {
        // sub-class may override
        return super.getKeyResource();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.security.DefaultCipherProvider#getDefaultKeyAlgorithm()
     */
    @Override
    protected String getDefaultKeyAlgorithm()
    {
        // sub-class may override
        return super.getDefaultKeyAlgorithm();
    }

}
