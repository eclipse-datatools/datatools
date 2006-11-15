/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.rte.jdbc;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCProcedureColumnLoader;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.impl.ProcedureImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class JDBCProcedure extends ProcedureImpl implements ICatalogObject {

	public void refresh() {
		synchronized (parametersLoaded) {
			if (parametersLoaded.booleanValue()) {
				parametersLoaded = Boolean.FALSE;
				getParameterLoader().clearColumns(super.getParameters());
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

	protected JDBCProcedureColumnLoader createParameterLoader() {
		return new JDBCProcedureColumnLoader(this);
	}

	protected final JDBCProcedureColumnLoader getParameterLoader() {
		if (paremeterLoaderRef == null || paremeterLoaderRef.get() == null) {
			paremeterLoaderRef = new SoftReference(createParameterLoader());
		}
		return (JDBCProcedureColumnLoader) paremeterLoaderRef.get();
	}

	private void loadParameters() {
		try {
			List parameters = getParameterLoader().loadColumns();
			int numParameters = parameters.size();
			if (numParameters > 0
					&& parameters.get(numParameters - 1) instanceof RoutineResultTable) {
				getResultSet().add(parameters.get(numParameters - 1));
				parameters.remove(numParameters - 1);
			}
			super.getParameters().addAll(parameters);
			parametersLoaded = Boolean.TRUE;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		if (eDerivedStructuralFeatureID(eFeature) == SQLRoutinesPackage.PROCEDURE__PARAMETERS) {
			this.getParameters();
		}

		return super.eIsSet(eFeature);
	}

	private Boolean parametersLoaded = Boolean.FALSE;
	private SoftReference paremeterLoaderRef;

}
