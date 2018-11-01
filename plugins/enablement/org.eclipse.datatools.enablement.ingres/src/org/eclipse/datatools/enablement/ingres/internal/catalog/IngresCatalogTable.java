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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.eclipse.datatools.connectivity.sqm.core.util.CatalogLoaderOverrideManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableConstraintLoader;
import org.eclipse.datatools.enablement.ingres.internal.loaders.IngresTableConstraintLoader;
import org.eclipse.datatools.enablement.ingres.internal.loaders.IngresTableTriggerLoader;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.common.util.EList;

/**
 * Ingres related extension of JDBCTable to provide support for loading
 * triggers and constraints.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresCatalogTable extends JDBCTable {
	
	private final Object TRIGGER_LOCK = new Object();
	
	private final Object CCS_LOCK = new Object();

	private Boolean triggersLoaded = Boolean.FALSE;

	private Boolean ccsLoaded = Boolean.FALSE;

	private transient SoftReference triggerLoaderRef;

	public EList getTriggers() {
		synchronized (TRIGGER_LOCK) {
			if (!triggersLoaded.booleanValue())
				loadTriggers();
		}
		return super.getTriggers();
	}

	public EList getConstraints() {
		synchronized (CCS_LOCK) {
			if (!ccsLoaded.booleanValue())
				loadCheckConstraints();
		}
		return super.getConstraints();
	}

	public void refresh() {
		synchronized (TRIGGER_LOCK) {
			if (triggersLoaded.booleanValue()) {
				triggersLoaded = Boolean.FALSE;
			}
		}
		synchronized (CCS_LOCK) {
			if (ccsLoaded.booleanValue()) {
				ccsLoaded = Boolean.FALSE;
			}
		}
		super.refresh();
	}

	private void loadTriggers() {
		boolean deliver = eDeliver();
		try {
			List container = super.getTriggers();
			List existingTriggers = new ArrayList(container);

			eSetDeliver(false);

			container.clear();

			getTriggerLoader().loadTriggers(container, existingTriggers);

			getTriggerLoader().clearTriggers(existingTriggers);

			triggersLoaded = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			eSetDeliver(deliver);
		}
	}

	protected final IngresTableTriggerLoader getTriggerLoader() {
		if (triggerLoaderRef == null || triggerLoaderRef.get() == null) {
			triggerLoaderRef = new SoftReference(createTriggerLoader());
		}
		return (IngresTableTriggerLoader) triggerLoaderRef.get();
	}

	protected IngresTableTriggerLoader createTriggerLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault()
				.getDatabaseDefinitionRegistry().getDefinition(
						this.getCatalogDatabase());

		JDBCBaseLoader loader = CatalogLoaderOverrideManager.INSTANCE
				.getLoaderForDatabase(databaseDefinition,
						SQLTablesPackage.eINSTANCE.getTrigger()
								.getInstanceClassName());

		if (loader != null) {
			IngresTableTriggerLoader triggerLoader = (IngresTableTriggerLoader) loader;
			triggerLoader.setCatalogObject(this);
			return triggerLoader;
		}
		return new IngresTableTriggerLoader(this);
	}

	private void loadCheckConstraints() {
		boolean deliver = eDeliver();
		try {
			List container = super.getConstraints();
			List existingCCs = internalGetCheckConstraints(container);
			container.removeAll(existingCCs);
			JDBCTableConstraintLoader loader = getConstraintLoader();
			if (loader instanceof IngresTableConstraintLoader) {
				((IngresTableConstraintLoader) loader).loadCheckConstraints(
						container, existingCCs);
			}
			ccsLoaded = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			eSetDeliver(deliver);
		}
	}

	private List internalGetCheckConstraints(Collection constraints) {
		Vector uniqueConstraints = new Vector();
		for (Iterator it = constraints.iterator(); it.hasNext();) {
			Constraint currentConstraint = (Constraint) it.next();
			if (currentConstraint instanceof CheckConstraint) {
				uniqueConstraints.add(currentConstraint);
			}
		}
		return uniqueConstraints;
	}

}
