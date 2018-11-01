/*******************************************************************************
 * Copyright (c) 2005, 2011 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *    Actuate Corporation - re-factored to support the cipherProvider extension point [BZ 358686]
 *
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity.internal.security;

/**
 * Internal interface for accessing functionality provided by security extensions.
 * <br>
 * The original internal interface here has been moved to the 
 * org.eclipse.datatools.connectivity.security package as a public API.
 * This now extends from it instead, for backward compatibility. 
 * @deprecated  since 1.2.4 (DTP 1.9.2); 
 *              replaced by {@link  org.eclipse.datatools.connectivity.security.ICipherProvider}
 */
public interface ICipherProvider extends
        org.eclipse.datatools.connectivity.security.ICipherProvider
{
}
