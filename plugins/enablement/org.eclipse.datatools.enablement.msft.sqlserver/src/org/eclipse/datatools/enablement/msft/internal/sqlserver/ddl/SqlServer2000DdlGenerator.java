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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCProcedure;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCUserDefinedFunction;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCView;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders.SQLs;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.models.SQLServerJdbcIndex;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.models.SQLServerTrigger;
import org.eclipse.datatools.modelbase.sql.constraints.Assertion;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;

public class SqlServer2000DdlGenerator extends GenericDdlGenerator {

	private SqlServer2000DdlBuilder builder;

	public SqlServer2000DdlGenerator() {
		this.builder = new SqlServer2000DdlBuilder();
	}
	
	public String[] generateDDL(SQLObject[] elements, IProgressMonitor progressMonitor) {
		return super.generateDDL(elements, progressMonitor);
	}

	protected String[] dropStatements(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor, int task) {
		SqlServerDdlScript script = new SqlServerDdlScript();

		EngineeringOption[] options = this.getSelectedOptions(elements);        

		Set allElements = this.getAllContainedDisplayableElementSet(elements);

		builder.setElements(allElements);

		allElements = new HashSet(allElements);
		filterElements(allElements);

		try {
	        Iterator it = allElements.iterator();
	        while(it.hasNext()) {
	            Object o = it.next();
	            if(o instanceof PersistentTable) {
	            	if (!this.generateTables(options)) continue;
	                String statement = builder.dropTable((PersistentTable) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) script.addDropTableStatement(statement);
	            }
	            else if(o instanceof ViewTable) {
	            	if (!this.generateViews(options)) continue;
	                String statement = builder.dropView((ViewTable) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) script.addDropViewStatement(statement);
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
	            	if (!this.generatePKConstraints(options) || builder.isImplicitConstraint((UniqueConstraint)o)) continue;
	                String statement = builder.dropTableConstraint((UniqueConstraint) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) script.addAlterTableDropConstraintStatement(statement);
	            }
	            else if(o instanceof ForeignKey) {
	            	if (!this.generateFKConstraints(options) || builder.isImplicitConstraint((ForeignKey)o)) continue;
	                String statement = builder.dropTableConstraint((ForeignKey) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) script.addAlterTableDropForeignKeyStatement(statement);
	            }
	            else if(o instanceof Index) {
	            	if (!this.generateIndexes(options)) {
	            		continue;
	            	}
	            	if (((SQLServerJdbcIndex) o).isUniqueConstraint()) {
	            		String statement = builder.dropTableConstraint((Index) o, quoteIdentifiers, qualifyNames);
	            		if(statement != null) script.addAlterTableDropConstraintStatement(statement);
	            	} else {
	            		String statement = builder.dropIndex((Index) o, quoteIdentifiers, qualifyNames);
		                if(statement != null) script.addDropIndexStatement(statement);
	            	}
	            }
	            else if(o instanceof Procedure) {
	            	if (!this.generateStoredProcedures(options)) continue;
	                String statement = builder.dropProcedure((Procedure) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) script.addDropStoredProcedureStatement(statement);
	            }
	            else if(o instanceof UserDefinedFunction) {
	            	if (!this.generateFunctions(options)) continue;
	                String statement = builder.dropFunction((UserDefinedFunction) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) script.addDropUserDefinedFunctionStatement(statement);
	            }
	            else if(o instanceof Schema) {
	            	if (!this.generateSchemas(options)) continue;
	                String statement = builder.dropSchema((Schema) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) script.addDropSchemaStatement(statement);
	            }
	            else if(o instanceof UserDefinedType) {
	            	if (!this.generateUserDefinedTypes(options)) continue;
	                String statement = builder.dropUserDefinedType((UserDefinedType) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) script.addDropUserDefinedTypeStatement(statement);
	            }
	            else if(o instanceof Assertion) {
	            	if (!this.generateAssertions(options)) continue;
	                String statement = builder.dropAssertion((Assertion) o, quoteIdentifiers, qualifyNames);
	                if(statement != null) script.addDropAssertionStatement(statement);
	            }
	        }
		} finally {   
			builder.clear();
		}
		return script.getStatements();	
	}
	
