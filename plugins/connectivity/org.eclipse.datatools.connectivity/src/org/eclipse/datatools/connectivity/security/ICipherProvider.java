/*******************************************************************************
 * Copyright (c) 2005, 2011 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *    Actuate Corporation - added the cipherProvider extension point [BZ 358686]
 *    
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity.security;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;


/**
 * [Provisional]<br>
 * Interface for accessing functionality provided by security extensions.
 * 
 * @author rcernich
 *
 * @since 1.2.4 (DTP 1.9.2)
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
