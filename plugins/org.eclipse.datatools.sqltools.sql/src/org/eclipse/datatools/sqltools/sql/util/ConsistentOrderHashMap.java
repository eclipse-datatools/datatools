/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * This class is just for keeping the key order when get values.
 * 
 * @author juewu
 *
 * @param <K>
 * @param <V>
 */
public class ConsistentOrderHashMap<K, V> extends HashMap<K, V> 
{
	private static final long serialVersionUID = -6136589405178747079L;

	/**
	 * Keep the order of keys.
	 */
	private List<K> _orderedKeyList = new ArrayList<K>();

	public V put(K key, V value) 
	{
		_orderedKeyList.add(key);

		return super.put(key, value);
	}

	/**
	 * Get values according to the specific order of keys.
	 */
	public Collection<V> values() 
	{
		List<V> valueList = new ArrayList<V>(_orderedKeyList.size());

		for(K tempKey : _orderedKeyList) 
		{
			valueList.add(get(tempKey));
		}

		return valueList;
	}
}
