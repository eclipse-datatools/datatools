/*******************************************************************************
 * Copyright (c) 2005, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.ddl;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

public interface OlapDdlBuilder {
	
	//	Returns a string to be added (with a checkbox) to the 'Generate DDL' wizard. When the checkbox is checked by the user the buildCreateStatement method will be subsequently called to retrieve OLAP DDL.
	public String getOption();

	//Returns a string that describes the option string returned by getOption. The string will be displayed as help for the option in the Generate DDL wizard.
	public String getOptionDescription();

	String [] buildCreateStatement(SQLObject[] objects,boolean quoteIdentifiers, boolean qualifyNames);
	String [] buildDropStatement(SQLObject[] objects,boolean quoteIdentifiers, boolean qualifyNames);
    //String [] buildCommentStatement(SQLObject[] objects,boolean quoteIdentifiers, boolean qualifyNames); //@d00058820gs
}
