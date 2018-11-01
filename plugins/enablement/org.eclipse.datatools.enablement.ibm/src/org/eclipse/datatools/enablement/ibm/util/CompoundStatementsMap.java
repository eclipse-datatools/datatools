/*******************************************************************************
 * Copyright (c) 2009, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;

/**
 * This class should be used when a requested action will result in multiple
 * DDL statements which must retain their relative ordering.  This class extends
 * The TreeMap class, and requires a Key of type String, and a Value of type
 * Vector<String>.  The key will be used to sort the Vectors of compound DDL
 * statements relative to each other.  The statements within each Vector will
 * not be sorted, but will retain the order in which they were added to the
 * Vector.
 * If the Value is null, the retrieval behavior of the getAll() method will
 * assume the Value is equivalent to a Vector with a single String element 
 * equal to its Key.
 * 
 * @author DonClare
 *
 */
public class CompoundStatementsMap extends TreeMap<String,Vector<String>> 
		implements IPreorderedStatementList {

	public Vector<String> getAll() {
		Vector<String> all = new Vector<String>();
		Iterator<String> it = keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (get(key) == null) all.add(key);
			else all.addAll(get(key));
		}
//		Iterator<Vector<String>> it = values().iterator();
//		while (it.hasNext()) {
//			all.addAll((Vector<String>)it.next());
//		}
		return all;
	}
}
