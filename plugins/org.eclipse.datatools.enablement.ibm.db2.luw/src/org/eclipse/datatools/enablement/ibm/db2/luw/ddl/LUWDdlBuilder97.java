/*******************************************************************************
 * Copyright (c) 2011, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.ddl;

import org.eclipse.datatools.modelbase.sql.schema.Sequence;

public class LUWDdlBuilder97 extends LUWDdlBuilder95{

	public LUWDdlBuilder97(){
	}
	public LUWDdlBuilder97(LUWDdlGenerator generator){
		super(generator);
	}

    public String commentOn(Sequence sequence, boolean quoteIdentifiers, boolean qualifyNames) {
        String comment = sequence.getDescription();
        if(comment == null || comment.length() == 0) {
            return null;
        }

        return COMMENT + SPACE + ON + SPACE + SEQUENCE + SPACE + getName(sequence, quoteIdentifiers,qualifyNames) + SPACE + IS
        	+ NEWLINE + this.getSingleQuotedString(comment);
    }

}
