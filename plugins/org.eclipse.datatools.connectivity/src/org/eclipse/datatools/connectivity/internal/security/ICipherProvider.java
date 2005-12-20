/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity.internal.security;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;


/**
 * Interface for accessing functionality provided by security extensions.
 * 
 * @author rcernich
 *
 * Created on Dec 19, 2005
 */
public interface ICipherProvider {
	
	/**
	 * @return a Cipher object for encryption
	 */
	public Cipher createEncryptionCipher() throws GeneralSecurityException;
	
	/**
	 * @return a Cipher object for decryption
	 */
	public Cipher createDecryptionCipher() throws GeneralSecurityException;

}
