/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.apache.internal.derby.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.apache.internal.derby.DerbyPlugin;
import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader;
import org.eclipse.datatools.modelbase.sql.routines.DataAccess;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

import com.ibm.icu.util.StringTokenizer;

public class DerbyRoutineLoader extends JDBCRoutineLoader {

	private String currentSchema;

	public DerbyRoutineLoader(ICatalogObject catalogObject) {
		super(catalogObject);
		this.setProcedureFactory(new DerbyProcedureFactory(catalogObject));
		this.setUserDefinedFunctionFactory(new DerbyUserDefinedFunctionFactory(catalogObject));
	}

	public DerbyRoutineLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider,
			IRoutineFactory udfFactory, IRoutineFactory spFactory) {
		super(catalogObject, connectionFilterProvider, udfFactory, spFactory);
		this.setProcedureFactory(new DerbyProcedureFactory(catalogObject));
		this.setUserDefinedFunctionFactory(new DerbyUserDefinedFunctionFactory(catalogObject));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader#createResultSet()
	 */
	protected ResultSet createResultSet() throws SQLException {
		Schema schema = (Schema) getCatalogObject();
		String query = "SELECT ALIAS AS " + COLUMN_PROCEDURE_NAME + ",ALIASTYPE," + //$NON-NLS-1$ //$NON-NLS-2$
				" CAST(NULL AS VARCHAR(1)) AS REMARKS, ALIAS, JAVACLASSNAME, ALIASINFO " + //$NON-NLS-1$
				" FROM SYS.SYSALIASES A,SYS.SYSSCHEMAS B" + //$NON-NLS-1$
				" WHERE A.ALIASTYPE IN ('P','F')" + //$NON-NLS-1$
				" AND A.SCHEMAID=B.SCHEMAID" + //$NON-NLS-1$
				" AND B.SCHEMANAME='" + schema.getName() + "'"; //$NON-NLS-1$//$NON-NLS-2$
		if (getJDBCFilterPattern() != null
				&& getJDBCFilterPattern().length() > 0) {
			String filter = " AND ALIAS LIKE " + getJDBCFilterPattern();//$NON-NLS-1$
			query = query + filter;
		}

		Statement s = getCatalogObject().getConnection().createStatement();
		currentSchema = DerbySchemaLoader.setSchema(s, "SYS");
		ResultSet r = s.executeQuery(query);
		return r;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader#closeResultSet(java.sql.ResultSet)
	 */
	protected void closeResultSet(ResultSet rs) {
		Statement s = null;
		try {
			s = rs.getStatement();
		} catch (SQLException e) {
		}
		
		super.closeResultSet(rs);
		
		try {
			DerbySchemaLoader.setSchema(s, currentSchema);
		} catch (SQLException e) {
		}
	}

	protected boolean isProcedure(ResultSet rs) throws SQLException {
		String type      = rs.getString("ALIASTYPE"); //$NON-NLS-1$
		if(type.equals("P"))  //$NON-NLS-1$
			return true;
		return false;
	}

	public static class DerbyProcedureFactory extends ProcedureFactory {
		
		private ICatalogObject catalogObject;
		
		public DerbyProcedureFactory(ICatalogObject catalogObject) {
			this.catalogObject = catalogObject;
		}

		protected Routine newRoutine() {
			return new DerbyCatalogProcedure();
		}

		public void initialize(Routine routine, ResultSet rs) throws SQLException {
			super.initialize(routine, rs);
			setRestOfMetaData(routine,rs,catalogObject);
		}

	}
	
	public static class DerbyUserDefinedFunctionFactory extends UserDefinedFunctionFactory {
		
		private ICatalogObject catalogObject;
		
		public DerbyUserDefinedFunctionFactory(ICatalogObject catalogObject) {
			this.catalogObject = catalogObject;
		}

		protected Routine newRoutine() {
			return new DerbyCatalogUserDefinedFunction();
		}

		public void initialize(Routine routine, ResultSet rs) throws SQLException {
			super.initialize(routine, rs);
			setRestOfMetaData(routine,rs,catalogObject);
		}

	}

	private static final String rsKeyword = "RESULT SETS"; //$NON-NLS-1$

	private static void setRestOfMetaData(Routine routine, ResultSet rs,
			ICatalogObject catalogObject) {
		routine.setLanguage("JAVA"); //$NON-NLS-1$
		routine.setParameterStyle("JAVA"); //$NON-NLS-1$

		try {

			// Set SQL Data Access
			String aliasInfoString = rs.getString("ALIASINFO"); //$NON-NLS-1$
			setSqlDataAccess(routine, aliasInfoString);

			// It appears that the aliasInfo object is not returned in the
			// resultset
			// if the connection is made using the DB2 Universal Driver.
			// So we will parse the string instead of executing the methods on
			// the object to obtain the information needed.
			// 
			int index = aliasInfoString.lastIndexOf(rsKeyword);
			if (index != -1) {
				StringTokenizer tokenizer = new StringTokenizer(aliasInfoString
						.substring(index + rsKeyword.length()));
				String nextToken = tokenizer.nextToken();

				// If for some reason, the next token is not a number, just
				// ignore it.
				try {
					int resultSets = Integer.parseInt(nextToken);
					((Procedure) routine).setMaxResultSets(resultSets);
				}

				catch (Exception e) {
				}
			}

			// Set External name
			index = aliasInfoString.indexOf("("); //$NON-NLS-1$
			String methodName = aliasInfoString.substring(0, index);
			routine
					.setExternalName(rs.getString("JAVACLASSNAME") + '.' + methodName); //$NON-NLS-1$
			loadSource(routine, aliasInfoString, catalogObject
					.getCatalogDatabase());

		}
		catch (Exception e) {
			Status status = new Status(
					Status.ERROR,
					DerbyPlugin.getDefault().getBundle().getSymbolicName(),
					Status.ERROR,
					"###Error..org.eclipse.datatools.connectivity.internal.derby.catalog.DerbyCatalogProcedure.load", //$NON-NLS-1$
					e);
			DerbyPlugin.getDefault().getLog().log(status);
		}
	}

	private static void setSqlDataAccess(Routine routine, String aliasInfo) {
		int index = aliasInfo.indexOf("NO SQL"); //$NON-NLS-1$
		if (index != -1) {
			routine.setSqlDataAccess(DataAccess.NO_SQL_LITERAL);
		}
		else {
			index = aliasInfo.indexOf("MODIFIES SQL DATA"); //$NON-NLS-1$
			if (index != -1) {
				routine.setSqlDataAccess(DataAccess.MODIFIES_SQL_DATA_LITERAL);

			}
			else {
				index = aliasInfo.indexOf("CONTAINS SQL"); //$NON-NLS-1$
				if (index != -1) {
					routine.setSqlDataAccess(DataAccess.CONTAINS_SQL_LITERAL);

				}
				else {
					index = aliasInfo.indexOf("READS SQL DATA"); //$NON-NLS-1$
					if (index != -1) {
						routine
								.setSqlDataAccess(DataAccess.READS_SQL_DATA_LITERAL);
					}
				}
			}
		}
	}

	private static void loadSource(Routine routine, String aliasInfo,
			Database database) {
		int index = aliasInfo.indexOf("("); //$NON-NLS-1$
		String body = aliasInfo.substring(index);
		body = body.replaceAll("IN ", ""); //$NON-NLS-1$ //$NON-NLS-2$
		final DatabaseDefinition definition = RDBCorePlugin.getDefault()
				.getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = definition
				.getDataModelElementFactory();

		Source s = (Source) factory.create(SQLRoutinesPackage.eINSTANCE
				.getSource());
		s.setBody(body);
		routine.setSource(s);
	}

}
