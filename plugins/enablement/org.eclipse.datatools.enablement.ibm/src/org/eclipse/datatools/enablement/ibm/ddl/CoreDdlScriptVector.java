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
package org.eclipse.datatools.enablement.ibm.ddl;

import java.util.Collection;
import java.util.TreeSet;
import java.util.Vector;

import org.eclipse.datatools.enablement.ibm.util.IPreorderedStatementList;

/**
 * CoreDdlScriptVector collects DDL statements which can be added
 * in current order, or ordered lexicographically as they are added.
 */
public class CoreDdlScriptVector extends Vector {

	/* Perform a sort of the elements of the input collection
	 * prior to adding them to the underlying Vector.
	 * @see java.util.Vector#addAll(java.util.Collection)
	 */
	public synchronized boolean addAll(Collection collection) {
		TreeSet set = new TreeSet(collection);
		return super.addAll(set);
	}

	public synchronized boolean addAll(Collection collection, boolean sort) {
		if(sort){
			TreeSet set = new TreeSet(collection);
			set.addAll(collection);
			return super.addAll(set);
		}else{
			return super.addAll(collection);
		}
	}

	/**
	 * This method is used for adding groups of statement sets 
	 * which must retain their current order.
	 * @param list An IPreorderedStatementList subclass instance
	 * which has some order.
	 * @return boolean true if this invocation altered the contents
	 * of this CoreDdlScriptVector.
	 */
	public synchronized boolean addAll(IPreorderedStatementList list) {
			return super.addAll(list.getAll());
	}
}
