/*******************************************************************************
 * Copyright (c) 2008 Ingres Corporation and others.
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
import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.ingres.internal.loaders.IngresTriggerSourceLoader;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresTriggerImpl;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesFactory;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.Database;

/**
 * This class represents an Ingres Trigger (Rule).
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresCatalogTrigger extends IngresTriggerImpl implements
		ICatalogObject {
	
	private final Object SOURCE_LOCK = new Object();

	private Boolean sourceLoaded = Boolean.FALSE;

	private SoftReference sourceLoaderRef;

	public Database getCatalogDatabase() {
		return this.getSchema().getCatalog().getDatabase();
	}

	public Connection getConnection() {
		Database db = this.getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}

	public void refresh() {
		synchronized (SOURCE_LOCK) {
			if (sourceLoaded.booleanValue()) {
				sourceLoaded = Boolean.FALSE;
			}
		}
		RefreshManager.getInstance().referesh(this);
	}

	public Source getSource() {
		synchronized (SOURCE_LOCK) {
			if (!sourceLoaded.booleanValue()) {
				loadSource();
			}
		}
		return super.getSource();
	}

	protected final IngresTriggerSourceLoader getSourceLoader() {
		if (sourceLoaderRef == null || sourceLoaderRef.get() == null) {
			sourceLoaderRef = new SoftReference(
					createSourceLoader());
		}
		return (IngresTriggerSourceLoader) sourceLoaderRef.get();
	}

	protected IngresTriggerSourceLoader createSourceLoader() {
		return new IngresTriggerSourceLoader(this);
	}

	private void loadSource() {
		boolean deliver = eDeliver();
		try {
			eSetDeliver(false);

			Source loadedSource = SQLRoutinesFactory.eINSTANCE.createSource();
			getSourceLoader().loadSource(this.getSubjectTable().getName(),
					this.getSchema().getName(), this.getName(), loadedSource);
			setSource(loadedSource);

			sourceLoaded = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			eSetDeliver(deliver);
		}
	}

}
