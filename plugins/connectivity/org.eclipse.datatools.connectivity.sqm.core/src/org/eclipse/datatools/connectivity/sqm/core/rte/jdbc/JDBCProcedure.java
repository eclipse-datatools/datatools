/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.rte.jdbc;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.core.util.CatalogLoaderOverrideManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCProcedureColumnLoader;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesFactory;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.impl.ProcedureImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class JDBCProcedure extends ProcedureImpl implements ICatalogObject {

	private static final long serialVersionUID = -734520220022088696L;
	
	public void refresh() {
		synchronized (parametersLoaded) {
			if (parametersLoaded.booleanValue()) {
				parametersLoaded = Boolean.FALSE;
			}
		}
		synchronized (resultTablesLoaded) {
			if (resultTablesLoaded.booleanValue()) {
				resultTablesLoaded = Boolean.FALSE;
			}
		}

		RefreshManager.getInstance().referesh(this);
	}

	public Database getCatalogDatabase() {
		return getSchema().getCatalog().getDatabase();
	}

	public Connection getConnection() {
		Database db = getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}

	public EList getParameters() {
		synchronized (parametersLoaded) {
			if (!parametersLoaded.booleanValue())
				loadParameters();
		}
		return super.getParameters();
	}

	public EList getResultSet() {
		synchronized (resultTablesLoaded) {
			if (!resultTablesLoaded.booleanValue())
				loadRoutineResultTables();
		}
		return super.getResultSet();
	}

	protected JDBCProcedureColumnLoader createParameterLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader = 
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLRoutinesFactory.eINSTANCE.createParameter().eClass().getInstanceClassName());
		
		if (loader != null) {
			JDBCProcedureColumnLoader procedureColumnLoader = (JDBCProcedureColumnLoader) loader;
			procedureColumnLoader.setCatalogObject(this);
			return procedureColumnLoader;
		}
		return new JDBCProcedureColumnLoader(this);
	}

	protected final JDBCProcedureColumnLoader getParameterLoader() {
		if (paremeterLoaderRef == null || paremeterLoaderRef.get() == null) {
			paremeterLoaderRef = new SoftReference(createParameterLoader());
		}
		return (JDBCProcedureColumnLoader) paremeterLoaderRef.get();
	}

	private void loadParameters() {
		parametersLoaded = Boolean.TRUE;

		boolean deliver = eDeliver();
		try {
			List parametersContainer = super.getParameters();
			List existingParameters = new ArrayList(parametersContainer);

			eSetDeliver(false);

			parametersContainer.clear();

			getParameterLoader().loadParameters(parametersContainer, existingParameters);
			getParameterLoader().clearColumns(existingParameters);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			eSetDeliver(deliver);
		}
	}
	
	private void loadRoutineResultTables() {
		resultTablesLoaded = Boolean.TRUE;

		boolean deliver = eDeliver();
		try {
			List resultTablesContainer = super.getResultSet();

			eSetDeliver(false);

			resultTablesContainer.clear();

			resultTablesContainer.addAll(getParameterLoader().loadRoutineResultTables());
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			eSetDeliver(deliver);
		}
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case SQLRoutinesPackage.PROCEDURE__PARAMETERS:
			getParameters();
			break;
		case SQLRoutinesPackage.PROCEDURE__MAX_RESULT_SETS:
			getMaxResultSets();
			break;
		}

		return super.eIsSet(eFeature);
	}

	private Boolean parametersLoaded = Boolean.FALSE;
	private Boolean resultTablesLoaded = Boolean.FALSE;
	private SoftReference paremeterLoaderRef;

}
