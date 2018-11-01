/*******************************************************************************
 * Copyright (c) 2008 NexB Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Anton Safonov and Ahti Kitsik - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.msft.internal.sqlserver.ddl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlScript;

public class SqlServerDdlScript extends GenericDdlScript {

    protected Vector setOptionStatements				= new Vector();
    protected Vector unsetOptionStatements				= new Vector();

    public void addSetOptionStatement(String statement) {
    	setOptionStatements.addElement(statement);
    }

    public void addUnsetOptionStatement(String statement) {
    	unsetOptionStatements.addElement(statement);
    }

	public String[] getStatements(){
		String[] stats = super.getStatements();

		ArrayList list = new ArrayList(stats.length + setOptionStatements.size() + unsetOptionStatements.size());
		list.addAll(setOptionStatements);
		list.addAll(Arrays.asList(stats));
		list.addAll(unsetOptionStatements);
		
		return (String[]) list.toArray(new String[list.size()]);
	}
}
