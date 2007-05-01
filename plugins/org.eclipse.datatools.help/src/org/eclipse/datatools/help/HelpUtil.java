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

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
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
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.help.IContextProvider;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

public class HelpUtil
{
	public static String			HELP_KEY = "org.eclipse.ui.help"; //$NON-NLS-1$
//TODO: Replace the com.sybase... value with something appropriate to DTP
	public static String			CONTEXT_PROVIDER_KEY = "com.sybase.stf.help.context.provider"; //$NON-NLS-1$
	
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
						Object		context = provider.getContext(event.widget);
						
						if (context != null) {
							event.widget.setData(HELP_KEY, context);
						}
					}
				}
			}
		}
	}
	
	private static HelpListener getHelpListener()
	{
		if (_helpListener == null)
		{
			_helpListener = new HelpUtilHelpListener();
		}
		return _helpListener;
	}
	
	public static void setHelp(Control control, String contextId)
	{
		if (control != null) {
			control.removeHelpListener(getHelpListener());
			control.addHelpListener(getHelpListener());
		}
        PlatformUI.getWorkbench().getHelpSystem().setHelp(control, contextId);
	}
	
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
	
//	TODO: Replace the com.sybase... value with something appropriate to DTP	
	/**
	 * @param helpKey 
	 * @param bundleID e.g. "com.sybase.stf.service.framework.ServiceFrameworkContextIds"
	 * @return
	 */
	public static String getContextId(String helpKey, String helpPluginID)
	{
		return getHelpString(helpKey, helpPluginID, "contextIds"); //$NON-NLS-1$
	}

//	TODO: Replace the com.sybase... value with something appropriate to DTP
	/**
	 * @param helpKey 
	 * @param bundleID e.g. "com.sybase.stf.service.framework.ServiceFrameworkSearchExpressions"
	 * @return
	 */
	public static String getSearchExpression(String helpKey, String helpPluginID)
	{
		return getHelpString(helpKey, helpPluginID, "searchExpressions"); //$NON-NLS-1$
	}
	
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
				String		propertiesFiles[] = getPropertiesFiles(helpPluginID, bundleType);
				
				properties = new Properties[propertiesFiles.length];
				for (int i = 0; i < propertiesFiles.length; i++)
				{
					String file = new Path(propertiesFiles[i]).toOSString();
					FileInputStream	fis = new FileInputStream(file);
					properties[i] = new Properties();
					properties[i].load(fis);
				}
    			_properties.put(helpPluginID + ';' + bundleType, properties);
			}
			String		bundleString = null;
			
			for (int i = 0; bundleString == null && i < properties.length; i++)
			{
				bundleString = properties[i].getProperty(helpKey);
			}
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
	
	private static String[] getPropertiesFiles(String pluginIDToMatch, String bundleType)
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
					URL		propertiesFileURL = helpPluginBundle.getResource(propertiesFile);
					propertiesFileURL = FileLocator.resolve(propertiesFileURL);
					propertiesFile = propertiesFileURL.getFile();
					if (propertiesFile.charAt(0) == '/')
					{
						propertiesFile = propertiesFile.substring(1);
					}
					propertiesFiles.add(propertiesFile);
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
			}
		}
		return (String[])propertiesFiles.toArray(new String[propertiesFiles.size()]);
	}
}