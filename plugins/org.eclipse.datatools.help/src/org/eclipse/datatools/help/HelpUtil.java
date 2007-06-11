/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.datatools.help;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.MissingResourceException;
import java.util.Properties;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

public class HelpUtil
{
	public static String			HELP_KEY = "org.eclipse.ui.help"; //$NON-NLS-1$

	public static String			CONTEXT_PROVIDER_KEY = "org.eclipse.datatools.help.context.provider"; //$NON-NLS-1$
	
	private static HelpListener		_helpListener = null;
	
	// To prevent this class from being instantiated
	private HelpUtil()
	{
	}
	
	private static class HelpUtilHelpListener implements HelpListener {
		public void helpRequested(HelpEvent event) {
			if (event.widget != null && !event.widget.isDisposed()) {
				Widget				widget = event.widget;
				IContextProvider 	provider = null;
				
				for (; widget != null && provider == null;) {
					if (widget instanceof IAdaptable) {
						provider = (IContextProvider)((IAdaptable)widget).getAdapter(IContextProvider.class);
					} else if (widget instanceof IContextProvider) {
						provider = (IContextProvider)widget;
					} else if (widget.getData(CONTEXT_PROVIDER_KEY) != null
										&& widget.getData(CONTEXT_PROVIDER_KEY) instanceof IContextProvider) {
						provider = (IContextProvider)widget.getData(CONTEXT_PROVIDER_KEY);
					}
					if (widget instanceof Control) {
						widget = ((Control)widget).getParent();
					} else {
						break;
					}
				}
				if (provider != null) {
					if ((provider.getContextChangeMask() & IContextProvider.SELECTION) != 0) {
						IContext		context = HelpSystem.getContext(getHelpKey(event.widget));
						
						if (context != null) {
							// determine a location in the upper right corner of the
							// widget
							Point point = computePopUpLocation(event.widget.getDisplay());
							// display the help
							PlatformUI.getWorkbench().getHelpSystem().displayContext(context, point.x, point.y);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Determines the location for the help popup shell given the widget which
	 * orginated the request for help.
	 * 
	 * @param display
	 *            the display where the help will appear
	 */
	private static Point computePopUpLocation(Display display) {
		Point point = display.getCursorLocation();
		return new Point(point.x + 15, point.y);
	}
	
	/**
	 * Returns the help listener
	 * @return
	 */
	private static HelpListener getHelpListener()
	{
		if (_helpListener == null)
		{
			_helpListener = new HelpUtilHelpListener();
		}
		return _helpListener;
	}
	
	/**
	 * Sets the help for a particular control
	 * @param control
	 * @param contextId
	 */
	public static void setHelp(Control control, String contextId)
	{
		if (control != null) {
			control.removeHelpListener(getHelpListener());
			control.addHelpListener(getHelpListener());
		}
		control.setData(HELP_KEY, contextId);
	}
	
	/**
	 * Retrieves the help key back from a particular control
	 * @see setHelp
	 * @param target
	 * @return
	 */
	public static String getHelpKey(Object target)
	{
		if (target instanceof Control)
		{
			for (Control control = (Control)target; control != null; control = control.getParent())
			{
				Object		contextId = control.getData(HELP_KEY);
				
				if (contextId != null && contextId instanceof String)
				{
					return (String)contextId;
				}
			}
		}
		return null;
	}
	
	/**
	 * @param helpKey 
	 * @param helpPluginID e.g. "org.eclipse.datatools.connectivity.ui"
	 * @return
	 */
	public static String getContextId(String helpKey, String helpPluginID)
	{
		return getHelpString(helpKey, helpPluginID, "contextIds"); //$NON-NLS-1$
	}

	/**
	 * @param helpKey 
	 * @param helpPluginID e.g. "org.eclipse.datatools.connectivity.ui"
	 * @return
	 */
	public static String getSearchExpression(String helpKey, String helpPluginID)
	{
		return getHelpString(helpKey, helpPluginID, "searchExpressions"); //$NON-NLS-1$
	}
	
	/**
	 * Retrieves the help string from the bundled properties
	 * @param helpKey
	 * @param helpPluginID
	 * @param bundleType
	 * @return
	 */
	private static String getHelpString(String helpKey, String helpPluginID, String bundleType)
	{
		if (helpKey == null)
		{
			return null;
		}
		Properties properties[] = (Properties[])_properties.get(helpPluginID + ';' + bundleType);
		try
		{
			if (properties == null)
			{
				URLConnection		propertiesFiles[] = getPropertiesFiles(helpPluginID, bundleType);
				
				properties = new Properties[propertiesFiles.length];
				for (int i = 0; i < propertiesFiles.length; i++)
				{
					URLConnection connection = (URLConnection) propertiesFiles[i];
					InputStream is = connection.getInputStream();
					properties[i] = new Properties();
					properties[i].load(is);
				}
    			_properties.put(helpPluginID + ';' + bundleType, properties);
			}
			String		bundleString = null;
			
			for (int i = 0; bundleString == null && i < properties.length; i++)
			{
				bundleString = properties[i].getProperty(helpKey);
			}
			if (bundleString != null && bundleString.length() > 0 && bundleString.indexOf(".") == -1) {
				bundleString = helpPluginID + "." + bundleString;
			}
			if (bundleString != null && bundleString.trim().length() == 0)
				bundleString = null;
			return bundleString;
		}
		catch (MissingResourceException e)
		{
			return null;
		}
		catch (IOException e)
		{
			return null;
		}
	}
	
	private static HashMap	_properties = new HashMap();
	
	/**
	 * Retrieves URLConnections to jarred doc context plugins to retrieve the appropriate properties files
	 * @param pluginIDToMatch
	 * @param bundleType
	 * @return
	 */
	private static URLConnection[] getPropertiesFiles(String pluginIDToMatch, String bundleType)
	{
		IExtensionPoint exp = Platform.getExtensionRegistry().getExtensionPoint("org.eclipse.datatools.help", "helpKeyProperties"); //$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] exts = exp.getExtensions();
		ArrayList propertiesFiles = new ArrayList();
		for (int index = 0, count = exts.length; index < count; ++index)
		{
			for (Iterator it = Arrays.asList(exts[index].getConfigurationElements()).iterator(); it.hasNext();)
			{
				IConfigurationElement elem = (IConfigurationElement) it.next();
				if (!elem.getName().equals(bundleType))
				{
					continue;
				}
				String	pluginID = elem.getAttribute("plugin"); //$NON-NLS-1$
				if (!pluginID.equals(pluginIDToMatch))
				{
					continue;
				}
				String	propertiesFile = elem.getAttribute("file"); //$NON-NLS-1$
				
				try
				{
					String	helpPluginID = exts[index].getNamespaceIdentifier();
					Bundle	helpPluginBundle = Platform.getBundle(helpPluginID);
					if (helpPluginBundle != null) {
						URL		propertiesFileURL = helpPluginBundle.getResource(propertiesFile);
						propertiesFileURL = FileLocator.resolve(propertiesFileURL);
						URLConnection connection = propertiesFileURL.openConnection();
						propertiesFiles.add(connection);
					}
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
			}
		}
		return (URLConnection[])propertiesFiles.toArray(new URLConnection[propertiesFiles.size()]);
	}
}