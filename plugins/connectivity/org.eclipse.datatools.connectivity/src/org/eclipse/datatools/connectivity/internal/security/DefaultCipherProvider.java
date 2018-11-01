/*******************************************************************************
 * Copyright (c) 2005, 2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 *  Actuate Corporation - re-factored to support the cipherProvider extension point [BZ 358686]
 * 
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.security;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

/**
 * Default cipher provider using a statically defined symmetric key.
 * 
 * @author rcernich
 */
@SuppressWarnings("deprecation")
public class DefaultCipherProvider implements ICipherProvider {

    private static final String SECRET_KEY_ALGORITHM_DESEDE = "DESede"; //$NON-NLS-1$
    private static final String SECRET_KEY_ALGORITHM_DES = "DES"; //$NON-NLS-1$
    
	public DefaultCipherProvider() {
		super();
	}

	public Cipher createEncryptionCipher() throws GeneralSecurityException {
		Key k = loadKey();
		if (k == null)
		    return null;
		Cipher c = Cipher.getInstance(k.getAlgorithm());
		c.init(Cipher.ENCRYPT_MODE, k);
		return c;
	}

	public Cipher createDecryptionCipher() throws GeneralSecurityException {
		Key k = loadKey();
		Cipher c = Cipher.getInstance(k.getAlgorithm());
		c.init(Cipher.DECRYPT_MODE, k);
		return c;
	}

	private Key loadKey() throws GeneralSecurityException {
		ObjectInputStream ois = null;
        try {
            KeySpec spec = getKeySpec();
            String algorithm = getDefaultKeyAlgorithm();

            if( spec instanceof SecretKeySpec )
            {
                SecretKeySpec secretSpec = (SecretKeySpec)spec;

                // use the algorithm specified in the SecretKeySpec
                algorithm = secretSpec.getAlgorithm();

                if( SECRET_KEY_ALGORITHM_DESEDE.equals( algorithm ) )
                    spec = new DESedeKeySpec( secretSpec.getEncoded() );
                else if( SECRET_KEY_ALGORITHM_DES.equals( algorithm ) )
                    spec = new DESKeySpec( secretSpec.getEncoded() );
                // else, use the KeySpec instance as is
            }

            SecretKeyFactory factory = SecretKeyFactory.getInstance( algorithm );
            return factory.generateSecret( spec );
        }
		finally {
			if (ois != null) {
				try {
					ois.close();
				}
				catch (IOException e) {
				}
			}
		}
	}
    
    /**
     * Returns the default secret-key algorithm to be used by this default implementation.
     * @return the standard name of a secret-key algorithm
     * @since DTP 1.9.2
     */
    protected String getDefaultKeyAlgorithm()
    {
        return SECRET_KEY_ALGORITHM_DESEDE;
    }
    
    /**
     * Returns a {@link KeySpec} for generating an encryption {@link Key}
     * for the encyrption and decryption of a target.
     * @return  a {@link KeySpec}
     * @throws GeneralSecurityException
     * @since DTP 1.9.2
     */
    protected KeySpec getKeySpec()  
        throws GeneralSecurityException
    {
        URL url = getKeyResource();
        if (url == null)
            throw new KeyStoreException();     // key resource is not available

        ObjectInputStream ois;
        try
        {
            ois = new ObjectInputStream( url.openStream() );
            Object readObject = ois.readObject();
            if( readObject instanceof KeySpec )
                return (KeySpec)readObject;
            throw new InvalidKeySpecException();
        }
        catch (IOException e) {
            throw new InvalidKeySpecException( e );
        }
        catch (ClassNotFoundException e) {
            throw new InvalidKeySpecException( e );
        }
    }

    /**
     * Returns the {@link URL} of a resource that contains a {@link KeySpec} object.
     * @return  the resource URL that contains a {@link KeySpec} object
     * @since DTP 1.9.2
     */
    protected URL getKeyResource()
    {
        return ConnectivityPlugin.getResource( 
                "org/eclipse/datatools/connectivity/internal/security/cpkey"); //$NON-NLS-1$
    }

}
