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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCSchema;
import org.eclipse.datatools.connectivity.sqm.core.util.CatalogLoaderOverrideManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.enablement.postgresql.catalog.loaders.PostgresSequenceLoader;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.common.util.EList;

/**
 * PostgreSQL Schema.
 * 
 * Enhances the original implementation of <code>JDBCSchema</code> to support
 * the following features:
 * <ul>
 * <li>Sequence loading</li>
 * </ul>
 * 
 * @author pierre.queinnec@zenika.com
 */
public class PostgresCatalogSchema extends JDBCSchema {

	private final Object SEQUENCE_LOCK = new Object();

	private Boolean sequencesLoaded = Boolean.FALSE;

	private transient SoftReference sequenceLoaderRef;

	public void refresh() {
		synchronized (SEQUENCE_LOCK) {
			if (sequencesLoaded.booleanValue()) {
				sequencesLoaded = Boolean.FALSE;
			}
		}

		super.refresh();
	}

	public EList getSequences() {
		synchronized (SEQUENCE_LOCK) {
			if (!sequencesLoaded.booleanValue())
				this.loadSequences();
		}

		return super.getSequences();
	}

	protected final PostgresSequenceLoader getSequenceLoader() {
		// cache the SequenceLoader for better performance
		if (sequenceLoaderRef == null || sequenceLoaderRef.get() == null) {
			sequenceLoaderRef = new SoftReference(createSequenceLoader());
		}

		return (PostgresSequenceLoader) sequenceLoaderRef.get();
	}

	private void loadSequences() {
		synchronized (SEQUENCE_LOCK) {
			boolean deliver = eDeliver();
			try {
				List container = super.getSequences();
				List existingSequences = new ArrayList(container);

				eSetDeliver(false);

				container.clear();
				getSequenceLoader().loadSequences(container, existingSequences);
				getSequenceLoader().clearSequences(existingSequences);

				sequencesLoaded = Boolean.TRUE;

			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				eSetDeliver(deliver);
			}
		}
	}

	/**
	 * Creates and returns an instance of the SequenceLoader. By default an
	 * instance of the <code>PostgresSequenceLoader</code> is returned. This
	 * behavior can be changed by providing an <code>overrideLoader</code> using
	 * the eclass org.eclipse.datatools.modelbase.sql.schema.Sequence.
	 * 
	 * @return An instance of PostgresSequenceLoader.
	 */
	private PostgresSequenceLoader createSequenceLoader() {
		// get the database definiton for the actual database
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault()
				.getDatabaseDefinitionRegistry()
				.getDefinition(this.getCatalogDatabase());

		// see if someone is interested in providing an own sequence loader
		JDBCBaseLoader loader = CatalogLoaderOverrideManager.INSTANCE
				.getLoaderForDatabase(databaseDefinition,
						SQLSchemaPackage.eINSTANCE.getSequence()
								.getInstanceClassName());

		if (loader != null) {
			PostgresSequenceLoader sequenceLoader = (PostgresSequenceLoader) loader;
			sequenceLoader.setCatalogObject(this);
			return sequenceLoader;
		}

		return new PostgresSequenceLoader(this);
	}

}
