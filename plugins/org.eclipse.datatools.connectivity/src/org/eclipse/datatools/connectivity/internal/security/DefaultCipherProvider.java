/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.security;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

/**
 * Default cipher provider using a statically defined symmetric key.
 * 
 * @author rcernich
 * 
 * Created on Dec 19, 2005
 */
public class DefaultCipherProvider implements ICipherProvider {

	public DefaultCipherProvider() {
		super();
	}

	public Cipher createEncryptionCipher() throws GeneralSecurityException {
		Key k = loadKey();
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
			URL url = ConnectivityPlugin
					.getDefault()
					.getBundle()
					.getResource(
							"org/eclipse/datatools/connectivity/internal/security/cpkey"); //$NON-NLS-1$

			ois = new ObjectInputStream(url.openStream());

			SecretKeySpec spec = (SecretKeySpec) ois.readObject();
			SecretKeyFactory factory = SecretKeyFactory.getInstance(spec
					.getAlgorithm());

			return factory.generateSecret(new DESedeKeySpec(spec.getEncoded()));
		}
		catch (IOException e) {
			throw new InvalidKeySpecException();
		}
		catch (ClassNotFoundException e) {
			throw new InvalidKeySpecException();
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

}
