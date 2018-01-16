/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.layout;

/**
 * @author ljulien
 */
public interface IServerExplorerLayoutExtensionProvider
{
	public void enableLayout (Layout layout);

	/**
	 * Enumeration that will define the Layout implemented by the Server Explorer
	 * @author ljulien
	 */
	public class Layout 
	{
		public static final Layout HIERARCHICAL = new Layout ();
		public static final Layout VNODE = new Layout ();
	}
}
