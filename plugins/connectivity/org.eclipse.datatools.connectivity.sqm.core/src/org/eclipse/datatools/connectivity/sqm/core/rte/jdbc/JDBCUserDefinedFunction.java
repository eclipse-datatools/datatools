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
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.core.util.CatalogLoaderOverrideManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCUDFColumnLoader;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesFactory;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.impl.UserDefinedFunctionImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class JDBCUserDefinedFunction extends UserDefinedFunctionImpl implements
		ICatalogObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6800525292996291562L;
	public void refresh() {
		synchronized (parametersLoaded) {
			if (parametersLoaded.booleanValue()) {
				setReturnScalar(null);
				parametersLoaded = Boolean.FALSE;
			}
		}

		synchronized (resultTableLoaded) {
			if (resultTableLoaded.booleanValue()) {
				setReturnTable(null);
				resultTableLoaded = Boolean.FALSE;
			}
		}

		RefreshManager.getInstance().referesh(this);
	}

	public Connection getConnection() {
		Database db = getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}

	public Database getCatalogDatabase() {
		return getSchema().getCatalog().getDatabase();
	}

	public EList getParameters() {
		synchronized (parametersLoaded) {
			if (!parametersLoaded.booleanValue())
				loadParameters();
		}
		return super.getParameters();
	}

	public Parameter getReturnScalar() {
		synchronized (parametersLoaded) {
			if (!parametersLoaded.booleanValue())
				loadParameters();
		}
		return super.getReturnScalar();
	}

	public RoutineResultTable getReturnTable() {
		synchronized (resultTableLoaded) {
			if (!resultTableLoaded.booleanValue())
				loadResultTable();
		}
		return super.getReturnTable();
	}

	protected JDBCUDFColumnLoader createParameterLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader = 
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLRoutinesFactory.eINSTANCE.createParameter().eClass().getInstanceClassName());
		
		if (loader != null) {
			JDBCUDFColumnLoader udfColumnLoader = (JDBCUDFColumnLoader) loader;
			udfColumnLoader.setCatalogObject(this);
			return udfColumnLoader;
		}
		return new JDBCUDFColumnLoader(this);
	}

	protected final JDBCUDFColumnLoader getParameterLoader() {
		if (paremeterLoaderRef == null || paremeterLoaderRef.get() == null) {
			paremeterLoaderRef = new SoftReference(createParameterLoader());
		}
		return (JDBCUDFColumnLoader) paremeterLoaderRef.get();
	}

	private void loadParameters() {
		parametersLoaded = Boolean.TRUE;

		boolean deliver = eDeliver();
		try {
			List parametersContainer = super.getParameters();
			List existingParameters = new ArrayList(parametersContainer);

			eSetDeliver(false);

			parametersContainer.clear();
			setReturnScalar(null);

			getParameterLoader().loadParameters(parametersContainer, existingParameters);
			getParameterLoader().clearColumns(existingParameters);
			
			ArrayList removeList = new ArrayList();
			for (Iterator it = parametersContainer.iterator(); it.hasNext(); ) {
				Parameter p = (Parameter)it.next();
				if (p.getMode() == ParameterMode.OUT_LITERAL) {
					setReturnScalar(p);
					removeList.add(p);
					break;
				}
			}
			for (Iterator it = removeList.iterator(); it.hasNext(); ) {
				Parameter removedObject = (Parameter)it.next();
				parametersContainer.remove(removedObject);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			eSetDeliver(deliver);
		}
	}
	
	private void loadResultTable() {
		resultTableLoaded = Boolean.TRUE;

		boolean deliver = eDeliver();
		try {
			eSetDeliver(false);

			List returnTableList = getParameterLoader().loadRoutineResultTables();
			if (returnTableList.size() > 0) {
				setReturnTable((RoutineResultTable)returnTableList.get(0));
			}
			
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
		case SQLRoutinesPackage.USER_DEFINED_FUNCTION__PARAMETERS:
			getParameters();
			break;
		case SQLRoutinesPackage.USER_DEFINED_FUNCTION__RETURN_TABLE:
			getReturnTable();
			break;
		case SQLRoutinesPackage.USER_DEFINED_FUNCTION__RETURN_SCALAR:
			getReturnScalar();
			break;
		}

		return super.eIsSet(eFeature);
	}

	private Boolean parametersLoaded = Boolean.FALSE;
	private Boolean resultTableLoaded = Boolean.FALSE;
	private SoftReference paremeterLoaderRef;

}
