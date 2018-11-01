/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.logging;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.ServerUIPlugin;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.ServerUIDebugOptions;

/**
 * @author ljulien
 */
public class Logger
{
	private static final ILog		logger;
	private static final Map		cachedOptions;
	private static final boolean	DEBUG;
	static
	{
		logger = ServerUIPlugin.getDefault().getLog();
		cachedOptions = new HashMap();
		DEBUG = ServerUIPlugin.getDefault().isDebugging();
	}
	private static Map getCachedOptions()
	{
		return cachedOptions;
	}
	/**
	 * @param option -
	 *            The option to consider
	 * @return - True if we should trace this option
	 */
	private static boolean shouldLog(ServerUIDebugOptions option)
	{
		Boolean value = (Boolean) getCachedOptions().get(option.getValue());
		if (value == null)
		{
			value = new Boolean(Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(option.getValue())));
			getCachedOptions().put(option.getValue(), value);
		}
		return value.booleanValue();
	}
	/**
	 * @param owner -
	 *            The object where the exception has been thrown from -> pass
	 *            in "this"
	 * @param exception -
	 *            The exception to report
	 */
	public static void log(Object owner, Throwable exception, ServerUIDebugOptions option)
	{
		String className = owner != null ? owner.getClass().getName() : null;
		logger.log(new Status(IStatus.ERROR, ServerUIPlugin.getDefault().getBundle().getSymbolicName(),
				IStatus.ERROR, "Detected in Class : " + className, exception)); //$NON-NLS-1$
	}
}
