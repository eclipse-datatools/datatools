/*******************************************************************************
 * Copyright (c) 2011 Zenika
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: queinnec - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.postgresql.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCDatabase;
import org.eclipse.datatools.connectivity.sqm.core.util.CatalogLoaderOverrideManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.enablement.postgresql.catalog.loaders.PostgresAuthorizationIdentifierLoader;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.emf.common.util.EList;

/**
 * A PostgreSQL database.
 * 
 * @author pierre.queinnec@zenika.com
 */
public class PostgresCatalogDatabase extends JDBCDatabase {

	private final Object AUTHORIZATION_IDS_LOCK = new Object();

	private Boolean authorizationIdsLoaded = Boolean.FALSE;

	private transient SoftReference authorizationIdLoaderRef;

	public PostgresCatalogDatabase(Connection connection) {
		super(connection);
	}

	public void refresh() {
		synchronized (AUTHORIZATION_IDS_LOCK) {
			if (authorizationIdsLoaded.booleanValue()) {
				authorizationIdsLoaded = Boolean.FALSE;
			}
		}

		super.refresh();
	}

	@Override
	public EList getAuthorizationIds() {
		synchronized (AUTHORIZATION_IDS_LOCK) {
			if (!authorizationIdsLoaded.booleanValue())
				this.loadAuthorizationIdentifiers();
		}

		return super.getAuthorizationIds();
	}

	private void loadAuthorizationIdentifiers() {
		synchronized (AUTHORIZATION_IDS_LOCK) {
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
	}

	protected final PostgresAuthorizationIdentifierLoader getAuthorizationIdentifierLoader() {
		// cache the AuthorizationIdentifierLoader for better performance
		if (authorizationIdLoaderRef == null || authorizationIdLoaderRef.get() == null) {
			authorizationIdLoaderRef = new SoftReference(createAuthorizationIdentifierLoader());
		}

		return (PostgresAuthorizationIdentifierLoader) authorizationIdLoaderRef.get();
	}

	/**
	 * Creates and returns an instance of the AuthorizationIdentifierLoader. By default an instance of the
	 * <code>PostgresAuthorizationIdentifierLoader</code> is returned. This behavior can be changed by providing an
	 * <code>overrideLoader</code> using the eclass org.eclipse.datatools.modelbase.sql.accesscontrol.
	 * AuthorizationIdentifier.
	 * 
	 * @return An instance of PostgresAuthorizationIdentifierLoader.
	 */
	private PostgresAuthorizationIdentifierLoader createAuthorizationIdentifierLoader() {
		// get the database definition for the actual database
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry()
				.getDefinition(this.getCatalogDatabase());

		// see if someone is interested in providing an own authorization identifier loader
		JDBCBaseLoader loader = CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition,
				SQLAccessControlPackage.eINSTANCE.getAuthorizationIdentifier().getInstanceClassName());

		if (loader != null) {
			PostgresAuthorizationIdentifierLoader authorizationIdLoader = (PostgresAuthorizationIdentifierLoader) loader;
			authorizationIdLoader.setCatalogObject(this);
			return authorizationIdLoader;
		}

		return new PostgresAuthorizationIdentifierLoader(this);
	}

}
