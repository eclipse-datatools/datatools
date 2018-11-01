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
import org.eclipse.datatools.enablement.ingres.internal.loaders.IngresTableConstraintDefinitionLoader;
import org.eclipse.datatools.modelbase.sql.constraints.impl.CheckConstraintImpl;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsFactory;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.schema.Database;

/**
 * Ingres related extension of CheckConstraint to provide support for loading the
 * constraint definition.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresCatalogCheckConstraint extends CheckConstraintImpl implements
		ICatalogObject {
	
	private final Object SEARCHCONDITION_LOCK = new Object();

	private Boolean searchConditionLoaded = Boolean.FALSE;

	private SoftReference constraintDefinitionLoaderRef;

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject#getCatalogDatabase()
	 */
	public Database getCatalogDatabase() {
		return getBaseTable().getSchema().getCatalog().getDatabase();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject#getConnection()
	 */
	public Connection getConnection() {
		Database db = getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject#refresh()
	 */
	public void refresh() {
		synchronized (SEARCHCONDITION_LOCK) {
			if (searchConditionLoaded.booleanValue()) {
				searchConditionLoaded = Boolean.FALSE;
			}
		}
		RefreshManager.getInstance().referesh(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.CheckConstraintImpl#getSearchCondition()
	 */
	public SearchCondition getSearchCondition() {
		synchronized (SEARCHCONDITION_LOCK) {
			if (!searchConditionLoaded.booleanValue()) {
				loadSearchCondition();
			}
		}
		return super.getSearchCondition();
	}

	protected final IngresTableConstraintDefinitionLoader getConstraintDefinitionLoader() {
		if (constraintDefinitionLoaderRef == null || constraintDefinitionLoaderRef.get() == null) {
			constraintDefinitionLoaderRef = new SoftReference(
					createConstraintDefinitionLoader());
		}
		return (IngresTableConstraintDefinitionLoader) constraintDefinitionLoaderRef.get();
	}

	protected IngresTableConstraintDefinitionLoader createConstraintDefinitionLoader() {
		return new IngresTableConstraintDefinitionLoader(this);
	}

	private void loadSearchCondition() {
		boolean deliver = eDeliver();
		try {
			eSetDeliver(false);

			SearchCondition loadedSearchCondition = SQLExpressionsFactory.eINSTANCE
					.createSearchConditionDefault();
			getConstraintDefinitionLoader().loadCheckConstraintDefinition(
					getBaseTable().getSchema().getName(),
					getBaseTable().getName(), getName(), loadedSearchCondition);
			setSearchCondition(loadedSearchCondition);

			searchConditionLoaded = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			eSetDeliver(deliver);
		}
	}

}
