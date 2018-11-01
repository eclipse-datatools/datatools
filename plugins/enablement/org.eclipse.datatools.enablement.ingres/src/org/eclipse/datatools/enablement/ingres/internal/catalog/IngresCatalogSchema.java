/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.catalog;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.core.util.CatalogLoaderOverrideManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilterListener;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.enablement.ingres.internal.loaders.IngresDBEventLoader;
import org.eclipse.datatools.enablement.ingres.internal.loaders.IngresSequenceLoader;
import org.eclipse.datatools.enablement.ingres.internal.loaders.IngresSynonymLoader;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresSchemaImpl;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

/**
 * Ingres Schema
 * 
 * Enhances the original implementation of <code>JDBCSchema</code> to support
 * the following features:
 * <ul>
 * <li>Sequence loading</li>
 * </ul>
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresCatalogSchema extends IngresSchemaImpl {
	
	private final Object SEQUENCE_LOCK = new Object();
	
	private final Object DBEVENT_LOCK = new Object();
	
	private final Object SYNONYM_LOCK = new Object();

	private Boolean sequencesLoaded = Boolean.FALSE;

	private Boolean dbEventsLoaded = Boolean.FALSE;

	private Boolean synonymsLoaded = Boolean.FALSE;

	private transient SoftReference sequenceLoaderRef;

	private transient SoftReference dbEventLoaderRef;

	private transient SoftReference synonymLoaderRef;

	/**
	 * Registers/unregisters a filter listener to handle sequence filter events.
	 * 
	 * @see org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCSchema#basicSetCatalog(org.eclipse.datatools.modelbase.sql.schema.Catalog,
	 *      org.eclipse.emf.common.notify.NotificationChain)
	 */
	public NotificationChain basicSetCatalog(Catalog newCatalog,
			NotificationChain msgs) {
		if (catalog != null && catalog.getDatabase() != null) {
			ConnectionInfo connectionInfo = DatabaseConnectionRegistry
					.getInstance().getConnectionForDatabase(
							catalog.getDatabase());
			connectionInfo.removeFilterListener(filterListener);
		}
		if (newCatalog != null && newCatalog.getDatabase() != null) {
			ConnectionInfo connectionInfo = DatabaseConnectionRegistry
					.getInstance().getConnectionForDatabase(
							newCatalog.getDatabase());
			connectionInfo.addFilterListener(filterListener);
		}
		return super.basicSetCatalog(newCatalog, msgs);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCSchema#refresh()
	 */
	public void refresh() {
		synchronized (SEQUENCE_LOCK) {
			if (sequencesLoaded.booleanValue()) {
				sequencesLoaded = Boolean.FALSE;
			}
		}
		synchronized (DBEVENT_LOCK) {
			if (dbEventsLoaded.booleanValue()) {
				dbEventsLoaded = Boolean.FALSE;
			}
		}
		synchronized (SYNONYM_LOCK) {
			if (synonymsLoaded.booleanValue()) {
				synonymsLoaded = Boolean.FALSE;
			}
		}
		super.refresh();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl#getSequences()
	 */
	public EList getSequences() {
		synchronized (SEQUENCE_LOCK) {
			if (!sequencesLoaded.booleanValue())
				loadSequences();
		}
		return super.getSequences();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresSchemaImpl#getSynonyms()
	 */
	public EList getSynonyms() {
		synchronized (SYNONYM_LOCK) {
			if (!synonymsLoaded.booleanValue())
				loadSynonyms();
		}
		return super.getSynonyms();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresSchemaImpl#getDBEvents()
	 */
	public EList getDBEvents() {
		synchronized (DBEVENT_LOCK) {
			if (!dbEventsLoaded.booleanValue())
				loadDbEvents();
		}
		return super.getDBEvents();
	}

	protected final IngresSequenceLoader getSequenceLoader() {
		// cache the SequenceLoader for better performance
		if (sequenceLoaderRef == null || sequenceLoaderRef.get() == null) {
			sequenceLoaderRef = new SoftReference(
					createSequenceLoader());
		}
		return (IngresSequenceLoader) sequenceLoaderRef.get();
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

	protected final IngresDBEventLoader getDbEventLoader() {
		// cache the SequenceLoader for better performance
		if (dbEventLoaderRef == null || dbEventLoaderRef.get() == null) {
			dbEventLoaderRef = new SoftReference(
					createDbEventLoader());
		}
		return (IngresDBEventLoader) dbEventLoaderRef.get();
	}

	private void loadDbEvents() {
		synchronized (DBEVENT_LOCK) {
			boolean deliver = eDeliver();
			try {
				List container = super.getDBEvents();
				List existingDbEvents = new ArrayList(container);

				eSetDeliver(false);

				container.clear();
				getDbEventLoader().loadDbEvents(container, existingDbEvents);
				getDbEventLoader().clearDbEvents(existingDbEvents);

				dbEventsLoaded = Boolean.TRUE;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				eSetDeliver(deliver);
			}
		}
	}

	protected final IngresSynonymLoader getSynonymLoader() {
		// cache the SequenceLoader for better performance
		if (synonymLoaderRef == null || synonymLoaderRef.get() == null) {
			synonymLoaderRef = new SoftReference(
					createSynonymLoader());
		}
		return (IngresSynonymLoader) synonymLoaderRef.get();
	}

	private void loadSynonyms() {
		synchronized (SYNONYM_LOCK) {
			boolean deliver = eDeliver();
			try {
				List container = super.getSynonyms();
				List existingSynonyms = new ArrayList(container);

				eSetDeliver(false);

				container.clear();
				getSynonymLoader().loadSynonyms(container, existingSynonyms);
				getSynonymLoader().clearSynonyms(existingSynonyms);

				synonymsLoaded = Boolean.TRUE;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				eSetDeliver(deliver);
			}
		}
	}

	/**
	 * Creates and returns an instance of the SequenceLoader. By default an
	 * instance of the <code>IngresSequenceLoader</code> is returned. This
	 * behavior can be changed by providing an <code>overrideLoader</code>
	 * using the eclass org.eclipse.datatools.modelbase.sql.schema.Sequence.
	 * 
	 * @return An instance of IngresSequenceLoader.
	 */
	private IngresSequenceLoader createSequenceLoader() {
		// get the database definiton for the actual database
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault()
				.getDatabaseDefinitionRegistry().getDefinition(
						this.getCatalogDatabase());

		// see if someone is interested in providing an own sequence loader
		JDBCBaseLoader loader = CatalogLoaderOverrideManager.INSTANCE
				.getLoaderForDatabase(databaseDefinition,
						SQLSchemaPackage.eINSTANCE.getSequence()
								.getInstanceClassName());

		if (loader != null) {
			IngresSequenceLoader sequenceLoader = (IngresSequenceLoader) loader;
			sequenceLoader.setCatalogObject(this);
			return sequenceLoader;
		}

		return new IngresSequenceLoader(this);
	}

	/**
	 * Creates and returns an instance of the DbEventLoader. By default an
	 * instance of the <code>IngresDbEventLoader</code> is returned. This
	 * behavior can be changed by providing an <code>overrideLoader</code>
	 * using the eclass
	 * org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent
	 * 
	 * @return An instance of IngresDbEventLoader.
	 */
	private IngresDBEventLoader createDbEventLoader() {
		// get the database definiton for the actual database
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault()
				.getDatabaseDefinitionRegistry().getDefinition(
						this.getCatalogDatabase());

		// see if someone is interested in providing an own db event loader
		JDBCBaseLoader loader = CatalogLoaderOverrideManager.INSTANCE
				.getLoaderForDatabase(databaseDefinition,
						IngressqlmodelPackage.eINSTANCE.getIngresDBEvent()
								.getInstanceClassName());

		if (loader != null) {
			IngresDBEventLoader sequenceLoader = (IngresDBEventLoader) loader;
			sequenceLoader.setCatalogObject(this);
			return sequenceLoader;
		}

		return new IngresDBEventLoader(this);
	}

	/**
	 * Creates and returns an instance of the SynonymLoader. By default an
	 * instance of the <code>IngresSynonymLoader</code> is returned. This
	 * behavior can be changed by providing an <code>overrideLoader</code>
	 * using the eclass
	 * org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym
	 * 
	 * @return An instance of IngresSynonymLoader.
	 */
	private IngresSynonymLoader createSynonymLoader() {
		// get the database definiton for the actual database
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault()
				.getDatabaseDefinitionRegistry().getDefinition(
						this.getCatalogDatabase());

		// see if someone is interested in providing an own db event loader
		JDBCBaseLoader loader = CatalogLoaderOverrideManager.INSTANCE
				.getLoaderForDatabase(databaseDefinition,
						IngressqlmodelPackage.eINSTANCE.getIngresSynonym()
								.getInstanceClassName());

		if (loader != null) {
			IngresSynonymLoader synonymLoader = (IngresSynonymLoader) loader;
			synonymLoader.setCatalogObject(this);
			return synonymLoader;
		}

		return new IngresSynonymLoader(this);
	}

	/**
	 * Force refresh of loaded sequences if necessary.
	 * 
	 * @param filterKey
	 *            the id of the changed filter
	 */
	private void handleFilterChanged(String filterKey) {
		boolean refresh = false;
		ConnectionInfo conInf = DatabaseConnectionRegistry.getInstance()
				.getConnectionForDatabase(getCatalogDatabase());
		if (sequencesLoaded.booleanValue()
				&& filterKey.equals(getSequenceFilterKey())
				|| (conInf != null
						&& conInf.getFilter(getSequenceFilterKey()) == null && (ConnectionFilter.SEQUENCE_FILTER
						.equals(filterKey) || filterKey.equals(getCatalog()
						.getName()
						+ ConnectionFilter.FILTER_SEPARATOR
						+ ConnectionFilter.SEQUENCE_FILTER)))) {
			sequencesLoaded = Boolean.FALSE;
			getSequenceLoader().clearSequences(super.getSequences());
			refresh = true;
		}
		if (dbEventsLoaded.booleanValue()
				&& filterKey.equals(getDbEventFilterKey())
				|| (conInf != null
						&& conInf.getFilter(getDbEventFilterKey()) == null && (IngresCatalogUtils.DB_EVENT_FILTER
						.equals(filterKey) || filterKey.equals(getCatalog()
						.getName()
						+ ConnectionFilter.FILTER_SEPARATOR
						+ IngresCatalogUtils.DB_EVENT_FILTER)))) {
			dbEventsLoaded = Boolean.FALSE;
			getDbEventLoader().clearDbEvents(super.getDBEvents());
			refresh = true;
		}
		if (synonymsLoaded.booleanValue()
				&& filterKey.equals(getSynonymFilterKey())
				|| (conInf != null
						&& conInf.getFilter(getSynonymFilterKey()) == null && (IngresCatalogUtils.SYNONYM_FILTER
						.equals(filterKey) || filterKey.equals(getCatalog()
						.getName()
						+ ConnectionFilter.FILTER_SEPARATOR
						+ IngresCatalogUtils.SYNONYM_FILTER)))) {
			synonymsLoaded = Boolean.FALSE;
			getSynonymLoader().clearSynonyms(super.getSynonyms());
			refresh = true;
		}
		if (refresh) {
			RefreshManager.getInstance().referesh(this);
		}
	}

	private String getSequenceFilterKey() {
		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
				+ getName() + ConnectionFilter.FILTER_SEPARATOR
				+ ConnectionFilter.SEQUENCE_FILTER;
	}

	private String getDbEventFilterKey() {
		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
				+ getName() + ConnectionFilter.FILTER_SEPARATOR
				+ IngresCatalogUtils.DB_EVENT_FILTER;
	}

	private String getSynonymFilterKey() {
		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
				+ getName() + ConnectionFilter.FILTER_SEPARATOR
				+ IngresCatalogUtils.DB_EVENT_FILTER;
	}

	private final transient ConnectionFilterListener filterListener = new ConnectionFilterListener() {

		public void connectionFilterAdded(String filterKey) {
			handleFilterChanged(filterKey);
		}

		public void connectionFilterRemoved(String filterKey) {
			handleFilterChanged(filterKey);
		}

	};

}
