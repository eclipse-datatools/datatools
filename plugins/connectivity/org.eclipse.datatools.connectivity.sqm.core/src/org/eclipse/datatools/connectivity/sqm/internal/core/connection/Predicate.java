/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.internal.core.connection;

import java.io.Serializable;

public class Predicate implements Serializable {

	private static final long serialVersionUID = 2754142397787308454L;
	private String value = "";
	private int operator;

	public Predicate(int operator, String value) {
		this.operator = operator;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public int getOperator() {
		return operator;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}
}
