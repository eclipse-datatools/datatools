/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * This class is just for keeping the key order when get values.
 * 
 * @author juewu
 *
 */
public class ConsistentOrderHashMap extends HashMap
{
	private static final long serialVersionUID = -6136589405178747079L;

	/**
	 * Keep the order of keys.
	 */
	private List _orderedKeyList = new ArrayList();

	public Object put(Object key, Object value) 
	{
		_orderedKeyList.add(key);

		return super.put(key, value);
	}

	/**
	 * Get values according to the specific order of keys.
	 */
	public Collection values() 
	{
		List valueList = new ArrayList(_orderedKeyList.size());

		for(Iterator iterator = _orderedKeyList.iterator(); iterator.hasNext();) 
		{
		    Object key = iterator.next();
		    valueList.add(get(key));
		}

		return valueList;
	}
}
