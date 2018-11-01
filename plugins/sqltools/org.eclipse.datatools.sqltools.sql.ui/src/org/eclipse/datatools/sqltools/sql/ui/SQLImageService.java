/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sql.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.swt.graphics.Image;

public class SQLImageService 
{
	
	public static final SQLImageService INSTANCE = new SQLImageService();
	
	private Map _nodeImageHandlerMap = new HashMap();
	
	private SQLImageService() {

		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint(
				"org.eclipse.datatools.sqltools.sql.ui", "sqlNodesImage"); //$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] extensions = extensionPoint.getExtensions();
		for (int i = 0; i < extensions.length; ++i) {
			IConfigurationElement[] configElements = extensions[i]
					.getConfigurationElements();
			for (int j = 0; j < configElements.length; ++j) {
				if (configElements[j].getName().equals("nodeImage")) { //$NON-NLS-1$
					String nodeClazz = configElements[j]
							.getAttribute("nodeClazzName"); //$NON-NLS-1$
					INodesImageHandler handler = null;
					try {
						handler = (INodesImageHandler) configElements[j]
								.createExecutableExtension("imageHandler"); //$NON-NLS-1$
						
						_nodeImageHandlerMap.put(nodeClazz, handler);
					} catch (CoreException e) {
						IStatus status = new Status(
								IStatus.ERROR,
								SQLUIActivator.getDefault().getBundle()
										.getSymbolicName(),
								IStatus.ERROR,
								"The error was detected when creating the sql node image handler for " + nodeClazz , //$NON-NLS-1$
								e);
						SQLUIActivator.getDefault().getLog().log(status);
						continue;
					}
				}
			}
		}
	}
	
	public INodesImageHandler getImageHandler(Node node)
	{
		INodesImageHandler handler = null;
		Class nodeClazz = node.getClass();
		while(nodeClazz != null)
		{
			String clazzName = nodeClazz.getName();
			handler = (INodesImageHandler)_nodeImageHandlerMap.get(clazzName);
			if(handler != null)
			{
				break;
			}
			nodeClazz = nodeClazz.getSuperclass();
		}
		return handler; 
	}
	
	public Image getNodeImage(Node node)
	{
		INodesImageHandler handler = getImageHandler(node);
		if(handler != null)
		{
			return handler.getImage(node);
		}
		else
		{
			return null;
		}
	}
}
