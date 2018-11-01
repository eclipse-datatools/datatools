/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sybase.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hao-yue
 * 
 */
public class SystemTableParameters 
{
	private static SystemTableParameters _instance;
	private static Map _map = null;
	
	private SystemTableParameters()
	{
		
	}
	
	public synchronized static SystemTableParameters getInstance()
	{
		if(_instance == null)
		{
			_instance = new SystemTableParameters();
			_map = new HashMap();
		}
		return _instance;
	}
	
	public Map getMap()
	{
		return _map;
	}
	
	public void setMap(Map map)
	{
		_map = map;
	}
}
