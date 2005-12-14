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
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.layout;

import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServerExplorerContentService;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServicesManager;

/**
 * @author ljulien
 */
public class VirtualFolderLayoutAction
{
	/**
	 * Will enable the Server Explorer Virtual Node Layout option
	 */
	public void execute ()
	{
		IServerExplorerContentService serverExplorer = IServicesManager.INSTANCE.getServerExplorerContentService();
		serverExplorer.getServerExplorerLayoutService().enableVirtualNodeLayout();
		serverExplorer.updateLayout();
	}
}
