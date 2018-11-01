/*******************************************************************************
 * Copyright (c) 2010, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.ddl;

import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

public class LUWDdlBuilder95 extends LUWDdlBuilder9 {

	public LUWDdlBuilder95(){
	}
	public LUWDdlBuilder95(LUWDdlGenerator generator){
		super(generator);
	}

    public String commentOn(Role role, boolean quoteIdentifiers, boolean qualifyNames) {
        String comment = role.getDescription();
        if(comment == null || comment.length() == 0) {
            return null;
        }

        return COMMENT + SPACE + ON + SPACE + ROLE + SPACE + getName(role, quoteIdentifiers) + SPACE + IS
        	+ NEWLINE + this.getSingleQuotedString(comment);
    }

	protected String getCommentType(SQLObject sqlObject,boolean quoteIdentifiers, boolean qualifyNames) {
        String comments = "";
        if (sqlObject instanceof Role) {
        	comments += ROLE + SPACE + getName((Role)sqlObject,quoteIdentifiers);
        } else {
        	comments = super.getCommentType(sqlObject, quoteIdentifiers, qualifyNames);
        }
        return comments;
    }

}