	protected String[] createStatements(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor, int task) {
		SqlServerDdlScript script = new SqlServerDdlScript();
		EngineeringOption[] options = this.getSelectedOptions(elements);

		Set allElements = this.getAllContainedDisplayableElementSet(elements);

		builder.setElements(allElements);

		allElements = new HashSet(allElements);
		filterElements(allElements);

		try {
			String[] setOptions = builder.createSetOptions(elements);
			for (int i = 0; i < setOptions.length; i++) {
				script.addSetOptionStatement(setOptions[i]);
			}

			Iterator it = allElements.iterator();
			while (it.hasNext()) {
				Object o = it.next();

				if (o instanceof TableConstraint && allElements.contains(((TableConstraint) o).getBaseTable())) {
					// will be generated as part of table clause
					continue;
				}
				
				if (o instanceof PersistentTable) {
					if (!this.generateTables(options))
						continue;
					String statement = builder.createTable((PersistentTable) o, quoteIdentifiers, qualifyNames);
					if (statement != null)
						script.addCreateTableStatement(statement);
				} else if (o instanceof ViewTable) {
					if (!this.generateViews(options))
						continue;
					String statement = null;
					if (o instanceof JDBCView) {
						statement = executeQuery(((JDBCView) o).getConnection(), ((JDBCView) o).getName());
					}
					if (statement != null)
						script.addCreateViewStatement(statement);
				} else if (o instanceof Trigger) {
					if (!this.generateTriggers(options))
						continue;
					String statement = executeQuery(((SQLServerTrigger) o).getConnection(), ((SQLServerTrigger) o).getName());
					if (statement != null)
						script.addCreateTriggerStatement(statement);
				} else if (o instanceof CheckConstraint) {
					if (!this.generateCKConstraints(options)) {
						continue;
					}
					String statement = builder.addCheckConstraint((CheckConstraint) o, quoteIdentifiers, qualifyNames);
					if (statement != null)
						script.addAlterTableAddConstraintStatement(statement);
				} else if (o instanceof UniqueConstraint) {
					if (!this.generatePKConstraints(options) || builder.isImplicitConstraint((UniqueConstraint) o))
						continue;
					String statement = builder.addUniqueConstraint((UniqueConstraint) o, quoteIdentifiers, qualifyNames);
					if (statement != null)
						script.addAlterTableAddConstraintStatement(statement);
				} else if (o instanceof ForeignKey) {
					if (!this.generateFKConstraints(options) || builder.isImplicitConstraint((ForeignKey) o))
						continue;
					String statement = builder.addForeignKey((ForeignKey) o, quoteIdentifiers, qualifyNames);
					if (statement != null)
						script.addAlterTableAddForeignKeyStatement(statement);
				} else if (o instanceof Index) {
					if (!this.generateIndexes(options)) {
						continue;
					}
					if (((SQLServerJdbcIndex) o).isUniqueConstraint()) {
						String statement = builder.addUniqueConstraint((Index) o, quoteIdentifiers, qualifyNames);
						if (statement != null) script.addAlterTableAddConstraintStatement(statement);
					} else {
						String statement = builder.createIndex((Index) o, quoteIdentifiers, qualifyNames);
						if (statement != null) script.addCreateIndexStatement(statement);
					}
				} else if (o instanceof Procedure) {
					if (!this.generateStoredProcedures(options))
						continue;
					String statement = null; // ignore the builder as we don't have
					// the resources to build a satisfactory builder.
					if (o instanceof JDBCProcedure) {
						statement = builder.createProcedure(((JDBCProcedure) o), quoteIdentifiers, qualifyNames);
					}
					if (statement != null)
						script.addCreateStoredProcedureStatement(statement);
				} else if (o instanceof UserDefinedFunction) {
					if (!this.generateFunctions(options))
						continue;
					String statement = null; // ignore the builder as we don't have
					// the resources to build a satisfactory builder.
					if (o instanceof JDBCUserDefinedFunction) {
						statement = executeQuery(((JDBCUserDefinedFunction) o).getConnection(), ((JDBCUserDefinedFunction) o).getName());
					}

					if (statement != null)
						script.addCreateUserDefinedFunctionStatement(statement);
				} else if (o instanceof Schema) {
					if (!this.generateSchemas(options))
						continue;
					String statement = builder.createSchema((Schema) o, quoteIdentifiers, qualifyNames);
					if (statement != null)
						script.addCreateSchemaStatement(statement);
				} else if (o instanceof UserDefinedType) {
					if (!this.generateUserDefinedTypes(options))
						continue;
					String statement = builder.createUserDefinedType((UserDefinedType) o, quoteIdentifiers, qualifyNames);
					if (statement != null)
						script.addCreateUserDefinedTypeStatement(statement);
				} else if (o instanceof Assertion) {
					if (!this.generateAssertions(options))
						continue;
					String statement = builder.createAssertion((Assertion) o, quoteIdentifiers, qualifyNames);
					if (statement != null)
						script.addCreateAssertionStatement(statement);
				}
			}
			
			String[] unsetOptions = builder.createUnsetOptions(elements);
			for (int i = 0; i < unsetOptions.length; i++) {
				script.addUnsetOptionStatement(unsetOptions[i]);
			}
			
		} finally {
			builder.clear();
		}
		
		return script.getStatements();
	}

	private void filterElements(Set allElements) {
		Iterator toFilter = allElements.iterator();
		while (toFilter.hasNext()) {
			Object obj = toFilter.next();
			if (obj instanceof Index) {
				ReferenceConstraint constr = builder.findConstraintByIndex((Index) obj);
				if (constr != null) {
					toFilter.remove();
					continue;
				} 
				if (((SQLServerJdbcIndex) obj).isPrimaryKey()) {
					toFilter.remove();
					continue;
				}
			}
		}
	}
	
	private String executeQuery(Connection connection, String name) {
		try {
			PreparedStatement stmt = connection.prepareStatement(SQLs.SELECT_OBJ_BY_NAME_2000);

			stmt.setString(1, name);

			ResultSet r = stmt.executeQuery();

			StringBuffer statement = new StringBuffer();
			while(r.next()) {
				statement.append(r.getString(1));
			}
			return statement.toString();
		} catch (SQLException e) {
			// TODO log properly
			e.printStackTrace();
		}
		return null;
	}
}
