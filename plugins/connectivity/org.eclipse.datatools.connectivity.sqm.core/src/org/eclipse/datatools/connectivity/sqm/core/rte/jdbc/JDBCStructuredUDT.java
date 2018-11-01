/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.rte.jdbc;

import java.lang.ref.SoftReference;
import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.core.util.CatalogLoaderOverrideManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCUDTAttributeLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCUDTSuperTypeLoader;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesFactory;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.impl.StructuredUserDefinedTypeImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class JDBCStructuredUDT extends StructuredUserDefinedTypeImpl implements
		ICatalogObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8270619856243796282L;
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

	public void refresh() {
		synchronized (attributesLoaded) {
			if (attributesLoaded.booleanValue()) {
				attributesLoaded = Boolean.FALSE;
				getParameterLoader().clearAttributeDefinitions(
						super.getAttributes());
			}
		}
		synchronized (superLoaded) {
			if (superLoaded.booleanValue()) {
				superLoaded = Boolean.FALSE;
				setSuper(null);
			}
		}

		RefreshManager.getInstance().referesh(this);
	}

	public EList getAttributes() {
		synchronized (attributesLoaded) {
			if (!attributesLoaded.booleanValue())
				loadAttributes();
		}
		return super.getAttributes();
	}

	protected JDBCUDTAttributeLoader createParameterLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLDataTypesFactory.eINSTANCE.getSQLDataTypesPackage().getAttributeDefinition().getInstanceClassName());
		
		if (loader != null) {
			JDBCUDTAttributeLoader parameterLoader = (JDBCUDTAttributeLoader) loader;
			parameterLoader.setCatalogObject(this);
			return parameterLoader;
		}
		return new JDBCUDTAttributeLoader(this);
	}

	protected final JDBCUDTAttributeLoader getParameterLoader() {
		if (paremeterLoaderRef == null || paremeterLoaderRef.get() == null) {
			paremeterLoaderRef = new SoftReference(createParameterLoader());
		}
		return (JDBCUDTAttributeLoader) paremeterLoaderRef.get();
	}

	private void loadAttributes() {
		try {
			super.getAttributes().addAll(
					getParameterLoader().loadAttributeDefinitions());
			attributesLoaded = Boolean.TRUE;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StructuredUserDefinedType getSuper() {
		synchronized (superLoaded) {
			if (!superLoaded.booleanValue())
				loadSuper();
		}
		return super.getSuper();
	}

	protected JDBCUDTSuperTypeLoader createSuperLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLDataTypesFactory.eINSTANCE.getSQLDataTypesPackage().getUserDefinedType().getInstanceClassName());
		
		if (loader != null) {
			JDBCUDTSuperTypeLoader superTypeLoader = (JDBCUDTSuperTypeLoader) loader;
			superTypeLoader.setCatalogObject(this);
			return superTypeLoader;
		}
		return new JDBCUDTSuperTypeLoader(this);
	}

	protected final JDBCUDTSuperTypeLoader getSuperLoader() {
		if (superLoaderRef == null || superLoaderRef.get() == null) {
			superLoaderRef = new SoftReference(createSuperLoader());
		}
		return (JDBCUDTSuperTypeLoader) superLoaderRef.get();
	}

	private void loadSuper() {
		try {
			UserDefinedType udt = getSuperLoader().loadSuperType();
			if (udt instanceof StructuredUserDefinedType) {
				setSuper((StructuredUserDefinedType) udt);
			}
			superLoaded = Boolean.TRUE;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__ATTRIBUTES:
			getAttributes();
			break;
		case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUPER:
			getSuper();
			break;
		}

		return super.eIsSet(eFeature);
	}

	private Boolean attributesLoaded = Boolean.FALSE;
	private SoftReference paremeterLoaderRef;
	private Boolean superLoaded = Boolean.FALSE;
	private SoftReference superLoaderRef;

}
