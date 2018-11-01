/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.apache.internal.derby.ddl;

import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlScript;

public class DerbyDdlScript extends GenericDdlScript {

    public void addCommentOnStatement(String statement) {
        this.commentOnStatements.add(statement);
    }

    public void addDropRoutineStatement(String statement) {
        this.dropRoutineStatements.add(statement);
    }

    public void addCreateRoutineStatement(String statement) {
        this.createRoutineStatements.add(statement);
    }
    
	public String[] getStatements(){
		Vector scriptVec = new Vector();
		scriptVec.addAll(dropTriggerStatements);
		scriptVec.addAll(dropRoutineStatements);
		scriptVec.addAll(dropForeignKeyStatements);
		scriptVec.addAll(dropConstraintStatements);
		scriptVec.addAll(dropIndexStatements);
		scriptVec.addAll(dropViewStatements);
		scriptVec.addAll(dropTableStatements);
		scriptVec.addAll(createTableStatements);
		scriptVec.addAll(createViewStatements);
		scriptVec.addAll(createIndexStatements);
		scriptVec.addAll(addConstraintStatements);
		scriptVec.addAll(addForeignKeyStatements);
		scriptVec.addAll(createRoutineStatements);
		scriptVec.addAll(createTriggerStatements);
		scriptVec.addAll(commentOnStatements);
		
		String[] scripts = new String[scriptVec.size()];
		scriptVec.copyInto(scripts);
		return scripts;
	}
    protected Vector commentOnStatements                    = new Vector();
    protected Vector dropRoutineStatements                  = new Vector();
    protected Vector createRoutineStatements                  = new Vector();
}

