/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.datatools.help;

import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;

public class ContextProviderDelegate implements IContextProvider
{
	private String			_contextHelpPluginID;
	
	public ContextProviderDelegate(String contextHelpPluginID)
	{
		_contextHelpPluginID = contextHelpPluginID;
	}
	
	public IContext getContext(Object target)
	{
		String		contextId = HelpUtil.getContextId(HelpUtil.getHelpKey(target), _contextHelpPluginID);
		
		if (contextId == null)
		{
			return null;
		}
		return HelpSystem.getContext(contextId);
	}

	public int getContextChangeMask()
	{
		return SELECTION;
	}

	public String getSearchExpression(Object target)
	{
		return HelpUtil.getSearchExpression(HelpUtil.getHelpKey(target), _contextHelpPluginID);
	}
}