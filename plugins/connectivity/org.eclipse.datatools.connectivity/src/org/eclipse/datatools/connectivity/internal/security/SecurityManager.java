/*******************************************************************************
 * Copyright (c) 2005, 2011 Sybase, Inc. and others
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 *    Actuate Corporation - added the cipherProvider extension point [BZ 358686]
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.security;

import java.io.File;

/**
 * This class is intended manage security extensions used by the connection
 * profile framework.
 * 
 * Initially, only a non-configurable default encryption provider will be
 * implemented.
 * 
 * @author rcernich
 * 
 * Created on Dec 19, 2005
 */
public class SecurityManager {

	private static SecurityManager sInstance;

	@SuppressWarnings("deprecation")
    private ICipherProvider mDefaultCipherProvider;

	public static SecurityManager getInstance() {
		if (sInstance == null) {
			sInstance = new SecurityManager();
		}
		return sInstance;
	}

	private SecurityManager() {
		super();
		mDefaultCipherProvider = new DefaultCipherProvider();
	}

	@SuppressWarnings("deprecation")
    public ICipherProvider getDefaultCipherProvider() {
		return mDefaultCipherProvider;
	}
    
	/**
	 * Returns the cipher provider instance registered for the file extension 
	 * of the specified file.
	 * The default cipher provider is returned if no custom cipher provider is registered 
	 * for the file extension.
	 * @param profileStoreFile     the abstract representation of a connection profile store file
     * @return  an instance of {@link org.eclipse.datatools.connectivity.security.ICipherProvider}  
     *          that provides cipher instances for the specified file's type
	 * @since 1.2.4 (DTP 1.9.2)
	 */
    public org.eclipse.datatools.connectivity.security.ICipherProvider getCipherProvider( File profileStoreFile ) {
        org.eclipse.datatools.connectivity.security.ICipherProvider provider = 
                CipherProviderExtensions.getCipherProviderForFile( profileStoreFile );
        if( provider == null )
            provider = getDefaultCipherProvider();
        return provider;
    }

    /**
     * Returns the cipher provider instance registered for the specified file extension. 
     * The default cipher provider is returned if no custom cipher provider is registered 
     * for the file extension.
     * @param fileExtension    the file extension for which a cipher provider is registered
     * @return  an instance of {@link org.eclipse.datatools.connectivity.security.ICipherProvider}  
     *          that provides cipher instances for the specified file extension
     * @since 1.2.4 (DTP 1.9.2)
     */
    public org.eclipse.datatools.connectivity.security.ICipherProvider getCipherProviderForFileExtension( 
            String fileExtension ) {
        org.eclipse.datatools.connectivity.security.ICipherProvider provider = 
                CipherProviderExtensions.getCipherProviderForFileExtension( fileExtension );
        if( provider == null )
            provider = getDefaultCipherProvider();
        return provider;
    }

}
