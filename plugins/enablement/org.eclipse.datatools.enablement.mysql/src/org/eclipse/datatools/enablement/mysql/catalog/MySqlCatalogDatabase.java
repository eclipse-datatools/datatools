/*******************************************************************************
 * Copyright (c) 2005, 2009 Versant Corporation and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License 2.0 which accompanies this distribution, and is
 * available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Versant Corporation - initial API and implementation
 * 		brianf - updates to make catalog loading work with filtering
 ******************************************************************************/
package org.eclipse.datatools.enablement.mysql.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCDatabase;
import org.eclipse.datatools.connectivity.sqm.core.util.CatalogLoaderOverrideManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.enablement.mysql.catalog.loaders.MySqlAuthorizationIdentifierLoader;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;

/**
 * This is the Database implementation, and contains schemas (MySql does not
 * have schemas)
 */
public class MySqlCatalogDatabase extends JDBCDatabase {
	
	private Boolean schemasLoaded = Boolean.FALSE;
	private Boolean catalogsLoaded = Boolean.FALSE;
	private Boolean authorizationIdsLoaded = Boolean.FALSE;

	private transient SoftReference authorizationIdLoaderRef;

	public MySqlCatalogDatabase(Connection connection){
		super(connection);
	}

	public void refresh() {
		synchronized (schemasLoaded) {
			if (schemasLoaded.booleanValue()) {
				schemasLoaded = Boolean.FALSE;
			}
		}

		synchronized (catalogsLoaded) {
			if (catalogsLoaded.booleanValue()) {
				catalogsLoaded = Boolean.FALSE;
			}
		}

		synchronized (authorizationIdsLoaded) {
			if (authorizationIdsLoaded.booleanValue()) {
				authorizationIdsLoaded = Boolean.FALSE;
			}
		}

		super.refresh();
	}

	public EList getSchemas() {
		synchronized (schemasLoaded) {
			if(!schemasLoaded.booleanValue()) { 
				if (schemas == null) {
					schemas = new EObjectWithInverseResolvingEList(Schema.class, this,
							SQLSchemaPackage.DATABASE__SCHEMAS,
							SQLSchemaPackage.SCHEMA__DATABASE);
					Schema schema = new MySqlCatalogSchema();
					schema.setName(getName());
					schemas.add(schema);
					schemasLoaded = Boolean.TRUE;
				}
				return this.schemas;
			}
		}
		return super.getSchemas();
	}

	public EList getCatalogs() {
		synchronized (catalogsLoaded) {
			if(!catalogsLoaded.booleanValue()) { 
				if (catalogs == null) {
					catalogs = new EObjectWithInverseResolvingEList(Schema.class, this,
									SQLSchemaPackage.DATABASE__CATALOGS,
									SQLSchemaPackage.SCHEMA__DATABASE);
					catalogsLoaded = Boolean.TRUE;
				}
			}
		}
		return catalogs;
	}

	public EList getAuthorizationIds() {
		synchronized (this.authorizationIdsLoaded) {
			if (!authorizationIdsLoaded.booleanValue())
				this.loadAuthorizationIdentifiers();
		}

		return super.getAuthorizationIds();
	}

	private void loadAuthorizationIdentifiers() {
		boolean deliver = eDeliver();
		try {
			List container = super.getAuthorizationIds();
			List existingAuthorizationIds = new ArrayList(container);

			eSetDeliver(false);

			container.clear();
			getAuthorizationIdentifierLoader().loadAuthorizationIdentifiers(container, existingAuthorizationIds);
			getAuthorizationIdentifierLoader().clearAuthorizationIdentifiers(existingAuthorizationIds);

			authorizationIdsLoaded = Boolean.TRUE;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			eSetDeliver(deliver);
		}
	}

	protected final MySqlAuthorizationIdentifierLoader getAuthorizationIdentifierLoader() {
		// cache the AuthorizationIdentifierLoader for better performance
		if (authorizationIdLoaderRef == null || authorizationIdLoaderRef.get() == null) {
			authorizationIdLoaderRef = new SoftReference(createAuthorizationIdentifierLoader());
		}

		return (MySqlAuthorizationIdentifierLoader) authorizationIdLoaderRef.get();
	}

	/**
	 * Creates and returns an instance of the AuthorizationIdentifierLoader. By default an instance of the
	 * <code>MySqlAuthorizationIdentifierLoader</code> is returned. This behavior can be changed by providing an
	 * <code>overrideLoader</code> using the eclass org.eclipse.datatools.modelbase.sql.accesscontrol.
	 * AuthorizationIdentifier.
	 * 
	 * @return An instance of MySqlAuthorizationIdentifierLoader.
	 */
	private MySqlAuthorizationIdentifierLoader createAuthorizationIdentifierLoader() {
		// get the database definition for the actual database
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry()
				.getDefinition(this.getCatalogDatabase());

		// see if someone is interested in providing an own authorization identifier loader
		JDBCBaseLoader loader = CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition,
				SQLAccessControlPackage.eINSTANCE.getAuthorizationIdentifier().getInstanceClassName());

		if (loader != null) {
			MySqlAuthorizationIdentifierLoader authorizationIdLoader = (MySqlAuthorizationIdentifierLoader) loader;
			authorizationIdLoader.setCatalogObject(this);
			return authorizationIdLoader;
		}

		return new MySqlAuthorizationIdentifierLoader(this);
	}

}
