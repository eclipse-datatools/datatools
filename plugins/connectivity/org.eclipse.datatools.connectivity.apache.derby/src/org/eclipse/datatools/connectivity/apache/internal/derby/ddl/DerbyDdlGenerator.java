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


import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlGenerator;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;

public final class DerbyDdlGenerator extends GenericDdlGenerator implements DDLGenerator ,IExecutableExtension {
	
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		this.product = config.getAttribute("product"); //$NON-NLS-1$
		this.version = config.getAttribute("version"); //$NON-NLS-1$
		if(version.equals("10.1") || version.equals("10.2")) {  //$NON-NLS-1$ //$NON-NLS-2$
			builder = new DerbyDdlBuilder101();
		}
		else {
			builder = new DerbyDdlBuilder();			
		}
	}
	protected String[] createStatements(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor, int task) {
        DerbyDdlScript script = new DerbyDdlScript();
        if(this.builder == null) {
            this.builder = new DerbyDdlBuilder();
        }
        EngineeringOption[] options = this.getSelectedOptions(elements);        
        Iterator it = this.getAllContainedDisplayableElementSet(elements).iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(o instanceof BaseTable) {
            	if (!this.generateTables(options)) continue;
                String statement = builder.createTable((BaseTable) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCreateTableStatement(statement);
            }
            else if(o instanceof ViewTable) {
            	if (!this.generateViews(options)) continue;
                String statement = builder.createView((ViewTable) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCreateViewStatement(statement);
            }
            else if(o instanceof Procedure) {
            	if (!this.generateStoredProcedures(options)) continue;
                String statement = builder.createProcedure((Procedure) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCreateRoutineStatement(statement);
            }
            else if(o instanceof UserDefinedFunction) {
            	if (!this.generateFunctions(options)) continue;
                String statement = builder.createUserDefinedFunction((UserDefinedFunction) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCreateRoutineStatement(statement);
            }
            else if(o instanceof Trigger) {
            	if (!this.generateTriggers(options)) continue;
                String statement = builder.createTrigger((Trigger) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCreateTriggerStatement(statement);
            }
            else if(o instanceof CheckConstraint) {
            	if (!this.generateCKConstraints(options)) continue;
            	String statement = builder.addCheckConstraint((CheckConstraint) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addAlterTableAddConstraintStatement(statement);
            }
            else if(o instanceof UniqueConstraint) {
            	if (!this.generatePKConstraints(options)) continue;
                String statement = builder.addUniqueConstraint((UniqueConstraint) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addAlterTableAddConstraintStatement(statement);
            }
            else if(o instanceof ForeignKey) {
            	if (!this.generateFKConstraints(options)) continue;
                String statement = builder.addForeignKey((ForeignKey) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addAlterTableAddForeignKeyStatement(statement);
            }
            else if(o instanceof Index) {
            	if (!this.generateIndexes(options)) continue;
                String statement = builder.createIndex((Index) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addCreateIndexStatement(statement);
            }
        }
        return script.getStatements();
    }
    
	protected String[] dropStatements(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor, int task) {
        DerbyDdlScript script = new DerbyDdlScript();
        if(this.builder == null) {
            this.builder = new DerbyDdlBuilder();
        }
        EngineeringOption[] options = this.getSelectedOptions(elements);        
        Iterator it = this.getAllContainedDisplayableElementSet(elements).iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(o instanceof  BaseTable) {
            	if (!this.generateTables(options)) continue;
                String statement = builder.dropTable((BaseTable) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addDropTableStatement(statement);
            }
            else if(o instanceof ViewTable) {
            	if (!this.generateViews(options)) continue;
                String statement = builder.dropView((ViewTable) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addDropViewStatement(statement);
            }
            else if(o instanceof Procedure) {
            	if (!this.generateStoredProcedures(options)) continue;
                String statement = builder.dropProcedure((Procedure) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addDropRoutineStatement(statement);
            }
            else if(o instanceof UserDefinedFunction) {
            	if (!this.generateFunctions(options)) continue;
                String statement = builder.dropFunction((UserDefinedFunction) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addDropRoutineStatement(statement);
            }
            else if(o instanceof Trigger) {
            	if (!this.generateTriggers(options)) continue;
                String statement = builder.dropTrigger((Trigger) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addDropTriggerStatement(statement);
            }
            else if(o instanceof CheckConstraint) {
            	if (!this.generateCKConstraints(options)) continue;
                String statement = builder.dropTableConstraint((CheckConstraint) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addAlterTableDropConstraintStatement(statement);
            }
            else if(o instanceof UniqueConstraint) {
            	if (!this.generatePKConstraints(options)) continue;
                String statement = builder.dropTableConstraint((UniqueConstraint) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addAlterTableDropConstraintStatement(statement);
            }
            else if(o instanceof ForeignKey) {
            	if (!this.generateFKConstraints(options)) continue;
                String statement = builder.dropTableConstraint((ForeignKey) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addAlterTableDropForeignKeyStatement(statement);
            }
            else if(o instanceof Index) {
            	if (!this.generateIndexes(options)) continue;
                String statement = builder.dropIndex((Index) o, quoteIdentifiers, qualifyNames);
                if(statement != null) script.addDropIndexStatement(statement);
            }
        }
        return script.getStatements();
    }
    
    private String product;
    private String version;
	private DerbyDdlBuilder builder = null;
	
}

