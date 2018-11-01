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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType;
import org.eclipse.datatools.modelbase.sql.tables.ActionTimeType;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;

public class DerbyDdlBuilder101 extends DerbyDdlBuilder{
	protected final static String NO_CASCADE          = "NO CASCADE"; //$NON-NLS-1$

    public String createTrigger(Trigger trigger, boolean quoteIdentifiers, boolean qualifyNames) {
        String statement = CREATE + SPACE + TRIGGER + SPACE + getName(trigger, quoteIdentifiers, qualifyNames) + SPACE;

        final ActionTimeType actionTime = trigger.getActionTime();
        if(actionTime == ActionTimeType.AFTER_LITERAL) {
            statement += AFTER;
        }
        else if(actionTime == ActionTimeType.BEFORE_LITERAL) {
            statement += NO_CASCADE + SPACE + BEFORE;
        }
        else if(actionTime == ActionTimeType.INSTEADOF_LITERAL) {
            statement += INSTEAD_OF;
        }
	    statement += SPACE;

        if(trigger.isDeleteType()) {
    	    statement += DELETE;
    	}
    	else if(trigger.isInsertType()) {
    	    statement += INSERT;
    	}
    	else if(trigger.isUpdateType()) {
    	    statement += UPDATE;
    	    Collection updateColumns = trigger.getTriggerColumn();
            if(!updateColumns.isEmpty()) {
                statement += SPACE + OF + SPACE ;
                Iterator it = updateColumns.iterator();
                while(it.hasNext()) {
                    Column column = (Column) it.next();
                    statement += column.getName();
                    if(it.hasNext()) {
                        statement += COMMA + SPACE;
                    }
                }
            }
    	}
    	
        statement += SPACE + ON + SPACE + getName(trigger.getSubjectTable(), quoteIdentifiers, qualifyNames) + NEWLINE;

        final String newRow = trigger.getNewRow();
        final String oldRow = trigger.getOldRow();
        final String newTable = trigger.getNewTable();
        final String oldTable = trigger.getOldTable();

        if(newRow != null && newRow.length() != 0) {
            statement += REFERENCING + SPACE + NEW + SPACE + AS + SPACE + newRow + NEWLINE;
        }
        if(oldRow != null && oldRow.length() != 0) {
            statement += REFERENCING + SPACE + OLD + SPACE + AS + SPACE + oldRow + NEWLINE;
        }

        if(trigger.getActionGranularity() == ActionGranularityType.ROW_LITERAL) {
            statement += FOR + SPACE + EACH + SPACE + ROW + SPACE + MODE_DB2SQL + NEWLINE;
    	}
        else {
            statement += FOR + SPACE + EACH + SPACE + STATEMENT + SPACE + MODE_DB2SQL + NEWLINE;
    	}

        Iterator it = trigger.getActionStatement().iterator();
        while(it.hasNext()) {
            SQLStatement s = (SQLStatement) it.next();
            statement += s.getSQL();
        }

        return statement;
    }
}
