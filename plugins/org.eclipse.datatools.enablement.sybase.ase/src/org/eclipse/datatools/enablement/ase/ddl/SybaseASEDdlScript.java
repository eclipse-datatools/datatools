/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.datatools.enablement.ase.ddl;

import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlScript;

public class SybaseASEDdlScript extends GenericDdlScript {

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

